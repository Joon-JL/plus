<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>System Alert</title>
<LINK href="<c:url value='/style/secfw/common.css' />" type="text/css" rel="stylesheet">
</head>
<body>
        <div class="alert_wrap">
	        <div class="exp_box alert_width">
		        <div class="exp_box_in alert_height">
		            <div class="alert_title"><spring:message code="common.page.field.SystemAlert.systemOnRepair"/></div>
		            <ul class="alert_contents">
		            	<li class="img"></li>
		            	<li class="txt">
		            		<spring:message code="common.page.field.SystemAlert.closeSystem"/><br>
		            		<spring:message code="common.page.field.SystemAlert.alertDate"/> : 2022/07/15 20:00 ~ 2022/07/17 21:00<br>
		            		<br>
		            		<strong>E-mail : selmsplus@samsung.com</strong><br>
		                <!-- 	<strong>Tel : +82-2-2255-4063</strong>  -->
		                </li>
		          	</ul>
		        </div>
	      	</div>
      	</div>
      	
		<!-- Footer -->
		<address class="alert_address">
			<spring:message code="secfw.page.copyright.content.bottom" />
		</address>
		<!-- //Footer -->
</body>
</html>
