/**
* Project Name : 계약관리시스템
* File Name :ConsultationService.java
* Description : 체결품의Service인터페이스
* Author : 심주완
* Date : 2011.09.16
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.manage.service;

import java.util.List;
import java.util.HashMap;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sec.clm.manage.dto.ConsultationVO;
import com.sec.clm.manage.dto.ConsultationSpecialVO;
import com.sec.clm.manage.dto.ConsultationExecVO;



public interface ConsultationService {
	//체결품의목록
	List listConsultation(ConsultationVO vo) throws Exception;
	
	// 체결품의에서 회신상태로 프로세스 복원
	int cancelConsultation(ConsultationVO vo) throws Exception;
	
	//체결품의 등록
	//int modifyConsultation(ConsultationVO vo, ConsultationExecVO execVo, ConsultationSpecialVO specialVo) throws Exception;
	int modifyConsultation(ConsultationVO vo, ConsultationExecVO execVo) throws Exception;
	//int modifyConsultation(ConsultationVO vo, ExecutionVO execVo) throws Exception;
	//체결품의 상세
	List detailConsultation(ConsultationVO vo) throws Exception;
	
	//체결품의 상세
	List detailOnlyConsultation(ConsultationVO vo) throws Exception;
	
	//체결품의상세-계약탭
	List listConsultationContract(ConsultationVO vo) throws Exception;
	//체결품의 계약상세
	List detailConsultationContractMaster(ConsultationVO vo) throws Exception;
	
	//체결품의 연관계약정보
	List listConsultationRealtion(ConsultationVO vo) throws Exception;
	
	// 2014-09-09 Kevin added. Its a new version of listConsultationRelation method above.
	List listConsultationRealtion2(ConsultationVO vo) throws Exception;
	
	//체결품의 특화정보이력
	List listConsultationSpecial(ConsultationSpecialVO specialVo) throws Exception;
	//체결품의 이행정보
	List listConsultationExec(ConsultationExecVO execVo) throws Exception;
	//체결품의-합의자목록
	List listConsultationAgreeInfo(ConsultationVO vo) throws Exception;
	//재상신 or 재검토상태로 수정(계약)
	int modifyReworkContractStatus(ConsultationVO vo) throws Exception;
	//재상신 or 재검토상태로 수정(의뢰)
	int modifyReworkRequestStatus(ConsultationVO vo) throws Exception;
	//재상신 or 재검토상태로 수정(검토)
	int modifyReworkCnsdStatus(ConsultationVO vo) throws Exception;
	//재상신시 합의정보삭제
	int modifyReworkAgreeInfo(ConsultationVO vo) throws Exception;
	//관련자정보업데이트
	int modifyAuthReqClient(String authReqClient, String cntrt_id, String demndman_id, String demndman_nm, String demndman_dept_nm) throws Exception;
	//보류상태업데이트
	int modifyConsultationStatusDefer(ConsultationVO vo) throws Exception;
	/*===========================================
	 * 결재관련서비스시작
	 ============================================*/
	//의뢰정보
	List detailConsultationApprovalRequest(ConsultationVO vo) throws Exception;
	//계약목록
	List listConsultationApprovalContract(ConsultationVO vo) throws Exception;
	//첨부파일목록
	List listConsultationApprovalAttachInfo(ConsultationVO vo) throws Exception;
	//결재상신후 상태정보수정
	int modifyConsultationStatus(ConsultationVO vo) throws Exception;
	//계약상세정보
	List detailConsultationContractMasterApproval(ConsultationVO vo) throws Exception;
	// 15개 필수 항목 체크
	List listCheckListAppl(ConsultationVO vo) throws Exception;
	
	//해당의뢰건이 Drop(Auto Drop포함)되었을 경우 승인자에게 메일을 발송처리한다.
	//의뢰건일경우 첫번째계약건으로 해서 체결예정일,체결여부를 표시한다.
	void sendDropReq(ListOrderedMap resultTargetMap) throws Exception;
	
	//결재History검색
	List listApprovalHis(ConsultationVO vo) throws Exception;
	
	//체결품의-계약유호성체크
	List listApprovalValidation(ConsultationVO vo) throws Exception;

	/*
	 * TODO : Sungwoo added 2014-09-24 마지막 review 내역 가져오기 위해 임시추가
	 * 추후 Detail 페이지 공통 처리 후 삭제 예정
	*/
	List<?>listReqContractReview(ConsultationVO vo) throws Exception;
}