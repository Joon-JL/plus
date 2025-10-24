/**
 * Project Name : 법무시스템 이식
 * File name	: MainLawInfoForm.java
 * Description	: 주요입법동향/법무사례, 화면-서비스 로직 연결
 * Author		: SDS
 * Date			: 2011. 08
 * Copyright	: 2011 by SAMSUNG. All rights reserved.  
 */
package com.sec.las.lawinformation.dto;

import com.sds.secframework.common.dto.CommonForm;

/**
 * Description	: 주요입법동향/법무사례  Data Form(Model), 화면-서비스 로직 연결
 * Author 		: SDS
 * Date			: 2011. 08 
 */
public class MainLawInfoForm extends CommonForm {

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
	/** 검색 시작 날짜 */
	private String srch_start_dt;
	/** 검색 마지막 날짜 */
	private String srch_end_dt;
	/** 검색 내용 */
	private String srch_name;
	/** 검색 콤보 */
	private String srch_combo;
	/** 국가 코드 **/
	private String srch_nation;
	/** 사레 종류 **/
	private String srch_mainlawexam;
	/** 검색 국내/해외구분 **/
	private String srch_dmstfrgn_gbn;
	
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
	/** 정보 구분 - 공통코드(시스템 : LAS, 그룹코드 : C004) "C00403", "C00404" 만 사용 */
	private String info_gbn;
	/**일렬번호*/
	private int seqno;
	/**법률구분*/
	private String law_gbn;
	/**제목*/
	private String title;
	/**내용*/
	private String cont;
	/**국내해외_구분*/
	private String dmstfrgn_gbn;
	/**조회수 */
	private int rdcnt;
	/**대상국가*/
	private String trgt_nation;
	/**등록_일시 */ 
	private String reg_dt;
	/**등록자_ID*/
	private String reg_id;
	/**등록자 명*/
	private String reg_nm;
	/**등록_부서*/
	private String reg_dept;
	/**등록 부서명*/
	private String reg_dept_nm;
	/**수정_일시*/
	private String mod_dt;
	/**수정자_ID*/
	private String mod_id;
	/**수정자_명*/
	private String mod_nm;
	/**삭제자_명*/
	private String del_yn;
	/**삭제_일시*/
	private String del_dt;
	/**삭제자_ID*/
	private String del_id;
	/**삭제자_명*/
	private String del_nm;

	/**********************************************
	 * getter, setter 메소드
	 **********************************************/
	public boolean isAuth_insert() {
		return auth_insert;
	}
	public void setAuth_insert(boolean auth_insert) {
		this.auth_insert = auth_insert;
	}
	public boolean isAuth_modify() {
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
	public String getSrch_nation() {
		return srch_nation;
	}
	public void setSrch_nation(String srch_nation) {
		this.srch_nation = srch_nation;
	}
	public String getSrch_mainlawexam() {
		return srch_mainlawexam;
	}
	public void setSrch_mainlawexam(String srch_mainlawexam) {
		this.srch_mainlawexam = srch_mainlawexam;
	}
	public String getSrch_dmstfrgn_gbn() {
		return srch_dmstfrgn_gbn;
	}
	public void setSrch_dmstfrgn_gbn(String srch_dmstfrgn_gbn) {
		this.srch_dmstfrgn_gbn = srch_dmstfrgn_gbn;
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
	
	public String getInfo_gbn() {
		return info_gbn;
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
	public String getLaw_gbn() {
		return law_gbn;
	}
	public void setLaw_gbn(String law_gbn) {
		this.law_gbn = law_gbn;
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
	public String getDmstfrgn_gbn() {
		return dmstfrgn_gbn;
	}
	public void setDmstfrgn_gbn(String dmstfrgn_gbn) {
		this.dmstfrgn_gbn = dmstfrgn_gbn;
	}
	public int getRdcnt() {
		return rdcnt;
	}
	public void setRdcnt(int rdcnt) {
		this.rdcnt = rdcnt;
	}
	public String getTrgt_nation() {
		return trgt_nation;
	}
	public void setTrgt_nation(String trgt_nation) {
		this.trgt_nation = trgt_nation;
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
}
