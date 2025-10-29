/**
* Project Name : 계약관리시스템
* File Name : RefContractService.java
* Description : Reference계약서 Service
* Author : 신남원
* Date : 2010.08.29
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.draft.service;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sec.clm.draft.dto.LibraryVO;

public interface RefContractService {

	/**
	* Reference 계약서 목록조회
	* 
	* @param LibraryVO
	* @return List
	* @throws Exception
	*/
	List listRefContract(LibraryVO vo) throws Exception;

	/**
	* Reference 계약서 저장
	* 
	* @param LibraryVO
	* @return int
	* @throws Exception
	*/
	int insertRefContract(LibraryVO vo) throws Exception;

	/**
	* Reference 계약서 수정
	* 
	* @param LibraryVO
	* @return int
	* @throws Exception
	*/
	int modifyRefContract(LibraryVO vo) throws Exception;

	/**
	* Reference 계약서 삭제
	* 
	* @param LibraryVO
	* @return int
	* @throws Exception
	*/
	int deleteRefContract(LibraryVO vo) throws Exception;

	/**
	* Reference 계약서 상세조회
	* 
	* @param LibraryVO
	* @return ListOrderedMap
	* @throws Exception
	*/
	ListOrderedMap detailRefContract(LibraryVO vo) throws Exception;

	/**
	* Reference 계약서 권한조회
	* 
	* @param LibraryVO
	* @return boolean
	* @throws Exception
	*/
	boolean	authRefContract(String mode, LibraryVO vo) throws Exception;
}