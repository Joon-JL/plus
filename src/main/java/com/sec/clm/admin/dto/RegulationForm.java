/**
* Project Name : 계약관리시스템
* File Name : RegulationForm.java
* Description : 프로세스&규정지침 Form
* Author : 신남원
* Date : 2010.09.01
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.admin.dto;

import com.sds.secframework.common.dto.CommonForm;

public class RegulationForm extends CommonForm {
	private String srch_one_rule_depth;
	private String srch_two_rule_depth;
	private String srch_cont;
	private String srch_rule_choice;
	private String one_rule_title_input;
	
	/**********************************************************************
	 * DB 변수
	 **********************************************************************/
	private int rule_no;
	private int up_rule_no;
	private int rule_depth;
	private int rule_srt;
	private String dn_rule_exist_yn;
	private String rule_title;
	private String rule_title_en;
	private String rule_cont;
	private String rule_cont_en;
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
	private String one_title;
	private String one_cont;
	private String two_title;
	private String two_cont;

	/**********************************************************************
	 * Getters and Setters Method
	 **********************************************************************/
	public String getSrch_one_rule_depth() {
		return srch_one_rule_depth;
	}
	public void setSrch_one_rule_depth(String srch_one_rule_depth) {
		this.srch_one_rule_depth = srch_one_rule_depth;
	}
	public String getSrch_two_rule_depth() {
		return srch_two_rule_depth;
	}
	public void setSrch_two_rule_depth(String srch_two_rule_depth) {
		this.srch_two_rule_depth = srch_two_rule_depth;
	}
	public String getSrch_cont() {
		return srch_cont;
	}
	public void setSrch_cont(String srch_cont) {
		this.srch_cont = srch_cont;
	}
	public String getSrch_rule_choice() {
		return srch_rule_choice;
	}
	public void setSrch_rule_choice(String srch_rule_choice) {
		this.srch_rule_choice = srch_rule_choice;
	}
	public String getOne_rule_title_input() {
		return one_rule_title_input;
	}
	public void setOne_rule_title_input(String one_rule_title_input) {
		this.one_rule_title_input = one_rule_title_input;
	}
	
	
	public void setRule_no(int rule_no) {
		this.rule_no = rule_no;
	}
	public void setUp_rule_no(int up_rule_no) {
		this.up_rule_no = up_rule_no;
	}
	public void setRule_depth(int rule_depth) {
		this.rule_depth = rule_depth;
	}
	public void setRule_srt(int rule_srt) {
		this.rule_srt = rule_srt;
	}
	public void setDn_rule_exist_yn(String dn_rule_exist_yn) {
		this.dn_rule_exist_yn = dn_rule_exist_yn;
	}
	public void setRule_title(String rule_title) {
		this.rule_title = rule_title;
	}
	public void setRule_title_en(String rule_title_en) {
		this.rule_title_en = rule_title_en;
	}
	public void setRule_cont(String rule_cont) {
		this.rule_cont = rule_cont;
	}
	public void setRule_cont_en(String rule_cont_en) {
		this.rule_cont_en = rule_cont_en;
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
	public int getRule_no() {
		return this.rule_no;
	}
	public int getUp_rule_no() {
		return this.up_rule_no;
	}
	public int getRule_depth() {
		return this.rule_depth;
	}
	public int getRule_srt() {
		return this.rule_srt;
	}
	public String getDn_rule_exist_yn() {
		return this.dn_rule_exist_yn;
	}
	public String getRule_title() {
		return this.rule_title;
	}
	public String getRule_cont() {
		return this.rule_cont;
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
	public String getOne_title() {
		return one_title;
	}
	public void setOne_title(String one_title) {
		this.one_title = one_title;
	}
	public String getOne_cont() {
		return one_cont;
	}
	public void setOne_cont(String one_cont) {
		this.one_cont = one_cont;
	}
	public String getTwo_title() {
		return two_title;
	}
	public void setTwo_title(String two_title) {
		this.two_title = two_title;
	}
	public String getTwo_cont() {
		return two_cont;
	}
	public void setTwo_cont(String two_cont) {
		this.two_cont = two_cont;
	}
	public String getRule_title_en() {
		return rule_title_en;
	}
	public String getRule_cont_en() {
		return rule_cont_en;
	}
	
}