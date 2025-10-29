package com.sec.clm.share.service;

import java.util.HashMap;
import java.util.List;

import com.sec.clm.share.dto.ContractNegoPointVO;
import com.sec.common.util.ClmsDataUtil;
import com.sec.las.lawinformation.dto.MainLawInfoVO;

public interface ContractNegoPointService {
	
	/**
	 * 주요거래선 유형별 협상포인트 목록을 조회한다.
	 * @param  vo ContractNegoPointVO
	 * @return List
	 * @throws Exception
	 */
	
	List listContractNegoPoint(ContractNegoPointVO vo) throws Exception;
	

	/**
	 * 주요거래선 유형별 협상포인트를 등록한다
	 * 회한다.
	 * @param  vo ContractNegoPointVO
	 * @return List
	 * @throws Exception
	 */
	
	int insertContractNegoPoint(ContractNegoPointVO vo) throws Exception;
	
	/**
	 * 주요거래선 유형별 협상포인트을 수정한다
	 * 회한다.
	 * @param  vo ContractNegoPointVO
	 * @return List
	 * @throws Exception
	 */
	
	int modifyContractNegoPoint(ContractNegoPointVO vo) throws Exception;
	
	/**
	 * 주요거래선 유형별 협상포인트 삭제한다
	 * 회한다.
	 * @param  vo ContractNegoPointVO
	 * @return List
	 * @throws Exception
	 */
	
	int deleteContractNegoPoint(ContractNegoPointVO vo) throws Exception;
	
	/**
	 * 주요거래선 유형별 협상포인트를 상세조회한다
	 * 회한다.
	 * @param  vo ContractNegoPointVO
	 * @return List
	 * @throws Exception
	 */
	
	List detailContractNegoPoint(ContractNegoPointVO vo) throws Exception;
	
	/**
	 * 주요거래선 유형별 협상포인트  조회수 증가
	 * @param  vo ContractNegoPointVO
	 * @return List
	 * @throws Exception
	 */
	
	void increseRdcnt(ContractNegoPointVO vo) throws Exception;
	
	/**
	 * 주요거래선 유형별 협상포인트 권한조회
	 * @param  vo ContractNegoPointVO, mode String
	 * @return boolean
	 * @throws Exception
	 */
	boolean authContractNegoPint(String mode, ContractNegoPointVO vo) throws Exception;

}