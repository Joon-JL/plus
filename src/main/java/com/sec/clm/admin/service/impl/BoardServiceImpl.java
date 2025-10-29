/**
* Project Name : 계약관리시스템
* File Name : BoardServiceImpl.java
* Description : 공지사항 ServiceImpl
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
import com.sec.clm.admin.service.BoardService;
import com.sec.common.clmsfile.dto.ClmsFileVO;

public class BoardServiceImpl extends CommonServiceImpl implements BoardService {

	/**
	* 공지사항 목록조회
	* 
	* @param BoardVO
	* @return List
	* @throws Exception
	*/
	public List listBoard(BoardVO vo) throws Exception {
		List resultList = null;
		vo.setManager_yn(getCntrtManager(vo));
		if("A".equals(vo.getMode())){
			resultList = commonDAO.list("clm.admin.listBoard", vo);
		}else{
			resultList = commonDAO.list("clm.counsel.listBoard", vo);
		}
		
		return resultList;
	}

	/**
	* 공지사항 등록
	* 
	* @param BoardVO
	* @return List
	* @throws Exception
	*/
	public int insertBoard(BoardVO vo) throws Exception {
		//libNo 구하기
		int seqNo = 1;
		List seqnoList = null;
		
		seqnoList = commonDAO.list("clm.admin.maxSeqNoBoard", vo);
		
		if (seqnoList != null && seqnoList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap)seqnoList.get(0);

			seqNo = ((Integer)lom.get("max_seq_no")).intValue() + 1;
		}
		vo.setSeqno(seqNo);
		
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
		commonDAO.insert("clm.admin.insertBoard", vo);

		//조직저장
		if("C01602".equals(vo.getRd_trgt1())){
			String[] orgnzCds = vo.getOrgnz_cds();
			
			if(orgnzCds != null && orgnzCds.length > 0){
				for(int i=0; i < orgnzCds.length; i++){
					vo.setOrgnz_cd(orgnzCds[i]);
					commonDAO.insert("clm.admin.insertBoardOrgnz", vo);
				}
				
			}
		}
		return seqNo;
	}

	/**
	* 공지사항 상세조회
	* 
	* @param BoardVO
	* @return List
	* @throws Exception
	*/
	public ListOrderedMap detailBoard(BoardVO vo) throws Exception {
		ListOrderedMap result = null;
		
		//데이터 조회
		List resultList = commonDAO.list("clm.admin.detailBoard", vo);
		
		if(resultList != null && resultList.size() > 0){
			result = (ListOrderedMap)resultList.get(0);

			//조회수 증가 : 관리자 모드에서는 조회수 증가 취소
			commonDAO.modify("clm.admin.modifyRdcntBoard", vo);
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
	public List listOrgnz(BoardVO vo) throws Exception{
		return commonDAO.list("clm.admin.listBoardOrgnz", vo);
	}
	
	/**
	* 공지사항 수정
	* 
	* @param BoardVO
	* @return List
	* @throws Exception
	*/	public int modifyBoard(BoardVO vo) throws Exception {
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
		
		commonDAO.modify("clm.admin.modifyBoard", vo);

		//조직저장
		if("C01602".equals(vo.getRd_trgt1())){
			
			commonDAO.delete("clm.admin.deleteBoardOrgnz", vo);
			
			String[] orgnzCds = vo.getOrgnz_cds();
			
			if(orgnzCds != null && orgnzCds.length > 0){
				for(int i=0; i < orgnzCds.length; i++){
					vo.setOrgnz_cd(orgnzCds[i]);
					commonDAO.insert("clm.admin.insertBoardOrgnz", vo);
				}
				
			}
		}
		return vo.getSeqno();		
	}

	/**
	* 공지사항 삭제
	* 
	* @param BoardVO
	* @return List
	* @throws Exception
	*/
	public int deleteBoard(BoardVO vo) throws Exception {
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
		
		//조직삭제
		commonDAO.delete("clm.admin.deleteBoardOrgnz", vo);
		
		return commonDAO.modify("clm.admin.deleteBoard", vo);
	}

	
	/**
	* 공지사항 권한조회
	* 
	* @param BoardVO
	* @return boolean
	* @throws Exception
	*/
	public boolean authBoard(String mode, BoardVO vo) throws Exception {
		boolean result = false;
		
		//관리자가 체크
		boolean adminCheck = false;
		
		//Admin화면일 경우
		if("A".equals(vo.getMode())){
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
		}
		
		if(adminCheck){
			result = true;
		}else{
			List infoList = commonDAO.list("clm.admin.authBoard",vo);
			if(infoList != null && infoList.size() > 0){
				ListOrderedMap info = (ListOrderedMap)infoList.get(0);
				//조회모드 일 경우
				if(SEARCH.equals(mode)){
					//Admin CP관리자일경우 조회가능, 나머지는 조회불가
					if("A".equals(vo.getMode())){

					}else if("M".equals(vo.getMode())){
						result = true;
					}
				}
				//등록모드 일 경우
				if(INSERT.equals(mode)){
					if("A".equals(vo.getMode())){
						
					}else if("M".equals(vo.getMode())){
						
					}
				}				
				//수정모드 일 경우
				if(MODIFY.equals(mode)){
					if("A".equals(vo.getMode())){
						
					}else if("M".equals(vo.getMode())){
						
					}
				}
				//삭제모드 일 경우
				if(DELETE.equals(mode)){
					if("A".equals(vo.getMode())){
						
					}else if("M".equals(vo.getMode())){
						
					}
				}
			}
		}

		return result;
	}
	
	/**
	* 계약관리조직체크
	* 
	* @param RequestVO
	* @return List
	* @throws Exception
	*/
	private String getCntrtManager(BoardVO vo) throws Exception {

	
        /*	계약관리조직
			시스템관리자        	RA00, AA00
			법무관리자          	RA01, AA01
			법무담당자          	RA02, AA02
			법무일반 사용자     	RA03, AA03
			cp관리자         RC01, AC01
			사업부계약관리자   RD01, AD01
			사업부계약담당자   RD02, AD02
			시스템운영자        	RM00, AM00
			일반임직원         	RZ00, AZ00
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
            if(userRoleList.contains("RA00") || userRoleList.contains("RA01") || userRoleList.contains("RA02") || userRoleList.contains("RA03") || userRoleList.contains("RD01") || userRoleList.contains("RM00")) {	
                result = "Y";
            } else {
                result = "N";
            }
        }
        return result;
    }
	
	/**
	* 공지사항 목록조회 - 계약관리 메인
	* 
	* @param BoardVO
	* @return List
	* @throws Exception
	*/
	public List listBoardByMain(BoardVO vo) throws Exception {
		List result = null;
		
		vo.setManager_yn(getCntrtManager(vo));
		result = commonDAO.list("clm.admin.listBoardByMain", vo);
		return result;
	}	
}