/**
 * Project Name : 법무 시스템 이식
 * File name	: NoticeServiceImpl.java
 * Description	: 공지 Service impl(concrete class)
 * Author		: 한지훈
 * Date			: 2011. 08. 16
 * Copyright	:
 */
package com.sec.las.board.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;

import anyframe.core.idgen.IIdGenerationService;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sec.common.clmsfile.dto.ClmsFileVO;
import com.sec.las.board.dto.NoticeVO;
import com.sec.las.board.service.NoticeService;

/**
 * Description	: Service impl(concrete class)
 * Author		: 한지훈
 * Date			: 2011. 08. 16
 */
public class NoticeServiceImpl extends CommonServiceImpl implements NoticeService {
	/**
	 * ID 생성 서비스
	 */
	IIdGenerationService idGenerationService;
	
	/**
	 * ID 생성 서비스 세팅
	 * @param idGenerationService
	 */
	public void setIdGenerationService(IIdGenerationService idGenerationService) {
		this.idGenerationService = idGenerationService;
	}

	/**
	 * 조회
	 * @param vo NoticeVO
	 * @return List
	 * @throws Exception
	 */
	public List listNotice(NoticeVO vo) throws Exception {
		return commonDAO.list("las.board.listNotice", vo);
	}
	
	/**
	 * 등록
	 * @param  vo NoticeVO
	 * @return int
	 * @throws Exception  sqlCUDExecutor(IQueryInfo queryInfo, Object[] values)
	 */
	public int insertNotice(NoticeVO vo) throws Exception {
		int seqno = idGenerationService.getNextIntegerId();
		vo.setSeqno(seqno);
		
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
		
		/*************************************************
		 * 나모 웹 에디터 처리
		 *************************************************/
//		String decodeText 	= vo.getBody_mime();
//		String decodeTextEn = vo.getBody_mime_en();
//		
//		HashMap hm 		= comUtilService.getNamoContentAndFileInfo(decodeText);
//		HashMap hmEn 	= comUtilService.getNamoContentAndFileInfo(decodeTextEn); 
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
//			vo.setCont_en((StringUtil.convertNamoCharsToHtml((String)hmEn.get("CONTENT"))));
//		}else {
//			vo.setCont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
//			vo.setCont_en((StringUtil.convertNamoCharsToHtml((String)hmEn.get("CONTENT"))));
//		}
		
		vo.setCont_en(vo.getBody_mime_en());
		
		clmsFileService.mngClmsAttachFile(fvo);
		
		commonDAO.insert("las.board.insertNotice", vo);
				
		return seqno;
	}
	
	/**
	 * 수정
	 * @param  vo NoticeVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyNotice(NoticeVO vo) throws Exception {
		
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
//		String decodeText 	= vo.getBody_mime();
//		String decodeTextEn = vo.getBody_mime_en(); 
//		
//		HashMap hm = comUtilService.getNamoContentAndFileInfo(decodeTextEn);
//		HashMap hmEn = comUtilService.getNamoContentAndFileInfo(decodeTextEn);
//		
//		if (hmEn.get("TYPE").equals("M")) {
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
//			vo.setCont_en((StringUtil.convertNamoCharsToHtml((String)hmEn.get("CONTENT"))));
//		}else {
//			vo.setCont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT"))));
//			vo.setCont_en((StringUtil.convertNamoCharsToHtml((String)hmEn.get("CONTENT"))));
//		}
		vo.setCont_en(vo.getBody_mime_en());
		
		return commonDAO.modify("las.board.modifyNotice", vo);
	}
	
	/**
	 * 삭제
	 * @param  vo NoticeVO
	 * @return int
	 * @throws Exception
	 */
	public int deleteNotice(NoticeVO vo) throws Exception {

		int result=0;
		result = commonDAO.delete("las.board.deleteNotice", vo);
		
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
	 * 상세조회
	 * @param  vo NoticeVO
	 * @return List
	 * @throws Exception
	 */
	public List detailNotice(NoticeVO vo) throws Exception {
		return commonDAO.list("las.board.detailNotice", vo);
	}
	
	/**
	 * 조회수 증가
	 * @param vo
	 * @throws Exception
	 */
	public void increaseRdCnt(NoticeVO vo) throws Exception {
		commonDAO.modify("las.board.increaseRdCnt", vo) ;
	}
	
	/**
	* 표준 조항 권한조회
	* 
	* @param ItemLibraryVO
	* @return boolean
	* @throws Exception
	*/
	public boolean authNotice(String mode, NoticeVO vo) throws Exception {
		boolean result = true;
		
		//관리자가 체크
		boolean adminCheck = false;
		
		for(int i=0; i<vo.getSession_user_role_cds().size();i++){
			HashMap roleCd = (HashMap)vo.getSession_user_role_cds().get(i);
			if("RA00".equals((String)roleCd.get("role_cd")) || "RM00".equals((String)roleCd.get("role_cd"))){
				adminCheck = true;
			}
		}

		if(!adminCheck){
			List infoList = commonDAO.list("las.board.authNotice",vo);
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
	public String checkAuthByRole(NoticeVO vo) throws Exception{
		try {

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
			
			// FAQ의 경우
			if("FAQ".equals(vo.getAnnce_knd())){
				if(userRoleList != null && userRoleList.size() > 0){
					if(userRoleList.contains("RA00") || userRoleList.contains("RM00"))
						accessLevel = "A";
					else 
						accessLevel = "B";
				}
			// 법무공지 / 공지 등
			}else{
				if(userRoleList != null && userRoleList.size() > 0){
					if(userRoleList.contains("RA00") || userRoleList.contains("RM00") || userRoleList.contains("RA01") || 
						userRoleList.contains("RA02") || userRoleList.contains("RA03")){
						accessLevel = "A";
					} else {
						accessLevel = "B";
					}
				}
			}
			return accessLevel;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("NoticeController.detailNotice() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.detailNotice() Throwable !!");
		}
	}
	
	/**
	 * 법무 메인 - 공지사항 조회
	 * @param vo NoticeVO
	 * @return
	 * @throws Exception
	 */
	public List listNoticeByMain(NoticeVO vo) throws Exception {
		return commonDAO.list("las.board.listNoticeByMain", vo);
	}
	
	/**
	 * 전결규정 해당회사 MAX SEQNO 조회 
	 * @param vo NoticeVO
	 * @return 
	 * @throws Exception
	 */
	public List getMaxSeqNoByCompCd(NoticeVO vo) throws Exception{
		return commonDAO.list("las.board.getMaxSeqNoByCompCd",vo);
	}

	/**
	 * 기존에 팝업공지 설정된 공지사항이 있는지 조회하여 게시글의 SEQNO를 리턴 
	 * @param vo NoticeVO
	 * @return
	 * @throws Exception
	 */
	public List listNoticeLatestSeqno(NoticeVO vo) throws Exception{
		return commonDAO.list("las.board.listNoticeLatestSeqno",vo);
	}
	
	/**
	 * 기존에 팝업공지 설정된 공지사항의 팝업YN 값을 업데이트 
	 * @param vo NoticeVO
	 * @return 
	 * @throws Exception
	 */
	public int updateNoticePopupValue(NoticeVO vo) throws Exception{
		return commonDAO.modify("las.board.updateNoticePopupValueOff", vo);
	}
	
}
