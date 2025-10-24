/**
 * Project Name : 법무시스템 이식
 * File name	: LawyerManageForm.java
 * Description	: 변호사 관리 Data Form(Model)
 * Author		: 박병주
 * Date			: 2011. 09.
 * Copyright	: 2011 by SAMSUNG. All rights reserved.     
 */
package com.sec.las.lawservice.dto;

public class LawyerManageForm extends LwsCommonForm {
	
	// 사건번호
	private String[] event_no;	
	// 변호사 평가 번호
	private String estmt_no;
	// 변호사 평가 이름
	private String[] estmt_title;
	// 변호사 평가 내용
	private String[] estmt_cont;	
	// 사건 번호  임시저장용
	private String event_no_tmp;	
	// 변호사 평가 번호 임시저장용
	private String estmt_title_tmp;	
	// 변호사 평가 내용 임시저장용
	private String estmt_cont_tmp;	
	// 변호사_직급_이름
	private String lwr_jikgup_nm;	
	// 변호사_평가레벨_이름
	private String lwr_estmt_lvl_nm;	
	// 변호사평가 리스트 수
	private int estimate_list_cnt;
	// 사건 리스트 수
	private int event_list_cnt;
	// 로펌_ID
	private String lawfirm_id;
	// 변호사 순서
	private String lwr_srt ;
	// 변호사 번호
	private String lwr_no;	
	// 변호사 이름
	private String lwr_nm;
	// 첫이름
	private String first_name;
	// 마지막_이름
	private String last_name;
	// 변호사_직급
	private String lwr_jikgup;
	// 전화번호
	private String telno;
	// 지급_레벨
	private String pay_lvl;
	// 이메일
	private String email;
	// 전문_분야
	private String expert_area;
	// 출신_학교
	private String from_school;
	// 출신_학교_졸업년도
	private String from_school_grdtyear;
	// 경력_내용
	private String career_cont;
	// 변호사_평가
	private String lwr_estmt;
	// 변호사_평가_레벨
	private String lwr_estmt_lvl;
	// 변호사_평가일
	private String lwr_estmtday;
	// 로스쿨
	private String lawschool;
	// 로스쿨_졸업년도
	private String lawschool_grdtyear;
	// 국가
	private String nation;	
	// 국가이름
	private String nation_nm;		

	private String lawfirm_nm;	
	private String lawschool_nm;

	public int getEstimate_list_cnt() {
		return estimate_list_cnt;
	}
	public void setEstimate_list_cnt(int estimate_list_cnt) {
		this.estimate_list_cnt = estimate_list_cnt;
	}
	public int getEvent_list_cnt() {
		return event_list_cnt;
	}
	public void setEvent_list_cnt(int event_list_cnt) {
		this.event_list_cnt = event_list_cnt;
	}	
	public String getLawschool_nm() {
		return lawschool_nm;
	}
	public void setLawschool_nm(String lawschool_nm) {
		this.lawschool_nm = lawschool_nm;
	}
	public String getLawfirm_nm() {
		return lawfirm_nm;
	}
	public void setLawfirm_nm(String lawfirm_nm) {
		this.lawfirm_nm = lawfirm_nm;
	}
	public String getNation_nm() {
		return nation_nm;
	}
	public void setNation_nm(String nation_nm) {
		this.nation_nm = nation_nm;
	}
	public String getLwr_jikgup_nm() {
		return lwr_jikgup_nm;
	}
	public void setLwr_jikgup_nm(String lwr_jikgup_nm) {
		this.lwr_jikgup_nm = lwr_jikgup_nm;
	}
	public String getLwr_estmt_lvl_nm() {
		return lwr_estmt_lvl_nm;
	}
	public void setLwr_estmt_lvl_nm(String lwr_estmt_lvl_nm) {
		this.lwr_estmt_lvl_nm = lwr_estmt_lvl_nm;
	}	
	public String getEvent_no_tmp() {
		return event_no_tmp;
	}
	public void setEvent_no_tmp(String event_no_tmp) {
		this.event_no_tmp = event_no_tmp;
	}
	public String getEstmt_title_tmp() {
		return estmt_title_tmp;
	}
	public void setEstmt_title_tmp(String estmt_title_tmp) {
		this.estmt_title_tmp = estmt_title_tmp;
	}
	public String getEstmt_cont_tmp() {
		return estmt_cont_tmp;
	}
	public void setEstmt_cont_tmp(String estmt_cont_tmp) {
		this.estmt_cont_tmp = estmt_cont_tmp;
	}
	public String[] getEvent_no() {
		return event_no;
	}
	public void setEvent_no(String[] event_no) {
		this.event_no = event_no;
	}
	public String getEstmt_no() {
		return estmt_no;
	}
	public void setEstmt_no(String estmt_no) {
		this.estmt_no = estmt_no;
	}
	public String[] getEstmt_title() {
		return estmt_title;
	}
	public void setEstmt_title(String[] estmt_title) {
		this.estmt_title = estmt_title;
	}
	public String[] getEstmt_cont() {
		return estmt_cont;
	}
	public void setEstmt_cont(String[] estmt_cont) {
		this.estmt_cont = estmt_cont;
	}
	public String getLawfirm_id() {
		return lawfirm_id;
	}
	public void setLawfirm_id(String lawfirm_id) {
		this.lawfirm_id = lawfirm_id;
	}
	public String getLwr_srt() {
		return lwr_srt;
	}
	public void setLwr_srt(String lwr_srt) {
		this.lwr_srt = lwr_srt;
	}
	public String getLwr_no() {
		return lwr_no;
	}
	public void setLwr_no(String lwr_no) {
		this.lwr_no = lwr_no;
	}
	public String getLwr_nm() {
		return lwr_nm;
	}
	public void setLwr_nm(String lwr_nm) {
		this.lwr_nm = lwr_nm;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getLwr_jikgup() {
		return lwr_jikgup;
	}
	public void setLwr_jikgup(String lwr_jikgup) {
		this.lwr_jikgup = lwr_jikgup;
	}
	public String getTelno() {
		return telno;
	}
	public void setTelno(String telno) {
		this.telno = telno;
	}
	public String getPay_lvl() {
		return pay_lvl;
	}
	public void setPay_lvl(String pay_lvl) {
		this.pay_lvl = pay_lvl;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getExpert_area() {
		return expert_area;
	}
	public void setExpert_area(String expert_area) {
		this.expert_area = expert_area;
	}
	public String getFrom_school() {
		return from_school;
	}
	public void setFrom_school(String from_school) {
		this.from_school = from_school;
	}
	public String getFrom_school_grdtyear() {
		return from_school_grdtyear;
	}
	public void setFrom_school_grdtyear(String from_school_grdtyear) {
		this.from_school_grdtyear = from_school_grdtyear;
	}
	public String getCareer_cont() {
		return career_cont;
	}
	public void setCareer_cont(String career_cont) {
		this.career_cont = career_cont;
	}
	public String getLwr_estmt() {
		return lwr_estmt;
	}
	public void setLwr_estmt(String lwr_estmt) {
		this.lwr_estmt = lwr_estmt;
	}
	public String getLwr_estmt_lvl() {
		return lwr_estmt_lvl;
	}
	public void setLwr_estmt_lvl(String lwr_estmt_lvl) {
		this.lwr_estmt_lvl = lwr_estmt_lvl;
	}
	public String getLwr_estmtday() {
		return lwr_estmtday;
	}
	public void setLwr_estmtday(String lwr_estmtday) {
		this.lwr_estmtday = lwr_estmtday;
	}
	public String getLawschool() {
		return lawschool;
	}
	public void setLawschool(String lawschool) {
		this.lawschool = lawschool;
	}
	public String getLawschool_grdtyear() {
		return lawschool_grdtyear;
	}
	public void setLawschool_grdtyear(String lawschool_grdtyear) {
		this.lawschool_grdtyear = lawschool_grdtyear;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}	
}
