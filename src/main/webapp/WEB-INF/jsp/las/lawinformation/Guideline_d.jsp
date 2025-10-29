<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.las.lawinformation.dto.GuideneduForm" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%@ include file= "/WEB-INF/jsp/common/common.jsp" %>
<%-- 
/**
 * 파  일  명 : Guideline_d.jsp
 * 프로그램명 : 계약서 Guideline 상세
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 2011.09
 */
 --%>
<%
	GuideneduForm command = (GuideneduForm)request.getAttribute("command");
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet" />
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/util/fckeditor/fckeditor.js' />"></script>
<script language="javascript">

	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/las/lawinformation/guidenedu.do' />";
		
		if(flag == "list"){
		    frm.method.value = "listGuideline";
	
		    viewHiddenProgress(true) ;
			frm.submit();
			
		} else if(flag == "insertForm"){
		    frm.method.value = "forwardInsertGuideline";

		    viewHiddenProgress(true) ;
			frm.submit();
			
		}else if(flag == "modify") {
		    frm.method.value = "forwardModifyGuideline";

		    viewHiddenProgress(true) ;
			frm.submit();
			
		}else if(flag == "delete") {

			if(confirm("<spring:message code='las.msg.ask.delete'/>")){
				frm.method.value = "deleteGuideline";
				
				viewHiddenProgress(true);
				frm.submit();
			}
		}
	}
	
	function moveTab(){
		
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "/las/lawinformation/library.do";
		frm.method.value = "listLibrary";

	    viewHiddenProgress(true) ;
		frm.submit();
	}

</script>

</head>
<body>

<!-- **************************** Form Setting **************************** -->
<form:form name="frm" method="post" autocomplete="off">
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />

<!-- key form -->
<input type="hidden" name="info_gbn" id="info_gbn" value="<c:out value='${command.info_gbn}'/>" />
<input type="hidden" name="seqno"    id="seqno"    value="<c:out value='${command.seqno}'/>" />

<!-- //**************************** Form Setting **************************** -->
<div id="wrap">	
	<div id="container">
		<!-- Location -->
		<div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0" alt="Home" /><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->
		<!-- content -->
		<div id="content">
			<!-- Title -->
			<div class="title">
				<h3><spring:message code="las.page.title.contract.foreign.Library" /></h3>
			</div>
			<!-- //title -->
			<!-- tab01 -->
			<ul class="tab_basic04">
				<li class="on"><a href="#"><spring:message code="las.page.field.lawinformation.tab.guideline"/></a></li>
				<li><a href="javascript:moveTab();"><spring:message code="las.page.field.lawinformation.tab.samplecont"/></a></li>
			</ul>
			<!-- 두줄라인 추가 --><div class="tal_topLine"></div><!-- //두줄라인 추가 -->
			<!--  view  -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
					<col width="15%" />
					<col width="85%" />
				</colgroup>
				<tbody>
					<c:choose>
						<c:when test="${returnMessage!='noResult'}">
							<tr>
								<th><spring:message code='las.page.field.contract.library.title' /></th>
								<td><c:out value='${lom.title}' default="" /></td>
							</tr>
							<tr class="end">
								<th><spring:message code="clm.page.msg.common.content" /></th>
								<td><c:out value='${lom.cont}' escapeXml="false" /></td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr class="end">
								<td colspan="2" align="center" class="tC"><spring:message code="las.page.msg.lawinformation.noData"/><c:out value='${lom.size }'/></td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<!-- //view  -->
			<!-- Function Button  -->
			<div class="t_titBtn">
				<div class="btn_wrap">
					<c:choose>
						<c:when test="${returnMessage=='noResult'}">
							<%if(command.isAuth_insert()){ %> 	
							<span class="btn_all_b"><span class="save"></span><a href="javascript:pageAction('insertForm');"><spring:message code='las.page.button.new' /></a></span>
							<%} %>
						</c:when>
						<c:otherwise>
							<%if(command.isAuth_modify()){ %> 	
								<span class="btn_all_w"><span class="edit"></span><a href="javascript:pageAction('modify');"><spring:message code='las.page.button.modify' /></a></span>
							<%} %>
							<%if(command.isAuth_delete()){ %> 	
								<span class="btn_all_w"><span class="delete"></span><a href="javascript:pageAction('delete');"><spring:message code='las.page.button.delete' /></a></span>
							<%} %>
						</c:otherwise>
					</c:choose>
					<span class="btn_all_w"><span class="list"></span><a href="javascript:pageAction('list');"><spring:message code='las.page.button.list' /></a></span>
				</div>
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
