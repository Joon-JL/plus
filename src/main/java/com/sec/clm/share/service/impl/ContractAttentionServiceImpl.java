/**
 * Project Name : 계약관리시스템 
 * File name	: ContractAttentionServiceImpl.java
 * Description	: 계약지식공유 > 계약체결시유의사항 Service impl
 * Author		: 유영섭
 * Date			: 2011. 09. 18 
 * Copyright	:
 */

package com.sec.clm.share.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.util.StringUtil;

import com.sec.clm.share.dto.ContractAttentionVO; 
import com.sec.clm.share.service.ContractAttentionService;
import com.sec.common.util.ClmsDataUtil;
import com.sec.las.lawinformation.dto.MainLawInfoVO;


public class ContractAttentionServiceImpl extends CommonServiceImpl implements ContractAttentionService {
	  
	/**
	 * 계약체결 유의사항 목록을 조회한다.
	 * @param  vo ContractAttentionVO 
	 * @return List   
	 * @throws Exception
	 */
	
	public List listContractAttention(ContractAttentionVO vo) throws Exception {
		return commonDAO.list("clm.share.ListContractAttention", vo);
	}


	/**
	 * 계약 체결시 유의사항을 등록한다.
	 * @param  vo ContractAttentionVO
	 * @return int
	 * @throws Exception
	 */
	
	public int insertContractAttention(ContractAttentionVO vo) throws Exception {
		//make sequence 
		int mtbat_no = 1; 
		List resultList = null;
		
		resultList  = commonDAO.list("clm.share.makeSeqLibrary", vo);

		
		if (resultList != null && resultList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			
			vo.setMtbat_no(((Integer)lom.get("next_mtbat_no")).intValue());
			
			mtbat_no = vo.getMtbat_no();

		}else{
			
			vo.setMtbat_no(mtbat_no);
			
		}
		
		/*************************************************
		 * 나모 웹 에디터 처리
		 *************************************************/
		

		String decodeText = vo.getBody_mime();  
		 
		HashMap hm = comUtilService.getNamoContentAndFileInfo(decodeText);
		

		if (hm.get("TYPE").equals("M")) {
			ArrayList fileList = (ArrayList)hm.get("FILE_INFO");
			

			
			for (int i = 0; i < fileList.size(); i++) {
				HashMap fileMap = (HashMap)fileList.get(i);
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
		
		commonDAO.insert("clm.share.InsertContractAttention", vo);
		
		return mtbat_no;
	}
	/**
	 * 계약 체결시 유의사항을 수정한다.
	 * @param  vo ContractAttentionVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyContractAttention(ContractAttentionVO vo) throws Exception {
		
		/*************************************************
		 * 나모 웹 에디터 처리
		 *************************************************/
		
		String decodeText = vo.getBody_mime();
		
		HashMap hm = comUtilService.getNamoContentAndFileInfo(decodeText);
		
		 vo.setCont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT"))));
		
		return commonDAO.modify("clm.share.ModifyContractAttention", vo);
	}
	
	/**
	 * 계약 체결시 유의사항을 삭제한다.
	 * @param  vo ContractAttentionVO
	 * @return int
	 * @throws Exception
	 */
	public int deleteContractAttention(ContractAttentionVO vo) throws Exception {
		 return commonDAO.delete("clm.share.DeleteContractAttention", vo);
	}
	
	/**
	 * 계약 체결시 유의사항을 상세조회한다.
	 * @param  vo ContractAttentionVO
	 * @return List
	 * @throws Exception
	 */
  
	public List detailContractAttention(ContractAttentionVO vo) throws Exception {
		return commonDAO.list("clm.share.detailContractAttention", vo);
	}
	
	/**
	 * 계약 체결시 유의사항을 조회수 증가
	 * @param  vo LibraryVO
	 * @return List
	 * @throws Exception
	 */
	
	public void increseRdcnt(ContractAttentionVO vo) throws Exception {
		commonDAO.modify("clm.share.ContractAttention.increseRdcnt", vo) ;
	}
	
	/**
	* 계약 체결시 유의사항 권한조회
	* 
	* @param ItemLibraryVO
	* @return boolean
	* @throws Exception
	*/
	public boolean authContractAttention(String mode, ContractAttentionVO vo) throws Exception {
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