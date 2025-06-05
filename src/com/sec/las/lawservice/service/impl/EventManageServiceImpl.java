/**
 * Project Name : 법무시스템
 * File Name : EventManageServiceImpl.java
 * Description : 사건 관리 ServiceImpl
 * Author : 박병주
 * Date : 2011.09
 * Copyright : 2011 by SAMSUNG. All rights reserved.
 */
package com.sec.las.lawservice.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sec.common.clmsfile.dto.ClmsFileVO;
import com.sec.las.lawservice.dto.DeptListVO;
import com.sec.las.lawservice.dto.EventAcceptLawyerVO;
import com.sec.las.lawservice.dto.EventAcceptSrvCostVO;
import com.sec.las.lawservice.dto.EventAcceptVO;
import com.sec.las.lawservice.dto.EventManageVO;
import com.sec.las.lawservice.dto.EventRefDeptVO;
import com.sec.las.lawservice.service.EventManageService;

public class EventManageServiceImpl extends CommonServiceImpl implements EventManageService {
	/**
	 * 사건관리 리스트
	 * 
	 * @param EventManageVO
	 * @return List
	 * @throws Exception
	 */
	public List listEventManage(EventManageVO vo) throws Exception {
		return commonDAO.list("las.lawservice.listEventManage", vo);
	}
	/**
	 * 사건관리 등록
	 * 
	 * @param EventManageVO
	 * @return int
	 * @throws Exception
	 */
	public int insertEventManage(EventManageVO vo) throws Exception {
		
		// 첨부파일 저장
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(vo.getEvent_no());
		fvo.setFileInfos(vo.getFileInfos());
		fvo.setReg_id(vo.getSession_user_id());

		clmsFileService.mngClmsAttachFile(fvo);

		
		// 부서 등록
		String str_intnl_dept_cd[] = vo.getIntnl_dept_cd().toString().split("/");
		String str_dept_nm[] = vo.getDept_nm().toString().split("/");
		String str_grp_dept_cd[] = vo.getGrp_dept_cd().toString().split("/");
		
		EventRefDeptVO dvo = null;
		
		for(int i=0; i < str_intnl_dept_cd.length; i++) {
			dvo = new EventRefDeptVO();
			
			if(!str_intnl_dept_cd[i].equals("")){
				
				dvo.setEvent_no(vo.getEvent_no());
				dvo.setIntnl_dept_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(str_intnl_dept_cd[i],"")));
				dvo.setGrp_dept_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(str_grp_dept_cd[i],"")));
				dvo.setDept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(str_dept_nm[i],"")));
				dvo.setReg_id(vo.getReg_id());
				dvo.setReg_nm(vo.getReg_nm());
				
				commonDAO.insert("las.lawservice.insertRefDept", dvo);
			}
			
			dvo = null;
		}		
		
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
			vo.setEvent_cont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
		}else {
			vo.setEvent_cont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
		}		
		
		return commonDAO.insert("las.lawservice.insertEventManage", vo);
	}
	/**
	 * 사건관리 수정
	 * 
	 * @param EventManageVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyEventManage(EventManageVO vo) throws Exception {
		
		// 첨부파일 저장
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(vo.getEvent_no());
		fvo.setFileInfos(vo.getFileInfos());
		fvo.setReg_id(vo.getSession_user_id());

		clmsFileService.mngClmsAttachFile(fvo);
		
		
		// 이전 부서 삭제처리
		commonDAO.delete("las.lawservice.deleteRefDept", vo);
		
		// 부서 등록
		String str_intnl_dept_cd[] = vo.getIntnl_dept_cd().toString().split("/");
		String str_dept_nm[] = vo.getDept_nm().toString().split("/");
		String str_grp_dept_cd[] = vo.getGrp_dept_cd().toString().split("/");
		
		EventRefDeptVO dvo = null;

		
		for(int i=0; i < str_intnl_dept_cd.length; i++) {
			dvo = new EventRefDeptVO();
			
			if(!str_intnl_dept_cd[i].equals("")){
				
				dvo.setEvent_no(vo.getEvent_no());
				dvo.setIntnl_dept_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(str_intnl_dept_cd[i],"")));
				dvo.setGrp_dept_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(str_grp_dept_cd[i],"")));
				dvo.setDept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(str_dept_nm[i],"")));
				dvo.setReg_id(vo.getMod_id());
				dvo.setReg_nm(vo.getMod_nm());
				
				commonDAO.insert("las.lawservice.insertRefDept", dvo);
			}
			
			dvo = null;
		}
		
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
			vo.setEvent_cont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
		}else {
			vo.setEvent_cont((StringUtil.convertNamoCharsToHtml((String)hm.get("CONTENT")))); //Cross-site Scripting 방지 처리
		}		
		
		return commonDAO.modify("las.lawservice.modifyEventManage", vo);
	}
	/**
	 * 사건관리 삭제
	 * 
	 * @param EventManageVO
	 * @return int
	 * @throws Exception
	 */
	public int deleteEventManage(EventManageVO vo) throws Exception {
		
        ClmsFileVO fvo = new ClmsFileVO(); 

        fvo.setSys_cd(vo.getSys_cd());
        fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
        fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
        fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
        fvo.setRef_key((vo.getEvent_no()));
        fvo.setFileInfos(vo.getFileInfos());
        fvo.setDel_id(vo.getSession_user_id());        

        clmsFileService.delClmsAttachFile(fvo);	
		
		 return commonDAO.delete("las.lawservice.deleteEventManage", vo);
	}
	/**
	 * 사건관리 상세
	 * 
	 * @param EventManageVO
	 * @return List
	 * @throws Exception
	 */
	public List detailEventManage(EventManageVO vo) throws Exception {
		return commonDAO.list("las.lawservice.detailEventManage", vo);
	}	
	/**
	 * 사건관리 사건시퀀스
	 * 
	 * @param EventManageVO
	 * @return String
	 * @throws Exception
	 */
	public String getMaxEventNo(EventManageVO vo) throws Exception {
		
		List resultList = null;		
		String max_event_no = "";
		
		resultList = commonDAO.list("las.lawservice.getMaxEventNo", vo);
		
		if (resultList != null && resultList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			max_event_no = (String)lom.get("event_no");
		}
		return max_event_no;		
	}
	
	/**
	 * 등록 부서 목록 조회
	 * @param DeptListVO
	 * @return List
	 * @throws Exception
	 */
	public List getListRefDept(EventRefDeptVO vo) throws Exception {		
		return commonDAO.list("las.lawservice.getListRefDept", vo);
	}
	
	/**
	 * 사건관련 변호사 목록 조회
	 * @param DeptListVO
	 * @return List
	 * @throws Exception
	 */
	public List getListLawywerAcceptEvent(EventAcceptLawyerVO vo) throws Exception {
		return commonDAO.list("las.lawservice.getListLawywerAcceptEvent", vo);
	}
	
	/**
	 * 등록사건 목록 조회
	 * @param EventAcceptVO
	 * @return List
	 * @throws Exception
	 */
	public List getListEventAccept(EventAcceptVO vo) throws Exception {
		return commonDAO.list("las.lawservice.getListEventAccept", vo);
	}
	/**
	 * 사건 접수 송장 목록 조회
	 * @param EventAcceptVO
	 * @return List
	 * @throws Exception
	 */
	public List getListEventAcceptInvoice(EventAcceptVO vo) throws Exception {
		return commonDAO.list("las.lawservice.getListEventAcceptInvoice", vo);
	}
	
	/**
	 * 부서 리스트를 반환한다 (트리 구조)
	 */
	public List getListDept() throws Exception {
		return commonDAO.list("las.lawservice.getListDept");
	}
	
	/**
	 * 부서 리스트를 반환한다 (트리 구조)
	 */
	public List listDeptTree(DeptListVO vo) throws Exception {
		return commonDAO.list("las.lawservice.listDept", vo);
	}
	
	/**
	 * 하위부서 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	public JSONArray listDeptTreeAjax(DeptListVO vo) throws Exception {
		List al =  commonDAO.list("las.lawservice.listDeptTree", vo);

		JSONArray jsonArray = new JSONArray();

		if(al!=null && al.size()>0) {
			
			for(int i=0; i<al.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)al.get(i);

				JSONObject jsonObject = new JSONObject();
				
				jsonObject.put("dept_cd", (String)lom.get("dept_cd"));
				jsonObject.put("dept_nm", (String)lom.get("dept_nm"));
				jsonObject.put("in_dept_cd", (String)lom.get("in_dept_cd"));
				jsonObject.put("dept_level", ((Integer)lom.get("dept_level")).toString());
				jsonObject.put("dept_order", ((Integer)lom.get("dept_order")).toString());
				jsonObject.put("down_dept_yn", (String)lom.get("down_dept_yn"));
				jsonObject.put("up_dept_cd", (String)lom.get("up_dept_cd"));

				jsonArray.add(jsonObject);
			}
		}
		
		return jsonArray;
	}

}