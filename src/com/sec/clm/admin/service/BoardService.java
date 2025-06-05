/**
* Project Name : 계약관리시스템
* File Name : BoardService.java
* Description : 공지사항 Service
* Author : 신남원
* Date : 2010.09.01
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.admin.service;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sec.clm.admin.dto.BoardVO;

public interface BoardService {

	/**
	* 공지사항 목록조회
	* 
	* @param BoardVO
	* @return List
	* @throws Exception
	*/
	List listBoard(BoardVO vo) throws Exception;

	/**
	* 공지사항 등록
	* 
	* @param BoardVO
	* @return List
	* @throws Exception
	*/
	int insertBoard(BoardVO vo) throws Exception;

	/**
	* 공지사항 상세조회
	* 
	* @param BoardVO
	* @return List
	* @throws Exception
	*/
	ListOrderedMap detailBoard(BoardVO vo) throws Exception;

	/**
	* 공지사항 단위조직 조회
	* 
	* @param BoardVO
	* @return List
	* @throws Exception
	*/
	List listOrgnz(BoardVO vo) throws Exception;
	
	/**
	* 공지사항 수정
	* 
	* @param BoardVO
	* @return List
	* @throws Exception
	*/
	int modifyBoard(BoardVO vo) throws Exception;

	/**
	* 공지사항 삭제
	* 
	* @param BoardVO
	* @return List
	* @throws Exception
	*/
	int deleteBoard(BoardVO vo) throws Exception;

	/**
	* 공지사항 권한조회
	* 
	* @param BoardVO
	* @return boolean
	* @throws Exception
	*/
	boolean authBoard(String mode, BoardVO vo) throws Exception;
	
	
	/**
	* 공지사항 목록조회 - 메인
	* 
	* @param BoardVO
	* @return List
	* @throws Exception
	*/
	List listBoardByMain(BoardVO vo) throws Exception;

}