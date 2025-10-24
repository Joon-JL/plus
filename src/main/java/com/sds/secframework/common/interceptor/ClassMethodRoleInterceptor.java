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

import com.sds.secframework.auth.dto.ClassMethodAuthVO;
import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.menu.dto.MenuMngVO;

public class ClassMethodRoleInterceptor extends HandlerInterceptorAdapter {
	
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

	public boolean preHandle(HttpServletRequest request,	HttpServletResponse response, Object handler) throws Exception {
		String classNm = handler.getClass().getName() ;
		String methodNm = request.getParameter("method") ;
//		String menuId = request.getParameter("menu_id") ;
//		
		// session 체크
		checkSession(request) ;
		
		// login 체크
		checkLogin(request) ;
		
		// 메뉴 체크
		checkMenuAuth(request) ;
		
		// 메소드 체크
		checkClassMethod(request, classNm, methodNm) ;
		
		return super.preHandle(request, response, handler);
	}
	
	/**
	 * Session 체크
	 * @param request
	 * @throws Exception
	 */
	private void checkSession(HttpServletRequest request)  throws Exception{
		HttpSession session = request.getSession(false) ;
		Locale locale = new RequestContext(request).getLocale() ;
		
		if(session==null) {
			throwsModelAndViewDefiningException(propertyService.getProperty("secfw.url.alertPage"), 
					messageSource.getMessage("secfw.page.field.alert.noSession", null, locale), // 메시지처리 -  세션정보가 없습니다.<br>로그인후 사용하시기 바랍니다.
					messageSource.getMessage("secfw.page.field.alert.adminMessage", null, locale)) ;// 메시지처리 - 관리자에게 문의하십시오.
		}
	}
	
	/**
	 * 로그인 체크
	 * @param request
	 * @throws Exception
	 */
	private void checkLogin(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(false) ;
		Locale locale = new RequestContext(request).getLocale() ;
		String userId = (String)session.getAttribute("secfw.session.user_id") ;
		
		if(userId==null) {
			throwsModelAndViewDefiningException(propertyService.getProperty("secfw.url.alertPage"), 
					messageSource.getMessage("secfw.page.field.alert.noLoing", null, locale), // 메시지처리 - 로그인 정보가 없습니다.<br>로그인후 사용하시기 바랍니다.
					messageSource.getMessage("secfw.page.field.alert.adminMessage", null, locale)) ;// 메시지처리 - 관리자에게 문의하십시오.
		}
	}
	
	/**
	 * 메뉴 체크 권한 체크를 한다.
	 * @param url
	 * @throws Exception
	 */
	private void checkMenuAuth(HttpServletRequest request) throws Exception {
		Locale locale = new RequestContext(request).getLocale() ;
		HttpSession session = request.getSession(false) ;
		String userId = (String)session.getAttribute("secfw.session.user_id") ; 
		String menuId = request.getParameter("menu_id") ;
		String sysCd = (String)session.getAttribute("secfw.session.sys_cd") ; 
		
		//menuId==null 이면 정상적인 접근이 아님
		if(menuId==null){
	    	throwsModelAndViewDefiningException(propertyService.getProperty("secfw.url.alertPage"), 
					messageSource.getMessage("secfw.page.field.alert.abnormal.approach", null, locale), // 메시지처리 - 비정상적인 접근입니다.
					messageSource.getMessage("secfw.page.field.alert.adminMessage", null, locale)) ;// 메시지처리 - 관리자에게 문의하십시오.
		}
				
		MenuMngVO menuVo = new MenuMngVO() ;
		menuVo.setSys_cd(sysCd) ;
		menuVo.setUser_id(userId) ;
		menuVo.setMenu_id(menuId) ;
		
		List menuList = commonDAO.list("secfw.menu.listMenuUserAuth", menuVo) ;
		
		//권한없음
		if(menuList==null || menuList.size()==0) {
			throwsModelAndViewDefiningException(propertyService.getProperty("secfw.url.alertPage"), 
					messageSource.getMessage("secfw.page.field.alert.noAuthTitle", null, locale), // 메시지처리 - 페이지 권한이 없습니다.
					messageSource.getMessage("secfw.page.field.alert.adminMessage", null, locale)) ;// 메시지처리 - 관리자에게 문의하십시오.
		} 
		
		// 사용되지 않는 페이지
		Map map = (Map)menuList.get(0) ;
		String useYN = (String)map.get("use_yn") ;
		if(StringUtil.nvl(useYN, "N").equals("N")){
			
			throwsModelAndViewDefiningException(propertyService.getProperty("secfw.url.alertPage"), 
					messageSource.getMessage("secfw.page.field.alert.notUsePage", null, locale), // 메시지처리 - 사용이 정지된 페이지 입니다.
					messageSource.getMessage("secfw.page.field.alert.adminMessage", null, locale)) ;// 메시지처리 - 관리자에게 문의하십시오.
		}
	}
	
	/**
	 * 해당 클래스의 메소드에 권한이 있는지 체크
	 * @param request HttpServletRequest
	 * @param classNm 클래스명(패키지 포함)
	 * @param methodNm 메소드명
	 * @throws Exception
	 */
	private void checkClassMethod(HttpServletRequest request, String classNm, String methodNm) throws Exception {
		Locale locale = new RequestContext(request).getLocale() ;
		HttpSession session = request.getSession(false) ;
		
		ClassMethodAuthVO vo = new ClassMethodAuthVO() ;
		vo.setClass_nm(classNm) ;
		vo.setMethod_nm(methodNm) ;
		
		List methodAuthList = commonDAO.list("shri.method.listAuth",vo) ;
		List userAuthList = (List)session.getAttribute("secfw.session.role") ;
		
		boolean result = false ;
		
		// 체크여부가 Y 인 것만 체크
		if(methodAuthList!=null && methodAuthList.size()>0) {
			if(((Map)methodAuthList.get(0)).get("check_yn").equals("N")){
				result = true ;
			}
		}
		
		// 해당 메소드의 권한이 있는지 체크
		for(int i=0; !result && i<userAuthList.size(); i++) {
			Map userAuth = (Map)userAuthList.get(i) ;
			for(int j=0; j<methodAuthList.size(); j++) {
				Map methodAuth = (Map)methodAuthList.get(j) ;
				if(userAuth.get("role_cd").equals(methodAuth.get("role_cd"))){
					result = true ;
					break ;
				}
			}
		}
		
		// 권한이 없으면 에러페이지로 이동
		if(!result) {
			throwsModelAndViewDefiningException(propertyService.getProperty("secfw.url.alertPage"), 
					messageSource.getMessage("secfw.page.field.alert.noAuth", null, locale), // 메시지처리 - 권한이 없습니다.
					messageSource.getMessage("secfw.page.field.alert.adminMessage", null, locale)) ;// 메시지처리 - 관리자에게 문의하십시오.
		}
	}
	
	/**
	 * 권한체크를 통과하지 못했을 경우 
	 * 에러 페이지로 넘기기 위한 Excetpion 을 발생
	 * @param errorPage
	 * @param errorTitle
	 * @param errorMessage
	 * @throws Exception
	 */
	private void throwsModelAndViewDefiningException(String errorPage, String errorTitle, String errorMessage) throws Exception{
		ModelAndView mav = new ModelAndView() ; 
		mav.setViewName(errorPage);
		mav.addObject("secfw.alert.title", errorTitle);
		mav.addObject("secfw.alert.message",  errorMessage);
		
		throw new ModelAndViewDefiningException(mav) ;
	}
	
}
