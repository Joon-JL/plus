package com.sec.clm.manage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sec.clm.manage.dto.ConsiderationVO;
import com.sec.clm.manage.dto.ConsultationVO;
import com.sec.clm.manage.dto.ExecutionVO;
import com.sec.clm.manage.dto.GERPInfoVO;

public interface ConsiderationService {
	
	/**
	 * 계약 아이디  설정   
	 * @return
	 * @throws Exception 	
	 */
	String getId() throws Exception;	
	
	/**
	 * 으뢰 아이디 설정 현재 사용
	 * @param str
	 * @return
	 * @throws Exception
	 */
	//String getNewReqId(String str) throws Exception;
	
	/**
	 * 회신 요청일 +3 일 이후 설정 하기 
	 * @return 
	 * @throws Exception
	 */
	String getReDemndday() throws Exception;	
	/**
	 * 최초의뢰 / 재의뢰/ 최종본 의뢰 상태 설정 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	String getReqStatus(String prev_cnsdreq_id, String plndbn_req_yn, ConsiderationVO vo) throws Exception;
	/**
	 * 최초의뢰 / 재의뢰/ 최종본 의뢰 상태 설정 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	String getReqStatus(String prev_cnsdreq_id, String plndbn_req_yn, String last_locale) throws Exception;
	/**
	 * 의뢰건 상세조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List detailConsideration(ConsultationVO vo) throws Exception;
	/**
	 * prcs_depth를 변수로 받아서 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List detailConsideration2(ConsultationVO vo) throws Exception;
	
	/**
	 * 의뢰건 상세조회 - 체결 후 등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List detailConsiderationReg(ConsultationVO vo) throws Exception;
	
	/**
	 * 체결 후 등록 재의뢰/예정본의뢰/수정/임시저장
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	ListOrderedMap insertConsiderationReg(ConsultationVO vo) throws Exception;
	
	
	/**
	 * 재의뢰/예정본의뢰/수정/임시저장
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	ListOrderedMap insertConsideration(ConsultationVO vo) throws Exception;
	
	/**
	 * 최초의뢰시/최종본의뢰시 임시저장후 상태값 변경
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int insertConsiderationStatus(ConsultationVO vo) throws Exception;
	
	/**
	 * View 조회 에서 재의뢰,최종본의뢰 버튼 클릭 시 이전 의뢰 정보 Copy 
	 * @param vo
	 * @return 새 의뢰아이디 
	 * @throws Exception
	 */
	ListOrderedMap prevCnsdReqCopyConsideration(ConsultationVO vo) throws Exception;
	
	/**
	 * 의뢰시 첨부 필수 사항 체크 최종본 의뢰시 계약 여러건 인경우 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	ListOrderedMap getFilevalidate(ConsultationVO vo) throws Exception;
	/**
	 * 저장시 필수 입력 사항 검색 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	ListOrderedMap searchRequired(ConsultationVO vo) throws Exception;
		
	
	/**
	 * 재검토 의뢰시 내용 저장 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	ListOrderedMap modifyAgainReConsideration(ConsultationVO vo) throws Exception;
	
	/**
	 * 의뢰 및 계약건 삭제,Drop,보류
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	ListOrderedMap deleteDropDefer(ConsultationVO vo) throws Exception;
	
	/**
	 * 의뢰 및 계약건 Drop , 보류 사유 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List detailDropDefer(ConsultationVO vo) throws Exception;	
	
	/**
	 * 계약 상세 내역 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List detailContractMaster(ConsultationVO vo) throws Exception;
	
	/**
	 * 최조의뢰, 최종본의뢰시 수정 화면  계약건 정보조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List detailForwardContractMaster(ConsultationVO vo) throws Exception;
	
	/**
	 * 계약건 저장
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	ListOrderedMap insertContractMaster(ConsultationVO vo) throws Exception;
	
	/**
	 * 계약건에 연관 계약 설정을 위한 계약 리스트 팝업(법무 그룹장 로그인 시)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List popupListContract(ConsultationVO vo) throws Exception;	
	
	/**
	 * 계약건에 연관 계약 설정을 위한 계약 리스트 팝업(법무 그룹장 로그인 시)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List popupListContract1(ConsultationVO vo) throws Exception;	
	
	/**
	 * 계약건에 연관 계약 설정을 위한 계약 리스트 팝업(법무 그룹장 로그인 시)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List popupListContract4(ConsultationVO vo) throws Exception;	
	
	/**
	 * 계약건에 연관 계약 설정을 위한 계약 리스트 팝업(법무 담당자 로그인 시)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List popupListContract2(ConsultationVO vo) throws Exception;
	
	/**
	 * 계약건에 연관 계약 설정을 위한 계약 리스트 팝업(시스템 관리자 로그인 시)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List popupListContract3(ConsultationVO vo) throws Exception;
	
	/**
	 * 계약건에 계약 유형별 특화 속성 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List listTypeSpclinfo(ConsultationVO vo) throws Exception;
	
	/**
	 * 계약건에 계약 특화 속성 수정 화면 속성 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List listTypeSpclinfoMod(ConsultationVO vo) throws Exception;
	
	/**
	 * 연관계약 select
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	HashMap listRelationContract(ConsultationVO vo) throws Exception;
	/**
	 * 연관계약 insert/delete
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int actionRelationContract(ConsultationVO vo) throws Exception;
	
	/**
	 * 계약건 권한설정 (관련자 조회 )
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List listContractAuthreq(ConsultationVO vo) throws Exception;
	

	/**
	 * Contract Management - Reviewer List
	 * Sungwoo added 2014-12-08 for Reviewer List Search.
	 * @return
	 * @throws Exception
	 */
	List listContractReviewer(ConsultationVO vo) throws Exception;
	
	/**
	 * 의뢰건 보류 리스트
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List listCnsdreqHold(ConsultationVO vo) throws Exception;
	
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
	List listHisExecution(ExecutionVO vo) throws Exception;	
	
	/**
	 * 결재관련서비스 의뢰정보 
	 * @param  vo ExecutionVO
	 * @return List
	 * @throws Exception
	 */
	List detailConsiderationApprovalRequest(ConsultationVO vo) throws Exception;
	
	/**
	 * 결재관련서비스 계약마스터정보
	 * @param  vo ExecutionVO
	 * @return List
	 * @throws Exception
	 */
	List listConsiderationApprovalContract(ConsultationVO vo) throws Exception;
	
	/**
	 * 결재관련서비스 
	 * @param  vo ExecutionVO
	 * @return List
	 * @throws Exception
	 */
	List listConsiderationApprovalAttachInfo(ConsultationVO vo) throws Exception;
	
	/**
	 * 결재관련서비스 
	 * @param  vo ExecutionVO
	 * @return List
	 * @throws Exception
	 */
	int modifyConsiderationStatus(ConsultationVO vo) throws Exception;
	
	/**
	 * 검토의뢰로 상태값 변경
	 * @param  vo ExecutionVO
	 * @return List
	 * @throws Exception
	 */
	int modifyConsiderationApprove(ConsultationVO vo) throws Exception;
	
	/**
	 * 테스트용
	 * @param vo
	 * @return
	 * @throws Exception
	 * 
	 */
	int saveFileUpload(ConsultationVO vo) throws Exception;
	
	/**
	 * 의뢰 중복방지 구분값 조회 
	 * @param vo ConsultationVO
	 * @return
	 * @throws Exception
	 */
	List preventDuplication(ConsultationVO vo) throws Exception;
	/**
	 * 최종본검토 가능여부 체크
	 * @param  vo ExecutionVO
	 * @return List
	 * @throws Exception
	 */
	String verifyFinalConsideration(ConsultationVO vo) throws Exception;
	
	/**
	 * 검토의뢰 복사
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	String insertCopyCnsd(ConsultationVO vo) throws Exception;
	
	/**
	 * 표준계약서 반려처리
	 * @param  vo ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	public int rejctOpnn(ConsultationVO vo) throws Exception;
	
	/**
	 * 표준계약서 승인처리
	 * @param  vo ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	public ListOrderedMap ApprovalStdContr(ConsultationVO vo) throws Exception;
	
	/**
	 * 인쇄용 검토정보
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Map getConsiderationInfoPrint(ConsiderationVO vo) throws Exception ;
	
	
	/**
	 * 인쇄용 관련자정보
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public String getReqAuthPrint(ConsiderationVO vo) throws Exception ;
	
	/**
	 * 인쇄용 의뢰 첨부파일 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List getReqAttachList(ConsiderationVO vo) throws Exception ;
	
	/**
	 * 인쇄용 회신 첨부파일 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List getReplyAttachList(ConsiderationVO vo) throws Exception ;
	
	/**
	 * 인쇄용 기타 첨부파일 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List getEtcAttachList(ConsiderationVO vo) throws Exception ;
	
	/**
	 * 인쇄용 특화정보 가져오기
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List getSpclList(ConsiderationVO vo) throws Exception ;
	
	/**
	 * 인쇄용 이력 목록 가져오기
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List getHisList(ExecutionVO vo) throws Exception ;
	
	/**
	 * 인쇄용 결재라인 가져오기
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List getApprLineList(ConsiderationVO vo) throws Exception ;
	
	/**
	 * 인쇄용 승인자(결재자)정보
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Map getContractApproveMan(ConsiderationVO vo) throws Exception ;
	
	/**
	 * 인쇄용 계약자동연장 이력
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List getAutoRnewList(ConsiderationVO vo) throws Exception ;

	/**
	 * 리뷰어 휴가 여부
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List checkVacation(ConsiderationVO vo) throws Exception ;
	
	/**
	 * 계약 상세 중 GERP Information 영역의 데이터 조회.
	 * 2014-03-27 Kevin Added.
	 * @param vo(GERPInfo type)
	 * @return List collection
	 * @throws Exception
	 */
	public List getGERPInformationDetail(GERPInfoVO vo) throws Exception;
	
	/**
	 * 정의된 Code type에 따라 해당하는 코드 리스트를 리턴한다.
	 * 2014-04-08 Kevin added.
	 * @param vo(GERPInfo type)
	 * @return List collection
	 * @throws Exception
	 */
	public List getGERPCodeListByType(GERPInfoVO vo) throws Exception;
	
	
	/**
	 * 2014-07-24 Kevin
	 * Get step, status with index description when the state is in (drafted saved, review in progress, replied)
	 * Those data will be placed on each contract detail page.
	 */
	Map getStepStatusInformationWithCNSDREQID(ConsiderationVO vo) throws Exception;

	/**
	 * 2014-08-12 Kevin
	 * Get data to be used into Basic Information section that divided for common use from each contract detail page.
	 */
	List getDataForBasicInformationSection(ConsiderationVO vo) throws Exception;
	
	/**
	 * 2014-09-01 Sungwoo.
	 * Get attachment to be used Contract History Information section on each contract detail page.
	 */
	List listCompletionAttachInfo(ExecutionVO vo)throws Exception;
}