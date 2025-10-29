
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title>No Authorisation</title>
<link href="<c:url value='/style/secfw/common.css' />" type="text/css"
	rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script type="text/javascript">
function openItvoc(){
	window.open("http://it4u.sec.samsung.net/itvoc/jsp/itvoclink/itvocLstDo.jsp?cmd=insertform&fromCmd=ListRequest&&sysCode=11P002410D0660");
}
</script>
</head>
<body style='overflow: hidden;'>
	<div class="alert_wrap">
		<div class="exp_box alert_width" style='width: 520px;'>
			<div class="exp_box_in alert_height">
				<div class="alert_title"><%="No Authorisation" %></div>
				<ul class="alert_contents">
					<li class="img"></li>
					<li class="txt" style='width: 370px;'><spring:message
							code="secfw.page.field.alert.noAuthorisation" /><br /> <strong>E-mail
							: sdsehelp@samsung.com</strong><br /> <strong>or click the
							following link to raise IT VOC</strong><br/><br/><br/>
						<a
							href="http://it4u.sec.samsung.net/itvoc/jsp/itvoclink/itvocLstDo.jsp?cmd=insertform&fromCmd=ListRequest&&sysCode=11P002410D0660"
							target="_blank"> <img
								src="<c:url value='/images/clm/en/common/bananer_VOC.jpg' />"
								alt="IT-VOC" title="IT-VOC" /></a>
					</li>
				</ul>
			</div>

		</div>
	</div>
	<!-- footer -->
	<address class="alert_address">
		<span><spring:message
				code="secfw.page.copyright.content.bottom" /></span>
	</address>
	<!-- //footer -->
</body>
</html>