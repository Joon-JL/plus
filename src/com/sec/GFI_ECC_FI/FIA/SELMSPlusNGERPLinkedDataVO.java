package com.sec.GFI_ECC_FI.FIA;

public class SELMSPlusNGERPLinkedDataVO {

	private String selmsPlus_ID = null;
	public void setSELMSPlusID(String selmsPlusID){
		this.selmsPlus_ID = selmsPlusID; 
	}
	public String getSELMSPlusID(){
		return this.selmsPlus_ID;
	}
	
	private String comp_cd = null;
	public void setCompCD(String compCd){
		this.comp_cd = compCd;
	}
	public String getCompCD(){
		return this.comp_cd;
	}
	
	private String ba_cd = null;
	public void setBACD(String baCd){
		this.ba_cd = baCd;
	}
	public String getBACD(){
		return this.ba_cd;
	}
	
	private String gerp_id = null;
	public void setGERPID(String gerpID){
		this.gerp_id = gerpID;
	}
	public String getGERPID(){
		return this.gerp_id;
	}

	private String linked = null;
	public void setLinked(String linked){
		this.linked = linked;
	}
	public String getLinked(){
		return this.linked;
	}
	
	private String linked_dateTime = null;
	public void setLinkedDateTime(String linkedDateTime){
		this.linked_dateTime = linkedDateTime;
	}
	public String getLinkedDateTime(){
		return this.linked_dateTime;
	}
	
	private String module = null;
	public void setModule(String module){
		this.module = module;
	}
	public String getModule(){
		return this.module;
	}
	
	private float amount = 0f;
	public void setAmount(float amount){
		this.amount = amount;
	}
	public float getAmount(){
		return this.amount;
	}
	
	private String currency = null;
	public void setCurrency(String currency){
		this.currency = currency;
	}
	public String getCurrency(){
		return this.currency;
	}
}
