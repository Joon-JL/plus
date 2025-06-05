/**
 * Project Name : 계약관리시스템
 * File name	: DecideArbitrarilyReForm.java
 * Description	: 전결규정 Data Form(Model), 서비스 로직-DB 연결
 * Author		: dawn
 * Date			: 2011. 09. 26
 * Copyright	:   
 */

package com.sec.clm.admin.dto;

import com.sds.secframework.common.dto.CommonForm;

public class DecideArbitrarilyReForm extends CommonForm{
	
	/* 사업부 selectbox */
	private String orgnz_cd;
	private String orgnz_gbn;
	private String orgnz_nm;
	private String up_orgnz_cd;
	
	/* 비즈니스 단계 selectbox */
	private String up_type_cd;
	private String type_cd;
	private String cd_nm;
	
	private String srch_operdiv_cd;
	private String srch_biz_clsfcn;
	
	private int seqno;
	private String operdiv_cd;
	private String operdiv_nm;
	private String biz_clsfcn;
	private String biz_clsfcn_nm;
	private String dcd_crtrn;
	private String dcd_authman;
	private String dcd_authman_nm;
	private String note;
	private String rule_cont;
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
	
	public String getOrgnz_cd() {
		return orgnz_cd;
	}
	public void setOrgnz_cd(String orgnz_cd) {
		this.orgnz_cd = orgnz_cd;
	}
	public String getOrgnz_gbn() {
		return orgnz_gbn;
	}
	public void setOrgnz_gbn(String orgnz_gbn) {
		this.orgnz_gbn = orgnz_gbn;
	}
	public String getOrgnz_nm() {
		return orgnz_nm;
	}
	public void setOrgnz_nm(String orgnz_nm) {
		this.orgnz_nm = orgnz_nm;
	}
	public String getUp_orgnz_cd() {
		return up_orgnz_cd;
	}
	public void setUp_orgnz_cd(String up_orgnz_cd) {
		this.up_orgnz_cd = up_orgnz_cd;
	}
	
	public String getUp_type_cd() {
		return up_type_cd;
	}
	public void setUp_type_cd(String up_type_cd) {
		this.up_type_cd = up_type_cd;
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
	
	public String getSrch_operdiv_cd() {
		return srch_operdiv_cd;
	}
	public void setSrch_operdiv_cd(String srch_operdiv_cd) {
		this.srch_operdiv_cd = srch_operdiv_cd;
	}
	public String getSrch_biz_clsfcn() {
		return srch_biz_clsfcn;
	}
	public void setSrch_biz_clsfcn(String srch_biz_clsfcn) {
		this.srch_biz_clsfcn = srch_biz_clsfcn;
	}
	
	public int getSeqno() {
		return seqno;
	}
	public void setSeqno(int seqno) {
		this.seqno = seqno;
	}
	public String getOperdiv_cd() {
		return operdiv_cd;
	}
	public void setOperdiv_cd(String operdiv_cd) {
		this.operdiv_cd = operdiv_cd;
	}
	public String getOperdiv_nm() {
		return operdiv_nm;
	}
	public void setOperdiv_nm(String operdiv_nm) {
		this.operdiv_nm = operdiv_nm;
	}
	public String getBiz_clsfcn() {
		return biz_clsfcn;
	}
	public void setBiz_clsfcn(String biz_clsfcn) {
		this.biz_clsfcn = biz_clsfcn;
	}
	public String getBiz_clsfcn_nm() {
		return biz_clsfcn_nm;
	}
	public void setBiz_clsfcn_nm(String biz_clsfcn_nm) {
		this.biz_clsfcn_nm = biz_clsfcn_nm;
	}
	public String getDcd_crtrn() {
		return dcd_crtrn;
	}
	public void setDcd_crtrn(String dcd_crtrn) {
		this.dcd_crtrn = dcd_crtrn;
	}
	public String getDcd_authman() {
		return dcd_authman;
	}
	public void setDcd_authman(String dcd_authman) {
		this.dcd_authman = dcd_authman;
	}
	public String getDcd_authman_nm() {
		return dcd_authman_nm;
	}
	public void setDcd_authman_nm(String dcd_authman_nm) {
		this.dcd_authman_nm = dcd_authman_nm;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getRule_cont() {
		return rule_cont;
	}
	public void setRule_cont(String rule_cont) {
		this.rule_cont = rule_cont;
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
	
	

}
