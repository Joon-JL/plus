/**
 * Project Name : 계약관리시스템
 * File name	: ApprovalPathService.java
 * Description	: 결재라인Path관리 Service interface
 * Author		: 박정훈
 * Date			: 2014. 05. 19
 * Copyright	:
 */
package com.sec.clm.admin.service;

import java.util.List;

import com.sec.clm.admin.dto.ApprovalPathVO;
import com.sec.clm.admin.dto.RoleVO;

/**
 * Description	: Service interface
 * Author		: 박정훈
 * Date			: 2014. 05. 19
 */
public interface ApprovalPathService {
	
	/**
	 * 조회
	 * @param vo ApprovalPathVO
	 * @return
	 * @throws Exception
	 */
	List listApprovalPath(ApprovalPathVO vo) throws Exception;
	
	
	/**
	 * 등록
	 * @param vo ApprovalPathVO
	 * @return String
	 * @throws Exception
	 */
	int insertApprovalPath(ApprovalPathVO vo) throws Exception;
	
	/**
	 * 수정
	 * @param vo ApprovalPathVO
	 * @return int
	 * @throws Exception
	 */
	int modifyApprovalPath(ApprovalPathVO vo) throws Exception;
	 
	/**
	 * 삭제(실제 삭제하는 것이 아니고 USE_YN을 "N"로 업데이트) 
	 * @param vo ApprovalPathVO
	 * @return int
	 * @throws Exception
	 */
	int deleteApprovalPath(ApprovalPathVO vo) throws Exception;
	
	/**
	 * 상세조회
	 * @param  vo ApprovalPathVO
	 * @return List
	 * @throws Exception
	 */
	List detailApprovalPath(ApprovalPathVO vo) throws Exception;
	
	/**
	 * 상세Detail조회
	 * @param  vo ApprovalPathVO
	 * @return List
	 * @throws Exception
	 */
	List detailApprovalPathDetail(ApprovalPathVO vo) throws Exception;
	
	/**
	 * detailApprovalUsers
	 * @param  vo ApprovalPathVO
	 * @return List
	 * @throws Exception
	 */
	List detailApprovalUsers(ApprovalPathVO vo) throws Exception;
	
	/**
	 * contract type 조회
	 * @param  vo ApprovalPathVO
	 * @return List
	 * @throws Exception
	 */
	List listCombolist(ApprovalPathVO vo) throws Exception;
	
	/**
	 * rolelist 조회
	 * @param  vo ApprovalPathVO
	 * @return List
	 * @throws Exception
	 */
	List roleCombolist(ApprovalPathVO vo) throws Exception;
	
	/**
	 * Users상세조회 업데이트
	 * @param  vo ApprovalPathVO
	 * @return 
	 * @throws Exception
	 */
	void UsersInfo(ApprovalPathVO vo) throws Exception;
	
	/**
	 * listApprovalPathExcel 조회
	 * @param  vo ApprovalPathVO
	 * @return List
	 * @throws Exception
	 */
	List listApprovalPathExcel(ApprovalPathVO vo) throws Exception;	

	
}
