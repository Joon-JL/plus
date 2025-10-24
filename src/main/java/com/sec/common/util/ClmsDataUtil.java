package com.sec.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sds.secframework.common.dto.CommonForm;
import com.sds.secframework.common.dto.CommonVO;
import com.sds.secframework.common.util.StringUtil;

public class ClmsDataUtil {
	
	private static Log logger = LogFactory.getLog(ClmsDataUtil.class);
	
	/**                                                                                                                 
	 * HttpServletRequest 로 넘어오는 parameter 를 debugging                                                            
	 */                                                                                                                 
	public static void debugParamByRequest(Object paramObj, boolean print){
		
		
		 if(paramObj == null)
			 return;

		 String key = null;

		 if(print){
			if(paramObj instanceof HttpServletRequest){
				logger.debug("############ print request parameter start ############");

				Map paramMap = ((HttpServletRequest)paramObj).getParameterMap();
				Iterator iterator = paramMap.keySet().iterator();

				while(iterator.hasNext()){
					key = (String)iterator.next();
					String[] valAry = (String[])paramMap.get(key);
					String val = "";

					if(valAry != null && valAry.length > 0){
						for(int i=0;i<valAry.length;i++){
							val = val + "{" + valAry[i] + "}";
						}
						logger.debug("[" + key + "] : [" + val + "]");
					}
				}
				logger.debug("############ print request parameter end ############");            
			}else if(paramObj instanceof CommonForm || paramObj instanceof CommonVO){
				Map methodMap = getGetterMethod(paramObj.getClass());
				Iterator iterator = methodMap.keySet().iterator();

				logger.debug("############ print VO(Form) parameter start ############");

				while (iterator.hasNext())                                                                              
				{                                                                                                       
					key = (String)iterator.next();                                                                      
					Method method = (Method)methodMap.get(key);                                                         
					                                                                                                    
					try                                                                                                 
					{                                                                                                   
						logger.debug("[" + key + "] : [" + method.invoke(paramObj, null) + "]");                        
					}                                                                                                   
					catch (Exception ignored)                                                                           
					{                                                                                                   
					}                                                                                                   
				}                         
				logger.debug("############ print VO(Form) parameter end ############");
			}
		}
	}
	
	/**
	 * VO 및 Form 객체에서 getxxx method 를 반환한다
	 */
	public static Map getGetterMethod(Class cls){
		Map getterMethod = new HashMap();
		Method[] methodAry = cls.getMethods();

		for (int i = 0; i < methodAry.length; i++)
		{
			if (methodAry[i].getName().startsWith("get") && methodAry[i].getName().length() > 3)
			{
				String methodName = methodAry[i].getName();
				String key = methodName.substring(3).toLowerCase();
				
				if (getterMethod.get(key) == null)
					getterMethod.put(key, methodAry[i]);
			}
		}

		return getterMethod;
	}


	/**
	 * VO 및 Form 객체에서 setxxx method 를 반환한다
	 */
	public static Map getSetterMethod(Class cls) {
		Map setterMethod = new HashMap();
		Method[] metAry = cls.getMethods();
		
		for (int i = 0; i < metAry.length; i++) {
			if (metAry[i].getName().startsWith("set") && metAry[i].getName().length() > 3 && metAry[i].getParameterTypes().length == 1) {
				String metName = metAry[i].getName();
				String key = metName.substring(3).toLowerCase();
				
				if (setterMethod.get(key) == null) {
					setterMethod.put(key, metAry[i]);
				}
			}
		}
		return setterMethod;
	}

	/**
	 * debug log
	 */
	public static void debug(Object logVal) {
		logger.debug(logVal);
	}
	
	/**
	 * info log
	 */
	public static void info(Object logVal) {
		logger.info(logVal);
	}
	
	/**
	 * original 구분을 target구분으로 변환하여 url을 생성한다.
	 */
	public static String makeMenuURL(String url, String originGbn, String targetGbn){
		
		String retUrl = "";
		
		if("".equals(StringUtil.bvl(url, ""))){
			return retUrl;
		}
		
		int cnt = 0;
		
		StringTokenizer token = new StringTokenizer(url, originGbn);
		while(token.hasMoreTokens()){
			if(cnt == 0){
				retUrl = token.nextToken();
			}else{
				retUrl = retUrl + targetGbn + token.nextToken();
			}
			
			++cnt;
		}
		return retUrl;
	}
	
	/**
	 * Gais interface 실패시 파일로 로그를 남긴다.
	 * 의뢰ID, 계약ID 를 받아온다.
	 */
	public static void traceGaisErrLog(String cnsdreq_id, String cntrt_id, String errType) {
		
		SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss"); //시간설정
		//에러 메세지 셋팅
		String errMessage = "## cnsdreq_id : " + cnsdreq_id + " | cntrt_id : " + cntrt_id + " | errType : " + errType +" | error time : " + fmt.format(new Date());
		PrintWriter pw = null;
		
		try{
			Date date = new Date();  //현재날짜시간
			String curYear = new SimpleDateFormat("yyyy").format(date);
			String curMonth = new SimpleDateFormat("MM").format(date);
			String curDay = new SimpleDateFormat("dd").format(date);
			
			//gais 에러로그 폴더위치
			String folder = "/las/logs/intf/gaislog";
			
			//파일생성
			String filePath = folder + "/" + "GaisErrLog" + curYear + "_" + curMonth + "_" + curDay + ".txt";
			File dir2 = new File(filePath);
			if(dir2.isFile()){ //경로상에 파일이 있다면				
			}else{
				FileOutputStream fileoutputstream = new FileOutputStream(filePath);
				Writer writer = new OutputStreamWriter(fileoutputstream, "UTF-8");
				writer.close();				
			}
			
			pw = new PrintWriter(new FileWriter(filePath, true)); //파일 내용 이어쓰기
			pw.write(errMessage);
			pw.write("\r\n");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			pw.close();
		}
	}	
}
