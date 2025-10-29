<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>
<%@ page import="com.sec.clm.manage.dto.ConsultationForm" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%--
/**
 * 파  일  명 : Consideration_d_new.jsp
 * 프로그램명 : 검토의뢰 상세
 * 설	  명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2013.04
 */
--%>
<% 
		
	ListOrderedMap lomRq = (ListOrderedMap)request.getAttribute("lomRq"); // 의뢰 정보
	ListOrderedMap lomRq2 = (ListOrderedMap)request.getAttribute("lomRq2"); // 계약정보(기존 inner 에서 가져왔단 정보)
	
	ArrayList listTs = (ArrayList)request.getAttribute("listTs");  // 특화정보
	
%>

<html>
<!-- page name: Consideration_d_new.jsp -->
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta charset="utf-8" />
<title><spring:message code="clm.main.title" /></title>

<link href="<%=CSS%>/las.css"	type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="/script/clms/common.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/excanvas.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery-1.7.2.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="/script/clms/clms_common.js"></script>
<script src="/script/func/ConsiderationFunc.js"></script><!-- 2014-07-24 Kevin added -->
<script>

	$(document).ready(function(){	
		$("[id^='tr_show']").hide();
		$("[id^='tr_show01']").hide();
		
		// 2014-08-14 Kevin added.
		$("#tbBasicInfo").block({
			message:"<img src='/images/secfw/common/process_circle.gif' />",
			css:{border:"0px solid #FFFFFF", backgroundColor:"transparent"}
		});
		
		// 2014-07-24 Kevin added.
		// Following codes retrive step/status information and show them.
		var considerationFunc = new ConsiderationFunc($);
		considerationFunc.getStepStatusInfoPerCnsdreqid($("#cnsdreq_id").val(), function(data){
			$("#spStep").text(data.step);
			$("#spStatus").text(data.status+" "+data.status_depth);
		});
		
		// 화면로드시 금액 100,000 형태로 변환
		var frm = document.frm;
		var amt = frm.cntrt_amt.value;
		frm.cntrt_amt.value = Comma(amt);
		
		//첨부파일 설정 
		initPage();
		
		if(<%=StringUtil.isNull((String)lomRq2.get("cntrt_untprc_expl"))%> == false) { 
			frm.target		  = "fileList3";
			frm.action		  = "<c:url value='/clms/common/clmsfile.do' />";
			frm.method.value	= "forwardClmsFilePage";
			frm.file_bigclsfcn.value = "F012";
			frm.file_midclsfcn.value = "F01202";
			frm.file_smlclsfcn.value = "F0120211";
			frm.ref_key.value = $('#master_cntrt_id').val();
			frm.view_gbn.value = "download";
			frm.fileInfoName.value = "fileInfos3";
			frm.fileFrameName.value = "fileList3";
			/* getClmsFilePageCheck('frm'); */
			frm.submit();
		}
		
		// 2012.03.28 활성화 계약건의 상태에 따라 DROP버튼 Display added by hanjihoon
		if($('#session_user_id').val() == $('#reqman_id').val() && ($('#depth_status').val() == "C02606" || $('#depth_status').val() == "C02608")){
			$("#id_dropContract").show();
		}else{
			$("#id_dropContract").hide();
		}
		
		contractHisList();	//Sungwoo added 2014-09-04
		
		<% if("C04201".equals(lomRq.get("prgrs_status")) && "C02601".equals(lomRq.get("depth_status"))){ 
			if(StringUtil.bvl(session.getAttribute("secfw.session.user_id"), "").equals(lomRq.get("reqman_id"))){
				 int level = ((BigDecimal) lomRq.get("cnsd_level")).intValue();
				 if( level != 1) {
		%>
		alert("<spring:message code='clm.page.msg.manage.announce207' />");
		<%	  } else {%>
		
		alert("<spring:message code='clm.page.msg.manage.announce208' />");
		<%	  }}} %>
		
		
	});
	
	function forwardConsultation(arg){
		//detailConsultation
		var frm = document.frm;
	
		if($("#sys_nm").val() =="myProject"){
			alert("<spring:message code='clm.page.msg.manage.announce056' />");
			return;
		}else{
			frm.status.value = arg;
			frm.target = "_self";		 
			frm.action = "<c:url value='/clm/manage/consultation.do?method=detailConsultationNew&page_gbn=modify' />";
			frm.submit();
		}
	}
	
	/**
	* page Action
	*/
	function forwardConsideration(arg){
		var frm = document.frm;
		
		//mycontract에서 접근했을 시
		if(frm.ismycontract.value == 'Y' && arg == 'list'){
			frm.target = "_self";		 
			frm.action = "<c:url value='/clm/manage/myContract.do' />";
			frm.method.value = "listMyContract";
			frm.submit();
		}
		else{
			frm.target = "_self";		 
			frm.action = "<c:url value='/clm/manage/consideration.do' />";
			
			if(arg == "save"){	//임시저장 사용 안함
				alert("View X");
			}else if(arg == "req"){		//검토의뢰	사용안함 
				frm.status.value = "forwardReq";
				frm.method.value = "prevCnsdReqCopyConsideration";			
			}else if(arg == "last"){	//최종본회신	사용안함		
				frm.status.value="forwardLast"; 
				frm.method.value = "prevCnsdReqCopyConsideration";			
			}else if(arg == "list"){	//목록			
				frm.method.value = "listManageConsideration";			
			}else if(arg == "mod"){	//	수정	화면으로 이동 
				frm.status.value = "forwardModify";
				frm.method.value = "forwardModifyConsiderationNew";			
			}else if(arg == "again"){	//재검토 화면으로 이동 
				frm.status.value = "forwardModify";
				frm.method.value = "forwardModifyConsiderationNew";
			}else if(arg == "detail"){		//상세 화면
				frm.status.value = "";
				frm.method.value = "detailConsideration";
			}
		
			frm.submit();
		}
	}
	
	function prevCnsdReqCopyConsideration(arg){
		var frm = document.frm;
		if(arg == "again"){
			if(!confirm("<spring:message code='clm.page.msg.manage.announce145' />")) return;
			frm.status.value = "forwardReq";
		}else if(arg == "last"){
			// 2012.07.26 최종본검토건일 경우, 이전검토건중에 회신건이 한건이상일경우에만 가능 added by hanjihoon
			verifyFinalConsideration();
			return;
			
		}else if(arg == "deferAgain"){
			frm.status.value = "forwardReq";
		}
		var options = {
			url: "<c:url value='/clm/manage/consideration.do?method=prevCnsdReqCopyConsideration' />",
			type: "post",
			dataType: "json",			
			success: function(responseText, statusText) {

				if(responseText.cnsdreqId == undefined){
					viewHiddenProgress(false);
					alert("<spring:message code='clm.page.msg.manage.announce157' />");
				}else{
					document.frm.cnsdreq_id.value = responseText.cnsdreqId;
					if(arg == "again" || arg == "deferAgain"){  //재검토 의뢰시 재검토 화면 으로 이동 
						forwardConsideration('again');
					}else if(arg == "last"){
						forwardConsideration('mod');
					}
				}
			}
		};
		viewHiddenProgress(true);
		$("#frm").ajaxSubmit(options);
	}
	
	/**
	 * 최종본검토의뢰 
	 */
	function verifyFinalConsideration(arg){
		var options = {
			url: "<c:url value='/clm/manage/consideration.do?method=verifyFinalConsideration' />",
			type: "post",
			dataType: "json",
			success: function(responseText, statusText){
				viewHiddenProgress(false);
				
				if(responseText != null && responseText.length != 0 && responseText.returnMessage == "OK") {
					
					if(!confirm("<spring:message code='clm.page.msg.manage.announce186' />")) return;
					alert("<spring:message code='clm.page.msg.manage.announce002' />");
					
					frm.status.value="forwardLast";

					var options2 = {
						url: "<c:url value='/clm/manage/consideration.do?method=prevCnsdReqCopyConsideration' />",
						type: "post",
						dataType: "json",			
						success: function(responseText, statusText) {
							viewHiddenProgress(false);
							
							if(responseText.cnsdreqId == undefined){
								alert("<spring:message code='clm.page.msg.manage.announce157' />");
							}else{
								document.frm.cnsdreq_id.value = responseText.cnsdreqId;
								forwardConsideration('mod');
							}
						}
					};
					viewHiddenProgress(true);
					$("#frm").ajaxSubmit(options2);
				}else{
					alert(responseText.returnMessage);
				}
			}
		};
		viewHiddenProgress(true);
		$("#frm").ajaxSubmit(options);
	}
	
	/**
	* 의뢰건 상단 버튼 Action 의뢰 보류/ 드랍 /삭제 
	*/
	function actionConsideration(arg){
		//검토 담당자가 임시저장일 경우 보류하지 못하도록 변경(2013.07.18)
		if("deferRequest" == arg){
			if("<c:out value='${lomRq.dept_cnsd_status}' />" == "C04302" ){
				alert("<spring:message code='clm.page.msg.deferRequest.message' />"); // 법무검토자 검토 중이므로 보류 할 수 없습니다.
				return;
			}
		}
		
		var msg ="";
		//defer -보류 , drop -Drop ,delete - 삭제
		if("dropRequest" == arg){
			msg = "<spring:message code='clm.page.msg.manage.announce206' />";			
		}else if("deferRequest" == arg){
			msg = "<spring:message code='clm.page.msg.manage.announce126' />";
		}else if("deleteRequest" == arg){		//의뢰건 삭제 
			msg = "<spring:message code='clm.page.msg.manage.announce120' />";
		}else if("dropContract" == arg){		//의뢰건 삭제 
			msg = "<spring:message code='clm.page.msg.manage.announce108' />";
		}else if("deferCancelRequest" == arg){		//의뢰건 보류 해제
			msg = "<spring:message code='clm.page.msg.manage.announce127' />";
		}else if("saveCancelRequest" == arg){	//작성중일때 (재의뢰시) 의뢰 취소 
			msg = "<spring:message code='clm.page.msg.manage.announce142' />";
		}
		
		if("deleteRequest" == arg){
			if(confirm(msg)){ 
				processConsideration(arg);
			}
		}else if("dropRequest" == arg || "deferRequest" == arg || "dropContract" == arg){
			if(confirm(msg)){
				opnnConsideration(arg);
			}
		}else if("deferCancelRequest" == arg){
			if(confirm(msg)){ 			
				processConsideration(arg);
			}
		}else if("saveCancelRequest" == arg){
			if(confirm(msg)){ 
				processConsideration(arg);
			}
		}
	}
	/**
	* 의뢰 계약 보류 삭제 드랍 처리 
	*/
	function processConsideration(arg){
		var frm = document.frm;
		frm.submit_status.value = arg;

		var options = {
			url: "<c:url value='/clm/manage/consideration.do?method="+ arg +"' />",
			type: "post",
			dataType: "json",			
			success: function(responseText, statusText) {
				if(responseText.returnCd == "Y"){
					alert("<spring:message code='clm.page.msg.manage.announce158' />");
						if("deleteRequest" == arg){
							forwardConsideration('list');
						}else if("deferCancelRequest" == arg){		//보류해제시  재의뢰 할수 있도록 재의뢰 Process Copy 진행				
							//현재 페이지 reload
							detailAction();
						}else if ("saveCancelRequest" ==  arg){	//작성중인 의뢰건 취소 후 
							forwardConsideration('list');
						}
					
				}else{
					alert("<spring:message code='clm.page.msg.manage.announce157' />");
				}
			}
		};
		$("#frm").ajaxSubmit(options);
	}
	
	
	/**
	* Reload
	*/
	/**
	* 관리페이지 이동
	*/	
	function detailAction(){
		var frm = document.frm;
		frm.target = "_self";
		frm.action = "/clm/manage/consideration.do";
		frm.method.value = "detailConsiderationNew";//detailConsideration		
		frm.submit();
	}
	
	 /**
	 * 의뢰 보류 / drop /계약 drop 
	 * frm.submit_status.value = arg;
	 */
	 function opnnConsideration(arg){
		
	 	var frm = document.frm;
	 	frm.submit_status.value = arg; 
	 	
	 	PopUpWindowOpen('', "530", "200", true);
		frm.action = "<c:url value='/clm/manage/consideration.do' />";
		frm.method.value="opnnConsideration";
		frm.target = "PopUpWindow";
		frm.submit();
	 	
	 }

	 function setOpnnConsideration(obj) {    
        var strMethod =   obj.strMethod;
        
        if("deferRequest" == strMethod){	//deferRequest 의뢰 보류
        	detailAction();
  			        	
        }else if("dropContract" == strMethod){	//dropContract 계약 drop
        	forwardConsideration("detail");
        }else{	        	
        	forwardConsideration("list");
        }
	 }
	 
	 //거래선 거래상대방 상세내역 팝업
	 function customerPop(customerCd, dodun){
			var frm = document.frm;
			PopUpWindowOpen('', 900, 300, true);
			frm.target = "PopUpWindow";
			frm.customer_cd.value = customerCd;
			frm.dodun.value		= dodun;
			frm.action = "<c:url value='/clm/draft/customerTest.do'/>";
			frm.method.value = "detailCustomerShare";
			frm.submit();
		}
	
	function initPage(){		 
		var frm = document.frm;	
			
		  //계약관련 #4
			frm.target		  = "fileList4";
			frm.action		  = "<c:url value='/clms/common/clmsfile.do' />";
			frm.method.value	= "forwardClmsFilePage";
			frm.file_bigclsfcn.value = "F012";
			frm.file_midclsfcn.value = "F01201";
			frm.file_smlclsfcn.value = "F0120101";
			frm.ref_key.value = $('#master_cntrt_id').val();
			frm.fileInfoName.value = "fileInfos4";
			frm.fileFrameName.value = "fileList4";
			frm.submit();

			// 2014-04-03 Kevin Added.
			// GERP readonly iframe load. 
			frm.target		  = "iframeGERP";
			frm.action		  = "<c:url value='/clm/manage/consideration.do' />";
			frm.gerpPageType.value = "R";		// detail readonly
			frm.method.value	= "forwardGERPDetail";
			frm.submit();			
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
			
			
			function tncfunc(){
			    
		    	//alert("1");
		    	
		    	$("#cntrt_top30_cus").attr('disabled', false);
				//$("#cntrt_top30_cus").val("C05606");
				$("#cntrt_src_cont_drft").attr('disabled', false);
				//$("#cntrt_src_cont_drft").val("SOC10");
		    }
		    
		    
		    function tncfunc_false(){
		    	
		    	//alert("2");
		    	
		    	$("#cntrt_top30_cus").attr('disabled', true);
				//$("#cntrt_top30_cus").val("C05606");
				$("#cntrt_src_cont_drft").attr('disabled', true);
				//$("#cntrt_src_cont_drft").val("SOC10");
		    }
	 
</script>
</head>
<body><!--J15002-->
<div id="wrap">
	<!-- container -->
	<div id="container">
		<!-- location -->
		<div class="location">
			<img src="<%=IMAGE %>/icon/ico_home.gif" border="0" alt="Home"></img> <spring:message code="clm.page.msg.common.contractManagement" /> > <spring:message code="clm.page.msg.manage.reviewReq" /> > <strong><c:out value='${command.req_status_title}'/> <spring:message code="clm.page.msg.manage.write" /></strong>
		</div>
		<!-- //location -->
		<!-- title -->
		<div class="title">
		<h3><c:choose>
				<c:when test="${(command.req_status_title == '검토')}">
					<spring:message code="clm.page.msg.manage.1stReview" />
				</c:when>
				<c:otherwise>
					<c:out value='${command.req_status_title}'/>
				 </c:otherwise>
			 </c:choose>&nbsp;<c:out value='${lomRq.depth_status_nm}'/>
		 </h3>
		<!-- //title -->		
		<!-- content -->	
		<div id="content">
			<div id="content_in">
			<form:form name="frm" id='frm' method="post" autocomplete="off">
			<input type="hidden" name="contents" id="contents" />
		<input type="hidden" name="method" id="method" value="" />
		<input type="hidden" name="menu_id" id="menu_id" value="<c:out value='${command.menu_id}'/>" />
		<input type="hidden" name="curPage" id="curPage" value="<c:out value='${command.curPage}'/>" />
		<!-- 리스트 검색 조건  -->
		<input type="hidden" name="list_mode" value="<c:out value='${command.list_mode}'/>"/>
		<input type="hidden" name="srch_resp_dept" value="<c:out value='${command.srch_resp_dept}'/>"/>				<!-- 담당부서코드 -->
		<input type="hidden" name="srch_oppnt_cd" value="<c:out value='${command.srch_oppnt_cd}'/>"/>				<!-- 계약상대방코드 -->
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
		<input type="hidden" name="srch_if_sys_cd" value="<c:out value='${command.srch_if_sys_cd}'/>" />				<!-- 연계시스템 -->
		<input type="hidden" name="srch_cnclsnpurps_bigclsfcn" value="<c:out value='${command.srch_cnclsnpurps_bigclsfcn}'/>" />	<!-- 체결목적-->
		<input type="hidden" name="srch_step" value="<c:out value='${command.srch_step}'/>" />						<!-- 상태 -->
		<input type="hidden" name="cnsd_status" id="cnsd_status" value="<c:out value='${lomRq.cnsd_status}'/>" />
		<input type="hidden" name="prgrs_status" id="prgrs_status" value="<c:out value='${lomRq.prgrs_status}'/>" />	
		<input type="hidden" name="plndbn_req_yn" id="plndbn_req_yn" value="<c:out value='${lomRq.plndbn_req_yn}'/>" />
		<input type="hidden" name="sys_nm" id="sys_nm" value="<c:out value='${lomRq.sys_nm}'/>" />
		<!-- 13/10/29 검색 조건 유지 추가 -->
		<input type="hidden" name="srch_cntrt_nm" value="<c:out value='${command.srch_cntrt_nm}'/>" />						<!-- Contract Title -->
		<input type="hidden" name="srch_cntrt_no" value="<c:out value='${command.srch_cntrt_no}'/>" />						<!-- Contract ID -->
		<input type="hidden" name="srch_tnc_no" value="<c:out value='${command.srch_tnc_no}'/>" />						<!-- TNC NO -->
		<input type="hidden" name="srch_str_org_acptday" value="<c:out value='${command.srch_str_org_acptday}'/>" />		<!-- Original Copy -->
		<input type="hidden" name="srch_end_org_acptday" value="<c:out value='${command.srch_end_org_acptday}'/>" />		<!-- Original Copy -->
		<!-- Sungwoo added search parameter 2014-06-12 -->
		<input type="hidden" name="srch_division" value="<c:out value='${command.srch_division}'/>" />					<!-- Original Copy -->
		<input type="hidden" name="srch_vendor_type" value="<c:out value='${command.srch_vendor_type}'/>" />					<!-- Original Copy -->
		<input type="hidden" name="srch_vendor_type_detail" value="<c:out value='${command.srch_vendor_type_detail}'/>" />		<!-- Original Copy -->
		<input type="hidden" name="mis_id" /> <!-- 12/07/13 추가 consultation 에서 끌어옴 -->
		<!-- 의뢰시 필수 파라민터  -->
		<input type="hidden" name="status" id="status" value="" />
		<input type="hidden" name="tab_cnt" id="tab_cnt" value="<c:out value='${lomRq.total_cnt}'/>" /><!-- Tab 계약 증가 값 -->
		<input type="hidden" name="prev_cnsdreq_id" id ="prev_cnsdreq_id" value="<c:out value='${lomRq.prev_cnsdreq_id}'/>" />
		<input type="hidden" name="cnsdreq_id" id="cnsdreq_id" value="<c:out value='${lomRq.cnsdreq_id}'/>" />	 
		<input type="hidden" name="cntrt_id" id="cntrt_id"  value="<c:out value='${lomRq.cntrt_id}'/>" />
		
		<input type="hidden" name="dmstfrgn_gbn" id="dmstfrgn_gbn"  value="<c:out value='${lomRq.dmstfrgn_gbn}'/>" />
		<c:forEach var="cntrtMt" items="${listDc}">
			<input type="hidden" name="arr_cntrt_id"  value="<c:out value='${cntrtMt.cntrt_id}' />" /> <!-- 계약_마스터_계약_ID -->	
		</c:forEach>
		<input type="hidden" name="parent_cntrt_id" id="parent_cntrt_id" />
		<input type="hidden" name="submit_status" id="submit_status" />
		<!-- 첨부파일정보 -->
		<input type="hidden" name="fileInfos"	value="" /> <!-- 저장될 파일 정보 -->
		<input type="hidden" name="fileInfos1"	value="" /> <!-- 저장될 파일 정보 -->
		<input type="hidden" name="fileInfosEtc"	value="" /> <!-- 저장될 파일 정보 -->	
		<input type="hidden" name="fileInfos2"	value="" /> <!-- 저장될 파일 정보 -->	
		<input type="hidden" name="fileInfos3"	value="" /> <!-- 저장될 파일 정보 -->	
		<input type="hidden" name="fileInfos4"	value="" /> <!-- 저장될 파일 정보 -->
		<input type="hidden" name="fileInfos5"	value="" /> <!-- 저장될 파일 정보 -->	
		<input type="hidden" name="fileInfos6"	value="" /> <!-- 저장될 파일 정보 -->
		<input type="hidden" name="fileInfoName"	value="" /> <!-- 첨부파일 정보가 리턴될 객체명 -->
		<input type="hidden" name="fileFrameName"	value="" /> <!-- 첨부파일 화면 iFrame 명 -->
		<input type="hidden" name="file_bigclsfcn"  value="" /> <!-- 대분류 -->	
		<input type="hidden" name="file_midclsfcn"  value="" /> <!-- 중분류 -->	
		<input type="hidden" name="file_smlclsfcn"  value="" /> <!-- 소분류 -->	
		<input type="hidden" name="ref_key"	 value="<c:out value='${lomRq.cnsdreq_id}'/>" /> <!-- 키값 -->	
		<input type="hidden" name="view_gbn"	value="download" /> <!-- 첨부파일 출력 화면 종류 : upload, modify, download -->	
		<!-- 거래선 상대방 팝업 파라미터  -->
		<input type="hidden" name="customer_cd" id="customer_cd" value="" />
		<input type="hidden" name="dodun" id="dodun" value="" />
		<!-- 관련자 데이타 설정(구주추가요청 14.01)  -->
		<input type="hidden" name="chose_client" id="chose_client" />
		<!-- Mycontract 파라미터 -->
		<input type="hidden" name="ismycontract" id="ismycontract" value="<c:out value='${command.ismycontract}'/>"/>
		
		<!-- Consideration_inner_d.jsp 에 있던 hidden -->
		<input type="hidden" name="master_cnslt_no" id="master_cnslt_no"  value="<c:out value='${lomRq2.cnslt_no}'/>@<c:out value='${lomRq2.hstry_no}'/>" />
		<input type="hidden" name="master_cntrt_id" id="master_cntrt_id"  value="<c:out value='${lomRq.cntrt_id}'/>" />
		<input type="hidden" name="file_validate" id="file_validate" value="<c:out value='${command.file_validate}'/>" />
		<input type="hidden" name="session_user_id" id="session_user_id" value="<c:out value='${command.session_user_id}'/>" />
		<input type="hidden" name="reqman_id" id="reqman_id" value="<c:out value='${lomRq.reqman_id}'/>" />
		<input type="hidden" name="depth_status" id="depth_status" value="<c:out value='${lomRq.depth_status}'/>" />
		
		<!-- Sungwoo added 2014-12-01 Reply 시 필수 입력 필드 값 처리위해서 -->
		<input type="hidden" name="req_dt" id="req_dt" value="<c:out value='${lomRq.req_dt}'/>" />
		<input type="hidden" name="lastbn_chge_yn" id="lastbn_chge_yn" value="<c:out value='${lomRq.lastbn_chge_yn}'/>" />
		<input type="hidden" name="rebfr_apprvl_yn" id="rebfr_apprvl_yn" value="<c:out value='${lomRq.rebfr_apprvl_yn}'/>" />
		<input type="hidden" name="pub_yn" id="pub_yn" value="<c:out value='${lomRq.pub_yn}'/>" />
		
		<!-- 2014-04-08 Kevin added. GERP 관련 페이지 형태 구분.(R/I) -->
		<input type="hidden" name="gerpPageType" id="gerpPageType" />
			<div class="content-step t_titBtn">
				<ol>
					<li class='step_on'>
						<img src="<%=IMAGE %>/common/step01_on.gif"  alt="<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" />" />
						<img src="<%=IMAGE %>/common/step_arr01.gif" alt="Next" title="Next" />
						<div class='step_link'><a href="javascript:open_window('win', '../../step01<%=langCd.equals("ko")?"":"_"+langCd %>.html', 0, 0, 696, 588, 0, 0, 0, 0, 0)" ><img src="<%=IMAGE %>/common/step_q.gif" alt="<spring:message code="clm.page.msg.manage.procStep"/>" title="<spring:message code="clm.page.msg.manage.procStep"/>" style="padding-top:5px; padding-left:3px;" /></a></div>
					</li>
					<li><img src="<%=IMAGE %>/common/step02.gif"	 alt="<spring:message code="clm.page.msg.manage.step02" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step02" htmlEscape="true" />" /></li>
					<li><img src="<%=IMAGE %>/common/step03.gif"	 alt="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" /></li>
					<li><img src="<%=IMAGE %>/common/step04.gif"	 alt="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" /></li>
					<li><img src="<%=IMAGE %>/common/step05.gif"	 alt="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" /></li>
				</ol>
			</div>
			<div class="t_titBtn">
				<!-- title -->
				<div class="title_basic">
					<h4><c:out value='${command.req_status_title}'/> <spring:message code="clm.page.msg.manage.reqInf" /><!-- <img src="</%=IMAGE %>/btn/btn_up.gif" alt="up" onclick="tit_Toggle(this,'tr_down01');"/> --></h4>
				</div>
				<!-- //title -->
						
				<!-- button -->
				<div class="btnwrap mt_22">		
				<c:if test='${lomRq.close_yn ne "Y"}'>	  				
				<c:if test="${command.session_user_id == lomRq.reqman_id}">
				<!-- C04201 : 미체결  C02601 : 작성중 -->				
				<% if("C04201".equals(lomRq.get("prgrs_status")) && ("C02601".equals(lomRq.get("depth_status")))){ %>
					<c:choose>						
						<c:when test="${lomRq.plndbn_req_yn == 'N' && lomRq.prev_cnsdreq_id != ''}">
							<!-- 재의뢰 -->
							<span class="btn_all_w"><span class="check2"></span><a href="javascript:forwardConsideration('again');"><spring:message code="clm.page.msg.common.modify" /></a></span>
						</c:when>
						<c:otherwise> 
							<!-- 수정 -->
							<span class="btn_all_w"><span class="check2"></span><a href="javascript:forwardConsideration('mod');"><spring:message code="clm.page.msg.common.modify" /></a></span>
						</c:otherwise>
					</c:choose>
					<c:if test="${lomRq.prev_cnsdreq_id !=''}">
						<span class="btn_all_w"><span class="check2"></span><a href="javascript:actionConsideration('saveCancelRequest');"><spring:message code="clm.page.msg.manage.cnclTmpSave" /></a></span>
					</c:if>
				<%//C04305: Finished Review ,   C02607: Holding --- C04305 or C04226 %>	
				<% }else if(!"Y".equals(lomRq.get("plndbn_req_yn")) && "C04305".equals(lomRq.get("cnsd_status")) && (!"C02607".equals(lomRq.get("depth_status")))){%>
					<span class="btn_all_w"><span class="check2"></span><a href="javascript:prevCnsdReqCopyConsideration('again');"><spring:message code="clm.page.msg.manage.reply" /></a></span>
				<%  }else if("C02607".equals(lomRq.get("depth_status")) || "C02608".equals(lomRq.get("depth_status"))  || "C04215".equals(lomRq.get("prgrs_status")) || "C04216".equals(lomRq.get("prgrs_status"))){  %>
					<%if("Y".equals(lomRq.get("plndbn_req_yn")) && "C04207".equals(lomRq.get("prgrs_status"))){ //품의작성 버튼 %>		
						<%if("CPCEX".equals(lomRq.get("sys_nm")) || "CMS".equals(lomRq.get("sys_nm"))){ }else{ //연계건은 품의작성버튼을 막는다%>
							<span class="btn_all_w"><span class="reply"></span><a href="javascript:forwardConsultation('consult');"><spring:message code="clm.page.button.contract.modifyconsultation" /></a></span>
						<%  } %>
					<%  } %>
				<%  } %>
			 
				<%if(!"Y".equals(lomRq.get("plndbn_req_yn")) && "C04305".equals(lomRq.get("cnsd_status"))){%>		
				<%//C04226 : Admin Replied, C02608:Replied, C04215: 상신취소,C04216:반려  %>
				<%}else if( "C04226".equals(lomRq.get("prgrs_status"))  || "C02608".equals(lomRq.get("depth_status"))  || "C04215".equals(lomRq.get("prgrs_status")) || "C04216".equals(lomRq.get("prgrs_status"))){  %>
					<span class="btn_all_w"><span class="check2"></span><a href="javascript:prevCnsdReqCopyConsideration('again');"><spring:message code="clm.page.msg.manage.reply" /></a></span>
				<%} %>	
				
				<% if("C04202".equals(lomRq.get("prgrs_status")) || "C04203".equals(lomRq.get("prgrs_status")) || "C04204".equals(lomRq.get("prgrs_status")) || "C04205".equals(lomRq.get("prgrs_status")) || "C04206".equals(lomRq.get("prgrs_status")) || "C04207".equals(lomRq.get("prgrs_status")) ){	//검토중  회신 일때 Drop 보임%>
					<c:choose>						
						<c:when test="${lomRq.defer_btn == 'defer' }">
							<span id ="id_defer" class="btn_all_w"><span class="reject"></span><a href="javascript:actionConsideration('deferRequest');" title="<spring:message code="clm.page.msg.manage.tempConcl" htmlEscape="true" />"><spring:message code="clm.page.msg.manage.delay" /></a></span>
						</c:when>
						<c:otherwise> 
							<span class="btn_all_w"><span class="check2"></span><a href="javascript:prevCnsdReqCopyConsideration('again');"><spring:message code="clm.page.msg.manage.reply" /></a></span>
						</c:otherwise>
					</c:choose>									
				<% }else if( (! "TNC".equals(lomRq.get("sys_nm"))) &&  "".equals(lomRq.get("prev_cnsdreq_id")) && "C04201".equals(lomRq.get("prgrs_status")) && "C02601".equals(lomRq.get("depth_status")) ){ %>				
					<span id ="id_delete"  class="btn_all_w"><span class="delete"></span><a href="javascript:actionConsideration('deleteRequest');"><spring:message code="clm.page.msg.common.delete" /></a></span>
				<% }%>
				</c:if>
				</c:if>
				<!-- 인쇄 버튼 -->		
				<span class="btn_all_p ml_10"><span class="print"></span><a href="javascript:considerationPreview();"><spring:message code="clm.page.msg.manage.print" /></a></span>
				<!-- 목록 버튼 -->		
				<span class="btn_all_p"><span class="list"></span><a href="javascript:forwardConsideration('list');"><spring:message code="clm.page.msg.manage.list" /></a></span>
				</div>
				<!-- //button -->
			</div>
			<!-- toptable -->
			
			<!-- 2014-08-11 Kevin changed. Basic Information 섹션을 공통 페이지로 분리해서 include 시킨다. -->
			<jsp:include page="/WEB-INF/jsp/clm/common/basicInfo.jsp">
				<jsp:param name="cnsdreq_id" value='<%=lomRq.get("cnsdreq_id") %>' />
			</jsp:include>
			
			<div class="title_basic">
				<h4><spring:message code="clm.page.msg.manage.contInfo" /></h4>
			</div>
			<!-- //title -->
			<div class='title_basic3'><spring:message code="clm.page.msg.manage.bscInf" /></div>
			<table class="table-style01">
				<colgroup>
					<col width="13%" />
					<col width="22%" />
					<col width="11%" />
					<col width="21%" />
					<col width="12%" />
					<col width="21%" />
				</colgroup>
				<tr>
					<th><spring:message code="clm.page.msg.manage.reqName" /></th>
					<td colspan="6"><c:out value='${lomRq.req_title}'  escapeXml="false"/></td>
				</tr>
				<tr>
					<th><spring:message code="clm.page.msg.manage.contName" /></th>
					<td colspan="3"><span class="fL"><c:out value="${lomRq2.cntrt_nm}" /></span></td>
					<th><spring:message code="clm.page.msg.manage.contId" /></th>
					<td><span class="fL"><c:out value="${lomRq2.cntrt_no}" /></span>					
                    	<%if(lomRq2.get("tnc_no")!=null && !lomRq2.get("tnc_no").equals("")) { %>
				            <span style='color:#A9B4BC;'>(<%=lomRq2.get("tnc_no") %>)</span>
				            <%  } %>
					</td>
				</tr>
				<tr>
					<th><spring:message code="clm.page.msg.manage.reqPerson" /></th>
					<td>
						<c:out value="${lomRq2.reqman_nm}" />/
						<c:out value="${lomRq2.reqman_jikgup_nm}" />/
						<c:out value="${lomRq2.req_dept_nm}" />
					</td>
					<th><spring:message code="clm.page.msg.manage.chrgPerson" /></th>
					<td>
						<c:out value="${lomRq2.cntrt_respman_nm}" />/
						<c:out value="${lomRq2.cntrt_respman_jikgup_nm}" />/
						<c:out value="${lomRq2.cntrt_resp_dept_nm}" />
					</td>
					<th><spring:message code="clm.page.msg.manage.reviewer" /></th>
					<td><c:out value="${lomRq2.cnsdmans}"  escapeXml="false"/></td>
				</tr>
				<tr>
					<th><spring:message code="clm.page.msg.common.otherParty" /></th>
					<td><a href="javascript:customerPop('<c:out value="${lomRq2.cntrt_oppnt_cd}" />','<c:out value="${lomRq2.cntrt_oppnt_cd}" />');"><c:out value="${lomRq2.cntrt_oppnt_nm}"  escapeXml="false"/></a></td>
					<th><spring:message code="clm.page.field.customer.registerNo" /></th>
					<td><c:out value="${lomRq2.cntrt_oppnt_rprsntman}"  escapeXml="false"/></td>
					<th></th>
					<td></td>
				</tr>
				<tr>
                    <th><spring:message code="clm.page.msg.manage.top30Cus" /><img src="<%=IMAGE %>/icon/ico_search2.gif" class="cp" onClick="javascript:openTop30Customer();" alt="search" style="padding-left:5px" title="Top 30 Customer List"/></th>
                    <td>
                        <c:out value="${lomRq2.top_30_cus}"  escapeXml="false"/>    
                    </td>
                    <th><spring:message code="clm.page.msg.manage.relatedPrd" /></th>
                    <td><c:out value="${lomRq2.related_products}"  escapeXml="false"/></td>
                    <th><spring:message code="clm.page.msg.manage.srcContDraft" /></th>
                    <td><c:out value="${lomRq2.cont_draft}" /></td>
                </tr>
				<%  String strColspan="5";
				if("Y".equals(lomRq2.get("plndbn_req_yn")) && "C02608".equals(lomRq2.get("depth_status"))){ //최종본의뢰여부
					strColspan="3";
					}
				%>
				<tr>
					<th><spring:message code="clm.page.msg.manage.contType" /></th>					
					<td colspan="<%=strColspan%>">				  
						<c:out value="${lomRq.biz_clsfcn_nm}" /> / 
						<c:out value="${lomRq.depth_clsfcn_nm}" /> / 
						<c:out value="${lomRq.cnclsnpurps_bigclsfcn_nm}" /> / 
						<c:out value="${lomRq.cnclsnpurps_midclsfcn_nm}" /> /
						
					</td>
					<%  if("Y".equals(lomRq2.get("plndbn_req_yn")) && "C02608".equals(lomRq2.get("depth_status"))){ //최종본의뢰여부%>					
					<th><spring:message code="clm.page.msg.manage.autoExpYn" /></th>
					<td><c:out value="${lomRq2.auto_rnew_yn}" /></td>				
					<% }%>				  
				</tr>
				<tr>
					<th><spring:message code="clm.page.msg.manage.contItm" /></th>
					<td>
						<c:out value="${lomRq2.cntrt_trgt_nm}"  escapeXml="false"/>	
					</td>
					<th><spring:message code="clm.page.msg.manage.contItmDtl" /></th>
					<td colspan='4'><c:out value="${lomRq2.cntrt_trgt_det}"  escapeXml="false"/></td>
				</tr>
				<tr>
					<th><spring:message code="clm.page.msg.manage.contPeriod" /></th>
					<td colspan="3">
						<c:out value="${lomRq2.cntrtperiod_startday}" /> <c:if test="${lomRq2.cntrtperiod_startday ne ''}">~</c:if> <c:out value="${lomRq2.cntrtperiod_endday}" />
					</td>
					<th><spring:message code="clm.page.msg.manage.sendRcvDiv" /></th>
					<td><c:out value="${lomRq2.payment_gbn_nm}" /></td>
				</tr>
				<%  strColspan="3";
				if(!"".equals(lomRq2.get("cntrt_untprc_expl"))){ //단가로 체결 여부
					strColspan="0";
					}
				%>
				<tr>
					<th><spring:message code="clm.page.msg.manage.contAmt" /></th>
					<td colspan="<%=strColspan%>">
						<input type="text" id="cntrt_amt" name="cntrt_amt" style="border:0px; width:100%;" value="<c:out value='${lomRq.cntrt_amt}' />" readonly="readonly"/>
					</td>
					<% if(!"".equals(lomRq2.get("cntrt_untprc_expl"))){  %>
					<th><spring:message code="clm.page.msg.manage.contUnitPrice" /></th>
					<td colspan="<%=strColspan%>">
						<spring:message code="clm.page.msg.manage.conclSingleAmt" />
					</td>
					<% } %>
					<th><spring:message code="clm.page.msg.manage.curr" /></th>
					<td><c:out value="${lomRq2.crrncy_unit}" /></td>
				</tr>
				<%if(!"".equals(lomRq2.get("cntrt_untprc_expl"))){ //단가로 체결 여부 %>
				<tr>
					<th rowspan="2"><spring:message code="clm.page.msg.manage.singleAmtSum" /></th>
					<td colspan="5">
						<c:out value="${lomRq2.cntrt_untprc_expl }" escapeXml="false"/>
					</td>
				</tr>
				<tr>
					<td colspan="5"  class="tal_lineL">
						<iframe src="<c:url value='/clm/blank.do' />" id="fileList3" name="fileList3" frameborder="0" width="100%" height="80px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
					</td> 
				</tr>
				
				<%} %>
				<tr>
					<th><spring:message code="clm.page.msg.manage.bg" /></th>  <!-- 추진목적 및 배경 -->					
					<td colspan="5" >					
						<textarea id="if_cnsd_bg_cont_textarea" name="cnsd_bg_cont_textarea" style="display: none;"><c:out value="${lomRq2.pshdbkgrnd_purps}" /></textarea>
						<iframe id="if_cnsd_bg_cont" name="cnsd_bg_cont" src="<c:url value='/clm/blank.do' />" frameborder="0"  style="width:100%"></iframe>													  
					</td>
				</tr>
				<tr>
					<th><spring:message code="clm.page.msg.manage.etcMain" /></th>
					<td colspan="5" >					
						<c:out value="${lomRq2.etc_main_cont}" escapeXml="false"/>
						<c:if test="${!empty lomRq2.if_sys_cd}"> [<c:out value="${lomRq2.if_sys_cd}" escapeXml="false" />]</c:if>
					</td>
				</tr>
				<!-- 법무 회신 최종본 회신 완료시  표시 해야할 항목 임당~~ -->				
				<c:if test='${lomRq2.cnsd_status == "C04305" && lomRq2.plndbn_req_yn == "Y"}' >
				<c:if test="${!empty lomRq2.oblgt_lmt}">
				<tr>
					<th><spring:message code="clm.page.msg.manage.limitResp" /></th>
					<td colspan="5"><c:out escapeXml="false" value="${lomRq2.oblgt_lmt}" /></td>
				</tr>
				</c:if>
				<c:if test="${!empty lomRq2.spcl_cond}">
				<tr>
					<th><spring:message code="clm.page.msg.manage.etcSpecial" /></th>
					<td colspan="5"><c:out escapeXml="false" value="${lomRq2.spcl_cond}" /></td>
				</tr>
				</c:if>
				<tr>
					<th><spring:message code="clm.page.msg.manage.properLaw" /></th>
					<td><c:out value="${lomRq2.loac_nm}" /></td>
					<th><spring:message code="clm.page.msg.manage.properLawDtl" /></th>
					<td colspan="3"><c:out escapeXml="false" value="${lomRq2.loac_etc}" /></td>
				</tr>
				<tr>
					<th><spring:message code="clm.page.msg.manage.method" /></th>
					<td><c:out value="${lomRq2.dspt_resolt_mthd_nm}" /></td>
					<th><spring:message code="clm.page.msg.manage.methodDetail" /></th>
					<td colspan="3"><c:out escapeXml="false" value="${lomRq2.dspt_resolt_mthd_det}" /></td>
				</tr>
				</c:if>
				<!-- 법무 회신 최종본 회신 완료시  표시 해야할 항목 임당~~ -->
				<tr>
					<th>D.P. Agreement</th>
					<td colspan="5">
						<!-- <c:out value="${lomRq2.cnclsnpurps_midclsfcn_etc}" /> -->
						<c:if test="${lomRq2.cnclsnpurps_midclsfcn_etc == 'Y'}">
							YES
						</c:if>
						<c:if test="${lomRq2.cnclsnpurps_midclsfcn_etc != 'Y'}">
							NO
						</c:if>
					</td>
				</tr>
			</table>
			
			<!-- 2014-04-03 Kevin Added. GERP Information -->
			<iframe id="iframeGERP" name="iframeGERP" src="/clm/blank.do" style="border:0; width:100%; height:115px; margin:10px 0 10px 0;" scrolling="no"></iframe>
			
			<!-- tnc include JSP -->
			<jsp:include page="/clm/manage/completion.do">
				<jsp:param name="method" value="getTncLink" />
				<jsp:param name="cntrt_id4" value='<%=lomRq.get("cntrt_id") %>'/>
			</jsp:include>
			
			<div class='title_basic3'><spring:message code="clm.page.msg.manage.preApprInf" /></div>
			<table class="table-style01">
				<colgroup>
					<col width="12%" />
					<col width="38%" />
					<col width="12%" />
					<col width="38%" />
				</colgroup>
				<tr>
					<th><spring:message code="clm.page.msg.manage.apprDt" /></th>
					<td><c:out value='${lomRq2.bfhdcstn_apbtday}'/></td>
					<th><spring:message code="clm.page.msg.manage.apprType" /></th>
					<td><c:out value="${lomRq2.BFHDCSTN_APBT_MTHD_NM}"/></td>
				</tr>
				<tr>
					<th><spring:message code="clm.page.msg.manage.proposer" /></th>
					<td>
						<c:if test="${lomRq2.bfhdcstn_mtnman_nm != ''}">
							<c:out value="${lomRq2.bfhdcstn_mtnman_nm}"  escapeXml="false"/>/ <c:out value='${lomRq2.bfhdcstn_mtnman_jikgup_nm}'/>/ <c:out value='${lomRq2.bfhdcstn_mtn_dept_nm}'/>
						</c:if>
					</td>
					<th><spring:message code="clm.page.msg.manage.apprPer" /></th>
					<td>
						<c:if test="${lomRq2.bfhdcstn_apbtman_nm != ''}">
							<c:out value='${lomRq2.bfhdcstn_apbtman_nm}' escapeXml="false"/>/ <c:out value='${lomRq2.bfhdcstn_apbtman_jikgup_nm}'/>/ <c:out value='${lomRq2.bfhdcstn_apbt_dept_nm}'/>
						</c:if>
					</td>
				</tr>
				<tr>
					<th><spring:message code="clm.page.msg.manage.attachData" /></th>
					<td colspan="3">
						<spring:message code="clm.page.msg.manage.announce097" />					
						<!-- Sungwoo replacement height size 2014-07-03-->
						<iframe src="<c:url value='/clm/blank.do' />" id="fileList4" name="fileList4" frameborder="0" class='addfile_iframe_d'  width="100%" height="100%" scrolling="auto" allowTransparency="true"></iframe>
					</td>
				</tr>
			</table>
			
			<div class='title_basic3'><spring:message code="clm.page.msg.manage.relContInf" /></div>
			<table id="tbl_rel_contract" cellspacing="0" cellpadding="0" border="0" class="table-style01">
				<colgroup>
						<col width="17%" />
						<col width="40%" />
						<col width="14%" />
						<col width="20%" />
						<col/>
				</colgroup>
				<tr>
					<th class="tC"><spring:message code="clm.page.msg.manage.relation" /></th>
					<th class="tC"><spring:message code="clm.page.msg.manage.relCont" /></th>
					<th class="tC"><spring:message code="clm.page.msg.manage.define" /></th>
					<th class="tC"><spring:message code="clm.page.msg.manage.relDetail" /></th>					
					<th class="tC"></th>									
				</tr>				
				<c:out value="${contRc}" escapeXml="false"/>
			</table>
			
			<%
         		if("Y".equals(lomRq2.get("plndbn_req_yn"))){ //최종본의뢰여부
         	%>
            <!-- 15개 항목 상세 -->
			<jsp:include page="/clm/review/considerationHQ.do">
				<jsp:param name="method" value="getCheckList" />
				<jsp:param name="cntrt_id3" value='<%=lomRq.get("cntrt_id") %>'/>
			</jsp:include>
            <%} %>
			
			<!-- 이력정보 -->
			<div id="contractHis-list"></div>
			<!-- //이력정보 -->	
				
			<div class="t_titBtn">
			<div class="btn_wrap_c">	
			<c:if test='${lomRq.close_yn ne "Y"}'>	  
				<c:if test="${command.session_user_id == lomRq.reqman_id}">
				<% if("C04201".equals(lomRq.get("prgrs_status")) && "C02601".equals(lomRq.get("depth_status")) ){ %>
					<c:choose>					
						<c:when test="${lomRq.plndbn_req_yn == 'N' && lomRq.prev_cnsdreq_id != ''}">
						<span class="btn_all_w"><span class="check2"></span><a href="javascript:forwardConsideration('again');"><spring:message code="clm.page.msg.common.modify" /></a></span>
						</c:when>
						<c:otherwise> 
						<span class="btn_all_w"><span class="check2"></span><a href="javascript:forwardConsideration('mod');"><spring:message code="clm.page.msg.common.modify" /></a></span>
						</c:otherwise>
					</c:choose>	
					<c:if test="${lomRq.prev_cnsdreq_id !=''}">
					<span class="btn_all_w"><span class="check2"></span><a href="javascript:actionConsideration('saveCancelRequest');"><spring:message code="clm.page.msg.manage.cnclTmpSave" /></a></span>
					</c:if>						
				<% }else if(!"Y".equals(lomRq.get("plndbn_req_yn")) && "C04305".equals(lomRq.get("cnsd_status")) && (!"C02607".equals(lomRq.get("depth_status")))){%>
					<span class="btn_all_w"><span class="check2"></span><a href="javascript:prevCnsdReqCopyConsideration('again');"><spring:message code="clm.page.msg.manage.reply" /></a></span>
				<%  }else if("C02608".equals(lomRq.get("depth_status"))  || "C04215".equals(lomRq.get("prgrs_status")) || "C04216".equals(lomRq.get("prgrs_status"))){  %>
					<%if("Y".equals(lomRq.get("plndbn_req_yn")) && "C04207".equals(lomRq.get("prgrs_status"))){ //품의작성 버튼 %>
						<%if("CPCEX".equals(lomRq.get("sys_nm")) || "CMS".equals(lomRq.get("sys_nm"))){ }else{ //연계건은 품의작성버튼을 막는다%>
							<span class="btn_all_w"><span class="reply"></span><a href="javascript:forwardConsultation('consult');"><spring:message code="clm.page.button.contract.modifyconsultation" /></a></span>
					<%  } %>
					<%  } %>
				<%  } %>		 
				
				<%if(!"Y".equals(lomRq.get("plndbn_req_yn")) && "C04305".equals(lomRq.get("cnsd_status"))){%>		
				<%}else if("C02608".equals(lomRq.get("depth_status"))  || "C04215".equals(lomRq.get("prgrs_status")) || "C04216".equals(lomRq.get("prgrs_status"))){  %>
					<span class="btn_all_w"><span class="check2"></span><a href="javascript:prevCnsdReqCopyConsideration('again');"><spring:message code="clm.page.msg.manage.reply" /></a></span>
				<%} %>	
				
				<% if("C04202".equals(lomRq.get("prgrs_status")) || "C04203".equals(lomRq.get("prgrs_status")) || "C04204".equals(lomRq.get("prgrs_status")) || "C04205".equals(lomRq.get("prgrs_status")) || "C04206".equals(lomRq.get("prgrs_status")) || "C04207".equals(lomRq.get("prgrs_status")) ){	//작성중 빼고 보류가능%>
					<c:choose>						
						<c:when test="${lomRq.defer_btn == 'defer' }">
							<span id ="id_defer" class="btn_all_w"><span class="reject"></span><a href="javascript:actionConsideration('deferRequest');" title="<spring:message code="clm.page.msg.manage.tempConcl" htmlEscape="true" />"><spring:message code="clm.page.msg.manage.delay" /></a></span>
						</c:when>
						<c:otherwise> 
							<span class="btn_all_w"><span class="check2"></span><a href="javascript:prevCnsdReqCopyConsideration('again');"><spring:message code="clm.page.msg.manage.reply" /></a></span>
						</c:otherwise>
					</c:choose>
				<% }else if( (! "TNC".equals(lomRq.get("sys_nm"))) && "".equals(lomRq.get("prev_cnsdreq_id")) && "C04201".equals(lomRq.get("prgrs_status")) && "C02601".equals(lomRq.get("depth_status")) ){ %>				
				<span id ="id_delete"  class="btn_all_w"><span class="delete"></span><a href="javascript:actionConsideration('deleteRequest');"><spring:message code="clm.page.msg.common.delete" /></a></span>
				<% }%>
				</c:if>
			</c:if>
				<span class="btn_all_p ml_10"><span class="print"></span><a href="javascript:considerationPreview();"><spring:message code="clm.page.msg.manage.print" /></a></span> <!-- 인쇄 -->
				<span class="btn_all_p"><span class="list"></span><a href="javascript:forwardConsideration('list');"><spring:message code="clm.page.msg.manage.list" /></a></span> <!-- 목록 -->
				</div>
				</div>
				<!-- //button -->
		</form:form>
		</div>
		</div>
		</div>
		<!-- //content_id -->
		</div></div>
		<!-- //content -->

	<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- // footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</body>
</html>