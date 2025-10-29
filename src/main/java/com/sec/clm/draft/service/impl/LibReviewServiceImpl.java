/**
 * Project Name : 계약서Library 유형별 조회
 * File name	: LibRevieweService.java
 * Description	: 계약서Library 유형별 조회 Service impl
 * Author		: 신승완
 * Date			: 2011. 08. 29
 * Copyright	:
 */
package com.sec.clm.draft.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import anyframe.core.idgen.IIdGenerationService;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sec.common.clmsfile.dto.ClmsFileVO;
import com.sec.clm.draft.dto.LibReviewVO;
import com.sec.clm.draft.service.LibReviewService;

/**
 * Description	: 국내/해외계약서 Service impl(concrete class)
 * Author 		: 신승완
 * Date			: 2011. 08. 04
 */
public class LibReviewServiceImpl extends CommonServiceImpl implements LibReviewService {

	/**
	 * 계약서Library 유형별  전체 목록을 조회한다.
	 * @param  vo LibraryVO
	 * @return List
	 * @throws Exception
	 */
	public List listLibReview1(LibReviewVO vo) throws Exception {
		return commonDAO.list("clm.draft.listLibReview1", vo);
	}
	
	/**
	 * 계약서Library 유형별  전체 목록을 조회하기위한 유형을 조회한다.
	 * @param  vo LibraryVO
	 * @return List
	 * @throws Exception
	 */
	public List listLibReviewType(LibReviewVO vo) throws Exception {
		return commonDAO.list("clm.draft.listLibReviewType", vo);
	}
	
	/**
	 * 계약서Library 유형별  목록을 조회한다.
	 * @param  vo LibraryVO
	 * @return List
	 * @throws Exception
	 */
	public List listLibReview(LibReviewVO vo) throws Exception {
		return commonDAO.list("clm.draft.listLibReview", vo);
	}

	/**
	 * 계약서Library를 상세조회한다.
	 * @param  vo LibraryVO
	 * @return List
	 * @throws Exception
	 */
	public List getLibReview(LibReviewVO vo) throws Exception {
		return commonDAO.list("clm.draft.getLibReview", vo);
	}
	
	/**
	 * 국내/해외계약서 조회수 증가
	 * @param  vo LibraryVO
	 * @return List
	 * @throws Exception
	 */
	public void increseRdcnt(LibReviewVO vo) throws Exception {
		commonDAO.modify("clm.draft.increseRdcnt", vo);
	}

}