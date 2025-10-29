/**
* Project Name : 계약관리시스템
* File Name : FaqService.java
* Description : ADMIN FAQ Service
* Author : 신남원
* Date : 2010.09.05
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.admin.service;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sec.clm.admin.dto.BoardVO;
import com.sec.clm.admin.dto.FaqVO;

public interface FaqService {

	/**
	* ADMIN FAQ 목록조회
	* 
	* @param FaqVO
	* @return List
	* @throws Exception
	*/
	List listFaq(FaqVO vo) throws Exception;

	/**
	* FAQ 목록조회
	* 
	* @param FaqVO
	* @return List
	* @throws Exception
	*/
	List listCounselFaq(FaqVO vo) throws Exception;
	
	/**
	* ADMIN FAQ 등록
	* 
	* @param FaqVO
	* @return List
	* @throws Exception
	*/
	int insertFaq(FaqVO vo) throws Exception;

	/**
	* ADMIN FAQ 상세조회
	* 
	* @param FaqVO
	* @return List
	* @throws Exception
	*/
	ListOrderedMap detailFaq(FaqVO vo) throws Exception;

	/**
	* 공지사항 단위조직 조회
	* 
	* @param FaqVO
	* @return List
	* @throws Exception
	*/
	List listOrgnz(FaqVO vo) throws Exception;
	
	/**
	* FAQ 조회수 증가
	* 
	* @param FaqVO
	* @return 
	* @throws Exception
	*/
	int incRdcntFaq(FaqVO vo) throws Exception;
	
	/**
	* ADMIN FAQ 수정
	* 
	* @param FaqVO
	* @return List
	* @throws Exception
	*/
	int modifyFaq(FaqVO vo) throws Exception;

	/**
	* ADMIN FAQ 삭제
	* 
	* @param FaqVO
	* @return List
	* @throws Exception
	*/
	int deleteFaq(FaqVO vo) throws Exception;

	/**
	* ADMIN FAQ 권한조회
	* 
	* @param FaqVO
	* @return boolean
	* @throws Exception
	*/
	boolean authFaq(String mode, FaqVO vo) throws Exception;

}