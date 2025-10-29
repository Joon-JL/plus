/**
* Project Name : 계약관리시스템
* File Name : ItemLibraryController.java
* Description : 조항 라이브러리 Controller
* Author : 신남원
* Date : 2010.08.31
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.draft.control;

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
import com.sec.clm.draft.dto.LibraryForm;
import com.sec.clm.draft.dto.LibraryVO;
import com.sec.clm.draft.service.ItemLibraryService;

public class ItemLibraryController extends CommonController {
	private ItemLibraryService itemLibraryService;
	public void setItemLibraryService(ItemLibraryService itemLibraryService) {
		this.itemLibraryService = itemLibraryService;
	}

	/**
	* 조항 라이브러리 목록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listItemLibrary(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			LibraryForm cmd = (LibraryForm)request.getAttribute("command");
			
			/*********************************************************
			 * Forwarding URL
			**********************************************************/			
			String forwardURL = "/WEB-INF/jsp/clm/draft/ItemLibrary_l.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			LibraryForm form = new LibraryForm();
			LibraryVO vo = new LibraryVO();
			
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

			vo.setLib_gbn("C03503");

			if(cmd != null){
				form = cmd;
				form.setLib_no(0);
				vo.setLib_no(0);
			}
			
			//최초 접속시 null -> '' 처리
			form.setSrch_biz_clsfcn(StringUtil.bvl(form.getSrch_biz_clsfcn(), ""));
			vo.setSrch_biz_clsfcn(form.getSrch_biz_clsfcn());
			form.setSrch_cnclsnpurps_bigclsfcn(StringUtil.bvl(form.getSrch_cnclsnpurps_bigclsfcn(), ""));
			vo.setSrch_cnclsnpurps_bigclsfcn(form.getSrch_cnclsnpurps_bigclsfcn());
			form.setSrch_cnclsnpurps_midclsfcn(StringUtil.bvl(form.getSrch_cnclsnpurps_midclsfcn(), ""));
			vo.setSrch_cnclsnpurps_midclsfcn(form.getSrch_cnclsnpurps_midclsfcn());
			form.setSrch_depth_clsfcn(StringUtil.bvl(form.getSrch_depth_clsfcn(), ""));
			vo.setSrch_depth_clsfcn(form.getSrch_depth_clsfcn());
			form.setSrch_lang_gbn(StringUtil.bvl(form.getSrch_lang_gbn(), ""));
			vo.setSrch_lang_gbn(form.getSrch_lang_gbn());
			
			//XSS 처리
			vo.setSrch_keyword_content(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_keyword_content(), "").toUpperCase()));
			
	        /*********************************************************
			 * JSP 반환변수
			**********************************************************/
			String returnMessage = "";

			/*********************************************************
			 * 검색처리
			**********************************************************/
			List resultList = itemLibraryService.listItemLibrary(vo);

			//조회 데이터 처리
			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

				pageUtil.setTotalRow(((Integer)lom.get("total_cnt")).intValue());
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();
			}
			
			//메세지 처리
			if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
				returnMessage = (String)request.getAttribute("returnMessage");
			}
			
			//등록권한 설정
			boolean insertAuth = itemLibraryService.authItemLibrary(INSERT, vo);
			form.setAuth_insert(insertAuth);
			/*********************************************************
			 * Return Value
			**********************************************************/
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
	* 조항 라이브러리 저장화면 호출
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView forwardInsertItemLibrary(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Forwarding URL
			**********************************************************/			
			String forwardURL = "/WEB-INF/jsp/clm/draft/ItemLibrary_i.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			LibraryForm form = new LibraryForm();
			LibraryVO vo = new LibraryVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			
			/*********************************************************
			 * JSP 반환변수
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			//등록권한 체크
			boolean insertAuth = itemLibraryService.authItemLibrary(INSERT, vo);
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
	* 조항 라이브러리 저장
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView insertItemLibrary(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			LibraryForm form = new LibraryForm();
			LibraryVO vo = new LibraryVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			vo.setLib_gbn("C03503");
			vo.setReg_id(form.getSession_user_id());
			vo.setReg_nm(form.getSession_user_nm());

			ModelAndView mav = new ModelAndView();
			
			//등록권한 체크
			boolean insertAuth = itemLibraryService.authItemLibrary(INSERT, vo);
			if(insertAuth){
				/*********************************************************
				 * Insert 처리
				**********************************************************/	
				//XSS 처리
				vo.setTitle(StringUtil.convertHtmlTochars(form.getTitle()));
				int libNo = itemLibraryService.insertItemLibrary(vo);
				form.setLib_no(libNo);
				
				String returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());

				/*********************************************************
				 * Forwarding URL
				**********************************************************/			
				String forwardURL = "/clm/draft/itemLibrary.do?method=detailItemLibrary";

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
	* 조항 라이브러리 상세화면
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView detailItemLibrary(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			LibraryForm cmd = (LibraryForm)request.getAttribute("command");
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			LibraryForm form = new LibraryForm();
			LibraryVO vo = new LibraryVO();
			
			bind(request, form);
			bind(request, vo);

			if(cmd != null){
				form = cmd;
				vo.setLib_no(form.getLib_no());
			}

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * JSP 반환변수
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			/*********************************************************
			 * 권한 처리
			**********************************************************/	
			boolean searchAuth = itemLibraryService.authItemLibrary(SEARCH, vo);
			if(searchAuth){
				/*********************************************************
				 * 조회 처리
				**********************************************************/			
				ListOrderedMap lom = itemLibraryService.detailItemLibrary(vo);
		
				if(lom != null){
					boolean modifyAuth = itemLibraryService.authItemLibrary(MODIFY, vo);
					boolean deleteAuth = itemLibraryService.authItemLibrary(DELETE, vo);
					form.setAuth_modify(modifyAuth);
					form.setAuth_delete(deleteAuth);
					/*********************************************************
					 * Forwarding URL
					**********************************************************/			
					String forwardURL = "/WEB-INF/jsp/clm/draft/ItemLibrary_d.jsp";
		
					mav.setViewName(forwardURL);
					mav.addObject("lom", lom);
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
				// 메시지처리 - 조회권한이 없습니다. 
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
	* 조항 라이브러리 수정화면 호출
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView forwardModifyItemLibrary(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			LibraryForm form = new LibraryForm();
			LibraryVO vo = new LibraryVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * Forwarding URL
			**********************************************************/			
			String forwardURL = "/WEB-INF/jsp/clm/draft/ItemLibrary_u.jsp";

			/*********************************************************
			 * JSP 반환변수
			**********************************************************/
			ListOrderedMap lom = itemLibraryService.detailItemLibrary(vo);
			ModelAndView mav = new ModelAndView();
			
			if(lom != null){
				boolean modifyAuth = itemLibraryService.authItemLibrary(MODIFY, vo);
				if(modifyAuth){
					form.setAuth_modify(modifyAuth);
					mav.setViewName(forwardURL);
					mav.addObject("lom", lom);
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

				// 메시지처리 - 사용자 정보가 없습니다. 
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
	* 조항 라이브러리 수정
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView modifyItemLibrary(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			LibraryForm form = new LibraryForm();
			LibraryVO vo = new LibraryVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * JSP 반환변수
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			boolean modifyAuth = itemLibraryService.authItemLibrary(MODIFY, vo);
			if(modifyAuth){
				/*********************************************************
				 * Update 처리
				**********************************************************/
				//XSS 처리
				vo.setTitle(StringUtil.convertHtmlTochars(form.getTitle()));
				
				vo.setLib_gbn("C03503");
				vo.setMod_id(vo.getSession_user_id());
				vo.setMod_nm(vo.getSession_user_nm());
				itemLibraryService.modifyItemLibrary(vo);
				String returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());

				/*********************************************************
				 * Forwarding URL
				**********************************************************/			
				String forwardURL = "/clm/draft/itemLibrary.do?method=detailItemLibrary";
				
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
	* 조항 라이브러리 삭제
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView deleteItemLibrary(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			LibraryForm form = new LibraryForm();
			LibraryVO vo = new LibraryVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * 권한체크에 따른 처리
			**********************************************************/					
			ModelAndView mav = new ModelAndView();

			boolean deleteAuth = itemLibraryService.authItemLibrary(DELETE, vo);
			if(deleteAuth){
				/*********************************************************
				 * Delete 처리
				**********************************************************/
				vo.setDel_id(vo.getSession_user_id());
				vo.setDel_nm(vo.getSession_user_nm());
				itemLibraryService.deleteItemLibrary(vo);

				String returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale());

				/*********************************************************
				 * Forwarding 
				**********************************************************/			
				String forwardURL = "/clm/draft/itemLibrary.do?method=listItemLibrary";
				
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