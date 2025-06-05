/**
 * Project Name : 법무시스템
 * File Name : LawServiceStatisticServiceImpl.java
 * Description : 로펌서비스 통계 Service
 * Author : 박병주
 * Date : 2011.10
 * Copyright : 2011 by SAMSUNG. All rights reserved.
 */
package com.sec.las.lawservice.service.impl;

import java.util.List;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sec.las.lawservice.dto.LwsSearchVO;
import com.sec.las.lawservice.service.LawServiceStatisticService;

public class LawServiceStatisticServiceImpl extends CommonServiceImpl implements LawServiceStatisticService {
	/**
	 * 사건별 현황 통계 조회
	 * @param LwsSearchVO
	 * @return List
	 * @throws Exception
	 */
	public List listStasticsEvent(LwsSearchVO vo) throws Exception {
		return commonDAO.list("las.lawservice.listStasticsEvent", vo);
	}
	/**
	 * 로펌별 현황 통계 조회
	 * @param LwsSearchVO
	 * @return List
	 * @throws Exception
	 */
	public List listStasticsLawfirm(LwsSearchVO vo) throws Exception {
		return commonDAO.list("las.lawservice.listStasticsLawfirm", vo);
	}
	/**
	 * 사건별/로펌별 합계 현황 통계 조회
	 * @param LwsSearchVO
	 * @return List
	 * @throws Exception
	 */
	public List listStasticsSum(LwsSearchVO vo) throws Exception {
		return commonDAO.list("las.lawservice.listStasticsSum", vo);
	}
	/**
	 * 사건별 현황 통계 조회(USD)
	 * @param LwsSearchVO
	 * @return List
	 * @throws Exception
	 */
	public List listStasticsEventUsd(LwsSearchVO vo) throws Exception {
		return commonDAO.list("las.lawservice.listStasticsEventUsd", vo);
	}
	/**
	 * 로펌별 현황 통계 조회(USD)
	 * @param LwsSearchVO
	 * @return List
	 * @throws Exception
	 */
	public List listStasticsLawfirmUsd(LwsSearchVO vo) throws Exception {
		return commonDAO.list("las.lawservice.listStasticsLawfirmUsd", vo);
	}
	/**
	 * 사건별/로펌별 합계 현황 통계 조회(USD)
	 * @param LwsSearchVO
	 * @return List
	 * @throws Exception
	 */
	public List listStasticsSumUsd(LwsSearchVO vo) throws Exception {
		return commonDAO.list("las.lawservice.listStasticsSumUsd", vo);
	}
	/**
	 * 총괄별 현황 통계 조회
	 * @param LwsSearchVO
	 * @return List
	 * @throws Exception
	 */
	public List listStasticsGroup(LwsSearchVO vo) throws Exception {
		return commonDAO.list("las.lawservice.listStasticsGroup", vo);
	}
	/**
	 * 소송상대 DOJ건 현황 통계 조회
	 * @param LwsSearchVO
	 * @return List
	 * @throws Exception
	 */
	public List listStasticsDOJ(LwsSearchVO vo) throws Exception {
		return commonDAO.list("las.lawservice.listStasticsDOJ", vo);
	}
	/**
	 * 소송상대 DOJ건 현황 통계 조회(합계)
	 * @param LwsSearchVO
	 * @return List
	 * @throws Exception
	 */
	public List listStasticsDOJSum(LwsSearchVO vo) throws Exception {
		return commonDAO.list("las.lawservice.listStasticsDOJSum", vo);
	}	
	/**
	 * 년도별 사건 현황 통계 조회
	 * @param LwsSearchVO
	 * @return List
	 * @throws Exception
	 */
	public List listStasticsEventAll(LwsSearchVO vo) throws Exception {
		return commonDAO.list("las.lawservice.listStasticsEventAll", vo);
	}

}