/**
 * Project Name : 법무시스템 이식
 * File name	: LawfirmManageForm.java
 * Description	: 로펌관리 Data Form(Model)
 * Author		: 박병주
 * Date			: 2011. 08. 20
 * Copyright	:   
 */
package com.sec.las.lawservice.dto;

public class LawfirmManageForm extends LwsCommonForm {
	/**********************************************************************
	 * DB 변수
	 **********************************************************************/
	/** 로펌_ID */
	private String lawfirm_id;
	/** 국가 */
	private String nation;
	/** 로펌_명 */
	private String lawfirm_nm;
	/** 최초_담당사건 */
	private String fst_event_no;
	/** 최초_계약일 */
	private String fst_cntrtday;
	/** 대표_전화 */
	private String rprsnt_tel;
	/** 대표_팩스 */
	private String rprsnt_fax;
	/** 대표_이메일 */
	private String rprsnt_email;
	/** 홈페이지 */
	private String homepage;
	/** 주_구분 */
	private String state_gbn;
	/** 주소 */
	private String addr;
	/** 주요특징_평가 */
	private String mainftre_estmt;
	/** 은행_주소 */
	private String bank_addr;
	/** 은행_명 */
	private String bank_nm;
	/** 계좌_번호 */
	private String accnt_no;
	/** 은행_비고 */
	private String bank_note;
	/** 로그_일자 */
	private String log_ymd;
	
	/**********************************************************************
	 * DB 외  변수
	 **********************************************************************/	

	/** 로펌평가수 */
	private String estmt_no;
	/** 등록구분 */
	private String insert_kbn;
	/** 국가이름 */
	private String nation_nm;
	/** 최초담당사건 이름 */
	private String fst_event_nm;	
	/** 검색된 하단 사건리스트 수 */
	private int event_list_cnt;	
	/** 검색된평가리스트 수 */
	private int estimate_list_cnt;	
	
	/**********************************************************************
	 * Getters and Setters Method
	 **********************************************************************/	
	
	public String getInsert_kbn() {
		return insert_kbn;
	}
	public void setInsert_kbn(String insert_kbn) {
		this.insert_kbn = insert_kbn;
	}
	public String getEstmt_no() {
		return estmt_no;
	}
	public void setEstmt_no(String estmt_no) {
		this.estmt_no = estmt_no;
	}
	public int getEvent_list_cnt() {
		return event_list_cnt;
	}
	public void setEvent_list_cnt(int event_list_cnt) {
		this.event_list_cnt = event_list_cnt;
	}
	public int getEstimate_list_cnt() {
		return estimate_list_cnt;
	}
	public void setEstimate_list_cnt(int estimate_list_cnt) {
		this.estimate_list_cnt = estimate_list_cnt;
	}
	public String getNation_nm() {
		return nation_nm;
	}
	public void setNation_nm(String nation_nm) {
		this.nation_nm = nation_nm;
	}	
	public String getLawfirm_id() {
		return lawfirm_id;
	}
	public void setLawfirm_id(String lawfirm_id) {
		this.lawfirm_id = lawfirm_id;
	}
	public String getFst_event_no() {
		return fst_event_no;
	}
	public void setFst_event_no(String fst_event_no) {
		this.fst_event_no = fst_event_no;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getLawfirm_nm() {
		return lawfirm_nm;
	}
	public void setLawfirm_nm(String lawfirm_nm) {
		this.lawfirm_nm = lawfirm_nm;
	}
	public String getFst_cntrtday() {
		return fst_cntrtday;
	}
	public void setFst_cntrtday(String fst_cntrtday) {
		this.fst_cntrtday = fst_cntrtday;
	}
	public String getRprsnt_tel() {
		return rprsnt_tel;
	}
	public void setRprsnt_tel(String rprsnt_tel) {
		this.rprsnt_tel = rprsnt_tel;
	}
	public String getRprsnt_fax() {
		return rprsnt_fax;
	}
	public void setRprsnt_fax(String rprsnt_fax) {
		this.rprsnt_fax = rprsnt_fax;
	}
	public String getRprsnt_email() {
		return rprsnt_email;
	}
	public void setRprsnt_email(String rprsnt_email) {
		this.rprsnt_email = rprsnt_email;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getState_gbn() {
		return state_gbn;
	}
	public void setState_gbn(String state_gbn) {
		this.state_gbn = state_gbn;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getMainftre_estmt() {
		return mainftre_estmt;
	}
	public void setMainftre_estmt(String mainftre_estmt) {
		this.mainftre_estmt = mainftre_estmt;
	}
	public String getBank_addr() {
		return bank_addr;
	}
	public void setBank_addr(String bank_addr) {
		this.bank_addr = bank_addr;
	}
	public String getBank_nm() {
		return bank_nm;
	}
	public void setBank_nm(String bank_nm) {
		this.bank_nm = bank_nm;
	}
	public String getAccnt_no() {
		return accnt_no;
	}
	public void setAccnt_no(String accnt_no) {
		this.accnt_no = accnt_no;
	}
	public String getBank_note() {
		return bank_note;
	}
	public void setBank_note(String bank_note) {
		this.bank_note = bank_note;
	}
	public String getLog_ymd() {
		return log_ymd;
	}
	public void setLog_ymd(String log_ymd) {
		this.log_ymd = log_ymd;
	}
	public String getFst_event_nm() {
		return fst_event_nm;
	}
	public void setFst_event_nm(String fst_event_nm) {
		this.fst_event_nm = fst_event_nm;
	}	
}
