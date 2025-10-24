/**
* Project Name : 계약관리시스템
* File Name :ConclusionDelayForm.java
* Description : 체결본미체결사유 Form
* Author : 심주완
* Date : 2011.09.05
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.manage.dto;

import java.util.List;
import com.sds.secframework.common.dto.CommonForm;

public class ConclusionDelayForm extends CommonForm {
	
	/**********************************************************************
	 * DB 변수-대상테이블(TN_CLM_CONTRACT_CNCLSNDLAY); 
	 **********************************************************************/
	private String cntrt_id;				//계약아이디
	private int dlay_seqno;				//지연일련번호
	private String chgebfr_cnclsn_plndday;	//변경전체결예정일
	private String chgeaft_cnclsn_plndday;	//변경후체결예정일
	private String dlay_cause;				//지연사유
	private String reg_id;					//등록자아이디
	private String reg_nm;					//등록자이름
	private String del_yn;					//삭제여부
	private String del_id;					//삭제자아이디
	private String del_nm;					//삭제자이름
	
	private String cntrt_id_arr[];			//계약아이디
	private int    dlay_seqno_arr[];		    //지연일련번호
	private String chgebfr_cnclsn_plndday_arr[];	//변경전체결예정일
	private String chgeaft_cnclsn_plndday_arr[];	//변경후체결예정일
	private String dlay_cause_arr[];				//지연사유
	
	private String reg_id_arr[];					//등록자아이디
	private String reg_nm_arr[];					//등록자이름
	private String del_yn_arr[];					//삭제여부
	private String del_id_arr[];					//삭제자아이디
	private String del_nm_arr[];					//삭제자이름
	
	/**********************************************************************
	 * 페이지사용변수 
	 **********************************************************************/
	private String chk_val;
	private String work_flag;
	
	private String chk_val_arr[];
	private String work_flag_arr[];
	
	//setter
	public void setCntrt_id(String cntrt_id) {
		this.cntrt_id = cntrt_id;
	}
	public void setDlay_seqno(int dlay_seqno) {
		this.dlay_seqno = dlay_seqno;
	}
	public void setChgebfr_cnclsn_plndday(String chgebfr_cnclsn_plndday) {
		this.chgebfr_cnclsn_plndday = chgebfr_cnclsn_plndday;
	}
	public void setChgeaft_cnclsn_plndday(String chgeaft_cnclsn_plndday) {
		this.chgeaft_cnclsn_plndday = chgeaft_cnclsn_plndday;
	}
	public void setDlay_cause(String dlay_cause) {
		this.dlay_cause = dlay_cause;
	}
	
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public void setReg_nm(String reg_nm) {
		this.reg_nm = reg_nm;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	
	public void setDel_id(String del_id) {
		this.del_id = del_id;
	}
	public void setDel_nm(String del_nm) {
		this.del_nm = del_nm;
	}
	public void setChk_val(String chk_val) {
		this.chk_val = chk_val;
	}
	public void setWork_flag(String work_flag) {
		this.work_flag = work_flag;
	}
	
	//배열setter
	public void setCntrt_id_arr(String[] cntrt_id_arr) {
		this.cntrt_id_arr = cntrt_id_arr;
	}
	public void setDlay_seqno_arr(int[] dlay_seqno_arr) {
		this.dlay_seqno_arr = dlay_seqno_arr;
	}
	public void setChgebfr_cnclsn_plndday_arr(String[] chgebfr_cnclsn_plndday_arr) {
		this.chgebfr_cnclsn_plndday_arr = chgebfr_cnclsn_plndday_arr;
	}
	public void setChgeaft_cnclsn_plndday_arr(String[] chgeaft_cnclsn_plndday_arr) {
		this.chgeaft_cnclsn_plndday_arr = chgeaft_cnclsn_plndday_arr;
	}
	public void setDlay_cause_arr(String[] dlay_cause_arr) {
		this.dlay_cause_arr = dlay_cause_arr;
	}
	
	public void setReg_id_arr(String[] reg_id_arr) {
		this.reg_id_arr = reg_id_arr;
	}
	public void setReg_nm_arr(String[] reg_nm_arr) {
		this.reg_nm_arr = reg_nm_arr;
	}
	public void setDel_yn_arr(String[] del_yn_arr) {
		this.del_yn_arr = del_yn_arr;
	}
	
	public void setDel_id_arr(String[] del_id_arr) {
		this.del_id_arr = del_id_arr;
	}
	public void setDel_nm_arr(String[] del_nm_arr) {
		this.del_nm_arr = del_nm_arr;
	}
	public void setChk_val_arr(String[] chk_val_arr) {
		this.chk_val_arr = chk_val_arr;
	}
	public void setWork_flag_arr(String[] work_flag_arr) {
		this.work_flag_arr = work_flag_arr;
	}
	
	//getter
	public String getCntrt_id() {
		return this.cntrt_id;
	}
	public int getDlay_seqno() {
		return this.dlay_seqno;
	}
	public String getChgebfr_cnclsn_plndday() {
		return this.chgebfr_cnclsn_plndday;
	}
	public String getChgeaft_cnclsn_plndday() {
		return this.chgeaft_cnclsn_plndday;
	}
	public String getDlay_cause() {
		return this.dlay_cause;
	}
	public String getReg_id() {
		return this.reg_id;
	}
	public String getReg_nm() {
		return this.reg_nm;
	}
	public String getDel_yn() {
		return this.del_yn;
	}
	public String getDel_id() {
		return this.del_id;
	}
	public String getDel_nm() {
		return this.del_nm;
	}
	public String getChk_val() {
		return this.chk_val;
	}
	public String getWork_flag() {
		return this.work_flag;
	}
	
	//배열getter
	public String[] getCntrt_id_arr() {
		return this.cntrt_id_arr;
	}
	public int[] getDlay_seqno_arr() {
		return this.dlay_seqno_arr;
	}
	public String[] getChgebfr_cnclsn_plndday_arr() {
		return this.chgebfr_cnclsn_plndday_arr;
	}
	public String[] getChgeaft_cnclsn_plndday_arr() {
		return this.chgeaft_cnclsn_plndday_arr;
	}
	public String[] getDlay_cause_arr() {
		return this.dlay_cause_arr;
	}
	public String[] getReg_id_arr() {
		return this.reg_id_arr;
	}
	public String[] getReg_nm_arr() {
		return this.reg_nm_arr;
	}
	public String[] getDel_yn_arr() {
		return this.del_yn_arr;
	}
	public String[] getDel_id_arr() {
		return this.del_id_arr;
	}
	public String[] getDel_nm_arr() {
		return this.del_nm_arr;
	}
	public String[] getChk_val_arr() {
		return this.chk_val_arr;
	}
	public String[] getWork_flag_arr() {
		return this.work_flag_arr;
	}
}