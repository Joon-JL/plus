/**
 * Project Name : 법무시스템 이식
 * File name	: LwsCommonForm.java
 * Description	: 로펌서비스 공통  Data Form(Model)
 * Author		: 박병주
 * Date			: 2011. 09
 * Copyright	: 2011 by SAMSUNG. All rights reserved.   
 */
package com.sec.las.lawservice.dto;

import com.sds.secframework.common.dto.CommonForm;

public class LwsCommonForm extends CommonForm {
	
	// 검색조건	
	private String srch_lawfirm_id;	
	private String srch_lawfirm_nm;	
	private String srch_event_no;	
	private String srch_event_nm;	
	private String srch_lwr_nm;	
	private String srch_reg_nm;	
	private String srch_kbn1;	
	private String srch_kbn2;	
	private String srch_start_dt;	
	private String srch_end_dt;	
	private String srch_lwr_no;	
	private String srch_order_type;
	private String srch_nation;
	private String srch_nation_nm;	
	private String srch_mainftre_estmt;
	private String srch_nopay_start_dt;
	private String srch_nopay_end_dt;
	
    // 검색조건 소송상대코드
	private String srch_lawsuit_trgt_cd;
    // 검색조건 미지급여부
	private String srch_unpay_yn;
    // 검색조건 송금 여부
	private String srch_remit_yn;
    // 검색조건 리뷰 여부
	private String srch_review_yn;
    // 검색조건 총괄코드
	private String srch_group_cd;
    // 검색조건 접수일 시작
	private String srch_acpt_start_dt;
    // 검색조건 접수일 끝
	private String srch_acpt_end_dt;
    // 검색조건 미지급일 시작
	private String srch_unpay_start_dt;
    // 검색조건 미지급일 끝
	private String srch_unpay_end_dt;
    // 검색조건 송금일 시작
	private String srch_remit_start_dt;
    // 검색조건 송금일 끝
	private String srch_remit_end_dt;
	
	
	//검색조건: 전문분야
	private String srch_expert_area;
	//검색조건: 변호사평가
	private String srch_lwr_estimate_lvl;
	
	// 화면제어
	private String forward_from;
	
	// 공통 DB 
	private String reg_dt;
	private String reg_id;
	private String reg_nm;
	private String mod_dt;
	private String mod_id;
	private String mod_nm;
	private String del_yn;
	private String del_dt;
	private String del_id;
	private String del_nm;
	
	// 나모
	private String body_mime;	
	
	//권한관련
	private boolean lws_auth_admin_access;
	private boolean lws_auth_access;
	private boolean lws_auth_search;
	private boolean lws_auth_modify;
	private boolean lws_auth_delete;	
	
	

	public String getSrch_lawsuit_trgt_cd() {
		return srch_lawsuit_trgt_cd;
	}

	public void setSrch_lawsuit_trgt_cd(String srch_lawsuit_trgt_cd) {
		this.srch_lawsuit_trgt_cd = srch_lawsuit_trgt_cd;
	}

	public String getSrch_unpay_yn() {
		return srch_unpay_yn;
	}

	public void setSrch_unpay_yn(String srch_unpay_yn) {
		this.srch_unpay_yn = srch_unpay_yn;
	}

	public String getSrch_remit_yn() {
		return srch_remit_yn;
	}

	public void setSrch_remit_yn(String srch_remit_yn) {
		this.srch_remit_yn = srch_remit_yn;
	}

	public String getSrch_review_yn() {
		return srch_review_yn;
	}

	public void setSrch_review_yn(String srch_review_yn) {
		this.srch_review_yn = srch_review_yn;
	}

	public String getSrch_group_cd() {
		return srch_group_cd;
	}

	public void setSrch_group_cd(String srch_group_cd) {
		this.srch_group_cd = srch_group_cd;
	}

	public String getSrch_acpt_start_dt() {
		return srch_acpt_start_dt;
	}

	public void setSrch_acpt_start_dt(String srch_acpt_start_dt) {
		this.srch_acpt_start_dt = srch_acpt_start_dt;
	}

	public String getSrch_acpt_end_dt() {
		return srch_acpt_end_dt;
	}

	public void setSrch_acpt_end_dt(String srch_acpt_end_dt) {
		this.srch_acpt_end_dt = srch_acpt_end_dt;
	}

	public String getSrch_unpay_start_dt() {
		return srch_unpay_start_dt;
	}

	public void setSrch_unpay_start_dt(String srch_unpay_start_dt) {
		this.srch_unpay_start_dt = srch_unpay_start_dt;
	}

	public String getSrch_unpay_end_dt() {
		return srch_unpay_end_dt;
	}

	public void setSrch_unpay_end_dt(String srch_unpay_end_dt) {
		this.srch_unpay_end_dt = srch_unpay_end_dt;
	}

	public String getSrch_nopay_start_dt() {
		return srch_nopay_start_dt;
	}

	public void setSrch_nopay_start_dt(String srch_nopay_start_dt) {
		this.srch_nopay_start_dt = srch_nopay_start_dt;
	}

	public String getSrch_nopay_end_dt() {
		return srch_nopay_end_dt;
	}

	public void setSrch_nopay_end_dt(String srch_nopay_end_dt) {
		this.srch_nopay_end_dt = srch_nopay_end_dt;
	}

	public String getSrch_remit_start_dt() {
		return srch_remit_start_dt;
	}

	public void setSrch_remit_start_dt(String srch_remit_start_dt) {
		this.srch_remit_start_dt = srch_remit_start_dt;
	}

	public String getSrch_remit_end_dt() {
		return srch_remit_end_dt;
	}

	public void setSrch_remit_end_dt(String srch_remit_end_dt) {
		this.srch_remit_end_dt = srch_remit_end_dt;
	}

	public boolean isLws_auth_admin_access() {
		return lws_auth_admin_access;
	}

	public void setLws_auth_admin_access(boolean lws_auth_admin_access) {
		this.lws_auth_admin_access = lws_auth_admin_access;
	}

	public boolean getLws_auth_access() {
		return lws_auth_access;
	}

	public void setLws_auth_access(boolean lws_auth_access) {
		this.lws_auth_access = lws_auth_access;
	}

	public boolean getLws_auth_search() {
		return lws_auth_search;
	}

	public void setLws_auth_search(boolean lws_auth_search) {
		this.lws_auth_search = lws_auth_search;
	}

	public boolean getLws_auth_modify() {
		return lws_auth_modify;
	}

	public void setLws_auth_modify(boolean lws_auth_modify) {
		this.lws_auth_modify = lws_auth_modify;
	}

	public boolean getLws_auth_delete() {
		return lws_auth_delete;
	}

	public void setLws_auth_delete(boolean lws_auth_delete) {
		this.lws_auth_delete = lws_auth_delete;
	}

	public String getSrch_expert_area() {
		return srch_expert_area;
	}

	public void setSrch_expert_area(String srch_expert_area) {
		this.srch_expert_area = srch_expert_area;
	}

	public String getSrch_lwr_estimate_lvl() {
		return srch_lwr_estimate_lvl;
	}

	public void setSrch_lwr_estimate_lvl(String srch_lwr_estimate_lvl) {
		this.srch_lwr_estimate_lvl = srch_lwr_estimate_lvl;
	}

	public String getSrch_mainftre_estmt() {
		return srch_mainftre_estmt;
	}

	public void setSrch_mainftre_estmt(String srch_mainftre_estmt) {
		this.srch_mainftre_estmt = srch_mainftre_estmt;
	}

	public String getSrch_nation() {
		return srch_nation;
	}

	public void setSrch_nation(String srch_nation) {
		this.srch_nation = srch_nation;
	}

	public String getSrch_nation_nm() {
		return srch_nation_nm;
	}

	public void setSrch_nation_nm(String srch_nation_nm) {
		this.srch_nation_nm = srch_nation_nm;
	}

	public String getBody_mime() {
		return body_mime;
	}

	public void setBody_mime(String body_mime) {
		this.body_mime = body_mime;
	}

	public String getSrch_lawfirm_id() {
		return srch_lawfirm_id;
	}

	public void setSrch_lawfirm_id(String srch_lawfirm_id) {
		this.srch_lawfirm_id = srch_lawfirm_id;
	}

	public String getSrch_lawfirm_nm() {
		return srch_lawfirm_nm;
	}

	public void setSrch_lawfirm_nm(String srch_lawfirm_nm) {
		this.srch_lawfirm_nm = srch_lawfirm_nm;
	}

	public String getSrch_event_no() {
		return srch_event_no;
	}

	public void setSrch_event_no(String srch_event_no) {
		this.srch_event_no = srch_event_no;
	}

	public String getSrch_event_nm() {
		return srch_event_nm;
	}

	public void setSrch_event_nm(String srch_event_nm) {
		this.srch_event_nm = srch_event_nm;
	}

	public String getSrch_lwr_nm() {
		return srch_lwr_nm;
	}

	public void setSrch_lwr_nm(String srch_lwr_nm) {
		this.srch_lwr_nm = srch_lwr_nm;
	}

	public String getSrch_reg_nm() {
		return srch_reg_nm;
	}

	public void setSrch_reg_nm(String srch_reg_nm) {
		this.srch_reg_nm = srch_reg_nm;
	}

	public String getSrch_kbn1() {
		return srch_kbn1;
	}

	public void setSrch_kbn1(String srch_kbn1) {
		this.srch_kbn1 = srch_kbn1;
	}

	public String getSrch_kbn2() {
		return srch_kbn2;
	}

	public void setSrch_kbn2(String srch_kbn2) {
		this.srch_kbn2 = srch_kbn2;
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

	public String getSrch_lwr_no() {
		return srch_lwr_no;
	}

	public void setSrch_lwr_no(String srch_lwr_no) {
		this.srch_lwr_no = srch_lwr_no;
	}

	public String getSrch_order_type() {
		return srch_order_type;
	}

	public void setSrch_order_type(String srch_order_type) {
		this.srch_order_type = srch_order_type;
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

	public String getForward_from() {
		return forward_from;
	}

	public void setForward_from(String forward_from) {
		this.forward_from = forward_from;
	}	
}
