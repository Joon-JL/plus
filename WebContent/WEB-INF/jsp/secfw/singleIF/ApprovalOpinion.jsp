<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.Token" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%--
/**
 * 파     일     명 : ApprovalOpinion.jsp
 * 프로그램명 : 결재상신의견 등록페이지
 * 설               명 : 
 * 작     성     자 : 금현서
 * 작     성     일 : 2009.11
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
<head>
<meta charset="utf-8" />

<title><spring:message code="secfw.page.field.approval.submit" /></title>
<link href="<%=CSS%>/las.css"    type="text/css" rel="stylesheet">
<link href="<%=CSS%>/popup.css"    type="text/css" rel="stylesheet">
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript">
<!--

/**
* - 페이지 초기화 --
*/
$(document).ready(function(){

	/**
	* 버튼 동작 부분
	*/
	// 취소
	$('.btnw').click(function(){
		self.close();
	});

	// 결재상신
	$('.btnb').click(function(){
		if(validateForm(document.frm) != false) { 
			opener.document.frm.opinion.value = $('textarea[name=opinion]').val();
			opener.approvalSubmit();
			
			window.close();
		}
	});
});

//-->
</script>
</head>
<body class="pop">
<!-- header -->
<h1><spring:message code="secfw.page.field.approval.submit" /></h1>
<!-- //header -->

<div class="pop_area">
	<div class="pop_content">
		<form:form id='frm' name='frm' autocomplete="off">

			<table class='list_basic' >
				<colgroup>
					<col width="20%" />
					<col width="80%" />
				</colgroup>
				<tr>
					<th><spring:message code="secfw.page.field.approval.comments" /></th>
					<td>
					  <textarea name='opinion' rows="10" class='IpTextLe' style='width:97%; height:120px' fieldTitle="<spring:message code="secfw.page.field.approval.comments" />" maxLength="1024"></textarea>
					  <div class='ico_alert01 fR'><spring:message code="secfw.page.field.approval.commentsText" /></div>
					</td>
				</tr>
			</table>
			
			<style>
				.btnb {margin-top:5px; float:right}
			</style>
			<div class='btnb'>
				<span class="btn_all_w"><span class="confirm"></span><a><spring:message code="secfw.page.field.approval.submit" /></a></span>
		    </div>
						
						
		</form:form>

	</div>
</div>

<!--footer -->
<div class="pop_footer">
	<div class="btn_wrap fR" >
		<span class="btn_all_w mR5"><span class="cancel"></span><a href="#" onclick="javascript:self.close();"><spring:message code="clm.page.button.cancel" /></a></span>
	</div>
</div>
<!-- //footer -->
</body>
</html>
