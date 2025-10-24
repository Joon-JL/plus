<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.draft.dto.CustomerTestForm" %>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.List" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%-- 
/**
 * 파  일  명 : CustomerView_d.jsp
 * 프로그램명 :  CustomerView
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2011.11.14
 */
--%>
<%
	String menuNavi          = (String)request.getAttribute("secfw.menuNavi");
    CustomerTestForm command = (CustomerTestForm)request.getAttribute("command");
	ListOrderedMap lom       = (ListOrderedMap)request.getAttribute("lom"); 

%>	

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<LINK href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet">
<LINK href="<%=CSS%>/common.css"   type="text/css" rel="stylesheet">

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript">

	function pageAction(flag){
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/draft/customerTest.do' />";
		if(flag == "list"){	
			viewHiddenProgress(true) ;
		    frm.method.value = "detailCustomerShare";
			frm.submit();
		}
	}
	
	function exit(){
		window.close();
	}

</script>

</head>
<body>

<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id="frm" method="post" autocomplete="off">

<!-- search form -->
<input type="hidden" name="method"   value="">
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>">

<!-- // **************************** Form Setting **************************** -->
<div id="wrap">  
	<!-- container -->
	<div id="container">	
		<!-- Location -->
		<div class="location"></div>
		<!-- //Location -->
		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.field.share.customerDetail" /></h3>
		</div>
		<!-- //title -->
		<!-- content -->
		<div id="content">
			<!-- view -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
			<colgroup>
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
			</colgroup>
			<tbody>
				<tr><!-- 업체명, 사업분야 -->
					<th><spring:message code="clm.page.field.customer.customerNm1"/></th>
					<td><c:out value='${lom.customer_nm1}' /></td>
					<th><spring:message code="clm.page.field.customer.repNm"/></th>
					<td><c:out value='${lom.rep_nm}' /></td>
					<th><spring:message code="clm.page.field.customer.telephone1"/></th>
				    <td><c:out value='${lom.telephone1}' /></td>
				</tr>
				<tr>
					<th><spring:message code="clm.page.field.customer.gerpCode"/></th>
					<td><c:out value='${lom.gerp_cd}' /></td>
					<th><spring:message code="clm.page.field.customer.vendorType"/></th>
					<td><c:out value='${lom.vendor_type_nm}' /></td>
					<!-- 2014-03-20 Kevin 필수 계약 체결 여부 -->
					<th><spring:message code="clm.page.field.customer.IsContractRequired" /></th>
					<td><c:out value='${lom.mandatory_desc}' /></td>
				</tr>
				<tr><!-- 대표자, 사업자등록번호, email -->
					<th><spring:message code="clm.page.field.customer.registerNo"/></th>
					<td><c:out value='${lom.tax_no}' /></td>
					<th><spring:message code="clm.page.field.customer.vatno" /></th>
					<td><c:out value='${lom.vat_no}' /></td>
					<th><spring:message code="clm.page.field.customer.Email"/></th>
					<td><c:out value='${lom.email}' /></td>
				</tr>
				<tr><!-- 국가, 주, 도시 -->
					<th><spring:message code="clm.page.field.customer.nation"/></th>
					<td><c:out value='${lom.nation_nm}' /></td>
					<th><spring:message code="clm.page.field.customer.stapr"/></th>
					<td><c:out value='${lom.stapr} ${ lom.cityn}' /></td>
					<th><spring:message code="clm.page.field.customer.postalcode"/></th>
				    <td><c:out value='${lom.postalcode}' /></td>
				</tr>
				<tr><!-- 주소 -->
					<th><spring:message code="clm.page.field.customer.street"/></th>
					<td colspan="5"><c:out value='${lom.street}' /></td>
				</tr>
			</tbody>
		</table>
			<!-- //view -->
	
			<div class="btn_wrap">
				<span class="btn_all_w"><span class="cancel"></span><a href="javascript:exit();"><spring:message code="clm.page.msg.common.close" /></a></span>
			</div>
		</div>
		<!-- //content -->
        
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
</form:form>
</body>
</html>