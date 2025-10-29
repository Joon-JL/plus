/**
* Project Name : 계약관리시스템
* File Name : RequestService.java
* Description : 계약권한요청 Service
* Author : 신남원
* Date : 2010.09.27
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.manage.service;

import org.apache.commons.collections.map.ListOrderedMap;
import com.sec.clm.manage.dto.RequestVO;

public interface RequestService {

	/**
	* 의뢰정보 조회
	* 
	* @param RequestVO
	* @param 
	* @return List
	* @throws Exception
	*/
	ListOrderedMap detailRequest(RequestVO vo) throws Exception;
	
	/**
	* 계약정보 조회
	* 
	* @param RequestVO
	* @param 
	* @return List
	* @throws Exception
	*/
	ListOrderedMap detailCntrt(RequestVO vo) throws Exception;
	
	/**
	* 권한요청 정보 조회
	* 
	* @param RequestVO
	* @param 
	* @return List
	* @throws Exception
	*/
	ListOrderedMap detailAuthReqInfo(RequestVO vo) throws Exception;
	
	/**
	* 계약권한요청 저장 
	* 
	* @param RequestVO
	* @return int
	* @throws Exception
	*/
	int insertRequest(RequestVO vo) throws Exception ;
	
	/**
	* 계약권한요청 수정 - 승인
	* 
	* @param RequestVO
	* @return int
	* @throws Exception
	*/
	int modifyRequest(RequestVO vo) throws Exception ;

	/**
	* 계약권한요청 삭제 - 취소
	* 
	* @param RequestVO
	* @return int
	* @throws Exception
	*/
	int deleteRequest(RequestVO vo) throws Exception ;	
	
	/**
	* 계약 담당자 변경 권한 체크
	* 
	* @param RequestVO
	* @return boolean
	* @throws Exception
	*/
	boolean checkChangeAuth(RequestVO vo) throws Exception ;	

	/**
	* 계약 담당자 변경을 위한 기본 조회
	* 
	* @param RequestVO
	* @param 
	* @return List
	* @throws Exception
	*/
	ListOrderedMap detailChangeInfo(RequestVO vo) throws Exception;

	/**
	* 계약서 담당자변경처리
	* 
	* @param RequestVO
	* @return int
	* @throws Exception
	*/
	int modifyChange(RequestVO vo) throws Exception ;

	
}