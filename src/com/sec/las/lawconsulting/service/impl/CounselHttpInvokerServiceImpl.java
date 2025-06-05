package com.sec.las.lawconsulting.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sec.common.clmsfile.dto.ClmsFileVO;
import com.sec.las.lawconsulting.dto.LawConsultVO;
import com.sec.las.lawconsulting.service.CounselHttpInvokerService;
import com.sec.las.lawconsulting.service.LawConsultService;

public class CounselHttpInvokerServiceImpl extends CommonServiceImpl implements CounselHttpInvokerService {
	
	
	public CounselHttpInvokerServiceImpl(){
	}
	
	/**
	 * 법률자문 서비스
	 */
	private LawConsultService lawConsultService;
	
	/**
	 * 법률자문 서비스 세팅
	 * 
	 * @param lawConsultService
	 */
	public void setLawConsultService(LawConsultService lawConsultService) {
		this.lawConsultService = lawConsultService;
	}

	public String receiveCounselResult(HashMap<String, Object> hm) throws Exception {
		
		// 작업 성공여부에 대한 초기값 세팅 (FAIL)
		String Resp = "FAIL";
		// 상대방으로 부터 받은 값 hm에서 값을 추출하여
		// 실개발시에는 Business Logic 으로 대체 (값을 디비에 넣고, 첨부파일 저장하고...)
		
		/********************************************************************/
		int result = 0;
		LawConsultVO vo = new LawConsultVO();
		/*************************************************
		* LawConsult VO SET
		*************************************************/
		    vo.setIf_gbn("IF");
		    vo.setIf_key_no((String)hm.get("IF_KEY_NO"));
			vo.setHstry_no(0);
			// 국내/해외 구분 변수 세팅
			vo.setIsForeign((String)hm.get("DMSTFRGN_GBN"));
		    vo.setCnslt_pos(0); 
	  	    vo.setCnslt_srt(0);   
	        vo.setDmstfrgn_gbn((String)hm.get("DMSTFRGN_GBN"));
	        vo.setTitle("[CPMS이관] "+(String)hm.get("TITLE"));
		    vo.setCont((String)hm.get("CONT"));  
		    vo.setPrgrs_status("S02");
		    vo.setExtnl_cnsltyn("N");
		    vo.setCnslt_amt("0");  
		    vo.setWeekdutyyn("Y"); 
		    vo.setMonthdutyyn("Y");  
		    vo.setConf_weekdutyyn("Y");  
		    vo.setConf_monthdutyyn("Y"); 
		    vo.setReg_id((String)hm.get("REG_ID"));
		    vo.setReg_nm((String)hm.get("REG_NM"));
		    vo.setReg_dept((String)hm.get("REG_DEPT"));
		    vo.setReg_intnl_dept("");       
		    vo.setReg_dept_nm((String)hm.get("REG_DEPT_NM"));
		    vo.setReg_telno("");
		    vo.setDel_yn("N");
		    vo.setGrpmgr_re_yn("N");
		    vo.setPub_yn("Y");
		    vo.setReg_operdiv("");     //등록사업부
		    vo.setConsult_type((String)hm.get("CONSULT_TYPE")); //자문유형
		    /*************************************************
			* 첨부파일 VO SET
			*************************************************/
			    List fileList = (ArrayList)hm.get("FILES");
			    String file_Info = (String)hm.get("FILE_INFO");
			   
			    // 첨부파일 저장정보
				String fileInfos = "";
				// 파일의 확장자 정보를 소문자로 변환해서 가져온다.
				String fileInfo = "";
			    
				if (fileList != null && fileList.size() > 0) {
					// File 단위로 자른다.
					String[] arrFileInfo = StringUtil.token(file_Info, "|");
					
					for(int j=0; j<fileList.size(); j++) {
						HashMap fileMap = (HashMap)fileList.get(j);
						
						// 파일의 확장자 정보를 소문자로 변환해서 가져온다.
    				    fileInfo = fileMap.get("FILE_NM").toString().substring(fileMap.get("FILE_NM").toString().lastIndexOf(".") + 1).toLowerCase();
    				    String[] arrFileDetail = StringUtil.token(arrFileInfo[j], "*");
    				    //20120813_10711774249509*20120705_10903449567612.pdf*pdf*C:\Temp\201208\20120813_10711774249509.pdf*34475181*0*add |
						fileInfos  	= fileInfos 
					            + arrFileDetail[0]+"*" 
					            + fileMap.get("FILE_NM")+"*"
					            + fileInfo+"*"        
					            + arrFileDetail[3]+"*"
					            + Integer.valueOf(arrFileDetail[4]).intValue()+"*"
					            + 0+"*"
					            + "add|";	
					
					}
					vo.setSys_cd("LAS");                       
					vo.setFile_bigclsfcn("F003");        
					vo.setFile_midclsfcn("F00301");         
					vo.setFile_smlclsfcn("");   
					//FileInfos (seq_no*로컬파일명*파일정보*서버파일명(경로+실제저장파일명)*크기|....)
					vo.setFileInfos(fileInfos);
				}
					
			// 중복 등록이 아니면 등록 서비스 호출
			result = lawConsultService.insertLawConsult(null, vo);
		/**************************************************************************************/
		if (result > 0) {
			// 성공시에 리턴값을 "SUCC"로 세팅해서 돌려준다.
			Resp = "SUCC";		
		}
		
		return Resp;		
	}
}
