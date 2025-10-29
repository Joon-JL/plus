<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>
<%@ page import="com.sec.las.lawinformation.dto.LibraryForm" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%@ include file= "/WEB-INF/jsp/common/common.jsp" %>
<%-- 
/**
 * 파  일  명 : ContractLibrary_d.jsp
 * 프로그램명 : 국내/해외 계약서 상세
 * 설      명 : 
 * 작  성  자 : 신승완
 * 작  성  일 : 2011.08
 */
 --%>
<%
	LibraryForm command = (LibraryForm)request.getAttribute("command");
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.main.title" /></title>
<LINK href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/util/fckeditor/fckeditor.js' />"></script>
<script language="javascript">
<!--
	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		var frm = document.frm;
	
		if(flag == "modify"){
			
			frm.target = "_self";
			frm.action = "<c:url value='/las/lawinformation/library.do' />";
		    frm.method.value = "forwardModifyLibrary";

		    viewHiddenProgress(true) ;
			frm.submit();

		} else if(flag == "delete"){	

			if(confirm("<spring:message code='las.msg.ask.delete'/>")){
				frm.target = "_self";
				frm.action = "<c:url value='/las/lawinformation/library.do' />";
			    frm.method.value = "deleteLibrary";

			    viewHiddenProgress(true) ;
			    frm.submit();
			}
			
		} else if(flag == "list"){	

			frm.target = "_self";
			frm.action = "<c:url value='/las/lawinformation/library.do' />";
		    frm.method.value = "listLibrary";

		    viewHiddenProgress(true) ;
			frm.submit();
			
		}
	}

	/**
	* Tab 이동
	*/
	
	function moveTab(){
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "/las/lawinformation/guidenedu.do";
		frm.method.value = "listGuideline";
				
		frm.submit();
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
	    frm.action = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value  	= "forwardClmsFilePage";
		
		frm.submit();	
	}	
	
//-->

</script>

</head>
<body onLoad='showMessage("<c:out value='${returnMessage}'/>");'>

<!-- **************************** Form Setting **************************** -->
<form:form name="frm" method="post" autocomplete="off">

<!-- search form -->
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />
<input type="hidden" name="srch_start_dt" 	value="<c:out value='${command.srch_start_dt}'/>" />
<input type="hidden" name="srch_end_dt"   	value="<c:out value='${command.srch_end_dt}'/>" />
<input type="hidden" name="srch_combo"      value="<c:out value='${command.srch_combo}'/>" />
<input type="hidden" name="srch_name"       value="<c:out value='${command.srch_name}'/>" />
<input type="hidden" name="srch_lib_gbn"    value="<c:out value='${command.srch_lib_gbn}'/>" />

<!-- key form -->
<input type="hidden" name="info_gbn" id="info_gbn" value="<c:out value='${command.info_gbn}'/>" />
<input type="hidden" name="seqno"    id="seqno"    value="<c:out value='${command.seqno}'/>" />

<!-- 첨부파일정보 -->
<input type="hidden" name="fileInfos" 	value="" />
<input type="hidden" name="file_bigclsfcn" 	value="F004" />
<input type="hidden" name="file_midclsfcn" 	value="<c:out value='${command.info_gbn}'/>" />
<input type="hidden" name="file_smlclsfcn" 	value="" />
<input type="hidden" name="ref_key" 	value="<c:out value='${command.seqno}'/>" />
<input type="hidden" name="view_gbn" 	value="download" />

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
			<c:choose>
				<c:when test="${command.info_gbn=='C00403'}">
				<h3><spring:message code="las.page.title.contract.local.Library" /></h3>
				</c:when>
				<c:otherwise>
				<h3><spring:message code="las.page.title.contract.foreign.Library" /></h3>
				</c:otherwise>
			</c:choose>
			</div>
			<!-- //title -->
			<c:if test="${command.info_gbn=='C00404'}">
			<!-- tab01 -->
			<ul class="tab_basic04">
				<li><a href="javascript:moveTab();"><spring:message code="las.page.field.lawinformation.tab.guideline"/></a></li>
				<li class="on"><a href="#"><spring:message code="las.page.field.lawinformation.tab.samplecont"/></a></li>
			</ul>
			<!-- 두줄라인 추가 --><div class="tal_topLine"></div><!-- //두줄라인 추가 -->
			</c:if>
			<!--  view  -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
			<c:choose>
				<c:when test="${command.info_gbn=='C00403'}">
				<colgroup>
					<col width="15%" />
					<col width="35%" />
					<col width="15%" />
					<col width="35%" />
				</colgroup>
				</c:when>
				<c:otherwise>
				<colgroup>
					<col width="15%" />
					<col width="85%" />
				</colgroup>
				</c:otherwise>
			</c:choose>
				<tbody>
				<c:choose>
					<c:when test="${command.info_gbn=='C00403'}">
		        	<tr>
			            <th><spring:message code='las.page.field.contract.library.lib_gbn' /></th>
			        	<td>
			        		<c:out value='${lom.lib_gbnnm}' default="" />
			        	</td>
			        	<th><spring:message code='las.page.field.contract.library.reg_dt2' /></th>
			        	<td>
			        		<c:out value='${lom.reg_dt}' default="" />
			        	</td>
		        	</tr>
		        	<tr>
			            <th><spring:message code='las.page.field.contract.library.title' /></th>
			            <td colspan="3">
			            	<c:out value='${lom.title}' default="" />
			            </td>
		        	</tr>
		        	</c:when>
		        	<c:otherwise>
		        	<tr>
			            <th><spring:message code='las.page.field.contract.library.title' /></th>
			        	<td colspan="3">
			        		<c:out value='${lom.lib_gbnnm}' default="" />
			        	</td>
		        	</tr>
		        	<tr>
			            <th><spring:message code='las.page.field.contract.library.reg_dt2' /></th>
			        	<td colspan="3">
			        		<c:out value='${lom.reg_dt}' default="" />
			        	</td>
		        	</tr>
		        	</c:otherwise>
		        </c:choose>
		        	<tr>
			            <th><spring:message code='las.page.field.contract.library.usage' /></th>
			            <td colspan="3">
			            	<c:out value='${lom.usage}' default="" />
			            </td>
		        	</tr>
					<tr>
						<th><spring:message code="clm.page.msg.common.content" /></th>
						<td colspan="3">
							<c:out value='${lom.cont}' escapeXml="false" />						
						</td>
					</tr>
					<tr class="end">
			    		<th><spring:message code="las.page.field.board.notice.attach_file" /></th>
						<td colspan="3">
							<!-- Sungwoo Replaced scroll option 2014/08/04 -->
		            		<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" class='addfile_iframe_d' scrolling="auto" ></iframe>
		            	</td>
		          	</tr>
				</tbody>
			</table>
			<!-- //view  -->
			<!-- Function Button  -->
			<div class="t_titBtn">
				<div class="btn_wrap">
					<%if(command.isAuth_modify()){ %> 	
						<span class="btn_all_w"><span class="edit"></span><a href="javascript:pageAction('modify')"><spring:message code='las.page.button.modify' /></a></span>
					<%} %>
					<%if(command.isAuth_delete()){ %>  
						<span class="btn_all_w"><span class="delete"></span><a href="javascript:pageAction('delete')"><spring:message code='las.page.button.delete' /></a></span>
					<%} %>
					<span class="btn_all_w"><span class="list"></span><a href="javascript:pageAction('list')"><spring:message code='las.page.button.list' /></a></span>
				</div>
			</div>
			<!-- //Function Button  -->
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