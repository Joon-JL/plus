<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%--
/**
 * 파  일  명 	 : ConclusionDelay_p.jsp
 * 프로그램명 : 의뢰인선택팝업
 * 설      명 	 : 
 * 작  성  자 	 : 심주완
 * 작  성  일       : 2011.08
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title" /></title>
<link href="<%=CSS%>/main.css"   type="text/css" rel="stylesheet"></link>
<link href="<%=CSS%>/popup.css"   type="text/css" rel="stylesheet"></link>
<link href="<%=CSS%>/common.css"   type="text/css" rel="stylesheet"></link>
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet"></link>

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<!-- 달력관련추가 -->
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script language="javascript">

function validationForm() {
	var frm = document.frm;
	
	
	if(typeof frm.chk_val_arr.length == "undefined") {
		frm.chgebfr_cnclsn_plndday_arr.value = frm.chgeaft_cnclsn_plndday_arr.value;
		if(frm.dlay_cause_arr.value == ""){
			alert("<spring:message code="clm.page.msg.manage.announce086" />");
            frm.dlay_cause_arr.focus();
            return false; 
        }
		
		if(frm.dlay_cause_arr.maxLength != null && !checkMaxLengthUtf8(frm.dlay_cause_arr.value, frm.dlay_cause_arr.maxLength)){
             alert("<spring:message code="clm.page.msg.manage.announce084" />"+ "\n\n"  + "<spring:message code="clm.page.msg.manage.stringLen" />" + " : " + frm.dlay_cause_arr.maxLength);
             frm.dlay_cause_arr.focus();
             return false; 
        }
		
		if(frm.chgeaft_cnclsn_plndday_arr.value=="") {
			 alert("<spring:message code="clm.page.msg.manage.announce171" />");
             frm.chgeaft_cnclsn_plndday_arr.focus();
             return false;
		}
	} else {
		for(var i=0; i < frm.chk_val_arr.length; i++) {
			//alert(frm.chgeaft_cnclsn_plndday_arr[0].value);
			if(i==0) {
				frm.chgebfr_cnclsn_plndday_arr[0].value = frm.chgeaft_cnclsn_plndday_arr[0].value;
			} else {
				frm.chgebfr_cnclsn_plndday_arr[i].value = frm.chgeaft_cnclsn_plndday_arr[i-1].value;
			}	
			if(frm.dlay_cause_arr[i].value == ""){
				alert("<spring:message code="clm.page.msg.manage.announce086" />");
	            frm.dlay_cause_arr[i].focus();
	            return false; 
	        }
			if(frm.dlay_cause_arr[i].maxLength != null && !checkMaxLengthUtf8(frm.dlay_cause_arr[i].value, frm.dlay_cause_arr[i].maxLength)){
	             alert("<spring:message code="clm.page.msg.manage.announce085" />"+ "\n\n"  + "<spring:message code="clm.page.msg.manage.stringLen" />" + " : " + frm.dlay_cause_arr[i].maxLength);
	             frm.dlay_cause_arr[i].focus();
	             return false; 
	        }
			
			if(frm.chgeaft_cnclsn_plndday_arr[i].value=="") {
				 alert("<spring:message code="clm.page.msg.manage.announce171" />");
	             frm.chgeaft_cnclsn_plndday_arr[i].focus();
	             return false;
			}
		}	
	}
	return true;
}

function goAction(){
	var frm = document.frm;
	
	if(!validationForm()) return;
	if(!validateThisForm()) return;
	//if(!validateForm()) return;
	frm.target = "_self";
	frm.method.value = "insertConclusionDelay";
	//var returnString = "";
	//var result = true;
	var options = {
		url: "<c:url value='/clm/manage/conclusionDelay.do' />",
		type: "post",
		dataType: "json",
		success: function(returnJson,returnMessage) {
			if(returnJson.result == "1") {
				opener.setDelayInfo("Y");				
			} else {
				opener.setDelayInfo("N");
			}
			self.close();
		}
	};
	
	$("#frm").ajaxSubmit(options);
}


//주요이행사항-행추가버튼클릭
function addRow() {
	var trcnt = $('#delay_tbody tr').length+1;
	//alert(trcnt);
	var html = "<tr id=\"delay_tr\">"
			 + "	<td>"
			 + "		<input type=\"checkbox\" class=\"checkbox\" name=\"chk_val_arr\" id=\"chk_val_arr\" value=\"Y\" />"
			 + "  		<input type=\"hidden\" name=\"del_yn_arr\" value=\"N\"/>"
			 + " 		<input type=\"hidden\" name=\"work_flag_arr\" value=\"I\"/>"
			 + "		<input type=\"hidden\" name=\"cntrt_id_arr\" id=\"cntrt_id_arr\" value=\"<c:out value='${delayCommand.cntrt_id}'/>\"/>"
  			 + "  		<input type=\"hidden\" name=\"dlay_seqno_arr\" id=\"dlay_seqno_arr\" value=\"-1\"/>"
  			 + "		<input type=\"hidden\" name=\"chgebfr_cnclsn_plndday_arr\" id=\"chgebfr_cnclsn_plndday_arr\"/>"
			 + "	</td>"
			 + " 	<td class=\"tL\">"
			 + "		<textarea name=\"dlay_cause_arr\" id=\"dlay_cause_arr\" cols=\"10\" rows=\"3\" class=\"text_area_full\" maxLength=\"1000\" required alt=\"<spring:message code='clm.page.msg.manage.rsnForNotConcl' htmlEscape='true' />\"></textarea>"
			 + "	</td>"
			 + "  	<td>"
			 + "        <input type=\"text\" name=\"chgeaft_cnclsn_plndday_arr\" id=\"chgeaft_cnclsn_plndday_arr" + trcnt + "\" class=\"text_calendar02\" require alt=\"<spring:message code='clm.page.msg.manage.conclResDate' htmlEscape='true' />\"/>"
  			 + "	</td>"
			 + "</tr>";
			 
	$('#delay_tbody').append(html);
	initCal("chgeaft_cnclsn_plndday_arr" + trcnt);
	$('#delay_tbody tr:last td:input[name=dlay_cause_arr]').focus();
	
}

//주요이행사항-행삭제버튼클릭
function deleteRow(){
	var frm = document.frm;
	var today = frm.today.value;
	$('#delay_tr :checked').siblings('input[name=del_yn_arr]').val("Y");
	$('#delay_tr :checked').each(function(){
		if($(this).siblings('input[name=work_flag_arr]').val()=="I") {
			if(typeof $(this).parent().parent().prev().contents().find('input[name=work_flag_arr]').val()!="undefined") {
				if($(this).parent().parent().prev().contents().find('input[name=work_flag_arr]').val()!="I") {
					var prevcnclsnplndday = ($(this).parent().parent().prev().contents().find('input[name=chgeaft_cnclsn_plndday_arr]').val()).replace(/-/g, "");
					if(Number(today)< Number(prevcnclsnplndday)) {
						$(this).parent().parent().prev().contents().find('input[type=hidden]').removeAttr("disabled");
						$(this).parent().parent().prev().contents().find('#chk_val_arr').removeAttr("disabled");	
						$(this).parent().parent().prev().contents().find('#dlay_cause_arr').removeAttr("disabled");
						$(this).parent().parent().prev().contents().find('#chk_val_arr').removeAttr("disabled");
						$(this).parent().parent().prev().contents().find('input[name=chgeaft_cnclsn_plndday_arr]').removeAttr("disabled");
					}
				}
			}
			$(this).parent().parent().remove();
			
		} else {
			//alert(typeof $(this).parent().parent().prev().contents().find('input[name=chgeaft_cnclsn_plndday_arr]').val());
			if(typeof $(this).parent().parent().prev().contents().find('input[name=chgeaft_cnclsn_plndday_arr]').val()!="undefined"){
				var prevcnclsnplndday = ($(this).parent().parent().prev().contents().find('input[name=chgeaft_cnclsn_plndday_arr]').val()).replace(/-/g, "");
				if(Number(today)< Number(prevcnclsnplndday)) {
					$(this).parent().parent().prev().contents().find('input[type=hidden]').removeAttr("disabled");
					$(this).parent().parent().prev().contents().find('#chk_val_arr').removeAttr("disabled");	
					$(this).parent().parent().prev().contents().find('#dlay_cause_arr').removeAttr("disabled");
					$(this).parent().parent().prev().contents().find('#chk_val_arr').removeAttr("disabled");
					$(this).parent().parent().prev().contents().find('input[name=chgeaft_cnclsn_plndday_arr]').removeAttr("disabled");
				}
			}
			$(this).parent().parent().contents().find('input[name=chgeaft_cnclsn_plndday_arr]').val(today);
			$(this).parent().parent().attr("style", "display:none");
		}
	});
}

function initCalendar() {
	$('input[type=text]').each(function(){
		if($(this).hasClass('text_calendar02')) {
			initCal($(this).attr("id"));	
		}
	});
}

function setInitControl() {
	var frm = document.frm;
	var today = frm.today.value;
	var trcnt = $('#delay_tbody tr').length;
	
	$('input[type=text]').each(function(idx){
		if($(this).hasClass('text_calendar02')) {
			var afterday = ($(this).val()).replace(/-/g, "");
			if(idx+1 < trcnt) {
				$(this).parent().parent().contents().find('input[type=hidden]').attr("disabled", "disabled");
				$(this).parent().parent().contents().find('#chk_val_arr').attr("disabled", "disabled");	
				$(this).parent().parent().contents().find('#dlay_cause_arr').attr("disabled", "disabled");
				$(this).attr("disabled", "disabled");
			} else {
				if(Number(afterday)< Number(today)) {
					$(this).parent().parent().contents().find('input[type=hidden]').attr("disabled", "disabled");
					$(this).parent().parent().contents().find('#chk_val_arr').attr("disabled", "disabled");	
					$(this).parent().parent().contents().find('#dlay_cause_arr').attr("disabled", "disabled");
					$(this).attr("disabled", "disabled");
				} 
			}	
		}
	});
}

function initPage() {
	initCalendar();
	setInitControl();
}

function validateThisForm() {
	var frm = document.frm;
	var today = frm.today.value;
	var result = true;
	
	$('input[type=checkbox]').each(function(idx){
		if($(this).parent().parent().contents().find('input[name=work_flag_arr]').val()!="I") {
			if(!$(this).attr("disabled")) {	//active한것
				if(typeof $(this).parent().parent().contents().find('input[name=chgeaft_cnclsn_plndday_arr]').val()!= "undefined") {
					var cnclsnplndday = ($(this).parent().parent().contents().find('input[name=chgeaft_cnclsn_plndday_arr]').val()).replace(/-/g, "");
					if(Number(today) > Number(cnclsnplndday)) {
						alert("<spring:message code='clm.msg.alert.delaycause.pastnotallow'/>");
						result = false;
						$(this).parent().parent().contents().find('input[name=chgeaft_cnclsn_plndday_arr]').focus();
						return false;
					}
				}
				if(typeof $(this).parent().parent().prev().contents().find('input[name=chgeaft_cnclsn_plndday_arr]').val()!= "undefined") {
					var prevcnclsnplndday = ($(this).parent().parent().prev().contents().find('input[name=chgeaft_cnclsn_plndday_arr]').val()).replace(/-/g, "");
					if(Number(prevcnclsnplndday) > Number(cnclsnplndday)) {
						alert("<spring:message code='clm.msg.alert.delaycause.prevpastnotallow'/>");
						$(this).parent().parent().contents().find('input[name=chgeaft_cnclsn_plndday_arr]').focus();
						result = false;
						return false;
					}
				}	
			}
		} else {
			if(typeof $(this).parent().parent().contents().find('input[name=chgeaft_cnclsn_plndday_arr]').val()!= "undefined") {
				var cnclsnplndday = ($(this).parent().parent().contents().find('input[name=chgeaft_cnclsn_plndday_arr]').val()).replace(/-/g, "");
				if(Number(today) > Number(cnclsnplndday)) {
					alert("<spring:message code='clm.msg.alert.delaycause.pastnotallow'/>");
					result = false;
					$(this).parent().parent().contents().find('input[name=chgeaft_cnclsn_plndday_arr]').focus();
					return false;
				}
			}
			if(typeof $(this).parent().parent().prev().contents().find('input[name=chgeaft_cnclsn_plndday_arr]').val()!= "undefined") {
				var prevcnclsnplndday = ($(this).parent().parent().prev().contents().find('input[name=chgeaft_cnclsn_plndday_arr]').val()).replace(/-/g, "");
				if(Number(prevcnclsnplndday) > Number(cnclsnplndday)) {
					alert("<spring:message code='clm.msg.alert.delaycause.prevpastnotallow'/>");
					$(this).parent().parent().contents().find('input[name=chgeaft_cnclsn_plndday_arr]').focus();
					result = false;
					return false;
				}
			}
		}	
		
		
	});
	
	
	/*$('input[name*=work_flag_arr]').each(function(){
		if($(this).val()== "I") {
			var cnclsnplndday = ($(this).parent().parent().contents().find('input[name=chgeaft_cnclsn_plndday_arr]').val()).replace(/-/g, "");
			if(Number(today) > Number(cnclsnplndday)) {
				alert("<spring:message code='clm.msg.alert.delaycause.pastnotallow'/>");
				$(this).parent().parent().contents().find('input[name=chgeaft_cnclsn_plndday_arr]').val("");
				result = false;
				return false;
			}
		}
	});*/
	return result;
}
</script>
</head>
<body class="pop" onload="javascript:initPage();">
<!-- header -->
<h1><spring:message code="clm.page.title.delaycause.mainTitle"/></h1>
<!-- //header -->
<!-- body -->
<div class="pop_area">
	<div>
		<form:form name="frm" id='frm' method="post" autocomplete="off"> 
	<!-- search form -->
	<input type="hidden" name="method" value="" />
	<input type="hidden" name="menu_id" id="menu_id" value="<c:out value='${delayCommand.menu_id}'/>" />
	<input type="hidden" name="today" id="today" value="<c:out value='${today}'/>" />
		<div class="pop_content">
    	<!-- Popup Detail -->
        <!-- Popup Size 600*600 -->
	  			<div class="btn_wrap_t04">
					<span class="btn"><span class="add"></span><a href="javascript:addRow();"><spring:message code="clm.page.button.contract.addrow"/></a></span>
					<span class="btn"><span class="delete"></span><a href="javascript:deleteRow();"><spring:message code="clm.page.button.contract.deleterow"/></a></span>
				</div>
				<table id="delay_table" border="0" cellspacing="0" cellpadding="0" class="list_basic">
				<colgroup>
					<col width="8%" />
					<col width="65%"/>
					<col width="18%" />
				</colgroup>
			 	<thead>
					<tr>
						<th><spring:message code="clm.page.field.delaycause.choice"/></th>
					  	<th><spring:message code="clm.page.field.delaycause.delaycause"/></th>
					  	<th><spring:message code="clm.page.field.delaycauce.cnclsnplndday"/></th>
				 	</tr>
				</thead>
			  	<tbody id="delay_tbody">
				<c:forEach var="list" items="${resultList}" varStatus="i">
					<tr id="delay_tr">
						<td>
							<input type="checkbox" class="checkbox" name="chk_val_arr" id="chk_val_arr" value="Y" />
							<input type="hidden" name="del_yn_arr" value="<c:out value='${list.del_yn}'/>"/>
							<input type="hidden" name="work_flag_arr" value="U"/>
							<input type="hidden" name="cntrt_id_arr" id="cntrt_id_arr" value="<c:out value='${list.cntrt_id}'/>"/>
      				  		<input type="hidden" name="dlay_seqno_arr" id="dlay_seqno_arr" value="<c:out value='${list.dlay_seqno}'/>"/>
      				  		<input type="hidden" name="chgebfr_cnclsn_plndday_arr" id="chgebfr_cnclsn_plndday" value="<c:out value='${list.chgebfr_cnclsn_plndday}'/>"/>
      				  	</td>	
					  	<td class="tL">
							<textarea name="dlay_cause_arr" id="dlay_cause_arr" cols="10" rows="3" class="text_area_full" required maxlength="1000" alt="<spring:message code="clm.page.msg.manage.rsnForNotConcl" htmlEscape="true" />"><c:out value="${list.dlay_cause}" escapeXml="false"/></textarea>
					  	</td>
					  	<td>
					  		<input type="text" name="chgeaft_cnclsn_plndday_arr" id="chgeaft_cnclsn_plndday_arr<c:out value='${i.count}'/>" value="<c:out value='${list.chgeaft_cnclsn_plndday}'/>"  class="text_calendar02" require alt="<spring:message code="clm.page.msg.manage.conclResDate" htmlEscape="true" />"/>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			</div>
			</form:form>
		</div>
	</div>	
	<div class="pop_footer">
		<!-- button -->
		<div class="btn_wrap fR">
			<span class="btn_all_b"><span class="save"></span><a href="javascript:goAction();"><spring:message code="clm.page.button.contract.save"/></a></span>
			<span class="btn_all_w"><span class="cancel"></span><a href="#" onclick="javascript:self.close();"><spring:message code="clm.page.button.contract.close"/></a></span>
		</div>
		<!-- //button -->
	</div>
	<!-- //body -->
	<!--footer -->
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</body>
</html>
