/**
 * Project Name : 법무시스템
 * File Name : LwsCommonServiceImpl.java
 * Description : 로펌서비스 공통  ServiceImpl
 * Author : 박병주
 * Date : 2011.10
 * Copyright : 2011 by SAMSUNG. All rights reserved.
 */
package com.sec.las.lawservice.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sec.las.lawservice.dto.LwsCommonVO;
import com.sec.las.lawservice.service.LwsCommonService;

public class LwsCommonServiceImpl extends CommonServiceImpl implements LwsCommonService {
	
	/**
	* 표준 조항 권한조회
	* 
	* @param LwsCommonVO
	* @return boolean
	* @throws Exception
	*/
	public boolean authLws(String mode, LwsCommonVO lcvo, String sqlmode) throws Exception {
		
		String auth_sql = "";
		boolean result = true;
				
		//관리자가 체크
		boolean adminCheck = false;
		
		if("LAWFIRM".equals(sqlmode)){
			auth_sql = "las.lws.auth.lawfirm";
		} else if("LAWYER".equals(sqlmode)){
			auth_sql = "las.lws.auth.lawyer";
		} else if("EVENT".equals(sqlmode)){
			auth_sql = "las.lws.auth.event";
		} else if("ACPT".equals(sqlmode)){
			auth_sql = "las.lws.auth.acpt";
		} else if("REMITCERT".equals(sqlmode)){
			auth_sql = "las.lws.auth.remitcert";
		}		
		
		for(int i=0; i<lcvo.getSession_user_role_cds().size();i++){
			HashMap roleCd = (HashMap)lcvo.getSession_user_role_cds().get(i);
			if("RA00".equals((String)roleCd.get("role_cd"))){
				adminCheck = true;
			}  
		}
		
		if(lcvo.getSession_user_id().equals("S020508102712C100225") 
				|| lcvo.getSession_user_id().equals("R020218102336C109808")
				|| lcvo.getSession_user_id().equals("R020218102359C106505")
				|| lcvo.getSession_user_id().equals("R020218102336C109809")
		){
			adminCheck = true;
		}

		if(!adminCheck){
			List infoList = commonDAO.list(auth_sql,lcvo);
			if(infoList != null && infoList.size() > 0){
				ListOrderedMap info = (ListOrderedMap)infoList.get(0);
				//수정모드 일 경우
				if(MODIFY.equals(mode)){
					if(!lcvo.getSession_user_id().equals((String)info.get("reg_id"))){
						result = false;
					}
				}
				//삭제모드 일 경우
				if(DELETE.equals(mode)){
					if(!lcvo.getSession_user_id().equals((String)info.get("reg_id"))){
						result = false;
					}
				}
			}
		}
	
		return result;
	}
	
	/**
	 * 사용자 ROLE에 따른 권한 제어
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	public String checkAuthByRole(LwsCommonVO vo) throws Exception{

		/*
			시스템관리자        	RA00, AA00
			법무관리자          	RA01, AA01
			법무담당자          	RA02, AA02
			법무일반 사용자     	RA03, AA03
			cp관리자         RC01, AC01
			사업부계약관리자   RD01, AD01
			사업부계약담당자   RD02, AD02
			시스템운영자        	RM00, AM00
			일반임직원         	RZ00, AZ00 
			
			Z: CRUD
			A: CRUD
			B: R
			
			vo.getSession_blngt_orgnz()
			A00000001		// 국내법무팀
			A00000002		// 해외법무팀
			A00000003  		// IP
		*/

		String accessLevel = "";

        ArrayList roleList = vo.getSession_user_role_cds();
		ArrayList userRoleList = new ArrayList();
		
		if(roleList != null && roleList.size() > 0){
		    for(int idx = 0; idx < roleList.size(); idx++){
		        HashMap roleListMap = (HashMap)roleList.get(idx);
		        userRoleList.add((String)roleListMap.get("role_cd"));
		    }
		}
		
		if(userRoleList != null && userRoleList.size() > 0){
			if(userRoleList.contains("RA00")){
				accessLevel = "Z";
			}
			if(userRoleList.contains("RA01") || userRoleList.contains("RA02") || userRoleList.contains("RA03")){
				accessLevel = "A";
			} else {
				accessLevel = "B";
			}
		}

		return accessLevel;
	}
	
	/**
	 * 사용자 조직에 따른 권한 제어:해외 법무팀 , IP 만 접속허용(정상적이지 않은 경로로 접근했을때 Error 처리)
	 * @param LwsCommonVO
	 * @return void
	 * @throws Exception
	 */
	public void checkBasicAccessAuth(LwsCommonVO vo,String returnMessage) throws Exception {
		boolean result = false;
		String accessLevel = "";

		accessLevel = checkAuthByRole(vo);
		
		if("Z".equals(accessLevel)){
			result = true;
		} else {
			if(vo.getSession_blngt_orgnz().equals("A00000002") || vo.getSession_blngt_orgnz().equals("A00000003")){
				result = true;
			}
		}
		//양지나 한혜미,박희원,김일환는 전체 권한 
		if(vo.getSession_user_id().equals("S020508102712C100225") 
				|| vo.getSession_user_id().equals("R020218102336C109808")
				|| vo.getSession_user_id().equals("R020218102359C106505")
				|| vo.getSession_user_id().equals("R020218102336C109809")
		){
			result = true;
		}
		
		if(!result)
			throw new Exception("이 페이지에 대한 권한이 없습니다.");	
	}
	
	/**
	 * 관리자 권한을 가지는 가의 체크
	 * 
	 * @param LwsCommonVO
	 * @return void
	 * @throws Exception
	 */
	public boolean isAccessAuthAdmin(LwsCommonVO lcvo) throws Exception {
		boolean result = false;		
		for(int i=0; i<lcvo.getSession_user_role_cds().size();i++){
			HashMap roleCd = (HashMap)lcvo.getSession_user_role_cds().get(i);
			if("RA00".equals((String)roleCd.get("role_cd"))){
				result = true;
			}
		}
		//양지나 한혜미,박희원,김일환는 전체 권한 
		if(lcvo.getSession_user_id().equals("S020508102712C100225") 
				|| lcvo.getSession_user_id().equals("R020218102336C109808")
				|| lcvo.getSession_user_id().equals("R020218102359C106505")
				|| lcvo.getSession_user_id().equals("R020218102336C109809")
				) {
			result = true;
		}
			
		
		return result;
	}
}