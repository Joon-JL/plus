/**
 * Project Name : 법무시스템 이식
 * File name	: LawyerEstimateVO.java
 * Description	: 변호사 관리 변호사 평가 Data VO(Model)
 * Author		: 박병주
 * Date			: 2011. 09.
 * Copyright	:   
 */
package com.sec.las.lawservice.dto;

import com.sds.secframework.common.dto.CommonVO;

public class LawyerEstimateVO extends CommonVO {
	// 변호사 번호
	private String lwr_no;
	// 평가명
	private String estmt_title;
	// 평가내역
	private String estmt_cont;

	public String getLwr_no() {
		return lwr_no;
	}

	public void setLwr_no(String lwr_no) {
		this.lwr_no = lwr_no;
	}

	public String getEstmt_title() {
		return estmt_title;
	}

	public void setEstmt_title(String estmt_title) {
		this.estmt_title = estmt_title;
	}

	public String getEstmt_cont() {
		return estmt_cont;
	}

	public void setEstmt_cont(String estmt_cont) {
		this.estmt_cont = estmt_cont;
	}

}
