package com.sds.secframework.menu.dto;

import com.sds.secframework.common.dto.CommonForm;

/**
 * 페이지관리 Value Object
 *  
 * @version V1.0
 * 작성일 : 2009/12/18
 * @author 금현서, ace4u_khs@samsung.com
 */

public class SiteMapForm extends CommonForm {

	/**페이지아이디 */
	private String page_id;         
	/**페이지명 */
	private String page_nm;         
	/**페이지URL */
	private String page_url;        
	/**사용여부 */
	private String use_yn;          
	/**권한체크여부 */
	private String authcheck_yn;    
	/**개발자성명 */
	private String developer_nm;    
	/**설명 */
	private String comments;        
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
	/**검색 페이지명 */
	private String srch_page_nm;    
	/**검색사용여부 */
	private String srch_use_yn;     

    /**********************************************************************
	* 페이지삭제
	**********************************************************************/
	/**메뉴삭제리스트 */
	private String[] chk_id;     

    /**********************************************************************
	* 페이지데이타(입력용)
	**********************************************************************/
	/**시스템코드 */
	private String[] sys_cds;    
	/**페이지아이디 */
	private String[] page_ids;   
	/**페이지명 */
	private String[] page_nms;   
	/**페이지URL */
	private String[] page_urls;  
	/**사용여부 */
	private String[] use_yns;    
	/**권한체크여부 */
	private String[] authcheck_yns; 
	/**개발자성명 */
	private String[] developer_nms; 
	/**설명 */
	private String[] commentss;     
	/**등록자아이디 */
	private String[] reg_ids;       
	/**등록일시 */
	private String[] reg_dts;       
	/**수정자아이디 */
	private String[] mod_ids;       
	/**수정일시 */
	private String[] mod_dts;       

	public String getPage_id() {
		return page_id;
	}
	public void setPage_id(String page_id) {
		this.page_id = page_id;
	}
	public String getPage_nm() {
		return page_nm;
	}
	public void setPage_nm(String page_nm) {
		this.page_nm = page_nm;
	}
	public String getPage_url() {
		return page_url;
	}
	public void setPage_url(String page_url) {
		this.page_url = page_url;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public String getAuthcheck_yn() {
		return authcheck_yn;
	}
	public void setAuthcheck_yn(String authcheck_yn) {
		this.authcheck_yn = authcheck_yn;
	}
	public String getDeveloper_nm() {
		return developer_nm;
	}
	public void setDeveloper_nm(String developer_nm) {
		this.developer_nm = developer_nm;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
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
	public String getSrch_page_nm() {
		return srch_page_nm;
	}
	public void setSrch_page_nm(String srch_page_nm) {
		this.srch_page_nm = srch_page_nm;
	}
	public String getSrch_use_yn() {
		return srch_use_yn;
	}
	public void setSrch_use_yn(String srch_use_yn) {
		this.srch_use_yn = srch_use_yn;
	}
	public String[] getChk_id() {
		return chk_id;
	}
	public void setChk_id(String[] chk_id) {
		this.chk_id = chk_id;
	}
	public String[] getSys_cds() {
		return sys_cds;
	}
	public void setSys_cds(String[] sys_cds) {
		this.sys_cds = sys_cds;
	}
	public String[] getPage_ids() {
		return page_ids;
	}
	public void setPage_ids(String[] page_ids) {
		this.page_ids = page_ids;
	}
	public String[] getPage_nms() {
		return page_nms;
	}
	public void setPage_nms(String[] page_nms) {
		this.page_nms = page_nms;
	}
	public String[] getPage_urls() {
		return page_urls;
	}
	public void setPage_urls(String[] page_urls) {
		this.page_urls = page_urls;
	}
	public String[] getUse_yns() {
		return use_yns;
	}
	public void setUse_yns(String[] use_yns) {
		this.use_yns = use_yns;
	}
	public String[] getAuthcheck_yns() {
		return authcheck_yns;
	}
	public void setAuthcheck_yns(String[] authcheck_yns) {
		this.authcheck_yns = authcheck_yns;
	}
	public String[] getDeveloper_nms() {
		return developer_nms;
	}
	public void setDeveloper_nms(String[] developer_nms) {
		this.developer_nms = developer_nms;
	}
	public String[] getCommentss() {
		return commentss;
	}
	public void setCommentss(String[] commentss) {
		this.commentss = commentss;
	}
	public String[] getReg_ids() {
		return reg_ids;
	}
	public void setReg_ids(String[] reg_ids) {
		this.reg_ids = reg_ids;
	}
	public String[] getReg_dts() {
		return reg_dts;
	}
	public void setReg_dts(String[] reg_dts) {
		this.reg_dts = reg_dts;
	}
	public String[] getMod_ids() {
		return mod_ids;
	}
	public void setMod_ids(String[] mod_ids) {
		this.mod_ids = mod_ids;
	}
	public String[] getMod_dts() {
		return mod_dts;
	}
	public void setMod_dts(String[] mod_dts) {
		this.mod_dts = mod_dts;
	}

}
