<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.las.board.dto.NoticeForm" %>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%--
/**
 * 파  일  명 : Notice_p.jsp
 * 프로그램명 : 법무메인 - 공지사항팝업
 * 설      명 : 
 * 작  성  자 : 김형준
 * 작  성  일 : 2011.09.21
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	NoticeForm command = (NoticeForm)request.getAttribute("command");
	ListOrderedMap lom = (ListOrderedMap)request.getAttribute("lom"); 
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css"    type="text/css" rel="stylesheet">
<link href="<%=CSS%>/popup.css"    type="text/css" rel="stylesheet">
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/clms/common.js' />"></script>
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

/**
* 하루동안 창을 열지 않기
*/
function closeWin(){
	var frm = document.getElementById("closeEvent");
	if (frm.checked) {
		setCookie("noticePopupLasMain", "no" , 1); // 1일 간 쿠키적용 	
	}
	self.close();	
}

</script>
</head>
<body class="pop">

<!-- header -->
<h1><spring:message code="las.page.title.board.Notice" /><span class="close" onclick="javascript:self.close();" title="close"></span></h1>
<!-- //header -->

<div class="pop_area">
	<div class="pop_content"  style='height:420px;'>
<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id="frm" method="post" autocomplete="off">

<!-- search form -->
<input type="hidden" name="method"   value="">
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />

<!-- key form-->
<input type="hidden" name="seqno"	value="<c:out value='${command.seqno}'/>" />

<%
String aaa = (command.getSys_cd().equals("LAS")?"F001":"F008");
%>

<!-- 첨부파일정보 -->
<input type="hidden" name="fileInfos" 		value="" />
<input type="hidden" name="file_bigclsfcn" 	value="F007" />
<input type="hidden" name="file_midclsfcn" 	value="<c:out value='${command.annce_knd}'/>" />
<input type="hidden" name="file_smlclsfcn" 	value="" />
<input type="hidden" name="ref_key" 		value="<c:out value='${command.seqno}'/>" />
<input type="hidden" name="view_gbn" 		value="download" />

<!-- // **************************** Form Setting **************************** -->

		<table class="table-style01">
		  <colgroup>
		    <col width="15%" />
		    <col width="35%" />
		    <col width="15%" />
		    <col width="35%" />   
	      </colgroup>
		  <tr>
		    <th><spring:message code="clm.page.field.board.title" /></th>
<%-- 		    <%if("ko".equals(command.getSession_user_locale())){ %> --%>
<%-- 		    	<td colspan="3"><c:out value='${lom.title}' /></td> --%>
<%-- 		    <%} %> --%>
<%-- 		    <% else{ %> --%>
		    	<td colspan="3"><c:out value='${lom.title_en}' /></td>
<%-- 		    <%} %> --%>
	      </tr>
		  <tr>
		    <th><spring:message code="clm.page.field.board.regNm" /></th>
		    <td> <c:out value='${lom.reg_nm}' /></td>
		    <th><spring:message code="clm.page.field.board.regDt" /></th>
		    <td><%=lom.get("reg_dt")%></td>
		    
	      </tr>
		  <tr>
		    <th><spring:message code="clm.page.field.board.cont" /></th>
		    <td colspan="3">
<%-- 		    <%if("ko".equals(command.getSession_user_locale())){ %> --%>
<%-- 		        <div style="overflow:auto"><c:out value='${lom.cont}' escapeXml="false" /></div>  <!-- 보여지느 내용이 짤리는 현상으로 height:250px; 삭제함. 김재원 --> --%>
<%-- 		    <%}%> --%>
<%-- 		    <% else{ %> --%>
		        <div style="overflow:auto"><c:out value='${lom.cont_en}' escapeXml="false" /></div>  <!-- 보여지느 내용이 짤리는 현상으로 height:250px; 삭제함. 김재원 -->
<%-- 		    <%}%> --%>
		    </td>
	      </tr>
		  <tr>
		    <th><spring:message code="secfw.page.field.bbs.file" /></th>
		    <td colspan="3">
			    <!-- Sungwoo Replaced scroll option 2014/08/04 -->
		    	<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe>
	        </td>
	      </tr>
	    </table>
	    </form:form>
	  </div>
	</div>




<!--footer -->

<div class="pop_footer">
	
	<div class="btn_wrap fR" >
	
		<%if("Main".equals(command.getPath_gbn())){%>
				<input type="checkbox" name="closeEvent" id="closeEvent" onClick="closeWin()"/>&nbsp;<spring:message code="las.msg.inform.noPopup" />&nbsp;
		<%} %>
		<span class="btn_all_w"><span class="cancel"></span><a href="#" onclick="javascript:self.close();"><spring:message code="las.page.button.board.close"/></a></span>
	</div>
</div>
<!-- //footer -->

</body>
</html>