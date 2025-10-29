/**
* Project Name : 계약관리시스템
* File Name : CustomerNewForm.java
* Description : 계약상대방 신규등록 Form
* Author : dawn
* Date : 2011.10.21
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.draft.dto;

import com.sds.secframework.common.dto.CommonForm;

public class CustomerNewForm extends CommonForm {
	
	private String srch_customer;//업체명 검색
	private String customer_gb;//여러 건 선택인지 한 건 선택인지 구분하기 위해
	private String nationRd;//국내인지 해외인지 구분
	
	private String customer_cd;
	private String customer_nm1;
	private String iv_nm1;
	private String gudun;
	private String gunam;
	private String dodun;
	private String donam;
	private String hqdun;
	private String hqnam;
	private String customer_sort;
	private String sec_cd;
	private String tax_no;
	private String erdat;
	private String street;
	private String cityn;
	private String stapr;
	private String nation;
	private String nation_nm;
	private String postalcode;
	private String telephone1;
	private String rep_nm;
	private String linbu;
	private String legst;
	private String legst_nm;
	private String busopen;
	private String busopen_nm;
	private String business_gubn;
	private String business_gubn_nm;
	private String ceonm_eng;
	private String add_eng;
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
	private String email;
	private String vatno;
	private String check_texNo;
	private String check_texNm;
	private String check_YN;
	private String srch_nation;
	private String srch_taxNo;
	private String hp_input;
	
	
	private String customer_nm1_H;
	private String linbu_H;
	
	private String gerp_cd;
	private String vendor_type;
	
	
	public String getVatno() {
		return vatno;
	}
	public void setVatno(String vatno) {
		this.vatno = vatno;
	}
	public String getGerp_cd() {
		return gerp_cd;
	}
	public void setGerp_cd(String gerp_cd) {
		this.gerp_cd = gerp_cd;
	}
	public String getVendor_type() {
		return vendor_type;
	}
	public void setVendor_type(String vendor_type) {
		this.vendor_type = vendor_type;
	}
	
	public String getLinbu_H() {
		return linbu_H;
	}
	public void setLinbu_H(String linbu_H) {
		this.linbu_H = linbu_H;
	}
	public String getCustomer_nm1_H() {
		return customer_nm1_H;
	}
	public void setCustomer_nm1_H(String customer_nm1_H) {
		this.customer_nm1_H = customer_nm1_H;
	}
	public String getHp_input() {
		return hp_input;
	}
	public void setHp_input(String hp_input) {
		this.hp_input = hp_input;
	}
	public String getSrch_nation() {
		return srch_nation;
	}
	public void setSrch_nation(String srch_nation) {
		this.srch_nation = srch_nation;
	}
	public String getSrch_taxNo() {
		return srch_taxNo;
	}
	public void setSrch_taxNo(String srch_taxNo) {
		this.srch_taxNo = srch_taxNo;
	}
	public String getCheck_YN() {
		return check_YN;
	}
	public void setCheck_YN(String check_YN) {
		this.check_YN = check_YN;
	}
	public String getCheck_texNo() {
		return check_texNo;
	}
	public void setCheck_texNo(String check_texNo) {
		this.check_texNo = check_texNo;
	}
	public String getCheck_texNm() {
		return check_texNm;
	}
	public void setCheck_texNm(String check_texNm) {
		this.check_texNm = check_texNm;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSrch_customer() {
		return srch_customer;
	}
	public void setSrch_customer(String srch_customer) {
		this.srch_customer = srch_customer;
	}
	public String getCustomer_gb() {
		return customer_gb;
	}
	public void setCustomer_gb(String customer_gb) {
		this.customer_gb = customer_gb;
	}
	public String getNationRd() {
		return nationRd;
	}
	public void setNationRd(String nationRd) {
		this.nationRd = nationRd;
	}
	
	
	public String getCustomer_cd() {
		return customer_cd;
	}
	public void setCustomer_cd(String customer_cd) {
		this.customer_cd = customer_cd;
	}
	public String getCustomer_nm1() {
		return customer_nm1;
	}
	public void setCustomer_nm1(String customer_nm1) {
		this.customer_nm1 = customer_nm1;
	}
	public String getIv_nm1() {
		return iv_nm1;
	}
	public void setIv_nm1(String iv_nm1) {
		this.iv_nm1 = iv_nm1;
	}
	public String getGudun() {
		return gudun;
	}
	public void setGudun(String gudun) {
		this.gudun = gudun;
	}
	public String getGunam() {
		return gunam;
	}
	public void setGunam(String gunam) {
		this.gunam = gunam;
	}
	public String getDodun() {
		return dodun;
	}
	public void setDodun(String dodun) {
		this.dodun = dodun;
	}
	public String getDonam() {
		return donam;
	}
	public void setDonam(String donam) {
		this.donam = donam;
	}
	public String getHqdun() {
		return hqdun;
	}
	public void setHqdun(String hqdun) {
		this.hqdun = hqdun;
	}
	public String getHqnam() {
		return hqnam;
	}
	public void setHqnam(String hqnam) {
		this.hqnam = hqnam;
	}
	public String getCustomer_sort() {
		return customer_sort;
	}
	public void setCustomer_sort(String customer_sort) {
		this.customer_sort = customer_sort;
	}
	public String getSec_cd() {
		return sec_cd;
	}
	public void setSec_cd(String sec_cd) {
		this.sec_cd = sec_cd;
	}
	public String getTax_no() {
		return tax_no;
	}
	public void setTax_no(String tax_no) {
		this.tax_no = tax_no;
	}
	public String getErdat() {
		return erdat;
	}
	public void setErdat(String erdat) {
		this.erdat = erdat;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCityn() {
		return cityn;
	}
	public void setCityn(String cityn) {
		this.cityn = cityn;
	}
	public String getStapr() {
		return stapr;
	}
	public void setStapr(String stapr) {
		this.stapr = stapr;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getNation_nm() {
		return nation_nm;
	}
	public void setNation_nm(String nation_nm) {
		this.nation_nm = nation_nm;
	}
	public String getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	public String getTelephone1() {
		return telephone1;
	}
	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}
	public String getRep_nm() {
		return rep_nm;
	}
	public void setRep_nm(String rep_nm) {
		this.rep_nm = rep_nm;
	}
	public String getLinbu() {
		return linbu;
	}
	public void setLinbu(String linbu) {
		this.linbu = linbu;
	}
	public String getLegst() {
		return legst;
	}
	public void setLegst(String legst) {
		this.legst = legst;
	}
	public String getLegst_nm() {
		return legst_nm;
	}
	public void setLegst_nm(String legst_nm) {
		this.legst_nm = legst_nm;
	}
	public String getBusopen() {
		return busopen;
	}
	public void setBusopen(String busopen) {
		this.busopen = busopen;
	}
	public String getBusopen_nm() {
		return busopen_nm;
	}
	public void setBusopen_nm(String busopen_nm) {
		this.busopen_nm = busopen_nm;
	}
	public String getBusiness_gubn() {
		return business_gubn;
	}
	public void setBusiness_gubn(String business_gubn) {
		this.business_gubn = business_gubn;
	}
	public String getBusiness_gubn_nm() {
		return business_gubn_nm;
	}
	public void setBusiness_gubn_nm(String business_gubn_nm) {
		this.business_gubn_nm = business_gubn_nm;
	}
	public String getCeonm_eng() {
		return ceonm_eng;
	}
	public void setCeonm_eng(String ceonm_eng) {
		this.ceonm_eng = ceonm_eng;
	}
	public String getAdd_eng() {
		return add_eng;
	}
	public void setAdd_eng(String add_eng) {
		this.add_eng = add_eng;
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
	
	String isModify;
	String customer_nm1_detail ;   
	String linbu_detail        ;
	String rep_nm_detail       ;
	String tax_no_detail       ;
	String nation_nm_detail    ;
	String stapr_detail        ;
	String cityn_detail        ;
	String street_detail       ;
	String postalcode_detail   ;
	String telephone1_detail   ;
	String hp_no_detail        ;
	String email_detail        ;
	String vatno_detail        ;
	String nation_detail       ;
	String customer_cd_detail  ;
	String legst_detail;
	String busopen_detail;
	String business_gubn_detail;

	
	public String getVatno_detail() {
		return vatno_detail;
	}
	public void setVatno_detail(String vatno_detail) {
		this.vatno_detail = vatno_detail;
	}
	public String getIsModify() {
		return isModify;
	}
	public void setIsModify(String isModify) {
		this.isModify = isModify;
	}
	public String getCustomer_nm1_detail() {
		return customer_nm1_detail;
	}
	public void setCustomer_nm1_detail(String customer_nm1_detail) {
		this.customer_nm1_detail = customer_nm1_detail;
	}
	public String getLinbu_detail() {
		return linbu_detail;
	}
	public void setLinbu_detail(String linbu_detail) {
		this.linbu_detail = linbu_detail;
	}
	public String getRep_nm_detail() {
		return rep_nm_detail;
	}
	public void setRep_nm_detail(String rep_nm_detail) {
		this.rep_nm_detail = rep_nm_detail;
	}
	public String getTax_no_detail() {
		return tax_no_detail;
	}
	public void setTax_no_detail(String tax_no_detail) {
		this.tax_no_detail = tax_no_detail;
	}
	public String getNation_nm_detail() {
		return nation_nm_detail;
	}
	public void setNation_nm_detail(String nation_nm_detail) {
		this.nation_nm_detail = nation_nm_detail;
	}
	public String getStapr_detail() {
		return stapr_detail;
	}
	public void setStapr_detail(String stapr_detail) {
		this.stapr_detail = stapr_detail;
	}
	public String getCityn_detail() {
		return cityn_detail;
	}
	public void setCityn_detail(String cityn_detail) {
		this.cityn_detail = cityn_detail;
	}
	public String getStreet_detail() {
		return street_detail;
	}
	public void setStreet_detail(String street_detail) {
		this.street_detail = street_detail;
	}
	public String getPostalcode_detail() {
		return postalcode_detail;
	}
	public void setPostalcode_detail(String postalcode_detail) {
		this.postalcode_detail = postalcode_detail;
	}
	public String getTelephone1_detail() {
		return telephone1_detail;
	}
	public void setTelephone1_detail(String telephone1_detail) {
		this.telephone1_detail = telephone1_detail;
	}
	public String getHp_no_detail() {
		return hp_no_detail;
	}
	public void setHp_no_detail(String hp_no_detail) {
		this.hp_no_detail = hp_no_detail;
	}
	public String getEmail_detail() {
		return email_detail;
	}
	public void setEmail_detail(String email_detail) {
		this.email_detail = email_detail;
	}
	public String getNation_detail() {
		return nation_detail;
	}
	public void setNation_detail(String nation_detail) {
		this.nation_detail = nation_detail;
	}
	public String getCustomer_cd_detail() {
		return customer_cd_detail;
	}
	public void setCustomer_cd_detail(String customer_cd_detail) {
		this.customer_cd_detail = customer_cd_detail;
	}
	public String getLegst_detail() {
		return legst_detail;
	}
	public void setLegst_detail(String legst_detail) {
		this.legst_detail = legst_detail;
	}
	public String getBusopen_detail() {
		return busopen_detail;
	}
	public void setBusopen_detail(String busopen_detail) {
		this.busopen_detail = busopen_detail;
	}
	public String getBusiness_gubn_detail() {
		return business_gubn_detail;
	}
	public void setBusiness_gubn_detail(String business_gubn_detail) {
		this.business_gubn_detail = business_gubn_detail;
	}
}
