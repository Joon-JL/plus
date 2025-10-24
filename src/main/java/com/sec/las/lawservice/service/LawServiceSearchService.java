/**
 * Project Name : 법무시스템
 * File Name : LawServiceSearchService.java
 * Description : 로펌서비스 검색 Service
 * Author : 박병주
 * Date : 2011.10
 * Copyright : 2011 by SAMSUNG. All rights reserved.
 */
package com.sec.las.lawservice.service;

import java.util.List;

import com.sec.las.lawservice.dto.LwsSearchVO;

public interface LawServiceSearchService {
	
	/**
	 * 검색
	 * @param LwsSearchVO
	 * @return List
	 * @throws Exception
	 */
	List listLawServiceSearch(LwsSearchVO vo) throws Exception;
	
	/**
	 * 검색결과  EXCEL 다운로드
	 * @param LwsSearchVO
	 * @return List
	 * @throws Exception
	 */
	List excelLawServiceSearch(LwsSearchVO vo) throws Exception;

}