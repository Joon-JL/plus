<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>
<%@ page import="com.sec.las.lawinformation.dto.MainLawInfoForm" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%--
/**
 * 파  일  명 : MainLawInfo_d.jsp
 * 프로그램명 : 주요입법동향/법무사례 상세
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 2011.08
 */
 --%>
<%
	MainLawInfoForm command = (MainLawInfoForm)request.getAttribute("command");
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	
	ListOrderedMap lom = (ListOrderedMap)request.getAttribute("lom"); 
	
%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta charset="utf-8" />

<title><spring:message code="las.main.title" /></title>
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
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
			frm.action = "<c:url value='/las/lawinformation/mainLawInfo.do' />";
		    frm.method.value = "forwardModifyMainLawInfo";

		    viewHiddenProgress(true) ;
			frm.submit();

		} else if(flag == "delete"){	

			if(confirm("<spring:message code='las.msg.ask.delete'/>")){
				frm.target = "_self";
				frm.action = "<c:url value='/las/lawinformation/mainLawInfo.do' />";
			    frm.method.value = "deleteMainLawInfo";

			    viewHiddenProgress(true) ;
			    frm.submit();
			}
			
		} else if(flag == "list"){	

			frm.target = "_self";
			frm.action = "<c:url value='/las/lawinformation/mainLawInfo.do' />";
		   	frm.method.value = "listMainLawInfo";

		    viewHiddenProgress(true) ;
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
	    frm.action = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value  	= "forwardClmsFilePage";
		
		frm.submit();	
	}	
	
//-->

</script>

</head>
<body onLoad='showMessage("<c:out value='${returnMessage}'/>");'>


<div id="wrap">
	<!-- container -->
	<div id="container">
		<!-- Location -->
		<div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0" alt="Home" /><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->
		
		<!-- Title -->
		<div class="title">
		<c:choose>
			<c:when test="${command.info_gbn=='C00405'}">
		   		<h3><spring:message code="las.page.title.lawinformation.mayorLegTrends" /></h3>
			</c:when>
			<c:when test="${command.info_gbn=='C00406'}">
				<h3><spring:message code="las.page.title.lawinformation.mayorLagCases" /></h3>
			</c:when>
		</c:choose>
		</div>
		<!-- //title -->
		
		<!-- content -->
		<div id="content">
		<div id="content_in">
			<!-- **************************** Form Setting **************************** -->
			<form:form name="frm" method="post" autocomplete="off">
			
			<!-- search form -->
			<input type="hidden" name="method"   value="" />
			<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />
			<input type="hidden" name="srch_start_dt" value="<c:out value='${command.srch_start_dt}'/>" />
			<input type="hidden" name="srch_end_dt"   value="<c:out value='${command.srch_end_dt}'/>" />
			<input type="hidden" name="srch_combo" value="<c:out value='${command.srch_combo}'/>" />
			<input type="hidden" name="srch_name" value="<c:out value='${command.srch_name}'/>" />
			<input type="hidden" name="srch_mainlawexam" value="<c:out value='${command.srch_mainlawexam}'/>" />
			<input type="hidden" name="srch_dmstfrgn_gbn" value="<c:out value='${command.srch_dmstfrgn_gbn}'/>" />
			<input type="hidden" name="srch_nation"   value="<c:out value='${command.srch_nation}'/>" />
			
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
			
			<!-- Function Button  -->
			<div class="btnwrap">
				<%if(command.isAuth_modify()){ %> 	
					<span class="btn_all_w"><span class="edit"></span><a href="javascript:pageAction('modify')"><spring:message code='las.page.button.modify' /></a></span>
				<%} %>
				<%if(command.isAuth_delete()){ %>  
					<span class="btn_all_w"><span class="delete"></span><a href="javascript:pageAction('delete')"><spring:message code='las.page.button.delete' /></a></span>
				<%} %>
				<span class="btn_all_w"><span class="list"></span><a href="javascript:pageAction('list')"><spring:message code='las.page.button.list' /></a></span> 
			</div>
			<!-- //Function Button  -->
			
			<!--  view  -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
					<col width="12%" />
					<col width="21%" />
					<col width="12%" />
					<col width="21%" />
					<col width="12%" />
					<col width="22%" />
				</colgroup>
				<tbody>
					<c:choose>
						<c:when test="${command.info_gbn=='C00405' }">
				        	<tr>
								<th><spring:message code="las.page.field.board.notice.title" /></th>
					            <td colspan="3"><c:out value='${lom.title}' default="" /></td>
					            <th><spring:message code="las.page.field.board.notice.reg_nm" /></th>
					            <td><c:out value='${lom.reg_nm}' default="" />/<c:out value="${lom.jikgup_nm }" />/<c:out value="${lom.dept_nm }" /></td>
				        	</tr>
				        	<tr>
					            <th><spring:message code="las.page.field.board.notice.reg_dt" /></th>
					        	<td><c:out value='${lom.reg_dt}' default="" /></td>
					            <th><spring:message code="las.page.field.lawinformation.classfy" /></th>
					        	<td><c:out value='${lom.branch_nm}' default="" /></td>
					          	<th><spring:message code='las.page.field.lawinformation.country' /></th> 
					        	<td><c:out value='${lom.nation_nm}' default="" /></td>
				        	</tr>
			        	</c:when>
						<c:when test="${command.info_gbn=='C00406' }">
			        		<tr>
			        			<th><spring:message code="las.page.field.lawinformation.law_gbn"/></th>
					            <td colspan="5"><c:out value='${lom.branch_nm}' default="" /></td>
			        		</tr>
			        		<tr>
			        			<th><spring:message code="las.page.field.board.notice.title" /></th>
					            <td colspan="5"><c:out value='${lom.title}' default="" /></td>
					        </tr>
			        		<tr>
			        			<th><spring:message code="las.page.field.board.notice.reg_nm" /></th>
					            <td colspan="3"><c:out value='${lom.reg_nm}' default="" />/<c:out value="${lom.jikgup_nm }" />/<c:out value="${lom.dept_nm }" /></td>
					            <th><spring:message code='las.page.field.lawinformation.country' /></th> 
					        	<td><c:out value='${lom.nation_nm}' default="" /></td>
			        		</tr>
						</c:when>
			        </c:choose>
		        	<tr>
			        	<th><spring:message code="clm.page.msg.common.content" /></th>
			           	<td colspan="5">
<%-- 			          		<c:out value='${lom.cont}' escapeXml="false" /> --%>
			          		<%=StringUtil.convertEnterToBR((String)lom.get("cont"))%>
					   	</td>
			       	</tr>
					<tr>
			    		<th><spring:message code="las.page.field.board.notice.attach_file" /></th>
						<td colspan="5">
							<!-- Sungwoo Replaced scroll option 2014/08/04 -->
		            		<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" class='addfile_iframe_d' scrolling="auto" ></iframe>
		            	</td>
		          	</tr>
				</tbody>
			</table>
			<!-- //view  -->
			<!-- Function Button  -->
			<div class="btnwrap mt20">
				<%if(command.isAuth_modify()){ %> 	
					<span class="btn_all_w"><span class="edit"></span><a href="javascript:pageAction('modify')"><spring:message code='las.page.button.modify' /></a></span>
				<%} %>
				<%if(command.isAuth_delete()){ %>  
					<span class="btn_all_w"><span class="delete"></span><a href="javascript:pageAction('delete')"><spring:message code='las.page.button.delete' /></a></span>
				<%} %>
				<span class="btn_all_w"><span class="list"></span><a href="javascript:pageAction('list')"><spring:message code='las.page.button.list' /></a></span> 
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