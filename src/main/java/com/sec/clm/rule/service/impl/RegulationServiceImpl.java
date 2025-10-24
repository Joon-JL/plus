/**
* Project Name : 계약관리시스템
* File Name : RegulationServiceImpl.java
* Description : 프로세스&규정지침서 ServiceImpl
* Author : 신남원
* Date : 2010.09.06
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.rule.service.impl;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sec.clm.rule.dto.RegulationVO;
import com.sec.clm.rule.service.RegulationService;

public class RegulationServiceImpl extends CommonServiceImpl implements RegulationService {

	/**
	* 프로세스&규정지침서 목록조회
	* 
	* @param RegulationVO
	* @return List
	* @throws Exception
	*/
	public List listRegulation(RegulationVO vo) throws Exception {
		return commonDAO.list("clm.rule.listRegulation", vo);
	}
}