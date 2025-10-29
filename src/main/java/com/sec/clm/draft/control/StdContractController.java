/**
* Project Name : 계약관리시스템
* File Name : StdContractController.java
* Description : 표준계약서 Controller
* Author : 이민우
* Date : 2012.07.16
* Copyright : 2012 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.draft.control;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

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
import com.sec.clm.draft.dto.StdContractForm;
import com.sec.clm.draft.dto.StdContractVO;
import com.sec.clm.draft.service.StdContractService;
import com.sec.las.lawconsulting.dto.LawConsultForm;
import com.sec.las.lawconsulting.dto.LawConsultVO;

public class StdContractController extends CommonController {
	private StdContractService stdContractService;
	public void setStdContractService(StdContractService stdContractService) {
		this.stdContractService = stdContractService;
	}

	/**
	* 표준 계약서 목록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listStdContract(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Forwarding URL
			**********************************************************/			
			String forwardURL = "/WEB-INF/jsp/clm/draft/StdContract_l.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			StdContractForm form = new StdContractForm();
			StdContractVO vo = new StdContractVO();
			
			bind(request, form);
			bind(request, vo);

			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * 페이지 객체
			**********************************************************/
			PageUtil pageUtil = new PageUtil();
			
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(), "1")));
			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());
			pageUtil.setRowPerPage(form.getRow_cnt());

			/*********************************************************
			 * JSP 반환변수
			**********************************************************/
			String returnMessage = "";

			/*********************************************************
			 * 검색처리
			**********************************************************/
			// 검색관련변수 XXS 처리
			vo.setSrch_start_ymd(StringUtil.convertHtmlTochars(vo.getSrch_start_ymd()));
			vo.setSrch_end_ymd(StringUtil.convertHtmlTochars(vo.getSrch_end_ymd()));
			vo.setSrch_respman_nm(StringUtil.convertHtmlTochars(vo.getSrch_respman_nm()));
			vo.setSrch_reg_nm(StringUtil.convertHtmlTochars(vo.getSrch_reg_nm()));
			vo.setSrch_reg_dept(vo.getSession_dept_cd());
			vo.setSrch_title(StringUtil.convertHtmlTochars(vo.getSrch_title()));
			vo.setSrch_cont(StringUtil.convertHtmlTochars(vo.getSrch_cont()));
			//vo.setSrch_consult_type("A20"); //표준계약서코드
			vo.setUser_id(vo.getSession_user_id());
			
			List resultList = stdContractService.listStdContract(vo);
			form.setLawconsult_list(resultList);
			form.setSrch_reg_dept(vo.getSession_dept_cd());    //부서코드
			form.setSrch_reg_dept_nm(vo.getSession_comp_nm()); //부서명
			
			//조회 데이터 처리
			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
				
				pageUtil.setTotalRow(((Integer)lom.get("total_cnt")).intValue());
				
				/*
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();
				*/
			}
			
			//메세지 처리
			if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
				returnMessage = (String)request.getAttribute("returnMessage");
			}
			
		
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
	 * 표준 계약서 상세 페이지 이동
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardDetailStdContract(HttpServletRequest request, HttpServletResponse response) throws Exception {
		StdContractForm form = new StdContractForm();
		StdContractVO vo = new StdContractVO();
		ModelAndView mav = new ModelAndView();
		bind(request, form);
		bind(request, vo);

		// 사용자 정보 세팅
		COMUtil.getUserAuditInfo(request, vo);
		COMUtil.getUserAuditInfo(request, form);

		mav = forwardDetailStdContract(request, form, vo, new RequestContext(request).getLocale());

		return mav;
	}

	/**
	 * 표준 계약서 상세보기
	 * 
	 * @param request
	 * @param form
	 * @param vo
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardDetailStdContract(HttpServletRequest request, StdContractForm form, StdContractVO vo, Locale locale) throws Exception {
		ModelAndView mav = new ModelAndView();
		String forwardURL = "";

		String returnMessage = "";
		ListOrderedMap lom = new ListOrderedMap();
		List historyList = null;
		List extnlcompList = null;
		List respmanList = null;
		List cnsdrejctList = null;

		try {
			
			forwardURL = "/WEB-INF/jsp/clm/draft/StdContract_d.jsp";
			lom = stdContractService.detailStdContract(vo);
			historyList = stdContractService.listStdContracttHistory(vo);
			//extnlcompList = lawConsultService.listLawConsultExtnlcomp(vo);
			//respmanList = lawConsultService.listLawConsultRespman(vo);
			
			if (lom.size() == 0) {
				returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, locale);
			}
			
			form.setOrdr_cont(StringUtil.replace((String) lom.get("ordr_cont"), "<br>", "\r\n"));

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
		
		
		
		String auth = "N";
		if(vo.getSession_blngt_orgnz().equals("A00000001") && "H".equals((String)lom.get("dmstfrgn_gbn"))){
			auth = "Y";
		}
		if(vo.getSession_blngt_orgnz().equals("A00000002") && "F".equals((String)lom.get("dmstfrgn_gbn"))){
			auth = "Y";
		}
		
		// 리턴메시지 세팅
		form.setReturn_message(returnMessage);
		mav.setViewName(forwardURL);
		mav.addObject("auth", auth);
		mav.addObject("historyList", historyList);
		mav.addObject("extnlcompList", extnlcompList);
		mav.addObject("respmanList", respmanList);
		mav.addObject("lom", lom);
		mav.addObject("command", form);
		return mav;
	}
	
}