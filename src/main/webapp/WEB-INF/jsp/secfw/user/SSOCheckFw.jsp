<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%
	String forwardURL = "";
	forwardURL = StringUtil.bvl((String)request.getParameter("secfw.forwardURL"),(String)request.getAttribute("secfw.forwardURL"));
	forwardURL = StringUtil.bvl(forwardURL, "");
	
%> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title> Login Check </title>
<script language="javaScript">
<!--
	//alert('TEST 001');
	function goSession() 
	{		
	    if(navigator.userAgent.toLowerCase().indexOf("msie")==-1 && navigator.userAgent.toLowerCase().indexOf("windows") ==-1 ){
			
			//setTimeout("document.location.href = http://myslas.samsung.net:8080/ssoLoginCrossBrowse.do?_frameF=true", 1000);
			document.form2.submit();
		}else{
			var	rrtn	= EpAdmC.GetSecureBox();
			if( rrtn != "" ) 
			{
				EpAdmC.Regist("SEC_LEGAL",0,"SEC_LEGAL","");
				form1.totaldata.value = rrtn;
				form1.submit();
		  	} 
			else
			{
				alert('MySingle Login is required. Please log-in to MySingle.');
				location.replace('http://www.samsung.net');
				return;
			}
		}
	}
//-->
</script>
</head>
<body onload="javascript:goSession()">
<!-- 기존싱글 -->
<%--
<OBJECT ID="EpAdm2 Control" name="EpAdmC" CLASSID="CLSID:C63E3330-049F-4C31-B47E-425C84A5A725"></OBJECT>
--%>
<object id="EpAdm2 Control" name="EpAdmC" classid="CLSID:C63E3330-049F-4C31-B47E-425C84A5A725"></object>
	<form name="form1" method="post" action="<c:out value='${pageContext.request.contextPath}' />/secfw/ssoCheck.do" autocomplete="off">
		<input type="hidden" name="method" value="clmsLogin"/>
		<input type="hidden" name="totaldata" value=""/>
		<input type="hidden" name="secfw.forwardURL" value="<%=forwardURL%>"/>
		<input type="hidden" name="menuLogEnable" value="Y"/>	
	 <!-- <input type="hidden" name="x" value="o">  <!-- 시스템 작업중일 시 주석해제하면 공지페이지로 바로 이동한다. -->
	</form>
	<form name="form2" method="post" action="<c:out value='${pageContext.request.contextPath}' />/ssoLoginCrossBrowse.do" autocomplete="off"></form>
</body>
</html>