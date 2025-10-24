/**
* Project Name : 계약관리시스템
* File Name : StdContractServiceImpl.java
* Description : 표준계약서 ServiceImpl
* Author : 이민우
* Date : 2012.07.16
* Copyright : 2012 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.draft.service.impl;

import java.util.List;
import org.apache.commons.collections.map.ListOrderedMap;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sec.clm.draft.dto.StdContractVO;
import com.sec.clm.draft.service.StdContractService;

public class StdContractServiceImpl extends CommonServiceImpl implements StdContractService {

	/**
	* 표준계약서 목록조회
	* 
	* @param LibraryVO
	* @return List
	* @throws Exception
	*/
	public List listStdContract(StdContractVO vo) throws Exception {
		return commonDAO.list("clm.draft.listStdContract", vo);
	}
	
	/**
	* 표준계약서 목록조회
	* 
	* @param LibraryVO
	* @return List
	* @throws Exception
	*/
	public ListOrderedMap detailStdContract(StdContractVO vo) throws Exception {
		// 상세조회할 데이터를 불러온다
		List list = commonDAO.list("clm.draft.detailStdContract", vo);
		ListOrderedMap lom = new ListOrderedMap();

		if (list != null && list.size() != 0) {
			lom = (ListOrderedMap) list.get(0);
		}

		return lom;
	}
	
	public List listStdContracttHistory(StdContractVO vo) throws Exception {

		return commonDAO.list("clm.draft.listHistory_reqman", vo);
	}


}