package com.sds.secframework.util.control;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.util.service.ComboService;

public class ComboController extends CommonController {

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private ComboService comboService;
	public void setComboService(ComboService comboService) {
		this.comboService = comboService;
	}

	/**
	 * 공통콤보
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void getCommonCodeCombo(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			/*********************************************************
			 * 시스템  코드
			**********************************************************/
	        String sysCd  = propertyService.getProperty("secfw.sysCd");
	
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
    		HashMap hm = new HashMap();
    		
    		hm.put("sys_cd",   sysCd);
    		hm.put("grp_cd",   StringUtil.bvl((String)request.getParameter("grp_cd"), ""));
    		hm.put("selected", StringUtil.bvl((String)request.getParameter("selected"), ""));
    		hm.put("use_yn",   StringUtil.bvl((String)request.getParameter("use_yn"), ""));
    		hm.put("language", StringUtil.bvl((String)request.getParameter("language"), ""));
    		hm.put("abbr",     StringUtil.bvl((String)request.getParameter("abbr"), ""));
	        
	        /*********************************************************
			 * 목록 조회
			**********************************************************/
    		String result = comboService.getCommonCodeCombo(hm);		
    		
    		response.setContentType("text/html; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(result);
    		out.flush();
    		out.close();
    		
		} catch (Exception e) {
    		String result = messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale());
    		
    		response.setContentType("text/html; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(result);
    		out.flush();
    		out.close();
		} catch (Throwable t) {
    		String result = messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale());
    		
    		response.setContentType("text/html; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(result);
    		out.flush();
    		out.close();
		} 
    }
	
}
