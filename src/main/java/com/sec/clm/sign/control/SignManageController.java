/**
 * Project Name : 법무시스템
 * File Name : SignManageController.java
 * Description : 날인 관리 Controller
 * Author : 박병주
 * Date : 2013.05
 * Copyright : 2013 by SAMSUNG. All rights reserved.
 */
package com.sec.clm.sign.control;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.http.HttpRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import samsung.esb.approval.vo.StartProcessWSVO;

import anyframe.common.util.DateUtil;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.ExcelBuilder;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.singleIF.service.EsbApprovalService;
import com.sds.secframework.singleIF.service.EsbOrgService;
import com.sds.secframework.util.service.ComUtilService;
import com.sec.clm.manage.dto.ConsultationForm;
import com.sec.clm.manage.dto.ConsultationVO;
import com.sec.clm.sign.dto.SignManageForm;
import com.sec.clm.sign.dto.SignManageVO;
import com.sec.clm.sign.service.SignManageService;
import com.sec.common.clmscom.service.CLMSCommonService;

public class SignManageController extends CommonController {
	
	/**
	 * ROLE 구분을 위한 변수
	 */
	static String SEAL = "SC0101"; // 날인관리
	static String DOC = "SC0102"; // 증명서류 발급 관리

	private SignManageService signManageService;
	public void setSignManageService(SignManageService signManageService) {
		this.signManageService = signManageService;
	}
	
	private CLMSCommonService clmsComService;
	public void setClmsComService(CLMSCommonService clmsComService)
	{
		this.clmsComService = clmsComService;
	}
	
	// ComUtil 서비스
	private ComUtilService comUtilService;

	public void setComUtilService(ComUtilService comUtilService) {
		this.comUtilService = comUtilService;
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

//	/**
//	 * 메일전송
//	 */
//	private MailSendService mailSendService ;
//
//	public void setMailSendService(MailSendService mailSendService) {
//		this.mailSendService = mailSendService;
//	}
	
	/**
	 * 날인 신청 목록
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listSign(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			
			HttpSession session = request.getSession(false);
			
			String forwardURL = "/WEB-INF/jsp/clm/sign/Sign_l.jsp";

			SignManageForm form = null;
			SignManageVO vo =	null;		

			form = new SignManageForm();
			vo = new SignManageVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
            String login_nm = "";
			
			if("C10".equals((String)session.getAttribute("secfw.session.comp_cd"))){
				
				login_nm = "";
				
			} else {
				
				login_nm = (String)session.getAttribute("secfw.session.user_nm");
				
			}
			
			//XSS
			form.setSrch_apl_m(StringUtil.bvl(form.getSrch_apl_m(),""));
		    form.setSrch_apl_cls(StringUtil.bvl(form.getSrch_apl_cls(),""));
			form.setSrch_apl_prev30d(StringUtil.bvl(form.getSrch_apl_prev30d(),""));
			form.setSrch_apl_seal_no(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_apl_seal_no(),"")));
			form.setSrch_apl_y(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_apl_y(),"")));
			form.setSrch_doc_issue_status(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_doc_issue_status(),"")));
			form.setSrch_doc_no(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_doc_no(),"0")));
			form.setSrch_sbm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_sbm(),"")));
			form.setSrch_seal_ffmt_status(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_seal_ffmt_status(),"")));
			form.setSrch_seal_ffmtday_end(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_seal_ffmtday_end(),"")));
			form.setSrch_seal_ffmtday_start(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_seal_ffmtday_start(),"")));
			form.setSrch_seal_ffmtman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_seal_ffmtman_nm(),"")));
			form.setSrch_seal_knd_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_seal_knd_cd(),"")));
			form.setSrch_seal_rqst_status(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_seal_rqst_status(),"")));
			form.setSrch_seal_rqstman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_seal_rqstman_nm(),"")));
			form.setSrch_title(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_title(),"")));
			
			form.setSrch_apl_out_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_apl_out_yn(),"")));
			form.setSrch_prc_seal_no(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_prc_seal_no(),"")));
			form.setSrch_rtn_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_rtn_yn(),"")));
			form.setSrch_prc_ymd_start(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_prc_ymd_start(),"")));
			form.setSrch_prc_ymd_end(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_prc_ymd_end(),"")));
			form.setSrch_rtn_ymd_start(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_rtn_ymd_start(),"")));
			form.setSrch_rtn_ymd_end(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_rtn_ymd_end(),"")));
			
			form.setGoExcel(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getGoExcel(),"N")));

			vo.setSrch_apl_m(StringUtil.bvl(vo.getSrch_apl_m(),""));
		    vo.setSrch_apl_cls(StringUtil.bvl(vo.getSrch_apl_cls(),""));
			vo.setSrch_apl_prev30d(StringUtil.bvl(vo.getSrch_apl_prev30d(),""));
			vo.setSrch_apl_seal_no(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_apl_seal_no(),"")));
			vo.setSrch_apl_y(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_apl_y(),"")));
			vo.setSrch_doc_issue_status(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_doc_issue_status(),"")));
			vo.setSrch_doc_no(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_doc_no(),"0")));
			vo.setSrch_sbm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_sbm(),"")));
			vo.setSrch_seal_ffmt_status(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_ffmt_status(),"")));
			vo.setSrch_seal_ffmtday_end(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_ffmtday_end(),"")));
			vo.setSrch_seal_ffmtday_start(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_ffmtday_start(),"")));
			vo.setSrch_seal_ffmtman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_ffmtman_nm(),"")));
			vo.setSrch_seal_knd_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_knd_cd(),"")));
			vo.setSrch_seal_rqst_status(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_rqst_status(),"")));
			vo.setSrch_seal_rqstman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_rqstman_nm(),"")));
			vo.setSrch_title(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_title(),"")));
			
			vo.setSrch_apl_out_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_apl_out_yn(),"")));
			vo.setSrch_prc_seal_no(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_prc_seal_no(),"")));
			vo.setSrch_rtn_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_rtn_yn(),"")));
			vo.setSrch_prc_ymd_start(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_prc_ymd_start(),"")));
			vo.setSrch_prc_ymd_end(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_prc_ymd_end(),"")));
			vo.setSrch_rtn_ymd_start(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_rtn_ymd_start(),"")));
			vo.setSrch_rtn_ymd_end(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_rtn_ymd_end(),"")));
			vo.setSrch_seal_rqstman_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_rqstman_id(),"")));
			
			vo.setGoExcel(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getGoExcel(),"N")));
			
			//날짜 "-" 삭제 처리
			vo.setSrch_seal_ffmtday_start(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_seal_ffmtday_start(),"")),"-"));
			vo.setSrch_prc_ymd_start(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_prc_ymd_start(),"")),"-"));
			vo.setSrch_prc_ymd_end(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_prc_ymd_end(),"")),"-"));
			vo.setSrch_rtn_ymd_start(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_rtn_ymd_start(),"")),"-"));
			vo.setSrch_rtn_ymd_end(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_rtn_ymd_end(),"")),"-"));
						
			// 신청 목록용 조건 세팅 START
			vo.setSrch_seal_ffmtman_id("");
			vo.setSrch_doc_issuer_id("");					
			// 권한세팅 : RM00, RA00, RE01, RE02 인지 확인 후 해당 권한이  아니면 신청 당사자 본인만 보이게 세팅
			boolean auth_sign_process = checkAuthByRole(vo);
			if(!auth_sign_process){
				vo.setAuth_search(true);
				vo.setSrch_seal_rqstman_id(form.getSession_user_id());
				
			}
			// 신청 목록용 조건 세팅 END
			
			PageUtil pageUtil = new PageUtil();

			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());	

			String returnMessage = "";
			
			ArrayList tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.roleList") ;
			
			if(tmpSessionRoleList.contains("RB01") || tmpSessionRoleList.contains("RB02")){
				
				// 전자 담당자일 경우 각 회사를 조회 할 수 있게 처리 됨.
				vo.setsRb("Y");
				
			} else {
				
				vo.setsRb("");
				
			}
			

			List resultList = signManageService.listSign(vo);

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
				
				BigDecimal total_cnt  = (BigDecimal)lom.get("total_cnt");

				pageUtil.setTotalRow(total_cnt.intValue());
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();

				if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
					returnMessage = (String)request.getAttribute("returnMessage");
				}
			}else {
				if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
					returnMessage = (String)request.getAttribute("returnMessage");
				}
			}

			ModelAndView mav = new ModelAndView();
			
			mav.setViewName(forwardURL);
			form.setForwardFrom("listSign");
			
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			
			// 신청/처리 구분자
			mav.addObject("prc_mode", "apl");
			return mav;

		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 * 날인 처리 목록
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listSignMng(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			HttpSession session = request.getSession(false);
			
			String forwardURL = "/WEB-INF/jsp/clm/sign/Sign_l.jsp";

			SignManageForm form = null;
			SignManageVO vo =	null;		

			form = new SignManageForm();
			vo = new SignManageVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
            String login_nm = "";
			
			if("C10".equals((String)session.getAttribute("secfw.session.comp_cd"))){
				
				login_nm = "";
				
			} else {
				
				if("".equals((String)form.getSrch_seal_ffmtman_nm())){
					login_nm = "";
				} else {
					login_nm = (String)session.getAttribute("secfw.session.user_nm");
				}
				
				
			}
			
			//XSS
			form.setSrch_apl_m(StringUtil.bvl(form.getSrch_apl_m(),""));
		    form.setSrch_apl_cls(StringUtil.bvl(form.getSrch_apl_cls(),""));
			form.setSrch_apl_prev30d(StringUtil.bvl(form.getSrch_apl_prev30d(),""));
			form.setSrch_apl_seal_no(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_apl_seal_no(),"")));
			form.setSrch_apl_y(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_apl_y(),"")));
			form.setSrch_doc_issue_status(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_doc_issue_status(),"")));
			form.setSrch_doc_no(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_doc_no(),"0")));
			form.setSrch_sbm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_sbm(),"")));
			form.setSrch_seal_ffmt_status(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_seal_ffmt_status(),"")));
			form.setSrch_seal_ffmtday_end(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_seal_ffmtday_end(),"")));
			form.setSrch_seal_ffmtday_start(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_seal_ffmtday_start(),"")));
			form.setSrch_seal_ffmtman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_seal_ffmtman_nm(),login_nm)));
			form.setSrch_seal_knd_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_seal_knd_cd(),"")));
			form.setSrch_seal_rqst_status(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_seal_rqst_status(),"")));
			form.setSrch_seal_rqstman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_seal_rqstman_nm(),"")));
			form.setSrch_title(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_title(),"")));			
			
			form.setSrch_apl_out_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_apl_out_yn(),"")));
			form.setSrch_prc_seal_no(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_prc_seal_no(),"")));
			form.setSrch_rtn_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_rtn_yn(),"")));
			form.setSrch_prc_ymd_start(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_prc_ymd_start(),"")));
			form.setSrch_prc_ymd_end(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_prc_ymd_end(),"")));
			form.setSrch_rtn_ymd_start(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_rtn_ymd_start(),"")));
			form.setSrch_rtn_ymd_end(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_rtn_ymd_end(),"")));
			
			form.setGoExcel(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getGoExcel(),"N")));

			vo.setSrch_apl_m(StringUtil.bvl(vo.getSrch_apl_m(),""));
		    vo.setSrch_apl_cls(StringUtil.bvl(vo.getSrch_apl_cls(),""));
			vo.setSrch_apl_prev30d(StringUtil.bvl(vo.getSrch_apl_prev30d(),""));
			vo.setSrch_apl_seal_no(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_apl_seal_no(),"")));
			vo.setSrch_apl_y(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_apl_y(),"")));
			vo.setSrch_doc_issue_status(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_doc_issue_status(),"")));
			vo.setSrch_doc_no(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_doc_no(),"0")));
			vo.setSrch_sbm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_sbm(),"")));
			vo.setSrch_seal_ffmt_status(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_ffmt_status(),"")));
			vo.setSrch_seal_ffmtday_end(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_ffmtday_end(),"")));
			vo.setSrch_seal_ffmtday_start(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_ffmtday_start(),"")));
			vo.setSrch_seal_ffmtman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_ffmtman_nm(),login_nm)));
			vo.setSrch_seal_knd_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_knd_cd(),"")));
			vo.setSrch_seal_rqst_status(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_rqst_status(),"")));
			vo.setSrch_seal_rqstman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_rqstman_nm(),"")));
			vo.setSrch_title(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_title(),"")));
			
			vo.setSrch_apl_out_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_apl_out_yn(),"")));
			vo.setSrch_prc_seal_no(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_prc_seal_no(),"")));
			vo.setSrch_rtn_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_rtn_yn(),"")));
			vo.setSrch_prc_ymd_start(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_prc_ymd_start(),"")));
			vo.setSrch_prc_ymd_end(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_prc_ymd_end(),"")));
			vo.setSrch_rtn_ymd_start(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_rtn_ymd_start(),"")));
			vo.setSrch_rtn_ymd_end(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_rtn_ymd_end(),"")));
			vo.setSrch_seal_rqstman_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_rqstman_id(),"")));
			
			//날짜 "-" 삭제 처리
			vo.setSrch_seal_ffmtday_start(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_seal_ffmtday_start(),"")),"-"));
			vo.setSrch_prc_ymd_start(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_prc_ymd_start(),"")),"-"));
			vo.setSrch_prc_ymd_end(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_prc_ymd_end(),"")),"-"));
			vo.setSrch_rtn_ymd_start(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_rtn_ymd_start(),"")),"-"));
			vo.setSrch_rtn_ymd_end(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_rtn_ymd_end(),"")),"-"));
			
			vo.setGoExcel(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getGoExcel(),"N")));
						
			// 처리 목록 조건 세팅
			vo.setSrch_seal_rqst_status("AP0103"); //신청처리 상태가 '승인' 인 것만 표시

			
			// 메인화면에서 링크를 타고 오는 경우
			String forwardFrom = StringUtil.bvl((String) request.getParameter("forwardFrom"), "");
			
			if("MAIN".equals(forwardFrom)){
				vo.setForwardFrom(forwardFrom);
				vo.setSrch_seal_ffmtman_id(vo.getSession_user_id());
				vo.setSrch_doc_issuer_id(vo.getSession_user_id());
			} else{
				vo.setSrch_seal_ffmtman_id("");
				vo.setSrch_doc_issuer_id("");
			}
			
			PageUtil pageUtil = new PageUtil();

			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());	

			String returnMessage = "";

			List resultList = signManageService.listSign(vo);

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
				
				BigDecimal total_cnt  = (BigDecimal)lom.get("total_cnt");
				pageUtil.setTotalRow(total_cnt.intValue());
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();

				if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
					returnMessage = (String)request.getAttribute("returnMessage");
				}
			}else {
				if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
					returnMessage = (String)request.getAttribute("returnMessage");
				}
			}

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			form.setForwardFrom("listSignMng");
			
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			
			// 신청/처리구분
			mav.addObject("prc_mode", "prc");
			
			return mav;

		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 * 인장반출 내역
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listAplOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			HttpSession session = request.getSession(false);
			
			String forwardURL = "/WEB-INF/jsp/clm/sign/Sign_l.jsp";

			SignManageForm form = null;
			SignManageVO vo =	null;		

			form = new SignManageForm();
			vo = new SignManageVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			String login_nm = "";
			
			if("C10".equals((String)session.getAttribute("secfw.session.comp_cd"))){
				
				login_nm = "";
				
			} else {
				
				login_nm = (String)session.getAttribute("secfw.session.user_nm");
				
			}
			
			//XSS
			form.setSrch_apl_m(StringUtil.bvl(form.getSrch_apl_m(),""));
		    form.setSrch_apl_cls(StringUtil.bvl(form.getSrch_apl_cls(),""));
			form.setSrch_apl_prev30d(StringUtil.bvl(form.getSrch_apl_prev30d(),""));
			form.setSrch_apl_seal_no(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_apl_seal_no(),"")));
			form.setSrch_apl_y(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_apl_y(),"")));
			form.setSrch_doc_issue_status(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_doc_issue_status(),"")));
			form.setSrch_doc_no(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_doc_no(),"0")));
			form.setSrch_sbm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_sbm(),"")));
			form.setSrch_seal_ffmt_status(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_seal_ffmt_status(),"")));
			form.setSrch_seal_ffmtday_end(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_seal_ffmtday_end(),"")));
			form.setSrch_seal_ffmtday_start(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_seal_ffmtday_start(),"")));
			form.setSrch_seal_ffmtman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_seal_ffmtman_nm(),"")));
			form.setSrch_seal_knd_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_seal_knd_cd(),"")));
			form.setSrch_seal_rqst_status(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_seal_rqst_status(),"")));
			form.setSrch_seal_rqstman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_seal_rqstman_nm(),"")));
			form.setSrch_title(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_title(),"")));			
			
			form.setSrch_apl_out_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_apl_out_yn(),"")));
			form.setSrch_prc_seal_no(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_prc_seal_no(),"")));
			form.setSrch_rtn_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_rtn_yn(),"")));
			form.setSrch_prc_ymd_start(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_prc_ymd_start(),"")));
			form.setSrch_prc_ymd_end(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_prc_ymd_end(),"")));
			form.setSrch_rtn_ymd_start(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_rtn_ymd_start(),"")));
			form.setSrch_rtn_ymd_end(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_rtn_ymd_end(),"")));
			
			form.setGoExcel(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getGoExcel(),"N")));

			vo.setSrch_apl_m(StringUtil.bvl(vo.getSrch_apl_m(),""));
		    vo.setSrch_apl_cls(StringUtil.bvl(vo.getSrch_apl_cls(),""));
			vo.setSrch_apl_prev30d(StringUtil.bvl(vo.getSrch_apl_prev30d(),""));
			vo.setSrch_apl_seal_no(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_apl_seal_no(),"")));
			vo.setSrch_apl_y(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_apl_y(),"")));
			vo.setSrch_doc_issue_status(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_doc_issue_status(),"")));
			vo.setSrch_doc_no(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_doc_no(),"0")));
			vo.setSrch_sbm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_sbm(),"")));
			vo.setSrch_seal_ffmt_status(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_ffmt_status(),"")));
			vo.setSrch_seal_ffmtday_end(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_ffmtday_end(),"")));
			vo.setSrch_seal_ffmtday_start(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_ffmtday_start(),"")));
			vo.setSrch_seal_ffmtman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_ffmtman_nm(),"")));
			vo.setSrch_seal_knd_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_knd_cd(),"")));
			vo.setSrch_seal_rqst_status(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_rqst_status(),"")));
			vo.setSrch_seal_rqstman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_rqstman_nm(),"")));
			vo.setSrch_title(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_title(),"")));
			
			vo.setSrch_apl_out_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_apl_out_yn(),"")));
			vo.setSrch_prc_seal_no(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_prc_seal_no(),"")));
			vo.setSrch_rtn_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_rtn_yn(),"")));
			vo.setSrch_prc_ymd_start(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_prc_ymd_start(),"")));
			vo.setSrch_prc_ymd_end(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_prc_ymd_end(),"")));
			vo.setSrch_rtn_ymd_start(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_rtn_ymd_start(),"")));
			vo.setSrch_rtn_ymd_end(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_rtn_ymd_end(),"")));
			vo.setSrch_seal_rqstman_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_rqstman_id(),"")));
			
			//날짜 "-" 삭제 처리
			vo.setSrch_seal_ffmtday_start(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_seal_ffmtday_start(),"")),"-"));
			vo.setSrch_prc_ymd_start(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_prc_ymd_start(),"")),"-"));
			vo.setSrch_prc_ymd_end(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_prc_ymd_end(),"")),"-"));
			vo.setSrch_rtn_ymd_start(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_rtn_ymd_start(),"")),"-"));
			vo.setSrch_rtn_ymd_end(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_rtn_ymd_end(),"")),"-"));
			
			vo.setGoExcel(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getGoExcel(),"N")));
						
			// 신청처리 상태가 '승인' 인 것만 표시
			vo.setSrch_seal_rqst_status("AP0103"); 
			
			// 인장 반출 신청이 있는 건만 표시
			vo.setSrch_apl_out_yn("Y");
			vo.setSrch_doc_issuer_id("");		

			
			// 권한처리 : 날인담당자 인지 확인하고 아닐 경우 본인 신청건만 표시 되도록
			if(checkAuthByRole(vo,SEAL)){
				vo.setSrch_seal_ffmtman_id(vo.getSession_user_id());
				vo.setGubn_cd(SEAL);
			}	else {
				// 날인담당자가 아닐 경우
				vo.setSrch_seal_ffmtman_id("");
				
				// 권한세팅 : RM00, RA00, RE01, RE02 인지 확인 후 해당 권한이  아니면 신청 당사자 본인만 보이게 세팅
				boolean auth_sign_process = checkAuthByRole(vo);
				if(!auth_sign_process){
					vo.setAuth_search(true);
					vo.setSrch_seal_rqstman_id(form.getSession_user_id());
				}
			}
			
			PageUtil pageUtil = new PageUtil();

			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());	

			String returnMessage = "";
			
            ArrayList tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.roleList") ;
			
            if(tmpSessionRoleList.contains("RB01") || tmpSessionRoleList.contains("RB02")){
				
				// 전자 담당자일 경우 각 회사를 조회 할 수 있게 처리 됨.
				vo.setsRb("Y");
				
			} else {
				
				vo.setsRb("");
				
			}

			List resultList = signManageService.listSign(vo);

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
				
				BigDecimal total_cnt  = (BigDecimal)lom.get("total_cnt");

				pageUtil.setTotalRow(total_cnt.intValue());
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();

				if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
					returnMessage = (String)request.getAttribute("returnMessage");
				}
			}else {
				if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
					returnMessage = (String)request.getAttribute("returnMessage");
				}
			}

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			form.setForwardFrom("aplout");
			
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			
			// 목록구분코드
			mav.addObject("prc_mode", "aplout");
			
			return mav;

		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 * 엑셀다운로드
	 * 
	 * @param request
	 * @return void
	 * @throws Exception
	 */
	public void excelDown(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{		
			
			HttpSession session = request.getSession(false);

			SignManageForm form = null;
			SignManageVO vo =	null;		

			form = new SignManageForm();
			vo = new SignManageVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//XSS
			form.setSrch_apl_m(StringUtil.bvl(form.getSrch_apl_m(),""));
		    form.setSrch_apl_cls(StringUtil.bvl(form.getSrch_apl_cls(),""));
			form.setSrch_apl_prev30d(StringUtil.bvl(form.getSrch_apl_prev30d(),""));
			form.setSrch_apl_seal_no(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_apl_seal_no(),"")));
			form.setSrch_apl_y(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_apl_y(),"")));
			form.setSrch_doc_issue_status(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_doc_issue_status(),"")));
			form.setSrch_doc_no(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_doc_no(),"0")));
			form.setSrch_sbm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_sbm(),"")));
			form.setSrch_seal_ffmt_status(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_seal_ffmt_status(),"")));
			form.setSrch_seal_ffmtday_end(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_seal_ffmtday_end(),"")));
			form.setSrch_seal_ffmtday_start(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_seal_ffmtday_start(),"")));
			form.setSrch_seal_ffmtman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_seal_ffmtman_nm(),"")));
			form.setSrch_seal_knd_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_seal_knd_cd(),"")));
			form.setSrch_seal_rqst_status(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_seal_rqst_status(),"")));
			form.setSrch_seal_rqstman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_seal_rqstman_nm(),"")));
			form.setSrch_title(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_title(),"")));
			
			form.setSrch_apl_out_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_apl_out_yn(),"")));
			form.setSrch_prc_seal_no(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_prc_seal_no(),"")));
			form.setSrch_rtn_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_rtn_yn(),"")));
			form.setSrch_prc_ymd_start(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_prc_ymd_start(),"")));
			form.setSrch_prc_ymd_end(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_prc_ymd_end(),"")));
			form.setSrch_rtn_ymd_start(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_rtn_ymd_start(),"")));
			form.setSrch_rtn_ymd_end(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_rtn_ymd_end(),"")));

			form.setExcel_method(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getExcel_method(),"")));

			vo.setSrch_apl_m(StringUtil.bvl(vo.getSrch_apl_m(),""));
		    vo.setSrch_apl_cls(StringUtil.bvl(vo.getSrch_apl_cls(),""));
			vo.setSrch_apl_prev30d(StringUtil.bvl(vo.getSrch_apl_prev30d(),""));
			vo.setSrch_apl_seal_no(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_apl_seal_no(),"")));
			vo.setSrch_apl_y(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_apl_y(),"")));
			vo.setSrch_doc_issue_status(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_doc_issue_status(),"")));
			vo.setSrch_doc_no(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_doc_no(),"0")));
			vo.setSrch_sbm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_sbm(),"")));
			vo.setSrch_seal_ffmt_status(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_ffmt_status(),"")));
			vo.setSrch_seal_ffmtday_end(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_ffmtday_end(),"")));
			vo.setSrch_seal_ffmtday_start(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_ffmtday_start(),"")));
			vo.setSrch_seal_ffmtman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_ffmtman_nm(),"")));
			vo.setSrch_seal_knd_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_knd_cd(),"")));
			vo.setSrch_seal_rqst_status(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_rqst_status(),"")));
			vo.setSrch_seal_rqstman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_rqstman_nm(),"")));
			vo.setSrch_title(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_title(),"")));
			
			vo.setSrch_apl_out_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_apl_out_yn(),"")));
			vo.setSrch_prc_seal_no(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_prc_seal_no(),"")));
			vo.setSrch_rtn_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_rtn_yn(),"")));
			vo.setSrch_prc_ymd_start(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_prc_ymd_start(),"")));
			vo.setSrch_prc_ymd_end(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_prc_ymd_end(),"")));
			vo.setSrch_rtn_ymd_start(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_rtn_ymd_start(),"")));
			vo.setSrch_rtn_ymd_end(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_rtn_ymd_end(),"")));
			vo.setSrch_seal_rqstman_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_seal_rqstman_id(),"")));

			vo.setExcel_method(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getExcel_method(),"")));
			
			// 검색조건 세팅
			vo.setGoExcel("Y");
			
			if("listSign".equals(form.getExcel_method())){ // 신청목록 
				vo.setSrch_seal_ffmtman_id("");
				vo.setSrch_doc_issuer_id("");					
				// 권한세팅 : RM00, RA00, RE01, RE02 인지 확인 후 해당 권한이  아니면 신청 당사자 본인만 보이게 세팅
				boolean auth_sign_process = checkAuthByRole(vo);
				if(!auth_sign_process){
					vo.setAuth_search(true);
					vo.setSrch_seal_rqstman_id(form.getSession_user_id());
				}				
			} else if("listSignMng".equals(form.getExcel_method())){ // 처리목록
				
				vo.setSrch_seal_rqst_status("AP0103"); //신청처리 상태가 '승인' 인 것만 표시
				vo.setSrch_seal_ffmtman_id(vo.getSession_user_id());
				vo.setGubn_cd(SEAL);
				vo.setSrch_doc_issuer_id(vo.getSession_user_id());
				vo.setGubn_cd(DOC);	
				
			} else if("listAplOut".equals(form.getExcel_method())){ // 인장 반출 내역
				
				// 신청처리 상태가 '승인' 인 것만 표시
				vo.setSrch_seal_rqst_status("AP0103"); 
				
				// 인장 반출 신청이 있는 건만 표시
				vo.setSrch_apl_out_yn("Y");
				vo.setSrch_doc_issuer_id("");		

				
				// 권한처리 : 날인담당자 인지 확인하고 아닐 경우 본인 신청건만 표시 되도록
				if(checkAuthByRole(vo,SEAL)){
					vo.setSrch_seal_ffmtman_id(vo.getSession_user_id());
					vo.setGubn_cd(SEAL);
				}	else {
					// 날인담당자가 아닐 경우
					vo.setSrch_seal_ffmtman_id("");
					
					// 권한세팅 : RM00, RA00, RE01, RE02 인지 확인 후 해당 권한이  아니면 신청 당사자 본인만 보이게 세팅
					boolean auth_sign_process = checkAuthByRole(vo);
					if(!auth_sign_process){
						vo.setAuth_search(true);
						vo.setSrch_seal_rqstman_id(form.getSession_user_id());
					}
				}
			}
			
            ArrayList tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.roleList") ;
			
            if(tmpSessionRoleList.contains("RB01") || tmpSessionRoleList.contains("RB02")){
				
				// 전자 담당자일 경우 각 회사를 조회 할 수 있게 처리 됨.
				vo.setsRb("Y");
				
			} else {
				
				vo.setsRb("");
				
			}
			
			List resultList = signManageService.listSign(vo);
		
			/*********************************************************
			 * Excel DownLoad
			**********************************************************/
			String		fileNm			= "";
			String[]	sheetNmAry		= new String[1];
			String[]	titleInfo		= new String[1];
			
			String[]	subTitleInfo	= null;
			String[]	columnInfo		= null;
			short[]		columnAlign		= null;			
			
			String reqday = messageSource.getMessage("clm.field.signmng.reqday", null, new RequestContext(request).getLocale()); //메시지 처리 - 신청일자
			String title = messageSource.getMessage("clm.field.signmng.title", null, new RequestContext(request).getLocale()); //메시지 처리 - 건명  
			String towhere = messageSource.getMessage("clm.field.signmng.towhere", null, new RequestContext(request).getLocale()); //메시지 처리 - 제출처 
			String reqman = messageSource.getMessage("clm.field.signmng.reqman", null, new RequestContext(request).getLocale()); //메시지 처리 - 신청자 
			String apltype = messageSource.getMessage("clm.field.signmng.apltype", null, new RequestContext(request).getLocale()); //메시지 처리 - 신청유형
			String apprst = messageSource.getMessage("clm.field.signmng.apprst", null, new RequestContext(request).getLocale()); //메시지 처리 - 결재상태
			String signreqlst = messageSource.getMessage("clm.field.signmng.signreqlst", null, new RequestContext(request).getLocale()); //메시지 처리 - 날인 신청내역
			String aploutreq = messageSource.getMessage("clm.field.signmng.aploutreq", null, new RequestContext(request).getLocale()); //메시지 처리 - 인장반출 신청
			String docreqyn = messageSource.getMessage("clm.field.signmng.docreqyn", null, new RequestContext(request).getLocale()); //메시지 처리 - 증명서류 신청유무
			String aplprcman = messageSource.getMessage("clm.field.signmng.aplprcman", null, new RequestContext(request).getLocale()); //메시지 처리 - 날인담당자
			String prcstat = messageSource.getMessage("clm.field.signmng.prcstat", null, new RequestContext(request).getLocale()); //메시지 처리 - 처리상태
			String signprcday = messageSource.getMessage("clm.field.signmng.signprcday", null, new RequestContext(request).getLocale()); //메시지 처리 - 날인처리일
			String docissuer = messageSource.getMessage("clm.field.signmng.docissuer", null, new RequestContext(request).getLocale()); //메시지 처리 - 증명서류 발급담당자
			String docprcst = messageSource.getMessage("clm.field.signmng.docprcst", null, new RequestContext(request).getLocale()); //메시지 처리 - 증명서류처리상태
			String docissueday = messageSource.getMessage("clm.field.signmng.docissueday", null, new RequestContext(request).getLocale()); //메시지 처리 - 증명발급 처리일
			String prcaplno = messageSource.getMessage("clm.field.signmng.prcaplno", null, new RequestContext(request).getLocale()); //메시지 처리 - 처리인장번호
			String outfrom = messageSource.getMessage("clm.field.signmng.outfromto", null, new RequestContext(request).getLocale()) + "FROM"; //메시지 처리 - 반출기간 FROM
			String outto = messageSource.getMessage("clm.field.signmng.outfromto", null, new RequestContext(request).getLocale()) + "TO"; //메시지 처리 - 반출기간 TO
			String rtnday = messageSource.getMessage("clm.field.signmng.rtnday", null, new RequestContext(request).getLocale()); //메시지 처리 - 반납일
			
			// 날짜로 시퀀스 따기
			String today = DateUtil.getCurrentDay().replace("-","");
			
			if("listSign".equals(form.getExcel_method())){ // 날인 증명서류 신청
				fileNm			= (String)messageSource.getMessage("clm.field.signmng.apltab", null, new RequestContext(request).getLocale()) +today + ".xls";
				sheetNmAry[0]	= today;
				titleInfo[0]	= (String)messageSource.getMessage("clm.field.signmng.apltab", null, new RequestContext(request).getLocale()); // 날인 증명서류 신청
				subTitleInfo	= new String[]{
						 reqday
						,title
						,towhere
						,reqman
						,apltype
						,apprst
						,signreqlst
						,aploutreq
						,docreqyn
						};
				columnInfo		= new String[]{
						  "SEAL_RQSTDAY_TXT"
						, "TITLE"
						, "SBM"
						, "SEAL_RQSTMAN_TXT"
						, "APL_CLS_NM"
						, "SEAL_RQST_STATUS_NM"
						, "SEAL_KND_NM"
						, "APL_OUT_NM"
						, "DOC_YN" 
						};
				columnAlign		= new short[]{
						  ExcelBuilder.ALIGN_CENTER
						, ExcelBuilder.ALIGN_CENTER
						, ExcelBuilder.ALIGN_CENTER
						, ExcelBuilder.ALIGN_CENTER
						, ExcelBuilder.ALIGN_CENTER
						, ExcelBuilder.ALIGN_CENTER
						, ExcelBuilder.ALIGN_CENTER
						, ExcelBuilder.ALIGN_CENTER
						, ExcelBuilder.ALIGN_CENTER
						};
		
			} else if("listSignMng".equals(form.getExcel_method())){ // 날인 증명서류 처리
				
				fileNm			= (String)messageSource.getMessage("clm.field.signmng.prctab", null, new RequestContext(request).getLocale()) +today + ".xls";
				sheetNmAry[0]	= today;
				titleInfo[0]	= (String)messageSource.getMessage("clm.field.signmng.apltab", null, new RequestContext(request).getLocale()); // 날인 증명서류 처리
				subTitleInfo	= new String[]{
						 reqday
						,title
						,towhere
						,reqman
						,apltype
						,signreqlst
						,aploutreq
						,docreqyn
						,aplprcman // 날인처리자
						,prcstat //날인처리상태
						,signprcday // 날인처리일
						,docissuer // 증명서류 발급처리자
						,prcstat// 증명서류 발급처리상태
						,docissueday // 증명서류 발급처리일
						};
				columnInfo		= new String[]{
						  "SEAL_RQSTDAY_TXT"
						, "TITLE"
						, "SBM"
						, "SEAL_RQSTMAN_TXT"
						, "APL_CLS_NM"
						, "SEAL_KND_NM"
						, "APL_OUT_NM"
						, "DOC_YN" 
						, "SEAL_FFMTMAN_TXT" // 날인처리자
						, "SEAL_FFMT_STATUS_NM" //날인처리상태
						, "SEAL_FFMTDAY_TXT" // 날인처리일
						, "DOC_ISSUER_TXT" // 증명서류 발급처리자
						, "DOC_ISSUE_STATUS_NM" // 증명서류 발급처리상태
						, "DOC_ISSUEDAY_TXT" // 증명서류 발급처리일
						};
				columnAlign		= new short[]{
						  ExcelBuilder.ALIGN_CENTER
						, ExcelBuilder.ALIGN_CENTER
						, ExcelBuilder.ALIGN_CENTER
						, ExcelBuilder.ALIGN_CENTER
						, ExcelBuilder.ALIGN_CENTER
						, ExcelBuilder.ALIGN_CENTER
						, ExcelBuilder.ALIGN_CENTER
						, ExcelBuilder.ALIGN_CENTER
						, ExcelBuilder.ALIGN_CENTER
						, ExcelBuilder.ALIGN_CENTER
						, ExcelBuilder.ALIGN_CENTER
						, ExcelBuilder.ALIGN_CENTER
						, ExcelBuilder.ALIGN_CENTER
						, ExcelBuilder.ALIGN_CENTER						
						};
				
			} else if("listAplOut".equals(form.getExcel_method())){ // 인장반출 내역
				
				fileNm			= (String)messageSource.getMessage("clm.field.signmng.aplouttab", null, new RequestContext(request).getLocale()) +today + ".xls";
				sheetNmAry[0]	= today;
				titleInfo[0]	= (String)messageSource.getMessage("clm.field.signmng.apltab", null, new RequestContext(request).getLocale()); // 인장반출 내역
				subTitleInfo	= new String[]{
						  reqday
						, reqman
						, aplprcman // 날인담당자
						, prcaplno // 처리인장번호
						, outfrom// 반출일 FROM
						, outto// 반출일 TO
						, rtnday// 반납일
						, prcstat//  처리상태
						};
				columnInfo		= new String[]{
						  "SEAL_RQSTDAY_TXT"
						, "SEAL_RQSTMAN_TXT"
						, "SEAL_FFMTMAN_TXT" // 날인담당자
						, "PRC_SEAL_NO" // 처리인장번호
						, "PRC_YMD_FROM_TXT"// 반출일 FROM
						, "PRC_YMD_TO_TXT"// 반출일 TO
						, "RTN_YMD_TXT"// 반납일
						, "SEAL_FFMT_STATUS_NM"//  처리상태 
						};
				columnAlign		= new short[]{
						  ExcelBuilder.ALIGN_CENTER
						, ExcelBuilder.ALIGN_CENTER
						, ExcelBuilder.ALIGN_CENTER
						, ExcelBuilder.ALIGN_CENTER
						, ExcelBuilder.ALIGN_CENTER
						, ExcelBuilder.ALIGN_CENTER
						, ExcelBuilder.ALIGN_CENTER
						, ExcelBuilder.ALIGN_CENTER
						};
			}		
	        
	        ExcelBuilder excel = new ExcelBuilder(sheetNmAry);

			int sheetsCount = excel.getSheetsCount();                                // Sheet의 갯수			

			for(int i=0;i<sheetsCount;i++) {                                         		// Sheet의 수 만큼 Loop				
				/*
				excel.setFontName	("맑은 고딕");                                         		// Row의 모든 Cell의 Font명을 굴림체로 설정한다.
				excel.setFontSize	((short)11); 
				if (i == 0) {
					excel.addTitleRow(i, titleInfo);                                 		// 타이틀을 설정한다.
				}	
				excel.setAlign		(ExcelBuilder.ALIGN_CENTER);                           	// Row의 모든 Cell을 Center 정렬한다.
				excel.setBold		(true);                                                 // Row의 모든 Cell을 Bold로 설정한다.
				excel.setFontColor	(ExcelBuilder.COLOR_BLACK);                        		// Row의 모든 Cell의 글자색을 White로 설정한다.				
				excel.setBgColor	(ExcelBuilder.COLOR_YELLOW);                     	// Row의 모든 Cell의 배경색을 부드러운 Blue로 설정한다.
				excel.setBorder		(true); 
				// Row의 모든 Cell의 테두리를 설정한다.
				excel.addRow(i, subTitleInfo);	
				excel.setBold		(false);  
				excel.setBgColor	(ExcelBuilder.COLOR_WHITE);                     	// Row의 모든 Cell의 배경색을 부드러운 Blue로 설정한다.                                            		
				if (i == 0) {
					excel.setAlign(columnAlign);                                     		// 셀 정렬
				}
				excel.setVAlign(ExcelBuilder.VALIGN_CENTER);                         		// Row의 모든 Cell을 Vertical Center 정렬한다.
				excel.setBorder(new boolean[] {true});                               		// Row의 모든 Cell의 테두리를 설정한다. (cause. 배열의 크기가 1)				
				if (i == 0) {
					excel.addRows(i, columnInfo, resultList);                        		// 데이타 엑셀에 박기										
				}
				excel.setDefaultStyle();                                             		// Row의 모든 Style을 기본값으로 복원한다.
				*/
				
				excel.setBold(true);
				excel.setFontColor(ExcelBuilder.COLOR_BLACK);
				excel.setFontName("맑은 고딕");
				excel.setFontSize((short)11);
				excel.setBgColor(ExcelBuilder.COLOR_YELLOW);
				excel.setBorder(true);
				
				excel.addTitleRow(i, subTitleInfo);
				excel.setDefaultStyle();
				
				excel.setAlign(columnAlign);
				excel.setVAlign(ExcelBuilder.VALIGN_CENTER);
				excel.setBorder(new boolean[]{true});
				
				excel.addRows(i, columnInfo, resultList);
				
				excel.setDefaultStyle();
			
			}			
			
			excel.download(fileNm, response);			
			
		}catch (Exception e) {
			e.printStackTrace();
		}catch (Throwable t) {
			t.printStackTrace();
		}	
	}
	
	/**
	 * 날인 신청 등록화면 호출
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardSignInsert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			HttpSession session = request.getSession(false);
			
			String forwardURL = "/WEB-INF/jsp/clm/sign/Sign_i.jsp";

			SignManageForm form = null;
			form = new SignManageForm();
			SignManageVO vo = null;
			vo = new SignManageVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			form.setReg_dt(DateUtil.getCurrentDay());
			form.setReg_nm(vo.getSession_user_nm());

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			
			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 * 날인 관리 등록
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView saveSign(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {			
			
			HttpSession session = request.getSession(false);
			
			SignManageForm form = new SignManageForm();
			SignManageVO vo = new SignManageVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//Cross-site Scripting 방지
			form.setApl_sqn(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getApl_sqn(),"")));			
			form.setTitle(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getTitle(),"")));			
			form.setSbm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSbm(),"")));			
			form.setReg_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSession_user_nm(),"")));
			form.setReg_dt(DateUtil.getCurrentDay());
			form.setSeal_knd_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSeal_knd_cd(),"")));			
			form.setTxt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getTxt(),"")));			
			form.setSeal_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSeal_yn(),"N")));			
			form.setApl_cls(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getApl_cls(),"")));		
			form.setApl_out_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getApl_out_yn(),"N")));			
			form.setApl_seal_no(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getApl_seal_no(),"")));			
			form.setApl_ymd_from(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getApl_ymd_from(),"")));
			form.setApl_ymd_to(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getApl_ymd_to(),"")));			
			form.setDoc_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getDoc_yn(),"N")));			
			form.setDoc1(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getDoc1(),"")));
			form.setDoc2(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getDoc2(),"")));		
			form.setDoc3(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getDoc3(),"")));		
			form.setDoc4(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getDoc4(),"")));		
			form.setDoc5(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getDoc5(),"")));		
			form.setDoc6(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getDoc6(),"")));			
			form.setUse_summ(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getUse_summ(),"")));			
			form.setSeal_ffmtman_id(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSeal_ffmtman_id(),"")));
			form.setSeal_ffmtman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSeal_ffmtman_nm(),"")));		
			form.setSeal_rqst_dept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSeal_rqst_dept_nm(),"")));		
			form.setSeal_ffmtman_jikgup_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSeal_ffmtman_jikgup_nm(),"")));			
			form.setDoc_issuer_id(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getDoc_issuer_id(),"")));
			form.setDoc_issuer_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getDoc_issuer_nm(),"")));		
			form.setDoc_issuer_dept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getDoc_issuer_dept_nm(),"")));		
			form.setDoc_issuer_jikgup_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getDoc_issuer_jikgup_nm(),"")));		
			form.setDoc_scrtxt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getDoc_scrtxt(),"")));		
			
			vo.setApl_sqn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getApl_sqn(),"")));			
			vo.setTitle(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getTitle(),"")));			
			vo.setSbm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSbm(),"")));			
			vo.setReg_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_user_nm(),"")));
			vo.setReg_dt(DateUtil.getCurrentDay());
			vo.setSeal_knd_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSeal_knd_cd(),"")));			
			vo.setTxt(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getTxt(),"")));			
			vo.setSeal_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSeal_yn(),"N")));			
			vo.setApl_cls(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getApl_cls(),"")));		
			vo.setApl_out_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getApl_out_yn(),"N")));			
			vo.setApl_seal_no(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getApl_seal_no(),"")));			
			vo.setApl_ymd_from(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getApl_ymd_from(),"")));
			vo.setApl_ymd_to(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getApl_ymd_to(),"")));			
			vo.setDoc_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDoc_yn(),"N")));			
			vo.setDoc1(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDoc1(),"")));
			vo.setDoc2(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDoc2(),"")));		
			vo.setDoc3(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDoc3(),"")));		
			vo.setDoc4(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDoc4(),"")));		
			vo.setDoc5(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDoc5(),"")));		
			vo.setDoc6(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDoc6(),"")));			
			vo.setUse_summ(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getUse_summ(),"")));			
			vo.setSeal_ffmtman_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSeal_ffmtman_id(),"")));
			vo.setSeal_ffmtman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSeal_ffmtman_nm(),"")));		
			vo.setSeal_rqst_dept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSeal_rqst_dept_nm(),"")));		
			vo.setSeal_ffmtman_jikgup_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSeal_ffmtman_jikgup_nm(),"")));			
			vo.setDoc_issuer_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDoc_issuer_id(),"")));
			vo.setDoc_issuer_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDoc_issuer_nm(),"")));		
			vo.setDoc_issuer_dept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDoc_issuer_dept_nm(),"")));		
			vo.setDoc_issuer_jikgup_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDoc_issuer_jikgup_nm(),"")));
			vo.setDoc_scrtxt(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDoc_scrtxt(),"")));		
			
			// 신청자 세팅
			vo.setComp_cd(vo.getSession_comp_cd());
			vo.setComp_nm(vo.getSession_comp_nm());
			vo.setSeal_rqstman_id(vo.getSession_user_id());
			vo.setSeal_rqstman_nm(vo.getSession_user_nm());
			vo.setSeal_rqst_dept_nm(vo.getSession_dept_nm());
			vo.setSeal_rqstman_jikgup_nm(vo.getSession_jikgup_nm());	
			
			String returnMessage = "";
			String makeHtml = "";
			String appr_title = StringUtil.convertHtmlTochars(StringUtil.bvl(form.getTitle(),""));
			
			// 상신 버튼을 눌었을 경우의 메세지 처리
			if("Y".equals(form.getGo_pumui())){
				returnMessage = "";
			} else {
				returnMessage = messageSource.getMessage("secfw.msg.succ.save", null, new RequestContext(request).getLocale());
			}
			
			String apl_sqn = "";
			
			ModelAndView mav = new ModelAndView();
			
            
			
			// 시퀀스가 없는 경우 등록
			if("".equals(form.getApl_sqn())){
				apl_sqn = signManageService.insertSign(vo);
				
				form.setApl_sqn(apl_sqn);
				request.setAttribute("temp_apl_sqn", apl_sqn);	
				
				String app_cont = makeAppHtml(request, response);
	            
				form.setApp_contents(app_cont);
				form.setApp_title(appr_title);
				
				form.setForwardFrom("listSign");				
				mav = detailSign(request,response);		
				mav.addObject("command", form);
				mav.addObject("returnMessage", returnMessage);
			} else {
				
				boolean auth_modify = signManageService.authModify(MODIFY, vo);
				
				// 권한이 없을 경우 
				if(!auth_modify){
					mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
					mav.addObject("secfw.alert.title", messageSource.getMessage("secfw.page.field.alert.noModifyAuth", null, new RequestContext(request).getLocale()));
					mav.addObject("secfw.alert.message", messageSource.getMessage("secfw.page.field.alert.noLinkMessage", null, new RequestContext(request).getLocale()));
					return mav;
				}				
				
				signManageService.modifySign(vo);
				
				String app_cont = makeAppHtml(request, response);
				
				
	            
				form.setApp_contents(app_cont);
				form.setApp_title(appr_title);
				
				form.setForwardFrom("listSign");				
				mav = detailSign(request,response);			
				mav.addObject("command", form);
				mav.addObject("returnMessage", returnMessage);
			}
			
            
			
			return mav;

		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 * 날인 상세
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailSign(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			HttpSession session = request.getSession(false);
			
			String forwardURL = "/WEB-INF/jsp/clm/sign/Sign_d.jsp";
			SignManageForm form = null;
			SignManageVO vo = null;			
			List resultList = null;
			String makeHtml = "";

			form = new SignManageForm();
			vo = new SignManageVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			String temp_apl_sqn = StringUtil.bvl((String)request.getAttribute("temp_apl_sqn"),"");
			
			if(!temp_apl_sqn.equals(""))
				vo.setApl_sqn(temp_apl_sqn);
			
			// 상세정보취득
			resultList = signManageService.detailSign(vo);
			ListOrderedMap lom = null;
			
			// 첨부파일 정보 취득
			List attachList = signManageService.getAttachList(vo);
			
			String appr_status = "";
			String ref_key = "";
			String cntrt_id = "";
			String cnsdreq_id = "";
			String txt = "";
			String txt2 = "";
			String appr_title = "";
			List listSignAppr = null;

			if(resultList!=null){				
				lom = (ListOrderedMap)resultList.get(0);				
				appr_status = (String)lom.get("SEAL_RQST_STATUS");
				cntrt_id = StringUtil.bvl((String)lom.get("CNTRT_ID"),"");
				cnsdreq_id = StringUtil.bvl((String)lom.get("CNSDREQ_ID"),"");
				txt = StringUtil.convertEnterToBR((String)lom.get("TXT"));
				txt2 = (String)lom.get("TXT");
				appr_title = (String)lom.get("TITLE");
				
				form.setTxt(txt);
								
				// 의뢰 아이디가 존재 하는 경우 체결에서 넘어온 날인 신청건으로 간주하고, 존재 하지 않는 경우 날인 신청화면에서 신청한 날인 신청으로 판단한다.
				if("".equals(cnsdreq_id)){
					ref_key = vo.getApl_sqn();					
				} else {
					ref_key = cnsdreq_id;
				}
				
				// 반려나 승인이 된 신청이 경우 승인이력 정보 취득
				if(appr_status.equals("AP0103") || appr_status.equals("AP0104")){
					vo.setRef_key(ref_key);
					vo.setCnsdreq_id(cnsdreq_id);
					listSignAppr = signManageService.listSignAppr(vo);					
				}
			}			
			
			// 첨부파일 참조를 위한 키 설정
			form.setCnsdreq_id(cnsdreq_id);
			form.setRef_key(cntrt_id + "@" + cnsdreq_id);
			
			// 버튼 권한 처리
			vo.setAuth_str_id(vo.getApl_sqn());
			vo.setSession_user_role_cds(vo.getSession_user_role_cds());
			vo.setSession_user_id(vo.getSession_user_id());
			// 수정/삭제 권한 
			boolean auth_modify = signManageService.authModify(MODIFY, vo);
			// 날인처리/ 반납처리 권한
			boolean auth_sign_process = signManageService.authProcess(SEAL, vo);	
			// 증명서류 처리권한
			boolean auth_doc_process = signManageService.authProcess(DOC, vo);
			
			ModelAndView mav = new ModelAndView();
			
			String app_cont = makeAppHtml(request, response);
			
			form.setApp_contents(app_cont);
			form.setApp_title(appr_title);
			
			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("auth_modify", auth_modify);
			mav.addObject("auth_sign_process", auth_sign_process);
			mav.addObject("auth_doc_process", auth_doc_process);
			
 			mav.addObject("listSignAppr", listSignAppr); // 승인이력
 			mav.addObject("attachList", attachList); // 첨부파일 정보 
			mav.addObject("lom", lom);
			
			
			
			return mav;

		}catch (Exception e) {
			e.printStackTrace();   
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}	

	/**
	 * 날인 수정화면 호출
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardSignModify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);
			
			String forwardURL = "/WEB-INF/jsp/clm/sign/Sign_i.jsp";;

			SignManageForm form = null;
			form = new SignManageForm();
			SignManageVO vo = null;
			vo = new SignManageVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			List resultList = signManageService.detailSign(vo);
			ListOrderedMap lom = null;

			if(resultList!=null){				
				lom = (ListOrderedMap)resultList.get(0);
			} 

			form.setReg_dt(DateUtil.getCurrentDay());
			form.setReg_nm(vo.getSession_user_nm());

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("lom", lom);
			
			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 * 날인 담당자 수정
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView modifySignMan(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);
			
			SignManageForm form = null;
			form = new SignManageForm();
			SignManageVO vo = null;
			vo = new SignManageVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
						
			//Cross-site Scripting 방지
			form.setApl_sqn(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getApl_sqn(),"")));	
			form.setComp_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getComp_cd(),"")));	
			form.setComp_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getComp_nm(),"")));			
			form.setGubn_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getGubn_cd(),"")));		
			form.setApl_ym(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getApl_ym(),"")));		
			
			form.setSeal_ffmtman_id(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSeal_ffmtman_id(),"")));
			form.setSeal_ffmtman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSeal_ffmtman_nm(),"")));		
			form.setSeal_rqst_dept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSeal_rqst_dept_nm(),"")));		
			form.setSeal_ffmtman_jikgup_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSeal_ffmtman_jikgup_nm(),"")));			
			form.setDoc_issuer_id(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getDoc_issuer_id(),"")));
			form.setDoc_issuer_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getDoc_issuer_nm(),"")));		
			form.setDoc_issuer_dept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getDoc_issuer_dept_nm(),"")));		
			form.setDoc_issuer_jikgup_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getDoc_issuer_jikgup_nm(),"")));	
			
			vo.setApl_sqn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getApl_sqn(),"")));	
			vo.setComp_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getComp_cd(),"")));	
			vo.setComp_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getComp_nm(),"")));			
			vo.setGubn_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getGubn_cd(),"")));		
			vo.setApl_ym(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getApl_ym(),"")));		
			
			vo.setSeal_ffmtman_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSeal_ffmtman_id(),"")));
			vo.setSeal_ffmtman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSeal_ffmtman_nm(),"")));		
			vo.setSeal_rqst_dept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSeal_rqst_dept_nm(),"")));		
			vo.setSeal_ffmtman_jikgup_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSeal_ffmtman_jikgup_nm(),"")));			
			vo.setDoc_issuer_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDoc_issuer_id(),"")));
			vo.setDoc_issuer_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDoc_issuer_nm(),"")));		
			vo.setDoc_issuer_dept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDoc_issuer_dept_nm(),"")));		
			vo.setDoc_issuer_jikgup_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDoc_issuer_jikgup_nm(),"")));		
			
			ModelAndView mav = new ModelAndView();
			
			//권한 체크
			boolean auth_process = false;			
			if(SEAL.equals(vo.getGubn_cd())){				
				auth_process = signManageService.authProcess(SEAL, vo);			
			} else if("SC0102".equals(vo.getGubn_cd())){				
				auth_process = signManageService.authProcess(DOC, vo);				
			}
			
			// 권한이 없을 경우 
			if(!auth_process){
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
				mav.addObject("secfw.alert.title", messageSource.getMessage("secfw.page.field.alert.noModifyAuth", null, new RequestContext(request).getLocale()));
				mav.addObject("secfw.alert.message", messageSource.getMessage("secfw.page.field.alert.noLinkMessage", null, new RequestContext(request).getLocale()));
				return mav;
			}

			int result = signManageService.modifySignMan(vo);
			String returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());			

			mav = listSignMng(request,response);			
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			return mav;

		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}	
	
	/**
	 * 날인 처리
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView signAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);
			
			SignManageForm form = null;
			form = new SignManageForm();
			SignManageVO vo = null;
			vo = new SignManageVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
						
			//Cross-site Scripting 방지
			form.setApl_sqn(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getApl_sqn(),"")));	
			form.setComp_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getComp_cd(),"")));	
			form.setComp_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getComp_nm(),"")));			
			form.setGubn_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getGubn_cd(),"")));		
			form.setApl_ym(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getApl_ym(),"")));		
			
			form.setPrc_seal_no(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getPrc_seal_no(),"")));
			form.setPrc_ymd_from(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getPrc_ymd_from(),"")));		
			form.setPrc_ymd_to(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getPrc_ymd_to(),"")));			
			form.setRtn_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getRtn_yn(),"")));		
			form.setRtn_ymd(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getRtn_ymd(),"")));			
			
			form.setSeal_ffmtman_id(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSeal_ffmtman_id(),"")));
			form.setSeal_ffmtman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSeal_ffmtman_nm(),"")));		
			form.setSeal_ffmt_dept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSeal_ffmt_dept_nm(),"")));		
			form.setSeal_ffmtman_jikgup_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSeal_ffmtman_jikgup_nm(),"")));			
			form.setDoc_issuer_id(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getDoc_issuer_id(),"")));
			form.setDoc_issuer_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getDoc_issuer_nm(),"")));		
			form.setDoc_issuer_dept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getDoc_issuer_dept_nm(),"")));		
			form.setDoc_issuer_jikgup_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getDoc_issuer_jikgup_nm(),"")));				
			form.setSeal_rtnman_id(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSeal_rtnman_id(),"")));
			form.setSeal_rtnman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSeal_rtnman_nm(),"")));		
			form.setSeal_rtnman_dept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSeal_rtnman_dept_nm(),"")));		
			form.setSeal_rtnman_jikgup_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSeal_rtnman_jikgup_nm(),"")));		
			form.setRtn_seal_no(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getRtn_seal_no(),"")));
			
			vo.setApl_sqn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getApl_sqn(),"")));	
			vo.setComp_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getComp_cd(),"")));	
			vo.setComp_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getComp_nm(),"")));			
			vo.setGubn_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getGubn_cd(),"")));		
			vo.setApl_ym(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getApl_ym(),"")));		
			
			vo.setPrc_seal_no(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getPrc_seal_no(),"")));
			vo.setPrc_ymd_from(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getPrc_ymd_from(),"")));		
			vo.setPrc_ymd_to(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getPrc_ymd_to(),"")));			
			vo.setRtn_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getRtn_yn(),"")));		
			vo.setRtn_ymd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getRtn_ymd(),"")));	
			
			vo.setSeal_ffmtman_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSeal_ffmtman_id(),"")));
			vo.setSeal_ffmtman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSeal_ffmtman_nm(),"")));		
			vo.setSeal_ffmt_dept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSeal_ffmt_dept_nm(),"")));		
			vo.setSeal_ffmtman_jikgup_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSeal_ffmtman_jikgup_nm(),"")));			
			vo.setDoc_issuer_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDoc_issuer_id(),"")));
			vo.setDoc_issuer_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDoc_issuer_nm(),"")));		
			vo.setDoc_issuer_dept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDoc_issuer_dept_nm(),"")));		
			vo.setDoc_issuer_jikgup_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDoc_issuer_jikgup_nm(),"")));	
			vo.setSeal_rtnman_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSeal_rtnman_id(),"")));
			vo.setSeal_rtnman_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSeal_rtnman_nm(),"")));		
			vo.setSeal_rtnman_dept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSeal_rtnman_dept_nm(),"")));		
			vo.setSeal_rtnman_jikgup_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSeal_rtnman_jikgup_nm(),"")));		
			vo.setRtn_seal_no(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getRtn_seal_no(),"")));
			
			ModelAndView mav = new ModelAndView();
			
			//권한 체크
			boolean auth_process = false;			
			vo.setGubn_cd(SEAL);
			auth_process = signManageService.authProcess(SEAL, vo);	
			
			// 권한이 없을 경우 
			if(!auth_process){
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
				mav.addObject("secfw.alert.title", messageSource.getMessage("secfw.page.field.alert.noModifyAuth", null, new RequestContext(request).getLocale()));
				mav.addObject("secfw.alert.message", messageSource.getMessage("secfw.page.field.alert.noLinkMessage", null, new RequestContext(request).getLocale()));
				return mav;
			}		
			
			if("N".equals(vo.getApl_out_yn())){ // 인장 반출 없는 날인처리인 경우 

				vo.setSeal_ffmt_status("SL0103"); // 바로 처리완료 
			
			} else if("Y".equals(vo.getApl_out_yn()) && "".equals(vo.getRtn_ymd())){ // 반출신청 O 반납일 X 인 경우
				
				vo.setSeal_ffmt_status("SL0102"); //  반출중 처리
			
			} else if("Y".equals(vo.getApl_out_yn()) && !"".equals(vo.getRtn_ymd())){ // 반출신청 O 반납일 O 인 경우 반납처리 / 처리완료
				
				vo.setSeal_ffmt_status("SL0103");  // 처리완료 
				vo.setRtn_yn("Y"); // 반납처리			
			}
			
			signManageService.signAction("clm.sign.prc.sign",vo);
			
			String returnMessage = messageSource.getMessage("clm.page.msg.manage.announce156", null, new RequestContext(request).getLocale());	//  정상처리 되었습니다.		

			mav = detailSign(request,response);		
			form.setForwardFrom("listSignMng");		
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			return mav;

		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}	
	
	/**
	 * 증명서류발급 처리
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView docAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);
			
			SignManageForm form = null;
			form = new SignManageForm();
			SignManageVO vo = null;
			vo = new SignManageVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
						
			//Cross-site Scripting 방지
			form.setApl_sqn(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getApl_sqn(),"")));	
			form.setComp_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getComp_cd(),"")));	
			form.setComp_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getComp_nm(),"")));			
			form.setGubn_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getGubn_cd(),"")));		
			form.setApl_ym(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getApl_ym(),"")));		
			
			form.setDoc_issuer_id(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getDoc_issuer_id(),"")));
			form.setDoc_issuer_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getDoc_issuer_nm(),"")));		
			form.setDoc_issuer_dept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getDoc_issuer_dept_nm(),"")));		
			form.setDoc_issuer_jikgup_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getDoc_issuer_jikgup_nm(),"")));				
			
			vo.setApl_sqn(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getApl_sqn(),"")));	
			vo.setComp_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getComp_cd(),"")));	
			vo.setComp_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getComp_nm(),"")));			
			vo.setGubn_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getGubn_cd(),"")));		
			vo.setApl_ym(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getApl_ym(),"")));		
	
			vo.setDoc_issuer_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDoc_issuer_id(),"")));
			vo.setDoc_issuer_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDoc_issuer_nm(),"")));		
			vo.setDoc_issuer_dept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDoc_issuer_dept_nm(),"")));		
			vo.setDoc_issuer_jikgup_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDoc_issuer_jikgup_nm(),"")));	
			
			ModelAndView mav = new ModelAndView();
			
			//권한 체크
			boolean auth_process = false;			
			vo.setGubn_cd(DOC);
			auth_process = signManageService.authProcess(DOC, vo);	
			
			// 권한이 없을 경우 
			if(!auth_process){
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
				mav.addObject("secfw.alert.title", messageSource.getMessage("secfw.page.field.alert.noModifyAuth", null, new RequestContext(request).getLocale()));
				mav.addObject("secfw.alert.message", messageSource.getMessage("secfw.page.field.alert.noLinkMessage", null, new RequestContext(request).getLocale()));
				return mav;
			}		
			
			vo.setDoc_issue_status("DC0102"); // 증명서류 발급처리 완료
			signManageService.signAction("clm.sign.prc.doc",vo);
			
			String returnMessage = messageSource.getMessage("clm.page.msg.manage.announce156", null, new RequestContext(request).getLocale());			

			mav = detailSign(request,response);			
			form.setForwardFrom("listSignMng");		
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			return mav;

		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}		


	/**
	 * 날인 삭제
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView deleteSign(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);

			SignManageForm form = new SignManageForm();
			SignManageVO vo = new SignManageVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			boolean auth_modify = signManageService.authModify(DELETE, vo);
			
			ModelAndView mav = new ModelAndView();
			
			// 권한이 없을 경우 
			if(!auth_modify){
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
				mav.addObject("secfw.alert.title", messageSource.getMessage("secfw.page.field.alert.noDeleteAuth", null, new RequestContext(request).getLocale()));
				mav.addObject("secfw.alert.message", messageSource.getMessage("secfw.page.field.alert.noLinkMessage", null, new RequestContext(request).getLocale()));
				return mav;
			}	

			int result = signManageService.deleteSign(vo);
			String returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale());

			form.setCurPage("1");
			
			mav = listSign(request,response);			
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			
			return mav;
		
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 * 상신 후 상태변경
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView modifySignStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			HttpSession session = request.getSession(false);
			
			String forwardURL = "/clm/sign/signManage.do?method=listSign";

			SignManageForm form = new SignManageForm();
			SignManageVO vo = new SignManageVO();
			
			bind(request, form);
			bind(request, vo);
			
/*			String f_apl_sqn = (String)request.getAttribute("apl_sqn"); //최초 의뢰시 임시저장없이 바로 신청품의를 하는 경우에 처리하기위해
			if(f_apl_sqn !=null && !f_apl_sqn.equals(""))	vo.setApl_sqn(f_apl_sqn);*/
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			boolean auth_modify = signManageService.authModify(MODIFY, vo);
			
			ModelAndView mav = new ModelAndView();
			
			// 권한이 없을 경우 
			if(!auth_modify){
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
				mav.addObject("secfw.alert.title", messageSource.getMessage("secfw.page.field.alert.noDeleteAuth", null, new RequestContext(request).getLocale()));
				mav.addObject("secfw.alert.message", messageSource.getMessage("secfw.page.field.alert.noLinkMessage", null, new RequestContext(request).getLocale()));
				return mav;
			}	

			vo.setSeal_rqst_status("AP0102"); // 결재중

			int result = signManageService.modifySignStatus(vo);

			String returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());
			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 * 날인신청 승인이력 정보를 조회한다.
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	private List listSignAppr(String ref_key) throws Exception {
		try {			
			
			List listSignAppr = null;
						
			StartProcessWSVO swo = null;
			String argMisId = "";
			
			swo  = esbApprovalService.getStartProcessWSVO("LAS", argMisId);

			return listSignAppr;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 * 사용자 ROLE에 따른 권한 제어
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	private boolean checkAuthByRole(SignManageVO vo,String gubn_cd) throws Exception{

		boolean hasRight = false;

        ArrayList roleList = vo.getSession_user_role_cds();
		ArrayList userRoleList = new ArrayList();
		
		if(roleList != null && roleList.size() > 0){
		    for(int idx = 0; idx < roleList.size(); idx++){
		        HashMap roleListMap = (HashMap)roleList.get(idx);
		        userRoleList.add((String)roleListMap.get("role_cd"));
		    }
		}
		
		if(userRoleList != null && userRoleList.size() > 0){
			// 어드민 인 경우 화면 조회 및 상세 조회 권한 처리
			if(userRoleList.contains("RA00") && "ADMIN".equals(gubn_cd)){
				hasRight = true;
			} 
			if(userRoleList.contains("RM00") && "ADMIN".equals(gubn_cd)){
				hasRight = true;
			} 
			if(userRoleList.contains("RE01") && SEAL.equals(gubn_cd)){
				hasRight = true;
			} 
			if(userRoleList.contains("RE02") && DOC.equals(gubn_cd)){
				hasRight = true;
			} 
		}

		return hasRight;
	}
	
	/**
	 * 사용자 ROLE에 따른 권한 제어
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	private boolean checkAuthByRole(SignManageVO vo) throws Exception{

		boolean hasRight = false;

        ArrayList roleList = vo.getSession_user_role_cds();
		ArrayList userRoleList = new ArrayList();
		
		if(roleList != null && roleList.size() > 0){
		    for(int idx = 0; idx < roleList.size(); idx++){
		        HashMap roleListMap = (HashMap)roleList.get(idx);
		        userRoleList.add((String)roleListMap.get("role_cd"));
		    }
		}
		
		if(userRoleList != null && userRoleList.size() > 0){
			// 어드민 인 경우 화면 조회 및 상세 조회 권한 처리
			if(userRoleList.contains("RA00")){
				hasRight = true;
			} 
			if(userRoleList.contains("RM00")){
				hasRight = true;
			} 
			if(userRoleList.contains("RE01")){
				hasRight = true;
			} 
			if(userRoleList.contains("RE02")){
				hasRight = true;
			} 
		}

		return hasRight;
	}
	
	/**
	 * 결재 화면 내용 만들기
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	
	public String makeAppHtml(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav= new ModelAndView();
		
		//화면에서 넘어온 값 정리
		String forwardURL = "/WEB-INF/jsp/clm/sign/Sign_i.jsp";;

		SignManageForm form = null;
		form = new SignManageForm();
		SignManageVO vo = null;
		vo = new SignManageVO();
		
		bind(request, form);
		bind(request, vo);
		
		COMUtil.getUserAuditInfo(request, form);
		COMUtil.getUserAuditInfo(request, vo);
		
		String temp_apl_sqn = StringUtil.bvl((String)request.getAttribute("temp_apl_sqn"),"");
		
		if(!temp_apl_sqn.equals(""))
			vo.setApl_sqn(temp_apl_sqn);
		
		//결재창에 표시될 부분의 스트링 값.		
		String strUrl = (request.getRequestURL()).toString();
		
		String url  = strUrl.substring(0, strUrl.indexOf("/", 7) + 1);
		String attach_url  = url + "/clms/common/clmsfile.do?method=doClmsDownload&file_id=";	
		
		String title_1 = messageSource.getMessage("clm.field.signmng.apltab", null, new RequestContext(request).getLocale()); // 날인/증명서류 발급 신청		                
		String title_2 = messageSource.getMessage("clm.field.signmng.apprmsg9", null, new RequestContext(request).getLocale()); // 날인/증명서류 발급 신청을 상신하오니 재가하여 주시기 바랍니다.
		
		String lavel_1 = messageSource.getMessage("clm.field.signmng.basicinfo", null, new RequestContext(request).getLocale()); // 기본내역
		String lavel_2 = messageSource.getMessage("clm.field.signmng.title", null, new RequestContext(request).getLocale()); // 건명
		String lavel_3 = messageSource.getMessage("clm.field.signmng.towhere", null, new RequestContext(request).getLocale()); // 제출처
		String lavel_4 = messageSource.getMessage("clm.field.signmng.reqman", null, new RequestContext(request).getLocale()); // 신청자
		String lavel_5 = messageSource.getMessage("clm.field.signmng.reqday", null, new RequestContext(request).getLocale()); // 신청일자
		String lavel_6 = messageSource.getMessage("clm.field.signmng.apltype", null, new RequestContext(request).getLocale()); // 신청유형
		String lavel_7 = messageSource.getMessage("clm.field.signmng.detaildsc", null, new RequestContext(request).getLocale()); // 세부내용 // clm.field.signmng.detaildsc
		String lavel_8 = messageSource.getMessage("clm.page.field.srch.attach", null, new RequestContext(request).getLocale()); // 첨부
		String lavel_9 = messageSource.getMessage("clm.field.signmng.aplinfo", null, new RequestContext(request).getLocale()); // 신청내역
		String lavel_10 = messageSource.getMessage("clm.field.signmng.apl", null, new RequestContext(request).getLocale()); // 날인신청
		String lavel_11 = messageSource.getMessage("clm.field.signmng.aploutreq", null, new RequestContext(request).getLocale()); // 인장반출 신청
		String lavel_12 = messageSource.getMessage("clm.field.signmng.aplno", null, new RequestContext(request).getLocale()); // 반출인장번호
		String lavel_13 = messageSource.getMessage("clm.field.signmng.apprmsg3", null, new RequestContext(request).getLocale()); // 회사 외부로의 인장반출을 신청합니다.
		String lavel_14 = messageSource.getMessage("clm.field.signmng.outfromto", null, new RequestContext(request).getLocale()); // 반출기간
		String lavel_15 = messageSource.getMessage("clm.field.signmng.doc", null, new RequestContext(request).getLocale()); // 증명서류
		String lavel_16 = messageSource.getMessage("clm.field.signmng.cert1", null, new RequestContext(request).getLocale()); // 법인인감증명서
		String lavel_17 = messageSource.getMessage("clm.field.signmng.cert2", null, new RequestContext(request).getLocale()); // 등기부등본
		String lavel_18 = messageSource.getMessage("clm.field.signmng.cert3", null, new RequestContext(request).getLocale()); // 등기부초본
		String lavel_19 = messageSource.getMessage("clm.field.signmng.cert4", null, new RequestContext(request).getLocale()); // 사용인감계
		String lavel_20 = messageSource.getMessage("clm.field.signmng.usem", null, new RequestContext(request).getLocale()); // 사용용도
		String lavel_21 = messageSource.getMessage("clm.field.signmng.cert5", null, new RequestContext(request).getLocale()); // 일반위임장
		String lavel_22 = messageSource.getMessage("clm.field.signmng.cert6", null, new RequestContext(request).getLocale()); // 공증위임장
		String lavel_23 = messageSource.getMessage("clm.field.signmng.cert7", null, new RequestContext(request).getLocale()); // 대표이사신분증사본
		String lavel_24 = messageSource.getMessage("clm.field.signmng.manhis", null, new RequestContext(request).getLocale()); // 담당자내역
		String lavel_25 = messageSource.getMessage("clm.field.signmng.aplprcman", null, new RequestContext(request).getLocale()); // 날인담당자
		String lavel_26 = messageSource.getMessage("clm.field.signmng.docprcman", null, new RequestContext(request).getLocale()); // 증명서류 발급담당자
		String lavel_27 = messageSource.getMessage("clm.field.signmng.adddoccnt", null, new RequestContext(request).getLocale()); // 부 
		String lavel_28 = messageSource.getMessage("clm.page.field.decidearbitrarilyre.note", null, new RequestContext(request).getLocale()); // 비고
		
		
		// 본문에 표기할 내용을 DB에서 조회해 온다
		// 상세정보취득
		List resultList = null;
		
		resultList = signManageService.detailSign(vo);
		ListOrderedMap lom = null;		
					
		// 첨부파일 정보 취득
		List attachList = signManageService.getAttachList(vo);
		
		String appr_status = "";  // 결재 상태 값
		String ref_key = "";    // 첨부 파일 키
		String cntrt_id = "";   // 계약 아이디
		String cnsdreq_id = ""; // 의뢰 아이디
		String txt = "";    // 본문 내용 (br거친 내용)
		String txt2 = "";   // 본문 내용 (br안거친 생짜)
		String title = ""; // 날인 신청 제목
		String seal_yn = ""; // 날인 신청 여부
		String seal_knd_nm = "";
		String apl_out_yn = "";
		String doc_yn = ""; // 문서 선택 여부
		BigDecimal doc1 = null; // 신청 문서 부수
		BigDecimal doc2 = null; // 신청 문서 부수
		BigDecimal doc3 = null; // 신청 문서 부수
		BigDecimal doc4 = null; // 신청 문서 부수
		BigDecimal doc5 = null; // 신청 문서 부수
		BigDecimal doc6 = null; // 신청 문서 부수
		BigDecimal doc7 = null; // 신청 문서 부수
		BigDecimal doc8 = null; // 신청 문서 부수
		BigDecimal doc9 = null; // 신청 문서 부수
		BigDecimal doc10 = null; // 신청 문서 부수
		String use_summ = "";
		String doc_scrtxt = "";
		String sbm = "";
		String seal_rqstman_txt = "";
		String reg_dt = "";
		String apl_cls_nm = "";
		String seal_ffmtman_txt = "";
		String doc_issuer_txt = "";
		String apl_seal_no = "";
		String apl_ymd_txt = "";
		
		List listSignAppr = null;

		if(resultList.size() > 0){				
			lom = (ListOrderedMap)resultList.get(0);				
			appr_status = (String)lom.get("SEAL_RQST_STATUS");
			cntrt_id = StringUtil.bvl((String)lom.get("CNTRT_ID"),"");
			cnsdreq_id = StringUtil.bvl((String)lom.get("CNSDREQ_ID"),"");
			txt = StringUtil.convertEnterToBR((String)lom.get("TXT"));
			txt2 = (String)lom.get("TXT");
			title = (String)lom.get("TITLE");
			seal_yn = (String)lom.get("SEAL_YN");
			seal_knd_nm = (String)lom.get("SEAL_KND_NM");
			apl_out_yn = (String)lom.get("APL_OUT_YN");
			doc_yn = (String)lom.get("DOC_YN");
			doc1  = (BigDecimal)lom.get("DOC1");
			doc2  = (BigDecimal)lom.get("DOC2");
			doc3  = (BigDecimal)lom.get("DOC3");
			doc4  = (BigDecimal)lom.get("DOC4");
			doc5  = (BigDecimal)lom.get("DOC5");
			doc6  = (BigDecimal)lom.get("DOC6");
			doc7  = (BigDecimal)lom.get("DOC7");
			doc8  = (BigDecimal)lom.get("DOC8");
			doc9  = (BigDecimal)lom.get("DOC9");
			doc10 = (BigDecimal)lom.get("DOC10");
			use_summ = (String)lom.get("USE_SUMM");
			doc_scrtxt = (String)lom.get("DOC_SCRTXT");
			sbm = (String)lom.get("SBM");
			seal_rqstman_txt = (String)lom.get("SEAL_RQSTMAN_TXT");
			reg_dt = lom.get("REG_DT").toString();
			apl_cls_nm = (String)lom.get("APL_CLS_NM");
			seal_ffmtman_txt = (String)lom.get("SEAL_FFMTMAN_TXT");
			doc_issuer_txt = (String)lom.get("DOC_ISSUER_TXT");
			apl_seal_no = (String)lom.get("APL_SEAL_NO");
			apl_ymd_txt = (String)lom.get("APL_YMD_TXT");
			
			
			form.setTxt(txt);
							
			// 의뢰 아이디가 존재 하는 경우 체결에서 넘어온 날인 신청건으로 간주하고, 존재 하지 않는 경우 날인 신청화면에서 신청한 날인 신청으로 판단한다.
			if("".equals(cnsdreq_id)){
				ref_key = vo.getApl_sqn();					
			} else {
				ref_key = cnsdreq_id;
			}
			
			// 반려나 승인이 된 신청이 경우 승인이력 정보 취득
			if(appr_status.equals("AP0103") || appr_status.equals("AP0104")){
				vo.setRef_key(ref_key);
				vo.setCnsdreq_id(cnsdreq_id);
				listSignAppr = signManageService.listSignAppr(vo);					
			}
		}			
		
		// 첨부파일 참조를 위한 키 설정
		form.setCnsdreq_id(cnsdreq_id);
		form.setRef_key(cntrt_id + "@" + cnsdreq_id);
		
		// 버튼 권한 처리
		vo.setAuth_str_id(vo.getApl_sqn());
		vo.setSession_user_role_cds(vo.getSession_user_role_cds());
		vo.setSession_user_id(vo.getSession_user_id());
		// 수정/삭제 권한 
		boolean auth_modify = signManageService.authModify(MODIFY, vo);
		// 날인처리/ 반납처리 권한
		boolean auth_sign_process = signManageService.authProcess(SEAL, vo);	
		// 증명서류 처리권한
		boolean auth_doc_process = signManageService.authProcess(DOC, vo);
		
		// 본문 만들기 - HEARDER
		StringBuffer sb =  new StringBuffer();		
		   
		sb.append("<!DOCTYPE html>\n")
		  .append("<html>\n")
		  .append("<head>\n")
		//  .append("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=8; IE=9\" />\n")
		  .append("<title>"+ title +"</title>\n")    
		  .append("<LINK href=\""+url+"/style/las/ko/las.css\"   type=\"text/css\" rel=\"stylesheet\">\n")
		  .append("<LINK href=\""+url+"/style/las/ko/mail.css\"   type=\"text/css\" rel=\"stylesheet\">\n")
		  .append("</head>\n")
		  .append("<body>\n")
		
		  .append("<div id=\"m_wrap\">\n")
		  .append("    <div class=\"m_header menu2\">\n")
		  .append("    <h1></h1>\n")
		  .append("    <h2>" + title_1 +"<span class=\"confidential\"></SPAN></h2>\n") // 날인/증명서류 발급 신청
		  .append("    </div>\n")
		  .append("    <div id=\"m_container\">\n")
		  .append("        <div class=\"contents\">\n")
		  .append("        <h3>\n")
		  .append("        <p>" + title_2 + "</p></h3>\n") // 날인/증명서류 발급 신청을 상신하오니 재가하여 주시기 바랍니다.
		
		
		// 본문 만들기 - BODY

	      .append("<div class=\"title_basic mt0\">\n")
		  .append("	<h4>" + lavel_1 +"</h4>\n") // 기본내역
		  .append("</div>\n")
		  .append("")
		  .append("<table class=\"list_basic\" id=\"lom_basic_info\">\n")
		  .append("	<colgroup>\n")
		  .append("		<col width=\"27%\" />\n")
		  .append("		<col width=\"*%\" />\n")
		  .append("	</colgroup>					\n")		
		  .append("	<tr>\n")
		  .append("		<th>" + lavel_2 +"</th>\n") // 건명
		  .append("		<td class='overflow' id=\"lom_title\" >" + title + "</td>\n")
		  .append("	</tr>\n")
		  .append("	<tr>\n")
		  .append("		<th>" + lavel_3 +"</th>\n") // 제출처
		  .append("		<td class='overflow'>" + sbm + "</td>\n")
		  .append("	</tr>\n")
		  .append("	<tr>\n")
		  .append("		<th>" + lavel_4 +"</th>\n") // 신청자
		  .append("	    <td>" + seal_rqstman_txt + "</td>\n")
		  .append("	</tr>\n")
		  .append("	<tr>\n")
		  .append("	    <th>" + lavel_5 +"</th>\n") // 신청일자
		  .append("		<td>" + reg_dt + "</td>\n")
		  .append("	</tr>\n")
		  .append("	<tr>\n")
		  .append("		<th>" + lavel_6 +"</th>\n") // 신청유형
		  .append("		<td>" + apl_cls_nm + "</td>\n")
		  .append("	</tr>\n")
		  .append("	<tr>\n")
		  .append("		<th>" + lavel_7 +"</th>\n") // 세부내용
		  .append("		<td>" + txt + "</td>\n")							
		  .append("	</tr>\n");
 		
 			if (attachList.size() > 0){
 				sb.append("<tr>\n")
 				  .append("<th>" + lavel_8 +"</th><td>\n"); // 첨부
 					
				for(int i=0;i<attachList.size();i++){
					
					ListOrderedMap fLom = (ListOrderedMap)attachList.get(i);
					
					sb.append("<a href='" + attach_url + fLom.get("FILE_ID") +"'>"+fLom.get("ORG_FILE_NM") +" ("+fLom.get("FILE_SZ")+" byte)</a>" + "<br>\n");
					                                                                                           
				}
				
 				sb.append("</td></tr> \n");
 			}
		 		 
		sb.append("</table>\n"); // 본문 1차 끝
		
		// 본문 2차 시작
		
		
		sb.append("<div class=\"title_basic mt10\">\n")
		  .append("	<h4>" + lavel_9 +"</h4>\n") // 신청내역
		  .append("</div>\n")

		  .append("<table class=\"list_basic\" id=\"lom_apl_info\">\n")
		  .append("	<colgroup>\n")
		  .append("		<col width=\"27%\" />\n")
		  .append("		<col width=\"*%\" />\n")
		  .append("	</colgroup>\n");
		
		if("Y".equals(seal_yn)){
			sb.append("	<tr>\n")
			  .append("		<th>" + lavel_10 +"</th>\n")  // 날인신청
			  .append("		<td>"+seal_knd_nm +"</td>\n")
			  .append("	</tr>\n");	
		}
		
		if("Y".equals(apl_out_yn)){
			sb.append("	<tr>\n")
			  .append("		<th >" + lavel_11 +"</th>\n")  // 인장반출신청
			  .append( "		<td>\n")
			  .append( "			 [" + lavel_12 +"] " + apl_seal_no + lavel_13) // 인장번호 회사외부로의 반출을 신청합니다.
			  .append( "		</td>\n")
			  .append( "	</tr>\n")
			  .append( "	<tr>\n")
			  .append( "		<th >" + lavel_14 +"</th>\n") // 반출기간
			  .append( "		<td>" + apl_ymd_txt + "</td>\n")
			  .append( "	</tr>\n");
		}
		
		if("Y".equals(doc_yn)){
			sb.append("	<tr>\n")
			.append( "		<th> " + lavel_15 +"</th>\n") //증명서류
			.append( "		<td>\n");
			
			if(!"".equals(doc1) && doc1 != null){
				sb.append( "	" + lavel_16 +" : " + doc1 + " " + lavel_27 +" <br>\n"); // 법인인감증명서
			}
			
			if(!"".equals(doc2) && doc2 != null){
				sb.append( "	" + lavel_17 +" : " + doc2 + " " + lavel_27 +" <br>\n"); // 등기부등본
			}
			
			if(!"".equals(doc3) && doc3 != null){
				sb.append( "	" + lavel_18 +" : " + doc3 + " " + lavel_27 +" <br>\n"); //등기부초본
			}
			
			if(!"".equals(doc4) && doc4 != null){
				sb.append( "	" + lavel_19 +" : " + doc4 + " " + lavel_27 +" <br>\n") //사용인감계
				  .append( "	" + lavel_20 +" : " + use_summ + "<br>\n"); // 사용용도
			}
			
			if(!"".equals(doc5) && doc5 != null){
				sb.append( "	" + lavel_21 +" : " + doc5 + " " + lavel_27 +" <br>\n"); // 일반위임장
			}
			
			if(!"".equals(doc6) && doc6 != null){
				sb.append( "	" + lavel_22 +" : " + doc6 + " " + lavel_27 +" <br>\n"); // 공증위임장
			}
			
			if(!"".equals(doc7) && doc7 != null){
				sb.append( "	" + lavel_23 +" : " + doc7 + " " + lavel_27 + " \n"); // 대표이사신분증
			}
			
			sb.append( "</td>\n")
			  .append( "</tr>\n");
			
			if(!"".equals(doc_scrtxt)){
				sb.append( "	<tr>\n")
				  .append( "		<th>" + lavel_28 + "</th>\n") //비고
				  .append( "		<td>" + doc_scrtxt + "</td>\n")
				  .append( "</tr>\n");
			}
			
			sb.append("</table>\n");
			
			sb.append("<div class=\"title_basic mt10\">\n")
			  .append("	<h4>" + lavel_24 +"</h4>\n") // 담당자내역
			  .append("</div>\n")
			  .append("<table class=\"list_basic\" id=\"lom_basic_info\">\n")
			  .append("	<colgroup>\n")
			  .append("		<col width=\"27%\" />\n")
			  .append("		<col width=\"*%\" />\n")
			  .append("	</colgroup>					\n");
			
			
		
			if("Y".equals(seal_yn)){
				sb.append("	<tr>\n")
				  .append("		<th >" + lavel_25 +"</th>\n") // 날인담당자
				  .append("		<td>" + seal_ffmtman_txt + "</td>\n")
				  .append("	</tr>\n");
			}
								
			
			if("Y".equals(doc_yn)){
				sb.append("	<tr>\n")
				  .append("		<th >" + lavel_26 +"</th>\n") // 증명서루 발급담당자
				  .append("		<td>" + doc_issuer_txt + "</td>\n")
				  .append("	</tr>\n");
			}
								

			sb.append("</table>\n")
			  .append("</div>\n")
			  .append("</div>\n")
			  .append("</div>\n")
			  .append("</body>\n")
			  .append("</html>\n");
				
		}
		
				
			
		
		String contents = sb.toString();
		
		return contents;
		
	}
	
	
	/*
	 *상신 HTML 작성
	 jsp에서 사용되던 것 백업으로 남겨둠.
	function makeApprovalHTMLXXXXXXXXXXXX(){
		 		
		var makeFlag = true;
		frm.approval_content.value = "";

		// var url  = "<spring:message code='secfw.url.domain' />";
		var url  = "<%=strServer%>";
		var attach_url  = url + "/clms/common/clmsfile.do?method=doClmsDownload&file_id=";	
		
		var title_1 = "<spring:message code='clm.field.signmng.apltab' />"; // 날인/증명서류 발급 신청
		var title_2 = "<spring:message code='clm.field.signmng.apprmsg9' />"; // 날인/증명서류 발급 신청을 상신하오니 재가하여 주시기 바랍니다.
		
		var lavel_1 = "<spring:message code='clm.field.signmng.basicinfo' />"; // 기본내역
		var lavel_2 = "<spring:message code='clm.field.signmng.title' />"; // 건명
		var lavel_3 = "<spring:message code='clm.field.signmng.towhere' />"; // 제출처
		var lavel_4 = "<spring:message code='clm.field.signmng.reqman' />"; // 신청자
		var lavel_5 = "<spring:message code='clm.field.signmng.reqday' />"; // 신청일자
		var lavel_6 = "<spring:message code='clm.field.signmng.apltype' />"; // 신청유형
		var lavel_7 = "<spring:message code='clm.field.signmng.detaildsc' />"; // 세부내용 // clm.field.signmng.detaildsc
		var lavel_8 = "<spring:message code='clm.page.field.srch.attach' />"; // 첨부
		var lavel_9 = "<spring:message code='clm.field.signmng.aplinfo' />"; // 신청내역
		var lavel_10 = "<spring:message code='clm.field.signmng.apl' />"; // 날인신청
		var lavel_11 = "<spring:message code='clm.field.signmng.aploutreq' />"; // 인장반출 신청
		var lavel_12 = "<spring:message code='clm.field.signmng.aplno' />"; // 반출인장번호
		var lavel_13 = "<spring:message code='clm.field.signmng.apprmsg3' />"; // 회사 외부로의 인장반출을 신청합니다.
		var lavel_14 = "<spring:message code='clm.field.signmng.outfromto' />"; // 반출기간
		var lavel_15 = "<spring:message code='clm.field.signmng.doc' />"; // 증명서류
		var lavel_16 = "<spring:message code='clm.field.signmng.cert1' />"; // 법인인감증명서
		var lavel_17 = "<spring:message code='clm.field.signmng.cert2' />"; // 등기부등본
		var lavel_18 = "<spring:message code='clm.field.signmng.cert3' />"; // 등기부초본
		var lavel_19 = "<spring:message code='clm.field.signmng.cert4' />"; // 사용인감계
		var lavel_20 = "<spring:message code='clm.field.signmng.usem' />"; // 사용용도
		var lavel_21 = "<spring:message code='clm.field.signmng.cert5' />"; // 일반위임장
		var lavel_22 = "<spring:message code='clm.field.signmng.cert6' />"; // 공증위임장
		var lavel_23 = "<spring:message code='clm.field.signmng.cert7' />"; // 대표이사신분증사본
		var lavel_24 = "<spring:message code='clm.field.signmng.manhis' />"; // 담당자내역
		var lavel_25 = "<spring:message code='clm.field.signmng.aplprcman' />"; // 날인담당자
		var lavel_26 = "<spring:message code='clm.field.signmng.docprcman' />"; // 증명서류 발급담당자
		var lavel_27 = "<spring:message code='clm.field.signmng.adddoccnt' />"; // 부 
		var lavel_28 = "<spring:message code='clm.page.field.decidearbitrarilyre.note' />"; // 비고
		
		var txt_val = $("#rel_txt").val();
		
		var cont_header = "";		
		   
		cont_header += "<!DOCTYPE html>\n";
		cont_header += "<html>\n";
		cont_header += "<head>\n";
		cont_header += "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=8; IE=9\" />\n";
		cont_header += "<title>"+ $("#lom_title").html() +"</title>\n";
		cont_header += "<LINK href=\""+url+"/style/las/ko/las.css\"   type=\"text/css\" rel=\"stylesheet\">";
		cont_header += "<LINK href=\""+url+"/style/las/ko/mail.css\"   type=\"text/css\" rel=\"stylesheet\">";
		cont_header += "</head>\n";
		cont_header += "<body>";				
		
		cont_header +="<div id=\"m_wrap\">\n";
		cont_header +="    <div class=\"m_header menu2\">\n";
		cont_header +="    <h1></h1>\n";
		cont_header +="    <h2>" + title_1 +"<span class=\"confidential\"></SPAN></h2>\n"; // 날인/증명서류 발급 신청
		cont_header +="    </div>\n";
		cont_header +="    <div id=\"m_container\">\n";
		cont_header +="        <div class=\"contents\">\n";
		cont_header +="        <h3>\n";
		cont_header +="        <p>" + title_2 + "</p></h3>\n"; // 날인/증명서류 발급 신청을 상신하오니 재가하여 주시기 바랍니다.
		
		var cont_basic = "";

	    cont_basic += "<div class=\"title_basic mt0\">";
		cont_basic += "	<h4>" + lavel_1 +"</h4>"; // 기본내역
		cont_basic += "</div>";
		cont_basic += "";
		cont_basic += "<table class=\"list_basic\" id=\"lom_basic_info\">";
		cont_basic += "	<colgroup>";
		cont_basic += "		<col width=\"27%\" />";
		cont_basic += "		<col width=\"*%\" />";
		cont_basic += "	</colgroup>					";		
		cont_basic += "	<tr>";
		cont_basic += "		<th>" + lavel_2 +"</th>"; // 건명
		cont_basic += "		<td class='overflow' id=\"lom_title\" >" + "<c:out value='${lom.title}' escapeXml='false' />" + "</td>";
		cont_basic += "	</tr>";
		cont_basic += "	<tr>";
		cont_basic += "		<th>" + lavel_3 +"</th>"; // 제출처
		cont_basic += "		<td class='overflow'>" + "<c:out value='${lom.sbm}' escapeXml='false' />" + "</td>";
		cont_basic += "	</tr>";
		cont_basic += "	<tr>";
		cont_basic += "		<th>" + lavel_4 +"</th>"; // 신청자
		cont_basic += "	    <td>" + "<c:out value='${lom.seal_rqstman_txt}' />" + "</td>";
		cont_basic += "	</tr>";
		cont_basic += "	<tr>";
		cont_basic += "	    <th>" + lavel_5 +"</th>"; // 신청일자
		cont_basic += "		<td>" + "<c:out value='${lom.reg_dt}' />" + "</td>";
		cont_basic += "	</tr>";
		cont_basic += "	<tr>";
		cont_basic += "		<th>" + lavel_6 +"</th>"; // 신청유형
		cont_basic += "		<td>" + "<c:out value='${lom.apl_cls_nm}' />" + "</td>";
		cont_basic += "	</tr>";
  		cont_basic += "	<tr>";
		cont_basic += "		<th  >" + lavel_7 +"</th>"; // 세부내용
		cont_basic += "		<td>" + txt_val + "</td>";							
 		cont_basic += "	</tr>"; 
 		
 			<c:if test="${!empty attachList}">		
		 		cont_basic += "	<tr>";
				cont_basic += "		<th >" + lavel_8 +"</th><td style='height:100px; background:red'>"; // 첨부
 					
				<c:forEach var="list" items="${attachList}">
						cont_basic += "<a href='" + attach_url + "<c:out value='${list.file_id}' />' ><c:out value='${list.org_file_nm}' /> (<c:out value='${list.file_sz}' /> byte)</a>" + "<br>";		
				</c:forEach>
				
				cont_basic += "</td></tr> "; 
			</c:if>
		
		cont_basic += "</table>"; // 
		
		var cont_apl = "";
		
		cont_apl += "<div class=\"title_basic mt10\">";
		cont_apl += "	<h4>" + lavel_9 +"</h4>"; // 신청내역
		cont_apl += "</div>";

		cont_apl += "<table class=\"list_basic\" id=\"lom_apl_info\">";
		cont_apl += "	<colgroup>";
		cont_apl += "		<col width=\"27%\" />";
		cont_apl += "		<col width=\"*%\" />";
		cont_apl += "	</colgroup>";
		
		<c:if test="${lom.seal_yn eq 'Y'}" >		
			cont_apl += "	<tr>";
			cont_apl += "		<th>" + lavel_10 +"</th>";  // 날인신청
			cont_apl += "		<td><c:out value='${lom.seal_knd_nm}' /></td>";
			cont_apl += "	</tr>";		
		</c:if>
		
		<c:if test="${lom.apl_out_yn eq 'Y'}">		
			cont_apl += "	<tr>";
			cont_apl += "		<th >" + lavel_11 +"</th>";  // 인장반출신청
			cont_apl += "		<td>";
			cont_apl += "			 [" + lavel_12 +"] " + "<c:out value='${lom.apl_seal_no}' escapeXml='false' />" + lavel_13; // 인장번호 회사외부로의 반출을 신청합니다.
			cont_apl += "		</td>";
			cont_apl += "	</tr>";
			cont_apl += "	<tr>";
			cont_apl += "		<th >" + lavel_14 +"</th>"; // 반출기간
			cont_apl += "		<td>" + "<c:out value='${lom.apl_ymd_txt}' escapeXml='false' />" + "</td>";
			cont_apl += "	</tr>";			
		</c:if>
		
		<c:if test="${lom.doc_yn eq 'Y'}">		
			cont_apl += "	<tr>";
			cont_apl += "		<th> " + lavel_15 +"</th>"; //증명서류
			cont_apl += "		<td>";
			
			<c:if test="${!empty lom.doc1 }">				
				cont_apl += "	" + lavel_16 +" : " + "<c:out value='${lom.doc1}' />" + " " + lavel_27 +" <br>"; // 법인인감증명서
			</c:if>
			<c:if test="${!empty lom.doc2 }">				
				cont_apl += "	" + lavel_17 +" : " + "<c:out value='${lom.doc2}' />" + " " + lavel_27 +" <br>"; // 등기부등본
			</c:if>
			<c:if test="${!empty lom.doc3 }">				
				cont_apl += "	" + lavel_18 +" : " + "<c:out value='${lom.doc3}' />" + " " + lavel_27 +" <br>"; //등기부초본
			</c:if>		
			<c:if test="${!empty lom.doc4 }">	
				cont_apl += "	" + lavel_19 +" : " + "<c:out value='${lom.doc4}' />" + " " + lavel_27 +" <br>"; //사용인감계
				cont_apl += "	" + lavel_20 +" : " + "<c:out value='${lom.use_summ}' escapeXml='false' />" + "<br>"; // 사용용도
			</c:if>
			<c:if test="${!empty lom.doc5 }">				
				cont_apl += "	" + lavel_21 +" : " + "<c:out value='${lom.doc5}' />" + " " + lavel_27 +" <br>"; // 일반위임장
			</c:if>	
			<c:if test="${!empty lom.doc6 }">				
				cont_apl += "	" + lavel_22 +" : " + "<c:out value='${lom.doc6}' />" + " " + lavel_27 +" <br>"; // 공증위임장
			</c:if>	
			<c:if test="${!empty lom.doc7 }">				
				cont_apl += "	" + lavel_23 +" : " + "<c:out value='${lom.doc7}' />" + " " + lavel_27 + " "; // 대표이사신분증
			</c:if>		
			cont_apl += "</td>";
			cont_apl += "</tr>";			
			<c:if test="${!empty lom.doc_scrtxt}">	
			cont_apl += "	<tr>";
			cont_apl += "		<th>" + lavel_28 + "</th>"; //비고
			cont_apl += "		<td>" + "<c:out value='${lom.doc_scrtxt}' escapeXml='false' />" + "</td>";
			cont_apl += "</tr>";	
			</c:if>	
		</c:if>
		
		cont_apl += "</table>";	
		
		var cont_person = "";

	    cont_person += "<div class=\"title_basic mt10\">";
		cont_person += "	<h4>" + lavel_24 +"</h4>"; // 담당자내역
		cont_person += "</div>";
		cont_person += "<table class=\"list_basic\" id=\"lom_basic_info\">";
		cont_person += "	<colgroup>";
		cont_person += "		<col width=\"27%\" />";
		cont_person += "		<col width=\"*%\" />";
		cont_person += "	</colgroup>					";		
	
		<c:if test="${lom.seal_yn eq 'Y'}">		
			cont_person += "	<tr>";
			cont_person += "		<th >" + lavel_25 +"</th>"; // 날인담당자
			cont_person += "		<td>" +"<c:out value='${lom.seal_ffmtman_txt}' />" + "</td>";
			cont_person += "	</tr>";				
		</c:if>	
		
		<c:if test="${lom.doc_yn eq 'Y'}">
			cont_person += "	<tr>";
			cont_person += "		<th >" + lavel_26 +"</th>"; // 증명서루 발급담당자
			cont_person += "		<td>" + "<c:out value='${lom.doc_issuer_txt}' />" + "</td>";
			cont_person += "	</tr>";				
		</c:if>	

		cont_person += "</table>";
		
		var cont_footer = "";		
		
		cont_footer += "</div>";
		cont_footer += "</div>";
		cont_footer += "</body>\n";
		cont_footer += "</html>\n";
		
		frm.approval_title.value = $("#lom_title").html();
		frm.approval_content.value = cont_header + cont_basic + cont_apl + cont_person + cont_footer;
	
		return makeFlag;
	}
	*/

}