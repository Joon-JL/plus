package com.sec.clm.manage.dto;

import com.sds.secframework.common.dto.CommonForm;

public class MyNoticeForm extends CommonForm {
	
	private String module_id;
	private String mis_id;
	private String msg_key;
	private String subject;
	private String body;
	private String msg_type;
	private String bhtml_content_check;
	private String ifile_count;
	private String time_zone;
	private String is_dst;
	private String sender_mail_addr;
	private String sender_single_id;
	private String status;
	private String create_date;
    private String name;
	private String start_index;
	private String end_index;
	private String curPage;

	
	private String srch_start_reg_dt;
	private String srch_end_reg_dt;
	
	private String srch_keyword_mailtitle;
	private String srch_Addressee;
	private String srch_stat;
	private String srch_step;
	private String srch_name;

	
	public void setModule_id(String module_id) {
		this.module_id = module_id;
	}
	public void setMis_id(String mis_id) {
		this.mis_id = mis_id;
	}
	public void setMsg_key(String msg_key) {
		this.msg_key = msg_key;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}
	public void setBhtml_content_check(String bhtml_content_check) {
		this.bhtml_content_check = bhtml_content_check;
	}
	public void setIfile_count(String ifile_count) {
		this.ifile_count = ifile_count;
	}
	public void setTime_zone(String time_zone) {
		this.time_zone = time_zone;
	}
	public void setIs_dst(String is_dst) {
		this.is_dst = is_dst;
	}
	public void setSender_mail_addr(String sender_mail_addr) {
		this.sender_mail_addr = sender_mail_addr;
	}
	public void setSender_single_id(String sender_single_id) {
		this.sender_single_id = sender_single_id;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public void setStart_index(String start_index) {
		this.start_index = start_index;
	}
	public void setEnd_index(String end_index) {
		this.end_index = end_index;
	}
	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}
	public String getModule_id() {
		return this.module_id;
	}
	public String getMis_id() {
		return this.mis_id;
	}
	public String getMsg_key() {
		return this.msg_key;
	}
	public String getSubject() {
		return this.subject;
	}
	public String getBody() {
		return this.body;
	}
	public String getMsg_type() {
		return this.msg_type;
	}
	public String getBhtml_content_check() {
		return this.bhtml_content_check;
	}
	public String getIfile_count() {
		return this.ifile_count;
	}
	public String getTime_zone() {
		return this.time_zone;
	}
	public String getIs_dst() {
		return this.is_dst;
	}
	public String getSender_mail_addr() {
		return this.sender_mail_addr;
	}
	public String getSender_single_id() {
		return this.sender_single_id;
	}
	public String getStatus() {
		return this.status;
	}
	public String getCreate_date() {
		return this.create_date;
	}
	public String getStart_index() {
		return this.start_index;
	}
	public String getEnd_index() {
		return this.end_index;
	}
	public String getCurPage() {
		return this.curPage;
	}
	public String getSrch_start_reg_dt() {
		return srch_start_reg_dt;
	}
	public void setSrch_start_reg_dt(String srch_start_reg_dt) {
		this.srch_start_reg_dt = srch_start_reg_dt;
	}
	public String getSrch_end_reg_dt() {
		return srch_end_reg_dt;
	}
	public void setSrch_end_reg_dt(String srch_end_reg_dt) {
		this.srch_end_reg_dt = srch_end_reg_dt;
	}
	public String getSrch_keyword_mailtitle() {
		return srch_keyword_mailtitle;
	}
	public void setSrch_keyword_mailtitle(String srch_keyword_mailtitle) {
		this.srch_keyword_mailtitle = srch_keyword_mailtitle;
	}
	public String getSrch_Addressee() {
		return srch_Addressee;
	}
	public void setSrch_Addressee(String srch_Addressee) {
		this.srch_Addressee = srch_Addressee;
	}
	public String getSrch_stat() {
		return srch_stat;
	}
	public void setSrch_stat(String srch_stat) {
		this.srch_stat = srch_stat;
	}
	public String getSrch_step() {
		return srch_step;
	}
	public void setSrch_step(String srch_step) {
		this.srch_step = srch_step;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSrch_name() {
		return srch_name;
	}
	public void setSrch_name(String srch_name) {
		this.srch_name = srch_name;
	}
	
	
}