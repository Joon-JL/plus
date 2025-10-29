/**
* Project Name : 계약관리시스템
* File Name :ContractAgreeServiceImpl.java
* Description : 계약합의 Service Impl
* Author : 심주완
* Date : 2011.09.30
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.manage.service.impl;


import java.util.List;

import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.util.StringUtil;

import com.sec.clm.manage.dto.ContractAgreeVO;
import com.sec.clm.manage.service.ContractAgreeService;

public class ContractAgreeServiceImpl extends CommonServiceImpl implements ContractAgreeService {

	//2011.08.31 심주완추가 계약선택팝업에서 계약목록조회
	public List listContractAgree(ContractAgreeVO vo) throws Exception {
		return commonDAO.list("clm.manage.listContractAgree", vo);		
	}
	
	public int modifyContractAgree(ContractAgreeVO vo) throws Exception {
		return commonDAO.insert("clm.manage.modifyContractAgree", vo);
	}
	
	public int insertContractAgree(ContractAgreeVO vo) throws Exception {
		return commonDAO.insert("clm.manage.insertContractAgree", vo);
	}
}