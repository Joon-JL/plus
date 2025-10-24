package com.sds.secframework.auth.service;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import com.sds.secframework.auth.dto.AuthVO;

public interface AuthMappingService {


	/**
	 * 역할목록조회
	 * @param HashMap
	 * @return 역할 목록
	 * @throws Exception
	 */
	JSONArray listRole(HashMap hm) throws Exception;

	/**
	 * 사용자목록조회
	 * @param HashMap
	 * @return 역할 목록
	 * @throws Exception
	 */
	JSONArray listUser(HashMap hm) throws Exception;

	/**
	 * 역할-사용자 목록조회
	 * @param AuthVO 권한관리 Value Object
	 * @return 역할-사용자 목록
	 * @throws Exception
	 */
	JSONArray listRoleUser(AuthVO vo) throws Exception;

	/**
	 * 역할-사용자 등록
	 * @param AuthVO 권한관리 Value Object
	 * @return 등록결과
	 * @throws Exception
	 */
	void insertRoleUser(AuthVO vo) throws Exception;

	/**
	 * 역할별 사용자 삭제
	 * @param AuthVO 권한관리 Value Object
	 * @return 삭제결과
	 * @throws Exception
	 */
	void deleteRoleUser(AuthVO vo) throws Exception;

	/**
	 * 권한목록조회
	 * @param HashMap
	 * @return 권한 목록
	 * @throws Exception
	 */
	JSONArray listAuth(HashMap hm) throws Exception;
	
	/**
	 * 역할-권한 목록조회
	 * @param AuthVO 권한관리 Value Object
	 * @return 역할-사용자 목록
	 * @throws Exception
	 */
	JSONArray listRoleAuth(AuthVO vo) throws Exception;

	/**
	 * 역할-권한 등록
	 * @param AuthVO 권한관리 Value Object
	 * @return 등록결과
	 * @throws Exception
	 */
	void insertRoleAuth(AuthVO vo) throws Exception;

	/**
	 * 역할별 권한 삭제
	 * @param AuthVO 권한관리 Value Object
	 * @return 삭제결과
	 * @throws Exception
	 */
	void deleteRoleAuth(AuthVO vo) throws Exception;
	
	/**
	 * 메뉴-권한 목록조회
	 * @param AuthVO 권한관리 Value Object
	 * @return 역할-사용자 목록
	 * @throws Exception
	 */
	JSONArray listMenuAuth(HashMap vo) throws Exception;

	/**
	 * 메뉴-권한 등록
	 * @param AuthVO 권한관리 Value Object
	 * @return 등록결과
	 * @throws Exception
	 */
	void insertMenuAuth(AuthVO vo) throws Exception;

	/**
	 * 메뉴 Access Control 조회
	 * @param AuthVO 권한관리 Value Object
	 * @return 등록결과
	 * @throws Exception
	 */
	String getAccessControl(AuthVO vo) throws Exception;
	
	/**
	 * 메뉴 Access Control 등록
	 * @param AuthVO 권한관리 Value Object
	 * @return 등록결과
	 * @throws Exception
	 */
	void insertAccessControl(AuthVO vo) throws Exception;

	/**
	 * 메뉴별 권한 삭제
	 * @param AuthVO 권한관리 Value Object
	 * @return 삭제결과
	 * @throws Exception
	 */
	void deleteMenuAuth(AuthVO vo) throws Exception;

	/**
	 * 메소드-권한 목록조회
	 * @param AuthVO 권한관리 Value Object
	 * @return 역할-사용자 목록
	 * @throws Exception
	 */
	JSONArray listMethodAuth(HashMap vo) throws Exception;

	/**
	 * 메소드-권한 등록
	 * @param AuthVO 권한관리 Value Object
	 * @return 등록결과
	 * @throws Exception
	 */
	void insertMethodAuth(AuthVO vo) throws Exception;

	/**
	 * 페이지 목록조회
	 * @param AuthVO 권한관리 Value Object
	 * @return 역할-사용자 목록
	 * @throws Exception
	 */
	JSONArray listPage(AuthVO vo) throws Exception;

	/**
	 * 페이지-권한 목록조회
	 * @param AuthVO 권한관리 Value Object
	 * @return 역할-사용자 목록
	 * @throws Exception
	 */
	JSONArray listPageAuth(AuthVO vo) throws Exception;

	/**
	 * 페이지-권한 등록
	 * @param AuthVO 권한관리 Value Object
	 * @return 등록결과
	 * @throws Exception
	 */
	void insertPageAuth(AuthVO vo) throws Exception;

	/**
	 * 페이지 권한 삭제
	 * @param AuthVO 권한관리 Value Object
	 * @return 삭제결과
	 * @throws Exception
	 */
	void deletePageAuth(AuthVO vo) throws Exception;
	
	/**
	 * 사별 메뉴-권한 목록조회
	 * @param AuthVO 권한관리 Value Object
	 * @return 역할-사용자 목록
	 * @throws Exception
	 */
	JSONArray listCompMenuAuth(HashMap<String, String> vo) throws Exception;

	
	
	/**
	 * 사별 메뉴-권한 등록
	 * @param AuthVO 권한관리 Value Object
	 * @return 등록결과
	 * @throws Exception
	 */
	void insertCompMenuAuth(AuthVO vo) throws Exception;
	
	/**
	 * 사용자목록조회
	 * @param HashMap
	 * @return 역할 목록
	 * @throws Exception
	 */
	JSONArray listUserByRole(HashMap hm) throws Exception;
	
	/**
	 * 관리회사 등록
	 * @param AuthVO 권한관리 Value Object
	 * @return 등록결과
	 * @throws Exception
	 */
	void insertMngComp(AuthVO vo) throws Exception;
	
	/**
	 * 관리회사 목록조회
	 * @param AuthVO 권한관리 Value Object
	 * @return 역할-사용자 목록
	 * @throws Exception
	 */
	JSONArray listMngComp(AuthVO vo) throws Exception;
}
