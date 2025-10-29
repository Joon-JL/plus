/**
* Project Name : 계약관리시스템
* File Name : NationController.java
* Description : 국가 검색 팝업 Controller
* Author : dawn
* Date : 2011.10.26
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.draft.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.draft.dto.NationForm;
import com.sec.clm.draft.dto.NationVO;
import com.sec.clm.draft.service.NationService;

public class NationController extends CommonController {
	private NationService nationService;
	
	public void setNationService(NationService nationService) {
		this.nationService = nationService;
	}
	
	/**
	* 국가 검색 화면 호출
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listNation(HttpServletRequest request, HttpServletResponse response)throws Exception{
		try{
			String forwardURL = "/WEB-INF/jsp/clm/draft/Nation_p.jsp";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			NationForm form = new NationForm();
			NationVO vo = new NationVO();
			
			bind(request, form);
			bind(request, vo);
	
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			form.setGu(StringUtil.bvl(form.getGu(), "A"));//tab 검색
			form.setTabVal(StringUtil.bvl(form.getTabVal(), "1"));//tab 순서
			form.setCnt(StringUtil.bvl(form.getCnt(), "N"));
			vo.setGu(StringUtil.bvl(form.getGu(), "A"));
			
			List nationList = null;
			
			if(!"".equals(form.getGu())){
				nationList = nationService.listNation(vo);
			}
			
			if(nationList != null && nationList.size() > 0 ){
				form.setCnt("Y");
			}
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("nationList", nationList);
			mav.addObject("command", form);
			
			return mav;
		
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Error");
		}catch(Throwable t){
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	
}
