package com.sec.clm.tnc.service;

import java.util.List;
import java.util.Map;

import model.outldap.samsung.net.Employee;

import com.sec.clm.tnc.dto.InfContCnsdreqVO;
import com.sec.clm.tnc.dto.InfContractInfoVO;
import com.sec.clm.tnc.dto.InfCustomerVO;
import com.sec.clm.tnc.dto.InfFileAttachVO;

/**
 * Description	: 계약관리 인터페이스 Service
 * Author 		: 홍성현
 * Date			: 2014. 05. 26
 */
public interface LegacyInterfaceService {

	
	int insertInfContCnsdreq(InfContCnsdreqVO infContCnsdreqVO);	
	
	List loadInfCopySendinfo(InfContractInfoVO vo) throws Exception;

	int writeFileForCopySendInfo(List copySendInfoList, String path) throws Exception;

	int insertInfFileAttach(InfFileAttachVO infFileAttachVO) throws Exception;
}