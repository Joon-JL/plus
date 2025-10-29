<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
/*
화면별로 특화정보 <table> 구성이 틀려서 페이지 플래그로 구분하기위해
 PG001 : 계약체결
 PG002 : 계약등록
*/
HttpSession ss_sflag = request.getSession(false);
 
String sp_flag = (String)ss_sflag.getAttribute("special_page_flag") == null ? "PG001" : (String)ss_sflag.getAttribute("special_page_flag");
ss_sflag.setAttribute("special_page_flag",null); //초기화
%>

<!-- key hidden Form -->
<%if(sp_flag.equals("PG001")) {%>
<table class="table-style01 borz01"  style="DISPLAY: block" cellSpacing=0 cellPadding=0 width="100%" border=0>
	<colgroup>
		<col width="16%" />
		<col width="16%" />
		<col width="16%" />
		<col width="16%" />
		<col width="16%" />
		<col width="20%" />
	</colgroup>

<%}else if(sp_flag.equals("PG002")) {%>
<TABLE class="table-style01 borz01"  style="DISPLAY: block" cellSpacing=0 cellPadding=0 width="100%" border=0>
	<COLGROUP>
	<COL width="14%">
	<COL width="10%">
	<COL width="14%">
	<COL width="16%">
	<COL width="14%">
	<COL width="32%"></COLGROUP>
<%} %>

	<TBODY>
 		<c:forEach var="list" items="${considerationSpecialList}">
 			<c:if test="${!empty list.attr_value}">
			<tr>	
				<th><c:out value="${list.attr_nm}"/></th>
				<td colspan="5"><c:out value="${list.attr_value}"/></td>
			</tr>
			</c:if>	
		</c:forEach>
	
	
		<c:forEach var="list" items="${consultationSpecialList}">
			<c:if test="${!empty list.attr_value}">
		  	<tr>
				<th><c:out value="${list.attr_nm}"/></th>
				<td class="tal_lineL" colspan="5"><c:out value="${list.attr_value}"/></td>
		  	</tr>
		  	</c:if>
		</c:forEach>
				
	</TBODY>	  
</table>	

	  