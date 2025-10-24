/**
* Project Name : 계약관리시스템
* File Name : ManageServiceImpl.java
* Description : 계약공통목록 ServiceImpl
* Author : 신남원
* Date : 2010.09.29
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.manage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sec.clm.manage.dto.ConclusionVO;
import com.sec.clm.manage.dto.ManageVO;
import com.sec.clm.manage.service.ManageService;

public class ManageServiceImpl extends CommonServiceImpl implements ManageService {

	/**
	* 계약공통 목록조회(계약검토~종료)
	* 
	* @param RequestVO
	* @return List
	* @throws Exception
	*/
	public List listManage(ManageVO vo) throws Exception {
		List resultList = null;
		vo.setTop_role(getAccessLevel(vo));

		if("cnsdreq".equals(vo.getList_mode())){
			vo.setTop_role(getAccessLevel2(vo));
			resultList	= commonDAO.list("clm.manage.listManage1", vo);
		}else if("cnsdreq1".equals(vo.getList_mode())){
			resultList	= commonDAO.list("clm.manage.listManage3", vo);
		}else if("registration".equals(vo.getList_mode())){
			resultList	= commonDAO.list("clm.manage.listManage4", vo);
		}else{
			resultList	= commonDAO.list("clm.manage.listManage2", vo);
		}
		
		return resultList;
	}

	/**
	* 계약공통 목록조회 Legal Admin(계약검토~종료)
	* 
	* @param RequestVO
	* @return List
	* @throws Exception
	*/
	public List listManage_legalAdmin(ManageVO vo) throws Exception {
		List resultList = null;
		vo.setTop_role(getAccessLevel(vo));
		resultList	= commonDAO.list("clm.manage.listManage4_legalAdmin", vo);
		
		return resultList;
	}
	
	/**
	* MyContract 목록조회
	* 
	* @param ManageVO
	* @return List
	* @throws Exception
	*/
	public List listMyContract(ManageVO vo) throws Exception{
		List resultList = null;
		
		// 2014-07-08 Kevin added.
		if(vo.getSession_auth_comp_cd().contains("'")){
			vo.setSession_auth_comp_cd(vo.getSession_auth_comp_cd().replace("'", ""));
		}
		
		if("cnsdreq".equals(vo.getList_mode())){
			vo.setTop_role(getAccessLevel2(vo));
			resultList	= commonDAO.list("clm.manage.listManage1", vo);
		}else{
			vo.setTop_role(getAccessLevel(vo));
			resultList	= commonDAO.list("clm.manage.listManage2", vo);
		}
		
		return resultList;	
	}
	
	/**
	* StatisticsListManagelistMyContract 목록조회
	* 
	* @param ManageVO
	* @return List
	* @throws Exception
	*/
	public List StatisticsListManagelistMyContract(ManageVO vo) throws Exception{
		List resultList = null;
		
		if("cnsdreq".equals(vo.getList_mode())){
			
			
			// excel 다운 로그 할때 사용됨.
			vo.setTop_role(getAccessLevel2(vo));
			resultList	= commonDAO.list("clm.manage.listManage100", vo);
		}else{
			
			vo.setTop_role(getAccessLevel(vo));
			resultList	= commonDAO.list("clm.manage.listManage2", vo);
		}
		
		return resultList;	
	}

	/**
	* MyApproval 목록조회
	* 
	* @param ManageVO
	* @return List
	* @throws Exception
	*/
	public List listMyApproval(ManageVO vo) throws Exception{
		List resultList = null;
		vo.setTop_role(getAccessLevel(vo));

		//if("cnsdreq".equals(vo.getList_mode())){
			resultList	= commonDAO.list("clm.manage.listManageMyApproval1", vo);
		//}else{
		//	resultList	= commonDAO.list("clm.manage.listManageMyApproval2", vo);
		//}
		
		return resultList;
	}
	
	/**
	* 계약서담당자변경요청 목록조회
	* 
	* @param ManageVO
	* @return List
	* @throws Exception
	*/
	public List listRequest(ManageVO vo) throws Exception{
		List resultList = null;
		vo.setTop_role(getAccessLevel(vo));

		if("cnsdreq".equals(vo.getList_mode())){
			resultList	= commonDAO.list("clm.manage.listManageRequest1", vo);
		}else{
			resultList	= commonDAO.list("clm.manage.listManageRequest2", vo);
		}
		
		return resultList;
	}

	/**
	* 계약서담당자변경 목록조회
	* 
	* @param ManageVO
	* @return List
	* @throws Exception
	*/
	public List listChange(ManageVO vo) throws Exception{
		List resultList = null;
		vo.setTop_role(getAccessLevel(vo));

		if("cnsdreq".equals(vo.getList_mode())){
			resultList	= commonDAO.list("clm.manage.listManageChange1", vo);
		}else{
			resultList	= commonDAO.list("clm.manage.listManageChange2", vo);
		}
		
		return resultList;
	}

	/**
	* 조회권한설정
	* 
	* @param RequestVO
	* @return List
	* @throws Exception
	*/
	private String getAccessLevel(ManageVO vo) throws Exception {

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

			A:모든것들 조회 
			B:본인등록건 + 자기지역담당건 + 자기한테 배정된 건
			C:본인등록건 + 자기한테 배정된 건          
			D:본인등록건 (아무권한 없는 일반임직원) 
        */

        String result = "";
        ArrayList roleList = vo.getSession_user_role_cds();
        
        ArrayList userRoleList = new ArrayList();

        if(roleList != null && roleList.size()>0){
        	for(int i=0; i < roleList.size() ;i++){
        		HashMap hm = (HashMap)roleList.get(i);
        		String roleCd = (String)hm.get("role_cd");
        		userRoleList.add(roleCd);
        	}
        }
        
        if(userRoleList != null && userRoleList.size()>0) {
            if(userRoleList.contains("RA00") || userRoleList.contains("RC01")) {	
                result = "A";
            } else if (userRoleList.contains("RA01") || userRoleList.contains("RA02") || userRoleList.contains("RA03")) {
                result = "B";
            } else if (userRoleList.contains("RD01")) {
                result = "C";
            } else {
                result = "D";
            }
        }
        return result;
    }
	
	/**
	* 조회권한설정
	* 
	* @param RequestVO
	* @return List
	* @throws Exception
	*/
	private String getAccessLevel2(ManageVO vo) throws Exception {

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

			A:모든것들 조회 
			B:본인등록건 + 자기지역담당건 + 자기한테 배정된 건
			C:본인등록건 + 자기한테 배정된 건          
			D:본인등록건 (아무권한 없는 일반임직원)
			
			RA01    Legal Admin
            RA02    Reviewer
            RZ00    Requester

        */

        String result = "";
        ArrayList roleList = vo.getSession_user_role_cds();
        
        ArrayList userRoleList = new ArrayList();

        if(roleList != null && roleList.size()>0){
        	for(int i=0; i < roleList.size() ;i++){
        		
        		HashMap hm = (HashMap)roleList.get(i);
        		
        		String roleCd = (String)hm.get("role_cd");
        		
        		userRoleList.add(roleCd);
        	}
        }
        
        if(userRoleList != null && userRoleList.size()>0) {
            
        	if(userRoleList.contains("RA00") || userRoleList.contains("RC01") || userRoleList.contains("RA01")) {	
                result = "A";
            } else if (userRoleList.contains("RA02") || userRoleList.contains("RA03")) {
                result = "B";
            } else if (userRoleList.contains("RD01")) {
                result = "C";
            } else {
                result = "D";
            }
            
        }
        return result;
    }
	
	/**
	* 타시스템에 제공되는 계약리스트
	* 
	* @param RequestVO
	* @return List
	* @throws Exception
	*/
	public List listContract(ManageVO vo) throws Exception{
		return commonDAO.list("clm.manage.listContractPop",vo);
		
	}
	
	/**
	* IRP시스템에 제공되는 계약리스트
	* 
	* @param RequestVO
	* @return List
	* @throws Exception
	*/
	public List listContractIRP(ManageVO vo) throws Exception{
		return commonDAO.list("clm.manage.listContractIRPPop",vo);
		
	}	
	
	/**
	* 의뢰목록의 Layer에 출력되는 계약목록
	* 
	* @param HashMap
	* @return String
	* @throws Exception
	*/
	public String getCntrtHTML(HashMap hm) throws Exception{
		String result = "";
		
		String locale = (String)hm.get("locale");
		
		String tableTop = "";
		String tableCol = "";
		String tableThead = "";
		String tableTbody = "";
		String tableBottom = "";
		
		tableTop += "<div class='option_bg'>";
		tableTop += "<div class='option_bg_btl'>";
		tableTop += "<div class='option_bg_tpr'>";
		tableTop += "<div class='option_bg_btr'>";
		
		tableTop += "<table cellspacing='0' cellpadding='0' border='0' width='550px' class='table-style_sub'>";

		tableCol += "<colgroup><col width='430px' /><col width='50px' /><col width='70px' /></colgroup>";
		
		String title1 = messageSource.getMessage("clm.page.field.manageCommon.cntrtNm", null, new Locale(locale));
		String title2 = messageSource.getMessage("clm.page.field.manageCommon.step", null, new Locale(locale));
		String title3 = messageSource.getMessage("clm.page.field.manageCommon.status", null, new Locale(locale));
			
		tableThead = "<thead><tr><td style='width:400px;'><nobr>"+title1+"</nobr></td><td style='width:50px;'><nobr>"+title2+"</nobr></td><td style='width:70px;'><nobr>"+title3+"</nobr></td></tr></thead>";
		
		List resultList = commonDAO.list("clm.manage.listContractLayer", hm);
		
		if(resultList != null && resultList.size()>0){
			for(int i=0; i<resultList.size(); i++){
				ListOrderedMap lom = (ListOrderedMap)resultList.get(i);
				
				String cntrtNm = (String)lom.get("cntrt_nm");
				String prcsDepth = (String)lom.get("prcs_depth_nm");
				String depthStatus = (String)lom.get("depth_status_nm");
				
				tableTbody += "<tbody><tr><td class='tL txtover pL10' title='"+cntrtNm+"'>"+cntrtNm+"</td><td><nobr>"+prcsDepth+"</nobr></td><td><nobr>"+depthStatus+"</nobr></td></tr></tbody>";
			}
			
		}else{
			String message = messageSource.getMessage("secfw.msg.succ.noResult", null, new Locale(locale));
			tableTbody = "<tr><td colspan='3'>"+message+"</td></tr>";
		}

		tableBottom += "</table>";
		tableBottom += "</div>";
		tableBottom += "</div>";
		tableBottom += "</div>";
		tableBottom += "</div>";

		result = tableTop + tableThead + tableTbody + tableBottom;
		
		return result;
	}
	
	/**
	* 타시스템에 제공되는 계약리스트
	* 
	* @param RequestVO
	* @return List
	* @throws Exception
	*/
	public List listType(ManageVO vo) throws Exception{
		return commonDAO.list("clm.manage.listType",vo);
		
	}
	
	/**
	 * 의뢰현황 엑셀다운 
	 */
	public List listConsiderationExcel(ManageVO vo) throws Exception{
		List resultList = null;
		resultList	= commonDAO.list("clm.manage.listConsiderationExcel", vo);
		return resultList;	
	}

	/**
	* 기간연장 승인 목록조회
	* 
	* @param ManageVO
	* @return List
	* @throws Exception
	*/
	public List listAutoRenewApproval(ManageVO vo) throws Exception{
		List resultList = null;
		vo.setTop_role(getAccessLevel(vo));

		if("cnsdreq".equals(vo.getList_mode())){
			resultList	= commonDAO.list("clm.manage.listManageAutoRenewApproval1", vo);
		}else{
			resultList	= commonDAO.list("clm.manage.listManageAutoRenewApproval2", vo);
		}
		
		return resultList;
	}
	
	/**
	* 체결후등록 승인 목록조회
	* 
	* @param ManageVO
	* @return List
	* @throws Exception
	*/
	public List listRegistrationApproval(ManageVO vo) throws Exception {
		List resultList = null;
		vo.setTop_role(getAccessLevel(vo));

		resultList	= commonDAO.list("clm.manage.listManageRegistrationApproval", vo);
		
		return resultList;
	} 
	
	/**
	* 표준계약서목록조회
	* 
	* @param ManageVO
	* @return List
	* @throws Exception
	*/
	public List listStdContract(ManageVO vo) throws Exception{
		List resultList = null;
		vo.setTop_role(getAccessLevel(vo));

		if("cnsdreq".equals(vo.getList_mode())){
			resultList	= commonDAO.list("clm.manage.listStd1", vo);
		}else{
			resultList	= commonDAO.list("clm.manage.listStd2", vo);
		}
		
		return resultList;
	}
	
	/**
	 * 의뢰현황 (사업장 구분별)
	 */
	public List listConsiderationMnCnsdDept(ManageVO vo) throws Exception{
		List resultList = null;
		resultList	= commonDAO.list("clm.manage.listConsiderationCnsdDept", vo);
		return resultList;	
	}
	
	/**
	 * 의뢰현황 (사업별)
	 */
	public List listConsiderationMnCnsdOrgnz(ManageVO vo) throws Exception{
		List resultList = null;
		resultList	= commonDAO.list("clm.manage.listConsiderationCnsdOrgnz", vo);
		return resultList;	
	}

	@Override
	public ConclusionVO getConclusionVObyKeyId(String key_id,String sysNm) throws Exception {
		
		ConclusionVO conclusionVO = new ConclusionVO();
		
		HashMap hm = new HashMap();
		hm.put("key_id", key_id);
		hm.put("sys_nm", sysNm);
		
		List resultList = commonDAO.list("common.legacyintf.getCntrtInfoFromInfContractMach", hm);

		
		if(resultList != null && resultList.size()>0){
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);			
			conclusionVO.setCntrt_id((String)lom.get("cntrt_id"));
			conclusionVO.setCntrt_nm((String)lom.get("cntrt_nm"));
			conclusionVO.setCntrt_oppnt_nm((String)lom.get("cntrt_oppnt_nm"));
			conclusionVO.setCntrt_amt((String)lom.get("cntrt_amt"));
			conclusionVO.setCrrncy_unit((String)lom.get("crrncy_unit"));
		}		
		
		
		return conclusionVO;
	}

	
}