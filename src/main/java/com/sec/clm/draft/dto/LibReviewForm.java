/**
 * Project Name : 법무시스템 이식
 * File name	: LibraryForm.java
 * Description	: 국내/해외계약서 Library Data Form(Model), 화면-서비스 로직 연결
 * Author		: 신승완
 * Date			: 2011. 08. 04
 * Copyright	:   
 */
package com.sec.clm.draft.dto;

import com.sds.secframework.common.dto.CommonForm;

/**
 * Description	: 유형별조회 Data Form(Model), 화면-서비스 로직 연결
 * Author 		: 신승완
 * Date			: 2011. 08. 29
 */
public class LibReviewForm extends CommonForm {
	
	/** 라이브러리_번호 : required */
	private int lib_no;
	/** 라이브러리_구분 : required - 공통코드 (시스템 : CLM, 그룹코드 : C035) */
	private String lib_gbn;
	/** 지역_구분 : required - 공통코드 (시스템 : CLM, 그룹코드 : C018)*/
	private String region_gbn;
	/** 비즈니스_분류  - 계약유형코드 (상위코드 : T01)*/
	private String biz_clsfcn;
	/** 단계_분류 - 계약유형코드 (상위코드 : T02)*/
	private String depth_clsfcn;
	/** 체결목적_대분류  - 계약유형코드 (상위코드 : T03)*/
	private String cnclsnpurps_bigclsfcn;
	/** 체결목적_중분류 - 계약유형코드 (상위코드 : 체결목적_대분류)*/
	private String cnclsnpurps_midclsfcn;
	/** 제목 : required */
	private String title;
	/** 내용 */
	private String cont;
	/** 조회수 : required */
	private int rdcnt;

	/** 등록 일시 : required */
	private String reg_dt;
	/** 등록자_ID : required */
	private String reg_id;
	/** 등록자_명 : required */
	private String reg_nm;
	/** 수정_일시 */
	private String mod_dt;
	/** 수정자_ID */
	private String mod_id;
	/** 수정자_명 */
	private String mod_nm;
	/** 삭제_여부 : required */
	private String del_yn;
	/** 삭제_일시 */
	private String del_dt;
	/** 삭제자_ID */
	private String del_id;
	/** 삭제자_명 */
	private String del_nm;
	
	/** 검색 Property */
	/** 비즈니스_분류  */
	private String srch_biz_clsfcn;
	/** 단계_분류 */
	private String srch_depth_clsfcn;
	/** 체결목적_대분류 */
	private String srch_cnclsnpurps_bigclsfcn;
	/** 체결목적_중분류 */
	private String srch_cnclsnpurps_midclsfcn;
	
	/**
	 * getter/setter method
	 */
	
	public int getLib_no() {
		return lib_no;
	}
	public void setLib_no(int lib_no) {
		this.lib_no = lib_no;
	}
	public String getLib_gbn() {
		return lib_gbn;
	}
	public void setLib_gbn(String lib_gbn) {
		this.lib_gbn = lib_gbn;
	}
	public String getRegion_gbn() {
		return region_gbn;
	}
	public void setRegion_gbn(String region_gbn) {
		this.region_gbn = region_gbn;
	}
	public String getBiz_clsfcn() {
		return biz_clsfcn;
	}
	public void setBiz_clsfcn(String biz_clsfcn) {
		this.biz_clsfcn = biz_clsfcn;
	}
	public String getDepth_clsfcn() {
		return depth_clsfcn;
	}
	public void setDepth_clsfcn(String depth_clsfcn) {
		this.depth_clsfcn = depth_clsfcn;
	}
	public String getCnclsnpurps_bigclsfcn() {
		return cnclsnpurps_bigclsfcn;
	}
	public void setCnclsnpurps_bigclsfcn(String cnclsnpurps_bigclsfcn) {
		this.cnclsnpurps_bigclsfcn = cnclsnpurps_bigclsfcn;
	}
	public String getCnclsnpurps_midclsfcn() {
		return cnclsnpurps_midclsfcn;
	}
	public void setCnclsnpurps_midclsfcn(String cnclsnpurps_midclsfcn) {
		this.cnclsnpurps_midclsfcn = cnclsnpurps_midclsfcn;
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
	public int getRdcnt() {
		return rdcnt;
	}
	public void setRdcnt(int rdcnt) {
		this.rdcnt = rdcnt;
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
	public String getSrch_biz_clsfcn() {
		return srch_biz_clsfcn;
	}
	public void setSrch_biz_clsfcn(String srch_biz_clsfcn) {
		this.srch_biz_clsfcn = srch_biz_clsfcn;
	}
	public String getSrch_depth_clsfcn() {
		return srch_depth_clsfcn;
	}
	public void setSrch_depth_clsfcn(String srch_depth_clsfcn) {
		this.srch_depth_clsfcn = srch_depth_clsfcn;
	}
	public String getSrch_cnclsnpurps_bigclsfcn() {
		return srch_cnclsnpurps_bigclsfcn;
	}
	public void setSrch_cnclsnpurps_bigclsfcn(String srch_cnclsnpurps_bigclsfcn) {
		this.srch_cnclsnpurps_bigclsfcn = srch_cnclsnpurps_bigclsfcn;
	}
	public String getSrch_cnclsnpurps_midclsfcn() {
		return srch_cnclsnpurps_midclsfcn;
	}
	public void setSrch_cnclsnpurps_midclsfcn(String srch_cnclsnpurps_midclsfcn) {
		this.srch_cnclsnpurps_midclsfcn = srch_cnclsnpurps_midclsfcn;
	}
	
}