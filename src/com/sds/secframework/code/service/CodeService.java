package com.sds.secframework.code.service;

import java.util.HashMap;

import net.sf.json.JSONArray;

import com.sds.secframework.code.dto.CodeVO;

public interface CodeService {

	/**********************************************************
	 * 공통코드
	 **********************************************************/

	// 그룹코드 목록조회
	JSONArray listGrpCode(CodeVO vo) throws Exception;

	// 공통코드 목록조회	
	JSONArray listCode(CodeVO vo) throws Exception;

	// 공통코드(콤보)	
	JSONArray listCodeCombo(CodeVO vo) throws Exception;
	
	// 그룹코드 신규등록
	int insertGrpCode(CodeVO vo) throws Exception;

	// 공통코드 신규등록
	int insertCode(CodeVO vo) throws Exception;
	
	// 그룹코드 수정
	int modifyGrpCode(CodeVO vo) throws Exception;

	// 공통코드 수정
	int modifyCode(CodeVO vo) throws Exception;

	// 공통코드 삭제
	int deleteGrpCode(CodeVO vo) throws Exception;

	// 공통코드 삭제
	int deleteCode(CodeVO vo) throws Exception;

	// 그룹코드 존재여부
	String existsGrpCode(HashMap hm) throws Exception;

	// 공통코드 존재여부
	String existsCode(HashMap hm) throws Exception;

	/**
	 * 대분류에 의한 공통 코드 리스트를 반환한다
	 * @param GRP_CD 그룹코드
	 * @param SELECTED 디폴트 선택 코드
	 * @param LOCALE 한글영어 (ko : 한글, en : 영어)
	 * @param ABBR 약어/Full Name (A:약어, F:풀네임)
	 * @param DEL_YN 삭제여부(N : 삭제아닌 것들만, Y : 전부다)
	 * @param TYPE 최상위 표지구분 (S : 선택, T : 전체, "" : NULL)
	 */
	String getCdComboByGrpCd(HashMap map) throws Exception;
}
