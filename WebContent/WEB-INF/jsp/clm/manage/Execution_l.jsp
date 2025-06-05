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
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%@ include file= "/WEB-INF/jsp/common/common.jsp" %>
<%-- 
/**
 * 파  일  명 : Execution_l.jsp
 * 프로그램명 : 이행관리 상세 목록
 * 설      명 : 
 * 작  성  자 : 박정훈
 * 작  성  일 : 2013.04
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet"/>
<!-- [if IE]><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery-1.7.2.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="/script/clms/common.js" type="text/javascript"></script>
<script language="javascript" src="/script/clms/clms_common.js" type="text/javascript"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/menu.js'/>"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript"  >
$(document).ready(function(){
	$('#max_exec_num').val("<c:out value='${max_exec_num}'/>");
	detailExecution("<c:out value='${contractCommand.cnsdreq_id}'/>","<c:out value='${contractCommand.cntrt_id}'/>");
});
/**
 * 이행정보
 */
function executionlist() {	
	var options = {
		url: "<c:url value='/clm/manage/execution.do?method=listExecution' />",
		type: "post",
		async: false,
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
	};
	$("#frm").ajaxSubmit(options);
}
function executionDetail() {

	var options = {
		url: "<c:url value='/clm/manage/execution.do?method=detailExecution' />",
		type: "post",
		async: false,
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
	};
	$("#frm").ajaxSubmit(options);
}


/**
 * Conclusion Information added 2014-09-09 Sungwoo 
 * Conclusion Information이 Execution History에 있어 해당내역 추가
 */
function conclusionInform() {

	var options = {
		url: "<c:url value='/clm/manage/execution.do?method=listContractHis' />",
 		type: "post",
		async: false,
		dataType: "html",
		success: function (data) {
			$("#conclusion-Info").html("");
			$("#conclusion-Info").html(data);
		}
	};
	$("#frm").ajaxSubmit(options);
}

/**
* 상세내역 조회
*/
function detailExecution(cnsdreq_id, cntrt_id){
	viewHiddenProgress(true);		
	 //초기화 : 연계 시 이 부분을 주석처리...
	$('input[name=cnsdreq_id]').val("");
	$('input[name=cntrt_id]').val("");
    //설정
    $('#cnsdreq_id').val(cnsdreq_id);
    $('#cntrt_id').val(cntrt_id);
    
    var options = {
    		url: "<c:url value='/clm/manage/execution.do?method=detailContract' />",
    		type: "post",
    		async: false,
    		dataType: "html",
    			
    		success: function(data){
    			$("#contract-view").html("");
    			$("#contract-view").html(data);
    			executionDetail();
    			conclusionInform();	//Sungwoo added 2014-09-09
    			contractHisList();
    			listContractRole();
    		}
    };
    $("#frm").ajaxSubmit(options);    
    attachPage();
    viewHiddenProgress(false);
}

function goCompletion(cnsdreq_id){
	
	var cntrt_id = $('#cntrt_id').val();

	var confirmMessage =  "<spring:message code='clm.msg.confirm.execution.gocompletion' />";
	
	 if(confirm(confirmMessage)){
		/* Sungwoo Commented 2014-06-05 do not change status when clicked Termination Management button.
		 var options = {
				url: "<c:url value='/clm/manage/execution.do?method=modifyContractStatus' />",
		    	type: "post",
		    	async: false,
		    	dataType: "html",
		    	
		    	success: function(data){
		    		Menu.detail('frm', '_top', '20130319152827427_0000000336', '20130319154642301_0000000355',  
		    				'/clm/manage/completion.do?method=listContract&cnsdreq_id='+cnsdreq_id+'&cntrt_id='+cntrt_id);
		    	}
		 };
		 $("#frm").ajaxSubmit(options);*/
		 Menu.detail('frm', '_top', '20130319152827427_0000000336', '20130319154642301_0000000355',  
 				'/clm/manage/completion.do?method=listContract&cnsdreq_id='+cnsdreq_id+'&cntrt_id='+cntrt_id);
	 }
}


//인터페이스작업[GSEMS로 계약정보 보내기] ajax로 리턴
function listSSemsIF() {
	
	var options = {
		url: "<c:url value='/clm/manage/conclusion.do?method=listSSemsIF'/>",
		type: "post",
		async: false,
		dataType: "json",
		success: function (data) {
						
			if(data != null && data.length != 0) {
								
				$.each(data, function(entryIndex, entry) {
					
					alert(entry['RESULT_MSG']);					
					
				});
				
			}
			
		}
	};
	if(confirm("<spring:message code="clm.page.msg.manage.announce008" /> <spring:message code="clm.page.msg.manage.announce003" />")){
		$("#frm").ajaxSubmit(options);		
	}
}


/**
* 계약기본정보 조회
*/
function detailBaseExecution(cnsdreq_id, cntrt_id){
	
	 //초기화 : 연계 시 이 부분을 주석처리...
	$('input[name=cnsdreq_id]').val("");
	$('input[name=cntrt_id]').val("");
	
    //설정
    $('#cnsdreq_id').val(cnsdreq_id);
    $('#cntrt_id').val(cntrt_id);
    
    var options = {
    		url: "<c:url value='/clm/manage/execution.do?method=detailContract' />",
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
    			
    		}
    };
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
	if("<c:out value='${contractCommand.session_user_id}'/>" != frm.reqman_id.value ){
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
	
	//Sungwoo replaced 2014-09-30
	if(frm.cntrt_info_gbn.value =='C05401'){
		frm.file_smlclsfcn.value = "F0120202";
	}else{
		frm.file_smlclsfcn.value = "F0120201";
	}
	frm.ref_key.value = "<c:out value='${contractCommand.cntrt_id}'/>@<c:out value='${contractCommand.cnsdreq_id}'/>";
	frm.submit();
	
    //계약서- 첨부/별첨
    frm.target          = "fileList3";
	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	frm.method.value    = "forwardClmsFilePage";
	frm.fileInfoName.value = "fileInfos3";
	frm.fileFrameName.value = "fileList3";

	frm.file_bigclsfcn.value = "F012";
	frm.file_midclsfcn.value = "F01202";
	//Sungwoo replaced 2014-09-30
	if(frm.cntrt_info_gbn.value =='C05401'){
		frm.file_smlclsfcn.value = "F0120209";
	}else{
		frm.file_smlclsfcn.value = "F0120208";
	}
	frm.ref_key.value = "<c:out value='${contractCommand.cntrt_id}'/>@<c:out value='${contractCommand.cnsdreq_id}'/>";
	frm.submit();
	
	//계약서 기타_체결본 파일
	frm.target          = "fileList2";
	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	frm.method.value    = "forwardClmsFilePage";
	frm.fileInfoName.value = "fileInfos2";
	frm.fileFrameName.value = "fileList2";

	frm.file_bigclsfcn.value = "F012";
	frm.file_midclsfcn.value = "F01202";
	//Sungwoo replaced 2014-09-30
	if(frm.cntrt_info_gbn.value =='C05401'){
		frm.file_smlclsfcn.value = "F0120206";
	}else{
		frm.file_smlclsfcn.value = "F0120205";
	}
	frm.ref_key.value = "<c:out value='${contractCommand.cntrt_id}'/>@<c:out value='${contractCommand.cnsdreq_id}'/>";
	frm.submit();   
	
	//OutLook 첨부파일 2013.10.25 추가
    frm.target          = "fileListOut";
    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
    frm.method.value    = "forwardClmsFilePage";
    frm.file_bigclsfcn.value = "O001";
    frm.file_midclsfcn.value = "O0011";
    frm.file_smlclsfcn.value = "O00111";
    frm.ref_key.value = "<c:out value='${contractCommand.cntrt_id}'/>@<c:out value='${contractCommand.cnsdreq_id}'/>";
    frm.fileInfoName.value = "fileListOut";
    frm.fileFrameName.value = "fileListOut";
    frm.submit();
	
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
    frm.submit();
	
    //단가내역    
    //2014-05-09 Sungwoo. Added null protasis
    if ( $("#untprc_expl").html() != null & $("#untprc_expl").html() != "" ){
        frm.target          		= "fileConsultationUntPrc";
        frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
        frm.method.value    		= "forwardClmsFilePage";
        frm.file_midclsfcn.value 	= "F01202";
        //frm.file_smlclsfcn.value 	= "F0120212";
        frm.file_smlclsfcn.value 	= "F0120211";
        frm.ref_key.value   		= frm.cntrt_id.value + "@" + frm.cnsdreq_id.value;
        frm.fileInfoName.value 		= "fileInfos5";
        frm.fileFrameName.value 	= "fileConsultationUntPrc";
        frm.submit();
    }    
    
  //계약서 사전검토 파일
	frm.target          = "fileList33";
	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	frm.method.value    = "forwardClmsFilePage";
	frm.fileInfoName.value = "fileInfos33";
	frm.fileFrameName.value = "fileList33";
	frm.file_bigclsfcn.value = "F012";
	frm.file_midclsfcn.value = "F01201";
	frm.file_smlclsfcn.value = "F0120101";
	frm.ref_key.value = frm.cntrt_id.value;
	frm.view_gbn.value = "download";
	frm.submit();
}

/**
* 목록
*/
function listManageExecution(){
	var frm = document.frm;
		
	if(frm.ismycontract.value == 'Y'){
		frm.target = "_self";		 
		frm.action = "<c:url value='/clm/manage/myContract.do' />";
		frm.method.value = "listMyContract";
		viewHiddenProgress(true) ;
		frm.submit();
	}
	else{
		frm.target = "_self";		 
		frm.action = "<c:url value='/clm/manage/execution.do' />";
		frm.method.value = "listManageExecution";			
		viewHiddenProgress(true) ;
		frm.submit();	
	}
}	

/*
 * 인쇄 팝업 띄우기
 */
function openPrint2() {
	var frm = document.frm;
	
	PopUpWindowOpen('', '1000','768',true, "popupMyApproval");
	
	frm.action	= "<c:url value='/clm/manage/myApproval.do' />";
	frm.method.value = "forwardPrintPop";
	frm.target = "popupMyApproval";
	frm.submit();
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
	
	
	var Feture = "toolbar=0, location=0, status=1, menubar=0, scrollbars=no, resizable=yes, top="+Top+", left="+Left+", width="+popupwidth+", height="+popupheight;
	PopUpWindow = window.open(surl, popName , Feture);

	PopUpWindow.focus();
	
}
/**
 * 인쇄버튼 
 * 
 */
function goRdView() {
	 var frm = document.frm;
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
	frm.buGubn.value = "";
	frm.mis_id.value = mis_id;
	frm.target = "popUpHistory";
	frm.submit();
}

/************************************************************************
 * 의뢰인관리 팝업
 */
 function openChooseClient(){
    var frm = document.frm;
    //alert($("input[name=arr_trgtman_id]").length);
    
    var items_str1 = $("input[name=arr_demnd_seqno]").map(function(){return this.value;}).get();
    var items_str2 = $("input[name=arr_trgtman_id]").map(function(){return this.value;}).get();
    var items_str3 = $("input[name=arr_trgtman_nm]").map(function(){return this.value;}).get();
    var items_str4 = $("input[name=arr_trgtman_jikgup_nm]").map(function(){return this.value;}).get();
    var items_str5 = $("input[name=arr_trgtman_dept_nm]").map(function(){return this.value;}).get();
    
    var items = items_str1+"!@#$"+ items_str2  +"!@#$"+  items_str3  +"!@#$"+  items_str4 +"!@#$"+items_str5;
    
    frm.chose_client.value = items;
            
    PopUpWindowOpen('', "530", "480",true,'PopUpWindow');
    frm.action = "<c:url value='/clm/manage/chooseClient.do' />";
    frm.method.value="forwardChooseClientPopup";
    frm.target = "PopUpWindow";
    frm.submit();
 }
 
 /**
 *의뢰자 리턴 객체 받기 심주완수정-2011.10.15
 */
 function setListClientInfo(returnValue) {
// 	var frm = document.frm;
       var arrReturn = returnValue.split("!@#$");
       var innerHtml ="";	
       
       $('#id_trgtman_nm').html("");
       
       if(arrReturn[0]=="") {
       	return ;
       }
       
       for(var i=0; i < arrReturn.length;i++) {
       	var arrInfo = arrReturn[i].split("|");
       	if((i != 0 && i != 1) && (i % 2 == 0)){
   			innerHtml += "<br/>";
       	}
       	if(i != 0 && (i % 2 != 0)){
       		innerHtml += ",";
       	}
       		innerHtml += "<input type='hidden' name='arr_demnd_seqno' id='arr_demnd_seqno' value='"+ arrInfo[0] +"' />";
       		innerHtml += "<input type='hidden' name='arr_trgtman_id' id='arr_trgtman_id' value='"+ arrInfo[1] +"' />";
       		innerHtml += "<input type='hidden' name='arr_trgtman_nm' id='arr_trgtman_nm' value='"+ arrInfo[2] +"' />";		        	
       		innerHtml += "<input type='hidden' name='arr_trgtman_jikgup_nm' id='arr_trgtman_jikgup_nm' value='"+ arrInfo[3] +"' />";
       		innerHtml += "<input type='hidden' name='arr_trgtman_dept_nm' id='arr_trgtman_dept_nm' value='"+ arrInfo[4] +"' />";
       		
       		innerHtml += arrInfo[2] +"/"+arrInfo[3] + "/" + arrInfo[4] ;
       		
       	$('#id_trgtman_nm').html(innerHtml);
       	
       }
       
       // 관련자 리스트 수정 여부 저장
       frm.client_modify_div.value = "Y";
       
       // 여기 부터 AJAX 로 실시간 DB 저장 처리   메소드 명 modifyRefCCAJAX
       var options = {   
				url: "<c:url value='/clm/review/consideration.do?method=modifyRefCCAJAX' />",
				type: "post",
				dataType: "json",
				success: function(responseText, statusText) {
					if(responseText.returnCnt != 0) {
						var html = "";
						$.each(responseText, function(entryIndex, entry) {
							var return_val = entry['return_val'];
						});						
					}						
				}
		};		
		$("#frm").ajaxSubmit(options);	
       
       
 }
</script>
</head>
<body>

<div id="wrap">
<!-- container -->
<div id="container">
	<!-- location -->
	<div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
	<!-- //location -->
	<!-- title -->
	<div class="title">
		<h3><spring:message code="clm.page.msg.manage.exeManage" /></h3>
	</div>
	<!-- //title -->
	<!-- content -->
	<div id="content">
	<div id="content_in">
		<!-- **************************** Form Setting ****************************-->
		<form:form name="frm" id='frm' method="post" autocomplete="off">
			<!-- required Form -->
			<input type="hidden" name="method"   value=""/>
			<input type="hidden" name="menu_id" value="<c:out value='${contractCommand.menu_id}'/>" />
			<!-- key hidden Form -->
			<input type="hidden" name="cnsdreq_id"	id="cnsdreq_id"   value="<c:out value='${contractCommand.cnsdreq_id}'/>" />
			<input type="hidden" name="cntrt_id"		id="cntrt_id"     value="<c:out value='${contractCommand.cntrt_id}'/>" />
			<input type="hidden" name="reqman_id"		id="reqman_id"     value="<c:out value='${contractCommand.reqman_id}'/>" />
			<!-- <input type="text" name="cnsd_dept"		id="cnsd_dept"    value="<c:out value='${contractCommand.cnsd_dept}'/>" /> -->
			
			<!-- 리스트 검색 조건시작  -->
			<input type="hidden" name="list_mode" value="<c:out value='${command.list_mode}'/>"/>
			<input type="hidden" name="srch_resp_dept" value="<c:out value='${command.srch_resp_dept}' escapeXml='false'/>"/>				<!-- 담당부서코드 -->
			<input type="hidden" name="srch_oppnt_cd" value="<c:out value='${command.srch_oppnt_cd}' escapeXml='false'/>"/>   				<!-- 계약상대방코드 -->
			<input type="hidden" name="srch_review_title" value="<c:out value='${command.srch_review_title}' escapeXml='false'/>" />		<!-- 의뢰명 -->
			<input type="hidden" name="srch_reqman_nm" value="<c:out value='${command.srch_reqman_nm}' escapeXml='false'/>" />				<!-- 의뢰자 -->
			<input type="hidden" name="srch_start_reqday" value="<c:out value='${command.srch_start_reqday}' escapeXml='false'/>" />		<!-- 의뢰 시작일 -->
			<input type="hidden" name="srch_end_reqday" value="<c:out value='${command.srch_end_reqday}' escapeXml='false'/>" />			<!-- 의뢰 종료일 -->
			<input type="hidden" name="srch_start_cnlsnday" value="<c:out value='${command.srch_start_cnlsnday}' escapeXml='false'/>" />	<!-- 계약 시작일 -->
			<input type="hidden" name="srch_end_cnlsnday" value="<c:out value='${command.srch_end_cnlsnday}' escapeXml='false'/>" />		<!-- 계약 종료일 -->
			<input type="hidden" name="srch_resp_dept_nm" value="<c:out value='${command.srch_resp_dept_nm}' escapeXml='false'/>" />		<!-- 담당부서명 -->
			<input type="hidden" name="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}' escapeXml='false'/>" />			<!-- 담당자명 -->
			<input type="hidden" name="srch_biz_clsfcn" value="<c:out value='${command.srch_biz_clsfcn}' escapeXml='false'/>" />			<!-- 계약단계 -->
			<input type="hidden" name="srch_cnsdman_nm" value="<c:out value='${command.srch_cnsdman_nm}' escapeXml='false'/>" />			<!-- 검토자 -->
			<input type="hidden" name="srch_oppnt_nm" value="<c:out value='${command.srch_oppnt_nm}' escapeXml='false'/>" />				<!-- 계약상대방 -->
			<input type="hidden" name="srch_state" value="<c:out value='${command.srch_state}' escapeXml='false'/>" />						<!-- 상태 -->
			<input type="hidden" name="srch_cnclsnpurps_bigclsfcn" value="<c:out value='${command.srch_cnclsnpurps_bigclsfcn}' escapeXml='false'/>" />	<!-- 체결목적-->
			<input type="hidden" name="ismycontract" id="ismycontract" value="<c:out value='${command.ismycontract}'/>"/>
			<input type="hidden" name="srch_step" value="<c:out value='${command.srch_step}' escapeXml='false'/>" />						<!-- 상태 -->
			<!-- Attach confg -->
			<input type="hidden" name="fileInfos"   value="" /> <!-- 저장될 파일 정보 -->
		    <input type="hidden" name="fileInfos2"  value="" /> <!-- 두번째첨부파일정보 -->
		    <input type="hidden" name="fileInfos3"  value="" /> <!-- 두번째첨부파일정보 -->
		    <input type="hidden" name="fileInfos4"  value="" /> <!-- 체결본 사본 -->
		    <input type="hidden" name="fileInfos5"  value="" /> <!-- 단가내역 -->
		    <!-- <input type="hidden" name="fileInfos3"  value="" /> <!-- 세번째첨부파일정보 -->
		    <!-- <input type="hidden" name="fileInfos20"  value="" /> 이력정보첨부파일정보 -->
		
		    <input type="hidden" name="fileInfoName"    value="" /> <!-- 첨부파일 정보가 리턴될 객체명 -->
		    <input type="hidden" name="fileFrameName"    value="" /> <!-- 첨부파일 화면 iFrame 명 -->
		
			<input type="hidden" name="file_bigclsfcn"  value="" /> <!-- 대분류 -->
			<input type="hidden" name="file_midclsfcn"  value="" /> <!-- 중분류 -->
			<input type="hidden" name="file_smlclsfcn"  value="" /> <!-- 소분류 -->
			<input type="hidden" name="ref_key"     value="" /> <!-- 키값 -->
			<input type="hidden" name="view_gbn"    value="download" /> <!-- 첨부파일 화면 -->
			<!-- //Attach confg -->
		
			<!-- forward page -->
			<input type="hidden" name="targetMenuId"/>
			<input type="hidden" name="selected_menu_id"/>
			<input type="hidden" name="selected_menu_detail_id"/>
			<input type="hidden" name="set_init_url"/>
			
			<input type="hidden" name="selected_paygbn"	id="selected_paygbn"    value="" />
			<!--// forward page -->
			
			<input type="hidden" name="contents"		id="contents"     value="" />
			<input type="hidden" name="buGubn" value=""/>
			<input type="hidden" name="mis_id" />
			
			<!-- 13/10/29 검색 조건 유지 추가 -->
			<input type="hidden" name="srch_cntrt_nm" value="<c:out value='${command.srch_cntrt_nm}'/>" />						<!-- Contract Title -->
			<input type="hidden" name="srch_cntrt_no" value="<c:out value='${command.srch_cntrt_no}'/>" />						<!-- Contract ID -->
			<input type="hidden" name="srch_tnc_no" value="<c:out value='${command.srch_tnc_no}'/>" />						<!-- tnc_no -->
			<input type="hidden" name="srch_str_org_acptday" value="<c:out value='${command.srch_str_org_acptday}'/>" />		<!-- Original Copy -->
			<input type="hidden" name="srch_end_org_acptday" value="<c:out value='${command.srch_end_org_acptday}'/>" />		<!-- Original Copy -->
			
			<input type="hidden" name="cntrt_no"   id="cntrt_no"  value="<c:out value='${cntrt_no}'/>" />
			<input type="hidden" name="ssems_oppnt_cd"   id="ssems_oppnt_cd"  value="<c:out value='${ssems_oppnt_cd}'/>" />
			
			<!-- 관련자 데이타 설정(구주추가요청 14.01)  -->
			<input type="hidden" name="master_cntrt_id" id="master_cntrt_id"  value="<c:out value='${command.cntrt_id}'/>" />
			<input type="hidden" name="client_modify_div" id="client_modify_div" />
		    <input type="hidden" name="chose_client" id="chose_client" />
			    
			<!-- Sungwoo added search parameter 2014-06-12 -->
			<input type="hidden" name="srch_division" value="<c:out value='${command.srch_division}'/>" />					<!-- Original Copy -->
			<input type="hidden" name="srch_vendor_type" value="<c:out value='${command.srch_vendor_type}'/>" />					<!-- Original Copy -->
			<input type="hidden" name="srch_vendor_type_detail" value="<c:out value='${command.srch_vendor_type_detail}'/>" />		<!-- Original Copy -->
			
			<!-- Sungwoo added 첨부파일 분기처리용 2014-09-30-->
			<input type="hidden" name="cntrt_info_gbn" id="cntrt_info_gbn" value="<c:out value='${contractReqLom.cntrt_info_gbn}' />" />
			<!-- //**************************** Form Setting ****************************-->
			<div class="content-step t_titBtn">
				<ol>
					<li><img src="<%=IMAGE %>/common/step01.gif"	 alt="<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" />" /></li>
					<li><img src="<%=IMAGE %>/common/step02.gif"     alt="<spring:message code="clm.page.msg.manage.step02" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step02" htmlEscape="true" />" /></li>
					<li><img src="<%=IMAGE %>/common/step03.gif"     alt="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" /></li>
					<li class='step_on'>
						<img src="<%=IMAGE %>/common/step04_on.gif"  alt="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" />
						<img src="<%=IMAGE %>/common/step_arr01.gif" alt="Next" title="Next" />
						<div class='step_link'><a href="javascript:open_window('win', '../../step04<%=langCd.equals("ko")?"":"_"+langCd %>.html', 0, 0, 696, 588, 0, 0, 0, 0, 0)" ><img src="<%=IMAGE %>/common/step_q.gif" alt="<spring:message code="clm.page.msg.manage.procStep"/>" title="<spring:message code="clm.page.msg.manage.procStep"/>" style="padding-top:5px;padding-left:3px;" /></a></div>
					</li>
					<li><img src="<%=IMAGE %>/common/step05.gif"     alt="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" /></li>
				</ol>
			</div>
		<!-- 계약기본정보 -->
		<!--  title -->
		<div class="t_titBtn">
			<div class="title_basic">
                <h4><spring:message code="clm.page.msg.manage.contInfo" /></h4>
            </div>
			<div class="btnwrap mt_22" id="contract-btnList"></div>
		</div>
		<!-- //title -->
		<!-- toptable-->
		<!-- 계약기본정보 부분 -->
		<div id="contract-view"></div>
		<!-- //계약기본정보 부분 -->
		<!-- //top table -->
		<!-- //계약기본정보 -->
		<!-- bottom table -->
		<!-- 이행관리 부분 -->
			
		<div id="contract-detail"></div>
		<div id="contract-cnclsnInfo"></div>
		<div id="contract-cnclsnIns"></div>
		<div id="contract-relation"></div>
		
		<!-- Conclusion Information -->
			<div id="conclusion-Info"></div>
		<!-- //Conclusion Information -->
		<!-- 이력정보 -->
			<div id="contractHis-list"></div>
		<!-- //이력정보 -->
		<!-- //button -->
			<div id="contract-btnList2" align="center" style="padding:20px 0px 20px 0px;">
				<c:if test="${contractCommand.session_user_id == contractLom[0].cntrt_respman_id || 'RA01' == command.top_role  }" >
				<script language="javascript">
				$(document).ready(function(){
					$('#goSave2').bind('click',function(){
						executionlist();
						$(this).remove();
						$('#goSave').remove();
						alert("<spring:message code="clm.page.msg.manage.announce161" />");
						$('#mainExecutionDetail-Content').focus();
					});
				});
				</script>
		        <!-- 종료 관리 버튼 임. RA01 일 경우 보이지 않게 표기 해야 함. => 담당자와 리갈어드민으로 버튼 활성화로 변경 joon lee with kelly 03/12/2014 -->
		        <span class="btn_all_w" id="bnt_forwardview"><span class="jungsanend_clear"></span><a href="javascript:alert('error')" onclick="javascript:goCompletion(document.frm.cnsdreq_id.value); return false;"><spring:message code="clm.page.msg.manage.endManage" /></a></span>				     
				</c:if>
							
				<c:if test="${prcs_depth == 'C02504' && ssems_oppnt_cd != null && ssems_oppnt_cd != ''}">
					<span class="btn_all_w"><span class="list"></span><a href="javascript:listSSemsIF();"><spring:message code="clm.page.msg.manage.ssemsCont" />(<c:out value='${ssems_oppnt_cd}'/>)</a></span>
				</c:if>				
				
				<span class="btn_all_p"><span class="print_title"></span><a href="javascript:openPrint2();"><spring:message code="clm.page.msg.manage.printCover" /></a></span>
				<span class="btn_all_w"><span class="print"></span><a href="javascript:openPrint();"><spring:message code="clm.page.msg.manage.print" /></a></span>
				<span class="btn_all_w" id="bnt_list"><span class="list"></span><a href="javascript:listManageExecution();"><spring:message code="clm.page.msg.manage.list" /></a></span>
			</div>
		
		<!-- //bottom table -->
	

					<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
				</form:form>
			</div>
		</div>
	</div>
</div>
	<!-- footer  -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
	<!-- // footer -->
</body>
</html>
