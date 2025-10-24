<!DOCTYPE html>
<%@page import="com.sec.clm.admin.dto.RegulationForm"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%-- 
/**
 * 파  일  명 : Regulation_d.jsp
 * 프로그램명 : 프로세스 & 규정 지침서 상세
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	RegulationForm command = (RegulationForm)request.getAttribute("command");
	ListOrderedMap lom = (ListOrderedMap)request.getAttribute("lom"); 
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet" />
<!-- style -->
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
		frm.action = "<c:url value='/clm/admin/regulation.do' />";
		if(flag == "modify"){
			viewHiddenProgress(true) ;
		    frm.method.value = "forwardModifyRegulation";
			frm.submit();

		} else if(flag == "delete"){	
			if(confirm("<spring:message code='secfw.msg.ask.delete' />")){
				viewHiddenProgress(true) ;
			    frm.method.value = "deleteRegulation";
			    frm.submit();
			}	
		} else if(flag == "list"){	
			viewHiddenProgress(true) ;
		    frm.method.value = "listRegulation";
			frm.submit();
		}
	}
	
	/**
	* 메시지 처리
	*/
	function showMessage(msg){
		if( msg != "" && msg != null && msg.length > 1 ) {
			alert(msg);
		}
	}
	
	$(document).ready(function(){
		//첨부파일창 load
		initPage();
	});
	
	function initPage(){
		var frm = document.frm;
		
	    frm.target			= "fileList";
	    frm.action 			= "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value  	= "forwardClmsFilePage";
		getClmsFilePageCheck('frm');
	}	
	
//-->

</script>

</head>
<body onLoad='showMessage("<c:out value='${returnMessage}'/>");'>

<div id="wrap">
	<!-- container -->
	<div id="container">	
	
		<!-- Location -->
		<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->
	
		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.title.regulation.listTitle" /></h3>
		</div>
		<!-- //title -->
	
		<!-- content -->
		<div id="content">
		<div id="content_in">
			<!-- **************************** Form Setting **************************** -->
			<form:form name="frm" id="frm" method="post" autocomplete="off">
			
			<!-- search form -->
			<input type="hidden" name="method"   value="" />
			<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />
			<!-- key form-->
			<input type="hidden" name="rule_no"	 value="<c:out value='${command.rule_no}'/>" />
			<!-- 첨부파일정보 -->
			<input type="hidden" name="fileInfos" 	value="" />
			<input type="hidden" name="file_bigclsfcn" 	value="F015" />
			<input type="hidden" name="file_midclsfcn" 	value="" />
			<input type="hidden" name="file_smlclsfcn" 	value="" />
			<input type="hidden" name="ref_key" 	value="" />
			<input type="hidden" name="view_gbn" 	value="download" />

			<!-- // **************************** Form Setting **************************** -->
			
			<div class="btnwrap">
				<span class="btn_all_w"><span class="edit"></span><a href="javascript:pageAction('modify');"><spring:message code="secfw.page.button.modify" /></a></span>
				<span class="btn_all_w"><span class="delete"></span><a href="javascript:pageAction('delete');"><spring:message code="secfw.page.button.delete" /></a></span>
				<span class="btn_all_w"><span class="list"></span><a href="javascript:pageAction('list');"><spring:message code="secfw.page.button.list" /></a></span>
			</div>
			
			<!-- view -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
					<col width="100" />
					<col width="720" />
				</colgroup>
				<tbody>
					<tr>
						<th><spring:message code="clm.page.field.regulation.ruleTitle" /></th>
						<td><%=lom.get("rule_title_en")%></td>
						
<%-- 						<%if("ko".equals(command.getSession_user_locale()) || command.isAuth_modify() ){ %> --%>
<%-- 							<td><%=lom.get("rule_title")%></td> --%>
<%-- 						<%}else if("en".equals(command.getSession_user_locale()) || command.isAuth_modify() ){ %> --%>
<%-- 							<td><%=lom.get("rule_title_en")%></td> --%>
<%-- 						<%}else{%> --%>
<%-- 							<td><%=lom.get("rule_title_en")%></td> --%>
<%-- 							<%}%> --%>
					</tr>
					<tr>
					    <th><spring:message code="clm.page.field.regulation.regNm"/></th>
					    <td><%=StringUtil.convertNamoCharsToHtml((String)lom.get("reg_nm"))%></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.regulation.ruleCont" /></th>
						<td><%=StringUtil.convertEnterToBR((String)lom.get("rule_cont_en"))%></td>
						
<%-- 						<%if("ko".equals(command.getSession_user_locale()) || command.isAuth_modify() ){ %> --%>
<%-- 							<td><%=StringUtil.convertNamoCharsToHtml((String)lom.get("rule_cont"))%></td> --%>
<%-- 						<%}else if("en".equals(command.getSession_user_locale()) || command.isAuth_modify() ){ %> --%>
<%-- 							<td><%=StringUtil.convertNamoCharsToHtml((String)lom.get("rule_cont_en"))%></td> --%>
<%-- 						<%}else{ %> --%>
<%-- 							<td><%=StringUtil.convertNamoCharsToHtml((String)lom.get("rule_cont_en"))%></td> --%>
<%-- 						<% } %> --%>
					</tr>
					<tr class="end">
		            	<th><spring:message code="secfw.page.field.bbs.file" /></th>
		            	<td>
		            	<iframe src="<c:url value='/clm/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="100px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
		            	</td>
		          	</tr>
				</tbody>
			</table>
			<!-- //view -->
	
			<div class="btnwrap mt20">
				<span class="btn_all_w"><span class="edit"></span><a href="javascript:pageAction('modify');"><spring:message code="secfw.page.button.modify" /></a></span>
				<span class="btn_all_w"><span class="delete"></span><a href="javascript:pageAction('delete');"><spring:message code="secfw.page.button.delete" /></a></span>
				<span class="btn_all_w"><span class="list"></span><a href="javascript:pageAction('list');"><spring:message code="secfw.page.button.list" /></a></span>
			</div>
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