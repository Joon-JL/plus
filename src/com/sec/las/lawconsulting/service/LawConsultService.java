/**
* Project Name : 법무시스템 이식
* File Name : LawConsultController.java
* Description : 법률자문 서비스
* Author : 김현구
* Date : 2011. 08. 29
* Copyright : 
*/
package com.sec.las.lawconsulting.service;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sec.las.lawconsulting.dto.LawConsultVO;

/**
 * Description	: 법률자문 Service interface
 * Author 		: 김현구
 * Date			: 2011. 08. 29
 */
public interface LawConsultService {
	/**
	 * 리스트 리턴
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List listLawConsult(LawConsultVO vo) throws Exception;
	
	/**
	 * 리스트 리턴 - 의뢰인
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List listLawConsultReqman(LawConsultVO vo) throws Exception;
	
	
	/*
	 * CC된 자문 리스트 리턴
	 * @param vo
	 * @return
	 * @throws Exception
	 * */
	List listLawConsultCCed(LawConsultVO vo) throws Exception;

	/**
	 * 자문 리스트 리턴 - 그룹장
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List listLawConsultGrpmgr(LawConsultVO vo) throws Exception;
	
	/**
	 * 접수 리스트 최근게시물 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List listLawConsultRecent(LawConsultVO vo) throws Exception;

	/**
	 * 자문 리스트 리턴 - 관리자
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List listLawConsultAdmin(LawConsultVO vo) throws Exception;
	
	
	/**
	 * 자문(표준계약서만) 리스트 리턴 - 관리자
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List listLawStaConsultAdmin(LawConsultVO vo) throws Exception;
	
	/**
	 * 자문(표준계약서만) 리스트 리턴 - 그룹장
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List listLawStaConsultGrpmgr(LawConsultVO vo) throws Exception;
	
	/**
	 * 자문(표준계약서만) 리스트 리턴 - 의뢰인
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List listLawStaConsultReqman(LawConsultVO vo) throws Exception;
	
	/**
	 * 조회한 건에 대한 담당자 리스트 리턴
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List listLawConsultRespman2(LawConsultVO vo) throws Exception;
	
	/**
	 * 팝업창 담당자 리스트
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List listLawConsultRespmanPop(LawConsultVO vo) throws Exception;
	
	/**
	 * 이력 리스트 리턴
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List listLawConsultHistory(LawConsultVO vo) throws Exception;

	
	/**
	 * 이력 리스트 리턴
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List listLawConsultHistoryReqman(LawConsultVO vo) throws Exception;

	/**
	 * 담당자 리스트 리턴
1	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List listLawConsultRespman(LawConsultVO vo) throws Exception;
	
	/**
	 * 삽입
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int insertLawConsult(HttpServletRequest request, LawConsultVO vo) throws Exception;
	
	/**
	 * 검토의견 추가
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int insertReviewLawConsult(HttpServletRequest request, LawConsultVO vo) throws Exception;
	
	/**
	 * 검토의견 추가
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int insertRespmanLawConsult(HttpServletRequest request, LawConsultVO vo) throws Exception;
	
	
	/**
	 * 담당자 찾기
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	String findLawConsultRespman(LawConsultVO vo) throws Exception;
	
	/**
	 * 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int modifyLawConsult(LawConsultVO vo) throws Exception;

	/**
	 * 진행상태 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int modifyStatusLawConsult(HttpServletRequest request, LawConsultVO vo) throws Exception;
	
	/**
	 * 진행상태(EXTNL) 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int modifyExtnlStatusLawConsult(LawConsultVO vo) throws Exception;
	
	/**
	 * 진행상태(INTNL) 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int modifyIntnlStatusLawConsult(LawConsultVO vo) throws Exception;
	
	
	/**
	 * 회신일자 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int modifyReDtLawConsult(LawConsultVO vo) throws Exception;
	
	/**
	 * 그룹장 상세화면에서의 수정(지정버튼)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int modifyGrpmgrDetailLawConsult(LawConsultVO vo) throws Exception;
	
	
	/**
	 * 자문요청 완료상태 변경 (미완료<->완료)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int updateCompleteStatusLawConsult(LawConsultVO vo) throws Exception;
	
	
	/**
	 * 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int deleteLawConsult(LawConsultVO vo) throws Exception;

	/**
	 * 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int deleteAllLawConsult(LawConsultVO vo) throws Exception;
	
	/**
	 * CONSULT_TYPE 테이블 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int deleteLawConsultType(LawConsultVO vo) throws Exception;
	
	/**
	 * 담당자 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int deleteRespmanLawConsult(LawConsultVO vo) throws Exception;
	/**
	 * 상세조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	ListOrderedMap detailLawConsult(LawConsultVO vo) throws Exception;
	
	/**
	 * 상세조회 - 의뢰인
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	ListOrderedMap detailLawConsultReqman(LawConsultVO vo) throws Exception;
	
	/**
	 * 상세조회 - 그룹장
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	ListOrderedMap detailLawConsultGrpmgr(LawConsultVO vo) throws Exception;
	
	/**
	 * 상세조회 - 담당자
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	ListOrderedMap detailLawConsultRespman(LawConsultVO vo) throws Exception;
	
	/**
	 * 이관
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int transferLawConsult(LawConsultVO vo) throws Exception;
	
	/**
	 * 상위 메뉴 ID를 가져온다
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	String getUpMenuId(LawConsultVO vo) throws Exception;
	
	
	/**
	 * user e-mail 정보 획득
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List findUserEmail(LawConsultVO vo) throws Exception;
	
	/**
	 * primary key 생성
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public String getPrimaryKey(LawConsultVO vo) throws Exception;
	
	/**
	 * 법률자문 카운트(왼쪽 메뉴바 내가 할일 출력용)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List countLawConsult(LawConsultVO vo) throws Exception;
	
	/**
	 * 표준계약서 카운트(왼쪽 메뉴바 내가 할일 출력용)
	 * @param vo LawConsultVO
	 * @return
	 * @throws Exception
	 */
	List countStandardContract(LawConsultVO vo) throws Exception;
	
	/**
	 * 자문 참조자 조회 
	 * @return
	 * @throws Exception
	 */
	List<?> listContractAuthreq(LawConsultVO vo) throws Exception;
	
	
	/**
	 * 자문 중요도 마크업 업데이트 ( 설정 / 해제 )
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int setMarkUpAJAX(LawConsultVO vo) throws Exception;
	
}