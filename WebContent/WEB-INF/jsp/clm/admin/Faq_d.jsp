<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.admin.dto.FaqForm" %>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%-- 
/**
 * 파  일  명 : Faq_d.jsp
 * 프로그램명 : FAQ 상세
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	FaqForm command = (FaqForm)request.getAttribute("command");
	ListOrderedMap lom = (ListOrderedMap)request.getAttribute("lom"); 
	List orgnzList = (List)request.getAttribute("orgnzList");
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
		frm.action = "<c:url value='/clm/admin/faq.do' />";
		if(flag == "modify"){
			viewHiddenProgress(true) ;
		    frm.method.value = "forwardModifyFaq";
			frm.submit();

		} else if(flag == "delete"){	
			if(confirm("<spring:message code='secfw.msg.ask.delete' />")){
				viewHiddenProgress(true) ;
			    frm.method.value = "deleteFaq";
			    frm.submit();
			}	
		} else if(flag == "list"){	
			viewHiddenProgress(true) ;
		    frm.method.value = "listFaq";
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
		
//-->

</script>

</head>
<body onload='showMessage("<c:out value='${returnMessage}'/>");'>

<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id="frm" method="post" autocomplete="off">

<!-- search form -->
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />
<input type="hidden" name="srch_cont_type" value="<c:out value='${command.srch_cont_type}'/>" />
<input type="hidden" name="srch_keyword"    value="<c:out value='${command.srch_keyword}'/>" />
<input type="hidden" name="srch_keyword_content"    value="<c:out value='${command.srch_keyword_content}'/>" />

<!-- key form-->
<input type="hidden" name="seqno"	value="<c:out value='${command.seqno}'/>" />

<!-- // **************************** Form Setting **************************** -->
<div id="wrap">
	<!-- container -->
	<div id="container">	

		<!-- Location -->
		<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->

		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.title.faq.detailTitle" /></h3>
		</div>
		<!-- //title -->

		<!-- content -->
		<div id="content">
			<!-- view -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
					<col width="100" />
					<col width="720" />
				</colgroup>
				<tbody>
					<tr>
						<th><spring:message code="clm.page.field.faq.contType" /></th>
						<td><%=lom.get("cont_type_nm")%></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.faq.titleKr" /></th>
						<td><%=lom.get("title")%></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.faq.titleEn" /></th>
						<td><%=lom.get("title_en")%></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.faq.rdTrgt1" /></th>
						<td><%=lom.get("rd_trgt1_nm")%>
						<%if("C01602".equals((String)lom.get("rd_trgt1"))){ %>
							<%if(orgnzList != null && orgnzList.size()>0){ 
								for(int i=0; i<orgnzList.size();i++){
									ListOrderedMap orgnz = (ListOrderedMap)orgnzList.get(i);
									
									if(orgnzList.size() == 1){
							%>
									[<span style='padding-left:3px;padding-right:3px;'><%=(String)orgnz.get("orgnz_nm") %></span>]
							<%										
									}else{
										if(i == 0){
							%>
										[<span style='padding-left:3px;padding-right:3px;'><%=(String)orgnz.get("orgnz_nm") %></span>,
							<%
										}else if(i == orgnzList.size()-1){
							%>
										<span style='padding-left:3px;padding-right:3px;'><%=(String)orgnz.get("orgnz_nm") %></span>]
							<%
										}else{
							%>
										<span style='padding-left:3px;padding-right:3px;'><%=(String)orgnz.get("orgnz_nm") %></span>,
							<%
										}
									}
							%>
								
								
						<%		
								}
							  }
							}
						%>
						</td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.faq.regDt" /></th>
						<td><%=DateUtil.formatDate((String)lom.get("reg_dt"), "-")%></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.faq.contKr" /></th>
						<td>
							<%=StringUtil.convertNamoCharsToHtml((String)lom.get("cont"))%>
						</td>
					</tr>
					<tr  class="end">
						<th><spring:message code="clm.page.field.faq.contEn" /></th>
						<td>
							<%=StringUtil.convertNamoCharsToHtml((String)lom.get("cont_en"))%>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- //view -->
	
			<div class="btn_wrap">
	<%if(command.isAuth_modify()){ %>		
				<span class="btn_all_w"><span class="edit"></span><a href="javascript:pageAction('modify');"><spring:message code="secfw.page.button.modify" /></a></span>
	<%}%>
	<%if(command.isAuth_delete()){ %>		
				<span class="btn_all_w"><span class="delete"></span><a href="javascript:pageAction('delete');"><spring:message code="secfw.page.button.delete" /></a></span>
	<%}%>
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