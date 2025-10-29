package com.sec.GFI_ECC_FI.FIA;

import org.joda.time.DateTime;

import com.sds.secframework.common.dto.CommonVO;

public class SELMSPlusDataLogVO extends CommonVO {
	
	private DateTime _executionDate = null;
	private int _selmsplusDataCnt = 0;
	private int _transferredDataCnt = 0;
	private String _faildData = null;
	
	public DateTime getExecutionDate(){
		return _executionDate;
	}
	
	public int getSELMSPlusDataCnt(){
		return _selmsplusDataCnt;
	}
	
	public int getTransferredDataCnt() {
		return _transferredDataCnt;
	}
	
	public String getFaildData(){
		return _faildData;
	}
	
	public void setExecutionDate(DateTime executionDate){
		this._executionDate = executionDate;
	}
	
	public void setSELMSPlusDataCnt(int selmsPlusDataCnt){
		this._selmsplusDataCnt = selmsPlusDataCnt;
	}
	
	public void setTransferredDataCnt(int transferredDataCnt){
		this._transferredDataCnt = transferredDataCnt;
	}
	
	public void setFaildData(String faildData){
		this._faildData = faildData;
	}
}
