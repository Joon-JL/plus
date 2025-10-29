/**
* Project Name : 계약관리시스템
* File Name : CustomerServiceImpl.java
* Description : 계약상대방 검색 팝업 ServiceImpl
* Author : dawn
* Date : 2011.09.29
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.draft.service.impl;

import java.util.List;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sec.clm.draft.dto.CustomerNewVO;
import com.sec.clm.draft.dto.CustomerTestVO;
import com.sec.clm.draft.service.CustomerTestService;

public class CustomerTestServiceImpl extends CommonServiceImpl implements CustomerTestService{

	/**
	* 거래상대방 목록
	* 
	* @param CustomerVO
	* @return boolean
	* @throws Exception
	*/
	public List listCustomerTest(CustomerTestVO vo) throws Exception {
		
		return commonDAO.list("clm.draft.listCustomerTest", vo);	
	}
	
	/**
	* 거래상대방 상세
	* 
	* @param CustomerVO
	* @return boolean
	* @throws Exception
	*/
	public List detailCustomerShare(CustomerTestVO vo) throws Exception {
		return commonDAO.list("clm.draft.listCustomerPop", vo);
	}

	
	public int modifyCustomer(CustomerTestVO vo) throws Exception {
		int result = 0;
		
		/* Update 하기전의 정보를 TH_CLM_CUSTOMER 테이블에 저장 */
//		result = commonDAO.insert("clm.draft.copyCustomer", vo);
		
		/* 기존 TN_CLM_CUSTOMER 테이블의 정보를 업데이트 */
		result = commonDAO.modify("clm.draft.modifyCustomer", vo);
		return result;
		
	}

	@Override
	public List getNationList(CustomerTestVO vo) throws Exception {
		// TODO Auto-generated method stub
		return commonDAO.list("clm.draft.listUserNation", vo);
	}

	@Override
	public List checkRegistrationNo(CustomerTestVO vo) throws Exception {
		// TODO Auto-generated method stub
		return commonDAO.list("clm.draft.checkRegistrationNo", vo);
	}

	@Override
	public int updateRegistrationNo(CustomerTestVO vo) throws Exception {
		// TODO Auto-generated method stub
		return commonDAO.modify("clm.draft.modifyRegistrationNo", vo);
	}

	@Override
	public List listTop30Customer(CustomerTestVO vo) throws Exception {
		// TODO Auto-generated method stub
		return commonDAO.list("clm.draft.listTop30Cus", vo);	
	}

	@Override
	public List checkGerpCd(CustomerTestVO vo) throws Exception {
		// TODO Auto-generated method stub
		return commonDAO.list("clm.draft.checkGerpCd", vo);
	}

	@Override
	public List checkGerpCus(CustomerTestVO vo) throws Exception {
		// TODO Auto-generated method stub
		return commonDAO.list("clm.draft.checkGerpCus", vo);
	}

	@Override
	public List overTop30Cus(CustomerTestVO vo) throws Exception {
		// TODO Auto-generated method stub
		return commonDAO.list("clm.draft.overTop30Cus", vo);
	}
	
}
