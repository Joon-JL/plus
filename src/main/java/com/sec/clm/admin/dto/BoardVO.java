/**
* Project Name : 계약관리시스템
* File Name : BoardVO.java
* Description : 공지사항 VO
* Author : 신남원
* Date : 2010.09.01
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.admin.dto;

import com.sds.secframework.common.dto.CommonVO;

public class BoardVO extends CommonVO {
	/**********************************************************************
	 * DB 변수
	 **********************************************************************/
	private int seqno;
	private String annce_gbn;
	private String title;
	private String cont_type;
	private String cont;
	private int rdcnt;
	private String rd_trgt1;
	private String rd_trgt2;
	private String annce_startday;
	private String annce_endday;
	private String reg_dt;
	private String reg_id;
	private String reg_nm;
	private String regman_jikgup_nm;
	private String reg_dept_nm;
	private String sec_yn;
	private String mod_dt;
	private String mod_id;
	private String mod_nm;
	private String del_yn;
	private String del_dt;
	private String del_id;
	private String del_nm;

	private String orgnz_cd;
	private String[] orgnz_cds;
	/**********************************************************************
	 * DB 외 변수
	 **********************************************************************/
	private String srch_start_dt;
	private String srch_end_dt;
	private String srch_reg_operdiv;
	private String srch_keyword;
	private String srch_keyword_content;
	
	private String mode;
	private String manager_yn;
	
	/**********************************************************************
	 * 영문화 추가
	 **********************************************************************/
	private String cont_en;
	private String title_en;
	private String sel_lang;	//선택된 언어
	
	/**********************************************************************
	 * Getters and Setters Method
	 **********************************************************************/

	public int getSeqno() {
		return seqno;
	}

	public String getManager_yn() {
		return manager_yn;
	}

	public void setManager_yn(String manager_yn) {
		this.manager_yn = manager_yn;
	}

	public String getOrgnz_cd() {
		return orgnz_cd;
	}

	public void setOrgnz_cd(String orgnz_cd) {
		this.orgnz_cd = orgnz_cd;
	}

	public String[] getOrgnz_cds() {
		return orgnz_cds;
	}

	public void setOrgnz_cds(String[] orgnz_cds) {
		this.orgnz_cds = orgnz_cds;
	}

	public String getSec_yn() {
		return sec_yn;
	}

	public void setSec_yn(String sec_yn) {
		this.sec_yn = sec_yn;
	}

	public void setSeqno(int seqno) {
		this.seqno = seqno;
	}

	public String getAnnce_gbn() {
		return annce_gbn;
	}

	public void setAnnce_gbn(String annce_gbn) {
		this.annce_gbn = annce_gbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCont_type() {
		return cont_type;
	}

	public void setCont_type(String cont_type) {
		this.cont_type = cont_type;
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

	public String getRd_trgt1() {
		return rd_trgt1;
	}

	public void setRd_trgt1(String rd_trgt1) {
		this.rd_trgt1 = rd_trgt1;
	}

	public String getRd_trgt2() {
		return rd_trgt2;
	}

	public void setRd_trgt2(String rd_trgt2) {
		this.rd_trgt2 = rd_trgt2;
	}

	public String getAnnce_startday() {
		return annce_startday;
	}

	public void setAnnce_startday(String annce_startday) {
		this.annce_startday = annce_startday;
	}

	public String getAnnce_endday() {
		return annce_endday;
	}

	public void setAnnce_endday(String annce_endday) {
		this.annce_endday = annce_endday;
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

	public String getRegman_jikgup_nm() {
		return regman_jikgup_nm;
	}

	public void setRegman_jikgup_nm(String regman_jikgup_nm) {
		this.regman_jikgup_nm = regman_jikgup_nm;
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

	public String getSrch_reg_operdiv() {
		return srch_reg_operdiv;
	}

	public void setSrch_reg_operdiv(String srch_reg_operdiv) {
		this.srch_reg_operdiv = srch_reg_operdiv;
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

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public String getSel_lang() {
		return sel_lang;
	}
	public void setSel_lang(String sel_lang) {
		this.sel_lang = sel_lang;
	}
	
	public String getCont_en() {
		return cont_en;
	}

	public void setCont_en(String cont_en) {
		this.cont_en = cont_en;
	}

	public String getTitle_en() {
		return title_en;
	}

	public void setTitle_en(String title_en) {
		this.title_en = title_en;
	}

}