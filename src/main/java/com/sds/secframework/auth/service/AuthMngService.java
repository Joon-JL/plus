package com.sds.secframework.auth.service;

import java.util.HashMap;

import net.sf.json.JSONArray;

import com.sds.secframework.auth.dto.AuthVO;

public interface AuthMngService {

	/**
	 * 권한 목록 조회
	 * @param sys_cd 시스템 코드
	 * @return 관리자용 메뉴리스트
	 * @throws Exception
	 */
	JSONArray listAuth(HashMap hm) throws Exception;

	/**
	 * 권한 내역 등록
	 * @param vo 게시판관리 Value Object
	 * @return 
	 * @throws Exception
	 */
	int insertAuth(AuthVO vo) throws Exception;

	/**
	 * 권한 내역 수정
	 * @param vo 게시판관리 Value Object
	 * @return 
	 * @throws Exception
	 */
	int modifyAuth(AuthVO vo) throws Exception;

	/**
	 * 권한 내역 삭제
	 * @param vo 게시판관리 Value Object
	 * @return 
	 * @throws Exception
	 */
	void deleteAuth(AuthVO vo) throws Exception;
}
