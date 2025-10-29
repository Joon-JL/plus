/**
* Project Name : 계약관리시스템
* File Name : ConculsionDelayController.java
* Description : 미체결사유Controller
* Author : 심주완
* Date : 2011.09.05
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.manage.control;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.PrintWriter;

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

import com.sec.clm.manage.dto.ConclusionDelayForm;
import com.sec.clm.manage.dto.ConclusionDelayVO;

import com.sec.clm.manage.service.ConclusionDelayService;
import com.sds.secframework.common.control.CommonController;

import net.sf.json.JSONObject;

public class ConclusionDelayController extends CommonController {
	private ConclusionDelayService conclusionDelayService;
	public void setConclusionDelayService(ConclusionDelayService conclusionDelayService) {
		this.conclusionDelayService = conclusionDelayService;
	}
	
	/**
	* 미체결사유 목록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listConclusionDelay(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
				/*********************************************************
				 * Parameter 
				**********************************************************/
				String forwardURL    		= "";
				String returnMessage 		= "";
				
				ConclusionDelayForm form   = null;
				ConclusionDelayVO   vo     = null;
				List   		resultList 	   = null;
				ListOrderedMap	delayLom   = null;
				String today               = "";
				/*********************************************************
				 * Session Check
				**********************************************************/	
				HttpSession session = request.getSession(false);
							
				/*********************************************************
				 * Forward setting
				**********************************************************/
				forwardURL = "/WEB-INF/jsp/clm/manage/ConclusionDelay_p.jsp";	
				
				/*********************************************************
				 * Form & VO Binding
				**********************************************************/	
				form 	= new ConclusionDelayForm();
				vo 		= new ConclusionDelayVO();
				
				bind(request, form);
				bind(request, vo);
				
				COMUtil.getUserAuditInfo(request, form);
				COMUtil.getUserAuditInfo(request, vo);

							
				/*********************************************************
				 * Service
				**********************************************************/
				resultList = conclusionDelayService.listConclusionDelay(vo);

				if (resultList != null && resultList.size() > 0) {
					delayLom = (ListOrderedMap)resultList.get(0);
					today = (String)delayLom.get("today");
					// 메시지처리 - 정상적으로 조회되었습니다.
					if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
						returnMessage = (String)request.getAttribute("returnMessage");
					}
				}else {
					// 메시지처리 - 조회된 결과가 없습니다.
	                if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
	                	returnMessage = (String)request.getAttribute("returnMessage");
	                } 
				}
				
				/*********************************************************
				 * ModelAndView
				**********************************************************/
				ModelAndView mav = new ModelAndView();
				
				mav.setViewName(forwardURL);
				this.getLogger().debug("forwardURL: " +forwardURL);
				
				mav.addObject("resultList", resultList);
				mav.addObject("delayLom", delayLom);
				mav.addObject("delayCommand", form);
				mav.addObject("today", today);
				mav.addObject("returnMessage", returnMessage);

				return mav;
				
			}catch (Exception e) {
				e.printStackTrace();
				throw new Exception("listExecution() Exception !!");
			}catch (Throwable t) {
				t.printStackTrace();
				throw new Exception("listExecution() Throwable !!");
			}
	}

	/**
	* 미체결사유 등록 및 수정
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public void insertConclusionDelay(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String forwardURL = "/WEB-INF/jsp/clm/manage/ConclusionDelay_ins_p.jsp";
			ConclusionDelayForm form 	= new ConclusionDelayForm();
			ConclusionDelayVO vo 		= new ConclusionDelayVO();
			
			bind(request, form);
			bind(request, vo);
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			int result = conclusionDelayService.insertConclusionDelay(vo);
			
			JSONObject jo = new JSONObject();
			jo.put("result", result);
			
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
    		
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jo = new JSONObject();
    		jo.put("errorMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
    		jo.put("errorMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} 
	}
	
}	