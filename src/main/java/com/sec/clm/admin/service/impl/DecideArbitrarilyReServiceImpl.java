/**
* Project Name : 계약관리시스템
* File Name : DecideArbitrarilyReServiceImpl.java
* Description : 전결규정 ServiceImpl
* Author : dawn
* Date : 2011.09.26
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.admin.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sec.clm.admin.dto.DecideArbitrarilyReVO;
import com.sec.clm.admin.service.DecideArbitrarilyReService;

public class DecideArbitrarilyReServiceImpl extends CommonServiceImpl implements DecideArbitrarilyReService{

	/**
	* 전결규정 목록화면의 사업부 selectbox
	* 
	* @param 
	* @return List
	* @throws Exception
	*/
	public List operdivCdList() throws Exception {
		return commonDAO.list("clm.admin.operdivCdList");
	}

	/**
	* 전결규정 목록화면의 비즈니스 단계 selectbox
	* 
	* @param 
	* @return List
	* @throws Exception
	*/
	public List bizClsfcnList() throws Exception {
		return commonDAO.list("clm.admin.bizClsfcnList");
	}
	
	/**
	* 전결 규정 사용자 권한 조회.
	* 
	* @param DecideArbitrarilyReVO
	* @return List
	* @throws Exception
	*/
	public ListOrderedMap roleCdDecideArbitrarilyRe(DecideArbitrarilyReVO vo) throws Exception {
		ListOrderedMap result = null;
		
		//데이터 조회
		List resultList = commonDAO.list("clm.admin.roleCdDecideArbitrarilyRe", vo);
		
		if(resultList != null && resultList.size() > 0){
			result = (ListOrderedMap)resultList.get(0);
		}
		
		return result;
	}

	/**
	* 전결규정 목록조회
	* 
	* @param DecideArbitrarilyReVO
	* @return List
	* @throws Exception
	*/
	public List listDecideArbitrarilyRe(DecideArbitrarilyReVO vo)
			throws Exception {
		return commonDAO.list("clm.admin.listDecideArbitrarilyRe", vo);
	}

	/**
	* 전결규정 등록화면의 전결권자 radio
	* 
	* @param 
	* @return List
	* @throws Exception
	*/
	public List dcdAuthmanList() throws Exception {
		return commonDAO.list("clm.admin.dcdAuthmanList");
	}

	/**
	* 전결규정 등록
	* 
	* @param DecideArbitrarilyReVO
	* @return List
	* @throws Exception
	*/
	public int insertDecideArbitrarilyRe(DecideArbitrarilyReVO vo)
			throws Exception {
		int seqNo = 0;//일련번호
		
		List maxSeqNo = commonDAO.list("clm.admin.insertmaxSeqNoDecideArbitrarilyRe");
		
		if(maxSeqNo != null && maxSeqNo.size() > 0){
			ListOrderedMap lom = (ListOrderedMap)maxSeqNo.get(0);
			
			seqNo = ((BigDecimal)lom.get("max_seqno")).intValue();
		}
		
		vo.setSeqno(seqNo);
		
		return commonDAO.insert("clm.admin.insertDecideArbitrarilyRe", vo);
	}

	/**
	* 전결규정 상세
	* 
	* @param DecideArbitrarilyReVO
	* @return List
	* @throws Exception
	*/
	public ListOrderedMap detailDecideArbitrarilyRe(DecideArbitrarilyReVO vo)
			throws Exception {
		ListOrderedMap result = null;
		
		//데이터 조회
		List resultList = commonDAO.list("clm.admin.detailDecideArbitrarilyRe", vo);
		
		if(resultList != null && resultList.size() > 0){
			result = (ListOrderedMap)resultList.get(0);
		}
		
		return result; 
	}

	/**
	* 전결규정 수정
	* 
	* @param DecideArbitrarilyReVO
	* @return List
	* @throws Exception
	*/
	public int modefyDecideArbitrarilyRe(DecideArbitrarilyReVO vo)
			throws Exception {
		return commonDAO.modify("clm.admin.modefyDecideArbitrarilyRe", vo);
	}

	/**
	* 전결규정 삭제
	* 
	* @param DecideArbitrarilyReVO
	* @return List
	* @throws Exception
	*/
	public int deleteDecideArbitrarilyRe(DecideArbitrarilyReVO vo)
			throws Exception {
		return commonDAO.modify("clm.admin.deleteDecideArbitrarilyRe", vo);
	}
	
}
