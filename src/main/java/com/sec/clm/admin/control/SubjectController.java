/**
 * Project Name : 계약관리시스템
 * File name	: SubjectController.java
 * Description	: 계약분류상세관리  Controller
 * Author		: 한지훈
 * Date			: 2011. 08. 24
 * Copyright	:
 */
package com.sec.clm.admin.control;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.admin.dto.SubjectForm;
import com.sec.clm.admin.dto.SubjectVO;
import com.sec.clm.admin.service.SubjectService;
import com.sec.common.util.ClmsBoardUtil;

/**
 * Description	: Controller
 * Author		: 한지훈
 * Date			: 2011. 08. 24
 */
public class SubjectController extends CommonController {
	
	/**
	 * Subject 서비스
	 */
	public SubjectService subjectService;
	
	/**
	 * Subject 서비스 세팅
	 * 
	 * @param subjectService
	 * @return void
	 * @throws
	 */
	public void setSubjectService(SubjectService subjectService) {
		this.subjectService = subjectService;
	}
	
	/**
	 * 목록조회
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listSubject(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			SubjectForm form       = null;
			SubjectVO   vo         = null;
			PageUtil 	pageUtil   = null;
			List   		resultList = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/admin/Subject_l.jsp";
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new SubjectForm();
			vo   = new SubjectVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//Cross-site Scripting 방지
			form.setSrch_dimension(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_dimension(),"")));
			form.setCurPage(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getCurPage(),"")));
			
			/*********************************************************
			 * Page  setting
			**********************************************************/
			pageUtil = new PageUtil();
			
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			vo.setStart_index(pageUtil.getStartIndex());	//현재 페이지의 첫번째 게시물번호  set
			vo.setEnd_index(pageUtil.getEndIndex());		//현재 페이지의 마지막 게시물번호  set

			if(form.getCurPage() == null) {
				setInitFormVO(request, form, vo);
			}
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = subjectService.listSubject(vo);

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap tmp = (ListOrderedMap)resultList.get(0);

				pageUtil.setTotalRow(((BigDecimal)tmp.get("total_cnt")).intValue());
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();

				// 메시지처리 - 정상적으로 조회되었습니다.
				if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
					returnMessage = (String)request.getAttribute("returnMessage");
				}else {
					//메뉴 최초 진입 시 메세지 여부
					if("Y".equals(vo.getDoSearch()))
						returnMessage = messageSource.getMessage("secfw.msg.succ.search", null, new RequestContext(request).getLocale());
				}
			}else {
				// 메시지처리 - 조회된 결과가 없습니다.
                if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
                	returnMessage = (String)request.getAttribute("returnMessage");
                } else {
                	//메뉴 최초 진입 시 메세지 여부
                	if("Y".equals(vo.getDoSearch()))
                		returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, new RequestContext(request).getLocale());
                }
			}
			
			ArrayList lom = (ArrayList)resultList;

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			form.setReturn_message(returnMessage);
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName(forwardURL);

			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("lom", lom);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("SubjectController.listSubject() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("SubjectController.listSubject() Throwable !!");
		}
	}
	
	/**
	 * 등록 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardInsertSubject(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			
			SubjectForm form       = null;
			SubjectVO   vo         = null;

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);

			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/admin/Subject_i.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/			
			form = new SubjectForm();
			vo   = new SubjectVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("command", form);

			return mav; 
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("SubjectController.forwardInsertSubject() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("SubjectController.forwardInsertSubject() Throwable !!");
		}
	}
	
	/**
	 * 등록
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView insertSubject(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			SubjectForm form       = null;
			SubjectVO   vo         = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/clm/admin/subject.do?method=detailSubject";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new SubjectForm();
			vo   = new SubjectVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			String type_cd = subjectService.insertSubject(vo);
			
			form.setType_cd(type_cd);
			
			vo.setType_cd(type_cd);
			
			/*********************************************************
			 * Massage
			**********************************************************/
			// 메시지처리 - 등록되었습니다.
			returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);

			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("SubjectController.insertSubject() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("SubjectController.insertSubject() Throwable !!");
		}
	}
	
	/**
	 * 상세정보
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailSubject(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			SubjectForm form       = null;
			SubjectVO   vo         = null;
			List   	    resultList = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/admin/Subject_d.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new SubjectForm();
			vo   = new SubjectVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			//forward request 속성 값 가져오기...
			if(request.getAttribute("command")!=null) {
				SubjectForm command = (SubjectForm)request.getAttribute("command");
				
				form.setType_cd(command.getType_cd());
				
				vo.setType_cd(command.getType_cd());
			}

			// 수정권한 세팅
			form.setAuth_modify(ClmsBoardUtil.getDeleteModifyAuth(request, form.getReg_id())); //reg_id : 등록자 EPID
			// 삭제권한 세팅
			form.setAuth_delete(ClmsBoardUtil.getDeleteModifyAuth(request, form.getReg_id())); //reg_id : 등록자 EPID

			/*********************************************************
			 * Service
			**********************************************************/
			resultList = subjectService.detailSubject(vo);

			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			
			/*********************************************************
			 * Massage
			**********************************************************/
			returnMessage = StringUtil.bvl((String)request.getAttribute("returnMessage"), "");

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			
			mav.addObject("resultList", resultList);
			mav.addObject("lom", lom);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("SubjectController.detailSubject() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("SubjectController.detailSubject() Throwable !!");
		}
	}

	/**
	 * 수정 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardModifySubject(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			SubjectForm form       = null;
			SubjectVO   vo         = null;
			List   	   resultList = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/admin/Subject_u.jsp";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new SubjectForm();
			vo   = new SubjectVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = subjectService.detailSubject(vo);
			
			if(resultList==null)
				throw new Exception("##### queryService is null ##### ");
			
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			
			mav.addObject("resultList", resultList);
			mav.addObject("lom", lom);
			mav.addObject("command", form);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("SubjectController.forwardModifySubject() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("SubjectController.forwardModifySubject() Throwable !!");
		}
	}

	/**
	 * 수정 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView modifySubject(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			SubjectForm form     = null;
			SubjectVO   vo       = null;

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter
			**********************************************************/
			forwardURL = "/clm/admin/subject.do?method=detailSubject";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new SubjectForm();
			vo   = new SubjectVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			form.setMod_id(vo.getSession_user_id());
			form.setMod_nm(vo.getSession_user_nm());
			form.setMod_dept_nm(vo.getSession_user_nm());
			
			/*********************************************************
			 * Service
			**********************************************************/
			int result = subjectService.modifySubject(vo);
			
			/*********************************************************
			 * Massage
			**********************************************************/
			// 메시지처리 - 수정되었습니다.
			returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("SubjectController.modifySubject() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("SubjectController.modifySubject() Throwable !!");
		}
	}

	/**
	 * 삭제
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView deleteSubject(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			SubjectForm form       = null;
			SubjectVO   vo         = null;
			PageUtil 	pageUtil   = null;
			List   		resultList = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Parameter
			**********************************************************/
			forwardURL = "/clm/admin/subject.do?method=listSubject";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new SubjectForm();
			vo   = new SubjectVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			form.setMod_id(vo.getSession_user_id());
			form.setMod_nm(vo.getSession_user_nm());
			form.setMod_dept_nm(vo.getSession_user_nm());

			/*********************************************************
			 * Service
			**********************************************************/
			int result = subjectService.deleteSubject(vo);
			
			/*********************************************************
			 * Massage
			**********************************************************/
			// 메시지처리 - 삭제되었습니다.
			returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale());

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("SubjectController.deleteSubject() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("SubjectController.deleteSubject() Throwable !!");
		}
	}

	/**
	 * Form과 VO 검색조건 및 현재페이지 변수 초기화
	 * @param request
	 * @param form
	 * @param vo
	 * @throws Exception
	 */
	public void setInitFormVO(HttpServletRequest request, SubjectForm form, SubjectVO vo) throws Exception {
		form.setCurPage("1");
		vo.setCurPage("1");
	}
}