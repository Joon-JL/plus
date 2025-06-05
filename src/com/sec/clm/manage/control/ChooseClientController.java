/**
* Project Name : 계약관리시스템
* File Name : ChooseClientController.java
* Description : 의뢰인선택팝업Controller
* Author : 심주완
* Date : 2011.09.25
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.manage.control;

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

import com.sec.clm.manage.dto.ChooseClientForm;
import com.sec.clm.manage.dto.ChooseClientVO;
import com.sec.clm.manage.dto.ConsiderationForm;
import com.sec.clm.manage.dto.ConsiderationVO;

import com.sec.clm.manage.service.ChooseClientService;
import com.sds.secframework.common.control.CommonController;

public class ChooseClientController extends CommonController {
	private ChooseClientService chooseClientService;
	public void setChooseClientService(ChooseClientService chooseClientService) {
		this.chooseClientService = chooseClientService;
	}
	
	/**
	* 계약팝업
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	//===================2011.08.31 심주완추가  의뢰인관리팝업페이지호출=========================
	public ModelAndView forwardChooseClientPopup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			String forwardURL 		= "/WEB-INF/jsp/clm/manage/ChooseClient_p.jsp";
			String returnMessage 	= "";
			List resultList			= null;
			ChooseClientForm form 	= new ChooseClientForm();		
			ChooseClientVO vo 		= new ChooseClientVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*resultList = chooseClientService.listChooseClient(vo);
			
			if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
				returnMessage = (String)request.getAttribute("returnMessage");
			}*/
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
}	