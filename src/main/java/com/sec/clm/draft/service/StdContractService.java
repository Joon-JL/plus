/**
* Project Name : 계약관리시스템
* File Name : StdContractService.java
* Description : 표준계약서 Service
* Author : 이민우
* Date : 2012.07.16
* Copyright : 2012 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.draft.service;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sec.clm.draft.dto.LibraryVO;
import com.sec.clm.draft.dto.StdContractVO;

public interface StdContractService {

	/**
	* 표준계약서 목록조회
	* 
	* @param LibraryVO
	* @return List
	* @throws Exception
	*/
	List listStdContract(StdContractVO vo) throws Exception;

	/**
	* 표준계약서 상세조회
	* 
	* @param LibraryVO
	* @return ListOrderedMap
	* @throws Exception
	*/
	ListOrderedMap detailStdContract(StdContractVO vo) throws Exception;
	
	/**
	* 표준계약서 의뢰회신 List
	* 
	* @param LibraryVO
	* @return ListOrderedMap
	* @throws Exception
	*/
	List listStdContracttHistory(StdContractVO vo) throws Exception;

}