<!DOCTYPE html>

<%@page import="java.math.BigDecimal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ include file= "/WEB-INF/jsp/common/common.jsp" %>

<%-- 
/**
 * 파  일  명 : CompletionApproval_d.jsp
 * 프로그램명 : 자동연장 상세화면
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2013.04
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	
	//===================================================================
	//검토이력, 승인이력 관련 시작
	//===================================================================
	ArrayList review	= (ArrayList)request.getAttribute("review");
	ArrayList approve 	= (ArrayList)request.getAttribute("approve");
	ArrayList info 		= (ArrayList)request.getAttribute("info");
	//===================================================================
	//검토이력, 승인이력 관련 끝
	//===================================================================
	
	//===================================================================
	//자동연장 관련 시작
	//===================================================================	
	ArrayList orgMngHistory 	= (ArrayList)request.getAttribute("orgMngHistory");
	String cntrtperiod_endday 	= "";
	String mod_cause 			= "";
	int seqno 	= 0;
	int hisLen 	= 0;
	
	if(null != orgMngHistory){
		hisLen = orgMngHistory.size();
	}
	
	if(hisLen > 0){
		cntrtperiod_endday 	= (String)((ListOrderedMap)orgMngHistory.get(hisLen-1)).get("cntrtperiod_endday");
		mod_cause 			= (String)((ListOrderedMap)orgMngHistory.get(hisLen-1)).get("org_mod_cause");
		seqno 				= ((BigDecimal)((ListOrderedMap)orgMngHistory.get(hisLen-1)).get("seqno")).intValue();
	}
	
	ListOrderedMap contractMstLom = (ListOrderedMap)request.getAttribute("contractMstLom");
	//===================================================================
	//자동연장 관련 끝
	//===================================================================

%>
<html>

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />

<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet"></link>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="/script/clms/common.js" type="text/javascript"></script>
<script language="javascript" src="/script/clms/clms_common.js" type="text/javascript"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>

<script language="javascript"  >

$(document).ready(function(){
	// 2014-04-17 Kevin Added.
    // GERP readonly iframe load. 
	var frm = document.frm;
    frm.target          = "iframeGERP";
    frm.action          = "<c:url value='/clm/manage/consideration.do' />";
    frm.gerpPageType.value = "R";		// detail readonly
    frm.method.value    = "forwardGERPDetail";
	frm.submit();
	
	contractHisList();		//Sungwoo added 2014-09-15
});

//거래선 팝업
function customerPop2(customerCd, dodun){
	PopUpWindowOpen("/clm/draft/customerTest.do?method=detailCustomerShare&customer_cd="+customerCd + "&dodun=" + dodun, 900, 300, false);
}
//TOP 30 팝업
function openTop30Customer(){
	   PopUpWindowOpen2("/clm/draft/customerTest.do?method=listTop30Customer", 500, 540, false, "Top30Customer");
}
/**
* 목록
*/
function listManageCompletion(){
	viewHiddenProgress(true);
	var frm = document.frm;

	frm.target = "_self";		 
	frm.action = "<c:url value='/clm/manage/completion.do' />";
	frm.method.value = "listAutoRenewApproval";
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

//승인/반려 창 띄우기
function openProcessInfoLayer(tmp,seq){
	
	if(tmp == '1'){//승인
		if(seq == '1'){
			$("#div_app_info").css("top", $("#approval_btn").offset().top +25);
			$("#div_app_info").css("left", $("#approval_btn").offset().left - 1200 );
		}else{
			$("#div_app_info").css("top", $("#approval_btn_b").offset().top -400);
			$("#div_app_info").css("left", $("#approval_btn_b").offset().left - 650 );
		}
		
		$("#div_app_info").show();
	}else{
		if(seq == '1'){
			$("#div_rej_info").css("top", $("#approval_btn").offset().top +25);
			$("#div_rej_info").css("left", $("#approval_btn").offset().left - 1200 );
		}else{
			$("#div_rej_info").css("top", $("#approval_btn_b").offset().top -400);
			$("#div_rej_info").css("left", $("#approval_btn_b").offset().left - 650 );
		}
		$("#div_rej_info").show();
	}
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
function showCompletionPop(){
	
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
	frm.target = "popUpHistory";
	frm.submit();
}

/*
 * 승인이력 - [종료] 상세 팝업 띄우기
 */
function openHis2() {
	var frm = document.frm;
	frm.conListGu.value = "preview";
	var options = {
 		url: "<c:url value='/clm/manage/completion.do?method=listContract' />",
 		type: "post",
 		async: false,
 		dataType: "html",
 			
 		success: function(data){
 			frm.contents.value=data;
 			showCompletionPop(); 			
 		}
   };
   $("#frm").ajaxSubmit(options);
}

//팝업오픈
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
</script>
</head>
<body>
				<style>
					.title_basic h4, 
					*html .title_basic h4 {padding:8px 10px 2px 18px; background-position:3px 11px}
					.mt20 {margin-top:20px;}
					.table-style01 th {text-align:center}
					.tableWrap {width:100%; clear:both;}
				    .tableone {margin-right:17px; _width:100%; }
				    *:first-child+html .tableone {margin-right:0px; }
				    .tabletwo {overflow-y:scroll; overflow-x:hidden; table-layout:fixed;}
				   
					.table-style_sub03 {width:99%; _border-left:1px solid #fff; margin:0; zoom:1; table-layout:fixed;}
					.table-style_sub03 th {line-height:150%; text-align:left; padding:3px 10px; background:#f6f6f6; border:1px solid #dddddd; color:#647996; font-weight:normal;border-right:0;}
					.table-style_sub03 td {line-height:150%;text-align:left; padding:3px 10px; border-left:1px solid #dddddd; border-bottom:1px solid #dddddd; border-top:1px solid #dddddd; color:#7d848a;}
					.table-style_sub03 .blank {border:1px solid #fff}
					.table-style_sub03 .blank_side {border-left:1px solid #fff;}
					.table-style_sub03 .topline {border-top:1px solid #7697c2;}
					
					.divModal {width:100%; height:100%; position:absolute; z-index:10; left:0px; top:0px;  overflow:hidden}
				    .divModal .inWrap {position:relative; width:350px; left:50%; border:1px solid #00809D; margin: 30px 0 0 -175px; background:#fff}
				    .divModal .header {position:relative; width:100%; height:40px; background:url(<%=IMAGE%>/new2011/bg_pop_header.gif) left top repeat-x;}
				    .divModal .header h1 {padding-left:7px; line-height:28px !important;height:35px; color:#fff; font:bold 14px Malgun Gothic; text-indent:12px;}
				    .divModal .header .close {cursor:pointer; position:absolute;top:6px;right:10px;background:url(<%=IMAGE%>/icon/ico_close.gif) no-repeat left center; width:19px; height:17px;}
				    .divModal .conWrap {padding:10px 16px 15px 18px;overflow:hidden}
				    :root .divModal .conWrap {padding:10px 14px 15px 18px;overflow:hidden}
				    .divModal .conWrap .btnWrap {float:right; margin:10px 0px 0px 0}
				    *html .divModal .conWrap .btnWrap {float:right; margin:0px 17px 10px 0}
				    :root .divModal .conWrap .btnWrap {float:right; margin:10px 2px 0px 0}
				</style>

<div id="wrap">
<!-- container -->
<div id="container">
	<!-- location -->
	<div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
	<!-- //location -->
	<!-- title -->
	<div class="title">
		<h3><spring:message code="clm.page.msg.common.appAutoExt" /></h3>
	</div>
	<!-- //title -->
	<!-- content -->
	<div id="content">
	<div id="content_in">
	<!-- 

**************************** Form Setting **************************** 
-->
<form:form name="frm" id='frm' method="post" autocomplete="off">
	<!-- required Form -->
	<input type="hidden" name="method"   value="" />
	<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
	<!-- key hidden Form -->
	<input type="hidden" name="cnsdreq_id"	id="cnsdreq_id"   value="<c:out value='${command.cnsdreq_id}'/>" />
	<input type="hidden" name="cntrt_id"		id="cntrt_id"     value="<c:out value='${command.cntrt_id}'/>" />
	<input type="hidden" name="reqman_id"		id="reqman_id"     value="<c:out value='${command.reqman_id}'/>" />
	<input type="hidden" name="conListGu" value="<c:out value='${command.conListGu}'/>" />
	<input type="hidden" name="cntrt_nm"	 value="<c:out value='${contractReqLom.cntrt_nm}' />" />
	<input type="hidden" name="req_title"	value="<c:out value='${contractReqLom.req_title}' />" />
	<input type="hidden" name="req_dt"	value="<c:out value='${completionLom.cntrt_chge_demndday}' />" />
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
	<input type="hidden" name="chose_client" value="<c:out value='${reqAuthFormInfo}'/>" />
	<input type="hidden" name="authreq_client" value="<c:out value='${reqAuthSvcInfo}'/>" />
	<input type="hidden" id="hidden_reqAuthInfo" name="hidden_reqAuthInfo" value="<c:out value='${reqAuthInfo}'/>" />
	<!-- //계약상세관련 -->
	<!-- 계약연장승인관련 -->
	<input type="hidden" name="prcs_depth"  />
	<input type="hidden" name="depth_status"  />
	<input type="hidden" name="cntrt_chge_conf_cont"  />
	<input type="hidden" name="prgrs_status"  />
	<input type="hidden" name="cntrt_chge_conf_yn"  />
	<input type="hidden" name="seqno" value="<%=seqno %>" />
	<input type="hidden" name="gubun"  />
	<input type="hidden" name="cntrtperiod_endday" value="<%=cntrtperiod_endday %>" />
	<%-- 신성우 주석처리 2014-04-01<input type="hidden" name="exprt_notiday" value="<%=exprt_notiday %>" /> --%>
	<input type="hidden" name="exprt_notiday" value="" />
	<!-- //계약연장승인관련 -->
	<!-- forward page -->
	<input type="hidden" name="targetMenuId" />
	<input type="hidden" name="selected_menu_id" />
	<input type="hidden" name="selected_menu_detail_id" />
	<input type="hidden" name="set_init_url" />
	<input type="hidden" name="selected_paygbn"	id="selected_paygbn"    value="" />
	<!--// forward page -->
	<!-- //html Contents -->
	<input type="hidden" name="contents"		id="contents"     value="" />
	<!-- 리스트 검색 조건시작  -->
	<input type="hidden" name="list_mode" value="<c:out value='${command.list_mode}'/>"/>
	<input type="hidden" name="srch_resp_dept" value="<c:out value='${command.srch_resp_dept}' />"/>				<!-- 담당부서코드 -->
	<input type="hidden" name="srch_oppnt_cd" value="<c:out value='${command.srch_oppnt_cd}' />"/>   				<!-- 계약상대방코드 -->
	<input type="hidden" name="srch_review_title" value="<c:out value='${command.srch_review_title}' />" />		<!-- 의뢰명 -->
	<input type="hidden" name="srch_reqman_nm" value="<c:out value='${command.srch_reqman_nm}' />" />				<!-- 의뢰자 -->
	<input type="hidden" name="srch_start_reqday" value="<c:out value='${command.srch_start_reqday}'/>" />		<!-- 의뢰 시작일 -->
	<input type="hidden" name="srch_end_reqday" value="<c:out value='${command.srch_end_reqday}' />" />			<!-- 의뢰 종료일 -->
	<input type="hidden" name="srch_start_cnlsnday" value="<c:out value='${command.srch_start_cnlsnday}'/>" />	<!-- 계약 시작일 -->
	<input type="hidden" name="srch_end_cnlsnday" value="<c:out value='${command.srch_end_cnlsnday}'/>" />		<!-- 계약 종료일 -->
	<input type="hidden" name="srch_resp_dept_nm" value="<c:out value='${command.srch_resp_dept_nm}'/>" />		<!-- 담당부서명 -->
	<input type="hidden" name="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}'/>" />			<!-- 담당자명 -->
	<input type="hidden" name="srch_biz_clsfcn" value="<c:out value='${command.srch_biz_clsfcn}'/>" />			<!-- 계약단계 -->
	<input type="hidden" name="srch_cnsdman_nm" value="<c:out value='${command.srch_cnsdman_nm}'/>" />			<!-- 검토자 -->
	<input type="hidden" name="srch_oppnt_nm" value="<c:out value='${command.srch_oppnt_nm}'/>" />				<!-- 계약상대방 -->
	<input type="hidden" name="srch_state" value="<c:out value='${command.srch_state}' />" />						<!-- 상태 -->
	<input type="hidden" name="srch_cnclsnpurps_bigclsfcn" value="<c:out value='${command.srch_cnclsnpurps_bigclsfcn}'/>" />	<!-- 체결목적-->
	<input type="hidden" name="ismycontract" id="ismycontract" value="<c:out value='${command.ismycontract}'/>"/>
	<input type="hidden" name="srch_step" value="<c:out value='${command.srch_step}'/>" />						<!-- 상태 -->
	<input type="hidden" name="mis_id"/>
	<!-- 2014-04-17 Kevin added. GERP 관련 페이지 형태 구분.(R/I) -->
	<input type="hidden" name="gerpPageType" id="gerpPageType" />
<!-- //
**************************** Form Setting **************************** 
-->
			<!-- 승인 창 -->
			    <div class='divModal' id='div_app_info' style="display:none">
				  <div class='inWrap'>
				    <div class='header'>
				        <h1><spring:message code="clm.page.msg.manage.apprOpin" /><span class="close" onclick="divHidden(div_app_info)" title="close"></span></h1>
				       </div>
				       
				       <div class='conWrap'>
				     <textarea name="app_info" id="app_info" class="text_full" cols="as" rows="6" style=' height:200px'></textarea>
				                 <!-- Button -->
				     <div class="btnWrap">
				      <span class="btn_all_b"><span class="save"></span><a href="javascript:goApproval();"><spring:message code="clm.page.msg.manage.approval" /></a></span>
				      <span class="btn_all_b"><span class="close"></span><a href="javascript:divHidden(div_app_info);"><spring:message code="clm.page.msg.common.close" /></a></span>
				     </div>
				     <!-- // Button -->
				    </div>
				  </div>   
				  </div>
			    <!-- 반려 창 -->
			    <div class='divModal' id='div_rej_info' style="display:none">
				   <div class='inWrap'>
				    <div class='header'>
				        <h1><spring:message code="clm.page.msg.manage.opiOfReturn" /><span class="close" onclick="divHidden(div_rej_info)" title="close"></span></h1>
				       </div>
				       
				       <div class='conWrap'>
				     <textarea name="reject_info" id="reject_info" class="text_full" cols="as" rows="6" style=' height:200px'></textarea>
				                 <!-- Button -->
				     <div class="btnWrap">
				      <span class="btn_all_b"><span class="save"></span><a href="javascript:goReject();"><spring:message code="clm.page.msg.common.return" /></a></span>
				      <span class="btn_all_b"><span class="close"></span><a href="javascript:divHidden(div_rej_info);"><spring:message code="clm.page.msg.common.close" /></a></span>
				     </div>
				     <!-- // Button -->
				    </div>
				       
				      </div>
				  </div>
			<div class="content-step t_titBtn">
				<ol>
					<li><img src="<%=IMAGE %>/common/step01.gif"  	 alt="<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" />" /></li>
					<li><img src="<%=IMAGE %>/common/step02.gif"     alt="<spring:message code="clm.page.msg.manage.step02" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step02" htmlEscape="true" />" /></li>
					<li><img src="<%=IMAGE %>/common/step03.gif"     alt="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" /></li>
					<li><img src="<%=IMAGE %>/common/step04.gif"     alt="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" /></li>
					<li class='step_on'>
						<img src="<%=IMAGE %>/common/step05_on.gif"  alt="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" />
						<div class='step_link'><a href="javascript:open_window('win', '../../step05<%=langCd.equals("ko")?"":"_"+langCd %>.html', 0, 0, 696, 588, 0, 0, 0, 0, 0)" ><img src="<%=IMAGE %>/common/step_q.gif" alt="<spring:message code="clm.page.msg.manage.procStep"/>" title="<spring:message code="clm.page.msg.manage.procStep"/>" /></a></div>
					</li>
				</ol>
			</div>
			
				<div style='width:100%; margin-bottom:7px; overflow:hidden'>
					<!-- 계약정보 -->
					<div class="title_basic" style='width:100%; float:left'>
						<h4><spring:message code="clm.page.msg.manage.contInfo" /></h4>
					</div>
					
					<!-- button -->
					<div class="btn_wrap_t02 fR" style='margin-top:-20px'>
						<span class="btn_all_w" id="approval_btn"><span class="tsave"></span><a href="javascript:openProcessInfoLayer('1','1');"><spring:message code="clm.page.msg.manage.approval" /></a></span>
						<span class="btn_all_w"  id="reject_btn"><span class="tsave"></span><a href="javascript:openProcessInfoLayer('2','1');"><spring:message code="clm.page.msg.common.return" /></a></span>
						<span class="btn_all_p ml_10"><span class="list"></span><a href="javascript:listManageCompletion();"><spring:message code="clm.page.msg.manage.list" /></a></span>
					</div>
					<!-- //button -->
				</div>				
				
				<div class='title_basic3'><spring:message code="clm.page.msg.manage.bscInf" /></div>
				<table class="table-style01">
					<colgroup>
						<col width="12%" />
						<col width="8%" />
						<col width="14%" />
						<col width="12%" />
						<col width="21%" />
						<col width="12%" />
						<col width="21%" />
					</colgroup>
					<tr>
						<th><spring:message code="clm.page.msg.manage.reqName" /></th>
					    <td colspan="6"><c:out value="${contractReqLom.req_title}" /></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.manage.contName" /></th>
						<td colspan='4'><c:out value="${contractReqLom.cntrt_nm}" /></td>
						<th><spring:message code="clm.page.msg.manage.contId" /></th>
						<td><c:out value="${contractMstLom.cntrt_no}" /></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.manage.reqPerson" /></th>
						<td colspan="2"><c:out value="${contractReqLom.reqman_nm}" /></td>
						<th><spring:message code="clm.page.msg.manage.chrgPerson" /></th>
						<td><c:out value="${contractReqLom.cntrt_respman_nm}" /></td>
						<th><spring:message code="clm.page.msg.manage.reviewer" /></th>
						<td><c:out value="${contractReqLom.cnsdman_nm}" /></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.common.otherParty" /></th>
						<td colspan="2"><a href="javascript:customerPop2('<c:out value="${contractMstLom.cntrt_oppnt_cd}" />', '<c:out value="${contractMstLom.cntrt_oppnt_cd}" />');"><c:out value="${contractMstLom.cntrt_oppnt_nm}" /></a></td>
						<th><spring:message code="clm.page.field.customer.registerNo" /></th>
						<td><c:out value="${contractMstLom.cntrt_oppnt_ceo}" /></td>
						<th><spring:message code="clm.page.field.customer.contractRequired" /></th>
						<td><c:out value="${contractMstLom.cntrt_oppnt_type_nm}" /></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.manage.contItm" /></th>
						<td colspan="2"><c:out value="${contractMstLom.cntrt_trgt_nm}" /></td>
						<th><spring:message code="clm.page.msg.manage.contItmDtl" /></th>
						<td colspan="3"><c:out value="${contractMstLom.cntrt_trgt_det}" /></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.manage.contType" /></th>
						<td colspan="6"><c:out value='${contractMstLom.biz_clsfcn_nm}'/>/<c:out value='${contractMstLom.depth_clsfcn_nm}'/>/<c:out value='${contractMstLom.cnclsnpurps_bigclsfcn_nm}'/>/<c:out value='${contractMstLom.cnclsnpurps_midclsfcn_nm}'/></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.manage.top30Cus" /><img src="<%=IMAGE%>/icon/ico_search2.gif" class="cp" onClick="javascript:openTop30Customer();" alt="search" style="padding-left:5px" title="Top 30 Customer List"/></th>
						<td colspan="2"><c:out value="${contractMstLom.top_30_cus_nm}" /></td>
						<th><spring:message code="clm.page.msg.manage.relatedPrd" /></th>
						<td><c:out value="${contractMstLom.related_products_nm}" escapeXml="false"/></td>
						<th><spring:message code="clm.page.msg.manage.srcContDraft" /></th>
						<td><c:out value="${contractMstLom.cont_draft_nm}" /></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.manage.contPeriod" /></th>
						<td colspan="2"><c:out value="${contractMstLom.cntrtperiod_startday}" /> ~ <c:out value="${contractMstLom.cntrtperiod_endday}" /></td>
						<th><spring:message code="clm.page.field.contract.consultation.detail.autorenew" /></th>
						<td>
						<c:if test="${contractMstLom.auto_rnew_yn=='Y'}">Yes</c:if>
						<c:if test="${contractMstLom.auto_rnew_yn=='N'}">No</c:if>
						</td>
						<th><spring:message code="clm.page.msg.manage.sendRcvDiv" /></th>
						<td><c:out value="${contractMstLom.payment_gbn_nm}" /></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.manage.contAmt" /></th>
						<td colspan='4'>
						<input type="text" class="text" style="border:0px solid #fff;" value="<c:out value="${contractMstLom.cntrt_amt}" />" />
						<c:if test="${!empty contractMstLom.cntrt_untprc_expl}"><input type='checkbox' class='checkbox' checked disabled /> <spring:message code="clm.page.msg.manage.conclSingleAmt" /></c:if>
						 </td>
						<th><spring:message code="clm.page.msg.manage.curr" /></th>
						<td><c:out value="${contractMstLom.crrncy_unit}" /></td>
					</tr>
					<c:if test="${!empty contractMstLom.cntrt_untprc_expl}">
					<tr>
						<th><spring:message code="clm.page.msg.manage.contUnitPriceSum" /></th>
						<td colspan='6'>
							<%=StringUtil.convertEnterToBR(StringUtil.bvl((String)contractMstLom.get("cntrt_untprc_expl"),""))%>
						</td>
					</tr>
					</c:if>
					<tr>
						<th><spring:message code="clm.page.msg.manage.bg" /></th>
						<td colspan="6"><c:out value="${contractMstLom.pshdbkgrnd_purps}" escapeXml="false" />
						</td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.manage.etcMain" /></th>
						<td colspan="6">
						<c:out value="${contractMstLom.etc_main_cont}"  />
						<c:if test="${!empty contractMstLom.if_sys_cd}"> [<c:out value="${contractMstLom.if_sys_cd}" />]</c:if>
						</td>
					</tr>
					<!-- 특화 정보 -->
		            <c:forEach var="list" items="${considerationSpecialList}">
			            <c:if test="${!empty list.attr_value}">
			            <tr>    
			                <th><c:out value="${list.attr_nm}"/></th>
			                <td colspan="6"><c:out value="${list.attr_value}" escapeXml="false"/></td>
			            </tr>
			            </c:if> 
			        </c:forEach>
	
			        <c:forEach var="list" items="${consultationSpecialList}">
			            <c:if test="${!empty list.attr_value}">
			            <tr>
			                <th><c:out value="${list.attr_nm}"/></th>
			                <td colspan="6"><c:out value="${list.attr_value}" escapeXml="false"/></td>
			            </tr>
			            </c:if>
			        </c:forEach>
			        
			        <!-- 특화 정보 끝 -->
			        
			        <!-- 배상책임한도 -->
			        <c:if test='${!empty contractMstLom.oblgt_lmt}'>
		            <tr>
		                <th><spring:message code="clm.page.field.contract.consultation.detail.obligationlimit"/></th>
		                <td colspan="6">
		                    <c:out value='${contractMstLom.oblgt_lmt}' escapeXml="false"/>
		                </td>
		            </tr>
		            </c:if>
		            
		            <!-- 기타 특약사항 -->
		            <c:if test='${!empty contractMstLom.spcl_cond}'>
		            <tr>
		                <th><spring:message code="clm.page.msg.manage.etcSpecial" /></th>
		                <td colspan="6"><c:out value='${contractMstLom.spcl_cond}' escapeXml="false"/></td>
		            </tr>
		            </c:if>
		            
		            <!-- 준거법 / 준거법상세 -->
		            <tr>
		                <th><spring:message code="clm.page.field.contract.detail.loac"/></th>
		                <td colspan="2"><c:out value='${contractMstLom.loac}'/></td>
		                <th><spring:message code="clm.page.field.contract.detail.loacdetail"/></th>
		                <td colspan="3"><c:out value='${contractMstLom.loac_etc}' escapeXml="false"/></td>
		            </tr>
		            <!-- 분쟁해결방법 / 분쟁해결방법 상세 -->
		            <tr>
		                <th><spring:message code="clm.page.field.contract.detail.solutionmethod"/></th>
		                <td colspan="2"><c:out value='${contractMstLom.dspt_resolt_mthd}' escapeXml="false"/></td>
		                <th><spring:message code="clm.page.field.contract.detail.solutionmethoddetail"/></th>
		                <td colspan="3"><c:out value='${contractMstLom.dspt_resolt_mthd_det}' escapeXml="false"/></td>
		            </tr>
					<tr>
						<th rowspan="3"><spring:message code="clm.page.msg.common.attachment" /></th>
						<td><span class="blueD"><spring:message code="clm.page.msg.manage.contract" /></span></td>
						<td colspan="5">
							<iframe src="<c:url value='/clm/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
						</td>
					</tr>
					<tr>
						<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.manage.attachment" /></span></td>
						<td colspan="5">
							<iframe src="<c:url value='/clm/blank.do' />" id="fileList3" name="fileList3" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
						</td>
					</tr> 
					<tr>
						<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.common.etc" /></span></td>
						<td colspan="5">
							<iframe src="<c:url value='/clm/blank.do' />" id="fileList2" name="fileList2" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
						</td>
					</tr>
				</table>	
				
				<!-- 2014-04-17 Kevin Added. GERP Information -->
            	<iframe id="iframeGERP" name="iframeGERP" src="/clm/blank.do" style="border:0; width:100%; height:115px; margin:10px 0 10px 0;" scrolling="no"></iframe>
				
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
							<iframe src="<c:url value='/clm/blank.do' />" id="fileList33" name="fileList33" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
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
						<th class="tC"><spring:message code="clm.page.msg.manage.relation" /></th>
						<th class="tC"><spring:message code="clm.page.msg.manage.relCont" /></th>
						<th class="tC"><spring:message code="clm.page.msg.manage.define" /></th>
						<th class="tC"><spring:message code="clm.page.msg.manage.relDetail" /></th>
					</tr>
						<c:choose>
						<c:when test ="${!empty contractRelationLom}">
							<c:forEach var="list" items="${contractRelationLom}" varStatus="i">
								<tr>
									<td><c:out value='${list.rel_type_nm}'/></td>
									<td><c:out value='${list.relation_cntrt_nm}'/></td>
									<td><c:out value='${list.rel_defn}'/></td>
									<td><c:out value='${list.expl}'/></td>
								</tr>		
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr><td colspan="4" align="center"><spring:message code="clm.msg.succ.noResult" /></td></tr>
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
						<col width="21%" />
						<col width="12%" />
						<col width="21%" />
					</colgroup>
					<tr>
						<th><spring:message code="clm.page.msg.manage.conclConf" /></th>
						<td>
						<c:choose>
						<c:when test="${contractMstLom.cntrt_cnclsn_yn=='Y'}">Yes</c:when>
						<c:otherwise>No</c:otherwise>
						</c:choose>	
						</td>
						<th><spring:message code="clm.page.msg.manage.conclResDate" /></th>
						<td><c:out value='${contractMstLom.cnclsn_plndday}'/></td>
						<th><spring:message code="clm.page.msg.manage.contConclDt" /></th>
						<td><c:out value='${contractMstLom.cntrt_cnclsnday}'/></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.manage.signDiv" /></th>
						<td><c:out value='${contractMstLom.seal_mthd_nm}'/></td>
						<c:choose>
						<c:when test="${contractMstLom.seal_mthd eq 'C02102'}">
						<th><spring:message code="clm.page.msg.manage.signChrgMan" /></th>
						<td colspan='3'><c:out value='${contractMstLom.sign_plndman_nm}'/>/<c:out value='${contractMstLom.sign_plndman_jikgup_nm}'/>/<c:out value='${contractMstLom.sign_plnd_dept_nm}'/></td>
						</c:when>
						<c:otherwise>
						<th><spring:message code="clm.page.msg.manage.signManager" /></th>
						<td colspan='3'><c:out value='${contractMstLom.seal_ffmtman_nm}'/>/<c:out value='${contractMstLom.seal_ffmtman_jikgup_nm}'/>/<c:out value='${contractMstLom.seal_ffmt_dept_nm}'/></td>
						</c:otherwise>
						</c:choose>	
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.manage.chrgCont" /></th>
						<td><c:out value='${contractMstLom.cntrt_respman_nm}'/>/<c:out value='${contractMstLom.cntrt_respman_jikgup_nm}'/>/<c:out value='${contractMstLom.cntrt_resp_dept_nm}'/></td>
						<%-- 신성우 주석처리 2014-04-01
						<th><spring:message code="clm.page.msg.manage.preAnnDate" /></th>
						<td colspan='3'><c:out value='${contractMstLom.exprt_notiday}'/></td>
						 --%>
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
						<td colspan="3"><iframe src="<c:url value='/clm/blank.do' />" id="fileList4" name="fileList4" frameborder="0" width="100%" height="50px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe></td>
					</tr>
				</table>
				
				<div class='title_basic3'><spring:message code="clm.page.msg.manage.orgInf" /></div>
				<table class="table-style01">
					<colgroup>
						<col width="12%" />
						<col width="22%" />
						<col width="12%" />
						<col width="54%" />
					</colgroup>
					<tr>
						<th><spring:message code="clm.page.msg.manage.orgSendPer" /></th>
						<td><c:out value='${contractMstLom.org_hdovman_nm}'/>/<c:out value='${contractMstLom.org_hdovman_jikgup_nm}'/>/<c:out value='${contractMstLom.org_hdov_dept_nm}'/></td>
						<th><spring:message code="clm.page.msg.manage.orgRcvDate" /></th>
						<td><c:out value='${contractMstLom.org_acptday}'/></td>
						
					</tr>
					<tr>
						<th><spring:message code="las.page.field.contractManager.groupChief" /></th>
						<td><c:out value='${contractMstLom.org_tkovman_nm}'/>/<c:out value='${contractMstLom.org_tkovman_jikgup_nm}'/>/<c:out value='${contractMstLom.org_tkov_dept_nm}'/></td>
						<th><spring:message code="clm.page.msg.manage.orgPlace" /></th>
						<td><c:out value='${contractMstLom.org_strg_pos}'/></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.common.memo" /></th>
						<td colspan='3'>
							<%=StringUtil.convertEnterToBR(StringUtil.bvl((String)contractMstLom.get("org_acpt_dlay_cause"),""))%>
						</td>
					</tr>
				</table>
				<!-- // 계약상세내역 -->
			<!-- 이력정보 -->
			<div id="contractHis-list"></div>
			<!-- //이력정보 -->	
				<!-- 종료정보 -->
				<div class="title_basic" style='width:100%; margin:10px 0 0 0'> 
					<h4><spring:message code="clm.page.msg.manage.autoExpReqInf" /></h4>
				</div>

				<table class="table-style01">
					<colgroup>
						<col width="12%" />
						<col width="22%" />
						<col width="12%" />
						<col width="54%" />
					</colgroup>
					<tr>
						<th ><spring:message code="clm.page.msg.manage.reqPrsn" /></th>
						<td>
						<c:out value='${completionLom.cntrt_chge_demndman_nm}' />
							<c:if test="${!empty completionLom.cntrt_chge_demndman_jikgup_nm}">/</c:if>
						<c:out value='${completionLom.cntrt_chge_demndman_jikgup_nm}' />
							<c:if test="${!empty completionLom.cntrt_chge_demnd_dept_nm}">/</c:if>
						<c:out value='${completionLom.cntrt_chge_demnd_dept_nm}' /></td>
						<th ><spring:message code="clm.page.msg.manage.reqDt" /></th>
						<td colspan="3"><c:out value='${completionLom.cntrt_chge_demndday}' /></td>
					</tr>	
					<tr>
						<th><spring:message code="clm.page.msg.manage.orgContEndDt" /></th>
						<td><c:out value="${contractMstLom.cntrtperiod_endday}" /></td>
						<th><spring:message code="clm.page.msg.manage.expContEndDate" /></th>
						<td><!-- input type="text" name="req_endday" id="req_endday" class="text" style="border:0px solid #fff;" readonly /-->
						<%=cntrtperiod_endday %>
						</td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.manage.contEndDt" /><br><spring:message code="clm.page.msg.manage.rsnOfChange" /></th>
						<td colspan="3"><!-- input type="text" name="cntrt_chge_demnd_cause" id="cntrt_chge_demnd_cause" class="text" style="border:0px solid #fff;width:100%;" readonly/-->
						<%=mod_cause %>
						</td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.common.attachment" /></th>
						<td colspan="3">
							<iframe src="<c:url value='/clm/blank.do' />" id="fileList10" name="fileList10" frameborder="0" width="100%" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
						</td>
					</tr>
				</table>
				
				<!-- // 종료정보 -->
						
				<!-- 자동연장이력 -->
				<c:if test="${completionLom.auto_rnew_yn eq 'Y'}"><%//자동연장여부 'y'인 경우에만 보임 %>		
				<div class="title_basic" style='width:100%; margin:10px 0 0 0;'>
					<h4><spring:message code="clm.page.msg.manage.autoExpHstr" /> </h4>
				</div>
				
				<table class="table-style01" id="contrack-autoHistoryView" style="display:">
					<colgroup>
						<col width="12%" />
						<col width="15%" />
						<col width="15%" />
						<col />
						<col width="17%" />
					</colgroup>
					<tr>
						<th class="tC"><spring:message code="clm.page.msg.manage.seqNo" /></th>
						<th class="tC"><spring:message code="clm.page.msg.manage.expReqDate" /></th>
						<th class="tC"><spring:message code="clm.page.msg.manage.termDate" /></th>
						<th class="tC"><spring:message code="clm.page.msg.common.note" /></th>
						<th class="tC"><spring:message code="clm.page.msg.manage.apprPer" /></th>
					</tr>
					<c:set var="sum" value="0" />
						<c:choose>
						<c:when test ="${!empty orgMngHistory}">
							<c:forEach var="list" items="${orgMngHistory}" >
								<tr>
									<td class="tC"><c:out value='${list.rn}'/><spring:message code="clm.page.msg.manage.seq" /></td>
									<td class="tC"><c:out value='${list.cntrtperiod_startday}'/></td>
									<td class="tC"><c:out value='${list.cntrtperiod_endday}'/></td>
									<td><c:out value='${list.mod_cause}'/></td>
									<td class="tL txtover">
									<c:if test="${list.gubun eq 'A' or list.gubun eq 'J'}">
									<c:out value='${list.mod_nm}'/>/<c:out value='${list.mod_dept_nm}'/>/<c:out value='${list.mod_jikgup_nm}'/>
									</c:if>
									</td>
									<c:set var="sum" value="${sum + 1}" />
								</tr>		
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr><td colspan="5" align="center"><spring:message code="clm.page.msg.manage.announce141" /> </td></tr>
						</c:otherwise>
						</c:choose>	
				</table>
				</c:if>
				<!-- 자동연장이력 -->

				<!-- button -->
				<div class="t_titBtn">
      			<div class="btn_wrap_c">
					<span class="btn_all_w" id="approval_btn_b"><span class="tsave"></span><a href="javascript:openProcessInfoLayer('1','2');"><spring:message code="clm.page.msg.manage.approval" /></a></span>
					<span class="btn_all_w" id="reject_btn_b"><span class="tsave"></span><a href="javascript:openProcessInfoLayer('2','2');"><spring:message code="clm.page.msg.common.return" /></a></span>
					<span class="btn_all_p ml_10"><span class="list"></span><a href="javascript:listManageCompletion();"><spring:message code="clm.page.msg.manage.list" /></a></span>
				</div>
				</div>
				<!-- //button -->

				
		</div>
		<!-- //content -->
		</form:form>
	</div>
</div>
</div>
<script language="javascript">
$(document).ready(function(){
	attachPage('<c:out value="${contractMstLom.cntrt_id}" />');
	attachHisPage();
	
	$('#max_exec_num').val("<c:out value='${max_exec_num}'/>");


});
 	var collapseIcon = '<%=IMAGE%>/icon/ico_plus.gif';
 	var expandIcon = '<%=IMAGE%>/icon/ico_minus.gif';
 	
	//검토이력
	$('img[alt$=show]').toggle(function(){
		$(this).removeAttr().attr("src",expandIcon);
		$(this).parent().parent().parent().next('#tr_show').attr("style", "display:");	
		$("iframe[id^='final_cont']").each(function(i,o){
			var is_chrome = navigator.userAgent.toLowerCase().indexOf('chrome') > -1;
			if (is_chrome) {
				o.height = o.contentDocument.documentElement.scrollHeight + 30;	
			} else {
				o.height = o.contentWindow.document.body.scrollHeight + 30;	
			}
		});
	}, function(){
		$(this).removeAttr().attr("src",collapseIcon);
		$(this).parent().parent().parent().next('#tr_show').attr("style", "display:none");
	});
	//승인이력
	$('img[alt$=show01]').toggle(function(){
		$(this).removeAttr().attr("src",expandIcon);
		$(this).parent().parent().parent().next('#tr_show01').attr("style", "display:");
		
	}, function(){
		$(this).removeAttr().attr("src",collapseIcon);
		$(this).parent().parent().parent().next('#tr_show01').attr("style", "display:none");
	});

	//첨부파일
	function attachPage(temp_cntrt_id){

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
		frm.ref_key.value = temp_cntrt_id + "@<c:out value='${command.cnsdreq_id}'/>";
		
		frm.view_gbn.value = "download";
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
		frm.ref_key.value = temp_cntrt_id + "@<c:out value='${command.cnsdreq_id}'/>";
		frm.view_gbn.value = "download";
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
		frm.ref_key.value = temp_cntrt_id + "@<c:out value='${command.cnsdreq_id}'/>";
		frm.view_gbn.value = "download";
		getClmsFilePageCheck('frm');
		
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
	    
		
		//종료관리 파일
		frm.target          = "fileList10";
		frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value    = "forwardClmsFilePage";
		frm.fileInfoName.value = "fileInfos10";
		frm.fileFrameName.value = "fileList10";

		frm.file_bigclsfcn.value = "F012";
		frm.file_midclsfcn.value = "F01204";
		frm.file_smlclsfcn.value = "";
		frm.ref_key.value   = temp_cntrt_id + "@<c:out value='${command.cnsdreq_id}'/>";
		
		//의뢰인, 담당자인 경우 첨부파일 유형변경
		if($('#cntrt_chge_demndman_id').length < 1){
			frm.view_gbn.value = "download";
			getClmsFilePageCheck('frm');
		}else{
			frm.view_gbn.value = "modify";
			frm.submit();
		}
		
		
		
	}
	//첨부파일
	function attachHisPage(){
	var frm = document.frm;
	
	    //의뢰-계약서파일 CONSULT
	     $('input[name^=fileInfos_cntrt]').each(function(index){
	    	$fileInfos_cntrt = $(this).attr("name");
	        $fileList_cntrt = $(this).prev().attr("name");
	        $cntrt_cnsdreq_id = $(this).next().val();
	    	
	        frm.target          = $fileList_cntrt;
	    	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    	frm.method.value    = "forwardClmsFilePage";
	    	frm.fileInfoName.value = $fileInfos_cntrt;
	    	frm.fileFrameName.value = $fileList_cntrt;
	    	//의뢰 계약서파일
	    	frm.file_bigclsfcn.value = "F012";
	    	frm.file_midclsfcn.value = "F01202";
	    	frm.file_smlclsfcn.value = "F0120201";
	    	frm.ref_key.value = "<c:out value='${command.cntrt_id}'/>@"+$cntrt_cnsdreq_id;
	    	frm.view_gbn.value = "download";
	    	getClmsFilePageCheck('frm');
	    });
	     //의뢰-첨부/별첨파일 CONSULT
	     $('input[name^=fileInfos_append]').each(function(index){
	    	$fileInfos_append = $(this).attr("name");
	        $fileList_append = $(this).prev().attr("name");
	        $append_cnsdreq_id = $(this).next().val();
	    	
	        frm.target          = $fileList_append;
	    	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    	frm.method.value    = "forwardClmsFilePage";
	    	frm.fileInfoName.value = $fileInfos_append;
	    	frm.fileFrameName.value = $fileList_append;
	    	//의뢰 계약서파일
	    	frm.file_bigclsfcn.value = "F012";
	    	frm.file_midclsfcn.value = "F01202";
	    	frm.file_smlclsfcn.value = "F0120208";
	    	frm.ref_key.value = "<c:out value='${command.cntrt_id}'/>@"+$append_cnsdreq_id;
	    	frm.view_gbn.value = "download";
	    	getClmsFilePageCheck('frm');
	    });
 	     //의뢰-기타_체결본 파일 CONSULT
	     $('input[name^=fileInfos_other]').each(function(index){
	    	$fileInfos_other = $(this).attr("name");
	        $fileList_other = $(this).prev().attr("name");
	        $other_cnsdreq_id = $(this).next().val();
	    	
	        frm.target          = $fileList_other;
	    	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    	frm.method.value    = "forwardClmsFilePage";
	    	frm.fileInfoName.value = $fileInfos_other;
	    	frm.fileFrameName.value = $fileList_other;
	    	frm.file_bigclsfcn.value = "F012";
	    	frm.file_midclsfcn.value = "F01202";
	    	frm.file_smlclsfcn.value = "F0120212";
	    	frm.ref_key.value = "<c:out value='${command.cntrt_id}'/>@"+$other_cnsdreq_id;
	    	frm.view_gbn.value = "download";
	    	getClmsFilePageCheck('frm');
	    });
<%
if("CLM".equals(session.getAttribute("secfw.session.sys_cd"))){
%>
	     //회신-계약서파일 CONSULT
	     $('input[name^=reply_fileInfos_cntrt]').each(function(index){
	    	$reply_fileInfos_cntrt = $(this).attr("name");
	        $reply_fileList_cntrt = $(this).prev().attr("name");
	        $reply_cntrt_cnsdreq_id = $(this).next().val();
	    	
	        frm.target          = $reply_fileList_cntrt;
	    	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    	frm.method.value    = "forwardClmsFilePage";
	    	frm.fileInfoName.value = $reply_fileInfos_cntrt;
	    	frm.fileFrameName.value = $reply_fileList_cntrt;
	    	//회신 계약서파일
	    	frm.file_bigclsfcn.value = "F012";
	    	frm.file_midclsfcn.value = "F01202";
	    	frm.file_smlclsfcn.value = "F0120202";
	    	frm.ref_key.value = "<c:out value='${command.cntrt_id}'/>@"+$reply_cntrt_cnsdreq_id;
	    	frm.view_gbn.value = "download";
	    	getClmsFilePageCheck('frm');
	    });
	     //회신-첨부/별첨파일 CONSULT
	     $('input[name^=reply_fileInfos_append]').each(function(index){
	    	$reply_fileInfos_append = $(this).attr("name");
	        $reply_fileList_append = $(this).prev().attr("name");
	        $reply_append_cnsdreq_id = $(this).next().val();
	    	
	        frm.target          = $reply_fileList_append;
	    	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    	frm.method.value    = "forwardClmsFilePage";
	    	frm.fileInfoName.value = $reply_fileInfos_append;
	    	frm.fileFrameName.value = $reply_fileList_append;
	    	//회신 계약서파일
	    	frm.file_bigclsfcn.value = "F012";
	    	frm.file_midclsfcn.value = "F01202";
	    	frm.file_smlclsfcn.value = "F0120209";
	    	frm.ref_key.value = "<c:out value='${command.cntrt_id}'/>@"+$reply_append_cnsdreq_id;
	    	frm.view_gbn.value = "download";
	    	getClmsFilePageCheck('frm');
	    });
<%
}else if("LAS".equals(session.getAttribute("secfw.session.sys_cd"))){
%>
		//회신-계약서파일 (일반/IP) CONSULT
	     $('input[name^=reply_fileInfos_sub_cntrt]').each(function(index){
	    	$reply_fileInfos_sub_cntrt = $(this).attr("name");
	        $reply_fileList_sub_cntrt = $(this).prev().attr("name");
	        $reply_sub_cntrt_cnsdreq_id = $(this).next().val();
	        $reply_sub_cntrt_cnsd_dept = $(this).next().next().val();
	    	
	        frm.target          = $reply_fileList_sub_cntrt;
	    	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    	frm.method.value    = "forwardClmsFilePage";
	    	frm.fileInfoName.value = $reply_fileInfos_sub_cntrt;
	    	frm.fileFrameName.value = $reply_fileList_sub_cntrt;
	    	//회신 계약서파일
	    	frm.file_bigclsfcn.value = "F012";
	    	frm.file_midclsfcn.value = "F01202";
	    	frm.file_smlclsfcn.value = "F0120203";
	    	frm.ref_key.value = "<c:out value='${command.cntrt_id}'/>@"+$reply_sub_cntrt_cnsdreq_id+"@"+$reply_sub_cntrt_cnsd_dept;
	    	frm.view_gbn.value = "download";
	    	getClmsFilePageCheck('frm');
	    });
	     //회신-첨부/별첨파일 (일반/IP)CONSULT
	     $('input[name^=reply_fileInfos_sub_append]').each(function(index){
	    	$reply_fileInfos_sub_append = $(this).attr("name");
	        $reply_fileList_sub_append = $(this).prev().attr("name");
	        $reply_sub_append_cnsdreq_id = $(this).next().val();
	        $reply_sub_append_cnsd_dept = $(this).next().next().val();
	    	
	        frm.target          = $reply_fileList_sub_append;
	    	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    	frm.method.value    = "forwardClmsFilePage";
	    	frm.fileInfoName.value = $reply_fileInfos_sub_append;
	    	frm.fileFrameName.value = $reply_fileList_sub_append;
	    	//회신 계약서파일
	    	frm.file_bigclsfcn.value = "F012";
	    	frm.file_midclsfcn.value = "F01202";
	    	frm.file_smlclsfcn.value = "F0120210";
	    	frm.ref_key.value = "<c:out value='${command.cntrt_id}'/>@"+$reply_sub_append_cnsdreq_id+"@"+$reply_sub_append_cnsd_dept;
	    	frm.view_gbn.value = "download";
	    	getClmsFilePageCheck('frm');
	    });
	   //회신-기타파일 (일반/IP)CONSULT
	     $('input[name^=reply_fileInfos_sub_other]').each(function(index){
	    	$reply_fileInfos_sub_other = $(this).attr("name");
	        $reply_fileList_sub_other = $(this).prev().attr("name");
	        $reply_sub_other_cnsdreq_id = $(this).next().val();
	        $reply_sub_other_cnsd_dept = $(this).next().next().val();
	    	
	        frm.target          = $reply_fileList_sub_other;
	    	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    	frm.method.value    = "forwardClmsFilePage";
	    	frm.fileInfoName.value = $reply_fileInfos_sub_other;
	    	frm.fileFrameName.value = $reply_fileList_sub_other;
	    	//회신 계약서파일
	    	frm.file_bigclsfcn.value = "F012";
	    	frm.file_midclsfcn.value = "F01202";
	    	frm.file_smlclsfcn.value = "F0120207";
	    	frm.ref_key.value = "<c:out value='${command.cntrt_id}'/>@"+$reply_sub_other_cnsdreq_id+"@"+$reply_sub_other_cnsd_dept;
	    	frm.view_gbn.value = "download";
	    	getClmsFilePageCheck('frm');
	    });
	   //회신-계약단가파일(일반/IP) CONSULT
	     $('input[name^=reply_fileInfos_sub_unit]').each(function(index){
	    	$reply_fileInfos_sub_unit = $(this).attr("name");
	        $reply_fileList_sub_unit = $(this).prev().attr("name");
	        $reply_sub_unit_cnsdreq_id = $(this).next().val();
	        $reply_sub_unit_cnsd_dept = $(this).next().next().val();
	    	
	        frm.target          = $reply_fileList_sub_unit;
	    	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    	frm.method.value    = "forwardClmsFilePage";
	    	frm.fileInfoName.value = $reply_fileInfos_sub_unit;
	    	frm.fileFrameName.value = $reply_fileList_sub_unit;
	    	//회신 계약서파일
	    	frm.file_bigclsfcn.value = "F012";
	    	frm.file_midclsfcn.value = "F01202";
	    	frm.file_smlclsfcn.value = "F0120213";
	    	frm.ref_key.value = "<c:out value='${command.cntrt_id}'/>@"+$reply_sub_unit_cnsdreq_id+"@"+$reply_sub_unit_cnsd_dept;
	    	frm.view_gbn.value = "download";
	    	getClmsFilePageCheck('frm');
	    });
<%
}
%>
	     //승인-파일 
	     $('input[name^=approve_fileInfos]').each(function(index){
	    	$approve_fileInfos = $(this).attr("name");
	        $approve_fileList = $(this).prev().attr("name");
	        $approve_cnsdreq_id = $(this).next().val();
	    	
	        frm.target          = $approve_fileList;
	    	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    	frm.method.value    = "forwardClmsFilePage";
	    	frm.fileInfoName.value = $approve_fileInfos;
	    	frm.fileFrameName.value = $approve_fileList;
	    	//회신 계약서파일
	    	frm.file_bigclsfcn.value = "F012";
	    	frm.file_midclsfcn.value = "F01203";
	    	frm.file_smlclsfcn.value = "";
	    	frm.ref_key.value = "<c:out value='${command.cntrt_id}'/>";
	    	getClmsFilePageCheck('frm');
	    });
	   //사전검토정보-파일 
	     $('input[name^=info_fileInfos]').each(function(index){
	    	$info_fileInfos = $(this).attr("name");
	        $info_fileList = $(this).prev().attr("name");
	    	
	        frm.target          = $info_fileList;
	    	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    	frm.method.value    = "forwardClmsFilePage";
	    	frm.fileInfoName.value = $info_fileInfos;
	    	frm.fileFrameName.value = $info_fileList;
	    	//회신 계약서파일
	    	frm.file_bigclsfcn.value = "F012";
	    	frm.file_midclsfcn.value = "F01201";
	    	frm.file_smlclsfcn.value = "F0120101";
	    	frm.ref_key.value = "<c:out value='${command.cntrt_id}'/>";
	    	getClmsFilePageCheck('frm');
	    });
	   //체결이력 사본첨부-파일 
	     $('input[name^=sign_copyFileInfos]').each(function(index){
	    	$sign_copyFileInfos = $(this).attr("name");
	        $sign_copyFileList = $(this).prev().attr("name");
	    	
	        frm.target          = $sign_copyFileList;
	    	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    	frm.method.value    = "forwardClmsFilePage";
	    	frm.fileInfoName.value = $sign_copyFileInfos;
	    	frm.fileFrameName.value = $sign_copyFileList;
	    	//회신 계약서파일
	    	frm.file_bigclsfcn.value = "F012";
	    	frm.file_midclsfcn.value = "F01203";
	    	frm.file_smlclsfcn.value = "";
	    	frm.ref_key.value = "<c:out value='${command.cntrt_id}'/>";
	    	getClmsFilePageCheck('frm');
	    });	
	}
	

</script>
<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- // footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</body>		
</html>

