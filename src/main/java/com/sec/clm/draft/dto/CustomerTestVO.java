/**
* Project Name : 계약관리시스템
* File Name : CustomerVO.java
* Description :  계약거래처 VO
* Author : dawn
* Date : 2011.09.29
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.draft.dto;

import com.sds.secframework.common.dto.CommonVO;

public class CustomerTestVO extends CommonVO {
	
	private String srch_gerp;//gerp 검색
	private String srch_customer;//업체명 검색
	private String customer_gb;//여러 건 선택인지 한 건 선택인지 구분하기 위해
	
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
	private String srch_nation;
	private String srch_taxNo;
	private String nation2;
	private String congbn;
	private String hp_no;
	private String gerp_cd;
	private String vendor_type;
	private String cntrt_top30_cus;
	
	public String getVatno() {
		return vatno;
	}
	public void setVatno(String vatno) {
		this.vatno = vatno;
	}
	public String getSrch_gerp() {
		return srch_gerp;
	}
	public void setSrch_gerp(String srch_gerp) {
		this.srch_gerp = srch_gerp;
	}
	public String getHp_no() {
		return hp_no;
	}
	public void setHp_no(String hp_no) {
		this.hp_no = hp_no;
	}
	public String getCongbn() {
		return congbn;
	}
	public void setCongbn(String congbn) {
		this.congbn = congbn;
	}
	public String getNation2() {
		return nation2;
	}
	public void setNation2(String nation2) {
		this.nation2 = nation2;
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
	public String getCntrt_top30_cus() {
		return cntrt_top30_cus;
	}
	public void setCntrt_top30_cus(String cntrt_top30_cus) {
		this.cntrt_top30_cus = cntrt_top30_cus;
	}
	
	private String[] customer_cds;
	private String[] customer_nm1s;
	private String[] iv_nm1s;
	private String[] guduns;
	private String[] gunams;
	private String[] doduns;
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
	public String[] getGerp_cds() {
		return gerp_cds;
	}
	public void setGerp_cds(String[] gerp_cds) {
		this.gerp_cds = gerp_cds;
	}
	public String[] getVendor_types() {
		return vendor_types;
	}
	public void setVendor_types(String[] vendor_types) {
		this.vendor_types = vendor_types;
	}
	
	private String[] donams;
	private String[] hqduns;
	private String[] hqnams;
	private String[] customer_sorts;
	private String[] sec_cds;
	private String[] tax_nos;
	private String[] erdats;
	private String[] streets;
	private String[] cityns;
	private String[] staprs;
	private String[] nations;
	private String[] nation_nms;
	private String[] postalcodes;
	private String[] telephone1s;
	private String[] rep_nms;
	private String[] linbus;
	private String[] legsts;
	private String[] legst_nms;
	private String[] busopens;
	private String[] busopen_nms;
	private String[] business_gubns;
	private String[] business_gubn_nms;
	private String[] ceonm_engs;
	private String[] add_engs;
	private String[] emails;
	private String[] vatnos;
	private String[] congbns;
	private String[] gerp_cds;
	private String[] vendor_types;
	
	
	public String[] getVatnos() {
		return vatnos;
	}
	public void setVatnos(String[] vatnos) {
		this.vatnos = vatnos;
	}
	public String[] getCongbns() {
		return congbns;
	}
	public void setCongbns(String[] congbns) {
		this.congbns = congbns;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String[] getEmails() {
		return emails;
	}
	public void setEmails(String[] emails) {
		this.emails = emails;
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
	
	
	public String[] getCustomer_cds() {
		return customer_cds;
	}
	public void setCustomer_cds(String[] customer_cds) {
		this.customer_cds = customer_cds;
	}
	public String[] getCustomer_nm1s() {
		return customer_nm1s;
	}
	public void setCustomer_nm1s(String[] customer_nm1s) {
		this.customer_nm1s = customer_nm1s;
	}
	public String[] getIv_nm1s() {
		return iv_nm1s;
	}
	public void setIv_nm1s(String[] iv_nm1s) {
		this.iv_nm1s = iv_nm1s;
	}
	public String[] getGuduns() {
		return guduns;
	}
	public void setGuduns(String[] guduns) {
		this.guduns = guduns;
	}
	public String[] getGunams() {
		return gunams;
	}
	public void setGunams(String[] gunams) {
		this.gunams = gunams;
	}
	public String[] getDoduns() {
		return doduns;
	}
	public void setDoduns(String[] doduns) {
		this.doduns = doduns;
	}
	public String[] getDonams() {
		return donams;
	}
	public void setDonams(String[] donams) {
		this.donams = donams;
	}
	public String[] getHqduns() {
		return hqduns;
	}
	public void setHqduns(String[] hqduns) {
		this.hqduns = hqduns;
	}
	public String[] getHqnams() {
		return hqnams;
	}
	public void setHqnams(String[] hqnams) {
		this.hqnams = hqnams;
	}
	public String[] getCustomer_sorts() {
		return customer_sorts;
	}
	public void setCustomer_sorts(String[] customer_sorts) {
		this.customer_sorts = customer_sorts;
	}
	public String[] getSec_cds() {
		return sec_cds;
	}
	public void setSec_cds(String[] sec_cds) {
		this.sec_cds = sec_cds;
	}
	public String[] getTax_nos() {
		return tax_nos;
	}
	public void setTax_nos(String[] tax_nos) {
		this.tax_nos = tax_nos;
	}
	public String[] getErdats() {
		return erdats;
	}
	public void setErdats(String[] erdats) {
		this.erdats = erdats;
	}
	public String[] getStreets() {
		return streets;
	}
	public void setStreets(String[] streets) {
		this.streets = streets;
	}
	public String[] getCityns() {
		return cityns;
	}
	public void setCityns(String[] cityns) {
		this.cityns = cityns;
	}
	public String[] getStaprs() {
		return staprs;
	}
	public void setStaprs(String[] staprs) {
		this.staprs = staprs;
	}
	public String[] getNations() {
		return nations;
	}
	public void setNations(String[] nations) {
		this.nations = nations;
	}
	public String[] getNation_nms() {
		return nation_nms;
	}
	public void setNation_nms(String[] nation_nms) {
		this.nation_nms = nation_nms;
	}
	public String[] getPostalcodes() {
		return postalcodes;
	}
	public void setPostalcodes(String[] postalcodes) {
		this.postalcodes = postalcodes;
	}
	public String[] getTelephone1s() {
		return telephone1s;
	}
	public void setTelephone1s(String[] telephone1s) {
		this.telephone1s = telephone1s;
	}
	public String[] getRep_nms() {
		return rep_nms;
	}
	public void setRep_nms(String[] rep_nms) {
		this.rep_nms = rep_nms;
	}
	public String[] getLinbus() {
		return linbus;
	}
	public void setLinbus(String[] linbus) {
		this.linbus = linbus;
	}
	public String[] getLegsts() {
		return legsts;
	}
	public void setLegsts(String[] legsts) {
		this.legsts = legsts;
	}
	public String[] getLegst_nms() {
		return legst_nms;
	}
	public void setLegst_nms(String[] legst_nms) {
		this.legst_nms = legst_nms;
	}
	public String[] getBusopens() {
		return busopens;
	}
	public void setBusopens(String[] busopens) {
		this.busopens = busopens;
	}
	public String[] getBusopen_nms() {
		return busopen_nms;
	}
	public void setBusopen_nms(String[] busopen_nms) {
		this.busopen_nms = busopen_nms;
	}
	public String[] getBusiness_gubns() {
		return business_gubns;
	}
	public void setBusiness_gubns(String[] business_gubns) {
		this.business_gubns = business_gubns;
	}
	public String[] getBusiness_gubn_nms() {
		return business_gubn_nms;
	}
	public void setBusiness_gubn_nms(String[] business_gubn_nms) {
		this.business_gubn_nms = business_gubn_nms;
	}
	public String[] getCeonm_engs() {
		return ceonm_engs;
	}
	public void setCeonm_engs(String[] ceonm_engs) {
		this.ceonm_engs = ceonm_engs;
	}
	public String[] getAdd_engs() {
		return add_engs;
	}
	public void setAdd_engs(String[] add_engs) {
		this.add_engs = add_engs;
	}
	
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
	String gerp_cd_detail;
	String vendor_type_detail;
	// 2014-03-18 Kevin Added.
	String contract_required;
	// 2014-09-26 Kevin added.
	private String srch_vendor_type;
	
	private String cntrt_top30_cus_detail;
	
	public String getVatno_detail() {
		return vatno_detail;
	}
	public void setVatno_detail(String vatno_detail) {
		this.vatno_detail = vatno_detail;
	}
	public String getGerp_cd_detail() {
		return gerp_cd_detail;
	}
	public void setGerp_cd_detail(String gerp_cd_detail) {
		this.gerp_cd_detail = gerp_cd_detail;
	}
	public String getVendor_type_detail() {
		return vendor_type_detail;
	}
	public void setVendor_type_detail(String vendor_type_detail) {
		this.vendor_type_detail = vendor_type_detail;
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
	// 2014-03-18 Kevin Added.
	public String getContract_required(){
		return contract_required;
	}
	public void setContract_required(String contract_required){
		this.contract_required = contract_required;
	}
	// 2014-09-25 Kevin added.
	public String getSrch_vendor_type() {
		return srch_vendor_type;
	}
	public void setSrch_vendor_type(String srchVendorType){
		this.srch_vendor_type = srchVendorType;
	}
	public String getCntrt_top30_cus_detail() {
		return cntrt_top30_cus_detail;
	}
	public void setCntrt_top30_cus_detail(String cntrt_top30_cus_detail) {
		this.cntrt_top30_cus_detail = cntrt_top30_cus_detail;
	}
	
	
}
