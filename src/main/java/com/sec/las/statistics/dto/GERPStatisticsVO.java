package com.sec.las.statistics.dto;

import com.sds.secframework.common.dto.CommonVO;

public class GERPStatisticsVO extends CommonVO {
	
	private String type = null;
	public void setType(String type){
		this.type = type;
	}
	public String getType(){
		return this.type;
	}
	
	private String targetYear = null;
	public void setTargetYear(String targetYear){
		this.targetYear = targetYear;
	}
	public String getTargetYear(){
		return this.targetYear;
	}
	
	private String compCd = null;
	public void setCompCd(String compCd){
		this.compCd = compCd;
	}
	public String getCompCd(){
		return this.compCd;
	}
	
	private String vendorType = null;
	public void setVendorType(String venderType){
		this.vendorType = venderType;
	}
	public String getVendorType(){
		return this.vendorType;
	}
}
