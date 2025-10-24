/**
* Project Name : 계약관리시스템
* File Name : LawTermsVo.java
* Description : 계약용어집목록 Vo
* Author : 유영섭
* Date : 2011.09.05
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.share.dto;

import com.sds.secframework.common.dto.CommonVO;

public class LawTermsVO extends CommonVO {
	
	/**********************************************
	 * 게시판설정(게시판명, 권한)
	 **********************************************/
	/** 게시판 등록 가능 여부 */
	private boolean auth_insert;
	/** 게시판 수정 가능 여부 */
	private boolean auth_modify;
	/** 게시판 삭제 가능 여부 */
	private boolean auth_delete;
	
	/**********************************************************************
	 * DB 변수
	 **********************************************************************/
	   /**일렬 번호 */ 
		private int seqno;
		/**용어 명 */ 
		private String terms_nm;
		/**용어 약어 명 */ 
		private String terms_abbr_nm;
		/**용어 영문 명 */ 
		private String terms_eng_nm;
		/**용어 영문 약어명 */ 	
		private String terms_eng_abbr_nm;
		/**용어_설명*/ 
		private String terms_expl;
		/**조회수 */ 
		private int rdcnt;
		/**등록일시 */ 
		private String reg_dt;
		/**등록자ID */ 
		private String reg_id;
		/**등록자명 */ 
		private String reg_nm;
		/**수정일시*/ 
		private String mod_dt;
		/**수정자ID*/ 
		private String mod_id;
		/**수정자명 */ 
		private String mod_nm;
		/**삭제여부 */ 
		private String del_yn;
		/**일렬 일시 */ 
		private String del_dt;
		/**삭제자 ID */ 
		private String del_id;
		/**삭제자 명 */ 
		private String del_nm;
		
		/**나모웹에디터 타입 - 내용에 사용 */
		private String body_mime;

	
	/**********************************************************************
	 * DB 외 변수
	 **********************************************************************/
	private String srch_keyword; 
	
	private String srch_keyword_content;
	
	/**********************************************************************
	 * Getters and Setters Method
	 **********************************************************************/
	
	
	public void setSeqno(int seqno) {
		this.seqno = seqno;
	}
	public String getSrch_keyword() {
		return srch_keyword;
	}
	public void setSrch_keyword(String srch_keyword) {
		this.srch_keyword = srch_keyword;
	}
	public String getSrch_keyword_content() {
		return srch_keyword_content;
	}
	public void setSrch_keyword_content(String srch_keyword_content) {
		this.srch_keyword_content = srch_keyword_content;
	}
	public void setTerms_nm(String terms_nm) {
		this.terms_nm = terms_nm;
	}
	public void setTerms_abbr_nm(String terms_abbr_nm) {
		this.terms_abbr_nm = terms_abbr_nm;
	}
	public void setTerms_eng_nm(String terms_eng_nm) {
		this.terms_eng_nm = terms_eng_nm;
	}
	public void setTerms_eng_abbr_nm(String terms_eng_abbr_nm) {
		this.terms_eng_abbr_nm = terms_eng_abbr_nm;
	}
	public void setTerms_expl(String terms_expl) {
		this.terms_expl = terms_expl;
	}
	public void setRdcnt(int rdcnt) {
		this.rdcnt = rdcnt;
	}
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public void setReg_nm(String reg_nm) {
		this.reg_nm = reg_nm;
	}
	public void setMod_dt(String mod_dt) {
		this.mod_dt = mod_dt;
	}
	public void setMod_id(String mod_id) {
		this.mod_id = mod_id;
	}
	public void setMod_nm(String mod_nm) {
		this.mod_nm = mod_nm;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	public void setDel_dt(String del_dt) {
		this.del_dt = del_dt;
	}
	public void setDel_id(String del_id) {
		this.del_id = del_id;
	}
	public void setDel_nm(String del_nm) {
		this.del_nm = del_nm;
	}
	public int getSeqno() {
		return this.seqno;
	}
	public String getTerms_nm() {
		return this.terms_nm;
	}
	public String getTerms_abbr_nm() {
		return this.terms_abbr_nm;
	}
	public String getTerms_eng_nm() {
		return this.terms_eng_nm;
	}
	public String getTerms_eng_abbr_nm() {
		return this.terms_eng_abbr_nm;
	}
	public String getTerms_expl() {
		return this.terms_expl;
	}
	public int getRdcnt() {
		return this.rdcnt;
	}
	public String getReg_dt() {
		return this.reg_dt;
	}
	public String getReg_id() {
		return this.reg_id;
	}
	public String getReg_nm() {
		return this.reg_nm;
	}
	public String getMod_dt() {
		return this.mod_dt;
	}
	public String getMod_id() {
		return this.mod_id;
	}
	public String getMod_nm() {
		return this.mod_nm;
	}
	public String getDel_yn() {
		return this.del_yn;
	}
	public String getDel_dt() {
		return this.del_dt;
	}
	public String getDel_id() {
		return this.del_id;
	}
	public String getDel_nm() {
		return this.del_nm;
	}
	
	public String getBody_mime() {
		return body_mime;
	}
	public void setBody_mime(String body_mime) {
		this.body_mime = body_mime;
	}
	public boolean isAuth_insert() {
		return auth_insert;
	}
	public void setAuth_insert(boolean auth_insert) {
		this.auth_insert = auth_insert;
	}
	public boolean isAuth_modify() {
		return auth_modify;
	}
	public void setAuth_modify(boolean auth_modify) {
		this.auth_modify = auth_modify;
	}
	public boolean isAuth_delete() {
		return auth_delete;
	}
	public void setAuth_delete(boolean auth_delete) {
		this.auth_delete = auth_delete;
	}
	
	
}