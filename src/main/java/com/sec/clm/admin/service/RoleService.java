/**
 * Project Name : 계약관리시스템
 * File name	: RoleService.java
 * Description	: 결재라인Role관리 Service interface
 * Author		: 박정훈
 * Date			: 2014. 05. 19
 * Copyright	:
 */
package com.sec.clm.admin.service;

import java.util.List;

import com.sec.clm.admin.dto.RoleVO;

/**
 * Description	: Service interface
 * Author		: 박정훈
 * Date			: 2014. 05. 19
 */
public interface RoleService {
	
	/**
	 * 조회
	 * @param vo RoleVO
	 * @return
	 * @throws Exception
	 */
	List listRole(RoleVO vo) throws Exception;
	
	
	/**
	 * 등록
	 * @param vo RoleVO
	 * @return String
	 * @throws Exception
	 */
	int insertRole(RoleVO vo) throws Exception;
	
	/**
	 * 수정
	 * @param vo RoleVO
	 * @return int
	 * @throws Exception
	 */
	int modifyRole(RoleVO vo) throws Exception;
	 
	/**
	 * 삭제(실제 삭제하는 것이 아니고 USE_YN을 "N"로 업데이트) 
	 * @param vo RoleVO
	 * @return int
	 * @throws Exception
	 */
	int deleteRole(RoleVO vo) throws Exception;
	
	/**
	 * 상세조회
	 * @param  vo RoleVO
	 * @return List
	 * @throws Exception
	 */
	List detailRole(RoleVO vo) throws Exception;
	
	/**
	 * AssignedUsers상세조회
	 * @param  vo RoleVO
	 * @return List
	 * @throws Exception
	 */
	List detailAssignedUsers(RoleVO vo) throws Exception;
	
	/**
	 * Users상세조회 업데이트
	 * @param  vo RoleVO
	 * @return 
	 * @throws Exception
	 */
	void UsersInfo(RoleVO vo) throws Exception;
	
	/**
	 * reviewAutolist조회
	 * @param  vo RoleVO
	 * @return List
	 * @throws Exception
	 */
	List reviewAutolist(RoleVO vo) throws Exception;
	
	/**
	 * checkApproval Yn 조회
	 * @param  vo RoleVO
	 * @return int
	 * @throws Exception
	 */
	int checkApprovalYn(RoleVO vo) throws Exception;
	
	
}
