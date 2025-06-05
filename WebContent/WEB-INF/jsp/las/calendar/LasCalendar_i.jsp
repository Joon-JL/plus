<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%--
/**
 * 파  일  명 : LasCalendar_i.jsp
 * 프로그램명 : 법무캘린더 개인일정 등록 / 수정 팝업
 * 설      명 : 
 * 작  성  자 : 박병주
 * 작  성  일 : 2013.10.11
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
%>
	
<html>
<head>
<meta charset="utf-8" />

<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css"    type="text/css" rel="stylesheet">
<link href="<%=CSS%>/popup.css" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="/script/secfw/jquery/calendar/jquery.mousewheel.min.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>

<script language="JavaScript" type="text/JavaScript" >

/**
 * 초기화
 */
$(document).ready(function() {		
	
	initCal("start_dt");
	initCal("end_dt");	
	
	// 부모창에서 선택한 날짜를 일정입력난에 세팅
	if(''!="<c:out value='${command.pop_up_day}'/>"){
		$("#start_dt").val("<c:out value='${command.pop_up_day}'/>");
		$("#end_dt").val("<c:out value='${command.pop_up_day}'/>");
	} 
	
	<c:if test="${empty lom.end_hh}">
		$("#end_hh").val("23");
	</c:if>
	
	<c:if test="${empty lom.end_mm}">
		$("#end_mm").val("50");
	</c:if>	
	
	// 일정타입1
	getCodeSelectByUpCd2("frm", "type1", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+"C071"+"&combo_abbr=F&combo_type=S2&combo_use_yn=Y&combo_selected="+"<c:out value='${lom.type1}'/>");	
});

/**
* 글자수 제한[한글 3byte]
*/
	function f_chk_byte(aro_name,ari_max) {   
    var ls_str     = aro_name.value;
    var li_str_len = ls_str.length;
    var li_max      = ari_max;
    var i           = 0;
    var li_byte     = 0;
    var li_len      = 0;
    var ls_one_char = "";
    var ls_str2     = "";

    for(i=0; i< li_str_len; i++) {
        ls_one_char = ls_str.charAt(i);
        if (escape(ls_one_char).length > 4) 
            li_byte += 3;
        else 
            li_byte++;
        
        if (li_byte <= li_max) li_len = i + 1;
    }
    
    if(li_byte > li_max) {
        alert("<spring:message code='las.page.field.contractManager.overByte'/>");
        ls_str2 = ls_str.substr(0, li_len);
        aro_name.value = ls_str2;
    }
    aro_name.focus();   
}

/*
 * 저장 버튼시 체크
 */
function saveCheck(){
	
	var check_flag = true;		
	
	if($('#title').val()==''){
		alert($('#title').attr("alt")+"<spring:message code='las.msg.alert.isRequired'/>");
		$('#title').focus();
		check_flag = false;
		return;
	}	
	
	if($('#type1').val()==''){
		alert($('#type1').attr("alt")+"<spring:message code='las.msg.alert.isRequired'/>");
		check_flag = false;
		return;
	}
	
	if($('#start_dt').val()==''){
		alert($('#start_dt').attr("alt")+"<spring:message code='las.msg.alert.isRequired'/>");
		check_flag = false;
		return;
	}
	
	if($('#end_dt').val()==''){
		alert($('#end_dt').attr("alt")+"<spring:message code='las.msg.alert.isRequired'/>");
		check_flag = false;
		return;
	}
	
	return check_flag;
}	

/**
* 버튼처리
*/	
function pageAction(flag) {
	var frm = document.frm;		
	
	if(flag == "save"){		
 		
		if(!saveCheck())
			return;	
		
 		var confirmMessage = "<spring:message code="las.msg.ask.insert"/>"; // 등록 하시겠습니까 
 		
		if(confirm(confirmMessage)){					
			viewHiddenProgress(true) ;				
			frm.target = "_self";
			frm.action = "<c:url value='/las/cal.do' />";
			frm.method.value = "saveCalSKDL";
			frm.submit();
		}	 
	} else if(flag == "del"){		
		
 		var confirmMessage = "<spring:message code="las.msg.ask.delete"/>"; // 삭제 하시겠습니까 
 		
		if(confirm(confirmMessage)){					
			viewHiddenProgress(true) ;				
			frm.target = "_self";
			frm.action = "<c:url value='/las/cal.do' />";
			frm.method.value = "delCalSKDL";
			frm.submit();
		}	 
	}
}	

/**
* 메세지 표시 
*/
function showMessage(msg){
	viewHiddenProgress(false) ;
	if( msg != "" && msg != null && msg.length > 1 ) {
		
		alert(msg);			
		//  부모창을 등록한 일정의 시작 일정으로 세팅한후 DAY_TAB 활성화 상태로 처리한다.
		$('#srch_yyyymmdd2',opener.document).val("<c:out value='${command.srch_yyyymmdd2}'/>"); 
		$(opener.location).attr("href", "javascript:tabAction('tab_day')");
		
		self.close();
	}
}	

</script>
</head>
<body class="pop" onLoad='showMessage("<c:out value='${returnMessage}'/>");'>
<!-- Calendar  -->
<div id='popCal' style='position: absolute; visibility: hidden; ridge; width: 10; z-index:1'>
<iframe name="popFrame" src="<c:url value='/secfw/popCalendar.do?method=goPopCalendar' />" frameborder="0" scrolling="no" width="196" height="188"></iframe></div>
<script event=onclick() for=document> 
    if(popCal.style.visibility == "visible"){
      popCal.style.visibility="hidden";
    }
</script>

<!-- header -->
<h1><spring:message code="las.page.field.cal.skdlinsert" /><!-- 일정등록 --><span class="close" onclick="javascript:self.close();" title="close"></span></h1>
<!-- //header -->
<div class="pop_area">
<!-- Popup Detail -->
		
		<!-- pop_content -->
		<div class="pop_content">
		<form:form name="frm" id="frm" method="post" autocomplete="off">
		<input type="hidden" name="method" 		value="">
		<input type="hidden" name="menu_id" 	value="<c:out value='${command.menu_id}'/>" />
		<input type="hidden" name="seqno" 	value="<c:out value='${command.seqno}'/>" />

		<c:choose>
			<c:when test="${empty command.seqno}">
				<input type="hidden" id="write_mode" name="write_mode" value="INSERT">	
			</c:when>
			<c:otherwise>
				<input type="hidden" id="write_mode" name="write_mode" value="MODIFY">	
			</c:otherwise>
		</c:choose>
			
		<table cellspacing='0' cellpadding='0' class='table-style01' border='0'>
			<colgroup>
				 <col width="20%" />
				 <col width="80%" />
			</colgroup>
			<tbody>
			<tr>
				<th><spring:message code="las.page.field.common.title"/><!-- 제목 --></th>
				<td><input name="title" id="title" type="text" class='text w_98' value="<c:out value='${lom.title}'/>" alt="<spring:message code="las.page.field.common.title"/>" onkeyup="f_chk_byte(this,50)" maxLength='50'  ></td>
			</tr>
			<tr>
				<th><spring:message code="las.page.field.auth.reg_id"/><!-- 등록자 --></th>
				<td><c:out value='${command.session_user_nm}'/>/<c:out value='${command.session_dept_nm}'/></td>
			</tr>
			<tr>
				<th><spring:message code="las.page.field.cal.Type" /><!-- 일정타입 --></th>
				<td><select id="type1" name="type1" style='width:204px;' alt="type1"></select></td>
			</tr>
			<tr>
				<th><spring:message code="las.page.field.cal.Start" /><!-- 시작 --></th>
				<td>
					<input type="text" name=start_dt id="start_dt" required alt="Starts" value="<c:out value='${lom.start_dt}'/>" class="text_calendar02"/>
					
					<select id="start_hh" name="start_hh" style='width:40px;' alt="">
						<c:forEach begin="1" end="23" var='cnt'>	
							<option <c:if test="${lom.start_hh eq cnt}">selected</c:if>><c:out value='${cnt}'/></option>
						</c:forEach>	
					</select>
					:
					<select id="start_mm" name="start_mm" style='width:40px;' alt="">
						<option <c:if test="${lom.start_mm eq '00'}">selected</c:if>>00</option>
						<option <c:if test="${lom.start_mm eq '10'}">selected</c:if>>10</option>
						<option <c:if test="${lom.start_mm eq '20'}">selected</c:if>>20</option>
						<option <c:if test="${lom.start_mm eq '30'}">selected</c:if>>30</option>
						<option <c:if test="${lom.start_mm eq '40'}">selected</c:if>>40</option>
						<option <c:if test="${lom.start_mm eq '50'}">selected</c:if>>50</option>
					</select>				
				</td>
			</tr>
			<tr>
				<th><spring:message code="las.page.field.cal.End" /><!-- 끝 --></th>
				<td>
				<input type="text" name="end_dt" id="end_dt" required alt="Ends"  value="<c:out value='${lom.end_dt}'/>" class="text_calendar02"/>
					<select id="end_hh" name="end_hh" style='width:40px;' alt="">
						<c:forEach begin="1" end="23" var='cnt'>	
							<option <c:if test="${lom.end_hh eq cnt}">selected</c:if>><c:out value='${cnt}'/></option>
						</c:forEach>	
					</select>
					:
					<select id="end_mm" name="end_mm" style='width:40px;' alt="">
						<option <c:if test="${lom.end_mm eq '00'}">selected</c:if>>00</option>
						<option <c:if test="${lom.end_mm eq '10'}">selected</c:if>>10</option>
						<option <c:if test="${lom.end_mm eq '20'}">selected</c:if>>20</option>
						<option <c:if test="${lom.end_mm eq '30'}">selected</c:if>>30</option>
						<option <c:if test="${lom.end_mm eq '40'}">selected</c:if>>40</option>
						<option <c:if test="${lom.end_mm eq '50'}">selected</c:if>>50</option>
					</select>					
				</td>
			</tr>
			<tr>
				<th><spring:message code="las.page.field.cal.Location" /><!-- 장소 --></th>
				<td ><input name="loca" id="loca" type="text"  class='text w_98' value="<c:out value='${lom.loca}'/>" onkeyup="f_chk_byte(this,50)" maxLength='50'   ></td>
			</tr>
			<tr>
				<th><spring:message code="las.page.field.cal.Notes" /><!-- 내용 --></th>
				<td>
				<span id="cont1">0</span>/<spring:message code="clm.page.msg.manage.stringLen2000" /><br>
				<textarea  id="cont" name="cont" rows="" cols="" style="width:98%;height:105px" maxLength="2000" onkeyup="frmChkLenLang(this,2000,'cont1','<%=langCd%>')" class="text_area_full" ><c:out value='${lom.cont}'/></textarea></td>
			</tr>
			</tbody>
		</table>
					
		<!-- button 	 	<div class="btn_wrap fR mt20">	 	</div>  //button -->
	 	
       </form:form>
</div>
</div>
	<!--footer -->
<div class="pop_footer">
	<div class="btn_wrap fR" >
		<c:if test="${'MODIFY' eq command.write_mode}">
			<span class="btn_all_w" onclick="pageAction('del');"><span class="delete"></span><a><spring:message code="las.page.button.delete"/><!-- 삭제--></a></span>
		</c:if>		
		<span class="btn_all_w" onclick="pageAction('save');"><span class="save"></span><a><spring:message code="las.page.button.save"/><!-- 저장 --></a></span>
		<span class="btn_all_w mR5" onclick="self.close();"><span class="cancel"></span><a><spring:message code='las.page.button.close' /><!-- 닫기 --></a></span>
	</div>
</div>
<!-- //footer -->	
<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</body>
</html>