package com.sds.secframework.util.service.impl;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.FileUtil;
import com.sds.secframework.common.util.NamoMime;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.util.service.ComUtilService;

public class ComUtilServiceImpl extends CommonServiceImpl implements ComUtilService {

	public HashMap getNamoContentAndFileInfo(String value) throws Exception {
			
		HashMap result = new HashMap();
		NamoMime mime = new NamoMime();
		 
        mime.decode(value);
       
        if(mime.isMultiPart()) {
        	
			String saveUrl  = propertyService.getProperty("secfw.namo.http.url") + "/" + DateUtil.getTime("yyyyMM") ;
			String savePath = propertyService.getProperty("secfw.namo.attach.dir") + "/" + DateUtil.getTime("yyyyMM");

			FileUtil.mkdir(savePath);
			
			ArrayList fileList = new ArrayList();

			mime.setSaveURL(saveUrl);
	        mime.setSavePath(savePath);        
	
	        fileList = mime.saveFile();
	        
			result.put("FILE_INFO", fileList);

			//String msgbody = Converter.replace(mime.getBodyContent(), "'", "\\'");
			String msgbody = mime.getBodyContent();
			//msgbody=removeXssString(msgbody);
			result.put("CONTENT", msgbody);

			result.put("", value);
			
			result.put("TYPE", "M"); //MULTI PART
        } else {

			//String msgbody = Converter.replace(mime.getBodyContent(), "'", "\\'");
        	String msgbody = mime.getBodyContent(); 
        	//msgbody=removeXssString(msgbody);
			result.put("CONTENT", msgbody);		

	       	result.put("TYPE", "T"); //NON MULTI PART
        	
        	/*주의 : 보안검사시 주석해제 할것*/
        //	if(msgbody.toLowerCase().indexOf(".jsp")>=0 ){
		//		throw new Exception();
		//	}
        }
		
		return result;
	}
			
	public boolean isCronServer() throws Exception {
		
		String cronIP = propertyService.getProperty("secfw.server.batchIP");
		boolean result = false;

		InetAddress[] local = InetAddress.getAllByName(InetAddress.getLocalHost().getHostName());

	      for (int i=0; i < local.length; i++){
	    	  if(cronIP.equals((String)local[i].getHostAddress())) {
	    		  result = true;
	    		  break;
	    	  }
	      }		
	      
	    return result;
	}
	
	//해당 서버의 IP와 ProcessID도 함께 체크 : 2개의 값이 일치해야 true
	public boolean isCronServerAndPort() throws Exception {
		boolean result = isCronServer();
	     
	    if(result){
	    	result = false;
	    	String ins1pidFile = "/las/logs/las1/las1.pid";
	    	
			File ins1File = new File(ins1pidFile);
			if(ins1File.exists()) {
				FileInputStream fis = new FileInputStream(ins1File);
		    	BufferedReader dis  = new BufferedReader(new InputStreamReader(fis));
		    	
		    	String ins1Pid = "";
 
		    	if(dis.ready()) {
		    		ins1Pid = dis.readLine();
		    	}
		    	
		    	RuntimeMXBean rmb = ManagementFactory.getRuntimeMXBean();
		        String processId = rmb.getName();
		        
		        if(processId.length() > ins1Pid.length()) {
		         processId = processId.substring(0, ins1Pid.length());
		        }
		        
		        // 첫번째 인스턴스이면
		        if(processId.equals(ins1Pid)) {
		        	result = true;
		        }
			}

	    }
	    
	    return result;
	}
	
	private String removeXssString(String msgbody) {	
	
	    
		if (StringUtil.isNull(msgbody)) return msgbody;		
		String[][] replaceRegE = {
				{"<SCRIPT", 	"<_SCRIPT"},		
				{"<FRAME", 		"<_FRAME"},		
				{"<IFRAME", 	"<_IFRAME"},		
				{"<OBJECT", 	"<_OBJECT"},	
				{"JAVASCRIPT", 	"JAVA_SCRIPT"},
				{"JSCRIPT", 	"J_SCRIPT"},
				{"ALERT",    	"A L E R T"},
				{"VBSCRIPT",	"VB_SCRIPT"}
		};
		 msgbody = msgbody.toUpperCase();
		for(int i=0, leni=replaceRegE.length; i < leni; i++)
		{
			msgbody = Pattern.compile(replaceRegE[i][0], Pattern.CASE_INSENSITIVE).matcher(msgbody).replaceAll(replaceRegE[i][1]);
	
		}	
		
		return msgbody;
	}
}
