package com.sds.secframework.auth.dto;

import com.sds.secframework.common.dto.CommonVO;

public class ClassMethodAuthVO extends CommonVO {
	/**
	 * 시스템코드
	 */
	private String sys_cd;

	/**
	 * 클래스명
	 */
	private String class_nm;

	/**
	 * 메소드명
	 */
	private String method_nm;
	
	/**
	 * 권한체크여부
	 */
	private String check_yn;

	/**
	 * 권한
	 */
	private String auth_cd;

	/**
	 * 등록자 EPID
	 */
	private String reg_id;

	/**
	 * 등록자명
	 */
	private String reg_nm;

	/**
	 * 등록자직급
	 */
	private String reg_jikgup_nm;

	/**
	 * 등록자부서
	 */
	private String reg_dept_nm;

	/**
	 * 등록일시
	 */
	private String reg_dt;

	/**
	 * 수정자 EPID
	 */
	private String mod_id;

	/**
	 * 수정자명
	 */
	private String mod_nm;

	/**
	 * 수정자직급
	 */
	private String mod_jikgup_nm;

	/**
	 * 수정자부서
	 */
	private String mod_dept_nm;

	/**
	 * 수정일시
	 */
	private String mod_dt;
	
	/**
	 * 검색타입(전체,Y,N)
	 */
	private String srch_type; 
	
	/**
	 * 클래스 검색
	 */
	private String class_srch_word; 
	
	/**
	 * 메소드 검색
	 */
	private String method_srch_word;

	/**
	 * 테이블 이름
	 */
	private String table_name;
	
	private String[] available_auth_list;
	
	private String[] assigned_auth_list;
	
	private String[] checkbox;
	
	public ClassMethodAuthVO(){
		super();
		//sys_cd = "SHRI";
		srch_type = "ALL";
	}

	public String getSys_cd() {
		return sys_cd;
	}

	public void setSys_cd(String sys_cd) {
		this.sys_cd = sys_cd;
	}

	public String getClass_nm() {
		return class_nm;
	}

	public void setClass_nm(String class_nm) {
		this.class_nm = class_nm;
	}

	public String getMethod_nm() {
		return method_nm;
	}

	public void setMethod_nm(String method_nm) {
		this.method_nm = method_nm;
	}

	public String getAuth_cd() {
		return auth_cd;
	}

	public void setAuth_cd(String auth_cd) {
		this.auth_cd = auth_cd;
	}

	public String getReg_id() {
		return reg_id;
	}

	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}

	public String getReg_nm() {
		return reg_nm;
	}

	public void setReg_nm(String reg_nm) {
		this.reg_nm = reg_nm;
	}

	public String getReg_jikgup_nm() {
		return reg_jikgup_nm;
	}

	public void setReg_jikgup_nm(String reg_jikgup_nm) {
		this.reg_jikgup_nm = reg_jikgup_nm;
	}

	public String getReg_dept_nm() {
		return reg_dept_nm;
	}

	public void setReg_dept_nm(String reg_dept_nm) {
		this.reg_dept_nm = reg_dept_nm;
	}

	public String getReg_dt() {
		return reg_dt;
	}

	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}

	public String getMod_id() {
		return mod_id;
	}

	public void setMod_id(String mod_id) {
		this.mod_id = mod_id;
	}

	public String getMod_nm() {
		return mod_nm;
	}

	public void setMod_nm(String mod_nm) {
		this.mod_nm = mod_nm;
	}

	public String getMod_jikgup_nm() {
		return mod_jikgup_nm;
	}

	public void setMod_jikgup_nm(String mod_jikgup_nm) {
		this.mod_jikgup_nm = mod_jikgup_nm;
	}

	public String getMod_dept_nm() {
		return mod_dept_nm;
	}

	public void setMod_dept_nm(String mod_dept_nm) {
		this.mod_dept_nm = mod_dept_nm;
	}

	public String getMod_dt() {
		return mod_dt;
	}

	public void setMod_dt(String mod_dt) {
		this.mod_dt = mod_dt;
	}
	
	public String getCheck_yn() {
		return check_yn;
	}

	public void setCheck_yn(String check_yn) {
		this.check_yn = check_yn;
	}

	public String getSrch_type() {
		return srch_type;
	}

	public void setSrch_type(String srch_type) {
		this.srch_type = srch_type;
	}

	public String getClass_srch_word() {
		return class_srch_word;
	}

	public void setClass_srch_word(String class_srch_word) {
		this.class_srch_word = class_srch_word;
	}

	public String getMethod_srch_word() {
		return method_srch_word;
	}

	public void setMethod_srch_word(String method_srch_word) {
		this.method_srch_word = method_srch_word;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public String[] getAvailable_auth_list() {
		return available_auth_list;
	}

	public void setAvailable_auth_list(String[] available_auth_list) {
		this.available_auth_list = available_auth_list;
	}

	public String[] getAssigned_auth_list() {
		return assigned_auth_list;
	}

	public void setAssigned_auth_list(String[] assigned_auth_list) {
		this.assigned_auth_list = assigned_auth_list;
	}

	public String[] getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(String[] checkbox) {
		this.checkbox = checkbox;
	}

	
}
