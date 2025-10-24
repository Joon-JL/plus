<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sec.clm.manage.dto.ConsultationForm" %>
<%@ page import="com.sec.common.util.ClmsDataUtil" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%
	ListOrderedMap lomReq		=  (ListOrderedMap)request.getAttribute("lomReq");
	ListOrderedMap contractLom	=  (ListOrderedMap)request.getAttribute("contractLom");
	ConsultationForm command	= (ConsultationForm)request.getAttribute("command");
	
%>
<%--
/**
 * 파  일  명 	 : Consultation_d_view_new.jsp
 * 프로그램명 	 : 체결품의상세화면(조회)
 * 설      명 	 : 
 * 작  성  자 	 : 박정훈
 * 작  성  일      : 2013.04
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
	<title><spring:message code="clm.main.title"/></title>
	<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet"/>
	<!-- [if IE]><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
<script language="javascript" src="/script/clms/common.js"></script>
<script language="javascript" src="/script/secfw/common/common.js"></script>
<script language="javascript" src="/script/clms/clms_common.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery-1.7.2.min.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.form.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.flot.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<!-- 달력관련추가 -->
<script language="javascript" src="/script/secfw/jquery/calendar/jquery.mousewheel.min.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script src="/script/func/ConsiderationFunc.js"></script><!-- 2014-07-28 Kevin added -->
<script>
$(document).ready(function(){
	var frm = document.frm;
	var status_cd = frm.prgrs_status.value;

	$("[id^='tr_show']").hide();
	$("[id^='tr_show01']").hide();
	
	// 2014-08-14 Kevin added.
	$("#tbBasicInfo").block({
		message:"<img src='/images/secfw/common/process_circle.gif' />",
		css:{border:"0px solid #FFFFFF", backgroundColor:"transparent"}
	});
	    
	initAttach() ;
	contractHisList(); //2014-09-02 Sungwoo Replaced Contract History. 

    if(status_cd == "C04215" || status_cd == "C04216") {
        if("<c:out value='${lomReq.reqman_id}'/>"=="<c:out value='${command.session_user_id}'/>") { //요청자와 현사용자가 같으면
            openChooseStatus();
        }   
    }
    
    setInitControl();
	
    // 2014-08-14 Kevin bug fix. 수정일 경우에만 아래 함수 로직을 실행할 수 있음.
    if("<c:out value='${lomReq.page_mode}'/>" == "M") {
    	paymentGbnChange();
    }
});

//팝업오픈-모달리스
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
	
	var Feture = "toolbar=0, location=0, status=1, menubar=0, scrollbars=" + (bScroll ? "yes" : "no") + ", resizable=yes, top="+Top+", left="+Left+", width="+popupwidth+", height="+popupheight;
	PopUpWindow = window.open(surl, popName , Feture);
	PopUpWindow.focus();
	
}

//첨부파일초기화
function initAttach(){
    //사전검토정보-첨부
    frm.target          		= "fileConsultationPre";
    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
    frm.method.value    		= "forwardClmsFilePage";
    frm.file_midclsfcn.value 	= "F01201";
    frm.file_smlclsfcn.value 	= "F0120101";
    frm.ref_key.value   		= frm.cntrt_id.value;
    frm.fileInfoName.value 		= "fileInfos3";
    frm.fileFrameName.value 	= "fileConsultationPre";
    frm.submit();
    
    //단가내역-첨부
    if(<%=StringUtil.isNull((String)lomReq.get("cntrt_untprc_expl"))%> == false) {	
	    frm.target          		= "fileConsultationUntPrc";
	    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    		= "forwardClmsFilePage";
	    frm.file_midclsfcn.value 	= "F01202";
	    frm.file_smlclsfcn.value 	= "F0120211";
	    frm.ref_key.value   		= frm.cntrt_id.value;
	    frm.fileInfoName.value 		= "fileInfos4";
	    frm.fileFrameName.value 	= "fileConsultationUntPrc";
	    frm.submit();
    }
}

//컨트롤설정
function setInitControl() {
	var frm = document.frm;
	var status_cd = frm.prgrs_status.value;
 	 
	if(status_cd != "C04211" && status_cd != "C04207" && status_cd != "C04212" ) {	//작성중이 아니면
		$('div[id^=consultation_btn]').attr("style", "display:none");
		$('div[id^=consultation_btn_list]').attr("style", "display:block");
		$('#rowAddDel').attr("style","display:none");
		$('#rowAddDel2').attr("style","display:none");	
		$('#rowAddDel3').attr("style","display:none");
	} else {
		if("<c:out value='${command.session_user_id}'/>" == frm.reqman_id.value) {
			$('div[id^=consultation_btn]').attr("style", "display:block");
			$('div[id^=consultation_btn_list]').attr("style", "display:none");
		} else {
			$('div[id^=consultation_btn]').attr("style", "display:none");
			$('div[id^=consultation_btn_list]').attr("style", "display:block");
			$('#rowAddDel').attr("style","display:none");
			$('#rowAddDel2').attr("style","display:none");	
			$('#rowAddDel3').attr("style","display:none");
		}	
	}
	
	if($('#close_yn').val()=='Y' ) {	
		$('div[id^=consultation_btn]').attr("style", "display:none");
		$('div[id^=consultation_btn_list]').attr("style", "display:block");
	}
}

function setSealMethod(val) {
	if(val=="C02101") {
		$('#seal-tr').attr("style", "display:block");
		$('#sign-tr').attr("style", "display:none");
		$('#seal_ffmtman_id').attr("required", "");
		$('#seal_ffmtman_nm').attr("required", "");
		$('#seal_ffmtman_search_nm').attr("required", "");
		$('#sign_plndman_id').removeAttr("required");
		$('#sign_plndman_nm').removeAttr("required");
		$('#sign_plndman_search_nm').removeAttr("required");
	} else {
		$('#seal-tr').attr("style", "display:none");
		$('#sign-tr').attr("style", "display:block");
		$('#seal_ffmtman_id').removeAttr("required");
		$('#seal_ffmtman_nm').removeAttr("required");
		$('#seal_ffmtman_search_nm').removeAttr("required");
		$('#sign_plndman_id').attr("required", "");
		$('#sign_plndman_nm').attr("required", "");
		$('#sign_plndman_search_nm').attr("required", "");
	}
}
/*
 * 재상신/재검토의뢰선택팝업
 */
function openChooseStatus(){
	var frm = document.frm;
	PopUpWindowOpen('', "200", "100", false, "popUpChooseStatus");
    frm.action="<c:url value='/clm/manage/consultation.do'/>";
    frm.method.value="forwardChooseStatusPopup";
    frm.target = "popUpChooseStatus";
    frm.submit();  
}

function modifyStatus(arg) {
	var frm = document.frm;
	if(arg=="dropRequest") {
		frm.action="<c:url value='/clm/manage/consultation.do' />";
		frm.target="_self";
		frm.method.value="listManageConsultation";
	} else {
		frm.action="<c:url value='/clm/manage/consultation.do' />";
		frm.target="_self";
		frm.method.value="modifyConsultationStatusDefer";
	}
	frm.submit();
}


<%--버튼액션부분시작--%>
function actionConsideration(arg){
	var msg = "<spring:message code='clm.msg.confirm.contract.consultation.deferrequest'/>";  			
	if(confirm(msg)){
		opnnConsideration(arg);
	}
}	

<%--품의작성--%>
function goModify() {
	viewHiddenProgress(true) ;
	frm.action 			= "<c:url value='/clm/manage/consultation.do' />";
	frm.target 			= "_self";
	frm.page_gbn.value 	= "modify";
	frm.method.value	= "detailConsultationNew";
	frm.submit();
}
<%--목록--%>
function goList() {
	viewHiddenProgress(true) ;
	//mycontract에서 접근했을 시
	if(frm.ismycontract.value == 'Y'){
		frm.target = "_self";		 
		frm.action = "<c:url value='/clm/manage/myContract.do' />";
		frm.method.value = "listMyContract";
		frm.submit();
	}
	else{
		frm.action			= "<c:url value='/clm/manage/consultation.do' />";
		frm.target			= "_self";
		frm.method.value	= "listManageConsultation";
		frm.submit();
	}
}


 /**
  * 의뢰 보류 / drop /계약 drop 
  * frm.submit_status.value = arg;
  */
  function opnnConsideration(arg){
 	
  	var frm 				= document.frm;
  	frm.submit_status.value = arg;
  	
     PopUpWindowOpen('', "530", "470", true, "popUpDrop");
     frm.action = "<c:url value='/clm/manage/consideration.do?prcsDepth=C02502' />";
     frm.method.value="opnnConsideration";
     frm.target = "popUpDrop";
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
  	frm.target = "popUpHistory";
  	frm.submit();
  }
  
  /**
	 * 회신상태로 프로세스 복원
	 */
	function processCancel(){
	    if("<c:out value='${contractLom.cnslt_no}'/>" == ""){
	    	if(!confirm("<spring:message code="clm.page.msg.manage.announce185" />")) return;
	    }else{
	    	alert("<spring:message code="clm.page.msg.manage.announce193" />"); 
	    	return;
	    }
		frm.method.value = "cancelConsultation";	
		frm.target = "_self";
		frm.tempsave_flag.value='button';
		frm.action = "<c:url value='/clm/manage/consultation.do' />";
	    frm.submit();
	    
	    viewHiddenProgress(true) ;
	}
  
  
	function paymentGbnChange(){         
        //payment_gbn 지수불 구분이 해당사항 없음이면 계약 금액 은 입력 불가 처리
        //계약금액,통화단위 ,계약단가 비활성화
        if($('#payment_gbn > option:selected').val() == "C02004" ){
            //cntrt_amt 계약금액            
            $("#cntrt_amt").val("");
            $("input[name=cntrt_amt]").attr('disabled', true);
            $("input[name=cntrt_untprc]").attr('checked', false);
            $("#crrncy_unit").attr('disabled', true);
            $("#crrncy_unit").html("<option value=><spring:message code='las.page.field.contractManager.notInv' />'</option>");
            $("input[name=cntrt_untprc]").attr('disabled', true);
            document.getElementById("trCntrtUntprc").style.display="none";
            $("#cntrt_untprc_expl").val("");
            //단가내역 요약 첨부파일 초기화
        }else{
            $("input[name=cntrt_amt]").attr('disabled', false);
            getCodeSelectByUpCd("frm", "crrncy_unit", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=A&combo_grp_cd=C5&combo_type=S&combo_del_yn=N&combo_selected=<c:out value='${contractLom.crrncy_unit}'/>');
            $("#input[name=cntrt_untprc]").attr('disabled', false);
            $("#crrncy_unit").attr('disabled', false);
            $("input[name=cntrt_untprc]").attr('disabled', false);
            if(frm.page_gbn.value == 'modify'){
                if($("#cntrt_untprc_expl").val()){
                    document.getElementById("trCntrtUntprc").style.display="block";
                    $("[name=cntrt_untprc]").attr('checked', true);
                }
            }
        }
    }
		// TOP 30 팝업
			function openTop30Customer(){
				   PopUpWindowOpen2("/clm/draft/customerTest.do?method=listTop30Customer", 500, 540, false, "Top30Customer");
				
			}
			
			function PopUpWindowOpen2(surl, popupwidth, popupheight, bScroll, popName) {
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
 	
//-->	
</script>
</head>
<body><!--J15003-->
<!-- container -->
<div id="wrap">
	<div id="container">
			
		<!-- location -->
		<!-- <div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0" alt="Home">계약관리 > 계약체결 ><strong> 쳬결품의</strong></div>-->
	    <div class="location">
	    	<img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0"/><c:out value='${menuNavi}' escapeXml="false"/>
	    </div>
	    <!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.title.contract.consultation.detail.title"/></h3>
		</div>
	    <!-- //location -->
		<!-- content -->
		<div id="content">
			<div id="content_in">
			<form:form name="frm" id="frm" method="post" autocomplete="off">
				<input type="hidden" name="contents" id="contents" />
				<!-- 리스트 검색 조건시작  -->
				<input type="hidden" name="list_mode" value="<c:out value='${command.list_mode}'/>"/>
				<input type="hidden" name="srch_resp_dept" value="<c:out value='${command.srch_resp_dept}'/>"/>				<!-- 담당부서코드 -->
				<input type="hidden" name="srch_oppnt_cd" value="<c:out value='${command.srch_oppnt_cd}'/>"/>   				<!-- 계약상대방코드 -->
				<input type="hidden" name="srch_review_title" value="<c:out value='${command.srch_review_title}'/>" />		<!-- 의뢰명 -->
				<input type="hidden" name="srch_reqman_nm" value="<c:out value='${command.srch_reqman_nm}'/>" />				<!-- 의뢰자 -->
				<input type="hidden" name="srch_start_reqday" value="<c:out value='${command.srch_start_reqday}'/>" />		<!-- 의뢰 시작일 -->
				<input type="hidden" name="srch_end_reqday" value="<c:out value='${command.srch_end_reqday}'/>" />			<!-- 의뢰 종료일 -->
				<input type="hidden" name="srch_start_cnlsnday" value="<c:out value='${command.srch_start_cnlsnday}'/>" />	<!-- 계약 시작일 -->
				<input type="hidden" name="srch_end_cnlsnday" value="<c:out value='${command.srch_end_cnlsnday}'/>" />		<!-- 계약 종료일 -->
				<input type="hidden" name="srch_resp_dept_nm" value="<c:out value='${command.srch_resp_dept_nm}'/>" />		<!-- 담당부서명 -->
				<input type="hidden" name="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}'/>" />			<!-- 담당자명 -->
				<input type="hidden" name="srch_biz_clsfcn" value="<c:out value='${command.srch_biz_clsfcn}'/>" />			<!-- 계약단계 -->
				<input type="hidden" name="srch_cnsdman_nm" value="<c:out value='${command.srch_cnsdman_nm}'/>" />			<!-- 검토자 -->
				<input type="hidden" name="srch_oppnt_nm" value="<c:out value='${command.srch_oppnt_nm}'/>" />				<!-- 계약상대방 -->
				<input type="hidden" name="srch_state" value="<c:out value='${command.srch_state}'/>" />						<!-- 상태 -->
				<input type="hidden" name="srch_if_sys_cd" value="<c:out value='${command.srch_if_sys_cd}'/>" />						<!-- 연계시스템 -->
				<input type="hidden" name="srch_cnclsnpurps_bigclsfcn" value="<c:out value='${command.srch_cnclsnpurps_bigclsfcn}'/>" />	<!-- 체결목적-->
				<input type="hidden" name="ismycontract" id="ismycontract" value="<c:out value='${command.ismycontract}'/>"/>
				<input type="hidden" name="srch_step" value="<c:out value='${command.srch_step}'/>" />						<!-- 상태 -->
				<!-- 13/10/29 검색 조건 유지 추가 -->
				<input type="hidden" name="srch_cntrt_nm" value="<c:out value='${command.srch_cntrt_nm}'/>" />						<!-- Contract Title -->
				<input type="hidden" name="srch_cntrt_no" value="<c:out value='${command.srch_cntrt_no}'/>" />						<!-- Contract ID -->
				<input type="hidden" name="srch_str_org_acptday" value="<c:out value='${command.srch_str_org_acptday}'/>" />		<!-- Original Copy -->
				<input type="hidden" name="srch_end_org_acptday" value="<c:out value='${command.srch_end_org_acptday}'/>" />		<!-- Original Copy -->
				<input type="hidden" name="method" id="method" value=""/>
				<input type="hidden" name="srch_user_cntnt_type" value=""/>
				<input type="hidden" name="srch_user_cntnt" value=""/>
				<input type="hidden" name="srch_user_cntnt_target" value=""/>
				<input type="hidden" name="worktype" value=""/>
				<input type="hidden" name="menu_id" id="menu_id" value="<c:out value='${command.menu_id}'/>" />
				<input type="hidden" name="curPage" id="curPage" value="<c:out value='${command.curPage}'/>"/>
				<input type="hidden" name="progressapprovalYN" value="N"/>
				<input type="hidden" name="approval_cntrt_info" value=""/>
				<input type="hidden" name="work_flag" value=""/>
				<input type="hidden" name="page_gbn"  value=""/>	
				<input type="hidden" name="submit_status"  value=""/>
				<input type="hidden" name="tempsave_flag" id="tempsave_flag" />
			    <!-- 계약추가 증가데이타 -->	    
			    <input type="hidden" name="master_cnslt_no" id="master_cnslt_no"  value="<c:out value='${contractLom.cnslt_no}'/>@<c:out value='${contractLom.hstry_no}'/>" />
			    <input type="hidden" name="cnsdreq_id" id="cnsdreq_id" value="<c:out value='${lomReq.cnsdreq_id}'/>" />
			    <input type="hidden" name="approval_key" id="approval_key" value="<c:out value='${lomReq.cnsdreq_id}'/>" />
			    <input type="hidden" name="prgrs_status" id="prgrs_status" value="<c:out value='${lomReq.prgrs_status}'/>" />
			    <input type="hidden" name="cntrt_id" id="cntrt_id"  value="<c:out value='${lomReq.cntrt_id}'/>" />
			    <input type="hidden" name="cntrt_untprc_expl" id="cntrt_untprc_expl"  value="<c:out value='${lomReq.cntrt_untprc_expl}'/>" />
			    <input type="hidden" name="reqman_id" id="reqman_id"  value="<c:out value='${lomReq.reqman_id}'/>" />
			    <!-- 관련자 데이타 설정(구주추가요청 14.01)  -->
	    		<input type="hidden" name="chose_client" id="chose_client" />
	    		<input type="hidden" name="master_cntrt_id" id="master_cntrt_id"  value="<c:out value='${lomReq.cntrt_id}'/>" />
			    <!-- 첨부파일정보 시작 -->
				<input type="hidden" name="fileInfos"       value="" /> 		<!-- 계약서 파일 정보 -->
				<input type="hidden" name="fileInfos2"      value="" /> 		<!-- 기타 파일 정보 -->
				<!-- <input type="hidden" name="fileInfos3"      value="" />-->	<!-- 사전검토 파일 정보 -->
				<input type="hidden" name="fileInfos4"      value="" /> 		<!-- 단가 파일 정보 -->
				<input type="hidden" name="fileInfos5"      value="" /> 		<!-- 별첨 파일 정보 -->
				<input type="hidden" name="file_bigclsfcn"  value="F012" /> 	<!-- 대분류 -->
				<input type="hidden" name="file_midclsfcn"  value="" /> 		<!-- 중분류 -->
				<input type="hidden" name="file_smlclsfcn"  value="" /> 		<!-- 소분류 -->
				<input type="hidden" name="ref_key"     	value="" /> 		<!-- 키값 -->
				<input type="hidden" name="view_gbn"    	value="download" />	<!-- 첨부파일 화면 -->
				<input type="hidden" name="fileInfoName"    value="" /> 		<!-- 저장될 파일 정보 -->
				<input type="hidden" name="fileFrameName"   value="" /> 		<!-- 소분류 -->
				<input type="hidden" name="mis_id" />
				<input type="hidden" name="close_yn" id="close_yn" value= "<c:out value='${contractLom.close_yn}'/>" />
				<div class="content-step t_titBtn">
					<ol>
						<li><img src="<%=IMAGE %>/common/step01.gif"	 alt="<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" />" /></li>
						<li class='step_on'>
							<img src="<%=IMAGE %>/common/step02_on.gif"  alt="<spring:message code="clm.page.msg.manage.step02" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step02" htmlEscape="true" />" />
							<img src="<%=IMAGE %>/common/step_arr01.gif" alt="Next" title="Next" />
							<div class='step_link'><a href="javascript:open_window('win', '../../step02<%=langCd.equals("ko")?"":"_"+langCd %>.html', 0, 0, 696, 588, 0, 0, 0, 0, 0)" ><img src="<%=IMAGE %>/common/step_q.gif" alt="<spring:message code="clm.page.msg.manage.procStep"/>" title="<spring:message code="clm.page.msg.manage.procStep"/>" style="padding-top:5px; padding-left:3px;" /></a></div>
						</li>
						<li><img src="<%=IMAGE %>/common/step03.gif"     alt="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" /></li>
						<li><img src="<%=IMAGE %>/common/step04.gif"     alt="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" /></li>
						<li><img src="<%=IMAGE %>/common/step05.gif"     alt="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" /></li>
					</ol>
				</div>
			<!-- //tab01 -->
			
			<!-- title -->
			  	<div class="title_basic">
					<h4><spring:message code="clm.page.msg.manage.revReplInf" /> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'req-table');"/></h4>
				</div>
				<div class="btnwrap mt_22" id="consultation_btn" style="display:none">
					<span class="btn_all_w" id="sp_defer1"><span class="delete"></span><a href="javascript:actionConsideration('deferRequest');"><spring:message code='clm.page.button.contract.defer' /></a></span>
					<span class="btn_all_w"><span class="preview"></span><a href="javascript:goModify();"><spring:message code='clm.page.button.contract.modifyconsultation' /></a></span>
					<span class="btn_all_w"><span class="reset"></span><a href="javascript:processCancel();"><spring:message code="clm.page.button.contract.rollback" /></a></span>
					<span class="btn_all_w"><span class="print"></span><a href="javascript:considerationPreview();"><spring:message code="clm.page.msg.manage.print" /></a></span>
					<span class="btn_all_w"><span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /></a></span>			
				</div>
				<div class="btnwrap mt_22" id="consultation_btn_list" style="display:none">
					<span class="btn_all_w"><span class="print"></span><a href="javascript:considerationPreview();"><spring:message code="clm.page.msg.manage.print" /></a></span>
					<span class="btn_all_w"><span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /></a></span>			
				</div>
			
				<!-- 2014-08-11 Kevin changed. Basic Information 섹션을 공통 페이지로 분리해서 include 시킨다. -->
			  	<jsp:include page="/WEB-INF/jsp/clm/common/basicInfo.jsp">
			  		<jsp:param name="cnsdreq_id" value='<%=lomReq.get("cnsdreq_id") %>' />
			  	</jsp:include>

			<div class="title_basic">
                <h4><spring:message code="clm.page.msg.manage.contInfo" /></h4>
            </div>
            <!-- //title -->
            <div class='title_basic3'><spring:message code="clm.page.msg.manage.bscInf" /></div>
            <table class="table-style01">
                <colgroup>
                    <col width="16%" />
                    <col width="20%" />
                    <col width="21%" />
                    <col width="15%" />
                    <col width="12%" />
                    <col width="16%" />
                </colgroup>
                <tr>
                    <th><spring:message code="clm.page.msg.manage.reqName" /></th>
                    <td colspan="3"><c:out value='${lomReq.req_title}' default="" escapeXml="false"/></td>
                    <th><spring:message code="clm.page.msg.manage.reqPerson" /></th>
                    <td>
                        <c:out value="${lomReq.reqman_nm}" />/<c:out value="${lomReq.reqman_jikgup_nm}" />/<c:out value="${lomReq.req_dept_nm}" />                      
                    </td>
                </tr>
                <tr>
                    <th><spring:message code='clm.page.field.contract.basic.name' /></th>
                    <td colspan="3"><c:out value="${contractLom.cntrt_nm}" />
                        <input type="hidden" name="cntrt_nm" id="cntrt_nm" value="<c:out value='${contractLom.cntrt_nm}'/>" />
                    </td>
                    <th><spring:message code="clm.page.msg.manage.contId" /></th>
                    <td><c:out value="${contractLom.cntrt_no}" />
                    	<%if(contractLom.get("tnc_no")!=null && !contractLom.get("tnc_no").equals("")) { %>
				            <span style='color:#A9B4BC;'>(<%=contractLom.get("tnc_no") %>)</span>
				            <%  } %>
                    </td>
                </tr>
                <tr>
                    <th><spring:message code='clm.page.msg.common.otherParty' /></th>
                    <td>
                        <input type="hidden" name="customer_cd" id="customer_cd" value="" />
                        <input type="hidden" name="dodun" id="dodun" value="" />
                        
                        <a href="javascript:customerPop('<c:out value="${contractLom.cntrt_oppnt_cd}" />','<c:out value="${contractLom.cntrt_oppnt_cd}" />');">
                            <c:out value="${contractLom.cntrt_oppnt_nm}" escapeXml="false"/>
                        </a>
                    </td>           
                    <th><spring:message code='clm.page.field.customer.registerNo' /></th>
                    <td><c:out value="${contractLom.cntrt_oppnt_rprsntman}" escapeXml="false"/></td>
                    <th><spring:message code="clm.page.field.customer.contractRequired" /></th>
                    <td><c:out value="${contractLom.cntrt_oppnt_type_nm}" /></td>
                </tr>
                <tr>
                    <th><spring:message code="clm.page.msg.manage.top30Cus" /><img src="<%=IMAGE %>/icon/ico_search2.gif" class="cp" onClick="javascript:openTop30Customer();" alt="search" style="padding-left:5px" title="Top 30 Customer List"/></th>
                    <td><c:out value="${contractLom.top_30_cus}"  escapeXml="false"/></td>
                    <th><spring:message code="clm.page.msg.manage.relatedPrd" /></th>
                    <td><c:out value="${contractLom.related_products}"  escapeXml="false"/></td>
                    <th><spring:message code="clm.page.msg.manage.srcContDraft" /></th>
                    <td><c:out value="${contractLom.cont_draft}" /></td>
                </tr>
                <tr>
                    <th><spring:message code='clm.page.field.contract.basic.type' /></th>
                    <td colspan="3">
                    <c:out value='${contractLom.biz_clsfcn_nm}'/> / <c:out value='${contractLom.depth_clsfcn_nm}'/> / <c:out value='${contractLom.cnclsnpurps_bigclsfcn_nm}'/> / <c:out value='${contractLom.cnclsnpurps_midclsfcn_nm}'/>
                    </td>
                    <th><spring:message code="clm.page.field.contract.consultation.detail.autorenew" /></th>
                    <td>
                        <c:if test="${contractLom.auto_rnew_yn=='Y'}">Yes</c:if>
                        <c:if test="${contractLom.auto_rnew_yn!='Y'}">No</c:if>
                    </td>
                </tr>
                <tr>
                    <th><spring:message code="clm.page.field.contract.basic.thing" /></th>
                    <td><c:out value="${contractLom.cntrt_trgt_nm}" /></td>
                    <th><spring:message code="clm.page.field.contract.basic.detail" /></th>
                    <td colspan="3">
                    <c:choose>
                        <c:when test="${lomReq.page_mode=='M'}">
                            <input type="text" class="text_full" name="cntrt_trgt_det" id="cntrt_trgt_det" value="<c:out value='${contractLom.cntrt_trgt_det}' escapeXml="false"/>"/></td>
                        </c:when>
                        <c:otherwise>
                            <c:out value="${contractLom.cntrt_trgt_det}" escapeXml="false"/>
                        </c:otherwise>
                    </c:choose>
                    </td>
                </tr>
                <tr>
                    <c:choose>
                        <c:when test="${lomReq.page_mode=='M'}">
                            <th><spring:message code='clm.page.field.contract.detail.period' /></th>
                            <td colspan="3">
                                <input type="text" name="cntrtperiod_startday" id="cntrtperiod_startday" value="<c:out value='${contractLom.cntrtperiod_startday}'/>" class="text_calendar02" required alt="<spring:message code="clm.page.msg.manage.contPeriod" htmlEscape="true" />" />
                                <c:if test="${contractLom.cntrtperiod_startday ne ''}">~</c:if> 
                                <input type="text" name="cntrtperiod_endday" id="cntrtperiod_endday" value="<c:out value='${contractLom.cntrtperiod_endday}'/>" class="text_calendar02" required alt="<spring:message code="clm.page.msg.manage.contPeriod" htmlEscape="true" />" />
                            <th><spring:message code='clm.page.field.contract.detail.paygubun' /></th>
                            <td>
                                <select name="payment_gbn" id="payment_gbn" required class="all" style="width:150px" onChange="paymentGbnChange();" >
                                    <c:out value="${combo.paymentGbn}" escapeXml="false"/>
                                </select>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <th><spring:message code='clm.page.field.contract.detail.period' /></th>
                            <td colspan="3"><c:out value="${contractLom.cntrtperiod_startday}" />
                            <c:if test="${contractLom.cntrtperiod_startday ne ''}">~</c:if>
                            <c:out value="${contractLom.cntrtperiod_endday}" /> 
                            <input type="hidden" name="cntrtperiod_startday" value="<c:out value='${contractLom.cntrtperiod_startday}' />"/>
                            <input type="hidden" name="cntrtperiod_endday" value="<c:out value='${contractLom.cntrtperiod_endday}' />"/>
                            </td>            
                            <th><spring:message code='clm.page.field.contract.detail.paygubun' /></th>
                            <td>
                                <c:out value="${contractLom.payment_gbn_nm}" />
                                <input type="hidden" name="payment_gubun" value="<c:out value='${contractLom.payment_gbn}' />"/>
                            </td>
                        </c:otherwise>
                    </c:choose>                 
                </tr>
                <tr>
                    <c:choose>
                        <c:when test="${lomReq.page_mode=='M'}">
                            <th><spring:message code='clm.page.field.contract.detail.price' /></th>
                            <td colspan="3">
                                <input type="text" name="cntrt_amt" id="cntrt_amt" value="<c:out value='${contractLom.cntrt_amt}'/>" onkeyup='olnyNum(this)' style="width: 150px; text-align: right; margin-right: 1px; IME-MODE: disabled;" class="text_full"  />
                                <input type="checkbox" name="cntrt_untprc" id="cntrt_untprc" value="Y" onclick="clickCntrtUntprc();" /><label for="" > <spring:message code="clm.page.msg.manage.conclSingleAmt" /></label>
                            </td>
                            <th><spring:message code='clm.page.field.contract.detail.money' /></th>
                            <td>
                                <select name="crrncy_unit" id="crrncy_unit">
                                    <c:out value="${combo.crrncyUnit }" escapeXml="false"/>
                                </select>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <th><spring:message code='clm.page.field.contract.detail.price' /></th>
                            <td colspan="3"><c:out value="${contractLom.cntrt_amt}" /><c:if test="${contractLom.cntrt_untprc_expl!=''}"> &nbsp;&nbsp;&nbsp;<spring:message code="clm.page.msg.manage.unitPrice" /></c:if></td>
                            <th><spring:message code='clm.page.field.contract.detail.money' /></th>
                            <td><c:out value="${contractLom.crrncy_unit}" /></td>
                        </c:otherwise>
                    </c:choose>                
                </tr>
                <c:choose>
                    <c:when test="${lomReq.page_mode=='M'}">
                        <tr id="trCntrtUntprc" style="display:none">
                           <th><spring:message code="clm.page.msg.manage.singleAmtSum" /></th>
                           <td colspan="5">
                               <textarea name="cntrt_untprc_expl" id="cntrt_untprc_expl" cols="30" rows="3" onkeyup="f_chk_byte(this,300)" class="text_area_full"><c:out value='${contractLom.cntrt_untprc_expl}'/></textarea>
                               <iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationUntPrc" name="fileConsultationUntPrc" frameborder="0" class="addfile_iframe_d" scrolling="auto" allowTransparency="true"></iframe>
                           </td>
                       </tr>
                    </c:when>
                    <c:otherwise>
                        <c:if test='${contractLom.cntrt_untprc_expl != "" }'>
                            <tr>
                                <th rowspan="2"><spring:message code='clm.page.field.contract.detail.unitcont' /></th>
                                <td colspan="5">
                                    <%=StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String)lomReq.get("cntrt_untprc_expl")))%>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="5" class="tal_lineL">
                                    <iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationUntPrc" name="fileConsultationUntPrc" frameborder="0" class="addfile_iframe_d" scrolling="auto" allowTransparency="true"></iframe>                           
                                </td>
                            </tr>
                        </c:if> 
                    </c:otherwise>
                </c:choose>
                <!-- 추진목적 및 배경 -->
                <c:choose>
                    <c:when test="${lomReq.page_mode=='M'}">
                        <tr>
                            <th><spring:message code='clm.page.field.contract.detail.object' /></th>
                            <td colspan="5">
	                            <input type="hidden" name="pshdbkgrnd_purps" id="pshdbkgrnd_purps" value="" />
	                            <input type="hidden" name="body_mime" id="body_mime" value="<c:out value='${contractLom.pshdbkgrnd_purps}'/>" />
                            </td>
                            
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <th><spring:message code='clm.page.field.contract.detail.object' /></th>
                            <td colspan="5"><c:out value="${contractLom.pshdbkgrnd_purps}" escapeXml="false"/>
                        </tr>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${lomReq.page_mode=='M'}">
                        <tr>
                            <th><spring:message code="clm.page.msg.manage.etcMain" /></th>
                            <td colspan="5">
                                <textarea name="etc_main_cont" id="etc_main_cont" alt="<spring:message code="clm.page.msg.manage.etcMain" htmlEscape="true" />" cols="30" rows="4" onkeyup="f_chk_byte(this,300)" class="text_area_full" maxLength="800"><c:out value='${contractLom.etc_main_cont}'/></textarea>
                            </td>
                            
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <th><spring:message code="clm.page.msg.manage.etcMain" /></th>
                            <td colspan="5">
                                <%=StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String)contractLom.get("etc_main_cont")))%>
                            </td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                
                <!-- 특화 정보 -->
                <c:forEach var="list" items="${considerationSpecialList}">
                    <c:if test="${!empty list.attr_value}">
                    <tr>    
                        <th><c:out value="${list.attr_nm}"/></th>
                        <td colspan="5"><c:out value="${list.attr_value}" escapeXml="false"/></td>
                    </tr>
                    </c:if> 
                </c:forEach>
                <c:forEach var="list" items="${consultationSpecialList}">
                    <c:if test="${!empty list.attr_value}">
                    <tr>
                        <th><c:out value="${list.attr_nm}"/></th>
                        <td colspan="5"><c:out value="${list.attr_value}" escapeXml="false"/></td>
                    </tr>
                    </c:if>
                </c:forEach>
                <!-- 특화 정보 끝 -->
                
                <!-- 배상책임한도 -->
                <c:if test='${!empty contractLom.oblgt_lmt}'>
                <tr>
                    <th><spring:message code="clm.page.field.contract.consultation.detail.obligationlimit"/></th>
                    <td colspan="5">
                        <c:out value='${contractLom.oblgt_lmt}' escapeXml="false"/>
                    </td>
                </tr>
                </c:if>
                
                <!-- 기타 특약사항 -->
                <c:if test='${!empty contractLom.spcl_cond}'>
                <tr>
                    <th><spring:message code="clm.page.msg.manage.etcSpecial" /></th>
                    <td colspan="5"><c:out value='${contractLom.spcl_cond}' escapeXml="false"/></td>
                </tr>
                </c:if>
                <!-- 비밀유지기간 -->
                <tr>
                    <th><spring:message code="clm.page.field.contract.detail.secret" /></th>
                    <td colspan="5"><c:out value='${contractLom.secret_keepperiod}' escapeXml="false"/></td>
                </tr>
                <!-- 준거법 / 준거법상세 -->
                <tr>
                    <th><spring:message code="clm.page.field.contract.detail.loac"/></th>
                    <td><c:out value='${contractLom.loac}'/></td>
                    <th><spring:message code="clm.page.field.contract.detail.loacdetail"/></th>
                    <td colspan="3"><c:out value='${contractLom.loac_etc}' escapeXml="false"/></td>
                </tr>
                <!-- 분쟁해결방법 / 분쟁해결방법 상세 -->
                <tr>
                    <th><spring:message code="clm.page.field.contract.detail.solutionmethod"/></th>
                    <td><c:out value='${contractLom.dspt_resolt_mthd}'/></td>
                    <th><spring:message code="clm.page.field.contract.detail.solutionmethoddetail"/></th>
                    <td colspan="3"><c:out value='${contractLom.dspt_resolt_mthd_det}' escapeXml="false"/></td>
                </tr>
            
            </table>
            
            <div class='title_basic3'><spring:message code="clm.page.msg.manage.preApprInf" /></div>
            <table class="table-style01">
                <colgroup>
                    <col width="12%" />
                    <col width="38%" />
                    <col width="12%" />
                    <col width="38%" />
                </colgroup>
                <c:choose>
                    <c:when test="${lomReq.page_mode=='M'}">
                        <tr>
                            <th><spring:message code="clm.page.msg.manage.apprDt" /></th>
                            <td><input type="text" name="bfhdcstn_apbtday" id="bfhdcstn_apbtday" class="text_calendar02" value="<c:out value='${contractLom.bfhdcstn_apbtday}'/>" /></td>
                            <th><spring:message code="clm.page.msg.manage.apprType" /></th>
                            <td>
                                <select name="bfhdcstn_apbt_mthd" id="bfhdcstn_apbt_mthd">
                                    <c:out value="${combo.bfhdcstnApbtMthd }" escapeXml="false"/>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th><spring:message code="clm.page.msg.manage.proposer" /></th>
                            <td>
                                <input type="hidden" name="bfhdcstn_mtnman_id" value="<c:out value='${contractLom.bfhdcstn_mtnman_id}'/>" />
                                <input type="hidden" name="bfhdcstn_mtn_dept_nm" value="<c:out value='${contractLom.bfhdcstn_mtn_dept_nm}'/>" />
                                <input type="text" name="bfhdcstn_mtnman_nm" value="<c:out value='${contractLom.bfhdcstn_mtnman_nm}'/>" style="width:120px" class="text_search" onKeyPress="if(event.keyCode==13) {searchEmployee('mtn');return false;}" /><img src="<%=IMAGE %>/icon/ico_search.gif" class="cp" alt="search" onclick="searchEmployee('mtn');" style="cursor:pointer"/>
                                <input type="hidden" name="bfhdcstn_mtnman_jikgup_nm" value="<c:out value='${contractLom.bfhdcstn_mtnman_jikgup_nm}'/>" />
                                <span id="bfhdcstn_mtnman_jikgup_nm_span"><c:out value='${contractLom.bfhdcstn_mtnman_jikgup_nm}'/></span>
                                / <span id="bfhdcstn_mtn_dept_nm_span"><c:out value='${contractLom.bfhdcstn_mtn_dept_nm}'/></span>
                            </td>
                            <th><spring:message code="clm.page.msg.manage.apprPer" /></th>
                            <td>
                                <input type="hidden" name="bfhdcstn_apbtman_id" id="bfhdcstn_apbtman_id"  value="<c:out value='${contractLom.bfhdcstn_apbtman_id}'/>" />
                                <input type="hidden" name="bfhdcstn_apbtman_jikgup_nm" id="bfhdcstn_apbtman_jikgup_nm" value="<c:out value='${contractLom.bfhdcstn_apbtman_jikgup_nm}'/>" />
                                <input type="hidden" name="bfhdcstn_apbt_dept_nm" id="bfhdcstn_apbt_dept_nm" value="<c:out value='${contractLom.bfhdcstn_apbt_dept_nm}'/>" />
                                <input type="text" name="bfhdcstn_apbtman_nm" id="bfhdcstn_apbtman_nm" value="<c:out value='${contractLom.bfhdcstn_apbtman_nm}'/>" style="width:120px" class="text_search" onKeyPress="if(event.keyCode==13) {searchEmployee('apbt');return false;}"/><img src="<%=IMAGE %>/icon/ico_search.gif" class="cp" alt="search"  onclick="searchEmployee('apbt');" style="cursor:pointer"/>
                                <span id="bfhdcstn_apbtman_jikgup_nm_span"><c:out value='${contractLom.bfhdcstn_apbtman_jikgup_nm}'/></span>
                                / <span id="bfhdcstn_apbt_dept_nm_span"><c:out value='${contractLom.bfhdcstn_apbt_dept_nm}'/></span>
                            </td>
                        </tr>
                        
                        <tr>
                            <th><spring:message code="clm.page.msg.manage.attachData" /></th>
                            <td colspan="3">
                                <spring:message code="clm.page.msg.manage.announce097" />
                                <!-- 2014-07-03 Sungwoo replacement height size -->
                        		<iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationPre" name="fileConsultationPre" frameborder="0" width="100%" height="100%" leftmargin="0" topmargin="0" scrolling="auto" class="addfile_iframe" allowTransparency="true"></iframe>                             
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <th><spring:message code="clm.page.msg.manage.apprDt" /></th>
                            <td><c:out value='${contractLom.bfhdcstn_apbtday}'/></td>
                            <th><spring:message code="clm.page.msg.manage.apprType" /></th>
                            <td><c:out value="${contractLom.bfhdcstn_apbt_mthd_nm}" /></td>
                        </tr>
                        <tr>
                            <th><spring:message code="clm.page.msg.manage.proposer" /></th>
                            <td>
                                <c:if test="${contractLom.bfhdcstn_mtnman_nm != ''}">
                                    <c:out value="${contractLom.bfhdcstn_mtnman_nm}" /> / <c:out value='${contractLom.bfhdcstn_mtnman_jikgup_nm}'/> / <c:out value='${contractLom.bfhdcstn_mtn_dept_nm}'/>
                                </c:if>
                            </td>
                            <th><spring:message code="clm.page.msg.manage.apprPer" /></th>
                            <td>
                                <c:if test="${contractLom.bfhdcstn_apbtman_nm != ''}">
                                    <c:out value='${contractLom.bfhdcstn_apbtman_nm}'/> / <c:out value='${contractLom.bfhdcstn_apbtman_jikgup_nm}'/> / <c:out value='${contractLom.bfhdcstn_apbt_dept_nm}'/>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <th><spring:message code="clm.page.msg.manage.attachData" /></th>
                            <td colspan="3">
                                <!-- 2014-07-03 Sungwoo replacement height size -->
                        		<iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationPre" name="fileConsultationPre" frameborder="0" width="100%" height="100%" leftmargin="0" topmargin="0" scrolling="auto" class="addfile_iframe" allowTransparency="true"></iframe>                             
                            </td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </table>
            
            <!-- tnc include JSP -->
			<jsp:include page="/clm/manage/completion.do">
				<jsp:param name="method" value="getTncLink" />
				<jsp:param name="cntrt_id4" value='<%=contractLom.get("cntrt_id") %>'/>
			</jsp:include>
            
            <div class='title_basic3'><spring:message code="clm.page.msg.manage.relContInf" /> <img src="<%=IMAGE %>/btn/btn_up.gif" alt="up" onclick="tit_Toggle(this,'div_rel_contract');" style="cursor:pointer"/></div>
                <div id="div_rel_contract">
                    <c:choose>
                        <c:when test="${lomReq.page_mode=='M'}">
                            <table cellspacing="0" cellpadding="0" border="0" class="table-style01">
                                <colgroup>
                                    <col width="12%" />
                                    <col width="50%" />
                                    <col width="10%" />
                                    <col/>                  
                                </colgroup>
                                <tbody id="relationTbody">
                                <tr id="trRelationContract">
                                    <th><spring:message code="clm.page.msg.manage.relation" /></th>
                                    <th><spring:message code="clm.page.msg.manage.relCont" /></th>
                                    <th><spring:message code="clm.page.msg.manage.define" /></th>
                                    <th><spring:message code="clm.page.msg.manage.relDetail" /></th>
                                </tr>
                                <tr id="trRelationContract">
                                    <td><select name="rel_type" id="rel_type" onchange="reltypeChange();"><c:out value="${combo.relType }" escapeXml="false"/></select></td>
                                    <td><input type="hidden" name="parent_cntrt_id" id="parent_cntrt_id"/>
                                        <input type="text" name="parent_cntrt_name" id="parent_cntrt_name" class="text_search" style="width:80%" readonly="readonly"/><img src="<%=IMAGE %>/icon/ico_search.gif" onclick="javascript:popupListContract('C03201');" class="cp" alt="search" /></td>
                                    <td><select name="rel_defn" id="rel_defn"></select></td>
                                    <td><input type="text" name="expl" id="expl" class="text_full" style="width:60%"/>
                                    <a href="javascript:actionRelationContract('insert','','');"><img src="<%=IMAGE %>/btn/btn_apply.gif" /></a></td>
                                </tr>
                                <tr id="trRelationContract1"></tr>
                                <c:choose>
                                <c:when test ="${relationListSize > 0}">
                                    <c:forEach var="list" items="${relationList}" varStatus="i">
                                        <tr id="trRelationContractCont">    
                                            <td class='tC'><c:out value='${list.rel_type_nm}'/></td>
                                            <td>
                                            	<!-- 신성우 변경처리 CNTRT_ID -> CNSDREQ_ID 2014-04-10 POPUP 호출시 REQ_ID 전달안함-->
                                                <a href="javascript:goDetail('<c:out value='${list.cnsdreq_id}'/>');"><c:out value='${list.relation_cntrt_nm}'/></a>
                                            </td>
                                            <td class='tC'><c:out value='${list.rel_defn}'/></td>
                                            <td class='tC'>
                                                <c:out value='${list.expl}'/>
                                                <a href="javascript:actionRelationContract('delete','<c:out value='${list.parent_cntrt_id}'/>');"><img src="/script/secfw/jquery/uploadify/cancel_new_en.gif"></a>
                                            </td>
                                        </td>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr><td colspan="4" align="center">No Data Found</td></tr>
                                </c:otherwise>
                                </c:choose>
                                </tr>   
                            </table>    
                            <table cellspacing="0" cellpadding="0" border="0" class="table-style01 borz01">
                                <tr>
                                    <td>
                                        ※ <spring:message code="clm.page.msg.manage.announce205" /> <br>
                                        &nbsp;<spring:message code="clm.page.msg.manage.forRel" />  <br>
                                        &nbsp;<spring:message code="clm.page.msg.manage.smRel" /> <br>
                                        &nbsp;<spring:message code="clm.page.msg.manage.chgCancel" /> <br>
                                        &nbsp;4) <spring:message code="clm.page.msg.common.etc" />  
                                    </td>
                                </tr>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <table cellspacing="0" cellpadding="0" border="0" class="table-style01">
                                <colgroup>
                                    <col width="12%" />
                                    <col width="50%" />
                                    <col width="10%" />
                                    <col/>                  
                                </colgroup>
                                <tbody id="relationTbody">
                                <tr id="trRelationContract">
                                    <th>RelationType</th>
                                    <th><spring:message code="clm.page.msg.manage.relCont" /></th>
                                    <th><spring:message code="clm.page.msg.manage.define" /></th>
                                    <th><spring:message code="clm.page.msg.manage.relDetail" /></th>
                                </tr>
                                <c:choose>
                                <c:when test ="${relationListSize > 0}">
                                    <c:forEach var="list" items="${relationList}" varStatus="i">
                                        <tr id="trRelationContractCont">
                                            <td><c:out value='${list.rel_type_nm}'/></td>
                                            <td>
                                                <!-- 신성우 변경처리 CNTRT_ID -> CNSDREQ_ID 2014-04-10 POPUP 호출시 REQ_ID 전달안함-->
                                                <a href="javascript:goDetail('<c:out value='${list.cnsdreq_id}'/>');"><c:out value='${list.relation_cntrt_nm}'/></a>
                                            </td>
                                            <td><c:out value='${list.rel_defn}'/></td>
                                            <td><c:out value='${list.expl}' escapeXml="false"/></td>
                                        </tr>       
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr id="trRelationContractCont"><td colspan="4" align="center">No Data Found</td></tr>
                                </c:otherwise>
                                </c:choose>         
                            </table>
                        </c:otherwise>
                    </c:choose>
                </div>
			
			<div class="title_basic">
                <h4><spring:message code="clm.page.title.contract.conclusion.detail.title"/> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'tab_contents_sub_conts3');" style="cursor:pointer"/></h4>
            </div>
            <table id="tab_contents_sub_conts3" cellspacing="0" cellpadding="0" border="0" class="table-style01">
                <colgroup>
                    <col width="12%" />
                    <col width="88%" />
                    <col />
                </colgroup>
                <tr>
                    <th><spring:message code="clm.page.field.contract.consultation.detail.methodgbn"/></th>
                    <td>
                        <c:choose>
                            <c:when test="${lomReq.page_mode=='M'}">
                                <input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02103" onclick="javascript:setSealMethod(this.value);"<c:if test="${contractLom.seal_mthd=='C02103'}"> checked</c:if>><spring:message code="clm.page.msg.manage.signCorp" /></input>-->
                                <input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02102" onclick="javascript:setSealMethod(this.value);"<c:if test="${contractLom.seal_mthd=='C02102'}"> checked</c:if>><spring:message code="clm.page.msg.manage.sign" /></input>
                                 <br/>※ <spring:message code="clm.page.msg.manage.announce066" />
                                 <br/>※ <spring:message code="clm.page.msg.manage.announce154" />
                            </c:when>
                            <c:otherwise>
                                <c:out value="${contractLom.seal_mthd_nm}"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
				<c:set var="display2" value="block"/>
				<c:set var="dispcolspan" value="2"/>
                <!-- 서명인 경우 -->
                <input type="hidden" name="seal_mthd" id="seal_mthd" value="C02102" />
                <tr id="sign-tr">
                    <th><spring:message code="clm.page.field.contract.consultation.detail.signplannernm"/></th><!-- 서명예정자 -->
                    <td>
                        <c:choose>
                            <c:when test="${lomReq.page_mode=='M'}">
                                <input type="hidden" name="sign_plndman_id" id="sign_plndman_id" value="<c:out value='${contractLom.sign_plndman_id}'/>" />
                                <input type="hidden" name="sign_plndman_nm" id="sign_plndman_nm" value="<c:out value='${contractLom.sign_plndman_nm}'/>" />
                                <input type="hidden" name="sign_plndman_jikgup_nm" id="sign_plndman_jikgup_nm" value="<c:out value='${contractLom.sign_plndman_jikgup_nm}'/>"/>
                                <input type="hidden" name="sign_plnd_dept_nm" id="sign_plnd_dept_nm" value="<c:out value='${contractLom.sign_plnd_dept_nm}'/>"/>
                                <input type="text" name="sign_plndman_search_nm" id="sign_plndman_search_nm" value="" style="width:110px" class="text_search" alt="<spring:message code='clm.page.field.contract.consultation.detail.signplannernm'/>" onkeydown="if(event.keyCode==13){event.returnValue = false;javascript:searchEmployee('sign');}"/><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchEmployee('sign');" class="cp" alt="search" />
                                <span id="plndman">&nbsp;&nbsp;<c:out value='${contractLom.sign_plndman_nm}'/>/<c:out value='${contractLom.sign_plndman_jikgup_nm}'/>/<c:out value='${contractLom.sign_plnd_dept_nm}'/></span>
                            </c:when>
                            <c:otherwise>
                                <c:out value='${contractLom.sign_plndman_nm}'/>/<c:out value='${contractLom.sign_plndman_jikgup_nm}'/>/<c:out value='${contractLom.sign_plnd_dept_nm}'/>
                            </c:otherwise>
                        </c:choose>      
                    </td>
                </tr>
                <tr>
                    <th><spring:message code="clm.page.field.contract.consultation.detail.contractrespmannm"/></th>
                    <td>
                        <c:choose>
                            <c:when test="${lomReq.page_mode=='M'}">
                                <input type="hidden" name="cntrt_respman_id" id="cntrt_respman_id" value="<c:out value='${contractLom.cntrt_respman_id}'/>" />
                                <input type="hidden" name="cntrt_respman_nm" id="cntrt_respman_nm" value="<c:out value='${contractLom.cntrt_respman_nm}'/>" />
                                <input type="hidden" name="cntrt_respman_jikgup_nm" id="cntrt_respman_jikgup_nm" value="<c:out value='${contractLom.cntrt_respman_jikgup_nm}'/>"/>
                                <input type="hidden" name="cntrt_resp_dept" id="cntrt_resp_dept" value="<c:out value='${contractLom.cntrt_resp_dept}'/>"/>
                                <input type="hidden" name="cntrt_resp_dept_nm" id="cntrt_resp_dept_nm" value="<c:out value='${contractLom.cntrt_resp_dept_nm}'/>"/>
                                <input type="text" name="cntrt_respman_search_nm" id="cntrt_respman_search_nm" value=""  style="width:110px" class="text_search"  alt="<spring:message code='clm.page.field.contract.consultation.detail.contractrespmannm'/>" onkeydown="if(event.keyCode==13){event.returnValue = false;javascript:searchEmployee('contract');}"/><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchEmployee('contract');" class="cp" alt="search"/>
                                <span id="respman">&nbsp;&nbsp;<c:out value='${contractLom.cntrt_respman_nm}'/>/<c:out value='${contractLom.cntrt_respman_jikgup_nm}'/>/<c:out value='${contractLom.cntrt_resp_dept_nm}'/></span>
                                <br><br><span><spring:message code="clm.msg.alert.contract.consultation.respmannotice"/></span>
                            </c:when>
                            <c:otherwise>
                                <c:out value='${contractLom.cntrt_respman_nm}'/>/<c:out value='${contractLom.cntrt_respman_jikgup_nm}'/>/<c:out value='${contractLom.cntrt_resp_dept_nm}'/>
                            </c:otherwise>
                        </c:choose>     
                    </td>
                </tr>
            </table>
            
            <!-- 15개 항목 상세 -->
			<jsp:include page="/clm/review/considerationHQ.do">
				<jsp:param name="method" value="getCheckList" />
				<jsp:param name="cntrt_id3" value='<%=contractLom.get("cntrt_id") %>'/>
			</jsp:include>
			
			<!-- 이력정보 -->
			<div id="contractHis-list"></div>
			<!-- //이력정보 -->	
			
			<div class="t_titBtn">
	                <div class="btn_wrap_c" id="consultation_btn" style="display:none;">
	                    <span class="btn_all_w" id="sp_defer2"><span class="delete"></span><a href="javascript:actionConsideration('deferRequest');"><spring:message code='clm.page.button.contract.defer' /></a></span>
	                    <span class="btn_all_w"><span class="preview"></span><a href="javascript:goModify();"><spring:message code='clm.page.button.contract.modifyconsultation' /></a></span>
	                    <span class="btn_all_w"><span class="reset"></span><a href="javascript:processCancel();"><spring:message code="clm.page.button.contract.rollback" /></a></span>
	                    <span class="btn_all_w"><span class="print"></span><a href="javascript:printPage();"><spring:message code="clm.page.msg.manage.print" /></a></span>
	                    <span class="btn_all_w"><span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /></a></span>         
	                </div>
	                <div class="btn_wrap_c" id="consultation_btn_list" style="display:none;">
	                    <span class="btn_all_w"><span class="print"></span><a href="javascript:printPage();"><spring:message code="clm.page.msg.manage.print" /></a></span>
	                    <span class="btn_all_w"><span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /></a></span>         
	                </div>
	         </div>
			</form:form>
           </div><!-- content_in End -->
		</div><!-- content End -->
	</div><!-- Contaner End -->
</div><!-- wrap End -->
		<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- // footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</body>
</html>