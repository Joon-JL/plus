package com.sec.clm.manage.service;

import java.util.HashMap;
import java.util.List;

import com.sec.clm.manage.dto.ExecutionVO;
import com.sec.clm.manage.dto.MyApprovalVO;

/**
 * Description	: 이행정보 Service interface
 * Author 		: 신승완
 * Date			: 2011. 09. 05
 */
public interface ExecutionService {
	
	/**
	 * 계약  전체목록을 조회한다.
	 * @param  vo ExecutionVO
	 * @return List
	 * @throws Exception
	 */
	public List listContract(ExecutionVO vo) throws Exception;
	
	/**
	 * 계약  상세정보를 조회한다.
	 * @param  vo ExecutionVO
	 * @return List
	 * @throws Exception
	 */
	public List detailContract(ExecutionVO vo) throws Exception;
	
	/**
	 * 이행정보  전체 목록을 조회한다.
	 * @param  vo ExecutionVO
	 * @return List
	 * @throws Exception
	 */
	List listExecution(ExecutionVO vo) throws Exception;
	
	/**
	 * 이행정보 등록한다.
	 * @param  vo ExecutionVO
	 * @return int
	 * @throws Exception
	 */
	int insertExecution(ExecutionVO vo) throws Exception;

	/**
	 * 이행정보 수정한다.
	 * @param  vo ExecutionVO
	 * @return int
	 * @throws Exception
	 */
	int modifyExecution(ExecutionVO vo) throws Exception;
	
	/**
	 * 이행 최대 번호를 정한다.
	 * @param  vo ExecutionVO
	 * @return int
	 * @throws Exception
	 */
	String getMaxExecNum(ExecutionVO vo) throws Exception;

	/**
	 * 이행정보 삭제한다.
	 * @param  vo ExecutionVO
	 * @return int
	 * @throws Exception
	 */
	int deleteExecution(ExecutionVO vo) throws Exception;
	
	/**
	 * 이력정보  전체 목록을 조회한다.
	 * @param  vo ExecutionVO
	 * @return List
	 * @throws Exception
	 */
	List listHisExecution(ExecutionVO vo) throws Exception;
	
	/**
	 * 이행중->종료대상 으로 상태변경
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int modifyContractStatus(ExecutionVO vo) throws Exception;

}