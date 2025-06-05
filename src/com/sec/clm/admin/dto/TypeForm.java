/**
 * Project Name : 계약관리시스템
 * File name	: TypeForm.java
 * Description	: 계약유형관리 Data Form(Model), 화면-서비스 로직 연결
 * Author		: 한지훈
 * Date			: 2011. 08. 24
 * Copyright	:   
 */
package com.sec.clm.admin.dto;

import com.sds.secframework.common.dto.CommonForm;

/**
 * Description	: Data Form(Model), 화면-서비스 로직 연결
 * Author		: 한지훈
 * Date			: 2011. 08. 24
 */
public class TypeForm extends CommonForm {
	
	/**********************************************
	 * 게시판설정(게시판명, 권한)
	 **********************************************/
	/** 게시판 등록 가능 여부 */
	private boolean auth_insert;
	/** 게시판 수정 가능 여부 */
	private boolean auth_modify;
	/** 게시판 삭제 가능 여부 */
	private boolean auth_delete;

	/**********************************************
	 * 검색 조건
	 **********************************************/
	/** 검색 콤보(계약분류) */
	private String srch_dimension;
	/** 검색어 */
	private String srch_word;
	
	/**********************************************
	 * 페이징
	 **********************************************/
	/** 페이지(공통) */
	protected String start_index;                      
	/** 페이지(공통) */
	protected String end_index;                        
	/** 페이지(공통) */
	protected String curPage;  
	/** 페이지당 row 수 */
	int row_cnt = 10;

	/**********************************************
	 * 입력값
	 **********************************************/
	/** 분류코드 */
	private String dimension_cd;
	/** 상세코드 */
	private String detail_cd;
	/** 유형코드 */
	private String type_cd;
	/** 분류코드_명 */
	private String dimension_nm;
	/** 상세코드_명 */
	private String detail_nm;	
	/** 유형코드_명 */
	private String cd_nm;
	/** 유형코드_명_약어 */
	private String cd_nm_abbr;
	/** 유형코드_명_영문 */
	private String cd_nm_eng;
	/** 유형코드_명_약어_영문 */
	private String cd_nm_abbr_eng;
	/** 출력_순서 */
	private String prnt_srt;
	/** 코드_설명 */
	private String cd_expl;
	/** 사용_여부 */
	private String use_yn;
	/** 등록_일시 */
	private String reg_dt;
	/** 등록자_ID */
	private String reg_id;
	/** 등록자_명 */
	private String reg_nm;
	/** 등록_부서_명 */
	private String reg_dept_nm;
	/** 수정_일시 */
	private String mod_dt;
	/** 수정자_ID */
	private String mod_id;
	/** 수정자_명 */
	private String mod_nm;
	/** 수정_부서_명 */
	private String mod_dept_nm;
	/** 적용 대상 코드 */
	private String item_cd;
	/** 적용 대상 코드 */
	private String item_cds[];
	/** 적용 대상 코드_명 */
	private String item_cd_nm;
	/** 적용 대상 코드_명 */
	private String item_cd_nms[];
	/** 적용 대상 코드_명_약어 */
	private String item_cd_nm_abbr;
	/** 적용 대상 코드_명_약어 */
	private String item_cd_nm_abbrs[];
	/** 적용 대상 코드_명_영문 */
	private String item_cd_nm_eng;
	/** 적용 대상 코드_명_영문 */
	private String item_cd_nm_engs[];
	/** 적용 대상 코드_명_약어_영문  */
	private String item_cd_nm_abbr_eng;
	/** 적용 대상 코드_명_약어_영문  */
	private String item_cd_nm_abbr_engs[];
	
	/**********************************************
	 * getter, setter 메소드
	 **********************************************/
	public boolean getAuth_insert() {
		return auth_insert;
	}
	public void setAuth_insert(boolean auth_insert) {
		this.auth_insert = auth_insert;
	}
	public boolean getAuth_modify() {
		return auth_modify;
	}
	public void setAuth_modify(boolean auth_modify) {
		this.auth_modify = auth_modify;
	}
	public boolean isAuth_delete() {
		return auth_delete;
	}
	public void setAuth_delete(boolean auth_delete) {
		this.auth_delete = auth_delete;
	}
	public String getSrch_dimension() {
		return srch_dimension;
	}
	public void setSrch_dimension(String srch_dimension) {
		this.srch_dimension = srch_dimension;
	}
	public String getSrch_word() {
		return srch_word;
	}
	public void setSrch_word(String srch_word) {
		this.srch_word = srch_word;
	}
	public String getStart_index() {
		return start_index;
	}
	public void setStart_index(String start_index) {
		this.start_index = start_index;
	}
	public String getEnd_index() {
		return end_index;
	}
	public void setEnd_index(String end_index) {
		this.end_index = end_index;
	}
	public String getCurPage() {
		return curPage;
	}
	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}
	public int getRow_cnt() {
		return row_cnt;
	}
	public void setRow_cnt(int row_cnt) {
		this.row_cnt = row_cnt;
	}
	public String getDimension_cd() {
		return dimension_cd;
	}
	public void setDimension_cd(String dimension_cd) {
		this.dimension_cd = dimension_cd;
	}
	public String getDetail_cd() {
		return detail_cd;
	}
	public void setDetail_cd(String detail_cd) {
		this.detail_cd = detail_cd;
	}
	public String getType_cd() {
		return type_cd;
	}
	public void setType_cd(String type_cd) {
		this.type_cd = type_cd;
	}
	public String getDimension_nm() {
		return dimension_nm;
	}
	public void setDimension_nm(String dimension_nm) {
		this.dimension_nm = dimension_nm;
	}
	public String getDetail_nm() {
		return detail_nm;
	}
	public void setDetail_nm(String detail_nm) {
		this.detail_nm = detail_nm;
	}
	public String getCd_nm() {
		return cd_nm;
	}
	public void setCd_nm(String cd_nm) {
		this.cd_nm = cd_nm;
	}
	public String getCd_nm_abbr() {
		return cd_nm_abbr;
	}
	public void setCd_nm_abbr(String cd_nm_abbr) {
		this.cd_nm_abbr = cd_nm_abbr;
	}
	public String getCd_nm_eng() {
		return cd_nm_eng;
	}
	public void setCd_nm_eng(String cd_nm_eng) {
		this.cd_nm_eng = cd_nm_eng;
	}
	public String getCd_nm_abbr_eng() {
		return cd_nm_abbr_eng;
	}
	public void setCd_nm_abbr_eng(String cd_nm_abbr_eng) {
		this.cd_nm_abbr_eng = cd_nm_abbr_eng;
	}
	public String getPrnt_srt() {
		return prnt_srt;
	}
	public void setPrnt_srt(String prnt_srt) {
		this.prnt_srt = prnt_srt;
	}
	public String getCd_expl() {
		return cd_expl;
	}
	public void setCd_expl(String cd_expl) {
		this.cd_expl = cd_expl;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
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
	public String getMod_dept_nm() {
		return mod_dept_nm;
	}
	public void setMod_dept_nm(String mod_dept_nm) {
		this.mod_dept_nm = mod_dept_nm;
	}
	public String getItem_cd() {
		return item_cd;
	}
	public void setItem_cd(String item_cd) {
		this.item_cd = item_cd;
	}
	public String[] getItem_cds() {
		return item_cds;
	}
	public void setItem_cds(String item_cds[]) {
		this.item_cds = item_cds;
	}
	public String getItem_cd_nm() {
		return item_cd_nm;
	}
	public void setItem_cd_nm(String item_cd_nm) {
		this.item_cd_nm = item_cd_nm;
	}
	public String[] getItem_cd_nms() {
		return item_cd_nms;
	}
	public void setItem_cd_nms(String[] item_cd_nms) {
		this.item_cd_nms = item_cd_nms;
	}
	public String getItem_cd_nm_abbr() {
		return item_cd_nm_abbr;
	}
	public void setItem_cd_nm_abbr(String item_cd_nm_abbr) {
		this.item_cd_nm_abbr = item_cd_nm_abbr;
	}
	public String[] getItem_cd_nm_abbrs() {
		return item_cd_nm_abbrs;
	}
	public void setItem_cd_nm_abbrs(String[] item_cd_nm_abbrs) {
		this.item_cd_nm_abbrs = item_cd_nm_abbrs;
	}
	public String getItem_cd_nm_eng() {
		return item_cd_nm_eng;
	}
	public void setItem_cd_nm_eng(String item_cd_nm_eng) {
		this.item_cd_nm_eng = item_cd_nm_eng;
	}
	public String[] getItem_cd_nm_engs() {
		return item_cd_nm_engs;
	}
	public void setItem_cd_nm_engs(String[] item_cd_nm_engs) {
		this.item_cd_nm_engs = item_cd_nm_engs;
	}
	public String getItem_cd_nm_abbr_eng() {
		return item_cd_nm_abbr_eng;
	}
	public void setItem_cd_nm_abbr_eng(String item_cd_nm_abbr_eng) {
		this.item_cd_nm_abbr_eng = item_cd_nm_abbr_eng;
	}
	public String[] getItem_cd_nm_abbr_engs() {
		return item_cd_nm_abbr_engs;
	}
	public void setItem_cd_nm_abbr_engs(String[] item_cd_nm_abbr_engs) {
		this.item_cd_nm_abbr_engs = item_cd_nm_abbr_engs;
	}
}