/**
 * Project Name : 계약관리시스템
 * File name	: RoleForm.java
 * Description	: 결재라인Role관리 Data Form(Model)
 * Author		: 박정훈
 * Date			: 2014. 05. 19
 * Copyright	:
 */
package com.sec.clm.admin.dto;

import com.sds.secframework.common.dto.CommonForm;

/**
 * Description	: 결재라인Role관리 Data Form(Model)
 * Author		: 박정훈
 * Date			: 2014. 05. 19
 */
public class RoleForm extends CommonForm {
	

	/**********************************************
	 * 검색 조건
	 **********************************************/
	private String srch_role_name;
	/** 사용여부 */
	private String srch_use_yn;
	
	private String srch_loc_gbn;
	
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
	/** comp_cd */
	private String comp_cd;
	/** role_cd */
	private String role_cd;
	/** role_name */
	private String role_name;
	
	/** 등록_일시 */
	private String reg_dt;
	/** 등록자_ID */
	private String reg_id;
	/** 수정_일시 */
	private String mod_dt;
	/** 수정_ID */
	private String mod_id;
	/** 삭제_일시 */
	private String del_dt;
	/** 삭제_ID */
	private String del_id;
	/** 설명 */
	private String description;
	/** 사용여부 */
	private String use_yn;
	
	/**Assigned Users**/
	private String user_id;
	
	private String user_nm;
	
	private String ass_id_list;
	private String rev_id_list;
	private String user_id_tmp;
	
	private String review_yn;
	
	private String rev_id_tmp;
	
	private String loc_gbn;
	
	/**********************************************
	 * getter, setter 메소드
	 **********************************************/

	
	
	public String getSrch_role_name() {
		return srch_role_name;
	}
	public void setSrch_role_name(String srch_role_name) {
		this.srch_role_name = srch_role_name;
	}
	public String getSrch_use_yn() {
		return srch_use_yn;
	}
	public void setSrch_use_yn(String srch_use_yn) {
		this.srch_use_yn = srch_use_yn;
	}
	

	public String getSrch_loc_gbn() {
		return srch_loc_gbn;
	}
	public void setSrch_loc_gbn(String srch_loc_gbn) {
		this.srch_loc_gbn = srch_loc_gbn;
	}
	public String getLoc_gbn() {
		return loc_gbn;
	}
	public void setLoc_gbn(String loc_gbn) {
		this.loc_gbn = loc_gbn;
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
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getComp_cd() {
		return comp_cd;
	}
	public void setComp_cd(String comp_cd) {
		this.comp_cd = comp_cd;
	}
	public String getRole_cd() {
		return role_cd;
	}
	public void setRole_cd(String role_cd) {
		this.role_cd = role_cd;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_id_tmp() {
		return user_id_tmp;
	}
	public void setUser_id_tmp(String user_id_tmp) {
		this.user_id_tmp = user_id_tmp;
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
	public String getAss_id_list() {
		return ass_id_list;
	}
	public void setAss_id_list(String ass_id_list) {

		this.ass_id_list = ass_id_list;
	}
	public String getUser_nm() {
		return user_nm;
	}
	public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	}
	public String getReview_yn() {
		return review_yn;
	}
	public void setReview_yn(String review_yn) {
		this.review_yn = review_yn;
	}

	public String getRev_id_list() {
		return rev_id_list;
	}
	public void setRev_id_list(String rev_id_list) {
		this.rev_id_list = rev_id_list;
	}
	public String getRev_id_tmp() {
		return rev_id_tmp;
	}
	public void setRev_id_tmp(String rev_id_tmp) {
		this.rev_id_tmp = rev_id_tmp;
	}
	
	
}