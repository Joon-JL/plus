<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.sec.clm.manage.control.MyApprovalController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.DateUtil"%>
<%@ page import="com.sds.secframework.common.util.PageUtil"%>
<%@ page import="com.sds.secframework.common.util.StringUtil"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap"%>
<%--
/**
 * 파  일  명 	 : MyApproval_d_view.jsp
 * 프로그램명 	 : 원본접수 목록의  상세화면
 * 설      명 	 : 
 * 작  성  자 	 : 박정훈
 * 작  성  일      : 2013.04
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="clm.main.title" />
</title>
<!--계약관리일 경우  -->
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet" />
<!-- [if IE]><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
	<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
	<script language="javascript" src="<c:url value='/script/clms/common.js' />"></script>
	<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
	<script language="javascript" src="<c:url value='/script/secfw/jquery/excanvas.min.js' />"></script>
	<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery-1.7.2.min.js' />"></script>
	<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
	<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
	<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>

	<!-- 달력관련추가 -->
	<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
	<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
	<script>
	$(document).ready(function(){

		var cntrt_id = "";
		viewHiddenProgress(true) ;
		if("<c:out value='${command.cntrt_id}'/>" == ""){			
			// 프로그래스바표시를 위해 타이머함수 사용
			setTimeout(function() {         
				changeContract("<c:out value='${lomReq.cntrt_id}'/>");
		    }, 10);			
			cntrt_id = "<c:out value='${lomReq.cntrt_id}'/>";
		} else {			
			// 프로그래스바표시를 위해 타이머함수 사용
			setTimeout(function() {         
				changeContract("<c:out value='${command.cntrt_id}'/>");
		    }, 10);			
			cntrt_id = "<c:out value='${command.cntrt_id}'/>";
		}
		
		
		$('#agree_yn').val("<c:out value='${lomReq.agree_yn}'/>");
		
	});

/**
* 계약검토의뢰등록 폼  inner View 
*/
function detailMyApprovalContractMaster(){
	var options = {
			url: "<c:url value='/clm/manage/myApproval.do?method=detailMyApprovalContractMaster' />",
			type: "post",
			async: false,
			dataType: "html",
			success: function (data) {
				$("#contract-master-info").html("");
				$("#contract-master-info").html(data);
			}
		};
	$("#frm").ajaxSubmit(options);
}

/**
 * 종료관리
 */
function forwardCompletion() {

	var options = {
		url: "<c:url value='/clm/manage/completion.do?method=forwardInsertCompletion&isMyApproval=Y' />",
		type: "post",
		async: false,
		dataType: "html",
		success: function (data) {
			$("#contract-CompletionInfo").html("");
			$("#contract-CompletionInfo").html(data);			
		}
	};
	$("#frm").ajaxSubmit(options);
}

/**
 *계약탭 클릭
 */
 function changeContract(cntrt_id){
	document.frm.cntrt_id.value = cntrt_id;
	detailMyApprovalContractMaster();
	if('<c:out value='${cntrtLom.depth_status}'/>' == 'C02685')
		forwardCompletion();
	contractHisList();
	initAttach();
	initButton();
	viewHiddenProgress(false) ;
 }

//첨부파일초기화
function initAttach(){
	//계약-계약서파일
	frm.target          		= "fileConsultationContract";
    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
    frm.method.value    		= "forwardClmsFilePage";
    frm.file_midclsfcn.value 	= "F01202";
    frm.file_smlclsfcn.value	= "F0120201";
    frm.ref_key.value   		= frm.cntrt_id.value + "@" + frm.cnsdreq_id.value;
    frm.fileInfoName.value 		= "fileInfos";
    frm.fileFrameName.value 	= "fileConsultationContract";
    getClmsFilePageCheck('frm');   
	
  //계약-별첨
    frm.target          		= "fileConsultationEtc";
    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
    frm.method.value   		 	= "forwardClmsFilePage";
    frm.file_midclsfcn.value 	= "F01202";
    frm.file_smlclsfcn.value 	= "F0120208";
    frm.ref_key.value   		= frm.cntrt_id.value + "@" + frm.cnsdreq_id.value;
    frm.fileInfoName.value 		= "fileInfos5";
    frm.fileFrameName.value 	= "fileConsultationEtc";
    getClmsFilePageCheck('frm');
    
    //계약-기타
    frm.target          		= "fileConsultationEtc2";
    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
    frm.method.value   		 	= "forwardClmsFilePage";
    frm.file_midclsfcn.value 	= "F01202";
    frm.file_smlclsfcn.value 	= "F0120205";
    frm.ref_key.value   		= frm.cntrt_id.value + "@" + frm.cnsdreq_id.value;
    frm.fileInfoName.value 		= "fileInfos2";
    frm.fileFrameName.value 	= "fileConsultationEtc2";
    getClmsFilePageCheck('frm');
    
  //단가내역-첨부
  if("<c:out value='${lomReq.cntrt_untprc_expl}' />" != "") {
    frm.target          		= "fileConsultationUntPrc";
    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
    frm.method.value    		= "forwardClmsFilePage";
    frm.file_midclsfcn.value 	= "F01202";
    frm.file_smlclsfcn.value 	= "F0120211";
    frm.ref_key.value   		= frm.cntrt_id.value + "@" + frm.cnsdreq_id.value;
    frm.fileInfoName.value 		= "fileInfos4";
    frm.fileFrameName.value 	= "fileConsultationUntPrc";
    getClmsFilePageCheck('frm');
  }
  
  if('<c:out value='${cntrtLom.depth_status}'/>' == 'C02642' || '<c:out value='${cntrtLom.depth_status}'/>' == 'C02685'){
	  //계약서사본
	  frm.target          		= "fileContractCopy";
	  frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
	  frm.method.value    		= "forwardClmsFilePage";
	  frm.file_midclsfcn.value	= "F01203";
	  frm.file_smlclsfcn.value 	= "";
	  frm.fileInfoName.value 	= "fileInfos6";
	  frm.fileFrameName.value	= "fileContractCopy";
	  frm.multiYn.value 		= "N";
	  frm.ref_key.value			= frm.cntrt_id.value;
	  getClmsFilePageCheck('frm');
  }
  
  if('<c:out value='${cntrtLom.depth_status}'/>' == 'C02685'){
	//종료관리 파일
	frm.target          = "fileList10";
	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	frm.method.value    = "forwardClmsFilePage";
	frm.fileInfoName.value = "fileInfos10";
	frm.fileFrameName.value = "fileList10";

	frm.file_bigclsfcn.value = "F012";
	frm.file_midclsfcn.value = "F01204";
	frm.file_smlclsfcn.value = "";
	frm.ref_key.value = "<c:out value='${command.cntrt_id}'/>";
	
	frm.view_gbn.value = "modify";
	if($('#cntrt_chge_demndman_id').length < 1){
		frm.view_gbn.value = "download";
		getClmsFilePageCheck('frm');
	}else{
		frm.view_gbn.value = "modify";
		frm.submit();
	}
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
	getClmsFilePageCheck('frm');
  
}

function goList() {
	viewHiddenProgress(true) ;
	
	// 2014-07-28 Kevin changed.
	// Incorrect redirection bug fix.
	var frm = document.frm;
	/* frm.target          = "_self";
	frm.action          = "<c:url value='/clm/manage/myApproval.do' />";
    frm.method.value    = "listMyApproval";
    frm.submit(); */
    
    frm.target = "_self";		 
	frm.action = "/clm/manage/myContract.do";
	frm.method.value = "listMyContract";
	frm.submit();
}

function goModify() {
	
	alert("<spring:message code="clm.page.msg.manage.announce047" />");
	
	var frm = document.frm;
	frm.target          = "_self";
	frm.action          = "<c:url value='/clm/manage/myApproval.do' />";
	frm.page_gbn.value	= "modify";	
    frm.method.value    = "detailMyApproval";
    viewHiddenProgress(true) ;
    frm.submit();
}


function initButton() {
	var frm = document.frm;

	if('<c:out value='${cntrtLom.depth_status}'/>' == 'C02642' || '<c:out value='${cntrtLom.depth_status}'/>' == 'C02636') { //원본미등록
		if("<c:out value='${command.user_role}'/>"=="RD01") {
			$('div[id^=myapproval_btn]').attr("style", "display:");
			$('div[id^=myapproval_btn_list]').attr("style", "display:none");
		} else {
			$('div[id^=myapproval_btn]').attr("style", "display:none");
			$('div[id^=myapproval_btn_list]').attr("style", "display:");
		}
	} else {
		$('div[id^=myapproval_btn]').attr("style", "display:none");
		$('div[id^=myapproval_btn_list]').attr("style", "display:");
	}
}


/*
 * 인쇄 팝업 띄우기
 */
function openPrint() {
	var frm = document.frm;
	
	PopUpWindowOpen('', '1000','768',true, "popupMyApproval");
	
	frm.action	= "<c:url value='/clm/manage/myApproval.do' />";
	frm.method.value = "forwardPrintPop";
	frm.target = "popupMyApproval";
	frm.submit();
}

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

</script>
</head>
<body><!--J15004-->
	<!-- container -->
	<div id="wrap">
		<div id="container">
			<!-- location -->
			<div class="location">
				<img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11"
					border="0" />
				<c:out value='${menuNavi}' escapeXml="false" />
			</div>
			<!-- title -->
			<div class="title">
				<h3>
					<spring:message code="clm.page.title.myapproval.mainTitle" />
				</h3>
			</div>
			<!-- //location -->
			<!-- content -->
			<div id="content">
				<div id="content_in">
				<div class="content-step t_titBtn">
					<ol>
						<li><img src="<%=IMAGE %>/common/step01.gif"	 alt="<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" />" /></li>
						<li><img src="<%=IMAGE %>/common/step02.gif"     alt="<spring:message code="clm.page.msg.manage.step02" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step02" htmlEscape="true" />" /></li>

						<c:choose>
							<c:when test="${cntrtLom.depth_status == 'C02642'}">
								<li class='step_on'>
									<img src="<%=IMAGE %>/common/step03_on.gif"  alt="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" />
									<img src="<%=IMAGE %>/common/step_arr01.gif" alt="Next" title="Next" />
									<div class='step_link'><a href="javascript:open_window('win', '../../step03<%=langCd.equals("ko")?"":"_"+langCd %>.html', 0, 0, 696, 588, 0, 0, 0, 0, 0)" ><img src="<%=IMAGE %>/common/step_q.gif" alt="<spring:message code="clm.page.msg.manage.procStep"/>" title="<spring:message code="clm.page.msg.manage.procStep"/>" /></a></div>
								</li>
							</c:when>
							<c:otherwise>
								<li><img src="<%=IMAGE %>/common/step03.gif"     alt="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" /></li>
							</c:otherwise>
						</c:choose>

						<li><img src="<%=IMAGE %>/common/step04.gif"     alt="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" /></li>

						<c:choose>
							<c:when test="${cntrtLom.depth_status == 'C02685'}">
								<li class='step_on'>
									<img src="<%=IMAGE %>/common/step05_on.gif"  alt="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" />
									<div class='step_link'><a href="javascript:open_window('win', 'step05<%=langCd.equals("ko")?"":"_"+langCd %>.html', 0, 0, 696, 588, 0, 0, 0, 0, 0)" ><img src="<%=IMAGE %>/common/step_q.gif" alt="<spring:message code="clm.page.msg.manage.procStep"/>" title="<spring:message code="clm.page.msg.manage.procStep"/>" /></a></div>
								</li>
							</c:when>
							<c:otherwise>
								<li><img src="<%=IMAGE %>/common/step05.gif"     alt="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" /></li>
							</c:otherwise>
						</c:choose>

					</ol>
				</div>
				<!-- //tab01 -->
				<form:form name="frm" id='frm' method="post" autocomplete="off">
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
				<input type="hidden" name="srch_step" value="<c:out value='${command.srch_step}' escapeXml='false'/>" />			<!-- 담당자명 -->
				<input type="hidden" name="srch_biz_clsfcn" value="<c:out value='${command.srch_biz_clsfcn}' escapeXml='false'/>" />			<!-- 계약단계 -->
				<input type="hidden" name="srch_cnsdman_nm" value="<c:out value='${command.srch_cnsdman_nm}' escapeXml='false'/>" />			<!-- 검토자 -->
				<input type="hidden" name="srch_oppnt_nm" value="<c:out value='${command.srch_oppnt_nm}' escapeXml='false'/>" />				<!-- 계약상대방 -->
				<input type="hidden" name="srch_state" value="<c:out value='${command.srch_state}' escapeXml='false'/>" />						<!-- 상태 -->
				<input type="hidden" name="srch_cnclsnpurps_bigclsfcn" value="<c:out value='${command.srch_cnclsnpurps_bigclsfcn}' escapeXml='false'/>" />	<!-- 체결목적-->			
				<input type="hidden" name="isOrgMgr" value="<c:out value='${command.isOrgMgr}' escapeXml='false'/>" />	<!-- 2012/07/12 추가. 원본관리를 위한 구분자-->
				
				<!-- 13/10/29 검색 조건 유지 추가 -->
				<input type="hidden" name="srch_cntrt_nm" value="<c:out value='${command.srch_cntrt_nm}'/>" />						<!-- Contract Title -->
				<input type="hidden" name="srch_cntrt_no" value="<c:out value='${command.srch_cntrt_no}'/>" />						<!-- Contract ID -->
				<input type="hidden" name="srch_tnc_no" value="<c:out value='${command.srch_tnc_no}'/>" />						<!-- srch_tnc_no -->
				<input type="hidden" name="srch_str_org_acptday" value="<c:out value='${command.srch_str_org_acptday}'/>" />		<!-- Original Copy -->
				<input type="hidden" name="srch_end_org_acptday" value="<c:out value='${command.srch_end_org_acptday}'/>" />		<!-- Original Copy -->

				<input type="hidden" name="method" id="method" value="" />
				<input type="hidden" name="menu_id" id="menu_id" value="<c:out value='${command.menu_id}'/>" />
				<input type="hidden" name="curPage" id="curPage" value="<c:out value='${command.curPage}'/>" />
				<input type="hidden" name="page_gbn" id="page_gbn" value="<c:out value='${command.page_gbn}'/>" />
				<input type="hidden" name="user_role" id="user_role" value="<c:out value='${command.user_role}'/>" />

				<!-- 계약추가 증가데이타 -->
				<input type="hidden" name="cnsdreq_id" id="cnsdreq_id" value="<c:out value='${lomReq.cnsdreq_id}'/>" />
				<input type="hidden" name="cntrt_id" id="cntrt_id" value="<c:out value='${command.cntrt_id}'/>" />
				<input type="hidden" name="agreeman_id" id="agreeman_id" value="<c:out value='${lomReq.agreeman_id}'/>" />
				<input type="hidden" name="prgrs_status" id="prgrs_status" value="<c:out value='${lomReq.prgrs_status}'/>" />
				<input type="hidden" name="cntrt_untprc_expl" id="cntrt_untprc_expl" value="<c:out value='${lomReq.cntrt_untprc_expl}'/>" />

				<!-- Sungwoo added search parameter 2014-06-12 -->
				<input type="hidden" name="srch_division" value="<c:out value='${command.srch_division}'/>" />					<!-- Original Copy -->
				<input type="hidden" name="srch_vendor_type" value="<c:out value='${command.srch_vendor_type}'/>" />					<!-- Original Copy -->
				<input type="hidden" name="srch_vendor_type_detail" value="<c:out value='${command.srch_vendor_type_detail}'/>" />		<!-- Original Copy -->
				

					<!-- 첨부파일정보 시작 -->
					<input type="hidden" name="fileInfos" value="" />
					<!-- 계약서 파일 정보 -->
					<input type="hidden" name="fileInfos2" value="" />
					<!-- 기타 파일 정보 -->
					<input type="hidden" name="fileInfos3" value="" />
					<!-- 사전검토 파일 정보 -->
					<input type="hidden" name="fileInfos4" value="" />
					<!-- 단가 파일 정보 -->
					<input type="hidden" name="fileInfos5" value="" />
					<input type="hidden" name="fileInfos6" value="" />
					<input type="hidden" name="fileInfos10"  value="" /> <!-- 종료관리 첨부파일정보 -->
					<input type="hidden" name="fileInfos33" value="" />
					<!-- 별첨 파일 정보 -->
					<input type="hidden" name="file_bigclsfcn" value="F012" />
					<!-- 대분류 -->
					<input type="hidden" name="file_midclsfcn" value="" />
					<!-- 중분류 -->
					<input type="hidden" name="file_smlclsfcn" value="" />
					<!-- 소분류 -->
					<input type="hidden" name="ref_key" value="" />
					<!-- 키값 -->
					<input type="hidden" name="view_gbn" value="download" />
					<!-- 첨부파일 화면 -->
					<input type="hidden" name="fileInfoName" value="" />
					<!-- 저장될 파일 정보 -->
					<input type="hidden" name="fileFrameName" value="" />
					<!-- 소분류 -->
					<input type="hidden" name="multiYn" value="" />
					<!-- 멀티여부 -->

					<!-- 첨부파일정보 시작 -->
					<input type="hidden" name="module_id" id="module_id" value="<c:out value='${lomReq.module_id}'/>" />
					<input type="hidden" name="mis_id" id="mis_id" value="<c:out value='${lomReq.mis_id}'/>" />
				    <input type="hidden" name="mis_id2" id="mis_id2"/>

					<!-- title -->
					<div class="title_basic mt20">
						<h4><spring:message code="clm.page.msg.manage.contInfo" /></h4>
					</div>
					
					<div class="btnwrap mt_22" id="myapproval_btn" style="display: none;">
						<span class="btn_all_w"> <span class="contract"></span><a href="javascript:goModify();">
							<c:choose>
							<c:when test="${cntrtLom.depth_status == 'C02642'}"><spring:message code="clm.page.msg.manage.rcvOrg" /></c:when>
							<c:otherwise><spring:message code="clm.page.msg.manage.write" /></c:otherwise>
							</c:choose>
							</a> </span>
							<span class="btn_all_w"><span class="print"></span><a href="javascript:openPrint();"><spring:message code="clm.page.msg.manage.printCover" /></a></span>
							<span class="btn_all_w"> <span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /> </a> </span>
					</div>

					<div class="btnwrap mt_22" id="myapproval_btn_list" style="display: none;">
						<span class="btn_all_w"> <span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /> </a> </span>
					</div>
					
					<!-- toptable-->
					<div id="contract-master-info"></div>
					
					<!-- 종료정보 -->
					<div id="contract-CompletionInfo"></div>
					
					<!-- 이력정보start -->
					<div id="contractHis-list"></div>
					
					<!-- 이력정보end -->
					<div class="t_titBtn">
						<div class="btn_wrap_c" id="myapproval_btn" style="display: none;">
							<span class="btn_all_w"><span class="contract"></span><a href="javascript:goModify();">
								<c:choose>
								<c:when test="${cntrtLom.depth_status == 'C02642'}"><spring:message code="clm.page.msg.manage.rcvOrg" /></c:when>
								<c:otherwise><spring:message code="clm.page.msg.manage.write" /></c:otherwise>
								</c:choose>
								</a> </span>
								<span class="btn_all_w"><span class="print"></span><a href="javascript:openPrint();"><spring:message code="clm.page.msg.manage.printCover" /></a></span>
								<span class="btn_all_w"><span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /> </a> </span>
						</div>
						<div class="btn_wrap_c" id="myapproval_btn_list" style="display: none;">
							<span class="btn_all_w"><span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /></a> </span>
						</div>
					</div>
				</form:form>
				</div>
			</div>
			<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
		</div>
	</div>
	<!-- footer  -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
	<!-- // footer -->
</body>
</html>