/**
 * Project Name : 법무시스템 이식
 * File name	: NoticeController.java
 * Description	: 게시판(법무공지/법원송달문서공지/시스템FAQ) Controller
 * Author		: 한지훈
 * Date			: 2011. 08
 * Copyright	: 2011 by SAMSUNG. All rights reserved.
 */
package com.sec.las.board.control;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.sec.las.board.dto.NoticeForm;
import com.sec.las.board.dto.NoticeVO;
import com.sec.las.board.service.NoticeService;

/**
 * Description	: 게시판(법무공지/법원송달문서공지/시스템FAQ) Controller
 * Author		: 한지훈
 * Date			: 2011. 08. 12
 */
public class NoticeController extends CommonController {
	
	/**
	 * Notice 서비스
	 */
	private NoticeService noticeService;
	
	/**
	 * Notice 서비스 세팅
	 * 
	 * @param noticeService
	 * @return void
	 * @throws
	 */
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	/**
	 * 법무공지 목록조회
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listLawNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return listNotice(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("NoticeController.listLawNotice() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.listLawNotice() Throwable !!");
		}
	}
	
	/**
	 * 법원송달문서공지 목록조회
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listDispatchNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return listNotice(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("NoticeController.listDispatchNotice() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.listDispatchNotice() Throwable !!");
		}
	}
	
	/**
	 * 시스템FAQ 목록조회
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listSysFaq(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return listNotice(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("NoticeController.listSysFaq() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.listSysFaq() Throwable !!");
		}
	}
	
	/**
	 * 법무공지 등록 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardInsertLawNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return forwardInsertNotice(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("NoticeController.forwardInsertLawNotice() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.forwardInsertLawNotice() Throwable !!");
		}
	}
	
	/**
	 * 법원송달문서공지 등록 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardInsertDispatchNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return forwardInsertNotice(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("NoticeController.forwardInsertDispatchNotice() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.forwardInsertDispatchNotice() Throwable !!");
		}
	}
	
	/**
	 * 시스템FAQ 등록 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardInsertSysFaq(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return forwardInsertNotice(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("NoticeController.forwardInsertSysFaq() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.forwardInsertSysFaq() Throwable !!");
		}
	}
	
	/**
	 * 법무공지 등록
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView insertLawNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String test="";
			return insertNotice(request, response, "detailLawNotice");
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("NoticeController.insertLawNotice() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.insertLawNotice() Throwable !!");
		}
	}
	
	/**
	 * 법원송달문서공지 등록
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView insertDispatchNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return insertNotice(request, response, "detailDispatchNotice");
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("NoticeController.insertDispatchNotice() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.insertDispatchNotice() Throwable !!");
		}
	}
	
	/**
	 * 시스템FAQ 등록
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView insertSysFaq(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return insertNotice(request, response, "detailSysFaq");
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("NoticeController.insertSysFaq() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.insertSysFaq() Throwable !!");
		}
	}

	/**
	 * 법무공지 상세정보
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailLawNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return detailNotice(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("NoticeController.detailLawNotice() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.detailLawNotice() Throwable !!");
		}
	}
	
	/**
	 * 법원송달문서공지 상세정보
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailDispatchNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return detailNotice(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("NoticeController.detailDispatchNotice() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.detailDispatchNotice() Throwable !!");
		}
	}
	
	/**
	 * 시스템FAQ 상세정보
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailSysFaq(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return detailNotice(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("NoticeController.detailSysFaq() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.detailSysFaq() Throwable !!");
		}
	}
	
	/**
	 * 법무공지 수정 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardModifyLawNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return forwardModifyNotice(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("NoticeController.forwardModifyLawNotice() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.forwardModifyLawNotice() Throwable !!");
		}
	}
	
	/**
	 * 법원송달문서공지 수정 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardModifyDispatchNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return forwardModifyNotice(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("NoticeController.forwardModifyDispatchNotice() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.forwardModifyDispatchNotice() Throwable !!");
		}
	}
	
	/**
	 * 시스템FAQ 수정 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardModifySysFaq(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return forwardModifyNotice(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("NoticeController.forwardModifySysFaq() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.forwardModifySysFaq() Throwable !!");
		}
	}

	/**
	 * 법무공지 수정
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView modifyLawNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return modifyNotice(request, response, "detailLawNotice");
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("NoticeController.modifyLawNotice() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.modifyLawNotice() Throwable !!");
		}
	}
	
	/**
	 * 법원송달문서공지 수정
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView modifyDispatchNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return modifyNotice(request, response, "detailDispatchNotice");
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("NoticeController.modifyDispatchNotice() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.modifyDispatchNotice() Throwable !!");
		}
	}
	
	/**
	 * 시스템FAQ 수정
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView modifySysFaq(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return modifyNotice(request, response, "detailSysFaq");
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("NoticeController.modifySysFaq() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.modifySysFaq() Throwable !!");
		}
	}

	/**
	 * 법무공지 삭제
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView deleteLawNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return deleteNotice(request, response, "listLawNotice");
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("NoticeController.deleteLawNotice() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.deleteLawNotice() Throwable !!");
		}
	}
	
	/**
	 * 법원송달문서공지 삭제
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView deleteDispatchNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return deleteNotice(request, response, "listDispatchNotice");
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("NoticeController.deleteDispatchNotice() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.deleteDispatchNotice() Throwable !!");
		}
	}
	
	/**
	 * 시스템FAQ 삭제
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView deleteSysFaq(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return deleteNotice(request, response, "listSysFaq");
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("NoticeController.deleteSysFaq() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.deleteSysFaq() Throwable !!");
		}
	}

	/**
	 * Notice 목록조회
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	private ModelAndView listNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			String accessLevel	 = "";
			
			NoticeForm form       = null;
			NoticeVO   vo         = null;
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
			forwardURL = "/WEB-INF/jsp/las/board/Notice_l.jsp";
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new NoticeForm();
			vo   = new NoticeVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			
			
			//Cross-site Scripting 방지
			form.setAnnce_knd(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getAnnce_knd(),"")));
			form.setSrch_start_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_start_dt(),"")));
			form.setSrch_end_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_end_dt(),"")));			
			form.setSrch_combo(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_combo(),"")));
			form.setSrch_word(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_word(),"")));
			form.setCurPage(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getCurPage(),"")));
			
			vo.setAnnce_knd(StringUtil.bvl(vo.getAnnce_knd(),""));
			vo.setSrch_start_dt(StringUtil.bvl(vo.getSrch_start_dt(),""));
			vo.setSrch_end_dt(StringUtil.bvl(vo.getSrch_end_dt(),""));
			vo.setSrch_combo(StringUtil.bvl(vo.getSrch_combo(),""));
			vo.setSrch_word(StringUtil.bvl(vo.getSrch_word(),""));
			
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
			resultList = noticeService.listNotice(vo);

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap tmp = (ListOrderedMap)resultList.get(0);

				pageUtil.setTotalRow(Integer.parseInt(String.valueOf(tmp.get("total_cnt"))));
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
             * 권한 처리
            **********************************************************/ 
			accessLevel = noticeService.checkAuthByRole(vo);
			if ("A".equals(accessLevel)){
				form.setAuth_insert(true);
			}

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
			throw new Exception("NoticeController.listNotice() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.listNotice() Throwable !!");
		}
	}
	
	/**
	 * Notice 등록폼 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	private ModelAndView forwardInsertNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			
			NoticeForm form       = null;
			NoticeVO   vo         = null;

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);

			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/las/board/Notice_i.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/			
			form = new NoticeForm();
			vo   = new NoticeVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			String isExistPopupYn = "N";
			List latestSeqnoList = noticeService.listNoticeLatestSeqno(vo);
			
			if(latestSeqnoList.size()>0)
				isExistPopupYn = "Y";
						
			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("isExistPopupYn", isExistPopupYn );

			return mav; 
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("NoticeController.forwardInsertNotice() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.forwardInsertNotice() Throwable !!");
		}
	}
	
	/**
	 * Notice 등록
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	private ModelAndView insertNotice(HttpServletRequest request, HttpServletResponse response, String divNotice) throws Exception {
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			NoticeForm form       = null;
			NoticeVO   vo         = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/las/board/notice.do?method="+divNotice;

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new NoticeForm();
			vo   = new NoticeVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//Cross-site Scripting 방지
			vo.setTitle(StringUtil.convertHtmlTochars(vo.getTitle_en()));
			vo.setTitle_en(StringUtil.convertHtmlTochars(vo.getTitle_en()));
			
			
			//cont에 들어있는 내용은 service단에서 나모파일 저장 & convert 후 저장
//			vo.setReg_id(vo.getSession_user_id());
//			vo.setReg_nm(vo.getSession_user_nm());
//			vo.setReg_dept(vo.getSession_dept_cd());
//			vo.setReg_dept_nm(vo.getSession_dept_nm());
			
			//세션값 한글깨짐 방지 
			vo.setReg_id(form.getSession_user_id());
			vo.setReg_nm(form.getSession_user_nm());
			vo.setReg_dept(form.getSession_dept_cd());
			vo.setReg_dept_nm(form.getSession_dept_nm());
			
			vo.setBody_mime_en(vo.getBody_mime_en());
			
			form.setAnnce_st_ymd(StringUtil.bvl(form.getAnnce_st_ymd(), "00000000"));
			form.setAnnce_ed_ymd(StringUtil.bvl(form.getAnnce_ed_ymd(), "99999999"));
			
			if(!"".equals(form.getAnnce_st_ymd())){
				vo.setAnnce_st_ymd(form.getAnnce_st_ymd().replace("-", ""));
			}			
			if(!"".equals(form.getAnnce_ed_ymd())){
				vo.setAnnce_ed_ymd(form.getAnnce_ed_ymd().replace("-", ""));
			}
			
			/*********************************************************
			 * Service
			**********************************************************/
			//새 게시글이 팝업공지일 경우, 기존팝업공지 게시글의 설정을 해제한다.
			if("Y".equals(vo.getPopup_yn())){
				noticeService.updateNoticePopupValue(vo);
			}
			
			String accessLevel = noticeService.checkAuthByRole(vo);
		    int seqno =0;
		    
		    if ("A".equals(accessLevel)){
		    	seqno = noticeService.insertNotice(vo);
		    }else{
		    	throw new Exception(messageSource.getMessage("secfw.page.field.alert.noAuth", null, new RequestContext(request).getLocale()));
		    }
			
			
			form.setSeqno(seqno);
			
			vo.setSeqno(seqno);
			
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
			throw new Exception("NoticeController.insertNotice() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.insertNotice() Throwable !!");
		}
	}
	
	/**
	 * Notice 상세정보 조회
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	private ModelAndView detailNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			NoticeForm cmd = (NoticeForm)request.getAttribute("command");
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			List resultList = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/las/board/Notice_d.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			NoticeForm form = new NoticeForm();
			NoticeVO   vo   = new NoticeVO();

			bind(request, form);
			bind(request, vo);

			if(cmd != null){
				form = cmd;
				vo.setAnnce_knd(form.getAnnce_knd());
				vo.setSeqno(form.getSeqno());
			}
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * Service
			**********************************************************/
			resultList = noticeService.detailNotice(vo);

			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			lom.put("cont", StringUtil.convertCharsToHtml((String)lom.get("cont")));
			if(resultList==null)
				throw new Exception("##### queryService is null ##### ");
			
			/*********************************************************
             * 권한 처리
            **********************************************************/ 
            boolean modifyAuth = noticeService.authNotice(MODIFY, vo);
            boolean deleteAuth = noticeService.authNotice(DELETE, vo);
			String accessLevel = noticeService.checkAuthByRole(vo);
			
			if ("A".equals(accessLevel)){
	            form.setAuth_modify(modifyAuth);
	            form.setAuth_delete(deleteAuth);
			}

			/*********************************************************
			 * 본인 글이 아닐 경우 조회수 증가
			**********************************************************/
			if(!vo.getSession_user_id().equals(lom.get("reg_id"))) {
				getLogger().debug("###### Increase Rdcnt #####");
				noticeService.increaseRdCnt(vo) ;				
			}
			
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
			throw new Exception("NoticeController.detailNotice() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.detailNotice() Throwable !!");
		}
	}

	/**
	 * Notice 수정폼 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	private ModelAndView forwardModifyNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			NoticeForm form       = null;
			NoticeVO   vo         = null;
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
			forwardURL = "/WEB-INF/jsp/las/board/Notice_u.jsp";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new NoticeForm();
			vo   = new NoticeVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			// 팝업공지 게시물 존재유무 조회
			String isExistPopupYn = "N";
			List latestSeqnoList = noticeService.listNoticeLatestSeqno(vo);
			
			if(latestSeqnoList.size()>0)
				isExistPopupYn = "Y";
			
			// 상세조회
			resultList = noticeService.detailNotice(vo);
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

			ModelAndView mav = new ModelAndView();
			
			if(lom != null){
				boolean auth = noticeService.authNotice(MODIFY, vo);
				String accessLevel = noticeService.checkAuthByRole(vo);
				
				if(auth && "A".equals(accessLevel)){
					mav.setViewName(forwardURL);
					mav.addObject("resultList", resultList);
					mav.addObject("lom", lom);
					mav.addObject("command", form);
					mav.addObject("isExistPopupYn", isExistPopupYn);
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
			throw new Exception("NoticeController.forwardModifyNotice() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.forwardModifyNotice() Throwable !!");
		}
	}

	/**
	 * Notice 수정 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	private ModelAndView modifyNotice(HttpServletRequest request, HttpServletResponse response, String divNotice) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			NoticeForm form       = null;
			NoticeVO   vo         = null;

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter
			**********************************************************/
			forwardURL = "/las/board/notice.do?method="+divNotice;

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new NoticeForm();
			vo   = new NoticeVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			ModelAndView mav = new ModelAndView();
			
			/*********************************************************
			 * 수정권한 Check
			**********************************************************/
			boolean auth = noticeService.authNotice(MODIFY, vo);
			String accessLevel = noticeService.checkAuthByRole(vo);
			
			/* !@# 공지기간 set */
			form.setAnnce_st_ymd(StringUtil.bvl(form.getAnnce_st_ymd(), "00000000"));
			form.setAnnce_ed_ymd(StringUtil.bvl(form.getAnnce_ed_ymd(), "99999999"));
			
			if(!"".equals(form.getAnnce_st_ymd())){
				vo.setAnnce_st_ymd(form.getAnnce_st_ymd().replace("-", ""));
			}			
			if(!"".equals(form.getAnnce_ed_ymd())){
				vo.setAnnce_ed_ymd(form.getAnnce_ed_ymd().replace("-", ""));
			}
			
			if(auth && "A".equals(accessLevel)){

//				vo.setMod_id(vo.getSession_user_id());
//				vo.setMod_nm(vo.getSession_user_nm());
//				
//				form.setMod_id(vo.getSession_user_id());
//				form.setMod_nm(vo.getSession_user_nm());
				
				vo.setMod_id(form.getSession_user_id());
				vo.setMod_nm(form.getSession_user_nm());
				
				vo.setTitle(StringUtil.convertHtmlTochars(vo.getTitle()));
				
				/*********************************************************
				 * Service
				**********************************************************/
				//새 게시글이 팝업공지일 경우, 기존팝업공지 게시글의 설정을 해제한다.
				if("Y".equals(vo.getPopup_yn())){
					if("SYS".equals(form.getSystem_notice_yn()))
						vo.setSystem_notice_yn("Y");
					
					noticeService.updateNoticePopupValue(vo);
				}
				
				int result = noticeService.modifyNotice(vo);
			
				/*********************************************************
				 * Massage
				**********************************************************/
				// 메시지처리 - 수정되었습니다.
				returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());
			
				/*********************************************************
				 * ModelAndView
				**********************************************************/
				mav.setViewName(forwardURL);
				mav.addObject("command", form);
				mav.addObject("returnMessage", returnMessage);

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
			throw new Exception("NoticeController.modifyNotice() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.modifyNotice() Throwable !!");
		}
	}

	/**
	 * Notice 삭제
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	private ModelAndView deleteNotice(HttpServletRequest request, HttpServletResponse response, String divNotice) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			NoticeForm form       = null;
			NoticeVO   vo         = null;
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
			forwardURL = "/las/board/notice.do?method="+divNotice;
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new NoticeForm();
			vo   = new NoticeVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * 삭제권한 Check
			**********************************************************/
			boolean auth = noticeService.authNotice(DELETE, vo);
			String accessLevel = noticeService.checkAuthByRole(vo);
			
			ModelAndView mav = new ModelAndView();
			
			if(auth && "A".equals(accessLevel)){
			
				vo.setDel_id(vo.getSession_user_id());
				vo.setDel_nm(vo.getSession_user_nm());
				
				form.setDel_id(vo.getSession_user_id());
				form.setDel_nm(vo.getSession_user_nm());
	
				/*********************************************************
				 * Service
				**********************************************************/
				int result = noticeService.deleteNotice(vo);
				
				/*********************************************************
				 * Massage
				**********************************************************/
				// 메시지처리 - 삭제되었습니다.
				returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale());
	
				/*********************************************************
				 * ModelAndView
				**********************************************************/
				mav.setViewName(forwardURL);
				mav.addObject("command", form);
				mav.addObject("returnMessage", returnMessage);

	       	}else {

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
			throw new Exception("NoticeController.deleteNotice() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.deleteNotice() Throwable !!");
		}
	}

	/**
	 * Form과 VO 검색조건 및 현재페이지 변수 초기화
	 * @param request
	 * @param form
	 * @param vo
	 * @return void
	 * @throws Exception
	 */
	private void setInitFormVO(HttpServletRequest request, NoticeForm form, NoticeVO vo) throws Exception {
		form.setCurPage("1");
		vo.setCurPage("1");
	}
	
	/**
	 * Notice 상세정보 조회 팝업(메인페이지)
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailNoticeByMain(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			List resultList = null;
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/las/board/Notice_p.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			NoticeForm form = new NoticeForm();
			NoticeVO   vo   = new NoticeVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			// MEMEO = 공지사항
			vo.setAnnce_knd("MEMO");
			
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = noticeService.detailNotice(vo);
			
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			lom.put("cont", StringUtil.convertCharsToHtml((String)lom.get("cont")));
			if(resultList==null)
				throw new Exception("##### queryService is null ##### ");
			
			/*********************************************************
			 * 본인 글이 아닐 경우 조회수 증가
			**********************************************************/
			if(!vo.getSession_user_id().equals(lom.get("reg_id"))) {
				getLogger().debug("###### Increase Rdcnt #####");
				noticeService.increaseRdCnt(vo) ;				
			}
			
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
			throw new Exception("NoticeController.detailNotice() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.detailNotice() Throwable !!");
		}
	}	
	
	/**
	 * 전결규정 입력/수정폼 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardInsertDecideArbitrarilyRe(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			NoticeForm form       = null;
			NoticeVO   vo         = null;
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
			forwardURL = "/WEB-INF/jsp/las/board/DecideArbitrarilyRe_i.jsp";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new NoticeForm();
			vo   = new NoticeVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			// ARBT : Decide Arbitrarily(전결규정)
			vo.setAnnce_knd("ARBT");
			form.setAnnce_knd("ARBT");
			
			/*********************************************************
			 * Service
			**********************************************************/
			// 해당 회사의 전결규정이 존재하는지 seqno 조회를 통해 구분
			List maxSeqno = noticeService.getMaxSeqNoByCompCd(vo);
			ListOrderedMap lomTmp = (ListOrderedMap)maxSeqno.get(0);
							
			// SEQNO가 0이면 INSERT
			if(Integer.parseInt(lomTmp.get("maxSeqno").toString()) > 0){
				vo.setSeqno(Integer.parseInt(lomTmp.get("maxSeqno").toString()));
				form.setSeqno(Integer.parseInt(lomTmp.get("maxSeqno").toString()));
			}
			
			
			resultList = noticeService.detailNotice(vo);
			
			ListOrderedMap 	lom 	= new ListOrderedMap(); 
			ModelAndView 	mav 	= new ModelAndView();
			
			if(resultList.size() > 0)
				lom = (ListOrderedMap)resultList.get(0);
						
			if(lom != null){
				
				Calendar cal = Calendar.getInstance();  
		        String dateString;  
		  
		        dateString = String.format("%04d-%02d-%02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));  
				
				if(lom.get("reg_nm") == null || lom.get("reg_nm").equals("")){
					lom.put("reg_nm", form.getSession_user_nm());
				}
				if(lom.get("reg_dt") == null || lom.get("reg_dt").equals("")){
					lom.put("reg_dt", dateString);
				}
//				lom.put("reg_nm", form.getSession_user_nm());
				boolean auth = noticeService.authNotice(MODIFY, vo);
				String accessLevel = noticeService.checkAuthByRole(vo);
				
				if(auth && "A".equals(accessLevel)){
					mav.setViewName(forwardURL);
					mav.addObject("resultList", resultList);
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
			throw new Exception("NoticeController.forwardModifyNotice() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.forwardModifyNotice() Throwable !!");
		}
	}
	
	/**
	 * 전결규정 입력/수정 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	
	public ModelAndView updateDecideArbitrarilyRe(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			NoticeForm form       = null;
			NoticeVO   vo         = null;

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter
			**********************************************************/
			//forwardURL = "/secfw/main.do?method=forwardPage&forward_url=/WEB-INF/jsp/clm/rule/Arbitrary_l.jsp";
			forwardURL = "/las/board/notice.do?method=detailDecideArbitrarilyRe";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new NoticeForm();
			vo   = new NoticeVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			ModelAndView mav = new ModelAndView();
			
			/*********************************************************
			 * 수정권한 Check
			**********************************************************/
			boolean auth = noticeService.authNotice(MODIFY, vo);
			String accessLevel = noticeService.checkAuthByRole(vo);
			
			if(auth && "A".equals(accessLevel)){
				

				vo.setMod_id(vo.getSession_user_id());
				vo.setMod_nm(form.getSession_user_nm());
				
				form.setMod_id(vo.getSession_user_id());
				form.setMod_nm(form.getSession_user_nm());
				
				vo.setTitle(StringUtil.convertHtmlTochars(vo.getTitle()));
				
				/*********************************************************
				 * Service
				**********************************************************/
				int result;
				
				// 해당 회사의 전결규정이 존재하는지 seqno 조회를 통해 구분
				List resultList = noticeService.getMaxSeqNoByCompCd(vo);
				ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
								
				// 해당 회사의 전결규정이 존재하면 UPDATE 
				// SEQNO가 0이면 INSERT
				if(Integer.parseInt(lom.get("maxSeqno").toString()) > 0){
					vo.setSeqno(Integer.parseInt(lom.get("maxSeqno").toString()));
					
					
					
					result = noticeService.modifyNotice(vo);
				}else{
					insertNotice(request, response, "detailDecideArbitrarilyRe");
				}
				
			
				/*********************************************************
				 * Massage
				**********************************************************/
				// 메시지처리 - 수정되었습니다.
				returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());
			
				/*********************************************************
				 * ModelAndView
				**********************************************************/
				mav.setViewName(forwardURL);
				mav.addObject("command", form);
				mav.addObject("returnMessage", returnMessage);

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
			throw new Exception("NoticeController.modifyNotice() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.modifyNotice() Throwable !!");
		}
	}
	
	/**
	 * 전결규정 상세정보 조회
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailDecideArbitrarilyRe(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			NoticeForm cmd = (NoticeForm)request.getAttribute("command");
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			List resultList = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/las/board/DecideArbitrarilyRe_d.jsp";
							
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			NoticeForm form = new NoticeForm();
			NoticeVO   vo   = new NoticeVO();

			bind(request, form);
			bind(request, vo);

			// ARBT : Decide Arbitrarily(전결규정)
			vo.setAnnce_knd("ARBT");
			form.setAnnce_knd("ARBT");
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * Service
			**********************************************************/
			// 해당회사 전결규정의 SEQNO를 가져옴
			ListOrderedMap lom = new ListOrderedMap();
			List maxSeqno = noticeService.getMaxSeqNoByCompCd(vo);
			ListOrderedMap lomTmp = (ListOrderedMap)maxSeqno.get(0);
			
			if(Integer.parseInt(lomTmp.get("maxSeqno").toString()) > 0){
				vo.setSeqno(Integer.parseInt(lomTmp.get("maxSeqno").toString()));
				form.setSeqno(Integer.parseInt(lomTmp.get("maxSeqno").toString()));
			}
			
			// 상세내역 조회
			resultList = noticeService.detailNotice(vo);

			if(resultList.size()>0){
				lom = (ListOrderedMap)resultList.get(0);
				lom.put("cont", StringUtil.convertCharsToHtml((String)lom.get("cont")));
			}
			
			/*********************************************************
             * 권한 처리
            **********************************************************/ 
            boolean modifyAuth = noticeService.authNotice(MODIFY, vo);
            boolean deleteAuth = noticeService.authNotice(DELETE, vo);
			String accessLevel = noticeService.checkAuthByRole(vo);
			
			if ("A".equals(accessLevel)){
	            form.setAuth_modify(modifyAuth);
	            form.setAuth_delete(deleteAuth);
			}
			
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
			throw new Exception("NoticeController.detailNotice() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.detailNotice() Throwable !!");
		}
	}
}