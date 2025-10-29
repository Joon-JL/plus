/**
 * Project Name : 법무시스템
 * File Name : LawyerManageServiceImpl.java
 * Description : 변호사 관리 ServiceImpl
 * Author : 박병주
 * Date : 2011.09
 * Copyright : 2011 by SAMSUNG. All rights reserved.
 */
package com.sec.las.lawservice.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sec.common.clmsfile.dto.ClmsFileVO;
import com.sec.las.lawservice.dto.EventManageVO;
import com.sec.las.lawservice.dto.LawfirmManageVO;
import com.sec.las.lawservice.dto.LawyerEstimateVO;
import com.sec.las.lawservice.dto.LawyerManageVO;
import com.sec.las.lawservice.service.LawyerManageService;

public class LawyerManageServiceImpl extends CommonServiceImpl implements LawyerManageService {
	
	/**
	 * 변호사관리 목록 조회
	 * 
	 * @param LawyerManageVO
	 * @return List
	 * @throws Exception
	 */
	public List listLawyerManage(LawyerManageVO vo) throws Exception {		
		return commonDAO.list("las.lawservice.listLawyerManage", vo);
	}
	
	/**
	 * 변호사관리 등록
	 * 
	 * @param LawyerManageVO
	 * @return List
	 * @throws Exception
	 */
	public int insertLawyerManage(LawyerManageVO vo) throws Exception {
		
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(vo.getLwr_no());
		fvo.setFileInfos(vo.getFileInfos());
		fvo.setReg_id(vo.getSession_user_id());
		
		clmsFileService.mngClmsAttachFile(fvo);
		
		//Cross-site Scripting 방지
		vo.setLwr_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLwr_nm(),"")));
		vo.setFirst_name(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getFirst_name(),"")));
		vo.setLast_name(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLast_name(),"")));
		vo.setLwr_jikgup(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLwr_jikgup(),"")));
		vo.setTelno(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getTelno(),"")));
		vo.setPay_lvl(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getPay_lvl(),"")));
		vo.setEmail(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getEmail(),"")));
		vo.setExpert_area(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getExpert_area(),"")));
		vo.setFrom_school(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getFrom_school(),"")));
		vo.setFrom_school_grdtyear(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getFrom_school_grdtyear(),"")));
		// vo.setCareer_cont(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCareer_cont(),"")));
		vo.setLawschool(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLawschool(),"")));
		vo.setLawschool_grdtyear(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLawschool_grdtyear(),"")));
		vo.setNation(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getNation(),"")));
				
		// 로펌변호사 테이블 등록
		if(!vo.getLawfirm_id().equals("")){
			commonDAO.insert("las.lawservice.insertLawfirmLawyer", vo);
		}

		
		// 사건등록
		String event_no[] = vo.getEvent_no();
		
		for(int i=0; i < event_no.length; i++) {
			if(!event_no[i].equals("")){
				vo.setEvent_no_tmp(StringUtil.convertHtmlTochars(StringUtil.bvl(event_no[i],"")));
				commonDAO.insert("las.lawservice.insertLawyerEvent", vo);
			}
		}
		
		// 변호사 평가 등록
		String estmt_title[] = vo.getEstmt_title();
		String estmt_cont[] = vo.getEstmt_cont();
		
		for(int i=0; i < estmt_title.length; i++) {
			if(!estmt_title[i].equals("")){
				vo.setEstmt_title_tmp(StringUtil.convertHtmlTochars(StringUtil.bvl(estmt_title[i],"")));
				vo.setEstmt_cont_tmp(StringUtil.convertHtmlTochars(StringUtil.bvl(estmt_cont[i],"")));
				commonDAO.insert("las.lawservice.insertLawyerEstimate", vo);
			}
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
				
				Integer seq = new Integer(i);
				String fileNm = (String)fileMap.get("FILE_NM");
				String filePath = (String)fileMap.get("FILE_PTH");
				String fileUrl = (String)fileMap.get("FILE_URL");
				
				File f = new File(filePath);
				Long fileSize = new Long(f.length());
			}			
			vo.setCareer_cont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
		}else {
			vo.setCareer_cont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
		}		

		return commonDAO.insert("las.lawservice.insertLawyerManage", vo);
	}
	
	/**
	 * 변호사관리 수정
	 * 
	 * @param LawyerManageVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyLawyerManage(LawyerManageVO vo) throws Exception {
		
		//Cross-site Scripting 방지
		vo.setLwr_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLwr_nm(),"")));
		vo.setFirst_name(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getFirst_name(),"")));
		vo.setLast_name(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLast_name(),"")));
		vo.setLwr_jikgup(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLwr_jikgup(),"")));
		vo.setTelno(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getTelno(),"")));
		vo.setPay_lvl(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getPay_lvl(),"")));
		vo.setEmail(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getEmail(),"")));
		vo.setExpert_area(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getExpert_area(),"")));
		vo.setFrom_school(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getFrom_school(),"")));
		vo.setFrom_school_grdtyear(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getFrom_school_grdtyear(),"")));
		// vo.setCareer_cont(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCareer_cont(),"")));
		vo.setLawschool(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLawschool(),"")));
		vo.setLawschool_grdtyear(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLawschool_grdtyear(),"")));
		vo.setNation(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getNation(),"")));
		
		// 로펌변호사 테이블 등록
		if(!vo.getLawfirm_id().equals("")){
			commonDAO.modify("las.lawservice.modifyLawfirmLawyer", vo);
		} else {
			commonDAO.delete("las.lawservice.deleteLawfirmLawyer", vo);
		}
		
		// 사건등록
		commonDAO.delete("las.lawservice.deleteLawyerEvent", vo);
		
		String event_no[] = vo.getEvent_no();
		
		for(int i=0; i < event_no.length; i++) {
			if(!event_no[i].equals("")){
				vo.setEvent_no_tmp(StringUtil.convertHtmlTochars(StringUtil.bvl(event_no[i],"")));
				commonDAO.insert("las.lawservice.insertLawyerEvent", vo);
			}
		}

		// 변호사 평가 등록
		commonDAO.delete("las.lawservice.deleteLawyerEstimate", vo);		
		
		String estmt_title[] = vo.getEstmt_title();
		String estmt_cont[] = vo.getEstmt_cont();
		
		for(int i=0; i < estmt_title.length; i++) {
			if(!estmt_title[i].equals("")){
				vo.setEstmt_title_tmp(StringUtil.convertHtmlTochars(StringUtil.bvl(estmt_title[i],"")));
				vo.setEstmt_cont_tmp(StringUtil.convertHtmlTochars(StringUtil.bvl(estmt_cont[i],"")));
				commonDAO.insert("las.lawservice.insertLawyerEstimate", vo);
			}
		}
		
		// 첨부파일처리		
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(vo.getLwr_no());
		fvo.setFileInfos(vo.getFileInfos());
		fvo.setReg_id(vo.getSession_user_id());
		
		clmsFileService.mngClmsAttachFile(fvo);
		
		// 나모 웹 에디터 처리
		String decodeText = vo.getBody_mime();
		HashMap hm = comUtilService.getNamoContentAndFileInfo(decodeText);

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
			vo.setCareer_cont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
		}else {
			vo.setCareer_cont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
		}

		
		return commonDAO.modify("las.lawservice.modifyLawyerManage", vo);
	}
	
	/**
	 * 변호사관리 삭제
	 * 
	 * @param LawyerManageVO
	 * @return int
	 * @throws Exception
	 */
	public int deleteLawyerManage(LawyerManageVO vo) throws Exception {
		
		// 첨부파일 삭제처리
        ClmsFileVO fvo = new ClmsFileVO(); 

        fvo.setSys_cd(vo.getSys_cd());
        fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
        fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
        fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
        fvo.setRef_key((vo.getLwr_no()));
        fvo.setFileInfos(vo.getFileInfos());
        fvo.setDel_id(vo.getSession_user_id());        
        clmsFileService.delClmsAttachFile(fvo);	
		
		 return commonDAO.delete("las.lawservice.deleteLawyerManage", vo);
	}
	
	/**
	 * 변호사관리 상세
	 * 
	 * @param LawyerManageVO
	 * @return List
	 * @throws Exception
	 */
	public List detailLawyerManage(LawyerManageVO vo) throws Exception {
		return commonDAO.list("las.lawservice.detailLawyerManage", vo);
	}
	
	/**
	 * 변호사관리 상세 하단 사건리스트
	 * 
	 * @param EventManageVO
	 * @return List
	 * @throws Exception
	 */
	public List getLawyerEventList(LawyerManageVO vo) throws Exception {
		return commonDAO.list("las.lawservice.getLawyerEventList", vo);
	}
	
	/**
	 * 변호사관리 수정화면 용 사건리스트
	 * 
	 * @param EventManageVO
	 * @return List
	 * @throws Exception
	 */
	public List getLawyerEventList2(EventManageVO vo) throws Exception {
		return commonDAO.list("las.lawservice.getLawyerEventList2", vo);
	}
	
	/**
	 * 변호사관리 사건리스트
	 * 
	 * @param EventManageVO
	 * @return List
	 * @throws Exception
	 */
	public List listLawyerManageGetEventList(EventManageVO vo) throws Exception {
		return commonDAO.list("las.lawservice.listLawyerManage.getEventList", vo);
	}
	
	/**
	 * 변호사관리 변호사ID 시퀀스
	 * 
	 * @param LawyerManageVO
	 * @return String
	 * @throws Exception
	 */
	public String getMaxLawyerNo(LawyerManageVO vo) throws Exception {
		
		List resultList = null;		
		String lwr_no = "";
		
		resultList = commonDAO.list("las.lawservice.getMaxLawyerNo", vo);
		
		if (resultList != null && resultList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			lwr_no = (String)lom.get("lwr_no");
		}
		return lwr_no;
	}
	
	/**
	 * 변호사관리 변호사평가  ID 시퀀스
	 * 
	 * @param LawyerManageVO
	 * @return String
	 * @throws Exception
	 */
	public String getMaxLawyerEstmtNo(LawyerManageVO vo) throws Exception {
		
		List resultList = null;		
		String estmt_no = "";
		
		resultList = commonDAO.list("las.lawservice.getMaxLawyerEstimationNo", vo);
		
		if (resultList != null && resultList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			estmt_no = (String)lom.get("estmt_no");
		}
		return estmt_no;		
	}
	
	/**
	 * 변호사관리 로펌리스트
	 * 
	 * @param LawfirmManageVO
	 * @return List
	 * @throws Exception
	 */
	public List getListLawfirm(LawfirmManageVO vo) throws Exception {
		return commonDAO.list("las.lawservice.getListLawfirm", vo);
	}
	/**
	 * 변호사관리 평가리스트
	 * 
	 * @param LawyerEstimateVO
	 * @return List
	 * @throws Exception
	 */
	public List getListLawyerEstimate(LawyerEstimateVO vo) throws Exception {
		return commonDAO.list("las.lawservice.listLawyerEstimate", vo);
	}
	
	/**
	 * 변호사관리 변호사 사진 등록
	 * 
	 * @param LawyerManageVO
	 * @return List
	 * @throws Exception
	 */
	public int insertLawyerProfilePhoto(LawyerManageVO vo) throws Exception {
		
		return commonDAO.insert("las.lawservice.insertLawyerPhotoImage", vo);
	}
}