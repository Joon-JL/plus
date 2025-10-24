package com.sds.secframework.common.filter;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.sds.secframework.common.util.StringUtil;

public final class RequestWrapper extends HttpServletRequestWrapper{

	public RequestWrapper(HttpServletRequest servletRequest) {
		super(servletRequest);
		// TODO Auto-generated constructor stub
	}
	
	public String[] getParameterValues(String parameter) {
		  String[] values = super.getParameterValues(parameter);
		  if (values==null)  {
	                  return null;
	          }
		  int count = values.length;
		  String[] encodedValues = new String[count];
		  for (int i = 0; i < count; i++) {
	                 encodedValues[i] = cleanXSS(values[i]);
		   }
		  return encodedValues;
		}
	
	public String getParameter(String parameter) {
		  String value = super.getParameter(parameter);
		  if (value == null) {
		         return null;
                  }
		  return cleanXSS(value);
	}
	
	public String getHeader(String name) {
	    String value = super.getHeader(name);
	    if (value == null)
	        return null;
	    return cleanXSS(value);
	}
	
	private String cleanXSS(String value) {		
		if (StringUtil.isNull(value)) return value;		
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

		for(int i=0, leni=replaceRegE.length; i < leni; i++)
		{
			value = Pattern.compile(replaceRegE[i][0], Pattern.CASE_INSENSITIVE).matcher(value).replaceAll(replaceRegE[i][1]);
		}	
		return value;
	}
}
