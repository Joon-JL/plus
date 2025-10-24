package com.sds.secframework.common.util;

import java.io.CharArrayReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.dto.CommonForm;
import com.sds.secframework.common.dto.CommonVO;

public class COMUtil {

	public static Properties  prop;
	
	 public static String getProperties(String key) throws Exception {
		  String result = "";
		  
		  setProperties();
		  result = new String(prop.getProperty(key).getBytes(),"utf-8");
		  
		  String result1 = new String(prop.getProperty(key).getBytes(),"utf-8");
		  String result2 = new String(prop.getProperty(key).getBytes(),"euc-kr");
		  String result3 = new String(prop.getProperty(key).getBytes(),"iso-8859-1");
		 	  
		  return result;
	 } 

	 public static void setProperties() throws Exception {

		  if (prop == null) {
			  String strProp = System.getProperty("property.properties");
			  
			  prop = new Properties();
			  prop.load(new FileInputStream(strProp));

		  }
	 }
	 
		public static HashMap getNamoContentAndFileInfo(String value) throws IOException, Exception {
			
			HashMap result = new HashMap();
						
			NamoMime mime = new NamoMime();
			
	        mime.decode(value);

	        if(mime.isMultiPart()) {
	        	
				String saveUrl  = getProperties("smd.item.namo.http.url") + "/" + DateUtil.getTime("yyyyMM") ;
				String savePath = getProperties("smd.item.namo.attach.dir") + "/" + DateUtil.getTime("yyyyMM");
	
				FileUtil.mkdir(savePath);
				
				ArrayList fileList = new ArrayList();
	
				mime.setSaveURL(saveUrl);
		        mime.setSavePath(savePath);        
		
		        fileList = mime.saveFile();
		        
				result.put("FILE_INFO", fileList);
	
				String msgbody = Converter.replace(mime.getBodyContent(), "'", "\\'");
				result.put("CONTENT", msgbody);
	
				result.put("", value);
				
				result.put("TYPE", "M"); //MULTI PART
	        } else {

				String msgbody = Converter.replace(mime.getBodyContent(), "'", "\\'");
				result.put("CONTENT", msgbody);

	        	result.put("TYPE", "T"); //NON MULTI PART
	        }
			
			return result;
		}
		
		public static String getStringFromCLOB(Clob clob) throws Exception {
			String result = "";
			StringBuffer output = new StringBuffer();
			
		     Reader input = clob.getCharacterStream();
		       char[] buffer = new char[1024];
		       int byteRead;
		       while((byteRead=input.read(buffer,0,1024))!=-1)
		       {
		           output.append(buffer,0,byteRead);
		        }
		      input.close();
		      result = output.toString();
			
			return result;
		}
		
		public static Reader getCLOBFromString(String str) throws Exception {
            
            return new CharArrayReader(str.toCharArray());

		}
		
		public static boolean isCronServer() throws Exception {
			
			String cronIP = getProperties("smd.item.cron.ip");
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

	    /*public static synchronized String getMD5_Base64(String input) {

	    	MessageDigest digest = null;

	        try {
	            digest = MessageDigest.getInstance("MD5");
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }

	        if (digest == null)
	            return input;

	        try {
	            digest.update(input.getBytes("UTF-8"));
	        } catch (java.io.UnsupportedEncodingException ex) {
	            ex.printStackTrace();
	        }
	        byte[] rawData = digest.digest();
	        sun.misc.BASE64Encoder bencoder = new sun.misc.BASE64Encoder();
	        return bencoder.encode(rawData);
	    }*/
	    
		/**
		 * 세션정보에서 사용자-audit정보를 (session -> VO)세팅한다
		 */
	    public static void getUserAuditInfo(HttpServletRequest request, Object obj) throws Exception {

	    	HttpSession session = request.getSession(true);
	    	
	    	Locale lc = new RequestContext(request).getLocale();
	        String locale = StringUtil.bvl(lc.getLanguage(), "en");
	        
	        if (obj instanceof CommonVO) {
	        	
		        ((CommonVO)obj).setSys_cd(StringUtil.bvl(session.getAttribute("secfw.session.sys_cd"), ""));
		        ((CommonVO)obj).setSession_comp_cd(StringUtil.bvl(session.getAttribute("secfw.session.comp_cd"), ""));
		        ((CommonVO)obj).setSession_comp_nm(StringUtil.bvl(session.getAttribute("secfw.session.comp_nm"), ""));
		        //사업부코드 추가 : 신남원 : 2011.09.05
		        ((CommonVO)obj).setSession_division_cd(StringUtil.bvl(session.getAttribute("secfw.session.division_cd"), ""));
		        ((CommonVO)obj).setSession_dept_cd(StringUtil.bvl(session.getAttribute("secfw.session.dept_cd"), ""));
		        ((CommonVO)obj).setSession_dept_nm(StringUtil.bvl(session.getAttribute("secfw.session.dept_nm"), ""));
		        // 2012.05.29 담당사업부코드 세션 추가 added by hanjihoon
		        ((CommonVO)obj).setSession_clms_user_orgnz(StringUtil.bvl(session.getAttribute("secfw.session.clms_user_orgnz"), ""));
		        ((CommonVO)obj).setSession_resp_operdiv(StringUtil.bvl(session.getAttribute("secfw.session.resp_operdiv"), ""));
		        
		        ((CommonVO)obj).setSession_user_id(StringUtil.bvl(session.getAttribute("secfw.session.user_id"), ""));
		        ((CommonVO)obj).setSession_user_nm(StringUtil.bvl(session.getAttribute("secfw.session.user_nm"), ""));
		        ((CommonVO)obj).setSession_user_locale(locale);
		        ((CommonVO)obj).setSession_jikgup_cd(StringUtil.bvl(session.getAttribute("jikgup_cd"), ""));
		        ((CommonVO)obj).setSession_jikgup_nm(StringUtil.bvl(session.getAttribute("secfw.session.grade_nm"), ""));
		        ((CommonVO)obj).setSession_user_role_cds((ArrayList)session.getAttribute("secfw.session.role"));
		        ((CommonVO)obj).setSession_comp_tel(StringUtil.bvl(session.getAttribute("secfw.session.comp_tel"), ""));
		        ((CommonVO)obj).setSession_mobile(StringUtil.bvl(session.getAttribute("secfw.session.mobile"), ""));
		        
		        ((CommonVO)obj).setSession_up_level_dept_cd(StringUtil.bvl(session.getAttribute("secfw.session.up_level_dept_cd"), "")); //상위 부서코드들(^구분자로 연결) 추가 : 2011.09.23
		        ((CommonVO)obj).setSession_blngt_orgnz(StringUtil.bvl(session.getAttribute("secfw.session.blngt_orgnz"), "")); //소속조직 코드 추가 : 2011.09.23
		        ((CommonVO)obj).setSession_blngt_orgnz_nm(StringUtil.bvl(session.getAttribute("secfw.session.blngt_orgnz_nm"), "")); //소속조직명
		        ((CommonVO)obj).setSession_blngt_orgnz_nm_abbr(StringUtil.bvl(session.getAttribute("secfw.session.blngt_orgnz_nm_abbr"), "")); //소속조직약어명
		        ((CommonVO)obj).setSession_manager_yn(StringUtil.bvl(session.getAttribute("secfw.session.manager_yn"), "")); //조직장여부 추가 : 2011.09.23
		        ((CommonVO)obj).setSession_support_yn(StringUtil.bvl(session.getAttribute("secfw.session.support_yn"), "")); //지원부서여부 추가 : 2011.09.23
		        ((CommonVO)obj).setSession_in_dept_cd(StringUtil.bvl(session.getAttribute("secfw.session.in_dept_cd"), "")); 

		        ((CommonVO)obj).setSession_email(StringUtil.bvl(session.getAttribute("secfw.session.email"), "")); 
		        ((CommonVO)obj).setSession_single_id(StringUtil.bvl(session.getAttribute("secfw.session.single_id"), ""));
		        
		        ((CommonVO)obj).setSession_auth_apnt_yn(StringUtil.bvl(session.getAttribute("secfw.session.auth_apnt_yn"), ""));	//권한지정여부추가 : 2012.1.4
		        ((CommonVO)obj).setSession_auth_apnt_dept(StringUtil.bvl(session.getAttribute("secfw.session.auth_apnt_dept"), "")); //권한지정부서추가 : 2012.1.4
		        
		        ((CommonVO)obj).setSession_infsysnm(StringUtil.bvl(session.getAttribute("secfw.session.infsysnm"), "")); //연계시스템구분추가 : 2012.05.14
		        
		        ((CommonVO)obj).setSession_auth_comp_cd(StringUtil.bvl(session.getAttribute("secfw.session.auth_comp_cd"), "")); //COMP_CD : 2013.04.16
		        
		        ((CommonVO)obj).setSession_related_products(StringUtil.bvl(session.getAttribute("secfw.session.related_products"), "")); // 상품군 코드 2014.6.17
		        
	        } else if (obj instanceof CommonForm) {
		        ((CommonForm)obj).setSys_cd(StringUtil.bvl(session.getAttribute("secfw.session.sys_cd"), ""));
		        ((CommonForm)obj).setSession_comp_cd(StringUtil.bvl(session.getAttribute("secfw.session.comp_cd"), ""));
		        ((CommonForm)obj).setSession_comp_nm(StringUtil.bvl(session.getAttribute("secfw.session.comp_nm"), ""));
		        //사업부코드 추가 : 신남원 : 2011.09.05
		        ((CommonForm)obj).setSession_division_cd(StringUtil.bvl(session.getAttribute("secfw.session.division_cd"), ""));
		        ((CommonForm)obj).setSession_dept_cd(StringUtil.bvl(session.getAttribute("secfw.session.dept_cd"), ""));
		        ((CommonForm)obj).setSession_dept_nm(StringUtil.bvl(session.getAttribute("secfw.session.dept_nm"), ""));
		        // 2012.05.29 담당사업부코드 세션 추가 added by hanjihoon
		        ((CommonForm)obj).setSession_clms_user_orgnz(StringUtil.bvl(session.getAttribute("secfw.session.clms_user_orgnz"), ""));
		        ((CommonForm)obj).setSession_resp_operdiv(StringUtil.bvl(session.getAttribute("secfw.session.resp_operdiv"), ""));
		        ((CommonForm)obj).setSession_user_id(StringUtil.bvl(session.getAttribute("secfw.session.user_id"), ""));
		        ((CommonForm)obj).setSession_user_nm(StringUtil.bvl(session.getAttribute("secfw.session.user_nm"), ""));
		        ((CommonForm)obj).setSession_user_locale(locale);
		        ((CommonForm)obj).setSession_jikgup_cd(StringUtil.bvl(session.getAttribute("jikgup_cd"), ""));
		        ((CommonForm)obj).setSession_jikgup_nm(StringUtil.bvl(session.getAttribute("secfw.session.grade_nm"), ""));
		        ((CommonForm)obj).setSession_jikgup_nm(StringUtil.bvl(session.getAttribute("secfw.session.grade_nm"), ""));
		        ((CommonForm)obj).setSession_user_role_cds((ArrayList)session.getAttribute("secfw.session.role"));
		        ((CommonForm)obj).setSession_comp_tel(StringUtil.bvl(session.getAttribute("secfw.session.comp_tel"), ""));
		        ((CommonForm)obj).setSession_mobile(StringUtil.bvl(session.getAttribute("secfw.session.mobile"), ""));
		        
		        ((CommonForm)obj).setSession_up_level_dept_cd(StringUtil.bvl(session.getAttribute("secfw.session.up_level_dept_cd"), "")); //상위 부서코드들(^구분자로 연결) 추가 : 2011.09.23
		        ((CommonForm)obj).setSession_blngt_orgnz(StringUtil.bvl(session.getAttribute("secfw.session.blngt_orgnz"), "")); //소속조직 코드 추가 : 2011.09.23
		        ((CommonForm)obj).setSession_blngt_orgnz_nm(StringUtil.bvl(session.getAttribute("secfw.session.blngt_orgnz_nm"), "")); //소속조직명
		        ((CommonForm)obj).setSession_blngt_orgnz_nm_abbr(StringUtil.bvl(session.getAttribute("secfw.session.blngt_orgnz_nm_abbr"), "")); //소속조직약어명
		        ((CommonForm)obj).setSession_manager_yn(StringUtil.bvl(session.getAttribute("secfw.session.manager_yn"), "")); //조직장여부 추가 : 2011.09.23
		        ((CommonForm)obj).setSession_support_yn(StringUtil.bvl(session.getAttribute("secfw.session.support_yn"), "")); //지원부서여부 추가 : 2011.09.23
		        ((CommonForm)obj).setSession_in_dept_cd(StringUtil.bvl(session.getAttribute("secfw.session.in_dept_cd"), ""));

		        ((CommonForm)obj).setSession_email(StringUtil.bvl(session.getAttribute("secfw.session.email"), "")); 
		        ((CommonForm)obj).setSession_single_id(StringUtil.bvl(session.getAttribute("secfw.session.single_id"), "")); 
		        
		        ((CommonForm)obj).setSession_auth_apnt_yn(StringUtil.bvl(session.getAttribute("secfw.session.auth_apnt_yn"), ""));	//권한지정여부추가 : 2012.1.4
		        ((CommonForm)obj).setSession_auth_apnt_dept(StringUtil.bvl(session.getAttribute("secfw.session.auth_apnt_dept"), "")); //권한지정부서추가 : 2012.1.4
		        
		        ((CommonForm)obj).setSession_infsysnm(StringUtil.bvl(session.getAttribute("secfw.session.infsysnm"), "")); //연계시스템구분추가 : 2012.05.14
		        
		        ((CommonForm)obj).setSession_auth_comp_cd(StringUtil.bvl(session.getAttribute("secfw.session.auth_comp_cd"), "")); //COMP_CD : 2013.04.16
		        ((CommonForm)obj).setSession_related_products(StringUtil.bvl(session.getAttribute("secfw.session.related_products"), "")); // 상품군 코드 2014.6.17
	        }
	        
	    }
	   
}
