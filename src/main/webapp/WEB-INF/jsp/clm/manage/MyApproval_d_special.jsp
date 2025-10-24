<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- key hidden Form -->


<table cellspacing="0" cellpadding="0" border="0" class="table-style01 borz01" style="margin-top:0" width="100%">
	<colgroup>
		<col width="14%" />
		<col width="10%" />
		<col width="14%" />
		<col width="16%" />
		<col width="14%" />
		<col width="32%" />
	</colgroup>
	
	<!-- 특화정보  -->

		<c:forEach var="list" items="${considerationSpecialList}">
			<c:if test="${!empty list.attr_value}">
			<tr>	
				<th><c:out value="${list.attr_nm}"/></th>
				<td class="tal_lineL" colspan="5"><c:out value="${list.attr_value}"/></td>
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
	
	<!-- //특화정보 -->
	
<!-- 	
<c:choose>
	<c:when test="${considerationSpecialSize==1}">
		<c:forEach var="list" items="${considerationSpecialList}">
			<tr>	
				<th><c:out value="${list.attr_nm}"/></th>
				<td class="tal_lineL" colspan="5"><c:out value="${list.attr_cont}"/></td>
			</tr>	
		</c:forEach>
	</c:when>
	<c:when test="${considerationSpecialSize==2}">
	<tr>
		<c:forEach var="list" items="${considerationSpecialList}">
		<th><c:out value="${list.attr_nm}"/></th>
		<td class="tal_lineL" colspan="2"><c:out value="${list.attr_cont}"/></td>
		</c:forEach>
	</tr>	
	</c:when>
</c:choose>

<c:choose>
	<c:when test="${consultationSpecialSize==2}">
		<tr>
		<c:forEach var="list" items="${consultationSpecialList}">
			<th><c:out value="${list.attr_nm}"/></th>
			<td class="tal_lineL" colspan="2"><c:out value="${list.attr_cont}"/></td>
		</c:forEach>
		</tr>	
	</c:when>
	<c:when test="${consultationSpecialSize==3}">
	<tr>
		<c:forEach var="list" items="${consultationSpecialList}" varStatus="i">
			<c:if test="${i.count==2}">
				</tr><tr>
			</c:if>
			<th><c:out value="${list.attr_nm}"/></th>
			<c:choose>
				<c:when test="${i.count==1}">
				<td class="tal_lineL" colspan="5"><c:out value="${list.attr_cont}"/></td>
				</c:when>
				<c:otherwise>
				<td class="tal_lineL" colspan="2"><c:out value="${list.attr_cont}"/></td>
				</c:otherwise>
			</c:choose>	
		</c:forEach>
	</tr>	
	</c:when>
	<c:when test="${consultationSpecialSize==4}">
		<tr>
			<c:forEach var="list" items="${consultationSpecialList}" varStatus="i">
			<c:if test="${i.count==3}">
				</tr><tr>
			</c:if>
			<th><c:out value="${list.attr_nm}"/></th>
			<td class="tal_lineL" colspan="2"><c:out value="${list.attr_cont}"/></td>
			</c:forEach>
		</tr>	
	</c:when>
	<c:when test="${consultationSpecialSize==5}">
	<tr>
		<c:forEach var="list" items="${consultationSpecialList}" varStatus="i">
		<c:if test="${i.count==4}">
		</tr><tr>
		</c:if>
		<th><c:out value="${list.attr_nm}"/></th>
		<c:choose>
			<c:when test="${i.count < 4}">
			<td class="tal_lineL"><c:out value="${list.attr_cont}"/></td>
			</c:when>
			<c:otherwise>
			<td class="tal_lineL" colspan="2"><c:out value="${list.attr_cont}"/></td>
			</c:otherwise>
		</c:choose>
		</c:forEach>
	</tr>	
	</c:when>
</c:choose> -->
</table>	

	  