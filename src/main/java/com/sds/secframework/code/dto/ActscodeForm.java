package com.sds.secframework.code.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.math.BigDecimal;

public class ActscodeForm extends CommonForm {
	/**********************************************************************
	 * 공통코드드 DB
	 **********************************************************************/
	private String cd;				// 코드
	private String grp_cd;			// 그룹코드 (상위코드)
	private String[] cd_arr;		// 코드 배열
	private String[] cd_nm_arr;		// 코드명 배열
	private String[] cd_nm_eng_arr;	// 코드영문명 배열
	
	/**********************************************************************
	 * DB 이외 추가
	 **********************************************************************/


	/**********************************************************************
	 * 검색조건
	 **********************************************************************/
	private String srch_sys_cd;		// 코드 검색용 시스템 코드
	
	/**********************************************************************
	 * Getters and Setters Method
	 **********************************************************************/
	public String getGrp_cd() {
		return grp_cd;
	}
	public void setGrp_cd(String grp_cd) {
		this.grp_cd = grp_cd;
	}
	public String getSrch_sys_cd() {
		return srch_sys_cd;
	}
	public void setSrch_sys_cd(String srch_sys_cd) {
		this.srch_sys_cd = srch_sys_cd;
	}
	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	public String[] getCd_arr() {
		return cd_arr;
	}
	public void setCd_arr(String[] cd_arr) {
		this.cd_arr = cd_arr;
	}
	public String[] getCd_nm_arr() {
		return cd_nm_arr;
	}
	public void setCd_nm_arr(String[] cd_nm_arr) {
		this.cd_nm_arr = cd_nm_arr;
	}
	public String[] getCd_nm_eng_arr() {
		return cd_nm_eng_arr;
	}
	public void setCd_nm_eng_arr(String[] cd_nm_eng_arr) {
		this.cd_nm_eng_arr = cd_nm_eng_arr;
	}

	
}