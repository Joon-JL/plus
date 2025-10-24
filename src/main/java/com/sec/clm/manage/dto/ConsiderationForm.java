package com.sec.clm.manage.dto;

import com.sds.secframework.common.dto.CommonForm;

public class ConsiderationForm extends CommonForm {
	private String cnsdreq_id;
	private String mod_cntrt_id;
	private String mn_cnsd_dept;
	private String mn_respman_apnt_yn;
	private String vc_cnsd_dept;
	private String vc_cnsd_demnd_dt;
	private String vc_cnsd_demnd_cont;
	private String vc_respman_apnt_yn;
	private String dmstfrgn_gbn;
	private String draft_demnd_yn;
	private String plndbn_req_yn;
	private String last_cnsd_yn;
	private String prev_cnsdreq_id;
	private String req_title;
	private String reqman_id;
	private String reqman_nm;
	private String req_dept;
	private String req_dept_nm;
	private String req_dt;
	private String re_demndday;
	private String lastbn_chge_yn;
	private String cnsd_demnd_cont;
	private String prgrs_status;
	private String rebfr_apprvl_yn;
	private String re_dt;
	private String pub_yn;
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

	private String start_index;
	private String end_index;
	private String curPage;
	private String status;
	
	private String search_cntrt_nm;
	private String search_req_nm;
	
	private String srch_regdt1;
	private String srch_regdt2;
	private String 	srch_tnc_no;
	
	private String arg;
	public String getArg() {
		return arg;
	}
	public void setArg(String arg) {
		this.arg = arg;
	}
	
	
	public String getSrch_regdt1() {
		return srch_regdt1;
	}

	public void setSrch_regdt1(String srch_regdt1) {
		this.srch_regdt1 = srch_regdt1;
	}

	public String getSrch_regdt2() {
		return srch_regdt2;
	}

	public void setSrch_regdt2(String srch_regdt2) {
		this.srch_regdt2 = srch_regdt2;
	}

	public String getSearch_req_nm() {
		return search_req_nm;
	}

	public void setSearch_req_nm(String search_req_nm) {
		this.search_req_nm = search_req_nm;
	}

	public String getSearch_cntrt_nm() {
		return search_cntrt_nm;
	}

	public void setSearch_cntrt_nm(String search_cntrt_nm) {
		this.search_cntrt_nm = search_cntrt_nm;
	}
	/**
	 * 검토 부서
	 * CNSD_DEPT
	 */
	private String cnsd_dept;
	
	public void setCnsd_dept(String cnsd_dept){
		this.cnsd_dept = cnsd_dept;
	}
	
	public String getCnsd_dept() {
		return this.cnsd_dept;
	}
	/**
	 * 부검토_부서_지정_여부
	 * TN_CLM_CONT_CNSDREQ.VCCNSD_DEPT_APNT_YN
	 */
	private String vccnsd_dept_apnt_yn;	
	
	public void setVccnsd_dept_apnt_yn(String vccnsd_dept_apnt_yn){
		this.vccnsd_dept_apnt_yn = vccnsd_dept_apnt_yn;		
	}
	
	public String getVccnsd_dept_apnt_yn() {
		return this.vccnsd_dept_apnt_yn;
	}
	/*계약관리_계악_검토의뢰_담장자 */
	/**
	 * TN_CLM_CONT_CNSDREQ_RESPMAN.RESPMAN_GBN
	 * 담장자구분
	 */
	private String respman_gbn;
	
	public void setRespman_gbn(String respman_gbn){
		this.respman_gbn = respman_gbn;		
	}
	
	public String getRespman_gbn() {
		return this.respman_gbn;
	}	
	
	/**
	 * TN_CLM_CONT_CNSDREQ_RESPMAN.ASGNDAY
	 * 배정일
	 */
	private String assgnDay;
	
	public void setAssgnDay(String assgnDay){
		this.assgnDay = assgnDay;		
	}
	
	public String getAssgnDay() {
		return this.assgnDay;
	}
	
		
	
	
	private String body_mime;
	
	/**의뢰자ID*/
	private String search_reqman_id;
	
	/**계약대상*/
	private String search_contract_taget;
	
	/**step*/  
	private String search_step; 
	
	/**상태 */
	private String search_state; 

	/**검토의뢰제목*/
	private String search_review_title; 
	
	  /** 비즈니스 분류 */
    private String search_biz_clsfcn;                  
    
    	/** 단계 분류 */
    private String search_depth_clsfcn;                    
    	
    	/** 단계 분류 */	
    private String search_cnclsnpurps_bigclsfcn;  
    	
    	/** 체결목적중 분류 */
    private String search_cnclsnpurps_midclsfcn; 
    

	/**의뢰시작일*/
	private String search_start_reqday;
	
	
	/**의뢰종료일*/
	private String search_end_reqday;
	
	
	/**유형타입*/
	private String search_contract_type_nm;
	
	/**의뢰자 */
	private String search_reqman_nm;
	
	
	//SET
	public void setCnsdreq_id(String cnsdreq_id) {
		this.cnsdreq_id = cnsdreq_id;
	}
	public void setMod_cntrt_id(String mod_cntrt_id) {
		this.mod_cntrt_id = mod_cntrt_id;
	}
	
	public void setMn_cnsd_dept(String mn_cnsd_dept) {
		this.mn_cnsd_dept = mn_cnsd_dept;
	}
	public void setMn_respman_apnt_yn(String mn_respman_apnt_yn) {
		this.mn_respman_apnt_yn = mn_respman_apnt_yn;
	}
	public void setVc_cnsd_dept(String vc_cnsd_dept) {
		this.vc_cnsd_dept = vc_cnsd_dept;
	}
	public void setVc_cnsd_demnd_dt(String vc_cnsd_demnd_dt) {
		this.vc_cnsd_demnd_dt = vc_cnsd_demnd_dt;
	}
	public void setVc_cnsd_demnd_cont(String vc_cnsd_demnd_cont) {
		this.vc_cnsd_demnd_cont = vc_cnsd_demnd_cont;
	}
	public void setVc_respman_apnt_yn(String vc_respman_apnt_yn) {
		this.vc_respman_apnt_yn = vc_respman_apnt_yn;
	}
	public void setDmstfrgn_gbn(String dmstfrgn_gbn) {
		this.dmstfrgn_gbn = dmstfrgn_gbn;
	}
	public void setDraft_demnd_yn(String draft_demnd_yn) {
		this.draft_demnd_yn = draft_demnd_yn;
	}
	public void setPlndbn_req_yn(String plndbn_req_yn) {
		this.plndbn_req_yn = plndbn_req_yn;
	}
	public void setLast_cnsd_yn(String last_cnsd_yn) {
		this.last_cnsd_yn = last_cnsd_yn;
	}
	public void setPrev_cnsdreq_id(String prev_cnsdreq_id) {
		this.prev_cnsdreq_id = prev_cnsdreq_id;
	}
	public void setReq_title(String req_title) {
		this.req_title = req_title;
	}
	public void setReqman_id(String reqman_id) {
		this.reqman_id = reqman_id;
	}
	public void setReqman_nm(String reqman_nm) {
		this.reqman_nm = reqman_nm;
	}
	public void setReq_dept(String req_dept) {
		this.req_dept = req_dept;
	}
	public void setReq_dept_nm(String req_dept_nm) {
		this.req_dept_nm = req_dept_nm;
	}
	public void setReq_dt(String req_dt) {
		this.req_dt = req_dt;
	}
	public void setRe_demndday(String re_demndday) {
		this.re_demndday = re_demndday;
	}
	public void setLastbn_chge_yn(String lastbn_chge_yn) {
		this.lastbn_chge_yn = lastbn_chge_yn;
	}
	public void setCnsd_demnd_cont(String cnsd_demnd_cont) {
		this.cnsd_demnd_cont = cnsd_demnd_cont;
	}
	public void setPrgrs_status(String prgrs_status) {
		this.prgrs_status = prgrs_status;
	}
	public void setRebfr_apprvl_yn(String rebfr_apprvl_yn) {
		this.rebfr_apprvl_yn = rebfr_apprvl_yn;
	}
	public void setRe_dt(String re_dt) {
		this.re_dt = re_dt;
	}
	public void setPub_yn(String pub_yn) {
		this.pub_yn = pub_yn;
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
	public void setStart_index(String start_index) {
		this.start_index = start_index;
	}
	public void setEnd_index(String end_index) {
		this.end_index = end_index;
	}
	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}
	public void setStatus(String status){
		this.status = status;
	}
	
	public void setBody_mime(String body_mime){
		this.body_mime = body_mime;
	}
	
	//GET
	public String getCnsdreq_id() {
		return this.cnsdreq_id;
	}
	
	public String getMod_cntrt_id() {
		return this.mod_cntrt_id;
	}
	public String getMn_cnsd_dept() {
		return this.mn_cnsd_dept;
	}
	public String getMn_respman_apnt_yn() {
		return this.mn_respman_apnt_yn;
	}
	public String getVc_cnsd_dept() {
		return this.vc_cnsd_dept;
	}
	public String getVc_cnsd_demnd_dt() {
		return this.vc_cnsd_demnd_dt;
	}
	public String getVc_cnsd_demnd_cont() {
		return this.vc_cnsd_demnd_cont;
	}
	public String getVc_respman_apnt_yn() {
		return this.vc_respman_apnt_yn;
	}
	public String getDmstfrgn_gbn() {
		return this.dmstfrgn_gbn;
	}
	public String getDraft_demnd_yn() {
		return this.draft_demnd_yn;
	}
	public String getPlndbn_req_yn() {
		return this.plndbn_req_yn;
	}
	public String getLast_cnsd_yn() {
		return this.last_cnsd_yn;
	}
	public String getPrev_cnsdreq_id() {
		return this.prev_cnsdreq_id;
	}
	public String getReq_title() {
		return this.req_title;
	}
	public String getReqman_id() {
		return this.reqman_id;
	}
	public String getReqman_nm() {
		return this.reqman_nm;
	}
	public String getReq_dept() {
		return this.req_dept;
	}
	public String getReq_dept_nm() {
		return this.req_dept_nm;
	}
	public String getReq_dt() {
		return this.req_dt;
	}
	public String getRe_demndday() {
		return this.re_demndday;
	}
	public String getLastbn_chge_yn() {
		return this.lastbn_chge_yn;
	}
	public String getCnsd_demnd_cont() {
		return this.cnsd_demnd_cont;
	}
	public String getPrgrs_status() {
		return this.prgrs_status;
	}
	public String getRebfr_apprvl_yn() {
		return this.rebfr_apprvl_yn;
	}
	public String getRe_dt() {
		return this.re_dt;
	}
	public String getPub_yn() {
		return this.pub_yn;
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
	public String getStart_index() {
		return this.start_index;
	}
	public String getEnd_index() {
		return this.end_index;
	}
	public String getCurPage() {
		return this.curPage;
	}
	public String getStatus(){
		return this.status;
	}
	
	public String getBody_mime(){   
		return this.body_mime;
	}
	public String getSearch_start_reqday() {
		return search_start_reqday;
	}
	public void setSearch_start_reqday(String search_start_reqday) {
		this.search_start_reqday = search_start_reqday;
	}
	public String getSearch_end_reqday() {
		return search_end_reqday;
	}
	public void setSearch_end_reqday(String search_end_reqday) {
		this.search_end_reqday = search_end_reqday;
	}
	public String getSearch_reqman_id() {
		return search_reqman_id;
	}
	public void setSearch_reqman_id(String search_reqman_id) {
		this.search_reqman_id = search_reqman_id;
	}	

	public String getSearch_biz_clsfcn() {
		return search_biz_clsfcn;
	}
	public void setSearch_biz_clsfcn(String search_biz_clsfcn) {
		this.search_biz_clsfcn = search_biz_clsfcn;
	}
	public String getSearch_depth_clsfcn() {
		return search_depth_clsfcn;
	}
	public void setSearch_depth_clsfcn(String search_depth_clsfcn) {
		this.search_depth_clsfcn = search_depth_clsfcn;
	}
	public String getSearch_cnclsnpurps_bigclsfcn() {
		return search_cnclsnpurps_bigclsfcn;
	}
	public void setSearch_cnclsnpurps_bigclsfcn(String search_cnclsnpurps_bigclsfcn) {
		this.search_cnclsnpurps_bigclsfcn = search_cnclsnpurps_bigclsfcn;
	}
	public String getSearch_cnclsnpurps_midclsfcn() {
		return search_cnclsnpurps_midclsfcn;
	}
	public void setSearch_cnclsnpurps_midclsfcn(String search_cnclsnpurps_midclsfcn) {
		this.search_cnclsnpurps_midclsfcn = search_cnclsnpurps_midclsfcn;
	}
		
	public String getSearch_contract_type_nm() {
		return search_contract_type_nm;
	}
	public void setSearch_contract_type_nm(String search_contract_type_nm) {
		this.search_contract_type_nm = search_contract_type_nm;
	}
	public String getSearch_reqman_nm() {
		return search_reqman_nm;
	}
	public void setSearch_reqman_nm(String search_reqman_nm) {
		this.search_reqman_nm = search_reqman_nm;
	}
	
	public String getSearch_contract_taget() {
		return search_contract_taget;
	}
	public void setSearch_contract_taget(String search_contract_taget) {
		this.search_contract_taget = search_contract_taget;
	}
	public String getSearch_step() {
		return search_step;
	}
	public void setSearch_step(String search_step) {
		this.search_step = search_step;
	}
	public String getSearch_state() {
		return search_state;
	}
	public void setSearch_state(String search_state) {
		this.search_state = search_state;
	}
	public String getSearch_review_title() {
		return search_review_title;
	}
	public void setSearch_review_title(String search_review_title) {
		this.search_review_title = search_review_title;
	}
	public String getSrch_tnc_no() {
		return srch_tnc_no;
	}
	public void setSrch_tnc_no(String srch_tnc_no) {
		this.srch_tnc_no = srch_tnc_no;
	}	
}