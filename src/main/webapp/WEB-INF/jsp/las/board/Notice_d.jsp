<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>
<%@ page import="com.sec.las.board.dto.NoticeForm" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<%--
/**
 * 파  일  명 : Notice_d.jsp
 * 프로그램명 : 법무공지, 법원송달문서공지, 시스템FAQ - 상세
 * 설      명 : 
 * 작  성  자 : 한지훈
 * 작  성  일 : 2011.08.18
 */
 --%>

<%
	NoticeForm command = (NoticeForm)request.getAttribute("command");
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>

<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript">

	function goList() {
		var frm = document.frm;
		
		viewHiddenProgress(true) ;
		
		<c:choose>
			<c:when test="${command.annce_knd=='MEMO'}">
				frm.method.value = "listLawNotice";
			</c:when>			
			<c:when test="${command.annce_knd=='ANNO'}">
				frm.method.value = "listLawNotice";
			</c:when>
			<c:when test="${command.annce_knd=='DEPT'}">
				frm.method.value = "listDispatchNotice";
			</c:when>
			<c:when test="${command.annce_knd=='FAQ'}">
				frm.method.value = "listSysFaq";
			</c:when>
		</c:choose>
		
		frm.action = "<c:url value='/las/board/notice.do' />";
		frm.target = "_self";
		frm.submit();
	}
	
	function modify() {
		
		var frm = document.frm;
		
		viewHiddenProgress(true) ;

		<c:choose>
			<c:when test="${command.annce_knd=='ANNO'}">
				frm.method.value = "forwardModifyLawNotice";
			</c:when>
			<c:when test="${command.annce_knd=='DEPT'}">
				frm.method.value = "forwardModifyDispatchNotice";
			</c:when>
			<c:when test="${command.annce_knd=='FAQ'}">
				frm.method.value = "forwardModifySysFaq";
			</c:when>
			<c:when test="${command.annce_knd=='MEMO'}">
				frm.method.value = "forwardModifyLawNotice";
			</c:when>
		</c:choose>
		
		frm.action = "<c:url value='/las/board/notice.do' />";
		frm.target = "_self";
		frm.submit();
	}
	
	function remove(){
		
		var frm = document.frm;
		
		if(confirm("<spring:message code='secfw.msg.ask.delete' />")) {

			<c:choose>
				<c:when test="${command.annce_knd=='ANNO'}">
					frm.method.value = "deleteLawNotice";
				</c:when>
				<c:when test="${command.annce_knd=='DEPT'}">
					frm.method.value = "deleteDispatchNotice";
				</c:when>
				<c:when test="${command.annce_knd=='FAQ'}">
					frm.method.value = "deleteSysFaq";
				</c:when>
				<c:when test="${command.annce_knd=='MEMO'}">
					frm.method.value = "deleteLawNotice";
				</c:when>
			</c:choose>
			
			frm.action = "<c:url value='/las/board/notice.do' />";
			frm.target = "_self";

			viewHiddenProgress(true) ;
			frm.submit();
		}
	}
	
	function showMessage(msg){
		if( msg != "" && msg != null && msg.length > 1 ) {
			alert(msg);
		}
	}
	
	/**
	* - Namo Editor 세팅 
	*/
	$(document).ready(function(){
		
		var frm = document.frm;
		
		//첨부파일창 load
	    frm.target = "fileList";
	    frm.action = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value = "forwardClmsFilePage";
		
		frm.submit();
	});

</script>
</head>
<body onLoad='showMessage("<c:out value='${returnMessage}'/>");'>


<div id="wrap">	
	<div id="container">
		<!-- location -->
		<div class="location"><IMG SRC="<%=IMAGE %>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //location -->
		<!-- title -->			
		<div class="title">
			<c:choose>
				<c:when test="${command.annce_knd=='ANNO'}">
				<h3><spring:message code="las.page.title.board.LawNotice" /></h3>
				</c:when>
				<c:when test="${command.annce_knd=='DEPT'}">
				<h3><spring:message code="las.page.title.board.DispatchNotice" /></h3>
				</c:when>
				<c:when test="${command.annce_knd=='FAQ'}">
				<h3><spring:message code="las.page.title.board.SysFaq" /></h3>
				</c:when>
				<c:when test="${command.annce_knd=='MEMO'}">
					<h3><spring:message code="las.page.title.board.Notice" /></h3>
				</c:when>
			</c:choose>
		</div>
		<!-- //title -->
		
		<!-- content -->
		<div id="content"> 
		<div id="content_in">
			<form:form name="frm" id="frm" autocomplete="off">
			<input type="hidden" name="method"   	 	value="" />
			<input type="hidden" name="menu_id"  	 	value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="curPage"  	 	value="<c:out value='${command.curPage}'/>" />
			<input type="hidden" name="srch_word"		value="<c:out value='${command.srch_word}'/>" />
			<input type="hidden" name="srch_combo" 		value="<c:out value='${command.srch_combo}'/>" />
			<input type="hidden" name="srch_start_dt" 	value="<c:out value='${command.srch_start_dt}'/>" />
			<input type="hidden" name="srch_end_dt" 	value="<c:out value='${command.srch_end_dt}'/>" />
			
			<input type="hidden" name="annce_knd" id="annce_knd" value="<c:out value='${command.annce_knd}'/>" />
			<input type="hidden" name="seqno"     id="seqno"     value="<c:out value='${command.seqno}'/>" />
			
			<!-- 첨부파일정보 -->
			<input type="hidden" name="fileInfos" 		value="" />
			<input type="hidden" name="file_bigclsfcn" 	value="F007" />
			<input type="hidden" name="file_midclsfcn" 	value="<c:out value='${command.annce_knd}'/>" />
			<input type="hidden" name="file_smlclsfcn" 	value="" />
			<input type="hidden" name="ref_key" 		value="<c:out value='${command.seqno}'/>" />
			<input type="hidden" name="view_gbn" 		value="download" />
			
			<!-- //**************************** Form Setting **************************** -->
			
			<!-- Function Button  -->
			<div class="btnwrap">
				<%if(command.isAuth_modify()){ %> 	
					<span class="btn_all_w"><span class="edit"></span><a href="javascript:modify()"><spring:message code='las.page.button.modify' /></a></span>
				<%} %>
				<%if(command.isAuth_delete()){ %>  
					<span class="btn_all_w"><span class="delete"></span><a href="javascript:remove()"><spring:message code='las.page.button.delete' /></a></span>
				<%} %>
				<span class="btn_all_w"><span class="list"></span><a href="javascript:goList()"><spring:message code='las.page.button.list' /></a></span>
			</div>
			<!-- //Function Button  -->	
					
			<!--  view  -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
					<col width="15%"/>
					<col width="35%"/>
					<col width="15%"/>
					<col width="35%"/>
				</colgroup>
				<tbody>				
<%-- 					<%if("ko".equals(command.getSession_user_locale()) || command.isAuth_modify() ){ %> --%>
<!-- 						<tr> -->
<%-- 						<th><spring:message code='las.page.field.board.notice.title' /><c:if test="${command.auth_modify}"> (Ko)</c:if></th> --%>
<!-- 			            <td colspan="3"> -->
<%-- 			            	<c:out value='${lom.title}' default="" /> --%>
<!-- 			            </td> -->
<!-- 			        	</tr> -->
<%-- 			        <%} %> --%>
			        <%if(command.isAuth_modify() ){ %>
					<tr>
						<th><spring:message code='las.page.field.board.notice.title' /><c:if test="${command.auth_modify}"></c:if></th>
			            <td colspan="3">
			            	<c:out value='${lom.title_en}' default="" />
			            </td>
			        </tr>
			        <%}else{ %>
			        <tr>
						<th><spring:message code='las.page.field.board.notice.title' /></th>
			            <td colspan="3">
			            	<c:out value='${lom.title_en}' default="" />
			            </td>
			        </tr>
			        <%} %>
			        
					<tr>
						<th><spring:message code='las.page.field.board.notice.reg_nm' /></th>
						<td>
							<c:out value='${lom.reg_nm}' default="" />/<c:out value="${lom.jikgup_nm }" />/<c:out value="${lom.dept_nm }" />
						</td>
						<th><spring:message code='las.page.field.board.notice.reg_dt' /></th>
						<td>
							<c:out value='${lom.reg_dt}' default="" />
						</td>
					</tr>
					
					<c:choose>
						<c:when test="${command.annce_knd=='MEMO'}">
							<!-- 공지기간 -->
							<tr>				
								<th><spring:message code="clm.page.field.board.annceDt" /></th>
								<td colspan="3">
									<c:choose>
										<c:when test="${lom.annce_st_ymd!=''}">
											<c:out value = "${lom.annce_st_ymd}"/> ~ <c:out value="${lom.annce_ed_ymd}"></c:out>										
										</c:when>
										<c:when test="${lom.annce_st_ymd==''}">
											<spring:message code="clm.msg.field.board.always"/>
										</c:when>	
									</c:choose>
								</td>
							</tr>
						</c:when>
					</c:choose>
					
<%-- 					<%if("ko".equals(command.getSession_user_locale()) || command.isAuth_modify() ){ %> --%>
<!-- 						<tr> -->
<%-- 						<th><spring:message code="clm.page.msg.common.content" /><c:if test="${command.auth_modify}"> (Ko)</c:if></th> --%>
<!-- 						<td colspan="3"> -->
<%-- 							<c:out value='${lom.cont}' escapeXml="false" />						 --%>
<!-- 						</td> -->
<!-- 						</tr> -->
<%-- 					<%} %> --%>
					<%if(command.isAuth_modify() ){ %>
						<tr>
						<th><spring:message code="clm.page.msg.common.content" /><c:if test="${command.auth_modify}"></c:if></th>
						<td colspan="3">
							<c:out value='${lom.cont_en}' escapeXml="false" />						
						</td>
						</tr>
					<%}else{ %>
						<tr>
						<th><spring:message code="clm.page.msg.common.content" /></th>
						<td colspan="3">
							<c:out value='${lom.cont_en}' escapeXml="false" />						
						</td>
						</tr>
					<%} %>
					<tr>
		            	<th><spring:message code='las.page.field.board.notice.attach_file' /></th>
		            	<td colspan="3">
		            		<!-- Sungwoo Replaced scroll option 2014/08/04 -->
		            		<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe>
		            	</td>
		          	</tr>
				</tbody>
			</table>
			<!-- //view  -->
			
			<!-- Function Button  -->
			<div class="btnwrap mt20">
				<%if(command.isAuth_modify()){ %> 	
					<span class="btn_all_w"><span class="edit"></span><a href="javascript:modify()"><spring:message code='las.page.button.modify' /></a></span>
				<%} %>
				<%if(command.isAuth_delete()){ %>  
					<span class="btn_all_w"><span class="delete"></span><a href="javascript:remove()"><spring:message code='las.page.button.delete' /></a></span>
				<%} %>
				<span class="btn_all_w"><span class="list"></span><a href="javascript:goList()"><spring:message code='las.page.button.list' /></a></span>
			</div>
			<!-- //Function Button  -->
			</form:form>
		</div>
		</div>
		<!-- //content -->			
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
		<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- // footer -->

</body>
</html>