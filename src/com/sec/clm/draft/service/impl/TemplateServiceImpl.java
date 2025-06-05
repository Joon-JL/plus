/**
* Project Name : 계약관리시스템
* File Name : TemplateServiceImpl.java
* Description : 표준 Template ServiceImpl
* Author : 신남원
* Date : 2010.08.31
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.draft.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.draft.dto.LibraryVO;
import com.sec.clm.draft.service.TemplateService;
import com.sec.common.clmsfile.dto.ClmsFileVO;

public class TemplateServiceImpl extends CommonServiceImpl implements TemplateService {

	/**
	* 표준 Template 목록조회
	* 
	* @param LibraryVO
	* @return List
	* @throws Exception
	*/
	public List listTemplate(LibraryVO vo) throws Exception {
		return commonDAO.list("clm.draft.listTemplate", vo);
	}

	/**
	* 표준 Template 저장
	* 
	* @param LibraryVO
	* @return int
	* @throws Exception
	*/
	public int insertTemplate(LibraryVO vo) throws Exception {
		//libNo 구하기
		int libNo = 1;
		List libnoList = null;
		
		libnoList = commonDAO.list("clm.draft.maxLibNoTemplate", vo);
		
		if (libnoList != null && libnoList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap)libnoList.get(0);

			libNo = ((Integer)lom.get("max_lib_no")).intValue() + 1;
		}
		vo.setLib_no(libNo);
		
		/*************************************************
		 * 첨부파일 저장
		 *************************************************/
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(Integer.toString(vo.getLib_no()));
		fvo.setFileInfos(vo.getFileInfos());
		fvo.setReg_id(vo.getSession_user_id());

		clmsFileService.mngClmsAttachFile(fvo, vo);
		
		/*************************************************
		 * 나모 웹 에디터 처리
		 *************************************************/
		String decodeText = vo.getCont();
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
			vo.setCont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
		}else {
			vo.setCont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
		}
		
		//표준 TemPlate 저장
		commonDAO.insert("clm.draft.insertTemplate", vo);
		
		//상대방 정보 저장
		String[] oppntCdList = vo.getCntrt_oppnt_cds();
		String[] oppntNmList = vo.getCntrt_oppnt_nms();
		
		if(oppntCdList != null && oppntCdList.length > 0){
			for(int i=0; i<oppntCdList.length ;i++){
				if(!"".equals(oppntCdList[i]) && oppntCdList[i] != null){
					LibraryVO subvo = new LibraryVO();
					subvo.setLib_no(vo.getLib_no());
					subvo.setCntrt_oppnt_cd(oppntCdList[i]);
					subvo.setCntrt_oppnt_nm(oppntNmList[i]);
					subvo.setReg_id(vo.getReg_id());
					subvo.setReg_nm(vo.getReg_nm());
	
					commonDAO.insert("clm.draft.insertTemplateOppntCd", subvo);
				}
			}
		}

		return libNo;
	}

	/**
	* 표준 Template 수정
	* 
	* @param LibraryVO
	* @return int
	* @throws Exception
	*/
	public int modifyTemplate(LibraryVO vo) throws Exception {
		
		/*************************************************
		 * 첨부파일 저장
		 *************************************************/
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(Integer.toString(vo.getLib_no()));
		fvo.setFileInfos(vo.getFileInfos());
		fvo.setReg_id(vo.getSession_user_id());
		
		clmsFileService.mngClmsAttachFile(fvo, vo);
		
		/*************************************************
		 * 나모 웹 에디터 처리
		 *************************************************/
		String decodeText = vo.getCont();
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
			vo.setCont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
		}else {
			vo.setCont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
		}
		
		//표준 TemPlate 저장
		commonDAO.modify("clm.draft.modifyTemplate", vo);

		//상대방 정보 전체 삭제
		commonDAO.delete("clm.draft.deleteTemplateOppntCd", vo);
		//상대방 정보 저장
		String[] oppntCdList = vo.getCntrt_oppnt_cds();
		String[] oppntNmList = vo.getCntrt_oppnt_nms();
		
		if(oppntCdList != null && oppntCdList.length > 0){
			for(int i=0; i<oppntCdList.length ;i++){
				if(!"".equals(oppntCdList[i]) && oppntCdList[i] != null){
					LibraryVO subvo = new LibraryVO();
					subvo.setLib_no(vo.getLib_no());
					subvo.setCntrt_oppnt_cd(oppntCdList[i]);
					subvo.setCntrt_oppnt_nm(oppntNmList[i]);
					subvo.setReg_id(vo.getSession_user_id());
					subvo.setReg_nm(vo.getSession_user_nm());
	
					commonDAO.insert("clm.draft.insertTemplateOppntCd", subvo);
				}
			}
		}
		
		return vo.getLib_no();
	}

	/**
	* 표준 Template 삭제
	* 
	* @param LibraryVO
	* @return int
	* @throws Exception
	*/
	public int deleteTemplate(LibraryVO vo) throws Exception {
		
		/*************************************************
		 * 첨부파일 삭제
		 *************************************************/
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(Integer.toString(vo.getLib_no()));
		fvo.setFileInfos(vo.getFileInfos());
		fvo.setDel_id(vo.getSession_user_id());
		
		clmsFileService.delClmsAttachFile(fvo);		
		
		//상대방 정보 삭제
		commonDAO.delete("clm.draft.deleteTemplateOppntCd", vo);
		
		return commonDAO.modify("clm.draft.deleteTemplate", vo);
	}

	/**
	* 표준 Template 상세조회
	* 
	* @param LibraryVO
	* @return ListOrderedMap
	* @throws Exception
	*/
	public ListOrderedMap detailTemplate(LibraryVO vo) throws Exception {
		ListOrderedMap result = null;
		
		//데이터 조회
		List resultList = commonDAO.list("clm.draft.detailTemplate", vo);
		
		if(resultList != null && resultList.size() > 0){
			result = (ListOrderedMap)resultList.get(0);

			//조회수 증가
			commonDAO.modify("clm.draft.modifyRdcntTemplate", vo);
		}
		
		return result; 
	}

	/**
	* 표준 Template 거래상대방 목록조회
	* 
	* @param LibraryVO
	* @return List
	* @throws Exception
	*/
	public List listTemplateOppntCd(LibraryVO vo) throws Exception {
		return commonDAO.list("clm.draft.listTemplateOppntCd", vo);
	}

	/**
	* 표준 Template 권한조회
	* 
	* @param LibraryVO
	* @return boolean
	* @throws Exception
	*/
	public boolean authTemplate(String mode, LibraryVO vo) throws Exception {
		boolean result = false;
		
		//관리자가 체크
		boolean adminCheck = false;
		
		for(int i=0; i<vo.getSession_user_role_cds().size();i++){
			HashMap roleCd = (HashMap)vo.getSession_user_role_cds().get(i);
			if("RA00".equals((String)roleCd.get("role_cd")) || 
				"RA01".equals((String)roleCd.get("role_cd")) || 
				"RA02".equals((String)roleCd.get("role_cd")) || 
				"RA03".equals((String)roleCd.get("role_cd")) || 
				"RM00".equals((String)roleCd.get("role_cd"))){
				adminCheck = true;
			}
		}
		
		if(adminCheck){
			result = true;
		}else{
			List infoList = commonDAO.list("clm.draft.authTemplate",vo);
			if(infoList != null && infoList.size() > 0){
				ListOrderedMap info = (ListOrderedMap)infoList.get(0);
				//조회모드 일 경우
				if(SEARCH.equals(mode)){
					result = true;
				}
				//등록모드 일 경우
				if(INSERT.equals(mode)){

				}				
				//수정모드 일 경우
				if(MODIFY.equals(mode)){
				}
				//삭제모드 일 경우
				if(DELETE.equals(mode)){
				}
			}
		}
	
		return result;
	}

}