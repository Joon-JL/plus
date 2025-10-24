package com.sec.las.board.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sec.common.clmsfile.dto.ClmsFileVO;
import com.sec.las.board.dto.BbsVO;
import com.sec.las.board.dto.NoticeVO;
import com.sec.las.board.service.BbsService;

/**
 * Description	: BBS(자유게시판/Q&A/자료실/법무시스템매뉴얼) Service impl(concrete class)
 * Author 		: 박병주
 * Date			: 2013. 05. 09
 */
public class BbsServiceImpl extends CommonServiceImpl implements BbsService {
	
	// 등록/답변등록 구분자
	private static String REPLY = "1";
	
	/**
	 * 페이징 처리를 하는 리스트를 리턴한다.
	 * @param vo bbsVO
	 * @return
	 * @throws Exception
	 */
	public List listbbs(BbsVO vo) throws Exception {
		if("Y".equals(vo.getIsPds())){
			return commonDAO.list("las.board.bbs.listPds", vo);
		}
		else {
			return commonDAO.list("las.board.bbs.listBbs", vo);
		}
	}	
	/**
	 * 상세 글을 조회한다.
	 * @param vo bbsVO
	 * @return 조회 결과 없을 경우 빈 Map을 리턴한다.
	 * @throws Exception
	 */
	public List detailbbs(BbsVO vo) throws Exception {
		return commonDAO.list("las.board.bbs.detailBbs", vo);
	}
	
	/**
	 *  열린마당 게시판 글을 등록한다.
	 * @param  vo bbsVO
	 * @return int
	 * @throws Exception  sqlCUDExecutor(IQueryInfo queryInfo, Object[] values)
	 */
	public int insertbbs(BbsVO vo, String insert_kbn) throws Exception {	
		
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
		
		int sussess_kbn = 0;
		int grp_no = 0;
		int grp_seqno = 0;
		int total_seqno = 0;
		List resultList = null;

		//make sequence: total_seqno
		total_seqno = 0;
		
		resultList = commonDAO.list("las.board.bbs.getNextTotalSeqno", vo);
		
		if (resultList != null && resultList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			vo.setTotal_seqno(Integer.parseInt(String.valueOf(lom.get("nextTotalSeqno"))));
			
		} else {
			vo.setTotal_seqno(total_seqno);
		}
		
		if(insert_kbn.equals(REPLY)){
			
			//make sequence: grp_seqno
			grp_seqno = 1;
			
			resultList = commonDAO.list("las.board.bbs.getNextGrpseqno", vo);
			
			if (resultList != null && resultList.size() > 0) {
				
				ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
				vo.setGrp_seqno(Integer.parseInt(String.valueOf(lom.get("nextGrpSeqno"))));
				
			} else {
				
				vo.setGrp_seqno(grp_seqno);
			}
			
			sussess_kbn = commonDAO.insert("las.board.bbs.replyBbs", vo);
			
		} else {

			//make sequence: grp_no
			grp_no = 0;
			
			resultList = commonDAO.list("las.board.bbs.getNextGrpno", vo);
			
			if (resultList != null && resultList.size() > 0) {
				
				ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
				vo.setGrp_no(Integer.parseInt(String.valueOf(lom.get("nextGrpNo"))));
				
			} else {
				
				vo.setGrp_no(grp_no);
			}
			sussess_kbn = commonDAO.insert("las.board.bbs.insertBbs", vo);
		}	
		
		/*************************************************
		 * 첨부파일 저장
		 *************************************************/
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(Integer.toString(vo.getTotal_seqno()));
		fvo.setFileInfos(vo.getFileInfos());
		fvo.setReg_id(vo.getSession_user_id());
		
		clmsFileService.mngClmsAttachFile(fvo);
		
		return vo.getTotal_seqno();
		
	}
	

	public int updatePos(BbsVO vo) throws Exception {
		return commonDAO.modify("las.board.bbs.updatePos", vo);
	}
	
	public int updatePosDel(BbsVO vo) throws Exception {
		return commonDAO.modify("las.board.bbs.updatePosDel", vo);
	}


	
	public List detailListbbs(BbsVO vo) throws Exception {
		return commonDAO.list("las.board.bbs.detailList", vo);
	}

	public int modifybbs(BbsVO vo) throws Exception {
		
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
		
//		vo.setCont(vo.getBody_mime());
		
		result = commonDAO.modify("las.board.bbs.modifyBbs", vo);

		/*************************************************
		 * 첨부파일 저장
		 *************************************************/
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(Integer.toString(vo.getTotal_seqno()));
		fvo.setFileInfos(vo.getFileInfos());
		fvo.setReg_id(vo.getSession_user_id());
		
		clmsFileService.mngClmsAttachFile(fvo);
		
		return vo.getTotal_seqno();
	}

	public int deletebbs(BbsVO vo) throws Exception {
		
		int result=0;
		result = commonDAO.delete("las.board.bbs.deleteBbs", vo);
		
        /*************************************************
         * 첨부파일 삭제
         *************************************************/
        ClmsFileVO fvo = new ClmsFileVO();

        fvo.setSys_cd(vo.getSys_cd());
        fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
        fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(Integer.toString(vo.getTotal_seqno()));
		fvo.setFileInfos(vo.getFileInfos());
        fvo.setDel_id(vo.getSession_user_id());

        clmsFileService.delClmsAttachFile(fvo);  

		return result;
	}
	
	public void increseRdcnt(BbsVO vo) throws Exception {
		commonDAO.modify("las.board.bbs.increseRdcnt", vo) ;
	}
	
	/**
	* 표준 조항 권한조회
	* 
	* @param ItemLibraryVO
	* @return boolean
	* @throws Exception
	*/
	public boolean authBbs(String mode, BbsVO vo) throws Exception {
		boolean result = true;
		
		//관리자가 체크
		boolean adminCheck = false;
		
		for(int i=0; i<vo.getSession_user_role_cds().size();i++){
			HashMap roleCd = (HashMap)vo.getSession_user_role_cds().get(i);
			if("RA00".equals((String)roleCd.get("role_cd"))){
				adminCheck = true;
				
			// Q&A 답변은 RA00, RM00에게만 권한 부여
			}else if("RM00".equals((String)roleCd.get("role_cd")) && "C01201".equals(vo.getBbs_knd())){
				adminCheck = true;
			}
		}

		if(!adminCheck){
			List infoList = commonDAO.list("las.board.authBbs",vo);
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
	public String checkAuthByRole(BbsVO vo) throws Exception{

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
			if(userRoleList.contains("RM00") || userRoleList.contains("RA00") || userRoleList.contains("RA01") || userRoleList.contains("RA02") || userRoleList.contains("RA03")|| userRoleList.contains("RZ00")){
				accessLevel = "A";
			} else {
				accessLevel = "B";
			}
		}

		return accessLevel;
	}
	
	/**
	 * 게시물 삭제 가능 여부 체크
	 * @param BbsVO vo
	 * @return boolean
	 * @throws Exception
	 */
	@Override
	public List delCheck(BbsVO vo) throws Exception {
		// TODO Auto-generated method stub
		vo.setPostup_srt(vo.getPostup_srt()+1);
		return commonDAO.list("las.board.bbs.delCheck", vo);
		
		//다음 포스트의 뎁스가 크면 리플이 있는 것으로 간주
//		vo.setPostup_srt(vo.getPostup_srt()+1);
//		List del = 
//		BbsVO tvo = (BbsVO) del.get(0);
//		return true;
//		if(commonDAO.list("las.board.bbs.delCheck", vo)==null){
//			return true;
//		}else {
//			return false;
//		}
	}
}