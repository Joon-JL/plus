/**
 * File name	: OpinionAcceptanceService.java
 * Description	: 타법인 의견 수렴 서비스
 * Author		: 
 * Date			: 2013. 11
 * Copyright	: SAMSUNG.
 */
package com.sec.las.board.service;

import java.util.List;
import com.sec.las.board.dto.OpinionAcceptanceForm;
import com.sec.las.board.dto.OpinionAcceptanceVO;

public interface OpinionAcceptanceService {
	
	/**
	 * 조회
	 * @param vo OpinionAcceptanceVO
	 * @return
	 * @throws Exception
	 */
	List listOpinionAcceptance(OpinionAcceptanceVO vo) throws Exception;
	
	/**
	 * 등록
	 * @param vo OpinionAcceptanceVO
	 * @return int
	 * @throws Exception
	 */
	int insertOpinionAcceptance(OpinionAcceptanceForm form, OpinionAcceptanceVO vo) throws Exception;
	
	/**
	 * 수정
	 * @param vo OpinionAcceptanceVO
	 * @return int
	 * @throws Exception
	 */
	int modifyOpinionAcceptance(OpinionAcceptanceForm form, OpinionAcceptanceVO vo) throws Exception;
	
	/**
	 * 답변 수정
	 * @param vo OpinionAcceptanceVO
	 * @return int
	 * @throws Exception
	 */
	int modifyReplyOpinionAcceptance(OpinionAcceptanceVO vo) throws Exception;
	
	/**
	 * 참여자 등록
	 * @param vo OpinionAcceptanceVO
	 * @return int
	 * @throws Exception
	 */
	int insertOpinionAcceptanceUser(OpinionAcceptanceVO vo) throws Exception;	
	
	/**
	 * 답변 등록
	 * @param vo OpinionAcceptanceVO
	 * @return int
	 * @throws Exception
	 */
	int saveOpinionAcceptanceReply(OpinionAcceptanceVO vo, String insert_kbn) throws Exception;
	
	/**
	 * 과제 답변 대상자 메일 발송 /  과제 스테이터스 변경
	 * @param vo OpinionAcceptanceVO
	 * @return int
	 * @throws Exception
	 */
	int sendRequestReplyOpinion(OpinionAcceptanceVO vo) throws Exception;
	 
	/**
	 * 삭제
	 * @param vo OpinionAcceptanceVO
	 * @return int
	 * @throws Exception
	 */
	int deleteOpinionAcceptance(OpinionAcceptanceVO vo) throws Exception;
	
	/**
	 * 답변 삭제
	 * @param vo OpinionAcceptanceVO
	 * @return int
	 * @throws Exception
	 */
	int deleteOpinionAcceptanceReply(OpinionAcceptanceVO vo) throws Exception;
	
	/**
	 * 상세조회
	 * @param  vo OpinionAcceptanceVO
	 * @return List
	 * @throws Exception
	 */
	List detailOpinionAcceptance(OpinionAcceptanceVO vo) throws Exception;
	
	/**
	 * 답변 상세조회
	 * @param  vo OpinionAcceptanceVO
	 * @return List
	 * @throws Exception
	 */
	List detailOpinionAcceptanceReply(OpinionAcceptanceVO vo) throws Exception;
	
	/**
	 * 하단 리스트 상세조회
	 * @param  vo OpinionAcceptanceVO
	 * @return List
	 * @throws Exception
	 */
	List detailListOpinionAcceptance(OpinionAcceptanceVO vo) throws Exception;
	
	/**
	 * 초기 참여자 리스트 조회 ( 법인 전체 LEGAL ADMIN / 검토자(RA02)
	 * @param  vo OpinionAcceptanceVO
	 * @return List
	 * @throws Exception
	 */
	List getOpinionAcceptanceUser(OpinionAcceptanceVO vo) throws Exception;
	
	/**
	 * 참여자 리스트 상세조회
	 * @param  vo OpinionAcceptanceVO
	 * @return List
	 * @throws Exception
	 */
	List listOpinionAcceptanceUser(OpinionAcceptanceVO vo) throws Exception;
	
	/**
	 * 권한 조회 (자기 작성 글인지 확인)
	 * @param  vo OpinionAcceptanceVO
	 * @return List
	 * @throws Exception
	 */
	Boolean authOpinionAcceptance(OpinionAcceptanceVO vo, String mode) throws Exception;

}
