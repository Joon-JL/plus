/**
* Project Name : 계약관리시스템
* File Name :ChooseContractTypeForm.java
* Description : 계약유형선택팝업 Form
* Author : 심주완
* Date : 2011.09.25
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.manage.dto;

import java.util.List;
import com.sds.secframework.common.dto.CommonForm;

public class ChooseContractTypeForm extends CommonForm {
	
	/**********************************************************************
	 * DB 변수-대상테이블(TC_CLM_CONTRACTTYPE_CD); 
	 **********************************************************************/
	private String type_cd;			//유형코드
	private String up_type_cd;		//상위유형코드
	private String cd_nm;			//코드명
	private String cd_nm_abbr;		//코드명약어
	private String use_yn;			//사용여부
	private String forword_from;			// 호출 메소드
		
	public String getForword_from() {
		return forword_from;
	}
	public void setForword_from(String forword_from) {
		this.forword_from = forword_from;
	}
	public String getType_cd() {
		return type_cd;
	}
	public void setType_cd(String type_cd) {
		this.type_cd = type_cd;
	}
	public String getUp_type_cd() {
		return up_type_cd;
	}
	public void setUp_type_cd(String up_type_cd) {
		this.up_type_cd = up_type_cd;
	}
	public String getCd_nm() {
		return cd_nm;
	}
	public void setCd_nm(String cd_nm) {
		this.cd_nm = cd_nm;
	}
	public String getCd_nm_abbr() {
		return cd_nm_abbr;
	}
	public void setCd_nm_abbr(String cd_nm_abbr) {
		this.cd_nm_abbr = cd_nm_abbr;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	
}