/**
* Project Name : CLMS
* File Name : ClmsFileController.java
* Description : 공통 첨부파일 Vo
* Author : 신남원
* Date : 2011. 08. 04
* Copyright : SamSung
*/
package com.sec.common.clmsfile.dto;

import com.sds.secframework.common.dto.CommonVO;

public class ClmsFileVO extends CommonVO {
	/**********************************************************************
	 * 첨부파일
	 **********************************************************************/
	/** 파일ID **/
	private String file_id;
	/** 파일경로 **/
	private String file_path;
	/** 원본파일명 **/
	private String org_file_nm;
	/** 파일순서 **/
	private int file_srt;
	/** 파일크기 **/
	private int file_sz;
	/** 시스템코드 **/
	private String sys_cd;
	/** 대분류 **/
	private String file_bigclsfcn;
	/** 중분류 **/
	private String file_midclsfcn;
	/** 소분류 **/
	private String file_smlclsfcn;
	/** 참조키 **/
	private String ref_key;
	/** 파일정보 **/
	private String file_info;
	/** 등록일자 **/
	private String reg_dt;
	/** 등록자 **/
	private String reg_id;
	/** 삭제여부 **/
	private String del_yn;
	/** 삭제일자 **/
	private String del_dt;
	/** 삭제자 **/
	private String del_id;
	
	/**********************************************************************
	 * DB 외 추가 변수
	 **********************************************************************/
	/** 파일 삭제 모드 **/
	private String file_del_mode;
	/** 파일 저장구분 **/
	private String file_gbn;
	/** 오픈모드 **/
	private String view_gbn;
	/** 리턴받을 파일객체 명 **/
	private String fileInfoName;
	/** 파일의 frame 명 **/
	private String fileFrameName;
	/* Multi 여부*/
	private String multiYn;
	/** 파일생성규칙에 따른 파일명**/
	private String newFileNm;
	/** 파일버전**/
	private String fileVersion;	
	/** 특정 확장자만 별도로 셋팅할 때 */
	private String preAllowFileList;
	/** 임의로 파일버전 말고 final을 주고 싶을 때 */
	private String finalVersion;
	/** 체결본 사본 등록 때 파일뒤에 붙일 내용 */
	private String finalAdd;
	
	/**CPMS IF용 파일사이즈제한 구분 */
	private String size_gbn;
	/**********************************************************************
	 * Getters and Setters Method
	 **********************************************************************/
	
	/** ocr을 위해서 추가한 내용 입니다. */
	private String ocr_work_target;
	
	private String ocr_work_lang;
	
	public String getOcr_work_target() {
		return ocr_work_target;
	}
	public void setOcr_work_target(String ocr_work_target) {
		this.ocr_work_target = ocr_work_target;
	}
	public String getOcr_work_lang() {
		return ocr_work_lang;
	}
	public void setOcr_work_lang(String ocr_work_lang) {
		this.ocr_work_lang = ocr_work_lang;
	}
	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}
	public String getFileVersion() {
		return fileVersion;
	}
	public void setFileVersion(String fileVersion) {
		this.fileVersion = fileVersion;
	}
	public String getNewFileNm() {
		return newFileNm;
	}
	public void setNewFileNm(String newFileNm) {
		this.newFileNm = newFileNm;
	}
	public String getMultiYn() {
		return multiYn;
	}
	public void setMultiYn(String multiYn) {
		this.multiYn = multiYn;
	}
	public String getFileFrameName() {
		return fileFrameName;
	}
	public void setFileFrameName(String fileFrameName) {
		this.fileFrameName = fileFrameName;
	}
	public String getFileInfoName() {
		return fileInfoName;
	}
	public void setFileInfoName(String fileInfoName) {
		this.fileInfoName = fileInfoName;
	}
	public String getFile_del_mode() {
		return file_del_mode;
	}
	public void setFile_del_mode(String file_del_mode) {
		this.file_del_mode = file_del_mode;
	}
	public String getFile_gbn() {
		return file_gbn;
	}
	public void setFile_gbn(String file_gbn) {
		this.file_gbn = file_gbn;
	}
	public String getView_gbn() {
		return view_gbn;
	}
	public void setView_gbn(String view_gbn) {
		this.view_gbn = view_gbn;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public void setOrg_file_nm(String org_file_nm) {
		this.org_file_nm = org_file_nm;
	}
	public void setFile_srt(int file_srt) {
		this.file_srt = file_srt;
	}
	public void setFile_sz(int file_sz) {
		this.file_sz = file_sz;
	}
	public void setSys_cd(String sys_cd) {
		this.sys_cd = sys_cd;
	}
	public void setFile_bigclsfcn(String file_bigclsfcn) {
		this.file_bigclsfcn = file_bigclsfcn;
	}
	public void setFile_midclsfcn(String file_midclsfcn) {
		this.file_midclsfcn = file_midclsfcn;
	}
	public void setFile_smlclsfcn(String file_smlclsfcn) {
		this.file_smlclsfcn = file_smlclsfcn;
	}
	public void setRef_key(String ref_key) {
		this.ref_key = ref_key;
	}
	public void setFile_info(String file_info) {
		this.file_info = file_info;
	}
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	public void setDel_dt(String del_dt) {
		this.del_dt = del_dt;
	}
	public void setDel_id(String del_id) {
		this.del_id = del_id;
	}
	public String getFile_id() {
		return this.file_id;
	}
	public String getFile_path() {
		return this.file_path;
	}
	public String getOrg_file_nm() {
		return this.org_file_nm;
	}
	public int getFile_srt() {
		return this.file_srt;
	}
	public int getFile_sz() {
		return this.file_sz;
	}
	public String getSys_cd() {
		return this.sys_cd;
	}
	public String getFile_bigclsfcn() {
		return this.file_bigclsfcn;
	}
	public String getFile_midclsfcn() {
		return this.file_midclsfcn;
	}
	public String getFile_smlclsfcn() {
		return this.file_smlclsfcn;
	}
	public String getRef_key() {
		return this.ref_key;
	}
	public String getFile_info() {
		return this.file_info;
	}
	public String getReg_dt() {
		return this.reg_dt;
	}
	public String getReg_id() {
		return this.reg_id;
	}
	public String getDel_yn() {
		return this.del_yn;
	}
	public String getDel_dt() {
		return this.del_dt;
	}
	public String getDel_id() {
		return this.del_id;
	}
	public String getPreAllowFileList() {
		return preAllowFileList;
	}
	public void setPreAllowFileList(String preAllowFileList) {
		this.preAllowFileList = preAllowFileList;
	}
	public String getFinalVersion() {
		return finalVersion;
	}
	public void setFinalVersion(String finalVersion) {
		this.finalVersion = finalVersion;
	}
	public String getFinalAdd() {
		return finalAdd;
	}
	public void setFinalAdd(String finalAdd) {
		this.finalAdd = finalAdd;
	}
	public String getSize_gbn() {
		return size_gbn;
	}
	public void setSize_gbn(String size_gbn) {
		this.size_gbn = size_gbn;
	}
	
	
}