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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.EsbUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.singleIF.dto.ApprovalVO;
import com.sds.secframework.singleIF.service.EsbApprovalService;
import com.sds.secframework.singleIF.service.EsbOrgService;
import com.sec.clm.manage.dto.ChooseContractVO;
import com.sec.clm.manage.dto.ConsiderationForm;
import com.sec.clm.manage.dto.ConsiderationVO;
import com.sec.clm.manage.dto.ConsultationExecVO;
import com.sec.clm.manage.dto.ConsultationForm;
import com.sec.clm.manage.dto.ConsultationSpecialVO;
import com.sec.clm.manage.dto.ConsultationVO;
import com.sec.clm.manage.dto.ExecutionForm;
import com.sec.clm.manage.dto.ExecutionVO;
import com.sec.clm.manage.dto.GERPInfoVO;
import com.sec.clm.manage.service.ConsiderationService;
import com.sec.clm.manage.service.ConsultationService;
import com.sec.clm.manage.service.MailSendService;
import com.sec.common.clmscom.service.CLMSCommonService;
import com.sec.common.clmsfile.service.ClmsFileService;
import com.sec.common.util.ClmsDataUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 계약관리 >My Contract>검토의뢰
 * 
 * @author HP @
 */
public class ConsiderationController extends CommonController {

	private ManageController manageControl;

	public void setManageControl(ManageController manageControl) {
		this.manageControl = manageControl;
	}

	private ConsultationService consultationService;

	public void setConsultationService(ConsultationService consultationService) {
		this.consultationService = consultationService;
	}

	private ConsiderationService considerationService;

	public void setConsiderationService(ConsiderationService considerationService) {
		this.considerationService = considerationService;
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
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private CLMSCommonService clmsComService;

	public void setClmsComService(CLMSCommonService clmsComService) {
		this.clmsComService = clmsComService;
	}

	/**
	 * 첨부파일
	 */
	private ClmsFileService clmsFileService;

	public void setClmsFileService(ClmsFileService clmsFileService) {
		this.clmsFileService = clmsFileService;
	}

	/**
	 * 메일전송
	 */
	private MailSendService mailSendService;

	public void setMailSendService(MailSendService mailSendService) {
		this.mailSendService = mailSendService;
	}

	/**
	 * 계약이행 목록
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listManageConsideration(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView();
		String pgGbu = "";

		pgGbu = request.getParameter("pageGbn");

		if ("registration".equals(pgGbu)) {
			request.setAttribute("status_mode", "registration");
		} else {
			request.setAttribute("status_mode", "C02501");
		}

		mav = manageControl.listManage(request, response);

		return mav;
	}

	/**
	 * @param request
	 * @param response
	 * @return 연관계약 팝업 목록
	 * @throws Exception
	 */
	public ModelAndView popupListContract(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter
			 **********************************************************/
			String forwardURL = "";
			String returnMessage = "";
			String roleCd = "";
			String rel_type1 = "";
			// 계약관리 필요한 세션 조건 값 세팅
			String dept = ""; // 소속 조직
			String user_id = ""; // 세션 user_id
			String part = ""; // 세션 부서 트리
			String borgnz = ""; // 지원부서여부
			String manager_yn = ""; // 부서장 여부

			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();

			PageUtil pageUtil = null;
			List<?> resultList = null;

			/*********************************************************
			 * Session Check
			 **********************************************************/
			HttpSession session = request.getSession(false);

			// session에서 권한을 가지고 오기 위해서 로그인 한 사람의 권한 정보를 가지고 온다.
			ArrayList<?> roleList = (ArrayList<?>) session.getAttribute("secfw.session.role");

			String sys_cd = (String) session.getAttribute("secfw.session.sys_cd");

			/*********************************************************
			 * Parameter setting
			 **********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/manage/RelMMContract_l.jsp"; // 변경 연관
																		// 계약
																		// 입니다.

			/*********************************************************
			 * Form & VO Binding
			 **********************************************************/
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

			form.setSearch_cntrt_nm(form.getSearch_cntrt_nm());
			form.setSearch_req_nm(form.getSearch_req_nm());
			form.setArg(form.getArg());

			rel_type1 = form.getRel_type1();

			ClmsDataUtil.debug("rel_type1 = " + rel_type1);

			ArrayList<String> userRoleList = new ArrayList<String>();
			if (roleList != null && roleList.size() > 0) {
				for (int i = 0; i < roleList.size(); i++) {
					HashMap hm = (HashMap) roleList.get(i);
					roleCd = (String) hm.get("role_cd");
					userRoleList.add(roleCd);
				}
			}

			/*********************************************************
			 * Service 세션의 권한 값에 따라서 분기를 해야 됩니다. 접속한 시스템으로 최초 분기 CLM : 의뢰 시, 최종본
			 * 의뢰 요청 시, 체결 상신 시 LAS : 법무 그룹장 배정 및 승인 시, 법무 담당자 발신 시 의뢰/최종본/체결 상신
			 * 시 --> 체결 완료 된 계약만 조회되도록 변경
			 * 
			 * 법무 그룹장 로그인 => 해당 업체 계약 조회 법무 담당자 로그인 => 본인이 담당자였던 계약만 조회
			 * 
			 * 계약 관리(CLM)로 들어 올 경우 : 세션 소속 조직, 세션 USER_ID, 세션 부서 트리 값이 필요 법무
			 * 관리(LAS)로 들어 올 경우 : 권한이 조건으로 넘어감.(RA01, RA02), 세션 USER_ID
			 * 
			 * 조건의 추가가 됩니다.2012년 1월 2일 연관 계약 팝업 시 Relation Type 에서 Biz 관계일 경우
			 * 연관계약을 띄울때 계약 유형이 NDA, MOU/LOI만 조회해서 띄울 것.
			 * 
			 **********************************************************/

			dept = (String) session.getAttribute("secfw.session.blngt_orgnz"); // 소속
																				// 조직
			user_id = (String) session.getAttribute("secfw.session.user_id"); // 세션
																				// user_id
			part = (String) session.getAttribute("secfw.session.dept_cd"); // 세션
																			// 부서
																			// 트리
			borgnz = (String) session.getAttribute("secfw.session.support_yn"); // 지원
																				// 부서
																				// 여부
			manager_yn = (String) session.getAttribute("secfw.session.manager_yn"); // 부서장
																					// 여부

			ClmsDataUtil.debug("user_id = " + user_id);
			ClmsDataUtil.debug("part = " + part);
			ClmsDataUtil.debug("borgnz = " + borgnz);
			ClmsDataUtil.debug("manager_yn = " + manager_yn);

			// vo 담기
			vo.setDept(dept);
			vo.setUser_id(user_id);
			vo.setPart(part);
			vo.setBorgnz(borgnz);
			vo.setManager_yn(manager_yn);

			if ("C03201".equals(rel_type1)) {
				ClmsDataUtil.debug("1 = " + rel_type1);
				vo.setRel_type1(rel_type1);
				form.setRel_type1(rel_type1);

			}

			if ("CLM".equals(sys_cd)) {
				if (userRoleList != null && userRoleList.size() > 0) {
					if (userRoleList.contains("RD01")) {
						resultList = considerationService.popupListContract1(vo); // 준법지원
					} else {
						resultList = considerationService.popupListContract4(vo); // 그외
																					// 사업부
					}
				}

			} else if ("LAS".equals(sys_cd)) {
				if (userRoleList != null && userRoleList.size() > 0) {
					if (userRoleList.contains("RA01") || userRoleList.contains("RA02")) {
						resultList = considerationService.popupListContract2(vo);
					} else {
						resultList = considerationService.popupListContract3(vo);
					}
				}

			} else {
				ClmsDataUtil.debug((String) messageSource.getMessage("clm.page.field.consideration.popupListContract01", null, new RequestContext(request).getLocale()));
			}

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap) resultList.get(0);

				pageUtil.setTotalRow(((Integer) lom.get("total_cnt")).intValue());
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();

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

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + forwardURL);

			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

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
	 * 계약 등록 폼
	 */
	public ModelAndView forwardInsertContractMaster(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter
			 **********************************************************/
			ListOrderedMap lomRq = new ListOrderedMap();
			List<?> listTs = null;
			List<?> listRc = null;
			/*********************************************************
			 * Forward setting
			 **********************************************************/
			String forwardURL = "";

			/*********************************************************
			 * Form & VO Binding
			 **********************************************************/
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();

			bind(request, form);
			bind(request, vo);

			form.setPageGbn(StringUtil.nvl((String) form.getPageGbn(), ""));

			if ("registration".equals(form.getPageGbn().replaceAll(",", ""))) {
				forwardURL = "/WEB-INF/jsp/clm/manage/RegConsideration_inner_i.jsp";
			} else {
				forwardURL = "/WEB-INF/jsp/clm/manage/Consideration_inner_i.jsp";
			}

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			/*********************************************************
			 * Service
			 **********************************************************/
			vo.setCntrt_id(form.getMod_cntrt_id());

			List<?> listDc = considerationService.detailForwardContractMaster(vo);

			if (listDc != null && listDc.size() > 0) {
				lomRq = (ListOrderedMap) listDc.get(0);

				vo.setCnclsnpurps_bigclsfcn((String) lomRq.get("cnclsnpurps_bigclsfcn"));
				vo.setCnclsnpurps_midclsfcn((String) lomRq.get("cnclsnpurps_midclsfcn"));

				listTs = considerationService.listTypeSpclinfoMod(vo);// TN_CLM_TYPE_SPCLINFO
																		// 특화정보
				ClmsDataUtil.debug("forwardInsertContractMaster  listDc >>  " + listDc);
				lomRq.put("total_cnt", form.getTab_cnt()); // 계약 Cnt
				ClmsDataUtil.debug("#############################################");
				ClmsDataUtil.debug(lomRq.get("prev_cnsdreq_id"));
				ClmsDataUtil.debug(lomRq.get("plndbn_req_yn"));
				ClmsDataUtil.debug("#############################################");
			} else {
				lomRq.put("cntrt_id", considerationService.getId());
				lomRq.put("cntrtperiod_startday", "");
				lomRq.put("cntrtperiod_endday", "");
				lomRq.put("bfhdcstn_apbtday", "");
				// 지불/수금정보
				lomRq.put("payment_gbn", form.getPayment_gbn());
				// 통화단위 해당없음으로 통일
				lomRq.put("crrncy_unit", "");
				lomRq.put("total_cnt", form.getTab_cnt()); // 계약 Cnt
				// cntrt_nm 계약명
				lomRq.put("cntrt_nm", form.getCntrt_nm());
				// 거래 상대방 설정
				lomRq.put("cntrt_oppnt_cd", form.getTmp_cntrt_oppnt_cd());
				lomRq.put("region_gbn", form.getTmp_region_gbn());
				lomRq.put("cntrt_oppnt_nm", form.getTmp_cntrt_oppnt_nm());
				lomRq.put("cntrt_oppnt_rprsntman", form.getTmp_cntrt_oppnt_rprsntman());
				lomRq.put("cntrt_oppnt_type", form.getTmp_cntrt_oppnt_type());
				lomRq.put("cntrt_oppnt_respman", form.getTmp_cntrt_oppnt_respman());
				lomRq.put("cntrt_oppnt_telno", form.getTmp_cntrt_oppnt_telno());
				lomRq.put("cntrt_oppnt_email", form.getTmp_cntrt_oppnt_email());
				// 계약유형
				lomRq.put("region_gbn", form.getRegion_gbn());
			}
			// 의뢰정보
			lomRq.put("prev_cnsdreq_id", form.getPrev_cnsdreq_id());
			lomRq.put("plndbn_req_yn", form.getPlndbn_req_yn());

			lomRq.put("status", form.getStatus());
			// 최최 의뢰,재의뢰 , 최종본 의뢰 상태 설정
			form.setReq_status_title(considerationService.getReqStatus((String) lomRq.get("prev_cnsdreq_id"), (String) lomRq.get("plndbn_req_yn"), vo));
			// 최종본 , 계약 여러건인경우 필구 첨부파일 체크
			// if("Y".equals(vo.getPlndbn_req_yn()) && 1 >
			// Integer.parseInt(vo.getTab_cnt())){
			ListOrderedMap returnLom = considerationService.getFilevalidate(vo);
			form.setFile_validate((String) returnLom.get("fileValidate"));
			// }
			/*********************************************************
			 * ModelAndView
			 **********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + forwardURL);

			mav.addObject("lomRq", lomRq);
			mav.addObject("listDc", listDc);
			mav.addObject("listTs", listTs);
			mav.addObject("listRc", listRc);
			mav.addObject("command", form);
			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("tabConsMasterExecution() Exception !!");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("tabConsMasterExecution() Throwable !!");
		}
	}

	/**
	 * 계약 탭 클릭시 데이타 저장 master tb
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void insertContractMaster(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * 시스템 코드 및 사용자아이디
			 **********************************************************/
			ListOrderedMap returnLom = new ListOrderedMap();

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * 내역 등록
			 **********************************************************/

			returnLom = considerationService.insertConsideration(vo);

			ClmsDataUtil.debug("returnLom >>>" + returnLom);
			ClmsDataUtil.debug("vo.getCnsdreq_id() >>>" + vo.getCnsdreq_id());

			JSONObject jo = new JSONObject();
			jo.put("returnCd", returnLom.get("cd"));
			jo.put("returnCnsdReqId", returnLom.get("cnsdreq_id"));
			jo.put("returnCntrtId", returnLom.get("cntrt_id"));
			jo.put("returnMsg", returnLom.get("msg"));
			jo.put("exeStatus", returnLom.get("exe_status"));

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
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

	/**
	 * 재의뢰 , 최종본 의뢰 ( 이전 의뢰건 회신완료인경우 계약상세 페이지에서 사용 ) 이전의뢰 정보을 일괄 복사하여 재검토의뢰 또는
	 * 최종본의뢰 임시저장 상태로 변경
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void prevCnsdReqCopyConsideration(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String dup_div = "Y"; // 중복 의뢰 구분값

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			ClmsDataUtil.debugParamByRequest(vo, true);

			/*********************************************************
			 * 내역 등록
			 **********************************************************/
			// 재의뢰 , 최종본의뢰시 의뢰정보을 insert 하고 임시저장 상태로 변경후 수정 폼으로 이동 하게 전달
			if ("forwardReq".equals(form.getStatus()) || "forwardLast".equals(form.getStatus())) {

				// 2012.06.13 중복의뢰 방지 added by hanjihoon
				List<?> listPd = considerationService.preventDuplication(vo);
				ListOrderedMap lomPd = (ListOrderedMap) listPd.get(0);

				if ("N".equals((String) lomPd.get("dup_div"))) {
					dup_div = "N";

					vo.setPrev_cnsdreq_id(vo.getCnsdreq_id());// 이전의뢰아이디 설절
					vo.setRe_demndday(considerationService.getReDemndday());// 회신요청일
					vo.setReq_dt(DateUtil.getTime("yyyy-MM-dd"));// 의뢰일

					if ("forwardLast".equals(form.getStatus())) {
						vo.setPlndbn_req_yn("Y");
					} else {
						vo.setPlndbn_req_yn("N");
					}
					ListOrderedMap returnLom = considerationService.prevCnsdReqCopyConsideration(vo);
					vo.setCnsdreq_id((String) returnLom.get("cnsdreq_id"));
				}
			}

			ClmsDataUtil.debug("vo.getCnsdreq_id() >>>" + vo.getCnsdreq_id());

			JSONObject jo = new JSONObject();
			if ("N".equals(dup_div)) {
				jo.put("cnsdreqId", vo.getCnsdreq_id());
			} else {
				jo.put("cnsdreqId", "");
			}

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
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

	/**
	 * 계약 기본정보 상세보기
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView detailContractMaster(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Forward setting
			 **********************************************************/
			String forwardURL = "/WEB-INF/jsp/clm/manage/Consideration_inner_d.jsp";

			/*********************************************************
			 * Form & VO Binding
			 **********************************************************/
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			ModelAndView mav = new ModelAndView();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * Service
			 **********************************************************/
			List<?> listDc = considerationService.detailContractMaster(vo);

			ListOrderedMap lomRq = (ListOrderedMap) listDc.get(0);

			// 나모 잔여 태그 제거
			lomRq.put("cnsd_demnd_cont", StringUtil.convertNamoReplaceAll(StringUtil.bvl((String) lomRq.get("cnsd_demnd_cont"), "")));

			// 기대효과 <BR>antcptnefctANTCPTNEFCT
			lomRq.put("antcptnefct", StringUtil.convertEnterToBR((String) lomRq.get("antcptnefct")));
			// 지불/수분조건 <BR>PAYMENT_COND
			lomRq.put("payment_cond", StringUtil.convertEnterToBR((String) lomRq.get("payment_cond")));
			// 기타주요사항 <BR>ETC_MAIN_CONT
			lomRq.put("etc_main_cont", StringUtil.convertEnterToBR((String) lomRq.get("etc_main_cont")));
			//
			lomRq.put("cntrt_chge_demnd_cause", StringUtil.convertEnterToBR((String) lomRq.get("cntrt_chge_demnd_cause")));
			// 단가내역 요약cntrt_untprc_expl
			lomRq.put("cntrt_untprc_expl", StringUtil.convertEnterToBR(StringUtil.bvl((String) lomRq.get("cntrt_untprc_expl"), "")));
			// 책임한도 oblgt_lmt
			lomRq.put("oblgt_lmt", StringUtil.convertEnterToBR(StringUtil.bvl((String) lomRq.get("oblgt_lmt"), "")));
			// Special Condition spcl_cond
			lomRq.put("spcl_cond", StringUtil.convertEnterToBR(StringUtil.bvl((String) lomRq.get("spcl_cond"), "")));
			// loac_etc 준거법 상세
			lomRq.put("loac_etc", StringUtil.convertEnterToBR(StringUtil.bvl((String) lomRq.get("loac_etc"), "")));
			// dspt_resolt_mthd_det 분쟁해결방법상세
			lomRq.put("dspt_resolt_mthd_det", StringUtil.convertEnterToBR(StringUtil.bvl((String) lomRq.get("dspt_resolt_mthd_det"), "")));

			vo.setCnclsnpurps_bigclsfcn((String) lomRq.get("cnclsnpurps_bigclsfcn"));
			vo.setCnclsnpurps_midclsfcn((String) lomRq.get("cnclsnpurps_midclsfcn"));

			vo.setPlndbn_req_yn((String) lomRq.get("plndbn_req_yn"));
			vo.setCnsd_status((String) lomRq.get("cnsd_status"));

			ClmsDataUtil.debug(">>>>>>>>>>>>>>" + vo.getPlndbn_req_yn());
			ClmsDataUtil.debug(">>>>>>>>>>>>>>" + vo.getCnsd_status());

			List listTs = considerationService.listTypeSpclinfo(vo);// TN_CLM_TYPE_SPCLINFO
																	// 특화정보

			// 추진목적 및 배경
			lomRq.put("pshdbkgrnd_purps", StringUtil.convertBRToEnter(StringUtil.bvl((String) lomRq.get("pshdbkgrnd_purps"), "")));

			// 종합검토의견
			if (lomRq.get("cnsd_opnn") != null) {
				lomRq.put("cnsd_opnn", StringUtil.bvl((String) lomRq.get("cnsd_opnn"), ""));
			}
			// 최최 의뢰,재의뢰 , 최종본 의뢰 상태 설정
			form.setReq_status_title(considerationService.getReqStatus((String) lomRq.get("prev_cnsdreq_id"), (String) lomRq.get("plndbn_req_yn"), vo));
			form.setPlndbn_req_yn(vo.getPlndbn_req_yn());

			ListOrderedMap returnLom = considerationService.getFilevalidate(vo);
			form.setFile_validate((String) returnLom.get("fileValidate"));

			ArrayList<?> lomTs = (ArrayList<?>) listTs;

			/*********************************************************
			 * ModelAndView
			 **********************************************************/
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + forwardURL);

			mav.addObject("lomRq", lomRq);
			mav.addObject("lomTs", lomTs); // 특화속성
			mav.addObject("listDc", listDc);
			mav.addObject("listTs", listTs);
			mav.addObject("command", form);

			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("tabConsMasterExecution() Exception !!");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("tabConsMasterExecution() Throwable !!");
		}
	}

	/**
	 * 계약관리 의뢰 테이블 데이타 저장
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void insertConsideration(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			ListOrderedMap returnLom = new ListOrderedMap();
			ListOrderedMap requiredLom = new ListOrderedMap();

			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			form.setAttr_cd(StringUtil.bvl(form.getAttr_cd(), ""));
			vo.setAttr_cd(StringUtil.bvl(form.getAttr_cd(), ""));

			form.setAttr_cont(StringUtil.bvl(form.getAttr_cont(), ""));
			vo.setAttr_cont(StringUtil.bvl(form.getAttr_cont(), ""));

			/*
			 * 체결 후 등록 입력 후 update 값
			 */
			vo.setRegNotiday_before(form.getExprt_notiday()); // 만료시전 알림일
			vo.setRegSeal_ffmtman_id(form.getSeal_ffmtman_id());
			vo.setRegSeal_ffmtman_nm(form.getSeal_ffmtman_nm());
			vo.setRegSeal_ffmtman_jikgup_nm(form.getSeal_ffmtman_jikgup_nm());
			vo.setRegSeal_ffmt_dept_cd(form.getSeal_ffmt_dept_cd());
			vo.setRegSeal_ffmt_dept_nm(form.getSeal_ffmt_dept_nm());

			vo.setStd_cnslt_no(StringUtil.nvl((String) vo.getStd_cnslt_no(), ""));
			/*********************************************************
			 * Service clm.manage.requiredInfo
			 **********************************************************/
			// 2014-04-14 Kevin added.
			if (request.getParameter("gerpCostType") != null) {
				vo.setGERPCostType(request.getParameter("gerpCostType").toString());
			}
			if (request.getParameter("gerpContractType") != null) {
				vo.setGERPContractType(request.getParameter("gerpContractType").toString());
			}
			if (request.getParameter("gerpDivCode") != null) {
				vo.setGERPDivCode(request.getParameter("gerpDivCode").toString());
			}

			ClmsDataUtil.debug("insertConsideration  vo.getSubmit_status() >>>>>" + vo.getSubmit_status());

			// TODO
			// Need to deploy after confirmation by EHQ.
			// 2014-07-17 Kevin added. Save user configuration information which
			// whether to show or hide basic information.
			/*
			 * String userConfig = request.getParameter("toggleConfigVal");
			 * if(userConfig != null && !userConfig.equals("")){
			 * response.addCookie(new Cookie(COOKIE_BASICINFO_VIEW,
			 * userConfig)); }
			 */

			String pageGbn = "";
			pageGbn = StringUtil.nvl((String) form.getPageGbn(), "");
			form.setPageGbn(StringUtil.nvl((String) form.getPageGbn(), ""));

			if ("registration".equals(pageGbn.replace(",", ""))) { // 계약 후 체결 일
																	// 경우 여기를 타야
																	// 된다.
				returnLom = considerationService.insertConsiderationReg(vo);
			} else {
				returnLom = considerationService.insertConsideration(vo);
			}

			vo.setCnsdreq_id((String) returnLom.get("cnsdreq_id")); // 의뢰테이블 저장

			/********************************************************
			 * a멀티 계약건 필수 입력 항목 체크 후 결과 리턴
			 ********************************************************/
			if (!"".equals(vo.getCnsdreq_id()) && !"save".equals(vo.getSubmit_status()) && "".equals(vo.getStd_cnslt_no())) {

				requiredLom = considerationService.searchRequired(vo);

				if ("OK".equals(requiredLom.get("ret_msg"))) {
					/********************************************************
					 * 임시저장 이후 검토의뢰 인경우 최종본 의뢰인 경우 상태값 변경
					 ********************************************************/
					// 상태값 변경
					considerationService.insertConsiderationStatus(vo);

					HashMap<String, String> hm = new HashMap<String, String>();
					hm.put("main_title", messageSource.getMessage("clm.page.field.mailsend.requiredTitle", null, new RequestContext(request).getLocale()));
					hm.put("reqman_info", vo.getSession_user_nm() + "/" + vo.getSession_jikgup_nm() + "/" + vo.getSession_dept_nm());
					hm.put("cnsdreq_id", vo.getCnsdreq_id());
					hm.put("cntrt_id", vo.getCntrt_id());
					hm.put("locale", vo.getSession_user_locale());

					hm.put("submit_status", vo.getSubmit_status());

					if (vo.getSubmit_status().equals("req")) {
						if ("registration".equals(pageGbn)) {
							request.setAttribute("f_cnsdreq_id", vo.getCnsdreq_id()); // 의뢰테이블
																						// 저장
							makeApprovalHtmlDirect2(request, response);
						} else {
							request.setAttribute("f_cnsdreq_id", vo.getCnsdreq_id()); // 의뢰테이블
																						// 저장
							// 의뢰상신건에 대한 정상결재상신후 상태코드 업데이트 처리
							// UPDATE TN_CLM_CONTRACT_MASTER SET DEPTH_STATUS =
							// 'C02601', MOD_DT = GETDATE() WHERE 1=1 AND
							// DEL_YN = 'N' AND DEPTH_STATUS NOT IN ('C02603')
							// AND CNTRT_ID IN ( '20151126514360014' )
							modifyConsiderationStatus(request, response);
						}
						// 1. Create Request : Request for Contract Review has
						// been submitted by the requestor
						hm.put("email.subject", messageSource.getMessage("selms.email.contract.review.request.subject", null, new RequestContext(request).getLocale()));
						hm.put("main_title", messageSource.getMessage("selms.email.contract.review.request.contents.title", null, new RequestContext(request).getLocale()));
						hm.put("mail_content", messageSource.getMessage("selms.email.contract.review.request.contents.body", null, new RequestContext(request).getLocale()));
						hm.put("request", "Legal"); // Sungwoo added 2014-06-26
						mailSendService.sendReqInfoMail(hm);
					} else if (vo.getSubmit_status().equals("again") && vo.getRespman_id() == null) {
						// 1. Create Request : Request for Contract Review has
						// been submitted by the requestor
						hm.put("email.subject", messageSource.getMessage("selms.email.contract.review.requestAgain.subject", null, new RequestContext(request).getLocale()));
						hm.put("main_title", messageSource.getMessage("selms.email.contract.review.requestAgain.contents.title", null, new RequestContext(request).getLocale()));
						hm.put("mail_content", messageSource.getMessage("selms.email.contract.review.requestAgain.contents.body", null, new RequestContext(request).getLocale()));
						hm.put("request", "re_request"); // Sungwoo added
															// 2014-06-26
						mailSendService.sendReqInfoMail(hm);
					} else if (vo.getSubmit_status().equals("again")) { // Sungwoo
																		// added
																		// 2014-06-26
																		// Request
																		// -
																		// Again
						hm.put("email.subject", messageSource.getMessage("selms.email.contract.review.requestAgain.subject", null, new RequestContext(request).getLocale()));
						hm.put("main_title", messageSource.getMessage("selms.email.contract.review.requestAgain.contents.title", null, new RequestContext(request).getLocale()));
						hm.put("mail_content", messageSource.getMessage("selms.email.contract.review.requestAgain.contents.body", null, new RequestContext(request).getLocale()));
						hm.put("request", "re_request"); // Sungwoo added
															// 2014-06-26
						mailSendService.sendReqInfoMail(hm);
					}
					requiredLom.put("result", "Y");
					requiredLom.put("msg", messageSource.getMessage("clm.page.field.consideration.modifyAgainReConsideration01", null, new RequestContext(request).getLocale()));// 정상처리
																																													// 되었습니다.
				} else {// 필수 체크에 걸림
					requiredLom.put("result", "N");
					requiredLom.put("msg", messageSource.getMessage("clm.page.field.consideration.insertConsideration02", null, new RequestContext(request).getLocale()));// 다른
																																											// 계약건에
																																											// 입력되지
																																											// 않은
																																											// 필수
																																											// 입력
																																											// 항목이
																																											// 존재
																																											// 합니다.
				}
			} else {
				requiredLom.put("result", "Y");
				requiredLom.put("msg", messageSource.getMessage("clm.page.field.consideration.modifyAgainReConsideration01", null, new RequestContext(request).getLocale())); // 정상처리
																																												// 되었습니다.

				// 표준계약서일 경우 해당사업부 관리자에게 메일 전송
				if (!"".equals(vo.getStd_cnslt_no()) && "req".equals(vo.getSubmit_status())) {
					HashMap<String, String> hm = new HashMap<String, String>();
					hm.put("main_title", messageSource.getMessage("clm.page.field.mailsend.requiredTitle", null, new RequestContext(request).getLocale()));
					hm.put("mail_content", messageSource.getMessage("clm.page.field.consideration.insertConsideration04", null, new RequestContext(request).getLocale()));
					hm.put("reqman_info", vo.getSession_user_nm() + "/" + vo.getSession_jikgup_nm() + "/" + vo.getSession_dept_nm());
					hm.put("cnsdreq_id", vo.getCnsdreq_id());
					hm.put("cntrt_id", vo.getCntrt_id());
					hm.put("locale", vo.getSession_user_locale());

					mailSendService.sendAcceptInfoMail(hm);
				}
			}

			requiredLom.put("cnsdreq_id", vo.getCnsdreq_id());
			requiredLom.put("cntrt_id", vo.getCntrt_id());

			ClmsDataUtil.debug("=======================");
			ClmsDataUtil.debug(requiredLom);
			ClmsDataUtil.debug("=======================");

			JSONObject jo = new JSONObject();
			jo.put("ret_msg", requiredLom.get("ret_msg"));
			jo.put("returnValue", requiredLom.get("result"));
			jo.put("returnApproval", "");
			jo.put("msg", requiredLom.get("msg"));
			jo.put("cnsdreq_id", requiredLom.get("cnsdreq_id"));
			jo.put("cntrt_id", requiredLom.get("cntrt_id"));
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
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

	/**
	 * 신규 의뢰 시 폼 기본 데이타 설정
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardInsertConsideration(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Forward setting
			 **********************************************************/
			String forwardURL = "";

			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			ListOrderedMap lomRq = new ListOrderedMap();
			bind(request, form);
			bind(request, vo);

			form.setPageGbn(StringUtil.nvl((String) form.getPageGbn(), ""));

			if ("registration".equals(form.getPageGbn())) {
				forwardURL = "/WEB-INF/jsp/clm/manage/RegConsideration_i.jsp";
			} else {
				forwardURL = "/WEB-INF/jsp/clm/manage/Consideration_i.jsp";
			}

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			/*********************************************************
			 * Service
			 **********************************************************/
			lomRq.put("total_cnt", "1"); // lomRq.total_cnt
			lomRq.put("req_dept", vo.getSession_dept_cd());
			lomRq.put("req_dept_nm", vo.getSession_dept_nm());
			lomRq.put("reqman_jikgup_nm", vo.getSession_jikgup_nm()); // 직급
			lomRq.put("reqman_id", vo.getSession_user_id());
			lomRq.put("reqman_nm", vo.getSession_user_nm());
			lomRq.put("req_dt", DateUtil.getTime("yyyy-MM-dd"));
			lomRq.put("re_dt", DateUtil.getTime("yyyy-MM-dd"));

			String curYMD = DateUtil.today();
			// 3일후로 설정
			// Week : 1[일요일], 2[월요일], 3[화요일], 4[수요일], 5[목요일], 6[금요일], 7[토요일]
			if (DateUtil.isDayOfWeek(curYMD, 7)) {
				// 토요일이면 1일 증가 : 3 + 1 = 4
				lomRq.put("re_demndday", DateUtil.formatDate(DateUtil.addDays(DateUtil.today(), 4), "-"));
			} else if (DateUtil.isDayOfWeek(curYMD, 6) || DateUtil.isDayOfWeek(curYMD, 5) || DateUtil.isDayOfWeek(curYMD, 4)) {
				// 수, 목, 금요일이면 2일 증가 : 3 + 2 = 5
				lomRq.put("re_demndday", DateUtil.formatDate(DateUtil.addDays(DateUtil.today(), 5), "-"));
			} else {
				// 그외 (일, 월, 화요일)이면 증가 없음 : 3 + 0 = 3
				lomRq.put("re_demndday", DateUtil.formatDate(DateUtil.addDays(DateUtil.today(), 3), "-"));
			}

			lomRq.put("rn", "1");
			lomRq.put("cntrt_id", considerationService.getId());
			lomRq.put("cnsdreq_id", ""); // 2011.11.01 의뢰 아이디 새 포맷으로 변경
			lomRq.put("cntrtperiod_startday", "");
			lomRq.put("cntrtperiod_endday", "");
			lomRq.put("bfhdcstn_apbtday", DateUtil.getTime("yyyy-MM-dd"));
			lomRq.put("cnsd_demnd_cont", "");
			lomRq.put("status", "forwardInsert");

			lomRq.put("prev_cnsdreq_id", "");
			lomRq.put("plndbn_req_yn", "N");

			// 최최 의뢰,재의뢰 , 최종본 의뢰 상태 설정
			form.setReq_status_title(considerationService.getReqStatus((String) lomRq.put("prev_cnsdreq_id", ""), (String) lomRq.put("plndbn_req_yn", "N"), vo));

			ArrayList listDc = new ArrayList();
			ArrayList listTs = new ArrayList();
			ArrayList listRc = new ArrayList();
			ArrayList listCa = new ArrayList();

			listDc.add(lomRq);

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + mav.getViewName());

			mav.addObject("lomRq", lomRq);
			mav.addObject("listDc", listDc);
			mav.addObject("listTs", listTs);
			mav.addObject("listRc", listRc);
			mav.addObject("listCa", listCa);
			mav.addObject("command", form);

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
	 * 재검토의뢰 정보 임시저장 / 검토의뢰
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void modifyAgainReqConsideration(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * 내역 등록
			 **********************************************************/
			ListOrderedMap returnLom = considerationService.modifyAgainReConsideration(vo);

			ClmsDataUtil.debug("returnLom >>>" + returnLom);
			ClmsDataUtil.debug("vo.getCnsdreq_id() >>>" + vo.getCnsdreq_id());
			ClmsDataUtil.debug("==========================");

			JSONObject jo = new JSONObject();
			jo.put("returnCd", returnLom.get("cd"));
			jo.put("returnVal", returnLom.get("val"));
			jo.put("returnMsg", returnLom.get("msg"));

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
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

	/**
	 * 재 검토 의뢰 작성 폼
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardAgainReqConsideration(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			String forwardURL = "/WEB-INF/jsp/clm/manage/Consideration_again.jsp";
			ListOrderedMap lomCh = new ListOrderedMap();
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			ModelAndView mav = new ModelAndView();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * Service++
			 **********************************************************/
			List<?> listDc = considerationService.detailConsideration(vo);

			ListOrderedMap lomRq = (ListOrderedMap) listDc.get(0);

			// 나모 잔여 태그 제거
			lomRq.put("cnsd_demnd_cont", StringUtil.convertNamoReplaceAll(StringUtil.bvl((String) lomRq.get("cnsd_demnd_cont"), "")));
			lomRq.put("total_cnt", Integer.toString(listDc.size()));

			// 의뢰테이블에 조인된 마스터 테이블에 계약1번 데이타
			vo.setCntrt_id((String) lomRq.get("cntrt_id"));
			vo.setCnclsnpurps_bigclsfcn((String) lomRq.get("cnclsnpurps_bigclsfcn"));
			vo.setCnclsnpurps_midclsfcn((String) lomRq.get("cnclsnpurps_midclsfcn"));
			List<?> listTs = considerationService.listTypeSpclinfo(vo);// TN_CLM_TYPE_SPCLINFO
																		// 특화정보
			List<?> listCa = considerationService.listContractAuthreq(vo);// 권한요청자
																			// -관련자
			List<?> listCh = considerationService.listCnsdreqHold(vo); // 보류사유
			if (!listCh.isEmpty()) {
				lomCh = (ListOrderedMap) listCh.get(0);
				lomCh.put("hold_cause", StringUtil.convertEnterToBR((String) lomCh.get("hold_cause")));
			}

			// 회신요청일 설정 데이타가 없으면 + 3일로 설정
			if ("".equals((String) lomRq.get("re_demndday"))) {
				String curYMD = DateUtil.today();
				// 3일후로 설정
				// Week : 1[일요일], 2[월요일], 3[화요일], 4[수요일], 5[목요일], 6[금요일], 7[토요일]
				if (DateUtil.isDayOfWeek(curYMD, 7)) {
					// 토요일이면 1일 증가 : 3 + 1 = 4
					lomRq.put("re_demndday", DateUtil.formatDate(DateUtil.addDays(DateUtil.today(), 4), "-"));
				} else if (DateUtil.isDayOfWeek(curYMD, 6) || DateUtil.isDayOfWeek(curYMD, 5) || DateUtil.isDayOfWeek(curYMD, 4)) {
					// 수, 목, 금요일이면 2일 증가 : 3 + 2 = 5
					lomRq.put("re_demndday", DateUtil.formatDate(DateUtil.addDays(DateUtil.today(), 5), "-"));
				} else {
					// 그외 (일, 월, 화요일)이면 증가 없음 : 3 + 0 = 3
					lomRq.put("re_demndday", DateUtil.formatDate(DateUtil.addDays(DateUtil.today(), 3), "-"));
				}
			}

			// 변경사항
			lomRq.put("plndbn_chge_cont", StringUtil.convertEnterToBR(StringUtil.nvl((String) lomRq.get("plndbn_chge_cont"), "")));

			// 최최 의뢰,재의뢰 , 최종본 의뢰 상태 설정
			form.setReq_status_title(considerationService.getReqStatus((String) lomRq.get("prev_cnsdreq_id"), (String) lomRq.get("plndbn_req_yn"), vo));

			// master_cnt, master_id
			ArrayList<?> lomTs = (ArrayList<?>) listTs;

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + mav.getViewName());

			mav.addObject("lomRq", lomRq);

			mav.addObject("lomTs", lomTs); // 특화속성
			mav.addObject("listDc", listDc);
			mav.addObject("listTs", listTs);
			mav.addObject("listCa", listCa);
			mav.addObject("lomCh", lomCh); // 보류사유
			mav.addObject("command", form);

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
	 * 계약건 수정 폼으로 전달
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardModifyConsideration(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			ClmsDataUtil.debug("forwardModifyConsideration   >>>>   ");
			String forwardURL = "";
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			ListOrderedMap lomCh = new ListOrderedMap();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			String pageGbn = "";

			form.setPageGbn(StringUtil.nvl((String) form.getPageGbn(), ""));

			pageGbn = form.getPageGbn();

			if ("registration".equals(pageGbn)) {
				forwardURL = "/WEB-INF/jsp/clm/manage/RegConsideration_i.jsp";
			} else {
				forwardURL = "/WEB-INF/jsp/clm/manage/Consideration_i.jsp";
			}

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			List<?> listDc = null;

			if ("registration".equals(pageGbn)) {
				listDc = considerationService.detailConsiderationReg(vo);
			} else {
				listDc = considerationService.detailConsideration(vo);
			}

			ListOrderedMap lomRq = (ListOrderedMap) listDc.get(0);
			lomRq.put("total_cnt", Integer.toString(listDc.size()));
			lomRq.put("cnsd_demnd_cont", StringUtil.convertCharsToHtml((String) lomRq.get("cnsd_demnd_cont"))); // 검토_요청_내용
			lomRq.put("pshdbkgrnd_purps", (StringUtil.bvl((String) lomRq.get("pshdbkgrnd_purps"), ""))); // 추진배경_목적
			lomRq.put("status", form.getStatus());

			String curYMD = DateUtil.today();

			// 3일후로 설정
			// Week : 1[일요일], 2[월요일], 3[화요일], 4[수요일], 5[목요일], 6[금요일], 7[토요일]
			if (DateUtil.isDayOfWeek(curYMD, 7)) {
				// 토요일이면 1일 증가 : 3 + 1 = 4
				lomRq.put("re_demndday", DateUtil.formatDate(DateUtil.addDays(DateUtil.today(), 4), "-"));
			} else if (DateUtil.isDayOfWeek(curYMD, 6) || DateUtil.isDayOfWeek(curYMD, 5) || DateUtil.isDayOfWeek(curYMD, 4)) {
				// 수, 목, 금요일이면 2일 증가 : 3 + 2 = 5
				lomRq.put("re_demndday", DateUtil.formatDate(DateUtil.addDays(DateUtil.today(), 5), "-"));
			} else {
				// 그외 (일, 월, 화요일)이면 증가 없음 : 3 + 0 = 3
				lomRq.put("re_demndday", DateUtil.formatDate(DateUtil.addDays(DateUtil.today(), 3), "-"));
			}

			// 의뢰테이블에 조인된 마스터 테이블에 계약1번 데이타
			vo.setCntrt_id((String) lomRq.get("cntrt_id"));
			vo.setCnclsnpurps_bigclsfcn((String) lomRq.get("cnclsnpurps_bigclsfcn"));
			vo.setCnclsnpurps_midclsfcn((String) lomRq.get("cnclsnpurps_midclsfcn"));
			List<?> listTs = considerationService.listTypeSpclinfoMod(vo);// TN_CLM_TYPE_SPCLINFO
																			// 특화정보

			vo.setStatus("consider");

			List<?> listCa = null;
			List<?> listCh = null;

			if ("registration".equals(pageGbn)) {

				vo.setStatus("registration");

				listCa = considerationService.listContractAuthreq(vo);// 권한요청자
																		// -관련자
				listCh = considerationService.listCnsdreqHold(vo); // 보류사유

			} else {
				listCa = considerationService.listContractAuthreq(vo);// 권한요청자
																		// -관련자
				listCh = considerationService.listCnsdreqHold(vo); // 보류사유

			}

			if (!listCh.isEmpty()) {
				lomCh = (ListOrderedMap) listCh.get(0);
			}

			// 최최 의뢰,재의뢰 , 최종본 의뢰 상태 설정
			form.setReq_status_title(considerationService.getReqStatus((String) lomRq.get("prev_cnsdreq_id"), (String) lomRq.get("plndbn_req_yn"), vo));

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + mav.getViewName());

			mav.addObject("lomRq", lomRq);

			mav.addObject("listDc", listDc);
			mav.addObject("listTs", listTs);
			mav.addObject("listCa", listCa);
			// lomCh
			mav.addObject("lomCh", lomCh);
			mav.addObject("command", form);

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
	 * 첨부파일 멀티 테스트
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView saveFileUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			String forwardURL = "";

			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();

			bind(request, form);
			bind(request, vo);

			ClmsDataUtil.debug("******************************************************");
			ClmsDataUtil.debugParamByRequest(request, true);
			ClmsDataUtil.debug("******************************************************");

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			int result = considerationService.saveFileUpload(vo);

			String returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + mav.getViewName());

			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

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
	 * 계약 상세내역
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView detailConsideration(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			String forwardURL = "";
			ListOrderedMap lomCh = new ListOrderedMap();
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			ModelAndView mav = new ModelAndView();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			form.setPageGbn(StringUtil.nvl((String) form.getPageGbn(), ""));

			if ("registration".equals(form.getPageGbn())) {

				forwardURL = "/WEB-INF/jsp/clm/manage/RegConsideration_d.jsp";
			} else {

				forwardURL = "/WEB-INF/jsp/clm/manage/Consideration_d.jsp";
			}

			/*********************************************************
			 * Service++
			 **********************************************************/

			List listDc = null;

			if ("registration".equals(form.getPageGbn())) {
				listDc = considerationService.detailConsiderationReg(vo);
			} else {
				listDc = considerationService.detailConsideration(vo);
			}

			ListOrderedMap lomRq = (ListOrderedMap) listDc.get(0);

			// 나모 잔여 태그 제거
			lomRq.put("cnsd_demnd_cont", StringUtil.convertNamoReplaceAll(StringUtil.bvl((String) lomRq.get("cnsd_demnd_cont"), "")));
			lomRq.put("total_cnt", Integer.toString(listDc.size()));

			// 의뢰테이블에 조인된 마스터 테이블에 계약1번 데이타
			vo.setCntrt_id((String) lomRq.get("cntrt_id"));
			vo.setCnclsnpurps_bigclsfcn((String) lomRq.get("cnclsnpurps_bigclsfcn"));
			vo.setCnclsnpurps_midclsfcn((String) lomRq.get("cnclsnpurps_midclsfcn"));
			List<?> listTs = considerationService.listTypeSpclinfo(vo);// TN_CLM_TYPE_SPCLINFO
																		// 특화정보

			ArrayList<Object> deferList = new ArrayList<Object>();

			String arr_cntrt_id[] = new String[listDc.size()];
			for (int j = 0; j < listDc.size(); j++) {
				int k = j + 1;

				ListOrderedMap lomRq_cntrt_id = (ListOrderedMap) listDc.get(j);
				ClmsDataUtil.debug("(String)lomRq_cntrt_id.get()  : " + (String) lomRq_cntrt_id.get("cntrt_id"));
				arr_cntrt_id[j] = (String) lomRq_cntrt_id.get("cntrt_id");
				vo.setArr_cntrt_id(arr_cntrt_id);
				vo.setStatus(Integer.toString(k));

				// 보류 / 보류 해제 버튼 활성화을 위한
				deferList.add(lomRq_cntrt_id.get("depth_status"));
			}

			if (deferList.contains("C02607")) { // 보류상태
				lomRq.put("defer_btn", "cancel");
			} else {
				lomRq.put("defer_btn", "defer");
			}

			ClmsDataUtil.debug("arr_cntrt_idarr_cntrt_idarr_cntrt_id: " + arr_cntrt_id);

			vo.setStatus("consider");
			List<?> listCa = considerationService.listContractAuthreq(vo);// 권한요청자
																			// -관련자
			List<?> listCh = considerationService.listCnsdreqHold(vo); // 보류사유
			if (!listCh.isEmpty()) {
				lomCh = (ListOrderedMap) listCh.get(0);
				lomCh.put("hold_cause", StringUtil.convertEnterToBR((String) lomCh.get("hold_cause")));
			}

			// 변경사항
			lomRq.put("plndbn_chge_cont", StringUtil.convertEnterToBR(StringUtil.nvl((String) lomRq.get("plndbn_chge_cont"), "")));

			// 최최 의뢰,재의뢰 , 최종본 의뢰 상태 설정
			// 2012.03.07 타이틀을 목록의 상태명으로 나오도록 수정 added by hanjihoon
			form.setReq_status_title(considerationService.getReqStatus((String) lomRq.get("prev_cnsdreq_id"), (String) lomRq.get("plndbn_req_yn"), vo));

			ArrayList<?> lomTs = (ArrayList<?>) listTs;

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + mav.getViewName());

			mav.addObject("lomRq", lomRq);

			mav.addObject("lomTs", lomTs); // 특화속성
			// mav.addObject("lomRc", lomRc); //연관계약

			mav.addObject("listDc", listDc);
			mav.addObject("listTs", listTs);
			// mav.addObject("listRc", listRc);
			mav.addObject("listCa", listCa);
			mav.addObject("lomCh", lomCh); // 보류사유
			mav.addObject("command", form);

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
	 * 보류 /drop 팝업
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView opnnConsideration(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			// 체결에서 drop시킬때는 prcs_depth가 틀리기 때문에 의뢰쪽 prcs_depth를 쓸수 없다.
			// 체결에서는 화면에서 바로 받아온다.
			String prcsDepth = StringUtil.bvl(request.getParameter("prcsDepth"), "");
			String forwardURL = "/WEB-INF/jsp/clm/manage/Consideration_opnn_p.jsp";
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			ClmsDataUtil.debug("opnnConsideration   vo.getCnsdreq_id() >>>  " + vo.getCnsdreq_id());
			ClmsDataUtil.debug("opnnConsideration   vo.getCntrt_id() >>>  " + vo.getCntrt_id());
			ClmsDataUtil.debug("opnnConsideration   vo.getArr_cntrt_id() >>>  " + vo.getArr_cntrt_id());

			// 체결에서 넘어온 건임
			if ("C02502".equals(prcsDepth)) {
				vo.setPrcs_depth("C02502");
			} else {
				vo.setPrcs_depth("C02501");
			}

			List listDc = considerationService.detailConsideration2(vo);
			ListOrderedMap lomRq = null;
			if (listDc != null && listDc.size() > 0) {
				lomRq = (ListOrderedMap) listDc.get(0);
			}

			form.setCnsdreq_id(vo.getCnsdreq_id());
			form.setCntrt_id(vo.getCntrt_id());

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + mav.getViewName());

			mav.addObject("lomRq", lomRq);
			mav.addObject("listDc", listDc);
			mav.addObject("command", form);

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
	 * saveCancelRequest 작성중의 의로건 취소 하기 이전 의뢰의 회신 완료 상태로 진행
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void saveCancelRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			ListOrderedMap returnMap = new ListOrderedMap();
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			JSONObject jo = new JSONObject();
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/

			ClmsDataUtil.debug("   saveCancelRequest  >>> vo.getCnsdreq_id() 1111   " + vo.getCnsdreq_id());

			/*********************************************************
			 * Service
			 **********************************************************/
			returnMap = considerationService.deleteDropDefer(vo);

			ClmsDataUtil.debug("returnVal >>>" + returnMap.get("cd"));

			jo.put("returnCd", returnMap.get("cd"));
			jo.put("returnMsg", returnMap.get("msg"));

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
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

	/**
	 * 검토의뢰 보류 해제
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void deferCancelRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			ListOrderedMap returnMap = new ListOrderedMap();
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			JSONObject jo = new JSONObject();
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			ClmsDataUtil.debug("   deferRequest  >>> vo.getCnsdreq_id() 1111   " + vo.getCnsdreq_id());

			/*********************************************************
			 * Service
			 **********************************************************/
			returnMap = considerationService.deleteDropDefer(vo);

			ClmsDataUtil.debug("returnVal >>>" + returnMap.get("cd"));
			jo.put("returnCd", returnMap.get("cd"));
			jo.put("returnMsg", returnMap.get("msg"));

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
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

	/**
	 * 검토의뢰 보류
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void deferRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			ListOrderedMap returnMap = new ListOrderedMap();
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			JSONObject jo = new JSONObject();
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			vo.setDel_id(vo.getSession_user_id());
			vo.setDel_nm(vo.getSession_user_nm());
			vo.setDel_yn("Y");

			/*********************************************************
			 * Service
			 **********************************************************/
			returnMap = considerationService.deleteDropDefer(vo);

			ClmsDataUtil.debug("returnVal >>>" + returnMap.get("cd"));
			jo.put("returnCd", returnMap.get("cd"));
			jo.put("returnMsg", returnMap.get("msg"));
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
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

	/**
	 * 보류 드랍시 작성된 사유 조회
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void detailDropDefer(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * 시스템 코드 및 사용자아이디
			 **********************************************************/
			List<?> listOpnn = null;
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			vo.setDel_id(vo.getSession_user_id());
			vo.setDel_nm(vo.getSession_user_nm());
			vo.setDel_yn("Y");

			ClmsDataUtil.debug("   detailDropDefer  >>> vo.getCnsdreq_id() 1111   " + vo.getCnsdreq_id());
			ClmsDataUtil.debug("   detailDropDefer  >>> getCntrt_id() 1111   " + vo.getCntrt_id());
			/*********************************************************
			 * 내역 등록
			 **********************************************************/
			listOpnn = considerationService.detailDropDefer(vo);

			ListOrderedMap lomOpnn = (ListOrderedMap) listOpnn.get(0);

			if ("deferRequest".equals(vo.getSubmit_status())) {// 의뢰 보류 사유
				lomOpnn.put("opnn", StringUtil.convertEnterToBR((String) lomOpnn.get("hold_cause")));
			} else if ("dropContract".equals(vo.getSubmit_status())) {// 계약 드랍
																		// 사유
				lomOpnn.put("opnn", StringUtil.convertEnterToBR((String) lomOpnn.get("cntrt_chge_demnd_cause")));
			}

			ClmsDataUtil.debug("returnVal >>>" + listOpnn);

			JSONObject jo = new JSONObject();
			jo.put("returnVal", (String) lomOpnn.get("opnn"));

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
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

	/**
	 * Drop 의뢰건
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dropRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			ListOrderedMap returnMap = new ListOrderedMap();
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			JSONObject jo = new JSONObject();
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			vo.setDel_id(vo.getSession_user_id());
			vo.setDel_nm(vo.getSession_user_nm());
			vo.setDel_yn("Y");

			ClmsDataUtil.debug("   dropRequest  >>> vo.getCntrt_id() 1111   " + vo.getCntrt_id());
			/*********************************************************
			 * 내역 등록
			 **********************************************************/
			returnMap = considerationService.deleteDropDefer(vo);

			ClmsDataUtil.debug("returnVal >>>" + returnMap.get("cd"));
			jo.put("returnCd", returnMap.get("cd"));
			jo.put("returnMsg", returnMap.get("msg"));

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
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

	/**
	 * 검토의뢰 삭제
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void deleteRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			ListOrderedMap returnMap = new ListOrderedMap();
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			JSONObject jo = new JSONObject();
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			vo.setDel_id(vo.getSession_user_id());
			vo.setDel_nm(vo.getSession_user_nm());
			vo.setDel_yn("Y");
			/*********************************************************
			 * 내역 등록
			 **********************************************************/
			returnMap = considerationService.deleteDropDefer(vo);

			ClmsDataUtil.debug("returnVal >>>" + returnMap.get("cd"));

			jo.put("returnCd", returnMap.get("cd"));
			jo.put("returnMsg", returnMap.get("msg"));

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
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

	/**
	 * 계약건삭제
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void deleteContract(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			ListOrderedMap returnMap = new ListOrderedMap();
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			JSONObject jo = new JSONObject();
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			vo.setDel_id(vo.getSession_user_id());
			vo.setDel_nm(vo.getSession_user_nm());
			vo.setDel_yn("Y");

			ClmsDataUtil.debug("   deleteContract  >>> vo.getCntrt_id() 1111   " + vo.getCntrt_id());
			/*********************************************************
			 * 내역 등록
			 **********************************************************/
			returnMap = considerationService.deleteDropDefer(vo);

			ClmsDataUtil.debug("returnVal >>>" + returnMap.get("cd"));
			jo.put("returnCd", returnMap.get("cd"));
			jo.put("returnMsg", returnMap.get("msg"));

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
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

	/**
	 * 계약건 drop
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dropContract(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			ListOrderedMap returnMap = new ListOrderedMap();
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			JSONObject jo = new JSONObject();
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			vo.setDel_id(vo.getSession_user_id());
			vo.setDel_nm(vo.getSession_user_nm());
			vo.setDel_yn("Y");

			ClmsDataUtil.debug("   dropContract  >>> vo.getCntrt_id() 1111   " + vo.getCntrt_id());
			/*********************************************************
			 * 내역 등록
			 **********************************************************/
			returnMap = considerationService.deleteDropDefer(vo);

			ClmsDataUtil.debug("returnVal >>>" + returnMap.get("cd"));

			jo.put("returnCd", returnMap.get("cd"));
			jo.put("returnMsg", returnMap.get("msg"));

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
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

	/**
	 * 연관계약 insert/delete
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void actionRelationContract(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * 시스템 코드 및 사용자아이디
			 **********************************************************/
			int returnVal = -1;
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();

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
			returnVal = considerationService.actionRelationContract(vo);

			JSONObject jo = new JSONObject();
			jo.put("returnVal", returnVal);

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
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

	/**
	 * 연관계약 정보 select
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void listRelationContract(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			// 계약 id 전달
			HashMap<?, ?> result = considerationService.listRelationContract(vo);

			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print((String) result.get("contRc"));
			out.flush();
			out.close();

		} catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale()));

			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale()));

			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		}
	}

	/**
	 * 특화속성 내용 표시 하기
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void listSpecialAttr(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/**
			 * 파라미터 셋팅
			 */
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("CNCLSNPURPS_BIGCLSFCN", StringUtil.bvl((String) request.getParameter("bigclsfcn"), ""));
			map.put("CNCLSNPURPS_MIDCLSFCN", StringUtil.bvl((String) request.getParameter("midclsfcn"), ""));

			Locale locale1 = new RequestContext(request).getLocale();
			String last_locale = StringUtil.bvl(locale1.getLanguage(), "en");
			map.put("LAST_LOCALE", last_locale);
			String result = considerationService.listSpecialAttr(map);

			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();

		} catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale()));

			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale()));

			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		}
	}

	/**
	 * 결재관련
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void makeApprovalHtmlDirect(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);
			String sysCd = (String) session.getAttribute("secfw.session.sys_cd");
			String userId = (String) session.getAttribute("secfw.session.user_id");
			String strUrl = (request.getRequestURL()).toString();
			String strAttachUrl = strUrl.substring(0, strUrl.indexOf("/", 7) + 1) + "clms/common/clmsfile.do?method=doClmsDownload&file_id=";
			ApprovalVO apprVo = new ApprovalVO();
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();

			bind(request, form);
			bind(request, vo);

			String f_cnsdreq_id = (String) request.getAttribute("f_cnsdreq_id"); // 최초
																					// 의뢰시
																					// 임시저장없이
																					// 바로
																					// 검토의뢰를
																					// 하는
																					// 경우에
																					// 처리하기위해
			if (f_cnsdreq_id != null && !f_cnsdreq_id.equals(""))
				vo.setCnsdreq_id(f_cnsdreq_id);

			ClmsDataUtil.debug("=============makeApprovalHtmlDirect===============");
			ClmsDataUtil.debug(vo.getCnsdreq_id());
			ClmsDataUtil.debug("=============makeApprovalHtmlDirect===============");

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			List<?> reqList = null; // 의뢰정보
			List<?> contractList = null;
			ListOrderedMap reqLom = null;
			StringBuffer sbContent = null;

			reqList = considerationService.detailConsiderationApprovalRequest(vo);
			contractList = considerationService.listConsiderationApprovalContract(vo);
			List<?> authReqList = considerationService.listContractAuthreq(vo);// 권한요청자
																				// -관련자
			StringBuffer reqAuthInfo = new StringBuffer();
			if (authReqList != null && authReqList.size() > 0) {
				for (int i = 0; i < authReqList.size(); i++) {
					ListOrderedMap authLom = (ListOrderedMap) authReqList.get(i);
					if (i == 0) {
						reqAuthInfo.append(StringUtil.bvl((String) authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String) authLom.get("trgtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String) authLom.get("trgtman_dept_nm"), ""));
					} else if (i % 2 == 0 && authReqList.size() > 2) {
						reqAuthInfo.append(
								",<br/>" + StringUtil.bvl((String) authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String) authLom.get("trgtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String) authLom.get("trgtman_dept_nm"), ""));
					} else {
						reqAuthInfo.append(
								", " + StringUtil.bvl((String) authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String) authLom.get("trgtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String) authLom.get("trgtman_dept_nm"), ""));
					}
				}
			}
			String reqAuthString = "";
			if (reqAuthInfo != null)
				reqAuthString = reqAuthInfo.toString();

			Locale lc = (Locale) localeResolver.resolveLocale(request);
			String locale = StringUtil.bvl(lc.getLanguage(), "en");
			sbContent = new StringBuffer();
			if (reqList != null && reqList.size() > 0) {
				reqLom = new ListOrderedMap();
				reqLom = (ListOrderedMap) reqList.get(0);
				makeApprovalHeader(sbContent, strUrl.substring(0, strUrl.indexOf("/", 7)), locale);
				makeApprovalReqInfo(sbContent, reqLom, form, reqAuthString, (String) lc.getLanguage());
				makeApprovalContractInfo(sbContent, contractList, strAttachUrl, vo, (String) lc.getLanguage());
				makeApprovalFooter(sbContent);
			}

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
			apprVo.setBody_type("1");
			// [계약검토의뢰]
			apprVo.setTitle(
					StringUtil.bvl((String) request.getAttribute("approval_title"), messageSource.getMessage("clm.page.field.consideration.makeApprovalHtmlDirect01", null, new RequestContext(request).getLocale()) + vo.getReq_title()));
			// Approval Post Method
			apprVo.setMethod("postAppContStatus");

			String contentHtml = sbContent.toString();
			contentHtml = StringUtil.replace(contentHtml, "<html>", "<html xmlns='http://www.w3.org/1999/xhtml'>");
			contentHtml = StringUtil.replace(contentHtml, "<HTML>", "<html xmlns='http://www.w3.org/1999/xhtml'>");
			contentHtml = StringUtil.replace(contentHtml, "</HTML>", "</html>");
			apprVo.setBody(contentHtml);

			String approvalDrafterID = userId;
			String approvalDrafterUserPath = "";
			String approvalDrafterUserRight = "";

			// 2011.10.20 심주완추가 계약의뢰상신시 사용
			String approvalAuthorizerID = StringUtil.bvl((String) request.getAttribute("approval_auth_id"), form.getApprovalman_id());
			// 2011.10.13 심주완추가 계약체결품의시 사용
			String approvalAuthorizerUserPath = "";
			String approvalAuthorizerUserRight = "";

			// 없음
			String none_str = (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalHtmlDirect02", null, new RequestContext(request).getLocale());
			if (approvalDrafterID != null && !"".equals(approvalDrafterID)) {
				Vector<?> drafterUserVector = null;
				// !@# ESB 인터페이스 변경(authCompCd 추가) 2013.04.25
				drafterUserVector = esbOrgService.userSearchByEpid(approvalDrafterID, lc);

				if (drafterUserVector != null && drafterUserVector.size() > 0) {
					Hashtable<?, ?> ht = (Hashtable<?, ?>) drafterUserVector.get(0);
					// 기안자정보 아이디 | 이름 | 직급코드 | 직급명 |
					approvalDrafterUserPath = "0|" + approvalDrafterID + "|" + ht.get("cn") + "|" + ht.get("eptitlenumber") + "|" + ht.get("title") + "|";
					// 기안자정보 계속 부서코드 | 부서명 | 회사코드 | 회사명 |
					approvalDrafterUserPath = approvalDrafterUserPath + ht.get("departmentnumber") + "|" + ht.get("department") + "|" + ht.get("eporganizationnumber") + "|" + ht.get("o") + "|";
					// 기안자정보계속 총괄코드 | 총괄명 | 메일주소
					approvalDrafterUserPath = approvalDrafterUserPath + StringUtil.bvl(ht.get("epsuborgcode"), "A") + "|" + StringUtil.bvl(ht.get("epsuborgname"), none_str) + "|" + ht.get("mail");
					approvalDrafterUserRight = approvalDrafterID + "|-1|-1|-1";
				}
			}

			// 2011.10.20 심주완추가-결재자정보조회때문에 추가
			if (approvalAuthorizerID != null && !"".equals(approvalAuthorizerID)) {

				Vector<?> authorizerUserVector = null;
				// !@# ESB 인터페이스 변경(authCompCd 추가) 2013.04.25
				authorizerUserVector = esbOrgService.userSearchByEpid(approvalAuthorizerID, lc);

				if (authorizerUserVector != null && authorizerUserVector.size() > 0) {
					Hashtable<?, ?> ht = (Hashtable<?, ?>) authorizerUserVector.get(0);
					approvalAuthorizerUserPath = "1|" + approvalAuthorizerID + "|" + ht.get("cn") + "|" + ht.get("eptitlenumber") + "|" + ht.get("title") + "|";
					approvalAuthorizerUserPath = approvalAuthorizerUserPath + ht.get("departmentnumber") + "|" + ht.get("department") + "|" + ht.get("eporganizationnumber") + "|" + ht.get("o") + "|";
					approvalAuthorizerUserPath = approvalAuthorizerUserPath + StringUtil.bvl(ht.get("epsuborgcode"), "A") + "|" + StringUtil.bvl(ht.get("epsuborgname"), none_str) + "|" + ht.get("mail");
					approvalAuthorizerUserRight = approvalAuthorizerID + "|-1|-1|-1";

				}
			}
			ClmsDataUtil.debug("approvalDrafterUserPath>>>>>>>>" + approvalDrafterUserPath);

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
					ClmsDataUtil.debug("#################################");
					ClmsDataUtil.debug("#approval:" + approvalRoutes[i]);
					ClmsDataUtil.debug("#approvalRigh:" + approvalRouteRights[i]);
					ClmsDataUtil.debug("#################################");
					String[] approvalRoute = StringUtil.token(approvalRoutes[i], "|");
					String[] approvalRouteRight = StringUtil.token(approvalRouteRights[i], "|");

					activitys[i] = approvalRoute[0];
					actionTypes[i] = "1";
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
					mailAddress[i] = approvalRoute[11];

					routeModifys[i] = approvalRouteRight[1];
					bodyModifys[i] = approvalRouteRight[2];
					arbitrarys[i] = approvalRouteRight[3];

				}
			}
			apprVo.setRef_key(vo.getCnsdreq_id());
			apprVo.setApprvl_clsfcn("C03001");
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
			opinions[0] = (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalHtmlDirect03", null, new RequestContext(request).getLocale());// 계약검토의뢰입니다.
			apprVo.setOpinions(opinions);
			// 예약 상신 시간
			String[] reserveds = new String[2];
			for (int i = 0; i < approvalRoutes.length; i++) {
				reserveds[i] = "";
			}
			reserveds[0] = "";
			apprVo.setReserveds(reserveds);

			/*********************************************************
			 * 결재상신
			 **********************************************************/
			boolean isSuccess = false;
			isSuccess = esbApprovalService.submit(apprVo);
			ClmsDataUtil.debug("$%%%%%%%%%%%%%%");
			ClmsDataUtil.debug("isSuccess:" + isSuccess);
			ClmsDataUtil.debug("$%%%%%%%%%%%%%%");
			String result = "N";
			if (isSuccess) {
				result = "Y";
				if (vo.getSubmit_status().equals("req")) {
					// 의뢰상신건에 대한 정상결재상신후 상태코드 업데이트 처리
					modifyConsiderationStatus(request, response);
				}
			}
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.succ.submit", null, new RequestContext(request).getLocale()));
			jo.put("returnValue", "Y");
			jo.put("returnApproval", result);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale()));
			jo.put("returnValue", "Y");
			jo.put("returnApproval", "N");
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale()));
			jo.put("returnValue", "Y");
			jo.put("returnApproval", "N");
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		}

	}

	/**
	 * 결재관련 - 체결 후 등록 화면 변경
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void makeApprovalHtmlDirect2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);
			String sysCd = (String) session.getAttribute("secfw.session.sys_cd");
			String userId = (String) session.getAttribute("secfw.session.user_id");
			String strUrl = (request.getRequestURL()).toString();
			String strAttachUrl = strUrl.substring(0, strUrl.indexOf("/", 7) + 1) + "clms/common/clmsfile.do?method=doClmsDownload&file_id=";
			ApprovalVO apprVo = new ApprovalVO();
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();

			bind(request, form);
			bind(request, vo);

			String f_cnsdreq_id = (String) request.getAttribute("f_cnsdreq_id"); // 최초
																					// 의뢰시
																					// 임시저장없이
																					// 바로
																					// 검토의뢰를
																					// 하는
																					// 경우에
																					// 처리하기위해
			if (f_cnsdreq_id != null && !f_cnsdreq_id.equals(""))
				vo.setCnsdreq_id(f_cnsdreq_id);

			ClmsDataUtil.debug("=============makeApprovalHtmlDirect===============");
			ClmsDataUtil.debug(vo.getCnsdreq_id());
			ClmsDataUtil.debug("=============makeApprovalHtmlDirect===============");

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			List<?> reqList = null; // 의뢰정보
			List<?> contractList = null;
			ListOrderedMap reqLom = null;
			StringBuffer sbContent = null;

			reqList = considerationService.detailConsiderationApprovalRequest(vo);
			contractList = considerationService.listConsiderationApprovalContract(vo);
			List<?> authReqList = considerationService.listContractAuthreq(vo);// 권한요청자
																				// -관련자
			StringBuffer reqAuthInfo = new StringBuffer();
			if (authReqList != null && authReqList.size() > 0) {
				for (int i = 0; i < authReqList.size(); i++) {
					ListOrderedMap authLom = (ListOrderedMap) authReqList.get(i);
					if (i == 0) {
						reqAuthInfo.append(StringUtil.bvl((String) authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String) authLom.get("trgtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String) authLom.get("trgtman_dept_nm"), ""));
					} else if (i % 2 == 0 && authReqList.size() > 2) {
						reqAuthInfo.append(
								",<br/>" + StringUtil.bvl((String) authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String) authLom.get("trgtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String) authLom.get("trgtman_dept_nm"), ""));
					} else {
						reqAuthInfo.append(
								", " + StringUtil.bvl((String) authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String) authLom.get("trgtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String) authLom.get("trgtman_dept_nm"), ""));
					}
				}
			}
			String reqAuthString = "";
			if (reqAuthInfo != null)
				reqAuthString = reqAuthInfo.toString();

			ClmsDataUtil.debug("============================" + reqList);
			ClmsDataUtil.debug("============================" + reqList);
			ClmsDataUtil.debug("============================" + reqList);

			Locale lc = (Locale) localeResolver.resolveLocale(request);
			String locale = StringUtil.bvl(lc.getLanguage(), "en");
			sbContent = new StringBuffer();
			if (reqList != null && reqList.size() > 0) {
				reqLom = new ListOrderedMap();
				reqLom = (ListOrderedMap) reqList.get(0);
				makeApprovalHeader(sbContent, strUrl.substring(0, strUrl.indexOf("/", 7)), locale);
				makeApprovalReqInfo(sbContent, reqLom, form, reqAuthString, (String) lc.getLanguage());
				makeApprovalContractInfo2(sbContent, contractList, strAttachUrl, vo, (String) lc.getLanguage()); // 체결
																													// 후
																													// 등록과
																													// 관련된
																													// 내용을
																													// 가지고
																													// 옵니다
				makeApprovalFooter(sbContent);
			}

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
			apprVo.setBody_type("1");
			// [계약검토의뢰]
			apprVo.setTitle(StringUtil.bvl((String) request.getAttribute("approval_title"),
					(String) messageSource.getMessage("clm.page.field.consideration.makeApprovalHtmlDirect201", null, new RequestContext(request).getLocale()) + vo.getReq_title()));
			// Approval Post Method
			apprVo.setMethod("postAppContStatus");

			String contentHtml = sbContent.toString();
			contentHtml = StringUtil.replace(contentHtml, "<html>", "<html xmlns='http://www.w3.org/1999/xhtml'>");
			contentHtml = StringUtil.replace(contentHtml, "<HTML>", "<html xmlns='http://www.w3.org/1999/xhtml'>");
			contentHtml = StringUtil.replace(contentHtml, "</HTML>", "</html>");
			apprVo.setBody(contentHtml);

			String approvalDrafterID = userId;
			String approvalDrafterUserPath = "";
			String approvalDrafterUserRight = "";

			// 2011.10.20 심주완추가 계약의뢰상신시 사용
			String approvalAuthorizerID = StringUtil.bvl((String) request.getAttribute("approval_auth_id"), form.getApprovalman_id());
			// 2011.10.13 심주완추가 계약체결품의시 사용
			String approvalAuthorizerUserPath = "";
			String approvalAuthorizerUserRight = "";

			// 없음
			String none_str = (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalHtmlDirect202", null, new RequestContext(request).getLocale());

			if (approvalDrafterID != null && !"".equals(approvalDrafterID)) {
				Vector<?> drafterUserVector = null;
				// !@# ESB 인터페이스 변경(authCompCd 추가) 2013.04.25
				drafterUserVector = esbOrgService.userSearchByEpid(approvalDrafterID, lc);

				if (drafterUserVector != null && drafterUserVector.size() > 0) {
					Hashtable<?, ?> ht = (Hashtable<?, ?>) drafterUserVector.get(0);
					// 기안자정보 아이디 | 이름 | 직급코드 | 직급명 |
					approvalDrafterUserPath = "0|" + approvalDrafterID + "|" + ht.get("cn") + "|" + ht.get("eptitlenumber") + "|" + ht.get("title") + "|";
					// 기안자정보 계속 부서코드 | 부서명 | 회사코드 | 회사명 |
					approvalDrafterUserPath = approvalDrafterUserPath + ht.get("departmentnumber") + "|" + ht.get("department") + "|" + ht.get("eporganizationnumber") + "|" + ht.get("o") + "|";
					// 기안자정보계속 총괄코드 | 총괄명 | 메일주소
					approvalDrafterUserPath = approvalDrafterUserPath + StringUtil.bvl(ht.get("epsuborgcode"), "A") + "|" + StringUtil.bvl(ht.get("epsuborgname"), none_str) + "|" + ht.get("mail");
					// approvalDrafterUserPath = approvalDrafterUserPath +
					// ht.get("mail");

					approvalDrafterUserRight = approvalDrafterID + "|-1|-1|-1";
				}
			}

			// 2011.10.20 심주완추가-결재자정보조회때문에 추가
			if (approvalAuthorizerID != null && !"".equals(approvalAuthorizerID)) {

				Vector<?> authorizerUserVector = null;

				// !@# ESB 인터페이스 변경(authCompCd 추가) 2013.04.25
				authorizerUserVector = esbOrgService.userSearchByEpid(approvalAuthorizerID, lc);

				if (authorizerUserVector != null && authorizerUserVector.size() > 0) {
					Hashtable<?, ?> ht = (Hashtable<?, ?>) authorizerUserVector.get(0);
					approvalAuthorizerUserPath = "1|" + approvalAuthorizerID + "|" + ht.get("cn") + "|" + ht.get("eptitlenumber") + "|" + ht.get("title") + "|";
					approvalAuthorizerUserPath = approvalAuthorizerUserPath + ht.get("departmentnumber") + "|" + ht.get("department") + "|" + ht.get("eporganizationnumber") + "|" + ht.get("o") + "|";
					approvalAuthorizerUserPath = approvalAuthorizerUserPath + StringUtil.bvl(ht.get("epsuborgcode"), "A") + "|" + StringUtil.bvl(ht.get("epsuborgname"), none_str) + "|" + ht.get("mail");
					approvalAuthorizerUserRight = approvalAuthorizerID + "|-1|-1|-1";

				}
			}
			ClmsDataUtil.debug("approvalDrafterUserPath>>>>>>>>" + approvalDrafterUserPath);

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
					ClmsDataUtil.debug("#################################");
					ClmsDataUtil.debug("#approval:" + approvalRoutes[i]);
					ClmsDataUtil.debug("#approvalRigh:" + approvalRouteRights[i]);
					ClmsDataUtil.debug("#################################");
					String[] approvalRoute = StringUtil.token(approvalRoutes[i], "|");
					String[] approvalRouteRight = StringUtil.token(approvalRouteRights[i], "|");

					activitys[i] = approvalRoute[0];
					actionTypes[i] = "1";
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
					mailAddress[i] = approvalRoute[11];

					routeModifys[i] = approvalRouteRight[1];
					bodyModifys[i] = approvalRouteRight[2];
					arbitrarys[i] = approvalRouteRight[3];

				}
			}
			apprVo.setRef_key(vo.getCnsdreq_id());
			apprVo.setApprvl_clsfcn("C03001");
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
			// 계약검토의뢰입니다.
			opinions[0] = (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalHtmlDirect203", null, new RequestContext(request).getLocale());
			apprVo.setOpinions(opinions);
			// 예약 상신 시간
			String[] reserveds = new String[2];
			for (int i = 0; i < approvalRoutes.length; i++) {
				reserveds[i] = "";
			}
			reserveds[0] = "";
			apprVo.setReserveds(reserveds);

			/*********************************************************
			 * 결재상신
			 **********************************************************/
			boolean isSuccess = false;
			isSuccess = esbApprovalService.submit(apprVo);
			ClmsDataUtil.debug("$%%%%%%%%%%%%%%");
			ClmsDataUtil.debug("isSuccess:" + isSuccess);
			ClmsDataUtil.debug("$%%%%%%%%%%%%%%");
			String result = "N";
			if (isSuccess) {
				result = "Y";
				if (vo.getSubmit_status().equals("req")) {
					// 의뢰상신건에 대한 정상결재상신후 상태코드 업데이트 처리
					modifyConsiderationStatus(request, response);
				}
			}
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.succ.submit", null, new RequestContext(request).getLocale()));
			jo.put("returnValue", "Y");
			jo.put("returnApproval", result);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale()));
			jo.put("returnValue", "Y");
			jo.put("returnApproval", "N");
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale()));
			jo.put("returnValue", "Y");
			jo.put("returnApproval", "N");
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		}

	}

	/**
	 * 결재관련 추가 2011.10.12 심주완추가 결재상신관련
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView makeApprovalHtml(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			String forwardURL = "";
			String strUrl = (request.getRequestURL()).toString();
			String strAttachUrl = strUrl.substring(0, strUrl.indexOf("/", 7) + 1) + "clms/common/clmsfile.do?method=doClmsDownload&file_id=";

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			forwardURL = "/secfw/esbApproval.do?method=forwardApproval";

			List<?> reqList = null; // 의뢰정보
			List<?> contractList = null;
			ListOrderedMap reqLom = null;
			StringBuffer sbContent = null;

			ModelAndView mav = new ModelAndView();
			reqList = considerationService.detailConsiderationApprovalRequest(vo);
			contractList = considerationService.listConsiderationApprovalContract(vo);

			List<?> authReqList = considerationService.listContractAuthreq(vo);// 권한요청자
																				// -관련자
			StringBuffer reqAuthInfo = new StringBuffer();
			if (authReqList != null && authReqList.size() > 0) {
				for (int i = 0; i < authReqList.size(); i++) {
					ListOrderedMap authLom = (ListOrderedMap) authReqList.get(i);
					if (i > 0) {
						reqAuthInfo.append(
								", " + StringUtil.bvl((String) authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String) authLom.get("trgtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String) authLom.get("trgtman_dept_nm"), ""));
					} else {

						reqAuthInfo.append(StringUtil.bvl((String) authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String) authLom.get("trgtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String) authLom.get("trgtman_dept_nm"), ""));
					}
				}
			}
			Locale lc = (Locale) localeResolver.resolveLocale(request);
			String locale = StringUtil.bvl(lc.getLanguage(), "en");

			String reqAuthString = "";
			if (reqAuthInfo != null)
				reqAuthString = reqAuthInfo.toString();
			sbContent = new StringBuffer();
			if (reqList != null && reqList.size() > 0) {
				reqLom = new ListOrderedMap();
				reqLom = (ListOrderedMap) reqList.get(0);
				makeApprovalHeader(sbContent, strUrl.substring(0, strUrl.indexOf("/", 7)), locale);
				makeApprovalReqInfo(sbContent, reqLom, form, reqAuthString, locale);
				makeApprovalContractInfo(sbContent, contractList, strAttachUrl, vo, (String) lc.getLanguage());
				makeApprovalFooter(sbContent);
			}
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + mav.getViewName());

			mav.addObject("form", form);
			mav.addObject("approval_content", sbContent.toString());
			// [계약검토의뢰]
			mav.addObject("approval_title", (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalHtml01", null, new RequestContext(request).getLocale()) + form.getReq_title());
			mav.addObject("approval_option", "A");
			mav.addObject("apprvl_clsfcn", "C03001");
			mav.addObject("approval_auth_id", form.getApprovalman_id()); // 결재자정보
																			// 2011.10.20
																			// 심주완추가
			mav.addObject("approval_post_process", "postAppContStatus");
			mav.addObject("ref_key", vo.getCnsdreq_id());
			mav.addObject("approval_opinion", form.getApproval_opinion());

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
	 * CLM_결재문서 스타일정보설정
	 */
	public void makeApprovalHeader(StringBuffer sb, String url, String last_locale) throws Exception {
		try {
			Locale locale1 = new Locale(last_locale);
			sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n").append("<html xmlns=\"http://www.w3.org/1999/xhtml\"  lang=\"ko\" xml:lang=\"ko\">\n")
					.append("<head>\n").append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n")
					// ::: 삼성전자 계약관리시스템 :::
					.append("<title>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalHeader01", null, locale1) + "</title>\n").append("<style>\n")
					.append("body, div, h1, h2, h3, h4, h5, h6, ul, ol, li, dl, dt, dd, p, form, fieldset, input, table, tr, th, td, a{margin:0;padding:0; font-family:gulim, arial;}\n")
					.append("html {height:100%; scrollbar-face-color: #FFFFFF; scrollbar-shadow-color: #CCCCCC; scrollbar-highlight-color: #CCCCCC; scrollbar-3dlight-color: #FFFFFF; scrollbar-darkshadow-color: #FFFFFF;  scrollbar-track-color: #F7F7F7; scrollbar-arrow-color: #B8B8B8;}\n")
					.append("body {height:100%; font-family:nanumgothic, Dotum, tahoma, Arial;color:#363636;font-size:12px;background:#fff;*word-break:break-all;-ms-word-break:break-all;}\n")
					.append("h1,h2,h3,h4,h5,h6,pre,code {font-size:1em; margin:0; padding:0}\n").append("ul,ol,li,h1,h2,h3,h4,h5,h6,pre,form,body,html,p,blockquote,fieldset,input {margin:0; padding:0}\n")
					.append("table {clear:both; border-collapse:collapse; border-spacing:0;}\n").append("table th,table td {font:normal 12px/15px gulim,arial; }\n").append(":focus {outline: 0;} \n")
					.append("img, fieldset, iframe {border:0 none;}\n").append("ul, ol, li {list-style:none;}\n").append("img, input, textarea,select {font-family:gulim, Arial; vertical-align:middle;}\n")
					.append("input {height:16px; line-height:16px;}\n").append("*html input {height:18px; line-height:16px;}\n").append("hr {display:none; clear:both;}\n")
					.append("a { font-family:gulim, Arial; text-decoration:none; cursor:pointer;}\n").append("a:hover {color:#17549f; text-decoration:none; font-family:gulim, Arial;}\n").append("em {font-style:normal;}\n")
					.append("address {font-style:normal; padding:7px;}\n").append("button, label {cursor:pointer;}\n").append("form {padding:0;border:0 none;  font-size:12px;}\n")
					.append("select,text,input {font-size:12px; color:#5D6879;}\n").append("textarea {color:#5D6879;font-size:12px; border:1px solid #7F9DB9;padding:5px;line-height:18px; word-wrap:break-word!important}\n")
					.append("legend {visibility:hidden; height:0; width:0; font-size:0;}\n").append("p {line-height:16px;}\n").append("select {margin:0 0 0 0px;}\n").append("#m_wrap {margin:0 0 0 10px; width: 900px;}\n")
					.append(".m_header h1 {background:url(" + url + "/images/clm/" + last_locale + "/common/system_logo.gif) no-repeat left center; text-indent:-1000px; height:32px;}\n")
					.append(".m_header h2 {position:relative;background:url(" + url + "/images/clm/" + last_locale
							+ "/mail/bg_top_mail.jpg) no-repeat left top; width:900px; height:33px; color:#fff; font:bold 14px/32px nanumgothic; text-indent:1.2em;}\n")
					.append(".m_header h2 span.confidential {position: absolute; right:10px; top:6px; }\n").append(".menu2 h2 {background-position:left -33px;}\n").append("th.borTz02 {border-top:0;}\n")
					.append("*:first-child+html th.borTz02 {border-top:1px solid #CADBE2;}\n").append("#m_container {width:898px; padding:0; margin:0; float:left;border:1px solid #cdd6d9; border-top:none;}\n")
					.append("#m_container .contents {background:#fff; margin:0 ; padding:15px 15px ; width:868px;}\n")
					.append("#m_container .contents h3 {margin-bottom:8px; height:32px; background:#EAF4F6 url(" + url + "/images/clm/" + last_locale
							+ "/mail/bg_title_h3.jpg) repeat-x left top; border:1px solid #CFD6E0; border-color:#34A8DB #34A8DB #2b66b9 #34A8DB;}\n")
					.append("#m_container .contents h3 p {padding:7px 10px 5px 10px;font-size:13px; font-family:nanumgothic; color:#3E3E3E;}\n")
					.append("#m_footer {float:left; width:880px; height:35px; border:0px solid #cdd6d9; border-top:0px solid #e5e5e5; color:#818181; font:normal 11px/31px arial; margin: 10px 10px;}\n")
					.append(".copy {font-family:\"돋움\", Arial; font-size:11px; color: #777; background-color: #f9f8f8; border:1px solid #ddd; margin:15px 0 0 0; padding: 10px 10px 10px 10px; line-height:120%;word-break:keep-all;}\n")
					.append(".copy span {font-family:Arial; font-size:10px; color: #777;}\n").append(".copy br {line-height:60%;}\n").append(".title_basic {float:left; clear:both; overflow:hidden;}\n")
					.append("*+html .title_basic {padding:1px 0 5px;}\n")
					.append(".title_basic h4 {min-height:20px; color:#353D4A; font:bold 14px nanumgothic; background:url(" + url + "/images/clm/" + last_locale
							+ "/icon/bu_arrow_t1.gif) no-repeat 3px 15px; padding:12px 10px 6px 18px;}/* 2011-10-01 min-height:20px; 추가 */\n")
					.append("*html .title_basic h4 {padding:12px 10px 6px 18px;}\n").append("/* table_style01 */ \n")
					.append(".table-style01 {width:100%; border-top:2px solid #3e74be; _border-left:1px solid #fff; margin:0; clear:both; table-layout:fixed;}\n")
					.append(".table-style01 th {line-height:18px; padding:4px 10px; background:#f0f7fc; text-align:left; border:1px solid #CBDCE4; color:#1d6498; font-weight:normal;border-right:0;}\n")
					.append(".table-style01 td {line-height:18px;  padding:4px 10px;border-left:1px solid #cadbe2;border-bottom:1px solid #CADBE2;color:#7b858e;}\n").append(".table-style01 tr td {border-left:1px solid #CADBE2}\n")
					.append(".table-style01 tr.end th,.table-style01 tr.end td {border-bottom:1px solid #7696c2;}\n").append(".table-style01 a {color:#2f8cb7;}\n")
					.append(".table-style01 a:hover {color:#0061b6; text-decoration:underline;}\n").append(".table-style01 .on td, .list_sub .on td {color:#259bb8;}\n")
					.append(".table-style01 th:last-child, .table-style01 td:last-child {border-right:0}  \n").append(".table-style01 th:first-child,.table-style01 th.sub:first-child, .table-style01 td:first-child {border-left:0} \n")
					.append(".table-style01 td img {vertical-align:middle;}\n").append(".table-style01 td a {vertical-align:middle;}\n").append(".borz01 {margin-top:-1px; border-top:0;}\n").append("/* table-style_sub */\n")
					.append(".table-style_sub {border-top:1px solid #3e92be; _border-left:1px solid #fff; margin:0; clear:both;table-layout:fixed;}\n").append("*+html .table-style_sub {border-left:1px solid #fff;}\n")
					.append(".table-style_sub th {line-height:18px; text-align:center; padding:2px; background:#e8eff5; border:1px solid #3e92be; border-bottom-color:#7597bd; color:#637893; font-weight:normal;border-right:0;}\n")
					.append(".table-style_sub td {line-height:18px;text-align:center;   padding:2px; border-left:1px solid #dcdcdc;border-bottom:1px solid #dcdcdc;color:#79868c; }\n")
					.append(".table-style_sub th:last-child, .table-style_sub td:last-child {border-right:0}  \n")
					.append(".table-style_sub th:first-child,.table-style_sub th.sub:first-child, .table-style_sub td:first-child {border-left:0} \n").append(".table-style_sub td img {vertical-align:middle;}\n")
					.append(".table-style_sub td a {vertical-align:middle;}\n").append(".mt5  {margin-top:5px;}\n").append(".mt7  {margin-top:7px;}\n").append("/***** Color *****/\n").append(".blueD {color:#5f6da4}\n")
					.append(".th-color {color:#1d6498;}\n").append(".tal_lineL {border-left:1px solid #cadbe2 !important;}\n").append(".text_area_full {border:1px solid #7F9DB9 !important; width:99%; padding:0 1px; margin:0 0px 0 -1px;}\n")
					.append(":root .text_calendar, :root .text_search {padding-top:2px; height:14px;}\n").append(".text_search {border:1px solid #7F9DB9; border-right:none; width:105px; padding:0 1px; margin:0 0 0 0px; }\n")
					.append(".text_calendar {border:1px solid #7F9DB9; border-right:none; width:80px; padding:0 1px; margin:0 0 0 -1px; }\n").append(".text_full {width:99%; border:1px solid #7F9DB9; padding:0 1px; margin:0 0px 1px 0px;}\n")
					.append(":root .text_full {width:99%; margin:0 0px 0 -2px; padding:1px 1px 0 1px; height:15px;} \n").append(".txt_style02 {color:#00abe3; font-weight:bold; line-height:20px;}\n").append("</style>\n").append("</head>\n")
					.append("<body>");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * LAS_결재문서 스타일정보설정
	 * 
	 * @param sb
	 * @param url
	 * @throws Exception
	 */
	public void makeApprovalHeader2(StringBuffer sb, String url, String last_locale) throws Exception {
		try {
			Locale locale1 = new Locale(last_locale);

			sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n").append("<html xmlns=\"http://www.w3.org/1999/xhtml\"  lang=\"ko\" xml:lang=\"ko\">\n")
					.append("<head>\n").append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n")
					// ::: 삼성전자 법무시스템 :::
					.append("<title>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalHeader01", null, locale1) + "</title>\n").append("<style>\n")
					.append("body, div, h1, h2, h3, h4, h5, h6, ul, ol, li, dl, dt, dd, p, form, fieldset, input, table, tr, th, td, a{margin:0;padding:0; font-family:gulim, arial;}\n")
					.append("html {height:100%; scrollbar-face-color: #FFFFFF; scrollbar-shadow-color: #CCCCCC; scrollbar-highlight-color: #CCCCCC; scrollbar-3dlight-color: #FFFFFF; scrollbar-darkshadow-color: #FFFFFF;  scrollbar-track-color: #F7F7F7; scrollbar-arrow-color: #B8B8B8;}\n")
					.append("body {height:100%; font-family:nanumgothic, Dotum, tahoma, Arial;color:#363636;font-size:12px;background:#fff;*word-break:break-all;-ms-word-break:break-all;}\n")
					.append("h1,h2,h3,h4,h5,h6,pre,code {font-size:1em; margin:0; padding:0}\n").append("ul,ol,li,h1,h2,h3,h4,h5,h6,pre,form,body,html,p,blockquote,fieldset,input {margin:0; padding:0}\n")
					.append("table {clear:both; border-collapse:collapse; border-spacing:0;}\n").append("table th,table td {font:normal 12px/15px gulim,arial; }\n").append(":focus {outline: 0;} \n")
					.append("img, fieldset, iframe {border:0 none;}\n").append("ul, ol, li {list-style:none;}\n").append("img, input, textarea,select {font-family:gulim, Arial; vertical-align:middle;}\n")
					.append("input {height:16px; line-height:16px;}\n").append("*html input {height:18px; line-height:16px;}\n").append("hr {display:none; clear:both;}\n")
					.append("a { font-family:gulim, Arial; text-decoration:none; cursor:pointer;}\n").append("a:hover {color:#17549f; text-decoration:none; font-family:gulim, Arial;}\n").append("em {font-style:normal;}\n")
					.append("address {font-style:normal; padding:7px;}\n").append("button, label {cursor:pointer;}\n").append("form {padding:0;border:0 none;  font-size:12px;}\n")
					.append("select,text,input {font-size:12px; color:#5D6879;}\n").append("textarea {color:#5D6879;font-size:12px; border:1px solid #7F9DB9;padding:5px;line-height:18px; word-wrap:break-word!important}\n")
					.append("legend {visibility:hidden; height:0; width:0; font-size:0;}\n").append("p {line-height:16px;}\n").append("select {margin:0 0 0 0px;}\n").append("#m_wrap {margin:0 0 0 10px; width: 900px;}\n")
					.append(".m_header h1 {background:url(" + url + "/images/clm/" + last_locale + "/common/system_logo2.gif) no-repeat left center; text-indent:-1000px; height:32px;}\n")
					.append(".m_header h2 {position:relative;background:url(" + url + "/images/clm/" + last_locale
							+ "/mail/bg_top_mail.jpg) no-repeat left top; width:900px; height:33px; color:#fff; font:bold 14px/32px nanumgothic; text-indent:1.2em;}\n")
					.append(".m_header h2 span.confidential {position: absolute; right:10px; top:6px; }\n").append(".menu2 h2 {background-position:left -33px;}\n").append("th.borTz02 {border-top:0;}\n")
					.append("*:first-child+html th.borTz02 {border-top:1px solid #CADBE2;}\n").append("#m_container {width:898px; padding:0; margin:0; float:left;border:1px solid #cdd6d9; border-top:none;}\n")
					.append("#m_container .contents {background:#fff; margin:0 ; padding:15px 15px ; width:868px;}\n")
					.append("#m_container .contents h3 {margin-bottom:8px; height:32px; background:#EAF4F6 url(" + url + "/images/clm/" + last_locale
							+ "/mail/bg_title_h3.jpg) repeat-x left top; border:1px solid #CFD6E0; border-color:#34A8DB #34A8DB #2b66b9 #34A8DB;}\n")
					.append("#m_container .contents h3 p {padding:7px 10px 5px 10px;font-size:13px; font-family:nanumgothic; color:#3E3E3E;}\n")
					.append("#m_footer {float:left; width:880px; height:35px; border:0px solid #cdd6d9; border-top:0px solid #e5e5e5; color:#818181; font:normal 11px/31px arial; margin: 10px 10px;}\n")
					.append(".copy {font-family:\"돋움\", Arial; font-size:11px; color: #777; background-color: #f9f8f8; border:1px solid #ddd; margin:15px 0 0 0; padding: 10px 10px 10px 10px; line-height:120%;word-break:keep-all;}\n")
					.append(".copy span {font-family:Arial; font-size:10px; color: #777;}\n").append(".copy br {line-height:60%;}\n").append(".title_basic {float:left; clear:both; overflow:hidden;}\n")
					.append("*+html .title_basic {padding:1px 0 5px;}\n")
					.append(".title_basic h4 {min-height:20px; color:#353D4A; font:bold 14px nanumgothic; background:url(" + url + "/images/clm/" + last_locale
							+ "/icon/bu_arrow_t1.gif) no-repeat 3px 15px; padding:12px 10px 6px 18px;}/* 2011-10-01 min-height:20px; 추가 */\n")
					.append("*html .title_basic h4 {padding:12px 10px 6px 18px;}\n").append("/* table_style01 */ \n")
					.append(".table-style01 {width:100%; border-top:2px solid #3e74be; _border-left:1px solid #fff; margin:0; clear:both; table-layout:fixed;}\n")
					.append(".table-style01 th {line-height:18px; padding:4px 10px; background:#f0f7fc; text-align:left; border:1px solid #CBDCE4; color:#1d6498; font-weight:normal;border-right:0;}\n")
					.append(".table-style01 td {line-height:18px;  padding:4px 10px;border-left:1px solid #cadbe2;border-bottom:1px solid #CADBE2;color:#7b858e;}\n").append(".table-style01 tr td {border-left:1px solid #CADBE2}\n")
					.append(".table-style01 tr.end th,.table-style01 tr.end td {border-bottom:1px solid #7696c2;}\n").append(".table-style01 a {color:#2f8cb7;}\n")
					.append(".table-style01 a:hover {color:#0061b6; text-decoration:underline;}\n").append(".table-style01 .on td, .list_sub .on td {color:#259bb8;}\n")
					.append(".table-style01 th:last-child, .table-style01 td:last-child {border-right:0}  \n").append(".table-style01 th:first-child,.table-style01 th.sub:first-child, .table-style01 td:first-child {border-left:0} \n")
					.append(".table-style01 td img {vertical-align:middle;}\n").append(".table-style01 td a {vertical-align:middle;}\n").append(".borz01 {margin-top:-1px; border-top:0;}\n").append("/* table-style_sub */\n")
					.append(".table-style_sub {border-top:1px solid #3e92be; _border-left:1px solid #fff; margin:0; clear:both;table-layout:fixed;}\n").append("*+html .table-style_sub {border-left:1px solid #fff;}\n")
					.append(".table-style_sub th {line-height:18px; text-align:center; padding:2px; background:#e8eff5; border:1px solid #3e92be; border-bottom-color:#7597bd; color:#637893; font-weight:normal;border-right:0;}\n")
					.append(".table-style_sub td {line-height:18px;text-align:center;   padding:2px; border-left:1px solid #dcdcdc;border-bottom:1px solid #dcdcdc;color:#79868c; }\n")
					.append(".table-style_sub th:last-child, .table-style_sub td:last-child {border-right:0}  \n")
					.append(".table-style_sub th:first-child,.table-style_sub th.sub:first-child, .table-style_sub td:first-child {border-left:0} \n").append(".table-style_sub td img {vertical-align:middle;}\n")
					.append(".table-style_sub td a {vertical-align:middle;}\n").append(".mt5  {margin-top:5px;}\n").append(".mt7  {margin-top:7px;}\n").append("/***** Color *****/\n").append(".blueD {color:#5f6da4}\n")
					.append(".th-color {color:#1d6498;}\n").append(".tal_lineL {border-left:1px solid #cadbe2 !important;}\n").append(".text_area_full {border:1px solid #7F9DB9 !important; width:99%; padding:0 1px; margin:0 0px 0 -1px;}\n")
					.append(":root .text_calendar, :root .text_search {padding-top:2px; height:14px;}\n").append(".text_search {border:1px solid #7F9DB9; border-right:none; width:105px; padding:0 1px; margin:0 0 0 0px; }\n")
					.append(".text_calendar {border:1px solid #7F9DB9; border-right:none; width:80px; padding:0 1px; margin:0 0 0 -1px; }\n").append(".text_full {width:99%; border:1px solid #7F9DB9; padding:0 1px; margin:0 0px 1px 0px;}\n")
					.append(":root .text_full {width:99%; margin:0 0px 0 -2px; padding:1px 1px 0 1px; height:15px;} \n").append(".txt_style02 {color:#00abe3; font-weight:bold; line-height:20px;}\n").append("</style>\n").append("</head>\n")
					.append("<body>");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 결재문서 스타일정보설정
	 * 
	 * @param sb
	 * @throws Exception
	 */
	public void makeApprovalFooter(StringBuffer sb) throws Exception {
		try {
			sb.append("<div id=\"m_footer\"><img src='../../../images/las/ko/common/logo_samsung.gif'> ⓒ SAMSUNG </div>\n").append("</div>").append("</div>").append("</body>\n").append("</html>\n");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * CLM_검토의뢰정보셋팅
	 * 
	 * @param sb
	 * @param lom
	 * @param form
	 * @param reqAuthInfo
	 * @throws Exception
	 */
	public void makeApprovalReqInfo(StringBuffer sb, ListOrderedMap lom, ConsultationForm form, String reqAuthInfo, String last_locale) throws Exception {
		try {
			Locale locale1 = new Locale(last_locale);

			sb.append("<div id=\"m_wrap\">\n").append("    <div class=\"m_header menu2\">\n").append("    <h1></h1>\n")
					// 계약검토의뢰
					.append("    <h2>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalReqInfo01", null, locale1) + "<span style=\"float:right;margin-right:20px\"><img src=\"../../../images/clm/" + last_locale
							+ "/icon/pncacc.gif\"></SPAN></h2>\n")
					.append("    </div>\n").append("    <div id=\"m_container\">\n").append("        <div class=\"contents\">\n").append("        <!-- title -->\n").append("        <h3>\n")
					// 계약검토의뢰결재를 상신하오니 재가하여 주시기 바랍니다.
					.append("        <p>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalReqInfo02", null, locale1) + "</p></h3>\n").append("        <!-- title --> \n")
					.append("        <div class=\"title_basic\">\n")
					// 검토의뢰정보
					.append("          <h4>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalReqInfo03", null, locale1) + "</h4>\n").append("        </div>\n").append("        <!-- //title -->\n")
					.append("        <!-- table -->\n").append("        <table cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n").append("            <colgroup>\n").append("                <col width=\"13%\" />\n")
					.append("                <col width=\"12%\" />\n").append("                <col width=\"10%\" />\n").append("                <col width=\"15%\" />\n").append("                <col width=\"13%\" />\n")
					.append("                <col width=\"12%\" />\n").append("                <col width=\"13%\" />\n").append("                <col width=\"14%\" />\n").append("            </colgroup>              \n").append("	<tr>\n")
					// 검토의뢰제목
					.append("		<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalReqInfo04", null, locale1) + "</th>\n").append("    	<td colspan=\"7\">");
			// [계약검토의뢰]
			sb.append((String) messageSource.getMessage("clm.page.field.consideration.makeApprovalReqInfo05", null, locale1) + lom.get("req_title"));
			sb.append("		</td>\n").append("	</tr>\n").append("  <tr class=\"lineAdd\">\n")
					// 의뢰자
					.append(" 	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalReqInfo06", null, locale1) + "</th>\n")
					.append("    	<td colspan=\"3\">" + lom.get("reqman_nm") + "/" + lom.get("reqman_jikgup_nm") + "/" + lom.get("req_dept_nm") + "</td>\n")
					// 의뢰일
					.append("     <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalReqInfo07", null, locale1) + "</th>\n").append("		<td>");
			sb.append(lom.get("req_dt"));
			sb.append("		</td>\n")
					// 회신요청일
					.append("		<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalReqInfo08", null, locale1) + "</th>\n").append("     <td>");
			sb.append(lom.get("re_demndday"));
			sb.append("		</td>\n").append("	</tr>\n").append("	<tr class=\"lineAdd\">\n")
					// 관련자
					.append("  	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalReqInfo09", null, locale1) + "</th>\n").append("     <td colspan=\"7\">" + reqAuthInfo + "</td>\n").append(" </tr>\n")
					.append("	<tr class=\"lineAdd\">\n")
					// 검토요청 내용
					.append("  	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalReqInfo10", null, locale1) + "</th>\n").append("      <td colspan=\"7\">");
			sb.append(StringUtil.bvl(lom.get("cnsd_demnd_cont"), ""));
			sb.append("		</td>\n").append("	</tr>\n").append("</table>\n");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * LAS_검토의뢰정보셋팅
	 * 
	 * @param sb
	 * @param lom
	 * @param form
	 * @param reqAuthInfo
	 * @throws Exception
	 */
	public void makeApprovalReqInfo2(StringBuffer sb, ListOrderedMap lom, ConsultationForm form, String reqAuthInfo, String last_locale) throws Exception {
		try {
			Locale locale1 = new Locale(last_locale);

			sb.append("<div id=\"m_wrap\">\n").append("    <div class=\"m_header menu2\">\n").append("    <h1></h1>\n")
					// 체결후등록
					.append("    <h2>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalReqInfo201", null, locale1) + "<span style=\"float:right;margin-right:20px\"><img src=\"../../../images/clm/" + last_locale
							+ "/icon/pncacc.gif\"></SPAN></h2>\n")
					.append("    </div>\n").append("    <div id=\"m_container\">\n").append("        <div class=\"contents\">\n").append("        <div class=\"title_basic\">\n")
					// 검토의뢰정보
					.append("          <h4>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalReqInfo202", null, locale1) + "</h4>\n").append("        </div>\n").append("        <!-- //title -->\n")
					.append("        <!-- table -->\n").append("        <table cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n").append("            <colgroup>\n").append("                <col width=\"13%\" />\n")
					.append("                <col width=\"12%\" />\n").append("                <col width=\"10%\" />\n").append("                <col width=\"15%\" />\n").append("                <col width=\"13%\" />\n")
					.append("                <col width=\"12%\" />\n").append("                <col width=\"13%\" />\n").append("                <col width=\"14%\" />\n").append("            </colgroup>              \n").append("	<tr>\n")
					// 검토의뢰제목
					.append("		<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalReqInfo203", null, locale1) + "</th>\n").append("    	<td colspan=\"7\">");
			// [계약검토의뢰]
			sb.append((String) messageSource.getMessage("clm.page.field.consideration.makeApprovalReqInfo204", null, locale1) + lom.get("req_title"));
			sb.append("		</td>\n").append("	</tr>\n").append("  <tr class=\"lineAdd\">\n")
					// 의뢰자
					.append(" 	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalReqInfo205", null, locale1) + "</th>\n")
					.append("    	<td colspan=\"3\">" + lom.get("reqman_nm") + "/" + lom.get("reqman_jikgup_nm") + "/" + lom.get("req_dept_nm") + "</td>\n")
					// 의뢰일
					.append("     <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalReqInfo206", null, locale1) + "</th>\n").append("		<td>");
			sb.append(lom.get("req_dt"));
			sb.append("		</td>\n")
					// 회신요청일
					.append("		<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalReqInfo207", null, locale1) + "</th>\n").append("     <td>");
			sb.append(lom.get("re_demndday"));
			sb.append("		</td>\n").append("	</tr>\n").append("	<tr class=\"lineAdd\">\n")
					// 관련자
					.append("  	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalReqInfo208", null, locale1) + "</th>\n").append("     <td colspan=\"7\">" + reqAuthInfo + "</td>\n").append(" </tr>\n")
					.append("	<tr class=\"lineAdd\">\n")
					// 체결 등록 사유
					.append("  	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalReqInfo209", null, locale1) + "</th>\n").append("      <td colspan=\"7\">");
			sb.append(StringUtil.bvl(lom.get("cnsd_demnd_cont"), ""));
			sb.append("		</td>\n").append("	</tr>\n").append("</table>\n");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 계약상세정보셋팅
	 */
	public void makeApprovalContractInfo(StringBuffer sb, List list, String attachinfo, ConsultationVO vo, String last_locale) throws Exception {
		try {
			ListOrderedMap lom = null;
			ListOrderedMap tempLom = null;

			int iSize = list.size();
			Locale locale1 = new Locale(last_locale);

			for (int i = 0; i < list.size(); i++) {
				String titleIndex = "";
				// 계약
				if (iSize > 1)
					titleIndex = (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo01", null, locale1) + (i + 1) + " - ";
				lom = (ListOrderedMap) list.get(i);
				vo.setCntrt_id((String) lom.get("cntrt_id"));

				List detailContract = considerationService.detailContractMaster(vo);
				List listAttachInfo = considerationService.listConsiderationApprovalAttachInfo(vo);
				List specialList = considerationService.listTypeSpclinfo(vo);

				ArrayList listContractAttach = new ArrayList();
				ArrayList listEtcAttach = new ArrayList();
				ArrayList listUntprcAttach = new ArrayList();
				ArrayList listPreAttach = new ArrayList();
				ArrayList listRestAttach = new ArrayList();
				int cntrtSize = 0;
				int etcSize = 0;
				if (listAttachInfo != null && listAttachInfo.size() > 0) {
					for (int j = 0; j < listAttachInfo.size(); j++) {
						tempLom = (ListOrderedMap) listAttachInfo.get(j);
						if ("1".equals((String) tempLom.get("filetype"))) { // 계약서
							listContractAttach.add(listAttachInfo.get(j));
						} else if ("2".equals((String) tempLom.get("filetype"))) {// 별첨
							listEtcAttach.add(listAttachInfo.get(j));
						} else if ("5".equals((String) tempLom.get("filetype"))) {// 기타
							listRestAttach.add(listAttachInfo.get(j));
						} else if ("3".equals((String) tempLom.get("filetype"))) {// 사전
																					// 폼의
																					// 파일
							listPreAttach.add(listAttachInfo.get(j));
						} else if ("4".equals((String) tempLom.get("filetype"))) {// 의뢰
																					// 단가
																					// 파일
							listUntprcAttach.add(listAttachInfo.get(j));
						} else {
						}
					}
				}

				if (listContractAttach != null && listContractAttach.size() > 0) {
					cntrtSize = listContractAttach.size();
				}

				if (listEtcAttach != null && listEtcAttach.size() > 0) {
					etcSize = listEtcAttach.size();
				}

				if (cntrtSize + etcSize > 2) {
				}

				ListOrderedMap lomDetail = (ListOrderedMap) detailContract.get(0);

				// 기대효과 <BR>antcptnefctANTCPTNEFCT
				lomDetail.put("antcptnefct", StringUtil.convertEnterToBR((String) lomDetail.get("antcptnefct")));
				// 지불/수분조건 <BR>PAYMENT_COND
				lomDetail.put("payment_cond", StringUtil.convertEnterToBR((String) lomDetail.get("payment_cond")));
				// 기타주요사항 <BR>ETC_MAIN_CONT
				lomDetail.put("etc_main_cont", StringUtil.convertEnterToBR((String) lomDetail.get("etc_main_cont")));
				//
				lomDetail.put("cntrt_chge_demnd_cause", StringUtil.convertEnterToBR((String) lomDetail.get("cntrt_chge_demnd_cause")));
				// 단가내역 요약cntrt_untprc_expl
				lomDetail.put("cntrt_untprc_expl", StringUtil.convertEnterToBR(StringUtil.bvl((String) lomDetail.get("cntrt_untprc_expl"), "")));
				// 책임한도 oblgt_lmt
				lomDetail.put("oblgt_lmt", StringUtil.convertEnterToBR(StringUtil.bvl((String) lomDetail.get("oblgt_lmt"), "")));
				// Specila Condition spcl_cond
				lomDetail.put("spcl_cond", StringUtil.convertEnterToBR(StringUtil.bvl((String) lomDetail.get("spcl_cond"), "")));
				// loac_etc 준거법 상세
				lomDetail.put("loac_etc", StringUtil.convertEnterToBR(StringUtil.bvl((String) lomDetail.get("loac_etc"), "")));
				// dspt_resolt_mthd_det 분쟁해결방법상세
				lomDetail.put("dspt_resolt_mthd_det", StringUtil.convertEnterToBR(StringUtil.bvl((String) lomDetail.get("dspt_resolt_mthd_det"), "")));

				sb.append("<div class=\"mt5\"></div>\n").append("	<div width=\"100%\" class=\"title_basic\">\n")
						// 계약기본정보
						.append("	        <h4>" + " " + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo02", null, locale1) + " " + "</h4>\n").append("</div>\n")
						.append("	<div style=\"margin:20px 0 0 0; float:right;\">\n")
						// 계약 ID
						.append("	        " + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo03", null, locale1) + " : <c:out value='${contractMstLom.cntrt_id}'/>&nbsp;&nbsp;\n").append("</div>\n")
						.append("<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n").append("<colgroup>\n").append("    <col width=\"13%\" />\n").append("	   <col width=\"9%\" />\n")
						.append("	   <col width=\"18%\" />\n").append("    <col width=\"13%\" />\n").append("    <col width=\"16%\" />\n").append("    <col width=\"13%\" />\n").append("    <col width=\"18%\" />\n").append("</colgroup>\n")
						.append("    <tr>\n")
						// 계약명
						.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo04", null, locale1) + "</th>\n")
						.append("        <td colspan=\"6\"><span class=\"fL\">" + lomDetail.get("cntrt_nm") + "</span></td>\n").append("    </tr>\n").append("    <tr>\n")
						// 계약상대방
						.append("        <th>" + (String) messageSource.getMessage("clm.page.msg.common.otherParty", null, locale1) + "</th>\n").append("        <td colspan=\"2\">" + lomDetail.get("cntrt_oppnt_nm") + "</td>\n")
						// 대표자명
						.append("        <th>" + (String) messageSource.getMessage("clm.page.field.customer.registerNo", null, locale1) + "</th>\n")
						.append("        <td>" + StringUtil.bvl(lomDetail.get("cntrt_oppnt_rprsntman"), "") + "</td>\n")
						// 업체Type
						.append("        <th>" + (String) messageSource.getMessage("clm.page.field.customer.contractRequired", null, locale1) + "</th>\n")
						.append("        <td>" + StringUtil.bvl(lomDetail.get("cntrt_oppnt_type_nm"), "") + "</td>\n").append("	   </tr>\n").append("    	<tr>\n")
						// 상대방담당자
						.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo08", null, locale1) + "</th>\n")
						.append("        <td colspan=\"2\">" + StringUtil.bvl(lomDetail.get("cntrt_oppnt_respman"), "") + "</td>\n")
						// 전화번호
						.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo09", null, locale1) + "</th>\n")
						.append("        <td>" + StringUtil.bvl(lomDetail.get("cntrt_oppnt_telno"), "") + "</td>\n").append("        <th>E-mail</th>\n")
						.append("        <td>" + StringUtil.bvl(lomDetail.get("cntrt_oppnt_email"), "") + "</td>\n").append("	   	</tr>\n").append("	   <tr class=\"slide-target02 slide-area\">\n")
						// 계약유형
						.append("	       <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo10", null, locale1) + "</th>\n").append("    	   <td colspan=\"6\">");
				sb.append(lomDetail.get("biz_clsfcn_nm") + " / " + lomDetail.get("depth_clsfcn_nm") + " / " + lomDetail.get("cnclsnpurps_bigclsfcn_nm") + " / " + lomDetail.get("cnclsnpurps_midclsfcn_nm"));
				sb.append("		   </td>\n").append("	   </tr>\n").append("    <tr>\n")
						// 계약대상
						.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo11", null, locale1) + "</th>\n").append("        <td colspan=\"6\">");
				sb.append(lomDetail.get("cntrt_trgt_nm"));
				sb.append("		   </td>\n").append("	   </tr>\n").append("    <tr>\n")
						// 계약대상상세
						.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo12", null, locale1) + "</th>\n").append("        <td colspan=\"6\">");
				sb.append(lomDetail.get("cntrt_trgt_det"));
				sb.append("		   </td>\n").append("	   </tr>\n");
				if (!"".equals(StringUtil.bvl(lomDetail.get("pshdbkgrnd_purps"), ""))) {
					sb.append("    <tr>\n")
							// 추진목적 및 배경
							.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo13", null, locale1) + "</th>\n").append("        <td colspan=\"6\"><span class=\"fL\">");
					sb.append(StringUtil.bvl(lomDetail.get("pshdbkgrnd_purps"), ""));
					sb.append("            </span></td>\n").append("    </tr>\n");
				}
				if (!"".equals(StringUtil.bvl(lomDetail.get("antcptnefct"), ""))) {
					sb.append("    <tr>\n")
							// 기대효과
							.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo14", null, locale1) + "</th>\n").append("        <td colspan=\"6\"><span class=\"fL\">");
					sb.append(StringUtil.convertEnterToBR(StringUtil.bvl(lomDetail.get("antcptnefct"), "")));
					sb.append("            </span></td>\n").append("    </tr>\n");
				}

				sb.append("    <tr>\n").append("	</table>\n").append("<div class=\"mt5\"></div>\n")
						// 계약상세정보
						.append("<div class=\"title_basic\"><h4>" + titleIndex + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo15", null, locale1) + "</h4></div>\n")
						.append("<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n").append("<colgroup>\n").append("    <col width=\"13%\" />\n").append("	   <col width=\"9%\" />\n")
						.append("	   <col width=\"18%\" />\n").append("    <col width=\"13%\" />\n").append("    <col width=\"16%\" />\n").append("    <col width=\"13%\" />\n").append("    <col width=\"18%\" />\n").append("</colgroup>\n")
						.append("     <tr>\n")
						// 계약기간
						.append(" 	    <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo16", null, locale1) + "</th>\n")
						.append("	        <td colspan=\"6\">" + lomDetail.get("cntrtperiod_startday") + " ~ " + lomDetail.get("cntrtperiod_endday") + "</td>\n").append("     </tr>\n");

				// if("해당없음".equals((String)lomDetail.get("payment_gbn_nm"))){
				if ("C02004".equals(StringUtil.bvl(lomDetail.get("payment_gbn"), ""))) { // 해당없음
																							// 이면
					sb.append("     <tr>\n")
							// 지불/수불 구분
							.append("	    	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo17", null, locale1) + "</th>\n")
							.append(" 	    <td colspan=\"6\">" + lomDetail.get("payment_gbn_nm") + "</td>\n").append("    </tr>\n");

				} else {
					sb.append("     <tr>\n")
							// 지불/수불 구분
							.append("	    	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo17", null, locale1) + "</th>\n")
							.append(" 	    <td colspan=\"2\">" + lomDetail.get("payment_gbn_nm") + "</td>\n")
							// 계약금액
							.append("        	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo18", null, locale1) + "</th>\n")
							.append("        	<td colspan=\"3\" class=\"tR\">" + StringUtil.commaIn(StringUtil.bvl(lomDetail.get("cntrt_amt"), "0")) + "</td>\n").append("    </tr>\n");
					if (!"".equals(StringUtil.bvl(lomDetail.get("crrncy_unit"), ""))) {
						sb.append("	   <tr>\n")
								// 통화(단위)
								.append("        	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo19", null, locale1) + "</th>\n")
								.append("        	<td colspan=\"6\">" + lomDetail.get("crrncy_unit") + "</td>\n").append("    </tr>\n");
					}
				}

				if (!"".equals(StringUtil.bvl(lomDetail.get("cntrt_untprc_expl"), ""))) {
					sb.append("     <tr>\n");
					// 단가내역
					sb.append("         <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo20", null, locale1) + "</th>\n")
							.append("         <td colspan=\"6\">" + StringUtil.bvl(lomDetail.get("cntrt_untprc_expl"), "") + "</td>\n").append("     </tr>\n");
				}
				if (!"".equals(StringUtil.bvl(lomDetail.get("payment_cond"), ""))) {
					sb.append("     <tr>\n")
							// 지불/수불 조건
							.append("	    	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo21", null, locale1) + "</th>\n")
							.append("	        <td colspan=\"6\">" + StringUtil.bvl(lomDetail.get("payment_cond"), "") + "</td>\n").append("     </tr>\n");
				}
				// 특화정보시작
				if (specialList != null && specialList.size() > 0) {
					for (int iSpclIdx = 0; iSpclIdx < specialList.size(); iSpclIdx++) {
						tempLom = (ListOrderedMap) specialList.get(iSpclIdx);
						if (!"".equals(StringUtil.bvl(tempLom.get("attr_value"), ""))) {
							sb.append("		<tr>\n").append("     	<th>" + tempLom.get("attr_nm") + "</th>\n").append("			<td class=\"tal_lineL\" colspan=\"6\">" + StringUtil.bvl(tempLom.get("attr_value"), "") + "</td>\n")
									.append("     </tr>");
						}
					}
				}

				if (!"".equals(StringUtil.bvl(lomDetail.get("etc_main_cont"), "")) || !"".equals(StringUtil.bvl(lomDetail.get("if_sys_cd"), ""))) {
					sb.append("     <tr>\n")
							// 기타 주요사항
							.append("	    	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo22", null, locale1) + "</th>\n");
					if (!"".equals((String) lomDetail.get("if_sys_cd"))) {
						sb.append("           <td colspan=\"6\" valign=\"top\">" + StringUtil.convertEnterToBR(StringUtil.bvl(lomDetail.get("etc_main_cont"), "")) + " [" + lomDetail.get("if_sys_cd") + "]</td>\n");
					} else {
						sb.append("           <td colspan=\"6\" valign=\"top\">" + StringUtil.convertEnterToBR(StringUtil.bvl(lomDetail.get("etc_main_cont"), "")) + "</td>\n");
					}
					sb.append("     </tr>\n");
				}

				sb.append("    </table>\n").append("<div class=\"mt5\"></div>\n")
						// 첨부파일
						.append("<div class=\"title_basic\"><h4>" + titleIndex + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo23", null, locale1) + "</h4></div>\n")
						.append("<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n").append("<colgroup>\n").append("    	<col width=\"13%\" />\n").append("	   	<col width=\"9%\" />\n")
						.append("	   	<col width=\"18%\" />\n").append("    	<col width=\"13%\" />\n").append("    	<col width=\"16%\" />\n").append("    	<col width=\"13%\" />\n").append("    	<col width=\"18%\" />\n")
						.append("</colgroup>\n").append("	 	<tr>\n")
						// 계약서
						.append("       	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo24", null, locale1) + "</th>\n").append("        	<td colspan=\"6\">");
				if (listContractAttach != null && listContractAttach.size() > 0) {
					tempLom = (ListOrderedMap) listContractAttach.get(0);
					sb.append("<a href=\"" + attachinfo + StringUtil.bvl(tempLom.get("file_id"), "") + "\">" + StringUtil.bvl(tempLom.get("org_file_nm"), "") + "</a>");
				} else {
					// sb.append("첨부파일없음");
				}
				sb.append("			</td>\n").append("    </tr>\n");

				// 첨부파일 별첨
				if (listEtcAttach != null && listEtcAttach.size() > 0) {
					for (int k = 0; k < listEtcAttach.size(); k++) {
						tempLom = (ListOrderedMap) listEtcAttach.get(k);
						sb.append("<tr>\n");
						if (k == 0) {
							if (listEtcAttach.size() == 1) {
								// 첨부/별첨
								sb.append("<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo25", null, locale1) + "</th>\n");
							} else {
								// 첨부/별첨
								sb.append("<th rowspan=\"" + listEtcAttach.size() + "\">" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo25", null, locale1) + "</th>\n");
							}
							sb.append("<td colspan=\"6\" class=\"tal_lineL\"><a href=\"" + attachinfo + StringUtil.bvl(tempLom.get("file_id"), "") + "\">" + StringUtil.bvl(tempLom.get("org_file_nm"), "") + "</a></td>");
						} else {
							sb.append("<td colspan=\"6\" class=\"tal_lineL\"><a href=\"" + attachinfo + StringUtil.bvl(tempLom.get("file_id"), "") + "\">" + StringUtil.bvl(tempLom.get("org_file_nm"), "") + "</a></td>");
						}
						sb.append("</tr>\n");
					}
				}

				// 첨부파일 기타
				if (listRestAttach != null && listRestAttach.size() > 0) {
					for (int k = 0; k < listRestAttach.size(); k++) {
						tempLom = (ListOrderedMap) listRestAttach.get(k);
						sb.append("<tr>\n");
						if (k == 0) {
							if (listPreAttach.size() == 1) {
								// 기타
								sb.append("<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo26", null, locale1) + "</th>\n");
							} else {
								// 기타
								sb.append("<th rowspan=\"" + listRestAttach.size() + "\">" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo26", null, locale1) + "</th>\n");
							}
							sb.append("<td colspan=\"6\" class=\"tal_lineL\"><a href=\"" + attachinfo + StringUtil.bvl(tempLom.get("file_id"), "") + "\">" + StringUtil.bvl(tempLom.get("org_file_nm"), "") + "</a></td>");
						} else {
							sb.append("<td colspan=\"6\" class=\"tal_lineL\"><a href=\"" + attachinfo + StringUtil.bvl(tempLom.get("file_id"), "") + "\">" + StringUtil.bvl(tempLom.get("org_file_nm"), "") + "</a></td>");
						}
						sb.append("</tr>\n");
					}
				}

				// 의뢰단가파일 listUntprcAttach
				if (listUntprcAttach != null && listUntprcAttach.size() > 0) {
					for (int k = 0; k < listUntprcAttach.size(); k++) {
						tempLom = (ListOrderedMap) listUntprcAttach.get(k);
						sb.append("<tr>\n");
						if (k == 0) {
							if (listUntprcAttach.size() == 1) {
								// 단가첨부파일
								sb.append("<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo27", null, locale1) + "</td>\n");
							} else {
								// 단가첨부파일
								sb.append("<th rowspan=\"" + listUntprcAttach.size() + "\">" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo27", null, locale1) + " </td>\n");
							}
							sb.append("<td colspan=\"6\" class=\"tal_lineL\"><a href=\"" + attachinfo + StringUtil.bvl(tempLom.get("file_id"), "") + "\">" + StringUtil.bvl(tempLom.get("org_file_nm"), "") + "</a></td>");
						} else {
							sb.append("<td colspan=\"6\" class=\"tal_lineL\"><a href=\"" + attachinfo + StringUtil.bvl(tempLom.get("file_id"), "") + "\">" + StringUtil.bvl(tempLom.get("org_file_nm"), "") + "</a></td>");
						}
						sb.append("</tr>\n");
					}
				}

				// 사전 폼의 파일
				if (listPreAttach != null && listPreAttach.size() > 0) {
					for (int k = 0; k < listPreAttach.size(); k++) {
						tempLom = (ListOrderedMap) listPreAttach.get(k);
						sb.append("<tr>\n");
						if (k == 0) {
							if (listPreAttach.size() == 1) {
								// 사전승인
								sb.append("<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo28", null, locale1) + "</th>\n");
							} else {
								// 사전승인
								sb.append("<th rowspan=\"" + listPreAttach.size() + "\">" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo28", null, locale1) + "</th>\n");
							}
							sb.append("<td colspan=\"6\" class=\"tal_lineL\"><a href=\"" + attachinfo + StringUtil.bvl(tempLom.get("file_id"), "") + "\">" + StringUtil.bvl(tempLom.get("org_file_nm"), "") + "</a></td>");
						} else {
							sb.append("<td colspan=\"6\" class=\"tal_lineL\"><a href=\"" + attachinfo + StringUtil.bvl(tempLom.get("file_id"), "") + "\">" + StringUtil.bvl(tempLom.get("org_file_nm"), "") + "</a></td>");
						}
						sb.append("</tr>\n");
					}
				}
				sb.append("</table>\n");
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
	 * 계약상세정보셋팅 - 체결 후 등록
	 */
	public void makeApprovalContractInfo2(StringBuffer sb, List list, String attachinfo, ConsultationVO vo, String last_locale) throws Exception {
		try {
			ListOrderedMap lom = null;
			ListOrderedMap tempLom = null;
			ConsultationSpecialVO specialVo = new ConsultationSpecialVO();
			ConsultationExecVO execVo = new ConsultationExecVO();
			ChooseContractVO contractVo = new ChooseContractVO();
			Locale locale1 = new Locale(last_locale);

			int iSize = contractVo.getApproval_yn_arr().length;
			for (int i = 0; i < contractVo.getApproval_yn_arr().length; i++) {
				if ("Y".equals(contractVo.getApproval_yn_arr()[i])) {
					vo.setCntrt_id(contractVo.getCntrt_id_arr()[i]);
					List detailContract = consultationService.detailConsultationContractMasterApproval(vo);
					specialVo.setCntrt_id(vo.getCntrt_id());
					List listSpecial = consultationService.listConsultationSpecial(specialVo);
					ArrayList special1List = new ArrayList();
					ArrayList special2List = new ArrayList();
					if (listSpecial.size() > 0 && listSpecial != null) {
						for (int j = 0; j < listSpecial.size(); j++) {
							ListOrderedMap specialLom = (ListOrderedMap) listSpecial.get(j);
							if ("C04002".equals(specialLom.get("crtn_depth"))) {
								special1List.add(listSpecial.get(j));
							} else if ("C04005".equals(specialLom.get("crtn_depth"))) {
								special2List.add(listSpecial.get(j));
							}
						}
					}

					/******************************************************
					 * 1. 버튼에 따라서 이행정보의 완료일,비고, 확인여부를 보여주고 숨겨야 된다. 2. 아래
					 * 값(buGubn)을 이용하여 버튼에 따라서 값을 던져줘야 된다.
					 **************************************************/

					String buGubn = ""; // 인쇄 버튼의 구분

					buGubn = StringUtil.bvl(vo.getBuGubn(), ""); // 버튼의 구분 여부를
																	// 체크 합니다.

					// 이행정보조회
					execVo.setCntrt_id(vo.getCntrt_id());
					List listExec = consultationService.listConsultationExec(execVo);

					ArrayList listContractAttach = new ArrayList(); // 의뢰계약서
					ArrayList listEtcAttach = new ArrayList(); // 의뢰기타
					ArrayList listPreAttach = new ArrayList(); // 사전검토
					ArrayList listPriceAttach = new ArrayList(); // 단가내역
					ArrayList listEtc2Attach = new ArrayList(); // 의뢰별첨

					List<?> listAttachInfo = consultationService.listConsultationApprovalAttachInfo(vo);
					if (listAttachInfo != null && listAttachInfo.size() > 0) {
						for (int j = 0; j < listAttachInfo.size(); j++) {
							tempLom = (ListOrderedMap) listAttachInfo.get(j);
							if ("1".equals((String) tempLom.get("filetype"))) {
								listContractAttach.add(listAttachInfo.get(j));
							} else if ("2".equals((String) tempLom.get("filetype"))) {
								listEtcAttach.add(listAttachInfo.get(j));
							} else if ("3".equals((String) tempLom.get("filetype"))) {
								listPreAttach.add(listAttachInfo.get(j));
							} else if ("4".equals((String) tempLom.get("filetype"))) {
								listPriceAttach.add(listAttachInfo.get(j));
							} else if ("5".equals((String) tempLom.get("filetype"))) {
								listEtc2Attach.add(listAttachInfo.get(j));
							}
						}
					}

					String titleIndex = "";
					// 계약
					if (iSize > 1)
						titleIndex = (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo201", null, locale1) + " " + (i + 1) + " " + " - ";
					ListOrderedMap lomDetail = (ListOrderedMap) detailContract.get(0);
					sb.append("	<div class=\"mt5\"></div>\n").append("	<div width=\"100%\" class=\"title_basic\">\n").append("	<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n").append("	<colgroup>\n")
							.append("		<col width=\"50%\" />\n").append("		<col width=\"50%\" />\n").append("	</colgroup>\n").append("	    <tr>\n")
							// 계약기본정보
							.append("	        <td><h4>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo202", null, locale1)
									+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h4></td>\n");
					if (!"".equals(StringUtil.bvl(lomDetail.get("cntrt_no"), ""))) {
						sb.append("		    <td align=\"right\"> ID : " + lomDetail.get("cntrt_no") + "</td>\n");
					}
					sb.append("	    </tr>\n").append("	</table>\n").append("</div>\n").append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n").append("	<colgroup>\n")
							.append("		<col width=\"17%\" />\n").append("		<col width=\"11%\" />\n").append("		<col width=\"17%\" />\n").append("		<col width=\"14%\" />\n").append("		<col width=\"12%\" />\n")
							.append("		<col width=\"12%\" />\n").append("		<col width=\"17%\" />\n").append("	</colgroup>\n").append("    	<tr>\n")
							// 계약명
							.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo203", null, locale1) + "</th>\n")
							.append("        <td colspan=\"6\"><span class=\"fL\">" + lomDetail.get("cntrt_nm") + "</span></td>\n").append("    	</tr>\n").append("    	<tr>\n")
							// 계약상대방
							.append("        <th>" + (String) messageSource.getMessage("clm.page.msg.common.otherParty", null, locale1) + "</th>\n").append("        <td colspan=\"2\">" + lomDetail.get("cntrt_oppnt_nm") + "</td>\n")
							// 대표자명
							.append("        <th>" + (String) messageSource.getMessage("clm.page.field.customer.registerNo", null, locale1) + "</th>\n").append("        <td>" + lomDetail.get("cntrt_oppnt_rprsntman") + "</td>\n")
							// 업체Type
							.append("        <th>" + (String) messageSource.getMessage("clm.page.field.customer.contractRequired", null, locale1) + "</th>\n")
							.append("        <td>" + StringUtil.bvl(lomDetail.get("cntrt_oppnt_type_nm"), "") + "</td>\n").append("	   	</tr>\n").append("    	<tr>\n")
							// 상대방담당자
							.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo207", null, locale1) + "</th>\n")
							.append("        <td colspan=\"2\">" + StringUtil.bvl(lomDetail.get("cntrt_oppnt_respman"), "") + "</td>\n")
							// 전화번호
							.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo208", null, locale1) + "</th>\n")
							.append("        <td>" + StringUtil.bvl(lomDetail.get("cntrt_oppnt_telno"), "") + "</td>\n").append("        <th>E-mail</th>\n")
							.append("        <td>" + StringUtil.bvl(lomDetail.get("cntrt_oppnt_email"), "") + "</td>\n").append("	   	</tr>\n").append("	   	<tr class=\"slide-target02 slide-area\">\n")
							// 계약유형
							.append("	       <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo209", null, locale1) + "</th>\n")
							.append("    	   <td colspan=\"6\">" + lomDetail.get("biz_clsfcn_nm") + " / " + lomDetail.get("depth_clsfcn_nm") + " / " + lomDetail.get("cnclsnpurps_bigclsfcn_nm") + " / "
									+ lomDetail.get("cnclsnpurps_midclsfcn_nm") + "</td>\n")
							.append("	   	</tr>\n").append("    	<tr>\n")
							// 계약대상
							.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo210", null, locale1) + "</th>\n")
							.append("        <td colspan=\"2\">" + lomDetail.get("cntrt_trgt_nm") + "</td>\n")
							// 계약대상상세
							.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo211", null, locale1) + "</th>\n")
							.append("        <td colspan=\"3\">" + lomDetail.get("cntrt_trgt_det") + "</td>\n").append("		</tr>\n");
					if (!"".equals(StringUtil.bvl(lomDetail.get("pshdbkgrnd_purps"), ""))) {
						sb.append("		<tr>\n")
								// 추진목적 및 배경
								.append("			<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo212", null, locale1) + "</th>\n")
								.append("			<td colspan=\"6\">" + StringUtil.bvl((String) lomDetail.get("pshdbkgrnd_purps"), "") + "</td>\n").append("		</tr>\n");
					}
					if (!"".equals(StringUtil.bvl(lomDetail.get("antcptnefct"), ""))) {
						sb.append("		<tr>\n")
								// 기대효과
								.append("			<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo213", null, locale1) + "</th>\n")
								.append("			<td colspan=\"6\">" + StringUtil.convertEnterToBR(StringUtil.bvl((String) lomDetail.get("antcptnefct"), "")) + "</td>\n").append("  	</tr>\n");
					}
					sb.append("		<tr>\n")
							// 계약기간
							.append(" 	    <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo214", null, locale1) + "</th>\n")
							.append("	        <td colspan=\"6\">" + lomDetail.get("cntrtperiod_startday") + " ~ " + lomDetail.get("cntrtperiod_endday") + "</td>\n").append("		</tr>\n").append("     <tr>\n")
							// 지불/수금 구분
							.append("			<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo215", null, locale1) + "</th>\n");
					if ("C02004".equals((String) lomDetail.get("payment_gbn"))) {
						sb.append("   <td colspan=\"6\">" + lomDetail.get("payment_gbn_nm") + "</td>\n");
					} else {
						sb.append("   <td colspan=\"2\">" + lomDetail.get("payment_gbn_nm") + "</td>\n")
								// 계약금액
								.append("   <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo216", null, locale1) + "</th>\n")
								.append("  	<td align=\"right\" class=\"tR\">" + StringUtil.bvl(lomDetail.get("cntrt_amt"), "0") + "</td>\n")
								// 통화(단위)
								.append("	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo217", null, locale1) + "</th>\n").append("	<td>" + lomDetail.get("crrncy_unit") + "</td>\n");
					}
					sb.append("		</tr>\n");
					if (!"".equals(StringUtil.bvl(lomDetail.get("cntrt_untprc_expl"), ""))) {
						sb.append("     <tr>\n")
								// 단가내역
								.append("         <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo218", null, locale1) + "</th>\n")
								.append("         <td colspan=\"6\">" + StringUtil.bvl(lomDetail.get("cntrt_untprc_expl"), "") + "</td>\n").append("     </tr>\n");
					}
					if (!"".equals(StringUtil.bvl(lomDetail.get("payment_cond"), ""))) {
						sb.append("     <tr>\n")
								// 지불/수금 조건
								.append("	    	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo219", null, locale1) + "</th>\n")
								.append("	        <td colspan=\"6\">" + StringUtil.convertEnterToBR(StringUtil.bvl(lomDetail.get("payment_cond"), "")) + "</td>\n").append("     </tr>\n");
					}
					sb.append("	</table>\n").append("	<div class=\"mt5\"></div>\n")
							// 계약상세정보
							.append("	<div class=\"title_basic\"><h4>" + " " + titleIndex + " " + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo220", null, locale1) + "</h4></div>\n")
							.append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n").append("	<colgroup>\n").append("		<col width=\"20%\" />\n").append("		<col width=\"80%\" />\n")
							.append("	</colgroup>\n");
					if (!"".equals(StringUtil.bvl(lomDetail.get("secret_keepperiod"), ""))) {
						sb.append("     <tr>\n")
								// 비밀유지기간
								.append("         <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo221", null, locale1) + "</th>\n")
								.append("	        <td> " + StringUtil.bvl(lomDetail.get("secret_keepperiod"), "") + "</td>\n").append("     </tr>\n");
					}
					// 특화조건시작
					if (special1List != null && special1List.size() > 0) {
						if (special1List.size() > 0) {
							for (int k = 0; k < special1List.size(); k++) {
								ListOrderedMap s1Lom = (ListOrderedMap) special1List.get(k);
								if (!"".equals(StringUtil.bvl(s1Lom.get("attr_value"), ""))) {
									sb.append("	<tr>\n").append("		<th>" + s1Lom.get("attr_nm") + "</th>\n").append("		<td>" + s1Lom.get("attr_value") + "</td>\n").append("	</tr>\n");
								}
							}
						}
					}

					if (special2List != null && special2List.size() > 0) {
						if (special2List.size() > 0) {

							for (int k = 0; k < special2List.size(); k++) {
								ListOrderedMap s2Lom = (ListOrderedMap) special2List.get(k);
								if (!"".equals(StringUtil.bvl(s2Lom.get("attr_value"), ""))) {
									sb.append("<tr>").append("		<th>" + s2Lom.get("attr_nm") + "</th>\n").append("		<td>" + StringUtil.bvl(s2Lom.get("attr_value"), "") + "</td>\n").append("</tr>\n");
								}
							}

						}
					}
					// 특화정보끝

					if (!"".equals(StringUtil.bvl(lomDetail.get("auto_rnew_yn"), ""))) {
						sb.append("<tr>\n")
								// 자동연장여부
								.append("		<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo222", null, locale1) + "</th>\n");
						if ("Y".equals((String) lomDetail.get("auto_rnew_yn"))) {
							sb.append("	<td>YES</td>\n");
						} else {
							sb.append("	<td>NO</td>\n");
						}
						sb.append("</tr>\n");
					}
					if (!"".equals(StringUtil.bvl(lomDetail.get("oblgt_lmt"), ""))) {
						sb.append("<tr>\n")
								// 배상책임한도
								.append("		<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo223", null, locale1) + "</th>\n")
								.append("		<td>" + StringUtil.bvl(lomDetail.get("oblgt_lmt"), "") + "</td>\n").append("	</tr>\n");
					}
					if (!"".equals(StringUtil.bvl(lomDetail.get("spcl_cond"), ""))) {
						sb.append("	<tr>\n")
								// 기타 특약사항
								.append("		<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo224", null, locale1) + "</th>\n")
								.append("		<td>" + StringUtil.bvl(lomDetail.get("spcl_cond"), "") + "</td>\n").append("	</tr>\n");
					}
					if (!"".equals(StringUtil.bvl(lomDetail.get("loac"), ""))) {
						sb.append("	<tr>\n")
								// 준거법
								.append("	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo225", null, locale1) + "</th>\n").append("		<td>" + lomDetail.get("loac") + "</td>\n")
								.append("	</tr>\n");
					}
					if (!"".equals(StringUtil.bvl(lomDetail.get("loac_etc"), ""))) {
						sb.append("	<tr>\n")
								// 준거법상세
								.append("		<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo226", null, locale1) + "</th>\n")
								.append("		<td>" + StringUtil.convertEnterToBR((String) StringUtil.bvl(lomDetail.get("loac_etc"), "")) + "</td>\n").append("	</tr>\n");
					}
					if (!"".equals(StringUtil.bvl(lomDetail.get("dspt_resolt_mthd"), ""))) {
						sb.append("	<tr>\n")
								// 분쟁해결방법
								.append("		<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo227", null, locale1) + "</th>\n")
								.append(" 	<td>" + lomDetail.get("dspt_resolt_mthd") + "</td>\n").append("	</tr>\n");
					}
					if (!"".equals(StringUtil.bvl(lomDetail.get("dspt_resolt_mthd_det"), ""))) {
						sb.append("	<tr>\n")
								// 분쟁해결방법상세
								.append("		<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo228", null, locale1) + "</th>\n")
								.append("		<td>" + StringUtil.convertEnterToBR((String) StringUtil.bvl(lomDetail.get("dspt_resolt_mthd_det"), "")) + "</td>\n").append("	</tr>\n");
					}
					sb.append(" <tr>\n")
							// 법무팀검토담당자
							.append(" 	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo229", null, locale1) + "</th>\n")
							.append("		<td>" + StringUtil.bvl(lomDetail.get("cnsdman_nm"), "") + "/" + StringUtil.bvl(lomDetail.get("cnsd_jikgup_nm"), "") + "/" + StringUtil.bvl(lomDetail.get("cnsd_dept_nm"), "") + "</td>\n")
							.append("	</tr>\n").append(" <tr>\n")
							// 최종법무검토의견
							.append(" 	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo230", null, locale1) + "</th>\n")
							.append("		<td>" + StringUtil.convertCharsToHtml(StringUtil.bvl((String) lomDetail.get("cnsd_opnn"), "")) + "</td>\n").append("	</tr>\n").append("</table>\n");

					// TODO 2011-12-05 김형준 : 고객요청으로 체결 품의상신시 주요이행사항, 지불계획,
					// 기타이행계획 안보이게 한다.
					if ("C04211".equals((String) lom.get("prgrs_status")) || "C04212".equals((String) lom.get("prgrs_status")) || "C04213".equals((String) lom.get("prgrs_status")) || "C04214".equals((String) lom.get("prgrs_status"))
							|| "C04215".equals((String) lom.get("prgrs_status")) || "C04216".equals((String) lom.get("prgrs_status")) || "C04211".equals((String) lom.get("prgrs_status"))) {

					} else {

						int iC05501Size = 0; // 지불계획
						int iC05502Size = 0; // 수금계획
						int iC05503Size = 0; // 기타이행계획
						for (int j = 0; j < listExec.size(); j++) {
							ListOrderedMap execLom = (ListOrderedMap) listExec.get(j);
							if ("C05501".equals(execLom.get("exec_gbn"))) {
								iC05501Size++;
							} else if ("C05502".equals(execLom.get("exec_gbn"))) {
								iC05502Size++;
							} else {
								iC05503Size++;
							}
						}

						if (iC05501Size != 0 || iC05502Size != 0 || iC05503Size != 0) {

							// 이행사항시작
							sb.append("	<div class=\"mt5\"></div>\n").append("	<div class=\"title_basic\">\n")
									// 주요이행사항
									.append("	<h4>" + titleIndex + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo231", null, locale1) + "</h4>\n").append(" </div>");
							// sb.append("<div style=\"width:100%; clear:both;
							// border-top:2px solid #3E74BE; _border-left:1px
							// solid #fff; margin:0; table-layout:fixed;\">\n");

							String sPayGgn = StringUtil.bvl(lomDetail.get("payment_gbn"), "");
							// if("C02001".equals((String)lomDetail.get("payment_gbn"))
							// ||
							// "C02002".equals((String)lomDetail.get("payment_gbn"))
							// || iC05501Size > 0) { //지불
							if ("C02001".equals(sPayGgn) || "C02002".equals(sPayGgn) || iC05501Size > 0) { // 지불
								sb.append("	<div class=\"mt5\"></div>\n").append("	<div class=\"title_basic\">\n")
										// 지불계획
										.append("	<h5 class=\"ntitle05\">" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo232", null, locale1) + " </h5>\n").append(" </div>")
										.append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style02\">\n");
								if ("".equals(buGubn)) { // 하단의 각 단계 별로 눌렀을 경우
									sb.append("	<colgroup>\n").append(" 	<col width=\"7%\"/>\n").append("		<col width=\"20%\"/>\n").append("		<col width=\"12%\"/>\n").append("		<col width=\"13%\"/>\n")
											.append("		<col width=\"17%\"/>\n")
											// .append(" <col
											// width=\"12%\"/>\n")
											// .append(" <col
											// width=\"16%\"/>\n")
											// .append(" <col
											// width=\"10%\"/>\n")
											.append("	</colgroup>\n");
								} else { // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
									sb.append("	<colgroup>\n").append(" 	<col width=\"7%\"/>\n").append("		<col width=\"13%\"/>\n").append("		<col width=\"12%\"/>\n").append("		<col width=\"13%\"/>\n")
											.append("		<col width=\"17%\"/>\n").append("		<col width=\"12%\"/>\n").append("		<col width=\"16%\"/>\n").append("		<col width=\"10%\"/>\n").append("	</colgroup>\n");
								}
								sb.append("	<thead>\n").append("	<tr>\n");
								if ("".equals(buGubn)) { // 하단의 각 단계 별로 눌렀을 경우(
									sb.append("		<th>No</th>\n")
											// 이행항목
											.append(" 	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo233", null, locale1) + "</th>\n")
											// 완료예정일
											.append(" 	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo234", null, locale1) + "</th>\n")
											// 금액
											.append("		<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo235", null, locale1) + "</th>\n")
											// 지불조건
											.append(" 	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo236", null, locale1) + "</th>\n");
									// .append(" <th>완료일</th>\n")
									// .append(" <th>비고</th>\n")
									// .append(" <th>확인여부</th>\n")
								} else { // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
									sb.append("		<th>No</th>\n")
											// 이행항목
											.append(" 	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo233", null, locale1) + "</th>\n")
											// 완료예정일
											.append(" 	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo234", null, locale1) + "</th>\n")
											// 금액
											.append("		<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo235", null, locale1) + "</th>\n")
											// 지불조건
											.append(" 	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo236", null, locale1) + "</th>\n")
											// 완료일
											.append(" 	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo237", null, locale1) + "</th>\n")
											// 비고
											.append(" 	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo238", null, locale1) + "</th>\n")
											// 확인여부
											.append("		<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo239", null, locale1) + "</th>\n");
								}

								sb.append("	</tr>\n").append("	</thead>\n").append("	<tbody>\n");
								if (iC05501Size > 0) {
									int k = 1;
									for (int j = 0; j < listExec.size(); j++) {
										ListOrderedMap execLom = (ListOrderedMap) listExec.get(j);
										if ("C05501".equals(StringUtil.bvl(execLom.get("exec_gbn"), ""))) {
											sb.append("	<tr>\n");
											if ("".equals(buGubn)) { // 하단의 각 단계
																		// 별로
																		// 눌렀을
																		// 경우(
												sb.append("		<td align=\"center\">" + k + "</td>\n").append("		<td>" + execLom.get("exec_itm") + "</td>\n")
														.append("		<td align=\"center\">" + execLom.get("exec_plndday") + "</td>\n").append("		<td align=\"right\">" + execLom.get("exec_amt") + "</td>\n")
														.append("		<td>" + StringUtil.bvl(execLom.get("exec_cont"), "") + "</td>\n").append("	</tr>\n");
											} else {
												sb.append("		<td align=\"center\">" + k + "</td>\n").append("		<td>" + execLom.get("exec_itm") + "</td>\n")
														.append("		<td align=\"center\">" + execLom.get("exec_plndday") + "</td>\n").append("		<td align=\"right\">" + execLom.get("exec_amt") + "</td>\n")
														.append("		<td>" + StringUtil.bvl(execLom.get("exec_cont"), "") + "</td>\n").append("		<td>" + StringUtil.bvl(execLom.get("exec_cmpltday"), "") + "</td>\n")
														.append("		<td>" + StringUtil.bvl(execLom.get("note"), "") + "</td>\n").append("		<td>" + StringUtil.bvl(execLom.get("exec_statusnm"), "") + "</td>\n").append("	</tr>\n");
											}
											k++;
										}
									}
								}
								sb.append("</tbody>\n").append("</table>\n");
							}

							if ("C02001".equals(sPayGgn) || "C02003".equals(sPayGgn) || iC05502Size > 0) { // 수금
								sb.append("	<div class=\"mt5\"></div>\n").append("	<div class=\"title_basic\">\n")
										// 수금계획
										.append("	<h5 class=\"ntitle05\">" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo240", null, locale1) + "</h5>\n").append(" </div>")
										.append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style02\">\n").append("	<colgroup>\n");
								if ("".equals(buGubn)) { // 하단의 각 단계 별로 눌렀을 경우
									sb.append(" 	<col width=\"7%\"/>\n").append("		<col width=\"20%\"/>\n").append("		<col width=\"12%\"/>\n").append("		<col width=\"13%\"/>\n").append("		<col width=\"17%\"/>\n");
								} else { // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
									sb.append(" 	<col width=\"7%\"/>\n").append("		<col width=\"13%\"/>\n").append("		<col width=\"12%\"/>\n").append("		<col width=\"13%\"/>\n").append("		<col width=\"17%\"/>\n")
											.append("		<col width=\"12%\"/>\n").append("		<col width=\"16%\"/>\n").append("		<col width=\"10%\"/>\n");
								}
								sb.append("	</colgroup>\n").append("	<thead>\n").append("	<tr>\n");
								if ("".equals(buGubn)) { // 하단의 각 단계 별로 눌렀을 경우
									sb.append("		<th>No</th>\n")
											// 이행항목
											.append(" 	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo233", null, locale1) + "</th>\n")
											// 완료예정일
											.append(" 	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo234", null, locale1) + "</th>\n")
											// 금액
											.append("		<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo235", null, locale1) + "</th>\n")
											// 수금조건
											.append(" 	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo241", null, locale1) + "</th>\n");
								} else { // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
									sb.append("		<th>No</th>\n")
											// 이행항목
											.append(" 	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo233", null, locale1) + "</th>\n")
											// 완료예정일
											.append(" 	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo234", null, locale1) + "</th>\n")
											// 금액
											.append("		<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo235", null, locale1) + "</th>\n")
											// 수금조건
											.append(" 	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo241", null, locale1) + "</th>\n")
											// 완료일
											.append(" 	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo237", null, locale1) + "</th>\n")
											// 비고
											.append(" 	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo238", null, locale1) + "</th>\n")
											// 확인여부
											.append("		<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo239", null, locale1) + "</th>\n");
								}
								sb.append("	</tr>\n").append("	</thead>\n").append("	<tbody>\n");
								if (iC05502Size > 0) {
									int k = 1;
									for (int j = 0; j < listExec.size(); j++) {
										ListOrderedMap execLom = (ListOrderedMap) listExec.get(j);
										if ("C05502".equals(StringUtil.bvl(execLom.get("exec_gbn"), ""))) {
											sb.append("	<tr>\n");
											if ("".equals(buGubn)) { // 하단의 각 단계
																		// 별로
																		// 눌렀을
																		// 경우
												sb.append("		<td align=\"center\">" + k + "</td>\n").append("		<td>" + execLom.get("exec_itm") + "</td>\n")
														.append("		<td align=\"center\">" + execLom.get("exec_plndday") + "</td>\n").append("		<td align=\"right\">" + execLom.get("exec_amt") + "</td>\n")
														.append("		<td>" + StringUtil.bvl(execLom.get("exec_cont"), "") + "</td>\n");
											} else { // 상단의 인쇄 버튼 클릭 시 (품의서 출력
														// 버튼)
												sb.append("		<td align=\"center\">" + k + "</td>\n").append("		<td>" + execLom.get("exec_itm") + "</td>\n")
														.append("		<td align=\"center\">" + execLom.get("exec_plndday") + "</td>\n").append("		<td align=\"right\">" + execLom.get("exec_amt") + "</td>\n")
														.append("		<td>" + StringUtil.bvl(execLom.get("exec_cont"), "") + "</td>\n").append("		<td>" + StringUtil.bvl(execLom.get("exec_cmpltday"), "") + "</td>\n")
														.append("		<td>" + StringUtil.bvl(execLom.get("note"), "") + "</td>\n").append("		<td>" + StringUtil.bvl(execLom.get("exec_statusnm"), "") + "</td>\n");
											}
											sb.append("	</tr>\n");
											k++;
										}
									}
								}
								sb.append("</tbody>\n").append("</table>\n");
							}
							if (iC05503Size > 0) {
								sb.append("	<div class=\"mt5\"></div>\n").append("	<div class=\"title_basic\">\n")
										// 기타이행계획
										.append("	<h5 class=\"ntitle05\">" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo242", null, locale1) + "</h5>\n").append(" </div>")
										.append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style02\">\n").append("	<colgroup>\n");
								if ("".equals(buGubn)) { // 하단의 각 단계 별로 눌렀을 경우
									sb.append(" 	<col width=\"7%\"/>\n").append("		<col width=\"20%\"/>\n").append("		<col width=\"12%\"/>\n").append("		<col width=\"30%\"/>\n");
								} else { // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
									sb.append(" 	<col width=\"7%\"/>\n").append("		<col width=\"13%\"/>\n").append("		<col width=\"12%\"/>\n").append("		<col width=\"30%\"/>\n").append("		<col width=\"12%\"/>\n")
											.append("		<col width=\"16%\"/>\n").append("		<col width=\"10%\"/>\n");
								}
								sb.append("	</colgroup>\n").append("	<thead>\n").append("	<tr>\n");
								if ("".equals(buGubn)) { // 하단의 각 단계 별로 눌렀을 경우
									sb.append("		<th>No</th>\n")
											// 이행항목
											.append(" 	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo233", null, locale1) + "</th>\n")
											// 완료예정일
											.append(" 	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo234", null, locale1) + "</th>\n").append(" 	<th>Description</th>\n");
								} else { // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
									sb.append("		<th>No</th>\n")
											// 이행항목
											.append(" 	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo233", null, locale1) + "</th>\n")
											// 완료예정일
											.append(" 	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo234", null, locale1) + "</th>\n").append(" 	<th>Description</th>\n")
											// 완료일
											.append(" 	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo237", null, locale1) + "</th>\n")
											// 비고
											.append(" 	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo238", null, locale1) + "</th>\n")
											// 확인여부
											.append("		<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo239", null, locale1) + "</th>\n");
								}
								sb.append("	</tr>\n").append("	</thead>\n").append("	<tbody>\n");

								int k = 1;
								for (int j = 0; j < listExec.size(); j++) {
									ListOrderedMap execLom = (ListOrderedMap) listExec.get(j);
									if ("C05503".equals(StringUtil.bvl(execLom.get("exec_gbn"), ""))) {
										sb.append("	<tr>\n");
										if ("".equals(buGubn)) { // 하단의 각 단계 별로
																	// 눌렀을 경우
											sb.append("		<td align=\"center\">" + k + "</td>\n").append("		<td>" + execLom.get("exec_itm") + "</td>\n")
													.append("		<td align=\"center\">" + execLom.get("exec_plndday") + "</td>\n").append("		<td>" + StringUtil.bvl(execLom.get("exec_cont"), "") + "</td>\n");
										} else { // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
											sb.append("		<td align=\"center\">" + k + "</td>\n").append("		<td>" + execLom.get("exec_itm") + "</td>\n")
													.append("		<td align=\"center\">" + execLom.get("exec_plndday") + "</td>\n").append("		<td>" + StringUtil.bvl(execLom.get("exec_cont"), "") + "</td>\n")
													.append("		<td>" + StringUtil.bvl(execLom.get("exec_cmpltday"), "") + "</td>\n").append("		<td>" + StringUtil.bvl(execLom.get("note"), "") + "</td>\n")
													.append("		<td>" + StringUtil.bvl(execLom.get("exec_statusnm"), "") + "</td>\n");
										}
										sb.append("	</tr>\n");
										k++;
									}
								}
							}

						}

						sb.append("</tbody>\n").append("</table>\n").append("</div>\n");
						// 이행사항 종료
					}
					sb.append("	<div class=\"mt5\"></div>\n").append("	<div class=\"title_basic\">\n")
							// 체결정보
							.append("	<h4>" + titleIndex + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo243", null, locale1) + "</h4>\n").append(" </div>")
							.append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n").append("	<colgroup>\n").append("		<col width=\"16%\" />\n").append("		<col width=\"16%\" />\n")
							.append("		<col width=\"16%\" />\n").append("		<col width=\"16%\" />\n").append("		<col width=\"16%\" />\n").append("		<col width=\"20%\" />\n").append("	</colgroup>\n").append("		<tr>\n")
							// 직인서명구분
							.append("			<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo244", null, locale1) + "</th>\n");
					if ("C02101".equals(StringUtil.bvl(lomDetail.get("seal_mthd"), ""))) {
						// 직인
						sb.append("	<td colspan=\"2\">" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo245", null, locale1) + "</td>\n");
					} else {
						// 서명
						sb.append("	<td colspan=\"2\">" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo246", null, locale1) + "</td>\n");
					}
					// 만료사전알림일
					sb.append("		</tr>\n");
					sb.append("   	<tr>\n");

					if ("C02101".equals(StringUtil.bvl(lomDetail.get("seal_mthd"), ""))) {
						// 날인담당자
						sb.append("<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo248", null, locale1) + "</th>")
								.append("<td colspan=\"2\">" + StringUtil.bvl((String) lomDetail.get("seal_ffmtman_nm"), "") + "/" + StringUtil.bvl((String) lomDetail.get("seal_ffmtman_jikgup_nm"), "") + "/"
										+ StringUtil.bvl((String) lomDetail.get("seal_ffmt_dept_nm"), "") + "</td>\n");
					} else {
						// 서명예정자
						sb.append("<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo249", null, locale1) + "</th>")
								.append("<td colspan=\"2\">" + StringUtil.bvl((String) lomDetail.get("sign_plndman_nm"), "") + "/" + StringUtil.bvl((String) lomDetail.get("sign_plndman_jikgup_nm"), "") + "/"
										+ StringUtil.bvl((String) lomDetail.get("sign_plnd_dept_nm"), "") + "</td>\n");
					}
					// 체결예정일
					sb.append("		<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo250", null, locale1) + "</th>\n")
							.append("		<td colspan=\"2\">" + lomDetail.get("cnclsn_plndday") + "</td>\n").append("</tr>\n").append("	<tr>\n")
							// 계약담당자
							.append("		<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo251", null, locale1) + "</th>\n")
							.append("		<td colspan=\"5\">" + StringUtil.bvl((String) lomDetail.get("cntrt_respman_nm"), "") + "/" + StringUtil.bvl((String) lomDetail.get("cntrt_respman_jikgup_nm"), "") + "/"
									+ StringUtil.bvl((String) lomDetail.get("cntrt_resp_dept_nm"), "") + "</td>\n")
							.append("	</tr>\n").append("</table>\n");

					sb.append("	<div class=\"mt5\"></div>\n").append("	<div class=\"title_basic\">\n")
							// 첨부파일
							.append("	<h4>" + titleIndex + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo252", null, locale1) + "</h4>\n").append(" </div>")
							.append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n").append("	<colgroup>\n").append("		<col width=\"14%\" />\n").append("		<col />\n")
							.append("	</colgroup>\n").append(" <tr>\n")
							// 계약서
							.append("		<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo253", null, locale1) + "</th>\n");
					if (listContractAttach != null && listContractAttach.size() > 0) {
						tempLom = (ListOrderedMap) listContractAttach.get(0);
						sb.append("<td><a href=\"" + attachinfo + (String) tempLom.get("file_id") + "\">" + (String) tempLom.get("org_file_nm") + "</a></td>\n");
					} else {
						sb.append("");
					}
					sb.append("</tr>\n");

					if (listEtc2Attach != null && listEtc2Attach.size() > 0) {
						for (int k = 0; k < listEtc2Attach.size(); k++) {
							tempLom = (ListOrderedMap) listEtc2Attach.get(k);
							sb.append("<tr>\n");
							if (k == 0) {
								if (listEtc2Attach.size() == 1) {
									// 첨부/별첨
									sb.append("<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo254", null, locale1) + "</td>\n");
								} else {
									// 첨부/별첨
									sb.append("<th rowspan=\"" + listEtc2Attach.size() + "\">" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo254", null, locale1) + "</td>\n");
								}
								sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String) tempLom.get("file_id") + "\">" + (String) tempLom.get("org_file_nm") + "</a></td>\n");
							} else {
								sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String) tempLom.get("file_id") + "\">" + (String) tempLom.get("org_file_nm") + "</a></td>\n");
							}
							sb.append("</tr>\n");
						}
					}

					if (listPreAttach != null && listPreAttach.size() > 0) {
						for (int k = 0; k < listPreAttach.size(); k++) {
							tempLom = (ListOrderedMap) listPreAttach.get(k);
							sb.append("<tr>\n");
							if (k == 0) {
								if (listPreAttach.size() == 1) {
									// 사전승인
									sb.append("<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo255", null, locale1) + "</td>\n");
								} else {
									// 사전승인
									sb.append("<th rowspan=\"" + listPreAttach.size() + "\">" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo255", null, locale1) + "</td>\n");
								}
								sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String) tempLom.get("file_id") + "\">" + (String) tempLom.get("org_file_nm") + "</a></td>\n");
							} else {
								sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String) tempLom.get("file_id") + "\">" + (String) tempLom.get("org_file_nm") + "</a></td>\n");
							}
							sb.append("</tr>\n");
						}
					}

					if (listEtcAttach != null && listEtcAttach.size() > 0) {
						for (int k = 0; k < listEtcAttach.size(); k++) {
							tempLom = (ListOrderedMap) listEtcAttach.get(k);
							sb.append("<tr>\n");
							if (k == 0) {
								if (listEtcAttach.size() == 1) {
									// 기타
									sb.append("<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo256", null, locale1) + "</th>\n");
								} else {
									// 기타
									sb.append("<th rowspan=\"" + listEtcAttach.size() + "\">" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo256", null, locale1) + "</th>\n");
								}
								sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String) tempLom.get("file_id") + "\">" + (String) tempLom.get("org_file_nm") + "</a></td>\n");
							} else {
								sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String) tempLom.get("file_id") + "\">" + (String) tempLom.get("org_file_nm") + "</a></td>\n");
							}
							sb.append("</tr>\n");
						}
					}

					if (listPriceAttach != null && listPriceAttach.size() > 0) {
						for (int k = 0; k < listPriceAttach.size(); k++) {
							tempLom = (ListOrderedMap) listPriceAttach.get(k);
							sb.append("<tr>\n");
							if (k == 0) {
								if (listPriceAttach.size() == 1) {
									// 단가정보
									sb.append("<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo257", null, locale1) + "</th>\n");
								} else {
									// 단가정보
									sb.append("<th rowspan=\"" + listPriceAttach.size() + "\">" + (String) messageSource.getMessage("clm.page.field.consideration.makeApprovalContractInfo257", null, locale1) + "</th>\n");
								}
								sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String) tempLom.get("file_id") + "\">" + (String) tempLom.get("org_file_nm") + "</a></td>\n");
							} else {
								sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String) tempLom.get("file_id") + "\">" + (String) tempLom.get("org_file_nm") + "</a></td>\n");
							}
							sb.append("</tr>\n");
						}
					}
					sb.append("</table>\n");
				}
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
	 * 체결품의상신 후 상태변경 (체결 후 등록 추가 합니다.) @param request, response @return
	 * ModelAndView @throws
	 */
	public ModelAndView modifyConsiderationStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String forwardURL = "";
			String pageGbn = "";

			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();

			bind(request, form);
			bind(request, vo);

			String f_cnsdreq_id = (String) request.getAttribute("f_cnsdreq_id"); // 최초
																					// 의뢰시
																					// 임시저장없이
																					// 바로
																					// 검토의뢰를
																					// 하는
																					// 경우에
																					// 처리하기위해
			if (f_cnsdreq_id != null && !f_cnsdreq_id.equals(""))
				vo.setCnsdreq_id(f_cnsdreq_id);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			pageGbn = StringUtil.bvl(form.getPageGbn(), "");

			if ("registration".equals(pageGbn)) {

				forwardURL = "/clm/manage/consideration.do?method=listManageConsideration&pageGbn=registration";

				vo.setPrgrs_status("C04214"); // 체결후등록 결재중
				vo.setPrcs_depth("C02508"); // 프로세스 단계
				vo.setDepth_status("C02632"); // 단계상태
				vo.setCntrt_status("C02401");

			} else {

				forwardURL = "/clm/manage/consideration.do?method=listManageConsideration";

				vo.setPrgrs_status("C04201"); // 검토의뢰
				vo.setPrcs_depth("C02501");// 프로세스 단계
				vo.setDepth_status("C02602");// 단계상태
				vo.setCntrt_status("C02401");

			}

			considerationService.modifyConsiderationStatus(vo);

			if (!"registration".equals(pageGbn)) {
				considerationService.modifyConsiderationApprove(vo);
			}

			String returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + mav.getViewName());

			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

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
	 * 이력정보 전체조회 @param request, response @return ModelAndView @throws
	 */
	public ModelAndView listContractHis(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter
			 **********************************************************/
			String forwardURL = "";
			ExecutionForm form = null;
			ExecutionVO vo = null;
			List<?> resultList = null;

			ArrayList<?> review = null;
			ArrayList<?> approve = null;
			ArrayList<?> info = null;
			List<?> resultAttachList = null; // 히스토리 내역 첨부파일 전체

			/*********************************************************
			 * Forward setting
			 **********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/common/historyInfo.jsp"; // Sungwoo
																	// Replaced
																	// 2014-09-02

			/*********************************************************
			 * Form & VO Binding
			 **********************************************************/
			form = new ExecutionForm();
			vo = new ExecutionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * Service
			 **********************************************************/
			resultList = considerationService.listHisExecution(vo);

			// 첨부파일 Sungwoo added 2014-09-01
			resultAttachList = considerationService.listCompletionAttachInfo(vo);

			if (resultList != null && resultList.size() > 0) {
				review = (ArrayList) resultList.get(0);
				approve = (ArrayList) resultList.get(1);
				info = (ArrayList) resultList.get(2);

				ClmsDataUtil.debug("#############################");
				ClmsDataUtil.debug("review : " + review.size());
				ClmsDataUtil.debug("approve : " + approve.size());
				ClmsDataUtil.debug("info : " + info.size());
				ClmsDataUtil.debug("#############################");
			}

			/*********************************************************
			 * ModelAndView
			 **********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + mav.getViewName());

			mav.addObject("review", review);
			mav.addObject("approve", approve);
			mav.addObject("info", info);
			mav.addObject("hisCommand", form);
			mav.addObject("resultAttachList", resultAttachList); // Sungwoo
																	// added
																	// 2014-09-02

			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("listContractHis() Exception !!");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("listContractHis() Throwable !!");
		}
	}

	/**
	 * 의뢰작성시 미리보기
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardPreviewPop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);
			String forwardURL = "/WEB-INF/jsp/clm/manage/Consideration_preview_p.jsp";
			String strUrl = (request.getRequestURL()).toString();
			String strAttachUrl = strUrl.substring(0, strUrl.indexOf("/", 7) + 1) + "clms/common/clmsfile.do?method=doClmsDownload&file_id=";

			/*********************************************************
			 * Form & VO Binding
			 **********************************************************/
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();

			String f_cnsdreq_id = (String) request.getAttribute("f_cnsdreq_id"); // 최초
																					// 의뢰시
																					// 임시저장없이
																					// 바로
																					// 검토의뢰를
																					// 하는
																					// 경우에
																					// 처리하기위해
			if (f_cnsdreq_id != null && !f_cnsdreq_id.equals(""))
				vo.setCnsdreq_id(f_cnsdreq_id);

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			List<?> reqList = null; // 의뢰정보
			List<?> contractList = null;
			ListOrderedMap reqLom = null;
			StringBuffer sbContent = null;

			reqList = considerationService.detailConsiderationApprovalRequest(vo);
			contractList = considerationService.listConsiderationApprovalContract(vo);
			List authReqList = considerationService.listContractAuthreq(vo);// 권한요청자
																			// -관련자
			StringBuffer reqAuthInfo = new StringBuffer();
			if (authReqList != null && authReqList.size() > 0) {
				for (int i = 0; i < authReqList.size(); i++) {
					ListOrderedMap authLom = (ListOrderedMap) authReqList.get(i);
					if (i > 0) {
						if (i % 2 == 0 && authReqList.size() > 2) {
							reqAuthInfo.append(",<br/>" + StringUtil.bvl((String) authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String) authLom.get("trgtman_jikgup_nm"), "") + "/"
									+ StringUtil.bvl((String) authLom.get("trgtman_dept_nm"), ""));
						} else {
							reqAuthInfo.append(
									", " + StringUtil.bvl((String) authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String) authLom.get("trgtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String) authLom.get("trgtman_dept_nm"), ""));
						}

					} else {
						reqAuthInfo.append(StringUtil.bvl((String) authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String) authLom.get("trgtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String) authLom.get("trgtman_dept_nm"), ""));
					}
				}
			}
			Locale lc = (Locale) localeResolver.resolveLocale(request);
			String locale = StringUtil.bvl(lc.getLanguage(), "en");

			String reqAuthString = "";
			if (reqAuthInfo != null)
				reqAuthString = reqAuthInfo.toString();
			sbContent = new StringBuffer();
			if (reqList != null && reqList.size() > 0) {
				reqLom = new ListOrderedMap();
				reqLom = (ListOrderedMap) reqList.get(0);

				if ("CLM".equals((String) session.getAttribute("secfw.session.sys_cd"))) {
					makeApprovalHeader(sbContent, strUrl.substring(0, strUrl.indexOf("/", 7)), locale);
					makeApprovalReqInfo(sbContent, reqLom, form, reqAuthString, (String) lc.getLanguage());
				} else if ("LAS".equals((String) session.getAttribute("secfw.session.sys_cd"))) {
					forwardURL = "/WEB-INF/jsp/las/contractManager/Consideration_preview_p.jsp";
					makeApprovalHeader2(sbContent, strUrl.substring(0, strUrl.indexOf("/", 7)), locale);
					makeApprovalReqInfo2(sbContent, reqLom, form, reqAuthString, (String) lc.getLanguage());
				}

				makeApprovalContractInfo(sbContent, contractList, strAttachUrl, vo, (String) lc.getLanguage());
				makeApprovalFooter(sbContent);
			}

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + mav.getViewName());

			mav.addObject("command", form);
			mav.addObject("content", sbContent.toString());
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
	 * 계약의뢰 상세화면 인쇄
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardPrintPop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String forwardURL = "/WEB-INF/jsp/clm/report/ConsiderationPrint.jsp";
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
			this.getLogger().debug("forwardURL: " + mav.getViewName());

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
	 * 선택 되지 않은 계약건의 필수 조건 체크
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void requiredValidation(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			ListOrderedMap requiredLom = new ListOrderedMap();

			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			Locale lc = (Locale) localeResolver.resolveLocale(request);

			/*********************************************************
			 * Service
			 **********************************************************/
			requiredLom = considerationService.searchRequired(vo); // 저장 시 필수 체크
			if ("OK".equals(requiredLom.get("ret_msg"))) {
				requiredLom.put("result", "Y");
				// 정상처리 되었습니다.
				requiredLom.put("message", (String) messageSource.getMessage("clm.page.field.consideration.requiredValidation01", null, lc));

			} else {// 필수 체크에 걸림
				requiredLom.put("result", "N");
				// 다른 계약건에 입력되지 않은 필수 입력 항목이 존재 합니다.
				requiredLom.put("message", (String) messageSource.getMessage("clm.page.field.consideration.requiredValidation02", null, lc));
			}

			JSONObject jo = new JSONObject();
			jo.put("result", requiredLom.get("result"));
			jo.put("message", requiredLom.get("message"));
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
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

	/**
	 * 최종본검토 가능여부 체크
	 * 
	 * @param request
	 * @param response
	 * @return void
	 * @throws Exception
	 */
	public void verifyFinalConsideration(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter
			 **********************************************************/
			ConsultationForm form = null;
			ConsultationVO vo = null;
			String returnValue = null;

			/*********************************************************
			 * Session Check
			 **********************************************************/
			HttpSession session = request.getSession(false);

			if (session == null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			form = new ConsultationForm();
			vo = new ConsultationVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * Service
			 **********************************************************/
			Locale lc = (Locale) localeResolver.resolveLocale(request);

			returnValue = considerationService.verifyFinalConsideration(vo);

			JSONObject jo = new JSONObject();

			if (returnValue.equals("OK")) {// RET_MSG ret_msg
				jo.put("returnMessage", "OK");
			} else {
				// 회신된 검토의뢰건이 없어 최종본의뢰가 불가능합니다.
				jo.put("returnMessage", (String) messageSource.getMessage("clm.page.field.consideration.verifyFinalConsideration01", null, lc));
			}

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
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

	/**
	 * 신규 의뢰 시 폼 기본 데이타 설정
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardInsertConsiderationNew(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter
			 **********************************************************/

			/*********************************************************
			 * Session Check
			 **********************************************************/
			HttpSession session = request.getSession(false);
			/*********************************************************
			 * Forward setting
			 **********************************************************/
			String forwardURL = "";

			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			ListOrderedMap lomRq = new ListOrderedMap();
			bind(request, form);
			bind(request, vo);

			form.setPageGbn(StringUtil.nvl((String) form.getPageGbn(), ""));

			getLogger().debug("##### pageGbn = " + form.getPageGbn()); // 등록 :
																		// ""

			// Sungwoo commented 2014-09-09 Registration 분기 사용하지 않음
			/*
			 * if("registration".equals(form.getPageGbn())){ forwardURL =
			 * "/WEB-INF/jsp/clm/manage/RegConsideration_i.jsp"; // 재의뢰나 최종본
			 * 의뢰(계약검토/회신 상태에서 상세들어가서 재의뢰버튼클릭시) } else { // 등록 forwardURL =
			 * "/WEB-INF/jsp/clm/manage/Consideration_i_new.jsp"; // 최초의뢰
			 * 
			 * // TODO // Crate Request improvement. Need to deploy after
			 * confirmation by EHQ // 2014-07-17 Kevin changed. /*forwardURL =
			 * "/WEB-INF/jsp/clm/manage/SimpleContractCreate.jsp"; // Get user
			 * configuration information for basic information view. String
			 * basicInfoViewCookieVal = ""; Cookie[] cookies =
			 * request.getCookies(); for(int i = 0; i < cookies.length; i++){
			 * if(cookies[i].getName().equals(COOKIE_BASICINFO_VIEW)){
			 * basicInfoViewCookieVal = cookies[i].getValue(); break; } }
			 * 
			 * lomRq.put(COOKIE_BASICINFO_VIEW, basicInfoViewCookieVal);* / }
			 */
			forwardURL = "/WEB-INF/jsp/clm/manage/Consideration_i_new.jsp"; // 최초의뢰

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			/*********************************************************
			 * Service
			 **********************************************************/
			lomRq.put("total_cnt", "1"); // lomRq.total_cnt. 계약 개수
			lomRq.put("req_dept", vo.getSession_dept_cd()); // 의뢰 부서
			lomRq.put("req_dept_nm", vo.getSession_dept_nm()); // 의뢰 부서명
			lomRq.put("reqman_jikgup_nm", vo.getSession_jikgup_nm()); // 의뢰자 직급
			lomRq.put("reqman_id", vo.getSession_user_id()); // 의뢰자 id
			lomRq.put("reqman_nm", vo.getSession_user_nm()); // 의뢰자 명
			lomRq.put("req_dt", DateUtil.getTime("yyyy-MM-dd")); // 의뢰 일자
			lomRq.put("re_dt", DateUtil.getTime("yyyy-MM-dd")); // 회신 일자

			String curYMD = DateUtil.today();

			// 회신 요청일
			// 3일후로 설정
			// Week : 1[일요일], 2[월요일], 3[화요일], 4[수요일], 5[목요일], 6[금요일], 7[토요일]
			if (DateUtil.isDayOfWeek(curYMD, 7)) {
				// 토요일이면 1일 증가 : 3 + 1 = 4
				lomRq.put("re_demndday", DateUtil.formatDate(DateUtil.addDays(DateUtil.today(), 4), "-"));
			} else if (DateUtil.isDayOfWeek(curYMD, 6) || DateUtil.isDayOfWeek(curYMD, 5) || DateUtil.isDayOfWeek(curYMD, 4)) {
				// 수, 목, 금요일이면 2일 증가 : 3 + 2 = 5
				lomRq.put("re_demndday", DateUtil.formatDate(DateUtil.addDays(DateUtil.today(), 5), "-"));
			} else {
				// 그외 (일, 월, 화요일)이면 증가 없음 : 3 + 0 = 3
				lomRq.put("re_demndday", DateUtil.formatDate(DateUtil.addDays(DateUtil.today(), 3), "-"));
			}

			lomRq.put("cntrt_id", considerationService.getId()); // 계약 ID
			lomRq.put("cnsdreq_id", ""); // 2011.11.01 의뢰 아이디 새 포맷으로 변경
			lomRq.put("cntrtperiod_startday", ""); // 계약기간(시작)
			lomRq.put("cntrtperiod_endday", ""); // 계약기간(종료)
			lomRq.put("bfhdcstn_apbtday", ""); // 사전승인승인일
			lomRq.put("cnsd_demnd_cont", ""); // 의뢰내용(검토 요청내용)
			lomRq.put("status", "forwardInsert"); // 상태(JSP에서 사용하는 상태)

			lomRq.put("prev_cnsdreq_id", ""); // 이전의뢰ID
			lomRq.put("plndbn_req_yn", "N"); // 예정본 의뢰 여부

			lomRq.put("crrncy_unit", ""); // 화폐단위
			lomRq.put("cntrt_nm", form.getCntrt_nm());
			lomRq.put("cntrt_oppnt_cd", "");
			lomRq.put("region_gbn", "");
			lomRq.put("cntrt_oppnt_nm", "");
			lomRq.put("cntrt_oppnt_nm", "");
			lomRq.put("cntrt_oppnt_rprsntman", "");
			lomRq.put("cntrt_oppnt_type", "");
			lomRq.put("cntrt_oppnt_respman", "");
			lomRq.put("cntrt_oppnt_telno", "");
			lomRq.put("cntrt_oppnt_email", "");
			lomRq.put("region_gbn", form.getRegion_gbn());

			// 최최 의뢰,재의뢰 , 최종본 의뢰 상태 설정
			form.setReq_status_title(considerationService.getReqStatus((String) lomRq.put("prev_cnsdreq_id", ""), (String) lomRq.put("plndbn_req_yn", "N"), vo));

			// 연관계약정보
			vo.setCntrt_id((String) lomRq.get("cntrt_id"));
			vo.setParent_cntrt_id(StringUtil.nvl(vo.getParent_cntrt_id(), ""));
			String contRc = (String) ((HashMap) considerationService.listRelationContract(vo)).get("contRc");

			ArrayList listDc = new ArrayList();
			ArrayList listRc = new ArrayList();
			ArrayList listCa = new ArrayList();

			listDc.add(lomRq);

			Locale lc = (Locale) localeResolver.resolveLocale(request);
			String locale = StringUtil.bvl(lc.getLanguage(), "en");

			// 공통코드 콤보 세팅
			HashMap comboMap = new HashMap();
			comboMap.put("SYS_CD", "LAS");
			comboMap.put("SELECT", "#");
			comboMap.put("LOCALE", locale);
			comboMap.put("DEL_YN", "N");
			comboMap.put("USEMAN_MNG_ITM1", "");
			comboMap.put("USEMAN_MNG_ITM2", "");
			comboMap.put("USEMAN_MNG_ITM3", "");
			comboMap.put("USEMAN_MNG_ITM4", "");
			comboMap.put("USEMAN_MNG_ITM5", "");

			// 지불 구분
			comboMap.put("GRP_CD", "C020");
			comboMap.put("SELECTED", StringUtil.bvl(form.getPayment_gbn(), ""));
			comboMap.put("ABBR", "F");
			comboMap.put("TYPE", "S");
			String paymentGbnCombo = clmsComService.listComCdByGrpCd(comboMap);

			// 화폐 단위
			comboMap.put("GRP_CD", "C5");
			comboMap.put("SELECTED", (String) lomRq.get("crrncy_unit"));
			comboMap.put("ABBR", "A");
			comboMap.put("TYPE", "S");
			String crrncyUnitCombo = clmsComService.listComCdByGrpCd(comboMap);

			// 업체 타입
			comboMap.put("GRP_CD", "C056");
			comboMap.put("SELECTED", "C05601");
			comboMap.put("ABBR", "F");
			comboMap.put("TYPE", "S");
			String cntrtOppntTypeCombo = clmsComService.listComCdByGrpCd(comboMap);

			// 사전승인 승인방식
			comboMap.put("GRP_CD", "C028");
			comboMap.put("SELECTED", "");
			comboMap.put("ABBR", "A");
			comboMap.put("TYPE", "S");
			String bfhdcstnApbtMthdCombo = clmsComService.listComCdByGrpCd(comboMap);

			// 연관계약 관계유형
			comboMap.put("GRP_CD", "C032");
			comboMap.put("SELECTED", "");
			comboMap.put("ABBR", "A");
			comboMap.put("TYPE", "S");
			String relYypeCombo = clmsComService.listComCdByGrpCd(comboMap);

			// 계약대상
			comboMap.put("GBN", "CONTRACTTYPE");
			comboMap.put("UP_CD", "");
			comboMap.put("SELECTED", "");
			comboMap.put("ABBR", "F");
			comboMap.put("TYPE", "T");
			comboMap.put("ADD_YN", "");
			String cntrtTrgtCombo = clmsComService.getComboHTML(comboMap);

			// TOP_30_CUS
			comboMap.put("GRP_CD", "C056");
			comboMap.put("SELECTED", lomRq.get("top_30_cus"));
			comboMap.put("ABBR", "A");
			comboMap.put("TYPE", "S");
			String cntrt_top30_cus = clmsComService.listComCdByGrpCd(comboMap);

			// Source of contract drafts
			comboMap.put("GRP_CD", "SOC01");
			comboMap.put("SELECTED", "");
			comboMap.put("ABBR", "A");
			comboMap.put("TYPE", "S");
			String srcConDraft = clmsComService.listComCdByGrpCd(comboMap);

			// Related products
			comboMap.put("GRP_CD", "RP01");
			comboMap.put("SELECTED", "");
			comboMap.put("ABBR", "A");
			comboMap.put("TYPE", "S");
			String relPrdCombo = clmsComService.listComCdByGrpCd(comboMap);

			HashMap combo = new HashMap();
			combo.put("paymentGbn", paymentGbnCombo); // 지불구분 콤보
			combo.put("crrncyUnit", crrncyUnitCombo); // 화폐단위 콤보
			combo.put("cntrtOppntType", cntrtOppntTypeCombo); // 업체타입 콤보
			combo.put("bfhdcstnApbtMthd", bfhdcstnApbtMthdCombo); // 사전승인 승인방식
																	// 콤보
			combo.put("relType", relYypeCombo); // 연관계약 관계유형 콤보
			combo.put("cntrtTrgt", cntrtTrgtCombo); // 계약대상 콤보
			combo.put("srcConDraft", srcConDraft); // Source of contract drafts
			combo.put("relPrdCombo", relPrdCombo); // Related products
			combo.put("cntrt_top30_cus", cntrt_top30_cus); // Top30 Customer

			// 로그인한 사람의 회사약어명을 세팅한다.(session에서 가지고 옴.)
			String abbr_comp_nm = (String) session.getAttribute("secfw.session.abbr_comp_nm");

			form.setAbbr_comp_nm(abbr_comp_nm);

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + mav.getViewName());

			mav.addObject("combo", combo);
			lomRq.put("req_dept", form.getSession_dept_cd()); // 의뢰 부서
			lomRq.put("req_dept_nm", form.getSession_dept_nm()); // 의뢰 부서명
			lomRq.put("reqman_jikgup_nm", form.getSession_jikgup_nm()); // 의뢰자
																		// 직급
			lomRq.put("reqman_id", form.getSession_user_id()); // 의뢰자 id
			lomRq.put("reqman_nm", form.getSession_user_nm()); // 의뢰자 명

			mav.addObject("lomRq", lomRq);
			mav.addObject("listDc", listDc);
			mav.addObject("listRc", listRc);
			mav.addObject("listCa", listCa);
			mav.addObject("contRc", contRc);
			mav.addObject("command", form);

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
	 * 계약건 수정 폼으로 전달
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardModifyConsiderationNew(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);

			String forwardURL = "";
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			ListOrderedMap lomCh = new ListOrderedMap();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			String pageGbn = "";

			form.setPageGbn(StringUtil.nvl((String) form.getPageGbn(), ""));

			pageGbn = form.getPageGbn();

			// Sungwoo commented 2014-09-09 Registration 사용하지 않음
			/*
			 * if("registration".equals(pageGbn)){ forwardURL =
			 * "/WEB-INF/jsp/clm/manage/RegConsideration_i.jsp"; } else {
			 * forwardURL = "/WEB-INF/jsp/clm/manage/Consideration_i_new.jsp"; }
			 */
			forwardURL = "/WEB-INF/jsp/clm/manage/Consideration_i_new.jsp";

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			List listDc = null;
			ListOrderedMap lomRq = null;

			if ("registration".equals(pageGbn)) {
				listDc = considerationService.detailConsiderationReg(vo);
			} else {
				listDc = considerationService.detailConsideration(vo);
			}
			if (listDc.size() > 0) {
				lomRq = (ListOrderedMap) listDc.get(0);
				lomRq.put("total_cnt", Integer.toString(listDc.size()));
				lomRq.put("cnsd_demnd_cont", StringUtil.convertBRToEnter(StringUtil.convertCharsToHtml((String) lomRq.get("cnsd_demnd_cont")))); // 검토_요청_내용
				lomRq.put("pshdbkgrnd_purps", StringUtil.convertBRToEnter(StringUtil.bvl((String) lomRq.get("pshdbkgrnd_purps"), ""))); // 추진배경_목적
				lomRq.put("status", form.getStatus());
			}

			String curYMD = DateUtil.today();

			// 3일후로 설정
			// Week : 1[일요일], 2[월요일], 3[화요일], 4[수요일], 5[목요일], 6[금요일], 7[토요일]
			if (DateUtil.isDayOfWeek(curYMD, 7)) {
				// 토요일이면 1일 증가 : 3 + 1 = 4
				lomRq.put("re_demndday", DateUtil.formatDate(DateUtil.addDays(DateUtil.today(), 4), "-"));
			} else if (DateUtil.isDayOfWeek(curYMD, 6) || DateUtil.isDayOfWeek(curYMD, 5) || DateUtil.isDayOfWeek(curYMD, 4)) {
				// 수, 목, 금요일이면 2일 증가 : 3 + 2 = 5
				lomRq.put("re_demndday", DateUtil.formatDate(DateUtil.addDays(DateUtil.today(), 5), "-"));
			} else {
				// 그외 (일, 월, 화요일)이면 증가 없음 : 3 + 0 = 3
				lomRq.put("re_demndday", DateUtil.formatDate(DateUtil.addDays(DateUtil.today(), 3), "-"));
			}

			// 의뢰테이블에 조인된 마스터 테이블에 계약1번 데이타
			vo.setCntrt_id((String) lomRq.get("cntrt_id"));
			vo.setCnclsnpurps_bigclsfcn((String) lomRq.get("cnclsnpurps_bigclsfcn"));
			vo.setCnclsnpurps_midclsfcn((String) lomRq.get("cnclsnpurps_midclsfcn"));

			vo.setStatus("consider");

			List listCa = null;
			List listCh = null;

			// Sungwoo commented 2014-09-09 Registration 사용하지 않음
			/*
			 * if("registration".equals(pageGbn)){ vo.setStatus("registration");
			 * }
			 */

			listCa = considerationService.listContractAuthreq(vo);// 권한요청자 -관련자
			listCh = considerationService.listCnsdreqHold(vo); // 보류사유

			if (!listCh.isEmpty()) {
				lomCh = (ListOrderedMap) listCh.get(0);
			}

			// 최최 의뢰,재의뢰 , 최종본 의뢰 상태 설정
			form.setReq_status_title(considerationService.getReqStatus((String) lomRq.get("prev_cnsdreq_id"), (String) lomRq.get("plndbn_req_yn"), vo));

			// 연관계약정보
			vo.setCntrt_id((String) lomRq.get("cntrt_id"));
			vo.setParent_cntrt_id(StringUtil.nvl(vo.getParent_cntrt_id(), ""));
			vo.setSubmit_status("edit");
			String contRc = (String) ((HashMap) considerationService.listRelationContract(vo)).get("contRc");

			Locale lc = (Locale) localeResolver.resolveLocale(request);
			String locale = StringUtil.bvl(lc.getLanguage(), "en");

			// 공통코드 콤보 세팅
			HashMap comboMap = new HashMap();
			comboMap.put("SYS_CD", "LAS");
			comboMap.put("SELECT", "#");
			comboMap.put("LOCALE", locale);
			comboMap.put("DEL_YN", "N");
			comboMap.put("USEMAN_MNG_ITM1", "");
			comboMap.put("USEMAN_MNG_ITM2", "");
			comboMap.put("USEMAN_MNG_ITM3", "");
			comboMap.put("USEMAN_MNG_ITM4", "");
			comboMap.put("USEMAN_MNG_ITM5", "");

			// 지불 구분
			comboMap.put("GRP_CD", "C020");
			comboMap.put("SELECTED", StringUtil.bvl(lomRq.get("payment_gbn"), ""));
			comboMap.put("ABBR", "F");
			comboMap.put("TYPE", "S");
			String paymentGbnCombo = clmsComService.listComCdByGrpCd(comboMap);

			// 화폐 단위
			comboMap.put("GRP_CD", "C5");
			comboMap.put("SELECTED", (String) lomRq.get("crrncy_unit"));
			comboMap.put("ABBR", "A");
			comboMap.put("TYPE", "S");
			String crrncyUnitCombo = clmsComService.listComCdByGrpCd(comboMap);

			// 업체 타입
			comboMap.put("GRP_CD", "C056");
			comboMap.put("SELECTED", lomRq.get("cntrt_oppnt_type"));
			comboMap.put("ABBR", "F");
			comboMap.put("TYPE", "S");
			String cntrtOppntTypeCombo = clmsComService.listComCdByGrpCd(comboMap);

			// 사전승인 승인방식
			comboMap.put("GRP_CD", "C028");
			comboMap.put("SELECTED", lomRq.get("bfhdcstn_apbt_mthd"));
			comboMap.put("ABBR", "A");
			comboMap.put("TYPE", "S");
			String bfhdcstnApbtMthdCombo = clmsComService.listComCdByGrpCd(comboMap);

			// 연관계약 관계유형
			comboMap.put("GRP_CD", "C032");
			comboMap.put("SELECTED", "");
			comboMap.put("ABBR", "A");
			comboMap.put("TYPE", "S");
			String relYypeCombo = clmsComService.listComCdByGrpCd(comboMap);

			// 계약대상
			comboMap.put("GBN", "CONTRACTTYPE");
			comboMap.put("UP_CD", lomRq.get("cnclsnpurps_midclsfcn"));
			comboMap.put("SELECTED", lomRq.get("cntrt_trgt"));
			comboMap.put("ABBR", "F");
			comboMap.put("TYPE", "T");
			comboMap.put("ADD_YN", "");
			String cntrtTrgtCombo = clmsComService.getComboHTML(comboMap);

			// TOP_30_CUS
			comboMap.put("GRP_CD", "C056");
			comboMap.put("SELECTED", lomRq.get("top_30_cus"));
			comboMap.put("ABBR", "A");
			comboMap.put("TYPE", "S");
			String cntrt_top30_cus = clmsComService.listComCdByGrpCd(comboMap);

			// Source of contract drafts
			comboMap.put("GRP_CD", "SOC01");
			comboMap.put("SELECTED", lomRq.get("cont_draft"));
			comboMap.put("ABBR", "A");
			comboMap.put("TYPE", "S");
			String srcConDraft = clmsComService.listComCdByGrpCd(comboMap);

			// Related products
			comboMap.put("GRP_CD", "RP01");
			comboMap.put("SELECTED", lomRq.get("related_products"));
			comboMap.put("ABBR", "A");
			comboMap.put("TYPE", "S");
			String relPrdCombo = clmsComService.listComCdByGrpCd(comboMap);

			HashMap combo = new HashMap();
			combo.put("paymentGbn", paymentGbnCombo); // 지불구분 콤보
			combo.put("crrncyUnit", crrncyUnitCombo); // 화폐단위 콤보
			combo.put("cntrtOppntType", cntrtOppntTypeCombo); // 업체타입 콤보
			combo.put("bfhdcstnApbtMthd", bfhdcstnApbtMthdCombo); // 사전승인 승인방식
																	// 콤보
			combo.put("relType", relYypeCombo); // 연관계약 관계유형 콤보
			combo.put("cntrtTrgt", cntrtTrgtCombo); // 계약대상 콤보

			combo.put("srcConDraft", srcConDraft); // Source of contract drafts
			combo.put("relPrdCombo", relPrdCombo); // Related products
			combo.put("cntrt_top30_cus", cntrt_top30_cus); // Top30 Customer

			// 로그인한 사람의 회사약어명을 세팅한다.(session에서 가지고 옴.)
			String abbr_comp_nm = (String) session.getAttribute("secfw.session.abbr_comp_nm");

			form.setAbbr_comp_nm(abbr_comp_nm);

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + mav.getViewName());

			mav.addObject("combo", combo); // 콤보
			mav.addObject("lomRq", lomRq); // 의뢰 정보
			mav.addObject("listDc", listDc); // 의뢰 정보 리스트
			mav.addObject("listCa", listCa); // 관련자 정보
			mav.addObject("contRc", contRc); // 연관 계약 정보
			mav.addObject("lomCh", lomCh); // 보류 정보
			mav.addObject("command", form);

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
	 * 계약 상세내역
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView detailConsiderationNew(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);
			String forwardURL = "";
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			ModelAndView mav = new ModelAndView();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			form.setPageGbn(StringUtil.nvl((String) form.getPageGbn(), ""));

			// Sungwoo commented 2014-09-09 Registration 분기 사용하지 않음
			/*
			 * if("registration".equals(form.getPageGbn())){ forwardURL =
			 * "/WEB-INF/jsp/clm/manage/RegConsideration_d_new.jsp"; // 체결 후 등록
			 * } else { forwardURL =
			 * "/WEB-INF/jsp/clm/manage/Consideration_d_new.jsp"; // 계약 상세 화면 }
			 */
			forwardURL = "/WEB-INF/jsp/clm/manage/Consideration_d_new.jsp"; // 계약
																			// 상세
																			// 화면

			List listDc = null;

			// Sungwoo commented 2014-09-09 Registration 분기 사용하지 않음
			/*
			 * if("registration".equals(form.getPageGbn())){ listDc =
			 * considerationService.detailConsiderationReg(vo); } else { listDc
			 * = considerationService.detailConsideration(vo); }
			 */
			listDc = considerationService.detailConsideration(vo);

			ListOrderedMap lomRq = (ListOrderedMap) listDc.get(0);

			// 나모 잔여 태그 제거
			lomRq.put("cnsd_demnd_cont", StringUtil.convertEnterToBR(StringUtil.bvl((String) lomRq.get("cnsd_demnd_cont"), "")));

			lomRq.put("total_cnt", Integer.toString(listDc.size()));

			// 의뢰테이블에 조인된 마스터 테이블에 계약1번 데이타
			vo.setCntrt_id((String) lomRq.get("cntrt_id"));
			vo.setCnclsnpurps_bigclsfcn((String) lomRq.get("cnclsnpurps_bigclsfcn"));
			vo.setCnclsnpurps_midclsfcn((String) lomRq.get("cnclsnpurps_midclsfcn"));

			ArrayList deferList = new ArrayList();

			String arr_cntrt_id[] = new String[listDc.size()];
			for (int j = 0; j < listDc.size(); j++) {
				int k = j + 1;

				ListOrderedMap lomRq_cntrt_id = (ListOrderedMap) listDc.get(j);
				ClmsDataUtil.debug("(String)lomRq_cntrt_id.get()  : " + (String) lomRq_cntrt_id.get("cntrt_id"));
				arr_cntrt_id[j] = (String) lomRq_cntrt_id.get("cntrt_id");
				vo.setArr_cntrt_id(arr_cntrt_id);
				vo.setStatus(Integer.toString(k));

				// 보류 / 보류 해제 버튼 활성화을 위한
				deferList.add(lomRq_cntrt_id.get("depth_status"));
			}

			if (deferList.contains("C02607")) { // 보류상태
				lomRq.put("defer_btn", "cancel");
			} else {
				lomRq.put("defer_btn", "defer");
			}

			ClmsDataUtil.debug("arr_cntrt_idarr_cntrt_idarr_cntrt_id: " + arr_cntrt_id);

			vo.setStatus("consider");
			List listCh = considerationService.listCnsdreqHold(vo); // 보류사유

			// 변경사항
			lomRq.put("plndbn_chge_cont", StringUtil.convertEnterToBR(StringUtil.nvl((String) lomRq.get("plndbn_chge_cont"), "")));

			// 최최 의뢰,재의뢰 , 최종본 의뢰 상태 설정
			// 2012.03.07 타이틀을 목록의 상태명으로 나오도록 수정 added by hanjihoon
			form.setReq_status_title(considerationService.getReqStatus((String) lomRq.get("prev_cnsdreq_id"), (String) lomRq.get("plndbn_req_yn"), vo));

			/**************************************************
			 * 계약정보 상세 기존 detailContractMaster() 메소드에 있는 정보
			 **************************************************/

			/******* 1. 첫번째 계약ID의 계약 상세 조회한다. *******/
			List listDc2 = considerationService.detailContractMaster(vo);

			ListOrderedMap lomRq2 = (ListOrderedMap) listDc2.get(0);

			// 기타주요사항 <BR>ETC_MAIN_CONT
			lomRq2.put("etc_main_cont", StringUtil.convertEnterToBR((String) lomRq2.get("etc_main_cont")));
			// 단가내역 요약cntrt_untprc_expl
			lomRq2.put("cntrt_untprc_expl", StringUtil.convertEnterToBR(StringUtil.bvl((String) lomRq2.get("cntrt_untprc_expl"), "")));
			// 책임한도 oblgt_lmt
			lomRq2.put("oblgt_lmt", StringUtil.convertEnterToBR(StringUtil.bvl((String) lomRq2.get("oblgt_lmt"), "")));
			// Special Condition spcl_cond
			lomRq2.put("spcl_cond", StringUtil.convertEnterToBR(StringUtil.bvl((String) lomRq2.get("spcl_cond"), "")));
			// loac_etc 준거법 상세
			lomRq2.put("loac_etc", StringUtil.convertEnterToBR(StringUtil.bvl((String) lomRq2.get("loac_etc"), "")));
			// dspt_resolt_mthd_det 분쟁해결방법상세
			lomRq2.put("dspt_resolt_mthd_det", StringUtil.convertEnterToBR(StringUtil.bvl((String) lomRq2.get("dspt_resolt_mthd_det"), "")));
			// 추진목적
			lomRq2.put("pshdbkgrnd_purps", StringUtil.convertEnterToBR(StringUtil.bvl((String) lomRq2.get("pshdbkgrnd_purps"), "")));

			/******* 2. 특화정보를 조회한다. *******/
			vo.setCnclsnpurps_bigclsfcn((String) lomRq2.get("cnclsnpurps_bigclsfcn"));
			vo.setCnclsnpurps_midclsfcn((String) lomRq2.get("cnclsnpurps_midclsfcn"));

			vo.setPlndbn_req_yn((String) lomRq2.get("plndbn_req_yn"));
			vo.setCnsd_status((String) lomRq2.get("cnsd_status"));

			// 최최 의뢰,재의뢰 , 최종본 의뢰 상태 설정
			form.setReq_status_title(considerationService.getReqStatus((String) lomRq2.get("prev_cnsdreq_id"), (String) lomRq2.get("plndbn_req_yn"), vo));
			form.setPlndbn_req_yn(vo.getPlndbn_req_yn());

			ListOrderedMap returnLom = considerationService.getFilevalidate(vo);
			form.setFile_validate((String) returnLom.get("fileValidate"));

			// 계약 id 전달
			vo.setCntrt_id((String) lomRq.get("cntrt_id"));
			vo.setParent_cntrt_id(StringUtil.nvl(vo.getParent_cntrt_id(), ""));
			vo.setSubmit_status("");
			HashMap contRcMap = considerationService.listRelationContract(vo);

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

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " + mav.getViewName());

			mav.addObject("lomRq", lomRq); // 의뢰 정보
			mav.addObject("lomRq2", lomRq2); // 계약 정보
			mav.addObject("contRc", contRcMap.get("contRc")); // 연관계약
			mav.addObject("listDc", listDc); // 의뢰 정보 LIST
			mav.addObject("command", form);

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
	 * 2014-04-03 Kevin Added 페이지 구분에 따라 GERP Detail로 이동한다.
	 * 
	 * Iframe으로 되어 있는 GERP 상세 페이지의 src에 정의될 경로를 통해 호출되며 바인딩 할 페이지 경로를 리턴한다. R은
	 * readonly 페이지, I는 input 페이지로 리턴.
	 */
	public ModelAndView forwardGERPDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String cntrtid = request.getParameter("cntrt_id");
			String pageType = request.getParameter("gerpPageType");
			String forwardURL = (pageType.equals("R") ? "/WEB-INF/jsp/clm/manage/GERPInfo_Readonly.jsp" : "/WEB-INF/jsp/clm/manage/GERPInfo_inputForm.jsp");

			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			if (pageType.equals("R")) {
				mav.addObject("cntrtid", cntrtid);
			}
			return mav;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(ex);
		}
	}

	/*
	 * 2014-04-01 Kevin Added. GERP detail readonly 페이지에서 ajax를 통해 데이터를 조회한다.
	 */
	public void getGERPInformation(HttpServletRequest request, HttpServletResponse response) throws Exception {

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", "succeeded");

		try {
			Object cntrtID = request.getParameter("cntrtid");

			if (cntrtID == null) {
				jsonObj.remove("result");
				jsonObj.put("result", "ID is null");

				return;
			}

			GERPInfoVO vo = new GERPInfoVO();
			vo.setCntrtID(cntrtID.toString());
			List list = considerationService.getGERPInformationDetail(vo);
			if (list == null || list.size() == 0) {
				jsonObj.remove("result");
				jsonObj.put("result", "no detail information");
				return;
			}

			ListOrderedMap detailInfo = (ListOrderedMap) list.get(0);
			jsonObj.put("name", detailInfo.get("CUSTOMER_NM1"));
			jsonObj.put("gerpCd", detailInfo.get("GERP_CD"));
			jsonObj.put("mandatory", detailInfo.get("MANDATORY"));
			jsonObj.put("vendorType", detailInfo.get("VENDOR_TYPE"));
			jsonObj.put("vebderTypeDesc", detailInfo.get("VENDOR_TYPE_DESC"));
			jsonObj.put("divCode", detailInfo.get("CONTRACT_DIV_CODE"));
			jsonObj.put("divCodeDesc", detailInfo.get("GERP_DIVISION"));
			jsonObj.put("contractType", detailInfo.get("CONTRACT_TYPE"));
			jsonObj.put("contractTypeDesc", detailInfo.get("CONTRACT_TYPE_DESC"));
			jsonObj.put("costType", detailInfo.get("COST_TYPE"));
			jsonObj.put("costTypeDesc", detailInfo.get("COST_TYPE_DESC"));

		} catch (Exception ex) {
			jsonObj.remove("result");
			jsonObj.put("result", ex.getMessage());
		} finally {
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = response.getWriter();

			out.print(jsonObj);
			out.flush();
			out.close();
		}
	}

	/**
	 * 타입 별로 GERP의 코드 리스트를 조회한다. 20140-04-08 Kevin added.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getGERPCodeListByType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", "succeeded");

		try {
			String codeType = request.getParameter("gerpCodeType").toString();
			HttpSession session = request.getSession(false);
			String compCd = (session != null ? StringUtil.bvl(session.getAttribute("secfw.session.comp_cd"), "") : "");
			GERPInfoVO vo = new GERPInfoVO();
			vo.setGERPCodeType(codeType);
			vo.setCompCd(compCd);

			List list = considerationService.getGERPCodeListByType(vo);

			if (list == null || list.size() == 0) {
				jsonObj.remove("result");
				jsonObj.put("result", "no data");
				return;
			}

			JSONArray codeList = new JSONArray();
			for (Object item : list) {
				ListOrderedMap row = (ListOrderedMap) item;
				Map map = new HashMap();
				map.put("code", row.get("GERP_CODE").toString());
				map.put("desc", row.get("GERP_CODE_DESC").toString());
				codeList.add(map);
			}

			jsonObj.put("list", codeList);
		} catch (Exception ex) {
			jsonObj.remove("result");
			jsonObj.put("result", ex.getMessage());
		} finally {
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = response.getWriter();

			out.print(jsonObj);
			out.flush();
			out.close();
		}
	}

	/**
	 * 계약관리 의뢰 복사 저장하기
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView insertCopyCnsd(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession(false);

		/*********************************************************
		 * Form 및 VO Binding
		 **********************************************************/
		ConsultationForm form = new ConsultationForm();
		ConsultationVO vo = new ConsultationVO();
		ModelAndView mav = new ModelAndView();

		// bind
		bind(request, form);
		bind(request, vo);

		COMUtil.getUserAuditInfo(request, vo);

		String forwardURL = "/clm/manage/myContract.do?method=listMyContract&status_mode=myContract";
		String returnMessage = null;

		vo.setReqman_id((String) session.getAttribute("secfw.session.user_id")); // 검토의뢰자
																					// ID
		vo.setReqdept_nm((String) session.getAttribute("secfw.session.dept_nm")); // 검토의뢰자
																					// 부서명
		// vo.setCntrt_resp_up_dept((String)session.getAttribute("secfw.session.up_level_dept_cd"));
		// //계약_담당_상위_부서
		vo.setCntrt_resp_up_dept((String) session.getAttribute("secfw.session.dept_cd")); // 계약_담당_부서
		vo.setRe_demndday(considerationService.getReDemndday());// 회신요청일

		try {
			// 3. 등록 서비스 호출
			String result = considerationService.insertCopyCnsd(vo);

			// 4. 등록에 성공하면 리스트로 이동한다.
			// 모든 검색 조건은 초기화 되며 1페이지로 이동한다.
			if (result.equals("OK")) {
				// 메시지 처리 - 검토의뢰가 복사 되었습니다.
				returnMessage = (String) messageSource.getMessage("clm.page.field.consideration.insertCopyCnsd01", null, new RequestContext(request).getLocale());// messageSource.getMessage("secfw.msg.succ.insert",
																																									// null,
																																									// new
																																									// RequestContext(request).getLocale())
																																									// ;

			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			// 5. 실패하면 에러메시지 세팅 후 등록화면으로 이동
			returnMessage = messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale());
			forwardURL = "/clm/manage/myContract.do?method=listMyContract&status_mode=myContract";
			e.printStackTrace();
		}

		mav.setViewName(forwardURL);
		this.getLogger().debug("forwardURL: " + mav.getViewName());

		mav.addObject("command", form);
		mav.addObject("returnMessage", returnMessage);
		return mav;
	}

	/**
	 * 미리보기
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardPrintNew(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);

		/*********************************************************
		 * Form 및 VO Binding
		 **********************************************************/
		ConsiderationForm form = new ConsiderationForm();
		ConsiderationVO vo = new ConsiderationVO();
		ModelAndView mav = new ModelAndView();
		bind(request, form);
		bind(request, vo);

		COMUtil.getUserAuditInfo(request, form);
		COMUtil.getUserAuditInfo(request, vo);

		String forwardURL = "/WEB-INF/jsp/clm/manage/print_new.jsp";
		String returnMessage = "";
		StringBuffer html = new StringBuffer();
		String strUrl = (request.getRequestURL()).toString();
		String strAttachUrl = strUrl.substring(0, strUrl.indexOf("/", 7) + 1) + "clms/common/clmsfile.do?method=doClmsDownload&file_id=";

		try {

			Map result = result = considerationService.getConsiderationInfoPrint(vo); // 의뢰/계약/승인
																						// 정보
			String reqAuthInfo = considerationService.getReqAuthPrint(vo); // 관련자
			List etcAttachList = considerationService.getEtcAttachList(vo); // 그외
																			// 첨부파일

			// 승인이력 정보
			ArrayList review = null;
			ArrayList approve = null;
			ArrayList sign = null;
			ArrayList info = null;
			ArrayList defer = null;

			ExecutionVO execVo = new ExecutionVO();
			execVo.setCntrt_id((String) result.get("cntrt_id"));
			execVo.setCnsdreq_id((String) result.get("cnsdreq_id"));
			execVo.setSession_user_locale(vo.getSession_user_locale());
			List resultList = considerationService.getHisList(execVo);

			if (resultList != null && resultList.size() > 0) {
				review = (ArrayList) resultList.get(0);
				approve = (ArrayList) resultList.get(1);
				sign = (ArrayList) resultList.get(2);
				info = (ArrayList) resultList.get(3);
				defer = (ArrayList) resultList.get(4);

				ClmsDataUtil.debug("#############################");
				ClmsDataUtil.debug("review : " + review.size());
				ClmsDataUtil.debug("approve : " + approve.size());
				ClmsDataUtil.debug("sign : " + sign.size());
				ClmsDataUtil.debug("info : " + info.size());
				// ClmsDataUtil.debug("hold : " +hold.size());
				ClmsDataUtil.debug("#############################");
			}
			// 최종 건을 구하기 위한 maxrm 변수 세팅
			// 회신 내용 구하기(가장 마지막 회신내용)
			int maxrm = 0;
			ListOrderedMap lomRe = null;
			if (review != null) {
				ListOrderedMap _lom = new ListOrderedMap();
				for (int i = 0; i < review.size(); i++) {
					_lom = (ListOrderedMap) review.get(i);

					if (maxrm < Integer.parseInt(String.valueOf(_lom.get("rm")))) {
						maxrm = Integer.parseInt(String.valueOf(_lom.get("rm")));
					}
				}

				// 마지막 회신내용
				for (int i = review.size() - 1; i >= 0; i--) {
					lomRe = (ListOrderedMap) review.get(i);
					if ("REPLY__".equals(lomRe.get("cr_flag"))) {

						break;
					}
				}
			}

			Locale lc = (Locale) localeResolver.resolveLocale(request);
			makePrintHeader(html, result, strUrl.substring(0, strUrl.indexOf("/", 7)), (String) lc.getLanguage());

			/********** 1. 결재라인 정보 **********/
			makePrintApprLine(html, result, vo, (String) lc.getLanguage());

			/********** 2. 검토의뢰 정보 **********/
			makePrintReqHtml(html, result, vo, reqAuthInfo, (String) lc.getLanguage()); // 검토의뢰정보
																						// HTML

			/********** 3. 계약정보 **********/
			makePrintCntrtHtml(html, result, vo, etcAttachList, (String) lc.getLanguage());

			/********** 4. 사전승인정보 ***********/
			makePrintPreApproveHtml(html, result, vo, etcAttachList, (String) lc.getLanguage());

			/********** 5. 연관계약정보 ***********/
			makePrintRelationHtml(html, result, (String) lc.getLanguage());

			/********** 6. 체결정보 ***********/
			makePrintConclusionHtml(html, result, etcAttachList, reqAuthInfo, (String) lc.getLanguage());

			/********** 7. 이행정보 ***********/
			makePrintExecHtml(html, result, (String) lc.getLanguage());

			/********** 8. 종료정보 ***********/
			makePrintCompletionHtml(html, result, vo, etcAttachList, (String) lc.getLanguage());

			/********** 9. 검토이력 ***********/
			makeRespHisHtml(html, review, (String) lc.getLanguage());

			/********** 10. 승인이력 ***********/
			makeAprroveHisHtml(html, result, approve, (String) lc.getLanguage());

			/********** 11. 자동연장이력 ***********/
			makeAutoRnewHisHtml(html, result, vo, (String) lc.getLanguage());

			makeApprovalFooter(html);
		} catch (Exception e) {
			e.printStackTrace();
			returnMessage = messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale());
		}

		// bind

		mav.setViewName(forwardURL);
		this.getLogger().debug("forwardURL: " + mav.getViewName());

		mav.addObject("content", html.toString());
		mav.addObject("returnMessage", returnMessage);
		return mav;
	}

	/**
	 * 인쇄용 스타일 설정
	 */
	public void makePrintHeader(StringBuffer sb, Map map, String url, String last_locale) throws Exception {
		try {
			Locale locale1 = new Locale(last_locale);

			sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n").append("<html xmlns=\"http://www.w3.org/1999/xhtml\"  lang=\"ko\" xml:lang=\"ko\">\n")
					.append("<head>\n").append("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge; charset=utf-8\" />\n")
					// ::: 삼성전자 계약관리시스템 :::
					.append("<title>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintHeader01", null, locale1) + "</title>\n").append("<style>\n")
					.append("body, div, h1, h2, h3, h4, h5, h6, ul, ol, li, dl, dt, dd, p, form, fieldset, input, table, tr, th, td, a{margin:0;padding:0; font-family:gulim, arial;}\n")
					.append("html {height:100%; scrollbar-face-color: #FFFFFF; scrollbar-shadow-color: #CCCCCC; scrollbar-highlight-color: #CCCCCC; scrollbar-3dlight-color: #FFFFFF; scrollbar-darkshadow-color: #FFFFFF;  scrollbar-track-color: #F7F7F7; scrollbar-arrow-color: #B8B8B8;}\n")
					.append("body {height:100%; font-family:nanumgothic, Dotum, tahoma, Arial;color:#363636;font-size:12px;background:#fff;*word-break:break-all;-ms-word-break:break-all;}\n")
					.append("h1,h2,h3,h4,h5,h6,pre,code {font-size:1em; margin:0; padding:0}\n").append("ul,ol,li,h1,h2,h3,h4,h5,h6,pre,form,body,html,p,blockquote,fieldset,input {margin:0; padding:0}\n")
					.append("table {clear:both; border-collapse:collapse; border-spacing:0;}\n").append("table th,table td {font:normal 12px/15px gulim,arial; }\n").append(":focus {outline: 0;} \n")
					.append("img, fieldset, iframe {border:0 none;}\n").append("ul, ol, li {list-style:none;}\n").append("img, input, textarea,select {font-family:gulim, Arial; vertical-align:middle;}\n")
					.append("input {height:16px; line-height:16px;}\n").append("*html input {height:18px; line-height:16px;}\n").append("hr {display:none; clear:both;}\n")
					.append("a { font-family:gulim, Arial; text-decoration:none; cursor:pointer;}\n").append("a:hover {color:#17549f; text-decoration:none; font-family:gulim, Arial;}\n").append("em {font-style:normal;}\n")
					.append("address {font-style:normal; padding:7px;}\n").append("button, label {cursor:pointer;}\n").append("form {padding:0;border:0 none;  font-size:12px;}\n")
					.append("select,text,input {font-size:12px; color:#5D6879;}\n").append("textarea {color:#5D6879;font-size:12px; border:1px solid #7F9DB9;padding:5px;line-height:18px; word-wrap:break-word!important}\n")
					.append("legend {visibility:hidden; height:0; width:0; font-size:0;}\n").append("p {line-height:16px;}\n").append("select {margin:0 0 0 0px;}\n").append("#m_wrap {margin:0 0 0 10px; width: 980px;}\n")
					.append(".m_header h1 {background:url(" + url + "/images/clm/" + last_locale + "/common/system_logo.gif) no-repeat left center; text-indent:-1000px; height:32px;}\n")
					.append(".m_header h2 {position:relative;background:url(" + url + "/images/clm/" + last_locale
							+ "/mail/bg_top_mail.jpg) no-repeat left top; width:980px; height:33px; color:#fff; font:bold 14px/32px nanumgothic; text-indent:1.2em;}\n")
					.append(".m_header h2 span.confidential {position: absolute; right:30px; top:6px; }\n").append(".menu2 h2 {background-position:left -33px;}\n").append("th.borTz02 {border-top:0;}\n")
					.append("*:first-child+html th.borTz02 {border-top:1px solid #CADBE2;}\n").append("#m_container {width:968px; padding:0; margin:0; float:left;border:1px solid #cdd6d9; }\n")
					.append("#m_container .contents {background:#fff; margin:0 ; padding:15px 15px ; width:938px;}\n")
					.append("#m_container .contents h3 {margin-bottom:8px; height:32px; background:#EAF4F6 url(" + url + "/images/clm/" + last_locale
							+ "/mail/pncacc.gif) repeat-x left top; border:1px solid #CFD6E0; border-color:#34A8DB #34A8DB #2b66b9 #34A8DB;}\n")
					.append("#m_container .contents h3 p {padding:7px 10px 5px 10px;font-size:13px; font-family:nanumgothic; color:#3E3E3E;}\n")
					.append("#m_footer {float:left; width:950px; height:35px; border:0px solid #cdd6d9; border-top:0px solid #e5e5e5; color:#818181; font:normal 11px/31px arial; margin: 10px 10px;}\n")
					.append(".copy {font-family:\"돋움\", Arial; font-size:11px; color: #777; background-color: #f9f8f8; border:1px solid #ddd; margin:15px 0 0 0; padding: 10px 10px 10px 10px; line-height:120%;word-break:keep-all;}\n")
					.append(".copy span {font-family:Arial; font-size:10px; color: #777;}\n").append(".copy br {line-height:60%;}\n").append(".title_basic {float:left; clear:both; overflow:hidden;}\n")
					.append(".title_basic3 {background:url(" + url + "/images/clm/" + last_locale
							+ "/icon/bu_dot_org2.gif) no-repeat 3px 7px; padding-left:10px;clear:both; overflow:hidden;margin-top:10px;font-weight:bold; line-height:140% }\n")
					.append("*+html .title_basic {padding:1px 0 5px;}\n")
					.append(".title_basic h4 {min-height:20px; color:#353D4A; font:bold 14px nanumgothic; background:url(" + url + "/images/clm/" + last_locale + "/icon/bu_arrow_t1.gif) no-repeat 3px 15px; padding:16px 10px 2px 18px;}\n")
					.append("*html .title_basic h4 {padding:12px 10px 6px 18px;}\n").append("/* table_style01 */ \n")
					.append(".table-style01 {width:100%; border-top:2px solid #3e74be; _border-left:1px solid #fff; margin:0; clear:both; table-layout:fixed;}\n")
					.append(".table-style01 th {line-height:18px; padding:4px 10px; background:#f0f7fc; text-align:left; border:1px solid #CBDCE4; color:#1d6498; font-weight:normal;border-right:0;}\n")
					.append(".table-style01 td {line-height:18px;  padding:4px 10px;border-left:1px solid #cadbe2;border-bottom:1px solid #CADBE2;color:#7b858e;}\n").append(".table-style01 tr td {border-left:1px solid #CADBE2}\n")
					.append(".table-style01 tr.end th,.table-style01 tr.end td {border-bottom:1px solid #7696c2;}\n").append(".table-style01 a {color:#2f8cb7;}\n")
					.append(".table-style01 a:hover {color:#0061b6; text-decoration:underline;}\n").append(".table-style01 .on td, .list_sub .on td {color:#259bb8;}\n")
					.append(".table-style01 th:last-child, .table-style01 td:last-child {border-right:0}  \n").append(".table-style01 th:first-child,.table-style01 th.sub:first-child, .table-style01 td:first-child {border-left:0} \n")
					.append(".table-style01 td img {vertical-align:middle;}\n").append(".table-style01 td a {vertical-align:middle;}\n").append(".borz01 {margin-top:-1px; border-top:0;}\n").append("/* table-style_sub */\n")
					.append(".table-style_sub {border-top:1px solid #3e92be; _border-left:1px solid #fff; margin:0; clear:both;table-layout:fixed;}\n").append("*+html .table-style_sub {border-left:1px solid #fff;}\n")
					.append(".table-style_sub th {line-height:18px; text-align:center; padding:2px; background:#e8eff5; border:1px solid #3e92be; border-bottom-color:#7597bd; color:#637893; font-weight:normal;border-right:0;}\n")
					.append(".table-style_sub td {line-height:18px;text-align:center;   padding:2px; border-left:1px solid #dcdcdc;border-bottom:1px solid #dcdcdc;color:#79868c; }\n")
					.append(".table-style_sub th:last-child, .table-style_sub td:last-child {border-right:0}  \n")
					.append(".table-style_sub th:first-child,.table-style_sub th.sub:first-child, .table-style_sub td:first-child {border-left:0} \n").append(".table-style_sub td img {vertical-align:middle;}\n")
					.append(".table-style_sub td a {vertical-align:middle;}\n").append(".mt5  {margin-top:5px;}\n").append(".mt7  {margin-top:7px;}\n").append("/***** Color *****/\n").append(".blueD {color:#5f6da4}\n")
					.append(".th-color {color:#1d6498;}\n").append(".tal_lineL {border-left:1px solid #cadbe2 !important;}\n").append(".text_area_full {border:1px solid #7F9DB9 !important; width:99%; padding:0 1px; margin:0 0px 0 -1px;}\n")
					.append(":root .text_calendar, :root .text_search {padding-top:2px; height:14px;}\n").append(".text_search {border:1px solid #7F9DB9; border-right:none; width:105px; padding:0 1px; margin:0 0 0 0px; }\n")
					.append(".text_calendar {border:1px solid #7F9DB9; border-right:none; width:80px; padding:0 1px; margin:0 0 0 -1px; }\n").append(".text_full {width:99%; border:1px solid #7F9DB9; padding:0 1px; margin:0 0px 1px 0px;}\n")
					.append(":root .text_full {width:99%; margin:0 0px 0 -2px; padding:1px 1px 0 1px; height:15px;} \n").append(".txt_style02 {color:#00abe3; font-weight:bold; line-height:20px;}\n")
					.append(".tC {text-align:center !important; padding-left:2px !important;padding-right:2px !important;}\n").append(".tR {text-align:right !important; padding-left:2px !important;padding-right:10px !important;}\n")
					.append("</style>\n").append("</head>\n").append("<body>");

			String reqStatusTitle = null;
			String reqTitle1 = null;

			// 계약검토인 경우
			if ("C02501".equals(map.get("prcs_depth"))) {
				reqStatusTitle = considerationService.getReqStatus((String) map.get("prev_cnsdreq_id"), (String) map.get("plndbn_req_yn"), last_locale);

				// 회신인 경우
				if ("C04207".equals(map.get("prgrs_status"))) {
					// 검토회신 정보
					reqTitle1 = (String) messageSource.getMessage("clm.page.field.consideration.makePrintHeader02", null, locale1);
				}
				// 의뢰인 경우
				else {
					// 의뢰 정보
					reqTitle1 = reqStatusTitle + (String) messageSource.getMessage("clm.page.field.consideration.makePrintHeader03", null, locale1);
				}

			}
			// 계약체결인 경우
			else if ("C02502".equals(map.get("prcs_depth"))) {
				// 검토회신 정보
				reqTitle1 = (String) messageSource.getMessage("clm.page.field.consideration.makePrintHeader02", null, locale1);
			}

			sb.append("<div id=\"m_wrap\">\n").append("    <div class=\"m_header menu2\">\n")
					.append("    <h2><span style=\"float:right;margin-right:20px\"><img src=\"../../../images/clm/" + last_locale + "/icon/pncacc.gif\"></SPAN></h2>\n").append("    </div>\n").append("    <div id=\"m_container\">\n")
					.append("        <div class=\"contents\">\n");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 인쇄용 의뢰정보 html
	 * 
	 * @param sb
	 * @param map
	 * @param form
	 * @param reqAuthInfo
	 * @throws Exception
	 */
	public void makePrintReqHtml(StringBuffer sb, Map map, ConsiderationVO vo, String reqAuthInfo, String last_locale) throws Exception {

		if ("C02503".equals(map.get("prcs_depth")) || "C02504".equals(map.get("prcs_depth")) || "C02505".equals(map.get("prcs_depth"))) {
			return;
		}

		try {
			Locale locale1 = new Locale(last_locale);
			String reqStatusTitle = null;
			String reqTitle1 = null;
			String reqTitle2 = null;
			String reqCont = null;
			String apbtOpnn = null;

			vo.setCnslt_no((String) map.get("cnslt_no"));

			List reqAttachList = considerationService.getReqAttachList(vo); // 의뢰
																			// 첨부파일
			List replyAttachList = considerationService.getReplyAttachList(vo); // 회신
																				// 첨부파일
			boolean isReplyAttach = false; // 회신첨부파일을 보여줄지 여부

			// 최종본 회신이며 회신 계약서가 있는 경우에 회신 첨부파일을 보여준다.
			for (int i = 0; i < replyAttachList.size(); i++) {
				Map attach = (Map) replyAttachList.get(i);
				if ("1".equals(attach.get("filetype"))) {
					isReplyAttach = true;
					break;
				}
			}

			// 계약검토인 경우
			if ("C02501".equals(map.get("prcs_depth"))) {
				reqStatusTitle = considerationService.getReqStatus((String) map.get("prev_cnsdreq_id"), (String) map.get("plndbn_req_yn"), vo);

				// 회신인 경우
				if ("C04207".equals(map.get("prgrs_status"))) {
					reqTitle1 = (String) messageSource.getMessage("clm.page.field.consideration.makePrintReqHtml01", null, locale1);// 검토회신
																																	// 정보
					reqCont = (String) map.get("cnsd_opnn");
					apbtOpnn = (String) map.get("apbt_opnn");

					// 최종본인 경우
					if ("Y".equals((String) map.get("plndbn_req_yn"))) {
						reqTitle2 = (String) messageSource.getMessage("clm.page.field.consideration.makePrintReqHtml02", null, locale1); // 최종본
																																			// 검토의견
					} else {
						reqTitle2 = (String) messageSource.getMessage("clm.page.field.consideration.makePrintReqHtml03", null, locale1);// 검토의견
					}
				}
				// 의뢰인 경우
				else {
					reqTitle1 = reqStatusTitle + (String) messageSource.getMessage("clm.page.field.consideration.makePrintReqHtml04", null, locale1);// 의뢰
																																						// 정보
					reqCont = StringUtil.convertEnterToBR((String) map.get("cnsd_demnd_cont"));

					// 최종본인 경우
					if ("Y".equals(map.get("plndbn_req_yn"))) {
						reqTitle2 = (String) messageSource.getMessage("clm.page.field.consideration.makePrintReqHtml05", null, locale1);// 최종본
																																		// 검토의뢰내용
					} else {
						reqTitle2 = reqStatusTitle + " " + (String) messageSource.getMessage("clm.page.field.consideration.makePrintReqHtml06", null, locale1);// 요청내용
					}
				}

			}
			// 계약체결인 경우
			else if ("C02502".equals(map.get("prcs_depth"))) {
				reqTitle1 = (String) messageSource.getMessage("clm.page.field.consideration.makePrintReqHtml01", null, locale1);// 검토회신
																																// 정보
				reqTitle2 = (String) messageSource.getMessage("clm.page.field.consideration.makePrintReqHtml02", null, locale1);// 최종본
																																// 검토의견
				reqCont = (String) map.get("cnsd_opnn");
				apbtOpnn = (String) map.get("apbt_opnn");
			}

			sb.append("        <div class=\"title_basic\">\n").append("          <h4>" + reqTitle1 + "</h4>\n").append("        </div>\n").append("        <table cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n")
					.append("            <colgroup>\n").append("                <col width=\"13%\" />\n").append("                <col width=\"12%\" />\n").append("                <col width=\"10%\" />\n")
					.append("                <col width=\"15%\" />\n").append("                <col width=\"13%\" />\n").append("                <col width=\"12%\" />\n").append("                <col width=\"13%\" />\n")
					.append("                <col width=\"14%\" />\n").append("            </colgroup>              \n").append("	<tr>\n")
					// 검토의뢰제목
					.append("		<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintReqHtml07", null, locale1) + "</th>\n").append("    	<td colspan=\"7\">")
					.append(StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String) map.get("req_title")))).append("		</td>\n").append("	</tr>\n").append("  <tr class=\"lineAdd\">\n")
					// 의뢰자
					.append(" 	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintReqHtml08", null, locale1) + "</th>\n")
					.append("    	<td colspan=\"3\">" + map.get("reqman_nm") + "/" + map.get("reqman_jikgup_nm") + "/" + map.get("req_dept_nm") + "</td>\n")
					// 의뢰일
					.append("     <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintReqHtml09", null, locale1) + "</th>\n").append("		<td>").append(map.get("req_dt")).append("		</td>\n")
					// 회신요청일
					.append("		<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintReqHtml10", null, locale1) + "</th>\n").append("     <td>").append(map.get("re_demndday")).append("		</td>\n")
					.append("	</tr>\n").append("	<tr class=\"lineAdd\">\n")
					// 관련자
					.append("  	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintReqHtml11", null, locale1) + "</th>\n").append("     <td colspan=\"7\">" + reqAuthInfo + "</td>\n").append(" </tr>\n")
					.append("	<tr class=\"lineAdd\">\n").append("  	<th>" + reqTitle2 + "</th>\n").append("      <td colspan=\"7\">").append(StringUtil.nvl(reqCont, "")).append("		</td>\n").append("	</tr>\n");
			// 그룹장 의견이 있는 경우
			if (!StringUtil.nvl(apbtOpnn, "").equals("")) {
				sb.append("	<tr class=\"lineAdd\">\n")
						// 그룹장 의견
						.append("  	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintReqHtml12", null, locale1) + "</th>\n")
						.append("     <td colspan=\"7\">" + StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars(apbtOpnn)) + "</td>\n").append(" </tr>\n");
			}

			// 검토본 변경여부가 "Y" 이거나 최종본일 경우 검토본변경여부 와 사유를 보여준다.
			if ("C02501".equals(map.get("prcs_depth"))) {
				if ("Y".equals(map.get("plndbn_req_yn")) || "Y".equals(map.get("lastbn_chge_yn"))) {
					if (!StringUtil.isNull((String) map.get("lastbn_chge_yn_nm"))) {
						sb.append("	<tr class=\"lineAdd\">\n")
								// 검토본변경여부
								.append("  	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintReqHtml13", null, locale1) + "</th>\n")
								.append("     <td colspan=\"7\">" + map.get("lastbn_chge_yn_nm") + "</td>\n").append(" </tr>\n");
					}
					if (!StringUtil.isNull((String) map.get("plndbn_chge_cont"))) {
						sb.append("	<tr class=\"lineAdd\">\n")
								// 변경내역 및 사유
								.append("  	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintReqHtml14", null, locale1) + "</th>\n")
								.append("     <td colspan=\"7\">" + StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String) map.get("plndbn_chge_cont"))) + "</td>\n").append(" </tr>\n");
					}
				}
			}
			if (!StringUtil.isNull((String) map.get("hold_cause"))) {
				sb.append("	<tr class=\"lineAdd\">\n")
						// 보류 사유
						.append("  	<th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintReqHtml15", null, locale1) + "</th>\n")
						.append("     <td colspan=\"7\">" + StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String) map.get("hold_cause"))) + "</td>\n").append(" </tr>\n");
			}
			// 첨부파일
			// 최종본 회신이며 회신 계약서가 있는 경우에 회신 첨부파일을 보여준다.
			int fileCnt1 = 0;
			int fileCnt2 = 0;
			int fileCnt3 = 0;
			int fileCntTatal = 0;
			int rowspan = 0;
			if (!isReplyAttach) {
				// 최종본
				String fileTtitle = "Y".equals((String) map.get("plndbn_req_yn")) ? (String) messageSource.getMessage("clm.page.field.consideration.makePrintReqHtml16", null, locale1) : "";
				;

				for (int i = 0; i < reqAttachList.size(); i++) {
					Map attach = (Map) reqAttachList.get(i);
					if ("1".equals(attach.get("filetype"))) {
						fileCnt1++;
						fileCntTatal++;
					} else if ("2".equals(attach.get("filetype"))) {
						fileCnt2++;
						fileCntTatal++;
					} else if ("3".equals(attach.get("filetype"))) { // 추가
																		// 2013_1110
						fileCnt3++;
						fileCntTatal++;
					}

					if ("5".equals(attach.get("filetype"))) { // 추가 2013_1110
						fileCnt3++;
						fileCntTatal++;
					}
				}

				rowspan = fileCnt1 > 0 ? rowspan + 1 : rowspan;
				rowspan = fileCnt2 > 0 ? rowspan + 1 : rowspan;
				rowspan = fileCnt3 > 0 ? rowspan + 1 : rowspan;

				if (fileCnt1 > 0) {
					sb.append("	<tr class=\"lineAdd\">\n")
							// 첨부파일
							.append("  	<th rowspan='" + rowspan + "'>" + fileTtitle + (String) messageSource.getMessage("clm.page.field.consideration.makePrintReqHtml17", null, locale1) + "</th>\n")
							// 계약서
							.append("  	<td style='border-left:1px solid #CBDCE4'>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintReqHtml18", null, locale1) + "</td>\n").append("     <td colspan=\"6\">");
					for (int i = 0; i < reqAttachList.size(); i++) {
						Map attach = (Map) reqAttachList.get(i);
						if ("1".equals(attach.get("filetype"))) {
							sb.append(attach.get("org_file_nm") + "<br/>");
						}
					}
					sb.append("    </td>\n").append(" </tr>\n");
				}

				if (fileCnt2 > 0) {
					sb.append("	<tr class=\"lineAdd\">\n");
					if (fileCnt1 == 0) {
						// 첨부파일
						sb.append("  	<th rowspan='" + rowspan + "'>" + fileTtitle + (String) messageSource.getMessage("clm.page.field.consideration.makePrintReqHtml17", null, locale1) + "</th>\n");
					}
					// 첨부/별첨
					sb.append("  	<td style='border-left:1px solid #CBDCE4'>" + (String) messageSource.getMessage("clm.page.msg.manage.attachment_br", null, locale1) + "</td>\n").append("     <td colspan=\"6\">");
					for (int i = 0; i < reqAttachList.size(); i++) {
						Map attach = (Map) reqAttachList.get(i);
						if ("2".equals(attach.get("filetype"))) {
							sb.append(attach.get("org_file_nm") + "<br/>");
						}
					}
					sb.append("    </td>\n").append(" </tr>\n");
				}

				if (fileCnt3 > 0) {
					sb.append("	<tr class=\"lineAdd\">\n");
					if (fileCnt1 + fileCnt2 == 0) {
						// 첨부파일
						sb.append("  	<th rowspan='" + rowspan + "'>" + fileTtitle + (String) messageSource.getMessage("clm.page.field.consideration.makePrintReqHtml17", null, locale1) + "</th>\n");
					}
					// 기타
					sb.append("  	<td style='border-left:1px solid #CBDCE4'>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintReqHtml20", null, locale1) + "</td>\n").append("     <td colspan=\"6\">");
					for (int i = 0; i < reqAttachList.size(); i++) {
						Map attach = (Map) reqAttachList.get(i);
						if ("3".equals(attach.get("filetype"))) {
							sb.append(attach.get("org_file_nm") + "<br/>");
						} else if ("5".equals(attach.get("filetype"))) {
							sb.append(attach.get("org_file_nm") + "<br/>");
						}
					}
					sb.append("    </td>\n").append(" </tr>\n");
				}
			} else {
				for (int i = 0; i < replyAttachList.size(); i++) {
					Map attach = (Map) replyAttachList.get(i);
					if ("1".equals(attach.get("filetype"))) {
						fileCnt1++;
						fileCntTatal++;
					} else if ("2".equals(attach.get("filetype"))) {
						fileCnt2++;
						fileCntTatal++;
					} else if ("3".equals(attach.get("filetype"))) {
						fileCnt3++;
						fileCntTatal++;
					}
				}

				rowspan = fileCnt1 > 0 ? rowspan + 1 : rowspan;
				rowspan = fileCnt2 > 0 ? rowspan + 1 : rowspan;
				rowspan = fileCnt3 > 0 ? rowspan + 1 : rowspan;

				if (fileCnt1 > 0) {
					sb.append("	<tr class=\"lineAdd\">\n")
							// 회신 첨부파일
							.append("  	<th rowspan='" + rowspan + "'>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintReqHtml21", null, locale1) + "</th>\n")
							// 계약서
							.append("  	<td style='border-left:1px solid #CBDCE4'>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintReqHtml18", null, locale1) + "</td>\n").append("     <td colspan=\"6\">");
					for (int i = 0; i < replyAttachList.size(); i++) {
						Map attach = (Map) replyAttachList.get(i);
						if ("1".equals(attach.get("filetype"))) {
							sb.append(attach.get("org_file_nm") + "<br/>");
						}
					}
					sb.append("    </td>\n").append(" </tr>\n");
				}

				if (fileCnt2 > 0) {
					sb.append("	<tr class=\"lineAdd\">\n");
					if (fileCnt1 == 0) {
						// 회신 첨부파일
						sb.append("  	<th rowspan='" + rowspan + "'>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintReqHtml21", null, locale1) + "</th>\n");
					}
					// 첨부/별첨
					sb.append("  	<td style='border-left:1px solid #CBDCE4'>" + (String) messageSource.getMessage("clm.page.msg.manage.attachment_br", null, locale1) + "</td>\n").append("     <td colspan=\"6\">");
					for (int i = 0; i < replyAttachList.size(); i++) {
						Map attach = (Map) replyAttachList.get(i);
						if ("2".equals(attach.get("filetype"))) {
							sb.append(attach.get("org_file_nm") + "<br/>");
						}
					}
					sb.append("    </td>\n").append(" </tr>\n");
				}

				if (fileCnt3 > 0) {
					sb.append("	<tr class=\"lineAdd\">\n");
					if (fileCnt1 + fileCnt2 == 0) {
						// 회신 첨부파일
						sb.append("  	<th rowspan='" + rowspan + "'>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintReqHtml21", null, locale1) + "</th>\n");
					}
					// 기타
					sb.append("  	<td style='border-left:1px solid #CBDCE4'>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintReqHtml20", null, locale1) + "</td>\n").append("     <td colspan=\"6\">");
					for (int i = 0; i < replyAttachList.size(); i++) {
						Map attach = (Map) replyAttachList.get(i);
						if ("3".equals(attach.get("filetype"))) {
							sb.append(attach.get("org_file_nm") + "<br/>");
						}
					}
					sb.append("    </td>\n").append(" </tr>\n");
				}
			}

			sb.append("</table>\n");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 인쇄용 계약정보 html
	 * 
	 * @param sb
	 * @param map
	 * @param form
	 * @param reqAuthInfo
	 * @throws Exception
	 */
	public void makePrintCntrtHtml(StringBuffer sb, Map map, ConsiderationVO vo, List etcAttachList, String last_locale) throws Exception {
		vo.setCnsd_status((String) map.get("cnsd_status"));
		vo.setPlndbn_req_yn((String) map.get("plndbn_req_yn"));
		List spclList = considerationService.getSpclList(vo);
		Locale locale1 = new Locale(last_locale);

		// 계약정보
		sb.append("<div class=\"title_basic\"><h4>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCntrtHtml01", null, locale1) + "</h4></div>\n");
		// 기본정보
		sb.append("<div class=\"title_basic3\">" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCntrtHtml02", null, locale1) + "</div>\n");
		sb.append("<table class=\"table-style01\">\n");
		sb.append("    <colgroup>\n");
		sb.append("        <col width=\"12%\" />\n");
		sb.append("        <col width=\"10%\" />\n");
		sb.append("        <col width=\"14%\" />\n");
		sb.append("        <col width=\"12%\" />\n");
		sb.append("        <col width=\"21%\" />\n");
		sb.append("        <col width=\"12%\" />\n");
		sb.append("        <col width=\"21%\" />\n");
		sb.append("    </colgroup>\n");
		sb.append("    <tr>\n");
		// 의뢰명
		sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCntrtHtml03", null, locale1) + "</th>\n");
		sb.append("        <td colspan=\"6\">" + StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String) map.get("req_title"))) + "</td>\n");
		sb.append("    </tr>\n");
		sb.append("    <tr>\n");
		// 계약명
		sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCntrtHtml04", null, locale1) + "</th>\n");
		sb.append("        <td colspan=\"4\">" + StringUtil.bvl((String) map.get("cntrt_nm"), "") + "</td>\n");
		// 계약ID
		sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCntrtHtml05", null, locale1) + "</th>\n");
		sb.append("        <td>" + StringUtil.bvl((String) map.get("cntrt_no"), "") + "</td>\n");
		sb.append("    </tr>\n");
		sb.append("    <tr>\n");
		// 의뢰자
		sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCntrtHtml06", null, locale1) + "</th>\n");
		sb.append("        <td colspan=\"2\">\n");
		sb.append("             " + StringUtil.bvl((String) map.get("reqman_nm"), "") + " / " + StringUtil.bvl((String) map.get("reqman_jikgup_nm"), "") + " / " + StringUtil.bvl((String) map.get("req_dept_nm"), "") + "\n");
		sb.append("        </td>\n");
		// 담당자
		sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCntrtHtml07", null, locale1) + "</th>\n");
		sb.append("        <td>\n");
		sb.append("             " + StringUtil.bvl((String) map.get("cntrt_respman_nm"), "") + " / " + StringUtil.bvl((String) map.get("cntrt_respman_jikgup_nm"), "") + " / " + StringUtil.bvl((String) map.get("cntrt_resp_dept_nm"), "")
				+ "\n");
		sb.append("        </td>\n");
		// 검토자
		sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCntrtHtml08", null, locale1) + "</th>\n");
		sb.append("        <td>" + StringUtil.bvl(map.get("cnsdmans"), "") + "</td>\n");
		sb.append("    </tr>\n");
		sb.append("    <tr>\n");
		// 계약상대방
		sb.append("        <th>" + (String) messageSource.getMessage("clm.page.msg.common.otherParty", null, locale1) + "</th>\n");
		sb.append("        <td colspan=\"2\">" + (String) map.get("cntrt_oppnt_nm") + "</td>\n");
		// 대표자명
		sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.customer.registerNo", null, locale1) + "</th>\n");
		sb.append("        <td>" + StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String) map.get("cntrt_oppnt_rprsntman"))) + "</td>\n");
		// 상대방유형
		sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.customer.contractRequired", null, locale1) + "</th>\n");
		sb.append("        <td>" + StringUtil.bvl((String) map.get("cntrt_oppnt_type_nm"), "") + "</td>\n");
		sb.append("    </tr>\n");

		int colspan = "Y".equals(map.get("plndbn_req_yn")) && "C02608".equals(map.get("depth_status")) ? 4 : 6;
		sb.append("    <tr>\n");
		// 계약유형
		sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCntrtHtml12", null, locale1) + "</th>\n");
		sb.append("        <td colspan=\"" + colspan + "\">\n");
		sb.append("            " + map.get("biz_clsfcn_nm") + "/ \n");
		sb.append("            " + map.get("depth_clsfcn_nm") + " / \n");
		sb.append("            " + map.get("cnclsnpurps_bigclsfcn_nm") + " / \n");
		sb.append("            " + map.get("cnclsnpurps_midclsfcn_nm") + " \n");
		sb.append("        </td>\n");
		if ("Y".equals(map.get("plndbn_req_yn")) && "C02608".equals(map.get("depth_status"))) { // 최종본의뢰여부%>
			// 자동연장여부
			sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCntrtHtml13", null, locale1) + "</th>\n");
			sb.append("        <td>" + map.get("auto_rnew_yn") + "</td>\n");
		}
		sb.append("    </tr>\n");

		sb.append("    <tr>\n");
		// 계약대상
		sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCntrtHtml14", null, locale1) + "</th>\n");
		sb.append("        <td colspan=\"2\">" + (String) map.get("cntrt_trgt_nm") + "</td>\n");
		// 계약대상 상세
		sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCntrtHtml15", null, locale1) + "</th>\n");
		sb.append("        <td colspan=\"4\">" + StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String) map.get("cntrt_trgt_det"))) + "</td>\n");
		sb.append("    </tr>\n");

		sb.append("    <tr>\n");
		// 계약기간
		sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCntrtHtml17", null, locale1) + "</th>\n");
		sb.append("        <td colspan=\"4\">" + (String) map.get("cntrtperiod_startday") + " ~ " + (String) map.get("cntrtperiod_endday") + "</td>\n");
		// 지불/수금 구분
		sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCntrtHtml18", null, locale1) + "</th>\n");
		sb.append("        <td>" + (String) map.get("payment_gbn_nm") + "</td>\n");
		sb.append("    </tr>\n");

		colspan = !"".equals(StringUtil.nvl(map.get("cntrt_untprc_expl"), "")) ? 2 : 4;
		sb.append("		<tr>\n");
		// 계약금액
		sb.append("		    <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCntrtHtml19", null, locale1) + "</th>\n");
		sb.append("		    <td colspan=\"" + colspan + "\">" + map.get("cntrt_amt") + "</td>\n");
		if (!"".equals(StringUtil.nvl((String) map.get("cntrt_untprc_expl"), ""))) {
			// 계약단가
			sb.append("		    <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCntrtHtml20", null, locale1) + "</th>\n");
			// 단가로체결
			sb.append("		    <td>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCntrtHtml21", null, locale1) + "</td>");
		}
		// 통화단위
		sb.append("		    <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCntrtHtml22", null, locale1) + "</th>\n");
		sb.append("		    <td>" + StringUtil.bvl((String) map.get("crrncy_unit"), "") + "</td>\n");
		sb.append("		</tr>\n");

		int rowspan = 0;
		if (!"".equals(StringUtil.nvl((String) map.get("cntrt_untprc_expl"), ""))) {
			for (int i = 0; i < etcAttachList.size(); i++) {
				Map attach = (Map) etcAttachList.get(i);
				if ("2".equals(attach.get("filetype"))) {
					rowspan = 2;
					break;
				}
			}

			sb.append("    <tr>\n");
			// 단가내역 요약
			sb.append("        <th rowspan=\"" + rowspan + "\">" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCntrtHtml23", null, locale1) + "</th>\n");
			sb.append("        <td colspan=\"6\">" + StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String) map.get("cntrt_untprc_expl"))) + "</td>\n");
			sb.append("    </tr>\n");
			if (rowspan == 2) {
				sb.append("    <tr>\n");
				sb.append("        <td colspan=\"6\"  class=\"tal_lineL\">\n");
				for (int i = 0; i < etcAttachList.size(); i++) {
					Map attach = (Map) etcAttachList.get(i);
					if ("2".equals(attach.get("filetype"))) {
						sb.append(attach.get("org_file_nm") + "<br/>");
					}
				}
				sb.append("        </td>\n");
				sb.append("    </tr>\n");
			}
		}

		sb.append("    <tr>\n");
		// 추진목적 및 배경
		sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCntrtHtml24", null, locale1) + "</th>\n");
		sb.append("        <td colspan=\"6\" >" + (String) map.get("pshdbkgrnd_purps") + "</td>\n");
		sb.append("    </tr>\n");
		sb.append("    <tr>\n");
		// 기타주요사항
		sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCntrtHtml25", null, locale1) + "</th>\n");
		sb.append("        <td colspan=\"6\" >\n");
		sb.append("            " + StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String) map.get("etc_main_cont"))) + (StringUtil.nvl((String) map.get("if_sys_cd"), "").equals("") ? "" : " [" + map.get("if_sys_cd") + "]"));
		sb.append("        </td>\n");
		sb.append("    </tr>\n");

		// 특화정보
		for (int i = 0; i < spclList.size(); i++) {
			Map spcl = (Map) spclList.get(i);
			sb.append("    <tr>\n");
			sb.append("        <th><span>" + (String) spcl.get("attr_nm") + "</span></th>\n");
			sb.append("        <td valign=\"top\" colspan=\"6\">" + StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String) spcl.get("attr_value"))) + "</td>\n");
			sb.append("    </tr>\n");
		}

		// 법무 회신 최종본 회신 완료시 표시 해야할 항목
		if ("C04305".equals(map.get("cnsd_status")) && "Y".equals(map.get("plndbn_req_yn"))) {
			if (!StringUtil.nvl(map.get("oblgt_lmt"), "").equals("")) {
				sb.append("    <tr>\n");
				// 배상책임한도
				sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCntrtHtml26", null, locale1) + "</th>\n");
				sb.append("        <td colspan=\"6\">" + StringUtil.convertEnterToBR((String) map.get("oblgt_lmt")) + "</td>\n");
				sb.append("    </tr>\n");
				// sb.append(" </c:if>\n");
			}
			if (!StringUtil.nvl(map.get("spcl_cond"), "").equals("")) {
				// sb.append(" <c:if test=\"${!empty lomRq2.spcl_cond}\">\n");
				sb.append("    <tr>\n");
				// 기타 특약사항
				sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCntrtHtml27", null, locale1) + "</th>\n");
				sb.append("        <td colspan=\"6\">" + StringUtil.convertEnterToBR((String) map.get("spcl_cond")) + "</td>\n");
				sb.append("    </tr>\n");
				// sb.append(" </c:if>\n");
			}
			sb.append("    <tr>\n");
			// 준거법
			sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCntrtHtml28", null, locale1) + "</th>\n");
			sb.append("        <td colspan=\"2\">" + map.get("loac_nm") + "</td>\n");
			// 준거법 상세
			sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.contract.detail.loacdetail", null, locale1) + "</th>\n");
			sb.append("        <td colspan=\"3\">" + map.get("loac_etc") + "</td>\n");
			sb.append("    </tr>\n");
			sb.append("    <tr>\n");
			// 분쟁해결방법
			sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCntrtHtml30", null, locale1) + "</th>\n");
			sb.append("        <td colspan=\"2\">" + map.get("dspt_resolt_mthd_nm") + "</td>\n");
			// 분쟁해결방법상세
			sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCntrtHtml31", null, locale1) + "</th>\n");
			sb.append("        <td colspan=\"3\">" + StringUtil.convertEnterToBR((String) map.get("dspt_resolt_mthd_det")) + "</td>\n");
			sb.append("    </tr>\n");
		}

		if ("C02503".equals(map.get("prcs_depth"))) {
			int fileCnt1 = 0;
			int fileCnt2 = 0;
			int fileCnt3 = 0;
			int fileCntTatal = 0;

			vo.setCnslt_no((String) map.get("cnslt_no"));

			List reqAttachList = considerationService.getReqAttachList(vo); // 의뢰
																			// 첨부파일
			for (int i = 0; i < reqAttachList.size(); i++) {
				Map attach = (Map) reqAttachList.get(i);
				if ("1".equals(attach.get("filetype"))) {
					fileCnt1++;
					fileCntTatal++;
				} else if ("2".equals(attach.get("filetype"))) {
					fileCnt2++;
					fileCntTatal++;
				} else if ("3".equals(attach.get("filetype"))) {
					fileCnt3++;
					fileCntTatal++;
				} else if ("5".equals(attach.get("filetype"))) {
					fileCnt3++;
					fileCntTatal++;
				}
			}

			rowspan = fileCnt1 > 0 ? rowspan + 1 : rowspan;
			rowspan = fileCnt2 > 0 ? rowspan + 1 : rowspan;
			rowspan = fileCnt3 > 0 ? rowspan + 1 : rowspan;

			if (fileCnt1 > 0) {
				sb.append("	<tr class=\"lineAdd\">\n")
						// 첨부파일
						.append("  	<th rowspan='" + rowspan + "'>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCntrtHtml32", null, locale1) + "</th>\n")
						// 계약서
						.append("  	<td style='border-left:1px solid #CBDCE4'>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCntrtHtml33", null, locale1) + "</td>\n").append("     <td colspan=\"6\">");
				for (int i = 0; i < reqAttachList.size(); i++) {
					Map attach = (Map) reqAttachList.get(i);
					if ("1".equals(attach.get("filetype"))) {
						sb.append(attach.get("org_file_nm") + "<br/>");
					}
				}
				sb.append("    </td>\n").append(" </tr>\n");
			}

			if (fileCnt2 > 0) {
				sb.append("	<tr class=\"lineAdd\">\n");
				if (fileCnt1 == 0) {
					// 첨부파일
					sb.append("  	<th rowspan='" + rowspan + "'>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCntrtHtml32", null, locale1) + "</th>\n");
				}
				// 첨부/별첨
				sb.append("  	<td style='border-left:1px solid #CBDCE4'>" + (String) messageSource.getMessage("clm.page.msg.manage.attachment_br", null, locale1) + "</td>\n").append("     <td colspan=\"6\">");
				for (int i = 0; i < reqAttachList.size(); i++) {
					Map attach = (Map) reqAttachList.get(i);
					if ("2".equals(attach.get("filetype"))) {
						sb.append(attach.get("org_file_nm") + "<br/>");
					}
				}
				sb.append("    </td>\n").append(" </tr>\n");
			}

			if (fileCnt3 > 0) {
				sb.append("	<tr class=\"lineAdd\">\n");
				if (fileCnt1 + fileCnt2 == 0) {
					// 첨부파일
					sb.append("  	<th rowspan='" + rowspan + "'>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCntrtHtml32", null, locale1) + "</th>\n");
				}
				// 기타
				sb.append("  	<td style='border-left:1px solid #CBDCE4'>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCntrtHtml35", null, locale1) + "</td>\n").append("     <td colspan=\"6\">");
				for (int i = 0; i < reqAttachList.size(); i++) {
					Map attach = (Map) reqAttachList.get(i);
					// 첨부 파일 로직 수정 2013.11.11
					if ("3".equals(attach.get("filetype"))) {
						sb.append(attach.get("org_file_nm") + "<br/>");
					} else if ("5".equals(attach.get("filetype"))) {
						sb.append(attach.get("org_file_nm") + "<br/>");
					}
				}
				sb.append("    </td>\n").append(" </tr>\n");
			}
		}
		sb.append("</table>\n");
	}

	/**
	 * 인쇄용 사전승인정보 html
	 * 
	 * @param sb
	 * @param map
	 * @param form
	 * @param reqAuthInfo
	 * @throws Exception
	 */
	public void makePrintPreApproveHtml(StringBuffer sb, Map map, ConsiderationVO vo, List etcAttachList, String last_locale) throws Exception {
		Locale locale1 = new Locale(last_locale);
		// 사전승인정보
		sb.append("<div class=\"title_basic3\">" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintPreApproveHtml01", null, locale1) + "</div>\n");
		sb.append("<table class=\"table-style01\">\n");
		sb.append("    <colgroup>\n");
		sb.append("        <col width=\"12%\" />\n");
		sb.append("        <col width=\"38%\" />\n");
		sb.append("        <col width=\"12%\" />\n");
		sb.append("        <col width=\"38%\" />\n");
		sb.append("    </colgroup>\n");
		sb.append("    <tr>\n");
		// 승인일자
		sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintPreApproveHtml02", null, locale1) + "</th>\n");
		sb.append("        <td>" + (String) map.get("bfhdcstn_apbtday") + "</td>\n");
		// 승인방식
		sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintPreApproveHtml03", null, locale1) + "</th>\n");
		sb.append("        <td>" + (String) map.get("bfhdcstn_apbt_mthd_nm") + "</td>\n");
		sb.append("    </tr>\n");
		sb.append("    <tr>\n");
		// 발의자
		sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintPreApproveHtml04", null, locale1) + "</th>\n");
		sb.append("        <td>\n");
		if (!StringUtil.nvl(map.get("bfhdcstn_mtnman_nm"), "").equals("")) {
			sb.append("            " + (String) map.get("bfhdcstn_mtnman_nm") + " / " + (String) map.get("bfhdcstn_mtnman_jikgup_nm") + " / " + (String) map.get("bfhdcstn_mtn_dept_nm") + "\n");
		}
		sb.append("        </td>\n");
		// 승인자
		sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintPreApproveHtml05", null, locale1) + "</th>\n");
		sb.append("        <td>\n");
		if (!StringUtil.nvl(map.get("bfhdcstn_mtnman_nm"), "").equals("")) {
			sb.append("            " + (String) map.get("bfhdcstn_apbtman_nm") + " / " + (String) map.get("bfhdcstn_apbtman_jikgup_nm") + " / " + (String) map.get("bfhdcstn_apbt_dept_nm") + "\n");
		}
		sb.append("        </td>\n");
		sb.append("    </tr>\n");

		int fileCnt = 0;
		;
		for (int i = 0; i < etcAttachList.size(); i++) {
			Map attach = (Map) etcAttachList.get(i);
			if ("1".equals(attach.get("filetype"))) {
				fileCnt++;
			}
		}

		if (fileCnt > 0) {
			sb.append("    <tr>\n");
			// 첨부자료
			sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintPreApproveHtml06", null, locale1) + "</th>\n");
			sb.append("        <td colspan='3'>\n");
			for (int i = 0; i < etcAttachList.size(); i++) {
				Map attach = (Map) etcAttachList.get(i);
				if ("1".equals(attach.get("filetype"))) {
					sb.append(attach.get("org_file_nm") + "<br/>");
				}
			}
			sb.append("        </td>\n");
			sb.append("    </tr>\n");
		}

		sb.append("</table>\n");
	}

	/**
	 * 인쇄용 연관계약정보 html
	 * 
	 * @param sb
	 * @param map
	 * @param form
	 * @param reqAuthInfo
	 * @throws Exception
	 */
	public void makePrintRelationHtml(StringBuffer sb, Map map, String last_locale) throws Exception {
		Locale locale1 = new Locale(last_locale);

		// 계약 id 전달
		ConsultationVO vo = new ConsultationVO();
		vo.setCntrt_id((String) map.get("cntrt_id"));
		vo.setParent_cntrt_id("");
		vo.setSubmit_status("");
		vo.setSession_user_locale(last_locale);// locale 값 추가 박정훈 !@#2013.04
		HashMap contRcMap = considerationService.listRelationContract(vo);
		// 연관계약정보
		sb.append("<div class=\"title_basic3\">" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintRelationHtml01", null, locale1) + "</div>\n");
		sb.append("<table class=\"table-style01\">\n");
		sb.append("    <colgroup>\n");
		sb.append("        <col width=\"12%\" />\n");
		sb.append("        <col width=\"50%\" />\n");
		sb.append("        <col width=\"10%\" />\n");
		sb.append("        <col/>\n");
		sb.append("    </colgroup>\n");
		sb.append("    <tr>\n");
		// 관계
		sb.append("        <th class=\"tC\">" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintRelationHtml02", null, locale1) + "</th>\n");
		// 연관계약
		sb.append("        <th class=\"tC\">" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintRelationHtml03", null, locale1) + "</th>\n");
		// 세부정의
		sb.append("        <th class=\"tC\">" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintRelationHtml04", null, locale1) + "</th>\n");
		// 관계상세
		sb.append("        <th class=\"tC\">" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintRelationHtml05", null, locale1) + "</th>\n");
		sb.append("    </tr>\n");
		sb.append("    " + contRcMap.get("contRc"));
		sb.append("</table>\n");
	}

	/**
	 * 인쇄용 검토이력 html
	 * 
	 * @param sb
	 * @param map
	 * @param form
	 * @param reqAuthInfo
	 * @throws Exception
	 */
	public void makeRespHisHtml(StringBuffer sb, List review, String last_locale) throws Exception {
		Locale locale1 = new Locale(last_locale);

		// 검토이력
		sb.append("<div class=\"title_basic\"><h4>" + (String) messageSource.getMessage("clm.page.field.consideration.makeRespHisHtml01", null, locale1) + "</h4></div>\n");
		sb.append("<table class=\"table-style01\">\n");
		sb.append("    <colgroup>\n");
		sb.append("        <col width=\"66%\" />\n");
		sb.append("        <col width=\"10%\" />\n");
		sb.append("        <col width=\"24%\" />\n");
		sb.append("    </colgroup>\n");
		sb.append("    <tr>\n");
		// 의뢰내역
		sb.append("        <th class=\"tC\">" + (String) messageSource.getMessage("clm.page.field.consideration.makeRespHisHtml02", null, locale1) + "</th>\n");
		// 일자
		sb.append("        <th class=\"tC\">" + (String) messageSource.getMessage("clm.page.field.consideration.makeRespHisHtml03", null, locale1) + "</th>\n");
		// 의뢰인, 검토자
		sb.append("        <th class=\"tC\">" + (String) messageSource.getMessage("clm.page.field.consideration.makeRespHisHtml04", null, locale1) + "</th>\n");
		sb.append("    </tr>\n");

		for (int i = 0; i < review.size(); i++) {
			Map lom = (Map) review.get(i);
			sb.append("    <tr>\n");
			sb.append("        <td>" + lom.get("cr_flan_nm") + " " + StringUtil.convertHtmlTochars((String) lom.get("title")) + "</td>\n");
			sb.append("        <td class=\"tC\">" + lom.get("reg_dt") + "</td>\n");
			sb.append("        <td>" + (!StringUtil.nvl((String) lom.get("dept_nm"), "").equals("") ? lom.get("man_nm") + " / " + lom.get("dept_nm") : lom.get("man_nm")) + "</td>\n");
			sb.append("    </tr>\n");
		}

		sb.append("</table>\n");
	}

	/**
	 * 인쇄용 승인이력 html
	 * 
	 * @param sb
	 * @param map
	 * @param form
	 * @param reqAuthInfo
	 * @throws Exception
	 */
	public void makeAprroveHisHtml(StringBuffer sb, Map map, List approve, String last_locale) throws Exception {
		Locale locale1 = new Locale(last_locale);

		// 승인이력
		sb.append("<div class=\"title_basic\"><h4>" + (String) messageSource.getMessage("clm.page.field.consideration.makeAprroveHisHtml01", null, locale1) + "</h4></div>\n");
		sb.append("<table class=\"table-style01\">\n");
		sb.append("    <colgroup>\n");
		sb.append("        <col width=\"55%\" />\n");
		sb.append("        <col width=\"10%\" />\n");
		sb.append("        <col width=\"10%\" />\n");
		sb.append("        <col width=\"25%\" />\n");
		sb.append("    </colgroup>\n");
		sb.append("    <tr>\n");
		// 품의명
		sb.append("        <th class=\"tC\">" + (String) messageSource.getMessage("clm.page.field.consideration.makeAprroveHisHtml02", null, locale1) + "</th>\n");
		// 상태
		sb.append("        <th class=\"tC\">" + (String) messageSource.getMessage("clm.page.field.consideration.makeAprroveHisHtml03", null, locale1) + "</th>\n");
		// 일자
		sb.append("        <th class=\"tC\">" + (String) messageSource.getMessage("clm.page.field.consideration.makeAprroveHisHtml04", null, locale1) + "</th>\n");
		// 승인자
		sb.append("        <th class=\"tC\">" + (String) messageSource.getMessage("clm.page.field.consideration.makeAprroveHisHtml05", null, locale1) + "</th>\n");
		sb.append("    </tr>\n");

		sb.append("    <tr>\n");
		// 사전승인정보
		sb.append("        <td>" + (String) messageSource.getMessage("clm.page.field.consideration.makeAprroveHisHtml06", null, locale1) + "</td>\n");
		sb.append("        <td class=\"tC\">" + (String) map.get("bfhdcstn_apbt_mthd_nm") + "</td>\n");
		sb.append("        <td class=\"tC\">" + (String) map.get("bfhdcstn_apbtday") + "</td>\n");
		sb.append("        <td>" + (String) map.get("bfhdcstn_apbtman_nm") + " / " + (String) map.get("bfhdcstn_apbtman_jikgup_nm") + " / " + (String) map.get("bfhdcstn_apbt_dept_nm") + "</td>\n");
		sb.append("    </tr>\n");

		for (int i = 0; i < approve.size(); i++) {
			Map lom = (Map) approve.get(i);

			sb.append("    <tr>\n");
			sb.append("        <td>" + StringUtil.convertHtmlTochars((String) lom.get("title")) + "</td>\n");
			sb.append("        <td class=\"tC\">" + lom.get("appr_status") + "</td>\n");
			sb.append("        <td class=\"tC\">" + lom.get("appr_create_date") + "</td>\n");
			sb.append("        <td>" + lom.get("appr_mans") + "</td>\n");
			sb.append("    </tr>\n");

		}

		sb.append("</table>\n");
	}

	/**
	 * 인쇄용 체결정보 html
	 * 
	 * @param sb
	 * @param map
	 * @param form
	 * @param reqAuthInfo
	 * @throws Exception
	 */
	public void makePrintConclusionHtml(StringBuffer sb, Map map, List etcAttachList, String reqAuthInfo, String last_locale) throws Exception {

		if ("C02501".equals(map.get("prcs_depth"))) {
			return;
		}

		Locale locale1 = new Locale(last_locale);
		// 체결정보
		sb.append("<div class=\"title_basic\"><h4>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintConclusionHtml01", null, locale1) + "</h4></div>\n");
		// 기본정보
		sb.append("<div class=\"title_basic3\">" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintConclusionHtml02", null, locale1) + "</div>\n");
		sb.append("<table class=\"table-style01\">\n");
		sb.append("    <colgroup>\n");
		sb.append("        <col width=\"12%\" />\n");
		sb.append("        <col width=\"21%\" />\n");
		sb.append("        <col width=\"12%\" />\n");
		sb.append("        <col width=\"21%\" />\n");
		sb.append("        <col width=\"12%\" />\n");
		sb.append("        <col width=\"22%\" />\n");
		sb.append("    </colgroup>\n");
		sb.append("    <tr>\n");
		// 체결여부
		sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintConclusionHtml03", null, locale1) + "</th>\n");
		sb.append("        <td>" + (StringUtil.nvl((String) map.get("seal_mthd_nm"), "").equals("Y") ? "Yes" : "No") + "</td>\n");
		// 체결예정일
		sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintConclusionHtml04", null, locale1) + "</th>\n");
		sb.append("        <td class=\"tC\">" + (String) map.get("cnclsn_plndday") + "</td>\n");
		// 계약체결일
		sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintConclusionHtml05", null, locale1) + "</th>\n");
		sb.append("        <td class=\"tC\">" + (String) map.get("cntrt_cnclsnday") + "</td>\n");
		sb.append("    </tr>\n");

		// 날인정보
		if ("C02101".equals(map.get("seal_mthd")) || "C02103".equals(map.get("seal_mthd"))) {
			sb.append("    <tr>\n");
			// 날인방식
			sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintConclusionHtml06", null, locale1) + "</th>\n");
			sb.append("        <td>" + (String) map.get("seal_mthd_nm") + "</td>\n");
			// 날인담당자
			sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintConclusionHtml07", null, locale1) + "</th>\n");
			sb.append("        <td colspan='3'>" + (String) map.get("seal_ffmtman_nm") + " / " + (String) map.get("seal_ffmtman_jikgup_nm") + " / " + (String) map.get("seal_ffmt_dept_nm") + "</td>\n");
			sb.append("    </tr>\n");
		}
		// 서명정보
		else {
			sb.append("    <tr>\n");
			// 날인방식
			sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintConclusionHtml06", null, locale1) + "</th>\n");
			sb.append("        <td>" + (String) map.get("seal_mthd_nm") + "</td>\n");
			// 서명예정자
			sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintConclusionHtml08", null, locale1) + "</th>\n");
			sb.append("        <td colspan='3'>" + StringUtil.bvl((String) map.get("sign_plndman_nm"), "") + " / " + StringUtil.bvl((String) map.get("sign_plndman_jikgup_nm"), "") + " / "
					+ StringUtil.bvl((String) map.get("sign_plnd_dept_nm"), "") + "</td>\n");
			sb.append("    </tr>\n");
			sb.append("    <tr>\n");
			// 서명자(당사)
			sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintConclusionHtml09", null, locale1) + "</th>\n");
			sb.append("        <td colspan='3'>" + StringUtil.bvl((String) map.get("signman_nm"), "") + " / " + StringUtil.bvl((String) map.get("signman_jikgup_nm"), "") + " / " + StringUtil.bvl((String) map.get("sign_dept_nm"), "")
					+ "</td>\n");
			// 서명일(당사)
			sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintConclusionHtml10", null, locale1) + "</th>\n");
			sb.append("        <td class=\"tC\">" + (String) map.get("signday") + "</td>\n");
			sb.append("    </tr>\n");
			sb.append("    <tr>\n");
			// 서명자(상대)
			sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintConclusionHtml11", null, locale1) + "</th>\n");
			sb.append("        <td colspan='3'>" + StringUtil.bvl((String) map.get("oppnt_signman_nm"), "") + " / " + StringUtil.bvl((String) map.get("oppnt_signman_jikgup"), "") + " / "
					+ StringUtil.bvl((String) map.get("oppnt_signman_dept"), "") + "</td>\n");
			// 서명일(상대)
			sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintConclusionHtml12", null, locale1) + "</th>\n");
			sb.append("        <td class=\"tC\">" + (String) map.get("oppnt_signday") + "</td>\n");
			sb.append("    </tr>\n");

		}

		sb.append("    <tr>\n");
		// 계약담당자
		sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintConclusionHtml13", null, locale1) + "</th>\n");
		sb.append("        <td colspan='3'>" + (String) map.get("cntrt_respman_nm") + " / " + (String) map.get("cntrt_respman_jikgup_nm") + " / " + (String) map.get("cntrt_resp_dept_nm") + "</td>\n");
		sb.append("    </tr>\n");
		sb.append("</table>\n");

		boolean isFile = false;
		for (int i = 0; i < etcAttachList.size(); i++) {
			Map lom = (Map) etcAttachList.get(i);
			if ("3".equals(lom.get("filetype"))) {
				isFile = true;
				break;
			}
		}

		if (isFile) {
			// 사본정보
			sb.append("<div class=\"title_basic3\">" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintConclusionHtml15", null, locale1) + "</div>\n");
			sb.append("<table class=\"table-style01\">\n");
			sb.append("    <colgroup>\n");
			sb.append("        <col width=\"12%\" />\n");
			sb.append("        <col width=\"54%\" />\n");
			sb.append("        <col width=\"12%\" />\n");
			sb.append("        <col width=\"22%\" />\n");
			sb.append("    </colgroup>\n");
			sb.append("    <tr>\n");
			// 사본등록자
			sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintConclusionHtml16", null, locale1) + "</th>\n");
			sb.append("        <td>" + (String) map.get("cpy_regman_nm") + " / " + (String) map.get("cpy_regman_jikgup_nm") + " / " + (String) map.get("cpy_reg_dept_nm") + "</td>\n");
			// 사본등록일
			sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintConclusionHtml17", null, locale1) + "</th>\n");
			sb.append("        <td class=\"tC\">" + (String) map.get("cpy_regday") + "</td>\n");
			sb.append("    </tr>\n");
			sb.append("    <tr>\n");
			// 체결본사본
			sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintConclusionHtml18", null, locale1) + "</th>\n");
			sb.append("        <td colspan='3'>\n");
			for (int i = 0; i < etcAttachList.size(); i++) {
				Map attach = (Map) etcAttachList.get(i);
				if ("3".equals(attach.get("filetype"))) {
					sb.append(attach.get("org_file_nm") + "<br/>");
				}
			}
			sb.append("        </td>\n");
			sb.append("    </tr>\n");

			sb.append("</table>\n");
		}

		if ("C02504".equals(map.get("prcs_depth")) || "C02505".equals(map.get("prcs_depth"))) {
			// 원본정보
			sb.append("<div class=\"title_basic3\">" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintConclusionHtml19", null, locale1) + "</div>\n");
			sb.append("<table class=\"table-style01\">\n");
			sb.append("    <colgroup>\n");
			sb.append("        <col width=\"12%\" />\n");
			sb.append("        <col width=\"54%\" />\n");
			sb.append("        <col width=\"12%\" />\n");
			sb.append("        <col width=\"22%\" />\n");
			sb.append("    </colgroup>\n");
			sb.append("    <tr>\n");
			// 원본인계자
			sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintConclusionHtml20", null, locale1) + "</th>\n");
			sb.append("        <td>" + (String) map.get("org_hdovman_nm") + " / " + (String) map.get("org_hdovman_jikgup_nm") + " / " + (String) map.get("org_hdov_dept_nm") + "</td>\n");
			// 원본접수일
			sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintConclusionHtml21", null, locale1) + "</th>\n");
			sb.append("        <td class=\"tC\">" + (String) map.get("org_acptday") + "</td>\n");
			sb.append("    </tr>\n");
			sb.append("    <tr>\n");
			// 원본접수자
			sb.append("        <th>" + (String) messageSource.getMessage("las.page.field.contractManager.groupChief", null, locale1) + "</th>\n");
			sb.append("        <td>" + (String) map.get("org_tkovman_nm") + " / " + (String) map.get("org_tkovman_jikgup_nm") + " / " + (String) map.get("org_tkov_dept_nm") + "</td>\n");
			// 원본보관위치
			sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintConclusionHtml23", null, locale1) + "</th>\n");
			sb.append("        <td>" + StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String) map.get("org_strg_pos"))) + "</td>\n");
			sb.append("    </tr>\n");
			sb.append("    <tr>\n");
			// 메모
			sb.append("        <th>" + (String) messageSource.getMessage("clm.page.msg.common.memo", null, locale1) + "</th>\n");
			sb.append("        <td colspan='3'>" + StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String) map.get("org_acpt_dlay_cause"))) + "</td>\n");
			sb.append("    </tr>\n");

			sb.append("</table>\n");
		}
	}

	/**
	 * 인쇄용 이행정보 html
	 * 
	 * @param sb
	 * @param map
	 * @param form
	 * @param reqAuthInfo
	 * @throws Exception
	 */
	public void makePrintExecHtml(StringBuffer sb, Map map, String last_locale) throws Exception {
		if ("C02501".equals(map.get("prcs_depth"))) {
			return;
		}

		Locale locale1 = new Locale(last_locale);
		ConsultationExecVO vo = new ConsultationExecVO();
		vo.setCntrt_id((String) map.get("cntrt_id"));
		List listExec = consultationService.listConsultationExec(vo);

		int cnt1 = 0;
		int cnt2 = 0;
		int cnt3 = 0;

		for (int i = 0; i < listExec.size(); i++) {
			Map lom = (Map) listExec.get(i);
			if ("C05501".equals(lom.get("exec_gbn"))) {
				cnt1++;
			} else if ("C05502".equals(lom.get("exec_gbn"))) {
				cnt2++;
			} else {
				cnt3++;
			}
		}

		if (cnt1 + cnt2 + cnt3 == 0) {
			return;
		}

		// 이행정보
		sb.append("<div class=\"title_basic\"><h4>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintExecHtml01", null, locale1) + "</h4></div>\n");

		// 지불 계획
		if (cnt1 > 0) {
			// 지불계획
			sb.append("<div class=\"title_basic3\">" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintExecHtml02", null, locale1) + "</div>\n");
			sb.append("<table class=\"table-style01\">\n");
			sb.append("    <colgroup>\n");
			sb.append("        <col width=\"24%\" />\n");
			sb.append("        <col width=\"15%\" />\n");
			sb.append("        <col width=\"16%\" />\n");
			sb.append("        <col width=\"20%\" />\n");
			sb.append("    </colgroup>\n");
			sb.append("    <tr>\n");
			// (지불)이행항목
			sb.append("        <th class=\"tC\">" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintExecHtml03", null, locale1) + "</th>\n");
			// 완료예정일
			sb.append("        <th class=\"tC\">" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintExecHtml04", null, locale1) + "</th>\n");
			// 금액
			sb.append("        <th class=\"tC\">" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintExecHtml05", null, locale1) + "</th>\n");
			// 지불조건
			sb.append("        <th class=\"tC\">" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintExecHtml06", null, locale1) + "</th>\n");
			sb.append("    </tr>\n");
			for (int i = 0; i < listExec.size(); i++) {
				Map lom = (Map) listExec.get(i);
				if ("C05501".equals(lom.get("exec_gbn"))) {
					sb.append("    <tr>\n");
					sb.append("        <td>" + StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String) lom.get("exec_itm"))) + "</td>\n");
					sb.append("        <td class=\"tC\">" + (String) lom.get("exec_plndday") + "</td>\n");
					sb.append("        <td class=\"tR\">" + (String) lom.get("exec_amt") + "</td>\n");
					sb.append("        <td>" + StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars(StringUtil.nvl((String) lom.get("exec_cont"), ""))) + "</td>\n");
					sb.append("    </tr>\n");
				}
			}
			sb.append("</table>\n");
		}

		// 수금 계획
		if (cnt2 > 0) {
			// 수금계획
			sb.append("<div class=\"title_basic3\">" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintExecHtml07", null, locale1) + "</div>\n");
			sb.append("<table class=\"table-style01\">\n");
			sb.append("    <colgroup>\n");
			sb.append("        <col width=\"24%\" />\n");
			sb.append("        <col width=\"15%\" />\n");
			sb.append("        <col width=\"16%\" />\n");
			sb.append("        <col width=\"20%\" />\n");
			sb.append("    </colgroup>\n");
			sb.append("    <tr>\n");
			// (수금)이행항목
			sb.append("        <th class=\"tC\">" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintExecHtml08", null, locale1) + "</th>\n");
			// 완료예정일
			sb.append("        <th class=\"tC\">" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintExecHtml04", null, locale1) + "</th>\n");
			// 금액
			sb.append("        <th class=\"tC\">" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintExecHtml05", null, locale1) + "</th>\n");
			// 지불조건
			sb.append("        <th class=\"tC\">" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintExecHtml06", null, locale1) + "</th>\n");
			sb.append("    </tr>\n");
			for (int i = 0; i < listExec.size(); i++) {
				Map lom = (Map) listExec.get(i);
				if ("C05502".equals(lom.get("exec_gbn"))) {
					sb.append("    <tr>\n");
					sb.append("        <td>" + StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String) lom.get("exec_itm"))) + "</td>\n");
					sb.append("        <td class=\"tC\">" + (String) lom.get("exec_plndday") + "</td>\n");
					sb.append("        <td class=\"tR\">" + (String) lom.get("exec_amt") + "</td>\n");
					sb.append("        <td>" + StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars(StringUtil.nvl((String) lom.get("exec_cont"), ""))) + "</td>\n");
					sb.append("    </tr>\n");
				}
			}
			sb.append("</table>\n");
		}

		// 기타 이행계획
		if (cnt3 > 0) {
			// 기타 이행계획
			sb.append("<div class=\"title_basic3\">" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintExecHtml09", null, locale1) + "</div>\n");
			sb.append("<table class=\"table-style01\">\n");
			sb.append("    <colgroup>\n");
			sb.append("        <col width=\"24%\" />\n");
			sb.append("        <col width=\"15%\" />\n");
			sb.append("        <col width=\"36%\" />\n");
			sb.append("    </colgroup>\n");
			sb.append("    <tr>\n");
			// (기타)이행항목
			sb.append("        <th class=\"tC\">" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintExecHtml10", null, locale1) + "</th>\n");
			// 완료예정일
			sb.append("        <th class=\"tC\">" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintExecHtml04", null, locale1) + "</th>\n");
			sb.append("        <th class=\"tC\">Description</th>\n");
			for (int i = 0; i < listExec.size(); i++) {
				Map lom = (Map) listExec.get(i);
				if ("C05503".equals(lom.get("exec_gbn"))) {
					sb.append("    <tr>\n");
					sb.append("        <td>" + StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String) lom.get("exec_itm"))) + "</td>\n");
					sb.append("        <td class=\"tC\">" + (String) lom.get("exec_plndday") + "</td>\n");
					sb.append("        <td>" + StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars(StringUtil.nvl((String) lom.get("exec_cont"), ""))) + "</td>\n");
					sb.append("    </tr>\n");
				}
			}
			sb.append("    </tr>\n");
			sb.append("</table>\n");
		}
	}

	/**
	 * 인쇄용 결재라인 html
	 * 
	 * @param sb
	 * @param map
	 * @param vo
	 * @throws Exception
	 */
	public void makePrintApprLine(StringBuffer sb, Map map, ConsiderationVO vo, String last_locale) throws Exception {
		if (StringUtil.nvl(vo.getMis_id(), "").equals("")) {
			return;
		}

		try {
			Locale locale1 = new Locale(last_locale);
			List approvalHisList = considerationService.getApprLineList(vo);
			String prevMisId = null;
			if (approvalHisList != null && approvalHisList.size() > 0) {

				ListOrderedMap lomDetail = (ListOrderedMap) approvalHisList.get(0);

				sb.append("<table cellspacing=0 cellpadding=0 width=\"100%\" border=\"0\" nowrap>\n").append("<tbody>\n").append("	<tr>\n").append("		<td>\n")
						.append("			<table cellspacing=0 cellpadding=0 width=\"100%\" border=0>\n").append("			<tbody>\n").append("				<tr>\n")
						.append("					<td style=\"background-color: #ffffff\" height=10></td>\n").append("				</tr>\n").append("			</tbody>\n").append("			</table>\n").append("		</td>\n")
						.append("	</tr>\n").append("	<tr align=middle>\n").append("		<td style=\"padding-right: 0px; padding-left: 0px; padding-bottom: 0px; padding-top: 0px;\">\n")
						.append("			<table cellspacing=0 cellpadding=0 width=\"100%\" border=0>\n").append("			<tbody>\n").append("				<tr>\n")
						.append("					<td style=\"background-color: #b4b4b4\" height=1></td>\n").append("				</tr>\n").append("			</tbody>\n").append("			</table>\n")
						.append("			<table cellspacing=0 cellpadding=0 width=\"100%\" bgcolor=#ffffff border=0 style='margin-bottom:10px;'>\n").append("			<tbody>\n").append("				<tr>\n")
						// 제목
						.append("					<td style=\"padding-right: 5px; padding-left: 10px; font-weight: bold; font-size: 9pt; width: 10%; color: #7a5e4b; height: 28px; background-color: #f4f1e7\" nowrap>"
								+ (String) messageSource.getMessage("clm.page.field.consideration.makePrintApprLine01", null, locale1) + "</td>\n")
						.append("					<td style=\"padding-left: 5px; font-weight: bold; font-size: 9pt; color: #666666; border-left: #cccccc 1px solid; padding-top: 5px; background-color: #f4f1e7;text-align: left !important\" width=\"100%\">\n")
						.append("						<table cellspacing=0 cellpadding=0 border=0>\n").append("						<tbody>\n").append("							<tr>\n")
						.append("								<td style=\"padding-left: 10px; font-size: 9pt; color: #666666; padding-top: 2px; height: 28px; background-color: #f4f1e7\">"
								+ StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String) lomDetail.get("title"))) + "</td>\n")
						.append("							</tr>\n").append("						</tbody>\n").append("						</table>\n").append("					</td>\n").append("				</tr>\n")
						.append("				<tr>\n").append("					<td style=\"background-color: #dbdbdb\" colspan=2 height=1></td>\n").append("				</tr>\n").append("				<tr>\n")
						// 작성자
						.append("					<td style=\"padding-right: 5px; padding-left: 10px; font-weight: bold; font-size: 9pt; width: 10%; color: #7a5e4b; padding-top: 1px; height: 28px; background-color: #fcfbf8\">"
								+ (String) messageSource.getMessage("clm.page.field.consideration.makePrintApprLine02", null, locale1) + "</td>\n")
						.append("					<td style=\"padding-left: 5px; font-weight: bold; font-size: 9pt; color: #666666; border-left: #cccccc 1px solid; padding-top: 5px; background-color: #fcfbf8;text-align: left !important\" width=\"100%\">\n")
						.append("						<table cellspacing=0 cellpadding=0 border=0>\n").append("						<tbody>\n").append("							<tr>\n")
						.append("								<td style=\"padding-left: 10px; font-size: 9pt; color: #666666; padding-top: 2px; height: 28px; background-color: #fcfbf8\">" + lomDetail.get("user_name") + " / "
								+ lomDetail.get("duty") + " / " + lomDetail.get("dept_name") + "</td>\n")
						.append("							</tr>\n").append("						</tbody>\n").append("						</table>\n").append("					</td>\n").append("				</tr>\n")
						.append("				<tr>\n").append("					<td style=\"background-color: #dbdbdb\" colspan=2 height=1></td>\n").append("				</tr>\n")
						.append("				<tr style='border-bottom: #cccccc 1px solid;'>\n")
						// 표준시간
						.append("					<td style=\"padding-right: 5px; padding-left: 10px; font-weight: bold; font-size: 9pt; width: 10%; color: #7a5e4b; padding-top: 1px; height: 28px; background-color: #fcfbf8\">"
								+ (String) messageSource.getMessage("clm.page.field.consideration.makePrintApprLine03", null, locale1) + "</td>\n")
						.append("					<td style=\"padding-left: 5px; font-weight: bold; font-size: 9pt; color: #666666; border-left: #cccccc 1px solid; padding-top: 5px; background-color: #fcfbf8;text-align: left !important\" width=\"100%\">\n")
						.append("						<table cellspacing=0 cellpadding=0 border=0>\n").append("						<tbody>\n").append("							<tr>\n")
						.append("								<td style=\"padding-left: 10px; font-size: 9pt; color: #666666; padding-top: 2px; height: 28px; background-color: #fcfbf8\">" + lomDetail.get("time_zone") + "</td>\n")
						.append("							</tr>\n").append("						</tbody>\n").append("						</table>\n").append("					</td>\n").append("				</tr>\n")
						.append("			</tbody>\n").append("			</table>\n").append("			<table cellspacing=0 cellpadding=0 width=\"100%\" border=0>\n").append("			<tbody>\n").append("				<tr>\n")
						.append("					<td style=\"background-color: #b4b4b4\" height=1></td>\n").append("				</tr>\n").append("			</tbody>\n").append("			</table>\n")
						.append("			<table style=\"table-layout: fixed\" cellspacing=0 cellpadding=0 width=\"100%\" border=0>\n").append("			<tbody>\n").append("				<tr align=middle>\n")
						// 순번
						.append("					<td style=\"font-weight: bold; font-size: 9pt; color: #7a5e4b; height: 28px; background-color: #e4e2d6; text-align: center\" nowrap width=\"10%\" >"
								+ (String) messageSource.getMessage("clm.page.field.consideration.makePrintApprLine04", null, locale1) + "</td>\n")
						// 구분
						.append("					<td style=\"font-weight: normal; border-left: #cccccc 1px solid; font-size: 9pt; color: #333333; height: 28px; background-color: #e4e2d6; text-align: center\" nowrap width=\"10%\" >"
								+ (String) messageSource.getMessage("clm.page.field.consideration.makePrintApprLine05", null, locale1) + "</td>\n")
						// 성명
						.append("					<td style=\"font-weight: normal; border-left: #cccccc 1px solid; font-size: 9pt; color: #333333; height: 28px; background-color: #e4e2d6; text-align: center\" nowrap width=\"20%\" >"
								+ (String) messageSource.getMessage("clm.page.field.consideration.makePrintApprLine06", null, locale1) + "</td>\n")
						// 결재일시
						.append("					<td style=\"font-weight: normal; border-left: #cccccc 1px solid; font-size: 9pt; color: #333333; height: 28px; background-color: #e4e2d6; text-align: center\" nowrap width=\"25%\" >"
								+ (String) messageSource.getMessage("clm.page.field.consideration.makePrintApprLine07", null, locale1) + "</td>\n")
						// 부서명
						.append("					<td style=\"font-weight: normal; border-left: #cccccc 1px solid; font-size: 9pt; color: #333333; height: 28px; background-color: #e4e2d6; text-align: center\" nowrap width=\"25%\" >"
								+ (String) messageSource.getMessage("clm.page.field.consideration.makePrintApprLine08", null, locale1) + "</td>\n")
						// 상태
						.append("					<td style=\"font-weight: normal; border-left: #cccccc 1px solid; font-size: 9pt; color: #333333; height: 28px; background-color: #e4e2d6; text-align: center\" nowrap width=\"10%\" >"
								+ (String) messageSource.getMessage("clm.page.field.consideration.makePrintApprLine09", null, locale1) + "</td>\n")
						.append("				</tr>\n").append("				<tr>\n").append("					<td bgcolor=#d9d9d9 colspan=6 height=1 ></td>\n").append("				</tr>\n");

				int idx = 0;
				for (int i = 0; i < approvalHisList.size(); i++) {
					ListOrderedMap s1Lom = (ListOrderedMap) approvalHisList.get(i);
					sb.append("				<tr>\n");
					if (prevMisId == null || !prevMisId.equals(s1Lom.get("mis_id"))) {
						sb.append(
								"					<td style=\"padding-right: 5px; padding-left: 10px; font-weight: bold; font-size: 9pt; color: #7a5e4b; padding-top: 1px; border-bottom: #cccccc 1px solid; height: 28px; background-color: #fcfbf8\" align=middle rowspan='"
										+ s1Lom.get("cnt") + "' >" + (++idx) + "</td>\n");
					}
					sb.append(
							"					<td style=\"padding-left: 10px; font-size: 9pt; color: #333333; padding-top: 2px; border-bottom: #cccccc 1px solid; border-left: #cccccc 1px solid; height: 28px; background-color: #fcfbf8;text-align: left !important\" align='middle'>"
									+ s1Lom.get("activity_nm") + "</td>\n")
							.append("					<td style=\"padding-left: 10px; font-size: 9pt; color: #333333; padding-top: 2px; border-bottom: #cccccc 1px solid; border-left: #cccccc 1px solid; height: 28px; background-color: #fcfbf8;text-align: left !important\">"
									+ s1Lom.get("user_name") + "</a></td>\n")
							.append("					<td style=\"padding-left: 10px; font-size: 9pt; color: #755232; padding-top: 2px; border-bottom: #cccccc 1px solid; border-left: #cccccc 1px solid; height: 28px; background-color: #fcfbf8;text-align: left !important\" align='middle'>"
									+ s1Lom.get("approved") + "</td>\n")
							.append("					<td style=\"padding-left: 10px; font-size: 9pt; color: #333333; padding-top: 2px; border-bottom: #cccccc 1px solid; border-left: #cccccc 1px solid; height: 28px; background-color: #fcfbf8;text-align: left !important\">"
									+ s1Lom.get("dept_name") + "</td>\n");
					if (prevMisId == null || !prevMisId.equals(s1Lom.get("mis_id"))) {
						sb.append(
								"					<td style=\"padding-right: 5px; padding-left: 10px; font-size: 9pt; color: #7a5e4b; padding-top: 1px; border-bottom: #cccccc 1px solid; border-left: #cccccc 1px solid; height: 28px; background-color: #fcfbf8\" align=middle rowspan='"
										+ s1Lom.get("cnt") + "' >" + s1Lom.get("status_nm") + "</td>\n");
					}
					sb.append("				</tr>\n");
					if (!"9".equals(s1Lom.get("activity"))) {
						sb.append("				<tr>\n")
								.append("					<td style=\"padding-left: 10px; font-size: 9pt; color: #666666; word-break: break-all; padding-top: 2px; border-bottom: #cccccc 1px solid; border-left: #cccccc 1px solid; height: 28px; background-color: #ffffff; word-wrap: break-word;text-align: left !important\" colspan=4 >"
										+ StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String) s1Lom.get("opinion"))) + "</td>\n")
								.append("				</tr>\n");
					} else {
					}
					prevMisId = (String) s1Lom.get("mis_id");
				}

				sb.append("			</tbody>\n").append("			</table>\n").append("		</td>\n").append("	</tr>\n").append("</tbody>\n").append("</table>\n");
			} else {

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
	 * 종료정보 세팅
	 * 
	 * @param sb
	 * @param map
	 * @param form
	 * @param reqAuthInfo
	 * @throws Exception
	 */
	public void makePrintCompletionHtml(StringBuffer sb, Map map, ConsiderationVO vo, List etcAttachList, String last_locale) throws Exception {

		if (!"C02505".equals(map.get("prcs_depth"))) {
			return;
		}

		Locale locale1 = new Locale(last_locale);
		Map lom = considerationService.getContractApproveMan(vo);
		// 종료정보
		sb.append("<div class=\"title_basic\"><h4>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCompletionHtml01", null, locale1) + "</h4></div>\n");
		sb.append("<table class=\"table-style01\">\n");
		sb.append("    <colgroup>\n");
		sb.append("        <col width=\"12%\" />\n");
		sb.append("        <col width=\"21%\" />\n");
		sb.append("        <col width=\"12%\" />\n");
		sb.append("        <col width=\"21%\" />\n");
		sb.append("        <col width=\"12%\" />\n");
		sb.append("        <col width=\"22%\" />\n");
		sb.append("    </colgroup>\n");
		sb.append("    <tr>\n");
		// 요청자
		sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCompletionHtml02", null, locale1) + "</th>\n");
		sb.append("        <td colspan='3'>" + StringUtil.bvl((String) map.get("cntrt_chge_demndman_nm"), "") + " / " + StringUtil.bvl((String) map.get("cntrt_chge_demndman_jikgup_nm"), "") + " / "
				+ StringUtil.bvl((String) map.get("cntrt_chge_demnd_dept_nm"), "") + "</td>\n");
		// 요청일
		sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCompletionHtml03", null, locale1) + "</th>\n");
		sb.append("        <td>" + (String) map.get("cntrt_chge_demndday") + "</td>\n");
		sb.append("    </tr>\n");
		sb.append("    <tr>\n");
		// 승인자
		sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCompletionHtml04", null, locale1) + "</th>\n");
		sb.append("        <td colspan='3'>" + StringUtil.bvl((String) lom.get("user_name"), "") + " / " + StringUtil.bvl((String) lom.get("duty"), "") + " / " + StringUtil.bvl((String) lom.get("dept_name"), "") + "</td>\n");
		// 상태변경
		sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCompletionHtml05", null, locale1) + "</th>\n");
		sb.append("        <td>" + (String) map.get("cntrt_status_nm") + "</td>\n");
		sb.append("    </tr>\n");
		sb.append("    <tr>\n");
		// 상태변경사유
		sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCompletionHtml06", null, locale1) + "</th>\n");
		sb.append("        <td colspan='5'>" + StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String) map.get("cntrt_chge_demnd_cause"))) + "</td>\n");
		sb.append("    </tr>\n");
		sb.append("    <tr>\n");
		// 첨부파일
		sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCompletionHtml07", null, locale1) + "</th>\n");
		sb.append("        <td colspan=\"5\"  class=\"tal_lineL\">\n");
		for (int i = 0; i < etcAttachList.size(); i++) {
			Map attach = (Map) etcAttachList.get(i);
			if ("4".equals(attach.get("filetype"))) {
				sb.append(attach.get("org_file_nm") + "<br/>");
			}
		}
		sb.append("        </td>\n");
		sb.append("    </tr>\n");

		if (!StringUtil.nvl((String) map.get("cntrt_chge_confman_nm"), "").equals("")) {
			sb.append("    <tr>\n");
			// 확인자
			sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCompletionHtml08", null, locale1) + "</th>\n");
			sb.append("        <td colspan='3'>" + (String) map.get("cntrt_chge_confman_nm") + " / " + (String) map.get("cntrt_chge_confman_jikgup_nm") + " / " + (String) map.get("cntrt_chge_conf_dept_nm") + "</td>\n");
			// 확인여부
			sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCompletionHtml09", null, locale1) + "</th>\n");
			sb.append("        <td>" + (String) map.get("cntrt_chge_conf_yn") + "</td>\n");
			sb.append("    </tr>\n");
			sb.append("    <tr>\n");
			// 확인의견
			sb.append("        <th>" + (String) messageSource.getMessage("clm.page.field.consideration.makePrintCompletionHtml10", null, locale1) + "</th>\n");
			sb.append("        <td colspan='5'>" + StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String) map.get("cntrt_chge_conf_cont"))) + "</td>\n");
			sb.append("    </tr>\n");
		}
		sb.append("</table>\n");

	}

	/**
	 * 이행정보 세팅
	 * 
	 * @param sb
	 * @param map
	 * @param form
	 * @param reqAuthInfo
	 * @throws Exception
	 */
	public void makeAutoRnewHisHtml(StringBuffer sb, Map map, ConsiderationVO vo, String last_locale) throws Exception {
		if (!"C02505".equals(map.get("prcs_depth"))) {
			return;
		}

		List list = considerationService.getAutoRnewList(vo);
		Locale locale1 = new Locale(last_locale);

		if (list.size() > 0) {
			// 자동연장이력
			sb.append("<div class=\"title_basic\"><h4>" + (String) messageSource.getMessage("clm.page.field.consideration.makeAutoRnewHisHtml01", null, locale1) + "</h4></div>\n");

			sb.append("<table class=\"table-style01\">\n");
			sb.append("    <colgroup>\n");
			sb.append("        <col width=\"8%\" />\n");
			sb.append("        <col width=\"15%\" />\n");
			sb.append("        <col width=\"15%\" />\n");
			sb.append("        <col width=\"31%\" />\n");
			sb.append("        <col width=\"31%\" />\n");
			sb.append("    </colgroup>\n");
			sb.append("    <tr>\n");
			// 차수
			sb.append("        <th class=\"tC\">" + (String) messageSource.getMessage("clm.page.field.consideration.makeAutoRnewHisHtml02", null, locale1) + "</th>\n");
			// 연장요청일
			sb.append("        <th class=\"tC\">" + (String) messageSource.getMessage("clm.page.field.consideration.makeAutoRnewHisHtml03", null, locale1) + "</th>\n");
			// 만료일
			sb.append("        <th class=\"tC\">" + (String) messageSource.getMessage("clm.page.field.consideration.makeAutoRnewHisHtml04", null, locale1) + "</th>\n");
			// 비고
			sb.append("        <th class=\"tC\">" + (String) messageSource.getMessage("clm.page.field.consideration.makeAutoRnewHisHtml05", null, locale1) + "</th>\n");
			// 승인자
			sb.append("        <th class=\"tC\">" + (String) messageSource.getMessage("clm.page.field.consideration.makeAutoRnewHisHtml06", null, locale1) + "</th>\n");
			sb.append("    </tr>\n");
			for (int i = 0; i < list.size(); i++) {
				Map lom = (Map) list.get(i);
				sb.append("    <tr>\n");
				// 차
				sb.append("        <td class=\"tC\">" + lom.get("rn") + (String) messageSource.getMessage("clm.page.field.consideration.makeAutoRnewHisHtml07", null, locale1) + "</td>\n");
				sb.append("        <td class=\"tC\">" + (String) lom.get("cntrtperiod_startday") + "</td>\n");
				sb.append("        <td class=\"tC\">" + (String) lom.get("cntrtperiod_endday") + "</td>\n");
				sb.append("        <td>" + StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars(StringUtil.nvl((String) lom.get("mod_cause"), ""))) + "</td>\n");
				sb.append("        <td>" + (String) lom.get("mod_nm") + " / " + (String) lom.get("mod_jikgup_nm") + " / " + (String) lom.get("mod_dept_nm") + "</td>\n");
				sb.append("    </tr>\n");
			}
			sb.append("</table>\n");
		}

	}

	/**
	 * 리뷰어 휴가 기간 조회
	 * 
	 * @param sb
	 * @param map
	 * @param form
	 * @param reqAuthInfo
	 * @throws Exception
	 */
	public void checkVacation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			ConsiderationForm form = new ConsiderationForm();
			ConsiderationVO vo = new ConsiderationVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			List resultList = null;

			resultList = considerationService.checkVacation(vo);
			ListOrderedMap lom = null;

			String data = "";
			JSONObject jo = new JSONObject();
			if (resultList != null) {
				lom = (ListOrderedMap) resultList.get(0);
				data = "Start Date : " + lom.get("start_dt") + "  ~  " + "End Date : " + lom.get("end_dt");
				if (StringUtil.bvl(lom.get("type1"), "").trim().equals("C07102")) {
					jo.put("vacation", "V");
				} else if (StringUtil.bvl(lom.get("type1"), "").trim().equals("C07103")) {
					jo.put("vacation", "B");
				} else if (StringUtil.bvl(lom.get("type1"), "").trim().equals("C07104")) {
					jo.put("vacation", "E");
				}
			}
			jo.put("result", data);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
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

	/**
	 * 2014-07-24 Kevin added. This method is called through ajax code from
	 * client side, returns step/status information of specified cnsdreq id.
	 * 
	 * @param req
	 * @param res
	 */
	public void getStepStatusinformationPerCnsdreqID(HttpServletRequest req, HttpServletResponse res) {
		JSONObject json = new JSONObject();
		PrintWriter output = null;

		try {
			res.setContentType("application/json; charset=utf-8");
			output = res.getWriter();

			String cnsdreq_id = req.getParameter("cnsdreq_id");
			ConsiderationVO vo = new ConsiderationVO();
			vo.setCnsdreq_id(cnsdreq_id);
			Map map = considerationService.getStepStatusInformationWithCNSDREQID(vo);
			if (map == null) {
				json.put("result", false);
			} else {
				json.putAll(map);
				json.put("result", true);
			}
		} catch (Exception ex) {
			json.put("result", false);
		} finally {
			output.print(json);
			output.flush();
			output.close();
		}
	}

	/**
	 * 2014-08-12 Kevin added. Get data to be used Basic Information section on
	 * each contract detail page.
	 */
	public void getDataForBasicInformationSection(HttpServletRequest req, HttpServletResponse res) {
		JSONObject json = new JSONObject();
		PrintWriter writer = null;

		try {
			res.setContentType("application/json; charset=utf-8");
			writer = res.getWriter();

			String cnsdreq_id = req.getParameter("cnsdreq_id");
			ConsiderationVO vo = new ConsiderationVO();
			vo.setCnsdreq_id(cnsdreq_id);
			List list = considerationService.getDataForBasicInformationSection(vo);
			json.put("result", (list != null && list.size() > 0));
			json.put("list", list);
		} catch (Exception ex) {
			json.put("result", false);
		} finally {
			writer.print(json);
			writer.flush();
			writer.close();
		}
	}
}
