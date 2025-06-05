/**
* Project Name : 법무시스템 이식
* File Name : SpeakConsultController.java
* Description : 구두계약 서비스
* Author : 김현구
* Date : 2011. 08. 05
* Copyright : 
*/

package com.sec.las.lawconsulting.service;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sec.las.lawconsulting.dto.SpeakConsultVO;

/**
 * Description	: 구두계약 Service interface
 * Author 		: 김현구
 * Date			: 2011. 08. 05
 */
public interface SpeakConsultService {

	/**
	 * 리스트 리턴
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List listSpeakConsult(SpeakConsultVO vo) throws Exception;

	/**
	 * 삽입
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int insertSpeakConsult(SpeakConsultVO vo) throws Exception;

	/**
	 * 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int modifySpeakConsult(SpeakConsultVO vo) throws Exception;

	/**
	 * 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int deleteSpeakConsult(SpeakConsultVO vo) throws Exception;

	/**
	 * 상세조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	ListOrderedMap detailSpeakConsult(SpeakConsultVO vo) throws Exception;
	
	/**
	 * 상위 메뉴 ID를 가져온다
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	String getUpMenuId(SpeakConsultVO vo) throws Exception;

}