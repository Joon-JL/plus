package com.sec.las.lawinformation.dto;

import com.sds.secframework.common.dto.CommonForm;
import com.sds.secframework.common.util.DateUtil;

public class GuideneduForm extends CommonForm {

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
	/**검색타입(제목, 작성자, 내용)*/
	private String srch_type; 
	/**검색어*/
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

	/**********************************************
	 * 입력값
	 **********************************************/
	private String info_gbn;
	private int seqno;
	private String title;
	private String cont;
	private int rdcnt;
	private String reg_dt;
	private String reg_id;
	private String reg_nm;
	private String reg_dept;
	private String reg_dept_nm;
	private String mod_dt;
	private String mod_id;
	private String mod_nm;
	private String del_yn;
	private String del_dt;
	private String del_id;
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
	public String getSrch_type() {
		return srch_type;
	}
	public void setSrch_type(String srch_type) {
		this.srch_type = srch_type;
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