package com.sds.secframework.user.service;

import java.util.List;

import com.sds.secframework.user.dto.UserSortingManagerVO;

public interface UserSortingManagerService {
	
	
	
	/**
	 * 사용자 sorting정보 조회
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	List listSortingUser(UserSortingManagerVO vo) throws Exception;
	
	/**
	 *  사용자 sorting정보 저장
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	boolean saveSortingUser(UserSortingManagerVO vo) throws Exception;
}
