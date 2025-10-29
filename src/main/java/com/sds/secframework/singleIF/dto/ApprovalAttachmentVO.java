package com.sds.secframework.singleIF.dto;

/**
 * Single결재 ESB 연동 결재첨부 Value Object
 *  
 * @version com.sds.secframework.singleIF V1.0
 * 작성일 : 2009/12/18
 * @author 금현서, ace4u_khs@samsung.com
 */
public class ApprovalAttachmentVO {

	/**********************************************************************
	* 결재첨부
	**********************************************************************/
	/**모듈 식별자 ID*/
	private String module_id;                 
	/**Mis ID*/
	private String mis_id;                    
	/**첨부파일 순서*/
	private String sequence;                  
	/**첨부파일명*/
	private String file_name;                 
	/**첨부파일 크기*/
	private String file_size;                 
	/**파일저장 경로*/
	private String store_location;            
	public String getModule_id() {
		return module_id;
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
	public String getFile_size() {
		return file_size;
	}
	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}
	public String getStore_location() {
		return store_location;
	}
	public void setStore_location(String store_location) {
		this.store_location = store_location;
	}
	
}
