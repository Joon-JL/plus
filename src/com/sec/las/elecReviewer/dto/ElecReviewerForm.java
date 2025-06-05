/**
* Project Name : 법무 라이트
* File Name : ElecReviewerForm.java
* Description : 전자검토자 임시담당자 지정
* Author : 김현구
* Date : 2013. 06. 20
*/

package com.sec.las.elecReviewer.dto;

import java.util.List;

import com.sds.secframework.common.dto.CommonForm;
import com.sds.secframework.common.util.DateUtil;

public class ElecReviewerForm extends CommonForm{

	/*************************************
	 TABLE 변수
	 *************************************/
	
	/* TN_LAS_TMPRESP (메인테이블) */
	private String tmp_id;					//테이블 pk
	private String comp_cd;					//회사코드 (pk)
	private String comp_nm;					//회사이름
	private String org_resp_id;				//원담당자_id
	private String org_resp_nm;				//원담당자_이름
	private String org_resp_dept;			//원담당자_부서id
	private String org_resp_dept_nm;		//원담당자_부서_이름
	private String org_resp_jikgup_nm;		//원담당자_직급
	private String tmp_resp_id;				//임시담당자_id
	private String tmp_resp_nm;				//임시담당자_이름
	private String tmp_resp_dept;			//임시담당자_부서id
	private String tmp_resp_dept_nm;		//임시담당자_부서_이름
	private String tmp_resp_jikgup_nm;		//임시담당자_직급
	private String tmp_cause;				//임시담당자 지정 사유
	private String reg_dt;					//등록일
	private String reg_id;					//등록자id
	private String reg_nm;					//등록자_이름
	private String del_yn;					//삭제여부
	private String del_dt;					//삭제일
	private String del_id;					//삭제자id
	private String del_nm;					//삭제자_이름
	
	/* TN_LAS_TMPRESP_CONT (데이터 테이블) */
	private int seqno;
	private String cont_type;				//계약,자문,표준계약서 type (C:계약, L:자문, S:표준계약서)
	private String cont_id;					//데이터 PK
	
	/* TN_LAS_TMPRESP_MNG_COMP (임시담당자 관리회사코드 테이블) */
	private String mng_comp_cd;				//담당회사코드
	
	/*************************************
	 검색 필드
	 *************************************/
	private String srch_user_nm;


	public String getTmp_id() {
		return tmp_id;
	}


	public void setTmp_id(String tmp_id) {
		this.tmp_id = tmp_id;
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


	public String getOrg_resp_id() {
		return org_resp_id;
	}


	public void setOrg_resp_id(String org_resp_id) {
		this.org_resp_id = org_resp_id;
	}


	public String getOrg_resp_nm() {
		return org_resp_nm;
	}


	public void setOrg_resp_nm(String org_resp_nm) {
		this.org_resp_nm = org_resp_nm;
	}


	public String getOrg_resp_dept() {
		return org_resp_dept;
	}


	public void setOrg_resp_dept(String org_resp_dept) {
		this.org_resp_dept = org_resp_dept;
	}


	public String getOrg_resp_dept_nm() {
		return org_resp_dept_nm;
	}


	public void setOrg_resp_dept_nm(String org_resp_dept_nm) {
		this.org_resp_dept_nm = org_resp_dept_nm;
	}


	public String getOrg_resp_jikgup_nm() {
		return org_resp_jikgup_nm;
	}


	public void setOrg_resp_jikgup_nm(String org_resp_jikgup_nm) {
		this.org_resp_jikgup_nm = org_resp_jikgup_nm;
	}


	public String getTmp_resp_id() {
		return tmp_resp_id;
	}


	public void setTmp_resp_id(String tmp_resp_id) {
		this.tmp_resp_id = tmp_resp_id;
	}


	public String getTmp_resp_nm() {
		return tmp_resp_nm;
	}


	public void setTmp_resp_nm(String tmp_resp_nm) {
		this.tmp_resp_nm = tmp_resp_nm;
	}


	public String getTmp_resp_dept() {
		return tmp_resp_dept;
	}


	public void setTmp_resp_dept(String tmp_resp_dept) {
		this.tmp_resp_dept = tmp_resp_dept;
	}


	public String getTmp_resp_dept_nm() {
		return tmp_resp_dept_nm;
	}


	public void setTmp_resp_dept_nm(String tmp_resp_dept_nm) {
		this.tmp_resp_dept_nm = tmp_resp_dept_nm;
	}


	public String getTmp_resp_jikgup_nm() {
		return tmp_resp_jikgup_nm;
	}


	public void setTmp_resp_jikgup_nm(String tmp_resp_jikgup_nm) {
		this.tmp_resp_jikgup_nm = tmp_resp_jikgup_nm;
	}


	public String getTmp_cause() {
		return tmp_cause;
	}


	public void setTmp_cause(String tmp_cause) {
		this.tmp_cause = tmp_cause;
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


	public int getSeqno() {
		return seqno;
	}


	public void setSeqno(int seqno) {
		this.seqno = seqno;
	}


	public String getCont_type() {
		return cont_type;
	}


	public void setCont_type(String cont_type) {
		this.cont_type = cont_type;
	}


	public String getCont_id() {
		return cont_id;
	}


	public void setCont_id(String cont_id) {
		this.cont_id = cont_id;
	}


	public String getMng_comp_cd() {
		return mng_comp_cd;
	}


	public void setMng_comp_cd(String mng_comp_cd) {
		this.mng_comp_cd = mng_comp_cd;
	}


	public String getSrch_user_nm() {
		return srch_user_nm;
	}


	public void setSrch_user_nm(String srch_user_nm) {
		this.srch_user_nm = srch_user_nm;
	}

}
