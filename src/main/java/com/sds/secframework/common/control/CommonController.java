package com.sds.secframework.common.control;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.web.servlet.LocaleResolver;

import anyframe.web.springmvc.controller.AnyframeFormController;

import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.util.ObjectCopyUtil;
import com.sds.secframework.log.service.LogService;

public class CommonController extends AnyframeFormController {

	protected static final String MODIFY = "modify";
	protected static final String SEARCH = "search";
	protected static final String DELETE = "delete";
	protected static final String INSERT = "insert";
	
	/**
	 * Locale 선언 및 세팅
	 */
	protected LocaleResolver localeResolver;
	public void setLocaleResolver(LocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
	}

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅 (Logging Service)
	 */
	protected LogService logService;
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	/**
	 * MessageSource 선언 및 세팅
	 */
	protected MessageSource messageSource;
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	/**
	 * PropertyService 선언 및 세팅
	 */
	protected PropertyService propertyService;
	public void setPropertyService(PropertyService propertyService) {
		this.propertyService = propertyService;
	}
	
	/**
	 * VO에 공통값 세팅 
	 * 
	 * @param vo 세팅할 VO
	 * @param request HttpServletRequest
	 */
	public void setCommonInfo(Object vo, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(false);
		
		if(session!=null) {
		
			// 시스템 설정값
			String sysCd = (String)session.getAttribute("secfw.session.sys_cd");
			String defaultLocale = propertyService.getProperty("secfw.defaultLocale") ;
			ObjectCopyUtil.setFieldValue(vo, "sys_cd", sysCd, String.class) ;
			ObjectCopyUtil.setFieldValue(vo, "defaultLocale", defaultLocale, String.class) ;
			
			// 관리자 인지 여부
			/*
			boolean isAdmin = false ;
			List userRoleList = (List) session.getAttribute("secfw.session.role");
			for(int i=0; i<userRoleList.size(); i++) {
				Map userRoleMap = (Map)userRoleList.get(i) ;
				String userRoleCd = (String) userRoleMap.get("role_cd");
				if(userRoleCd.equals("ADMIN")) {
					isAdmin = true ;
					break ;
				}
			}*/
					
			// 사용자 정보
			ObjectCopyUtil.setFieldValue(vo, "reg_id", (String)session.getAttribute("secfw.session.user_id"), String.class) ;
			//ObjectCopyUtil.setFieldValue(vo, "reg_nm", isAdmin ? "ADMIN" : (String)session.getAttribute("secfw.session.user_nm"), String.class) ; // 관리자인 경우에는 "ADMIN" 로 세팅
			ObjectCopyUtil.setFieldValue(vo, "reg_nm", (String)session.getAttribute("secfw.session.user_nm"), String.class) ; // 굳이 ADMIN으로 표시할 필요없어서 user_nm으로 표시
			ObjectCopyUtil.setFieldValue(vo, "reg_dept_nm", (String)session.getAttribute("secfw.session.dept_nm"), String.class) ;
			ObjectCopyUtil.setFieldValue(vo, "reg_jikgup_nm", (String)session.getAttribute("secfw.session.grade_nm"), String.class) ;
			
			ObjectCopyUtil.setFieldValue(vo, "mod_id", (String)session.getAttribute("secfw.session.user_id"), String.class) ;
			//ObjectCopyUtil.setFieldValue(vo, "mod_nm", isAdmin ? "ADMIN" : (String)session.getAttribute("secfw.session.user_nm"), String.class) ; // 관리자인 경우에는 "ADMIN" 로 세팅
			ObjectCopyUtil.setFieldValue(vo, "mod_nm", (String)session.getAttribute("secfw.session.user_nm"), String.class) ; // 굳이 ADMIN으로 표시할 필요없어서 user_nm으로 표시
			ObjectCopyUtil.setFieldValue(vo, "mod_dept_nm", (String)session.getAttribute("secfw.session.dept_nm"), String.class) ;
			ObjectCopyUtil.setFieldValue(vo, "mod_jikgup_nm", (String)session.getAttribute("secfw.session.grade_nm"), String.class) ;
			
			ObjectCopyUtil.setFieldValue(vo, "user_id", (String)session.getAttribute("secfw.session.user_id"), String.class) ;
		}
	}
}
