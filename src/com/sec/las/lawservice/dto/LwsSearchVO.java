/**
 * Project Name : 법무시스템 이식
 * File name	: LwsSearchVO.java
 * Description	: 로펌서비스 검색 Data VO(Model)
 * Author		: 박병주
 * Date			: 2011. 10
 * Copyright	:  2011 by SAMSUNG. All rights reserved.  
 */
package com.sec.las.lawservice.dto;

public class LwsSearchVO extends LwsCommonVO {
    // 검색조건 검색타입
	private String srch_type;
    // 검색조건 년도
	private String srch_year;
    // 검색조건 년도 타입
	private String srch_year_type;
    // 검색조건 비용구분
	private String srch_money_type;
	public String getSrch_type() {
		return srch_type;
	}
	public void setSrch_type(String srch_type) {
		this.srch_type = srch_type;
	}
	public String getSrch_year() {
		return srch_year;
	}
	public void setSrch_year(String srch_year) {
		this.srch_year = srch_year;
	}
	public String getSrch_year_type() {
		return srch_year_type;
	}
	public void setSrch_year_type(String srch_year_type) {
		this.srch_year_type = srch_year_type;
	}
	public String getSrch_money_type() {
		return srch_money_type;
	}
	public void setSrch_money_type(String srch_money_type) {
		this.srch_money_type = srch_money_type;
	}		
	
	
}
