<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.share.dto.ContractAttentionForm" %>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%-- 
/**
 * 파  일  명 : ContractAttention_d.jsp
 * 프로그램명 : 계약 체결시 유의사항
 * 설      명 : 
 * 작  성  자 : 유영섭 
 * 작  성  일 : 2011.09.20
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
    ContractAttentionForm command = (ContractAttentionForm)request.getAttribute("command");
	ListOrderedMap lom = (ListOrderedMap)request.getAttribute("lom"); 

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
<!--
	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/share/ContractAttention.do' />";
		if(flag == "list"){	
			viewHiddenProgress(true) ;
		    frm.method.value = "listContractAttention";
			frm.submit();
		}
	}
	
//-->

	function modify() {
		viewHiddenProgress(true) ;
		var frm = document.frm ;
		frm.method.value = "forwardContractAttentionModify" ;
		frm.action = "<c:url value='/clm/share/ContractAttention.do' />";
		frm.target = "_self" ;
		frm.submit() ;
	}
	
	function remove(){
		if(confirm("<spring:message code="clm.page.msg.common.announce003" />")) {
			viewHiddenProgress(true) ;
			var frm = document.frm ;
			frm.method.value = "deleteContractAttention" ;
			frm.action = "<c:url value='/clm/share/ContractAttention.do' />";
			frm.target = "_self" ;
			frm.submit() ;
		}
	}


</script>

</head>
<body onLoad=''>

<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id="frm" method="post" autocomplete="off">

<!-- search form -->
<input type="hidden" name="method"   value="">
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>">


<!-- key form-->
<input type="hidden" name="mtbat_no" value="<c:out value='${command.mtbat_no}'/>" />

<input type="hidden" name="type_gbn"  value="<c:out value='${lom.type_gbn}'/>">
<input type="hidden" name="type_bigclsfcn"  value="<c:out value='${lom.type_bigclsfcn}'/>">
<input type="hidden" name="type_midclsfcn"  value="<c:out value='${lom.type_midclsfcn}'/>">



<!-- // **************************** Form Setting **************************** -->
<div id="wrap">  

	
	<!-- container -->
	<div id="container">	
		<!-- Location -->
		<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->
		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.title.contractattention.listTitle" /></h3>
		</div>
		<!-- //title -->
		<!-- content -->
		<div id="content">
			<!-- view -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
					<col class="tal_w04" />
					<col />
					<col class="tal_w04" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th><spring:message code="clm.page.field.contractattention.exam" /></th>
						<td colspan="3"><c:out value='${lom.type_gbn_nm}' /></td>
						
						
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.contractattention.title" /></th>
						<td colspan="3"><c:out value='${lom.title}' /></td>
					
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.contractattention.regNm" /></th>
						<td colspan="1"><c:out value='${lom.reg_nm}' /></td>
			             
			            <th><spring:message code="clm.page.field.contractattention.regdt" /></th>
						<td><c:out value='${lom.reg_dt}' /></td>
							  
						  
						
							
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.contractattention.cont" /></th>
						<td colspan="3">
							<c:out value='${lom.cont}' escapeXml="false" />
						</td>
					</tr>
				</tbody>
			</table>
			<!-- //view -->
	
			<div class="btn_wrap">
				<%if(command.isAuth_modify()){ %>
				<span class="btn_all_w"><span class="edit"></span><a href="javascript:modify();"><spring:message code="las.page.button.modify" /></a></span>
				<%} %>
				<%if(command.isAuth_delete()){ %>
				<span class="btn_all_w"><span class="delete"></span><a href="javascript:remove();"><spring:message code="las.page.button.delete" /></a></span>
				<%} %>
				<span class="btn_all_w"><span class="list"></span><a href="javascript:pageAction('list');"><spring:message code="secfw.page.button.list" /></a></span>
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