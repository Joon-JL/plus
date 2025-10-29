/**
* File Name : OpinionAcceptanceController.java
* Description : 타법인 의견 수렴 Controller
* Author : 
* Date : 2013.11
*/
package com.sec.las.board.control;

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
import com.sds.secframework.common.util.Token;
import com.sec.las.board.dto.OpinionAcceptanceForm;
import com.sec.las.board.dto.OpinionAcceptanceVO;
import com.sec.las.board.service.OpinionAcceptanceService;

public class OpinionAcceptanceController extends CommonController {

	/**
	 * OpinionAcceptance 서비스
	 */
	private OpinionAcceptanceService opinionAcceptanceService = null;
	
	/**
	 * OpinionAcceptance 서비스 세팅
	 * @param opinionAcceptanceService
	 * @return void
	 */
	public void setOpinionAcceptanceService(OpinionAcceptanceService opinionAcceptanceService) {
		this.opinionAcceptanceService = opinionAcceptanceService;
	}

	/**
	 * OpinionAcceptance 목록조회
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listOpinionAcceptance(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			String accessLevel	 = "";
			
			OpinionAcceptanceForm form       = null;
			OpinionAcceptanceVO   vo         = null;
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
			forwardURL = "/WEB-INF/jsp/las/board/OpinionAcceptance_l.jsp";
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new OpinionAcceptanceForm();
			vo   = new OpinionAcceptanceVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);		
			
			//Cross-site Scripting 방지
			form.setSrch_start_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_start_dt(),"")));
			form.setSrch_end_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_end_dt(),"")));			
			form.setSrch_combo(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_combo(),"")));
			form.setSrch_word(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_word(),"")));
			form.setCurPage(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getCurPage(),"")));
			
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
				form.setCurPage("1");
				vo.setCurPage("1");
			}
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = opinionAcceptanceService.listOpinionAcceptance(vo);

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
			/*
			accessLevel = opinionAcceptanceService.checkAuthByRole(vo);
			if ("A".equals(accessLevel)){
				form.setAuth_insert(true);
			}
			*/
			/*********************************************************
			 * ModelAndView
			**********************************************************/
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
			throw new Exception("OpinionAcceptanceController.listOpinionAcceptance() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("OpinionAcceptanceController.listOpinionAcceptance() Throwable !!");
		}
	}
	
	/**
	 * OpinionAcceptance 등록폼 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardInsertOpinionAcceptance(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			String forwardURL = "/WEB-INF/jsp/las/board/OpinionAcceptance_i.jsp";
			
			OpinionAcceptanceForm form       = new OpinionAcceptanceForm();
			OpinionAcceptanceVO   vo         = new OpinionAcceptanceVO();
			
			ListOrderedMap lom = new ListOrderedMap();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			form.setInsert_kbn("INSERT");
			
			//   TB_COM_USER 에서 과제 참여자 초기 대상을 조회해 온다. (시스템 산하 모든 법인의 LEGAL ADMIN 권한자 +  검토자(RA02) ) 
			List member_list = opinionAcceptanceService.getOpinionAcceptanceUser(vo);
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("member_list", member_list);
			mav.addObject("rowspan", member_list.size());
			mav.addObject("command", form);
			return mav; 
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("OpinionAcceptanceController.forwardInsertOpinionAcceptance() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("OpinionAcceptanceController.forwardInsertOpinionAcceptance() Throwable !!");
		}
	}
	
	/**
	 * OpinionAcceptance 등록폼 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardInsertReplyOpinionAcceptance(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			String forwardURL = "/WEB-INF/jsp/las/board/OpinionAcceptance_i.jsp";
			
			OpinionAcceptanceForm form       = new OpinionAcceptanceForm();
			OpinionAcceptanceVO   vo         = new OpinionAcceptanceVO();
			
			ModelAndView mav = new ModelAndView();
			ListOrderedMap lom = new ListOrderedMap();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			List resultList = opinionAcceptanceService.detailOpinionAcceptance(vo);
			lom = (ListOrderedMap)resultList.get(0);			
			
			form.setInsert_kbn("REPLY");	
	
			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("title", "[Re:]" + (String)lom.get("title"));
			return mav; 
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("OpinionAcceptanceController.forwardInsertOpinionAcceptance() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("OpinionAcceptanceController.forwardInsertOpinionAcceptance() Throwable !!");
		}
	}
	
	
	/**
	 * OpinionAcceptance 등록
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView insertOpinionAcceptance(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			
			String returnMessage = "";
			String forwardURL    = "";
			
			OpinionAcceptanceForm form = new OpinionAcceptanceForm();
			OpinionAcceptanceVO vo = new OpinionAcceptanceVO();
		
			bind(request, form);
			bind(request, vo);			

			// 사용자 정보 세팅
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//Cross-site Scripting 방지
			form.setTitle(StringUtil.convertHtmlTochars(form.getTitle()));
			form.setCont(StringUtil.convertHtmlTochars(form.getCont()));
			vo.setTitle(StringUtil.convertHtmlTochars(vo.getTitle()));
			vo.setCont(StringUtil.convertHtmlTochars(vo.getCont()));
			
			// Service
			int part_member_cnt = 0;
			
			if (Token.isValid(request)) {
				part_member_cnt = opinionAcceptanceService.insertOpinionAcceptance(form, vo);
			}				

			//forwardURL = "/las/board/opinionAcceptance.do?method=detailOpinionAcceptance";
			
			// 메시지처리 - 등록되었습니다.
			if(part_member_cnt > 0)
				returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());

			// ModelAndView
			//ModelAndView mav = new ModelAndView();

			ModelAndView mav = detailOpinionAcceptance(request, response, vo.getSeqno());
			mav.addObject("returnMessage", returnMessage);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("OpinionAcceptanceController.insertOpinionAcceptance() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("OpinionAcceptanceController.insertOpinionAcceptance() Throwable !!");
		}
	}	
	
	/**
	 * OpinionAcceptance Reply 등록
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView insertReplyOpinionAcceptance(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			String returnMessage = "";
			String forwardURL    = "";

			OpinionAcceptanceForm form = new OpinionAcceptanceForm();
			OpinionAcceptanceVO vo = new OpinionAcceptanceVO();
			
			bind(request, form);
			bind(request, vo);			

			// 사용자 정보 세팅
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//Cross-site Scripting 방지
			form.setTitle(StringUtil.convertHtmlTochars(form.getTitle()));
			form.setCont(StringUtil.convertHtmlTochars(form.getCont()));
			vo.setTitle(StringUtil.convertHtmlTochars(vo.getTitle()));
			vo.setCont(StringUtil.convertHtmlTochars(vo.getCont()));						
		
			forwardURL = "/las/board/opinionAcceptance.do?method=detailOpinionAcceptance";

			int insert_cnt = opinionAcceptanceService.saveOpinionAcceptanceReply(vo, "REPLY");		

			// 메시지처리 - 등록되었습니다.
			returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("OpinionAcceptanceController.insertOpinionAcceptance() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("OpinionAcceptanceController.insertOpinionAcceptance() Throwable !!");
		}
	}		
	
	/**
	 * 과제 답변 대상자 메일 발송 /  과제 스테이터스 변경
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView sendRequestReplyOpinion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			int sent_cnt = 0;
			
			String returnMessage = "";
			String forwardURL = "/las/board/opinionAcceptance.do?method=detailOpinionAcceptance";

			OpinionAcceptanceForm form = new OpinionAcceptanceForm();
			OpinionAcceptanceVO vo = new OpinionAcceptanceVO();
			
			bind(request, form);
			bind(request, vo);			

			// 사용자 정보 세팅
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			sent_cnt = opinionAcceptanceService.sendRequestReplyOpinion(vo);		

			// 메시지처리 - 전송되었습니다.
			if(sent_cnt > 0)
				returnMessage = messageSource.getMessage("secfw.msg.succ.sendMail" , null, new RequestContext(request).getLocale()); 

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("OpinionAcceptanceController.insertOpinionAcceptance() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("OpinionAcceptanceController.insertOpinionAcceptance() Throwable !!");
		}
	}	
	
	/**
	 * OpinionAcceptance 상세정보 조회
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailOpinionAcceptance(HttpServletRequest request, HttpServletResponse response,String seqno) throws Exception {
		try {
			HttpSession session = request.getSession(false);
			
			String forwardURL    = "";
			String returnMessage = "";

			forwardURL = "/WEB-INF/jsp/las/board/OpinionAcceptance_d.jsp";

			OpinionAcceptanceForm form = new OpinionAcceptanceForm();
			OpinionAcceptanceVO   vo   = new OpinionAcceptanceVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);			
						
			if(!"".equals(seqno)){
				form.setSeqno(seqno);
				vo.setSeqno(seqno);
			}
			
			List resultList = opinionAcceptanceService.detailOpinionAcceptance(vo);
			ListOrderedMap lom = null;

						
			if(resultList!=null){				
				lom = (ListOrderedMap)resultList.get(0);
				//lom.put("CONT", StringUtil.convertCharsToHtml((String)lom.get("CONT")));
				lom.put("CONT", StringUtil.convertEnterToBR(((String)lom.get("CONT"))));
			} 	
			
			List userList  = opinionAcceptanceService.listOpinionAcceptanceUser(vo); // 참여 대상자 목록			
			List underList = opinionAcceptanceService.detailListOpinionAcceptance(vo); // 답변 목록
			
			// 개행처리
			for(int i =0;i < underList.size(); i ++){
				ListOrderedMap lom2 = (ListOrderedMap)underList.get(i);
				lom2.put("CONT", StringUtil.convertEnterToBR(StringUtil.bvl((String)lom2.get("CONT"),"")));
			}
			
			int totalCnt = 0;
			totalCnt = underList.size();			
			form.setTotalCnt(totalCnt);			

			returnMessage = StringUtil.bvl((String)request.getAttribute("returnMessage"), "");

			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			
			mav.addObject("resultList", resultList);
			mav.addObject("underList", underList);
			mav.addObject("userList", userList);
			mav.addObject("rowspan", userList.size());
			mav.addObject("lom", lom);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("OpinionAcceptanceController.detailOpinionAcceptance() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("OpinionAcceptanceController.detailOpinionAcceptance() Throwable !!");
		}
	}

	/**
	 * OpinionAcceptance 수정폼 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardModifyOpinionAcceptance(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");

			String forwardURL    = "";
			
			OpinionAcceptanceForm form       = new OpinionAcceptanceForm();
			OpinionAcceptanceVO   vo         = new OpinionAcceptanceVO();
		
			List resultList 	= null;
			ListOrderedMap lom = new ListOrderedMap();

			forwardURL = "/WEB-INF/jsp/las/board/OpinionAcceptance_i.jsp";			

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			resultList = opinionAcceptanceService.detailOpinionAcceptance(vo);
			//  기 등록 참여자 목록
			List member_list  = opinionAcceptanceService.listOpinionAcceptanceUser(vo); // 참여 대상자 목록			
			lom = (ListOrderedMap)resultList.get(0);
			
			form.setInsert_kbn("MODIFY");	

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("lom", lom);
			mav.addObject("member_list", member_list);
			mav.addObject("rowspan", member_list.size());
			mav.addObject("command", form);
			
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("OpinionAcceptanceController.forwardModifyOpinionAcceptance() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("OpinionAcceptanceController.forwardModifyOpinionAcceptance() Throwable !!");
		}
	}
	
	/**
	 * OpinionAcceptance 답변 수정폼 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardModifyOpinionAcceptanceReply(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");

			String forwardURL    = "";
			
			OpinionAcceptanceForm form       = new OpinionAcceptanceForm();
			OpinionAcceptanceVO   vo         = new OpinionAcceptanceVO();
		
			List resultList 	= null;
			ListOrderedMap lom = new ListOrderedMap();

			forwardURL = "/WEB-INF/jsp/las/board/OpinionAcceptance_i.jsp";			

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			resultList = opinionAcceptanceService.detailOpinionAcceptanceReply(vo);

			lom = (ListOrderedMap)resultList.get(0);
			
			form.setInsert_kbn("REPLY_MODIFY");	

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("lom", lom);
			mav.addObject("command", form);
			
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("OpinionAcceptanceController.forwardModifyOpinionAcceptance() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("OpinionAcceptanceController.forwardModifyOpinionAcceptance() Throwable !!");
		}
	}
	

	/**
	 * OpinionAcceptance 수정 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView modifyOpinionAcceptance(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			String forwardURL = "/las/board/opinionAcceptance.do?method=detailOpinionAcceptance";
			String returnMessage = "";
			
			OpinionAcceptanceForm form       = new OpinionAcceptanceForm();
			OpinionAcceptanceVO   vo         =  new OpinionAcceptanceVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			// XSS 처리
			vo.setTitle(StringUtil.convertHtmlTochars(vo.getTitle()));
			form.setTitle(StringUtil.convertHtmlTochars(vo.getTitle()));
			
			// 수정 권한 체크			
			
			
			int mod_cnt = opinionAcceptanceService.modifyOpinionAcceptance(form, vo);
			
			// 메시지처리
			if(mod_cnt > 0)
				returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale()); //  수정되었습니다.
			
			//  ModelAndView
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("OpinionAcceptanceController.modifyOpinionAcceptance() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("OpinionAcceptanceController.modifyOpinionAcceptance() Throwable !!");
		}
	}

	
	/**
	 * OpinionAcceptance 답변 수정 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView modifyReplyOpinionAcceptance(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			String forwardURL = "/las/board/opinionAcceptance.do?method=detailOpinionAcceptance";
			String returnMessage = "";
			
			OpinionAcceptanceForm form       = new OpinionAcceptanceForm();
			OpinionAcceptanceVO   vo         =  new OpinionAcceptanceVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			// XSS 처리
			vo.setTitle(StringUtil.convertHtmlTochars(vo.getTitle()));
			form.setTitle(StringUtil.convertHtmlTochars(vo.getTitle()));			
			// 수정 권한 체크				
			
			int mod_cnt = opinionAcceptanceService.saveOpinionAcceptanceReply(vo, "REPLY_MOD");
			
			// 메시지처리
			if(mod_cnt > 0)
				returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale()); //  수정되었습니다.
			
			//  ModelAndView
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("OpinionAcceptanceController.modifyOpinionAcceptance() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("OpinionAcceptanceController.modifyOpinionAcceptance() Throwable !!");
		}
	}
	
	
	/**
	 * OpinionAcceptance 삭제
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView deleteOpinionAcceptance(HttpServletRequest request, HttpServletResponse response, String divOpinionAcceptance) throws Exception {
		try {		
			
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");

			String forwardURL    = "";
			String returnMessage = "";
			
			OpinionAcceptanceForm form       = new OpinionAcceptanceForm();
			OpinionAcceptanceVO   vo         =  new OpinionAcceptanceVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			forwardURL = "/las/board/opinionAcceptance.do?method=listOpinionAcceptance";
			
			if(!hasAuth(session, form, vo)){
				// 권한이 없습니다.
				returnMessage = messageSource.getMessage("las.page.field.consideration.returnConsideration01", null, new RequestContext(request).getLocale());
			} else {

				int del_cnt = opinionAcceptanceService.deleteOpinionAcceptance(vo);
				// 메시지처리 - 삭제되었습니다.
				returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale());
			}

			ModelAndView mav = new ModelAndView();
			
			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("OpinionAcceptanceController.deleteOpinionAcceptance() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("OpinionAcceptanceController.deleteOpinionAcceptance() Throwable !!");
		}
	}	
	
	/**
	 * OpinionAcceptance 삭제
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView deleteOpinionAcceptanceReply(HttpServletRequest request, HttpServletResponse response, String divOpinionAcceptance) throws Exception {
		try {		
			
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");

			String forwardURL    = "";
			String returnMessage = "";
			
			OpinionAcceptanceForm form       = new OpinionAcceptanceForm();
			OpinionAcceptanceVO   vo         =  new OpinionAcceptanceVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			forwardURL = "/las/board/opinionAcceptance.do?method=detailOpinionAcceptance";
			
			if(!hasAuth(session, form, vo)){
				// 권한이 없습니다.
				returnMessage = messageSource.getMessage("las.page.field.consideration.returnConsideration01", null, new RequestContext(request).getLocale());
			} else {

				int del_cnt = opinionAcceptanceService.deleteOpinionAcceptanceReply(vo);
				// 메시지처리 - 삭제되었습니다.
				returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale());
			}

			ModelAndView mav = new ModelAndView();
			
			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("OpinionAcceptanceController.deleteOpinionAcceptance() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("OpinionAcceptanceController.deleteOpinionAcceptance() Throwable !!");
		}
	}	
	
	/**
	 * SYSTEM ADMIN OR  자기 자신이 작성한 지 여부 체크
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	private boolean hasAuth(HttpSession session , OpinionAcceptanceForm form , OpinionAcceptanceVO vo) throws Exception {
		
		boolean authYN = false;
		
		ArrayList tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.roleList");
			
		// 시스템 어드민
		if(tmpSessionRoleList.contains("RA00")){
			authYN = true;
		} else {
			
			if("REPLY".equals(form.getInsert_kbn())){
				authYN = opinionAcceptanceService.authOpinionAcceptance(vo, "REPLY");
			} else {
				authYN = opinionAcceptanceService.authOpinionAcceptance(vo, "XXXX");
			}		
		}
		
/*		else if(tmpSessionRoleList.contains("RA01")){
			authYN = true;
		}else if(tmpSessionRoleList.contains("RA02")){
			authYN = true;
		}else if(tmpSessionRoleList.contains("RA03")){
			authYN = true;
		}		*/
		
		return authYN;		
	}	
}