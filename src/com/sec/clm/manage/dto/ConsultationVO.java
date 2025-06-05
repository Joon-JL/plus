package com.sec.clm.manage.dto;

import java.math.BigDecimal;
import com.sec.clm.manage.dto.ConsiderationVO;
/**
 * 계약관리_계약_마스터
 * @author chahyunjin
 */
public class ConsultationVO extends ConsiderationVO {
	private String[] arr_cntrt_id;	
	private String cntrt_id;
	private String cntrt_nm;
	private String region_gbn;
	private String biz_clsfcn;
	private String depth_clsfcn;
	private String cnclsnpurps_bigclsfcn;
	private String cnclsnpurps_midclsfcn;
	private String cnclsnpurps_midclsfcn_etc;
	private String cntrt_trgt;
	private String cntrt_trgt_det;
	private String pshdbkgrnd_purps;
	private String payment_gbn;
	private String antcptnefct;
	private String cntrtperiod_startday;
	private String cntrtperiod_endday;
	private String secret_keepperiod;
	private String demnd_gbn;
	private String master_cntrt_id;
	private String role_cd;
	
	private String dept;
	private String user_id;
	private String part;
	private String borgnz;
	private String manager_yn;
	
	private String blngt_orgnz_id;
	
    private String buGubn;                      // 인쇄 버튼의 구분
    
	private int 	exec_seqno_arr[];		//이행일련번호
	private String 	exec_cont_arr[];		//이행내용
	private String 	exec_plndday_arr[];		//이행예정일
	private String 	exec_status_arr[];		//이행상태
	private String 	del_yn_arr[];			//삭제여부
	
	private int 	exec_seqno;				//이행일련번호
	private String 	exec_cont;				//이행내용
	private String 	exec_plndday;			//이행예정일
	private String 	exec_status;			//이행상태
	
	private String 	std_cnslt_no;           //표준계약서 자문번호
	private String 	rejct_opnn;             //표준계약서 반려사유
	private String 	param_seqno;            //표준계약서 일련번호
	private String 	active_cntrt_id;
	private String 	deptcnsd_cnsd_dept;
	private String 	deptcnsd_cnsdman_id;
	private String 	deptcnsd_cnsdman_nm;
	private String 	chg_cntrt_status;
	private String 	chg_prcs_depth;
	private String 	chg_depth_status;
	
	
	private String 	deptcnsd_apbtman_id;
	private String 	deptcnsd_apbtman_nm;
	private String 	deptcnsd_apbt_opnn;
	private String 	deptcnsd_cnsd_status;
	private String 	main_matr_cont;
	private String 	cnsd_memo;
	private String 	reqdept_nm;
	
	private String en_cntrt_nm; // 영문 계약 명
	
	private String hq_req_yn;
	
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
	
	/** 결재 키 값 */
	private String mis_id;
	
	/**연관계약 update를 위한 키*/
	private String rel_up_cntrt_id;
	/**연관계약 유무*/
	private String rel_yn;
	
	private String comp_cd;
	
	public String getHq_req_yn() {
		return hq_req_yn;
	}
	public void setHq_req_yn(String hq_req_yn) {
		this.hq_req_yn = hq_req_yn;
	}
	public String getComp_cd() {
		return comp_cd;
	}
	public String getEn_cntrt_nm() {
		return en_cntrt_nm;
	}
	public void setEn_cntrt_nm(String en_cntrt_nm) {
		this.en_cntrt_nm = en_cntrt_nm;
	}
	public void setComp_cd(String comp_cd) {
		this.comp_cd = comp_cd;
	}
	public String getRel_up_cntrt_id() {
		return rel_up_cntrt_id;
	}
	public void setRel_up_cntrt_id(String rel_up_cntrt_id) {
		this.rel_up_cntrt_id = rel_up_cntrt_id;
	}
	public String getRel_yn() {
		return rel_yn;
	}
	public void setRel_yn(String rel_yn) {
		this.rel_yn = rel_yn;
	}
	public String getMis_id() {
		return mis_id;
	}
	public void setMis_id(String mis_id) {
		this.mis_id = mis_id;
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
	public String[] getDel_yn_arr() {
		return del_yn_arr;
	}
	public void setDel_yn_arr(String[] del_yn_arr) {
		this.del_yn_arr = del_yn_arr;
	}
	public String getBuGubn() {
		return buGubn;
	}
	public void setBuGubn(String buGubn) {
		this.buGubn = buGubn;
	}
	
	public String getBlngt_orgnz_id() {
		return blngt_orgnz_id;
	}
	public void setBlngt_orgnz_id(String blngt_orgnz_id) {
		this.blngt_orgnz_id = blngt_orgnz_id;
	}
	
	private String rel_type1;
	
	public String getRel_type1() {
		return rel_type1;
	}
	public void setRel_type1(String rel_type1) {
		this.rel_type1 = rel_type1;
	}
	public String getBorgnz() {
		return borgnz;
	}
	public void setBorgnz(String borgnz) {
		this.borgnz = borgnz;
	}
	public String getManager_yn() {
		return manager_yn;
	}
	public void setManager_yn(String manager_yn) {
		this.manager_yn = manager_yn;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public String getRole_cd() {
		return role_cd;
	}
	public void setRole_cd(String role_cd) {
		this.role_cd = role_cd;
	}
	public String getMaster_cntrt_id() {
		return master_cntrt_id;
	}
	public void setMaster_cntrt_id(String master_cntrt_id) {
		this.master_cntrt_id = master_cntrt_id;
	}
	public String getDemnd_gbn() {
		return demnd_gbn;
	}
	public void setDemnd_gbn(String demnd_gbn) {
		this.demnd_gbn = demnd_gbn;
	}



	/**
	 * 계약_단가 - 사용안함
	 */
	//private String cntrt_untprc;
	/**
	 * 계약_단가_설명
	 */
	private String cntrt_untprc_expl;
	private String cntrt_amt;
	private String crrncy_unit;
	private String payment_cond;
	private String auto_rnew_yn;
	private String exprt_notiday;
	private String oblgt_lmt;
	private String spcl_cond;
	private String cnclsn_plndday;
	private String cnclsn_plndday1;
	private String cntrt_respman_id;
	private String cntrt_respman_nm;
	private String cntrt_oppnt_cd;
	private String seal_ffmt_dept_cd;
	
	
	

	/*
	 * 체결 후 등록 필요 정보
	 */
	private String regNotiday_before;  // 만료시전 알림
	private String regExprt_notiday;   // 만료시전 알림일
	
	private String regSeal_ffmtman_id; // 날인 담당자 ID
	private String regSeal_ffmtman_nm; // 날인 담당자 이름
	private String regSeal_ffmt_dept_cd; // 날인 담당자 부서코드
	private String regSeal_ffmt_dept_nm; // 날인 담당자 부서이름
	private String regSeal_ffmtman_jikgup_nm; // 날인 담당자 직급명
	
	
	public String getRegNotiday_before() {
		return regNotiday_before;
	}
	public void setRegNotiday_before(String regNotiday_before) {
		this.regNotiday_before = regNotiday_before;
	}
	public String getRegExprt_notiday() {
		return regExprt_notiday;
	}
	public void setRegExprt_notiday(String regExprt_notiday) {
		this.regExprt_notiday = regExprt_notiday;
	}
	public String getRegSeal_ffmtman_id() {
		return regSeal_ffmtman_id;
	}
	public void setRegSeal_ffmtman_id(String regSeal_ffmtman_id) {
		this.regSeal_ffmtman_id = regSeal_ffmtman_id;
	}
	public String getRegSeal_ffmtman_nm() {
		return regSeal_ffmtman_nm;
	}
	public void setRegSeal_ffmtman_nm(String regSeal_ffmtman_nm) {
		this.regSeal_ffmtman_nm = regSeal_ffmtman_nm;
	}
	public String getRegSeal_ffmt_dept_cd() {
		return regSeal_ffmt_dept_cd;
	}
	public void setRegSeal_ffmt_dept_cd(String regSeal_ffmt_dept_cd) {
		this.regSeal_ffmt_dept_cd = regSeal_ffmt_dept_cd;
	}
	public String getRegSeal_ffmt_dept_nm() {
		return regSeal_ffmt_dept_nm;
	}
	public void setRegSeal_ffmt_dept_nm(String regSeal_ffmt_dept_nm) {
		this.regSeal_ffmt_dept_nm = regSeal_ffmt_dept_nm;
	}
	public String getRegSeal_ffmtman_jikgup_nm() {
		return regSeal_ffmtman_jikgup_nm;
	}
	public void setRegSeal_ffmtman_jikgup_nm(String regSeal_ffmtman_jikgup_nm) {
		this.regSeal_ffmtman_jikgup_nm = regSeal_ffmtman_jikgup_nm;
	}
	
	public String getSeal_ffmt_dept_cd() {
		return seal_ffmt_dept_cd;
	}
	public void setSeal_ffmt_dept_cd(String seal_ffmt_dept_cd) {
		this.seal_ffmt_dept_cd = seal_ffmt_dept_cd;
	}

	/**
	 * 계약 상대방 업체명 
	 */
	private String cntrt_oppnt_nm;
	public String getCntrt_oppnt_nm() {
		return cntrt_oppnt_nm;
	}
	public void setCntrt_oppnt_nm(String cntrt_oppnt_nm) {
		this.cntrt_oppnt_nm = cntrt_oppnt_nm;
	}
	public String getCntrt_oppnt_telno() {
		return cntrt_oppnt_telno;
	}
	public void setCntrt_oppnt_telno(String cntrt_oppnt_telno) {
		this.cntrt_oppnt_telno = cntrt_oppnt_telno;
	}
	public String getCntrt_oppnt_email() {
		return cntrt_oppnt_email;
	}
	public void setCntrt_oppnt_email(String cntrt_oppnt_email) {
		this.cntrt_oppnt_email = cntrt_oppnt_email;
	}
	public String getCntrt_oppnt_rprsntman() {
		return cntrt_oppnt_rprsntman;
	}
	public void setCntrt_oppnt_rprsntman(String cntrt_oppnt_rprsntman) {
		this.cntrt_oppnt_rprsntman = cntrt_oppnt_rprsntman;
	}
	/**
	 * 거래상대방 업체 Type
	 */
	private String cntrt_oppnt_type;
	public String getCntrt_oppnt_type() {
		return cntrt_oppnt_type;
	}
	public void setCntrt_oppnt_type(String cntrt_oppnt_type) {
		this.cntrt_oppnt_type = cntrt_oppnt_type;
	}
	/**
	 * 계약상대방 담당자 전화번호
	 */
	private String cntrt_oppnt_telno;
	/**
	 * 계약상대방 담당자 이메일
	 */
	private String cntrt_oppnt_email;
	/**
	 * 계약상대방 담당자 ceo 명 
	 */
	private String cntrt_oppnt_rprsntman;
	
	/**
	 * 계약 상대방 담당자 
	 */
	private String cntrt_oppnt_respman;
	
	
	public String getCntrt_oppnt_respman() {
		return cntrt_oppnt_respman;
	}
	public void setCntrt_oppnt_respman(String cntrt_oppnt_respman) {
		this.cntrt_oppnt_respman = cntrt_oppnt_respman;
	}
	
	//임시저장 종류 flag(button, tap)
	private String tempsave_flag;


	private String cntrt_oppnt_nation;
	private String sign_plndman_id;
	private String sign_plndman_nm;
	private String sign_plnd_dept_nm;
	private String sign_plndman_jikgup_nm;
	private String seal_mthd;
	private String cntrt_cnclsn_yn;
	private String signman_id;
	private String signman_nm;
	private String signman_jikgup_nm;
	private String sign_dept_nm;
	private String signday;
	private String oppnt_signman_nm;
	private String oppnt_signman_jikgup;
	private String oppnt_signman_dept;
	private String oppnt_signday;
	private String cntrt_cnclsnday;
	private String cpy_regday;
	private String cntrt_no;
	private String loac;
	private String dspt_resolt_mthd;
	private String dspt_resolt_mthd_det;
	private String org_acpt_dlay_cause;
	private String org_acptday;
	private String org_tkovman_id;
	private String org_tkovman_nm;
	private String org_hdovman_id;
	private String org_hdovman_nm;
	private String org_hdov_dept_nm;
	private String org_hdovman_jikgup_nm;
	private String org_strg_pos;
	private String prcs_depth;
	private String depth_status;	
	private String reg_dt;
	private String reg_id;
	private String reg_nm;
	private String mod_dt;
	private String mod_id;
	private String mod_nm;
	private String del_yn;
	private String del_dt;
	private String del_id;
	private String del_nm;
	private String loac_etc;
	
	/**
	 * 계약_변경_요청_사유 계약 drop
	 */
	private String cntrt_status;//계약_상태
	private String cntrt_chge_demndday;//계약_변경_요청일
	private String cntrt_chge_demndman_id;//계약_변경_요청자_ID
	private String cntrt_chge_demndman_nm;//계약_변경_요청자_명
	private String cntrt_chge_demnd_dept_nm;//계약_변경_요청_부서_명
	private String cntrt_chge_demndman_jikgup_nm;//계약_변경_요청자_직급_명
	private String cntrt_chge_demnd_cause;//계약_변경_요청_사유
	private String cntrt_chge_plndday;//계약_변경_예정일
	
	public void setCntrt_status(String cntrt_status) {
		this.cntrt_status = cntrt_status;
	}
	public String getCntrt_status() {
		return this.cntrt_status;
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

	
	
	/**
	 * 의뢰  or 계약 변경 사유 -의뢰보류사유,의뢰drop 사유,계약drop 사유
	 */
	private String chage_cause; 	
	
	public String getChage_cause() {
		return chage_cause;
	}
	public void setChage_cause(String chage_cause) {
		this.chage_cause = chage_cause;
	}

	private String cntrt_chge_conf_yn;
	private String cntrt_chge_confday;
	private String cntrt_chge_confman_id;
	private String cntrt_chge_confman_nm;
	private String bfhdcstn_mtnman_id;
	private String bfhdcstn_mtnman_nm;
	private String bfhdcstn_mtnman_jikgup_nm;
	private String bfhdcstn_mtn_dept_nm;
	private String bfhdcstn_mtnman_email;
	private String bfhdcstn_apbt_mthd;
	private String bfhdcstn_apbtman_id;
	private String bfhdcstn_apbtman_nm;
	private String bfhdcstn_apbtman_jikgup_nm;
	private String bfhdcstn_apbt_dept_nm;
	private String bfhdcstn_apbtday;
	private String seal_rqstday;
	private String seal_rqstman_id;
	private String seal_rqstman_nm;
	private String seal_ffmtman_id;
	private String seal_ffmtman_nm;
	private String seal_ffmtday;
	private String seal_ffmt_dept_nm;
	private String seal_ffmtman_jikgup_nm;
	private String reg_operdiv;

	private String start_index;
	private String end_index;
	private String curPage;
	private String body_mime1;
	
	/**
	 * 검토의뢰 첨부파일
	 */
	/**
	 * 검토의뢰 첨부파일
	 */
	private String fileInfos1;
	private String fileInfos2;
	private String fileInfos3; 
	private String fileInfos4;
	private String fileInfos5;
	private String fileInfos6;
	/**
	 * 첨부파일 -첨부/별첨
	 */
	private String fileInfosEtc;

	public String getFileInfosEtc() {
		return fileInfosEtc;
	}
	public void setFileInfosEtc(String fileInfosEtc) {
		this.fileInfosEtc = fileInfosEtc;
	}
	
	public String getFileInfos1() {
		return fileInfos1;
	}
	public void setFileInfos1(String fileInfos1) {
		this.fileInfos1 = fileInfos1;
	}
	public String getFileInfos2() {
		return fileInfos2;
	}
	public void setFileInfos2(String fileInfos2) {
		this.fileInfos2 = fileInfos2;
	}
	public String getFileInfos3() {
		return fileInfos3;
	}
	public void setFileInfos3(String fileInfos3) {
		this.fileInfos3 = fileInfos3;
	}
	public String getFileInfos4() {
		return fileInfos4;
	}
	public void setFileInfos4(String fileInfos4) {
		this.fileInfos4 = fileInfos4;
	}
	public String getFileInfos5() {
		return fileInfos5;
	}
	public void setFileInfos5(String fileInfos5) {
		this.fileInfos5 = fileInfos5;
	}
	
	
	public String getFileInfos6() {
		return fileInfos6;
	}
	public void setFileInfos6(String fileInfos6) {
		this.fileInfos6 = fileInfos6;
	}

	/**
	 * Tab 계약 Cnt
	 */
	private String tab_cnt;
	
	public void setTab_cnt(String tab_cnt){
		this.tab_cnt = tab_cnt;		
	}	
	public String getTab_cnt() {
		return this.tab_cnt;
	}	
	
	
	
	
	
	



	
	



	
	
			
	
	/**
	 * TN_CLM_CONTRACT_CNSD 
	 */
	
	private String cnsdman_id;
	/**
	 * 검토자_ID
	 * @param cnsdman_id
	 */
	public void setCnsdman_id(String cnsdman_id){
		this.cnsdman_id = cnsdman_id;		
	}	
	public String getCnsdman_id() {
		return this.cnsdman_id;
	}
	
	private String cnsdman_nm;
	/**
	 * 검토자_명
	 * @param cnsdman_nm
	 */
	public void setCnsdman_nm(String cnsdman_nm){
		this.cnsdman_nm = cnsdman_nm;		
	}	
	public String getCnsdman_nm() {
		return this.cnsdman_nm;
	}
	
	private String cnsDay;
	/**
	 * 검토일
	 * @param cnsDay
	 */
	public void setCnsDay(String cnsDay){
		this.cnsDay = cnsDay;		
	}	
	public String getCnsDay() {
		return this.cnsDay;
	}
	
	private String cnsd_opnn;
	/**
	 * 검토_의견
	 * @param cnsd_opnn
	 */
	public void setCnsd_opnn(String cnsd_opnn){
		this.cnsd_opnn = cnsd_opnn;		
	}	
	public String getCnsd_opnn() {
		return this.cnsd_opnn;
	}
	
	private String apbtman_id;
	/**
	 * 승인자_ID
	 * @param apbtman_id
	 */
	public void setApbtman_id(String apbtman_id){
		this.apbtman_id = apbtman_id;		
	}	
	public String getApbtman_id() {
		return this.apbtman_id;
	}
	
	private String apbtman_nm;
	/**
	 * 승인자_명
	 * @param apbtman_nm
	 */
	public void setApbtman_nm(String apbtman_nm){
		this.apbtman_nm = apbtman_nm;		
	}	
	public String getApbtman_nm() {
		return this.apbtman_nm;
	}
	
	private String apbtDay;
	/**
	 * 승인일
	 * @param apbtDay
	 */
	public void setApbtDay(String apbtDay){
		this.apbtDay = apbtDay;		
	}	
	public String getApbtDay() {
		return this.apbtDay;
	}
	private String apbt_opnn;
	/**
	 *  승인_의견
	 * @param apbt_opnn
	 */
	public void setApbt_opnn(String apbt_opnn){
		this.apbt_opnn = apbt_opnn;		
	}	
	public String getApbt_opnn() {
		return this.apbt_opnn;
	}
	
	private String cnsd_status;
	/**
	 * 검토_상태
	 * @param cnsd_status
	 */
	public void setCnsd_status(String cnsd_status){
		this.cnsd_status = cnsd_status;		
	}	
	public String getCnsd_status() {
		return this.cnsd_status;
	}
	
	private String risk;
	/**
	 * RISK
	 * @param risk
	 */
	public void setRisk(String risk){
		this.risk = risk;		
	}	
	public String getRisk() {
		return this.risk;
	}
	
	/**
	 * TN_CLM_RELATION_CONTRACT - 계약관리_연관계약
	 */
	private String[] arr_parent_cntrt_id;	
	private String parent_cntrt_id;
	/**
	 * 모_계약_ID
	 * @param parent_cntrt_id
	 */
	public void setArr_parent_cntrt_id(String[] arr_parent_cntrt_id){
		this.arr_parent_cntrt_id = arr_parent_cntrt_id;		
	}	
	public String[] getArr_parent_cntrt_id() {
		return this.arr_parent_cntrt_id;
	}
	
	public void setParent_cntrt_id(String parent_cntrt_id){
		this.parent_cntrt_id = parent_cntrt_id;		
	}	
	public String getParent_cntrt_id() {
		return this.parent_cntrt_id;
	}
	private String[] arr_rel_type;
	private String rel_type;
	/**
	 * 관련_유형
	 * @param rel_type
	 */
	
	public void setArr_rel_type(String[] arr_rel_type){
		this.arr_rel_type = arr_rel_type;		
	}	
	public String[] getArr_rel_type() {
		return this.arr_rel_type;
	}
	
	public void setRel_type(String rel_type){
		this.rel_type = rel_type;		
	}	
	public String getRel_type() {
		return this.rel_type;
	}
	
	private String[] arr_expl;
	private String expl;
	
	public void setArr_expl(String[] arr_expl){
		this.arr_expl = arr_expl;		
	}	
	public String[] getArr_expl() {
		return this.arr_expl;
	}
	
	private String[] arr_rel_defn;
	
	public String[] getArr_rel_defn() {
		return arr_rel_defn;
	}
	public void setArr_rel_defn(String[] arr_rel_defn) {
		this.arr_rel_defn = arr_rel_defn;
	}


	private String rel_defn;
	
	public void setRel_defn(String rel_defn){
		this.rel_defn = rel_defn;
	}
	
	public String getRel_defn(){
		return this.rel_defn;
	}
	
	public void setExpl(String expl){
		this.expl = expl;		
	}	
	public String getExpl() {
		return this.expl;
	}
	
	
	/**
	 * TN_CLM_SPECIAL_ATTR 계약관리_특화_속성
	 */
	
	private String attr_seqno;
	/**
	 * 속성_일련번호
	 * @param attr_seqno
	 */
	public void setAttr_seqno(String attr_seqno){
		this.attr_seqno = attr_seqno;		
	}	
	public String getAttr_seqno() {
		return this.attr_seqno;
	}
	
	private String attr_nm;
	/**
	 * 속성_명
	 * @param attr_nm
	 */
	public void setAttr_nm(String attr_nm){
		this.attr_nm = attr_nm;		
	}	
	public String getAttr_nm() {
		return this.attr_nm;
	}
	
	private String attr_nm_eng;
	/**
	 * 속성_명_영문
	 * @param attr_nm_eng
	 */
	public void setAttr_nm_eng(String attr_nm_eng){
		this.attr_nm_eng = attr_nm_eng;		
	}	
	public String getAttr_nm_eng() {
		return this.attr_nm_eng;
	}
	
	private String attr_expl;
	/**
	 * 속성_설명
	 * @param attr_expl
	 */
	public void setAttr_expl(String attr_expl){
		this.attr_expl = attr_expl;		
	}	
	public String getAttr_expl() {
		return this.attr_expl;
	}
	
	private String attr_frm;
	/**
	 * 속성_형태
	 * @param attr_frm
	 */
	public void setAttr_frm(String attr_frm){
		this.attr_frm = attr_frm;		
	}	
	public String getAtr_frm() {
		return this.attr_frm;
	}
	
	private String attr_sz_wdth;
	/**
	 * 속성_크기_가로
	 * @param attr_sz_wdth
	 */
	public void setAttr_sz_wdth(String attr_sz_wdth){
		this.attr_sz_wdth = attr_sz_wdth;		
	}	
	public String getAttr_sz_wdth() {
		return this.attr_sz_wdth;
	}
	
	private String attr_sz_hght;
	/**
	 * 속성_크기_세로
	 * @param attr_sz_hght
	 */
	public void setAttr_sz_hght(String attr_sz_hght){
		this.attr_sz_hght = attr_sz_hght;		
	}	
	public String getAttr_sz_hght() {
		return this.attr_sz_hght;
	}
	
	private String attr_max_sz;
	/**
	 * 속성_최대_크기
	 * @param attr_max_sz
	 */
	public void setAttr_max_sz(String attr_max_sz){
		this.attr_max_sz = attr_max_sz;		
	}	
	public String getAttr_max_sz() {
		return this.attr_max_sz;
	}
	
	private String mndtry_yn;
	/**
	 * 필수_여부
	 * @param mndtry_yn
	 */
	public void setMndtry_yn(String mndtry_yn){
		this.mndtry_yn = mndtry_yn;		
	}	
	public String getMndtry_yn() {
		return this.mndtry_yn;
	}
	
	private String cd_yn;
	/**
	 * 코드_여부
	 * @param cd_yn
	 */
	public void setCd_yn(String cd_yn){
		this.cd_yn = cd_yn;		
	}	
	public String getCd_yn() {
		return this.cd_yn;
	}
	
	private String cd_sys;
	/**
	 * 코드_시스템
	 * @param cd_sys
	 */
	public void setCd_sys(String cd_sys){
		this.cd_sys = cd_sys;		
	}	
	public String getCd_sys() {
		return this.cd_sys;
	}
	private String cd_grp;
	/**
	 * 코드_그룹
	 * @param cd_grp
	 */
	public void setCd_grp(String cd_grp){
		this.cd_grp = cd_grp;		
	}	
	public String getCd_grp() {
		return this.cd_grp;
	}
	
	private String prnt_srt;
	/**
	 * 출력_순서
	 * @param prnt_srt
	 */
	public void setPrnt_srt(String prnt_srt){
		this.prnt_srt = prnt_srt;		
	}	
	public String getPrnt_srt() {
		return this.prnt_srt;
	}
	
	/**
	 * TN_CLM_CONT_TYPE_SPCLINFO 계약관리_계약유형_특화정보
	 */
	private String[] arr_attr_seqno;
	
	/**
	 * 속성_일련번호
	 * @param attr_cd
	 */
	public void setArr_attr_seqno(String[] arr_attr_seqno){
		this.arr_attr_seqno = arr_attr_seqno;		
	}	
	public String[] getArr_attr_seqno() {
		return this.arr_attr_seqno;
	}
	
	private String[] arr_attr_cd;
	private String attr_cd;
	/**
	 * 속성_코드 
	 * @param attr_cd
	 */
	public void setArr_attr_cd(String[] arr_attr_cd){
		this.arr_attr_cd = arr_attr_cd;		
	}	
	public String[] getArr_attr_cd() {
		return this.arr_attr_cd;
	}
	
	public void setAttr_cd(String attr_cd){
		this.attr_cd = attr_cd;		
	}	
	public String getAttr_cd() {
		return this.attr_cd;
	}
	
	private String[] arr_attr_cont;
	private String attr_cont;
	/**
	 * 속성_내용
	 * @param attr_cont
	 */
	public void setArr_attr_cont(String[] arr_attr_cont){
		this.arr_attr_cont = arr_attr_cont;		
	}	
	public String[] getArr_attr_cont() {
		return this.arr_attr_cont;
	}
	
	public void setAttr_cont(String attr_cont){
		this.attr_cont = attr_cont;		
	}	
	public String getAttr_cont() {
		return this.attr_cont;
	}
	
	/**
	 * 계약_권한요청 TN_CLM_CONTRACT_AUTHREQ
	 */
//	private String cntrt_id;
	private String demnd_seqno;
	private String demndman_id;
	private String demndman_nm;
	private String demndman_dept_nm;
	private String trgtman_id;
	private String[] arr_trgtman_id;
	private String trgtman_nm;
	private String[] arr_trgtman_nm;
	private String trgtman_dept_nm;
	private String[] arr_trgtman_dept_nm;
	private String[] arr_trgtman_jikgup_nm;
	private String trgtman_jikgup_nm;
	public String getTrgtman_jikgup_nm() {
		return trgtman_jikgup_nm;
	}
	public void setTrgtman_jikgup_nm(String trgtman_jikgup_nm) {
		this.trgtman_jikgup_nm = trgtman_jikgup_nm;
	}
	public String[] getArr_trgtman_jikgup_nm() {
		return arr_trgtman_jikgup_nm;
	}
	public void setArr_trgtman_jikgup_nm(String[] arr_trgtman_jikgup_nm) {
		this.arr_trgtman_jikgup_nm = arr_trgtman_jikgup_nm;
	}



	private String rd_auth;
	private String auth_startday;
	private String auth_endday;
	private String demnd_status;
	private String demnd_dt;
	private String hndl_dt;
	private String hndlman_id;
	private String hndlman_nm;
	private String hndl_cont;
//	private String del_yn;
//	private String del_dt;
//	private String del_id;
//	private String del_id;
	/**
	 * SET
	 */
	public void setDemnd_seqno(String demnd_seqno) {
		this.demnd_seqno = demnd_seqno;
	}
	public void setDemndman_id(String demndman_id) {
		this.demndman_id = demndman_id;		
	}
	public void setDemndman_nm(String demndman_nm) {
		this.demndman_nm = demndman_nm;
	}
	public void setDemndman_dept_nm(String demndman_dept_nm) {
		this.demndman_dept_nm = demndman_dept_nm;
	}
	public void setTrgtman_id(String trgtman_id) {
		this.trgtman_id = trgtman_id;
	}
	/**
	 * 
	 * @param arr_trgtman_id
	 */
	public void setArr_trgtman_id(String[] arr_trgtman_id){
		this.arr_trgtman_id = arr_trgtman_id;
	}
	public void setTrgtman_nm(String trgtman_nm) {
		this.trgtman_nm = trgtman_nm;
	}
	/**
	 * 
	 * @param arr_trgtman_nm
	 */
	public void setArr_trgtman_nm(String[] arr_trgtman_nm){
		this.arr_trgtman_nm = arr_trgtman_nm;
	}
	public void setTrgtman_dept_nm(String trgtman_dept_nm) {
		this.trgtman_dept_nm = trgtman_dept_nm;
	}
	/**
	 * 
	 * @param arr_trgtman_dept_nm
	 */
	public void setArr_trgtman_dept_nm(String[] arr_trgtman_dept_nm){
		this.arr_trgtman_dept_nm = arr_trgtman_dept_nm;
	}
	public void setRd_auth(String rd_auth) {
		this.rd_auth = rd_auth;
	}
	public void setAuth_startday(String auth_startday) {
		this.auth_startday = auth_startday;
	}
	public void setAuth_endday(String auth_endday) {
		this.auth_endday = auth_endday;
	}
	public void setDemnd_status(String demnd_status) {
		this.demnd_status = demnd_status;
	}
	public void setDemnd_dt(String demnd_dt) {
		this.demnd_dt = demnd_dt;
	}
	public void setHndl_dt(String hndl_dt) {
		this.hndl_dt = hndl_dt;
	}
	public void setHndlman_id(String hndlman_id) {
		this.hndlman_id = hndlman_id;
	}
	public void setHndlman_nm(String hndlman_nm) {
		this.hndlman_nm = hndlman_nm;
	}
	public void setHndl_cont(String hndl_cont) {
		this.hndl_cont = hndl_cont;
	}
	/**
	 * GET
	 * @return
	 */
	public String getDemnd_seqno() {
		return this.demnd_seqno;
	}
	public String getDemndman_id() {
		return this.demndman_id;
	}
	public String getDemndman_nm() {
		return this.demndman_nm;
	}
	public String getDemndman_dept_nm() {
		return this.demndman_dept_nm;
	}
	public String getTrgtman_id() {
		return this.trgtman_id;
	}
	/**
	 * 
	 * @return
	 */
	public String[] getArr_trgtman_id(){
		return this.arr_trgtman_id;
	}
	public String getTrgtman_nm() {
		return this.trgtman_nm;
	}
	/**
	 * 
	 * @return
	 */
	public String[] getArr_trgtman_nm(){
		return this.arr_trgtman_nm;
	}
	public String getTrgtman_dept_nm() {
		return this.trgtman_dept_nm;
	}
	public String[] getArr_trgtman_dept_nm(){
		return this.arr_trgtman_dept_nm;
	}
	public String getRd_auth() {
		return this.rd_auth;
	}
	public String getAuth_startday() {
		return this.auth_startday;
	}
	public String getAuth_endday() {
		return this.auth_endday;
	}
	public String getDemnd_status() {
		return this.demnd_status;
	}
	public String getDemnd_dt() {
		return this.demnd_dt;
	}
	public String getHndl_dt() {
		return this.hndl_dt;
	}
	public String getHndlman_id() {
		return this.hndlman_id;
	}
	public String getHndlman_nm() {
		return this.hndlman_nm;		
	}
	public String getHndl_cont() {
		return this.hndl_cont;
	}	

	public void setArr_cntrt_id(String[] arr_cntrt_id){
		this.arr_cntrt_id = arr_cntrt_id;
	}
	public void setCntrt_id(String cntrt_id) {
		this.cntrt_id = cntrt_id;
	}
	public void setCntrt_nm(String cntrt_nm) {
		this.cntrt_nm = cntrt_nm;
	}
	public void setRegion_gbn(String region_gbn) {
		this.region_gbn = region_gbn;
	}
	public void setBiz_clsfcn(String biz_clsfcn) {
		this.biz_clsfcn = biz_clsfcn;
	}
	public void setDepth_clsfcn(String depth_clsfcn) {
		this.depth_clsfcn = depth_clsfcn;
	}
	public void setCnclsnpurps_bigclsfcn(String cnclsnpurps_bigclsfcn) {
		this.cnclsnpurps_bigclsfcn = cnclsnpurps_bigclsfcn;
	}
	public void setCnclsnpurps_midclsfcn(String cnclsnpurps_midclsfcn) {
		this.cnclsnpurps_midclsfcn = cnclsnpurps_midclsfcn;
	}
	public void setCnclsnpurps_midclsfcn_etc(String cnclsnpurps_midclsfcn_etc) {
		this.cnclsnpurps_midclsfcn_etc = cnclsnpurps_midclsfcn_etc;
	}
	public void setCntrt_trgt(String cntrt_trgt) {
		this.cntrt_trgt = cntrt_trgt;
	}
	public void setCntrt_trgt_det(String cntrt_trgt_det) {
		this.cntrt_trgt_det = cntrt_trgt_det;
	}
	public void setPshdbkgrnd_purps(String pshdbkgrnd_purps) {
		this.pshdbkgrnd_purps = pshdbkgrnd_purps;
	}
	public void setPayment_gbn(String payment_gbn) {
		this.payment_gbn = payment_gbn;
	}
	public void setAntcptnefct(String antcptnefct) {
		this.antcptnefct = antcptnefct;
	}
	public void setCntrtperiod_startday(String cntrtperiod_startday) {
		this.cntrtperiod_startday = cntrtperiod_startday;
	}
	public void setCntrtperiod_endday(String cntrtperiod_endday) {
		this.cntrtperiod_endday = cntrtperiod_endday;
	}
	public void setSecret_keepperiod(String secret_keepperiod) {
		this.secret_keepperiod = secret_keepperiod;
	}
	public void setCntrt_untprc_expl(String cntrt_untprc_expl) {
		this.cntrt_untprc_expl = cntrt_untprc_expl;
	}
	public void setCntrt_amt(String cntrt_amt) {
		this.cntrt_amt = cntrt_amt;
	}
	public void setCrrncy_unit(String crrncy_unit) {
		this.crrncy_unit = crrncy_unit;
	}
	public void setPayment_cond(String payment_cond) {
		this.payment_cond = payment_cond;
	}
	public void setAuto_rnew_yn(String auto_rnew_yn) {
		this.auto_rnew_yn = auto_rnew_yn;
	}
	public void setExprt_notiday(String exprt_notiday) {
		this.exprt_notiday = exprt_notiday;
	}
	public void setOblgt_lmt(String oblgt_lmt) {
		this.oblgt_lmt = oblgt_lmt;
	}
	public void setSpcl_cond(String spcl_cond) {
		this.spcl_cond = spcl_cond;
	}
	public void setCnclsn_plndday(String cnclsn_plndday) {
		this.cnclsn_plndday = cnclsn_plndday;
	}
	public void setCnclsn_plndday1(String cnclsn_plndday1) {
		this.cnclsn_plndday1 = cnclsn_plndday1;
	}
	public void setCntrt_respman_id(String cntrt_respman_id) {
		this.cntrt_respman_id = cntrt_respman_id;
	}
	public void setCntrt_respman_nm(String cntrt_respman_nm) {
		this.cntrt_respman_nm = cntrt_respman_nm;
	}
	public void setCntrt_oppnt_cd(String cntrt_oppnt_cd) {
		this.cntrt_oppnt_cd = cntrt_oppnt_cd;
	}
	public void setCntrt_oppnt_nation(String cntrt_oppnt_nation){
		this.cntrt_oppnt_nation = cntrt_oppnt_nation;
	}
	public void setSign_plndman_id(String sign_plndman_id) {
		this.sign_plndman_id = sign_plndman_id;
	}
	public void setSign_plndman_nm(String sign_plndman_nm) {
		this.sign_plndman_nm = sign_plndman_nm;
	}
	public void setSign_plnd_dept_nm(String sign_plnd_dept_nm) {
		this.sign_plnd_dept_nm = sign_plnd_dept_nm;
	}
	public void setSign_plndman_jikgup_nm(String sign_plndman_jikgup_nm) {
		this.sign_plndman_jikgup_nm = sign_plndman_jikgup_nm;
	}
	public void setSeal_mthd(String seal_mthd) {
		this.seal_mthd = seal_mthd;
	}
	public void setCntrt_cnclsn_yn(String cntrt_cnclsn_yn) {
		this.cntrt_cnclsn_yn = cntrt_cnclsn_yn;
	}
	public void setSignman_id(String signman_id) {
		this.signman_id = signman_id;
	}
	public void setSignman_nm(String signman_nm) {
		this.signman_nm = signman_nm;
	}
	public void setOppnt_signman_nm(String oppnt_signman_nm) {
		this.oppnt_signman_nm = oppnt_signman_nm;
	}
	public void setOppnt_signman_jikgup(String oppnt_signman_jikgup) {
		this.oppnt_signman_jikgup = oppnt_signman_jikgup;
	}
	public void setOppnt_signman_dept(String oppnt_signman_dept) {
		this.oppnt_signman_dept = oppnt_signman_dept;
	}
	public void setOppnt_signday(String oppnt_signday) {
		this.oppnt_signday = oppnt_signday;
	}
	public void setCntrt_cnclsnday(String cntrt_cnclsnday) {
		this.cntrt_cnclsnday = cntrt_cnclsnday;
	}
	public void setCpy_regday(String cpy_regday) {
		this.cpy_regday = cpy_regday;
	}
	public void setCntrt_no(String cntrt_no) {
		this.cntrt_no = cntrt_no;
	}
	public void setLoac(String loac) {
		this.loac = loac;
	}
	public void setDspt_resolt_mthd(String dspt_resolt_mthd) {
		this.dspt_resolt_mthd = dspt_resolt_mthd;
	}
	public void setDspt_resolt_mthd_det(String dspt_resolt_mthd_det) {
		this.dspt_resolt_mthd_det = dspt_resolt_mthd_det;
	}
	public void setOrg_acpt_dlay_cause(String org_acpt_dlay_cause) {
		this.org_acpt_dlay_cause = org_acpt_dlay_cause;
	}
	public void setOrg_acptday(String org_acptday) {
		this.org_acptday = org_acptday;
	}
	public void setOrg_tkovman_id(String org_tkovman_id) {
		this.org_tkovman_id = org_tkovman_id;
	}
	public void setOrg_tkovman_nm(String org_tkovman_nm) {
		this.org_tkovman_nm = org_tkovman_nm;
	}
	public void setOrg_hdovman_id(String org_hdovman_id) {
		this.org_hdovman_id = org_hdovman_id;
	}
	public void setOrg_hdovman_nm(String org_hdovman_nm) {
		this.org_hdovman_nm = org_hdovman_nm;
	}
	public void setOrg_strg_pos(String org_strg_pos) {
		this.org_strg_pos = org_strg_pos;
	}
	public void setPrcs_depth(String prcs_depth) {
		this.prcs_depth = prcs_depth;
	}
	public void setDepth_status(String depth_status) {
		this.depth_status = depth_status;
	}
	
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public void setReg_nm(String reg_nm) {
		this.reg_nm = reg_nm;
	}
	public void setMod_dt(String mod_dt) {
		this.mod_dt = mod_dt;
	}
	public void setMod_id(String mod_id) {
		this.mod_id = mod_id;
	}
	public void setMod_nm(String mod_nm) {
		this.mod_nm = mod_nm;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	public void setDel_dt(String del_dt) {
		this.del_dt = del_dt;
	}
	public void setDel_id(String del_id) {
		this.del_id = del_id;
	}
	public void setDel_nm(String del_nm) {
		this.del_nm = del_nm;
	}
	public void setLoac_etc(String loac_etc) {
		this.loac_etc = loac_etc;
	}
	public void setCntrt_chge_demndday(String cntrt_chge_demndday) {
		this.cntrt_chge_demndday = cntrt_chge_demndday;
	}
	public void setCntrt_chge_demndman_id(String cntrt_chge_demndman_id) {
		this.cntrt_chge_demndman_id = cntrt_chge_demndman_id;
	}
	public void setCntrt_chge_demndman_nm(String cntrt_chge_demndman_nm) {
		this.cntrt_chge_demndman_nm = cntrt_chge_demndman_nm;
	}
	public void setCntrt_chge_demnd_cause(String cntrt_chge_demnd_cause) {
		this.cntrt_chge_demnd_cause = cntrt_chge_demnd_cause;
	}
	public void setCntrt_chge_plndday(String cntrt_chge_plndday) {
		this.cntrt_chge_plndday = cntrt_chge_plndday;
	}
	public void setCntrt_chge_conf_yn(String cntrt_chge_conf_yn) {
		this.cntrt_chge_conf_yn = cntrt_chge_conf_yn;
	}
	public void setCntrt_chge_confday(String cntrt_chge_confday) {
		this.cntrt_chge_confday = cntrt_chge_confday;
	}
	public void setCntrt_chge_confman_id(String cntrt_chge_confman_id) {
		this.cntrt_chge_confman_id = cntrt_chge_confman_id;
	}
	public void setCntrt_chge_confman_nm(String cntrt_chge_confman_nm) {
		this.cntrt_chge_confman_nm = cntrt_chge_confman_nm;
	}
	public void setBfhdcstn_mtnman_id(String bfhdcstn_mtnman_id) {
		this.bfhdcstn_mtnman_id = bfhdcstn_mtnman_id;
	}
	public void setBfhdcstn_mtnman_nm(String bfhdcstn_mtnman_nm) {
		this.bfhdcstn_mtnman_nm = bfhdcstn_mtnman_nm;
	}
	public void setBfhdcstn_mtnman_jikgup_nm(String bfhdcstn_mtnman_jikgup_nm) {
		this.bfhdcstn_mtnman_jikgup_nm = bfhdcstn_mtnman_jikgup_nm;
	}
	public void setBfhdcstn_mtn_dept_nm(String bfhdcstn_mtn_dept_nm) {
		this.bfhdcstn_mtn_dept_nm = bfhdcstn_mtn_dept_nm;
	}
	public void setBfhdcstn_mtnman_email(String bfhdcstn_mtnman_email) {
		this.bfhdcstn_mtnman_email = bfhdcstn_mtnman_email;
	}
	public void setBfhdcstn_apbt_mthd(String bfhdcstn_apbt_mthd) {
		this.bfhdcstn_apbt_mthd = bfhdcstn_apbt_mthd;
	}
	public void setBfhdcstn_apbtman_id(String bfhdcstn_apbtman_id) {
		this.bfhdcstn_apbtman_id = bfhdcstn_apbtman_id;
	}
	public void setBfhdcstn_apbtman_nm(String bfhdcstn_apbtman_nm) {
		this.bfhdcstn_apbtman_nm = bfhdcstn_apbtman_nm;
	}
	public void setBfhdcstn_apbtman_jikgup_nm(String bfhdcstn_apbtman_jikgup_nm) {
		this.bfhdcstn_apbtman_jikgup_nm = bfhdcstn_apbtman_jikgup_nm;
	}
	public void setBfhdcstn_apbt_dept_nm(String bfhdcstn_apbt_dept_nm) {
		this.bfhdcstn_apbt_dept_nm = bfhdcstn_apbt_dept_nm;
	}
	public void setBfhdcstn_apbtday(String bfhdcstn_apbtday) {
		this.bfhdcstn_apbtday = bfhdcstn_apbtday;
	}
	public void setSeal_rqstday(String seal_rqstday) {
		this.seal_rqstday = seal_rqstday;
	}
	public void setSeal_rqstman_id(String seal_rqstman_id) {
		this.seal_rqstman_id = seal_rqstman_id;
	}
	public void setSeal_rqstman_nm(String seal_rqstman_nm) {
		this.seal_rqstman_nm = seal_rqstman_nm;
	}
	public void setSeal_ffmtman_id(String seal_ffmtman_id) {
		this.seal_ffmtman_id = seal_ffmtman_id;
	}
	public void setSeal_ffmtman_nm(String seal_ffmtman_nm) {
		this.seal_ffmtman_nm = seal_ffmtman_nm;
	}
	public void setSeal_ffmtday(String seal_ffmtday) {
		this.seal_ffmtday = seal_ffmtday;
	}
	public void setSeal_ffmt_dept_nm(String seal_ffmt_dept_nm) {
		this.seal_ffmt_dept_nm = seal_ffmt_dept_nm;
	}
	public void setSeal_ffmtman_jikgup_nm(String seal_ffmtman_jikgup_nm) {
		this.seal_ffmtman_jikgup_nm = seal_ffmtman_jikgup_nm;
	}
	public void setReg_operdiv(String reg_operdiv) {
		this.reg_operdiv = reg_operdiv;
	}
	public void setStart_index(String start_index) {
		this.start_index = start_index;
	}
	public void setEnd_index(String end_index) {
		this.end_index = end_index;
	}
	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}
	public void setBody_mime1(String body_mime1){
		this.body_mime1 = body_mime1;
	}
	
	public String[] getArr_cntrt_id(){
		return this.arr_cntrt_id;
	}
	public String getCntrt_id() {
		return this.cntrt_id;
	}
	public String getCntrt_nm() {
		return this.cntrt_nm;
	}
	public String getRegion_gbn() {
		return this.region_gbn;
	}
	public String getBiz_clsfcn() {
		return this.biz_clsfcn;
	}
	public String getDepth_clsfcn() {
		return this.depth_clsfcn;
	}
	public String getCnclsnpurps_bigclsfcn() {
		return this.cnclsnpurps_bigclsfcn;
	}
	public String getCnclsnpurps_midclsfcn() {
		return this.cnclsnpurps_midclsfcn;
	}
	public String getCnclsnpurps_midclsfcn_etc() {
		return this.cnclsnpurps_midclsfcn_etc;
	}
	public String getCntrt_trgt() {
		return this.cntrt_trgt;
	}
	public String getCntrt_trgt_det() {
		return this.cntrt_trgt_det;
	}
	public String getPshdbkgrnd_purps() {
		return this.pshdbkgrnd_purps;
	}
	public String getPayment_gbn() {
		return this.payment_gbn;
	}
	public String getAntcptnefct() {
		return this.antcptnefct;
	}
	public String getCntrtperiod_startday() {
		return this.cntrtperiod_startday;
	}
	public String getCntrtperiod_endday() {
		return this.cntrtperiod_endday;
	}
	public String getSecret_keepperiod() {
		return this.secret_keepperiod;
	}
	public String getCntrt_untprc_expl() {
		return this.cntrt_untprc_expl;
	}
	public String getCntrt_amt() {
		return this.cntrt_amt;
	}
	public String getCrrncy_unit() {
		return this.crrncy_unit;
	}
	public String getPayment_cond() {
		return this.payment_cond;
	}
	public String getAuto_rnew_yn() {
		return this.auto_rnew_yn;
	}
	public String getExprt_notiday() {
		return this.exprt_notiday;
	}
	public String getOblgt_lmt() {
		return this.oblgt_lmt;
	}
	public String getSpcl_cond() {
		return this.spcl_cond;
	}
	public String getCnclsn_plndday() {
		return this.cnclsn_plndday;
	}
	public String getCnclsn_plndday1() {
		return this.cnclsn_plndday1;
	}
	public String getCntrt_respman_id() {
		return this.cntrt_respman_id;
	}
	public String getCntrt_respman_nm() {
		return this.cntrt_respman_nm;
	}
	public String getCntrt_oppnt_cd() {
		return this.cntrt_oppnt_cd;
	}
	public String getCntrt_oppnt_nation(){
		return this.cntrt_oppnt_nation;
	}
	public String getSign_plndman_id() {
		return this.sign_plndman_id;
	}
	public String getSign_plndman_nm() {
		return this.sign_plndman_nm;
	}
	public String getSign_plnd_dept_nm() {
		return this.sign_plnd_dept_nm;
	}
	public String getSign_plndman_jikgup_nm() {
		return this.sign_plndman_jikgup_nm;
	}
	public String getSeal_mthd() {
		return this.seal_mthd;
	}
	public String getCntrt_cnclsn_yn() {
		return this.cntrt_cnclsn_yn;
	}
	public String getSignman_id() {
		return this.signman_id;
	}
	public String getSignman_nm() {
		return this.signman_nm;
	}
	public String getOppnt_signman_nm() {
		return this.oppnt_signman_nm;
	}
	public String getOppnt_signman_jikgup() {
		return this.oppnt_signman_jikgup;
	}
	public String getOppnt_signman_dept() {
		return this.oppnt_signman_dept;
	}
	public String getOppnt_signday() {
		return this.oppnt_signday;
	}
	public String getCntrt_cnclsnday() {
		return this.cntrt_cnclsnday;
	}
	public String getCpy_regday() {
		return this.cpy_regday;
	}
	public String getCntrt_no() {
		return this.cntrt_no;
	}
	public String getLoac() {
		return this.loac;
	}
	public String getDspt_resolt_mthd() {
		return this.dspt_resolt_mthd;
	}
	public String getDspt_resolt_mthd_det() {
		return this.dspt_resolt_mthd_det;
	}
	public String getOrg_acpt_dlay_cause() {
		return this.org_acpt_dlay_cause;
	}
	public String getOrg_acptday() {
		return this.org_acptday;
	}
	public String getOrg_tkovman_id() {
		return this.org_tkovman_id;
	}
	public String getOrg_tkovman_nm() {
		return this.org_tkovman_nm;
	}
	public String getOrg_hdovman_id() {
		return this.org_hdovman_id;
	}
	public String getOrg_hdovman_nm() {
		return this.org_hdovman_nm;
	}
	public String getOrg_strg_pos() {
		return this.org_strg_pos;
	}
	public String getPrcs_depth() {
		return this.prcs_depth;
	}
	public String getDepth_status() {
		return this.depth_status;
	}
	
	public String getReg_dt() {
		return this.reg_dt;
	}
	public String getReg_id() {
		return this.reg_id;
	}
	public String getReg_nm() {
		return this.reg_nm;
	}
	public String getMod_dt() {
		return this.mod_dt;
	}
	public String getMod_id() {
		return this.mod_id;
	}
	public String getMod_nm() {
		return this.mod_nm;
	}
	public String getDel_yn() {
		return this.del_yn;
	}
	public String getDel_dt() {
		return this.del_dt;
	}
	public String getDel_id() {
		return this.del_id;
	}
	public String getDel_nm() {
		return this.del_nm;
	}
	public String getLoac_etc() {
		return this.loac_etc;
	}
	public String getCntrt_chge_demndday() {
		return this.cntrt_chge_demndday;
	}
	public String getCntrt_chge_demndman_id() {
		return this.cntrt_chge_demndman_id;
	}
	public String getCntrt_chge_demndman_nm() {
		return this.cntrt_chge_demndman_nm;
	}
	public String getCntrt_chge_demnd_cause() {
		return this.cntrt_chge_demnd_cause;
	}
	public String getCntrt_chge_plndday() {
		return this.cntrt_chge_plndday;
	}
	public String getCntrt_chge_conf_yn() {
		return this.cntrt_chge_conf_yn;
	}
	public String getCntrt_chge_confday() {
		return this.cntrt_chge_confday;
	}
	public String getCntrt_chge_confman_id() {
		return this.cntrt_chge_confman_id;
	}
	public String getCntrt_chge_confman_nm() {
		return this.cntrt_chge_confman_nm;
	}
	public String getBfhdcstn_mtnman_id() {
		return this.bfhdcstn_mtnman_id;
	}
	public String getBfhdcstn_mtnman_nm() {
		return this.bfhdcstn_mtnman_nm;
	}
	public String getBfhdcstn_mtnman_jikgup_nm() {
		return this.bfhdcstn_mtnman_jikgup_nm;
	}
	public String getBfhdcstn_mtn_dept_nm() {
		return this.bfhdcstn_mtn_dept_nm;
	}
	public String getBfhdcstn_mtnman_email() {
		return this.bfhdcstn_mtnman_email;
	}
	public String getBfhdcstn_apbt_mthd() {
		return this.bfhdcstn_apbt_mthd;
	}
	public String getBfhdcstn_apbtman_id() {
		return this.bfhdcstn_apbtman_id;
	}
	public String getBfhdcstn_apbtman_nm() {
		return this.bfhdcstn_apbtman_nm;
	}
	public String getBfhdcstn_apbtman_jikgup_nm() {
		return this.bfhdcstn_apbtman_jikgup_nm;
	}
	public String getBfhdcstn_apbt_dept_nm() {
		return this.bfhdcstn_apbt_dept_nm;
	}
	public String getBfhdcstn_apbtday() {
		return this.bfhdcstn_apbtday;
	}
	public String getSeal_rqstday() {
		return this.seal_rqstday;
	}
	public String getSeal_rqstman_id() {
		return this.seal_rqstman_id;
	}
	public String getSeal_rqstman_nm() {
		return this.seal_rqstman_nm;
	}
	public String getSeal_ffmtman_id() {
		return this.seal_ffmtman_id;
	}
	public String getSeal_ffmtman_nm() {
		return this.seal_ffmtman_nm;
	}
	public String getSeal_ffmtday() {
		return this.seal_ffmtday;
	}
	public String getSeal_ffmt_dept_nm() {
		return this.seal_ffmt_dept_nm;
	}
	public String getSeal_ffmtman_jikgup_nm() {
		return this.seal_ffmtman_jikgup_nm;
	}
	public String getReg_operdiv() {
		return this.reg_operdiv;
	}
	public String getStart_index() {
		return this.start_index;
	}
	public String getEnd_index() {
		return this.end_index;
	}
	public String getCurPage() {
		return this.curPage;
	}
	public String getBody_mime1(){
		return this.body_mime1;
	}
	
	
	
	
	
	private String user_role;
	private String user_orgnz;
	
	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}
	public String getUser_role() {
		return this.user_role;
	}
	public void setUser_orgnz(String user_orgnz) {
		this.user_orgnz = user_orgnz;
	}
	public String getUser_orgnz() {
		return this.user_orgnz;
	}
	

	private String etc_main_cont;


	private String cntrt_resp_dept;
	private String cntrt_resp_dept_nm;
	private String cntrt_resp_up_dept;
	private String cntrt_respman_jikgup_nm;


	private String status;
	
	public void setStatus(String status){
		this.status = status;
	}
	public String getStatus(){
		return this.status;
	} 
	
	/**
	 * 계약담당부서 / 계약담당부서명 / 계약담당상위부서 / 계약담당자직급명
	 * @param cntrt_resp_dept
	 */
	public void setCntrt_resp_dept(String cntrt_resp_dept){
		this.cntrt_resp_dept = cntrt_resp_dept;
	}
	public void setCntrt_resp_dept_nm(String cntrt_resp_dept_nm){
		this.cntrt_resp_dept_nm = cntrt_resp_dept_nm;
	}
	public void setCntrt_resp_up_dept(String cntrt_resp_up_dept){
		this.cntrt_resp_up_dept = cntrt_resp_up_dept;
		
	}
	public void setCntrt_respman_jikgup_nm(String cntrt_respman_jikgup_nm){
		this.cntrt_respman_jikgup_nm = cntrt_respman_jikgup_nm;
	}
	
	private String fst_cntrt_resp_dept;
	public String getFst_cntrt_resp_dept() {
		return fst_cntrt_resp_dept;
	}
	public void setFst_cntrt_resp_dept(String fst_cntrt_resp_dept) {
		this.fst_cntrt_resp_dept = fst_cntrt_resp_dept;
	}
	public String getFst_cntrt_resp_up_dept() {
		return fst_cntrt_resp_up_dept;
	}
	public void setFst_cntrt_resp_up_dept(String fst_cntrt_resp_up_dept) {
		this.fst_cntrt_resp_up_dept = fst_cntrt_resp_up_dept;
	}



	private String fst_cntrt_resp_up_dept;
	
	
	
	/**
	 * 
	 * @param cntrt_oppnt_cd
	 */

	public void setEtc_main_cont(String etc_main_cont){
		this.etc_main_cont = etc_main_cont;
	}
	public String getEtc_main_cont(){
		return this.etc_main_cont;
	}

	public String getCntrt_resp_dept(){
		return this.cntrt_resp_dept;
	}
	public String getCntrt_resp_dept_nm(){
		return this.cntrt_resp_dept_nm;
	}
	public String getCntrt_resp_up_dept(){
		return this.cntrt_resp_up_dept;		
	}
	public String getCntrt_respman_jikgup_nm(){
		return this.cntrt_respman_jikgup_nm;
	}
	
	
	private int agree_seqno;
	private String agreeman_id;
	private String agreeman_nm;
	private String agreeman_jikgup_nm;
	private String agreeman_dept_nm;
	private String agreeday;            
	private String agree_yn;			  
	private String agree_cause;

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
	public String getAgreeday() {
		return agreeday;
	}
	public void setAgreeday(String agreeday) {
		this.agreeday = agreeday;
	}
	public String getAgree_yn() {
		return agree_yn;
	}
	public void setAgree_yn(String agree_yn) {
		this.agree_yn = agree_yn;
	}
	public String getAgree_cause() {
		return agree_cause;
	}
	public void setAgree_cause(String agree_cause) {
		this.agree_cause = agree_cause;
	}
	
	//결재상신등록후처리
	private String approval_cntrt_info;

	public String getApproval_cntrt_info() {
		return approval_cntrt_info;
	}
	public void setApproval_cntrt_info(String approval_cntrt_info) {
		this.approval_cntrt_info = approval_cntrt_info;
	}
	
	private String plndbn_chge_cont;
	
	public String getPlndbn_chge_cont() {
		return plndbn_chge_cont;
	}
	public void setPlndbn_chge_cont(String plndbn_chge_cont) {
		this.plndbn_chge_cont = plndbn_chge_cont;
	}
	
	private String chg_prgrs_status;

	public String getChg_prgrs_status() {
		return chg_prgrs_status;
	}
	public void setChg_prgrs_status(String chg_prgrs_status) {
		this.chg_prgrs_status = chg_prgrs_status;
	}
	
	/**
	 * 공통 리스트 검색 조건 - 차현진 10.25
	 */
	private String list_mode;

	private String srch_start_reqday;
	private String srch_end_reqday;
	private String srch_reqman_nm;
	private String srch_contract_type_nm;
	private String srch_contract_taget;
	private String srch_state;
	private String srch_review_title;
	private String srch_cntrt_nm; 				// Contract Title by hong
	private String srch_cntrt_no;				// Contract ID by hong
	private String srch_str_org_acptday;		// Original Copy by hong
	private String srch_end_org_acptday;		// Original Copy by hong
	
	private String srch_start_cnlsnday;			// 계약 시작일
	private String srch_end_cnlsnday;			// 계약 종료일
	private String srch_resp_dept_nm;			// 담당부서명	
	private String srch_respman_nm;				// 담당자명
	private String srch_oppnt_nm;				// 계약상대방명
	private String srch_cnsdman_nm;				// 검토자
	private String srch_if_sys_cd;				// 연계시스템
	private String srch_biz_clsfcn;				// 계약단계
	private String srch_cnclsnpurps_bigclsfcn; 	// 체결목적
	private String srch_step; // 2012-05-03 추가
	
	public String getList_mode() {
		return list_mode;
	}
	public void setList_mode(String list_mode) {
		this.list_mode = list_mode;
	}
	public String getSrch_start_reqday() {
		return srch_start_reqday;
	}
	public void setSrch_start_reqday(String srch_start_reqday) {
		this.srch_start_reqday = srch_start_reqday;
	}
	public String getSrch_end_reqday() {
		return srch_end_reqday;
	}
	public void setSrch_end_reqday(String srch_end_reqday) {
		this.srch_end_reqday = srch_end_reqday;
	}
	public String getSrch_reqman_nm() {
		return srch_reqman_nm;
	}
	public void setSrch_reqman_nm(String srch_reqman_nm) {
		this.srch_reqman_nm = srch_reqman_nm;
	}
	public String getSrch_contract_type_nm() {
		return srch_contract_type_nm;
	}
	public void setSrch_contract_type_nm(String srch_contract_type_nm) {
		this.srch_contract_type_nm = srch_contract_type_nm;
	}
	public String getSrch_contract_taget() {
		return srch_contract_taget;
	}
	public void setSrch_contract_taget(String srch_contract_taget) {
		this.srch_contract_taget = srch_contract_taget;
	}
	public String getSrch_state() {
		return srch_state;
	}
	public void setSrch_state(String srch_state) {
		this.srch_state = srch_state;
	}
	
	/**
	 * 의뢰 보류시 사용
	 */
	private int hold_seqno;
	private String hold_cause;
	public int getHold_seqno() {
		return hold_seqno;
	}
	public void setHold_seqno(int hold_seqno) {
		this.hold_seqno = hold_seqno;
	}
	public String getHold_cause() {
		return hold_cause;
	}
	public void setHold_cause(String hold_cause) {
		this.hold_cause = hold_cause;
	}
	
	public String getSrch_review_title() {
		return srch_review_title;
	}
	public void setSrch_review_title(String srch_review_title) {
		this.srch_review_title = srch_review_title;
	}
		
	public String getSrch_cntrt_nm() {
		return srch_cntrt_nm;
	}
	public void setSrch_cntrt_nm(String srch_cntrt_nm) {
		this.srch_cntrt_nm = srch_cntrt_nm;
	}
	public String getSrch_cntrt_no() {
		return srch_cntrt_no;
	}
	public void setSrch_cntrt_no(String srch_cntrt_no) {
		this.srch_cntrt_no = srch_cntrt_no;
	}
	public String getSrch_str_org_acptday() {
		return srch_str_org_acptday;
	}
	public void setSrch_str_org_acptday(String srch_str_org_acptday) {
		this.srch_str_org_acptday = srch_str_org_acptday;
	}
	public String getSrch_end_org_acptday() {
		return srch_end_org_acptday;
	}
	public void setSrch_end_org_acptday(String srch_end_org_acptday) {
		this.srch_end_org_acptday = srch_end_org_acptday;
	}
	
	

	public String getSrch_start_cnlsnday() {
		return srch_start_cnlsnday;
	}
	public void setSrch_start_cnlsnday(String srch_start_cnlsnday) {
		this.srch_start_cnlsnday = srch_start_cnlsnday;
	}
	public String getSrch_end_cnlsnday() {
		return srch_end_cnlsnday;
	}
	public void setSrch_end_cnlsnday(String srch_end_cnlsnday) {
		this.srch_end_cnlsnday = srch_end_cnlsnday;
	}
	public String getSrch_resp_dept_nm() {
		return srch_resp_dept_nm;
	}
	public void setSrch_resp_dept_nm(String srch_resp_dept_nm) {
		this.srch_resp_dept_nm = srch_resp_dept_nm;
	}
	public String getSrch_respman_nm() {
		return srch_respman_nm;
	}
	public void setSrch_respman_nm(String srch_respman_nm) {
		this.srch_respman_nm = srch_respman_nm;
	}
	public String getSrch_oppnt_nm() {
		return srch_oppnt_nm;
	}
	public void setSrch_oppnt_nm(String srch_oppnt_nm) {
		this.srch_oppnt_nm = srch_oppnt_nm;
	}
	public String getSrch_cnsdman_nm() {
		return srch_cnsdman_nm;
	}
	public void setSrch_cnsdman_nm(String srch_cnsdman_nm) {
		this.srch_cnsdman_nm = srch_cnsdman_nm;
	}
	public String getSrch_if_sys_cd() {
		return srch_if_sys_cd;
	}
	public void setSrch_if_sys_cd(String srch_if_sys_cd) {
		this.srch_if_sys_cd = srch_if_sys_cd;
	}
	public String getSrch_biz_clsfcn() {
		return srch_biz_clsfcn;
	}
	public void setSrch_biz_clsfcn(String srch_biz_clsfcn) {
		this.srch_biz_clsfcn = srch_biz_clsfcn;
	}
	public String getSrch_cnclsnpurps_bigclsfcn() {
		return srch_cnclsnpurps_bigclsfcn;
	}
	public void setSrch_cnclsnpurps_bigclsfcn(String srch_cnclsnpurps_bigclsfcn) {
		this.srch_cnclsnpurps_bigclsfcn = srch_cnclsnpurps_bigclsfcn;
	}
	public String getSrch_step() {
		return srch_step;
	}
	public void setSrch_step(String srch_step) {
		this.srch_step = srch_step;
	}

	//관련자정보사용
	private String authreq_client;

	public String getAuthreq_client() {
		return authreq_client;
	}
	public void setAuthreq_client(String authreq_client) {
		this.authreq_client = authreq_client;
	}
	public String getOrg_hdov_dept_nm() {
		return org_hdov_dept_nm;
	}
	public void setOrg_hdov_dept_nm(String org_hdov_dept_nm) {
		this.org_hdov_dept_nm = org_hdov_dept_nm;
	}
	public String getOrg_hdovman_jikgup_nm() {
		return org_hdovman_jikgup_nm;
	}
	public void setOrg_hdovman_jikgup_nm(String org_hdovman_jikgup_nm) {
		this.org_hdovman_jikgup_nm = org_hdovman_jikgup_nm;
	}
	public String getAttr_frm() {
		return attr_frm;
	}
	public String getTempsave_flag() {
		return tempsave_flag;
	}
	public void setTempsave_flag(String tempsave_flag) {
		this.tempsave_flag = tempsave_flag;
	}
	public String getStd_cnslt_no() {
		return std_cnslt_no;
	}
	public void setStd_cnslt_no(String std_cnslt_no) {
		this.std_cnslt_no = std_cnslt_no;
	}
	public String getRejct_opnn() {
		return rejct_opnn;
	}
	public void setRejct_opnn(String rejct_opnn) {
		this.rejct_opnn = rejct_opnn;
	}
	public String getParam_seqno() {
		return param_seqno;
	}
	public void setParam_seqno(String param_seqno) {
		this.param_seqno = param_seqno;
	}
	public String getActive_cntrt_id() {
		return active_cntrt_id;
	}
	public void setActive_cntrt_id(String active_cntrt_id) {
		this.active_cntrt_id = active_cntrt_id;
	}
	public String getDeptcnsd_cnsd_dept() {
		return deptcnsd_cnsd_dept;
	}
	public void setDeptcnsd_cnsd_dept(String deptcnsd_cnsd_dept) {
		this.deptcnsd_cnsd_dept = deptcnsd_cnsd_dept;
	}
	public String getDeptcnsd_cnsdman_id() {
		return deptcnsd_cnsdman_id;
	}
	public void setDeptcnsd_cnsdman_id(String deptcnsd_cnsdman_id) {
		this.deptcnsd_cnsdman_id = deptcnsd_cnsdman_id;
	}
	public String getDeptcnsd_cnsdman_nm() {
		return deptcnsd_cnsdman_nm;
	}
	public void setDeptcnsd_cnsdman_nm(String deptcnsd_cnsdman_nm) {
		this.deptcnsd_cnsdman_nm = deptcnsd_cnsdman_nm;
	}
	public String getChg_depth_status() {
		return chg_depth_status;
	}
	public void setChg_depth_status(String chg_depth_status) {
		this.chg_depth_status = chg_depth_status;
	}
	public String getDeptcnsd_apbtman_id() {
		return deptcnsd_apbtman_id;
	}
	public void setDeptcnsd_apbtman_id(String deptcnsd_apbtman_id) {
		this.deptcnsd_apbtman_id = deptcnsd_apbtman_id;
	}
	public String getDeptcnsd_apbtman_nm() {
		return deptcnsd_apbtman_nm;
	}
	public void setDeptcnsd_apbtman_nm(String deptcnsd_apbtman_nm) {
		this.deptcnsd_apbtman_nm = deptcnsd_apbtman_nm;
	}
	public String getDeptcnsd_apbt_opnn() {
		return deptcnsd_apbt_opnn;
	}
	public void setDeptcnsd_apbt_opnn(String deptcnsd_apbt_opnn) {
		this.deptcnsd_apbt_opnn = deptcnsd_apbt_opnn;
	}
	public String getDeptcnsd_cnsd_status() {
		return deptcnsd_cnsd_status;
	}
	public void setDeptcnsd_cnsd_status(String deptcnsd_cnsd_status) {
		this.deptcnsd_cnsd_status = deptcnsd_cnsd_status;
	}
	public String getMain_matr_cont() {
		return main_matr_cont;
	}
	public void setMain_matr_cont(String main_matr_cont) {
		this.main_matr_cont = main_matr_cont;
	}
	public String getChg_cntrt_status() {
		return chg_cntrt_status;
	}
	public void setChg_cntrt_status(String chg_cntrt_status) {
		this.chg_cntrt_status = chg_cntrt_status;
	}
	public String getChg_prcs_depth() {
		return chg_prcs_depth;
	}
	public void setChg_prcs_depth(String chg_prcs_depth) {
		this.chg_prcs_depth = chg_prcs_depth;
	}
	public String getCnsd_memo() {
		return cnsd_memo;
	}
	public void setCnsd_memo(String cnsd_memo) {
		this.cnsd_memo = cnsd_memo;
	}
	public String getReqdept_nm() {
		return reqdept_nm;
	}
	public void setReqdept_nm(String reqdept_nm) {
		this.reqdept_nm = reqdept_nm;
	}
	public String getSignman_jikgup_nm() {
		return signman_jikgup_nm;
	}
	public void setSignman_jikgup_nm(String signman_jikgup_nm) {
		this.signman_jikgup_nm = signman_jikgup_nm;
	}
	public String getSign_dept_nm() {
		return sign_dept_nm;
	}
	public void setSign_dept_nm(String sign_dept_nm) {
		this.sign_dept_nm = sign_dept_nm;
	}
	public String getSignday() {
		return signday;
	}
	public void setSignday(String signday) {
		this.signday = signday;
	}
	
	// 2014-04-14 Kevin added.
	private String gerpCostType = null;
	private String gerpContractType = null;
	private String gerpDivCode = null;
	
	public void setGERPCostType(String gerpCostType){
		this.gerpCostType = gerpCostType;
	}
	public String getGERPCostType(){
		return this.gerpCostType;
	}
	public void setGERPContractType(String gerpContractType){
		this.gerpContractType = gerpContractType;
	}
	public String getGERPContractType(){
		return this.gerpContractType;
	}
	public void setGERPDivCode(String gerpDivCode){
		this.gerpDivCode = gerpDivCode;
	}
	public String getGERPDivCode(){
		return this.gerpDivCode;
	}
	
	/* eCMS 관련 필드 추가  */
	private String cntrt_top30_cus;
	
	private String cntrt_src_cont_drft;
	
	private String cntrt_rel_prd;
	
	private String tnc_app_link;
	private String ctc_link;
	private String otc_link;
	private String tnc_summary_link;
	private String sys_nm;
	
	private String asgn_dt;

	
	
	public String getCntrt_top30_cus() {
		return cntrt_top30_cus;
	}
	public void setCntrt_top30_cus(String cntrt_top30_cus) {
		this.cntrt_top30_cus = cntrt_top30_cus;
	}
	public String getCntrt_src_cont_drft() {
		return cntrt_src_cont_drft;
	}
	public void setCntrt_src_cont_drft(String cntrt_src_cont_drft) {
		this.cntrt_src_cont_drft = cntrt_src_cont_drft;
	}
	public String getCntrt_rel_prd() {
		return cntrt_rel_prd;
	}
	public void setCntrt_rel_prd(String cntrt_rel_prd) {
		this.cntrt_rel_prd = cntrt_rel_prd;
	}
	public String getGerpCostType() {
		return gerpCostType;
	}
	public void setGerpCostType(String gerpCostType) {
		this.gerpCostType = gerpCostType;
	}
	public String getGerpContractType() {
		return gerpContractType;
	}
	public void setGerpContractType(String gerpContractType) {
		this.gerpContractType = gerpContractType;
	}
	public String getGerpDivCode() {
		return gerpDivCode;
	}
	public void setGerpDivCode(String gerpDivCode) {
		this.gerpDivCode = gerpDivCode;
	}
	public String getTnc_app_link() {
		return tnc_app_link;
	}
	public void setTnc_app_link(String tnc_app_link) {
		this.tnc_app_link = tnc_app_link;
	}
	public String getCtc_link() {
		return ctc_link;
	}
	public void setCtc_link(String ctc_link) {
		this.ctc_link = ctc_link;
	}
	public String getOtc_link() {
		return otc_link;
	}
	public void setOtc_link(String otc_link) {
		this.otc_link = otc_link;
	}
	public String getTnc_summary_link() {
		return tnc_summary_link;
	}
	public void setTnc_summary_link(String tnc_summary_link) {
		this.tnc_summary_link = tnc_summary_link;
	}
	public String getSys_nm() {
		return sys_nm;
	}
	public void setSys_nm(String sys_nm) {
		this.sys_nm = sys_nm;
	}
	public String getAsgn_dt() {
		return asgn_dt;
	}
	public void setAsgn_dt(String asgn_dt) {
		this.asgn_dt = asgn_dt;
	}
	
	
	
}