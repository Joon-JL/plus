<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.las.lawinformation.dto.GuideneduForm" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>


<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%--
/**
 * 파  일  명 : ChangeReviewer_l.jsp
 * 프로그램명 : 전자검토자 임시 담당자 지정 목록
 * 작  성  자 : 제이남
 * 작  성  일 : 2013.06
 */
--%>
<%
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html>
<head>
<meta charset="utf-8" />

<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css"    type="text/css" rel="stylesheet">
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value="/script/secfw/common/common.js"/>"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script language="javascript">
	$(function() {
		
	});

	/**
	* 임시담당자 지정화면 호출 function
	*/
	function goInsertForm(){
		var frm 	= document.frm;
		
		frm.method.value 	= "goInsertForm";
		frm.action 			= "<c:url value='/las/elecReviewer/elecReviewer.do' />";	
		frm.target 			= "_self";
		viewHiddenProgress(true);
		frm.submit();	
	}
	
	//담당자 원복
	function returnOrgElecReviewer(){
		var frm 	= document.frm;
		
		if(!confirm("<spring:message code='las.page.field.changRespman013' />")) return; //담당자 원복하시겠습니까?
		
		frm.method.value 	= "returnOrgElecReviewer";
		frm.action 			= "<c:url value='/las/elecReviewer/elecReviewer.do' />";	
		frm.target 			= "_self";
		viewHiddenProgress(true);
		frm.submit();	
	}
	
	function chkClick(tmp_id, comp_cd, comp_nm, org_resp_id, org_resp_nm, tmp_resp_id, tmp_resp_nm){
		var frm 	= document.frm;
		if($('input[name=chkValues]:checked').val()==tmp_id){
			
			frm.tmp_id.value = tmp_id;
			frm.comp_cd.value = comp_cd;
			frm.comp_nm.value = comp_nm;
			frm.org_resp_id.value = org_resp_id;
			frm.org_resp_nm.value = org_resp_nm;
			frm.tmp_resp_id.value = tmp_resp_id;
			frm.tmp_resp_nm.value = tmp_resp_nm;
		}
		else{
			frm.tmp_id.value = null;
			frm.comp_cd.value = null;
			frm.comp_nm.value = null;
			frm.org_resp_id.value = null;
			frm.org_resp_nm.value = null;
			frm.tmp_resp_id.value = null;
			frm.tmp_resp_nm.value = null;
		}
	}
	
	/**
	* 메시지 처리
	*/
	function showMessage(msg) {
		if( msg != "" && msg != null && msg.length > 1 ) {
			alert(msg);
		}
	}
</script>
</head>

<body onLoad='showMessage("<c:out value='${returnMessage}'/>");'>

<div id="wrap">	
	<div id="container">	
		<!-- Location -->
		<div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0" alt="Home" /><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->

		<!-- title -->
		<div class="title">
			<h3><spring:message code='las.page.field.changRespman001' /> <!-- 전자검토자 임시 담당자 지정 --></h3>
		</div>
		<!-- //title -->
					
		<!-- content -->
		<div id="content">
		<div id="content_in">
			<!--**************************** Form Setting ****************************-->
			<form:form name="frm" id='frm' method="post" autocomplete="off"> 
			<!-- search form -->
			<input type="hidden" name="method"  value="" />
			<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>" />	
			
			<input type="hidden" name="tmp_id" id="tmp_id" />
			<input type="hidden" name="comp_cd" id="comp_cd" />	
			<input type="hidden" name="comp_nm" id="comp_nm" />	
			<input type="hidden" name="org_resp_id" id="org_resp_id" />	
			<input type="hidden" name="org_resp_nm" id="org_resp_nm" />	
			<input type="hidden" name="tmp_resp_id" id="tmp_resp_id" />	
			<input type="hidden" name="tmp_resp_nm" id="tmp_resp_nm" />
			<!-- //**************************** Form Setting ****************************-->

			<!-- button -->
			<div class="t_titBtn">
				<div class="btn_wrap_t">
					<span class="btn_all_w" onclick="goInsertForm();"><span class="edit"></span><a><spring:message code='las.page.field.changRespman002' /> <!-- 임시 담당자 지정 --></a></span>
					<span class="btn_all_w" onclick="returnOrgElecReviewer();"><span class="edit"></span><a><spring:message code='las.page.field.changRespman003' /> <!-- 담당자 원복 --></a></span>
				</div>
			</div>
			<!-- //button -->
			<!-- List -->
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
				<colgroup>
					<col width="5%" />
					<col width="10%" />
					<col width="25%" />
					<col width="10%" />
					<col width="10%" />
					<col width="40%" />
				</colgroup>
		    	<thead>
		          <tr>
		            <th><spring:message code='las.page.field.changRespman011' /></th>	
		            <th><spring:message code='las.page.field.changRespman004' /></th>	<!-- 원 담당자 -->
		            <th><spring:message code='las.page.field.changRespman005' /></th>	<!-- 담당회사 -->
		            <th><spring:message code='las.page.field.changRespman006' /></th>	<!-- 임시 담당자 -->
		            <th><spring:message code='las.page.field.changRespman010' /></th>	<!-- 변경일 -->
		            <th><spring:message code='las.page.field.changRespman017' /></th>	<!-- 변경사유 -->
		          </tr>
		        </thead>
			  	<tbody>
			  	<c:choose>
				<c:when test="${!empty resultList }" >
					<c:forEach var="list" items="${resultList}">
				 		<tr>
			            <td class='tC'><input type="radio" name="chkValues" value="<c:out value='${list.tmp_id}'/>" onClick="chkClick('<c:out value='${list.tmp_id}'/>',
			                                                                                   '<c:out value='${list.comp_cd}'/>',
			                                                                                   '<c:out value='${list.comp_nm}'/>',
			                                                                                   '<c:out value='${list.org_resp_id}'/>',
			                                                                                   '<c:out value='${list.org_resp_nm}'/>',
			                                                                                   '<c:out value='${list.tmp_resp_id}'/>',
			                                                                                   '<c:out value='${list.tmp_resp_nm}'/>'			                                                                                   
			                                                                                      );"/></td>
			            <td class='tC'><c:out value='${list.org_resp_nm}'/></td>
			            <td class='tC'><c:out value='${list.comp_nm}'/></td>
			            <td class='tC'><c:out value='${list.tmp_resp_nm}'/></td>
			            <td class='tC'><c:out value='${list.reg_dt}'/></td>
			            <td><c:out value='${list.tmp_cause}'/></td>
			          </tr>
		          </c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td class='tC' colspan='6'><spring:message code="las.msg.succ.noResult" /></td>
					</tr>
				</c:otherwise>
			</c:choose>
			  	</tbody>
	        </table>
			<div class="t_titBtn">
				<!-- //List -->
				<div class="total_num">Total : <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%></div>
				<!-- Paging  -->
				<div class="pagination"> 
				<%=pageUtil.getPageNavi(pageUtil, "") %>
				</div>
				<!-- // Paging -->
			</div>
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