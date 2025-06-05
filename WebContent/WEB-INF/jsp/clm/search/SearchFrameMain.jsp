<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@page import="java.util.List"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Map"%>

<% request.setCharacterEncoding("utf-8"); %>

<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />   
<title><spring:message code="las.main.title" /></title>
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->

<script type="text/javascript">
<!--//
//검색 요청 파라메터를 재전송한다.
function passSearch(){
    
    var form = document.createElement("FORM");
    form.method = "post";
    form.action = "/clm/search/search.do";
    form.target = "subFrame";

<%
	Enumeration<?> e = request.getAttributeNames();
	while (e.hasMoreElements()) {
		String name = (String) e.nextElement();
		Object value = request.getAttribute(name);
		
		if(value instanceof String[]) {
			value = ((String[])value)[0];
%>
	    var input = document.createElement("INPUT");
	    input.type = "hidden";
	    input.name = "<%=name%>";
	    input.value = "<%=value%>";
	    form.appendChild(input);
<%
		}
	}
%>
	
	document.body.appendChild(form);
	form.submit();
}
//-->
</script>
</head>
<frameset id='frTop' rows="100,*" frameborder="no" border="0" framespacing="0"  scrolling="no" onLoad="passSearch();">
	<frame src="<%=request.getContextPath()%>/secfw/main.ftl?method=forwardTopPage&menu_gbn=TOP" name="topFrame" scrolling="No" noresize="noresize">
	<frame name="subFrame" >
</frameset>
<noframes>
<body>
</body>
</noframes>
</html>
