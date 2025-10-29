/**
 * Project Name : 계약관리시스템
 * File name	: TypeService.java
 * Description	: 계약유형관리 Service interface
 * Author		: 한지훈
 * Date			: 2011. 08. 24
 * Copyright	:
 */
package com.sec.clm.admin.service;

import java.util.List;

import com.sec.clm.admin.dto.TypeVO;

/**
 * Description	: Service interface
 * Author		: 한지훈
 * Date			: 2011. 08. 24
 */
public interface TypeService {
	
	/**
	 * 조회
	 * @param vo TypeVO
	 * @return
	 * @throws Exception
	 */
	List listType(TypeVO vo) throws Exception;
	
	/**
	 * Item 조회
	 * @param vo TypeVO
	 * @return
	 * @throws Exception
	 */
	List listTypeItem(TypeVO vo) throws Exception;
	
	/**
	 * Item 적용 목록 조회
	 * @param vo TypeVO
	 * @return
	 * @throws Exception
	 */
	List listApplyItem(TypeVO vo) throws Exception;
	
	/**
	 * 등록
	 * @param vo TypeVO
	 * @return String
	 * @throws Exception
	 */
	String insertType(TypeVO vo) throws Exception;
	
	/**
	 * 수정
	 * @param vo TypeVO
	 * @return int
	 * @throws Exception
	 */
	int modifyType(TypeVO vo) throws Exception;
	 
	/**
	 * 삭제(실제 삭제하는 것이 아니고 USE_YN을 "N"로 업데이트) 
	 * @param vo TypeVO
	 * @return int
	 * @throws Exception
	 */
	int deleteType(TypeVO vo) throws Exception;
	
	/**
	 * 상세조회
	 * @param  vo ContDivMngVO
	 * @return List
	 * @throws Exception
	 */
	List detailType(TypeVO vo) throws Exception;
}
