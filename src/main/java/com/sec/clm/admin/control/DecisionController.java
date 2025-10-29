/**
* Project Name : 계약관리시스템
* File Name : DecisionController.java
* Description : 전결규정 Controller
* Author : 신남원
* Date : 2010.09.01
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.admin.control;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;

import com.sec.clm.admin.dto.DecisionForm;
import com.sec.clm.admin.dto.DecisionVO;
import com.sec.clm.admin.service.DecisionService;
import com.sds.secframework.common.control.CommonController;

public class DecisionController extends CommonController {
	private DecisionService decisionService;
	public void setDecisionService(DecisionService decisionService) {
		this.decisionService = decisionService;
	}

	/**
	* 전결규정 목록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listDecision(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Forwarding URL
			**********************************************************/	
			String forwardURL = "";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			DecisionForm form = new DecisionForm();
			DecisionVO vo = new DecisionVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * 페이지 객체
			**********************************************************/
			PageUtil pageUtil = new PageUtil();

			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());

	        /*********************************************************
			 * JSP 반환변수
			**********************************************************/
			String returnMessage = "";

			List resultList = decisionService.listDecision(vo);

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

				pageUtil.setTotalRow(((BigDecimal)lom.get("total_cnt")).intValue());
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();

				if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
					returnMessage = (String)request.getAttribute("returnMessage");
				}
			}else {
				if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
					returnMessage = (String)request.getAttribute("returnMessage");
				}
			}

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	* 전결규정 목록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView forwardInsertDecision(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Forwarding URL
			**********************************************************/	
			String forwardURL = "";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			DecisionForm form = new DecisionForm();
			DecisionVO vo = new DecisionVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

	        /*********************************************************
			 * JSP 반환변수
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
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

	/**
	* 전결규정 목록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView insertDecision(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Forwarding URL
			**********************************************************/	
			String forwardURL = "";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			DecisionForm form = new DecisionForm();
			DecisionVO vo = new DecisionVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

	        /*********************************************************
			 * JSP 반환변수
			**********************************************************/
			int result = decisionService.insertDecision(vo);

			String returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	* 전결규정 목록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView detailDecision(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Forwarding URL
			**********************************************************/	
			String forwardURL = "";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			DecisionForm form = new DecisionForm();
			DecisionVO vo = new DecisionVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

	        /*********************************************************
			 * JSP 반환변수
			**********************************************************/
			ListOrderedMap lom = decisionService.detailDecision(vo);

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("lom", lom);
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

	/**
	* 전결규정 목록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView forwardModifyDecision(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Forwarding URL
			**********************************************************/	
			String forwardURL = "";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			DecisionForm form = new DecisionForm();
			DecisionVO vo = new DecisionVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

	        /*********************************************************
			 * JSP 반환변수
			**********************************************************/
			ListOrderedMap lom = decisionService.detailDecision(vo);

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("lom", lom);
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

	/**
	* 전결규정 목록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView modifyDecision(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Forwarding URL
			**********************************************************/	
			String forwardURL = "";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			DecisionForm form = new DecisionForm();
			DecisionVO vo = new DecisionVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

	        /*********************************************************
			 * JSP 반환변수
			**********************************************************/
			int result = decisionService.modifyDecision(vo);

			String returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("returnMessage", returnMessage);
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

	/**
	* 전결규정 목록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView deleteDecision(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Forwarding URL
			**********************************************************/	
			String forwardURL = "";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			DecisionForm form = new DecisionForm();
			DecisionVO vo = new DecisionVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

	        /*********************************************************
			 * JSP 반환변수
			**********************************************************/
			int result = decisionService.deleteDecision(vo);

			String returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale());

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("returnMessage", returnMessage);
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