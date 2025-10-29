package com.sds.secframework.common.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.util.StringUtil;

public class AuthCheckInterceptor extends HandlerInterceptorAdapter {

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
		
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,	ModelAndView mav) throws Exception {
		
		HttpSession session = request.getSession(false);
		
		if(session != null && !"". equals(StringUtil.bvl((String)session.getAttribute("secfw.session.user_id"),""))) {

			String sys_cd  = propertyService.getProperty("secfw.sysCd");
			String user_id = StringUtil.bvl((String)session.getAttribute("secfw.session.user_id"),"");
			//String menu_id = StringUtil.bvl((String)request.getParameter("menu_id"),"");
			//Locale lc = (Locale)localeResolver.resolveLocale(request);
			
			/***********************************************************
			 * 페이지 권한체크 - 시작 
			************************************************************/
			boolean processFlag = false;
			
			String menuCheckType = propertyService.getProperty("secfw.auth.menuCheckType");
			StringTokenizer st = new StringTokenizer(menuCheckType, ",");
			
			String menu_url = mav.getViewName();
			String extention = StringUtil.bvl(menu_url.substring(menu_url.lastIndexOf(".")+1),"");
			
			while(st.hasMoreTokens()) {
				if(extention.equals(st.nextToken())) { 
					processFlag = true;
					break;
				}
			}
			
			if(processFlag) {
			
				HashMap authMap = new HashMap();
				authMap.put("sys_cd", sys_cd);
				authMap.put("user_id", user_id);
				authMap.put("menu_url", menu_url);
				
				List menuCheckList = commonDAO.list("secfw.auth.isAvailiableMenu", authMap);
				if(menuCheckList != null && menuCheckList.size()>0) {
					
					ListOrderedMap menuCheckListMap = (ListOrderedMap)menuCheckList.get(0);
					String useYn = (String)menuCheckListMap.get("use_yn");
					String authCheckYn = (String)menuCheckListMap.get("authcheck_yn");
					
					if("Y".equals(useYn)) { // 사용가능함
	
						if("Y".equals(authCheckYn)) { // 권한체크 필요
	
							List authList = commonDAO.list("secfw.auth.isGrantPage", authMap);
								
							if(authList != null && authList.size()>0) {
									
								ListOrderedMap authListMap = (ListOrderedMap)authList.get(0);
								String pageAuthYn = (String)authListMap.get("menu_auth_yn");
									
								if(!"Y".equals(pageAuthYn)) { // 권한없음
									
									String alertTitle = messageSource.getMessage("secfw.page.field.alert.noAuthTitle", null, new RequestContext(request).getLocale());
							    	String alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());
								    	
									mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
									mav.addObject("secfw.alert.title", alertTitle);
									mav.addObject("secfw.alert.message", alertMessage);
								} 							
							}
						} 
						
					} else if("N".equals(useYn)) { //사용불가
	
						// 메시지처리 - 사용정지된 페이지 입니다. 
				    	String alertTitle = messageSource.getMessage("secfw.page.field.alert.notUsePage", null, new RequestContext(request).getLocale());
				    	// 메시지처리 - 관리자에게 문의하십시오.
				    	String alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());
				    	
						mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
						
						mav.addObject("secfw.alert.title", alertTitle);
						mav.addObject("secfw.alert.message", alertMessage);
					}
					
				} else {
	//				// 메시지처리 - 등록되지 않은 페이지  입니다. 
	//		    	String alertTitle = messageSource.getMessage("secfw.page.field.alert.notRegisterPage", null, new RequestContext(request).getLocale());
	//		    	// 메시지처리 - 관리자에게 문의하십시오.
	//		    	String alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());
	//		    	
	//				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
	//				
	//				mav.addObject("secfw.alert.title", alertTitle);
	//				mav.addObject("secfw.alert.message", alertMessage);
					
				}
			
			}
		} else { //세션정보가 없을경우

			// 메시지처리 - 세션정보가 없습니다.<br>로그인후 사용하시기 바랍니다.
	    	String alertTitle = messageSource.getMessage("secfw.page.field.alert.noSession", null, new RequestContext(request).getLocale());
	    	// 메시지처리 - 관리자에게 문의하십시오.
	    	String alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());
	    	
			mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
			
			mav.addObject("secfw.alert.title", alertTitle);
			mav.addObject("secfw.alert.message", alertMessage);

		}
		
		super.postHandle(request, response, handler, mav);
	}
	
}
