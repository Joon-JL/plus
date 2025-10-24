package com.sds.secframework.auth.dto;

import com.sds.secframework.common.dto.CommonVO;

/**
 * 권한정보 Value Object
 *  
 * @version V1.0
 * 작성일 : 2009/12/18
 * @author 금현서, ace4u_khs@samsung.com
 */

public class AuthVO extends CommonVO {

	/**********************************************************************
	 * 역할 항목
	 **********************************************************************/
	/**역활코드*/
	private String role_cd;                       
	/**역활명*/
	private String role_nm;                       
	/**가상그룹설명*/
	private String comments;                      
	/**사용여부*/
	private String use_yn;                        
	/**등록자아이디*/
	private String reg_id;                        
	/**등록일시*/
	private String reg_dt;                        
	/**수정자아이디*/
	private String mod_id;                        
	/**수정일시*/
	private String mod_dt;

	/**********************************************************************
	 * 메뉴 Access Control 설정
	 **********************************************************************/
	/**메뉴아이디*/
	private String menu_id;
	/**access control*/
	private String access_control;
	
	/**********************************************************************
	 * 권한 항목
	 **********************************************************************/
	/**역활코드*/
	private String auth_cd;                       
	/**역활명*/
	private String auth_nm;                       
	/**회사코드 */
	private String comp_cd;

	/**********************************************************************
	 * 메소드
	 **********************************************************************/
	/**패키지명*/
	private String package_nm;
	/**클래스명*/
	private String class_nm;
	/**메소드명*/
	private String method_nm;

	/**********************************************************************
	 * 역할별 사용자 항목
	 **********************************************************************/
	/**성명*/
	private String user_nm;                      
	/**부서코드*/
	private String dept_cd;                      
	/**부서명*/
	private String dept_nm;                      
	/**직급명*/
	private String jikgup_nm;                    
	
	/**********************************************************************
	 * 전자검토자 항목
	 **********************************************************************/
	/**관리회사코드*/
	private String mng_comp_cd;
	/**관리자ID*/
	private String mng_user_id;
	
	/**********************************************************************
	 * Mapping 배열정보
	 **********************************************************************/
	/**사용자아이디(역활별 사용자)*/
	private String[] mapping_user_id;             
	/**역활코드(역활-권한)*/
	private String[] mapping_auth_cd;             
	/**메뉴아이디(권한-메뉴)*/
	private String[] mapping_menu_id;              
	/**메소드정보(권한-메소드:패키지명, 클래스명, 메소드명)*/
	private String[] mapping_method_infos;        
	/**페이지아이디(권한-페이지)*/
	private String[] mapping_page_id;
	/**회사아이디(전자검토자관리)*/
	private String[] mapping_comp_cd;

	/**********************************************************************
	 * DB 이외 추가
	 **********************************************************************/
	/**검색타입*/
	private String srch_cntnt_type;              
	/**검색내용*/
	private String srch_cntnt;                   
	/**사용자부서구분*/
	private String user_type;			
	/**검색(권한코드)*/
	private String srch_auth_cd;			
	/**검색(사용여부)*/
	private String srch_use_yn;                  
	/**체크박스 값(삭제시 사용)*/
	private String[] chkValues;                  


	//권한매핑 화면에서 검색조건으로 사용
	/**역활검색명*/
	private String srch_role_cntnt;              
	/**권한검색명*/
	private String srch_auth_cntnt;              
	/**사용자검색타입*/
	private String srch_user_cntnt_type;         
	/**사용자검색명*/
	private String srch_user_cntnt;              
	/**페이지검색명*/
	private String srch_page_cntnt;              
	
    /**********************************************************************
	* 페이지삭제
	**********************************************************************/
	/**메뉴삭제리스트*/
	private String[] chk_id;                     

    /**********************************************************************
	* 페이지데이타(입력용)
	**********************************************************************/
	/**시스템코드*/
	private String[] sys_cds;                     
	/**역활코드*/
	private String[] role_cds;                    
	/**역활명*/
	private String[] role_nms;                    
	/**권한코드*/
	private String[] auth_cds;                    
	/**권한명*/
	private String[] auth_nms;                    
	/**설명*/
	private String[] commentss;                   
	/**사용여부*/
	private String[] use_yns;
	
	public String getRole_cd() {
		return role_cd;
	}
	public void setRole_cd(String role_cd) {
		this.role_cd = role_cd;
	}
	public String getRole_nm() {
		return role_nm;
	}
	public void setRole_nm(String role_nm) {
		this.role_nm = role_nm;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
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
	public String getSrch_cntnt_type() {
		return srch_cntnt_type;
	}
	public void setSrch_cntnt_type(String srch_cntnt_type) {
		this.srch_cntnt_type = srch_cntnt_type;
	}
	public String getSrch_cntnt() {
		return srch_cntnt;
	}
	public void setSrch_cntnt(String srch_cntnt) {
		this.srch_cntnt = srch_cntnt;
	}
	public String getUser_nm() {
		return user_nm;
	}
	public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	}	
	public String getDept_cd() {
		return dept_cd;
	}
	public void setDept_cd(String dept_cd) {
		this.dept_cd = dept_cd;
	}
	public String getDept_nm() {
		return dept_nm;
	}
	public void setDept_nm(String dept_nm) {
		this.dept_nm = dept_nm;
	}
	public String getJikgup_nm() {
		return jikgup_nm;
	}
	public void setJikgup_nm(String jikgup_nm) {
		this.jikgup_nm = jikgup_nm;
	}	
	public String[] getMapping_user_id() {
		return mapping_user_id;
	}
	public void setMapping_user_id(String[] mapping_user_id) {
		this.mapping_user_id = mapping_user_id;
	}
	public String[] getMapping_auth_cd() {
		return mapping_auth_cd;
	}
	public void setMapping_auth_cd(String[] mapping_auth_cd) {
		this.mapping_auth_cd = mapping_auth_cd;
	}
	public String[] getMapping_menu_id() {
		return mapping_menu_id;
	}
	public void setMapping_menu_id(String[] mapping_menu_id) {
		this.mapping_menu_id = mapping_menu_id;
	}	
	public String[] getMapping_method_infos() {
		return mapping_method_infos;
	}
	public void setMapping_method_infos(String[] mapping_method_infos) {
		this.mapping_method_infos = mapping_method_infos;
	}
	public String[] getMapping_page_id() {
		return mapping_page_id;
	}
	public void setMapping_page_id(String[] mapping_page_id) {
		this.mapping_page_id = mapping_page_id;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}	
	
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getAccess_control() {
		return access_control;
	}
	public void setAccess_control(String access_control) {
		this.access_control = access_control;
	}
	public String getAuth_cd() {
		return auth_cd;
	}
	public void setAuth_cd(String auth_cd) {
		this.auth_cd = auth_cd;
	}
	public String getAuth_nm() {
		return auth_nm;
	}
	public void setAuth_nm(String auth_nm) {
		this.auth_nm = auth_nm;
	}
	public String getSrch_auth_cd() {
		return srch_auth_cd;
	}
	public void setSrch_auth_cd(String srch_auth_cd) {
		this.srch_auth_cd = srch_auth_cd;
	}	
	public String getSrch_use_yn() {
		return srch_use_yn;
	}
	public void setSrch_use_yn(String srch_use_yn) {
		this.srch_use_yn = srch_use_yn;
	}
	public String[] getChkValues() {
		return chkValues;
	}
	public void setChkValues(String[] chkValues) {
		this.chkValues = chkValues;
	}
	public String getSrch_role_cntnt() {
		return srch_role_cntnt;
	}
	public void setSrch_role_cntnt(String srch_role_cntnt) {
		this.srch_role_cntnt = srch_role_cntnt;
	}
	public String getSrch_auth_cntnt() {
		return srch_auth_cntnt;
	}
	public void setSrch_auth_cntnt(String srch_auth_cntnt) {
		this.srch_auth_cntnt = srch_auth_cntnt;
	}
	public String getSrch_user_cntnt_type() {
		return srch_user_cntnt_type;
	}
	public void setSrch_user_cntnt_type(String srch_user_cntnt_type) {
		this.srch_user_cntnt_type = srch_user_cntnt_type;
	}
	public String getSrch_user_cntnt() {
		return srch_user_cntnt;
	}
	public void setSrch_user_cntnt(String srch_user_cntnt) {
		this.srch_user_cntnt = srch_user_cntnt;
	}
	public String getSrch_page_cntnt() {
		return srch_page_cntnt;
	}
	public void setSrch_page_cntnt(String srch_page_cntnt) {
		this.srch_page_cntnt = srch_page_cntnt;
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
	public String[] getRole_cds() {
		return role_cds;
	}
	public void setRole_cds(String[] role_cds) {
		this.role_cds = role_cds;
	}
	public String[] getRole_nms() {
		return role_nms;
	}
	public void setRole_nms(String[] role_nms) {
		this.role_nms = role_nms;
	}
	public String[] getAuth_cds() {
		return auth_cds;
	}
	public void setAuth_cds(String[] auth_cds) {
		this.auth_cds = auth_cds;
	}
	public String[] getAuth_nms() {
		return auth_nms;
	}
	public void setAuth_nms(String[] auth_nms) {
		this.auth_nms = auth_nms;
	}
	public String[] getCommentss() {
		return commentss;
	}
	public void setCommentss(String[] commentss) {
		this.commentss = commentss;
	}
	public String[] getUse_yns() {
		return use_yns;
	}
	public void setUse_yns(String[] use_yns) {
		this.use_yns = use_yns;
	}
	public String getPackage_nm() {
		return package_nm;
	}
	public void setPackage_nm(String package_nm) {
		this.package_nm = package_nm;
	}
	public String getClass_nm() {
		return class_nm;
	}
	public void setClass_nm(String class_nm) {
		this.class_nm = class_nm;
	}
	public String getMethod_nm() {
		return method_nm;
	}
	public void setMethod_nm(String method_nm) {
		this.method_nm = method_nm;
	}
	public String getComp_cd() {
		return comp_cd;
	}
	public void setComp_cd(String comp_cd) {
		this.comp_cd = comp_cd;
	}
	public String[] getMapping_comp_cd() {
		return mapping_comp_cd;
	}
	public void setMapping_comp_cd(String[] mapping_comp_cd) {
		this.mapping_comp_cd = mapping_comp_cd;
	}
	public String getMng_comp_cd() {
		return mng_comp_cd;
	}
	public void setMng_comp_cd(String mng_comp_cd) {
		this.mng_comp_cd = mng_comp_cd;
	}
	public String getMng_user_id() {
		return mng_user_id;
	}
	public void setMng_user_id(String mng_user_id) {
		this.mng_user_id = mng_user_id;
	}
	
}
