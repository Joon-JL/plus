/**
 * Project Name : ecms
 * File name	: ApprovalPathForm.java
 * Description	: 결재라인Role관리 Data Form(Model)
 * Author		: 박정훈
 * Date			: 2014. 05. 19
 * Copyright	:
 */
package com.sec.clm.admin.dto;

import com.sds.secframework.common.dto.CommonForm;

/**
 * Description	: 결재라인ApprovalPathForm관리 Data Form(Model)
 * Author		: 박정훈
 * Date			: 2014. 05. 19
 */
public class ApprovalPathForm  extends CommonForm {
	

	/**********************************************
	 * 검색 조건
	 **********************************************/
	private String srch_path_name;
	/** 사용여부 */
	private String srch_use_yn;
	
	private String srch_loc_gbn;
	
	/**********************************************
	 * 페이징
	 **********************************************/
	/** 페이지(공통) */
	protected String start_index;                      
	/** 페이지(공통) */
	protected String end_index;                        
	/** 페이지(공통) */
	protected String curPage;  
	/** 페이지당 row 수 */
	int row_cnt = 10;

	/**********************************************
	 * 입력값
	 **********************************************/
	/** comp_cd */
	private String comp_cd;
	
	/** path_cd */
	private String path_id;
	/** path_nm */
	private String path_nm;
	
	/** role_cd */
	private String role_cd;
	/** role_name */
	private String role_name;
	
	/** priority */
	private String priority;
	
	/** mandatory */
	private String mandatory;
		
	
	/** 등록_일시 */
	private String reg_dt;
	/** 등록자_ID */
	private String reg_id;
	/** 수정_일시 */
	private String mod_dt;
	/** 수정_ID */
	private String mod_id;
	/** 삭제_일시 */
	private String del_dt;
	/** 삭제_ID */
	private String del_id;
	/** 설명 */
	private String comments;
	/** 사용여부 */
	private String use_yn;
	
	/**Approval Path Users**/
	private String user_id;
	
	private String a_type;
	private String r_type;
	private String role_user_id;
	
	
	private String ass_id_list;
	private String atype_val;
	private String rtype_val;
	
	private String user_id_tmp;
	private String atype_val_tmp;
	private String rtype_val_tmp;
	
	private String sort_no;
	
	/** contract detail */
	private int condition_id;
	private String operation;
	private String condition;
	private String c_option;
	private String c_val;
	private String codition_chk;
	
	
	private String input_condi_list;
	private String input_condi_val;
	private String input_condi_option;
	
	private String condi_list_tmp;
	private String condi_val_tmp;
	private String condi_option_tmp;
	
	/** contract type */
	private String type_1;
	private String type_2;
	private String type_3;
	private String type_4;
	
	private String up_type_cd;
	
	/**********************************************
	 * getter, setter 메소드
	 **********************************************/

	private String loc_gbn;
	
	
	public String getSrch_path_name() {
		return srch_path_name;
	}
	public void setSrch_path_name(String srch_path_name) {
		this.srch_path_name = srch_path_name;
	}
	public String getSrch_use_yn() {
		return srch_use_yn;
	}
	public void setSrch_use_yn(String srch_use_yn) {
		this.srch_use_yn = srch_use_yn;
	}
	

	public String getSrch_loc_gbn() {
		return srch_loc_gbn;
	}
	public void setSrch_loc_gbn(String srch_loc_gbn) {
		this.srch_loc_gbn = srch_loc_gbn;
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
	public String getCurPage() {
		return curPage;
	}
	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}
	public int getRow_cnt() {
		return row_cnt;
	}
	public void setRow_cnt(int row_cnt) {
		this.row_cnt = row_cnt;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}
	public String getReg_id() {
		return reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getComp_cd() {
		return comp_cd;
	}
	public void setComp_cd(String comp_cd) {
		this.comp_cd = comp_cd;
	}
	public String getRole_cd() {
		return role_cd;
	}
	public void setRole_cd(String role_cd) {
		this.role_cd = role_cd;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_id_tmp() {
		return user_id_tmp;
	}
	public void setUser_id_tmp(String user_id_tmp) {
		this.user_id_tmp = user_id_tmp;
	}
	public String getMod_dt() {
		return mod_dt;
	}
	public void setMod_dt(String mod_dt) {
		this.mod_dt = mod_dt;
	}
	public String getMod_id() {
		return mod_id;
	}
	public void setMod_id(String mod_id) {
		this.mod_id = mod_id;
	}
	public String getDel_dt() {
		return del_dt;
	}
	public void setDel_dt(String del_dt) {
		this.del_dt = del_dt;
	}
	public String getDel_id() {
		return del_id;
	}
	public void setDel_id(String del_id) {
		this.del_id = del_id;
	}
	public String getAss_id_list() {
		return ass_id_list;
	}
	public void setAss_id_list(String ass_id_list) {

		this.ass_id_list = ass_id_list;
	}
	public String getUp_type_cd() {
		return up_type_cd;
	}
	public void setUp_type_cd(String up_type_cd) {
		this.up_type_cd = up_type_cd;
	}
	public String getType_1() {
		return type_1;
	}
	public void setType_1(String type_1) {
		this.type_1 = type_1;
	}
	public String getType_2() {
		return type_2;
	}
	public void setType_2(String type_2) {
		this.type_2 = type_2;
	}
	public String getType_3() {
		return type_3;
	}
	public void setType_3(String type_3) {
		this.type_3 = type_3;
	}
	public String getType_4() {
		return type_4;
	}
	public void setType_4(String type_4) {
		this.type_4 = type_4;
	}
	public String getPath_id() {
		return path_id;
	}
	public void setPath_id(String path_id) {
		this.path_id = path_id;
	}
	public String getPath_nm() {
		return path_nm;
	}
	public void setPath_nm(String path_nm) {
		this.path_nm = path_nm;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getMandatory() {
		return mandatory;
	}
	public void setMandatory(String mandatory) {
		this.mandatory = mandatory;
	}
	public int getCondition_id() {
		return condition_id;
	}
	public void setCondition_id(int condition_id) {
		this.condition_id = condition_id;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getC_option() {
		return c_option;
	}
	public void setC_option(String c_option) {
		this.c_option = c_option;
	}
	public String getC_val() {
		return c_val;
	}
	public void setC_val(String c_val) {
		this.c_val = c_val;
	}
	public String getCodition_chk() {
		return codition_chk;
	}
	public void setCodition_chk(String codition_chk) {
		this.codition_chk = codition_chk;
	}
	public String getInput_condi_list() {
		return input_condi_list;
	}
	public void setInput_condi_list(String input_condi_list) {
		this.input_condi_list = input_condi_list;
	}
	public String getInput_condi_val() {
		return input_condi_val;
	}
	public void setInput_condi_val(String input_condi_val) {
		this.input_condi_val = input_condi_val;
	}
	public String getInput_condi_option() {
		return input_condi_option;
	}
	public void setInput_condi_option(String input_condi_option) {
		this.input_condi_option = input_condi_option;
	}
	public String getA_type() {
		return a_type;
	}
	public void setA_type(String a_type) {
		this.a_type = a_type;
	}
	public String getR_type() {
		return r_type;
	}
	public void setR_type(String r_type) {
		this.r_type = r_type;
	}
	public String getRole_user_id() {
		return role_user_id;
	}
	public void setRole_user_id(String role_user_id) {
		this.role_user_id = role_user_id;
	}
	public String getCondi_list_tmp() {
		return condi_list_tmp;
	}
	public void setCondi_list_tmp(String condi_list_tmp) {
		this.condi_list_tmp = condi_list_tmp;
	}
	public String getCondi_val_tmp() {
		return condi_val_tmp;
	}
	public void setCondi_val_tmp(String condi_val_tmp) {
		this.condi_val_tmp = condi_val_tmp;
	}
	public String getCondi_option_tmp() {
		return condi_option_tmp;
	}
	public void setCondi_option_tmp(String condi_option_tmp) {
		this.condi_option_tmp = condi_option_tmp;
	}
	public String getAtype_val() {
		return atype_val;
	}
	public void setAtype_val(String atype_val) {
		this.atype_val = atype_val;
	}
	public String getRtype_val() {
		return rtype_val;
	}
	public void setRtype_val(String rtype_val) {
		this.rtype_val = rtype_val;
	}
	public String getAtype_val_tmp() {
		return atype_val_tmp;
	}
	public void setAtype_val_tmp(String atype_val_tmp) {
		this.atype_val_tmp = atype_val_tmp;
	}
	public String getRtype_val_tmp() {
		return rtype_val_tmp;
	}
	public void setRtype_val_tmp(String rtype_val_tmp) {
		this.rtype_val_tmp = rtype_val_tmp;
	}
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	public String getLoc_gbn() {
		return loc_gbn;
	}
	public void setLoc_gbn(String loc_gbn) {
		this.loc_gbn = loc_gbn;
	}

	
}