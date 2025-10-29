/**
* Project Name : 계약관리시스템
* File Name : CustomerNewVO.java
* Description :  계약상대방 신규등록 VO
* Author : dawn
* Date : 2011.10.21
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.draft.dto;

import com.sds.secframework.common.dto.CommonVO;

public class ExcelForm extends CommonVO {
	
	private String tableNm;
	private String fileNm;
	private String runIp;
	private String workSheetNm;
	private String vendorType;
	// 검색어
	private String srch_word;
	// 검색어
	private String srch_word2;
	// 검색어
	private String srch_word3;
	// 조회일자
	private String srch_start_dt;
	// 조회종료일자
	private String srch_end_dt;
	
	
	
	public String getTableNm() {
		return tableNm;
	}
	public void setTableNm(String tableNm) {
		this.tableNm = tableNm;
	}
	public String getFileNm() {
		return fileNm;
	}
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
	public String getRunIp() {
		return runIp;
	}
	public void setRunIp(String runIp) {
		this.runIp = runIp;
	}
	public String getWorkSheetNm() {
		return workSheetNm;
	}
	public void setWorkSheetNm(String workSheetNm) {
		this.workSheetNm = workSheetNm;
	}
	public String getVendorType() {
		return vendorType;
	}
	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}
	public String getSrch_word() {
		return srch_word;
	}
	public void setSrch_word(String srch_word) {
		this.srch_word = srch_word;
	}
	public String getSrch_word2() {
		return srch_word2;
	}
	public void setSrch_word2(String srch_word2) {
		this.srch_word2 = srch_word2;
	}
	public String getSrch_word3() {
		return srch_word3;
	}
	public void setSrch_word3(String srch_word3) {
		this.srch_word3 = srch_word3;
	}
	public String getSrch_start_dt() {
		return srch_start_dt;
	}
	public void setSrch_start_dt(String srch_start_dt) {
		this.srch_start_dt = srch_start_dt;
	}
	public String getSrch_end_dt() {
		return srch_end_dt;
	}
	public void setSrch_end_dt(String srch_end_dt) {
		this.srch_end_dt = srch_end_dt;
	}
	
}
