<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.Token" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.sec.las.statistics.dto.StatisticsForm" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>   

<%--
/**
 * 파     일     명 : StatisticsResAsgn_l.jsp
 * 프로그램명 : 담당자배정내역 조회
 * 설               명 : 
 * 작     성     자 : 서백진
 * 작     성     일 : 2011.09
 */
--%>
<%
	//메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil") ;
	ArrayList resultList = (ArrayList)request.getAttribute("resultList");
	String curpage = (String)request.getAttribute("curPage");
	String returnMessage = (String)request.getAttribute("returnMessage");
	HttpSession hs = request.getSession(false);
	
	StatisticsForm statisticsForm = (StatisticsForm)request.getAttribute("command") ;
	String locale = statisticsForm.getDmstfrgn_gbn();
	String pattern = "0,000";
	DecimalFormat df = new DecimalFormat(pattern);	
	String report_url   = "";
	String img_url   	= "";	
	int t_totcnt1=0;
	int t_totcnt=0;
	int t_cnclsnpurps_bigclsfcn1=0;
	int t_cnclsnpurps_bigclsfcn2=0;
	int t_cnclsnpurps_bigclsfcn3=0;	
	int t_cnclsnpurps_bigclsfcn4=0;
	int t_cnclsnpurps_bigclsfcn5=0;
	int t_cnclsnpurps_bigclsfcn6=0;
	int t_cnclsnpurps_bigclsfcn7=0;
	int t_cnclsnpurps_bigclsfcn8=0;
	int t_cnclsnpurps_bigclsfcn9=0;
	int t_cnclsnpurps_bigclsfcn10=0;
	int t_cnclsnpurps_bigclsfcn11=0;
	int t_cnclsnpurps_bigclsfcn12=0;
	int t_cnclsnpurps_bigclsfcn13=0;	
	int t_totcnt2=0;  	
	
	if(statisticsForm!=null){			
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
<title><spring:message code="las.page.title.statistics.StatisticsResAsgn" /></title>
<LINK href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet">  
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/CommonProgress.js' />"></script>
<script language="javascript">
<!--
	$(document).ready(function(){
	    initCal("srch_regdt1");   //첫번재 날짜창 설정 : 변수는 해당 태그의 id값
	    initCal("srch_regdt2");     //두번재 날짜창 설정 : 변수는 해당 태그의 id값
	});  
	$(function() {

		if("<c:out value='${command.dmstfrgn_gbn}'/>" == "F"){
			setRadioBtn(document.frm.dmstfrgn_gbn, "F");
		}else{
			setRadioBtn(document.frm.dmstfrgn_gbn, "H");
		}
		
		$(document).keydown(function(event){
	
		});
	});

	function setRadioBtn(obj, value) {
	    if (obj.length > 0) {
	        for (var i = 0; i < obj.length; i++) {
	            if (obj(i).value == value) {
	                obj(i).checked = true;
	            }
	        }
	    }   
	}
	
	function showMessage(msg){
		if(msg != null && msg.length > 1) {
			alert(msg);
		}
	}	

	/**
	* 목록 조회
	*/

	function listStatistics(displayYn, page){
		var frm = document.frm;

		if(validateForm(document.frm) == false) {  
			return;
		}else if(frm.srch_regdt1.value.length > 0 && frm.srch_regdt2.value.length > 0){
			if(frm.srch_regdt1.value > frm.srch_regdt2.value){
				alert("<spring:message code='las.page.field.statistics.overFromDate'/>");
				frm.srch_regdt1.focus();
				return;
			}else if(frm.srch_regdt2.value > getCurDate()){
				alert("<spring:message code='las.page.field.statistics.overToDate'/>");
				frm.srch_regdt2.focus();
				return;
			}
		}else if(frm.srch_regdt1.value.length == 0 && frm.srch_regdt2.value.length > 0){
			alert("<spring:message code='las.page.field.statistics.inputFromDate'/>");
			frm.srch_regdt1.focus();
			return;
		}else if(frm.srch_regdt1.value.length > 0 && frm.srch_regdt2.value.length == 0){
			alert("<spring:message code='las.page.field.statistics.inputToDate'/>");
			frm.srch_regdt2.focus();
			return;
		}
	
		frm.target = "_self";
		frm.action = "<c:url value='/las/statistics/statistics.do' />";
		frm.method.value = "listStatistics";
		frm.doSearch.value= "Y";
		frm.curPage.value = page;

		if("N" != displayYn)
			viewHiddenProgress(true) ;
	
		frm.submit();
		
		if("N" != displayYn)
			progressWinClose();		
	
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
	function rdOpen() {
		var frm = document.frm;
		var param = "";
		var dmstfrgn_gbn="";
		if(frm.dmstfrgn_gbn[0].checked)
			dmstfrgn_gbn = "F";
		else
			dmstfrgn_gbn = "H";

		if(frm.srch_regdt1.value.length > 0 && frm.srch_regdt2.value.length > 0){
			if(frm.srch_regdt1.value > frm.srch_regdt2.value){
				alert("<spring:message code='las.page.field.statistics.overFromDate'/>");
				frm.srch_regdt1.focus();
				return;
			}else if(frm.srch_regdt2.value > getCurDate()){
				alert("<spring:message code='las.page.field.statistics.overToDate'/>");
				frm.srch_regdt2.focus();
				return;
			}
		}else if(frm.srch_regdt1.value.length == 0 && frm.srch_regdt2.value.length > 0){
			alert("<spring:message code='las.page.field.statistics.inputFromDate'/>");
			frm.srch_regdt1.focus();
			return;
		}else if(frm.srch_regdt1.value.length > 0 && frm.srch_regdt2.value.length == 0){
			alert("<spring:message code='las.page.field.statistics.inputToDate'/>");
			frm.srch_regdt2.focus();
			return;
		}		
	<%
		int type=2;
		Date cal= new Date();
		int iYear = cal.getYear()+2000-100;
		String mrd = "StatisticsResAsgn_l.mrd"; 
		String mrd_param = "";

		mrd_param = 
			" /rchartactive "+
			"/rv image_url["+img_url+"logo.jpg]"+
			" image_confi_url["+img_url+"confidential.jpg]"+
			"/riprnmargin";	
	%>	
		param = "/rp ["+dmstfrgn_gbn+"]"+	
				"["+frm.srch_regdt1.value+"]"+
				"["+frm.srch_regdt2.value+"]";
						
		reportPop(param+'<%=mrd_param%>', '<%=report_url+mrd%>', '<%=mrd%>');	
		    //return true;
	}	

    function reportPop(Params , URL, mrd){ 
    	var frm = document.frm;
        frm.target = "PopUpWindow";
        frm.action = "<c:url value='/las/statistics/statistics.do' />";
        frm.method.value = "reportPopup";
        frm.report_param.value = Params;
        frm.report_url.value = URL; 
       	PopUpWindowOpen('', 1000, 450, true);
		frm.submit();
    }	
//-->
</script>

</head>
<body onLoad="showMessage('<%=returnMessage %>');">  

<form:form id="frm" name="frm" method="post" autocomplete="off">
	<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
	<!-- 페이지 공통 -->	
	<input type="hidden" name="method"   value="">
	<input type="hidden" name="doSearch" value="<c:out value='${command.doSearch}'/>">
	<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>">	
	<input type="hidden" name="transfer" value="">
	
	<input type="hidden" name="report_param"   value="">
	<input type="hidden" name="report_url"   value="">		
	<!-- 이중등록 방지 -->
	<input type="hidden" name="TOKEN"  value="<%=Token.set(request) %>">
<!-- container -->
<div id="wrap">	
	<div id="container">	

		<div class="location">
			<img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/>		
			<c:out value='${menuNavi}' escapeXml="false"/>
		</div>	
		
		
		<!-- Title -->
		<div class="title">
		<h3><spring:message code="las.page.title.statistics.StatisticsResAsgn" /></h3>
		</div> 		
		
		<!-- content -->
		<div id="content">
	
		<!-- search-->
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
								<col width="7%"/>
								<col width="10%"/>
								<col width="7%"/>
								<col width="76%"/>								
							</colgroup>
							<tr>
							<th><spring:message code="las.page.field.statistics.scope" /></th>
							<td>
							<input name="dmstfrgn_gbn" id="dmstfrgn_gbn" type="radio" value="H" <%=(locale != null && locale.equals("H")?"checked":"") %>><spring:message code="las.page.tab.lawsuit.dmst" />
							<input name="dmstfrgn_gbn" id="dmstfrgn_gbn" type="radio" value="F" <%=(locale != null && locale.equals("F")?"checked":"") %>><spring:message code="las.page.tab.lawsuit.frgn" />
							</td>
							<th><spring:message code="las.page.field.statistics.date" /></th>
							<td>
  							<input type="text" name="srch_regdt1" id="srch_regdt1" format='9999-99-99' value="<c:out value='${command.srch_regdt1}'/>"  required alt="<spring:message code="las.page.field.contract.library.reg_dt2" />" class="text_calendar02"/>
							~
							<input type="text" name="srch_regdt2" id="srch_regdt2" format='9999-99-99' value="<c:out value='${command.srch_regdt2}'/>" required alt="<spring:message code="las.page.field.contract.library.reg_dt2" />" class="text_calendar02"/>
	    	        		</td>							
							</tr>
						</table>
					</td>
					
					<td class="vb tC">
					<a href="javascript:listStatistics('Y','1');"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code='las.page.button.search' />"/></a>
					</td>
					</tr>
				</table>
			</div>
  		</div>
	 
		<div class="btn_wrap fR">
			<span class="btn"><span class="excel_down"></span><a href="javascript:rdOpen();"><spring:message code="las.page.field.statistics.excelDw"/></a></span>
		</div>
		<!-- list -->
		<table class="list_basic">
		  <colgroup>
			<!--  
		    <col width="25px"/>
		    -->		   
		    <col width="10%"/>
			<col width="10%"/>
			
		    <col width="5%"/>		    
		    <col width="5%"/>
			<col width="5%"/>
		    <col width="5%"/>
		    <col width="5%"/>
		    <col width="5%"/>
		    <col width="5%"/>
		    <col width="5%"/>
			<col width="5%"/>
			<col width="5%"/>
			<col width="5%"/>
			<col width="5%"/>
			<col width="5%"/>	
			<col width="5%"/>
					
			<col width="5%"/>
	      </colgroup>
		  <thead>
		    <tr>
		    <!-- 
		      <th rowspan="2">No</th>
			 -->		      
		      <th rowspan="2"><spring:message code='las.page.field.speakconsult.respman_nm' /></th>
		      <th rowspan="2"><spring:message code='las.page.field.statistics.totcnt' /></th>
		      <th colspan="14"><spring:message code='las.page.field.statistics.contract' /></th>
		      <th rowspan="2"><spring:message code='las.page.field.statistics.consult' /></th>
	        </tr>
			<tr>
				<th class="tal_lineL tal_bg_cor01"><spring:message code='las.page.field.statistics.totcnt1' /></th>
				
				<th class="tal_bg_cor01" title="<spring:message code='las.page.field.statistics.cnclsnpurps_bigclsfcn1' />"><spring:message code='las.page.field.statistics.cnclsnpurps_bigclsfcn1' /></th>
				<th class="tal_bg_cor01"><spring:message code='las.page.field.statistics.cnclsnpurps_bigclsfcn2' /></th>
				<th class="tal_bg_cor01"><spring:message code='las.page.field.statistics.cnclsnpurps_bigclsfcn3' /></th>
				<th class="tal_bg_cor01"><spring:message code='las.page.field.statistics.cnclsnpurps_bigclsfcn4' /></th>
				<th class="tal_bg_cor01" title="<spring:message code='las.page.field.statistics.cnclsnpurps_bigclsfcn5' />"><spring:message code='las.page.field.statistics.cnclsnpurps_bigclsfcn5' /></th>
				<th class="tal_bg_cor01" title="<spring:message code='las.page.field.statistics.cnclsnpurps_bigclsfcn6' />"><spring:message code='las.page.field.statistics.cnclsnpurps_bigclsfcn6' /></th>
				<th class="tal_bg_cor01" title="<spring:message code='las.page.field.statistics.cnclsnpurps_bigclsfcn7' />"><spring:message code='las.page.field.statistics.cnclsnpurps_bigclsfcn7' /></th>
				<th class="tal_bg_cor01" title="<spring:message code='las.page.field.statistics.cnclsnpurps_bigclsfcn8' />"><spring:message code='las.page.field.statistics.cnclsnpurps_bigclsfcn8' /></th>
				<th class="tal_bg_cor01" title="<spring:message code='las.page.field.statistics.cnclsnpurps_bigclsfcn9' />"><spring:message code='las.page.field.statistics.cnclsnpurps_bigclsfcn9' /></th>
				<th class="tal_bg_cor01"><spring:message code='las.page.field.statistics.cnclsnpurps_bigclsfcn10' /></th>
				<th class="tal_bg_cor01" title="<spring:message code='las.page.field.statistics.cnclsnpurps_bigclsfcn11' />"><spring:message code='las.page.field.statistics.cnclsnpurps_bigclsfcn11' /></th>
				<th class="tal_bg_cor01" title="<spring:message code='las.page.field.statistics.cnclsnpurps_bigclsfcn12' />"><spring:message code='las.page.field.statistics.cnclsnpurps_bigclsfcn12' /></th>
				<th class="tal_bg_cor01"><spring:message code='las.page.field.statistics.cnclsnpurps_bigclsfcn13' /></th>
			</tr>
	      </thead>
		  <tbody>

	<%
		if(resultList.size() > 0){
			//int idx = (pageUtil.getThisPage() -1)*pageUtil.getRowPerPage()+1;
			for(int idx=0;idx < resultList.size();idx++){
			    
				ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
				t_totcnt1                  = t_totcnt1                + Integer.parseInt(lom.get("totcnt1").toString());                  
				t_totcnt                   = t_totcnt                 + Integer.parseInt(lom.get("totcnt").toString());                    
				t_cnclsnpurps_bigclsfcn1   = t_cnclsnpurps_bigclsfcn1 + Integer.parseInt(lom.get("cnclsnpurps_bigclsfcn1").toString());    
				t_cnclsnpurps_bigclsfcn2   = t_cnclsnpurps_bigclsfcn2 + Integer.parseInt(lom.get("cnclsnpurps_bigclsfcn2").toString());    
				t_cnclsnpurps_bigclsfcn3   = t_cnclsnpurps_bigclsfcn3 + Integer.parseInt(lom.get("cnclsnpurps_bigclsfcn3").toString()); 
				t_cnclsnpurps_bigclsfcn4   = t_cnclsnpurps_bigclsfcn4 + Integer.parseInt(lom.get("cnclsnpurps_bigclsfcn4").toString());    
				t_cnclsnpurps_bigclsfcn5   = t_cnclsnpurps_bigclsfcn5 + Integer.parseInt(lom.get("cnclsnpurps_bigclsfcn5").toString());    
				t_cnclsnpurps_bigclsfcn6   = t_cnclsnpurps_bigclsfcn6 + Integer.parseInt(lom.get("cnclsnpurps_bigclsfcn6").toString());    
				t_cnclsnpurps_bigclsfcn7   = t_cnclsnpurps_bigclsfcn7 + Integer.parseInt(lom.get("cnclsnpurps_bigclsfcn7").toString());    
				t_cnclsnpurps_bigclsfcn8   = t_cnclsnpurps_bigclsfcn8 + Integer.parseInt(lom.get("cnclsnpurps_bigclsfcn8").toString());    
				t_cnclsnpurps_bigclsfcn9   = t_cnclsnpurps_bigclsfcn9 + Integer.parseInt(lom.get("cnclsnpurps_bigclsfcn9").toString());    
				t_cnclsnpurps_bigclsfcn10  = t_cnclsnpurps_bigclsfcn10+ Integer.parseInt(lom.get("cnclsnpurps_bigclsfcn10").toString());   
				t_cnclsnpurps_bigclsfcn11  = t_cnclsnpurps_bigclsfcn11+ Integer.parseInt(lom.get("cnclsnpurps_bigclsfcn11").toString());   
				t_cnclsnpurps_bigclsfcn12  = t_cnclsnpurps_bigclsfcn12+ Integer.parseInt(lom.get("cnclsnpurps_bigclsfcn12").toString());   
				t_cnclsnpurps_bigclsfcn13  = t_cnclsnpurps_bigclsfcn13+ Integer.parseInt(lom.get("cnclsnpurps_bigclsfcn13").toString()); 	
				t_totcnt2                  = t_totcnt2                + Integer.parseInt(lom.get("totcnt2").toString());
			}				
		%>			
	          <tr>
	          
	          <!-- 
			    <td>0</td>
			     -->
			     
			    <td><spring:message code='las.page.field.statistics.totcnt' /></td>
			    <td style="text-align:right"><%=t_totcnt1>=1000?df.format(t_totcnt1):t_totcnt1%></td>
			    <td style="text-align:right"><%=t_totcnt>=1000?df.format(t_totcnt):t_totcnt  %></td>
			    <td style="text-align:right"><%=t_cnclsnpurps_bigclsfcn1>=1000?df.format(t_cnclsnpurps_bigclsfcn1):t_cnclsnpurps_bigclsfcn1  %></td>
			    <td style="text-align:right"><%=t_cnclsnpurps_bigclsfcn2>=1000?df.format(t_cnclsnpurps_bigclsfcn2):t_cnclsnpurps_bigclsfcn2  %></td>
			    <td style="text-align:right"><%=t_cnclsnpurps_bigclsfcn3>=1000?df.format(t_cnclsnpurps_bigclsfcn3):t_cnclsnpurps_bigclsfcn3  %></td>	
			    <td style="text-align:right"><%=t_cnclsnpurps_bigclsfcn4>=1000?df.format(t_cnclsnpurps_bigclsfcn4):t_cnclsnpurps_bigclsfcn4  %></td>
			    <td style="text-align:right"><%=t_cnclsnpurps_bigclsfcn5>=1000?df.format(t_cnclsnpurps_bigclsfcn5):t_cnclsnpurps_bigclsfcn5  %></td>
			    <td style="text-align:right"><%=t_cnclsnpurps_bigclsfcn6>=1000?df.format(t_cnclsnpurps_bigclsfcn6):t_cnclsnpurps_bigclsfcn6  %></td>
			    <td style="text-align:right"><%=t_cnclsnpurps_bigclsfcn7>=1000?df.format(t_cnclsnpurps_bigclsfcn7):t_cnclsnpurps_bigclsfcn7  %></td>
			    <td style="text-align:right"><%=t_cnclsnpurps_bigclsfcn8>=1000?df.format(t_cnclsnpurps_bigclsfcn8):t_cnclsnpurps_bigclsfcn8  %></td>
			    <td style="text-align:right"><%=t_cnclsnpurps_bigclsfcn9>=1000?df.format(t_cnclsnpurps_bigclsfcn9):t_cnclsnpurps_bigclsfcn9  %></td>
			    <td style="text-align:right"><%=t_cnclsnpurps_bigclsfcn10>=1000?df.format(t_cnclsnpurps_bigclsfcn10):t_cnclsnpurps_bigclsfcn10  %></td>
			    <td style="text-align:right"><%=t_cnclsnpurps_bigclsfcn11>=1000?df.format(t_cnclsnpurps_bigclsfcn11):t_cnclsnpurps_bigclsfcn11  %></td>
			    <td style="text-align:right"><%=t_cnclsnpurps_bigclsfcn12>=1000?df.format(t_cnclsnpurps_bigclsfcn12):t_cnclsnpurps_bigclsfcn12  %></td>
			    <td style="text-align:right"><%=t_cnclsnpurps_bigclsfcn13>=1000?df.format(t_cnclsnpurps_bigclsfcn13):t_cnclsnpurps_bigclsfcn13  %></td>	
			    <td style="text-align:right"><%=t_totcnt2>=1000?df.format(t_totcnt2):t_totcnt2  %></td>         
	          </tr>				   
		<%
			for(int idx=0;idx < resultList.size();idx++){
			    
			   ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);			    	
		%>
	          <tr>
	          <!-- 
			    <td><%=lom.get("rm") %></td>
			     -->
			    <td><%=lom.get("respman_nm") %></td>
			    <td style="text-align:right"><%=lom.get("totcnt1") %></td>
			    <td style="text-align:right"><%=lom.get("totcnt") %></td>
			    <td style="text-align:right"><%=lom.get("cnclsnpurps_bigclsfcn1") %></td>
			    <td style="text-align:right"><%=lom.get("cnclsnpurps_bigclsfcn2") %></td>
			    <td style="text-align:right"><%=lom.get("cnclsnpurps_bigclsfcn3") %></td>	
			    <td style="text-align:right"><%=lom.get("cnclsnpurps_bigclsfcn4") %></td>
			    <td style="text-align:right"><%=lom.get("cnclsnpurps_bigclsfcn5") %></td>
			    <td style="text-align:right"><%=lom.get("cnclsnpurps_bigclsfcn6") %></td>
			    <td style="text-align:right"><%=lom.get("cnclsnpurps_bigclsfcn7") %></td>
			    <td style="text-align:right"><%=lom.get("cnclsnpurps_bigclsfcn8") %></td>
			    <td style="text-align:right"><%=lom.get("cnclsnpurps_bigclsfcn9") %></td>
			    <td style="text-align:right"><%=lom.get("cnclsnpurps_bigclsfcn10") %></td>
			    <td style="text-align:right"><%=lom.get("cnclsnpurps_bigclsfcn11") %></td>
			    <td style="text-align:right"><%=lom.get("cnclsnpurps_bigclsfcn12") %></td>
			    <td style="text-align:right"><%=lom.get("cnclsnpurps_bigclsfcn13") %></td>	
			    <td style="text-align:right"><%=lom.get("totcnt2") %></td>         
	          </tr>
	<%
			}
		} else { // 조회된 데이타가 없을경우
	%>
			  <tr>
			     <td colspan="18"><spring:message code="las.msg.succ.noResult" /></td>
			   </tr>
	<%
		}
	%>	
		  </tbody>
		  </table>
	  
		</div>		
		<!-- //List -->
	
	
	</div>
	<!-- //container -->
	<!-- footer  -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>	
	<!-- // footer -->
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</div>
</form:form>
</body>
</html>