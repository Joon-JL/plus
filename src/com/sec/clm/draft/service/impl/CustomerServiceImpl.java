/**
* Project Name : 계약관리시스템
* File Name : CustomerServiceImpl.java
* Description : 거래상대방 검색 팝업 ServiceImpl
* Author : dawn
* Date : 2011.09.29
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.draft.service.impl;

import java.util.List;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sec.clm.draft.dto.CustomerVO;
import com.sec.clm.draft.service.CustomerService;

public class CustomerServiceImpl extends CommonServiceImpl implements CustomerService{

	/**
	* 거래상대방 목록
	* 
	* @param CustomerVO
	* @return boolean
	* @throws Exception
	*/
	public List listCustomer(CustomerVO vo) throws Exception {
		return commonDAO.list("clm.draft.listCustomer", vo);
	}

}
