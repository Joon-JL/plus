/**
 * Project Name : 계약관리시스템 
 * File name	: ContractAttentionServiceImpl.java
 * Description	: 계약지식공유 > 주요 거래선 유형별 협상 포인트 
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

import com.sec.clm.share.dto.ContractNegoPointVO;
import com.sec.clm.share.dto.LawTermsVO;
import com.sec.clm.share.service.ContractNegoPointService;
import com.sec.common.clmsfile.dto.ClmsFileVO;
import com.sec.common.util.ClmsDataUtil;
import com.sec.las.lawinformation.dto.MainLawInfoVO;

public class ContractNegoPointServiceImpl extends CommonServiceImpl implements ContractNegoPointService {

	/**
	 * 주요 거래선 협상포인트 목록을 조회한다.
	 * @param  vo ContractNegoPointVO 
	 * @return List   
	 * @throws Exception
	 */
	
	public List listContractNegoPoint(ContractNegoPointVO vo) throws Exception {
		return commonDAO.list("clm.share.ListNegoPoint", vo);
	}

	/**
	 * 주요 거래선 유형별 협상 포인트 유의사항을 등록한다.
	 * @param  vo ContractNegoPointVO 
	 * @return int  
	 * @throws Exception
	 */
	
	public int insertContractNegoPoint(ContractNegoPointVO vo) throws Exception {
		
		//nego_no 구하기
		int nego_no = 1;
		List resultList = null;
		
		resultList = commonDAO.list("clm.share.makeSeqNegoPoint", vo);
		
		if (resultList != null && resultList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			vo.setNego_no(((Integer)lom.get("next_nego")).intValue());
			nego_no = vo.getNego_no();
		}else{
			
			vo.setNego_no(nego_no);
		}
		/*************************************************
		 * 첨부파일 저장
		 *************************************************/
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(Integer.toString(vo.getNego_no()));
		fvo.setFileInfos(vo.getFileInfos());
		fvo.setReg_id(vo.getSession_user_id());
		
		clmsFileService.mngClmsAttachFile(fvo);
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
		commonDAO.insert("clm.share.InsertNegoPoint", vo);
		return nego_no;
	}
	
	/**
	 * 주요 거래선 유형별 협상 포인트를 수정한다.
	 * @param  vo ContractNegoPointVO 
	 * @return int  
	 * @throws Exception
	 */
	
	public int modifyContractNegoPoint(ContractNegoPointVO vo) throws Exception {
		/*************************************************
		 * 나모 웹 에디터 처리
		 *************************************************/
	
	
		String decodeText = vo.getBody_mime();
		
	
		HashMap hm = comUtilService.getNamoContentAndFileInfo(decodeText);
		
		vo.setCont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT"))));
	
		return commonDAO.modify("clm.share.ModifyNegoPoint", vo);
	}
	
	/**
	 * 주요 거래선 유형별 협상 포인트를 삭제한다.
	 * @param  vo ContractNegoPointVO 
	 * @return int  
	 * @throws Exception
	 */

	public int deleteContractNegoPoint(ContractNegoPointVO vo) throws Exception {
		 return commonDAO.delete("clm.share.DeleteNegoPoint", vo);
	}
    
	
	/**
	 * 주요 거래선 유형별 협상 포인트를 상세조회한다.
	 * @param  vo ContractNegoPointVO 
	 * @return int  
	 * @throws Exception
	 */
	
	public List detailContractNegoPoint(ContractNegoPointVO vo) throws Exception {
		return commonDAO.list("clm.share.DetailNegoPoint", vo);
	}
	
	/**
	 * 주요 거래선 유형별 협상 포인트 증가
	 * @param  vo ContractNegoPointVO
	 * @return List
	 * @throws Exception
	 */
	public void increseRdcnt(ContractNegoPointVO vo) throws Exception {
		commonDAO.modify("clm.share.modifyRdcntNegoPoint", vo) ;
	}
	
	/**
	* 주요 거래선 유형별 협상 포인트 권한조회
	* 
	* @param ItemLibraryVO
	* @return boolean
	* @throws Exception
	*/
	public boolean authContractNegoPint(String mode, ContractNegoPointVO vo) throws Exception {
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