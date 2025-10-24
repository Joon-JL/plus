/**
* Project Name : 계약관리시스템
* File Name : QnaForm.java
* Description : Q&A VO
* Author : Kim hyun goo
* Date : 2010. 11. 24
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.counsel.dto;

import com.sds.secframework.common.dto.CommonVO;
import com.sds.secframework.common.util.DateUtil;

public class QnaVO extends CommonVO{
	
	
	/*******************************************
	 Search 
	 *******************************************/
	private String srch_start_dt;     //게시일 start
	private String srch_end_dt;       //게시일 end
	private String srch_prgrs_status; //진행상태
	private String srch_title;        //제목
	private String srch_select_title; //제목 검색 구분
	private String replyGu;

	
	/*******************************************
	 DB Column 
	 *******************************************/
	private int seqno;				//일련번호			Not null
	private int up_seqno;			//상위_일련번호		Not null
	private int postup_srt;			//게시_순서			Not null
	private int postup_depth;		//게시_단계			Not null
	private String bbs_knd;			//게시판_종류					(CLM, GRP_CD : C014)
	private String cont_type;		//내용_유형						(CLM, GRP_CD : C058)
	private String title;			//제목				Not null
	private String cont;			//내용
	private int rdcnt;				//조회수			Not null
	private String prgrs_status;	//진행_상태			Not null	(CLM, GRP_CD : C017)
	private String trans_dt;		//이관_일시
	private String trans_demnd_cont;//이관_요청_내용
	private String pub_yn;			//공개_여부			Not null
	private String reg_operdiv;		//등록_사업부
	private String reg_dt;			//등록_일시			Not null
	private String reg_id;			//등록자_ID			Not null
	private String reg_nm;			//등록자_명
	private String reg_dept;		//등록_부서
	private String reg_dept_nm;		//등록_부서_명
	private String regman_jikgup_nm;//등록자_직급
	private String mod_dt;			//수정_일시
	private String mod_id;			//수정자_ID
	private String mod_nm;			//수정자_명
	private String del_yn;			//삭제_여부			Not null
	private String del_dt;			//삭제_일시
	private String del_id;			//삭제자_ID
	private String del_nm;			//삭제자_명

	
	/*******************************************
	 etc 
	 *******************************************/
	private String top_role;
	private String bbs_knd_nm;		//게시판_종류_이름
	private String prgrs_status_nm;	//진행_상태_이름
	private String returnMessage;	//리턴메시지	
	
	/* default 값 세팅 */
	public QnaVO() throws Exception {
		srch_start_dt = DateUtil.formatDate(DateUtil.addDays(DateUtil.today(), -365), "-");
		srch_end_dt = DateUtil.formatDate(DateUtil.today(), "-");
		
		up_seqno = 0;
		postup_srt = 0;
		postup_depth = 0;
		rdcnt = 0;
		pub_yn = "Y";
		del_yn = "N";
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

	public String getSrch_prgrs_status() {
		return srch_prgrs_status;
	}

	public void setSrch_prgrs_status(String srch_prgrs_status) {
		this.srch_prgrs_status = srch_prgrs_status;
	}

	public String getSrch_title() {
		return srch_title;
	}

	public void setSrch_title(String srch_title) {
		this.srch_title = srch_title;
	}

	public String getSrch_select_title() {
		return srch_select_title;
	}

	public void setSrch_select_title(String srch_select_title) {
		this.srch_select_title = srch_select_title;
	}

	public String getReplyGu() {
		return replyGu;
	}

	public void setReplyGu(String replyGu) {
		this.replyGu = replyGu;
	}

	public int getSeqno() {
		return seqno;
	}

	public void setSeqno(int seqno) {
		this.seqno = seqno;
	}

	public int getUp_seqno() {
		return up_seqno;
	}

	public void setUp_seqno(int up_seqno) {
		this.up_seqno = up_seqno;
	}

	public int getPostup_srt() {
		return postup_srt;
	}

	public void setPostup_srt(int postup_srt) {
		this.postup_srt = postup_srt;
	}

	public int getPostup_depth() {
		return postup_depth;
	}

	public void setPostup_depth(int postup_depth) {
		this.postup_depth = postup_depth;
	}

	public String getBbs_knd() {
		return bbs_knd;
	}

	public void setBbs_knd(String bbs_knd) {
		this.bbs_knd = bbs_knd;
	}

	public String getCont_type() {
		return cont_type;
	}

	public void setCont_type(String cont_type) {
		this.cont_type = cont_type;
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

	public int getRdcnt() {
		return rdcnt;
	}

	public void setRdcnt(int rdcnt) {
		this.rdcnt = rdcnt;
	}

	public String getPrgrs_status() {
		return prgrs_status;
	}

	public void setPrgrs_status(String prgrs_status) {
		this.prgrs_status = prgrs_status;
	}

	public String getTrans_dt() {
		return trans_dt;
	}

	public void setTrans_dt(String trans_dt) {
		this.trans_dt = trans_dt;
	}

	public String getTrans_demnd_cont() {
		return trans_demnd_cont;
	}

	public void setTrans_demnd_cont(String trans_demnd_cont) {
		this.trans_demnd_cont = trans_demnd_cont;
	}

	public String getPub_yn() {
		return pub_yn;
	}

	public void setPub_yn(String pub_yn) {
		this.pub_yn = pub_yn;
	}

	public String getReg_operdiv() {
		return reg_operdiv;
	}

	public void setReg_operdiv(String reg_operdiv) {
		this.reg_operdiv = reg_operdiv;
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

	public String getReg_dept() {
		return reg_dept;
	}

	public void setReg_dept(String reg_dept) {
		this.reg_dept = reg_dept;
	}

	public String getReg_dept_nm() {
		return reg_dept_nm;
	}

	public void setReg_dept_nm(String reg_dept_nm) {
		this.reg_dept_nm = reg_dept_nm;
	}

	public String getMod_dt() {
		return mod_dt;
	}

	public void setMod_dt(String mod_dt) {
		this.mod_dt = mod_dt;
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

	public String getTop_role() {
		return top_role;
	}

	public void setTop_role(String top_role) {
		this.top_role = top_role;
	}

	public String getBbs_knd_nm() {
		return bbs_knd_nm;
	}

	public void setBbs_knd_nm(String bbs_knd_nm) {
		this.bbs_knd_nm = bbs_knd_nm;
	}

	public String getPrgrs_status_nm() {
		return prgrs_status_nm;
	}

	public void setPrgrs_status_nm(String prgrs_status_nm) {
		this.prgrs_status_nm = prgrs_status_nm;
	}

	public String getRegman_jikgup_nm() {
		return regman_jikgup_nm;
	}

	public void setRegman_jikgup_nm(String regman_jikgup_nm) {
		this.regman_jikgup_nm = regman_jikgup_nm;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}
	
}
