/**
* Project Name : 계약관리시스템
* File Name : ManageForm.java
* Description : 계약 공통 목록Form
* Author : 신남원
* Date : 2010.09.29
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.manage.dto;

import com.sds.secframework.common.dto.CommonVO;

import java.util.List;

public class ManageVO extends CommonVO {
	/**********************************************************************
	 * DB 변수
	 **********************************************************************/
	/**********************************************************************
	 * DB 외 변수
	 **********************************************************************/
	/**상태 구분자*/
	private String status_mode;
	/**리스트 구분자*/
	private String list_mode;
	/**검색 제목*/
	private String srch_review_title; 
	/**검색 계약명*/
	private String srch_cntrt_nm;
	/** 검색 계약번호**/
	private String srch_cntrt_no;
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
	/**체결시작일*/
	private String srch_start_cnlsnday;
	/**체결종료일*/
	private String srch_end_cnlsnday;
	private String srch_if_sys_cd;			// 연계 시스템 검색
	
	private String isOrgMgr; //계약변경 구분자
	
	/** 상세 검색 시작 **/
	
	/**상세 검색 - 유형 조건 **/
	private List srch_lSrch_Type_cd;
	
	
	/**상세 검색 - 대유형 조건 **/
	private List srch_lsrch_Ltype_cd;
	
	/**상세 검색 - 중유형 조건 **/
	private List srch_lsrch_Mtype_cd;
	
	/**상세 검색 - 소유형 조건 **/
	private List srch_lsrch_Stype_cd;
	
	/**상세 검색 - 대유형 조건 String Type **/
	private String srch_sSrch_Ltype_cd;
	
	/**상세 검색 - 중유형 조건  String Type**/
	private String srch_sSrch_Mtype_cd;
	
	/**상세 검색 - 소유형 조건  String Type **/
	private String srch_sSrch_Stype_cd;
	
	/**상세 검색 - 계약 대상 상세 **/
	private String srch_cntrt_trgt_det2;
	
	/**상세 검색 - 지불/수불 조건 **/
	private List srch_lPayment_gbn;
	
	private String srch_sPayment_gbn;
	
	/**상세 검색 - 사전품의 승인자 **/
	private String srch_bfhdcstn_apbtman_nm;
	
	/**상세 검색 - 계약 Ower **/
	private List srch_lMn_cnsd_dept;
	
	private String srch_sMn_cnsd_dept;
	
	private List srch_mn_cnsd_dept;
	
	/**상세 검색 - 체결 예정 계약서 최종확인일 시작 **/
	private String srch_str_org_acptday;
	
	/**상세 검색 - 체결 예정 계약서 최종확인일 종료 **/
	private String srch_end_org_acptday;
	
	/**상세 검색 - 자동 갱신여부 **/
	private String srch_auto_rnew_yn;
	
	private List srch_lAuto_rnew_yn;
	
	/**상세 검색 - 날인방식 **/
	private List srch_lSeal_mthd;
	
	private String srch_sSeal_mthd;
	
	/**상세 검색 - 서명자 정보 **/
	private String srch_signman_nm;
	
	/**상세 검색 - 준거법 **/
	private List srch_lLoac;
	
	private String srch_sLoac;
	
	/**상세 검색 - 분쟁해결방법 **/
	private List srch_lDesp_resolt_mthd;
	
	private String srch_sDesp_resolt_mthd;
	
	private String srch_mn_cnsd;
	
	private String dept_gbn;
	
	/** 상세 검색  종료 끝 **/
	
	private String isExcelFlag;		// 엑셀다운로드 여부
	
	private String arg;
	
	// 전자 변호사일 경우 회사를 선택할 수 있는 검색 조건 
	private String sElComp;
	
	// 전자 변호사일 경우 소속된 계약을 볼 수 있어야 한다.
	private String elUserlYn;
	
	// 계약담당자수정 권한에 따른 조회 조건 
	private String request_Yn;
	
	// 상세화면의 계약ID
	private String except_cntrt_id;
	
	// closed_yn : Regal Admin의 종료여부(구주 추가)
	private String closed_yn;
	
	// comp_cd : 2014-03-26 신성우 법인조회용 추가
	private String comp_cd;
	
	/** Sungwoo added G-Erp Search option 2014-06-10 **/
	// srch_division : gerp name 조회
	private String srch_division;

	// srch_vendor_type : vendor type 조회
	private String srch_vendor_type;

	// srch_vendor_type_detail : vendor type code 조회
	private String srch_vendor_type_detail;
	
	//HQ검토 진행상태
	private String hq_cnsd_status;
	/**********************************************************************
	 * Getters and Setters Method
	 **********************************************************************/
	
	private String srch_tnc_no;

	public String getComp_cd() {
		return comp_cd;
	}

	public void setComp_cd(String comp_cd) {
		this.comp_cd = comp_cd;
	}
	
	public String getRequest_Yn() {
		return request_Yn;
	}

	public void setRequest_Yn(String request_Yn) {
		this.request_Yn = request_Yn;
	}
	
	public String getArg() {
		return arg;
	}

	public String getElUserlYn() {
		return elUserlYn;
	}

	public void setElUserlYn(String elUserlYn) {
		this.elUserlYn = elUserlYn;
	}

	public String getSElComp() {
		return sElComp;
	}
	public void setSElComp(String sElComp) {
		this.sElComp = sElComp;
	}

	public void setArg(String arg) {
		this.arg = arg;
	}
	public String getStatus_mode() {
		return status_mode;
	}
	public List getSrch_lSrch_Type_cd() {
		return srch_lSrch_Type_cd;
	}
	public void setSrch_lSrch_Type_cd(List srch_lSrch_Type_cd) {
		this.srch_lSrch_Type_cd = srch_lSrch_Type_cd;
	}
	public List getSrch_lsrch_Ltype_cd() {
		return srch_lsrch_Ltype_cd;
	}
	public void setSrch_lsrch_Ltype_cd(List srch_lsrch_Ltype_cd) {
		this.srch_lsrch_Ltype_cd = srch_lsrch_Ltype_cd;
	}
	public List getSrch_lsrch_Mtype_cd() {
		return srch_lsrch_Mtype_cd;
	}
	public void setSrch_lsrch_Mtype_cd(List srch_lsrch_Mtype_cd) {
		this.srch_lsrch_Mtype_cd = srch_lsrch_Mtype_cd;
	}
	public List getSrch_lsrch_Stype_cd() {
		return srch_lsrch_Stype_cd;
	}
	public void setSrch_lsrch_Stype_cd(List srch_lsrch_Stype_cd) {
		this.srch_lsrch_Stype_cd = srch_lsrch_Stype_cd;
	}
	public String getSrch_sSrch_Ltype_cd() {
		return srch_sSrch_Ltype_cd;
	}
	public void setSrch_sSrch_Ltype_cd(String srch_sSrch_Ltype_cd) {
		this.srch_sSrch_Ltype_cd = srch_sSrch_Ltype_cd;
	}
	public String getSrch_sSrch_Mtype_cd() {
		return srch_sSrch_Mtype_cd;
	}
	public void setSrch_sSrch_Mtype_cd(String srch_sSrch_Mtype_cd) {
		this.srch_sSrch_Mtype_cd = srch_sSrch_Mtype_cd;
	}
	public String getSrch_sSrch_Stype_cd() {
		return srch_sSrch_Stype_cd;
	}
	public void setSrch_sSrch_Stype_cd(String srch_sSrch_Stype_cd) {
		this.srch_sSrch_Stype_cd = srch_sSrch_Stype_cd;
	}
	public String getSrch_cntrt_trgt_det2() {
		return srch_cntrt_trgt_det2;
	}
	public void setSrch_cntrt_trgt_det2(String srch_cntrt_trgt_det2) {
		this.srch_cntrt_trgt_det2 = srch_cntrt_trgt_det2;
	}
	public List getSrch_lPayment_gbn() {
		return srch_lPayment_gbn;
	}
	public void setSrch_lPayment_gbn(List srch_lPayment_gbn) {
		this.srch_lPayment_gbn = srch_lPayment_gbn;
	}
	public String getSrch_sPayment_gbn() {
		return srch_sPayment_gbn;
	}
	public void setSrch_sPayment_gbn(String srch_sPayment_gbn) {
		this.srch_sPayment_gbn = srch_sPayment_gbn;
	}
	public String getSrch_bfhdcstn_apbtman_nm() {
		return srch_bfhdcstn_apbtman_nm;
	}
	public void setSrch_bfhdcstn_apbtman_nm(String srch_bfhdcstn_apbtman_nm) {
		this.srch_bfhdcstn_apbtman_nm = srch_bfhdcstn_apbtman_nm;
	}
	public List getSrch_lMn_cnsd_dept() {
		return srch_lMn_cnsd_dept;
	}
	public void setSrch_lMn_cnsd_dept(List srch_lMn_cnsd_dept) {
		this.srch_lMn_cnsd_dept = srch_lMn_cnsd_dept;
	}
	public String getSrch_sMn_cnsd_dept() {
		return srch_sMn_cnsd_dept;
	}
	public void setSrch_sMn_cnsd_dept(String srch_sMn_cnsd_dept) {
		this.srch_sMn_cnsd_dept = srch_sMn_cnsd_dept;
	}
	public List getSrch_mn_cnsd_dept() {
		return srch_mn_cnsd_dept;
	}
	public void setSrch_mn_cnsd_dept(List srch_mn_cnsd_dept) {
		this.srch_mn_cnsd_dept = srch_mn_cnsd_dept;
	}
	public String getSrch_str_org_acptday() {
		return srch_str_org_acptday;
	}
	public void setSrch_str_org_acptday(String srch_str_org_acptday) {
		this.srch_str_org_acptday = srch_str_org_acptday;
	}
	public String getSrch_end_org_acptday() {
		return srch_end_org_acptday;
	}
	public void setSrch_end_org_acptday(String srch_end_org_acptday) {
		this.srch_end_org_acptday = srch_end_org_acptday;
	}
	public String getSrch_auto_rnew_yn() {
		return srch_auto_rnew_yn;
	}
	public void setSrch_auto_rnew_yn(String srch_auto_rnew_yn) {
		this.srch_auto_rnew_yn = srch_auto_rnew_yn;
	}
	public List getSrch_lAuto_rnew_yn() {
		return srch_lAuto_rnew_yn;
	}
	public void setSrch_lAuto_rnew_yn(List srch_lAuto_rnew_yn) {
		this.srch_lAuto_rnew_yn = srch_lAuto_rnew_yn;
	}
	public List getSrch_lSeal_mthd() {
		return srch_lSeal_mthd;
	}
	public void setSrch_lSeal_mthd(List srch_lSeal_mthd) {
		this.srch_lSeal_mthd = srch_lSeal_mthd;
	}
	public String getSrch_sSeal_mthd() {
		return srch_sSeal_mthd;
	}
	public void setSrch_sSeal_mthd(String srch_sSeal_mthd) {
		this.srch_sSeal_mthd = srch_sSeal_mthd;
	}
	public String getSrch_signman_nm() {
		return srch_signman_nm;
	}
	public void setSrch_signman_nm(String srch_signman_nm) {
		this.srch_signman_nm = srch_signman_nm;
	}
	public List getSrch_lLoac() {
		return srch_lLoac;
	}
	public void setSrch_lLoac(List srch_lLoac) {
		this.srch_lLoac = srch_lLoac;
	}
	public String getSrch_sLoac() {
		return srch_sLoac;
	}
	public void setSrch_sLoac(String srch_sLoac) {
		this.srch_sLoac = srch_sLoac;
	}
	public List getSrch_lDesp_resolt_mthd() {
		return srch_lDesp_resolt_mthd;
	}
	public void setSrch_lDesp_resolt_mthd(List srch_lDesp_resolt_mthd) {
		this.srch_lDesp_resolt_mthd = srch_lDesp_resolt_mthd;
	}
	public String getSrch_sDesp_resolt_mthd() {
		return srch_sDesp_resolt_mthd;
	}
	public void setSrch_sDesp_resolt_mthd(String srch_sDesp_resolt_mthd) {
		this.srch_sDesp_resolt_mthd = srch_sDesp_resolt_mthd;
	}
	public String getSrch_start_cnlsnday() {
		return srch_start_cnlsnday;
	}
	public void setSrch_start_cnlsnday(String srch_start_cnlsnday) {
		this.srch_start_cnlsnday = srch_start_cnlsnday;
	}
	public String getSrch_end_cnlsnday() {
		return srch_end_cnlsnday;
	}
	public void setSrch_end_cnlsnday(String srch_end_cnlsnday) {
		this.srch_end_cnlsnday = srch_end_cnlsnday;
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
	public String getIsExcelFlag() {
		return isExcelFlag;
	}
	public void setIsExcelFlag(String isExcelFlag) {
		this.isExcelFlag = isExcelFlag;
	}
	public String getSrch_if_sys_cd() {
		return srch_if_sys_cd;
	}
	public void setSrch_if_sys_cd(String srch_if_sys_cd) {
		this.srch_if_sys_cd = srch_if_sys_cd;
	}
	public String getSrch_cntrt_nm() {
		return srch_cntrt_nm;
	}
	public void setSrch_cntrt_nm(String srch_cntrt_nm) {
		this.srch_cntrt_nm = srch_cntrt_nm;
	}
	public String getSrch_cntrt_no() {
		return srch_cntrt_no;
	}
	public void setSrch_cntrt_no(String srch_cntrt_no) {
		this.srch_cntrt_no = srch_cntrt_no;
	}
	public String getIsOrgMgr() {
		return isOrgMgr;
	}
	public void setIsOrgMgr(String isOrgMgr) {
		this.isOrgMgr = isOrgMgr;
	}
	public String getSrch_mn_cnsd() {
		return srch_mn_cnsd;
	}
	public void setSrch_mn_cnsd(String srch_mn_cnsd) {
		this.srch_mn_cnsd = srch_mn_cnsd;
	}
	public String getDept_gbn() {
		return dept_gbn;
	}
	public void setDept_gbn(String dept_gbn) {
		this.dept_gbn = dept_gbn;
	}
	
	public String getExcept_cntrt_id() {
		return except_cntrt_id;
	}
	public void setExcept_cntrt_id(String except_cntrt_id) {
		this.except_cntrt_id = except_cntrt_id;
	}

	public String getClosed_yn() {
		return closed_yn;
	}

	public void setClosed_yn(String closed_yn) {
		this.closed_yn = closed_yn;
	}

	public String getsElComp() {
		return sElComp;
	}

	public void setsElComp(String sElComp) {
		this.sElComp = sElComp;
	}

	public String getSrch_division() {
		return srch_division;
	}

	public void setSrch_division(String srch_division) {
		this.srch_division = srch_division;
	}

	public String getSrch_vendor_type() {
		return srch_vendor_type;
	}

	public void setSrch_vendor_type(String srch_vendor_type) {
		this.srch_vendor_type = srch_vendor_type;
	}

	public String getSrch_vendor_type_detail() {
		return srch_vendor_type_detail;
	}

	public void setSrch_vendor_type_detail(String srch_vendor_type_detail) {
		this.srch_vendor_type_detail = srch_vendor_type_detail;
	}
	public String getHq_cnsd_status() {
		return hq_cnsd_status;
	}
	public void setHq_cnsd_status(String hq_cnsd_status) {
		this.hq_cnsd_status = hq_cnsd_status;
	}

	public String getSrch_tnc_no() {
		return srch_tnc_no;
	}

	public void setSrch_tnc_no(String srch_tnc_no) {
		this.srch_tnc_no = srch_tnc_no;
	}
	
}