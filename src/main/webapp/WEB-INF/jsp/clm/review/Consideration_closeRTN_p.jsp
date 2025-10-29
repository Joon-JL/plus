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
 * 파  일  명 : Consideration_closeRTN_p.jsp
 * 프로그램명 :close 처리 사유 조회 팝업
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
<meta sci="Consideration_closeRTN_p.jsp" />
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
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
	
	//initCal("start_dt");
	//initCal("end_dt");	
	

	// 일정타입1
	//getCodeSelectByUpCd2("frm", "type1", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+"C071"+"&combo_abbr=F&combo_type=S2&combo_use_yn=Y&combo_selected="+"<c:out value='${lom.type1}'/>");	
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

</script>
</head>
<body class="pop" >

<!-- header -->
<h1><spring:message code="las.page.title.main.tab04"/> <spring:message code="las.page.button.lawconsult.close"/><!-- Close --><span class="close" onclick="javascript:self.close();" title="close"></span></h1>
<!-- //header -->
<div class="pop_area">
<!-- Popup Detail -->
		
		<!-- pop_content -->
		<div class="pop_content">
		<form:form name="frm" id="frm" method="post" autocomplete="off">
		<input type="hidden" name="method" 		value="">
		<input type="hidden" name="menu_id" 	value="<c:out value='${command.menu_id}'/>" />
			
		<table cellspacing='0' cellpadding='0' class='table-style01' border='0'>
			<colgroup>
				 <col width="20%" />
				 <col width="80%" />
			</colgroup>
			<tbody>
 			<tr>
				<th><spring:message code="clm.field.signmng.prcman"/><!-- 처리자 --></th>
				<td><c:out value='${resultList[0].REG_NM}'/></td>
			</tr> 
			<tr>
				<th><spring:message code="clm.field.signmng.prcday" /><!-- 처리일 --></th>
				<td><c:out value='${resultList[0].REG_DT}'/></td>
			</tr>
			<tr>
				<th><spring:message code="las.page.field.cal.Notes" /><!-- 내용 --></th>
				<td>
				<textarea  id="cont" name="cont" rows="" cols="" style="width:98%;height:105px" class="text_area_full" READONLY ><c:out value='${resultList[0].CLOSE_CONT}'/></textarea></td>
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
		<span class="btn_all_w mR5" onclick="self.close();"><span class="cancel"></span><a><spring:message code='las.page.button.close' /><!-- 닫기 --></a></span>
	</div>
</div>
<!-- //footer -->	
<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</body>
</html>