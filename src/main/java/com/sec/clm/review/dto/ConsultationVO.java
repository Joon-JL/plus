package com.sec.clm.review.dto;

/**
 * 계약관리_계약_마스터
 * 
 * @author chahyunjin
 */
public class ConsultationVO extends ConsiderationVO {
	
	/** 테이블 기준 */
	private String table_div;
	/** 상대회사명 */
	private String cntrt_oppnt_nm;
	/** 제목 */
	private String srch_cntrt_nm;
	/** 상대회사관련 계약건 검색 여부 */
	private String cntrt_srch_yn;
	/** 검토의뢰_제목 */
	private String req_title;
	/** 검토의뢰_이관_요청_일시 */
	private String trnsf_demnd_dt;
	/** 검토의뢰_이관_요청자_명 */
	private String trnsf_demndman_nm;
	/** 검토의뢰_이관_승인자_명 */
	private String trnsf_hndlman_nm;
	/** 검토의뢰_이관_사유 */
	private String trnsf_demnd_cause;
	/** 검토의뢰_처리_의견 */
	private String trnsf_hndl_cause;
	/** 검토의뢰_검토요청_사유 */
	private String vc_cnsd_demnd_cont;
	/** 검토의뢰_이관_상태 */
	private String trnsf_trans_status;
	/** 검토의뢰_이관_상태_명 */
	private String trnsf_trans_status_nm;
	/** 검토의뢰_생성_일련번호 */
	private String param_seqno;
	/** 검토의뢰_이관_처리_일시 */
	private String trnsf_hndl_dt;

	/** 검토의뢰_정_검토_부서 */
	private String mn_cnsd_dept;
	/** 검토의뢰_부_검토_부서 */
	private String vc_cnsd_dept;
	/** 세션_검토_부서 */
	private String cnsd_dept;

	/** 검토의뢰_담당자_담당자_ID */
	private String list_respman_id;
	/** 검토의뢰_담당자_담당자_ID */
	private String list_respman_ids[];
	/** 검토의뢰_담당자_담당자_명 */
	private String list_respman_nm;
	/** 검토의뢰_담당자_담당자_명 */
	private String list_respman_nms[];

	/** 소속조직 */
	private String blngt_orgnz;
	/** 권한직위 */
	private String top_role;
	/** 담당자 로그인 여부 */
	private String respman_apnt_yn;
	/** 세션_소속부서 */
	private String mod_dept_cd;
	/** 세션_소속부서_명 */
	private String mod_dept_nm;

	/** 계약별_검토_진행상태 변경 */
	private String chg_prgrs_status;
	/** 계약_마스터_단계상태 변경 */
	private String chg_depth_status;

	/** 그룹장_반려_의견 */
	private String rejct_opnn;
	/** 버튼 객체 */
	private String event_but;
	/** 의뢰일 */
	private String req_dt;
	/** 그룹장 권한 화면 구분값 */
	private String page_div;
	/** 정_검토담당자 */
	private String main_man_id;
	/** 최종본회신 구분값 */
	private String gbn_last;
	/** 정담당자 지정여부 */
	private String mn_respman_apnt_yn;
	/** 부담당자 지정여부 */
	private String vc_respman_apnt_yn;
	private String comp_cd;
	/** 미결 검토회신 여부 */
	private String not_appr_yn;
	/** 검토방식(단독여부) */
	private String solo_yn;
	
	// 필수 항목 체크 관련
	private String item_cd;
	private String dis_yn;
	private String remark;
	private String sum_text;
	private String cnsd_level;
	private String hqreqcheck;
	private String check_yn;
	
	private String re_dt;
	
	private String asgn_dt;
	
	public String getEvent_but() {
		return event_but;
	}
	public String getItem_cd() {
		return item_cd;
	}
	public void setItem_cd(String item_cd) {
		this.item_cd = item_cd;
	}
	public String getDis_yn() {
		return dis_yn;
	}
	public void setDis_yn(String dis_yn) {
		this.dis_yn = dis_yn;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSum_text() {
		return sum_text;
	}
	public void setSum_text(String sum_text) {
		this.sum_text = sum_text;
	}
	public String getCnsd_level() {
		return cnsd_level;
	}
	public void setCnsd_level(String cnsd_level) {
		this.cnsd_level = cnsd_level;
	}
	public String getHqreqcheck() {
		return hqreqcheck;
	}
	public void setHqreqcheck(String hqreqcheck) {
		this.hqreqcheck = hqreqcheck;
	}
	public String getCheck_yn() {
		return check_yn;
	}
	public void setCheck_yn(String check_yn) {
		this.check_yn = check_yn;
	}
	public String getRe_dt() {
		return re_dt;
	}
	public void setRe_dt(String re_dt) {
		this.re_dt = re_dt;
	}
	public String getAsgn_dt() {
		return asgn_dt;
	}
	public void setAsgn_dt(String asgn_dt) {
		this.asgn_dt = asgn_dt;
	}
	public String getCntrt_top30_cus() {
		return cntrt_top30_cus;
	}
	public void setCntrt_top30_cus(String cntrt_top30_cus) {
		this.cntrt_top30_cus = cntrt_top30_cus;
	}
	public String getCntrt_rel_prd() {
		return cntrt_rel_prd;
	}
	public void setCntrt_rel_prd(String cntrt_rel_prd) {
		this.cntrt_rel_prd = cntrt_rel_prd;
	}
	public String getCntrt_src_cont_drft() {
		return cntrt_src_cont_drft;
	}
	public void setCntrt_src_cont_drft(String cntrt_src_cont_drft) {
		this.cntrt_src_cont_drft = cntrt_src_cont_drft;
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
	public void setEvent_but(String event_but) {
		this.event_but = event_but;
	}
	public String getReq_dt() {
		return req_dt;
	}
	public void setReq_dt(String req_dt) {
		this.req_dt = req_dt;
	}
	public String getPage_div() {
		return page_div;
	}
	public String getMain_man_id() {
		return main_man_id;
	}
	public void setMain_man_id(String main_man_id) {
		this.main_man_id = main_man_id;
	}
	public void setPage_div(String page_div) {
		this.page_div = page_div;
	}
	public String getTable_div() {
		return table_div;
	}
	public void setTable_div(String table_div) {
		this.table_div = table_div;
	}
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
	
	
	
	
	public String getSrch_cntrt_nm() {
		return srch_cntrt_nm;
	}
	public void setSrch_cntrt_nm(String srch_cntrt_nm) {
		this.srch_cntrt_nm = srch_cntrt_nm;
	}
	public String getAttr_frm() {
		return attr_frm;
	}
	public String getCntrt_srch_yn() {
		return cntrt_srch_yn;
	}
	public void setCntrt_srch_yn(String cntrt_srch_yn) {
		this.cntrt_srch_yn = cntrt_srch_yn;
	}
	public String getReq_title() {
		return req_title;
	}
	public void setReq_title(String req_title) {
		this.req_title = req_title;
	}
	public String getTrnsf_demnd_dt() {
		return trnsf_demnd_dt;
	}
	public void setTrnsf_demnd_dt(String trnsf_demnd_dt) {
		this.trnsf_demnd_dt = trnsf_demnd_dt;
	}
	public String getTrnsf_demndman_nm() {
		return trnsf_demndman_nm;
	}
	public void setTrnsf_demndman_nm(String trnsf_demndman_nm) {
		this.trnsf_demndman_nm = trnsf_demndman_nm;
	}
	public String getTrnsf_hndlman_nm() {
		return trnsf_hndlman_nm;
	}
	public void setTrnsf_hndlman_nm(String trnsf_hndlman_nm) {
		this.trnsf_hndlman_nm = trnsf_hndlman_nm;
	}
	public String getTrnsf_demnd_cause() {
		return trnsf_demnd_cause;
	}
	public void setTrnsf_demnd_cause(String trnsf_demnd_cause) {
		this.trnsf_demnd_cause = trnsf_demnd_cause;
	}
	public String getTrnsf_hndl_cause() {
		return trnsf_hndl_cause;
	}
	public void setTrnsf_hndl_cause(String trnsf_hndl_cause) {
		this.trnsf_hndl_cause = trnsf_hndl_cause;
	}
	public String getVc_cnsd_demnd_cont() {
		return vc_cnsd_demnd_cont;
	}
	public void setVc_cnsd_demnd_cont(String vc_cnsd_demnd_cont) {
		this.vc_cnsd_demnd_cont = vc_cnsd_demnd_cont;
	}
	public String getTrnsf_trans_status() {
		return trnsf_trans_status;
	}
	public void setTrnsf_trans_status(String trnsf_trans_status) {
		this.trnsf_trans_status = trnsf_trans_status;
	}
	public String getTrnsf_trans_status_nm() {
		return trnsf_trans_status_nm;
	}
	public void setTrnsf_trans_status_nm(String trnsf_trans_status_nm) {
		this.trnsf_trans_status_nm = trnsf_trans_status_nm;
	}
	public String getParam_seqno() {
		return param_seqno;
	}
	public void setParam_seqno(String param_seqno) {
		this.param_seqno = param_seqno;
	}
	public String getTrnsf_hndl_dt() {
		return trnsf_hndl_dt;
	}
	public void setTrnsf_hndl_dt(String trnsf_hndl_dt) {
		this.trnsf_hndl_dt = trnsf_hndl_dt;
	}
	public String getMn_cnsd_dept() {
		return mn_cnsd_dept;
	}
	public void setMn_cnsd_dept(String mn_cnsd_dept) {
		this.mn_cnsd_dept = mn_cnsd_dept;
	}
	public String getVc_cnsd_dept() {
		return vc_cnsd_dept;
	}
	public void setVc_cnsd_dept(String vc_cnsd_dept) {
		this.vc_cnsd_dept = vc_cnsd_dept;
	}
	public String getCnsd_dept() {
		return cnsd_dept;
	}
	public void setCnsd_dept(String cnsd_dept) {
		this.cnsd_dept = cnsd_dept;
	}
	public String getList_respman_id() {
		return list_respman_id;
	}
	public void setList_respman_id(String list_respman_id) {
		this.list_respman_id = list_respman_id;
	}
	public String[] getList_respman_ids() {
		return list_respman_ids;
	}
	public void setList_respman_ids(String[] list_respman_ids) {
		this.list_respman_ids = list_respman_ids;
	}
	public String getList_respman_nm() {
		return list_respman_nm;
	}
	public void setList_respman_nm(String list_respman_nm) {
		this.list_respman_nm = list_respman_nm;
	}
	public String[] getList_respman_nms() {
		return list_respman_nms;
	}
	public void setList_respman_nms(String[] list_respman_nms) {
		this.list_respman_nms = list_respman_nms;
	}
	public String getBlngt_orgnz() {
		return blngt_orgnz;
	}
	public void setBlngt_orgnz(String blngt_orgnz) {
		this.blngt_orgnz = blngt_orgnz;
	}
	public String getTop_role() {
		return top_role;
	}
	public void setTop_role(String top_role) {
		this.top_role = top_role;
	}
	public String getRespman_apnt_yn() {
		return respman_apnt_yn;
	}
	public void setRespman_apnt_yn(String respman_apnt_yn) {
		this.respman_apnt_yn = respman_apnt_yn;
	}
	public String getMod_dept_cd() {
		return mod_dept_cd;
	}
	public void setMod_dept_cd(String mod_dept_cd) {
		this.mod_dept_cd = mod_dept_cd;
	}
	public String getMod_dept_nm() {
		return mod_dept_nm;
	}
	public void setMod_dept_nm(String mod_dept_nm) {
		this.mod_dept_nm = mod_dept_nm;
	}
	public String getChg_prgrs_status() {
		return chg_prgrs_status;
	}
	public void setChg_prgrs_status(String chg_prgrs_status) {
		this.chg_prgrs_status = chg_prgrs_status;
	}
	public String getChg_depth_status() {
		return chg_depth_status;
	}
	public void setChg_depth_status(String chg_depth_status) {
		this.chg_depth_status = chg_depth_status;
	}
	public String getRejct_opnn() {
		return rejct_opnn;
	}
	public void setRejct_opnn(String rejct_opnn) {
		this.rejct_opnn = rejct_opnn;
	}
	
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
	private String cntrt_untprc;
	private String cntrt_amt;
	private String crrncy_unit;
	private String payment_cond;
	private String auto_rnew_yn;
	private String exprt_notiday;
	private String oblgt_lmt;
	private String spcl_cond;
	private String cnclsn_plndday;
	private String cntrt_respman_id;
	private String cntrt_respman_nm;
	private String cntrt_resp_dept;
	private String cntrt_resp_dept_nm;
	private String cntrt_resp_up_dept;
	private String cntrt_respman_jikgup_nm;
	private String reg_intnl_dept_cd;
	private String cntrt_oppnt_cd;
	private String sign_plndman_id;
	private String sign_plndman_nm;
	private String seal_mthd;
	private String cntrt_cnclsn_yn;
	private String signman_id;
	private String signman_nm;
	private String signman_jikgup_nm;
	private String sign_dept_nm;
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
	private String org_strg_pos;
	private String prcs_depth;
	private String depth_status;
	private String cntrt_status;
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
	private String cntrt_chge_demndday;
	private String cntrt_chge_demndman_id;
	private String cntrt_chge_demndman_nm;
	private String cntrt_chge_demnd_cause;
	private String cntrt_chge_plndday;
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
	private String seal_ffmt_dept_nm;
	private String seal_ffmtman_jikgup_nm;
	private String seal_ffmtday;
	private String reg_operdiv;
	private String req_operdiv;
	
	private String start_index;
	private String start_index_pop;
	private String end_index;
	private String end_index_pop;
	private String curPage;
	private String body_mime1;
	private String en_cntrt_nm;
	
	private String cnsdreq_id;
	
	// 추가 되는 내용들
	private String cntrt_top30_cus;
	private String cntrt_rel_prd;
	private String cntrt_src_cont_drft;

	private String view_gbn;	//Sungwoo added 2014-09-25 reAttch 처리위해 추가

	private String cnclsnaft_cstn_cause;

	public String getCnclsnaft_cstn_cause() {
		return cnclsnaft_cstn_cause;
	}

	public void setCnclsnaft_cstn_cause(String cnclsnaft_cstn_cause) {
		this.cnclsnaft_cstn_cause = cnclsnaft_cstn_cause;
	}


	
	public String getView_gbn() {
		return view_gbn;
	}
	public void setView_gbn(String view_gbn) {
		this.view_gbn = view_gbn;
	}
	public String getCnsdreq_id() {
		return cnsdreq_id;
	}
	public void setCnsdreq_id(String cnsdreq_id) {
		this.cnsdreq_id = cnsdreq_id;
	}
	/**
	 * Tab 계약 Cnt
	 */
	private String tab_cnt;

	public void setTab_cnt(String tab_cnt) {
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
	 * 
	 * @param cnsdman_id
	 */
	public void setCnsdman_id(String cnsdman_id) {
		this.cnsdman_id = cnsdman_id;
	}
	public String getCnsdman_id() {
		return this.cnsdman_id;
	}

	private String cnsdman_nm;

	/**
	 * 검토자_명
	 * 
	 * @param cnsdman_nm
	 */
	public void setCnsdman_nm(String cnsdman_nm) {
		this.cnsdman_nm = cnsdman_nm;
	}
	public String getCnsdman_nm() {
		return this.cnsdman_nm;
	}

	private String cnsd_dt;

	/**
	 * 검토일
	 * 
	 * @param cnsDay
	 */
	public void setCnsd_dt(String cnsd_dt) {
		this.cnsd_dt = cnsd_dt;
	}

	public String getCnsd_dt() {
		return this.cnsd_dt;
	}

	private String cnsd_opnn;
	private String cnsd_memo;

	public String getCnsd_memo() {
		return cnsd_memo;
	}
	public void setCnsd_memo(String cnsd_memo) {
		this.cnsd_memo = cnsd_memo;
	}
	/**
	 * 검토_의견
	 * 
	 * @param cnsd_opnn
	 */
	public void setCnsd_opnn(String cnsd_opnn) {
		this.cnsd_opnn = cnsd_opnn;
	}
	public String getCnsd_opnn() {
		return this.cnsd_opnn;
	}

	private String main_matr_cont;

	/**
	 * 주요_사안_내용 MAIN_MATR_CONT
	 * 
	 * @param main_matr_cont
	 */
	public void setMain_matr_cont(String main_matr_cont) {
		this.main_matr_cont = main_matr_cont;
	}
	public String getMain_matr_cont() {
		return this.main_matr_cont;
	}

	private String apbtman_id;

	/**
	 * 승인자_ID
	 * 
	 * @param apbtman_id
	 */
	public void setApbtman_id(String apbtman_id) {
		this.apbtman_id = apbtman_id;
	}
	public String getApbtman_id() {
		return this.apbtman_id;
	}

	private String apbtman_nm;

	/**
	 * 승인자_명
	 * 
	 * @param apbtman_nm
	 */
	public void setApbtman_nm(String apbtman_nm) {
		this.apbtman_nm = apbtman_nm;
	}
	public String getApbtman_nm() {
		return this.apbtman_nm;
	}

	private String apbtDay;

	/**
	 * 승인일
	 * 
	 * @param apbtDay
	 */
	public void setApbtDay(String apbtDay) {
		this.apbtDay = apbtDay;
	}
	public String getApbtDay() {
		return this.apbtDay;
	}

	private String apbt_opnn;

	/**
	 * 승인_의견
	 * 
	 * @param apbt_opnn
	 */
	public void setApbt_opnn(String apbt_opnn) {
		this.apbt_opnn = apbt_opnn;
	}
	public String getApbt_opnn() {
		return this.apbt_opnn;
	}
	
	private String apbt_memo;

	/**
	 * 승인자_메모
	 * 
	 * @param apbt_memo
	 */
	public void setApbt_memo(String apbt_memo) {
		this.apbt_memo = apbt_memo;
	}
	public String getApbt_memo() {
		return this.apbt_memo;
	}

	private String auto_apbt_yn;
	
	/**
	 * 전결여부
	 * 
	 * @param auto_apbt_yn
	 */
	public String getAuto_apbt_yn() {
		return auto_apbt_yn;
	}
	public void setAuto_apbt_yn(String auto_apbt_yn) {
		this.auto_apbt_yn = auto_apbt_yn;
	}
	
	private String fast_track_div;
	
	/**
	 * 전결여부
	 * 
	 * @param auto_apbt_yn
	 */
	public String getFast_track_div() {
		return fast_track_div;
	}
	public void setFast_track_div(String fast_track_div) {
		this.fast_track_div = fast_track_div;
	}
	
	
	private String cnsd_status;

	/**
	 * 검토_상태
	 * 
	 * @param cnsd_status
	 */
	public void setCnsd_status(String cnsd_status) {
		this.cnsd_status = cnsd_status;
	}
	public String getCnsd_status() {
		return this.cnsd_status;
	}

	private String risk;

	/**
	 * RISK
	 * 
	 * @param risk
	 */
	public void setRisk(String risk) {
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
	private String master_cntrt_id;
	private String active_cntrt_id;
	private String[] master_cntrt_ids;

	/**
	 * 모_계약_ID
	 * 
	 * @param parent_cntrt_id
	 */
	public void setArr_parent_cntrt_id(String[] arr_parent_cntrt_id) {
		this.arr_parent_cntrt_id = arr_parent_cntrt_id;
	}

	public String[] getArr_parent_cntrt_id() {
		return this.arr_parent_cntrt_id;
	}

	public void setParent_cntrt_id(String parent_cntrt_id) {
		this.parent_cntrt_id = parent_cntrt_id;
	}

	public String getParent_cntrt_id() {
		return this.parent_cntrt_id;
	}

	public String getMaster_cntrt_id() {
		return master_cntrt_id;
	}
	
	public String getActive_cntrt_id() {
		return active_cntrt_id;
	}
	
	public void setActive_cntrt_id(String active_cntrt_id) {
		this.active_cntrt_id = active_cntrt_id;
	}
	
	public void setMaster_cntrt_id(String master_cntrt_id) {
		this.master_cntrt_id = master_cntrt_id;
	}

	public String[] getMaster_cntrt_ids() {
		return master_cntrt_ids;
	}

	public void setMaster_cntrt_ids(String[] master_cntrt_ids) {
		this.master_cntrt_ids = master_cntrt_ids;
	}

	private String[] arr_rel_type;
	private String rel_type;
	
	private String rel_defn;

	public String getRel_defn() {
		return rel_defn;
	}
	public void setRel_defn(String rel_defn) {
		this.rel_defn = rel_defn;
	}
	/**
	 * 관련_유형
	 * 
	 * @param rel_type
	 */

	public void setArr_rel_type(String[] arr_rel_type) {
		this.arr_rel_type = arr_rel_type;
	}

	public String[] getArr_rel_type() {
		return this.arr_rel_type;
	}

	public void setRel_type(String rel_type) {
		this.rel_type = rel_type;
	}

	public String getRel_type() {
		return this.rel_type;
	}

	private String[] arr_expl;
	private String expl;

	public void setArr_expl(String[] arr_expl) {
		this.arr_expl = arr_expl;
	}

	public String[] getArr_expl() {
		return this.arr_expl;
	}

	public void setExpl(String expl) {
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
	 * 
	 * @param attr_seqno
	 */
	public void setAttr_seqno(String attr_seqno) {
		this.attr_seqno = attr_seqno;
	}

	public String getAttr_seqno() {
		return this.attr_seqno;
	}

	private String attr_nm;

	/**
	 * 속성_명
	 * 
	 * @param attr_nm
	 */
	public void setAttr_nm(String attr_nm) {
		this.attr_nm = attr_nm;
	}

	public String getAttr_nm() {
		return this.attr_nm;
	}

	private String attr_nm_eng;

	/**
	 * 속성_명_영문
	 * 
	 * @param attr_nm_eng
	 */
	public void setAttr_nm_eng(String attr_nm_eng) {
		this.attr_nm_eng = attr_nm_eng;
	}

	public String getAttr_nm_eng() {
		return this.attr_nm_eng;
	}

	private String attr_expl;

	/**
	 * 속성_설명
	 * 
	 * @param attr_expl
	 */
	public void setAttr_expl(String attr_expl) {
		this.attr_expl = attr_expl;
	}

	public String getAttr_expl() {
		return this.attr_expl;
	}

	private String attr_frm;

	/**
	 * 속성_형태
	 * 
	 * @param attr_frm
	 */
	public void setAttr_frm(String attr_frm) {
		this.attr_frm = attr_frm;
	}

	public String getAtr_frm() {
		return this.attr_frm;
	}

	private String attr_sz_wdth;

	/**
	 * 속성_크기_가로
	 * 
	 * @param attr_sz_wdth
	 */
	public void setAttr_sz_wdth(String attr_sz_wdth) {
		this.attr_sz_wdth = attr_sz_wdth;
	}

	public String getAttr_sz_wdth() {
		return this.attr_sz_wdth;
	}

	private String attr_sz_hght;

	/**
	 * 속성_크기_세로
	 * 
	 * @param attr_sz_hght
	 */
	public void setAttr_sz_hght(String attr_sz_hght) {
		this.attr_sz_hght = attr_sz_hght;
	}

	public String getAttr_sz_hght() {
		return this.attr_sz_hght;
	}

	private String attr_max_sz;

	/**
	 * 속성_최대_크기
	 * 
	 * @param attr_max_sz
	 */
	public void setAttr_max_sz(String attr_max_sz) {
		this.attr_max_sz = attr_max_sz;
	}

	public String getAttr_max_sz() {
		return this.attr_max_sz;
	}

	private String mndtry_yn;

	/**
	 * 필수_여부
	 * 
	 * @param mndtry_yn
	 */
	public void setMndtry_yn(String mndtry_yn) {
		this.mndtry_yn = mndtry_yn;
	}

	public String getMndtry_yn() {
		return this.mndtry_yn;
	}

	private String cd_yn;

	/**
	 * 코드_여부
	 * 
	 * @param cd_yn
	 */
	public void setCd_yn(String cd_yn) {
		this.cd_yn = cd_yn;
	}

	public String getCd_yn() {
		return this.cd_yn;
	}

	private String cd_sys;

	/**
	 * 코드_시스템
	 * 
	 * @param cd_sys
	 */
	public void setCd_sys(String cd_sys) {
		this.cd_sys = cd_sys;
	}

	public String getCd_sys() {
		return this.cd_sys;
	}

	private String cd_grp;

	/**
	 * 코드_그룹
	 * 
	 * @param cd_grp
	 */
	public void setCd_grp(String cd_grp) {
		this.cd_grp = cd_grp;
	}

	public String getCd_grp() {
		return this.cd_grp;
	}

	private String prnt_srt;

	/**
	 * 출력_순서
	 * 
	 * @param prnt_srt
	 */
	public void setPrnt_srt(String prnt_srt) {
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
	 * 
	 * @param attr_cd
	 */
	public void setArr_attr_seqno(String[] arr_attr_seqno) {
		this.arr_attr_seqno = arr_attr_seqno;
	}

	public String[] getArr_attr_seqno() {
		return this.arr_attr_seqno;
	}

	private String[] arr_attr_cd;
	private String attr_cd;

	/**
	 * 속성_코드
	 * 
	 * @param attr_cd
	 */
	public void setArr_attr_cd(String[] arr_attr_cd) {
		this.arr_attr_cd = arr_attr_cd;
	}

	public String[] getArr_attr_cd() {
		return this.arr_attr_cd;
	}

	public void setAttr_cd(String attr_cd) {
		this.attr_cd = attr_cd;
	}

	public String getAttr_cd() {
		return this.attr_cd;
	}

	private String[] arr_attr_cont;
	private String attr_cont;

	/**
	 * 속성_내용
	 * 
	 * @param attr_cont
	 */
	public void setArr_attr_cont(String[] arr_attr_cont) {
		this.arr_attr_cont = arr_attr_cont;
	}

	public String[] getArr_attr_cont() {
		return this.arr_attr_cont;
	}

	public void setAttr_cont(String attr_cont) {
		this.attr_cont = attr_cont;
	}

	public String getAttr_cont() {
		return this.attr_cont;
	}

	/**
	 * 계약_권한요청 TN_CLM_CONTRACT_AUTHREQ
	 */
	// private String cntrt_id;
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
	private String trgtman_jikgup_nm;
	private String[] arr_trgtman_jikgup_nm;
	private String client_modify_div;
	private String rd_auth;
	private String auth_startday;
	private String auth_endday;
	private String demnd_status;
	private String demnd_gbn;
	private String demnd_dt;
	private String hndl_dt;
	private String hndlman_id;
	private String hndlman_nm;
	private String hndl_cont;
	
	//getSubmit_status
	private String submit_status;
	

	public String getSubmit_status() {
		return submit_status;
	}
	public void setSubmit_status(String submit_status) {
		this.submit_status = submit_status;
	}
	// private String del_yn;
	// private String del_dt;
	// private String del_id;
	// private String del_id;
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
	public void setArr_trgtman_id(String[] arr_trgtman_id) {
		this.arr_trgtman_id = arr_trgtman_id;
	}

	public void setTrgtman_nm(String trgtman_nm) {
		this.trgtman_nm = trgtman_nm;
	}

	/**
	 * 
	 * @param arr_trgtman_nm
	 */
	public void setArr_trgtman_nm(String[] arr_trgtman_nm) {
		this.arr_trgtman_nm = arr_trgtman_nm;
	}

	public void setTrgtman_dept_nm(String trgtman_dept_nm) {
		this.trgtman_dept_nm = trgtman_dept_nm;
	}

	/**
	 * 
	 * @param arr_trgtman_dept_nm
	 */
	public void setArr_trgtman_dept_nm(String[] arr_trgtman_dept_nm) {
		this.arr_trgtman_dept_nm = arr_trgtman_dept_nm;
	}
	
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
	
	public String getClient_modify_div() {
		return client_modify_div;
	}
	public void setClient_modify_div(String client_modify_div) {
		this.client_modify_div = client_modify_div;
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
	 * 
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
	public String[] getArr_trgtman_id() {
		return this.arr_trgtman_id;
	}

	public String getTrgtman_nm() {
		return this.trgtman_nm;
	}

	/**
	 * 
	 * @return
	 */
	public String[] getArr_trgtman_nm() {
		return this.arr_trgtman_nm;
	}

	public String getTrgtman_dept_nm() {
		return this.trgtman_dept_nm;
	}

	public String[] getArr_trgtman_dept_nm() {
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

	public void setCntrt_untprc(String cntrt_untprc) {
		this.cntrt_untprc = cntrt_untprc;
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
	
	
	private String etc_main_cont;
	
	public void setEtc_main_cont(String etc_main_cont){
		this.etc_main_cont = etc_main_cont;
	}
	public String getEtc_main_cont(){
		return this.etc_main_cont;
	}
	
	private String cntrt_untprc_expl;
	public void setCntrt_untprc_expl(String cntrt_untprc_expl) {
		this.cntrt_untprc_expl = cntrt_untprc_expl;
	}
	public String getCntrt_untprc_expl() {
		return this.cntrt_untprc_expl;
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

	public void setCntrt_respman_id(String cntrt_respman_id) {
		this.cntrt_respman_id = cntrt_respman_id;
	}

	public void setCntrt_respman_nm(String cntrt_respman_nm) {
		this.cntrt_respman_nm = cntrt_respman_nm;
	}

	public void setCntrt_oppnt_cd(String cntrt_oppnt_cd) {
		this.cntrt_oppnt_cd = cntrt_oppnt_cd;
	}

	public void setSign_plndman_id(String sign_plndman_id) {
		this.sign_plndman_id = sign_plndman_id;
	}

	public void setSign_plndman_nm(String sign_plndman_nm) {
		this.sign_plndman_nm = sign_plndman_nm;
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
	
	public void setCntrt_status(String cntrt_status) {
		this.cntrt_status = cntrt_status;
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

	public void setReg_operdiv(String reg_operdiv) {
		this.reg_operdiv = reg_operdiv;
	}
	
	public void setReq_operdiv(String req_operdiv) {
		this.req_operdiv = req_operdiv;
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

	public void setBody_mime1(String body_mime1) {
		this.body_mime1 = body_mime1;
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

	public String getCntrt_untprc() {
		return this.cntrt_untprc;
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

	public String getCntrt_respman_id() {
		return this.cntrt_respman_id;
	}

	public String getCntrt_respman_nm() {
		return this.cntrt_respman_nm;
	}

	public String getCntrt_oppnt_cd() {
		return this.cntrt_oppnt_cd;
	}

	public String getSign_plndman_id() {
		return this.sign_plndman_id;
	}

	public String getSign_plndman_nm() {
		return this.sign_plndman_nm;
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

	public String getCntrt_status() {
		return this.cntrt_status;
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

	public String getReg_operdiv() {
		return this.reg_operdiv;
	}
	
	public String getReq_operdiv() {
		return req_operdiv;
	}

	public String getStart_index() {
		return this.start_index;
	}
	public String getStart_index_pop() {
		return start_index_pop;
	}
	public void setStart_index_pop(String start_index_pop) {
		this.start_index_pop = start_index_pop;
	}
	public String getEnd_index() {
		return this.end_index;
	}
	public String getEnd_index_pop() {
		return end_index_pop;
	}
	public void setEnd_index_pop(String end_index_pop) {
		this.end_index_pop = end_index_pop;
	}
	public String getCurPage() {
		return this.curPage;
	}

	public String getBody_mime1() {
		return this.body_mime1;
	}
	
	
	/**
	 * 검토의뢰 첨부파일
	 */
	
	private String fileInfos1;
	private String fileInfos2;
	private String fileInfos3; 
	private String fileInfos4;
	private String fileInfos5;
	
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
	
	
	
	

	/**
	 * TN_CLM_CONTRACT_DEPTCNSD - 계약관리_계약별_부서검토 *
	 */
	/**
	 * 검토_부서 CNSD_DEPT VARCHAR(9) NOT NULL
	 */
	private String deptcnsd_cnsd_dept;
	/**
	 * 검토자_ID CNSDMAN_ID VARCHAR(9) NOT NULL
	 */
	private String deptcnsd_cnsdman_id;
	/**
	 * 검토자_명 CNSDMAN_NM VARCHAR(100) NULL
	 */
	private String deptcnsd_cnsdman_nm;
	/**
	 * 검토_일시 CNSD_DT TIMESTAMP NOT NULL
	 */
	private String deptcnsd_cnsd_dt;
	/**
	 * 검토_의견 CNSD_OPNN CLOB NULL
	 */
	private String deptcnsd_cnsd_opnn;
	private String ipdeptcnsd_cnsd_opnn;
	/**
	 * 승인자_ID APBTMAN_ID VARCHAR(9) NULL
	 */
	private String deptcnsd_apbtman_id;
	/**
	 * 승인자_명 APBTMAN_NM VARCHAR(100) NULL
	 */
	private String deptcnsd_apbtman_nm;
	/**
	 * 승인_일시 APBT_DT TIMESTAMP NULL
	 */
	private String deptcnsd_apbt_dt;
	/**
	 * 승인_의견 APBT_OPNN VARCHAR(100) NULL
	 */
	private String deptcnsd_apbt_opnn;
	/**
	 * 검토_상태 CNSD_STATUS VARCHAR(9) NULL
	 */
	private String deptcnsd_cnsd_status;

	// SET
	/**
	 * 검토_부서 CNSD_DEPT VARCHAR(9) NOT NULL
	 */
	public void setDeptcnsd_cnsd_dept(String deptcnsd_cnsd_dept) {
		this.deptcnsd_cnsd_dept = deptcnsd_cnsd_dept;
	}

	/**
	 * 검토자_ID CNSDMAN_ID VARCHAR(9) NOT NULL
	 */
	public void setDeptcnsd_cnsdman_id(String deptcnsd_cnsdman_id) {
		this.deptcnsd_cnsdman_id = deptcnsd_cnsdman_id;
	}

	/**
	 * 검토자_명 CNSDMAN_NM VARCHAR(100) NULL
	 */
	public void setDeptcnsd_cnsdman_nm(String deptcnsd_cnsdman_nm) {
		this.deptcnsd_cnsdman_nm = deptcnsd_cnsdman_nm;
	}

	/**
	 * 검토_일시 CNSD_DT TIMESTAMP NOT NULL
	 */
	public void setDeptcnsd_cnsd_dt(String deptcnsd_cnsd_dt) {
		this.deptcnsd_cnsd_dt = deptcnsd_cnsd_dt;
	}

	/**
	 * 검토_의견 CNSD_OPNN CLOB NULL
	 */
	public void setDeptcnsd_cnsd_opnn(String deptcnsd_cnsd_opnn) {
		this.deptcnsd_cnsd_opnn = deptcnsd_cnsd_opnn;
	}

	public void setIpdeptcnsd_cnsd_opnn(String ipdeptcnsd_cnsd_opnn) {
		this.ipdeptcnsd_cnsd_opnn = ipdeptcnsd_cnsd_opnn;
	}

	/**
	 * 승인자_ID APBTMAN_ID VARCHAR(9) NULL
	 */
	public void setDeptcnsd_apbtman_id(String deptcnsd_apbtman_id) {
		this.deptcnsd_apbtman_id = deptcnsd_apbtman_id;
	}

	/**
	 * 승인자_명 APBTMAN_NM VARCHAR(100) NULL
	 */
	public void setDeptcnsd_apbtman_nm(String deptcnsd_apbtman_nm) {
		this.deptcnsd_apbtman_nm = deptcnsd_apbtman_nm;
	}

	/**
	 * 승인_일시 APBT_DT TIMESTAMP NULL
	 */
	public void setDeptcnsd_apbt_dt(String deptcnsd_apbt_dt) {
		this.deptcnsd_apbt_dt = deptcnsd_apbt_dt;
	}

	/**
	 * 승인_의견 APBT_OPNN VARCHAR(100) NULL
	 */
	public void setDeptcnsd_apbt_opnn(String deptcnsd_apbt_opnn) {
		this.deptcnsd_apbt_opnn = deptcnsd_apbt_opnn;
	}

	/**
	 * 검토_상태 CNSD_STATUS VARCHAR(9) NULL
	 */
	public void setDeptcnsd_cnsd_status(String deptcnsd_cnsd_status) {
		this.deptcnsd_cnsd_status = deptcnsd_cnsd_status;
	}

	// GET
	/**
	 * 검토_부서 CNSD_DEPT VARCHAR(9) NOT NULL
	 */
	public String getDeptcnsd_cnsd_dept() {
		return this.deptcnsd_cnsd_dept;
	}

	/**
	 * 검토자_ID CNSDMAN_ID VARCHAR(9) NOT NULL
	 */
	public String getDeptcnsd_cnsdman_id() {
		return this.deptcnsd_cnsdman_id;
	}

	/**
	 * 검토자_명 CNSDMAN_NM VARCHAR(100) NULL
	 */
	public String getDeptcnsd_cnsdman_nm() {
		return this.deptcnsd_cnsdman_nm;
	}

	/**
	 * 검토_일시 CNSD_DT TIMESTAMP NOT NULL
	 */
	public String getDeptcnsd_cnsd_dt() {
		return this.deptcnsd_cnsd_dt;
	}

	/**
	 * 검토_의견 CNSD_OPNN CLOB NULL
	 */
	public String getDeptcnsd_cnsd_opnn() {
		return this.deptcnsd_cnsd_opnn;
	}

	public String getIpdeptcnsd_cnsd_opnn() {
		return this.ipdeptcnsd_cnsd_opnn;
	}

	/**
	 * 승인자_ID APBTMAN_ID VARCHAR(9) NULL
	 */
	public String getDeptcnsd_apbtman_id() {
		return this.deptcnsd_apbtman_id;
	}

	/**
	 * 승인자_명 APBTMAN_NM VARCHAR(100) NULL
	 */
	public String getDeptcnsd_apbtman_nm() {
		return this.deptcnsd_apbtman_nm;
	}

	/**
	 * 승인_일시 APBT_DT TIMESTAMP NULL
	 */
	public String getDeptcnsd_apbt_dt() {
		return this.deptcnsd_apbt_dt;
	}

	/**
	 * 승인_의견 APBT_OPNN VARCHAR(100) NULL
	 */
	public String getDeptcnsd_apbt_opnn() {
		return this.deptcnsd_apbt_opnn;
	}

	/**
	 * 검토_상태 CNSD_STATUS VARCHAR(9) NULL
	 */
	public String getDeptcnsd_cnsd_status() {
		return this.deptcnsd_cnsd_status;
	}
	
	private String body_mime;
	

	public String getBody_mime() {
		return body_mime;
	}
	public void setBody_mime(String body_mime) {
		this.body_mime = body_mime;
	}

	/**
	 * 사안개요
	 */
	private String body_mime_dept_main;

	public void setBody_mime_dept_main(String body_mime_dept_main) {
		this.body_mime_dept_main = body_mime_dept_main;
	}

	public String getBody_mime_dept_main() {
		return this.body_mime_dept_main;
	}

	/**
	 * 일반조항
	 */
	private String body_mime_cnsd_memo;

	public void setBody_mime_cnsd_memo(String body_mime_cnsd_memo) {
		this.body_mime_cnsd_memo = body_mime_cnsd_memo;
	}

	public String getBody_mime_cnsd_memo() {
		return this.body_mime_cnsd_memo;
	}

	/**
	 * IP조항 검토의뢰
	 */
	private String body_mime_dept_cnsd;

	public void setBody_mime_dept_cnsd(String body_mime_dept_cnsd) {
		this.body_mime_dept_cnsd = body_mime_dept_cnsd;
	}

	public String getBody_mime_dept_cnsd() {
		return this.body_mime_dept_cnsd;
	}

	/**
	 * 최종확인 의견
	 */
	private String body_mime_cnsd_opnn;

	public void setBody_mime_cnsd_opnn(String body_mime_cnsd_opnn) {
		this.body_mime_cnsd_opnn = body_mime_cnsd_opnn;
	}

	public String getBody_mime_cnsd_opnn() {
		return this.body_mime_cnsd_opnn;
	}

	/**
	 * 첨부파일 #1
	 */	

	private String fileInfosReContract;
	private String fileInfosReEtc;
	private String fileInfosReGita;
	
	public String getFileInfosReContract() {
		return fileInfosReContract;
	}
	public void setFileInfosReContract(String fileInfosReContract) {
		this.fileInfosReContract = fileInfosReContract;
	}
	public String getFileInfosReEtc() {
		return fileInfosReEtc;
	}
	public void setFileInfosReEtc(String fileInfosReEtc) {
		this.fileInfosReEtc = fileInfosReEtc;
	}
	public String getFileInfosReGita() {
		return fileInfosReGita;
	}
	public void setFileInfosReGita(String fileInfosReGita) {
		this.fileInfosReGita = fileInfosReGita;
	}
	
	private String fileInfosContract;
	private String fileInfosEtc;
	private String fileInfosGita;
	
	public String getFileInfosContract() {
		return fileInfosContract;
	}
	public void setFileInfosContract(String fileInfosContract) {
		this.fileInfosContract = fileInfosContract;
	}
	public String getFileInfosEtc() {
		return fileInfosEtc;
	}
	public void setFileInfosEtc(String fileInfosEtc) {
		this.fileInfosEtc = fileInfosEtc;
	}
	public String getFileInfosGita() {
		return fileInfosGita;
	}
	public void setFileInfosGita(String fileInfosGita) {
		this.fileInfosGita = fileInfosGita;
	}
	
	private String fileInfosReVcContract;
	private String fileInfosReVcEtc;
	private String fileInfosReVcGita;
	
	public String getFileInfosReVcContract() {
		return fileInfosReVcContract;
	}
	public void setFileInfosReVcContract(String fileInfosReVcContract) {
		this.fileInfosReVcContract = fileInfosReVcContract;
	}
	public String getFileInfosReVcEtc() {
		return fileInfosReVcEtc;
	}
	public void setFileInfosReVcEtc(String fileInfosReVcEtc) {
		this.fileInfosReVcEtc = fileInfosReVcEtc;
	}
	public String getFileInfosReVcGita() {
		return fileInfosReVcGita;
	}
	public void setFileInfosReVcGita(String fileInfosReVcGita) {
		this.fileInfosReVcGita = fileInfosReVcGita;
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
	public String getSeal_ffmt_dept_nm() {
		return seal_ffmt_dept_nm;
	}
	public void setSeal_ffmt_dept_nm(String seal_ffmt_dept_nm) {
		this.seal_ffmt_dept_nm = seal_ffmt_dept_nm;
	}
	public String getSeal_ffmtman_jikgup_nm() {
		return seal_ffmtman_jikgup_nm;
	}
	public void setSeal_ffmtman_jikgup_nm(String seal_ffmtman_jikgup_nm) {
		this.seal_ffmtman_jikgup_nm = seal_ffmtman_jikgup_nm;
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
	public String getReg_intnl_dept_cd() {
		return reg_intnl_dept_cd;
	}
	public void setReg_intnl_dept_cd(String reg_intnl_dept_cd) {
		this.reg_intnl_dept_cd = reg_intnl_dept_cd;
	}
	public String getDemnd_gbn() {
		return demnd_gbn;
	}
	public void setDemnd_gbn(String demnd_gbn) {
		this.demnd_gbn = demnd_gbn;
	}
	public String getGbn_last() {
		return gbn_last;
	}
	public void setGbn_last(String gbn_last) {
		this.gbn_last = gbn_last;
	}
	public String getMn_respman_apnt_yn() {
		return mn_respman_apnt_yn;
	}
	public void setMn_respman_apnt_yn(String mn_respman_apnt_yn) {
		this.mn_respman_apnt_yn = mn_respman_apnt_yn;
	}
	public String getVc_respman_apnt_yn() {
		return vc_respman_apnt_yn;
	}
	public void setVc_respman_apnt_yn(String vc_respman_apnt_yn) {
		this.vc_respman_apnt_yn = vc_respman_apnt_yn;
	}
	public String getEn_cntrt_nm() {
		return en_cntrt_nm;
	}
	public void setEn_cntrt_nm(String en_cntrt_nm) {
		this.en_cntrt_nm = en_cntrt_nm;
	}
	public String getComp_cd() {
		return comp_cd;
	}
	public void setComp_cd(String comp_cd) {
		this.comp_cd = comp_cd;
	}
	public String getNot_appr_yn() {
		return not_appr_yn;
	}
	public void setNot_appr_yn(String not_appr_yn) {
		this.not_appr_yn = not_appr_yn;
	}
	public String getSolo_yn() {
		return solo_yn;
	}
	public void setSolo_yn(String solo_yn) {
		this.solo_yn = solo_yn;
	}
	
	// 2014-04-17 Kevin added.
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
	
	// 2024-11-21 Fernando
	private String dpAgreement = null;
	public String getDpAgreement() {
		return dpAgreement;
	}
	public void setDpAgreement(String dpAgreement) {
		this.dpAgreement = dpAgreement;
	}
}