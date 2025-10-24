package com.sds.secframework.singleIF.dto;

import model.outldap.samsung.net.Organization;

public class OrganizationVO extends Organization {

	/**********************************************************************
	 * 공통내역
	 **********************************************************************/
	/** 메뉴아이디 */
	private String menu_id;    
	/** 페이지아이디 */
	private String page_id;    
	/** 사용자아이디 */
	private String user_id;    
	/** 검색여부 */
	private String doSearch;    
	/**게시검색타입 */
	private String srch_dept_cntnt_type;               
	/**게시검색내용 */
	private String srch_dept_cntnt;                    
	
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getPage_id() {
		return page_id;
	}
	public void setPage_id(String page_id) {
		this.page_id = page_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getDoSearch() {
		return doSearch;
	}
	public void setDoSearch(String doSearch) {
		this.doSearch = doSearch;
	}
	public String getSrch_dept_cntnt_type() {
		return srch_dept_cntnt_type;
	}
	public void setSrch_dept_cntnt_type(String srch_dept_cntnt_type) {
		this.srch_dept_cntnt_type = srch_dept_cntnt_type;
	}
	public String getSrch_dept_cntnt() {
		return srch_dept_cntnt;
	}
	public void setSrch_dept_cntnt(String srch_dept_cntnt) {
		this.srch_dept_cntnt = srch_dept_cntnt;
	}

}
