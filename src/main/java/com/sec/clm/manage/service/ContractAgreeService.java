/**
* Project Name : 계약관리시스템
* File Name :ContractAgreeService.java
* Description : 계약합의정보 Service interface
* Author : 심주완
* Date : 2011.09.30
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.manage.service;

import java.util.List;

import com.sec.clm.manage.dto.ContractAgreeVO;

public interface ContractAgreeService {
	List listContractAgree(ContractAgreeVO vo) throws Exception;
	int insertContractAgree(ContractAgreeVO vo) throws Exception;
	int modifyContractAgree(ContractAgreeVO vo) throws Exception;
}