package com.sds.secframework.singleIF.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import mySingle.service.APStatusInquiryServiceLocator;
import mySingle.service.APSubmitServiceLocator;
import mySingle.service.APWithdrawServiceLocator;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import samsung.esb.approval.service.APStatusInquiryService;
import samsung.esb.approval.service.APSubmitService;
import samsung.esb.approval.service.APWithdrawService;
import samsung.esb.approval.vo.AttachmentWSVO;
import samsung.esb.approval.vo.CancelProcessWSVO;
import samsung.esb.approval.vo.MisKeyWSVO;
import samsung.esb.approval.vo.ProcessStatusWSVO;
import samsung.esb.approval.vo.RouteWSVO;
import samsung.esb.approval.vo.StartProcessWSVO;
import samsung.esb.common.vo.ESBAuthVO;
import samsung.esb.common.vo.ESBFaultVO;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.EsbUtil;
import com.sds.secframework.common.util.FormatUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.singleIF.dto.ApprovalRouteVO;
import com.sds.secframework.singleIF.dto.ApprovalVO;
import com.sds.secframework.singleIF.service.EsbApprovalPostProcessService;
import com.sds.secframework.singleIF.service.EsbApprovalService;
import com.sec.common.clmsfile.dto.ComFileVO;

/**
 * <P>Single ESB 결재 Class</P>
 * 
 * [지원기능]<BR>
 * <BR>
 * - 결재상신 : ESB결재연동 수행<BR>
 * - 결재상태조회 : ESB 통하여 결재상태 및 경로 정보조회 후 해당 테이블에 최신상태로 반영, 결재상태정보(경로정보포함) 리턴 <BR>
 * - 결재상신취소 : 상신된 문건에 대하여 상신취소 처리<BR>
 * 
 * <br>
 * [외부 Transaction에 종속적으로 작동시킬 경우]<BR>
 * 각 메소드별 try ~ catch 문을 주석처리후 상위로 예외를 throw 하여 처리하도록 한다.<BR>
 *   
 * @version com.sds.secframework.singleIF V1.0
 * 작성일 : 2009/12/18
 * @author 금현서, ace4u_khs@samsung.com
 */
public class EsbApprovalServiceImpl extends CommonServiceImpl implements EsbApprovalService {

	private static String esbApprId = "";
	private static String esbApprPw = "";
	private static String esbDevTF  = "";

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private EsbApprovalPostProcessService esbApprovalPostProcessService;
	public void setEsbApprovalPostProcessService(EsbApprovalPostProcessService esbApprovalPostProcessService) {
		this.esbApprovalPostProcessService = esbApprovalPostProcessService;
	}

	public void setEsbApprId(String esbApprId) {
		this.esbApprId = esbApprId;
	}

	public void setEsbApprPw(String esbApprPw) {
		this.esbApprPw = esbApprPw;
	}
	
	public void setEsbDevTF(String esbDevTF) {
		this.esbDevTF = esbDevTF;
	}

	/**
	 * <P>결재상신</P>
	 * 결재관련 테이블에 해당 데이터를 입력한 후, ESB결재연동을 수행.
	 * 
	 * @param ApprovalVO : 결재상신 Value Object
	 * @return 결재상신 결과 (성공:true, 실패:false)
	 */
	public boolean submit(ApprovalVO vo) {
		
		boolean result = false;
		
		String argModuleId = "";
		String argMisId    = "";
		
		try {
			APSubmitServiceLocator serviceLoacator = new APSubmitServiceLocator();
			APSubmitService apSubmitService = serviceLoacator.getAPSubmitService_InboundPort();
			
			//모듈아이디 및 misId 세팅 -> (각 시스템/업무별로 개별 Key값을 입력)
			//argModuleId = propertyService.getProperty("secfw.sysCd");
			argModuleId = vo.getSys_cd();
			argMisId    = EsbUtil.generateMisId("APPR");
			
			vo.setModule_id(argModuleId);
			vo.setMis_id(argMisId);
			
			//=================================================================================
			//체결건(C03002)이면서 STATUS가 결재진행중(1)인 건이  중복 입력되는것을 막기 위해 (2012. 2. 21)
			//=================================================================================
			String apprvl_clsfcn = (String)vo.getApprvl_clsfcn();
			boolean isExist = false;
			if(apprvl_clsfcn !=null && apprvl_clsfcn.equals("C03002")){
				List approvalHeaderList = (List)commonDAO.list("secfw.singleIF.approval.selectApprovalHeaderCount", vo);
				
				if(approvalHeaderList != null && approvalHeaderList.size()>0) {
					ListOrderedMap lom = (ListOrderedMap)approvalHeaderList.get(0);
					if(((BigDecimal)lom.get("wsvo_cnt")).intValue()  >0) isExist=true;
				}
 			}
			//=================================================================================
			
			
			if(! isExist){
				//결재내역 등록
				insertApproval(vo);
				
				ESBAuthVO esbAuthVO = EsbUtil.getESBAuthVO(esbApprId, esbApprPw);
					
				StartProcessWSVO startProcessWSVO = this.getStartProcessWSVO(argModuleId, argMisId);
				startProcessWSVO.setAttachmentVOs(this.getAttachmentWSVO(argModuleId, argMisId));
				startProcessWSVO.setRouteVOs(this.getRouteWSVO(argModuleId, argMisId));
						
				String returnMsg = apSubmitService.submitApproval(esbAuthVO, startProcessWSVO);
				getLogger().debug( "returnMsg :" + returnMsg);
				
				// Header 상태를 업데이트 한다.
				HashMap modifyStatusMap = new HashMap();
				modifyStatusMap.put("status","1");
				modifyStatusMap.put("module_id",argModuleId);
				modifyStatusMap.put("mis_id",argMisId);
						
				commonDAO.modify("secfw.singleIF.approval.modifyStatus", modifyStatusMap);
			}
			
			result = true;
				
		} catch(ESBFaultVO fault) {
			fault.printStackTrace();
			try {	
				//에러로그 
				insertError(argModuleId, argMisId, fault);
				
				// 결재상태 코드를 Error로  업데이트 한다.
				HashMap modifyStatusMap = new HashMap();
				modifyStatusMap.put("status","EF");
				modifyStatusMap.put("module_id",argModuleId);
				modifyStatusMap.put("mis_id",argMisId);
					
				commonDAO.modify("secfw.singleIF.approval.modifyStatus", modifyStatusMap);

			} catch (SQLException e) {				
				getLogger().error( "  SQLException : " + e);
            	e.printStackTrace();
		    } catch (Exception e) {
		    	getLogger().error( "  Exception : " + e);
            	e.printStackTrace();
		    }
		} catch (SQLException e) {
			getLogger().error( " sendApproval SQLException : " + e);
        	e.printStackTrace();
        } catch (Exception e) {
        	getLogger().error( " sendApproval  Exception : " + e);
        	e.printStackTrace();
        }finally{ 
        	return result;
        }
	}
	
	/**
	 * <P>결재상신</P>
	 * 결재관련 테이블에 해당 데이터를 입력
	 * 
	 * @param ApprovalVO : 결재상신 Value Object
	 * @return 결재상신 결과 (성공:true, 실패:false)
	 */	
	public boolean preSubmit(ApprovalVO vo) {
		boolean result = false;
		
		String argModuleId = "";
		String argMisId    = "";
		
		try {
			
			//모듈아이디 및 misId 세팅 -> (각 시스템/업무별로 개별 Key값을 입력)
			argModuleId = vo.getSys_cd();
			argMisId    = EsbUtil.generateMisId("APPR");
			
			vo.setModule_id(argModuleId);
			vo.setMis_id(argMisId);
			
			//결재내역 등록
			insertApproval(vo);
			
			result = true;
				
		} catch (SQLException e) {
			getLogger().error( " sendApproval SQLException : " + e);
        	e.printStackTrace();
        } catch (Exception e) {
        	getLogger().error( " sendApproval  Exception : " + e);
        	e.printStackTrace();
        }finally{ 
        	return result;
        }		
	}
	
	/**
	 * <P>결재상신</P>
	 * 기 등록건에 대해 ESB결재연동을 수행.
	 * @param ApprovalVO : 결재상신 Value Object
	 * @return 결재상신 결과 (성공:true, 실패:false)
	 */
	public boolean afterSubmit(ApprovalVO vo){
		boolean result = false;
		
		String argModuleId = "";
		String argMisId    = "";
		
		try {
			APSubmitServiceLocator serviceLoacator = new APSubmitServiceLocator();
			APSubmitService apSubmitService = serviceLoacator.getAPSubmitService_InboundPort();
			
			
			
			
			ESBAuthVO esbAuthVO = EsbUtil.getESBAuthVO(esbApprId, esbApprPw);
				
			StartProcessWSVO startProcessWSVO = this.getStartProcessWSVO(vo.getModule_id(), vo.getMis_id());
			startProcessWSVO.setAttachmentVOs(this.getAttachmentWSVO(vo.getModule_id(), vo.getMis_id()));
			startProcessWSVO.setRouteVOs(this.getRouteWSVO(vo.getModule_id(), vo.getMis_id()));
					
			String returnMsg = apSubmitService.submitApproval(esbAuthVO, startProcessWSVO);
			getLogger().debug( "returnMsg :" + returnMsg);
			
			//12/05 심우규 추가
			argModuleId = vo.getModule_id();
			argMisId    = vo.getMis_id();
			
			// Header 상태를 업데이트 한다.
			HashMap modifyStatusMap = new HashMap();
			modifyStatusMap.put("status","1");
			modifyStatusMap.put("module_id",argModuleId);
			modifyStatusMap.put("mis_id",argMisId);
					
			commonDAO.modify("secfw.singleIF.approval.modifyStatus", modifyStatusMap);
			
			result = true;
				
		} catch(ESBFaultVO fault) {
			fault.printStackTrace();
			try {	
				//에러로그 
				insertError(argModuleId, argMisId, fault);
				
				// 결재상태 코드를 Error로  업데이트 한다.
				HashMap modifyStatusMap = new HashMap();
				modifyStatusMap.put("status","EF");
				modifyStatusMap.put("module_id",argModuleId);
				modifyStatusMap.put("mis_id",argMisId);
					
				commonDAO.modify("secfw.singleIF.approval.modifyStatus", modifyStatusMap);

			} catch (SQLException e) {				
				getLogger().error( "  SQLException : " + e);
            	e.printStackTrace();
		    } catch (Exception e) {
		    	getLogger().error( "  Exception : " + e);
            	e.printStackTrace();
		    }
		} catch (SQLException e) {
			getLogger().error( " sendApproval SQLException : " + e);
        	e.printStackTrace();
        } catch (Exception e) {
        	getLogger().error( " sendApproval  Exception : " + e);
        	e.printStackTrace();
        }finally{ 
        	return result;
        }		
	}
	
	
	
	/**
	 * <P>결재상태조회</P>
	 * 결재상태 조회. 결재경로 정보를 받아와서 최신상태로 반영.
	 * 
	 * @param argModuleId:모듈ID,  argMisId:MIS ID(ESB연동KEY)
	 * @return HashMap 결재상태 조회결과
	 *        KEY : "status",      VALUE : 결재상태값 (1:결재진행중, 2:완결, 3:반려, 4:상신취소, 5:전결, 6:후완결)
	 *        KEY : "approvalPath" VALUE : 결재경로 정보 RouteWSVO[] 타입
	 */
	public HashMap getStatusWithPath(String argModuleId,String argMisId) {
		
		HashMap     result                  = new HashMap();
		ProcessStatusWSVO processStatusWSVO = null;
		RouteWSVO[] pathResult              = null;
	
		try {
	
			// 결재상신 : 기본정보를 가져온다.
			HashMap getApprovalInfoMap = new HashMap();
			getApprovalInfoMap.put("module_id", argModuleId);
			getApprovalInfoMap.put("mis_id", argMisId);
			HashMap resultMap = (HashMap)commonDAO.list("secfw.singleIF.approval.getApprovalHeader", getApprovalInfoMap);
			
			String status = (String)resultMap.get("STATUS");
			
			//결재진행 중인 문서에 한하여 결재상태 조회를 실시한다.
			if("1".equals(status)) {

				APStatusInquiryServiceLocator serviceLoacator = new APStatusInquiryServiceLocator();
				APStatusInquiryService apStatusInquiryService = serviceLoacator.getAPStatusInquiryService_InboundPort();
				
				processStatusWSVO = new ProcessStatusWSVO();
				
				ESBAuthVO esbAuthVO = EsbUtil.getESBAuthVO(esbApprId, esbApprPw);
				MisKeyWSVO misKeyWSVO = new MisKeyWSVO();
				misKeyWSVO.setMisID(argMisId);
				misKeyWSVO.setSystemID(esbApprId);
				
				processStatusWSVO = apStatusInquiryService.getStatusByMisId(esbAuthVO, misKeyWSVO);
				RouteWSVO[] routeWSVO = processStatusWSVO.getRouteVOs();
	
				// 결재경로 정보를 삭제한다.
				HashMap deleteApprovalPathMap = new HashMap();
				deleteApprovalPathMap.put("module_id",argModuleId);
				deleteApprovalPathMap.put("mis_id",argMisId);
				
				commonDAO.delete("secfw.singleIF.approval.deleteApprovalPath", deleteApprovalPathMap);
				
				// 결재경로 정보를 신규로 입력한다.(싱글결재 경로로 재설정작업)
				for(int i=0; i<routeWSVO.length; i++) {
					
					ApprovalRouteVO vo = new ApprovalRouteVO();
	
					vo.setModule_id(argModuleId);
					vo.setMis_id(argMisId);
					vo.setAction_type(routeWSVO[i].getActionType());
					vo.setActivity(routeWSVO[i].getActivity());
					vo.setAlarm_type(routeWSVO[i].getAlarmType());
					vo.setApproved(routeWSVO[i].getApproved());
					vo.setArbitrary(routeWSVO[i].getArbitrary());
					vo.setArrived(routeWSVO[i].getArrived());
					vo.setBody_modify(routeWSVO[i].getBodyModify());
					vo.setDept_code(routeWSVO[i].getDeptCode());
					vo.setDept_name(routeWSVO[i].getDeptName());
					vo.setDuration(routeWSVO[i].getDuration());
					vo.setDuty(routeWSVO[i].getDuty());
					vo.setDuty_code(routeWSVO[i].getDutyCode());
					vo.setGroup_code(routeWSVO[i].getGroupCode());
					vo.setGroup_name(routeWSVO[i].getGroupName());
					vo.setMail_address(routeWSVO[i].getMailAddress());
					vo.setOpinion(routeWSVO[i].getOpinion());
					vo.setReserved(routeWSVO[i].getReserved());
					vo.setRoute_modify(routeWSVO[i].getRouteModify());
					vo.setSequence(routeWSVO[i].getSequence());
					vo.setSocial_id(routeWSVO[i].getSocialID());
					vo.setUser_id(routeWSVO[i].getUserID());
					vo.setUser_name(routeWSVO[i].getUserName());
					vo.setDelegated(routeWSVO[i].getDelegated());
					
					commonDAO.modify("secfw.singleIF.approval.insertApprovalPath", vo);
					
				}
				
				// Header 상태를 업데이트 한다.
				HashMap modifyStatusMap = new HashMap();
				modifyStatusMap.put("status",processStatusWSVO.getStatus());
				modifyStatusMap.put("module_id",argModuleId);
				modifyStatusMap.put("mis_id",argMisId);
				
				commonDAO.modify("secfw.singleIF.approval.modifyStatus", modifyStatusMap);
			}
			
			//결재 상태값 및 결재경로 정보를 리턴한다.			
			result.put("status", processStatusWSVO.getStatus());
			result.put("approvalPath", getRouteWSVO(argModuleId, argMisId));

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
        }finally {        		
        	return result;
        }
	}

	/**
	 * <P>결재상신취소</P>
	 * 현재 결재진행중인 건에 한하여 결재상신 취소를 수행.
	 * 
	 * @param argModuleId:모듈ID,  argMisId:MIS ID(ESB연동KEY), argOpinion:상신취소의견
	 * @return 결재상신 취소결과 (성공:1, 결재진행인 건이 아닐경우:2 예외:E)
	 */
	public String cancelApproval(String argModuleId,String argMisId, String argOpinion) {
		
		String result = "E";

		try {
	
			// 결재상신 : 기본정보를 가져온다.
			HashMap getApprovalInfoMap = new HashMap();
			getApprovalInfoMap.put("module_id", argModuleId);
			getApprovalInfoMap.put("mis_id", argMisId);
			HashMap resultMap = (HashMap)commonDAO.list("secfw.singleIF.approval.getApprovalHeader", getApprovalInfoMap);
			
			String status   = (String)resultMap.get("status");
			String timeZone = (String)resultMap.get("time_zone");
			
			String sigleStatus = "";
			
			// 로컬Checking
			// 0 : 결재상신 대기문서(사용자 입력시) 이거나, 
			// 1 : 결재진행 중인 문서에 한하여 결재상신취소 할 수 있다.
			if("0".equals(status) || "1".equals(status)) {
				
				if(getStatusByMisId(argModuleId, argMisId) == null) {
					throw new Exception("NULL Return in getStatusByMisId");
				}
				
				sigleStatus = getStatusByMisId(argModuleId, argMisId);
				
				// ESB Checking
				// ESB 호출 후 상태가 결재진행인 건에 한하여만 상신취소를 한다.
				if("1".equals(sigleStatus)) {
				
					APWithdrawServiceLocator serviceLocator = new APWithdrawServiceLocator();
					APWithdrawService cancelService = serviceLocator.getAPWithdrawService_InboundPort();
		
					ESBAuthVO esbAuthVO = EsbUtil.getESBAuthVO(esbApprId, esbApprPw);
					CancelProcessWSVO cancelProcessWSVO = new CancelProcessWSVO();
					
					cancelProcessWSVO.setMisID(argMisId);
					cancelProcessWSVO.setSystemID(esbApprId);
					cancelProcessWSVO.setOpinion(argOpinion);
					cancelProcessWSVO.setCancelDate(EsbUtil.getTime("yyyyMMddHHmmss"));
					cancelProcessWSVO.setTimeZone(timeZone);
					
					String returnMessage = cancelService.cancelApproval(esbAuthVO, cancelProcessWSVO);
					
					// 상신취소가 성공적으로 수행된 경우
					if(returnMessage != null && "SUCCESS".equals(returnMessage.toUpperCase())) {
						
						// 결재상태를 상신취소로 변경한다.
						// Header 상태를 업데이트 한다.
						HashMap modifyStatusMap = new HashMap();
						modifyStatusMap.put("status",propertyService.getProperty("secfw.esb.appr.cancelStatus"));
						modifyStatusMap.put("module_id",argModuleId);
						modifyStatusMap.put("mis_id",argMisId);
						
						commonDAO.modify("secfw.singleIF.approval.modifyStatus", modifyStatusMap);
						
						result = "1";
					}
				} else {
					result = "2";
				}
			}
		
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
        	return result;
        }
	}
	
	/**
	 * <P>결재기본정보 조회</P>
	 * 결재기본 정보를 읽어온다.
	 * 테이블 : TB_COM_START_PROCESS_WSVO
	 * 
	 * @param argModuleId:모듈ID,  argMisId:MIS ID(ESB연동KEY)
	 * @return StartProcessWSVO 결재기본정보 VO
	 */
	public StartProcessWSVO getStartProcessWSVO(String argModuleId,String argMisId) throws Exception {
        StartProcessWSVO	startProcessWSVO= new StartProcessWSVO();

		// 결재상신 : 기본정보을 가져온다.
		HashMap getApprovalInfoMap = new HashMap();            
		getApprovalInfoMap.put("module_id",argModuleId);
		getApprovalInfoMap.put("mis_id",argMisId);
		List approvalHeaderList = (List)commonDAO.list("secfw.singleIF.approval.getApprovalHeader", getApprovalInfoMap);
		
		if(approvalHeaderList != null && approvalHeaderList.size()>0) {
			
			ListOrderedMap lom = (ListOrderedMap)approvalHeaderList.get(0);
			
			String ls_body          = EsbUtil.bvl((String)lom.get("body"),"");
			String ls_bodyType 		= EsbUtil.bvl((String)lom.get("body_type"),"");
			String ls_createDate 	= EsbUtil.bvl((String)lom.get("create_date"),"");
			String ls_drmOptionInfo = EsbUtil.bvl((String)lom.get("drm_option_info"),"");
			String ls_localInfo 	= EsbUtil.bvl((String)lom.get("locale_info"),"");
			String ls_notiMail 		= EsbUtil.bvl((String)lom.get("noti_mail"),"");
			String ls_priority 		= EsbUtil.bvl((String)lom.get("priority"),"");
			String ls_security 		= EsbUtil.bvl((String)lom.get("security"),"");
			String ls_timeZone 		= EsbUtil.bvl((String)lom.get("time_zone"),"");
			String ls_title 		= EsbUtil.bvl((String)lom.get("title"),"");
			
			getLogger().debug( "ls_body ===> " + ls_body);
			getLogger().debug( "ls_bodyType ===> " + ls_bodyType);
			getLogger().debug( "ls_createDate ===> " + ls_createDate);
			getLogger().debug( "ls_drmOptionInfo ===> " + ls_drmOptionInfo);
			getLogger().debug( "ls_localInfo ===> " + ls_localInfo);
			getLogger().debug( "ls_notiMail ===> " + ls_notiMail);
			getLogger().debug( "ls_priority ===> " + ls_priority);
			getLogger().debug( "ls_security ===> " + ls_security);
			getLogger().debug( "ls_timeZone ===> " + ls_timeZone);
			getLogger().debug( "ls_title ===> " + ls_title);
			getLogger().debug( "argMisId ===> " + argMisId);
			getLogger().debug( "esbApprId ===> " + esbApprId);
			
			startProcessWSVO.setBody(ls_body);
			startProcessWSVO.setBodyType(ls_bodyType);
			startProcessWSVO.setCreateDate(ls_createDate);
			startProcessWSVO.setDrmOptionInfo(ls_drmOptionInfo); 
			startProcessWSVO.setLocaleInfo(ls_localInfo);
			startProcessWSVO.setMisID(argMisId);
			startProcessWSVO.setSystemID(esbApprId);
			startProcessWSVO.setNotiMail(ls_notiMail);
			startProcessWSVO.setPriority(ls_priority);
			startProcessWSVO.setSecurity(ls_security);
			startProcessWSVO.setTimeZone(ls_timeZone);
			startProcessWSVO.setTitle(ls_title);
		}

		return startProcessWSVO;
	}
	
	/**
	 * <P>결재경로조회</P>
	 * 결재경로 정보를 읽어온다.
	 * 테이블 : TB_COM_ROUTE_WSVO
	 * 
	 * @param argModuleId:모듈ID,  argMisId:MIS ID(ESB연동KEY)
	 * @return RouteWSVO[] 결재경로 VO
	 */
	public RouteWSVO[] getRouteWSVO(String argModuleId,String argMisId) throws Exception {
        RouteWSVO[]			routeWSVO		= null;
        int					li_total		= 0;

		// 결재상신 : 결재경로 정보
		HashMap listApprovalPathMap = new HashMap();            
		listApprovalPathMap.put("module_id",argModuleId);
		listApprovalPathMap.put("mis_id",argMisId);
		ArrayList list = (ArrayList)commonDAO.list("secfw.singleIF.approval.listApprovalPath", listApprovalPathMap);

		ListOrderedMap countMap = new ListOrderedMap();
		countMap = (ListOrderedMap)list.get(0);
		
		li_total = FormatUtil.formatInt(countMap.get("total_cnt"));
		routeWSVO = new RouteWSVO[li_total];

		for(int i=0; i<list.size(); i++){
			
			ListOrderedMap temp = (ListOrderedMap)list.get(i);
			
			String ls_actionType	= EsbUtil.bvl((String)temp.get("action_type"),"");
			String ls_activity		= EsbUtil.bvl((String)temp.get("activity"),"");
			String ls_alarmType 	= EsbUtil.bvl((String)temp.get("alarm_type"),"");
			String ls_approved 		= EsbUtil.bvl((String)temp.get("approved"),"");
			String ls_arbitrary 	= EsbUtil.bvl((String)temp.get("arbitrary"),"");
			String ls_arrvied 		= EsbUtil.bvl((String)temp.get("arrived"),"");
			String ls_bodyModify 	= EsbUtil.bvl((String)temp.get("body_modify"),"");
			String ls_compCode 		= EsbUtil.bvl((String)temp.get("comp_code"),"");
			String ls_compName 		= EsbUtil.bvl((String)temp.get("comp_name"),"");
			String ls_deptCode 		= EsbUtil.bvl((String)temp.get("dept_code"),"");
			String ls_deptName 		= EsbUtil.bvl((String)temp.get("dept_name"),"");
			String ls_duration 		= EsbUtil.bvl((String)temp.get("duration"),"");
			String ls_duty 			= EsbUtil.bvl((String)temp.get("duty"),"");
			String ls_dutyCode 		= EsbUtil.bvl((String)temp.get("duty_code"),"");
			String ls_groupCode 	= EsbUtil.bvl((String)temp.get("group_code"),"");
			String ls_groupName 	= EsbUtil.bvl((String)temp.get("group_name"),"");
			String ls_mailAddress 	= EsbUtil.bvl((String)temp.get("mail_address"),"");
			String ls_opinion 		= EsbUtil.bvl((String)temp.get("opinion"),"");
			String ls_reserved 		= EsbUtil.bvl((String)temp.get("reserved"),"");
			String ls_routeModify 	= EsbUtil.bvl((String)temp.get("route_modify"),"");
			String ls_sequence 		= EsbUtil.bvl((String)temp.get("sequence"),"");
			String ls_socialId 		= EsbUtil.bvl((String)temp.get("social_id"),"");
			String ls_userId 		= EsbUtil.bvl((String)temp.get("user_id"),"");
			String ls_userName 		= EsbUtil.bvl((String)temp.get("user_name"),"");
			String ls_delegated		= EsbUtil.bvl((String)temp.get("delegated"),"");
			
			getLogger().debug( "ls_actionType	 ===> " + ls_actionType);
			getLogger().debug( "ls_activity	 ===> " + ls_activity);
			getLogger().debug( "ls_alarmType 	 ===> " + ls_alarmType);
			getLogger().debug( "ls_approved 	 ===> " + ls_approved);
			getLogger().debug( "ls_arbitrary 	 ===> " + ls_arbitrary);
			getLogger().debug( "ls_arrvied 	 ===> " + ls_arrvied);
			getLogger().debug( "ls_bodyModify  ===> " + ls_bodyModify);
			getLogger().debug( "ls_compCode 	 ===> " + ls_compCode);
			getLogger().debug( "ls_compName 	 ===> " + ls_compName);
			getLogger().debug( "ls_deptCode 	 ===> " + ls_deptCode);
			getLogger().debug( "ls_deptName 	 ===> " + ls_deptName);
			getLogger().debug( "ls_duration 	 ===> " + ls_duration);
			getLogger().debug( "ls_duty 		 ===> " + ls_duty);
			getLogger().debug( "ls_dutyCode 	 ===> " + ls_dutyCode);
			getLogger().debug( "ls_groupCode 	 ===> " + ls_groupCode);
			getLogger().debug( "ls_groupName 	 ===> " + ls_groupName);
			getLogger().debug( "ls_mailAddress ===> " + ls_mailAddress);
			getLogger().debug( "ls_opinion 	 ===> " + ls_opinion);
			getLogger().debug( "ls_reserved 	 ===> " + ls_reserved);
			getLogger().debug( "ls_routeModify ===> " + ls_routeModify);
			getLogger().debug( "ls_sequence 	 ===> " + ls_sequence);
			getLogger().debug( "ls_socialId 	 ===> " + ls_socialId);
			getLogger().debug( "ls_userId 	 ===> " + ls_userId);
			getLogger().debug( "ls_userName 	 ===> " + ls_userName);
			getLogger().debug( "ls_delegated	 ===> " + ls_delegated);	
			
			routeWSVO[i] = new RouteWSVO();

			routeWSVO[i].setActionType(ls_actionType);
			routeWSVO[i].setActivity(ls_activity);
			routeWSVO[i].setAlarmType(ls_alarmType);
			routeWSVO[i].setApproved(ls_approved);
			routeWSVO[i].setArbitrary(ls_arbitrary);
			routeWSVO[i].setArrived(ls_arrvied);
			routeWSVO[i].setBodyModify(ls_bodyModify);
			routeWSVO[i].setCompCode(ls_compCode);
			routeWSVO[i].setCompName(ls_compName);
			routeWSVO[i].setDeptCode(ls_deptCode);
			routeWSVO[i].setDeptName(ls_deptName);
			routeWSVO[i].setDuration(ls_duration);
			routeWSVO[i].setDuty(ls_duty);
			routeWSVO[i].setDutyCode(ls_dutyCode);
			routeWSVO[i].setGroupCode(ls_groupCode);
			routeWSVO[i].setGroupName(ls_groupName);
			
			if(ls_mailAddress != null && !ls_mailAddress.trim().equals("")) {
				if("T".equals(esbDevTF)) {
					routeWSVO[i].setMailAddress(ls_mailAddress.substring(0, ls_mailAddress.indexOf("@")) + "@stage.samsung.com");
				} else {
					routeWSVO[i].setMailAddress(ls_mailAddress);
				}
			} else {
				routeWSVO[i].setMailAddress(ls_mailAddress);
			}
			
			routeWSVO[i].setOpinion(ls_opinion);
			routeWSVO[i].setReserved(ls_reserved);
			routeWSVO[i].setRouteModify(ls_routeModify);
			routeWSVO[i].setSequence(ls_sequence);
			routeWSVO[i].setSocialID(ls_socialId);
			routeWSVO[i].setUserID(ls_userId);
			routeWSVO[i].setUserName(ls_userName);
			routeWSVO[i].setDelegated(ls_delegated);
		}

		return routeWSVO;
	}
	
	/**
	 * <P>첨부파일조회</P>
	 * 결재첨부파일 정보를 읽어온다.
	 * 테이블 : TB_COM_ATTACHMENT_WSVO
	 * 
	 * @param argModuleId:모듈ID,  argMisId:MIS ID(ESB연동KEY)
	 * @return AttachmentWSVO[] 첨부파일 VO
	 */
	public AttachmentWSVO[] getAttachmentWSVO(String argModuleId,String argMisId) throws Exception {
        AttachmentWSVO[]	attachmentWSVO	= null;
        int					li_total		= 0;
        
        File 				file 			= null;
		FileDataSource 		fds 			= null;
		DataHandler 		dh 				= null;
			
		// 첨부파일 정보
		HashMap listAttachMap = new HashMap();            
		listAttachMap.put("module_id",argModuleId);
		listAttachMap.put("mis_id",argMisId);
		ArrayList list = (ArrayList)commonDAO.list("secfw.singleIF.approval.listApprovalAttach", listAttachMap);

		if(list != null && list.size()>0) {

			ListOrderedMap countMap = new ListOrderedMap();
			countMap = (ListOrderedMap)list.get(0);
			
			li_total = FormatUtil.formatInt(countMap.get("total_cnt"));
			attachmentWSVO = new AttachmentWSVO[li_total];
			
			for(int i=0; i<list.size(); i++){
				
				ListOrderedMap temp = (ListOrderedMap)list.get(i);
				
				String ls_fileName = EsbUtil.bvl((String)temp.get("file_name"),"");
				String ls_fileSize = EsbUtil.bvl((String)temp.get("file_size"),"");
				String ls_sequence = EsbUtil.bvl((String)temp.get("sequence"),"");
				String ls_storeLocation = EsbUtil.bvl((String)temp.get("store_location"),"");
				
				getLogger().debug( "ls_fileName ===> " + ls_fileName);
				getLogger().debug( "ls_fileSize ===> " + ls_fileSize);
				getLogger().debug( "ls_sequence ===> " + ls_sequence);
				getLogger().debug( "ls_storeLocation ===> " + ls_storeLocation);
				
				// file 객체 생성
				file = new File(ls_storeLocation);
				if(file != null && file.isFile()) {
					fds = new FileDataSource(file);
					dh = new DataHandler(fds);
				}
				// 2014-02-28 Kevin
				
				attachmentWSVO[i] = new AttachmentWSVO();
				attachmentWSVO[i].setFileName(ls_fileName);
				attachmentWSVO[i].setFileSize(ls_fileSize);
				/*attachmentWSVO[i].setSequence(ls_sequence);*/
				attachmentWSVO[i].setSequence(String.valueOf(i));
				attachmentWSVO[i].setStoreLocation(ls_storeLocation);
				attachmentWSVO[i].setFile(dh);

			}
		} 
		
    	return attachmentWSVO;
	}
	
	/**
	 * <P>결재상황조회</P>
	 * 해당 결재 문건에 대한 결재상황을 조회.
	 * 같은 클래스에서 상신취소에서 취소가능 한 지에 대한 여부를 선택할 때 사용.
	 * 
	 * @param argModuleId:모듈ID,  argMisId:MIS ID(ESB연동KEY)
	 * @return 결재상황 조회결과(1:결재진행중, 2:완결, 3:반려, 4:상신취소, 5:전결, 6:후완결)
	 */
	private String getStatusByMisId(String argModuleId,String argMisId) throws Exception {
		
		String result = null;
		
		try {

			// 결재상신 : 기본정보를 가져온다.
			HashMap getApprovalInfoMap = new HashMap();
			getApprovalInfoMap.put("module_id", argModuleId);
			getApprovalInfoMap.put("mis_id", argMisId);
			ArrayList ApprovalHeaderList = (ArrayList)commonDAO.list("secfw.singleIF.approval.getApprovalHeader", getApprovalInfoMap);

			ListOrderedMap resultMap = new ListOrderedMap();
			resultMap = (ListOrderedMap)ApprovalHeaderList.get(0);
			
			String status = (String)resultMap.get("status");
			
			//결재상신 대기문서 이거나, 결재진행 중인 문서에 한하여 결재상태 조회를 실시한다.
			if("0".equals(status) || "1".equals(status)) {

				APStatusInquiryServiceLocator serviceLoacator = new APStatusInquiryServiceLocator();
				APStatusInquiryService apStatusInquiryService = serviceLoacator.getAPStatusInquiryService_InboundPort();
				
				ProcessStatusWSVO processStatusWSVO = new ProcessStatusWSVO();
				
				ESBAuthVO esbAuthVO = EsbUtil.getESBAuthVO(esbApprId, esbApprPw);
				MisKeyWSVO misKeyWSVO = new MisKeyWSVO();
				misKeyWSVO.setMisID(argMisId);
				misKeyWSVO.setSystemID(esbApprId);
				
				processStatusWSVO = apStatusInquiryService.getStatusByMisId(esbAuthVO, misKeyWSVO);
				result = processStatusWSVO.getStatus();
	
			} else {
				result = status;
			}
			
		} catch(ESBFaultVO fault) {

			try {	
				//에러로그 
				insertError(argModuleId, argMisId, fault);
				
			} catch (SQLException e) {
				getLogger().error( "  SQLException : " + e);
				throw new Exception(e.getMessage());
		    } catch (Exception e) {
		    	getLogger().error( "  Exception : " + e);
		    	throw new Exception(e.getMessage());
		    }		       
		} catch (Exception e) {
        	getLogger().error( "  Exception : " + e);		    
		    throw new Exception(e.getMessage());
        } finally {  
        	return result;
        }
	}
	
	/**
	 * <P>에러로그</P>
	 * ESB연계시 발생한 에러로그를 남긴다.
	 * 테이블 : TB_COM_ESB_FAULT_VO 
	 * 
	 * @param argModuleId:모듈ID,  argMisId:MIS ID(ESB연동KEY), fault:ESBFaultVO 오류Value Object
	 */
	public void insertError(String argModuleId,String argMisId, ESBFaultVO fault) throws Exception {
		
		String ls_actor    =  fault.getFaultActor1();
		String ls_code     =  fault.getFaultCode1();
		String ls_string   =  fault.getFaultString1();
		String ls_message  =  fault.getFaultString();
		String ls_sequence = "";
		
		getLogger().error("Fault Actor   : " + ls_actor);
		getLogger().error("Fault Code    : " + ls_code);
		getLogger().error("Fault String  : " + ls_string);
		getLogger().error("Fault Message : " + ls_message);
		
		// 에러순번을 생성한다.
		HashMap genErrorSeqMap = new HashMap();
		genErrorSeqMap.put("module_id",argModuleId);
		genErrorSeqMap.put("mis_id",argMisId);
		
		ls_sequence = "";
		
		List tempList = commonDAO.list("secfw.singleIF.approval.genErrorSeq", genErrorSeqMap);
		
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
		
		commonDAO.insert("secfw.singleIF.approval.insertError", insertErrorMap);
							
	}

	/************************************************************************************
	 ***********************              결재내역 등록        **************************
	 ************************************************************************************/

	/**
	 * <P>결재상신 정보등록</P>
	 * 
	 * @param vo 싱글결재 Value Object
	 * @throws Exception 
	 */
	public void insertApproval(ApprovalVO vo) throws Exception {
		
		String time = EsbUtil.getTimeByGMT("yyyyMMddHHmmss");
		
		vo.setCreate_date(time);
		
		insertApprovalHeader(vo);
		insertApprovalPath(vo);
		insertAttachInfo(vo);
		
	}

	/**
	 * <P>결재기본 등록</P>
	 * 테이블 : TB_COM_START_PROCESS_WSVO
	 * 
	 * @param vo 싱글결재 Value Object
	 * @throws Exception 
	 */
	private void insertApprovalHeader(ApprovalVO vo) throws Exception {
		
		vo.setNoti_mail(StringUtil.bvl(vo.getNoti_mail(),"0")); //통보메일 (0:통보자, 1:모두)
		vo.setPriority(StringUtil.bvl(vo.getPriority(),"0"));	//긴급여부 (0:보통, 1:긴급)
		vo.setSecurity(StringUtil.bvl(vo.getSecurity(),"0"));	//보안구분 (0:일반, 1:대외비, 2:극비)
		
		commonDAO.insert("secfw.singleIF.approval.insertApprovalHeader", vo);
	}
	/**
	 * <P>결재선 정보를 등록</P>
	 * 
	 * 테이블 : TB_COM_ROUTE_WSVO
	 * 
	 * @param vo 싱글결재 Value Object
	 * @throws Exception 
	 */
	private void insertApprovalPath(ApprovalVO vo) throws Exception {
		
		String[] mail_addresss = vo.getMail_addresss();
		String[] action_types  = vo.getAction_types ();
		String[] activitys     = vo.getActivitys    ();
		String[] alarm_types   = vo.getAlarm_types  ();
		String[] approveds     = vo.getApproveds    ();
		String[] arbitrarys    = vo.getArbitrarys   ();
		String[] arriveds      = vo.getArriveds     ();
		String[] body_modifys  = vo.getBody_modifys ();
		String[] dept_codes    = vo.getDept_codes   ();
		String[] dept_names    = vo.getDept_names   ();
		String[] durations     = vo.getDurations    ();
		String[] dutys         = vo.getDutys        ();
		String[] duty_codes    = vo.getDuty_codes   ();
		String[] group_codes   = vo.getGroup_codes  ();
		String[] group_names   = vo.getGroup_names  ();
		String[] opinions      = vo.getOpinions     ();
		String[] reserveds     = vo.getReserveds    ();
		String[] route_modifys = vo.getRoute_modifys();
		String[] sequences     = vo.getSequences    ();
		String[] social_ids    = vo.getSocial_ids   ();
		String[] user_ids      = vo.getUser_ids     ();
		String[] user_names    = vo.getUser_names   ();
		String[] delegateds    = vo.getDelegateds   ();
		
		String cntrt_id = "";
		String cnsdreq_id = vo.getRef_key();
		List listCnt = null;
		
		if(!"".equals(cnsdreq_id)){
			listCnt = commonDAO.list("secfw.singleIF.approval.findCntrt_id", vo);
			
			if(0 < listCnt.size()){
				ListOrderedMap lom = (ListOrderedMap)listCnt.get(0);
				cntrt_id = (String)lom.get("cntrt_id");
			}
		}
		
		if(user_ids!=null && user_ids.length>0) {
			for(int i=0; i<user_ids.length; i++) {
				//vo.setMail_address(mail_addresss[i]);
                vo.setAction_type (action_types[i]);
                vo.setActivity    (activitys[i]);
                vo.setAlarm_type  (EsbUtil.bvl(vo.getAlarm_type(),"0"));      //(Option)SMS 전송 여부(1:미전송, 0:전송)
				//vo.setApproved    (approveds[i]);
                if ("R020218102334C105660".equals(user_ids[i])){
                	vo.setArbitrary   (EsbUtil.bvl(vo.getArbitrary(),"0"));      //(Option)전결권한(-1:불가, 0:가능)
                } else {
				vo.setArbitrary   (EsbUtil.bvl(vo.getArbitrary(),"-1"));      //(Option)전결권한(-1:불가, 0:가능)
                }
				//vo.setArrived     (arriveds[i]);
                vo.setBody_modify (EsbUtil.bvl(vo.getBody_modify(),"-1"));    //(Option)본문수정 권한(-1:불가, 0:가능)
                vo.setDept_code   (dept_codes[i]);
                vo.setDept_name   (dept_names[i]);
                vo.setDuration    (EsbUtil.bvl(vo.getDuration(),"0"));        //(Option)기한제 적용 시간(0:ESB는 적용불가)
                                
                if(dutys[i].length() > 35){
                	vo.setDuty(dutys[i].substring(0,34));
                } else {
                	vo.setDuty(dutys[i]);                	
                }     
				
                vo.setDuty_code   (duty_codes[i]);
                vo.setGroup_code  (group_codes[i]);
                vo.setGroup_name  (group_names[i]);
                vo.setOpinion     (StringUtil.bvl(opinions[i],""));
                if(reserveds!=null) vo.setReserved    (reserveds[i]);
                vo.setRoute_modify(EsbUtil.bvl(vo.getRoute_modify(),"-1"));   //(Option)경로 변경 권한(-1:불가, 0:가능)
				vo.setSequence    (String.valueOf(i));
                //vo.setSocial_id   (social_ids[i]);
                vo.setUser_id     (user_ids[i]);
                vo.setUser_name   (user_names[i]);
                //vo.setDelegated   (delegateds[i]);
                
                commonDAO.insert("secfw.singleIF.approval.insertApprovalPath", vo); //////////////////////
                    
                // 김재원 추가 
        		// 결재 내용이 등록 되면서 결재자로 등록된 사람들은 CC로 자동 지정이 되어야 한다.
        		
                vo.setMaster_cntrt_id(cntrt_id);	
				vo.setDemnd_seqno(Integer.toString(i+1));
				vo.setDemnd_gbn("C04601");
				vo.setDemndman_id(vo.getSession_user_id());	//요청자 아이디
				vo.setDemndman_nm(vo.getSession_user_nm());
				vo.setDemndman_dept_nm(vo.getSession_dept_nm());			
				vo.setTrgtman_id(user_ids[i]);  			//
				vo.setTrgtman_nm(user_names[i]);				
				vo.setTrgtman_dept_nm(user_names[i]);
				vo.setTrgtman_jikgup_nm(user_names[i]);
				vo.setDemnd_status("C03702");
				vo.setDemnd_cont("Approval Path Auto");
				
				commonDAO.insert("clm.manage.approvalCCinsertContractAuthreq",vo);
            
			}
		}

	}

	/**
	 * <P>첨부파일 등록:사용자가 상신한 결재문서의 첨부파일을 첨부테이블에 등록한다.</P>
	 * 테이블 : TB_COM_ATTACHMENT_WSVO
	 * 
	 * @param vo 싱글결재 Value Object
	 * @throws Exception 
	 */
	private void insertAttachInfo(ApprovalVO vo) throws Exception {

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
			fvo.setSys_gbn("approval");
			clmsFileService.mngComAttachFile(fvo);	
		}
	}
	
	/**
	 * 기안자 ESB조회 예외 여부 조회
	 */
	public List listExceptWsvoList(HashMap hm) throws Exception{
		String new_flag = StringUtil.bvl((String)hm.get("new_flag"), "N");
		String sql = new_flag.equals("N") ? "secfw.singleIF.approval.listExceptWsvoList" : "secfw.singleIF.approval.listExceptWsvo_NewList";
		return  commonDAO.list(sql, hm);
	}
	
	
	
	
	/**
	 * approval path 에 등록된 조건과 일치하는 결재자들 조회
	 *
	 */
	public List listExceptWsvoListPath(HashMap hm) throws Exception{

		ApprovalVO vo = new ApprovalVO();
		
		//계약정보
		ArrayList ContractInfo = (ArrayList) commonDAO.list("secfw.singleIF.approval.cntrtMasterInfo",hm);

		ListOrderedMap resultMap = new ListOrderedMap();
		resultMap = (ListOrderedMap)ContractInfo.get(0);
		
		
		HashMap lom = new HashMap(); 
		lom.put("requ_item", resultMap.get("REQU_ITEM"));
		
		getLogger().debug("========LOM 1= " + ((Integer)lom.get("requ_item")).intValue());
    	
		vo.setComp_cd(StringUtil.convertHtmlTochars(StringUtil.bvl((String)hm.get("compCd"),"")));
		vo.setBiz_clsfcn(StringUtil.convertHtmlTochars(StringUtil.bvl((String)resultMap.get("biz_clsfcn"),"")));
		vo.setDepth_clsfcn(StringUtil.convertHtmlTochars(StringUtil.bvl((String)resultMap.get("depth_clsfcn"),"")));
		vo.setCnclsnpurps_bigclsfcn(StringUtil.convertHtmlTochars(StringUtil.bvl((String)resultMap.get("cnclsnpurps_bigclsfcn"),"")));
		vo.setCnclsnpurps_midclsfcn(StringUtil.convertHtmlTochars(StringUtil.bvl((String)resultMap.get("cnclsnpurps_midclsfcn"),"")));
		vo.setReq_title_sub(StringUtil.convertHtmlTochars(StringUtil.bvl((String)resultMap.get("req_title_sub"),"")));
		
		vo.setRelated_products(StringUtil.convertHtmlTochars(StringUtil.bvl((String)resultMap.get("related_products"),"")));
		
		if(((Integer)lom.get("requ_item")).intValue() > 0 ){
			vo.setRequ_item("Y");		
		}else{
			vo.setRequ_item("N");
		}

		
	
		
		//contract detail (조건)
		ArrayList approvalInfo = (ArrayList) commonDAO.list("secfw.singleIF.approval.approvalInfo",vo);
		
		//path변수
		ArrayList pathInfo =new ArrayList(); 
		ListOrderedMap resultPath = new ListOrderedMap();
		
		//opn변수
		StringBuffer opn = new StringBuffer();
		
		StringBuffer orAmount = new StringBuffer();
		StringBuffer orAuto_yn = new StringBuffer();
		StringBuffer orModatory = new StringBuffer();
		
		StringBuffer andAmount = new StringBuffer();
		StringBuffer andAuto_yn = new StringBuffer();
		StringBuffer andModatory = new StringBuffer();
		
		StringBuffer path_id = new StringBuffer();
		

		vo.setPath_id("''");
		String loofId = "";
		vo.setCntrt_id(hm.get("cntrt_id").toString());
		
		
		String cntrtYn = "Y";
		
		getLogger().debug("======================================================================================================================================");
		getLogger().debug("vo.getPath_id="+vo.getPath_id()  + "=1=path_id=" + path_id + ", loofId = "+ loofId);
		getLogger().debug("======================================================================================================================================");

		if(approvalInfo != null && approvalInfo.size()>0) {
			
			ListOrderedMap appresultMap = new ListOrderedMap();
			
			for(int i=0; i<approvalInfo.size(); i++) {
				
				appresultMap = (ListOrderedMap)approvalInfo.get(i);
				
				getLogger().debug("=appresultMap=" + appresultMap.toString());
				
				
				//detail조건에 맞는 계약인지 확인 
				if(!loofId.equals((String)appresultMap.get("PATH_ID")) && loofId != ""){
					
					

					getLogger().debug("=====================");
					getLogger().debug("vo.getPath_id="+vo.getPath_id()  + "=2=path_id=" + path_id + ", loofId = "+ loofId);
					getLogger().debug("======================================================================================================================================");
			
					vo.setPath_Query(opn.toString());
					//vo.setCntrt_id(hm.get("cntrt_id").toString());
					pathInfo = (ArrayList) commonDAO.list("secfw.singleIF.approval.cntrtMasterEqual", vo);
					resultPath = (ListOrderedMap)pathInfo.get(0);
					
				
					
					if(((Integer)resultPath.get("ct_count")).intValue() > 0 ){
						path_id.append(loofId+",");
					}
					

					getLogger().debug("======================================================================================================================================");
					getLogger().debug("vo.getPath_id="+vo.getPath_id()  + "=3=path_id=" + path_id + ", loofId = "+ loofId);
					getLogger().debug("======================================================================================================================================");
					
					
					//buffer 초기화
					opn.delete(0, opn.capacity());

					
				}
				orAmount.delete(0, orAmount.capacity());
				orAuto_yn.delete(0, orAuto_yn.capacity());
				orModatory.delete(0, orModatory.capacity());
				andAmount.delete(0, andAmount.capacity());
				andAuto_yn.delete(0, andAuto_yn.capacity());
				andModatory.delete(0, andModatory.capacity());
				
				getLogger().debug("=====vo.getBiz_clsfcn()=" + vo.getBiz_clsfcn());
				getLogger().debug("=====vo.getDepth_clsfcn()=" + vo.getDepth_clsfcn());
				getLogger().debug("=====vo.getCnclsnpurps_bigclsfcn()=" + vo.getCnclsnpurps_bigclsfcn());
				getLogger().debug("=====vo.getCnclsnpurps_midclsfcn()=" + vo.getCnclsnpurps_midclsfcn());
				
				//계약조건 여부 확인
				if(!"".equals((String)appresultMap.get("type_1")) ){
					if(!vo.getBiz_clsfcn().equals((String)appresultMap.get("type_1")) ){
						cntrtYn ="N";
					}else{
						cntrtYn ="Y";
					}
				}
				
				if(!"".equals((String)appresultMap.get("type_2")) ){
					if(!vo.getDepth_clsfcn().equals((String)appresultMap.get("type_2")) ){
						cntrtYn ="N";
					}else{
						cntrtYn ="Y";
					}
				}
				
				
				if(!"".equals((String)appresultMap.get("type_3")) ){
					if(!vo.getCnclsnpurps_bigclsfcn().equals((String)appresultMap.get("type_3")) ){
						cntrtYn ="N";
					}else{
						cntrtYn ="Y";
					}
				}

				if(!"".equals((String)appresultMap.get("type_4")) ){
					if(!vo.getCnclsnpurps_midclsfcn().equals((String)appresultMap.get("type_4")) ){
						cntrtYn ="N";
					}else{
						cntrtYn ="Y";
					}
				}
				
				
				if("Y".equals(cntrtYn)){//계약조건 성립하는 경우의 if
				
				
	
					//조건이 OR인경우
					if("OR".equals((String)appresultMap.get("operation"))){
						
						if(opn.length() != 0){
							opn.append(" OR ");
						} 
						
						if("C001".equals((String)appresultMap.get("condition"))){
							orAmount.append("CNTRT_AMT");
			
							if("1".equals((String)appresultMap.get("condition_option"))){
								orAmount.append(" >  cast(replace('"+(String)appresultMap.get("condition_val")+"',',','') as numeric)");
							}else if("2".equals((String)appresultMap.get("condition_option"))){
								orAmount.append(" =  cast(replace('"+(String)appresultMap.get("condition_val")+"',',','') as numeric)");
							}else if("3".equals((String)appresultMap.get("condition_option"))){
								orAmount.append(" <  cast(replace('"+(String)appresultMap.get("condition_val")+"',',','') as numeric)");
							}
							
						}else if("C002".equals((String)appresultMap.get("condition"))){
							orAuto_yn.append("AUTO_RNEW_YN");
							orAuto_yn.append(" = '"+ (String)appresultMap.get("condition_val")+"'");
						}else if("C003".equals((String)appresultMap.get("condition"))){
							
						
							if("Y".equals((String)appresultMap.get("condition_val"))){
					
								//필수조항 15개에 만족한다면 
								if(((Integer)lom.get("requ_item")).intValue() > 0 ){
									orModatory.append("1=1");	
								}
								
							}else if("N".equals((String)appresultMap.get("condition_val"))){
								//필수조항 15개에 만족한다면 
								if(((Integer)lom.get("requ_item")).intValue() > 0 ){
									orModatory.append("1=2");	
								}
							}
						}
						
						
						opn.append(orAmount);
						opn.append(orAuto_yn);
						opn.append(orModatory);
							
					//detail 조건이 and 인경우
					}else if("AND".equals((String)appresultMap.get("operation"))){
						
						if(opn.length() != 0){
							opn.append(" AND ");
						} 
						
						if("C001".equals((String)appresultMap.get("condition"))){
							andAmount.append(" CNTRT_AMT");
							if("1".equals((String)appresultMap.get("condition_option"))){
								andAmount.append(" >  cast(replace('"+(String)appresultMap.get("condition_val")+"',',','') as numeric)");
							}else if("2".equals((String)appresultMap.get("condition_option"))){
								andAmount.append(" =  cast(replace('"+(String)appresultMap.get("condition_val")+"',',','') as numeric)");
							}else if("3".equals((String)appresultMap.get("condition_option"))){
								andAmount.append(" <  cast(replace('"+(String)appresultMap.get("condition_val")+"',',','') as numeric)");
							}
						}else if("C002".equals((String)appresultMap.get("condition"))){
							andAuto_yn.append("AUTO_RNEW_YN");
							andAuto_yn.append(" = '"+ (String)appresultMap.get("condition_val")+"'");
							
						}else if("C003".equals((String)appresultMap.get("condition"))){
							
						
							if("Y".equals((String)appresultMap.get("condition_val"))){
								//필수조항 15개에 만족한다면 
								if(((Integer)lom.get("requ_item")).intValue() > 0 ){
									andModatory.append("1=1");	
								}else{
									andModatory.append("1=2");	
								}
							}else if("N".equals((String)appresultMap.get("condition_val"))){
								//필수조항 15개에 만족한다면 
								if(((Integer)lom.get("requ_item")).intValue() > 0 ){
									andModatory.append("1=2");	
								}else{
									andModatory.append("1=1");	
								}
							}
						}
		
						opn.append(andAmount);
						opn.append(andAuto_yn);
						opn.append(andModatory);
						
					}//detail 조건의 if end
	
				}else{//계약조건 성립하는 경우의 if end
					if(opn.length() != 0){
						opn.append(" AND ");
					} 
					opn.append("1=2");
				}

				getLogger().debug("======================================================================================================================================");
				getLogger().debug("vo.getPath_id="+vo.getPath_id()  + "=4=path_id=" + path_id + ", loofId = "+ loofId);
				getLogger().debug("======================================================================================================================================");
				
				
				loofId = (String)appresultMap.get("PATH_ID");

				getLogger().debug("======================================================================================================================================");
				getLogger().debug("vo.getPath_id="+vo.getPath_id()  + "=5=path_id=" + path_id + ", loofId = "+ loofId);
				getLogger().debug("======================================================================================================================================");
				
				
			}//for문end
			

			getLogger().debug("======================================================================================================================================");
			getLogger().debug("vo.getPath_id="+vo.getPath_id()  + "=6=path_id=" + path_id + ", loofId = "+ loofId);
			getLogger().debug("======================================================================================================================================");
			
			
			if("Y".equals(cntrtYn)){
				vo.setPath_Query(opn.toString());
				//vo.setCntrt_id(hm.get("cntrt_id").toString());
				pathInfo = (ArrayList) commonDAO.list("secfw.singleIF.approval.cntrtMasterEqual", vo);
				resultPath = (ListOrderedMap)pathInfo.get(0);
				
				
				if(((Integer)resultPath.get("ct_count")).intValue() > 0 ){
					path_id.append(loofId+",");
				}
				
				//buffer 초기화
				opn.delete(0, opn.capacity());
				orAmount.delete(0, orAmount.capacity());
				orAuto_yn.delete(0, orAuto_yn.capacity());
				orModatory.delete(0, orModatory.capacity());
				andAmount.delete(0, andAmount.capacity());
				andAuto_yn.delete(0, andAuto_yn.capacity());
				andModatory.delete(0, andModatory.capacity());
			}			

			getLogger().debug("======================================================================================================================================");
			getLogger().debug("vo.getPath_id="+vo.getPath_id()  + "=7=path_id=" + path_id + ", loofId = "+ loofId);
			getLogger().debug("======================================================================================================================================");
						
			if(path_id.length() > 0){
				vo.setPath_id(path_id.toString().substring(0, path_id.toString().length()-1));
			}

			getLogger().debug("======================================================================================================================================");
			getLogger().debug("vo.getPath_id="+vo.getPath_id()  + "=8=path_id=" + path_id + ", loofId = "+ loofId);
			getLogger().debug("======================================================================================================================================");			
			
		}

		
		
		vo.setUser_id((String)hm.get("user_id"));
		List resultList = null;

		resultList = commonDAO.list("secfw.singleIF.approval.listExceptWsvoListPathForSEF", vo);
		
		List isTNCList = commonDAO.list("secfw.singleIF.approval.isTNCContract", vo);


		Map isTNCMap  = new HashMap(); 
		

		if (isTNCList.size() > 0 && resultList.size() <= 1 ) {
			isTNCMap = (Map) isTNCList.get(0);
			
			resultList.clear();
			if ( "true".equals(isTNCMap.get("isTNC"))) {
				resultList  = commonDAO.list("secfw.singleIF.approval.listExceptWsvoListPathForTNC", vo);

			} else {
				
				resultList  = commonDAO.list("secfw.singleIF.approval.listExceptWsvoListPath", vo);
			}
		
			
		}	
		
		//중복인 결재자와 합의자를 찾아 합의자를 뺀다
		ListOrderedMap resultListMap = new ListOrderedMap();
		
		StringBuffer approveresult_id = new StringBuffer();
		StringBuffer consentresult_id = new StringBuffer();
		
		for(int i=0; i<resultList.size(); i++) {
			
			resultListMap = (ListOrderedMap)resultList.get(i);
			
			if("1".equals(resultListMap.get("approval_type"))){
				approveresult_id.append(resultListMap.get("user_id")+"/");
			}
			
			if("2".equals(resultListMap.get("approval_type"))){
				if(approveresult_id.toString().contains((String)resultListMap.get("user_id"))){
					resultList.remove(i);
				}
			}
			
		}
		
		

			return  resultList;

	}
	
	

	protected Log getLogger() {
		return LogFactory.getLog(this.getClass());
	}

}
