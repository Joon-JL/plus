/**
* Project Name : 계약관리시스템
* File Name : CustomerNewService.java
* Description : 계약상대방 신규등록 팝업 Service
* Author : dawn
* Date : 2011.01.21
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.draft.service;

import java.util.List;

import com.sec.clm.draft.dto.CustomerNewVO;

public interface CustomerNewService {

	/**
	* 계약상대방 신규등록
	* 
	* @param CustomerNewVO
	* @return int
	* @throws Exception
	*/
	int insertCustomer(CustomerNewVO vo) throws Exception ;
	
	/**
	* 계약상대방 신규등록 시 중복 체크
	* 
	* @param CustomerNewVO
	* @return int
	* @throws Exception
	*/
	List checkTexNo(CustomerNewVO vo) throws Exception ;

	String insertCustomerReturnCd(CustomerNewVO cvo) throws Exception ;

}
