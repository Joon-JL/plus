package com.sds.secframework.dept.dto;

import com.sds.secframework.common.dto.CommonForm;

/**
 * 부서정보 Value Object
 *  
 * @version V1.0
 * 작성일 : 2009/12/18
 * @author 금현서, ace4u_khs@samsung.com
 */

public class DeptForm extends CommonForm {

	/**********************************************************************
	 * 부서정보
	 **********************************************************************/
	/**부서코드 */
	private String dept_cd;                         
	/**부서명 */
	private String dept_nm;                         
	/**부서영문명 */
	private String dept_nm_eng;                     
	/**부서레벨 */
	private int    dept_level;                      
	/**부서간순위 */
	private int    dept_order;                      
	/**내부부서코드 */
	private String in_dept_cd;                      
	/**상위부서코드 */
	private String up_dept_cd;                      
	/**상위부서명 */
	private String up_dept_nm;                      
	/**상위부서영문명 */
	private String up_dept_nm_eng; 
	/**회사코드 */
	private String comp_cd;                         
	/**회사명 */
	private String comp_nm;                         
	/**회사영문명 */
	private String comp_nm_eng;                         
	/**부문코드 */
	private String business_cd;                     
	/**부문명 */
	private String business_nm;                     
	/**총괄코드 */
	private String sub_org_cd;                     
	/**총괄명 */
	private String sub_org_nm;                     
	/**영문총괄명 */
	private String sub_org_nm_eng;                     
	/**하위부서여부 */
	private String down_dept_yn;                    
	/**부서장사번 */
	private String dept_mgr_emp_no;                 
	/**부서장성명 */
	private String dept_mgr_nm;                     
	/**부서장직급명 */
	private String dept_mgr_jikgup_nm;              
	/**부서장영문직급명 */
	private String dept_mgr_jikgup_nm_eng;          
	/**등록자아이디 */
	private String reg_id;                          
	/**등록일시 */
	private String reg_dt;                          
	/**수정자아이디 */
	private String mod_id;                          
	/**수정일시 */
	private String mod_dt;                          

	/**********************************************************************
	 * DB 이외 추가
	 **********************************************************************/
	/**(검색)부서명 */
	private String srch_dept_nm;                  
	/**(검색)부서검색 코드 */
	private String tree_dept_cd;                  
	/**선택부서코드 */
	private String select_dept_cd;                

	private String menu_id;
	
	/**********************************************************************
	 * 공통성격
	 **********************************************************************/
	/**검색명 */
	private String search_name;					  	

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
	public String getDept_nm_eng() {
		return dept_nm_eng;
	}
	public void setDept_nm_eng(String dept_nm_eng) {
		this.dept_nm_eng = dept_nm_eng;
	}
	public int getDept_level() {
		return dept_level;
	}
	public void setDept_level(int dept_level) {
		this.dept_level = dept_level;
	}
	public int getDept_order() {
		return dept_order;
	}
	public void setDept_order(int dept_order) {
		this.dept_order = dept_order;
	}
	public String getIn_dept_cd() {
		return in_dept_cd;
	}
	public void setIn_dept_cd(String in_dept_cd) {
		this.in_dept_cd = in_dept_cd;
	}
	public String getUp_dept_cd() {
		return up_dept_cd;
	}
	public void setUp_dept_cd(String up_dept_cd) {
		this.up_dept_cd = up_dept_cd;
	}
	public String getUp_dept_nm() {
		return up_dept_nm;
	}
	public void setUp_dept_nm(String up_dept_nm) {
		this.up_dept_nm = up_dept_nm;
	}
	public String getComp_cd() {
		return comp_cd;
	}
	public void setComp_cd(String comp_cd) {
		this.comp_cd = comp_cd;
	}
	public String getComp_nm() {
		return comp_nm;
	}
	public void setComp_nm(String comp_nm) {
		this.comp_nm = comp_nm;
	}
	public String getBusiness_cd() {
		return business_cd;
	}
	public void setBusiness_cd(String business_cd) {
		this.business_cd = business_cd;
	}
	public String getBusiness_nm() {
		return business_nm;
	}
	public void setBusiness_nm(String business_nm) {
		this.business_nm = business_nm;
	}
	public String getDown_dept_yn() {
		return down_dept_yn;
	}
	public void setDown_dept_yn(String down_dept_yn) {
		this.down_dept_yn = down_dept_yn;
	}
	public String getDept_mgr_emp_no() {
		return dept_mgr_emp_no;
	}
	public void setDept_mgr_emp_no(String dept_mgr_emp_no) {
		this.dept_mgr_emp_no = dept_mgr_emp_no;
	}
	public String getDept_mgr_nm() {
		return dept_mgr_nm;
	}
	public void setDept_mgr_nm(String dept_mgr_nm) {
		this.dept_mgr_nm = dept_mgr_nm;
	}
	public String getDept_mgr_jikgup_nm() {
		return dept_mgr_jikgup_nm;
	}
	public void setDept_mgr_jikgup_nm(String dept_mgr_jikgup_nm) {
		this.dept_mgr_jikgup_nm = dept_mgr_jikgup_nm;
	}
	public String getDept_mgr_jikgup_nm_eng() {
		return dept_mgr_jikgup_nm_eng;
	}
	public void setDept_mgr_jikgup_nm_eng(String dept_mgr_jikgup_nm_eng) {
		this.dept_mgr_jikgup_nm_eng = dept_mgr_jikgup_nm_eng;
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
	public String getSrch_dept_nm() {
		return srch_dept_nm;
	}
	public void setSrch_dept_nm(String srch_dept_nm) {
		this.srch_dept_nm = srch_dept_nm;
	}
	public String getTree_dept_cd() {
		return tree_dept_cd;
	}
	public void setTree_dept_cd(String tree_dept_cd) {
		this.tree_dept_cd = tree_dept_cd;
	}
	public String getSelect_dept_cd() {
		return select_dept_cd;
	}
	public void setSelect_dept_cd(String select_dept_cd) {
		this.select_dept_cd = select_dept_cd;
	}
	public String getSearch_name() {
		return search_name;
	}
	public void setSearch_name(String search_name) {
		this.search_name = search_name;
	}
	public String getSub_org_cd() {
		return sub_org_cd;
	}
	public void setSub_org_cd(String sub_org_cd) {
		this.sub_org_cd = sub_org_cd;
	}
	public String getSub_org_nm() {
		return sub_org_nm;
	}
	public void setSub_org_nm(String sub_org_nm) {
		this.sub_org_nm = sub_org_nm;
	}
	public String getSub_org_nm_eng() {
		return sub_org_nm_eng;
	}
	public void setSub_org_nm_eng(String sub_org_nm_eng) {
		this.sub_org_nm_eng = sub_org_nm_eng;
	}
	public String getUp_dept_nm_eng() {
		return up_dept_nm_eng;
	}
	public void setUp_dept_nm_eng(String up_dept_nm_eng) {
		this.up_dept_nm_eng = up_dept_nm_eng;
	}
	public String getComp_nm_eng() {
		return comp_nm_eng;
	}
	public void setComp_nm_eng(String comp_nm_eng) {
		this.comp_nm_eng = comp_nm_eng;
	}
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
}
