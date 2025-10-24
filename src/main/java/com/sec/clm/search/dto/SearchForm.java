package com.sec.clm.search.dto;

import com.sds.secframework.common.dto.CommonForm;

public class SearchForm extends CommonForm {
	
	private String queryText;
	private String srch_start_dt;
	private String srch_end_dt;
	private String srch_index_db;
	private String queryField;
	private String fieldText;
	private String srchMinDate;
	private String srchMaxDate;
	private String cntrt_status;

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
	public void setSrch_index_db(String srch_index_db) {
		this.srch_index_db = srch_index_db;
	}
	public String getSrch_index_db() {
		return srch_index_db;
	}
	public void setQueryField(String queryField) {
		this.queryField = queryField;
	}
	public String getQueryField() {
		return queryField;
	}
	public void setFieldText(String fieldText) {
		this.fieldText = fieldText;
	}
	public String getFieldText() {
		return fieldText;
	}
	public void setSrchMinDate(String srchMinDate) {
		this.srchMinDate = srchMinDate;
	}
	public String getSrchMinDate() {
		return srchMinDate;
	}
	public void setSrchMaxDate(String srchMaxDate) {
		this.srchMaxDate = srchMaxDate;
	}
	public String getSrchMaxDate() {
		return srchMaxDate;
	}
	
}