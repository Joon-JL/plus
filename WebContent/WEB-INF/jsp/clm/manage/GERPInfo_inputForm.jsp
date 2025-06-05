<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
	
	$$(["D", "E", "F"]).each(function(idx, item){
		$$.ajax({
			type: "GET",
			url: "/clm/manage/consideration.do?menu_id=20140404000000000_0000000001&method=getGERPCodeListByType",
			data: {"gerpCodeType": item},
			dataType: "json"
		}).done(function(data){
			var selectBox = null;
			if(item === "D"){
				selectBox = $$("#sCostType");
			} else if(item === "E"){
				selectBox = $$("#sContractType");
			} else if(item === "F"){
				selectBox = $$("#sGERPDivList");
			}
			
			if(data.result === "succeeded"){
				selectBox.empty();
				selectBox.append("<option value=''>All</option>");
				$$(data.list).each(function(idx, item){
					selectBox.append("<option value='"+item.code+"'>"+item.desc+"</option>");
				});
			} else if(data.result === "no data"){
				selectBox.empty();
				selectBox.append("<option value=''>None</option>");
			}
		});
	});
	
	var cntrtid = ($$(parent.document).find("#mod_cntrt_id").val() || $$(parent.document).find("#cntrt_id").val());
	if(cntrtid != null && cntrtid != ""){
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
						$$("#sGERPDivList").val(data.divCode);
						$$("#sCostType").val(data.costType);
						$$("#sContractType").val(data.contractType);
					}
		}).fail(function(xhr, textStatus, errorThrown){
			$$("#hidGerpResult").val(xhr.responseText);
		});
	}
	
	$$("#gerpInfoBody").unblock();
	
});
</script>
</head>
<body style='margin:0; padding:0' id="gerpInfoBody">
	<form id="formGERPInfo">
	     <div class="title_basic3"><spring:message code="clm.page.field.contract.gerp.gerpInformation"/></div>
	     <table id="tb_grp_info" class="table-style01">
	      <colgroup>
	      	<col width="20%"/>
	      	<col width="14%"/>
	      	<col width="18%"/>
	      	<col width="14%"/>
	      	<col width="18%"/>
	      	<col width="16%"/>
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
		           <td><select id="sGERPDivList" name="sGERPDivList" style="width:140px"></select></td>
		       </tr>
		       <tr>
		           <th><spring:message code="clm.page.field.contract.gerp.costType"/></th>
		           <td><select id="sCostType" name="sCostType" style="width:120px"></select></td>
		           <th><spring:message code="clm.page.field.contract.gerp.contractClassification"/></th>
		           <td><select id="sContractType" name="sContractType" style="width:120px"></select></td>
		           <th></th>
		           <td></td>
		       </tr>
	      </tbody>
	  </table>
	  <input type="hidden" id="hidGerpResult">
   </form>
</body>
<script>
	
	function fnSetCounterpartyNameNCode(name, code, isContractRequired, vendorType){
		$$("#sCounterpartyName").html(name);
		$$("#sGerpCode").html(code);
		if(isContractRequired == null || isContractRequired == ""){
			$$("#sIsContractRequired").html("Not Decided");
		} else {
			$$("#sIsContractRequired").html((isContractRequired.toUpperCase() === "Y" ? "Yes" : "No"));	
		}
		$$("#sVendorType").html(vendorType);
	}
</script>
</html>