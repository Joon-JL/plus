<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%
    String alertTitle   = StringUtil.bvl((String)request.getAttribute("secfw.alert.title"), "");
    String alertMessage = StringUtil.bvl((String)request.getAttribute("secfw.alert.message"), "");
%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title>System Alert</title>
<link href="<c:url value='/style/secfw/common.css' />" type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
</head>
<body style='overflow:hidden;'>
<div class="alert_wrap">
  <div class="exp_box alert_width" style='width:520px;'>
    <div class="exp_box_in alert_height">
      <div class="alert_title"><%= alertTitle %></div>
      <ul class="alert_contents">
        <li class="img"></li>
        <li class="txt" style='width:370px;'>
          <%= alertMessage %><br />
          <strong>E-mail : sdsehelp@samsung.com</strong><br />
          <strong>VOC : <spring:message code="secfw.page.field.adminUser.voc" /></strong>
        </li>
      </ul>
    </div>
  </div>
</div>
<!-- footer -->
<address class="alert_address">
  <span><spring:message code="secfw.page.copyright.content.bottom" /></span>
</address>
<!-- //footer -->
</body>
</html>