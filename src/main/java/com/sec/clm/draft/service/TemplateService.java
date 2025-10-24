/**
* Project Name : 계약관리시스템
* File Name : TemplateService.java
* Description : 표준 Template Service
* Author : 신남원
* Date : 2010.08.31
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.draft.service;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sec.clm.draft.dto.LibraryVO;

public interface TemplateService {

	/**
	* 표준 Teplate 목록조회
	* 
	* @param LibraryVO
	* @return List
	* @throws Exception
	*/
	List listTemplate(LibraryVO vo) throws Exception;

	/**
	* 표준 Teplate 저장
	* 
	* @param LibraryVO
	* @return int
	* @throws Exception
	*/
	int insertTemplate(LibraryVO vo) throws Exception;

	/**
	* 표준 Teplate 수정
	* 
	* @param LibraryVO
	* @return int
	* @throws Exception
	*/
	int modifyTemplate(LibraryVO vo) throws Exception;

	/**
	* 표준 Teplate 삭제
	* 
	* @param LibraryVO
	* @return int
	* @throws Exception
	*/
	int deleteTemplate(LibraryVO vo) throws Exception;

	/**
	* 표준 Teplate 상세조회
	* 
	* @param LibraryVO
	* @return ListOrderedMap
	* @throws Exception
	*/
	ListOrderedMap detailTemplate(LibraryVO vo) throws Exception;

	/**
	* 표준 Teplate 거래 상대방 목록조회
	* 
	* @param LibraryVO
	* @return List
	* @throws Exception
	*/
	List listTemplateOppntCd(LibraryVO vo) throws Exception;
	
	/**
	* 표준 Teplate 권한조회
	* 
	* @param LibraryVO
	* @return boolean
	* @throws Exception
	*/
	boolean	authTemplate(String mode, LibraryVO vo) throws Exception;
}