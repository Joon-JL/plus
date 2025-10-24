package com.sds.secframework.common.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.user.service.UserService;

public class MenuInterceptor extends HandlerInterceptorAdapter {
	
	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */	
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
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
	 * Locale 선언 및 세팅
	 */
	private LocaleResolver localeResolver;
	public void setLocaleResolver(LocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
	}
	
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession(false);
		
		if (session != null && !"".equals(StringUtil.bvl((String)session.getAttribute("secfw.session.user_id"), ""))) {
			/*********************************************************
			 * 사용자 로케일 정보 변경
			**********************************************************/
			String changeLocale = StringUtil.bvl(request.getParameter("siteLanguage"), "");
			
			if (!"".equals(changeLocale)) {
				HashMap<String, String> localeMap = new HashMap<String, String>();
				localeMap.put("last_locale", changeLocale);
				
				userService.changeLocale(localeMap);
				
				Locale locale = new Locale(changeLocale);
				localeResolver.setLocale(request, response, locale);
			}
			
			/*********************************************************
			 * 메뉴사용현황 로그
			**********************************************************/
			String menuLogFlag = StringUtil.bvl(request.getParameter("menuLogEnable"), "");
			
			if ("Y".equals(menuLogFlag)) {
				String sys_cd  = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
				String menu_id = StringUtil.bvl((String)request.getParameter("menu_id"), "");
				String user_id = StringUtil.bvl((String)session.getAttribute("secfw.session.user_id"), "");
				String user_nm = StringUtil.bvl((String)session.getAttribute("secfw.session.user_nm"), "");
				String dept_nm = StringUtil.bvl((String)session.getAttribute("secfw.session.dept_nm"), "");
				
				if (menu_id != null && !menu_id.trim().equals("")) {
					HashMap<String, String> hm = new HashMap<String, String>();
					
					hm.put("sys_cd", sys_cd);
					hm.put("menu_id", menu_id);
					hm.put("user_id", user_id);
					hm.put("user_nm", user_nm);
					hm.put("dept_nm", dept_nm);
					
					try {
						// 메뉴사용현황 이력관리
						commonDAO.insert("secfw.log.insertPageUseLog", hm);
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		} 
		
		return true;
	}
	
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,	ModelAndView mav) throws Exception {
		// TODO Auto-generated method stub
		
		if (mav == null) {
			return;
		}
		
		HttpSession session = request.getSession(false);
		String sys_cd  = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"),"");
		String menu_id = StringUtil.bvl((String)request.getParameter("menu_id"),"");
		
		Locale lc = (Locale)localeResolver.resolveLocale(request);
		
		/***********************************************************
		 * 페이지 메뉴네비게이션 - 시작 ( 관리자 > 게시판 > 나의게시판)
		************************************************************/
		String language = StringUtil.bvl(lc.getLanguage(), propertyService.getProperty("secfw.defaultLocale"));
		
		if (!menu_id.trim().equals("")) {
			HashMap hm = new HashMap();
			
			hm.put("sys_cd", sys_cd);
			hm.put("menu_id", menu_id);
			hm.put("language", language);
			
			try {
				List menuNaviList = commonDAO.list("secfw.menu.getMenuNaviInPage", hm);
				String menuNavi = "";
				
				if (menuNaviList !=null && menuNaviList.size() > 0) {
					ListOrderedMap lom = (ListOrderedMap)menuNaviList.get(0);
					
					if ("ko".equals(language)) {
						menuNavi = (String)lom.get("menu_navi_kor");
					} else if ("zh".equals(language)) {
						menuNavi = (String)lom.get("menu_navi_cha");							
					} else if ("ja".equals(language)) {
						menuNavi = (String)lom.get("menu_navi_jpn");							
					} else if ("fr".equals(language)) {
						menuNavi = (String)lom.get("menu_navi_fra");							
					} else if ("de".equals(language)) {
						menuNavi = (String)lom.get("menu_navi_deu");							
					} else if ("it".equals(language)) {
						menuNavi = (String)lom.get("menu_navi_ita");							
					} else if ("es".equals(language)) {
						menuNavi = (String)lom.get("menu_navi_esp");							
					} else {
						menuNavi = (String)lom.get("menu_navi_eng");
					}
				}
				
				mav.addObject("menuNavi", "Home > " + menuNavi);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		super.postHandle(request, response, handler, mav);
	}
}
