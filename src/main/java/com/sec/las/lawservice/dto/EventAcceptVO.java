/**
 * Project Name : 법무시스템 이식
 * File name	: EventAcceptVO.java
 * Description	: 등록 사건 리스트를 취득한다.
 * Author		: 박병주
 * Date			: 2011. 09
 * Copyright	:   
 */
package com.sec.las.lawservice.dto;

import java.math.BigDecimal;

public class EventAcceptVO extends LwsCommonVO {
	
	private String acpt_no;	
	private String event_no;	
	private String acptday;
	private String lawfirm_id;
	private String claimday;
	private String claim_amt;	
	private String crrncy_unit;	
	private String claim_cont;	
	private String invoice_no;
	private String srvcstartday;	
	private String srvcendday;	
	private String remit_amt;
	private String remitplndday;	
	private String remitday;
	private String unpayday;	
	private String review_yn;	
	private String review_cont;	
	private String lawfirm_nm;
	private String lawsuit_trgt;
	private String lawsuit_trgt_nm;	
	private String upfile_yn;
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
	
	private String c_dt;
	
	// 부서별 용역비 입력정보
	private String[] intnl_dept_cd;
	private String[] grp_dept_cd;
	private String[] dept_nm;
	private BigDecimal[] totamt;	
	private BigDecimal[] srvc_amt;	
	private BigDecimal[] addtnl_amt;
	private BigDecimal[] dc_rate;	
	private BigDecimal[] plnd_remit_amt;	
	private BigDecimal[] usd_amt;	
	private String tot_remit_amt;
	
	// 변호사 정보
	private String[] lwr_no;
	
	private String event_nm;
	private String event_gbn1;
	private String event_gbn2;
			
	public String getEvent_nm() {
		return event_nm;
	}
	public void setEvent_nm(String event_nm) {
		this.event_nm = event_nm;
	}
	public String getEvent_gbn1() {
		return event_gbn1;
	}
	public void setEvent_gbn1(String event_gbn1) {
		this.event_gbn1 = event_gbn1;
	}
	public String getEvent_gbn2() {
		return event_gbn2;
	}
	public void setEvent_gbn2(String event_gbn2) {
		this.event_gbn2 = event_gbn2;
	}
	
	public String[] getLwr_no() {
		return lwr_no;
	}
	public void setLwr_no(String[] lwr_no) {
		this.lwr_no = lwr_no;
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
	public String[] getIntnl_dept_cd() {
		return intnl_dept_cd;
	}
	public void setIntnl_dept_cd(String[] intnl_dept_cd) {
		this.intnl_dept_cd = intnl_dept_cd;
	}
	public String[] getGrp_dept_cd() {
		return grp_dept_cd;
	}
	public void setGrp_dept_cd(String[] grp_dept_cd) {
		this.grp_dept_cd = grp_dept_cd;
	}
	public String[] getDept_nm() {
		return dept_nm;
	}
	public void setDept_nm(String[] dept_nm) {
		this.dept_nm = dept_nm;
	}
	public BigDecimal[] getTotamt() {
		return totamt;
	}
	public void setTotamt(BigDecimal[] totamt) {
		this.totamt = totamt;
	}
	public BigDecimal[] getSrvc_amt() {
		return srvc_amt;
	}
	public void setSrvc_amt(BigDecimal[] srvc_amt) {
		this.srvc_amt = srvc_amt;
	}
	public BigDecimal[] getAddtnl_amt() {
		return addtnl_amt;
	}
	public void setAddtnl_amt(BigDecimal[] addtnl_amt) {
		this.addtnl_amt = addtnl_amt;
	}
	public BigDecimal[] getDc_rate() {
		return dc_rate;
	}
	public void setDc_rate(BigDecimal[] dc_rate) {
		this.dc_rate = dc_rate;
	}
	public BigDecimal[] getPlnd_remit_amt() {
		return plnd_remit_amt;
	}
	public void setPlnd_remit_amt(BigDecimal[] plnd_remit_amt) {
		this.plnd_remit_amt = plnd_remit_amt;
	}
	public BigDecimal[] getUsd_amt() {
		return usd_amt;
	}
	public void setUsd_amt(BigDecimal[] usd_amt) {
		this.usd_amt = usd_amt;
	}

	public String getTot_remit_amt() {
		return tot_remit_amt;
	}
	public void setTot_remit_amt(String tot_remit_amt) {
		this.tot_remit_amt = tot_remit_amt;
	}
	public String getC_dt() {
		return c_dt;
	}
	public void setC_dt(String c_dt) {
		this.c_dt = c_dt;
	}
	public String getAcpt_no() {
		return acpt_no;
	}
	public void setAcpt_no(String acpt_no) {
		this.acpt_no = acpt_no;
	}
	public String getEvent_no() {
		return event_no;
	}
	public void setEvent_no(String event_no) {
		this.event_no = event_no;
	}
	public String getAcptday() {
		return acptday;
	}
	public void setAcptday(String acptday) {
		this.acptday = acptday;
	}
	public String getLawfirm_id() {
		return lawfirm_id;
	}
	public void setLawfirm_id(String lawfirm_id) {
		this.lawfirm_id = lawfirm_id;
	}
	public String getClaimday() {
		return claimday;
	}
	public void setClaimday(String claimday) {
		this.claimday = claimday;
	}
	public String getClaim_amt() {
		return claim_amt;
	}
	public void setClaim_amt(String claim_amt) {
		this.claim_amt = claim_amt;
	}
	public String getCrrncy_unit() {
		return crrncy_unit;
	}
	public void setCrrncy_unit(String crrncy_unit) {
		this.crrncy_unit = crrncy_unit;
	}
	public String getClaim_cont() {
		return claim_cont;
	}
	public void setClaim_cont(String claim_cont) {
		this.claim_cont = claim_cont;
	}
	public String getInvoice_no() {
		return invoice_no;
	}
	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}
	public String getSrvcstartday() {
		return srvcstartday;
	}
	public void setSrvcstartday(String srvcstartday) {
		this.srvcstartday = srvcstartday;
	}
	public String getSrvcendday() {
		return srvcendday;
	}
	public void setSrvcendday(String srvcendday) {
		this.srvcendday = srvcendday;
	}
	public String getRemit_amt() {
		return remit_amt;
	}
	public void setRemit_amt(String remit_amt) {
		this.remit_amt = remit_amt;
	}
	public String getRemitplndday() {
		return remitplndday;
	}
	public void setRemitplndday(String remitplndday) {
		this.remitplndday = remitplndday;
	}
	public String getRemitday() {
		return remitday;
	}
	public void setRemitday(String remitday) {
		this.remitday = remitday;
	}
	public String getUnpayday() {
		return unpayday;
	}
	public void setUnpayday(String unpayday) {
		this.unpayday = unpayday;
	}
	public String getReview_yn() {
		return review_yn;
	}
	public void setReview_yn(String review_yn) {
		this.review_yn = review_yn;
	}
	public String getReview_cont() {
		return review_cont;
	}
	public void setReview_cont(String review_cont) {
		this.review_cont = review_cont;
	}
	public String getLawfirm_nm() {
		return lawfirm_nm;
	}
	public void setLawfirm_nm(String lawfirm_nm) {
		this.lawfirm_nm = lawfirm_nm;
	}
	public String getLawsuit_trgt() {
		return lawsuit_trgt;
	}
	public void setLawsuit_trgt(String lawsuit_trgt) {
		this.lawsuit_trgt = lawsuit_trgt;
	}
	public String getLawsuit_trgt_nm() {
		return lawsuit_trgt_nm;
	}
	public void setLawsuit_trgt_nm(String lawsuit_trgt_nm) {
		this.lawsuit_trgt_nm = lawsuit_trgt_nm;
	}
	public String getUpfile_yn() {
		return upfile_yn;
	}
	public void setUpfile_yn(String upfile_yn) {
		this.upfile_yn = upfile_yn;
	}
	
}
