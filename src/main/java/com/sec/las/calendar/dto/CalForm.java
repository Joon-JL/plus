/**
 * Project Name : 법무시스템 구주총괄
 * File name	: CalForm.java
 * Description	: 법무시스템 캘린더 비지니스객체
 * Author		: 박병주
 * Date			: 2013. 10
 * Copyright 	: 2013 by SAMSUNG. All rights reserved.
 */
package com.sec.las.calendar.dto;

import com.sds.secframework.common.dto.CommonForm;

public class CalForm extends CommonForm  {
	private String div_grp_cd;
	
    private String seqno;
	private String srch_year;
	private String srch_month;
	private String srch_yyyymm;
	private String srch_yyyymmdd;
	private String srch_yyyymmdd2;
	private String srch_mode;
		
	private String srch_emp_no;
	private String write_mode;	
	private String pop_up_day;
	private String tab_mode;
	
	public String getSrch_yyyymmdd2() {
		return srch_yyyymmdd2;
	}
	public void setSrch_yyyymmdd2(String srch_yyyymmdd2) {
		this.srch_yyyymmdd2 = srch_yyyymmdd2;
	}
	public String getTab_mode() {
		return tab_mode;
	}
	public void setTab_mode(String tab_mode) {
		this.tab_mode = tab_mode;
	}
	public String getPop_up_day() {
		return pop_up_day;
	}
	public void setPop_up_day(String pop_up_day) {
		this.pop_up_day = pop_up_day;
	}
	public String getSeqno() {
		return seqno;
	}
	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}
	public String getWrite_mode() {
		return write_mode;
	}
	public void setWrite_mode(String write_mode) {
		this.write_mode = write_mode;
	}
	public String getSrch_mode() {
		return srch_mode;
	}
	public void setSrch_mode(String srch_mode) {
		this.srch_mode = srch_mode;
	}
	public String getSrch_year() {
		return srch_year;
	}
	public void setSrch_year(String srch_year) {
		this.srch_year = srch_year;
	}
	public String getSrch_month() {
		return srch_month;
	}
	public void setSrch_month(String srch_month) {
		this.srch_month = srch_month;
	}
	public String getSrch_yyyymm() {
		return srch_yyyymm;
	}
	public void setSrch_yyyymm(String srch_yyyymm) {
		this.srch_yyyymm = srch_yyyymm;
	}
	public String getSrch_yyyymmdd() {
		return srch_yyyymmdd;
	}
	public void setSrch_yyyymmdd(String srch_yyyymmdd) {
		this.srch_yyyymmdd = srch_yyyymmdd;
	}
	public String getDiv_grp_cd() {
		return div_grp_cd;
	}
	public void setDiv_grp_cd(String div_grp_cd) {
		this.div_grp_cd = div_grp_cd;
	}
	public String getSrch_emp_no() {
		return srch_emp_no;
	}
	public void setSrch_emp_no(String srch_emp_no) {
		this.srch_emp_no = srch_emp_no;
	}
}
