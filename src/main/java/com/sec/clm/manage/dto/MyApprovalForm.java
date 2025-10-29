package com.sec.clm.manage.dto;

import com.sec.clm.manage.dto.ConsultationForm;
/**
 * 계약관리_계약_마스터
 * @author chahyunjin
 */
public class MyApprovalForm extends ConsultationForm {
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
	 
	 private String page_gbn;

	 public String getPage_gbn() {
		return page_gbn;
	 }
	 public void setPage_gbn(String page_gbn) {
		this.page_gbn = page_gbn;
	 }
	 
	 private String user_role;

	public String getUser_role() {
		return user_role;
	}
	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}
	
	/**
	 * 공통 리스트 검색 조건 - 차현진 10.25  심주완 11.24 추가 및 화면순서대로 조정 
	 */
	private String list_mode;					// 검색구분(의뢰별/계약별)
	private String srch_resp_dept;				// 담당부서코드
	private String srch_oppnt_cd;   			// 상대방코드
	private String srch_review_title;			// 의뢰명
	private String srch_reqman_nm;				// 의뢰자
	private String srch_start_reqday;			// 의뢰일시작
	private String srch_end_reqday;				// 의뢰일끝
	private String srch_start_cnlsnday;			// 계약 시작일
	private String srch_end_cnlsnday;			// 계약 종료일
	private String srch_resp_dept_nm;			// 담당부서명	
	private String srch_respman_nm;				// 담당자명
	private String srch_oppnt_nm;				// 계약상대방명
	private String srch_cnsdman_nm;				// 검토자
	private String srch_state;					// 상태
	private String srch_biz_clsfcn;				// 계약단계
	private String srch_cnclsnpurps_bigclsfcn; 	// 체결목적
	private String srch_step; 
	
	private String srch_cntrt_nm; 				// Contract Title by hong
	private String srch_cntrt_no;				// Contract ID by hong
	private String srch_str_org_acptday;		// Original Copy by hong
	private String srch_end_org_acptday;		// Original Copy by hong
	
	
	
	public String getSrch_step() {
		return srch_step;
	}
	public void setSrch_step(String srch_step) {
		this.srch_step = srch_step;
	}
	public String getList_mode() {
		return list_mode;
	}
	public void setList_mode(String list_mode) {
		this.list_mode = list_mode;
	}
	public String getSrch_resp_dept() {
		return srch_resp_dept;
	}
	public void setSrch_resp_dept(String srch_resp_dept) {
		this.srch_resp_dept = srch_resp_dept;
	}
	public String getSrch_oppnt_cd() {
		return srch_oppnt_cd;
	}
	public void setSrch_oppnt_cd(String srch_oppnt_cd) {
		this.srch_oppnt_cd = srch_oppnt_cd;
	}
	public String getSrch_review_title() {
		return srch_review_title;
	}
	public void setSrch_review_title(String srch_review_title) {
		this.srch_review_title = srch_review_title;
	}
	public String getSrch_reqman_nm() {
		return srch_reqman_nm;
	}
	public void setSrch_reqman_nm(String srch_reqman_nm) {
		this.srch_reqman_nm = srch_reqman_nm;
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
	public String getSrch_state() {
		return srch_state;
	}
	public void setSrch_state(String srch_state) {
		this.srch_state = srch_state;
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

	//원본관리와 MyApproval 구분을 위한 구분자 추가. 2012-07-12. -김현구-
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
	
	/** Sungwoo added G-Erp Search option 2014-06-10 **/
	// srch_customer : gerp name 조회
	private String srch_division;

	// srch_vendor_type : vendor type 조회
	private String srch_vendor_type;

	// srch_vendor_type_detail : vendor type code 조회
	private String srch_vendor_type_detail;

	public String getSrch_division() {
		return srch_division;
	}
	public void setSrch_division(String srch_division) {
		this.srch_division = srch_division;
	}
	public String getSrch_vendor_type() {
		return srch_vendor_type;
	}
	public void setSrch_vendor_type(String srch_vendor_type) {
		this.srch_vendor_type = srch_vendor_type;
	}
	public String getSrch_vendor_type_detail() {
		return srch_vendor_type_detail;
	}
	public void setSrch_vendor_type_detail(String srch_vendor_type_detail) {
		this.srch_vendor_type_detail = srch_vendor_type_detail;
	}
	
}