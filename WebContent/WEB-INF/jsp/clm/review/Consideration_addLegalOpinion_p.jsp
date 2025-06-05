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
 * 파  일  명 : Consideration_addLegalOpinion_p.jsp
 * 프로그램명 : 추가 검토의견 입력 팝업
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2014.03.31
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
%>
<html>
<head>
<meta sci="Consideration_addLegalOpinion_p.jsp" />
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

<script type="text/javascript" src="/crosseditor/js/namo_scripteditor.js"></script>

<script language="JavaScript" type="text/JavaScript" >


/*
 * 내용 저장 하기
 */
function saveOpinion(){
	
	var frm = document.frm;
	
	var mark_src = "";
	var msg_comp = "<spring:message code='las.msg.succ.process' />";   // 처리 되었습니다.
	
	var time = new Date();
	var toMonth = time.getMonth() + 1;
	var toDay = time.getDate();
	var setToDay = "";
	var setToMonth = "";
	var nowHours = time.getHours();
	var nowMin = time.getMinutes();
	var nowSec = time.getSeconds();
	
	if(toMonth.toString().length == 1 ){
		setToMonth = "0" + toMonth;
	} else {
		setToMonth = toMonth;
	}
	
	if(toDay.toString().length == 1 ){
		setToDay = "0" + toDay
	} else {
		setToDay = toDay;
	}
	
	if(nowHours.toString().length == 1 ){
		setTime = "0" + nowHours;
	} else {
		setTime = nowHours;
	}
	
	if(nowMin.toString().length == 1 ){
		setMin = "0" + nowMin;
	} else {
		setMin = nowMin;
	}
	
	if(nowSec.toString().length == 1 ){
		setSec = "0" + nowSec;
	} else {
		setSec = nowSec;
	}
	
	var sumTime = setTime+ ":" + setMin+ ":" + setSec;
	
	var nowDate = time.getYear() + '-' + setToMonth + '-' + setToDay + " " + sumTime;
	
	var nowOpinion = CrossEditor.GetBodyValue();
	
	if(stripHTMLtag(nowOpinion) == ""){
		alert("<spring:message code="las.page.msg.manage.AddReviewOpRequ" />");
		return;
	}

	var add_opinion = frm.cont.value + '<br/><br/>------------------------- ['+ nowDate +'] ------------------------------<br/><br/>' + nowOpinion;
	
	frm.cnsd_opnn.value = add_opinion; //  종합검토의견W
	
	// 필수 체크
	if($('#close_cont').val()==''){
		alert("<spring:message code="clm.page.msg.manage.reason" /> " +"<spring:message code='las.msg.alert.isRequired'/>");
		$('#close_cont').focus();
		check_flag = false;
		return;
	}	
		
	var options = {   
			url: "<c:url value='/clm/review/consideration.do?method=updateLegOpinion' />",
			type: "post",
			dataType: "json",
			success: function(responseText, statusText) {
				if(responseText.returnCnt != 0) {
					var html = "";
					$.each(responseText, function(entryIndex, entry) {
						
						var return_val = entry['return_val'];
						
						if(return_val == '1'){
							alert(msg_comp); // 처리하였습니다.							
							
							$(opener.location).attr("href", "javascript:forwardConsideration('LIST')"); //  부모창을 새로고침 시킨다.
							
							self.close();  // 입력창을 닫아 버린다
						} else {
							alert("<spring:message code="clm.page.msg.manage.announce157" />");
						}									

					});						
				}	 
			}
	};		
	$("#frm").ajaxSubmit(options);	
}

</script>
</head>
<body class="pop" >

<!-- header -->
<h1><spring:message code="las.jsp.field.lawconsult.lasopnn"/> <spring:message code="las.page.field.lawconsulting.update"/> <spring:message code="las.page.field.statistics.return"/><!-- Close --><span class="close" onclick="javascript:self.close();" title="close"></span></h1>
<!-- //header -->
<div class="pop_area">
<!-- Popup Detail -->
		
		<!-- pop_content -->
		<div class="pop_content" style='height:544px'>
		<form:form name="frm" id="frm" method="post" autocomplete="off">
		<input type="hidden" name="method" 		value="">
		<input type="hidden" name="menu_id" 	value="<c:out value='${command.menu_id}'/>" />
		<input type="hidden" name="cntrt_id" 	value="<c:out value='${resultList[0].CNTRT_ID}'/>" />
		<input type="hidden" name="cnsdreq_id" 	value="<c:out value='${resultList[0].CNSDREQ_ID}'/>" />
		<input type="hidden" name="con_depth_status" value="<c:out value='${resultList[0].depth_status}'/>" />
		<input type="hidden" name="top_role" 	value="<c:out value='${command.top_role}'/>" />
		<input type="hidden" name=cnsd_opnn     value=""/>
					
		<table cellspacing='0' cellpadding='0' class='table-style01' border='0'>
			<colgroup>
				 <col width="15%" />
				 <col width="85%" />
			</colgroup>
			<tbody>
			<tr>
				<th><spring:message code="las.page.msg.manage.OldReviewOpRequ" /></th><!-- 이전 검토의견 --><!-- 내용 -->
				<td>
				    <textarea  id="cont" name="cont" rows="" cols="" style="display: none;" class="text_area_full" READONLY >
				        <c:out value='${resultList[0].CNSD_OPNN}' escapeXml=""/>
				    </textarea>
				    
				    <c:out value='${resultList[0].CNSD_OPNN}' escapeXml=""/>
				</td>
			</tr>
			<tr>
			    <th><spring:message code="las.page.msg.manage.NewReviewOpRequ" /> <span class='astro'>*</span></th><!-- 새로운 검토의견 --><!-- 내용 -->
			    <td>
			    <script type="text/javascript">										
											
					var CrossEditor = new NamoSE('namoeditor');
					CrossEditor.params.Width = "100%";
					CrossEditor.params.Height = "300px";
				    CrossEditor.params.UserLang = "enu";
				    CrossEditor.params.FullScreen = false;
				    CrossEditor.ShowTab(false);
				    CrossEditor.UserToolbar = true;
				    CrossEditor.params.CreateToolbar = "newdoc|saveas|print|spacebar|undo|redo|cut|copy|paste|pastetext|search|replace|selectall|spacebar|word_style|space|word_color|space|cancelattribute|spacebar|word_justify|space|word_indentset|spacebar|word_listset|spacebar|tableinsert|spacebar|tablerowinsert|tablerowdelete|spacebar|tablecolumninsert|tablecolumndelete|spacebar|tablecellmerge|tablecellsplit|spacebar|tablecellattribute|formatblock|space|fontname|space|fontsize|space|lineheight|spacebar|fullscreen";
				    CrossEditor.EditorStart();										

				</script>
			    
			</tr>
			</tbody>
		</table>
       </form:form>
</div>
</div>
<!--footer -->
<div class="pop_footer">
	<!-- button -->
	 <div class="btn_wrap fR">
	     <span class="btn_all_w"><span class="save"></span><a href="javascript:saveOpinion();"><spring:message code="las.page.button.contractmanager.consideration_d.resp" /></a></span>
	     <span class="btn_all_w" onclick="javascript:self.close();"><span class="cancel"></span><a><spring:message code="clm.page.msg.common.close" /></a></span>
	 </div>
<!-- //button -->
</div>
<!-- //footer -->
<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</body>
</html>