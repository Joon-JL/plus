/**
* Project Name : 계약관리시스템
* File Name : ChooseContractTypeController.java
* Description : 계약유형선택팝업Controller
* Author : 심주완
* Date : 2011.09.25
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.manage.control;

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
import com.sds.secframework.common.util.StringUtil;

import com.sec.clm.manage.dto.ChooseContractTypeForm;
import com.sec.clm.manage.dto.ChooseContractTypeVO;

import com.sec.clm.manage.service.ChooseContractTypeService;
import com.sds.secframework.common.control.CommonController;

public class ChooseContractTypeController extends CommonController {
	private ChooseContractTypeService chooseContractTypeService;
	public void setChooseContractTypeService(ChooseContractTypeService chooseContractTypeService) {
		this.chooseContractTypeService = chooseContractTypeService;
	}
	
	/**
	* 계약유형팝업
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView forwardChooseContractTypePopup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			ChooseContractTypeForm form     = null;
			ChooseContractTypeVO   vo       = null;
			List   		resultList 			= null;
			List		resultList2 		= null;
			List		resultList3			= null;
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			//forwardURL = "/WEB-INF/jsp/clm/manage/ChooseContractType_p.jsp";
			forwardURL = "/WEB-INF/jsp/clm/manage/ChooseContractType_p_old.jsp";
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new ChooseContractTypeForm();
			vo   = new ChooseContractTypeVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = chooseContractTypeService.listChooseContractTypeBiz(vo);
			resultList2 = chooseContractTypeService.listChooseContractTypeDepth(vo);
			resultList3 = chooseContractTypeService.listChooseContractTypePurpose(vo);
			
			if (resultList != null && resultList.size() > 0) {
				// 메시지처리 - 정상적으로 조회되었습니다.
				returnMessage = (String)request.getAttribute("returnMessage");
				
			} else {
				
				// 메시지처리 - 조회된 결과가 없습니다.
               	returnMessage = (String)request.getAttribute("returnMessage");
			}
			
			if (resultList2 != null && resultList2.size() > 0) {
				// 메시지처리 - 정상적으로 조회되었습니다.
				returnMessage = (String)request.getAttribute("returnMessage");
				
			} else {
				// 메시지처리 - 조회된 결과가 없습니다.
               	returnMessage = (String)request.getAttribute("returnMessage");
			}
			
			if (resultList3 != null && resultList3.size() > 0) {
				// 메시지처리 - 정상적으로 조회되었습니다.
				returnMessage = (String)request.getAttribute("returnMessage");
				
			} else {
				// 메시지처리 - 조회된 결과가 없습니다.
               	returnMessage = (String)request.getAttribute("returnMessage");
			}
			
			//ArrayList arrList  = (ArrayList)resultList;
			//ArrayList arrList2 = (ArrayList)resultList2;
		   
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +forwardURL);
			mav.addObject("biz" , resultList);
			mav.addObject("depth", resultList2);
			mav.addObject("purpose" , resultList3);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ConsiderationController.forwardChooseContractTypePopup() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("ConsiderationController.forwardChooseContractTypePopup() Throwable !!");
		}
	}
	
	/**
	* 의뢰 경로 선택 팝업
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView forwardTncOrEcms(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			
			ChooseContractTypeForm form     = null;
			ChooseContractTypeVO   vo       = null;
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			//forwardURL = "/WEB-INF/jsp/clm/manage/ChooseContractType_p.jsp";
			forwardURL = "/WEB-INF/jsp/clm/manage/ChooseContractTncOrEcms_p.jsp";
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new ChooseContractTypeForm();
			vo   = new ChooseContractTypeVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			
		   
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +forwardURL);
			mav.addObject("command", form);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ConsiderationController.forwardChooseContractTypePopup() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("ConsiderationController.forwardChooseContractTypePopup() Throwable !!");
		}
	}
}	