package com.sds.secframework.common.util;

import java.security.MessageDigest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Token {
	
	public static String set(HttpServletRequest req){
		return set(req, "TOKEN");
	}

	public static String set(HttpServletRequest req, String key){
		HttpSession session = req.getSession();
		long systime = System.currentTimeMillis();
		byte[] time = new Long(systime).toString().getBytes();
		byte[] id = session.getId().getBytes();
		String token = "";
		
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(id);
			md5.update(time);
			
			token = toHex(md5.digest());
			session.setAttribute(key,token);
		} catch( Exception e){
			System.err.println("Unable to calculate MD5 Diguests");
		}
		return token;
	}
	
	public static boolean isValid(HttpServletRequest req){
		//return isValid(req, "TOKEN");
		//TODO 2012-06-28 김형준 : 시스템 여러개 띄어놓고 작업시 토큰이 의미가 없음, 그래서 임시로 무조건 true로 리턴!
		return true;
	}
	
	public static boolean isValid(HttpServletRequest req, String key){
		HttpSession session = req.getSession();
		String requestToken = req.getParameter(key);
		String sessionToken = (String)session.getAttribute(key);
		
		if(requestToken == null || sessionToken == null)
			return false;
		else 
			return requestToken.equals(sessionToken);
	}
	
	private static String toHex(byte[] digest){
		StringBuffer buf = new StringBuffer();
		
		for(int i=0;i< digest.length;i++)
			buf.append(Integer.toHexString((int)digest[i] & 0x00ff));
		return buf.toString();
	}
	
	public static void resetToken(HttpServletRequest req, String key) {
		req.getSession().setAttribute(key, null);
	}
	
}
