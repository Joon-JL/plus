<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<div class="title_basic">
	<h5 class="ntitle05"><spring:message code="clm.page.tab.title.contractexec"/> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'exec-table');"/></h5>
</div>
<table cellspacing="0" cellpadding="0" border="0" class="table-style01" id="exec-table">
	<colgroup>
		<col width="13%" />
		<col width="10%" />
		<col width="11%" />
		<col width="14%" />
		<col width="11%" />
		<col width="10%" />
		<col width="10%" />
		<col />
	</colgroup>
	<tbody id="execTbody">
	<c:forEach var="list" items="${execList}" varStatus="i">
	<tr id="exec-tr">
		<c:choose>
			<c:when test="${list.rm == 1}">
				<c:choose>
					<c:when test="${list.rowspan_cnt == 0}">
		<th id="exec-title"><spring:message code='clm.page.field.contract.consultation.detail.execution' /></th>
					</c:when>
					<c:otherwise>
		<th id="exec-title" rowspan="<c:out value='${list.rowspan_cnt}'/>"><spring:message code='clm.page.field.contract.consultation.detail.execution' /></th>
					</c:otherwise>	
				</c:choose>
			</c:when>
		</c:choose>		
		<td class="tal_lineL" id="exec_rm" colspan="5">
			<c:out value='${list.exec_cont}'/>
		</td>
		<th><spring:message code='clm.page.field.contract.consultation.detail.executionplanday' /></th>
		<td>
			~ <c:out value='${list.exec_plndday}'/>
		</td>	
	</tr>
	</c:forEach>
	</tbody>
</table>