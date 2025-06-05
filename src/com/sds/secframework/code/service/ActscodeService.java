package com.sds.secframework.code.service;

import java.util.List;

import net.sf.json.JSONArray;

import com.sds.secframework.code.dto.ActscodeVO;

public interface ActscodeService {
	
	/**********************************************************
	 * 감사커뮤니티 공통코드 처리
	 **********************************************************/

	/**
	 * 코드 목록 조회
	 */
	public List listCode(ActscodeVO vo) throws Exception;

	/**
	 * 공통코드 목록 (Ajax처리)
	 */
	public JSONArray listCodeAjax(ActscodeVO vo) throws Exception;

	/**
	 * 공통코드 저장 (등록/수정/삭제 포함)
	 */
	public boolean saveCode(ActscodeVO vo) throws Exception;
	
}
