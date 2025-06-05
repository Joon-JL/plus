<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.admin.dto.RegulationForm" %>

<%@page import="com.sds.secframework.common.util.Token"%>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%--
/**
 * 파  일  명 : RegulationTemp_i.jsp
 * 프로그램명 : 규정 및 프로세스 임시  등록
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
 --%>
<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<link href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet" />

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>

<script language="javascript">

	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/admin/regulation.do' />";
		//저장
		if(flag == "insert"){
			if(confirm("<spring:message code='secfw.msg.ask.insert' />")){
				
			    frm.method.value = "insertTempRegulation";
			    
			    //나모웹에디터 관련
			    frm.rule_cont.value = frm.wec.MIMEValue;
				
				viewHiddenProgress(true) ;
				
				frm.submit();
			}

		}
	}

</script>

</head>
<body>
<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id="frm" method="post" autocomplete="off">

<!-- search form -->
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />

<!-- key form-->
<input type="hidden" name="rule_cont"	value="" />

<!-- // **************************** Form Setting **************************** -->

<div>
	<!-- container -->
	<div id="container">	

		<!-- Location -->
		<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->
	
		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.msg.admin.tsaveRuleNProc" /></h3>
		</div>
		<!-- //title -->
		
		<!-- content -->
		<div id="content">
			<!-- view -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
					<col width="100" />
					<col width="310"/>
					<col width="100" />
					<col width="310"/>
				</colgroup>
				<tbody>
					<tr>
						<th><spring:message code="clm.page.msg.admin.ruleNo" /><span class="astro">*</span></th>
						<td>
							<input type="text" name="rule_no" id=""rule_no"" style="width:50px" value="<c:out value='${command.rule_no}'/>"/>
						</td>
						<th><spring:message code="clm.page.msg.admin.dnq" /></th>
						<td>
							<spring:message code="clm.page.msg.admin.highRule" /><input type="text" name="up_rule_no" id="up_rule_no" style="width:50px" value="" />
							Depth<input type="text" name="rule_depth" id="rule_depth" style="width:50px" value=""/>
							<spring:message code="clm.page.msg.common.sequence" /><input type="text" name="rule_srt" id="rule_srt" style="width:50px" value=""/>
						</td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.common.title" /></th>
						<td colspan="3">
							<input type="text" name="rule_title" id="rule_title" style="width:300px" value=""/>
						</td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.common.content" /></th>
						<td  colspan="3">
							<%@ include file="/WEB-INF/jsp/secfw/common/NamoEditInclude.jspf"%>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- //view -->
	
			<div class="btn_wrap">
				<span class="btn_all_b"><span class="save"></span><a href="javascript:pageAction('insert');"><spring:message code="secfw.page.button.insert" /></a></span>
			</div>
		</div>
		<!-- //content -->
		<!-- footer  -->
	
		<!-- // footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
</form:form>
</body>
<!-- 나모 웹에디터 관련 추가 -->
<SCRIPT language="javascript" FOR="wec" EVENT="OnInitCompleted()">
	var wecObj = document.wec;
	wecObj.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
	wecObj.SetDefaultFontSize("9");
	wecObj.EditMode = 1;
</SCRIPT>
</html>
