/**
* Project Name : 계약관리시스템
* File Name : CustomerService.java
* Description : 계약상대방 검색 팝업 Service
* Author : dawn
* Date : 2011.09.29
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.draft.service;

import java.util.List;

import com.sec.clm.draft.dto.CustomerNewVO;
import com.sec.clm.draft.dto.CustomerTestVO;

public interface CustomerTestService {

	/**
	* 거래상대방 목록
	* 
	* @param CustomerVO
	* @return boolean
	* @throws Exception
	*/
	List listCustomerTest(CustomerTestVO vo) throws Exception ;
	
	/**
	* 거래상대방 상세
	* 
	* @param CustomerVO
	* @return boolean
	* @throws Exception
	*/
	List detailCustomerShare(CustomerTestVO vo) throws Exception ;
	
	/**
	 * 거래상대방 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int modifyCustomer(CustomerTestVO vo) throws Exception;

	/**
	 * 국가 검색
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List getNationList(CustomerTestVO vo) throws Exception;
	
	List checkRegistrationNo(CustomerTestVO vo) throws Exception ;

	int updateRegistrationNo(CustomerTestVO vo)throws Exception ;

	List listTop30Customer(CustomerTestVO vo) throws Exception ;

	List checkGerpCd(CustomerTestVO vo) throws Exception ;

	List checkGerpCus(CustomerTestVO vo) throws Exception ;

	List overTop30Cus(CustomerTestVO vo) throws Exception ;
}
