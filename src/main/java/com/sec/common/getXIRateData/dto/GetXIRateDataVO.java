/**
 * Project Name : 법무시스템 이식
 * File name	: GetXIRateDataVO.java
 * Description	: XI 시스템에서 가져온 환율정보를 DB에 INSERT 한다.
 * Author		: 박병주
 * Date		: 2011. 11
 * Copyright	:  2011 by SAMSUNG. All rights reserved.  
 */
package com.sec.common.getXIRateData.dto;

import com.sds.secframework.common.dto.CommonVO;

public class GetXIRateDataVO extends CommonVO {
	
	private String co_cd;
	private String c_dt;
	private String currency;
	private String csrate;
	private String cbrate;
	private String ttsrate;
	private String ttbrate;
	private String arrate;
	private String ins_dt;
	private String ins_id;
	private String usdar;
	
	public String getCo_cd() {
		return co_cd;
	}
	public void setCo_cd(String co_cd) {
		this.co_cd = co_cd;
	}
	public String getC_dt() {
		return c_dt;
	}
	public void setC_dt(String c_dt) {
		this.c_dt = c_dt;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCsrate() {
		return csrate;
	}
	public void setCsrate(String csrate) {
		this.csrate = csrate;
	}
	public String getCbrate() {
		return cbrate;
	}
	public void setCbrate(String cbrate) {
		this.cbrate = cbrate;
	}
	public String getTtsrate() {
		return ttsrate;
	}
	public void setTtsrate(String ttsrate) {
		this.ttsrate = ttsrate;
	}
	public String getTtbrate() {
		return ttbrate;
	}
	public void setTtbrate(String ttbrate) {
		this.ttbrate = ttbrate;
	}
	public String getArrate() {
		return arrate;
	}
	public void setArrate(String arrate) {
		this.arrate = arrate;
	}
	public String getIns_dt() {
		return ins_dt;
	}
	public void setIns_dt(String ins_dt) {
		this.ins_dt = ins_dt;
	}
	public String getIns_id() {
		return ins_id;
	}
	public void setIns_id(String ins_id) {
		this.ins_id = ins_id;
	}
	public String getUsdar() {
		return usdar;
	}
	public void setUsdar(String usdar) {
		this.usdar = usdar;
	}	
 
}
