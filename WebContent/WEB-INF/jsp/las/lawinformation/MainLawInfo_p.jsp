<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.las.lawinformation.dto.MainLawInfoForm" %>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%--
/**
 * 파  일  명 : Notice_p.jsp
 * 프로그램명 : 법무메인 - 주요입법동향 팝업
 * 설      명 : 
 * 작  성  자 : 김형준
 * 작  성  일 : 2011.09.21
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	MainLawInfoForm command = (MainLawInfoForm)request.getAttribute("command");
	ListOrderedMap lom = (ListOrderedMap)request.getAttribute("lom"); 
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css"    type="text/css" rel="stylesheet">
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
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />

<!-- key form-->
<input type="hidden" name="seqno"	value="<c:out value='${command.seqno}'/>" />

<!-- 첨부파일정보 -->
<input type="hidden" name="fileInfos" 	value="" />
<input type="hidden" name="file_bigclsfcn" 	value="F004" />
<input type="hidden" name="file_midclsfcn" 	value="<%=lom.get("info_gbn")%>" />
<input type="hidden" name="file_smlclsfcn" 	value="" />
<input type="hidden" name="ref_key" 	value="<%=lom.get("seqno")%>" />
<input type="hidden" name="view_gbn" 	value="download" />
<!-- // **************************** Form Setting **************************** -->


<!-- header -->
<h1><spring:message code="las.page.title.lawinformation.mayorLegTrends" /><span class="close" onclick="javascript:self.close();" title="close"></span></h1>
<!-- //header -->
<!-- body -->
<div class="pop_area">
	<!-- Popup Detail -->
	<!-- Popup Size 600*600 -->
	<div>
	  <div class="pop_content">
		<!-- title -->
		<!-- //title -->
		<table cellspacing="0" cellpadding="0" class="table-style01" border="0">
		  <colgroup>
		    <col width="100" />
		    <col width="150" />
		    <col width="100" />
		    <col />            
	      </colgroup>
		  <tr>
		    <th><spring:message code="clm.page.field.board.title" /></th>
		    <td colspan="3"><c:out value='${lom.title}' /></td>
	      </tr>
		  <tr>
		    <th><spring:message code="clm.page.field.board.regNm" /></th>
		    <td> <c:out value='${lom.reg_nm}' /></td>
		    <th><spring:message code="clm.page.field.board.regDt" /></th>
		    <td><%=lom.get("reg_dt")%></td>		    
	      </tr>
	      <tr>
		    <th><spring:message code="las.page.field.lawinformation.classfy" /></th>
		    <td><%=lom.get("branch_nm")%></td>
		    <th><spring:message code='las.page.field.lawinformation.country' /></th>
		    <td><%=lom.get("nation_nm")%></td>		    
	      </tr>
		  <tr>
		    <th><spring:message code="clm.page.field.board.cont" /></th>
		    <td colspan="3"><div style="height:220px; overflow:auto"><c:out value='${lom.cont}' escapeXml="false" /></div></td>
	      </tr>
		  <tr>
		    <th><spring:message code="secfw.page.field.bbs.file" /></th>
		    <td colspan="3">
	        	<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="80px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
	        </td>
	      </tr>
	    </table>
	  </div>
	</div>
	<!-- //Popup Detail -->
	<!--footer -->
	<div class="pop_footer">
		<!-- button -->
		 <div class="btn_wrap fR" style="position:fixed;bottom:10px;right:10px;">
		 <span class="btn_all_w"><span class="cancel"></span><a href="#" onclick="javascript:self.close();"><spring:message code="las.page.button.lawinformation.close"/></a></span>
		 </div>
	<!-- //button -->
	</div>
	<!-- //footer -->
</div>
<!-- //body -->
</form:form>
</body>
</html>