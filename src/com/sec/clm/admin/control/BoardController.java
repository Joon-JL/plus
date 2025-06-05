/**
* Project Name : 계약관리시스템
* File Name : BoardController.java
* Description : 공지사항 Controller
* Author : 신남원
* Date : 2010.09.01
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
import com.sec.clm.admin.dto.BoardForm;
import com.sec.clm.admin.dto.BoardVO;
import com.sec.clm.admin.service.BoardService;
import com.sec.common.util.ClmsBoardUtil;

public class BoardController extends CommonController {
	private BoardService boardService;
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	/**
	* 공지사항 목록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			BoardForm cmd = (BoardForm)request.getAttribute("command");
			//A:admin, M:일반(계약지식공유)
			String mode = StringUtil.bvl((String)request.getParameter("mode"), "");
			
			if(ClmsBoardUtil.searchRole(request, "RA00") || ClmsBoardUtil.searchRole(request, "RA01") || ClmsBoardUtil.searchRole(request, "RA02")){
				mode = "A";
			 }
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			BoardForm form = new BoardForm();
			BoardVO vo = new BoardVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			if(cmd != null){
				form = cmd;
				form.setSeqno(0);
				vo.setSeqno(0);
			}
			if(!"".equals(mode)){
				form.setMode(mode);
				vo.setMode(mode);
			}
			//logger.debug(">>>>>>>>>>>>>>"+vo.getMode());
			
			//데이터 초기화 해주기
			form.setSrch_reg_operdiv(StringUtil.bvl(form.getSrch_reg_operdiv(),""));
			vo.setSrch_reg_operdiv(form.getSrch_reg_operdiv());
			form.setSrch_start_dt(StringUtil.bvl(form.getSrch_start_dt(), ""));
			vo.setSrch_start_dt(StringUtil.bvl(form.getSrch_start_dt(), ""));
			form.setSrch_end_dt(StringUtil.bvl(form.getSrch_end_dt(), ""));
			vo.setSrch_end_dt(StringUtil.bvl(form.getSrch_end_dt(), ""));
			form.setSrch_keyword(StringUtil.bvl(form.getSrch_keyword(), ""));
			vo.setSrch_keyword(form.getSrch_keyword());
			form.setSrch_keyword_content(StringUtil.bvl(form.getSrch_keyword_content(), ""));
			vo.setSrch_keyword_content(form.getSrch_keyword_content());
			
			//vo.setAnnce_gbn("C01301");

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
			
			vo.setSrch_start_dt(form.getSrch_start_dt().replace("-", ""));
        	vo.setSrch_end_dt(form.getSrch_end_dt().replace("-", ""));
			
			List resultList = boardService.listBoard(vo);

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

				pageUtil.setTotalRow(((Integer)lom.get("total_cnt")).intValue());
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();
			}
			
			if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
				returnMessage = (String)request.getAttribute("returnMessage");
			}

			/*********************************************************
			 * Forwarding URL
			**********************************************************/			
			String forwardURL = "";
			if("M".equals(form.getMode())){
				 forwardURL = "/WEB-INF/jsp/clm/counsel/Board_l.jsp"; 
			}else{
				forwardURL = "/WEB-INF/jsp/clm/admin/Board_l.jsp";
				
				boolean insertAuth = boardService.authBoard(INSERT, vo);
				form.setAuth_insert(insertAuth);
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
	* 공지사항 등록화면 호출
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView forwardInsertBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Forwarding URL
			**********************************************************/			
			String forwardURL = "/WEB-INF/jsp/clm/admin/Board_i.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			BoardForm form = new BoardForm();
			BoardVO vo = new BoardVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * JSP 반환변수
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			boolean insertAuth = boardService.authBoard(INSERT, vo);
			
			if(insertAuth){
				getLogger().error(">>>>>>>>>>"+insertAuth);
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
	* 공지사항 저장
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView insertBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			BoardForm form = new BoardForm();
			BoardVO vo = new BoardVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			vo.setAnnce_gbn("C01301");
			vo.setReg_id(form.getSession_user_id());
			vo.setReg_nm(form.getSession_user_nm());
			
			form.setAnnce_startday(StringUtil.bvl(form.getAnnce_startday(), "00000000"));
			form.setAnnce_endday(StringUtil.bvl(form.getAnnce_endday(), "99999999"));
			/*********************************************************
			 * Insert 처리
			**********************************************************/	
			ModelAndView mav = new ModelAndView();
			
			boolean insertAuth = boardService.authBoard(INSERT, vo);
			
			if(insertAuth){
				//XSS처리
				vo.setTitle(StringUtil.convertHtmlTochars(form.getTitle()));

				if(!"".equals(form.getAnnce_startday())){
					vo.setAnnce_startday(form.getAnnce_startday().replace("-", ""));
				}			
				if(!"".equals(form.getAnnce_endday())){
					vo.setAnnce_endday(form.getAnnce_endday().replace("-", ""));
				}			
				
				vo.setRegman_jikgup_nm(vo.getSession_jikgup_nm());
				vo.setReg_dept_nm(vo.getSession_dept_nm());

				int seqNo = boardService.insertBoard(vo);
				form.setSeqno(seqNo);
				
				ListOrderedMap lom = boardService.detailBoard(vo);
				
				if(lom != null){
					/*********************************************************
					 * 권한 처리
					**********************************************************/			
					boolean modifyAuth = boardService.authBoard(MODIFY, vo);
					boolean deleteAuth = boardService.authBoard(DELETE, vo);
					form.setAuth_modify(modifyAuth);
					form.setAuth_delete(deleteAuth);
		
					List orgnzList = boardService.listOrgnz(vo);
					
					/*********************************************************
					 * Forwarding URL
					**********************************************************/	
					String forwardURL = "";
					if("M".equals(form.getMode())){
						forwardURL = "/WEB-INF/jsp/clm/counsel/Board_d.jsp";
					}else{
						forwardURL = "/WEB-INF/jsp/clm/admin/Board_d.jsp";
					}
					
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
				
				String returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());

				/*********************************************************
				 * Forwarding URL
				**********************************************************/	
				String forwardURL = "/clm/admin/board.do?method=listBoard";

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
	* 공지사항 상세화면 호출
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView detailBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			BoardForm cmd = (BoardForm)request.getAttribute("command");
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			BoardForm form = new BoardForm();
			BoardVO vo = new BoardVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			if(cmd != null){
				form = cmd;
				vo.setSeqno(form.getSeqno());
			}
			/*********************************************************
			 * JSP 반환변수
			**********************************************************/			
			ModelAndView mav = new ModelAndView();
			
			boolean searchAuth = boardService.authBoard(SEARCH, vo);
			if(searchAuth){
				/*********************************************************
				 * 조회 처리
				**********************************************************/
				ListOrderedMap lom = boardService.detailBoard(vo);

				if(lom != null){
					/*********************************************************
					 * 권한 처리
					**********************************************************/			
					boolean modifyAuth = boardService.authBoard(MODIFY, vo);
					boolean deleteAuth = boardService.authBoard(DELETE, vo);
					form.setAuth_modify(modifyAuth);
					form.setAuth_delete(deleteAuth);
		
					List orgnzList = boardService.listOrgnz(vo);
					
					/*********************************************************
					 * Forwarding URL
					**********************************************************/	
					String forwardURL = "";
					if("M".equals(form.getMode())){
						forwardURL = "/WEB-INF/jsp/clm/counsel/Board_d.jsp";
					}else{
						forwardURL = "/WEB-INF/jsp/clm/admin/Board_d.jsp";
					}
					
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
	* 공지사항 수정화면 호출
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView forwardModifyBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Forwarding URL
			**********************************************************/			
			String forwardURL = "/WEB-INF/jsp/clm/admin/Board_u.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			BoardForm form = new BoardForm();
			BoardVO vo = new BoardVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * JSP 반환변수
			**********************************************************/
			ListOrderedMap lom = boardService.detailBoard(vo);
			ModelAndView mav = new ModelAndView();
			
			if(lom != null){
				boolean modifyAuth = boardService.authBoard(MODIFY, vo);
				if(modifyAuth){
					//단위조직 리스트 조회
					List orgnzList = boardService.listOrgnz(vo);
					
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
	* 공지사항 수정
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView modifyBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			BoardForm form = new BoardForm();
			BoardVO vo = new BoardVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * JSP 반환변수
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			boolean modifyAuth = boardService.authBoard(MODIFY, vo);
			if(modifyAuth){
				/*********************************************************
				 * Update 처리
				**********************************************************/
				//XSS처리
				vo.setTitle(StringUtil.convertHtmlTochars(form.getTitle()));
				
				vo.setMod_id(form.getSession_user_id());
				vo.setMod_nm(form.getSession_user_nm());
				
				if("".equals(form.getAnnce_startday())){
					vo.setAnnce_startday("00000000");
					vo.setAnnce_endday("99999999");
				}else{
					vo.setAnnce_startday(form.getAnnce_startday().replace("-", ""));
					vo.setAnnce_endday(form.getAnnce_endday().replace("-", ""));
				}	

				int result = boardService.modifyBoard(vo);

				String returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());

				/*********************************************************
				 * Forwarding URL
				**********************************************************/			
//				String forwardURL = "/clm/admin/board.do?method=detailBoard";
				
//				mav.setViewName(forwardURL);
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

			return detailBoard(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	* 공지사항 삭제
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView deleteBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			BoardForm form = new BoardForm();
			BoardVO vo = new BoardVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * 권한체크에 따른 처리
			**********************************************************/				
			ModelAndView mav = new ModelAndView();
			
			boolean deleteAuth = boardService.authBoard(DELETE, vo);
			if(deleteAuth){
				/*********************************************************
				 * Delete 처리
				**********************************************************/
				vo.setDel_id(vo.getSession_user_id());
				vo.setDel_nm(vo.getSession_user_nm());
				
				int result = boardService.deleteBoard(vo);

				String returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale());

				/*********************************************************
				 * Forwarding 
				**********************************************************/			
				String forwardURL = "/clm/admin/board.do?method=listBoard";
				
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
	
	/**
	* 공지사항 상세화면 팝업호출(메인페이지에서)
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView detailBoardByMain(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			BoardForm form = new BoardForm();
			BoardVO vo = new BoardVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			vo.setSeqno(form.getSeqno());
			
			/*********************************************************
			 * JSP 반환변수
			**********************************************************/			
			ModelAndView mav = new ModelAndView();
			
			/*********************************************************
			* 조회 처리
			**********************************************************/
			ListOrderedMap lom = boardService.detailBoard(vo);
	
			/*********************************************************
			 * Forwarding URL
			**********************************************************/	
			String forwardURL = "/WEB-INF/jsp/clm/admin/Board_p.jsp";			
				
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

}