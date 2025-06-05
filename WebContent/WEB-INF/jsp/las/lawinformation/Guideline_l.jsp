<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%@ include file= "/WEB-INF/jsp/common/common.jsp" %>
<%-- 
/**
 * 파  일  명 : Guideline_l.jsp
 * 프로그램명 : 해외계약서 GuideLine
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 2011.09
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.page.title.contract.foreign.Library" /></title>

<LINK href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/excanvas.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript">

	/**
	* 상세내역 조회
	*/
	function detailView(seqno){
		var frm = document.frm;
	    frm.seqno.value		= seqno;

		frm.target = "_self";
		frm.action = "<c:url value='/las/lawinformation/guidenedu.do' />";
		frm.method.value = "detailGuideline";

		viewHiddenProgress(true) ;
		frm.submit();		
	}	
	
	/**
	* Tab 이동
	*/
	function moveTab(){
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "/las/lawinformation/library.do";
		frm.method.value = "listLibrary";
				
		frm.submit();
	}
	
	/**
	* 메시지 처리
	*/
	function showMessage(msg) {
		if( msg != "" && msg != null && msg.length > 1 ) {
			alert(msg);
		}
	}
</script>
</head>

<body onLoad='showMessage("<c:out value='${returnMessage}'/>");'>
<!-- 
**************************** Form Setting **************************** 
-->
<form:form name="frm" id='frm' method="post" autocomplete="off"> 
	<!-- search form -->
	<input type="hidden" name="method"   value="" />
	<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
	<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>" />
	
	<!-- key Form -->
	<input type="hidden" name="info_gbn" id="info_gbn" value="<c:out value='${command.info_gbn}'/>" />
	<input type="hidden" name="seqno"    id="seqno"    value="<c:out value='${command.seqno}'/>" />
<!-- //
**************************** Form Setting **************************** 
-->
<div id="wrap">	
	<div id="container">
		<!-- Location -->
		<div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0" alt="Home" /><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->
		<!-- content -->
		<div id="content">
			<!-- title -->
			<div class="title">
				<h3><spring:message code="las.page.title.contract.foreign.Library" /></h3>
			</div>
			<!-- //title -->
			<!-- tab01 -->
			<ul class="tab_basic04">
				<li class="on"><a href="#"><spring:message code="las.page.field.lawinformation.tab.guideline"/></a></li>
				<li><a href="javascript:moveTab();"><spring:message code="las.page.field.lawinformation.tab.samplecont"/></a></li>
			</ul>
			<!-- 두줄라인 추가 --><div class="tal_topLine"></div><!-- //두줄라인 추가 -->
			<div class="sampleBox">
				<div class="sample_list">
					<ul>					
						<li><a href='javascript:detailView("1");'><spring:message code="las.page.field.lawinformation.guideline1"/></a></li>
						<li><a href='javascript:detailView("2");'><spring:message code="las.page.field.lawinformation.guideline2"/></a></li>
						<li><a href='javascript:detailView("3");'><spring:message code="las.page.field.lawinformation.guideline3"/></a></li>
						<li><a href='javascript:detailView("4");'><spring:message code="las.page.field.lawinformation.guideline4"/></a></li>
						<li><a href='javascript:detailView("5");'><spring:message code="las.page.field.lawinformation.guideline5"/></a></li>
						<li><a href='javascript:detailView("6");'><spring:message code="las.page.field.lawinformation.guideline6"/></a></li>
						<li><a href='javascript:detailView("7");'><spring:message code="las.page.field.lawinformation.guideline7"/></a></li>
						<li><a href='javascript:detailView("8");'><spring:message code="las.page.field.lawinformation.guideline8"/></a></li>
						<li><a href='javascript:detailView("9");'><spring:message code="las.page.field.lawinformation.guideline9"/></a></li>
						<li><a href='javascript:detailView("10");'><spring:message code="las.page.field.lawinformation.guideline10"/></a></li>
						<li><a href='javascript:detailView("11");'><spring:message code="las.page.field.lawinformation.guideline11"/></a></li>
						<li><a href='javascript:detailView("12");'><spring:message code="las.page.field.lawinformation.guideline12"/></a></li>
					</ul>
				</div>
			</div>
		</div>
		<!-- //content -->
		<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- // footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
</div>
</form:form>
</body>
</html>
