package com.sds.secframework.util.service;

import java.io.IOException;
import java.util.HashMap;

public interface ComUtilService {

	public HashMap getNamoContentAndFileInfo(String value) throws IOException, Exception;
			
	public boolean isCronServer() throws Exception;
	
	//해당 서버의 IP와 ProcessID도 함께 체크 : 2개의 값이 일치해야 true
	public boolean isCronServerAndPort() throws Exception;
}
