<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<% response.setStatus(200); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
<title>System Alert</title>
<LINK href="<c:url value='/style/secfw/common.css' />" type="text/css" rel="stylesheet">
</head>
<body style='overflow:hidden;'>
        <div class="alert_wrap">
	        <div class="exp_box alert_width">
		        <div class="exp_box_in alert_height">
		            <div class="alert_title"><spring:message code="secfw.msg.error.error" /></div>
		            <ul class="alert_contents">
		            <li class="img"></li>
		            <li class="txt"><spring:message code="secfw.msg.error.ask" /><br />
		            	<strong>VOC : <spring:message code="secfw.page.field.adminUser.voc" /></strong><br />
		                <strong>E-mail : sdsehelp@samsung.com</strong><br />
		            </li>
		          </ul>
		        </div>
	      	</div>
      	</div>
		<!-- Footer -->
		<address class="alert_address">
			<span><spring:message code="secfw.page.copyright.content.bottom" /></span>
		</address>
		<!-- //Footer -->
</body>
</html>
