/**
 * Project Name : 법무시스템
 * File Name : LawServiceStatisticController.java
 * Description : 로펌서비스 통계 Controller
 * Author : 박병주
 * Date : 2011.10
 * Copyright : 2011 by SAMSUNG. All rights reserved.
 */
package com.sec.las.lawservice.control;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.util.service.ComUtilService;
import com.sec.common.clmscom.service.CLMSCommonService;
import com.sec.las.lawservice.dto.EventManageVO;
import com.sec.las.lawservice.dto.LawfirmManageVO;
import com.sec.las.lawservice.dto.LwsSearchForm;
import com.sec.las.lawservice.dto.LwsSearchVO;
import com.sec.las.lawservice.service.EventManageService;
import com.sec.las.lawservice.service.LawServiceStatisticService;
import com.sec.las.lawservice.service.LawyerManageService;

public class LawServiceStatisticController extends CommonController {
	
	private LawServiceStatisticService lawServiceStatisticService;
	public void setLawServiceStatisticService(LawServiceStatisticService lawServiceStatisticService) {
		this.lawServiceStatisticService = lawServiceStatisticService;
	}
	
	private LawyerManageService lawyerManageService;	
	public void setLawyerManageService(LawyerManageService lawyerManageService) {
		this.lawyerManageService = lawyerManageService;
	}
	
	private EventManageService eventManageService;
	public void setEventManageService(EventManageService eventManageService) {
		this.eventManageService = eventManageService;
	}
	
	private CLMSCommonService clmsComService;
	public void setClmsComService(CLMSCommonService clmsComService)
	{
		this.clmsComService = clmsComService;
	}
	
	// ComUtil 서비스
	private ComUtilService comUtilService;

	public void setComUtilService(ComUtilService comUtilService) {
		this.comUtilService = comUtilService;
	}
	
	/**
	 * 총괄별 / DOJ / 년도 사건별 통계
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listLawServiceStatistic(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);

			String forwardURL = "/WEB-INF/jsp/las/lawservice/LawServiceStatistic_l.jsp";

			LwsSearchForm form = new LwsSearchForm();
			LwsSearchVO vo = new LwsSearchVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//XSS
			check_XSS_form(form);
			check_XSS_vo(vo);
			
			// 사건 comboListBox
			EventManageVO evo = new EventManageVO();
			List srch_event_list = lawyerManageService.listLawyerManageGetEventList(evo);
			
			// 로펌 comboListBox
			LawfirmManageVO lvo = new LawfirmManageVO();
			List srch_lawfirm_list = lawyerManageService.getListLawfirm(lvo);
			
			// 년도리스트 : 년도리스트 : 현재 부터 20년전 까지 산출
			List year_list = getYearList(20);

			String returnMessage = "";
			List resultList_group = null;
			List resultList_DOJ = null;
			List resultList_DOJSum = null;
			List resultList_year = null;
			ArrayList<String> resultList_DOJSum_all = null;
			
			if(vo.getSrch_type().equals("group")){
				resultList_group = lawServiceStatisticService.listStasticsGroup(vo);
			} else if(vo.getSrch_type().equals("DOJ")){
				resultList_DOJ = lawServiceStatisticService.listStasticsDOJ(vo);
				resultList_DOJSum = lawServiceStatisticService.listStasticsDOJSum(vo);
				resultList_DOJSum_all = add_sum_DOJ(resultList_DOJSum);
			} else if(vo.getSrch_type().equals("event_all")){
				resultList_year = lawServiceStatisticService.listStasticsEventAll(vo);
			}
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("resultList_group", resultList_group);
			mav.addObject("resultList_DOJ", resultList_DOJ);
			mav.addObject("resultList_DOJSum", resultList_DOJSum);
			mav.addObject("resultList_DOJSum_all", resultList_DOJSum_all);
			mav.addObject("resultList_year", resultList_year);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			mav.addObject("srch_event_list", srch_event_list);
			mav.addObject("srch_lawfirm_list", srch_lawfirm_list);
			mav.addObject("year_list", year_list);

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
	 * 사건/로펌별 통계 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView popLawServiceStatistic(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);
			
			ModelAndView mav = new ModelAndView();

			String forwardURL = "/WEB-INF/jsp/las/lawservice/LawServiceStatistic_p.jsp";

			LwsSearchForm form = new LwsSearchForm();
			LwsSearchVO vo = new LwsSearchVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//XSS
			check_XSS_form(form);
			check_XSS_vo(vo);		
				
			// 사건 comboListBox
			EventManageVO evo = new EventManageVO();
			List srch_event_list = lawyerManageService.listLawyerManageGetEventList(evo);
			
			// 로펌 comboListBox
			LawfirmManageVO lvo = new LawfirmManageVO();
			List srch_lawfirm_list = lawyerManageService.getListLawfirm(lvo);
			
			// 년도리스트 : 현재 부터 20년전 까지 산출
			List year_list = getYearList(20);
			
			// 총괄 comboListBox
			List srch_dept_list = eventManageService.getListDept();			
			List resultList = null;
			List resultSumList = null;
			ListOrderedMap lom_sum = null;
			ArrayList<String> lom_sum_all = null;
			ArrayList<String> lom_sum_exp = null;
			ArrayList<String> lom_sum_fore = null;
			
			if(form.getForward_from().equals("pop") && check_form(form)){
				if(vo.getSrch_money_type().equals("USD")){
					if(vo.getSrch_type().equals("event")){
						resultList = lawServiceStatisticService.listStasticsEventUsd(vo);
						resultSumList = lawServiceStatisticService.listStasticsSumUsd(vo);
						lom_sum_all = add_sum(resultSumList, "ALL");
						lom_sum_exp = add_sum(resultSumList, "EXP");
					} else {
						resultList = lawServiceStatisticService.listStasticsLawfirmUsd(vo);
						resultSumList = lawServiceStatisticService.listStasticsSumUsd(vo);
						lom_sum_all = add_sum(resultSumList, "ALL");
						lom_sum_fore = add_sum(resultSumList, "EXP");
					}					
				} else {
					if(vo.getSrch_type().equals("event")){
						resultList = lawServiceStatisticService.listStasticsEvent(vo);
						resultSumList = lawServiceStatisticService.listStasticsSum(vo);
						lom_sum_all = add_sum(resultSumList, "ALL");
						lom_sum_exp = add_sum(resultSumList, "EXP");
						lom_sum_fore = add_sum(resultSumList, "FORE");
					} else {
						resultList = lawServiceStatisticService.listStasticsLawfirm(vo);
						resultSumList = lawServiceStatisticService.listStasticsSum(vo);
						lom_sum_all = add_sum(resultSumList, "ALL");
						lom_sum_exp = add_sum(resultSumList, "EXP");
						lom_sum_fore = add_sum(resultSumList, "FORE");
					}
				}
			}
			
			String returnMessage = "";			

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

				if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
					returnMessage = (String)request.getAttribute("returnMessage");
				}
			}else {
				if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
					returnMessage = (String)request.getAttribute("returnMessage");
				}
			}

			mav.setViewName(forwardURL);
			mav.addObject("resultList", resultList);
			mav.addObject("resultSumList", resultSumList);
			mav.addObject("lom_sum_all", lom_sum_all);
			mav.addObject("lom_sum_exp", lom_sum_exp);
			mav.addObject("lom_sum_fore", lom_sum_fore);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			mav.addObject("srch_event_list", srch_event_list);
			mav.addObject("srch_lawfirm_list", srch_lawfirm_list);
			mav.addObject("srch_dept_list", srch_dept_list);
			mav.addObject("year_list", year_list);
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
	 * DOJ 통계  합계를 구한다.
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ArrayList<String> add_sum_DOJ(List resultList) throws Exception {
		
		ListOrderedMap lom = null;
		ArrayList<String> lom_sum = new ArrayList<String>();
		
		DecimalFormat df = new  DecimalFormat("#,##0.00");
		double x = 0.00;
		String str_pre_for_amount_sum = null; 
		String str_totamt_sum = null; 
		String str_srvc_amt_sum = null; 
		String str_addtnl_amt_sum = null; 
		String str_exp_amount_ex_sum = null; 
		String str_exp_amount_sum = null; 
		
		BigDecimal pre_for_amount_sum = new BigDecimal(x);
		BigDecimal totamt_sum = new BigDecimal(x);
		BigDecimal srvc_amt_sum = new BigDecimal(x);
		BigDecimal addtnl_amt_sum = new BigDecimal(x);
		BigDecimal exp_amount_exp_sum = new BigDecimal(x);
		BigDecimal exp_amount_sum = new BigDecimal(x);
		
		pre_for_amount_sum.setScale(2);
		totamt_sum.setScale(2);
		srvc_amt_sum.setScale(2);
		addtnl_amt_sum.setScale(2);
		exp_amount_exp_sum.setScale(2);
		exp_amount_sum.setScale(2);
		
	    for(int i=0; i<resultList.size(); i++){
			lom = (ListOrderedMap)resultList.get(i);
			pre_for_amount_sum = pre_for_amount_sum.add(new BigDecimal((String)lom.get("pre_for_amount")));
			totamt_sum = totamt_sum.add(new BigDecimal((String)lom.get("totamt")));
			srvc_amt_sum = srvc_amt_sum.add(new BigDecimal((String)lom.get("srvc_amt")));
			addtnl_amt_sum = addtnl_amt_sum.add(new BigDecimal((String)lom.get("addtnl_amt")));
			exp_amount_exp_sum = exp_amount_exp_sum.add(new BigDecimal((String)lom.get("exp_amount_exp")));
			exp_amount_sum = exp_amount_sum.add(new BigDecimal((String)lom.get("exp_amount")));
	    } 
	    
	    str_pre_for_amount_sum = df.format(pre_for_amount_sum.doubleValue()); 
	    str_totamt_sum = df.format(totamt_sum.doubleValue()); 
	    str_srvc_amt_sum = df.format(srvc_amt_sum.doubleValue()); 
	    str_addtnl_amt_sum = df.format(addtnl_amt_sum.doubleValue()); 
	    str_exp_amount_ex_sum = df.format(exp_amount_exp_sum.doubleValue()); 
	    str_exp_amount_sum = df.format(exp_amount_sum.doubleValue()); 
 	    
	    lom_sum.add(str_pre_for_amount_sum);
	    lom_sum.add(str_totamt_sum);
	    lom_sum.add(str_srvc_amt_sum);
	    lom_sum.add(str_addtnl_amt_sum);
	    lom_sum.add(str_exp_amount_ex_sum);
	    lom_sum.add(str_exp_amount_sum);

	    return lom_sum;
	}	
	
	/**
	 * 사건/로펌별 통계  합계를 구한다.
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ArrayList<String> add_sum(List resultList, String mode) throws Exception {
		
		ListOrderedMap lom = null;
		ArrayList<String> lom_sum = new ArrayList<String>();
		
		DecimalFormat df = new  DecimalFormat("#,##0.00");
		
		double x = 0.00;
		
		String str_exp01_sum = null; 
		String str_exp02_sum = null; 
		String str_exp03_sum = null; 
		String str_exp04_sum = null; 
		String str_exp05_sum = null; 
		String str_exp06_sum = null; 
		String str_exp07_sum = null; 
		String str_exp08_sum = null; 
		String str_exp09_sum = null; 
		String str_exp10_sum = null; 
		String str_exp11_sum = null; 
		String str_exp12_sum = null; 
		
		String str_for01_sum = null; 
		String str_for02_sum = null; 
		String str_for03_sum = null; 
		String str_for04_sum = null; 
		String str_for05_sum = null; 
		String str_for06_sum = null; 
		String str_for07_sum = null; 
		String str_for08_sum = null; 
		String str_for09_sum = null; 
		String str_for10_sum = null; 
		String str_for11_sum = null; 
		String str_for12_sum = null; 
		
		String str_exp12_sum_all = null;
		String str_for12_sum_all = null;
		
		BigDecimal exp01_sum = new BigDecimal(x);
		BigDecimal exp02_sum = new BigDecimal(x);
		BigDecimal exp03_sum = new BigDecimal(x);
		BigDecimal exp04_sum = new BigDecimal(x);
		BigDecimal exp05_sum = new BigDecimal(x);
		BigDecimal exp06_sum = new BigDecimal(x);
		BigDecimal exp07_sum = new BigDecimal(x);
		BigDecimal exp08_sum = new BigDecimal(x);
		BigDecimal exp09_sum = new BigDecimal(x);
		BigDecimal exp10_sum = new BigDecimal(x);
		BigDecimal exp11_sum = new BigDecimal(x);
		BigDecimal exp12_sum = new BigDecimal(x);
		BigDecimal exp12_sum_all = new BigDecimal(x);
		
		exp01_sum.setScale(2);
		exp02_sum.setScale(2);
		exp03_sum.setScale(2);
		exp04_sum.setScale(2);
		exp05_sum.setScale(2);
		exp06_sum.setScale(2);
		exp07_sum.setScale(2);
		exp08_sum.setScale(2);
		exp09_sum.setScale(2);
		exp10_sum.setScale(2);
		exp11_sum.setScale(2);
		exp12_sum.setScale(2);
		exp12_sum_all.setScale(2); 
		
		BigDecimal for01_sum = new BigDecimal(x);
		BigDecimal for02_sum = new BigDecimal(x);
		BigDecimal for03_sum = new BigDecimal(x);
		BigDecimal for04_sum = new BigDecimal(x);
		BigDecimal for05_sum = new BigDecimal(x);
		BigDecimal for06_sum = new BigDecimal(x);
		BigDecimal for07_sum = new BigDecimal(x);
		BigDecimal for08_sum = new BigDecimal(x);
		BigDecimal for09_sum = new BigDecimal(x);
		BigDecimal for10_sum = new BigDecimal(x);
		BigDecimal for11_sum = new BigDecimal(x);
		BigDecimal for12_sum = new BigDecimal(x);
		BigDecimal for12_sum_all = new BigDecimal(x);
		
		for01_sum.setScale(2);
		for02_sum.setScale(2);
		for03_sum.setScale(2);
		for04_sum.setScale(2);
		for05_sum.setScale(2);
		for06_sum.setScale(2);
		for07_sum.setScale(2);
		for08_sum.setScale(2);
		for09_sum.setScale(2);
		for10_sum.setScale(2);
		for11_sum.setScale(2);
		for12_sum.setScale(2);
		for12_sum_all.setScale(2); 
		
	    for(int i=0; i<resultList.size(); i++){
			lom = (ListOrderedMap)resultList.get(i);
			exp01_sum = exp01_sum.add(new BigDecimal((String)lom.get("exp01_no_comma")));
			exp02_sum = exp02_sum.add(new BigDecimal((String)lom.get("exp02_no_comma")));
			exp03_sum = exp03_sum.add(new BigDecimal((String)lom.get("exp03_no_comma")));
			exp04_sum = exp04_sum.add(new BigDecimal((String)lom.get("exp04_no_comma")));
			exp05_sum = exp05_sum.add(new BigDecimal((String)lom.get("exp05_no_comma")));
			exp06_sum = exp06_sum.add(new BigDecimal((String)lom.get("exp06_no_comma")));
			exp07_sum = exp07_sum.add(new BigDecimal((String)lom.get("exp07_no_comma")));
			exp08_sum = exp08_sum.add(new BigDecimal((String)lom.get("exp08_no_comma")));
			exp09_sum = exp09_sum.add(new BigDecimal((String)lom.get("exp09_no_comma")));
			exp10_sum = exp10_sum.add(new BigDecimal((String)lom.get("exp10_no_comma")));
			exp11_sum = exp11_sum.add(new BigDecimal((String)lom.get("exp11_no_comma")));
			exp12_sum = exp12_sum.add(new BigDecimal((String)lom.get("exp12_no_comma")));
			exp12_sum_all = exp12_sum_all.add(new BigDecimal((String)lom.get("expsum_no_comma")));
			for01_sum = for01_sum.add(new BigDecimal((String)lom.get("for01_no_comma")));
			for02_sum = for02_sum.add(new BigDecimal((String)lom.get("for02_no_comma")));
			for03_sum = for03_sum.add(new BigDecimal((String)lom.get("for03_no_comma")));
			for04_sum = for04_sum.add(new BigDecimal((String)lom.get("for04_no_comma")));
			for05_sum = for05_sum.add(new BigDecimal((String)lom.get("for05_no_comma")));
			for06_sum = for06_sum.add(new BigDecimal((String)lom.get("for06_no_comma")));
			for07_sum = for07_sum.add(new BigDecimal((String)lom.get("for07_no_comma")));
			for08_sum = for08_sum.add(new BigDecimal((String)lom.get("for08_no_comma")));
			for09_sum = for09_sum.add(new BigDecimal((String)lom.get("for09_no_comma")));
			for10_sum = for10_sum.add(new BigDecimal((String)lom.get("for10_no_comma")));
			for11_sum = for11_sum.add(new BigDecimal((String)lom.get("for11_no_comma")));
			for12_sum = for12_sum.add(new BigDecimal((String)lom.get("for12_no_comma")));
			for12_sum_all = for12_sum_all.add(new BigDecimal((String)lom.get("forsum_no_comma")));
	    } 
	    
		str_exp01_sum = df.format(exp01_sum.doubleValue()); 
		str_exp02_sum = df.format(exp02_sum.doubleValue()); 
		str_exp03_sum = df.format(exp03_sum.doubleValue()); 
		str_exp04_sum = df.format(exp04_sum.doubleValue()); 
		str_exp05_sum = df.format(exp05_sum.doubleValue()); 
		str_exp06_sum = df.format(exp06_sum.doubleValue()); 
		str_exp07_sum = df.format(exp07_sum.doubleValue()); 
		str_exp08_sum = df.format(exp08_sum.doubleValue()); 
		str_exp09_sum = df.format(exp09_sum.doubleValue()); 
		str_exp10_sum = df.format(exp10_sum.doubleValue()); 
		str_exp11_sum = df.format(exp11_sum.doubleValue()); 
		str_exp12_sum = df.format(exp12_sum.doubleValue()); 
		str_exp12_sum_all = df.format(exp12_sum_all.doubleValue()); 

		str_for01_sum = df.format(for01_sum.doubleValue()); 
		str_for02_sum = df.format(for02_sum.doubleValue()); 
		str_for03_sum = df.format(for03_sum.doubleValue()); 
		str_for04_sum = df.format(for04_sum.doubleValue()); 
		str_for05_sum = df.format(for05_sum.doubleValue()); 
		str_for06_sum = df.format(for06_sum.doubleValue()); 
		str_for07_sum = df.format(for07_sum.doubleValue()); 
		str_for08_sum = df.format(for08_sum.doubleValue()); 
		str_for09_sum = df.format(for09_sum.doubleValue()); 
		str_for10_sum = df.format(for10_sum.doubleValue()); 
		str_for11_sum = df.format(for11_sum.doubleValue()); 
		str_for12_sum_all = df.format(for12_sum_all.doubleValue());

	    if(mode.equals("ALL")){	    
		    lom_sum.add(str_for01_sum);
		    lom_sum.add(str_exp01_sum);
		    lom_sum.add(str_for02_sum);
		    lom_sum.add(str_exp02_sum);
		    lom_sum.add(str_for03_sum);
		    lom_sum.add(str_exp03_sum);
		    lom_sum.add(str_for04_sum);
		    lom_sum.add(str_exp04_sum);
		    lom_sum.add(str_for05_sum);
		    lom_sum.add(str_exp05_sum);
		    lom_sum.add(str_for06_sum);
		    lom_sum.add(str_exp06_sum);
		    lom_sum.add(str_for07_sum);
		    lom_sum.add(str_exp07_sum);
		    lom_sum.add(str_for08_sum);
		    lom_sum.add(str_exp08_sum);
		    lom_sum.add(str_for09_sum);
		    lom_sum.add(str_exp09_sum);
		    lom_sum.add(str_for10_sum);
		    lom_sum.add(str_exp10_sum);
		    lom_sum.add(str_for11_sum);
		    lom_sum.add(str_exp11_sum);
		    lom_sum.add(str_for12_sum);
		    lom_sum.add(str_exp12_sum);
		    lom_sum.add(str_for12_sum_all);
		    lom_sum.add(str_exp12_sum_all);	    
	    } else if (mode.equals("EXP")){	    	
	    	lom_sum.add(str_exp01_sum);
	    	lom_sum.add(str_exp02_sum);
	    	lom_sum.add(str_exp03_sum);
	    	lom_sum.add(str_exp04_sum);
	    	lom_sum.add(str_exp05_sum);
	    	lom_sum.add(str_exp06_sum);
	    	lom_sum.add(str_exp07_sum);
	    	lom_sum.add(str_exp08_sum);
	    	lom_sum.add(str_exp09_sum);
	    	lom_sum.add(str_exp10_sum);
	    	lom_sum.add(str_exp11_sum);
	    	lom_sum.add(str_exp12_sum);
		    lom_sum.add(str_exp12_sum_all);    	
	    } else if (mode.equals("FORE")){	    	
	    	lom_sum.add(str_for01_sum);
	    	lom_sum.add(str_for02_sum);
	    	lom_sum.add(str_for03_sum);
	    	lom_sum.add(str_for04_sum);
	    	lom_sum.add(str_for05_sum);
	    	lom_sum.add(str_for06_sum);
	    	lom_sum.add(str_for07_sum);
	    	lom_sum.add(str_for08_sum);
	    	lom_sum.add(str_for09_sum);
	    	lom_sum.add(str_for10_sum);
	    	lom_sum.add(str_for11_sum);
	    	lom_sum.add(str_for12_sum);
	    	lom_sum.add(str_for12_sum_all);	    	
	    }

	    return lom_sum;
	}		

	/**
	 * 검색 옵션을 체크한다.
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public boolean check_form(LwsSearchForm form) throws Exception {
		boolean check_flag = true;
		String str_test = "";
		
		str_test += form.getSrch_type();
		str_test += form.getSrch_kbn1();
		str_test += form.getSrch_kbn2();
		str_test += form.getSrch_year_type();
		str_test += form.getSrch_year();
		str_test += form.getSrch_group_cd();
		str_test += form.getSrch_event_no();		
		str_test += form.getSrch_money_type();
		str_test += form.getSrch_lawsuit_trgt_cd();
		str_test += form.getSrch_lawfirm_id();
		
		if(str_test.length() == 0){
			check_flag = false;
		}		
		return check_flag;
	}
	
	/**
	 * 검색 옵션을 체크한다.
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public boolean check_form_pop(LwsSearchForm form) throws Exception {
		boolean check_flag = true;
		String str_test = "";
		
		str_test += form.getSrch_type();
		str_test += form.getSrch_kbn1();
		str_test += form.getSrch_kbn2();
		str_test += form.getSrch_year_type();
		str_test += form.getSrch_year();
		str_test += form.getSrch_group_cd();
		str_test += form.getSrch_event_no();		
		str_test += form.getSrch_money_type();
		str_test += form.getSrch_lawsuit_trgt_cd();
		str_test += form.getSrch_lawfirm_id();
		
		if(str_test.length() == 0){
			check_flag = false;
		}		
		return check_flag;
	}
	
	/**
	 * XSS 를 체크한다.
	 * @param LwsSearchForm
	 * @throws Exception
	 */
	public LwsSearchForm check_XSS_form(LwsSearchForm form) throws Exception {
		
		form.setSrch_type(StringUtil.bvl(form.getSrch_type(),""));
		form.setSrch_kbn1(StringUtil.bvl(form.getSrch_kbn1(),""));
		form.setSrch_kbn2(StringUtil.bvl(form.getSrch_kbn2(),""));
		form.setSrch_year_type(StringUtil.bvl(form.getSrch_year_type(),""));
		form.setSrch_year(StringUtil.bvl(form.getSrch_year(),""));
		form.setSrch_group_cd(StringUtil.bvl(form.getSrch_group_cd(),""));			
		form.setSrch_event_no(StringUtil.bvl(form.getSrch_event_no(),""));
		form.setSrch_money_type(StringUtil.bvl(form.getSrch_money_type(),""));
		form.setSrch_lawsuit_trgt_cd(StringUtil.bvl(form.getSrch_lawsuit_trgt_cd(),""));			
		form.setSrch_lawfirm_id(StringUtil.bvl(form.getSrch_lawfirm_id(),""));			

		return form;
	}
	
	/**
	 * XSS 를 체크한다.
	 * @param LwsSearchVO
	 * @throws Exception
	 */
	public LwsSearchVO check_XSS_vo(LwsSearchVO vo) throws Exception {
		
		vo.setSrch_type(StringUtil.bvl(vo.getSrch_type(),""));
		vo.setSrch_kbn1(StringUtil.bvl(vo.getSrch_kbn1(),""));
		vo.setSrch_kbn2(StringUtil.bvl(vo.getSrch_kbn2(),""));
		vo.setSrch_year_type(StringUtil.bvl(vo.getSrch_year_type(),""));
		vo.setSrch_year(StringUtil.bvl(vo.getSrch_year(),""));
		vo.setSrch_group_cd(StringUtil.bvl(vo.getSrch_group_cd(),""));			
		vo.setSrch_event_no(StringUtil.bvl(vo.getSrch_event_no(),""));
		vo.setSrch_money_type(StringUtil.bvl(vo.getSrch_money_type(),""));
		vo.setSrch_lawsuit_trgt_cd(StringUtil.bvl(vo.getSrch_lawsuit_trgt_cd(),""));			
		vo.setSrch_lawfirm_id(StringUtil.bvl(vo.getSrch_lawfirm_id(),""));	

		return vo;
	}
	
	/**
	 * DOJ 검색결과  EXCEL 다운로드
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ModelAndView excelDownLoad(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);

		String forwardURL = "/WEB-INF/jsp/las/lawservice/LawServiceStatistic_dnExcel.jsp";

		LwsSearchForm form = new LwsSearchForm();
		LwsSearchVO vo = new LwsSearchVO();
		
		bind(request, form);
		bind(request, vo);
		
		COMUtil.getUserAuditInfo(request, form);
		COMUtil.getUserAuditInfo(request, vo);
		
		ModelAndView mav = new ModelAndView();
		
		if(vo.getSrch_type().equals("event") || vo.getSrch_type().equals("lawfirm")){
			mav = popLawServiceStatistic(request,response); 
		} else if(vo.getSrch_type().equals("DOJ")){
			mav = excelDownLoad2(request,response);
		} 
		mav.setViewName(forwardURL);
		return mav;
	}
	
	/**
	 * DOJ 검색결과  EXCEL 다운로드
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ModelAndView excelDownLoad2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);

		String forwardURL = "/WEB-INF/jsp/las/lawservice/LawServiceStatistic_dnExcel.jsp";

		LwsSearchForm form = new LwsSearchForm();
		LwsSearchVO vo = new LwsSearchVO();
		
		bind(request, form);
		bind(request, vo);
		
		COMUtil.getUserAuditInfo(request, form);
		COMUtil.getUserAuditInfo(request, vo);
		
		//XSS
		check_XSS_form(form);
		check_XSS_vo(vo);

		String returnMessage = "";
		List resultList_group = null;
		List resultList_DOJ = null;
		List resultList_DOJSum = null;
		List resultList_year = null;
		ArrayList<String> resultList_DOJSum_all = null;
		
		if(vo.getSrch_type().equals("group")){
			resultList_group = lawServiceStatisticService.listStasticsGroup(vo);
		} else if(vo.getSrch_type().equals("DOJ")){
			resultList_DOJ = lawServiceStatisticService.listStasticsDOJ(vo);
			resultList_DOJSum = lawServiceStatisticService.listStasticsDOJSum(vo);
			resultList_DOJSum_all = add_sum_DOJ(resultList_DOJSum);
		} else if(vo.getSrch_type().equals("event_all")){
			resultList_year = lawServiceStatisticService.listStasticsEventAll(vo);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(forwardURL);
		mav.addObject("resultList_group", resultList_group);
		mav.addObject("resultList_DOJ", resultList_DOJ);
		mav.addObject("resultList_DOJSum", resultList_DOJSum);
		mav.addObject("resultList_DOJSum_all", resultList_DOJSum_all);
		mav.addObject("resultList_year", resultList_year);
		mav.addObject("command", form);


		return mav;
	}
	
	public List getYearList(int year_count) throws Exception {
		ArrayList year_list = null;
		year_list = new ArrayList();
		Integer cyear = Integer.parseInt(DateUtil.year());
		for(int i = 0;i < year_count;i++){
			Integer int_year = cyear - i;
			year_list.add(int_year);
			//year_list.add(int_year);			
		}
		return (List)year_list;
	}
	
}