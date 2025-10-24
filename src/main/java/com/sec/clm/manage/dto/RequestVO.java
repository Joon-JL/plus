/**
* Project Name : 계약관리시스템
* File Name : RequestVO.java
* Description : 계약권한요청 VO
* Author : 신남원
* Date : 2010.09.27
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.manage.dto;

import com.sds.secframework.common.dto.CommonVO;

public class RequestVO extends CommonVO {
	/**********************************************************************
	 * DB 변수
	 **********************************************************************/
	private String cntrt_id;
	private String cnsdreq_id;
	
	private int demnd_seqno;
	private String demnd_gbn;
	private String demndman_id;
	private String demndman_nm;
	private String demndman_dept_nm;
	private String demndman_jikgup_nm;	
	private String trgtman_id;
	private String trgtman_nm;
	private String trgtman_dept;
	private String trgtman_dept_nm;
	private String trgtman_jikgup_nm;
	private String trgtman_in_dept;
	private String rd_auth;
	private String auth_startday;
	private String auth_endday;
	private String demnd_status;	
	private String demnd_cont;
	private String demnd_dt;
	private String hndl_dt;
	private String hndlman_nm;
	private String hndlman_id;
	private String hndl_cont;
	private String del_yn;
	private String del_dt;
	private String del_id;
	private String del_nm;
	
	private String req_title;
	private String cntrt_respman_id;
	private String cntrt_respman_nm;
	private String cntrt_nm;

	/**********************************************************************
	 * DB 외 변수
	 **********************************************************************/
	/** Parameter */
	private String p_demnd_gbn;	
	private String p_prcs_depth;
	
	/**상태 구분자*/
	private String status_mode;
	/**리스트 구분자*/
	private String list_mode;
	/**검색 제목*/
	private String srch_review_title; 
	/**의뢰자 */
	private String srch_reqman_nm;
  /**의뢰시작일*/
	private String srch_start_reqday;
	/**의뢰종료일*/
	private String srch_end_reqday;
	/**담당자*/
	private String srch_respman_nm;
	/**담당부서*/
	private String srch_resp_dept;
	/**담당부서명*/
	private String srch_resp_dept_nm;
	/**계약상대방*/
	private String srch_oppnt_cd;
	/**계약상대방명*/
	private String srch_oppnt_nm;
	/**법무검토자*/
	private String srch_cnsdman_nm;
	/** 비즈니스 분류 */
	private String srch_biz_clsfcn;                  
	/** 쳬결목적 대분류 */	
	private String srch_cnclsnpurps_bigclsfcn;  
	/**단계*/  
	private String srch_step; 
	/**상태 */
	private String srch_state; 
	/**요청구분 **/
	private String srch_demand_gbn;
	/**진행상태 **/
	private String srch_demand_status;
	
	/**********************************************************************
	 * Getters and Setters Method
	 **********************************************************************/
	
	public String getCntrt_id() {
		return cntrt_id;
	}
	public String getP_demnd_gbn() {
		return p_demnd_gbn;
	}
	public void setP_demnd_gbn(String p_demnd_gbn) {
		this.p_demnd_gbn = p_demnd_gbn;
	}
	public String getP_prcs_depth() {
		return p_prcs_depth;
	}
	public void setP_prcs_depth(String p_prcs_depth) {
		this.p_prcs_depth = p_prcs_depth;
	}
	public void setCntrt_id(String cntrt_id) {
		this.cntrt_id = cntrt_id;
	}
	public int getDemnd_seqno() {
		return demnd_seqno;
	}
	public void setDemnd_seqno(int demnd_seqno) {
		this.demnd_seqno = demnd_seqno;
	}
	public String getDemndman_id() {
		return demndman_id;
	}
	public void setDemndman_id(String demndman_id) {
		this.demndman_id = demndman_id;
	}
	public String getDemndman_nm() {
		return demndman_nm;
	}
	public void setDemndman_nm(String demndman_nm) {
		this.demndman_nm = demndman_nm;
	}
	public String getDemndman_dept_nm() {
		return demndman_dept_nm;
	}
	public void setDemndman_dept_nm(String demndman_dept_nm) {
		this.demndman_dept_nm = demndman_dept_nm;
	}
	public String getTrgtman_id() {
		return trgtman_id;
	}
	public void setTrgtman_id(String trgtman_id) {
		this.trgtman_id = trgtman_id;
	}
	public String getTrgtman_nm() {
		return trgtman_nm;
	}
	public void setTrgtman_nm(String trgtman_nm) {
		this.trgtman_nm = trgtman_nm;
	}
	public String getTrgtman_dept_nm() {
		return trgtman_dept_nm;
	}
	public void setTrgtman_dept_nm(String trgtman_dept_nm) {
		this.trgtman_dept_nm = trgtman_dept_nm;
	}
	public String getRd_auth() {
		return rd_auth;
	}
	public void setRd_auth(String rd_auth) {
		this.rd_auth = rd_auth;
	}
	public String getAuth_startday() {
		return auth_startday;
	}
	public void setAuth_startday(String auth_startday) {
		this.auth_startday = auth_startday;
	}
	public String getAuth_endday() {
		return auth_endday;
	}
	public void setAuth_endday(String auth_endday) {
		this.auth_endday = auth_endday;
	}
	public String getDemnd_status() {
		return demnd_status;
	}
	public void setDemnd_status(String demnd_status) {
		this.demnd_status = demnd_status;
	}
	public String getDemnd_dt() {
		return demnd_dt;
	}
	public void setDemnd_dt(String demnd_dt) {
		this.demnd_dt = demnd_dt;
	}
	public String getHndl_dt() {
		return hndl_dt;
	}
	public void setHndl_dt(String hndl_dt) {
		this.hndl_dt = hndl_dt;
	}
	public String getHndl_cont() {
		return hndl_cont;
	}
	public void setHndl_cont(String hndl_cont) {
		this.hndl_cont = hndl_cont;
	}
	public String getHndlman_nm() {
		return hndlman_nm;
	}
	public void setHndlman_nm(String hndlman_nm) {
		this.hndlman_nm = hndlman_nm;
	}
	public String getHndlman_id() {
		return hndlman_id;
	}
	public void setHndlman_id(String hndlman_id) {
		this.hndlman_id = hndlman_id;
	}
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
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
	public String getDel_nm() {
		return del_nm;
	}
	public void setDel_nm(String del_nm) {
		this.del_nm = del_nm;
	}
	public String getDemnd_gbn() {
		return demnd_gbn;
	}
	public void setDemnd_gbn(String demnd_gbn) {
		this.demnd_gbn = demnd_gbn;
	}
	public String getDemndman_jikgup_nm() {
		return demndman_jikgup_nm;
	}
	public void setDemndman_jikgup_nm(String demndman_jikgup_nm) {
		this.demndman_jikgup_nm = demndman_jikgup_nm;
	}
	public String getTrgtman_jikgup_nm() {
		return trgtman_jikgup_nm;
	}
	public void setTrgtman_jikgup_nm(String trgtman_jikgup_nm) {
		this.trgtman_jikgup_nm = trgtman_jikgup_nm;
	}
	public String getDemnd_cont() {
		return demnd_cont;
	}
	public void setDemnd_cont(String demnd_cont) {
		this.demnd_cont = demnd_cont;
	}
	public String getCnsdreq_id() {
		return cnsdreq_id;
	}
	public void setCnsdreq_id(String cnsdreq_id) {
		this.cnsdreq_id = cnsdreq_id;
	}
	public String getStatus_mode() {
		return status_mode;
	}
	public void setStatus_mode(String status_mode) {
		this.status_mode = status_mode;
	}
	public String getList_mode() {
		return list_mode;
	}
	public void setList_mode(String list_mode) {
		this.list_mode = list_mode;
	}
	public String getSrch_review_title() {
		return srch_review_title;
	}
	public void setSrch_review_title(String srch_review_title) {
		this.srch_review_title = srch_review_title;
	}
	public String getSrch_reqman_nm() {
		return srch_reqman_nm;
	}
	public void setSrch_reqman_nm(String srch_reqman_nm) {
		this.srch_reqman_nm = srch_reqman_nm;
	}
	public String getSrch_start_reqday() {
		return srch_start_reqday;
	}
	public void setSrch_start_reqday(String srch_start_reqday) {
		this.srch_start_reqday = srch_start_reqday;
	}
	public String getSrch_end_reqday() {
		return srch_end_reqday;
	}
	public void setSrch_end_reqday(String srch_end_reqday) {
		this.srch_end_reqday = srch_end_reqday;
	}
	public String getSrch_respman_nm() {
		return srch_respman_nm;
	}
	public void setSrch_respman_nm(String srch_respman_nm) {
		this.srch_respman_nm = srch_respman_nm;
	}
	public String getSrch_resp_dept() {
		return srch_resp_dept;
	}
	public void setSrch_resp_dept(String srch_resp_dept) {
		this.srch_resp_dept = srch_resp_dept;
	}
	public String getSrch_resp_dept_nm() {
		return srch_resp_dept_nm;
	}
	public void setSrch_resp_dept_nm(String srch_resp_dept_nm) {
		this.srch_resp_dept_nm = srch_resp_dept_nm;
	}
	public String getSrch_oppnt_cd() {
		return srch_oppnt_cd;
	}
	public void setSrch_oppnt_cd(String srch_oppnt_cd) {
		this.srch_oppnt_cd = srch_oppnt_cd;
	}
	public String getSrch_oppnt_nm() {
		return srch_oppnt_nm;
	}
	public void setSrch_oppnt_nm(String srch_oppnt_nm) {
		this.srch_oppnt_nm = srch_oppnt_nm;
	}
	public String getSrch_cnsdman_nm() {
		return srch_cnsdman_nm;
	}
	public void setSrch_cnsdman_nm(String srch_cnsdman_nm) {
		this.srch_cnsdman_nm = srch_cnsdman_nm;
	}
	public String getSrch_biz_clsfcn() {
		return srch_biz_clsfcn;
	}
	public void setSrch_biz_clsfcn(String srch_biz_clsfcn) {
		this.srch_biz_clsfcn = srch_biz_clsfcn;
	}
	public String getSrch_cnclsnpurps_bigclsfcn() {
		return srch_cnclsnpurps_bigclsfcn;
	}
	public void setSrch_cnclsnpurps_bigclsfcn(String srch_cnclsnpurps_bigclsfcn) {
		this.srch_cnclsnpurps_bigclsfcn = srch_cnclsnpurps_bigclsfcn;
	}
	public String getSrch_step() {
		return srch_step;
	}
	public void setSrch_step(String srch_step) {
		this.srch_step = srch_step;
	}
	public String getSrch_state() {
		return srch_state;
	}
	public void setSrch_state(String srch_state) {
		this.srch_state = srch_state;
	}
	public String getSrch_demand_gbn() {
		return srch_demand_gbn;
	}
	public void setSrch_demand_gbn(String srch_demand_gbn) {
		this.srch_demand_gbn = srch_demand_gbn;
	}
	public String getSrch_demand_status() {
		return srch_demand_status;
	}
	public void setSrch_demand_status(String srch_demand_status) {
		this.srch_demand_status = srch_demand_status;
	}
	public String getReq_title() {
		return req_title;
	}
	public void setReq_title(String req_title) {
		this.req_title = req_title;
	}
	public String getCntrt_respman_id() {
		return cntrt_respman_id;
	}
	public void setCntrt_respman_id(String cntrt_respman_id) {
		this.cntrt_respman_id = cntrt_respman_id;
	}
	public String getCntrt_respman_nm() {
		return cntrt_respman_nm;
	}
	public void setCntrt_respman_nm(String cntrt_respman_nm) {
		this.cntrt_respman_nm = cntrt_respman_nm;
	}
	public String getCntrt_nm() {
		return cntrt_nm;
	}
	public void setCntrt_nm(String cntrt_nm) {
		this.cntrt_nm = cntrt_nm;
	}
	public String getTrgtman_dept() {
		return trgtman_dept;
	}
	public void setTrgtman_dept(String trgtman_dept) {
		this.trgtman_dept = trgtman_dept;
	}
	public String getTrgtman_in_dept() {
		return trgtman_in_dept;
	}
	public void setTrgtman_in_dept(String trgtman_in_dept) {
		this.trgtman_in_dept = trgtman_in_dept;
	}
	
}