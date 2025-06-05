/**
* Project Name : 계약관리시스템
* File Name : ItemLibraryService.java
* Description : 조항 라이브러리 Service
* Author : 신남원
* Date : 2010.08.31
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.draft.service;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sec.clm.draft.dto.LibraryVO;

public interface ItemLibraryService {

	/**
	* 조항 라이브러리 목록조회
	* 
	* @param LibraryVO
	* @return List
	* @throws Exception
	*/
	List listItemLibrary(LibraryVO vo) throws Exception;

	/**
	* 조항 라이브러리 저장
	* 
	* @param LibraryVO
	* @return int
	* @throws Exception
	*/
	int insertItemLibrary(LibraryVO vo) throws Exception;

	/**
	* 조항 라이브러리 수정
	* 
	* @param LibraryVO
	* @return int
	* @throws Exception
	*/
	int modifyItemLibrary(LibraryVO vo) throws Exception;

	/**
	* 조항 라이브러리 삭제
	* 
	* @param LibraryVO
	* @return int
	* @throws Exception
	*/
	int deleteItemLibrary(LibraryVO vo) throws Exception;

	/**
	* 조항 라이브러리 상세조회
	* 
	* @param LibraryVO
	* @return ListOrderedMap
	* @throws Exception
	*/
	ListOrderedMap detailItemLibrary(LibraryVO vo) throws Exception;

	/**
	* 조항 라이브러리 권한조회
	* 
	* @param LibraryVO
	* @return boolean
	* @throws Exception
	*/
	boolean	authItemLibrary(String mode, LibraryVO vo) throws Exception;
}