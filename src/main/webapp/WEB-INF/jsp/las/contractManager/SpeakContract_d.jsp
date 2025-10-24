<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@page import="com.sds.secframework.common.util.PageUtil"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%@page import="com.sds.secframework.common.util.Token"%>


<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.main.title" /></title>
<LINK href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet">

<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/ui.core.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>

<script language="javascript">
	function init(){
		alertMessage('<c:out value='${command.return_message}'/>') ;
	}

	function goList() {
		viewHiddenProgress(true) ;
		var f = document.frm ;
		f.method.value = "listSpeakContract" ;
		f.action = "<c:url value='/las/contractmanager/speakContract.do' />" ;
		f.target = "_self" ;
		f.submit() ;
	}
	
	function remove(){
		if(confirm("<spring:message code="secfw.msg.ask.delete" />")) {
			viewHiddenProgress(true) ;
			var f = document.frm ;
			f.method.value = "deleteSpeakContract" ;
			f.action = "<c:url value='/las/contractmanager/speakContract.do' />" ;
			f.target = "_self" ;
			f.submit() ;
		}
	}

	function modify() {
		viewHiddenProgress(true) ;
		var f = document.frm ;
		f.method.value = "forwardModifySpeakContract" ;
		f.action = "<c:url value='/las/contractmanager/speakContract.do' />" ;
		f.target = "_self" ;
		f.submit() ;
	}
	
	function initPage(){
	    frm.target			= "fileList";
	    frm.action = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value  	= "forwardClmsFilePage";
		getClmsFilePageCheck('frm');
	}	
	
</script>


</head>
<body onLoad="alertMessage('<c:out value='${command.return_message}'/>');initPage();">
<form:form name="frm" id="frm" autocomplete="off">
<input type="hidden" name="method" value=""/>
<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>"/>
<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>"/>
<input type="hidden" name="srch_title" value="<c:out value='${command.srch_title}'/>">
<input type="hidden" name="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}'/>">
<input type="hidden" name="srch_respman_id" value="<c:out value='${command.srch_respman_id}'/>"/>
<input type="hidden" name="srch_start_ymd" value="<c:out value='${command.srch_start_ymd}'/>">
<input type="hidden" name="srch_end_ymd" value="<c:out value='${command.srch_end_ymd}'/>">
<input type="hidden" name="row_cnt" value="<c:out value='${command.row_cnt}'/>">
<input type="hidden" name="seqno" value="<c:out value='${command.seqno}'/>"/>
<input type="hidden" name="isForeign" value="<c:out value='${command.isForeign}'/>"/>

<!-- 첨부파일정보 -->
<input type="hidden" name="fileInfos" 	value="" />
<input type="hidden" name="file_bigclsfcn" 	value="F002" />
<input type="hidden" name="file_midclsfcn" 	value="F00201" />
<input type="hidden" name="file_smlclsfcn" 	value="" />
<input type="hidden" name="ref_key" 	value="<c:out value='${command.seqno}'/>" />
<input type="hidden" name="view_gbn" 	value="download" />

<!-- 이중등록 방지 -->
<input type="hidden" name="TOKEN"  value="<%=Token.set(request) %>" />

<div id="wrap">
<!-- container -->
<div id="container">	
	<!-- Location -->
        <div class="location">
            <img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/>
        </div>
    <!-- //Location -->
 
	<!-- content -->
	<div id="content">
 	<div class="title">
			<h3><spring:message code="las.page.field.speakcontract.main_title" /></h3>
	</div>
	
	<!-- title -->
	<div class="t_titBtn">
		<div class="btn_wrap">
			<c:if test="${command.auth_modify}">
			<span class="btn_all_w">
				<span class="edit"></span>
				<a href="javascript:modify()"><spring:message code="las.page.button.modify" /></a>
			</span>
			</c:if>
					
			<c:if test="${command.auth_delete}">
			<span class="btn_all_w">
				<span class="delete"></span>
				<a href="javascript:remove()"><spring:message code="las.page.button.delete" /></a>
			</span>
			</c:if>		
		</div>
		<!-- //button -->
	</div>
	<!-- //title -->
	
<!-- list -->
				<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
					<colgroup>
						<col width="60px"/>
						<col width="/"/>
						<col width="60px"/>
						<col width="/"/>
					</colgroup>
				<tr>
				 	<th><spring:message code="las.page.field.speakcontract.reqman_nm" /></th>
					<td>
						<c:out value="${lom.reqman_nm}"/>
					</td>
					<th><spring:message code="las.page.field.speakcontract.reqman_dept_nm" /></th>
					<td>
						<c:out value="${lom.reqman_dept_nm}"/>
					</td>
				</tr>
				
				<tr>
				 	<th><spring:message code="las.page.field.speakcontract.reg_dt" /></th>
					<td>
						<c:out value="${lom.reg_dt}"/>
					</td>
					<th><spring:message code="las.page.field.speakcontract.respman_nm" /></th>
					<td>
						<c:out value="${lom.respman_nm}"/>
					</td>
				</tr>
				
				<tr>
					<th><spring:message code="las.page.field.speakcontract.title" /></th>
					<td colspan="3">
						<c:out value="${lom.title}"/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.speakcontract.re_cont" /></th>
					<td colspan="3">
						<c:out value='${lom.re_cont}' escapeXml='false'/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.speakcontract.file" /></th>
					<td colspan="3">
						<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="5px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
					</td>
				</tr>
			</table>	
 
 			<!-- button -->
				<div class="btn_wrap tC">
					&nbsp;&nbsp;
					<span class="btn_all_w">
						<span class="cancel"></span>
						<a href="javascript:goList()"><spring:message code="las.page.button.back" /></a>
					</span>
				</div>
			<!-- //button -->
			
	</div>
	<!-- //content -->
	<!-- footer  -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
	<!-- // footer -->
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</div>
<!-- //container -->
</div>
</form:form>
</body>
</html>
