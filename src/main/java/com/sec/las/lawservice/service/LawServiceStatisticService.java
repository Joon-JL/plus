/**
 * Project Name : 법무시스템
 * File Name : LawServiceStatisticService.java
 * Description : 로펌서비스 통계 Service
 * Author : 박병주
 * Date : 2011.10
 * Copyright : 2011 by SAMSUNG. All rights reserved.
 */
package com.sec.las.lawservice.service;

import java.util.List;

import com.sec.las.lawservice.dto.LwsSearchVO;

public interface LawServiceStatisticService {
	/**
	 * 사건별 현황 통계 조회
	 * @param LwsSearchVO
	 * @return List
	 * @throws Exception
	 */
	List listStasticsEvent(LwsSearchVO vo) throws Exception;
	/**
	 * 로펌별 현황 통계 조회
	 * @param LwsSearchVO
	 * @return List
	 * @throws Exception
	 */
	List listStasticsLawfirm(LwsSearchVO vo) throws Exception;
	/**
	 * 사건별/로펌별 합계 현황 통계 조회
	 * @param LwsSearchVO
	 * @return List
	 * @throws Exception
	 */
	List listStasticsSum(LwsSearchVO vo) throws Exception;
	/**
	 * 사건별 현황 통계 조회(USD)
	 * @param LwsSearchVO
	 * @return List
	 * @throws Exception
	 */
	List listStasticsEventUsd(LwsSearchVO vo) throws Exception;
	/**
	 * 로펌별 현황 통계 조회(USD)
	 * @param LwsSearchVO
	 * @return List
	 * @throws Exception
	 */
	List listStasticsLawfirmUsd(LwsSearchVO vo) throws Exception;
	/**
	 * 사건별/로펌별 합계 현황 통계 조회(USD)
	 * @param LwsSearchVO
	 * @return List
	 * @throws Exception
	 */
	List listStasticsSumUsd(LwsSearchVO vo) throws Exception;
	/**
	 * 총괄별 현황 통계 조회
	 * @param LwsSearchVO
	 * @return List
	 * @throws Exception
	 */
	List listStasticsGroup(LwsSearchVO vo) throws Exception;
	/**
	 * 소송상대 DOJ건 현황 통계 조회
	 * @param LwsSearchVO
	 * @return List
	 * @throws Exception
	 */
	List listStasticsDOJ(LwsSearchVO vo) throws Exception;
	/**
	 * 소송상대 DOJ건 현황 통계 조회(합계)
	 * @param LwsSearchVO
	 * @return List
	 * @throws Exception
	 */
	List listStasticsDOJSum(LwsSearchVO vo) throws Exception;
	/**
	 * 년도별 사건 현황 통계 조회
	 * @param LwsSearchVO
	 * @return List
	 * @throws Exception
	 */
	List listStasticsEventAll(LwsSearchVO vo) throws Exception;
	
}