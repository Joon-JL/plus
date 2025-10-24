/**
 * Project Name : 계약관리시스템
 * File name	: DimensionForm.java
 * Description	: 계약분류관리 Data Form(Model), 화면-서비스 로직 연결
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
public class DimensionForm extends CommonForm {
	
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
	/** 코드 */
	private String type_cd;
	/** 코드_명 */
	private String cd_nm;
	/** 코드_명_약어 */
	private String cd_nm_abbr;
	/** 코드_명_영문 */
	private String cd_nm_eng;
	/** 코드_명_약어_영문 */
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
	/** 검색 번호 */
	private String srch_type_cd;
	
	
	
	private String srch_one_rule_depth;
	private String srch_two_rule_depth;
	private String srch_cont;
	private String srch_rule_choice;
	private String one_rule_title_input;
	private int srch_rule_no;
	private String menuGubun;
	
	
	public String getMenuGubun() {
		return menuGubun;
	}
	public void setMenuGubun(String menuGubun) {
		this.menuGubun = menuGubun;
	}
	public int getSrch_rule_no() {
		return srch_rule_no;
	}
	public void setSrch_rule_no(int srch_rule_no) {
		this.srch_rule_no = srch_rule_no;
	}
	/**********************************************************************
	 * DB 변수
	 **********************************************************************/
	private int rule_no;
	private int up_rule_no;
	private int rule_depth;
	private int rule_srt;
	private String dn_rule_exist_yn;
	private String rule_title;
	private String rule_cont;
	private String rule_cont_en;
	public String getSrch_one_rule_depth() {
		return srch_one_rule_depth;
	}
	public void setSrch_one_rule_depth(String srch_one_rule_depth) {
		this.srch_one_rule_depth = srch_one_rule_depth;
	}
	public String getSrch_two_rule_depth() {
		return srch_two_rule_depth;
	}
	public void setSrch_two_rule_depth(String srch_two_rule_depth) {
		this.srch_two_rule_depth = srch_two_rule_depth;
	}
	public String getSrch_cont() {
		return srch_cont;
	}
	public void setSrch_cont(String srch_cont) {
		this.srch_cont = srch_cont;
	}
	public String getSrch_rule_choice() {
		return srch_rule_choice;
	}
	public void setSrch_rule_choice(String srch_rule_choice) {
		this.srch_rule_choice = srch_rule_choice;
	}
	public String getOne_rule_title_input() {
		return one_rule_title_input;
	}
	public void setOne_rule_title_input(String one_rule_title_input) {
		this.one_rule_title_input = one_rule_title_input;
	}
	public int getRule_no() {
		return rule_no;
	}
	public void setRule_no(int rule_no) {
		this.rule_no = rule_no;
	}
	public int getUp_rule_no() {
		return up_rule_no;
	}
	public void setUp_rule_no(int up_rule_no) {
		this.up_rule_no = up_rule_no;
	}
	public int getRule_depth() {
		return rule_depth;
	}
	public void setRule_depth(int rule_depth) {
		this.rule_depth = rule_depth;
	}
	public int getRule_srt() {
		return rule_srt;
	}
	public void setRule_srt(int rule_srt) {
		this.rule_srt = rule_srt;
	}
	public String getDn_rule_exist_yn() {
		return dn_rule_exist_yn;
	}
	public void setDn_rule_exist_yn(String dn_rule_exist_yn) {
		this.dn_rule_exist_yn = dn_rule_exist_yn;
	}
	public String getRule_title() {
		return rule_title;
	}
	public void setRule_title(String rule_title) {
		this.rule_title = rule_title;
	}
	public String getRule_cont() {
		return rule_cont;
	}
	public void setRule_cont(String rule_cont) {
		this.rule_cont = rule_cont;
	}
	public String getRule_cont_en() {
		return rule_cont_en;
	}
	public void setRule_cont_en(String rule_cont_en) {
		this.rule_cont_en = rule_cont_en;
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
	public String getOne_title() {
		return one_title;
	}
	public void setOne_title(String one_title) {
		this.one_title = one_title;
	}
	public String getOne_cont() {
		return one_cont;
	}
	public void setOne_cont(String one_cont) {
		this.one_cont = one_cont;
	}
	public String getTwo_title() {
		return two_title;
	}
	public void setTwo_title(String two_title) {
		this.two_title = two_title;
	}
	public String getTwo_cont() {
		return two_cont;
	}
	public void setTwo_cont(String two_cont) {
		this.two_cont = two_cont;
	}
	private String del_yn;
	private String del_dt;
	private String del_id;
	private String del_nm;
	private String one_title;
	private String one_cont;
	private String two_title;
	private String two_cont;
	
	public String getSrch_type_cd() {
		return srch_type_cd;
	}
	public void setSrch_type_cd(String srch_type_cd) {
		this.srch_type_cd = srch_type_cd;
	}
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
	public String getType_cd() {
		return type_cd;
	}
	public void setType_cd(String type_cd) {
		this.type_cd = type_cd;
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
}