package com.sds.secframework.log.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sds.secframework.common.dto.CommonVO;
import com.sds.secframework.common.util.DateUtil;

/**
 * 로그정보 Value Object
 * 
 * @version V1.0 작성일 : 2009/12/18
 * @author 금현서, ace4u_khs@samsung.com
 */

public class LogVO extends CommonVO {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date curDate = new Date();

	/** 사용자아이디 */
	private String user_id;
	/** 사용자명 */
	private String user_nm;
	/** 부서명 */
	private String dept_nm;
	/** 접속년 */
	private String year;
	/** 접속월 */
	private String month;
	/** 접속일 */
	private String day;
	/** 접속시간 */
	private String hour;
	/** 접속일시 */
	private String login_dt;
	/** 로그아웃일시 */
	private String logout_dt;
	/** 아이피주소 */
	private String ip_address;
	/** 브라우져타입 */
	private String browser_type;

	/** 메뉴아이디 */
	private String menu_id;
	/** 메뉴명 */
	private String menu_nm;
	/** 접속시각 */
	private String contact_dt;

	/** EPFTP 첨부파일 문자열 */
	private String file_info_str;
	/** 파일경로(경로+서버저장파일명) */
	private String file_path;
	/** 파일명(사용자파일명) */
	private String file_nm;
	/** 사용구분[파일->업로드:U,다운로드:D,삭제:X] */
	private String use_gubun;

	/** 검색 시작 날짜 */
	private String start_date = sdf.format(curDate);
	/** 검색 마지막 날짜 */
	private String end_date = sdf.format(curDate);
	/** 검색 시작 시간 **/
	private String start_time = "00:00";
	/** 검색 종료 시간 **/
	private String end_time = DateUtil.getTime("HH") + ":00";
	/** 검색 시작 날짜시간 **/
	private String start_datetime = start_date + " " + start_time;
	/** 검색 종료 날짜시간 **/
	private String end_datetime = end_date + " " + end_time;
	/** 검색 내용 */
	private String search_name;
	/** 검색 콤보 */
	private String search_combo;
	/** 검색 시작일자 */
	private String srch_start_dt = sdf.format(curDate);
	/** 검색 종료일자 */
	private String srch_end_dt = sdf.format(curDate);
	/** SESSION ID */
	private String session_id;

	/** 엑셀로 다운받을 테이블 **/
	private String table_nm;
	
	/**회사코드(사별메뉴권한의 조건으로 사용)*/
	private String auth_comp_cd;

	/**********************************************************************
	 * 챠트
	 **********************************************************************/
	/** 챠트구간 */
	private int range_date;

	public String getUser_nm() {
		return user_nm;
	}

	public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	}

	public String getDept_nm() {
		return dept_nm;
	}

	public void setDept_nm(String dept_nm) {
		this.dept_nm = dept_nm;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getLogin_dt() {
		return login_dt;
	}

	public void setLogin_dt(String login_dt) {
		this.login_dt = login_dt;
	}

	public String getLogout_dt() {
		return logout_dt;
	}

	public void setLogout_dt(String logout_dt) {
		this.logout_dt = logout_dt;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	public String getBrowser_type() {
		return browser_type;
	}

	public void setBrowser_type(String browser_type) {
		this.browser_type = browser_type;
	}

	public String getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}

	public String getMenu_nm() {
		return menu_nm;
	}

	public void setMenu_nm(String menu_nm) {
		this.menu_nm = menu_nm;
	}

	public String getContact_dt() {
		return contact_dt;
	}

	public void setContact_dt(String contact_dt) {
		this.contact_dt = contact_dt;
	}

	public String getFile_info_str() {
		return file_info_str;
	}

	public void setFile_info_str(String file_info_str) {
		this.file_info_str = file_info_str;
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

	public String getUse_gubun() {
		return use_gubun;
	}

	public void setUse_gubun(String use_gubun) {
		this.use_gubun = use_gubun;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getSearch_name() {
		return search_name;
	}

	public void setSearch_name(String search_name) {
		this.search_name = search_name;
	}

	public String getSearch_combo() {
		return search_combo;
	}

	public void setSearch_combo(String search_combo) {
		this.search_combo = search_combo;
	}

	public int getRange_date() {
		return range_date;
	}

	public void setRange_date(int range_date) {
		this.range_date = range_date;
	}

	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	public Date getCurDate() {
		return curDate;
	}

	public void setCurDate(Date curDate) {
		this.curDate = curDate;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public String getTable_nm() {
		return table_nm;
	}

	public void setTable_nm(String table_nm) {
		this.table_nm = table_nm;
	}
	
	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getStart_datetime() {
		return start_datetime;
	}

	public void setStart_datetime(String start_datetime) {
		this.start_datetime = start_datetime;
	}

	public String getEnd_datetime() {
		return end_datetime;
	}

	public void setEnd_datetime(String end_datetime) {
		this.end_datetime = end_datetime;
	}

	public String getAuth_comp_cd() {
		return auth_comp_cd;
	}

	public void setAuth_comp_cd(String auth_comp_cd) {
		this.auth_comp_cd = auth_comp_cd;
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
}
