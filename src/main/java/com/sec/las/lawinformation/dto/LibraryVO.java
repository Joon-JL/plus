/**
 * Project Name : 법무시스템 이식
 * File name	: LibraryForm.java
 * Description	: 국내/해외계약서 Library Data Form(Model), 서비스 로직-DB 연결
 * Author		: 신승완
 * Date			: 2011. 08. 04
 * Copyright	:   
 */
package com.sec.las.lawinformation.dto;

import com.sds.secframework.common.dto.CommonVO;

/**
 * Description	: 국내/해외계약서 Library Data Form(Model), 서비스 로직-DB 연결
 * Author 		: 신승완
 * Date			: 2011. 08. 04
 */
public class LibraryVO extends CommonVO {
	
	
	/** 정보 구분 - 공통코드(시스템 : LAS, 그룹코드 : C004) "C00403", "C00404" 만 사용 */
	private String info_gbn;
	/** 일련번호 */
	private int seqno;
	/** 라이브러리 구분 - 정보 구분에 따라 "C00403", "C00404" 사용 */
	private String lib_gbn;
	/** 제목 */
	private String title;
	/** 내용 */
	private String cont;
	/**나모웹에디터 타입 - 내용에 사용 */
	private String body_mime;
	/** 용도*/
	private String usage;
	/** 조회수 - 기본 값 : 0 */
	private int rdcnt;
	/** 조회 권한 - 공통코드(시스템 : LAS, 그룹코드 : C005 ) */
	private String rd_auth;
	/** 등록 일시 - 기본 값 : SYSDATE */
	private String reg_dt;
	/** 등록자 ID */
	private String reg_id;
	/** 등록자 명 */
	private String reg_nm;
	/** 등록 부서 */
	private String reg_dept;
	/** 등록 부서 명 */
	private String reg_dept_nm;
	/** 수정 일시 */
	private String mod_dt;
	/** 수정자 ID */
	private String mod_id;
	/** 수정자 명 */
	private String mod_nm;
	/** 삭제 여부 - 기본 값 : "N" */
	private String del_yn;
	/** 삭제 일시 */
	private String del_dt;
	/** 삭제자 ID */
	private String del_id;
	/** 삭제자 명 */
	private String del_nm;
	
	
	/** 검색 시작 날짜 */
	private String srch_start_dt;
	/** 검색 마지막 날짜 */
	private String srch_end_dt;
	/** 검색 내용 */
	private String srch_name;
	/** 검색 콤보 */
	private String srch_combo;
	/** 검색 계약서 분류 **/
	private String srch_lib_gbn;
	
	/**
	 * getter/setter method
	 */
	
	public String getInfo_gbn() {
		return info_gbn;
	}
	public String getBody_mime() {
		return body_mime;
	}
	public void setBody_mime(String body_mime) {
		this.body_mime = body_mime;
	}
	public String getSrch_start_dt() {
		return srch_start_dt;
	}
	public void setSrch_start_dt(String srch_start_dt) {
		this.srch_start_dt = srch_start_dt;
	}
	public String getSrch_end_dt() {
		return srch_end_dt;
	}
	public void setSrch_end_dt(String srch_end_dt) {
		this.srch_end_dt = srch_end_dt;
	}
	public void setInfo_gbn(String info_gbn) {
		this.info_gbn = info_gbn;
	}
	public int getSeqno() {
		return seqno;
	}
	public void setSeqno(int seqno) {
		this.seqno = seqno;
	}
	public String getLib_gbn() {
		return lib_gbn;
	}
	public void setLib_gbn(String lib_gbn) {
		this.lib_gbn = lib_gbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	public int getRdcnt() {
		return rdcnt;
	}
	public void setRdcnt(int rdcnt) {
		this.rdcnt = rdcnt;
	}
	public String getRd_auth() {
		return rd_auth;
	}
	public void setRd_auth(String rd_auth) {
		this.rd_auth = rd_auth;
	}
	public String getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
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
	public String getReg_dept() {
		return reg_dept;
	}
	public void setReg_dept(String reg_dept) {
		this.reg_dept = reg_dept;
	}
	public String getReg_dept_nm() {
		return reg_dept_nm;
	}
	public void setReg_dept_nm(String reg_dept_nm) {
		this.reg_dept_nm = reg_dept_nm;
	}
	public String getMod_dt() {
		return mod_dt;
	}
	public void setMod_dt(String mod_dt) {
		this.mod_dt = mod_dt;
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
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	public String getDel_dt() {
		return del_dt;
	}
	public void setDel_dt(String del_dt) {
		this.del_dt = del_dt;
	}
	public String getDel_id() {
		return del_id;
	}
	public void setDel_id(String del_id) {
		this.del_id = del_id;
	}
	public String getDel_nm() {
		return del_nm;
	}
	public void setDel_nm(String del_nm) {
		this.del_nm = del_nm;
	}
	public String getSrch_name() {
		return srch_name;
	}
	public void setSrch_name(String srch_name) {
		this.srch_name = srch_name;
	}
	public String getSrch_combo() {
		return srch_combo;
	}
	public void setSrch_combo(String srch_combo) {
		this.srch_combo = srch_combo;
	}
	public String getSrch_lib_gbn() {
		return srch_lib_gbn;
	}
	public void setSrch_lib_gbn(String srch_lib_gbn) {
		this.srch_lib_gbn = srch_lib_gbn;
	}	
}