/**
* Project Name : 계약관리시스템
* File Name :ChooseSealPersonForm.java
* Description : 날인담당자선택팝업 Form
* Author : 심주완
* Date : 2011.09.29
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.manage.dto;

import com.sds.secframework.common.dto.CommonForm;

public class ChooseSealPersonForm extends CommonForm {
	
	/**********************************************************************
	 * DB 변수-대상테이블(GAIS시스템연계); 
	 **********************************************************************/
	private String seal_ffmtman_id;
	private String seal_ffmtman_nm;
	private String seal_ffmtman_search_nm;
	private String seal_ffmtman_jikgup_nm;
	private String seal_ffmt_dept_cd;
	private String seal_ffmt_search_dept_cd;
	private String seal_ffmt_dept_nm;
	private String seal_ffmtman_jikgup_cd;
		
	//  날인 담당자 / 증명서류 발급자 구분자
	private String srch_type_gbn;
	private String srch_cntnt_type;
	private String srch_cntnt;	
	    
	public String getSrch_type_gbn() {
		return srch_type_gbn;
	}
	public void setSrch_type_gbn(String srch_type_gbn) {
		this.srch_type_gbn = srch_type_gbn;
	}
	public String getSrch_cntnt_type() {
		return srch_cntnt_type;
	}
	public void setSrch_cntnt_type(String srch_cntnt_type) {
		this.srch_cntnt_type = srch_cntnt_type;
	}
	public String getSrch_cntnt() {
		return srch_cntnt;
	}
	public void setSrch_cntnt(String srch_cntnt) {
		this.srch_cntnt = srch_cntnt;
	}
	public String getSeal_ffmtman_id() {
		return seal_ffmtman_id;
	}
	public void setSeal_ffmtman_id(String seal_ffmtman_id) {
		this.seal_ffmtman_id = seal_ffmtman_id;
	}
	public String getSeal_ffmtman_nm() {
		return seal_ffmtman_nm;
	}
	public void setSeal_ffmtman_nm(String seal_ffmtman_nm) {
		this.seal_ffmtman_nm = seal_ffmtman_nm;
	}
	public String getSeal_ffmtman_search_nm() {
		return seal_ffmtman_search_nm;
	}
	public void setSeal_ffmtman_search_nm(String seal_ffmtman_search_nm) {
		this.seal_ffmtman_search_nm = seal_ffmtman_search_nm;
	}
	public String getSeal_ffmtman_jikgup_nm() {
		return seal_ffmtman_jikgup_nm;
	}
	public void setSeal_ffmtman_jikgup_nm(String seal_ffmtman_jikgup_nm) {
		this.seal_ffmtman_jikgup_nm = seal_ffmtman_jikgup_nm;
	}
	public String getSeal_ffmt_dept_cd() {
		return seal_ffmt_dept_cd;
	}
	public void setSeal_ffmt_dept_cd(String seal_ffmt_dept_cd) {
		this.seal_ffmt_dept_cd = seal_ffmt_dept_cd;
	}
	public String getSeal_ffmt_search_dept_cd() {
		return seal_ffmt_search_dept_cd;
	}
	public void setSeal_ffmt_search_dept_cd(String seal_ffmt_search_dept_cd) {
		this.seal_ffmt_search_dept_cd = seal_ffmt_search_dept_cd;
	}
	public String getSeal_ffmt_dept_nm() {
		return seal_ffmt_dept_nm;
	}
	public void setSeal_ffmt_dept_nm(String seal_ffmt_dept_nm) {
		this.seal_ffmt_dept_nm = seal_ffmt_dept_nm;
	}
	public String getSeal_ffmtman_jikgup_cd() {
		return seal_ffmtman_jikgup_cd;
	}
	public void setSeal_ffmtman_jikgup_cd(String seal_ffmtman_jikgup_cd) {
		this.seal_ffmtman_jikgup_cd = seal_ffmtman_jikgup_cd;
	}
	
}