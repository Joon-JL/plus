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

}
