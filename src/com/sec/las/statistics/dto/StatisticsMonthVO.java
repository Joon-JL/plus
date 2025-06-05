/**
 * Project Name : 법무시스템 이식
 * File name	: StatisticsMonthVO.java
 * Description	: 통계 월간업무 Data Form(Model), 서비스 로직-DB 연결
 * Author		: 서백진
 * Date			: 2011. 09. 06
 * Copyright	:
 */
package com.sec.las.statistics.dto;

import com.sds.secframework.common.dto.CommonVO;

public class StatisticsMonthVO extends CommonVO {
	
	private String dmstfrgn_gbn;	
	private String cnsdreq_id;
	private String crntmonth_rslt;
	private String nextmonth_plan;
	private String cntrt_id;
	private String cnsd_dept;
	private String monthdutyyn;	
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

	/**update monthdutyyn*/	
	private String[] monthdutyyns;		
	
	public void setSeqnos(String[] seqnos) {
		this.seqnos = seqnos;
	}
	public void setMonthdutyyns(String[] monthdutyyns) {
		this.monthdutyyns = monthdutyyns;
	}	
	public void setTabs(String[] tabs) {
		this.tabs = tabs;
	}
	public void setTab(String tab) {
		this.tab = tab;
	}	
	public void setCrntmonth_rslt(String crntmonth_rslt) {
		this.crntmonth_rslt = crntmonth_rslt;
	}	
	public void setNextmonth_plan(String nextmonth_plan) {
		this.nextmonth_plan = nextmonth_plan;
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
	public void setMonthdutyyn(String monthdutyyn) {
		this.monthdutyyn = monthdutyyn;
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
	public String getCrntmonth_rslt() {
		return this.crntmonth_rslt;
	}
	public String getNextmonth_plan() {
		return this.nextmonth_plan;
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
	public String getMonthdutyyn() {
		return this.monthdutyyn;
	}
	public String[] getMonthdutyyns() {
		return this.monthdutyyns;
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