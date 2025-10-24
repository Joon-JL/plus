/**
 * Project Name : 법무시스템 이식
 * File name	: StatisticsServiceImpl.java
 * Description	: 통계 Service impl(concrete class)
 * Author		: 서백진
 * Date			: 2011. 09. 06
 * Copyright	:
 */
package com.sec.las.statistics.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Calendar;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sec.las.statistics.dto.StatisticsVO;
import com.sec.las.statistics.service.StatisticsService;
/**
 * Description	: 통계 Service impl(concrete class)
 * Author 		: 서백진
 * Date			: 2011. 09. 08
 */

public class StatisticsServiceImpl extends CommonServiceImpl implements StatisticsService {
	/**
	 * 담당자배정현황 목록을 조회한다.
	 * @param  vo StatisticsVO
	 * @return List
	 * @throws Exception
	 */
	public List listStatistics(StatisticsVO vo) throws Exception {
		return commonDAO.list("las.statistics.listStatistics", vo);
	}
	/**
	 * 개인별 통계 현황을 조회한다.
	 * @param  vo StatisticsVO
	 * @return List
	 * @throws Exception
	 */	
	public List listPersonStatistics(StatisticsVO vo) throws Exception {
		return commonDAO.list("las.statistics.listPersonStatistics", vo);
	}
	
	/**
	 * 팀별 통계 현황을 조회한다.
	 * @param  vo StatisticsVO
	 * @return List
	 * @throws Exception
	 */	
	public List listDeptStatistics(StatisticsVO vo) throws Exception {
		return commonDAO.list("las.statistics.listDeptStatistics", vo);
	}	
	/**
	 * 사업부별 통계 현황을 조회한다.
	 * @param  vo StatisticsVO
	 * @return List
	 * @throws Exception
	 */	
	public List listDeptStatistics1(StatisticsVO vo) throws Exception {
		return commonDAO.list("las.statistics.listDeptStatistics1", vo);
	}	
	/**
	 * 검색 조건을 조회한다.
	 * @param  vo StatisticsVO
	 * @return ListOrderedMap
	 * @throws Exception
	 */
	public ListOrderedMap selectStatisticsSearch(StatisticsVO vo) throws Exception {
		List list = commonDAO.list("las.statistics.selectSearchDt", vo);
		ListOrderedMap lom = new ListOrderedMap();
		
		if(list!=null && list.size() != 0){
			lom = (ListOrderedMap)list.get(0);
		}

		return lom;			
	}

	/**
	 * 통계 타이틀
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listCntrtDiv(StatisticsVO vo) throws Exception {
		List list = null ;
		
		// 계약검토인 경우
		if(vo.getSrch_type().equals("C")){
			list = commonDAO.list("las.statistics.selectCntrtTypeDiv", vo);
		}
		// 법률자문인 경우
		else if(vo.getSrch_type().equals("L")){
			vo.setSrch_grp_cd(vo.getSrch_dmstfrgn_gbn().equals("F") ? "N3" : "N2") ; // 해외인경우 N3, 국내/ip는 N2
			list = commonDAO.list("las.statistics.selectLawTypeDiv", vo);
		}
		// 표준계약서인 경우
		else if(vo.getSrch_type().equals("P")){
			vo.setSrch_grp_cd(vo.getSrch_dmstfrgn_gbn().equals("F") ? "N3" : "N2") ; // 해외인경우 N3, 국내/ip는 N2
			list = commonDAO.list("las.statistics.selectLawTypeDiv_p", vo);
		}
		
		return list ;
	}
	
	private List listCntrtDiv2(String last_locale) {
		List list = new ArrayList() ;
		Locale locale1 = new Locale(last_locale);
		
		Map map = new HashMap() ;
		map.put("cd", "DEV");
		//개발/라이선스
		map.put("cd_nm", (String)messageSource.getMessage("las.page.field.statisticsImpl.listCntrtDiv201", null, locale1));
		list.add(map) ;
		
		map = new HashMap() ;
		map.put("cd", "IP");
		//특허
		map.put("cd_nm", (String)messageSource.getMessage("las.page.field.statisticsImpl.listCntrtDiv202", null, locale1));
		list.add(map) ;
		
		map = new HashMap() ;
		map.put("cd", "ETC");
		//일반
		map.put("cd_nm", (String)messageSource.getMessage("las.page.field.statisticsImpl.listCntrtDiv203", null, locale1));
		list.add(map) ;
		
		return list ;
	}
	
	/**
	 * 담당자배정 현황
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Map listStatisticsAssign(StatisticsVO vo) throws Exception {
		Map result = new HashMap() ;
		List divList = null ;
		List resultList = null ;
		
		vo.setSrch_start_dt(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_start_dt(),"")),"-"));
		vo.setSrch_end_dt(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_end_dt(),"")),"-"));
		
		// 계약검토인 경우
		if(vo.getSrch_type().equals("C")){
			
			// 주관부서 세팅
			// H : 국내
			if(vo.getSrch_dmstfrgn_gbn().equals("H")) {
				vo.setSrch_dmstfrgn_gbn("A00000001") ;
			}
			// F : 해외
			else if(vo.getSrch_dmstfrgn_gbn().equals("F")) {
				vo.setSrch_dmstfrgn_gbn("A00000002") ;
			}
			// IP : IP
			else if(vo.getSrch_dmstfrgn_gbn().equals("IP")) {
				vo.setSrch_dmstfrgn_gbn("A00000003") ;
			}
			
			// 계약유형 목록(화면에서 목록 타이틀)
			divList = listCntrtDiv(vo) ;
			
			// 통계조회
			resultList = commonDAO.list("las.statistics.assignCntrt", vo);
			
			Map totalMap = new HashMap() ;
			Locale locale1 = new Locale((String)vo.getSession_user_locale());
			//계
			totalMap.put("user_nm", (String)messageSource.getMessage("las.page.field.statisticsImpl.listCntrtDiv204", null, locale1)) ;
			totalMap.put("total_cnt", 0) ;
			totalMap.put("total_cnt_bu", 0) ;
			
			int total_cnt = 0;
			int total_cnt_bu = 0;
			/*
			// 총합계 계산 및 세팅
			for(int i=0; i<resultList.size(); i++) {
				Map resultMap = (Map)resultList.get(i) ;
				Integer totalMapCnt = (Integer)totalMap.get("total_cnt");
				BigDecimal resultMapCnt = (BigDecimal)resultMap.get("total_cnt");
				Integer totalMapCntBu = (Integer)totalMap.get("total_cnt_bu");
				BigDecimal resultMapCntBu = (BigDecimal)resultMap.get("total_cnt_bu");
				
				totalMap.put("total_cnt", totalMapCnt.intValue() + resultMapCnt.intValue());
				totalMap.put("total_cnt_bu", totalMapCntBu.intValue() + resultMapCntBu.intValue());
				
				total_cnt = total_cnt + resultMapCnt.intValue();
				total_cnt_bu = total_cnt_bu +  resultMapCntBu.intValue();
				
				for(int j=0; j<divList.size(); j++) {
					Map divMap = (Map)divList.get(j) ;
					//totalMap.put(divMap.get("cd"), (totalMap.get(divMap.get("cd"))==null ? 0 : (Integer)totalMap.get(divMap.get("cd")))  + (Integer)resultMap.get((String)divMap.get("cd"))) ;
					//totalMap.put(divMap.get("cd") + "_bu", (totalMap.get(divMap.get("cd") + "_bu")==null ? 0 : (Integer)totalMap.get(divMap.get("cd") + "_bu"))  + (Integer)resultMap.get((String)divMap.get("cd") + "_bu")) ;
					
					if(totalMap.get(divMap.get("cd"))==null){
						totalMap.put(divMap.get("cd"),0);
					} else {
						Integer divMap_cdCnt = (Integer)totalMap.get(divMap.get("cd"));
						Integer resultMap_cdCnt = (Integer)resultMap.get((String)divMap.get("cd"));
						
						totalMap.put(divMap.get("cd"),divMap_cdCnt.intValue() + resultMap_cdCnt.intValue());
					}
					
					if(totalMap.get(divMap.get("cd") + "_bu")==null){
						totalMap.put(divMap.get("cd") + "_bu",0);
					} else {
						Integer divMap_cdCnt_bu = (Integer)totalMap.get(divMap.get("cd")+ "_bu");
						Integer resultMap_cdCnt_bu = (Integer)resultMap.get((String)divMap.get("cd")+ "_bu");
						
						totalMap.put(divMap.get("cd") + "_bu",divMap_cdCnt_bu.intValue() + resultMap_cdCnt_bu.intValue());
					}
									
				}
			}
			
			resultList.add(0, totalMap) ;
			*/
		}
		// 벌률자문인 경우
		else if(vo.getSrch_type().equals("L")){
			vo.setSrch_grp_cd(vo.getSrch_dmstfrgn_gbn().equals("F") ? "N3" : "N2") ; // 해외인경우 N3, 국내/ip는 N2
			// 법률유형 목록(화면에서 목록 타이틀)
			divList = listCntrtDiv(vo) ;
			
			// 통계 조회
			resultList = commonDAO.list("las.statistics.assignLaw", vo);
		} 
		// 표준계약서인 경우
		else if(vo.getSrch_type().equals("P")){
			vo.setSrch_grp_cd(vo.getSrch_dmstfrgn_gbn().equals("F") ? "N3" : "N2") ; // 해외인경우 N3, 국내/ip는 N2
			// 법률유형 목록(화면에서 목록 타이틀)
			divList = listCntrtDiv(vo) ;
			
			// 통계 조회
			resultList = commonDAO.list("las.statistics.assignLaw_p", vo);			
		}
		
		result.put("div_list", divList) ;
		result.put("div_cnt", divList==null ? 0 : divList.size()) ;
		result.put("result_list", resultList) ;
		
		return result ;
	}
	
	
	/**
	 * 담당자별 통계 현황
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listStatisticsPerson(StatisticsVO vo) throws Exception {
		List resultList = null ;
		
		// 계약검토인 경우
		if(vo.getSrch_type().equals("C")){
			// 주관부서 세팅
			// H : 국내
			if(vo.getSrch_dmstfrgn_gbn().equals("H")) {
				vo.setSrch_dmstfrgn_gbn("A00000001") ;
			}
			// F : 해외
			else if(vo.getSrch_dmstfrgn_gbn().equals("F")) {
				vo.setSrch_dmstfrgn_gbn("A00000002") ;
			}
			// IP : IP
			else if(vo.getSrch_dmstfrgn_gbn().equals("IP")) {
				vo.setSrch_dmstfrgn_gbn("A00000003") ;
			}
			
			// 검색기간 - 현재기준
			if(vo.getSrch_period_gbn1().equals("1")) {
				Map dateMap = selectStatisticsSearch(vo) ;
				
				vo.setSrch_beforeweek_s(dateMap.get("beforeweek_s").toString());
				vo.setSrch_beforeweek_e(dateMap.get("beforeweek_e").toString());
				vo.setSrch_beforemonth_s(dateMap.get("beforemonth_s").toString());
				vo.setSrch_beforemonth_e(dateMap.get("beforemonth_e").toString());
				vo.setSrch_curweek_s(dateMap.get("curweek_s").toString());
				vo.setSrch_curweek_e(dateMap.get("curweek_e").toString());
				vo.setSrch_curmonth_s(dateMap.get("curmonth_s").toString());
				vo.setSrch_curmonth_e(dateMap.get("curmonth_e").toString());
								
				resultList = commonDAO.list("las.statistics.personCntrtToday", vo);
			}
			// 검색기간 - 기간별실적
			else {
				// 검색기간 세팅
				// 1. 연도별
				if(vo.getSrch_period_gbn2().equals("1")) {
					//vo.setSrch_start_dt(vo.getSrch_year() + "-01-01") ;
					//vo.setSrch_end_dt(vo.getSrch_year() + "-12-31") ;
					vo.setSrch_start_dt(vo.getSrch_year() + "0101") ;
					vo.setSrch_end_dt(vo.getSrch_year() + "1231") ;
				}
				// 2. 분기별
				else if(vo.getSrch_period_gbn2().equals("2")) {
					// String[] tmpSrchDt = DateUtil.getQuarterFromToDay(vo.getSrch_year(), vo.getSrch_quarter(), "-") ;
					String[] tmpSrchDt = DateUtil.getQuarterFromToDay(vo.getSrch_year(), vo.getSrch_quarter(), "") ;
					vo.setSrch_start_dt(tmpSrchDt[0]) ;
					vo.setSrch_end_dt(tmpSrchDt[1]) ;
				}
				// 3. 월별
				else if(vo.getSrch_period_gbn2().equals("3")) {
					//vo.setSrch_start_dt(vo.getSrch_year() + "-" + vo.getSrch_month() + "-01") ;
					vo.setSrch_start_dt(vo.getSrch_year() + "" + vo.getSrch_month() + "01") ;
					//vo.setSrch_end_dt(DateUtil.lastDay(vo.getSrch_year() + vo.getSrch_month(), "-")) ;
					vo.setSrch_end_dt(DateUtil.lastDay(vo.getSrch_year() + vo.getSrch_month(), "")) ;
					//String[] tmpSrchDt = DateUtil.getQuarterFromToDay(vo.getSrch_year(), vo.getSrch_quarter(), "") ;
				}				
				// 4. 기간별 
				else if(vo.getSrch_period_gbn2().equals("4")) {
					vo.setSrch_start_dt(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_start_dt(),"")),"-"));
					vo.setSrch_end_dt(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_end_dt(),"")),"-"));
				}
				
				resultList = commonDAO.list("las.statistics.personCntrtPeriod", vo);
			}
		}
		// 벌률자문인 경우
		else {
			// 검색기간 - 현재기준
			if(vo.getSrch_period_gbn1().equals("1")) {
				resultList = commonDAO.list("las.statistics.personLawToday", vo);
			}
			// 검색기간 - 기간별실적
			else {
				// 검색기간 세팅
				// 1. 연도별
				if(vo.getSrch_period_gbn2().equals("1")) {
					vo.setSrch_start_dt(vo.getSrch_year() + "0101") ;
					vo.setSrch_end_dt(vo.getSrch_year() + "1231") ;
				}
				// 2. 분기별
				else if(vo.getSrch_period_gbn2().equals("2")) {
					String[] tmpSrchDt = DateUtil.getQuarterFromToDay(vo.getSrch_year(), vo.getSrch_quarter(), "") ;
					vo.setSrch_start_dt(tmpSrchDt[0]) ;
					vo.setSrch_end_dt(tmpSrchDt[1]) ;
				}
				// 3. 월별
				else if(vo.getSrch_period_gbn2().equals("3")) {
					vo.setSrch_start_dt(vo.getSrch_year() + "" + vo.getSrch_month() + "01") ;
					vo.setSrch_end_dt(DateUtil.lastDay(vo.getSrch_year() + vo.getSrch_month(), "")) ;
				}
				// 4. 기간별 
				else if(vo.getSrch_period_gbn2().equals("4")) {
					vo.setSrch_start_dt(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_start_dt(),"")),"-"));
					vo.setSrch_end_dt(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_end_dt(),"")),"-"));
				}
				
				resultList = commonDAO.list("las.statistics.personLawPeriod", vo);
			}
		}
		
		// 합계 계산 및 세팅
		int colCnt = vo.getSrch_period_gbn1().equals("1") ? 5 : 1 ; // 현재기준일 때는 5개, 기간별 실적은 1개
		float[] lt = new float[colCnt] ;
		int[] ltCnt = new int[colCnt] ;
		DecimalFormat df = new DecimalFormat("0.0") ;
		
		Map totalMap = new HashMap() ;
		Locale locale1 = new Locale((String)vo.getSession_user_locale());
		//계
		totalMap.put("respman_nm", (String)messageSource.getMessage("las.page.field.statisticsImpl.listCntrtDiv204", null, locale1)) ;
		
		for(int j=1; j<=colCnt; j++) {
			
			totalMap.put("req_jung" + j, 0) ;
			totalMap.put("chuli_jung" + j, 0) ;
			totalMap.put("reply_jung" + j, 0) ;
			
			totalMap.put("req_bu" + j, 0) ;
			totalMap.put("chuli_bu" + j, 0) ;
			totalMap.put("reply_bu" + j, 0) ;
		}
		
		// 총합계 계산 및 세팅
		for(int i=0; i<resultList.size(); i++) {
			Map resultMap = (Map)resultList.get(i) ;
			
			for(int j=1; j<=colCnt; j++) {
				
				//totalMap.put("req_jung" + j, (totalMap.get("req_jung" + j)==null ? 0 : (Integer)totalMap.get("req_jung" + j))  + (Integer)resultMap.get("req_jung" + j)) ;
				if(totalMap.get("req_jung" + j)==null){
					totalMap.put("req_jung" + j ,0);
				} else {
					Integer totalMap_1 = (Integer)totalMap.get("req_jung" + j);
					Integer resultMap_1 = ((Number)resultMap.get("req_jung" + j)).intValue();					
					totalMap.put("req_jung" + j ,totalMap_1.intValue() + resultMap_1.intValue());
				}
								
				//totalMap.put("chuli_jung" + j, (totalMap.get("chuli_jung" + j)==null ? 0 : (Integer)totalMap.get("chuli_jung" + j))  + (Integer)resultMap.get((String)"chuli_jung" + j)) ;
				if(totalMap.get("chuli_jung" + j)==null){
					totalMap.put("chuli_jung" + j ,0);
				} else {
					Integer totalMap_2 = (Integer)totalMap.get("chuli_jung" + j);
					Integer resultMap_2 = ((Number)resultMap.get("chuli_jung" + j)).intValue();					
					totalMap.put("chuli_jung" + j ,totalMap_2.intValue() + resultMap_2.intValue());
				}
				
				//totalMap.put("reply_jung" + j, (totalMap.get("reply_jung" + j)==null ? 0 : (Integer)totalMap.get("reply_jung" + j))  + (Integer)resultMap.get("reply_jung" + j)) ;
				if(totalMap.get("reply_jung" + j)==null){
					totalMap.put("reply_jung" + j ,0);
				} else {
					Integer totalMap_3 = (Integer)totalMap.get("reply_jung" + j);
					Integer resultMap_3 = ((Number)resultMap.get("reply_jung" + j)).intValue();					
					totalMap.put("reply_jung" + j ,totalMap_3.intValue() + resultMap_3.intValue());
				}
				
				//totalMap.put("req_bu" + j, (totalMap.get("req_bu" + j)==null ? 0 : (Integer)totalMap.get("req_bu" + j))  + (Integer)resultMap.get("req_bu" + j)) ;
				if(totalMap.get("req_bu" + j)==null){
					totalMap.put("req_bu" + j ,0);
				} else {
					Integer totalMap_4 = (Integer)totalMap.get("req_bu" + j);
					Integer resultMap_4 = ((Number)resultMap.get("req_bu" + j)).intValue();					
					totalMap.put("req_bu" + j ,totalMap_4.intValue() + resultMap_4.intValue());
				}
				
				//totalMap.put("chuli_bu" + j, (totalMap.get("chuli_bu" + j)==null ? 0 : (Integer)totalMap.get("chuli_bu" + j))  + (Integer)resultMap.get("chuli_bu" + j)) ;
				if(totalMap.get("chuli_bu" + j)==null){
					totalMap.put("chuli_bu" + j ,0);
				} else {
					Integer totalMap_5 = (Integer)totalMap.get("chuli_bu" + j);
					Integer resultMap_5 = ((Number)resultMap.get("chuli_bu" + j)).intValue();						
					totalMap.put("chuli_bu" + j ,totalMap_5.intValue() + resultMap_5.intValue());
				}
				
				//totalMap.put("reply_bu" + j, (totalMap.get("reply_bu" + j)==null ? 0 : (Integer)totalMap.get("reply_bu" + j))  + (Integer)resultMap.get("reply_bu" + j)) ;
				if(totalMap.get("reply_bu" + j)==null){
					totalMap.put("reply_bu" + j ,0);
				} else {
					Integer totalMap_6 = (Integer)totalMap.get("reply_bu" + j);
					Integer resultMap_6 = ((Number)resultMap.get("reply_bu" + j)).intValue();						
					totalMap.put("reply_bu" + j ,totalMap_6.intValue() + resultMap_6.intValue());
				}
				
				String checkFloat = String.valueOf(resultMap.get("lt" + j + "_sum")) ;

				if(checkFloat.indexOf(".")!=-1){
					lt[j-1] += Float.parseFloat(checkFloat) ;
				}else{
					lt[j-1] += Float.parseFloat(((String)resultMap.get("lt" + j + "_sum"))) ;
				}
				
				//ltCnt[j-1] += ((Integer)resultMap.get("reply_jung" + j)) ; 
				Integer resultMap_7 = ((Number)resultMap.get("reply_jung" + j)).intValue();		
				ltCnt[j-1] += resultMap_7.intValue();
				
			}
			
			// 마지막에서 L/T 의 합계의 평군을 구한다.
			if(i==resultList.size()-1) {
				for(int j=1; j<=colCnt; j++) {					
					totalMap.put("lt"+j, df.format(ltCnt[j-1]==0 ? 0 : lt[j-1] / ltCnt[j-1])) ;
				}
			}
		}		
		
		resultList.add(0, totalMap) ;
				
		return resultList ;
	}
	
	
	
	/**
	 * 사업부별통계 현황
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Map listStatisticsDept(StatisticsVO vo) throws Exception {
		Map result = new HashMap() ;
		List divList = null ;
		List resultList = null ;
		Locale locale1 = new Locale((String)vo.getSession_user_locale());
		
		vo.setSrch_dmstfrgn_gbn("A00000001") ;
		
		// 검색기간 세팅
		// 1. 연도별
		if(vo.getSrch_period_gbn2().equals("1")) {
			vo.setSrch_start_dt(vo.getSrch_year() + "0101") ;
			vo.setSrch_end_dt(vo.getSrch_year() + "1231") ;
		}
		// 2. 분기별
		else if(vo.getSrch_period_gbn2().equals("2")) {
			String[] tmpSrchDt = DateUtil.getQuarterFromToDay(vo.getSrch_year(), vo.getSrch_quarter(), "") ;
			vo.setSrch_start_dt(tmpSrchDt[0]) ;
			vo.setSrch_end_dt(tmpSrchDt[1]) ;
		}
		// 3. 월별
		else if(vo.getSrch_period_gbn2().equals("3")) {
			vo.setSrch_start_dt(vo.getSrch_year() + "" + vo.getSrch_month() + "01") ;
			vo.setSrch_end_dt(DateUtil.lastDay(vo.getSrch_year() + vo.getSrch_month(), "")) ;
		}
		// 4. 기간별 
		else if(vo.getSrch_period_gbn2().equals("4")) {
			vo.setSrch_start_dt(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_start_dt(),"")),"-"));
			vo.setSrch_end_dt(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_end_dt(),"")),"-"));
		}		
				
		Map totalMap = new HashMap() ;
		//계
		// totalMap.put("req_dept", (String)messageSource.getMessage("las.page.field.statisticsImpl.listCntrtDiv204", null, locale1)) ;
		totalMap.put("req_total", 0) ;
		totalMap.put("chuli_total", 0) ;
		totalMap.put("reply_total", 0) ;
		
		// 계약검토인 경우
		if(vo.getSrch_type().equals("C")){
			
			// 계약유형 목록(화면에서 목록 타이틀)
			divList = vo.getSrch_div().equals("T03") ? listCntrtDiv2((String)vo.getSession_user_locale()) : listCntrtDiv(vo) ;
			
			// 합계 계산 및 세팅
			int colCnt = divList.size()+1 ;
			float[] lt = new float[colCnt] ;
			int[] ltCnt = new int[colCnt] ;
			DecimalFormat df = new DecimalFormat("0.0") ;

			resultList = commonDAO.list("las.statistics.deptCntrt", vo);

/*			for(int j=0; j<divList.size(); j++) {				
				Map map = (Map)divList.get(j) ;
				totalMap.put("req_" + map.get("cd"), 0) ;
				totalMap.put("chuli_" + map.get("cd"), 0) ;
				totalMap.put("reply_" + map.get("cd"), 0) ;
			}*/
			
			// 총합계 계산 및 세팅
/*			for(int i=0; i<resultList.size(); i++) {
				Map resultMap = (Map)resultList.get(i) ;
				
				//totalMap.put("req_total", (totalMap.get("req_total")==null ? 0 : (Integer)totalMap.get("req_total"))  + (Integer)resultMap.get("req_total")) ;
				if(totalMap.get("req_total")==null){
					totalMap.put("req_total" ,"0");
				} else {
					Integer totalMap_1 = (Integer)totalMap.get("req_total");
					BigDecimal resultMap_1 = (BigDecimal)resultMap.get("req_total");						
					int result_1 = totalMap_1.intValue() + resultMap_1.intValue();				
					totalMap.put("req_total" ,(String)String.valueOf(result_1));				
				}			
				
				//totalMap.put("chuli_total", (totalMap.get("chuli_total")==null ? 0 : (Integer)totalMap.get("chuli_total"))  + (Integer)resultMap.get((String)"chuli_total")) ;
				if(totalMap.get("chuli_total")==null){
					totalMap.put("chuli_total" ,"0");
				} else {
					Integer totalMap_2 = (Integer)totalMap.get("chuli_total");
					BigDecimal resultMap_2 = (BigDecimal)resultMap.get("chuli_total");					
					//totalMap.put("chuli_total" ,totalMap_2.intValue() + resultMap_2.intValue());
					
					int result_2 = totalMap_2.intValue() + resultMap_2.intValue();				
					totalMap.put("chuli_total" ,(String)String.valueOf(result_2));	
				}	
				
				//totalMap.put("reply_total", (totalMap.get("reply_total")==null ? 0 : (Integer)totalMap.get("reply_total"))  + (Integer)resultMap.get("reply_total")) ;
				if(totalMap.get("reply_total")==null){
					totalMap.put("reply_total" ,"0");
				} else {
					Integer totalMap_3 = (Integer)totalMap.get("reply_total");
					BigDecimal resultMap_3 = (BigDecimal)resultMap.get("reply_total");					
					//totalMap.put("reply_total" ,totalMap_3.intValue() + resultMap_3.intValue());
					int result_3 = totalMap_3.intValue() + resultMap_3.intValue();				
					totalMap.put("reply_total" ,(String)String.valueOf(result_3));	
				}	
				
				lt[0] += Float.parseFloat(((String)resultMap.get("lt_total_sum"))) ;
				//ltCnt[0] += (Integer)resultMap.get("reply_total")  ;
				BigDecimal resultMap_4 = (BigDecimal)resultMap.get("reply_total");			
				ltCnt[0] += resultMap_4.intValue();
				
				for(int j=0; j<divList.size(); j++) {
					Map map = (Map)divList.get(j) ;
					
					//totalMap.put("req_" + map.get("cd"), (totalMap.get("req_" + map.get("cd"))==null ? 0 : (Integer)totalMap.get("req_" + map.get("cd")))  + (Integer)resultMap.get("req_" + map.get("cd"))) ;
					if(totalMap.get("req_" + map.get("cd"))==null){
						totalMap.put("req_" + map.get("cd") ,"0");
					} else {
						Integer totalMap_5 = (Integer)totalMap.get("req_" + map.get("cd"));
						BigDecimal resultMap_5 = (BigDecimal)resultMap.get("req_" + map.get("cd"));					
						//totalMap.put("req_" + map.get("cd") ,totalMap_5.intValue() + resultMap_5.intValue());
						
						int result_5 = totalMap_5.intValue() + resultMap_5.intValue();				
						totalMap.put("req_" + map.get("cd") ,(String)String.valueOf(result_5));	
					}
					
					//totalMap.put("chuli_" + map.get("cd"), (totalMap.get("chuli_" + map.get("cd"))==null ? 0 : (Integer)totalMap.get("chuli_" + map.get("cd")))  + (Integer)resultMap.get((String)"chuli_" + map.get("cd"))) ;
					if(totalMap.get("chuli_" + map.get("cd"))==null){
						totalMap.put("chuli_" + map.get("cd") ,"0");
					} else {
						Integer totalMap_6 = (Integer)totalMap.get("chuli_" + map.get("cd"));
						BigDecimal resultMap_6 = (BigDecimal)resultMap.get("chuli_" + map.get("cd"));					
						// totalMap.put("chuli_" + map.get("cd") ,totalMap_6.intValue() + resultMap_6.intValue());
						
						int result_6 = totalMap_6.intValue() + resultMap_6.intValue();				
						totalMap.put("chuli_" + map.get("cd") ,(String)String.valueOf(result_6));	
					}
					
					//totalMap.put("reply_" + map.get("cd"), (totalMap.get("reply_" + map.get("cd"))==null ? 0 : (Integer)totalMap.get("reply_" + map.get("cd")))  + (Integer)resultMap.get((String)"reply_" + map.get("cd"))) ;
					if(totalMap.get("reply_" + map.get("cd"))==null){
						totalMap.put("reply_" + map.get("cd") ,"0");
					} else {
						Integer totalMap_7 = (Integer)totalMap.get("reply_" + map.get("cd"));
						BigDecimal resultMap_7 = (BigDecimal)resultMap.get("reply_" + map.get("cd"));					
						// totalMap.put("reply_" + map.get("cd") ,totalMap_7.intValue() + resultMap_7.intValue());
						int result_7 = totalMap_7.intValue() + resultMap_7.intValue();				
						totalMap.put("reply_" + map.get("cd") ,(String)String.valueOf(result_7));	
					}
					
					lt[j+1] += Float.parseFloat(((String)resultMap.get("lt_" + map.get("cd")+"_sum"))) ;
					// ltCnt[j+1] += (Integer)resultMap.get("reply_" + map.get("cd")) ; 
					BigDecimal resultMap_8 = (BigDecimal)resultMap.get("reply_" + map.get("cd"));			
					ltCnt[j+1] += resultMap_8.intValue();
				}
				
				// 마지막에서 L/T 의 합계의 평군을 구한다.
				if(i==resultList.size()-1) {
					totalMap.put("lt_total", df.format(ltCnt[0]==0 ? 0 : lt[0] / ltCnt[0])) ;
					
					for(int j=0; j<divList.size(); j++) {
						Map map = (Map)divList.get(j) ;
						totalMap.put("lt_" + map.get("cd"), df.format(ltCnt[j+1]==0 ? 0 : lt[j+1] / ltCnt[j+1])) ;						
					}
				}
			}*/		
			
		}
		// 벌률자문인 경우
		else {
			divList = new ArrayList() ;
			
			// 합계 계산 및 세팅
			int colCnt = divList.size()+1 ;
			float[] lt = new float[colCnt] ;
			int[] ltCnt = new int[colCnt] ;
			DecimalFormat df = new DecimalFormat("0.0") ;
			
			resultList = commonDAO.list("las.statistics.deptLaw", vo);
			
/*			for(int j=0; j<divList.size(); j++) {				
				Map map = (Map)divList.get(j) ;
				totalMap.put("req_" + map.get("cd"), 0) ;
				totalMap.put("chuli_" + map.get("cd"), 0) ;
				totalMap.put("reply_" + map.get("cd"), 0) ;
			}
			
			// 총합계 계산 및 세팅
			for(int i=0; i<resultList.size(); i++) {
				Map resultMap = (Map)resultList.get(i) ;
				
				totalMap.put("req_total", (totalMap.get("req_total")==null ? 0 : (Integer)totalMap.get("req_total"))  + (Integer)resultMap.get("req_total")) ;
				totalMap.put("chuli_total", (totalMap.get("chuli_total")==null ? 0 : (Integer)totalMap.get("chuli_total"))  + (Integer)resultMap.get((String)"chuli_total")) ;
				totalMap.put("reply_total", (totalMap.get("reply_total")==null ? 0 : (Integer)totalMap.get("reply_total"))  + (Integer)resultMap.get("reply_total")) ;
					
				lt[0] += Float.parseFloat(((String)resultMap.get("lt_total_sum"))) ;
				ltCnt[0] += (Integer)resultMap.get("reply_total")  ;

				
				for(int j=0; j<divList.size(); j++) {
					Map map = (Map)divList.get(j) ;
					
					totalMap.put("req_" + map.get("cd"), (totalMap.get("req_" + map.get("cd"))==null ? 0 : (Integer)totalMap.get("req_" + map.get("cd")))  + (Integer)resultMap.get("req_" + map.get("cd"))) ;					
					totalMap.put("chuli_" + map.get("cd"), (totalMap.get("chuli_" + map.get("cd"))==null ? 0 : (Integer)totalMap.get("chuli_" + map.get("cd")))  + (Integer)resultMap.get((String)"chuli_" + map.get("cd"))) ;					
					totalMap.put("reply_" + map.get("cd"), (totalMap.get("reply_" + map.get("cd"))==null ? 0 : (Integer)totalMap.get("reply_" + map.get("cd")))  + (Integer)resultMap.get((String)"reply_" + map.get("cd"))) ;
					
					lt[j+1] += Float.parseFloat(((String)resultMap.get("lt_" + map.get("cd")+"_sum"))) ;
					ltCnt[j+1] += (Integer)resultMap.get("reply_" + map.get("cd")) ; 
				}
				
				// 마지막에서 L/T 의 합계의 평군을 구한다.
				if(i==resultList.size()-1) {
					totalMap.put("lt_total", df.format(ltCnt[0]==0 ? 0 : lt[0] / ltCnt[0])) ;
					
					for(int j=0; j<divList.size(); j++) {
						Map map = (Map)divList.get(j) ;
						totalMap.put("lt_" + map.get("cd"), df.format(ltCnt[j+1]==0 ? 0 : lt[j+1] / ltCnt[j+1])) ;						
					}
				}
			}*/
		}
		
		// resultList.add(0, totalMap) ;		
		result.put("div_list", divList) ;
		result.put("div_cnt", divList==null ? 0 : divList.size()) ;
		result.put("result_list", resultList) ;
		
		return result ;
	}
	
	
	/**
	 * 법무팀별 통계 현황
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listStatisticsLaw(StatisticsVO vo) throws Exception {
		List resultList = null ;
		
		// 계약검토인 경우
		if(vo.getSrch_type().equals("C")){
			
			//국내그룹장 & 관리자
			if(vo.getSrch_dmstfrgn_gbn().equals("H1")){
				vo.setSrch_dmstfrgn_gbn("") ; //전체 조회
			}else if(vo.getSrch_dmstfrgn_gbn().equals("H2")){ //국내 담당자
				vo.setSrch_dmstfrgn_gbn("A00000001") ;
			}else if(vo.getSrch_dmstfrgn_gbn().equals("F1") || vo.getSrch_dmstfrgn_gbn().equals("F2")){ //해외그룹장, 담당자
				vo.setSrch_dmstfrgn_gbn("A00000002") ;
			}else if(vo.getSrch_dmstfrgn_gbn().equals("IP1") || vo.getSrch_dmstfrgn_gbn().equals("IP2")){ //IP그룹장, 담당자
				vo.setSrch_dmstfrgn_gbn("A00000003") ;
			}			
			
			// 검색기간 - 현재기준
			if(vo.getSrch_period_gbn1().equals("1")) {
				Map dateMap = selectStatisticsSearch(vo) ;
				
				vo.setSrch_beforeweek_s(dateMap.get("beforeweek_s").toString());
				vo.setSrch_beforeweek_e(dateMap.get("beforeweek_e").toString());
				vo.setSrch_beforemonth_s(dateMap.get("beforemonth_s").toString());
				vo.setSrch_beforemonth_e(dateMap.get("beforemonth_e").toString());
				vo.setSrch_curweek_s(dateMap.get("curweek_s").toString());
				vo.setSrch_curweek_e(dateMap.get("curweek_e").toString());
				vo.setSrch_curmonth_s(dateMap.get("curmonth_s").toString());
				vo.setSrch_curmonth_e(dateMap.get("curmonth_e").toString());
								
				resultList = commonDAO.list("las.statistics.lawCntrtToday", vo);
			}
			// 검색기간 - 기간별실적
			else {
				// 검색기간 세팅
				// 1. 연도별
				if(vo.getSrch_period_gbn2().equals("1")) {
					//vo.setSrch_start_dt(vo.getSrch_year() + "-01-01") ;
					//vo.setSrch_end_dt(vo.getSrch_year() + "-12-31") ;
					vo.setSrch_start_dt(vo.getSrch_year() + "0101") ;
					vo.setSrch_end_dt(vo.getSrch_year() + "1231") ;
				}
				// 2. 분기별
				else if(vo.getSrch_period_gbn2().equals("2")) {
					//String[] tmpSrchDt = DateUtil.getQuarterFromToDay(vo.getSrch_year(), vo.getSrch_quarter(), "-") ;
					String[] tmpSrchDt = DateUtil.getQuarterFromToDay(vo.getSrch_year(), vo.getSrch_quarter(), "") ;
					vo.setSrch_start_dt(tmpSrchDt[0]) ;
					vo.setSrch_end_dt(tmpSrchDt[1]) ;
				}
				// 3. 월별
				else if(vo.getSrch_period_gbn2().equals("3")) {
					//vo.setSrch_start_dt(vo.getSrch_year() + "-" + vo.getSrch_month() + "-01") ;
					//vo.setSrch_end_dt(DateUtil.lastDay(vo.getSrch_year() + vo.getSrch_month(), "-")) ;
					vo.setSrch_start_dt(vo.getSrch_year() + vo.getSrch_month() + "01") ;
					vo.setSrch_end_dt(DateUtil.lastDay(vo.getSrch_year() + vo.getSrch_month(), "")) ;
				}				
				// 4. 기간별 
				else if(vo.getSrch_period_gbn2().equals("4")) {
					vo.setSrch_start_dt(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_start_dt(),"")),"-"));
					vo.setSrch_end_dt(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_end_dt(),"")),"-"));
				}
				
				resultList = commonDAO.list("las.statistics.lawCntrtPeriod", vo);
			}
		}
		// 벌률자문인 경우
		else {
			
			//국내그룹장 & 관리자
			if(vo.getSrch_dmstfrgn_gbn().equals("H1")){
				vo.setSrch_dmstfrgn_gbn("") ; //전체 조회
			}else if(vo.getSrch_dmstfrgn_gbn().equals("H2")){ //국내 담당자
				vo.setSrch_dmstfrgn_gbn("H") ;
			}else if(vo.getSrch_dmstfrgn_gbn().equals("F1") || vo.getSrch_dmstfrgn_gbn().equals("F2")){ //해외그룹장, 담당자
				vo.setSrch_dmstfrgn_gbn("F") ;
			}else if(vo.getSrch_dmstfrgn_gbn().equals("IP1") || vo.getSrch_dmstfrgn_gbn().equals("IP2")){ //IP그룹장, 담당자
				vo.setSrch_dmstfrgn_gbn("IP") ;
			}		
			
			// 검색기간 - 현재기준
			if(vo.getSrch_period_gbn1().equals("1")) {
				resultList = commonDAO.list("las.statistics.lawLawToday", vo);
			}
			// 검색기간 - 기간별실적
			else {
				// 검색기간 세팅
				// 1. 연도별
				if(vo.getSrch_period_gbn2().equals("1")) {
					//vo.setSrch_start_dt(vo.getSrch_year() + "-01-01") ;
					//vo.setSrch_end_dt(vo.getSrch_year() + "-12-31") ;
					vo.setSrch_start_dt(vo.getSrch_year() + "0101") ;
					vo.setSrch_end_dt(vo.getSrch_year() + "1231") ;
				}
				// 2. 분기별
				else if(vo.getSrch_period_gbn2().equals("2")) {
					// String[] tmpSrchDt = DateUtil.getQuarterFromToDay(vo.getSrch_year(), vo.getSrch_quarter(), "-") ;
					String[] tmpSrchDt = DateUtil.getQuarterFromToDay(vo.getSrch_year(), vo.getSrch_quarter(), "") ;
					vo.setSrch_start_dt(tmpSrchDt[0]) ;
					vo.setSrch_end_dt(tmpSrchDt[1]) ;
				}
				// 3. 월별
				else if(vo.getSrch_period_gbn2().equals("3")) {
					//vo.setSrch_start_dt(vo.getSrch_year() + "-" + vo.getSrch_month() + "-01") ;
					//vo.setSrch_end_dt(DateUtil.lastDay(vo.getSrch_year() + vo.getSrch_month(), "-")) ;
					vo.setSrch_start_dt(vo.getSrch_year() + vo.getSrch_month() + "01") ;
					vo.setSrch_end_dt(DateUtil.lastDay(vo.getSrch_year() + vo.getSrch_month(), "")) ;
				}
				// 4. 기간별 
				else if(vo.getSrch_period_gbn2().equals("4")) {
					vo.setSrch_start_dt(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_start_dt(),"")),"-"));
					vo.setSrch_end_dt(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_end_dt(),"")),"-"));
				}
				
				resultList = commonDAO.list("las.statistics.lawLawPeriod", vo);
			}
		}
		
		// 합계 계산 및 세팅
		int colCnt = vo.getSrch_period_gbn1().equals("1") ? 5 : 1 ; // 현재기준일 때는 5개, 기간별 실적은 1개
		float[] lt = new float[colCnt] ;
		int[] ltCnt = new int[colCnt] ;
		DecimalFormat df = new DecimalFormat("0.0") ;
		
		Map totalMap = new HashMap() ;
		Locale locale1 = new Locale((String)vo.getSession_user_locale());
		//계
		totalMap.put("req_dept", (String)messageSource.getMessage("las.page.field.statisticsImpl.listCntrtDiv204", null, locale1)) ;
		
		for(int j=1; j<=colCnt; j++) {
			
			totalMap.put("req" + j, 0) ;
			totalMap.put("chuli" + j, 0) ;
			totalMap.put("reply" + j, 0) ;
		}
		
		// 총합계 계산 및 세팅
		for(int i=0; i<resultList.size(); i++) {
			Map resultMap = (Map)resultList.get(i) ;
			
			for(int j=1; j<=colCnt; j++) {
				
				//totalMap.put("req" + j, (totalMap.get("req" + j)==null ? 0 : (Integer)totalMap.get("req" + j))  + (Integer)resultMap.get("req" + j)) ;
				if(totalMap.get("req" + j)==null){
					totalMap.put("req" + j ,0);
				} else {
					Integer totalMap_1 = (Integer)totalMap.get("req" + j);
					Integer resultMap_1 = ((Number)resultMap.get("req" + j)).intValue();
					totalMap.put("req" + j ,totalMap_1.intValue() + resultMap_1.intValue());
				}
				
				//totalMap.put("chuli" + j, (totalMap.get("chuli" + j)==null ? 0 : (Integer)totalMap.get("chuli" + j))  + (Integer)resultMap.get((String)"chuli" + j)) ;
				if(totalMap.get("chuli" + j)==null){
					totalMap.put("chuli" + j ,0);
				} else {
					Integer totalMap_2 = (Integer)totalMap.get("chuli" + j);
					Integer resultMap_2 = ((Number)resultMap.get("chuli" + j)).intValue();
					totalMap.put("chuli" + j ,totalMap_2.intValue() + resultMap_2.intValue());
				}
				
				//totalMap.put("reply" + j, (totalMap.get("reply" + j)==null ? 0 : (Integer)totalMap.get("reply" + j))  + (Integer)resultMap.get("reply" + j)) ;
				if(totalMap.get("reply" + j)==null){
					totalMap.put("reply" + j ,0);
				} else {
					Integer totalMap_3 = (Integer)totalMap.get("reply" + j);
					Integer resultMap_3 = ((Number)resultMap.get("reply" + j)).intValue();
					totalMap.put("reply" + j ,totalMap_3.intValue() + resultMap_3.intValue());
				}
				
				lt[j-1] += Float.parseFloat(((String)resultMap.get("lt" + j + "_sum"))) ;
				//ltCnt[j-1] += ((Integer)resultMap.get("reply" + j)) ; 
				Integer resultMap_4 = ((Number)resultMap.get("reply" + j)).intValue();		
				ltCnt[j-1] += resultMap_4.intValue();
				
			}
			
			// 마지막에서 L/T 의 합계의 평군을 구한다.
			if(i==resultList.size()-1) {
				for(int j=1; j<=colCnt; j++) {
					
					totalMap.put("lt"+j, df.format(ltCnt[j-1]==0 ? 0 : lt[j-1] / ltCnt[j-1])) ;
				}
			}
		}
		
		
		resultList.add(0, totalMap) ;
				
		return resultList ;
	}
	
	
	/**
	 * 관계사통계 : 전자 검토자 통계
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listStatisticsElecReviewer(StatisticsVO vo) throws Exception{
		
		List resultList = null ;
		
	
			// H : 국내
			if(vo.getSrch_dmstfrgn_gbn().equals("H")) {
				vo.setSrch_dmstfrgn_gbn("A00000001") ;
			}
			// F : 해외
			else if(vo.getSrch_dmstfrgn_gbn().equals("F")) {
				vo.setSrch_dmstfrgn_gbn("A00000002") ;
			}
			
			// 검색기간 - 현재기준
			if(vo.getSrch_period_gbn1().equals("1")) {
		
				Map dateMap = selectStatisticsSearch(vo) ;
							
					vo.setSrch_beforeweek_s(dateMap.get("beforeweek_s").toString());
					vo.setSrch_beforeweek_e(dateMap.get("beforeweek_e").toString());
					vo.setSrch_beforemonth_s(dateMap.get("beforemonth_s").toString());
					vo.setSrch_beforemonth_e(dateMap.get("beforemonth_e").toString());
					vo.setSrch_curweek_s(dateMap.get("curweek_s").toString());
					vo.setSrch_curweek_e(dateMap.get("curweek_e").toString());
					vo.setSrch_curmonth_s(dateMap.get("curmonth_s").toString());
					vo.setSrch_curmonth_e(dateMap.get("curmonth_e").toString());
											
					resultList = commonDAO.list("las.statistics.listStatisticsElecReviewer", vo);
			}else {
				// 검색기간 - 기간별실적
				// 검색기간 세팅
				// 1. 연도별
				if(vo.getSrch_period_gbn2().equals("1")) {
					
					vo.setSrch_start_dt(vo.getSrch_year() + "0101") ;
					vo.setSrch_end_dt(vo.getSrch_year() + "1231") ;
				}
				// 2. 분기별
				else if(vo.getSrch_period_gbn2().equals("2")) {
					String[] tmpSrchDt = DateUtil.getQuarterFromToDay(vo.getSrch_year(), vo.getSrch_quarter(), "") ;
					vo.setSrch_start_dt(tmpSrchDt[0]) ;
					vo.setSrch_end_dt(tmpSrchDt[1]) ;
				}
				// 3. 월별
				else if(vo.getSrch_period_gbn2().equals("3")) {
					vo.setSrch_start_dt(vo.getSrch_year() + "" + vo.getSrch_month() + "01") ;
					vo.setSrch_end_dt(DateUtil.lastDay(vo.getSrch_year() + vo.getSrch_month(), "")) ;
				}				
				// 4. 기간별 
				else if(vo.getSrch_period_gbn2().equals("4")) {
					vo.setSrch_start_dt(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_start_dt(),"")),"-"));
					vo.setSrch_end_dt(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_end_dt(),"")),"-"));
				}
					resultList = commonDAO.list("las.statistics.listStatisticsElecReviewerP", vo);
			}
		
		
		// 합계 계산 및 세팅
		int colCnt = vo.getSrch_period_gbn1().equals("1") ? 3 : 1 ; // 현재기준일 때는 5개, 기간별 실적은 1개 
		float[] lt = new float[colCnt] ;
		int[] ltCnt = new int[colCnt] ;
		DecimalFormat df = new DecimalFormat("0.0") ;
		
		Map totalMap = new HashMap() ;
		Locale locale1 = new Locale((String)vo.getSession_user_locale());
		//계
		totalMap.put("respman_nm", (String)messageSource.getMessage("las.page.field.statisticsImpl.listCntrtDiv204", null, locale1)) ;
		
		for(int j=1; j<=colCnt; j++) {
			
			totalMap.put("req" + j, 0) ;
			totalMap.put("reply" + j, 0) ;
			
			totalMap.put("req" + j, 0) ;
			totalMap.put("reply" + j, 0) ;
		}
		
		
		
		Integer totalMap_1 = 0;
		BigDecimal resultMap_1;
		
		
		Integer totalMap_3= 0 ;
		BigDecimal resultMap_3;
		
		Integer totalMap_4=0;
		BigDecimal resultMap_4;
		
		Integer totalMap_6=0;
		BigDecimal resultMap_6;
		
		
		BigDecimal resultMap_7;
		
		// 총합계 계산 및 세팅
		for(int i=0; i<resultList.size(); i++) {
			Map resultMap = (Map)resultList.get(i) ;
			
			for(int j=1; j<=colCnt; j++) {
				
				if(totalMap.get("req" + j)==null){
					totalMap.put("req" + j ,0);
				} else {
					 totalMap_1 = (Integer)totalMap.get("req" + j);
					 resultMap_1 = (BigDecimal)resultMap.get("req" + j);					
					totalMap.put("req" + j ,totalMap_1.intValue() + resultMap_1.intValue());
				}
				
				if(totalMap.get("reply" + j)==null){
					totalMap.put("reply" + j ,0);
				} else {
					 totalMap_3 = (Integer)totalMap.get("reply" + j);
					 resultMap_3 = (BigDecimal)resultMap.get("reply" + j);					
					totalMap.put("reply" + j ,totalMap_3.intValue() + resultMap_3.intValue());
				}
				lt[j-1] += Float.parseFloat(((String)resultMap.get("lt" + j + "_sum"))) ;
				//ltCnt[j-1] += ((Integer)resultMap.get("reply_jung" + j)) ; 
				 resultMap_7 = (BigDecimal)resultMap.get("reply" + j);			
				ltCnt[j-1] += resultMap_7.intValue();
				
			}
			
			// 마지막에서 L/T 의 합계의 평군을 구한다.
			if(i==resultList.size()-1) {
				for(int j=1; j<=colCnt; j++) {					
					totalMap.put("lt"+j, df.format(ltCnt[j-1]==0 ? 0 : lt[j-1] / ltCnt[j-1])) ;
				}
			}
		}		
		
		resultList.add(0, totalMap) ;
				
		return resultList ;
	
	}
		
	/**
	 * 관계사통계 : 단계별 소요시간
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listStatisticsLeadtimeByStep(StatisticsVO vo) throws Exception{
		return commonDAO.list("las.statistics.listStatisticsLeadtimeByStep", vo); 
	}	
	
	/**
	 * 관계사통계 : 계약 체결 건수
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listStatisticsConsultation(StatisticsVO vo) throws Exception{
		// 호출 QUERY ID :commonDAO.list("las.statistics.listStatisticsConsultaion", vo); 
		
		List resultList = null ;
		
		if("M".equals(vo.getSrch_div())){// 월별
			if("C".equals(vo.getSrch_type())){
				resultList = commonDAO.list("las.statistics.listStatisticsConsultationM", vo);
			}							
		}else if("W".equals(vo.getSrch_div())){// 주별
			if("C".equals(vo.getSrch_type())){
				resultList = commonDAO.list("las.statistics.listStatisticsConsultationW", vo);
			}
			
			//년주차로 몇월의 몇주째인지 구하기 ex) 26 -> 7월 1주
			Calendar c = Calendar.getInstance();

			String datew ="";
			Map rlist = new HashMap();
			for(int i=0; i<resultList.size();i++){
				rlist = (Map)resultList.get(i);
				rlist.get("gubun");
				if("계".equals((String)rlist.get("gubun"))){
					if(vo.getSession_user_locale().equals("ko")){
						datew = "계";
					}else if(vo.getSession_user_locale().equals("en")){
						datew = "Total";
					}
				}else if(!"계".equals((String)rlist.get("gubun"))){
					c.set( Calendar.WEEK_OF_YEAR, Integer.parseInt((String)rlist.get("gubun")) );
					if(vo.getSession_user_locale().equals("ko")){
						datew =(c.get(Calendar.MONTH)+1) +"월"+c.get(Calendar.DAY_OF_WEEK_IN_MONTH)+"주"+"("+c.get(Calendar.WEEK_OF_YEAR)+"w)" ;
					}else if(vo.getSession_user_locale().equals("en")){
						datew =(c.get(Calendar.MONTH)+1) +"m"+c.get(Calendar.DAY_OF_WEEK_IN_MONTH)+"w"+"("+c.get(Calendar.WEEK_OF_YEAR)+"w)" ;
					}
					
				}
				rlist.put("gubun",datew);
			}
		}
		
		return resultList;
	}
	
	/**
	 * 관계사통계 : 검토 의뢰 건수
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listStatisticsConsideration(StatisticsVO vo) throws Exception{
		
		List resultList = null ;
		
		if("M".equals(vo.getSrch_div())){// 월별
			if("C".equals(vo.getSrch_type())){
				//계약검토
				resultList = commonDAO.list("las.statistics.listStatisticsConsiderationLasM", vo);				
			}else if("L".equals(vo.getSrch_type())){
				//법률자문
				resultList = commonDAO.list("las.statistics.listStatisticsConsiderationLawM", vo);
			}
		}else if("W".equals(vo.getSrch_div())){
			// 주별
			if("C".equals(vo.getSrch_type())){
				//계약검토
				resultList = commonDAO.list("las.statistics.listStatisticsConsiderationLasW", vo);
			}else if("L".equals(vo.getSrch_type())){
				//법률자문
				resultList = commonDAO.list("las.statistics.listStatisticsConsiderationLawW", vo);
			}
			
			//년주차로 몇월의 몇주째인지 구하기 ex) 26 -> 7월 1주
			Calendar c = Calendar.getInstance();

			String datew ="";
			Map rlist = new HashMap();
			for(int i=0; i<resultList.size();i++){
				rlist = (Map)resultList.get(i);
				rlist.get("gubun");
				
				if("계".equals((String)rlist.get("gubun"))){
					if(vo.getSession_user_locale().equals("ko")){
						datew = "계";
					}else if(vo.getSession_user_locale().equals("en")){
						datew = "Total";
					}
				}else if(!"계".equals((String)rlist.get("gubun"))){
					c.set( Calendar.WEEK_OF_YEAR, Integer.parseInt((String)rlist.get("gubun")) );
					
					if(vo.getSession_user_locale().equals("ko")){
						datew =(c.get(Calendar.MONTH)+1) +"월"+c.get(Calendar.DAY_OF_WEEK_IN_MONTH)+"주"+"("+c.get(Calendar.WEEK_OF_YEAR)+"w)" ;
					}else if(vo.getSession_user_locale().equals("en")){
						datew =(c.get(Calendar.MONTH)+1) +"m"+c.get(Calendar.DAY_OF_WEEK_IN_MONTH)+"w"+"("+c.get(Calendar.WEEK_OF_YEAR)+"w)" ;
					}
					
				}
				rlist.put("gubun",datew);
			}
		}
		
		return resultList;
	}
	
	/**
	 * 부서별 전사 계약현황
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listStatisticsContract(StatisticsVO vo) throws Exception {
		//divList = listCntrtDiv(vo) ;
		// 통계조회
		if(vo.getSrch_year()==null) vo.setSrch_year("XXNULL");
		if(vo.getSrch_month()==null) vo.setSrch_month("XXNULL");
		else if(vo.getSrch_month()!=null && vo.getSrch_month().equals("")) vo.setSrch_month("XXNULL");
		return commonDAO.list("las.statistics.listStatisticsContract", vo);

	}
	/**
	 * 부서별 전사 자문현황
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listStatisticsLawCnslt(StatisticsVO vo) throws Exception {
		if(vo.getSrch_year()==null) vo.setSrch_year("XXNULL");
		if(vo.getSrch_month()==null) vo.setSrch_month("XXNULL");
		else if(vo.getSrch_month()!=null && vo.getSrch_month().equals("")) vo.setSrch_month("XXNULL");
		// 통계 조회
		return  commonDAO.list("las.statistics.listStatisticsLaw", vo);

	}
	/**
	 * 월별 전사 계약현황
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listStatisticsContractByMonth(StatisticsVO vo) throws Exception {
		if(vo.getSrch_year()==null) vo.setSrch_year("XXNULL");
		if(vo.getSrch_month()==null) vo.setSrch_month("XXNULL");
		else if(vo.getSrch_month()!=null && vo.getSrch_month().equals("")) vo.setSrch_month("XXNULL");
		// 통계조회
		return commonDAO.list("las.statistics.listStatisticsContractByMonth", vo);
		
	}

	/**
	 * 구주 통계1
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Map listStatisticsGuju_law1(StatisticsVO vo) throws Exception {
		Map result = new HashMap() ;
		List divList = null ;
		List resultList = null ;
		
		vo.setSrch_start_dt(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_start_dt(),"")),"-"));
		vo.setSrch_end_dt(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_end_dt(),"")),"-"));
		
		// 계약검토인 경우
		if(vo.getSrch_type().equals("C")){
			// 통계조회
			resultList = commonDAO.list("las.statistics.guju_law1.c", vo);
		}
		// 벌률자문인 경우
		else if(vo.getSrch_type().equals("L")){
			// 통계 조회
			resultList = commonDAO.list("las.statistics.guju_law1.l", vo);
		} 
		
		result.put("div_list", divList) ;
		result.put("div_cnt", divList==null ? 0 : divList.size()) ;
		result.put("result_list", resultList) ;
		
		return result ;
	}
	
	/**
	 * 담당자배정 현황
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Map listStatisticsGuju_law1_Excel(StatisticsVO vo) throws Exception {
		Map result = new HashMap() ;
		List divList = null ;
		List resultList = null ;
		
		vo.setSrch_start_dt(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_start_dt(),"")),"-"));
		vo.setSrch_end_dt(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_end_dt(),"")),"-"));
		
		// 계약검토인 경우
		if(vo.getSrch_type().equals("C")){
			// 통계조회
			resultList = commonDAO.list("las.statistics.guju_law1_excel.c", vo);
		}
		// 벌률자문인 경우
		else if(vo.getSrch_type().equals("L")){
			// 통계 조회
			resultList = commonDAO.list("las.statistics.guju_law1_excel.l", vo);
		} 
		
		result.put("div_list", divList) ;
		result.put("div_cnt", divList==null ? 0 : divList.size()) ;
		result.put("result_list", resultList) ;
		
		return result ;
	}
	
	/**
	 * 구주 기존통계 화면 : Contract & Legal Advice Statistics
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Map listStatisticsContLegalAdvice(StatisticsVO vo) throws Exception {
		Map result = new HashMap() ;
		List divList = null ;
		List resultList = null ;
		
		vo.setSrch_start_dt(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_start_dt(),"")),"-"));
		vo.setSrch_end_dt(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_end_dt(),"")),"-"));
		
		// SELMS 계약검토인 경우
		if(vo.getSrch_type().equals("C")){
			// 통계조회
			vo.setTop_role(getAccessLevel2(vo));
			resultList = commonDAO.list("las.statistics.contlegaladvice.c", vo);
		}
		// SELMS 법률자문인 경우
		else if(vo.getSrch_type().equals("L")){
			// 통계 조회
			resultList = commonDAO.list("las.statistics.contlegaladvice.l", vo);
			
		}
		// SELMSPLUS 계약검토인 경우
		else if(vo.getSrch_type().equals("PC")){
			// 통계조회
			resultList = commonDAO.list("las.statistics.contlegaladvice.pc", vo);
		}
		// SELMSPLUS 법률자문인 경우
		else if(vo.getSrch_type().equals("PL")){
			// 통계 조회
			resultList = commonDAO.list("las.statistics.contlegaladvice.pl", vo);
		}  
		
		result.put("div_list", divList) ;
		result.put("div_cnt", divList==null ? 0 : divList.size()) ;
		result.put("result_list", resultList) ;
		
		return result ;
	}
	
	/**
	 * 구주 기존통계 화면 : Contract & Legal Advice Statistics Excel download
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Map listStatisticsContLegalAdvice_Excel(StatisticsVO vo) throws Exception {
		Map result = new HashMap() ;
		List divList = null ;
		List resultList = null ;
		
		vo.setSrch_start_dt(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_start_dt(),"")),"-"));
		vo.setSrch_end_dt(StringUtil.removeChar((StringUtil.bvl(vo.getSrch_end_dt(),"")),"-"));
		
		// SELMS 계약검토인 경우
		if(vo.getSrch_type().equals("C")){
			// 통계조회
			resultList = commonDAO.list("las.statistics.contlegaladvice.c", vo);
		}
		// SELMS 법률자문인 경우
		else if(vo.getSrch_type().equals("L")){
			// 통계 조회
			resultList = commonDAO.list("las.statistics.contlegaladvice.l", vo);
		}
		// SELMSPLUS 계약검토인 경우
		else if(vo.getSrch_type().equals("PC")){
			// 통계조회
			resultList = commonDAO.list("las.statistics.contlegaladvice.pc", vo);
		}
		// SELMSPLUS 법률자문인 경우
		else if(vo.getSrch_type().equals("PL")){
			// 통계 조회
			resultList = commonDAO.list("las.statistics.contlegaladvice.pl", vo);
		}  
		
		result.put("div_list", divList) ;
		result.put("div_cnt", divList==null ? 0 : divList.size()) ;
		result.put("result_list", resultList) ;
		
		return result ;
	}
	
	private String getAccessLevel2(StatisticsVO vo) throws Exception {

        /*
			시스템관리자        	RA00, AA00
			법무관리자          	RA01, AA01
			법무담당자          	RA02, AA02
			법무일반 사용자     	RA03, AA03
			cp관리자         RC01, AC01
			사업부계약관리자   RD01, AD01
			사업부계약담당자   RD02, AD02
			시스템운영자        	RM00, AM00
			일반임직원         	RZ00, AZ00

			A:모든것들 조회 
			B:본인등록건 + 자기지역담당건 + 자기한테 배정된 건
			C:본인등록건 + 자기한테 배정된 건          
			D:본인등록건 (아무권한 없는 일반임직원)
			
			RA01    Legal Admin
            RA02    Reviewer
            RZ00    Requester

        */

        String result = "";
        ArrayList roleList = vo.getSession_user_role_cds();
        
        ArrayList userRoleList = new ArrayList();

        if(roleList != null && roleList.size()>0){
        	for(int i=0; i < roleList.size() ;i++){
        		HashMap hm = (HashMap)roleList.get(i);
        		String roleCd = (String)hm.get("role_cd");
        		
        		userRoleList.add(roleCd);
        	}
        }
        
        if(userRoleList != null && userRoleList.size()>0) {
            
        	if(userRoleList.contains("RA00") || userRoleList.contains("RC01") || userRoleList.contains("RA01")) {	
                result = "A";
            } else if (userRoleList.contains("RA02") || userRoleList.contains("RA03")) {
                result = "B";
            } else if (userRoleList.contains("RD01")) {
                result = "C";
            } else {
                result = "D";
            }
            
        }
        return result;
    }
	
	/**
	 * Lapsed time by Request
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listStatisticsLap_time(StatisticsVO vo) throws Exception {
		List resultList = null ;
						
		resultList = commonDAO.list("las.statistics.listStatisticsLap_time", vo);
				
		return resultList ;
	}
	
	/**
	 * Lapsed time by Request
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Map listStatisticsLap_time_Excel(StatisticsVO vo) throws Exception {
		Map result = new HashMap() ;
		List divList = null ;
		List resultList = null ;
		
		resultList = commonDAO.list("las.statistics.listStatisticsLap_time", vo);
		
		result.put("div_list", divList) ;
		result.put("div_cnt", divList==null ? 0 : divList.size()) ;
		result.put("result_list", resultList) ;
		
		return result ;
	}
}