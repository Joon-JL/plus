/**
 * Project Name : 법무시스템 이식
 * File name	: StatisticsForm.java
 * Description	: 통계 Data Form(Model), 서비스 로직-DB 연결
 * Author		: 서백진
 * Date			: 2011. 09. 06
 * Copyright	:
 */
package com.sec.las.statistics.dto;

import com.sds.secframework.common.dto.CommonForm;

public class StatisticsForm extends CommonForm {
	private String req_no;
	private String dmstfrgn_gbn;

	private String start_index;
	private String end_index;
	private String curPage;
	private String cntrtType;	// 계약유형
	private String transfer;

	/**검색시작일*/
	private String srch_regdt1;  
	/**검색종료일*/
	private String srch_regdt2;
	/**검색국내외구분*/
	private String srch_dmstfrgn_gbn;
	/**전주 시작일 */
	private String srch_beforeweek_s;
	/**전주 종료일 */
	private String srch_beforeweek_e;
	/**전월 시작일 */
	private String srch_beforemonth_s;
	/**전월 종료일 */
	private String srch_beforemonth_e;
	/**금주 시작일 */
	private String srch_curweek_s;
	/**금주 종료일 */
	private String srch_curweek_e;
	/**금월 시작일 */
	private String srch_curmonth_s;
	/**금월 종료일 */
	private String srch_curmonth_e;
	
	private String srch_year;
	private String srch_bigclsfcn;
	private String srch_midclsfcn;
	private String srch_gbn;
	
	/**보고서 관련*/
	private String report_url;
	private String report_param;
	private String srch_dept;
	private String srch_prcs;
	
	/**주간보고 관련*/
	private String seqno;             // 시퀀스 번호
	private String crntweek_rslt;     // 주 결과
	private String nextweek_plan;     // 차주 결과
	private String weekdutyyn;        // 주간보고 보고 여부
	private String reg_id;            // 등록자 ID
	private String weekFirstDay;      // 주간 시작 일자
	private String weekLastDay;       // 주간 종료 일자
	private String year;              // 주간 년도
	private String weekseq;           // 주간 주에 대한 시퀀스
	private String result_yn;         // 실적/계획에 따른 구분 값
	private String cnslt_no;          // 주간-자문 번호
	private String title;             // 주간-자문 제목
	private String cont;              // 주간-자문 내용
	private String mod_id;            // 수정자 아이디
	private String mod_nm;            // 수정자 이름
	private String re_cont;           // 응답
	private String reqman_dept_nm;    // 요청 부서 이름
	private String page_gbn;          // 페이지 구분
	private String display_conf_yn;   // 보여주는 구분
	private String display_yn;
	private String report_nm;         // 리포트 이름
	private String mrd;   
	
	/** 신규 통계 관련 필드 **/
	private String gubun ; // 1:담당자배정현황, 2:담당자별통계현황, 3:사업부별통계현황, 4.법무팀별통계현황
	private String srch_type; // 검색 구분
	private String srch_div ; // 검색 분류 
	private String srch_start_dt ; // 검색 시작일
	private String srch_end_dt ; // 검색 종료일
	private String srch_period_gbn1 ; // 1:현재기준, 2:기간별 실적 
	private String srch_period_gbn2 ;  // 1:연도별, 2:분기별, 3:월별, 4:기간설정
	private String srch_month ; // 검색 월
	private String srch_quarter; // 검색 분기
	private String srch_comp_cd; // 지법인코드
	
	private String srch_cntrt_oppnt_nm; //Counterparty
	private String srch_cntrtperiod_startday; //Contract Term
	private String srch_cntrtperiod_endday; //Contract Term
	
	private String srch_req_title; //제목
	private String srch_requester; //requester
	private String srch_reviewer; //reviewer
	
	/** 신규 통계 관련 필드 **/
	private int srch_start_y ; // 검색 시작년
	private int srch_end_y ; // 검색 종료년
	
	/** 신규통계 - 단계별소요시간 필드 **/
	private String req_lt;	// 계약검토 리드타임
	private String cnt_lt;	// 계약체결 리드타임
	private String reg_lt;	// 계약등록 리드타임

	private String[] this_mon;	//이번달
	private String[] one_mon;	//1month
	private String[] three_mon;	//3month
	private String[] one_year;	//1년
	private String day_set;	//선택한 날자타입
	
	public int getSrch_start_y() {
		return srch_start_y;
	}
	public void setSrch_start_y(int srch_start_y) {
		this.srch_start_y = srch_start_y;
	}
	public int getSrch_end_y() {
		return srch_end_y;
	}
	public void setSrch_end_y(int srch_end_y) {
		this.srch_end_y = srch_end_y;
	}
	
	public String getMrd() {
		return mrd;
	}
	public void setMrd(String mrd) {
		this.mrd = mrd;
	}
	public String getReport_nm() {
		return report_nm;
	}
	public void setReport_nm(String report_nm) {
		this.report_nm = report_nm;
	}
	public void setSrch_midclsfcn(String srch_midclsfcn) {
		this.srch_midclsfcn = srch_midclsfcn;
	}
	public String getDisplay_yn() {
		return display_yn;
	}
	public void setDisplay_yn(String display_yn) {
		this.display_yn = display_yn;
	}
	public String getDisplay_conf_yn() {
		return display_conf_yn;
	}
	public void setDisplay_conf_yn(String display_conf_yn) {
		this.display_conf_yn = display_conf_yn;
	}
	public String getPage_gbn() {
		return page_gbn;
	}
	public void setPage_gbn(String page_gbn) {
		this.page_gbn = page_gbn;
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
	public String getRe_cont() {
		return re_cont;
	}
	public void setRe_cont(String re_cont) {
		this.re_cont = re_cont;
	}
	public String getReqman_dept_nm() {
		return reqman_dept_nm;
	}
	public void setReqman_dept_nm(String reqman_dept_nm) {
		this.reqman_dept_nm = reqman_dept_nm;
	}
	public String getCnslt_no() {
		return cnslt_no;
	}
	public void setCnslt_no(String cnslt_no) {
		this.cnslt_no = cnslt_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	public String getResult_yn() {
		return result_yn;
	}
	public void setResult_yn(String result_yn) {
		this.result_yn = result_yn;
	}
	public String getWeekseq() {
		return weekseq;
	}
	public void setWeekseq(String weekseq) {
		this.weekseq = weekseq;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getWeekFirstDay() {
		return weekFirstDay;
	}
	public void setWeekFirstDay(String weekFirstDay) {
		this.weekFirstDay = weekFirstDay;
	}
	public String getWeekLastDay() {
		return weekLastDay;
	}
	public void setWeekLastDay(String weekLastDay) {
		this.weekLastDay = weekLastDay;
	}
	public String getSeqno() {
		return seqno;
	}
	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}
	public String getCrntweek_rslt() {
		return crntweek_rslt;
	}
	public void setCrntweek_rslt(String crntweek_rslt) {
		this.crntweek_rslt = crntweek_rslt;
	}
	public String getNextweek_plan() {
		return nextweek_plan;
	}
	public void setNextweek_plan(String nextweek_plan) {
		this.nextweek_plan = nextweek_plan;
	}
	public String getWeekdutyyn() {
		return weekdutyyn;
	}
	public void setWeekdutyyn(String weekdutyyn) {
		this.weekdutyyn = weekdutyyn;
	}
	public String getReg_id() {
		return reg_id;
	}
	public String getReport_url() {
		return report_url;
	}
	public String getReport_param() {
		return report_param;
	}
	public String getSrch_dept() {
		return srch_dept;
	}	
	public String getSrch_year() {
		return srch_year;
	}	
	public String getSrch_bigclsfcn() {
		return srch_bigclsfcn;
	}
	
	public String getSrch_midclsfcn() {
		return srch_midclsfcn;
	}	
	public String getSrch_prcs() {
		return srch_prcs;
	}	
	public String getSrch_gbn() {
		return srch_gbn;
	}	

	public void setSrch_gbn(String srch_gbn) {
		this.srch_gbn = srch_gbn;
	}
	public void setSrch_prcs(String srch_prcs) {
		this.srch_prcs = srch_prcs;
	}
	public void setSrch_misclsfcn(String srch_midclsfcn) {
		this.srch_midclsfcn = srch_midclsfcn;
	}
	public void setSrch_bigclsfcn(String srch_bigclsfcn) {
		this.srch_bigclsfcn = srch_bigclsfcn;
	}
	public void setSrch_year(String srch_year) {
		this.srch_year = srch_year;
	}
	public void setReport_url(String report_url) {
		this.report_url = report_url;
	}
	public void setReport_param(String report_param) {
		this.report_param = report_param;
	}
	public void setSrch_dept(String srch_dept) {
		this.srch_dept = srch_dept;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public void setReq_no(String req_no) {
		this.req_no = req_no;
	}
	public void setDmstfrgn_gbn(String dmstfrgn_gbn) {
		this.dmstfrgn_gbn = dmstfrgn_gbn;
	}
	public void setSrch_dmstfrgn_gbn(String srch_dmstfrgn_gbn) {
		this.srch_dmstfrgn_gbn = srch_dmstfrgn_gbn;
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
	public void setCntrtType(String cntrtType) {
		this.cntrtType = cntrtType;
	}	
	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}
	public void setSrch_regdt1(String srch_regdt1) {
		this.srch_regdt1 = srch_regdt1;
	}
	public void setSrch_regdt2(String srch_regdt2) {
		this.srch_regdt2 = srch_regdt2;
	}				
	
	public void setSrch_beforeweek_s(String srch_beforeweek_s) {
		this.srch_beforeweek_s = srch_beforeweek_s;
	}
	public void setSrch_beforeweek_e(String srch_beforeweek_e) {
		this.srch_beforeweek_e = srch_beforeweek_e;
	}
	public void setSrch_beforemonth_s(String srch_beforemonth_s) {
		this.srch_beforemonth_s = srch_beforemonth_s;
	}
	public void setSrch_beforemonth_e(String srch_beforemonth_e) {
		this.srch_beforemonth_e = srch_beforemonth_e;
	}
	public void setSrch_curweek_s(String srch_curweek_s) {
		this.srch_curweek_s = srch_curweek_s;
	}
	public void setSrch_curweek_e(String srch_curweek_e) {
		this.srch_curweek_e = srch_curweek_e;
	}
	public void setSrch_curmonth_s(String srch_curmonth_s) {
		this.srch_curmonth_s = srch_curmonth_s;
	}
	public void setSrch_curmonth_e(String srch_curmonth_e) {
		this.srch_curmonth_e = srch_curmonth_e;
	}

	public String getSrch_beforeweek_s() {
		return this.srch_beforeweek_s;
	}
	public String getSrch_beforeweek_e() {
		return this.srch_beforeweek_e;
	}	
	public String getSrch_beforemonth_s() {
		return this.srch_beforemonth_s;
	}
	public String getSrch_beforemonth_e() {
		return this.srch_beforemonth_e;
	}
	public String getSrch_curweek_s() {
		return this.srch_curweek_s;
	}
	public String getSrch_curweek_e() {
		return this.srch_curweek_e;
	}
	public String getSrch_curmonth_s() {
		return this.srch_curmonth_s;
	}
	public String getSrch_curmonth_e() {
		return this.srch_curmonth_e;
	}	
	public String getSrch_regdt1() {
		return this.srch_regdt1;
	}
	public String getSrch_regdt2() {
		return this.srch_regdt2;
	}	
	public String getTransfer() {
		return this.transfer;
	}		
	public String getReq_no() {
		return this.req_no;
	}
	public String getDmstfrgn_gbn() {
		return this.dmstfrgn_gbn;
	}
	public String getSrch_dmstfrgn_gbn() {
		return this.srch_dmstfrgn_gbn;
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
	public String getCntrtType() {
		return this.cntrtType;
	}
	public String getGubun() {
		return gubun;
	}
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}
	public String getSrch_type() {
		return srch_type;
	}
	public void setSrch_type(String srch_type) {
		this.srch_type = srch_type;
	}
	public String getSrch_div() {
		return srch_div;
	}
	public void setSrch_div(String srch_div) {
		this.srch_div = srch_div;
	}
	public String getSrch_start_dt() {
		return srch_start_dt;
	}
	public void setSrch_start_dt(String srch_start_dt) {
		this.srch_start_dt = srch_start_dt;
	}
	public String getSrch_end_dt() {
		return srch_end_dt;
	}
	public void setSrch_end_dt(String srch_end_dt) {
		this.srch_end_dt = srch_end_dt;
	}
	public String getSrch_period_gbn1() {
		return srch_period_gbn1;
	}
	public void setSrch_period_gbn1(String srch_period_gbn1) {
		this.srch_period_gbn1 = srch_period_gbn1;
	}
	public String getSrch_period_gbn2() {
		return srch_period_gbn2;
	}
	public void setSrch_period_gbn2(String srch_period_gbn2) {
		this.srch_period_gbn2 = srch_period_gbn2;
	}
	public String getSrch_month() {
		return srch_month;
	}
	public void setSrch_month(String srch_month) {
		this.srch_month = srch_month;
	}
	public String getSrch_quarter() {
		return srch_quarter;
	}
	public void setSrch_quarter(String srch_quarter) {
		this.srch_quarter = srch_quarter;
	}	

	public String getReq_lt(){
		return req_lt;
	}
	
	public void set (String req_lt){
		this.req_lt = req_lt;
	}
	
	public String getCnt_lt(){
		return cnt_lt;
	}
	
	public void setCnt_lt(String cnt_lt){
		this.cnt_lt = cnt_lt;
	}
	
	public String getReg_lt(){
		return reg_lt;
	}
	
	public void setReg_lt(String reg_lt){
		this.reg_lt = reg_lt;
	}
	
	public String getSrch_req_title(){
		return srch_req_title;
	}
	
	public void setSrch_req_title(String srch_req_title){
		this.srch_req_title = srch_req_title;
	}
	
	public String getSrch_requester(){
		return srch_requester;
	}
	
	public void setSrch_requester(String srch_requester){
		this.srch_requester = srch_requester;
	}
	
	public String getSrch_reviewer(){
		return srch_reviewer;
	}
	
	public void setSrch_reviewer(String srch_reviewer){
		this.srch_reviewer = srch_reviewer;
	}
	
	public String[] getThis_mon(){
		return this_mon;
	}
	
	public void setThis_mon(String[] this_mon){
		this.this_mon = this_mon;
	}
	
	public String[] getOne_mon(){
		return one_mon;
	}
	
	public void setOne_mon(String[] one_mon){
		this.one_mon = one_mon;
	}

	public String[] getThree_mon(){
		return three_mon;
	}
	
	public void setThree_mon(String[] three_mon){
		this.three_mon = three_mon;
	}

	public String[] getOne_year(){
		return one_year;
	}
	
	public void setOne_year(String[] one_year){
		this.one_year = one_year;
	}
	
	public String getDay_set(){
		return day_set;
	}
	
	public void setDay_set(String day_set){
		this.day_set = day_set;
	}
	
	public String getSrch_cntrt_oppnt_nm(){
		return srch_cntrt_oppnt_nm;
	}
	
	public void setSrch_cntrt_oppnt_nm(String srch_cntrt_oppnt_nm){
		this.srch_cntrt_oppnt_nm = srch_cntrt_oppnt_nm;
	}
	
	public String getSrch_cntrtperiod_startday(){
		return srch_cntrtperiod_startday;
	}
	
	public void setSrch_cntrtperiod_startday(String srch_cntrtperiod_startday){
		this.srch_cntrtperiod_startday = srch_cntrtperiod_startday;
	}
	
	public String getSrch_cntrtperiod_endday(){
		return srch_cntrtperiod_endday;
	}
	
	public void setSrch_cntrtperiod_endday(String srch_cntrtperiod_endday){
		this.srch_cntrtperiod_endday = srch_cntrtperiod_endday; 
	}
	
	public String getSrch_comp_cd(){
		return srch_comp_cd;
	}
	
	public void setSrch_comp_cd(String srch_comp_cd){
		this.srch_comp_cd = srch_comp_cd; 
	}
}