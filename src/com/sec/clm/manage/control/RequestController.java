/**
* Project Name : 계약관리시스템
* File Name : RequestController.java
* Description : 계약권한요청 Controller
* Author : 신남원
* Date : 2010.09.27
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sec.clm.manage.dto.ManageVO;
import com.sec.clm.manage.dto.RequestForm;
import com.sec.clm.manage.dto.RequestVO;
import com.sec.clm.manage.service.RequestService;

public class RequestController extends CommonController {
	private ManageController manageControl;
	
	public void setManageControl(ManageController manageControl) {
		this.manageControl = manageControl;
	}
	
	private RequestService requestService;
	public void setRequestService(RequestService requestService) {
		this.requestService = requestService;
	}
	
	/**
	* 계약서 담당자 변경요청 목록
	* 공통목록 : 신남원 : 2011.10.04
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ModelAndView mav = new ModelAndView();
			request.setAttribute("status_mode", "request");
			mav = manageControl.listManage(request, response);

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
	* 계약서 담당자 변경 목록
	* 공통목록 : 최준영 : 2011.12.05
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listChange(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ModelAndView mav = new ModelAndView();
			request.setAttribute("status_mode", "change");
			mav = manageControl.listManage(request, response);

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
	* 계약권한요청 수정화면이동 수정/상세/승인
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView forwardModifyRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/		
			RequestForm form = new RequestForm();
			RequestVO vo = new RequestVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			String forwardURL = "";
			String returnMessage = "";
			
			ModelAndView mav = new ModelAndView();
			
			/*********************************************************
			 * JSP 반환변수
			**********************************************************/
			forwardURL  = "/WEB-INF/jsp/clm/manage/ManageRequest_u.jsp";
			
			//권한요청 정보
			ListOrderedMap authReqInfo = null;
			boolean authChk = false;
			
			getLogger().error(">>>>>>>>>>>>>prcs_depth="+form.getP_prcs_depth());
			getLogger().error(">>>>>>>>>>>>>demnd_gbn="+form.getP_demnd_gbn());

			//계약검토, 계약체결중인 데이터
			if("C02501".equals(form.getP_prcs_depth()) || "C02502".equals(form.getP_prcs_depth())){
				//신청가능
				if("D".equals(form.getP_demnd_gbn())){
					authReqInfo = requestService.detailRequest(vo);
					if(vo.getSession_user_id().equals((String)authReqInfo.get("demndman_id"))){
						authChk = true;
					}
				//신청
				}else if("E".equals(form.getP_demnd_gbn())){
					authReqInfo = requestService.detailAuthReqInfo(vo);
					if(vo.getSession_user_id().equals((String)authReqInfo.get("demndman_id"))){
						authChk = true;
					}
				//승인
				}else if("T".equals(form.getP_demnd_gbn())){
					authReqInfo = requestService.detailAuthReqInfo(vo);
					if(vo.getSession_user_id().equals((String)authReqInfo.get("trgtman_id"))){
						authChk = true;
					}
				}
			}else{
				//신청가능
				if("D".equals(form.getP_demnd_gbn())){
					authReqInfo = requestService.detailCntrt(vo);
					if(vo.getSession_user_id().equals((String)authReqInfo.get("demndman_id"))){
						authChk = true;
					}
				//신청
				}else if("E".equals(form.getP_demnd_gbn())){
					authReqInfo = requestService.detailAuthReqInfo(vo);
					if(vo.getSession_user_id().equals((String)authReqInfo.get("demndman_id"))){
						authChk = true;
					}
				//승인
				}else if("T".equals(form.getP_demnd_gbn())){
					authReqInfo = requestService.detailAuthReqInfo(vo);
					if(vo.getSession_user_id().equals((String)authReqInfo.get("trgtman_id"))){
						authChk = true;
					}
				}
			}
			
			if(authChk){	
				mav.setViewName(forwardURL);
				this.getLogger().debug("forwardURL: " +mav.getViewName());
				
				mav.addObject("authReqInfo", authReqInfo);
				mav.addObject("command", form);
			}else{
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
				this.getLogger().debug("forwardURL: " +mav.getViewName());
				// 메시지처리 - 페이지 권한이 없습니다.
		    	String alertTitle = messageSource.getMessage("secfw.page.field.alert.noAuthTitle", null, new RequestContext(request).getLocale());
		    	// 메시지처리 - 시스템 관리자에게 문의하십시오.
		    	String alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());
				mav.addObject("secfw.alert.title", alertTitle);
				mav.addObject("secfw.alert.message", alertMessage);
			}
			
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
	* 계약권한변경 화면이동
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView forwardModifyChange(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			HttpSession session = request.getSession(false);
			
			RequestForm form = new RequestForm();
			RequestVO vo = new RequestVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			String forwardURL = "";
			String returnMessage = "";
			
			ModelAndView mav = new ModelAndView();
			
			/*********************************************************
			 * JSP 반환변수
			**********************************************************/
			forwardURL  = "/WEB-INF/jsp/clm/manage/ManageChange_u.jsp";
			
			//권한요청 정보
			ListOrderedMap authReqInfo = null;
			boolean authChk = false;
			
			vo.setTop_role(getCntrtChangeAuth(vo));		
			
			ArrayList tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.roleList") ;
			/*
			//담당자변경 계약관리자,admin 인 경우는 모두 조회가능  
			if(tmpSessionRoleList.contains("RD01")){
				vo.setRequest_Yn("Y");	
			}else{
				vo.setRequest_Yn("N");
			}*/
			
			/*
			authChk = requestService.checkChangeAuth(vo);
			
			if(authChk){
				authReqInfo = requestService.detailChangeInfo(vo);
				
				mav.setViewName(forwardURL);
				this.getLogger().debug("forwardURL: " +mav.getViewName());
				
				mav.addObject("authReqInfo", authReqInfo);
				mav.addObject("command", form);
			}else{
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
				this.getLogger().debug("forwardURL: " +mav.getViewName());
				
				// 메시지처리 - 페이지 권한이 없습니다.
		    	String alertTitle = messageSource.getMessage("secfw.page.field.alert.noAuthTitle", null, new RequestContext(request).getLocale());
		    	// 메시지처리 - 시스템 관리자에게 문의하십시오.
		    	String alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());
				mav.addObject("secfw.alert.title", alertTitle);
				mav.addObject("secfw.alert.message", alertMessage);
			}*/
			
			authReqInfo = requestService.detailChangeInfo(vo);
			
			String s_user_id = (String)session.getAttribute("secfw.session.user_id");
			
			Boolean buttonAuth = false;
			
			if(s_user_id.equals(authReqInfo.get("cntrt_respman_id")) || tmpSessionRoleList.contains("RD01")){
				buttonAuth = true;
			}
			
			mav.setViewName(forwardURL);
			mav.addObject("authReqInfo", authReqInfo);
			mav.addObject("buttonAuth", buttonAuth);
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
	* 계약권한요청 요청
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView insertRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/	
			RequestForm form = new RequestForm();
			RequestVO vo = new RequestVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			form.setDemnd_gbn("C04602");
			vo.setDemnd_gbn(form.getDemnd_gbn());

			/*********************************************************
			 * JSP 반환변수
			**********************************************************/
			String forwardURL = "/clm/manage/request.do?method=listRequest";
			String returnMessage = messageSource.getMessage("secfw.msg.succ.request", null, new RequestContext(request).getLocale());
						
			requestService.insertRequest(vo);

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
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
	* 계약권한요청 승인
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView modifyRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/	
			RequestForm form = new RequestForm();
			RequestVO vo = new RequestVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			form.setDemnd_gbn("C04602");
			vo.setDemnd_gbn(form.getDemnd_gbn());
			
			vo.setHndlman_id(vo.getSession_user_id());
			vo.setHndlman_nm(vo.getSession_user_nm());
			/*********************************************************
			 * JSP 반환변수
			**********************************************************/
			String forwardURL = "/clm/manage/request.do?method=listRequest";
			String returnMessage = messageSource.getMessage("secfw.msg.succ.approve", null, new RequestContext(request).getLocale());
			
			requestService.modifyRequest(vo);

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
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
	* 계약서 담당자변경처리
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView modifyChange(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/	
			RequestForm form = new RequestForm();
			RequestVO vo = new RequestVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			form.setDemnd_gbn("C04602");
			vo.setDemnd_gbn(form.getDemnd_gbn());
			
			vo.setHndlman_id(vo.getSession_user_id());
			vo.setHndlman_nm(vo.getSession_user_nm());
			/*********************************************************
			 * JSP 반환변수
			**********************************************************/
			String forwardURL = "/clm/manage/request.do?method=listChange";
			
			int retCnt = requestService.modifyChange(vo);
			
			String returnMessage = "";
			if(retCnt == 0) {
				returnMessage = messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale());
			} else {
				returnMessage = messageSource.getMessage("secfw.msg.succ.process", null, new RequestContext(request).getLocale());
			}

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
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
	* 계약권한요청 취소
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView deleteRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/	
			RequestForm form = new RequestForm();
			RequestVO vo = new RequestVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			vo.setDel_id(form.getSession_user_id());
			vo.setDel_nm(form.getSession_user_nm());
			
			form.setDemnd_gbn("C04602");
			vo.setDemnd_gbn(form.getDemnd_gbn());
			/*********************************************************
			 * JSP 반환변수
			**********************************************************/
			String forwardURL = "/clm/manage/request.do?method=listRequest";
			String returnMessage = messageSource.getMessage("secfw.msg.succ.cancel", null, new RequestContext(request).getLocale());

			requestService.deleteRequest(vo);

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
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
	* 계약 담당자 변경을 할 수 있는 조회권한설정
	* 
	* @param RequestVO
	* @return List
	* @throws Exception
	*/
	private String getCntrtChangeAuth(RequestVO vo) throws Exception {

        /*
			시스템관리자        	RA00, AA00
			법무관리자          	RA01, AA01
			법무담당자          	RA02, AA02
			법무일반 사용자     	RA03, AA03
			cp관리자         RC01, AC01
			사업부계약관리자   RD01, AD01
			사업부계약담당자   RD02, AD02
			시스템운영자        	RM00, AM00
			일반임직원         	RZ00, AZ00

			A:모든것들 조회 
			B:본인등록건 + 자기지역담당건 + 자기한테 배정된 건
			C:본인등록건 + 자기한테 배정된 건          
			D:본인등록건 (아무권한 없는 일반임직원) 
        */

        String result = "";
        ArrayList roleList = vo.getSession_user_role_cds();
        
        ArrayList userRoleList = new ArrayList();

        if(roleList != null && roleList.size()>0){
        	for(int i=0; i < roleList.size() ;i++){
        		HashMap hm = (HashMap)roleList.get(i);
        		String roleCd = (String)hm.get("role_cd");
        		userRoleList.add(roleCd);
        	}
        }
        
        if(userRoleList != null && userRoleList.size()>0) {
            if(userRoleList.contains("RA00") || userRoleList.contains("RC01")) {	
                result = "A";
            } else if (userRoleList.contains("RA01") || userRoleList.contains("RA02") || userRoleList.contains("RA03")) {
                result = "B";
            } else if (userRoleList.contains("RD01")) {
                result = "C";
            } else {
                result = "D";
            }
        }
        return result;
    }
	
}