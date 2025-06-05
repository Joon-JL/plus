package com.sec.las.lawconsulting.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.MultipartPostMethod;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.file.dto.AttachFileVO;
import com.sds.secframework.file.service.AttachFileService;
import com.sec.common.clmsfile.dto.ClmsFileVO;
import com.sec.las.lawconsulting.dto.LawConsultVO;
import com.sec.las.lawconsulting.service.CounselHttpInvokerClientService;
import com.sec.las.lawconsulting.service.CounselHttpInvokerService;

public class CounselHttpInvokerClientServiceImpl extends CommonServiceImpl implements CounselHttpInvokerClientService {

	private CounselHttpInvokerService service;
	public void setCounselHttpInvokerClientService(CounselHttpInvokerService service) {
		this.service = service;
	}

	/**
	 * FileService 선언 및 세팅
	 */
	private AttachFileService attachFileService;
	public void setAttachFileService(AttachFileService attachFileService) {
		this.attachFileService = attachFileService;
	}
	
	
	public String sendCounselToCPMS (LawConsultVO vo) throws Exception {
		
		String result = "FAIL";
		HashMap<String, Object> ifMap = new HashMap<String, Object>();
		List<HashMap<String,Object>> ifFileList = new ArrayList<HashMap<String,Object>>();
		
         /* - INQ_ID QA20120048800 문의아이디 : CPMS에서 넘겨준 값
            -STAT_CNTNT 입력한 결과내역 회신내역 : TEXTAREA (HTML 태크 없는 값)
            -FILES
            -FIRST_REG_DT 2012-04-23 오후 2:19:23 회신일시
            -FIRST_REGPSN_ID M030318143510D118914 회신자ID
            -FIRST_REGPSN_DEPT_CD C10D0494 회신자 부서코드
            -FIRST_REGPSN_DEPT_NM  IMC파트(무선) 회신자 부서명
            -CLM_STATUS 회신/반려 구분코드
		    -검토주관부서 -> DMSTFRGN_GBN
		    -법률자문유형 -> LAW_TYPE*/
         // ********* 예) 시작
		 
		 
		ifMap.put("INQ_ID", StringUtil.bvl(vo.getIf_key_no(), ""));                 /*문의아이디*/
		if("S03".equals(StringUtil.bvl(vo.getPrgrs_status(), "")) ){
			ifMap.put("STAT_CNTNT",  StringUtil.bvl(vo.getCnsd_opnn_body(), ""));   /*검토의견*/
		}else{
			ifMap.put("STAT_CNTNT",  StringUtil.bvl(vo.getRejct_cause(), ""));      /*반려사유*/
		}
		  
		ifMap.put("FIRST_REGPSN_ID", StringUtil.bvl(vo.getRegpsn_id(), ""));           /*회신자ID*/
		ifMap.put("FIRST_REGPSN_NM", StringUtil.bvl(vo.getRegpsn_nm(), ""));           /*회신자명*/
		ifMap.put("FIRST_REGPSN_DEPT_CD", StringUtil.bvl(vo.getRegpsn_dept_cd(), "")); /*회신자 부서코드*/ 
		ifMap.put("FIRST_REGPSN_DEPT_NM", StringUtil.bvl(vo.getRegpsn_dept_nm(), "")); /*회신자 부서명*/
		ifMap.put("CLM_STATUS", StringUtil.bvl(vo.getPrgrs_status(), ""));             /*'S03':회신, 'S07':반려, 'S11':법무장반려, 'S05':보류*/
		ifMap.put("DMSTFRGN_GBN", StringUtil.bvl(vo.getDmstfrgn_gbn(), ""));           /*검토주관부서('H':국내,'F':해외)*/
		ifMap.put("LAW_TYPE", StringUtil.bvl(vo.getConsult_type(), ""));               /*법률자문유형*/

		/*
		 *  첨부파일 정보 이관
		 */
		String fileInfos = vo.getFileInfos();
		
        /** 파일정보 저장 및 삭제 **/
		if(clmsFileService.isExistsFileInfos(fileInfos)){
			//첨부파일 리스트
			List fileList = clmsFileService.getFileInfoToCpmsFileList(fileInfos);
			if(fileList != null && fileList.size() > 0){
				ClmsFileVO file = new ClmsFileVO();
				for(int i=0; i<fileList.size(); i++){
					HashMap<String,Object> fileMap = new HashMap<String,Object>();
					file = (ClmsFileVO)fileList.get(i);
					
			        fileMap.put("FILE_NM", file.getOrg_file_nm());  // 원본파일명
			        ifFileList.add(fileMap);
			    }
			}
			
			ifMap.put("FILE_INFO", StringUtil.bvl(vo.getReadLine(), ""));  //서버저장파일정보
	        ifMap.put("FILES", ifFileList);
		}
	    
		//CPMS Receive 호출
		try{
			result = service.receiveCounselResult(ifMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			result = "HttpInvoker-Error : "+e.getMessage();
		} 
		
		return result;		
		
	}
}
