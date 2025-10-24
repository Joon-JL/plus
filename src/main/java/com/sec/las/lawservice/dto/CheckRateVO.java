package com.sec.las.lawservice.dto;

import java.math.BigDecimal;

import com.sds.secframework.common.dto.CommonForm;

public class CheckRateVO extends CommonForm {
	// 환율정보 조회날짜
	private String c_dt;
	private String crrncy_unit;
	public String getC_dt() {
		return c_dt;
	}
	public void setC_dt(String c_dt) {
		this.c_dt = c_dt;
	}
	public String getCrrncy_unit() {
		return crrncy_unit;
	}
	public void setCrrncy_unit(String crrncy_unit) {
		this.crrncy_unit = crrncy_unit;
	}

}
