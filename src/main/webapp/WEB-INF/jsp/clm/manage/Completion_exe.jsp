<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
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
<input type="hidden" name="exec_up_no"	id="exec_up_no"    value="" />
<input type="hidden" name="exec_srt"	id="exec_srt"    value="" />
<input type="hidden" name="exec_status"	id="exec_status"    value="" />
<input type="hidden" id="exe_count" name="exe_count"/>
<input type="hidden" id="payment_gbn" name="payment_gbn" value="<c:out value='${payment_gbn}'/>"/>
<input type="hidden" name="max_exec_num"	id="max_exec_num"    value="" />
<input type="hidden" name="exec_mod_flag"	id="exec_mod_flag"    value="" />
<input type="hidden" name="note"	id="note"    value="" />
<input type="hidden" name="exec_cmpltday"	id="exec_cmpltday"    value="" />

<div class="title_basic">
	<h5 class="ntitle05"><spring:message code="clm.page.msg.manage.exeInf" /> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'mainExecutionDetail-Content');"/></h5>
</div>
<div id="mainExecutionDetail-Content">
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
<c:if test="${payment_gbn=='C02001' or payment_gbn=='C02002' }">
	<table cellspacing="0" cellpadding="0" border="0" class="list_basic" id="execution-mng-content_tb">
			<colgroup>
				<col  width="18%"/>
				<col  width="13%"/>
				<col  width="14%"/>
				<col  width="18%"/>
				<col  width="13%"/>
				<col  width="13%"/>
				<col  width="11%"/>
			</colgroup>
			<thead>
			<tr>
				<th><spring:message code="clm.page.msg.manage.exeItm" /></th>
				<th><spring:message code="clm.page.msg.manage.compResDate" /></th>
				<th><spring:message code="clm.page.msg.manage.amount" /></th>
				<th><spring:message code="clm.page.msg.manage.sendCond" /></th>
				<th><spring:message code="clm.page.msg.manage.compDate" /></th>
				<th><spring:message code="clm.page.msg.common.note" /></th>
				<th><spring:message code="clm.page.field.execution.exec_status" /></th>
			</tr>
			</thead>
		</table>
	<div id="execution-mng-content" class="list_addtal">
		<table cellspacing="0" cellpadding="0" border="0" class="list_basic" style="border-top:0px;">
			<colgroup>
				<col  width="18%"/>
				<col  width="13%"/>
				<col  width="14%"/>
				<col  width="18%"/>
				<col  width="13%"/>
				<col  width="13%"/>
				<col  width="11%"/>
			</colgroup>
			<tbody id="executionTbody">
	<c:choose>
	<c:when test="${pageUtil.totalRow > 0}">
		<c:forEach var="list" items="${executionLom}" varStatus="status">
		<c:if test="${list.exec_gbn=='C05501' }">
		<c:choose>
			<c:when test="${list.exec_status=='C03100'}">
			<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
			    <!-- <td id="exec_rn"><c:out value='${list.rno}'/></td> -->
			    <td class="tL txtover"  title="<c:out value='${list.exec_itm}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_itm}'/></span>
			    <input type="hidden" name="exec_itm_arr" id="exec_itm_arr" value="<c:out value='${list.exec_itm}'/>"/></td>
			    <td><c:out value='${list.exec_plndday}'/>
			    <input type="hidden" name="exec_plndday_arr" id="exec_plndday_arr<c:out value='${list.rno}'/>" class="input_calendar" readonly="readonly" value="<c:out value='${list.exec_plndday}'/>" size="10" alt="<spring:message code="clm.page.msg.manage.compResDate" htmlEscape="true" />"  /></td>
			    <td><input type="text" name="exec_amt_arr" id="exec_amt_arr" class="text_full" value="<c:out value='${list.exec_amt2}'/>" size="10" maxlength="15" alt="<spring:message code="clm.page.msg.manage.amount" htmlEscape="true" />"  /></td>
			    <td><input type="text" name="exec_cont_arr" id="exec_cont_arr" class="text_full" value="<c:out value='${list.exec_cont}'/>" size="20" maxlength="60" alt="<spring:message code="clm.page.msg.manage.sendCond" htmlEscape="true" />" /></td>
			    <td><input type="text" name="exec_cmpltday_arr" id="exec_cmpltday_arr1<c:out value='${list.exec_seqno}'/>" class="input_calendar" readonly="readonly" value="<c:out value='${list.exec_cmpltday}'/>" size="10" alt="<spring:message code="clm.page.msg.manage.compDate" htmlEscape="true" />" /></td>
			    <td><input type="text" name="note_arr" id="note_arr<c:out value='${list.rno}'/>" class="text_full" value="<c:out value='${list.note}'/>" size="20" maxlength="300" alt="<spring:message code="clm.page.msg.common.note" htmlEscape="true" />" /></td>
			    <td>			    
			    <span class="btn_option"><span class="ico_com"></span>
			    <a id="com_bt<c:out value='${list.exec_seqno}'/>" href='javascript:modifyExecution("<c:out value='${list.cntrt_id}'/>","<c:out value='${list.exec_seqno}'/>","<c:out value='${list.exec_status}'/>","exec_cmpltday_arr1<c:out value='${list.exec_seqno}'/>","note_arr<c:out value='${list.rno}'/>","<c:out value='${status.count}'/>");'>
			    <spring:message code="clm.page.msg.common.save" /></a></span>
			    <input type="hidden" name="exec_seqno_arr" id="exec_seqno_arr" value="<c:out value='${list.exec_seqno}'/>"/>
	            <input type="hidden" name="exec_status_arr" id="exec_status_arr" value="<c:out value='${list.exec_status}'/>"/>
	            <input type="hidden" id="exec_gbn_arr" name="exec_gbn_arr" value="<c:out value='${list.exec_gbn}'/>" />
			    </td>	           
	            <script language='javascript'>
					//initCal("exec_plndday_arr<c:out value='${list.rno}'/>");
					initCal("exec_cmpltday_arr1<c:out value='${list.exec_seqno}'/>");
	           	</script>
			</tr>
			</c:when>
			<c:when test="${list.exec_status=='C03102'}">
			<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
			    <!-- <td id="exec_rn"><c:out value='${list.rno}'/></td> -->
			    <td class="tL txtover"  title="<c:out value='${list.exec_itm}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_itm}'/></span>
			    <input type="hidden" name="exec_itm_arr" id="exec_itm_arr" value="<c:out value='${list.exec_itm}'/>"/></td>
			    <td><c:out value='${list.exec_plndday}'/>
			    <input type="hidden" name="exec_plndday_arr" id="exec_plndday_arr<c:out value='${list.rno}'/>" class="input_calendar" readonly="readonly" value="<c:out value='${list.exec_plndday}'/>" size="10" alt="<spring:message code="clm.page.msg.manage.compResDate" htmlEscape="true" />"  /></td>
			    <td><input type="text" name="exec_amt_arr" id="exec_amt_arr" class="text_full" value="<c:out value='${list.exec_amt2}'/>" size="10" maxlength="15" alt="<spring:message code="clm.page.msg.manage.amount" htmlEscape="true" />"  /></td>
			    <td><input type="text" name="exec_cont_arr" id="exec_cont_arr" class="text_full" value="<c:out value='${list.exec_cont}'/>" size="20" maxlength="60" alt="<spring:message code="clm.page.msg.manage.sendCond" htmlEscape="true" />" /></td>
			    <td><input type="text" name="exec_cmpltday_arr" id="exec_cmpltday_arr1<c:out value='${list.rno}'/>" class="input_calendar" readonly="readonly" value="<c:out value='${list.exec_cmpltday}'/>" size="10" alt="<spring:message code="clm.page.msg.manage.compDate" htmlEscape="true" />" /></td>
			    <td><input type="text" name="note_arr" id="note_arr<c:out value='${list.rno}'/>" class="text_full" value="<c:out value='${list.note}'/>" size="20" maxlength="300" alt="<spring:message code="clm.page.msg.common.note" htmlEscape="true" />" /></td>
			    <td>			    
			    <span class="btn_option"><span class="ico_com"></span><a id="com_bt<c:out value='${list.exec_seqno}'/>" href='javascript:modifyExecution("<c:out value='${list.cntrt_id}'/>","<c:out value='${list.exec_seqno}'/>","<c:out value='${list.exec_status}'/>","exec_cmpltday_arr1<c:out value='${list.rno}'/>","note_arr<c:out value='${list.rno}'/>","<c:out value='${status.count}'/>");'><spring:message code="clm.page.msg.manage.complete" /></a></span>
			    <span class="btn_option"><span class="ico_com"></span><a href='javascript:addExecution("<c:out value='${list.cntrt_id}'/>","<c:out value='${list.exec_seqno}'/>","<c:out value='${list.exec_status}'/>","exec_cmpltday_arr1<c:out value='${list.rno}'/>","note_arr<c:out value='${list.rno}'/>", "<c:out value='${status.count}'/>");'><spring:message code="clm.page.msg.manage.change" /></a></span>
			    <input type="hidden" name="exec_seqno_arr" id="exec_seqno_arr" value="<c:out value='${list.exec_seqno}'/>"/>
	            <input type="hidden" name="exec_status_arr" id="exec_status_arr" value="<c:out value='${list.exec_status}'/>"/>
	            <input type="hidden" id="exec_gbn_arr" name="exec_gbn_arr" value="<c:out value='${list.exec_gbn}'/>" />
			    </td>	            
	            <script language='javascript'>
					//initCal("exec_plndday_arr<c:out value='${list.rno}'/>");
					initCal("exec_cmpltday_arr1<c:out value='${list.rno}'/>");
	           	</script>
			</tr>
			</c:when>
			<c:otherwise>
			<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
			    <!-- <td id="exec_rn"><c:out value='${list.rno}'/></td> -->
			    <td class="tL txtover"  title="<c:out value='${list.exec_itm}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_itm}'/></span></td>
			    <td><c:out value='${list.exec_plndday}'/></td>
			    <td class="tR"><fmt:formatNumber value='${list.exec_amt2}' pattern="#,#00.00#"/></td>
			    <td class="tL" title="<c:out value='${list.exec_cont}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_cont}'/></span></td>
			    <td><c:out value='${list.exec_cmpltday}'/></td>
			    <td class="tL"><c:out value='${list.note}'/></td>
			    <td>
			    <c:choose>
				   	<c:when test="${list.exec_status=='C03101'}">
				   	    <spring:message code="clm.page.msg.manage.change" />
				   	</c:when>
				   	<c:when test="${list.exec_status=='C03104'}">
				   	    <spring:message code="clm.page.msg.manage.complete" />
				   	</c:when>
				</c:choose>
			    </td>
			</tr>
			</c:otherwise>
		</c:choose>
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
		<table cellspacing="0" cellpadding="0" border="0" class="list_basic" id="execution-mng2-content_tb" >
			<colgroup>
				<col  width="18%"/>
				<col  width="13%"/>
				<col  width="14%"/>
				<col  width="18%"/>
				<col  width="13%"/>
				<col  width="13%"/>
				<col  width="11%"/>
			</colgroup>
			<thead>
			<tr>
				<th><spring:message code="clm.page.msg.manage.exeItm" /></th>
				<th><spring:message code="clm.page.msg.manage.compResDate" /></th>
				<th><spring:message code="clm.page.msg.manage.amount" /></th>
				<th><spring:message code="clm.page.msg.manage.receiveCond" /></th>
				<th><spring:message code="clm.page.msg.manage.compDate" /></th>
				<th><spring:message code="clm.page.msg.common.note" /></th>
				<th><spring:message code="clm.page.field.execution.exec_status" /></th>
			</tr>
			</thead>
	</table>	
	<div id="execution-mng2-content" class="list_addtal">
		<table cellspacing="0" cellpadding="0" border="0" class="list_basic" style="border-top:0px;">
			<colgroup>
				<col  width="18%"/>
				<col  width="13%"/>
				<col  width="14%"/>
				<col  width="18%"/>
				<col  width="13%"/>
				<col  width="13%"/>
				<col  width="11%"/>
			</colgroup>
			<tbody id="executionTbody2">
	<c:choose>
	<c:when test="${pageUtil.totalRow > 0}">
		<c:forEach var="list" items="${executionLom}" varStatus="status">
		<c:if test="${list.exec_gbn=='C05502' }">
		<c:choose>
			<c:when test="${list.exec_status=='C03100'}">
			<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
			    <!-- <td id="exec_rn"><c:out value='${list.rno}'/></td> -->
			    <td class="tL txtover"  title="<c:out value='${list.exec_itm}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_itm}'/></span>
			    <input type="hidden" name="exec_itm_arr" id="exec_itm_arr" value="<c:out value='${list.exec_itm}'/>"/></td>
			    <td><c:out value='${list.exec_plndday}'/>
			    <input type="hidden" name="exec_plndday_arr" id="exec_plndday_arr<c:out value='${list.rno}'/>" class="input_calendar" readonly="readonly" value="<c:out value='${list.exec_plndday}'/>" size="10" alt="<spring:message code="clm.page.msg.manage.compResDate" htmlEscape="true" />"  /></td>
			    <td><input type="text" name="exec_amt_arr" id="exec_amt_arr" class="text_full" value="<c:out value='${list.exec_amt2}'/>" size="10" maxlength="15" alt="<spring:message code="clm.page.msg.manage.amount" htmlEscape="true" />"  /></td>
			    <td><input type="text" name="exec_cont_arr" id="exec_cont_arr" class="text_full" value="<c:out value='${list.exec_cont}'/>" size="20" maxlength="60" alt="<spring:message code="clm.page.msg.manage.receiveCond" htmlEscape="true" />" /></td>
			    <td><input type="text" name="exec_cmpltday_arr" id="exec_cmpltday_arr2<c:out value='${list.exec_seqno}'/>" class="input_calendar" readonly="readonly" value="<c:out value='${list.exec_cmpltday}'/>" size="10" alt="<spring:message code="clm.page.msg.manage.compDate" htmlEscape="true" />" /></td>
			    <td><input type="text" name="note_arr" id="note_arr<c:out value='${list.rno}'/>" class="text_full" value="<c:out value='${list.note}'/>" size="20" maxlength="300" alt="<spring:message code="clm.page.msg.common.note" htmlEscape="true" />" /></td>
			    <td>			    
			    <span class="btn_option"><span class="ico_com"></span>
			    <a id="com_bt<c:out value='${list.exec_seqno}'/>" href='javascript:modifyExecution("<c:out value='${list.cntrt_id}'/>","<c:out value='${list.exec_seqno}'/>","<c:out value='${list.exec_status}'/>","exec_cmpltday_arr2<c:out value='${list.exec_seqno}'/>","note_arr<c:out value='${list.rno}'/>","<c:out value='${status.count}'/>");'>
			    <spring:message code="clm.page.msg.common.save" /></a></span>
			    <input type="hidden" name="exec_seqno_arr" id="exec_seqno_arr" value="<c:out value='${list.exec_seqno}'/>"/>
	            <input type="hidden" name="exec_status_arr" id="exec_status_arr" value="<c:out value='${list.exec_status}'/>"/>
	            <input type="hidden" id="exec_gbn_arr" name="exec_gbn_arr" value="<c:out value='${list.exec_gbn}'/>" />
			    </td>	            
	            <script language='javascript'>
					//initCal("exec_plndday_arr<c:out value='${list.rno}'/>");
					initCal("exec_cmpltday_arr2<c:out value='${list.exec_seqno}'/>");
	           	</script>
			</tr>
			</c:when>
			
			<c:when test="${list.exec_status=='C03102'}">
			<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
			    <!-- <td id="exec_rn"><c:out value='${list.rno}'/></td> -->
			    <td class="tL txtover"  title="<c:out value='${list.exec_itm}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_itm}'/></span>
			    <input type="hidden" name="exec_itm_arr" id="exec_itm_arr" value="<c:out value='${list.exec_itm}'/>"/></td>
			    <td><c:out value='${list.exec_plndday}'/>
			    <input type="hidden" name="exec_plndday_arr" id="exec_plndday_arr2<c:out value='${list.rno}'/>" class="input_calendar" readonly="readonly" value="<c:out value='${list.exec_plndday}'/>" size="10" alt="<spring:message code="clm.page.msg.manage.compResDate" htmlEscape="true" />"  /></td>
			    <td><input type="text" name="exec_amt_arr" id="exec_amt_arr" class="text_full" value="<c:out value='${list.exec_amt2}'/>" size="10" maxlength="15" alt="<spring:message code="clm.page.msg.manage.amount" htmlEscape="true" />"  /></td>
			    <td><input type="text" name="exec_cont_arr" id="exec_cont_arr" class="text_full" value="<c:out value='${list.exec_cont}'/>" size="20" maxlength="60" alt="<spring:message code="clm.page.msg.manage.receiveCond" htmlEscape="true" />" /></td>
			    <td><input type="text" name="exec_cmpltday_arr" id="exec_cmpltday_arr2<c:out value='${list.rno}'/>" class="input_calendar" readonly="readonly" value="<c:out value='${list.exec_cmpltday}'/>" size="10" alt="<spring:message code="clm.page.msg.manage.compDate" htmlEscape="true" />" /></td>
			    <td><input type="text" name="note_arr" id="note_arr<c:out value='${list.rno}'/>" class="text_full" value="<c:out value='${list.note}'/>" size="20" maxlength="300" alt="<spring:message code="clm.page.msg.common.note" htmlEscape="true" />" /></td>
			    <td>			    
			    <span class="btn_option"><span class="ico_com"></span><a id="com_bt<c:out value='${list.exec_seqno}'/>" href='javascript:modifyExecution("<c:out value='${list.cntrt_id}'/>","<c:out value='${list.exec_seqno}'/>","<c:out value='${list.exec_status}'/>","exec_cmpltday_arr2<c:out value='${list.rno}'/>","note_arr<c:out value='${list.rno}'/>","<c:out value='${status.count}'/>");'><spring:message code="clm.page.msg.manage.complete" /></a></span>
			    <span class="btn_option"><span class="ico_com"></span><a href='javascript:addExecution("<c:out value='${list.cntrt_id}'/>","<c:out value='${list.exec_seqno}'/>","<c:out value='${list.exec_status}'/>","exec_cmpltday_arr2<c:out value='${list.rno}'/>","note_arr<c:out value='${list.rno}'/>", "<c:out value='${status.count}'/>");'><spring:message code="clm.page.msg.manage.change" /></a></span>
			    <input type="hidden" name="exec_seqno_arr" id="exec_seqno_arr" value="<c:out value='${list.exec_seqno}'/>"/>
	            <input type="hidden" name="exec_status_arr" id="exec_status_arr" value="<c:out value='${list.exec_status}'/>"/>
	            <input type="hidden" id="exec_gbn_arr" name="exec_gbn_arr" value="<c:out value='${list.exec_gbn}'/>" />
			    </td>	  
			     
	            <script language='javascript'>
					//initCal("exec_plndday_arr<c:out value='${list.rno}'/>");
					initCal("exec_cmpltday_arr2<c:out value='${list.rno}'/>");
	           	</script>
			</tr>
			</c:when>
			
			<c:otherwise>
			<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
			    <!-- <td id="exec_rn"><c:out value='${list.rno}'/></td> -->
			    <td class="tL txtover"  title="<c:out value='${list.exec_itm}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_itm}'/></span></td>
			    <td><c:out value='${list.exec_plndday}'/></td>
			    <td class="tR"><fmt:formatNumber value='${list.exec_amt2}' pattern="#,#00.00#"/></td>
			    <td class="tL" title="<c:out value='${list.exec_cont}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_cont}'/></span></td>
			    <td><c:out value='${list.exec_cmpltday}'/></td>
			    <td class="tL"><c:out value='${list.note}'/></td>
			    <!--  -<td><span class="btn_option"><span class="ico_com"></span><a href='javascript:modifyExecution("<c:out value='${list.cntrt_id}'/>","<c:out value='${list.exec_seqno}'/>","<c:out value='${list.exec_status}'/>");'><c:out value='${list.exec_statusnm}'/></a></span></td>-->
			    <td>			    
			    <c:choose>
				   	<c:when test="${list.exec_status=='C03101'}">
				   	    <spring:message code="clm.page.msg.manage.change" />
				   	</c:when>
				   	<c:when test="${list.exec_status=='C03104'}">
				   	    <spring:message code="clm.page.msg.manage.complete" />
				   	</c:when>
				</c:choose>
				</td>
			</tr>
			</c:otherwise>
		</c:choose>
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
<table cellspacing="0" cellpadding="0" border="0" class="list_basic" style="border-top:0px;" id="execution-mng3-content_tb">
		<colgroup>
			<col  width="18%"/>
			<col  width="13%"/>
			<col  width="14%"/>
			<col  width="18%"/>
			<col  width="13%"/>
			<col  width="13%"/>
		</colgroup>
		<thead>
		<tr>
			<!-- <th>No</th> -->
			<th><spring:message code="clm.page.msg.manage.exeItm" /></th>
			<th><spring:message code="clm.page.msg.manage.compResDate" /></th>
			<th>Description</th>
			<th><spring:message code="clm.page.msg.manage.compDate" /></th>
			<th><spring:message code="clm.page.msg.common.note" /></th>
			<th><spring:message code="clm.page.field.execution.exec_status" /></th>
		</tr>
		</thead>
	</table>
<div id="execution-mng3-content" class="list_addtal">
	<table cellspacing="0" cellpadding="0" border="0" class="list_basic" style="border-top:0px;">
		<colgroup>
			<col  width="18%"/>
			<col  width="13%"/>
			<col  width="14%"/>
			<col  width="18%"/>
			<col  width="13%"/>
			<col  width="13%"/>
		</colgroup>
		<tbody id="executionTbody3">
<c:choose>
<c:when test="${pageUtil.totalRow > 0}">
	<c:forEach var="list" items="${executionLom}" varStatus="status">
	<c:if test="${list.exec_gbn=='C05503' }">
	<c:choose>
		
			<c:when test="${list.exec_status=='C03100'}">
					<tr onmouseout="this.className='';" onmouseover="this.className='selected'" id="tr_<c:out value='${list.exec_seqno}'/>">
					 <!-- <td id='exec_rn'><c:out value='${list.rno}'/></td> //-->
					 <td><input type='text' name='exec_itm_arr' id='exec_itm_arr' class='text_full' value="<c:out value='${list.exec_itm}'/>" size='20' maxlength='30' alt="<spring:message code="clm.page.msg.manage.exeItm" htmlEscape="true" />" required readonly="readonly"/></td>
			         <td><input type='text' name='exec_plndday_arr' id="exec_plndday_arr<c:out value='${list.rno}'/>" class='input_calendar' readonly='readonly' value="<c:out value='${list.exec_plndday}'/>" size='10' alt="<spring:message code="clm.page.msg.manage.compResDate" htmlEscape="true" />" required /></td>
			         <td><input type='text' name='exec_cont_arr' id='exec_cont_arr' class='text_full' value="<c:out value='${list.exec_cont}'/>" size='20' maxlength='60' alt='Description' required /></td>
			         <td><input type='text' name='exec_cmpltday_arr' id="exec_cmpltday_arr3<c:out value='${list.exec_seqno}'/>" class="input_calendar<c:out value='${list.rno}'/>" readonly='readonly' value="<c:out value='${list.exec_cmpltday}'/>" size='10' alt="<spring:message code="clm.page.msg.manage.compDate" htmlEscape="true" />"  /></td>
			         <td><input type='text' name='note_arr' id='note_arr' class='text_full' value="<c:out value='${list.note}'/>" size='20' maxlength='300' alt="<spring:message code="clm.page.msg.common.note" htmlEscape="true" />"  /></td>
			         <td><span class="btn_option">			         
				         <span class="ico_com"></span><a href='javascript:modifyExecution("<c:out value='${list.cntrt_id}'/>"
				    	,"<c:out value='${list.exec_seqno}'/>"
				    	,"<c:out value='${list.exec_status}'/>"
				    	,"exec_cmpltday_arr3<c:out value='${list.exec_seqno}'/>"
					    ,"note_arr<c:out value='${list.exec_seqno}'/>"
				    	,"<c:out value='${status.count}'/>");'><spring:message code="clm.page.msg.common.save" /></a></span>		    	
			         	<input type="hidden" name="exec_seqno_arr" id="exec_seqno_arr" value="<c:out value='${list.exec_seqno}'/>"/>
				        <input type="hidden" name="exec_status_arr" id="exec_status_arr" value="<c:out value='${list.exec_status}'/>"/>
				        <input type="hidden" id="exec_gbn_arr" name="exec_gbn_arr" value="<c:out value='${list.exec_gbn}'/>" />
				        <input type="hidden" id="exec_amt_arr" name="exec_amt_arr" value="0" />
			         </td>
			            <script language='javascript'>
						initCal("exec_plndday_arr<c:out value='${list.rno}'/>");
						initCal("exec_cmpltday_arr3<c:out value='${list.exec_seqno}'/>");
			           	</script>
				     </tr>	
			</c:when>
		
<c:when test="${list.exec_status=='C03102'}">
			<tr onmouseout="this.className='';" onmouseover="this.className='selected'" id="tr_<c:out value='${list.exec_seqno}'/>">
			    <!-- <td id='exec_rn'><c:out value='${list.rno}'/></td> //-->
			    <td class="tL txtover"  title="<c:out value='${list.exec_itm}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_itm}'/></span>
			    <input type="hidden" name="exec_itm_arr" id="exec_itm_arr" value="<c:out value='${list.exec_itm}'/>"/></td>
			    <td><c:out value='${list.exec_plndday}'/>
			    <input type="hidden" name="exec_plndday_arr" id="exec_plndday_arr<c:out value='${list.rno}'/>" class="input_calendar" readonly="readonly" value="<c:out value='${list.exec_plndday}'/>" size="10" alt="<spring:message code="clm.page.msg.manage.compResDate" htmlEscape="true" />" required /></td>
			    <td class="tL"><c:out value='${list.exec_cont}'/>
			    <input type="hidden" name="exec_cont_arr" id="exec_cont_arr"<c:out value='${list.rno}'/>" class="text_full" value="<c:out value='${list.exec_cont}'/>" size="20" maxlength="60" alt="Description" /></td>
			    <td><input type="text" name="exec_cmpltday_arr" id="exec_cmpltday_arr3<c:out value='${list.rno}'/>" class="input_calendar" readonly="readonly" value="<c:out value='${list.exec_cmpltday}'/>" size="10" alt="<spring:message code="clm.page.msg.manage.compDate" htmlEscape="true" />" /></td>
			    <td><input type="text" name="note_arr" id="note_arr<c:out value='${list.exec_seqno}'/>" class="text_full" value="<c:out value='${list.note}'/>" size="20" maxlength="300" alt="<spring:message code="clm.page.msg.common.note" htmlEscape="true" />" /></td>
			    <td>
			    <span class="btn_option" id="btn_com"><span class="ico_com"></span>
			    	
			    	<a href='javascript:modifyExecution(
			    	"<c:out value='${list.cntrt_id}'/>"
			    	,"<c:out value='${list.exec_seqno}'/>"
			    	,"<c:out value='${list.exec_status}'/>"
			    	,"exec_cmpltday_arr3<c:out value='${list.rno}'/>"
				    ,"note_arr<c:out value='${list.exec_seqno}'/>"
			    	,"<c:out value='${status.count}'/>"
			    	);'><spring:message code="clm.page.msg.manage.complete" /></a></span>
			    
			    <span class="btn_option" id="btn_rev"><span class="ico_com"></span>
			    	
			    	<a href='javascript:addExecution(
			    	"<c:out value='${list.cntrt_id}'/>"
				    ,"<c:out value='${list.exec_seqno}'/>"
				    ,"<c:out value='${list.exec_status}'/>"
				    ,"exec_cmpltday_arr3<c:out value='${list.rno}'/>"
				    ,"note_arr<c:out value='${list.exec_seqno}'/>"
				    ,"<c:out value='${status.count}'/>"
				    );'><spring:message code="clm.page.msg.manage.change" /></a></span>
			    <!-- <input type="checkbox" name="exec_chk_arr" id="exec_chk_arr" value="<c:out value='${list.exec_seqno}'/>"/> -->
	            <input type="hidden" name="exec_seqno_arr" id="exec_seqno_arr" value="<c:out value='${list.exec_seqno}'/>"/>
	            <input type="hidden" name="exec_status_arr" id="exec_status_arr" value="<c:out value='${list.exec_status}'/>"/>
	            <input type="hidden" id="exec_gbn_arr" name="exec_gbn_arr" value="<c:out value='${list.exec_gbn}'/>" />
	            <input type="hidden" id="exec_amt_arr" name="exec_amt_arr" value="0" />
	            </td>
	            <script language='javascript'>
					//initCal("exec_plndday_arr<c:out value='${list.rno}'/>");
					initCal("exec_cmpltday_arr3<c:out value='${list.rno}'/>");
	           	</script>
			</tr>
			</c:when>
		
		<c:otherwise>
		<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
		   <!-- <td id='exec_rn'><c:out value='${list.rno}'/></td> //-->
		    <td class="tL txtover"  title="<c:out value='${list.exec_itm}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_itm}'/></span></td>
		    <td><c:out value='${list.exec_plndday}'/></td>
		    <td class="tL" title="<c:out value='${list.exec_cont}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_cont}'/></span></td>
		    <td><c:out value='${list.exec_cmpltday}'/></td>
		    <td class="tL"><c:out value='${list.note}'/></td>
		    <td>
		       <c:choose>
				    	<c:when test="${list.exec_status=='C03101'}">
				    	    <spring:message code="clm.page.msg.manage.change" />
				    	</c:when>
				    	<c:when test="${list.exec_status=='C03104'}">
				    	    <spring:message code="clm.page.msg.manage.complete" />
				    	</c:when>
				    	<c:otherwise>
				      <span class="btn_option"><span class="ico_com"></span><a href='javascript:modifyExecution("<c:out value='${list.cntrt_id}'/>","<c:out value='${list.exec_seqno}'/>","<c:out value='${list.exec_status}'/>");'><c:out value='${list.exec_statusnm}'/></a></span>
				    	</c:otherwise>
				    </c:choose> 
	
		    <input type="hidden" id="exec_amt_arr" name="exec_amt_arr" value="0" />
		    </td>
		</tr>
		</c:otherwise>
	</c:choose>
	</c:if>
	</c:forEach>
</c:when>
    <c:otherwise>
		<tr id="notFoundList3" onmouseout="this.className='';" onmouseover="this.className='selected'">
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
	
	$('#execution-mng3-content_tb').attr("style","display:none");
	$('#max_exec_num').val("<c:out value='${max_exec_num}'/>");
	
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
		$('#selected_paygbn').val("tab1");				
		
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
		$('#selected_paygbn').val("tab2");				
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
		$('#selected_paygbn').val("tab3");				
	});
});

/**
* 이행 확인여부 기능
*/
function modifyExecution(cntrt_id,exec_seqno,exec_status,exec_cmpltday,exec_note,count) {
	var frm = document.frm;
	var msg = "";
	
	$('input[name=exec_seqno_arr]').each(function(index) {
		if($(this).val() == exec_seqno){
			frm.exe_count.value = index;
		}	
    });

	if(exec_status=="C03104"){
		//처리 완료
		return;
	} else if(exec_status=="C03102"){
		msg = "<spring:message code='secfw.msg.ask.complete'/>";
		
		if($("#"+ exec_cmpltday + "").val() == ''){
			alert("<spring:message code="clm.page.msg.manage.announce121" />");
			return;
		}
		
		$('input[name=note]').val("");
	    $('#note').val($('#' + exec_note + '').val());
		$('input[name=exec_cmpltday]').val("");
	    $('#exec_cmpltday').val($('#' + exec_cmpltday + '').val());
		
	} else if(exec_status=="C03101"){
		msg = "C03101";
	} else if(exec_status=="C03103"){
		msg = "C03103";
	} else if(exec_status=="C03100"){
		msg = "<spring:message code="clm.page.msg.manage.announce147" />";
	}
	
	if(confirm(msg)){
	    //초기화 : 연계 시 이 부분을 주석처리...
		$('input[name=exec_seqno]').val("");
		$('input[name=exec_status]').val("");
		$('input[name=cntrt_id]').val("");
	    //설정
	    $('#cntrt_id').val(cntrt_id);
	    $('#exec_seqno').val(exec_seqno);
	    $('#exec_status').val(exec_status);
	
		var options = {
				url: "<c:url value='/clm/manage/completion.do?method=modifyExecution' />",
				type: "post",
				dataType: "html",
				beforeSubmit: function(formData, form){
					viewHiddenProgress(true);
				},	
				success: function(data) {
					viewHiddenProgress(false);
					alert('<spring:message code="secfw.msg.succ.modify"/>');
					$("#contractExeCmpltHis-list").html("");
					$("#contractExeCmpltHis-list").html(data);
					
					$('#executionDetail').removeClass('on');
					$('#execution-DetailTab').addClass("on");
										
					$('#execution-mng-content').attr("style","display:");
					$('#execution-mng2-content').attr("style","display:none");
					$('#execution-mng3-content').attr("style","display:none");
					$('#execution-mng-content_tb').attr("style","display:");
					$('#execution-mng2-content_tb').attr("style","display:none");
					$('#execution-mng3-content_tb').attr("style","display:none");
					
					$('#rowAddDel').attr("style","display:");
					$('#rowAddDel2').attr("style","display:none");
					$('#rowAddDel3').attr("style","display:none");
					
					// 기타이행탭 활성화
					if($('#payment_gbn').val() == 'C02004'){
						$('#execution-DetailTab3').click();
						$('#execution-mng3-content_tb').attr("style","display:");
						$('#execution-DetailTab3').addClass('on');
					}
					
					if($('#selected_paygbn').val() == 'tab1'){
						$('#execution-DetailTab').click();
					} else if($('#selected_paygbn').val() == 'tab2'){
						$('#execution-DetailTab2').click();
					} else if($('#selected_paygbn').val() == 'tab3'){
						$('#execution-DetailTab3').click();
					}
					
					listContractRole();
				}
			};
		$("#frm").ajaxSubmit(options);
	}	
}

/**
* 이행변경
*/
function addExecution(cntrt_id,exec_seqno,exec_status,exec_cmpltday,exec_note,count){

	var frm = document.frm;
	
	$('input[name=exec_seqno_arr]').each(function(index) {
		if($(this).val() == exec_seqno){
			frm.exe_count.value = index;
		}	
    });
	
	if($("#"+ exec_note + "").val().replace(' ','') == ''){
		alert("<spring:message code="clm.page.msg.manage.announce094" />");
		return;
	}

	if(exec_status=="C03101"){
		//처리 완료
		return;
	}
	
	if(confirm("<spring:message code="clm.page.msg.manage.announce090" />")){

	    $('input[name=exec_seqno]').val("");
		$('input[name=exec_status]').val("");
		$('input[name=cntrt_id]').val("");
		$('input[name=note]').val("");
		
	    //설정
	    $('#cntrt_id').val(cntrt_id);
	    $('#exec_seqno').val(exec_seqno);
	    $('#note').val($('#' + exec_note + '').val());
	    $('#exec_status').val(exec_status);
	    $('#exec_mod_flag').val("true");
	    	
		var options = {
				url: "<c:url value='/clm/manage/completion.do?method=modifyExecution' />",
				type: "post",
				dataType: "html",
				beforeSubmit: function(formData, form){
					viewHiddenProgress(true);
				},		
				success: function(data) {
					
					viewHiddenProgress(false);
					$("#contractExeCmpltHis-list").html("");
					$("#contractExeCmpltHis-list").html(data);
					//alert('<spring:message code="secfw.msg.succ.modify"/>');
					//$("#execution-mng").html("");
					//$("#execution-mng").html(data);

					//$('#executionTab li').removeClass('on');
					//$('#contract-executionDetailTab').addClass("on");;
					//$('#contract-execMngTab').addClass("on");
					$('#executionDetail').removeClass('on');
					$('#execution-DetailTab').addClass("on");
					
					$('#execution-mng-content').attr("style","display:");
					$('#execution-mng2-content').attr("style","display:none");
					$('#execution-mng3-content').attr("style","display:none");
					
					$('#execution-mng-content_tb').attr("style","display:");
					$('#execution-mng2-content_tb').attr("style","display:none");
					$('#execution-mng3-content_tb').attr("style","display:none");
					
					$('#rowAddDel').attr("style","display:");
					$('#rowAddDel2').attr("style","display:none");
					$('#rowAddDel3').attr("style","display:none");
					
					// 기타이행탭 활성화
					if($('#payment_gbn').val() == 'C02004'){
						$('#execution-DetailTab3').click();
						$('#execution-mng3-content_tb').attr("style","display:");
						$('#execution-DetailTab3').addClass('on');
					}
					
					if($('#selected_paygbn').val() == 'tab1'){
						$('#execution-DetailTab').click();
					} else if($('#selected_paygbn').val() == 'tab2'){
						$('#execution-DetailTab2').click();
					} else if($('#selected_paygbn').val() == 'tab3'){
						$('#execution-DetailTab3').click();
					}

					var focus_num = $('#max_exec_num').val();
					alert("<spring:message code="clm.page.msg.manage.announce166" />");
					//alert("focus_num:" + focus_num);
					$('#exec_cmpltday_arr' + focus_num).focus();
				}
			};
		$("#frm").ajaxSubmit(options);
	}		
}
</script>