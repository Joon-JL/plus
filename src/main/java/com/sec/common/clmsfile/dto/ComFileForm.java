/**
 * 공통첨부파일 
 * 메일 및 결재 첨부파일 
 * @version 
 * 작성일 : 2011.09.21
 * @author 신남원
 */

package com.sec.common.clmsfile.dto;

import com.sds.secframework.common.dto.CommonForm;

public class ComFileForm extends CommonForm {

	/**********************************************************************
	* DB 구분자
	**********************************************************************/
	/**모듈 식별자 ID*/
	private String module_id;                 
	/**Mis Id*/
	private String mis_id;  
	/**파일일련번호*/
	private String sequence;
	/**수신자일련번호*/
	private String file_name;
	/**수신자일련번호*/
	private String file_path;
	/**첨부파일사이즈 */
	private String file_size;
	
	/**********************************************************************
	* DB외 구분자
	**********************************************************************/
	/**시스템구분(mail, approval)**/
	private String sys_gbn;
	/** 오픈모드 **/
	private String view_gbn;
	/** 파일 삭제 모드 **/
	private String file_del_mode;
	/** 파일 저장구분 **/
	private String file_gbn;
	/** 리턴받을 파일객체 명 **/
	private String fileInfoName;
	/** 파일의 frame 명 **/
	private String fileFrameName;	
	/**********************************************************************
	 * Getters and Setters Method
	 **********************************************************************/

	public String getModule_id() {
		return module_id;
	}

	public String getFileInfoName() {
		return fileInfoName;
	}

	public void setFileInfoName(String fileInfoName) {
		this.fileInfoName = fileInfoName;
	}

	public String getFileFrameName() {
		return fileFrameName;
	}

	public void setFileFrameName(String fileFrameName) {
		this.fileFrameName = fileFrameName;
	}

	public String getFile_gbn() {
		return file_gbn;
	}

	public void setFile_gbn(String file_gbn) {
		this.file_gbn = file_gbn;
	}

	public String getFile_del_mode() {
		return file_del_mode;
	}

	public void setFile_del_mode(String file_del_mode) {
		this.file_del_mode = file_del_mode;
	}

	public String getSys_gbn() {
		return sys_gbn;
	}

	public void setSys_gbn(String sys_gbn) {
		this.sys_gbn = sys_gbn;
	}

	public void setModule_id(String module_id) {
		this.module_id = module_id;
	}

	public String getMis_id() {
		return mis_id;
	}

	public void setMis_id(String mis_id) {
		this.mis_id = mis_id;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getFile_size() {
		return file_size;
	}

	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}

	public String getView_gbn() {
		return view_gbn;
	}

	public void setView_gbn(String view_gbn) {
		this.view_gbn = view_gbn;
	}

}
