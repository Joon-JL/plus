<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%-- 
/**
 * 파  일  명 : ClmsSelLoginPage.jsp
 * 프로그램명 : 사용자 선택 로그인 페이지
 * 설      명 : 
 * 작  성  자 : 신남원
 * 작  성  일 : 2011.10.18
 */
--%>
<%

%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<link href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet"/>
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript">
<!--
	/**
	* 버튼 동작 부분
	*/
	function doAction(){
		var frm = document.frm;
		
		if(frm.login_id.value == "" || frm.login_pwd.value == ""){
			alert("<spring:message code='secfw.page.field.common.inputIdPw'/>");
			return;
		}
		frm.target = "_self";
		frm.action = "<c:url value='/secfw/ssoCheck.do' />";
		frm.submit();
	}
	
//-->

</script>

</head>
<body onLoad=''>

<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id="frm" method="post" autocomplete="off">
	<input type="hidden" name="method"   value="clmsSelLoginView">
	<input type="hidden" name="f" value="<%=request.getAttribute("f")%>"/>
	<!-- <input type="hidden" name="x" value="o"> 시스템 작업중일 시 주석해제하면 공지페이지로 바로 이동한다. -->
<!-- // **************************** Form Setting **************************** -->

<div id="login_box">
 
    <!-- login -->
    <div class="login">
     <span class="logo"><img src="<%=IMAGE%>/common/system_logo2.gif" alt="logo"  /></span>
        <dl>
        <dt>ID</dt><dd><input type="text" name="login_id" id="login_id" alt="" class="text_full" style="width:150px" /></dd>
        <dt>PWD</dt><dd><input type="password" name="login_pwd" id="login_pwd" alt="" class="text_full" style="width:150px" onKeypress="if(event.keyCode == 13){doAction();return false;}"/></dd>
        </dl>
		<a href="javascript:doAction();" class="btn_main" style='left:195px'><spring:message code="secfw.page.field.common.confirm"/></a>
    </div>
    <!-- //login -->  
 <jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</div>
</form:form>
</body>
</html>