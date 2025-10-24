<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader("Expires",0);
	
	String userId = StringUtil.bvl((String)session.getAttribute("secfw.session.user_id"), "");
	
    if(session == null || "".equals(userId)) {
   		request.setAttribute("secfw.alertGbn"   ,"noSession");
		pageContext.forward("/secfw/systemAlert.do?method=forwardSystemAlert");
		return;
    }

%>