﻿<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%--
/**
 * 파  일  명 : Approval_d.jsp
 * 프로그램명 : 결재 승인자 - 상세
 * 설      명 : 
 * 작  성  자 : 박정훈
 * 작  성  일 : 2014.06.03
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%@ include file="/WEB-INF/jsp/common/messageUtil.jsp" %>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta charset="utf-8" />
<meta http-equiv="x-ua-compatible" content="IE=edge" >
<title><spring:message code="las.main.title" /></title>
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>


<script language="javascript">

	
	//목록가기
	function goList() { 
		var frm = document.frm;

		frm.method.value = "listApprovalPath";
		frm.action = "<c:url value='/clm/admin/approvalPath.do' />";
		frm.target = "_self";
		viewHiddenProgress(true);
		frm.submit();
	}
	//수정
	function mod() {
		var frm = document.frm;
		
		    	frm.method.value = "forwardModifyApprovalPath";
				frm.action = "<c:url value='/clm/admin/approvalPath.do' />";
				frm.target = "_self";
				viewHiddenProgress(true);
				frm.submit();
		
	}
	//삭제
	function delInfo() {
		var frm = document.frm;
		
	    if(confirm("<spring:message code='secfw.msg.ask.delete' />")){
	    	frm.method.value = "deleteApprovalPath";
			frm.action = "<c:url value='/clm/admin/approvalPath.do' />";
			frm.target = "_self";
			viewHiddenProgress(true);
			frm.submit();
			
			}
	
		}


	
</script>
</head>
<body>

<div id="wrap">
	<!-- container -->
	<div id="container">
		<!-- location -->
		<div class="location"><img src="<%=IMAGE %>/icon/ico_home.gif" width="13" height="11" border="0" alt="home"><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //location -->
		
		<!-- title -->
		<div class="title">
			<h3><spring:message code="secfw.page.field.approvalPath.Information" /></h3><!--Approval Path Information-->
		</div>
		<!-- //title -->
		<!-- content -->
		<div id="content">
		<div id="content_in">
			<!-- title -->
			<div class="title_basic">
				<h4><spring:message code="secfw.page.field.approvalPath.Information" /></h4><!--Approval Path Information-->
			</div>
			<!-- //title -->
		
			<form:form name="frm" id="frm" method="post" autocomplete="off">
			<input type="hidden" name="method"   value="" />
			<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />
			<input type="hidden" name="path_id"  	 		value="<c:out value='${lom.path_id}'/>" />

			<!--assign user parameter  -->
			<input type="hidden" id="Ass_no" name="Ass_no" value="1" />
			<input type="hidden" id="cod_no" name="cod_no" value="1" />
			<!-- insert parameter -->
			<input type="hidden" id="src_no" name="src_no" value="" />
			<input type="hidden" id="ass_id_list" name="ass_id_list" value="" />
			<input type="hidden" id="input_condi_list" name="input_condi_list" value="" />
			<input type="hidden" id="input_condi_option" name="input_condi_option" value="" />
			<input type="hidden" id="input_condi_val" name="input_condi_val" value="" />
			<input type="hidden" id="atype_val" name="atype_val" value="" />
			<input type="hidden" id="rtype_val" name="rtype_val" value="" />

			<input type="hidden" name="type_3"	value="<c:out value='${lom.type_3}'/>" />

			
			<!-- 결재선검색 -->
			<input type='hidden' name='srch_user_cntnt_type' />
			<input type='hidden' name='srch_user_cntnt' />
			<input type='hidden' name='doSearch' value='Y' />
					
			<!-- button -->
			<div class="btnwrap">
				<span class="btn_all_w"><span class="save"></span><a href="javascript:mod()"><spring:message code='clm.page.button.modify' /></a></span>
				<span class="btn_all_w"><span class="del"></span><a href="javascript:delInfo()"><spring:message code='clm.page.button.delete' /></a></span>
				<span class="btn_all_w"><span class="list"></span><a href="javascript:goList()"><spring:message code='clm.page.button.list' /></a></span>
			</div>
			<!-- //button -->
					
			<!--  view  -->
			<table class="table-style01">
				<colgroup>
					<col width="10%"/>
					<col width="40%"/>
					<col width="10%"/>
					<col width="*"/>
				</colgroup>
				<tbody>
					<tr>
						<th><spring:message code="secfw.page.field.approvalPath.PathName" /></th><!-- Path Name -->
						<td colspan="3"><c:out value='${lom.path_nm}'/></td>
					</tr>
					<tr>
						<th><spring:message code="secfw.page.field.approvalPath.Description" /></th><!-- Description-->
						<td colspan="3"><c:out value='${lom.comments}'/></td>
					</tr>
					<tr>
						<th><spring:message code="secfw.page.field.approvalPath.CreatedBy" /></th><!-- Created By-->
						<td><c:out value='${lom.reg_id}'/></td>
						<th><spring:message code='clm.page.field.admin.type.reg_dt' /></th>
						<td><c:out value='${lom.reg_dt}'/></td>
					</tr>
					<tr>
						<th><spring:message code="secfw.page.field.approvalPath.Priority" /></th><!-- Priority -->
						<td><c:out value='${lom.priority}'/></td>
						<th><spring:message code="secfw.page.field.approvalPath.Mandatory" /></th><!-- Mandatory -->
						<td><c:if test="${lom.mandatory eq 'M'}">Mandatory </c:if>
							<c:if test="${lom.mandatory eq 'O'}">Option </c:if>
						</td>
					</tr>
					<tr>
						<!-- 지법인 선택 -->
						<th class='head'><spring:message code="las.page.filed.userLoc"/></th>
						<td colspan="3">
							<c:out value='${lom.comp_cd}'/>
							<input type="hidden" id="loc_gbn" name="loc_gbn" value='<c:out value='${lom.comp_cd}'/>' />
						</td>
					</tr>
					<tr class="end">
						<th><spring:message code="secfw.page.field.approvalPath.Active" /></th><!-- Active -->
						<td colspan="3"><c:out value='${lom.use_yn}'/></td>
					</tr>
				</tbody>
			</table>
			
			
			<!-- title -->
			<div class="title_basic">
				<h4><spring:message code="secfw.page.field.approvalPath.Condition" /></h4><!-- Condition -->
			</div>
			<!--  view  -->
			<table class="table-style01">
				<colgroup>
					<col width="10%"/>
					<col width="*%"/>
				</colgroup>
				<tbody>
					<tr>
						<th><spring:message code="secfw.page.field.approvalPath.ContractType" /></th><!-- Contract Type -->
						<td><c:out value='${lom.type_1_nm}'/>&nbsp;/&nbsp;<c:out value='${lom.type_2_nm}'/>&nbsp;/&nbsp;<c:out value='${lom.type_3_nm}'/>&nbsp;/&nbsp;<c:out value='${lom.type_4_nm}'/></td>
					</tr>
					<tr class="end">
						<th><spring:message code="secfw.page.field.approvalPath.ConditionDetail" /></th><!-- Condition Detail -->
						<td>
							<c:if test="${lom2.operation eq 'AND'}">AND(All items are true) </c:if>
							<c:if test="${lom2.operation eq 'OR'}">OR(Atleast one item is true) </c:if></br>
						<c:choose>
							<c:when test="${resultdetailsize > 0}">
							<c:forEach var="list" items="${resultListDETAIL}" varStatus="i">
							<c:choose>
								<c:when test="${list.condition eq 'C001'}">Amount</c:when>
								<c:when test="${list.condition eq 'C002'}">Automatic Extension</c:when>
								<c:when test="${list.condition eq 'C003'}">Mandatory provisions</c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
							
							<c:choose>
								<c:when test="${list.condition_option eq '1'}">></c:when>
								<c:when test="${list.condition_option eq '2'}">=</c:when>
								<c:when test="${list.condition_option eq '3'}"><</c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
							<c:out value='${list.condition_val}'/>
							
							</br>
							</c:forEach>
							</c:when>
						</c:choose>
						</td>
					</tr>
				</tbody>
			</table>
			
			
			<!-- title -->
			<div class="title_basic">
				<h4><spring:message code="secfw.page.field.approvalPath.ApprovalPathList" /></h4><!-- Approval Path List -->
			</div>	
	            <div id="div_rel_contract">
	            <table cellspacing="0" cellpadding="0" border="0" class="table-style01" id="trRelationContract">
	                <colgroup>
	                    <col width="10%" />
	                    <col width="20%" />
	                    <col width="15%" />
	                    <col width="*%" />
	                    <col/>
	                </colgroup>
	                <tr>
	                    <th class="tC"><spring:message code="secfw.page.field.approvalPath.No" /></th><!-- No -->
	                    <th class="tC"><spring:message code="secfw.page.field.approvalPath.ApprovalType" /></th> <!-- Approval Type -->
	                    <th class="tC"><spring:message code="secfw.page.field.approvalPath.RoleUser" /></th><!-- Role/User -->
	                    <th class="tC"><spring:message code="secfw.page.field.approvalPath.Approver" /></th><!-- Approver -->
	                </tr>             
	                <c:forEach var="list2" items="${resultListUser}" varStatus="i">
		                <tr>
		                	<td class='tC'><c:out value='${list2.sort_no}'/></td>
	                     	<td class='tC'><c:if test="${list2.a_type eq '1'}"><spring:message code="secfw.page.field.approvalPath.Approve" /></c:if>
	                     		<c:if test="${list2.a_type eq '2'}">Consent</c:if>
	                     	</td>
	                     	<td class='tC'><c:if test="${list2.r_type eq 'r'}"><spring:message code="secfw.page.field.approvalPath.Role" /></c:if>
	                     		<c:if test="${list2.r_type eq 'u'}">USER</c:if>
	                     	</td>
	                     	<td class='tC'>
	                    		<c:out value='${list2.user_nm}'/>
	                    	</td>
		                </tr>
	                </c:forEach>  
	            </table>
            </div>
			<!-- //view  -->
			
			<!-- button -->
			<div class="btnwrap">
				<span class="btn_all_w"><span class="save"></span><a href="javascript:mod()"><spring:message code='clm.page.button.modify' /></a></span>
				<span class="btn_all_w"><span class="del"></span><a href="javascript:delInfo()"><spring:message code='clm.page.button.delete' /></a></span>
				<span class="btn_all_w"><span class="list"></span><a href="javascript:goList()"><spring:message code='clm.page.button.list' /></a></span>
			</div>
			<!-- //button -->
			
			</form:form>
		</div>
		</div>
		<!-- //content -->
		
		
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
		<!-- footer -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- //footer -->
</body>
</html>