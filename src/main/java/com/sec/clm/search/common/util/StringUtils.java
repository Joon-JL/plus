package com.sec.clm.search.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	
	public static String TOKEN_SPACE = " ";
	public static String TOKEN_COMMA = ",";
	public static String TOKEN_SPACEORCOMMA = "[\\s || ,]";
	
	/**
	 * String[]을 Map에 담는다. Map의 특성상 String이 중복될 경우  중복제거된다.
	 * @param str
	 * @return <code>java.util.HashMap<String,String></code>
	 */
	public static HashMap<String,String> arrayToMapByKillDuplicate(String[] str){
		
		HashMap<String,String> map = new HashMap<String,String>();
		if(str == null) return map; //validation check
		
		for(int i = 0 ; i < str.length; i++){
			if("".equals(str[i].trim())){
				continue;
			}
			map.put(str[i],str[i]);
		}
		
		return map;
	}
	
	/**
	 * String을 Map에 담는다. Map의 특성상 토큰된 String이 중복될 경우
	 * 중복제거된다.
	 * @param str
	 * @return <code>java.util.HashMap<String,String></code>
	 */
	public static HashMap<String,String> stringToMapTokenByKillDuplicate(String str, String token){
		if(str == null) return new HashMap<String,String>(); 
		return arrayToMapByKillDuplicate(str.split(token));
	}
	
    /**
     * 불필요한 특수문자를 제거한다.
     * jdk 1.4 이상에서 사용할 것
     * @return
     */
    public static String stripSpecialChar (String szSrc) {
    	
    	if ("".equals(szSrc) || szSrc == null)
    		return "";
    	
    	String result = ""; 
    	//String szPattern = "\\p{Punct}";
    	String szPattern = "[.\\-&/%=:@#$(),.+;~\\_]+";
    	
    	Pattern p = Pattern.compile (szPattern);
    	Matcher m = p.matcher (szSrc);
    	
		if (m.find())  //특수문자를 찾은 경우 해당 특수문자를 공백문자로 변환한다.
    		result = m.replaceAll(" ");
		else result = szSrc;  // 특수문자를 찾지 못한 경우 원래 소스문장을 result로 치환한다.
		
    	return result;
    }
	
}
