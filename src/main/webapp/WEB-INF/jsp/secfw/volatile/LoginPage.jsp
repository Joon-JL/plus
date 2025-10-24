<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.ArrayList" %>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%--
/**
 * 파     일     명 : LoginPage.jsp
 * 프로그램명 : 로그인페이지
 * 설               명 : 
 * 작     성     자 : 금현서
 * 작     성     일 : 2010.12
 */
--%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Log-In</title>

<LINK href="<%=CSS %>/common.css" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/CommonProgress.js' />"></script>
<script language="javascript">
<!--
	function login(){	
		var frm = document.frm;
		frm.action = "<c:url value='/secfw/volatile.do' />";
		frm.method.value = "login";
		frm.target = "_self";
		frm.method.value="login";
		frm.submit();
	}
//-->
</script>

</head>
<LINK href="<c:url value='/style/secfw/common.css' />" type="text/css" rel="stylesheet">
<body>
<!-- 
**************************** Form Setting **************************** 
-->
<form:form name="frm" method="post" autocomplete="off">
	<input type="hidden" name="method" />
<!-- //
**************************** Form Setting **************************** 
-->
	<div class='login'>
		<div class='login_title'>Login Page</div>
		<select name="locale" class="select left">
			<option value='ko'><spring:message code="secfw.page.field.volatile.korean"/></option>
			<option value='en'><spring:message code="secfw.page.field.volatile.english"/></option>
			<option value='zh'><spring:message code="secfw.page.field.volatile.chinese"/></option>
			<option value='ja'><spring:message code="secfw.page.field.volatile.japanese"/></option>
			<option value='fr'><spring:message code="secfw.page.field.volatile.french"/></option>
			<option value='de'><spring:message code="secfw.page.field.volatile.german"/></option>
		</select>
		<input type="text" name="user_id" id="user_id" class="text left mr_2" value="" />
		<a href="javascript:login()" class="bt_search"><span><spring:message code="secfw.page.field.volatile.search2"/></span></a>
	</div>

</form:form>
</body>
</html>