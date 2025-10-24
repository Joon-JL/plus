/**
* Project Name : 계약관리시스템
* File Name :ChooseContractTypeForm.java
* Description : 계약선택팝업 Form
* Author : 심주완
* Date : 2011.09.25
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.manage.dto;

import java.util.List;
import com.sds.secframework.common.dto.CommonForm;

public class ChooseClientForm extends CommonForm {
	
	/**********************************************************************
	 * DB 변수-대상테이블(TN_CLM_CONT_CNSDREQ,TN_CLM_CONTRACT_CNSD,TN_CLM_CONTRACT_MASTER,TN_CLM_CONTRACT_AUTHREQ); 
	 **********************************************************************/
	private String cnsdreq_id;	
	private String cntrt_id;
    private int    demnd_seqno;
    private String demndman_id;
    private String demndman_nm;
    private String demndman_dept_nm;
    private String trgtman_id;
    private String trgtman_nm;
    private String trgtman_dept_nm;
    private String rd_auth;
    private String auth_startday;
    private String auth_endday;
    private String demnd_status;
    private String demnd_dt;
    private String hndl_dt;
    private String hndl_cont;
    private String hndlman_nm;
    private String hndlman_id;
    private String chose_client;
	
    public String getCnsdreq_id() {
		return cnsdreq_id;
	}
	public void setCnsdreq_id(String cnsdreq_id) {
		this.cnsdreq_id = cnsdreq_id;
	}
	public String getCntrt_id() {
		return cntrt_id;
	}
	public void setCntrt_id(String cntrt_id) {
		this.cntrt_id = cntrt_id;
	}
	public int getDemnd_seqno() {
		return demnd_seqno;
	}
	public void setDemnd_seqno(int demnd_seqno) {
		this.demnd_seqno = demnd_seqno;
	}
	public String getDemndman_id() {
		return demndman_id;
	}
	public void setDemndman_id(String demndman_id) {
		this.demndman_id = demndman_id;
	}
	public String getDemndman_nm() {
		return demndman_nm;
	}
	public void setDemndman_nm(String demndman_nm) {
		this.demndman_nm = demndman_nm;
	}
	public String getDemndman_dept_nm() {
		return demndman_dept_nm;
	}
	public void setDemndman_dept_nm(String demndman_dept_nm) {
		this.demndman_dept_nm = demndman_dept_nm;
	}
	public String getTrgtman_id() {
		return trgtman_id;
	}
	public void setTrgtman_id(String trgtman_id) {
		this.trgtman_id = trgtman_id;
	}
	public String getTrgtman_nm() {
		return trgtman_nm;
	}
	public void setTrgtman_nm(String trgtman_nm) {
		this.trgtman_nm = trgtman_nm;
	}
	public String getTrgtman_dept_nm() {
		return trgtman_dept_nm;
	}
	public void setTrgtman_dept_nm(String trgtman_dept_nm) {
		this.trgtman_dept_nm = trgtman_dept_nm;
	}
	public String getRd_auth() {
		return rd_auth;
	}
	public void setRd_auth(String rd_auth) {
		this.rd_auth = rd_auth;
	}
	public String getAuth_startday() {
		return auth_startday;
	}
	public void setAuth_startday(String auth_startday) {
		this.auth_startday = auth_startday;
	}
	public String getAuth_endday() {
		return auth_endday;
	}
	public void setAuth_endday(String auth_endday) {
		this.auth_endday = auth_endday;
	}
	public String getDemnd_status() {
		return demnd_status;
	}
	public void setDemnd_status(String demnd_status) {
		this.demnd_status = demnd_status;
	}
	public String getDemnd_dt() {
		return demnd_dt;
	}
	public void setDemnd_dt(String demnd_dt) {
		this.demnd_dt = demnd_dt;
	}
	public String getHndl_dt() {
		return hndl_dt;
	}
	public void setHndl_dt(String hndl_dt) {
		this.hndl_dt = hndl_dt;
	}
	public String getHndl_cont() {
		return hndl_cont;
	}
	public void setHndl_cont(String hndl_cont) {
		this.hndl_cont = hndl_cont;
	}
	public String getHndlman_nm() {
		return hndlman_nm;
	}
	public void setHndlman_nm(String hndlman_nm) {
		this.hndlman_nm = hndlman_nm;
	}
	public String getHndlman_id() {
		return hndlman_id;
	}
	public void setHndlman_id(String hndlman_id) {
		this.hndlman_id = hndlman_id;
	}
	public String getChose_client() {
		return chose_client;
	}
	public void setChose_client(String chose_client) {
		this.chose_client = chose_client;
	}
	
}