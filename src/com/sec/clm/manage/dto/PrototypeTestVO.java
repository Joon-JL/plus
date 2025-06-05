package com.sec.clm.manage.dto;

import com.sds.secframework.common.dto.CommonVO;

public class PrototypeTestVO extends CommonVO {
	
	/** 계약_ID : required - YYYY + MM + DD + HH24 + MI + SS + SSS */
	private String cntrt_id;
	/** 이행_일련번호 : required */
	private int exec_seqno;
	/** 이행_내용 : required */
	private String exec_cont;
	/** 이행_예정일 : required - YYYYMMDD */
	private String exec_plndday;
	/** 이행_상태 : required - 공통코드 (시스템 : CLM, 그룹코드 : C031 - 02 : 미확인, 03 : 확인중, 04 :  완료)*/
	private String exec_status;
	/** 등록_일시 : required */
	private String reg_dt;
	/** 등록자_ID : required */
	private String reg_id;
	/** 등록자_명 */
	private String reg_nm;
	/** 수정_일시 */
	private String mod_dt;
	/** 수정자_ID */
	private String mod_id;
	/** 수정자_명 */
	private String mod_nm;
	/** 삭제_여부 : required */
	private String del_yn;
	/** 삭제_일시 */
	private String del_dt;
	/** 삭제자_ID */
	private String del_id;
	/** 삭제자_명 */
	private String del_nm;
	
	/** 계약관리_계약별_검토*/
	/** 검토의뢰_ID required : 국내해외구분(1) + YY + MM + DD + SEQ(3) */
	private String cnsdreq_id;
	/** 검토부서_ID required : 공통코드 (시스템 : CLM, 그룹코드 : C039 - C03901 :법무팀, C03902 :IP센터 ) */
	private String cnsd_dept;	

	/** 배열 Property */
	/** 계약_ID : required - YYYY + MM + DD + HH24 + MI + SS + SSS */
	private String cntrt_id_arr[];
	/** 이행_일련번호 : required */
	private int exec_seqno_arr[];
	/** 이행_내용 : required */
	private String exec_cont_arr[];
	/** 이행_예정일 : required - YYYYMMDD */
	private String exec_plndday_arr[];
	/** 이행_상태 : required - 공통코드 (시스템 : CLM, 그룹코드 : C031 - 02 : 미확인, 03 : 확인중, 04 :  완료)*/
	private String exec_status_arr[];
	
	private int exec_chk_arr[];
	/**
	 * getter/setter method
	 */
	public String getCntrt_id() {
		return cntrt_id;
	}
	public void setCntrt_id(String cntrt_id) {
		this.cntrt_id = cntrt_id;
	}
	public int getExec_seqno() {
		return exec_seqno;
	}
	public void setExec_seqno(int exec_seqno) {
		this.exec_seqno = exec_seqno;
	}
	public String getExec_cont() {
		return exec_cont;
	}
	public void setExec_cont(String exec_cont) {
		this.exec_cont = exec_cont;
	}
	public String getExec_plndday() {
		return exec_plndday;
	}
	public void setExec_plndday(String exec_plndday) {
		this.exec_plndday = exec_plndday;
	}
	public String getExec_status() {
		return exec_status;
	}
	public void setExec_status(String exec_status) {
		this.exec_status = exec_status;
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
	public String getMod_dt() {
		return mod_dt;
	}
	public void setMod_dt(String mod_dt) {
		this.mod_dt = mod_dt;
	}
	public String getMod_id() {
		return mod_id;
	}
	public void setMod_id(String mod_id) {
		this.mod_id = mod_id;
	}
	public String getMod_nm() {
		return mod_nm;
	}
	public void setMod_nm(String mod_nm) {
		this.mod_nm = mod_nm;
	}
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	public String getDel_dt() {
		return del_dt;
	}
	public void setDel_dt(String del_dt) {
		this.del_dt = del_dt;
	}
	public String getDel_id() {
		return del_id;
	}
	public void setDel_id(String del_id) {
		this.del_id = del_id;
	}
	public String getDel_nm() {
		return del_nm;
	}
	public void setDel_nm(String del_nm) {
		this.del_nm = del_nm;
	}
	public String[] getCntrt_id_arr() {
		return cntrt_id_arr;
	}
	public void setCntrt_id_arr(String[] cntrt_id_arr) {
		this.cntrt_id_arr = cntrt_id_arr;
	}
	public int[] getExec_seqno_arr() {
		return exec_seqno_arr;
	}
	public void setExec_seqno_arr(int[] exec_seqno_arr) {
		this.exec_seqno_arr = exec_seqno_arr;
	}
	public String[] getExec_cont_arr() {
		return exec_cont_arr;
	}
	public void setExec_cont_arr(String[] exec_cont_arr) {
		this.exec_cont_arr = exec_cont_arr;
	}
	public String[] getExec_plndday_arr() {
		return exec_plndday_arr;
	}
	public void setExec_plndday_arr(String[] exec_plndday_arr) {
		this.exec_plndday_arr = exec_plndday_arr;
	}
	public String[] getExec_status_arr() {
		return exec_status_arr;
	}
	public void setExec_status_arr(String[] exec_status_arr) {
		this.exec_status_arr = exec_status_arr;
	}
	public int[] getExec_chk_arr() {
		return exec_chk_arr;
	}
	public void setExec_chk_arr(int[] exec_chk_arr) {
		this.exec_chk_arr = exec_chk_arr;
	}
	public String getCnsdreq_id() {
		return cnsdreq_id;
	}
	public void setCnsdreq_id(String cnsdreq_id) {
		this.cnsdreq_id = cnsdreq_id;
	}
	public String getCnsd_dept() {
		return cnsd_dept;
	}
	public void setCnsd_dept(String cnsd_dept) {
		this.cnsd_dept = cnsd_dept;
	}
		
}