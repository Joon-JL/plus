<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%
	String forwardURL = "";
	forwardURL = StringUtil.bvl((String)request.getParameter("secfw.forwardURL"),(String)request.getAttribute("secfw.forwardURL"));
	forwardURL = StringUtil.bvl(forwardURL, "");
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<OBJECT ID="EpAdmC" name="EpAdmC" classid="CLSID:C63E3330-049F-4C31-B47E-425C84A5A725" CODEBASE="<c:url value='http://v6.samsung.net/portalWeb/cabs/Tray/EpAdm2.cab'/>"></OBJECT>
<head>
<title> Login Check </title>

<script type='text/javascript' src="<c:url value='/script/secfw/mySingle/mySingleAdm.js'/>"></script>
<script type='text/javascript' src="<c:url value='/script/secfw/mySingle/checkSyncTray.js'/>"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="JavaScript">

	function goSession() {

	alert('goSession');		
		var	rrtn	= document.EpAdmC.GetSecureBox();

		if(rrtn != "") {
			
			EpAdmC.Regist("SECFW",0,"SECFW","");
			alert('0');
			$('#totaldata').val(rrtn);
			alert($('#totaldata').val());
			document.form1.submit();

		} else {
			alert('MySingle Login is required. Please log-in to MySingle.');
			location.replace('http://www.samsung.net');
			return;
			//location.href="" + STR_INDEX_PAGE + "";
		}
	}

	function init() {
		alert('sss init');
		mySingleAdm.installComponent("goInit();", "/cabs/SSO/");
	}

	function goInit() {

		var MSIEIndex = navigator.userAgent.indexOf("MSIE");
		if(navigator.userAgent.indexOf("MSIE")==-1){

			mySingleAdm.initialize();
			mySingleAdm.useEpAdmJC(checkUseSyncEXE());
			mySingleAdm.islogin('isloginCallback');
		} else{
			goSession();
		}	
	}	
			
	function isloginCallback(result) {
		alert('result=' + result);
		if (result) {
			mySingleAdm.sync('autosubmitCallback');
		} else {
			alert('re-login process');
		}
	}
											
	function autosubmitCallback() {
		var rrtn2 = mySingleAdm.getSecureBox();
		
		if(rrtn2 != "") {
			alert(rrtn2);

			window.parent.document.form1.totaldata.value = rrtn2;
			alert('222');
			alert(document.form1.totaldata.value);
			document.form1.submit();
			

		}else{ 
			alert('MySingle Login is required. Please log-in to MySingle.');
			location.replace('http://www.samsung.net');
			return;
//			location.href="" + STR_INDEX_PAGE + "";
		}	
	}

	$(function() {
		alert('start init');
		init();
	});
</script>
</head>
<body>
	<form name="form1" id="form1" method="post" action="<c:url value='/secfw/ssoCheck.do' />" autocomplete="off">
		<input type="hidden" name="method" value="ssoLogin">
		<input type="hidden" name="totaldata" id="totaldata" value="">
		<input type="hidden" name="secfw.forwardURL" value="<%=forwardURL %>">
	</form>
</body>
</html>