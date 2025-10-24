package com.sds.secframework.bbs.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import anyframe.core.idgen.IIdGenerationService;

import com.sds.secframework.bbs.dto.BBSVO;
import com.sds.secframework.bbs.service.BBSService;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.FormatUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.file.dto.AttachFileVO;

public class BBSServiceImpl extends CommonServiceImpl implements BBSService {

	/**
	 * ID 값을 생성하기 위한 idGeneration 선언 및 세팅
	 */
	private IIdGenerationService idGenerationService = null;
	public void setIdGenerationService(IIdGenerationService idGenerationService) {
		this.idGenerationService = idGenerationService;
	}

	
	/**********************************************************
	 * 게시판
	 **********************************************************/

	/**
	 * 게시글 목록조회
	 * <P>
	 *  0. 게시물 목록조회
	 *  1. 말머리 조회
	 *  2. 년도 (앞뒤로 5년)
	 * 
	 * @param
	 *           
	 * @return  
	 * @throws
	 */
	public List listBBSDetailPage(BBSVO vo) throws Exception {
		
		/***************************************************
		 * - Logging 사용방법
		 *    : Service의 경우 extends CommonService 
		 *   그리고, 아래처럼 사용
		 * *************************************************/ 
		
		return commonDAO.list("secfw.bbs.listBBSDetailPage", vo);
	}
	
	/**
	 * 게시글 상세조회
	 * <P>
	 * 0. 조회카운트 증가
	 * 1. 내역조회
	 * 2. 첨부조회
	 * 3. 덧글조회
	 * 4. 이전글/다음글 NOTICE_ID 조회
	 * 5. 추천여부 조회
	 * @param 게시판아이디, 게시글 아이디.
	 *           
	 * @return  
	 * @throws
	 */
	public HashMap detailBBSDetail(BBSVO vo) throws Exception{

		HashMap result = new HashMap();
		
		// 0. 조회카운트 증가 (sys_cd, bbs_id, notice_id)
		if(!"N".equals(vo.getRef_cnt_apply_yn())) {
			
			String userId = vo.getUser_id();
			String regId  = "";
			List BBSDetailTemp = commonDAO.list("secfw.bbs.detailBBSDetail", vo);
			
			if(BBSDetailTemp != null && BBSDetailTemp.size()>0) {
				ListOrderedMap lom = (ListOrderedMap)BBSDetailTemp.get(0);
				regId = (String)lom.get("reg_id");
			}
			
			//세션사용자 아이디와 등록자 아이디가 서로 다르면 조회수 증가
			if(!userId.equals(regId)) {
				commonDAO.modify("secfw.bbs.modifyDetailRefCnt", vo);
			}
		}
		
		// 1. 내역조회 (sys_cd, bbs_id, notice_id)		
		List getBBSDetail = commonDAO.list("secfw.bbs.detailBBSDetail", vo);
		
		// 2. 첨부조회 (sys_cd, file_ref_no)
		HashMap fileHm = new HashMap();
		fileHm.put("sys_cd", vo.getSys_cd());
		if(getBBSDetail != null && getBBSDetail.size()>0) {
			ListOrderedMap lom = (ListOrderedMap)getBBSDetail.get(0);
			fileHm.put("file_ref_no", (String)lom.get("file_ref_no"));
		}
		
		// 파일정보 조회 -> 파일스트링 변환
		List fileList = commonDAO.list("secfw.file.listAttachFileInfo", fileHm);
		String listBBSFile = attachFileService.getFileInfosToString(fileList);
		
		// 3. 덧글조회 (sys_cd, bbs_id, notice_id)
		List listBBSAppend = commonDAO.list("secfw.bbs.listBBSAppend", vo);
				
		// 4. 이전글, 다음글 NoticeID조회 (sys_cd, bbs_id, notice_id)
		List getBBSPrevNextID = commonDAO.list("secfw.bbs.getBBSPrevNextID", vo);

		// 5. 이전글, 다음글 제목 조회
		String strPrevID = "";
		String strNextID = "";
		
		String strPrevTitle = "";
		String strNextTitle = "";
		
		if(getBBSPrevNextID != null && getBBSPrevNextID.size()>0) {
			ListOrderedMap lomPrev = (ListOrderedMap)getBBSPrevNextID.get(0);
			strPrevID = (String)lomPrev.get("notice_id");

			ListOrderedMap lomNext = (ListOrderedMap)getBBSPrevNextID.get(1);
			strNextID = (String)lomNext.get("notice_id");
		}
		
		//이전글
		if(!"N".equals(strPrevID)) {
			vo.setNotice_id(strPrevID);
			List getBBSDetailTitlePrev = commonDAO.list("secfw.bbs.detailBBSDetail", vo);
		
			if(getBBSDetailTitlePrev != null && getBBSDetailTitlePrev.size()>0) {
				ListOrderedMap lom = (ListOrderedMap)getBBSDetailTitlePrev.get(0);
				strPrevTitle = (String)lom.get("notice_title");
			}
		
		} 
		//다음글
		if(!"N".equals(strNextID)) {
			vo.setNotice_id(strNextID);
			List getBBSDetailTitleNext = commonDAO.list("secfw.bbs.detailBBSDetail", vo);
		
			if(getBBSDetailTitleNext != null && getBBSDetailTitleNext.size()>0) {
				ListOrderedMap lom = (ListOrderedMap)getBBSDetailTitleNext.get(0);
				strNextTitle = (String)lom.get("notice_title");
			}
		
		} 
		
		// 6. 추천여부 (sys_cd, bbs_id, notice_id)
		List temp = commonDAO.list("secfw.bbs.getBBSRecommend", vo);
		String getBBSRecommendYn = "";
		
		if(temp != null && temp.size()>0){
			ListOrderedMap lm = (ListOrderedMap)temp.get(0);
			getBBSRecommendYn = (String)lm.get("gbn");
		}
				
		result.put("getBBSDetail", getBBSDetail);
		result.put("listBBSFile", listBBSFile);
		result.put("listBBSAppend", listBBSAppend);
		result.put("getBBSPrevNextID", getBBSPrevNextID);
		result.put("getPrevTitle", strPrevTitle);
		result.put("getNextTitle", strNextTitle);
		result.put("getBBSRecommendYn", getBBSRecommendYn);

		return result;

	}
	
	/**
	 * 게시글 등록
	 * <P>
	 * 0. 게시글관련 아이디 채번
	 * 1. 첨부등록
	 * 2. 게시글 등록(임시저장)
	 * @param
	 *           
	 * @return  
	 * @throws
	 */
	public String insertBBSDetail(BBSVO vo) throws Exception{
		
		/***************************************
		 *  [아이디 채번]
		 *    - 시스템코드 채번
		 *    - 게시글 아이디 채번
		 *    - 상위게시글 아이디 채번
		 *    - 게시글 정렬순서 채번(동일레벨)
		 *    - 게시글 깊이 세팅
		 *    - 첨부파일 일련번호 채번
		****************************************/		

		getLogger().debug("debug");
		getLogger().info("info");
		getLogger().error("error");
		
		//게시글 아이디 채번
		String noticeId = getNoticeId();

		//상위게시글 아이디 채번
		String upNoticeId  = "*";
		
		//게시글 정렬순번 채번(동일레벨)
		vo.setUp_notice_id(upNoticeId);
		int    noticeOrder = getBBSDetailNoticeOrder(vo);
		
		//게시글 깊이 세팅
		int    noticeDepth = 1;
		
		//첨부파일 일련번호 채번
		String fileRefNo = "";
		
		/***************************************************
		 * 1. 첨부등록
		 * *************************************************/ 
		String fileInfos = vo.getFileInfos();
		
		// 첨부파일 정보가 있으면
		if(attachFileService.isExistsFileInfos(fileInfos)) {
			
			fileRefNo = attachFileService.getFileRefNo();
			
			List fileList = attachFileService.getFileVOFromFileInfos(fileInfos);
			AttachFileVO fileVO = null;
			
			for(int i = 0; i < fileList.size(); i++) {
	
				fileVO = (AttachFileVO)fileList.get(i);	
				
				//시스템코드, 첨부파일일련번호  추가
				fileVO.setSys_cd(vo.getSys_cd());
				fileVO.setFile_ref_no(fileRefNo);

				commonDAO.insert("secfw.file.insertAttachFile", fileVO);
			}
			
		}

		/***************************************************
		 * 2. 게시글 등록
		 * *************************************************/ 
		vo.setSys_cd(vo.getSys_cd());                                              // 시스템코드
		vo.setNotice_id(noticeId);                                                 // 게시글아이디
		vo.setUp_notice_id(upNoticeId);                                            // 상위게시글아이디  
		vo.setNotice_title(StringUtil.convertHtmlTochars(vo.getNotice_title()));   // 게시글타이틀
		vo.setNotice_grp_id(noticeId);                                             // 글그룹아이디
		vo.setNotice_depth(noticeDepth);                                           // 게시글 깊이
		vo.setNotice_order(noticeOrder);                                           // 게시글 순서
		vo.setFile_ref_no(fileRefNo);                                              // 첨부파일 일련번호
		
		commonDAO.insert("secfw.bbs.insertBBSDetail", vo);
		
		return noticeId;
	}
	
	/**
	 * 댓글 등록하기 위하여 조회
	 * <P>
	 * @param BBSVO vo 게시판VO
	 *           
	 * @return  
	 * @throws
	 */
	public List getBBSDetailForReply(BBSVO vo) throws Exception{
		return commonDAO.list("secfw.bbs.detailBBSDetail", vo);
	}

	/**
	 * 댓글 등록
	 * <P>
	 * 1. 첨부등록
	 * 2. 댓글 등록(임시저장 없음)
	 * 3. 원글사용자에게 메신저 전송이 체크되어 있을경우 알림메시지 전송
	 * @param
	 *           
	 * @return  
	 * @throws
	 */
	public String insertBBSDetailReply(BBSVO vo) throws Exception{
	
		/***************************************
		 *  0. 게시글 아이디 채번
		 *     게시판정렬순번 채번
		 *     상위게시글 아이디 세팅
		 *     게시글 깊이 세팅
		****************************************/
		//게시글 아이디 채번
		String noticeId    = getNoticeId();
		vo.setNotice_id(noticeId);            // 게시글아이디
		
		//상위글 아이디 		
		String upNoticeId  = vo.getUp_notice_id();
		
		// 게시글 그룹아이디 채번
		String noticeGrpId = getBBSDetailNoticeGrpId(vo);
		
		// 게시글 순서  채번
		int noticeOrder = getBBSDetailNoticeOrder(vo);
		
		// 게시글 깊이 채번
		int noticeDepth = getBBSDetailChildNoticeDepth(vo);
		
		//첨부파일 일련번호 채번
		String fileRefNo = "";
		
		/***************************************************
		 * 1. 첨부등록
		 * *************************************************/ 
		String fileInfos = vo.getFileInfos();
		
		// 첨부파일 정보가 있으면
		if(attachFileService.isExistsFileInfos(fileInfos)) {
			
			fileRefNo = attachFileService.getFileRefNo();
			
			List fileList = attachFileService.getFileVOFromFileInfos(fileInfos);
			AttachFileVO fileVO = null;
			
			for(int i = 0; i < fileList.size(); i++) {
	
				fileVO = (AttachFileVO)fileList.get(i);	
				
				//시스템코드, 첨부파일일련번호  추가
				fileVO.setSys_cd(vo.getSys_cd());
				fileVO.setFile_ref_no(fileRefNo);
				
				commonDAO.insert("secfw.file.insertAttachFile", fileVO);
			}
			
		}

		/***************************************************
		 * 2. 게시글 등록
		 * *************************************************/ 
		vo.setUp_notice_id(upNoticeId);                                            // 상위게시글아이디(게시원글)  
		vo.setNotice_title(StringUtil.convertHtmlTochars(vo.getNotice_title()));   // 게시글타이틀
		vo.setNotice_grp_id(noticeGrpId);                                          // 글그룹아이디
		vo.setNotice_depth(noticeDepth);                                           // 게시글 깊이
		vo.setNotice_order(noticeOrder);                                           // 게시글 순서
		vo.setFile_ref_no(fileRefNo);                                              // 첨부파일 일련번호
				
		commonDAO.insert("secfw.bbs.insertBBSDetail", vo);
		
		/***************************************************
		 * 3. 게시댓글 등록 후 정렬순서 정렬          	   *    
		 * *************************************************/ 
		List tempOrder = commonDAO.list("secfw.bbs.listBBSChildDisp", vo);
		if(tempOrder!=null && tempOrder.size() >0) {
			
			HashMap dispHm = null;
			
			for(int i=0;i<tempOrder.size();i++) {

				ListOrderedMap tempOrderLM = (ListOrderedMap)tempOrder.get(i);
				
				dispHm = new HashMap();
				
				dispHm.put("sys_cd", vo.getSys_cd());
				dispHm.put("bbs_id", vo.getBbs_id());
				dispHm.put("notice_id", (String)tempOrderLM.get("notice_id"));
				dispHm.put("notice_disp", (BigDecimal)tempOrderLM.get("rn"));

				commonDAO.modify("secfw.bbs.modifyBBSChildDisp", dispHm);
			}
		}
				
		return noticeId;
	}
	
	/**
	 * 게시글 수정(게시글 본인에 한하여)
	 * <P>
	 * 0. 첨부추가 및 첨부삭제
	 * 1. 게시글, 댓글 수정
     *
	 * @param
	 *           
	 * @return  
	 * @throws
	 */
	public int modifyBBSDetail(BBSVO vo) throws Exception{
		
		int result = -1;
		
		/***************************************************
		 * 0. 첨부삭제          	       
		 * *************************************************/ 
		String fileInfos = vo.getFileInfos();
		if(attachFileService.isExistsFileInfos(fileInfos)) {

			List fileList = attachFileService.getFileVOFromFileInfos(fileInfos);
			AttachFileVO fileVO = null;
			
			for(int i = 0; i < fileList.size(); i++) {
	
				fileVO = (AttachFileVO)fileList.get(i);	
				
				//시스템코드, 첨부파일일련번호  추가
				fileVO.setSys_cd(vo.getSys_cd());
				fileVO.setFile_ref_no(vo.getFile_ref_no());
				
				commonDAO.delete("secfw.file.deleteAttachFileAll", vo);
			}			
		}
		
		/***************************************************
		 * 1. 첨부추가          	       
		 * *************************************************/ 
		// 첨부파일 정보가 있으면
		if(attachFileService.isExistsFileInfos(fileInfos)) {
			
			String fileRefNo = vo.getFile_ref_no();
			
			List fileList = attachFileService.getFileVOFromFileInfos(fileInfos);
			AttachFileVO fileVO = null;
			
			for(int i = 0; i < fileList.size(); i++) {
	
				fileVO = (AttachFileVO)fileList.get(i);	
				
				//시스템코드, 첨부파일일련번호  추가
				fileVO.setSys_cd(vo.getSys_cd());
				fileVO.setFile_ref_no(fileRefNo);
				
				commonDAO.insert("secfw.file.insertAttachFile", fileVO);
			}			
		}
		
		/***************************************************
		 * 2. 게시글, 댓글(Reply) 수정          	       
		 * *************************************************/ 
		result = commonDAO.modify("secfw.bbs.modifyBBSDetail", vo);
		return result;
	}
	
	/**
	 * 게시글 삭제(게시글 본인에 한하여)
	 * <P>
	 *  0. 해당글 + 하위글의 게시판아이디(NOTICE_ID) 받아오기.
	 *  1. 해당글 및 하위글 추천정보 삭제
	 *  2. 해당글 및 하위글의 덧글(한줄답변)삭제
	 *  3. 해당글 및 하위글의 첨부삭제
	 *  4. 해당글 및 게시글삭제
	 * @param
	 *           
	 * @return
	 * @throws
	 */
	public int deleteBBSDetail(BBSVO vo) throws Exception{
		int result = -1;
		
		// 0. 해당글 + 하위글의 게시글 아이디(NOTICE_ID) 받아오기.
		List noticeIdAl = commonDAO.list("secfw.bbs.listBBSChildNoticeId", vo);
				
		for(int i=0; i<noticeIdAl.size();i++){
			
			ListOrderedMap lm = (ListOrderedMap)noticeIdAl.get(i);
			
			//해당글 및 하위글의 게시글 아이디
			String noticeId = (String)lm.get("notice_id");
			
			//해당글 및 하위글의 첨부파일일련번호
			String fileRefNo = (String)lm.get("file_ref_no");
			
			vo.setNotice_id(noticeId);
			
			// 1. 해당글 및 하위글 추천정보 삭제
			commonDAO.delete("secfw.bbs.deleteBBSRecommend", vo);

			// 2. 해당글 및 하위글의 덧글(한줄답변)삭제
			commonDAO.delete("secfw.bbs.deleteBBSAppendAll", vo);

			// 3. 해당글 및 하위글의 첨부삭제
			if(fileRefNo != null && fileRefNo.length() > 1) {
				AttachFileVO attachFileVO = new AttachFileVO();
				attachFileVO.setSys_cd(vo.getSys_cd());
				attachFileVO.setFile_ref_no(fileRefNo);
				
				attachFileService.deleteFile(attachFileVO);
			}
			
			// 4. 게시글 및 댓글삭제
			commonDAO.delete("secfw.bbs.deleteBBSDetail", vo);

		}
		return result;
	}
	
	/**
	 * 덧글등록
	 * <P>
	 * 
	 * @param
	 *           
	 * @return  
	 * @throws
	 */
	public int insertBBSAppend(BBSVO vo) throws Exception{
		
		int result = -1;
		
		List temp = commonDAO.list("secfw.bbs.getBBSAppendSeq", vo);
		String appendSeq = "1";
		
		if(temp != null && temp.size() > 0) {
			ListOrderedMap lm = (ListOrderedMap)temp.get(0);
			appendSeq = FormatUtil.formatNumToString(lm.get("append_seq"));
		}
		
		vo.setAppend_seq(appendSeq);
		
		result = commonDAO.insert("secfw.bbs.insertBBSAppend", vo);
		
		return result;
	}
	
	/**
	 * 덧글수정(덧글본인에 한하여)
	 * <P>
	 * 
	 * @param
	 *           
	 * @return  
	 * @throws
	 */
	public int modifyBBSAppend(BBSVO vo) throws Exception{
		int result = -1;
		result = commonDAO.modify("secfw.bbs.modifyBBSAppend", vo);
		
		return result;
	}
	
	/**
	 * 덧글삭제(덧글본인에 한하여)
	 * <P>
	 * 
	 * @param
	 *           
	 * @return  
	 * @throws
	 */
	public int deleteBBSAppend(BBSVO vo) throws Exception{
		int result = -1;
		result = commonDAO.modify("secfw.bbs.deleteBBSAppend", vo);

		return result;
	}
	
	/**
	 * 추천등록
	 * <P>
	 * 0. 추천등록
	 * 1. 게시글 추천카운트 증가
	 * @param
	 *           
	 * @return  
	 * @throws
	 */
	public int insertBBSRecommend(BBSVO vo) throws Exception{
		int result = -1;

		List temp = commonDAO.list("secfw.bbs.getBBSRecommend", vo);
		String gbn = "";
		
		if(temp != null && temp.size()>0){
			ListOrderedMap lm = (ListOrderedMap)temp.get(0);
			gbn = (String)lm.get("gbn");
		}
		
		if("N".equals(gbn)) {
			// 0. 추천등록		
			result = commonDAO.modify("secfw.bbs.insertBBSRecommend", vo);
			// 1. 게시글 추천카운트 증가
			result = commonDAO.modify("secfw.bbs.modifyDetailRecommendCnt", vo);
			//추천등록 성공함
			result = 1;
		} else {
			//추천결과가 있음
			result = 0;
		}
		
		return result;
	}
	
	/**********************************************************
	 * 게시판 Utility Method
	 **********************************************************/	
	/**
	 * 게시판관련 아이디 채번<P>
	 *
	 * @param 
	 * @return 게시판아이디, 게시글 아이디
	 * @throws Exception
	*/
	private String getNoticeId() throws Exception {
		return DateUtil.getTime("yyyyMMddHHmmssSSS") + idGenerationService.getNextStringId();		
	}
	
	/**
	 * 게시글 그룹아이디 채번<P>
	 *
	 * @param 
	 * @return 게시판정렬순번
	 * @throws Exception
	*/
	private String getBBSDetailNoticeGrpId(BBSVO vo) throws Exception {
		
		String result = "";
		List temp = commonDAO.list("secfw.bbs.getBBSDetailNoticeGrpId", vo);
		
		if(temp != null && temp.size() > 0) {
			ListOrderedMap lm = (ListOrderedMap)temp.get(0);
			
			result = (String)lm.get("notice_grp_id");
		}
		return result;
	}

	/**
	 * 게시글 정렬순번 채번<P>
	 *
	 * @param 
	 * @return 게시판정렬순번
	 * @throws Exception
	*/
	private int getBBSDetailNoticeOrder(BBSVO vo) throws Exception {
		int result = 1;
		
		List temp = commonDAO.list("secfw.bbs.getBBSDetailNoticeOrder", vo);
		
		if(temp != null && temp.size() > 0) {
			ListOrderedMap lm = (ListOrderedMap)temp.get(0);
			
			result = FormatUtil.formatInt(lm.get("notice_order"));
		}
		
		return result;
	}

	/**
	 * 자식노드 게시글 깊이 채번<P>
	 *
	 * @param 
	 * @return 자식노드 게시글 깊이
	 * @throws Exception
	*/
	private int getBBSDetailChildNoticeDepth(BBSVO vo) throws Exception {
		int rtnValue = -1;
				
		List temp = commonDAO.list("secfw.bbs.getBBSDetailChildNoticeDepth", vo);
		
		if(temp != null && temp.size() > 0) {
			ListOrderedMap lm = (ListOrderedMap)temp.get(0);
			
			rtnValue = FormatUtil.formatInt(lm.get("notice_depth"));
		}
		
		return rtnValue;
	}
		
}
