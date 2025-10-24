/**
 * Project Name : 계약관리시스템 
 * File name	: ContractAttentionService.java
 * Description	: 계약지식공유 > 계약체결시 유의사항 Service interface
 * Author		: 유영섭
 * Date			: 2011. 09. 19
 * Copyright	:
 */
package com.sec.clm.share.service;

import java.util.HashMap;
import java.util.List;


import com.sec.clm.share.dto.ContractAttentionForm;
import com.sec.clm.share.dto.ContractAttentionVO;
import com.sec.las.lawinformation.dto.MainLawInfoVO;





public interface ContractAttentionService {
	
	
	/**
	 * 계약체결시 유의사항을 조회한다.
	 * @param  vo ContractAttentionVO
	 * @return List
	 * @throws Exception
	 */
	

	List listContractAttention(ContractAttentionVO vo) throws Exception;
	
	/**
	 * 계약체결시 유의사항을 등록한다
	 * 회한다.
	 * @param  vo ContractAttentionVO
	 * @return List
	 * @throws Exception
	 */

	int insertContractAttention(ContractAttentionVO vo) throws Exception;
	
	/**
	 * 계약체결시 유의사항을 수정한다
	 * 회한다.
	 * @param  vo ContractAttentionVO
	 * @return List
	 * @throws Exception
	 */

	int modifyContractAttention(ContractAttentionVO vo) throws Exception;
	
	/**
	 * 계약체결시 유의사항을 삭제한다
	 * 회한다.
	 * @param  vo ContractAttentionVO
	 * @return List
	 * @throws Exception
	 */


	int deleteContractAttention(ContractAttentionVO vo) throws Exception;
	
	/**
	 * 계약체결시 유의사항을 상세조회한다
	 * 회한다.
	 * @param  vo ContractAttentionVO
	 * @return List
	 * @throws Exception
	 */

	List detailContractAttention(ContractAttentionVO vo) throws Exception;
	
	/**
	 * 계약체결시 유의사항  조회수 증가
	 * @param  vo ContractAttentionVO
	 * @return List
	 * @throws Exception
	 */
	void increseRdcnt(ContractAttentionVO vo) throws Exception;
	
	/**
	 * 계약체결시 유의사항 권한조회
	 * @param  vo ContractAttentionVO, mode String
	 * @return boolean
	 * @throws Exception
	 */
	boolean authContractAttention(String mode, ContractAttentionVO vo) throws Exception;

}