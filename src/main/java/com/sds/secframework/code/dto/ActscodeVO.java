package com.sds.secframework.code.dto;

import java.math.BigDecimal;

import com.sds.secframework.common.dto.CommonVO;

public class ActscodeVO extends CommonVO {
	/**********************************************************************
	 * 공통코드 DB
	 **********************************************************************/
	private String cd;				// 코드
	private String cd_nm;			// 코드명
	private String cd_nm_eng;		// 코드영문명
	private int cd_order;			// 코드 순서
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
	public String getCd_nm() {
		return cd_nm;
	}
	public void setCd_nm(String cd_nm) {
		this.cd_nm = cd_nm;
	}
	public String getCd_nm_eng() {
		return cd_nm_eng;
	}
	public void setCd_nm_eng(String cd_nm_eng) {
		this.cd_nm_eng = cd_nm_eng;
	}
	public int getCd_order() {
		return cd_order;
	}
	public void setCd_order(int cd_order) {
		this.cd_order = cd_order;
	}

	
}