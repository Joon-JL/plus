<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%--
/**
 * 파  일  명 : Dimension_i.jsp
 * 프로그램명 : 계약분류관리 - 등록
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%@ include file="/WEB-INF/jsp/common/messageUtil.jsp" %>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<title><spring:message code="clm.main.title" /></title>
<link href="<%=CSS%>/las.css"    type="text/css" rel="stylesheet" />

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>

<script language="javascript">

$(document).ready(function(){
  	frmChkLenMe(frm.cd_expl,1000,'curByteExpl');
});

	function goList() {
		var frm = document.frm;
		
		frm.method.value = "listDimension";
		frm.action = "<c:url value='/clm/admin/dimension.do' />";
		frm.target = "_self";
		viewHiddenProgress(true);
		frm.submit();
	}
	
	function refresh(){
		if (confirm("<spring:message code='las.msg.alert.reset' />")) {		  	
		  	$("#cd_nm_eng").val("");
		  	$("#cd_nm_abbr_eng").val("");
		  	$("#cd_expl").val("");
		}
	}

	function save() {
		var frm = document.frm;
		if(frm.cd_nm_eng.value==""){
			alert("<spring:message code='las.page.field.admin.dimension.cd_nm' />"+"<spring:message code='las.msg.alert.isRequired'/>");
		}else{
	        if(confirm("<spring:message code='secfw.msg.ask.insert' />")){
				frm.method.value = "insertDimension";
				frm.action = "<c:url value='/clm/admin/dimension.do' />";
				frm.target = "_self";
				viewHiddenProgress(true);
				frm.submit();
			}
		}
	}
</script>
</head>
<body>

<div id="wrap">
	<!-- container -->
	<div id="container">
		<!-- location -->
		<div class="location"><IMG SRC="<%=IMAGE %>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //location -->
		
		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.title.admin.Dimension" /></h3>
		</div>
		<!-- //title -->
		
		<!-- content -->
		<div id="content">
		<div id="content_in">
					
			<form:form name="frm" id="frm" method="post" autocomplete="off">
			<input type="hidden" name="method"   	 		value="" />
			<input type="hidden" name="menu_id"  	 		value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="curPage"  	 		value="<c:out value='${command.curPage}'/>" />
			<input type="hidden" name="srch_word"  			value="<c:out value='${command.srch_word}'/>" />
			<input type="hidden" name="type_cd"     id="type_cd"     value="" />

			<!-- button -->
			<div class="btnwrap">
				<span class="btn_all_w"><span class="save"></span><a href="javascript:save()"><spring:message code='clm.page.button.save' /></a></span>
				<span class="btn_all_w"><span class="reset"></span><a href="javascript:refresh()"><spring:message code='clm.page.button.reset' /></a></span>
				<span class="btn_all_w"><span class="list"></span><a href="javascript:goList()"><spring:message code='clm.page.button.list' /></a></span>
			</div>
			<!-- //button -->
								
			<!--  view  -->
			<table class="table-style01">
				<colgroup>
					<col width="15%"/>
					<col width="40%"/>
					<col width="15%"/>
					<col width="*"/>
				</colgroup>
				<tbody>
<!-- 					<tr> -->
<!-- 						<th> -->
<%-- 							<spring:message code='las.page.field.admin.dimension.cd_nm' /> --%>
<!-- 						</th> -->
<!-- 						<td colspan="3"> -->
<%-- 					  		<span><input type="text" name="cd_nm" id="cd_nm" class="text" size="100" value="" alt="<spring:message code='las.page.field.admin.dimension.cd_nm' />" maxLength="100" required /></span> --%>
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<th> -->
<%-- 							<spring:message code='clm.page.field.admin.dimension.cd_nm_abbr' /> --%>
<!-- 						</th> -->
<!-- 						<td colspan="3"> -->
<%-- 					  		<input type="text" name="cd_nm_abbr" id="cd_nm_abbr" class="text" size="100" value="" alt="<spring:message code='clm.page.field.admin.type.cd_nm_abbr' />" maxLength="50" /> --%>
<!-- 						</td> -->
<!-- 					</tr> -->
					<tr>
						<th>
							<spring:message code='las.page.field.admin.dimension.cd_nm' />
						</th>
						<td colspan="3">
					  		<input type="text" name="cd_nm_eng" id="cd_nm_eng" class="text_full" value="" alt="<spring:message code='las.page.field.admin.dimension.cd_nm' />" maxLength="100" />
						</td>
					</tr>
					<tr>
						<th>
							<spring:message code='clm.page.field.admin.dimension.cd_nm_abbr' />
						</th>
						<td colspan="3">
					  		<input type="text" name="cd_nm_abbr_eng" id="cd_nm_abbr_eng" class="text_full" value="" alt="<spring:message code='clm.page.field.admin.type.cd_nm_abbr' />" maxLength="50" />
						</td>
					</tr>
					<tr>
						<th>
							<spring:message code='clm.page.field.admin.dimension.reg_dt' />
						</th>
						<td>
							<%=DateUtil.getTime("yyyy-MM-dd")%>
						</td>
						<th>
							<spring:message code='clm.page.field.admin.dimension.registrant' />
						</th>
						<td>
							<c:out value='${command.session_user_nm}'/>/<c:out value='${command.session_dept_nm}'/>
						</td>
					</tr>
					<tr class="end">
						<th>
							<spring:message code='clm.page.field.admin.dimension.cd_expl' />
						</th>
						<td colspan="3">
							<span id="curByteExpl">0</span>/<spring:message code="clm.page.msg.common.max1000byte" /><br>
							<textarea name="cd_expl" id="cd_expl" rows="10" maxLength="1000" onkeyup="frmChkLenMe(this,1000,'curByteExpl')" class="text_area_full"></textarea>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- //view  -->
			
			<!-- button -->
			<div class="btnwrap mt20">
				<span class="btn_all_w"><span class="save"></span><a href="javascript:save()"><spring:message code='clm.page.button.save' /></a></span>
				<span class="btn_all_w"><span class="reset"></span><a href="javascript:refresh()"><spring:message code='clm.page.button.reset' /></a></span>
				<span class="btn_all_w"><span class="list"></span><a href="javascript:goList()"><spring:message code='clm.page.button.list' /></a></span>
			</div>
			<!-- //button -->
			</form:form>
		</div>
		</div>
		<!-- //content -->
		
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
<!-- footer -->
<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
<!-- //footer -->
</body>
</html>