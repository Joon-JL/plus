<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<!-- //key hidden Form -->
<div class="title_basic">
	<h5 class="ntitle05"><spring:message code="clm.page.tab.title.contractexec"/> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'exec-table');"/></h5>
</div>
<!-- button -->
<div class="btn_wrap_t02" id="exec-btn">
	<span class="btn"><span class="add"></span><a href="javascript:addRow();"> <spring:message code="clm.page.button.contract.addrow"/></a></span>
	<span class="btn"><span class="delete"></span><a href="javascript:deleteRow();"> <spring:message code="clm.page.button.contract.deleterow"/></a></span>
</div>
<!-- //button -->
<table cellspacing="0" cellpadding="0" border="0" class="table-style01" id="exec-table">
	<colgroup>
		<col width="16%" />
		<col width="16%" />
		<col width="16%" />
		<col width="16%" />
		<col width="16%" />
		<col width="20%" />
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
		<td class="tal_lineL" id="exec_rm" colspan="2">
			<c:choose>
				<c:when test="${command.prgrs_status=='C04211' or command.prgrs_status=='C04207' or command.prgrs_status=='C04212'}">
			<input type="checkbox" name="del_yn_arr" value="Y" />
			<input type="hidden" name="exec_seqno_arr" value="<c:out value='${list.exec_seqno}'/>"/>
			<input type="text" name="exec_cont_arr" value="<c:out value='${list.exec_cont}'/>" style="width:200px" class="text_full" required maxLength="200" alt="<spring:message code='clm.page.field.contract.consultation.detail.execution' />"/>
				</c:when>
				<c:otherwise>
				<c:out value='${list.exec_cont}'/>
				</c:otherwise>
			</c:choose>	
		</td>
		<th><spring:message code='clm.page.field.contract.consultation.detail.executionplanday' /></th>
		<td colspan="2">~
			<c:choose> 
				<c:when test="${command.prgrs_status=='C04211'or command.prgrs_status=='C04207' or command.prgrs_status=='C04212'}">
			<input type="text" name="exec_plndday_arr" id="exec_plndday_arr<c:out value='${i.count}'/>" value="<c:out value='${list.exec_plndday}'/>"  class="text_calendar02"/ required/>
				</c:when>
				<c:otherwise>
				<c:out value='${list.exec_plndday}'/>
				</c:otherwise>	
			</c:choose>
		</td>
	</tr>
	</c:forEach>
	</tbody>
</table>