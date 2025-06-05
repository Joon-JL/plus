package com.sec.GFI_ECC_FI.FIA;

public interface SELMSPlusDataService {

	void SendData();
	
	int SendData(SELMSPlusDataVO vo);
	
	void ReceiveData(SELMSPlusDataVO vo);

	void ReceiveData();
}
