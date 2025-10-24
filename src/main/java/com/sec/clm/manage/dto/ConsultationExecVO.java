/**
* Project Name : 계약관리시스템
* File Name :ConsultationExecVO.java
* Description : 체결품의이행정보 VO
* Author : 심주완
* Date : 2011.09.16
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.manage.dto;

import java.math.BigDecimal;

import com.sds.secframework.common.dto.CommonVO;

public class ConsultationExecVO extends CommonVO {
	
	/**********************************************************************
	 * DB 변수-대상테이블(TN_CLM_CONT_EXECINFO); 
	 **********************************************************************/
	private String 	cntrt_id;				//계약아이디
	private int 	exec_seqno;				//이행일련번호
	private String 	exec_cont;				//이행내용
	private String 	exec_plndday;			//이행예정일
	private String 	exec_status;			//이행상태
	private String 	del_yn;					//삭제여부
	
	//다중입출력관련 
	private String 	cntrt_id_arr[];			//계약아이디
	private int 	exec_seqno_arr[];		//이행일련번호
	private String 	exec_cont_arr[];		//이행내용
	private String 	exec_plndday_arr[];		//이행예정일
	private String 	exec_status_arr[];		//이행상태
	private String 	del_yn_arr[];			//삭제여부
	
	
	
	/*=========setter & getter===========*/
	//계약아이디
	public String getCntrt_id() {
		return cntrt_id;
	}
	public void setCntrt_id(String cntrt_id) {
		this.cntrt_id = cntrt_id;
	}
	//이행일련번호
	public int getExec_seqno() {
		return exec_seqno;
	}
	public void setExec_seqno(int exec_seqno) {
		this.exec_seqno = exec_seqno;
	}
	//이행내용
	public String getExec_cont() {
		return exec_cont;
	}
	public void setExec_cont(String exec_cont) {
		this.exec_cont = exec_cont;
	}
	//이행예정일
	public String getExec_plndday() {
		return exec_plndday;
	}
	public void setExec_plndday(String exec_plndday) {
		this.exec_plndday = exec_plndday;
	}
	//이행상태
	public String getExec_status() {
		return exec_status;
	}
	public void setExec_status(String exec_status) {
		this.exec_status = exec_status;
	}
	//삭제여부
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	
	//다중입출력관련 setter & getter
	//계약아이디
	public String[] getCntrt_id_arr() {
		return cntrt_id_arr;
	}
	public void setCntrt_id_arr(String[] cntrt_id_arr) {
		this.cntrt_id_arr = cntrt_id_arr;
	}
	//이행일련번호
	public int[] getExec_seqno_arr() {
		return exec_seqno_arr;
	}
	public void setExec_seqno_arr(int[] exec_seqno_arr) {
		this.exec_seqno_arr = exec_seqno_arr;
	}
	//이행내용
	public String[] getExec_cont_arr() {
		return exec_cont_arr;
	}
	public void setExec_cont_arr(String[] exec_cont_arr) {
		this.exec_cont_arr = exec_cont_arr;
	}
	//이행예정일
	public String[] getExec_plndday_arr() {
		return exec_plndday_arr;
	}
	public void setExec_plndday_arr(String[] exec_plndday_arr) {
		this.exec_plndday_arr = exec_plndday_arr;
	}
	//이행상태
	public String[] getExec_status_arr() {
		return exec_status_arr;
	}
	public void setExec_status(String[] exec_status_arr) {
		this.exec_status_arr = exec_status_arr;
	}
	//삭제여부
	public String[] getDel_yn_arr() {
		return del_yn_arr;
	}
	public void setDel_yn_arr(String[] del_yn_arr) {
		this.del_yn_arr = del_yn_arr;
	}
	
	
	/** 이행구분 GRP_CD C055 : C05501-지급조건상세, C05502-산출물 */
	private String exec_gbn;
	/** 이행항목  */
	private String exec_itm;
	/** 금액  */
	private BigDecimal exec_amt;
	/** 완료일  */
	private String exec_cmpltday;
	/** 비고 */
	private String note;
	
	
	public String getExec_gbn() {
		return exec_gbn;
	}
	public void setExec_gbn(String exec_gbn) {
		this.exec_gbn = exec_gbn;
	}
	public String getExec_itm() {
		return exec_itm;
	}
	public void setExec_itm(String exec_itm) {
		this.exec_itm = exec_itm;
	}
	public BigDecimal getExec_amt() {
		return exec_amt;
	}
	public void setExec_amt(BigDecimal exec_amt) {
		this.exec_amt = exec_amt;
	}
	public String getExec_cmpltday() {
		return exec_cmpltday;
	}
	public void setExec_cmpltday(String exec_cmpltday) {
		this.exec_cmpltday = exec_cmpltday;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}


	private int exec_chk_arr[];
	/** 이행구분 GRP_CD C055 : C05501-지급조건상세, C05502-산출물 */
	private String exec_gbn_arr[];
	/** 이행항목  */
	private String exec_itm_arr[];
	/** 금액  */
	private String exec_amt_arr[];
	/** 완료일  */
	private String exec_cmpltday_arr[];
	/** 비고 */
	private String note_arr[];



	public int[] getExec_chk_arr() {
		return exec_chk_arr;
	}
	public void setExec_chk_arr(int[] exec_chk_arr) {
		this.exec_chk_arr = exec_chk_arr;
	}
	public String[] getExec_gbn_arr() {
		return exec_gbn_arr;
	}
	public void setExec_gbn_arr(String[] exec_gbn_arr) {
		this.exec_gbn_arr = exec_gbn_arr;
	}
	public String[] getExec_itm_arr() {
		return exec_itm_arr;
	}
	public void setExec_itm_arr(String[] exec_itm_arr) {
		this.exec_itm_arr = exec_itm_arr;
	}
	public String[] getExec_amt_arr() {
		return exec_amt_arr;
	}
	public void setExec_amt_arr(String[] exec_amt_arr) {
		this.exec_amt_arr = exec_amt_arr;
	}
	public String[] getExec_cmpltday_arr() {
		return exec_cmpltday_arr;
	}
	public void setExec_cmpltday_arr(String[] exec_cmpltday_arr) {
		this.exec_cmpltday_arr = exec_cmpltday_arr;
	}
	public String[] getNote_arr() {
		return note_arr;
	}
	public void setNote_arr(String[] note_arr) {
		this.note_arr = note_arr;
	}
	public void setExec_status_arr(String[] exec_status_arr) {
		this.exec_status_arr = exec_status_arr;
	}
	
	
	
	
}