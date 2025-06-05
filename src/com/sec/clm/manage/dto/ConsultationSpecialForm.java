/**
* Project Name : 계약관리시스템
* File Name :ConsultationSpecialVO.java
* Description : 체결품의특화이력 Form
* Author : 심주완
* Date : 2011.09.08
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.manage.dto;

import com.sds.secframework.common.dto.CommonForm;

public class ConsultationSpecialForm extends CommonForm {
	
	/**********************************************************************
	 * DB 변수-대상테이블(TN_CLM_CONT_TYPE_SPCLINFO); 
	 **********************************************************************/
	private String cntrt_id;		//계약아이디
	private int attr_seqno;			//속성일련번호
	private String attr_cd;			//속성코드
	private String attr_cont;		//속성내용
	
	
	
	//다중입출력관련 
	private String cntrt_id_arr[];
	private int    attr_seqno_arr[];
	private String attr_cd_arr[];
	private String attr_cont_arr[];
	
	
	
	/**********************************************************************
	 * 페이지 변수 
	 **********************************************************************/
	private String crtn_depth;		//특화생성단계구분
	private int    td_cnt;			//상단TD갯수
	private String colspan_string;
	private String attr_worktype;
	private String attr_worktype_arr[];
	
	//setter
	public void setCntrt_id(String cntrt_id) {
		this.cntrt_id = cntrt_id;
	}
	public void setAttr_seqno(int attr_seqno) {
		this.attr_seqno = attr_seqno;
	}
	public void setAttr_cd(String attr_cd) {
		this.attr_cd = attr_cd;
	}
	public void setAttr_cont(String attr_cont) {
		this.attr_cont = attr_cont;
	}
	public void setCrtn_depth(String crtn_depth) {
		this.crtn_depth = crtn_depth;
	}
	public void setTd_cnt(int td_cnt) {
		this.td_cnt = td_cnt;
	}
	public void setColspan_string(String colspan_string) {
		this.colspan_string = colspan_string;
	}
	public void setAttr_worktype(String attr_worktype) {
		this.attr_worktype = attr_worktype;
	}
	public void setCntrt_id_arr(String[] cntrt_id_arr) {
		this.cntrt_id_arr = cntrt_id_arr;
	}
	public void setAttr_seqno_arr(int[] attr_seqno_arr) {
		this.attr_seqno_arr = attr_seqno_arr;
	}
	public void setAttr_cd_arr(String[] attr_cd_arr) {
		this.attr_cd_arr = attr_cd_arr;
	}
	public void setAttr_cont_arr(String[] attr_cont_arr) {
		this.attr_cont_arr = attr_cont_arr;
	}
	public void setAttr_worktype_arr(String[] attr_worktype_arr){
		this.attr_worktype_arr = attr_worktype_arr;
	}
	//getter
	public String getCntrt_id() {
		return cntrt_id;
	}
	public int getAttr_seqno() {
		return attr_seqno;
	}	
	public String getAttr_cd() {
		return attr_cd;
	}	
	public String getAttr_cont() {
		return attr_cont;
	}
	public String getCrtn_depth() {
		return crtn_depth;
	}
	public int getTd_cnt() {
		return td_cnt;
	}
	public String getColspan_string() {
		return colspan_string;
	}
	public String getAttr_worktype(){
		return attr_worktype;
	}
	public String[] getCntrt_id_arr() {
		return cntrt_id_arr;
	}
	public int[] getAttr_seqno_arr() {
		return attr_seqno_arr;
	}
	public String[] getAttr_cd_arr() {
		return attr_cd_arr;
	}
	public String[] getAttr_cont_arr() {
		return attr_cont_arr;
	}
	public String[] getAttr_worktype_arr() {
		return attr_worktype_arr;
	}
}