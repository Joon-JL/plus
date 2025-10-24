/**
 * Project Name : 법무시스템 이식
 * File name	: SignManageVO.java
 * Description	: 날인 Data VO(Model)
 * Author		: 박병주
 * Date			: 2013. 05
 * Copyright : 2013 by SAMSUNG. All rights reserved.
 */
package com.sec.clm.sign.dto;

import com.sds.secframework.common.dto.CommonVO;

public class SignManageVO extends CommonVO {
	
	//권한 관리용
	private String auth_str_id;
	
	// 날인신청 검색용 필드	
	// 신청년
	private String srch_apl_y;
	// 신청년
	private String srch_apl_m;
	// 이전 30일
	private String srch_apl_prev30d;	
	// 결재상태(날인신청 상태)
	private String srch_seal_rqst_status;
	// 날인 신청(유형)
	private String srch_seal_knd_cd;
	// 날인 처리 상태 (후처리에서 상신의 결재 상태  를 참조하여 날인처리상태 코드 취득)
	private String srch_seal_ffmt_status;
	// 건명
	private String srch_title;
	// 신청유형 (금융거래용,계약용,,,)
	private String srch_apl_cls;
	// 증빙서류
	private String srch_doc_no;
	// 증빙서류처리상태
	private String srch_doc_issue_status;
	// 제출처 (계약상대방)
	private String srch_sbm;
	// 신청자
	private String srch_seal_rqstman_nm;	
	// 신청자 ID
	private String srch_seal_rqstman_id;	
	// 처리자 (날인자)
	private String srch_seal_ffmtman_nm;
	// 처리일자 시작
	private String srch_seal_ffmtday_start;
	// 처리일자 끝
	private String srch_seal_ffmtday_end;
	// 신청인장번호
	private String srch_apl_seal_no;
	// 처리인장번호
	private String srch_prc_seal_no;
	// 날인처리 담당자
	private String srch_seal_ffmtman_id;
	// 증명서류 발급처리 담당자
	private String srch_doc_issuer_id;	
	// 인장반납여부
	private String srch_rtn_yn;	
	// 인장 반출일
	private String srch_prc_ymd_start;
	private String srch_prc_ymd_end;
	// 인장 반납일
	private String srch_rtn_ymd_start;
	private String srch_rtn_ymd_end;

	// 인장반출신청여부
	private String srch_apl_out_yn;
	
	private String reg_dt;
	private String reg_nm;
	
	private String comp_cd;
	private String comp_nm;
	private String apl_ym;
	private String apl_sqn;
	private String apl_cls;
	private String title;
	private String sbm;
	private String use_summ;
	private String txt;
	private String seal_knd_cd;
	private String seal_yn;
	private String apl_out_yn;
	private String apl_seal_no;
	private String prc_seal_no;
	private String apl_ymd_from;
	private String apl_ymd_to;
	private String prc_ymd_from;
	private String prc_ymd_to;
	private String rtn_yn;
	private String rtn_ymd;
	private String doc_yn;
	private String doc1;
	private String doc2;
	private String doc3;
	private String doc4;
	private String doc5;
	private String doc6;
	private String doc7;
	private String doc8;
	private String doc9;
	private String doc10;
	private String seal_rqstman_id;
	private String seal_rqstman_nm;
	private String seal_rqstman_jikgup_nm;
	private String seal_rqst_dept_nm;
	private String seal_rqst_status;
	private String seal_rqstday;
	private String seal_ffmtman_id;
	private String seal_ffmtman_nm;
	private String seal_ffmtman_jikgup_nm;
	private String seal_ffmt_dept_nm;
	private String seal_ffmt_status;
	private String seal_ffmtday;
	private String doc_issuer_id;
	private String doc_issuer_nm;
	private String doc_issuer_jikgup_nm;
	private String doc_issuer_dept_nm;
	private String doc_issue_status;
	private String doc_issueday;
	private String ref_key;
	private String cnsdreq_id;
	private String cntrt_id;
	
	private String select_auth_apnt_yn;
	private String gubn_cd;
	private String ara_nm;
	private String comments;
	
	private String forwardFrom;
	private String goExcel;
	private String excel_method;
	
	private String seal_rtnman_id;
	private String seal_rtnman_nm;
	private String seal_rtnman_dept_nm;
	private String seal_rtnman_jikgup_nm;	
	//  반출인장번호
	private String rtn_seal_no;
	private String doc_scrtxt;
	
	// 전자 임원 판단 여부
	private String sRb;
		
	public String getDoc_scrtxt() {
		return doc_scrtxt;
	}
	public String getsRb() {
		return sRb;
	}
	public void setsRb(String sRb) {
		this.sRb = sRb;
	}
	public void setDoc_scrtxt(String doc_scrtxt) {
		this.doc_scrtxt = doc_scrtxt;
	}
	public String getRtn_seal_no() {
		return rtn_seal_no;
	}
	public void setRtn_seal_no(String rtn_seal_no) {
		this.rtn_seal_no = rtn_seal_no;
	}
	public String getSrch_seal_rqstman_id() {
		return srch_seal_rqstman_id;
	}
	public void setSrch_seal_rqstman_id(String srch_seal_rqstman_id) {
		this.srch_seal_rqstman_id = srch_seal_rqstman_id;
	}
	public String getExcel_method() {
		return excel_method;
	}
	public void setExcel_method(String excel_method) {
		this.excel_method = excel_method;
	}
	public String getGoExcel() {
		return goExcel;
	}
	public void setGoExcel(String goExcel) {
		this.goExcel = goExcel;
	}
	public String getSeal_rtnman_id() {
		return seal_rtnman_id;
	}
	public void setSeal_rtnman_id(String seal_rtnman_id) {
		this.seal_rtnman_id = seal_rtnman_id;
	}
	public String getSeal_rtnman_nm() {
		return seal_rtnman_nm;
	}
	public void setSeal_rtnman_nm(String seal_rtnman_nm) {
		this.seal_rtnman_nm = seal_rtnman_nm;
	}
	public String getSeal_rtnman_dept_nm() {
		return seal_rtnman_dept_nm;
	}
	public void setSeal_rtnman_dept_nm(String seal_rtnman_dept_nm) {
		this.seal_rtnman_dept_nm = seal_rtnman_dept_nm;
	}
	public String getSeal_rtnman_jikgup_nm() {
		return seal_rtnman_jikgup_nm;
	}
	public void setSeal_rtnman_jikgup_nm(String seal_rtnman_jikgup_nm) {
		this.seal_rtnman_jikgup_nm = seal_rtnman_jikgup_nm;
	}
	public String getSrch_apl_out_yn() {
		return srch_apl_out_yn;
	}
	public void setSrch_apl_out_yn(String srch_apl_out_yn) {
		this.srch_apl_out_yn = srch_apl_out_yn;
	}
	public String getSrch_prc_seal_no() {
		return srch_prc_seal_no;
	}
	public void setSrch_prc_seal_no(String srch_prc_seal_no) {
		this.srch_prc_seal_no = srch_prc_seal_no;
	}
	public String getSrch_rtn_yn() {
		return srch_rtn_yn;
	}
	public void setSrch_rtn_yn(String srch_rtn_yn) {
		this.srch_rtn_yn = srch_rtn_yn;
	}
	public String getSrch_prc_ymd_start() {
		return srch_prc_ymd_start;
	}
	public void setSrch_prc_ymd_start(String srch_prc_ymd_start) {
		this.srch_prc_ymd_start = srch_prc_ymd_start;
	}
	public String getSrch_prc_ymd_end() {
		return srch_prc_ymd_end;
	}
	public void setSrch_prc_ymd_end(String srch_prc_ymd_end) {
		this.srch_prc_ymd_end = srch_prc_ymd_end;
	}
	public String getSrch_rtn_ymd_start() {
		return srch_rtn_ymd_start;
	}
	public void setSrch_rtn_ymd_start(String srch_rtn_ymd_start) {
		this.srch_rtn_ymd_start = srch_rtn_ymd_start;
	}
	public String getSrch_rtn_ymd_end() {
		return srch_rtn_ymd_end;
	}
	public void setSrch_rtn_ymd_end(String srch_rtn_ymd_end) {
		this.srch_rtn_ymd_end = srch_rtn_ymd_end;
	}
	public String getForwardFrom() {
		return forwardFrom;
	}
	public void setForwardFrom(String forwardFrom) {
		this.forwardFrom = forwardFrom;
	}
	public String getSrch_seal_ffmtman_id() {
		return srch_seal_ffmtman_id;
	}
	public void setSrch_seal_ffmtman_id(String srch_seal_ffmtman_id) {
		this.srch_seal_ffmtman_id = srch_seal_ffmtman_id;
	}
	public String getSrch_doc_issuer_id() {
		return srch_doc_issuer_id;
	}
	public void setSrch_doc_issuer_id(String srch_doc_issuer_id) {
		this.srch_doc_issuer_id = srch_doc_issuer_id;
	}
	public String getAuth_str_id() {
		return auth_str_id;
	}
	public void setAuth_str_id(String auth_str_id) {
		this.auth_str_id = auth_str_id;
	}
	public String getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}
	public String getReg_nm() {
		return reg_nm;
	}
	public void setReg_nm(String reg_nm) {
		this.reg_nm = reg_nm;
	}
	public String getSelect_auth_apnt_yn() {
		return select_auth_apnt_yn;
	}
	public void setSelect_auth_apnt_yn(String select_auth_apnt_yn) {
		this.select_auth_apnt_yn = select_auth_apnt_yn;
	}
	public String getGubn_cd() {
		return gubn_cd;
	}
	public void setGubn_cd(String gubn_cd) {
		this.gubn_cd = gubn_cd;
	}
	public String getAra_nm() {
		return ara_nm;
	}
	public void setAra_nm(String ara_nm) {
		this.ara_nm = ara_nm;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getSrch_apl_cls() {
		return srch_apl_cls;
	}
	public void setSrch_apl_cls(String srch_apl_cls) {
		this.srch_apl_cls = srch_apl_cls;
	}
	public String getSrch_apl_y() {
		return srch_apl_y;
	}
	public void setSrch_apl_y(String srch_apl_y) {
		this.srch_apl_y = srch_apl_y;
	}
	public String getSrch_apl_m() {
		return srch_apl_m;
	}
	public void setSrch_apl_m(String srch_apl_m) {
		this.srch_apl_m = srch_apl_m;
	}
	public String getSrch_apl_prev30d() {
		return srch_apl_prev30d;
	}
	public void setSrch_apl_prev30d(String srch_apl_prev30d) {
		this.srch_apl_prev30d = srch_apl_prev30d;
	}
	public String getSrch_seal_rqst_status() {
		return srch_seal_rqst_status;
	}
	public void setSrch_seal_rqst_status(String srch_seal_rqst_status) {
		this.srch_seal_rqst_status = srch_seal_rqst_status;
	}
	public String getSrch_seal_knd_cd() {
		return srch_seal_knd_cd;
	}
	public void setSrch_seal_knd_cd(String srch_seal_knd_cd) {
		this.srch_seal_knd_cd = srch_seal_knd_cd;
	}
	public String getSrch_seal_ffmt_status() {
		return srch_seal_ffmt_status;
	}
	public void setSrch_seal_ffmt_status(String srch_seal_ffmt_status) {
		this.srch_seal_ffmt_status = srch_seal_ffmt_status;
	}
	public String getSrch_title() {
		return srch_title;
	}
	public void setSrch_title(String srch_title) {
		this.srch_title = srch_title;
	}

	public String getSrch_doc_no() {
		return srch_doc_no;
	}
	public void setSrch_doc_no(String srch_doc_no) {
		this.srch_doc_no = srch_doc_no;
	}
	public String getSrch_doc_issue_status() {
		return srch_doc_issue_status;
	}
	public void setSrch_doc_issue_status(String srch_doc_issue_status) {
		this.srch_doc_issue_status = srch_doc_issue_status;
	}
	public String getSrch_sbm() {
		return srch_sbm;
	}
	public void setSrch_sbm(String srch_sbm) {
		this.srch_sbm = srch_sbm;
	}
	public String getSrch_seal_rqstman_nm() {
		return srch_seal_rqstman_nm;
	}
	public void setSrch_seal_rqstman_nm(String srch_seal_rqstman_nm) {
		this.srch_seal_rqstman_nm = srch_seal_rqstman_nm;
	}
	public String getSrch_seal_ffmtman_nm() {
		return srch_seal_ffmtman_nm;
	}
	public void setSrch_seal_ffmtman_nm(String srch_seal_ffmtman_nm) {
		this.srch_seal_ffmtman_nm = srch_seal_ffmtman_nm;
	}
	public String getSrch_seal_ffmtday_start() {
		return srch_seal_ffmtday_start;
	}
	public void setSrch_seal_ffmtday_start(String srch_seal_ffmtday_start) {
		this.srch_seal_ffmtday_start = srch_seal_ffmtday_start;
	}
	public String getSrch_seal_ffmtday_end() {
		return srch_seal_ffmtday_end;
	}
	public void setSrch_seal_ffmtday_end(String srch_seal_ffmtday_end) {
		this.srch_seal_ffmtday_end = srch_seal_ffmtday_end;
	}
	public String getSrch_apl_seal_no() {
		return srch_apl_seal_no;
	}
	public void setSrch_apl_seal_no(String srch_apl_seal_no) {
		this.srch_apl_seal_no = srch_apl_seal_no;
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
	public String getApl_ym() {
		return apl_ym;
	}
	public void setApl_ym(String apl_ym) {
		this.apl_ym = apl_ym;
	}
	public String getApl_sqn() {
		return apl_sqn;
	}
	public void setApl_sqn(String apl_sqn) {
		this.apl_sqn = apl_sqn;
	}
	public String getApl_cls() {
		return apl_cls;
	}
	public void setApl_cls(String apl_cls) {
		this.apl_cls = apl_cls;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSbm() {
		return sbm;
	}
	public void setSbm(String sbm) {
		this.sbm = sbm;
	}
	public String getUse_summ() {
		return use_summ;
	}
	public void setUse_summ(String use_summ) {
		this.use_summ = use_summ;
	}
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	public String getSeal_knd_cd() {
		return seal_knd_cd;
	}
	public void setSeal_knd_cd(String seal_knd_cd) {
		this.seal_knd_cd = seal_knd_cd;
	}
	public String getSeal_yn() {
		return seal_yn;
	}
	public void setSeal_yn(String seal_yn) {
		this.seal_yn = seal_yn;
	}
	public String getApl_out_yn() {
		return apl_out_yn;
	}
	public void setApl_out_yn(String apl_out_yn) {
		this.apl_out_yn = apl_out_yn;
	}
	public String getApl_seal_no() {
		return apl_seal_no;
	}
	public void setApl_seal_no(String apl_seal_no) {
		this.apl_seal_no = apl_seal_no;
	}
	public String getPrc_seal_no() {
		return prc_seal_no;
	}
	public void setPrc_seal_no(String prc_seal_no) {
		this.prc_seal_no = prc_seal_no;
	}
	public String getApl_ymd_from() {
		return apl_ymd_from;
	}
	public void setApl_ymd_from(String apl_ymd_from) {
		this.apl_ymd_from = apl_ymd_from;
	}
	public String getApl_ymd_to() {
		return apl_ymd_to;
	}
	public void setApl_ymd_to(String apl_ymd_to) {
		this.apl_ymd_to = apl_ymd_to;
	}
	public String getPrc_ymd_from() {
		return prc_ymd_from;
	}
	public void setPrc_ymd_from(String prc_ymd_from) {
		this.prc_ymd_from = prc_ymd_from;
	}
	public String getPrc_ymd_to() {
		return prc_ymd_to;
	}
	public void setPrc_ymd_to(String prc_ymd_to) {
		this.prc_ymd_to = prc_ymd_to;
	}
	public String getRtn_yn() {
		return rtn_yn;
	}
	public void setRtn_yn(String rtn_yn) {
		this.rtn_yn = rtn_yn;
	}
	public String getRtn_ymd() {
		return rtn_ymd;
	}
	public void setRtn_ymd(String rtn_ymd) {
		this.rtn_ymd = rtn_ymd;
	}
	public String getDoc_yn() {
		return doc_yn;
	}
	public void setDoc_yn(String doc_yn) {
		this.doc_yn = doc_yn;
	}
	public String getDoc1() {
		return doc1;
	}
	public void setDoc1(String doc1) {
		this.doc1 = doc1;
	}
	public String getDoc2() {
		return doc2;
	}
	public void setDoc2(String doc2) {
		this.doc2 = doc2;
	}
	public String getDoc3() {
		return doc3;
	}
	public void setDoc3(String doc3) {
		this.doc3 = doc3;
	}
	public String getDoc4() {
		return doc4;
	}
	public void setDoc4(String doc4) {
		this.doc4 = doc4;
	}
	public String getDoc5() {
		return doc5;
	}
	public void setDoc5(String doc5) {
		this.doc5 = doc5;
	}
	public String getDoc6() {
		return doc6;
	}
	public void setDoc6(String doc6) {
		this.doc6 = doc6;
	}
	public String getDoc7() {
		return doc7;
	}
	public void setDoc7(String doc7) {
		this.doc7 = doc7;
	}
	public String getDoc8() {
		return doc8;
	}
	public void setDoc8(String doc8) {
		this.doc8 = doc8;
	}
	public String getDoc9() {
		return doc9;
	}
	public void setDoc9(String doc9) {
		this.doc9 = doc9;
	}
	public String getDoc10() {
		return doc10;
	}
	public void setDoc10(String doc10) {
		this.doc10 = doc10;
	}
	public String getSeal_rqstman_id() {
		return seal_rqstman_id;
	}
	public void setSeal_rqstman_id(String seal_rqstman_id) {
		this.seal_rqstman_id = seal_rqstman_id;
	}
	public String getSeal_rqstman_nm() {
		return seal_rqstman_nm;
	}
	public void setSeal_rqstman_nm(String seal_rqstman_nm) {
		this.seal_rqstman_nm = seal_rqstman_nm;
	}
	public String getSeal_rqstman_jikgup_nm() {
		return seal_rqstman_jikgup_nm;
	}
	public void setSeal_rqstman_jikgup_nm(String seal_rqstman_jikgup_nm) {
		this.seal_rqstman_jikgup_nm = seal_rqstman_jikgup_nm;
	}
	public String getSeal_rqst_dept_nm() {
		return seal_rqst_dept_nm;
	}
	public void setSeal_rqst_dept_nm(String seal_rqst_dept_nm) {
		this.seal_rqst_dept_nm = seal_rqst_dept_nm;
	}
	public String getSeal_rqst_status() {
		return seal_rqst_status;
	}
	public void setSeal_rqst_status(String seal_rqst_status) {
		this.seal_rqst_status = seal_rqst_status;
	}
	public String getSeal_rqstday() {
		return seal_rqstday;
	}
	public void setSeal_rqstday(String seal_rqstday) {
		this.seal_rqstday = seal_rqstday;
	}
	public String getSeal_ffmtman_id() {
		return seal_ffmtman_id;
	}
	public void setSeal_ffmtman_id(String seal_ffmtman_id) {
		this.seal_ffmtman_id = seal_ffmtman_id;
	}
	public String getSeal_ffmtman_nm() {
		return seal_ffmtman_nm;
	}
	public void setSeal_ffmtman_nm(String seal_ffmtman_nm) {
		this.seal_ffmtman_nm = seal_ffmtman_nm;
	}
	public String getSeal_ffmtman_jikgup_nm() {
		return seal_ffmtman_jikgup_nm;
	}
	public void setSeal_ffmtman_jikgup_nm(String seal_ffmtman_jikgup_nm) {
		this.seal_ffmtman_jikgup_nm = seal_ffmtman_jikgup_nm;
	}
	public String getSeal_ffmt_dept_nm() {
		return seal_ffmt_dept_nm;
	}
	public void setSeal_ffmt_dept_nm(String seal_ffmt_dept_nm) {
		this.seal_ffmt_dept_nm = seal_ffmt_dept_nm;
	}
	public String getSeal_ffmt_status() {
		return seal_ffmt_status;
	}
	public void setSeal_ffmt_status(String seal_ffmt_status) {
		this.seal_ffmt_status = seal_ffmt_status;
	}
	public String getSeal_ffmtday() {
		return seal_ffmtday;
	}
	public void setSeal_ffmtday(String seal_ffmtday) {
		this.seal_ffmtday = seal_ffmtday;
	}
	public String getDoc_issuer_id() {
		return doc_issuer_id;
	}
	public void setDoc_issuer_id(String doc_issuer_id) {
		this.doc_issuer_id = doc_issuer_id;
	}
	public String getDoc_issuer_nm() {
		return doc_issuer_nm;
	}
	public void setDoc_issuer_nm(String doc_issuer_nm) {
		this.doc_issuer_nm = doc_issuer_nm;
	}
	public String getDoc_issuer_jikgup_nm() {
		return doc_issuer_jikgup_nm;
	}
	public void setDoc_issuer_jikgup_nm(String doc_issuer_jikgup_nm) {
		this.doc_issuer_jikgup_nm = doc_issuer_jikgup_nm;
	}
	public String getDoc_issuer_dept_nm() {
		return doc_issuer_dept_nm;
	}
	public void setDoc_issuer_dept_nm(String doc_issuer_dept_nm) {
		this.doc_issuer_dept_nm = doc_issuer_dept_nm;
	}
	public String getDoc_issue_status() {
		return doc_issue_status;
	}
	public void setDoc_issue_status(String doc_issue_status) {
		this.doc_issue_status = doc_issue_status;
	}
	public String getDoc_issueday() {
		return doc_issueday;
	}
	public void setDoc_issueday(String doc_issueday) {
		this.doc_issueday = doc_issueday;
	}
	public String getRef_key() {
		return ref_key;
	}
	public void setRef_key(String ref_key) {
		this.ref_key = ref_key;
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
		
	
}