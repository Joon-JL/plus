<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%@ include file= "/WEB-INF/jsp/common/common.jsp" %>
<%-- 
/**
 * 파  일  명 : ContractLibrary_d.jsp
 * 프로그램명 : 국내/해외 계약서 상세
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
 --%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<!-- style -->
<link href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet" />
<link href="<%=CSS%>/popup.css"   type="text/css" rel="stylesheet" />

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>

<script language="javascript">
<!--
	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		var frm = document.frm;
		
		if(flag == "list"){	
			
			viewHiddenProgress(true) ;
			
			frm.target = "_self";
			frm.action = "<c:url value='/clm/draft/libReview.do' />";
		    frm.method.value = "listLibReview";
		    
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
	    frm.target			= "fileList";
	    frm.action 			= "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value  	= "forwardClmsFilePage";
		frm.submit();	
	}	
	
//-->

</script>

</head>
<body onload='showMessage("<c:out value='${returnMessage}'/>");'>

<!-- **************************** Form Setting **************************** -->
<form:form name="frm" method="post" autocomplete="off">

<!-- search form -->
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />
<input type="hidden" name="srch_biz_clsfcn"            id="srch_biz_clsfcn"             value="<c:out value='${command.srch_biz_clsfcn}'/>" />
<input type="hidden" name="srch_depth_clsfcn"          id="srch_depth_clsfcn"           value="<c:out value='${command.srch_depth_clsfcn}'/>" />
<input type="hidden" name="srch_cnclsnpurps_bigclsfcn" id="srch_cnclsnpurps_bigclsfcn"  value="<c:out value='${command.srch_cnclsnpurps_bigclsfcn}'/>" />
<input type="hidden" name="srch_cnclsnpurps_midclsfcn" id="srch_cnclsnpurps_midclsfcn"  value="<c:out value='${command.srch_cnclsnpurps_midclsfcn}'/>" />

<!-- key form -->
<input type="hidden" name="lib_no"     id="lib_no"     value="<c:out value='${command.lib_no}'/>" />
<input type="hidden" name="lib_gbn"    id="lib_gbn"    value="<c:out value='${command.lib_gbn}'/>" />
<input type="hidden" name="region_gbn" id="region_gbn" value="<c:out value='${command.region_gbn}'/>" />

<!-- 첨부파일정보 -->
<input type="hidden" name="fileInfos" 	value="" />
<input type="hidden" name="file_bigclsfcn" 	value="F013" />
<input type="hidden" name="file_midclsfcn" 	value="" />
<input type="hidden" name="file_smlclsfcn" 	value="" />
<input type="hidden" name="ref_key" 	value="<c:out value='${command.lib_no}'/>" />
<input type="hidden" name="view_gbn" 	value="download" />

<!-- //**************************** Form Setting **************************** -->
<div id="wrap">
	<!-- container -->
	<div id="container">	

		<!-- Location -->
		<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->

		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.title.libreview.detailTitle" /></h3>
		</div>
		<!-- //title -->
		
		<!-- content -->
		<div id="content">
			<!-- view -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
			<colgroup>
				<col width="100" />
				<col width="310" />
				<col width="100" />
				<col width="310" />
			</colgroup>
				<tbody>
					<tr>
			            <th><spring:message code="clm.page.field.libreview.biz_clsfcn" /></th>
			            <td><span><c:out value='${lom.biz_clsfcnnm}' default="" /></span></td>
			            <th><spring:message code="clm.page.field.libreview.depth_clsfcn" /></th>
			            <td><span><c:out value='${lom.depth_clsfcnnm}' default="" /></span></td>
		        	</tr>
		        	<tr>
			            <th><spring:message code="clm.page.field.libreview.cnclsnpurps_bigclsfcn" /></th>
			            <td><span><c:out value='${lom.cnclsnpurps_path}' default="" /></span></td>
			            <th><spring:message code="clm.page.field.libreview.lib_gbn" /></th>
			            <td><span><c:out value='${lom.lib_gbnnm}' default="" /></span></td>
		        	</tr>
		        	<tr>
			            <th><spring:message code="clm.page.field.libreview.title" /></th>
			            <td colspan="3"><span><c:out value='${lom.title}' default="" /></span></td>
		        	</tr>
					<tr>
						<th><spring:message code="clm.page.msg.common.content" /></th>
						<td colspan="3" class="pd" id="">
							<c:out value='${lom.cont}' escapeXml="false" />						
						</td>
					</tr>
					<tr class="end">
		            	<th><spring:message code="clm.page.msg.common.attachment" /></th>
		            	<td colspan="3">
		            	<iframe src="<c:url value='/clm/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="100px" leftmargin="0" topmargin="0" scrolling="auto" onLoad="resizeActsfileiframe(this, 'fileList');" ></iframe>
		            	</td>
		          	</tr>
				</tbody>
			</table>
			<!-- //view -->
	
			<div class="btn_wrap">
				<span class="btn_all_w"><span class="cancel"></span><a href="javascript:pageAction('list');"><spring:message code="secfw.page.button.list" /></a></span>
			</div>
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