<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page import="java.util.List"%>
<%@ page import="com.sds.secframework.common.util.PageUtil"%>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%--
/**
 * 파  일  명 : Customer_u.jsp
 * 프로그램명 : 거래선 수정 화면
 * 설      명 : 
 * 작  성  자 : 박정훈
 * 작  성  일 : 2013.04
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
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<html>
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="clm.main.title" /></title>
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet" />
<!-- [if IE]><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
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
		
		if (frm.srch_gerp.value == "" || frm.srch_gerp.value.length < 1) {
			//최소 3글자 이상 넣을 경우만 조회가능하게.
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
	
	function openTop30Customer(){
		 
		 PopUpWindowOpen('', 500, 540, false, 'Top30Customer');
		    frm.target = "Top30Customer";
		 
		 frm.action = "<c:url value='/clm/draft/customerTest.do' />";
		 frm.method.value = "listTop30Customer";
		 frm.submit();
	 
	}
	
	//리스트 click 시
	function rowOnClick(idx){
		if(<%=customerCnt%> == 1){//검색된 리스트가 1개일 경우
			frm.customer_nm1_detail.value     = frm.customer_nm1.value;
			frm.rep_nm_detail.value           = frm.rep_nm.value;
			frm.tax_no_detail.value           = frm.tax_no.value; // Registration No.
			frm.nation_nm_detail.value        = frm.nation_nm.value;			
			frm.stapr_detail.value            = frm.stapr.value;
			frm.street_detail.value           = frm.street.value;	// 2014-03-18 Kevin. 예전에 Town으로 되어 있던 input 박스는 없앴음.
			frm.postalcode_detail.value       = frm.postalcode.value;
			frm.telephone1_detail.value       = frm.telephone1.value;
			frm.email_detail.value            = frm.email.value;
			frm.vatno_detail.value            = frm.vatno.value;
			frm.nation_detail.value 		  = frm.nation.value;
			frm.customer_cd_detail.value 	  = frm.customer_cd.value;
			frm.vendor_type_detail.value      = frm.vendor_type.value; // Vendor/Customer (type)
			frm.gerp_cd_detail.value          = frm.gerp_cd.value; // G-ERP Code
			frm.cntrt_top30_cus_detail.value  = frm.cntrt_top30_cus.value; // TOP 30 CUS
			// Kevin 추가
			$("#contract_required").val(frm.mandatory_counterparty.value).attr("selected", "selected");
			$("#vendor_type_detail").val(frm.vendor_type.value).attr("selected", "selected");
			
		}else if(<%=customerCnt%> > 1){//검색된 리스트가 여러개 일 경우
			frm.customer_nm1_detail.value     = frm.customer_nm1[idx].value;
			frm.rep_nm_detail.value           = frm.rep_nm[idx].value;
			frm.tax_no_detail.value           = frm.tax_no[idx].value;// Registration No.
			frm.nation_nm_detail.value        = frm.nation_nm[idx].value;
			frm.stapr_detail.value            = frm.stapr[idx].value;
			frm.street_detail.value           = frm.street[idx].value;	// 2014-03-18 Kevin. 예전에 Town으로 되어 있던 input 박스는 없앴음.;
			frm.postalcode_detail.value       = frm.postalcode[idx].value;
			frm.telephone1_detail.value       = frm.telephone1[idx].value;
			/* frm.hp_no_detail.value            = frm.hp_no[idx].value; */
			frm.email_detail.value            = frm.email[idx].value;
			frm.vatno_detail.value            = frm.vatno[idx].value;
			frm.nation_detail.value 		  = frm.nation[idx].value;  
			frm.customer_cd_detail.value 	  = frm.customer_cd[idx].value;
			frm.vendor_type_detail.value      = frm.vendor_type[idx].value; // Vendor/Customer (type)
			frm.gerp_cd_detail.value          = frm.gerp_cd[idx].value; // G-ERP Code
			frm.cntrt_top30_cus_detail.value  = frm.cntrt_top30_cus[idx].value; // TOP 30 CUS
			// Kevin 추가
			$("#contract_required").val(frm.mandatory_counterparty[idx].value).attr("selected", "selected");
			$("#vendor_type_detail").val(frm.vendor_type[idx].value).attr("selected", "selected");
		}
	}

    //신규등록 클릭 시
    function customerNew(){
    	var result = "";
    	popObj = PopUpWindowOpen('', 900, 300, false,"PopUpWindow2");
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
    
    
    function registerNew(){
    	var result = "";
    	PopUpWindowOpen('', 1000, 350, false,"PopUpWindow2");
		frm.target = "PopUpWindow2";
		frm.action = "<c:url value='/clm/draft/customerNew.do' />";
		frm.method.value = "listCustomerNew";
		frm.submit();
    }
    
    function checkGerpCd(callback){// G-ERP 코드 중복 체크
		var options = {
                url: "<c:url value='/clm/draft/customerTest.do?method=checkGerpCd' />",		
                type: "post",
        		async : false,
        		dataType : "json",
        		success : function (data, message){ 
        			callback(data.result);      
        		}
            };
		$("#frm").ajaxSubmit(options);  
    }
    
    function gerpCallback(result){
    	var frm = document.frm;
    	
		if(result==="N"){        	
            if(frm.cntrt_top30_cus_detail.value=="Y"){			
				var options = {
	                    url: "<c:url value='/clm/draft/customerTest.do?method=overTop30Cus' />",		
	                    type: "post",
	            		async : false,
	            		dataType : "json",
	            		success : function (data, message){
	            			if(data.result == "N"){
	            				frm.target = "_self";
	            				frm.action = "<c:url value='/clm/draft/customerTest.do' />";
	            				frm.method.value = "modifyCustomer";
	            				frm.page_gbn.value = "search"; 
	            				
	            				viewHiddenProgress(true) ;
	            				frm.submit();
	            			}else{
	            				alert(data.message);            			
	            			}
	            		}
		            };
		            $("#frm").ajaxSubmit(options);  
				
			}else{
				frm.target = "_self";
				frm.action = "<c:url value='/clm/draft/customerTest.do' />";
				frm.method.value = "modifyCustomer";
				frm.page_gbn.value = "search"; 
				
				viewHiddenProgress(true) ;
				frm.submit();
			}
        }else{
        	alert("<spring:message code='clm.page.field.customer.alreadyExistGerp' />");
        }
		
	}
    
    //확인버튼 클릭.
	function confirm(){
    	var frm = document.frm;
		var userInfo = new Object();
		
		if(<%=customerCnt%> == 0){		//조회 하지 않고 수기 등록시 script error 방지 박정훈.!@#.2013.04
			alert("<spring:message code="clm.page.msg.draft.announce021" />");
			return;
		}

		if(frm.customer_nm1_detail.value == ""){
			alert("<spring:message code="clm.page.msg.draft.announce021" />");
			return;
		}
		
		if(frm.gerp_cd_detail.value !== ""){ 
			checkGerpCd(gerpCallback);
		}else{
			gerpCallback("N");
		}
		
// 		frm.target = "_self";
// 		frm.action = "<c:url value='/clm/draft/customerTest.do' />";
// 		frm.method.value = "modifyCustomer";
// 		frm.page_gbn.value = "search"; 
		
// 		viewHiddenProgress(true) ;
// 		frm.submit();
		
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
		
		var Feture = "toolbar=0, location=0, status=1, menubar=0, scrollbars=no, resizable=yes, top="+Top+", left="+Left+", width="+popupwidth+", height="+popupheight;
		var PopUpWindow = window.open(surl, popName , Feture);

		PopUpWindow.focus();
		
	}
	
	/**
	* 국가선택 POPUP function
	*/
	function NationPop(mode)
	{
    	var frm = document.frm;
    	frm.isSearch.value = "Y";
    	PopUpWindowOpen('', 890, 500, false, 'PopUpWindow3');
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
	
	function setNation2(obj){
		var frm = document.frm;
		frm.nation_detail.value   = obj.nation;//국가코드
		frm.nation_nm_detail.value = obj.nationNm;//국가명
		
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
	
    function nmAlert2(){
		var frm = document.frm;
	}
    
	// 신규 등록 후 다시 조회
	function setInfo(customer1){
		var frm = document.frm;
		
		frm.srch_customer.value = "";
		frm.srch_customer.value = customer1;
		pageAction();  // 다시 조회
		
	}
	
	// 취소하기
	function can(){
		window.close();
	}
	
	
	//국가 검색 팝업 호출
	function nationPop(){
		var frm = document.frm;
		var nation = "";

		frm.isSearch.value = "N";
		PopUpWindowOpen('', 890, 500, false, 'PopUpWindow3');
		frm.target = "PopUpWindow3";
			
		frm.action = "<c:url value='/clm/draft/nation.do' />";
		frm.method.value = "listNation";
		frm.submit();
		
	}

</script>
</head>
<body autocomplete="off">
	<div id="wrap">
		<!-- container -->
		<div id="container">
			<!-- location -->
			<div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" border="0" alt="Home"> Home > <spring:message code="clm.page.msg.common.contractManagement" /> > <strong><spring:message code="clm.page.msg.draft.chgCtp" /></strong></div>
			<!-- //location -->
			<!-- title -->
				<div class="title">
					<h3><spring:message code="clm.page.msg.draft.chgCtp" /></h3>
				</div>
				<!-- //title -->	
				
			<!-- content -->
			<div id="content">
			<div id="content_in">
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
		        <input type="hidden" name="isModify" id="isModify" value="<c:out value='${command.isModify}'/>"  />
		        <input type="hidden" name="isSearch" id="isSearch"/>
		        <input type="hidden" name="nation_detail" id="nation_detail" value=""  />
		        <input type="hidden" name="customer_cd_detail" id="customer_cd_detail" value=""  />
		        <input type="hidden" name="check_texNo"  value="<c:out value='${check.check_texNo}'/>" />
				<input type="hidden" name="check_texNm"  value="<c:out value='${check.check_texNm}'/>" />
				<input type="hidden" name="check_YN"     value="<c:out value='${check.check_YN}'/>" />
		
				<!-- 첨부파일정보 -->
				<!-- // **************************** Form Setting **************************** -->
				<!-- header -->
				
					<!-- title -->
					<div class="title_basic mt0">
						<h4><spring:message code="clm.page.title.customer.listTitle" /></h4>
					</div>
					<!-- //title -->
				
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
												    <input class='text_full' type="text" id="srch_customer" name="srch_customer" value="<c:out value='${command.srch_customer}' />"  onkeypress="if(event.keyCode==13) {pageAction();return false;}" onclick="javascript:nmAlert();" maxLength="50" />
												</td>
												<th><spring:message code="las.page.field.common.type" /></th>
												<td>
												    <input type="radio" id="rdoAll" name="srch_vendor_type" value=""><span style="margin:0 7px 0 5px;">All</span>
												    <input type="radio" id="rdoCustomer" name="srch_vendor_type" value="C"><span style="margin:0 7px 0 5px;">Customer</span>
												    <input type="radio" id="rdoVendor" name="srch_vendor_type" value="V"><span style="margin:0 0 0 5px;">Vendor</span>
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
												    <input class='text_search' type="text" id="srch_nation" name="srch_nation" style="width: 50%" value="<c:out value='${command.srch_nation}' />" onclick="javascript:NationPop2('srch')" readonly/><img src="<%=IMAGE%>/icon/ico_search.gif" class=cp onclick="javascript:NationPop('srch');" alt="<spring:message code="clm.page.msg.draft.nationSearch" htmlEscape="true" />" />
												</td>
											</tr>
										</table></td>
									<td class="tC"><a href="javascript:pageAction();"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="clm.page.msg.common.inquire" htmlEscape="true" />" /></a></td>
								</tr>
							</table>
						</div>
					</div>
					<!--//search-->
					<!-- list -->
					<table class="table-style01 mt20">
						<colgroup>
								<col width="4%" />
								<col width="26%" />
								<col width="17%" />
								<col width="9%" />
								<col width="19%" />
						</colgroup>
						<thead>
							<tr>
								<th><spring:message code="clm.page.field.admin.subject.select_yn" /></th>
								<th class='tC'><spring:message code="clm.page.field.customer.donam" />/<spring:message code="clm.page.msg.draft.nmInd" /></th>
								<th class='tC'><spring:message code="clm.page.field.customer.gerpCode" /></th>
								<th class='tC'><spring:message code="las.page.field.common.type" /></th>
								<th class='tC'><spring:message code="clm.page.field.customer.street" /></th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${pageUtil.totalRow > 0}">
									<c:forEach var="list" items="${customerList}" varStatus="listCnt">
										<tr>
											<td class="tC">
											    <input type="radio" id="check_item" name="check_item" value="<c:out value='${list.customer_cd}'/>" onClick="javascript:rowOnClick('<c:out value='${listCnt.index}'/>');" />
											</td>
											<c:choose>
												<c:when test="${list.gudun == list.dodun}">
													<td class="tL" class="txtover"><strong><font color="0061b6" title="<c:out value='${list.donam}'/>"><c:out value="${list.donam}" /></font></strong></td>
												</c:when>
												<c:when test="${list.gudun != list.dodun}">
													<td class="tL" title="<c:out value='${list.donam}'/>"><c:out value="${list.donam}" />
													</td>
												</c:when>
											</c:choose>
											<td class="tL txtover" title="<c:out value="${list.gerp_cd}"/>"><nobr>
												<c:out value="${list.gerp_cd}" /></nobr>
											</td>
											<td class="tC"><c:out value="${list.vendor_type_nm}" /></td>
											<td class="tL txtover" title="<c:out value="${list.street}"/>"><nobr>
												<c:out value="${list.street}" /></nobr>
											</td>
											<input type="hidden" id="customer_cd" name="customer_cd" value="<c:out value='${list.customer_cd}'/>" />
											<input type="hidden" id="customer_nm1" name="customer_nm1" value="<c:out value='${list.customer_nm1}'/>" />
											<input type="hidden" id="tax_no" name="tax_no" value="<c:out value='${list.tax_no}'/>" />
											<input type="hidden" id="street" name="street" value="<c:out value='${list.street}'/>" />
											<input type="hidden" id="cityn" name="cityn" value="<c:out value='${list.cityn}'/>" />
											<input type="hidden" id="stapr" name="stapr" value="<c:out value='${list.cityn}'/>" />
											<input type="hidden" id="nation" name="nation" value="<c:out value='${list.nation}'/>" />
											<input type="hidden" id="nation_nm" name="nation_nm" value="<c:out value='${list.nation_nm}'/>" />
											<input type="hidden" id="postalcode" name="postalcode" value="<c:out value='${list.postalcode}'/>" />
											<input type="hidden" id="telephone1" name="telephone1" value="<c:out value='${list.telephone1}'/>" />
											<input type="hidden" id="hp_no" name="hp_no" value="<c:out value='${list.hp_no}'/>" />
											<input type="hidden" id="rep_nm" name="rep_nm" value="<c:out value='${list.rep_nm}'/>" />
											<input type="hidden" id="email" name="email" value="<c:out value='${list.email}'/>" />
											<input type="hidden" id="vatno" name="vatno" value="<c:out value='${list.vat_no}'/>" />
											<input type="hidden" id="gerp_cd" name="gerp_cd" value="<c:out value='${list.gerp_cd}'/>" />
											<input type="hidden" id="vendor_type" name="vendor_type" value="<c:out value='${list.vendor_type}'/>" />
											<input type="hidden" id="cntrt_top30_cus" name="cntrt_top30_cus" value="<c:out value='${list.top30cus_yn}'/>" />		
											<!-- 2014-03-18 Kevin. 필수 계약 체결 여부 속성 값. -->
											<input type="hidden" id="mandatory_counterparty" name="mandatory_counterparty" value="<c:out value='${list.mandatory}' />" />																			
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="5" class="tC">
											<spring:message code="secfw.msg.succ.noResult" />		 
										</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
					<!-- //list -->

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
					<table class="table-style01">
						<colgroup>
								<col width="15%" />
								<col width="20%" />
								<col width="15%" />
								<col width="17%" />
								<col width="15%" />
								<col width="18%" />
						</colgroup>
						<tbody>
							<tr>
								<!-- 업체명, 대표자, 연락처 -->
								<th><spring:message code="clm.page.field.customer.customerNm1" />/<spring:message code="clm.page.msg.draft.nmInd" /></th>
								<td><input class='text' type="text" style="width:140px" id="customer_nm1_detail" name="customer_nm1_detail"  value="" maxLength="200" /></td>
								<th><spring:message code="clm.page.field.customer.repNm" /></th>
								<td><input class='text' type="text" id="rep_nm_detail" style="width:140px"  name="rep_nm_detail"  value="" maxlength="105"/></td>	
								<th><spring:message code="clm.page.field.customer.telephone1" /></th>
								<td><input class='text' type="text" id="telephone1_detail" style="width:140px" name="telephone1_detail"  value="" maxlength="100" /></td>
							</tr>
							<tr>
							<!-- GERP 코드, Vender/Customer, 필수 계약 체결 여부 -->
								<th><spring:message code="clm.page.field.customer.gerpCode" /></th>
								<td><input class='text' type="text" id="gerp_cd_detail" style="width:140px" name="gerp_cd_detail"  value="" maxLength="50" /></td>
								<th><spring:message code="clm.page.field.customer.vendorType" /></th>
								<td id="vendorTypeInp">
								    <select name="vendor_type_detail" id="vendor_type_detail" style="width:140px">
								    	<option value="">Not Decided</option>
								    	<option value="V">Vendor</option>
								    	<option value="C">Customer</option>
								    </select>
								</td>
								<!-- 2014-03-18 Kevin. 필수 계약 counterparty 설정 추가 -->
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
								<!-- 사업자등록번호, VAT No, eMail -->
								<th><spring:message code="clm.page.field.customer.registerNo" /></th>
								<td><input class='text' type="text" id="tax_no_detail" style="width:140px" name="tax_no_detail"  value="" maxLength="50" /></td>
								<th><spring:message code="clm.page.field.customer.vatno" /></th>
								<td><input class='text' type="text" id="vatno_detail" style="width:140px" name="vatno_detail"  value="" maxlength="100" /></td>
								<th><spring:message code="clm.page.field.customer.Email" /></th>
								<td><input class='text' type="text" id="email_detail" style="width:140px" name="email_detail"  value="" maxlength="100" /></td>
							</tr>
							<tr>
								<!-- Country, City, PostCode -->
								<th><spring:message code="clm.page.field.customer.nation" /></th>
								<td><input class='text_search' type="text" readonly="true" id="nation_nm_detail" name="nation_nm_detail" style="width:123px" value="" /><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search" onclick="javascript:nationPop();" /></td>
								<th><spring:message code="clm.page.field.customer.stapr" /></th>
								<td><input class='text' type="text" id="stapr_detail" style="width:140px" name="stapr_detail"  value="" maxlength="50"/></td>
								<th><spring:message code="clm.page.field.customer.postalcode" /></th>
								<td><input class='text' type="text" id="postalcode_detail" style="width:140px" name="postalcode_detail"  value="" maxlength="100" /></td>
							</tr>
							<tr>
								<th><spring:message code="clm.page.msg.manage.top30Cus" /><img src="<%=IMAGE %>/icon/ico_search2.gif" class="cp" onclick="javascript:openTop30Customer();" alt="search" style="padding-left:5px" title="Top 30 Customer List"/></th>
								<td>
										<select name="cntrt_top30_cus_detail" id="cntrt_top30_cus_detail" style="width:140px">
											<option value="">Not Decided</option>
											<option value="Y">Yes</option>
											<option value="N">No</option>		
										</select>
								</td>
								<!-- 상세 주소 -->
								<th><spring:message code="clm.page.field.customer.street" /></th>
								<td colspan="3"><input class='text_full' type="text" id="street_detail"  name="street_detail"  value="" maxlength="200" />
								</td>
							</tr>
						</tbody>
					</table>
					<!-- //view -->
					<div class='ico_alert01 mt5'><spring:message code="clm.page.msg.draft.announce013" /></div>
					<!-- button -->
					<div class="btnwrap">
						<!-- 2014-07-29 Kevin commented. Following two buttons are not required anymore. -->
						<%-- <span class="btn_all_w"><span class="excel_down"></span><a href="javascript:errorReport()"><spring:message code="las.page.field.excelErrorReport" /></a></span>
						<span class="btn_all_w"><span class="upload"></span><a href="javascript:upload()"><spring:message code="las.page.button.upload" /></a></span> --%>
						<span class="btn_all_w"><span class="edit"></span><a href="javascript:registerNew()"><spring:message code="clm.page.button.customer.customerNew" /></a></span>
						<span class="btn_all_w"><span class="modify"></span><a href="javascript:confirm()"><spring:message code="clm.page.msg.common.modify" /></a></span>
						<span class="btn_all_w"><span class="cancel"></span><a href="javascript:can()"><spring:message code="clm.page.msg.common.cancel" /></a></span>
					</div>
					<!-- //button -->
					<br/><br/><br/>
					
					<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>

			</form:form>
			</div>
			<!-- //content -->
		</div>
		<!-- //bottom table -->
	</div>
	<!-- //container -->
</div>
		<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- // footer -->
</body>
</html>