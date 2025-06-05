/**
 * Project Name : 법무시스템 이식
 * File name	: EventManageVO.java
 * Description	: 사건관리  Data Form(Model)
 * Author		: 박병주
 * Date			: 2011. 09
 * Copyright	:   
 */
package com.sec.las.lawservice.dto;


public class EventManageVO extends LwsCommonVO {
	// 사건_번호
	private String event_no;
	// 사건_명
	private String event_nm;
	// 사건_구분1
	private String event_gbn1;
	// 사건_구분2
	private String event_gbn2;
	// 사건_내용
	private String event_cont;
	// 소송_대상
	private String lawsuit_trgt;
	private String event_gbn1_nm;
	private String event_gbn2_nm;

	private String lawsuit_trgt_nm;	
	private String lawsuit_trgt_cd;
	private String acpt_dt;
	private String acpt_nm;
	private String lwr_no;
	private String lawfirm_id;
	private String intnl_dept_cd;
	private String dept_nm;
	private String grp_dept_cd;

	public String getEvent_gbn1_nm() {
		return event_gbn1_nm;
	}
	public void setEvent_gbn1_nm(String event_gbn1_nm) {
		this.event_gbn1_nm = event_gbn1_nm;
	}
	public String getEvent_gbn2_nm() {
		return event_gbn2_nm;
	}
	public void setEvent_gbn2_nm(String event_gbn2_nm) {
		this.event_gbn2_nm = event_gbn2_nm;
	}
	public String getLawsuit_trgt_cd() {
		return lawsuit_trgt_cd;
	}
	public void setLawsuit_trgt_cd(String lawsuit_trgt_cd) {
		this.lawsuit_trgt_cd = lawsuit_trgt_cd;
	}
	public String getDept_nm() {
		return dept_nm;
	}
	public void setDept_nm(String dept_nm) {
		this.dept_nm = dept_nm;
	}
	public String getGrp_dept_cd() {
		return grp_dept_cd;
	}
	public void setGrp_dept_cd(String grp_dept_cd) {
		this.grp_dept_cd = grp_dept_cd;
	}
	public String getIntnl_dept_cd() {
		return intnl_dept_cd;
	}
	public void setIntnl_dept_cd(String intnl_dept_cd) {
		this.intnl_dept_cd = intnl_dept_cd;
	}
	public String getLawsuit_trgt_nm() {
		return lawsuit_trgt_nm;
	}
	public void setLawsuit_trgt_nm(String lawsuit_trgt_nm) {
		this.lawsuit_trgt_nm = lawsuit_trgt_nm;
	}
	public String getAcpt_dt() {
		return acpt_dt;
	}
	public void setAcpt_dt(String acpt_dt) {
		this.acpt_dt = acpt_dt;
	}
	public String getAcpt_nm() {
		return acpt_nm;
	}
	public void setAcpt_nm(String acpt_nm) {
		this.acpt_nm = acpt_nm;
	}
	public String getLwr_no() {
		return lwr_no;
	}
	public void setLwr_no(String lwr_no) {
		this.lwr_no = lwr_no;
	}
	public String getLawfirm_id() {
		return lawfirm_id;
	}
	public void setLawfirm_id(String lawfirm_id) {
		this.lawfirm_id = lawfirm_id;
	}
	public void setEvent_no(String event_no) {
		this.event_no = event_no;
	}
	public void setEvent_nm(String event_nm) {
		this.event_nm = event_nm;
	}
	public void setEvent_gbn1(String event_gbn1) {
		this.event_gbn1 = event_gbn1;
	}
	public void setEvent_gbn2(String event_gbn2) {
		this.event_gbn2 = event_gbn2;
	}
	public void setEvent_cont(String event_cont) {
		this.event_cont = event_cont;
	}
	public void setLawsuit_trgt(String lawsuit_trgt) {
		this.lawsuit_trgt = lawsuit_trgt;
	}
	public String getEvent_no() {
		return this.event_no;
	}
	public String getEvent_nm() {
		return this.event_nm;
	}
	public String getEvent_gbn1() {
		return this.event_gbn1;
	}
	public String getEvent_gbn2() {
		return this.event_gbn2;
	}
	public String getEvent_cont() {
		return this.event_cont;
	}
	public String getLawsuit_trgt() {
		return this.lawsuit_trgt;
	}
}