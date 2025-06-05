<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%--
/**
 * 파  일  명 : LawPrecedent.jsp
 * 프로그램명 : 법령판례검색
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 2011.09
 */
--%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta charset="utf-8" />

<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet" />
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/jquery-1.6.1.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/new_style.js' />"></script>
</head>
<body>
<div id="wrap">	
	<div id="container">
		<!-- content -->
		<!-- location -->
		<div class="location"></div>
		<!-- //location -->
		
		<!-- title -->
		<div class="title">
			<h3><spring:message code='las.page.title.lawinformation.lawPrecedent' /></h3>
		</div>
		<!-- //title -->
			
		<div id="content">
		<div id="content_in">
		
			<div class="seach_listBox">
				<dl class="Kor_list01 fL">
					<dt><spring:message code='las.page.field.lawinformation.local.lawSearch' /></dt>
					<dd>
						<ul>
							<li><span class="fL"><spring:message code='las.page.field.lawinformation.scourt' /></span> <span class="fR"><a href="<spring:message code='las.page.field.lawinformation.url.scourt' />" target="_blank"><spring:message code='las.page.field.lawinformation.url.scourt' /></a></span></li>
							<li><span class="fL"><spring:message code='las.page.field.lawinformation.moleg' /></span> <span class="fR"><a href="<spring:message code='las.page.field.lawinformation.url.moleg' />" target="_blank"><spring:message code='las.page.field.lawinformation.url.moleg' /></a></span></li>
							<li><span class="fL"><spring:message code='las.page.field.lawinformation.lawnb' /></span> <span class="fR"><a href="<spring:message code='las.page.field.lawinformation.url.lawnb' />" target="_blank"><spring:message code='las.page.field.lawinformation.url.lawnb' /></a></span></li>
							<%--
							<li><span class="fL"><spring:message code='las.page.field.lawinformation.kolis' /></span> <span class="fR"><a href="<spring:message code='las.page.field.lawinformation.url.kolis' />" target="_blank"><spring:message code='las.page.field.lawinformation.url.kolis' /></a></span></li>
							<li><span class="fL"><spring:message code='las.page.field.lawinformation.netlaw' /></span> <span class="fR"><a href="<spring:message code='las.page.field.lawinformation.url.netlaw' />" target="_blank"><spring:message code='las.page.field.lawinformation.url.netlaw' /></a></span></li>
							 --%>
							<li><span class="fL">LawKorea</span> <span class="fR"><a href="<spring:message code='las.page.field.lawinformation.url.lawkorea' />" target="_blank"><spring:message code='las.page.field.lawinformation.url.lawkorea' /></a></span></li>
						</ul>
					</dd>
				</dl>
				<dl class="Kor_list01 fR">
					<dt><spring:message code='las.page.field.lawinformation.local.precedentSearch' /></dt>
					<dd>
						<ul>
							<li><span class="fL"><spring:message code='las.page.field.lawinformation.scourt' /></span> <span class="fR"><a href="<spring:message code='las.page.field.lawinformation.url.moleg' />" target="_blank"><spring:message code='las.page.field.lawinformation.url.moleg' /></a></span></li>
							<li><span class="fL"><spring:message code='las.page.field.lawinformation.kiri' /></span> <span class="fR"><a href="<spring:message code='las.page.field.lawinformation.url.kiri' />" target="_blank"><spring:message code='las.page.field.lawinformation.url.kiri' /></a></span></li>
							<li><span class="fL"><spring:message code='las.page.field.lawinformation.lawnb' /></span> <span class="fR"><a href="<spring:message code='las.page.field.lawinformation.url.lawnb' />" target="_blank"><spring:message code='las.page.field.lawinformation.url.lawnb' /></a></span></li>
							<%--
							<li><span class="fL"><spring:message code='las.page.field.lawinformation.kolis' /></span> <span class="fR"><a href="<spring:message code='las.page.field.lawinformation.url.kolis' />" target="_blank"><spring:message code='las.page.field.lawinformation.url.kolis' /></a></span></li>
							<li><span class="fL"><spring:message code='las.page.field.lawinformation.netlaw' /></span> <span class="fR"><a href="<spring:message code='las.page.field.lawinformation.url.netlaw' />" target="_blank"><spring:message code='las.page.field.lawinformation.url.netlaw' /></a></span></li>
							 --%>
							<li><span class="fL"><spring:message code='las.page.field.lawinformation.yeslaw' /></span> <span class="fR"><a href="<spring:message code='las.page.field.lawinformation.url.yeslaw' />" target="_blank"><spring:message code='las.page.field.lawinformation.url.yeslaw' /></a></span></li>
							<li><span class="fL"><spring:message code='las.page.field.lawinformation.ccourt' /></span> <span class="fR"><a href="<spring:message code='las.page.field.lawinformation.url.ccourt' />" target="_blank"><spring:message code='las.page.field.lawinformation.url.ccourt' /></a></span></li>
							<li><span class="fL"><spring:message code='las.page.field.lawinformation.moel' /></span> <span class="fR"><a href="<spring:message code='las.page.field.lawinformation.url.moel' />" target="_blank"><spring:message code='las.page.field.lawinformation.url.moel' /></a></span></li>
						</ul>	
					</dd>
				</dl>
			</div>
			<div class="seach_listBox">
				<dl class="Usa_list01 fL">
					<dt><spring:message code='las.page.field.lawinformation.foreign.lawSearch' /></dt>
					<dd>
						<ul>
							<li><span class="fL"><spring:message code='las.page.field.lawinformation.findlaw' /></span> <span class="fR"><a href="<spring:message code='las.page.field.lawinformation.url.findlaw' />" target="_blank"><spring:message code='las.page.field.lawinformation.url.findlaw' /></a></span></li>
							<li><span class="fL"><spring:message code='las.page.field.lawinformation.westlaw' /></span> <span class="fR"><a href="<spring:message code='las.page.field.lawinformation.url.westlaw' />" target="_blank"><spring:message code='las.page.field.lawinformation.url.westlaw' /></a></span></li>
							<li><span class="fL"><spring:message code='las.page.field.lawinformation.law' /></span> <span class="fR"><a href="<spring:message code='las.page.field.lawinformation.url.law' />" target="_blank"><spring:message code='las.page.field.lawinformation.url.law' /></a></span></li>
							<li><span class="fL"><spring:message code='las.page.field.lawinformation.url.alllaw' /></span> <span class="fR"><a href="<spring:message code='las.page.field.lawinformation.url.alllaw' />" target="_blank"><spring:message code='las.page.field.lawinformation.url.alllaw' /></a></span></li>
						</ul>
					</dd>
				</dl>
				<dl class="Usa_list01 fR">
					<dt><spring:message code='las.page.field.lawinformation.foreign.precedentSearch' /></dt>
					<dd>
						<ul>
							<li><span class="fL"><spring:message code='las.page.field.lawinformation.findlaw' /></span> <span class="fR"><a href="<spring:message code='las.page.field.lawinformation.url.findlaw' />.com" target="_blank"><spring:message code='las.page.field.lawinformation.url.findlaw' /></a></span></li>
							<li><span class="fL"><spring:message code='las.page.field.lawinformation.westlaw' /></span> <span class="fR"><a href="<spring:message code='las.page.field.lawinformation.url.westlaw' />" target="_blank"><spring:message code='las.page.field.lawinformation.url.westlaw' /></a></span></li>
							<li><span class="fL"><spring:message code='las.page.field.lawinformation.law' /></span> <span class="fR"><a href="<spring:message code='las.page.field.lawinformation.url.law' />" target="_blank"><spring:message code='las.page.field.lawinformation.url.law' /></a></span></li>
							<li><span class="fL"><spring:message code='las.page.field.lawinformation.url.alllaw' /></span> <span class="fR"><a href="<spring:message code='las.page.field.lawinformation.url.alllaw' />" target="_blank"><spring:message code='las.page.field.lawinformation.url.alllaw' /></a></span></li>
							<li><span class="fL"><spring:message code='las.page.field.lawinformation.washlaw' /></span> <span class="fR"><a href="<spring:message code='las.page.field.lawinformation.washlaw' />" target="_blank"><spring:message code='las.page.field.lawinformation.url.washlaw' /></a></span></li>
						</ul>
					</dd>
				</dl>
			</div>
		</div>
		</div>
		<!-- //content -->
		
	</div>
	<!-- //container -->
</div>
<!-- footer  -->
<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
<!-- // footer -->
</body>
</html>
