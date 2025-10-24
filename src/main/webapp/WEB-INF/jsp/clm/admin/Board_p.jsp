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
 * 파  일  명 : Board_p.jsp
 * 프로그램명 : 계약관리메인 - 공지사항팝업
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	BoardForm command = (BoardForm)request.getAttribute("command");
	ListOrderedMap lom = (ListOrderedMap)request.getAttribute("lom"); 
	String localeCode = (String)session.getAttribute("secfw.session.language_flag");
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><spring:message code="clm.main.title" /></title>
<link href="<%=CSS%>/clm.css"    type="text/css" rel="stylesheet" />
<link href="<%=CSS%>/popup.css"    type="text/css" rel="stylesheet" />

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
	//frm.submit();
	getClmsFilePageCheck('frm');
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
<input type="hidden" name="file_bigclsfcn" 	value="F008" />
<input type="hidden" name="file_midclsfcn" 	value="" />
<input type="hidden" name="file_smlclsfcn" 	value="" />
<input type="hidden" name="ref_key" 	value="<%=lom.get("seqno")%>" />
<input type="hidden" name="view_gbn" 	value="download" />

<!-- // **************************** Form Setting **************************** -->

<!-- header -->
<h1><spring:message code="clm.page.msg.admin.noticeDetail" /></h1>
<!-- //header -->
<!-- body -->
	<div class="pop_area">
    	<!-- Popup Detail -->
        <!-- Popup Size 800*600 -->
        <div class="h_600">
			<!-- table01 -->
	  		<div class="title_basic">
				<h4><spring:message code="clm.page.msg.admin.noticeDetail" /></h4>
		  	</div>
			<table class="table-notice">
			<colgroup>
			   	<col width="130px" />
			   	<col />
			</colgroup>
			<tbody>
			<tr>
				<%if("C01601".equals((String)lom.get("rd_trgt1"))){%>
				<th><spring:message code="clm.page.msg.admin.enterpriseNotice" /></th>
				<%}else{%>
				<th><spring:message code="clm.page.msg.admin.unitOrgNotice" /></th>
				<%} %>
				<td>
				<% if(localeCode.equals("en")) { %>
				<c:out value='${lom.title_en}' />
				<%} else {%>
				<c:out value='${lom.title}' />
				<% } %>
				</td>
		    </tr>
			<tr>
				<th colspan="2"><div class="notice_txt"></div>
				<%if("Y".equals((String)lom.get("sec_yn"))){%>			
				<div class="notice_txt_img"><img src="<%=IMAGE%>/common/notice_txt.jpg"  /></div>
				<%}%>
				</th>
			</tr>
			<tr class="end">
				<th class="name" colspan="2">
					<strong><c:out value='${lom.reg_nm}' /></strong>/<c:out value='${lom.reg_dept_nm}' />
					<%if(!"".equals((String)lom.get("annce_startday"))){%>
				    	<%=DateUtil.formatDate((String)lom.get("annce_startday"), "-")%>
				        ~
				    <%}%>
				    <%if(!"".equals((String)lom.get("annce_endday"))){%>
				    	<%=DateUtil.formatDate((String)lom.get("annce_endday"), "-")%>
				    <%}%>
				    <%if("".equals((String)lom.get("annce_startday")) && "".equals((String)lom.get("annce_endday"))){%>
				    	<spring:message code="clm.msg.field.board.always" />
					<%} %>
					| Hit:<c:out value='${lom.rdcnt}' />
				</th>
		    </tr>
			</tbody>
		  	</table>
			<table style="width:100%; border: 2px solid #ddd;">
		  	<tr>
				<td colspan="6" style="padding: 10px; color: #7b858e; height:250px; vertical-align:top;">
					<% if(localeCode.equals("en")) { %>
					<c:out value='${lom.cont_en}' escapeXml="false" />
					<%} else {%>
					<c:out value='${lom.cont}' escapeXml="false" />
					<% } %>
				</td>
			</tr>
		  	</table>
			<table style="width:100%; border: 2px solid #ddd; margin:0; table-layout:fixed; margin-top: 8px;">
		    <colgroup>
				<col width="130px" />
			    <col />
			</colgroup>
			<tr>
				<td style="padding: 10px; color: #7b858e; font-weight: bold;"><spring:message code="secfw.page.field.bbs.file" /></td>
                <td style="padding: 10px; color: #7b858e;">
                	<iframe src="<c:url value='/clm/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="100px" leftmargin="0" topmargin="0" scrolling="auto"  ></iframe>
                </td>
			</tr>
			</table>
			<div class="btn_wrap fR">
			 <span class="btn_all_w"><span class="cancel"></span><a href="#" onclick="javascript:self.close();"><spring:message code="clm.page.msg.common.close" /></a></span>
			 </div>
		</div>
		<div>
	<!-- button -->
	</div>
</div>

<!-- //body -->
<!--footer -->

<!-- //button -->
</div>
<!-- //footer -->
</form:form>
</body>
</html>