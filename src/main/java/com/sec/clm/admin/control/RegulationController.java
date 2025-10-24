/**
* Project Name : 계약관리시스템
* File Name : RegulationController.java
* Description : 프로세스&규정지침 Controller
* Author : 신남원
* Date : 2010.08.29
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.admin.control;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;

import com.sec.clm.admin.dto.RegulationForm;
import com.sec.clm.admin.dto.RegulationVO;
import com.sec.clm.admin.service.RegulationService;
import com.sds.secframework.common.control.CommonController;

public class RegulationController extends CommonController {
	private RegulationService regulationService;
	public void setRegulationService(RegulationService regulationService) {
		this.regulationService = regulationService;
	}

	/**
	* 프로세스&규정지침 목록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listRegulation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Forwarding URL
			**********************************************************/	
			String forwardURL = "/WEB-INF/jsp/clm/admin/Regulation_l.jsp";//목록페이지로 이동.
			RegulationForm cmd = (RegulationForm)request.getAttribute("command");

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			RegulationForm form = new RegulationForm();
			RegulationVO vo = new RegulationVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			if(cmd != null){//저장 후 데이터 호출을 위해.
				form = cmd;
				vo.setRule_no(form.getRule_no());
			}
			
			form.setSrch_one_rule_depth(StringUtil.bvl(form.getSrch_one_rule_depth(), ""));//1단계
			form.setSrch_two_rule_depth(StringUtil.bvl(form.getSrch_two_rule_depth(), ""));//2단계
			form.setSrch_cont(StringUtil.bvl(form.getSrch_cont(), ""));//내용
			form.setSrch_rule_choice(StringUtil.bvl(form.getSrch_rule_choice(), ""));//최상위
			
			vo.setSrch_one_rule_depth(StringUtil.bvl(form.getSrch_one_rule_depth(), ""));//1단계
			vo.setSrch_two_rule_depth(StringUtil.bvl(form.getSrch_two_rule_depth(), ""));//2단계
			vo.setSrch_cont(StringUtil.bvl(form.getSrch_cont(), ""));//내용
			vo.setSrch_rule_choice(StringUtil.bvl(form.getSrch_rule_choice(), ""));//최상위
			
			/*********************************************************
			 * 페이지 객체
			**********************************************************/
			PageUtil pageUtil = new PageUtil();

			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());

	        /*********************************************************
			 * JSP 반환변수
			**********************************************************/
			String returnMessage = "";

			/*********************************************************
			 * 검색처리
			**********************************************************/
			List ruleListODepth = regulationService.listODepthRegulation(vo);//1단계 조회
			List resultList = regulationService.listRegulation(vo);

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

				pageUtil.setTotalRow(((BigDecimal)lom.get("total_cnt")).intValue());
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
			mav.addObject("resultList", resultList);
			mav.addObject("ruleListODepth", ruleListODepth);//1단계 조회
			mav.addObject("pageUtil", pageUtil);
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
	 * 리스트 1단계 선택 시 2단계 리스트 값 가져오기
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getRuldCdHTML(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		try{
			
			HttpSession session = request.getSession(false);
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
			
			HashMap hm = new HashMap();
			
			/**
			 * 파라미터 셋팅
			 */
			Locale lc = (Locale)localeResolver.resolveLocale(request);
			String locale = StringUtil.bvl(lc.getLanguage(), "en");
			
			hm.put("COMP_CD", StringUtil.bvl((String)session.getAttribute("secfw.session.comp_cd"), ""));
			hm.put("UP_RULE_NO", StringUtil.bvl((String)request.getParameter("combo_up_cd"), ""));
			hm.put("SELECTED", StringUtil.bvl((String)request.getParameter("combo_selected"), ""));
			hm.put("LOCALE",   locale);

			String result = regulationService.getRulCdHTML(hm);
			
			response.setContentType("text/html; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(result);
    		out.flush();
    		out.close();
    		
		}catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		}catch (Throwable t) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
    		response.setContentType("text/html; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} 
	}
	
	/**
	* 프로세스&규정지침 목록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView forwardInsertRegulation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Forwarding URL
			**********************************************************/	
			String forwardURL = "/WEB-INF/jsp/clm/admin/Regulation_i.jsp";//목록페이지로 이동.

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			RegulationForm form = new RegulationForm();
			RegulationVO vo = new RegulationVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			List upRuleList = regulationService.listUpRule(vo);//전체depth selectbox 조회.

	        /*********************************************************
			 * JSP 반환변수
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("upRuleList", upRuleList);
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
	* 프로세스&규정지침 목록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView insertRegulation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Forwarding URL
			**********************************************************/	
			String forwardURL = "/clm/admin/regulation.do?method=listRegulation";//목록페이지로 이동.

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			RegulationForm form = new RegulationForm();
			RegulationVO vo = new RegulationVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			vo.setOne_rule_title_input(StringUtil.bvl(form.getOne_rule_title_input(), ""));//최상위제목
			vo.setReg_id(form.getSession_user_id());
			vo.setReg_nm(form.getSession_user_nm());

	        /*********************************************************
			 * JSP 반환변수
			**********************************************************/
			int result = regulationService.insertRegulation(vo);

			String returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());

			ModelAndView mav = new ModelAndView();

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
	* 프로세스&규정지침 상세
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView detailRegulation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Forwarding URL
			**********************************************************/	
			String forwardURL = "/WEB-INF/jsp/clm/admin/Regulation_d.jsp";//상세페이지로 이동.
			RegulationForm cmd = (RegulationForm)request.getAttribute("command");
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			RegulationForm form = new RegulationForm();
			RegulationVO vo = new RegulationVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			if(cmd != null){//저장 후 데이터 호출을 위해.
				form = cmd;
				vo.setRule_no(form.getRule_no());
			}

	        /*********************************************************
			 * JSP 반환변수
			**********************************************************/
			ListOrderedMap lom = regulationService.detailRegulation(vo);

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("lom", lom);
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
	* 프로세스&규정지침 목록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView forwardModifyRegulation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Forwarding URL
			**********************************************************/	
			String forwardURL = "/WEB-INF/jsp/clm/admin/Regulation_u.jsp";//수정페이지로 이동.

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			RegulationForm form = new RegulationForm();
			RegulationVO vo = new RegulationVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

	        /*********************************************************
			 * JSP 반환변수
			**********************************************************/
			ListOrderedMap lom = regulationService.detailRegulation(vo);

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("lom", lom);
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
	* 프로세스&규정지침 수정
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView modifyRegulation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Forwarding URL
			**********************************************************/	
			String forwardURL = "/clm/admin/regulation.do?method=detailRegulation";//상세페이지로 이동.

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			RegulationForm form = new RegulationForm();
			RegulationVO vo = new RegulationVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			vo.setMod_id(form.getSession_user_id());
			vo.setMod_nm(form.getSession_user_nm());

	        /*********************************************************
			 * JSP 반환변수
			**********************************************************/
			int result = regulationService.modifyRegulation(vo);

			String returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("returnMessage", returnMessage);
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
	* 프로세스&규정지침서 삭제
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView deleteRegulation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Forwarding URL
			**********************************************************/	
			String forwardURL = "/clm/admin/regulation.do?method=listRegulation";//목록으로 이동.

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			RegulationForm form = new RegulationForm();
			RegulationVO vo = new RegulationVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			vo.setDel_id(form.getSession_user_id());
			vo.setDel_nm(form.getSession_user_nm());

	        /*********************************************************
			 * JSP 반환변수
			**********************************************************/
			int result = regulationService.deleteRegulation(vo);

			String returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale());

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("returnMessage", returnMessage);
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
	

}