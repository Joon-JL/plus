/**
* Project Name : 계약관리시스템
* File Name : QnaController
* Description : QNA Controller
* Author : dawn
* Date : 2010.09.06
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.counsel.control;

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
import com.sec.clm.counsel.dto.QnaForm;
import com.sec.clm.counsel.dto.QnaVO;
import com.sec.clm.counsel.service.QnaService;

public class QnaController extends CommonController{
	
	private static String RE = "[Re:]";
	private static String QNA_BBS_KND = "C01401";
	
	private QnaService qnaService;
	public void setQnaService(QnaService qnaService) {
		this.qnaService = qnaService;
	}

	/**
	* QNA 목록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listQna(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String forwardURL = "/WEB-INF/jsp/clm/counsel/Qna_l.jsp";//목록페이지로 이동.
		String returnMessage = "";
		
		QnaForm form = new QnaForm();
		QnaVO   vo   = new QnaVO();
		
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
		
		//XSS처리
		vo.setTitle(StringUtil.convertHtmlTochars(form.getTitle()));
		vo.setSrch_select_title(StringUtil.convertHtmlTochars(form.getSrch_select_title()));
		
		vo.setSrch_title(StringUtil.nvl(form.getSrch_title(),""));
		
		try{
			List resultList = qnaService.listQna(vo);
			if (resultList != null && resultList.size() > 0) {
				pageUtil.setTotalRow(pageUtil.getTotalRow(resultList, "total_cnt"));
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();
			}
						
			if ((String) request.getAttribute("returnMessage") != null && ((String) request.getAttribute("returnMessage")).length() > 1) {
				returnMessage = (String) request.getAttribute("returnMessage");
				form.setReturn_message(returnMessage);
			}
			
			ListOrderedMap lom = null;
			String total_cnt = "";
			
			if(resultList.size() > 0){
				lom = (ListOrderedMap)resultList.get(0);
				
				total_cnt = StringUtil.commaIn(String.valueOf(lom.get("rn")));
				
			} else {
				
				total_cnt = "0";
				
			}
			
			
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("returnMessage", returnMessage);
			mav.addObject("command", form);
			mav.addObject("total_cnt", total_cnt);
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
	* QNA 상세목록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView detailQna(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String forwardURL = "/WEB-INF/jsp/clm/counsel/Qna_d.jsp";//상세페이지로 이동.
		QnaForm cmd = (QnaForm)request.getAttribute("command");
		
		QnaForm form = new QnaForm();
		QnaVO vo = new QnaVO();
		
		bind(request, form);
		bind(request, vo);
		
		COMUtil.getUserAuditInfo(request, form);
		COMUtil.getUserAuditInfo(request, vo);
		
		if(cmd != null){//저장 후 데이터 호출을 위해.
			form = cmd;
			vo.setUp_seqno(form.getUp_seqno());
			vo.setSeqno(form.getSeqno());
		}

		try{
			ModelAndView mav = new ModelAndView();
			ListOrderedMap lom = qnaService.detailQna(vo);
			List replyList = qnaService.detailReplyQna(vo);
			int countReply = qnaService.countReplyQna(vo);
			boolean modifyAuth = qnaService.authQna("modify", vo);// 수정권한(수정 버튼 control)
			boolean deleteAuth = qnaService.authQna("delete", vo);// 삭제권한(삭제 버튼 control)
			boolean transferAuth = qnaService.authQna("transfer", vo);// 이관권한(이관 버튼 control)
			boolean replyAuth = qnaService.authQna("reply", vo);// 답변 권한(답변 버튼 control)
			form.setAuth_modify(modifyAuth);
			form.setAuth_delete(deleteAuth);
			form.setAuth_transfer(transferAuth);
			form.setAuth_reply(replyAuth);

			mav.setViewName(forwardURL);
			mav.addObject("lom", lom);
			mav.addObject("replyList", replyList);
			mav.addObject("command", form);
			mav.addObject("countReply", countReply);
			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch(Throwable t){
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	* QNA 수정화면 호출
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView forwardModifyQna(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String forwardURL = "/WEB-INF/jsp/clm/counsel/Qna_u.jsp";//수정페이지로 이동.
		
		QnaForm form = new QnaForm();
		QnaVO vo = new QnaVO();
		
		bind(request, form);
		bind(request, vo);
		
		COMUtil.getUserAuditInfo(request, form);
		COMUtil.getUserAuditInfo(request, vo);
		
		try{
			ListOrderedMap lom = qnaService.detailQna(vo);
			
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
	* QNA 수정
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView modifyQna(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String forwardURL = "/clm/counsel/qna.do?method=detailQna";//상세페이지로 이동.
		String returnMessage = "";
		
		QnaForm form = new QnaForm();
		QnaVO vo = new QnaVO();
		
		bind(request, form);
		bind(request, vo);
		
		COMUtil.getUserAuditInfo(request, form);
		COMUtil.getUserAuditInfo(request, vo);

		vo.setMod_id(form.getSession_user_id());
		vo.setMod_nm(form.getSession_user_nm());
		
		try{
			int result = qnaService.modifyQna(vo);
			
			if(result == 1){
				returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());
			}
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName(forwardURL);
			form.setReturn_message(returnMessage);
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
	* QNA 삭제
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView deleteQna(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String forwardURL = "/clm/counsel/qna.do?method=listQna";//목록으로 이동.
		String returnMessage = "";
		
		QnaForm form = new QnaForm();
		QnaVO vo = new QnaVO();
		
		bind(request, form);
		bind(request, vo);
		
		COMUtil.getUserAuditInfo(request, form);
		COMUtil.getUserAuditInfo(request, vo);
		
		/*********************************************************
		 * Delete 처리
		**********************************************************/
		vo.setDel_id(vo.getSession_user_id());
		vo.setDel_nm(vo.getSession_user_nm());
		
		try{
			int result = qnaService.deleteQna(vo);
			
			if(result == 1){
				returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale());
			}
			
			ModelAndView mav = new ModelAndView();
			form.setReturn_message(returnMessage);
			mav.addObject("returnMessage", returnMessage);
			mav.setViewName(forwardURL);
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
	* QNA 저장하는 화면 호출
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView forwardInsertQna(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String forwardURL = "/WEB-INF/jsp/clm/counsel/Qna_i.jsp";//저장페이지로 이동.

		QnaForm form = new QnaForm();
		QnaVO vo = new QnaVO();
		
		bind(request, form);
		bind(request, vo);

		COMUtil.getUserAuditInfo(request, form);
		COMUtil.getUserAuditInfo(request, vo);

		ModelAndView mav = new ModelAndView();

		mav.setViewName(forwardURL);
		mav.addObject("command", form);
		return mav;
	}
	
	/**
	* QNA 저장
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView insertQna(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//String forwardURL = "/clm/counsel/qna.do?method=detailQna";//상세페이지로 이동.
		String forwardURL = "/clm/counsel/qna.do?method=listQna";//목록페이지로 이동.
		ModelAndView mav = new ModelAndView();
		String returnMessage = "";
		
		QnaForm form = new QnaForm();
		QnaVO vo = new QnaVO();
		
		bind(request, form);
		bind(request, vo);

		COMUtil.getUserAuditInfo(request, form);
		COMUtil.getUserAuditInfo(request, vo);
		
		//XSS처리
		vo.setTitle(StringUtil.convertHtmlTochars(form.getTitle()));
		
		vo.setPrgrs_status("C01701");//C017코드(C01701: 진행, C01702 :완료)
		vo.setBbs_knd(QNA_BBS_KND);//C014코드(C01401: Q&A)
		
		setUserInfo(form, vo);
		try{
			int result = 0;	
			result = qnaService.insertQna(vo);
			//form.setUp_seqno(upSeqNo);//상세페이지 호출하기위해.
			returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());

		}catch(Exception e){
			returnMessage = messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale());
			forwardURL = "/WEB-INF/jsp/clm/counsel/Qna_i.jsp";
			form.setCont(vo.getCont());
			e.printStackTrace();
		}catch(Throwable t){
			t.printStackTrace();
			throw new Exception("Error");
		}
		form.setReturn_message(returnMessage);
		mav.addObject("returnMessage", returnMessage);
		mav.setViewName(forwardURL);
		mav.addObject("command", form);
		return mav;
	}
	
	/**
	* QNA 답변 화면 호출.
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView forwardReplyQna(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String forwardURL = "/WEB-INF/jsp/clm/counsel/Qna_i.jsp";//저장페이지로 이동.
		
		QnaForm form = new QnaForm();
		QnaVO vo = new QnaVO();
		
		bind(request, form);
		bind(request, vo);

		COMUtil.getUserAuditInfo(request, form);
		COMUtil.getUserAuditInfo(request, vo);
		
		form.setTitle(RE.concat(form.getTitle()));
		
		try{
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
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
	* QNA 이관
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView transferQna(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String forwardURL = "/clm/counsel/qna.do?method=listQna";//상세페이지로 이동.
		String returnMessage = "";
		
		ModelAndView mav = new ModelAndView();
		
		QnaForm form = new QnaForm();
		QnaVO vo = new QnaVO();
		
		bind(request, form);
		bind(request, vo);
		
		COMUtil.getUserAuditInfo(request, form);
		COMUtil.getUserAuditInfo(request, vo);
		
		if(!(StringUtil.checkScript(vo.getTrans_demnd_cont()) )){
			forwardURL = "/WEB-INF/jsp/secfw/error/ErrorBoard.jsp";	
			mav.setViewName(forwardURL);
			form.setReturn_message(messageSource.getMessage("secfw.page.field.alert.noLinkMessage", null, new RequestContext(request).getLocale()));
			form.setReturn_title(messageSource.getMessage("secfw.page.field.alert.noAuthTitle", null, new RequestContext(request).getLocale()));
			mav.addObject("command", form);
			return mav;
		}
		
		try{
			int result = qnaService.transferQna(vo);
			
			if(result == 1){
				returnMessage = messageSource.getMessage("secfw.msg.succ.transfer", null, new RequestContext(request).getLocale());
			}
			
			mav.setViewName(forwardURL);
			mav.addObject("returnMessage", returnMessage);
			form.setReturn_message(returnMessage);
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
	* QNA 이관화면 호출
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView forwardTransferQna(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String forwardURL = "/WEB-INF/jsp/clm/counsel/Qna_transfer.jsp";//수정페이지로 이동.
		
		QnaForm form = new QnaForm();
		QnaVO vo = new QnaVO();
		
		bind(request, form);
		bind(request, vo);
		
		COMUtil.getUserAuditInfo(request, form);
		COMUtil.getUserAuditInfo(request, vo);
		
		try{
			ListOrderedMap lom = qnaService.detailQna(vo);
			
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
	 * 세션에 담겨있는 User정보를 vo객체에 세팅
	 * 
	 * @param form
	 * @param vo
	 * @throws Exception
	 */
	private void setUserInfo(QnaForm form, QnaVO vo) throws Exception {
		vo.setReg_id(vo.getSession_user_id());
		vo.setReg_nm(vo.getSession_user_nm());
		vo.setReg_dept(vo.getSession_in_dept_cd());
		vo.setReg_dept_nm(vo.getSession_dept_nm());
		vo.setRegman_jikgup_nm(vo.getSession_jikgup_nm());

		form.setReg_id(vo.getSession_user_id());
		form.setReg_nm(vo.getSession_user_nm());
		form.setReg_dept(vo.getSession_in_dept_cd());
		form.setReg_dept_nm(vo.getSession_dept_nm());
		form.setRegman_jikgup_nm(form.getSession_jikgup_nm());
	}
}
