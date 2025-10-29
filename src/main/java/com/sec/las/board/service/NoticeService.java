/**
 * Project Name : 법무시스템 이식
 * File name	: NoticeService.java
 * Description	: 공지 Service interface
 * Author		: 한지훈
 * Date			: 2011. 08. 16
 * Copyright	:
 */
package com.sec.las.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sec.las.board.dto.NoticeVO;

/**
 * Description	: Service interface
 * Author		: 한지훈
 * Date			: 2011. 08. 16
 */
public interface NoticeService {
	
	/**
	 * 조회
	 * @param vo NoticeVO
	 * @return
	 * @throws Exception
	 */
	List listNotice(NoticeVO vo) throws Exception;
	
	/**
	 * 등록
	 * @param vo NoticeVO
	 * @return int
	 * @throws Exception
	 */
	int insertNotice(NoticeVO vo) throws Exception;
	
	/**
	 * 수정
	 * @param vo NoticeVO
	 * @return int
	 * @throws Exception
	 */
	int modifyNotice(NoticeVO vo) throws Exception;
	 
	/**
	 * 삭제(실제 삭제하는 것이 아니고 DEL_YN을 "Y"로 업데이트) 
	 * @param vo NoticeVO
	 * @return int
	 * @throws Exception
	 */
	int deleteNotice(NoticeVO vo) throws Exception;
	
	/**
	 * 상세조회
	 * @param  vo ContDivMngVO
	 * @return List
	 * @throws Exception
	 */
	List detailNotice(NoticeVO vo) throws Exception;
	
	/**
	 * 조회수 증가
	 * @param vo NoticeVO
	 * @throws Exception
	 */
	void increaseRdCnt(NoticeVO vo) throws Exception;

	/**
	* 표준 조항 권한조회
	* 
	* @param ItemLibraryVO
	* @return boolean
	* @throws Exception
	*/
	boolean authNotice(String mode, NoticeVO vo) throws Exception;

	/**
	 * 사용자 ROLE에 따른 권한 제어
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	String checkAuthByRole(NoticeVO vo) throws Exception;
	
	/**
	 * 법무 메인 - 공지사항 조회
	 * @param vo NoticeVO
	 * @return
	 * @throws Exception
	 */
	List listNoticeByMain(NoticeVO vo) throws Exception;
	
	/**
	 * 전결규정 해당회사 MAX SEQNO 조회 
	 * @param vo NoticeVO
	 * @return 
	 * @throws Exception
	 */
	List getMaxSeqNoByCompCd(NoticeVO vo) throws Exception;
	
	/**
	 * 기존 게시글의 팝업공지를 해제
	 * @throws Exception
	 */
	int updateNoticePopupValue(NoticeVO vo) throws Exception;
	
	/**
	 * 해당 회사의 공지중 팝업 공지가 존재하는지 조회 
	 * @param vo NoticeVO
	 * @return
	 * @throws Exception
	 */
	public List listNoticeLatestSeqno(NoticeVO vo) throws Exception;
	
}
