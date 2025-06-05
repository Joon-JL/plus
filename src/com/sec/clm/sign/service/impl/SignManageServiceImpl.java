/**
 * Project Name : 법무시스템
 * File Name : SignServiceImpl.java
 * Description : 날인 ServiceImpl
 * Author : 박병주
 * Date : 2013.05
 * Copyright : 2013 by SAMSUNG. All rights reserved.
 */
package com.sec.clm.sign.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sec.common.clmsfile.dto.ClmsFileVO;
import com.sec.clm.sign.dto.SignManageVO;
import com.sec.clm.sign.dto.SignManageVO;
import com.sec.clm.sign.service.SignManageService;
import com.sec.las.lawservice.dto.LwsCommonVO;

public class SignManageServiceImpl extends CommonServiceImpl implements SignManageService {
	
	/**
	 * ROLE 구분을 위한 변수
	 */
	static String SEAL = "SC0101"; // 날인관리
	static String DOC = "SC0102"; // 증명서류 발급 관리
	
	/**
	 * 날인 목록 조회
	 * @param SignManageVO
	 * @return List
	 * @throws Exception
	 */
	public List listSign(SignManageVO vo) throws Exception {		
		
		getLogger().info("[INFO] SignManageServiceImpl.listSign");
		
		return commonDAO.list("clm.sign.list", vo);
	}
	
	/**
	 * 날인 등록
	 * @param SignManageVO
	 * @return int
	 * @throws Exception
	 */
	public String insertSign(SignManageVO vo) throws Exception {	
		
		getLogger().info("[INFO] SignManageServiceImpl.insertSign");
		
		List resultList = commonDAO.list("clm.sign.getseq", vo);
		BigDecimal dec_key = new BigDecimal(0);
		String ref_key = "";
		
		if (resultList != null && resultList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			dec_key = (BigDecimal)lom.get("SSEQ");
			ref_key = dec_key.toString();
		} 	
		
		vo.setApl_sqn(ref_key);
		commonDAO.insert("clm.sign.insert", vo);
		
		// 첨부파일 저장
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(ref_key);
		fvo.setFileInfos(vo.getFileInfos());
		fvo.setReg_id(vo.getSession_user_id());
		
		clmsFileService.mngClmsAttachFile(fvo);	
		
		commonDAO.insert("clm.sign.aftermodify", vo);
		
		return ref_key;
	}
	
	/**
	 * 날인 수정
	 * @param SignManageVO
	 * @return int
	 * @throws Exception
	 */
	public int modifySign(SignManageVO vo) throws Exception {	
		
		getLogger().info("[INFO] SignManageServiceImpl.modifySign");
		
		// 첨부파일 저장
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(vo.getApl_sqn());
		fvo.setFileInfos(vo.getFileInfos());
		fvo.setReg_id(vo.getSession_user_id());

		clmsFileService.mngClmsAttachFile(fvo);	
		
		return commonDAO.modify("clm.sign.modify", vo);
	}
	
	/**
	 * 날인 처리
	 * @param SignManageVO
	 * @return int
	 * @throws Exception
	 */
	public int signAction(String modeSQL,SignManageVO vo) throws Exception {		
		getLogger().info("[INFO] SignManageServiceImpl.signAction");
		return commonDAO.modify(modeSQL, vo);
	}
	
	/**
	 * 날인 담당자 수정
	 * @param SignManageVO
	 * @return int
	 * @throws Exception
	 */
	public int modifySignMan(SignManageVO vo) throws Exception {
		getLogger().info("[INFO] SignManageServiceImpl.modifySignMan");
		int s_num = 0;
				
		// 담당자 변경이력 등록 처리 
		s_num = commonDAO.insert("clm.sign.modify.mngmanhis", vo);
		
		if(s_num==1){
			commonDAO.modify("clm.sign.modify.mngman", vo);
		}
		
		return s_num;
	}
	
	/**
	 * 날인 삭제
	 * @param SignManageVO
	 * @return int
	 * @throws Exception
	 */
	public int deleteSign(SignManageVO vo) throws Exception {
		getLogger().info("[INFO] SignManageServiceImpl.deleteSign");
        ClmsFileVO fvo = new ClmsFileVO(); 

        fvo.setSys_cd(vo.getSys_cd());
        fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
        fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
        fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
        fvo.setRef_key((vo.getApl_sqn()));
        fvo.setFileInfos(vo.getFileInfos());
        fvo.setDel_id(vo.getSession_user_id());        

        clmsFileService.delClmsAttachFile(fvo);
		
		 return commonDAO.delete("clm.sign.delete", vo);
	}
	
	/**
	 * 날인 상세
	 * @param SignManageVO
	 * @return List
	 * @throws Exception
	 */
	public List detailSign(SignManageVO vo) throws Exception {
		getLogger().info("[INFO] SignManageServiceImpl.detailSign");
		return commonDAO.list("clm.sign.detail", vo);
	}
	
	/**
	 * 날인 상세_첨부파일 취득
	 * @param SignManageVO
	 * @return List
	 * @throws Exception
	 */
	public List getAttachList(SignManageVO vo) throws Exception {
		getLogger().info("[INFO] SignManageServiceImpl.getAttachList");
		return commonDAO.list("clm.sign.getAttachLst", vo);
	}
	
	/**
	 * 승인이력
	 * @param SignManageVO
	 * @return List
	 * @throws Exception
	 */
	public List listSignAppr(SignManageVO vo) throws Exception {
		getLogger().info("[INFO] SignManageServiceImpl.listSignAppr");
		return commonDAO.list("clm.sign.getApprLstTypeA", vo);
	}
	
	/**
	 * 날인 상태 수정(품의상신 성공시)
	 * @param SignManageVO
	 * @return int
	 * @throws Exception
	 */
	public int modifySignStatus(SignManageVO vo) throws Exception{	
		getLogger().info("[INFO] SignManageServiceImpl.modifySignStatus");
		 return commonDAO.delete("clm.sign.modifySignStatus", vo);
	}
	
	/**
	*  의뢰자/배정자 권한 체크  (수정/삭제 버튼제어)
	* 
	* @param SignManageVO
	* @return boolean
	* @throws Exception
	*/
	public boolean authModify(String mode, SignManageVO lcvo) throws Exception {
		getLogger().info("[INFO] SignManageServiceImpl.authModify");
		boolean hasRight = false;
				
		//관리자가 체크
		boolean adminCheck = false;	
		
		// 시스템 권한 체크 (어드민은 수정 및 삭제에 대한 권한이 없다 : 조회권한만 가진다.)
/*		for(int i=0; i<lcvo.getSession_user_role_cds().size();i++){
			HashMap roleCd = (HashMap)lcvo.getSession_user_role_cds().get(i);
			if("RM00".equals((String)roleCd.get("role_cd"))){
				adminCheck = true;
			}  
		}*/
		
		// 특정사용자에 대한 시스템 권한 부여 처리 
/*		if(lcvo.getSession_user_id().equals("S020508102712C100225") 
				|| lcvo.getSession_user_id().equals("R020218102336C109808")
				|| lcvo.getSession_user_id().equals("R020218102359C106505")
				|| lcvo.getSession_user_id().equals("R020218102336C109809")
		){
			adminCheck = true;
		}*/
		
		ArrayList roleList = lcvo.getSession_user_role_cds();
		ArrayList userRoleList = new ArrayList();
		
		if(roleList != null && roleList.size() > 0){
		    for(int idx = 0; idx < roleList.size(); idx++){
		        HashMap roleListMap = (HashMap)roleList.get(idx);
		        userRoleList.add((String)roleListMap.get("role_cd"));
		    }
		}		
		
		if(!adminCheck){
			List infoList = commonDAO.list("las.auth.sign",lcvo);			
			
			if(infoList != null && infoList.size() > 0){
				ListOrderedMap info = (ListOrderedMap)infoList.get(0);
				
				//  의뢰자 체크
				if(lcvo.getSession_user_id().equals((String)info.get("SEAL_RQSTMAN_ID"))){
					hasRight = true;
				}
				
				getLogger().info("test");
				
				// 배정자 체크 배정자는 신청내용에 대한 수정권한을 가지지 않는다.
/*				if(userRoleList != null && userRoleList.size() > 0){
					// 날인담당자
					if(userRoleList.contains("RE01")){
						if(lcvo.getSession_user_id().equals((String)info.get("SEAL_FFMTMAN_ID"))){
							hasRight = true;
						}
					}
					// 증명서류 발급담당자
					if(userRoleList.contains("RE02")){
						if(lcvo.getSession_user_id().equals((String)info.get("DOC_ISSUER_ID"))){
							hasRight = true;
						}
					}
				}*/
			}
		}	
	
		return hasRight;
	}
	
	/**
	*  의뢰자/배정자 권한 체크 (날인처리/반납처리/증명서류처리 버튼제어)
	* 
	* @param SignManageVO
	* @return boolean
	* @throws Exception
	*/
	public boolean authProcess(String mode, SignManageVO lcvo) throws Exception {
		getLogger().info("[INFO] SignManageServiceImpl.authProcess");
		boolean hasRight = false;
		
		ArrayList roleList = lcvo.getSession_user_role_cds();
		ArrayList userRoleList = new ArrayList();
		
		if(roleList != null && roleList.size() > 0){
		    for(int idx = 0; idx < roleList.size(); idx++){
		        HashMap roleListMap = (HashMap)roleList.get(idx);
		        userRoleList.add((String)roleListMap.get("role_cd"));
		    }
		}		

		List infoList = commonDAO.list("las.auth.sign",lcvo);			
		
		if(infoList != null && infoList.size() > 0){
			ListOrderedMap info = (ListOrderedMap)infoList.get(0);
			
			// 배정자 체크 (처리에 대한 배정을 받은자는 수정 및 삭제의 권한을 가진다)
			if(userRoleList != null && userRoleList.size() > 0){
				// 날인담당자
				if(SEAL.equals(mode) && userRoleList.contains("RE01")){
					if(lcvo.getSession_user_id().equals((String)info.get("SEAL_FFMTMAN_ID"))){
						hasRight = true;
					}
				
				}
				// 증명서류 발급담당자
				if(DOC.equals(mode) && userRoleList.contains("RE02")){
					if(lcvo.getSession_user_id().equals((String)info.get("DOC_ISSUER_ID"))){
						hasRight = true;
					}				
				}
			}
		}	
		return hasRight;
	}
	
	/**
	 * 사용자 ROLE에 따른 권한 제어
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	public String checkAuthByRole(SignManageVO vo) throws Exception{

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
	public void checkBasicAccessAuth(SignManageVO vo,String returnMessage) throws Exception {
		getLogger().info("[INFO] SignManageServiceImpl.checkBasicAccessAuth");
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
	public boolean isAccessAuthAdmin(SignManageVO lcvo) throws Exception {
		getLogger().info("[INFO] SignManageServiceImpl.isAccessAuthAdmin");
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

	/**
	 * 결재후처리 날인 등록(postAppContStatus) 
	 * @param  
	 * @return 
	 * @throws Exception
	 */
	public HashMap insertPrtbInfo(String cnsdreq_id, String cntrt_id, String create_date) throws Exception {
		getLogger().info("[INFO] SignManageServiceImpl.insertPrtbInfo");
		boolean returnResult = true;
		String errType = "";
		try {		

			
			List listInfo 		= null;		
			ListOrderedMap keyLom	= null;

			String prcs_depth		= "";
			String depth_status		= "";
			String cntrt_info_gbn		= "";
			boolean result			= false;
			
			HashMap hm				= new HashMap();
			hm.put("cntrt_id", cntrt_id);
			
			listInfo = this.getAppInfoList(hm);
			
			if(listInfo != null && listInfo.size() > 0) {
				keyLom = (ListOrderedMap)listInfo.get(0);
				cntrt_id 	= (String)keyLom.get("cntrt_id");
				prcs_depth 	= (String)keyLom.get("prcs_depth");
				depth_status= (String)keyLom.get("depth_status");
				cntrt_info_gbn= (String)keyLom.get("cntrt_info_gbn");

				
				if("C02503".equals(prcs_depth) && "C02641".equals(depth_status) && !"C05404".equals(cntrt_info_gbn)) {//계약등록,체결미확인일 때만 등록
					HashMap iHm		= new HashMap();
					iHm.put("cnsdreq_id", cnsdreq_id);
					iHm.put("cntrt_id", cntrt_id);
					iHm.put("create_date", create_date);

					result = this.insertLAS_Prtb_sd_apl_if(iHm);
					if(result == false){
						errType = "LAS_Prtb_sd_apl_if";
						throw new Exception();
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnResult = false;

		} 

		
		HashMap returnResultMap = new HashMap();
		returnResultMap.put("returnResult", returnResult);
		returnResultMap.put("errType", errType);
		
		return returnResultMap;
	}
	
	//날인테이블에 등록
	public boolean insertLAS_Prtb_sd_apl_if(HashMap hm) throws Exception { //gais
		getLogger().info("[INFO] SignManageServiceImpl.insertLAS_Prtb_sd_apl_if");
		try{
			int result = 0;

					
			result = commonDAO.insert("clm.sign.insert_Prtb_sd_apl_if", hm);
	
			
			if(result > 0){
				return true;
			}else{
				return false;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}		
	}
	
	//인장 정보 조회
	public List getAppInfoList(HashMap hm) throws Exception {
		getLogger().info("[INFO] SignManageServiceImpl.getAppInfoList");
		return commonDAO.list("clm.sign.getAppInfoList", hm);		
	}
	
}