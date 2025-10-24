/**
* Project Name : 계약관리시스템
* File Name : TemplateForm.java
* Description : 표준 Template Form
* Author : 신남원
* Date : 2010.08.31
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.draft.dto;

import com.sds.secframework.common.dto.CommonForm;

public class LibraryForm extends CommonForm {
	/**********************************************************************
	 * DB 변수
	 **********************************************************************/
	private int 	lib_no;
	private String 	lib_gbn;
	private String 	region_gbn;
	private String  lang_gbn;	
	private String 	biz_clsfcn;
	private String 	depth_clsfcn;
	private String 	cnclsnpurps_bigclsfcn;
	private String 	cnclsnpurps_midclsfcn;
	private String 	title;
	private String 	cont;
	private int 	rdcnt;
	private String 	reg_dt;
	private String 	reg_id;
	private String 	reg_nm;
	private String 	mod_dt;
	private String 	mod_id;
	private String 	mod_nm;
	private String 	del_yn;
	private String 	del_dt;
	private String 	del_id;
	private String 	del_nm;
	
	//계약 상대
	private String cntrt_oppnt_cd;
	private String cntrt_oppnt_nm;
	private String[] cntrt_oppnt_cds;
	private String[] cntrt_oppnt_nms;
	
	//거래상대방 검색
	private String customer;

	/**********************************************************************
	 * 검색 변수
	 **********************************************************************/
	private String 	srch_lang_gbn;
	private String 	srch_biz_clsfcn;
	private String 	srch_depth_clsfcn;
	private String 	srch_cnclsnpurps_bigclsfcn;
	private String 	srch_cnclsnpurps_midclsfcn;
	private String 	srch_keyword;
	private String 	srch_keyword_content;
	
	/**********************************************************************
	 * Getters and Setters Method
	 **********************************************************************/

	public void setLib_no(int lib_no) {
		this.lib_no = lib_no;
	}
	public String getSrch_lang_gbn() {
		return srch_lang_gbn;
	}
	public void setSrch_lang_gbn(String srch_lang_gbn) {
		this.srch_lang_gbn = srch_lang_gbn;
	}
	public String getLang_gbn() {
		return lang_gbn;
	}
	public void setLang_gbn(String lang_gbn) {
		this.lang_gbn = lang_gbn;
	}
	public String getCntrt_oppnt_cd() {
		return cntrt_oppnt_cd;
	}
	public void setCntrt_oppnt_cd(String cntrt_oppnt_cd) {
		this.cntrt_oppnt_cd = cntrt_oppnt_cd;
	}
	public String getSrch_keyword() {
		return srch_keyword;
	}
	public void setSrch_keyword(String srch_keyword) {
		this.srch_keyword = srch_keyword;
	}
	public String getSrch_keyword_content() {
		return srch_keyword_content;
	}
	public void setSrch_keyword_content(String srch_keyword_content) {
		this.srch_keyword_content = srch_keyword_content;
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
	public void setLib_gbn(String lib_gbn) {
		this.lib_gbn = lib_gbn;
	}
	public void setRegion_gbn(String region_gbn) {
		this.region_gbn = region_gbn;
	}
	public void setBiz_clsfcn(String biz_clsfcn) {
		this.biz_clsfcn = biz_clsfcn;
	}
	public void setDepth_clsfcn(String depth_clsfcn) {
		this.depth_clsfcn = depth_clsfcn;
	}
	public void setCnclsnpurps_bigclsfcn(String cnclsnpurps_bigclsfcn) {
		this.cnclsnpurps_bigclsfcn = cnclsnpurps_bigclsfcn;
	}
	public void setCnclsnpurps_midclsfcn(String cnclsnpurps_midclsfcn) {
		this.cnclsnpurps_midclsfcn = cnclsnpurps_midclsfcn;
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
	public int getLib_no() {
		return this.lib_no;
	}
	public String getLib_gbn() {
		return this.lib_gbn;
	}
	public String getRegion_gbn() {
		return this.region_gbn;
	}
	public String getBiz_clsfcn() {
		return this.biz_clsfcn;
	}
	public String getDepth_clsfcn() {
		return this.depth_clsfcn;
	}
	public String getCnclsnpurps_bigclsfcn() {
		return this.cnclsnpurps_bigclsfcn;
	}
	public String getCnclsnpurps_midclsfcn() {
		return this.cnclsnpurps_midclsfcn;
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
	
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	

	public String getCntrt_oppnt_nm() {
		return cntrt_oppnt_nm;
	}
	public void setCntrt_oppnt_nm(String cntrt_oppnt_nm) {
		this.cntrt_oppnt_nm = cntrt_oppnt_nm;
	}
	public String[] getCntrt_oppnt_cds() {
		return cntrt_oppnt_cds;
	}
	public void setCntrt_oppnt_cds(String[] cntrt_oppnt_cds) {
		this.cntrt_oppnt_cds = cntrt_oppnt_cds;
	}
	public String[] getCntrt_oppnt_nms() {
		return cntrt_oppnt_nms;
	}
	public void setCntrt_oppnt_nms(String[] cntrt_oppnt_nms) {
		this.cntrt_oppnt_nms = cntrt_oppnt_nms;
	}
}