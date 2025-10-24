/**
* Project Name : 계약관리시스템
* File Name : RegulationService.java
* Description : 프로세스&규정지침서 Service
* Author : 신남원
* Date : 2010.09.01
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.admin.service;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sec.clm.admin.dto.RegulationVO;

public interface RegulationService {

	/**
	* 프로세스&규정지침서 목록조회
	* 
	* @param RegulationVO
	* @return List
	* @throws Exception
	*/
	List listRegulation(RegulationVO vo) throws Exception;

	/*
	 * up_rule_cd에 따른 rule_cd 
	 */
	String getRulCdHTML(HashMap hm) throws Exception;
	
	/**
	* 프로세스&규정지침서 등록
	* 
	* @param RegulationVO
	* @return List
	* @throws Exception
	*/
	int insertRegulation(RegulationVO vo) throws Exception;

	/**
	* 프로세스&규정지침서 상세조회
	* 
	* @param RegulationVO
	* @return List
	* @throws Exception
	*/
	ListOrderedMap detailRegulation(RegulationVO vo) throws Exception;

	/**
	* 프로세스&규정지침서 수정
	* 
	* @param RegulationVO
	* @return List
	* @throws Exception
	*/
	int modifyRegulation(RegulationVO vo) throws Exception;

	/**
	* 프로세스&규정지침서 삭제
	* 
	* @param RegulationVO
	* @return List
	* @throws Exception
	*/
	int deleteRegulation(RegulationVO vo) throws Exception;

	/**
	* 프로세스&규정지침서 1단계 조회
	* 
	* @param 
	* @return List
	* @throws Exception
	*/
	List listODepthRegulation(RegulationVO vo) throws Exception ;

	/**
	* 등록 화면전체depth(selectbox)조회.
	* 
	* @param 
	* @return List
	* @throws Exception
	*/
	List listUpRule(RegulationVO vo) throws Exception ;
}