/**
 * Project Name : 법무시스템 구주총괄
 * File name	: CalVO.java
 * Description	: 법무시스템 캘린더 비지니스객체
 * Author		: 박병주
 * Date			: 2013. 10
 * Copyright 	: 2013 by SAMSUNG. All rights reserved.
 */
package com.sec.las.calendar.dto;

import com.sds.secframework.common.dto.CommonVO;

public class CalVO extends CommonVO  {
	
	private String srch_year;
	private String srch_month;
	private String srch_yyyymm;
	private String srch_yyyymmdd;
	private String srch_yyyymmdd2;
	private String srch_mode;		

	private String srch_emp_no;
	
    private String seqno;
    private String title;
    private String loca;
    private String cont;
    private String start_dt;
    private String start_hh;
    private String start_mm;
    private String end_dt;
    private String end_hh;
    private String end_mm;
    private String type1;
    private String type2;
    
    private String write_mode;
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

	public String getSrch_mode() {
		return srch_mode;
	}

	public void setSrch_mode(String srch_mode) {
		this.srch_mode = srch_mode;
	}

	public String getSrch_emp_no() {
		return srch_emp_no;
	}

	public void setSrch_emp_no(String srch_emp_no) {
		this.srch_emp_no = srch_emp_no;
	}

	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLoca() {
		return loca;
	}

	public void setLoca(String loca) {
		this.loca = loca;
	}

	public String getCont() {
		return cont;
	}

	public void setCont(String cont) {
		this.cont = cont;
	}

	public String getStart_dt() {
		return start_dt;
	}

	public void setStart_dt(String start_dt) {
		this.start_dt = start_dt;
	}

	public String getEnd_dt() {
		return end_dt;
	}

	public void setEnd_dt(String end_dt) {
		this.end_dt = end_dt;
	}

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	public String getWrite_mode() {
		return write_mode;
	}

	public void setWrite_mode(String write_mode) {
		this.write_mode = write_mode;
	}

	public String getStart_hh() {
		return start_hh;
	}

	public void setStart_hh(String start_hh) {
		this.start_hh = start_hh;
	}

	public String getStart_mm() {
		return start_mm;
	}

	public void setStart_mm(String start_mm) {
		this.start_mm = start_mm;
	}

	public String getEnd_hh() {
		return end_hh;
	}

	public void setEnd_hh(String end_hh) {
		this.end_hh = end_hh;
	}

	public String getEnd_mm() {
		return end_mm;
	}

	public void setEnd_mm(String end_mm) {
		this.end_mm = end_mm;
	}
	
}
