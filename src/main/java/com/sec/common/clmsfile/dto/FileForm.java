/**
 * 공통첨부파일 
 * 메일 및 결재 첨부파일 
 * @version 
 * 작성일 : 2011.09.21
 * @author 신남원
 */

package com.sec.common.clmsfile.dto;

import com.sds.secframework.common.dto.CommonForm;

public class FileForm extends CommonForm {

	/**********************************************************************
	* DB외 구분자
	**********************************************************************/
	/**수신자일련번호*/
	private String fileNm;
	/** 업로드될 폴더 **/
	private String folderNm;

	/** 파일유형 **/
	private String fileType;
	
	/**********************************************************************
	 * Getters and Setters Method
	 **********************************************************************/
	
	public String getFileNm() {
		return fileNm;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
	public String getFolderNm() {
		return folderNm;
	}
	public void setFolderNm(String folderNm) {
		this.folderNm = folderNm;
	}
}
