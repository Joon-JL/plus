<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.ArrayList" %>

<%@ include file= "/WEB-INF/jsp/common/common.jsp" %>
<%-- 
/**
 * 파  일  명 : RegExecution_l.jsp
 * 프로그램명 : 이행정보 조회 목록 - 체결 후 등록
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2012.02
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<LINK href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet"></LINK>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="/script/clms/common.js" type="text/javascript"></script>
<script language="javascript" src="/script/clms/clms_common.js" type="text/javascript"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/menu.js'/>"></script>
<script language="javascript"  >
$(document).ready(function(){
	$('#max_exec_num').val("<c:out value='${max_exec_num}'/>");
	detailExecution("<c:out value='${contractCommand.cnsdreq_id}'/>","<c:out value='${contractCommand.cntrt_id}'/>");
	//계약 tab
	$('#contractTab li').bind('click', function(){
		$('#contractTab li').removeClass('on');
		$(this).addClass('on');
		//이행관리 탭 활성화
		$('#executionTab li').removeClass('on');
		$('#executionTab li:eq(4)').addClass('on');
		//계약상세내역, 사전검토정보 diplay:none
		$('#contract-detail-content').attr("style","display:none");
		$('#contract-cnclsnInfo-content').attr("style","display:none");
		$('#contract-cnclsnIns-content').attr("style","display:none");
		$('#contract-relation-content').attr("style","display:none");
	});
	//이행 tab
	$('#executionTab li').bind('click', function(){
		
		$('#executionTab li').removeClass('on');
		$(this).addClass('on');
	});
	//계약상세내역 display를 위한 이벤트 처리...
	$('#executionTab li:eq(0)').bind('click', function(){
		$('#contract-detail-content').attr("style","display:");
		$('#contract-cnclsnInfo-content').attr("style","display:none");
		$('#contract-cnclsnIns-content').attr("style","display:none");
		$('#contract-relation-content').attr("style","display:none");
		$('#mainExecutionDetail-Content').attr("style","display:none");
	});
	//연관계약정보 display를 위한 이벤트 처리...cnclsnInfo
	$('#executionTab li:eq(1)').bind('click', function(){
		$('#contract-detail-content').attr("style","display:none");
		$('#contract-cnclsnInfo-content').attr("style","display:none");
		$('#contract-cnclsnIns-content').attr("style","display:none");
		$('#contract-relation-content').attr("style","display:");
		$('#mainExecutionDetail-Content').attr("style","display:none");
	});
	//주요이행사항 display를 위한 이벤트 처리
	$('#executionTab li:eq(2)').bind('click', function(){
		$('#contract-detail-content').attr("style","display:none");
		$('#contract-cnclsnInfo-content').attr("style","display:none");
		$('#contract-cnclsnIns-content').attr("style","display:none");
		$('#contract-relation-content').attr("style","display:none");
		$('#mainExecutionDetail-Content').attr("style","display:");
		$('#mainExecutionDetail-Content').attr("style","width:100%; clear:both; border-top:2px solid #3E74BE; _border-left:1px solid #fff; margin:0; table-layout:fixed;");
	});
});
function executionDetail() {

	var options = {
		url: "<c:url value='/clm/manage/execution.do?method=detailExecution&pageGbn=registration' />",
		type: "post",
		dataType: "html",
		success: function (data) {
			//계약상세내역 잘라내기 붙이기...
			$('#contract-detail').append($('#contract-detail-content'));
			$('#contract-cnclsnInfo').append($('#contract-cnclsnInfo-content'));
			$('#contract-cnclsnInfo').append($('#contract-cnclsnIns-content'));
			$('#contract-relation').append($('#contract-relation-content'));
			$('#contract-detail-content').attr("style","display:none");
			$('#contract-cnclsnInfo-content').attr("style","display:none");
			$('#contract-cnclsnIns-content').attr("style","display:none");
			$('#contract-relation-content').attr("style","display:none");
			
		}
	}
	$("#frm").ajaxSubmit(options);
}

/**
* 상세내역 조회
*/
function detailExecution(cnsdreq_id, cntrt_id){
	viewHiddenProgress(true);		
	var frm = document.frm;
	 //초기화 : 연계 시 이 부분을 주석처리...
	$('input[name=cnsdreq_id]').val("");
	$('input[name=cntrt_id]').val("");
    //설정
    $('#cnsdreq_id').val(cnsdreq_id);
    $('#cntrt_id').val(cntrt_id);
    var options = {
    		url: "<c:url value='/clm/manage/execution.do?method=detailContract&pageGbn=registration' />",
    		type: "post",
    		async: false,
    		dataType: "html",
    			
    		success: function(data){
    			$("#contract-view").html("");
    			$("#contract-view").html(data);
    			executionDetail();
    			contractHisList();
    			listContractRole();
    		}
    }
    $("#frm").ajaxSubmit(options);
    attachPage();
    viewHiddenProgress(false);
}

function goCompletion(cnsdreq_id){
	var frm = document.frm;
	var cntrt_id = $('#cntrt_id').val();
	var confirmMessage =  "<spring:message code='clm.msg.confirm.execution.gocompletion' />";
	
	 if(confirm(confirmMessage)){
		 var options = {
				url: "<c:url value='/clm/manage/execution.do?method=modifyContractStatus&pageGbn=registration' />",
		    	type: "post",
		    	async: false,
		    	dataType: "html",
		    	success: function(data){
		    		Menu.detail('frm', '_top', '20110802182454521_0000000036', '20110803091537445_0000000053',  
		    				'/clm/manage/completion.do?method=listContract&pageGbn=registration&cnsdreq_id='+cnsdreq_id+'&cntrt_id='+cntrt_id);
		    	}
		 }
		 $("#frm").ajaxSubmit(options);
	 }
}

/**
* 계약기본정보 조회
*/
function detailBaseExecution(cnsdreq_id, cntrt_id){
		
	var frm = document.frm;
	 //초기화 : 연계 시 이 부분을 주석처리...
	$('input[name=cnsdreq_id]').val("");
	$('input[name=cntrt_id]').val("");
	
    //설정
    $('#cnsdreq_id').val(cnsdreq_id);
    $('#cntrt_id').val(cntrt_id);
    
    var options = {
    		url: "<c:url value='/clm/manage/execution.do?method=detailContract&pageGbn=registration' />",
    		type: "post",
    		async: false,
    		dataType: "html",
    			
    		success: function(data){
    			$("#contract-view").html("");
    			$("#contract-view").html(data);
    			//계약상세내역 잘라내기 붙이기...
    			$('#contract-detail').html("");
    			$('#contract-cnclsnInfo').html("");
    			$('#contract-cnclsnInfo').html("");
    			$('#contract-relation').html("");
    			$('#contract-detail').append($('#contract-detail-content'));
    			$('#contract-cnclsnInfo').append($('#contract-cnclsnInfo-content'));
    			$('#contract-cnclsnInfo').append($('#contract-cnclsnIns-content'));
    			$('#contract-relation').append($('#contract-relation-content'));
    			$('#contract-detail-content').attr("style","display:none");
    			$('#contract-cnclsnInfo-content').attr("style","display:none");
    			$('#contract-cnclsnIns-content').attr("style","display:none");
    			$('#contract-relation-content').attr("style","display:none");
    			
				//이행관리 탭 활성화
				$('#executionTab li').removeClass('on');
				$('#executionTab li:eq(4)').addClass('on');
    		}
    }
    $("#frm").ajaxSubmit(options);
    
    attachPage();
}

/**
* 권한에따른 버튼제어
*/
function listContractRole(){
	//버튼제어
	$('#contract-btnList').html("");
	$('#contract-btnList').html($('#contract-btn-content'));
	//이행 버튼 제어
	if("<c:out value='${contractCommand.session_user_id}'/>"!=frm.reqman_id.value){
		$('#rowAddDel').empty();
		$('#rowAddDel3').empty();
	}
}

//첨부파일
function attachPage(){
	var frm = document.frm;
	
    //게약서파일       
	frm.target          = "fileList";
	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	frm.method.value    = "forwardClmsFilePage";
	frm.fileInfoName.value = "fileInfos";
	frm.fileFrameName.value = "fileList";
	
	frm.file_bigclsfcn.value = "F012";
	frm.file_midclsfcn.value = "F01202";
	frm.file_smlclsfcn.value = "F0120201";
	frm.ref_key.value = "<c:out value='${contractCommand.cntrt_id}'/>@<c:out value='${contractCommand.cnsdreq_id}'/>";
	getClmsFilePageCheck('frm');   
	
    //계약서- 첨부/별첨
    frm.target          = "fileList3";
	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	frm.method.value    = "forwardClmsFilePage";
	frm.fileInfoName.value = "fileInfos3";
	frm.fileFrameName.value = "fileList3";

	frm.file_bigclsfcn.value = "F012";
	frm.file_midclsfcn.value = "F01202";
	frm.file_smlclsfcn.value = "F0120208";
	frm.ref_key.value = "<c:out value='${contractCommand.cntrt_id}'/>@<c:out value='${contractCommand.cnsdreq_id}'/>";

	getClmsFilePageCheck('frm');
	
	//계약서 기타_체결본 파일
	frm.target          = "fileList2";
	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	frm.method.value    = "forwardClmsFilePage";
	frm.fileInfoName.value = "fileInfos2";
	frm.fileFrameName.value = "fileList2";

	frm.file_bigclsfcn.value = "F012";
	frm.file_midclsfcn.value = "F01202";
	frm.file_smlclsfcn.value = "F0120212";
	frm.ref_key.value = "<c:out value='${contractCommand.cntrt_id}'/>@<c:out value='${contractCommand.cnsdreq_id}'/>";

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
    frm.ref_key.value			= frm.cntrt_id.value;
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
* 목록
*/
function listManageExecution(){
	var frm = document.frm;
		
	frm.target = "_self";		 
	frm.action = "<c:url value='/clm/manage/consideration.do?pageGbn=registration' />";
	frm.method.value = "listManageConsideration";
	frm.submit();	
}	

function openChooseClient(){
    var frm = document.frm;
    PopUpWindowOpen('', "530", "470", true, "PopUpChooseClient");
    frm.action = "<c:url value='/clm/manage/chooseClient.do' />";
    frm.method.value="forwardChooseClientPopup";
    frm.target = "PopUpChooseClient";
    frm.submit();
 }

//관련자정보세팅...
function setListClientInfo(returnValue) {
    var arrReturn 	= returnValue.split("!@#$");
    var frm 		= document.frm;
    var innerHtml 	= "";	
    frm.authreq_client.value = returnValue;
    $('#id_trgtman_nm').html("");
    for(var i=0; i < arrReturn.length;i++) {
    	var arrInfo = arrReturn[i].split("|");
    	if((i != 0 && i != 1) && (i % 2 == 0)){
			innerHtml += "<br/>";
    	}
    	if(i != 0 && (i % 2 != 0)){
    		innerHtml += ",";
    	}
    	innerHtml += arrInfo[2] +"/"+arrInfo[3] + "/" + arrInfo[4] ;
    	$('#id_trgtman_nm').html(innerHtml);
    }
    frm.authreq_client.value = returnValue;
 }
 
/*
 * 팝업오픈-모달리스
 */
function PopUpWindowOpen(surl, popupwidth, popupheight, bScroll, popName) {
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
	
	
	var Feture = "toolbar=0, location=0, status=1, menubar=0, scrollbars=yes, resizable=yes, top="+Top+", left="+Left+", width="+popupwidth+", height="+popupheight;
	PopUpWindow = window.open(surl, popName , Feture);

	PopUpWindow.focus();
	
}

/**
 * 인쇄버튼 
 * 
 */
function goRdView() {
	 var frm 				= document.frm;
	 PopUpWindowOpen('', "1000", "600", true, "PopUpWindow");
	 frm.action = "<c:url value='/clm/manage/execution.do' />";
	 frm.method.value="forwardPrintPop";		 		 
	 frm.target = "PopUpWindow";
	 frm.submit();
}

function showExecutionPop(){
	var frm = document.frm;
	PopUpWindowOpen('', '1024','768',true,'popUpHistory2');
	frm.action	= "<c:url value='/clm/manage/completion.do' />";
	frm.method.value = "forwardCompletionHisPop";
	frm.target = "popUpHistory2";
	frm.submit();
}

/*
 * 승인이력 - [체결품의] 상세 팝업 띄우기
 */
function openHis(mis_id){
	var frm = document.frm;
	PopUpWindowOpen('', '1024','768',true,'popUpHistory');
	frm.action	= "<c:url value='/clm/manage/consultation.do' />";
	frm.method.value = "forwardHistoryPop";
	frm.mis_id.value = mis_id;
	frm.buGubn.value = "";
	frm.target = "popUpHistory";
	frm.submit();
}

/**
* page Action
*/
function forwardConsideration(){
	var frm = document.frm;
	frm.action = "<c:url value='/clm/manage/consideration.do' />";
	frm.method.value = "forwardModifyConsideration";
	frm.pageGbn.value = "registration";
	frm.status.value = "forwardModify";
	frm.target = "_self";

	frm.submit();
	
}

// 결재하기
function insertApproval() {
	var frm = document.frm;
	
	var options = {
	 		url: "<c:url value='/clm/manage/completion.do?method=makeApprovalHtmlDirect' />",
	 		type: "post",
	 		async: false,
	 		dataType: "json",
	 			
	 		success: function(responseText,returnMessage) {
				if(responseText.returnValue=="Y"){
					viewHiddenProgress(false);
					detailBaseContract(frm.cnsdreq_id.value,frm.cntrt_id.value);
				}else {
					viewHiddenProgress(false);
					alert(responseText.returnMessage);
				}
	 		}
	   }
	   $("#frm").ajaxSubmit(options);
}

</script>
</head>
<body>

<!-- 

**************************** Form Setting **************************** 
-->
<form:form name="frm" id='frm' method="post" autocomplete="off">
	<!-- required Form -->
	<input type="hidden" name="method"   value="">
	<input type="hidden" name="menu_id" value="<c:out value='${contractCommand.menu_id}'/>" />
	<!-- key hidden Form -->
	<input type="hidden" name="cnsdreq_id"	id="cnsdreq_id"   value="<c:out value='${contractCommand.cnsdreq_id}'/>" />
	<input type="hidden" name="cntrt_id"		id="cntrt_id"     value="<c:out value='${contractCommand.cntrt_id}'/>" />
	<input type="hidden" name="reqman_id"		id="reqman_id"     value="<c:out value='${contractCommand.reqman_id}'/>" />
	<!-- Attach confg -->
	<input type="hidden" name="fileInfos"   value="" /> <!-- 저장될 파일 정보 -->
    <input type="hidden" name="fileInfos2"  value="" /> <!-- 두번째첨부파일정보 -->
    <input type="hidden" name="fileInfos3"  value="" /> <!-- 두번째첨부파일정보 -->
    <input type="hidden" name="fileInfos4"  value="" /> <!-- 체결본 사본 -->
    <input type="hidden" name="fileInfos5"  value="" /> <!-- 단가내역 -->
    <input type="hidden" name="fileInfoName"    value="" /> <!-- 첨부파일 정보가 리턴될 객체명 -->
    <input type="hidden" name="fileFrameName"    value="" /> <!-- 첨부파일 화면 iFrame 명 -->
	<input type="hidden" name="file_bigclsfcn"  value="" /> <!-- 대분류 -->
	<input type="hidden" name="file_midclsfcn"  value="" /> <!-- 중분류 -->
	<input type="hidden" name="file_smlclsfcn"  value="" /> <!-- 소분류 -->
	<input type="hidden" name="ref_key"     value="" /> <!-- 키값 -->
	<input type="hidden" name="view_gbn"    value="download" /> <!-- 첨부파일 화면 -->
	<!-- //Attach confg -->
	<!-- forward page -->
	<input type="hidden" name="targetMenuId">
	<input type="hidden" name="selected_menu_id">
	<input type="hidden" name="selected_menu_detail_id">
	<input type="hidden" name="set_init_url">
	<input type="hidden" name="selected_paygbn"	id="selected_paygbn"    value="" />
	<!--// forward page -->
	<input type="hidden" name="contents"		id="contents"     value="" />
	<input type="hidden" name="buGubn" value=""/>
	<input type="hidden" name="pageGbn" value=""/>
	<input type="hidden" name="status" value=""/>
	<input type="hidden" name="mis_id" />
	<!-- 13/10/29 검색 조건 유지 추가 -->
	<input type="hidden" name="srch_review_title" value="<c:out value='${command.srch_review_title}'/>" />		<!-- 의뢰명 -->
	<input type="hidden" name="srch_cntrt_nm" value="<c:out value='${command.srch_cntrt_nm}'/>" />						<!-- Contract Title -->
	<input type="hidden" name="srch_cntrt_no" value="<c:out value='${command.srch_cntrt_no}'/>" />						<!-- Contract ID -->
	<input type="hidden" name="srch_str_org_acptday" value="<c:out value='${command.srch_str_org_acptday}'/>" />		<!-- Original Copy -->
	<input type="hidden" name="srch_end_org_acptday" value="<c:out value='${command.srch_end_org_acptday}'/>" />		<!-- Original Copy -->
	<input type="hidden" name="srch_reqman_nm" value="<c:out value='${command.srch_reqman_nm}'/>" />				<!-- 의뢰자 -->
	<input type="hidden" name="srch_cnsdman_nm" value="<c:out value='${command.srch_cnsdman_nm}'/>" />			<!-- 검토자 -->
	<input type="hidden" name="srch_cnclsnpurps_bigclsfcn" value="<c:out value='${command.srch_cnclsnpurps_bigclsfcn}'/>" />	<!-- 체결목적-->
	<input type="hidden" name="srch_start_reqday" value="<c:out value='${command.srch_start_reqday}'/>" />		<!-- 의뢰 시작일 -->
	<input type="hidden" name="srch_end_reqday" value="<c:out value='${command.srch_end_reqday}'/>" />			<!-- 의뢰 종료일 -->
	<input type="hidden" name="srch_start_cnlsnday" value="<c:out value='${command.srch_start_cnlsnday}'/>" />	<!-- 계약 시작일 -->
	<input type="hidden" name="srch_end_cnlsnday" value="<c:out value='${command.srch_end_cnlsnday}'/>" />		<!-- 계약 종료일 -->
	<input type="hidden" name="srch_step" value="<c:out value='${command.srch_step}'/>" />						<!-- 상태 -->
	<input type="hidden" name="srch_state" value="<c:out value='${command.srch_state}'/>" />					<!-- 상태 -->
	<input type="hidden" name="srch_oppnt_nm" value="<c:out value='${command.srch_oppnt_nm}'/>" />				<!-- 계약상대방 -->
	
	<!-- Sungwoo added search parameter 2014-06-12 -->
	<input type="hidden" name="srch_division" value="<c:out value='${command.srch_division}'/>" />					<!-- Original Copy -->
	<input type="hidden" name="srch_vendor_type" value="<c:out value='${command.srch_vendor_type}'/>" />					<!-- Original Copy -->
	<input type="hidden" name="srch_vendor_type_detail" value="<c:out value='${command.srch_vendor_type_detail}'/>" />		<!-- Original Copy -->
	
<!-- //
**************************** Form Setting **************************** 
-->
<div id="wrap">
<!-- container -->
<div id="container">
	<!-- location -->
	<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
	<!-- //location -->
	<!-- title -->
	<div class="title">
		<h3><spring:message code="clm.page.msg.manage.aftConclRegDtl" /></h3>
	</div>
	<!-- //title -->
	<!-- content -->
	<div id="content">
		<!-- 계약기본정보 -->
		<!--  title -->
		<div class="t_titBtn">
			<div class="title_basic">
				<h4><spring:message code="clm.page.msg.manage.bscInfCont" /> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'contract-view-tab');"/></h4>
			</div>
			<div class="btn_wrap_t02" id="contract-btnList"></div>
		</div>
		<!-- //title -->
		<!-- tab01 -->
		<ul id="contractTab" class="tab_basic02">
		<c:forEach var="list" items="${contractLom}">
			<li class=<c:if test="${list.rn=='1'}">on</c:if>><a><spring:message code="clm.page.msg.common.contract" /></a></li>
		</c:forEach>
		</ul>
		<!-- //tab01 -->
		<!-- 두줄라인 추가 --><div class="tal_topLine"></div><!-- //두줄라인 추가 -->
		<!-- toptable-->
		<!-- 계약기본정보 부분 -->
		<div id="contract-view"></div>
		<!-- //계약기본정보 부분 -->
		<!-- //top table -->
		<!-- //계약기본정보 -->
		<!-- bottom table -->
		<!-- 이력정보 -->
			<div id="contractHis-list"></div>
		<!-- //이력정보 -->
		<!-- //button -->
			<div id="contract-btnList2" align="center" style="padding:20px 0px 20px 0px;">
				<span class="btn_all_w" id="bnt_list"><span class="list"></span><a href="javascript:listManageExecution();"><spring:message code="clm.page.msg.manage.list" /></a></span>
			</div>
		<!-- //bottom table -->
	</div>
	<!-- footer  -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
	<!-- // footer -->
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</div>
</div>
</form:form>
</body>
</html>
