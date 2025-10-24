package com.sec.clm.review.dto;


/**
 * Project Name : 법무시스템
 * File Name : ConsiderationHQVO.java
 * Description : 본사 검토용 모델객체
 * Author : 박병주
 * Date : 2014.05
 * Copyright : 2014 by SAMSUNG. All rights reserved.
 */
public class ConsiderationHQVO extends ConsiderationVO {
	// 빈 객체가 아님 -  ConsiderationVO 를 상속 받고 있다.
	
	private String srch_hq_reqman_nm ="";
	private String srch_hq_respman_id = "";
	private String srch_prgrs_status_hq = "";		

	private String hq_cnsdreq_id   ="";
	private String cnsdreq_id  ="";
	private String cntrt_id  ="";
	private String cnsd_level ="";
	private String comp_cd  ="";
	private String comp_nm ="";  
	private String prev_hq_cnsdreq_id ="";  
	private String hq_req_title =""; 
	private String hq_cnsd_demnd_cont =""; 
	private String reqman_id  ="";
	private String reqman_nm ="";  
	private String req_dept ="";  
	private String req_dept_nm =""; 
	private String req_dt =""; 
	private String rebfr_apprvl_yn =""; 
	private String re_dt =""; 
	private String product_gbn ="";  
	private String hq_cnsdman_id ="";
	private String hq_cnsdman_nm =""; 
	private String hq_cnsd_dt =""; 
	private String hq_cnsd_status ="";
	private String hq_cnsd_opnn ="";
	private String hq_ce_cnsdman_id =""; 
	private String hq_ce_cnsdman_nm =""; 
	private String hq_ce_cnsd_dt ="";
	private String hq_ce_cnsd_opnn =""; 
	private String hq_im_cnsdman_id =""; 
	private String hq_im_cnsdman_nm =""; 
	private String hq_im_cnsd_dt ="";
	private String hq_im_cnsd_opnn =""; 
	private String hq_not_cnsdman_id =""; 
	private String hq_not_cnsdman_nm ="";  
	private String hq_not_cnsd_dt ="";
	private String hq_not_cnsd_opnn ="";  
	private String hq_apbtman_id ="";  
	private String hq_apbtman_nm =""; 
	private String hq_apbt_dt ="";
	private String hq_apbt_opnn ="";  
	private String sys_last_gbn =""; 
	
	// 배정 데이타
	/** 검토의뢰_담당자_담당자_ID */
	private String list_respmanHQ_id;
	/** 검토의뢰_담당자_담당자_ID */
	private String list_respmanHQ_ids[];
	
	private String mod_id = "";
	private String mod_nm = "";
	private String apbt_memo ="";	
	private String main_man_id = "";
	
	// admin reply
	private String rtnMode = "";
	private String rtn_cont = "";	
	
	private String hq_rel_pro ="";
	
	private String fileInfosCE;
	private String fileInfosIM;
	private String fileInfosNOT;
	
	private String ce_resp_yn;
	private String im_resp_yn;
	private String hq_apbt_memo;
	
	
					
	public String getHq_apbt_memo() {
		return hq_apbt_memo;
	}

	public void setHq_apbt_memo(String hq_apbt_memo) {
		this.hq_apbt_memo = hq_apbt_memo;
	}

	public String getCe_resp_yn() {
		return ce_resp_yn;
	}

	public void setCe_resp_yn(String ce_resp_yn) {
		this.ce_resp_yn = ce_resp_yn;
	}

	public String getIm_resp_yn() {
		return im_resp_yn;
	}

	public void setIm_resp_yn(String im_resp_yn) {
		this.im_resp_yn = im_resp_yn;
	}
				
	public String getSrch_hq_respman_id() {
		return srch_hq_respman_id;
	}

	public void setSrch_hq_respman_id(String srch_hq_respman_id) {
		this.srch_hq_respman_id = srch_hq_respman_id;
	}

	public String getFileInfosCE() {
		return fileInfosCE;
	}

	public void setFileInfosCE(String fileInfosCE) {
		this.fileInfosCE = fileInfosCE;
	}

	public String getFileInfosIM() {
		return fileInfosIM;
	}

	public void setFileInfosIM(String fileInfosIM) {
		this.fileInfosIM = fileInfosIM;
	}

	public String getFileInfosNOT() {
		return fileInfosNOT;
	}

	public void setFileInfosNOT(String fileInfosNOT) {
		this.fileInfosNOT = fileInfosNOT;
	}

	public String getHq_rel_pro() {
		return hq_rel_pro;
	}

	public void setHq_rel_pro(String hq_rel_pro) {
		this.hq_rel_pro = hq_rel_pro;
	}

	public String getRtnMode() {
		return rtnMode;
	}

	public void setRtnMode(String rtnMode) {
		this.rtnMode = rtnMode;
	}

	public String getRtn_cont() {
		return rtn_cont;
	}

	public void setRtn_cont(String rtn_cont) {
		this.rtn_cont = rtn_cont;
	}

	public String getMain_man_id() {
		return main_man_id;
	}

	public void setMain_man_id(String main_man_id) {
		this.main_man_id = main_man_id;
	}

	public String getList_respmanHQ_id() {
		return list_respmanHQ_id;
	}

	public void setList_respmanHQ_id(String list_respmanHQ_id) {
		this.list_respmanHQ_id = list_respmanHQ_id;
	}

	public String[] getList_respmanHQ_ids() {
		return list_respmanHQ_ids;
	}

	public void setList_respmanHQ_ids(String[] list_respmanHQ_ids) {
		this.list_respmanHQ_ids = list_respmanHQ_ids;
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

	public String getApbt_memo() {
		return apbt_memo;
	}

	public void setApbt_memo(String apbt_memo) {
		this.apbt_memo = apbt_memo;
	}

	public String getSrch_prgrs_status_hq() {
		return srch_prgrs_status_hq;
	}

	public void setSrch_prgrs_status_hq(String srch_prgrs_status_hq) {
		this.srch_prgrs_status_hq = srch_prgrs_status_hq;
	}

	public String getSrch_hq_reqman_nm() {
		return srch_hq_reqman_nm;
	}

	public void setSrch_hq_reqman_nm(String srch_hq_reqman_nm) {
		this.srch_hq_reqman_nm = srch_hq_reqman_nm;
	}

	public String getHq_cnsdreq_id() {
		return hq_cnsdreq_id;
	}

	public void setHq_cnsdreq_id(String hq_cnsdreq_id) {
		this.hq_cnsdreq_id = hq_cnsdreq_id;
	}

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

	public String getCnsd_level() {
		return cnsd_level;
	}

	public void setCnsd_level(String cnsd_level) {
		this.cnsd_level = cnsd_level;
	}

	public String getComp_cd() {
		return comp_cd;
	}

	public void setComp_cd(String comp_cd) {
		this.comp_cd = comp_cd;
	}

	public String getComp_nm() {
		return comp_nm;
	}

	public void setComp_nm(String comp_nm) {
		this.comp_nm = comp_nm;
	}

	public String getPrev_hq_cnsdreq_id() {
		return prev_hq_cnsdreq_id;
	}

	public void setPrev_hq_cnsdreq_id(String prev_hq_cnsdreq_id) {
		this.prev_hq_cnsdreq_id = prev_hq_cnsdreq_id;
	}

	public String getHq_req_title() {
		return hq_req_title;
	}

	public void setHq_req_title(String hq_req_title) {
		this.hq_req_title = hq_req_title;
	}

	public String getHq_cnsd_demnd_cont() {
		return hq_cnsd_demnd_cont;
	}

	public void setHq_cnsd_demnd_cont(String hq_cnsd_demnd_cont) {
		this.hq_cnsd_demnd_cont = hq_cnsd_demnd_cont;
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

	public String getReq_dept() {
		return req_dept;
	}

	public void setReq_dept(String req_dept) {
		this.req_dept = req_dept;
	}

	public String getReq_dept_nm() {
		return req_dept_nm;
	}

	public void setReq_dept_nm(String req_dept_nm) {
		this.req_dept_nm = req_dept_nm;
	}

	public String getReq_dt() {
		return req_dt;
	}

	public void setReq_dt(String req_dt) {
		this.req_dt = req_dt;
	}

	public String getRebfr_apprvl_yn() {
		return rebfr_apprvl_yn;
	}

	public void setRebfr_apprvl_yn(String rebfr_apprvl_yn) {
		this.rebfr_apprvl_yn = rebfr_apprvl_yn;
	}

	public String getRe_dt() {
		return re_dt;
	}

	public void setRe_dt(String re_dt) {
		this.re_dt = re_dt;
	}

	public String getProduct_gbn() {
		return product_gbn;
	}

	public void setProduct_gbn(String product_gbn) {
		this.product_gbn = product_gbn;
	}

	public String getHq_cnsdman_id() {
		return hq_cnsdman_id;
	}

	public void setHq_cnsdman_id(String hq_cnsdman_id) {
		this.hq_cnsdman_id = hq_cnsdman_id;
	}

	public String getHq_cnsdman_nm() {
		return hq_cnsdman_nm;
	}

	public void setHq_cnsdman_nm(String hq_cnsdman_nm) {
		this.hq_cnsdman_nm = hq_cnsdman_nm;
	}

	public String getHq_cnsd_dt() {
		return hq_cnsd_dt;
	}

	public void setHq_cnsd_dt(String hq_cnsd_dt) {
		this.hq_cnsd_dt = hq_cnsd_dt;
	}

	public String getHq_cnsd_status() {
		return hq_cnsd_status;
	}

	public void setHq_cnsd_status(String hq_cnsd_status) {
		this.hq_cnsd_status = hq_cnsd_status;
	}

	public String getHq_cnsd_opnn() {
		return hq_cnsd_opnn;
	}

	public void setHq_cnsd_opnn(String hq_cnsd_opnn) {
		this.hq_cnsd_opnn = hq_cnsd_opnn;
	}

	public String getHq_ce_cnsdman_id() {
		return hq_ce_cnsdman_id;
	}

	public void setHq_ce_cnsdman_id(String hq_ce_cnsdman_id) {
		this.hq_ce_cnsdman_id = hq_ce_cnsdman_id;
	}

	public String getHq_ce_cnsdman_nm() {
		return hq_ce_cnsdman_nm;
	}

	public void setHq_ce_cnsdman_nm(String hq_ce_cnsdman_nm) {
		this.hq_ce_cnsdman_nm = hq_ce_cnsdman_nm;
	}

	public String getHq_ce_cnsd_dt() {
		return hq_ce_cnsd_dt;
	}

	public void setHq_ce_cnsd_dt(String hq_ce_cnsd_dt) {
		this.hq_ce_cnsd_dt = hq_ce_cnsd_dt;
	}

	public String getHq_ce_cnsd_opnn() {
		return hq_ce_cnsd_opnn;
	}

	public void setHq_ce_cnsd_opnn(String hq_ce_cnsd_opnn) {
		this.hq_ce_cnsd_opnn = hq_ce_cnsd_opnn;
	}

	public String getHq_im_cnsdman_id() {
		return hq_im_cnsdman_id;
	}

	public void setHq_im_cnsdman_id(String hq_im_cnsdman_id) {
		this.hq_im_cnsdman_id = hq_im_cnsdman_id;
	}

	public String getHq_im_cnsdman_nm() {
		return hq_im_cnsdman_nm;
	}

	public void setHq_im_cnsdman_nm(String hq_im_cnsdman_nm) {
		this.hq_im_cnsdman_nm = hq_im_cnsdman_nm;
	}

	public String getHq_im_cnsd_dt() {
		return hq_im_cnsd_dt;
	}

	public void setHq_im_cnsd_dt(String hq_im_cnsd_dt) {
		this.hq_im_cnsd_dt = hq_im_cnsd_dt;
	}

	public String getHq_im_cnsd_opnn() {
		return hq_im_cnsd_opnn;
	}

	public void setHq_im_cnsd_opnn(String hq_im_cnsd_opnn) {
		this.hq_im_cnsd_opnn = hq_im_cnsd_opnn;
	}

	public String getHq_not_cnsdman_id() {
		return hq_not_cnsdman_id;
	}

	public void setHq_not_cnsdman_id(String hq_not_cnsdman_id) {
		this.hq_not_cnsdman_id = hq_not_cnsdman_id;
	}

	public String getHq_not_cnsdman_nm() {
		return hq_not_cnsdman_nm;
	}

	public void setHq_not_cnsdman_nm(String hq_not_cnsdman_nm) {
		this.hq_not_cnsdman_nm = hq_not_cnsdman_nm;
	}

	public String getHq_not_cnsd_dt() {
		return hq_not_cnsd_dt;
	}

	public void setHq_not_cnsd_dt(String hq_not_cnsd_dt) {
		this.hq_not_cnsd_dt = hq_not_cnsd_dt;
	}

	public String getHq_not_cnsd_opnn() {
		return hq_not_cnsd_opnn;
	}

	public void setHq_not_cnsd_opnn(String hq_not_cnsd_opnn) {
		this.hq_not_cnsd_opnn = hq_not_cnsd_opnn;
	}

	public String getHq_apbtman_id() {
		return hq_apbtman_id;
	}

	public void setHq_apbtman_id(String hq_apbtman_id) {
		this.hq_apbtman_id = hq_apbtman_id;
	}

	public String getHq_apbtman_nm() {
		return hq_apbtman_nm;
	}

	public void setHq_apbtman_nm(String hq_apbtman_nm) {
		this.hq_apbtman_nm = hq_apbtman_nm;
	}

	public String getHq_apbt_dt() {
		return hq_apbt_dt;
	}

	public void setHq_apbt_dt(String hq_apbt_dt) {
		this.hq_apbt_dt = hq_apbt_dt;
	}

	public String getHq_apbt_opnn() {
		return hq_apbt_opnn;
	}

	public void setHq_apbt_opnn(String hq_apbt_opnn) {
		this.hq_apbt_opnn = hq_apbt_opnn;
	}

	public String getSys_last_gbn() {
		return sys_last_gbn;
	}

	public void setSys_last_gbn(String sys_last_gbn) {
		this.sys_last_gbn = sys_last_gbn;
	}	

}