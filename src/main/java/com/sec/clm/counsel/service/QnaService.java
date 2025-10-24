/**
* Project Name : 계약관리시스템
* File Name : QnaService.java
* Description : QNA Service
* Author : dawn
* Date : 2010.09.06
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.counsel.service;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sec.clm.counsel.dto.QnaVO;

public interface QnaService {

	/**
	* QNA 목록
	* 
	* @param QnaVO
	* @return List
	* @throws Exception
	*/
	List listQna(QnaVO vo) throws Exception ;

	/**
	* QNA 상세목록
	* 
	* @param QnaVO
	* @return List
	* @throws Exception
	*/
	ListOrderedMap detailQna(QnaVO vo) throws Exception ;

	/**
	* QNA 수정
	* 
	* @param QnaVO
	* @return List
	* @throws Exception
	*/
	int modifyQna(QnaVO vo) throws Exception ;

	/**
	* QNA 삭제
	* 
	* @param QnaVO
	* @return List
	* @throws Exception
	*/
	int deleteQna(QnaVO vo) throws Exception ;

	/**
	* QNA 저장
	* 
	* @param QnaVO
	* @return List
	* @throws Exception
	*/
	int insertQna(QnaVO vo) throws Exception ;

	/**
	* QNA 권한체크
	* 
	* @param String, QnaVO
	* @return boolean
	* @throws Exception
	*/
	boolean authQna(String mode, QnaVO vo) throws Exception ;

	/**
	* QNA 상세화면 댓글목록
	* 
	* @param QnaVO
	* @return List
	* @throws Exception
	*/
	List detailReplyQna(QnaVO vo) throws Exception ;

	/**
	* 사용자 권한조회
	* 
	* @param QnaVO
	* @return 
	* @throws Exception
	*/
	ListOrderedMap roleCdQna(QnaVO vo) throws Exception ;
	
	/**
	* QNA 이관
	* 
	* @param QnaVO
	* @return List
	* @throws Exception
	*/
	int transferQna(QnaVO vo) throws Exception ;
	
	/**
	 * 답변 개수 카운트 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int countReplyQna(QnaVO vo) throws Exception ;
	
	/**
	* QNA 카운트
	* 
	* @param QnaVO
	* @return List
	* @throws Exception
	*/
	int countQna(QnaVO vo) throws Exception ;
}
