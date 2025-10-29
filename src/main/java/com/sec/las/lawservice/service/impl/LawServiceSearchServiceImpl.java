/**
 * Project Name : 법무시스템
 * File Name : LawServiceSearchServiceImpl.java
 * Description : 로펌서비스 검색 ServiceImpl
 * Author : 박병주
 * Date : 2011.10
 * Copyright : 2011 by SAMSUNG. All rights reserved.
 */
package com.sec.las.lawservice.service.impl;

import java.util.List;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sec.las.lawservice.dto.LwsSearchVO;
import com.sec.las.lawservice.service.LawServiceSearchService;

public class LawServiceSearchServiceImpl extends CommonServiceImpl implements LawServiceSearchService {
	
	/**
	 * 검색
	 * @param LwsSearchVO
	 * @return List
	 * @throws Exception
	 */
	public List listLawServiceSearch(LwsSearchVO vo) throws Exception {
		return commonDAO.list("las.lawservice.listLawfirmSearch", vo);
	}
	
	/**
	 * 검색결과  EXCEL 다운로드
	 * @param LwsSearchVO
	 * @return List
	 * @throws Exception
	 */
	public List excelLawServiceSearch(LwsSearchVO vo) throws Exception {
		return commonDAO.list("las.lawservice.excelLawfirmSearch", vo);
	}

}