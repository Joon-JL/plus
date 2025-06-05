/**
* Project Name : 계약관리시스템
* File Name : ChooseSealPersonController.java
* Description : 날인담당자선택팝업Controller
* Author : 심주완
* Date : 2011.09.29
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.manage.control;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.StringUtil;

import com.sec.clm.manage.dto.ChooseSealPersonForm;
import com.sec.clm.manage.dto.ChooseSealPersonVO;

import com.sec.clm.manage.service.ChooseSealPersonService;
import com.sec.common.util.ClmsDataUtil;
import com.sds.secframework.common.control.CommonController;

public class ChooseSealPersonController extends CommonController {
	private ChooseSealPersonService chooseSealPersonService;
	public void setChooseSealPersonService(ChooseSealPersonService chooseSealPersonService) {
		this.chooseSealPersonService = chooseSealPersonService;
	}
	
	/**
	* 날인담당자선택팝업
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView forwardChooseSealPersonPopup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			String forwardURL 		= "/WEB-INF/jsp/clm/manage/ChooseSealPerson_p.jsp";
			String returnMessage 	= "";
			List resultList			= null;
			ChooseSealPersonForm form 	= new ChooseSealPersonForm();		
			ChooseSealPersonVO vo 		= new ChooseSealPersonVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			if(vo.getSeal_ffmt_search_dept_cd() == null){
				if(vo.getSeal_ffmt_dept_cd()!= null && !"".equals(vo.getSeal_ffmt_dept_cd())){
					//2012-08-17 김형준 수정 : 이름으로 조회시 에러발생(원인은 기존에 부서도 선택해서 조회할 수 있었는데 없어졌나봄) 에러 안나게 수정
					//vo.setSeal_ffmt_search_dept_cd(vo.getSeal_ffmt_dept_cd());
					//form.setSeal_ffmt_search_dept_cd(vo.getSeal_ffmt_search_dept_cd());
					
					vo.setSeal_ffmt_search_dept_cd("*");
					form.setSeal_ffmt_search_dept_cd("*");
				} else {
					vo.setSeal_ffmt_search_dept_cd("*");
					form.setSeal_ffmt_search_dept_cd("*");
				}
			} 
			
			// 증명서류발급 담당 SC0102 / 날인담당 SC0101
			if(form.getSrch_type_gbn().equals("SC0102")){
				resultList = chooseSealPersonService.listChooseCertificatIssuer(vo);				
			} else{
				resultList = chooseSealPersonService.listChooseSealPerson(vo);
			}
			
			if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
				returnMessage = (String)request.getAttribute("returnMessage");
			}
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +forwardURL);
			mav.addObject("returnMessage", returnMessage);
			mav.addObject("command", form);
			mav.addObject("resultList", resultList);

			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 * 각 테이블들의 COMBO 리스트 값 리턴
	 * 계약유형/ 
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getSealDeptComboHTML(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			
			HttpSession session = request.getSession(false);
			
			/**
			 * Form 및 VO Binding
			 */
			HashMap hm = new HashMap();
			
			hm.put("SEARCH_DEPT_CD", StringUtil.bvl((String)request.getParameter("seal_ffmt_search_dept_cd"), ""));
			hm.put("LAST_LOCALE", new RequestContext(request).getLocale());
			/*
			 * 목록조회
			 */
			String result = chooseSealPersonService.getSealDeptComboHTML(hm);	
			
			response.setContentType("text/html; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(result);
    		out.flush();
    		out.close();
    		
		}catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		}catch (Throwable t) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
    		response.setContentType("text/html; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} 
	}
}	