package com.sds.secframework.file.dto;

import com.sds.secframework.common.dto.CommonVO;

/**
 * 첨부파일 Value Object
 *  
 * @version V1.0
 * 작성일 : 2009/12/18
 * @author 금현서, ace4u_khs@samsung.com
 */

public class AttachFileVO extends CommonVO {

	/**원문일련번호 */
	private String file_ref_no;               
	/**첨부파일일련번호 */
	private String seq_no;                    
	/**첨부파일경로 */
	private String file_path;                 
	/**파일명(사용자파일명) */
	private String file_nm;                   
	/**첨부파일사이즈 */
	private String file_size;                 
	/**파일정보 */
	private String file_info;                 
	/**메뉴아이디 */
	private String menu_id;    
	/**시스템명 */
	private String sys_cd;    
	

	public String getSys_cd() {
		return sys_cd;
	}
	public void setSys_cd(String sys_cd) {
		this.sys_cd = sys_cd;
	}
	public String getFile_ref_no() {
		return file_ref_no;
	}
	public void setFile_ref_no(String file_ref_no) {
		this.file_ref_no = file_ref_no;
	}
	public String getSeq_no() {
		return seq_no;
	}
	public void setSeq_no(String seq_no) {
		this.seq_no = seq_no;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getFile_nm() {
		return file_nm;
	}
	public void setFile_nm(String file_nm) {
		this.file_nm = file_nm;
	}
	public String getFile_size() {
		return file_size;
	}
	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}
	public String getFile_info() {
		return file_info;
	}
	public void setFile_info(String file_info) {
		this.file_info = file_info;
	}
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
}
