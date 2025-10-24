/**
 * Project Name : 법무시스템 전자검토자
 * File name	: ElecReviewerController.java
 * Description	: 법무시스템 전자검토자  Controller
 * Author		: 신승완
 * Date			: 2011. 08. 04
 * Copyright	:
 */
package com.sec.las.elecReviewer.control;

import java.math.BigDecimal;
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
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.manage.service.MailSendService;
import com.sec.common.clmscom.service.CLMSCommonService;
import com.sec.las.elecReviewer.dto.ElecReviewerForm;
import com.sec.las.elecReviewer.dto.ElecReviewerVO;
import com.sec.las.elecReviewer.service.ElecReviewerService;

/**
 * Description	: 전자검토자  Controller
 * Author 		: 제이남
 * Date			: 2013. 06. 18
 */
public class ElecReviewerController extends CommonController {
	
	/**
	 * 전자검토자 서비스
	 */
	private ElecReviewerService elecReviewerService;
	
	/**
	 * 전자검토자 서비스 세팅
	 * 
	 * @param elecReviewerService
	 * @return void
	 * @throws
	 */
	public void setElecReviewerService(ElecReviewerService elecReviewerService) {
		this.elecReviewerService = elecReviewerService;
	}
	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅 
	 * 공통코드 콤보셋팅 !@# 2013-04-16
	 */
	private CLMSCommonService clmsComService;
	public void setClmsComService(CLMSCommonService clmsComService)
	{
		this.clmsComService = clmsComService;
	}
	/**
	 * 메일전송 서비스
	 */
	private MailSendService mailSendService ;

	public void setMailSendService(MailSendService mailSendService) {
		this.mailSendService = mailSendService;
	}
	/**
	 * 전자검토자 업무이관  목록조회
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listChangeReviewer(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ModelAndView mav = new ModelAndView();
			String forwardURL  = "/WEB-INF/jsp/las/elecReviewer/ChangeReviewer_l.jsp";
			
			PageUtil 	pageUtil   	= null;
			
			ElecReviewerForm form =  new ElecReviewerForm();
			ElecReviewerVO vo =  new ElecReviewerVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Page  setting
			**********************************************************/
			pageUtil = new PageUtil();
			
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(), "1")));

			vo.setStart_index(pageUtil.getStartIndex());	//현재 페이지의 첫번째 게시물번호  set
			vo.setEnd_index(pageUtil.getEndIndex());		//현재 페이지의 마지막 게시물번호  set
			
			List resultList = elecReviewerService.listChangeReviewer(vo);
			
			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap tmp = (ListOrderedMap) resultList.get(0);
				
				pageUtil.setTotalRow(((BigDecimal)tmp.get("total_cnt")).intValue());
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();
			}
			form.setCurPage((StringUtil.bvl(form.getCurPage(),"")));
			
			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("resultList", resultList);
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
	 * 전자검토자 업무이관 지정 화면
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView goInsertForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ModelAndView mav = new ModelAndView();
			String forwardURL  = "/WEB-INF/jsp/las/elecReviewer/ChangeReviewer_i.jsp";
			ElecReviewerForm form =  new ElecReviewerForm();
			
			bind(request, form);
			
			mav.setViewName(forwardURL);
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
	 * 원담당자 조회
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView searchElecReviewer(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ModelAndView mav = new ModelAndView();
			String forwardURL  = "/WEB-INF/jsp/las/elecReviewer/ChangeReviewer_p.jsp";
			ElecReviewerForm form =  new ElecReviewerForm();
			ElecReviewerVO vo =  new ElecReviewerVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			vo.setRole_cd("RB01");//전자검토자 조회
			
			List resultList = elecReviewerService.searchElecReviewer(vo);
			
			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("resultList", resultList);
			
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
	 * 임시담당자 조회
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView searchElecReviewerTmp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ModelAndView mav = new ModelAndView();
			String forwardURL  = "/WEB-INF/jsp/las/elecReviewer/ChangeReviewerTmp_p.jsp";
			ElecReviewerForm form =  new ElecReviewerForm();
			ElecReviewerVO vo =  new ElecReviewerVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			vo.setRole_cd("RB01");//전자검토자 조회
			
			List resultList = elecReviewerService.searchElecReviewerTmp(vo);
			
			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("resultList", resultList);
			
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
	 * 임시 담당자 지정
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView appointTmpElecReviewer(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(true);
			ModelAndView mav = new ModelAndView();
			ElecReviewerForm form =  new ElecReviewerForm();
			ElecReviewerVO vo =  new ElecReviewerVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			String comp_cd = vo.getComp_cd();
			String comp_nm = vo.getComp_nm();
			
			int result = elecReviewerService.appointTmpElecReviewer(vo, session);
			
			//임시담당자 지정 후 메일발송
			if(result == 0){
				HashMap hm = new HashMap();
				hm.put("org_resp_id", vo.getOrg_resp_id());
				hm.put("org_resp_nm", vo.getOrg_resp_nm());
				hm.put("tmp_resp_id", vo.getTmp_resp_id());
				hm.put("tmp_resp_nm", vo.getTmp_resp_nm());
				hm.put("comp_cd"	, comp_cd);
				hm.put("comp_nm"	, comp_nm);
				hm.put("sendType"	, "A");
				hm.put("locale"		, vo.getSession_user_locale());
				
				mailSendService.sendElecRespmanMailSend(hm);
			}
			
			mav.addObject("command", form);
			
			return listChangeReviewer(request, response);
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 * 담당자 원복
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView returnOrgElecReviewer(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(true);
			ModelAndView mav = new ModelAndView();
			ElecReviewerForm form =  new ElecReviewerForm();
			ElecReviewerVO vo =  new ElecReviewerVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			int result = elecReviewerService.returnOrgElecReviewer(vo, session);
			
			//담당자원복 후 메일발송
			if(result == 0){
				HashMap hm = new HashMap();
				hm.put("org_resp_id", vo.getOrg_resp_id());
				hm.put("org_resp_nm", vo.getOrg_resp_nm());
				hm.put("tmp_resp_id", vo.getTmp_resp_id());
				hm.put("tmp_resp_nm", vo.getTmp_resp_nm());
				hm.put("comp_cd"	, vo.getComp_cd());
				hm.put("comp_nm"	, vo.getComp_nm());
				hm.put("sendType"	, "R");
				hm.put("locale"		, vo.getSession_user_locale());
				
				mailSendService.sendElecRespmanMailSend(hm);
			}
			mav.addObject("command", form);
			return listChangeReviewer(request, response);
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
}