/**
 * Project Name : 법무시스템
 * File name	: NoticeForm.java
 * Description	: 공지 Data Form(Model), 화면-서비스 로직 연결
 * Author		: 한지훈
 * Date			: 2011. 08. 11
 * Copyright	:   
 */
package com.sec.las.board.dto;

import com.sds.secframework.common.dto.CommonForm;

/**
 * Description	: Data Form(Model), 화면-서비스 로직 연결
 * Author		: 한지훈
 * Date			: 2011. 08. 10
 */
public class NoticeForm extends CommonForm {
	
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
	/** 검색 콤보(제목, 작성자, 내용) */
	private String srch_combo;
	/** 검색어 */
	private String srch_word;
	/** 조회시작일자 */
	private String srch_start_dt;
	/** 조회종료일자 */
	private String srch_end_dt;
	
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
	/* 전사공지 체크여부 */
	private String system_notice_yn;
	/* notice 호출 경로- 1.Main page 2. List Page */
	private String path_gbn; 

	/**********************************************
	 * 입력값
	 **********************************************/
	/** 일련번호 */
	private int seqno;
	/** 제목 */
	private String title;
	/** 내용 */
	private String cont;
	/** 나모웹에디터  - 내용 */
	private String body_mime;
	/** 영문제목 */
	private String title_en;
	/** 영문내용 */
	private String cont_en;
	/** 나모웹에디터  - 영문내용 */
	private String body_mime_en;
	/** 조회수 */
	private int rdcnt;
	/** 공지_종류 */
	private String annce_knd;
	/** 공지 기간 - 시작일 */
	private String annce_st_ymd;
	/** 공지 기간 - 종료일 */
	private String annce_ed_ymd;
	/** 중요공지사항 체크 */
	private String imp_flg;
	/** 메인 팝업체크 여부 */
	private String popup_yn;
	/** 등록_일시 */
	private String reg_dt;
	/** 등록자_ID */
	private String reg_id;
	/** 등록자_명 */
	private String reg_nm;
	/** 등록_부서 */
	private String reg_dept;
	/** 등록_부서_명 */
	private String reg_dept_nm;
	/** 수정_일시 */
	private String mod_dt;
	/** 수정자_ID */
	private String mod_id;
	/** 수정자_명 */
	private String mod_nm;
	/** 삭제_여부 */
	private String del_yn;
	/** 삭제_일시 */
	private String del_dt;
	/** 삭제자_ID */
	private String del_id;
	/** 삭제자_명 */
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
	public String getSrch_combo() {
		return srch_combo;
	}
	public void setSrch_combo(String srch_combo) {
		this.srch_combo = srch_combo;
	}
	public String getSrch_word() {
		return srch_word;
	}
	public void setSrch_word(String srch_word) {
		this.srch_word = srch_word;
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
	public int getSeqno() {
		return seqno;
	}
	public void setSeqno(int seqno) {
		this.seqno = seqno;
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
	public String getBody_mime() {
		return body_mime;
	}
	public void setBody_mime(String body_mime) {
		this.body_mime = body_mime;
	}
	public String getTitle_en() {
		return title_en;
	}
	public void setTitle_en(String title_en) {
		this.title_en = title_en;
	}
	public String getCont_en() {
		return cont_en;
	}
	public void setCont_en(String cont_en) {
		this.cont_en = cont_en;
	}
	public String getBody_mime_en() {
		return body_mime_en;
	}
	public void setBody_mime_en(String body_mime_en) {
		this.body_mime_en = body_mime_en;
	}
	public int getRdcnt() {
		return rdcnt;
	}
	public void setRdcnt(int rdcnt) {
		this.rdcnt = rdcnt;
	}
	public String getAnnce_knd() {
		return annce_knd;
	}
	public void setAnnce_knd(String annce_knd) {
		this.annce_knd = annce_knd;
	}
	public String getAnnce_st_ymd() {
		return annce_st_ymd;
	}
	public void setAnnce_st_ymd(String annce_st_ymd) {
		this.annce_st_ymd = annce_st_ymd;
	}
	public String getAnnce_ed_ymd() {
		return annce_ed_ymd;
	}
	public void setAnnce_ed_ymd(String annce_ed_ymd) {
		this.annce_ed_ymd = annce_ed_ymd;
	}	
	
	public String getImp_flg(){
		return imp_flg;
	}	
	public void setImp_flg(String imp_flg){
		this.imp_flg = imp_flg;
	}
	public String getSystem_notice_yn(){
		return system_notice_yn;
	}
	public void setSystem_notice_yn(String system_notice_yn){
		this.system_notice_yn = system_notice_yn;
	}
	
	public String getPopup_yn(){
		return popup_yn;
	}	
	public void setPopup_yn(String popup_yn){
		this.popup_yn = popup_yn;
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
	public String getPath_gbn(){
		return path_gbn;
	}
	public void setPath_gbn(String path_gbn){
		this.path_gbn = path_gbn;
	}
}