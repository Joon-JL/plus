/**
* Project Name : 계약관리시스템
* File Name :ChooseContractService.java
* Description : 계약선택팝업 Service interface
* Author : 심주완
* Date : 2011.09.25
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.manage.service;

import java.util.List;

import com.sec.clm.manage.dto.ChooseContractVO;
import com.sec.clm.manage.dto.ConsultationVO;

public interface ChooseContractService {
	//2011.08.31 심주완 추가-계약선택팝업에서 계약목록
	List listChooseContract(ChooseContractVO vo) throws Exception;
	
	int modifyChooseContract(ChooseContractVO vo) throws Exception;
	
	//체결품의-계약유호성체크
	List listApprovalValidation(ChooseContractVO vo) throws Exception;
}