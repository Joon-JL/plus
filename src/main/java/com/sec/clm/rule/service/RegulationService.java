/**
* Project Name : 계약관리시스템
* File Name : RegulationService.java
* Description : 프로세스&규정지침서 Service
* Author : 신남원
* Date : 2010.09.06
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.rule.service;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sec.clm.rule.dto.RegulationVO;

public interface RegulationService {

	/**
	* 프로세스&규정지침서 목록조회
	* 
	* @param RegulationVO
	* @return List
	* @throws Exception
	*/
	List listRegulation(RegulationVO vo) throws Exception;

}