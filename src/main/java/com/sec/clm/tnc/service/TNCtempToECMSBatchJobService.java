package com.sec.clm.tnc.service;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sec.clm.manage.dto.ConsultationVO;
import com.sec.clm.tnc.dto.InfContCnsdreqVO;
import com.sec.clm.tnc.dto.InfContractInfoVO;

/**
 * Description	: TNCtempToECMSBatchJobService
 * Author 		: 홍성현
 * Date			: 2014. 05. 26
 */
public interface TNCtempToECMSBatchJobService {
	
	List listTempECMSContractBatch() throws Exception;

//	int insertToECMSContractBatch(InfContCnsdreqVO infContCnsdreqVO) throws Exception;

	List getCntrtNm(InfContCnsdreqVO infContCnsdreqVO) throws Exception;

	int updateECMSContractBatch(InfContCnsdreqVO infContCnsdreqVO) throws Exception;

	List getCompInformation(InfContCnsdreqVO infContCnsdreqVO) throws Exception;

	void insertTnInfContCnsdReq(InfContCnsdreqVO infContCnsdreqVO) throws Exception;

	List checkCustomerYN(InfContCnsdreqVO infContCnsdreqVO) throws Exception;

	int insertNewTNCCustomer(InfContCnsdreqVO infContCnsdreqVO) throws Exception;

	List getECMSFileInfo(InfContCnsdreqVO infContCnsdreqVO) throws Exception;

	List getPostConYnByComp(InfContCnsdreqVO infContCnsdreqVO) throws Exception;

	List listMigTNCContractBatch(InfContCnsdreqVO infContCnsdreqVO) throws Exception;

	void updateMigTNCContractStatus(ConsultationVO consultationVO) throws Exception;
	
	
}