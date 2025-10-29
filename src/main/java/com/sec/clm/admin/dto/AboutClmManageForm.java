/**
* Project Name : 계약관리시스템
* File Name : AboutClmManageForm.java
* Description : about CLM 관리 Form
* Author : dawn
* Date : 2011.10.05
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.admin.dto;

import com.sds.secframework.common.dto.CommonForm;

public class AboutClmManageForm extends CommonForm {
	
	private int seqno;
	private String title;
	private String title_en;
	private String title_fr;
	private String title_de;
	private String cont;
	private String menu_pos;
	private String menu_pos_en;
	private String menu_pos_fr;
	private String menu_pos_de;
	private String srch_cont;
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
	private String seqNoHtml;
	private String sel_lang;	//선택된 언어
	
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
	public String getTitle_en() {
		return title_en;
	}
	public void setTitle_en(String title_en) {
		this.title_en = title_en;
	}
	public String getTitle_fr() {
		return title_fr;
	}
	public void setTitle_fr(String title_fr) {
		this.title_fr = title_fr;
	}
	public String getTitle_de() {
		return title_de;
	}
	public void setTitle_de(String title_de) {
		this.title_de = title_de;
	}
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	public String getMenu_pos() {
		return menu_pos;
	}
	public void setMenu_pos(String menu_pos) {
		this.menu_pos = menu_pos;
	}	
	public String getMenu_pos_en() {
		return menu_pos_en;
	}
	public void setMenu_pos_en(String menu_pos_en) {
		this.menu_pos_en = menu_pos_en;
	}
	public String getMenu_pos_fr() {
		return menu_pos_fr;
	}
	public void setMenu_pos_fr(String menu_pos_fr) {
		this.menu_pos_fr = menu_pos_fr;
	}
	public String getMenu_pos_de() {
		return menu_pos_de;
	}
	public void setMenu_pos_de(String menu_pos_de) {
		this.menu_pos_de = menu_pos_de;
	}
	public String getSrch_cont() {
		return srch_cont;
	}
	public void setSrch_cont(String srch_cont) {
		this.srch_cont = srch_cont;
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
	public String getSeqNoHtml() {
		return seqNoHtml;
	}
	public void setSeqNoHtml(String seqNoHtml) {
		this.seqNoHtml = seqNoHtml;
	}
	public String getSel_lang() {
		return sel_lang;
	}
	public void setSel_lang(String sel_lang) {
		this.sel_lang = sel_lang;
	}

}
