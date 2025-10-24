<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sds.secframework.common.util.PageUtil"%>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%--
/**
 * 파  일  명 : Customer1_p.jsp
 * 프로그램명 : 계약상대방 팝업
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2014.04
 */
--%>
 
<%
	String menuNavi = (String) request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil) request.getAttribute("pageUtil");
	List customerList = (List) request.getAttribute("customerList");
	String srch_vendor_type = (String)request.getAttribute("srch_vendor_type");
	int customerCnt = 0;
	if (customerList != null && customerList.size() > 0) {
		customerCnt = customerList.size();
	}
	
	String localeCode = (String)session.getAttribute("secfw.session.language_flag");
	
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>

<html>
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
	<title><spring:message code="clm.main.title" /></title>
	<link href="<%=CSS%>/las.css"    type="text/css" rel="stylesheet" />
	<link href="<%=CSS%>/popup.css"  type="text/css" rel="stylesheet" />
	<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
	<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
	<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
	<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
	<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
	<script language="javascript">
				
	var popObj = "";
	// 2014-09-24 Kevin added.
	$(function(){
		var srchVendorType = "<%=srch_vendor_type%>";
		switch(srchVendorType){
			case "":
				$("#rdoAll").attr("checked", "checked");
				break;
			case "C":
				$("#rdoCustomer").attr("checked", "checked");
				break;
			case "V":
				$("#rdoVendor").attr("checked", "checked");
				break;
			default:
				$("#rdoAll").attr("checked", "checked");
				break;
		}	
	});
	
	/**
	* 페이징 function
	*/
	function goPage(pgNum){
		var frm = document.frm;
		var srchValue  = comTrim(frm.srch_customer.value);
		
		if(srchValue == "<spring:message code="clm.page.msg.draft.announce003" />"){
			frm.srch_customer.value = "";
		}
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/draft/customerTest.do' />";
		frm.method.value = "listCustomerTest";
		frm.curPage.value = pgNum;
		frm.page_gbn.value = "search";
		
		viewHiddenProgress(true);
		
		frm.submit();
	}
	
	/**
	* 버튼 동작 부분
	*/
	function pageAction(){
		
		var frm = document.frm;
		var srchValue = comTrim(frm.srch_customer.value);
		var srchValue3 = comTrim(frm.srch_nation.value);		
		
		if(srchValue == "<spring:message code="clm.page.msg.draft.announce003" />"){
			frm.srch_customer.value = "";
		}
		
		if(srchValue3 == ""){
			frm.nation2.value = "";
		}
		
		//최소 3글자 이상 넣을 경우만 조회가능하게.
		if (frm.srch_gerp.value == "" || frm.srch_gerp.value.length < 1) {
			if (frm.srch_customer.value == "" || frm.srch_customer.value.length < 2) {
				alert('<spring:message code="clm.page.msg.draft.announce014" /> ' +'<spring:message code='secfw.msg.error.nameMinByte' />');
				return;
			}
		}
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/draft/customerTest.do'/>";
		frm.method.value = "listCustomerTest";
		frm.curPage.value = "1";		
		frm.page_gbn.value = "search"; 
		
		viewHiddenProgress(true);
		frm.submit();
	}
	
	//리스트 click 시
	function rowOnClick(idx){
		if(<%=customerCnt%> == 1){//검색된 리스트가 1개일 경우
			frm.customer_nm1_detail.value     = frm.customer_nm1.value;		//Sungwoo Replaced 2014-08-15
			frm.rep_nm_detail.value           = frm.rep_nm.value; //frm.ceonm_eng.value;
			frm.street_detail.value           = frm.street.value;
			frm.tax_no_detail.value           = frm.tax_no.value;
			frm.nation_nm_detail.value        = frm.nation_nm.value;
			frm.stapr_detail.value            = frm.stapr.value;
			frm.postalcode_detail.value       = frm.postalcode.value;
			frm.telephone1_detail.value       = frm.telephone1.value;
			frm.email_detail.value            = frm.email.value;
			frm.vatno_detail.value            = frm.vatno.value;
			frm.vendor_type_detail.value      = frm.vendor_type.value; // Vendor/Customer (type)
			frm.gerp_cd_detail.value          = frm.gerp_cd.value; // G-ERP Code
			frm.customer_cd_detail.value	  = frm.customer_cd.value;
			
			// 2014-03-19 Kevin Added.
			$("#contract_required").val($("#mandatory_counterparty").val()).attr("selected","selected").attr("disabled", true);
		}else if(<%=customerCnt%> > 1){//검색된 리스트가 여러개 일 경우
			frm.customer_nm1_detail.value     = frm.customer_nm1[idx].value;	//Sungwoo Replaced 2014-08-15
			frm.rep_nm_detail.value           = frm.rep_nm[idx].value;
			frm.street_detail.value           = frm.street[idx].value;
			frm.customer_cd_detail.value = frm.customer_cd[idx].value;
			frm.tax_no.value           = frm.tax_no[idx].value;
			frm.tax_no_detail.value           = frm.tax_no[idx].value;
			frm.nation_nm_detail.value        = frm.nation_nm[idx].value;
			frm.stapr_detail.value            = frm.stapr[idx].value;
			frm.postalcode_detail.value       = frm.postalcode[idx].value;
			frm.telephone1_detail.value       = frm.telephone1[idx].value;
			frm.email_detail.value            = frm.email[idx].value;
			frm.vatno_detail.value            = frm.vatno[idx].value;
			frm.vendor_type_detail.value      = frm.vendor_type[idx].value; // Vendor/Customer (type)
			frm.gerp_cd_detail.value          = frm.gerp_cd[idx].value; // G-ERP Code
			
			// 2014-03-19 Kevin Added.
			$("#contract_required").val(frm.mandatory_counterparty[idx].value).attr("selected","selected").attr("disabled", true);
		}
	}

    //신규등록 클릭 시
    function customerNew(){
    	var result = "";
    	popObj = PopUpWindowOpen('', 1000, 350, false,"PopUpWindow2");
		frm.target = "PopUpWindow2";
		frm.action = "<c:url value='/clm/draft/customerNew.do' />";
		frm.method.value = "listCustomerNew";
		frm.submit();
    }
    
    
    //모달팝업
	function PopUpWindowModalOpen2(surl, popupwidth, popupheight, bScroll, obj){
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
	
	   var sFeatures = "dialogWidth:" + popupwidth +"px;dialogHeight:" + popupheight+"px;scroll:" + (bScroll ? "on":"off")+ ";status:0ff;resizable:no;";
	    var returnVal = window.showModalDialog(surl, obj, sFeatures);
	    
	    if(typeof returnVal != "undefined"){
		    frm.srch_customer.value = returnVal;
		    pageAction();//다시 조회.
	    }
	}
    
    
    //수정버튼 클릭.
	function modify(){
    	if($("#tax_no_detail").val()==""){
    		alert("<spring:message code="las.page.field.contractManager.mustDoRegistration" />");
    		return;
    	}else{
    		var frm = document.frm;
        	var options = {
        		url : "<c:url value='/clm/draft/customerTest.do?method=modifyRegistrationNo' />",
        		type : "post",
        		async : false,
        		dataType : "json",
        		success : function (data, message){
        			if(data.result == "N"){
        				alert(data.message);
        			}else{
        				
        				var frm = document.frm;
        				var idx;
        				if(<%=customerCnt%> > 1){ // 행이 여러게 검색 된 경우
    							
    						for(var i = 0 ; i < frm.check_item.length ; i++) {
    							if(frm.check_item[i].checked == true){    								
    								idx = i;
    								break;
    							}
    						}
        				
        					frm.tax_no[idx].value = $("#tax_no_detail").val();
        				}else{
        					frm.tax_no.value = $("#tax_no_detail").val();
        				}
        				alert("<spring:message code="las.msg.succ.modify" />");
        			}
        		}
        	};
        	$("#frm").ajaxSubmit(options);
    	}
  	}
  	
    //확인버튼 클릭.
	function confirm(){
		var frm = document.frm;
		var userInfo = new Object();
		
		if(frm.customer_nm1_detail.value == ""){
			alert("<spring:message code="clm.page.msg.draft.announce021" />");
			return;
		}
		
		//한개만 선택이 가능할 경우.  라디오 버튼 시작
		var cnt = 0;
        var idx = 0;
        
        if(<%=customerCnt%> >= 0){
			if(cnt >= 0){//radio box 체크 했을 경우.
				if(<%=customerCnt%> > 1){ // 행이 여러게 검색 된 경우
					for(var i = 0 ; i < frm.check_item.length ; i++) {
						if(frm.check_item[i].checked == true){
							cnt++;
							idx = i;
							break;
						}
					}
				
					userInfo.customer_cd      = frm.customer_cd[idx].value;
					userInfo.customer_nm1     = frm.customer_nm1[idx].value;
					userInfo.iv_nm1           = frm.customer_nm1[idx].value;
					userInfo.sec_cd           = frm.sec_cd[idx].value;
					userInfo.tax_no           = frm.tax_no[idx].value;
					userInfo.street           = frm.street[idx].value;
					userInfo.stapr            = frm.stapr[idx].value;
					userInfo.nation           = frm.nation[idx].value;
					userInfo.nation_nm        = frm.nation_nm[idx].value;
					userInfo.postalcode       = frm.postalcode[idx].value;
					userInfo.telephone1       = frm.telephone1[idx].value;
					userInfo.rep_nm           = frm.rep_nm[idx].value;
					userInfo.email            = frm.email[idx].value;
					userInfo.vatno            = frm.vatno[idx].value;
					userInfo.index            = 0;
					// 2014-03-19 Kevin Added.
					userInfo.contract_required = frm.mandatory_counterparty[idx].value;
					userInfo.gerp_cd = frm.gerp_cd[idx].value;
					userInfo.vendorType = frm.vendor_type[idx].value;
					
				} else {   // 행이 하나 검색된 경우
					userInfo.customer_cd      = frm.customer_cd.value;
					userInfo.customer_nm1     = frm.customer_nm1.value;
					userInfo.iv_nm1           = frm.customer_nm1.value;
					userInfo.sec_cd           = frm.sec_cd.value;
					userInfo.tax_no           = frm.tax_no.value;
					userInfo.street           = frm.street.value;
					userInfo.stapr            = frm.stapr.value;
					userInfo.nation           = frm.nation.value;
					userInfo.nation_nm        = frm.nation_nm.value;
					userInfo.postalcode       = frm.postalcode.value;
					userInfo.telephone1       = frm.telephone1.value;
					userInfo.rep_nm           = frm.rep_nm.value;
					userInfo.email            = frm.email.value;
					userInfo.vatno            = frm.vatno.value;
					userInfo.index            = 0;
					// 2014-03-19 Kevin Added.
					userInfo.contract_required = frm.mandatory_counterparty.value;
					userInfo.gerp_cd = frm.gerp_cd.value;
					userInfo.vendorType = frm.vendor_type.value;
				}
			
				if('<%=compCd%>' == 'EHQ' || '<%=compCd%>' == 'SEUK' ){
					if(userInfo.nation === 'GB'){
						if(userInfo.tax_no == "" || typeof(userInfo.tax_no) == "undefined"){
							alert("<spring:message code="las.page.field.contractManager.mustDoRegistration" />");
							return;
						}
					}
				}else if('<%=compCd%>' === 'SENA'){
					if(userInfo.nation === 'SE'){
						if(userInfo.tax_no == "" || typeof(userInfo.tax_no) == "undefined"){
							alert("<spring:message code="las.page.field.contractManager.mustDoRegistration" />");
							return;
						}
					}
				}else if('<%=compCd%>' === 'SESK'){
					if(userInfo.nation === 'SK'){
						if(userInfo.tax_no == "" || typeof(userInfo.tax_no) == "undefined"){
							alert("<spring:message code="las.page.field.contractManager.mustDoRegistration" />");
							return;
						}
					}
				}
			
				opener.returnCustomer(userInfo);
				window.close();
			}else{//radio box 체크 안했을 경우.
				window.close();
			}
        }else{
        	window.close();
        }
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
	
	/**
	* 국가선택 POPUP function
	*/
	function NationPop(mode)
	{
    	var frm = document.frm;

    	PopUpWindowOpen('', 890, 500, true, 'PopUpWindow3');
	    frm.target = "PopUpWindow3";
		
		frm.action = "<c:url value='/clm/draft/nation.do' />";
		frm.method.value = "listNation";
		frm.submit();
	}
	
	//국가 검색 리턴
	function setNation(obj){
		var frm = document.frm;
		frm.nation2.value   = obj.nation;//국가코드
		frm.srch_nation.value = obj.nationNm;//국가명
		
	}
	
	function NationPop2(mode){
		
		var frm = document.frm;
		
		frm.srch_nation.value = "";
		frm.nation2.value = "";

	}
	
	function nmAlert(){
		
		var frm = document.frm;
		frm.srch_customer.value = "";
	}
	
	// 신규 등록 후 다시 조회
	function setInfo(customer1){
		var frm = document.frm;
		frm.srch_customer.value = "";
		frm.srch_customer.value = customer1;
		setInterval("pageAction()", 2000);
	}
	
	// 취소하기
	function can(){
		window.close();
	}

</script>
</head>
<body class="pop">
		<!-- header -->
		<h1>
			<spring:message code="clm.page.title.customer.title" />
			<span class="close" onclick="javascript:self.close();" title="close"></span>
		</h1>
		<!-- //header -->
		<!-- body -->
		<div class="pop_area">
			<div class="pop_content">
				
		<!-- **************************** Form Setting **************************** -->
		<form:form name="frm" id="frm" method="post" autocomplete="off">

		<!-- search form -->
		<input type="hidden" name="method" value="" />
		<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
		<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>" />
		<!-- key form-->
		<input type="hidden" name="customer_gb" id="customer_gb" value="<c:out value='${command.customer_gb}'/>" />
		<input type="hidden" name="selectCnt" id="selectCnt" value="<c:out value='${command.selectCnt}'/>" />
		<input type="hidden" name="selectRow" id="selectRow" value="<c:out value='${command.selectRow}'/>" />
        <input type="hidden" name="forward_url"  value="" />
        <input type="hidden" name="page_gbn"  value="" />
        <input type="hidden" name="nation2" value="<c:out value='${command.nation2}'/>"  />

		<!-- // **************************** Form Setting **************************** -->
					<!-- search-->
					<div class="search_box">
						<div class="search_box_inner">
							<table class="search_tb">
								<colgroup>
									<col />
									<col width="75px" />
								</colgroup>
								<tr>
									<td>
										<table class="search_form">
											<colgroup>
												<col width="15%" />
												<col width="35%" />
												<col width="15%" />
												<col width="35%" />
											</colgroup>
											<tr>
												<th>
												    <spring:message code="clm.page.field.customer.customerNm1" />/<spring:message code="clm.page.msg.draft.nmInd" /><span class='astro'>*</span>
												</th>
												<td>
												    <input class='text_full' type="text" id="srch_customer" name="srch_customer" style="width:97%" value="<c:out value='${command.srch_customer}' />" onKeyPress="if(event.keyCode==13) {pageAction();return false;}" onclick="javascript:nmAlert();" maxLength="50" />
												</td>
												<th><spring:message code="las.page.field.common.type" /></th>
												<td>
												    <input type="radio" id="rdoAll" name="srch_vendor_type" value=""><span style="margin:0 7px 0 5px;">All</span>
												    <input type="radio" id="rdoCustomer" name="srch_vendor_type" value="C"><span style="margin:0 7px 0 5px;">Customer</span>
												    <input type="radio" id="rdoVendor" name="srch_vendor_type" value="V"><span style="margin: 0 0 0 5px">Vendor</span>
												</td>
											</tr>
											<tr>
												<th>
												    <spring:message code="clm.page.field.customer.gerpCode" />
												</th>
												<td>
												    <input class='text' type="text" id="srch_gerp" name="srch_gerp" style="width: 50%" value="<c:out value='${command.srch_gerp}' />" />
												</td>
												<th><spring:message code="clm.page.msg.draft.nation" /></th>
												<td>
												    <input class='text_search w_70' type="text" id="srch_nation" name="srch_nation" style="width:60%" value="<c:out value='${command.srch_nation}' />" onclick="javascript:NationPop2('srch')" readonly/><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" onclick="javascript:NationPop('srch');" alt="<spring:message code="clm.page.msg.draft.nationSearch" htmlEscape="true" />" />
												</td>
											</tr>
										</table></td>
									<td class="tC"><a href="javascript:pageAction();"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="clm.page.msg.common.inquire" htmlEscape="true" />" />
									</a></td>
								</tr>
							</table>
						</div>
					</div>
					<!--//search-->
					<!-- list -->
					<style>.list_basic td, .list_basic th {padding:4px}</style>
		    		<div class='tableWrap2 mt20'>
		    		<div class='tableone'>
		    		<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
						<colgroup>
							<c:if test="${command.customer_gb eq 'M'}">
								<col width="8%" />
								<col width="25%" />
								<col width="16%" />
								<col width="9%" />
								<col width="18%" />
							</c:if>
							<c:if test="${command.customer_gb eq 'O'}">
								<col width="4%" />
								<col width="26%" />
								<col width="17%" />
								<col width="9%" />
								<col width="19%" />
							</c:if>
						</colgroup>
						<thead>
							<tr>
								<th></th>
								<th><spring:message code="clm.page.field.customer.donam" /><spring:message code="clm.page.msg.draft.nmInd" />
								</th>
								<th><spring:message code="clm.page.field.customer.gerpCode" />
								</th>
								<th><spring:message code="las.page.field.common.type" />
								</th>
								<th><spring:message code="clm.page.field.customer.street" />
								</th>
							</tr>
						</thead>
					</table>
				</div>
				</div>
				<div class='tabletwo' style='height:150px'>
			 		<table class='list_scr'>
						<colgroup>
						    <c:if test="${command.customer_gb eq 'M'}">
								<col width="8%" />
								<col width="25%" />
								<col width="16%" />
								<col width="9%" />
								<col width="18%" />
							</c:if>
							<c:if test="${command.customer_gb eq 'O'}">
								<col width="4%" />
								<col width="26%" />
								<col width="17%" />
								<col width="9%" />
								<col width="19%" />
							</c:if>
			  			</colgroup>					
						<tbody>
							<c:choose>
								<c:when test="${pageUtil.totalRow > 0}">
									<c:forEach var="list" items="${customerList}" varStatus="listCnt">
										<tr>
											<td class="tC"><input type="radio" id="check_item" name="check_item" value="<c:out value='${list.customer_cd}'/>" onclick="javascript:rowOnClick('<c:out value='${listCnt.index}'/>');" /></td>
											<td class="tL" class="txtover"><strong><font color="0061b6" title="<c:out value='${list.donam}'/>"><c:out value="${list.donam}" /></font></strong></td>
											<td class="tL txtover" title="<c:out value="${list.gerp_cd}"/>"><nobr><c:out value="${list.gerp_cd}" /></nobr></td>
											<td class="tC"><c:out value="${list.vendor_type_nm}" /></td>
											<td class="tL txtover" title="<c:out value="${list.street}"/>"><nobr><c:out value="${list.street}" /></nobr></td>
											<div style="display:none;">
												<input type="hidden" id="customer_cd" name="customer_cd" value="<c:out value='${list.customer_cd}'/>" />
												<input type="hidden" id="customer_nm1" name="customer_nm1" value="<c:out value='${list.customer_nm1}'/>" />
												<input type="hidden" id="iv_nm1" name="iv_nm1" value="<c:out value='${list.iv_nm1}'/>" />
												<input type="hidden" id="sec_cd" name="sec_cd" value="<c:out value='${list.sec_cd}'/>" />
												<input type="hidden" id="tax_no" name="tax_no" value="<c:out value='${list.tax_no}'/>" />
												<input type="hidden" id="erdat" name="erdat" value="<c:out value='${list.erdat}'/>" />
												<input type="hidden" id="street" name="street" value="<c:out value='${list.street}'/>" />
												<input type="hidden" id="stapr" name="stapr" value="<c:out value='${list.cityn}'/>" />
												<input type="hidden" id="nation" name="nation" value="<c:out value='${list.nation}'/>" />
												<input type="hidden" id="nation_nm" name="nation_nm" value="<c:out value='${list.nation_nm}'/>" />
												<input type="hidden" id="postalcode" name="postalcode" value="<c:out value='${list.postalcode}'/>" />
												<input type="hidden" id="telephone1" name="telephone1" value="<c:out value='${list.telephone1}'/>" />
												<input type="hidden" id="rep_nm" name="rep_nm" value="<c:out value='${list.rep_nm}'/>" />
												<input type="hidden" id="email" name="email" value="<c:out value='${list.email}'/>" />
												<input type="hidden" id="vatno" name="vatno" value="<c:out value='${list.vat_no}'/>" />
												<input type="hidden" id="gerp_cd" name="gerp_cd" value="<c:out value='${list.gerp_cd}'/>" />
												<input type="hidden" id="vendor_type" name="vendor_type" value="<c:out value='${list.vendor_type_nm}'/>" />
												<!-- 2014-03-19 Kevin. 필수 계약 체결 여부 속성 값. -->
												<input type="hidden" id="mandatory_counterparty" name="mandatory_counterparty" value="<c:out value='${list.mandatory}' />" />
											</div>										
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="5" class="tC">
											<spring:message code="clm.page.msg.draft.announce018" /> <spring:message code="clm.page.msg.draft.announce019" />		 
										</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</tbody>
						</table>
					</div>
					<!-- //list -->
					
					<c:if test="${pageUtil.totalRow > 0}">
							<div>
							<br>
							<b>※<spring:message code="clm.page.msg.draft.announce001" /><br>
							</b>
							</div>
					</c:if>

					<div class="total_num">
						<spring:message code="secfw.page.field.common.totalData" />
						: <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%>
					</div>

					<!-- pagination  -->
					<div class="pagination">
						<%=pageUtil.getPageNavi(pageUtil, "")%>
					</div>
					<!-- //pagination -->

					<!-- view -->
					<table class="list_basic">
						<colgroup>
							<col width='15%'/>
							<col width='20%'/>
							<col width='15%'/>
							<col width='18%'/>
							<col width='15%'/>
							<col width='17%'/>
						</colgroup>
						<tbody>
							<tr>
								<th><spring:message code="clm.page.field.customer.customerNm1" /><br>/<spring:message code="clm.page.msg.draft.nmInd" /></th>
								<td><input class='text' type="text" id="customer_nm1_detail" name="customer_nm1_detail" style="border-width:0; width: 100%" value="" readonly="readonly"/></td>
								<th><spring:message code="clm.page.field.customer.repNm" /></th>
								<td><input class='text' type="text" id="rep_nm_detail" name="rep_nm_detail" style="border-width:0; width: 100%" value="" readonly="readonly"/></td>
								<th><spring:message code="clm.page.field.customer.telephone1" /></th>
								<td><input class='text' type="text" id="telephone1_detail" name="telephone1_detail" style="border-width:0; width: 100%" value="" readonly="readonly"/></td>
							</tr>
							<tr>
								<th><spring:message code="clm.page.field.customer.gerpCode" /></th>
								<td><input class='text' type="text" id="gerp_cd_detail" name="gerp_cd_detail" style="border-width:0; width: 100%" value="" readonly="readonly"/></td>
								<th><spring:message code="clm.page.field.customer.vendorType" /></th>
								<td><input class='text' type="text" id="vendor_type_detail" name="vendor_type_detail" style="border-width:0; width: 100%" value="" readonly="readonly"/></td>
								<!-- 2014-03-19 Kevin. 필수 계약 counterparty 설정 추가 -->
								<th><spring:message code="clm.page.field.customer.IsContractRequired" /></th>
								<td>
										<select name="contract_required" id="contract_required" style="width:140px">
											<option value="">Not Decided</option>
											<option value="Y">Yes</option>
											<option value="N">No</option>		
										</select>
								</td>
							</tr>
							<tr>
								<th><spring:message code="clm.page.field.customer.registerNo" /></th>
								<td>
										<input type="hidden" id="customer_cd_detail" name="customer_cd_detail" value=""/>
										<input type="hidden" id="modifyOK" name="modifyOK"/>								
										<input class='text' type="text" id="tax_no_detail"	name="tax_no_detail" value="" />
										<span class="btn_all_b"><span class="modify"></span><a href="javascript:modify()"><spring:message code="clm.page.button.contract.modify"/></a></span>
								</td>								 
								<th><spring:message code="clm.page.field.customer.vatno" /></th>
								<td><input class='text' type="text" id="vatno_detail" name="vatno_detail" style="border-width:0; width: 100%" value="" readonly="readonly"/></td>
								<th><spring:message code="clm.page.field.customer.Email" /></th>
								<td><input class='text' type="text" id="email_detail" name="email_detail" style="border-width:0; width: 100%" value="" readonly="readonly"/></td>
							</tr>
							<tr>
								<th><spring:message code="clm.page.field.customer.nation" /></th>
								<td><input class='text' type="text" id="nation_nm_detail" name="nation_nm_detail" style="border-width:0; width: 100%" value="" readonly="readonly"/></td>
								<th><spring:message code="clm.page.field.customer.stapr" /></th>
								<td><input class='text' type="text" id="stapr_detail" name="stapr_detail" style="border-width:0; width: 100%" value="" readonly="readonly"/></td>
								<th><spring:message code="clm.page.field.customer.postalcode" /></th>
								<td><input class='text' type="text" id="postalcode_detail" name="postalcode_detail" style="border-width:0; width: 100%" value="" readonly="readonly"/></td>
							</tr>
							<tr>
								<th><spring:message code="clm.page.field.customer.street" /></th>
								<td colspan="5"><input class='text' type="text" id="street_detail" name="street_detail" style="border-width:0; width: 100%" value="" readonly="readonly"/></td>
							</tr>
						</tbody>
					</table>
					<div class='ico_alert01 mt5'><spring:message code="clm.page.msg.draft.announce013" /></div>
					<!-- //view -->
				</form:form>
				</div>
			</div>
			
			<!--footer -->
			<div class="pop_footer">
				<!-- button -->
				<div class="btn_wrap tR">
					<span class="btn_all_w"><span class="confirm"></span><a href="javascript:confirm()"><spring:message code='clm.page.msg.common.confirm' /></a></span>
					<span class="btn_all_w"><span class="cancel"></span><a href="javascript:can()"><spring:message code="clm.page.msg.common.cancel" /></a></span>
                    <span class="btn_all_w"><span class="edit"></span><a href="javascript:customerNew()"><spring:message code='clm.page.button.customer.customerNew' /></a></span>
				</div>
				<!-- //button -->
			</div>
			<!-- //footer -->
			
			<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</body>
</html>