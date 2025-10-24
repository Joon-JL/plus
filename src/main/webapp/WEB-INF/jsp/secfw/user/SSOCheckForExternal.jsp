<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%
	String id = StringUtil.bvl((String)request.getParameter("id"),"");
	String type  = StringUtil.bvl((String)request.getParameter("t"),"");	
	String m_type = StringUtil.bvl((String)request.getParameter("mt"),"");
%> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>SSO Check </title>
<script language="javaScript">
<!--
	function goSession() 
	{		
		if(navigator.userAgent.toLowerCase().indexOf("msie")==-1){
			
			//setTimeout("document.location.href = http://myslas.samsung.net:8080/ssoLoginCrossBrowse.do?_frameF=true", 1000);
			// temporary comment. 2014-05-14 Kevin.
			//document.form2.submit();
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
	<form name="form1" method="post" action="<c:out value='${pageContext.request.contextPath}' />/external/access.do" autocomplete="off">
		<input type="hidden" name="method" value="showDetail"/>
		<input type="hidden" name="totaldata" value=""/>
		<input type="hidden" name="id" value="<%=id%>"/>
		<input type="hidden" name="t" value="<%=type %>" />
		<input type="hidden" name="mt" value="<%=m_type %>" />
		<input type="hidden" name="menuLogEnable" value="Y"/>	
	</form>
<%-- 	<form name="form2" method="post" action="<c:out value='${pageContext.request.contextPath}' />/ssoLoginCrossBrowse.do" autocomplete="off"></form> --%>
</body>
</html>