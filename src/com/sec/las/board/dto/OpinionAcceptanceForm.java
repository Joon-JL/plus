package com.sec.las.board.dto;

import com.sds.secframework.common.dto.CommonForm;

public class OpinionAcceptanceForm extends CommonForm {	

	 // 검색조건 ///////////////////
	private String srch_combo;
	private String srch_word;
	private String srch_start_dt;
	private String srch_end_dt;
	
	// 등록 필드 ///////////////////
	private String seqno;
	private String insert_kbn;
	private String title;
	private String cont;
	private String expire_date;
	private String part_member_cnt;	
	private String re_seqno;
	
	 // Participant ///////////////////
	private int userSeqno;
	private String userEpid;
	private String userSingleId;
	private String userEmail;
	private String userName;
	private String userDeptNm;
	private String userJikupNm;
	private String userCompNm;
	private String userTelNo;
	private String userMobile;

	// 페이징 ///////////////////
	private int totalCnt;
	
	public String getRe_seqno() {
		return re_seqno;
	}

	public void setRe_seqno(String re_seqno) {
		this.re_seqno = re_seqno;
	}

	public String getSrch_combo() {
		return srch_combo;
	}

	public void setSrch_combo(String srch_combo) {
		this.srch_combo = srch_combo;
	}

	public String getSrch_word() {
		return srch_word;
	}

	public void setSrch_word(String srch_word) {
		this.srch_word = srch_word;
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

	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}

	public String getInsert_kbn() {
		return insert_kbn;
	}

	public void setInsert_kbn(String insert_kbn) {
		this.insert_kbn = insert_kbn;
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

	public String getExpire_date() {
		return expire_date;
	}

	public void setExpire_date(String expire_date) {
		this.expire_date = expire_date;
	}

	public String getPart_member_cnt() {
		return part_member_cnt;
	}

	public void setPart_member_cnt(String part_member_cnt) {
		this.part_member_cnt = part_member_cnt;
	}

	public int getUserSeqno() {
		return userSeqno;
	}

	public void setUserSeqno(int userSeqno) {
		this.userSeqno = userSeqno;
	}

	public String getUserEpid() {
		return userEpid;
	}

	public void setUserEpid(String userEpid) {
		this.userEpid = userEpid;
	}

	public String getUserSingleId() {
		return userSingleId;
	}

	public void setUserSingleId(String userSingleId) {
		this.userSingleId = userSingleId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserDeptNm() {
		return userDeptNm;
	}

	public void setUserDeptNm(String userDeptNm) {
		this.userDeptNm = userDeptNm;
	}

	public String getUserJikupNm() {
		return userJikupNm;
	}

	public void setUserJikupNm(String userJikupNm) {
		this.userJikupNm = userJikupNm;
	}

	public String getUserCompNm() {
		return userCompNm;
	}

	public void setUserCompNm(String userCompNm) {
		this.userCompNm = userCompNm;
	}

	public String getUserTelNo() {
		return userTelNo;
	}

	public void setUserTelNo(String userTelNo) {
		this.userTelNo = userTelNo;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}     
	
	
	
	
}