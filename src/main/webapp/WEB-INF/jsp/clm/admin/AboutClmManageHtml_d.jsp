<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil"%>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap"%>
<%-- 
/**
 * 파  일  명 : AboutClmManageHtml_d.jsp
 * 프로그램명 : About CLM Html
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%
    String menuNavi = (String) request.getAttribute("secfw.menuNavi");
			ListOrderedMap lom = (ListOrderedMap) request.getAttribute("lom");
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="clm.main.title" />
</title>

<!-- style -->
<link href="<%=CSS%>/clm.css" type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script type="text/javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script type="text/javascript">

	$(document).ready(function() {
		var frm = document.frm;
		
		var stHtml = '<html><head><meta content="text/html; charset=utf-8" http-equiv="Content-Type"><meta name="GENERATOR" content="ActiveSquare"></head><body>';
		var edHtml = '</body></html>';

		var contHtml = stHtml+frm.cont.value+edHtml;
		
// 		var contHtml = frm.cont.value;
		
		
		var lt = replace(contHtml, "＜", "<");
		var gt = replace(lt, "＞", ">");
		
		$('#content_in').append(gt);
});

</script>

</head>
<body>

	<div id="wrap">
		<!-- container -->
		<div id="container">
			<!-- Location -->
			<div class="location">
				<img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0" />
				<c:out value='${menuNavi}' escapeXml="false" />
			</div>
			<!-- //Location -->

			<!-- title -->
			<div class="title">
				<h3>
					<c:out value="${lom.title}" />
				</h3>
			</div>
			<!-- //title -->

			<!-- content -->
			<div id="content">
				<div id="content_in">
					<!-- **************************** Form Setting **************************** -->
					<form:form name="frm" id="frm" method="post" autocomplete="off">

						<!-- search form -->
						<input type="hidden" name="method" value="" />
						<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
						<!-- key form-->
						<input type="hidden" name="cont" id="cont" value="<c:out value='${lom.cont}'/>" />
						<input type="hidden" name="seqNoHtml" id="seqNoHtml" value="<c:out value='${command.seqNoHtml}'/>" />
						<!-- // **************************** Form Setting **************************** -->
					</form:form>
				</div>
			</div>
			<!-- //content -->

			<div class='tR mb20'>
				<a href="#wrap"><img src="<%=IMAGE%>/icon/ico_top.gif" title='Top' alt='Top' /></a>
			</div>


		</div>
	</div>
	<!-- footer  -->
	<script type="text/javascript" src="/script/clms/footer.js"></script>
	<!-- // footer -->
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp" />
</body>
</html>