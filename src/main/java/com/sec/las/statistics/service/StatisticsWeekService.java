/**
 * Project Name : 법무시스템 주간보고
 * File name	: StatisticsWeekService.java
 * Description	: 법무통계 - 주간 보고 Service
 * Author		: 김재원
 * Date			: 2011. 09. 15
 * Modify		: 서백진
 * Date			: 2011. 10. 27 
 * Copyright	:
 */
package com.sec.las.statistics.service;

import java.util.HashMap;
import java.util.List;
import org.apache.commons.collections.map.ListOrderedMap;

import com.sec.las.statistics.dto.StatisticsMonthVO;
import com.sec.las.statistics.dto.StatisticsWeekVO;

public interface StatisticsWeekService {
	/**
	 * 주간업무(계약)를 조회한다.
	 * @param  vo StatisticsMonthVO
	 * @return ListOrderedMap
	 * @throws Exception
	 */		
	List listStatisticsContract(StatisticsWeekVO vo) throws Exception;	
	
	/**
	 * 주간업무(자문)를 조회한다.
	 * @param  vo StatisticsMonthVO
	 * @return ListOrderedMap
	 * @throws Exception
	 */		
	List listStatisticsConsult(StatisticsWeekVO vo) throws Exception;

	/**
	 * 주간업무- 담당자를 조회한다.
	 * @param  vo StatisticsWeekVO
	 * @return List
	 * @throws Exception
	 */
	List listRespman(StatisticsWeekVO vo) throws Exception;
	
	/**
	 * 주간업무- 전체를 조회한다.
	 * @param  vo StatisticsWeekVO
	 * @return List
	 * @throws Exception
	 */
	List listStatistics(StatisticsWeekVO vo) throws Exception;
	
	/**
	 * 주간업무- 기타를 조회한다.
	 * @param  vo StatisticsWeekVO
	 * @return ListOrderedMap
	 * @throws Exception
	 */
	ListOrderedMap detailStatisticsWeek(StatisticsWeekVO vo) throws Exception;	
	
	/**
	 * 주간 업무 - 기타을  수정한다.
	 * @param  vo StatisticsWeekVO
	 * @return int
	 * @throws Exception
	 */	
	int modifyStatisticsWeek(StatisticsWeekVO vo) throws Exception;
	
	int insertStatisticsWeek(StatisticsWeekVO vo) throws Exception;
	/**
	 * 주간업무(전체)를 수정한다.
	 * @param  vo StatisticsWeekVO
	 * @return int
	 * @throws Exception
	 */		
	int modifyStatisticsWeekContract(StatisticsWeekVO vo) throws Exception;	
}