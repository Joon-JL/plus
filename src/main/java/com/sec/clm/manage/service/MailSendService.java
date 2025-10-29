/**
* Project Name : 계약관리시스템
* File Name :MailSendService.java
* Description : 단계별 알림메일발송 Service interface
* Author : 심주완
* Date : 2011.10.14
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.manage.service;

import java.util.HashMap;
import java.util.Map;

import com.sds.secframework.singleIF.dto.MailVO;
import com.sec.clm.manage.dto.CompletionVO;
import com.sec.clm.manage.dto.ConclusionVO;
import com.sec.clm.manage.dto.ConsultationVO;

public interface MailSendService {
	
	/**
	 * 계약체결품의 합의요청메일 발신
	 * @param vo ConsultationVO
	 * @return
	 * @throws Exception
	 */
	public void sendAgreeRequestNotifyMailSend(ConsultationVO vo) throws Exception;
	
	/**
	 * 계약체결본등록 체결본등록확인 요청메일
	 * @param vo ConclusionVO
	 * @return
	 * @throws Exception
	 */
	public void sendContractConculusionRegisterNotifyMailSend(ConclusionVO vo) throws Exception;
	
	/**
	 * 법무 검토의뢰 검토의견 작성 완료 발신
	 * @param vo ConsultationVO
	 * @return
	 * @throws Exception
	 */
	public void sendConsiderationDeliveryMailSend(com.sec.clm.review.dto.ConsultationVO vo) throws Exception;
	
	/**
	 * 법무 검토요청 발신
	 * @param vo ConsultationVO
	 * @return
	 * @throws Exception
	 */
	public void sendConsiderationApplyMailSend(com.sec.clm.review.dto.ConsultationVO vo) throws Exception;
	
	/**
	 * 법무 검토회신 발신
	 * @param vo ConsultationVO
	 * @return
	 * @throws Exception
	 */
	public void sendConsiderationReplyMailSend(com.sec.clm.review.dto.ConsultationVO vo) throws Exception;
	
	/**
	 * 법무담당자 지정 메일 발신
	 * @param vo ConsultationVO
	 * @return
	 * @throws Exception
	 */
	public void sendConsiderationRespmanMailSend(com.sec.clm.review.dto.ConsultationVO vo) throws Exception;
	
	
	
	/**
	 * 종료관리-변경확인 발신
	 * @param vo ConsultationVO
	 * @return
	 * @throws Exception
	 */
	public void sendCompletionNotifyMailSend(CompletionVO vo) throws Exception;
	
	/**
	 * 승인자 승인시 준법지원자에게 승인처리 알림 발신 - 
	 * @param vo ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	public void sendCompleteStatusInformMail(ConsultationVO vo) throws Exception;
	
	/**
	 * 메일 컨텐츠 생성
	 * @param HashMap
	 * @return String
	 * @throws Exception
	 */
	public String getMailContent(HashMap hm) throws Exception;
	
	/**
	 * 준법지원자에게 종료 알림 발신  
	 * @param vo ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	public void sendCompleteInformMail(ConsultationVO vo) throws Exception;
	/**
	 * 승인,반려 후 메일 보내기
	 * @param  HashMap map
	 * @return List
	 * @throws Exception
	 */
	public void sendMailAfterApproval(HashMap map) throws Exception;
	/**
	 * 접수 알림 메일 전송
	 * @param  CompletionVO vo
	 * @return List
	 * @throws Exception
	 */
	public void sendAcceptInfoMail(HashMap map) throws Exception;
	
	/**
	 * 전자검토자 임시 담당자 지정 메일 전송
	 * @param  HashMap map
	 * @return List
	 * @throws Exception
	 */
	public void sendElecRespmanMailSend(HashMap map) throws Exception ;
	
	/**
	 * 접수 알림 메일 전송 (담당회사 법무그룹장에게 메일전송) 
	 * @param  HashMap map
	 * @return List
	 * @throws Exception
	 */
	public void sendReqInfoMail(HashMap map) throws Exception ;
	
	/**
	 * 검토발신 시 전자검토자에게 알림메일 전송
	 * @param vo ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	public void sendConsiderationReviewerMailSend(com.sec.clm.review.dto.ConsultationVO vo) throws Exception ;
	
	/**
	 * 법률자문/표준계약서 메일 컨텐츠 생성
	 * @param hm
	 * @return
	 * @throws Exception
	 */
	public String getLawConsultMailContent(HashMap hm) throws Exception;
	
	/**
	 * 결재상신 시 수신자리스트에 알림메일 전송
	 * @param vo MailVO
	 * @return 
	 * @throws Exception
	 */
	public void sendApprovalMail(MailVO mailVo) throws Exception;
	
	/**
	 * 법무 검토자 의견 변경 건 메일 발송
	 * @param vo ConsultationVO
	 * @return
	 * @throws Exception
	 */
	public void sendMailSendModiLegalOpinion(com.sec.clm.review.dto.ConsultationVO vo) throws Exception;

	/**
	 * Send mail Hard copy not delivered
	 * Sungwoo added 2014-06-17
	 * @param vo ConsultationVO
	 * @return
	 * @throws Exception
	 */
	public void sendHardCopyMail(HashMap map) throws Exception;
	
	
	void sendEmailReviewReplied(com.sec.clm.review.dto.ConsultationVO vo) throws Exception ;
	
	/* FERNANDO (MFA project) Jan/2024 - start */
	public void sendSimpleEmail(Map params) throws Exception;
	/* FERNANDO (MFA project) Jan/2024 - end */
}