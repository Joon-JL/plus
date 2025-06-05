<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.Token" %>
<%@ page import="com.sec.las.statistics.dto.StatisticsForm" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>


<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Iterator" %>

<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.GregorianCalendar" %>
<%@ page import="java.math.BigDecimal" %>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>   
<%--
/**
 * 파     일     명 : ConsiderationPrint.jsp
 * 프로그램명 : 계약의뢰 품의서 출력
 * 설               명 : 
 * 작     성     자 : 
 * 작     성     일 : 2011.11
 */
--%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.page.msg.common.contractReview" /></title>
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet"/>  
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script language="javascript">
<!--
	$(document).ready(function(){
	});
	$(function() {

		$(document).keydown(function(event){
	
		});
	});


	function showMessage(msg){
		if(msg != null && msg.length > 1) {
			//alert(msg);
		}
	}	

	function rdOpen(path, param, data, zoom, print) {
		
		var	rd_path = path;
		var rd_param = param;
		Rdviewer.AutoAdjust = 0;
	    Rdviewer.ZoomRatio  = 100;
	    Rdviewer.SetBackgroundColor(255,255,255);
	    Rdviewer.SetPagelineColor(255,255,255);
	    Rdviewer.ViewShowMode (2);
		Rdviewer.HideStatusBar()
	    Rdviewer.SetPageScroll(0); 
		Rdviewer.FileOpen(rd_path, rd_param);
	}
		
//-->
</script>

</head>
<body > 

<div id="wrap">
	<!-- container -->
	<div id="container">	
	
	<form:form id="frm" name="frm" method="post" autocomplete="off">
		<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
		<!-- 페이지 공통 -->	
	    <!-- Location -->	
	    <div class="location">
	        <img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/>
	    </div>
	    <!-- //Location -->	
	
		<!-- Title -->
		<div class="title">
		<h3><a href="#"><spring:message code="clm.page.msg.common.contractReview" /></a></h3>
		</div>
		
		<!-- content --> 		
		<div id="content">
	
		</div>
		<!-- content -->

</form:form>	
<!--  form 밑에 선언해야 정상적으로 호출됨 -->
	<div align="center">
		<table width="100%" align="center">
		<tr>
		<td height="600px" >
	
			<script language="javascript" src="<c:url value='/script/secfw/report/rdviewer.js' />"></script>
			<script>	
			  	var parm =  "/rv system_logo[<c:out value='${command.report_url}'/><%=IMAGE%>/common/logo.jpg] cnsdreq_id[<c:out value='${command.cnsdreq_id}'/>] B.CNTRT_ID[<c:out value='${command.cntrt_id}'/>]";
			  	parm = replaceStr(parm, "&#039;","'");
			  	
			  	if(langCd.startsWith("ko")){
			  		rdOpen("<c:out value='${command.report_url}'/>"+"/report/contract_cnsdreq.mrd", parm , "", 100);
			  	}
			  	else{
			  		rdOpen("<c:out value='${command.report_url}'/>"+"/report/en_contract_cnsdreq.mrd", parm , "", 100);
			  	}

			</script>
		</td>
		</tr>	
		</table>	
	</div>	
	<!-- footer -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>	 
	<!-- // footer -->		
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
</body>
</html>