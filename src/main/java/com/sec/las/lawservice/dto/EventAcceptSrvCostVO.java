/**
 * Project Name : 법무시스템 이식
 * File name	: EventAcceptSrvCostVO.java
 * Description	: 용역비 VO(MODEL)
 * Author		: 박병주
 * Date			: 2011. 09
 * Copyright	:   
 */
package com.sec.las.lawservice.dto;

import java.math.BigDecimal;

import com.sds.secframework.common.dto.CommonVO;

public class EventAcceptSrvCostVO extends LwsCommonVO {
	private String acpt_no;
	private String intnl_dept_cd;
	private String grp_dept_cd;
	private String dept_nm;
	private BigDecimal totamt;	
	private BigDecimal srvc_amt;	
	private BigDecimal addtnl_amt;
	private BigDecimal dc_rate;	
	private BigDecimal plnd_remit_amt;	
	private BigDecimal usd_amt;	
	private BigDecimal tot_remit_amt;
	private String remitday;	
	private String event_no;
	private String reg_dt;	
	private String reg_id;	
	private String reg_nm;	
	private String mod_dt;	
	private String mod_id;	
	private String mod_nm;
	
	
	public BigDecimal getTot_remit_amt() {
		return tot_remit_amt;
	}
	public void setTot_remit_amt(BigDecimal tot_remit_amt) {
		this.tot_remit_amt = tot_remit_amt;
	}
	public String getAcpt_no() {
		return acpt_no;
	}
	public void setAcpt_no(String acpt_no) {
		this.acpt_no = acpt_no;
	}
	public String getIntnl_dept_cd() {
		return intnl_dept_cd;
	}
	public void setIntnl_dept_cd(String intnl_dept_cd) {
		this.intnl_dept_cd = intnl_dept_cd;
	}
	public String getGrp_dept_cd() {
		return grp_dept_cd;
	}
	public void setGrp_dept_cd(String grp_dept_cd) {
		this.grp_dept_cd = grp_dept_cd;
	}
	public String getDept_nm() {
		return dept_nm;
	}
	public void setDept_nm(String dept_nm) {
		this.dept_nm = dept_nm;
	}
	public BigDecimal getTotamt() {
		return totamt;
	}
	public void setTotamt(BigDecimal totamt) {
		this.totamt = totamt;
	}
	public BigDecimal getSrvc_amt() {
		return srvc_amt;
	}
	public void setSrvc_amt(BigDecimal srvc_amt) {
		this.srvc_amt = srvc_amt;
	}
	public BigDecimal getAddtnl_amt() {
		return addtnl_amt;
	}
	public void setAddtnl_amt(BigDecimal addtnl_amt) {
		this.addtnl_amt = addtnl_amt;
	}
	public BigDecimal getDc_rate() {
		return dc_rate;
	}
	public void setDc_rate(BigDecimal dc_rate) {
		this.dc_rate = dc_rate;
	}
	public BigDecimal getPlnd_remit_amt() {
		return plnd_remit_amt;
	}
	public void setPlnd_remit_amt(BigDecimal plnd_remit_amt) {
		this.plnd_remit_amt = plnd_remit_amt;
	}
	public BigDecimal getUsd_amt() {
		return usd_amt;
	}
	public void setUsd_amt(BigDecimal usd_amt) {
		this.usd_amt = usd_amt;
	}


	public String getRemitday() {
		return remitday;
	}
	public void setRemitday(String remitday) {
		this.remitday = remitday;
	}
	public String getEvent_no() {
		return event_no;
	}
	public void setEvent_no(String event_no) {
		this.event_no = event_no;
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
}
