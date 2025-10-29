﻿<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%--
/**
 * 파  일  명 : CntrBasicAttrMng_l.jsp
 * 프로그램명 : 계약속성관리 - 리스트
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><spring:message code="clm.main.title" /></title>
<link href="<%=CSS %>/clm.css" type="text/css" rel="stylesheet" />
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>

<script language="javascript">
	/**
	* 메시지 처리
	*/
	function showMessage(msg) {
		if(msg != "" && msg != null && msg.length > 1) {
			alert(msg);
		}
	}
	
	/**
	* 검색
	*/
	function goSearch(){
		var frm = document.frm;
		
		viewHiddenProgress(true) ;
		
		frm.curPage.value = 1;
		frm.method.value = "listCntrBasicAttrMng";
		frm.action = "<c:url value='/clm/admin/cntrBasicAttrMng.do' />";
		frm.target = "_self";
		frm.submit();
	}
	
	/**
	* 상세 이동
	*/
	function goDetail(seq){
		var frm = document.frm;
		viewHiddenProgress(true) ;

		frm.attr_seqno.value = seq;
		frm.method.value = "detailCntrBasicAttrMng";
		frm.action = "<c:url value='/clm/admin/cntrBasicAttrMng.do' />";
		frm.target = "_self";
		frm.submit();
	}
	
	/**
	* 페이지 이동
	*/
	function goPage(page) {
		var frm = document.frm;
		viewHiddenProgress(true) ;
		
		frm.curPage.value = page;
		frm.method.value = "listCntrBasicAttrMng";
		frm.action = "<c:url value='/clm/admin/cntrBasicAttrMng.do' />";
		frm.target = "_self";
		frm.submit();
	}

	
</script>
</head>
<body onload='showMessage("<c:out value='${returnMessage}'/>");'>
<form:form name="frm" id="frm" method="post" autocomplete="off">
<input type="hidden" name="method" 		value=""/>
<input type="hidden" name="menu_id" 	value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage" 	value="<c:out value='${command.curPage}'/>" />

<input type="hidden" name="attr_seqno" value="<c:out value='${command.attr_seqno}'/>"/>

<input type="hidden" name="approval_option" value=""/>

<div id="wrap">
	<!-- container -->
	<div id="container">
		
		<!-- location -->
		<div class="location"><IMG SRC="<%=IMAGE %>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //location -->

		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.title.cntrBasicAttrMng.listTitle" /></h3>
		</div>
		<!-- //title -->	
		
		<!-- content -->
		<div id="content">

			
			<div class="search_box">
				<div class="search_box_inner">
					<table class="search_tb">
						<colgroup>
							<col/>
							<col width="75px"/>
							</colgroup>
							<tr>
							<td>
							<table class="search_form">
								<colgroup>
									<col width="10%"/>
                                   <col />
			                   </colgroup>
								<tr>
								<th><spring:message code="clm.page.field.cntrBasicAttrMng.infoItemNm" /></th>
								<td>
									<input type="text" name="srch_info_itm" id="srch_info_itm" value="<c:out value='${command.srch_info_itm}'/>" style="width:300px" class="text_full" onKeyPress="if(event.keyCode==13) {goSearch();return false;}" maxLength="25"/>
								</td>
								<td> </td>
								<td> </td>
								</tr>
							</table>
						</td>
						<td class="tC"><a href="javascript:goSearch();"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="clm.page.msg.common.inquire" htmlEscape="true" />"/></a></td>
						</tr>
					</table>
				</div>
          	</div>
			<!-- search-->
			
			<!-- button -->
			<div class="t_titBtn">
				
			</div>
			<!-- //button -->
			
			<!-- list -->
			<table class="list_basic">
				<colgroup>
			    	<col width="45px"/>
					<col />
			    	<col width="70px" />
				   	<col width="70px" />
				   	<col width="60px" />
				   	<col width="90px" />
				   	<col width="60px" />
				   	<col width="60px" />
				   	<col width="70px" />
				   	<col width="50px" />
					<col width="50px" />
		      	</colgroup>
				
				<thead>
				<tr>
			    	<th rowspan="2">No</th> <!-- No  -->
			      	<th rowspan="2"><spring:message code="clm.page.field.cntrBasicAttrMng.infoItem" /></th> 			<!-- 정보항목 -->
			      	<th colspan="3"><spring:message code="clm.page.field.cntrBasicAttrMng.cntrLawExam" /></th> 			<!-- 계약서 법무검토 -->
			      	<th colspan="3"><spring:message code="clm.page.field.cntrBasicAttrMng.cntrConclusionNobr" /></th> 		<!-- 계약체결 -->
			      	<th rowspan="2"><spring:message code="clm.page.field.cntrBasicAttrMng.conclusionRegist" /></th> 	<!-- 체결본등록 -->
			      	<th rowspan="2"><spring:message code="clm.page.field.cntrBasicAttrMng.execMng" /></th> 				<!-- 이행관리 -->
				  	<th rowspan="2"><spring:message code="clm.page.field.cntrBasicAttrMng.closeMng" /></th> 			<!-- 종료관리 -->
		        </tr>
				<tr>
					<th class="tal_lineL tal_bg_cor01"><spring:message code="clm.page.field.cntrBasicAttrMng.cntrDraft" /></th> 	<!-- 계약서초안작성 -->
					<th class="tal_bg_cor01"><spring:message code="clm.page.field.cntrBasicAttrMng.cntrExamRequest" /></th> 					<!-- 계약검토의뢰 -->
					<th class="tal_bg_cor01"><spring:message code="clm.page.field.cntrBasicAttrMng.cntrExam" /></th> 						<!-- 계약검토 -->
					<th class="tal_bg_cor01"><spring:message code="clm.page.field.cntrBasicAttrMng.conclusionExam" /></th> 					<!-- 체결예정본 검토 -->
					<th class="tal_bg_cor01"><spring:message code="clm.page.field.cntrBasicAttrMng.conclusionConsult" /></th> 				<!-- 체결품의 -->
					<th class="tal_bg_cor01"><spring:message code="clm.page.field.cntrBasicAttrMng.cntrConclusion" /></th> 					<!-- 계약체결 -->
				</tr>
				</thead>
			
				<tbody>
				<c:choose>
					<c:when test="${pageUtil.totalRow > 0}">
						<c:forEach var="list" items="${lom}">
						<tr>
						    <td><c:out value='${list.rn}'/></td>
							<td class="tL">
								<a href='javascript:goDetail("<c:out value='${list.attr_seqno}'/>");'><c:out value='${list.info_itm}'/></a>
				          	</td>
				          	<td>
				          		<c:choose>
				          			<c:when test="${list.crtn_depth=='C04001'}">
				          				<input name="" type="radio" disabled="disabled" class="radio" value="" checked="checked" />
				          			</c:when>
				          			<c:otherwise>
				          				<input name="" type="radio" disabled="disabled" class="radio" value=""/>
				          			</c:otherwise>
				          		</c:choose>
				          	</td>
							<td>
								<c:choose>
				          			<c:when test="${list.crtn_depth=='C04002'}">
				          				<input name="" type="radio" disabled="disabled" class="radio" value="" checked="checked" />
				          			</c:when>
				          			<c:otherwise>
				          				<input name="" type="radio" disabled="disabled" class="radio" value=""/>
				          			</c:otherwise>
				          		</c:choose>
							</td>
							<td>
								<c:choose>
				          			<c:when test="${list.crtn_depth=='C04003'}">
				          				<input name="" type="radio" disabled="disabled" class="radio" value="" checked="checked" />
				          			</c:when>
				          			<c:otherwise>
				          				<input name="" type="radio" disabled="disabled" class="radio" value=""/>
				          			</c:otherwise>
				          		</c:choose>
							</td>
							<td>
								<c:choose>
				          			<c:when test="${list.crtn_depth=='C04004'}">
				          				<input name="" type="radio" disabled="disabled" class="radio" value="" checked="checked" />
				          			</c:when>
				          			<c:otherwise>
				          				<input name="" type="radio" disabled="disabled" class="radio" value=""/>
				          			</c:otherwise>
				          		</c:choose>
							</td>
							<td>
								<c:choose>
				          			<c:when test="${list.crtn_depth=='C04005'}">
				          				<input name="" type="radio" disabled="disabled" class="radio" value="" checked="checked" />
				          			</c:when>
				          			<c:otherwise>
				          				<input name="" type="radio" disabled="disabled" class="radio" value=""/>
				          			</c:otherwise>
				          		</c:choose>
							</td>
							<td>
								<c:choose>
				          			<c:when test="${list.crtn_depth=='C04006'}">
				          				<input name="" type="radio" disabled="disabled" class="radio" value="" checked="checked" />
				          			</c:when>
				          			<c:otherwise>
				          				<input name="" type="radio" disabled="disabled" class="radio" value=""/>
				          			</c:otherwise>
				          		</c:choose>
							</td>
							<td>
								<c:choose>
				          			<c:when test="${list.crtn_depth=='C04007'}">
				          				<input name="" type="radio" disabled="disabled" class="radio" value="" checked="checked" />
				          			</c:when>
				          			<c:otherwise>
				          				<input name="" type="radio" disabled="disabled" class="radio" value=""/>
				          			</c:otherwise>
				          		</c:choose>
							</td>
							<td>
								<c:choose>
				          			<c:when test="${list.crtn_depth=='C04008'}">
				          				<input name="" type="radio" disabled="disabled" class="radio" value="" checked="checked" />
				          			</c:when>
				          			<c:otherwise>
				          				<input name="" type="radio" disabled="disabled" class="radio" value=""/>
				          			</c:otherwise>
				          		</c:choose>
							</td>
							<td>
								<c:choose>
				          			<c:when test="${list.crtn_depth=='C04009'}">
				          				<input name="" type="radio" disabled="disabled" class="radio" value="" checked="checked" />
				          			</c:when>
				          			<c:otherwise>
				          				<input name="" type="radio" disabled="disabled" class="radio" value=""/>
				          			</c:otherwise>
				          		</c:choose>
							</td>
						</tr>
						</c:forEach>
				    </c:when>
				    <c:otherwise>
						<tr>
							<td colspan="11" align="center"><spring:message code="clm.msg.succ.noResult" /></td>
						</tr>
				    </c:otherwise>
				</c:choose>
			</tbody>
			</table>
			<!-- //list -->
		 	<div class="total_num">Total: <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%></div>
		 	<!-- pagination -->
			<div class="pagination">
				<%=pageUtil.getPageNavi(pageUtil, "") %>
			</div>
			<!-- //pagination -->						
		</div>
		<!-- //content -->

		<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js"></script>
		<!-- // footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>				
</form:form>
</body>
</html>