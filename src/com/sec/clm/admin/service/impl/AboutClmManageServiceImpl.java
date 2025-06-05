package com.sec.clm.admin.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.admin.dto.AboutClmManageVO;
import com.sec.clm.admin.service.AboutClmManageService;

public class AboutClmManageServiceImpl extends CommonServiceImpl implements AboutClmManageService {

	/**
	* About CLM 관리 목록조회
	* 
	* @param AboutClmManageVO
	* @return List
	* @throws Exception
	*/
	public List listAboutClmManage(AboutClmManageVO vo) throws Exception {
		return commonDAO.list("clm.admin.listAboutClmManage", vo);
	}

	/**
	* About CLM 관리 상세 조회
	* 
	* @param AboutClmManageVO
	* @return List
	* @throws Exception
	*/
	public ListOrderedMap detailAboutClmManage(AboutClmManageVO vo)
			throws Exception {
		ListOrderedMap result = null;
		
		List resultList = commonDAO.list("clm.admin.detailAboutClmManage", vo);
		if(resultList != null && resultList.size() > 0 ){
			result = (ListOrderedMap)resultList.get(0);
		}
		return result;
	}

	/**
	* About CLM 관리 수정
	* 
	* @param AboutClmManageVO
	* @return int
	* @throws Exception
	*/
	public int modifyAboutClmManage(AboutClmManageVO vo) throws Exception {
		
		/*************************************************
		 * 나모 웹 에디터 처리
		 *************************************************/
//		String decodeText = vo.getCont();
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
		vo.setCont((StringUtil.convertNamoCharsToHtml(vo.getCont()))); //Cross-site Scripting 방지 처리
		
		return commonDAO.modify("clm.admin.modifyAboutClmManage", vo);
	}

	/**
	* about CLM Html 호출
	* 
	* @param AboutClmManageVO
	* @return List
	* @throws Exception
	*/
	public ListOrderedMap listAboutClmManageHtml(AboutClmManageVO vo) throws Exception {
		ListOrderedMap result = null;

		List resultList = commonDAO.list("clm.admin.listAboutClmManageHtml", vo);
		if(resultList != null && resultList.size() > 0){
			
			result = (ListOrderedMap)resultList.get(0);
		}
		return result;
	}

}
