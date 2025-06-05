package com.sec.clm.manage.dto;

import com.sds.secframework.common.dto.CommonVO;

/*
 * GERP VO
 *  2014-04-04 Kevin created.
 */
public class GERPInfoVO extends CommonVO {
	
	// Contract ID
	private String cntrtid = null;
	public void setCntrtID(String cntrtID){
		this.cntrtid = cntrtID;
	}
	public String getCntrtID(){
		return this.cntrtid;
	}
	
	// GERP Code Type ie) Cost type, Contract type, Division list
	private String codeType = null;
	public void setGERPCodeType(String codeType){
		this.codeType = codeType;
	}
	public String getGERPCodeType(){
		return this.codeType;
	}
	
	// Comp code
	private String compCd = null;
	public void setCompCd(String compCd){
		this.compCd = compCd;
	}
	public String getCompCd(){
		return this.compCd;
	}
	
}
