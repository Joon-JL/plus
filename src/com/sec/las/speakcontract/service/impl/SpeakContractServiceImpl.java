/**
* Project Name : 법무시스템 이식
* File Name : SpeakContractController.java
* Description : 구두계약 서비스 구현(Impl)
* Author : 김현구
* Date : 2011. 08. 05
* Copyright : 
*/

package com.sec.las.speakcontract.service.impl;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import anyframe.core.idgen.IIdGenerationService;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sec.common.clmsfile.dto.ClmsFileVO;
import com.sec.las.speakcontract.dto.SpeakContractVO;
import com.sec.las.speakcontract.service.SpeakContractService;


/**
 * Description	: 구두계약 Service 구현체(impl)
 * Author 		: 김현구
 * Date			: 2011. 08. 05
 */
public class SpeakContractServiceImpl extends CommonServiceImpl implements SpeakContractService {
	
	/**
	 * ID 생성 서비스
	 */
	IIdGenerationService idGenerationService ;
	
	/**
	 * ID 생성 서비스 세팅
	 * @param idGenerationService
	 */
	public void setIdGenerationService(IIdGenerationService idGenerationService) {
		this.idGenerationService = idGenerationService;
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
		vo.setSeqno(idGenerationService.getNextIntegerId());
		
		//test를 위한 데이터 세팅
		vo.setReqman_dept("test");
		vo.setReqman_id("test");
		
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
		
		return commonDAO.insert("las.speakcontract.insert", vo);
	}


	/**
	 * 수정
	 */
	public int modifySpeakContract(SpeakContractVO vo) throws Exception {
		
		//test를 위한 데이터 세팅
		vo.setReqman_dept("test");
		vo.setReqman_id("test");
		
		//수정자 정보 세션에서 받아와 vo에 세팅
		vo.setMod_nm(vo.getSession_user_nm());
		vo.setMod_id(vo.getSession_user_id());
		
		//XSS 처리
		convertHtmlTochars(vo);
		
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
		/*Map map = null; 
		
		//상세조회할 데이터를 불러온다
		List list = commonDAO.list("las.speakcontract.detail", vo);
		
		//데이터가 있으면 map에 저장
		if(list!=null && list.size() != 0){
			map = (Map)list.get(0);
		}
		//데이터가 없으면
		else if(list.size() == 0){
			map = new ListOrderedMap();
		}
		return map;
		*/
		
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
		//textarea 줄바꿈 처리를 위해 \r\n을 <br>로 치환
		vo.setRe_cont(StringUtil.replace(vo.getRe_cont(),"\r\n", "<br>"));
		
		//문자열 XSS 처리
		vo.setTitle(StringUtil.convertHtmlTochars(vo.getTitle()));
		vo.setReqman_dept_nm(StringUtil.convertHtmlTochars(vo.getReqman_dept_nm()));
		vo.setReqman_nm(StringUtil.convertHtmlTochars(vo.getReqman_nm()));
		vo.setRe_cont(StringUtil.convertHtmlTochars(vo.getRe_cont()));
		
		//<br>태그는 XSS처리 예외 적용
		vo.setRe_cont(StringUtil.replace(vo.getRe_cont(),"&lt;br&gt;", "<br>"));
		
	}

}