/**
* Project Name : 계약관리시스템
* File Name : ContractNegoPointForm.java
* Description : 주요거래 협상포인트 Form
* Author : 유영섭
* Date : 2010.09.29
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/



package com.sec.clm.share.dto;

import com.sds.secframework.common.dto.CommonForm;

public class ContractNegoPointForm extends CommonForm {
	
	/**********************************************
	 * 게시판설정(게시판명, 권한)
	 **********************************************/
	/** 게시판 등록 가능 여부 */
	private boolean auth_insert;
	/** 게시판 수정 가능 여부 */
	private boolean auth_modify;
	/** 게시판 삭제 가능 여부 */
	private boolean auth_delete;
	
	/**********************************************************************
	 * DB 변수
	 **********************************************************************/
	
	/**협상번호*/
	private int nego_no;
	/**계약상대코드*/
	private String cntrt_oppnt_cd;
	/**계약상대명*/
	private String cntrt_oppnt_nm;
	/**제목*/
	private String title;
	/**내용*/
	private String cont;
	/**조회수*/
	private int rdcnt;
	/**등록일시*/
	private String reg_dt;
	/**등록자ID*/
	private String reg_id;
	/**등록자명*/
	private String reg_nm;
	/**수정일시*/
	private String mod_dt;
	/**수정자ID*/
	private String mod_id;
	/**수정자명*/
	private String mod_nm;
	/**삭제여부*/
	private String del_yn;
	/**삭제일시*/
	private String del_dt;
	/**삭제자ID*/
	private String del_id;
	/**삭제자명*/
	private String del_nm;
	/**start_index*/
	private String start_index;
	/**end_index*/
	private String end_index;
	/**최근페이지*/
	private String curPage;
	
	/**********************************************************************
	 * DB 외 변수
	 **********************************************************************/
	
	private String srch_keyword; 
	
	/**거래상대방*/
	private String srch_customer;
	
	private String mode;

	private String customer_cd;
	
	/**나모웹에디터 타입 - 내용에 사용 */
	private String body_mime;
	
	
	/**********************************************************************
	 * Getters and Setters Method
	 **********************************************************************/
	
	public void setNego_no(int nego_no) {
		this.nego_no = nego_no;
	}
	public void setCntrt_oppnt_cd(String cntrt_oppnt_cd) {
		this.cntrt_oppnt_cd = cntrt_oppnt_cd;
	}
	public void setCntrt_oppnt_nm(String cntrt_oppnt_nm) {
		this.cntrt_oppnt_nm = cntrt_oppnt_nm;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	public void setRdcnt(int rdcnt) {
		this.rdcnt = rdcnt;
	}
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public void setReg_nm(String reg_nm) {
		this.reg_nm = reg_nm;
	}
	public void setMod_dt(String mod_dt) {
		this.mod_dt = mod_dt;
	}
	public void setMod_id(String mod_id) {
		this.mod_id = mod_id;
	}
	public void setMod_nm(String mod_nm) {
		this.mod_nm = mod_nm;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	public void setDel_dt(String del_dt) {
		this.del_dt = del_dt;
	}
	public void setDel_id(String del_id) {
		this.del_id = del_id;
	}
	public void setDel_nm(String del_nm) {
		this.del_nm = del_nm;
	}
	public void setStart_index(String start_index) {
		this.start_index = start_index;
	}
	public void setEnd_index(String end_index) {
		this.end_index = end_index;
	}
	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}
	public int getNego_no() {
		return this.nego_no;
	}
	public String getCntrt_oppnt_cd() {
		return this.cntrt_oppnt_cd;
	}
	public String getCntrt_oppnt_nm() {
		return this.cntrt_oppnt_nm;
	}
	public String getTitle() {
		return this.title;
	}
	public String getCont() {
		return this.cont;
	}
	public int getRdcnt() {
		return this.rdcnt;
	}
	public String getReg_dt() {
		return this.reg_dt;
	}
	public String getReg_id() {
		return this.reg_id;
	}
	public String getReg_nm() {
		return this.reg_nm;
	}
	public String getMod_dt() {
		return this.mod_dt;
	}
	public String getMod_id() {
		return this.mod_id;
	}
	public String getMod_nm() {
		return this.mod_nm;
	}
	public String getDel_yn() {
		return this.del_yn;
	}
	public String getDel_dt() {
		return this.del_dt;
	}
	public String getDel_id() {
		return this.del_id;
	}
	public String getDel_nm() {
		return this.del_nm;
	}
	public String getStart_index() {
		return this.start_index;
	}
	public String getEnd_index() {
		return this.end_index;
	}
	public String getCurPage() {
		return this.curPage;
	}
	public String getSrch_keyword() {
		return srch_keyword;
	}
	public void setSrch_keyword(String srch_keyword) {
		this.srch_keyword = srch_keyword;
	}
	public String getSrch_customer() {
		return srch_customer;
	}
	public void setSrch_customer(String srch_customer) {
		this.srch_customer = srch_customer;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getCustomer_cd() {
		return customer_cd;
	}
	public void setCustomer_cd(String customer_cd) {
		this.customer_cd = customer_cd;
	}
	public String getBody_mime() {
		return body_mime;
	}
	public void setBody_mime(String body_mime) {
		this.body_mime = body_mime;
	}
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
    
	
}