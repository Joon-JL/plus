/**
 * Project Name : 법무시스템 이식
 * File name	: ConsiderationServiceImpl.java
 * Description	: 검토의뢰 Service interface
 * Author		: 한지훈
 * Date			: 2011. 09. 06
 * Copyright	:
 */
package com.sec.clm.review.service;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sec.clm.manage.dto.CompletionVO;
import com.sec.clm.manage.dto.ExecutionVO;
import com.sec.clm.review.dto.ConsiderationVO;
import com.sec.clm.review.dto.ConsultationVO;

/**
 * Description	: Service interface
 * Author		: 한지훈
 * Date			: 2011. 09. 06
 */
public interface ConsiderationService {
	
	/**
	 * 의뢰 중요도 체크 처리
	 * @param vo ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	public int setMarkUpAJAX(ConsiderationVO vo) throws Exception;
	
	/**
	 * RETURN 처리
	 * @param vo ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	public int returnRequest(ConsiderationVO vo) throws Exception;
	
	/**
	 * CLOSE 사유 조회
	 * @param vo ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	List closeRTNView(ConsiderationVO vo) throws Exception;
	
	/**
	 * CLOSE 처리
	 * @param vo ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	public int closeRequest(ConsiderationVO vo) throws Exception;
	
	/**
	 * 검토 상세에서 상시 관련자 추가 기능 
	 * @param vo ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	public int modifyRefCCAJAX(ConsultationVO vo) throws Exception;
	
	/**
	 * 의뢰내역 조회
	 * @param vo ConsiderationVO
	 * @return
	 * @throws Exception
	 */
	List listConsideration(ConsiderationVO vo) throws Exception;
	
	/**
	 * 상대회사관련 계약건 팝업
	 * @param vo ConsultationVO
	 * @return
	 * @throws Exception
	 */
	List listConsiderationPop(ConsultationVO vo) throws Exception;
	
	/**
	 * 유관부서팝업
	 * @param vo ConsiderationVO
	 * @return
	 * @throws Exception
	 */
	List listConsideration_under_d(ConsultationVO vo) throws Exception;
	
	/**
	 * 검토의뢰 상세
	 * @param vo ConsultationVO
	 * @return
	 * @throws Exception
	 */
	List detailConsideration(ConsultationVO vo) throws Exception;
	
	/*
	 * 2014-07-23 Kevin
	 * Update the contact to be in final review
	 */
	void updateContractAsFinalReview(ConsultationVO vo) throws Exception;

	/**
	 * 검토의뢰 요약 정보[검토이력 사용]
	 * @param vo ConsultationVO
	 * @return
	 * @throws Exception
	 */
	List detailConsiderationCnsdInfo(ExecutionVO vo) throws Exception;
	
	/**
	 * A.부서검토반려
	 * @param vo ConsultationVO
	 * @return
	 * @throws Exception
	 */
	
	ListOrderedMap contractCnsdrejct(ConsultationVO vo) throws Exception;
	
	/**
	 * B.계약별_부서검토반려
	 * @param vo ConsultationVO
	 * @return
	 * @throws Exception
	 */
	ListOrderedMap contractDeptcnsdrejct(ConsultationVO vo) throws Exception;
	
	/**
	 * 담당자 리스트[팝업]
	 * @param vo ConsultationVO
	 * @return
	 * @throws Exception
	 */
	List popRespman(ConsultationVO vo) throws Exception;
	
	/**
	 * 그룹장 메세지
	 * @param vo ConsultationVO
	 * @return
	 * @throws Exception
	 */
	List listApbtMemo(ConsultationVO vo) throws Exception;
	
	/**
	 * 담당자 리스트
	 * @param vo ConsultationVO
	 * @return
	 * @throws Exception
	 */
	List listRespman(ConsultationVO vo) throws Exception;
	
	/**
	 * 담당자 리스트[협업부서]
	 * @param vo ConsultationVO
	 * @return
	 * @throws Exception
	 */
	List listRespmanSub(ConsultationVO vo) throws Exception;
	
	/**
	 * 관련자 리스트
	 * @param vo ConsultationVO
	 * @return
	 * @throws Exception
	 */
	List listRelationman(ConsultationVO vo) throws Exception;
	
	/**
	 * 마스터테이블정보 리턴
	 * @param vo ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	List detailContractMaster(ConsultationVO vo) throws Exception;
	
	/**
	 * 첨부파일목록 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List listCompletionAttachInfo(ConsultationVO vo) throws Exception;
	
	/**
	 * 특화정보 리턴[계약관리]
	 * @param vo ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	List listTypeSpclinfoCntr(ConsultationVO vo) throws Exception;
	
	/**
	 * 특화정보 리턴[법무]
	 * @param vo ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	List listTypeSpclinfoCnsd(ConsultationVO vo) throws Exception;
	
	/**
	 * 연관계약 리턴
	 * @param vo ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	//List listRelationContract(ConsultationVO vo) throws Exception;
	
	/**
	 * 연관계약 select
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	HashMap listRelationContract(ConsultationVO vo) throws Exception;
	
	/**
	 * 의견전달, 발신 취소
	 * @param vo ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	int processCancel(ConsultationVO vo) throws Exception;
	
	/**
	 * 연관계약 insert/delete
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int actionRelationContract(ConsultationVO vo) throws Exception;
	
	/**
	 * 이관요청
	 * @param vo ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	int applyTrans(ConsultationVO vo) throws Exception;
	
	/**
	 * 법무부서 이관
	 * @param vo ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	int transLawDept(ConsultationVO vo) throws Exception;
	
	/**
	 * 검토요청
	 * @param vo ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	int applyResp(ConsultationVO vo) throws Exception;
	
	/**
	 * 이관불필요
	 * @param vo ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	int denyTrans(ConsultationVO vo) throws Exception;
	
	/**
	 * 이관승인
	 * @param vo ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	int approveTrans(ConsultationVO vo) throws Exception;
	
	/**
	 * 이관거부
	 * @param vo ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	int disapproveTrans(ConsultationVO vo) throws Exception;
	
	/**
	 * 담당자 의견 승인 처리
	 * @param vo ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	int approvalOpnn(ConsultationVO vo) throws Exception;
	
	/**
	 * 담당자 의견 반려 처리
	 * @param vo ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	int rejctOpnn(ConsultationVO vo) throws Exception;
	
	/**
	 * 담당자 승인
	 * @param vo ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	int confirmRespman(ConsultationVO vo) throws Exception;
	
	
	/**
	 * 선택 되지 않은 계약건의 필수 조건 체크
	 * @param vo ConsultationVO
	 * @return ListOrderedMap
	 * @throws Exception
	 */
	ListOrderedMap searchRequired(ConsultationVO vo) throws Exception;
	
	/**
	 * 임시저장/ 의견전달 /발신
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	HashMap returnTabConsideration(ConsultationVO vo) throws Exception;
	HashMap returnConsideration(ConsultationVO vo) throws Exception;	
	HashMap modifyContractMaster(ConsultationVO vo) throws Exception;	
	List forwardContractMaster(ConsultationVO vo) throws Exception;
	
	/**
	 * 계약건 권한설정 (관련자 조회 )
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List listContractAuthreq(ConsultationVO vo) throws Exception;
	
	/**
	 * 회신/의견전달 시 필요입력사항 체크 후 회신/의견전달이 가능한 상태인지 리턴 
	 * 현재 사용하지 않고 db function 으로 대체함
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	boolean getChkDeptCnsd(ConsultationVO vo) throws Exception;
	
	/**
	 * 정검토부서 검토의견 상태 정보 가져옴
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List returnListMndeptcnsd(ConsiderationVO vo) throws Exception;	
	
	/**
	 * 부검토부서 검토의견 상태정보 가져옴 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List returnListVcdeptcnsd(ConsiderationVO vo) throws Exception;
	
	/**
	 * 계약접수 건 카운트 (메인화면 출력용)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List countConsideration(ConsiderationVO vo) throws Exception;
	
	
	/**
	 * 계약관리 건 카운트 (메인화면 출력용)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List countContractMng(ConsiderationVO vo) throws Exception;
	
	/**
	 * 계약관리 건 카운트 (메인화면 출력용) NEW
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List countContractMngNew(ConsiderationVO vo) throws Exception;
	
	/**
	 * 증명서류건 카운트 (메인화면 출력용)
	 */
	List countSigndoc(ConsiderationVO vo) throws Exception;
	
	/**
	 * 날인접수 건 카운트 (메인화면 출력용)
	 */
	List countSeal(ConsiderationVO vo) throws Exception;
	
	/**
	 * 권한별 버튼 컨트롤 (메인화면 출력용)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	String[] getButtonControl(ConsiderationVO vo) throws Exception;
	
	/**
	 * 이력정보 리스트 기준 부서 조회
	 * @param  vo ExecutionVO
	 * @return List
	 * @throws Exception
	 */
	List listContractDeptDiv(ExecutionVO vo) throws Exception;
	
	/**
	 * 이력정보  전체 목록을 조회한다.
	 * @param  vo ExecutionVO
	 * @return List
	 * @throws Exception
	 */
	List listHisExecution(ExecutionVO vo) throws Exception;

	/**
	 * 부서 검토 승인, 반려 이력 조회
	 * @param  vo ExecutionVO
	 * @return List
	 * @throws Exception
	 */
	List listHisDeptCnsdRejct(ExecutionVO vo) throws Exception;
	
	/**
	 * (구)법무시스템에서 이관된 데이터의 이력정보  전체 목록을 조회한다.
	 * @param  vo ExecutionVO
	 * @return List
	 * @throws Exception
	 */
	List listOldHisExecution(ExecutionVO vo) throws Exception;

	
	/**
	 * 첨부 필수 사항 체크 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	ListOrderedMap getFilevalidate(ConsultationVO vo) throws Exception;
	
	/**
	 * 연관계약정보
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List listConsultationRealtion(ConsultationVO vo) throws Exception;
	
	/**
	 * 보류이력 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List listConsiderationHold(ConsultationVO vo) throws Exception;
	
	/**
	 * 사별이관 데이터 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int modifyOldConsideration(ConsultationVO vo) throws Exception;
	
	/**
	 * 사별이관 데이터 등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int insertOldConsideration(ConsultationVO vo) throws Exception;	
	
	/**
	 * 사별이관 데이터 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int deleteOldConsideration(ConsultationVO vo) throws Exception;
	
	/**
	 * 수정할 최종 legal opinion을 가지고 와서 popup화면에서 보여준다.
	 * @param vo ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	List forwardLegalOpinion(ConsultationVO vo) throws Exception;
	
	/**
	 * CLOSE 처리
	 * @param vo ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	public int updateLegOpinion(ConsultationVO vo) throws Exception;
	
	/**
	 * 계약에 대한 체크 리스트 항목을 보여 준다.
	 * @param vo ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	List forwardCheckItem(ConsiderationVO vo) throws Exception;
	
	/**
	 * 법인에 대한 체크 리스트 항목을 보여 준다.
	 * @param vo ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	List selectCheckItem(ConsiderationVO vo) throws Exception;
	
	/**
	 * HQ로 의뢰 할 수 있는 팝업을 띄어 준다. 임시 저장 건도 있기 때문에 내용을 조회해야 한다.
	 * @param vo ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	List forwardRequestHQ(ConsiderationVO vo) throws Exception;
	
	/**
	 * HQ로 재의뢰 할 수 있는 팝업을 띄어 준다. 가장 최근에 의뢰한 내용을 보여 준다.
	 * @param vo ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	List forwardRERequestHQ(ConsiderationVO vo) throws Exception;
	
	
	/**
	 * HQ로 의뢰 임시 저장을 한다.
	 * @param vo ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	public int insertHqRequest(ConsiderationVO vo) throws Exception;
	
	/**
	 * 필수 항목 중 No로 체크한 내용을 저장을 한다.
	 * @param vo ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	public int insertCheckReson(ConsiderationVO vo) throws Exception;
	
	/**
	 * 필수항목 체크 사유 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List listCheckList(ConsultationVO vo) throws Exception;
	
	/**
	 * hq 검토 의뢰 여부
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List listHqCheckList(ConsultationVO vo) throws Exception;
	
	/**
	 * hq 검토 의뢰 여부
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List listHqCheckList2(ConsultationVO vo) throws Exception;
	
	/**
	 * hq 검토 의뢰 여부
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List selectHqRequest(ConsultationVO vo) throws Exception;
	
	/**
	 * 복합 법인 검토자 여부 판단
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List listMultYn(ConsiderationVO vo) throws Exception;
}