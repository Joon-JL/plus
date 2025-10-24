package com.sds.secframework.board.dto;

import org.springframework.web.multipart.MultipartFile;

import com.sds.secframework.common.dto.CommonForm;
import com.sds.secframework.common.util.DateUtil;

public class BoardForm extends CommonForm {
	/************************************************************
	 * 검색 조건
	 ************************************************************/
	/**
	 * 구분
	 */
	private String srch_div_cd;

	/**
	 * 검색 타입(제목, 작성자)
	 */
	private String srch_type;

	/**
	 * 검색어
	 */
	private String srch_word;

	/**
	 * 조회시작날짜
	 */
	private String srch_start_dt = DateUtil.formatDate(DateUtil.today(), "-");

	/**
	 * 조회종료날짜
	 */
	private String srch_end_dt = DateUtil.formatDate(DateUtil.today(), "-");

	/************************************************************
	 * 세팅값
	 ************************************************************/
	/**
	 * 사용자 설정 Row 수
	 */
	private int user_row_cnt;
	
	/**
	 * 이미지 관련
	 */
	MultipartFile img_file ;
	
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

	/************************************************************
	 * 입력값
	 ************************************************************/
	/**
	 * 시스템코드
	 */
	private String sys_cd;

	/**
	 * 게시판ID
	 */
	private String board_id;

	/**
	 * 게시글ID
	 */
	private String notice_id;

	/**
	 * 상위게시글ID
	 */
	private String up_notice_id;

	/**
	 * 게시글레벨
	 */
	private String notice_level;

	/**
	 * 동일레벨순서
	 */
	private int notice_level_order;

	/**
	 * 게시글상태(00:저장(확정), 01:임시저장)
	 */
	private String notice_status_cd;

	/**
	 * 구분코드
	 */
	private String div_cd;

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
	 * 이미지파일ID
	 */
	private String img_file_ref_no;

	/**
	 * 조회수
	 */
	private int ref_cnt;

	/**
	 * 추천수
	 */
	private int recommend_cnt;

	/**
	 * 공개여부
	 */
	private String open_yn;

	/**
	 * 보안여부
	 */
	private String security_yn;

	/**
	 * 보안비빌번호
	 */
	private String password;

	/**
	 * 익명여부
	 */
	private String anonymity_yn;

	/**
	 * 익명
	 */
	private String anonymity_nm;

	/**
	 * 유효일자여부
	 */
	private String hold_day_yn;

	/**
	 * 유효일시작일자
	 */
	private String hold_start_ymd;

	/**
	 * 유효일종료일자
	 */
	private String hold_end_ymd;

	/**
	 * 긴급게시여부
	 */
	private String urgency_yn;

	/**
	 * 긴급게시시작일자
	 */
	private String urgency_start_ymd;

	/**
	 * 긴급게시종료일자
	 */
	private String urgency_end_ymd;

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

	public String getSrch_div_cd() {
		return srch_div_cd;
	}

	public void setSrch_div_cd(String srch_div_cd) {
		this.srch_div_cd = srch_div_cd;
	}

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

	public String getSrch_start_dt() {
		return srch_start_dt;
	}

	public void setSrch_start_dt(String srch_start_dt) {
		this.srch_start_dt = srch_start_dt;
	}

	public String getSrch_end_dt() {
		return srch_end_dt;
	}

	public void setSrch_end_dt(String srch_end_dt) {
		this.srch_end_dt = srch_end_dt;
	}

	public int getUser_row_cnt() {
		return user_row_cnt;
	}

	public void setUser_row_cnt(int user_row_cnt) {
		this.user_row_cnt = user_row_cnt;
	}

	public String getSys_cd() {
		return sys_cd;
	}

	public void setSys_cd(String sys_cd) {
		this.sys_cd = sys_cd;
	}

	public String getBoard_id() {
		return board_id;
	}

	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}

	public String getNotice_id() {
		return notice_id;
	}

	public void setNotice_id(String notice_id) {
		this.notice_id = notice_id;
	}

	public String getUp_notice_id() {
		return up_notice_id;
	}

	public void setUp_notice_id(String up_notice_id) {
		this.up_notice_id = up_notice_id;
	}

	public String getNotice_level() {
		return notice_level;
	}

	public void setNotice_level(String notice_level) {
		this.notice_level = notice_level;
	}

	public int getNotice_level_order() {
		return notice_level_order;
	}

	public void setNotice_level_order(int notice_level_order) {
		this.notice_level_order = notice_level_order;
	}

	public String getNotice_status_cd() {
		return notice_status_cd;
	}

	public void setNotice_status_cd(String notice_status_cd) {
		this.notice_status_cd = notice_status_cd;
	}

	public String getDiv_cd() {
		return div_cd;
	}

	public void setDiv_cd(String div_cd) {
		this.div_cd = div_cd;
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

	public String getFile_ref_no() {
		return file_ref_no;
	}

	public void setFile_ref_no(String file_ref_no) {
		this.file_ref_no = file_ref_no;
	}

	public String getImg_file_ref_no() {
		return img_file_ref_no;
	}

	public void setImg_file_ref_no(String img_file_ref_no) {
		this.img_file_ref_no = img_file_ref_no;
	}

	public int getRef_cnt() {
		return ref_cnt;
	}

	public void setRef_cnt(int ref_cnt) {
		this.ref_cnt = ref_cnt;
	}

	public int getRecommend_cnt() {
		return recommend_cnt;
	}

	public void setRecommend_cnt(int recommend_cnt) {
		this.recommend_cnt = recommend_cnt;
	}

	public String getOpen_yn() {
		return open_yn;
	}

	public void setOpen_yn(String open_yn) {
		this.open_yn = open_yn;
	}

	public String getSecurity_yn() {
		return security_yn;
	}

	public void setSecurity_yn(String security_yn) {
		this.security_yn = security_yn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAnonymity_yn() {
		return anonymity_yn;
	}

	public void setAnonymity_yn(String anonymity_yn) {
		this.anonymity_yn = anonymity_yn;
	}

	public String getAnonymity_nm() {
		return anonymity_nm;
	}

	public void setAnonymity_nm(String anonymity_nm) {
		this.anonymity_nm = anonymity_nm;
	}

	public String getHold_day_yn() {
		return hold_day_yn;
	}

	public void setHold_day_yn(String hold_day_yn) {
		this.hold_day_yn = hold_day_yn;
	}

	public String getHold_start_ymd() {
		return hold_start_ymd;
	}

	public void setHold_start_ymd(String hold_start_ymd) {
		this.hold_start_ymd = hold_start_ymd;
	}

	public String getHold_end_ymd() {
		return hold_end_ymd;
	}

	public void setHold_end_ymd(String hold_end_ymd) {
		this.hold_end_ymd = hold_end_ymd;
	}

	public String getUrgency_yn() {
		return urgency_yn;
	}

	public void setUrgency_yn(String urgency_yn) {
		this.urgency_yn = urgency_yn;
	}

	public String getUrgency_start_ymd() {
		return urgency_start_ymd;
	}

	public void setUrgency_start_ymd(String urgency_start_ymd) {
		this.urgency_start_ymd = urgency_start_ymd;
	}

	public String getUrgency_end_ymd() {
		return urgency_end_ymd;
	}

	public void setUrgency_end_ymd(String urgency_end_ymd) {
		this.urgency_end_ymd = urgency_end_ymd;
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

	public MultipartFile getImg_file() {
		return img_file;
	}

	public void setImg_file(MultipartFile img_file) {
		this.img_file = img_file;
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
	
	

}
