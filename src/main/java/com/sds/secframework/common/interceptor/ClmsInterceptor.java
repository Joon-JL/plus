package com.sds.secframework.common.interceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.util.StringUtil;

public class ClmsInterceptor extends HandlerInterceptorAdapter {
	
	/**
	 * CommonDAO에 대한 DAO선언 및 세팅
	 * @param commonDAO
	 */
	private CommonDAO commonDAO;
	public void setCommonDAO(CommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}

	/**
	 * MessageSource 선언 및 세팅
	 */
	private MessageSource messageSource;
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	/**
	 * PropertyService 선언 및 세팅
	 */
	private PropertyService propertyService;
	public void setPropertyService(PropertyService propertyService) {
		this.propertyService = propertyService;
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String classNm  = handler.getClass().getName();
		String methodNm = request.getParameter("method");
		String uri = request.getRequestURI();
		
		// session 체크
		checkSession(request);
		// login 체크
		checkLogin(request);

		// Quick bypass for your new development path
		if (uri.contains("/clm/admin/contract.do")) {
			System.out.println("Passing ClmsInterceptor...");
			return true;
		}
		// SYSTEM 체크
		checkSystem(request);
		
		return super.preHandle(request, response, handler);
	}
	
	/**
	 * Session 체크
	 * <p>
	 * @param request HttpServletRequest
	 * @throws Exception
	 */
	private void checkSession(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(false);
		Locale locale = new RequestContext(request).getLocale();
		
		if (session == null) {
			throwsModelAndViewDefiningException(propertyService.getProperty("secfw.url.alertPage"), 
				messageSource.getMessage("secfw.page.field.alert.noSession", null, locale), // 메시지처리 -  세션정보가 없습니다.<br>로그인후 사용하시기 바랍니다.
				messageSource.getMessage("secfw.page.field.alert.adminMessage", null, locale)) ;// 메시지처리 - 관리자에게 문의하십시오.
		}
	}
	
	/**
	 * 로그인 체크
	 * <p>
	 * @param request HttpServletRequest
	 * @throws Exception
	 */
	private void checkLogin(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(false);
		Locale locale = new RequestContext(request).getLocale();
		String userId = (String)session.getAttribute("secfw.session.user_id");
		
		if (userId == null) {
			throwsModelAndViewDefiningException(propertyService.getProperty("secfw.url.alertPage"), 
				messageSource.getMessage("secfw.page.field.alert.noLoing", null, locale), // 메시지처리 - 로그인 정보가 없습니다.<br>로그인후 사용하시기 바랍니다.
				messageSource.getMessage("secfw.page.field.alert.adminMessage", null, locale)) ;// 메시지처리 - 관리자에게 문의하십시오.
		}
	}
	
	/**
	 * 시스템 체크(계약관리인지 법무인지) - 도메인 1개에 서로 다른 시스템을 사용하기 위해
	 * menu_id 로 tb_com_menu에서 sys_cd를 조회한 후 세션 재세팅
	 * 조회한 sys_cd로 사용자 role도 조회 후 세션 재셋팅
	 * <p>
	 * @param request HttpServletRequest
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void checkSystem(HttpServletRequest request) throws Exception {
		Locale locale = new RequestContext(request).getLocale();
		HttpSession session = request.getSession(false);
		
		String sys_cd           = "";
		String menuId           = "";
		String flag             = StringUtil.bvl(request.getParameter("flag"), ""); //flag = C, L 이면 DB조회 없이 세션 재정의 한다.
		String menu_id          = StringUtil.bvl(request.getParameter("menu_id"), "");
		String target_menu_id   = StringUtil.bvl(request.getParameter("target_menu_id"), "");
		String selected_menu_id = StringUtil.bvl(request.getParameter("selected_menu_id"), "");
		
		// 2014-05-22 Kevin commented. Too many logs generated and bunch of logs on console are not helpful.
		/*System.out.println("[ClmsInterceptor.checkSystem] flag : " + flag);
		System.out.println("[ClmsInterceptor.checkSystem] menu_id : " + menu_id);
		System.out.println("[ClmsInterceptor.checkSystem] target_menu_id : " + target_menu_id);
		System.out.println("[ClmsInterceptor.checkSystem] selected_menu_id : " + selected_menu_id);*/
		
		String user_id      = (String)session.getAttribute("secfw.session.user_id");
		String resp_operdiv = StringUtil.bvl((String)session.getAttribute("secfw.session.resp_operdiv"), "");
		
		HashMap<String, String> hm = new HashMap<String, String>();
		
		if (!"".equals(target_menu_id)) {
			menuId = target_menu_id;
		} else if (!"".equals(menu_id)) {
			menuId = menu_id;
		} else if (!"".equals(selected_menu_id)) {
			menuId = selected_menu_id;
		}
		
		// sys_cd 조회
		if ("C".equals(flag) || "L".equals(flag)) {
			if ("C".equals(flag)) {
				sys_cd = "CLM";
				session.setAttribute("secfw.session.sys_cd", "CLM");
			} else {
				sys_cd = "LAS";
				session.setAttribute("secfw.session.sys_cd", "LAS");
			}			
		} else {
			hm.put("menu_id", menuId);
			
			// 해당 메뉴에 대한 시스템코드 조회
			List menuList = commonDAO.list("secfw.menu.listMenuUserMenuId", hm);
			
			if (menuList != null && menuList.size() > 0) {
				Map map = (Map)menuList.get(0) ;
				sys_cd = StringUtil.bvl((String)map.get("sys_cd"), "");
			}
			
			if ("".equals(StringUtil.bvl(sys_cd, ""))) {
				throwsModelAndViewDefiningException(propertyService.getProperty("secfw.url.alertPage"), 
						messageSource.getMessage("secfw.page.field.alert.noMenuIdInfo", null, locale), // 메시지처리 - 메뉴ID가 존재하지 않습니다.
						messageSource.getMessage("secfw.page.field.alert.adminMessage", null, locale));// 메시지처리 - 관리자에게 문의하십시오.
			}
			
			session.setAttribute("secfw.session.sys_cd", sys_cd);
		}
		
		if ("CLM".equals(session.getAttribute("secfw.session.sys_cd")) && (resp_operdiv.equals("C06099") || resp_operdiv.equals("C06299"))) {
			/*
			HashMap userInfoHm = new HashMap();
			userInfoHm.put("user_id", user_id);
			userInfoHm.put("sys_cd", StringUtil.bvl(session.getAttribute("secfw.session.sys_cd"),""));
			
			List userInfoList = commonDAO.list("secfw.user.getUserInfo", userInfoHm);
			
			if(userInfoList != null && userInfoList.size() > 0){
				for(int i=0;i<userInfoList.size();i++){
					
					ListOrderedMap userInfoLom = (ListOrderedMap)userInfoList.get(0);
					session.setAttribute("secfw.session.blngt_orgnz", StringUtil.bvl((String)userInfoLom.get("blngt_orgnz"),""));
				}
			}
			*/
			session.setAttribute("secfw.session.blngt_orgnz", StringUtil.bvl(session.getAttribute("secfw.session.clms_user_orgnz"),""));
		} else if ("LAS".equals(session.getAttribute("secfw.session.sys_cd"))) {
			if (resp_operdiv.equals("C06099")) { // 사업부담당자[국내]
				session.setAttribute("secfw.session.blngt_orgnz", "A00000001");
			} else if (resp_operdiv.equals("C06299")) { // 사업부담당자[해외]
				session.setAttribute("secfw.session.blngt_orgnz", "A00000002");
			}
		}
		
		/***********************************************************************************
		 * Role 정보 세팅
		 ***********************************************************************************/
		hm = new HashMap<String, String>();
		hm.put("sys_cd", sys_cd);
		hm.put("user_id", user_id);
		
		// 접속자 역할(Role) 조회
		List roleUserList = commonDAO.list("secfw.user.listUserRole", hm);
		
		ArrayList roleList = new ArrayList();

		if (roleUserList != null && roleUserList.size() > 0) {
			for (int i=0; i<roleUserList.size(); i++) {
				ListOrderedMap roleListLom = (ListOrderedMap)roleUserList.get(i);
				
				HashMap<String, String> roleMap = new HashMap<String, String>();
				roleMap.put("role_cd", (String)roleListLom.get("role_cd"));
				roleMap.put("role_nm", (String)roleListLom.get("role_nm"));
				
				roleList.add(roleMap);
			}
		}
		
		if (roleList == null) {
			throwsModelAndViewDefiningException(propertyService.getProperty("secfw.url.alertPage"), 
					messageSource.getMessage("secfw.page.field.alert.noRoleInfo", null, locale),   // 메시지처리 - 사용자의 역할 정보가 존재하지 않습니다.
					messageSource.getMessage("secfw.page.field.alert.adminMessage", null, locale));// 메시지처리 - 관리자에게 문의하십시오.
		}
		
		session.setAttribute("secfw.session.role",  roleList);
		
		/***********************************************************************************
		 * 권한정보 세팅
		 ***********************************************************************************/
		ArrayList authList = new ArrayList();
		
		// 접속자 권한(Auth) 조회
		List authUserList = commonDAO.list("secfw.user.listUserAuth", hm);
		
		if (authUserList != null && authUserList.size() > 0) {
			for (int i=0; i<authUserList.size(); i++) {
				ListOrderedMap authListLom = (ListOrderedMap)authUserList.get(i);
				
				HashMap<String, String> authMap = new HashMap<String, String>();
				authMap.put("auth_cd", (String)authListLom.get("auth_cd"));
				authMap.put("auth_nm", (String)authListLom.get("auth_nm"));
				
				authList.add(authMap);
			}
		}
		
		if (authList == null) {
			throwsModelAndViewDefiningException(propertyService.getProperty("secfw.url.alertPage"), 
				messageSource.getMessage("secfw.page.field.alert.noAuthInfo", null, locale),   // 메시지처리 - 사용자의 권한 정보가 존재하지 않습니다.
				messageSource.getMessage("secfw.page.field.alert.adminMessage", null, locale));// 메시지처리 - 관리자에게 문의하십시오.
		}
		
		session.setAttribute("secfw.session.auth",  authList);
	}
	
	/**
	 * 권한체크를 통과하지 못했을 경우 에러 페이지로 넘기기 위한 Excetpion을 발생
	 * <p>
	 * @param errorPage
	 * @param errorTitle
	 * @param errorMessage
	 * @throws Exception
	 */
	private void throwsModelAndViewDefiningException(String errorPage, String errorTitle, String errorMessage) throws Exception {
		ModelAndView mav = new ModelAndView(); 
		mav.setViewName(errorPage);
		mav.addObject("secfw.alert.title", errorTitle);
		mav.addObject("secfw.alert.message", errorMessage);
		
		throw new ModelAndViewDefiningException(mav);
	}
}
