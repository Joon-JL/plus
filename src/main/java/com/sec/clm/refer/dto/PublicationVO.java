package com.sec.clm.refer.dto;

import com.sds.secframework.common.dto.CommonVO;
import com.sds.secframework.common.util.DateUtil;

public class PublicationVO extends CommonVO {
	/**********************************************
	 * 검색 조건
	 **********************************************/

	/**
	 * 검색 타입(제목, 작성자)
	 */
	private String srch_type;

	/**
	 * 검색어
	 */
	private String srch_word;

	/**
	 * 조회시작일자
	 */
	private String srch_start_ymd;

	/**
	 * 조회종료일자
	 */
	private String srch_end_ymd;
	
	/**********************************************
	 * 첨부파일
	 **********************************************/
	/**
	 * 첨부파일ID
	 */
	private String file_ref_no;
	/**
	 * 첨부파일 처리 구분값
	 */
	private String view_gbn;

	/**
	 * 첨부파일정보
	 */
	private String fileInfos;

	
	/**********************************************
	 * 입력값
	 **********************************************/
	/**
	 * 시스템 코드
	 */
	private String sys_cd  ;
	
	/**
	 * 게시글ID
	 */
	private String notice_id;

	/**
	 * 제목
	 */
	private String title;

	/**
	 * 내용
	 */
	private String contents;
	
	/**
	 * 내용타입(H:HTML, T:TEXT)
	 */
	private String contents_type;

	/**
	 * 조회수
	 */
	private int ref_cnt;
	
	/**
	 * 새글유지일수
	 */
	private int new_hold_day;

	/**
	 * 삭제여부
	 */
	private String del_yn;

	/**
	 * 등록자 EPID
	 */
	private String reg_id;

	/**
	 * 등록자명
	 */
	private String reg_nm;

	/**
	 * 등록자직급
	 */
	private String reg_jikgup_nm;

	/**
	 * 등록자부서
	 */
	private String reg_dept_nm;

	/**
	 * 등록일시
	 */
	private String reg_dt;

	/**
	 * 수정자 EPID
	 */
	private String mod_id;

	/**
	 * 수정자명
	 */
	private String mod_nm;

	/**
	 * 수정자직급
	 */
	private String mod_jikgup_nm;

	/**
	 * 수정자부서
	 */
	private String mod_dept_nm;

	/**
	 * 수정일시
	 */
	private String mod_dt;
	
	/**
	 * 생성자
	 * 
	 * @throws Exception
	 */
	public PublicationVO() throws Exception {
		srch_start_ymd = DateUtil.formatDate(DateUtil.addDays(DateUtil.today(), -365), "-");
		srch_end_ymd = DateUtil.formatDate(DateUtil.today(), "-");

		new_hold_day = 7 ;
		del_yn = "N";
		contents_type = "H";
	}
	
	/**********************************************
	 * getter, setter 메소드
	 **********************************************/
	public String getSrch_type() {
		return srch_type;
	}

	public void setSrch_type(String srch_type) {
		this.srch_type = srch_type;
	}

	public String getSrch_word() {
		return srch_word;
	}

	public void setSrch_word(String srch_word) {
		this.srch_word = srch_word;
	}

	public String getSrch_start_ymd() {
		return srch_start_ymd;
	}

	public void setSrch_start_ymd(String srch_start_ymd) {
		this.srch_start_ymd = srch_start_ymd;
	}

	public String getSrch_end_ymd() {
		return srch_end_ymd;
	}

	public String getFile_ref_no() {
		return file_ref_no;
	}

	public void setFile_ref_no(String file_ref_no) {
		this.file_ref_no = file_ref_no;
	}

	public String getView_gbn() {
		return view_gbn;
	}

	public void setView_gbn(String view_gbn) {
		this.view_gbn = view_gbn;
	}

	public String getFileInfos() {
		return fileInfos;
	}

	public void setFileInfos(String fileInfos) {
		this.fileInfos = fileInfos;
	}

	public void setSrch_end_ymd(String srch_end_ymd) {
		this.srch_end_ymd = srch_end_ymd;
	}
	
	public String getSys_cd() {
		return sys_cd;
	}

	public void setSys_cd(String sys_cd) {
		this.sys_cd = sys_cd;
	}

	public String getNotice_id() {
		return notice_id;
	}

	public void setNotice_id(String notice_id) {
		this.notice_id = notice_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getContents_type() {
		return contents_type;
	}

	public void setContents_type(String contents_type) {
		this.contents_type = contents_type;
	}

	public int getRef_cnt() {
		return ref_cnt;
	}

	public void setRef_cnt(int ref_cnt) {
		this.ref_cnt = ref_cnt;
	}
	
	public int getNew_hold_day() {
		return new_hold_day;
	}

	public void setNew_hold_day(int new_hold_day) {
		this.new_hold_day = new_hold_day;
	}

	public String getDel_yn() {
		return del_yn;
	}

	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}

	public String getReg_id() {
		return reg_id;
	}

	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}

	public String getReg_nm() {
		return reg_nm;
	}

	public void setReg_nm(String reg_nm) {
		this.reg_nm = reg_nm;
	}

	public String getReg_jikgup_nm() {
		return reg_jikgup_nm;
	}

	public void setReg_jikgup_nm(String reg_jikgup_nm) {
		this.reg_jikgup_nm = reg_jikgup_nm;
	}

	public String getReg_dept_nm() {
		return reg_dept_nm;
	}

	public void setReg_dept_nm(String reg_dept_nm) {
		this.reg_dept_nm = reg_dept_nm;
	}

	public String getReg_dt() {
		return reg_dt;
	}

	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}

	public String getMod_id() {
		return mod_id;
	}

	public void setMod_id(String mod_id) {
		this.mod_id = mod_id;
	}

	public String getMod_nm() {
		return mod_nm;
	}

	public void setMod_nm(String mod_nm) {
		this.mod_nm = mod_nm;
	}

	public String getMod_jikgup_nm() {
		return mod_jikgup_nm;
	}

	public void setMod_jikgup_nm(String mod_jikgup_nm) {
		this.mod_jikgup_nm = mod_jikgup_nm;
	}

	public String getMod_dept_nm() {
		return mod_dept_nm;
	}

	public void setMod_dept_nm(String mod_dept_nm) {
		this.mod_dept_nm = mod_dept_nm;
	}

	public String getMod_dt() {
		return mod_dt;
	}

	public void setMod_dt(String mod_dt) {
		this.mod_dt = mod_dt;
	}

}
