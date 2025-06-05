/**
* Project Name : 계약관리시스템
* File Name :ChooseContractVO.java
* Description : 계약선택팝업 VO
* Author : 심주완
* Date : 2011.09.25
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.manage.dto;

import java.util.List;
import com.sds.secframework.common.dto.CommonVO;

public class ChooseContractVO extends CommonVO {
	
	/**********************************************************************
	 * DB 변수-대상테이블(TN_CLM_CONT_CNSDREQ,TN_CLM_CONTRACT_CNSD,TN_CLM_CONTRACT_MASTER); 
	 **********************************************************************/
	private String cnsdreq_id;					//검토의뢰아이디
	private String cntrt_id;					//계약아이디
	private String cntrt_nm;					//계약명
	private String region_gbn;					//지역구분
	private String biz_clsfcn;					//비즈니스분류
	private String depth_clsfcn;				//단계분류
	private String cnclsnpurps_bigclsfcn;		//체결목적대분류
	private String cnclsnpurps_midclsfcn;		//체결목적중분류
	private String cnclsnpurps_midclsfcn_etc;	//체결목적중분류기타
	private String[] cntrt_id_arr;
	private String[] approval_yn_arr;
	
	public String getCnsdreq_id() {
		return cnsdreq_id;
	}
	public void setCnsdreq_id(String cnsdreq_id) {
		this.cnsdreq_id = cnsdreq_id;
	}
	public String getCntrt_id() {
		return cntrt_id;
	}
	public void setCntrt_id(String cntrt_id) {
		this.cntrt_id = cntrt_id;
	}
	public String getCntrt_nm() {
		return cntrt_nm;
	}
	public void setCntrt_nm(String cntrt_nm) {
		this.cntrt_nm = cntrt_nm;
	}
	public String getRegion_gbn() {
		return region_gbn;
	}
	public void setRegion_gbn(String region_gbn) {
		this.region_gbn = region_gbn;
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
	public String getCnclsnpurps_midclsfcn_etc() {
		return cnclsnpurps_midclsfcn_etc;
	}
	public void setCnclsnpurps_midclsfcn_etc(String cnclsnpurps_midclsfcn_etc) {
		this.cnclsnpurps_midclsfcn_etc = cnclsnpurps_midclsfcn_etc;
	}
	public String[] getCntrt_id_arr(){
		return cntrt_id_arr;
	}
	public void setCntrt_id_arr(String[] cntrt_id_arr){
		this.cntrt_id_arr = cntrt_id_arr;
	}
	public String[] getApproval_yn_arr(){
		return approval_yn_arr;
	}
	public void setApproval_yn_arr(String[] approval_yn_arr){
		this.approval_yn_arr = approval_yn_arr;
	}
	
}