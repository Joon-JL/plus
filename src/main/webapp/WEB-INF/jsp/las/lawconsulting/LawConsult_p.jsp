<!DOCTYPE html>

<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="com.sds.secframework.common.util.PageUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.collections.map.ListOrderedMap"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>

<%
	List cdList = (List)request.getAttribute("cdList");
%>

<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.main.title" />
</title>

<LINK href="<%=CSS%>/popup.css" type="text/css" rel="stylesheet">
<LINK href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->

<script language="javascript"	src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript"	src="<c:url value='/script/secfw/common/CommonProgress.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript"	src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript"	src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript"	src="<c:url value='/script/secfw/jquery/ui.core.js' />"></script>
<script language="javascript"	src="<c:url value='/script/clms/clms_common.js' />"></script>

<script language="javascript">

function goInsertForm() {
		
		var type = "";
		var type_name = "";
		var count = 0;
		
		for(var i=0 ; i < frm.type.length ; i++){
			if(frm.type[i].checked == true){
				if(count == 0){
					type+=frm.type[i].value;
					type_name += frm.type_nm[i].value;
					
				}
				else{
					type+="," + frm.type[i].value;
					type_name+=", " + frm.type_nm[i].value;
				}
				
				count++;
			}
		}
		
		if(type == ""){
			alert("<spring:message code='las.page.field.lawconsulting.selectConsultType'/>");
			return;
		}
		
		opener.returnType(type, type_name);
		window.close();
	}

</script>

</head>
<!--  2012.04.26 Alert 수정 modified by hanjihoon  -->
<body class="pop">

	<form:form name="frm" id="frm" autocomplete="off">
		<input type="hidden" name="method" value="" />
		<input type="hidden" name="menu_id"	value="<c:out value='${command.menu_id}'/>" />
		<input type="hidden" name="curPage"	value="<c:out value='${command.curPage}'/>" />
		<input type="hidden" name="isForeign" value="<c:out value='${command.isForeign}'/>" />

	<!-- header -->
	<h1><spring:message code="las.page.field.lawconsulting.advChart"/><span class="close" onclick="javascript:self.close();" title="close"></span></h1>
	<!-- //header -->
	<!-- body -->
	<div class="pop_area">
		<!-- Popup Detail -->
		<!-- Popup Size width:630px; -->
	
		  <div class="pop_content">
			<!-- bigbox -->
	 
	<div class="pop_Bbox">
		<div class="pop_Bbox_sub">
			<ul>
				<c:forEach var="list" items="${cdList}" varStatus="cnt">
					<li>
						<input type="checkbox" id="type" name="type" value="<c:out value='${list.cd}'/>" /> 
					<c:choose>
						<c:when test="${langCd=='ko'}">
							<input type="hidden" id="type_nm" name="type_nm" value="<c:out value='${list.cd_nm}'/>" /> 
							<c:out value='${list.cd_nm}' />	
						</c:when>
						<c:when test="${langCd=='fr'}">
							<input type="hidden" id="type_nm" name="type_nm" value="<c:out value='${list.cd_nm_fra}'/>" /> 
							<c:out value='${list.cd_nm_fra}' />	
						</c:when>
						<c:when test="${langCd=='de'}">
							<input type="hidden" id="type_nm" name="type_nm" value="<c:out value='${list.cd_nm_deu}'/>" /> 
							<c:out value='${list.cd_nm_deu}' />	
						</c:when>
						<c:otherwise>
								<input type="hidden" id="type_nm" name="type_nm" value="<c:out value='${list.cd_nm_eng}'/>" /> 
							<c:out value='${list.cd_nm_eng}' />	
						</c:otherwise>
					</c:choose>						
					</li>
				</c:forEach>
			</ul>
			
			
		</div>
	</div>
	
		</div>
		<!-- //Popup Detail -->
	</div>
	 
	<!-- //body -->


	</form:form>

	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp" />
	<jsp:include page="/WEB-INF/jsp/secfw/common/DlgAlert.jsp"/>
	
	<!--footer -->
<div class="pop_footer">
	<div class="btn_wrap fR" >
	<span class="btn_all_w"><span class="check"></span><a href="javascript:goInsertForm()"><spring:message code="las.page.field.lawconsulting.select"/></a></span>
		<span class="btn_all_w mR5"><span class="cancel"></span><a href="#" onclick="javascript:self.close();"><spring:message code='las.page.button.cancel' /></a></span>
	</div>
</div>
<!-- //footer -->
	
</body>
</html>
