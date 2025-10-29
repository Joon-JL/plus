<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>

<%
List userRoleList = (List)session.getAttribute("secfw.session.role");
boolean isManager = false;
for(int i = 0; i < userRoleList.size(); i++){
	Map item = (Map)userRoleList.get(i);
	String roleCd = (String)item.get("role_cd");
	if(roleCd.equals("RC01")){
		isManager = true;
		break;
	}
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="las.main.title" /></title>
<link  type="text/css" rel="stylesheet" href="<%=CSS%>/las.css"></link>
<link type="text/css" rel="stylesheet"  href="/style/bootstrap.min.css"></link>
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script src="/script/secfw/jquery/jquery-1.7.2.min.js"></script>
<script src="/script/secfw/google/jsapi.js"></script>
<script src="/script/secfw/jquery/angular.min.js"></script>
<script src="/script/secfw/jquery/jquery.blockUI.js"></script>
<script>
	var ablock = false, bblock = false, targetType = "C", targetSub = "";
	
	$(function(){
		
		$("#monthlyprogressTitle, #noncontractedListTitle").hide();
		
		$("#targetType").val(targetType);
		
		$("#customerLink").click(function(){
			$("#vendorTab").removeClass("active");
			$("#customerTab").addClass("active");		
			
			targetType = "C";
			$("#targetType").val(targetType);
			
			drawChart();
			
			init();
		});
		
		$("#vendorLink").click(function(){
			$("#customerTab").removeClass("active");
			$("#vendorTab").addClass("active");		
			
			targetType = "V";
			$("#targetType").val(targetType);
			
			drawChart();
			
			init();
		});
		

		
		// Bind search period
		var startYear = 2014, 
				currentYear = new Date().getFullYear(),
				cachedCurrentYear = currentYear;
		while(startYear<= currentYear){
			var radio = $("<input/>", {type:"radio", name:"years", value:currentYear.toString()});
			if(currentYear === cachedCurrentYear){
				radio.attr("checked", "checked");
			}
			var t = $("<span></span>").css("margin-right", "10px").text(currentYear.toString());
			$("#searchYears").append($("<label></label>").addClass("radio-inline").append(radio).append(t));
			currentYear -= 1;
		}
		
		$("#theYear").text($("input[type=radio]:checked").val());
		$("#targetYear").val($("input[type=radio]:checked").val());
		
		$("#divChartContainer").block({
			message:"<h5>Loading statistics ... <img src='/images/secfw/common/loading_a.gif' /></h5>"
		});
		
	});
	
	// Load the Visualization API and the piechart package.
	google.load('visualization', '1.0', {'packages':['corechart']});
	google.load('visualization', '1', {packages: ['table']});
	
	$("input[type=radio]").change(function(){
		$("#targetYear").val($("input[type=radio]:checked").val());
		
		drawChart();
		// init related children charts.
		$("#divCounterpartyList, #divMonthlyChart").empty();
	});
	
	
	// Set a callback to run when the Google Visualization API is loaded.
    google.setOnLoadCallback(drawChart);
	
	// Drawing column chart illustrating contract conclusion rate in yearly basis.
	function drawChart(){
		
		var dataSource = new google.visualization.DataTable();
		dataSource.addColumn("string", "CompCd");
		dataSource.addColumn("number", "ContractRate");
		dataSource.addColumn({"type":"string", "role":"tooltip", "p": {"html": true}});
		
		$.ajax({
			url:"/las/statistics/gerpStatistics.do?menu_id=20140430000000000_0000000001&method=ListGERPContractRateByYear",
			data:{
				targetYear: $("input[type=radio]:checked").val(),
				targetType: targetType}
		}).done(function(data, status, xhr){
			var item, rate, tooltip;
			if(data.list !== undefined && data != null && data.list.length > 0){
				$(data.list).each(function(idx){
					item = data.list[idx];
					if(targetType === "C"){
						rate = parseFloat(item.customer.toFixed(1));
						tooltip = "<div style='color:red;font-weight:bold;width:150px'>"+item.compcd+": "+rate.toString()+"% ("+item.customerfigure.toString()+"/"+item.customerdenominator.toString()+")</div>";
					} else {
						rate = parseFloat(item.vendor.toFixed(1));
						tooltip = "<div style='color:red;font-weight:bold;width:150px'>"+item.compcd+": "+rate.toString()+"% ("+item.vendorfigure.toString()+"/"+item.vendordenominator.toString()+")</div>";
					}
					dataSource.addRow([item.compcd, rate, tooltip]);
				});
				
				var options = {
					legend:"none",
					tooltip: {isHtml: true},
					chartArea: {left:40, top:30, width:"100%"},
					height:400
				};
				
				// Get chart container
				var chart = new google.visualization.ColumnChart(document.getElementById("divYearlyChart"));
				
				// Register select event handler.
				google.visualization.events.addListener(chart, "select", function(){
					var selectedSub = chart.getSelection()[0];
					
					if(selectedSub){
						var sub = dataSource.getValue(selectedSub.row, 0);
						// if logined user is manager then can see all subsidiarie's detail, otherwise only own subsidiary's data is visiable.
						<% if(isManager){ %>
							selectHandler(sub);
						<%} else {%>
							if(sub == '<%= session.getAttribute("secfw.session.auth_comp_cd").toString().replace("'", "") %>'){
								selectHandler(sub);
							}
						<%}%>
						targetSub = sub;
					}
				});
				
				// Bind column chart
				chart.draw(dataSource, options);
				
				// Bind simplfied contact rate table.
				drawStatTable(data.list);
			} else {
				alert(" There are no results. ");
				$("#tb1, #tb2").hide();
			}
		}).fail(function(xhr, status, errorThrown){
			alert("no data!");
		}).always(function(){
			$("#divChartContainer").unblock();
		});
	}
	
	// 2014-05-12 Kevin added. Table statistics binding
	function drawStatTable(dataList){
		// initialize columns
		$("#tb1 tr").find("th:gt(0), td:gt(0)").remove();
		$("#tb2 tr").find("th:gt(0), td:gt(0)").remove();
		
		$("#tb1, #tb2").show();
		var current_idx = -1;
		var totalCount = totalContracted = 0;
		$(dataList).each(function(idx, item){
			
			var tb, row_idx, col_cnt;
			var customer_rate = parseFloat(item.customer.toFixed(1)) + "%";
			var vendor_rate = parseFloat(item.vendor.toFixed(1)) + "%";
			
			if(targetType == "C"){
				if(item.compcd === "EHQ" || item.compcd === "SRPOL" || item.compcd === "SELSK" || item.compcd === "SESK" || item.compcd === "SEH-P" || item.compcd === "SEPM" || item.compcd === "SELS"){
					return;
				}
				col_cnt = 8;
				current_idx +=1;
				
				tb = (current_idx < col_cnt ? "#tb1" : "#tb2");
				row_idx = (current_idx < col_cnt ? current_idx : current_idx-col_cnt);
				
				var customerdenominator = item.customerdenominator.toString();
				var customerfigure = item.customerfigure.toString()
				
				totalCount += item.customerdenominator;
				totalContracted += item.customerfigure;
				
				if(idx == 0){
					$(tb+" > thead > tr:eq(0)").append($("<th>Total</th>").css({"font-weight": "bold", "background": "#EBEBFF", "color":"red"}));
					$(tb+" > tbody > tr:eq(0)").append($("<td></td>").css("color", "red"));
					$(tb+" > tbody > tr:eq(1)").append($("<td></td>").css("color", "red"));
					$(tb+" > tbody > tr:eq(2)").append($("<td></td>").css({"color":"red", "font-weight":"bold"}).text("10%"));
				}
				// 2014-07-03 Kevin changed.
				// Due to the law issue, only own subsidiary detail data is visiable to user.
				// if logined user is manager then can see all subsidiarie's detail, otherwise only own subsidiary's data is visiable.
				<% if(isManager){ %>
					$(tb+" > thead > tr:eq(0)").append($("<th></th>").css({"font-weight": "bold", "background": "#EBEBFF"}).html('<a href=javascript:selectHandler("'+item.compcd+'")>'+item.compcd+'</a>'));
				<%} else {%>
					if(item.compcd == '<%= session.getAttribute("secfw.session.auth_comp_cd").toString().replace("'", "") %>'){
						$(tb+" > thead > tr:eq(0)").append($("<th></th>").css({"font-weight": "bold", "background": "#EBEBFF"}).html('<a href=javascript:selectHandler("'+item.compcd+'")>'+item.compcd+'</a>'));
					} else {
						$(tb+" > thead > tr:eq(0)").append($("<th></th>").css({"font-weight": "bold", "background": "#EBEBFF"}).text(item.compcd));
					}
				<%}%>
				$(tb+" > tbody > tr:eq(0)").append($("<td></td>").text(customerdenominator));
				$(tb+" > tbody > tr:eq(1)").append($("<td></td>").text(customerfigure));
				$(tb+" > tbody > tr:eq(2)").append($("<td></td>").css({"color":"red", "font-weight":"bold"}).text(customer_rate));
			} else if(targetType == "V") {
				
				if(item.compcd === "SELSK" || item.compcd === "SRPOL" || item.compcd === "EHQ"){
					return;
				}
				col_cnt = 10;
				tb = (idx < col_cnt ? "#tb1" : "#tb2");
				row_idx = (idx < col_cnt ? idx : idx-col_cnt);
				
				var vendordenominator = item.vendordenominator.toString();
				var vendorfigure = item.vendorfigure.toString();
				
				totalCount += item.vendordenominator;
				totalContracted += item.vendorfigure;
				
				if(idx == 0){
					$(tb+" > thead > tr:eq(0)").append($("<th>Total</th>").css({"font-weight": "bold", "background": "#EBEBFF", "color":"red"}));
					$(tb+" > tbody > tr:eq(0)").append($("<td></td>").css("color", "red").text("1000"));
					$(tb+" > tbody > tr:eq(1)").append($("<td></td>").css("color", "red").text("100"));
					$(tb+" > tbody > tr:eq(2)").append($("<td></td>").css({"color":"red", "font-weight":"bold"}).text("10%"));
				}
				<% if(isManager){ %>
					$(tb+" > thead > tr:eq(0)").append($("<th></th>").css({"font-weight": "bold", "background": "#EBEBFF"}).html('<a href=javascript:selectHandler("'+item.compcd+'")>'+item.compcd+'</a>'));
				<%} else {%>
					if(item.compcd == '<%= session.getAttribute("secfw.session.auth_comp_cd").toString().replace("'", "") %>'){
						$(tb+" > thead > tr:eq(0)").append($("<th></th>").css({"font-weight": "bold", "background": "#EBEBFF"}).html('<a href=javascript:selectHandler("'+item.compcd+'")>'+item.compcd+'</a>'));
					} else {
						$(tb+" > thead > tr:eq(0)").append($("<th></th>").css({"font-weight": "bold", "background": "#EBEBFF"}).text(item.compcd));
					}
				<%}%>
				$(tb+" > tbody > tr:eq(0)").append($("<td></td>").text(vendordenominator));
				$(tb+" > tbody > tr:eq(1)").append($("<td></td>").text(vendorfigure));
				$(tb+" > tbody > tr:eq(2)").append($("<td></td>").css({"color":"red", "font-weight":"bold"}).text(vendor_rate));
			}
		});
		
		$("#tb1 > tbody > tr:eq(0) > td:eq(1)").text(totalCount.toString());
		$("#tb1 > tbody > tr:eq(1) > td:eq(1)").text(totalContracted.toString());
		$("#tb1 > tbody > tr:eq(2) > td:eq(1)").text((totalContracted / totalCount * 100).toFixed(1)+"%");
	}
	
	// Yearly chart select event handler.
	function selectHandler(sub){
		
		$("#divChartContainer").block({
			message:"<h5>Updating statistics ...  <img src='/images/secfw/common/loading_a.gif' /></h5>"
		});
		
		$("#targetCompCd").val(sub);
		$("#tb3, #divNoncoutractedListArea").hide();
		
		ablock = bblock = true;
		
		// Bind table chart(left)
		bindSimpleDetailList(sub);
		// Bind line chart(right)
		bindContractConclusionRateInMonthlyBasis(sub);
	}
	
	// detail list
	function bindSimpleDetailList(sub){
		
		$("#sSub").text("["+sub+" - "+(targetType === "C" ? "Customer" : "Vendor")+"]");
		$("span[name='stype']").text((targetType === "C" ? "Customer" : "Vendor"));
		
		$("#divCounterpartyList").empty();
		
		$("#noncontractedListTitle").show();
		
		var detailList = new google.visualization.DataTable();
		detailList.addColumn("string", "Code");
		detailList.addColumn("string", "Name");
		detailList.addColumn("string", "Mandatory");
		detailList.addColumn("string", "Contract");
		
		$.ajax({
			url:"/las/statistics/gerpStatistics.do?menu_id=20140430000000000_0000000001&method=ListGERPNoncontractedCounterparty",
			data:{
				targetYear: $("input[type=radio]:checked").val(), 
				targetComp:sub,
				targetType: targetType
			}
		}).done(function(data, status, xhr){
			
			if(data != null && data.list !== "undefined" && data.list.length > 0){
				
				$("#tb3 > tbody > tr:eq(0) > td:eq(0)").text(data.stat.total);
				$("#tb3 > tbody > tr:eq(0) > td:eq(1)").text(data.stat.mandatory);
				$("#tb3 > tbody > tr:eq(0) > td:eq(2)").text(data.stat.cnt);
				$("#tb3 > tbody > tr:eq(0) > td:eq(3)").text(data.stat.rate+" %");
				
				$(data.list).each(function(idx){
					var item = data.list[idx];
					detailList.addRow([item.gerpcd, item.name, item.mandatory, item.cnt]);
					if(idx == 0){
						detailList.setProperty(0, 0, "style", "width:14%");
						detailList.setProperty(0, 1, "style", "width:50%");
						detailList.setProperty(0, 2, "style", "width:19%");
						detailList.setProperty(0, 3, "style", "width:17%");
					}
				});
				
				var table = new google.visualization.Table(document.getElementById("divCounterpartyList"));
				table.draw(detailList, {showRowNumber: false, allowHtml: true,  height:300, width:"98%"});
				
				$("#tb3, #divNoncoutractedListArea").show();
			}
		}).fail(function(xhr, status, errorThrown){
			alert("no data[1]!");
		}).always(function(){
			if(!bblock){
				$("#divChartContainer").unblock();
			}
			ablock = false;
		});

	}
	
	// Bind monthly contract conclusion rate
	function bindContractConclusionRateInMonthlyBasis(sub){
		
		$("#divMonthlyChart").empty();
		
		var monthlyContractRate = new google.visualization.DataTable();
		monthlyContractRate.addColumn("string", "Month");
		monthlyContractRate.addColumn("number", "Rate");
		monthlyContractRate.addColumn({"type":"string", "role":"annotation"});

		
		$("#monthlyprogressTitle").show();
		
		$.ajax({
			url:"/las/statistics/gerpStatistics.do?menu_id=20140430000000000_0000000001&method=ListGERPContractRateInMontlyBasis",
			data:{
				targetYear: $("input[type=radio]:checked").val(), 
				targetComp:sub,
				targetType: targetType
			}
		}).done(function(data, status, xhr){
			
			if(data != null && data.list !== "undefined" && data.list.length > 0){
				$(data.list).each(function(idx){
					var item = data.list[idx];
					if(targetType === "C"){
						monthlyContractRate.addRow([item.yearmonth.split("-")[1], item.cnumber, item.cnumber+"%"]);
					} else {
						monthlyContractRate.addRow([item.yearmonth.split("-")[1], item.vnumber, item.vnumber+"%"]);
					}
					if(data.list.length === (idx+1)){
						$("#lastUpdate").text(item.updatedate);
					}
				});
				
				var options = {
				     hAxis: {title: "Month", titleTextStyle: {color: "red"}},
					chartArea:{left:30, width:"100%"},				     
				     height:450,
				     legend:"none",
				     vAxis: {maxValue: 100, minValue: 0}
				};
				
				var lineChart = new google.visualization.LineChart(document.getElementById("divMonthlyChart"));
				lineChart.draw(monthlyContractRate, options);
				
			}
		}).fail(function(xhr, status, errorThrown){
			alert("no data[2]!");
		}).always(function(){
			if(!ablock){
				$("#divChartContainer").unblock();
			}
			bblock = false;
		});
		
	}
	
	// Excel download
	function excelDownload(isManager){
		var frm = document.frm;
	    
		if(isManager){
	    	$("#targetCompCd").val("");
	    }
	    
		frm.target = "_self";
		frm.action = "/las/statistics/gerpStatistics.do?menu_id=20140430000000000_0000000001&method=NoncontractedCounterpartyExceldownload";
	    frm.submit();
	}
	
	function init(){
		// Top column chart
		$("#divYearlyChart").empty();
		
		// left botton table chart
		$("#tb3 > tbody > tr:eq(0) > td").each(function(idx, ele){
			$(ele).text("");
		});
		$("#tb3, #divNoncoutractedListArea").hide();
		$("#sSub").text("");
		$("#divCounterpartyList").empty();
		// right botton line chart
		$("#divMonthlyChart").empty();
	}
</script>
</head>
<body>
<div id="wrap">
    <div id="container">        
	    <!-- Location -->
		<div class="location"><IMG SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->	 
	        
	    <!-- title -->
	    <div class="title">
	        <h3><spring:message code="las.page.field.statistics.contractConclusionRate"/></h3>
	    </div>
	    <!-- //title -->
		        
		 <!-- content -->
		 <div id="content">
		 	<div id="content_in">
				<form id="frm" name="frm" method="post" autocomplete="off">
					<input type="hidden" id="targetCompCd" name="targetCompCd" />
					<input type="hidden" id="targetYear" name="targetYear" />
					<input type="hidden" id="targetType" name="targetType" />
					
			         <!-- search -->
			         <div class="search_box">
			             <div class="search_box_inner">
			                 <table class="search_tb">
			                 	<colgroup>
	                                <col/>
	                                <col width="75px"/>
	                            </colgroup>
			                     <tr>
			                         <td>
			                             <table class="search_form">
			                                 <colgroup>
			                                     <col width="150px"/>
			                                     <col width="*%"/>                            
			                                 </colgroup>
			                                 <tr>
			                                     <th><spring:message code="las.page.field.statistics.searchYear"/></th>
			                                     <td>
			                                     	<div id="searchYears"></div>
			                                     </td>
			                                 </tr>
			                             </table>
			                         </td>
			                     </tr>
			                 </table>
			             </div>
			         </div>
			         <!-- // search -->
			         
			         <!-- tab -->
			         <ul class="nav nav-tabs">
						  <li class="active" id="customerTab"><a href="#" id="customerLink">Customer</a></li>
						  <li  id="vendorTab"><a href="#" id="vendorLink">Vendor</a></li>
					</ul>
					<!-- /tab -->
					
			         <!-- chart -->
					<div id="divChartContainer">
						<table style="width:100%; border=0;table-layout: fixed;">
							<tr>
								<td colspan="2">
									<div id="divYearlyChart" style="width:100%;"></div>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<table id="tb1" style="width:100%; display:none;" class="table table-bordered">
										<thead>
											<tr>
												<th style="background:#EBEBFF; width:13%;">Subsidiary</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td style="background:#EBEBFF">Total Count</td>
											</tr>
											<tr>
												<td style="background:#EBEBFF">Contracted Count</td>
											</tr>
											<tr>
												<td style="background:#EBEBFF">Current Rate</td>
											</tr>
										</tbody>
									</table>
									
									<table id="tb2" style="width:100%; display:none;" class="table table-bordered">
										<thead>
											<tr>
												<th style="background:#EBEBFF; width:13%;">Subsidiary</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td style="background:#EBEBFF">Total Count</td>
											</tr>
											<tr>
												<td style="background:#EBEBFF">Contracted Count</td>
											</tr>
											<tr>
												<td style="background:#EBEBFF">Current Rate</td>
											</tr>
										</tbody>
									</table> 
									<div style="margin-bottom:30px;"><h5>* Please note that only statuses after Approval in progress of Conclusion step are subjects to the contract conclusion rate.</h5></div>
								</td>
							</tr>
							<tr>
								<td>
									<div style="display:block;" id="noncontractedListTitle">
										<h4 style="display:inline-block;">- Detail List&nbsp;<span id="sSub"></span></h4>
									</div>
								</td>
								<td><div id="monthlyprogressTitle"><h4>- Monthly progress of <span id="theYear"></span>&nbsp;<small>(last update: <span id="lastUpdate"></span>)</small></h4></div></td>
							</tr>
							<tr>
								<td style="width:50%; height:400px;">
									<table id="tb3" style="width:98%; display:none;" class="table table-bordered">
										<colgroup>
											<col width="20%" />
											<col width="25%" />
											<col width="38%" />
											<col width="17%" />
										</colgroup>
										<thead>
											<tr style="background:#EBEBFF; font-weight:bold;">
												<th>Total <span name="stype"></span></th>
												<th>Mandatory <span name="stype"></span></th>
												<th>Contracted <span name="stype"></span>(Mandatory)</th>
												<th>Contract Rate</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td></td><td></td><td></td><td></td>
											</tr>
										</tbody>
									</table>
									<div id="divNoncoutractedListArea" style="display:none;">
										<!-- <div class="panel-group" id="unregisteredList">
										  	<div class="panel panel-default">
										    	<div class="panel-heading">
										      		<h4 class="panel-title">
										        		<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">* Unregistered&nbsp;<span name="stype"></span></a>
										      		</h4>
										    	</div>
										    	<div id="collapseOne" class="panel-collapse collapse in">
										      		<div class="panel-body">Unregistered vendor/customers will be listed up here</div>
										    	</div>
										  </div>
										</div> -->
										<div id="divCounterpartyList" ></div>
										<div style="margin-top:30px; width:100%; " id="nonContractedListComments">
											* This list just shows max 300 customers/vendors for reasons.<br>&nbsp;&nbsp;&nbsp;If you need whole list then please <a href="javascript: excelDownload();" id="btnExcelDownload"><b>click</b></a> to download with excel.
											<%if(isManager){ %>
											<br>
											<a href="javascript:excelDownload(true);"><h5>Download All</h5></a>
											<%} %>
										</div>
									</div>
								</td>
								<td style="width:50%">
									<div id="divMonthlyReportArea">
										<div id="divMonthlyChart" ></div>
									</div>
								</td>
							</tr>
						</table>
					</div>
					<!-- //chart -->
					
				</form>
			</div>
		</div>
		<!-- //content -->
	</div>
</div>

<!-- footer -->
<script language="JavaScript" src="/script/clms/footer.js"></script>
<!-- //footer -->
</body>
</html>