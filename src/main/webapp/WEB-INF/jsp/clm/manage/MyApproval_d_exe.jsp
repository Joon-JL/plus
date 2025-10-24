<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%-- 
/**
 * 파  일  명 : Myapproval_d_exe.jsp
 * 프로그램명 : 이행정보 조회 목록
 * 설      명 : 상세보기 page 내부의 Ajax로 호출되는 이행정보 조회 목록 page
 * 작  성  자 : 박정훈
 * 작  성  일 : 2013.05
 */
--%>

<script>
$(document).ready(function(){

	//지불_구분 C02001:지불/수금, C02002:지불, C02003:수금, C02004:해당없음
		$('#execution-mng-content').hide();
		$('#execution-mng2-content').hide();
		$('#execution-mng3-content').hide();
	
	if("<c:out value='${payment_gbn}'/>" == "C02001" || "<c:out value='${payment_gbn}'/>" == "C02002"){
		$('#executionDetail li').removeClass('on');
		$("#execution-DetailTab").addClass('on');
		$('#execution-mng-content').show();

	}else if("<c:out value='${payment_gbn}'/>" == "C02003"){
		$('#executionDetail li').removeClass('on');
		$("#execution-DetailTab2").addClass('on');
		$('#execution-mng2-content').show();
	}else if("<c:out value='${payment_gbn}'/>" == "C02004"){
		$('#executionDetail li').removeClass('on');
		$("#execution-DetailTab3").addClass('on');
		$('#execution-mng3-content').show();
	}else{
		$('#executionDetail li').removeClass('on');
	}
	
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
	$('#notFoundList').remove();
	var html = "<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\">"
		 +	"<td id=\'exec_rn\' class=\'tC\'>"+($('#executionTbody tr').length+1)+"</td>"
		 +	"<td><input type=\'text\' name=\'exec_itm_arr\' id=\'exec_itm_arr\' class=\'text_full\' value=\'\' size=\'20\' maxLength=\'30\' alt=\'<spring:message code='clm.page.msg.manage.exeItm' htmlEscape='true' />\' required /></td>"
         +	"<td class=\'tC\'><input type=\'text\' name=\'exec_plndday_arr\' id=\'exec_plndday_arr"+($('#executionTbody tr').length+1)+"\' class=\'input_calendar\' readonly=\'readonly\' value=\'\' size=\'10\' alt=\'<spring:message code='clm.page.msg.manage.compResDate' htmlEscape='true' />\' required /></td>"
         +	"<td class=\'tC\'><input type=\'text\' name=\'exec_amt_arr\' id=\'exec_amt_arr\' class=\'text_full\' value=\'\' size=\'10\' maxLength=\'15\' alt=\'<spring:message code='clm.page.msg.manage.amount' htmlEscape='true' />\' num required /></td>"
         +	"<td class=\'tC\'><input type=\'text\' name=\'exec_cont_arr\' id=\'exec_cont_arr\' class=\'text_full\' value=\'\' size=\'20\' maxLength=\'60\' alt=\'Description\' required /></td>"
         +	"<td class=\'tC\'><input type=\'text\' name=\'exec_cmpltday_arr\' id=\'exec_cmpltday_arr"+($('#executionTbody tr').length+1)+"\' class=\'input_calendar\' readonly=\'readonly\' value=\'\' size=\'10\' alt=\'<spring:message code='clm.page.msg.manage.compDate' htmlEscape='true' />\' ></td>"
         +	"<td class=\'tC\'><input type=\'text\' name=\'note_arr\' id=\'note_arr\' class=\'text_full\' value=\'\' size=\'20\' maxLength=\'300\' alt=\'<spring:message code='clm.page.msg.common.note' htmlEscape='true' />\' /></td>"
         +	"<td class=\'tC\'><span class=\"ico_uncom\"><spring:message code='clm.page.msg.manage.noConfirm' /></span></td>"
         +	"<td class=\'tC\'><input type=\'checkbox\' name=\'exec_chk_arr\' id=\'exec_chk_arr\' value=-1 /><input type=\'hidden\' id=\'exec_seqno_arr\' name=\'exec_seqno_arr\' value=-1 ><input type=\'hidden\' id=\'exec_gbn_arr\' name=\'exec_gbn_arr\' value=\'C05501\' ></td>"
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
	$('#notFoundList').remove();
	var html = "<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\">"
		 +	"<td id=\'exec_rn\' class=\'tC\'>"+($('#executionTbody2 tr').length+1)+"</td>"
		 +	"<td><input type=\'text\' name=\'exec_itm_arr\' id=\'exec_itm_arr\' class=\'text_full\' value=\'\' size=\'20\' maxLength=\'30\' alt=\'<spring:message code='clm.page.msg.manage.exeItm' htmlEscape='true' />\' required /></td>"
         +	"<td class=\'tC\'><input type=\'text\' name=\'exec_plndday_arr\' id=\'exec_plndday_arr2"+($('#executionTbody2 tr').length+1)+"\' class=\'input_calendar\' readonly=\'readonly\' value=\'\' size=\'10\' alt=\'<spring:message code='clm.page.msg.manage.compResDate' htmlEscape='true' />\' required /></td>"
         +	"<td class=\'tC\'><input type=\'text\' name=\'exec_amt_arr\' id=\'exec_amt_arr\' class=\'text_full\' value=\'\' size=\'10\' maxLength=\'15\' alt=\'<spring:message code='clm.page.msg.manage.amount' htmlEscape='true' />\' num required /></td>"
         +	"<td class=\'tC\'><input type=\'text\' name=\'exec_cont_arr\' id=\'exec_cont_arr\' class=\'text_full\' value=\'\' size=\'20\' maxLength=\'60\' alt=\'Description\' required /></td>"
         +	"<td class=\'tC\'><input type=\'text\' name=\'exec_cmpltday_arr\' id=\'exec_cmpltday_arr2"+($('#executionTbody2 tr').length+1)+"\' class=\'input_calendar\' readonly=\'readonly\' value=\'\' size=\'10\' alt=\'<spring:message code='clm.page.msg.manage.compDate' htmlEscape='true' />\' /></td>"
         +	"<td class=\'tC\'><input type=\'text\' name=\'note_arr\' id=\'note_arr\' class=\'text_full\' value=\'\' size=\'20\' maxLength=\'300\' alt=\'<spring:message code='clm.page.msg.common.note' htmlEscape='true' />\' /></td>"
         +	"<td class=\'tC\'><span class=\"ico_uncom\"><spring:message code='clm.page.msg.manage.noConfirm' /></span></td>"
         +	"<td class=\'tC\'><input type=\'checkbox\' name=\'exec_chk_arr\' id=\'exec_chk_arr\' value=-1 /><input type=\'hidden\' id=\'exec_seqno_arr\' name=\'exec_seqno_arr\' value=-1 ><input type=\'hidden\' id=\'exec_gbn_arr\' name=\'exec_gbn_arr\' value=\'C05502\' ></td>"
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
	var html = "<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\">"
		 +	"<td id=\'exec_rn\' class=\'tC\'>"+($('#executionTbody3 tr').length+1)+"</td>"
		 +	"<td class=\'tC\'><input type=\'text\' name=\'exec_itm_arr\' id=\'exec_itm_arr\' class=\'text_full\' value=\'\' size=\'20\' maxLength=\'30\' alt=\'<spring:message code='clm.page.msg.manage.exeItm' htmlEscape='true' />\' required /></td>"
         +	"<td class=\'tC\'><input type=\'text\' name=\'exec_plndday_arr\' id=\'exec_plndday_arr3"+($('#executionTbody3 tr').length+1)+"\' class=\'input_calendar\' readonly=\'readonly\' value=\'\' size=\'10\' alt=\'<spring:message code='clm.page.msg.manage.compResDate' htmlEscape='true' />\' required /></td>"
         +	"<td class=\'tC\'><input type=\'text\' name=\'exec_cont_arr\' id=\'exec_cont_arr\' class=\'text_full\' value=\'\' size=\'40\' maxLength=\'60\' alt=\'Description\' required /></td>"
         +	"<td class=\'tC\'><input type=\'text\' name=\'exec_cmpltday_arr\' id=\'exec_cmpltday_arr3"+($('#executionTbody3 tr').length+1)+"\' class=\'input_calendar\' readonly=\'readonly\' value=\'\' size=\'10\' alt=\'<spring:message code='clm.page.msg.manage.compDate' htmlEscape='true' />\' /></td>"
         +	"<td class=\'tC\'><input type=\'text\' name=\'note_arr\' id=\'note_arr\' class=\'text_full\' value=\'\' size=\'20\' maxLength=\'300\' alt=\'<spring:message code='clm.page.msg.common.note' htmlEscape='true' />\' /></td>"
         +	"<td class=\'tC\'><span class=\"ico_uncom\"><spring:message code='clm.page.msg.manage.noConfirm' /></span></td>"
         +	"<td class=\'tC\'><input type=\'checkbox\' name=\'exec_chk_arr\' id=\'exec_chk_arr\' value=-1 /><input type=\'hidden\' id=\'exec_seqno_arr\' name=\'exec_seqno_arr\' value=-1 ><input type=\'hidden\' id=\'exec_gbn_arr\' name=\'exec_gbn_arr\' value=\'C05503\' ></td>"
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
<!-- key hidden Form -->
<input type="hidden" name="exec_seqno"	id="exec_seqno"    value="<c:out value='${executionCommand.exec_seqno}'/>" />
<input type="hidden" name="exec_status"	id="exec_status"    value="" />


<!-- 이행정보 -->
<div class="title_basic" style='width:100%; margin:10px 0 0 0'>
	<h4><spring:message code="clm.page.msg.manage.exeInf" /></h4>
</div>

<div class="tab_box">
	<ul id="executionDetail" class="tab_basic">
		<c:if test="${payment_gbn=='C02001' or payment_gbn=='C02002' }">
		<li id="execution-DetailTab" class="on"><a href='javascript:'><spring:message code="clm.page.msg.manage.sendPlan" /></a></li>	
		</c:if>
		<c:if test="${payment_gbn=='C02001' or payment_gbn=='C02003' }">
		<li id="execution-DetailTab2" ><a href='javascript:'><spring:message code="clm.page.msg.manage.receivePlan" /></a></li>
		</c:if>
		<c:if test="${payment_gbn=='C02004' or payment_gbn=='C02001' or payment_gbn=='C02002'}">
		<li id="execution-DetailTab3" ><a href='javascript:'><spring:message code="clm.page.msg.manage.etcPlan" /></a></li>
		</c:if>
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
	<div id="execution-mng-content" >
		<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
			<colgroup>
				<col  width="5%"/>
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
				<th><spring:message code="clm.page.msg.common.sequence"/></th>
				<th><spring:message code="clm.page.msg.manage.exeItm" /></th>
				<th><spring:message code="clm.page.msg.manage.compResDate" /></th>
				<th><spring:message code="clm.page.msg.manage.amount" /></th>
				<th><spring:message code="clm.page.msg.manage.Description"/></th>
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
		<c:if test="${list.exec_gbn=='C05501'}">
			<c:choose>
				<c:when test="${command.page_gbn=='modify'}">
					<c:choose>
						<c:when test="${command.prgrs_status=='C04211' or command.prgrs_status=='C04207'}">
							<c:choose>
								<c:when test="${command.session_user_id == command.reqman_id}">
									<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
									    <td id="exec_rn" class='tC'><c:out value='${list.rno}'/></td>
									    <td><input type="text" name="exec_itm_arr" id="exec_itm_arr" class="text_full" value="<c:out value='${list.exec_itm}'/>" required/></td>
									    <td class='tC'><input type="text" name="exec_plndday_arr" id="exec_plndday_arr<c:out value='${list.rno}'/>" class="input_calendar" readonly="readonly" value="<c:out value='${list.exec_plndday}'/>" size="10" alt="<spring:message code="clm.page.msg.manage.compResDate" htmlEscape="true" />" required /></td>
									    <td class='tC'><input type="text" name="exec_amt_arr" id="exec_amt_arr" class="text_full" value="<c:out value='${list.exec_amt}'/>" size="10" maxLength="15" alt="<spring:message code="clm.page.msg.manage.amount" htmlEscape="true" />" num required/></td>
									    <td class='tC'><input type="text" name="exec_cont_arr" id="exec_cont_arr" class="text_full" value="<c:out value='${list.exec_cont}'/>" size="20" maxLength="60" alt="Description" required/></td>
									    <td class='tC'><input type="text" name="exec_cmpltday_arr" id="exec_cmpltday_arr<c:out value='${list.rno}'/>" class="input_calendar" readonly="readonly" value="<c:out value='${list.exec_cmpltday}'/>" size="10" alt="<spring:message code="clm.page.msg.manage.compDate" htmlEscape="true" />" /></td>
									    <td class='tC'><input type="text" name="note_arr" id="note_arr" class="text_full" value="<c:out value='${list.note}'/>" size="20" maxLength="300" alt="<spring:message code="clm.page.msg.common.note" htmlEscape="true" />" /></td>
									    <td class='tC'><c:out value='${list.exec_statusnm}'/></td>
									    <td class='tC'><input type="checkbox" name="exec_chk_arr" id="exec_chk_arr" value="<c:out value='${list.exec_seqno}'/>"/>
							            <input type="hidden" name="exec_seqno_arr" id="exec_seqno_arr" value="<c:out value='${list.exec_seqno}'/>"/>
							            <input type="hidden" name="exec_status_arr" id="exec_status_arr" value="<c:out value='${list.exec_status}'/>"/>
							            <input type="hidden" id="exec_gbn_arr" name="exec_gbn_arr" value="<c:out value='${list.exec_gbn}'/>" /></td>
							            <script language='javascript'>
											initCal("exec_plndday_arr<c:out value='${list.rno}'/>");
											initCal("exec_cmpltday_arr<c:out value='${list.rno}'/>");
							           	</script>
									</tr>
								</c:when>
								<c:otherwise>
									<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
									    <td id="exec_rn" class='tC'><c:out value='${list.rno}'/></td>
									    <td class="tL txtover"  title="<c:out value='${list.exec_itm}'/>"><nobr><c:out value='${list.exec_itm}' escapeXml="false"/></nobr></td>
									    <td class='tC'><c:out value='${list.exec_plndday}'/></td>
									    <td class="tR"><c:out value='${list.exec_amt}'/></td>
									    <td class="tC txtover" title="<c:out value='${list.exec_cont}'/>"><nobr><c:out value='${list.exec_cont}' escapeXml="false"/></nobr></td>
									    <td class='tC'><c:out value='${list.exec_cmpltday}'/></td>
									    <td class='tC'><c:out value='${list.note}' escapeXml="false"/></td>
									    <td class='tC'><c:out value='${list.exec_statusnm}'/></td>
									    <td class='tC'></td>
									</tr>
								</c:otherwise>
							</c:choose>		
						</c:when>
						<c:otherwise>
						<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
						    <td id="exec_rn" class='tC'><c:out value='${list.rno}'/></td>
						    <td class="tL txtover"  title="<c:out value='${list.exec_itm}'/>"><nobr><c:out value='${list.exec_itm}' escapeXml="false"/></nobr></td>
						    <td class='tC'><c:out value='${list.exec_plndday}'/></td>
						    <td class="tR"><c:out value='${list.exec_amt}'/></td>
						    <td class="tC txtover" title="<c:out value='${list.exec_cont}'/>"><nobr><c:out value='${list.exec_cont}' escapeXml="false"/></nobr></td>
						    <td class='tC'><c:out value='${list.exec_cmpltday}'/></td>
						    <td class="tC"><c:out value='${list.note}' escapeXml="false"/></td>
						    <td class='tC'><c:out value='${list.exec_statusnm}'/></td>
						    <td class='tC'></td>
						</tr>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
					    <td id="exec_rn" class='tC'><c:out value='${list.rno}'/></td>
					    <td class="tL txtover"  title="<c:out value='${list.exec_itm}'/>"><nobr><c:out value='${list.exec_itm}' escapeXml="false"/></nobr></td>
					    <td class='tC'><c:out value='${list.exec_plndday}'/></td>
					    <td class="tR"><c:out value='${list.exec_amt}'/></td>
					    <td class="tC txtover" title="<c:out value='${list.exec_cont}'/>"><nobr><c:out value='${list.exec_cont}' escapeXml="false"/></nobr></td>
					    <td class='tC'><c:out value='${list.exec_cmpltday}'/></td>
					    <td class="tC"><c:out value='${list.note}' escapeXml="false"/></td>
					    <td class='tC'><c:out value='${list.exec_statusnm}'/></td>
					    <td class='tC'></td>
					</tr>
				</c:otherwise>
			</c:choose>		
		</c:if>	
		</c:forEach>
	</c:when>
	    <c:otherwise>
			<tr id="notFoundList" onmouseout="this.className='';" onmouseover="this.className='selected'">
				<td colspan="9" align="center" class="tC"><spring:message code="clm.msg.succ.noResult" /></td>
			</tr>
	    </c:otherwise>
	</c:choose>
			</tbody>
		</table>
	</div>

<!-- //수금계획 -->	
	<div id="execution-mng2-content" >
		<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
			<colgroup>
				<col  width="5%"/>
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
				<th><spring:message code="clm.page.msg.common.sequence"/></th>
				<th><spring:message code="clm.page.msg.manage.exeItm" /></th>
				<th><spring:message code="clm.page.msg.manage.compResDate" /></th>
				<th><spring:message code="clm.page.msg.manage.amount" /></th>
				<th><spring:message code="clm.page.msg.manage.Description"/></th>
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
			<c:choose>
				<c:when test="${command.page_gbn=='modify'}">
					<c:choose>
						<c:when test="${command.prgrs_status=='C04211' or command.prgrs_status=='C04207'}">
							<c:choose>
								<c:when test="${command.session_user_id == command.reqman_id}">
									<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
									    <td id="exec_rn" class='tC'><c:out value='${list.rno}'/></td>
									    <td class='tC'><input type="text" name="exec_itm_arr" id="exec_itm_arr" class="text_full" value="<c:out value='${list.exec_itm}'/>" required /></td>
									    <td class='tC'><input type="text" name="exec_plndday_arr" id="exec_plndday_arr2<c:out value='${list.rno}'/>" class="input_calendar" readonly="readonly" value="<c:out value='${list.exec_plndday}'/>" size="10" alt="<spring:message code="clm.page.msg.manage.compResDate" htmlEscape="true" />" required /></td>
									    <td class='tR'><input type="text" name="exec_amt_arr" id="exec_amt_arr" class="text_full" value="<c:out value='${list.exec_amt}'/>" size="10" maxLength="15" alt="<spring:message code="clm.page.msg.manage.amount" htmlEscape="true" />" num required/></td>
									    <td class='tC'><input type="text" name="exec_cont_arr" id="exec_cont_arr" class="text_full" value="<c:out value='${list.exec_cont}'/>" size="20" maxLength="60" alt="Description" required/></td>
									    <td class='tC'><input type="text" name="exec_cmpltday_arr" id="exec_cmpltday_arr2<c:out value='${list.rno}'/>" class="input_calendar" readonly="readonly" value="<c:out value='${list.exec_cmpltday}'/>" size="10" alt="<spring:message code="clm.page.msg.manage.compDate" htmlEscape="true" />" /></td>
									    <td class='tC'><input type="text" name="note_arr" id="note_arr" class="text_full" value="<c:out value='${list.note}'/>" size="20" maxLength="300" alt="<spring:message code="clm.page.msg.common.note" htmlEscape="true" />" /></td>
									    <td class='tC'><c:out value='${list.exec_statusnm}'/></td>
									    <td class='tC'><input type="checkbox" name="exec_chk_arr" id="exec_chk_arr" value="<c:out value='${list.exec_seqno}'/>"/>
							            <input type="hidden" name="exec_seqno_arr" id="exec_seqno_arr" value="<c:out value='${list.exec_seqno}'/>"/>
							            <input type="hidden" name="exec_status_arr" id="exec_status_arr" value="<c:out value='${list.exec_status}'/>"/>
							            <input type="hidden" id="exec_gbn_arr" name="exec_gbn_arr" value="<c:out value='${list.exec_gbn}'/>" /></td>
							            <script language='javascript'>
							                initCal("exec_plndday_arr2<c:out value='${list.rno}'/>");
											initCal("exec_cmpltday_arr2<c:out value='${list.rno}'/>");
							           	</script>
									</tr>
								</c:when>
								<c:otherwise>
									<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
									    <td id="exec_rn" class='tC'><c:out value='${list.rno}'/></td>
									    <td class="tL"  title="<c:out value='${list.exec_itm}'/>"><nobr><c:out value='${list.exec_itm}' escapeXml="false"/></nobr></td>
									    <td class='tC'><c:out value='${list.exec_plndday}'/></td>
									    <td class='tR'><c:out value='${list.exec_amt}'/></td>
									    <td  class='tC' title="<c:out value='${list.exec_cont}'/>"><nobr><c:out value='${list.exec_cont}' escapeXml="false"/></nobr></td>
									    <td class='tC'><c:out value='${list.exec_cmpltday}'/></td>
									    <td class='tC'><c:out value='${list.note}' escapeXml="false"/></td>
									    <td class='tC'><c:out value='${list.exec_statusnm}'/></td>
									    <td class='tC'></td>
									</tr>
								</c:otherwise>
							</c:choose>		
						</c:when>
						<c:otherwise>
						<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
						    <td id="exec_rn" class='tC'><c:out value='${list.rno}'/></td>
						    <td class="tL"  title="<c:out value='${list.exec_itm}'/>"><nobr><c:out value='${list.exec_itm}' escapeXml="false"/></nobr></td>
						    <td class='tC'><c:out value='${list.exec_plndday}'/></td>
						    <td class='tR'><c:out value='${list.exec_amt}'/></td>
						    <td  class='tC'title="<c:out value='${list.exec_cont}'/>"><nobr><c:out value='${list.exec_cont}' escapeXml="false"/></nobr></td>
						    <td class='tC'><c:out value='${list.exec_cmpltday}'/></td>
						    <td class='tC'><c:out value='${list.note}' escapeXml="false"/></td>
						    <td class='tC'><c:out value='${list.exec_statusnm}'/></td>
						    <td class='tC'></td>
						</tr>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
					    <td id="exec_rn" class='tC'><c:out value='${list.rno}'/></td>
					    <td class="tL txtover"  title="<c:out value='${list.exec_itm}'/>"><nobr><c:out value='${list.exec_itm}' escapeXml="false"/></nobr></td>
					    <td class='tC'><c:out value='${list.exec_plndday}'/></td>
					    <td class="tR"><c:out value='${list.exec_amt}'/></td>
					    <td class="tC txtover" title="<c:out value='${list.exec_cont}'/>"><nobr><c:out value='${list.exec_cont}' escapeXml="false"/></nobr></td>
					    <td class='tC'><c:out value='${list.exec_cmpltday}'/></td>
					    <td class='tC'><c:out value='${list.note}' escapeXml="false"/></td>
					    <td class='tC'><c:out value='${list.exec_statusnm}'/></td>
					    <td class='tC'></td>
					</tr>
				</c:otherwise>
			</c:choose>		
		</c:if>	
		</c:forEach>
	</c:when>
	<c:otherwise>
			<tr id="notFoundList2" onmouseout="this.className='';" onmouseover="this.className='selected'">
				<td colspan="9" align="center" class="tC"><spring:message code="clm.msg.succ.noResult" /></td>
			</tr>
	    </c:otherwise>
	</c:choose>
			</tbody>
		</table>
	</div>

<!-- //기타 이행계획 -->
<div id="execution-mng3-content" >
	<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
		<colgroup>
			<col  width="5%"/>
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
			<th><spring:message code="clm.page.msg.common.sequence"/></th>
			<th><spring:message code="clm.page.msg.manage.exeItm" /></th>
			<th><spring:message code="clm.page.msg.manage.compResDate" /></th>
			<th><spring:message code="clm.page.msg.manage.Description"/></th>
			<th><spring:message code="clm.page.msg.manage.compDate" /></th>
			<th><spring:message code="clm.page.msg.common.note" /></th>
			<th><spring:message code="clm.page.field.execution.exec_status" /></th>
			<th><spring:message code="clm.page.msg.common.select" /></th>
		</tr>
		</thead>
		<tbody id="executionTbody3">
<c:choose>
<c:when test="${pageUtil.totalRow > 0}">
	<c:forEach var="list" items="${executionLom}">
	<c:if test="${list.exec_gbn=='C05503'}">
		<c:choose>
			<c:when test="${command.page_gbn=='modify'}">
				<c:choose>
					<c:when test="${command.prgrs_status=='C04211' or command.prgrs_status=='C04207'}">
						<c:choose>
							<c:when test="${command.session_user_id == command.reqman_id}">
								<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
								    <td id="exec_rn" class='tC'><c:out value='${list.rno}'/></td>
								    <td><input type="text" name="exec_itm_arr" id="exec_itm_arr" class="text_full" value="<c:out value='${list.exec_itm}'/>" required/></td>
								    <td class='tC'><input type="text" name="exec_plndday_arr" id="exec_plndday_arr3<c:out value='${list.rno}'/>" class="input_calendar" readonly="readonly" value="<c:out value='${list.exec_plndday}'/>" size="10" alt="<spring:message code="clm.page.msg.manage.compResDate" htmlEscape="true" />" required /></td>
								    <td class='tR'><input type="text" name="exec_cont_arr" id="exec_cont_arr" class="text_full" value="<c:out value='${list.exec_cont}'/>" size="20" maxlength="60" alt="Description" required/></td>
								    <td class='tC'><input type="text" name="exec_cmpltday_arr" id="exec_cmpltday_arr3<c:out value='${list.rno}'/>" class="input_calendar" readonly="readonly" value="<c:out value='${list.exec_cmpltday}'/>" size="10" alt="<spring:message code="clm.page.msg.manage.compDate" htmlEscape="true" />" /></td>
								    <td class='tC'><input type="text" name="note_arr" id="note_arr" class="text_full" value="<c:out value='${list.note}'/>" size="20" maxlength="300" alt="<spring:message code="clm.page.msg.common.note" htmlEscape="true" />" /></td>
								    <td class='tC'><c:out value='${list.exec_statusnm}'/></td>
								    <td class='tC'><input type="checkbox" name="exec_chk_arr" id="exec_chk_arr" value="<c:out value='${list.exec_seqno}'/>"/>
							        <input type="hidden" name="exec_seqno_arr" id="exec_seqno_arr" value="<c:out value='${list.exec_seqno}'/>"/>
							        <input type="hidden" name="exec_status_arr" id="exec_status_arr" value="<c:out value='${list.exec_status}'/>"/>
							        <input type="hidden" id="exec_gbn_arr" name="exec_gbn_arr" value="<c:out value='${list.exec_gbn}'/>" />
							        <input type="hidden" id="exec_amt_arr" name="exec_amt_arr" value="0" /></td>
							        <script language='javascript'>
										initCal("exec_plndday_arr3<c:out value='${list.rno}'/>");
										initCal("exec_cmpltday_arr3<c:out value='${list.rno}'/>");
							        </script>
								</tr>
							</c:when>
							<c:otherwise>
								<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
								    <td id="exec_rn" class='tC'><c:out value='${list.rno}'/></td>
								    <td class="tL"  title="<c:out value='${list.exec_itm}'/>"><nobr><c:out value='${list.exec_itm}' escapeXml="false"/></nobr></td>
								    <td class='tC'><c:out value='${list.exec_plndday}'/></td>
								    <td  class='tC' title="<c:out value='${list.exec_cont}'/>"><nobr><c:out value='${list.exec_cont}' escapeXml="false"/></nobr></td>
								    <td class='tC'><c:out value='${list.exec_cmpltday}'/></td>
								    <td class='tC'><c:out value='${list.note}' escapeXml="false"/></td>
								    <td class='tC'><c:out value='${list.exec_statusnm}'/></td>
								    <td class='tC'></td>
								</tr>
							</c:otherwise>
						</c:choose>	    		
					</c:when>
					<c:otherwise>
					<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
					    <td id="exec_rn" class='tC'><c:out value='${list.rno}'/></td>
					    <td class="tL"  title="<c:out value='${list.exec_itm}'/>"><nobr><c:out value='${list.exec_itm}' escapeXml="false"/></nobr></td>
					    <td class='tC'><c:out value='${list.exec_plndday}'/></td>
					    <td class='tC' title="<c:out value='${list.exec_cont}'/>"><nobr><c:out value='${list.exec_cont}' escapeXml="false"/></nobr></td>
					    <td class='tC'><c:out value='${list.exec_cmpltday}'/></td>
					    <td class='tC'><c:out value='${list.note}' escapeXml="false"/></td>
					    <td class='tC'><c:out value='${list.exec_statusnm}'/></td>
					    <td class='tC'></td>
					</tr>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
				    <td id="exec_rn" class='tC'><c:out value='${list.rno}'/></td>
				    <td class="tL txtover"  title="<c:out value='${list.exec_itm}'/>"><nobr><c:out value='${list.exec_itm}' escapeXml="false"/></nobr></td>
				    <td class='tC'><c:out value='${list.exec_plndday}'/></td>
				    <td class="tC txtover" title="<c:out value='${list.exec_cont}'/>"><nobr><c:out value='${list.exec_cont}' escapeXml="false"/></nobr></td>
				    <td class='tC'><c:out value='${list.exec_cmpltday}'/></td>
				    <td class="tC"><c:out value='${list.note}' escapeXml="false"/></td>
				    <td class='tC'><c:out value='${list.exec_statusnm}'/></td>
				    <td class='tC'></td>
				</tr>
			</c:otherwise>
		</c:choose>		
	</c:if>
	</c:forEach>
</c:when>
    <c:otherwise>
		<tr id="notFoundList3" onmouseout="this.className='';" onmouseover="this.className='selected'">
			<td colspan="8" align="center" class="tC"><spring:message code="clm.msg.succ.noResult" /></td>
		</tr>
    </c:otherwise>
</c:choose>
		</tbody>
	</table>
</div>
