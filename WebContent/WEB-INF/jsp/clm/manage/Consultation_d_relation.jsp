<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%
//ArrayList relationList	= (ArrayList)request.getAttribute("relationList");
%>
<!-- key hidden Form -->
<!-- //key hidden Form -->
<%
//if(relationList.size()==0){
%>
<script language="javascript">
//연관계약정보 탭 숨기기
//function displayTabChecked(){
//	$('#contract-relationInfoTab').attr("style","display:none");
//}
//displayTabChecked();
</script>
<%
//}
%>
<c:choose>
	<c:when test="${command.page_gbn=='modify'}">
		<c:choose>
			<c:when test="${command.prgrs_status =='C04211' or command.prgrs_status=='C04207' or command.prgrs_status=='C04212'}">
			
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
	<tr id="trRelationContract">
		<td><select name="rel_type" id="rel_type" onChange="reltypeChange();"></select></td>
		<td><input type="hidden" name="parent_cntrt_id" id="parent_cntrt_id">
			<input type="text" name="parent_cntrt_name" id="parent_cntrt_name" class="text_search" style="width:80%" readonly="readonly"><img src="<%=IMAGE %>/icon/ico_search.gif" onclick="javascript:popupListContract('C03201');" class="cp" alt="search" /></td>
		<td><select name="rel_defn" id="rel_defn"></select></td>
		<td><input type="text" name="expl" id="expl" class="text_full" style="width:60%">
		<a href="javascript:actionRelationContract('insert','','');"><img src="<%=IMAGE %>/btn/btn_apply.gif"></a></td>
	</tr>
	<tr id="trRelationContract1"></tr>
	<c:choose>
	<c:when test ="${relationListSize > 0}">
		<c:forEach var="list" items="${relationList}" varStatus="i">
			<tr id="trRelationContractCont">	
				<td><c:out value='${list.rel_type_nm}'/></td>
				<td>
					<!-- 신성우 변경처리 CNTRT_ID -> CNSDREQ_ID 2014-04-10 POPUP 호출시 REQ_ID 전달안함-->
                    <a href="javascript:goDetail('<c:out value='${list.cnsdreq_id}'/>');"><c:out value='${list.relation_cntrt_nm}'/></a>
				</td>
				<td><c:out value='${list.rel_defn}'/></td>
				<td>
					<c:out value='${list.expl}'/>
					<a href="javascript:actionRelationContract('delete','<c:out value='${list.parent_cntrt_id}'/>');"><img src="/script/secfw/jquery/uploadify/cancel_new_en.gif"></a>
				</td>
			</td>
		</c:forEach>
		
	</c:when>
	<c:otherwise>
		<tr><td colspan="4">No Data Found. </td></tr>
	</c:otherwise>
	</c:choose>
	</tr>	
</table>	
<table cellspacing="0" cellpadding="0" border="0" class="table-style01 borz01">
	<tr>
		<td>
			※ <spring:message code="clm.page.msg.manage.announce205" /><br>
			&nbsp;<spring:message code="clm.page.msg.manage.forRel" />  <br>
			&nbsp;<spring:message code="clm.page.msg.manage.smRel" /> <br>
			&nbsp;<spring:message code="clm.page.msg.manage.chgCancel" /> <br>
			&nbsp;4) <spring:message code="clm.page.msg.common.etc" />  
		</td>
	</tr>
</table>
			</c:when>
			<c:otherwise>
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
								<td>
									<!-- 신성우 변경처리 CNTRT_ID -> CNSDREQ_ID 2014-04-10 POPUP 호출시 REQ_ID 전달안함-->
				                    <a href="javascript:goDetail('<c:out value='${list.cnsdreq_id}'/>');"><c:out value='${list.relation_cntrt_nm}'/></a>
								</td>
								<td><c:out value='${list.rel_defn}'/></td>
								<td><c:out value='${list.expl}'/></td>
							</tr>		
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr id="trRelationContractCont"><td colspan="4">No Data Found. </td></tr>
					</c:otherwise>
					</c:choose>			
				</table>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
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
						<td>
							<!-- 신성우 변경처리 CNTRT_ID -> CNSDREQ_ID 2014-04-10 POPUP 호출시 REQ_ID 전달안함-->
		                    <a href="javascript:goDetail('<c:out value='${list.cnsdreq_id}'/>');"><c:out value='${list.relation_cntrt_nm}'/></a>
						</td>
						<td><c:out value='${list.rel_defn}'/></td>
						<td><c:out value='${list.expl}'/></td>
					</tr>		
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr id="trRelationContractCont"><td colspan="4">No Data Found. </td></tr>
			</c:otherwise>
			</c:choose>			
		</table>
	</c:otherwise>
</c:choose>	



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
				<th rowspan=<c:out value='${list.relation_cnt*3}'/>><spring:message code="clm.page.field.contract.detail.relation"/></th>	
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
	</table>
 -->	