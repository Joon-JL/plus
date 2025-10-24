/**
* Project Name : 계약관리시스템
* File Name : AboutClmManageController.java
* Description : About CLM 관리 Controller
* Author : dawn
* Date : 2010.10.05
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.admin.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.admin.dto.AboutClmManageForm;
import com.sec.clm.admin.dto.AboutClmManageVO;
import com.sec.clm.admin.service.AboutClmManageService;

public class AboutClmManageController extends CommonController {
	
	private AboutClmManageService aboutClmManageService;
	public void setAboutClmManageService(AboutClmManageService aboutClmManageService){
		this.aboutClmManageService = aboutClmManageService;
	}
	
	/**
	* about CLM 목록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listAboutClmManage(HttpServletRequest request, HttpServletResponse response)throws Exception {
		try{
			HttpSession session = request.getSession(false);
			
			String forwardURL = "/WEB-INF/jsp/clm/admin/AboutClmManage_l.jsp";//목록페이지로 이동.
			String compCd = StringUtil.bvl((String)session.getAttribute("secfw.session.comp_cd"), "");
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			AboutClmManageForm form = new AboutClmManageForm();
			AboutClmManageVO vo = new AboutClmManageVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			vo.setComp_cd(compCd);
			List resultList = aboutClmManageService.listAboutClmManage(vo);
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("resultList", resultList);
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
	
	/**
	* about CLM 관리 상세
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView detailAboutClmManage(HttpServletRequest request, HttpServletResponse response)throws Exception{
		try{
			
			HttpSession session = request.getSession(false);
			
			String compCd = StringUtil.bvl((String)session.getAttribute("secfw.session.comp_cd"), "");
			String forwardURL = "/WEB-INF/jsp/clm/admin/AboutClmManage_d.jsp";//상세페이지로 이동.
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			AboutClmManageForm form = new AboutClmManageForm();
			AboutClmManageVO vo = new AboutClmManageVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			vo.setComp_cd(compCd);
			ListOrderedMap lom = aboutClmManageService.detailAboutClmManage(vo);
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("lom", lom);
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
	
	/**
	* about CLM 관리 수정화면 호출
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView forwardModifyAboutClmManage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			
			HttpSession session = request.getSession(false);
			
			String compCd = StringUtil.bvl((String)session.getAttribute("secfw.session.comp_cd"), "");
			String forwardURL = "/WEB-INF/jsp/clm/admin/AboutClmManage_u.jsp";//수정페이지로 이동.
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			AboutClmManageForm form = new AboutClmManageForm();
			AboutClmManageVO vo = new AboutClmManageVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			vo.setComp_cd(compCd);
			ListOrderedMap lom = aboutClmManageService.detailAboutClmManage(vo);
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("lom", lom);
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
	
	/**
	* about CLM 관리 수정
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView modifyAboutClmManage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String forwardURL = "/clm/admin/aboutClmManage.do?method=detailAboutClmManage";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			AboutClmManageForm form = new AboutClmManageForm();
			AboutClmManageVO vo = new AboutClmManageVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			vo.setMod_id(form.getSession_user_id());
			vo.setMod_nm(form.getSession_user_nm());
			
			int result = aboutClmManageService.modifyAboutClmManage(vo);
			
			String returnMessage = "";
			
			if(result > 0){
				returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());
			}
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("returnMessage", returnMessage);
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
	
	
	/**
	* about CLM - Html 호출
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listAboutClmManageHtml(HttpServletRequest request, HttpServletResponse response)throws Exception {
		try{
			
			HttpSession session = request.getSession(false);
			
			String compCd = StringUtil.bvl((String)session.getAttribute("secfw.session.comp_cd"), "");
			String forwardURL = "/WEB-INF/jsp/clm/admin/AboutClmManageHtml_d.jsp";//상세페이지로 이동.
			String seqno = StringUtil.bvl((String)request.getParameter("seqno"), "");
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			AboutClmManageForm form = new AboutClmManageForm();
			AboutClmManageVO vo = new AboutClmManageVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			if(seqno != ""){
				form.setSeqNoHtml(seqno);
				vo.setSeqNoHtml(seqno);
				vo.setComp_cd(compCd);
			}
			
			ListOrderedMap lom = aboutClmManageService.listAboutClmManageHtml(vo);
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("lom", lom);
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
