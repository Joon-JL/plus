/**
* Project Name : 계약관리시스템
* File Name : FaqServiceImpl.java
* Description : ADMIN FAQ ServiceImpl
* Author : 신남원
* Date : 2010.09.01
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.admin.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.admin.dto.BoardVO;
import com.sec.clm.admin.dto.FaqVO;
import com.sec.clm.admin.service.FaqService;
import com.sec.common.clmsfile.dto.ClmsFileVO;

public class FaqServiceImpl extends CommonServiceImpl implements FaqService {

	/**
	* ADMIN FAQ 목록조회
	* 
	* @param FaqVO
	* @return List
	* @throws Exception
	*/
	public List listFaq(FaqVO vo) throws Exception {
		return commonDAO.list("clm.admin.listFaq", vo);
	}

	/**
	* FAQ 목록조회
	* 
	* @param FaqVO
	* @return List
	* @throws Exception
	*/
	public List listCounselFaq(FaqVO vo) throws Exception {
		return commonDAO.list("clm.counsel.listFaq", vo);
	}
	
	/**
	* ADMIN FAQ 등록
	* 
	* @param FaqVO
	* @return List
	* @throws Exception
	*/
	public int insertFaq(FaqVO vo) throws Exception {
		//libNo 구하기
		int seqNo = 1;
		List seqnoList = null;
		
		seqnoList = commonDAO.list("clm.admin.maxSeqNoFaq", vo);
		
		if (seqnoList != null && seqnoList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap)seqnoList.get(0);

			seqNo = ((Integer)lom.get("max_seq_no")).intValue() + 1;
		}
		vo.setSeqno(seqNo);
			
		/*************************************************
		 * 나모 웹 에디터 처리
		 *************************************************/
		String dcdContKr = vo.getCont();
		String dcdContEn = vo.getCont_en();
		HashMap hmKr = comUtilService.getNamoContentAndFileInfo(dcdContKr);
		HashMap hmEn = comUtilService.getNamoContentAndFileInfo(dcdContEn);

		if (hmKr.get("TYPE").equals("M")) {
			ArrayList fileList = (ArrayList)hmKr.get("FILE_INFO");
			
			for (int i = 0; i < fileList.size(); i++) {
				HashMap fileMap = (HashMap)fileList.get(i);
				
				Integer seq = new Integer(i);
				String fileNm = (String)fileMap.get("FILE_NM");
				String filePath = (String)fileMap.get("FILE_PTH");
				String fileUrl = (String)fileMap.get("FILE_URL");
				
				File f = new File(filePath);
				Long fileSize = new Long(f.length());
			}			
			vo.setCont((StringUtil.convertNamoCharsToHtml((String)hmKr.get("CONTENT")))); //Cross-site Scripting 방지 처리
			vo.setCont_en((StringUtil.convertNamoCharsToHtml((String)hmEn.get("CONTENT")))); //Cross-site Scripting 방지 처리
		}else {
			vo.setCont((StringUtil.convertNamoCharsToHtml((String)hmKr.get("CONTENT")))); //Cross-site Scripting 방지 처리
			vo.setCont_en((StringUtil.convertNamoCharsToHtml((String)hmEn.get("CONTENT")))); //Cross-site Scripting 방지 처리
		}
		
		//데이터 저장
		commonDAO.insert("clm.admin.insertFaq", vo);

		//조직저장
		if("C01602".equals(vo.getRd_trgt1())){
			String[] orgnzCds = vo.getOrgnz_cds();
			
			if(orgnzCds != null && orgnzCds.length > 0){
				for(int i=0; i < orgnzCds.length; i++){
					vo.setOrgnz_cd(orgnzCds[i]);
					commonDAO.insert("clm.admin.insertFaqOrgnz", vo);
				}
				
			}
		}
		return seqNo;
	}

	/**
	* ADMIN FAQ 상세조회
	* 
	* @param FaqVO
	* @return List
	* @throws Exception
	*/
	public ListOrderedMap detailFaq(FaqVO vo) throws Exception {
		ListOrderedMap result = null;
		
		//데이터 조회
		List resultList = commonDAO.list("clm.admin.detailFaq", vo);
		
		if(resultList != null && resultList.size() > 0){
			result = (ListOrderedMap)resultList.get(0);

			//조회수 증가
			commonDAO.modify("clm.admin.modifyRdcntFaq", vo);
		}
		
		return result; 
	}
	
	/**
	* 공지사항 단위조직 조회
	* 
	* @param BoardVO
	* @return List
	* @throws Exception
	*/
	public List listOrgnz(FaqVO vo) throws Exception{
		return commonDAO.list("clm.admin.listFaqOrgnz", vo);
	}	
	
	/**
	* FAQ 조회수 증가
	* 
	* @param FaqVO
	* @return List
	* @throws Exception
	*/
	public int incRdcntFaq(FaqVO vo) throws Exception {
		return commonDAO.modify("clm.admin.modifyRdcntFaq", vo); 
	}	

	/**
	* ADMIN FAQ 수정
	* 
	* @param FaqVO
	* @return List
	* @throws Exception
	*/	public int modifyFaq(FaqVO vo) throws Exception {

		/*************************************************
		 * 나모 웹 에디터 처리
		 *************************************************/
		String dcdContKr = vo.getCont();
		String dcdContEn = vo.getCont_en();
		HashMap hmKr = comUtilService.getNamoContentAndFileInfo(dcdContKr);
		HashMap hmEn = comUtilService.getNamoContentAndFileInfo(dcdContEn);

		if (hmKr.get("TYPE").equals("M")) {
			ArrayList fileList = (ArrayList)hmKr.get("FILE_INFO");
			
			for (int i = 0; i < fileList.size(); i++) {
				HashMap fileMap = (HashMap)fileList.get(i);
				
				Integer seq = new Integer(i);
				String fileNm = (String)fileMap.get("FILE_NM");
				String filePath = (String)fileMap.get("FILE_PTH");
				String fileUrl = (String)fileMap.get("FILE_URL");
				
				File f = new File(filePath);
				Long fileSize = new Long(f.length());
			}			
			vo.setCont((StringUtil.convertNamoCharsToHtml((String)hmKr.get("CONTENT")))); //Cross-site Scripting 방지 처리
			vo.setCont_en((StringUtil.convertNamoCharsToHtml((String)hmEn.get("CONTENT")))); //Cross-site Scripting 방지 처리
		}else {
			vo.setCont((StringUtil.convertNamoCharsToHtml((String)hmKr.get("CONTENT")))); //Cross-site Scripting 방지 처리
			vo.setCont_en((StringUtil.convertNamoCharsToHtml((String)hmEn.get("CONTENT")))); //Cross-site Scripting 방지 처리
		}
		
		commonDAO.modify("clm.admin.modifyFaq", vo);

		//조직저장
		if("C01602".equals(vo.getRd_trgt1())){
			
			commonDAO.delete("clm.admin.deleteFaqOrgnz", vo);
			
			String[] orgnzCds = vo.getOrgnz_cds();
			
			if(orgnzCds != null && orgnzCds.length > 0){
				for(int i=0; i < orgnzCds.length; i++){
					vo.setOrgnz_cd(orgnzCds[i]);
					commonDAO.insert("clm.admin.insertFaqOrgnz", vo);
				}
				
			}
		}
		return vo.getSeqno();		
	}

	/**
	* ADMIN FAQ 삭제
	* 
	* @param FaqVO
	* @return List
	* @throws Exception
	*/
	public int deleteFaq(FaqVO vo) throws Exception {
		//조직삭제
		commonDAO.delete("clm.admin.deleteFaqOrgnz", vo);
		return commonDAO.modify("clm.admin.deleteFaq", vo);
	}

	/**
	* ADMIN FAQ 권한조회
	* 
	* @param FaqVO
	* @return boolean
	* @throws Exception
	*/
	public boolean authFaq(String mode, FaqVO vo) throws Exception {
		boolean result = false;
		
		//관리자가 체크
		boolean adminCheck = false;
		
		for(int i=0; i<vo.getSession_user_role_cds().size();i++){
			HashMap roleCd = (HashMap)vo.getSession_user_role_cds().get(i);
			if("RA00".equals((String)roleCd.get("role_cd")) || 
				"RA01".equals((String)roleCd.get("role_cd")) || 
				"RA02".equals((String)roleCd.get("role_cd")) || 
				"RA03".equals((String)roleCd.get("role_cd")) ||
				"RD01".equals((String)roleCd.get("role_cd")) ||
				"RM00".equals((String)roleCd.get("role_cd"))){
				adminCheck = true;
			}
		}
		
		if(adminCheck){
			result = true;
		}else{
			List infoList = commonDAO.list("clm.admin.authFaq",vo);
			if(infoList != null && infoList.size() > 0){
				ListOrderedMap info = (ListOrderedMap)infoList.get(0);
				//조회모드 일 경우
				if(SEARCH.equals(mode)){
					if("A".equals(vo.getMode())){
						
					}else if("M".equals(vo.getMode())){
						result = true;
					}
				}
				//등록모드 일 경우
				if(INSERT.equals(mode)){
					result = false;
				}				
				//수정모드 일 경우
				if(MODIFY.equals(mode)){
					result = false;
				}
				//삭제모드 일 경우
				if(DELETE.equals(mode)){
					result = false;
				}
			}
		}

		return result;
	}
}