<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%-- 
/**
 * 파  일  명 : CustomerTest_l.jsp
 * 프로그램명 : 계약상대방 검색 테스트
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<link href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet" />

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<!-- 달력관련추가 -->
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script language="javascript"> 
    document.domain = "samsung.net"; 
</script> 
<script language="javascript">

    function customerPop(flag){
    	var frm = document.frm;
		var customer = "";
    	if(flag == "M"){
    		PopUpWindowOpen('', 900, 600, true);
    		frm.target = "PopUpWindow";
    		frm.action = "<c:url value='/clm/draft/customerTest.do' />";
    		frm.method.value = "listCustomerTest";
    		frm.srch_customer.value = customer;
    		frm.customer_gb.value   = flag;
    		frm.submit();
    	}else if(flag == "O"){
    		PopUpWindowOpen('', 900, 600, true);
    		frm.target = "PopUpWindow";
    		frm.action = "<c:url value='/clm/draft/customerTest.do' />";
    		frm.method.value = "listCustomerTest";
    		frm.srch_customer.value = customer;
    		frm.customer_gb.value   = flag;
    		frm.submit();
    	} else {
    		PopUpWindowOpen('', 750, 800, true);
    		frm.target = "PopUpWindow";
    		frm.action = "<c:url value='/clm/manage/myContract.do' />";
    		frm.method.value = "detailTypePopup";
    		frm.submit();
    	}
    }
    
  //거래상대방 팝업 리턴 값.
	function returnCustomer(obj){
		var returnVal = "";
		
		returnVal += "<span id='customer_"+obj.index+"'>"+obj.customer_nm1;
    	returnVal += "</span>&nbsp;&nbsp;";
	    
	    $('#customerReturnVal').append(returnVal);
	}
  
    function popDetailSrh(){
    	
    	PopUpWindowOpen('', 900, 600, true);
		frm.target = "PopUpWindow";
		frm.action = "<c:url value='/clm/manage/myContract.do' />";
		frm.method.value = "detailTypePopup";
    	
    }
    
    //상세 검색 리턴값.
	function returnCustomer2(obj){
		var returnVal = "";
		
		returnVal += "<span id='customer_Z'>"+obj.signman_nm;   // 넘어 오는 값에 대한 것을 받기
	   	returnVal += "</span>&nbsp;&nbsp;";
	    
	    $('#customerReturnVal').append(returnVal);
	    
	    goSearch();
	}
    
    function goSearch(){
    	alert("<spring:message code="clm.page.msg.draft.announce002" />");
    }
    
    //거래선 상세 페이지로 이동하게 변경 하기
	function rowOnClick(customerCd, dodun){
		var frm = document.frm;
		
		PopUpWindowOpen('', 900, 300, true);
		frm.target = "PopUpWindow";
		
		frm.customer_cd.value = customerCd;
		frm.dodun.value       = dodun;
		
		frm.action = "<c:url value='/clm/draft/customerTest.do'/>";
		
		frm.method.value = "detailCustomerShare";
		frm.submit();	
		
	}
    
    function goContract(){
    	
        var frm = document.frm;
		
		PopUpWindowOpen('', 900, 300, true);
		frm.target = "PopUpWindow1";
		
		frm.action = "<c:url value='/link.do'/>";
		frm.cntrt_gbn.value       = "ContractBu";
		
		frm.method.value = "linkSsoCheck";
		frm.submit();	
    }
   
   function setInfo(object){
	   var frm = document.frm;
	   
	   frm.customer_Z.value = object.cntNm;
	   
   }
   
   function setContract(id, name) {
	   document.getElementById("clmNo").value = id;
	  // document.getElementById("clmName").value = name;
   }
   
   /**
	* 연관계약 계약 목록 팝업
	*/
	function popupListContract(arg){
		//var frm = document.frm;
		//arg=$("#rel_type").val();
		//var parent_cntrt_id = "";
		//var parent_cntrt_nm = "";
	    
		
		//C03204
		//var result = new retRC(parent_cntrt_id ,parent_cntrt_nm ,rel_type1);
		
		//if(frm.cntrt_nm.value != ""){
		
		var rel_type1 = 'C03204';
			
		PopUpWindowOpen("/clm/manage/myContract.do?method=listMyContract&status_mode=rel", 840, 600, true);
		 
		//} else {
		// alert("  연관계약을 입력하기 위해서는 먼저 의뢰하려는\n 계약 정보의 계약명이 먼저 입력이 되어야 합니다.\n 계약 유형과 거래선을 먼저 선택하여 주세요.");
		 // frm.parent_cntrt_nm.focus();
		//}
	}
   
   /**
   * 연관계약 파법 목록 - 법무 
   */
   function popupListlas(){
	   
	   PopUpWindowOpen("/las/contractmanager/consideration.do?method=listConsideration&dmstfrgn=H&page_flag=5&page_mode=rel&menu_id=20111021144301175_0000000246", 840, 600, true);
	   
   }
   
   
	  /**
	  * Obj init
	  */
	  function retRC(parent_cntrt_id ,parent_cntrt_nm ,rel_type1){
		  this.parent_cntrt_id = parent_cntrt_id;
		  this.parent_cntrt_nm = parent_cntrt_nm;
		  this.rel_type1 = rel_type1;
	  }
	  
	  
	  
	  /**
		 *
		 */
		 function PopUpWindowOpen(surl, popupwidth, popupheight, bScroll) {
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
		     
		     
		     //팝업창  주소표시줄 검색 제공 자 없애기 위해서 수정 
		     var Feture = "toolbar=0, location=0, status=1, menubar=0, scrollbars="+(bScroll ? "yes" : "no")+", resizable=yes, top="+Top+", left="+Left+", width="+popupwidth+", height="+popupheight;
		     //var Feture = "fullscreen=no,toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=" + (bScroll ? "yes" : "no") + ",resizable=no,left=" + Left + ",top=" + Top + ",width=" + popupwidth + ",height=" + popupheight;
			 PopUpWindow = window.open(surl, "PopUpWindow" , Feture);
			 PopUpWindow.focus();
		 }

</script> 

</head>

<body>
<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id='frm' method="post" autocomplete="off"> 
<!-- search form -->
<input type="hidden" name="method"       value="" />
<input type="hidden" name="menu_id"      value="<c:out value='${command.menu_id}'/>" />
<!-- Detail Page Param -->
<!-- key Form -->
<input type="hidden" name="customer_gb" id="customer_gb" value="" />
<input type="hidden" name="srch_customer" id="srch_customer" value="" />

<input type="hidden" name="customer_cd" id="customer_cd" value="" />
<input type="hidden" name="dodun" id="dodun" value="" />

<input type="hidden" name="cntrt_gbn" id="cntrt_gbn" value="" />


<input type="hidden" name="rel_type" id="rel_type" value="C5021" />


<!-- //
**************************** Form Setting **************************** 
-->
<div id="wrap">
	<!-- container -->
	<div id="container">
	    <!-- Location -->
		<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->
		<!-- Title -->
		<div class="title">
		<h3><spring:message code="clm.page.title.customer.title" /></h3>
		<!-- //title -->
		</div>
		<!-- //title -->
		
		<!-- content -->
		<div id="content">
		    <!-- view -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01" style='margin-bottom:20px'>
				<colgroup>
					 <col width="20%"/>
					 <col width="80%"/>
				</colgroup>
				<tbody>
					<tr>
						<th><spring:message code="clm.page.msg.draft.many" /></th>
						<td>
						    <input type="text" name="customer_M" id="customer_M" value="" style="width:150px; " class="text_search" /><a href="javascript:customerPop('M');"><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search" /></a>
					    </td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.draft.single" /></th>
						<td><input type="text" name="customer_O" id="customer_O" value="" style="width:150px; " class="text_search" /><a href="javascript:customerPop('O');"><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search" /></a>
					    </td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.draft.connContPop" /></th>
						<td>
						    <input type="text" name="clmNo" id="clmNo" value="" style="width:150px; " class="text_search" /><a href="http://clm.sec.samsung.net:8080/link.do?method=linkSsoCheck&cntrt_gbn=Contract&returnURL=ABCDE" target="_a"><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search" /></a>
					    </td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.draft.connReqPop" /></th>
						<td>
						    <input type="text" name="customer_Z" id="customer_Z" value="" style="width:150px; " class="text_search" /><a href="http://clm.sec.samsung.net:8080/link.do?method=linkSsoCheck&cntrt_gbn=ContractBu&returnURL=ABCDE" target="_b"><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search" /></a>
					    </td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.draft.relContCont" /></th>
						<td>
						    <input type="text" name="customer_Z" id="customer_Z" value="" style="width:150px; " class="text_search" /><a href="javascript:popupListContract('C03201');"><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search" /></a>
					    </td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.draft.relContLegal" /></th>
						<td>
						    <input type="text" name="customer_Z" id="customer_Z" value="" style="width:150px; " class="text_search" /><a href="javascript:popupListlas();"><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search" /></a>
					    </td>
					</tr>
					<tr>
					    <td colspan="2">&nbsp;<span id="customerReturnVal"></span></td>
					</tr>
				</tbody>
			</table>
			<!-- //view -->
		</div>
		<!-- //content -->
	
		<!-- footer -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- //footer -->
	</div>
	<!-- //container -->
</div>	
</form:form>
</body>

</html>