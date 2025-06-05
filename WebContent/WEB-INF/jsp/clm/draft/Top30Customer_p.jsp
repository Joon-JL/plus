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
	<meta http-equiv="x-ua-compatible" content="IE=edge" >
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
			frm.customer_nm1_detail.value     = frm.iv_nm1.value; //영문 업체명 추가
			frm.rep_nm_detail.value           = frm.rep_nm.value; //frm.ceonm_eng.value;
			frm.street_detail.value           = frm.add_eng.value;
			frm.tax_no_detail.value           = frm.tax_no.value;
			frm.nation_nm_detail.value        = frm.nation_nm.value;
			frm.stapr_detail.value            = frm.stapr.value;
			/* frm.cityn_detail.value            = frm.cityn.value; */
			frm.postalcode_detail.value       = frm.postalcode.value;
			frm.telephone1_detail.value       = frm.telephone1.value;
			/* frm.hp_no_detail.value            = frm.hp_no.value; */
			/* frm.legst_nm_detail.value         = frm.legst_nm.value;
			frm.busopen_nm_detail.value       = frm.busopen_nm.value;
			frm.business_gubn_nm_detail.value = frm.business_gubn_nm.value; */
			frm.email_detail.value            = frm.email.value;
			frm.vatno_detail.value            = frm.vatno.value;
			frm.vendor_type_detail.value      = frm.vendor_type.value; // Vendor/Customer (type)
			frm.gerp_cd_detail.value          = frm.gerp_cd.value; // G-ERP Code
			frm.customer_cd_detail.value	  = frm.customer_cd.value;
			
			// 2014-03-19 Kevin Added.
			$("#contract_required").val($("#mandatory_counterparty").val()).attr("selected","selected").attr("disabled", true);
		}else if(<%=customerCnt%> > 1){//검색된 리스트가 여러개 일 경우
			frm.customer_nm1_detail.value	  = frm.iv_nm1[idx].value;	//영문 업체명 추가
			frm.rep_nm_detail.value           = frm.rep_nm[idx].value;
			frm.street_detail.value           = frm.add_eng[idx].value;
			frm.customer_cd_detail.value = frm.customer_cd[idx].value;
			frm.tax_no.value           = frm.tax_no[idx].value;
			frm.tax_no_detail.value           = frm.tax_no[idx].value;
			frm.nation_nm_detail.value        = frm.nation_nm[idx].value;
			frm.stapr_detail.value            = frm.stapr[idx].value;
			/* frm.cityn_detail.value            = frm.cityn[idx].value; */
			frm.postalcode_detail.value       = frm.postalcode[idx].value;
			frm.telephone1_detail.value       = frm.telephone1[idx].value;
			/*f rm.hp_no_detail.value            = frm.hp_no[idx].value;
			frm.legst_nm_detail.value         = frm.legst_nm[idx].value;
			frm.busopen_nm_detail.value       = frm.busopen_nm[idx].value;
			frm.business_gubn_nm_detail.value = frm.business_gubn_nm[idx].value; */
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
		window.close();
    }
    
	/**
	* 메시지 처리
	*/
	function showMessage(msg){
		
		var frm = document.frm;
		
		if( msg != "" && msg != null && msg.length > 1 ) {
			alert(msg);
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
<body class="pop" onload='showMessage("<c:out value='${returnMessage}'/>");'>
		<!-- header -->
		<h1>
			<spring:message code="clm.page.msg.manage.top30Cus" />
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
					
					<!-- list -->
					<style>.list_basic td, .list_basic th {padding:4px}</style>
		    		<div class='tableWrap2 mt20'>
		    		<div class='tableone'>
		    		<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
						<colgroup>
							<col width="50%" />
							<col width="*" />							
						</colgroup>
						<thead>
							<tr>
								<th><spring:message code="clm.page.field.customer.donam" />
								</th>
								<th><spring:message code="clm.page.field.customer.gerpCode" />
								</th>
							</tr>
						</thead>
					</table>
				</div>
				</div>
				<div class='tabletwo' style='height:400px'>
			 		<table class='list_scr'>
						<colgroup>
							<col width="50%" />
							<col width="*" />	
			  			</colgroup>					
						<tbody>
							<c:choose>
								<c:when test="${pageUtil.totalRow > 0}">
									<c:forEach var="list" items="${customerList}" varStatus="listCnt">
										<tr>
											<td class="tL" title="<c:out value='${list.customer_nm1}'/>"><c:out value="${list.customer_nm1}" /></td>
											<td class="tL txtover" title="<c:out value="${list.gerp_cd}"/>"><nobr><c:out value="${list.gerp_cd}" /></nobr></td>
<!-- 											<div style="display:none;"> -->
<%-- 												<input type="hidden" id="customer_cd" name="customer_cd" value="<c:out value='${list.customer_cd}'/>" /> --%>
<%-- 												<input type="hidden" id="customer_nm1" name="customer_nm1" value="<c:out value='${list.customer_nm1}'/>" /> --%>
<%-- 												<input type="hidden" id="iv_nm1" name="iv_nm1" value="<c:out value='${list.iv_nm1}'/>" /> --%>
<%-- 												<input type="hidden" id="sec_cd" name="sec_cd" value="<c:out value='${list.sec_cd}'/>" /> --%>
<%-- 												<input type="hidden" id="tax_no" name="tax_no" value="<c:out value='${list.tax_no}'/>" /> --%>
<%-- 												<input type="hidden" id="erdat" name="erdat" value="<c:out value='${list.erdat}'/>" /> --%>
<%-- 												<input type="hidden" id="street" name="street" value="<c:out value='${list.street}'/>" /> --%>
<%-- 												<input type="hidden" id="stapr" name="stapr" value="<c:out value='${list.stapr}'/>" /> --%>
<%-- 												<input type="hidden" id="nation" name="nation" value="<c:out value='${list.nation}'/>" /> --%>
<%-- 												<input type="hidden" id="nation_nm" name="nation_nm" value="<c:out value='${list.nation_nm}'/>" /> --%>
<%-- 												<input type="hidden" id="postalcode" name="postalcode" value="<c:out value='${list.postalcode}'/>" /> --%>
<%-- 												<input type="hidden" id="telephone1" name="telephone1" value="<c:out value='${list.telephone1}'/>" /> --%>
<%-- 												<input type="hidden" id="rep_nm" name="rep_nm" value="<c:out value='${list.rep_nm}'/>" /> --%>
<%-- 												<input type="hidden" id="add_eng" name="add_eng" value="<c:out value='${list.add_eng}'/>" /> --%>
<%-- 												<input type="hidden" id="email" name="email" value="<c:out value='${list.email}'/>" /> --%>
<%-- 												<input type="hidden" id="vatno" name="vatno" value="<c:out value='${list.vat_no}'/>" /> --%>
<%-- 												<input type="hidden" id="gerp_cd" name="gerp_cd" value="<c:out value='${list.gerp_cd}'/>" /> --%>
<%-- 												<input type="hidden" id="vendor_type" name="vendor_type" value="<c:out value='${list.vendor_type_nm}'/>" /> --%>
<!-- 												2014-03-19 Kevin. 필수 계약 체결 여부 속성 값. -->
<%-- 												<input type="hidden" id="mandatory_counterparty" name="mandatory_counterparty" value="<c:out value='${list.mandatory}' />" /> --%>
<!-- 											</div>										 -->
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="2" class="tC"><!--<spring:message code="secfw.msg.succ.noResult" /> 12/05/02 박보나 과장 요구 수정-->
											<spring:message code="clm.page.msg.draft.announce018" /> <spring:message code="clm.page.msg.draft.announce019" />		 
										</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</tbody>
						</table>
					</div>
					<!-- //list -->
					
<%-- 					<c:if test="${pageUtil.totalRow > 0}"> --%>
<!-- 							<div> -->
<!-- 							<br> -->
<%-- 							<b>※<spring:message code="clm.page.msg.draft.announce001" /><br> --%>
<!-- 							</b> -->
<!-- 							</div> -->
<%-- 					</c:if> --%>

<!-- 					<div class="total_num"> -->
<%-- 						<spring:message code="secfw.page.field.common.totalData" /> --%>
<%-- 						: <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%> --%>
<!-- 					</div> -->

<!-- 					pagination  -->
<!-- 					<div class="pagination"> -->
<%-- 						<%=pageUtil.getPageNavi(pageUtil, "")%> --%>
<!-- 					</div> -->
					<!-- //pagination -->
					
				</form:form>
			</div>
			</div>

			<!--footer -->
			<div class="pop_footer">
				<!-- button -->
				<div class="btn_wrap tR">
					<span class="btn_all_w"><span class="confirm"></span><a href="javascript:confirm()"><spring:message code='clm.page.msg.common.confirm' /></a></span>
				</div>
				<!-- //button -->
			</div>
			<!-- //footer -->
			
			<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</body>
</html>