/**
* Project Name : 계약관리시스템
* File Name :InterfaceManageService.java
* Description : 인터페이스테이블정보관리 Service interface
* Author : 심주완
* Date : 2011.10.05
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.manage.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

public interface InterfaceManageService {
	//인터페이스작업[일반]
	public void insertInterfaceInfo(String cntrt_id, String ip_address) throws Exception;
	
	//인터페이스작업[법무-검토회신]
	public void insertInterfaceInfoCnsd(String cnsdreq_id, String cntrt_id, String plndbn_req_yn, String ip_address,String standard_contract_status) throws Exception;
		
	//인터페이스작업[SSEMS로 계약정보 보내기]
	public JSONArray insertInterfaceInfoForSSEMS(String cntrt_id, String cntrt_no, String ssems_oppnt_cd, String ip_addressm, HttpServletRequest request) throws Exception;

	//인터페이스작업[IRP로 계약정보 보내기]
	public JSONArray insertInterfaceInfoForIRP(String cntrt_id,  String key_id, String key_nm, String pre_cntrt_id, String checked_sys_nm, HttpServletRequest request) throws Exception;
	
	//gais인터페이스
	public HashMap insertGaisInfo(String cnsdreq_id, String cntrt_id, String create_date) throws Exception;

	//법무lte 날인관리 인터페이스	
	public HashMap insertPrtbInfo(String cnsdreq_id, String cntrt_id, String create_date) throws Exception;
}