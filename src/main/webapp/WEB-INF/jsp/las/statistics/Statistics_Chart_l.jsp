<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="org.codehaus.jettison.json.JSONObject" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>

<%-- 
/**
 * 파  일  명 : Statistics_Chart_l.jsp
 * 프로그램명 : 검토의뢰건수 통계 차트화면 
 * 설      명 : 관련사통계 검토의뢰 건수 통계 차트조회 화면
 * 작  성  자 : 신성우
 * 작  성  일 : 2014.02
 */
--%>

<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<title><spring:message code="las.page.field.statistics.statistics" text="noMsg"/></title>
<%-- <link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet"> --%>

<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="/script/secfw/jquery/jquery-1.6.2.min.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery-migrate-1.2.1.min.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.form.js"></script>
<script language="javascript" src="/script/secfw/jquery/calendar/jquery.mousewheel.min.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script language="javascript" src="/script/secfw/google/jsapi.js"></script>
<script type="text/javascript">
google.load("visualization", "1", {packages:["corechart"]});
//google.setOnLoadCallback(drawChart);
function drawChart(ChartType, title) {
	
	//changed for ie7 and ie8 it's undefined row version windows
	var width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
	var height = window.innerHeight  || document.documentElement.clientHeight || document.body.clientHeight;
	var options = {
		title : '',
		titleTextStyle: {fontSize:30},
		allowHtml:true, 
		width: width/2,
		height: height-150,
		bar: { groupWidth: '75%' },
		vAxis : {minvalue:10, textStyle:{fontSize:13}},
		hAxis : {textStyle:{fontSize:10}},
		legendFontSize:12,
		//fontSize:10,
		isStacked: true
	};
	
		//chart1 data binding
		var jsonData = $.ajax({
			url: "/las/statistics/statistics.do?method=statistics_List&menu_id=20140226063925547_0000000463&srch_type=PC&ChartType="+ChartType,
			dataType:"json",
			async: false
		}).responseText; 
		var obj = jQuery.parseJSON(jsonData);
		var data = new google.visualization.DataTable(obj);
		
		options.title = title + "Contract Statistics";
		chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
		chart.draw(data, options);

		//chart2 data binding
		jsonData = $.ajax({
		url: "/las/statistics/statistics.do?method=statistics_List&menu_id=20140226063925547_0000000463&srch_type=PL&ChartType="+ChartType,
		dataType:"json",
		async: false
		}).responseText; 
		obj = jQuery.parseJSON(jsonData);	      
		data = new google.visualization.DataTable(obj);
		
		options.title = title + "Legal Advice Statistics";
		chart = new google.visualization.ColumnChart(document.getElementById('chart_div1'));
  		chart.draw(data, options);
	  		
}

function divChange(flag){

	document.getElementById("chart").style.display = "none";
	var c = $.ajax({
		url: "/las/statistics/statistics.do?method=listStatisticsNew&gubun=c_l_advice&menu_id=20131028175145507_0000000463&srch_type="+flag,
		//dataType:"text",
		async: false
		}).responseText; 
	document.getElementById("statistics_chart").innerHTML = c;	
	document.getElementById("location").style.display = "none";
	document.getElementById("btnwrap").style.display = "none";
	document.getElementById("title").style.display = "none";
}
//top 10
function a(){
	document.getElementById("chart").style.display = "";
	document.getElementById("statistics_chart").innerHTML = "";
	google.setOnLoadCallback(drawChart("Top10", "Top10 + Others "));
};
//others
function b(){
	document.getElementById("chart").style.display = "";
	document.getElementById("statistics_chart").innerHTML = "";
	google.setOnLoadCallback(drawChart("Othes", "Others "));
};
//Chart list
function c(){
	document.getElementById("chart").style.display = "none";
	var c = $.ajax({
		url: "/las/statistics/statistics.do?method=listStatisticsNew&gubun=c_l_advice&menu_id=20131028175145507_0000000463",
		//dataType:"text",
		async: false
		}).responseText; 
	document.getElementById("statistics_chart").innerHTML = c;	
	document.getElementById("location").style.display = "none";
	document.getElementById("btnwrap").style.display = "none";
	document.getElementById("title").style.display = "none";
};
$(document).ready(function () {
	
	var time = <%= Float.parseFloat(request.getParameter("time").toString())%>;
	
	if(time == null || time == "" || (isNaN(parseFloat(time)) && isFinite(time)))
		time = 60000;
	
	var intervalFunctions = [a,b,c];
	var intervalIndex = 0;
	
	//start top10
	a();
	//refresh as the setting times
	window.setInterval(function(){
		intervalFunctions[intervalIndex++ % intervalFunctions.length]();
	},time);
});

</script>
</head>
<body>
<div id="wrap">
    <div id="container">        
        
    <!-- title -->
    <div class="title">
        <h1 style="font-family: Arial"><spring:message code="las.page.field.statistics.statistics" text="noMsg"/></h1>
		<div class="location" style="height: 0"></div>
    </div> 
    <!-- //title -->
        
        <!-- content -->
		<table id="chart">
			<tr>
				<td width="50%"><div id="chart_div"></div></td>
				<td width="50%"><div id="chart_div1"></div></td>
			</tr>
		</table>
		<div id="statistics_chart"/>
        <!-- //content -->
        
        <jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>    
       
    </div>
    <!-- //container -->
</div>
<!-- //wrap -->

<!-- footer -->
<script language="JavaScript" src="/script/clms/footer.js"></script>
<!-- //footer -->
</body>
</html>
