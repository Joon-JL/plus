/**
 * Project Name : 법무시스템 이식
 * File name	: StatisticsWeekForm.java
 * Description	: 통계 주간업무 Data Form(Model), 서비스 로직-DB 연결
 * Author		: 서백진
 * Date			: 2011. 10. 17
 * Copyright	:
 */
package com.sec.las.statistics.dto;

import com.sds.secframework.common.dto.CommonForm;

public class StatisticsWeekForm extends CommonForm {
	
	private String dmstfrgn_gbn;	
	private String cnsdreq_id;
	private String crntweek_rslt;
	private String nextweek_plan;
	private String cntrt_id;
	private String cnsd_dept;
	private String weekdutyyn;	
	private String reg_id;
	private String seqno;
	private String cont;
	/**********************************************************************

	 * DB 이외 추가
	 **********************************************************************/
	private String page_gbn;
	private String totcnt;	
	private String start_index;
	private String end_index;
	private String curPage;
	private String transfer;	
	/**나모본문*/
	private String body_mime; 
	private String body_mime1; 
	/**검색등록일*/
	private String srch_regdt1;  
	/**검색등록일*/
	private String srch_regdt2;  
	/**검색주차*/
	private String srch_weekno;  	
	/**검색주 시작*/
	private String srch_weekfr; 
	/**검색주 끝*/
	private String srch_weekto; 	
	/**검색국내외구분*/
	private String srch_dmstfrgn_gbn;	
	/**실적구분*/
	private String result_yn;
	/**display구분*/
	private String display_yn;
	/**display구분(관리자용)*/
	private String display_conf_yn;	
	/**update key 조합*/	
	private String[] seqnos;	
	/**update 내용*/	
	private String[] conts;	
	/**update table*/	
	private String[] tabs;	
	private String tab;		
	/**update dept*/	
	private String[] cnsd_depts;
	/**update weekdutyyn*/	
	private String[] weekdutyyns;		
	private String weekFirstDay;      // 주간 시작 일자
	private String weekLastDay;       // 주간 종료 일자
	private String year;              // 주간 년도
	private String weekseq;           // 주간 주에 대한 시퀀스
	
	
	public void setSeqnos(String[] seqnos) {
		this.seqnos = seqnos;
	}
	public void setWeekdutyyns(String[] weekdutyyns) {
		this.weekdutyyns = weekdutyyns;
	}	
	public void setTabs(String[] tabs) {
		this.tabs = tabs;
	}
	public void setTab(String tab) {
		this.tab = tab;
	}	
	public void setCrntweek_rslt(String crntweek_rslt) {
		this.crntweek_rslt = crntweek_rslt;
	}	
	public void setNextweek_plan(String nextweek_plan) {
		this.nextweek_plan = nextweek_plan;
	}
	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}	
	public void setDmstfrgn_gbn(String dmstfrgn_gbn) {
		this.dmstfrgn_gbn = dmstfrgn_gbn;
	}	
	public void setSrch_dmstfrgn_gbn(String srch_dmstfrgn_gbn) {
		this.srch_dmstfrgn_gbn = srch_dmstfrgn_gbn;
	}		
	public void setPage_gbn(String page_gbn) {
		this.page_gbn = page_gbn;
	}
	public void setConts(String[] conts) {
		this.conts = conts;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}	
	public void setCnsdreq_id(String cnsdreq_id) {
		this.cnsdreq_id = cnsdreq_id;
	}
	public void setCntrt_id(String cntrt_id) {
		this.cntrt_id = cntrt_id;
	}	
	public void setCnsd_dept(String cnsd_dept) {
		this.cnsd_dept = cnsd_dept;
	}
	public void setCnsd_depts(String[] cnsd_depts) {
		this.cnsd_depts = cnsd_depts;
	}	
	public void setWeekdutyyn(String weekdutyyn) {
		this.weekdutyyn = weekdutyyn;
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
	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}												
	public void setTotcnt(String totcnt) {
		this.totcnt = totcnt;
	}
	public void setBody_mime(String body_mime) {
		this.body_mime = body_mime;
	}	
	public void setBody_mime1(String body_mime1) {
		this.body_mime1 = body_mime1;
	}	
	public void setSrch_regdt1(String srch_regdt1) {
		this.srch_regdt1 = srch_regdt1;
	}
	public void setSrch_regdt2(String srch_regdt2) {
		this.srch_regdt2 = srch_regdt2;
	}
	public void setResult_yn(String result_yn) {
		this.result_yn = result_yn;
	}
	public void setSrch_weekno(String srch_weekno) {
		this.srch_weekno = srch_weekno;
	}		
	public void setSrch_weekfr(String srch_weekfr) {
		this.srch_weekfr = srch_weekfr;
	}
	public void setSrch_weekto(String srch_weekto) {
		this.srch_weekto = srch_weekto;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public void setDisplay_yn(String display_yn) {
		this.display_yn = display_yn;
	}
	public void setDisplay_conf_yn(String display_conf_yn) {
		this.display_conf_yn = display_conf_yn;
	}
	public void setWeekseq(String weekseq) {
		this.weekseq = weekseq;
	}	
	public void setYear(String year) {
		this.year = year;
	}	
	public void setWeekFirstDay(String weekFirstDay) {
		this.weekFirstDay = weekFirstDay;
	}
	public void setWeekLastDay(String weekLastDay) {
		this.weekLastDay = weekLastDay;
	}
	
	public String getWeekseq() {
		return weekseq;
	}
	public String getYear() {
		return year;
	}
	public String getWeekFirstDay() {
		return weekFirstDay;
	}
	public String getWeekLastDay() {
		return weekLastDay;
	}
	public String getDisplay_conf_yn() {
		return this.display_conf_yn;
	}		
	public String getDisplay_yn() {
		return this.display_yn;
	}		
	public String getReg_id() {
		return this.reg_id;
	}		
	public String getSrch_weekto() {
		return this.srch_weekto;
	}		
	public String getSrch_weekfr() {
		return this.srch_weekfr;
	}	
	public String getSrch_weekno() {
		return this.srch_weekno;
	}	
	public String getResult_yn() {
		return this.result_yn;
	}	
	public String getSrch_regdt1() {
		return this.srch_regdt1;
	}
	public String getSrch_regdt2() {
		return this.srch_regdt2;
	}	
	public String getBody_mime() {
		return this.body_mime;
	}	
	public String getBody_mime1() {
		return this.body_mime1;
	}
	public String getTotcnt() {
		return this.totcnt;
	}	
	public String getTransfer() {
		return this.transfer;
	}		
	public String[] getSeqnos() {
		return this.seqnos;
	}
	public String getSeqno() {
		return this.seqno;
	}
	public String getCrntweek_rslt() {
		return this.crntweek_rslt;
	}
	public String getNextweek_plan() {
		return this.nextweek_plan;
	}	
	public String getDmstfrgn_gbn() {
		return this.dmstfrgn_gbn;
	}
	public String getSrch_dmstfrgn_gbn() {
		return this.srch_dmstfrgn_gbn;
	}	
	public String[] getConts() {
		return this.conts;
	}
	public String getCont() {
		return this.cont;
	}	
	public String[] getTabs() {
		return this.tabs;
	}
	public String getTab() {
		return this.tab;
	}	
	public String getCnsdreq_id() {
		return this.cnsdreq_id;
	}
	public String getCntrt_id() {
		return this.cntrt_id;
	}
	public String getCnsd_dept() {
		return this.cnsd_dept;
	}
	public String[] getCnsd_depts() {
		return this.cnsd_depts;
	}	
	public String getWeekdutyyn() {
		return this.weekdutyyn;
	}
	public String[] getWeekdutyyns() {
		return this.weekdutyyns;
	}	
	public String getPage_gbn() {
		return this.page_gbn;
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
}