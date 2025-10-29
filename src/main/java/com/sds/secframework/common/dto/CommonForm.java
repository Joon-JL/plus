package com.sds.secframework.common.dto;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.sds.secframework.common.util.ObjectCopyUtil;

/**
 * 공통 Value Object
 *  
 * @version V1.0
 * 작성일 : 2009/12/18
 * @author 금현서, ace4u_khs@samsung.com
 */

public class CommonForm {

	/**********************************************************************
	 * 공통내역
	 **********************************************************************/
	/** 시스템코드 */
	protected String sys_cd;    
	/** 사용자아이디 */
	protected String user_id;  
	/** 단순 forward_url */
	protected String forward_url; 
	
	/** 세션-사용자아이디 */
	protected String session_user_id;   
	/** 세션-사용자명 */
	protected String session_user_nm;   
	/** 세션-사용자부서코드 */
	protected String session_dept_cd;   
	/** 세션-사용자부서명 */
	protected String session_dept_nm;   
	/** 세션-담당사업부코드 */
	protected String session_clms_user_orgnz;
	/** 세션-담당사업부코드_법무 */
	protected String session_resp_operdiv;
	/** 세션-사용자회사코드 */
	protected String session_comp_cd;   
	/** 세션-사용자회사명 */
	protected String session_comp_nm;   
	/** 세션 사용자 직급코드 */
	protected String session_jikgup_cd;
	/** 세션 사용자 직급명 */
	protected String session_jikgup_nm;
	/** 사용자-Locale */
	protected String session_user_locale;
	/** 사용자 Roles */
	protected ArrayList session_user_role_cds;
	/** 사용자 email */
	protected String session_email;
	/** 사용자 Single_id */
	protected String session_single_id;
	/** 사용자 사업부 코드 */
	protected String session_division_cd;
	/** 세션-사용자 회사 전화번호*/
	protected String session_comp_tel;
	/** 세션-사용자 핸드폰 번호 */
	protected String session_mobile;
	
	/** 세션 - 상위 부서들 */
	protected String session_up_level_dept_cd;
	/** 세션 - 사용자 소속조직코드 */
	protected String session_blngt_orgnz;
	/** 세션 - 사용자 소속조직코드 */
	protected String session_blngt_orgnz_nm;
	/** 세션 - 사용자 소속조직코드 약어 */
	protected String session_blngt_orgnz_nm_abbr;
	/** 세션 - 사용자 조직장 여부 */
	protected String session_manager_yn;
	/** 세션 - 지원부서 여부 */
	protected String session_support_yn;
	/** 세션 - 내부 부서 코드 */
	protected String session_in_dept_cd;
	/** 세션 - 권한지정여부 */
	protected String session_auth_apnt_yn;
	/** 세션 - 권한지정부서*/
	protected String session_auth_apnt_dept;
	
	/** 세션 - COMP cd **/
	protected String session_auth_comp_cd;
	
	/**********************************************************************
	 * 공통성격
	 **********************************************************************/

	/**검색(공통) */
	protected String doSearch;                      
	/**정렬(공통) */
	protected String gSortStat;                     

	/**페이지(공통) */
	protected String start_index;                      
	/**페이지(공통) */
	protected String end_index;                        
	/**페이지(공통) */
	protected String curPage;   
	/**페이지(공통) */
	protected String srch_page_count;
	
	/**결과타이틀(공통)**/
	protected String return_title ;
	
	/**결과메시지(공통)**/
	protected String return_message ;
	
	/** 첨부파일(공통) **/
	protected String		fileInfos;
	protected String		file_bigclsfcn;
	protected String		file_midclsfcn;
	protected String		file_smlclsfcn;
	protected String 		ref_key;
	/**********************************************************************
	 * 메뉴정보
	 **********************************************************************/
	protected String menu_id;
	
	/**********************************************************************
	 * 데이타베이스 타입
	 **********************************************************************/
	protected String dbType;

	/**********************************************************************
	 * 권한 관련
	 **********************************************************************/
	protected boolean auth_modify;
	protected boolean auth_delete;
	protected boolean auth_search;
	protected boolean auth_insert;
	
	protected String top_role;
	
	/**********************************************************************
	 * 연계시스템 구분
	 **********************************************************************/
	protected String session_infsysnm;
	
	public String getSession_infsysnm() {
		return session_infsysnm;
	}

	public void setSession_infsysnm(String session_infsysnm) {
		this.session_infsysnm = session_infsysnm;
	}
	
	/**********************************************************************
	 * 상품 구분
	 **********************************************************************/
	protected String session_related_products;	

	public String getSession_related_products() {
		return session_related_products;
	}

	public void setSession_related_products(String session_related_products) {
		this.session_related_products = session_related_products;
	}

	/**********************************************************************
	 * Getters and Setters Method
	 **********************************************************************/
	
	public String getSys_cd() {
		return sys_cd;
	}

	public boolean isAuth_insert() {
		return auth_insert;
	}

	public void setAuth_insert(boolean auth_insert) {
		this.auth_insert = auth_insert;
	}

	public String getSession_email() {
		return session_email;
	}

	public void setSession_email(String session_email) {
		this.session_email = session_email;
	}

	public String getSession_single_id() {
		return session_single_id;
	}

	public void setSession_single_id(String session_single_id) {
		this.session_single_id = session_single_id;
	}

	public String getTop_role() {
		return top_role;
	}
	public void setTop_role(String top_role) {
		this.top_role = top_role;
	}
	public String getSession_division_cd() {
		return session_division_cd;
	}
	public void setSession_division_cd(String session_division_cd) {
		this.session_division_cd = session_division_cd;
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
	public boolean isAuth_search() {
		return auth_search;
	}
	public void setAuth_search(boolean auth_search) {
		this.auth_search = auth_search;
	}
	public String getRef_key() {
		return ref_key;
	}
	public void setRef_key(String ref_key) {
		this.ref_key = ref_key;
	}
	public String getFile_bigclsfcn() {
		return file_bigclsfcn;
	}
	public void setFile_bigclsfcn(String file_bigclsfcn) {
		this.file_bigclsfcn = file_bigclsfcn;
	}
	public String getFile_midclsfcn() {
		return file_midclsfcn;
	}
	public void setFile_midclsfcn(String file_midclsfcn) {
		this.file_midclsfcn = file_midclsfcn;
	}
	public String getFile_smlclsfcn() {
		return file_smlclsfcn;
	}
	public void setFile_smlclsfcn(String file_smlclsfcn) {
		this.file_smlclsfcn = file_smlclsfcn;
	}
	public String getgSortStat() {
		return gSortStat;
	}
	public void setgSortStat(String gSortStat) {
		this.gSortStat = gSortStat;
	}
	public String getFileInfos() {
		return fileInfos;
	}
	public void setFileInfos(String fileInfos) {
		this.fileInfos = fileInfos;
	}
	public void setSys_cd(String sys_cd) {
		this.sys_cd = sys_cd;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getSession_user_id() {
		return session_user_id;
	}
	public void setSession_user_id(String session_user_id) {
		this.session_user_id = session_user_id;
	}
	public String getSession_user_nm() {
		return session_user_nm;
	}
	public void setSession_user_nm(String session_user_nm) {
		this.session_user_nm = session_user_nm;
	}
	public String getSession_dept_cd() {
		return session_dept_cd;
	}
	public void setSession_dept_cd(String session_dept_cd) {
		this.session_dept_cd = session_dept_cd;
	}
	public String getSession_dept_nm() {
		return session_dept_nm;
	}
	public void setSession_dept_nm(String session_dept_nm) {
		this.session_dept_nm = session_dept_nm;
	}
	public String getSession_clms_user_orgnz() {
		return session_clms_user_orgnz;
	}
	public void setSession_clms_user_orgnz(String session_clms_user_orgnz) {
		this.session_clms_user_orgnz = session_clms_user_orgnz;
	}
	public String getSession_resp_operdiv() {
		return session_resp_operdiv;
	}
	public void setSession_resp_operdiv(String session_resp_operdiv) {
		this.session_resp_operdiv = session_resp_operdiv;
	}
	public String getSession_comp_cd() {
		return session_comp_cd;
	}
	public void setSession_comp_cd(String session_comp_cd) {
		this.session_comp_cd = session_comp_cd;
	}
	public String getSession_comp_nm() {
		return session_comp_nm;
	}
	public void setSession_comp_nm(String session_comp_nm) {
		this.session_comp_nm = session_comp_nm;
	}
	public String getSession_user_locale() {
		return session_user_locale;
	}
	public void setSession_user_locale(String session_user_locale) {
		this.session_user_locale = session_user_locale;
	}
	public String getSession_jikgup_cd() {
		return session_jikgup_cd;
	}
	public void setSession_jikgup_cd(String session_jikgup_cd) {
		this.session_jikgup_cd = session_jikgup_cd;
	}
	public String getUser_jikgup_nm() {
		return session_jikgup_nm;
	}
	public void setSession_jikgup_nm(String session_jikgup_nm) {
		this.session_jikgup_nm = session_jikgup_nm;
	}
	public ArrayList getSession_user_role_cds() {
		return session_user_role_cds;
	}
	public void setSession_user_role_cds(ArrayList session_user_role_cds) {
		this.session_user_role_cds = session_user_role_cds;
	}
	public String getSession_jikgup_nm() {
		return session_jikgup_nm;
	}
	public String getForward_url() {
		return forward_url;
	}
	public void setForward_url(String forward_url) {
		this.forward_url = forward_url;
	}
	
	public String getStart_index() {
		return start_index;
	}
	public void setStart_index(String start_index) {
		this.start_index = start_index;
	}
	public String getEnd_index() {
		return end_index;
	}
	public void setEnd_index(String end_index) {
		this.end_index = end_index;
	}
	public String getDoSearch() {
		return doSearch;
	}
	public void setDoSearch(String doSearch) {
		this.doSearch = doSearch;
	}
	public String getGSortStat() {
		return gSortStat;
	}
	public void setGSortStat(String sortStat) {
		gSortStat = sortStat;
	}
	public String getCurPage() {
		return curPage;
	}
	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getDbType() {
		return dbType;
	}
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	public String getReturn_message() {
		return return_message;
	}
	public void setReturn_message(String return_message) {
		this.return_message = return_message;
	}
	public String getReturn_title() {
		return return_title;
	}
	public void setReturn_title(String return_title) {
		this.return_title = return_title;
	}
	public String getSession_comp_tel() {
		return session_comp_tel;
	}
	public void setSession_comp_tel(String session_comp_tel) {
		this.session_comp_tel = session_comp_tel;
	}
	public String getSession_mobile() {
		return session_mobile;
	}
	public void setSession_mobile(String session_mobile) {
		this.session_mobile = session_mobile;
	}
	public String getSession_up_level_dept_cd() {
		return session_up_level_dept_cd;
	}
	public void setSession_up_level_dept_cd(String session_up_level_dept_cd) {
		this.session_up_level_dept_cd = session_up_level_dept_cd;
	}
	public String getSession_blngt_orgnz() {
		return session_blngt_orgnz;
	}
	public void setSession_blngt_orgnz(String session_blngt_orgnz) {
		this.session_blngt_orgnz = session_blngt_orgnz;
	}
	public String getSession_manager_yn() {
		return session_manager_yn;
	}
	public void setSession_manager_yn(String session_manager_yn) {
		this.session_manager_yn = session_manager_yn;
	}
	public String getSession_support_yn() {
		return session_support_yn;
	}
	public void setSession_support_yn(String session_support_yn) {
		this.session_support_yn = session_support_yn;
	}
	public String getSession_in_dept_cd() {
		return session_in_dept_cd;
	}
	public void setSession_in_dept_cd(String session_in_dept_cd) {
		this.session_in_dept_cd = session_in_dept_cd;
	}

	public String getSession_blngt_orgnz_nm() {
		return session_blngt_orgnz_nm;
	}

	public void setSession_blngt_orgnz_nm(String session_blngt_orgnz_nm) {
		this.session_blngt_orgnz_nm = session_blngt_orgnz_nm;
	}

	public String getSession_blngt_orgnz_nm_abbr() {
		return session_blngt_orgnz_nm_abbr;
	}

	public void setSession_blngt_orgnz_nm_abbr(String session_blngt_orgnz_nm_abbr) {
		this.session_blngt_orgnz_nm_abbr = session_blngt_orgnz_nm_abbr;
	}

	public String getSession_auth_apnt_yn() {
		return session_auth_apnt_yn;
	}

	public void setSession_auth_apnt_yn(String session_auth_apnt_yn) {
		this.session_auth_apnt_yn = session_auth_apnt_yn;
	}

	public String getSession_auth_apnt_dept() {
		return session_auth_apnt_dept;
	}

	public void setSession_auth_apnt_dept(String session_auth_apnt_dept) {
		this.session_auth_apnt_dept = session_auth_apnt_dept;
	}

	public String getSrch_page_count() {
		return srch_page_count;
	}

	public void setSrch_page_count(String srch_page_count) {
		this.srch_page_count = srch_page_count;
	}

	public String getSession_auth_comp_cd() {
		return session_auth_comp_cd;
	}

	public void setSession_auth_comp_cd(String session_auth_comp_cd) {
		this.session_auth_comp_cd = session_auth_comp_cd;
	}
	
	
	
	/**
	 * toString
	 *
	public String toString() {
		StringBuffer sb = new StringBuffer() ;

		Field[] fieldArray = this.getClass().getDeclaredFields() ;
		
		for(int i=0; i<fieldArray.length; i++) {
			try {
				sb.append(fieldArray[i].getName() + " = [" + ObjectCopyUtil.getFieldValue(this, fieldArray[i].getName()) + "]" ) ;
				if(i<fieldArray.length-1) {
					sb.append("\r\n") ;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return sb.toString() ;
	}*/
}
