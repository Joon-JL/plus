/**
 * 
 * Project Name : 계약관리시스템 구축
 * File name	: CLMSCommonController.java
 * Description	: 공통코드관련 Controller
 * Author		: SDS
 * Date			: 2011. 08. 04
 * Copyright	: 
 */
package com.sec.common.clmscom.service;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import com.sec.common.clmscom.dto.CLMSCommonVO;
import com.sec.common.clmscom.dto.OrgnzVO;

/**
 * 
 * Description	: 공통코드관련 Interface
 * Author 		: SDS
 * Date			: 2011. 08. 04
 * 
 */
public interface CLMSCommonService {
	
	/**
	 * 대분류에 의한 공통 코드 리스트를 반환한다
	 * @param GRP_CD 그룹코드
	 * @param SELECTED 디폴트 선택 코드
	 * @param LOCALE 한글영어 (ko : 한글, en : 영어)
	 * @param ABBR 약어/Full Name (A:약어, F:풀네임)
	 * @param DEL_YN 삭제여부(N : 삭제아닌 것들만, Y : 전부다)
	 * @param TYPE 최상위 표지구분 (S : 선택, T : 전체, "" : NULL)
	 * @return    Select Box의 <OPtion> ( <option value="code">code_nm_s</option><optio...)
	 */
	 public String listComCdByGrpCd(HashMap map) throws Exception;

	/**
	 * 대분류에 의한 공통 코드 리스트를 반환한다
	 * 체크박스 타입
	 * @param GRP_CD 그룹코드
	 * @param SELECTED 디폴트 선택 코드
	 * @param LOCALE 한글영어 (ko : 한글, en : 영어)
	 * @param ABBR 약어/Full Name (A:약어, F:풀네임)
	 * @param DEL_YN 삭제여부(N : 삭제아닌 것들만, Y : 전부다)
	 * @param TYPE 최상위 표지구분 (S : 선택, T : 전체, "" : NULL)
	 * @return    Select Box의 <OPtion> ( <option value="code">code_nm_s</option><optio...)
	 */
	 public String checkBoxComCdByGrpCd(HashMap map) throws Exception;
		 
	/**
	 * 대분류에 의한 공통 코드 리스트를 반환한다.(List 반환)
	 * @param SYS_CD 시스템코드
	 * @param GRP_CD 그룹코드
	 * @param DEL_YN 삭제여부(N : 삭제아닌 것들만, Y : 전부다)
	 */	 
	 public List listComCdByGrpCd2(CLMSCommonVO vo) throws Exception;
	 
	 
	 /**
	  * 테이블에서 해당 코드를 가져와서 Select Box의 <OPtion>을 채운다.
	  *
	  * @param GBN   구분(CONTRACTTYPE:계약유형,  )
	  * @param UP_CD 상위코드
	  * @param SELECTED 디폴트 선택 코드
	  * @param DEL_YN 삭제여부(N:삭제아닌 것들만, Y:전부다)
	  * @param LOCALE 한글영어(ko:한,en:영)
	  * @param ABBR 약어/Full Name(A:약어,F:풀네임)
	  * @param TYPE 최상위 표지구분 (S : 선택, T : 전체, N : 사용하지 않음, "" : NULL)
	  * @return Select Box의 <OPtion> ( <option value="code">code_nm_s</option><optio...)
	  * @exception Exception
	  */
	public String getComboHTML(HashMap hm) throws Exception;
	
	 /**
	  * 테이블에서 해당 코드를 가져와서 Select Box의 <OPtion>을 채운다.(법무담당자용)
	  * @param LOCALE 한글영어(ko:한,en:영)
	  * @param SELECTED 디폴트 선택 코드
	  * @param UP_ORGNZ_CD 상위조직코드
	  * @param TYPE 최상위 표지구분 (S : 선택, T : 전체, N : 사용하지 않음, "" : NULL)
	  * @return Select Box의 <OPtion> ( <option value="code">code_nm_s</option><optio...)
	  * @exception Exception
	  */
	public String getUnitOrgnzComboHTML(HashMap hm) throws Exception;
	
	 /**
	  * 테이블에서 해당 코드를 가져와서 Select Box의 <OPtion>을 채운다.(법무담당자용)
	  * @param SYS_CD 시스템코드 
	  * @param LOCALE 한글영어(ko:한,en:영)
	  * @param SELECTED 디폴트 선택 코드
	  * @param BLNGT_ORGNZ 소속조직코드
	  * @param TYPE 최상위 표지구분 (S : 선택, T : 전체, N : 사용하지 않음, "" : NULL)
	  * @return Select Box의 <OPtion> ( <option value="code">code_nm_s</option><optio...)
	  * @exception Exception
	  */
	public String getLasPersonComboHTML(HashMap hm) throws Exception;	
	
	/**
	 * 총괄 리스트를 가져와서 Select Box의 <Option>을 채운다.
	 * @param SELECTED 디폴트 선택 코드
	 * @param LOCALE 한글영어(ko:한,en:영)
	 * @param TYPE 최상위 표지구분 (S : 선택, T : 전체, N : 사용하지 않음, "" : NULL)
	 * @param hm
	 * @return
	 * @throws Exception
	 */
	public String getLasEpsuborgComboHTML(HashMap hm) throws Exception;
	
	/**
	 * 부서 리스트를 반환한다 (트리구조)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listDeptTree(CLMSCommonVO vo) throws Exception;
	
	/**
	 * 본인이 속한 부서 및 검색된 부서의 트리리스트를 반환한다. (수직계층만)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List userDeptTree(CLMSCommonVO vo) throws Exception;
	
	/**
	 * 하위부서 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	JSONArray listDeptTreeAjax(CLMSCommonVO vo) throws Exception;
	
	/**
	 * 일부이름으로 부서명을 조회한다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List searchDept(CLMSCommonVO vo) throws Exception;
	
	/**
	 * TB_COM_CD 정보조회
	 * @param hm
	 * @return
	 * @throws Exception
	 */
	public JSONArray listTbComCd(HashMap hm) throws Exception;
	
	/**
	 * CLM시스템의 현재, 페이지에서 사용가능한 수정, 조회 권한이 있는지 체크한다.
	 * @param hm 조회조건 
	 * @param chkSM(S:조회권한여부(Y/N),  M:수정권한여부(Y/N))
	 */
	public String getTrCheck(HashMap hm, String chkSM) throws Exception;
	
	/**
	 * LAS시스템의 현재, 페이지에서 사용가능한 수정, 조회 권한이 있는지 체크한다.
	 * @param hm 조회조건 
	 * @param chkSM(S:조회권한여부(Y/N),  M:수정권한여부(Y/N))
	 */
	public String getTrCheck2(HashMap hm, String chkSM) throws Exception;
	
	/**
	 * 단위조직 검색
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List searchOrgnz(OrgnzVO vo) throws Exception;	
	
	/**
	 * 법무담당자,관리자 alert리스트 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public HashMap searchAdminAlert(CLMSCommonVO vo, String role) throws Exception;
	
	/**
	 * 사업부담당자,관리자 alert리스트 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public HashMap getApprovalCnt(CLMSCommonVO vo) throws Exception;
	
	/**
	 * 필수 항목에 대한 체크 리스트를 가지고 온다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List checkList(CLMSCommonVO vo) throws Exception;
	
	
	/**
	 * 15개 항목에 대해서 필수 항목을 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int modifyCheckItem(CLMSCommonVO vo) throws Exception;
}
