/**
 * 
 * Project Name : 계약관리시스템 구축
 * File name	: CLMSCommonController.java
 * Description	: 공통코드관련 Controller
 * Author		: SDS
 * Date			: 2011. 08. 04
 * Copyright	: 
 */
package com.sec.common.clmscom.dto;

import com.sds.secframework.common.dto.CommonVO;

/**
 * Description	: 공통코드관련 vo 객체
 * Author 		: SDS
 * Date			: 2011. 08. 04
 * 
 */
public class CLMSCommonVO extends CommonVO {
	private String sys_cd;
	private String grp_cd;
	private String cd;
	private String cd_nm;
	private String cd_abbr_nm;
	private String cd_nm_eng;
	private String cd_abbr_nm_eng;
	private String selectCd;
	/** 메뉴구분 */
	private String menu_gbn;
	/** 메뉴 아이디 */
	private String menu_id;
	/** 메뉴 사용여부 */
	private String use_yn;
	/** 상위메뉴 아이디 */
	private String up_menu_id;
	/** 메뉴명 */
	private String menu_nm;
	/** 메뉴url */
	private String menu_url;
	/** 단순 forward_url */
	private String forward_url;
	/** 상위조직코드 */
	private String up_orgnz_cd;
	/** 부서코드 */
	private String dept_cd;
	/** 부서명 */
	private String dept_nm;
	/** 부서 (단계) 깊이 */
	private Integer dept_level;
	/** 부서 순서 */
	private Integer dept_order;
	/** 하위 부서 존재여부 (T/F) */
	private String down_dept_yn;
	/** 상위 부서 코드 */
	private String up_dept_cd;

	/** CLOB 조회 대상 테이블 */
	private String target_table;
	/** CLOB 조회용 Key */
	private String target_key;
	/** CLOB 데이터 */
	private String target_clob;
	/** page 구분 */
	private String page_gbn;
	/** 최상위 역할 */
	private String top_role;

	/***********************************************
	 *	팝업용 담당자용 필드 
	 ***********************************************/
	/** 사용자아이디 */
	private String user_id;
	/** 성명 */
	private String user_nm;
	/** Locale */
	private String locale;
	/** 권한CD */
	private String role_cd;
	/** 권한명 */
	private String role_nm;
	/** 지역코드 */
	private String region_cd;
	/** 지역명 */
	private String region_nm;
	/** 지역중분류코드 */
	private String region_midcate;
	/** 지역중분류명 */
	private String region_midcate_nm;
	/** 이메일 */
	private String email;
	/** 삭제여부 */
	private String del_yn;
	/** 탭구분 */
	private String div_nm;
	
	private String useman_mng_itm1;
	/** 회사코드 */
	private String comp_cd;
	
	/** 체크값을 화면에서 넘겨준다. */
	private String all_sum;
	
	private String dis_yn;
	
	private String item_cd;
	
	public String getSys_cd() {
		return sys_cd;
	}
	public String getAll_sum() {
		return all_sum;
	}
	public void setAll_sum(String all_sum) {
		this.all_sum = all_sum;
	}
	public String getDis_yn() {
		return dis_yn;
	}
	public void setDis_yn(String dis_yn) {
		this.dis_yn = dis_yn;
	}
	public String getItem_cd() {
		return item_cd;
	}
	public void setItem_cd(String item_cd) {
		this.item_cd = item_cd;
	}
	public void setSys_cd(String sys_cd) {
		this.sys_cd = sys_cd;
	}
	public String getGrp_cd() {
		return grp_cd;
	}
	public void setGrp_cd(String grp_cd) {
		this.grp_cd = grp_cd;
	}
	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	public String getCd_nm() {
		return cd_nm;
	}
	public void setCd_nm(String cd_nm) {
		this.cd_nm = cd_nm;
	}
	public String getCd_abbr_nm() {
		return cd_abbr_nm;
	}
	public void setCd_abbr_nm(String cd_abbr_nm) {
		this.cd_abbr_nm = cd_abbr_nm;
	}
	public String getCd_nm_eng() {
		return cd_nm_eng;
	}
	public void setCd_nm_eng(String cd_nm_eng) {
		this.cd_nm_eng = cd_nm_eng;
	}
	public String getCd_abbr_nm_eng() {
		return cd_abbr_nm_eng;
	}
	public void setCd_abbr_nm_eng(String cd_abbr_nm_eng) {
		this.cd_abbr_nm_eng = cd_abbr_nm_eng;
	}
	public String getSelectCd() {
		return selectCd;
	}
	public void setSelectCd(String selectCd) {
		this.selectCd = selectCd;
	}
	public String getMenu_gbn() {
		return menu_gbn;
	}
	public void setMenu_gbn(String menu_gbn) {
		this.menu_gbn = menu_gbn;
	}
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public String getUp_menu_id() {
		return up_menu_id;
	}
	public void setUp_menu_id(String up_menu_id) {
		this.up_menu_id = up_menu_id;
	}
	public String getMenu_nm() {
		return menu_nm;
	}
	public void setMenu_nm(String menu_nm) {
		this.menu_nm = menu_nm;
	}
	public String getMenu_url() {
		return menu_url;
	}
	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}
	public String getForward_url() {
		return forward_url;
	}
	public void setForward_url(String forward_url) {
		this.forward_url = forward_url;
	}
	public String getUp_orgnz_cd() {
		return up_orgnz_cd;
	}
	public void setUp_orgnz_cd(String up_orgnz_cd) {
		this.up_orgnz_cd = up_orgnz_cd;
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
	public Integer getDept_level() {
		return dept_level;
	}
	public void setDept_level(Integer dept_level) {
		this.dept_level = dept_level;
	}
	public Integer getDept_order() {
		return dept_order;
	}
	public void setDept_order(Integer dept_order) {
		this.dept_order = dept_order;
	}
	public String getDown_dept_yn() {
		return down_dept_yn;
	}
	public void setDown_dept_yn(String down_dept_yn) {
		this.down_dept_yn = down_dept_yn;
	}
	public String getUp_dept_cd() {
		return up_dept_cd;
	}
	public void setUp_dept_cd(String up_dept_cd) {
		this.up_dept_cd = up_dept_cd;
	}
	public String getTarget_table() {
		return target_table;
	}
	public void setTarget_table(String target_table) {
		this.target_table = target_table;
	}
	public String getTarget_key() {
		return target_key;
	}
	public void setTarget_key(String target_key) {
		this.target_key = target_key;
	}
	public String getTarget_clob() {
		return target_clob;
	}
	public void setTarget_clob(String target_clob) {
		this.target_clob = target_clob;
	}
	public String getPage_gbn() {
		return page_gbn;
	}
	public void setPage_gbn(String page_gbn) {
		this.page_gbn = page_gbn;
	}
	public String getTop_role() {
		return top_role;
	}
	public void setTop_role(String top_role) {
		this.top_role = top_role;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_nm() {
		return user_nm;
	}
	public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
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
	public String getRegion_cd() {
		return region_cd;
	}
	public void setRegion_cd(String region_cd) {
		this.region_cd = region_cd;
	}
	public String getRegion_nm() {
		return region_nm;
	}
	public void setRegion_nm(String region_nm) {
		this.region_nm = region_nm;
	}
	public String getRegion_midcate() {
		return region_midcate;
	}
	public void setRegion_midcate(String region_midcate) {
		this.region_midcate = region_midcate;
	}
	public String getRegion_midcate_nm() {
		return region_midcate_nm;
	}
	public void setRegion_midcate_nm(String region_midcate_nm) {
		this.region_midcate_nm = region_midcate_nm;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	public String getDiv_nm() {
		return div_nm;
	}
	public void setDiv_nm(String div_nm) {
		this.div_nm = div_nm;
	}
	public String getUseman_mng_itm1() {
		return useman_mng_itm1;
	}
	public void setUseman_mng_itm1(String useman_mng_itm1) {
		this.useman_mng_itm1 = useman_mng_itm1;
	}
	
	public String getComp_cd() {
		return comp_cd;
	}
	public void setComp_cd(String comp_cd) {
		this.comp_cd = comp_cd;
	}
}
