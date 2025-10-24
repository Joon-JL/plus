<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%-- 
/**
 * 파  일  명 : Execution_exe_d.jsp
 * 프로그램명 : 이행정보 조회 목록
 * 설      명 : 
 * 작  성  자 : 박정훈
 * 작  성  일 : 2013.05
 */
--%>
<!-- key hidden Form -->
<head>
<script language="javascript">
$(document).ready(function(){
	$('#execution-mng-content_tb').hide();
	$('#execution-mng2-content_tb').hide();
	$('#execution-mng3-content_tb').hide();
	
	if("<c:out value='${payment_gbn}'/>" == "C02001" || "<c:out value='${payment_gbn}'/>" == "C02002"){
		$('#executionDetail li').removeClass('on');
		$("#execution-DetailTab").addClass('on');
		$('#execution-mng-content_tb').show();

	}else if("<c:out value='${payment_gbn}'/>" == "C02003"){
		$('#executionDetail li').removeClass('on');
		$("#execution-DetailTab2").addClass('on');
		$('#execution-mng2-content_tb').show();
		
	}else if("<c:out value='${payment_gbn}'/>" == "C02004"){
		$('#executionDetail li').removeClass('on');
		$("#execution-DetailTab3").addClass('on');
		$('#execution-mng3-content_tb').show();
	}else{
		$('#executionDetail li').removeClass('on');
	}
	
	
	if($('#executionTbody tr').length == '0' && $('#executionTbody2 tr').length == '0'){
        $('#execution-mng-content_tb').hide(); //지불계획
        $('#execution-mng2-content_tb').hide(); //수금계획
        $('#execution-mng3-content_tb').show(); //기타이행계획
        $('#execution-DetailTab3').addClass('on');
        $('#execution-DetailTab').removeClass('on');
        $('#execution-DetailTab2').removeClass('on');
    }else{
        if($('#executionTbody tr').length != '0'){
            $('#execution-mng-content_tb').show(); //지불계획
            $('#execution-mng2-content_tb').hide(); //수금계획
            $('#execution-mng3-content_tb').hide(); //기타이행계획
        }else if($('#executionTbody tr').length == '0' && $('#executionTbody2 tr').length != '0'){
            $('#execution-mng-content_tb').hide(); //지불계획
            $('#execution-mng2-content_tb').show(); //수금계획
            $('#execution-mng3-content_tb').hide(); //기타이행계획
        }else{
            $('#execution-mng-content_tb').hide(); //지불계획
            $('#execution-mng2-content_tb').hide(); //수금계획
            $('#execution-mng3-content_tb').show(); //기타이행계획
        }
    }
	
	//주요이행사항-지불계획, 기타 이행계획 tab
	$('#executionDetail li').bind('click', function(){
		$('#executionDetail li').removeClass('on');
		$(this).addClass('on');
	});
	
	<c:if test="${payment_gbn!='C02004' }">
		$('#execution-mng3-content_tb').hide();
	</c:if>	
	
	<c:if test="${payment_gbn=='C02004' }">
		$('#execution-DetailTab3').addClass('on');
	</c:if>	
	

	
	
	//지불계획 display를 위한 처리...
	$('#execution-DetailTab').bind('click', function(){
		$('#execution-mng-content').show(); //
		$('#execution-mng-content_tb').show();
		$('#execution-mng2-content').hide();  // .attr("style","display:none");
		$('#execution-mng2-content_tb').hide();
		$('#execution-mng3-content').hide();
		$('#execution-mng3-content_tb').hide();
		$('#rowAddDel').show();
		$('#rowAddDel2').hide();	
		$('#rowAddDel3').hide();		
	});
	//수금계획 display를 위한 처리...
	$('#execution-DetailTab2').bind('click', function(){
		$('#execution-mng-content').hide();
		$('#execution-mng-content_tb').hide();
		$('#execution-mng2-content').show();
		$('#execution-mng2-content_tb').show();
		$('#execution-mng3-content').hide();
		$('#execution-mng3-content_tb').hide();
		$('#rowAddDel').hide();
		$('#rowAddDel2').show();
		$('#rowAddDel3').hide();
	});
	//기타 이행계획 display를 위한 처리...
	$('#execution-DetailTab3').bind('click', function(){
		$('#execution-mng-content').hide();
		$('#execution-mng-content_tb').hide();
		$('#execution-mng2-content').hide();
		$('#execution-mng2-content_tb').hide();
		$('#execution-mng3-content').show();
		$('#execution-mng3-content_tb').show();
		$('#rowAddDel').hide();
		$('#rowAddDel2').hide();
		$('#rowAddDel3').show();		
	});	
	
});
</script>
</head>
<input type="hidden" name="exec_seqno"	id="exec_seqno"    value="<c:out value='${executionCommand.exec_seqno}'/>" />
<input type="hidden" name="exec_status"	id="exec_status"    value="" />
<input type="hidden" name="checkYN"	id="checkYN"    value="${checkYN }" />
<input type="hidden" id="payment_gbn" name="payment_gbn" value="<c:out value='${payment_gbn}'/>"/>

<div class="title_basic">
	<h4><spring:message code="clm.page.tab.title.contractexec"/> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'consultation-execinfo-list');" style="cursor:pointer"/></h4>
</div>		
<div id="consultation-execinfo-list" style="display:;">
<div class="tab_box">
	<ul id="executionDetail" class="tab_basic">
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

	<table cellspacing="0" cellpadding="0" border="0" class="table-style01" id="execution-mng-content_tb" >
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
				<tbody id="executionTbody">
	<c:choose>
	<c:when test="${pageUtil.totalRow > 0}">
		<c:forEach var="list" items="${executionLom}">
		<c:if test="${list.exec_gbn=='C05501' }">
			<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
			  <!--   <td id="exec_rn"><c:out value='${list.rno}'/></td> //-->
			    <td class="tL txtover"  title="<c:out value='${list.exec_itm}'/>"><nobr><c:out value='${list.exec_itm}' escapeXml="false"/></nobr></td>
			    <td class="tC"><c:out value='${list.exec_plndday}'/></td>
			    <td class="tR"><c:out value='${list.exec_amt}'/></td>
			    <td class="tL txtover" title="<c:out value='${list.exec_cont}'/>"><nobr><c:out value='${list.exec_cont}' escapeXml="false"/></nobr></td>
			    <td class="tC"><c:out value='${list.exec_cmpltday}'/></td>
			    <td class="tL"><c:out value='${list.note}' escapeXml="false"/></td>
			    <td class="tC"><c:out value='${list.exec_statusnm}'/></td>
			    <td class="tC"></td>
			</tr>
		</c:if>	
		</c:forEach>
	</c:when>
	    <c:otherwise>
			<tr id="notFoundList" onmouseout="this.className='';" onmouseover="this.className='selected'">
				<td colspan="7" align="center" class="tC"><spring:message code="clm.msg.succ.noResult" /></td>
			</tr>
	    </c:otherwise>
	</c:choose>
			</tbody>
	</table>



<!-- //수금계획 -->

	<table cellspacing="0" cellpadding="0" border="0" class="table-style01" id="execution-mng2-content_tb">
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
			<tbody id="executionTbody2">
	<c:choose>
	<c:when test="${pageUtil.totalRow > 0}">
		<c:forEach var="list" items="${executionLom}">
		<c:if test="${list.exec_gbn=='C05502' }">
			<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
			    <!--   <td id="exec_rn"><c:out value='${list.rno}'/></td> //-->
			    <td class="tL txtover"  title="<c:out value='${list.exec_itm}'/>"><nobr><c:out value='${list.exec_itm}' escapeXml="false"/></nobr></td>
			    <td class="tC"><c:out value='${list.exec_plndday}'/></td>
			    <td class="tR"><c:out value='${list.exec_amt}'/></td>
			    <td class="tL" title="<c:out value='${list.exec_cont}'/>"><nobr><c:out value='${list.exec_cont}' escapeXml="false"/></nobr></td>
			    <td class="tC"><c:out value='${list.exec_cmpltday}'/></td>
			    <td class="tL"><c:out value='${list.note}' escapeXml="false"/></td>
			    <td class="tC"><c:out value='${list.exec_statusnm}'/></td>
			    <td class="tC"></td>
			</tr>
		</c:if>	
		</c:forEach>
	</c:when>
	    <c:otherwise>
			<tr id="notFoundList2" onmouseout="this.className='';" onmouseover="this.className='selected'">
				<td colspan="7" align="center" class="tC"><spring:message code="clm.msg.succ.noResult" /></td>
			</tr>
	    </c:otherwise>
	</c:choose>
			</tbody>	
	</table>


<!-- //기타 이행계획 -->
<table cellspacing="0" cellpadding="0" border="0" class="table-style01" id="execution-mng3-content_tb">
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
		</colgroup>
		<tbody id="executionTbody3">
<c:choose>
<c:when test="${pageUtil.totalRow > 0}">
	<c:forEach var="list" items="${executionLom}">
	<c:if test="${list.exec_gbn=='C05503' }">
		<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
		     <!--   <td id="exec_rn"><c:out value='${list.rno}'/></td> //-->
		    <td class="tL txtover"  title="<c:out value='${list.exec_itm}'/>"><nobr><c:out value='${list.exec_itm}' escapeXml="false"/></nobr></td>
		    <td class="tC"><c:out value='${list.exec_plndday}'/></td>
		    <td class="tL" title="<c:out value='${list.exec_cont}'/>"><nobr><c:out value='${list.exec_cont}' escapeXml="false"/></nobr></td>
		    <td class="tC"><c:out value='${list.exec_cmpltday}'/></td>
		    <td class="tL"><c:out value='${list.note}' escapeXml="false"/></td>
		    <td class="tC"><c:out value='${list.exec_statusnm}'/></td>
		    <td></td>
		</tr>
	</c:if>
	</c:forEach>
</c:when>
    <c:otherwise>
		<tr id="notFoundList3" onmouseout="this.className='';" onmouseover="this.className='selected'">
			<td colspan="7" align="center" class="tC"><spring:message code="clm.msg.succ.noResult" /></td>
		</tr>
    </c:otherwise>
</c:choose>
		</tbody>			
</table>

</div>
