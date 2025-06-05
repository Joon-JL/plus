/**
 * 
 * Project Name : 계약관리시스템 구축
 * File name	: CLMSCommonController.java
 * Description	: 공통코드관련 Controller
 * Author		: SDS
 * Date			: 2011. 08. 04
 * Copyright	: 
 */
package com.sec.common.clmscom.dto;

import com.sds.secframework.common.dto.CommonForm;

/**
 * Description	: 공통코드관련 Form 객체
 * Author 		: SDS
 * Date			: 2011. 08. 04
 * 
 */
public class OrgnzForm extends CommonForm {

	private String orgnz_cd;
	private String orgnz_nm;

	private String[] orgnz_cds; 
	
	private String srch_up_orgnz_cd;
	
	/**********************************************************************
	 * Getters and Setters Method
	 **********************************************************************/

	public String getOrgnz_cd() {
		return orgnz_cd;
	}

	public String getSrch_up_orgnz_cd() {
		return srch_up_orgnz_cd;
	}

	public void setSrch_up_orgnz_cd(String srch_up_orgnz_cd) {
		this.srch_up_orgnz_cd = srch_up_orgnz_cd;
	}

	public void setOrgnz_cd(String orgnz_cd) {
		this.orgnz_cd = orgnz_cd;
	}

	public String getOrgnz_nm() {
		return orgnz_nm;
	}

	public void setOrgnz_nm(String orgnz_nm) {
		this.orgnz_nm = orgnz_nm;
	}

	public String[] getOrgnz_cds() {
		return orgnz_cds;
	}

	public void setOrgnz_cds(String[] orgnz_cds) {
		this.orgnz_cds = orgnz_cds;
	}

}
