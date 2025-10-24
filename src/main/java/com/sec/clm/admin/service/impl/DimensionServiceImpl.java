/**
 * Project Name : 계약관리시스템
 * File name	: DimensionServiceImpl.java
 * Description	: 계약분류관리 Service impl(concrete class)
 * Author		: 한지훈
 * Date			: 2011. 08. 24
 * Copyright	:
 */
package com.sec.clm.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.FormatUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.admin.dto.DimensionVO;
import com.sec.clm.admin.service.DimensionService;
import com.sec.common.clmsfile.dto.ClmsFileVO;

/**
 * Description	: Service impl(concrete class)
 * Author		: 한지훈
 * Date			: 2011. 08. 24
 */
public class DimensionServiceImpl extends CommonServiceImpl implements DimensionService {

	/**
	 * 조회
	 * @param vo DimensionVO
	 * @return List
	 * @throws Exception
	 */
	public List listDimension(DimensionVO vo) throws Exception {
		//Cross-site Scripting 방지
		vo.setSrch_word(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_word(),"")));
		
		return commonDAO.list("clm.admin.listDimension", vo);
	}
	
	/**
	 * 등록
	 * @param  vo DimensionVO
	 * @return String
	 * @throws Exception  sqlCUDExecutor(IQueryInfo queryInfo, Object[] values)
	 */
	public String insertDimension(DimensionVO vo) throws Exception {
		String type_cd = "T";
		List resultList = null;
		
		resultList = commonDAO.list("clm.admin.makeDimensionCd", vo);
		
		if (resultList != null && resultList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

			vo.setType_cd(lom.get("nextDimensionCd").toString());
			
			type_cd = vo.getType_cd();
		} else {
			vo.setType_cd(type_cd);
		}

		//Cross-site Scripting 방지
		vo.setCd_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCd_nm(),"")));
		vo.setCd_nm_abbr(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCd_nm_abbr(),"")));
		vo.setCd_nm_eng(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCd_nm_eng(),"")));
		vo.setCd_nm_abbr_eng(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCd_nm_abbr_eng(),"")));
		vo.setPrnt_srt(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getPrnt_srt(),"")));
		vo.setCd_expl(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCd_expl(),"")));
		
		vo.setReg_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_user_id(),"")));
		vo.setReg_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_user_nm(),"")));
		vo.setReg_dept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_dept_nm(),"")));
		
		
		commonDAO.insert("clm.admin.insertDimension", vo);
		
		return type_cd;
	}
	
	/**
	 * 수정
	 * @param  vo DimensionVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyDimension(DimensionVO vo) throws Exception {
		//Cross-site Scripting 방지
		vo.setType_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getType_cd(),"")));
		vo.setCd_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCd_nm(),"")));
		vo.setCd_nm_abbr(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCd_nm_abbr(),"")));
		vo.setCd_nm_eng(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCd_nm_eng(),"")));
		vo.setCd_nm_abbr_eng(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCd_nm_abbr_eng(),"")));
		vo.setPrnt_srt(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getPrnt_srt(),"")));
		vo.setCd_expl(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCd_expl(),"")));
		
		vo.setMod_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_user_id(),"")));
		vo.setMod_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_user_nm(),"")));
		vo.setMod_dept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_dept_nm(),"")));
		
		return commonDAO.modify("clm.admin.modifyDimension", vo);
	}
	
	/**
	 * 삭제
	 * @param  vo DimensionVO
	 * @return int
	 * @throws Exception
	 */
	public int deleteDimension(DimensionVO vo) throws Exception {
		//Cross-site Scripting 방지
		vo.setType_cd(StringUtil.convertHtmlTochars(vo.getType_cd()));
		
		vo.setMod_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_user_id(),"")));
		vo.setMod_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_user_nm(),"")));
		vo.setMod_dept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_dept_nm(),"")));
		
		return commonDAO.delete("clm.admin.deleteDimension", vo);
	}
	
	/**
	 * 상세조회
	 * @param  vo DimensionVO
	 * @return List
	 * @throws Exception
	 */
	public List detailDimension(DimensionVO vo) throws Exception {
		return commonDAO.list("clm.admin.detailDimension", vo);
	}
	
	/**
	 * 유형 목록 Tree타입
	 * @param 
	 * @return 메뉴리스트 JSON
	 * @throws Exception 
	 */
	public JSONArray listDimensionTree(DimensionVO vo) throws Exception {
		
		ArrayList listMenuAl = (ArrayList)commonDAO.list("clm.admin.listDimensionTree", vo);

		JSONArray jsonArray = new JSONArray();
		ArrayList[] boxAl = null;

		if(listMenuAl!=null && listMenuAl.size()>0) {
			
			ListOrderedMap levelLom = (ListOrderedMap)listMenuAl.get(0);
			
			int maxLevel = (FormatUtil.formatInt(levelLom.get("tree_max_level")));
			boxAl = new ArrayList[maxLevel];
			
			for(int i=0; i<boxAl.length; i++) {
				boxAl[i] = new ArrayList();
			}
			
			//메뉴정보는 array에 담는다.
			for(int i=0; i<listMenuAl.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)listMenuAl.get(i);
				
				String treeKey    = (String)lom.get("tree_key");
				String treeUpKey  = (String)lom.get("tree_up_key");
				String treeTitle  = (String)lom.get("tree_title");
				int    treeLevel  = (FormatUtil.formatInt(lom.get("tree_level")));
				int    arrayLoc   = (FormatUtil.formatInt(lom.get("array_loc")));
				String treeSelect = StringUtil.bvl((String)lom.get("tree_select"),"");
				String treeIsLeaf = (String)lom.get("tree_isleaf");
				
				JSONObject jo = new JSONObject();
				JSONArray  ja = null;
				
				jo.put("title"    , treeTitle);
				jo.put("key"      , treeKey);
				jo.put("treeUpKey", treeUpKey);
				
				String selectTypeCd = StringUtil.bvl(vo.getType_cd(), "");
				if(selectTypeCd.equals(treeKey)) jo.put("activate", new Boolean(true));
				
				if("Y".equals(treeSelect)) jo.put("select", new Boolean(true));
				if("N".equals(treeIsLeaf)) {
					jo.put("isFolder", new Boolean(true));
					jo.put("expand", new Boolean(true));
					ja = new JSONArray();
					jo.put("children", ja);
				}

				ArrayList levelAl = boxAl[arrayLoc];
				levelAl.add(jo);
			}

			for(int i=maxLevel-1; i-1>=0; i--) {
				
				ArrayList childAl  = boxAl[i];
				ArrayList parentAl = boxAl[i-1];
				
				for(int j=0; j<parentAl.size();j++) {
					
					JSONObject parentJsonObject = (JSONObject)parentAl.get(j);
					String parentTreeKey = (String)parentJsonObject.get("key");
					JSONArray parentJsonArray = (JSONArray)parentJsonObject.get("children");
					
					for(int k=0; k<childAl.size();k++) {
						
						JSONObject childJsonObject = (JSONObject)childAl.get(k);
						String childTreeUpKey = (String)childJsonObject.get("treeUpKey");

						if(parentTreeKey.equals(childTreeUpKey)) {
							parentJsonArray.add(childJsonObject);
					
						}
						
					}
				}
			}
		}
		
		if(boxAl != null && boxAl.length>0){
			
			ArrayList al = boxAl[0];
			
			for(int i=0; i<al.size();i++){
				JSONObject jsonObject = (JSONObject)al.get(i);
				jsonArray.add(jsonObject);
			}
		}
		
		return jsonArray;		
	}
	
	/**
	 * 유형관리 차트 형식 - 쿼리는 위의 Tree형식과 동일한 것을 사용합니다.
	 * @param  vo DimensionVO
	 * @return List
	 * @throws Exception
	 */
	public List listDimensionChart(DimensionVO vo) throws Exception {
		return commonDAO.list("clm.admin.listDimensionTree", vo);
	}
	
	
	/**
	 * 계약유형 용어집 - 쿼리는 위의 Tree형식과 동일한 것을 사용합니다.
	 * @param  vo DimensionVO
	 * @return List
	 * @throws Exception
	 */
	public List listDimensionWord(DimensionVO vo) throws Exception {
		return commonDAO.list("clm.admin.listDimensionWord", vo);
	}
	
	/**
	* 계약용어집 목록 1단계 조회
	* 
	* @param 
	* @return List
	* @throws Exception
	*/
	public List listODepthDimesionWord(DimensionVO vo) throws Exception {
		return commonDAO.list("clm.admin.listODepthDimesionWord", vo);
	}
	
	/**
	* 계약용어집 목록조회
	* 
	* @param RegulationVO
	* @return List
	* @throws Exception
	*/
	public List listDimesionWordAdmin(DimensionVO vo) throws Exception {
		return commonDAO.list("clm.admin.listDimesionWordAdmin", vo);
	}
	
	
	/**
	* 등록 화면 전체depth(selectbox)조회.
	* 
	* @param 
	* @return List
	* @throws Exception
	*/
	public List listUpRule(DimensionVO vo) throws Exception {
		return commonDAO.list("clm.admin.listUpRuleDimension", vo);
	}
	
	/**
	* 계약용어집 등록
	* 
	* @param RegulationVO
	* @return List
	* @throws Exception
	*/
	public int insertDimensionWordAdmin(DimensionVO vo) throws Exception {
		int ruleNo    = 0;//규정번호
		int upRuleNo  = 0;//상위규정번호
		int ruleDepth = 0;//규정단계
		int ruleSrt   = 0;//규정순서
		int ruleDepthChk = 0;
		int ruleSrtChk   = 0;
		
		//규정번호 max값 조회.
		List maxRuleNo = commonDAO.list("clm.admin.maxRuleNoDimesion", vo);
		
		if(maxRuleNo != null && maxRuleNo.size() > 0){
			ListOrderedMap lom = (ListOrderedMap) maxRuleNo.get(0);
//			ruleNo = ((BigDecimal)lom.get("max_rule_no")).intValue(); by 성현
			ruleNo = Integer.parseInt(String.valueOf( lom.get("max_rule_no")));
		}
		
		vo.setRule_no(ruleNo);
		
		if(!"up".equals(vo.getSrch_rule_choice())){//최상위가  아닐 경우.
			List nUpRuleNo = commonDAO.list("clm.admin.nUpRuleNoDimesion", vo);
			 
			if(nUpRuleNo != null && nUpRuleNo.size() > 0){
				ListOrderedMap lom = (ListOrderedMap) nUpRuleNo.get(0);
//				upRuleNo = ((BigDecimal)lom.get("up_rule_no")).intValue(); by 성현
				upRuleNo = Integer.parseInt(String.valueOf(lom.get("up_rule_no")));
				
				vo.setUp_rule_no(upRuleNo);
				
				List maxRuleDepth = commonDAO.list("clm.admin.maxRuleDepthTwoDimesion", vo);
				
				if(maxRuleDepth != null && maxRuleDepth.size() > 0){
					ListOrderedMap lom2 = (ListOrderedMap)maxRuleDepth.get(0);
					
//					ruleDepth = ((BigDecimal)lom2.get("max_rule_depth")).intValue();
					ruleDepth = Integer.parseInt(String.valueOf(lom2.get("max_rule_depth")));
//					ruleSrt   = ((BigDecimal)lom2.get("max_rule_srt")).intValue();
					ruleSrt = Integer.parseInt(String.valueOf(lom2.get("max_rule_srt")));
				}
				
				vo.setRule_depth(ruleDepth);
				vo.setRule_srt(ruleSrt);
				vo.setGu("");
			}
			
		}else{//최상위일 경우
			List maxUpRuleNo = commonDAO.list("clm.admin.maxUpRuleNoDimesion", vo);
			
			if(maxUpRuleNo != null && maxUpRuleNo.size() > 0){
				ListOrderedMap lom = (ListOrderedMap) maxUpRuleNo.get(0);
				
//				upRuleNo = ((BigDecimal)lom.get("max_up_rule_no")).intValue();
				upRuleNo = Integer.parseInt(String.valueOf(lom.get("max_up_rule_no")));
			}
			vo.setUp_rule_no(upRuleNo);
			vo.setRule_depth(1);
			vo.setRule_srt(1);
			vo.setGu("up");
			
			commonDAO.insert("clm.admin.insertDimesion", vo);
			vo.setGu("");
			
			//규정번호 max값 조회.
			List maxRuleNo2 = commonDAO.list("clm.admin.maxRuleNoDimesion", vo);
				
			if(maxRuleNo2 != null && maxRuleNo2.size() > 0){
				ListOrderedMap lom = (ListOrderedMap) maxRuleNo2.get(0);
//				ruleNo = ((BigDecimal)lom.get("max_rule_no")).intValue();
				ruleNo = Integer.parseInt(String.valueOf(lom.get("max_rule_no")));
			}
				
			vo.setRule_no(ruleNo);

			List maxRuleDepth = commonDAO.list("clm.admin.maxRuleDepthDimesion", vo);
			
			if(maxRuleDepth != null && maxRuleDepth.size() > 0){
				ListOrderedMap lom2 = (ListOrderedMap)maxRuleDepth.get(0);
				
//				ruleDepthChk = ((BigDecimal)lom2.get("max_rule_depth")).intValue();
				ruleDepthChk = Integer.parseInt(String.valueOf(lom2.get("max_rule_depth")));
//				ruleSrtChk   = ((BigDecimal)lom2.get("max_rule_srt")).intValue();
				ruleSrtChk = Integer.parseInt(String.valueOf(lom2.get("max_rule_srt")));
				
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
		
		/*
		String decodeText = vo.getRule_cont();
//		String decodeTextEn = vo.getRule_cont_en();
		
		HashMap hm = comUtilService.getNamoContentAndFileInfo(decodeText);
//		HashMap hmEn = comUtilService.getNamoContentAndFileInfo(decodeTextEn);
		
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
			vo.setRule_cont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
//			vo.setRule_cont_en((StringUtil.convertNamoCharsToHtml((String)hmEn.get("CONTENT")))); //Cross-site Scripting 방지 처리
		}else {
			vo.setRule_cont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
//			vo.setRule_cont_en((StringUtil.convertNamoCharsToHtml((String)hmEn.get("CONTENT")))); //Cross-site Scripting 방지 처리
		}
		*/
		vo.setRule_cont_en(vo.getRule_cont_en());
		
		int resultList = commonDAO.insert("clm.admin.insertDimesion", vo);
		
		//하위 규정 존재 여부 'Y'셋팅해주기 위해(현재데이터의 상위데이터 'Y' 로 UPDATE)
		int ruleSrtDnRuleExistYn = ruleSrt - 1;
		vo.setRule_srt(ruleSrtDnRuleExistYn);
		
		commonDAO.modify("clm.admin.modifyDnRuleExistYnDimesion", vo);
		
		return resultList;
	}
	
	/**
	* 계약용어집 상세조회
	* 
	* @param RegulationVO
	* @return List
	* @throws Exception
	*/
	public ListOrderedMap detailDimensionWordAdmin(DimensionVO vo) throws Exception {
		ListOrderedMap result = null;
		
		//데이터 조회
		List resultList = commonDAO.list("clm.admin.detailDimensionWordAdmin", vo);
		
		if(resultList != null && resultList.size() > 0){
			result = (ListOrderedMap)resultList.get(0);
		}
		
		return result; 
	}
	
	/**
	* 계약용어집 수정
	* 
	* @param RegulationVO
	* @return List
	* @throws Exception
	*/
	public int modifyDimensionWordAdmin(DimensionVO vo) throws Exception {
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
//		vo.setRule_cont_en(StringUtil.convertEnterToBR(vo.getRule_cont_en()));
		
		return commonDAO.modify("clm.admin.modifyDimensionWordAdmin", vo);
	}
	
	/**
	* 계약용어집 삭제
	* 
	* @param RegulationVO
	* @return List
	* @throws Exception
	*/
	public int deleteDimensionWordAmdin(DimensionVO vo) throws Exception {
		 return commonDAO.delete("clm.admin.deleteDimensionWordAdmin", vo);
	}
	
}
