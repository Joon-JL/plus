package com.sec.clm.manage.dto;

import java.math.BigDecimal;

import com.sds.secframework.common.dto.CommonVO;

public class ExecutionVO extends CommonVO {

	/** 계약_ID : required - YYYY + MM + DD + HH24 + MI + SS + SSS */
	private String cntrt_id;
	private String cntrt_no;
	/** 계약_담당자_ID */
	private String cntrt_respman_id;
	/** 이행_일련번호 : required */
	private int exec_seqno;
	/** 이행_내용 : required */
	private String exec_cont;
	/** 이행_예정일 : required - YYYYMMDD */
	private String exec_plndday;
	/**
	 * 이행_상태 : required - 공통코드 (시스템 : CLM, 그룹코드 : C031 - 02 : 미확인, 03 : 확인중, 04
	 * : 완료)
	 */
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
	/** 참조이행번호 */
	private String exec_up_no;
	/** 참조정렬번호 */
	private String exec_srt;
	/** 변경flag */
	private String exec_mod_flag;

	private String max_exec_num;

	private String payment_gbn;
	/** 소속부서 */
	private String blngt_orgnz;
	/** 이력정보 리스트 기준 부서 */
	private String dept_div;

	private String pageGbn;

	public String getPageGbn() {
		return pageGbn;
	}

	public void setPageGbn(String pageGbn) {
		this.pageGbn = pageGbn;
	}

	public String getPayment_gbn() {
		return payment_gbn;
	}

	public void setPayment_gbn(String payment_gbn) {
		this.payment_gbn = payment_gbn;
	}

	public String getMax_exec_num() {
		return max_exec_num;
	}

	public void setMax_exec_num(String max_exec_num) {
		this.max_exec_num = max_exec_num;
	}

	public String getExec_mod_flag() {
		return exec_mod_flag;
	}

	public void setExec_mod_flag(String exec_mod_flag) {
		this.exec_mod_flag = exec_mod_flag;
	}

	public String getExec_up_no() {
		return exec_up_no;
	}

	public void setExec_up_no(String exec_up_no) {
		this.exec_up_no = exec_up_no;
	}

	public String getExec_srt() {
		return exec_srt;
	}

	public void setExec_srt(String exec_srt) {
		this.exec_srt = exec_srt;
	}

	/** 이행구분 GRP_CD C055 : C05501-지급조건상세, C05502-산출물 */
	private String exec_gbn;
	/** 이행항목 */
	private String exec_itm;
	/** 금액 */
	private BigDecimal exec_amt;
	/** 완료일 */
	private String exec_cmpltday;
	/** 비고 */
	private String note;

	/** 계약관리_계약별_검토 */
	/** 검토의뢰_ID required : 국내해외구분(1) + YY + MM + DD + SEQ(3) */
	private String cnsdreq_id;
	/**
	 * 검토부서_ID required : 공통코드 (시스템 : CLM, 그룹코드 : C039 - C03901 :법무팀, C03902
	 * :IP센터 )
	 */
	private String cnsd_dept;
	/** 의뢰자 ID */
	private String reqman_id;
	/** 의뢰자 명 */
	private String reqman_nm;

	/** 배열 Property */
	/** 계약_ID : required - YYYY + MM + DD + HH24 + MI + SS + SSS */
	private String cntrt_id_arr[];
	/** 이행_일련번호 : required */
	private int exec_seqno_arr[];
	/** 이행_내용 : required */
	private String exec_cont_arr[];
	/** 이행_예정일 : required - YYYYMMDD */
	private String exec_plndday_arr[];
	/**
	 * 이행_상태 : required - 공통코드 (시스템 : CLM, 그룹코드 : C031 - 02 : 미확인, 03 : 확인중, 04
	 * : 완료)
	 */
	private String exec_status_arr[];

	private int exec_chk_arr[];
	/** 이행구분 GRP_CD C055 : C05501-지급조건상세, C05502-산출물 */
	private String exec_gbn_arr[];
	/** 이행항목 */
	private String exec_itm_arr[];
	/** 금액 */
	private String exec_amt_arr[];
	/** 완료일 */
	private String exec_cmpltday_arr[];
	/** 비고 */
	private String note_arr[];

	private String depth_status;
	private String prgrs_status;
	/**
	 * 이행 리스트 몇 번째 row 인지 판별
	 */
	private String exe_count;

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

	public String getCntrt_respman_id() {
		return cntrt_respman_id;
	}

	public void setCntrt_respman_id(String cntrt_respman_id) {
		this.cntrt_respman_id = cntrt_respman_id;
	}

	public String getReqman_id() {
		return reqman_id;
	}

	public void setReqman_id(String reqman_id) {
		this.reqman_id = reqman_id;
	}

	public String getReqman_nm() {
		return reqman_nm;
	}

	public void setReqman_nm(String reqman_nm) {
		this.reqman_nm = reqman_nm;
	}

	public String[] getExec_itm_arr() {
		return exec_itm_arr;
	}

	public void setExec_itm_arr(String[] exec_itm_arr) {
		this.exec_itm_arr = exec_itm_arr;
	}

	public String[] getExec_amt_arr() {
		return exec_amt_arr;
	}

	public void setExec_amt_arr(String[] exec_amt_arr) {
		this.exec_amt_arr = exec_amt_arr;
	}

	public String[] getExec_cmpltday_arr() {
		return exec_cmpltday_arr;
	}

	public void setExec_cmpltday_arr(String[] exec_cmpltday_arr) {
		this.exec_cmpltday_arr = exec_cmpltday_arr;
	}

	public String[] getExec_gbn_arr() {
		return exec_gbn_arr;
	}

	public void setExec_gbn_arr(String[] exec_gbn_arr) {
		this.exec_gbn_arr = exec_gbn_arr;
	}

	public String[] getNote_arr() {
		return note_arr;
	}

	public void setNote_arr(String[] note_arr) {
		this.note_arr = note_arr;
	}

	public String getExec_gbn() {
		return exec_gbn;
	}

	public void setExec_gbn(String exec_gbn) {
		this.exec_gbn = exec_gbn;
	}

	public String getExec_itm() {
		return exec_itm;
	}

	public void setExec_itm(String exec_itm) {
		this.exec_itm = exec_itm;
	}

	public BigDecimal getExec_amt() {
		return exec_amt;
	}

	public void setExec_amt(BigDecimal exec_amt) {
		this.exec_amt = exec_amt;
	}

	public String getExec_cmpltday() {
		return exec_cmpltday;
	}

	public void setExec_cmpltday(String exec_cmpltday) {
		this.exec_cmpltday = exec_cmpltday;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	// 관련자정보사용
	private String authreq_client;

	public String getAuthreq_client() {
		return authreq_client;
	}

	public void setAuthreq_client(String authreq_client) {
		this.authreq_client = authreq_client;
	}

	public String getExe_count() {
		return exe_count;
	}

	public void setExe_count(String exe_count) {
		this.exe_count = exe_count;
	}

	public String getDepth_status() {
		return depth_status;
	}

	public void setDepth_status(String depth_status) {
		this.depth_status = depth_status;
	}

	public String getPrgrs_status() {
		return prgrs_status;
	}

	public void setPrgrs_status(String prgrs_status) {
		this.prgrs_status = prgrs_status;
	}

	public String getBlngt_orgnz() {
		return blngt_orgnz;
	}

	public void setBlngt_orgnz(String blngt_orgnz) {
		this.blngt_orgnz = blngt_orgnz;
	}

	public String getDept_div() {
		return dept_div;
	}

	public void setDept_div(String dept_div) {
		this.dept_div = dept_div;
	}

	public String getCntrt_no() {
		return cntrt_no;
	}

	public void setCntrt_no(String cntrt_no) {
		this.cntrt_no = cntrt_no;
	}
	
}