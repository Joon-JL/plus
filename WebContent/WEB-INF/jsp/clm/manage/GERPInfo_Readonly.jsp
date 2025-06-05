<!DOCTYPE html >
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>GERP Information</title>
<!-- [if IE]><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
<link href="/style/las/en/las.css" type="text/css" rel="stylesheet">
<script src="/script/secfw/jquery/jquery-1.7.2.min.js"></script>
<script src="/script/secfw/jquery/jquery.blockUI.js"></script>
<script>
var $$ = jQuery.noConflict(true);
$$(function(){
	$$("#gerpInfoBody").block({
		message:"<h1>Loading G-ERP Information now ...</h1>"
	});
});
</script>
</head>
<body style='margin:0; padding:0' id="gerpInfoBody">
	<form id="formGERPInfo">
		<input type="hidden" id="hidGerpResult">
	     <div class="title_basic3"><spring:message code="clm.page.field.contract.gerp.gerpInformation"/></div>
	     <table id="tb_grp_info" class="table-style01">
	      <colgroup>
	      	<col width="22%"/>
	      	<col width="14%"/>
	      	<col width="18%"/>
	      	<col width="14%"/>
	      	<col width="18%"/>
	      	<col width="14%"/>
	      </colgroup>
	      <tbody>
		      	<tr>
		           <th><spring:message code="clm.page.msg.common.otherParty"/></th>
		           <td colspan="3"><span id="sCounterpartyName"></span></td>
		           <th><spring:message code="clm.page.field.customer.gerpCode"/></th>
		           <td><span id="sGerpCode"></span></td>
		       </tr>
		       <tr>
		           <th><spring:message code="clm.page.field.customer.IsContractRequired2"/></th>
		           <td><span id="sIsContractRequired"></span></td>
		           <th><spring:message code="clm.page.field.customer.vendorType"/></th>
		           <td><span id="sVendorType"></span></td>
		           <th><spring:message code="clm.page.field.contract.gerp.headDivisionCode"/></th>
		           <td><span id="sDivName"></span></td>
		       </tr>
		       <tr>
		           <th><spring:message code="clm.page.field.contract.gerp.costType"/></th>
		           <td><span id="sCostType"></span></td>
		           <th><spring:message code="clm.page.field.contract.gerp.contractClassification"/></th>
		           <td><span id="sContractType"></span></td>
		           <th></th>
		           <td></td>
		       </tr>
	      </tbody>
	  </table>
   </form>
</body>
<script>
	var cntrtid = "<%=request.getAttribute("cntrtid").toString()%>";
	
	// Select GERP detail information.
	$$.post("/clm/manage/consideration.do?menu_id=20140404000000000_0000000001&method=getGERPInformation",
			"cntrtid="+cntrtid,
			function(data){
				if(data.result != "succeeded"){
					$$("#hidGerpResult").val(data.result);
					return;
				} else {
					$$("#sCounterpartyName").html(data.name);
					$$("#sGerpCode").html(data.gerpCd);
					$$("#sIsContractRequired").html((data.mandatory == "Y" ? "Yes" : "No"));
					$$("#sVendorType").html(data.vebderTypeDesc);
					$$("#sDivName").html(data.divCodeDesc);
					$$("#sCostType").html(data.costTypeDesc);
					$$("#sContractType").html(data.contractTypeDesc);
				}
	}).fail(function(xhr, textStatus, errorThrown){
		$$("#hidGerpResult").val(xhr.responseText);
	}).always(function(){
		$$("#gerpInfoBody").unblock();
	});
	
</script>
</html>
