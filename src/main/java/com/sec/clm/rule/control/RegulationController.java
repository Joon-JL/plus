/**
* Project Name : 계약관리시스템
* File Name : RegulationController.java
* Description : 프로세스&규정지침 Controller
* Author : 신남원
* Date : 2010.08.29
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.rule.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.StringUtil;

import com.sec.clm.rule.dto.RegulationForm;
import com.sec.clm.rule.dto.RegulationVO;
import com.sec.clm.rule.service.RegulationService;
import com.sds.secframework.common.control.CommonController;

public class RegulationController extends CommonController {
	private RegulationService regulationService;
	public void setRegulationService(RegulationService regulationService) {
		this.regulationService = regulationService;
	}

	/**
	* 프로세스&규정지침 차트
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listRegulation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);

			/*********************************************************
			 * Forwarding URL
			**********************************************************/	
			String forwardURL = "/WEB-INF/jsp/clm/rule/Regulation_l.jsp";
			String srch_rule_no = StringUtil.bvl((String)request.getParameter("srch_rule_no"), "0");
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			RegulationForm form = new RegulationForm();
			RegulationVO vo = new RegulationVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			form.setSrch_rule_no(Integer.parseInt(srch_rule_no));//검색시넘어오는규정번호
			
			/*********************************************************
			 * 검색처리
			**********************************************************/
			List resultList = regulationService.listRegulation(vo);

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("resultList", resultList);
			mav.addObject("command", form);

			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
}