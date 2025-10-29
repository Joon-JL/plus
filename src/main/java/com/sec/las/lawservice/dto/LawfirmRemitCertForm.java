/**
 * Project Name : 법무시스템 이식
 * File name	: LawfirmRemitCertForm.java
 * Description	: 송금증 Data FORM(Model)
 * Author		: 박병주
 * Date			: 2011. 10
 * Copyright	:   
 */
package com.sec.las.lawservice.dto;


public class LawfirmRemitCertForm extends LwsCommonForm {
	private String remitcert_no;
	private String lawfirm_id;
	private String lawfirm_nm;
	private String remitcert_nm;
	private String remitday;
	private String invoice_no;
	private String cont;
	
	
	public String getLawfirm_nm() {
		return lawfirm_nm;
	}
	public void setLawfirm_nm(String lawfirm_nm) {
		this.lawfirm_nm = lawfirm_nm;
	}
	public String getRemitcert_no() {
		return remitcert_no;
	}
	public void setRemitcert_no(String remitcert_no) {
		this.remitcert_no = remitcert_no;
	}
	public String getLawfirm_id() {
		return lawfirm_id;
	}
	public void setLawfirm_id(String lawfirm_id) {
		this.lawfirm_id = lawfirm_id;
	}
	public String getRemitcert_nm() {
		return remitcert_nm;
	}
	public void setRemitcert_nm(String remitcert_nm) {
		this.remitcert_nm = remitcert_nm;
	}
	public String getRemitday() {
		return remitday;
	}
	public void setRemitday(String remitday) {
		this.remitday = remitday;
	}
	public String getInvoice_no() {
		return invoice_no;
	}
	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}

	
}
