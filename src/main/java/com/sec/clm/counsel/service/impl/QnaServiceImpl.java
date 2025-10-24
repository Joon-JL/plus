/**
* Project Name : 계약관리시스템
* File Name : QnaServiceImpl.java
* Description : QNA ServiceImpl
* Author : dawn
* Date : 2010.09.06
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/


package com.sec.clm.counsel.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.counsel.dto.QnaVO;
import com.sec.clm.counsel.service.QnaService;
import com.sec.common.clmsfile.dto.ClmsFileVO;

public class QnaServiceImpl extends CommonServiceImpl implements QnaService {

	/**
	* QNA 목록조회
	* 
	* @param QnaVO
	* @return List
	* @throws Exception
	*/
	public List listQna(QnaVO vo) throws Exception {
		ListOrderedMap roleLom = new ListOrderedMap();
		//현재 사용자의 role_cd를 vo의 top_role 변수에 세팅
		roleLom = roleCdQna(vo);
		vo.setTop_role((String)roleLom.get("role_cd"));
		
		return commonDAO.list("clm.counsel.listQna", vo);
	}

	/**
	* QNA 상세목록
	* 
	* @param QnaVO
	* @return List
	* @throws Exception
	*/
	public ListOrderedMap detailQna(QnaVO vo) throws Exception {
		ListOrderedMap result = null;		
		//데이터 조회
		List resultList = commonDAO.list("clm.counsel.detailQna", vo);
		
		if(resultList != null && resultList.size() > 0){
			result = (ListOrderedMap)resultList.get(0);
			//조회수 증가
			commonDAO.modify("clm.counsel.modifyRdcntQna", vo);
		}
		return result; 
	}
	/**
	* QNA 상세화면 댓글목록
	* 
	* @param QnaVO
	* @return List
	* @throws Exception
	*/
	public List detailReplyQna(QnaVO vo) throws Exception {
		return commonDAO.list("clm.counsel.detailReplyQna", vo);
	}

	/**
	* QNA 수정
	* 
	* @param QnaVO
	* @return List
	* @throws Exception
	*/
	public int modifyQna(QnaVO vo) throws Exception {
		
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
		
		int iret = 9999;
		if(StringUtil.checkScript(vo.getTitle()) && StringUtil.checkScript(vo.getCont())){
			iret = commonDAO.modify("clm.counsel.modifyQna", vo);
		}
		
		return iret;
	}

	/**
	* QNA 삭제
	* 
	* @param QnaVO
	* @return List
	* @throws Exception
	*/
	public int deleteQna(QnaVO vo) throws Exception {
		
		//답변 삭제시 기존 질문 상태 문의로 변경
		if(vo.getUp_seqno() != 0){
			vo.setPrgrs_status("C01701");
			commonDAO.modify("clm.counsel.updatePrgrsStatusQna2", vo);
		}
		
		return commonDAO.modify("clm.counsel.deleteQna", vo);
	}

	/**
	* QNA 저장
	* 
	* @param QnaVO
	* @return List
	* @throws Exception
	*/
	public int insertQna(QnaVO vo) throws Exception {
		int upSeqNo = 0;//상위 일련번호
		int seqNo   = 0;//일련번호
		
		//max seq값 구함
		List seqnoList = commonDAO.list("clm.counsel.maxSeqNoQna", vo);
		if (seqnoList != null && seqnoList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap)seqnoList.get(0);
			seqNo = ((Integer)lom.get("max_seqno")).intValue();
		}

		//seqno가 0이 아닐 시(답변 시)
		if(vo.getSeqno() != 0){
			vo.setPrgrs_status("C01702");
			
			//기존 질문 상태를 답변완료로 업데이트
			commonDAO.modify("clm.counsel.updatePrgrsStatusQna",vo);	
			vo.setUp_seqno(vo.getSeqno());
			
			
			//현재 SEQNO 건의 답변 건수를 구한다.
			List replyList = commonDAO.list("clm.counsel.countReplyQna", vo);
			int countReply = 0;
			if (replyList != null && replyList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap)replyList.get(0);
				countReply = ((Integer)lom.get("count_reply")).intValue();
				
				//POSTUP_DEPTH값 세팅
				vo.setPostup_depth(countReply+1);
			
			}
		}
		//seqNo값 세팅
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
		
		String decodeText = vo.getCont();
		HashMap hm = comUtilService.getNamoContentAndFileInfo(decodeText);
		vo.setCont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT"))));
		int iret = -9999;
		if(!(StringUtil.checkScript(vo.getTitle()) && StringUtil.checkScript(vo.getCont()))){
			return iret;
		}

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
		//데이터 저장
		commonDAO.insert("clm.counsel.insertQna", vo);
		
		return upSeqNo;
	}

	/**
	* QNA 권한체크
	* 
	* @param String, QnaVO
	* @return boolean
	* @throws Exception
	*/
	public boolean authQna(String mode, QnaVO vo) throws Exception {
		boolean result = true;
		boolean adminCheck = false;
		
		ListOrderedMap roleLom = new ListOrderedMap();
		//현재 사용자의 role_cd를 vo의 top_role 변수에 세팅
		roleLom = roleCdQna(vo);
		vo.setTop_role((String)roleLom.get("role_cd"));
		
		//ADMIN 체크
		if("RA00".equals(vo.getTop_role())){
			adminCheck = true;
//			if("reply".equals(mode)){
//				return false;
//			}
		}
			
		if(!adminCheck){//ADMIN 권한이 아닐 경우.
			List infoList = commonDAO.list("clm.counsel.authQna",vo);
			if(infoList != null && infoList.size() > 0){
				ListOrderedMap info = (ListOrderedMap)infoList.get(0);
				
				//수정모드 일 경우
				if(MODIFY.equals(mode)){
					if(!vo.getSession_user_id().equals((String)info.get("reg_id"))){
						result = false;
					}
				}
				//삭제모드 일 경우
				else if(DELETE.equals(mode)){
					if(!vo.getSession_user_id().equals((String)info.get("reg_id"))){
						result = false;
					}
				}
				//이관
				else if("transfer".equals(mode)){
					// 권한이 RD01 & 등록된 글과 소속이 같을 경우 & 상태가 문의인 경우 TRUE
					if("RD01".equals(vo.getTop_role()) 
						&& vo.getSession_blngt_orgnz().equals((String)info.get("blngt_orgnz"))
						&& "C01701".equals((String)info.get("prgrs_status"))){
						result = true;
					}
					else
						result = false;
				}
				//답변
				else if("reply".equals(mode)){
					
					// 권한이 RD01 & 등록된 글과 소속이 같을 경우 & 상태가 '문의'인 경우 TRUE
					if("RD01".equals(vo.getTop_role()) 
						&& vo.getSession_blngt_orgnz().equals((String)info.get("blngt_orgnz"))
						&& "C01701".equals((String)info.get("prgrs_status"))){
						result = true;
					}
					
					// 권한이 RA로 시작 & 상태가 '답변조직 이관'인 경우 TRUE
					else if("RA".equals((String)vo.getTop_role().substring(0, 2)) 
						&& "C01703".equals((String)info.get("prgrs_status"))){
						result = true;
					}
					else
						result = false;
				}
			}
		}
		return result;
	}

	/**
	* 사용자 권한조회
	* 
	* @param QnaVO
	* @return 
	* @throws Exception
	*/
	public ListOrderedMap roleCdQna(QnaVO vo) throws Exception {
		ListOrderedMap result = null;
		
		//데이터 조회
		List resultList = commonDAO.list("clm.admin.roleCdQna", vo);
		
		if(resultList != null && resultList.size() > 0){
			result = (ListOrderedMap)resultList.get(0);
		}
		return result;
	}

	/**
	 * 이관
	 */
	public int transferQna(QnaVO vo) throws Exception {
		return commonDAO.modify("clm.counsel.transferQna", vo);
	}


	/**
	 * 답변 개수 카운트
	 */
	public int countReplyQna(QnaVO vo) throws Exception {
		List replyList = commonDAO.list("clm.counsel.countReplyQna",vo);
		ListOrderedMap lom = (ListOrderedMap) replyList.get(0);		
		int result = Integer.parseInt(String.valueOf(lom.get("count_reply")));
		return result;
	}


	public int countQna(QnaVO vo) throws Exception {
		List list = commonDAO.list("clm.counsel.countQna",vo);
		ListOrderedMap lom = (ListOrderedMap) list.get(0);
		int count = Integer.parseInt(String.valueOf(lom.get("rn")));

		return count;
	}
}
