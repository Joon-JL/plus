package com.sec.GFI_ECC_FI.FIA;

public class SELMSPlusDataJob {

	public SELMSPlusDataJob(){}
	
	private SELMSPlusDataService selmsPlusDataService = null;
	public void setSELMSPlusService(SELMSPlusDataService selmsPlusDataService){
		this.selmsPlusDataService = selmsPlusDataService;
	}
	
	public synchronized void sendBatch(){
		this.selmsPlusDataService.SendData();
	}
	// 2014-04-28 Kevin added.
	public synchronized void receiveBatch(){
		this.selmsPlusDataService.ReceiveData();
	}
}
