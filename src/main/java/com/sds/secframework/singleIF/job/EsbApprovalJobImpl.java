package com.sds.secframework.singleIF.job;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import mySingle.service.APStatusInquiryServiceLocator;
import mySingle.service.APSubmitServiceLocator;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Element;

import samsung.esb.approval.service.APStatusInquiryService;
import samsung.esb.approval.service.APSubmitService;
import samsung.esb.approval.vo.MisKeyWSVO;
import samsung.esb.approval.vo.ProcessStatusWSVO;
import samsung.esb.approval.vo.RouteWSVO;
import samsung.esb.approval.vo.StartProcessWSVO;
import samsung.esb.common.vo.ESBAuthVO;
import samsung.esb.common.vo.ESBFaultVO;

import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.util.EsbUtil;
import com.sds.secframework.singleIF.dto.ApprovalRouteVO;
import com.sds.secframework.singleIF.service.EsbApprovalPostProcessService;
import com.sds.secframework.singleIF.service.EsbApprovalService;
import com.sds.secframework.util.service.ComUtilService;

/**
 * <P>
 * Single ESB 결재 Class
 * </P>
 *
 * [지원기능]<BR>
 * <BR>
 * - [배치작업]결재상신 : 일정주기별로 결재대기중인 건을 읽어와서 결재상신<BR>
 * - [배치작업]결재상태조회 : 일정주기별로 결재상태 정보를 ESB연동조회 후 결재테이블에 반영<BR>
 *
 * @version com.sds.secframework.singleIF V1.0 작성일 : 2009/12/18
 * @author 금현서, ace4u_khs@samsung.com
 */
public class EsbApprovalJobImpl {

	private static String esbApprId = "";
	private static String esbApprPw = "";
	private static String esbDevTF = "";

	public void setEsbApprId(String esbApprId) {
		this.esbApprId = esbApprId;
	}

	public void setEsbApprPw(String esbApprPw) {
		this.esbApprPw = esbApprPw;
	}

	public void setEsbDevTF(String esbDevTF) {
		this.esbDevTF = esbDevTF;
	}

	protected CommonDAO commonDAO;
	protected PropertyService propertyService;
	protected ComUtilService comUtilService;

	/**
	 * comUtilService 선언 및 세팅
	 */
	public void setComUtilService(ComUtilService comUtilService) {
		this.comUtilService = comUtilService;
	}

	/**
	 * set commonDAO
	 */
	public void setCommonDAO(CommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}

	/**
	 * set propertyService
	 */
	public void setPropertyService(PropertyService propertyService) {
		this.propertyService = propertyService;
	}

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private EsbApprovalService esbApprovalService;

	public void setEsbApprovalService(EsbApprovalService esbApprovalService) {
		this.esbApprovalService = esbApprovalService;
	}

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private EsbApprovalPostProcessService esbApprovalPostProcessService;

	public void setEsbApprovalPostProcessService(EsbApprovalPostProcessService esbApprovalPostProcessService) {
		this.esbApprovalPostProcessService = esbApprovalPostProcessService;
	}

	/************************************************************************************
	 *********************** ESB 결재연계 *************************
	 ************************************************************************************/

	/**
	 * <P>
	 * [배치작업]결재상신
	 * </P>
	 * 일정주기별로 결재대기중인 건을 읽어와서 결재상신 한다. 결과를 결재 테이블(TB_COM_START_PROCESS_WSVO)에 반영.
	 *
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public synchronized void submitBatch() {
		String ls_moduleId = "";
		String ls_misId = "";

		try {
			APSubmitServiceLocator serviceLoacator = new APSubmitServiceLocator();
			APSubmitService apSubmitService = serviceLoacator.getAPSubmitService_InboundPort();

			// 결재 대기건을 읽어와서 결재 상신을 한다.
			HashMap<String, String> targetMap = new HashMap<String, String>();
			targetMap.put("status", "0");
			ArrayList targetList = (ArrayList) commonDAO.list("secfw.singleIF.approval.listTargetList", targetMap);

			if (targetList != null && targetList.size() > 0) {
				ESBAuthVO esbAuthVO = EsbUtil.getESBAuthVO(esbApprId, esbApprPw);

				for (int i = 0; i < targetList.size(); i++) {
					HashMap resultTargetMap = (HashMap) targetList.get(i);

					ls_moduleId = (String) resultTargetMap.get("module_id");
					ls_misId = (String) resultTargetMap.get("mis_id");

					StartProcessWSVO startProcessWSVO = esbApprovalService.getStartProcessWSVO(ls_moduleId, ls_misId);
					startProcessWSVO.setAttachmentVOs(esbApprovalService.getAttachmentWSVO(ls_moduleId, ls_misId));
					startProcessWSVO.setRouteVOs(esbApprovalService.getRouteWSVO(ls_moduleId, ls_misId));

					String returnMsg = apSubmitService.submitApproval(esbAuthVO, startProcessWSVO);

					// Header 상태를 업데이트 한다.
					HashMap<String, String> modifyStatusMap = new HashMap<String, String>();
					modifyStatusMap.put("status", "1");
					modifyStatusMap.put("module_id", ls_moduleId);
					modifyStatusMap.put("mis_id", ls_misId);

					commonDAO.modify("secfw.singleIF.approval.modifyStatus", modifyStatusMap);
				}
			} else {
				// getLogger().debug( "■■■■■■■■ 상신할 결재정보가 없습니다.■■■■■■■■");
			}
		} catch (ESBFaultVO fault) {
			fault.printStackTrace();

			try {
				// 에러 로그
				esbApprovalService.insertError(ls_moduleId, ls_misId, fault);

				// 결재상태 코드를 Error로 업데이트 한다.
				HashMap modifyStatusMap = new HashMap();
				modifyStatusMap.put("status", "E");
				modifyStatusMap.put("module_id", ls_moduleId);
				modifyStatusMap.put("mis_id", ls_misId);

				commonDAO.modify("secfw.singleIF.approval.modifyStatus", modifyStatusMap);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <P>
	 * [배치작업]결재상태조회
	 * </P>
	 * 결재상태 조회. 결재상태 정보를 ESB연동 후 결재테이블에 반영. 결과를 결재
	 * 테이블(TB_COM_START_PROCESS_WSVO)에 반영.
	 *
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public synchronized void getStatusBatch() throws Exception {

		if (comUtilService.isCronServer()) {
			String ls_moduleId = "";
			String ls_misId = "";

			ProcessStatusWSVO processStatusWSVO = null;
			ArrayList postProcessList = new ArrayList();

			try {
				// 결재진행중인 건을 읽어온다.
				HashMap targetMap = new HashMap();
				targetMap.put("status", "1");
				ArrayList targetList = (ArrayList) commonDAO.list("secfw.singleIF.approval.listTargetList", targetMap);

				if (targetList != null && targetList.size() > 0) {
					getLogger().info("### Approval Status Batch getStatusBatch :" + targetList.size());
					ESBAuthVO esbAuthVO = EsbUtil.getESBAuthVO(esbApprId, esbApprPw);

					for (int i = 0; i < targetList.size(); i++) {
						ListOrderedMap resultTargetMap = (ListOrderedMap) targetList.get(i);

						// 2011.12.05 에러가 나더라도 나머지 결재건은 계속 진행할수 있도록 하기위해 for문내에
						// try문을 넣음
						try {
							ls_moduleId = (String) resultTargetMap.get("module_id");
							ls_misId = (String) resultTargetMap.get("mis_id");

							APStatusInquiryServiceLocator serviceLoacator = new APStatusInquiryServiceLocator();
							APStatusInquiryService apStatusInquiryService = serviceLoacator.getAPStatusInquiryService_InboundPort();

							processStatusWSVO = new ProcessStatusWSVO();

							MisKeyWSVO misKeyWSVO = new MisKeyWSVO();
							misKeyWSVO.setMisID(ls_misId);
							misKeyWSVO.setSystemID(esbApprId);

							processStatusWSVO = apStatusInquiryService.getStatusByMisId(esbAuthVO, misKeyWSVO);
							RouteWSVO[] routeWSVO = processStatusWSVO.getRouteVOs();

							// 결재경로 정보를 삭제한다.
							HashMap deleteApprovalPathMap = new HashMap();
							deleteApprovalPathMap.put("module_id", ls_moduleId);
							deleteApprovalPathMap.put("mis_id", ls_misId);

							if (i == 0) {
								getLogger().info("### delete previous approval; module_id :" + ls_moduleId + ", mis_id : " + ls_misId);
							}
							commonDAO.delete("secfw.singleIF.approval.deleteApprovalPath", deleteApprovalPathMap);

							// 결재경로 정보를 신규로 입력한다.(싱글결재 경로로 재설정작업)
							for (int j = 0; j < routeWSVO.length; j++) {
								ApprovalRouteVO vo = new ApprovalRouteVO();

								vo.setModule_id(ls_moduleId);
								vo.setMis_id(ls_misId);
								vo.setAction_type(routeWSVO[j].getActionType());
								vo.setActivity(routeWSVO[j].getActivity());
								vo.setAlarm_type(routeWSVO[j].getAlarmType());
								vo.setApproved(routeWSVO[j].getApproved());
								vo.setArbitrary(routeWSVO[j].getArbitrary());
								vo.setArrived(routeWSVO[j].getArrived());
								vo.setBody_modify(routeWSVO[j].getBodyModify());
								vo.setComp_code(routeWSVO[j].getCompCode());
								vo.setComp_name(routeWSVO[j].getCompName());
								vo.setDept_code(routeWSVO[j].getDeptCode());
								vo.setDept_name(routeWSVO[j].getDeptName());
								vo.setDuration(routeWSVO[j].getDuration());
								vo.setDuty(routeWSVO[j].getDuty());
								vo.setDuty_code(routeWSVO[j].getDutyCode());
								vo.setGroup_code(routeWSVO[j].getGroupCode());
								vo.setGroup_name(routeWSVO[j].getGroupName());
								vo.setMail_address(routeWSVO[j].getMailAddress());
								vo.setOpinion(routeWSVO[j].getOpinion());
								vo.setReserved(routeWSVO[j].getReserved());
								vo.setRoute_modify(routeWSVO[j].getRouteModify());
								vo.setSequence(routeWSVO[j].getSequence());
								vo.setSocial_id(routeWSVO[j].getSocialID());
								vo.setUser_id(routeWSVO[j].getUserID());
								vo.setUser_name(routeWSVO[j].getUserName());
								vo.setDelegated(routeWSVO[j].getDelegated());

								getLogger().error("################################################");
								getLogger().error("# DUTY : " + routeWSVO[j].getDuty());
								getLogger().error("################################################");

								if (i == 0) {
									getLogger().info("### insert the updated approval; module_id :" + vo.toString());
								}
								commonDAO.insert("secfw.singleIF.approval.insertApprovalPath", vo);
							}

							// Header 상태를 업데이트 한다.
							HashMap modifyStatusMap = new HashMap();

							if (i == 0) {
								getLogger().info("### Update the current approval Status :" + processStatusWSVO.getStatus());
							}

							modifyStatusMap.put("status", processStatusWSVO.getStatus());
							modifyStatusMap.put("module_id", ls_moduleId);
							modifyStatusMap.put("mis_id", ls_misId);

							commonDAO.modify("secfw.singleIF.approval.modifyStatus", modifyStatusMap);

							// 결재후프로세스를 위한 키값 저장.
							HashMap tempMap = new HashMap();
							tempMap.put("module_id", ls_moduleId);
							tempMap.put("mis_id", ls_misId);
							tempMap.put("status", processStatusWSVO.getStatus());

							postProcessList.add(tempMap);
						} catch (ESBFaultVO fault) {
							fault.printStackTrace();

							esbApprovalService.insertError(ls_moduleId, ls_misId, fault);

							String ls_actor = fault.getFaultActor1();
							String ls_code = fault.getFaultCode1();
							String ls_string = fault.getFaultString1();
							String ls_message = fault.getFaultString();
							String ls_detail_message = fault.getFaultString1();
							Element[] fault_elements = fault.getFaultDetails();

							for (int irow = 0; i < fault_elements.length; irow++) {
								// for what??
							}

							// 2012.04.19 ESB에러시 상태값을 바꾸지 않으면 다음프로세스에 영향이 갈 수
							// 있으므로 추가 by 김회기
							HashMap modifyStatusMap = new HashMap();

							modifyStatusMap.put("status", "E");
							modifyStatusMap.put("module_id", ls_moduleId);
							modifyStatusMap.put("mis_id", ls_misId);

							commonDAO.modify("secfw.singleIF.approval.modifyStatus", modifyStatusMap);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} else {
					// getLogger().debug(
					// "■■■■■■■■ 상태조회할  결재정보가 없습니다.■■■■■■■■");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 결재후 프로세스
				// 결재진행중(status가 1)이 아닌 결재문건의 경우 후처리 프로세스 메소드를 호출할 수 있도록 지원
				if (postProcessList.size() > 0) {
					for (int i = 0; i < postProcessList.size(); i++) {
						HashMap postProcessMap = (HashMap) postProcessList.get(i);

						String postModuleId = (String) postProcessMap.get("module_id");
						String postMidId = (String) postProcessMap.get("mis_id");
						String postStatus = (String) postProcessMap.get("status");

						// 2:완결, 3:반려, 4:상신취소, 5:전결, 6:후완결시 후속프로세스 진행
						if ("2".equals(postStatus) || "3".equals(postStatus) || "4".equals(postStatus) || "5".equals(postStatus) || "6".equals(postStatus)) {
							// EsbApprovalPostProcessService postService = new
							// EsbApprovalPostProcessServiceImpl();
							esbApprovalPostProcessService.postProcess(postModuleId, postMidId, postStatus);
						}
					}// end for
				}
			}
		}
	}

	protected Log getLogger() {
		return LogFactory.getLog(this.getClass());
	}
}
