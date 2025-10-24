<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@page import="com.sds.secframework.common.util.StringUtil"%>

<%
String targetMenuId 		= StringUtil.bvl((String)request.getAttribute("targetMenuId"), "");
String selectedMenuId 		= StringUtil.bvl(request.getAttribute("selected_menu_id"), "");	
String selectedMenuDetailId = StringUtil.bvl(request.getAttribute("selected_menu_detail_id"), "");
//String set_init_url 		= URLEncoder.encode( StringUtil.bvl(request.getAttribute("set_init_url"), ""));
String set_init_url 		= StringUtil.bvl(request.getAttribute("set_init_url"), "");
set_init_url = StringUtil.replace(set_init_url,"&","^");
%>


<%@page import="java.net.URLEncoder"%><html xmlns='http://www.w3.org/1999/xhtml'>
<head>
</head>
<title><spring:message code="clm.main.title" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<frameset id='frTop' rows="102,*,0" frameborder="no" border="0" framespacing="0">
		<frame src="<%=request.getContextPath() %>/secfw/main.ftl?method=forwardTopPage&menu_gbn=TOP&target_menu_id=<%=targetMenuId%>&selected_menu_id=<%=selectedMenuId%>&selected_menu_detail_id=<%=selectedMenuDetailId%>" name="topFrame" scrolling="No" noresize="noresize">
		<frame src="<%=request.getContextPath() %>/secfw/main.do?method=forwardSubFrame&target_menu_id=<%=targetMenuId%>&selected_menu_id=<%=selectedMenuId%>&selected_menu_detail_id=<%=selectedMenuDetailId%>&set_init_url=<%=set_init_url%>" name="subFrame">
		<frame src="/clm/cntrtAlert.do" name="blankFrame" scrolling="No" noresize="noresize">		
	</frameset>
<noframes>
<body>
</body>
</noframes>
</html>
