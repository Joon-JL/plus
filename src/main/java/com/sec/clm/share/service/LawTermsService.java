/**
 * Project Name : 법무시스템 이식
 * File name	: LibraryController.java
 * Description	: 국내/해외계약서  Controller
 * Author		: 신승완
 * Date			: 2011. 08. 04
 * Copyright	:
 */

package com.sec.clm.share.service;

import java.util.HashMap;
import java.util.List;

import com.sec.clm.share.dto.LawTermsVO;
import com.sec.common.util.ClmsDataUtil;
import com.sec.las.lawinformation.dto.MainLawInfoVO;


public interface LawTermsService {
	
	/**
	 * 계약용어집 목록을 조회한다.
	 * @param  vo LawTermsVO
	 * @return List
	 * @throws Exception
	 */
	
	List listLawTerms(LawTermsVO vo) throws Exception;

	/**
	 * 계약용어집 을 등록한다
	 * 회한다.
	 * @param  vo LawTermsVO
	 * @return List
	 * @throws Exception
	 */
	
	int insertLawTerms(LawTermsVO vo) throws Exception;
	
	/**
	 * 계약용어집 을 수정한다
	 * 회한다.
	 * @param  vo LawTermsVO
	 * @return List
	 * @throws Exception
	 */
	
	int modifyLawTerms(LawTermsVO vo) throws Exception;
	/**
	 * 계약용어집 을 삭제한다
	 * 회한다.
	 * @param  vo LawTermsVO
	 * @return List
	 * @throws Exception
	 */

	int deleteLawTerms(LawTermsVO vo) throws Exception;
	
	/**
	 * 계약용어집 을 상세조회한다
	 * 회한다.
	 * @param  vo LawTermsVO
	 * @return List
	 * @throws Exception
	 */

	List detailLawTerms(LawTermsVO vo) throws Exception;
	
	/**
	 * 계약용어목록  조회수 증가
	 * @param  vo LawTermsVO
	 * @return List
	 * @throws Exception
	 */
	void increseRdcnt(LawTermsVO vo) throws Exception;
	
	/**
	 * 계약용어집 권한조회
	 * @param  vo LawTermsVO, mode String
	 * @return boolean
	 * @throws Exception
	 */
	boolean authLawTerms(String mode, LawTermsVO vo) throws Exception;


}