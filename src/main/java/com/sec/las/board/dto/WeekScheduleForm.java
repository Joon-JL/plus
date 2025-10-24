package com.sec.las.board.dto;

import com.sds.secframework.common.dto.CommonVO;

public class WeekScheduleForm extends CommonVO {	

	// 호출경로 구분
	private String path_gbn;
	
	public String getPath_gbn() {
		return path_gbn;
	}
	public void setPath_gbn(String path_gbn) {
		this.path_gbn = path_gbn;
	}
	
	/**********************************************
	 * 입력값
	 **********************************************/	
	private String user_id;
	private String year;
	private String weekseq;	
	
	private String week_first_day;
	private String week_last_day;
	
	private String user_nm;	
	private String crntweek_rslt;
	private String nextweek_plan;
	private String reg_dt;
	private String reg_id;
	private String reg_nm;
	private String mod_dt;
	private String mod_id;
	private String mod_nm;

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public void setWeekseq(String weekseq) {
		this.weekseq = weekseq;
	}	
	
	public void setWeekFirstDay(String week_first_day) {
		this.week_first_day = week_first_day;
	}
	
	
	public void setWeekLastDay(String week_last_day) {
		this.week_last_day = week_last_day;
	}	
	public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	}
	public void setCrntweek_rslt(String crntweek_rslt) {
		this.crntweek_rslt = crntweek_rslt;
	}
	public void setNextweek_plan(String nextweek_plan) {
		this.nextweek_plan = nextweek_plan;
	}
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public void setReg_nm(String reg_nm) {
		this.reg_nm = reg_nm;
	}
	public void setMod_dt(String mod_dt) {
		this.mod_dt = mod_dt;
	}
	public void setMod_id(String mod_id) {
		this.mod_id = mod_id;
	}
	public void setMod_nm(String mod_nm) {
		this.mod_nm = mod_nm;
	}
	
	public String getUser_id() {
		return this.user_id;
	}
	public String getYear() {
		return this.year;
	}
	public String getWeekseq() {
		return this.weekseq;
	}	
	public String getWeekFirstDay() {
		return this.week_first_day;
	}
	public String getWeekLastDay() {
		return this.week_last_day;
	}
	public String getUser_nm() {
		return this.user_nm;
	}
	public String getCrntweek_rslt() {
		return this.crntweek_rslt;
	}
	public String getNextweek_plan() {
		return this.nextweek_plan;
	}
	public String getReg_dt() {
		return this.reg_dt;
	}
	public String getReg_id() {
		return this.reg_id;
	}
	public String getReg_nm() {
		return this.reg_nm;
	}
	public String getMod_dt() {
		return this.mod_dt;
	}
	public String getMod_id() {
		return this.mod_id;
	}
	public String getMod_nm() {
		return this.mod_nm;
	}
}