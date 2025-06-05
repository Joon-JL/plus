/**
* Project Name : 계약관리시스템
* File Name : CustomerService.java
* Description : 거래상대방 검색 팝업 Service
* Author : dawn
* Date : 2011.09.29
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.draft.service;

import java.util.List;

import com.sec.clm.draft.dto.CustomerVO;

public interface CustomerService {

	/**
	* 거래상대방 목록
	* 
	* @param CustomerVO
	* @return boolean
	* @throws Exception
	*/
	List listCustomer(CustomerVO vo) throws Exception ;

}
