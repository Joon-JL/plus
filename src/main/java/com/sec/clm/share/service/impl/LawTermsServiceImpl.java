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

import com.sec.clm.share.dto.LawTermsVO;
import com.sec.clm.share.service.LawTermsService;
import com.sec.common.util.ClmsDataUtil;
import com.sec.las.lawinformation.dto.MainLawInfoVO;


/**
 * Description	: 계약용어집목록 Service impl(concrete class)
 * Author 		: 유영섭
 * Date			: 2011. 09. 05
 */
public class LawTermsServiceImpl extends CommonServiceImpl implements LawTermsService {

	/**
	 * 계약용어집 목록을 조회한다.
	 * @param  vo LawTermsVO 
	 * @return List
	 * @throws Exception
	 */
	
	
	public List listLawTerms(LawTermsVO vo) throws Exception {
		return commonDAO.list("clm.share.listLawTerms", vo);
	}
	
	/**
	 * 계약 용어집을 등록한다.
	 * @param  vo LawTermsVO
	 * @return int
	 * @throws Exception
	 */
	
	public int insertLawTerms(LawTermsVO vo) throws Exception {
		//make sequence
		int seqno = 1;
		List resultList = null;
		
		resultList = commonDAO.list("clm.lawterms.makeSeqLibrary", vo);
		
		if (resultList != null && resultList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			
			vo.setSeqno(((Integer)lom.get("next_seqno")).intValue());
			
			seqno = vo.getSeqno(); 
		}else{ 
			
			vo.setSeqno(seqno);
			
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
			vo.setTerms_expl((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
		}else {
			vo.setTerms_expl((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
		}
		
		 commonDAO.insert("clm.share.insertLawTerms", vo);
		
		
		
		return seqno;
	}

	/**
	 * 계약 용어집을 수정한다.
	 * @param  vo LawTermsVO
	 * @return int
	 * @throws Exception  
	 */
	
	
	public int modifyLawTerms(LawTermsVO vo) throws Exception {
		
		/*************************************************
		 * 나모 웹 에디터 처리
		 *************************************************/
	
		String decodeText = vo.getBody_mime();
		HashMap hm = comUtilService.getNamoContentAndFileInfo(decodeText);
	    vo.setTerms_expl((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT"))));
	
	
		return commonDAO.modify("clm.share.modifyLawTerms", vo);
	}

	
	/**
	 * 계약 용어집을 삭제한다.
	 * @param  vo LawTermsVO
	 * @return int
	 * @throws Exception  
	 */
	
	public int deleteLawTerms(LawTermsVO vo) throws Exception {
		 return commonDAO.delete("clm.share.deleteLawTerms", vo);
	}
	
	/**
	 * 계약 용어집을 상세조회한다.
	 * @param  vo LawTermsVO
	 * @return int
	 * @throws Exception 
	 */
	
	public List detailLawTerms(LawTermsVO vo) throws Exception {
		return commonDAO.list("clm.share.detailLawTerms", vo);
	}
	
	/**
	 * 계약 용어집조회수 증가
	 * @param  vo LawTermsVO
	 * @return List
	 * @throws Exception
	 */
	public void increseRdcnt(LawTermsVO vo) throws Exception {
		commonDAO.modify("clm.lawterms.increseRdcnt", vo) ;
	}
	
	/**
	* 계약 용어집 권한조회
	* 
	* @param ItemLibraryVO
	* @return boolean
	* @throws Exception
	*/
	public boolean authLawTerms(String mode, LawTermsVO vo) throws Exception {
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