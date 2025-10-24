<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@page import="org.springframework.web.servlet.support.RequestContext"%>
<%@page import="com.sds.secframework.common.util.StringUtil" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%--
/**
 * 파    일    명 : IntroNotice.jsp
 * 프 로 그 램 명 : 법무시스템 등록 인트로
 * 작    성    자 : 홍성현
 * 작    성    일 : 2013.10
 * 설          명 : 
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
<head>
<meta charset="utf-8" />

<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="/script/clms/common.js" type="text/javascript"></script>
<script language="javascript">
$(document).ready(function(){
    
});

</script>
</head>
<body>
<form:form id="frm" name="frm" autocomplete="off">
	<input type="hidden" id="method" name="method"/>
	<input type="hidden" id="flag" name="flag"/>
	<input type="hidden" id="access_yn" name="access_yn" value="Y"/>
<%-- 	<input type="hidden" name="menu_id"  value="<c:out value='20130321152454094_0000000430'/>" /> --%>
<div class="introWrap3"> 
		<!-----------------------------content----------------------------->
		<div class="intro_content3">
				<div class="article">
						<h1><img src="../../../images/las/en/mail/comming.gif" width="126" height="32"> </h1>
						<spring:message code="las.main.intro.subsidiary01" />
						<spring:message code="las.main.intro.subsidiary02" />
						<p class="intro_divider3"></p>
						<div>
								<ul>
										<li><img src="../../../images/las/en/icon/bu_arrow_black.gif" width="3" height="3"> <strong>15th November</strong> <span class="txcol">– EQH, SEUK</span></li>
										<li><img src="../../../images/las/en/icon/bu_arrow_black.gif" width="3" height="3"> <strong>25th November</strong> <span class="txcol">– SESA, SEBN, SEGR, SEP, SENA</span></li>
										<li><img src="../../../images/las/en/icon/bu_arrow_black.gif" width="3" height="3"> <strong>9th December</strong> <span class="txcol">– SEPOL, SECZ, SESK, SEAG, SHE-S, SHE-P, SEROM, SEB, SEAD, SELS, SEPM, SESG, SRPOL, SEF, SEG, SEI</span></li>
								</ul>
						</div>
				</div>
		</div>
</div>
</form:form>
</body>
</html>