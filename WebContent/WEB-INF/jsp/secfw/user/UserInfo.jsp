<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.Token" %>

<%@ page import="java.util.Hashtable" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%--
/**
 * 파     일     명 : UserInfo.jsp
 * 프로그램명 : 사용자 정보 화면
 * 설               명 : 
 * 작     성     자 : 금현서
 * 작     성     일 : 2009.11
 */
--%>
<%
	//ESB 사용자 정보
	Hashtable userInfoTable  = (Hashtable)request.getAttribute("userInfoTable");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>User Information</title>

<LINK href="<c:url value='/style/secfw/approval.css' />" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript">
<!--
	$(function() {

		//창닫기
		$('#closeBtn').click(function(){
			self.close();
		});
	
	});

//-->
</script>

</head>
<body>
<!-- **************************** Form Setting **************************** -->
	<form:form id="frm" name="frm" method="post" autocomplete="off">
		<!-- 페이지 공통 -->
		<input type="hidden" name="method"   value="">
		<input type="hidden" name="user_id"   value="<%=userInfoTable.get("epid") %>">
	
<!-- //**************************** Form Setting **************************** -->
	<table width='100%' height='100%' cellpadding='0' cellspacing='0'>
		<tr>
			<td id='bodyDivIn'>
				<!-- 기본정보 -->
				<div class='top'><div class='left'><spring:message code="secfw.page.field.userInfo.basicInformation" /></div><div class='right' id='closeBtn'></div></div> 
				<div class='topline'></div> 
				<div class='title_basic'><spring:message code="secfw.page.field.userInfo.basicInformation" /></div>
				<div>
					<table class="tbl_basic" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<!-- 성명 -->
							<th class='th_l'><spring:message code="secfw.page.field.userInfo.name" /></th>
							<td class='td_l'><div class='icon_name'><%=userInfoTable.get("cn") %></div></td>
							<!-- ID -->
							<th class='th_l'><spring:message code="secfw.page.field.userInfo.id" /></th>
							<td class='td_l'><%=userInfoTable.get("userid") %></td>
						</tr>
						<tr>
							<!-- 회사 -->
							<th class='th_l'><spring:message code="secfw.page.field.userInfo.company" /></th>
							<td class='td_l'><%=userInfoTable.get("o") %></td>
							<!-- 부서 -->
							<th class='th_l'><spring:message code="secfw.page.field.userInfo.team" /></th>
							<td class='td_l'><%=userInfoTable.get("department") %> </td>
						</tr>
						<tr>
							<!-- 직급 -->
							<th class='th_l'><spring:message code="secfw.page.field.userInfo.jobTitle" /></th>
							<td class='td_l'><%=userInfoTable.get("title") %></td>
							<!-- 사번 -->
							<th class='th_l'><spring:message code="secfw.page.field.userInfo.employeeNo" /></th>
							<td class='td_l'><%=userInfoTable.get("employeenumber") %></td>
						</tr>
						<tr>
							<!-- Nickname -->
							<th class='th_l'><spring:message code="secfw.page.field.userInfo.nickname" /></th>
							<td class='td_l'><%=userInfoTable.get("nickname") %></td>
							<!-- E-mail -->
							<th class='th_l'><spring:message code="secfw.page.field.userInfo.email" /></th>
							<td class='td_l'><%=userInfoTable.get("mail") %></td>
						</tr>
					</table>
				</div>
				<div style="height:20px"></div>
			</td>
		</tr>
	</table>	
	</form:form>
</body>
</html>