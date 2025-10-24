<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.Token" %>
<%@ page import="com.sec.las.statistics.dto.StatisticsWeekForm" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>
<%@ page import="java.util.ArrayList" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>   
<%@ page import="java.util.Date" %>
<%--
/**
 * 파     일     명 : StatisticsWeek_d.jsp
 * 프 로 그 램 명 : 주간업무 기본 조회
 * 설             명 : 주간업무에서 각 텝으로 이동이 되며 각 실적 및 계약을 보여준다. 
 * 작     성     자 : 김재원 
 * 작     성     일 : 2011.09
 * 수     정     자 : 서백진
 * 수     정     일 : 2011.10 
 */
--%>
<%
	//메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	ListOrderedMap lom = (ListOrderedMap)request.getAttribute("lom");
	Date cal= new Date();

	String sYear ="";
	String returnMessage = (String)request.getAttribute("returnMessage");	
	StatisticsWeekForm statisticsWeekForm = (StatisticsWeekForm)request.getAttribute("command") ;
	String locale = statisticsWeekForm.getDmstfrgn_gbn();			
%>

<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<title><spring:message code="las.page.field.board.weekSchedule.header" /></title>
<LINK href="<%=CSS%>/popup.css" type="text/css" rel="stylesheet">
<LINK href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">  
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value="/script/clms/jquery-1.6.2.min.js"/> type="text/javascript"></script>


<script language="javascript">
<!--
/**
* enter값으로 조회 하기
*/
	$(function() {
		
		$(document).keydown(function(event){
			
			if(event.keyCode == "13") {
				var target = event.target;
				if(target.id == "srch_cntnt") {
					//listLawSuitBasic("Y","1");
				} else {
					return false;
				}
			}
		});
	});
	/**
	* 수정
	*/
	function enableView(){
		document.all("namo1").style.display = "";
		//document.all("namo2").style.display = "";
		document.all("btn1").style.display = "";
		document.all("btn2").style.display = "";		
		document.all("id_crntweek_rslt").style.display = "none";
		//document.all("id_nextmonth_plan").style.display = "none";			
	}
	/**
	* 수정
	*/
	function modifyView(){
		var frm = document.frm;
	    //frm.seqno.value = seqno;
	    frm.body_mime.value = frm.wec[0].MIMEValue;
	    frm.body_mime1.value = frm.wec[1].MIMEValue;
	    frm.transfer.value = "etc";

		frm.target = "_self";
		frm.action = "<c:url value='/las/statistics/statisticsWeek.do' />";
		frm.method.value = "modifyStatisticsWeek";
		frm.page_gbn.value = "M";
		if(confirm("<spring:message code='secfw.msg.ask.update' />")){
			frm.submit();
		}
	}	
	/**
	* 등록
	*/
	function insertView(){
		var frm = document.frm;
	    //나모웹에디터 관련
	    frm.body_mime.value = frm.wec[0].MIMEValue;
	    frm.body_mime1.value = frm.wec[1].MIMEValue;
		frm.target = "_self";
		frm.action = "<c:url value='/las/statistics/statisticsWeek.do' />";
		frm.method.value = "insertStatisticsWeek";
		frm.page_gbn.value = "I";
		frm.transfer.value = "etc";
		if(confirm("<spring:message code='secfw.msg.ask.insert' />")){
			frm.submit();
		}
	}
	/**
	* Tab 이동
	*/
	function moveTab(flag){
		var frm = document.frm;
		if(validateForm(document.frm) == false) {  
			return false;
		}
    	var sData = new Array(3);
    	sData = GetWeekDay(document.all["year"].value,document.all["weekseq"].value);

   		sFrToday = "("+sData[1] + " ~ "+sData[2]+")";   //시작일 종료일 	
   		frm.weekFirstDay.value = sData[1];
   		frm.weekLastDay.value = sData[2];
	
		frm.target = "_self";
		frm.action = "<c:url value='/las/statistics/statisticsWeek.do' />";
		frm.method.value = "forwardStatisticsWeek";
				
		if(flag == "1"){
			frm.transfer.value = "contract";
		}else if(flag == "2"){
			frm.transfer.value = "consult";
		}else if(flag == "4"){
			frm.transfer.value = "etc";
		}
		frm.doSearch.value= "Y";
		frm.submit();
	}	
	function showMessage(msg){
		if(msg != null && msg.length > 1) {
			alert(msg);
		}
	}
	function GetWeekNo()
	{
	    var sTemp; 
	    var sToday; 
	    var sTemp1;
	    var sTemp2;  
	    var iIntTemp; 
	    var iIntTemp2; 
	    var now = new Date();
	    var startWeekDay;       //요일
	    var iYear; 
	    var sFebDayMax;         //2월달 날자수
	    var sMonArr = new Array();          //월
	    var iMon_DayArr = new Array();      //월별 총날자수
	    var sScopeDay = new Array();        //주차 시작일, 주차 종료일
	    var iRow; 
	    var iBasicWeek;        //주차 기준일을 설정
	    var iRowArrayNum;      //전체주수
	    var sYear;              //현재연도
	    var sData = new Array(3);
	    
	    //GetWeekNo = False
	    //저번주 목요일부터 이번주 수요일까지 1주차
	    // 0 1  2  3  4  5  6 
	    // 일 월 화 수 목 금 토
	    //iBasicWeek = 3      //목요일날 회의하는 날 (만일 수요일날 회의날자면 2 이됨)
	    //iBasicWeek = 4      //금요일날 회의하는 날 (만일 수요일날 회의날자면 2 이됨)
	    iBasicWeek = 3;      //수요일날 회의하는 날 (만일 수요일날 회의날자면 2 이됨)
	    
	    sMonArr = Array("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
	    iMon_DayArr = Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
	    
	    sYear = now.getFullYear(); 
	    
	    iYear = sYear *1;
	    //var math = new Math();
	    if( iYear >= 1900 && iYear <= 2100){
	        if( iYear % 4 == 0){
	            iMon_DayArr[1] = 29;
	        }
	        if( iYear % 100 == 0 ){
	            iMon_DayArr[1] = 28;
	        }
	        if( iYear % 400 == 0 ){
	            iMon_DayArr[1] = 29;
	        }
	    }
  
	    //저번주 목요일부터 이번주 수요일까지 1주차 // 수요일부터 화요일까지 주차가 바뀜..(2005/06/01)-법무팀 2주일간회의로
	    // 0  1  2  3  4  5  6
	    // 일 월 화 수 목 금 토
	    sTemp = "1/1/" + sYear;
	    var dTemp = new Date(sTemp);	    
	    startWeekDay = dTemp.getDay();   //1월 1일이 무슨요일인가
	    startWeekDay = startWeekDay + 1;
	    iRowArrayNum = 53;

	    //sScopeDay[iRowArrayNum, 2];  //시작일, 종료일
	    sScopeDay = new Array(iRowArrayNum, 2);
		
	    if( startWeekDay <= iBasicWeek ){
	        sScopeDay[0] = [sYear + "-01-01", sYear + "-01-0" + (iBasicWeek - startWeekDay + 1)];
	    }else if( startWeekDay == 7 ){  //2011년
	        sScopeDay[0] = [sYear + "-01-01", sYear + "-01-04"];
	    }else{
	        sScopeDay[0] = [sYear + "-01-01",sYear + "-01-0" + Math.abs(iBasicWeek - startWeekDay - 2)];//심우규 수정 2005년부터 주차가 잘못되어있음..
	        //sScopeDay(0, 1) = sYear + "-01-07"
	    }
	    
	    if( now.getMonth() + 1 < 10 ){
	        sToday = sYear + "0" + (now.getMonth()+1);
	    }else{
	        sToday = sYear + ""+(now.getMonth()+1);
	    }
	    
	    if(now.getDate() < 10 ){
	        sToday = sToday + "0" + now.getDate();
	    }else{
	        sToday = sToday + ""+ now.getDate();
	    } 
	    
	    for( iRow = 1 ;iRow < 54;iRow++){
		
	        if( (sScopeDay[iRow - 1][1]).substring(5, 10) != "12-31" ){
	            sTemp = replaceStr(sScopeDay[iRow - 1][1], "-", "")*1 + 1;
	            sTemp = sTemp+"";
	            
	            iIntTemp = sTemp.substring(4, 6)*1;   //sTemp 문자열의 가운데 월부분
	            iIntTemp2 = sTemp.substring(6, 8)*1;   //sTemp 문자열의 마지막 날자부분
            
	            if( iIntTemp < 12 ){
	                if( iIntTemp2 > iMon_DayArr[iIntTemp - 1] ){
	                    sTemp = sYear + sMonArr[iIntTemp] + "01";
	                }
	            }
	            
	            sTemp1 = sTemp.substring(0, 4) + "-" + sTemp.substring(4, 6) + "-" + sTemp.substring(6, 8);
            
	            sTemp = replaceStr(sTemp1, "-", "")*1 + 6;
	            
	            sTemp = sTemp+"";
	            iIntTemp = sTemp.substring(4, 6)*1;   //sTemp 문자열의 가운데 월부분
	            iIntTemp2 = sTemp.substring(6, 8)*1;   //sTemp 문자열의 마지막 날자부분
	            
	            if( iIntTemp < 12 ){
	                if( iIntTemp2 > iMon_DayArr[iIntTemp - 1] ){
	                    iIntTemp2 = iIntTemp2 - iMon_DayArr[iIntTemp - 1];
	                    sTemp = sYear + sMonArr[iIntTemp] + "0" + iIntTemp2;	                    
	                }
	            }

	            sTemp2 = sTemp.substring(0, 4) + "-" + sTemp.substring(4, 6) + "-" + sTemp.substring(6, 8);

	            if( sTemp.substring(4, 6) == "12" && sTemp.substring(6, 8) > 31 ){
	            	sTemp2 = sTemp.substring(0, 4) + "-" + sTemp.substring(4, 6) + "-" + "31";
	            }
	            sScopeDay[iRow]=[sTemp1, sTemp2];
	            //목요일날 아침에 회의하므로 목요일날 주차가 다음주차로 바뀌는걸 막기위해 // 수요일 회의로 바뀜..
	            if( replaceStr(sScopeDay[iRow][0], "-", "") == sToday && now.getDay() == 3 ){ // 4->3로 바뀜
	                sData[0] = iRow;
	            	sData[1] = sScopeDay[iRow - 1][0];
	            	sData[2] = sScopeDay[iRow - 1][1];
	                //GetWeekNo = True
	                
	                break;
	            }else if( replaceStr(sScopeDay[iRow][0], "-", "") > sToday ){
	                sData[0] = iRow;
	                sData[1] = sScopeDay[iRow - 1][0];
	                sData[2] = sScopeDay[iRow - 1][1];
	                
	                //GetWeekNo = True
	                break;
	            }
	        }else{
	        	sData[0] = 53;
		    } 
	    }

	    return sData;
	}
	function GetWeekDay(sYear, sWeekNo)
	{		
	    var sTemp; 
	    var sToday; 
	    var sTemp1; 
	    var sTemp2;
	    var iIntTemp; 
	    var iIntTemp2; 
	    var now = new Date();
	    var startWeekDay;       //요일
	    var iYear; 
	    var sFebDayMax;         //2월달 날자수
	    var sMonArr = new Array();          //월
	    var iMon_DayArr = new Array();      //월별 총날자수
	    var sScopeDay = new Array();        //주차 시작일, 주차 종료일
	    var iRow; 
	    var iBasicWeek;        //주차 기준일을 설정
	    var iRowArrayNum;      //전체주수
	    //var sYear;              //현재연도
	    var sData = new Array(3);
	    
	    //GetWeekNo = False
	    //저번주 목요일부터 이번주 수요일까지 1주차
	    // 0 1  2  3  4  5  6 
	    // 일 월 화 수 목 금 토
	    //iBasicWeek = 3      //목요일날 회의하는 날 (만일 수요일날 회의날자면 2 이됨)
	    //iBasicWeek = 4      //금요일날 회의하는 날 (만일 수요일날 회의날자면 2 이됨)
	    iBasicWeek = 3;      //수요일날 회의하는 날 (만일 수요일날 회의날자면 2 이됨)
	    
	    sMonArr = Array("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
	    iMon_DayArr = Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
	    
	    //sYear = now.getFullYear(); 
	    iYear = sYear *1;
	    //var math = new Math();
	    if( iYear >= 1900 && iYear <= 2100){
	        if( iYear % 4 == 0){
	            iMon_DayArr[1] = 29;
	        }
	        if( iYear % 100 == 0 ){
	            iMon_DayArr[1] = 28;
	        }
	        if( iYear % 400 == 0 ){
	            iMon_DayArr[1] = 29;
	        }
	    }
  
	    //저번주 목요일부터 이번주 수요일까지 1주차 // 수요일부터 화요일까지 주차가 바뀜..(2005/06/01)-법무팀 2주일간회의로
	    // 0  1  2  3  4  5  6
	    // 일 월 화 수 목 금 토
	    sTemp = "1/1/" + sYear;
	    var dTemp = new Date(sTemp);
	    startWeekDay = dTemp.getDay();   //1월 1일이 무슨요일인가
	    startWeekDay = startWeekDay + 1;
	    iRowArrayNum = 54;
	    //sScopeDay[iRowArrayNum, 2];  //시작일, 종료일
	    sScopeDay = new Array(iRowArrayNum, 2);
		
	    if( startWeekDay <= iBasicWeek ){
	        sScopeDay[0] = [sYear + "-01-01", sYear + "-01-0" + (iBasicWeek - startWeekDay + 1)];
	    }else if( startWeekDay == 7 ){  //2011년
	        sScopeDay[0] = [sYear + "-01-01", sYear + "-01-04"];
	    }else{
	        sScopeDay[0] = [sYear + "-01-01", sYear + "-01-0" + Math.abs(iBasicWeek - startWeekDay - 2)] ;//심우규 수정 2005년부터 주차가 잘못되어있음..
	        //sScopeDay(0, 1) = sYear + "-01-07"
	    }
	    
	    for( iRow = 1 ;iRow < 55;iRow++){
	    	
	        if( (sScopeDay[iRow - 1][1]).substring(5, 10) != "12-31" ){
	        	
	            sTemp = replaceStr(sScopeDay[iRow - 1][1], "-", "")*1 + 1;
	            sTemp = sTemp+"";
	            iIntTemp = sTemp.substring(4, 6)*1;   //sTemp 문자열의 가운데 월부분
	            iIntTemp2 = sTemp.substring(6, 8)*1;   //sTemp 문자열의 마지막 날자부분
            
	            if( iIntTemp < 12 ){
	                if( iIntTemp2 > iMon_DayArr[iIntTemp - 1] ){
	                    sTemp = sYear + sMonArr[iIntTemp] + "01";
	                }
	            }
	           
	            sTemp1 = sTemp.substring(0, 4) + "-" + sTemp.substring(4, 6) + "-" + sTemp.substring(6, 8);

	            sTemp = replaceStr(sTemp1, "-", "")*1 + 6;
	            
	            sTemp = sTemp+"";
	            iIntTemp = sTemp.substring(4, 6)*1;   //sTemp 문자열의 가운데 월부분
	            iIntTemp2 = sTemp.substring(6, 8)*1;   //sTemp 문자열의 마지막 날자부분
	            
	            if( iIntTemp < 12 ){
	                if( iIntTemp2 > iMon_DayArr[iIntTemp - 1] ){
	                    iIntTemp2 = iIntTemp2 - iMon_DayArr[iIntTemp - 1];
	                    sTemp = sYear + sMonArr[iIntTemp] + "0" + iIntTemp2;	                    
	                }
	            }

	            sTemp2 = sTemp.substring(0, 4) + "-" + sTemp.substring(4, 6) + "-" + sTemp.substring(6, 8);
	
	            if( sTemp.substring(4, 6) == "12" && sTemp.substring(6, 8) > 31 ){
	            	sTemp2 = sTemp.substring(0, 4) + "-" + sTemp.substring(4, 6) + "-" + "31";
	            }
	            sScopeDay[iRow] = [sTemp1, sTemp2];
	            if( sWeekNo == iRow ){ 

	                sData[0] = iRow;
		            sData[1] = sScopeDay[iRow - 1][0];
		            sData[2] = sScopeDay[iRow - 1][1];	                
	                //GetWeekNo = True
	                break;
	            }
	        }else{
		        	
	                sData[0] = iRow;
	                sData[1] = sScopeDay[iRow - 1][0];
	                sData[2] = sScopeDay[iRow - 1][1];
	                
	                //GetWeekNo = True
	                break;
	        }
	    } 

	    return sData;
	}

    function initPage() {
    	var frm = document.frm;
    	frm.page_gbn.value = "M";

   		if('<c:out value='${command.weekseq}'/>' == ""){

   	    	var sData = new Array(3);
   	    	var now = new Date();

   	    	sData = GetWeekNo();

   	     	var selectValue = document.getElementById("weekseq");
   	     	if(now.getDay() == 3)
   	    		selectValue.options[sData[0]].selected = true;   	    	
   	     	else
   	     		selectValue.options[sData[0]-1].selected = true;
   	     	
   	   		sFrToday = "("+sData[1] + " ~ "+sData[2]+")";   //시작일 종료일 	
   		}	
    }	
//-->
</script>

</head>
<body onLoad="showMessage('<%=returnMessage %>');initPage()">  

<div id="wrap">
<div id="container">

	<!-- Location -->
	<div class="location"> 
	    <img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/>
	</div>
	<!-- //Location -->
	<!-- Title -->
	<div class="title">
	    <h3><spring:message code="las.page.field.board.weekSchedule.header" /></h3>
	</div> 		

	<div id="content">
	<div id="content_in">
	
		<!--**************************** Form Setting ****************************-->
		<form:form id="frm" name="frm" method="post" autocomplete="off">
		<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
		<!-- 페이지 공통 -->	
		<input type="hidden" name="method"   value="">
		<input type="hidden" name="doSearch" value="<c:out value='${command.doSearch}'/>">
		<input type="hidden" name="transfer" value="<c:out value='${command.transfer}'/>">
		<input type="hidden" name="tabs" value="TN_LAS_ETC_WEEKDUTY">
		<!-- 이중등록 방지 -->
		<input type="hidden" name="TOKEN"  value="<%=Token.set(request) %>">
	
		<!-- 나모 웹 에디터 관련 -->
		<input type="hidden" name="body_mime" id="body_mime" value="<c:out value='${lom.crntweek_rslt}' />" />
		<input type="hidden" name="body_mime1" id="body_mime1" value="<c:out value='${lom.nextweek_plan}' />" />
		<input type="hidden" name="crntweek_rslt" id="crntweek_rslt" value="<c:out value='${lom.crntweek_rslt}' />" />
		<input type="hidden" name="nextweek_plan" id="nextweek_plan" value="<c:out value='${lom.nextweek_plan}' />" />
		
		<!-- 주단위 날짜 관련 -->
	    <input type="hidden" name="weekFirstDay" value="<c:out value='${command.weekFirstDay}'/>" />
	    <input type="hidden" name="weekLastDay"	value="<c:out value='${command.weekLastDay}'/>" />
	    
	    <input type="hidden" name="seqno"	value="<c:out value='${lom.seqno}'/>" />  
	    <input type="hidden" name="seqnos" value="<c:out value='${lom.seqno}'/>">  
	    <input type="hidden" name="page_gbn" value="<c:out value='${command.page_gbn}'/>">
	    <input type="hidden" name="dmstfrgn_gbn" value="<c:out value='${command.dmstfrgn_gbn}'/>">
		<!-- //	**************************** Form Setting ****************************-->
		
		<!-- tab01 -->
		<ul class="tab_basic">
			<li><a href="javascript:moveTab('1');"><spring:message code="las.page.field.statistics.contract" /></a></li>
			<li><a href="javascript:moveTab('2');"><spring:message code="las.page.field.statistics.consult" /></a></li>
			<li class="on"><a href="javascript:moveTab('4');"><spring:message code="las.page.field.statistics.etc" /></a></li>				
		</ul>
		<!-- 두줄라인 추가 --><div class="tal_topLine"></div><!-- //두줄라인 추가 -->
<%
	int iYear = cal.getYear()+2000-100;
	int iMonth = cal.getMonth()+1;
	int iDate = cal.getDate();
	String sDate = iYear+""+((iMonth<10)?"0"+iMonth:iMonth)+""+((iDate<10)?"0"+iDate:iDate);
	if((statisticsWeekForm.getYear() != null && Integer.parseInt(sDate) <= Integer.parseInt(statisticsWeekForm.getWeekLastDay().replaceAll("-","")) 
			&& iYear == Integer.parseInt(statisticsWeekForm.getYear()))
				|| statisticsWeekForm.getWeekseq() == null){

%>		
		<br />    
		<div class="t_titBtn">
			<div class="btn_wrap_t fR">
				<span class="btn_all_w"><span class="edit"></span><a href="javascript:enableView();"><spring:message code="las.page.button.modify" /></a></span>
			</div>	
    	</div>
<%		
	}else{
%>
		<BR></BR>
<%		
	}
%>     
		<!-- //Button -->
   		<div class="search_box">
			<div class="search_box_inner">
				<table class="search_tb">
						<colgroup>
						<col/>
						</colgroup>
						<tr>   
						<td>
						<table class="search_form">   
							<colgroup>
								<col width="10%"/>
								<col width="*%"/>
							</colgroup>
							<tr>						                                                                                                                                                                                                          
	    	        		<th><spring:message code='las.page.field.contract.library.reg_dt2' /></th>
							<td>
			    			<select name="year" id="year" class="select" onchange="javascript:moveTab('4')">
			    			<%
			    			iYear = cal.getYear()+2000-100;
			    			for(int i=0;i < 6 ; i++){	
			    				int iYear1 = iYear-i;
			    			%>
							<c:choose>
								<c:when test="${command.year==''}">
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
									if(statisticsWeekForm.getYear() != null && statisticsWeekForm.getYear().equals(Integer.toString(iYear1))){										
										sYear = iYear1+"";
										out.println("<option value='"+iYear1+"' selected>"+iYear1+"</option>");
									}else if(statisticsWeekForm.getYear() == null){
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
			    			</select> <spring:message code="las.page.field.statistics.year"/>&nbsp;
			    			<select name="weekseq" id="weekseq" class="select" onchange="javascript:moveTab('4')">		
   			
			    			<%
			    			for(int i=1;i < 54 ; i++){		
			    			%>
							<c:choose>
								<c:when test="${command.weekseq ==null}">

								<%
								if(statisticsWeekForm.getWeekseq() != null && statisticsWeekForm.getWeekseq().equals(Integer.toString(i))){
									out.println("<option value='"+i+"' selected>"+i+"</option>");
								}else{
									out.println("<option value='"+i+"'>"+i+"</option>");
								}
								 %>
								</c:when>
								<c:otherwise>
								<%
								if(statisticsWeekForm.getWeekseq() != null && statisticsWeekForm.getWeekseq().equals(Integer.toString(i))){
									out.println("<option value='"+i+"' selected>"+i+"</option>");
								}else{
									out.println("<option value='"+i+"'>"+i+"</option>");
								}
								 %>
								</c:otherwise>
							</c:choose>			    			
			    			
			    			<%} %>     			
			    			</select> <spring:message code='las.page.field.board.weekSchedule.week' /> 	&nbsp;  
			    			(<c:out value='${command.weekFirstDay }' /> ~ <c:out value='${command.weekLastDay }' />)			    			 	        		
							</td>
							
							</tr>
						</table>
						</td> 
						</tr>
				</table>
			</div>
		</div>

		<!-- //Search -->
		<!-- //Clear -->

		<!-- List -->
		<table id="id_crntweek_rslt" border="0" cellspacing="0" cellpadding="0" class="list_basic mt20">
			<colgroup>
			<col width="50%" align="center"/>
			<col width="50%" align="center"/>
			</colgroup>
			<thead>
			<tr>
				<th><spring:message code="las.page.field.statistics.weekperform" /></th>
				<th><spring:message code="las.page.field.statistics.weekplan" /></th>
			</tr>
			</thead>
			  <tbody>
				<tr>
					<td class="tL">
						<div class="tal_txt_style">
						<%=(lom != null && lom.size() > 0)?lom.get("crntweek_rslt"):"&nbsp;" %>	
						</div>
					</td>
					<td class="tL">
						<div class="tal_txt_style">
						<%=(lom != null && lom.size() > 0)?lom.get("nextweek_plan"):"&nbsp;" %>	
						</div>
					</td>					
				</tr>				
		  </tbody>
		 </table>
		 
		<table id="namo1" style="display:none;" border="0" cellspacing="0" cellpadding="0" class="list_basic mt20">
			<colgroup>
			<col width="50%" align="center"/>
			<col width="50%" align="center"/>
			</colgroup>
			<thead>
			<tr>
				<th><spring:message code="las.page.field.statistics.weekperform" /></th>
				<th><spring:message code="las.page.field.statistics.weekplan" /></th>
			</tr>
			</thead>			
			  <tbody>
				<tr>
					<td class="tL">
					<%@ include file="/WEB-INF/jsp/secfw/common/NamoEditInclude.jspf"%>
					</td>
					<td class="tL">
					<%@ include file="/WEB-INF/jsp/secfw/common/NamoEditInclude.jspf"%>
					</td>					
				</tr> 	
		  </tbody>
		 </table>		
			<!-- //view  -->
			<!--  Function Button  -->
<% if(lom != null && lom.size() > 0){ %>			
			<div class="btn_wrap" id="btn1" style="display:none;">
				<span class="btn_all_b"><span class="save"></span><a href="javascript:modifyView()"><spring:message code="las.page.button.save"/></a></span>
			</div>
			<div class="btn_wrap" id="btn2" style="display:none;">
			
			</div>			
<% }else{ %>
			<div class="btn_wrap" id="btn1" style="display:none;">
			
			</div>
			<div class="btn_wrap" id="btn2" style="display:none;">
				<span class="btn_all_b"><span class="save"></span><a href="javascript:insertView()"><spring:message code="las.page.button.save"/></a></span>
			</div>
<% }%>
			<!-- //  Function Button  -->	

	</form:form>
	</div>
	</div>
	
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</div>
</div>


<!-- footer -->
<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script> 
<!-- // footer -->
</body>
<!-- 나모 웹에디터 관련 추가 -->
<SCRIPT language="javascript" FOR="wec" EVENT="OnInitCompleted()">
	var wecObj0 = document.wec[0];
	wecObj0.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
	wecObj0.SetDefaultFontSize("9");
	wecObj0.EditMode = 1;
	wecObj0.Value = document.frm.crntweek_rslt.value; //namo 에 값 셋팅
	
	var wecObj1 = document.wec[1];
	wecObj1.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
	wecObj1.SetDefaultFontSize("9");
	wecObj1.EditMode = 1;
	wecObj1.Value = document.frm.nextweek_plan.value; //namo 에 값 셋팅
</SCRIPT>
</html>