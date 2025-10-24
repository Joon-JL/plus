package com.sds.secframework.dept.service;

import net.sf.json.JSONArray;

import com.sds.secframework.dept.dto.DeptVO;

/**
 * 부서정보
 * @author Hyunseo Kum
 */
public interface DeptService {

	/**
	 * 부서 목록 조회(Tree형식)
	 * @param dept_nm 부서명
	 * @return 부서리스트 (Tree형식)
	 * @throws Exception
	 */	
	JSONArray listDeptTree(DeptVO vo) throws Exception;
	
	/**
	 * 하위부서조회
	 * @param dept_cd 상위부서코드
	 * @return 부서리스트
	 * @throws Exception
	 */
	JSONArray listChildDept(DeptVO vo) throws Exception;
}