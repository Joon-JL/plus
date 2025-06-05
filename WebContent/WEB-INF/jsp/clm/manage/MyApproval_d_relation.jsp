<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<!-- key hidden Form -->
<!-- //key hidden Form -->
<!-- 
<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
	<colgroup>
		<col width="15%" />
		<col width="9%" />
		<col width="21%" />
		<col width="14%" />
		<col width="12%" />
		<col width="12%" />
		<col width="17%" />
	</colgroup>
	<tbody id="relationTbody">
	<c:forEach var="list" items="${relationList}" varStatus="i">
		<tr id="relationTR">
			<c:if test="${i.count == '1'}">
				<th rowspan="12"><spring:message code="clm.page.field.contract.detail.relation"/></th>	
			</c:if>						
			<td class="tal_lineL"><span class="th-color"><spring:message code="clm.page.field.contract.detail.relation.name"/></span></td>					
			<td colspan="5"><c:out value='${list.relation_cntrt_nm}'/></td>
		</tr>
		<tr>
			<td class="tal_lineL"><span class="th-color"><c:out value='${list.rel_type_nm}'/></span></td>
			<td class="tal_lineL" td colspan="5">
				<c:out value='${list.first_cntrt_nm}'/> <br/>
				<c:out value='${list.second_cntrt_nm}'/>  
				<c:out value='${list.rel_defn}'/>
			</td>
		</tr>
		<tr>
			<td class="tal_lineL">
				<span class="th-color"><spring:message code='clm.page.field.refcontract.cont'/></span>
			</td>
			<td class="tal_lineL" td colspan="5">
				<c:out value='${list.expl}'/>							
			</td>
		</tr>	
	</c:forEach>	
	</table> -->
	
<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
	<colgroup>
		<col width="12%" />
		<col width="50%" />
		<col width="10%" />
		<col/>					
	</colgroup>
	<tbody id="relationTbody">
	<tr id="trRelationContract">
		<th>RelationType</th>
		<th><spring:message code="clm.page.msg.manage.relCont" /></th>
		<th><spring:message code="clm.page.msg.manage.define" /></th>
		<th><spring:message code="clm.page.msg.manage.relDetail" /></th>
	</tr>	
	<c:choose>
	<c:when test ="${relationListSize > 0}">
		<c:forEach var="list" items="${relationList}" varStatus="i">
			<tr id="trRelationContractCont">
				<td><c:out value='${list.rel_type_nm}'/></td>
				<td><c:out value='${list.relation_cntrt_nm}'/></a>
				<td><c:out value='${list.rel_defn}'/></td>
				<td><c:out value='${list.expl}'/></td>
			</tr>		
		</c:forEach>
	</c:when>
	<c:otherwise>
		<tr id="trRelationContractCont"><td colspan="4">No Data Found</td></tr>
	</c:otherwise>
	</c:choose>			
</table>	
	
