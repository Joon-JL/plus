<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ include file= "/WEB-INF/jsp/common/common.jsp" %>

<%-- 
/**
 * 파  일  명 : RegistrationApproval_d.jsp
 * 프로그램명 : 체결후등록 승인화면
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2013.04
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	
	ListOrderedMap m_contractMstLom = (ListOrderedMap)request.getAttribute("contractMstLom"); // 추가 2013.10.12
	String str_org_acpt_dlay_cause="";
	if (m_contractMstLom!=null) {
		str_org_acpt_dlay_cause = StringUtil.convertEnterToBR((String)m_contractMstLom.get("org_acpt_dlay_cause")); // BR처리문제로 추가
	}
%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />

<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet" />
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="/script/clms/common.js" type="text/javascript"></script>
<script language="javascript" src="/script/clms/clms_common.js" type="text/javascript"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>

<script language="javascript">
$(document).ready(function(){
	attachPage('<c:out value="${contractMstLom.cntrt_id}" />');
	contractHisList(); //2014-09-02 Sungwoo Replaced Contract History.
	
	
	// 준거법
	getCodeSelectByUpCd("frm", "loac", "/common/clmsCom.do?method=listComCdByGrpCd&combo_abbr=F&combo_grp_cd=C022&combo_type=S&combo_del_yn=N&combo_selected=" + "<c:out value='${lomRq.loac}'/>");
	// 분쟁_해결_방법
	getCodeSelectByUpCd("frm", "dspt_resolt_mthd", "/common/clmsCom.do?method=listComCdByGrpCd&combo_abbr=F&combo_grp_cd=C023&combo_type=S&combo_del_yn=N&combo_selected=");
	
	
});
	//첨부파일
	function attachPage(temp_cntrt_id){

		var frm = document.frm;
		
		//계약서 사전검토 파일
		frm.target          = "fileList33";
		frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value    = "forwardClmsFilePage";
		frm.fileInfoName.value = "fileInfos33";
		frm.fileFrameName.value = "fileList33";
		frm.file_bigclsfcn.value = "F012";
	    frm.file_midclsfcn.value = "F01201";
	    frm.file_smlclsfcn.value = "F0120101";
		frm.ref_key.value = temp_cntrt_id;
		frm.view_gbn.value = "download";
		
		getClmsFilePageCheck('frm');
		
		//체결본사본
		frm.target          		= "fileList4";
	    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    		= "forwardClmsFilePage";
	    frm.fileInfoName.value 		= "fileInfos4";
	    frm.fileFrameName.value 	= "fileList4";
	    frm.file_bigclsfcn.value = "F012";
	    frm.file_midclsfcn.value	= "F01203";
	    frm.file_smlclsfcn.value 	= "";
	    frm.ref_key.value			= temp_cntrt_id;
	    
	    getClmsFilePageCheck('frm');
		
	  	//단가내역
	    if("<c:out value='${contractMstLom.cntrt_untprc_expl}' />" != "") {
	        frm.target          		= "fileConsultationUntPrc";
	        frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
	        frm.method.value    		= "forwardClmsFilePage";
	        frm.file_midclsfcn.value 	= "F01202";
	        frm.file_smlclsfcn.value 	= "F0120212";
	        frm.ref_key.value   		= frm.cntrt_id.value + "@" + frm.cnsdreq_id.value;
	        frm.fileInfoName.value 		= "fileInfos5";
	        frm.fileFrameName.value 	= "fileConsultationUntPrc";
	        getClmsFilePageCheck('frm');
	    }
	    
	}
	
	/**
*  분쟁해결 방법 셀렉트 추가 
*/
function addDspt(){

	var orign_text  = $("#dspt_resolt_mthd_det").val();
	var add_text = $("#dspt_resolt_mthd option:selected").text();
	
	if($("#dspt_resolt_mthd option:selected").val()==''){
		alert("<spring:message code='clm.page.msg.common.announce006' />");
		return;
	} 

	if(orign_text!='')
		add_text = ' , ' + add_text;
	
	orign_text = orign_text + add_text;
	
	$("#dspt_resolt_mthd_det").val(orign_text);
	
}
	
	
//거래선 팝업
function customerPop2(customerCd, dodun){
	PopUpWindowOpen("/clm/draft/customerTest.do?method=detailCustomerShare&customer_cd="+customerCd + "&dodun=" + dodun, 900, 300, false);
}

/**
* 목록
*/
function listManageRegistration(){
	viewHiddenProgress(true);
	
	var frm = document.frm;

	frm.target = "_self";		 
	frm.action = "<c:url value='/clm/manage/registration.do' />";
	frm.method.value = "listRegistrationApproval";
	frm.submit();	

}

function getDateAddDay(ymd, addDay) {
	var yyyy = ymd.substr(0, 4);
	var mm = eval(ymd.substr(4, 2) + "- 1") ;
	var dd = ymd.substr(6, 2);

	var dt = new Date(yyyy, mm, eval(dd + '+' + addDay));

	yyyy = dt.getFullYear();
	mm = (dt.getMonth() + 1) < 10 ? "0" + (dt.getMonth() + 1) : (dt.getMonth() + 1) ;
	dd = dt.getDate() < 10 ? "0" + dt.getDate() : dt.getDate();

	return  "" + yyyy +"-"+ mm +"-"+ dd ;
}

//승인
function goApproval(){
	
	if(confirm("<spring:message code="clm.page.msg.manage.announce111" />")){
		viewHiddenProgress(true);
		
		var frm = document.frm;
		
		frm.prcs_depth.value				= "C02504"; //계약이행
		frm.depth_status.value				= "C02662"; //이행중
		frm.cntrt_chge_conf_cont.value		= frm.app_info.value;
		frm.prgrs_status.value				= "C04219"; //이행중
		frm.cntrt_chge_conf_yn.value		= "Y";
		frm.gubun.value						= "A";
		frm.target 							= "_self";		 
		frm.action 							= "<c:url value='/clm/manage/completion.do' />";
		frm.method.value 					= "modifyAutoRenewApproval";
		frm.submit();
	}
}
//반려
function goReject(){
	
	if(confirm("<spring:message code="clm.page.msg.manage.announce088" />")){
		viewHiddenProgress(true);
		
		var frm = document.frm;
	
		frm.prcs_depth.value				= "C02505"; //계약종료
		frm.depth_status.value				= "C02681"; //종료대상
		frm.cntrt_chge_conf_cont.value		= frm.reject_info.value;
		frm.prgrs_status.value				= "C04220"; //종료대상
		frm.cntrt_chge_conf_yn.value		= "N";
		frm.gubun.value						= "J";
		frm.target 							= "_self";		 
		frm.action 							= "<c:url value='/clm/manage/completion.do' />";
		frm.method.value 					= "modifyAutoRenewApproval";
		frm.submit();	
	}
}

//원본등록폼
function insertOrgForm(){
	viewHiddenProgress(true);
	var frm = document.frm;
	frm.page_gbn.value = "modify";
	frm.target = "_self";		 
	frm.action = "<c:url value='/clm/manage/registration.do' />";
	frm.method.value = "listContract";
	frm.submit();
}

//승인 및 반려
function insertOrg(temp){
	var frm = document.frm;
	var reMessage = "";
	
	//Sungwoo commmented 2014-11-10 not used on post conclusion registration
	/*
	if(frm.loac.value == ""){
		msgVal = "<spring:message code='las.page.field.contractmanager.consideration_inner_d.loac_rq' />";//준거법
		alert("<spring:message code='las.page.field.main.confirmValue' arguments='"+msgVal+"' />");
		return;
	}
	*/
	if(temp == 'A'){//승인

		if($("#org_hdovman_search_nm").val() == null || $("#org_hdovman_search_nm").val() == ''){
			//alert('원본제출자를 등록해주십시오');
			alert('<spring:message code="clm.page.msg.manage.announce214" />');
			$("#org_hdovman_search_nm").focus();
			return;
		}
	
		if($("#org_acptday").val() == null || $("#org_acptday").val() == ''){
			alert('<spring:message code="clm.page.msg.manage.announce124" />'); // 원본접수일을 등록해주십시오			
			$("#org_acptday").focus();
			return;
		}
		
		if($("#org_strg_pos").val() == null || $("#org_strg_pos").val() == ''){
			alert('<spring:message code="clm.page.msg.manage.announce123" />');
			//alert('원본보관위치를 등록해주십시오');
			$("#org_strg_pos").focus();
			return;
		}
		
		frm.agree_yn.value = "Y";
		reMessage = "<spring:message code="clm.page.msg.manage.announce122" />";
	}else if(temp =='J'){//반려
		frm.agree_yn.value = "N";
		
		//메일발송시 요청자에게 남기는 반려사유 
		frm.reject_info.value = $("#reject_info_textarea").val();
		
		reMessage = "<spring:message code="clm.page.msg.manage.announce088" />";
	
	}
	
	if(confirm(reMessage)){
		viewHiddenProgress(true);
		
		frm.target = "_self";		 
		frm.action = "<c:url value='/clm/manage/registration.do' />";
		frm.method.value = "modifyContract";
		frm.submit();
	}
}
//각 팝업 오픈시 target을 달리 주고 싶을 때
function PopUpWindowOpen3(surl, popupwidth, popupheight, bScroll, popName) {
	if( popupwidth  > window.screen.width )
		popupwidth = window.screen.width;
	if( popupheight > window.screen.height )
		popupheight = window.screen.height; 
		
	if( isNaN(parseInt(popupwidth)) ){
		Top  = (window.screen.availHeight - 600) / 2;
		Left = (window.screen.availWidth  - 800) / 2;
	} else {
		Top  = (window.screen.availHeight - popupheight)  / 2;
		Left = (window.screen.availWidth  - popupwidth) / 2;
	}

	if (Top < 0) Top = 0;
	if (Left < 0) Left = 0;
	
	popupwidth = parseInt(popupwidth) + 10 ;
	popupheight = parseInt(popupheight) + 29 ;
	
	
	var Feture = "toolbar=0, location=0, status=1, menubar=0, scrollbars=" + (bScroll ? "yes" : "no") + ", resizable=no, top="+Top+", left="+Left+", width="+popupwidth+", height="+popupheight;
	PopUpWindow = window.open(surl, popName , Feture);

	PopUpWindow.focus();
	
} 
/*
 * 임직원조회 팝업
 */
function searchEmployee() {
	
	var frm 		= document.frm;
	
	var srchValue 	= "";
	var obj 		= new Object();
	obj = frm.org_hdovman_search_nm;
	
	srchValue = comTrim(obj.value);
	frm.target = "PopUpEmployee";
    frm.srch_user_cntnt_type.value = "userName"; //이름으로 검색  - 보통 이름으로 검색함
    frm.action = "<c:url value='/secfw/esbOrg.do' />";
    frm.method.value = "listEsbEmployee";
    frm.srch_user_cntnt.value = srchValue;
    if (srchValue == "" || srchValue.length < 4) { //최소 2자리 이상 입력받게 한다.
        alert('<spring:message code='secfw.msg.error.nameMinByte' />');
        obj.focus();
    } else {
    	PopUpWindowOpen3('', 800, 450, true,"PopUpEmployee");
    	frm.submit();
    }    	
} 

/**
* 임직원 조회정보 결과 setting
*/
function setUserInfos(obj) {
	var frm = document.frm;
	frm.org_hdovman_id.value = obj.epid;
    frm.org_hdovman_nm.value = obj.cn;
    frm.org_hdovman_search_nm.value = obj.cn;
    frm.org_hdov_dept_nm.value = obj.department;
    frm.org_hdovman_jikgup_nm.value = obj.title;

}
//반려창 띄우기
function openProcessInfoLayer(seq){
	
	if(seq == '1'){
		$("#div_rej_info").css("top", $("#reject_btn").offset().top +25);
		$("#div_rej_info").css("left", $("#reject_btn").offset().left - 1200 );
	}else{
		$("#div_rej_info").css("top", $("#reject_btn_b").offset().top -400);
		$("#div_rej_info").css("left", $("#reject_btn_b").offset().left - 1200 );
	}
	$("#div_rej_info").show();
}

/**
* 필수 항목 팝업 처리 : 20140502 김재원 
*/
function checkPagePup(){
	
	var frm = document.frm;
	
	//상태가 최종본이고, 검토중일 경우에만 입력창이 작동 해야 함.
//	var final_status = "";
//	var cnsd_status = "";
//	if("<c:out value='${lomRq.plndbn_req_yn}' />" == "Y" && "" == "<c:out value='${lomDcd.cnsd_status}' />"){
		PopUpWindowOpen2("/clm/review/consideration.do?method=actionCheckItem&menu_id=20130319152827188_0000000335&cntrt_id="+frm.cntrt_id.value, 800, 600, false, "CheckItemWindows");
//	} else {
		
//	}
		
}

function checkListAppend(){
	    
    var options = {   
			url: "<c:url value='/clm/review/consideration.do?method=listCheckListReal' />",
			type: "post",
			dataType: "json",
			success: function(responseText, statusText) {
				if(responseText.returnCnt != 0) {
					var html2 = "";
					var i = 0;
					html2 = "<div ='requedCheckList'>"
					       + "<table id='tab_contents_check_list' cellspacing='0' cellpadding='0' border='0' class='table-style01' >"
						   +"<colgroup>"
						   +"<col width='10%' />"
						   +"<col width='20%' />"
						   +"<col width='80%' />"
						   +"</colgroup>"     
					
					     + "<tr>"
				         + "<th class='tC'>No</th>"
				         + "<th class='tC'><spring:message code='las.page.field.hqrequest.page12' /></th>"
			             + "<th class='tC'><spring:message code='las.page.field.hqrequest.page13' /></th>"
				         + "</tr>";
					$.each(responseText, function(entryIndex, entry) {
						
						i ++;
						
						if(entry > '0'){
					         html2 += "<tr>"
					              + "<td class='tC'>"+i+"</td>"
						          + "<td>"+entry['CD_NM']+"</td>"
						          + "<td>"+entry['REMARK']+"</td>"
					              + "</tr>";
					              
						} else {
							alert("<spring:message code="clm.page.msg.manage.announce157" />");
						}									

					}
					);	
					html2 += "</table> </div>";
					$("#requedCheckList").html(html2);	
				}	 
				
				frm.openCheckList.value = "ok";
				
			}
	};		
	$("#frm").ajaxSubmit(options);    
}

function setCheckList(){
	
	var html = "";
	
	html = "<div ='requedCheckList'>"
	       + "<table id='tab_contents_check_list' cellspacing='0' cellpadding='0' border='0' class='table-style01' >"
		   +"<colgroup>"
		   +"<col width='10%' />"
		   +"<col width='20%' />"
		   +"<col width='80%' />"
		   +"</colgroup>"     
	
	     + "<tr>"
         + "<th class='tC'>No</th>"
         + "<th class='tC'><spring:message code='las.page.field.hqrequest.page12' /></th>"
         + "<th class='tC'><spring:message code='las.page.field.hqrequest.page13' /></th>"
         + "</tr>"

  <c:choose>
    <c:when test='${0 < chekItemListSize }'>
        <c:forEach var="chekList" items="${chekItemList }">
	        + "<tr>"
	        + "<td class='tC'><c:out value="${chekList.no }" escapeXml="false"/></td>"
	        + "    <td><c:out value="${chekList.cd_nm }" escapeXml="false"/></td>"
	        + "    <td><c:out value="${chekList.remark }" escapeXml="false"/></td>"
	        + "</tr>"
        </c:forEach>
    </c:when>
    <c:otherwise>
         + "<tr>"
         + "<td class='tC' colspan='3'>No Reason</td>"
         + "</tr>"
    </c:otherwise>
</c:choose>

         + "</table> </div>";
	
 $("#requedCheckList").html(html);	
 
 <c:choose>
     <c:when test="${'T030705' eq lomRq.cnsl_mid_cd3 || 'T030706' eq lomRq.cnsl_mid_cd3}">
         <c:if test="${ 0 < chekItemListSize }">
             frm.openCheckList.value = "ok";
         </c:if>
     </c:when>
     <c:otherwise>
         frm.openCheckList.value = "ok";
     </c:otherwise>
 </c:choose>
 
}
</script>
</head>
<body>

<div class='divModal' id='div_rej_info' style="display:none">
	<div class='inWrap'>
		<div class='header'>
			<h1><spring:message code="clm.page.msg.manage.opiOfReturn" /><span class="close" onclick="divHidden(div_rej_info)" title="close"></span></h1>
		</div>
		<div class='conWrap'>
			<textarea name="reject_info_textarea" id="reject_info_textarea" class="text_full" cols="as" rows="6" style=' height:200px'></textarea>
			<!-- Button -->
			<div class="btnWrap">
				<span class="btn_all_b"><span class="save"></span><a href="javascript:insertOrg('J');"><spring:message code="clm.page.msg.common.return" /></a></span>
				<span class="btn_all_b"><span class="close"></span><a href="javascript:divHidden(div_rej_info);"><spring:message code="clm.page.msg.common.close" /></a></span>
			</div>
			<!-- // Button -->
		</div>
	</div>
</div>
<div id="wrap">
	<!-- container -->
	<div id="container">
		<!-- location -->
		<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" /><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //location -->
		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.msg.manage.aftConclRegAppr" /></h3>
		</div>
		<!-- //title -->
		<!-- content -->
		<div id="content">
			<div id="content_in">
				<!-- **************************** Form Setting ****************************	-->
				<form:form name="frm" id='frm' method="post" autocomplete="off">
					<!-- required Form -->
					<input type="hidden" name="method"   value="" />
					<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
					<!-- key hidden Form -->
					<input type="hidden" name="req_title"	id="req_title"   value="<c:out value='${contractReqLom.req_title}' />" />
					<input type="hidden" name="req_dt"	id="req_dt"   value="<c:out value='${contractReqLom.req_dt}' />" />
					<input type="hidden" name="cnsdreq_id"	id="cnsdreq_id"   value="<c:out value='${command.cnsdreq_id}'/>" />
					<input type="hidden" name="cntrt_id"	id="cntrt_id"     value="<c:out value='${command.cntrt_id}'/>" />
					<input type="hidden" name="reqman_id"	id="reqman_id"     value="<c:out value='${command.reqman_id}'/>" />
					<input type="hidden" name="agree_yn"   value="" />
					
					<!-- 반려 메세지 -->
					<input type="hidden" name="reject_info"	id="reject_info" value="" />
					<!-- Attach confg -->
					<input type="hidden" name="fileInfos"   value="" /> <!-- 저장될 파일 정보 -->
					<input type="hidden" name="fileInfos2"  value="" /> <!-- 두번째첨부파일정보 -->
					<input type="hidden" name="fileInfos3"  value="" /> <!-- 두번째첨부파일정보 -->
					<input type="hidden" name="fileInfos4"  value="" /> <!-- 체결본 사본 -->
					<input type="hidden" name="fileInfos5"  value="" /> <!-- 단가내역 -->
					<input type="hidden" name="fileInfos10"  value="" /> <!-- 종료관리 첨부파일정보 -->
				
					<input type="hidden" name="fileInfoName"    value="" /> <!-- 첨부파일 정보가 리턴될 객체명 -->
					<input type="hidden" name="fileFrameName"    value="" /> <!-- 첨부파일 화면 iFrame 명 -->
				
					<input type="hidden" name="file_bigclsfcn"  value="" /> <!-- 대분류 -->
					<input type="hidden" name="file_midclsfcn"  value="" /> <!-- 중분류 -->
					<input type="hidden" name="file_smlclsfcn"  value="" /> <!-- 소분류 -->
					<input type="hidden" name="multiYn"  value="Y" /> <!-- 멀티여부 default:Y-->
					<input type="hidden" name="ref_key"     value="" /> <!-- 키값 -->
					<input type="hidden" name="view_gbn"    value="download" /> <!-- 첨부파일 화면 -->
					<!-- //Attach confg -->
					<!-- 계약상세관련 -->
					<input type="hidden" name="cntrt_respman_id"	id="cntrt_respman_id"   value="<c:out value='${contractMstLom.cntrt_respman_id}'/>" />
					<input type="hidden" name="cntrt_resp_info"	 value="<c:out value='${contractMstLom.cntrt_respman_nm}'/>/<c:out value='${contractMstLom.cntrt_respman_jikgup_nm}'/>/<c:out value='${contractMstLom.cntrt_resp_dept_nm}'/>" />
					<input type="hidden" name="cntrt_nm"	 value="<c:out value='${contractReqLom.cntrt_nm}' />" />
					<!-- //계약상세관련 -->
					<!-- forward page -->
					<input type="hidden" name="targetMenuId" />
					<input type="hidden" name="selected_menu_id" />
					<input type="hidden" name="selected_menu_detail_id" />
					<input type="hidden" name="set_init_url" />
					<input type="hidden" name="page_gbn" />
					<input type="hidden" name="srch_user_cntnt_type" value=""/>
					<input type="hidden" name="srch_user_cntnt" value=""/>
					<!--// forward page -->
					
					<!-- //html Contents -->
					<input type="hidden" name="contents"		id="contents"     value="" />
					
					<!-- 리스트 검색 조건시작  -->
					<input type="hidden" name="list_mode" value="<c:out value='${command.list_mode}'/>"/>
					<input type="hidden" name="srch_resp_dept" value="<c:out value='${command.srch_resp_dept}' />"/>				<!-- 담당부서코드 -->
					<input type="hidden" name="srch_oppnt_cd" value="<c:out value='${command.srch_oppnt_cd}' />"/>   				<!-- 계약상대방코드 -->
					<input type="hidden" name="srch_review_title" value="<c:out value='${command.srch_review_title}'/>" />		<!-- 의뢰명 -->
					<input type="hidden" name="srch_reqman_nm" value="<c:out value='${command.srch_reqman_nm}'/>" />				<!-- 의뢰자 -->
					<input type="hidden" name="srch_start_reqday" value="<c:out value='${command.srch_start_reqday}' />" />		<!-- 의뢰 시작일 -->
					<input type="hidden" name="srch_end_reqday" value="<c:out value='${command.srch_end_reqday}' />" />			<!-- 의뢰 종료일 -->
					<input type="hidden" name="srch_start_cnlsnday" value="<c:out value='${command.srch_start_cnlsnday}'/>" />	<!-- 계약 시작일 -->
					<input type="hidden" name="srch_end_cnlsnday" value="<c:out value='${command.srch_end_cnlsnday}' />" />		<!-- 계약 종료일 -->
					<input type="hidden" name="srch_resp_dept_nm" value="<c:out value='${command.srch_resp_dept_nm}' />" />		<!-- 담당부서명 -->
					<input type="hidden" name="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}' />" />			<!-- 담당자명 -->
					<input type="hidden" name="srch_biz_clsfcn" value="<c:out value='${command.srch_biz_clsfcn}' />" />			<!-- 계약단계 -->
					<input type="hidden" name="srch_cnsdman_nm" value="<c:out value='${command.srch_cnsdman_nm}' />" />			<!-- 검토자 -->
					<input type="hidden" name="srch_oppnt_nm" value="<c:out value='${command.srch_oppnt_nm}'/>" />				<!-- 계약상대방 -->
					<input type="hidden" name="srch_state" value="<c:out value='${command.srch_state}' />" />						<!-- 상태 -->
					<input type="hidden" name="srch_cnclsnpurps_bigclsfcn" value="<c:out value='${command.srch_cnclsnpurps_bigclsfcn}' />" />	<!-- 체결목적-->
					<input type="hidden" name="ismycontract" id="ismycontract" value="<c:out value='${command.ismycontract}'/>"/>
					<input type="hidden" name="srch_step" value="<c:out value='${command.srch_step}' />" />						<!-- 상태 -->
					<input type="hidden" name="mis_id" />
					
					<div class="content-step t_titBtn">
						<ol>
							<li><img src="<%=IMAGE %>/common/step01.gif"	 alt="<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" />" /></li>
							<li><img src="<%=IMAGE %>/common/step02.gif"     alt="<spring:message code="clm.page.msg.manage.step02" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step02" htmlEscape="true" />" /></li>
							<li class='step_on'>
								<img src="<%=IMAGE %>/common/step03_on.gif"  alt="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" />
								<img src="<%=IMAGE %>/common/step_arr01.gif" alt="Next" title="Next" />
								<div class='step_link'><a href="javascript:open_window('win', '../../step03<%=langCd.equals("ko")?"":"_"+langCd %>.html', 0, 0, 696, 588, 0, 0, 0, 0, 0)" ><img src="<%=IMAGE %>/common/step_q.gif" alt="<spring:message code="clm.page.msg.manage.procStep"/>" title="<spring:message code="clm.page.msg.manage.procStep"/>" /></a></div>
							</li>
							<li><img src="<%=IMAGE %>/common/step04.gif"     alt="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" /></li>
							<li><img src="<%=IMAGE %>/common/step05.gif"     alt="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" /></li>
						</ol>
					</div>
					<div style='width:100%; margin-bottom:7px; overflow:hidden'>
						<!-- 계약정보 -->
						<div class="title_basic" style='width:100%; float:left'>
							<h4><spring:message code="clm.page.msg.manage.contInfo" /></h4>
						</div>
						
						<!-- button -->
						<div class="btn_wrap_t02 fR" style='margin-top:-20px'>
							<c:if test="${command.page_gbn != 'modify' and contractMstLom.depth_status eq 'C02636'}">
								<span class="btn_all_w"><span class="sangsin"></span><a href="javascript:insertOrgForm();"><spring:message code="clm.page.msg.manage.approval" /></a></span>
								<span class="btn_all_w" id="reject_btn"><span class="reject"></span><a href="javascript:openProcessInfoLayer('1');"><spring:message code="clm.page.msg.common.return" /></a></span>
							</c:if>
							<c:if test="${command.page_gbn eq 'modify' and contractMstLom.depth_status eq 'C02636'}">
								<span class="btn_all_w"><span class="tsave"></span><a href="javascript:insertOrg('A');"><spring:message code="clm.page.msg.common.save" /></a></span>
							</c:if>
							<span class="btn_all_w"><span class="list"></span><a href="javascript:listManageRegistration();"><spring:message code="clm.page.msg.manage.list" /></a></span>
						</div>
						<!-- //button -->
					</div>		
					
					<div class='title_basic3'><spring:message code="clm.page.msg.manage.bscInf" /></div>
					<table class="table-style01">
						<colgroup>
							<col width="12%" />
							<col width="22%" />
							<col width="12%" />
							<col width="21%" />
							<col width="12%" />
							<col width="21%" />
						</colgroup>
						<tr>
							<th><spring:message code="clm.page.msg.manage.reqName" /></th>
						    <td colspan="5"><c:out value="${contractReqLom.req_title}" /></td>
						</tr>
						<tr>
							<th><spring:message code="clm.page.msg.manage.contName" /></th>
							<td colspan='3'><c:out value="${contractReqLom.cntrt_nm}" /></td>
							<th><spring:message code="clm.page.msg.manage.contId" /></th>
							<td><c:out value="${contractMstLom.cntrt_no}" /></td>
						</tr>
						<tr>
							<th><spring:message code="clm.page.msg.manage.reqPerson" /></th>
							<td><c:out value="${contractReqLom.reqman_nm}" />/<c:out value="${contractReqLom.reqman_jikgup_nm}" />/<c:out value="${contractReqLom.req_dept_nm}" /></td>
							<th><spring:message code="clm.page.msg.manage.chrgPerson" /></th>
							<td colspan="3"><c:out value="${contractReqLom.cntrt_respman_nm}" />/<c:out value="${contractMstLom.cntrt_respman_jikgup_nm}" />/<c:out value="${contractMstLom.cntrt_resp_dept_nm}" /></td>
						</tr>
						<tr>
							<th><spring:message code="clm.page.msg.common.otherParty" /></th>
							<td><a href="javascript:customerPop2('<c:out value="${contractMstLom.cntrt_oppnt_cd}" />', '<c:out value="${contractMstLom.cntrt_oppnt_cd}" />');"><c:out value="${contractMstLom.cntrt_oppnt_nm}" /></a></td>
							<th><spring:message code="clm.page.field.customer.registerNo" /></th>
							<td colspan='3'><c:out value="${contractMstLom.cntrt_oppnt_ceo}" /></td>
<%-- 							<th><spring:message code="clm.page.field.customer.contractRequired" /></th> --%>
<%-- 							<td><c:out value="${contractMstLom.cntrt_oppnt_type_nm}" /></td> --%>
						</tr>
						<tr>
					        <th><spring:message code="clm.page.msg.manage.top30Cus" /></th>
					        <td><c:out value="${contractMstLom.top_30_cus_nm}"  escapeXml="false"/></td>
					        <th><spring:message code="clm.page.msg.manage.relatedPrd" /></th>
							<td><c:out value="${contractMstLom.related_products_nm}"  escapeXml="false"/></td>
							<th><spring:message code="clm.page.msg.manage.srcContDraft" /></th>
							<td><c:out value="${contractMstLom.cont_draft_nm}" /></td>
					    </tr>
						<tr>
							<th><spring:message code="clm.page.msg.manage.contItm" /></th>
							<td><c:out value="${contractMstLom.cntrt_trgt_nm}" /></td>
							<th><spring:message code="clm.page.msg.manage.contItmDtl" /></th>
							<td colspan="3"><c:out value="${contractMstLom.cntrt_trgt_det}" /></td>
						</tr>
						<tr>
							<th><spring:message code="clm.page.msg.manage.contType" /></th>
							<td colspan="5"><c:out value='${contractMstLom.biz_clsfcn_nm}'/>/<c:out value='${contractMstLom.depth_clsfcn_nm}'/>/<c:out value='${contractMstLom.cnclsnpurps_bigclsfcn_nm}'/>/<c:out value='${contractMstLom.cnclsnpurps_midclsfcn_nm}'/></td>
						</tr>
						<tr>
							<th><spring:message code="clm.page.msg.manage.contPeriod" /></th>
							<td><c:out value="${contractMstLom.cntrtperiod_startday}" /> ~ <c:out value="${contractMstLom.cntrtperiod_endday}" /></td>
							<th><spring:message code="clm.page.field.contract.consultation.detail.autorenew" /></th>
							<td>
							<c:if test="${contractMstLom.auto_rnew_yn=='Y'}">Yes</c:if>
							<c:if test="${contractMstLom.auto_rnew_yn=='N'}">No</c:if>
							</td>
							<th><spring:message code="clm.page.msg.manage.sendRcvDiv" /></th>
							<td><c:out value="${contractMstLom.payment_gbn_nm}"  /></td>
						</tr>
						<tr>
							<th><spring:message code="clm.page.msg.manage.contAmt" /></th>
							<td colspan='3'>
							<input type="text" class="text" style="border:0px solid #fff;" value="<c:out value="${contractMstLom.cntrt_amt}" />" />
							<c:if test="${!empty contractMstLom.cntrt_untprc_expl}"><input type='checkbox' class='checkbox' checked disabled> <spring:message code="clm.page.msg.manage.conclSingleAmt" /></c:if>
							 </td>
							<th><spring:message code="clm.page.msg.manage.curr" /></th>
							<td><c:out value="${contractMstLom.crrncy_unit}" /></td>
						</tr>
						<c:if test="${!empty contractMstLom.cntrt_untprc_expl}">
						<tr>
							<th><spring:message code="clm.page.msg.manage.contUnitPriceSum" /></th>
							<td colspan='5'><c:out value="${contractMstLom.cntrt_untprc_expl}" escapeXml="false"/></td>
						</tr>
						</c:if>
						<tr>
							<th><spring:message code="clm.page.msg.manage.bg" /></th>
							<td colspan="5"><c:out value="${contractMstLom.pshdbkgrnd_purps}" escapeXml="false" />
							</td>
						</tr>
						<tr>
							<th><spring:message code="clm.page.msg.manage.etcMain" /></th>
							<td colspan="5">
							<c:out value="${contractMstLom.etc_main_cont}" escapeXml="false" />
							<c:if test="${!empty contractMstLom.if_sys_cd}"> [<c:out value="${contractMstLom.if_sys_cd}" escapeXml="false" />]</c:if>
							</td>
						</tr>
						
						
					<!-- 최종본에 입력 하는 내용 추가 부분 시작-->
					<c:if test="${command.page_gbn eq 'modify' and contractMstLom.depth_status eq 'C02636'}">								
					<tr>
						<th><spring:message code="clm.page.field.contract.consultation.detail.autorenew" /></th><!-- 자동연장여부 -->
						<td colspan="5"><input type="radio" name="auto_rnew_yn"  value="Y"  />Yes
						<input type="radio" name="auto_rnew_yn"  value="N" checked />No
						</td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.manage.secretPeriod" /></th><!-- 비밀유지기간 -->
						<td id="td_secret_keepperiod" colspan="5">
						<input type="text" id="secret_keepperiod" name="secret_keepperiod" class="text_full" onkeyup="f_chk_byte(this,64)" maxLength="64" value="" alt="<spring:message code='las.page.field.contractmanager.consideration_inner_d.secret_keepperiod_rq' />">
						</td>
					</tr>					
					<tr style="border-top:0px;">
						<th style="border-top:0px;"><spring:message code="las.page.field.contractmanager.consideration_inner_d.oblgt_lmt" /></th><!-- 배상책임한도 -->
						<td colspan="5" id="tr_oblgt_lmt">
							<textarea name="oblgt_lmt" id="oblgt_lmt" cols="30" rows="3" class="text_area_full" onkeyup="f_chk_byte(this,1000)" maxLength="1000" alt="<spring:message code='las.page.field.contractmanager.consideration_inner_d.oblgt_lmt' />"></textarea>
						</td>
					</tr>							
					<tr>
						<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.loac_rq" /><!--  <span class='astro'>* --></span></th><!-- * 준거법 -->
						<td id="td_loac">
							<select name="loac" id="loac" style="width:110px;" required alt="<spring:message code='las.page.field.contractmanager.consideration_inner_d.loac_rq' />" onChange="javascript:chgLoac(this.value);">
								<c:out value="RSEA002" escapeXml="false"/>						  
							</select>
						</td>
						<th id="th_loac_etc"><spring:message code="las.page.field.contractmanager.consideration_inner_d.loac_etc" /></th><!-- 준거법상세 -->
						<td id="td_loac_etc" colspan="3"  ><input type="text" name="loac_etc" id="loac_etc" class="text_full" onkeyup="f_chk_byte(this,500)" maxLength='500' value="" alt="<spring:message code='las.page.field.contractmanager.consideration_inner_d.loac_etc' />" />
						</td>
					</tr>						
					<tr>
						<th rowspan="2"  class='rightline'><spring:message code="las.page.field.contractmanager.consideration_inner_d.dspt_resolt_rq" /></th><!-- * 분쟁해결방법 -->
						<td id="td_dspt_resolt_mthd"  colspan="5">
						<select name="dspt_resolt_mthd" id="dspt_resolt_mthd" style="width:110px;" alt="<spring:message code='las.page.field.contractmanager.consideration_inner_d.dspt_resolt_rq' />" >
						</select> <span class="btn_all_b" onclick="javascript:addDspt();"><span class="add"></span> <a><spring:message code="las.page.field.contractManager.add"/></a></span> 
						</td>							 	
					</tr>
					<tr>
						<td id="td_dspt_resolt_mthd_det" colspan="5">
							<textarea name="dspt_resolt_mthd_det" id="dspt_resolt_mthd_det" cols="30" rows="3" class="text_area_full" onkeyup="f_chk_byte(this,1000)" maxLength='1000' alt="<spring:message code='las.page.field.contractmanager.consideration_inner_d.dspt_resolt_det' />"></textarea>
						</td>
					</tr>
					</c:if>
					<!-- 최종본에 입력 하는 내용 추가 부분 종료 -->
						
						
					</table>	
					<div class='title_basic3'><spring:message code="clm.page.msg.manage.preApprInf" /></div>
					<table class="table-style01">
						<colgroup>
							<col width="12%" />
							<col width="22%" />
							<col width="12%" />
							<col width="21%" />
							<col width="12%" />
							<col width="21%" />
						</colgroup>
						<tr>
							<th><spring:message code="clm.page.msg.manage.apprType" /></th>
							<td colspan='3'><c:out value="${contractMstLom.bfhdcstn_apbt_mthd_nm}" /></td>
							<th><spring:message code="clm.page.msg.manage.apprDate" /></th>
							<fmt:parseDate value="${contractMstLom.bfhdcstn_apbtday}" var="bfhdcstn_apbtday" pattern="yyyymmdd"/>
							<td><fmt:formatDate value="${bfhdcstn_apbtday}" pattern="yyyy-mm-dd"/></td>
						</tr>
						<tr>
							<th><spring:message code="clm.page.msg.manage.proposer" /></th>
							<td colspan='3'><c:out value="${contractMstLom.bfhdcstn_mtnman_nm}" />/<c:out value="${contractMstLom.bfhdcstn_mtnman_jikgup_nm}" />/<c:out value="${contractMstLom.bfhdcstn_mtn_dept_nm}" /> </td>
							<th><spring:message code="clm.page.msg.manage.apprPer" /></th>
							<td><c:out value="${contractMstLom.bfhdcstn_apbtman_nm}" />/<c:out value="${contractMstLom.bfhdcstn_apbtman_jikgup_nm}" />/<c:out value="${contractMstLom.bfhdcstn_apbt_dept_nm}" /></td>
						</tr>
						<tr>
							<th width="100"><spring:message code='clm.page.field.contract.consult.approval.file' /></th>
							<td class="last-td" colspan="5">
								<iframe src="<c:url value='/clm/blank.do' />" id="fileList33" name="fileList33" frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true" ></iframe>
							</td>
						</tr>
					</table>
						
					<div class='title_basic3'><spring:message code="clm.page.msg.manage.relContInf" /> </div>
					<table class="table-style01" id="contrack-relationView" >
						<colgroup>
							<col width="12%" />
							<col width="50%" />
							<col width="10%" />
							<col/>	
						</colgroup>
						<tr>
							<th><spring:message code="clm.page.msg.manage.relation" /></th>
							<th><spring:message code="clm.page.msg.manage.relCont" /></th>
							<th><spring:message code="clm.page.msg.manage.define" /></th>
							<th><spring:message code="clm.page.msg.manage.relDetail" /></th>
						</tr>
							<c:choose>
							<c:when test ="${!empty contractRelationLom}">
								<c:forEach var="list" items="${contractRelationLom}" varStatus="i">
									<tr>
										<td><c:out value='${list.rel_type_nm}'/></td>
										<td><c:out value='${list.relation_cntrt_nm}'/></a>
										<td class='tC'><c:out value='${list.rel_defn}'/></td>
										<td><c:out value='${list.expl}'/></td>
									</tr>		
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr><td colspan="4" align="center">No Data Found</td></tr>
							</c:otherwise>
							</c:choose>	
					</table>
					<!-- // 계약정보 -->
								
					<!-- 체결정보 -->
					<div class="title_basic" style='width:100%; margin:10px 0'>
						<h4><spring:message code="clm.page.msg.manage.conclInf" /></h4>
					</div>
					
					<div class='title_basic3'><spring:message code="clm.page.msg.manage.bscInf" /></div>
					<table class="table-style01">
						<colgroup>
							<col width="12%" />
							<col width="22%" />
							<col width="12%" />
							<col width="54%" />
						</colgroup>
						<tr>
							<th><spring:message code="clm.page.msg.manage.conclConf" /></th>
							<td>
							<c:choose>
							<c:when test="${contractMstLom.cntrt_cnclsn_yn=='Y'}">Yes</c:when>
							<c:otherwise>No</c:otherwise>
							</c:choose>	
							</td>
							<th><spring:message code="clm.page.msg.manage.contConclDt" /></th>
							<td><c:out value='${contractMstLom.cntrt_cnclsnday}'/></td>
						</tr>
						<tr>
							<th><spring:message code="clm.page.msg.manage.sign" /></th>
							<td><c:out value='${contractMstLom.seal_mthd_nm}'/></td>
							<c:choose>
							<c:when test="${contractMstLom.seal_mthd eq 'C02102'}">
							<th><spring:message code="clm.page.msg.manage.signChrgMan" /></th>
							<td><c:out value='${contractMstLom.sign_plndman_nm}'/>/<c:out value='${contractMstLom.sign_plndman_jikgup_nm}'/>/<c:out value='${contractMstLom.sign_plnd_dept_nm}'/></td>
							</c:when>
							<c:otherwise>
							<th><spring:message code="clm.page.msg.manage.signManager" /></th>
							<td><c:out value='${contractMstLom.seal_ffmtman_nm}'/>/<c:out value='${contractMstLom.seal_ffmtman_jikgup_nm}'/>/<c:out value='${contractMstLom.seal_ffmt_dept_nm}'/></td>
							</c:otherwise>
							</c:choose>	
						</tr>
					</table>
					
					<div class='title_basic3'><spring:message code="clm.page.msg.manage.copyVerInf" /></div>
					<table class="table-style01">
						<colgroup>
							<col width="12%" />
							<col width="22%" />
							<col width="12%" />
							<col width="54%" />
						</colgroup>
						<tr>
							<th><spring:message code="clm.page.msg.manage.copyVerRegPer" /></th>
							<td><c:out value='${contractMstLom.cpy_regman_nm}'/>/<c:out value='${contractMstLom.cpy_regman_jikgup_nm}'/>/<c:out value='${contractMstLom.cpy_reg_dept_nm}'/></td>
							<th><spring:message code="clm.page.msg.manage.copyVerRegDate" /></th>
							<td><c:out value='${contractMstLom.cpy_regday}'/></td>
						</tr>
						<tr>
							<th><spring:message code="clm.page.msg.manage.conclCopy" /></th>
							<td colspan="3">
								<!-- Sungwoo replacement height size 2014-07-03-->
								<iframe src="<c:url value='/clm/blank.do' />" id="fileList4" name="fileList4" frameborder="0" class='addfile_iframe_d'  width="100%" height="100%" scrolling="auto" allowTransparency="true"></iframe>
							</td>
						</tr>
					</table>
								
					<div class='title_basic3'><spring:message code="clm.page.msg.manage.orgInf" /></div>
					<table class="table-style01">
						<colgroup>
						<c:choose>
								<c:when test="${command.page_gbn eq 'modify' and contractMstLom.depth_status eq 'C02636'}">
									<col width="12%" />
									<col width="55%" />
									<col width="12%" />
									<col width="21%" />
								</c:when>
							<c:otherwise>
								<col width="12%" />
								<col width="22%" />
								<col width="12%" />
								<col width="54%" />
							</c:otherwise>
						</c:choose>
						</colgroup>
						<tr>
							<th><spring:message code="clm.page.msg.manage.orgSendPer" /></th>
							<td>
							<c:choose>
								<c:when test="${command.page_gbn eq 'modify' and contractMstLom.depth_status eq 'C02636'}">
									<input type="text" name="org_hdovman_search_nm" id="org_hdovman_search_nm" value="<c:out value='${contractMstLom.org_hdovman_nm}'/>" class="text_search" onKeyPress="if(event.keyCode==13) {searchEmployee();return false;}" /><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchEmployee();" class="cp" alt="search" />
									<input type="text" name="org_hdov_dept_nm" id="org_hdov_dept_nm" style="border: 0px;" value="<c:out value='${contractMstLom.org_hdov_dept_nm}'/>" readonly="readonly" />
									<input type="text" name="org_hdovman_jikgup_nm" id="org_hdovman_jikgup_nm" style="border: 0px" value="<c:out value='${contractMstLom.org_hdovman_jikgup_nm}'/>" readonly="readonly" />
									<input type="hidden" name="org_hdovman_nm" id="org_hdovman_nm" value="<c:out value='${contractMstLom.org_hdovman_nm}'/>" />
									<input type="hidden" name="org_hdovman_id" id="org_hdovman_id" value="<c:out value='${contractMstLom.org_hdovman_id}'/>" />
								</c:when>
							<c:otherwise>
								<c:out value='${contractMstLom.org_hdovman_nm}'/>/<c:out value='${contractMstLom.org_hdovman_jikgup_nm}'/>/<c:out value='${contractMstLom.org_hdov_dept_nm}'/>
							</c:otherwise>
							</c:choose>
							</td>
							<th><spring:message code="clm.page.msg.manage.orgRcvDate" /></th>
							<td>
							<c:choose>
								<c:when test="${command.page_gbn eq 'modify' and contractMstLom.depth_status eq 'C02636'}">
									<script>initCal("org_acptday");</script>
									<input type="text" name="org_acptday" id="org_acptday" value="<%=DateUtil.formatDate(DateUtil.today(), "-")%>" class="text_calendar02" />
								</c:when>
								<c:otherwise>
									<c:out value='${contractMstLom.org_acptday}'/>
								</c:otherwise>
							</c:choose>
							</td>
						</tr>
						<tr>
							<th><spring:message code="las.page.field.contractManager.groupChief" /></th>
							<td><c:choose>
								<c:when test="${command.page_gbn eq 'modify' and empty contractMstLom.org_tkovman_nm}">
									<c:out value='${command.session_user_nm}'/>/<c:out value='${command.session_jikgup_nm}'/>/<c:out value='${command.session_dept_nm}'/>
								</c:when>
								<c:otherwise>
									<c:out value='${contractMstLom.org_tkovman_nm}'/>/<c:out value='${contractMstLom.org_tkovman_jikgup_nm}'/>/<c:out value='${contractMstLom.org_tkov_dept_nm}'/>
								</c:otherwise>
								</c:choose>
							</td>
							<th><spring:message code="clm.page.msg.manage.orgPlace" /></th>
							<td>
							<c:choose>
								<c:when test="${command.page_gbn eq 'modify' and contractMstLom.depth_status eq 'C02636'}">
									<input type="text" name="org_strg_pos" id="org_strg_pos" value="<c:out value='${contractMstLom.org_strg_pos}'/>" style="width: 100%" class="text_full" maxLength="100" />
								</c:when>
								<c:otherwise>
									<c:out value='${contractMstLom.org_strg_pos}'/>
								</c:otherwise>
							</c:choose>
							</td>
						</tr>
						<tr>
							<th><spring:message code="clm.page.msg.common.memo" /></th>
							<td colspan='3'>
							<c:choose>
								<c:when test="${command.page_gbn eq 'modify' and contractMstLom.depth_status eq 'C02636'}">
								<span id="org_acpt_dlay_cause">0</span>/<spring:message code="clm.page.msg.manage.stringLen1000" /><br>
								<textarea name="org_acpt_dlay_cause" id="org_acpt_dlay_cause" onkeyup="frmChkLenLang(this,1000,'org_acpt_dlay_cause','<%=langCd%>')" cols="10" rows="3" class="text_area_full" maxLength="1000" ><c:out value='${contractMstLom.org_acpt_dlay_cause}' /></textarea>
								</c:when>
								<c:otherwise>
									<!-- c:out value='${contractMstLom.org_acpt_dlay_cause}' / -->
									<%=str_org_acpt_dlay_cause%>
								</c:otherwise>
							</c:choose>
							</td>
						</tr>
					</table>
					<!-- // 계약상세내역 -->
					
					<!--  유형이 "T030705" == cont_type || "T030706" == cont_type 일 경우에만 나타나게 된다. 김재원 -->
					<c:if test="${ 'T030705' eq contractMstLom.cnclsnpurps_midclsfcn || 'T030706' eq contractMstLom.cnclsnpurps_midclsfcn }">
					<!-- 필수 항목 저장 내용 시작 김재원 -->
					<!--  Brice block by requeset of Sharon 2019-04-19
					<div id="tr_down100">
						<div class="title_basic">
							<h4><spring:message code="las.page.field.hqrequest.page09" /> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="up" onclick="tit_Toggle(this,'tr_down_check_list');" style='cursor:pointer;'/></h4>
						</div>
						<div class="textBox">
							<table>
								<colgroup>
									<col width="130px" />
									<col width="*" />
								</colgroup>
								<c:choose>
								    <c:when test="${ 'C02401' eq contractMstLom.cntrt_status  && 'C02502' eq contractMstLom.prcs_depth && 'C02632' eq contractMstLom.depth_status }">
								        <tr>
									        <td class="brLine"><span class="btn_all_red" onClick="javascript:checkPagePup();"><a>checklist</a></span></td>
									        <td class="pl20"><spring:message code="las.page.field.hqrequest.page10" /></td>
								        </tr>
								    </c:when>
								    <c:otherwise>
								        <tr>
									        <td class="brLine"><span class="btn_all_red_d"><a>checklist</a></span></td>
									        <td class="pl20"><spring:message code="las.page.field.hqrequest.page10" /></td>
								        </tr>
								    </c:otherwise>
								</c:choose>
								
							</table>
						</div>
						-->
						<!-- 필수 항목 No 사유 리스트 -->
						<!-- 
						<div id="tr_down_check_list">								
									<div id="requedCheckList"></div>						
						</div>
						-->
						<!--// 필수 항목 체크 리스트 -->
			<!-- 		</div>  -->
					</c:if>	
					
					<!-- 이력정보 -->
					<div id="contractHis-list"></div>
					<!-- //이력정보 -->	
					
					<!-- button -->
					<div class="t_titBtn">
						<div class="btn_wrap_c">
							<c:if test="${command.page_gbn != 'modify' and contractMstLom.depth_status eq 'C02636'}">
								<span class="btn_all_w"><span class="sangsin"></span><a href="javascript:insertOrgForm();"><spring:message code="clm.page.msg.manage.approval" /></a></span>
								<span class="btn_all_w" id="reject_btn_b"><span class="reject"></span><a href="javascript:openProcessInfoLayer('2');"><spring:message code="clm.page.msg.common.return" /></a></span>
							</c:if>
							<c:if test="${command.page_gbn eq 'modify' and contractMstLom.depth_status eq 'C02636'}">
								<span class="btn_all_w"><span class="tsave"></span><a href="javascript:insertOrg('A');"><spring:message code="clm.page.msg.common.save" /></a></span>
							</c:if>
							<span class="btn_all_w"><span class="list"></span><a href="javascript:listManageRegistration();"><spring:message code="clm.page.msg.manage.list" /></a></span>
						</div>
					</div>
					<!-- //button -->
				</form:form>
				<!-- //**************************** Form Setting ****************************-->
			</div>
			<!-- //content -->
		</div>
	</div>
</div>
</body>
<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- // footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</html>

