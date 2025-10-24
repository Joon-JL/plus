<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%--
/**
 * 파  일  명 : ApprovalCheckResult.jsp
 * 프로그램명 : 계약유효성체크결과
 * 설      명 : 
 * 작  성  자 : 김형준
 * 작  성  일 : 2013.02
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
	String retErrType   = StringUtil.bvl((String)request.getAttribute("retErrType"),"");
%>
<html>
<head>
<meta charset="utf-8" />
<title><spring:message code="clm.main.title"/></title>
<link href="<%=CSS%>/popup.css"   type="text/css" rel="stylesheet">
<link href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet">
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script type="text/javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/common/CommonProgress.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script type="text/javascript">
function init(){
	
	opener.setApprovalYN('N');
	
	var msg = "";
	
	if("NOT_INSERT"=="<%=retErrType%>") { 		
		msg = "<spring:message code='clm.msg.alert.choosecontract.require01' />";
	}else if("ALREADY"=="<%=retErrType%>") {
		msg = "<spring:message code='clm.page.msg.manage.announce131' />";
	}else {
		msg = "<spring:message code='clm.page.msg.manage.announce055' />";
	}	
	alert(msg);
	self.close();
};
</script>
</head>
<body onload="javascript:init();">
</body>
</html>
