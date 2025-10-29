/**
 * Project Name : 법무시스템 이식
 * File name	: StatisticsService.java
 * Description	: 법무통계 Service
 * Author		: 서백진
 * Date			: 2011. 09. 06
 * Copyright	:
 */
package com.sec.las.statistics.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sec.las.statistics.dto.StatisticsMonthVO;
import com.sec.las.statistics.dto.StatisticsVO;

public interface StatisticsService {
	/**
	 * 담당자배정현황 목록을 조회한다.
	 * @param  vo StatisticsVO
	 * @return List
	 * @throws Exception
	 */
	List listStatistics(StatisticsVO vo) throws Exception;

	/**
	 * 개인별 통계 현황을 조회한다.
	 * @param  vo StatisticsVO
	 * @return List
	 * @throws Exception
	 */	
	List listPersonStatistics(StatisticsVO vo) throws Exception;
	
	/**
	 * 팀별 통계 현황을 조회한다.
	 * @param  vo StatisticsVO
	 * @return List
	 * @throws Exception
	 */	
	List listDeptStatistics(StatisticsVO vo) throws Exception;	
	/**
	 * 사업부별 통계 현황을 조회한다.
	 * @param  vo StatisticsVO
	 * @return List
	 * @throws Exception
	 */	
	List listDeptStatistics1(StatisticsVO vo) throws Exception;
	
	/**
	 * 검색 조건을 조회한다.
	 * @param  vo StatisticsVO
	 * @return ListOrderedMap
	 * @throws Exception
	 */		
	ListOrderedMap selectStatisticsSearch(StatisticsVO vo) throws Exception;
	/**
	 * 통계 타이틀
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listCntrtDiv(StatisticsVO vo) throws Exception ;
	/**
	 * 담당자배정 현황
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Map listStatisticsAssign(StatisticsVO vo) throws Exception ;
	
	/**
	 * 담당자별 통계 현황
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listStatisticsPerson(StatisticsVO vo) throws Exception ;
	
	/**
	 * 사업부별 현황
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Map listStatisticsDept(StatisticsVO vo) throws Exception ;
	
	/**
	 * 법무팀별 통계 현황
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listStatisticsLaw(StatisticsVO vo) throws Exception ;
	
	/**
	 * 관계사통계 : 전자 검토자 통계
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listStatisticsConsideration(StatisticsVO vo) throws Exception ;
	
	
	/**
	 * 관계사통계 : 전자 검토자 통계
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listStatisticsElecReviewer(StatisticsVO vo) throws Exception ;
	
	
	/**
	 * 관계사통계 : 단계별 소요시간
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listStatisticsLeadtimeByStep(StatisticsVO vo) throws Exception ;
		
	
	/**
	 * 관계사통계 : 계약체결건수
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listStatisticsConsultation(StatisticsVO vo) throws Exception ;

	
	/**
	 * 부서별 전사 계약현황
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listStatisticsContract(StatisticsVO vo) throws Exception ;
	/**
	 * 부서별 전사 자문현황
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listStatisticsLawCnslt(StatisticsVO vo) throws Exception ;
	
	/**
	 * 월별 전사 계약현황
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listStatisticsContractByMonth(StatisticsVO vo) throws Exception ;

	/**
	 * 구주1 계약현황
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Map listStatisticsGuju_law1(StatisticsVO vo) throws Exception ;
	
	/**
	 * 구주1 계약현황 Excel download
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Map listStatisticsGuju_law1_Excel(StatisticsVO vo) throws Exception ;
	
	/**
	 * 구주 기존통계 화면 : Contract & Legal Advice Statistics
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Map listStatisticsContLegalAdvice(StatisticsVO vo) throws Exception ;
	
	/**
	 * 구주 기존통계 화면 : Contract & Legal Advice Statistics Excel download
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Map listStatisticsContLegalAdvice_Excel(StatisticsVO vo) throws Exception ;
	
	/**
	 * Lapsed time by Request
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listStatisticsLap_time(StatisticsVO vo) throws Exception ;
 
	/**
	 * Lapsed time by Request Excel download
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Map listStatisticsLap_time_Excel(StatisticsVO vo) throws Exception ;
	
}