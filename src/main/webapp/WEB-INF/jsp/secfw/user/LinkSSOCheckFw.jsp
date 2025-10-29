<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%

	String CntrtGbn = StringUtil.bvl((String)request.getParameter("cntrt_gbn"), "");
	String SysNm = StringUtil.bvl((String)request.getParameter("sys_nm"), "");
	String KeyId = StringUtil.bvl((String)request.getParameter("key_id"), "");
	String CntrtId = StringUtil.bvl((String)request.getParameter("cntrt_id"), "");
	String CntrtNo = StringUtil.bvl((String)request.getParameter("cntrt_no"), "");
	String ssems_oppnt_cd = StringUtil.bvl((String)request.getParameter("ssems_oppnt_cd"), "");
	String returnURL = StringUtil.bvl((String)request.getParameter("returnURL"), "");
	String infsysnm = StringUtil.bvl((String)request.getParameter("infsysnm"), "");
%> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title> Login Check </title>
<script language="javaScript">
<!--
	function goSession() 
	{		
		var	rrtn	= EpAdmC.GetSecureBox();
		if( rrtn != "" ) 
		{
			EpAdmC.Regist("SEC_LEGAL",0,"SEC_LEGAL","");
			form1.totaldata.value = rrtn;
			form1.target = "_self";
			form1.submit();
	  	} 
		else
		{
			alert('MySingle Login is required. Please log-in to MySingle.');
			return;
		}
	}
//-->
</script>
</head>
<body onLoad="javascript:goSession()">
<!-- 기존싱글 -->
<%--
<OBJECT ID="EpAdm2 Control" name="EpAdmC" CLASSID="CLSID:C63E3330-049F-4C31-B47E-425C84A5A725"></OBJECT>
--%>
<OBJECT ID="EpAdm2 Control" name="EpAdmC" CLASSID="CLSID:C63E3330-049F-4C31-B47E-425C84A5A725"></OBJECT>
	<form name="form1" method="post" action="<c:url value='/secfw/ssoCheck.do' />" autocomplete="off">
		<input type="hidden" name="method" value="clmsLinkLogin">
		<input type="hidden" name="totaldata" value="">
		<input type="hidden" name="cntrt_gbn" value="<%=CntrtGbn%>">
		<input type="hidden" name="sys_nm" value="<%=SysNm%>">
		<input type="hidden" name="key_id" value="<%=KeyId%>">
		<input type="hidden" name="cntrt_id" value="<%=CntrtId%>">
		<input type="hidden" name="cntrt_no" value="<%=CntrtNo%>">
		<input type="hidden" name="ssems_oppnt_cd" value="<%=ssems_oppnt_cd%>">
		<input type="hidden" name="returnURL" value="<%=returnURL%>">
		<input type="hidden" name="infsysnm" value="<%=infsysnm%>">
		<input type="hidden" name="menuLogEnable" value="Y">	
		<!-- <input type="hidden" name="x" value="o">  시스템 작업중일 시 주석해제하면 공지페이지로 바로 이동한다. -->
	</form>
</body>
</html>