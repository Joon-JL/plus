package com.sds.secframework.board.dto;

import com.sds.secframework.common.dto.CommonVO;

public class BoardMngVO extends CommonVO {
	/************************************************************
	 * 검색 조건
	 ************************************************************/
	/**
	 * 검색 사용여부
	 */
	private String srch_use_yn;

	/**
	 * 검색 게시판명
	 */
	private String srch_board_nm;

	/************************************************************
	 * 게시판 마스터 입력 정보
	 ************************************************************/
	/**
	 * 게시판 설명
	 */
	private String comments;

	/**
	 * 게시판 타입
	 */
	private String board_type;

	/**
	 * 기본페이장 Row 수
	 */
	private int default_row_cnt = 10;

	/**
	 * 페이지당 Row
	 */
	private String row_cnt_yn = "N";

	/**
	 * 페이지당 Row 수
	 */
	private String row_cnt = "10,20,30,40,50"; // 10,20,30.. 처럼 , 로 구분

	/**
	 * 새글유지일수
	 */
	private int new_hold_day = 7;

	/**
	 * 기본유효일수
	 */
	private int default_hold_day;

	/**
	 * 유효일수
	 */
	private String hold_day_yn = "N";

	/**
	 * 이미지등록 여부
	 */
	private String img_yn = "N";

	/**
	 * 첨부파일 여부
	 */
	private String file_yn = "Y";

	/**
	 * 최대첨부파일수
	 */
	private int max_file_cnt = 0;

	/**
	 * 최대첨부파일용량
	 */
	private int max_file_size = 10;

	/**
	 * 구분여부
	 */
	private String div_yn = "N";

	/**
	 * 구분코드
	 */
	private String div_cd;

	/**
	 * 추천여부
	 */
	private String recommend_yn = "N";

	/**
	 * 댓글추천여부
	 */
	private String append_recommend_yn = "N";

	/**
	 * 답변여부
	 */
	private String reply_yn = "N";

	/**
	 * 댓글여부
	 */
	private String append_yn = "N";

	/**
	 * 공개여부
	 */
	private String open_yn = "N";

	/**
	 * 보안여부
	 */
	private String security_yn = "N";

	/**
	 * 익명여부
	 */
	private String anonymity_yn = "N";

	/**
	 * 사용여부
	 */
	private String use_yn = "Y";

	/**
	 * 메일전송여부
	 */
	private String send_mail_yn = "N";

	/**
	 * 전송메일주소
	 */
	private String send_mail_addr;

	/**
	 * 임시저장여부
	 */
	private String temp_save_yn = "N";

	/**
	 * 긴급게시물설정 여부
	 */
	private String urgency_yn = "N";

	/**
	 * 긴급게시일수
	 */
	private int urgency_day;

	/**
	 * 조회권한설정 여부
	 */
	private String read_user_yn = "N";

	/**
	 * 삭제여부
	 */
	private String del_yn = "N";

	/************************************************************
	 * 게시판 title 입력정보
	 ************************************************************/
	/**
	 * 게시판명 Array
	 */
	private String[] board_nm_array;

	/**
	 * 게시판명
	 */
	private String board_nm;

	/**
	 * 언어타입 Array
	 */
	private String[] language_type_array;

	/**
	 * 언어타입
	 */
	private String language_type;

	/************************************************************
	 * 공통 정보
	 ************************************************************/
	/**
	 * 기본지역
	 */
	private String defaultLocale;
	/**
	 * 시스템코드
	 */
	private String sys_cd;

	/**
	 * 게시판ID
	 */
	private String board_id;

	/**
	 * 등록자 싱글 EPID
	 */
	private String reg_id;

	/**
	 * 등록자명
	 */
	private String reg_nm;

	/**
	 * 등록자 직급
	 */
	private String reg_jikgup_nm;

	/**
	 * 등록작 부서
	 */
	private String reg_dept_nm;

	/**
	 * 등록일시
	 */
	private String reg_dt;

	/**
	 * 수정자 싱글 EPID
	 */
	private String mod_id;

	/**
	 * 수정자명
	 */
	private String mod_nm;

	/**
	 * 수정자 직급
	 */
	private String mod_jikgup_nm;

	/**
	 * 수정자 부서
	 */
	private String mod_dept_nm;

	/**
	 * 수정일시
	 */
	private String mod_dt;

	public String getDefaultLocale() {
		return defaultLocale;
	}

	public void setDefaultLocale(String defaultLocale) {
		this.defaultLocale = defaultLocale;
	}

	public String getSrch_use_yn() {
		return srch_use_yn;
	}

	public void setSrch_use_yn(String srch_use_yn) {
		this.srch_use_yn = srch_use_yn;
	}

	public String getSrch_board_nm() {
		return srch_board_nm;
	}

	public void setSrch_board_nm(String srch_board_nm) {
		this.srch_board_nm = srch_board_nm;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getBoard_type() {
		return board_type;
	}

	public void setBoard_type(String board_type) {
		this.board_type = board_type;
	}

	public int getDefault_row_cnt() {
		return default_row_cnt;
	}

	public void setDefault_row_cnt(int default_row_cnt) {
		this.default_row_cnt = default_row_cnt;
	}

	public String getRow_cnt_yn() {
		return row_cnt_yn;
	}

	public void setRow_cnt_yn(String row_cnt_yn) {
		this.row_cnt_yn = row_cnt_yn;
	}

	public String getRow_cnt() {
		return row_cnt;
	}

	public void setRow_cnt(String row_cnt) {
		this.row_cnt = row_cnt;
	}

	public int getNew_hold_day() {
		return new_hold_day;
	}

	public void setNew_hold_day(int new_hold_day) {
		this.new_hold_day = new_hold_day;
	}

	public int getDefault_hold_day() {
		return default_hold_day;
	}

	public void setDefault_hold_day(int default_hold_day) {
		this.default_hold_day = default_hold_day;
	}

	public String getHold_day_yn() {
		return hold_day_yn;
	}

	public void setHold_day_yn(String hold_day_yn) {
		this.hold_day_yn = hold_day_yn;
	}

	public String getImg_yn() {
		return img_yn;
	}

	public void setImg_yn(String img_yn) {
		this.img_yn = img_yn;
	}

	public String getFile_yn() {
		return file_yn;
	}

	public void setFile_yn(String file_yn) {
		this.file_yn = file_yn;
	}

	public int getMax_file_cnt() {
		return max_file_cnt;
	}

	public void setMax_file_cnt(int max_file_cnt) {
		this.max_file_cnt = max_file_cnt;
	}

	public int getMax_file_size() {
		return max_file_size;
	}

	public void setMax_file_size(int max_file_size) {
		this.max_file_size = max_file_size;
	}

	public String getDiv_yn() {
		return div_yn;
	}

	public void setDiv_yn(String div_yn) {
		this.div_yn = div_yn;
	}

	public String getDiv_cd() {
		return div_cd;
	}

	public void setDiv_cd(String div_cd) {
		this.div_cd = div_cd;
	}

	public String getRecommend_yn() {
		return recommend_yn;
	}

	public void setRecommend_yn(String recommend_yn) {
		this.recommend_yn = recommend_yn;
	}

	public String getAppend_recommend_yn() {
		return append_recommend_yn;
	}

	public void setAppend_recommend_yn(String append_recommend_yn) {
		this.append_recommend_yn = append_recommend_yn;
	}

	public String getReply_yn() {
		return reply_yn;
	}

	public void setReply_yn(String reply_yn) {
		this.reply_yn = reply_yn;
	}

	public String getAppend_yn() {
		return append_yn;
	}

	public void setAppend_yn(String append_yn) {
		this.append_yn = append_yn;
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

	public String getAnonymity_yn() {
		return anonymity_yn;
	}

	public void setAnonymity_yn(String anonymity_yn) {
		this.anonymity_yn = anonymity_yn;
	}

	public String getUse_yn() {
		return use_yn;
	}

	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}

	public String getSend_mail_yn() {
		return send_mail_yn;
	}

	public void setSend_mail_yn(String send_mail_yn) {
		this.send_mail_yn = send_mail_yn;
	}

	public String getSend_mail_addr() {
		return send_mail_addr;
	}

	public void setSend_mail_addr(String send_mail_addr) {
		this.send_mail_addr = send_mail_addr;
	}

	public String getTemp_save_yn() {
		return temp_save_yn;
	}

	public void setTemp_save_yn(String temp_save_yn) {
		this.temp_save_yn = temp_save_yn;
	}

	public String getUrgency_yn() {
		return urgency_yn;
	}

	public void setUrgency_yn(String urgency_yn) {
		this.urgency_yn = urgency_yn;
	}

	public int getUrgency_day() {
		return urgency_day;
	}

	public void setUrgency_day(int urgency_day) {
		this.urgency_day = urgency_day;
	}

	public String getRead_user_yn() {
		return read_user_yn;
	}

	public void setRead_user_yn(String read_user_yn) {
		this.read_user_yn = read_user_yn;
	}

	public String getDel_yn() {
		return del_yn;
	}

	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}

	public String[] getBoard_nm_array() {
		return board_nm_array;
	}

	public void setBoard_nm_array(String[] board_nm_array) {
		this.board_nm_array = board_nm_array;
	}

	public String getBoard_nm() {
		return board_nm;
	}

	public void setBoard_nm(String board_nm) {
		this.board_nm = board_nm;
	}

	public String[] getLanguage_type_array() {
		return language_type_array;
	}

	public void setLanguage_type_array(String[] language_type_array) {
		this.language_type_array = language_type_array;
	}

	public String getLanguage_type() {
		return language_type;
	}

	public void setLanguage_type(String language_type) {
		this.language_type = language_type;
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
