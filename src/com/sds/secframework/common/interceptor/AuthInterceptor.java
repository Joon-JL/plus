package com.sds.secframework.common.interceptor;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.menu.dto.MenuMngVO;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	
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
		// 메뉴 체크
		checkMenuAuth(request);
		
		return super.preHandle(request, response, handler);
	}
	
	/**
	 * 메뉴 체크 권한 체크를 한다.
	 * <p>
	 * @param request HttpServletRequest
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	private void checkMenuAuth(HttpServletRequest request) throws Exception {
		Locale locale = new RequestContext(request).getLocale() ;
		HttpSession session = request.getSession(false) ;
		String sysCd   = (String)session.getAttribute("secfw.session.sys_cd"); 
		String userId  = (String)session.getAttribute("secfw.session.user_id"); 
		String comp_cd = (String)session.getAttribute("secfw.session.comp_cd");
		String menuId  = request.getParameter("menu_id");
		
		// menuId == null 이면 정상적인 접근이 아님
		if (menuId == null) {
	    	throwsModelAndViewDefiningException(propertyService.getProperty("secfw.url.alertPage"), 
				messageSource.getMessage("secfw.page.field.alert.abnormal.approach", null, locale),// 메시지처리 - 비정상적인 접근입니다.
				messageSource.getMessage("secfw.page.field.alert.adminMessage", null, locale));    // 메시지처리 - 관리자에게 문의하십시오.
		}
		
		MenuMngVO menuVo = new MenuMngVO();
		menuVo.setSys_cd(sysCd);
		menuVo.setUser_id(userId);
		//menuVo.setUser_id(userId);
		menuVo.setMenu_id(menuId);
		menuVo.setComp_cd(comp_cd);
		
		// 해당사용자의 권한이 있는 메뉴 목록 조회
		List menuList = commonDAO.list("secfw.menu.listMenuUserAuth", menuVo) ;
		
		// 권한 없음
		if (menuList == null || menuList.size() == 0) {
			throwsModelAndViewDefiningException(propertyService.getProperty("secfw.url.alertPage"), 
				messageSource.getMessage("secfw.page.field.alert.noAuthTitle", null, locale),  // 메시지처리 - 페이지 권한이 없습니다.
				messageSource.getMessage("secfw.page.field.alert.adminMessage", null, locale));// 메시지처리 - 관리자에게 문의하십시오.
		} 
		
		// 사용되지 않는 페이지
		Map map = (Map)menuList.get(0);
		String useYN = (String)map.get("use_yn");
		if (StringUtil.nvl(useYN, "N").equals("N")) {
			throwsModelAndViewDefiningException(propertyService.getProperty("secfw.url.alertPage"), 
				messageSource.getMessage("secfw.page.field.alert.notUsePage", null, locale),   // 메시지처리 - 사용이 정지된 페이지 입니다.
				messageSource.getMessage("secfw.page.field.alert.adminMessage", null, locale));// 메시지처리 - 관리자에게 문의하십시오.
		}
	}
	
	/**
	 * 권한 체크를 통과하지 못했을 경우 에러 페이지로 넘기기 위한 Excetpion 을 발생
	 * <p>
	 * @param errorPage
	 * @param errorTitle
	 * @param errorMessage
	 * @throws Exception
	 */
	private void throwsModelAndViewDefiningException(String errorPage, String errorTitle, String errorMessage) throws Exception {
		ModelAndView mav = new ModelAndView() ; 
		mav.setViewName(errorPage);
		mav.addObject("secfw.alert.title", errorTitle);
		mav.addObject("secfw.alert.message", errorMessage);
		
		throw new ModelAndViewDefiningException(mav) ;
	}
}
