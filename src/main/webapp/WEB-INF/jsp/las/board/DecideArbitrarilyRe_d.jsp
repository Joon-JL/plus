<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>
<%@ page import="com.sec.las.board.dto.NoticeForm" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%--
/**
 * 파  일  명 : DecideArbitrarilyRe_d.jsp
 * 프로그램명 : 전결규정 상세
 * 설      명 : 	기존 CLM의 전결규정의 목록타입과 다르게
 				각사별로 ADMIN-전결규정 입력폼 -> 법무관련규정 단건 상세 조회 방식으로 구현
 				상세조회
 				Controller : NoticeController (TN_LAS_ANNOUNCE 테이블 사용) 
 * 작  성  자 : 황민우
 * 작  성  일 : 2013.05.20
 */
 --%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
	NoticeForm command = (NoticeForm)request.getAttribute("command");
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>

<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript">
	/**
	* - Namo Editor 세팅 
	*/
	$(document).ready(function(){
		var frm = document.frm;
		
		//첨부파일창 load
	    frm.target = "fileList";
	    frm.action = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value = "forwardClmsFilePage";
		
		frm.submit();
	});

</script>
</head>
<body>

<div id="wrap">	
	<div id="container">
		<!-- location -->
		<div class="location"><IMG SRC="<%=IMAGE %>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //location -->
		<!-- title -->			
		<div class="title">
			<h3><spring:message code="clm.page.title.decidearbitrarilyre.mainTitle" /></h3>
		</div>
		<!-- //title -->
		
		<!-- content -->
		<div id="content"> 
		<div id="content_in">
			<form:form name="frm" id="frm" autocomplete="off">
			<input type="hidden" name="method"   	 	value="" />
			<input type="hidden" name="menu_id"  	 	value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="curPage"  	 	value="<c:out value='${command.curPage}'/>" />
			<input type="hidden" name="srch_word"		value="<c:out value='${command.srch_word}'/>" />
			<input type="hidden" name="srch_combo" 		value="<c:out value='${command.srch_combo}'/>" />
			<input type="hidden" name="srch_start_dt" 	value="<c:out value='${command.srch_start_dt}'/>" />
			<input type="hidden" name="srch_end_dt" 	value="<c:out value='${command.srch_end_dt}'/>" />
			
			<input type="hidden" name="annce_knd" id="annce_knd" value="<c:out value='${command.annce_knd}'/>" />
			<input type="hidden" name="seqno"     id="seqno"     value="<c:out value='${command.seqno}'/>" />
			
			<!-- 첨부파일정보 -->
			<input type="hidden" name="fileInfos" 		value="" />
			<input type="hidden" name="file_bigclsfcn" 	value="F007" />
			<input type="hidden" name="file_midclsfcn" 	value="<c:out value='${command.annce_knd}'/>" />
			<input type="hidden" name="file_smlclsfcn" 	value="" />
			<input type="hidden" name="ref_key" 		value="<c:out value='${command.seqno}'/>" />
			<input type="hidden" name="view_gbn" 		value="download" />
			
			<!-- //**************************** Form Setting **************************** -->
					
			<!--  view  -->
			<c:choose>
			<c:when test="${lom.seqno>0}">
				<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
					<colgroup>
						<col width="15%"/>
						<col width="85%"/>
					</colgroup>
					<tbody>				
<%-- 						<%if("ko".equals(command.getSession_user_locale()) || command.isAuth_modify() ){ %> --%>
<!-- 							<tr> -->
<%-- 							<th><spring:message code='las.page.field.board.notice.title' /><c:if test="${command.auth_modify}"> (Ko)</c:if></th> --%>
<!-- 				            <td colspan="3"> -->
<%-- 				            	<c:out value='${lom.title}' default="" /> --%>
<!-- 				            </td> -->
<!-- 				        	</tr> -->
<%-- 				        <%} %> --%>
<%-- 				        <%if("en".equals(command.getSession_user_locale()) || command.isAuth_modify() ){ %> --%>
				        <%if(command.isAuth_modify() ){ %>
						<tr>
							<th><spring:message code='las.page.field.board.notice.title' /><c:if test="${command.auth_modify}"></c:if></th>
				            <td colspan="3">
				            	<c:out value='${lom.title_en}' default="" />
				            </td>
				        </tr>
				        <%}else{ %>
				        <tr>
							<th><spring:message code='las.page.field.board.notice.title' /></th>
				            <td colspan="3">
				            	<c:out value='${lom.title_en}' default="" />
				            </td>
				        </tr>
				        <%} %>
						
<%-- 						<%if("ko".equals(command.getSession_user_locale()) || command.isAuth_modify() ){ %> --%>
<!-- 							<tr> -->
<%-- 							<th><spring:message code="clm.page.msg.common.content" /><c:if test="${command.auth_modify}"> (Ko)</c:if></th> --%>
<!-- 							<td colspan="3"> -->
<%-- 								<c:out value='${lom.cont}' escapeXml="false" />						 --%>
<!-- 							</td> -->
<!-- 							</tr> -->
<%-- 						<%} %> --%>
<%-- 						<%if("en".equals(command.getSession_user_locale()) || command.isAuth_modify() ){ %> --%>
						<%if(command.isAuth_modify() ){ %>
						<tr>
							<th><spring:message code="clm.page.msg.common.content" /><c:if test="${command.auth_modify}"></c:if></th>
							<td colspan="3">
								<c:out value='${lom.cont_en}' escapeXml="false" />						
							</td>
						</tr>
						<%}else{ %>
						<tr>
							<th><spring:message code="clm.page.msg.common.content" /></th>
							<td colspan="3">
								<c:out value='${lom.cont_en}' escapeXml="false" />						
							</td>
						</tr>
						<%} %>
						<tr>
			            	<th><spring:message code='las.page.field.board.notice.attach_file' /></th>
			            	<td colspan="3">
			            		<!-- Sungwoo Replaced scroll option 2014/08/04 -->
			            		<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe>
			            	</td>
			          	</tr>
					</tbody>
				</table>
			<!-- //view  -->
			</c:when>
			<c:otherwise>
				<span class="tC"><h3><spring:message code="clm.page.msg.rule.announce028" /></h3></span>
           		<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="100px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
			</c:otherwise>
			</c:choose>
			</form:form>
		</div>
		</div>
		<!-- //content -->			
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
		<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- // footer -->

</body>
</html>