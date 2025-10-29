package com.sds.secframework.util.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.sds.secframework.common.control.CommonController;

public class CalendarController extends CommonController {

	/**
	 * 달력팝업호출
	 *
	 * @param  
	 * @return Log
	 * @throws 
	*/	
	public ModelAndView goPopCalendar(HttpServletRequest request, HttpServletResponse response) throws Exception {


		try {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/WEB-INF/jsp/secfw/common/CalendarPopup.jsp");
			
			return mav;
        
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
}
