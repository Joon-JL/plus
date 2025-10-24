package com.sec.clm.manage.control;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.EsbUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.singleIF.dto.ApprovalVO;
import com.sds.secframework.singleIF.service.EsbApprovalService;
import com.sds.secframework.singleIF.service.EsbOrgService;
import com.sds.secframework.util.service.ComUtilService;
import com.sec.clm.manage.dto.CompletionForm;
import com.sec.clm.manage.dto.CompletionVO;
import com.sec.clm.manage.dto.ConsultationForm;
import com.sec.clm.manage.dto.ConsultationVO;
import com.sec.clm.manage.dto.GERPInfoVO;
import com.sec.clm.manage.service.CompletionService;
import com.sec.clm.manage.service.ConsiderationService;
import com.sec.common.clmscom.service.CLMSCommonService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Description : 이행정보 Controller Author : 신승완 Date : 2011. 09. 05
 */
public class CompletionController extends CommonController {
	
	private static Log LOGGER = LogFactory.getLog(CompletionController.class);

	private ManageController manageControl;

	public void setManageControl(ManageController manageControl) {
		this.manageControl = manageControl;
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
	private EsbOrgService esbOrgService;

	public void setEsbOrgService(EsbOrgService esbOrgService) {
		this.esbOrgService = esbOrgService;
	}

	/**
	 * ExecutionService 서비스
	 */
	private CompletionService completionService;

	/**
	 * ComUtil 서비스
	 */
	@SuppressWarnings("unused")
	private ComUtilService comUtilService;

	/**
	 * ExecutionService 서비스 세팅 @param executionService @return void @throws
	 */
	public void setCompletionService(CompletionService completionService) {
		this.completionService = completionService;
	}

	/**
	 * ComUtil 서비스 세팅 @param comUtilService @return void @throws
	 */
	public void setComUtilService(ComUtilService comUtilService) {
		this.comUtilService = comUtilService;
	}

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	@SuppressWarnings("unused")
	private CLMSCommonService clmsComService;

	public void setClmsComService(CLMSCommonService clmsComService) {
		this.clmsComService = clmsComService;
	}

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private ConsiderationService considerationService;

	public void setConsiderationService(ConsiderationService considerationService) {
		this.considerationService = considerationService;
	}

	/**
	 * 계약종료 목록 작성자 : 신남원
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listManageCompletion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setAttribute("status_mode", "C02505");
		mav = manageControl.listManage(request, response);

		return mav;
	}

	/**
	 * 계약정보 전체조회 @param request, response @return ModelAndView @throws
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView listContract(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter
			 **********************************************************/
			
			
			String forwardURL = "";
			CompletionForm form = null;
			CompletionVO vo = null;
			List resultList = new ArrayList();
			List detailList = new ArrayList();
			List tempList = null;
			ListOrderedMap initLom = null;

			HttpSession session = null;
			/*********************************************************
			 * Session Check
			 **********************************************************/
			session = request.getSession(false);

			/*********************************************************
			 * Forward setting
			 **********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/manage/Completion_d.jsp";

			/*********************************************************
			 * Form & VO Binding
			 **********************************************************/
			form = new CompletionForm();
			vo = new CompletionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * roleCheck
			 **********************************************************/
			if ("renewApproval".equals(form.getConListGu())) {// 자동연장승인
				forwardURL = "/WEB-INF/jsp/clm/manage/CompletionApproval_d.jsp";
			} else if ("preview".equals(form.getConListGu())) { // 종료 인쇄 화면
				forwardURL = "/WEB-INF/jsp/clm/manage/Completion_d_preview.jsp";
			}

			tempList = completionService.listContract(vo);

			/*********************************************************
			 * Service
			 **********************************************************/
			if (tempList != null && tempList.size() > 0) {
				for (int i = 0; i < tempList.size(); i++) {
					resultList.add(tempList.get(i));
				}

				if (resultList.size() > 0) {
					initLom = (ListOrderedMap) resultList.get(0);
					form.setCnsdreq_id((String) initLom.get("cnsdreq_id"));
					form.setCntrt_id((String) initLom.get("cntrt_id"));
					form.setCntrt_status((String) initLom.get("cntrt_status"));
					form.setPrcs_depth((String) initLom.get("prcs_depth"));
					form.setDepth_status((String) initLom.get("depth_status"));
					form.setReqman_id((String) initLom.get("reqman_id"));
					form.setReqman_nm((String) initLom.get("reqman_nm"));
					form.setCntrt_respman_id((String) initLom.get("cntrt_respman_id"));
					form.setCntrt_respman_nm((String) initLom.get("cntrt_respman_nm"));
				}
			}

			vo.setCntrt_id(form.getCntrt_id());
			detailList = completionService.detailManageCompletion(vo);

			ListOrderedMap contractMstLom = null;
			ArrayList contractRelationLom = null;
			ListOrderedMap approveManLom = null;
			ArrayList orgMngHistory = null;
			ListOrderedMap completionLom = null;
			List authReqList = null;

			StringBuffer reqAuthInfo = new StringBuffer();

			if (detailList != null && detailList.size() > 0) {
				// 1. 계약정보
				if (detailList.get(0) != null && ((ArrayList) detailList.get(0)).size() > 0) {
					contractMstLom = (ListOrderedMap) ((ArrayList) detailList.get(0)).get(0);
					contractMstLom.put("antcptnefct", StringUtil.convertEnterToBR((String) contractMstLom.get("antcptnefct")));
					// 지불/수분조건 <BR>PAYMENT_COND
					contractMstLom.put("payment_cond", StringUtil.convertEnterToBR((String) contractMstLom.get("payment_cond")));
					// 기타주요사항 <BR>ETC_MAIN_CONT
					contractMstLom.put("etc_main_cont", StringUtil.convertEnterToBR((String) contractMstLom.get("etc_main_cont")));
					// CNTRT_CHGE_DEMND_CAUSE
					contractMstLom.put("cntrt_chge_demnd_cause", StringUtil.convertEnterToBR((String) contractMstLom.get("cntrt_chge_demnd_cause")));
					// 단가내역 요약cntrt_untprc_expl CNTRT_UNTPRC_EXPL
					contractMstLom.put("cntrt_untprc_expl", StringUtil.convertEnterToBR((String) contractMstLom.get("cntrt_untprc_expl")));
					// 책임한도 oblgt_lmt OBLGT_LMT
					contractMstLom.put("oblgt_lmt", StringUtil.convertEnterToBR((String) contractMstLom.get("oblgt_lmt")));
					// Special Condition spcl_cond SPCL_COND
					contractMstLom.put("spcl_cond", StringUtil.convertEnterToBR((String) contractMstLom.get("spcl_cond")));
					// loac_etc 준거법 상세 LOAC_ETC
					contractMstLom.put("loac_etc", StringUtil.convertEnterToBR((String) contractMstLom.get("loac_etc")));
					// dspt_resolt_mthd_det 분쟁해결방법상세 DSPT_RESOLT_DET
					contractMstLom.put("dspt_resolt_mthd_det", StringUtil.convertEnterToBR((String) contractMstLom.get("dspt_resolt_mthd_det")));
					// 메모
					contractMstLom.put("org_acpt_dlay_cause", StringUtil.convertEnterToBR((String) contractMstLom.get("org_acpt_dlay_cause")));

				}
				// 2. 연관계약정보
				if (detailList.get(1) != null && ((ArrayList) detailList.get(1)).size() > 0) {
					contractRelationLom = (ArrayList) detailList.get(1);
				}
				// 3. 승인자 정보(종료정보 中)
				if (detailList.get(2) != null && ((ArrayList) detailList.get(2)).size() > 0) {
					approveManLom = (ListOrderedMap) ((ArrayList) detailList.get(2)).get(0);
				}
				// 8. 종료정보
				if (detailList.get(3) != null && ((ArrayList) detailList.get(3)).size() > 0) {
					completionLom = (ListOrderedMap) ((ArrayList) detailList.get(3)).get(0);
				}
				// 9. 자동연장 이력
				if (detailList.get(4) != null && ((ArrayList) detailList.get(4)).size() > 0) {
					orgMngHistory = (ArrayList) detailList.get(4);
				}
				// 11.관련자 정보
				if (detailList.get(5) != null && ((List) detailList.get(5)).size() > 0) {
					authReqList = (ArrayList) detailList.get(5);

					for (int i = 0; i < authReqList.size(); i++) {
						ListOrderedMap authLom = (ListOrderedMap) authReqList.get(i);
						if (i > 0) {
							reqAuthInfo.append(", " + StringUtil.bvl((String) authLom.get("trgtman_nm"), "") + "/"
									+ StringUtil.bvl((String) authLom.get("trgtman_jikgup_nm"), "") + "/"
									+ StringUtil.bvl((String) authLom.get("trgtman_dept_nm"), "")); // display용
						} else {
							reqAuthInfo.append(
									StringUtil.bvl((String) authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String) authLom.get("trgtman_jikgup_nm"), "")
											+ "/" + StringUtil.bvl((String) authLom.get("trgtman_dept_nm"), ""));
						}
					}
				}
			}

			List tmpSessionRoleList = (List) session.getAttribute("secfw.session.role");
			String user_role = "";
			for (int i = 0; i < tmpSessionRoleList.size(); i++) {
				Map tmpSessionRoleMap = (Map) tmpSessionRoleList.get(i);
				if (tmpSessionRoleMap.containsValue("RD01")) {
					user_role = "RD01";
				}
			}

			/**
			 * 구주 법무시스템의 경우 로그인하 사람의 권한이 Legal Admin(RA01)일 경우 계약의 모든 권한을 가지게
			 * 된다.
			 */
			ArrayList userRoleList = (ArrayList) session.getAttribute("secfw.session.roleList"); // session에서
																									// ROLE에
																									// 대한
																									// 값을
																									// 가지고
																									// 온다.

			if (userRoleList.contains("RA01") || userRoleList.contains("RD01") || userRoleList.contains("RM00")) {
				form.setTop_role("RA01");
			} else if (userRoleList.contains("RA02") || userRoleList.contains("RA03")) {
				form.setTop_role("RA02");
			} else {
				form.setTop_role("ETC");
			}

			/*********************************************************
			 * ModelAndView
			 **********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + forwardURL);

			mav.addObject("contractReqLom", initLom);
			mav.addObject("contractMstLom", contractMstLom);
			mav.addObject("contractRelationLom", contractRelationLom);
			mav.addObject("approveManLom", approveManLom);
			mav.addObject("completionLom", completionLom);
			mav.addObject("user_role", user_role);
			mav.addObject("orgMngHistory", orgMngHistory);
			mav.addObject("reqAuthInfo", reqAuthInfo);
			mav.addObject("authReqList", authReqList);
			mav.addObject("command", form);

			mav.addObject("ssems_oppnt_cd", StringUtil.bvl(request.getParameter("ssems_oppnt_cd"), ""));// ssems인터페이스시
																										// 사용

			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("listContract() Exception !!");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("listContract() Throwable !!");
		}
	}

	/**
	 * 2014-05-14 Kevin added. This method is used to show a contract detail for
	 * requested from outside of SELMS+. This is consumed by GERP and other
	 * systems out of SELMS+.
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView getContractDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			String forwardURL = "/WEB-INF/jsp/clm/manage/ContractDetailForExternal.jsp";

			CompletionForm form = null;
			CompletionVO vo = null;
			List detailList = new ArrayList();
			List tempList = null;
			ListOrderedMap initLom = null;
			form = new CompletionForm();
			vo = new CompletionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			tempList = completionService.listContract(vo);

			if (tempList != null && tempList.size() > 0) {
				initLom = (ListOrderedMap) tempList.get(0);
				form.setCnsdreq_id((String) initLom.get("cnsdreq_id"));
				form.setCntrt_id((String) initLom.get("cntrt_id"));
				form.setCntrt_status((String) initLom.get("cntrt_status"));
				form.setPrcs_depth((String) initLom.get("prcs_depth"));
				form.setDepth_status((String) initLom.get("depth_status"));
				form.setReqman_id((String) initLom.get("reqman_id"));
				form.setReqman_nm((String) initLom.get("reqman_nm"));
				form.setCntrt_respman_id((String) initLom.get("cntrt_respman_id"));
				form.setCntrt_respman_nm((String) initLom.get("cntrt_respman_nm"));
			}

			vo.setCntrt_id(form.getCntrt_id());
			detailList = completionService.detailManageCompletion(vo);

			ListOrderedMap contractMstLom = null;
			ArrayList contractRelationLom = null;
			ListOrderedMap approveManLom = null;
			ArrayList review = null;
			ArrayList approve = null;
			ArrayList info = null;
			ArrayList executionList = null;
			ArrayList orgMngHistory = null;
			ListOrderedMap completionLom = null;
			ArrayList special1List = new ArrayList();
			ArrayList special2List = new ArrayList();
			ArrayList specialList = null;

			String payment_gbn = "";
			List authReqList = null;

			StringBuffer reqAuthInfo = new StringBuffer();

			if (detailList != null && detailList.size() > 0) {
				// 1. 계약정보
				if (detailList.get(0) != null && ((ArrayList) detailList.get(0)).size() > 0) {
					contractMstLom = (ListOrderedMap) ((ArrayList) detailList.get(0)).get(0);
					contractMstLom.put("antcptnefct", StringUtil.convertEnterToBR((String) contractMstLom.get("antcptnefct")));
					// 지불/수분조건 <BR>PAYMENT_COND
					contractMstLom.put("payment_cond", StringUtil.convertEnterToBR((String) contractMstLom.get("payment_cond")));
					// 기타주요사항 <BR>ETC_MAIN_CONT
					contractMstLom.put("etc_main_cont", StringUtil.convertEnterToBR((String) contractMstLom.get("etc_main_cont")));
					// CNTRT_CHGE_DEMND_CAUSE
					contractMstLom.put("cntrt_chge_demnd_cause", StringUtil.convertEnterToBR((String) contractMstLom.get("cntrt_chge_demnd_cause")));
					// 단가내역 요약cntrt_untprc_expl CNTRT_UNTPRC_EXPL
					contractMstLom.put("cntrt_untprc_expl", StringUtil.convertEnterToBR((String) contractMstLom.get("cntrt_untprc_expl")));
					// 책임한도 oblgt_lmt OBLGT_LMT
					contractMstLom.put("oblgt_lmt", StringUtil.convertEnterToBR((String) contractMstLom.get("oblgt_lmt")));
					// Special Condition spcl_cond SPCL_COND
					contractMstLom.put("spcl_cond", StringUtil.convertEnterToBR((String) contractMstLom.get("spcl_cond")));
					// loac_etc 준거법 상세 LOAC_ETC
					contractMstLom.put("loac_etc", StringUtil.convertEnterToBR((String) contractMstLom.get("loac_etc")));
					// dspt_resolt_mthd_det 분쟁해결방법상세 DSPT_RESOLT_DET
					contractMstLom.put("dspt_resolt_mthd_det", StringUtil.convertEnterToBR((String) contractMstLom.get("dspt_resolt_mthd_det")));
					// 메모
					contractMstLom.put("org_acpt_dlay_cause", StringUtil.convertEnterToBR((String) contractMstLom.get("org_acpt_dlay_cause")));
				}

				// 2. 연관계약정보
				if (detailList.get(1) != null && ((ArrayList) detailList.get(1)).size() > 0) {
					contractRelationLom = (ArrayList) detailList.get(1);
				}
				// 3. 승인자 정보(종료정보 中)
				if (detailList.get(2) != null && ((ArrayList) detailList.get(2)).size() > 0) {
					approveManLom = (ListOrderedMap) ((ArrayList) detailList.get(2)).get(0);
				}
				// 8. 종료정보
				if (detailList.get(3) != null && ((ArrayList) detailList.get(3)).size() > 0) {
					completionLom = (ListOrderedMap) ((ArrayList) detailList.get(3)).get(0);
				}
				// 9. 자동연장 이력
				if (detailList.get(4) != null && ((ArrayList) detailList.get(4)).size() > 0) {
					orgMngHistory = (ArrayList) detailList.get(4);
				}
				// 11.관련자 정보
				if (detailList.get(5) != null && ((List) detailList.get(5)).size() > 0) {
					authReqList = (ArrayList) detailList.get(5);

					for (int i = 0; i < authReqList.size(); i++) {
						ListOrderedMap authLom = (ListOrderedMap) authReqList.get(i);
						if (i > 0) {
							reqAuthInfo.append(", " + StringUtil.bvl((String) authLom.get("trgtman_nm"), "") + "/"
									+ StringUtil.bvl((String) authLom.get("trgtman_jikgup_nm"), "") + "/"
									+ StringUtil.bvl((String) authLom.get("trgtman_dept_nm"), "")); // display용
						} else {
							reqAuthInfo.append(
									StringUtil.bvl((String) authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String) authLom.get("trgtman_jikgup_nm"), "")
											+ "/" + StringUtil.bvl((String) authLom.get("trgtman_dept_nm"), ""));
						}
					}
				}
			}

			/*********************************************************
			 * ModelAndView
			 **********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + forwardURL);

			mav.addObject("contractReqLom", initLom);
			mav.addObject("contractMstLom", contractMstLom);
			mav.addObject("contractRelationLom", contractRelationLom);
			mav.addObject("approveManLom", approveManLom);
			mav.addObject("review", review);
			mav.addObject("approve", approve);
			mav.addObject("info", info);
			mav.addObject("payment_gbn", payment_gbn);
			mav.addObject("completionLom", completionLom);
			mav.addObject("orgMngHistory", orgMngHistory);
			mav.addObject("considerationSpecialList", special1List);
			mav.addObject("consultationSpecialList", special2List);
			mav.addObject("reqAuthInfo", reqAuthInfo);
			mav.addObject("authReqList", authReqList);
			mav.addObject("command", form);

			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("listContract() Exception !!");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("listContract() Throwable !!");
		}
	}

	/**
	 * 계약정보 전체조회 @param request, response @return ModelAndView @throws
	 */
	public ModelAndView listContract_back(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter
			 **********************************************************/
			String forwardURL = "";
			String conListGu = ""; // 팝업 페이지 구분값 입니다. 김재원 추가 했슴다~~
			CompletionForm form = null;
			CompletionVO vo = null;
			List resultList = new ArrayList();
			List tempList = null;
			ListOrderedMap initLom = null;
			ListOrderedMap tempLom = null;
			ArrayList lom = null;

			// *********************************************************
			// * Forward setting
			// **********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/manage/Completion_l.jsp";

			/// *********************************************************
			// * Form & VO Binding
			// **********************************************************/
			form = new CompletionForm();
			vo = new CompletionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			// *********************************************************
			// * roleCheck
			// **********************************************************/
			conListGu = form.getConListGu();
			form.setConListGu(conListGu);

			if ("Z1000".equals(conListGu)) {

				forwardURL = "/WEB-INF/jsp/clm/manage/Completion_l_p.jsp";

			}

			/*********************************************************
			 * Service
			 **********************************************************/
			// 연관 계약 관련하여 Cntrt_id 값을 받아서 condseq_id 값을 찾아 오는 내용입니다.
			// 여기서의 Key는 계약 아이디 입니다.
			if ("Z1000".equals(conListGu)) {

				tempList = completionService.listContract2(vo);
			} else {
				tempList = completionService.listContract(vo);
			}
			/*********************************************************
			 * Service
			 **********************************************************/
			if (tempList != null && tempList.size() > 0) {
				for (int i = 0; i < tempList.size(); i++) {
					tempLom = (ListOrderedMap) tempList.get(i);
					if (tempLom.get("depth_status").equals("C02685") || tempLom.get("depth_status").equals("C02682")
							|| tempLom.get("depth_status").equals("C02681") || tempLom.get("depth_status").equals("C02686")
							|| tempLom.get("depth_status").equals("C02687") || tempLom.get("depth_status").equals("C02688") || "Z1000".equals(conListGu)) {
						resultList.add(tempList.get(i));
					}
				}

				if (resultList.size() > 0) {
					initLom = (ListOrderedMap) resultList.get(0);
					form.setCnsdreq_id((String) initLom.get("cnsdreq_id"));
					form.setCntrt_id((String) initLom.get("cntrt_id"));
					form.setPrcs_depth((String) initLom.get("cntrt_status"));
					form.setPrcs_depth((String) initLom.get("prcs_depth"));
					form.setDepth_status((String) initLom.get("depth_status"));
					form.setReqman_id((String) initLom.get("reqman_id"));
					form.setReqman_nm((String) initLom.get("reqman_nm"));
					form.setCntrt_respman_id((String) initLom.get("cntrt_respman_id"));
					form.setCntrt_respman_nm((String) initLom.get("cntrt_respman_nm"));
				}
				lom = (ArrayList) resultList;
			}

			/*********************************************************
			 * ModelAndView
			 **********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + forwardURL);

			mav.addObject("contractLom", lom);
			mav.addObject("contractReqLom", initLom);
			mav.addObject("contractCommand", form);
			mav.addObject("command", form);

			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("listContract() Exception !!");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("listContract() Throwable !!");
		}
	}

	/**
	 * 계약정보상세조회 @param request, response @return ModelAndView @throws
	 */
	public ModelAndView detailContract(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter
			 **********************************************************/
			String forwardURL = "";
			String conListGu = ""; // 팝업 페이지 구분값 입니다. 김재원 추가 했슴다~~

			CompletionForm form = null;
			CompletionVO vo = null;
			List resultList = null;

			int td_cnt = 0;
			/*********************************************************
			 * Forward setting
			 **********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/manage/Completion_mst.jsp";

			/*********************************************************
			 * Form & VO Binding
			 **********************************************************/
			form = new CompletionForm();
			vo = new CompletionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * roleCheck
			 **********************************************************/
			conListGu = form.getConListGu();

			form.setConListGu(conListGu);

			if ("Z1000".equals(conListGu)) {
				forwardURL = "/WEB-INF/jsp/clm/manage/Completion_mst_p.jsp";
			}
			/*********************************************************
			 * Service
			 **********************************************************/
			resultList = completionService.detailContract(vo);

			ListOrderedMap lom = null;
			ArrayList contractMstLom = null;
			ArrayList contractExeLom = null;
			ArrayList contractSpclLom = null;
			ArrayList contractCnclsndlayLom = null;
			// 연관계약정보
			ArrayList contractRelationLom = null;

			// 특화정보
			ArrayList special1List = new ArrayList();
			ArrayList special2List = new ArrayList();

			int relationListSize = 0;

			if (resultList != null && resultList.size() > 0) {
				contractMstLom = (ArrayList) resultList.get(0);
				contractExeLom = (ArrayList) resultList.get(1);
				contractSpclLom = (ArrayList) resultList.get(2);
				contractCnclsndlayLom = (ArrayList) resultList.get(3);
				contractRelationLom = (ArrayList) resultList.get(4);

				if (contractRelationLom != null)
					relationListSize = contractRelationLom.size();

				if (contractMstLom != null && contractMstLom.size() > 0) {
					// master 정보
					lom = (ListOrderedMap) contractMstLom.get(0);
					// 마스터 정보 에 개행 문자 <BR> 변화
					// 기대효과 <BR>antcptnefctANTCPTNEFCT
					lom.put("antcptnefct", StringUtil.convertEnterToBR((String) lom.get("antcptnefct")));
					// 지불/수분조건 <BR>PAYMENT_COND
					lom.put("payment_cond", StringUtil.convertEnterToBR((String) lom.get("payment_cond")));
					// 기타주요사항 <BR>ETC_MAIN_CONT
					lom.put("etc_main_cont", StringUtil.convertEnterToBR((String) lom.get("etc_main_cont")));
					// CNTRT_CHGE_DEMND_CAUSE
					lom.put("cntrt_chge_demnd_cause", StringUtil.convertEnterToBR((String) lom.get("cntrt_chge_demnd_cause")));
					// 단가내역 요약cntrt_untprc_expl CNTRT_UNTPRC_EXPL
					lom.put("cntrt_untprc_expl", StringUtil.convertEnterToBR(StringUtil.bvl((String) lom.get("cntrt_untprc_expl"), "")));
					// 책임한도 oblgt_lmt OBLGT_LMT
					lom.put("oblgt_lmt", StringUtil.convertEnterToBR(StringUtil.bvl((String) lom.get("oblgt_lmt"), "")));
					// Specila Condition spcl_cond SPCL_COND
					lom.put("spcl_cond", StringUtil.convertEnterToBR(StringUtil.bvl((String) lom.get("spcl_cond"), "")));
					// loac_etc 준거법 상세 LOAC_ETC
					lom.put("loac_etc", StringUtil.convertEnterToBR(StringUtil.bvl((String) lom.get("loac_etc"), "")));
					// dspt_resolt_mthd_det 분쟁해결방법상세 DSPT_RESOLT_DET
					lom.put("dspt_resolt_mthd_det", StringUtil.convertEnterToBR(StringUtil.bvl((String) lom.get("dspt_resolt_mthd_det"), "")));

				} else {
					lom = new ListOrderedMap();
				}

				if (contractSpclLom != null && contractSpclLom.size() > 0) {
					// 특화정보 UI를 위한 정보
					for (int i = 0; i < contractSpclLom.size(); i++) {
						ListOrderedMap tempLom = (ListOrderedMap) contractSpclLom.get(i);
						if ("C04002".equals(tempLom.get("crtn_depth"))) {
							special1List.add(contractSpclLom.get(i));
						} else if ("C04005".equals(tempLom.get("crtn_depth"))) {
							special2List.add(contractSpclLom.get(i));
						}
					}
				} else {
					// td_cnt = 0;
				}
			}

			List authReqList = null;// 권한요청자 -관련자
			ConsultationVO consultationVo = new ConsultationVO();
			consultationVo.setCntrt_id(vo.getCntrt_id());
			authReqList = considerationService.listContractAuthreq(consultationVo);// 권한요청자
																					// -관련자

			StringBuffer reqAuthInfo = new StringBuffer();
			StringBuffer reqAuthFormInfo = new StringBuffer();
			StringBuffer reqAuthSvcInfo = new StringBuffer();

			StringBuffer reqAuthSeqno = new StringBuffer();
			StringBuffer reqAuthTrgrtman_id = new StringBuffer();
			StringBuffer reqAuthTrgrtman_nm = new StringBuffer();
			StringBuffer reqAuthTrgrtman_jikgup = new StringBuffer();
			StringBuffer reqAuthTrgrtman_dept = new StringBuffer();

			if (authReqList != null && authReqList.size() > 0) {
				for (int i = 0; i < authReqList.size(); i++) {
					ListOrderedMap authLom = (ListOrderedMap) authReqList.get(i);
					if (i > 0) {
						reqAuthInfo.append(", " + StringUtil.bvl((String) authLom.get("trgtman_nm"), "") + "/"
								+ StringUtil.bvl((String) authLom.get("trgtman_jikgup_nm"), "") + "/"
								+ StringUtil.bvl((String) authLom.get("trgtman_dept_nm"), "")); // display용
						reqAuthSvcInfo.append("!@#$" + authLom.get("demnd_seqno") + "|" + authLom.get("trgtman_id") + "|" + authLom.get("trgtman_nm") + "|"
								+ authLom.get("trgtman_jikgup_nm") + "|" + authLom.get("trgtman_dept_nm")); // 팝업과통신용
						reqAuthSeqno.append("," + authLom.get("demnd_seqno"));
						reqAuthTrgrtman_id.append("," + authLom.get("trgtman_id"));
						reqAuthTrgrtman_nm.append("," + authLom.get("trgtman_nm"));
						reqAuthTrgrtman_jikgup.append("," + authLom.get("trgtman_jikgup_nm"));
						reqAuthTrgrtman_dept.append("," + authLom.get("trgtman_dept_nm"));
					} else {
						reqAuthInfo.append(
								StringUtil.bvl((String) authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String) authLom.get("trgtman_jikgup_nm"), "")
										+ "/" + StringUtil.bvl((String) authLom.get("trgtman_dept_nm"), ""));
						reqAuthSvcInfo.append(authLom.get("demnd_seqno") + "|" + authLom.get("trgtman_id") + "|" + authLom.get("trgtman_nm") + "|"
								+ authLom.get("trgtman_jikgup_nm") + "|" + authLom.get("trgtman_dept_nm")); // 팝업과통신용
						reqAuthSeqno.append(authLom.get("demnd_seqno"));
						reqAuthTrgrtman_id.append(authLom.get("trgtman_id"));
						reqAuthTrgrtman_nm.append(authLom.get("trgtman_nm"));
						reqAuthTrgrtman_jikgup.append(authLom.get("trgtman_jikgup_nm"));
						reqAuthTrgrtman_dept.append(authLom.get("trgtman_dept_nm"));
					}
				}
				reqAuthFormInfo.append(reqAuthSeqno.toString() + "!@#$" + reqAuthTrgrtman_id.toString() + "!@#$" + reqAuthTrgrtman_nm.toString() + "!@#$"
						+ reqAuthTrgrtman_jikgup.toString() + "!@#$" + reqAuthTrgrtman_dept.toString());
			}
			/*********************************************************
			 * ModelAndView
			 **********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + forwardURL);

			// 특화정보 UI를 위한 정보
			mav.addObject("td_cnt", td_cnt);

			mav.addObject("contractMstLom", lom);
			mav.addObject("contractExeLom", contractExeLom);
			mav.addObject("contractSpclLom", contractSpclLom);
			mav.addObject("contractCnclsndlayLom", contractCnclsndlayLom);
			mav.addObject("contractRelationLom", contractRelationLom);
			// 특화정보
			mav.addObject("considerationSpecialList", special1List);
			mav.addObject("consultationSpecialList", special2List);
			mav.addObject("considerationSpecialSize", special1List.size());
			mav.addObject("consultationSpecialSize", special2List.size());
			if (reqAuthInfo != null && !"".equals(reqAuthInfo.toString())) {
				mav.addObject("reqAuthInfo", reqAuthInfo.toString());
				mav.addObject("reqAuthFormInfo", reqAuthFormInfo.toString());
				mav.addObject("reqAuthSvcInfo", reqAuthSvcInfo.toString());
			} else {
				mav.addObject("reqAuthInfo", "");
				mav.addObject("reqAuthFormInfo", "");
				mav.addObject("reqAuthSvcInfo", "");
			}

			mav.addObject("relationListSize", relationListSize);

			mav.addObject("command", form);

			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("detailContract() Exception !!");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("detailContract() Throwable !!");
		}
	}

	/**
	 * 이행정보 전체조회 @param request, response @return ModelAndView @throws
	 */
	public ModelAndView listExecutionHis(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter
			 **********************************************************/
			String forwardURL = "";
			String returnMessage = "";
			String conListGu = ""; // 팝업 페이지 구분값 입니다. 김재원 추가 했슴다~~

			CompletionForm form = null;
			CompletionVO vo = null;
			PageUtil pageUtil = null;
			List resultList = null;

			String payment_gbn = "";

			/*********************************************************
			 * Session Check
			 **********************************************************/
			HttpSession session = request.getSession(false);

			/*********************************************************
			 * Forward setting
			 **********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/manage/Completion_exe.jsp";

			/*********************************************************
			 * Form & VO Binding
			 **********************************************************/
			form = new CompletionForm();
			vo = new CompletionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * Page setting
			 **********************************************************/
			pageUtil = new PageUtil();

			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(), "1")));

			vo.setStart_index(pageUtil.getStartIndex()); // 현재 페이지의 첫번째 게시물번호
															// set
			vo.setEnd_index(pageUtil.getEndIndex()); // 현재 페이지의 마지막 게시물번호 set

			/*********************************************************
			 * roleCheck
			 **********************************************************/
			conListGu = form.getConListGu();

			form.setConListGu(conListGu);

			if ("Z1000".equals(conListGu)) {
				forwardURL = "/WEB-INF/jsp/clm/manage/Completion_exe_p.jsp";
			}
			/*********************************************************
			 * Service
			 **********************************************************/
			resultList = completionService.listExecutionHis(vo);

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap tmp = (ListOrderedMap) resultList.get(0);

				pageUtil.setTotalRow(((Integer) tmp.get("total_cnt")).intValue());
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();

				// 지불_구분 C02001:지불/수금, C02002:지불, C02003:수금, C02004:해당없음
				payment_gbn = (String) tmp.get("payment_gbn");

				// 메시지처리 - 정상적으로 조회되었습니다.
				if ((String) request.getAttribute("returnMessage") != null && ((String) request.getAttribute("returnMessage")).length() > 1) {
					returnMessage = (String) request.getAttribute("returnMessage");
				} else {
					// 메뉴 최초 진입 시 메세지 여부
					if ("Y".equals(vo.getDoSearch()))
						returnMessage = messageSource.getMessage("secfw.msg.succ.search", null, new RequestContext(request).getLocale());
				}
			} else {
				// 메시지처리 - 조회된 결과가 없습니다.
				if ((String) request.getAttribute("returnMessage") != null && ((String) request.getAttribute("returnMessage")).length() > 1) {
					returnMessage = (String) request.getAttribute("returnMessage");
				} else {
					// 메뉴 최초 진입 시 메세지 여부
					if ("Y".equals(vo.getDoSearch()))
						returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, new RequestContext(request).getLocale());
				}
			}

			ArrayList lom = (ArrayList) resultList;

			/*********************************************************
			 * ModelAndView
			 **********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + forwardURL);

			mav.addObject("executionList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("executionLom", lom);
			mav.addObject("payment_gbn", payment_gbn);
			mav.addObject("executionCommand", form);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("listExecutionHis() Exception !!");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("listExecutionHis() Throwable !!");
		}
	}

	/**
	 * 이행정보 상세조회 @param request, response @return ModelAndView @throws
	 */
	public ModelAndView listDetailExecutionHis(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter
			 **********************************************************/
			String forwardURL = "";
			String returnMessage = "";
			String conListGu = ""; // 팝업 페이지 구분값 입니다. 김재원 추가 했슴다~~

			CompletionForm form = null;
			CompletionVO vo = null;
			PageUtil pageUtil = null;
			List resultList = null;

			String payment_gbn = "";

			/*********************************************************
			 * Forward setting
			 **********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/manage/Completion_exe_d.jsp";

			/*********************************************************
			 * Form & VO Binding
			 **********************************************************/
			form = new CompletionForm();
			vo = new CompletionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * Page setting
			 **********************************************************/
			pageUtil = new PageUtil();

			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(), "1")));

			vo.setStart_index(pageUtil.getStartIndex()); // 현재 페이지의 첫번째 게시물번호
															// set
			vo.setEnd_index(pageUtil.getEndIndex()); // 현재 페이지의 마지막 게시물번호 set

			/*********************************************************
			 * roleCheck
			 **********************************************************/
			conListGu = form.getConListGu();
			form.setConListGu(conListGu);

			if ("Z1000".equals(conListGu)) {
				forwardURL = "/WEB-INF/jsp/clm/manage/Completion_exe_d_p.jsp";
			}

			/*********************************************************
			 * Service
			 **********************************************************/
			resultList = completionService.listExecutionHis(vo);

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap tmp = (ListOrderedMap) resultList.get(0);

				pageUtil.setTotalRow(((Integer) tmp.get("total_cnt")).intValue());
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();

				// 지불_구분 C02001:지불/수금, C02002:지불, C02003:수금, C02004:해당없음
				payment_gbn = (String) tmp.get("payment_gbn");

				// 메시지처리 - 정상적으로 조회되었습니다.
				if ((String) request.getAttribute("returnMessage") != null && ((String) request.getAttribute("returnMessage")).length() > 1) {
					returnMessage = (String) request.getAttribute("returnMessage");
				} else {
					// 메뉴 최초 진입 시 메세지 여부
					if ("Y".equals(vo.getDoSearch()))
						returnMessage = messageSource.getMessage("secfw.msg.succ.search", null, new RequestContext(request).getLocale());
				}
			} else {
				// 메시지처리 - 조회된 결과가 없습니다.
				if ((String) request.getAttribute("returnMessage") != null && ((String) request.getAttribute("returnMessage")).length() > 1) {
					returnMessage = (String) request.getAttribute("returnMessage");
				} else {
					// 메뉴 최초 진입 시 메세지 여부
					if ("Y".equals(vo.getDoSearch()))
						returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, new RequestContext(request).getLocale());
				}
			}

			ArrayList lom = (ArrayList) resultList;

			/*********************************************************
			 * ModelAndView
			 **********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);

			mav.addObject("executionList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("executionLom", lom);
			mav.addObject("payment_gbn", payment_gbn);
			mav.addObject("executionCommand", form);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("listExecutionHis() Exception !!");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("listExecutionHis() Throwable !!");
		}
	}

	/**
	 * 이행정보 상태값 수정 @param request, response @return ModelAndView @throws
	 */
	public ModelAndView modifyExecution(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter
			 **********************************************************/
			String forwardURL = "";
			String returnMessage = "";

			CompletionForm form = null;
			CompletionVO vo = null;
			/*********************************************************
			 * Parameter
			 **********************************************************/
			forwardURL = "/clm/manage/completion.do?method=listExecutionHis";

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			form = new CompletionForm();
			vo = new CompletionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * Service
			 **********************************************************/
			returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());
			completionService.modifyExecution(vo);

			if ("99".equals(form.getConListGu())) {
				return listContract(request, response);
			} else {
				String max_exec_num = completionService.getMaxExecNum(vo);
				/*********************************************************
				 * ModelAndView
				 **********************************************************/
				ModelAndView mav = new ModelAndView();

				mav.setViewName(forwardURL);
				this.getLogger().debug("forwardURL: " + forwardURL);

				mav.addObject("executionCommand", form);
				mav.addObject("command", form);
				mav.addObject("max_exec_num", max_exec_num);
				mav.addObject("returnMessage", returnMessage);

				return mav;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 종료관리 등록페이지 forward
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public ModelAndView forwardInsertCompletion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter
			 **********************************************************/
			String forwardURL = "";

			CompletionForm form = null;
			CompletionVO vo = null;

			List resultList = null;

			/*********************************************************
			 * Session Check
			 **********************************************************/
			HttpSession session = request.getSession(false);

			/*********************************************************
			 * Parameter setting
			 **********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/manage/Completion_cmplt.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			form = new CompletionForm();
			vo = new CompletionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * roleCheck
			 **********************************************************/
			String conListGu = "";

			conListGu = form.getConListGu();
			form.setConListGu(conListGu);

			if ("Z1000".equals(conListGu)) {
				forwardURL = "/WEB-INF/jsp/clm/manage/Completion_cmplt_p.jsp";
			}
			/*********************************************************
			 * Service
			 **********************************************************/
			if ("Z1000".equals(conListGu)) {
				resultList = completionService.detailCompletion2(vo);
			} else {
				resultList = completionService.detailCompletion(vo);
			}

			ListOrderedMap lom = null;
			ListOrderedMap roleLom = null;
			ArrayList completionLom = null;
			ArrayList cntrtStatusLom = null;
			ArrayList cntrtRoleLom = null;
			// 승인자
			ListOrderedMap approveManLom = new ListOrderedMap();
			ArrayList approveManList = new ArrayList();

			String userId = vo.getSession_user_id();
			if (resultList != null && resultList.size() > 0) {
				completionLom = (ArrayList) resultList.get(0);
				// 변경상태 List
				cntrtStatusLom = (ArrayList) resultList.get(1);

				// 권한
				cntrtRoleLom = (ArrayList) resultList.get(2);

				// 승인자
				approveManList = (ArrayList) resultList.get(3);
				if (approveManList != null && approveManList.size() > 0) {
					approveManLom = (ListOrderedMap) approveManList.get(0);
				}

				if (cntrtRoleLom != null && cntrtRoleLom.size() > 0) {
					roleLom = (ListOrderedMap) cntrtRoleLom.get(0);
					roleLom.put("cfrmYN", true);
				} else {
					roleLom = new ListOrderedMap();
					roleLom.put("session_user_id", vo.getSession_user_id());
					roleLom.put("cfrmYN", false);
				}

				// 종료 정보
				lom = (ListOrderedMap) completionLom.get(0);
			}
			/*********************************************************
			 * ModelAndView
			 **********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + forwardURL);

			mav.addObject("completionLom", lom);
			mav.addObject("cntrtStatusLom", cntrtStatusLom);
			mav.addObject("completionRoleLom", roleLom);
			mav.addObject("approveManLom", approveManLom);

			mav.addObject("compeltionCommand", form);
			mav.addObject("command", form);

			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("forwardInsertCompletion() Exception !!");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("forwardInsertCompletion() Throwable !!");
		}
	}

	/**
	 * 종료정보 임시저장 @param request, response @return ModelAndView @throws
	 */
	public ModelAndView insertTempCompletion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter
			 **********************************************************/
			String forwardURL = "";
			String returnMessage = "";

			CompletionForm form = null;
			CompletionVO vo = null;

			/*********************************************************
			 * Session Check
			 **********************************************************/
			HttpSession session = request.getSession(false);

			/*********************************************************
			 * Parameter
			 **********************************************************/
			forwardURL = "/clm/manage/completion.do?method=forwardInsertCompletion";

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			form = new CompletionForm();
			vo = new CompletionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			vo.setCntrt_chge_plndday((StringUtil.bvl(form.getCntrt_chge_plndday(), "")).replace("-", ""));
			vo.setCntrt_chge_demndday((StringUtil.bvl(form.getCntrt_chge_demndday(), "")).replace("-", ""));

			// 처리기능별 상태코드 설정
			// CNTRT_STATUS : C02402(체결), PRCS_DEPTH : C02505(계약종료)
			vo.setPrcs_depth("C02505");

			/*********************************************************
			 * Service
			 **********************************************************/
			returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());

			request.setAttribute("returnMessage", returnMessage);
			completionService.insertTempCompletion(vo);

			if ("99".equals(form.getConListGu())) {
				return listContract(request, response);
			} else {
				/*********************************************************
				 * ModelAndView
				 **********************************************************/
				ModelAndView mav = new ModelAndView();

				mav.setViewName(forwardURL);
				this.getLogger().debug("forwardURL: " + forwardURL);

				mav.addObject("executionCommand", form);
				mav.addObject("command", form);
				mav.addObject("returnMessage", returnMessage);

				return mav;//
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("insertCompletion() Exception");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("insertCompletion() Throwable");
		}
	}

	/**
	 * 종료정보 신규저장 @param request, response @return ModelAndView @throws
	 */
	public ModelAndView insertCompletion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter
			 **********************************************************/
			String forwardURL = "";
			String returnMessage = "";
			String returnYN = "Y";

			CompletionForm form = null;
			CompletionVO vo = null;

			List resultList = null;

			/*********************************************************
			 * Session Check
			 **********************************************************/
			HttpSession session = request.getSession(false);

			/*********************************************************
			 * Parameter
			 **********************************************************/
			forwardURL = "/clm/manage/completion.do?method=forwardInsertCompletion";

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			form = new CompletionForm();
			vo = new CompletionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			vo.setCntrt_chge_plndday(form.getCntrt_chge_plndday().replace("-", ""));
			vo.setCntrt_chge_demndday(form.getCntrt_chge_demndday().replace("-", ""));
			vo.setCntrtperiod_endday(vo.getCntrtperiod_endday().replace("-", ""));

			// 처리기능별 상태코드 설정
			// CNTRT_STATUS : C02402(체결), PRCS_DEPTH : C02505(계약종료), C02682(결재중)
			vo.setPrcs_depth("C02505");
			vo.setDepth_status("C02682");

			// 계약자동연장일 경우 추가(2012.07.05) => CNTRT_STATUS : C02402(체결),
			// PRCS_DEPTH : C02505(계약종료), C02685(확인중)
			if ("C02402".equals(vo.getCntrt_status())) {
				vo.setDepth_status("C02685");
			}

			// 계약관리 검토의뢰 진행상태코드 설정
			// PRGRS_STATUS : C04221(= 타 계약 단계상태("DEPTH_STATUS")가 높을경우...)
			resultList = completionService.listContract(vo);

			if (resultList != null && resultList.size() > 0) {
				if (resultList.size() == 1) {
					// 계약이 1건인 경우...
					// 계약자동연장일 경우 추가(2012.07.05)
					if ("C02402".equals(vo.getCntrt_status())) {
						vo.setPrgrs_status("C04222");// 확인중
					} else {
						vo.setPrgrs_status("C04221");// 결재중
					}
				} else {
					for (int i = 0; i < resultList.size(); i++) {
						ListOrderedMap testLom = (ListOrderedMap) resultList.get(i);

						String depth_status = (String) testLom.get("depth_status");
						String cntrt_id = (String) testLom.get("cntrt_id");

						if (!(vo.getCntrt_id().equals(cntrt_id))) {
							if ("C02662".equals(depth_status) || "C02681".equals(depth_status) || "C02682".equals(depth_status)) {
								// 검토의뢰의 진행상태코드는 모든계약건이 "확인중" 상태 이상일 경우
								// 업데이트(C04221 결재중)가능하다.
								break;
							}
							// 모든 계약건이 결재중상태 이상이므로 의뢰의 상태 변경...
							// 계약자동연장일 경우 추가(2012.07.05)
							if ("C02402".equals(vo.getCntrt_status())) {
								vo.setPrgrs_status("C04222");// 확인중
							} else {
								vo.setPrgrs_status("C04221");// 결재중
							}
						}
					} // for
				}
			}

			/*********************************************************
			 * Service
			 **********************************************************/
			// 결재

			int result = 0;

			if ("Y".equals(returnYN)) {
				completionService.insertCompletion(vo);
				returnMessage = messageSource.getMessage("las.page.field.contractManager.procEnd", null, new RequestContext(request).getLocale());

				request.setAttribute("returnMessage", returnMessage);
			}
			/*********************************************************
			 * Massage
			 **********************************************************/
			// 메시지처리 - 수정되었습니다.
			if ("Y".equals(returnYN)) {
				returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());
			} else {
				returnMessage = messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale());
			}

			if ("99".equals(form.getConListGu()) || "98".equals(form.getConListGu())) {
				return listContract(request, response);
			} else {
				/*********************************************************
				 * ModelAndView
				 **********************************************************/
				ModelAndView mav = new ModelAndView();

				mav.setViewName(forwardURL);
				this.getLogger().debug("forwardURL: " + forwardURL);

				mav.addObject("executionCommand", form);
				mav.addObject("command", form);
				mav.addObject("returnMessage", returnMessage);

				return mav;//
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("insertCompletion() Exception");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("insertCompletion() Throwable");
		}
	}

	/**
	 * 종료정보 종료확인 @param request, response @return ModelAndView @throws
	 */
	public ModelAndView insertCfrmCompletion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter
			 **********************************************************/
			String forwardURL = "";
			String returnMessage = "";

			CompletionForm form = null;
			CompletionVO vo = null;

			List resultList = null;

			String status = "";

			/*********************************************************
			 * Session Check
			 **********************************************************/
			HttpSession session = request.getSession(false);

			/*********************************************************
			 * Parameter
			 **********************************************************/
			forwardURL = "/clm/manage/myApproval.do?method=listMyApproval";

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			form = new CompletionForm();
			vo = new CompletionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			// 단계상태
			status = form.getCntrt_status();

			// 처리기능별 상태코드 설정
			// CNTRT_STATUS : C02403(변경), PRCS_DEPTH : C02404(만료), C02405(해지)
			vo.setCntrt_status(form.getCntrt_status());
			vo.setPrcs_depth("C02505");
			// DEPTH_STATUS : C02687(변경), C02686(만료), C02688(해지)
			if ("C02403".equals(status)) {
				vo.setDepth_status("C02687");
			} else if ("C02404".equals(status)) {
				vo.setDepth_status("C02686");
			} else if ("C02405".equals(status)) {
				vo.setDepth_status("C02688");
			}

			// 계약관리 검토의뢰 진행상태코드 설정
			// PRGRS_STATUS : C04221(= 타 계약 단계상태("DEPTH_STATUS")가 높을경우...)
			resultList = completionService.listContract(vo);

			if (resultList != null && resultList.size() > 0) {
				if (resultList.size() == 1) {
					// 계약이 1건인 경우...
					vo.setPrgrs_status("C04223");
				} else {
					for (int i = 0; i < resultList.size(); i++) {
						ListOrderedMap testLom = (ListOrderedMap) resultList.get(i);

						String depth_status = (String) testLom.get("depth_status");
						String cntrt_id = (String) testLom.get("cntrt_id");

						if (!(vo.getCntrt_id().equals(cntrt_id))) {
							if ("C02662".equals(depth_status) || "C02681".equals(depth_status) || "C02682".equals(depth_status)
									|| "C02685".equals(depth_status)) {
								// 검토의뢰의 진행상태코드는 모든계약건이 "확인중" 상태 이상일 경우
								// 업데이트(C04221 결재중)가능하다.
								break;
							}
							// 모든 계약건이 종료품의 승인(싱글)확인중상태 이상이므로 의뢰의 상태 변경...
							vo.setPrgrs_status("C04223");
						}
					} // for
				}
			}

			/*********************************************************
			 * Service
			 **********************************************************/
			returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());
			completionService.insertCfrmCompletion(vo);

			/*********************************************************
			 * ModelAndView
			 **********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + forwardURL);

			mav.addObject("executionCommand", form);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;//

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("insertCfrmCompletion() Exception");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("insertCfrmCompletion() Throwable");
		}
	}

	/**
	 * 역할-권한을 조회한다.
	 * 
	 * @param request
	 * @param response
	 * @return JSON Type
	 * @throws Exception
	 */
	public void listContractRole(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			CompletionForm form = new CompletionForm();
			CompletionVO vo = new CompletionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			/*********************************************************
			 * 내역 등록
			 **********************************************************/
			JSONArray jsonArray = completionService.listContractRole(vo);

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();

		} catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale()));

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale()));

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		}

	}

	public ModelAndView forwardPreviewPop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);
			String forwardURL = "/WEB-INF/jsp/clm/manage/Completion_preview_pop.jsp";

			/*********************************************************
			 * Forward setting
			 **********************************************************/
			CompletionForm form = new CompletionForm();
			CompletionVO vo = new CompletionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			String contents = request.getParameter("contents");
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + forwardURL);

			mav.addObject("command", form);
			mav.addObject("content", contents.toString());

			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 미리보기 정보
	 * 
	 * @param request
	 * @param response
	 * @return JSON Type
	 * @throws Exception
	 */
	public ModelAndView detailPreviewPop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter
			 **********************************************************/
			String forwardURL = "";
			String returnMessage = "";

			CompletionForm form = null;
			CompletionVO vo = null;
			List resultList = null;
			List resultExecList = null;
			List resultCmpltList = null;
			List resultHisList = null;
			List resultAttachList = null;

			String payment_gbn = "";

			String strUrl = (request.getRequestURL()).toString();
			String strAttachUrl = strUrl.substring(0, strUrl.indexOf("/", 7) + 1) + "clms/common/clmsfile.do?method=doClmsDownload&file_id=";

			/*********************************************************
			 * Session Check
			 **********************************************************/
			HttpSession session = request.getSession(false);

			/*********************************************************
			 * Forward setting
			 **********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/manage/Completion_preview_mst.jsp";

			/*********************************************************
			 * Form & VO Binding
			 **********************************************************/
			form = new CompletionForm();
			vo = new CompletionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * Page setting
			 **********************************************************/
			PageUtil pageUtil = new PageUtil();

			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(), "1")));

			vo.setStart_index(pageUtil.getStartIndex()); // 현재 페이지의 첫번째 게시물번호
															// set
			vo.setEnd_index(pageUtil.getEndIndex()); // 현재 페이지의 마지막 게시물번호 set

			/*********************************************************
			 * Service
			 **********************************************************/
			// 기본 정보
			resultList = completionService.detailContract(vo);
			// 이행정보
			resultExecList = completionService.listExecutionHis(vo);
			// 종료정보
			resultCmpltList = completionService.detailCompletion(vo);
			// 이력정보
			resultHisList = completionService.listHisCompletion(vo);
			// 첨부파일
			resultAttachList = completionService.listCompletionAttachInfo(vo);

			// 기본정보*
			ListOrderedMap lom = null;
			ListOrderedMap td_lom = null;
			ArrayList contractMstLom = null;
			ArrayList contractExeLom = null;
			ArrayList contractSpclLom = null;
			ArrayList contractCnclsndlayLom = null;
			// 연관계약정보
			ArrayList contractRelationLom = null;
			int relationListSize = 0;

			// 특화정보
			ArrayList special1List = new ArrayList();
			ArrayList special2List = new ArrayList();

			if (resultList != null && resultList.size() > 0) {
				contractMstLom = (ArrayList) resultList.get(0);
				contractExeLom = (ArrayList) resultList.get(1);
				contractSpclLom = (ArrayList) resultList.get(2);
				contractCnclsndlayLom = (ArrayList) resultList.get(3);
				contractRelationLom = (ArrayList) resultList.get(4);

				if (contractRelationLom != null)
					relationListSize = contractRelationLom.size();

				if (contractMstLom != null && contractMstLom.size() > 0) {
					// master 정보
					lom = (ListOrderedMap) contractMstLom.get(0);

				} else {
					lom = new ListOrderedMap();
				}

				if (contractSpclLom != null && contractSpclLom.size() > 0) {
					// 특화정보 UI를 위한 정보
					for (int i = 0; i < contractSpclLom.size(); i++) {
						ListOrderedMap tempLom = (ListOrderedMap) contractSpclLom.get(i);
						if ("C04002".equals(tempLom.get("crtn_depth"))) {
							special1List.add(contractSpclLom.get(i));
						} else if ("C04005".equals(tempLom.get("crtn_depth"))) {
							special2List.add(contractSpclLom.get(i));
						}
					}
				}
			}
			// 이행정보*
			if (resultExecList != null && resultExecList.size() > 0) {
				ListOrderedMap resultExecListTmp = (ListOrderedMap) resultExecList.get(0);

				pageUtil.setTotalRow(((Integer) resultExecListTmp.get("total_cnt")).intValue());
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();

				// 지불_구분 C02001:지불/수금, C02002:지불, C02003:수금, C02004:해당없음
				payment_gbn = (String) resultExecListTmp.get("payment_gbn");

			}

			ArrayList resultExecListLom = (ArrayList) resultExecList;

			// 종료정보*
			ArrayList completionList = null;
			ListOrderedMap completionLom = null;

			ListOrderedMap approveManLom = new ListOrderedMap();
			ArrayList approveManList = new ArrayList();

			if (resultCmpltList != null && resultCmpltList.size() > 0) {
				completionList = (ArrayList) resultCmpltList.get(0);
				approveManList = (ArrayList) resultCmpltList.get(3);

				// 종료 정보
				completionLom = (ListOrderedMap) completionList.get(0);
				if (approveManList != null && approveManList.size() > 0) {
					approveManLom = (ListOrderedMap) approveManList.get(0);
				}
			}

			// 이력정보*
			ArrayList review = null;
			ArrayList approve = null;
			ArrayList sign = null;
			ArrayList info = null;

			if (resultHisList != null && resultHisList.size() > 0) {
				review = (ArrayList) resultHisList.get(0);
				approve = (ArrayList) resultHisList.get(1);
				sign = (ArrayList) resultHisList.get(2);
				info = (ArrayList) resultHisList.get(3);
			}
			// 최종 건을 구하기 위한 maxrm 변수 세팅
			int maxrm = 0;
			if (review != null) {
				ListOrderedMap _lom = new ListOrderedMap();
				for (int i = 0; i < review.size(); i++) {
					_lom = (ListOrderedMap) review.get(i);

					if (maxrm < Integer.parseInt(String.valueOf(_lom.get("rm")))) {
						maxrm = Integer.parseInt(String.valueOf(_lom.get("rm")));
					}
				}
			}
			// 첨부파일*
			ArrayList resultAttachArrList = new ArrayList();
			resultAttachArrList = (ArrayList) resultAttachList;

			/*********************************************************
			 * ModelAndView
			 **********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + forwardURL);

			// 기본 정보
			mav.addObject("contractMstLom", lom);
			mav.addObject("contractExeLom", contractExeLom);
			mav.addObject("contractSpclLom", contractSpclLom);
			mav.addObject("contractCnclsndlayLom", contractCnclsndlayLom);
			mav.addObject("contractRelationLom", contractRelationLom);
			mav.addObject("relationListSize", relationListSize);
			// 특화정보
			mav.addObject("considerationSpecialList", special1List);
			mav.addObject("consultationSpecialList", special2List);
			mav.addObject("considerationSpecialSize", special1List.size());
			mav.addObject("consultationSpecialSize", special2List.size());
			// 이행정보
			mav.addObject("executionLom", resultExecListLom);
			mav.addObject("payment_gbn", payment_gbn);
			mav.addObject("pageUtil", pageUtil);
			// 종료정보
			mav.addObject("completionLom", completionLom);
			mav.addObject("approveManLom", approveManLom);
			// 이력정보
			mav.addObject("maxrm", maxrm);
			mav.addObject("review", review);
			mav.addObject("approve", approve);
			mav.addObject("sign", sign);
			mav.addObject("info", info);
			// 첨부파일정보
			mav.addObject("resultAttachArrList", resultAttachArrList);
			mav.addObject("attachinfo", strAttachUrl);

			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/* 결재관련추가 : 2011.10.26 팝업아닌경우를 위해 신규 추가 */
	// 2011.10.12 심주완추가 결재상신관련
	public void makeApprovalHtmlDirect(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);
			String sysCd = (String) session.getAttribute("secfw.session.sys_cd");
			String userId = (String) session.getAttribute("secfw.session.user_id");
			String authCompCd = (String) session.getAttribute("secfw.session.auth_comp_cd");
			String compCd = (String) session.getAttribute("secfw.session.comp_cd");
			String strUrl = (request.getRequestURL()).toString();
			String strAttachUrl = strUrl.substring(0, strUrl.indexOf("/", 7) + 1) + "clms/common/clmsfile.do?method=doClmsDownload&file_id=";
			ApprovalVO apprVo = new ApprovalVO();
			List resultList = null;
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			CompletionForm form = new CompletionForm();
			CompletionVO vo = new CompletionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			String contents = request.getParameter("contents");

			/** 1. 결재내역 정보 **/
			// 모듈아이디 및 misId 세팅 -> (각 시스템/업무별로 개별 Key값을 입력)
			String moduleId = StringUtil.bvl(sysCd, "LAS");
			String misId = "";
			misId = EsbUtil.generateMisId("APPR");
			apprVo.setModule_id("LAS");
			apprVo.setSys_cd(moduleId);
			apprVo.setMis_id(misId);

			// 로케일, 인코딩 설정, TIME_ZONE
			String locale_info = "en_US.UTF-8";

			apprVo.setLocale_info(locale_info);
			apprVo.setTime_zone(StringUtil.bvl((String) session.getAttribute("EP_TIMEZONE"), "GMT+0"));

			// STATUS 값 설정 - Default "0"
			apprVo.setStatus("0");

			// BODY Type
			String bodyType = "1";
			apprVo.setBody_type("1");

			List cntrtList = null;
			ListOrderedMap cntrtLom = null;
			String sCntrtTitle = ""; // 종료품의 계약서명([계약 + 상태코드 + 상신] + 계약서명) :
										// [계약변경상신]무선_삼성에스디에스(주)_본계약_신규계약
			cntrtList = completionService.detailCntrtTitle(vo);
			if (cntrtList != null && cntrtList.size() > 0) {
				cntrtLom = new ListOrderedMap();
				cntrtLom = (ListOrderedMap) cntrtList.get(0);
				sCntrtTitle = (String) cntrtLom.get("cntrt_nm");
			}
			apprVo.setTitle(sCntrtTitle);
			// Approval Post Method
			apprVo.setMethod("postAppContStatus");

			String contentHtml = contents.toString();
			contentHtml = StringUtil.replace(contentHtml, "<html>", "<html xmlns='http://www.w3.org/1999/xhtml'>");
			contentHtml = StringUtil.replace(contentHtml, "<HTML>", "<html xmlns='http://www.w3.org/1999/xhtml'>");
			contentHtml = StringUtil.replace(contentHtml, "</HTML>", "</html>");
			apprVo.setBody(contentHtml);

			// 로케일, 인코딩 설정, TIME_ZONE
			String sessionLocale = StringUtil.bvl((String) session.getAttribute("EP_LOCALE"), "en_US.UTF-8");
			String approvalEncoding = sessionLocale.substring(sessionLocale.indexOf(".") + 1);

			String approvalDrafterID = userId;
			String approvalDrafterUserPath = "";
			String approvalDrafterUserRight = "";

			// 2011.10.20 심주완추가 계약의뢰상신시 사용, 승인자
			String approvalAuthorizerID = StringUtil.bvl((String) request.getAttribute("approval_auth_id"), vo.getApprovalman_id());
			// 2011.10.13 심주완추가 계약체결품의시 사용
			String approvalAuthorizerUserPath = "";
			String approvalAuthorizerUserRight = "";

			// 상신자-요청자 정보 조회
			Locale lc = (Locale) localeResolver.resolveLocale(request);

			if (approvalDrafterID != null && !"".equals(approvalDrafterID)) {

				// 2012-07-23 삼성전자 외 타사임직원(삼성그룹내) 결재 사용 허용여부 확인
				HashMap hm = new HashMap();
				hm.put("user_id", approvalDrafterID);
				List exceptWsvoList = esbApprovalService.listExceptWsvoList(hm);
				if (exceptWsvoList != null && exceptWsvoList.size() > 0) { // 결재
																			// ESB조회
																			// 예외자가
																			// 존재(ESB
																			// 조회안함)
					ListOrderedMap lom = (ListOrderedMap) exceptWsvoList.get(0);

					// 기안자정보 아이디 | 이름 | 직급코드 | 직급명 |
					approvalDrafterUserPath = "0|" + approvalDrafterID + "|" + StringUtil.bvl((String) lom.get("user_nm"), "") + "|"
							+ StringUtil.bvl((String) lom.get("jikgup_cd"), "") + "|" + StringUtil.bvl((String) lom.get("jikgup_nm"), "") + "|";
					// 기안자정보 계속 부서코드 | 부서명 | 회사코드 | 회사명 |
					approvalDrafterUserPath = approvalDrafterUserPath + StringUtil.bvl((String) lom.get("dept_cd"), "") + "|"
							+ StringUtil.bvl((String) lom.get("dept_nm"), "") + "|" + StringUtil.bvl((String) lom.get("comp_cd"), "") + "|"
							+ StringUtil.bvl((String) lom.get("comp_nm"), "") + "|";
					// 기안자정보계속 총괄코드 | 총괄명 | 메일주소
					// 총괄명 메시지 처리 - 없음
					approvalDrafterUserPath = approvalDrafterUserPath + StringUtil.bvl((String) lom.get("division_cd"), "A") + "|"
							+ StringUtil.bvl((String) lom.get("division_nm"), (String) messageSource
									.getMessage("clm.page.field.completion.makeApprovalHtmlDirect01", null, new RequestContext(request).getLocale()))
							+ "|" + StringUtil.bvl((String) lom.get("email"), "");

					approvalDrafterUserRight = approvalDrafterID + "|-1|-1|-1";

				} else {
					Vector drafterUserVector = null;

					// !@# ESB 인터페이스 변경(authCompCd 추가) 2013.04.25
					drafterUserVector = esbOrgService.userSearchByEpid(approvalDrafterID, lc);

					if (drafterUserVector != null && drafterUserVector.size() > 0) {
						Hashtable ht = (Hashtable) drafterUserVector.get(0);
						// 기안자정보 아이디 | 이름 | 직급코드 | 직급명 |
						approvalDrafterUserPath = "0|" + approvalDrafterID + "|" + ht.get("cn") + "|" + ht.get("eptitlenumber") + "|" + ht.get("title") + "|";
						// 기안자정보 계속 부서코드 | 부서명 | 회사코드 | 회사명 |
						approvalDrafterUserPath = approvalDrafterUserPath + ht.get("departmentnumber") + "|" + ht.get("department") + "|"
								+ ht.get("eporganizationnumber") + "|" + ht.get("o") + "|";
						// 기안자정보계속 총괄코드 | 총괄명 | 메일주소
						approvalDrafterUserPath = approvalDrafterUserPath + StringUtil.bvl(ht.get("epsuborgcode"), "A")
								+ "|" + StringUtil.bvl(ht.get("epsuborgname"), (String) messageSource
										.getMessage("clm.page.field.completion.makeApprovalHtmlDirect01", null, new RequestContext(request).getLocale()))
								+ "|" + ht.get("mail");
						// approvalDrafterUserPath = approvalDrafterUserPath +
						// ht.get("mail");

						approvalDrafterUserRight = approvalDrafterID + "|-1|-1|-1";
					}
				}
			}

			// 2011.10.20 심주완추가-승인자-결재자정보조회때문에 추가
			if (approvalAuthorizerID != null && !"".equals(approvalAuthorizerID)) {

				Vector authorizerUserVector = null;
				// !@# ESB 인터페이스 변경(authCompCd 추가) 2013.04.25
				authorizerUserVector = esbOrgService.userSearchByEpid(approvalAuthorizerID, lc);

				if (authorizerUserVector != null && authorizerUserVector.size() > 0) {
					Hashtable ht = (Hashtable) authorizerUserVector.get(0);
					approvalAuthorizerUserPath = "1|" + approvalAuthorizerID + "|" + ht.get("cn") + "|" + ht.get("eptitlenumber") + "|" + ht.get("title") + "|";
					approvalAuthorizerUserPath = approvalAuthorizerUserPath + ht.get("departmentnumber") + "|" + ht.get("department") + "|"
							+ ht.get("eporganizationnumber") + "|" + ht.get("o") + "|";
					approvalAuthorizerUserPath = approvalAuthorizerUserPath + StringUtil.bvl(ht.get("epsuborgcode"), "A")
							+ "|" + StringUtil.bvl(ht.get("epsuborgname"), (String) messageSource
									.getMessage("clm.page.field.completion.makeApprovalHtmlDirect01", null, new RequestContext(request).getLocale()))
							+ "|" + ht.get("mail");
					approvalAuthorizerUserRight = approvalAuthorizerID + "|-1|-1|-1";
				}
			}

			/** 2. 결재경로 정보 **/
			String[] approvalRoutes = new String[2];
			approvalRoutes[0] = approvalDrafterUserPath;
			approvalRoutes[1] = approvalAuthorizerUserPath;
			String[] approvalRouteRights = new String[2];
			approvalRouteRights[0] = approvalDrafterUserRight;
			approvalRouteRights[1] = approvalAuthorizerUserRight;

			String[] activitys = null; // 설정구분
			String[] actionTypes = null; // 처리구분
			String[] userIds = null; // EPID
			String[] userNms = null;
			String[] jikgupCds = null;
			String[] jikgupNms = null;
			String[] deptCds = null;
			String[] deptNms = null;
			String[] compCds = null;
			String[] compNms = null;
			String[] grpCds = null;
			String[] grpNms = null;
			String[] mailAddress = null;
			String[] arbitrarys = null; // 전결권한
			String[] bodyModifys = null; // 본문수정권한
			String[] routeModifys = null; // 경로변경 권한

			if (approvalRoutes != null && approvalRoutes.length > 0) {

				activitys = new String[approvalRoutes.length];
				actionTypes = new String[approvalRoutes.length];
				userIds = new String[approvalRoutes.length];
				userNms = new String[approvalRoutes.length];
				jikgupCds = new String[approvalRoutes.length];
				jikgupNms = new String[approvalRoutes.length];
				deptCds = new String[approvalRoutes.length];
				deptNms = new String[approvalRoutes.length];
				compCds = new String[approvalRoutes.length];
				compNms = new String[approvalRoutes.length];
				grpCds = new String[approvalRoutes.length];
				grpNms = new String[approvalRoutes.length];
				mailAddress = new String[approvalRoutes.length];

				arbitrarys = new String[approvalRoutes.length];
				bodyModifys = new String[approvalRoutes.length];
				routeModifys = new String[approvalRoutes.length];

				for (int i = 0; i < approvalRoutes.length; i++) {
					String[] approvalRoute = StringUtil.token(approvalRoutes[i], "|");
					String[] approvalRouteRight = StringUtil.token(approvalRouteRights[i], "|");

					activitys[i] = approvalRoute[0];
					if(i==0){
						actionTypes[i] = "0";
					}else{
						actionTypes[i] = "1";
					}
					
					userIds[i] = approvalRoute[1];
					userNms[i] = approvalRoute[2];
					jikgupCds[i] = approvalRoute[3];
					jikgupNms[i] = approvalRoute[4];
					deptCds[i] = approvalRoute[5];
					deptNms[i] = approvalRoute[6];
					compCds[i] = approvalRoute[7];
					compNms[i] = approvalRoute[8];
					grpCds[i] = approvalRoute[9];
					grpNms[i] = approvalRoute[10];
					mailAddress[i] = (approvalRoute.length >= 12 ? approvalRoute[11] : "fernando.c@partner.samsung.com"); // testing termination bug 2023-12-21 Fernando

					routeModifys[i] = approvalRouteRight[1];
					bodyModifys[i] = approvalRouteRight[2];
					arbitrarys[i] = approvalRouteRight[3];

				}
			}
			apprVo.setRef_key(vo.getCntrt_id()); //
			apprVo.setApprvl_clsfcn("C03003"); //
			apprVo.setMethod("postAppContStatus");
			apprVo.setActivitys(activitys);
			apprVo.setAction_types(actionTypes);
			apprVo.setUser_ids(userIds);
			apprVo.setUser_names(userNms);
			apprVo.setDuty_codes(jikgupCds);
			apprVo.setDutys(jikgupNms);
			apprVo.setDept_codes(deptCds);
			apprVo.setDept_names(deptNms);
			apprVo.setComp_codes(compCds);
			apprVo.setComp_names(compNms);
			apprVo.setGroup_codes(grpCds);
			apprVo.setGroup_names(grpNms);
			apprVo.setMail_addresss(mailAddress);

			apprVo.setRoute_modifys(routeModifys);
			apprVo.setBody_modifys(bodyModifys);
			apprVo.setArbitrarys(arbitrarys);

			// 결재상신 의견
			String[] opinions = new String[2];
			for (int i = 0; i < approvalRoutes.length; i++) {
				opinions[i] = "";
			}
			// 메시지 처리 - 계약종료입니다.
			opinions[0] = (String) messageSource.getMessage("clm.page.field.completion.makeApprovalHtmlDirect02", null,
					new RequestContext(request).getLocale());
			apprVo.setOpinions(opinions);
			// 예약 상신 시간
			String[] reserveds = new String[2];
			for (int i = 0; i < approvalRoutes.length; i++) {
				reserveds[i] = "";
			}
			reserveds[0] = "";
			apprVo.setReserveds(reserveds);
			
			/* 3. Attachments 정보 
			 * fileInfos10 이 종료상신의 첨부 파일 정보를 가지고 있음
			 * **/
			
//			String revisedFileInfo="";
//			if(StringUtils.isNotEmpty(form.getFileInfos10())){
//				String fileInfos[]=form.getFileInfos10().split("|");
//				for(String fileInfo:fileInfos){
//					 //* fileInfos형식 : fileId * orgFileNm * fileInfo * filePath * fileSize * dnCnt * fileStatus |
//					//Stringseq_no*로컬파일명*파일정보*서버파일명(경로+실제저장파일명)*크기|
//					if(StringUtils.isNotEmpty(fileInfo)){
//						String[] details=fileInfo.split("*");
//						
//						
//						
//					}
//				}
//				apprVo.setFileInfos(revisedFileInfo);
//			}
//			
			
			
			/*********************************************************
			 * 결재상신
			 **********************************************************/
			boolean isSuccess = false;
			isSuccess = esbApprovalService.submit(apprVo);
			String result = "N";
			if (isSuccess) {
				result = "Y";
			}
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.succ.submit", null, new RequestContext(request).getLocale()));
			jo.put("returnValue", result);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("makeApprovalHtmlDirect Exception !!");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("makeApprovalHtmlDirect Exception !!");
		}

	}

	/**
	 * 계약종료 상세화면 인쇄
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardPrintPop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);

			String forwardURL = "/WEB-INF/jsp/clm/report/CompletionPrint.jsp";
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			form.setReport_url(propertyService.getProperty("secfw.url.domain"));

			ModelAndView mav = new ModelAndView();
			mav.addObject("command", form);
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + forwardURL);

			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/*
	 * 승인이력에서 종료 History 팝업띄위기 위한 작업
	 */
	public ModelAndView detailPreviewApprovalPop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter
			 **********************************************************/
			String forwardURL = "";
			String returnMessage = "";

			CompletionForm form = null;
			CompletionVO vo = null;

			List approvalHisList = null;
			ListOrderedMap lomDetail = null;

			List resultList = null;
			List resultExecList = null;
			List resultCmpltList = null;
			List resultHisList = null;
			List resultAttachList = null;

			String payment_gbn = "";

			String strUrl = (request.getRequestURL()).toString();
			String strAttachUrl = strUrl.substring(0, strUrl.indexOf("/", 7) + 1) + "clms/common/clmsfile.do?method=doClmsDownload&file_id=";

			/*********************************************************
			 * Session Check
			 **********************************************************/
			HttpSession session = request.getSession(false);

			/*********************************************************
			 * Forward setting
			 **********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/manage/Completion_preview_mst2.jsp";

			/*********************************************************
			 * Form & VO Binding
			 **********************************************************/
			form = new CompletionForm();
			vo = new CompletionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * Page setting
			 **********************************************************/
			PageUtil pageUtil = new PageUtil();

			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(), "1")));

			vo.setStart_index(pageUtil.getStartIndex()); // 현재 페이지의 첫번째 게시물번호
															// set
			vo.setEnd_index(pageUtil.getEndIndex()); // 현재 페이지의 마지막 게시물번호 set

			/*********************************************************
			 * Service
			 **********************************************************/
			// 결재경로 정보
			approvalHisList = completionService.listCompletionApprovalHis(vo);

			if (approvalHisList.size() > 0) {
				lomDetail = (ListOrderedMap) approvalHisList.get(0);
			} else {
				System.out.println((String) messageSource.getMessage("clm.page.field.completion.detailPreviewApprovalPop01", null,
						new RequestContext(request).getLocale()));
			}

			// 기본 정보
			resultList = completionService.detailContract(vo);
			// 이행정보
			resultExecList = completionService.listExecutionHis(vo);
			// 종료정보
			resultCmpltList = completionService.detailCompletion(vo);
			// 이력정보
			resultHisList = completionService.listHisCompletion(vo);
			// 첨부파일
			resultAttachList = completionService.listCompletionAttachInfo(vo);

			// 기본정보*
			ListOrderedMap lom = null;
			ListOrderedMap td_lom = null;
			ArrayList contractMstLom = null;
			ArrayList contractExeLom = null;
			ArrayList contractSpclLom = null;
			ArrayList contractCnclsndlayLom = null;
			// 연관계약정보
			ArrayList contractRelationLom = null;
			int relationListSize = 0;

			// 특화정보
			ArrayList special1List = new ArrayList();
			ArrayList special2List = new ArrayList();

			if (resultList != null && resultList.size() > 0) {
				contractMstLom = (ArrayList) resultList.get(0);
				contractExeLom = (ArrayList) resultList.get(1);
				contractSpclLom = (ArrayList) resultList.get(2);
				contractCnclsndlayLom = (ArrayList) resultList.get(3);
				contractRelationLom = (ArrayList) resultList.get(4);

				if (contractRelationLom != null)
					relationListSize = contractRelationLom.size();

				if (contractMstLom != null && contractMstLom.size() > 0) {
					// master 정보
					lom = (ListOrderedMap) contractMstLom.get(0);

				} else {
					lom = new ListOrderedMap();
				}

				if (contractSpclLom != null && contractSpclLom.size() > 0) {
					// 특화정보 UI를 위한 정보
					// td_lom = (ListOrderedMap)contractSpclLom.get(0);
					// td_cnt = (Integer)td_lom.get("td_cnt");
					for (int i = 0; i < contractSpclLom.size(); i++) {
						ListOrderedMap tempLom = (ListOrderedMap) contractSpclLom.get(i);
						if ("C04002".equals(tempLom.get("crtn_depth"))) {
							special1List.add(contractSpclLom.get(i));
						} else if ("C04005".equals(tempLom.get("crtn_depth"))) {
							special2List.add(contractSpclLom.get(i));
						}
					}
				} else {
					// td_cnt = 0;
				}
			}
			// 이행정보*
			if (resultExecList != null && resultExecList.size() > 0) {
				ListOrderedMap resultExecListTmp = (ListOrderedMap) resultExecList.get(0);

				pageUtil.setTotalRow(((Integer) resultExecListTmp.get("total_cnt")).intValue());
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();

				// 지불_구분 C02001:지불/수금, C02002:지불, C02003:수금, C02004:해당없음
				payment_gbn = (String) resultExecListTmp.get("payment_gbn");

			}

			ArrayList resultExecListLom = (ArrayList) resultExecList;

			// 종료정보*
			ArrayList completionList = null;
			ListOrderedMap completionLom = null;

			ListOrderedMap approveManLom = new ListOrderedMap();
			ArrayList approveManList = new ArrayList();

			if (resultCmpltList != null && resultCmpltList.size() > 0) {
				completionList = (ArrayList) resultCmpltList.get(0);
				approveManList = (ArrayList) resultCmpltList.get(3);

				// 종료 정보
				completionLom = (ListOrderedMap) completionList.get(0);
				if (approveManList != null && approveManList.size() > 0) {
					approveManLom = (ListOrderedMap) approveManList.get(0);
				}
			}

			// 이력정보*
			ArrayList review = null;
			ArrayList approve = null;
			ArrayList sign = null;
			ArrayList info = null;

			if (resultHisList != null && resultHisList.size() > 0) {
				review = (ArrayList) resultHisList.get(0);
				approve = (ArrayList) resultHisList.get(1);
				sign = (ArrayList) resultHisList.get(2);
				info = (ArrayList) resultHisList.get(3);
			}
			// 최종 건을 구하기 위한 maxrm 변수 세팅
			int maxrm = 0;
			if (review != null) {
				ListOrderedMap _lom = new ListOrderedMap();
				for (int i = 0; i < review.size(); i++) {
					_lom = (ListOrderedMap) review.get(i);

					if (maxrm < Integer.parseInt(String.valueOf(_lom.get("rm")))) {
						maxrm = Integer.parseInt(String.valueOf(_lom.get("rm")));
					}
				}
			}
			// 첨부파일*
			ArrayList resultAttachArrList = new ArrayList();
			resultAttachArrList = (ArrayList) resultAttachList;

			/*********************************************************
			 * ModelAndView
			 **********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + forwardURL);

			// 결재경로 정보
			mav.addObject("approvalHisList", approvalHisList);
			mav.addObject("lomDetail", lomDetail);

			// 기본 정보
			mav.addObject("contractMstLom", lom);
			mav.addObject("contractExeLom", contractExeLom);
			mav.addObject("contractSpclLom", contractSpclLom);
			mav.addObject("contractCnclsndlayLom", contractCnclsndlayLom);
			mav.addObject("contractRelationLom", contractRelationLom);
			mav.addObject("relationListSize", relationListSize);

			// 특화정보
			mav.addObject("considerationSpecialList", special1List);
			mav.addObject("consultationSpecialList", special2List);
			mav.addObject("considerationSpecialSize", special1List.size());
			mav.addObject("consultationSpecialSize", special2List.size());
			// 이행정보
			mav.addObject("executionLom", resultExecListLom);
			mav.addObject("payment_gbn", payment_gbn);
			mav.addObject("pageUtil", pageUtil);
			// 종료정보
			mav.addObject("completionLom", completionLom);
			mav.addObject("approveManLom", approveManLom);
			// 이력정보
			mav.addObject("maxrm", maxrm);
			mav.addObject("review", review);
			mav.addObject("approve", approve);
			mav.addObject("sign", sign);
			mav.addObject("info", info);
			// 첨부파일정보
			mav.addObject("resultAttachArrList", resultAttachArrList);
			mav.addObject("attachinfo", strAttachUrl);

			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/*
	 * 종료 History 팝업
	 */
	public ModelAndView forwardCompletionHisPop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);

			String forwardURL = "/WEB-INF/jsp/clm/manage/Consultation_preview_p.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			CompletionForm form = new CompletionForm();
			CompletionVO vo = new CompletionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			String contents = request.getParameter("contents");
			String contentHtml = contents.toString();
			contentHtml = StringUtil.replace(contentHtml, "<html>", "<html xmlns='http://www.w3.org/1999/xhtml'>");
			contentHtml = StringUtil.replace(contentHtml, "<HTML>", "<html xmlns='http://www.w3.org/1999/xhtml'>");
			contentHtml = StringUtil.replace(contentHtml, "</HTML>", "</html>");

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + forwardURL);

			mav.addObject("command", form);
			mav.addObject("content", contentHtml);

			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	// 법무시스템 - 계약상세 내용 조회(popup 위한)
	public ModelAndView detailForPop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter
			 **********************************************************/
			String forwardURL = "";
			String returnMessage = "";

			CompletionForm form = null;
			CompletionVO vo = null;

			List approvalHisList = null;
			ListOrderedMap lomDetail = null;

			List resultConsiderationList = null;
			List resultList = null;
			List resultExecList = null;
			List resultCmpltList = null;
			List resultHisList = null;
			List resultAttachList = null;
			List listDc = null;

			// close history list 변수 추가
			List resultCloseList = null;

			ArrayList<HashMap> totalList = new ArrayList<HashMap>();
			;

			String payment_gbn = "";

			String strUrl = (request.getRequestURL()).toString();
			String strAttachUrl = strUrl.substring(0, strUrl.indexOf("/", 7) + 1) + "clms/common/clmsfile.do?method=doClmsDownload&file_id=";

			/*********************************************************
			 * Session Check
			 **********************************************************/
			HttpSession session = request.getSession(false);

			/*********************************************************
			 * Forward setting
			 **********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/manage/Completion_preview_mst3.jsp";

			/*********************************************************
			 * Form & VO Binding
			 **********************************************************/
			form = new CompletionForm();
			vo = new CompletionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * Service
			 **********************************************************/
			// 검토의뢰 정보
			resultConsiderationList = completionService.detailConsideration(vo);

			// 검토의뢰 정보
			ListOrderedMap lomRq = null;

			// 기본정보*
			ListOrderedMap lom = null;
			ListOrderedMap td_lom = null;
			ArrayList contractMstLom = null;
			ArrayList contractExeLom = null;
			ArrayList contractSpclLom = null;
			ArrayList contractCnclsndlayLom = null;
			// 연관계약정보
			ArrayList contractRelationLom = null;
			int relationListSize = 0;

			// 2014-05-01 Kevin added. To get GERP detail
			GERPInfoVO gerpVO = new GERPInfoVO();
			gerpVO.setCntrtID(vo.getCntrt_id());
			List list = considerationService.getGERPInformationDetail(gerpVO);
			ListOrderedMap gerpDetail = null;
			if (list != null && list.size() > 0) {
				gerpDetail = (ListOrderedMap) list.get(0);
			}

			// 특화정보
			ArrayList special1List = new ArrayList();
			ArrayList special2List = new ArrayList();

			// 검토의뢰 정보
			lomRq = (ListOrderedMap) resultConsiderationList.get(0);

			lomRq.put("cnsd_demnd_cont", StringUtil.convertEnterToBR((String) lomRq.get("cnsd_demnd_cont")));
			lomRq.put("cnsd_demnd_cont", StringUtil.convertNamoReplaceAll(StringUtil.bvl((String) lomRq.get("cnsd_demnd_cont"), "")));
			lomRq.put("plndbn_chge_cont", StringUtil.convertEnterToBR((String) lomRq.get("plndbn_chge_cont")));

			// 관련자 List
			listDc = completionService.listCntrtId(vo);

			ArrayList lomRm = null;
			ListOrderedMap mapRm = null;
			if (listDc != null && listDc.size() > 0) {
				String[] relMan = new String[listDc.size()];
				String cntrtInfoGbn = "";

				for (int i = 0; i < listDc.size(); i++) {
					mapRm = (ListOrderedMap) listDc.get(i);
					relMan[i] = (String) mapRm.get("cntrt_id");
				}

				vo.setNote_arr(relMan); // 원래 note_arr이 아니지만 vo객체를 수정하지 않기 위해
										// 여기서는 필요없는 note_arr변수를 쓴다.
				lomRm = (ArrayList) completionService.listRelationman(vo); // 관련자
																			// List
			}

			if (listDc != null && listDc.size() > 0) {
				for (int i = 0; i < listDc.size(); i++) {
					mapRm = (ListOrderedMap) listDc.get(i);
					vo.setCntrt_id((String) mapRm.get("cntrt_id"));

					// 기본 정보
					resultList = completionService.detailContract(vo);
					// 이력정보
					resultHisList = completionService.listHisCompletion(vo);
					// 첨부파일
					resultAttachList = completionService.listCompletionAttachInfo(vo);

					// close정보 조회
					resultCloseList = completionService.closeRTNView(vo);

					// 계약기본정보
					if (resultList != null && resultList.size() > 0) {
						contractMstLom = (ArrayList) resultList.get(0);
						contractExeLom = (ArrayList) resultList.get(1);
						contractSpclLom = (ArrayList) resultList.get(2);
						contractCnclsndlayLom = (ArrayList) resultList.get(3);
						contractRelationLom = (ArrayList) resultList.get(4);

						if (contractRelationLom != null)
							relationListSize = contractRelationLom.size();

						if (contractMstLom != null && contractMstLom.size() > 0) {
							// master 정보
							lom = (ListOrderedMap) contractMstLom.get(0);
						} else {
							lom = new ListOrderedMap();
						}

						// Purpose & Background (추진 목적 및 배경)
						lom.put("pshdbkgrnd_purps", StringUtil.convertEnterToBR((String) lom.get("pshdbkgrnd_purps")));
						// Summary of Unit Price Details ( 단가체결내역)
						lom.put("cntrt_untprc_expl", StringUtil.convertEnterToBR((String) lom.get("cntrt_untprc_expl")));

						if (contractSpclLom != null && contractSpclLom.size() > 0) {
							// 특화정보 UI를 위한 정보
							// td_lom = (ListOrderedMap)contractSpclLom.get(0);
							// td_cnt = (Integer)td_lom.get("td_cnt");
							for (int j = 0; j < contractSpclLom.size(); j++) {
								ListOrderedMap tempLom = (ListOrderedMap) contractSpclLom.get(j);
								if ("C04002".equals(tempLom.get("crtn_depth"))) {
									special1List.add(contractSpclLom.get(j));
								} else if ("C04005".equals(tempLom.get("crtn_depth"))) {
									special2List.add(contractSpclLom.get(j));
								}
							}
						} else {
							// td_cnt = 0;
						}
					}

					// 이력정보*
					ArrayList review = null;
					ArrayList approve = null;
					ArrayList sign = null;
					ArrayList info = null;

					if (resultHisList != null && resultHisList.size() > 0) {
						review = (ArrayList) resultHisList.get(0);
						approve = (ArrayList) resultHisList.get(1);
						sign = (ArrayList) resultHisList.get(2);
						info = (ArrayList) resultHisList.get(3);
					}

					// close 정보를 위한 변수 추가및 할당
					ListOrderedMap close = null;
					if (resultCloseList != null && resultCloseList.size() > 0) {
						close = (ListOrderedMap) resultCloseList.get(0);

					}

					// 최종 건을 구하기 위한 maxrm 변수 세팅
					int maxrm = 0;
					if (review != null) {
						ListOrderedMap _lom = new ListOrderedMap();
						for (int j = 0; j < review.size(); j++) {
							_lom = (ListOrderedMap) review.get(j);

							_lom.put("cont", StringUtil.convertEnterToBR((String) _lom.get("cont")));
							_lom.put("plndbn_chge_cont", StringUtil.convertEnterToBR((String) _lom.get("plndbn_chge_cont")));
							_lom.put("apbt_cont", StringUtil.convertEnterToBR((String) _lom.get("apbt_cont")));

							if (maxrm < Integer.parseInt(String.valueOf(_lom.get("rm")))) {
								maxrm = Integer.parseInt(String.valueOf(_lom.get("rm")));
							}
						}
					}

					// 첨부파일*
					ArrayList resultAttachArrList = new ArrayList();
					resultAttachArrList = (ArrayList) resultAttachList;

					HashMap hmap = new HashMap();
					hmap.put("contractMstLom", lom);
					hmap.put("contractExeLom", contractExeLom);
					hmap.put("contractSpclLom", contractSpclLom);
					hmap.put("contractCnclsndlayLom", contractCnclsndlayLom);
					hmap.put("contractRelationLom", contractRelationLom);
					hmap.put("relationListSize", relationListSize);
					// 특화정보
					hmap.put("considerationSpecialList", special1List);
					hmap.put("consultationSpecialList", special2List);
					hmap.put("considerationSpecialSize", special1List.size());
					hmap.put("consultationSpecialSize", special2List.size());
					// 이력정보
					hmap.put("maxrm", maxrm);
					hmap.put("review", review);
					hmap.put("approve", approve);
					hmap.put("sign", sign);
					hmap.put("info", info);
					// 첨부파일정보
					hmap.put("resultAttachArrList", resultAttachArrList);

					// Close 정보 바인딩
					hmap.put("close", close);

					totalList.add(hmap);
				}
			}

			/*********************************************************
			 * ModelAndView
			 **********************************************************/
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + forwardURL);

			mav.addObject("lomRq", lomRq);
			mav.addObject("lomRm", lomRm);
			mav.addObject("totalList", totalList);
			mav.addObject("attachinfo", strAttachUrl);

			// 2014-05-01 Kevin added.
			mav.addObject("gerpDetail", gerpDetail);

			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	// 법무 계약리스트 상세 인쇄 팝업
	public ModelAndView forwardDetailPop(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);

			String forwardURL = "/WEB-INF/jsp/clm/manage/Completion_preview_pop3.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			 *********************************************************/
			CompletionForm form = new CompletionForm();
			CompletionVO vo = new CompletionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			String contents = request.getParameter("contents");
			String contentHtml = contents.toString();

			contentHtml = StringUtil.replace(contentHtml, "<html>", "<html xmlns='http://www.w3.org/1999/xhtml'>");
			contentHtml = StringUtil.replace(contentHtml, "<HTML>", "<html xmlns='http://www.w3.org/1999/xhtml'>");
			contentHtml = StringUtil.replace(contentHtml, "</HTML>", "</html>");

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + forwardURL);

			mav.addObject("command", form);
			mav.addObject("content", contentHtml);

			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 자동연장 승인목록 @param request, response @return ModelAndView @throws
	 */
	public ModelAndView listAutoRenewApproval(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setAttribute("status_mode", "renewApproval");
		mav = manageControl.listManage(request, response);
		return mav;
	}

	/**
	 * 자동연장 승인 @param request, response @return ModelAndView @throws
	 */
	public ModelAndView modifyAutoRenewApproval(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter
			 **********************************************************/
			String forwardURL = "";
			String returnMessage = "";

			CompletionForm form = null;
			CompletionVO vo = null;

			/*********************************************************
			 * Session Check
			 **********************************************************/
			HttpSession session = request.getSession(false);

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			form = new CompletionForm();
			vo = new CompletionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * roleCheck
			 **********************************************************/
			String checkYN = "";
			checkYN = "Y";

			/*********************************************************
			 * Service
			 **********************************************************/
			if ("Y".equals(checkYN)) {
				returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());
				completionService.modifyAutoRenewApproval(vo);
			} else {
				forwardURL = "/WEB-INF/jsp/secfw/error/ErrorBoard.jsp";
				form.setReturn_message(messageSource.getMessage("secfw.page.field.alert.noLinkMessage", null, new RequestContext(request).getLocale()));
				form.setReturn_title(messageSource.getMessage("secfw.page.field.alert.noAuthTitle", null, new RequestContext(request).getLocale()));
			}

			return listAutoRenewApproval(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 이행단계로 변경 @param request, response @return ModelAndView @throws
	 */
	public ModelAndView modifyCompletionStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter
			 **********************************************************/
			String forwardURL = "";
			String returnMessage = "";

			CompletionForm form = null;
			CompletionVO vo = null;
			ModelAndView mav = new ModelAndView();

			/*********************************************************
			 * Session Check
			 **********************************************************/
			HttpSession session = request.getSession(false);

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			form = new CompletionForm();
			vo = new CompletionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * roleCheck
			 **********************************************************/
			String checkYN = "";
			checkYN = "Y";

			/*********************************************************
			 * Service
			 **********************************************************/
			if ("Y".equals(checkYN)) {
				returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());

				vo.setCntrt_status("C02402"); // 체결
				vo.setPrcs_depth("C02504"); // 계약이행
				vo.setDepth_status("C02662"); // 이행중
				vo.setPrgrs_status("C04219"); // 이행중

				completionService.modifyCompletionStatus(vo);

				forwardURL = "/secfw/main.do?method=forwardSubFrame&targetMenuId=20110802182454521_0000000036&selected_menu_id=20110802182454521_0000000036&selected_menu_detail_id=20110803091537331_0000000052&set_init_url=/clm/manage/execution.do?method=listManageExecution";
						     
			} else {
				forwardURL = "/WEB-INF/jsp/secfw/error/ErrorBoard.jsp";
				form.setReturn_message(messageSource.getMessage("secfw.page.field.alert.noLinkMessage", null, new RequestContext(request).getLocale()));
				form.setReturn_title(messageSource.getMessage("secfw.page.field.alert.noAuthTitle", null, new RequestContext(request).getLocale()));
			}

			return listManageCompletion(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * T&C에서 상세 화면 호출 @param request, response @return ModelAndView @throws
	 */
	public ModelAndView tncListContract(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter
			 **********************************************************/
			ModelAndView mav = new ModelAndView();

			String forwardURL = "";
			String returnMessage = "";
			if (request.getAttribute("returnMessage") != null)
				returnMessage = (String) request.getAttribute("returnMessage");

			CompletionForm form = null;
			CompletionVO vo = null;
			List resultList = new ArrayList();
			List detailList = new ArrayList();
			List tempList = null;
			ListOrderedMap initLom = null;
			ListOrderedMap tempLom = null;
			ArrayList lom = null;

			HttpSession session = null;
			/*********************************************************
			 * Session Check
			 **********************************************************/
			session = request.getSession(false);

			/*********************************************************
			 * Forward setting
			 **********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/manage/Completion_TCMS.jsp";

			/*********************************************************
			 * Form & VO Binding
			 **********************************************************/
			form = new CompletionForm();
			vo = new CompletionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * Service
			 **********************************************************/
			tempList = completionService.listContract2(vo);

			/*********************************************************
			 * Service
			 **********************************************************/

			if (tempList != null && tempList.size() > 0) {
				for (int i = 0; i < tempList.size(); i++) {
					tempLom = (ListOrderedMap) tempList.get(i);

					resultList.add(tempList.get(i));

				}

				if (resultList.size() > 0) {
					initLom = (ListOrderedMap) resultList.get(0);
					form.setCnsdreq_id((String) initLom.get("cnsdreq_id"));
					form.setCntrt_id((String) initLom.get("cntrt_id"));
					form.setCntrt_status((String) initLom.get("cntrt_status"));
					form.setPrcs_depth((String) initLom.get("prcs_depth"));
					form.setDepth_status((String) initLom.get("depth_status"));
					form.setReqman_id((String) initLom.get("reqman_id"));
					form.setReqman_nm((String) initLom.get("reqman_nm"));
					form.setCntrt_respman_id((String) initLom.get("cntrt_respman_id"));
					form.setCntrt_respman_nm((String) initLom.get("cntrt_respman_nm"));
				}
				lom = (ArrayList) resultList;
			}
			vo.setCntrt_id(form.getCntrt_id());
			detailList = completionService.detailManageCompletion(vo);

			ListOrderedMap contractMstLom = null;
			ArrayList contractRelationLom = null;
			ListOrderedMap approveManLom = null;
			ArrayList review = null;
			ArrayList approve = null;
			ArrayList info = null;
			ArrayList executionList = null;
			ArrayList orgMngHistory = null;
			ListOrderedMap completionLom = null;
			ArrayList special1List = new ArrayList();
			ArrayList special2List = new ArrayList();
			ArrayList specialList = null;
			// List approvalHisList = null;
			// ListOrderedMap lomDetail = null;
			String payment_gbn = "";
			List authReqList = null;

			StringBuffer reqAuthInfo = new StringBuffer();

			if (detailList != null && detailList.size() > 0) {
				// 1. 계약정보
				if (detailList.get(0) != null && ((ArrayList) detailList.get(0)).size() > 0) {
					contractMstLom = (ListOrderedMap) ((ArrayList) detailList.get(0)).get(0);
					contractMstLom.put("antcptnefct", StringUtil.convertEnterToBR((String) contractMstLom.get("antcptnefct")));
					// 지불/수분조건 <BR>PAYMENT_COND
					contractMstLom.put("payment_cond", StringUtil.convertEnterToBR((String) contractMstLom.get("payment_cond")));
					// 기타주요사항 <BR>ETC_MAIN_CONT
					contractMstLom.put("etc_main_cont", StringUtil.convertEnterToBR((String) contractMstLom.get("etc_main_cont")));
					// CNTRT_CHGE_DEMND_CAUSE
					contractMstLom.put("cntrt_chge_demnd_cause", StringUtil.convertEnterToBR((String) contractMstLom.get("cntrt_chge_demnd_cause")));
					// 단가내역 요약cntrt_untprc_expl CNTRT_UNTPRC_EXPL
					contractMstLom.put("cntrt_untprc_expl", StringUtil.convertEnterToBR((String) contractMstLom.get("cntrt_untprc_expl")));
					// 책임한도 oblgt_lmt OBLGT_LMT
					contractMstLom.put("oblgt_lmt", StringUtil.convertEnterToBR((String) contractMstLom.get("oblgt_lmt")));
					// Specila Condition spcl_cond SPCL_COND
					contractMstLom.put("spcl_cond", StringUtil.convertEnterToBR((String) contractMstLom.get("spcl_cond")));
					// loac_etc 준거법 상세 LOAC_ETC
					contractMstLom.put("loac_etc", StringUtil.convertEnterToBR((String) contractMstLom.get("loac_etc")));
					// dspt_resolt_mthd_det 분쟁해결방법상세 DSPT_RESOLT_DET
					contractMstLom.put("dspt_resolt_mthd_det", StringUtil.convertEnterToBR((String) contractMstLom.get("dspt_resolt_mthd_det")));
					// 메모
					contractMstLom.put("org_acpt_dlay_cause", StringUtil.convertEnterToBR((String) contractMstLom.get("org_acpt_dlay_cause")));

					mav.addObject("contractMstLomSize", detailList.size());

				}

				// 2. 연관계약정보
				if (detailList.get(1) != null && ((ArrayList) detailList.get(1)).size() > 0) {
					contractRelationLom = (ArrayList) detailList.get(1);
				}
				// 3. 승인자 정보(종료정보 中)
				if (detailList.get(2) != null && ((ArrayList) detailList.get(2)).size() > 0) {
					approveManLom = (ListOrderedMap) ((ArrayList) detailList.get(2)).get(0);
				}
				// 8. 종료정보
				if (detailList.get(3) != null && ((ArrayList) detailList.get(3)).size() > 0) {
					completionLom = (ListOrderedMap) ((ArrayList) detailList.get(3)).get(0);
				}
				// 9. 자동연장 이력
				if (detailList.get(4) != null && ((ArrayList) detailList.get(4)).size() > 0) {
					orgMngHistory = (ArrayList) detailList.get(4);
				}
				// 11.관련자 정보
				if (detailList.get(5) != null && ((List) detailList.get(5)).size() > 0) {
					authReqList = (ArrayList) detailList.get(5);

					for (int i = 0; i < authReqList.size(); i++) {
						ListOrderedMap authLom = (ListOrderedMap) authReqList.get(i);
						if (i > 0) {
							reqAuthInfo.append(", " + StringUtil.bvl((String) authLom.get("trgtman_nm"), "") + "/"
									+ StringUtil.bvl((String) authLom.get("trgtman_jikgup_nm"), "") + "/"
									+ StringUtil.bvl((String) authLom.get("trgtman_dept_nm"), "")); // display용
						} else {
							reqAuthInfo.append(
									StringUtil.bvl((String) authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String) authLom.get("trgtman_jikgup_nm"), "")
											+ "/" + StringUtil.bvl((String) authLom.get("trgtman_dept_nm"), ""));
						}
					}
				}

			} else {
				mav.addObject("contractMstLomSize", detailList.size());
			}

			List tmpSessionRoleList = (List) session.getAttribute("secfw.session.role");
			String user_role = "";
			for (int i = 0; i < tmpSessionRoleList.size(); i++) {
				Map tmpSessionRoleMap = (Map) tmpSessionRoleList.get(i);
				if (tmpSessionRoleMap.containsValue("RD01")) {
					user_role = "RD01";
				}
			}

			/**
			 * 구주 법무시스템의 경우 로그인하 사람의 권한이 Legal Admin(RA01)일 경우 계약의 모든 권한을 가지게
			 * 된다.
			 */

			ArrayList userRoleList = (ArrayList) session.getAttribute("secfw.session.roleList"); // session에서
																									// ROLE에
																									// 대한
																									// 값을
																									// 가지고
																									// 온다.

			if (userRoleList.contains("RA01") || userRoleList.contains("RD01") || userRoleList.contains("RM00")) {
				form.setTop_role("RA01");
			} else if (userRoleList.contains("RA02") || userRoleList.contains("RA03")) {
				form.setTop_role("RA02");
			} else {
				form.setTop_role("ETC");
			}

			/*********************************************************
			 * ModelAndView
			 **********************************************************/

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + forwardURL);

			mav.addObject("contractLom", lom);

			mav.addObject("contractLom", lom);
			mav.addObject("contractReqLom", initLom);
			mav.addObject("contractMstLom", contractMstLom);
			mav.addObject("contractRelationLom", contractRelationLom);
			mav.addObject("approveManLom", approveManLom);
			mav.addObject("review", review);
			mav.addObject("approve", approve);
			mav.addObject("info", info);
			mav.addObject("executionList", executionList);
			mav.addObject("payment_gbn", payment_gbn);
			mav.addObject("completionLom", completionLom);
			mav.addObject("user_role", user_role);
			mav.addObject("orgMngHistory", orgMngHistory);
			mav.addObject("considerationSpecialList", special1List);
			mav.addObject("consultationSpecialList", special2List);
			// mav.addObject("approvalHisList" , approvalHisList);
			// mav.addObject("lomDetail" , lomDetail);
			mav.addObject("reqAuthInfo", reqAuthInfo);
			mav.addObject("authReqList", authReqList);
			mav.addObject("command", form);

			mav.addObject("ssems_oppnt_cd", StringUtil.bvl(request.getParameter("ssems_oppnt_cd"), ""));// ssems인터페이스시
																										// 사용

			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("listContract() Exception !!");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("listContract() Throwable !!");
		}
	}

	/**
	 * Tnc 링크 획득
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView getTncLink(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);
			String forwardURL = "/WEB-INF/jsp/clm/manage/TncInformation.jsp";
			List resultList = null;
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			form.setCntrt_id(vo.getCntrt_id());

			String cntrt_id4 = request.getParameter("cntrt_id4");

			vo.setCntrt_id(cntrt_id4);

			// 필수 항목 체크 내용 - 15개
			List tncList = completionService.listTncLink(vo);
			ListOrderedMap tnclom = new ListOrderedMap();

			if (tncList != null) {
				for (int j = 0; j < tncList.size(); j++) {
					tnclom = (ListOrderedMap) tncList.get(j);
					tnclom.put("TNC_APP_LINK", StringUtil.convertEnterToBR((String) tnclom.get("TNC_APP_LINK")));
					tnclom.put("CTC_LINK", StringUtil.convertEnterToBR((String) tnclom.get("CTC_LINK")));
					tnclom.put("OTC_LINK", StringUtil.convertEnterToBR((String) tnclom.get("OTC_LINK")));
					tnclom.put("TNC_SUMMARY_LINK", StringUtil.convertEnterToBR((String) tnclom.get("TNC_SUMMARY_LINK")));
				}
			}

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);

			mav.addObject("command", form);
			mav.addObject("tnclom", tnclom);

			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	
	/**
	 * 연관계약 상세 정보 표시
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView relatedContractDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			LOGGER.debug("referer:" + request.getHeader("referer"));
			
			/*********************************************************
			 * Parameter
			 **********************************************************/
			//('/clm/manage/completion.do?method=listContract&menu_id=20130319154642301_0000000355&conListGu=Z1000&cnsdreq_id='+cnsdreq_id
			
			String forwardURL = "";
			CompletionForm form = null;
			CompletionVO vo = null;
			List resultList = new ArrayList();
			List detailList = new ArrayList();
			List tempList = null;
			ListOrderedMap initLom = null;

			HttpSession session = null;
			/*********************************************************
			 * Session Check
			 **********************************************************/
			session = request.getSession(false);

			/*********************************************************
			 * Forward setting
			 **********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/manage/RelatedContractDetail.jsp";

			/*********************************************************
			 * Form & VO Binding
			 **********************************************************/
			form = new CompletionForm();
			vo = new CompletionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			ModelAndView mav = new ModelAndView();

		
			//list 지만 검색 조건에 id 가 들어가 있어, 무조건 한건만 조회됨
			tempList = completionService.listContract(vo);
			
			/*********************************************************
			 * roleCheck
			 **********************************************************/
			if( ((ArrayList<String>)session.getAttribute("secfw.session.roleList")).contains("RA01")    ){
			
			}else if(((ArrayList<String>)session.getAttribute("secfw.session.roleList")).contains("RA02")  ){
			
			//user가 requester 만 가지고 있을 경우, 체크 후 권한 없을시 권한없음 페이지로 이동
			}else{
				String userId=session.getAttribute("secfw.session.user_id").toString();
				ListOrderedMap  map= (ListOrderedMap) tempList.get(0);
				
				if(  !userId.equals( (String) map.get("cnsdman_id"))
						&&	!userId.equals( (String) map.get("reqman_id"))
						&&	!userId.equals( (String) map.get("cntrt_respman_id"))
						&&  completionService.countResponseManId(vo)== 0	
						){
					forwardURL = "/WEB-INF/jsp/clm/manage/RelatedContractDetailNoAuthorisation.jsp";
					mav.setViewName(forwardURL);
					return mav;
					
				} 
					
				 
				
			}


			/*********************************************************
			 * Service
			 **********************************************************/
			if (tempList != null && tempList.size() > 0) {
				for (int i = 0; i < tempList.size(); i++) {
					resultList.add(tempList.get(i));
				}

				if (resultList.size() > 0) {
					initLom = (ListOrderedMap) resultList.get(0);
					form.setCnsdreq_id((String) initLom.get("cnsdreq_id"));
					form.setCntrt_id((String) initLom.get("cntrt_id"));
					form.setCntrt_status((String) initLom.get("cntrt_status"));
					form.setPrcs_depth((String) initLom.get("prcs_depth"));
					form.setDepth_status((String) initLom.get("depth_status"));
					form.setReqman_id((String) initLom.get("reqman_id"));
					form.setReqman_nm((String) initLom.get("reqman_nm"));
					form.setCntrt_respman_id((String) initLom.get("cntrt_respman_id"));
					form.setCntrt_respman_nm((String) initLom.get("cntrt_respman_nm"));
				}
			}

			vo.setCntrt_id(form.getCntrt_id());
			detailList = completionService.detailManageCompletion(vo);

			ListOrderedMap contractMstLom = null;
			ArrayList contractRelationLom = null;
			ListOrderedMap approveManLom = null;
			ArrayList orgMngHistory = null;
			ListOrderedMap completionLom = null;
			List authReqList = null;

			StringBuffer reqAuthInfo = new StringBuffer();

			if (detailList != null && detailList.size() > 0) {
				// 1. 계약정보
				if (detailList.get(0) != null && ((ArrayList) detailList.get(0)).size() > 0) {
					contractMstLom = (ListOrderedMap) ((ArrayList) detailList.get(0)).get(0);
					contractMstLom.put("antcptnefct", StringUtil.convertEnterToBR((String) contractMstLom.get("antcptnefct")));
					// 지불/수분조건 <BR>PAYMENT_COND
					contractMstLom.put("payment_cond", StringUtil.convertEnterToBR((String) contractMstLom.get("payment_cond")));
					// 기타주요사항 <BR>ETC_MAIN_CONT
					contractMstLom.put("etc_main_cont", StringUtil.convertEnterToBR((String) contractMstLom.get("etc_main_cont")));
					// CNTRT_CHGE_DEMND_CAUSE
					contractMstLom.put("cntrt_chge_demnd_cause", StringUtil.convertEnterToBR((String) contractMstLom.get("cntrt_chge_demnd_cause")));
					// 단가내역 요약cntrt_untprc_expl CNTRT_UNTPRC_EXPL
					contractMstLom.put("cntrt_untprc_expl", StringUtil.convertEnterToBR((String) contractMstLom.get("cntrt_untprc_expl")));
					// 책임한도 oblgt_lmt OBLGT_LMT
					contractMstLom.put("oblgt_lmt", StringUtil.convertEnterToBR((String) contractMstLom.get("oblgt_lmt")));
					// Special Condition spcl_cond SPCL_COND
					contractMstLom.put("spcl_cond", StringUtil.convertEnterToBR((String) contractMstLom.get("spcl_cond")));
					// loac_etc 준거법 상세 LOAC_ETC
					contractMstLom.put("loac_etc", StringUtil.convertEnterToBR((String) contractMstLom.get("loac_etc")));
					// dspt_resolt_mthd_det 분쟁해결방법상세 DSPT_RESOLT_DET
					contractMstLom.put("dspt_resolt_mthd_det", StringUtil.convertEnterToBR((String) contractMstLom.get("dspt_resolt_mthd_det")));
					// 메모
					contractMstLom.put("org_acpt_dlay_cause", StringUtil.convertEnterToBR((String) contractMstLom.get("org_acpt_dlay_cause")));

				}
				// 2. 연관계약정보
				if (detailList.get(1) != null && ((ArrayList) detailList.get(1)).size() > 0) {
					contractRelationLom = (ArrayList) detailList.get(1);
				}
				// 3. 승인자 정보(종료정보 中)
				if (detailList.get(2) != null && ((ArrayList) detailList.get(2)).size() > 0) {
					approveManLom = (ListOrderedMap) ((ArrayList) detailList.get(2)).get(0);
				}
				// 8. 종료정보
				if (detailList.get(3) != null && ((ArrayList) detailList.get(3)).size() > 0) {
					completionLom = (ListOrderedMap) ((ArrayList) detailList.get(3)).get(0);
				}
				// 9. 자동연장 이력
				if (detailList.get(4) != null && ((ArrayList) detailList.get(4)).size() > 0) {
					orgMngHistory = (ArrayList) detailList.get(4);
				}
				// 11.관련자 정보
				if (detailList.get(5) != null && ((List) detailList.get(5)).size() > 0) {
					authReqList = (ArrayList) detailList.get(5);

					for (int i = 0; i < authReqList.size(); i++) {
						ListOrderedMap authLom = (ListOrderedMap) authReqList.get(i);
						if (i > 0) {
							reqAuthInfo.append(", " + StringUtil.bvl((String) authLom.get("trgtman_nm"), "") + "/"
									+ StringUtil.bvl((String) authLom.get("trgtman_jikgup_nm"), "") + "/"
									+ StringUtil.bvl((String) authLom.get("trgtman_dept_nm"), "")); // display용
						} else {
							reqAuthInfo.append(
									StringUtil.bvl((String) authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String) authLom.get("trgtman_jikgup_nm"), "")
											+ "/" + StringUtil.bvl((String) authLom.get("trgtman_dept_nm"), ""));
						}
					}
				}
			}

			List tmpSessionRoleList = (List) session.getAttribute("secfw.session.role");
			String user_role = "";
			for (int i = 0; i < tmpSessionRoleList.size(); i++) {
				Map tmpSessionRoleMap = (Map) tmpSessionRoleList.get(i);
				if (tmpSessionRoleMap.containsValue("RD01")) {
					user_role = "RD01";
				}
			}

			/**
			 * 구주 법무시스템의 경우 로그인하 사람의 권한이 Legal Admin(RA01)일 경우 계약의 모든 권한을 가지게
			 * 된다.
			 */
			ArrayList userRoleList = (ArrayList) session.getAttribute("secfw.session.roleList"); // session에서
																									// ROLE에
																									// 대한
																									// 값을
																									// 가지고
																									// 온다.

			if (userRoleList.contains("RA01") || userRoleList.contains("RD01") || userRoleList.contains("RM00")) {
				form.setTop_role("RA01");
			} else if (userRoleList.contains("RA02") || userRoleList.contains("RA03")) {
				form.setTop_role("RA02");
			} else {
				form.setTop_role("ETC");
			}

			/*********************************************************
			 * ModelAndView
			 **********************************************************/
			 

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + forwardURL);

			mav.addObject("contractReqLom", initLom);
			mav.addObject("contractMstLom", contractMstLom);
			mav.addObject("contractRelationLom", contractRelationLom);
			mav.addObject("approveManLom", approveManLom);
			mav.addObject("completionLom", completionLom);
			mav.addObject("user_role", user_role);
			mav.addObject("orgMngHistory", orgMngHistory);
			mav.addObject("reqAuthInfo", reqAuthInfo);
			mav.addObject("authReqList", authReqList);
			mav.addObject("command", form);

			mav.addObject("ssems_oppnt_cd", StringUtil.bvl(request.getParameter("ssems_oppnt_cd"), ""));// ssems인터페이스시
																										// 사용

			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("listContract() Exception !!");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("listContract() Throwable !!");
		}
	}

}