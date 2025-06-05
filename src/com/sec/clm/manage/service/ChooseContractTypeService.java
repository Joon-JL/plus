/**
* Project Name : 계약관리시스템
* File Name :ChooseContractTypeService.java
* Description : 계약유형선택팝업 Service interface
* Author : 심주완
* Date : 2011.09.25
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.manage.service;

import java.util.List;

import com.sec.clm.manage.dto.ChooseContractTypeVO;

public interface ChooseContractTypeService {
	//2011.08.31 심주완 추가-계약선택팝업에서 계약목록
	List listChooseContractType(ChooseContractTypeVO vo) throws Exception;
	
	//2011.09.02 심주완 추가-계약유형선택팝업에서 비지니스의 분류
	List listChooseContractTypeBiz(ChooseContractTypeVO vo) throws Exception; 
	
	//2011.09.02 심주완 추가-계약유형선택팝업에서 계약의 단계
	List listChooseContractTypeDepth(ChooseContractTypeVO vo) throws Exception;
	
	//2011.09.02 심주완 추가-계약유형선택팝업에서 체결의 목적
	List listChooseContractTypePurpose(ChooseContractTypeVO vo) throws Exception;
}