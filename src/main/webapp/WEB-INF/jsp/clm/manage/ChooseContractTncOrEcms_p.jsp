<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.manage.dto.ConsiderationForm"%>
<%@ page import="com.sds.secframework.common.util.DateUtil"%>
<%@ page import="com.sds.secframework.common.util.StringUtil"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<%--
/**
 * 파  일  명 : ChooseContractTncOrEcms_p.jsp
 * 프로그램명 : 계약유형선택팝업
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2014.06
 */
--%>

<html>
<meta charset="utf-8" />
<meta http-equiv="x-ua-compatible" content="IE=edge" >
<title>Select Contract Route</title>
<link href="<%=CSS%>/popup.css"   type="text/css" rel="stylesheet">
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet">

<script language="javascript" src="/script/secfw/jquery/jquery.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.form.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.flot.js"></script>
<script language="javascript">

 window.onbeforeunload = function() {
	 
	 noScr();
}

 function returnValue(){
	 
	 opener.returnValue();
	 
	 self.close();
 }
 
 function goTnc(){
	 
	 opener.opnerGoTnc();
	 
	 self.close();
 }
 
 function noScr(){
	 opener.noScreen();
	 
	 self.close();
 }
 
 function clseWindow(){
	 
	 
	 noScr();
 }


</script>

<body class="pop" >

		
		<!-- header -->
			<h1>Select</h1>
			<!-- //header -->
			<div class="pop_area">
			<!-- Popup Detail -->
		
		<!-- pop_content -->
		<div class="pop_content">
			<ul class="select_q">
				<li><spring:message code='las.page.field.hqrequest.page28'/> <br/><spring:message code='las.page.field.hqrequest.page32'/>
					<p><span class="btn_all_w" onclick=""><a href="javascript:goTnc();"><span class="arrow"></span><spring:message code='las.page.field.hqrequest.page29'/></a></span></p>
				</li>
				<li> <spring:message code='las.page.field.hqrequest.page30'/>
					<p><span class="btn_all_w" onclick=""><a href="javascript:returnValue();"><span class="arrow"></span><spring:message code='las.page.field.hqrequest.page31'/></a></span></p>
				</li>
			</ul>
		</div>
		<!-- //pop_content -->

		<!--footer -->
		<div class="pop_footer">
			<div class="btn_wrap fR" >
				<span class="btn_all_w mR5" onclick="javascript:noScr();"><span class="cancel"></span><a href="#">Close</a></span>
			</div>
		</div>
		<!-- //footer -->
</body>
</html>