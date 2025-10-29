<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%--
/**
 * 파  일  명 : Consideration_addLegalOpinion_p.jsp
 * 프로그램명 : 추가 검토의견 입력 팝업
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2014.03.31
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="x-ua-compatible" content="IE=edge" >
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css"    type="text/css" rel="stylesheet">
<link href="<%=CSS%>/popup.css" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="/script/secfw/jquery/calendar/jquery.mousewheel.min.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>

<script type="text/javascript" src="/crosseditor/js/namo_scripteditor.js"></script>

</head>
<body class="pop" >

<!-- header -->
<h1><spring:message code="las.jsp.field.lawconsult.lasopnn"/> <spring:message code="las.page.field.lawconsulting.update"/> <spring:message code="las.page.field.statistics.return"/><!-- Close --><span class="close" onclick="javascript:self.close();" title="close"></span></h1>
<!-- //header -->
<div class="pop_area">
<!-- Popup Detail -->
		
		<!-- pop_content -->
		<div class="pop_content" style='height:544px'>
		<!-- **************************** Form Setting **************************** -->
			<form:form name="frm" id='frm' method="post" autocomplete="off"> 
				<!-- search form -->
				<input type="hidden" name="method"   value="" />
				
			<!-- // **************************** Form Setting **************************** -->
			<!-- list -->
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic mt20">
				<colgroup>
				    <col width="10"/>
					<col width="10"/>
			  	</colgroup>
			  	<thead>
					<tr>
						<th><spring:message code="clm.page.field.lawterms.rdcnt"/></th>
						<th><spring:message code="clm.common.checkPageListTitle"/></th>
					</tr>
				</thead>
			 	<tbody>
			 	<c:choose>
				 	<c:when test="${resultListSize > 0 }">
				 	    <c:forEach var="chlist" items="${resultList}" varStatus="i">
					        <tr class="end">
					          <td class='tC'><c:out value='${chlist.no }'/> </td>
					          <td><c:out value='${chlist.cd_nm }'/></td>
					        </tr>
				        </c:forEach>
				    </c:when>
				    <c:otherwise>
				        <tr>
						  <td colspan="2" class="td_ac" align="center"><spring:message code="secfw.msg.succ.noResult" /></td>
						</tr>
				    </c:otherwise>
			    </c:choose>
			    
					
							
			  </tbody>
			</table>
			<!-- //list -->
		</form:form>
</div>
</div>
<!--footer -->
<div class="pop_footer">
	<!-- button -->
	 <div class="btn_wrap fR">
	     <span class="btn_all_w" onclick="javascript:self.close();"><span class="cancel"></span><a><spring:message code="clm.page.msg.common.close" /></a></span>
	 </div>
<!-- //button -->
</div>
<!-- //footer -->
<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</body>
</html>