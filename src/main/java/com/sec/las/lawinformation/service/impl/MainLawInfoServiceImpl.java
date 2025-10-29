/**
 * Project Name : 법무시스템 이식
 * File name	: MainLawInfoServiceImpl.java
 * Description	: 주요입법동향 ServiceImpl(concrete class)
 * Author		: 
 * Date			: 2011. 08
 * Copyright	:
 */


package com.sec.las.lawinformation.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sec.common.clmsfile.dto.ClmsFileVO;
import com.sec.las.lawinformation.dto.MainLawInfoForm;
import com.sec.las.lawinformation.dto.MainLawInfoVO;
import com.sec.las.lawinformation.service.MainLawInfoService;


/**
 * Description	: 주요입법동향 Service impl(concrete class)
 * Author 		: 유영섭
 * Date			: 2011. 08. 04
 */
public class MainLawInfoServiceImpl extends CommonServiceImpl implements MainLawInfoService {

	/**
	 * 주요입법동향 목록을 조회한다.
	 * @param  vo MainLawInfoVO
	 * @return List
	 * @throws Exception
	 */
	
	public List listMainLawInfo(MainLawInfoVO vo) throws Exception {
		return commonDAO.list("las.mainLawInfo.list", vo);
	}

	
	/**
	 * 주요입법동향을 등록한다.
	 * @param  vo MainLawInfoVO
	 * @return int
	 * @throws Exception  sqlCUDExecutor(IQueryInfo queryInfo, Object[] values)
	 */
	    
	public int insertMainLawInfo(MainLawInfoVO vo) throws Exception {
		
		MainLawInfoForm mainLawInfoForm = new MainLawInfoForm();
		
		int seqno = 1;  
		List resultList = null;
		 
		//make sequence
		resultList = commonDAO.list("las.mainLawInfo.makeSeqmainLawInfo", vo);
		
		if (resultList != null && resultList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			vo.setSeqno(Integer.parseInt(lom.get("next_seqno").toString()));
			seqno = vo.getSeqno();
		} else {
				vo.setSeqno(seqno);
			}
		
		/*************************************************
		 * 첨부파일 저장
		 *************************************************/
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(Integer.toString(vo.getSeqno()));
		fvo.setFileInfos(vo.getFileInfos());
		fvo.setReg_id(vo.getSession_user_id());
		
		clmsFileService.mngClmsAttachFile(fvo);
			
		/*************************************************
		 * 나모 웹 에디터 처리
		 *************************************************/

//		String decodeText = vo.getBody_mime();
//		HashMap hm = comUtilService.getNamoContentAndFileInfo(decodeText);
//
//		if (hm.get("TYPE").equals("M")) {
//			ArrayList fileList = (ArrayList)hm.get("FILE_INFO");
//			
//			for (int i = 0; i < fileList.size(); i++) {
//				HashMap fileMap = (HashMap)fileList.get(i);
//				
//				Integer seq = new Integer(i);
//				String fileNm = (String)fileMap.get("FILE_NM");
//				String filePath = (String)fileMap.get("FILE_PTH");
//				String fileUrl = (String)fileMap.get("FILE_URL");
//				
//				File f = new File(filePath);
//				Long fileSize = new Long(f.length());
//			}			
//			vo.setCont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
//		}else {
//			vo.setCont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
//		}
		
//		vo.setCont(vo.getBody_mime());
		 
		commonDAO.insert("las.mainLawInfo.insert", vo);
		
		return seqno;
		
	}


	
	public int modifyMainLawInfo(MainLawInfoVO vo) throws Exception {

		/*************************************************
		 * 첨부파일 저장
		 *************************************************/
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(Integer.toString(vo.getSeqno()));
		fvo.setFileInfos(vo.getFileInfos());
		fvo.setReg_id(vo.getSession_user_id());
		
		clmsFileService.mngClmsAttachFile(fvo);
			
		/*************************************************
		 * 나모 웹 에디터 처리
		 *************************************************/

//		String decodeText = vo.getBody_mime();
//		HashMap hm = comUtilService.getNamoContentAndFileInfo(decodeText);
//
//		if (hm.get("TYPE").equals("M")) {
//			ArrayList fileList = (ArrayList)hm.get("FILE_INFO");
//			
//			for (int i = 0; i < fileList.size(); i++) {
//				HashMap fileMap = (HashMap)fileList.get(i);
//				
//				Integer seq = new Integer(i);
//				String fileNm = (String)fileMap.get("FILE_NM");
//				String filePath = (String)fileMap.get("FILE_PTH");
//				String fileUrl = (String)fileMap.get("FILE_URL");
//				
//				File f = new File(filePath);
//				Long fileSize = new Long(f.length());
//			}			
//			vo.setCont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
//		}else {
//			vo.setCont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
//		}
		
//		vo.setCont(vo.getBody_mime());
		
		return commonDAO.modify("las.mainLawInfo.modifyMainLawInfo", vo);
	}
	
	/**
	 * 주요/입법동향을 삭제한다.
	 * @param  vo LibraryVO
	 * @return int
	 * @throws Exception
	 */
	
	public int deleteMainLawInfo(MainLawInfoVO vo) throws Exception {
		int result=0;
		result = commonDAO.delete("las.mainLawInfo.deleteMainLawInfo", vo);
		
        /*************************************************
         * 첨부파일 삭제
         *************************************************/
        ClmsFileVO fvo = new ClmsFileVO();

        fvo.setSys_cd(vo.getSys_cd());
        fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
        fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(Integer.toString(vo.getSeqno()));
		fvo.setFileInfos(vo.getFileInfos());
        fvo.setDel_id(vo.getSession_user_id());

        clmsFileService.delClmsAttachFile(fvo);  

		return result;
	}

	
	/**
	 * 주요입법동향 상세목록을 조회한다.
	 * @param  vo MainLawInfoVO
	 * @return List
	 * @throws Exception
	 */
	
	public List detailMainLawInfo(MainLawInfoVO vo) throws Exception {
		return commonDAO.list("las.mainLawInfo.detailMainLawInfo", vo);
	}
	
	
	/**
	 * 주요입법동향 조회수 증가
	 * @param  vo LibraryVO
	 * @return List
	 * @throws Exception
	 */
	public void increseRdcnt(MainLawInfoVO vo) throws Exception {
		commonDAO.modify("las.mainLawInfo.increseRdcnt", vo) ;
	}
	
	/**
	* 표준 조항 권한조회
	* 
	* @param ItemLibraryVO
	* @return boolean
	* @throws Exception
	*/
	public boolean authMainLawInfo(String mode, MainLawInfoVO vo) throws Exception {
		boolean result = true;
		
		//관리자가 체크
		boolean adminCheck = false;
		
		for(int i=0; i<vo.getSession_user_role_cds().size();i++){
			HashMap roleCd = (HashMap)vo.getSession_user_role_cds().get(i);
			if("RA00".equals((String)roleCd.get("role_cd"))){
				adminCheck = true;
			}
		}

		if(!adminCheck){
			List infoList = commonDAO.list("las.lawinformation.authMainLawInfo",vo);
			if(infoList != null && infoList.size() > 0){
				ListOrderedMap info = (ListOrderedMap)infoList.get(0);
				//수정모드 일 경우
				if(MODIFY.equals(mode)){
					if(!vo.getSession_user_id().equals((String)info.get("reg_id"))){
						result = false;
					}
				}
				//삭제모드 일 경우
				if(DELETE.equals(mode)){
					if(!vo.getSession_user_id().equals((String)info.get("reg_id"))){
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
	public String checkAuthByRole(MainLawInfoVO vo) throws Exception{

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
			
			A: CRUD
			B: R
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
			if(userRoleList.contains("RM00") || userRoleList.contains("RA00") || userRoleList.contains("RA01") || userRoleList.contains("RA02") || userRoleList.contains("RA03")){
				accessLevel = "A";
			} else {
				accessLevel = "B";
			}
		}

		return accessLevel;
	}
	
	/**
	 * 주요입법동향 목록을 조회한다.(법무메인)
	 * @param  vo MainLawInfoVO
	 * @return List
	 * @throws Exception
	 */
	public List listMainLawInfoByMain(MainLawInfoVO vo) throws Exception {
		return commonDAO.list("las.mainLawInfo.listByMain", vo);
	}	

}