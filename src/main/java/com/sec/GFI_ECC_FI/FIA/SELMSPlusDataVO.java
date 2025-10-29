package com.sec.GFI_ECC_FI.FIA;

import com.sds.secframework.common.dto.CommonVO;
import java.util.HashMap;

public class SELMSPlusDataVO extends CommonVO {
	
	private String targetDate = null;
	private int targetHour = 0;
	private int targetInterval = 0;
	private String cnsdReqID = null;
	private HashMap<String, String> targetCompCdList = null;
	private String targetCompCd = null;
	
	public void setTargetDate(String targetDate){
		this.targetDate = targetDate;
	}
	public String getTargetDate(){
		return this.targetDate;
	}
	
	public void setTargetHour(int targetHour){
		this.targetHour = targetHour;
	}
	public int getTargetHour(){
		return this.targetHour;
	}
	
	public void setTargetInterval(int targetInterval){
		this.targetInterval = targetInterval;
	}
	public int getTargetInterval(){
		return targetInterval;
	}
	
	public void setCNSDReqID(String cnsdReqID){
		this.cnsdReqID = cnsdReqID;
	}
	public String getCNSDReqID(){
		return this.cnsdReqID;
	}
	
	public void setTargetCompCdList(HashMap<String, String> targetCompCdList){
		this.targetCompCdList = targetCompCdList;
	}
	public HashMap<String, String> getTargetCompCdList(){
		return targetCompCdList;
	}
	
	public void setTargetCompCd(String targetCompCd){
		this.targetCompCd = targetCompCd;
	}
	public String getTargetCompCd(){
		return this.targetCompCd;
	}

}
