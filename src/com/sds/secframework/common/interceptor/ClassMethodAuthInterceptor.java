package com.sds.secframework.common.interceptor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.auth.dto.ClassMethodAuthVO;
import com.sds.secframework.auth.service.ClassMethodAuthService;
import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.menu.dto.MenuMngVO;
import com.sds.secframework.menu.dto.MenuVO;
import com.sds.secframework.menu.service.MenuService;

public class ClassMethodAuthInterceptor extends HandlerInterceptorAdapter {
	
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
	
	/**
	 * ClassMethodAuthService 선언 및 세팅
	 */
	private ClassMethodAuthService classMethodAuthService;
	public void setClassMethodAuthService(ClassMethodAuthService classMethodAuthService) {
		this.classMethodAuthService = classMethodAuthService;
	}
	
	/**
	 * MenuService 선언 및 세팅
	 */
	private MenuService menuService;
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public boolean preHandle(HttpServletRequest request,	HttpServletResponse response, Object handler) throws Exception {
		String classNm = handler.getClass().getName() ;
		String methodNm = request.getParameter("method") ;
		
		// 2013.06.18 해당 기능을 살리되, 권한 체크하는 부분은 주석 처리
		// 메뉴 체크
		//checkMenuAuth(request) ;
		
		// 메소드 체크
		checkClassAuthMethod(request, classNm, methodNm) ;
		
		return super.preHandle(request, response, handler);
	}
	
	/**
	 * 메뉴 체크 권한 체크를 한다.
	 * @param url
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	private void checkMenuAuth(HttpServletRequest request) throws Exception {
		Locale locale = new RequestContext(request).getLocale() ;
		HttpSession session = request.getSession(false) ;
		String userId = (String)session.getAttribute("secfw.session.user_id") ; 
		String sysCd = (String)session.getAttribute("secfw.session.sys_cd") ;
		String menuId = request.getParameter("menu_id") ;
		
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
	@SuppressWarnings("rawtypes")
	private void checkClassMethod(HttpServletRequest request, String classNm, String methodNm) throws Exception {
		Locale locale = new RequestContext(request).getLocale() ;
		HttpSession session = request.getSession(false) ;
		
		ClassMethodAuthVO vo = new ClassMethodAuthVO() ;
		vo.setClass_nm(classNm) ;
		vo.setMethod_nm(methodNm) ;
		
		List methodAuthList = commonDAO.list("shri.method.listAuth",vo) ;
		List roleAuthList = (List)session.getAttribute("secfw.session.role") ;
		
		boolean result = false ;
		
		// 체크여부가 Y 인 것만 체크
		if(methodAuthList!=null && methodAuthList.size()>0) {
			if(((Map)methodAuthList.get(0)).get("check_yn").equals("N")){
				result = true ;
			}
		}
		
		// 해당 메소드의 권한이 있는지 체크
		for(int i=0; !result && i<roleAuthList.size(); i++) {
			Map roleAuth = (Map)roleAuthList.get(i) ;
			for(int j=0; j<methodAuthList.size(); j++) {
				Map methodAuth = (Map)methodAuthList.get(j) ;
				if(roleAuth.get("role_cd").equals(methodAuth.get("role_cd"))){
					result = true ;
					break ;
				}
			}
		}
		
		// 권한이 없으면 에러페이지로 이동
		if(!result) {
			throwsModelAndViewDefiningException(propertyService.getProperty("secfw.url.alertPage"), 
					messageSource.getMessage("secfw.page.field.alert.noAuthTitle", null, locale), // 메시지처리 - 권한이 없습니다.
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void checkClassAuthMethod(HttpServletRequest request, String classNm, String methodNm) throws Exception {
		Locale locale = new RequestContext(request).getLocale() ;
		HttpSession session = request.getSession(false) ;
		
		//시스템코드 
		// 2014-01-31 Kevin. 시스템은 결국 LAS 값만 갖기 때문에 쿼리 할 필요 없음.
		final String SYSCD = "LAS";
		String sysCd = SYSCD; //StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
		//메뉴 아이디
		String menuId = request.getParameter("menu_id");
		
		ClassMethodAuthVO vo = new ClassMethodAuthVO() ;
		vo.setClass_nm(classNm);
		vo.setMethod_nm(methodNm);
		vo.setSys_cd(sysCd);
		
		// 2014-01-31 Kevin. 아래 로그 쿼리는 쓰이는 용도가 없고, 성능에 영향을 미쳐 주석 처리 함.
		/*
		boolean result = false ;
		
		//메소드 사용로그 남기기 Start!
		MenuVO mVo = new MenuVO();
		mVo.setSys_cd(sysCd);
		mVo.setMenu_id(menuId);
		
		Map menuDetailMap = (ListOrderedMap)menuService.getMenuDetail(mVo);
		
		HashMap hm = new HashMap();
		hm.put("sys_cd", sysCd);
		hm.put("menu_id", menuId);
		hm.put("user_id"	, StringUtil.bvl((String) session.getAttribute("secfw.session.user_id"), ""));
		hm.put("emp_no"		, StringUtil.bvl((String)session.getAttribute("secfw.session.emp_no"), ""));
		hm.put("comp_cd"	, StringUtil.bvl((String)session.getAttribute("secfw.session.comp_cd"), ""));
		hm.put("comp_nm"	, StringUtil.bvl((String)session.getAttribute("secfw.session.comp_nm"), ""));
		hm.put("dept_cd"	, StringUtil.bvl((String)session.getAttribute("secfw.session.dept_cd"), ""));
		hm.put("dept_nm"	, StringUtil.bvl((String)session.getAttribute("secfw.session.dept_nm"), ""));
		hm.put("jikgup_cd"	, StringUtil.bvl((String)session.getAttribute("secfw.session.grade_cd"), ""));
		hm.put("jikgup_nm"	, StringUtil.bvl((String)session.getAttribute("secfw.session.grade_nm"), ""));
		hm.put("user_nm"	, StringUtil.bvl((String)session.getAttribute("secfw.session.user_nm"), ""));
		hm.put("email"		, StringUtil.bvl((String)session.getAttribute("secfw.session.email"), ""));
		hm.put("single_id"	, StringUtil.bvl((String)session.getAttribute("secfw.session.single_id"), ""));
		
		if(menuDetailMap != null){
			hm.put("menu_nm"	, StringUtil.bvl((String)menuDetailMap.get("menu_nm"),""));
			hm.put("menu_url" 	, StringUtil.bvl((String)menuDetailMap.get("menu_url"),""));	
		}else{
			hm.put("menu_nm" 	, "");
			hm.put("menu_url" 	, "");
		}
		
		hm.put("class_nm"	, classNm);
		hm.put("method_nm"	, methodNm);
		
		Map paramMap = request.getParameterMap();
		Set paramKeySet = paramMap.keySet();
		Iterator keyIterator = paramKeySet.iterator();
		String parameter = "";
		
		while(keyIterator.hasNext()){
			String name = (String)keyIterator.next();
			String l_param = "";
			l_param = name + " : " ;
			
			String values[] = (String[])paramMap.get(name);
			for(int i=0; i<values.length; i++){
				l_param = l_param + values[i]+ " ";
				
				parameter = parameter + l_param;
			}
			parameter = parameter + "||";
			
		}
		hm.put("parameter", parameter);
		
		int resultVal = classMethodAuthService.insertLogData(hm);
		*/
		
		// 2013.06.18 해당 기능을 살리되, 권한 체크하는 부분은 주석 처리
		/*
		List methodAuthList = commonDAO.list("clms.method.listMethodAuth",vo);
		List userAuthList = (List)session.getAttribute("secfw.session.auth");
		
		// 저장이 제대로 이루어 지지 않을 경우
		if(resultVal < 0) {
			throwsModelAndViewDefiningException(propertyService.getProperty("secfw.url.alertPage"), 
					messageSource.getMessage("secfw.page.field.alert.exceptionMessage", null, locale), // 메시지처리 - 예외가 발생하였습니다.
					messageSource.getMessage("secfw.page.field.alert.adminMessage", null, locale)) ;// 메시지처리 - 관리자에게 문의하십시오.
		}
		//메소드 사용로그 남기기 End!
		
		
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
				if(userAuth.get("auth_cd").equals(methodAuth.get("auth_cd"))){
					result = true ;
					break ;
				}
			}
		}
		
		// 권한이 없으면 에러페이지로 이동
		if(!result) {
			throwsModelAndViewDefiningException(propertyService.getProperty("secfw.url.alertPage"), 
					messageSource.getMessage("secfw.page.field.alert.noAuthTitle", null, locale), // 메시지처리 - 권한이 없습니다.
					messageSource.getMessage("secfw.page.field.alert.adminMessage", null, locale)) ;// 메시지처리 - 관리자에게 문의하십시오.
		}
		*/
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
