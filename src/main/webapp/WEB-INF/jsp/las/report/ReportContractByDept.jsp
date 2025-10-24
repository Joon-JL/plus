<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.Token" %>
<%@ page import="com.sec.las.statistics.dto.StatisticsForm" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="enc.*" %>

<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Iterator" %>

<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.GregorianCalendar" %>
<%@ page import="java.math.BigDecimal" %>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>   
<%--
/**
 * 파     일     명 : ReportContractByDept.jsp
 * 프로그램명 : 총계약건수 by 조직
 * 설               명 : 
 * 작     성     자 : 서백진
 * 작     성     일 : 2011.09
 */
--%>
<%
	//메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	String returnMessage = (String)request.getAttribute("returnMessage");
	HttpSession hs = request.getSession(false);
	
	String term_gbn		="Y";
	String sYear 		="";
	String sMonth 		="";
	String blngt_orgnz_gb="";
	String report_url   = "";
	String img_url   	= "";
	Date cal= new Date();
	int iYear = cal.getYear()+2000-100;
	String blngt_orgnz = (String)hs.getAttribute("secfw.session.blngt_orgnz");
	ArrayList roleList = (ArrayList)hs.getAttribute("secfw.session.role"); //session 사용할 경우
	ArrayList userRoleList = new ArrayList(); //role_cd만 꺼내기 위한 arrayList
	
	if(roleList != null && roleList.size() > 0){	
	    for(int idx = 0; idx < roleList.size(); idx++){	
	        HashMap roleListMap = (HashMap)roleList.get(idx);	 
	        userRoleList.add((String)roleListMap.get("role_cd"));
	    }
	}
	String accessLevel = "";
	
	//사용자 role 비교
	// RA00: 시스템관리자  RA01: 법무관리자  RA02: 법무담당자  RA03: 법무일반사용자
	// RC01: CP관리자
	// RD01: 사업부계약관리자  RD02: 사업부계약담당자
	// RM00: 시스템운영자	
	if(userRoleList != null && userRoleList.size() > 0){
	    if(userRoleList.contains("RA00") || userRoleList.contains("RA01") || userRoleList.contains("RA02")){
	        accessLevel = "A";
	    }else if(userRoleList.contains("RD01") || userRoleList.contains("RD02")){
	        accessLevel = "D";
	        blngt_orgnz_gb = "B";
	    }else if(userRoleList.contains("RC01")){
	        accessLevel = "B";
	    }else{
	        accessLevel = "C";
	    }
	}

	StatisticsForm statisticsForm = (StatisticsForm)request.getAttribute("command") ;
	if(statisticsForm!=null){			
		term_gbn = statisticsForm.getSrch_gbn() ;
		if(langCd.startsWith("ko")){
			report_url = statisticsForm.getReport_url()+"/report/" ;
		}
		else{
			report_url = statisticsForm.getReport_url()+"/report/" + langCd + "_" ;
		}
		
		img_url = statisticsForm.getReport_url()+IMAGE+"/common/" ;
	}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.page.title.report.StatisticsContractByDept" /></title>
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet"/>  
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>

<script language="javascript">
<!--
	$(document).ready(function(){
	    initCal("srch_regdt1");   //첫번재 날짜창 설정 : 변수는 해당 태그의 id값
	    initCal("srch_regdt2");     //두번재 날짜창 설정 : 변수는 해당 태그의 id값
	    //getCodeSelectByUpCd("frm", "cntrtType", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=TOP&combo_abbr=F&combo_type=&combo_del_yn=N&combo_selected=<c:out value='${command.cntrtType}'/>");
	    var frm = document.frm;
	    <%
	    if(!accessLevel.equals("A")){
	    %>	
		frm.srch_dept.disabled = true;
	    <%	
	    }

	    if(statisticsForm.getSrch_regdt1() != null){
	    %>
	    frm.srch_regdt1.value = "<%=statisticsForm.getSrch_regdt1()%>";
	    frm.srch_regdt2.value = "<%=statisticsForm.getSrch_regdt2()%>";
	    <%
	    }else{
		%>
		
		    frm.srch_regdt1.value = "<%=cal.getYear()+2000-100%>"+"-01-31";
		    frm.srch_regdt2.value = getCurDate();

	    <%	
	    }
	    %>
	    termGbn('<%=term_gbn%>'); 
	    rdOpen("", "" , "", 100);	    
	});
	$(function() {

		$(document).keydown(function(event){
	
		});
	});


	function showMessage(msg){
		if(msg != null && msg.length > 1) {
			//alert(msg);
		}
	}	

	function deptView(){
		var frm = document.frm;
		frm.target = "_self";
		frm.action = "<c:url value='/las/report/report.do?reportSrcNm=RD01' />";
		frm.method.value = "listContractStatByDept";
		frm.transfer.value = "D";

		if(validateForm(document.frm) == false) {  
			return;
		}else if(frm.srch_regdt1.value.length > 0 && frm.srch_regdt2.value.length > 0){

			if(frm.srch_regdt1.value > frm.srch_regdt2.value){
				alert("<spring:message code='las.page.field.report.overFromDate'/>");
				frm.srch_regdt1.focus();
				return;
			}else if(frm.srch_regdt2.value > getCurDate()){
				alert("<spring:message code='las.page.field.report.overToDate'/>");
				frm.srch_regdt2.focus();
				return;
			}
		}else if(frm.srch_regdt1.value.length == 0 && frm.srch_regdt2.value.length > 0){
			alert("<spring:message code='las.page.field.report.inputFromDate'/>");
			frm.srch_regdt1.focus();
			return;
		}else if(frm.srch_regdt1.value.length > 0 && frm.srch_regdt2.value.length == 0){
			alert("<spring:message code='las.page.field.report.inputToDate'/>");
			frm.srch_regdt2.focus();
			return;
		}		
		frm.submit();
	
	}	
	function getCurDate(){
		var date = new Date();
		var year = date.getFullYear();
		var month = date.getMonth()+1;
		var day = date.getDate();	
		if(("" + month).length == 1){ month="0" + month; }
		if(("" + day).length == 1) { day="0" + day; }
		var today = year + "-"+month + "-"+day;
		return today; 
	}	

	function rdOpen(path, param, data, zoom, print) {
		Rdviewer.AutoAdjust = 0;
	    Rdviewer.ZoomRatio  = 100;
	    Rdviewer.SetBackgroundColor(255,255,255);
	    Rdviewer.SetPagelineColor(255,255,255);
	    Rdviewer.ViewShowMode (3);
	    //Rdviewer.SetReportDialogInfo (1,"데이타를 조회중입니다.", "데이타 조회중", 1, "보고서를 작성중입니다", "보고서작성중");
		Rdviewer.HideStatusBar();
	    Rdviewer.SetPageScroll(0); 
		Rdviewer.SetParameterEncrypt(1);
		Rdviewer.SetKindOfParam(2);

<%
		int type=2;
		String mrd = new String(C.process(report_url+"contractByType_l.mrd",type));
		String cntrtType = "";
		String dept = "";
		String mrd_param = "";
		String param = "";
		String srch_dt1 = "";
		String srch_dt2 = "";
		
		if(statisticsForm.getCntrtType() != null && statisticsForm.getCntrtType().equals("T01")){
			cntrtType = "E.BIZ_CLSFCN";
		}else if(statisticsForm.getCntrtType() != null && statisticsForm.getCntrtType().equals("T02")){
			cntrtType = "E.DEPTH_CLSFCN";
		}else if(statisticsForm.getCntrtType() == null || statisticsForm.getCntrtType().equals("T03")){
			cntrtType = "E.CNCLSNPURPS_BIGCLSFCN";
		}
		
		if(statisticsForm.getSrch_dept() != null && statisticsForm.getSrch_dept().length() >0){
			dept = statisticsForm.getSrch_dept();
		}
		
		if(statisticsForm.getSrch_regdt1() != null && statisticsForm.getSrch_regdt1().length() >0){
			srch_dt1 = statisticsForm.getSrch_regdt1();
		}else{
			srch_dt1 = iYear+"0101";
		}
		
		if(statisticsForm.getSrch_regdt2() != null && statisticsForm.getSrch_regdt2().length() >0){
			srch_dt2 = statisticsForm.getSrch_regdt2();
		}else{
			srch_dt2 = iYear+"1231";
		}		

		if(accessLevel.equals("A")){	//전사관리자
			mrd_param = 
				"/rp ["+blngt_orgnz_gb+"]"+
				"["+dept+"]"+
				"["+srch_dt1+"]"+
				"["+srch_dt2+"]"+
				" /rchartactive "+
				"/rv image_url["+img_url+"logo.jpg]"+
				" image_confi_url["+img_url+"confidential.jpg]"+
				"/riprnmargin";	

			param = new String(C.process(mrd_param,type));	
%>			
			Rdviewer.FileOpen('<%=mrd%>', '<%=param%>');
<%
		}else{	%>
			alert("<spring:message code='las.page.field.report.noAuth'/>");
<%		}	%>
	    return true;
	}	
	function termGbn(flag){ 
		var frm = document.frm;
		frm.srch_curmonth_s.disabled = true;
		frm.srch_curmonth_e.disabled = true;
		frm.srch_curweek_s.disabled = true;
		//frm.srch_curweek_s.style.visibility = 'hidden';
		frm.srch_gbn.value = flag;
		if(flag == 'Y'){
			frm.srch_curmonth_s.disabled = false;
			frm.srch_regdt1.value = frm.srch_curmonth_s.value+"-01-01" ;

			if(frm.srch_curmonth_s.value == '<%=iYear%>'){
				frm.srch_regdt2.value = getCurDate();
			}else{
				frm.srch_regdt2.value = frm.srch_curmonth_s.value+"-12-31" ;
			}			
			//frm.srch_regdt2.value = frm.srch_curmonth_s.value+"-12-31" ;
						
			document.getElementById("tr_year").style.display = "block";
			document.getElementById("tr_quarter").style.display = "none";	
			document.getElementById("tr_date").style.display = "none";	

			document.getElementById("tr_year1").style.display = "block";
			document.getElementById("tr_quarter1").style.display = "none";	
			document.getElementById("tr_date1").style.display = "none";	
								
			frm.srch_curmonth_s.focus();
		}else if(flag == 'Q'){
			frm.srch_curweek_s.disabled = false;
			frm.srch_curmonth_s.disabled = false;
			document.getElementById("tr_year").style.display = "none";
			document.getElementById("tr_quarter").style.display = "block";	
			document.getElementById("tr_date").style.display = "none";	

			document.getElementById("tr_year1").style.display = "none";
			document.getElementById("tr_quarter1").style.display = "block";	
			document.getElementById("tr_date1").style.display = "none";						
			frm.srch_curweek_s.focus();
			srch_curweek_s = frm.srch_curweek_s.value;
			
			if(srch_curweek_s == "1"){
				frm.srch_regdt1.value = frm.srch_year.value + "-01-01";
				frm.srch_regdt2.value = frm.srch_year.value + "-03-31";
			}else if(srch_curweek_s == "2"){
				frm.srch_regdt1.value = frm.srch_year.value + "-04-01";
				frm.srch_regdt2.value = frm.srch_year.value + "-06-30";
			}else if(srch_curweek_s == "3"){
				frm.srch_regdt1.value = frm.srch_year.value + "-07-01";
				frm.srch_regdt2.value = frm.srch_year.value + "-09-30";
			}else if(srch_curweek_s == "4"){
				frm.srch_regdt1.value = frm.srch_year.value + "-10-01";
				
				if(frm.srch_year.value == '<%=iYear%>'){
					frm.srch_regdt2.value = getCurDate();
				}else{
					frm.srch_regdt2.value = frm.srch_year.value+"-12-31" ;
				}
			}
			
		}else if(flag == 'M'){
			frm.srch_curmonth_s.disabled = false;
			frm.srch_curmonth_e.disabled = false;
			
			document.getElementById("tr_year").style.display = "block";
			document.getElementById("tr_quarter").style.display = "none";	
			document.getElementById("tr_date").style.display = "none";

			document.getElementById("tr_year1").style.display = "block";
			document.getElementById("tr_quarter1").style.display = "none";	
			document.getElementById("tr_date1").style.display = "none";
			chgMonth();				
			frm.srch_curmonth_e.focus();
		}else if(flag == 'P'){
			document.getElementById("tr_year").style.display = "none";
			document.getElementById("tr_quarter").style.display = "none";	
			document.getElementById("tr_date").style.display = "block";			

			document.getElementById("tr_year1").style.display = "none";
			document.getElementById("tr_quarter1").style.display = "none";	
			document.getElementById("tr_date1").style.display = "block";				
			frm.srch_regdt1.focus();
		}
	}
	function chgYear(){ 
		var frm = document.frm;
		var term_gbn = frm.term_gbn.value;
		if(frm.term_gbn[0].checked == true){
			term_gbn = "Y";
		}else if(frm.term_gbn[1].checked == true){
			term_gbn = "Q";
		}else if(frm.term_gbn[2].checked == true){
			term_gbn = "M";
		}else if(frm.term_gbn[3].checked == true){
			term_gbn = "P";
		}
		if(term_gbn == "M"){

			if(frm.srch_curmonth_e.value.length == 1){
				frm.srch_regdt1.value = frm.srch_curmonth_s.value+"-0"+frm.srch_curmonth_e.value+"-"+"01" ;
				frm.srch_regdt2.value = frm.srch_curmonth_s.value+"-0"+frm.srch_curmonth_e.value+"-"+"31" ;
			}else{
				frm.srch_regdt1.value = frm.srch_curmonth_s.value+"-"+frm.srch_curmonth_e.value+"-01" ;	
				frm.srch_regdt2.value = frm.srch_curmonth_s.value+"-"+frm.srch_curmonth_e.value+"-"+"31" ;
			}	
		}else{
			frm.srch_regdt1.value = frm.srch_curmonth_s.value+"-01-01" ;
			
			if(frm.srch_curmonth_s.value == '<%=iYear%>'){
				frm.srch_regdt2.value = getCurDate();
			}else{
				frm.srch_regdt2.value = frm.srch_curmonth_s.value+"-12-31" ;
			}					
		}
	}
	function chgYear1(){ 
		var frm = document.frm;
		
		srch_curweek_s = frm.srch_curweek_s.value;
		if(srch_curweek_s == "1"){
			frm.srch_regdt1.value = frm.srch_year.value + "-01-01";
			frm.srch_regdt2.value = frm.srch_year.value + "-03-31";
		}else if(srch_curweek_s == "2"){
			frm.srch_regdt1.value = frm.srch_year.value + "-04-01";
			frm.srch_regdt2.value = frm.srch_year.value + "-06-30";
		}else if(srch_curweek_s == "3"){
			frm.srch_regdt1.value = frm.srch_year.value + "-07-01";
			frm.srch_regdt2.value = frm.srch_year.value + "-09-30";
		}else if(srch_curweek_s == "4"){
			frm.srch_regdt1.value = frm.srch_year.value + "-10-01";
			frm.srch_regdt2.value = frm.srch_year.value + "-12-31";
		}	

		if(frm.srch_year.value == '<%=iYear%>'){
			frm.srch_regdt2.value = getCurDate();
		}else{
			frm.srch_regdt2.value = frm.srch_year.value+"-12-31" ;
		}				
	}		
	function chgMonth(){ 
		var frm = document.frm;
		var date = new Date();
		if(frm.srch_curmonth_e.value.length == 1){
			frm.srch_regdt1.value = frm.srch_curmonth_s.value+"-0"+frm.srch_curmonth_e.value+"-"+"01" ;
			frm.srch_regdt2.value = frm.srch_curmonth_s.value+"-0"+frm.srch_curmonth_e.value+"-"+"31" ;
		}else{
			frm.srch_regdt1.value = frm.srch_curmonth_s.value+"-"+frm.srch_curmonth_e.value+"-"+"01" ;
			frm.srch_regdt2.value = frm.srch_curmonth_s.value+"-"+frm.srch_curmonth_e.value+"-"+"31" ;
		}	
		if(frm.srch_curmonth_s.value == "<%=iYear%>" && frm.srch_curmonth_e.value == date.getMonth()+1 ){
			frm.srch_regdt2.value = getCurDate();
		}				
	}		
//-->
</script>

</head>
<body > 

<div id="wrap">
	<!-- container -->
	<div id="container">	
	
	<form:form id="frm" name="frm" method="post" autocomplete="off">
		<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
		<!-- 페이지 공통 -->	
		<input type="hidden" name="method"   value="">
		<input type="hidden" name="doSearch" value="">
		<input type="hidden" name="curPage" value="">	
		<input type="hidden" name="initYn" value="">
		<input type="hidden" name="term" value="">
		<input type="hidden" name="transfer" value="D">
		<input type="hidden" name="srch_gbn"   value="<c:out value='${command.srch_gbn}'/>">
	    <!-- Location -->	
	    <div class="location">
	        <img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/>
	    </div>
	    <!-- //Location -->	
	
		<!-- Title -->
		<div class="title">
		<h3><spring:message code="las.page.title.report.StatisticsContractByDept" /></h3>
		</div>
		
		<!-- content --> 		
		<div id="content">
	
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
									<col width="3%"/>
									<col width="27%"/>
									<col width="3%"/>
									<col width="27%"/>																		
								</colgroup>
								
								<tr>
								    <th><spring:message code="las.page.field.statistics.gubun" /></th>
								    <td>
								    <input name="term_gbn" id="term_gbn" type="radio" value="Y" <%=(term_gbn != null && term_gbn.equals("Y")?"checked":"") %> onclick="javascirpt:termGbn('Y')">Yearly
								    <input name="term_gbn" id="term_gbn" type="radio" value="Q" <%=(term_gbn != null && term_gbn.equals("Q")?"checked":"") %> onclick="javascirpt:termGbn('Q')">Quarterly
								    <input name="term_gbn" id="term_gbn" type="radio" value="M" <%=(term_gbn != null && term_gbn.equals("M")?"checked":"") %> onclick="javascirpt:termGbn('M')">Monthly
								    <input name="term_gbn" id="term_gbn" type="radio" value="P" <%=(term_gbn != null && term_gbn.equals("P")?"checked":"") %> onclick="javascirpt:termGbn('P')">Period
								    </td>
								    
								    <td>
									    <table class="search_form">
									    <!-- 
										<colgroup>
											<col width="5%"/>
											<col width="30%"/>							
										</colgroup>
										 -->
										<tr id="tr_date1" style="display:none;">
									    <th><spring:message code="las.page.field.statistics.date" /></th>
										</tr>
																			
										<tr id="tr_year1" style="display:none;">
										<th><spring:message code="las.page.title.report.searchYear" /></th>                                                                                                                                                                                            
										</tr>
										
										<tr id="tr_quarter1" style="display:none;">
				    	        		<th><spring:message code="las.page.title.report.Quarterly" /></th>
										</tr>
										</table>								    
								    </td>
								    <td>
									    <table class="search_form">
									    <!-- 
										<colgroup>
											<col width="5%"/>
											<col width="30%"/>							
										</colgroup>
										 -->
										<tr id="tr_date" style="display:none;">
									    <td>
		  							    <input type="text" name="srch_regdt1" id="srch_regdt1" format='9999-99-99' alt="<spring:message code="secfw.msg.validate.date" />" value="<c:out value='${command.srch_regdt1}'/>"  class="text_calendar02"/>
									    ~
									    <input type="text" name="srch_regdt2" id="srch_regdt2" format='9999-99-99' alt="<spring:message code="secfw.msg.validate.date" />" value="<c:out value='${command.srch_regdt2}'/>" class="text_calendar02"/>
			    	        		    </td>
										</tr>
																			
										<tr id="tr_year" style="display:none;">                                                                                                                                                                                       
			    	        		    <td>
						    			    <select name="srch_curmonth_s" id="srch_curmonth_s" class="select" onchange="javascirpt:chgYear()">
						    			    <%
						    			    
						    			    for(int i=0;i < 6 ; i++){	
						    			    	int iYear1 = iYear-i;
						    			    %>
										    <c:choose>
										    	<c:when test="${command.srch_curmonth_s==null}">
										    	<%
										    		if(iYear1  == iYear){
										    			sYear = iYear1+"";
										    			out.println("<option value='"+iYear1+"' selected>"+iYear1+"</option>");
										    		}else{
										    			out.println("<option value='"+iYear1+"'>"+iYear1+"</option>");
										    		}
										    	 %>
										    	</c:when>
										    	<c:otherwise>	
										    	<%							
										    		if(statisticsForm.getSrch_curmonth_s() != null && statisticsForm.getSrch_curmonth_s().equals(Integer.toString(iYear1))){
										    			sYear = iYear1+"";
										    			out.println("<option value='"+iYear1+"' selected>"+iYear1+"</option>");
										    		}else if(statisticsForm.getSrch_curmonth_s() == null){
										    			if(iYear1  == iYear){
										    				sYear = iYear1+"";
										    				out.println("<option value='"+iYear1+"' selected>"+iYear1+"</option>");
										    			}else{
										    				out.println("<option value='"+iYear1+"'>"+iYear1+"</option>");
										    			}
										    		}else{
										    			out.println("<option value='"+iYear1+"'>"+iYear1+"</option>");
										    		}
									        
										    	 %>
										    	</c:otherwise>
										    </c:choose>
						    			    
						    			    <%} %>    			
						    			    </select><spring:message code="las.page.title.report.Year" />   
						    			    
						    			    <select name="srch_curmonth_e" id="srch_curmonth_e" class="select" onchange="javascirpt:chgMonth()">		
				  			                
						    			    <%
						    			    int iMonth = cal.getMonth();
						    			    for(int i=1;i < 13 ; i++){			    				
						    			    %>
										    <c:choose>
										    	<c:when test="${command.srch_curmonth_e==null}">
										    	<%
										    		if(iMonth+1  == i){
										    			out.println("<option value='"+i+"' selected>"+i+"</option>");
										    		}else{
										    			out.println("<option value='"+i+"'>"+i+"</option>");
										    		}
										    	 %>
										    	</c:when>
										    	<c:otherwise>
										    	<%
										    	if(statisticsForm.getSrch_curmonth_e() != null && statisticsForm.getSrch_curmonth_e().equals(Integer.toString(i))){
										    		out.println("<option value='"+i+"' selected>"+i+"</option>");
										    	}else if(statisticsForm.getSrch_curmonth_e() == null){
										    		if(iMonth+1  == i){
										    			out.println("<option value='"+i+"' selected>"+i+"</option>");
										    		}else{
										    			out.println("<option value='"+i+"'>"+i+"</option>");
										    		}
										    	}else{
										    			out.println("<option value='"+i+"'>"+i+"</option>");
										    	}
										    	 %>
										    	</c:otherwise>
										    </c:choose>			    			
						    			    
						    			    <%} 	%>     			
						    			    </select><spring:message code="las.page.title.report.Month" />  
					    			    </td> 
										</tr>
										
										<tr id="tr_quarter" style="display:none;">
				    	        		<td>
						    			    <select name="srch_year" id="srch_year" class="select" onchange="javascirpt:chgYear1()">
						    			    <%
						    			    
						    			    for(int i=0;i < 6 ; i++){	
						    			    	int iYear1 = iYear-i;
						    			    %>
										    <c:choose>
										    	<c:when test="${command.srch_year==null}">
										    	<%
										    		if(iYear1  == iYear){
										    			sYear = iYear1+"";
										    			out.println("<option value='"+iYear1+"' selected>"+iYear1+"</option>");
										    		}else{
										    			out.println("<option value='"+iYear1+"'>"+iYear1+"</option>");
										    		}
										    	 %>
										    	</c:when>
										    	<c:otherwise>	
										    	<%							
										    		if(statisticsForm.getSrch_year() != null && statisticsForm.getSrch_year().equals(Integer.toString(iYear1))){
										    			sYear = iYear1+"";
										    			out.println("<option value='"+iYear1+"' selected>"+iYear1+"</option>");
										    		}else if(statisticsForm.getSrch_year() == null){
										    			if(iYear1  == iYear){
										    				sYear = iYear1+"";
										    				out.println("<option value='"+iYear1+"' selected>"+iYear1+"</option>");
										    			}else{
										    				out.println("<option value='"+iYear1+"'>"+iYear1+"</option>");
										    			}
										    		}else{
										    			out.println("<option value='"+iYear1+"'>"+iYear1+"</option>");
										    		}
									        
										    	 %>
										    	</c:otherwise>
										    </c:choose>
						    			    
						    			    <%} %>    			
						    			    </select><spring:message code="las.page.title.report.Year" />&nbsp;			    	        		
							    			<select name="srch_curweek_s" id="srch_curweek_s" class="select" onchange="javascirpt:termGbn('Q')">		
					  			
							    			<%
							    			iMonth = cal.getMonth();
							    			for(int i=1;i < 5 ; i++){			    				
							    			%>
											<c:choose>
												<c:when test="${command.srch_curweek_s==null}">
												<%
				
													if(iMonth+1 > 0 &&  iMonth+1 < 4 && i == 1){%>
														<option value='<%=i %>' selected><%=i %> <spring:message code='las.page.title.report.Quarterly' /></option>
													<%}else if(iMonth+1 > 3 &&  iMonth+1 < 7 && i == 2){%>
														<option value='<%=i %>' selected><%=i %> <spring:message code='las.page.title.report.Quarterly' /></option>
													<%}else if(iMonth+1 > 6 &&  iMonth+1 < 10 && i == 3){%>
														<option value='<%=i %>' selected><%=i %> <spring:message code='las.page.title.report.Quarterly' /></option>
													<%}else if(iMonth+1 > 9 && i == 4){%>
														<option value='<%=i %>' selected><%=i %> <spring:message code='las.page.title.report.Quarterly' /></option>
													<%}else{%>
														<option value='<%=i %>' ><%=i %> <spring:message code='las.page.title.report.Quarterly' /></option>
													<%}
												 %>
												</c:when>
												<c:otherwise>
												<%
				
													if(statisticsForm.getSrch_curweek_s() != null && statisticsForm.getSrch_curweek_s().equals(Integer.toString(i))){%>
														<option value='<%=i %>' selected><%=i %> <spring:message code='las.page.title.report.Quarterly' /></option>
													<%}else{%>
														<option value='<%=i %>' ><%=i %> <spring:message code='las.page.title.report.Quarterly' /></option>
													<%}
												 %>
												</c:otherwise>								
											</c:choose>			    			
							    			
							    			<%} 	%>     			
							    			</select>				    			 	        		
					    	        	</td> 									
										</tr>
										</table>
									</td>
								</tr>								
								
								<tr>
		    	        		    <th><spring:message code="las.page.title.report.Dept" /></th>
		    	        		    <td colspan="3">
								    <select name="srch_dept" id="srch_dept"  style='width:130'>

								    <option value='' selected><spring:message code="las.page.field.report.total"/></option>
								    <option value='B00000001' <%=(statisticsForm.getSrch_dept() != null && statisticsForm.getSrch_dept().equals("B00000001")?"selected":"") %>>&nbsp;<spring:message code="las.page.field.report.imgDispDept"/></option>
								    <option value='B00000002' <%=(statisticsForm.getSrch_dept() != null && statisticsForm.getSrch_dept().equals("B00000002")?"selected":"") %>>&nbsp;<spring:message code="las.page.field.report.deptWireless"/></option>
								    <option value='B00000003' <%=(statisticsForm.getSrch_dept() != null && statisticsForm.getSrch_dept().equals("B00000003")?"selected":"") %>>&nbsp;<spring:message code="las.page.field.report.deptMem"/></option>
								    <!--  <option value='' >&nbsp;</option>
								    <option value='C00000000' <%=(statisticsForm.getSrch_dept() != null && statisticsForm.getSrch_dept().equals("C00000000")?"selected":"") %>>--전사기능조직--</option>
								    <option value='C00000001' <%=(statisticsForm.getSrch_dept() != null && statisticsForm.getSrch_dept().equals("C00000001")?"selected":"") %>>&nbsp;한국총괄</option>
								    <option value='C00000002' <%=(statisticsForm.getSrch_dept() != null && statisticsForm.getSrch_dept().equals("C00000002")?"selected":"") %>>&nbsp;CS환경지원센터</option>
								    <option value='C00000003' <%=(statisticsForm.getSrch_dept() != null && statisticsForm.getSrch_dept().equals("C00000003")?"selected":"") %>>&nbsp;수원지원센터</option>
								    <option value='' >&nbsp;</option>	
								    <option value='D00000000' <%=(statisticsForm.getSrch_dept() != null && statisticsForm.getSrch_dept().equals("D00000000")?"selected":"") %>>--R&D특화조직--</option>
								    <option value='D00000001' <%=(statisticsForm.getSrch_dept() != null && statisticsForm.getSrch_dept().equals("D00000001")?"selected":"") %>>&nbsp;종합기술원</option>
								    <option value='D00000002' <%=(statisticsForm.getSrch_dept() != null && statisticsForm.getSrch_dept().equals("D00000002")?"selected":"") %>>&nbsp;반도체연구소</option>
								    <option value='D00000003' <%=(statisticsForm.getSrch_dept() != null && statisticsForm.getSrch_dept().equals("D00000003")?"selected":"") %>>&nbsp;DMC연구소</option>-->	
								    </select>
		    	        		    </td>
								</tr>								
                          
							</table>
						</td>
						
						<td class="vb tC"><a href="javascript:deptView();"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code='las.page.button.search' />"/></a></td>
						</tr>
					</table>
				</div>
	  		</div>	
			
		</div>
		<!-- content -->

</form:form>	

	<div align="center">
		<table width="100%" align="center">
		<tr>
		<td height="600px" >
		<script language="javascript" src="/script/secfw/report/rdviewer.js"></script>
		</td>
		</tr>	
		</table>	
	</div>
	
	<!-- footer -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>	 
	<!-- // footer -->
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
</body>
</html>