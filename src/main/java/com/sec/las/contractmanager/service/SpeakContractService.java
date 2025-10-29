/**
* Project Name : 법무시스템 이식
* File Name : SpeakContractController.java
* Description : 구두계약 서비스
* Author : 김현구
* Date : 2011. 08. 05
* Copyright : 
*/

package com.sec.las.contractmanager.service;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sec.las.contractmanager.dto.SpeakContractVO;

/**
 * Description	: 구두계약 Service interface
 * Author 		: 김현구
 * Date			: 2011. 08. 05
 */
public interface SpeakContractService {

	/**
	 * 리스트 리턴
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List listSpeakContract(SpeakContractVO vo) throws Exception;

	/**
	 * 삽입
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int insertSpeakContract(SpeakContractVO vo) throws Exception;

	/**
	 * 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int modifySpeakContract(SpeakContractVO vo) throws Exception;

	/**
	 * 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int deleteSpeakContract(SpeakContractVO vo) throws Exception;

	/**
	 * 상세조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	ListOrderedMap detailSpeakContract(SpeakContractVO vo) throws Exception;
	
	/**
	 * 상위 메뉴 ID를 가져온다
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	String getUpMenuId(SpeakContractVO vo) throws Exception;

}