package com.sec.clm.manage.dto;

import com.sec.clm.manage.dto.ConsultationVO;
/**
 * 계약관리_계약_마스터
 * @author chahyunjin
 */
public class MyApprovalVO extends ConsultationVO {
	private int agree_seqno;
	private String agreeman_id;
	private String agreeman_nm;
	private String agreeman_jikgup_nm;
	private String agreeman_dept_nm;
	private String agree_yn;
	private String agreeday;
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
	public String getAgree_yn() {
		return agree_yn;
	}
	public void setAgree_yn(String agree_yn) {
		this.agree_yn = agree_yn;
	}
	public String getAgreeday() {
		return agreeday;
	}
	public void setAgreeday(String agreeday) {
		this.agreeday = agreeday;
	}
	public String getAgree_cause() {
		return agree_cause;
	}
	public void setAgree_cause(String agree_cause) {
		this.agree_cause = agree_cause;
	}
	
	//결재정보
	 private String module_id;
	 private String mis_id;
	 private String activity;
	 private String user_id;
	 private String user_nm;
	 private String create_date;
	 private String title;
	 private String apprvl_clsfcn;
	 private String ref_key;
	 
	 public String getModule_id() {
		return module_id;
	 }
	 public void setModule_id(String module_id) {
		this.module_id = module_id;
	 }
	 public String getMis_id() {
		return mis_id;
	 }
	 public void setMis_id(String mis_id) {
		this.mis_id = mis_id;
	 }
	 public String getActivity() {
		return activity;
	 }
	 public void setActivity(String activity) {
		this.activity = activity;
	 }
	 public String getUser_id() {
		return user_id;
	 }
	 public void setUser_id(String user_id) {
		this.user_id = user_id;
	 }
	 public String getUser_nm() {
		return user_nm;
	 }
	 public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	 }
	 public String getCreate_date() {
		return create_date;
	 }
	 public void setCreate_date(String create_date) {
		this.create_date = create_date;
	 }
	 public String getTitle() {
		return title;
	 }
	 public void setTitle(String title) {
		this.title = title;
	 }
	 public String getApprvl_clsfcn() {
		return apprvl_clsfcn;
	 }
	 public void setApprvl_clsfcn(String apprvl_clsfcn) {
		this.apprvl_clsfcn = apprvl_clsfcn;
	 }
	 public String getRef_key() {
		return ref_key;
	 }
	 public void setRef_key(String ref_key) {
		this.ref_key = ref_key;
	 }
	 
	// 원본관리와 MyApproval 구분을 위한 구분자 추가. 2012-07-12. -김현구-
	public String isOrgMgr;

	public String getIsOrgMgr() {
		return isOrgMgr;
	}

	public void setIsOrgMgr(String isOrgMgr) {
		this.isOrgMgr = isOrgMgr;
	}
	
	/*원본관리*/
	int seqno;
	String mod_dt;
	String mod_id;
	String mod_nm;
	String mod_dept_nm;
	String mod_jikgup_nm;
	String mod_cause;
	String org_mng_dt;
	String org_mng_type;
	String org_mng_reqman_id;
	String org_mng_reqman_nm;
	String org_mng_req_dept_nm;
	String org_mng_req_jikgup_nm;

	public int getSeqno() {
		return seqno;
	}
	public void setSeqno(int seqno) {
		this.seqno = seqno;
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
	public String getMod_dept_nm() {
		return mod_dept_nm;
	}
	public void setMod_dept_nm(String mod_dept_nm) {
		this.mod_dept_nm = mod_dept_nm;
	}
	public String getMod_jikgup_nm() {
		return mod_jikgup_nm;
	}
	public void setMod_jikgup_nm(String mod_jikgup_nm) {
		this.mod_jikgup_nm = mod_jikgup_nm;
	}
	public String getMod_cause() {
		return mod_cause;
	}
	public void setMod_cause(String mod_cause) {
		this.mod_cause = mod_cause;
	}
	public String getOrg_mng_dt() {
		return org_mng_dt;
	}
	public void setOrg_mng_dt(String org_mng_dt) {
		this.org_mng_dt = org_mng_dt;
	}
	public String getOrg_mng_type() {
		return org_mng_type;
	}
	public void setOrg_mng_type(String org_mng_type) {
		this.org_mng_type = org_mng_type;
	}
	public String getOrg_mng_reqman_id() {
		return org_mng_reqman_id;
	}
	public void setOrg_mng_reqman_id(String org_mng_reqman_id) {
		this.org_mng_reqman_id = org_mng_reqman_id;
	}
	public String getOrg_mng_reqman_nm() {
		return org_mng_reqman_nm;
	}
	public void setOrg_mng_reqman_nm(String org_mng_reqman_nm) {
		this.org_mng_reqman_nm = org_mng_reqman_nm;
	}
	public String getOrg_mng_req_dept_nm() {
		return org_mng_req_dept_nm;
	}
	public void setOrg_mng_req_dept_nm(String org_mng_req_dept_nm) {
		this.org_mng_req_dept_nm = org_mng_req_dept_nm;
	}
	public String getOrg_mng_req_jikgup_nm() {
		return org_mng_req_jikgup_nm;
	}
	public void setOrg_mng_req_jikgup_nm(String org_mng_req_jikgup_nm) {
		this.org_mng_req_jikgup_nm = org_mng_req_jikgup_nm;
	}
	
	// 2014-05-20 Kevin added. [SP_14MAY01]
	private String prcs_depth = "";
	private String depth_status = "";
	public void setPrcs_depth(String prcs_depth){
		this.prcs_depth = prcs_depth;
	}
	public String getPrcs_depth(){
		return this.prcs_depth;
	}
	public void setDepth_status(String depth_status){
		this.depth_status = depth_status;
	}
	public String getDepth_status(){
		return this.depth_status;
	}
}