/**
* Project Name : 계약관리시스템
* File Name : NationVO.java
* Description :  국가검색 VO
* Author : dawn
* Date : 2011.10.26
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.draft.dto;

import com.sds.secframework.common.dto.CommonVO;

public class NationVO extends CommonVO {
	
	private String gu;
	
	private String cd;
	private String cd_nm;
	private String cd_abbr_nm;
	private String cd_nm_eng;
	private String cd_abbr_nm_eng;
	private String email;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGu() {
		return gu;
	}
	public void setGu(String gu) {
		this.gu = gu;
	}
	

	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	public String getCd_nm() {
		return cd_nm;
	}
	public void setCd_nm(String cd_nm) {
		this.cd_nm = cd_nm;
	}
	public String getCd_abbr_nm() {
		return cd_abbr_nm;
	}
	public void setCd_abbr_nm(String cd_abbr_nm) {
		this.cd_abbr_nm = cd_abbr_nm;
	}
	public String getCd_nm_eng() {
		return cd_nm_eng;
	}
	public void setCd_nm_eng(String cd_nm_eng) {
		this.cd_nm_eng = cd_nm_eng;
	}
	public String getCd_abbr_nm_eng() {
		return cd_abbr_nm_eng;
	}
	public void setCd_abbr_nm_eng(String cd_abbr_nm_eng) {
		this.cd_abbr_nm_eng = cd_abbr_nm_eng;
	}
	
}
