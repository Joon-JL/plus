/**
 * 
 * Project Name : 계약관리시스템 구축
 * File name	: CLMSCommonController.java
 * Description	: 공통코드관련 Controller
 * Author		: SDS
 * Date			: 2011. 08. 04
 * Copyright	: 
 */
package com.sec.common.clmscom.control;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.outldap.samsung.net.Organization;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import anyframe.web.springmvc.controller.AnyframeFormController;

import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.singleIF.service.EsbOrgService;
import com.sec.common.clmscom.dto.CLMSCommonForm;
import com.sec.common.clmscom.dto.CLMSCommonVO;
import com.sec.common.clmscom.dto.OrgnzForm;
import com.sec.common.clmscom.dto.OrgnzVO;
import com.sec.common.clmscom.service.CLMSCommonService;


/**
 * Description	: 공통코드관련 Controller
 * Author 		: SDS
 * Date			: 2011. 08. 04
 * 
 */
public class CLMSCommonController extends AnyframeFormController {

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private CLMSCommonService clmsComService;
	public void setClmsComService(CLMSCommonService clmsComService)
	{
		this.clmsComService = clmsComService;
	}
	
	/**
	 * PropertyService 선언 및 세팅
	 */
	private PropertyService propertyService;
	public void setPropertyService(PropertyService propertyService) {
		this.propertyService = propertyService;
	}
	
	/**
	 * Locale 선언 및 세팅
	 */
	private LocaleResolver localeResolver;
	public void setLocaleResolver(LocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
	}
	
	/**
	 * MessageSource 선언 및 세팅
	 */
	protected MessageSource messageSource;
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private EsbOrgService esbOrgService;	
	public void setEsbOrgService(EsbOrgService esbOrgService) {
		this.esbOrgService = esbOrgService;
	}
	
	/**
	 * 대분류에 의해 공통코드를 가져온다.
	 *
	 * @param request
	 * @param response
	 * @return void
	 * @throws Exception
	 */
	public void listComCdByGrpCd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
		
			HttpSession session = request.getSession(false);
	
			/**
			 * 시스템  코드 ()
			 */
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "LAS");
			String sUserId = StringUtil.bvl((String)session.getAttribute("secfw.session.user_id"), "");
			String topRole		= "";		// 사용자 Role
			
			ArrayList tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.roleList");
			
			if(tmpSessionRoleList.contains("RA00")){
				topRole = "RA00";
			}
	
			/**
			 * 파라미터 셋팅
			 */
			HashMap map = new HashMap();
	
			Locale lc = (Locale)localeResolver.resolveLocale(request);
			String locale = StringUtil.bvl(lc.getLanguage(), "en");
			
			map.put("SYS_CD", StringUtil.bvl((String)request.getParameter("combo_sys_cd"), sysCd));
			map.put("GRP_CD", StringUtil.bvl((String)request.getParameter("combo_grp_cd"), ""));
			map.put("SELECTED", StringUtil.bvl((String)request.getParameter("combo_selected"), ""));
			map.put("LOCALE", locale);
			map.put("ABBR", StringUtil.bvl((String)request.getParameter("combo_abbr"), ""));
			map.put("DEL_YN", StringUtil.bvl((String)request.getParameter("combo_del_yn"), ""));
			map.put("TYPE", StringUtil.bvl((String)request.getParameter("combo_type"), ""));
			map.put("SELECT_CD", StringUtil.bvl((String)request.getParameter("select_cd"), "#"));
			map.put("USEMAN_MNG_ITM1", StringUtil.bvl((String)request.getParameter("useman_mng_itm1"), ""));
			map.put("USEMAN_MNG_ITM2", StringUtil.bvl((String)request.getParameter("useman_mng_itm2"), ""));
			map.put("USEMAN_MNG_ITM3", StringUtil.bvl((String)request.getParameter("useman_mng_itm3"), ""));
			map.put("USEMAN_MNG_ITM4", StringUtil.bvl((String)request.getParameter("useman_mng_itm4"), ""));
			map.put("USEMAN_MNG_ITM5", StringUtil.bvl((String)request.getParameter("useman_mng_itm5"), ""));
			map.put("USER_ID",         sUserId);
			map.put("SEL_GRD", StringUtil.bvl((String)request.getParameter("sel_grd"), ""));
			map.put("PAGE_GBN", StringUtil.bvl((String)request.getParameter("page_gbn"), ""));
			map.put("COMP_CD", StringUtil.bvl((String)session.getAttribute("secfw.session.comp_cd"), ""));
			map.put("TOP_ROLE", topRole);
			map.put("LIMIT_CD", StringUtil.bvl((String)request.getParameter("limit_cd"), ""));
						
			String result = clmsComService.listComCdByGrpCd(map);
	
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
	 * 대분류에 의해 공통코드를 가져온다.
	 * 체크박스 타입으로 리턴
	 * @param request
	 * @param response
	 * @return void
	 * @throws Exception
	 */
	public void checkBoxComCdByGrpCd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
		
			HttpSession session = request.getSession(false);
	
			/**
			 * 시스템  코드
			 */
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "LAS");
	
			/**
			 * 파라미터 셋팅
			 */
			HashMap map = new HashMap();
	
			Locale lc = (Locale)localeResolver.resolveLocale(request);
			String locale = StringUtil.bvl(lc.getLanguage(), "en");
			
			map.put("SYS_CD", StringUtil.bvl((String)request.getParameter("combo_sys_cd"), sysCd));
			map.put("GRP_CD", StringUtil.bvl((String)request.getParameter("combo_grp_cd"), ""));
			map.put("CHECKED", StringUtil.bvl((String)request.getParameter("combo_checked"), "")); //체크된 값
			map.put("NAME", StringUtil.bvl((String)request.getParameter("combo_name"), ""));
			map.put("LOCALE", locale);
			map.put("ABBR", StringUtil.bvl((String)request.getParameter("combo_abbr"), ""));
			map.put("DEL_YN", StringUtil.bvl((String)request.getParameter("combo_del_yn"), ""));
			map.put("SELECT_CD", StringUtil.bvl((String)request.getParameter("select_cd"), ""));
			
			
			String result = clmsComService.checkBoxComCdByGrpCd(map);
	
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
	 * 각 테이블들의 COMBO 리스트 값 리턴
	 * 계약유형/ 
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getComboHTML(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		try{
			
			HttpSession session = request.getSession(false);
			/**
			 * 시스템  코드
			 */
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "LAS");
			
			/**
			 * Form 및 VO Binding
			 */
			HashMap hm = new HashMap();
			
			/**
			 * 파라미터 셋팅
			 */
			Locale lc = (Locale)localeResolver.resolveLocale(request);
			String locale = StringUtil.bvl(lc.getLanguage(), "en");
			
			hm.put("GBN", StringUtil.bvl((String)request.getParameter("combo_gbn"), ""));
			hm.put("UP_CD", StringUtil.bvl((String)request.getParameter("combo_up_cd"), ""));
			hm.put("SELECTED", StringUtil.bvl((String)request.getParameter("combo_selected"), ""));
			hm.put("LOCALE",   locale);
			hm.put("ABBR", StringUtil.bvl((String)request.getParameter("combo_abbr"), ""));
			hm.put("DEL_YN", StringUtil.bvl((String)request.getParameter("combo_del_yn"), ""));
			hm.put("TYPE", StringUtil.bvl((String)request.getParameter("combo_type"), ""));
			//사업부코드일경우에만 사용
			hm.put("ADD_YN", StringUtil.bvl((String)request.getParameter("combo_add_yn"), ""));
			/**
			 * 목록조회
			 */
			String result = clmsComService.getComboHTML(hm);	
			
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
	 * 조직부서
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getUnitOrgnzComboHTML(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		try{
			HttpSession session = request.getSession(false);

			/**
			 * 파라미터 셋팅
			 */
			HashMap hm = new HashMap();
			
			//Locale lc = (Locale)localeResolver.resolveLocale(request);
			//String locale = StringUtil.bvl(lc.getLanguage(), "en");
			
			//hm.put("LOCALE", locale);
			hm.put("LOCALE", StringUtil.bvl((String)request.getParameter("locale"), ""));
			hm.put("ABBR", StringUtil.bvl((String)request.getParameter("combo_abbr"), ""));
			hm.put("TYPE", StringUtil.bvl((String)request.getParameter("combo_type"), ""));
			hm.put("SELECTED", StringUtil.bvl((String)request.getParameter("combo_selected"), ""));
			hm.put("UP_ORGNZ_CD", StringUtil.bvl((String)request.getParameter("up_orgnz_cd"), ""));
			
			/**
			 * 목록조회
			 */
			String result = clmsComService.getUnitOrgnzComboHTML(hm);	
			
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
	 * 법부 담당자 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getLasPersonComboHTML(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		try{
			HttpSession session = request.getSession(false);
			/**
			 * 시스템  코드
			 */
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "LAS");
			/**
			 * 소속조직 코드
			 */
			String blngtOrgnz = StringUtil.bvl((String)session.getAttribute("secfw.session.blngt_orgnz"), ""); 
			
			
			
			/**
			 * 파라미터 셋팅
			 */
			Locale lc = (Locale)localeResolver.resolveLocale(request);
			String locale = StringUtil.bvl(lc.getLanguage(), "en");
			
			HashMap hm = new HashMap();
			
			hm.put("SYS_CD", StringUtil.bvl((String)request.getParameter("combo_sys_cd"), sysCd));
			hm.put("LOCALE", locale);
			hm.put("BLNGT_ORGNZ", blngtOrgnz);
			hm.put("TYPE", StringUtil.bvl((String)request.getParameter("combo_type"), ""));
			hm.put("SELECTED", StringUtil.bvl((String)request.getParameter("combo_selected"), ""));
			hm.put("STATS", StringUtil.bvl((String)request.getParameter("stats"), ""));
			hm.put("CNSDREQ_ID", StringUtil.bvl((String)request.getParameter("cnsdreq_id"), ""));
			hm.put("OPP_DEPT_DIV", StringUtil.bvl((String)request.getParameter("opp_dept_div"), ""));
			hm.put("SRCH_DEPT", StringUtil.bvl((String)request.getParameter("srch_dept"), ""));
			
			/**
			 * 목록조회
			 */
			String result = clmsComService.getLasPersonComboHTML(hm);	
			
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
	 * 법무시스템 총괄 콤보리스트
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getLasEpsuborgComboHTML(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		try{
			/**
			 * 파라미터 셋팅
			 */
			Locale lc = (Locale)localeResolver.resolveLocale(request);
			String locale = StringUtil.bvl(lc.getLanguage(), "en");
			
			HashMap hm = new HashMap();
			
			hm.put("LOCALE", locale);
			hm.put("TYPE", StringUtil.bvl((String)request.getParameter("combo_type"), ""));
			hm.put("SELECTED", StringUtil.bvl((String)request.getParameter("combo_selected"), ""));
			
			/**
			 * 목록조회
			 */
			String result = clmsComService.getLasEpsuborgComboHTML(hm);	
			
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
	 * 부서 리스트를 반환한다.(트리구조)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listDeptTree(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession(false);
		
		/*********************************************************                                                                                     
		 * Forwarding URL                                                                                                                              
		**********************************************************/                                                                                    
		String forwardURL = "/WEB-INF/jsp/common/DeptUserList.jsp";
		
		/*********************************************************                                                                                     
		 * bind Form, VO                                                                                                                              
		**********************************************************/
		CLMSCommonForm form  = new CLMSCommonForm();
		CLMSCommonVO vo = new CLMSCommonVO();
		
		bind(request, form);
		bind(request, vo);
		
		if(form.getDept_nm() == null) {
			form.setDept_nm("");
		} else {
			form.setDept_nm(StringUtil.convertHtmlTochars(form.getDept_nm()));
		}
		
		COMUtil.getUserAuditInfo(request, vo);
		COMUtil.getUserAuditInfo(request, form);
		
		String returnMessage = "";
		
		/*********************************************************                                                                                     
		 * 파라미터 세팅                                                                                                                                 
		**********************************************************/
		if(vo.getUp_dept_cd() == null || vo.getUp_dept_cd().equals("")) {
			vo.setUp_dept_cd("TOP");
			form.setUp_dept_cd(vo.getUp_dept_cd());
		}
		
		/*********************************************************                                                                                   
		 * business logic                                                                                                                            
		**********************************************************/                                                                                  
		List detailList = clmsComService.listDeptTree(vo);
		
		List userDeptList = null;
		if((vo.getDept_cd() != null && !vo.getDept_cd().equals("")) || (vo.getDept_nm() != null && !vo.getDept_nm().equals(""))) {
			userDeptList = clmsComService.userDeptTree(vo);
			
			if(userDeptList == null || userDeptList.size() == 0) {
				returnMessage = messageSource.getMessage("secfw.msg.succ.noResult",  null, new RequestContext(request).getLocale());
			}
		}
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName(forwardURL);
		
		mav.addObject("resultList", detailList);
		mav.addObject("userDeptList", userDeptList);
		mav.addObject("command", form);
		mav.addObject("returnMessage", returnMessage);
		
		return mav;
		
	}
	
	/**
	 * 부서 리스트를 반환한다.(해당부서의 하위부서만)
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void listDeptTreeAjax(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			/*********************************************************
			 * 시스템  코드 및 사용자아이디
			**********************************************************/
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			CLMSCommonVO vo = new CLMSCommonVO();
			bind(request, vo);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * 하위부서 목록조회
			**********************************************************/
			JSONArray jsonArray = clmsComService.listDeptTreeAjax(vo);
			
			response.setContentType("application/json; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.print(jsonArray);
	        out.flush();
	        out.close();
		} catch (Exception e) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		jo.put("returnCnt", 0);

    		response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
			out.flush();
			out.close();
		}
	}
	
	/**
	 * 일부이름으로 부서명을 조회한다.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView searchDept(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession(false);
		
		/*********************************************************                                                                                     
		 * Forwarding URL                                                                                                                              
		**********************************************************/                                                                                    
		String forwardURL = "/WEB-INF/jsp/common/DeptSearchList.jsp";
		
		/*********************************************************                                                                                     
		 * bind Form, VO                                                                                                                              
		**********************************************************/
		CLMSCommonForm form = new CLMSCommonForm();
		CLMSCommonVO vo = new CLMSCommonVO();
		
		bind(request, form);
		bind(request, vo);
		
		COMUtil.getUserAuditInfo(request, vo);
		COMUtil.getUserAuditInfo(request, form);
		
		/*********************************************************                                                                                     
		 * 페이지 객체                                                                                                                                 
		**********************************************************/
		
		/*********************************************************                                                                                     
		 * 파라미터 세팅                                                                                                                                 
		**********************************************************/
		if(vo.getDept_nm() == null) {
			vo.setDept_nm("");
		} else {
			vo.setDept_nm(StringUtil.replace(vo.getDept_nm(), "*", "%"));
		}
		
		/*********************************************************                                                                                   
		 * business logic                                                                                                                            
		**********************************************************/                                                                                  
		List detailList = clmsComService.searchDept(vo);
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName(forwardURL);
		
		mav.addObject("resultList", detailList);
		mav.addObject("command", form);
		
		return mav;
	}
	
	/**
	 * 결재적합직급여부
	 *
	 * @param request
	 * @param response
	 * @return void
	 * @throws Exception
	 */
	public void listTbComCd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = null;
		HashMap map = null;
		String sysCd = null;		
		String result = null;
		try{
			
			session = request.getSession(false);
	
			/**
			 * 시스템  코드
			 */
			sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
	
			/**
			 * 파라미터 셋팅
			 */
			
			map = new HashMap();			
			map.put("sys_cd", StringUtil.bvl((String)request.getParameter("sys_cd"), sysCd));
			map.put("grp_cd", StringUtil.bvl((String)request.getParameter("grp_cd"), ""));
			map.put("cd", StringUtil.bvl((String)request.getParameter("cd"), ""));
			map.put("cd_nm", StringUtil.bvl((String)request.getParameter("cd_nm"), ""));
			map.put("cd_abbr_nm", StringUtil.bvl((String)request.getParameter("cd_abbr_nm"), ""));
			map.put("cd_nm_eng", StringUtil.bvl((String)request.getParameter("cd_nm_eng"), ""));
			map.put("cd_abbr_nm_eng", StringUtil.bvl((String)request.getParameter("cd_abbr_nm_eng"), ""));
			map.put("useman_mng_itm1", StringUtil.bvl((String)request.getParameter("useman_mng_itm1"), ""));
			map.put("useman_mng_itm2", StringUtil.bvl((String)request.getParameter("useman_mng_itm2"), ""));
			map.put("useman_mng_itm3", StringUtil.bvl((String)request.getParameter("useman_mng_itm3"), ""));
			map.put("useman_mng_itm4", StringUtil.bvl((String)request.getParameter("useman_mng_itm4"), ""));
			map.put("useman_mng_itm5", StringUtil.bvl((String)request.getParameter("useman_mng_itm5"), ""));
			
			JSONArray jsonArray = clmsComService.listTbComCd(map);
	
			response.setContentType("text/html; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jsonArray);
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
	 * 단위조직 리스트 팝업
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardOrgnzPop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/*********************************************************                                                                                     
		 * Forwarding URL                                                                                                                              
		**********************************************************/                                                                                    
		String forwardURL = "/WEB-INF/jsp/common/OrgnzSearchList.jsp";
		
		/*********************************************************                                                                                     
		 * bind Form, VO                                                                                                                              
		**********************************************************/
		OrgnzForm form = new OrgnzForm();
		OrgnzVO vo = new OrgnzVO();
		
		bind(request, form);
		bind(request, vo);
		
		COMUtil.getUserAuditInfo(request, vo);
		COMUtil.getUserAuditInfo(request, form);
		
		/*********************************************************                                                                                   
		 * 조회처리                                                                                                                            
		**********************************************************/     
		List resultList = clmsComService.searchOrgnz(vo);
		
		String[] orgnzCds = form.getOrgnz_cds();
		if(orgnzCds != null && orgnzCds.length > 0){
			for(int i=0; i<orgnzCds.length; i++){
				for(int j=0; j<resultList.size(); j++){
					ListOrderedMap result =(ListOrderedMap)resultList.get(j);
					if(orgnzCds[i].equals((String)result.get("orgnz_cd"))){
						result.put("orgnz_chk", "Y");
						resultList.set(j, result);
					}
				}
			}
		}
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName(forwardURL);
		
		mav.addObject("resultList", resultList);
		mav.addObject("command", form);
		
		return mav;
	}	
	
	/**
	 * 법무담당자,관리자 알림창 띄우기
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getAdminAlert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession(false);
		ModelAndView mav = new ModelAndView();
		/*********************************************************                                                                                     
		 * Forwarding URL                                                                                                                              
		**********************************************************/                                                                                    
		String forwardURL = "/WEB-INF/jsp/common/AlertPopup.jsp";
		
		/*********************************************************                                                                                     
		 * bind Form, VO                                                                                                                              
		**********************************************************/
		CLMSCommonForm form = new CLMSCommonForm();
		CLMSCommonVO vo = new CLMSCommonVO();
		
		bind(request, form);
		bind(request, vo);
		
		COMUtil.getUserAuditInfo(request, vo);
		COMUtil.getUserAuditInfo(request, form);
	
		int cntrt_reqs_cnt = 0;
		int cntrt_returns_cnt = 0;
		int cnslt_reqs_cnt = 0;
		int cnslt_returns_cnt = 0;
		
		String roleCd = "";
		String role = "";
		HashMap map = null;
		
		ArrayList userRoleList = new ArrayList();
		ArrayList roleList = (ArrayList)session.getAttribute("secfw.session.role");
	    if(roleList != null && roleList.size()>0){
	    	for(int i=0; i < roleList.size() ;i++){
	        	HashMap hm = (HashMap)roleList.get(i);
	        	roleCd = (String)hm.get("role_cd");
	        	userRoleList.add(roleCd);
	        }
		}
	    if(userRoleList != null && userRoleList.size()>0) {
    		if(userRoleList.contains("RA02")) {	
    			role = "RA02";
    		}
	    }
		
	    if(!"".equals(role)){
	    	map = clmsComService.searchAdminAlert(vo, role);
	    	
	    	if(null != map){
				cntrt_reqs_cnt 		= (Integer)map.get("cntrt_reqs_cnt");
				cntrt_returns_cnt 	= (Integer)map.get("cntrt_returns_cnt");
				cnslt_reqs_cnt 		= (Integer)map.get("cnslt_reqs_cnt");
				cnslt_returns_cnt 	= (Integer)map.get("cnslt_returns_cnt");
			}
	    }
	    /*
	    JSONObject jo = new JSONObject();
		jo.put("cntrt_reqs_cnt"		, cntrt_reqs_cnt);
		jo.put("cntrt_returns_cnt"	, cntrt_returns_cnt);
		jo.put("cnslt_reqs_cnt"		, cnslt_reqs_cnt);
		jo.put("cnslt_returns_cnt"	, cnslt_returns_cnt);
	    response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jo);
		out.flush();
		out.close();
		*/
		mav.setViewName(forwardURL);
		mav.addObject("cntrt_reqs_cnt"		, cntrt_reqs_cnt);
		mav.addObject("cntrt_returns_cnt"	, cntrt_returns_cnt);
		mav.addObject("cnslt_reqs_cnt"		, cnslt_reqs_cnt);
		mav.addObject("cnslt_returns_cnt"	, cnslt_returns_cnt);
		mav.addObject("command", form);
		
		return mav;
		
	}		
	/**
	 * 사업부담당자,관리자 알림창 띄우기
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getApprovalCnt(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession(false);
		ModelAndView mav = new ModelAndView();
		/*********************************************************                                                                                     
		 * Forwarding URL                                                                                                                              
		**********************************************************/                                                                                    
		String forwardURL = "/WEB-INF/jsp/common/AlertApprovalPopup.jsp";
		
		/*********************************************************                                                                                     
		 * bind Form, VO                                                                                                                              
		**********************************************************/
		CLMSCommonForm form = new CLMSCommonForm();
		CLMSCommonVO vo = new CLMSCommonVO();
		
		bind(request, form);
		bind(request, vo);
		
		COMUtil.getUserAuditInfo(request, vo);
		COMUtil.getUserAuditInfo(request, form);
	
		int cntrt_cnt_1 = 0;
		int cntrt_cnt_2 = 0;
		int cntrt_cnt_3 = 0;
		int cntrt_cnt_4 = 0;
		
		String roleCd = "";
		String role = "";
		HashMap map = null;
		
		ArrayList userRoleList = new ArrayList();
		ArrayList roleList = (ArrayList)session.getAttribute("secfw.session.role");
	    if(roleList != null && roleList.size()>0){
	    	for(int i=0; i < roleList.size() ;i++){
	        	HashMap hm = (HashMap)roleList.get(i);
	        	roleCd = (String)hm.get("role_cd");
	        	userRoleList.add(roleCd);
	        }
		}
	    if(userRoleList != null && userRoleList.size()>0) {
    		if(userRoleList.contains("RD01")) {	
    			role = "RD01";
    		}
	    }
		
	    if(!"".equals(role)){
	    	map = clmsComService.getApprovalCnt(vo);
	    	
	    	if(null != map){
	    		cntrt_cnt_1 	= (Integer)map.get("cntrt_cnt_1");
	    		cntrt_cnt_2 	= (Integer)map.get("cntrt_cnt_2");
	    		cntrt_cnt_3 	= (Integer)map.get("cntrt_cnt_3");
	    		cntrt_cnt_4 	= (Integer)map.get("cntrt_cnt_4");
			}
	    }
		mav.setViewName(forwardURL);
		mav.addObject("cntrt_cnt_1"	, cntrt_cnt_1);
		mav.addObject("cntrt_cnt_2"	, cntrt_cnt_2);
		mav.addObject("cntrt_cnt_3"	, cntrt_cnt_3);
		mav.addObject("cntrt_cnt_4"	, cntrt_cnt_4);
		mav.addObject("command", form);
		
		return mav;
		
	}	//
	/**
	 * 사업부담당자,관리자 알림창 띄우기
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void getApprovalCntMain(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession(false);
		ModelAndView mav = new ModelAndView();
		/*********************************************************                                                                                     
		 * Forwarding URL                                                                                                                              
		**********************************************************/                                                                                    
		//String forwardURL = "/WEB-INF/jsp/common/AlertApprovalPopup.jsp";
		
		/*********************************************************                                                                                     
		 * bind Form, VO                                                                                                                              
		**********************************************************/
		CLMSCommonForm form = new CLMSCommonForm();
		CLMSCommonVO vo = new CLMSCommonVO();
		
		bind(request, form);
		bind(request, vo);
		
		COMUtil.getUserAuditInfo(request, vo);
		COMUtil.getUserAuditInfo(request, form);
	
		int cntrt_cnt_1 = 0;
		int cntrt_cnt_2 = 0;
		int cntrt_cnt_3 = 0;
		int cntrt_cnt_4 = 0;
		
		String roleCd = "";
		String role = "";
		HashMap map = null;
		
		ArrayList userRoleList = new ArrayList();
		ArrayList roleList = (ArrayList)session.getAttribute("secfw.session.role");
	    if(roleList != null && roleList.size()>0){
	    	for(int i=0; i < roleList.size() ;i++){
	        	HashMap hm = (HashMap)roleList.get(i);
	        	roleCd = (String)hm.get("role_cd");
	        	userRoleList.add(roleCd);
	        }
		}
	    if(userRoleList != null && userRoleList.size()>0) {
    		if(userRoleList.contains("RD01")) {	
    			role = "RD01";
    		}
	    }
		
	    if(!"".equals(role)){
	    	map = clmsComService.getApprovalCnt(vo);
	    	
	    	if(null != map){
	    		cntrt_cnt_1 	= (Integer)map.get("cntrt_cnt_1");
	    		cntrt_cnt_2 	= (Integer)map.get("cntrt_cnt_2");
	    		cntrt_cnt_3 	= (Integer)map.get("cntrt_cnt_3");
	    		cntrt_cnt_4 	= (Integer)map.get("cntrt_cnt_4");
			}
	    }
	    JSONObject jo = new JSONObject();
		jo.put("cntrt_cnt_1"	, cntrt_cnt_1);
		jo.put("cntrt_cnt_2"	, cntrt_cnt_2);
		jo.put("cntrt_cnt_3"	, cntrt_cnt_3);
		jo.put("cntrt_cnt_4"	, cntrt_cnt_4);
	    response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jo);
		out.flush();
		out.close();
		
	}
	
	/**
	 * ESB 인터페이스를 통해 부서 리스트를 반환한다.(트리구조)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listDeptTreeEsb(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession(false);
		
		/*********************************************************                                                                                     
		 * Forwarding URL                                                                                                                              
		**********************************************************/                                                                                    
		String forwardURL = "/WEB-INF/jsp/common/DeptUserList.jsp";
		
		/*********************************************************                                                                                     
		 * bind Form, VO                                                                                                                              
		**********************************************************/
		CLMSCommonForm form  = new CLMSCommonForm();
		CLMSCommonVO vo = new CLMSCommonVO();
		
		bind(request, form);
		bind(request, vo);
		
		if(form.getDept_nm() == null) {
			form.setDept_nm("");
		} else {
			form.setDept_nm(StringUtil.convertHtmlTochars(form.getDept_nm()));
		}
		
		COMUtil.getUserAuditInfo(request, vo);
		COMUtil.getUserAuditInfo(request, form);
		
		String returnMessage = "";
		
		String compCd = StringUtil.bvl(session.getAttribute("secfw.session.comp_cd"), "");
		
		/*********************************************************                                                                                     
		 * 파라미터 세팅                                                                                                                                 
		**********************************************************/
		if(vo.getUp_dept_cd() == null || vo.getUp_dept_cd().equals("")) {
			vo.setUp_dept_cd("TOP");
			form.setUp_dept_cd(vo.getUp_dept_cd());
		}
		
		/*********************************************************                                                                                   
		 * business logic                                                                                                                            
		**********************************************************/                                                                                  
//		List detailList = clmsComService.listDeptTree(vo);
		Organization orgArr[] = esbOrgService.listDeptTreeByCompCd(compCd, vo.getUp_dept_cd());
		Organization srchOrgArr[] = null;
		Organization rsltOrgArr[] = new Organization[]{};
		List userDeptList = new ArrayList();
		if((vo.getDept_cd() != null && !vo.getDept_cd().equals("")) || (vo.getDept_nm() != null && !vo.getDept_nm().equals(""))) {
//			userDeptList = clmsComService.userDeptTree(vo);
			
			srchOrgArr = esbOrgService.listDeptByDeptNm(compCd, vo.getDept_nm());
			
			if(srchOrgArr == null || srchOrgArr.length != 1 ) {
				returnMessage = messageSource.getMessage("secfw.msg.succ.noResult",  null, new RequestContext(request).getLocale());
			} else {
				Organization orgParents[] = esbOrgService.listDeptByDeptNo(compCd, srchOrgArr[0].getDepartmentnumber());
				
				while(!"TOP".equals(orgParents[0].getDepartmentnumber())) {
					ListOrderedMap lom = new ListOrderedMap();
					lom.put("dept_cd", orgParents[0].getDepartmentnumber());
					userDeptList.add(lom);
					orgParents = esbOrgService.listDeptByDeptNo(compCd, orgParents[0].getEphighdeptnumber());
				}
			}
		}
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName(forwardURL);
		
		mav.addObject("resultList", orgArr);
		mav.addObject("userDeptList", userDeptList);
		mav.addObject("command", form);
		mav.addObject("returnMessage", returnMessage);
		
		return mav;
		
	}
	
	/**
	 * ESB 인터페이스를 통해 부서 리스트를 반환한다.(해당부서의 하위부서만)
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void listDeptTreeAjaxEsb(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			HttpSession session = request.getSession(false);
			/*********************************************************
			 * 시스템  코드 및 사용자아이디
			**********************************************************/
			String compCd = StringUtil.bvl(session.getAttribute("secfw.session.comp_cd"), "");
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			CLMSCommonVO vo = new CLMSCommonVO();
			bind(request, vo);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * 하위부서 목록조회
			**********************************************************/
//			JSONArray jsonArray = clmsComService.listDeptTreeAjax(vo);
			Organization orgArr[] = esbOrgService.listDeptTreeByCompCd(compCd, vo.getUp_dept_cd());
			
			
			JSONArray jsonArray = new JSONArray();

			if(orgArr!=null && orgArr.length>0) {
				
				for(int i=0; i<orgArr.length; i++) {
					Organization org = orgArr[i];

					JSONObject jsonObject = new JSONObject();
					
					Locale lc = (Locale)localeResolver.resolveLocale(request);
					String locale = StringUtil.bvl(lc.getLanguage(), "en");
					
					jsonObject.put("dept_cd", org.getDepartmentnumber());
					jsonObject.put("dept_level", Integer.parseInt(org.getEpdeptlevel()));
					jsonObject.put("dept_order", Integer.parseInt(org.getEpdeptorder()));
					jsonObject.put("down_dept_yn", org.getEplowdept());
					jsonObject.put("up_dept_cd", org.getEphighdeptnumber());
					
					if("ko".equals(locale)) {
						jsonObject.put("dept_nm", org.getOu());
					} else {
						jsonObject.put("dept_nm", "UNKNOWN".equals(org.getEpendepartment().toUpperCase()) ? org.getOu() : org.getEpendepartment());
					}

					jsonArray.add(jsonObject);
				}
			}
			
			response.setContentType("application/json; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.print(jsonArray);
	        out.flush();
	        out.close();
		} catch (Exception e) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		jo.put("returnCnt", 0);

    		response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
			out.flush();
			out.close();
		}
	}
	
	/**
	 * ESB인터페이스를 통해 일부이름으로 부서명을 조회한다.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView searchDeptEsb(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession(false);
		
		String compCd = StringUtil.bvl(session.getAttribute("secfw.session.comp_cd"), "");
		/*********************************************************                                                                                     
		 * Forwarding URL                                                                                                                              
		**********************************************************/                                                                                    
		String forwardURL = "/WEB-INF/jsp/common/DeptSearchList.jsp";
		
		/*********************************************************                                                                                     
		 * bind Form, VO                                                                                                                              
		**********************************************************/
		CLMSCommonForm form = new CLMSCommonForm();
		CLMSCommonVO vo = new CLMSCommonVO();
		
		bind(request, form);
		bind(request, vo);
		
		COMUtil.getUserAuditInfo(request, vo);
		COMUtil.getUserAuditInfo(request, form);
		
		/*********************************************************                                                                                     
		 * 페이지 객체                                                                                                                                 
		**********************************************************/
		
		/*********************************************************                                                                                     
		 * 파라미터 세팅                                                                                                                                 
		**********************************************************/
		if(vo.getDept_nm() == null) {
			vo.setDept_nm("");
		} else {
			vo.setDept_nm(StringUtil.replace(vo.getDept_nm(), "*", ""));
		}
		
		/*********************************************************                                                                                   
		 * business logic                                                                                                                            
		**********************************************************/
		Organization orgArr[] = esbOrgService.listDeptByDeptNm(compCd, vo.getDept_nm());
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName(forwardURL);
		
		mav.addObject("resultList", orgArr);
		mav.addObject("command", form);
		
		return mav;
	}
	
	/**
	 * 15개 항목에 대해서 필수 항목을 설정 할 수 있는 리스트를 만든다.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView checkItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession(false);
		
		String compCd = StringUtil.bvl(session.getAttribute("secfw.session.comp_cd"), "");
		/*********************************************************                                                                                     
		 * Forwarding URL                                                                                                                              
		**********************************************************/                                                                                    
		String forwardURL = "/WEB-INF/jsp/common/CheckItemList.jsp";
		
		/*********************************************************                                                                                     
		 * bind Form, VO                                                                                                                              
		**********************************************************/
		CLMSCommonForm form = new CLMSCommonForm();
		CLMSCommonVO vo = new CLMSCommonVO();
		
		bind(request, form);
		bind(request, vo);
		
		COMUtil.getUserAuditInfo(request, vo);
		COMUtil.getUserAuditInfo(request, form);
		
		ModelAndView mav = new ModelAndView();
		
		int checkListSize = 0;
		
		/*********************************************************                                                                                     
		 * 페이지 객체                                                                                                                                 
		**********************************************************/
		
		/*********************************************************                                                                                     
		 * 파라미터 세팅                                                                                                                                 
		**********************************************************/
		vo.setComp_cd(compCd);
		
		/*********************************************************                                                                                   
		 * business logic                                                                                                                            
		**********************************************************/
		List checkList = clmsComService.checkList(vo);
		
		checkListSize = checkList.size();
		
		mav.setViewName(forwardURL);
		
		mav.addObject("checkList", checkList);
		mav.addObject("checkListSize", checkListSize);
		
		return mav;
	}
	
	/**
	 * 15개 항목에 대해서 필수 항목을 수정
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView modifyCheckItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession(false);
		
		String compCd = StringUtil.bvl(session.getAttribute("secfw.session.comp_cd"), "");
		/*********************************************************                                                                                     
		 * Forwarding URL                                                                                                                              
		**********************************************************/                                                                                    
		String forwardURL = "";
		
		/*********************************************************                                                                                     
		 * bind Form, VO                                                                                                                              
		**********************************************************/
		CLMSCommonForm form = new CLMSCommonForm();
		CLMSCommonVO vo = new CLMSCommonVO();
		
		bind(request, form);
		bind(request, vo);
		
		COMUtil.getUserAuditInfo(request, vo);
		COMUtil.getUserAuditInfo(request, form);
		
		ModelAndView mav = new ModelAndView();
		
		int checkListSize = 0;
		int modyResult    = 0;
		
		/*********************************************************                                                                                     
		 * 페이지 객체                                                                                                                                 
		**********************************************************/
		
		/*********************************************************                                                                                     
		 * 파라미터 세팅                                                                                                                                 
		**********************************************************/
		vo.setComp_cd(compCd);
		
		/*********************************************************                                                                                   
		 * business logic                                                                                                                            
		**********************************************************/
		modyResult = clmsComService.modifyCheckItem(vo);
		
		List checkList = clmsComService.checkList(vo);
		
		checkListSize = checkList.size();
		
		if(modyResult > 0){
			forwardURL = "/WEB-INF/jsp/common/CheckItemList.jsp";
		} else {
			forwardURL="/WEB-INF/jsp/secfw/error/ErrorBoard.jsp";
		}
		
		mav.setViewName(forwardURL);
		
		mav.addObject("checkList", checkList);
		mav.addObject("checkListSize", checkListSize);
		
		return mav;
	}
}
