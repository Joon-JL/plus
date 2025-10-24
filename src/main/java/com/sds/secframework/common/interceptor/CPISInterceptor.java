package com.sds.secframework.common.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.context.MessageSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.user.service.UserService;

public class CPISInterceptor extends HandlerInterceptorAdapter {


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

		boolean result = true;
		HttpSession session = request.getSession(false);
		
		if(session != null && !"".equals(StringUtil.bvl((String)session.getAttribute("secfw.session.user_id"),""))) {
		
			/*********************************************************
			 * 페이지사용현황 로그 - 시작
			**********************************************************/
			String menuLogFlag  = StringUtil.bvl(request.getParameter("menuLogEnable"),"Y");
			
			if("Y".equals(menuLogFlag)) {
				String sys_cd   = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"),"");
				String menu_id  = StringUtil.bvl((String)request.getParameter("menuId"),"");
				String user_id  = StringUtil.bvl((String)session.getAttribute("secfw.session.user_id"),"");
				String user_nm  = StringUtil.bvl((String)session.getAttribute("secfw.session.user_nm"),"");
				String dept_nm  = StringUtil.bvl((String)session.getAttribute("secfw.session.dept_nm"),"");
			
				if(menu_id != null && !menu_id.trim().equals("") ) {
		
					HashMap hm = new HashMap();
		
					hm.put("sys_cd", sys_cd);
					hm.put("menu_id", menu_id);
					hm.put("user_id", user_id);
					hm.put("user_nm", user_nm);
					hm.put("dept_nm", dept_nm);
					
					try {
						commonDAO.insert("secfw.log.insertPageUseLog", hm);
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			result = true;
			/*********************************************************
			 * 페이지사용현황 로그 - 끝
			**********************************************************/
		} else {

			response.sendRedirect("/WEB-INF/jsp/secfw/error/NoSession.jsp");
			result = false;
		}
		
		return result;

	}
	
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,	ModelAndView mav) throws Exception {
		// TODO Auto-generated method stub

		super.postHandle(request, response, handler, mav);
	}
}
