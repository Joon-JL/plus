/**
 * Project Name : 법무시스템 이식
 * File name	: StatisticsMonthService.java
 * Description	: 법무통계 월간업무 Service
 * Author		: 서백진
 * Date			: 2011. 09. 06
 * Copyright	:
 */
package com.sec.las.statistics.service;

import java.util.HashMap;
import java.util.List;
import org.apache.commons.collections.map.ListOrderedMap;

import com.sec.las.statistics.dto.StatisticsMonthVO;

public interface StatisticsMonthService {
	/**
	 * 월간업무- 전체를 조회한다.
	 * @param  vo StatisticsMonthVO
	 * @return List
	 * @throws Exception
	 */
	List listStatistics(StatisticsMonthVO vo) throws Exception;
	/**
	 * 월간업무- 담당자를 조회한다.
	 * @param  vo StatisticsMonthVO
	 * @return List
	 * @throws Exception
	 */
	List listRespman(StatisticsMonthVO vo) throws Exception;	
	/**
	 * 월간업무를 조회한다.
	 * @param  vo StatisticsMonthVO
	 * @return ListOrderedMap
	 * @throws Exception
	 */		
	ListOrderedMap detailStatisticsMonth(StatisticsMonthVO vo) throws Exception;
	
	/**
	 * 월간업무(기타)를 등록한다.
	 * @param  vo StatisticsMonthVO
	 * @return int
	 * @throws Exception
	 */		
	int insertStatisticsMonth(StatisticsMonthVO vo) throws Exception;

	/**
	 * 월간업무를 수정한다.
	 * @param  vo StatisticsMonthVO
	 * @return int
	 * @throws Exception
	 */		
	int modifyStatisticsMonth(StatisticsMonthVO vo) throws Exception;	

	/**
	 * 월간업무(기타)를 조회(중복)한다.
	 * @param  vo StatisticsMonthVO
	 * @return ListOrderedMap
	 * @throws Exception
	 */		
	ListOrderedMap selectStatisticsMonth(StatisticsMonthVO vo) throws Exception;
	
	/**
	 * 월간업무(계약)를 조회한다.
	 * @param  vo StatisticsMonthVO
	 * @return ListOrderedMap
	 * @throws Exception
	 */		
	List listStatisticsContract(StatisticsMonthVO vo) throws Exception;	
	
	/**
	 * 월간업무(자문)를 조회한다.
	 * @param  vo StatisticsMonthVO
	 * @return ListOrderedMap
	 * @throws Exception
	 */		
	List listStatisticsConsult(StatisticsMonthVO vo) throws Exception;
	
	/**
	 * 월간업무(전체)를 수정한다.
	 * @param  vo StatisticsMonthVO
	 * @return int
	 * @throws Exception
	 */		
	int modifyStatisticsMonthContract(StatisticsMonthVO vo) throws Exception;		
}