/**
* Project Name : 법무시스템 이식
* File Name : SpeakContractController.java
* Description : 구두계약 서비스 구현(Impl)
* Author : 김현구
* Date : 2011. 08. 05
* Copyright : 
*/

package com.sec.las.contractmanager.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;

import anyframe.core.idgen.IIdGenerationService;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.singleIF.service.EsbMailService;
import com.sec.clm.manage.service.MailSendService;
import com.sec.common.clmsfile.dto.ClmsFileVO;
import com.sec.las.contractmanager.dto.SpeakContractVO;
import com.sec.las.contractmanager.service.SpeakContractService;


/**
 * Description	: 구두계약 Service 구현체(impl)
 * Author 		: 김현구
 * Date			: 2011. 08. 05
 */
public class SpeakContractServiceImpl extends CommonServiceImpl implements SpeakContractService {
	
//	/**
//	 * ID 생성 서비스
//	 */
//	IIdGenerationService idGenerationService ;
//	
//	/**
//	 * ID 생성 서비스 세팅
//	 * @param idGenerationService
//	 */
//	public void setIdGenerationService(IIdGenerationService idGenerationService) {
//		this.idGenerationService = idGenerationService;
//	}
	
	/**
	 * 싱글 메일 서비스
	 */
	private EsbMailService esbMailService = null;
	
	/**
	 * 싱글 메일 서비스 세팅
	 * @param esbMailService
	 */
	public void setEsbMailService(EsbMailService esbMailService) {
		this.esbMailService = esbMailService;
	}
	
	
	private MailSendService mailSendService ;

	public void setMailSendService(MailSendService mailSendService) {
		this.mailSendService = mailSendService;
	}

	/**
	 * List 출력
	 */
	public List listSpeakContract(SpeakContractVO vo) throws Exception {
		
		// 검색관련변수 XXS 처리
		vo.setSrch_respman_nm(StringUtil.convertHtmlTochars(vo.getSrch_respman_nm()));
		vo.setSrch_title(StringUtil.convertHtmlTochars(vo.getSrch_title()));
		
		return commonDAO.list("las.speakcontract.list", vo);
	}


	/**
	 * 삽입
	 * 삽입하기 전 등록자 정보 세팅 및 XSS처리 
	 */
	public int insertSpeakContract(SpeakContractVO vo) throws Exception {
		
		//seqno 세팅
		//vo.setSeqno(idGenerationService.getNextIntegerId());
		
		ListOrderedMap maxseqLom = (ListOrderedMap) commonDAO.list("las.speakcontract.maxseq",vo).get(0);
		int maxseq = Integer.parseInt(String.valueOf(maxseqLom.get("maxseq")));
		vo.setSeqno(maxseq+1);
		
		//등록자 정보 세션에서 받아와 vo에 세팅
		vo.setRespman_nm(vo.getSession_user_nm());
		vo.setRespman_dept(vo.getSession_dept_cd());
		vo.setRespman_dept_nm(vo.getSession_dept_nm());
		vo.setRespman_id(vo.getSession_user_id());
		
		//XSS 처리
		convertHtmlTochars(vo);
		
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
		String decodeText = vo.getBody_mime();
		HashMap hm = comUtilService.getNamoContentAndFileInfo(decodeText);

		if (hm.get("TYPE").equals("M")) {
			ArrayList fileList = (ArrayList) hm.get("FILE_INFO");

			for (int i = 0; i < fileList.size(); i++) {
				HashMap fileMap = (HashMap) fileList.get(i);

				Integer seq = new Integer(i);
				String fileNm = (String) fileMap.get("FILE_NM");
				String filePath = (String) fileMap.get("FILE_PTH");
				String fileUrl = (String) fileMap.get("FILE_URL");

				File f = new File(filePath);
				Long fileSize = new Long(f.length());
			}
			vo.setRe_cont((StringUtil.convertNamoCharsToHtml((String) hm.get("CONTENT")))); // Cross-site
																							// Scripting
																							// 방지
																							// 처리
		} else {
			vo.setRe_cont((StringUtil.convertNamoCharsToHtml((String) hm.get("CONTENT")))); // Cross-site
																							// Scripting
																							// 방지
																							// 처리
		}
		
		return commonDAO.insert("las.speakcontract.insert", vo);
	}


	/**
	 * 수정
	 */
	public int modifySpeakContract(SpeakContractVO vo) throws Exception {
		
		//수정자 정보 세션에서 받아와 vo에 세팅
		vo.setMod_nm(vo.getSession_user_nm());
		vo.setMod_id(vo.getSession_user_id());
		
		//XSS 처리
		convertHtmlTochars(vo);
		
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
		String decodeText = vo.getBody_mime();
		HashMap hm = comUtilService.getNamoContentAndFileInfo(decodeText);

		if (hm.get("TYPE").equals("M")) {
			ArrayList fileList = (ArrayList) hm.get("FILE_INFO");

			for (int i = 0; i < fileList.size(); i++) {
				HashMap fileMap = (HashMap) fileList.get(i);

				Integer seq = new Integer(i);
				String fileNm = (String) fileMap.get("FILE_NM");
				String filePath = (String) fileMap.get("FILE_PTH");
				String fileUrl = (String) fileMap.get("FILE_URL");

				File f = new File(filePath);
				Long fileSize = new Long(f.length());
			}
			vo.setRe_cont((StringUtil.convertNamoCharsToHtml((String) hm.get("CONTENT")))); // Cross-site
																							// Scripting
																							// 방지
																							// 처리
		} else {
			vo.setRe_cont((StringUtil.convertNamoCharsToHtml((String) hm.get("CONTENT")))); // Cross-site
																							// Scripting
																							// 방지
																							// 처리
		}
		
		return commonDAO.modify("las.speakcontract.modify", vo);
	}

	/**
	 * 삭제
	 */
	public int deleteSpeakContract(SpeakContractVO vo) throws Exception {
		
		//삭제자 정보 세션에서 받아와 vo에 세팅
		vo.setDel_nm(vo.getSession_user_nm());
		vo.setDel_id(vo.getSession_user_id());
		
		 return commonDAO.delete("las.speakcontract.delete", vo);
	}

	/**
	 * 상세보기
	 */
	public ListOrderedMap detailSpeakContract(SpeakContractVO vo) throws Exception {
		
		//상세조회할 데이터를 불러온다
		List list = commonDAO.list("las.speakcontract.detail", vo);
		ListOrderedMap lom = new ListOrderedMap();
		
		if(list!=null && list.size() != 0){
			lom = (ListOrderedMap)list.get(0);
		}

		return lom;
	}
	
	/**
	 * <br> 처리 및 XSS 처리
	 * @param vo
	 * @throws Exception
	 */
	public void convertHtmlTochars(SpeakContractVO vo) throws Exception {
		
		//문자열 XSS 처리
		vo.setTitle(StringUtil.convertHtmlTochars(vo.getTitle()));
		vo.setReqman_dept_nm(StringUtil.convertHtmlTochars(vo.getReqman_dept_nm()));
		vo.setReqman_nm(StringUtil.convertHtmlTochars(vo.getReqman_nm()));
		
	}
	
	/**
	 * 상위 메뉴 ID를 가져온다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public String getUpMenuId(SpeakContractVO vo) throws Exception {
		String upMenuId = null ;
		
		List list = commonDAO.list("secfw.menu.detail", vo) ;
		if(list!=null && list.size()==1) {
			upMenuId = (String)(((Map)list.get(0)).get("up_menu_id")) ;
		}
				
		return upMenuId ;
	}

}