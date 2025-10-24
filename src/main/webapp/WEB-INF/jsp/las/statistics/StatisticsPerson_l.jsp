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
<%@ page import="java.util.Date" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>   
<%--
/**
 * 파     일     명 : StatisticsPerson_l.jsp
 * 프로그램명 : 개인별 통계 현황 조회
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
	String pattern = "0.##";
	DecimalFormat df = new DecimalFormat(pattern);
	
	pattern = "0,000";
	DecimalFormat df1 = new DecimalFormat(pattern);	

	String report_url   = "";
	String img_url   	= "";
	
	int t_req1 =0 ;
	int t_re_req1=0;
	int t_reply1=0;
	float t_reply_time1=0;
	int t_req2 =0 ;
	int t_re_req2=0;
	int t_reply2=0;
	float t_reply_time2=0; 
	int t_req3 =0 ;
	int t_re_req3=0;
	int t_reply3=0;
	float t_reply_time3=0;
	int t_req4 =0 ;
	int t_re_req4=0;
	int t_reply4=0;
	float t_reply_time4=0;
	int t_req5 =0 ;
	int t_re_req5=0;
	int t_reply5=0;
	float t_reply_time5=0;	
	
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
<title><spring:message code="las.page.title.statistics.StatisticsPerson" /></title>
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
		frm.method.value = "forwardStatistics";
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
	
	function showMessage(msg){
		if(msg != null && msg.length > 1) {
			alert(msg);
		}
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
		String mrd = "StatisticsPerson_l.mrd"; 
		String mrd_param = "";

		mrd_param = 
			"/rp ["+statisticsForm.getSrch_beforeweek_s()+"]"+
			"["+statisticsForm.getSrch_beforeweek_e()+"]"+
			"["+statisticsForm.getSrch_beforemonth_s()+"]"+
			"["+statisticsForm.getSrch_beforemonth_e()+"]"+
			"["+statisticsForm.getSrch_curweek_s()+"]"+
			"["+statisticsForm.getSrch_curweek_e()+"]"+
			"["+statisticsForm.getSrch_curmonth_s()+"]"+
			"["+statisticsForm.getSrch_curmonth_e()+"]"+			
			" /rchartactive "+
			"/rv image_url["+img_url+"logo.jpg]"+
			" image_confi_url["+img_url+"confidential.jpg]"+
			"/riprnmargin";	
	%>		
		reportPop('<%=mrd_param%>', '<%=report_url+mrd%>', '<%=mrd%>');	
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

<!-- container -->
<div id="wrap">
<div id="container">	
<form:form id="frm" name="frm" method="post" autocomplete="off">
	<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
	<!-- 페이지 공통 -->	
	<input type="hidden" name="method"   value="">
	<input type="hidden" name="doSearch" value="<c:out value='${command.doSearch}'/>">
	<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>">	
	<input type="hidden" name="transfer" value="person">
	
	<input type="hidden" name="report_param"   value="">
	<input type="hidden" name="report_url"   value="">		
	<!-- 이중등록 방지 -->
	<input type="hidden" name="TOKEN"  value="<%=Token.set(request) %>">
	<div class="location">
		<img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/>		
		<c:out value='${menuNavi}' escapeXml="false"/>
	</div>	
	<!-- content -->
		<!-- Title -->
		<div class="title">
		<h3><spring:message code="las.page.title.statistics.StatisticsPerson" /></h3>
		</div> 		
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

			<col width="75px"/>
		    
		    <col width="30px"/>
		    <col width="55px"/>
		    <col width="45px"/>
			<col width="45px"/>
			
		    <col width="30px"/>
		    <col width="60px"/>
		    <col width="45px"/>
		    <col width="45px"/>
		    
		    <col width="30px"/>
		    <col width="60px"/>
			<col width="45px"/>
			<col width="45px"/>
			
		    <col width="30px"/>
		    <col width="60px"/>
			<col width="45px"/>
			<col width="45px"/>
			
		    <col width="30px"/>
		    <col width="55px"/>
			<col width="55px"/>
			<col width="45px"/>			
						
	      </colgroup>
		  <thead>
		    <tr>

		      <th rowspan="2"><spring:message code='las.page.field.speakconsult.respman_nm' /></th>
		      <th colspan="4"><spring:message code='las.page.field.statistics.perform1' /></th>
		      <th colspan="4"><spring:message code='las.page.field.statistics.perform3' /></th>
		      <th colspan="4"><spring:message code='las.page.field.statistics.perform2' /></th>
		      <th colspan="4"><spring:message code='las.page.field.statistics.perform4' /></th>
		      <th colspan="4"><spring:message code='las.page.field.statistics.perform5' /></th>
	        </tr>
			<tr>
				<th class="tal_lineL tal_bg_cor01"><spring:message code='las.page.field.statistics.req' /></th>
				<th class="tal_bg_cor01"><spring:message code='las.page.field.statistics.re_req' /></th>
				<th class="tal_bg_cor01"><spring:message code='las.page.field.statistics.reply' /></th>
				<th class="tal_bg_cor01"><spring:message code='las.page.field.statistics.replytime' /></th>
				
				<th class="tal_bg_cor01"><spring:message code='las.page.field.statistics.req' /></th>
				<th class="tal_bg_cor01"><spring:message code='las.page.field.statistics.re_req' /></th>
				<th class="tal_bg_cor01"><spring:message code='las.page.field.statistics.reply' /></th>
				<th class="tal_bg_cor01"><spring:message code='las.page.field.statistics.replytime' /></th>
				
				<th class="tal_bg_cor01"><spring:message code='las.page.field.statistics.req' /></th>
				<th class="tal_bg_cor01"><spring:message code='las.page.field.statistics.re_req' /></th>
				<th class="tal_bg_cor01"><spring:message code='las.page.field.statistics.reply' /></th>
				<th class="tal_bg_cor01"><spring:message code='las.page.field.statistics.replytime' /></th>
				
				<th class="tal_bg_cor01"><spring:message code='las.page.field.statistics.req' /></th>
				<th class="tal_bg_cor01"><spring:message code='las.page.field.statistics.re_req' /></th>
				<th class="tal_bg_cor01"><spring:message code='las.page.field.statistics.reply' /></th>
				<th class="tal_bg_cor01"><spring:message code='las.page.field.statistics.replytime' /></th>
				
				<th class="tal_bg_cor01"><spring:message code='las.page.field.statistics.req' /></th>
				<th class="tal_bg_cor01"><spring:message code='las.page.field.statistics.re_req' /></th>
				<th class="tal_bg_cor01"><spring:message code='las.page.field.statistics.reply' /></th>
				<th class="tal_bg_cor01"><spring:message code='las.page.field.statistics.replytime' /></th>
																
			</tr>
	      </thead>
		  <tbody>

	<%
		int t_reply_cnt1 = 0;
		int t_reply_cnt2 = 0;
		int t_reply_cnt3 = 0;
		int t_reply_cnt4 = 0;
		int t_reply_cnt5 = 0;
		if(pageUtil != null && pageUtil.getTotalRow() > 0){
			int idx = (pageUtil.getThisPage() -1)*pageUtil.getRowPerPage()+1;
			for(idx=0;idx < resultList.size();idx++){
			    
				ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
				t_req1          = t_req1        + Integer.parseInt(lom.get("req1").toString());                  
				t_re_req1       = t_re_req1     + Integer.parseInt(lom.get("re_req1").toString());                    
				t_reply1        = t_reply1      + Integer.parseInt(lom.get("reply1").toString());    
				t_reply_time1   = t_reply_time1 + Float.parseFloat(lom.get("reply_time1").toString())-1; 
				   
				t_req2          = t_req2        + Integer.parseInt(lom.get("req2").toString());                  
				t_re_req2       = t_re_req2     + Integer.parseInt(lom.get("re_req2").toString());                    
				t_reply2        = t_reply2      + Integer.parseInt(lom.get("reply2").toString());    
				t_reply_time2   = t_reply_time2 + Float.parseFloat(lom.get("reply_time2").toString())-1;
				
				t_req3          = t_req3        + Integer.parseInt(lom.get("req3").toString());                  
				t_re_req3       = t_re_req3     + Integer.parseInt(lom.get("re_req3").toString());                    
				t_reply3        = t_reply3      + Integer.parseInt(lom.get("reply3").toString());    
				t_reply_time3   = t_reply_time3 + Float.parseFloat(lom.get("reply_time3").toString())-1; 
				
				t_req4          = t_req4        + Integer.parseInt(lom.get("req4").toString());                  
				t_re_req4       = t_re_req4     + Integer.parseInt(lom.get("re_req4").toString());                    
				t_reply4        = t_reply4      + Integer.parseInt(lom.get("reply4").toString());    
				t_reply_time4   = t_reply_time4 + Float.parseFloat(lom.get("reply_time4").toString())-1; 	
				
				t_req5          = t_req5        + Integer.parseInt(lom.get("req5").toString());                  
				t_re_req5       = t_re_req5     + Integer.parseInt(lom.get("re_req5").toString());                    
				t_reply5        = t_reply5      + Integer.parseInt(lom.get("reply5").toString());    
				t_reply_time5   = t_reply_time5 + Float.parseFloat(lom.get("reply_time5").toString())-1; 
				
				if(Integer.parseInt(lom.get("reply1").toString()) > 0)
					t_reply_cnt1 = t_reply_cnt1 + 1;
				
				if(Integer.parseInt(lom.get("reply2").toString()) > 0)
					t_reply_cnt2 = t_reply_cnt2 + 1;
				
				if(Integer.parseInt(lom.get("reply3").toString()) > 0)
					t_reply_cnt3 = t_reply_cnt3 + 1;
				
				if(Integer.parseInt(lom.get("reply4").toString()) > 0)
					t_reply_cnt4 = t_reply_cnt4 + 1;
				
				if(Integer.parseInt(lom.get("reply5").toString()) > 0)
					t_reply_cnt5 = t_reply_cnt5 + 1;
			}				
		%>	
			<tr>
			    
			    <td><spring:message code='las.page.field.statistics.totcnt' /></td>		
			    <td class="tR"><%=t_req1>=1000?df1.format(t_req1):t_req1%></td>	
			    <td class="tR"><%=t_re_req1>=1000?df1.format(t_re_req1):t_re_req1%></td>	
			    <td class="tR"><%=t_reply1>=1000?df1.format(t_reply1):t_reply1%></td>			    
			    <td class="tR"><%=(t_reply_cnt1 > 0)?df.format(t_reply_time1/t_reply_cnt1):"0"%></td>
			    
			    <td class="tR"><%=t_req2>=1000?df1.format(t_req2):t_req2%></td>	
			    <td class="tR"><%=t_re_req2>=1000?df1.format(t_re_req2):t_re_req2%></td>	
			    <td class="tR"><%=t_reply2>=1000?df1.format(t_reply2):t_reply2%></td>			    
			    <td class="tR"><%=(t_reply_cnt2 > 0)?df.format(t_reply_time2/t_reply_cnt2):"0"%></td>
			    
			    <td class="tR"><%=t_req3>=1000?df1.format(t_req3):t_req3%></td>	
			    <td class="tR"><%=t_re_req3>=1000?df1.format(t_re_req3):t_re_req3%></td>	
			    <td class="tR"><%=t_reply3>=1000?df1.format(t_reply3):t_reply3%></td>			    
			    <td class="tR"><%=(t_reply_cnt3 > 0)?df.format(t_reply_time3/t_reply_cnt3):"0"%></td>
			    
			    <td class="tR"><%=t_req4>=1000?df1.format(t_req4):t_req4%></td>	
			    <td class="tR"><%=t_re_req4>=1000?df1.format(t_re_req4):t_re_req4%></td>	
			    <td class="tR"><%=t_reply4>=1000?df1.format(t_reply4):t_reply4%></td>			    
			    <td class="tR"><%=(t_reply_cnt4 > 0)?df.format(t_reply_time4/t_reply_cnt4):"0"%></td>
			    
			    <td class="tR"><%=t_req5>=1000?df1.format(t_req5):t_req5%></td>	
			    <td class="tR"><%=t_re_req5>=1000?df1.format(t_re_req5):t_re_req5%></td>	
			    <td class="tR"><%=t_reply5>=1000?df1.format(t_reply5):t_reply5%></td>			    
			    <td class="tR"><%=(t_reply_cnt5 > 0)?df.format(t_reply_time5/t_reply_cnt5):"0"%></td>				    
			</tr>		
		
		<%		
			for(idx=0;idx < resultList.size();idx++){
			    
			   ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);			    	
	%>
	          <tr>

			    <td class="tL"><%=lom.get("respman_nm") %></td>
		     
			    <td class="tR"><%=(Integer)lom.get("req1")>=1000?df1.format((Integer)lom.get("req1")):(Integer)lom.get("req1")%></td>	
			    <td class="tR"><%=(Integer)lom.get("re_req1")>=1000?df1.format((Integer)lom.get("re_req1")):(Integer)lom.get("re_req1")%></td>	
			    <td class="tR"><%=(Integer)lom.get("reply1")>=1000?df1.format((Integer)lom.get("reply1")):(Integer)lom.get("reply1")%></td>				    
			    <td class="tR"><%= df.format(Float.valueOf((String)lom.get("reply_time1")).floatValue()-1)%></td>
			    
			    <td class="tR"><%=(Integer)lom.get("req2")>=1000?df1.format((Integer)lom.get("req2")):(Integer)lom.get("req2")%></td>	
			    <td class="tR"><%=(Integer)lom.get("re_req2")>=1000?df1.format((Integer)lom.get("re_req2")):(Integer)lom.get("re_req2")%></td>	
			    <td class="tR"><%=(Integer)lom.get("reply2")>=1000?df1.format((Integer)lom.get("reply2")):(Integer)lom.get("reply2")%></td>				    
			    <td class="tR"><%= df.format(Float.valueOf((String)lom.get("reply_time2")).floatValue()-1)%></td>
			    
			    <td class="tR"><%=(Integer)lom.get("req3")>=1000?df1.format((Integer)lom.get("req3")):(Integer)lom.get("req3")%></td>	
			    <td class="tR"><%=(Integer)lom.get("re_req3")>=1000?df1.format((Integer)lom.get("re_req3")):(Integer)lom.get("re_req3")%></td>	
			    <td class="tR"><%=(Integer)lom.get("reply3")>=1000?df1.format((Integer)lom.get("reply3")):(Integer)lom.get("reply3")%></td>				    
			    <td class="tR"><%= df.format(Float.valueOf((String)lom.get("reply_time3")).floatValue()-1)%></td>
			    
			    <td class="tR"><%=(Integer)lom.get("req4")>=1000?df1.format((Integer)lom.get("req4")):(Integer)lom.get("req4")%></td>	
			    <td class="tR"><%=(Integer)lom.get("re_req4")>=1000?df1.format((Integer)lom.get("re_req4")):(Integer)lom.get("re_req4")%></td>	
			    <td class="tR"><%=(Integer)lom.get("reply4")>=1000?df1.format((Integer)lom.get("reply4")):(Integer)lom.get("reply4")%></td>				    
			    <td class="tR"><%= df.format(Float.valueOf((String)lom.get("reply_time4")).floatValue()-1)%></td>
			    
			    <td class="tR"><%=(Integer)lom.get("req5")>=1000?df1.format((Integer)lom.get("req5")):(Integer)lom.get("req5")%></td>	
			    <td class="tR"><%=(Integer)lom.get("re_req5")>=1000?df1.format((Integer)lom.get("re_req5")):(Integer)lom.get("re_req5")%></td>	
			    <td class="tR"><%=(Integer)lom.get("reply5")>=1000?df1.format((Integer)lom.get("reply5")):(Integer)lom.get("reply5")%></td>				    
			    <td class="tR"><%= df.format(Float.valueOf((String)lom.get("reply_time5")).floatValue()-1)%></td>				    			    			    	         
	          </tr>
	<%
			}
		} else { // 조회된 데이타가 없을경우
	%>
			  <tr>
			     <td colspan="22"><spring:message code="las.msg.succ.noResult" /></td>
			   </tr>
	<%
		}
	%>	
				
		  </tbody>
		  </table>
		<!-- 페이징 처리 
		<div class="pagination"><%=pageUtil!=null?pageUtil.getPageNavi(pageUtil, ""):"" %></div>
		-->		  
	</div>		
		<!-- //List -->
	</form:form>
	
</div>
<!-- //container -->
	<!-- footer  -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
	<!-- // footer -->
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</div>	
</body>
</html>