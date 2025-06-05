/**
 * Project Name : 법무시스템 이식
 * File name	: StatisticsMonthController.java
 * Description	: 법무통계(월간업무)  Controller
 * Author		: 서백진
 * Date			: 2011. 09. 06
 * Copyright	:
 */
package com.sec.las.statistics.control;

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
import com.sds.secframework.util.service.ComUtilService;
import com.sec.las.statistics.dto.StatisticsMonthForm;
import com.sec.las.statistics.dto.StatisticsMonthVO;
import com.sec.las.statistics.service.StatisticsMonthService;
public class StatisticsMonthController extends CommonController {
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
	private StatisticsMonthService statisticsMonthService;
	public void setStatisticsMonthService(StatisticsMonthService statisticsMonthService) {
		this.statisticsMonthService = statisticsMonthService;
	}
	/**
	 * 통계 월간업무 목록 조회
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listStatisticsMonth(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);

			String forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsMonth_l.jsp";
			StatisticsMonthForm form = new StatisticsMonthForm();
			StatisticsMonthVO vo = new StatisticsMonthVO();
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
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

			List respmanList = statisticsMonthService.listRespman(vo);
			vo.setPage_gbn("C");
			vo.setResult_yn("R");
			List resultList = statisticsMonthService.listStatistics(vo);

			vo.setPage_gbn("C");
			vo.setResult_yn("P");
			List resultList1 = statisticsMonthService.listStatistics(vo);
			vo.setPage_gbn("S");
			vo.setResult_yn("R");			
			List resultList2 = statisticsMonthService.listStatistics(vo);
			vo.setPage_gbn("S");
			vo.setResult_yn("P");			
			List resultList3 = statisticsMonthService.listStatistics(vo);	
			
			vo.setPage_gbn("E");
			vo.setResult_yn("R");			
			List resultList4 = statisticsMonthService.listStatistics(vo);
			vo.setPage_gbn("E");
			vo.setResult_yn("P");			
			List resultList5 = statisticsMonthService.listStatistics(vo);				
			
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
	 * 통계 등록 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardStatistics(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);

			StatisticsMonthForm form = new StatisticsMonthForm();
			StatisticsMonthVO vo = new StatisticsMonthVO();
			ListOrderedMap lom = null;
			bind(request, form);
			bind(request, vo);

			String forwardURL = "";
			if(vo.getTransfer() != null && vo.getTransfer().equals("contract")){
				forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsMonthContract_d.jsp";
			}else if(vo.getTransfer() != null && vo.getTransfer().equals("consult")){
				forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsMonthConsult_d.jsp";
			}else if(vo.getTransfer() != null && vo.getTransfer().equals("etc")){
				forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsMonth_d.jsp";
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
				resultList = statisticsMonthService.listStatisticsContract(vo);
			}else if(vo.getTransfer() != null && vo.getTransfer().equals("consult")){
				resultList = statisticsMonthService.listStatisticsConsult(vo);
			}else if(vo.getTransfer() != null && vo.getTransfer().equals("etc")){
				lom = statisticsMonthService.detailStatisticsMonth(vo);
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
	 * 월간업무 상세
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailStatisticsMonth(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);
			StatisticsMonthForm form = new StatisticsMonthForm();
			StatisticsMonthVO vo = new StatisticsMonthVO();
			int result;
			String returnMessage = "";
			String forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsMonthContract_d.jsp";
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			setLocale(request, response, form, vo);	
			
			if(vo.getPage_gbn() != null && !vo.getPage_gbn().equals("I")){
				if(vo.getPage_gbn().equals("C")){
					forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsMonthContract_d.jsp";
				}else if(vo.getPage_gbn().equals("S")){
					forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsMonthConsult_d.jsp";
				}else if(vo.getPage_gbn().equals("A")){
					forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsMonth_l.jsp";
				}else if(vo.getPage_gbn().equals("M")){
					String decodeText = vo.getBody_mime();
					HashMap hm = comUtilService.getNamoContentAndFileInfo(decodeText);	
					vo.setCrntmonth_rslt((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
					
					String decodeText1 = vo.getBody_mime1();
					HashMap hm1 = comUtilService.getNamoContentAndFileInfo(decodeText1);	
					vo.setNextmonth_plan((StringUtil.convertNamoCharsToHtml((String)hm1.get("CONTENT")))); //Cross-site Scripting 방지 처리	
					
					//result = statisticsMonthService.modifyStatisticsMonth(vo);
				}
				result = statisticsMonthService.modifyStatisticsMonthContract(vo);
				returnMessage = messageSource.getMessage("las.msg.succ.modify", null, new RequestContext(request).getLocale());
			}else if(vo.getPage_gbn() != null && vo.getPage_gbn().equals("I")){
				String decodeText = vo.getBody_mime();
				HashMap hm = comUtilService.getNamoContentAndFileInfo(decodeText);	
				vo.setCrntmonth_rslt((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
				
				String decodeText1 = vo.getBody_mime1();
				HashMap hm1 = comUtilService.getNamoContentAndFileInfo(decodeText1);	
				vo.setNextmonth_plan((StringUtil.convertNamoCharsToHtml((String)hm1.get("CONTENT")))); //Cross-site Scripting 방지 처리				
				
				result = statisticsMonthService.insertStatisticsMonth(vo);
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
				resultList = statisticsMonthService.listStatisticsContract(vo);
			}else if(vo.getTransfer() != null && vo.getTransfer().equals("consult")){
				resultList = statisticsMonthService.listStatisticsConsult(vo);
			}else if(vo.getTransfer() != null && vo.getTransfer().equals("A")){
				if(vo.getReg_id().equals("null") || vo.getReg_id().equals("")){
					vo.setReg_id(vo.getSession_user_id());
				}else if(vo.getReg_id().equals("ALL")){
					vo.setReg_id("");
				}				
				respmanList = statisticsMonthService.listRespman(vo);
				
				vo.setPage_gbn("C");
				vo.setResult_yn("R");
				resultList = statisticsMonthService.listStatistics(vo);
				
				vo.setPage_gbn("C");
				vo.setResult_yn("P");
				resultList1 = statisticsMonthService.listStatistics(vo);
				vo.setPage_gbn("S");
				vo.setResult_yn("R");			
				resultList2 = statisticsMonthService.listStatistics(vo);
				vo.setPage_gbn("S");
				vo.setResult_yn("P");			
				resultList3 = statisticsMonthService.listStatistics(vo);	
				
				vo.setPage_gbn("E");
				vo.setResult_yn("R");			
				resultList4 = statisticsMonthService.listStatistics(vo);
				vo.setPage_gbn("E");
				vo.setResult_yn("P");			
				resultList5 = statisticsMonthService.listStatistics(vo);
				if(vo.getReg_id().equals("")){
					vo.setReg_id("ALL");
				}				
			}else if(vo.getTransfer() == null || vo.getTransfer().equals("etc")){
				lom = statisticsMonthService.detailStatisticsMonth(vo);
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
	public ModelAndView modifyStatisticsMonth(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);
			StatisticsMonthForm form = new StatisticsMonthForm();
			StatisticsMonthVO vo = new StatisticsMonthVO();
			int result;
			String returnMessage = "";
			String forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsMonthContract_d.jsp";
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			setLocale(request, response, form, vo);	

			if(vo.getPage_gbn().equals("C")){
				forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsMonthContract_d.jsp";
			}else if(vo.getPage_gbn().equals("S")){
				forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsMonthConsult_d.jsp";
			}else if(vo.getPage_gbn().equals("A")){
				forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsMonth_l.jsp";
			}else if(vo.getPage_gbn().equals("M")){
				String decodeText = vo.getBody_mime();
				HashMap hm = comUtilService.getNamoContentAndFileInfo(decodeText);	
				vo.setCrntmonth_rslt((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
				
				String decodeText1 = vo.getBody_mime1();
				HashMap hm1 = comUtilService.getNamoContentAndFileInfo(decodeText1);	
				vo.setNextmonth_plan((StringUtil.convertNamoCharsToHtml((String)hm1.get("CONTENT")))); //Cross-site Scripting 방지 처리	
				
				//result = statisticsMonthService.modifyStatisticsMonth(vo);
			}
			result = statisticsMonthService.modifyStatisticsMonthContract(vo);
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
				resultList = statisticsMonthService.listStatisticsContract(vo);
			}else if(vo.getTransfer() != null && vo.getTransfer().equals("consult")){
				resultList = statisticsMonthService.listStatisticsConsult(vo);
			}else if(vo.getTransfer() != null && vo.getTransfer().equals("A")){
				if(vo.getReg_id().equals("null") || vo.getReg_id().equals("")){
					vo.setReg_id(vo.getSession_user_id());
				}else if(vo.getReg_id().equals("ALL")){
					vo.setReg_id("");
				}				
				respmanList = statisticsMonthService.listRespman(vo);
				
				vo.setPage_gbn("C");
				vo.setResult_yn("R");
				resultList = statisticsMonthService.listStatistics(vo);
				
				vo.setPage_gbn("C");
				vo.setResult_yn("P");
				resultList1 = statisticsMonthService.listStatistics(vo);
				vo.setPage_gbn("S");
				vo.setResult_yn("R");			
				resultList2 = statisticsMonthService.listStatistics(vo);
				vo.setPage_gbn("S");
				vo.setResult_yn("P");			
				resultList3 = statisticsMonthService.listStatistics(vo);	
				
				vo.setPage_gbn("E");
				vo.setResult_yn("R");			
				resultList4 = statisticsMonthService.listStatistics(vo);
				vo.setPage_gbn("E");
				vo.setResult_yn("P");			
				resultList5 = statisticsMonthService.listStatistics(vo);
				if(vo.getReg_id().equals("")){
					vo.setReg_id("ALL");
				}				
			}else if(vo.getTransfer() == null || vo.getTransfer().equals("etc")){
				lom = statisticsMonthService.detailStatisticsMonth(vo);
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
	public ModelAndView insertStatisticsMonth(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);
			StatisticsMonthForm form = new StatisticsMonthForm();
			StatisticsMonthVO vo = new StatisticsMonthVO();
			int result;
			String returnMessage = "";
			String forwardURL = "/WEB-INF/jsp/las/statistics/StatisticsMonth_d.jsp";
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			setLocale(request, response, form, vo);	

			String decodeText = vo.getBody_mime();
			HashMap hm = comUtilService.getNamoContentAndFileInfo(decodeText);	
			vo.setCrntmonth_rslt((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
			
			String decodeText1 = vo.getBody_mime1();
			HashMap hm1 = comUtilService.getNamoContentAndFileInfo(decodeText1);	
			vo.setNextmonth_plan((StringUtil.convertNamoCharsToHtml((String)hm1.get("CONTENT")))); //Cross-site Scripting 방지 처리				
			
			result = statisticsMonthService.insertStatisticsMonth(vo);
			returnMessage = messageSource.getMessage("las.msg.succ.insert", null, new RequestContext(request).getLocale());
			
			ListOrderedMap lom = null;
			lom = statisticsMonthService.detailStatisticsMonth(vo);

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
	public void setLocale(HttpServletRequest request, HttpServletResponse response, StatisticsMonthForm form, StatisticsMonthVO vo) throws Exception {
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