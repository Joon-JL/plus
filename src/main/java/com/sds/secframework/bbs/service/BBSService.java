package com.sds.secframework.bbs.service;

import java.util.HashMap;
import java.util.List;
 
import com.sds.secframework.bbs.dto.BBSVO;

public interface BBSService {
	
	/**********************************************************
	 * 게시판
	 **********************************************************/

	/* 게시글 목록조회
	*/
	List listBBSDetailPage(BBSVO vo) throws Exception;

	/* 게시글 상세조회
	 * 0. 조회카운트 증가
	 * 1. 내역조회
	 * 2. 첨부조회
	 * 3. 덧글조회
	 * 4. 글그룹목록 조회
	 * 5. 이전글/다음글 NOTICE_ID 조회
	 */
	HashMap detailBBSDetail(BBSVO vo) throws Exception;
	
	/* 게시글 등록
	 * 0. 첨부등록
	 * 1. 게시글 등록(임시저장)
	 * Return : 게시글 아이디 반환
	 */
	String insertBBSDetail(BBSVO vo) throws Exception;
	
	/* 댓글 등록하기 위하여 조회
	 * 0. 게시글 원문 조회
	 */
	List getBBSDetailForReply(BBSVO vo) throws Exception;

	/* 댓글 등록
	 * 1. 첨부등록
	 * 2. 댓글 등록(임시저장 없음)
	 * 3. 원글사용자에게 메신저 전송이 체크되어 있을경우 알림메시지 전송
	 */
	String insertBBSDetailReply(BBSVO vo) throws Exception;
	
	/* 게시글 수정(게시글 본인에 한하여)
	 * 0. 첨부추가 및 첨부삭제
	 * 1. 게시글, 댓글 수정
	 */
	int modifyBBSDetail(BBSVO vo) throws Exception;
	
	/* 게시글 삭제(게시글 본인에 한하여)
	 *  0. 추천정보 삭제
	 *  1. 덧글삭제
	 *  2. 댓글 첨부삭제
	 *  3. 댓글삭제
	 *  4. 게시글 원문 첨부삭제
	 *  5. 게시글 원문 삭제
	 */
	int deleteBBSDetail(BBSVO vo) throws Exception;
	
    // 덧글등록
	int insertBBSAppend(BBSVO vo) throws Exception;
	
	// 덧글수정(덧글본인에 한하여)
	int modifyBBSAppend(BBSVO vo) throws Exception;
	
	// 덧글삭제(덧글본인에 한하여)
	int deleteBBSAppend(BBSVO vo) throws Exception;
	
	/* 추천등록
	 * 0. 게시글 추천카운트 증가
	 * 1. 추천등록
	 */
	int insertBBSRecommend(BBSVO vo) throws Exception;
	
}
