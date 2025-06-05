package com.sds.secframework.dext.service.impl;

import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

import anyframe.common.exception.BaseException;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.dext.service.DextUploadService;

import devpia.dextupload.DEXTUploadException;
import devpia.dextupload.FileItem;
import devpia.dextupload.FileUpload;

/**
 * @author Administrator
 *
 */
public class DextUploadServiceImpl extends CommonServiceImpl implements DextUploadService {
	
	public String dextFileUpload(FileUpload fileUpload) throws Exception {
		String fileInfos = "";
		
		try {
				String strPath = propertyService.getProperty("secfw.dextupload.config.path"); 
				String serverPath = propertyService.getProperty("secfw.dextupload.serverDir"); 
				String allowInfo = propertyService.getProperty("clms.uploadify.allowFileList");
				
				// 정품 혹은 평가판의 만료일을 판단하기 위한 라이센스 파일의 위치를 지정합니다.
				fileUpload.setLicenseFilePath(strPath);			
				fileUpload.setCharacterEncoding("UTF-8");
				fileUpload.setAutoMakeFolder(true);
				fileUpload.UploadStart(serverPath);

				// DEXTUploadFL 2 컴포넌트와 연동할 경우 파일 아이템은 모두 "DEXTUploadFL_FileData" 이름으로 전송된다. 
				FileItem[] value = fileUpload.getFileItemValues("DEXTUploadFL_FileData");
				if (value != null && value.length == 1) 
				{
					if(value[0].IsUploaded()) 
					{
						String fileName = value[0].getFileName();
						String extName = fileName.lastIndexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase() : ""; 
						String fileId = DateUtil.getShortDate(new Date()) + "_" + Long.toString(System.nanoTime());
						String subDir = DateUtil.getDate(new Date()).substring(0, 6);
						String saveDir = serverPath + "/" + subDir;
						
						if (checkFileInfo(allowInfo, extName)) {
							String saveName = fileId + "." + extName;
							value[0].SaveAs(saveDir + "/" + saveName, true);
						} else {
							throw new BaseException(
									messageSource.getMessage("secfw.msg.error.fileNonAllow", new String[] { extName }, Locale.getDefault()));
						}
					
						fileInfos = " " + "*" + " " + "*" + " " + "*"
						+ value[0].getFileName() + "*" + value[0].getLastSavedFilePath() + "*" + value[0].length()
						+ "*" + "add" + "|";
					}
				}
		} catch (DEXTUploadException ex) {
			// 예외에 대한 처리를 수행한다. 
			ex.printStackTrace();
		} catch (Exception ex) {
			// 예외에 대한 처리를 수행한다. 
			ex.printStackTrace();
		} finally {
			// 종료 시에 반드시 자원을 해제해야 한다.
			// 그렇지 않으면 임시 파일이 삭제되지 않고 남을 수 있다.
			fileUpload.dispose();
		}
		
		return fileInfos;
	}
	
	
	/**
	 * ClmsFile : 업로드 가능한 파일 확장자 인지 체크 한다.
	 * @param 업로드 시도한 파일의 확장자
	 * @return true : 허용된 파일형식이 있음, false : 허용된 파일 형식이 없음
	 * @throws Exception
	 */
	private boolean checkFileInfo(String allowInfo,  String fileInfo ) throws Exception {
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
}
