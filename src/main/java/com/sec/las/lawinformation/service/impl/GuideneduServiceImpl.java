package com.sec.las.lawinformation.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sec.common.clmsfile.dto.ClmsFileVO;
import com.sec.las.lawinformation.dto.GuideneduVO;
import com.sec.las.lawinformation.service.GuideneduService;

public class GuideneduServiceImpl extends CommonServiceImpl implements GuideneduService {
	/**
	 * 임직원법무교육자료 목록을 조회한다.
	 * @param  vo GuideneduVO
	 * @return List
	 * @throws Exception
	 */
	public List listGuidenedu(GuideneduVO vo) throws Exception {
		return commonDAO.list("las.contract.listGuidenedu", vo);
	}

	/**
	 * 임직원법무교육자료를 등록한다.
	 * @param  vo GuideneduVO
	 * @return int
	 * @throws Exception
	 */
	public int insertGuidenedu(GuideneduVO vo) throws Exception {
		
		//make sequence
		int seqno = 1;
		List resultList = null;
		
		resultList = commonDAO.list("las.contract.makeSeqGuidenedu", vo);
		
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
		
		
		commonDAO.insert("las.contract.insertGuidenedu", vo);
				
		return seqno;
	}

	/**
	 * 계약서 GuideLine 자료를 등록한다.
	 * @param  vo GuideneduVO
	 * @return int
	 * @throws Exception 
	 */
	public int insertGuideline(GuideneduVO vo) throws Exception {
		
		int result = 0;
		
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
		
		
		result = commonDAO.insert("las.contract.insertGuidenedu", vo);
				
		return result;
	}

	/**
	 * 임직원법무교육자료를 수정한다.
	 * @param  vo GuideneduVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyGuidenedu(GuideneduVO vo) throws Exception {
		
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
//			
//			vo.setCont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT"))));
//		}else {
//			vo.setCont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT"))));
//		}
//		vo.setCont(vo.getBody_mime());
		
		
		return commonDAO.modify("las.contract.modifyGuidenedu", vo);
	}

	/**
	 * 계약서 GuideLine 수정한다.
	 * @param  vo GuideneduVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyGuideline(GuideneduVO vo) throws Exception {
		
		/*************************************************
		 * 나모 웹 에디터 처리
		 *************************************************/
		String decodeText = vo.getBody_mime();
		HashMap hm = comUtilService.getNamoContentAndFileInfo(decodeText);
		
		if (hm.get("TYPE").equals("M")) {
			ArrayList fileList = (ArrayList)hm.get("FILE_INFO");
			
			for (int i = 0; i < fileList.size(); i++) {
				HashMap fileMap = (HashMap)fileList.get(i);
				
				Integer seq = new Integer(i);
				String fileNm = (String)fileMap.get("FILE_NM");
				String filePath = (String)fileMap.get("FILE_PTH");
				String fileUrl = (String)fileMap.get("FILE_URL");
				
				File f = new File(filePath);
				Long fileSize = new Long(f.length());
			}
			
			vo.setCont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT"))));
		}else {
			vo.setCont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT"))));
		}
		
		
		return commonDAO.modify("las.contract.modifyGuidenedu", vo);
	}

	/**
	 * 임직원법무교육자료를 삭제한다.
	 * @param  vo GuideneduVO
	 * @return int
	 * @throws Exception
	 */
	public int deleteGuidenedu(GuideneduVO vo) throws Exception {

		int result=0;
		result = commonDAO.delete("las.contract.deleteGuidenedu", vo);

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
		fvo.setReg_id(vo.getSession_user_id());

	    clmsFileService.delClmsAttachFile(fvo);  

		return result;
	}

	/**
	 * 계약서 GuideLine을 삭제한다.
	 * @param  vo GuideneduVO
	 * @return int
	 * @throws Exception
	 */
	public int deleteGuideline(GuideneduVO vo) throws Exception {

		return commonDAO.delete("las.contract.deleteGuideline", vo);
	}

	/**
	 * 임직원법무교육자료를 상세조회한다.
	 * @param  vo GuideneduVO
	 * @return List
	 * @throws Exception
	 */
	public List getGuidenedu(GuideneduVO vo) throws Exception {
		return commonDAO.list("las.contract.getGuidenedu", vo);
	}
	
	/**
	 * 임직원법무교육자료 조회수 증가
	 * @param  vo GuideneduVO
	 * @return List
	 * @throws Exception
	 */
	public void increseRdcnt(GuideneduVO vo) throws Exception {
		commonDAO.modify("las.contract.increseGuideneduRdcnt", vo) ;
	}
	
	/**
	* 표준 조항 권한조회
	* 
	* @param ItemGuideneduVO
	* @return boolean
	* @throws Exception
	*/
	public boolean authGuidenedu(String mode, GuideneduVO vo) throws Exception {
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
			List infoList = commonDAO.list("las.lawinformation.authGuidenedu",vo);
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
	 * @param vo GuideneduVO
	 * @return String
	 * @throws Exception
	 */
	public String checkAuthByRole(GuideneduVO vo) throws Exception{

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
			if( userRoleList.contains("RM00") || userRoleList.contains("RA00") || userRoleList.contains("RA01") ||  
					userRoleList.contains("RA02") || userRoleList.contains("RA03")){
				accessLevel = "A";
			} else {
				accessLevel = "B";
			}
		}

		return accessLevel;
	}
}