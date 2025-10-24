/**
* Project Name : 계약관리시스템
* File Name : RegulationServiceImpl.java
* Description : 프로세스&규정지침서 ServiceImpl
* Author : 신남원
* Date : 2010.09.01
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.admin.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.admin.dto.RegulationVO;
import com.sec.clm.admin.service.RegulationService;
import com.sec.common.clmsfile.dto.ClmsFileVO;

public class RegulationServiceImpl extends CommonServiceImpl implements RegulationService {

	/**
	* 프로세스&규정지침서 목록조회
	* 
	* @param RegulationVO
	* @return List
	* @throws Exception
	*/
	public List listRegulation(RegulationVO vo) throws Exception {
		return commonDAO.list("clm.admin.listRegulation", vo);
	}

	
	 /**
	  * 테이블에서 해당 코드를 가져와서 Select Box의 <OPtion>을 채운다.
	  *
	  * @param     UP_RULE_CD 상위코드
	  * @param     SELECTED 디폴트 선택 코드
	  * @return    Select Box의 <OPtion> ( <option value="code">code_nm_s</option><optio...)
	  * @exception Exception
	  */
	public String getRulCdHTML(HashMap hm) throws Exception{
		String result = "";
		String str = "";

		String compCd	= StringUtil.bvl((String)hm.get("COMP_CD"), "");
		String upRuleNo = StringUtil.bvl((String)hm.get("UP_RULE_NO"), "");
		String selected = StringUtil.bvl((String)hm.get("SELECTED"),"");
		String locale   = StringUtil.bvl((String)hm.get("LOCALE"),"en");
		
		HashMap iHm = new HashMap();
		iHm.put("up_rule_no", upRuleNo);
		iHm.put("comp_cd", compCd);
		
		List list = null;
		list = commonDAO.list("clm.admin.regulationListRuleCd", iHm);
		
		String title       = "";
		
		//한영 표시문자 선택
		if("ko".equals(locale)) {
			//최상위표시
			title     = "-- 선택 --";
		} else {
			//최상위표시
			title     = "-- Select --";
		}
		
		if(!"".equals(title)) {
			result = "<option value=''>"+title+"</option>";
		}
		
		if(list != null && list.size() > 0){
			for(int i = 0; i < list.size(); i++){
				ListOrderedMap lom = (ListOrderedMap)list.get(i);
				
				result = result+"<option value='"+lom.get("rule_no")+"' "+str+">"+lom.get("rule_title")+"</option>";
			}
		}
		
		return result;
	}
	
	/**
	* 프로세스&규정지침서 상세조회
	* 
	* @param RegulationVO
	* @return List
	* @throws Exception
	*/
	public ListOrderedMap detailRegulation(RegulationVO vo) throws Exception {
		ListOrderedMap result = null;
		
		//데이터 조회
		List resultList = commonDAO.list("clm.admin.detailRegulation", vo);
		
		if(resultList != null && resultList.size() > 0){
			result = (ListOrderedMap)resultList.get(0);
		}
		
		return result; 
	}

	/**
	* 프로세스&규정지침서 등록
	* 
	* @param RegulationVO
	* @return List
	* @throws Exception
	*/
	public int insertRegulation(RegulationVO vo) throws Exception {
		int ruleNo    = 0;//규정번호
		int upRuleNo  = 0;//상위규정번호
		int ruleDepth = 0;//규정단계
		int ruleSrt   = 0;//규정순서
		int ruleDepthChk = 0;
		int ruleSrtChk   = 0;
		
		//규정번호 max값 조회.
		List maxRuleNo = commonDAO.list("clm.admin.maxRuleNoRegulation", vo);
		
		if(maxRuleNo != null && maxRuleNo.size() > 0){
			ListOrderedMap lom = (ListOrderedMap) maxRuleNo.get(0);
			ruleNo = (Integer.parseInt(lom.get("max_rule_no").toString()));
		}
		
		vo.setRule_no(ruleNo);
		
		if(!"up".equals(vo.getSrch_rule_choice())){//최상위가  아닐 경우.
			List nUpRuleNo = commonDAO.list("clm.admin.nUpRuleNoRegulation", vo);
			
			if(nUpRuleNo != null && nUpRuleNo.size() > 0){
				ListOrderedMap lom = (ListOrderedMap) nUpRuleNo.get(0);
				upRuleNo = ((BigDecimal)lom.get("up_rule_no")).intValue();
				
				vo.setUp_rule_no(upRuleNo);
				
				List maxRuleDepth = commonDAO.list("clm.admin.maxRuleDepthTwoRegulation", vo);
				
				if(maxRuleDepth != null && maxRuleDepth.size() > 0){
					ListOrderedMap lom2 = (ListOrderedMap)maxRuleDepth.get(0);
					
					ruleDepth = ((BigDecimal)lom2.get("max_rule_depth")).intValue();
					ruleSrt   = ((BigDecimal)lom2.get("max_rule_srt")).intValue();
				}
				
				vo.setRule_depth(ruleDepth);
				vo.setRule_srt(ruleSrt);
				vo.setGu("");
			}
			
		}else{//최상위일 경우
			List maxUpRuleNo = commonDAO.list("clm.admin.maxUpRuleNoRegulation", vo);
			
			if(maxUpRuleNo != null && maxUpRuleNo.size() > 0){
				ListOrderedMap lom = (ListOrderedMap) maxUpRuleNo.get(0);
				
				upRuleNo = ((BigDecimal)lom.get("max_up_rule_no")).intValue();
			}
			vo.setUp_rule_no(upRuleNo);
			vo.setRule_depth(1);
			vo.setRule_srt(1);
			vo.setGu("up");
			
			commonDAO.insert("clm.admin.insertRegulation", vo);
			vo.setGu("");
			
			//규정번호 max값 조회.
			List maxRuleNo2 = commonDAO.list("clm.admin.maxRuleNoRegulation", vo);
				
			if(maxRuleNo2 != null && maxRuleNo2.size() > 0){
				ListOrderedMap lom = (ListOrderedMap) maxRuleNo2.get(0);
				ruleNo = ((BigDecimal)lom.get("max_rule_no")).intValue();
			}
				
			vo.setRule_no(ruleNo);

			List maxRuleDepth = commonDAO.list("clm.admin.maxRuleDepthRegulation", vo);
			
			if(maxRuleDepth != null && maxRuleDepth.size() > 0){
				ListOrderedMap lom2 = (ListOrderedMap)maxRuleDepth.get(0);
				
				ruleDepthChk = ((BigDecimal)lom2.get("max_rule_depth")).intValue();
				ruleSrtChk   = ((BigDecimal)lom2.get("max_rule_srt")).intValue();
				
				if(ruleDepthChk == 1){//DN_RULE_EXIST_YN 체크 하기  위해.
					ruleDepth = ruleDepthChk + 1;
					ruleSrt   = ruleSrtChk;
				}else{
					ruleDepth = ruleDepthChk;
					ruleSrt   = ruleSrtChk + 1;
				}
			}
			
			vo.setRule_depth(ruleDepth);
			vo.setRule_srt(ruleSrt);
		}
		
		/*************************************************
		 * 첨부파일 저장
		 *************************************************/
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(Integer.toString(vo.getRule_no()));
		fvo.setFileInfos(vo.getFileInfos());
		fvo.setReg_id(vo.getSession_user_id());
		
		clmsFileService.mngClmsAttachFile(fvo);
		
		/*************************************************
		 * 나모 웹 에디터 처리
		 *************************************************/
//		String decodeText = vo.getRule_cont();
//		String decodeTextEn = vo.getRule_cont_en();
//		HashMap hm = comUtilService.getNamoContentAndFileInfo(decodeText);
//		HashMap hmEn = comUtilService.getNamoContentAndFileInfo(decodeTextEn);
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
//			vo.setRule_cont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
//			vo.setRule_cont_en((StringUtil.convertNamoCharsToHtml((String)hmEn.get("CONTENT")))); //Cross-site Scripting 방지 처리
//		}else {
//			vo.setRule_cont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
//			vo.setRule_cont_en((StringUtil.convertNamoCharsToHtml((String)hmEn.get("CONTENT")))); //Cross-site Scripting 방지 처리
//		}
//		vo.setRule_cont_en(vo.getRule_cont_en());
		
		int resultList = commonDAO.insert("clm.admin.insertRegulation", vo);
		
		//하위 규정 존재 여부 'Y'셋팅해주기 위해(현재데이터의 상위데이터 'Y' 로 UPDATE)
		int ruleSrtDnRuleExistYn = ruleSrt - 1;
		vo.setRule_srt(ruleSrtDnRuleExistYn);
		
		commonDAO.modify("clm.admin.modifyDnRuleExistYnRegulation", vo);
		
		return resultList;
	}

	/**
	* 프로세스&규정지침서 수정
	* 
	* @param RegulationVO
	* @return List
	* @throws Exception
	*/
	public int modifyRegulation(RegulationVO vo) throws Exception {
		/*************************************************
		 * 첨부파일 저장
		 *************************************************/
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(Integer.toString(vo.getRule_no()));
		fvo.setFileInfos(vo.getFileInfos());
		fvo.setReg_id(vo.getSession_user_id());
		
		clmsFileService.mngClmsAttachFile(fvo);
		
		/*************************************************
		 * 나모 웹 에디터 처리
		 *************************************************/
//		String decodeText = vo.getRule_cont();
//		String decodeTextEn = vo.getRule_cont_en();
//		HashMap hm = comUtilService.getNamoContentAndFileInfo(decodeText);
//		HashMap hmEn = comUtilService.getNamoContentAndFileInfo(decodeTextEn);
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
//			vo.setRule_cont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
//			vo.setRule_cont_en((StringUtil.convertNamoCharsToHtml((String)hmEn.get("CONTENT")))); //Cross-site Scripting 방지 처리
//		}else {
//			vo.setRule_cont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
//			vo.setRule_cont_en((StringUtil.convertNamoCharsToHtml((String)hmEn.get("CONTENT")))); //Cross-site Scripting 방지 처리
//		}
//		vo.setRule_cont_en(vo.getRule_cont_en())
		
		return commonDAO.modify("clm.admin.modifyRegulation", vo);
	}

	/**
	* 프로세스&규정지침서 삭제
	* 
	* @param RegulationVO
	* @return List
	* @throws Exception
	*/
	public int deleteRegulation(RegulationVO vo) throws Exception {
		 return commonDAO.delete("clm.admin.deleteRegulation", vo);
	}

	//임시
	public int maxRuleNo(RegulationVO vo) throws Exception{
		int result = 1;
		
		List resultList = commonDAO.list("clm.admin.maxRegulationNo", vo);
		
		if(resultList != null && resultList.size()>0){
			ListOrderedMap resultMap = (ListOrderedMap)resultList.get(0);
			result = ((BigDecimal)resultMap.get("max_rule_no")).intValue();
			result = result +1;
		}
		
		return result;
	}

	/**
	* 프로세스&규정지침서 목록 1단계 조회
	* 
	* @param 
	* @return List
	* @throws Exception
	*/
	public List listODepthRegulation(RegulationVO vo) throws Exception {
		return commonDAO.list("clm.admin.listODepthRegulation",vo);
	}
	
	/**
	* 등록 화면 전체depth(selectbox)조회.
	* 
	* @param 
	* @return List
	* @throws Exception
	*/
	public List listUpRule(RegulationVO vo) throws Exception {
		return commonDAO.list("clm.admin.listUpRule",vo);
	}
} 