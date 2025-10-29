package com.sec.clm.manage.service;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import com.sec.clm.manage.dto.CompletionVO;
import com.sec.clm.manage.dto.ConsultationVO;
import com.sec.clm.manage.dto.ExecutionVO;

/**
 * Description	: 계약종료 Service interface
 * Author 		: 신승완
 * Date			: 2011. 09. 30
 */
public interface CompletionService {
	public List detailManageCompletion(CompletionVO vo) throws Exception ;
	/**
	 * 계약  전체목록을 조회한다.
	 * @param  vo ExecutionVO
	 * @return List
	 * @throws Exception
	 */
	public List listContract(CompletionVO vo) throws Exception;
	
	
	int countResponseManId(CompletionVO vo) throws Exception;
	 
	/**
	 * 계약  전체목록을 조회한다.
	 * @param  vo ExecutionVO
	 * @return List
	 * @throws Exception
	 */
	public List listContract2(CompletionVO vo) throws Exception;
	
	/**
	 * 계약  상세정보를 조회한다.
	 * @param  vo CompletionVO
	 * @return List
	 * @throws Exception
	 */
	public List detailContract(CompletionVO vo) throws Exception;
	
	/**
	 * 이행및종료이력을 조회한다.
	 * @param  vo CompletionVO
	 * @return List
	 * @throws Exception
	 */
	List listExecutionHis(CompletionVO vo) throws Exception;
	
	/**
	 * 이행정보 수정한다.
	 * @param  vo ExecutionVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyExecution(CompletionVO vo) throws Exception;
	
	/**
	 * 이행 최대 번호를 정한다.
	 * @param  vo ExecutionVO
	 * @return int
	 * @throws Exception
	 */
	String getMaxExecNum(CompletionVO vo) throws Exception;
	
	/**
	 * 종료정보 상세조회한다.
	 * @param  vo CompletionVO
	 * @return int
	 * @throws Exception
	 */
	public List detailCompletion(CompletionVO vo) throws Exception;
	
	/**
	 * 종료정보 상세조회한다.
	 * @param  vo CompletionVO
	 * @return int
	 * @throws Exception
	 */
	public List detailCompletion2(CompletionVO vo) throws Exception;

	/**
	 * 종료정보 임시저장한다.
	 * @param  vo CompletionVO
	 * @return int
	 * @throws Exception
	 */
	int insertTempCompletion(CompletionVO vo) throws Exception;	
	
	/**
	 * 종료정보 등록한다.
	 * @param  vo CompletionVO
	 * @return int
	 * @throws Exception
	 */
	int insertCompletion(CompletionVO vo) throws Exception;
	
	/**
	 * 종료정보 종료확인한다.
	 * @param  vo CompletionVO
	 * @return int
	 * @throws Exception
	 */
	int insertCfrmCompletion(CompletionVO vo) throws Exception;

	/**
	 * 종료정보 수정한다.
	 * @param  vo CompletionVO
	 * @return int
	 * @throws Exception
	 */
	int modifyCompletion(CompletionVO vo) throws Exception;

	/**
	 * 이행정보 삭제한다.
	 * @param  vo CompletionVO
	 * @return int
	 * @throws Exception
	 */
	//int deleteExecution(CompletionVO vo) throws Exception;
	
	/**
	 * 이력정보  전체 목록을 조회한다.
	 * @param  vo CompletionVO
	 * @return List
	 * @throws Exception
	 */
	List listHisCompletion(CompletionVO vo) throws Exception;
	
	/**
	 * 권한 목록 조회
	 * @param sys_cd 시스템 코드
	 * @return 관리자용 메뉴리스트
	 * @throws Exception
	 */
	JSONArray listContractRole(CompletionVO vo) throws Exception;
	
	/**
	 * 상신-검토의뢰조회한다.
	 * @param  vo CompletionVO
	 * @return int
	 * @throws Exception
	 */
	public List detailCompletionApprovalRequest(CompletionVO vo) throws Exception;
	
	/**
	 * 상신-검토의뢰계약목록조회한다.
	 * @param  vo CompletionVO
	 * @return int
	 * @throws Exception
	 */
	public List listCompletionApprovalContract(CompletionVO vo) throws Exception;
	
	/**
	 * 상신-체결목록계약상세정보조회 .
	 * @param  vo CompletionVO
	 * @return int
	 * @throws Exception
	 */
	public List detailConsultationContractMasterApproval(CompletionVO vo) throws Exception;
	
	/**
	 * 계약마스터 아이디로 특화 속성정보 가져오기
	 * @param  vo CompletionVO
	 * @return List
	 * @throws Exception
	 */
	public List listConsultationSpecial(CompletionVO vo) throws Exception;
	
	/**
	 * 체결품의 첨부파일목록 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List listCompletionApprovalAttachInfo(CompletionVO vo) throws Exception;
	
	/**
	 * 첨부파일목록 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List listCompletionAttachInfo(CompletionVO vo) throws Exception;
	
	/**
	 * 종료품의시 제목 조회 
	 * @param  vo CompletionVO
	 * @return List
	 * @throws Exception
	 */
	public List detailCntrtTitle(CompletionVO vo) throws Exception;
	
	
	/**
	 * 종료품의 완료 결재상황 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listCompletionApprovalHis(CompletionVO vo) throws Exception;
	
	/**
	 * 의뢰별 계약ID조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listCntrtId(CompletionVO vo) throws Exception;
	
	/**
	 * 검토의뢰 정보 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List detailConsideration(CompletionVO vo) throws Exception;
	
	/**
	 * 관련자 리스트 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listRelationman(CompletionVO vo) throws Exception;

	/**
	 * 기간연장 승인/반려.
	 * @param  vo CompletionVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyAutoRenewApproval(CompletionVO vo) throws Exception;
	/**
	 * 계약단계 변경
	 * @param  vo RegistrationVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyCompletionStatus(CompletionVO vo) throws Exception	;
	
	
	/**
	 * close 이력 조회
	 * @param  vo CompletionVO
	 * @return List
	 * @throws Exception
	 */
	public List closeRTNView(CompletionVO vo) throws Exception;
		
	
	/**
	 * TNC와 연동을 하기 위한 목록 조회
	 * @param  vo ExecutionVO
	 * @return List
	 * @throws Exception
	 */
	public List tncListContract(CompletionVO vo) throws Exception;
	
	
	public List listTncLink(ConsultationVO vo) throws Exception;
	
}