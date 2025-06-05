package com.sec.clm.manage.service;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sec.clm.manage.dto.ConsultationExecVO;
import com.sec.clm.manage.dto.ConsultationSpecialVO;
import com.sec.clm.manage.dto.ConsultationVO;
import com.sec.clm.manage.dto.ExecutionVO;
import com.sec.clm.manage.dto.ManageVO;
import com.sec.clm.manage.dto.RegistrationVO;


public interface RegistrationService {
	
	/**
	 * 계약 아이디  조회
	 * @return
	 * @throws Exception 	
	 */
	public String getId() throws Exception;	
	
	/**
	 * 의뢰 아이디 조회
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public String getNewReqId(String str) throws Exception;
	
	/**
	 * 회신 요청일 +3 일 이후 조회
	 * @return 
	 * @throws Exception
	 */
	public String getReDemndday() throws Exception;	
	/**
	 * 최초의뢰 / 재의뢰/ 최종본 의뢰 상태 설정 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public String getReqStatus(String prev_cnsdreq_id,String plndbn_req_yn, ConsultationVO vo) throws Exception;
	/**
	 * 의뢰건 상세조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List detailConsideration(ConsultationVO vo) throws Exception;
	/**
	 * 재의뢰/예정본의뢰/수정/임시저장
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public ListOrderedMap insertConsideration(ConsultationVO vo) throws Exception;
	
	/**
	 * View 조회 에서 재의뢰,최종본의뢰 버튼 클리기시 이전 의뢰 정보 Copy 
	 * @param vo
	 * @return 새 의뢰아이디 
	 * @throws Exception
	 */
	public ListOrderedMap prevCnsdReqCopyConsideration(ConsultationVO vo) throws Exception;
	
	/**
	 * 의뢰시 첨부 필수 사항 체크 최종본 의뢰시 계약 여러건 인경우 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public ListOrderedMap getFilevalidate(ConsultationVO vo) throws Exception;
		
	/**
	 * 재검토 의뢰시 내용 저장 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public ListOrderedMap modifyAgainReConsideration(ConsultationVO vo) throws Exception;
	
	/**
	 * 의뢰 및 계약건 삭제,Drop,보류
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public ListOrderedMap deleteDropDefer(ConsultationVO vo) throws Exception;
	
	/**
	 * 의뢰 및 계약건 Drop , 보류 사유 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List detailDropDefer(ConsultationVO vo) throws Exception;	
	
	/**
	 * 계약 상세 내역 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List detailContractMaster(ConsultationVO vo) throws Exception;
	
	/**
	 * 최조의뢰, 최종본의뢰시 수정 화면  계약건 정보조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List detailForwardContractMaster(ConsultationVO vo) throws Exception;
	
	/**
	 * 계약건 저장
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public ListOrderedMap insertContractMaster(ConsultationVO vo) throws Exception;
	
	/**
	 * 계약건에 연관 계약 설정을 위한 계약 리스트 팝업
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List popupListContract(ConsultationVO vo) throws Exception;		
	
	/**
	 * 계약건에 계약 유형별 특화 속성 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listTypeSpclinfo(ConsultationVO vo) throws Exception;
	
	/**
	 * 계약건에 계약 특화 속성 수정 화면 속성 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listTypeSpclinfoMod(ConsultationVO vo) throws Exception;
	
	/**
	 * 연관계약 select
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public HashMap listRelationContract(ConsultationVO vo) throws Exception;
	/**
	 * 연관계약 insert/delete
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int actionRelationContract(ConsultationVO vo) throws Exception;
	
	/**
	 * 계약건 권한설정 (관련자 조회 )
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listContractAuthreq(ConsultationVO vo) throws Exception;
	
	/**
	 * 의뢰건 보류 리스트
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listCnsdreqHold(ConsultationVO vo) throws Exception;
	
	/**
	 * 계약건 등록된 특화 속성 정보조회 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String listSpecialAttr(HashMap map) throws Exception;
	
	/**
	 * 이력정보  전체 목록을 조회한다.
	 * @param  vo ExecutionVO
	 * @return List
	 * @throws Exception
	 */
	public List listHisExecution(ExecutionVO vo) throws Exception;	
	
	/**
	 * 결재관련서비스 의뢰정보 
	 * @param  vo ExecutionVO
	 * @return List
	 * @throws Exception
	 */
	public List detailConsiderationApprovalRequest(ConsultationVO vo) throws Exception;
	
	/**
	 * 결재관련서비스 계약마스터정보
	 * @param  vo ExecutionVO
	 * @return List
	 * @throws Exception
	 */
	public List listConsiderationApprovalContract(ConsultationVO vo) throws Exception;
	
	/**
	 * 결재관련서비스 
	 * @param  vo ExecutionVO
	 * @return List
	 * @throws Exception
	 */
	public List listConsiderationApprovalAttachInfo(ConsultationVO vo) throws Exception;
	
	/**
	 * 결재관련서비스 
	 * @param  vo ExecutionVO
	 * @return List
	 * @throws Exception
	 */
	public int modifyConsiderationStatus(ConsultationVO vo) throws Exception;
	
	/**
	 * 파일업로드
	 * @param vo
	 * @return
	 * @throws Exception
	 * 
	 */
	public int saveFileUpload(ConsultationVO vo) throws Exception;
	
	/**
	 * 계약  전체목록을 조회한다.
	 * @param  vo RegistrationVO
	 * @return List
	 * @throws Exception
	 */
	public List listContract(RegistrationVO vo) throws Exception;
	
	/**
	 * 계약  상세정보를 조회한다.
	 * @param  vo RegistrationVO
	 * @return List
	 * @throws Exception
	 */
	public List detailManageCompletion(RegistrationVO vo) throws Exception;
	
	/**
	 * 체결후등록 입력
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public ListOrderedMap insertRegistration(ConsultationVO vo) throws Exception;
	
	/**
	 * 체결후 등록 승인 및 반려
	 * @param  vo RegistrationVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyContract(RegistrationVO vo) throws Exception;
	
	/**
	 * 계약 탭클릭시 정보 저장 master key 유무에 따라 update or insert -- 체결후등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public ListOrderedMap insertContractMasterReg(ConsultationVO vo) throws Exception;
	/**
	 * 체결후등록 상태변경
	 * @param vo
	 * @return
	 * @throws Exception
	 */	
	public int insertRegistrationStatus(ConsultationVO vo) throws Exception;
	
	/**
	 * 체결후등록 등록상세
	 * @param vo
	 * @return
	 * @throws Exception
	 */	
	public List detailConsiderationReg(ConsultationVO vo) throws Exception;
	
	/**
	 * 체결목록이행정보조회 
	 * @param  vo ConsultationExecVO
	 * @return list
	 * @throws Exception
	 */
	public List listConsultationExec(ConsultationExecVO execVo) throws Exception;
	
	/**
	 * 체결품의 첨부파일목록 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List listConsultationApprovalAttachInfo(ConsultationVO vo) throws Exception;
	
	/**
	 * 체결품의계약상세정보조회 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List detailConsultationContractMasterApproval(ConsultationVO vo) throws Exception;
	
	/**
	 * 체결목록특화정보조회 
	 * @param  vo ConsultationSpecialVO
	 * @return list
	 * @throws Exception
	 */
	public List listConsultationSpecial(ConsultationSpecialVO specialVo) throws Exception;
	
	/**
	 * 얀관계약정보 목록을 조회한다.
	 * @param  vo RegistrationVO
	 * @return List
	 * @throws Exception
	 */
	public List listContractRelation(ConsultationVO vo) throws Exception;
	
	/**
	 * 의뢰정보조회 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List detailConsultationApprovalRequest(ConsultationVO vo) throws Exception;

	/**
	 * 엑셀 다운로드
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List listRegistrationExcel(ManageVO vo) throws Exception;
	
	/**
	 * 엑셀 다운로드 for Legal Admin
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List listRegistrationExcelForLegalAdmin(ManageVO vo) throws Exception;
}