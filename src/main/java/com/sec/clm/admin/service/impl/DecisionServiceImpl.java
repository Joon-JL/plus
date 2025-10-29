/**
* Project Name : 계약관리시스템
* File Name : DecisionServiceImpl.java
* Description : 전결규정 ServiceImpl
* Author : 신남원
* Date : 2010.09.01
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.admin.service.impl;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sec.clm.admin.dto.DecisionVO;
import com.sec.clm.admin.service.DecisionService;

public class DecisionServiceImpl extends CommonServiceImpl implements DecisionService {

	/**
	* 전결규정 목록조회
	* 
	* @param DecisionVO
	* @return List
	* @throws Exception
	*/
	public List listDecision(DecisionVO vo) throws Exception {
		return commonDAO.list("clm.admin.listDecision", vo);
	}

	/**
	* 전결규정 등록
	* 
	* @param DecisionVO
	* @return List
	* @throws Exception
	*/
	public int insertDecision(DecisionVO vo) throws Exception {
		return commonDAO.insert("clm.admin.insertDecision", vo);
	}

	/**
	* 전결규정 상세조회
	* 
	* @param DecisionVO
	* @return List
	* @throws Exception
	*/
	public ListOrderedMap detailDecision(DecisionVO vo) throws Exception {
		ListOrderedMap result = null;
		
		//데이터 조회
		List resultList = commonDAO.list("clm.admin.detailDecision", vo);
		
		if(resultList != null && resultList.size() > 0){
			result = (ListOrderedMap)resultList.get(0);
		}

		return result;
	}
	
	/**
	* 전결규정 수정
	* 
	* @param DecisionVO
	* @return List
	* @throws Exception
	*/
	public int modifyDecision(DecisionVO vo) throws Exception {
		return commonDAO.modify("clm.admin.modifyDecision", vo);
	}

	/**
	* 전결규정 삭제
	* 
	* @param DecisionVO
	* @return List
	* @throws Exception
	*/
	public int deleteDecision(DecisionVO vo) throws Exception {
		 return commonDAO.delete("clm.admin.deleteDecision", vo);
	}

}