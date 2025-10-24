package com.sds.secframework.singleIF.service.impl;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.rpc.ServiceException;

import mySingle.service.MLCancelDeliveryServiceLocator;
import mySingle.service.MLDeliveryStatusInquiryServiceLocator;
import mySingle.service.MLSendSecuServiceLocator;
import mySingle.service.MLSendServiceLocator;

import org.apache.commons.collections.map.ListOrderedMap;
import org.w3c.dom.Element;

import samsung.esb.common.vo.ESBAuthVO;
import samsung.esb.common.vo.ESBFaultVO;
import samsung.esb.mail.secuservice.MLSendSecuService;
import samsung.esb.mail.service.MLCancelDeliveryService;
import samsung.esb.mail.service.MLDeliveryStatusInquiryService;
import samsung.esb.mail.service.MLSendService;
import samsung.esb.mail.vo.AttachEtyCSVO;
import samsung.esb.mail.vo.DrmCSVO;
import samsung.esb.mail.vo.HeaderHelperCSVO;
import samsung.esb.mail.vo.RecipientESBVO;
import samsung.esb.mail.vo.RecipientEtyCSVO;
import samsung.esb.mail.vo.ResourceCSVO;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.EsbUtil;
import com.sds.secframework.common.util.FormatUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.singleIF.dto.MailVO;
import com.sds.secframework.singleIF.service.EsbMailService;
import com.sec.common.clmsfile.dto.ComFileVO;

import anyframe.core.query.QueryServiceException;

/**
 * <P>Single ESB 메일 Class</P>
 *
 * [지원기능]<BR>
 * <BR>
 * - [배치작업]메일발송 : 일정주기별로 전송대기중인 건을 읽어와서 메일전송 후 결과를 메일발신 Header테이블에 반영<BR>
 * - 메일전송 : ESB메일전송<BR>
 * - 메일발송취소<BR>
 * - 메일수신상태조회 : 메일수신상태 조회 및 수신상태 정보 업데이트 수행<BR>
 * <br>
 * [외부 Transaction에 종속적으로 작동시킬 경우]<BR>
 * 각 메소드별 try ~ catch 문을 주석처리후 상위로 예외를 throw 하여 처리하도록 한다.<BR>
 *
 * @version com.sds.secframework.singleIF V1.0
 * 작성일 : 2009/12/18
 * @author 금현서, ace4u_khs@samsung.com
 */
public class EsbMailServiceImpl extends CommonServiceImpl implements EsbMailService {

	private String esbMailId = "";
	private String esbMailPw = "";
	private String esbDevTF  = "";

	public void setEsbMailId(String esbMailId) {
		this.esbMailId = esbMailId;
	}

	public void setEsbMailPw(String esbMailPw) {
		this.esbMailPw = esbMailPw;
	}

	public void setEsbDevTF(String esbDevTF) {
		this.esbDevTF = esbDevTF;
	}

	/************************************************************************************
	 ***********************              ESB 메일연계        **************************
	 ************************************************************************************/
	/**
	 * <P>[배치작업]메일발송</P>
	 * 일정주기별로 전송대기중인 건을 읽어와서 메일전송 후 결과를 메일발신 Header테이블에 반영한다.
	 * 테이블 : TB_COM_HEADER_HELPER_CSVO
	 *
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public synchronized void sendMailBatch() {
		String ls_moduleId = "";
		String ls_misId    = "";

		boolean cronServerCheck=false;
		boolean isException=false;
		
		try {
			cronServerCheck=super.comUtilService.isCronServer();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		 
			//if (super.comUtilService.isCronServerAndPort()) {
			if (cronServerCheck) {
				MLSendServiceLocator serviceLoacator = new MLSendServiceLocator();
				MLSendService mlSendService=null;
				try {
					mlSendService = serviceLoacator.getMLSendService_InboundPort();
				} catch (ServiceException e1) {
					e1.printStackTrace();
					return;
				}

				// 메일상태가 진행중인건을 가져온다.
				HashMap targetMap = new HashMap();
				targetMap.put("status", "0");

				ArrayList targetList=null;
				try {
					targetList = (ArrayList)commonDAO.list("secfw.singleIF.mail.listTargetList", targetMap);
				} catch (QueryServiceException e1) {
					e1.printStackTrace();
				}

				if (targetList != null && targetList.size() > 0) {
					ESBAuthVO esbAuthVO = EsbUtil.getESBAuthVO(esbMailId, esbMailPw);

					for (int i=0; i<targetList.size(); i++) {
						ListOrderedMap resultTargetMap = (ListOrderedMap)targetList.get(i);

						ls_moduleId = (String)resultTargetMap.get("module_id");
						ls_misId    = (String)resultTargetMap.get("mis_id");

						String ls_body                = "";
						String ls_bthml_content_check = (String)resultTargetMap.get("bhtml_content_check");
						String ls_fileCount           = (String)resultTargetMap.get("ifile_count");

						if ("true".equals(ls_bthml_content_check)) {
							ls_body = StringUtil.convertCharsToHtml((String)resultTargetMap.get("body"));
						} else {
							ls_body = (String)resultTargetMap.get("body");
						}

						AttachEtyCSVO[] attachEtyCSVO = null;

						if ("0".equals(ls_fileCount)) {
							attachEtyCSVO = new AttachEtyCSVO[0];
						} else {
							attachEtyCSVO =getAttachEtyCSVOArr(ls_moduleId, ls_misId);
						}

						String ls_returnMsg=null;
						try {
							ls_returnMsg = mlSendService.sendMISMail(esbAuthVO
																			, ls_body
																			, getHeaderHelperCSVO(ls_moduleId, ls_misId)
																			, getRecipientEtytCSVO(ls_moduleId, ls_misId)
																			, attachEtyCSVO
																			, getResourceCSVO(ls_moduleId, ls_misId));
							
							getLogger().debug("returnMsg : " + ls_returnMsg);
						} catch(ESBFaultVO fault) {
							fault.printStackTrace();
							isException=true;
							try {
								// 에러로그
								insertError(ls_moduleId, ls_misId, fault);

								// Header 상태를 업데이트 한다.
								HashMap modifyStatusMap = new HashMap();
								modifyStatusMap.put("status","E");
								modifyStatusMap.put("module_id",ls_moduleId);
								modifyStatusMap.put("mis_id",ls_misId);

								commonDAO.modify("secfw.singleIF.mail.modifyStatus", modifyStatusMap);
							} catch (SQLException e) {
								
								getLogger().error("SQLException : " + e);
								e.printStackTrace();
							} catch (Exception e) {
								getLogger().error("Exception : " + e);
								e.printStackTrace();
							}
						} catch (SQLException e) {
							isException=true;
							getLogger().error( "SQLException : " + e);
							e.printStackTrace();
						} catch (Exception e) {
							isException=true;
							getLogger().error( "Exception : " + e);
							e.printStackTrace();
						}
						

						// Header 상태를 업데이트 한다.
						if(!isException){
							HashMap modifyStatusMap = new HashMap();
							modifyStatusMap.put("status","1");
							modifyStatusMap.put("msg_key",ls_returnMsg);
							modifyStatusMap.put("module_id",ls_moduleId);
							modifyStatusMap.put("mis_id",ls_misId);

							try {
								commonDAO.modify("secfw.singleIF.mail.modifyStatus", modifyStatusMap);
							} catch (QueryServiceException e) {
								e.printStackTrace();
							}
						}
						isException=false;
						
					}
				} else {
					
				}
			}
	
	}

	/**
	 * <P>메일발송</P>
	 * 메일관련 테이블에 전송 데이터를 입력한 후, ESB메일전송을 수행.
	 *
	 * @param String argModuleId, String argMisId
	 * @return 메일전송 결과 (성공:true, 실패:false)
	 */
	public boolean sendMail(String argModuleId, String argMisId) {

		boolean result = false;

		try {

			MLSendServiceLocator serviceLoacator = new MLSendServiceLocator();
			MLSendService mlSendService = serviceLoacator.getMLSendService_InboundPort();

			// 메일Header 정보를 가져온다.
			HashMap getMailHeaderMap = new HashMap();
			getMailHeaderMap.put("module_id",argModuleId);
			getMailHeaderMap.put("mis_id",argMisId);

			ListOrderedMap targetMap = new ListOrderedMap();
			List tempList = commonDAO.list("secfw.singleIF.mail.getMailHeader", getMailHeaderMap);

			if(tempList != null && tempList.size()>0) {
				targetMap = (ListOrderedMap)tempList.get(0);
			}

			ESBAuthVO esbAuthVO = EsbUtil.getESBAuthVO(esbMailId, esbMailPw);

			String ls_fileCount = (String)targetMap.get("ifile_count");
			String ls_bthml_content_check = (String)targetMap.get("bhtml_content_check");

			String ls_body = "";

			if("true".equals(ls_bthml_content_check)) {
				ls_body = StringUtil.convertCharsToHtml((String)targetMap.get("body"));
			} else {
				ls_body = (String)targetMap.get("body");
			}

			AttachEtyCSVO[] attachEtyCSVO = null;
			if("0".equals(ls_fileCount)) {
				attachEtyCSVO = new AttachEtyCSVO[0];
			} else {
				attachEtyCSVO =getAttachEtyCSVOArr(argModuleId, argMisId);
			}

			String ls_returnMsg = mlSendService.sendMISMail(esbAuthVO
					, ls_body
					, getHeaderHelperCSVO(argModuleId, argMisId)
					, getRecipientEtytCSVO(argModuleId, argMisId)
					, attachEtyCSVO
					, getResourceCSVO(argModuleId, argMisId)
			);
			getLogger().debug( "returnMsg :" + ls_returnMsg);

			// Header 상태를 업데이트 한다.
			HashMap modifyStatusMap = new HashMap();
			modifyStatusMap.put("status","1");
			modifyStatusMap.put("msg_key",ls_returnMsg);
			modifyStatusMap.put("module_id",argModuleId);
			modifyStatusMap.put("mis_id",argMisId);

			commonDAO.modify("secfw.singleIF.mail.modifyStatus", modifyStatusMap);

			result = true;

		} catch(ESBFaultVO fault) {
			fault.printStackTrace();
			try {
				//에러로그
				insertError(argModuleId, argMisId, fault);

				// Header 상태를 업데이트 한다.
				HashMap modifyStatusMap = new HashMap();
				modifyStatusMap.put("status","E");
				modifyStatusMap.put("module_id",argModuleId);
				modifyStatusMap.put("mis_id",argMisId);

				commonDAO.modify("secfw.singleIF.mail.modifyStatus", modifyStatusMap);

			} catch (SQLException e) {
	        	getLogger().error( "  SQLException : " + e);
            	e.printStackTrace();
	        } catch (Exception e) {
	        	getLogger().error( "  Exception : " + e);
            	e.printStackTrace();
	        }
		} catch (SQLException e) {
        	getLogger().error( "  SQLException : " + e);
        	e.printStackTrace();
        } catch (Exception e) {
        	getLogger().error( "  Exception : " + e);
        	e.printStackTrace();
        } finally{

        	return result;
        }
	}

	/**
	 * <P>보안메일발송</P>
	 * 메일관련 테이블에 전송 데이터를 입력한 후, ESB메일전송을 수행.
	 *
	 * @param String argModuleId, String argMisId
	 * @return 메일전송 결과 (성공:true, 실패:false)
	 */
	public boolean sendMailSecu(String argModuleId, String argMisId) {

		boolean result = false;

		try {

			MLSendSecuServiceLocator serviceLoacator = new MLSendSecuServiceLocator();
			MLSendSecuService mlSendService = serviceLoacator.getMLSendSecuService_InboundPort();

			// 메일Header 정보를 가져온다.
			HashMap getMailHeaderMap = new HashMap();
			getMailHeaderMap.put("module_id",argModuleId);
			getMailHeaderMap.put("mis_id",argMisId);

			ListOrderedMap targetMap = new ListOrderedMap();
			List tempList = commonDAO.list("secfw.singleIF.mail.getMailHeader", getMailHeaderMap);

			if(tempList != null && tempList.size()>0) {
				targetMap = (ListOrderedMap)tempList.get(0);
			}

			ESBAuthVO esbAuthVO = EsbUtil.getESBAuthVO(esbMailId, esbMailPw);

			String ls_fileCount = (String)targetMap.get("ifile_count");
			String ls_bthml_content_check = (String)targetMap.get("bhtml_content_check");

			String ls_body = "";

			if("true".equals(ls_bthml_content_check)) {
				ls_body = StringUtil.convertCharsToHtml((String)targetMap.get("body"));
			} else {
				ls_body = (String)targetMap.get("body");
			}

			AttachEtyCSVO[] attachEtyCSVO = null;
			if("0".equals(ls_fileCount)) {
				attachEtyCSVO = new AttachEtyCSVO[0];
			} else {
				attachEtyCSVO =getAttachEtyCSVOArr(argModuleId, argMisId);
			}

			String ls_returnMsg = mlSendService.sendMISMailSecu(esbAuthVO
					, ls_body
					, getHeaderHelperCSVO(argModuleId, argMisId)
					, getRecipientEtytCSVO(argModuleId, argMisId)
					, attachEtyCSVO
					, getResourceCSVO(argModuleId, argMisId)
					, getDrmCSVO(argModuleId, argMisId)
			);
			getLogger().debug( "returnMsg :" + ls_returnMsg);

			// Header 상태를 업데이트 한다.
			HashMap modifyStatusMap = new HashMap();
			modifyStatusMap.put("status","1");
			modifyStatusMap.put("msg_key",ls_returnMsg);
			modifyStatusMap.put("module_id",argModuleId);
			modifyStatusMap.put("mis_id",argMisId);

			commonDAO.modify("secfw.singleIF.mail.modifyStatus", modifyStatusMap);

			result = true;

		} catch(ESBFaultVO fault) {
			fault.printStackTrace();
			try {
				//에러로그
				insertError(argModuleId, argMisId, fault);

				// Header 상태를 업데이트 한다.
				HashMap modifyStatusMap = new HashMap();
				modifyStatusMap.put("status","E");
				modifyStatusMap.put("module_id",argModuleId);
				modifyStatusMap.put("mis_id",argMisId);

				commonDAO.modify("secfw.singleIF.mail.modifyStatus", modifyStatusMap);

			} catch (SQLException e) {
	        	getLogger().error( "  SQLException : " + e);
            	e.printStackTrace();
	        } catch (Exception e) {
	        	getLogger().error( "  Exception : " + e);
            	e.printStackTrace();
	        }
		} catch (SQLException e) {
        	getLogger().error( "  SQLException : " + e);
        	e.printStackTrace();
        } catch (Exception e) {
        	getLogger().error( "  Exception : " + e);
        	e.printStackTrace();
        } finally{

        	return result;
        }
	}

	/**
	 * <P>메일발송 취소</P>
	 * 메일발송 취소를 수행 -> 수신인 테이블의 상태변경
	 * 테이블 : TB_COM_RECIPIENT_ETY_CSVO
	 *
	 * @param argModuleId : 모듈아이디, argMisId:MIS ID, argPassword:비밀번호
	 * @return 메일발송 취소결과 (성공:true, 실패:false)
	 */
	public boolean cancelMail(String argModuleId, String argMisId, String argPassword) {

		boolean result = false;

		try {

			MLCancelDeliveryServiceLocator serviceLocator = new MLCancelDeliveryServiceLocator();
			MLCancelDeliveryService cancelService = serviceLocator.getMLCancelDeliveryService_InboundPort();

			ESBAuthVO esbAuthVO = EsbUtil.getESBAuthVO(esbMailId, esbMailPw);

			String msgKey = "";

			//메일의 Message Key값을 가져온다.
			HashMap getMailHeaderMap = new HashMap();
			getMailHeaderMap.put("module_id",argModuleId);
			getMailHeaderMap.put("mis_id",argMisId);

			ListOrderedMap getMailHeaderResult = new ListOrderedMap();
			List tempList = commonDAO.list("secfw.singleIF.mail.getMailHeader", getMailHeaderMap);

			if(tempList != null && tempList.size()>0) {
				getMailHeaderResult = (ListOrderedMap)tempList.get(0);
			}

			if(getMailHeaderResult != null) {
				msgKey = (String)getMailHeaderResult.get("msg_key");
			}

			//발신자 정보를 가져온다.
			ResourceCSVO resourceCSVO = getResourceCSVO(argModuleId, argMisId);
			resourceCSVO.setPassword(EsbUtil.getMD5_Base64(argPassword));

			//수신인의 메일주소 리스트를 가져온다.
			RecipientEtyCSVO[] recipientEtyCSVOs = getRecipientEtytCSVO(argModuleId, argMisId);
			String[] recipientArray = new String[recipientEtyCSVOs.length];
			for(int i=0; i<recipientEtyCSVOs.length;i++) {
				RecipientEtyCSVO recipientEtyCSVO = recipientEtyCSVOs[i];
				recipientArray[i] = recipientEtyCSVO.getRecAddr();
			}

			String ls_returnMsg = cancelService.cancelMISMailByRecipient(
					  esbAuthVO
					, msgKey
					, recipientArray
					, resourceCSVO
			);
			getLogger().debug( "returnMsg :" + ls_returnMsg);

			if("SUCCESS".equals(ls_returnMsg.toUpperCase())) {
				// 수신인 정보를 취소 상태로 변경한다.
				HashMap cancelMailMap = new HashMap();
				cancelMailMap.put("module_id",argModuleId);
				cancelMailMap.put("mis_id",argMisId);
				cancelMailMap.put("status","MT_T_SENT_CANCEL_STATUS");

				commonDAO.modify("secfw.singleIF.mail.cancelMail", cancelMailMap);

				result = true;
			}

			return result;

		} catch(ESBFaultVO fault) {
		    fault.printStackTrace();
			try {
				//에러로그
				insertError(argModuleId, argMisId, fault);

			} catch (SQLException e) {
	        	getLogger().error( "  SQLException : " + e);
            	e.printStackTrace();
	        } catch (Exception e) {
	        	getLogger().error( "  Exception : " + e);
            	e.printStackTrace();
	        }
		} catch (SQLException e) {
        	getLogger().error( "  SQLException : " + e);
        	e.printStackTrace();
        } catch (Exception e) {
        	getLogger().error( "  Exception : " + e);
        	e.printStackTrace();
        } finally{
        	return result;
        }
	}

	/**
	 * <P>메일상태조회</P>
	 * 메일수신상태 조회. 수신상태 정보 업데이트 수행
	 *
	 * @param argModuleId : 모듈아이디, argMisId:MIS ID, argPassword:비밀번호
	 * @return 메일상태 조회결과
	 */
	public RecipientESBVO[] getRecipientStatus(String argModuleId, String argMisId) {

		RecipientESBVO[] recipientESBVOs = null;

		try {

			MLDeliveryStatusInquiryServiceLocator serviceLocator = new MLDeliveryStatusInquiryServiceLocator();
			MLDeliveryStatusInquiryService inquiryService = serviceLocator.getMLDeliveryStatusInquiryService_InboundPort();

			ESBAuthVO esbAuthVO = EsbUtil.getESBAuthVO(esbMailId, esbMailPw);

			String msgKey = "";

			//메일의 Message Key값을 가져온다.
			HashMap getMailHeaderMap = new HashMap();
			getMailHeaderMap.put("module_id",argModuleId);
			getMailHeaderMap.put("mis_id",argMisId);

			ListOrderedMap getMailHeaderResult = new ListOrderedMap();
			List tempList = commonDAO.list("secfw.singleIF.mail.getMailHeader", getMailHeaderMap);

			if(tempList != null && tempList.size()>0) {
				getMailHeaderResult = (ListOrderedMap)tempList.get(0);
			}

			if(getMailHeaderResult != null) {
				msgKey = (String)getMailHeaderResult.get("msg_key");
			}

			recipientESBVOs = inquiryService.getRecipientStatus(
					  esbAuthVO
					, msgKey
					, getResourceCSVO(argModuleId, argMisId)
			);

			//수신인 정보를 변경.
			if(recipientESBVOs!=null && recipientESBVOs.length>0) {

				for(int i=0; i<recipientESBVOs.length;i++) {

					HashMap modifyMailRecipientMap = new HashMap();

					modifyMailRecipientMap.put("module_id",argModuleId);
					modifyMailRecipientMap.put("mis_id",argMisId);
					modifyMailRecipientMap.put("rec_addr",recipientESBVOs[i].getEmail());
					modifyMailRecipientMap.put("name",recipientESBVOs[i].getName());
					modifyMailRecipientMap.put("position",recipientESBVOs[i].getPosition());
					modifyMailRecipientMap.put("department",recipientESBVOs[i].getDepartment());
					modifyMailRecipientMap.put("company",recipientESBVOs[i].getCompany());
					modifyMailRecipientMap.put("epid",recipientESBVOs[i].getEpID());
					modifyMailRecipientMap.put("send_time",EsbUtil.getDate(recipientESBVOs[i].getSendTime()));
					modifyMailRecipientMap.put("delv_time",EsbUtil.getDate(recipientESBVOs[i].getDelvTime()));
					modifyMailRecipientMap.put("seen_time",EsbUtil.getDate(recipientESBVOs[i].getSeenTime()));
					modifyMailRecipientMap.put("status",recipientESBVOs[i].getStatus());

					commonDAO.modify("secfw.singleIF.mail.modifyMailRecipient", modifyMailRecipientMap);

				}

			}

			//메일 수신상태 결과 리턴
			return recipientESBVOs;
//			HashMap listMailRecipientMap = new HashMap();
//
//			listMailRecipientMap.put("module_id",argModuleId);
//			listMailRecipientMap.put("mis_id",argMisId);
//
//			result = (ArrayList)commonDAO.list("secfw.singleIF.mail.listMailRecipient", listMailRecipientMap);
//			return result;

		} catch(ESBFaultVO fault) {
		    fault.printStackTrace();
			try {
				//에러로그
				insertError(argModuleId, argMisId, fault);

			} catch (SQLException e) {
	        	getLogger().error( "  SQLException : " + e);
            	e.printStackTrace();
	        } catch (Exception e) {
	        	getLogger().error( "  Exception : " + e);
            	e.printStackTrace();
	        }
		} catch (Exception e) {
        	getLogger().error( "  Exception : " + e);
        	e.printStackTrace();
        } finally {
        	return recipientESBVOs;
        }
	}

	/**
	 * <P>메일 Header 정보 조회</P>
	 * 메일 Header 정보를 읽어온다.
	 * 테이블 : TB_COM_HEADER_HELPER_CSVO
	 *
	 * @param argModuleId:모듈ID,  argMisId:MIS ID(ESB연동KEY)
	 * @return HeaderHelperCSVO 메일헤더 VO
	 */
	private HeaderHelperCSVO getHeaderHelperCSVO(String argModuleId,String argMisId) {
        HeaderHelperCSVO 	headerHelperCSVO= new HeaderHelperCSVO();
		try {
			String fld_val = "";

			//  메일 Header 정보을 가져온다.
			HashMap getMailHeaderMap = new HashMap();
			getMailHeaderMap.put("module_id",argModuleId);
			getMailHeaderMap.put("mis_id",argMisId);

			ListOrderedMap result = new ListOrderedMap();
			List tempList = commonDAO.list("secfw.singleIF.mail.getMailHeader", getMailHeaderMap);

			if(tempList != null && tempList.size()>0) {
				result = (ListOrderedMap)tempList.get(0);
			}

			if(result != null) {

				headerHelperCSVO.setSubject((String)result.get("subject"));
				headerHelperCSVO.setMsgType((String)result.get("msg_type"));
				headerHelperCSVO.setBHtmlContentCheck(new Boolean((String)result.get("bhtml_content_check")));
				headerHelperCSVO.setTimeZone((String)result.get("time_zone"));
				headerHelperCSVO.setIsDst(new Boolean((String)result.get("is_dst")));
				headerHelperCSVO.setIFileCount(new Integer((String)result.get("ifile_count")));

				fld_val = (String)result.get("sender_mail_addr");

				if( fld_val.indexOf("@") == -1 ){
					if("DEV".equals(esbDevTF)) {
						fld_val = (String)result.get("sender_single_id") + "@stage.samsung.com";
					} else {
						fld_val = (String)result.get("sender_single_id") + "@samsung.com";
					}
				}

				if("DEV".equals(esbDevTF)) {
					headerHelperCSVO.setSenderMailAddr(fld_val.substring(0, fld_val.indexOf("@")) + "@stage.samsung.com");
				} else {
					headerHelperCSVO.setSenderMailAddr(fld_val);
				}
			}


		} catch (Exception e) {
        	getLogger().error( "  Exception : " + e);
        }

		return headerHelperCSVO;
	}

	/**
	 * <P>보안메일 정보조회</P>
	 * 보안메일 정보를 읽어온다.
	 * 테이블 : TB_COM_HEADER_HELPER_CSVO
	 *
	 * @param argModuleId:모듈ID,  argMisId:MIS ID(ESB연동KEY)
	 * @return HeaderHelperCSVO 메일헤더 VO
	 */
	private DrmCSVO getDrmCSVO(String argModuleId,String argMisId) {
		DrmCSVO drmCSVO= new DrmCSVO();
		try {

			//  메일 Header 정보을 가져온다.
			HashMap getDrmMap = new HashMap();
			getDrmMap.put("module_id",argModuleId);
			getDrmMap.put("mis_id",argMisId);

			ListOrderedMap result = new ListOrderedMap();
			List tempList = commonDAO.list("secfw.singleIF.mail.getDrmInfo", getDrmMap);

			if(tempList != null && tempList.size()>0) {
				result = (ListOrderedMap)tempList.get(0);
			}

			if(result != null) {

				drmCSVO.setDrmType(new Integer((String)result.get("drm_type")));
				drmCSVO.setDrmCanPrint(new Integer((String)result.get("drm_can_print")));
				drmCSVO.setDrmCanSave(new Integer((String)result.get("drm_can_save")));
				drmCSVO.setDrmUseCount(new Integer((String)result.get("drm_use_count")));
				drmCSVO.setDrmPCCount(new Integer((String)result.get("drm_pc_count")));
				drmCSVO.setDrmValidDays(new Integer((String)result.get("drm_valid_days")));
				drmCSVO.setDrmConfirmMail4Int(new Integer((String)result.get("drm_confirm_mail4int")));
				drmCSVO.setDrmConfirmMail4Ext(new Integer((String)result.get("drm_confirm_mail4ext")));
				drmCSVO.setDrmCanViewRcpt(new Integer((String)result.get("drm_can_view_rcpt")));

			}


		} catch (Exception e) {
        	getLogger().error( "  Exception : " + e);
        }

		return drmCSVO;
	}

	/**
	 * <P>메일 발신자 정보 조회</P>
	 * 메일 발신자 정보를 읽어온다.
	 * 테이블 : TB_COM_RESOURCE_CSVO
	 *
	 * @param argModuleId:모듈ID,  argMisId:MIS ID(ESB연동KEY)
	 * @return ResourceCSVO 메일발신자 VO
	 */
	private ResourceCSVO getResourceCSVO(String argModuleId,String argMisId) {
		ResourceCSVO 		resourceCSVO 	= new ResourceCSVO();
		try {
			String fld_val = "";

			//  발신자 정보.
			HashMap getMailResourceMap = new HashMap();
			getMailResourceMap.put("module_id",argModuleId);
			getMailResourceMap.put("mis_id",argMisId);

			ListOrderedMap result = new ListOrderedMap();
			List tempList = commonDAO.list("secfw.singleIF.mail.getMailResource", getMailResourceMap);

			if(tempList != null && tempList.size()>0) {
				result = (ListOrderedMap)tempList.get(0);
			}

			if(result != null) {
				fld_val = (String)result.get("email");

				if( fld_val.indexOf("@") == -1 ){
					if("DEV".equals(esbDevTF)) {
						fld_val = fld_val + "@stage.samsung.com";
					} else {
						fld_val = fld_val + "@samsung.com";
					}
				}

				if("DEV".equals(esbDevTF)) {
					resourceCSVO.setEmail(fld_val.substring(0, fld_val.indexOf("@")) + "@stage.samsung.com");
				} else {
					resourceCSVO.setEmail((String)result.get("email"));
				}
				resourceCSVO.setLocale((String)result.get("locale"));
				resourceCSVO.setEncoding((String)result.get("encoding"));
				//resourceCSVO.setPassword((String)result.get("PASSWORD"));

			}

		} catch (Exception e) {
        	getLogger().error( "  Exception : " + e);
        }

		return resourceCSVO;
	}

	/**
	 * <P>메일 수신자 정보 조회</P>
	 * 메일 수신자 정보를 읽어온다.
	 * 테이블 : TB_COM_RECIPIENT_ETY_CSVO
	 *
	 * @param argModuleId:모듈ID,  argMisId:MIS ID(ESB연동KEY)
	 * @return RecipientEtyCSVO 메일수신자 VO
	 */
	private RecipientEtyCSVO[] getRecipientEtytCSVO(String argModuleId,String argMisId) throws Exception {
		RecipientEtyCSVO[] 	recipientEtytCSVO	= null;
        int					li_total			= 0;
        String 				fld_val 			= "";

		// 수신자 정보
		HashMap listMailRecipientMap = new HashMap();
		listMailRecipientMap.put("module_id",argModuleId);
		listMailRecipientMap.put("mis_id",argMisId);
		ArrayList list = (ArrayList)commonDAO.list("secfw.singleIF.mail.listMailRecipient", listMailRecipientMap);

		ListOrderedMap countMap = new ListOrderedMap();
		
		if(null != list && list.size() > 0){
			countMap = (ListOrderedMap)list.get(0);
			
			li_total = FormatUtil.formatInt(countMap.get("total_cnt"));

			recipientEtytCSVO = new RecipientEtyCSVO[li_total];

			for(int i=0; i<list.size(); i++){

				ListOrderedMap temp = (ListOrderedMap)list.get(i);

				fld_val = (String)temp.get("rec_addr");

				if( fld_val.indexOf("@") == -1 ){
					if("DEV".equals(esbDevTF)) {
						fld_val = fld_val + "@stage.samsung.com";
					} else {
						fld_val = fld_val + "@samsung.com";
					}
				}

				recipientEtytCSVO[i] = new RecipientEtyCSVO();
				if("DEV".equals(esbDevTF)) {
					recipientEtytCSVO[i].setRecAddr(fld_val.substring(0, fld_val.indexOf("@")) + "@stage.samsung.com");
				} else {
					recipientEtytCSVO[i].setRecAddr(fld_val);
				}

				recipientEtytCSVO[i].setISeqID(new Integer((String)temp.get("iseq_id")));
				recipientEtytCSVO[i].setRecType((String)temp.get("rec_type"));

			}
		}
		
		return recipientEtytCSVO;

	}

	/**
	 * <P>메일 첨부파일 정보 조회</P>
	 * 메일 첨부파일 정보를 읽어온다.
	 * 테이블 : TB_COM_ATTACH_ETY_CSVO
	 *
	 * @param argModuleId:모듈ID,  argMisId:MIS ID(ESB연동KEY)
	 * @return AttachEtyCSVO 메일첨부파일 VO
	 */
	private AttachEtyCSVO[] getAttachEtyCSVOArr(String argModuleId,String argMisId) {
		AttachEtyCSVO[] 	attachEtyCSVO 	= null;
        File 				file 			= null;
		FileDataSource 		fds 			= null;
		DataHandler 		dh 				= null;
		int					li_total		= 0;
		try {

			// 첨부파일 정보
			HashMap listAttachMap = new HashMap();
			listAttachMap.put("module_id",argModuleId);
			listAttachMap.put("mis_id",argMisId);

			ArrayList list = (ArrayList)commonDAO.list("secfw.singleIF.mail.listMailAttach", listAttachMap);

			if(list !=null && list.size()>0){

				ListOrderedMap countMap = new ListOrderedMap();
				countMap = (ListOrderedMap)list.get(0);

				li_total = FormatUtil.formatInt(countMap.get("total_cnt"));
				attachEtyCSVO = new AttachEtyCSVO[li_total];

				for(int i=0; i<list.size(); i++){
					attachEtyCSVO[i] = new AttachEtyCSVO();
					ListOrderedMap temp = (ListOrderedMap)list.get(i);

					// file 객체 생성
					file = new File((String)temp.get("local_path"));
					if(file != null && file.isFile()) {
						fds = new FileDataSource(file);
						dh = new DataHandler(fds);
					}
					attachEtyCSVO[i] = new AttachEtyCSVO();

					// DataHandler 객체를 첨부 vo의 file변수에 할당
					attachEtyCSVO[i].setFile(dh);
					// 파일의 순번을 지정
					attachEtyCSVO[i].setISeqID(new Integer((String)temp.get("iseq_id")));

					// 원본파일명을 지정
					attachEtyCSVO[i].setFileName((String)temp.get("file_name"));


				}
			}
		} catch (Exception e) {
        	getLogger().error( "  Exception : " + e);
        	e.printStackTrace();
        }

		return attachEtyCSVO;
	}

	/**
	 * <P>에러로그</P>
	 * ESB연계시 발생한 에러로그를 남긴다.
	 * 테이블 : TB_COM_ESB_FAULT_VO
	 *
	 * @param argModuleId:모듈ID,  argMisId:MIS ID(ESB연동KEY), fault:ESBFaultVO 오류Value Object
	 */
	private void insertError(String argModuleId,String argMisId, ESBFaultVO fault) throws Exception {

		String ls_actor    =  fault.getFaultActor1();
		String ls_code     =  fault.getFaultCode1();
		String ls_string   =  fault.getFaultString1();
		String ls_message  =  fault.getFaultString();
		String ls_detail_message  =  fault.getFaultString1();
		Element[] fault_elements = fault.getFaultDetails();

		String ls_sequence = "";

		getLogger().error("Fault Actor   : " + ls_actor);
		getLogger().error("Fault Code    : " + ls_code);
		getLogger().error("Fault String  : " + ls_string);
		getLogger().error("Fault Message : " + ls_message);
		getLogger().error("Fault Detail Message : " + ls_detail_message);

		// 에러순번을 생성한다.
		HashMap genErrorSeqMap = new HashMap();
		genErrorSeqMap.put("module_id",argModuleId);
		genErrorSeqMap.put("mis_id",argMisId);

		ls_sequence = "";

		List tempList = commonDAO.list("secfw.singleIF.mail.genErrorSeq", genErrorSeqMap);

		if(tempList != null && tempList.size()>0) {
			ListOrderedMap lom = (ListOrderedMap)tempList.get(0);
			ls_sequence = FormatUtil.formatNumToString(lom.get("seq"));
		}

		// 에러메세지를 남긴다.
		HashMap insertErrorMap = new HashMap();
		insertErrorMap.put("module_id",argModuleId);
		insertErrorMap.put("mis_id",argMisId);
		insertErrorMap.put("sequence",ls_sequence);
		insertErrorMap.put("fault_actor1",ls_actor);
		insertErrorMap.put("fault_code1",ls_code);
		insertErrorMap.put("fault_string1",ls_string);
		insertErrorMap.put("fault_message",ls_message);

		commonDAO.insert("secfw.singleIF.mail.insertError", insertErrorMap);

	}

	/************************************************************************************
	 ***********************              메일내역 등록        **************************
	 ************************************************************************************/

	/**
	 * <P>메일등록</P>
	 * @param vo 싱글메일 Value Object
	 * @throws Exception
	 */
	public void insertMail(MailVO vo) throws Exception {

		try {
			String fld_val = "";

			String time = EsbUtil.getTimeByLocalTimeZone("yyyyMMddHHmmss");
			vo.setCreate_date(time);

			//첨부파일 갯수
			String fileInfos = StringUtil.bvl(vo.getFileInfos(),"");
			List fileList = null;


			// 첨부파일 정보가 있으면
			if(clmsFileService.isExistsFileInfos(fileInfos)) {
				fileList = clmsFileService.getFileInfoToComFileList(fileInfos);
				vo.setIfile_count(String.valueOf(fileList.size()));
			} else {
				vo.setIfile_count("0");
			}

			fld_val = vo.getSender_mail_addr();

			if( fld_val.indexOf("@") == -1 ){
				if("DEV".equals(esbDevTF)) {
					fld_val = vo.getSender_single_id() + "@stage.samsung.com";
				} else {
					fld_val = vo.getSender_single_id() + "@samsung.com";
				}
			}


			if("DEV".equals(esbDevTF)) {
				vo.setSender_mail_addr(fld_val.substring(0, fld_val.indexOf("@")) + "@stage.samsung.com");
			} else {
				vo.setSender_mail_addr(fld_val);
			}

			insertMailHeader(vo);
			insertSender(vo);
			insertReceiver(vo);
			insertAttachInfo(vo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Mail Exception");
		}
	}

	/**
	 * <P>Admin 메일등록</P>
	 * @param vo 싱글메일 Value Object
	 * @throws Exception
	 */
	public void insertAdminMail(MailVO vo) throws Exception {

		try {
			String fld_val = "";

			String time = EsbUtil.getTimeByLocalTimeZone("yyyyMMddHHmmss");
			vo.setCreate_date(time);

			//첨부파일 갯수
			String fileInfos = StringUtil.bvl(vo.getFileInfos(),"");
			List fileList = null;

			

			// 첨부파일 정보가 있으면
			if(clmsFileService.isExistsFileInfos(fileInfos)) {
				fileList = clmsFileService.getFileInfoToComFileList(fileInfos);
				vo.setIfile_count(String.valueOf(fileList.size()));
			} else {
				vo.setIfile_count("0");
			}

			fld_val = vo.getSender_mail_addr();

			if( fld_val.indexOf("@") == -1 ){
				if( fld_val.indexOf("@") == -1 ){
					if("DEV".equals(esbDevTF)) {
						fld_val = (String)vo.getSender_single_id() + "@stage.samsung.com";
					} else {
						fld_val = (String)vo.getSender_single_id()  + "@samsung.com";
					}
				}
			}

			if("DEV".equals(esbDevTF)) {
				vo.setSender_mail_addr(fld_val.substring(0, fld_val.indexOf("@")) + "@stage.samsung.com");
			} else {
				vo.setSender_mail_addr(fld_val);
			}

			insertMailHeader(vo);
			insertSender(vo);
			insertAdminReceiver(vo);
			insertAttachInfo(vo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Mail Exception");
		}
	}

	/**
	 * <P>Admin 메일등록</P>
	 * @param vo 싱글메일 Value Object
	 * @throws Exception
	 */
	public void insertAdminMailSub(MailVO vo) throws Exception {

		try {
			String fld_val = "";

			String time = EsbUtil.getTimeByLocalTimeZone("yyyyMMddHHmmss");
			vo.setCreate_date(time);

			//첨부파일 갯수
			String fileInfos = StringUtil.bvl(vo.getFileInfos(),"");
			List fileList = null;


			// 첨부파일 정보가 있으면
			if(clmsFileService.isExistsFileInfos(fileInfos)) {
				fileList = clmsFileService.getFileInfoToComFileList(fileInfos);
				vo.setIfile_count(String.valueOf(fileList.size()));
			} else {
				vo.setIfile_count("0");
			}

			fld_val = vo.getSender_mail_addr();

			if( fld_val.indexOf("@") == -1 ){
				if("DEV".equals(esbDevTF)) {
					fld_val = (String)vo.getSender_single_id() + "@stage.samsung.com";
				} else {
					fld_val = (String)vo.getSender_single_id()  + "@samsung.com";
				}
			}

			if("DEV".equals(esbDevTF)) {
				vo.setSender_mail_addr(fld_val.substring(0, fld_val.indexOf("@")) + "@stage.samsung.com");
			} else {
				vo.setSender_mail_addr(fld_val);
			}

			insertMailHeader(vo);
			insertSender(vo);
			insertAdminReceiverSub(vo);
			insertAttachInfo(vo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Mail Exception");
		}
	}

	/**
	 * <P>메일헤더정보 등록</P>
	 * 테이블 : TB_COM_HEADER_HELPER_CSVO
	 *
	 * @param vo 싱글메일 Value Object
	 * @throws Exception
	 */
	private void insertMailHeader(MailVO vo) throws Exception {

		//옵션값 설정(Default Setting)
		vo.setMsg_type(EsbUtil.bvl(vo.getMsg_type(),propertyService.getProperty("secfw.mail.default.msg_type")));
		vo.setBhtml_content_check(EsbUtil.bvl(vo.getBhtml_content_check(),propertyService.getProperty("secfw.mail.default.bhtml_content_check")));
		vo.setTime_zone(EsbUtil.bvl(vo.getTime_zone(),propertyService.getProperty("secfw.mail.default.time_zone")));
		vo.setIs_dst(EsbUtil.bvl(vo.getIs_dst(),propertyService.getProperty("secfw.mail.default.is_dst")));

		commonDAO.insert("secfw.singleIF.mail.insertMailHeader", vo);
	}

	/**
	 * <P>메일 송신자 내역 등록</P>
	 * 테이블 : TB_COM_RESOURCE_CSVO
	 *
	 * @param vo 싱글메일 Value Object
	 * @throws Exception
	 */
	private void insertSender(MailVO vo) throws Exception {

		vo.setEncoding(EsbUtil.bvl(vo.getEncoding(),propertyService.getProperty("secfw.mail.default.encoding")));

		commonDAO.insert("secfw.singleIF.mail.insertMailResource", vo);
	}

	/**
	 * <P>메일 수신자 내역 등록</P>
	 * 테이블 : TB_COM_RECIPIENT_ETY_CSVO
	 *
	 * @param vo 싱글메일 Value Object
	 * @throws Exception
	 */
	private void insertReceiver(MailVO vo) throws Exception {

		String[] iseq_ids  = vo.getIseq_ids();
		String[] rec_types = vo.getRec_types();
		String[] rec_addrs = vo.getRec_addrs();

		if(iseq_ids!=null && iseq_ids.length>0) {
			for(int i=0; i<iseq_ids.length; i++) {
				vo.setIseq_id(iseq_ids[i]);
				vo.setRec_type(rec_types[i]);
				vo.setRec_addr(rec_addrs[i]);
				commonDAO.insert("secfw.singleIF.mail.insertMailRecipient", vo);
			}
		}
	}

	/**
	 * <P>Admin 메일 수신자 내역 등록</P>
	 * 테이블 : TB_COM_RECIPIENT_ETY_CSVO
	 *
	 * @param vo 싱글메일 Value Object
	 * @throws Exception
	 */
	private void insertAdminReceiver(MailVO vo) throws Exception {

		String[] iseq_ids  = vo.getIseq_ids();
		String[] rec_types = vo.getRec_types();
		String[] rec_addrs = vo.getRec_addrs();

		if(iseq_ids!=null && iseq_ids.length>0) {
			for(int i=0; i<iseq_ids.length; i++) {
				vo.setIseq_id(iseq_ids[i]);
				vo.setRec_type(rec_types[i]);
				if("DEV".equals(esbDevTF)) {
					vo.setRec_addr("ilovsimpd@stage.samsung.com");
				} else {
					vo.setRec_addr("ilovsimpd@samsung.com");
				}

				commonDAO.insert("secfw.singleIF.mail.insertMailRecipient", vo);
			}
		}
	}

	/**
	 * <P>Admin 메일 수신자 내역 등록</P>
	 * 테이블 : TB_COM_RECIPIENT_ETY_CSVO
	 *
	 * @param vo 싱글메일 Value Object
	 * @throws Exception
	 */
	private void insertAdminReceiverSub(MailVO vo) throws Exception {

		String[] iseq_ids  = vo.getIseq_ids();
		String[] rec_types = vo.getRec_types();
		String[] rec_addrs = vo.getRec_addrs();

		if(iseq_ids!=null && iseq_ids.length>0) {
			for(int i=0; i<iseq_ids.length; i++) {
				vo.setIseq_id(iseq_ids[i]);
				vo.setRec_type(rec_types[i]);
				if("DEV".equals(esbDevTF)) {
					vo.setRec_addr("jy1222.han@stage.samsung.com");
				} else {
					vo.setRec_addr("jy1222.han@samsung.com");
				}

				commonDAO.insert("secfw.singleIF.mail.insertMailRecipient", vo);
			}
		}
	}

	/**
	 * <P>메일 첨부파일 내역 등록</P>
	 * 테이블 : TB_COM_ATTACH_ETY_CSVO
	 *
	 * @param vo 싱글메일 Value Object
	 * @throws Exception
	 */
	private void insertAttachInfo(MailVO vo) throws Exception {

		String fileInfos = StringUtil.bvl(vo.getFileInfos(), "");

		// 첨부파일 정보가 있으면
		if(!"".equals(fileInfos)){
			/*************************************************
			 * 첨부파일 저장
			 *************************************************/
			ComFileVO fvo = new ComFileVO();

			fvo.setSys_cd(vo.getSys_cd());
			fvo.setModule_id(vo.getModule_id());
			fvo.setMis_id(vo.getMis_id());
			fvo.setFileInfos(fileInfos);
			fvo.setSys_gbn("mail");
			clmsFileService.mngComAttachFile(fvo);
		}

	}

	/**
	 * <P>메일전송목록</P>
	 * @param vo 싱글메일 Value Object
	 * @throws Exception
	 */
	public List listMail(MailVO vo) throws Exception {
		return commonDAO.list("secfw.singleIF.mail.listMail", vo);
	}
}
