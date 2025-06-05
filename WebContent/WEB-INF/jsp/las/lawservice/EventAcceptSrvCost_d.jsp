<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>
<%@ page import="com.sds.secframework.common.util.Token"%>

<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%--
/**
 * 파  일  명 : EventAcceptSrvCost_d.jsp
 * 프로그램명 : 용역비 상세 화면
 * 설      명 :  용역비 상세를 조회한다.
 * 작  성  자 :  박병주
 * 작  성  일 : 2011.10
 */
--%>
<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet" />

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>

<script language="javascript" src="<c:url value='/script/clms/menu.js'/>"></script>

<script language="javascript">
<!--
	/**
	* 초기화면 로딩
	*/
	$(document).ready(function(){
		
		var frm = document.frm;
		// 첨부파일창 load
		initPage();
		
		// 날짜 textbox
 		$('*').attr('class',function(index){
			if($(this).attr("class") == 'text_calendar02'){
				initCal($(this).attr("id"));
			}
		});
		
		// 금액관련 정렬
		$('*').attr('alt',function(index){
			if($(this).attr("alt") == 'money'){
				$(this).css('text-align','right');					
			}
		}); 
		
		// 사건구분1
		getCodeSelectByUpCd2("frm", "event_gbn1", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+"106"+"&combo_abbr=F&combo_type=T&combo_use_yn=Y&combo_selected="+"<c:out value='${lom.event_gbn1}'/>");
		
		// 사건구분2
		getCodeSelectByUpCd2("frm", "event_gbn2", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+"<c:out value='${lom.event_gbn1}'/>"+"&combo_abbr=F&combo_type=T&combo_use_yn=Y&combo_selected="+"<c:out value='${lom.event_gbn2}'/>");
	
		// 화폐단위
		// getCodeSelectByUpCd2("frm", "crrncy_unit", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+"C5"+"&combo_abbr=F&combo_type=T&combo_use_yn=Y&combo_selected="+"<c:out value='${lom.crrncy_unit}'/>");
	}); 	
	//초기화면 로딩 끝
	
	/**
	* INVOICE 기등록 여부 확인 정보를  Ajax로 호출
	*/
	function srch_invoice(invoice_no) {
				     		
		if($.trim($("#invoice_no").val()) == ''){
			alert("<spring:message code='las.page.msg.lawservice.invoiceno' />");
			$("#invoice_no").val("");
			return;
		}
		
		if(!checkMaxLength($("#invoice_no").val(),"24")){
			alert("<spring:message code='las.page.msg.lawservice.maxinvoicenum' />");
			$("#invoice_no").val("");
			return;
		}
		
		check_expform();
		
		var options = {
				url: "<c:url value='/las/lawservice/eventAcceptSrvCost.do?method=srchInvoiceNo' />",
				type: "post",
				dataType: "json",
				success: function(responseText, statusText) {
					if(responseText.returnCnt != 0) {
						$.each(responseText, function(entryIndex, entry) {
							if(entry['acpt_no'] != '') {
								detailInvoiceView(entry['acpt_no']);
							} else {
								alert("<spring:message code='las.page.field.lawservice.noResult'/>");
							}
						});
				} else {
					alert("<spring:message code='las.page.field.lawservice.noResult'/>");
				}
			}
		}
		
		$("#frm").ajaxSubmit(options);	
	}
	
	/**
	* 첨부파일창 load
	*/
	function initPage(){

	    frm.target          = "fileList";
	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    = "forwardClmsFilePage";
	    frm.submit();   
	}
	
 	/**
	* 환율적용날짜의 환율정보를  Ajax로 호출
	*/
	function check_rate() {
 		
 		var check_cnt = 0;
 		var check_rate_cnt = 0;
 		var usrate = 0.00;
 		var exrate = 0.00;
 		
 		var exp_pre_amount = 0.00; // (totamt * exrate); // 원화예상송금금액
 		var exp_usd_amount = 0.00; // exp_pre_amount / usrate; //USD환산
 		var totamt = 0.00; 
 		
 		if($.trim($("#unpayday").val()) == ''){ 			
 			alert("<spring:message code='las.page.msg.lawservice.unpayalert' />");
  			return;
 		} else {
 			$("#unpayday2").val($("#unpayday").val());
 			$("#c_dt").val($("#unpayday").val().replace(/-/gi,''));
 			$("#unpayday_flag").val("checekd");
 		}
 		
 		check_expform();
 		 		
		var options = {
				url: "<c:url value='/las/lawservice/eventAcceptSrvCost.do?method=checkRateAjax' />",
				type: "post",
				dataType: "json",
				success: function(responseText, statusText) {
					if(responseText.returnCnt != 0) {
						$.each(responseText, function(entryIndex, entry) {
							usrate = entry['usrate'];
							exrate = entry['exrate'];
							check_rate_cnt = entry['check_rate_cnt'];
						});
						
						if(check_rate_cnt == 0){
				 			$("#unpayday").val("");
							$("#unpayday_flag").val(""); 	
							alert("<spring:message code='las.page.msg.lawservice.norateinfoalert' />");
							return;
						} else {
 							$('#src_tb tr input').each(function(index) {
	 					 		var exp_pre_amount = 0.00; // (totamt * exrate); // 원화예상송금금액
						 		var exp_usd_amount = 0.00; // exp_pre_amount / usrate; //USD환산
								
						 		if($(this).attr('id')=='totamt'){
						 			var crrncyUnit = $.trim($("#crrncy_unit").val());
						 			totamt = $(this).val();
							 		
						 			//2012-09-04 엔화일 경우 100을 나눠줘야 원화가 된다.
						 			if(crrncyUnit == 'JPY'){
						 				exp_pre_amount = totamt * exrate / 100;
						 			}else if(crrncyUnit == 'KRW'){
						 				exp_pre_amount = totamt;	
						 			}else{
						 				exp_pre_amount = totamt * exrate;
						 			}
							 		exp_usd_amount = exp_pre_amount / usrate;
							 		
							 		if(crrncyUnit == 'KRW'){
							 			exp_pre_amount = exp_pre_amount;
							 		}else{
							 			exp_pre_amount = exp_pre_amount.toFixed(2);
							 		}
							 		exp_usd_amount = exp_usd_amount.toFixed(2);
							 		
							 		exp_pre_amount = Comma(exp_pre_amount);
							 		exp_usd_amount= Comma(exp_usd_amount);
							 		
							 		$(this).parent().parent().next().children().children().val(exp_pre_amount);
							 		$(this).parent().parent().next().children().next().children().val(exp_usd_amount);
							 										 		
							 		$('#exrate').val(exrate);
							 		$('#usrate').val(usrate);			
						 		}
							}); 
 							
 							$('#src_result tr input').each(function(index) {
	 					 		var exp_pre_amount = 0.00; // (totamt * exrate); // 원화예상송금금액
						 		var exp_usd_amount = 0.00; // exp_pre_amount / usrate; //USD환산
								
						 		if($(this).attr('id')=='totamt'){
						 			var crrncyUnit = $.trim($("#crrncy_unit").val());
						 			
						 			totamt = $(this).val();
							 		//2012-09-04 엔화일 경우 100을 나눠줘야 원화가 된다.
						 			if(crrncyUnit == 'JPY'){
						 				exp_pre_amount = totamt * exrate / 100;
						 			}else{
						 				exp_pre_amount = totamt * exrate;
						 			}
							 		exp_usd_amount = exp_pre_amount / usrate;
							 		
							 		exp_pre_amount = exp_pre_amount.toFixed(2);
							 		exp_usd_amount = exp_usd_amount.toFixed(2);
							 		
							 		exp_pre_amount = Comma(exp_pre_amount);
							 		exp_usd_amount = Comma(exp_usd_amount);
							 		
							 		$(this).parent().next().next().children().val(exp_pre_amount);
							 		$(this).parent().next().next().next().next().children().val(exp_usd_amount);
		
						 		}
							});
 							
					 		var plnd_remit_amt_all = 0.00;
					 		var totamt_all = 0.00;

 					 		$('#src_result tr input[name=totamt]').each(function(index,elem) {
 					 			var x = parseFloat($(elem).val());
					 			totamt_all = totamt_all +  x ;
					 		}); 

 					 		totamt_all = totamt_all.toFixed(2);
 					 		plnd_remit_amt_all = totamt_all* exrate;
 					 		
 					 		totamt_all = Comma(totamt_all);
 					 		plnd_remit_amt_all= Comma(plnd_remit_amt_all);
 					 		
					 		$('#claim_amt').val(totamt_all);
					 		$('#remit_amt').val(totamt_all);
					 		$('#tot_remit_amt').val(plnd_remit_amt_all);
						}
						
				} else {
					
					alert("<spring:message code='las.page.field.lawservice.exchngNoInfo'/>");					
					$("#unpayday").val("");
					$("#unpayday_flag").val("");					
					return;
				}
			}
		}
		
		$("#frm").ajaxSubmit(options);	
	}
 	
 	/**
	* 미지급일자 저장
	*/
	function updateUnpayday() {
 		
		if($.trim($("#unpayday").val()) == ''){
 			alert("<spring:message code='las.page.msg.lawservice.unpayalert' />");
 			return;
		} 
		
		if($.trim($("#unpayday_flag").val()) == ''){
 			alert("<spring:message code='las.page.msg.lawservice.excuterate' />");
 			return;
		}
		
		if(confirm("<spring:message code='secfw.msg.ask.insert' />")){
			
			$("#unpayday").val($("#unpayday").val().replace(/-/gi,''));
	    	
        	viewHiddenProgress(true) ;
        	check_expform();
	    	frm.method.value = "updateUnpayday";
			frm.target = "_self";
			frm.action = "<c:url value='/las/lawservice/eventAcceptSrvCost.do' />";
		  	frm.submit();	

        } else {
	    	return;	
	    } 	
 	}
 	
 	function updateRemitday(){
 		
 		if($.trim($("#remitday").val()) == ''){
 			alert("<spring:message code='las.page.msg.lawservice.inputremitday' />");
  			return;
 		} else {
 			
 			if(confirm("<spring:message code='secfw.msg.ask.insert' />")){
 				$("#remitday").val($("#remitday").val().replace(/-/gi,''));		
 		 		
 				var frm = document.frm; 				
 				viewHiddenProgress(true) ;
 				check_expform();
 				frm.target = "_self";
 				frm.action = "<c:url value='/las/lawservice/eventAcceptSrvCost.do' />";
 				frm.method.value = "updateRemitday"; 				
 				frm.submit();	
 			}		
 		}
 	}
 	
 	function updateReviewYn(){
		
		var frm = document.frm;
		
 		if(!checkMaxLength($("#review_cont").val(),"200")){
 			alert("<spring:message code='las.page.msg.lawservice.reviewsize' />");
 			$("#review_cont").val("");
 			return;
 		}
		
		if(confirm("<spring:message code='las.page.msg.lawservice.reviewconfirm' />")){
			viewHiddenProgress(true) ;
			check_expform();
			frm.target = "_self";
			frm.action = "<c:url value='/las/lawservice/eventAcceptSrvCost.do' />";
			frm.method.value = "updateReviewYn";				
			frm.submit();	
		}
 	}
	/**
	* 송금정보초기화
	*/
 	function updateRemitInit(){
		
		var frm = document.frm;
		if(confirm("<spring:message code='las.page.msg.lawservice.initremitinfo' />")){
			viewHiddenProgress(true) ;
			check_expform();
			frm.target = "_self";
			frm.action = "<c:url value='/las/lawservice/eventAcceptSrvCost.do' />";
			frm.method.value = "updateRemitInit";				
			frm.submit();	
		}	
 	}
 	
 	function updateCurrencyInfo(){
		
		var frm = document.frm;
		if(confirm("<spring:message code='las.page.field.lawservice.chkexchngInfoRenew'/>")){
			viewHiddenProgress(true) ;
			check_expform();
			frm.target = "_self";
			frm.action = "<c:url value='/las/lawservice/eventAcceptSrvCost.do' />";
			frm.method.value = "updateCurrencyInfo";				
			frm.submit();	
		}	
 	}
	
	/**
	* 용역비 상세화면으로 가기
	*/
	function detailInvoiceView(acpt_no){
		var frm = document.frm;
		
		viewHiddenProgress(true) ;
		check_expform();
		frm.acpt_no.value		= acpt_no;
		frm.target = "_self";
		frm.action = "<c:url value='/las/lawservice/eventAcceptSrvCost.do' />";
		frm.method.value = "detailEventAcceptSrvCost";
		
		frm.submit();		
	}
	
	/**
	* 목록으로 가기
	*/
	function goList() {
		var frm = document.frm;
		check_expform();
		<c:choose>
			<c:when test="${command.forward_from ==  '2'}">
				frm.action = "<c:url value='/las/lawservice/lawServiceSearch.do' />";
				frm.method.value = "listLawServiceSearch";
			</c:when>
			<c:otherwise>
				frm.action = "<c:url value='/las/lawservice/eventManage.do' />";
				frm.method.value = "detailEventManage";
			</c:otherwise>
		</c:choose>
		viewHiddenProgress(true);
		frm.target = "_self";
		frm.submit();
	}
	
	/**
	* 용역비 입력화면으로 가기
	*/
	function goSrvInsert(){
		
		var frm = document.frm;
		
		viewHiddenProgress(true);
		check_expform();
		frm.method.value = "forwardEventAcceptSrvCostInsert";
		frm.action = "<c:url value='/las/lawservice/eventAcceptSrvCost.do' />";
		frm.target = "_self";
		frm.submit();

	}
	
	/**
	* 수정 화면으로 가기
	*/
	function modify() {
		var frm = document.frm;
		viewHiddenProgress(true);
		check_expform();
		frm.method.value = "forwardEventAcceptSrvCostModify";		
		frm.action = "<c:url value='/las/lawservice/eventAcceptSrvCost.do' />";
		frm.target = "_self";
		frm.submit();
	}
	
	/**
	* 삭제처리
	*/
	function remove(){
		var frm = document.frm;
		
		if(confirm("<spring:message code='secfw.msg.ask.delete' />")) {
			
			viewHiddenProgress(true);
			check_expform();
			frm.method.value = "deleteEventAcceptSrvCost";
			frm.action = "<c:url value='/las/lawservice/eventAcceptSrvCost.do' />";
			frm.target = "_self";
			frm.submit();
		}
	}
	
	/**
	* 저장시 금액 form 에서 "," 를 삭제 
	*/
	function check_expform(){
		$('*').attr('alt',function(index){
			if($(this).attr("alt") == 'money'){
				$(this).val($(this).val().replace(/,/gi,''));					
			}
		}); 
	}

	/**
	* 상세내역 조회
	*/
	function detailView(event_no){
		var frm = document.frm;
		
		viewHiddenProgress(true) ;
		frm.event_no.value		= event_no;

		frm.target = "_self";
		frm.action = "<c:url value='/las/lawservice/eventManage.do' />";
		frm.method.value = "detailEventManage";
		
		frm.submit();		
	}	
	
	//미지급일 초기화 기능
	function clearUnpayday(){
		document.getElementById('unpayday').value = '';
	}
//-->
</script>

</head>
<body>
<!-- Calendar  -->
<div id='popCal' style='position: absolute; visibility: hidden; ridge; width: 10; z-index:1'>
<iframe name="popFrame" src="<c:url value='/secfw/popCalendar.do?method=goPopCalendar' />" frameborder="0" scrolling="no" width="196" height="188"></iframe></div>
<script event=onclick() for=document> 
    if(popCal.style.visibility == "visible"){
      popCal.style.visibility="hidden";
    }
</script>

<form:form name="frm" id="frm" method="post" autocomplete="off">
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />
<input type="hidden" name="forward_from"  value="<c:out value='${command.forward_from}'/>" />
<input type="hidden" name="forward_url"   value="" />

<!-- URL 이동시 사용 -->
<input type="hidden" name="targetMenuId">
<input type="hidden" name="selected_menu_id">
<input type="hidden" name="selected_menu_detail_id">
<input type="hidden" name="set_init_url">

<!-- search form -->
<input type="hidden" name="srch_event_no"   value="<c:out value='${command.srch_event_no}'/>" />
<input type="hidden" name="srch_event_nm"   value="<c:out value='${command.srch_event_nm}'/>" />
<input type="hidden" name="srch_lawfirm_nm"   value="<c:out value='${command.srch_lawfirm_nm}'/>" />  
<input type="hidden" name="srch_lawfirm_id"   value="<c:out value='${command.srch_lawfirm_id}'/>" />
<input type="hidden" name="srch_kbn1"   value="<c:out value='${command.srch_kbn1}'/>" />
<input type="hidden" name="srch_kbn2"   value="<c:out value='${command.srch_kbn2}'/>" />
<input type="hidden" name="srch_lwr_nm"   value="<c:out value='${command.srch_lwr_nm}'/>" />
<input type="hidden" name="srch_reg_nm"   value="<c:out value='${command.srch_reg_nm}'/>" />
<input type="hidden" name="srch_start_dt"   value="<c:out value='${command.srch_start_dt}'/>" />
<input type="hidden" name="srch_end_dt"   value="<c:out value='${command.srch_end_dt}'/>" />
<input type="hidden" name="srch_order_type"   value="<c:out value='${command.srch_order_type}'/>" />


<input type="hidden" name="srch_unpay_yn"   value="<c:out value='${command.srch_unpay_yn}'/>" />
<input type="hidden" name="srch_remit_yn"   value="<c:out value='${command.srch_remit_yn}'/>" />
<input type="hidden" name="srch_lawsuit_trgt_cd"   value="<c:out value='${command.srch_lawsuit_trgt_cd}'/>" />
<input type="hidden" name="srch_review_yn"   value="<c:out value='${command.srch_review_yn}'/>" />
<input type="hidden" name="srch_group_cd"   value="<c:out value='${command.srch_group_cd}'/>" />
<input type="hidden" name="srch_acpt_start_dt"   value="<c:out value='${command.srch_acpt_start_dt}'/>" />
<input type="hidden" name="srch_acpt_end_dt"   value="<c:out value='${command.srch_acpt_end_dt}'/>" />
<input type="hidden" name="srch_unpay_start_dt"   value="<c:out value='${command.srch_unpay_start_dt}'/>" />
<input type="hidden" name="srch_unpay_end_dt"   value="<c:out value='${command.srch_unpay_end_dt}'/>" />
<input type="hidden" name="srch_remit_start_dt"   value="<c:out value='${command.srch_remit_start_dt}'/>" />
<input type="hidden" name="srch_remit_end_dt"   value="<c:out value='${command.srch_remit_end_dt}'/>" />

<!-- key form-->
<input type="hidden" name="event_no" id="event_no" value="<c:out value='${lom.event_no}'/>" />
<input type="hidden" name="event_nm" id="event_nm" value="<c:out value='${lom.event_nm}'/>" />
<input type="hidden" name="lawsuit_trgt_nm" id="lawsuit_trgt_nm" value="<c:out value='${lom.lawsuit_trgt_nm}'/>" />
<input type="hidden" name="acpt_no" id="acpt_no" value="<c:out value='${lom.acpt_no}'/>" />
<input type="hidden" name="c_dt" id="c_dt" value="" required alt="<spring:message code="las.page.field.lawservice.crateday"/>" />
<input type="hidden" name="unpayday_flag" id="unpayday_flag" value="" />

<!-- 첨부파일정보 시작 -->
<input type="hidden" name="fileInfos"   value="" />
<input type="hidden" name="file_bigclsfcn"  value="F005" />
<input type="hidden" name="file_midclsfcn" 	value="F00504" />
<input type="hidden" name="file_smlclsfcn"  value="" />
<input type="hidden" name="ref_key"     value="<c:out value='${lom.acpt_no}'/>" />
<input type="hidden" name="view_gbn"    value="download" />

<div id="wrap">	
	<!-- container -->
<div id="container">
	<!-- Location -->
	<div class="location"><IMG SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
	<!-- //Location -->	
	<!-- title -->
	<div class="title">
		<h3><spring:message code="las.page.field.lawservice.srvcost"/>&nbsp;<spring:message code="las.page.field.lawservice.detail" /></h3>
	</div>
	<!-- //title -->
	<div class="btn_wrap">
		<c:if test="${command.lws_auth_modify}">
			<span class="btn_all_w"><span class="edit"></span><a href="javascript:modify()"><spring:message code="las.page.button.modify" /></a></span>
		</c:if>
		<c:if test="${command.lws_auth_delete}">
			<span class="btn_all_w"><span class="delete"></span><a href="javascript:remove()"><spring:message code="las.page.button.delete" /></a></span>
		</c:if>
	</div>
	<!-- content -->
	<div id="content">
		<!-- view -->
		<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
			<colgroup>
				<col width="15%" />
				<col width="35%" />
				<col width="15%" />
				<col width="35%" />
			</colgroup>
			<tbody>
				<tr>
					<th><spring:message code="las.page.field.lawservice.eventnm" /></th>
					<td colspan="3">
					<c:out value='${lom.event_nm}'/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.kbn" /></th>
					<td>
						<select id="event_gbn1" name="event_gbn1" style="width:150px;" disabled >
						</select>
						<select id="event_gbn2" name="event_gbn2" style="width:100px;" disabled >
						</select>
					</td>
					<th><spring:message code="las.page.field.lawservice.acptday" /></th>
					<td>
						<c:out value='${lom.acptday}'/>
					</td>
				</tr>
				<tr>	
					<th><spring:message code="las.page.field.lawservice.lawsuittrgt" /></th>
					<td>
						<c:out value='${lom.lawsuit_trgt_nm}'/>
					</td>
					<th><spring:message code="las.page.field.lawservice.incharge" /></th>
					<td>
						<c:out value='${lom.reg_nm}'/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.lawfirm" /></th>
					<td colspan="3">
						<c:out value='${lom.lawfirm_nm}'/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.lwr" /></th>
					<td colspan="3">						
						<c:forEach var="list" items="${lwr_list_event}"  varStatus="status">
							<a href='javascript:Menu.detail("frm", "_top", "20110804083733310_0000000168", "20110804083733310_0000000168", "/las/lawservice/lawyerManage.do?method=detailLawyerManage&lwr_no=<c:out value='${list.lwr_no}'/>")' >
								<c:out value='${list.lwr_nm}' /></a>
							<c:if test="${status.count > 0}">
								&nbsp;&nbsp;&nbsp;
							</c:if>
							<c:if test="${status.count mod 5==0}">
								</br>
							</c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th>INVOICE NO</th>
					<td>
						<c:out value='${lom.invoice_no}'/>
					</td>
					<th><spring:message code="las.page.field.lawservice.srvfromto"/></th>
					<td>
						<c:out value='${lom.srvcstartday}'/>
						~ 
						<c:out value='${lom.srvcendday}'/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.claimday"/></th>
					<td colspan="3">
						<c:out value='${lom.claimday}'/>
					</td>
				</tr>
				<tr>
					<th ><spring:message code="las.page.field.lawservice.claimamt"/></th>
					<td colspan="3" >
					
					<c:choose>
						<c:when test="${empty srv_cost_list}">
							<spring:message code="las.page.msg.lawservice.noclaimamt" />
						</c:when>
						<c:otherwise>
			
							<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-stylesub" id="src_tb">
			  					<colgroup>
							  <col width="18%" />
							  <col width="18%" />
							  <col width="18%" />
							  <col width="18%" />
							  <col width="20%" />
							</colgroup>
			  				<c:forEach var='list' items='${srv_cost_list}' varStatus='status'>
			  					<input type="hidden" id="dept_nm" name="dept_nm" value="<c:out value='${list.dept_nm}'/>" ></input>	
								<input type="hidden" id="intnl_dept_cd" name="intnl_dept_cd" value="<c:out value='${list.intnl_dept_cd}'/>" ></input>	
								<input type="hidden" id="grp_dept_cd" name="grp_dept_cd" value="<c:out value='${list.grp_dept_cd}'/>" ></input>		
							    <tr id="src_tbr">
							      <td id="fee" >fee.
							        <input type="text" id="srvc_amt" name="srvc_amt" value="<fmt:formatNumber value='${list.srvc_amt}' pattern='#,#00.00#'/>" width="100px" readonly alt="money" /></td>
							      <td id="dis" >Dis.
							        <input type="text" name="addtnl_amt" id="addtnl_amt" value="<fmt:formatNumber value='${list.addtnl_amt}' pattern='#,#00.00#'/>" width="100px" readonly alt="money"/></td>
							      <td id="sum" >SUM
							        <input type="text" name="totamt" id="totamt" value="<fmt:formatNumber value='${list.totamt}' pattern='#,#00.00#'/>" width="100px" readonly alt="money" /></td>
							      <td><spring:message code="las.page.field.lawservice.dcrate"/>				        
						          <input type="text" name="dc_rate" id="dc_rate" value="<c:out value='${list.dc_rate}'/>" width="100px" readonly alt="money" /></td>
							      <th rowspan="2"><c:out value='${list.dept_nm}'/></th>
						        </tr>
							    <tr id="src_tbr2" >
							      <td colspan="2"><spring:message code="las.page.field.lawservice.plndremitamt"/>
							      	<input type="text" name="plnd_remit_amt2" id="plnd_remit_amt2" value="<fmt:formatNumber value='${list.plnd_remit_amt}' pattern='#,#00.00#'/>" width="100px" readonly alt="money" /></td>
							      <td colspan="2"><spring:message code="las.page.field.lawservice.usdamt"/>
							      <input type="text" name="usd_amt2" id="usd_amt2" value="<fmt:formatNumber value='${list.usd_amt}' pattern='#,#00.00#'/>" width="50px" readonly alt="money" /></td>
							     </tr>
						       	</c:forEach>
						    </table>
						    *<spring:message code="las.page.field.lawservice.initrate"/> : <c:out value='${lom.exrt_applyday}'/>
					        </br>
					        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-stylesub" id="src_result">
					        <colgroup>
							  <col width="20%" />
							  <col width="20%" />
							  <col width="10%" />
							  <col width="20%" />
							  <col width="10%" />
							  <col width="20%" />
					        	<tr> 
									<th><spring:message code="las.page.field.lawservice.day"/></th>
									<td><input type="text" name="unpayday2" id="unpayday2" value="<c:out value='${lom.unpayday}'/>" width="100px" /></td>
									<th><spring:message code="las.page.field.lawservice.currency"/></th>
									<td><c:out value='${lom.crrncy_unit}'/></td>
									<th><spring:message code="las.page.field.lawservice.usd"/></th>
									<td>USD</td>
								</tr>
								<c:forEach var='list' items='${srv_cost_list}' varStatus='status'>
								<tr>
									<th><c:out value='${list.dept_nm}'/>/<spring:message code="las.page.field.lawservice.amt"/></th>
									<td><input type="text" name="totamt" id="totamt" value="<fmt:formatNumber value='${list.totamt}' pattern='#,#00.00#'/>" width="100px" alt="money" /></td>
									<th><spring:message code="las.page.field.lawservice.remitamt"/></th>
									<td><input type="text" name="plnd_remit_amt" id="plnd_remit_amt" value="<fmt:formatNumber value='${list.plnd_remit_amt}' pattern='#,#00.00#'/>" width="100px" alt="money"/></td>
									<th>US<spring:message code="las.page.field.lawservice.amt"/></th>
									<td><input type="text" name="usd_amt" id="usd_amt" value="<fmt:formatNumber value='${list.usd_amt}' pattern='#,#00.00#'/>" width="100px" alt="money"/></td>
									</tr>
								</c:forEach>
								<tr>
									<th><spring:message code="las.page.field.lawservice.claimamtall"/></th>
									<td><input type="text" name="claim_amt" id="claim_amt" value="<fmt:formatNumber value='${lom.claim_amt}' pattern='#,#00.00#'/>" width="50px" alt="money" /></td>
									<th><spring:message code="las.page.field.lawservice.remitamtall"/></th>
									<td><input type="text" name="tot_remit_amt" id="tot_remit_amt" value="<fmt:formatNumber value='${lom.remit_amt}' pattern='#,#00.00#'/>" width="50px" alt="money" /></td>
									<th><spring:message code="las.page.field.lawservice.rateusd"/></th>
									<td><input type="text" name="usrate" id="usrate" value="" width="50px" alt="money" /></td>
								</tr>
								<tr>
									<th><spring:message code="las.page.field.lawservice.rate"/> <c:out value='${lom.crrncy_unit}'/></th>
									<td><input type="text" name="exrate" id="exrate" value="" width="50px" alt="money" /></td>
									<td colspan="4" align="right" > 
	
									</td>
								</tr>
								<tr>
					        </table>						
						</c:otherwise>
					</c:choose>						

					</td>
				</tr>
				<tr>
					<th ><spring:message code="las.page.field.lawservice.claimamt"/></th>
					<td>				
						 <fmt:formatNumber value='${lom.claim_amt}' pattern='#,#00.00#'/>
					</td>
					<th><spring:message code="las.page.field.lawservice.cunit"/></th>
					<td>
						<select id="crrncy_unit" name="crrncy_unit" style='width:154px;' readonly >
							<option value='AUD' <c:if test="${lom.crrncy_unit == 'AUD'}"> selected </c:if>><spring:message code="las.page.field.lawservice.aud"/></option>
							<option value='CAD' <c:if test="${lom.crrncy_unit == 'CAD'}"> selected </c:if>><spring:message code="las.page.field.lawservice.cad"/></option>
							<option value='CHF' <c:if test="${lom.crrncy_unit == 'CHF'}"> selected </c:if>><spring:message code="las.page.field.lawservice.cchf"/></option>
							<option value='DEM' <c:if test="${lom.crrncy_unit == 'DEM'}"> selected </c:if>><spring:message code="las.page.field.lawservice.dem"/></option>
							<option value='EUR' <c:if test="${lom.crrncy_unit == 'EUR'}"> selected </c:if>><spring:message code="las.page.field.lawservice.eur"/></option>
							<option value='FRF' <c:if test="${lom.crrncy_unit == 'FRF'}"> selected </c:if>><spring:message code="las.page.field.lawservice.frf"/></option>
							<option value='GBP' <c:if test="${lom.crrncy_unit == 'GBP'}"> selected </c:if>><spring:message code="las.page.field.lawservice.gbp"/></option>
							<option value='ITL' <c:if test="${lom.crrncy_unit == 'ITL'}"> selected </c:if>><spring:message code="las.page.field.lawservice.itl"/></option>
							<option value='JPY' <c:if test="${lom.crrncy_unit == 'JPY'}"> selected </c:if>><spring:message code="las.page.field.lawservice.jpy"/></option>
							<option value='KRW' <c:if test="${lom.crrncy_unit == 'KRW'}"> selected </c:if>><spring:message code="las.page.field.lawservice.krw"/></option>
							<option value='USD' <c:if test="${lom.crrncy_unit == 'USD'}"> selected </c:if>><spring:message code="las.page.field.lawservice.cusd"/></option>
						</select> <c:out value='${lom.crrncy_unit}'/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.plndremitday"/></th>
					<td>
						<c:out value='${lom.remitplndday}'/>
					</td>
					<th><spring:message code="las.page.field.lawservice.remitday"/></th>
					<td><c:if test="${lom.remitday == ''}">
							<input type="text" name="remitday" id="remitday" value="<c:out value='${lom.remitday}'/>" class="text_calendar02" readonly />
							<span class="btn_all_b"><span class="save"></span><a href="javascript:updateRemitday()"><spring:message code="las.page.button.lawservice.save" /></a></span>
						</c:if>						
						<c:if test="${lom.remitday != ''}">
							<c:out value='${lom.remitday}'/> 
								<c:if test="${lom.speriod > 0}">
									<span style="font-weight: bold;color:#259bb8;">(<c:out value='${lom.speriod}'/>)</span>
								</c:if>
						</c:if>
						<c:if test="${command.lws_auth_admin_access}">
							<c:if test="${lom.unpayday != ''}">
								<span class="btn_all_b"><span class="save"></span><a href="javascript:updateRemitInit()"><spring:message code="las.page.button.lawservice.initbutton" /></a></span>
							</c:if>	
							<c:if test="${command.session_user_nm=='<spring:message code='las.page.field.lawservice.ShimWookyu'/>' || command.session_user_nm=='<spring:message code='las.page.field.lawservice.HanJiyeon'/>'}">
							<span class="btn_all_b"><span class="save"></span><a href="javascript:updateCurrencyInfo()"><spring:message code="las.page.field.lawservice.renewExchngInfo"/></a></span>
							</c:if>		
						</c:if>				
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.reviewcont"/></th>
					<td colspan="3">
						<select id="review_yn" name="review_yn" >
							<option value = "N" <c:if test="${lom.review_yn == 'N'}"> selected </c:if>>N</option>
							<option value = "Y" <c:if test="${lom.review_yn == 'Y'}"> selected </c:if>>Y</option>
						</select>
						&nbsp;<input type="text" name="review_cont" id="review_cont" size="70" value="<c:out value='${lom.review_cont}'/>" alt="review" maxLength="200" />
						<span class="btn_all_b"><span class="save"></span><a href="javascript:updateReviewYn()"><spring:message code="las.page.field.lawservice.save"/></a></span>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.unpayday"/></th>
					<td>
						<c:if test="${lom.unpayday == ''}">
							<input alt="<spring:message code="las.page.field.lawservice.unpayday"/>" type="text" name="unpayday" id="unpayday" value="<c:out value='${str_cday}'/>" class="text_calendar02" readonly  />
							<img src='<%=IMAGE%>/icon/iconClear.gif' onClick="javascript:clearUnpayday();" alt="Clear"/>  
							<span class="btn_option"><span class="arrow"></span><a href="javascript:check_rate()"> <spring:message code="las.page.field.lawservice.crate"/></a></span>
							<span class="btn_all_b"><span class="save"></span><a href="javascript:updateUnpayday()"> <spring:message code="las.page.button.lawservice.save" /></a></span>
						</c:if>
						<c:if test="${lom.unpayday != ''}">
							<c:out value='${lom.unpayday}'/>  
						</c:if>
						
					</td>
					<th><spring:message code="las.page.field.lawservice.remitamt"/></th>
					<td>
						<c:if test="${lom.unpayday == ''}">
							<input type="text" name="remit_amt" id="remit_amt" value="<c:out value='${lom.remit_amt}'/>" num="" alt="money" />
						</c:if>
						<c:if test="${lom.unpayday != ''}">
							<fmt:formatNumber value='${lom.remit_amt}' pattern='#,#00.00#'/>
						</c:if>
						
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.claimcont"/></th>
					<td colspan="3">
						<c:out value='${lom.claim_cont}'/>
					</td>
				</tr>
				<tr class="end">
					<th><spring:message code="las.page.field.lawservice.attachfile" /></th>
					<td colspan="3">
						<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="100px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
					</td>
				</tr>
			</tbody>
		</table>
		<!-- //view -->
		</br>
	
		<div class="t_titBtn">
			<div class="input_list">
				Invoice No: 
				  <input alt="INVOICE NO" class="text_full" type="text"  style='width:247px;' name="invoice_no" id="invoice_no" value="<c:out value='${lom.invoice_no}'/>" required onKeyDown="javascript:if(event.keyCode==13){event.returnValue = false;srch_invoice(this.value);}"  />
	    	</div>                
      				 <!-- button -->
			<div class="btn_wrap_t fR">
				<span class="btn_all_w"><span class="save"></span><a href="javascript:goSrvInsert()"><spring:message code="las.page.button.lawservice.input"/></a></span>
			</div>
			<!-- //button --> 
		</div>				    
	
		<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
		  <colgroup>
		  <col width="7%" />
		  <col width="28%" />
		  <col width="15%" />
		  <col width="15%" />
		  <col width="15%" />
		  <col width="10%" />
		  <col width="10%" />		
		  </colgroup>
		  <thead>
			<tr>
			  <th><spring:message code="las.page.field.lawservice.order" /></th>
			  <th><spring:message code="las.page.field.lawservice.eventnm" /></th>
			  <th><spring:message code="las.page.field.lawservice.lawfirm" /></th>
			  <th><spring:message code="las.page.field.lawservice.regnm" /></th>
			  <th><spring:message code="las.page.field.lawservice.acptday" /></th>
			  <th><spring:message code="las.page.field.lawservice.unpay" /></th>
			  <th><spring:message code="las.page.field.lawservice.remityn" /></th>
			</tr>
		  </thead>
		  <tbody>
		  	 <tr>
			  <td>&nbsp;</td>
			  <td>				
				<a href='javascript:detailView("<c:out value='${lom.event_no}'/>");'>
				<c:out value='${lom.event_nm}'/></a>
			  </td>	          				
			  <td>&nbsp;</td>
			  <td><c:out value='${lom.reg_nm}'/></td>
			  <td><c:out value='${lom.reg_dt}'/></td>
			  <td>&nbsp;</td>
			  <td>&nbsp;</td>
			</tr>
			<c:choose>
			<c:when test="${invoice_list_cnt > 0}">
				<c:forEach var="list" items="${invoice_list}">
					<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
					    <td  class="tC"><c:out value='${list.rn}'/></td>
						<td  class="tL">
						
						<img src="<%=IMAGE%>/icon/icon_reply.gif" />
						<c:if test="${list.upfile_yn != 'N'}">
							<img src="<%=IMAGE%>/icon/ico_save_w.gif" />
						</c:if>
						<a href="javascript:detailInvoiceView('<c:out value='${list.acpt_no}'/>')">
								<c:choose>
									<c:when test="${!empty lom.acpt_no}">
										<c:choose>
											<c:when test="${list.acpt_no == lom.acpt_no}">
												<strong><c:out value='${list.invoice_no}'/></strong>
											</c:when>
											<c:otherwise>
												<c:out value='${list.invoice_no}'/>
											</c:otherwise>	
										</c:choose>
									</c:when>
									<c:otherwise>
										<c:out value='${list.invoice_no}'/>
									</c:otherwise>
								</c:choose>									
						</a></nobr></td>
		            	<td><c:out value='${list.lawfirm_nm}'/></td>
		            	<td><c:out value='${list.reg_nm}'/></td>
		            	<td><c:out value='${list.acptday}'/></td>
		            	<td><c:out value='${list.unpayday}'/></td>
		            	<td><c:out value='${list.remitday}'/></td>
					</tr>
				</c:forEach>
		    </c:when>
		    <c:otherwise>
				<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
					<td colspan="7" align="center"><spring:message code="las.msg.succ.noResult" /></td>
				</tr>
		    </c:otherwise>
		</c:choose>
		 </tbody>
		 </table>
		  		
		<div class="t_titBtn">
			<div class="btn_wrap">
				<span class="btn_all_w"><span class="list"></span><a href="javascript:goList()"><spring:message code="las.page.button.lawservice.golist" /></a></span>
			</div>
		</div>	
		
	</div>
	<!-- //content -->
	<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
	<!-- // footer -->
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</div>
<!-- //container -->
</div>
</form:form>
</body>
</html>	