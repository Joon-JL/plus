/**
* Project Name : 계약관리시스템
* File Name : FaqController.java
* Description : ADMIN FAQ Controller
* Author : 신남원
* Date : 2010.09.05
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.admin.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.admin.dto.FaqForm;
import com.sec.clm.admin.dto.FaqVO;
import com.sec.clm.admin.service.FaqService;

public class FaqController extends CommonController {
	private FaqService faqService;
	public void setFaqService(FaqService faqService) {
		this.faqService = faqService;
	}

	/**
	* ADMIN FAQ 목록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listFaq(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			FaqForm cmd = (FaqForm)request.getAttribute("command");
			
			/*********************************************************
			 * Forwarding URL
			**********************************************************/			
			String forwardURL = "/WEB-INF/jsp/clm/admin/Faq_l.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			FaqForm form = new FaqForm();
			FaqVO vo = new FaqVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			if(cmd != null){
				form = cmd;
				form.setSeqno(0);
				vo.setSeqno(0);
			}
			
			//데이터 초기화 해주기
			form.setSrch_cont_type(StringUtil.bvl(form.getSrch_cont_type(),""));
			vo.setSrch_cont_type(form.getSrch_cont_type());
			form.setSrch_keyword(StringUtil.bvl(form.getSrch_keyword(), ""));
			vo.setSrch_keyword(form.getSrch_keyword());
			form.setSrch_keyword_content(StringUtil.bvl(form.getSrch_keyword_content(), ""));
			vo.setSrch_keyword_content(form.getSrch_keyword_content());
			
			//vo.setAnnce_gbn("C01302");
			form.setMode("A");
			vo.setMode("A");
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

			/*********************************************************
			 * 검색처리
			**********************************************************/
			//XSS 처리
			vo.setSrch_keyword_content(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_keyword_content(), "").toUpperCase()));
			
			List resultList = faqService.listFaq(vo);

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

				pageUtil.setTotalRow(((Integer)lom.get("total_cnt")).intValue());
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();
			}
			
			if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
				returnMessage = (String)request.getAttribute("returnMessage");
			}
			
			//등록권한 설정
			boolean insertAuth = faqService.authFaq(INSERT, vo);
			form.setAuth_insert(insertAuth);
			
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
	* ADMIN FAQ 등록화면 호출
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView forwardInsertFaq(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Forwarding URL
			**********************************************************/			
			String forwardURL = "/WEB-INF/jsp/clm/admin/Faq_i.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			FaqForm form = new FaqForm();
			FaqVO vo = new FaqVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * JSP 반환변수
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			boolean insertAuth = faqService.authFaq(INSERT, vo);
			if(insertAuth){
				form.setAuth_insert(insertAuth);
				mav.setViewName(forwardURL);
				mav.addObject("command", form);
			}else{
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
				// 메시지처리 - 등록권한이 없습니다. 
		    	String alertTitle = messageSource.getMessage("secfw.page.field.alert.noInsertAuth", null, new RequestContext(request).getLocale());
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
	* ADMIN FAQ 저장
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView insertFaq(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			FaqForm form = new FaqForm();
			FaqVO vo = new FaqVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			//vo.setAnnce_gbn("C01302");
			vo.setReg_id(form.getSession_user_id());
			vo.setReg_nm(form.getSession_user_nm());
			
			/*********************************************************
			 * Insert 처리
			**********************************************************/	
			ModelAndView mav = new ModelAndView();
			
			boolean insertAuth = faqService.authFaq(INSERT, vo);
			if(insertAuth){
				//XSS처리
				vo.setTitle(StringUtil.convertHtmlTochars(form.getTitle()));
				vo.setRegman_jikgup_nm(vo.getSession_jikgup_nm());
				vo.setReg_dept_nm(vo.getSession_dept_nm());
				int seqNo = faqService.insertFaq(vo);
				form.setSeqno(seqNo);
				
				String returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());

				/*********************************************************
				 * Forwarding URL
				**********************************************************/	
				String forwardURL = "/clm/admin/faq.do?method=detailFaq";

				mav.setViewName(forwardURL);
				mav.addObject("command", form);
				mav.addObject("returnMessage", returnMessage);				
			}else{
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
				// 메시지처리 - 등록권한이 없습니다. 
		    	String alertTitle = messageSource.getMessage("secfw.page.field.alert.noInsertAuth", null, new RequestContext(request).getLocale());
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
	* ADMIN FAQ 상세화면 호출
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView detailFaq(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			FaqForm cmd = (FaqForm)request.getAttribute("command");
			
			/*********************************************************
			 * Forwarding URL
			**********************************************************/			
			String forwardURL = "/WEB-INF/jsp/clm/admin/Faq_d.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			FaqForm form = new FaqForm();
			FaqVO vo = new FaqVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			if(cmd != null){
				form = cmd;
				vo.setSeqno(form.getSeqno());
			}
			form.setMode("A");
			vo.setMode("A");
			/*********************************************************
			 * JSP 반환변수
			**********************************************************/			
			ModelAndView mav = new ModelAndView();
			
			boolean searchAuth = faqService.authFaq(SEARCH, vo);
			if(searchAuth){
				/*********************************************************
				 * 조회 처리
				**********************************************************/
				ListOrderedMap lom = faqService.detailFaq(vo);
	
				if(lom != null){
					//단위조직 리스트 조회
					List orgnzList = faqService.listOrgnz(vo);
					
					/*********************************************************
					 * 권한 처리
					**********************************************************/			
					boolean modifyAuth = faqService.authFaq(MODIFY, vo);
					boolean deleteAuth = faqService.authFaq(DELETE, vo);
					form.setAuth_modify(modifyAuth);
					form.setAuth_delete(deleteAuth);
		
					mav.setViewName(forwardURL);
					mav.addObject("lom", lom);
					mav.addObject("orgnzList", orgnzList);
					mav.addObject("command", form);
				}else{
					mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
		
					// 메시지처리 - 데이터가 존재하지 않습니다. 
			    	String alertTitle = messageSource.getMessage("secfw.page.field.alert.noDataInfo", null, new RequestContext(request).getLocale());
			    	// 메시지처리 - 시스템 관리자에게 문의하십시오.
			    	String alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());
					
					mav.addObject("secfw.alert.title", alertTitle);
					mav.addObject("secfw.alert.message", alertMessage);
				}					
			}else{
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
				// 메시지처리 - 수정권한이 없습니다. 
		    	String alertTitle = messageSource.getMessage("secfw.page.field.alert.noSearchAuth", null, new RequestContext(request).getLocale());
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
	* ADMIN FAQ 수정화면 호출
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView forwardModifyFaq(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Forwarding URL
			**********************************************************/			
			String forwardURL = "/WEB-INF/jsp/clm/admin/Faq_u.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			FaqForm form = new FaqForm();
			FaqVO vo = new FaqVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * JSP 반환변수
			**********************************************************/
			ListOrderedMap lom = faqService.detailFaq(vo);
			ModelAndView mav = new ModelAndView();
			
			if(lom != null){
				boolean modifyAuth = faqService.authFaq(MODIFY, vo);
				if(modifyAuth){
					//단위조직 리스트 조회
					List orgnzList = faqService.listOrgnz(vo);
					
					form.setAuth_modify(modifyAuth);
					
					mav.setViewName(forwardURL);
					mav.addObject("lom", lom);
					mav.addObject("orgnzList", orgnzList);
					mav.addObject("command", form);
				}else{
    				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
    				// 메시지처리 - 수정권한이 없습니다. 
    		    	String alertTitle = messageSource.getMessage("secfw.page.field.alert.noModifyAuth", null, new RequestContext(request).getLocale());
    		    	// 메시지처리 - 시스템 관리자에게 문의하십시오.
    		    	String alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());
    				mav.addObject("secfw.alert.title", alertTitle);
    				mav.addObject("secfw.alert.message", alertMessage);  					
				}
			}else{
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));

				// 메시지처리 - 데이터가 존재하지 않습니다. 
		    	String alertTitle = messageSource.getMessage("secfw.page.field.alert.noDataInfo", null, new RequestContext(request).getLocale());
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
	* ADMIN FAQ 수정
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView modifyFaq(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			FaqForm form = new FaqForm();
			FaqVO vo = new FaqVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * JSP 반환변수
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			boolean modifyAuth = faqService.authFaq(MODIFY, vo);
			if(modifyAuth){
				/*********************************************************
				 * Update 처리
				**********************************************************/
				//XSS처리
				vo.setTitle(StringUtil.convertHtmlTochars(form.getTitle()));
				
				vo.setMod_id(form.getSession_user_id());
				vo.setMod_nm(form.getSession_user_nm());
				
				int result = faqService.modifyFaq(vo);

				String returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());

				/*********************************************************
				 * Forwarding URL
				**********************************************************/			
				String forwardURL = "/clm/admin/faq.do?method=detailFaq";
				
				mav.setViewName(forwardURL);
				mav.addObject("returnMessage", returnMessage);
				mav.addObject("command", form);
			}else{
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
				// 메시지처리 - 수정권한이 없습니다. 
		    	String alertTitle = messageSource.getMessage("secfw.page.field.alert.noModifyAuth", null, new RequestContext(request).getLocale());
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
	* ADMIN FAQ 삭제
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView deleteFaq(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			FaqForm form = new FaqForm();
			FaqVO vo = new FaqVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * 권한체크에 따른 처리
			**********************************************************/				
			ModelAndView mav = new ModelAndView();
			
			boolean deleteAuth = faqService.authFaq(DELETE, vo);
			if(deleteAuth){
				/*********************************************************
				 * Delete 처리
				**********************************************************/
				vo.setDel_id(vo.getSession_user_id());
				vo.setDel_nm(vo.getSession_user_nm());
				
				int result = faqService.deleteFaq(vo);

				String returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale());

				/*********************************************************
				 * Forwarding 
				**********************************************************/			
				String forwardURL = "/clm/admin/faq.do?method=listFaq";
				
				mav.setViewName(forwardURL);
				mav.addObject("returnMessage", returnMessage);
				mav.addObject("command", form);
			}else{
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
				// 메시지처리 - 삭제권한이 없습니다. 
		    	String alertTitle = messageSource.getMessage("secfw.page.field.alert.noDeleteAuth", null, new RequestContext(request).getLocale());
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

}