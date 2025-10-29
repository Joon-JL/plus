<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%-- 
/**
 * 파  일  명 : Execution_exe.jsp
 * 프로그램명 : 이행정보 조회 목록
 * 설      명 : 
 * 작  성  자 : 신승완
 * 작  성  일 : 2011.09
 */
--%>
<!-- key hidden Form -->
<input type="hidden" name="exec_seqno"	id="exec_seqno"    value="<c:out value='${executionCommand.exec_seqno}'/>" />
<input type="hidden" name="exec_status"	id="exec_status"    value="" />
<input type="hidden" name="checkYN"	id="checkYN"    value="${checkYN }" />
<input type="hidden" id="payment_gbn" name="payment_gbn" value="<c:out value='${payment_gbn}'/>"/>
<div id="mainExecutionDetail-Content" style="width:100%; clear:both; border-top:2px solid #3E74BE; _border-left:1px solid #fff; margin:0; table-layout:fixed;">

<div class="tab_box">
	<ul id="executionDetail" class="tab_basic05">
	<c:if test="${payment_gbn=='C02001' or payment_gbn=='C02002' }">
		<li id="execution-DetailTab" class="on"><a href='javascript:'><spring:message code="clm.page.msg.manage.sendPlan" /></a></li>
	</c:if>
	<c:if test="${payment_gbn=='C02001' or payment_gbn=='C02003' }">	
		<li id="execution-DetailTab2" ><a href='javascript:'><spring:message code="clm.page.msg.manage.receivePlan" /></a></li>
	</c:if>
		<li id="execution-DetailTab3" ><a href='javascript:'><spring:message code="clm.page.msg.manage.etcPlan" /></a></li>
	</ul>
</div>
<!-- //key hidden Form -->
<!-- //지불계획 -->
<c:if test="${payment_gbn=='C02001' or payment_gbn=='C02002' }">
	<table cellspacing="0" cellpadding="0" border="1" class="list_basic" id="execution-mng-content_tb" >
							<colgroup>
				<!-- 	<col  width="5%"/> //-->
					<col  width="13%"/>
					<col  width="12%"/>
					<col  width="13%"/>
					<col  width="17%"/>
					<col  width="12%"/>
					<col  width="12%"/>
					<col  width="10%"/>
					<col  width="6%"/>
				</colgroup>
							<thead>
				<tr>
				<!-- 	<th>No</th> //-->
					<th><spring:message code="clm.page.msg.manage.exeItm" /></th>
					<th><spring:message code="clm.page.msg.manage.compResDate" /></th>
					<th><spring:message code="clm.page.msg.manage.amount" /></th>
					<th><spring:message code="clm.page.msg.manage.sendCond" /></th>
					<th><spring:message code="clm.page.msg.manage.compDate" /></th>
					<th><spring:message code="clm.page.msg.common.note" /></th>
					<th><spring:message code="clm.page.field.execution.exec_status" /></th>
					<th><spring:message code="clm.page.msg.common.select" /></th>
				</tr>
				</thead>
	</table>
	<div id="execution-mng-content" class="list_addtal">
		<table cellspacing="0" cellpadding="0" border="0" class="list_basic" style="border-top:0px;">
			<colgroup>
			<!-- 	<col  width="5%"/> //-->
				<col  width="13%"/>
				<col  width="12%"/>
				<col  width="13%"/>
				<col  width="17%"/>
				<col  width="12%"/>
				<col  width="12%"/>
				<col  width="10%"/>
				<col  width="6%"/>
			</colgroup>
			<tbody id="executionTbody">
	<c:choose>
	<c:when test="${pageUtil.totalRow > 0}">
		<c:forEach var="list" items="${executionLom}">
		<c:if test="${list.exec_gbn=='C05501' }">
			<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
			  <!--   <td id="exec_rn"><c:out value='${list.rno}'/></td> //-->
			    <td class="tL txtover"  title="<c:out value='${list.exec_itm}'/>"><nobr><c:out value='${list.exec_itm}'/></nobr></td>
			    <td><c:out value='${list.exec_plndday}'/></td>
			    <td class="tR"><fmt:formatNumber value='${list.exec_amt2}' pattern="#,#00.00#"/></td>
			    <td class="tL txtover" title="<c:out value='${list.exec_cont}'/>"><nobr><c:out value='${list.exec_cont}'/></nobr></td>
			    <td><c:out value='${list.exec_cmpltday}'/></td>
			    <td class="tL"><c:out value='${list.note}'/></td>
			    <td><c:out value='${list.exec_statusnm}'/></td>
			    <td></td>
			</tr>
		</c:if>	
		</c:forEach>
	</c:when>
	    <c:otherwise>
			<tr id="notFoundList" onMouseOut="this.className='';" onMouseOver="this.className='selected'">
				<td colspan="5" align="center" class="tC"><spring:message code="clm.msg.succ.noResult" /></td>
			</tr>
	    </c:otherwise>
	</c:choose>
			</tbody>
		</table>
	</div>
</c:if>

<!-- //수금계획 -->
<c:if test="${payment_gbn=='C02001' or payment_gbn=='C02003' }">
	<table cellspacing="0" cellpadding="0" border="0" class="list_basic" id="execution-mng2-content_tb">
			<colgroup>
				<!-- 	<col  width="5%"/> //-->
				<col  width="13%"/>
				<col  width="12%"/>
				<col  width="13%"/>
				<col  width="17%"/>
				<col  width="12%"/>
				<col  width="12%"/>
				<col  width="10%"/>
				<col  width="6%"/>
			</colgroup>
			<thead>
			<tr>
				<!-- 	<th>No</th> //-->
				<th><spring:message code="clm.page.msg.manage.exeItm" /></th>
				<th><spring:message code="clm.page.msg.manage.compResDate" /></th>
				<th><spring:message code="clm.page.msg.manage.amount" /></th>
				<th><spring:message code="clm.page.msg.manage.receiveCond" /></th>
				<th><spring:message code="clm.page.msg.manage.compDate" /></th>
				<th><spring:message code="clm.page.msg.common.note" /></th>
				<th><spring:message code="clm.page.field.execution.exec_status" /></th>
				<th><spring:message code="clm.page.msg.common.select" /></th>
			</tr>
			</thead>	
	</table>
	<div id="execution-mng2-content" class="list_addtal">
		<table cellspacing="0" cellpadding="0" border="0" class="list_basic" style="border-top:0px;">
			<colgroup>
				<!-- 	<col  width="5%"/> //-->
				<col  width="13%"/>
				<col  width="12%"/>
				<col  width="13%"/>
				<col  width="17%"/>
				<col  width="12%"/>
				<col  width="12%"/>
				<col  width="10%"/>
				<col  width="6%"/>
			</colgroup>
			<tbody id="executionTbody2">
	<c:choose>
	<c:when test="${pageUtil.totalRow > 0}">
		<c:forEach var="list" items="${executionLom}">
		<c:if test="${list.exec_gbn=='C05502' }">
			<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
			    <!--   <td id="exec_rn"><c:out value='${list.rno}'/></td> //-->
			    <td class="tL txtover"  title="<c:out value='${list.exec_itm}'/>"><nobr><c:out value='${list.exec_itm}'/></nobr></td>
			    <td><c:out value='${list.exec_plndday}'/></td>
			    <td class="tR"><fmt:formatNumber value='${list.exec_amt2}' pattern="#,#00.00#"/></td>
			    <td class="tL" title="<c:out value='${list.exec_cont}'/>"><nobr><c:out value='${list.exec_cont}'/></nobr></td>
			    <td><c:out value='${list.exec_cmpltday}'/></td>
			    <td class="tL"><c:out value='${list.note}'/></td>
			    <td><c:out value='${list.exec_statusnm}'/></td>
			    <td></td>
			</tr>
		</c:if>	
		</c:forEach>
	</c:when>
	    <c:otherwise>
			<tr id="notFoundList2" onMouseOut="this.className='';" onMouseOver="this.className='selected'">
				<td colspan="5" align="center" class="tC"><spring:message code="clm.msg.succ.noResult" /></td>
			</tr>
	    </c:otherwise>
	</c:choose>
			</tbody>
		</table>
	</div>
</c:if>
<!-- //기타 이행계획 -->
<table cellspacing="0" cellpadding="0" border="0" class="list_basic" id="execution-mng3-content_tb">
	<colgroup>
				<!-- 	<col  width="5%"/> //-->
				<col  width="13%"/>
				<col  width="12%"/>
				<col  width="30%"/>
				<col  width="12%"/>
				<col  width="12%"/>
				<col  width="10%"/>
				<col  width="6%"/>
			</colgroup>
			<thead>
			<tr>
			<!-- 	<th>No</th> //-->
				<th><spring:message code="clm.page.msg.manage.exeItm" /></th>
				<th><spring:message code="clm.page.msg.manage.compResDate" /></th>
				<th><spring:message code="clm.page.msg.manage.Description" /></th>
				<th><spring:message code="clm.page.msg.manage.compDate" /></th>
				<th><spring:message code="clm.page.msg.common.note" /></th>
				<th><spring:message code="clm.page.field.execution.exec_status" /></th>
				<th><spring:message code="clm.page.msg.common.select" /></th>
			</tr>
			</thead>
</table>
<div id="execution-mng3-content" class="list_addtal">
	<table cellspacing="0" cellpadding="0" border="0" class="list_basic" style="border-top:0px;">
		<colgroup>
			<!-- 	<col  width="5%"/> //-->
			<col  width="13%"/>
			<col  width="12%"/>
			<col  width="30%"/>
			<col  width="12%"/>
			<col  width="12%"/>
			<col  width="10%"/>
			<col  width="6%"/>
		</colgroup>
		<tbody id="executionTbody3">
<c:choose>
<c:when test="${pageUtil.totalRow > 0}">
	<c:forEach var="list" items="${executionLom}">
	<c:if test="${list.exec_gbn=='C05503' }">
		<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
		     <!--   <td id="exec_rn"><c:out value='${list.rno}'/></td> //-->
		    <td class="tL txtover"  title="<c:out value='${list.exec_itm}'/>"><nobr><c:out value='${list.exec_itm}'/></nobr></td>
		    <td><c:out value='${list.exec_plndday}'/></td>
		    <td class="tL" title="<c:out value='${list.exec_cont}'/>"><nobr><c:out value='${list.exec_cont}'/></nobr></td>
		    <td><c:out value='${list.exec_cmpltday}'/></td>
		    <td class="tL"><c:out value='${list.note}'/></td>
		    <td><c:out value='${list.exec_statusnm}'/></td>
		    <td></td>
		</tr>
	</c:if>
	</c:forEach>
</c:when>
    <c:otherwise>
		<tr id="notFoundList3" onMouseOut="this.className='';" onMouseOver="this.className='selected'">
			<td colspan="5" align="center" class="tC"><spring:message code="clm.msg.succ.noResult" /></td>
		</tr>
    </c:otherwise>
</c:choose>
		</tbody>
	</table>
</div>
</div>
<script language="javascript">
$(document).ready(function(){
	
	//주요이행사항-지불계획, 기타 이행계획 tab
	$('#executionDetail li').bind('click', function(){
		$('#executionDetail li').removeClass('on');
		$(this).addClass('on');
	});
	
	<c:if test="${payment_gbn!='C02004' }">
		$('#execution-mng3-content_tb').attr("style","display:none");
	</c:if>	
	
	<c:if test="${payment_gbn=='C02004' }">
		$('#execution-DetailTab3').addClass('on');
	</c:if>	
	
	//지불계획 display를 위한 처리...
	$('#execution-DetailTab').bind('click', function(){
		$('#execution-mng-content').attr("style","display:");
		$('#execution-mng-content_tb').attr("style","display:");
		$('#execution-mng2-content').attr("style","display:none");
		$('#execution-mng2-content_tb').attr("style","display:none");
		$('#execution-mng3-content').attr("style","display:none");
		$('#execution-mng3-content_tb').attr("style","display:none");
		$('#rowAddDel').attr("style","display:");
		$('#rowAddDel2').attr("style","display:none");	
		$('#rowAddDel3').attr("style","display:none");		
	});
	//수금계획 display를 위한 처리...
	$('#execution-DetailTab2').bind('click', function(){
		$('#execution-mng-content').attr("style","display:none");
		$('#execution-mng-content_tb').attr("style","display:none");
		$('#execution-mng2-content').attr("style","display:");
		$('#execution-mng2-content_tb').attr("style","display:");
		$('#execution-mng3-content').attr("style","display:none");
		$('#execution-mng3-content_tb').attr("style","display:none");
		$('#rowAddDel').attr("style","display:none");
		$('#rowAddDel2').attr("style","display:");
		$('#rowAddDel3').attr("style","display:none");
	});
	//기타 이행계획 display를 위한 처리...
	$('#execution-DetailTab3').bind('click', function(){
		$('#execution-mng-content').attr("style","display:none");
		$('#execution-mng-content_tb').attr("style","display:none");
		$('#execution-mng2-content').attr("style","display:none");
		$('#execution-mng2-content_tb').attr("style","display:none");
		$('#execution-mng3-content').attr("style","display:");
		$('#execution-mng3-content_tb').attr("style","display:");
		$('#rowAddDel').attr("style","display:none");
		$('#rowAddDel2').attr("style","display:none");
		$('#rowAddDel3').attr("style","display:");		
	});
});
</script>