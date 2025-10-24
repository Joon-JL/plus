/**
* Project Name : 계약관리시스템
* File Name : NationForm.java
* Description : 국가검색 Form
* Author : dawn
* Date : 2011.10.26
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.draft.dto;

import com.sds.secframework.common.dto.CommonForm;

public class NationForm extends CommonForm {
	
	private String gu;
	private String tabVal;
	private String cnt;
	
	private String cd;
	private String cd_nm;
	private String cd_abbr_nm;
	private String cd_nm_eng;
	private String cd_abbr_nm_eng;
	private String email;
	
	private String isSearch;
	private String isModify;
	
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
	public String getTabVal() {
		return tabVal;
	}
	public void setTabVal(String tabVal) {
		this.tabVal = tabVal;
	}
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
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
	public String getIsSearch() {
		return isSearch;
	}
	public void setIsSearch(String isSearch) {
		this.isSearch = isSearch;
	}
	public String getIsModify() {
		return isModify;
	}
	public void setIsModify(String isModify) {
		this.isModify = isModify;
	}
	
	
}
