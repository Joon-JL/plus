/**
* Project Name : 계약관리시스템
* File Name : RegulationVO.java
* Description : 프로세스&규정지침 VO
* Author : 신남원
* Date : 2010.09.01
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.rule.dto;

import com.sds.secframework.common.dto.CommonVO;

public class RegulationVO extends CommonVO {
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
	private int cnt;
	private int up_rule_no_rn;
	
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
	public String getRule_title_en() {
		return this.rule_title_en;
	}
	public String getRule_cont() {
		return this.rule_cont;
	}
	public String getRule_cont_en() {
		return this.rule_cont_en;
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
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getUp_rule_no_rn() {
		return up_rule_no_rn;
	}
	public void setUp_rule_no_rn(int up_rule_no_rn) {
		this.up_rule_no_rn = up_rule_no_rn;
	}
}