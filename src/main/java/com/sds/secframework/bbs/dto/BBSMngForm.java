package com.sds.secframework.bbs.dto;

import com.sds.secframework.common.dto.CommonForm;

/**
 * 게시판관리 Value Object
 *  
 * @version V1.0
 * 작성일 : 2009/12/18
 * @author 금현서, ace4u_khs@samsung.com
 */

public class BBSMngForm extends CommonForm {

	/**********************************************************************
	 * 게시판 관리내역
	 **********************************************************************/
	/**게시판아이디 */
	private String bbs_id;             
	/**게시판명 */
	private String bbs_nm;             
	/**게시판영문명 */
	private String bbs_nm_eng;         
	/**게시판설명 */
	private String comments;           
	/**새글게시물유지일자 */
	private int    new_hold_term;      
	/**댓글여부 */
	private String reply_yn;           
	/**덧글여부 */
	private String append_yn;          
	/**첨부파일여부 */
	private String file_yn;            
	/**긴급여부 */
	private String urgency_yn;         
	/**추천여부 */
	private String recommend_yn;       
	/**게시기간설정여부 */
	private String hold_term_yn;       
	/**사용여부 */
	private String use_yn;             
	/**등록자아이디 */
	private String reg_id;             
	/**등록일시 */
	private String reg_dt;             
	/**수정자아이디 */
	private String mod_id;             
	/**수정일시 */
	private String mod_dt;             
	
	/**********************************************************************
	 * 검색조건
	 **********************************************************************/
	/**게시판명 */
	private String srch_bbs_nm;        
	/**사용여무 */
	private String srch_use_yn;        
	
	public String getBbs_id() {
		return bbs_id;
	}
	public void setBbs_id(String bbs_id) {
		this.bbs_id = bbs_id;
	}
	public String getBbs_nm() {
		return bbs_nm;
	}
	public void setBbs_nm(String bbs_nm) {
		this.bbs_nm = bbs_nm;
	}
	public String getBbs_nm_eng() {
		return bbs_nm_eng;
	}
	public void setBbs_nm_eng(String bbs_nm_eng) {
		this.bbs_nm_eng = bbs_nm_eng;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getNew_hold_term() {
		return new_hold_term;
	}
	public void setNew_hold_term(int new_hold_term) {
		this.new_hold_term = new_hold_term;
	}	
	public String getReply_yn() {
		return reply_yn;
	}
	public void setReply_yn(String reply_yn) {
		this.reply_yn = reply_yn;
	}
	public String getAppend_yn() {
		return append_yn;
	}
	public void setAppend_yn(String append_yn) {
		this.append_yn = append_yn;
	}
	public String getFile_yn() {
		return file_yn;
	}
	public void setFile_yn(String file_yn) {
		this.file_yn = file_yn;
	}
	public String getUrgency_yn() {
		return urgency_yn;
	}
	public void setUrgency_yn(String urgency_yn) {
		this.urgency_yn = urgency_yn;
	}
	public String getRecommend_yn() {
		return recommend_yn;
	}
	public void setRecommend_yn(String recommend_yn) {
		this.recommend_yn = recommend_yn;
	}
	public String getHold_term_yn() {
		return hold_term_yn;
	}
	public void setHold_term_yn(String hold_term_yn) {
		this.hold_term_yn = hold_term_yn;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public String getReg_id() {
		return reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public String getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}
	public String getMod_id() {
		return mod_id;
	}
	public void setMod_id(String mod_id) {
		this.mod_id = mod_id;
	}
	public String getMod_dt() {
		return mod_dt;
	}
	public void setMod_dt(String mod_dt) {
		this.mod_dt = mod_dt;
	}
	public String getSrch_bbs_nm() {
		return srch_bbs_nm;
	}
	public void setSrch_bbs_nm(String srch_bbs_nm) {
		this.srch_bbs_nm = srch_bbs_nm;
	}
	public String getSrch_use_yn() {
		return srch_use_yn;
	}
	public void setSrch_use_yn(String srch_use_yn) {
		this.srch_use_yn = srch_use_yn;
	}
}
