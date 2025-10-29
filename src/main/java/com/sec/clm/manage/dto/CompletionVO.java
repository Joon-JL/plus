package com.sec.clm.manage.dto;

import java.math.BigDecimal;

import com.sds.secframework.common.dto.CommonVO;

public class CompletionVO extends CommonVO {
	/** 계약_ID : required - YYYY + MM + DD + HH24 + MI + SS + SSS */
	private String cntrt_id;
	/** 계약_담당자_ID */
	private String cntrt_respman_id;
	/** 계약_담당자_명 */
	private String cntrt_respman_nm;
	/** 계약_담당_부서 */
	private String cntrt_resp_dept;
	/** 계약_담당_부서_명 */
	private String cntrt_resp_dept_nm;
	/** 계약_담당_상위_부서 */
	private String cntrt_resp_up_dept;
	/** 계약_담당자_직급_명 */
	private String cntrt_respman_jikgup_nm;	
	/** 계약_상태 required 공통코드 (시스템 : CLM, 그룹코드 : C024) */
	private String cntrt_status;
	/** 계약_변경_요청일 */
	private String cntrt_chge_demndday;
	/** 계약_변경_요청자_ID */
	private String cntrt_chge_demndman_id;
	/** 계약_변경_요청자_명 */
	private String cntrt_chge_demndman_nm;
	/** 계약_변경_요청_부서_명 */
	private String cntrt_chge_demnd_dept_nm;
	/** 계약_변경_요청자_직급_명 */
	private String cntrt_chge_demndman_jikgup_nm;
	/** 계약_변경_요청_사유 */
	private String cntrt_chge_demnd_cause;
	/** 계약_변경_예정일 */
	private String cntrt_chge_plndday;
	/** 계약_변경_확인_여부 required - default : 'N' */
	private String cntrt_chge_conf_yn;
	/** 계약_변경_확인일 */
	private String cntrt_chge_confday;
	/** 계약_변경_확인자_ID */
	private String cntrt_chge_confman_id;
	/** 계약_변경_확인자_명 */
	private String cntrt_chge_confman_nm;
	/** 계약_변경_확인_부서_명 */
	private String cntrt_chge_conf_dept_nm;
	/** 계약_변경_확인자_직급_명 */
	private String cntrt_chge_confman_jikgup_nm;
	/** 계약_변경_확인자_확인의견 */
	private String cntrt_chge_conf_cont;
	/** 프로세스_단계 required 공통코드 (시스템 : CLM, 그룹코드 : C025) */
	private String prcs_depth;
	/** 단계_상태 required 공통코드 (시스템 : CLM, 그룹코드 : 프로세스_단계) */
	private String depth_status;
	/** 계약_변경_계약기간_종료일 (2012.07.05 자동연장 추가) */
	private String cntrtperiod_endday;
	/** 계약_변경_만료_알림일 (2012.07.05 자동연장 추가) */
	private String exprt_notiday;
	
	/** 원본 히스토리 관리 */
	private int seqno;
	private String gubun;	
	
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
	
	/** 이행 관리 */
	/** 이행_일련번호 : required */
	private int exec_seqno;
	/** 이행_내용 : required */
	private String exec_cont;
	/** 이행_예정일 : required - YYYYMMDD */
	private String exec_plndday;
	/** 이행_상태 : required - 공통코드 (시스템 : CLM, 그룹코드 : C031 - 02 : 미확인, 03 : 확인중, 04 :  완료)*/
	private String exec_status;
	/** 이행구분 GRP_CD C055 : C05501-지급조건상세, C05502-산출물 */
	private String exec_gbn;
	/** 이행항목  */
	private String exec_itm;
	/** 금액  */
	private BigDecimal exec_amt;
	/** 완료일  */
	private String exec_cmpltday;
	/** 비고 */
	private String note;
	/** 계약관리_계약별_검토*/
	/** 검토의뢰_ID required : 국내해외구분(1) + YY + MM + DD + SEQ(3) */
	private String cnsdreq_id;
	/** 계약관리_계약_검토의뢰 : 공통코드 (시스템 : CLM, 그룹코드 : C042) */
	private String prgrs_status;
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
	/** 이행_상태 : required - 공통코드 (시스템 : CLM, 그룹코드 : C031 - 02 : 미확인, 03 : 확인중, 04 :  완료)*/
	private String exec_status_arr[];
	
	private int exec_chk_arr[];
	/** 이행구분 GRP_CD C055 : C05501-지급조건상세, C05502-산출물 */
	private String exec_gbn_arr[];
	/** 이행항목  */
	private String exec_itm_arr[];
	/** 금액  */
	private String exec_amt_arr[];
	/** 완료일  */
	private String exec_cmpltday_arr[];
	/** 비고 */
	private String note_arr[];
	
	/** 참조이행번호 */
	private String exec_up_no;
	/** 참조정렬번호 */
	private String exec_srt;
	/** 변경flag */
	private String exec_mod_flag;	
	
	private String max_exec_num;
	
	//승인/반려메일전송 관련
	private String cntrt_nm;
	private String req_title;
	private String req_dt;
	private String blngt_orgnz_id;
	private String comp_cd;
	
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

	public String getExec_mod_flag() {
		return exec_mod_flag;
	}

	public void setExec_mod_flag(String exec_mod_flag) {
		this.exec_mod_flag = exec_mod_flag;
	}

	public String getMax_exec_num() {
		return max_exec_num;
	}

	public void setMax_exec_num(String max_exec_num) {
		this.max_exec_num = max_exec_num;
	}

	//첨부파일 
	private String fileInfos10;
	
	/*
	 *결재자관련추가 
     */ 
	private String approvalman_id;
	private String approvalman_nm;
	private String approvalman_dept_nm;
	private String approvalman_jikgup_nm;
	private String approval_opinion;

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
	
	public String getCntrt_respman_id() {
		return cntrt_respman_id;
	}

	public void setCntrt_respman_id(String cntrt_respman_id) {
		this.cntrt_respman_id = cntrt_respman_id;
	}

	public String getCntrt_respman_nm() {
		return cntrt_respman_nm;
	}

	public void setCntrt_respman_nm(String cntrt_respman_nm) {
		this.cntrt_respman_nm = cntrt_respman_nm;
	}

	public String getCntrt_resp_dept() {
		return cntrt_resp_dept;
	}

	public void setCntrt_resp_dept(String cntrt_resp_dept) {
		this.cntrt_resp_dept = cntrt_resp_dept;
	}

	public String getCntrt_resp_dept_nm() {
		return cntrt_resp_dept_nm;
	}

	public void setCntrt_resp_dept_nm(String cntrt_resp_dept_nm) {
		this.cntrt_resp_dept_nm = cntrt_resp_dept_nm;
	}

	public String getCntrt_resp_up_dept() {
		return cntrt_resp_up_dept;
	}

	public void setCntrt_resp_up_dept(String cntrt_resp_up_dept) {
		this.cntrt_resp_up_dept = cntrt_resp_up_dept;
	}

	public String getCntrt_respman_jikgup_nm() {
		return cntrt_respman_jikgup_nm;
	}

	public void setCntrt_respman_jikgup_nm(String cntrt_respman_jikgup_nm) {
		this.cntrt_respman_jikgup_nm = cntrt_respman_jikgup_nm;
	}

	public String getCntrt_status() {
		return cntrt_status;
	}

	public void setCntrt_status(String cntrt_status) {
		this.cntrt_status = cntrt_status;
	}

	public String getCntrt_chge_demndday() {
		return cntrt_chge_demndday;
	}

	public void setCntrt_chge_demndday(String cntrt_chge_demndday) {
		this.cntrt_chge_demndday = cntrt_chge_demndday;
	}

	public String getCntrt_chge_demndman_id() {
		return cntrt_chge_demndman_id;
	}

	public void setCntrt_chge_demndman_id(String cntrt_chge_demndman_id) {
		this.cntrt_chge_demndman_id = cntrt_chge_demndman_id;
	}

	public String getCntrt_chge_demndman_nm() {
		return cntrt_chge_demndman_nm;
	}

	public void setCntrt_chge_demndman_nm(String cntrt_chge_demndman_nm) {
		this.cntrt_chge_demndman_nm = cntrt_chge_demndman_nm;
	}

	public String getCntrt_chge_demnd_dept_nm() {
		return cntrt_chge_demnd_dept_nm;
	}

	public void setCntrt_chge_demnd_dept_nm(String cntrt_chge_demnd_dept_nm) {
		this.cntrt_chge_demnd_dept_nm = cntrt_chge_demnd_dept_nm;
	}

	public String getCntrt_chge_demndman_jikgup_nm() {
		return cntrt_chge_demndman_jikgup_nm;
	}

	public void setCntrt_chge_demndman_jikgup_nm(
			String cntrt_chge_demndman_jikgup_nm) {
		this.cntrt_chge_demndman_jikgup_nm = cntrt_chge_demndman_jikgup_nm;
	}

	public String getCntrt_chge_demnd_cause() {
		return cntrt_chge_demnd_cause;
	}

	public void setCntrt_chge_demnd_cause(String cntrt_chge_demnd_cause) {
		this.cntrt_chge_demnd_cause = cntrt_chge_demnd_cause;
	}

	public String getCntrt_chge_plndday() {
		return cntrt_chge_plndday;
	}

	public void setCntrt_chge_plndday(String cntrt_chge_plndday) {
		this.cntrt_chge_plndday = cntrt_chge_plndday;
	}

	public String getCntrt_chge_conf_yn() {
		return cntrt_chge_conf_yn;
	}

	public void setCntrt_chge_conf_yn(String cntrt_chge_conf_yn) {
		this.cntrt_chge_conf_yn = cntrt_chge_conf_yn;
	}

	public String getCntrt_chge_confday() {
		return cntrt_chge_confday;
	}

	public void setCntrt_chge_confday(String cntrt_chge_confday) {
		this.cntrt_chge_confday = cntrt_chge_confday;
	}

	public String getCntrt_chge_confman_id() {
		return cntrt_chge_confman_id;
	}

	public void setCntrt_chge_confman_id(String cntrt_chge_confman_id) {
		this.cntrt_chge_confman_id = cntrt_chge_confman_id;
	}

	public String getCntrt_chge_confman_nm() {
		return cntrt_chge_confman_nm;
	}

	public void setCntrt_chge_confman_nm(String cntrt_chge_confman_nm) {
		this.cntrt_chge_confman_nm = cntrt_chge_confman_nm;
	}

	public String getCntrt_chge_conf_dept_nm() {
		return cntrt_chge_conf_dept_nm;
	}

	public void setCntrt_chge_conf_dept_nm(String cntrt_chge_conf_dept_nm) {
		this.cntrt_chge_conf_dept_nm = cntrt_chge_conf_dept_nm;
	}

	public String getCntrt_chge_confman_jikgup_nm() {
		return cntrt_chge_confman_jikgup_nm;
	}

	public void setCntrt_chge_confman_jikgup_nm(String cntrt_chge_confman_jikgup_nm) {
		this.cntrt_chge_confman_jikgup_nm = cntrt_chge_confman_jikgup_nm;
	}

	public String getPrcs_depth() {
		return prcs_depth;
	}

	public void setPrcs_depth(String prcs_depth) {
		this.prcs_depth = prcs_depth;
	}

	public String getDepth_status() {
		return depth_status;
	}

	public void setDepth_status(String depth_status) {
		this.depth_status = depth_status;
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

	public String getCnsdreq_id() {
		return cnsdreq_id;
	}

	public void setCnsdreq_id(String cnsdreq_id) {
		this.cnsdreq_id = cnsdreq_id;
	}

	public String getPrgrs_status() {
		return prgrs_status;
	}

	public void setPrgrs_status(String prgrs_status) {
		this.prgrs_status = prgrs_status;
	}

	public String getFileInfos10() {
		return fileInfos10;
	}

	public void setFileInfos10(String fileInfos10) {
		this.fileInfos10 = fileInfos10;
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

	public String getCntrt_chge_conf_cont() {
		return cntrt_chge_conf_cont;
	}

	public void setCntrt_chge_conf_cont(String cntrt_chge_conf_cont) {
		this.cntrt_chge_conf_cont = cntrt_chge_conf_cont;
	}

	public String getApprovalman_id() {
		return approvalman_id;
	}

	public void setApprovalman_id(String approvalman_id) {
		this.approvalman_id = approvalman_id;
	}

	public String getApprovalman_nm() {
		return approvalman_nm;
	}

	public void setApprovalman_nm(String approvalman_nm) {
		this.approvalman_nm = approvalman_nm;
	}

	public String getApprovalman_dept_nm() {
		return approvalman_dept_nm;
	}

	public void setApprovalman_dept_nm(String approvalman_dept_nm) {
		this.approvalman_dept_nm = approvalman_dept_nm;
	}

	public String getApprovalman_jikgup_nm() {
		return approvalman_jikgup_nm;
	}

	public void setApprovalman_jikgup_nm(String approvalman_jikgup_nm) {
		this.approvalman_jikgup_nm = approvalman_jikgup_nm;
	}

	public String getApproval_opinion() {
		return approval_opinion;
	}

	public void setApproval_opinion(String approval_opinion) {
		this.approval_opinion = approval_opinion;
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

	public String[] getExec_gbn_arr() {
		return exec_gbn_arr;
	}

	public void setExec_gbn_arr(String[] exec_gbn_arr) {
		this.exec_gbn_arr = exec_gbn_arr;
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

	public String[] getNote_arr() {
		return note_arr;
	}

	public void setNote_arr(String[] note_arr) {
		this.note_arr = note_arr;
	}
	
	//관련자정보사용
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

	public String getCntrtperiod_endday() {
		return cntrtperiod_endday;
	}

	public void setCntrtperiod_endday(String cntrtperiod_endday) {
		this.cntrtperiod_endday = cntrtperiod_endday;
	}

	public String getExprt_notiday() {
		return exprt_notiday;
	}

	public void setExprt_notiday(String exprt_notiday) {
		this.exprt_notiday = exprt_notiday;
	}

	public int getSeqno() {
		return seqno;
	}

	public void setSeqno(int seqno) {
		this.seqno = seqno;
	}

	public String getGubun() {
		return gubun;
	}

	public void setGubun(String gubun) {
		this.gubun = gubun;
	}

	public String getCntrt_nm() {
		return cntrt_nm;
	}

	public void setCntrt_nm(String cntrt_nm) {
		this.cntrt_nm = cntrt_nm;
	}

	public String getReq_title() {
		return req_title;
	}

	public void setReq_title(String req_title) {
		this.req_title = req_title;
	}

	public String getReq_dt() {
		return req_dt;
	}

	public void setReq_dt(String req_dt) {
		this.req_dt = req_dt;
	}

	public String getBlngt_orgnz_id() {
		return blngt_orgnz_id;
	}

	public void setBlngt_orgnz_id(String blngt_orgnz_id) {
		this.blngt_orgnz_id = blngt_orgnz_id;
	}

	public String getComp_cd() {
		return comp_cd;
	}

	public void setComp_cd(String comp_cd) {
		this.comp_cd = comp_cd;
	}
	
}