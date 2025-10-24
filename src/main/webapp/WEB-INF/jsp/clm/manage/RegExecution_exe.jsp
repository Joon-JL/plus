<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%-- 
/**
 * 파  일  명 : RegExecution_exe.jsp
 * 프로그램명 : 이행정보 등록
 * 설      명 : 
 * 작  성  자 : 김재원
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

<div id="mainExecutionDetail-Content" style="width:100%; clear:both; border-top:2px solid #3E74BE; _border-left:1px solid #fff; margin:0; table-layout:fixed;">

<div class="tab_box">
	<ul id="executionDetail" class="tab_basic05">
		<li id="execution-DetailTab" class="on"><a href='javascript:'><spring:message code="clm.page.msg.manage.sendPlan" /></a></li>
		<li id="execution-DetailTab2" ><a href='javascript:'><spring:message code="clm.page.msg.manage.receivePlan" /></a></li>
		<li id="execution-DetailTab3" ><a href='javascript:'><spring:message code="clm.page.msg.manage.etcPlan" /></a></li>
	</ul>
	<!-- button -->
		
		<!--// 지불계획 -->
		<div id="rowAddDel" class="btn_wrap_t01">
			<!-- <span class="btn"><span class="add"></span><a href="javascript:insertExecution();"> 전체저장</a></span> -->
			<span class="btn"><span class="add"></span><a href="javascript:newExecution();"> <spring:message code="clm.page.msg.manage.addRow" /></a></span>
			<span class="btn"><span class="delete"></span><a href="javascript:deleteExecution();"> <spring:message code="clm.page.msg.manage.delRow" /></a></span>
		</div>
		<!--// 수금계획 -->
		<div id="rowAddDel2" class="btn_wrap_t01">
			<!-- <span class="btn"><span class="add"></span><a href="javascript:insertExecution();"> 전체저장</a></span> -->
			<span class="btn"><span class="add"></span><a href="javascript:newExecution2();"> <spring:message code="clm.page.msg.manage.addRow" /></a></span>
			<span class="btn"><span class="delete"></span><a href="javascript:deleteExecution();"> <spring:message code="clm.page.msg.manage.delRow" /></a></span>
		</div>
		<!--// 기타 이행계획 -->
		<div id="rowAddDel3" class="btn_wrap_t01">
			<!-- <span class="btn"><span class="add"></span><a href="javascript:insertExecution();"> 전체저장</a></span> -->
			<span class="btn"><span class="add"></span><a href="javascript:newExecution3();"> <spring:message code="clm.page.msg.manage.addRow" /></a></span>
			<span class="btn"><span class="delete"></span><a href="javascript:deleteExecution();"> <spring:message code="clm.page.msg.manage.delRow" /></a></span>
		</div>
	<!-- //button -->
</div>
<!-- //key hidden Form -->
<!-- //지불계획 -->
	<table cellspacing="0" cellpadding="0" border="0" class="list_basic" id="execution-mng-content_tb">
			<colgroup>
				<!-- <col  width="5%"/> //-->
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
				<!--  <th>No</th> //-->
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
				<!-- <col  width="5%"/> //-->
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
		<c:forEach var="list" items="${executionLom}" varStatus="status">
		<c:if test="${list.exec_gbn=='C05501' }">
		<c:choose>
			<c:when test="${list.exec_status=='C03100'}">

					<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'" id="tr_<c:out value='${list.exec_seqno}'/>">
					 <!-- <td id='exec_rn'><c:out value='${list.rno}'/></td> //-->
					 <td class="tL"><c:out value='${list.exec_itm}'/>
					 <input type='hidden' name='exec_itm_arr' id='exec_itm_arr' class='text_full' value="<c:out value='${list.exec_itm}'/>" size='20' maxLength='30' alt="<spring:message code="clm.page.msg.manage.exeItm" htmlEscape="true" />" required readonly/></td>
			         <td><input type='text' name='exec_plndday_arr' id="exec_plndday_arr1<c:out value='${list.exec_seqno}'/>" class='input_calendar' readonly='readonly' value="<c:out value='${list.exec_plndday}'/>" size='10' alt="<spring:message code="clm.page.msg.manage.compResDate" htmlEscape="true" />" required /></td>
			         <td class="tR"><input type='text' name='exec_amt_arr' id='exec_amt_arr' class='text_full' value="<c:out value='${list.exec_amt2}'/>" size='10' maxLength='15' alt="<spring:message code="clm.page.msg.manage.amount" htmlEscape="true" />" num required /></td>
			         <td class="tL"><input type='text' name='exec_cont_arr' id='exec_cont_arr' class='text_full' value="<c:out value='${list.exec_cont}'/>" size='20' maxLength='60' alt="<spring:message code="clm.page.msg.manage.sendCond" htmlEscape="true" />" required /></td>
			         <td><input type='text' name='exec_cmpltday_arr' id="exec_cmpltday_arr1<c:out value='${list.exec_seqno}'/>" class="input_calendar<c:out value='${list.rno}'/>" readonly='readonly' value="<c:out value='${list.exec_cmpltday}'/>" size='10' alt="<spring:message code="clm.page.msg.manage.compDate" htmlEscape="true" />" /></td>
			         <td class="tL"><input type='text' name='note_arr' id='note_arr<c:out value='${list.exec_seqno}'/>' class='text_full' value="<c:out value='${list.note}'/>" size='20' maxLength='300' alt="<spring:message code="clm.page.msg.common.note" htmlEscape="true" />" /></td>
			         <td><span class="btn_option"><span class="ico_com"></span>
			         <a href='javascript:modifyExecution(
			         "<c:out value='${list.cntrt_id}'/>"
			    	,"<c:out value='${list.exec_seqno}'/>"
			    	,"<c:out value='${list.exec_status}'/>"
			    	,"exec_cmpltday_arr<c:out value='${list.rno}'/>"
				    ,"note_arr<c:out value='${list.rno}'/>"
			    	,"<c:out value='${status.count}'/>");'><spring:message code="clm.page.msg.common.save" /></a></span></td>
			         <td>
					    <input type="hidden" name="exec_seqno_arr" id="exec_seqno_arr" value="<c:out value='${list.exec_seqno}'/>"/>
			            <input type="hidden" name="exec_status_arr" id="exec_status_arr" value="<c:out value='${list.exec_status}'/>"/>
			            <input type="hidden" id="exec_gbn_arr" name="exec_gbn_arr" value="<c:out value='${list.exec_gbn}'/>" >
  
			         </td>
	            <script language='javascript'>
				initCal("exec_plndday_arr1<c:out value='${list.exec_seqno}'/>");
				initCal("exec_cmpltday_arr1<c:out value='${list.exec_seqno}'/>");
	           	</script>
				     </tr>
	
			</c:when>
			
			<c:when test="${list.exec_status=='C03102'}">
			<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'" id="tr_<c:out value='${list.exec_seqno}'/>">
			    <!-- <td id='exec_rn'><c:out value='${list.rno}'/></td> //-->
			    <td class="tL txtover"  title="<c:out value='${list.exec_itm}'/>"><nobr><c:out value='${list.exec_itm}'/></nobr>
			    <input type="hidden" name="exec_itm_arr" id="exec_itm_arr" value="<c:out value='${list.exec_itm}'/>"/></td>
			    <td><c:out value='${list.exec_plndday}'/>
			    <input type="hidden" name="exec_plndday_arr" id="exec_plndday_arr<c:out value='${list.rno}'/>" class="input_calendar" readonly="readonly" value="<c:out value='${list.exec_plndday}'/>" size="10" alt="<spring:message code="clm.page.msg.manage.compResDate" htmlEscape="true" />" required /></td>
			    <td class="tR"><c:out value='${list.exec_amt2}'/>
			    <input type="hidden" name="exec_amt_arr" id="exec_amt_arr" class="text_full" value="<c:out value='${list.exec_amt2}'/>" size="10" maxLength="15" alt="<spring:message code="clm.page.msg.manage.amount" htmlEscape="true" />" num /></td>
			    <td class="tL"><c:out value='${list.exec_cont}'/>
			    <input type="hidden" name="exec_cont_arr" id="exec_cont_arr" class="text_full" value="<c:out value='${list.exec_cont}'/>" size="20" maxLength="60" alt="<spring:message code="clm.page.msg.manage.sendCond" htmlEscape="true" />" /></td>
			    <td><input type="text" name="exec_cmpltday_arr" id="exec_cmpltday_arr1<c:out value='${list.rno}'/>" class="input_calendar" readonly="readonly" value="<c:out value='${list.exec_cmpltday}'/>" size="10" alt="<spring:message code="clm.page.msg.manage.compDate" htmlEscape="true" />" /></td>
			    <td><input type="text" name="note_arr" id="note_arr<c:out value='${list.exec_seqno}'/>" class="text_full" value="<c:out value='${list.note}'/>" size="20" maxLength="300" alt="<spring:message code="clm.page.msg.common.note" htmlEscape="true" />" /></td>
			    <td>
			    <span class="btn_option" id="btn_com"><span class="ico_com"></span>
			    	
			    	<a href='javascript:modifyExecution(
			    	"<c:out value='${list.cntrt_id}'/>"
			    	,"<c:out value='${list.exec_seqno}'/>"
			    	,"<c:out value='${list.exec_status}'/>"
			    	,"exec_cmpltday_arr1<c:out value='${list.rno}'/>"
				    ,"note_arr<c:out value='${list.exec_seqno}'/>"
			    	,"<c:out value='${status.count}'/>"
			    	);'><spring:message code="clm.page.msg.manage.complete" /></a></span>
			    
			    <span class="btn_option" id="btn_rev"><span class="ico_com"></span>
			    	
			    	<a href='javascript:addExecution(
			    	"<c:out value='${list.cntrt_id}'/>"
				    ,"<c:out value='${list.exec_seqno}'/>"
				    ,"<c:out value='${list.exec_status}'/>"
				    ,"exec_cmpltday_arr1<c:out value='${list.rno}'/>"
				    ,"note_arr<c:out value='${list.exec_seqno}'/>"
				    ,"<c:out value='${status.count}'/>"
				    );'><spring:message code="clm.page.msg.manage.change" /></a></span>
   
			    </td>
			    
			    <td>
			    <!-- <input type="checkbox" name="exec_chk_arr" id="exec_chk_arr" value="<c:out value='${list.exec_seqno}'/>"/> -->
	            <input type="hidden" name="exec_seqno_arr" id="exec_seqno_arr" value="<c:out value='${list.exec_seqno}'/>"/>
	            <input type="hidden" name="exec_status_arr" id="exec_status_arr" value="<c:out value='${list.exec_status}'/>"/>
	            <input type="hidden" id="exec_gbn_arr" name="exec_gbn_arr" value="<c:out value='${list.exec_gbn}'/>" >
	            </td>
	            <script language='javascript'>
					//initCal("exec_plndday_arr<c:out value='${list.rno}'/>");
					initCal("exec_cmpltday_arr1<c:out value='${list.rno}'/>");
	           	</script>
			</tr>
			</c:when>
			<c:otherwise>
			<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'" >
			   <!-- <td id='exec_rn'><c:out value='${list.rno}'/></td> //-->
			    <td class="tL txtover"  title="<c:out value='${list.exec_itm}'/>"><nobr><c:out value='${list.exec_itm}'/></nobr></td>
			    <td><c:out value='${list.exec_plndday}'/></td>
			    <td class="tR"><fmt:formatNumber value='${list.exec_amt2}' pattern="#,#00.00#"/></td>
			    <td class="tL txtover" title="<c:out value='${list.exec_cont}'/>"><nobr><c:out value='${list.exec_cont}'/></nobr></td>
			    <td><c:out value='${list.exec_cmpltday}'/></td>
			    <td class="tL txtover"><c:out value='${list.note}'/></td>
			    <td>
				    <c:choose>
				    	<c:when test="${list.exec_status=='C03101'}">
				    	    <spring:message code="clm.page.msg.manage.change" />
				    	</c:when>
				    	<c:when test="${list.exec_status=='C03104'}">
				    	    <spring:message code="clm.page.msg.manage.complete" />
				    	</c:when>
				    	<c:otherwise>
				    	<span class="btn_option"><span class="ico_com"></span>
				    		<a href='javascript:modifyExecution(
				    		"<c:out value='${list.cntrt_id}'/>"
				    		,"<c:out value='${list.exec_seqno}'/>"
				    		,"<c:out value='${list.exec_status}'/>
				    		,"exec_cmpltday_arr<c:out value='${list.rno}'/>"
				    		,"note_arr<c:out value='${list.exec_seqno}'/>"
				    		,"<c:out value='${status.count}'/>"
				    		);'><c:out value='${list.exec_statusnm}'/></a></span>
				 
				    	</c:otherwise>
				    </c:choose>
			    </td>
			    <td></td>
			</tr>
			</c:otherwise>
		</c:choose>
		</c:if>	
		</c:forEach>
	</c:when>
	    <c:otherwise>
			<tr id="notFoundList" onMouseOut="this.className='';" onMouseOver="this.className='selected'">
				<td colspan="8" align="center" class="tC"><spring:message code="clm.msg.succ.noResult" /></td>
			</tr>
	    </c:otherwise>
	</c:choose>
			</tbody>
		</table>
	</div>

<!-- //수금계획 -->
	<table cellspacing="0" cellpadding="0" border="0" class="list_basic" id="execution-mng2-content_tb">
		<colgroup>
						<!-- <col  width="5%"/> //-->
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
						<!--  <th>No</th> //-->
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
		<table cellspacing="0" cellpadding="0" border="0" class="list_basic" style="border-top:0px;" >
			<colgroup>
				<!-- <col  width="5%"/> //-->
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
		<c:forEach var="list" items="${executionLom}" varStatus="status">
		<c:if test="${list.exec_gbn=='C05502' }">
		<c:choose>
		
					<c:when test="${list.exec_status=='C03100'}">

					<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'" id="tr_<c:out value='${list.exec_seqno}'/>">
					 <!-- <td id='exec_rn'><c:out value='${list.rno}'/></td> //-->
					 <td><input type='text' name='exec_itm_arr' id='exec_itm_arr' class='text_full' value="<c:out value='${list.exec_itm}'/>" size='20' maxLength='30' alt="<spring:message code="clm.page.msg.manage.exeItm" htmlEscape="true" />" required readonly/></td>
			         <td><input type='text' name='exec_plndday_arr' id="exec_plndday_arr2<c:out value='${list.rno}'/>" class='input_calendar' readonly='readonly' value="<c:out value='${list.exec_plndday}'/>" size='10' alt="<spring:message code="clm.page.msg.manage.compResDate" htmlEscape="true" />" required /></td>
			         <td><input type='text' name='exec_amt_arr' id='exec_amt_arr' class='text_full' value="<c:out value='${list.exec_amt2}'/>" size='10' maxLength='15' alt="<spring:message code="clm.page.msg.manage.amount" htmlEscape="true" />" num required /></td>
			         <td><input type='text' name='exec_cont_arr' id='exec_cont_arr' class='text_full' value="<c:out value='${list.exec_cont}'/>" size='20' maxLength='60' alt="<spring:message code="clm.page.msg.manage.receiveCond" htmlEscape="true" />"' required /></td>
			         <td><input type='text' name='exec_cmpltday_arr' id="exec_cmpltday_arr2<c:out value='${list.exec_seqno}'/>" class="input_calendar<c:out value='${list.rno}'/>" readonly='readonly' value="<c:out value='${list.exec_cmpltday}'/>" size='10' alt="<spring:message code="clm.page.msg.manage.compDate" htmlEscape="true" />" /></td>
			         <td><input type='text' name='note_arr' id='note_arr<c:out value='${list.exec_seqno}'/>' class='text_full' value="<c:out value='${list.note}'/>" size='20' maxLength='300' alt="<spring:message code="clm.page.msg.common.note" htmlEscape="true" />" /></td>
 					 <td><span class="btn_option"><span class="ico_com"></span>
			         <a href='javascript:modifyExecution(
			         "<c:out value='${list.cntrt_id}'/>"
			    	,"<c:out value='${list.exec_seqno}'/>"
			    	,"<c:out value='${list.exec_status}'/>"
			    	,"exec_cmpltday_arr2<c:out value='${list.rno}'/>"
				    ,"note_arr<c:out value='${list.rno}'/>"
			    	,"<c:out value='${status.count}'/>");'><spring:message code="clm.page.msg.common.save" /></a></span></td>
			         <td>
			         <td>
						    <input type="hidden" name="exec_seqno_arr" id="exec_seqno_arr" value="<c:out value='${list.exec_seqno}'/>"/>
				            <input type="hidden" name="exec_status_arr" id="exec_status_arr" value="<c:out value='${list.exec_status}'/>"/>
				            <input type="hidden" id="exec_gbn_arr" name="exec_gbn_arr" value="<c:out value='${list.exec_gbn}'/>" >
			         </td>
			         
	            <script language='javascript'>
				initCal("exec_plndday_arr2<c:out value='${list.rno}'/>");
				initCal("exec_cmpltday_arr2<c:out value='${list.exec_seqno}'/>");
	           	</script>
				     </tr>
	
			</c:when>
		
		
		
			<c:when test="${list.exec_status=='C03102'}">
			<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'" id="tr_<c:out value='${list.exec_seqno}'/>">
			   <!-- <td id='exec_rn'><c:out value='${list.rno}'/></td> //-->
			    <td class="tL txtover"  title="<c:out value='${list.exec_itm}'/>"><nobr><c:out value='${list.exec_itm}'/></nobr>
			    <input type="hidden" name="exec_itm_arr" id="exec_itm_arr" value="<c:out value='${list.exec_itm}'/>"/></td>
			    <td><c:out value='${list.exec_plndday}'/>
			    <input type="hidden" name="exec_plndday_arr" id="exec_plndday_arr2<c:out value='${list.rno}'/>" class="input_calendar" readonly="readonly" value="<c:out value='${list.exec_plndday}'/>" size="10" alt="<spring:message code="clm.page.msg.manage.compResDate" htmlEscape="true" />" required /></td>
			    <td><input type="text" name="exec_amt_arr" id="exec_amt_arr" class="text_full" value="<c:out value='${list.exec_amt2}'/>" size="10" maxLength="15" alt="<spring:message code="clm.page.msg.manage.amount" htmlEscape="true" />" num /></td>
			    <td><input type="text" name="exec_cont_arr" id="exec_cont_arr" class="text_full" value="<c:out value='${list.exec_cont}'/>" size="20" maxLength="60" alt="<spring:message code="clm.page.msg.manage.receiveCond" htmlEscape="true" />" /></td>
			    <td><input type="text" name="exec_cmpltday_arr" id="exec_cmpltday_arr2<c:out value='${list.rno}'/>" class="input_calendar" readonly="readonly" value="<c:out value='${list.exec_cmpltday}'/>" size="10" alt="<spring:message code="clm.page.msg.manage.compDate" htmlEscape="true" />" /></td>
			    <td><input type="text" name="note_arr" id="note_arr<c:out value='${list.exec_seqno}'/>" class="text_full" value="<c:out value='${list.note}'/>" size="20" maxLength="300" alt="<spring:message code="clm.page.msg.common.note" htmlEscape="true" />" /></td>
			    <td>
			    <span class="btn_option" id="btn_com"><span class="ico_com"></span>
			    	
			    	<a href='javascript:modifyExecution(
			    	"<c:out value='${list.cntrt_id}'/>"
			    	,"<c:out value='${list.exec_seqno}'/>"
			    	,"<c:out value='${list.exec_status}'/>"
			    	,"exec_cmpltday_arr2<c:out value='${list.rno}'/>"
				    ,"note_arr<c:out value='${list.exec_seqno}'/>"
			    	,"<c:out value='${status.count}'/>"
			    	);'><spring:message code="clm.page.msg.manage.complete" /></a></span>
			    
			    <span class="btn_option" id="btn_rev"><span class="ico_com"></span>
			    	
			    	<a href='javascript:addExecution(
			    	"<c:out value='${list.cntrt_id}'/>"
				    ,"<c:out value='${list.exec_seqno}'/>"
				    ,"<c:out value='${list.exec_status}'/>"
				    ,"exec_cmpltday_arr2<c:out value='${list.rno}'/>"
				    ,"note_arr<c:out value='${list.exec_seqno}'/>"
				    ,"<c:out value='${status.count}'/>"
				    );'><spring:message code="clm.page.msg.manage.change" /></a></span>		    
			    </td>		    
			    <td>
			    <!--  <input type="checkbox" name="exec_chk_arr" id="exec_chk_arr" value="<c:out value='${list.exec_seqno}'/>"/> -->
	            <input type="hidden" name="exec_seqno_arr" id="exec_seqno_arr" value="<c:out value='${list.exec_seqno}'/>"/>
	            <input type="hidden" name="exec_status_arr" id="exec_status_arr" value="<c:out value='${list.exec_status}'/>"/>
	            <input type="hidden" id="exec_gbn_arr" name="exec_gbn_arr" value="<c:out value='${list.exec_gbn}'/>" ></td>
	            <script language='javascript'>
					//initCal("exec_plndday_arr<c:out value='${list.rno}'/>");
					initCal("exec_cmpltday_arr2<c:out value='${list.rno}'/>");
	           	</script>
			</tr>
			</c:when>
			<c:otherwise>
			<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
			    <!-- <td id='exec_rn'><c:out value='${list.rno}'/></td> //-->
			    <td class="tL txtover"  title="<c:out value='${list.exec_itm}'/>"><nobr><c:out value='${list.exec_itm}'/></nobr></td>
			    <td><c:out value='${list.exec_plndday}'/></td>
			    <td class="tR"><fmt:formatNumber value='${list.exec_amt2}' pattern="#,#00.00#"/></td>
			    <td class="tL" title="<c:out value='${list.exec_cont}'/>"><nobr><c:out value='${list.exec_cont}'/></nobr></td>
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
				    </td>
			    <td></td>
			</tr>
			</c:otherwise>
		</c:choose>
		</c:if>	
		</c:forEach>
	</c:when>
	    <c:otherwise>
			<tr id="notFoundList2" onMouseOut="this.className='';" onMouseOver="this.className='selected'">
				<td colspan="8" align="center" class="tC"><spring:message code="clm.msg.succ.noResult" /></td>
			</tr>
	    </c:otherwise>
	</c:choose>
			</tbody>
		</table>
	</div>
<!-- //기타 이행계획 -->
<table cellspacing="0" cellpadding="0" border="0" class="list_basic" id="execution-mng3-content_tb">
<colgroup>
			<!-- <col  width="5%"/> //-->
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
			<!--  <th>No</th> //-->
			<th><spring:message code="clm.page.msg.manage.exeItm" /></th>
			<th><spring:message code="clm.page.msg.manage.compResDate" /></th>
			<th>Description</th>
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
			<!-- <col  width="5%"/> //-->
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
	<c:forEach var="list" items="${executionLom}" varStatus="status">
	<c:if test="${list.exec_gbn=='C05503' }">
	<c:choose>
	
	
				<c:when test="${list.exec_status=='C03100'}">

					<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'" id="tr_<c:out value='${list.exec_seqno}'/>">
					 <!-- <td id='exec_rn'><c:out value='${list.rno}'/></td> //-->
					 <td><input type='text' name='exec_itm_arr' id='exec_itm_arr' class='text_full' value="<c:out value='${list.exec_itm}'/>" size='20' maxLength='30' alt="<spring:message code="clm.page.msg.manage.exeItm" htmlEscape="true" />" required readonly/></td>
			         <td><input type='text' name='exec_plndday_arr' id="exec_plndday_arr<c:out value='${list.rno}'/>" class='input_calendar' readonly='readonly' value="<c:out value='${list.exec_plndday}'/>" size='10' alt="<spring:message code="clm.page.msg.manage.compResDate" htmlEscape="true" />" required /></td>
			         <td><input type='text' name='exec_cont_arr' id='exec_cont_arr' class='text_full' value="<c:out value='${list.exec_cont}'/>" size='20' maxLength='60' alt='Description' required /></td>
			         <td><input type='text' name='exec_cmpltday_arr' id="exec_cmpltday_arr3<c:out value='${list.exec_seqno}'/>" class="input_calendar<c:out value='${list.rno}'/>" readonly='readonly' value="<c:out value='${list.exec_cmpltday}'/>" size='10' alt="<spring:message code="clm.page.msg.manage.compDate" htmlEscape="true" />"  /></td>
			         <td><input type='text' name='note_arr' id='note_arr' class='text_full' value="<c:out value='${list.note}'/>" size='20' maxLength='300' alt="<spring:message code="clm.page.msg.common.note" htmlEscape="true" />"  /></td>
			         <td><span class="btn_option">
			         
			         <span class="ico_com"></span><a href='javascript:modifyExecution("<c:out value='${list.cntrt_id}'/>"
			    	,"<c:out value='${list.exec_seqno}'/>"
			    	,"<c:out value='${list.exec_status}'/>"
			    	,"exec_cmpltday_arr3<c:out value='${list.exec_seqno}'/>"
				    ,"note_arr<c:out value='${list.exec_seqno}'/>"
			    	,"<c:out value='${status.count}'/>");'><spring:message code="clm.page.msg.common.save" /></a></span>
			    	
			    	
			    	</td>			         
			         
			         <td>
			         	    <input type="hidden" name="exec_seqno_arr" id="exec_seqno_arr" value="<c:out value='${list.exec_seqno}'/>"/>
				            <input type="hidden" name="exec_status_arr" id="exec_status_arr" value="<c:out value='${list.exec_status}'/>"/>
				            <input type="hidden" id="exec_gbn_arr" name="exec_gbn_arr" value="<c:out value='${list.exec_gbn}'/>" >
				            <input type="hidden" id="exec_amt_arr" name="exec_amt_arr" value="0" >
			         </td>
	            <script language='javascript'>
				initCal("exec_plndday_arr<c:out value='${list.rno}'/>");
				initCal("exec_cmpltday_arr3<c:out value='${list.exec_seqno}'/>");
	           	</script>
				     </tr>
	
			</c:when>
	
	
		<c:when test="${list.exec_status=='C03102'}">
			<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'" id="tr_<c:out value='${list.exec_seqno}'/>">
			    <!-- <td id='exec_rn'><c:out value='${list.rno}'/></td> //-->
			    <td class="tL txtover"  title="<c:out value='${list.exec_itm}'/>"><nobr><c:out value='${list.exec_itm}'/></nobr>
			    <input type="hidden" name="exec_itm_arr" id="exec_itm_arr" value="<c:out value='${list.exec_itm}'/>"/></td>
			    <td><c:out value='${list.exec_plndday}'/>
			    <input type="hidden" name="exec_plndday_arr" id="exec_plndday_arr<c:out value='${list.rno}'/>" class="input_calendar" readonly="readonly" value="<c:out value='${list.exec_plndday}'/>" size="10" alt="<spring:message code="clm.page.msg.manage.compResDate" htmlEscape="true" />" required /></td>
			    <td class="tL"><c:out value='${list.exec_cont}'/>
			    <input type="hidden" name="exec_cont_arr" id="exec_cont_arr"<c:out value='${list.rno}'/>" class="text_full" value="<c:out value='${list.exec_cont}'/>" size="20" maxLength="60" alt="Description" /></td>
			    <td><input type="text" name="exec_cmpltday_arr" id="exec_cmpltday_arr3<c:out value='${list.rno}'/>" class="input_calendar" readonly="readonly" value="<c:out value='${list.exec_cmpltday}'/>" size="10" alt="<spring:message code="clm.page.msg.manage.compDate" htmlEscape="true" />" /></td>
			    <td><input type="text" name="note_arr" id="note_arr<c:out value='${list.exec_seqno}'/>" class="text_full" value="<c:out value='${list.note}'/>" size="20" maxLength="300" alt="<spring:message code="clm.page.msg.common.note" htmlEscape="true" />" /></td>
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
   
			    </td>
			    
			    <td>
			    <!-- <input type="checkbox" name="exec_chk_arr" id="exec_chk_arr" value="<c:out value='${list.exec_seqno}'/>"/> -->
	            <input type="hidden" name="exec_seqno_arr" id="exec_seqno_arr" value="<c:out value='${list.exec_seqno}'/>"/>
	            <input type="hidden" name="exec_status_arr" id="exec_status_arr" value="<c:out value='${list.exec_status}'/>"/>
	            <input type="hidden" id="exec_gbn_arr" name="exec_gbn_arr" value="<c:out value='${list.exec_gbn}'/>" >
	            <input type="hidden" id="exec_amt_arr" name="exec_amt_arr" value="0" >
	            </td>
	            <script language='javascript'>
					//initCal("exec_plndday_arr<c:out value='${list.rno}'/>");
					initCal("exec_cmpltday_arr3<c:out value='${list.rno}'/>");
	           	</script>
			</tr>
			</c:when>
			
		<c:otherwise>
		<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
		   <!-- <td id='exec_rn'><c:out value='${list.rno}'/></td> //-->
		    <td class="tL txtover"  title="<c:out value='${list.exec_itm}'/>"><nobr><c:out value='${list.exec_itm}'/></nobr></td>
		    <td><c:out value='${list.exec_plndday}'/></td>
		    <td class="tL" title="<c:out value='${list.exec_cont}'/>"><nobr><c:out value='${list.exec_cont}'/></nobr></td>
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
		    <td>
		    <input type="hidden" id="exec_amt_arr" name="exec_amt_arr" value="0" >
		    </td>
		</tr>
		</c:otherwise>
	</c:choose>
	</c:if>
	</c:forEach>
</c:when>
    <c:otherwise>
		<tr id="notFoundList3" onMouseOut="this.className='';" onMouseOver="this.className='selected'">
			<td colspan="8" align="center" class="tC"><spring:message code="clm.msg.succ.noResult" /></td>
		</tr>
    </c:otherwise>
</c:choose>
		</tbody>
	</table>
</div>
</div>
<script language="javascript">
$(document).ready(function(){

	$('#max_exec_num').val("<c:out value='${max_exec_num}'/>");
	
		$('#execution-mng3-content_tb').attr("style","display:none");
		$('#execution-DetailTab3').addClass('on');
	
	//주요이행사항-지불계획, 기타 이행계획 tab
	$('#executionDetail li').bind('click', function(){
		$('#executionDetail li').removeClass('on');
		$(this).addClass('on');
	});
	
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
		$('#selected_paygbn').val("tab1");
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
		$('#selected_paygbn').val("tab2");
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
		$('#selected_paygbn').val("tab3");
	});

});

/**
* 지불계획추가
*/
function newExecution() {
	$('#notFoundList').remove();
	// 완료 OR 변경 버튼 비활성화 처리
 	$('#executionTbody :span[id=btn_com]').remove('');
	$('#executionTbody :span[id=btn_rev]').remove(''); 
	
	var html = "<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\">"
		// +	"<td id=\'exec_rn\'>"+($('#executionTbody tr').length+1)+"</td>"
		 +	"<td><input type=\'text\' name=\'exec_itm_arr\' id=\'exec_itm_arr\' class=\'text_full\' value=\'\' size=\'20\' maxLength=\'30\' alt=\'<spring:message code='clm.page.msg.manage.exeItm' htmlEscape='true' />\' required /></td>"
         +	"<td><input type=\'text\' name=\'exec_plndday_arr\' id=\'exec_plndday_arr"+($('#executionTbody tr').length+1)+"\' class=\'input_calendar\' readonly=\'readonly\' value=\'\' size=\'10\' alt=\'<spring:message code='clm.page.msg.manage.compResDate' htmlEscape='true' />\' required /></td>"
         +	"<td><input type=\'text\' name=\'exec_amt_arr\' id=\'exec_amt_arr\' class=\'text_full\' value=\'\' size=\'10\' maxLength=\'15\' alt=\'<spring:message code='clm.page.msg.manage.amount' htmlEscape='true' />\' num required /></td>"
         +	"<td><input type=\'text\' name=\'exec_cont_arr\' id=\'exec_cont_arr\' class=\'text_full\' value=\'\' size=\'20\' maxLength=\'60\' alt=\'<spring:message code='clm.page.msg.manage.sendCond' htmlEscape='true' />\' required /></td>"
         +	"<td><input type=\'text\' name=\'exec_cmpltday_arr\' id=\'exec_cmpltday_arr"+($('#executionTbody tr').length+1)+"\' class=\'input_calendar\' readonly=\'readonly\' value=\'\' size=\'10\' alt=\'<spring:message code='clm.page.msg.manage.compDate' htmlEscape='true' />\' /></td>"
         +	"<td><input type=\'text\' name=\'note_arr\' id=\'note_arr\' class=\'text_full\' value=\'\' size=\'20\' maxLength=\'300\' alt=\'<spring:message code='clm.page.msg.common.note' htmlEscape='true' />\' /></td>"
         +  "<td><span class=\"btn_option\"><span class=\"ico_com\"></span><a href=\'javascript:insertExecution();\'><spring:message code='clm.page.msg.common.save' /></a></span></td>"
         //+	"<td><span class=\"ico_uncom\">미확인</span></td>"
         +	"<td><input type=\'checkbox\' name=\'exec_chk_arr\' id=\'exec_chk_arr\' value=-1 /><input type=\'hidden\' id=\'exec_seqno_arr\' name=\'exec_seqno_arr\' value=-1 ><input type=\'hidden\' id=\'exec_gbn_arr\' name=\'exec_gbn_arr\' value=\'C05501\' ></td>"
	     + "</tr>"
         + "<script language='javascript'>"
         + "initCal(\'exec_plndday_arr"+($('#executionTbody tr').length+1)+"\');"
         + "initCal(\'exec_cmpltday_arr"+($('#executionTbody tr').length+1)+"\');"
         + "<"+"/script>";
	//+	"<img src=\'/images/secfw/ico/ico_input_calendar.gif\' onClick=\'popFrame.fPopCalendarDownMouse(exec_plndday_arr"+($('#executionTbody tr').length+1)+", exec_plndday_arr"+($('#executionTbody tr').length+1)+", popCal, popCal.style.left=document.body.scrollLeft + event.clientX, popCal.style.left=document.body.scrollLeft + event.clientY)\' class=\'cp\' /></td>"         
	$('#executionTbody').append(html);
	//$('#executionTbody tr:last td[id=exec_rn]').text($('#executionTbody tr').length);
	//$('#executionTbody tr:last td :input[name=exec_plndday]').attr("id","exec_plndday"+$('#executionTbody tr').length);
	$('#executionTbody tr:last td :input[name=exec_cont_arr]').focus();
	
}
/**
* 수금계획추가
*/
function newExecution2() {
	$('#notFoundList2').remove();
	$('#executionTbody2 :span[id=btn_com]').remove('');
	$('#executionTbody2 :span[id=btn_rev]').remove('');
	
	var html = "<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\">"
		// +	"<td id=\'exec_rn\'>"+($('#executionTbody2 tr').length+1)+"</td>"
		 +	"<td><input type=\'text\' name=\'exec_itm_arr\' id=\'exec_itm_arr\' class=\'text_full\' value=\'\' size=\'20\' maxLength=\'30\' alt=\'<spring:message code='clm.page.msg.manage.exeItm' htmlEscape='true' />\' required /></td>"
         +	"<td><input type=\'text\' name=\'exec_plndday_arr\' id=\'exec_plndday_arr2"+($('#executionTbody2 tr').length+1)+"\' class=\'input_calendar\' readonly=\'readonly\' value=\'\' size=\'10\' alt=\'<spring:message code='clm.page.msg.manage.compResDate' htmlEscape='true' />\' required /></td>"
         +	"<td><input type=\'text\' name=\'exec_amt_arr\' id=\'exec_amt_arr\' class=\'text_full\' value=\'\' size=\'10\' maxLength=\'15\' alt=\'<spring:message code='clm.page.msg.manage.amount' htmlEscape='true' />\' num required /></td>"
         +	"<td><input type=\'text\' name=\'exec_cont_arr\' id=\'exec_cont_arr\' class=\'text_full\' value=\'\' size=\'20\' maxLength=\'60\' alt=\'<spring:message code='clm.page.msg.manage.receiveCond' htmlEscape='true' />\' required /></td>"
         +	"<td><input type=\'text\' name=\'exec_cmpltday_arr\' id=\'exec_cmpltday_arr2"+($('#executionTbody2 tr').length+1)+"\' class=\'input_calendar\' readonly=\'readonly\' value=\'\' size=\'10\' alt=\'<spring:message code='clm.page.msg.manage.compDate' htmlEscape='true' />\' /></td>"
         +	"<td><input type=\'text\' name=\'note_arr\' id=\'note_arr\' class=\'text_full\' value=\'\' size=\'20\' maxLength=\'300\' alt=\'<spring:message code='clm.page.msg.common.note' htmlEscape='true' />\' /></td>"
         +  "<td><span class=\"btn_option\"><span class=\"ico_com\"></span><a href=\'javascript:insertExecution();\'><spring:message code='clm.page.msg.common.save' /></a></span></td>"
         //+	"<td><span class=\"ico_uncom\">미확인</span></td>"
         +	"<td><input type=\'checkbox\' name=\'exec_chk_arr\' id=\'exec_chk_arr\' value=-1 /><input type=\'hidden\' id=\'exec_seqno_arr\' name=\'exec_seqno_arr\' value=-1 ><input type=\'hidden\' id=\'exec_gbn_arr\' name=\'exec_gbn_arr\' value=\'C05502\' ></td>"
	     + "</tr>"
         + "<script language='javascript'>"
         + "initCal(\'exec_plndday_arr2"+($('#executionTbody2 tr').length+1)+"\');"
         + "initCal(\'exec_cmpltday_arr2"+($('#executionTbody2 tr').length+1)+"\');"
         + "<"+"/script>";
	//+	"<img src=\'/images/secfw/ico/ico_input_calendar.gif\' onClick=\'popFrame.fPopCalendarDownMouse(exec_plndday_arr"+($('#executionTbody2 tr').length+1)+", exec_plndday_arr"+($('#executionTbody2 tr').length+1)+", popCal, popCal.style.left=document.body.scrollLeft + event.clientX, popCal.style.left=document.body.scrollLeft + event.clientY)\' class=\'cp\' /></td>"         
	$('#executionTbody2').append(html);
	//$('#executionTbody2 tr:last td[id=exec_rn]').text($('#executionTbody2 tr').length);
	//$('#executionTbody2 tr:last td :input[name=exec_plndday]').attr("id","exec_plndday"+$('#executionTbody2 tr').length);
	$('#executionTbody2 tr:last td :input[name=exec_cont_arr]').focus();
	
}
/**
* 기타 이행계획추가
*/
function newExecution3() {
	
	$('#notFoundList3').remove();
	
	$('#executionTbody3 :span[id=btn_com]').remove('');
	$('#executionTbody3 :span[id=btn_rev]').remove('');
	
	var html = "<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\">"
	//	 +	"<td id=\'exec_rn\'>"+($('#executionTbody3 tr').length+1)+"</td>"
		 +	"<td><input type=\'text\' name=\'exec_itm_arr\' id=\'exec_itm_arr\' class=\'text_full\' value=\'\' size=\'20\' maxLength=\'30\' alt=\'<spring:message code='clm.page.msg.manage.exeItm' htmlEscape='true' />\' required /></td>"
         +	"<td><input type=\'text\' name=\'exec_plndday_arr\' id=\'exec_plndday_arr3"+($('#executionTbody3 tr').length+1)+"\' class=\'input_calendar\' readonly=\'readonly\' value=\'\' size=\'10\' alt=\'<spring:message code='clm.page.msg.manage.compResDate' htmlEscape='true' />\' required /></td>"
         +	"<td><input type=\'text\' name=\'exec_cont_arr\' id=\'exec_cont_arr\' class=\'text_full\' value=\'\' size=\'40\' maxLength=\'60\' alt=\'Description\' required />"
         +	"<input type=\'text\' name=\'exec_amt_arr\' id=\'exec_amt_arr\' class=\'text_full\' value=\'0\' size=\'10\' maxLength=\'15\' /></td>"
         +	"<td><input type=\'text\' name=\'exec_cmpltday_arr\' id=\'exec_cmpltday_arr3"+($('#executionTbody3 tr').length+1)+"\' class=\'input_calendar\' readonly=\'readonly\' value=\'\' size=\'10\' alt=\'<spring:message code='clm.page.msg.manage.compDate' htmlEscape='true' />\' /></td>"
         +	"<td><input type=\'text\' name=\'note_arr\' id=\'note_arr\' class=\'text_full\' value=\'\' size=\'20\' maxLength=\'300\' alt=\'<spring:message code='clm.page.msg.common.note' htmlEscape='true' />\' /></td>"
         +  "<td><span class=\"btn_option\"><span class=\"ico_com\"></span><a href=\'javascript:insertExecution();\'><spring:message code='clm.page.msg.common.save' /></a></span></td>"
         //+	"<td><span class=\"ico_uncom\">미확인</span></td>"
         +	"<td><input type=\'checkbox\' name=\'exec_chk_arr\' id=\'exec_chk_arr\' value=-1 /><input type=\'hidden\' id=\'exec_seqno_arr\' name=\'exec_seqno_arr\' value=-1 ><input type=\'hidden\' id=\'exec_gbn_arr\' name=\'exec_gbn_arr\' value=\'C05503\' ></td>"
	     + "<input type=\'hidden\' id=\'exec_amt_arr\' name=\'exec_amt_arr\' value=0 ></tr>"
         + "<script language='javascript'>"
         + "initCal(\'exec_plndday_arr3"+($('#executionTbody3 tr').length+1)+"\');"
         + "initCal(\'exec_cmpltday_arr3"+($('#executionTbody3 tr').length+1)+"\');"
         + "<"+"/script>";
	//+	"<img src=\'/images/secfw/ico/ico_input_calendar.gif\' onClick=\'popFrame.fPopCalendarDownMouse(exec_plndday_arr"+($('#executionTbody tr').length+1)+", exec_plndday_arr"+($('#executionTbody tr').length+1)+", popCal, popCal.style.left=document.body.scrollLeft + event.clientX, popCal.style.left=document.body.scrollLeft + event.clientY)\' class=\'cp\' /></td>"         
	$('#executionTbody3').append(html);
	//$('#executionTbody tr:last td[id=exec_rn]').text($('#executionTbody tr').length);
	//$('#executionTbody tr:last td :input[name=exec_plndday]').attr("id","exec_plndday"+$('#executionTbody tr').length);
	$('#executionTbody3 tr:last td :input[name=exec_cont_arr]').focus();	
}
/**
* 이행 저장
* 설명: 행추가 후 문서 전체 "저장" 클릭  시 사용될 메서드
*/
function insertExecution(exec_seqno, exec_status) {
	var frm = document.frm;	

    //유효성 체크
    if(validateForm(frm)){
    	if(confirm("<spring:message code="clm.page.msg.manage.announce147" />")){
    		
			var options = {
					url: "<c:url value='/clm/manage/execution.do?method=insertExecution&pgGbn=registration' />",
					type: "post",
					dataType: "html",
					beforeSubmit: function(formData, form){
						viewHiddenProgress(true);
					},		
					success: function(data) {
						viewHiddenProgress(false);
						//alert('<spring:message code="secfw.msg.succ.insert"/>');
						
						$("#execution-mng").html("");
						$("#execution-mng").html(data);
						
						detailBaseExecution2(frm.cntrt_id.value);
						//$('#executionTab li').removeClass('on');
						//$('#contract-executionDetailTab').addClass('on');
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
							$('#execution-DetailTab3').click();
							$('#execution-mng3-content_tb').attr("style","display:");
							$('#execution-DetailTab3').addClass('on');
						
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
}

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
				url: "<c:url value='/clm/manage/execution.do?method=modifyExecution' />",
				type: "post",
				dataType: "html",
				beforeSubmit: function(formData, form){
					viewHiddenProgress(true);
				},		
				success: function(data) {
					viewHiddenProgress(false);
					//alert('<spring:message code="secfw.msg.succ.modify"/>');
					$("#execution-mng").html("");
					$("#execution-mng").html(data);
					
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
						$('#execution-DetailTab3').click();
						$('#execution-mng3-content_tb').attr("style","display:");
						$('#execution-DetailTab3').addClass('on');
					
					if($('#selected_paygbn').val() == 'tab1'){
						$('#execution-DetailTab').click();
					} else if($('#selected_paygbn').val() == 'tab2'){
						$('#execution-DetailTab2').click();
					} else if($('#selected_paygbn').val() == 'tab3'){
						$('#execution-DetailTab3').click();
					}
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
	var exe_count = $('input[name=exec_seqno_arr]').length;
	
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
				url: "<c:url value='/clm/manage/execution.do?method=modifyExecution' />",
				type: "post",
				dataType: "html",
				beforeSubmit: function(formData, form){
					viewHiddenProgress(true);
				},		
				success: function(data) {
					
					viewHiddenProgress(false);
					
					//alert('<spring:message code="secfw.msg.succ.modify"/>');
					$("#execution-mng").html("");
					$("#execution-mng").html(data);

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
						$('#execution-DetailTab3').click();
						$('#execution-mng3-content_tb').attr("style","display:");
						$('#execution-DetailTab3').addClass('on');
					
					if($('#selected_paygbn').val() == 'tab1'){
						$('#execution-DetailTab').click();
					} else if($('#selected_paygbn').val() == 'tab2'){
						$('#execution-DetailTab2').click();
					} else if($('#selected_paygbn').val() == 'tab3'){
						$('#execution-DetailTab3').click();
					}

					var focus_num = $('#max_exec_num').val();
					//alert(focus_num);
					alert("<spring:message code="clm.page.msg.manage.announce089" />");
					$('#exec_cmpltday_arr' + focus_num).focus();
				}
			};
		$("#frm").ajaxSubmit(options);
	}	
	
}


/**
* 이행 삭제
*/
function deleteExecution() {
	
	var exec_chk_arr = document.getElementsByName("exec_chk_arr");
 	var j=0;
 	for(i=0;i<exec_chk_arr.length;i++){
		if(exec_chk_arr[i].checked == true){
			j++;
		}
	}
	
	if(j < 1){
		alert("<spring:message code='secfw.msg.ask.noSelect' />");
	} else {
		/* var options = {
				url: "<c:url value='/clm/manage/execution.do?method=deleteExecution' />",
				type: "post",
				dataType: "html",

				success: function(data) {
					alert('<spring:message code="secfw.msg.succ.delete" />');
					$("#execution-mng").html("");
					$("#execution-mng").html(data);
				}
			}
			$("#frm").ajaxSubmit(options); */
		$('input[name=exec_chk_arr]:checked').each(function(){
			if( $(this).val()<0 ){
				//alert("remove : " + $(this).parent().siblings('#exec_rn').text());				
				$(this).parent().parent().remove();
			}else if( $(this).val()>0 ){
				//alert("disable : " + $(this).parent().siblings('#exec_rn').text());				
				$(this).parent().parent().attr("style","display:none");
			}	
		//alert($('input[name=exec_chk_arr]:checked').length);
		//alert("remove : " + $(this).parent().siblings('#exec_rn').text());				
		});
		
	}
}
</script>