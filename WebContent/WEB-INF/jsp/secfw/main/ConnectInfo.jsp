<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="org.springframework.web.servlet.support.RequestContext"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%
Locale lc = new RequestContext(request).getLocale();
String locale = StringUtil.bvl(lc.getLanguage(), "en");
String userNm = "ko".equals(locale) ? StringUtil.bvl((String)session.getAttribute("EP_USERNAME"), "") : StringUtil.bvl((String)session.getAttribute("EP_USERNAME_EN"), "");

String userName = StringUtil.bvl((String)session.getAttribute("secfw.session.dept_nm"), "") + " " + StringUtil.bvl((String)session.getAttribute("secfw.session.user_nm"), "");

%>

<html xmlns='http://www.w3.org/1999/xhtml'>
<head>
<title>Connection Info</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<c:url value='/style/secfw/common.css'/>" rel='stylesheet' type='text/css'>
</HEAD>
<script language="javascript">
/**
 * 메인 페이지로 이동
 */
 function doAction() {
		var frm = document.frm;

		frm.action = "<c:url value='/secfw/main.do' />";
		frm.method.value = "forwardMainPage";
		
		frm.submit();
}

window.name = "CONNECTINFO_BASE_WIN";
window.focus();
</script>

<BODY>
<form:form id="frm" name="frm" autocomplete="off">
	<input type="hidden" id="method" name="method">
	<input type="hidden" id="siteLanguage" name="siteLanguage" value="<%=locale%>">
</form:form>
			<%
			if ("ko".equals(locale)) {
			%>
			<div class='intro'>
				<div class='intro_name'>☞ <%=userName%> <spring:message code="secfw.page.field.main.infoResourceMg"/></div>
				<div class='intro_title left'>└ <spring:message code="secfw.page.field.main.presentIpConn"/></div>
				<div class='intro_data'>: <%=StringUtil.bvl((String)session.getAttribute("EP_LOGINIP"), "")%></div>
				<div class='intro_title left'>└ <spring:message code="secfw.page.field.main.recentConnIP"/></div>
				<div class='intro_data'>: <%=StringUtil.bvl((String)session.getAttribute("EP_LOGINIP"), "")%></div>
				<div class='intro_title left'>└ <spring:message code="secfw.page.field.main.recentConnTime"/></div>
				<div class='intro_data'>: <%=getDate(new Date(), "yyyy-MM-dd HH:mm:ss")%></div>
				<div class='right mt_20'>
					<div class='intro_shortcut left' onclick=''>[<spring:message code="secfw.page.field.main.setShortcut"/>]</div>
					<div class='intro_ok left' onclick='javascript:doAction();'>[<spring:message code="secfw.page.field.main.confirm"/>]</div>
				</div>
			</div>
			<%
			}else {
			%>
			<div class='intro'>
				<div class='intro_name'>☞ The recent usage information by <%=userName%></div>
				<div class='intro_title left'>└ Actual connected IP</div>
				<div class='intro_data'>: <%=StringUtil.bvl((String)session.getAttribute("EP_LOGINIP"), "")%></div>
				<div class='intro_title left'>└ Latest connected IP</div>
				<div class='intro_data'>: <%=StringUtil.bvl((String)session.getAttribute("EP_LOGINIP"), "")%></div>
				<div class='intro_title left'>└ Latest connection Time</div>
				<div class='intro_data'>: <%=getDate(new Date(), "yyyy-MM-dd HH:mm:ss")%></div>
				<div class='right mt_20'>
					<div class='intro_shortcut left' onclick=''>[<spring:message code="secfw.page.field.main.setShortcut"/>]</div>
					<div class='intro_ok left' onclick='javascript:doAction();'>[<spring:message code="secfw.page.field.main.confirm"/>]</div>
				</div>
			</div>
			<%
			}
			%>

</BODY>
</HTML>

<%!
private String getDate(Date date, String format) {
	SimpleDateFormat formatter = new SimpleDateFormat(format);
	return formatter.format(date);
}
%>
<script>
doAction();
</script>
