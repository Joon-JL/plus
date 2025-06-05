/**
 * Project Name : 법무시스템 전자검토자
 * File name	: ElecReviewerService.java
 * Description	: 법무시스템 전자검토자
 * Author		: 제이남
 * Date			: 2013.06.19
 * Copyright	:
 */
package com.sec.las.elecReviewer.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.sec.las.elecReviewer.dto.ElecReviewerVO;


/**
 * Description	: 법무시스템 전자검토자
 * Author 		: 제이남
 * Date			: 2013.06.19
 */
public interface ElecReviewerService {
	/**
	 * 전자검토자 업무이관  목록조회
	 * @param  vo ElecReviewerVO
	 * @return List
	 * @throws Exception
	 */
	public List listChangeReviewer(ElecReviewerVO vo) throws Exception;
	
	/**
	 * 전자검토자 목록을 조회한다.
	 * @param  vo ElecReviewerVO
	 * @return List
	 * @throws Exception
	 */
	public List searchElecReviewer(ElecReviewerVO vo) throws Exception;
	
	
	/**
	 * 전자검토 목록 조회 (임시담당자 지정시 사용)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List searchElecReviewerTmp(ElecReviewerVO vo) throws Exception;
	
	/**
	 * 전자검토자 임시 담당자 지정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int appointTmpElecReviewer (ElecReviewerVO vo, HttpSession session) throws Exception;
	
	
	/**
	 * 담당자 원복
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int returnOrgElecReviewer (ElecReviewerVO vo, HttpSession session) throws Exception;
}