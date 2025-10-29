<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title>System Alert</title>
<link href="<c:url value='/style/secfw/common.css' />" type="text/css" rel="stylesheet">
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
</head>
<body style='overflow:hidden;'>
        <div class="alert_wrap">
	        <div class="exp_box alert_width">
		        <div class="exp_box_in alert_height">
		            <div class="alert_title"><spring:message code="secfw.page.field.underconstruction.title" /><!-- 개발 진행 중 입니다. --></div>
		            <ul class="alert_contents">
		            <li class="img"></li>
		            <li class="txt">
		                <strong><spring:message code="secfw.page.field.underconstruction.subtitle.01" /><!-- 현재 개발 진행 중 입니다. --><br/></strong>
		                <strong><spring:message code="secfw.page.field.underconstruction.subtitle.02" /><!-- 빠른 시일 내 완료하도록 하겠습니다. --><br/></strong></li>
		          </ul>
		        </div>
	      	</div>
      	</div>
		<!-- Footer>
		<address class="alert_address">
			<span><spring:message code="secfw.page.copyright.content.bottom" /></span>
		</address>
		<!-- //Footer -->
</body>
</html>