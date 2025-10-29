<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.manage.dto.ConsiderationForm" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%--
/**
 * 파  일  명 : ChooseStatus_p.jsp
 * 프로그램명 : 재작성단계팝업
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title" /></title>
<link href="<%=CSS%>/popup.css" type="text/css" rel="stylesheet" />
<link href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet" />

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript">
<!--
function goReRequest() {
	//var frm = document.frm;
	if(!confirm("<spring:message code="clm.page.msg.manage.announce144" />")) return;
	opener.setChooseStatusResult("request");
	self.close();
}

function goReApproval() {
	//var frm = document.frm;
	if(!confirm("<spring:message code="clm.page.msg.manage.announce146" />")) return;
	opener.setChooseStatusResult("approval");
	self.close();
}
//-->
</script>
</head>
<body class="pop">
<form:form name="frm" id='frm' method="post" autocomplete="off"> 
	<!-- search form -->
	<input type="hidden" name="method"   	value="" />
	<input type="hidden" name="menu_id" 	value="<c:out value='${command.menu_id}'/>" />
	<input type="hidden" name="cnsdreq_id"  value="<c:out value='${command.cnsdreq_id}'/>"/>
<h1><spring:message code="clm.page.msg.manage.rewriteStep" /><span class="close" onclick="javascript:self.close();" title="close"></span></h1>
<div>
	<div class="t_titBtn">
		<div class="btn_wrap_c">
			<span class="btn_all_w"><span class="reply"></span><a href="javascript:goReApproval();"><spring:message code="clm.page.msg.manage.reReport" /></a></span>
			<span class="btn_all_w"><span class="reply"></span><a href="javascript:goReRequest();"><spring:message code="clm.page.msg.manage.reReviewReq" /></a></span>			
		</div>
	</div>	
</div>	
</form:form>	
</body>
</html>
