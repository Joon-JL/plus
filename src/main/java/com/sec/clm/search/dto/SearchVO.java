package com.sec.clm.search.dto;

import com.sds.secframework.common.dto.CommonVO;

public class SearchVO extends CommonVO {
	
	private String queryText;
	private String srch_start_dt;
	private String srch_end_dt;
	private String dretitle;
	private String drecontent;
	private String dredate;
	private String lf_menu_pos;
	private String if_writer;
	private int total_cnt;	
	private float weight;
	private String srch_index_db;
	private String queryField;
	private String lf_menu_id;
	private String lf_up_menu_id;
	private String link_url;
	private String fieldText;
	private String srchMinDate;
	private String srchMaxDate;
	private String cntrt_status;
	
	private String dretitle2;

	public String getDretitle2() {
		return dretitle2;
	}
	public void setDretitle2(String dretitle2) {
		this.dretitle2 = dretitle2;
	}
	public String getCntrt_status() {
		return cntrt_status;
	}
	public void setCntrt_status(String cntrt_status) {
		this.cntrt_status = cntrt_status;
	}
	public String getQueryText() {
		return queryText;
	}
	public void setQueryText(String queryText) {
		this.queryText = queryText;
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
	public String getDretitle() {
		return dretitle;
	}
	public void setDretitle(String dretitle) {
		this.dretitle = dretitle;
	}
	public String getDredate() {
		return dredate;
	}
	public void setDredate(String dredate) {
		this.dredate = dredate;
	}
	public String getIf_writer() {
		return if_writer;
	}
	public void setIf_writer(String if_writer) {
		this.if_writer = if_writer;
	}
	public int getTotal_cnt() {
		return total_cnt;
	}
	public void setTotal_cnt(int total_cnt) {
		this.total_cnt = total_cnt;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public void setSrch_index_db(String srch_index_db) {
		this.srch_index_db = srch_index_db;
	}
	public String getSrch_index_db() {
		return srch_index_db;
	}
	public void setLf_menu_pos(String lf_menu_pos) {
		this.lf_menu_pos = lf_menu_pos;
	}
	public String getLf_menu_pos() {
		return lf_menu_pos;
	}
	public void setDrecontent(String drecontent) {
		this.drecontent = drecontent;
	}
	public String getDrecontent() {
		return drecontent;
	}
	public void setQueryField(String queryField) {
		this.queryField = queryField;
	}
	public String getQueryField() {
		return queryField;
	}
	public void setLf_menu_id(String lf_menu_id) {
		this.lf_menu_id = lf_menu_id;
	}
	public String getLf_menu_id() {
		return lf_menu_id;
	}
	public void setLf_up_menu_id(String lf_up_menu_id) {
		this.lf_up_menu_id = lf_up_menu_id;
	}
	public String getLf_up_menu_id() {
		return lf_up_menu_id;
	}
	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}
	public String getLink_url() {
		return link_url;
	}
	public void setFieldText(String fieldText) {
		this.fieldText = fieldText;
	}
	public String getFieldText() {
		return fieldText;
	}
	public void setSrchMaxDate(String srchMaxDate) {
		this.srchMaxDate = srchMaxDate;
	}
	public String getSrchMaxDate() {
		return srchMaxDate;
	}
	public void setSrchMinDate(String srchMinDate) {
		this.srchMinDate = srchMinDate;
	}
	public String getSrchMinDate() {
		return srchMinDate;
	}
		
}