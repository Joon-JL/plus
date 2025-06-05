<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.draft.dto.LibReviewForm" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%@ include file= "/WEB-INF/jsp/common/common.jsp" %>
<%-- 
/**
 * 파  일  명 : LibReview_l.jsp
 * 프로그램명 : 계약서 Library 유형별조회 목록
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<!-- style -->
<link href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet" />
<link href="<%=CSS%>/popup.css"   type="text/css" rel="stylesheet" />

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/excanvas.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>

<script language="javascript">

	/**
	* 페이징 function
	*/
	function goPage(pgNum){
		var frm = document.frm;

		viewHiddenProgress(true) ;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/draft/libReview.do' />";
		frm.method.value = "listLibReview";
		frm.curPage.value = pgNum;
		
		frm.submit();
	}
  	
	/**
	* 상세내역 조회
	*/
	function detailView(lib_no){
		var frm = document.frm;
		
		viewHiddenProgress(true) ;
		
	    frm.lib_no.value	= lib_no;

		frm.target = "_self";
		frm.action = "<c:url value='/clm/draft/libReview.do' />";
		frm.method.value = "detailLibReview";
		
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

<body onload='showMessage("<c:out value='${returnMessage}'/>");'>
<!-- 

**************************** Form Setting **************************** 
-->
<form:form name="frm" id='frm' method="post" autocomplete="off"> 
	<!-- search form -->
	<input type="hidden" name="method"   value="" />
	<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
	<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>" />
	<input type="hidden" name="srch_biz_clsfcn"            id="srch_biz_clsfcn"             value="<c:out value='${command.srch_biz_clsfcn}'/>" />
	<input type="hidden" name="srch_depth_clsfcn"          id="srch_depth_clsfcn"           value="<c:out value='${command.srch_depth_clsfcn}'/>" />
	<input type="hidden" name="srch_cnclsnpurps_bigclsfcn" id="srch_cnclsnpurps_bigclsfcn"  value="<c:out value='${command.srch_cnclsnpurps_bigclsfcn}'/>" />
	<input type="hidden" name="srch_cnclsnpurps_midclsfcn" id="srch_cnclsnpurps_midclsfcn"  value="<c:out value='${command.srch_cnclsnpurps_midclsfcn}'/>" />
	<!-- key Form -->
	<input type="hidden" name="lib_no"     id="lib_no"     value="<c:out value='${command.lib_no}'/>" />
	<input type="hidden" name="lib_gbn"    id="lib_gbn"    value="<c:out value='${command.lib_gbn}'/>" />
	<input type="hidden" name="region_gbn" id="region_gbn" value="<c:out value='${command.region_gbn}'/>" />

<!-- //
**************************** Form Setting **************************** 
-->
<div id="wrap">
	<!-- container -->
	<div id="container">

		<!-- Location -->
		<div class="location">
			<img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/>
		</div>
		<!-- //Location -->

		<!-- Title -->
		<div class="title">
			<h3><spring:message code="clm.page.title.libreview.listTitle" /></h3>
		</div>
		<!-- //title -->

		<!-- content -->
		<div id="content">
			<!-- List -->
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
				<colgroup>
					<col width="50px"/>
					<col width="90px"/>
					<col width="110px"/>
					<col width="75px"/>
					<col width="230px"/>
					<col width="240px"/>
					<col width="60px"/>
					<col width="60px"/>
				</colgroup>
				<thead>
					<tr>
					<!-- 번호 --><th><spring:message code="clm.page.field.libreview.no" /></th>
					<!-- 용도 --><th><spring:message code="clm.page.field.libreview.lib_gbn" /></th>
					<!-- 비즈니스 --><th><spring:message code="clm.page.field.libreview.biz_clsfcn" /></th>
					<!-- 계약단계 --><th><spring:message code="clm.page.field.libreview.depth_clsfcn" /></th>
					<!-- 계약유형--><th><spring:message code="clm.page.field.libreview.cnclsnpurps_bigclsfcn" /></th>
					<!-- 제목--><th><spring:message code="clm.page.field.libreview.title" /></th>
					<!-- 등록자--><th><spring:message code="clm.page.field.libreview.reg_nm" /></th>
					<!-- 조회수--><th><spring:message code="clm.page.field.libreview.rdcnt" /></th>
					</tr>
				</thead>
				<tbody>
			<c:choose>
				<c:when test="${pageUtil.totalRow > 0}">
					<c:forEach var="list" items="${lom}">
					<tr onMouseOut="this.className='';" onMouseOver="this.className='on';this.style.cursor='pointer'" onClick='javascript:detailView("<c:out value='${list.lib_no}'/>");'>
					    <td><c:out value='${list.rn}'/></td>
			            <td class="txtover" title="<c:out value='${list.lib_gbnnm}'/>"><nobr><c:out value='${list.lib_gbnnm}'/></nobr></td>
			            <td class="txtover" title="<c:out value='${list.biz_clsfcnnm}'/>"><nobr><c:out value='${list.biz_clsfcnnm}'/></nobr></td>
			            <td><c:out value='${list.depth_clsfcnnm}'/></td>
			            <td class="tL txtover" title="<c:out value='${list.cnclsnpurps_path}'/>"><nobr><c:out value='${list.cnclsnpurps_path}'/></nobr></td>				   
						<td class="tL txtover" title="<c:out value='${list.title}'/>"><nobr><c:out value='${list.title}'/></nobr></td>
						<td><c:out value='${list.reg_nm}'/></td>
						<td><c:out value='${list.rdcnt}'/></td>
					</tr>
					</c:forEach>
			    </c:when>
			    <c:otherwise>
					<tr class="end">
						<td colspan="8">NO</td>
					</tr>
			    </c:otherwise>
			</c:choose>
	        </table>
			<!-- //List -->
		
			<div class="t_titBtn">	
				<!-- total data -->
				<div class="total_num">
					<spring:message code="secfw.page.field.common.totalData" />: <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%><spring:message code="clm.page.msg.common.case" />
				</div>
				<!-- //total data -->
				
				<!-- pagination  -->
				<div class="pagination">
				<%=pageUtil.getPageNavi(pageUtil, "") %>
				</div>
				<!-- //pagination -->
			</div>
		</div>
		<!-- //content -->
		
		<!-- footer -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- //footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
</form:form>
</body>
</html>

