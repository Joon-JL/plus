/**
 * Project Name : 계약관리시스템
 * File name	: SubjectService.java
 * Description	: 계약분류상세관리 Service interface
 * Author		: 한지훈
 * Date			: 2011. 08. 24
 * Copyright	:
 */
package com.sec.clm.admin.service;

import java.util.List;

import com.sec.clm.admin.dto.SubjectVO;

/**
 * Description	: Service interface
 * Author		: 한지훈
 * Date			: 2011. 08. 24
 */
public interface SubjectService {
	
	/**
	 * 조회
	 * @param vo SubjectVO
	 * @return
	 * @throws Exception
	 */
	List listSubject(SubjectVO vo) throws Exception;
	
	/**
	 * 등록
	 * @param vo SubjectVO
	 * @return String
	 * @throws Exception
	 */
	String insertSubject(SubjectVO vo) throws Exception;
	
	/**
	 * 수정
	 * @param vo SubjectVO
	 * @return int
	 * @throws Exception
	 */
	int modifySubject(SubjectVO vo) throws Exception;
	 
	/**
	 * 삭제(실제 삭제하는 것이 아니고 DEL_YN을 "Y"로 업데이트) 
	 * @param vo SubjectVO
	 * @return int
	 * @throws Exception
	 */
	int deleteSubject(SubjectVO vo) throws Exception;
	
	/**
	 * 상세조회
	 * @param  vo ContDivMngVO
	 * @return List
	 * @throws Exception
	 */
	List detailSubject(SubjectVO vo) throws Exception;
}
