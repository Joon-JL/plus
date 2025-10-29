/**
* Project Name : 계약관리시스템
* File Name : DecideArbitrarilyReService.java
* Description : 전결규정 Service
* Author : dawn
* Date : 2011.09.26
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.admin.service;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sec.clm.admin.dto.DecideArbitrarilyReVO;

public interface DecideArbitrarilyReService {

	/**
	* 전결규정 목록화면의 사업부 selectbox
	* 
	* @param 
	* @return List
	* @throws Exception
	*/
	List operdivCdList() throws Exception ;

	/**
	* 전결규정 목록화면의 비즈니스 단계 selectbox
	* 
	* @param 
	* @return List
	* @throws Exception
	*/
	List bizClsfcnList() throws Exception ;
	
	/**
	* 전결 규정 사용자 권한 조회.
	* 
	* @param DecideArbitrarilyReVO
	* @return List
	* @throws Exception
	*/
	ListOrderedMap roleCdDecideArbitrarilyRe(DecideArbitrarilyReVO vo) throws Exception ;

	/**
	* 전결규정 목록조회
	* 
	* @param DecideArbitrarilyReVO
	* @return List
	* @throws Exception
	*/
	List listDecideArbitrarilyRe(DecideArbitrarilyReVO vo) throws Exception ;

	/**
	* 전결규정 등록화면의 전결권자 radio
	* 
	* @param 
	* @return List
	* @throws Exception
	*/
	List dcdAuthmanList() throws Exception ;

	/**
	* 전결규정 등록
	* 
	* @param DecideArbitrarilyReVO
	* @return List
	* @throws Exception
	*/
	int insertDecideArbitrarilyRe(DecideArbitrarilyReVO vo) throws Exception ;

	/**
	* 전결규정 상세
	* 
	* @param DecideArbitrarilyReVO
	* @return List
	* @throws Exception
	*/
	ListOrderedMap detailDecideArbitrarilyRe(DecideArbitrarilyReVO vo) throws Exception ;

	/**
	* 전결규정 수정
	* 
	* @param DecideArbitrarilyReVO
	* @return List
	* @throws Exception
	*/
	int modefyDecideArbitrarilyRe(DecideArbitrarilyReVO vo) throws Exception ;

	/**
	* 전결규정 삭제
	* 
	* @param DecideArbitrarilyReVO
	* @return List
	* @throws Exception
	*/
	int deleteDecideArbitrarilyRe(DecideArbitrarilyReVO vo) throws Exception ;

}
