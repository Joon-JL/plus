<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<%-- 
/**
 * 파  일  명 : Completion_exe_d_p.jsp
 * 프로그램명 : 연관계약 팝업에 사용
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2012.01
 */
--%>
<!-- key hidden Form -->
<input type="hidden" name="exec_seqno"	id="exec_seqno"    value="<c:out value='${executionCommand.exec_seqno}'/>" />
<input type="hidden" name="exec_status"	id="exec_status"    value="" />
<div class="title_basic">
	<h5 class="ntitle05"><spring:message code="clm.page.msg.manage.exeInf" /> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'mainExecutionDetail-Content');"/></h5>
</div>
<div id="mainExecutionDetail-Content">
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
	<table cellspacing="0" cellpadding="0" border="0" class="list_basic" id="execution-mng-content_tb">
	<colgroup>
				<col  width="13%"/>
				<col  width="12%"/>
				<col  width="13%"/>
				<col  width="17%"/>
				<col  width="12%"/>
				<col  width="12%"/>
			</colgroup>
			<thead>
			<tr>
				<th><spring:message code="clm.page.msg.manage.exeItm" /></th>
				<th><spring:message code="clm.page.msg.manage.compResDate" /></th>
				<th><spring:message code="clm.page.msg.manage.amount" /></th>
				<th>Description</th>
				<th><spring:message code="clm.page.msg.manage.compDate" /></th>
				<th><spring:message code="clm.page.msg.common.note" /></th>
			</tr>
			</thead> 
	</table>
	<div id="execution-mng-content" class="list_addtal">
		<table cellspacing="0" cellpadding="0" border="0" class="list_basic"  style="border-top:0px;">
			<colgroup>
				<col  width="13%"/>
				<col  width="12%"/>
				<col  width="13%"/>
				<col  width="17%"/>
				<col  width="12%"/>
				<col  width="12%"/>
			</colgroup>
			<tbody id="executionTbody">
	<c:choose>
	<c:when test="${pageUtil.totalRow > 0}">
		<c:forEach var="list" items="${executionLom}">
		<c:if test="${list.exec_gbn=='C05501' }">
			<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
			    <!--  <td id="exec_rn"><c:out value='${list.rno}'/></td> //-->
			    <td class="tL txtover"  title="<c:out value='${list.exec_itm}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_itm}'/></span></td>
			    <td><c:out value='${list.exec_plndday}'/></td>
			    <td><fmt:formatNumber value='${list.exec_amt2}' pattern="#,#00.00#"/></td>
			    <td class="txtover" title="<c:out value='${list.exec_cont}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_cont}'/></span></td>
			    <td><c:out value='${list.exec_cmpltday}'/></td>
			    <td><c:out value='${list.note}'/></td>
			</tr>
		</c:if>	
		</c:forEach>
	</c:when>
	    <c:otherwise>
			<tr id="notFoundList" onmouseout="this.className='';" onmouseover="this.className='selected'">
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
				<col  width="13%"/>
				<col  width="12%"/>
				<col  width="13%"/>
				<col  width="17%"/>
				<col  width="12%"/>
				<col  width="12%"/>
			</colgroup>
			<thead>
			<tr>
				<th><spring:message code="clm.page.msg.manage.exeItm" /></th>
				<th><spring:message code="clm.page.msg.manage.compResDate" /></th>
				<th><spring:message code="clm.page.msg.manage.amount" /></th>
				<th>Description</th>
				<th><spring:message code="clm.page.msg.manage.compDate" /></th>
				<th><spring:message code="clm.page.msg.common.note" /></th>
			</tr>
			</thead>
	</table>	
	<div id="execution-mng2-content" class="list_addtal">
		<table cellspacing="0" cellpadding="0" border="0" class="list_basic" style="border-top:0px;">
			<colgroup>
				<col  width="13%"/>
				<col  width="12%"/>
				<col  width="13%"/>
				<col  width="17%"/>
				<col  width="12%"/>
				<col  width="12%"/>
			</colgroup>
			<tbody id="executionTbody2">
	<c:choose>
	<c:when test="${pageUtil.totalRow > 0}">
		<c:forEach var="list" items="${executionLom}">
		<c:if test="${list.exec_gbn=='C05502' }">
			<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
			    <!-- 	 <td id="exec_rn"><c:out value='${list.rno}'/></td> //-->
			    <td class="tL txtover"  title="<c:out value='${list.exec_itm}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_itm}'/></span></td>
			    <td><c:out value='${list.exec_plndday}'/></td>
			    <td><fmt:formatNumber value='${list.exec_amt2}' pattern="#,#00.00#"/></td>
			    <td class="txtover" title="<c:out value='${list.exec_cont}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_cont}'/></span></td>
			    <td><c:out value='${list.exec_cmpltday}'/></td>
			    <td><c:out value='${list.note}'/></td>
			</tr>
		</c:if>	
		</c:forEach>
	</c:when>
	    <c:otherwise>
			<tr id="notFoundList2" onmouseout="this.className='';" onmouseover="this.className='selected'">
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
				<col  width="13%"/>
				<col  width="12%"/>
				<col  width="30%"/>
				<col  width="12%"/>
				<col  width="12%"/>
			</colgroup>
			<thead>
			<tr>
				<th><spring:message code="clm.page.msg.manage.exeItm" /></th>
				<th><spring:message code="clm.page.msg.manage.compResDate" /></th>
				<th>Description</th>
				<th><spring:message code="clm.page.msg.manage.compDate" /></th>
				<th><spring:message code="clm.page.msg.common.note" /></th>
			</tr>
			</thead>
</table>
<div id="execution-mng3-content" class="list_addtal">
	<table cellspacing="0" cellpadding="0" border="0" class="list_basic" style="border-top:0px;">
		<colgroup>
			<col  width="13%"/>
			<col  width="12%"/>
			<col  width="30%"/>
			<col  width="12%"/>
			<col  width="12%"/>
		</colgroup>
		<tbody id="executionTbody3">
<c:choose>
<c:when test="${pageUtil.totalRow > 0}">
	<c:forEach var="list" items="${executionLom}">
	<c:if test="${list.exec_gbn=='C05503' }">
		<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
		    <td class="tL txtover"  title="<c:out value='${list.exec_itm}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_itm}'/></span></td>
		    <td><c:out value='${list.exec_plndday}'/></td>
		    <td class="txtover" title="<c:out value='${list.exec_cont}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_cont}'/></span></td>
		    <td><c:out value='${list.exec_cmpltday}'/></td>
		    <td><c:out value='${list.note}'/></td>
		</tr>
	</c:if>
	</c:forEach>
</c:when>
    <c:otherwise>
		<tr id="notFoundList3" onmouseout="this.className='';" onmouseover="this.className='selected'">
			<td colspan="6" align="center" class="tC"><spring:message code="clm.msg.succ.noResult" /></td>
		</tr>
    </c:otherwise>
</c:choose>
		</tbody>
	</table>
</div>
</div>
<script language="javascript">
$(document).ready(function(){
	
	//기타 이행탭 헤더 감추기
	$('#execution-mng3-content_tb').attr("style","display:none");
	
	//주요이행사항-지불계획, 기타 이행계획 tab
	$('#executionDetail li').bind('click', function(){
		$('#executionDetail li').removeClass('on');
		$(this).addClass('on');
	});
	//지불계획 display를 위한 처리...
	$('#execution-DetailTab').bind('click', function(){
		$('#execution-mng-content').attr("style","display:");
		$('#execution-mng2-content').attr("style","display:none");
		$('#execution-mng3-content').attr("style","display:none");
		$('#execution-mng-content_tb').attr("style","display:");
		$('#execution-mng2-content_tb').attr("style","display:none");
		$('#execution-mng3-content_tb').attr("style","display:none");
		$('#rowAddDel').attr("style","display:");
		$('#rowAddDel2').attr("style","display:none");	
		$('#rowAddDel3').attr("style","display:none");		
	});
	//수금계획 display를 위한 처리...
	$('#execution-DetailTab2').bind('click', function(){
		$('#execution-mng-content').attr("style","display:none");
		$('#execution-mng2-content').attr("style","display:");
		$('#execution-mng3-content').attr("style","display:none");
		$('#execution-mng-content_tb').attr("style","display:none");
		$('#execution-mng2-content_tb').attr("style","display:");
		$('#execution-mng3-content_tb').attr("style","display:none");
		$('#rowAddDel').attr("style","display:none");
		$('#rowAddDel2').attr("style","display:");
		$('#rowAddDel3').attr("style","display:none");
	});
	//기타 이행계획 display를 위한 처리...
	$('#execution-DetailTab3').bind('click', function(){
		$('#execution-mng-content').attr("style","display:none");
		$('#execution-mng2-content').attr("style","display:none");
		$('#execution-mng3-content').attr("style","display:");
		$('#execution-mng-content_tb').attr("style","display:none");
		$('#execution-mng2-content_tb').attr("style","display:none");
		$('#execution-mng3-content_tb').attr("style","display:");
		$('#rowAddDel').attr("style","display:none");
		$('#rowAddDel2').attr("style","display:none");
		$('#rowAddDel3').attr("style","display:");		
	});
});
</script>