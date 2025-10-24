/**
* Project Name : 계약관리시스템
* File Name :ContractAgreeVO.java
* Description : 계약체결합의정보 VO
* Author : 심주완
* Date : 2011.09.30
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.manage.dto;

import com.sds.secframework.common.dto.CommonVO;

public class ContractAgreeVO extends CommonVO {
	
	/**********************************************************************
	 * DB 변수-대상테이블(TN_CLM_CNCLSN_AGREE) 
	 **********************************************************************/
	private String cntrt_id;
	private int agree_seqno;
	private String agreeman_id;
	private String agreeman_nm;
	private String agreeman_jikgup_nm;
	private String agreeman_dept_nm;
	private String agree_yn;
    private String agreeday;
    private String agree_cause;
	public String getCntrt_id() {
		return cntrt_id;
	}
	public void setCntrt_id(String cntrt_id) {
		this.cntrt_id = cntrt_id;
	}
	public int getAgree_seqno() {
		return agree_seqno;
	}
	public void setAgree_seqno(int agree_seqno) {
		this.agree_seqno = agree_seqno;
	}
	public String getAgreeman_id() {
		return agreeman_id;
	}
	public void setAgreeman_id(String agreeman_id) {
		this.agreeman_id = agreeman_id;
	}
	public String getAgreeman_nm() {
		return agreeman_nm;
	}
	public void setAgreeman_nm(String agreeman_nm) {
		this.agreeman_nm = agreeman_nm;
	}
	public String getAgreeman_jikgup_nm() {
		return agreeman_jikgup_nm;
	}
	public void setAgreeman_jikgup_nm(String agreeman_jikgup_nm) {
		this.agreeman_jikgup_nm = agreeman_jikgup_nm;
	}
	public String getAgreeman_dept_nm() {
		return agreeman_dept_nm;
	}
	public void setAgreeman_dept_nm(String agreeman_dept_nm) {
		this.agreeman_dept_nm = agreeman_dept_nm;
	}
	public String getAgree_yn() {
		return agree_yn;
	}
	public void setAgree_yn(String agree_yn) {
		this.agree_yn = agree_yn;
	}
	public String getAgreeday() {
		return agreeday;
	}
	public void setAgreeday(String agreeday) {
		this.agreeday = agreeday;
	}
	public String getAgree_cause() {
		return agree_cause;
	}
	public void setAgree_cause(String agree_cause) {
		this.agree_cause = agree_cause;
	}
	
}