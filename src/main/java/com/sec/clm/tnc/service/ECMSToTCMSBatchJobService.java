package com.sec.clm.tnc.service;

import java.util.List;

import com.sec.clm.tnc.dto.InfContCnsdreqVO;

/**
 * Description	: ECMSToTCMSBatchJobService
 * Author 		: 홍성현
 * Date			: 2014. 07. 26
 */
public interface ECMSToTCMSBatchJobService {
	
	List getAllContractsForTCMS() throws Exception;

	void writeFileForTCMSBatch(List copySendInfoForList, String serverDir) throws Exception;

	void updateAfterBatch(InfContCnsdreqVO vo) throws Exception;
	
	void insertAllContractsForTCMS() throws Exception;
	
}