package com.sds.secframework.auth.service;

import java.util.HashMap;

import net.sf.json.JSONArray;

import com.sds.secframework.auth.dto.AuthVO;

public interface RoleMngService {

	/**
	 * 역활 목록 조회
	 * @param sys_cd 시스템 코드
	 * @return 관리자용 메뉴리스트
	 * @throws Exception
	 */
	JSONArray listRole(HashMap hm) throws Exception;

	/**
	 * 역활 내역 등록
	 * @param vo 게시판관리 Value Object
	 * @return 
	 * @throws Exception
	 */
	int insertRole(AuthVO vo) throws Exception;

	/**
	 * 역활 내역 수정
	 * @param vo 게시판관리 Value Object
	 * @return 
	 * @throws Exception
	 */
	int modifyRole(AuthVO vo) throws Exception;

	/**
	 * 역활 내역 삭제
	 * @param vo 게시판관리 Value Object
	 * @return 
	 * @throws Exception
	 */
	void deleteRole(AuthVO vo) throws Exception;
}
