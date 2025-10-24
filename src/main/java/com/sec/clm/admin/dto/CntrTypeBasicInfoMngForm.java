/**
 * Project Name : 계약관리체계 강화 프로젝트
 * File name	: CntrTypeBasicInfoMngForm.java
 * Description	: 계약유형별 속성관리 Data Form(Model), 화면-서비스 로직 연결
 * Author		: 김형준
 * Date			: 2011. 09. 01
 * Copyright	:   
 */
package com.sec.clm.admin.dto;

import com.sds.secframework.common.dto.CommonForm;

/**
 * Description	: Data Form(Model), 화면-서비스 로직 연결
 * Author		: 김형준
 * Date			: 2011. 09. 01
 */
public class CntrTypeBasicInfoMngForm extends CommonForm {
	
	/**********************************************
	 * 검색 조건
	 **********************************************/
	/** 비즈니스 분류 */
	private String srch_biz_clsfcn;
	/** 단계 분류 */
	private String srch_depth_clsfcn;
	/** 체결목적 - 대분류 */
	private String srch_cnclsnpurps_bigclsfcn;
	/** 체결목적 - 중분류 */
	private String srch_cnclsnpurps_midclsfcn;
	
	/**********************************************
	 * iframe 부분 검색 위한 조건
	 **********************************************/
	/** 비즈니스 분류 */
	private String[] biz_clsfcn_arr;
	/** 단계 분류 */
	private String[] depth_clsfcn_arr;
	/** 체결목적 - 대분류 */
	private String[] cnclsnpurps_bigclsfcn_arr;
	/** 체결목적 - 중분류 */
	private String[] cnclsnpurps_midclsfcn_arr;
	/** 필수 여부 */
	private String[] mndtry_yn_arr;
	/** 일련 번호 */
	private String[] attr_seqno_arr;
	
	/**********************************************
	 * 입력값	 
	 **********************************************/
	/** 비즈니스 분류 */
	private String biz_clsfcn;
	/** 단계 분류 */
	private String depth_clsfcn;
	/** 체결목적 - 대분류 */
	private String cnclsnpurps_bigclsfcn;
	/** 체결목적 - 중분류 */
	private String cnclsnpurps_midclsfcn;
	/** 필수 여부 */
	private String mndtry_yn;
	/** 일련 번호 */
	private String attr_seqno;
	
	/**********************************************
	 * getter, setter 메소드
	 **********************************************/

	public String getSrch_biz_clsfcn() {
		return srch_biz_clsfcn;
	}
	public void setSrch_biz_clsfcn(String srch_biz_clsfcn) {
		this.srch_biz_clsfcn = srch_biz_clsfcn;
	}
	public String getSrch_depth_clsfcn() {
		return srch_depth_clsfcn;
	}
	public void setSrch_depth_clsfcn(String srch_depth_clsfcn) {
		this.srch_depth_clsfcn = srch_depth_clsfcn;
	}
	public String getSrch_cnclsnpurps_bigclsfcn() {
		return srch_cnclsnpurps_bigclsfcn;
	}
	public void setSrch_cnclsnpurps_bigclsfcn(String srch_cnclsnpurps_bigclsfcn) {
		this.srch_cnclsnpurps_bigclsfcn = srch_cnclsnpurps_bigclsfcn;
	}
	public String getSrch_cnclsnpurps_midclsfcn() {
		return srch_cnclsnpurps_midclsfcn;
	}
	public void setSrch_cnclsnpurps_midclsfcn(String srch_cnclsnpurps_midclsfcn) {
		this.srch_cnclsnpurps_midclsfcn = srch_cnclsnpurps_midclsfcn;
	}
	public String getBiz_clsfcn() {
		return biz_clsfcn;
	}
	public void setBiz_clsfcn(String biz_clsfcn) {
		this.biz_clsfcn = biz_clsfcn;
	}
	public String getDepth_clsfcn() {
		return depth_clsfcn;
	}
	public void setDepth_clsfcn(String depth_clsfcn) {
		this.depth_clsfcn = depth_clsfcn;
	}
	public String getCnclsnpurps_bigclsfcn() {
		return cnclsnpurps_bigclsfcn;
	}
	public void setCnclsnpurps_bigclsfcn(String cnclsnpurps_bigclsfcn) {
		this.cnclsnpurps_bigclsfcn = cnclsnpurps_bigclsfcn;
	}
	public String getCnclsnpurps_midclsfcn() {
		return cnclsnpurps_midclsfcn;
	}
	public void setCnclsnpurps_midclsfcn(String cnclsnpurps_midclsfcn) {
		this.cnclsnpurps_midclsfcn = cnclsnpurps_midclsfcn;
	}
	public String getMndtry_yn() {
		return mndtry_yn;
	}
	public void setMndtry_yn(String mndtry_yn) {
		this.mndtry_yn = mndtry_yn;
	}
	public String[] getBiz_clsfcn_arr() {
		return biz_clsfcn_arr;
	}
	public void setBiz_clsfcn_arr(String[] biz_clsfcn_arr) {
		this.biz_clsfcn_arr = biz_clsfcn_arr;
	}
	public String[] getDepth_clsfcn_arr() {
		return depth_clsfcn_arr;
	}
	public void setDepth_clsfcn_arr(String[] depth_clsfcn_arr) {
		this.depth_clsfcn_arr = depth_clsfcn_arr;
	}
	public String[] getCnclsnpurps_bigclsfcn_arr() {
		return cnclsnpurps_bigclsfcn_arr;
	}
	public void setCnclsnpurps_bigclsfcn_arr(String[] cnclsnpurps_bigclsfcn_arr) {
		this.cnclsnpurps_bigclsfcn_arr = cnclsnpurps_bigclsfcn_arr;
	}
	public String[] getCnclsnpurps_midclsfcn_arr() {
		return cnclsnpurps_midclsfcn_arr;
	}
	public void setCnclsnpurps_midclsfcn_arr(String[] cnclsnpurps_midclsfcn_arr) {
		this.cnclsnpurps_midclsfcn_arr = cnclsnpurps_midclsfcn_arr;
	}
	public String[] getMndtry_yn_arr() {
		return mndtry_yn_arr;
	}
	public void setMndtry_yn_arr(String[] mndtry_yn_arr) {
		this.mndtry_yn_arr = mndtry_yn_arr;
	}
	public String[] getAttr_seqno_arr() {
		return attr_seqno_arr;
	}
	public void setAttr_seqno_arr(String[] attr_seqno_arr) {
		this.attr_seqno_arr = attr_seqno_arr;
	}
	public String getAttr_seqno() {
		return attr_seqno;
	}
	public void setAttr_seqno(String attr_seqno) {
		this.attr_seqno = attr_seqno;
	}

	

}
