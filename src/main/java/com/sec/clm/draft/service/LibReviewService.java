/**
 * Project Name : 계약서Library 유형별 조회
 * File name	: LibRevieweService.java
 * Description	: 계약서Library 유형별 조회 Service interface
 * Author		: 신승완
 * Date			: 2011. 08. 29
 * Copyright	:
 */
package com.sec.clm.draft.service;

import java.util.HashMap;
import java.util.List;

import com.sec.clm.draft.dto.LibReviewVO;

/**
 * Description	: 국내/해외계약서 Service interface
 * Author 		: 신승완
 * Date			: 2011. 08. 04
 */
public interface LibReviewService {

	/**
	 * 계약서Library 유형별  전체 목록을 조회한다.
	 * @param  vo LibraryVO
	 * @return List
	 * @throws Exception
	 */
	List listLibReview1(LibReviewVO vo) throws Exception;
	
	/**
	 * 계약서Library 유형별  전체 목록을 조회하기위한 유형을 조회한다.
	 * @param  vo LibraryVO
	 * @return List
	 * @throws Exception
	 */
	List listLibReviewType(LibReviewVO vo) throws Exception;
	
	/**
	 * 계약서Library 유형별  목록을 조회한다.
	 * @param  vo LibraryVO
	 * @return List
	 * @throws Exception
	 */
	List listLibReview(LibReviewVO vo) throws Exception;

	/**
	 * 계약서Library를 상세조회한다.
	 * @param  vo LibraryVO
	 * @return List
	 * @throws Exception
	 */
	List getLibReview(LibReviewVO vo) throws Exception;
	
	/**
	 * 국내/해외계약서 조회수 증가
	 * @param  vo LibraryVO
	 * @return List
	 * @throws Exception
	 */
	void increseRdcnt(LibReviewVO vo) throws Exception;

}