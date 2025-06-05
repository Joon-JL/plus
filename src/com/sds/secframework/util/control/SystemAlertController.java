package com.sds.secframework.util.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.StringUtil;

public class SystemAlertController extends CommonController {

	/**
	 * System Alert페이지 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardSystemAlert(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	/**
    	 * 경고 페이지 정보를 가져온다.
    	 */
		String alertGbn     = StringUtil.bvl((String)request.getParameter("secfw.alertGbn"),(String)request.getAttribute("secfw.alertGbn"));
		String alertTitle   = "";
		String alertMessage = "";

		ModelAndView mav = new ModelAndView();
		mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
		
		if("noSession".equals(alertGbn)) {
			// 메시지처리 - 세션정보가 없습니다. 
	    	alertTitle = messageSource.getMessage("secfw.page.field.alert.noSession", null, new RequestContext(request).getLocale());
	    	// 메시지처리 - 시스템 관리자에게 문의하십시오.
	    	alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());
		} else if("noAuthTitle".equals(alertGbn)) {
			// 메시지처리 - 권한이 없습니다. 
	    	alertTitle = messageSource.getMessage("secfw.page.field.alert.noAuthTitle", null, new RequestContext(request).getLocale());
	    	// 메시지처리 - 시스템 관리자에게 문의하십시오.
	    	alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());
		
		} else {
			// 메시지처리 - 예외가 발생하였습니다. 
	    	alertTitle = messageSource.getMessage("secfw.page.field.alert.exceptionMessage", null, new RequestContext(request).getLocale());
	    	// 메시지처리 - 시스템 관리자에게 문의하십시오.
	    	alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());
		}

		mav.addObject("secfw.alert.title", alertTitle);
		mav.addObject("secfw.alert.message", alertMessage);
		
		return mav;	
		
	}
	
}
