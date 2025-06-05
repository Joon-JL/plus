<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.manage.dto.MyNoticeForm" %>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%--
/**
 * 파  일  명 : Board_p.jsp
 * 프로그램명 : 계약관리메인 - My Notice 팝업
 * 설      명 : 
 * 작  성  자 : 김형준
 * 작  성  일 : 2011.10.28
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	MyNoticeForm command = (MyNoticeForm)request.getAttribute("command");
	ListOrderedMap lom = (ListOrderedMap)request.getAttribute("lom"); 
	String sRe = (String)request.getAttribute("sRe");
	sRe = sRe.replaceAll("&lt;br&gt;","<br>").replaceAll("//","/");
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet" />
<link href="<%=CSS%>/popup.css"    type="text/css" rel="stylesheet">

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript">
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

</script>
</head>
<body class="pop">

<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id="frm" method="post" autocomplete="off">

<!-- search form -->
<input type="hidden" name="method"   value="">
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />

<!-- key form-->
<input type="hidden" name="mis_id" value="<c:out value='${command.mis_id}'/>" />
<input type="hidden" name="module_id" value="<c:out value='${command.module_id}'/>" />

<!-- 첨부파일정보 시작 -->
<input type="hidden" name="sys_gbn" 	value="mail" />
<input type="hidden" name="view_gbn" 	value="download" />

<!-- // **************************** Form Setting **************************** -->


<!-- header -->
<h1>My Notice<span class="close" onclick="javascript:self.close();" title="close"></span></h1>
<!-- //header -->
<!-- body -->
<div class="pop_area">
	<!-- Popup Detail -->
	<!-- Popup Size 600*600 -->
	<div>
	  <div class="pop_content">
		<!-- title -->
		<div class="title_sub">
			<h5><spring:message code="clm.page.title.MyNotice.detailTitle" /></h5> 			   
		</div>
		<!-- //title -->
		<table cellspacing="0" cellpadding="0" class="table-style01" border="0">
		  <colgroup>
		    <col width="100" />
		    <col />
		    <col width="100" />
		    <col />
            <col width="100" />
		    <col />
	      </colgroup>
		  <tr>
		    <th><spring:message code="clm.page.field.board.title" /></th>
		    <td colspan="5"><c:out value='${lom.subject}' /></td>
	      </tr>
		  <tr>
			<th><spring:message code="clm.page.field.MyNotice.Addressee" /></th>
			<td colspan="5"><%=sRe %></td>
		  </tr>
		  <tr>
			<th><spring:message code="clm.page.field.MyNotice.MessageKind" /></th>
			<td colspan="2"><c:out value='${lom.msg_type}' /></td>
			             
			<th><spring:message code="clm.page.field.MyNotice.regdt" /></th>
			<td colspan="2"><c:out value='${lom.create_date}' /></td>
		  </tr>
		  <tr>
			<th><spring:message code="clm.page.field.MyNotice.TransferState" /></th>
			<td colspan="5"><c:out value='${lom.status}' /></td>
		  </tr>
		  <tr>
			<th><spring:message code="clm.page.field.MyNotice.originator" /></th>
			<td colspan="5"><c:out value='${lom.sender_mail_addr}' /></td>
		   </tr>
		  <tr>
		    <th><spring:message code="clm.page.field.board.cont" /></th>
		    <td colspan="5"><c:out value='${lom.body}' escapeXml="false" /></td>
	      </tr>
		  <tr>
		    <th><spring:message code="secfw.page.field.bbs.file" /></th>
		    <td colspan="5">
	        	<iframe src="<c:url value='/clm/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="100px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
	        </td>
	      </tr>
	    </table>
	  </div>
	</div>
	<!-- //Popup Detail -->
</div>
<!-- //body -->
<!--footer -->
<div class="pop_footer">
	<!-- button -->
	 <div class="btn_wrap fR">
	 <span class="btn_all_w"><span class="cancel"></span><a href="#" onclick="javascript:self.close();"><spring:message code="clm.page.msg.common.close" /></a></span>
	 </div>
<!-- //button -->
</div>
<!-- //footer -->
</form:form>
</body>
</html>