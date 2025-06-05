<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.admin.dto.BoardForm" %>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%-- 
/**
 * 파  일  명 : Board_d.jsp
 * 프로그램명 : 공지사항 상세
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	BoardForm command = (BoardForm)request.getAttribute("command");
	ListOrderedMap lom = (ListOrderedMap)request.getAttribute("lom"); 
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<link href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet" />

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
		frm.action = "<c:url value='/clm/counsel/board.do' />";
		if(flag == "list"){	
			viewHiddenProgress(true) ;
		    frm.method.value = "listBoard";
			frm.submit();
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
<body onLoad=''>

<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id="frm" method="post" autocomplete="off">

<!-- search form -->
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />
<input type="hidden" name="mode"  value="<c:out value='${command.mode}'/>" />
<input type="hidden" name="srch_start_dt" value="<c:out value='${command.srch_start_dt}'/>" />
<input type="hidden" name="srch_end_dt"   value="<c:out value='${command.srch_end_dt}'/>" />
<input type="hidden" name="srch_keyword"    value="<c:out value='${command.srch_keyword}'/>" />
<input type="hidden" name="srch_keyword_content"    value="<c:out value='${command.srch_keyword_content}'/>" />

<!-- key form-->
<input type="hidden" name="seqno"	value="<c:out value='${command.seqno}'/>" />

<!-- 첨부파일정보 -->
<input type="hidden" name="fileInfos" 	value="" />
<input type="hidden" name="file_bigclsfcn" 	value="F008" />
<input type="hidden" name="file_midclsfcn" 	value="" />
<input type="hidden" name="file_smlclsfcn" 	value="" />
<input type="hidden" name="ref_key" 	value="<%=lom.get("seqno")%>" />
<input type="hidden" name="view_gbn" 	value="download" />

<!-- // **************************** Form Setting **************************** -->
<div id="wrap">
	<!-- container -->
	<div id="container">	

		<!-- Location -->
		<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->

		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.title.board.detailTitle" /></h3>
		</div>
		<!-- //title -->
		
		<!-- content -->
		<div id="content">
			<!-- view -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
					<col width="10%" />
					<col width="30%" />
					<col width="10%" />
					<col width="20%" />
					<col width="10%" />
					<col width="20%" />
				</colgroup>
				<tbody>
					<tr>
						<th><spring:message code="clm.page.field.board.title" /></th>
						<td colspan="5"><c:out value='${lom.title}' /></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.board.regNm" /></th>
						<td><c:out value='${lom.reg_nm}' /> / <c:out value='${lom.regman_jikgup_nm}'/> / <c:out value='${lom.reg_dept_nm}' /></td>
						<th><spring:message code="clm.page.field.board.regDt" /></th>
						<td><%=DateUtil.formatDate((String)lom.get("reg_dt"), "-")%></td>
						<th><spring:message code="clm.page.field.board.rdcnt" /></th>
						<td><%=lom.get("rdcnt")%></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.board.cont" /></th>
						<td colspan="5">
							<%if("Y".equals((String)lom.get("sec_yn"))){%>
								<div style="margin-bottom: 10px; margin-top:5px;"><img src="<%=IMAGE%>/common/notice_txt.jpg"  /></div>					  					  
							<%}%>
							<c:out value='${lom.cont}' escapeXml="false" />
						</td>
					</tr>
					<tr class="end">
		            	<th><spring:message code="secfw.page.field.bbs.file" /></th>
		            	<td colspan="5">
		            	<iframe src="<c:url value='/clm/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="100px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
		            	</td>
		          	</tr>
				</tbody>
			</table>
			<!-- //view -->
	
			<div class="btn_wrap">
				<span class="btn_all_w"><span class="list"></span><a href="javascript:pageAction('list');"><spring:message code="secfw.page.button.list" /></a></span>
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