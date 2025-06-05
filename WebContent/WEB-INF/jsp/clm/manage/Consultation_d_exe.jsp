<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
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
<input type="hidden" name="exec_status"	id="exec_status"    value="" />
<div id="mainExecutionDetail-Content">
<div class="tab_box">
	<ul id="executionDetail" class="tab_basic">
	 
	<c:if test="${payment_gbn=='C02001'}">
		<li id="execution-DetailTab" class="on"><a href='javascript:'><spring:message code="clm.page.msg.manage.sendPlan" /></a></li>
		<li id="execution-DetailTab2"><a href='javascript:'><spring:message code="clm.page.msg.manage.receivePlan" /></a></li>
	</c:if> 
	<c:if test="${payment_gbn=='C02002' }">
		<li id="execution-DetailTab" class="on"><a href='javascript:'><spring:message code="clm.page.msg.manage.sendPlan" /></a></li>
		<li id="execution-DetailTab2" style="display:none;"><a href='javascript:'><spring:message code="clm.page.msg.manage.receivePlan" /></a></li>
	</c:if>
	<c:if test="${payment_gbn=='C02003' }">	
		<li id="execution-DetailTab" style="display:none;"><a href='javascript:'><spring:message code="clm.page.msg.manage.sendPlan" /></a></li>
		<li id="execution-DetailTab2" class="on"><a href='javascript:'><spring:message code="clm.page.msg.manage.receivePlan" /></a></li>
	</c:if>
	<c:if test="${payment_gbn=='C02004' }">	
		<li id="execution-DetailTab" style="display:none;"><a href='javascript:'><spring:message code="clm.page.msg.manage.sendPlan" /></a></li>
		<li id="execution-DetailTab2" style="display:none;"><a href='javascript:'><spring:message code="clm.page.msg.manage.receivePlan" /></a></li>
	</c:if>
	
	
	<%--
	 	<li id="execution-DetailTab" class="on"><a href='javascript:'><spring:message code="clm.page.msg.manage.sendPlan" /></a></li>
	 	<li id="execution-DetailTab2" ><a href='javascript:'><spring:message code="clm.page.msg.manage.receivePlan" /></a></li>
	--%>	
		<li id="execution-DetailTab3" ><a href='javascript:'><spring:message code="clm.page.msg.manage.etcPlan" /></a></li>
	</ul>
	<!-- button -->
		<!--// 지불계획 -->
		<div id="rowAddDel" class="btn_wrap_t01">
			<span class="btn"><span class="add"></span><a href="javascript:newExecution();"> <spring:message code="clm.page.msg.manage.addRow" /></a></span>
			<span class="btn"><span class="delete"></span><a href="javascript:deleteExecution();"> <spring:message code="clm.page.msg.manage.delRow" /></a></span>
		</div>
		<!--// 수금계획 -->
		<div id="rowAddDel2" class="btn_wrap_t01">
			<span class="btn"><span class="add"></span><a href="javascript:newExecution2();"> <spring:message code="clm.page.msg.manage.addRow" /></a></span>
			<span class="btn"><span class="delete"></span><a href="javascript:deleteExecution();"> <spring:message code="clm.page.msg.manage.delRow" /></a></span>
		</div>
		<!--// 기타 이행계획 -->
		<div id="rowAddDel3" class="btn_wrap_t01">
			<span class="btn"><span class="add"></span><a href="javascript:newExecution3();"> <spring:message code="clm.page.msg.manage.addRow" /></a></span>
			<span class="btn"><span class="delete"></span><a href="javascript:deleteExecution();"> <spring:message code="clm.page.msg.manage.delRow" /></a></span>
		</div>
	<!-- //button -->
</div>
<!-- //key hidden Form -->
<!-- //지불계획 -->

<%-- 
<c:if test="${payment_gbn=='C02001' or payment_gbn=='C02002'}">
 --%>
	<div id="execution-mng-content" class="list_addtal02">
		<table cellspacing="0" cellpadding="0" border="0" class="list_basic">
			<colgroup>
				<!-- 
				<col  width="5%"/>
				<col  width="13%"/>
				<col  width="12%"/>
				<col  width="13%"/>
				<col  width="17%"/>
				<col  width="14%"/>
				<col  width="11%"/>
				<col  width="9%"/>
				<col  width="6%"/>
				 -->
				<col  width="21%"/>
				<col  width="15%"/>
				<col  width="16%"/>
				<col  width="17%"/>
				<col  width="6%"/>
			</colgroup>
			<thead>
			<tr>
				<!-- <th>No</th>  -->
				<th><spring:message code="clm.page.msg.manage.payExeItem" /></th>
				<th><spring:message code="clm.page.msg.manage.compResDate" /></th>
				<th><spring:message code="clm.page.msg.manage.amount" /></th>
				<th><spring:message code="clm.page.msg.manage.sendCond" /></th>
				<!-- 
				<th><spring:message code="clm.page.msg.manage.compDate" /></th>
				<th><spring:message code="clm.page.msg.common.note" /></th>
				 -->
				<!-- <th><spring:message code="clm.page.field.execution.exec_status" /></th> -->
				<th><spring:message code="clm.page.msg.common.select" /></th>
			</tr>
			</thead>
			<tbody id="executionTbody">
			<input type="hidden" name="maxCount" id="maxCount" value=""/>
	<c:choose>
	<c:when test="${pageUtil.totalRow > 0}">
		<c:forEach var="list" items="${executionLom}">
		<c:if test="${list.exec_gbn=='C05501'}">
			<c:choose>
				<c:when test="${command.page_gbn=='modify'}">
					<c:choose>
						<c:when test="${command.prgrs_status=='C04211' or command.prgrs_status=='C04207' or command.prgrs_status=='C04212'}">
							<c:choose>
								<c:when test="${command.session_user_id == command.reqman_id}">
									<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
									    <%--<td id="exec_rn"><c:out value='${list.rno}'/></td>--%>
									    <td><input type="text" name="exec_itm_arr" id="exec_itm_arr" class="text_full" value="<c:out value='${list.exec_itm}'/>" required/></td>
									    <td><input type="text" name="exec_plndday_arr" id="exec_plndday_arr<c:out value='${list.rno}'/>" class="input_calendar" readonly="readonly" value="<c:out value='${list.exec_plndday}'/>" size="10" alt="<spring:message code="clm.page.msg.manage.compResDate" htmlEscape="true" />" required /></td>
									    <td><input type="text" name="exec_amt_arr" id="exec_amt_arr" class="text_full" value="<c:out value='${list.exec_amt}'/>" size="10" maxlength="18" alt="<spring:message code="clm.page.msg.manage.amount" htmlEscape="true" />" onkeyup='olnyNum(this)' style="IME-MODE: disabled;" required/></td>
									    <td><input type="text" name="exec_cont_arr" id="exec_cont_arr" class="text_full" value="<c:out value='${list.exec_cont}'/>" size="20" maxlength="200" alt="Description" required/></td>
									    <!-- 
									    <td>
									    	<input type="text" name="exec_cmpltday_arr" id="exec_cmpltday_arr<c:out value='${list.rno}'/>" class="input_calendar" readonly="readonly" value="<c:out value='${list.exec_cmpltday}'/>" size="10" alt="<spring:message code="clm.page.msg.manage.compDate" htmlEscape="true" />" />
									    	<img src='<%=IMAGE%>/icon/iconClear.gif' onClick="javascript:clearDate('<c:out value='${list.rno}'/>');" alt="Clear"/>
									    </td>
									    <td><input type="text" name="note_arr" id="note_arr" class="text_full" value="<c:out value='${list.note}'/>" size="20" maxLength="300" alt="<spring:message code="clm.page.msg.common.note" htmlEscape="true" />" /></td>
									     -->
									    <!-- <td><c:out value='${list.exec_statusnm}'/></td> -->
									    <td><input type="checkbox" name="exec_chk_arr" id="exec_chk_arr" value="<c:out value='${list.exec_seqno}'/>"/>
							            <input type="hidden" name="exec_seqno_arr" id="exec_seqno_arr" value="<c:out value='${list.exec_seqno}'/>"/>
							            <input type="hidden" name="exec_status_arr" id="exec_status_arr" value="<c:out value='${list.exec_status}'/>"/>
							            <input type="hidden" id="exec_gbn_arr" name="exec_gbn_arr" value="<c:out value='${list.exec_gbn}'/>" /></td>
							            <script language='javascript'>
											initCal("exec_plndday_arr<c:out value='${list.rno}'/>");
											//initCal("exec_cmpltday_arr<c:out value='${list.rno}'/>");
							           	</script>
									</tr>
								</c:when>
								<c:otherwise>
									<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
									    <%--<td id="exec_rn"><c:out value='${list.rno}'/></td>--%>
									    <td class="tL"  title="<c:out value='${list.exec_itm}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_itm}'/></span></td>
									    <td><c:out value='${list.exec_plndday}'/></td>
									    <td><c:out value='${list.exec_amt}'/></td>
									    <td title="<c:out value='${list.exec_cont}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_cont}'/></span></td>
									    <!-- 
									    <td><c:out value='${list.exec_cmpltday}'/></td>
									    <td><c:out value='${list.note}'/></td>
									     -->
									    <!-- <td><c:out value='${list.exec_statusnm}'/></td> -->
									    <td></td>
									</tr>
								</c:otherwise>
							</c:choose>		
						</c:when>
						<c:otherwise>
						<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
						    <%--<td id="exec_rn"><c:out value='${list.rno}'/></td> --%>
						    <td class="tL"  title="<c:out value='${list.exec_itm}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_itm}'/></span></td>
						    <td><c:out value='${list.exec_plndday}'/></td>
						    <td><c:out value='${list.exec_amt}'/></td>
						    <td title="<c:out value='${list.exec_cont}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_cont}'/></span></td>
						    <!-- 
						    <td><c:out value='${list.exec_cmpltday}'/></td>
						    <td><c:out value='${list.note}'/></td>
						     -->
						    <!-- <td><c:out value='${list.exec_statusnm}'/></td> -->
						    <td></td>
						</tr>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
					    <%-- <td id="exec_rn"><c:out value='${list.rno}'/></td> --%>
					    <td class="tL"  title="<c:out value='${list.exec_itm}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_itm}'/></span></td>
					    <td><c:out value='${list.exec_plndday}'/></td>
					    <td><c:out value='${list.exec_amt}'/></td>
					    <td title="<c:out value='${list.exec_cont}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_cont}'/></span></td>
					    <!-- 
					    <td><c:out value='${list.exec_cmpltday}'/></td>
					    <td><c:out value='${list.note}'/></td>
					     -->
					    <!-- <td><c:out value='${list.exec_statusnm}'/></td> -->
					    <td></td>
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
<%--  	
</c:if>
--%>
<!-- //수금계획 -->
<%-- 
<c:if test="${payment_gbn=='C02001' or payment_gbn=='C02003' }">
--%>
	<div id="execution-mng2-content" class="list_addtal02">
		<table cellspacing="0" cellpadding="0" border="0" class="list_basic">
			<colgroup>
				<!-- 
				<col  width="5%"/>
				<col  width="13%"/>
				<col  width="12%"/>
				<col  width="13%"/>
				<col  width="17%"/>
				<col  width="12%"/>
				<col  width="12%"/>
				<col  width="10%"/>
				<col  width="6%"/>
				 -->
				<col  width="21%"/>
				<col  width="15%"/>
				<col  width="16%"/>
				<col  width="17%"/>
				<col  width="6%"/>
			</colgroup>
			<thead>
			<tr>
				<!-- <th>No</th>  -->
				<th><spring:message code="clm.page.msg.manage.colExeItem" /></th>
				<th><spring:message code="clm.page.msg.manage.compResDate" /></th>
				<th><spring:message code="clm.page.msg.manage.amount" /></th>
				<th><spring:message code="clm.page.msg.manage.receiveCond" /></th>
				<!-- 
				<th><spring:message code="clm.page.msg.manage.compDate" /></th>
				<th><spring:message code="clm.page.msg.common.note" /></th>
				 -->
				<!-- <th><spring:message code="clm.page.field.execution.exec_status" /></th> -->
				<th><spring:message code="clm.page.msg.common.select" /></th>
			</tr>
			</thead>
			<tbody id="executionTbody2">
			<input type="hidden" name="maxCount2" id="maxCount2" value="">
	<c:choose>
	<c:when test="${pageUtil.totalRow > 0}">
		<c:forEach var="list" items="${executionLom}">
		<c:if test="${list.exec_gbn=='C05502' }">
			<c:choose>
				<c:when test="${command.page_gbn=='modify'}">
					<c:choose>
						<c:when test="${command.prgrs_status=='C04211' or command.prgrs_status=='C04207' or command.prgrs_status=='C04212'}">
							<c:choose>
								<c:when test="${command.session_user_id == command.reqman_id}">
									<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
									    <%-- <td id="exec_rn"><c:out value='${list.rno}'/></td> --%>
									    <td><input type="text" name="exec_itm_arr" id="exec_itm_arr" class="text_full" value="<c:out value='${list.exec_itm}'/>" required /></td>
									    <td><input type="text" name="exec_plndday_arr" id="exec_plndday_arr2<c:out value='${list.rno}'/>" class="input_calendar" readonly="readonly" value="<c:out value='${list.exec_plndday}'/>" size="10" alt="<spring:message code="clm.page.msg.manage.compResDate" htmlEscape="true" />" required /></td>
									    <td><input type="text" name="exec_amt_arr" id="exec_amt_arr" class="text_full" value="<c:out value='${list.exec_amt}'/>" size="10" maxLength="18" alt="<spring:message code="clm.page.msg.manage.amount" htmlEscape="true" />" required onkeyup='olnyNum(this)' style="IME-MODE: disabled;"/></td>
									    <td><input type="text" name="exec_cont_arr" id="exec_cont_arr" class="text_full" value="<c:out value='${list.exec_cont}'/>" size="20" maxLength="200" alt="Description" required/></td>
									    <!-- 
									    <td>
									    	<input type="text" name="exec_cmpltday_arr" id="exec_cmpltday_arr2<c:out value='${list.rno}'/>" class="input_calendar" readonly="readonly" value="<c:out value='${list.exec_cmpltday}'/>" size="10" alt="<spring:message code="clm.page.msg.manage.compDate" htmlEscape="true" />" />
									    	<img src='<%=IMAGE%>/icon/iconClear.gif' onClick="javascript:clearDate2('<c:out value='${list.rno}'/>');" alt="Clear"/>
									    </td>
									    <td><input type="text" name="note_arr" id="note_arr" class="text_full" value="<c:out value='${list.note}'/>" size="20" maxLength="300" alt="<spring:message code="clm.page.msg.common.note" htmlEscape="true" />" /></td>
									     -->
									    <!-- <td><c:out value='${list.exec_statusnm}'/></td> -->
									    <td><input type="checkbox" name="exec_chk_arr" id="exec_chk_arr" value="<c:out value='${list.exec_seqno}'/>"/>
							            <input type="hidden" name="exec_seqno_arr" id="exec_seqno_arr" value="<c:out value='${list.exec_seqno}'/>"/>
							            <input type="hidden" name="exec_status_arr" id="exec_status_arr" value="<c:out value='${list.exec_status}'/>"/>
							            <input type="hidden" id="exec_gbn_arr" name="exec_gbn_arr" value="<c:out value='${list.exec_gbn}'/>" ></td>
							            <script language='javascript'>
							                initCal("exec_plndday_arr2<c:out value='${list.rno}'/>");
											//initCal("exec_cmpltday_arr2<c:out value='${list.rno}'/>");
							           	</script>
									</tr>
								</c:when>
								<c:otherwise>
									<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
									    <%-- <td id="exec_rn"><c:out value='${list.rno}'/></td> --%>
									    <td class="tL"  title="<c:out value='${list.exec_itm}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_itm}'/></span></td>
									    <td><c:out value='${list.exec_plndday}'/></td>
									    <td><c:out value='${list.exec_amt}'/></td>
									    <td title="<c:out value='${list.exec_cont}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_cont}'/></span></td>
									    <!-- 
									    <td><c:out value='${list.exec_cmpltday}'/></td>
									    <td><c:out value='${list.note}'/></td>
									     -->
									    <!-- <td><c:out value='${list.exec_statusnm}'/></td> -->
									    <td></td>
									</tr>
								</c:otherwise>
							</c:choose>		
						</c:when>
						<c:otherwise>
						<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
						    <%-- <td id="exec_rn"><c:out value='${list.rno}'/></td> --%>
						    <td class="tL"  title="<c:out value='${list.exec_itm}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_itm}'/></span></td>
						    <td><c:out value='${list.exec_plndday}'/></td>
						    <td><c:out value='${list.exec_amt}'/></td>
						    <td title="<c:out value='${list.exec_cont}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_cont}'/></span></td>
						    <!-- 
						    <td><c:out value='${list.exec_cmpltday}'/></td>
						    <td><c:out value='${list.note}'/></td>
						     -->
						    <!-- <td><c:out value='${list.exec_statusnm}'/></td> -->
						    <td></td>
						</tr>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
					    <%-- <td id="exec_rn"><c:out value='${list.rno}'/></td> --%>
					    <td class="tL"  title="<c:out value='${list.exec_itm}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_itm}'/></span></td>
					    <td><c:out value='${list.exec_plndday}'/></td>
					    <td><c:out value='${list.exec_amt}'/></td>
					    <td title="<c:out value='${list.exec_cont}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_cont}'/></span></td>
					    <!-- 
					    <td><c:out value='${list.exec_cmpltday}'/></td>
					    <td><c:out value='${list.note}'/></td>
					     -->
					    <!-- <td><c:out value='${list.exec_statusnm}'/></td> -->
					    <td></td>
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
<%-- 	
</c:if>
--%>
<!-- //기타 이행계획 -->
<div id="execution-mng3-content" class="list_addtal02">
	<table cellspacing="0" cellpadding="0" border="0" class="list_basic">
		<colgroup>
			<!-- 
			<col  width="5%"/>
			<col  width="13%"/>
			<col  width="12%"/>
			<col  width="30%"/>
			<col  width="12%"/>
			<col  width="12%"/>
			<col  width="10%"/>
			<col  width="6%"/>
			 -->
			<col  width="22%"/>
			<col  width="15%"/>
			<col  width="33%"/>
			<col  width="6%"/>
		</colgroup>
		<thead>
		<tr>
			<!-- <th>No</th>  -->
			<th><spring:message code="clm.page.msg.manage.etcItm" /></th>
			<th><spring:message code="clm.page.msg.manage.compResDate" /></th>
			<th>Description</th>
			<!-- 
			<th><spring:message code="clm.page.msg.manage.compDate" /></th>
			<th><spring:message code="clm.page.msg.common.note" /></th>
			 -->
			<!-- <th><spring:message code="clm.page.field.execution.exec_status" /></th> -->
			<th><spring:message code="clm.page.msg.common.select" /></th>
		</tr>
		</thead>
		<tbody id="executionTbody3">
		<input type="hidden" name="maxCount3" id="maxCount3" value=""/>
<c:choose>
<c:when test="${pageUtil.totalRow > 0}">
	<c:forEach var="list" items="${executionLom}">
	<c:if test="${list.exec_gbn=='C05503'}">
		<c:choose>
			<c:when test="${command.page_gbn=='modify'}">
				<c:choose>
					<c:when test="${command.prgrs_status=='C04211' or command.prgrs_status=='C04207' or command.prgrs_status=='C04212'}">
						<c:choose>
							<c:when test="${command.session_user_id == command.reqman_id}">
								<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
								    <%-- <td id="exec_rn"><c:out value='${list.rno}'/></td> --%>
								    <td><input type="text" name="exec_itm_arr" id="exec_itm_arr" class="text_full" value="<c:out value='${list.exec_itm}'/>" required/></td>
								    <td><input type="text" name="exec_plndday_arr" id="exec_plndday_arr3<c:out value='${list.rno}'/>" class="input_calendar" readonly="readonly" value="<c:out value='${list.exec_plndday}'/>" size="10" alt="<spring:message code="clm.page.msg.manage.compResDate" htmlEscape="true" />" required /></td>
								    <td><input type="text" name="exec_cont_arr" id="exec_cont_arr" class="text_full" value="<c:out value='${list.exec_cont}'/>" size="20" maxlength="200" alt="Description" required/></td>
								    <!-- 
								    <td>
								    	<input type="text" name="exec_cmpltday_arr" id="exec_cmpltday_arr3<c:out value='${list.rno}'/>" class="input_calendar" readonly="readonly" value="<c:out value='${list.exec_cmpltday}'/>" size="10" alt="<spring:message code="clm.page.msg.manage.compDate" htmlEscape="true" />" />
								    	<img src='<%=IMAGE%>/icon/iconClear.gif' onClick="javascript:clearDate3('<c:out value='${list.rno}'/>');" alt="Clear"/>
								    </td>
								    <td><input type="text" name="note_arr" id="note_arr" class="text_full" value="<c:out value='${list.note}'/>" size="20" maxLength="300" alt="<spring:message code="clm.page.msg.common.note" htmlEscape="true" />" /></td>
								     -->
								    <!-- <td><c:out value='${list.exec_statusnm}'/></td> -->
								    <td><input type="checkbox" name="exec_chk_arr" id="exec_chk_arr" value="<c:out value='${list.exec_seqno}'/>"/>
							        <input type="hidden" name="exec_seqno_arr" id="exec_seqno_arr" value="<c:out value='${list.exec_seqno}'/>"/>
							        <input type="hidden" name="exec_status_arr" id="exec_status_arr" value="<c:out value='${list.exec_status}'/>"/>
							        <input type="hidden" id="exec_gbn_arr" name="exec_gbn_arr" value="<c:out value='${list.exec_gbn}'/>" />
							        <input type="hidden" id="exec_amt_arr" name="exec_amt_arr" value="0" /></td>
							        <script language='javascript'>
										initCal("exec_plndday_arr3<c:out value='${list.rno}'/>");
										//initCal("exec_cmpltday_arr3<c:out value='${list.rno}'/>");
							        </script>
								</tr>
							</c:when>
							<c:otherwise>
								<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
								    <%-- <td id="exec_rn"><c:out value='${list.rno}'/></td> --%>
								    <td class="tL"  title="<c:out value='${list.exec_itm}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_itm}'/></span></td>
								    <td><c:out value='${list.exec_plndday}'/></td>
								    <td title="<c:out value='${list.exec_cont}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_cont}'/></span></td>
								    <!-- 
								    <td><c:out value='${list.exec_cmpltday}'/></td>
								    <td><c:out value='${list.note}'/></td>
								     -->
								    <!-- <td><c:out value='${list.exec_statusnm}'/></td> -->
								    <td></td>
								</tr>
							</c:otherwise>
						</c:choose>	    		
					</c:when>
					<c:otherwise>
					<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
					    <%-- <td id="exec_rn"><c:out value='${list.rno}'/></td> --%>
					    <td class="tL"  title="<c:out value='${list.exec_itm}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_itm}'/></span></td>
					    <td><c:out value='${list.exec_plndday}'/></td>
					    <td title="<c:out value='${list.exec_cont}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_cont}'/></span></td>
					    <!-- 
					    <td><c:out value='${list.exec_cmpltday}'/></td>
					    <td><c:out value='${list.note}'/></td>
					     -->
					    <!-- <td><c:out value='${list.exec_statusnm}'/></td> -->
					    <td></td>
					</tr>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
				    <%--<td id="exec_rn"><c:out value='${list.rno}'/></td> --%>
				    <td class="tL"  title="<c:out value='${list.exec_itm}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_itm}'/></span></td>
				    <td><c:out value='${list.exec_plndday}'/></td>
				    <td title="<c:out value='${list.exec_cont}'/>"><span style="white-space: nowrap;"><c:out value='${list.exec_cont}'/></span></td>
				    <!-- 
				    <td><c:out value='${list.exec_cmpltday}'/></td>
				    <td><c:out value='${list.note}'/></td>
				     -->
				    <!-- <td><c:out value='${list.exec_statusnm}'/></td> -->
				    <td></td>
				</tr>
			</c:otherwise>
		</c:choose>		
	</c:if>
	</c:forEach>
</c:when>
    <c:otherwise>
		<tr id="notFoundList3" onmouseout="this.className='';" onmouseover="this.className='selected'">
			<td colspan="4" align="center" class="tC"><spring:message code="clm.msg.succ.noResult" /></td>
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
	//지불계획 display를 위한 처리...
	$('#execution-DetailTab').bind('click', function(){
		$('#execution-mng-content').attr("style","display:");
		$('#execution-mng2-content').attr("style","display:none");
		$('#execution-mng3-content').attr("style","display:none");
		if("<c:out value='${command.page_gbn}'/>"=="modify") {
			if("<c:out value='${command.prgrs_status}'/>"=="C04211" || "<c:out value='${command.prgrs_status}'/>" == "C04207") {
				if("<c:out value='${command.session_user_id}'/>"=="<c:out value='${command.reqman_id}'/>") {
					$('#rowAddDel').attr("style","display:");
					$('#rowAddDel2').attr("style","display:none");	
					$('#rowAddDel3').attr("style","display:none");
				} else {
					$('#rowAddDel').attr("style","display:none");
					$('#rowAddDel2').attr("style","display:none");	
					$('#rowAddDel3').attr("style","display:none");
				}	
			} else {
				$('#rowAddDel').attr("style","display:none");
				$('#rowAddDel2').attr("style","display:none");	
				$('#rowAddDel3').attr("style","display:none");
			}
		} else {
			$('#rowAddDel').attr("style","display:none");
			$('#rowAddDel2').attr("style","display:none");	
			$('#rowAddDel3').attr("style","display:none");
		}	
	});
	//수금계획 display를 위한 처리...
	$('#execution-DetailTab2').bind('click', function(){
		$('#execution-mng-content').attr("style","display:none");
		$('#execution-mng2-content').attr("style","display:");
		$('#execution-mng3-content').attr("style","display:none");
		if("<c:out value='${command.page_gbn}'/>"=="modify") {
			if("<c:out value='${command.prgrs_status}'/>"=="C04211" || "<c:out value='${command.prgrs_status}'/>" == "C04207") {
				if("<c:out value='${command.session_user_id}'/>"=="<c:out value='${command.reqman_id}'/>") {
					$('#rowAddDel').attr("style","display:none");
					$('#rowAddDel2').attr("style","display:");
					$('#rowAddDel3').attr("style","display:none");
				} else {
					$('#rowAddDel').attr("style","display:none");
					$('#rowAddDel2').attr("style","display:none");	
					$('#rowAddDel3').attr("style","display:none");
				}	
			} else {
				$('#rowAddDel').attr("style","display:none");
				$('#rowAddDel2').attr("style","display:none");	
				$('#rowAddDel3').attr("style","display:none");
			}
		} else {
			$('#rowAddDel').attr("style","display:none");
			$('#rowAddDel2').attr("style","display:none");	
			$('#rowAddDel3').attr("style","display:none");
		}	
	});
	//기타 이행계획 display를 위한 처리...
	$('#execution-DetailTab3').bind('click', function(){
		$('#execution-mng-content').attr("style","display:none");
		$('#execution-mng2-content').attr("style","display:none");
		$('#execution-mng3-content').attr("style","display:");
		if("<c:out value='${command.page_gbn}'/>"=="modify") {
			if("<c:out value='${command.prgrs_status}'/>"=="C04211" || "<c:out value='${command.prgrs_status}'/>" == "C04207") {
				if("<c:out value='${command.session_user_id}'/>"=="<c:out value='${command.reqman_id}'/>") {	
					$('#rowAddDel').attr("style","display:none");
					$('#rowAddDel2').attr("style","display:none");
					$('#rowAddDel3').attr("style","display:");
				} else {
					$('#rowAddDel').attr("style","display:none");
					$('#rowAddDel2').attr("style","display:none");	
					$('#rowAddDel3').attr("style","display:none");
				}	
			} else {
				$('#rowAddDel').attr("style","display:none");
				$('#rowAddDel2').attr("style","display:none");	
				$('#rowAddDel3').attr("style","display:none");
			}
		} else {
			$('#rowAddDel').attr("style","display:none");
			$('#rowAddDel2').attr("style","display:none");	
			$('#rowAddDel3').attr("style","display:none");
		}	
	});
});
/**
* 지불계획추가
*/
function newExecution() {
	if($('#payment_gbn > option:selected').val() == "C02001" || $('#payment_gbn > option:selected').val() == "C02002"){
	
	$('#notFoundList').remove();
	
	var maxCount = $('#maxCount').val();
	if(maxCount == ''){
		maxCount = $('#executionTbody tr').length+1;
	}else{
		maxCount = Number(maxCount)+1;
	}
	$('#maxCount').val(maxCount);
	
	var html = "<tr onmouseout=\"this.className=\'\';\" onmouseover=\"this.className=\'selected\'\">"
		 //+	"<td id=\'exec_rn\'>"+($('#maxCount').val())+"</td>"
		 +	"<td><input type=\'text\' name=\'exec_itm_arr\' id=\'exec_itm_arr\' class=\'text_full\' value=\'\' size=\'20\' maxLength=\'60\' alt=\'<spring:message code='clm.page.msg.manage.exeItm' htmlEscape='true' />\' required /></td>"
         +	"<td><input type=\'text\' name=\'exec_plndday_arr\' id=\'exec_plndday_arr"+($('#maxCount').val())+"\' class=\'input_calendar\' readonly=\'readonly\' value=\'\' size=\'10\' alt=\'<spring:message code='clm.page.msg.manage.compResDate' htmlEscape='true' />\' required /></td>"
         +	"<td><input type=\'text\' name=\'exec_amt_arr\' id=\'exec_amt_arr\' class=\'text_full\' value=\'\' size=\'10\' maxLength=\'18\' alt=\'<spring:message code='clm.page.msg.manage.amount' htmlEscape='true' />\' required onkeyup=\'olnyNum(this)\' style=\'IME-MODE: disabled\' /></td>"
         +	"<td><input type=\'text\' name=\'exec_cont_arr\' id=\'exec_cont_arr\' class=\'text_full\' value=\'\' size=\'20\' maxLength=\'60\' alt=\'Description\' required /></td>"
         //+	"<td><input type=\'text\' name=\'exec_cmpltday_arr\' id=\'exec_cmpltday_arr"+($('#maxCount').val())+"\' class=\'input_calendar\' readonly=\'readonly\' value=\'\' size=\'10\' alt=\'완료일\' >"
         //+  "  <img src=\'<%=IMAGE%>/icon/iconClear.gif\' onClick=\'javascript:clearDate("+($('#maxCount').val())+");\' alt=\'Clear\'/> "
         //+  "</td>"
         //+	"<td><input type=\'text\' name=\'note_arr\' id=\'note_arr\' class=\'text_full\' value=\'\' size=\'20\' maxLength=\'300\' alt=\'비고\' /></td>"
         //+	"<td><span class=\"ico_uncom\">미확인</span>"
         +	"<td><input type=\'checkbox\' name=\'exec_chk_arr\' id=\'exec_chk_arr\' value=-1 /><input type=\'hidden\' id=\'exec_seqno_arr\' name=\'exec_seqno_arr\' value=-1 ><input type=\'hidden\' id=\'exec_gbn_arr\' name=\'exec_gbn_arr\' value=\'C05501\' ></td>"
	     + "</tr>"
         + "<script language='javascript'>"
         + "initCal(\'exec_plndday_arr"+($('#maxCount').val())+"\');"
         //+ "initCal(\'exec_cmpltday_arr"+($('#maxCount').val())+"\');"
         + "<"+"/script>";
   	//+	"<img src=\'/images/secfw/ico/ico_input_calendar.gif\' onClick=\'popFrame.fPopCalendarDownMouse(exec_plndday_arr"+($('#executionTbody tr').length+1)+", exec_plndday_arr"+($('#executionTbody tr').length+1)+", popCal, popCal.style.left=document.body.scrollLeft + event.clientX, popCal.style.left=document.body.scrollLeft + event.clientY)\' class=\'cp\' /></td>"         
	$('#executionTbody').append(html);
	//$('#executionTbody tr:last td[id=exec_rn]').text($('#executionTbody tr').length);
	//$('#executionTbody tr:last td :input[name=exec_plndday]').attr("id","exec_plndday"+$('#executionTbody tr').length);
	$('#executionTbody tr:last td :input[name=exec_cont_arr]').focus();
	
	}else{
		alert("<spring:message code="clm.page.msg.manage.announce165" />");
		return;
	}
}
/**
* 수금계획추가
*/
function newExecution2() {
	if($('#payment_gbn > option:selected').val() == "C02001" || $('#payment_gbn > option:selected').val() == "C02003"){
	
	
	$('#notFoundList').remove();
	
	var maxCount = $('#maxCount2').val();
	if(maxCount == ''){
		maxCount = $('#executionTbody2 tr').length+1;
	}else{
		maxCount = Number(maxCount)+1;
	}
	$('#maxCount2').val(maxCount);
	
	var html = "<tr onmouseout=\"this.className=\'\';\" onmouseover=\"this.className=\'selected\'\">"
		// +	"<td id=\'exec_rn\'>"+($('#maxCount2').val())+"</td>"
		 +	"<td><input type=\'text\' name=\'exec_itm_arr\' id=\'exec_itm_arr\' class=\'text_full\' value=\'\' size=\'20\' maxLength=\'60\' alt=\'<spring:message code='clm.page.msg.manage.exeItm' htmlEscape='true' />\' required /></td>"
         +	"<td><input type=\'text\' name=\'exec_plndday_arr\' id=\'exec_plndday_arr2"+($('#maxCount2').val())+"\' class=\'input_calendar\' readonly=\'readonly\' value=\'\' size=\'10\' alt=\'<spring:message code='clm.page.msg.manage.compResDate' htmlEscape='true' />\' required /></td>"
         +	"<td><input type=\'text\' name=\'exec_amt_arr\' id=\'exec_amt_arr\' class=\'text_full\' value=\'\' size=\'10\' maxLength=\'18\' alt=\'<spring:message code='clm.page.msg.manage.amount' htmlEscape='true' />\' required onkeyup=\'olnyNum(this)\' style=\'IME-MODE: disabled\' /></td>"
         +	"<td><input type=\'text\' name=\'exec_cont_arr\' id=\'exec_cont_arr\' class=\'text_full\' value=\'\' size=\'20\' maxLength=\'60\' alt=\'Description\' required /></td>"
         //+	"<td><input type=\'text\' name=\'exec_cmpltday_arr\' id=\'exec_cmpltday_arr2"+($('#maxCount2').val())+"\' class=\'input_calendar\' readonly=\'readonly\' value=\'\' size=\'10\' alt=\'완료일\' />"
         //+  "  <img src=\'<%=IMAGE%>/icon/iconClear.gif\' onClick=\'javascript:clearDate2("+($('#maxCount2').val())+");\' alt=\'Clear\'/> "
         //+  "</td>"
         //+	"<td><input type=\'text\' name=\'note_arr\' id=\'note_arr\' class=\'text_full\' value=\'\' size=\'20\' maxLength=\'300\' alt=\'비고\' /></td>"
         //+	"<td><span class=\"ico_uncom\">미확인</span>"
         +	"<td><input type=\'checkbox\' name=\'exec_chk_arr\' id=\'exec_chk_arr\' value=-1 /><input type=\'hidden\' id=\'exec_seqno_arr\' name=\'exec_seqno_arr\' value=-1 ><input type=\'hidden\' id=\'exec_gbn_arr\' name=\'exec_gbn_arr\' value=\'C05502\' ></td>"
	     + "</tr>"
         + "<script language='javascript'>"
         + "initCal(\'exec_plndday_arr2"+($('#maxCount2').val())+"\');"
         //+ "initCal(\'exec_cmpltday_arr2"+($('#maxCount2').val())+"\');"
         + "<"+"/script>";
    //+	"<img src=\'/images/secfw/ico/ico_input_calendar.gif\' onClick=\'popFrame.fPopCalendarDownMouse(exec_plndday_arr"+($('#executionTbody2 tr').length+1)+", exec_plndday_arr"+($('#executionTbody2 tr').length+1)+", popCal, popCal.style.left=document.body.scrollLeft + event.clientX, popCal.style.left=document.body.scrollLeft + event.clientY)\' class=\'cp\' /></td>"         
	$('#executionTbody2').append(html);
	//$('#executionTbody2 tr:last td[id=exec_rn]').text($('#executionTbody2 tr').length);
	//$('#executionTbody2 tr:last td :input[name=exec_plndday]').attr("id","exec_plndday"+$('#executionTbody2 tr').length);
	$('#executionTbody2 tr:last td :input[name=exec_cont_arr]').focus();
	
	}else{
		alert("<spring:message code="clm.page.msg.manage.announce164" />");
		return;
	}
}
/**
* 기타 이행계획추가
*/
function newExecution3() {
	$('#notFoundList3').remove();
	
	var maxCount = $('#maxCount3').val();
	if(maxCount == ''){
		maxCount = $('#executionTbody3 tr').length+1;
	}else{
		maxCount = Number(maxCount)+1;
	}
	$('#maxCount3').val(maxCount);
	
	
	var html = "<tr onmouseout=\"this.className=\'\';\" onmouseover=\"this.className=\'selected\'\">"
		// +	"<td id=\'exec_rn\'>"+($('#maxCount3').val())+"</td>"
		 +	"<td><input type=\'text\' name=\'exec_itm_arr\' id=\'exec_itm_arr\' class=\'text_full\' value=\'\' size=\'20\' maxLength=\'60\' alt=\'<spring:message code='clm.page.msg.manage.exeItm' htmlEscape='true' />\' required /></td>"
         +	"<td><input type=\'text\' name=\'exec_plndday_arr\' id=\'exec_plndday_arr3"+($('#maxCount3').val())+"\' class=\'input_calendar\' readonly=\'readonly\' value=\'\' size=\'10\' alt=\'<spring:message code='clm.page.msg.manage.compResDate' htmlEscape='true' />\' required /></td>"
         +	"<td><input type=\'text\' name=\'exec_cont_arr\' id=\'exec_cont_arr\' class=\'text_full\' value=\'\' size=\'40\' maxLength=\'60\' alt=\'Description\' required /></td>"
         //+	"<td><input type=\'text\' name=\'exec_cmpltday_arr\' id=\'exec_cmpltday_arr3"+($('#maxCount3').val())+"\' class=\'input_calendar\' readonly=\'readonly\' value=\'\' size=\'10\' alt=\'완료일\' />"
         //+  "  <img src=\'<%=IMAGE%>/icon/iconClear.gif\' onClick=\'javascript:clearDate3("+($('#maxCount3').val())+");\' alt=\'Clear\'/> "
         //+  "</td>"
         //+	"<td><input type=\'text\' name=\'note_arr\' id=\'note_arr\' class=\'text_full\' value=\'\' size=\'20\' maxLength=\'300\' alt=\'비고\' /></td>"
         //+	"<td><span class=\"ico_uncom\">미확인</span>"
         +	"<td><input type=\'checkbox\' name=\'exec_chk_arr\' id=\'exec_chk_arr\' value=-1 /><input type=\'hidden\' id=\'exec_seqno_arr\' name=\'exec_seqno_arr\' value=-1 ><input type=\'hidden\' id=\'exec_gbn_arr\' name=\'exec_gbn_arr\' value=\'C05503\' ></td>"
	     + "<input type=\'hidden\' id=\'exec_amt_arr\' name=\'exec_amt_arr\' value=0 ></tr>"
         + "<script language='javascript'>"
         + "initCal(\'exec_plndday_arr3"+($('#maxCount3').val())+"\');"
         //+ "initCal(\'exec_cmpltday_arr3"+($('#maxCount3').val())+"\');"
         + "<"+"/script>";
	//+	"<img src=\'/images/secfw/ico/ico_input_calendar.gif\' onClick=\'popFrame.fPopCalendarDownMouse(exec_plndday_arr"+($('#executionTbody tr').length+1)+", exec_plndday_arr"+($('#executionTbody tr').length+1)+", popCal, popCal.style.left=document.body.scrollLeft + event.clientX, popCal.style.left=document.body.scrollLeft + event.clientY)\' class=\'cp\' /></td>"         
	$('#executionTbody3').append(html);
	//$('#executionTbody tr:last td[id=exec_rn]').text($('#executionTbody tr').length);
	//$('#executionTbody tr:last td :input[name=exec_plndday]').attr("id","exec_plndday"+$('#executionTbody tr').length);
	$('#executionTbody3 tr:last td :input[name=exec_cont_arr]').focus();
	
}
/**
* 이행 삭제
*/
function deleteExecution() {
	
	var exec_chk_arr = document.getElementsByName("exec_chk_arr");
 	var j=0;
 	for(var i=0;i<exec_chk_arr.length;i++){
		if(exec_chk_arr[i].checked == true){
			j++;
		}
	}
	
	if(j < 1){
		alert("<spring:message code='secfw.msg.ask.noSelect' />");
	} else {
		$('input[name=exec_chk_arr]:checked').each(function(){
			$(this).parent().parent().remove();
		});
		
	}
}
</script>