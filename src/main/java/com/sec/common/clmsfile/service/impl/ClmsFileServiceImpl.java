/**
* Project Name : CLMS
* File Name : ClmsFileServiceImpl.java
* Description : 공통 첨부파일 ServiceImpl
* Author : 신남원
* Date : 2011. 08. 04
* Copyright : SamSung
*/
package com.sec.common.clmsfile.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import anyframe.common.exception.BaseException;

import com.sds.secframework.common.dto.CommonVO;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.FileUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.log.dto.LogVO;
import com.sds.secframework.singleIF.dto.MailVO;
import com.sec.clm.draft.dto.LibraryVO;
import com.sec.clm.manage.dto.ConclusionVO;
import com.sec.clm.manage.dto.ConsultationVO;
import com.sec.clm.manage.dto.MyApprovalVO;
import com.sec.clm.tnc.dto.InfFileAttachVO;
import com.sec.common.clmsfile.dto.ClmsFileVO;
import com.sec.common.clmsfile.dto.ComFileVO;
import com.sec.common.clmsfile.service.ClmsFileService;
import com.sec.common.util.ClmsDataUtil;

/**
 * 첨부파일 처리를 위한 Class
 * CLMS 시스템 공통 처리 (CLM, LAS)
 */
public class ClmsFileServiceImpl extends CommonServiceImpl implements ClmsFileService {
		
	/**********************************************************************
	* ClmsFile 처리 영역
	**********************************************************************/
	
	/**
	* ClmsFile : DB정보로부터 첨부파일정보 조회
	* 2011.09.08
	* 첨부파일을 DB로부터 조회하여 JSP로 반환되는 경우 사용
	* @param  ClmsFileVO
	* @return String FileInfos
	* @throws Exception
	*/
	public String getClmsDbFileInfos(ClmsFileVO vo) throws Exception{
		String fileInfos = "";
		
		//DB로부터 첨부파일정보 조회
		List fileList = listClmsFile(vo);
		
		//DB List -> String 변환
		if(fileList != null && fileList.size()>0){
			fileInfos = getClmsFileListToFileInfos(fileList, "old");
		}
		return fileInfos;
	}	
	
	/**
	* ClmsFile : List File을 String FileInfo형식으로 변환
	* FileList는  ListOrderedMap으로 구성 됨
	* @param    List File
	* @return   String FileInfo (seq_no*로컬파일명*파일정보*서버파일명(경로+실제저장파일명)*크기|....)
	*/
	public String getClmsFileListToFileInfos(List collist, String status) throws Exception {

		String fileInfos = "";

		if( collist.size() > 0){
			Iterator it = collist.iterator();
			while(it.hasNext())
			{
				ListOrderedMap hList = (ListOrderedMap)it.next();
				if(status.equals("add")){
					fileInfos	= fileInfos 
								+ (String)hList.get("file_id")+"*" 
								+ (String)hList.get("org_file_nm")+"*"
								+ (String)hList.get("file_info")+"*"
								+ (String)hList.get("file_path")+"*"
								+ ((Integer)(hList.get("file_sz"))).toString()+"*"
								+ ((Integer)(hList.get("dn_cnt"))).toString()+"*"
								+ status+"|";
				}else{
					fileInfos	= fileInfos 
								+ (String)hList.get("file_id")+"*" 
								+ (String)hList.get("org_file_nm")+"*"
								+ (String)hList.get("file_info")+"*"
								+ (String)hList.get("file_path")+ "*"
								+ String.valueOf(hList.get("file_sz")).toString()+"*"
								+ String.valueOf(hList.get("dn_cnt")).toString()+"*"
								+ status+"|";
				}
	 		}
		}
	
		return fileInfos;
	}	
	
	/**
	 * ClmsFile : 파일을 업로드하기 위한 method
	 * HttpServletRequest 객체로 부터 파일에 대한 정보를 가져와서
	 * 서버로 파일을 저장하고 저장된 파일 정보를 return 한다.
	 * @param request 첨부파일에 대한 정보가 포함된 HttpServletRequest 객체
	 * @return 파일정보를 담고 있는 fileInfos
	 * @throws Exception
	 */
	public String clmsFileUpload(HttpServletRequest request) throws Exception {
		String fileInfos = "";
		
		//파일정보 서버에 저장
		List fileList = ClmsFileListSave(request);
		
		if(fileList != null && fileList.size() > 0){
			//File List -> String 변환
			fileInfos = getClmsFileListToFileInfos(fileList, "add");
		}		
		return fileInfos;
	}

	/**
	 * ClmsFile : HttpServletRequest 객체로 부터 파일에 대한 정보를 가져와서
	 * 파일을 서버에 저장하고 저장된 리스트를 반환
	 * @param request 첨부파일에 대한 정보가 포함된 HttpServletRequest 객체
	 * @return 파일에 대한 메타 정보를 담고 있는 List 객체
	 * @throws Exception
	 */
	private List ClmsFileListSave(HttpServletRequest request) throws Exception {

		// 파일의 메타정보를 리턴하기 위한 List 
		List fileInfoList = new ArrayList();
		
		/*********************************************************
		 * 변수 정의
		**********************************************************/
		// 파일Id
		String fileId = "";
		// 원본 파일명
		String orgFileNm = "";
		// 서버 파일명
		String serverFileNm = "";
		// 파일 정보(확장자)
		String fileInfo = "";
		// 업로드구분
		// 파일 서버 업로드 경로
		String serverDir = "";
//		String fileInfos = StringUtil.bvl((String)request.getParameter("fileInfos"), "");
		String allowInfo = propertyService.getProperty("clms.uploadify.allowFileList");
		
		// 파일의 메타정보(파일사이즈,원래 파일명, 서버에 저장될 파일명, 저장경로)를 저장할 Map
		ListOrderedMap fileMetaInfo = null;
		
        try {
        	
        	MultipartHttpServletRequest multipart = (MultipartHttpServletRequest)request;
        	
        	final Map fileMap = multipart.getFileMap();
        	
        	if(fileMap != null) {
        		// looping을 위한 Iterator 객체를 가져온다.
	        	Iterator fileIterator = fileMap.values().iterator();
	        	MultipartFile file;
	        	
	        	while(fileIterator.hasNext()) {
	        		
	        		// 첨부파일 객체를 리스트에서 가져온다.
	        		file = (MultipartFile)fileIterator.next();
	        		
	        		// 파일에 Size 가 0 보다 큰경우 파일에 대한 정보를 추출한다.
	        		if(file.getSize() > 0) {
	        			serverDir = propertyService.getProperty("clms.uploadify.serverDir");
	        			
						orgFileNm = file.getOriginalFilename();
						
						// 원래 파일명이 empty String이 아닐 경우에만 파일에 대한 정보를 추출한다.
            			if (!"".equals(orgFileNm)) {
            				
            				// 파일의 확장자가 있을 경우 확인
            				if (orgFileNm.lastIndexOf(".") != -1) {
            					
            					// 파일의 확장자 정보를 소문자로 변환해서 가져온다.
            					 fileInfo = orgFileNm.substring(orgFileNm.lastIndexOf(".") + 1).toLowerCase();
            				}
            				
            				// 현재파일확장자가 허용된 확장자 인지 확인 한다. 
            				if (checkFileInfo(allowInfo, fileInfo)) {
            					String saveDir = "";
        						// 서버에 저장할 File Id를  생성
                				// 구성 : 년월_Nano시간
                				//fileId = DateUtil.getShortDate(new Date()) +"_"+ Long.toString(System.nanoTime());
            					//change from nanotime to currenttimemillis신성우
            					
            					// 2014-07-22 Kevin changed.
            					// To avoid file key duplication, the way to generate the file id changed. 
            					//Random r = new Random();
            					//fileId = String.valueOf(System.nanoTime())+"_"+String.valueOf(r.nextInt(Integer.MAX_VALUE));
            					fileId = getUUID();

                				// 서버에 저장할 파일명을 생성
                				serverFileNm = fileId + "." + fileInfo;
                				
                				// 파일을 저장할 서버의 경로를 구하기
                				String subDir = DateUtil.getDate(new Date()).substring(0,6);
                				saveDir = serverDir +'/'+subDir;
            			
                				// 서버의 저장경로가 없을경우 해당 디렉토리를 생성
                				if (!new File(saveDir).exists()) {
            						new File(saveDir).mkdirs();
            					}
            					
                				File uploadedFile = new File(saveDir, serverFileNm);
            					// 파일을 서버의 저장경로로 저장한다.	
            					file.transferTo(uploadedFile);
            					
                            
                				// 파일의 메타 정보를 담기 위한 map 객체를 생성후 정보 담기
                                fileMetaInfo = new ListOrderedMap();
      
                                fileMetaInfo.put("file_id", fileId);
                                fileMetaInfo.put("org_file_nm", orgFileNm);
                                fileMetaInfo.put("file_info", fileInfo);
                                fileMetaInfo.put("file_path", uploadedFile.getAbsolutePath());
                                fileMetaInfo.put("file_sz", Integer.valueOf(new Long(file.getSize()).toString()));
                                fileMetaInfo.put("dn_cnt", 0);

                				fileInfoList.add(fileMetaInfo);
            				}else{
            					throw new BaseException(messageSource.getMessage(
                						"secfw.msg.error.fileNonAllow", new String[] {fileInfo}, Locale.getDefault()));
            				}
            			}
	        		}
	        	}
        	}
        } catch (FileUploadException e) {
        	throw new BaseException(messageSource.getMessage(
					"secfw.msg.error.fileNonAllow", new String[] {fileInfo}, Locale.getDefault()));
        } catch (Exception e) {
        	throw new BaseException(messageSource.getMessage(
					"secfw.msg.error.fileNonAllow", new String[] {fileInfo}, Locale.getDefault()));
        }

        return fileInfoList;
	}	
	
	/**
	 * ClmsFile : 업로드 가능한 파일 확장자 인지 체크 한다.
	 * @param 업로드 시도한 파일의 확장자
	 * @return true : 허용된 파일형식이 있음, false : 허용된 파일 형식이 없음
	 * @throws Exception
	 */
	public boolean checkFileInfo(String allowInfo,  String fileInfo ) throws Exception {
		boolean result = false;
		
		String allowList = allowInfo;
		StringTokenizer	token = new StringTokenizer(allowList,",");
		
		while (token.hasMoreTokens()) {
			if(fileInfo.equals( token.nextToken().trim().toLowerCase())) {
				result = true;
				break;
			}
		}
		
		return result;		
	}	

	/**
	* ClmsFile : 첨부파일 관리 : 정보 저장 및 삭제
	* 업무 저장 또는 수정시 에 첨부되는 파일 정보를 수정 또는 삭제한다.
	* 각 서비스Impl에서 호출되는 부분
	* @param  ClmsFileVO vo
	* @return boolean 결과
	* @throws Exception
	*/
	public boolean mngClmsAttachFile(ClmsFileVO vo) throws Exception{
		return mngClmsAttachFile(vo, null); 
	}

	public boolean mngClmsAttachFile(ClmsFileVO vo, CommonVO cvo) throws Exception{
		boolean result = true;
		String fileGbn = "";
		String fileInfo = vo.getFileInfos();
		//첨부파일 종류
		String bigclsfcn = vo.getFile_bigclsfcn();
		String midclsfcn = vo.getFile_midclsfcn();
		String smlclsfcn = vo.getFile_smlclsfcn();
		String newFileNm = "";
		String finalVersion = StringUtil.bvl(vo.getFinalVersion(), "");
		
		String userLocale = (cvo == null) ? "ko" : (String)cvo.getSession_user_locale();
		Locale locale1 = new Locale(userLocale);
		
		String cnsdreq_id = "";
		if(cvo != null){
			if("LAS".equals(vo.getSys_cd()) && "F0120201".equals(vo.getFile_smlclsfcn())){ //사업부이관건(계약,법무)이면서 의뢰계약서일경우만!!
				if("com.sec.clm.review.dto.ConsultationVO".equals(cvo.getClass().getName())){
					com.sec.clm.review.dto.ConsultationVO tempvo= (com.sec.clm.review.dto.ConsultationVO)cvo;
					cnsdreq_id = StringUtil.bvl(tempvo.getCnsdreq_id(), "");	
				}else{
					com.sec.clm.manage.dto.ConsultationVO tempvo= (com.sec.clm.manage.dto.ConsultationVO)cvo;
					cnsdreq_id = StringUtil.bvl(tempvo.getCnsdreq_id(), "");	
				}					
			}else{
				if("com.sec.clm.manage.dto.MyApprovalVO".equals(cvo.getClass().getName())){
				}else if("com.sec.clm.manage.dto.ConclusionVO".equals(cvo.getClass().getName())){
				}else if("com.sec.clm.draft.dto.LibraryVO".equals(cvo.getClass().getName())){
				}else if("com.sec.clm.review.dto.ConsultationVO".equals(cvo.getClass().getName())){
					com.sec.clm.review.dto.ConsultationVO tempvo= (com.sec.clm.review.dto.ConsultationVO)cvo;
					cnsdreq_id = StringUtil.bvl(tempvo.getCnsdreq_id(), "");	
				}else{
					com.sec.clm.manage.dto.ConsultationVO tempvo= (com.sec.clm.manage.dto.ConsultationVO)cvo;
					cnsdreq_id = StringUtil.bvl(tempvo.getCnsdreq_id(), "");
				}				
			}
		}
		
		/** 파일명 생성 **/
		//라이브러리 신규생성
		if("F013".equals(bigclsfcn)){
			fileGbn = "lib";
			newFileNm = getNewFileName(vo, cvo); 
			vo.setNewFileNm(newFileNm);
			//기존첨부파일 수정 김재원 아래와 같은 이유로 변경되는 것 수정함.12.01.14
			//modifyBeforeLibFile(vo);
		//계약
		}else if("F012".equals(bigclsfcn)){
			//계약_검토의뢰
			if("F01202".equals(midclsfcn)){
				//계약서파일
				if("F0120201".equals(smlclsfcn)){
					fileGbn = "cntrt";
					
					newFileNm = "";
					
					//기존파일 삭제(계약서파일은 하나만 올릴 수 있으므로)
					if(isExistsFileInfos(fileInfo)){
						vo.setDel_id(vo.getReg_id());
						vo.setFile_smlclsfcn(smlclsfcn);	//Sungwoo added 2014-08-06 It was updated all other status files. therefore, It need to strict condition.
						deleteClmsBeforeFile(vo);
					}
					
					//파일명에 버전 붙이기
					String version = "";
					
					//계약체결단계에서는 아래 계약서회신파일을 썼었으나 
					//법무쪽 회신단계에서 회신파일을 첨부하는 기능이 사라지면서 의뢰쪽 계약서파일을 쓴다.
					//계약체결단계에서 품의상신할 때는 FINAL을 붙인다.
					if("Y".equals(finalVersion)){ 
						String[] amsg = {newFileNm};
						//newFileNm+"_최종본"
						vo.setNewFileNm((String)messageSource.getMessage("common.page.field.mngClmsAttachFile01", amsg, locale1));
					}else{
						version = getFileVer(vo, cnsdreq_id);
						if("LAST_VERSION".equals(version)){
							String[] amsg = {newFileNm};
							//newFileNm+"_최종본"
							vo.setNewFileNm((String)messageSource.getMessage("common.page.field.mngClmsAttachFile01", amsg, locale1));
						}else{
							String[] amsg = {newFileNm,version};
							//newFileNm+"_의뢰"+version+"차"
							vo.setNewFileNm((String)messageSource.getMessage("common.page.field.mngClmsAttachFile02", amsg, locale1));
						}
					}
					
					
				//계약서회신파일
				}else if("F0120202".equals(smlclsfcn) || "F0120203".equals(smlclsfcn)){
					fileGbn = "cntrt";
					
					newFileNm = "";
					
					//기존파일 삭제(계약서파일은 하나만 올릴 수 있으므로)
					if(isExistsFileInfos(fileInfo)){
						vo.setDel_id(vo.getReg_id());
						vo.setFile_smlclsfcn(smlclsfcn);	//Sungwoo added 2014-08-06 It was updated all other status files. therefore, It need to strict condition.
						deleteClmsBeforeFile(vo);
					}
					
					//파일명에 버전 붙이기
					String version = "";
					
					//계약체결단계에서는 아래 계약서회신파일을 썼었으나 
					//법무쪽 회신단계에서 회신파일을 첨부하는 기능이 사라지면서 의뢰쪽 계약서파일을 쓴다.
					//계약체결단계에서 품의상신할 때는 FINAL을 붙인다.
					if("Y".equals(finalVersion)){ 
						String[] amsg = {newFileNm};
						//newFileNm+"_최종본"
						vo.setNewFileNm((String)messageSource.getMessage("common.page.field.mngClmsAttachFile01", amsg, locale1));
					}else{
						version = getFileVer(vo, cnsdreq_id);
						if("LAST_VERSION".equals(version)){
							String[] amsg = {newFileNm};
							//newFileNm+"_최종본"
							vo.setNewFileNm((String)messageSource.getMessage("common.page.field.mngClmsAttachFile01", amsg, locale1));
						}else{
							String[] amsg = {newFileNm,version};
							//newFileNm+"_회신"+version+"차"
							vo.setNewFileNm((String)messageSource.getMessage("common.page.field.mngClmsAttachFile03", amsg, locale1));
						}
					}					
				}
			//계약체결(사본등록)
			}else if("F01203".equals(midclsfcn)){
				fileGbn = "cntrt";
				
				newFileNm = "";
				
				//기존파일 삭제(계약서파일은 하나만 올릴 수 있으므로)
				if(isExistsFileInfos(fileInfo)){
					vo.setDel_id(vo.getReg_id());
					//deleteClmsBeforeFile(vo);
				}
				
				vo.setNewFileNm(newFileNm);
			}
		//표준계약서파일
		}else if("F003".equals(bigclsfcn) && "F00301".equals(midclsfcn) && "N".equals(vo.getMultiYn())){
			//기존파일 삭제(표준계약서파일은 하나만 올릴 수 있으므로)
			if(isExistsFileInfos(fileInfo)){
				vo.setDel_id(vo.getReg_id());
				deleteClmsBeforeFile(vo);
			}
		}
		
		/** 파일정보 저장 및 삭제 **/
		if(isExistsFileInfos(fileInfo)){
			//첨부파일 리스트
			List fileList = getFileInfoToClmFileList(fileInfo);
			
			//첨부파일 삭제
			vo.setDel_id(vo.getSession_user_id());
			vo.setFile_del_mode("PART");
			//deleteClmsFile(vo);
			
			//Sungwoo added 2014-09-26 reAttach 처리 할 경우, 기존 파일 삭제 후 추가
			if("reAttach".equals(vo.getView_gbn())){
				deleteClmsFile(vo);
			}
			
			//첨부파일 순서 구하기
			int fileSrt = maxClmsSeqNo(vo);

			//첨부파일 처리
			if(fileList != null && fileList.size() > 0){
				
				for(int i=0; i<fileList.size(); i++){
					ClmsFileVO file = new ClmsFileVO();
					file = (ClmsFileVO)fileList.get(i);
					
					/* 파일을 신규 추가(저장)*/
					if("add".equals(file.getFile_gbn())){
						
						file.setFile_bigclsfcn(vo.getFile_bigclsfcn());
						file.setFile_midclsfcn(vo.getFile_midclsfcn());
						file.setFile_smlclsfcn(vo.getFile_smlclsfcn());
						file.setRef_key(vo.getRef_key());
						file.setFile_srt(fileSrt);
						file.setNewFileNm(vo.getNewFileNm());
						file.setSys_cd(vo.getSys_cd());
						file.setReg_id(vo.getReg_id());

						if("lib".equals(fileGbn)){
							if(!"".equals(vo.getNewFileNm())){
								String reName = vo.getNewFileNm() + "_"+fileSrt;
								String fileExt = file.getFile_info();
								file.setOrg_file_nm(reName+"."+fileExt);
							}
						}else if("cntrt".equals(fileGbn)){
							if(!"".equals(vo.getNewFileNm())){
								String fileExt = file.getFile_info();
								int indexFile = file.getOrg_file_nm().toLowerCase().indexOf("."+fileExt);								
								String orgFileNm = file.getOrg_file_nm().substring(0,indexFile);
								
								//파일명 길이 체크하여 파일명 재생성
								int totalFileNm = 240;
								if(orgFileNm.length() > totalFileNm){
									orgFileNm = orgFileNm.substring(0, totalFileNm);
								}
								
								file.setOrg_file_nm(orgFileNm+vo.getNewFileNm()+"."+fileExt);
								file.setOcr_work_lang(userLocale);
								
							}
						}							

						/* 파일 정상등록 여부 확인 신성우 2014-04-04 */
						File f = new File(file.getFile_path());
						if(f.exists()){
							insertClmsFile(file);
						}else{
							//throw new Exception("error!!!");
							return false;
						}
						fileSrt++;
					/* 기존 파일 삭제 */
					}else if("delete".equals(file.getFile_gbn())){
						
						this.getLogger().debug("Requested the deleted file : " + vo.getRef_key());
						file.setRef_key(vo.getRef_key());
						
						file.setFile_del_mode("PART");
						file.setDel_id(vo.getReg_id());
						file.setSys_cd(vo.getSys_cd());
						file.setFile_smlclsfcn(smlclsfcn);	//Sungwoo added 2014-08-06 It was updated all other status files. therefore, It need to strict condition.
						
						deleteClmsFile(file);
						
					}else if("old".equals(file.getFile_gbn()) && "reAttach".equals(vo.getView_gbn())){	//Sungwoo added 2014-09-25 파일 reAttach를 위한 분기처리 추가

						Random r = new Random();
						String fileId = "";
						file.setFile_bigclsfcn(vo.getFile_bigclsfcn());
						file.setFile_midclsfcn(vo.getFile_midclsfcn());
						file.setFile_smlclsfcn(vo.getFile_smlclsfcn());
						file.setRef_key(vo.getRef_key());
						file.setFile_srt(fileSrt);
						file.setSys_cd(vo.getSys_cd());
						file.setReg_id(vo.getReg_id());
						
						//fileId = String.valueOf(System.nanoTime())+"_"+String.valueOf(r.nextInt(Integer.MAX_VALUE));
						fileId = getUUID();

						file.setFile_id(fileId);
						file.setNewFileNm(file.getOrg_file_nm());
						file.setFile_info(file.getFile_info());
						file.setFile_path(file.getFile_path());
						file.setFile_sz(Integer.valueOf(file.getFile_sz()).intValue());
						file.setFile_gbn(file.getFile_gbn());

						insertClmsFile(file);
					}
				}
			}
		}
		
		return result;
	}
	//백업
	public boolean mngClmsAttachFile_temp(ClmsFileVO vo, CommonVO cvo) throws Exception{
		boolean result = true;
		String fileGbn = "";
		String fileInfo = vo.getFileInfos();
		//첨부파일 종류
		String bigclsfcn = vo.getFile_bigclsfcn();
		String midclsfcn = vo.getFile_midclsfcn();
		String smlclsfcn = vo.getFile_smlclsfcn();
		String newFileNm = "";
		String finalVersion = StringUtil.bvl(vo.getFinalVersion(), "");
		
		String userLocale = (cvo == null) ? "ko" : (String)cvo.getSession_user_locale();
		
		/** 파일명 생성 **/
		//라이브러리 신규생성
		if("F013".equals(bigclsfcn)){
			fileGbn = "lib";
			newFileNm = getNewFileName(vo, cvo); 
			vo.setNewFileNm(newFileNm);
			//기존첨부파일 수정 김재원 아래와 같은 이유로 변경되는 것 수정함.12.01.14
			//modifyBeforeLibFile(vo);
		//계약
		}else if("F012".equals(bigclsfcn)){
			//계약_검토의뢰
			if("F01202".equals(midclsfcn)){
				//계약서파일
				if("F0120201".equals(smlclsfcn)){
					fileGbn = "cntrt";
				   if("Y".equals(finalVersion)){ 
						ConsultationVO cntrtInfo = getCntrtInfo(vo);
						cntrtInfo.setSession_user_locale(StringUtil.bvl(cvo.getSession_user_locale(), "en"));

						newFileNm = getNewFileName(vo, cntrtInfo);
					}else{
						newFileNm = getNewFileName(vo, cvo);
					}					
					
					//기존파일 삭제(계약서파일은 하나만 올릴 수 있으므로)
					if(isExistsFileInfos(fileInfo)){
						vo.setDel_id(vo.getReg_id());
						deleteClmsBeforeFile(vo);
					}
					
					//파일명에 버전 붙이기
					String version = "";
					
					//계약체결단계에서는 아래 계약서회신파일을 썼었으나 
					//법무쪽 회신단계에서 회신파일을 첨부하는 기능이 사라지면서 의뢰쪽 계약서파일을 쓴다.
					//계약체결단계에서 품의상신할 때는 FINAL을 붙인다.
					if("Y".equals(finalVersion)){ 
						vo.setNewFileNm(newFileNm+"_FINAL");
					}else{
						version = getFileVer_temp(vo);
						vo.setNewFileNm(newFileNm+"_"+version);
					}
					
					//신규파일이 존재하지 않을 경우 기존계약서 파일명 수정(파일버전은 기존그대로)
					if(!isExistsFileInfos(fileInfo)){
						vo.setNewFileNm(newFileNm);
						modifyBeforeCntrtFile(vo);
					}					
					
				//계약서회신파일
				}else if("F0120202".equals(smlclsfcn) || "F0120203".equals(smlclsfcn)){
					fileGbn = "cntrt";
					ConsultationVO cntrtInfo = getCntrtInfo(vo);
					cntrtInfo.setSession_user_locale(StringUtil.bvl(cvo.getSession_user_locale(), "en"));

					newFileNm = getNewFileName(vo, cntrtInfo);
					
					//기존파일 삭제(계약서파일은 하나만 올릴 수 있으므로)
					if(isExistsFileInfos(fileInfo)){
						vo.setDel_id(vo.getReg_id());
						deleteClmsBeforeFile(vo);
					}
					
					Locale locale1 = new Locale((String)cntrtInfo.getSession_user_locale());
					
					//파일명에 버전 붙이기
					String version = "";
					//계약체결단계에서는 아래 계약서회신파일을 썼었으나 
					//법무쪽 회신단계에서 회신파일을 첨부하는 기능이 사라지면서 의뢰쪽 계약서파일을 쓴다.
					//계약체결단계에서 품의상신할 때는 FINAL을 붙인다.
					if("Y".equals(finalVersion)){
						String[] amsg = {newFileNm};
						//newFileNm+"_회신_FINAL"
						vo.setNewFileNm((String)messageSource.getMessage("common.page.field.mngClmsAttachFile_temp01", amsg, locale1));
					}else{
						version = getFileVer_temp(vo);
						String[] amsg = {newFileNm,version};
						//newFileNm+"_회신_"+version
						vo.setNewFileNm((String)messageSource.getMessage("common.page.field.mngClmsAttachFile_temp02", amsg, locale1));
					}
					
					//신규파일이 존재하지 않을 경우 기존계약서 파일명 수정(파일버전은 기존그대로)
					if(!isExistsFileInfos(fileInfo)){
						String[] amsg = {newFileNm};
						//newFileNm+"_회신"
						vo.setNewFileNm((String)messageSource.getMessage("common.page.field.mngClmsAttachFile_temp03", amsg, locale1));
						modifyBeforeCntrtFile(vo);
					}
				}
			//계약체결(사본등록)
			}else if("F01203".equals(midclsfcn)){
				fileGbn = "cntrt";
				//계약정보 조회
				ConsultationVO cntrtInfo = getCntrtInfo(vo);
				cntrtInfo.setSession_user_locale(StringUtil.bvl(cvo.getSession_user_locale(), "en"));
				
				newFileNm = getNewFileName(vo, cntrtInfo);
				
				//기존파일 삭제(계약서파일은 하나만 올릴 수 있으므로)
				if(isExistsFileInfos(fileInfo)){
					vo.setDel_id(vo.getReg_id());
					deleteClmsBeforeFile(vo);
				}
				//파일명에 등록일 및 FINAL 붙이기
				if("com.sec.clm.manage.dto.MyApprovalVO".equals(cvo.getClass().getName())){
					MyApprovalVO svo = (MyApprovalVO) cvo;
					
					if(svo.getCntrt_cnclsnday() != null && !"".equals(svo.getCntrt_cnclsnday())){
						//String cntrtCnlsnday = svo.getCntrt_cnclsnday();
						//cntrtCnlsnday = cntrtCnlsnday.substring(2);
						//vo.setNewFileNm(newFileNm+"_"+cntrtCnlsnday+"_FINAL");
						vo.setNewFileNm(newFileNm+"_"+vo.getFinalAdd());
					}else{
						vo.setNewFileNm(newFileNm);
					}
				}
				else{
					ConclusionVO svo = (ConclusionVO)cvo;
					
					if(svo.getCntrt_cnclsnday() != null && !"".equals(svo.getCntrt_cnclsnday())){
						//String cntrtCnlsnday = svo.getCntrt_cnclsnday();
						//cntrtCnlsnday = cntrtCnlsnday.substring(2);
						//vo.setNewFileNm(newFileNm+"_"+cntrtCnlsnday+"_FINAL");
						vo.setNewFileNm(newFileNm+"_"+vo.getFinalAdd());
					}else{
						vo.setNewFileNm(newFileNm);
					}
				}
			}
		}
		
		/** 파일정보 저장 및 삭제 **/
		if(isExistsFileInfos(fileInfo)){
			//첨부파일 리스트
			List fileList = getFileInfoToClmFileList(fileInfo);
			
			//첨부파일 삭제
			vo.setDel_id(vo.getSession_user_id());
			vo.setFile_del_mode("PART");
			//deleteClmsFile(vo);
			
			//첨부파일 순서 구하기
			int fileSrt = maxClmsSeqNo(vo);
			//첨부파일 처리
			if(fileList != null && fileList.size() > 0){
				for(int i=0; i<fileList.size(); i++){
					ClmsFileVO file = new ClmsFileVO();
					file = (ClmsFileVO)fileList.get(i);
					
					/* 파일을 신규 추가(저장)*/
					if("add".equals(file.getFile_gbn())){
						file.setFile_bigclsfcn(vo.getFile_bigclsfcn());
						file.setFile_midclsfcn(vo.getFile_midclsfcn());
						file.setFile_smlclsfcn(vo.getFile_smlclsfcn());
						file.setRef_key(vo.getRef_key());
						file.setFile_srt(fileSrt);
						file.setNewFileNm(vo.getNewFileNm());
						file.setSys_cd(vo.getSys_cd());
						file.setReg_id(vo.getReg_id());

						if("lib".equals(fileGbn)){
							if(!"".equals(vo.getNewFileNm())){
								String reName = vo.getNewFileNm() + "_"+fileSrt;
								String fileExt = file.getFile_info();
								file.setOrg_file_nm(reName+"."+fileExt);
							}
						}else if("cntrt".equals(fileGbn)){
							if(!"".equals(vo.getNewFileNm())){
								String fileExt = file.getFile_info();
								file.setOrg_file_nm(vo.getNewFileNm()+"."+fileExt);
							}
						}
						
						file.setOcr_work_lang(userLocale);
						
						insertClmsFile(file);
						fileSrt++;
					/* 기존 파일 삭제 */
					}else if("delete".equals(file.getFile_gbn())){
						file.setFile_del_mode("PART");
						file.setDel_id(vo.getReg_id());
						file.setSys_cd(vo.getSys_cd());
						deleteClmsFile(file);
					}
				}
			}
		}else{
			
		}
		return result;
	}
	
	/**
	* 회신 계약서 파일 및 계약서사본등록시 계약정보 읽어오기
	* @param  ClmsFileVO
	* @return ConsultationVO
	* @throws Exception
	*/
	private ConsultationVO getCntrtInfo(ClmsFileVO vo) throws Exception{
		ConsultationVO result = new ConsultationVO();
		
		//계약정보 조회
		String cntrtId = vo.getRef_key();
		int cntrtIndex = cntrtId.indexOf("@");
		if(cntrtIndex >= 0){
			cntrtId = cntrtId.substring(0, cntrtIndex);
		}
		//getLogger().error(">>>>>>>>>>>>>>>>> 검색 계약 Id="+cntrtId);
		HashMap hm = new HashMap();
		hm.put("cntrt_id", cntrtId);
		List cntrtList = commonDAO.list("clms.common.getCntrtInfo", hm); 
		
		if(cntrtList != null && cntrtList.size()>0){
			ListOrderedMap lom = (ListOrderedMap)cntrtList.get(0);

			result.setSys_cd(vo.getSys_cd());
			result.setReg_operdiv((String)lom.get("reg_operdiv"));
			result.setCntrt_oppnt_cd((String)lom.get("cntrt_oppnt_cd"));
			result.setBiz_clsfcn((String)lom.get("biz_clsfcn"));
			result.setDepth_clsfcn((String)lom.get("depth_clsfcn"));
			result.setCnclsnpurps_bigclsfcn((String)lom.get("cnclsnpurps_bigclsfcn"));
			result.setCnclsnpurps_midclsfcn((String)lom.get("cnclsnpurps_midclsfcn"));
		}
		return result;
		
	}
	
	/**
	* 계약서의 경우 신규파일이 올라올 경우 기존 파일은 삭제
	* @param  ClmsFileVO
	* @return void
	* @throws Exception
	*/
	private void deleteClmsBeforeFile(ClmsFileVO vo) throws Exception{
		commonDAO.modify("clms.common.deleteBeforeCntrtFile", vo);
	}

	/**
	* 계약서정보 변경시 기존 파일들 이름 변경
	* @param  ClmsFileVO
	* @return void
	* @throws Exception
	*/
	public void modifyBeforeCntrtFile(ClmsFileVO vo) throws Exception{
		String finalVersion = StringUtil.bvl(vo.getFinalVersion(), "");
		
		List resultList = commonDAO.list("clms.common.getCntrtFileInfo", vo);
		if(resultList != null && resultList.size()>0){
			ListOrderedMap result = (ListOrderedMap)resultList.get(0);
			String orgFileNm = (String)result.get("org_file_nm");
			String fileInfo = (String)result.get("file_info");
			int indexFile = orgFileNm.indexOf("."+fileInfo);
			String tempVersion = "";
			if("Y".equals(finalVersion)){ //임의로 final 붙이기
				tempVersion = "FINAL";
			}else{
				tempVersion = orgFileNm.substring(indexFile-3, indexFile);
			}
			vo.setFile_info(fileInfo);//확장자
			vo.setFileVersion(tempVersion);//버전

			commonDAO.modify("clms.common.modifyBeforeCntrtFile", vo);
		}
		
	}
	
	/**
	* 라이브러리 파일 변경시 기존 파일들 이름 변경
	* @param  ClmsFileVO 
	* @return void
	* @throws Exception
	*/
	public void modifyBeforeLibFile(ClmsFileVO vo) throws Exception{
		commonDAO.modify("clms.common.modifyBeforeLibFile", vo);
	}
	
	/**
	* 파일명 버젼 찾아오기
	* @param  HashMap hm
	* @return String codeNm
	* @throws Exception
	*/
	private String  getFileVer(ClmsFileVO vo, String cnsdreq_id) throws Exception{
		String version = "";
		String plndbnReqYn = "";
		
		HashMap hm = new HashMap();
		hm.put("cnsdreq_id", cnsdreq_id);

		
		//의뢰차수를 가져온다.
		List resultList = commonDAO.list("clms.common.getCnsdLevel", hm);
		
		if(resultList != null && resultList.size()>0){
			ListOrderedMap result = (ListOrderedMap)resultList.get(0);
			
			plndbnReqYn = (String)result.get("plndbn_req_yn");
			
			int org_ver = ((BigDecimal)result.get("cnsd_level")).intValue();
			
			if(org_ver < 10){
				version = "0"+Integer.toString(org_ver);
			}else{
				version = Integer.toString(org_ver);
			}
			
			if("Y".equals(plndbnReqYn)){
				version = "LAST_VERSION";
			}
			
		}else{
			version = "01";
		}
						
		return version;
	}
	//백업
	private String  getFileVer_temp(ClmsFileVO vo) throws Exception{
		String version = "";
		
		//계약정보 조회
		String cntrtId = vo.getRef_key();
		int cntrtIndex = cntrtId.indexOf("@");
		if(cntrtIndex >= 0){
			cntrtId = cntrtId.substring(0, cntrtIndex);
		}
		
		HashMap hm = new HashMap();
		hm.put("cntrt_id", cntrtId);
		
		//동일한 이름의 파일 중 최고 버전을 가져온다.
		List resultList = commonDAO.list("clms.common.getMaxFileNm", hm);
		
		if(resultList != null && resultList.size()>0){
			ListOrderedMap result = (ListOrderedMap)resultList.get(0);
			String orgFileNm = (String)result.get("org_file_nm");
			//getLogger().error(">>>>>>>>>>>>>>>>>orgFileNm="+orgFileNm);
			String fileInfo = (String)result.get("file_info");
			int indexFile = orgFileNm.indexOf("."+fileInfo);
			
			String tempVersion = "";
			
			//체결상신 후 반려가 났을 경우 재의뢰/최종본의뢰를 할수 있다.
			//이미 체결상신을 한 상태기 때문에 FINAL이 붙어있다.
			//FINAL이 있을 경우 그 아래건를 가져와서 버전을 다시 붙인다.
			String isFinal = orgFileNm.substring(indexFile-5, indexFile);
			if("FINAL".equals(isFinal)){
				if(resultList.size() == 1){
					tempVersion = "01";
				}else{
					for(int i=1;i<resultList.size();i++){
						ListOrderedMap result2 = (ListOrderedMap)resultList.get(i);
						
						String orgFileNm2 = (String)result2.get("org_file_nm");
						String fileInfo2 = (String)result2.get("file_info");
						int indexFile2 = orgFileNm2.indexOf("."+fileInfo2);
						
						if(!"FINAL".equals(orgFileNm2.substring(indexFile2-5, indexFile2))){
							tempVersion = orgFileNm2.substring(indexFile2-2, indexFile2);
							break;
						}
					}	
				}
			}else{
				tempVersion = orgFileNm.substring(indexFile-2, indexFile);				
			}			
			
			//getLogger().error(">>>>>>>> 이전 버전"+tempVersion);
			int org_ver = Integer.valueOf(tempVersion).intValue();
			org_ver = org_ver + 1;
			if(org_ver < 10){
				version = "V0"+Integer.toString(org_ver);
			}else{
				version = "V"+ Integer.toString(org_ver);
			}
		}else{
			version = "V01";
		}
		//getLogger().error(">>>>>>>> 생성된 버전"+version);
		return version;
	}
	
	/**
	* ClmsFile : 원본파일명 재생성
	* 계약서와 라이브러리 파일일 경우 파일명을 재 생성한다.
	* @param  ClmsFileVO vo, String gbn(T:Template , R:Reference, E:계약서)
	* @return String 파일명
	* @throws Exception
	*/
	private String getNewFileName(ClmsFileVO fvo, CommonVO vo) throws Exception{
		String result = "";
		
		//2012-07-30
		//라이브러리 및 계약서 파일을 사용자가 입력받은 그대로 쓰기로 변경되었기 때문에 
		//첨부파일명을 변경하는 로직이 필요가 없게 되어 아래 백업 후 기존 로직은 삭제!
		
		return result;
	}
	//백업
	private String getNewFileName_temp(ClmsFileVO fvo, CommonVO vo) throws Exception{
		String result = "";
		
		//사용변수 선언
		String libGbn ="";
		String langGbn ="";
		String bizClsFcn = "";
		String depthClsFcn = "";
		String bigClsFcn = "";
		String midClsFcn = "";
		String sysCd = "";
		String localeCd = "";
		String regOperdiv = "";
		String OppntCd = "";
		//코드명 결과
		String resultNm = "";
		//getLogger().error(">>>>>>>>>>>>>>>>>>>>BIG="+fvo.getFile_bigclsfcn());
		//VO에따른 데이터 설정
		if("F013".equals(fvo.getFile_bigclsfcn())){
			LibraryVO tempvo = (LibraryVO)vo;
			sysCd = StringUtil.bvl(tempvo.getSys_cd(), "");
			libGbn = StringUtil.bvl(tempvo.getLib_gbn(), "");
			localeCd = StringUtil.bvl(tempvo.getSession_user_locale(), "");
			langGbn = StringUtil.bvl(tempvo.getLang_gbn(), "");
			bizClsFcn = StringUtil.bvl(tempvo.getBiz_clsfcn(), "");
			depthClsFcn = StringUtil.bvl(tempvo.getDepth_clsfcn(), "");
			bigClsFcn = StringUtil.bvl(tempvo.getCnclsnpurps_bigclsfcn(), "");
			midClsFcn = StringUtil.bvl(tempvo.getCnclsnpurps_midclsfcn(), "");
		}else if("F012".equals(fvo.getFile_bigclsfcn())){
			//getLogger().error(">>>>>>>>>>>>계약서파일명 생성");             
			//계약서 등록시 CLM,LAS별로 캐스팅을 달리 한다. 
			if("LAS".equals(fvo.getSys_cd()) && "F0120201".equals(fvo.getFile_smlclsfcn())){ //사업부이관건(계약,법무)이면서 의뢰계약서일경우만!!
				ClmsDataUtil.debug("################1");
				com.sec.clm.review.dto.ConsultationVO tempvo= (com.sec.clm.review.dto.ConsultationVO)vo;
				sysCd = StringUtil.bvl(tempvo.getSys_cd(), "");
				regOperdiv = StringUtil.bvl(tempvo.getReg_operdiv(), "");
				OppntCd = StringUtil.bvl(tempvo.getCntrt_oppnt_cd(), "");
				localeCd = StringUtil.bvl(tempvo.getSession_user_locale(), "");
				bizClsFcn = StringUtil.bvl(tempvo.getBiz_clsfcn(), "");
				depthClsFcn = StringUtil.bvl(tempvo.getDepth_clsfcn(), "");
				bigClsFcn = StringUtil.bvl(tempvo.getCnclsnpurps_bigclsfcn(), "");
				midClsFcn = StringUtil.bvl(tempvo.getCnclsnpurps_midclsfcn(), "");
			}else{
				ClmsDataUtil.debug("################2");
				com.sec.clm.manage.dto.ConsultationVO tempvo= (com.sec.clm.manage.dto.ConsultationVO)vo;
				sysCd = StringUtil.bvl(tempvo.getSys_cd(), "");
				regOperdiv = StringUtil.bvl(tempvo.getReg_operdiv(), "");
				OppntCd = StringUtil.bvl(tempvo.getCntrt_oppnt_cd(), "");
				localeCd = StringUtil.bvl(tempvo.getSession_user_locale(), "");
				bizClsFcn = StringUtil.bvl(tempvo.getBiz_clsfcn(), "");
				depthClsFcn = StringUtil.bvl(tempvo.getDepth_clsfcn(), "");
				bigClsFcn = StringUtil.bvl(tempvo.getCnclsnpurps_bigclsfcn(), "");
				midClsFcn = StringUtil.bvl(tempvo.getCnclsnpurps_midclsfcn(), "");
			}
		}
		
		//라이브러리    2012.01.14 라이브러리 파일명 아래와 같이 변경되는 내용 변경되지 않게 수정함. 김재원
//		if("F013".equals(fvo.getFile_bigclsfcn())){
//			//구분_언어_유형1_유형2_유형3-1_유형3-2_등록일_serial number
//			//구분 : T:Template , R:Reference
//			//언어 : 국문(KOR)/영문(ENG)/기타(ETC)구분
//			//1. 구분설정
//			if("C03501".equals(libGbn)){//Reference계약서
//				result += "R";
//			}else if("C03502".equals(libGbn)){//표준Template
//				result += "T";
//			}else if("C03503".equals(libGbn)){//계약조항 Library (해당부분의 구분값은 정해진 바 없음 : 임의로 'I'로 지정)
//				result += "I";
//			}
//			//2.언어설정
//			if(!"".equals(langGbn)){
//			resultNm = getCodeName(sysCd, "common", "C045", langGbn, localeCd);
//			resultNm = StringUtil.StringReplace(resultNm);
//			result += "_" + resultNm;
//			}
//			//3.비즈니스단계명 설정
//			if(!"".equals(bizClsFcn)){
//			resultNm = getCodeName(sysCd, "cntrt", "T01", bizClsFcn, localeCd);
//			resultNm = StringUtil.StringReplace(resultNm);
//			result += "_" + resultNm;
//			}
//			//4.계약단계명 설정
//			if(!"".equals(depthClsFcn)){
//			resultNm = getCodeName(sysCd, "cntrt", "T02", depthClsFcn, localeCd);
//			resultNm = StringUtil.StringReplace(resultNm);
//			result += "_" + resultNm;
//			}
//			//5.대분류명 설정
//			if(!"".equals(bigClsFcn)){
//			resultNm = getCodeName(sysCd, "cntrt", "T03", bigClsFcn, localeCd);
//				resultNm = StringUtil.StringReplace(resultNm);
//				result += "_" + resultNm;
//			}
//			//6.중분류명 설정
//			if(!"".equals(midClsFcn)){
//			resultNm = getCodeName(sysCd, "cntrt", bigClsFcn, midClsFcn, localeCd);
//				resultNm = StringUtil.StringReplace(resultNm);
//				result += "_" + resultNm;
//			}
//			//7.등록일
//			String regDt = DateUtil.getShortDate(new Date()).substring(2, 8);
//			result += "_"+regDt;
//			
//		//계약서파일
//		}else 
		if("F012".equals(fvo.getFile_bigclsfcn())){
			/** 계약주체_계약상대방_비즈단계_계약단계_체결목적_유형_날짜_SEQ 
			 *  총길이 255가 넘을경우 계약상대방명을 줄인다.
			 * **/
			//계약주체
			String cntrtOwner = "";
			//계약상대방
			String cntrtOppnt = "";
			//비즈단계~날짜
			String cntrtEtc = "";

			//1. 계약주체
			cntrtOwner = getDivName(regOperdiv, localeCd);
			cntrtOwner = StringUtil.StringReplace(cntrtOwner);
			
			//2. 계약상대방
			cntrtOppnt = getOppntName(OppntCd);
			cntrtOppnt = "_"+StringUtil.StringReplace(cntrtOppnt);
			
			//3.비즈단계
			resultNm = getCodeName(sysCd, "cntrt", "T01", bizClsFcn, localeCd);
			if (!"".equals(resultNm)) {
				resultNm = StringUtil.StringReplace(resultNm);
				cntrtEtc += "_" + resultNm;
			}
			
			//4.계약단계
			resultNm = getCodeName(sysCd, "cntrt", "T02", depthClsFcn, localeCd);
			if(!"".equals(resultNm)){
				resultNm = StringUtil.StringReplace(resultNm);
				cntrtEtc += "_" + resultNm;
			}				
			
			//5.체결목적(대분류)
			resultNm = getCodeName(sysCd, "cntrt", "T03", bigClsFcn, localeCd);
			if(!"".equals(resultNm)){
				resultNm = StringUtil.StringReplace(resultNm);
				cntrtEtc += "_" + resultNm;
			}				
			
			//6.유형(중분류)
			resultNm = getCodeName(sysCd, "cntrt", bigClsFcn, midClsFcn, localeCd);
			if(!"".equals(resultNm)){
				resultNm = StringUtil.StringReplace(resultNm);
				cntrtEtc += "_" + resultNm;
			}

			//파일명 길이 체크하여 파일명 재생성
			int totalFileNm = 256;
			
			if(result.length() > totalFileNm){
				//파일명중 잘라야 하는 길이
				int moreLength = totalFileNm - result.length();
				//계약상대방명
				int oppntLength = cntrtOppnt.length();
				
				//잘라야하는 파일길이가 계약상대방길이보다 큰경우
				if(moreLength < oppntLength){
					cntrtOppnt = cntrtOppnt.substring(0, oppntLength-moreLength);
				}else{
					cntrtOppnt = "";
				}
			}
			
			result = cntrtOwner + cntrtOppnt + cntrtEtc;			
		}
		
		//getLogger().error(">>>>>>>>>>>>> 생성된 파일 ="+result);
		return result;
	}

	/**
	* 파일명 생성시 코드명칭 가져오기
	* 파일명 생성에 사용하는  공통코드 명칭을 가져온다.
	* @param  HashMap hm
	* @return String codeNm
	* @throws Exception
	*/
	private String getCodeName(String sysCd, String cdGbn, String grpCd, String cd, String localeCd) throws Exception{
		String codeNm = "";
		
		List resultList = null;
		
		HashMap hm = new HashMap();
		hm.put("sys_cd", sysCd);
		hm.put("locale_cd", localeCd);
		hm.put("grp_cd", grpCd);
		hm.put("cd", cd);
		
		if("common".equals(cdGbn)){
			resultList = commonDAO.list("clms.common.getCodeName", hm);
		}else{
			resultList = commonDAO.list("clms.common.getCntrtCodeName", hm);
		}
		if(resultList != null && resultList.size()>0){
			ListOrderedMap result = (ListOrderedMap)resultList.get(0);
			codeNm = (String)result.get("cd_nm");
		}
		
		return codeNm;
	}
	
	/**
	* 파일명 생성시계약사업부명칭 가져오기
	* 파일명 생성에 사용하는  계약사업부 명칭을 가져온다.
	* @param  HashMap hm
	* @return String codeNm
	* @throws Exception
	*/
	private String getDivName(String cd, String localeCd) throws Exception{
		String divNm = "";

		HashMap hm = new HashMap();
		hm.put("cd", cd);
		hm.put("locale_cd", localeCd);
		List resultList = commonDAO.list("clms.common.getDivName", hm);

		if(resultList != null && resultList.size()>0){
			ListOrderedMap result = (ListOrderedMap)resultList.get(0);
			divNm = (String)result.get("cd_nm");
		}
		
		return divNm;
	}

	/**
	* 파일명 생성시계약상대방명칭 가져오기
	* 파일명 생성에 사용하는  계약상대방 명칭을 가져온다.
	* @param  HashMap hm
	* @return String codeNm
	* @throws Exception
	*/
	private String getOppntName(String cd) throws Exception{
		String oppntNm = "";
		String nation = "";

		HashMap hm = new HashMap();
		hm.put("cd", cd);
		List resultList = commonDAO.list("clms.common.getOppntName", hm);

		if(resultList != null && resultList.size()>0){
			ListOrderedMap result = (ListOrderedMap)resultList.get(0);
			oppntNm = (String)result.get("customer_nm1");
			nation = (String)result.get("nation");
			
			/*if(oppntNm.length() > 8){   거래상대방이 APPLE INC. 인경우 오류 발생으로 주석 처리함 
				if("KR".equals(nation)){//한글
					oppntNm = oppntNm.substring(0,8);
				}else{//영문
					oppntNm = oppntNm.substring(0,12);
				}
			}*/
			
			
			if(oppntNm.length() > 8 && "KR".equals(nation)){	//거래상대방 이름이길이가 8 이상이고 국가가 한국인 경우 
				oppntNm = oppntNm.substring(0,8);
			}else if(oppntNm.length() > 12 && !"KR".equals(nation)){//거래상대방이름길이가 12이상이고 한국인 아니경우 
				oppntNm = oppntNm.substring(0,12);
			}else{
				oppntNm = oppntNm;
			}		
		}
		ClmsDataUtil.debug(">>>>>>>>>>>>>>>>>"+oppntNm);
		return oppntNm;
	}

	/**
	* ClmsFile : 첨부파일 관리 : 첨부파일 삭제
	* 각 업무에서 데이터 삭제시 해당 첨부파일을 전부 삭제 한다.
	* 각 서비스Impl에서 호출되는 부분
	* @param  ClmsFileVO vo
	* @return boolean 결과
	* @throws Exception
	*/
	public boolean delClmsAttachFile(ClmsFileVO vo) throws Exception{
		boolean result = true;
		
		vo.setFile_del_mode("ALL");
		int delResult = deleteClmsFile(vo);
		
		if(delResult != 1){
			result = false;
		}
		
		return result;
	}
	
	/**
	* ClmsFile : 첨부파일 Download
	* @param  ClmsFileVO vo
	* @return 다운로드 파일 정보
	* @throws Exception
	*/
	public List clmsFileDownload(ClmsFileVO vo) throws Exception{
		
		return detailClmsFile(vo);
	}
	
	/**
	* ClmsFile : String 으로 넘어온 FileInfo를 FileVO 타입으로 변환하여  FileList로 반환 
	* 첨부파일을 DB 저장시 주로 사용
	* @param    화면에서 넘어온 FileInfo Stringseq_no*로컬파일명*파일정보*서버파일명(경로+실제저장파일명)*크기|....)
	* @return   List
	*/
	private List<ClmsFileVO> getFileInfoToClmFileList(String fileInfos) throws Exception {
		List<ClmsFileVO> resultList = new ArrayList<ClmsFileVO>();
		Set<String> deletedFileKeys=new HashSet<String>();
		ClmsFileVO vo = null;
	 	
		// File 단위로 자른다.
		String[] arrFileInfo = StringUtil.token(fileInfos, "|");

		for(int i = 0; i < arrFileInfo.length; i++){
			String[] arrFileDetail = StringUtil.token(arrFileInfo[i], "*");
			vo = new ClmsFileVO();
			
			vo.setFile_id(arrFileDetail[0]);
			vo.setOrg_file_nm(arrFileDetail[1]);
			vo.setFile_info(arrFileDetail[2]);
			vo.setFile_path(arrFileDetail[3]);
			vo.setFile_sz(Integer.valueOf(arrFileDetail[4]).intValue());
			vo.setFile_gbn(arrFileDetail[6]);

			resultList.add(vo);	
			if("delete".equals(arrFileDetail[6])){
				deletedFileKeys.add(arrFileDetail[0]);
			}
		}
		 
		/*
		 * 2015-04-16 seil park
		 * If there are information of deletion, remove file informations except delete recode by file id.
		 * */
		for (Iterator<ClmsFileVO> iterator = resultList.iterator(); iterator.hasNext();) {
			ClmsFileVO clmsFileVO  = iterator.next();
		    if(deletedFileKeys.contains(clmsFileVO.getFile_id()) && !"delete".equals(clmsFileVO.getFile_gbn())){
		    	iterator.remove();
			}
		     
		}
			
		return resultList;
	}	

	/**
	* ClmsFile : 삭제될 파일 정보 반환
	* @param  ClmsFileVO vo
	* @return 다운로드 파일 정보
	* @throws Exception
	*/
	public String clmsFileDelete(ClmsFileVO vo) throws Exception{
		String fileInfos= "";

		//DB로부터 첨부파일정보 조회
		List fileList = detailClmsFile(vo);
		
		//DB List -> String 변환
		fileInfos = getClmsFileListToFileInfos(fileList, "delete");
		
		return fileInfos;
	}
	
	/**
	 * ClmsFile : 파일다운로드 로그등록
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	public void fileDownLog(LogVO vo) throws Exception {
		commonDAO.insert("secfw.log.insertFileDownLog", vo);
	}	
	
	/**
	* ClmsFile : 첨부파일 리스트 조회
	*
	* @param	ClmsFileVO vo
	* @param	List
	* @return name
	* @throws Exception
	*/
	private List listClmsFile(ClmsFileVO vo) throws Exception {
		List result = null;
		if("F013".equals(vo.getFile_bigclsfcn())){
			result = commonDAO.list("clms.common.listClmsFile", vo);
		}else{
			result = commonDAO.list("clms.common.listClmsFile2", vo);
		}
		return result;
	}

	/**
	* ClmsFile : 첨부파일정보을 DB에 삽입
	*
	* @param	ClmsFileVO vo
	* @return   int insert결과
	* @throws Exception
	*/
	private int insertClmsFile(ClmsFileVO vo) throws Exception {
		
		int file_result = 0;
		
		String file_smlclsfcn = (String)vo.getFile_smlclsfcn();
		
		vo.setOcr_work_lang(vo.getOcr_work_lang());
		
		if("F0120201".equals(file_smlclsfcn)){
			file_result = commonDAO.insert("clms.common.insertClmsFilePDF", vo);
		} else {
			file_result = commonDAO.insert("clms.common.insertClmsFile", vo);
		}
		
		return file_result;
	}

	/**
	* ClmsFile : 첨부파일정보을 DB에서 삭제
	* 실제 삭제 되지 않고 DEL_YN FLAG를 'Y'로 설정
	* @param	ClmsFileVO vo
	* @return   int delete결과
	* @throws Exception
	*/
	private int deleteClmsFile(ClmsFileVO vo) throws Exception {
		 return commonDAO.modify("clms.common.deleteClmsFile", vo);
	}

	/**
	* ClmsFile : 첨부파일정보을 DB에서 상세조회
	* @param	ClmsFileVO vo
	* @return   int delete결과
	* @throws Exception
	*/
	private List detailClmsFile(ClmsFileVO vo) throws Exception {
		
		 return commonDAO.list("clms.common.detailClmsFile", vo);
	}

	/**
	* ClmsFile : 첨부파일정보의 순번 가져오기
	* @param	ClmsFileVO vo
	* @return   int delete결과
	* @throws Exception
	*/
	private int maxClmsSeqNo(ClmsFileVO vo) throws Exception {
		int seqNo = 0;
		
		List fileList = commonDAO.list("clms.common.maxFileSrt", vo);
		
		if(fileList != null && fileList.size()>0){
			ListOrderedMap file = (ListOrderedMap)fileList.get(0);
			seqNo = ((BigDecimal)file.get("max_file_srt")).intValue();
		}
		seqNo = seqNo + 1;
		
		return seqNo;
	}
	
	/**********************************************************************
	* ComFile Mail & ApprovalFile(결재) 처리 영역
	**********************************************************************/	

	/**
	* ComFile : DB정보로부터 첨부파일정보 조회
	* @param  ComFileVO
	* @return String FileInfo
	* @throws Exception
	*/
	public String getComDbFileInfos(ComFileVO vo) throws Exception{
		String fileInfos = "";

		List fileList = null;
		
		//DB정보 읽어오기
		if("mail".equals(vo.getSys_gbn())){	//메일
			fileList = listMailFile(vo);
		}else if("approval".equals(vo.getSys_gbn())){ //결재
			fileList = listApprovalFile(vo);
		}
		
		//DB List -> String 변환
		if(fileList != null && fileList.size()>0){
			fileInfos = getComFileListToFileInfos(fileList, "old");
		}
		
		return fileInfos;
	}
	
	/**
	 * List File을 String FileInfo형식으로 변환 FileList는 ListOrderedMap으로 구성 됨
	 * 
	 * @param List
	 *            File
	 * @return String FileInfo (seq_no*로컬파일명*파일정보*서버파일명(경로+실제저장파일명)*크기|....)
	 */
	private String getComFileListToFileInfos(List collist, String status) throws Exception {

		String fileInfos = "";

		try {
			if (collist.size() > 0) {
				Iterator it = collist.iterator();
				while (it.hasNext()) {
					ListOrderedMap hList = (ListOrderedMap) it.next();
					
					if((hList.get("file_size")) instanceof String){
						fileInfos = fileInfos + (String) hList.get("module_id") + "*" + (String) hList.get("mis_id") + "*" + (String) hList.get("sequence") + "*"
							+ (String) hList.get("file_name") + "*" + (String) hList.get("file_path") + "*" + (hList.get("file_size"))
							+ "*" + status + "|";
					}else{
						fileInfos = fileInfos + (String) hList.get("module_id") + "*" + (String) hList.get("mis_id") + "*" + (String) hList.get("sequence") + "*"
							+ (String) hList.get("file_name") + "*" + (String) hList.get("file_path") + "*" + ((Integer) (hList.get("file_size"))).toString()
							+ "*" + status + "|";
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}

		return fileInfos;
	}
	
	/**
	 * ComFile : 파일을 업로드하기 위한 method HttpServletRequest 객체로 부터 파일에 대한 정보를 가져와서
	 * 서버로 파일을 저장하고 저장된 파일 정보를 return 한다.
	 * 
	 * @param request
	 *            첨부파일에 대한 정보가 포함된 HttpServletRequest 객체
	 * @return 파일정보를 담고 있는 fileInfos
	 * @throws Exception
	 */
	public String comFileUpload(HttpServletRequest request) throws Exception {
		String fileInfos = "";

		// 파일정보 서버에 저장
		List fileList = comFileListSave(request);

		if (fileList != null && fileList.size() > 0) {
			// File List -> String 변환
			fileInfos = getComFileListToFileInfos(fileList, "add");
		}

		return fileInfos;
	}
	
	/**
	 * ComFile : HttpServletRequest 객체로 부터 파일에 대한 정보를 가져와서 파일을 서버에 저장하고 저장된 리스트를
	 * 반환
	 * 
	 * @param request
	 *            첨부파일에 대한 정보가 포함된 HttpServletRequest 객체
	 * @return 파일에 대한 메타 정보를 담고 있는 List 객체
	 * @throws Exception
	 */
	private List comFileListSave(HttpServletRequest request) throws Exception {

		// 파일의 메타정보를 리턴하기 위한 List
		List fileInfoList = new ArrayList();

		/*********************************************************
		 * 변수 정의
		 **********************************************************/
		// 파일Id
		String fileId = "";
		// 원본 파일명
		String orgFileNm = "";
		// 서버 파일명
		String serverFileNm = "";
		// 파일 정보(확장자)
		String fileInfo = "";
		// 업로드구분
		// 파일 서버 업로드 경로
		String serverDir = "";
		// String fileInfos =
		// StringUtil.bvl((String)request.getParameter("fileInfos"), "");
		String allowInfo = propertyService.getProperty("clms.uploadify.allowFileList");

		// 파일의 메타정보(파일사이즈,원래 파일명, 서버에 저장될 파일명, 저장경로)를 저장할 Map
		ListOrderedMap fileMetaInfo = null;

		try {

			MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;

			final Map fileMap = multipart.getFileMap();

			if (fileMap != null) {
				// looping을 위한 Iterator 객체를 가져온다.
				Iterator fileIterator = fileMap.values().iterator();
				MultipartFile file;
				
				while (fileIterator.hasNext()) {

					// 첨부파일 객체를 리스트에서 가져온다.
					file = (MultipartFile) fileIterator.next();

					// 파일에 Size 가 0 보다 큰경우 파일에 대한 정보를 추출한다.
					if (file.getSize() > 0) {
						serverDir = propertyService.getProperty("clms.uploadify.serverDir");

						orgFileNm = file.getOriginalFilename();

						// 원래 파일명이 empty String이 아닐 경우에만 파일에 대한 정보를 추출한다.
						if (!"".equals(orgFileNm)) {

							// 파일의 확장자가 있을 경우 확인
							if (orgFileNm.lastIndexOf(".") != -1) {

								// 파일의 확장자 정보를 소문자로 변환해서 가져온다.
								fileInfo = orgFileNm.substring(orgFileNm.lastIndexOf(".") + 1).toLowerCase();
							}

							// 현재파일확장자가 허용된 확장자 인지 확인 한다.
							if (checkFileInfo(allowInfo, fileInfo)) {
								String saveDir = "";
								// 서버에 저장할 File Id를 생성
								// 구성 : 년월_Nano시간
                				//fileId = DateUtil.getShortDate(new Date()) +"_"+ Long.toString(System.nanoTime());
            					//change from nanotime to currenttimemillis신성우
								
								// 2014-07-22 Kevin changed.
								// To avoid file key duplication, the way to generate the file id changed. 
            					Random r = new Random();
            					//fileId = String.valueOf(System.nanoTime())+"_"+String.valueOf(r.nextInt(Integer.MAX_VALUE));
            					fileId = getUUID();

								// 서버에 저장할 파일명을 생성
								serverFileNm = fileId + "." + fileInfo;

								// 파일을 저장할 서버의 경로를 구하기
								String subDir = DateUtil.getDate(new Date()).substring(0, 6);
								saveDir = serverDir + '/' + subDir;

								// 서버의 저장경로가 없을경우 해당 디렉토리를 생성
								if (!new File(saveDir).exists()) {
									new File(saveDir).mkdirs();
								}

								File uploadedFile = new File(saveDir, serverFileNm);
								// 파일을 서버의 저장경로로 저장한다.
								file.transferTo(uploadedFile);

								// 파일의 메타 정보를 담기 위한 map 객체를 생성후 정보 담기
								fileMetaInfo = new ListOrderedMap();

								fileMetaInfo.put("module_id", " ");
								fileMetaInfo.put("mis_id", " ");
								fileMetaInfo.put("sequence", " ");
								fileMetaInfo.put("file_name", orgFileNm);
								fileMetaInfo.put("file_path", uploadedFile.getAbsolutePath());
								fileMetaInfo.put("file_size", Integer.valueOf(new Long(file.getSize()).toString()));

								fileInfoList.add(fileMetaInfo);
							} else {
								throw new BaseException(
										messageSource.getMessage("secfw.msg.error.fileNonAllow", new String[] { fileInfo }, Locale.getDefault()));
							}
						}
					}
				}
			}
		} catch (FileUploadException e) {
			throw new BaseException(messageSource.getMessage("secfw.msg.error.fileNonAllow", new String[] { fileInfo }, Locale.getDefault()));
		} catch (Exception e) {
			throw new BaseException(messageSource.getMessage("secfw.msg.error.fileNonAllow", new String[] { fileInfo }, Locale.getDefault()));
		}

		return fileInfoList;
	}

	/**
	* ClmsFile : 첨부파일 Download
	* @param  ClmsFileVO vo
	* @return 다운로드 파일 정보
	* @throws Exception
	*/
	public List comFileDownload(ComFileVO vo) throws Exception{
		List result = null;
		
		if("mail".equals(vo.getSys_gbn())){
			result = detailMailFile(vo);
		}else if("approval".equals(vo.getSys_gbn())){
			result = detailApprovalFile(vo);
		}
		return result;
	}
	
	/**
	* ClmsFile : 삭제될 파일 정보 반환
	* @param  ClmsFileVO vo
	* @return 다운로드 파일 정보
	* @throws Exception
	*/
	public String comFileDelete(ComFileVO vo) throws Exception{
		String fileInfos= "";

		//DB로부터 첨부파일정보 조회
		List fileList = null;
		if("mail".equals(vo.getSys_gbn())){
			fileList = detailMailFile(vo);
		}else if("approval".equals(vo.getSys_gbn())){
			fileList = detailApprovalFile(vo);
		}
		
		//DB List -> String 변환
		fileInfos = getComFileListToFileInfos(fileList, "delete");
		
		return fileInfos;
	}
	
	/**
	* ComFile : 첨부파일 관리 : 정보 저장 및 삭제
	* 업무 저장 또는 수정시 에 첨부되는 파일 정보를 수정 또는 삭제한다.
	* 각 서비스Impl에서 호출되는 부분
	* @param  ComFileVO vo
	* @return boolean 결과
	* @throws Exception
	*/
	public boolean mngComAttachFile(ComFileVO vo) throws Exception{
		boolean result = true;
		
		String fileInfo = vo.getFileInfos();
		
		if(isExistsFileInfos(fileInfo)){
			//첨부파일 리스트
			List fileList = getFileInfoToComFileList(fileInfo);
			
			//첨부파일 삭제
			vo.setFile_del_mode("PART");
			//deleteClmsFile(vo);
			
			//첨부파일 순서 구하기
			int fileSeqNo = maxComSeqNo(vo);
			
			//첨부파일 처리
			if(fileList != null && fileList.size() > 0){
				for(int i=0; i<fileList.size(); i++){
					ComFileVO file = new ComFileVO();
					file = (ComFileVO)fileList.get(i);
					
					if("add".equals(file.getFile_gbn())){
						file.setModule_id(vo.getModule_id());
						file.setMis_id(vo.getMis_id());
						file.setSequence(Integer.toString(fileSeqNo));
						if("mail".equals(vo.getSys_gbn())){
							insertMailFile(file);
						}else if("approval".equals(vo.getSys_gbn())){
							insertApprovalFile(file);
						}
						fileSeqNo++;
					}else if("delete".equals(file.getFile_gbn())){
						file.setFile_del_mode(vo.getFile_del_mode());
						file.setModule_id(vo.getModule_id());
						file.setMis_id(vo.getMis_id());
						file.setSequence(vo.getSequence());
						if("mail".equals(vo.getSys_gbn())){
							deleteMailFile(file);
						}else if("approval".equals(vo.getSys_gbn())){
							deleteApprovalFile(file);
						}
					}
				}
			}
		}
		return result;
	}
	
	/**
	* ComFile : String 으로 넘어온 FileInfo를 FileVO 타입으로 변환하여  FileList로 반환 
	* 첨부파일을 DB 저장시 주로 사용
	* @param    화면에서 넘어온 FileInfo Stringseq_no*로컬파일명*파일정보*서버파일명(경로+실제저장파일명)*크기|....)
	* @return   List
	*/
	public List getFileInfoToComFileList(String fileInfos) throws Exception {
		ArrayList resultList = new ArrayList();
		
		ComFileVO vo = null;
	 	
		// File 단위로 자른다.
		String[] arrFileInfo = StringUtil.token(fileInfos, "|");

		for(int i = 0; i < arrFileInfo.length; i++){
			String[] arrFileDetail = StringUtil.token(arrFileInfo[i], "*");
			vo = new ComFileVO();
			
			vo.setModule_id(arrFileDetail[0]);
			vo.setMis_id(arrFileDetail[1]);
			vo.setSequence(arrFileDetail[2]);
			vo.setFile_name(arrFileDetail[3]);
			vo.setFile_path(arrFileDetail[4]);
			vo.setFile_size(arrFileDetail[5]);
			vo.setFile_gbn(arrFileDetail[6]);
			
			resultList.add(vo);	
		}
			
		return resultList;
	}	
	
	/**
	* ComFile : 첨부파일정보의 순번 가져오기
	* @param	ComFileVO vo
	* @return   int delete결과
	* @throws Exception
	*/
	private int maxComSeqNo(ComFileVO vo) throws Exception {
		int seqNo = 0;
		
		List fileList = null;
		
		if("mail".equals(vo.getSys_gbn())){
			fileList = commonDAO.list("clms.common.maxMailFileSeqNo", vo);
		}else if("approval".equals(vo.getSys_gbn())){
			fileList = commonDAO.list("clms.common.maxApprovalFileSeqNo", vo);
		}
		
		if(fileList != null && fileList.size()>0){
			ListOrderedMap file = (ListOrderedMap)fileList.get(0);
			String maxSeq = (String)file.get("max_seq_no");
			if(!"".equals(maxSeq)){
				seqNo = Integer.parseInt(maxSeq);
			}
		}
		seqNo = seqNo + 1;
		
		return seqNo;
	}
	
	/**
	* ComFile : 첨부파일 관리 : 첨부파일 삭제
	* 각 업무에서 데이터 삭제시 해당 첨부파일을 전부 삭제 한다.
	* 각 서비스Impl에서 호출되는 부분
	* @param  ComFileVO vo
	* @return boolean 결과
	* @throws Exception
	*/
	public boolean delComAttachFile(ComFileVO vo) throws Exception{
		boolean result = true;
		
		vo.setFile_del_mode("ALL");

		int delResult = 0;
		if("mail".equals(vo.getSys_gbn())){
			delResult = deleteMailFile(vo);
		}else if("approval".equals(vo.getSys_gbn())){
			delResult = deleteApprovalFile(vo);
		}
		
		if(delResult != 1){
			result = false;
		}
		
		return result;
	}	
	
	/**********************************************************************
	* MailFile 처리 영역
	**********************************************************************/
	
	/**
	* MailFile : 첨부파일 리스트 조회
	* @param	MailFileVO vo
	* @param	List
	* @return name
	* @throws Exception
	*/
	private List listMailFile(ComFileVO vo) throws Exception {
		return commonDAO.list("clms.common.listMailFile", vo);
	}

	/**
	* MailFile : 첨부파일정보을 DB에 삽입
	*
	* @param	MailFileVO vo
	* @return   int insert결과
	* @throws Exception
	*/
	private int insertMailFile(ComFileVO vo) throws Exception {
		return commonDAO.insert("clms.common.insertMailFile", vo);
	}

	/**
	* MailFile : 첨부파일정보을 DB에서 삭제
	* @param	MailFileVO vo
	* @return   int delete결과
	* @throws Exception
	*/
	private int deleteMailFile(ComFileVO vo) throws Exception {
		 return commonDAO.modify("clms.common.deleteMailFile", vo);
	}

	/**
	* MailFile :  첨부파일정보을 DB에서 상세조회
	* @param	MailFileVO vo
	* @return   int delete결과
	* @throws Exception
	*/
	private List detailMailFile(ComFileVO vo) throws Exception {
		 return commonDAO.list("clms.common.detailMailFile", vo);
	}		
	
	/**********************************************************************
	* ApprovalFile(결재) 처리 영역
	**********************************************************************/
		
	/**
	* ApprovalFile : 첨부파일 리스트 조회
	* @param	ApprovalFileVO vo
	* @param	List
	* @return name
	* @throws Exception
	*/
	private List listApprovalFile(ComFileVO vo) throws Exception {
		return commonDAO.list("clms.common.listApprovalFile", vo);
	}

	/**
	* ApprovalFile : 첨부파일정보을 DB에 삽입
	*
	* @param	ApprovalFileVO vo
	* @return   int insert결과
	* @throws Exception
	*/
	private int insertApprovalFile(ComFileVO vo) throws Exception {
		return commonDAO.insert("clms.common.insertApprovalFile", vo);
	}

	/**
	* ApprovalFile : 첨부파일정보을 DB에서 삭제
	* @param	ApprovalFileVO vo
	* @return   int delete결과
	* @throws Exception
	*/
	private int deleteApprovalFile(ComFileVO vo) throws Exception {
		 return commonDAO.modify("clms.common.deleteApprovalFile", vo);
	}

	/**
	* ApprovalFile :  첨부파일정보을 DB에서 상세조회
	* @param	ApprovalFileVO vo
	* @return   int list조회결과
	* @throws Exception
	*/
	private List detailApprovalFile(ComFileVO vo) throws Exception {
		 return commonDAO.list("clms.common.detailApprovalFile", vo);
	}	
	
	/**********************************************************************
	* 업로드
	**********************************************************************/	
	
	/**
	 * 이미지 또는 엑셀파일등 단일 파일 업로드시 사용
	 * HttpServletRequest 객체로 부터 파일에 대한 정보를 가져와서
	 * 서버로 파일을 저장하고 저장된 파일 정보를 return 한다.
	 * @param request 첨부파일에 대한 정보가 포함된 HttpServletRequest 객체
	 * @return 파일정보를 담고 있는 fileInfos
	 * @throws Exception
	 */
	public String fileUpload(HttpServletRequest request) throws Exception {
		String fileInfos = "";
		
		//파일정보 서버에 저장
		fileInfos = fileListSave(request);
		
//		getLogger().error(">>>>>>>>>>>Services=fileInfos="+fileInfos);
		return fileInfos;
	}

	/**
	 * HttpServletRequest 객체로 부터 파일에 대한 정보를 가져와서
	 * 파일을 서버에 저장하고 저장된 리스트를 반환
	 * @param request 첨부파일에 대한 정보가 포함된 HttpServletRequest 객체
	 * @return 파일에 대한 메타 정보를 담고 있는 List 객체
	 * @throws Exception
	 */
	private String fileListSave(HttpServletRequest request) throws Exception {

		// 파일의 메타정보를 리턴하기 위한 List 
		String fileInfos = "";

		/*********************************************************
		 * 변수 정의
		**********************************************************/
		// 파일Id
		String fileId = "";
		// 원본 파일명
		String orgFileNm = "";
		// 서버 파일명
		String serverFileNm = "";
		// 파일 정보(확장자)
		String fileInfo = "";
		// 파일 서버 업로드 경로
		String serverDir = propertyService.getProperty("clms.file.default.dir");
		// 허용된 파일 확장자
		String allowInfo = propertyService.getProperty("clms.uploadify.allowFileList");
		
		// 파일의 메타정보(파일사이즈,원래 파일명, 서버에 저장될 파일명, 저장경로)를 저장할 Map
		ListOrderedMap fileMetaInfo = null;
		
		// FileItem 를 위한 Factory 생성
		FileItemFactory factory = new DiskFileItemFactory();
		
		// MultipartHttpServletRequest 객체에 담겨진 내용중에서 첨부 파일의 정보를 가져온다. 
        ServletFileUpload upload = new ServletFileUpload(factory);
        
        // 첨부파일 upload진행상황을 표시하기위한 Liestener
        //FileUploadListener listener = new FileUploadListener(request);

        // 첨부파일 정보 리스트 객체 
        List uploadedItems = null;
        
        // 첨부파일 정보
        FileItem fileItem = null;
        
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        
        try {
        	// 첨부파일 정보 리스트를 가져온다.
            uploadedItems = upload.parseRequest(request);
            // looping을 위한 Iterator 객체를 가져온다.
            Iterator i = uploadedItems.iterator();

            // request 객체로 넘어온 첨부파일의 개수 만큼 loop를 수행
            while (i.hasNext()) {
                
            	// 첨부파일 객체를 리스트에서 가져온다.
                fileItem = (FileItem) i.next();

                // 첨부파일 객체인지 여부 확인.
                if (fileItem.isFormField() == false) {
                	
                	// 파일에 Size 가 0 보다 큰경우 파일에 대한 정보를 추출한다.
                    if (fileItem.getSize() > 0) {
                        File uploadedFile = null;
                        
                        // 첨부파일의 전체 경로명을 가져온다.
                        String myFullFileName = fileItem.getName();
                        
                        // 첨부파일명을 알아내기 위해 pathSeparator 마지막 index를 가져온다.
                        int startIndex = myFullFileName.lastIndexOf(File.pathSeparator);
                        
                        // 사용자가 첨부한 파일의 원래 이름을 가져온다.
                        orgFileNm = myFullFileName.substring(startIndex + 1, myFullFileName.length());
                        
                        // 원래 파일명이 empty String이 아닐 경우에만 파일에 대한 정보를 추출한다.
            			if (!"".equals(orgFileNm)) {
            				
            				// 파일의 확장자가 있을 경우 확인
            				if (orgFileNm.lastIndexOf(".") != -1) {
            					
            					// 파일의 확장자 정보를 소문자로 변환해서 가져온다.
            					 fileInfo = orgFileNm.substring(orgFileNm.lastIndexOf(".") + 1).toLowerCase();
            				}

            				if (checkFileInfo(allowInfo, fileInfo)) {
	            				// 서버에 저장할 File Id를  생성
	            				// 구성 : 년월_Nano시간
                				//fileId = DateUtil.getShortDate(new Date()) +"_"+ Long.toString(System.nanoTime());
            					//change from nanotime to currenttimemillis신성우

            					// 2014-07-22 Kevin changed.
            					// To avoid file key duplication, the way to generate the file id changed. 
            					//Random r = new Random();
            					//fileId = String.valueOf(System.nanoTime())+"_"+String.valueOf(r.nextInt(Integer.MAX_VALUE));
            					fileId = getUUID();
            					
	            				// 서버에 저장할 파일명을 생성
	            				serverFileNm = fileId + "." + fileInfo;
	            				                				
	            				// 파일을 저장할 서버의 경로를 구하기
	            				String saveDir = serverDir;
	            			
	            				// 서버의 저장경로가 없을경우 해당 디렉토리를 생성
	            				if (!new File(saveDir).exists()) {
	            					new File(saveDir).mkdirs();
	            				}
	            			
	            				// 파일을 서버의 저장경로로 저장한다.
	            				uploadedFile = new File(saveDir, serverFileNm);
	                            fileItem.write(uploadedFile);
	                            
	                            //저장된 경로 리턴
	                            fileInfos = serverFileNm + "*"+ fileInfo +"*"+uploadedFile.getAbsolutePath()+"|";
            				}
            			}
                    }
                }

            }
        } catch (FileUploadException e) {
        	throw new BaseException(messageSource.getMessage(
					"secfw.msg.error.fileNonAllow", new String[] {fileInfo}, Locale.getDefault()));
        } catch (Exception e) {
        	throw new BaseException(messageSource.getMessage(
					"secfw.msg.error.fileNonAllow", new String[] {fileInfo}, Locale.getDefault()));
        }

        return fileInfos;
	}	
	
	/**
	 * 파일 업로드 후 업로드된 파일을 지정 업무폴더로 이동하고 파일명을 변경한다.
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String moveFile(HttpServletRequest request) throws Exception{
		String result = "";
		
		String fileInfos = StringUtil.bvl((String)request.getParameter("fileInfos"), "");
		String fileNm = StringUtil.bvl((String)request.getParameter("fileNm"), "");
		String folderNm = StringUtil.bvl((String)request.getParameter("folderNm"), "");
		String fileType = StringUtil.bvl((String)request.getParameter("fileType"), "");
		
/*		getLogger().error(">>>>>>>>>fileInfos="+fileInfos);
		getLogger().error(">>>>>>>>>fileNm="+fileNm);
		getLogger().error(">>>>>>>>>folderNm="+folderNm);
		getLogger().error(">>>>>>>>>fileType="+fileType);
*/
		String targetDir = "";
		String targetFileNm = "";
		String targetFilePath = "";
		
		if(!"".equals(fileInfos)){
			String orgFilePath = getFileName(fileInfos, "filepath");
			String orgFileExt = "."+getFileName(fileInfos, "extname");
			String orgFileNm = getFileName(fileInfos, "filename");
			getLogger().error(">>>>>>>>>원본파일="+orgFilePath);
			
			
			//복사될 폴더 경로 설정
			if("lawyer".equals(folderNm)){
				targetDir = propertyService.getProperty("clms.file.lawyer.dir");
			}else{
				targetDir = propertyService.getProperty("clms.file.default.dir");
			}
			
			//파일형식이 이미지 일경우 확장자 제거
			if("image".equals(fileType)){
				targetFileNm = fileNm;
			}else{
				targetFileNm = fileNm+orgFileExt;
			}
			
			targetFilePath = targetDir + "/" + targetFileNm;
//			getLogger().error(">>>>>>>>>타겟파일="+targetFilePath);
			
			File orgFile = new File(orgFilePath);
			
			File writeFile = new File(targetFilePath);
			
			if(writeFile.isFile()){
				writeFile.delete();
			}
			//파일이동
			boolean success = orgFile.renameTo(writeFile);
			//원본파일 삭제
			orgFile.delete();
			
		}
		return result;
	}
	
	private String getFileName(String fileInfos, String gbn) throws Exception {

		String result = "";
		
		ArrayList resultList = new ArrayList();
		
		// File 단위로 자른다.
		String[] arrFileInfo = StringUtil.token(fileInfos, "|");

		for(int i = 0; i < arrFileInfo.length; i++){
			String[] arrFileDetail = StringUtil.token(arrFileInfo[i], "*");
			if("filepath".equals(gbn)){		//파일경로
				result = arrFileDetail[2];
			}else if("extname".equals(gbn)){	//파일 확장자
				result = arrFileDetail[1];
			}else if("filename".equals(gbn)){ //실제  서버에 올라있는 파일명
				result = arrFileDetail[0];
			}
		}
			
		return result;
	}
	/**********************************************************************
	* 공통처리 부분
	**********************************************************************/	
	
	/**
	 * 첨부파일 유무. FileInfos정보로 파일이 있는지 없는지 판별<P>
	 *
	 * @param 
	 * @return boolean
	 * @throws Exception
	*/
	public boolean isExistsFileInfos(String fileInfos) throws Exception {
		
		boolean result = false;
		
		if(fileInfos != null && !fileInfos.trim().equals("") && fileInfos.length() > 10) {
			result = true;
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	





	

	
	

	


	  /**
	  * String 으로 넘어온 File Info를 ESB 첨부파일VO 타입으로 변환하여 넘겨준다.
	  *
	  * @param    화면에서 넘어온 FileInfo Stringseq_no*로컬파일명*파일정보*서버파일명(경로+실제저장파일명)*크기|....)
	  * @return   FileVO
	  */
	 public ArrayList getMailAttachEtyVO(String fileInfos) throws Exception {

		 ArrayList result = new ArrayList();
		 MailVO vo = null;
		 	
		// File 단위로 자른다.
		String[] arrFileInfo = StringUtil.token(fileInfos, "|");
		
		for(int i = 0; i < arrFileInfo.length; i++){
				
			String[] arrFileDetail = StringUtil.token(arrFileInfo[i], "*");
			vo = new MailVO();
			
			vo.setIfseq_id(String.valueOf(i));
			vo.setFile_name(arrFileDetail[1]);
			vo.setLocal_path(arrFileDetail[3]);
			
			result.add(vo);			
		}
			
		return result;
	 }
	 
	  /**
	  * 첨부파일 여부 확인
	  *
	  * @param    
	  * @return   
	  */
	 public JSONArray getClmsFilePageCheck(ClmsFileVO vo) throws Exception {

		List list = commonDAO.list("clms.common.getClmsFilePageCheck", vo);
		
		JSONArray  jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		
		if(list!=null && list.size()>0) {
			jsonObject = new JSONObject();
			jsonObject.put("exists","Y");
			jsonObject.put("fileInfoName",StringUtil.bvl(vo.getFileInfoName(), "fileInfos"));
			jsonObject.put("fileFrameName",StringUtil.bvl(vo.getFileFrameName(), "fileList"));
			jsonObject.put("file_bigclsfcn",StringUtil.bvl(vo.getFile_bigclsfcn(), ""));
			jsonObject.put("file_midclsfcn",StringUtil.bvl(vo.getFile_midclsfcn(), ""));
			jsonObject.put("file_smlclsfcn",StringUtil.bvl(vo.getFile_smlclsfcn(), ""));
			jsonObject.put("ref_key",StringUtil.bvl(vo.getRef_key(), ""));
			jsonObject.put("view_gbn",StringUtil.bvl(vo.getView_gbn(), ""));

			jsonArray.add(jsonObject);
		}else{
			jsonObject = new JSONObject();
			jsonObject.put("exists","N");
			jsonObject.put("fileInfoName",StringUtil.bvl(vo.getFileInfoName(), "fileInfos"));
			jsonObject.put("fileFrameName",StringUtil.bvl(vo.getFileFrameName(), "fileList"));
			jsonObject.put("file_bigclsfcn",StringUtil.bvl(vo.getFile_bigclsfcn(), ""));
			jsonObject.put("file_midclsfcn",StringUtil.bvl(vo.getFile_midclsfcn(), ""));
			jsonObject.put("file_smlclsfcn",StringUtil.bvl(vo.getFile_smlclsfcn(), ""));
			jsonObject.put("ref_key",StringUtil.bvl(vo.getRef_key(), ""));
			jsonObject.put("view_gbn",StringUtil.bvl(vo.getView_gbn(), ""));
			
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	 }	
	 
	/*
	 * 계약 체결단계에서 임시저장 및 체결품의 올릴떄 기존 파일에 FINAL을 붙인다.
	 * */ 
	 public boolean mngF012AttachFile(ClmsFileVO vo, CommonVO cvo) throws Exception{
		 boolean result = true;
		 
		 String newFileNm = "";
		 ConsultationVO cntrtInfo = getCntrtInfo(vo);
		 cntrtInfo.setSession_user_locale(StringUtil.bvl(cvo.getSession_user_locale(), "en"));

		 newFileNm = getNewFileName(vo, cntrtInfo);
			
		 vo.setNewFileNm(newFileNm);
		 modifyBeforeCntrtFile(vo);
		 
		 return result;
	 }
	 
	 /**
		 * CpmsFile : 파일을 업로드하기 위한 method
		 * HttpServletRequest 객체로 부터 파일에 대한 정보를 가져와서
		 * 서버로 파일을 저장하고 저장된 파일 정보를 return 한다.
		 * @param request 첨부파일에 대한 정보가 포함된 HttpServletRequest 객체
		 * @return 파일정보를 담고 있는 fileInfos
		 * @throws Exception
		 */
		public String cpmsFileUpload(HttpServletRequest request) throws Exception {
			String fileInfos = "";
			
			//파일정보 서버에 저장
			List fileList = CpmsFileListSave(request);
			
			if(fileList != null && fileList.size() > 0){
				//File List -> String 변환
				fileInfos = getClmsFileListToFileInfos(fileList, "add");
			}		
			return fileInfos;
		}
		
		/**
		 * CpmsFile : HttpServletRequest 객체로 부터 파일에 대한 정보를 가져와서
		 * 파일을 서버에 저장하고 저장된 리스트를 반환
		 * @param request 첨부파일에 대한 정보가 포함된 HttpServletRequest 객체
		 * @return 파일에 대한 메타 정보를 담고 있는 List 객체
		 * @throws Exception
		 */
		private List CpmsFileListSave(HttpServletRequest request) throws Exception {

			// 파일의 메타정보를 리턴하기 위한 List 
			List fileInfoList = new ArrayList();

			/*********************************************************
			 * 변수 정의
			**********************************************************/
			// 파일Id
			String fileId = "";
			// 원본 파일명
			String orgFileNm = "";
			// 서버 파일명
			String serverFileNm = "";
			// 파일 정보(확장자)
			String fileInfo = "";
			// 파일 서버 업로드 경로
			String serverDir = propertyService.getProperty("clms.uploadify.serverDir");
			
			String allowInfo = propertyService.getProperty("clms.uploadify.allowFileList");
			
			// 파일의 메타정보(파일사이즈,원래 파일명, 서버에 저장될 파일명, 저장경로)를 저장할 Map
			ListOrderedMap fileMetaInfo = null;
			
			// FileItem 를 위한 Factory 생성
			FileItemFactory factory = new DiskFileItemFactory();
			
			// MultipartHttpServletRequest 객체에 담겨진 내용중에서 첨부 파일의 정보를 가져온다. 
	        ServletFileUpload upload = new ServletFileUpload(factory);
	        
	        // 첨부파일 upload진행상황을 표시하기위한 Liestener
	        //FileUploadListener listener = new FileUploadListener(request);

	        // 첨부파일 정보 리스트 객체 
	        List uploadedItems = null;
	        
	        // 첨부파일 정보
	        FileItem fileItem = null;
	        
	        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	        
	        try {
	        	// 첨부파일 정보 리스트를 가져온다.
	            uploadedItems = upload.parseRequest(request);
	            // looping을 위한 Iterator 객체를 가져온다.
	            Iterator i = uploadedItems.iterator();

	            // request 객체로 넘어온 첨부파일의 개수 만큼 loop를 수행
	            while (i.hasNext()) {
	                
	            	// 첨부파일 객체를 리스트에서 가져온다.
	                fileItem = (FileItem) i.next();

	                // 첨부파일 객체인지 여부 확인.
	                if (fileItem.isFormField() == false) {
	                	
	                	// 파일에 Size 가 0 보다 큰경우 파일에 대한 정보를 추출한다.
	                    if (fileItem.getSize() > 0) {
	                        File uploadedFile = null;
	                        
	                        // 첨부파일의 전체 경로명을 가져온다.
	                        String myFullFileName = fileItem.getName();
	                        
	                        // 첨부파일명을 알아내기 위해 pathSeparator 마지막 index를 가져온다.
	                        int startIndex = myFullFileName.lastIndexOf(File.pathSeparator);
	                        
	                        // 사용자가 첨부한 파일의 원래 이름을 가져온다.
	                        orgFileNm = myFullFileName.substring(startIndex + 1, myFullFileName.length());
	                        
	                        // 원래 파일명이 empty String이 아닐 경우에만 파일에 대한 정보를 추출한다.
	            			if (!"".equals(orgFileNm)) {
	            				
	            				// 파일의 확장자가 있을 경우 확인
	            				if (orgFileNm.lastIndexOf(".") != -1) {
	            					
	            					// 파일의 확장자 정보를 소문자로 변환해서 가져온다.
	            					 fileInfo = orgFileNm.substring(orgFileNm.lastIndexOf(".") + 1).toLowerCase();
	            				}
	            				
	            				
	                				// 서버에 저장할 File Id를  생성
	                				// 구성 : 년월_Nano시간
	                				//fileId = DateUtil.getShortDate(new Date()) +"_"+ Long.toString(System.nanoTime());
	            					//TODO : change from nanotime to currenttimemillis신성우
	            					//fileId = DateUtil.getShortDate(new Date()) +"_"+ Long.toString(System.currentTimeMillis());
	            					fileId = getUUID();

	                				// 서버에 저장할 파일명을 생성
	                				serverFileNm = fileId + "." + fileInfo;
	                				
	                				// 파일을 저장할 서버의 경로를 구하기
	                				String subDir = DateUtil.getDate(new Date()).substring(0,6);
	                				String saveDir = serverDir +'/'+subDir;
	                			
	                				// 서버의 저장경로가 없을경우 해당 디렉토리를 생성
	                				if (!new File(saveDir).exists()) {
	                					new File(saveDir).mkdirs();
	                				}
	                			
	                				// 파일을 서버의 저장경로로 저장한다.
	                				uploadedFile = new File(saveDir, serverFileNm);
	                                fileItem.write(uploadedFile);
	                                
	                				// 파일의 메타 정보를 담기 위한 map 객체를 생성후 정보 담기
	                                fileMetaInfo = new ListOrderedMap();
	      
	                                fileMetaInfo.put("file_id", fileId);
	                                fileMetaInfo.put("org_file_nm", orgFileNm);
	                                fileMetaInfo.put("file_info", fileInfo);
	                                fileMetaInfo.put("file_path", uploadedFile.getAbsolutePath());
	                                fileMetaInfo.put("file_sz", Integer.valueOf(new Long(fileItem.getSize()).toString()));
	                                fileMetaInfo.put("dn_cnt", 0);

	                				fileInfoList.add(fileMetaInfo);
	            			}
	                    }
	                }

	            }
	        } catch (FileUploadException e) {
	        	throw new BaseException(messageSource.getMessage(
						"secfw.msg.error.fileNonAllow", new String[] {fileInfo}, Locale.getDefault()));
	        } catch (Exception e) {
	        	throw new BaseException(messageSource.getMessage(
						"secfw.msg.error.fileNonAllow", new String[] {fileInfo}, Locale.getDefault()));
	        }

	        return fileInfoList;
		}		
	 
		/**
		* CpmsFile : String 으로 넘어온 FileInfo를 FileVO 타입으로 변환하여  FileList로 반환 
		* 첨부파일을 DB 저장시 주로 사용
		* @param    화면에서 넘어온 FileInfo Stringseq_no*로컬파일명*파일정보*서버파일명(경로+실제저장파일명)*크기|....)
		* @return   List
		*/
		public List getFileInfoToCpmsFileList(String fileInfos) throws Exception {
			ArrayList resultList = new ArrayList();
			
			ClmsFileVO vo = null;
		 	
			// File 단위로 자른다.
			String[] arrFileInfo = StringUtil.token(fileInfos, "|");

			for(int i = 0; i < arrFileInfo.length; i++){
				String[] arrFileDetail = StringUtil.token(arrFileInfo[i], "*");
				vo = new ClmsFileVO();
				
				vo.setFile_id(arrFileDetail[0]);
				vo.setOrg_file_nm(arrFileDetail[1]);
				vo.setFile_info(arrFileDetail[2]);
				vo.setFile_path(arrFileDetail[3]);
				vo.setFile_sz(Integer.valueOf(arrFileDetail[4]).intValue());
				vo.setFile_gbn(arrFileDetail[6]);

				resultList.add(vo);	
			}
				
			return resultList;
		}	
		
		
		/**
		* 표준계약서/최종본 변경검토 정보 승인시 기존 파일들 이름 변경
		* @param  ClmsFileVO
		* @return void
		* @throws Exception
		*/
		public void modifyStdCntrtFile(ClmsFileVO vo) throws Exception{
			String finalVersion = StringUtil.bvl(vo.getFinalVersion(), "");
			String last_locale = StringUtil.nvl((String)vo.getSession_user_locale(), "en");
			Locale locale1 = new Locale(last_locale);
			
			List resultList = commonDAO.list("clms.common.getCntrtFileInfo", vo);
			if(resultList != null && resultList.size()>0){
				ListOrderedMap result = (ListOrderedMap)resultList.get(0);
				String orgFileNm = (String)result.get("org_file_nm");
				String fileInfo = (String)result.get("file_info");
				
				int indexFile =  orgFileNm.lastIndexOf("_");
				String tempVersion = "";
				if("Y".equals(finalVersion)){ //임의로 final 붙이기
					//최종본
					tempVersion = (String)messageSource.getMessage("common.page.field.modifyStdCntrtFile", null, locale1);
					vo.setNewFileNm(orgFileNm.substring(0, indexFile));
				}
				vo.setFile_info(fileInfo);//확장자
				vo.setFileVersion(tempVersion);//버전

				commonDAO.modify("clms.common.modifyBeforeCntrtFile", vo);
			}
			
		}
		
		/**
		* 표지인쇄에서 파일정보 조회 
		* 생성자: 소유진 (2012.09.11 표지인쇄시 첨부파일사본정보가 누락되어 생성)
		* @param  ClmsFileVO
		* @return ListOrderedMap
		* @throws Exception*/	
		
		public ListOrderedMap getFileInfo(ClmsFileVO vo) throws Exception{
			
			
			//DB로부터 첨부파일정보 조회
			List resultList = null;
			ListOrderedMap hList =null;
			resultList = commonDAO.list("clms.common.listClmsFile2", vo);
		     Iterator it = resultList.iterator();
		 		while(it.hasNext())
		 		{
		 		     hList = (ListOrderedMap)it.next();
		 			
		 		}			
			
			return hList;
		}

		public List tncFileListSave(List fileList) throws Exception {

			// 파일의 메타정보를 리턴하기 위한 List
			List fileInfoList = new ArrayList();

			/*********************************************************
			 * 변수 정의
			 **********************************************************/
			// 파일Id
			String fileId = "";
			// 원본 파일명
			String orgFileNm = "";
			// 서버 파일명
			String serverFileNm = "";
			// 파일 정보(확장자)
			String fileInfo = "";
			// 업로드구분
			// 파일 서버 업로드 경로
			String serverDir = "";
			// String fileInfos =
			// StringUtil.bvl((String)request.getParameter("fileInfos"), "");
			String allowInfo = propertyService.getProperty("clms.uploadify.allowFileList");

			// 파일의 메타정보(파일사이즈,원래 파일명, 서버에 저장될 파일명, 저장경로)를 저장할 Map
			ListOrderedMap fileMetaInfo = null;
			ListOrderedMap fileLom = null;
			
			File file = null;
//			File tempFile = null;

			try {
				

				for (int j = 0; j < fileList.size(); j++) {
					fileLom = (ListOrderedMap) fileList.get(j);
					
					file = new File((String)fileLom.get("file_path"));
					
					if(file.length() > 0) {
	        			serverDir = propertyService.getProperty("clms.uploadify.serverDir");
	        			
						orgFileNm = file.getName();
						
						// 원래 파일명이 empty String이 아닐 경우에만 파일에 대한 정보를 추출한다.
	        			if (!"".equals(orgFileNm)) {
	        				
	        				// 파일의 확장자가 있을 경우 확인
	        				//getLogger().info("orgFileNm.lastIndexOf(".") =" + orgFileNm.lastIndexOf("."));
	        				if (orgFileNm.lastIndexOf(".") != -1) {
	        					
	        					// 파일의 확장자 정보를 소문자로 변환해서 가져온다.
	        					 fileInfo = orgFileNm.substring(orgFileNm.lastIndexOf(".") + 1).toLowerCase();
	        				}else{
	        					if ((String)fileLom.get("file_info") != null)
	        					fileInfo = ((String)fileLom.get("file_info")).toLowerCase();
	        				}
	        				
	        				// 현재파일확장자가 허용된 확장자 인지 확인 한다. 
	        				if (clmsFileService.checkFileInfo(allowInfo, fileInfo)) {
	        					String saveDir = "";
	    						// 서버에 저장할 File Id를  생성
	            				// 구성 : 년월_Nano시간
	            				//fileId = DateUtil.getShortDate(new Date()) +"_"+ Long.toString(System.nanoTime());
	        					//TODO : change from nanotime to currenttimemillis신성우
	        					//fileId = DateUtil.getShortDate(new Date()) +"_"+ Long.toString(System.currentTimeMillis());
	        						        					
	        					//Changed fileID to UUID, as when the multiful files are coming, duplicated error
	        					//UUID uid = UUID.randomUUID();	        					
	        					//String UUIDSTR = uid.toString().replaceAll("-", "");	        					
	        					//fileId = UUIDSTR.substring(0, 32);	      
	        					fileId = getUUID();

	            				// 서버에 저장할 파일명을 생성
	            				serverFileNm = fileId + "." + fileInfo;
	            				
	            				// 파일을 저장할 서버의 경로를 구하기
	            				String subDir = DateUtil.getDate(new Date()).substring(0,6);
	            				saveDir = serverDir +'/'+subDir;
	        			
	            				// 서버의 저장경로가 없을경우 해당 디렉토리를 생성
	            				if (!new File(saveDir).exists()) {
	        						new File(saveDir).mkdirs();
	        					}
	        					
	            				File uploadedFile = new File(saveDir, serverFileNm);
	            				
	        					// 파일을 서버의 저장경로로 저장한다.	
	            				FileUtil.moveFile(file, uploadedFile);
	        					
	                        
	            				// 파일의 메타 정보를 담기 위한 map 객체를 생성후 정보 담기
	                            fileMetaInfo = new ListOrderedMap();
	  
	                            fileMetaInfo.put("file_id", fileId);
	                            fileMetaInfo.put("org_file_nm", (String)fileLom.get("org_file_nm"));
	                            fileMetaInfo.put("file_info", fileInfo);
	                            fileMetaInfo.put("file_path", uploadedFile.getAbsolutePath());
	                            fileMetaInfo.put("file_sz", Integer.parseInt(String.valueOf(fileLom.get("file_sz"))));
	                            fileMetaInfo.put("dn_cnt", 0);

	            				fileInfoList.add(fileMetaInfo);
	            				
	            				InfFileAttachVO infFileAttachVO = new InfFileAttachVO();
	                			infFileAttachVO.setFile_id((String)fileLom.get("file_id"));
	                			infFileAttachVO.setFile_srt(String.valueOf(fileLom.get("file_srt")));
	                			infFileAttachVO.setEcms_result_flag("Y");
	                			infFileAttachVO.setEcms_result("SUCCESS" );
	                			commonDAO.modify("common.legacyintf.updateAttachFailInfo", infFileAttachVO);
	        				}else{
	        					throw new BaseException(messageSource.getMessage(
	            						"secfw.msg.error.fileNonAllow", new String[] {fileInfo}, Locale.getDefault()));
	        				}
	        			}
	        		}else{	// 해당 파일이 존재하지 않을 경우 로그를 남김
	        			InfFileAttachVO infFileAttachVO = new InfFileAttachVO();
	        			infFileAttachVO.setFile_id((String)fileLom.get("file_id"));
	        			infFileAttachVO.setFile_srt(String.valueOf(fileLom.get("file_srt")));
	        			infFileAttachVO.setEcms_result_flag("F");
	        			infFileAttachVO.setEcms_result("Attach File does not exist" );
	        			commonDAO.modify("common.legacyintf.updateAttachFailInfo", infFileAttachVO);
	        			
	        		}
				}
			} catch (FileUploadException e) {
				throw new BaseException(messageSource.getMessage("secfw.msg.error.fileNonAllow", new String[] { fileInfo }, Locale.getDefault()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			return fileInfoList;
		}
		

		private String getUUID() {
			return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);	 
		}

}