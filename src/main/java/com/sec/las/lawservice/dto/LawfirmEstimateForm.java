/**
 * Project Name : 법무시스템 이식
 * File name	: LawfirmEstimateForm.java
 * Description	: 로펌평가  Data Form(Model)
 * Author		: 박병주
 * Date			: 2011. 08. 20
 * Copyright	:   
 */
package com.sec.las.lawservice.dto;

import com.sds.secframework.common.dto.CommonForm;

public class LawfirmEstimateForm extends CommonForm {
	/**********************************************************************
	 * DB  변수
	 **********************************************************************/	
	/** 평가_번호 */
	private String estmt_no;
	/** 로펌_ID */
	private String lawfirm_id;
	/** 평가_이름 */
	private String estmt_name;
	/** 평가_내용 */
	private String estmt_cont;
	/** 등록_일시 */
	private String reg_dt;
	/** 등록자_ID */
	private String reg_id;
	/** 등록자_명 */
	private String reg_nm;
	
	/**********************************************************************
	 * DB 외  변수
	 **********************************************************************/	
	/** 파일_ID */
	private String file_id;
	/** 파일_경로 */
	private String file_path;
	/** 원본_파일_명 */
	private String org_file_nm;
	/** 파일_크기 */
	private String file_sz;
	/** 참조_키 */
	private String ref_key;
	/** 다음 평가 번호 */
	private String next_estmt_no;
	
	/**********************************************************************
	 * Getters and Setters Method
	 **********************************************************************/	
	public String getFile_id() {
		return file_id;
	}
	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getRef_key() {
		return ref_key;
	}
	public void setRef_key(String ref_key) {
		this.ref_key = ref_key;
	}
	public String getOrg_file_nm() {
		return org_file_nm;
	}
	public void setOrg_file_nm(String org_file_nm) {
		this.org_file_nm = org_file_nm;
	}
	public String getFile_sz() {
		return file_sz;
	}
	public void setFile_sz(String file_sz) {
		this.file_sz = file_sz;
	}
	public String getEstmt_no() {
		return estmt_no;
	}
	public void setEstmt_no(String estmt_no) {
		this.estmt_no = estmt_no;
	}
	public String getNext_estmt_no() {
		return next_estmt_no;
	}
	public void setNext_estmt_no(String next_estmt_no) {
		this.next_estmt_no = next_estmt_no;
	}
	public String getLawfirm_id() {
		return lawfirm_id;
	}
	public void setLawfirm_id(String lawfirm_id) {
		this.lawfirm_id = lawfirm_id;
	}
	public String getEstmt_name() {
		return estmt_name;
	}
	public void setEstmt_name(String estmt_name) {
		this.estmt_name = estmt_name;
	}
	public String getEstmt_cont() {
		return estmt_cont;
	}
	public void setEstmt_cont(String estmt_cont) {
		this.estmt_cont = estmt_cont;
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
}
