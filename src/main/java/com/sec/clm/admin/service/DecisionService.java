/**
* Project Name : 계약관리시스템
* File Name : DecisionService.java
* Description : 전결규정 Service
* Author : 신남원
* Date : 2010.09.01
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.admin.service;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sec.clm.admin.dto.DecisionVO;

public interface DecisionService {

	/**
	* 전결규정 목록조회
	* 
	* @param DecisionVO
	* @return List
	* @throws Exception
	*/
	List listDecision(DecisionVO vo) throws Exception;

	/**
	* 전결규정 등록
	* 
	* @param DecisionVO
	* @return List
	* @throws Exception
	*/
	int insertDecision(DecisionVO vo) throws Exception;

	/**
	* 전결규정 상세조회
	* 
	* @param DecisionVO
	* @return List
	* @throws Exception
	*/
	ListOrderedMap detailDecision(DecisionVO vo) throws Exception;

	/**
	* 전결규정 수정
	* 
	* @param DecisionVO
	* @return List
	* @throws Exception
	*/
	int modifyDecision(DecisionVO vo) throws Exception;

	/**
	* 전결규정 삭제
	* 
	* @param DecisionVO
	* @return List
	* @throws Exception
	*/
	int deleteDecision(DecisionVO vo) throws Exception;


}