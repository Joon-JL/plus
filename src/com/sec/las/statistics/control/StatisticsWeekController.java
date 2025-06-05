/**
 * Project Name : 법무시스템 주간 보고
 * File name	: StatisticsWeekController.java
 * Description	: 법무통계 - 주간보고  Controller
 * Author		: 김재원
 * Date			: 2011. 09. 15
 * Modify		: 서백진
 * Date			: 2011. 10. 27
 * Copyright	:
 */
package com.sec.las.statistics.control;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.common.util.Token;
import com.sds.secframework.util.service.ComUtilService;
import com.sec.common.util.ClmsBoardUtil;
import com.sec.las.statistics.dto.StatisticsMonthForm;
import com.sec.las.statistics.dto.StatisticsMonthVO;
import com.sec.las.statistics.dto.StatisticsWeekForm;
import com.sec.las.statistics.dto.StatisticsWeekVO;
import com.sec.las.statistics.service.StatisticsWeekService;
public class StatisticsWeekController extends CommonController {
	/**
	 * ComUtil 서비스
	 */
	private ComUtilService comUtilService;
	
	/**
	 * ComUtil 서비스 세팅
	 * @param comUtilService
	 */
	public void setComUtilService(ComUtilService comUtilService) {
		this.comUtilService = comUtilService;
	}		
	private StatisticsWeekService statisticsWeekService;
	public void setStatisticsWeekService(StatisticsWeekService statisticsWeekService) {
		this.statisticsWeekService = statisticsWeekService;
	}
	
	String forwardURL = "";
	
	/**
	 * 통계 주간업무 총 목록 조회
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listStatisticsWeek(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);
			
			String forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsWeekTotal_l.jsp";
			
			PageUtil pageUtil = new PageUtil();

			StatisticsWeekForm form = new StatisticsWeekForm();
			StatisticsWeekVO vo = new StatisticsWeekVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());
			
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			setLocale(request, response, form, vo);	
			
			if(vo.getReg_id() == null){
				ArrayList roleList = (ArrayList)session.getAttribute("secfw.session.role"); //session 사용할 경우
				ArrayList userRoleList = new ArrayList(); //role_cd만 꺼내기 위한 arrayList
				
				if(roleList != null && roleList.size() > 0){	
				    for(int idx = 0; idx < roleList.size(); idx++){	
				        HashMap roleListMap = (HashMap)roleList.get(idx);	 
				        userRoleList.add((String)roleListMap.get("role_cd"));
				    }
				}

				//사용자 role 비교
				// RA00: 시스템관리자  RA01: 법무관리자  RA02: 법무담당자  RA03: 법무일반사용자
				// RC01: CP관리자
				// RD01: 사업부계약관리자  RD02: 사업부계약담당자
				// RM00: 시스템운영자
				
				if(userRoleList != null && userRoleList.size() > 0){
				    if(userRoleList.contains("RM00") || userRoleList.contains("RA00") || userRoleList.contains("RA01")){
				    	vo.setReg_id("");
				    }else{
				    	vo.setReg_id(vo.getSession_user_id());
				    }
				}		
			}else if(vo.getReg_id().equals("")){
				vo.setReg_id(vo.getSession_user_id());
			}else if(vo.getReg_id().equals("ALL")){
				vo.setReg_id("");
			}
			
			String returnMessage = "";
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/
			
			if(form.getWeekseq() == null){
				setDate(request, response, form, vo);			
			}
			if(request.getParameter("weekFirstDay") != null){
				vo.setWeekFirstDay(request.getParameter("weekFirstDay").replaceAll("-", ""));
			}
			
			if(request.getParameter("weekLastDay") != null){
				vo.setWeekLastDay(request.getParameter("weekLastDay").replaceAll("-", ""));
			}
			
			List respmanList = statisticsWeekService.listRespman(vo);
			
			
			vo.setPage_gbn("C");
			vo.setResult_yn("R");
			List resultList = statisticsWeekService.listStatistics(vo);
			

			vo.setPage_gbn("C");
			vo.setResult_yn("P");
			List resultList1 = statisticsWeekService.listStatistics(vo);
			
			
			vo.setPage_gbn("S");
			vo.setResult_yn("R");			
			List resultList2 = statisticsWeekService.listStatistics(vo);
			
			
			vo.setPage_gbn("S");
			vo.setResult_yn("P");			
			List resultList3 = statisticsWeekService.listStatistics(vo);
			
			
			vo.setPage_gbn("E");
			vo.setResult_yn("R");			
			List resultList4 = statisticsWeekService.listStatistics(vo);
			
			
			vo.setPage_gbn("E");
			vo.setResult_yn("P");			
			List resultList5 = statisticsWeekService.listStatistics(vo);
			
			
			if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
				returnMessage = (String)request.getAttribute("returnMessage");
			}
			
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("respmanList", respmanList);
			mav.addObject("resultList", resultList);
			mav.addObject("resultList1", resultList1);
			mav.addObject("resultList2", resultList2);
			mav.addObject("resultList3", resultList3);
			mav.addObject("resultList4", resultList4);
			mav.addObject("resultList5", resultList5);
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
	 * 주간계획 및 실적 forward(Tab 이동입니다.)
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardStatisticsWeek(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);

			StatisticsWeekForm form = new StatisticsWeekForm();
			StatisticsWeekVO vo = new StatisticsWeekVO();
			ListOrderedMap lom = null;
			bind(request, form);
			bind(request, vo);
			
			
			if(vo.getTransfer() != null && vo.getTransfer().equals("contract")){
				forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsWeekContract_d.jsp";  // 주간업무 계약
			}else if(vo.getTransfer() != null && vo.getTransfer().equals("consult")){
				forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsWeekConsult_d.jsp";   // 주간업무 자문
			}else if(vo.getTransfer() != null && vo.getTransfer().equals("etc")){
				forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsWeek_d.jsp";          // 주간업무 기타
			}
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			setLocale(request, response, form, vo);				
			PageUtil pageUtil = new PageUtil();

			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());

			String returnMessage = "";
			List resultList = null;
			if(vo.getTransfer() != null && vo.getTransfer().equals("contract")){
				resultList = statisticsWeekService.listStatisticsContract(vo);
			}else if(vo.getTransfer() != null && vo.getTransfer().equals("consult")){
				resultList = statisticsWeekService.listStatisticsConsult(vo);
			}else if(vo.getTransfer() != null && vo.getTransfer().equals("etc")){
				lom = statisticsWeekService.detailStatisticsWeek(vo);
			}
			if (resultList != null && resultList.size() > 0) {
				lom = (ListOrderedMap)resultList.get(0);

				pageUtil.setTotalRow(Integer.parseInt(String.valueOf(lom.get("total_cnt"))));
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();

				if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
					returnMessage = (String)request.getAttribute("returnMessage");
				}
			}else {
				if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
					returnMessage = (String)request.getAttribute("returnMessage");
				}
			}

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("lom", lom);
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
	 * 주간 업무 계획/실적(기타)에 대한 상세 값을 조회함.
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailStatisticsWeek(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);

			String forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsWeekContract_d.jsp";  // 여기를 변경하면 기본 값을 변경할 수 있음.
			String returnMessage = "";
			int result;
			StatisticsWeekForm form = new StatisticsWeekForm();
			StatisticsWeekVO vo = new StatisticsWeekVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			// 국내, 해외 구분 값. local에서 유무를 체크하고 없는 경우 다시 session에서 받아온다.
			setLocale(request, response, form, vo);	
			if(form.getWeekseq() == null){			
				//form.setWeekseq(DateUtil.getWeekOfYear(new Date()));
				setDate(request, response, form, vo);			
			}
			if(request.getParameter("weekFirstDay") != null){
				vo.setWeekFirstDay(request.getParameter("weekFirstDay").replaceAll("-", ""));
			}
			if(request.getParameter("weekLastDay") != null){
				vo.setWeekLastDay(request.getParameter("weekLastDay").replaceAll("-", ""));
			}
			if(vo.getPage_gbn() != null && !vo.getPage_gbn().equals("I")){
				if(vo.getPage_gbn().equals("C")){
					forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsWeekContract_d.jsp";
				}else if(vo.getPage_gbn().equals("S")){
					forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsWeekConsult_d.jsp";
				}else if(vo.getPage_gbn().equals("A")){
					forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsWeekTotal_l.jsp";
				}else if(vo.getPage_gbn().equals("M")){
					String decodeText = vo.getBody_mime();
					HashMap hm = comUtilService.getNamoContentAndFileInfo(decodeText);	
					vo.setCrntweek_rslt((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
					
					String decodeText1 = vo.getBody_mime1();
					HashMap hm1 = comUtilService.getNamoContentAndFileInfo(decodeText1);	
					vo.setNextweek_plan((StringUtil.convertNamoCharsToHtml((String)hm1.get("CONTENT")))); //Cross-site Scripting 방지 처리	
					
					//result = statisticsWeekService.modifyStatisticsWeek(vo);
				}
				result = statisticsWeekService.modifyStatisticsWeekContract(vo);
				returnMessage = messageSource.getMessage("las.msg.succ.modify", null, new RequestContext(request).getLocale());
			}else if(vo.getPage_gbn() != null && vo.getPage_gbn().equals("I")){
				String decodeText = vo.getBody_mime();
				HashMap hm = comUtilService.getNamoContentAndFileInfo(decodeText);	
				vo.setCrntweek_rslt((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
				
				String decodeText1 = vo.getBody_mime1();
				HashMap hm1 = comUtilService.getNamoContentAndFileInfo(decodeText1);	
				vo.setNextweek_plan((StringUtil.convertNamoCharsToHtml((String)hm1.get("CONTENT")))); //Cross-site Scripting 방지 처리				
				
				result = statisticsWeekService.insertStatisticsWeek(vo);
				returnMessage = messageSource.getMessage("las.msg.succ.insert", null, new RequestContext(request).getLocale());
			}
			List resultList = null;
			List resultList1 = null;
			List resultList2 = null;
			List resultList3 = null;
			List resultList4 = null;
			List resultList5 = null;
			List respmanList = null;
			ListOrderedMap lom = null;
			if(vo.getTransfer() != null && vo.getTransfer().equals("contract")){
				resultList = statisticsWeekService.listStatisticsContract(vo);
			}else if(vo.getTransfer() != null && vo.getTransfer().equals("consult")){
				resultList = statisticsWeekService.listStatisticsConsult(vo);
			}else if(vo.getTransfer() != null && vo.getTransfer().equals("A")){
				if(vo.getReg_id().equals("null") || vo.getReg_id().equals("")){
					vo.setReg_id(vo.getSession_user_id());
				}else if(vo.getReg_id().equals("ALL")){
					vo.setReg_id("");
				}				
				respmanList = statisticsWeekService.listRespman(vo);
				
				vo.setPage_gbn("C");
				vo.setResult_yn("R");
				resultList = statisticsWeekService.listStatistics(vo);
				
				vo.setPage_gbn("C");
				vo.setResult_yn("P");
				resultList1 = statisticsWeekService.listStatistics(vo);
				vo.setPage_gbn("S");
				vo.setResult_yn("R");			
				resultList2 = statisticsWeekService.listStatistics(vo);
				vo.setPage_gbn("S");
				vo.setResult_yn("P");			
				resultList3 = statisticsWeekService.listStatistics(vo);	
				
				vo.setPage_gbn("E");
				vo.setResult_yn("R");			
				resultList4 = statisticsWeekService.listStatistics(vo);
				vo.setPage_gbn("E");
				vo.setResult_yn("P");			
				resultList5 = statisticsWeekService.listStatistics(vo);
				if(vo.getReg_id().equals("")){
					vo.setReg_id("ALL");
				}				
			}else if(vo.getTransfer() == null || vo.getTransfer().equals("etc")){
				lom = statisticsWeekService.detailStatisticsWeek(vo);
			}

			ModelAndView mav = new ModelAndView();			

			if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
				returnMessage = (String)request.getAttribute("returnMessage");
			}

			mav.setViewName(forwardURL);
			mav.addObject("respmanList", respmanList);
			mav.addObject("lom", lom);
			mav.addObject("resultList", resultList);
			mav.addObject("resultList1", resultList1);
			mav.addObject("resultList2", resultList2);
			mav.addObject("resultList3", resultList3);
			mav.addObject("resultList4", resultList4);
			mav.addObject("resultList5", resultList5);
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
	public ModelAndView modifyStatisticsWeek(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);

			String forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsWeek_d.jsp";  // 여기를 변경하면 기본 값을 변경할 수 있음.
			String returnMessage = "";
			int result;
			StatisticsWeekForm form = new StatisticsWeekForm();
			StatisticsWeekVO vo = new StatisticsWeekVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			setLocale(request, response, form, vo);
				
			if(form.getWeekseq() == null){			
				//form.setWeekseq(DateUtil.getWeekOfYear(new Date()));
				setDate(request, response, form, vo);			
			}
			if(request.getParameter("weekFirstDay") != null){
				vo.setWeekFirstDay(request.getParameter("weekFirstDay").replaceAll("-", ""));
			}
			if(request.getParameter("weekLastDay") != null){
				vo.setWeekLastDay(request.getParameter("weekLastDay").replaceAll("-", ""));
			}
			
			if(vo.getPage_gbn().equals("C")){
				forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsWeekContract_d.jsp";
			}else if(vo.getPage_gbn().equals("S")){
				forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsWeekConsult_d.jsp";
			}else if(vo.getPage_gbn().equals("A")){
				forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsWeekTotal_l.jsp";
			}else if(vo.getPage_gbn().equals("M")){
				String decodeText = vo.getBody_mime();
				HashMap hm = comUtilService.getNamoContentAndFileInfo(decodeText);	
				vo.setCrntweek_rslt((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
				
				String decodeText1 = vo.getBody_mime1();
				HashMap hm1 = comUtilService.getNamoContentAndFileInfo(decodeText1);	
				vo.setNextweek_plan((StringUtil.convertNamoCharsToHtml((String)hm1.get("CONTENT")))); //Cross-site Scripting 방지 처리	
				
				//result = statisticsWeekService.modifyStatisticsWeek(vo);
			}
			result = statisticsWeekService.modifyStatisticsWeekContract(vo);
			returnMessage = messageSource.getMessage("las.msg.succ.modify", null, new RequestContext(request).getLocale());
			
			
			List resultList = null;
			List resultList1 = null;
			List resultList2 = null;
			List resultList3 = null;
			List resultList4 = null;
			List resultList5 = null;
			List respmanList = null;
			ListOrderedMap lom = null;
			if(vo.getTransfer() != null && vo.getTransfer().equals("contract")){
				resultList = statisticsWeekService.listStatisticsContract(vo);
			}else if(vo.getTransfer() != null && vo.getTransfer().equals("consult")){
				resultList = statisticsWeekService.listStatisticsConsult(vo);
			}else if(vo.getTransfer() != null && vo.getTransfer().equals("A")){
				if(vo.getReg_id().equals("null") || vo.getReg_id().equals("")){
					vo.setReg_id(vo.getSession_user_id());
				}else if(vo.getReg_id().equals("ALL")){
					vo.setReg_id("");
				}				
				respmanList = statisticsWeekService.listRespman(vo);
				
				vo.setPage_gbn("C");
				vo.setResult_yn("R");
				resultList = statisticsWeekService.listStatistics(vo);
				
				vo.setPage_gbn("C");
				vo.setResult_yn("P");
				resultList1 = statisticsWeekService.listStatistics(vo);
				vo.setPage_gbn("S");
				vo.setResult_yn("R");			
				resultList2 = statisticsWeekService.listStatistics(vo);
				vo.setPage_gbn("S");
				vo.setResult_yn("P");			
				resultList3 = statisticsWeekService.listStatistics(vo);	
				
				vo.setPage_gbn("E");
				vo.setResult_yn("R");			
				resultList4 = statisticsWeekService.listStatistics(vo);
				vo.setPage_gbn("E");
				vo.setResult_yn("P");			
				resultList5 = statisticsWeekService.listStatistics(vo);
				if(vo.getReg_id().equals("")){
					vo.setReg_id("ALL");
				}				
			}else if(vo.getTransfer() == null || vo.getTransfer().equals("etc")){
				lom = statisticsWeekService.detailStatisticsWeek(vo);
			}

			ModelAndView mav = new ModelAndView();			

			if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
				returnMessage = (String)request.getAttribute("returnMessage");
			}

			mav.setViewName(forwardURL);
			mav.addObject("respmanList", respmanList);
			mav.addObject("lom", lom);
			mav.addObject("resultList", resultList);
			mav.addObject("resultList1", resultList1);
			mav.addObject("resultList2", resultList2);
			mav.addObject("resultList3", resultList3);
			mav.addObject("resultList4", resultList4);
			mav.addObject("resultList5", resultList5);
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
	public ModelAndView insertStatisticsWeek(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);

			String forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsWeek_d.jsp";  // 여기를 변경하면 기본 값을 변경할 수 있음.
			String returnMessage = "";
			int result;
			StatisticsWeekForm form = new StatisticsWeekForm();
			StatisticsWeekVO vo = new StatisticsWeekVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			// 국내, 해외 구분 값. local에서 유무를 체크하고 없는 경우 다시 session에서 받아온다.
			setLocale(request, response, form, vo);	
			if(form.getWeekseq() == null){			
				//form.setWeekseq(DateUtil.getWeekOfYear(new Date()));
				setDate(request, response, form, vo);			
			}
			if(request.getParameter("weekFirstDay") != null){
				vo.setWeekFirstDay(request.getParameter("weekFirstDay").replaceAll("-", ""));
			}
			if(request.getParameter("weekLastDay") != null){
				vo.setWeekLastDay(request.getParameter("weekLastDay").replaceAll("-", ""));
			}

			String decodeText = vo.getBody_mime();
			HashMap hm = comUtilService.getNamoContentAndFileInfo(decodeText);	
			vo.setCrntweek_rslt((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
			
			String decodeText1 = vo.getBody_mime1();
			HashMap hm1 = comUtilService.getNamoContentAndFileInfo(decodeText1);	
			vo.setNextweek_plan((StringUtil.convertNamoCharsToHtml((String)hm1.get("CONTENT")))); //Cross-site Scripting 방지 처리				
			
			result = statisticsWeekService.insertStatisticsWeek(vo);
			returnMessage = messageSource.getMessage("las.msg.succ.insert", null, new RequestContext(request).getLocale());
				
			ListOrderedMap lom = null;
			lom = statisticsWeekService.detailStatisticsWeek(vo);

			ModelAndView mav = new ModelAndView();			

			if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
				returnMessage = (String)request.getAttribute("returnMessage");
			}

			mav.setViewName(forwardURL);
			mav.addObject("lom", lom);
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
	public void setDate(HttpServletRequest request, HttpServletResponse response, StatisticsWeekForm form, StatisticsWeekVO vo) throws Exception {
		Date aa = new Date();
		int i_day = aa.getDay();

		String sDate = DateUtil.getDate(aa).substring(0, 8);	
		String start_dt = "";
		String end_dt = "";

		if(i_day > 2){ // 화요일보다 크면
			String[] bb = DateUtil.getBothDayOfWeek(sDate, 0);
			start_dt = DateUtil.addDays(bb[0], 2);

			if(aa.getMonth() == 0){
				String cc = aa.getYear()+1900+"";					
				
				if(cc != start_dt.substring(0, 4)){
					start_dt = aa.getYear()+1900+"0101";
				}
			}
			end_dt = sDate;
		}else{
			String[] bb = DateUtil.getBothDayOfWeek(sDate, -1);
			start_dt = DateUtil.addDays(bb[0], 2);
			if(aa.getMonth() == 0){
				String cc = aa.getYear()+1900+"";	
				if(cc != start_dt.substring(0, 4)){
					start_dt = aa.getYear()+1900+"0101";
				}
			}
			end_dt = sDate;					
		}	
		form.setWeekFirstDay(start_dt.substring(0, 4)+"-"+start_dt.substring(4, 6)+"-"+start_dt.substring(6, 8));			
		form.setWeekLastDay(end_dt.substring(0, 4)+"-"+end_dt.substring(4, 6)+"-"+end_dt.substring(6, 8));	
		vo.setWeekFirstDay(start_dt.substring(0, 4)+"-"+start_dt.substring(4, 6)+"-"+start_dt.substring(6, 8));			
		vo.setWeekLastDay(end_dt.substring(0, 4)+"-"+end_dt.substring(4, 6)+"-"+end_dt.substring(6, 8));		
	}
	public void setLocale(HttpServletRequest request, HttpServletResponse response, StatisticsWeekForm form, StatisticsWeekVO vo) throws Exception {
		HttpSession session = request.getSession(false);
		if(request.getParameter("locale") != null){
			if(request.getParameter("locale").equals("F")){
				vo.setDmstfrgn_gbn("F");
				form.setDmstfrgn_gbn("F");
			}else{
				vo.setDmstfrgn_gbn("H");
				form.setDmstfrgn_gbn("H");
			}				
		}else if(vo.getDmstfrgn_gbn() == null){
			if(request.getParameter("locale") != null && request.getParameter("locale").equals("F")){
				vo.setDmstfrgn_gbn("F");
				form.setDmstfrgn_gbn("F");
			}else{
				String locale = (String)session.getAttribute("secfw.session.blngt_orgnz");
				if(locale != null && locale.equals("A00000001")){ // 국내
					locale = "H";
				}else if(locale != null && locale.equals("A00000002")){ // 해외
					locale = "F";
				}else{
					locale = "H";
				}					
				vo.setDmstfrgn_gbn(locale);
				form.setDmstfrgn_gbn(locale);
			}				
		}	
	
	}	
}