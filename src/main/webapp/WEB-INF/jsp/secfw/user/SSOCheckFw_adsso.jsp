<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<% /*** LDAP INTEGRATION 01 - START *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***/ %>
<%@page import="java.util.Collection"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%> 
<%@page import="org.apache.commons.lang3.StringUtils" %>
<%@page import="com.onelogin.saml2.SamlSSOAuth"%>
<% /*** LDAP INTEGRATION 01 - END  *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***/ %>
<%
	String forwardURL = "";
	forwardURL = StringUtil.bvl((String)request.getParameter("secfw.forwardURL"),(String)request.getAttribute("secfw.forwardURL"));
	forwardURL = StringUtil.bvl(forwardURL, "");
	
%> 
<% /*** LDAP INTEGRATION 02 - START *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***/ %>
<%
	SamlSSOAuth auth = null;
	Error authError = null;
	try {
		auth = new SamlSSOAuth(request, response);
		//auth.login(); //test
		auth.processResponse();
	} catch(Error ex) {
		authError = ex;
		auth = null;
	}
%>
<% /*** LDAP INTEGRATION 02 - END  *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***/ %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title> Login Check </title>
<script language="javaScript">
<!--
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
<% /*** LDAP INTEGRATION 03 - START *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***/ %>
				form1.submit();
				// TESTING
				//form1.submit();
				/*var frm = document.form3;
				frm.target = "_self";
				frm.action = "/secfw/ssoCheck.do";
				frm.method.value = "clmsSelLoginPrcs";
				frm.user_id.value = 'cerdeira.f'; //'M170413092332C6E4985'; //'cerdeira.f';
				frm.submit();*/
<% /*** LDAP INTEGRATION 03 - END  *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***/ %>
		  	} 
			else
			{
				alert('MySingle Login is required. Please log-in to MySingle.');
				location.replace('http://w1.samsung.net');
				return;
			}
		}
	}
//-->
</script>
</head>
<% /*** LDAP INTEGRATION 04 - START *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***/ %>
<body _onload="javascript:goSession()">
<% /*** LDAP INTEGRATION 04 - END *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***/ %>
<!-- ê¸°ì¡´ì‹±ê¸€ -->
<%--
<OBJECT ID="EpAdm2 Control" name="EpAdmC" CLASSID="CLSID:C63E3330-049F-4C31-B47E-425C84A5A725"></OBJECT>
--%>
<object id="EpAdm2 Control" name="EpAdmC" classid="CLSID:C63E3330-049F-4C31-B47E-425C84A5A725"></object>
	<form name="form1" method="post" action="<c:out value='${pageContext.request.contextPath}' />/secfw/ssoCheck.do" autocomplete="off">
		<input type="hidden" name="method" value="clmsLogin"/>
		<input type="hidden" name="totaldata" value=""/>
		<input type="hidden" name="secfw.forwardURL" value="<%=forwardURL%>"/>
		<input type="hidden" name="menuLogEnable" value="Y"/>	
		<!-- <input type="hidden" name="x" value="o"> ì‹œìŠ¤í…œ ìž‘ì—…ì¤‘ì¼ ì‹œ ì£¼ì„í•´ì œí•˜ë©´ ê³µì§€íŽ˜ì´ì§€ë¡œ ë°”ë¡œ ì´ë™í•œë‹¤. -->
	</form>
	<form name="form2" method="post" action="<c:out value='${pageContext.request.contextPath}' />/ssoLoginCrossBrowse.do" autocomplete="off"></form>
<% /*** LDAP INTEGRATION 05 - START *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***/ %>
	<form id="form3" name="frm" action="/secfw/ssoCheck.do?method=clmsSelLoginList" method="post" autocomplete="off"> 
		<input type="hidden" name="method"   value="" />
		<input type="hidden" name="user_id" value="" />
		<input type="hidden" name="curPage" value="">
		<input type="hidden" name="f" value="l"/>
	</form>	
<%
	if ((auth==null)||(!auth.getErrors().isEmpty())||(!auth.isAuthenticated())) 
	{
		if (auth!=null) {
			List<String> errors = auth.getErrors();
			out.println("<p>Error code: " + StringUtils.join(errors, ", ") + "</p>");
			out.println("<br/><br/><br/>");
		}
		if (authError!=null) {
			out.println("<p>" + authError.toString() + "</p>");
			out.println("<br/><br/><br/>");
		}
		out.println("<p>" + "LDAP authentication not accessible." + "</p>");
		out.println("<p>" + "Checking Knox portal instead, it may take few min... (3)" + "</p>");
		out.println("<br/><br/><br/>");
%>
		<script language="javaScript">
			setTimeout(function(){
				goSession();				
			}, 2000);
		</script>
<%
	} 
	else 
	{
		out.println("<p>" + "Checking LDAP authentication, it may take few min... (3)" + "</p>");
%>

		<table>
			<colgroup>
				<col width="70%">
				<col width="">
			</colgroup>
			<thead>
				<tr>
					<th class="center">Claim Key</th>
					<th class="center">Claim Value</th>
				</tr>
			</thead>
			<tbody>
<%
			String userID = "";

			// Search the received authentication result at the time of authentication success.
			Map<String, List<String>> attributes = auth.getAttributes();
			if (attributes.isEmpty()) 
			{
%>
				<tr>
					<td colspan="2">You don't have any attributes</td>
				</tr>
<%
			}
			else
			{
				Collection<String> keys = attributes.keySet();
				for(String name :keys)
				{
					out.println("<tr><td>" + name + "</td>");
					List<String> values = attributes.get(name);
					for(String value :values) 
					{
						out.println("<td>" + value + "</td>");
						
						if (
							(name.equalsIgnoreCase("LoginId"))
							||
							(name.equalsIgnoreCase("http://schemas.sec.com/2018/05/identity/claims/LoginId"))
							||
							(name.toUpperCase().indexOf("LOGINID")>=0)
						   )
						{
							userID = value;
						}  
					}
					out.println("</td></tr>");
				}
			}
%>
			</tbody>
		</table>
		<script language="javaScript">
			setTimeout(function(){
				var ssoUsr = "<%=userID%>";
				//alert(ssoUsr);
				var frm = document.form3;
				frm.target = "_self";
				frm.action = "/secfw/ssoCheck.do";
				frm.method.value = "clmsSelLoginPrcs";
				frm.user_id.value = ssoUsr;
				frm.submit();
			}, 2000);
		</script>
<%
	}
%>
<% /*** LDAP INTEGRATION 05 - END *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***/ %>	
</body>
</html>