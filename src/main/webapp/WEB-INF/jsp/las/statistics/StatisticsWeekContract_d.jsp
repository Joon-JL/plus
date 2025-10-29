<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.Token" %>
<%@ page import="com.sec.las.statistics.dto.StatisticsWeekForm" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %> 
<%--
/**
 * 파     일     명 : StatisticsWeekContract_d.jsp
 * 프로그램명 : 주간업무(계약) 기본 조회
 * 설               명 : 
 * 작     성     자 : 김재원
 * 작     성     일 : 2011.09
 * 수     정     자 : 서백진
 * 수     정     일 : 2011.10
 */
--%>
<%
	//메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	ArrayList resultList = (ArrayList)request.getAttribute("resultList");
	Date cal= new Date();
	//ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
	StatisticsWeekForm statisticsWeekForm = (StatisticsWeekForm)request.getAttribute("command") ;

	//처리결과 메시지
	String returnMessage = (String)request.getAttribute("returnMessage");	
	String sYear ="";
	String locale = statisticsWeekForm.getDmstfrgn_gbn();			
%>

<html>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<title><spring:message code="las.page.field.board.weekSchedule.header" /></title>

<LINK href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet">  
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value="/script/clms/jquery-1.6.1.min.js"/> type="text/javascript"></script>
<script language="javascript" src="<c:url value="/script/clms/new_style.js"/>ss type="text/javascript"></script>
<script language="javascript" src="<c:url value='/util/fckeditor/fckeditor.js' />"></script>
<script language="javascript">
<!--

	$(function() {

		$(document).keydown(function(event){
	
			if(event.keyCode == "13") {
				var target = event.target;
				if(target.id == "srch_cntnt") {
					//listLawSuitBasic("Y","1");
				} else {
					//return false;
				}
			}
		});
	});
	/**
	* 수정
	*/
	function enableView1(){
		var i = 0;
		result_yn = document.all("result_yn").value;
		obj = document.getElementsByName("seqnos1");
		obj1 = document.getElementsByName("weekdutyyns");
		//while(obj[i] && result_yn=="P"){
		while(obj[i]){
			obj[i].style.display = "";
			if(obj1[i].value == 'Y'){
				obj[i].checked = true;
			}
			i++;
		}
		//if(result_yn=="P"){
			document.all("btn1").style.display = "";
		//}		
	}	
	function enableView(){
		var frm = document.frm;
		frm.target = "_self";
		frm.action = "<c:url value='/las/statistics/statisticsWeek.do' />";
		frm.method.value = "forwardStatisticsWeek";
		frm.transfer.value = "contract";
		//frm.page_gbn.value = "C";
		frm.display_yn.value = "Y";
		frm.submit();				
	}
	function chkSeqnos(idx){
		obj = document.getElementsByName("weekdutyyns");
		obj1 = document.getElementsByName("seqnos");
		if(obj1[idx].checked){
			obj[idx].value = "Y";
		}else{
			obj[idx].value = "N";
		}
	}	
	/**
	* 수정
	*/
	function modifyView(){
		var frm = document.frm;
		frm.target = "_self";
		frm.action = "<c:url value='/las/statistics/statisticsWeek.do' />";
		frm.method.value = "modifyStatisticsWeek";
		frm.page_gbn.value = "C";
		if(confirm("<spring:message code='secfw.msg.ask.update' />")){
			frm.submit();
		}
	}	

	/**
	* Tab 이동
	*/
	
	function moveTab(flag){
		var frm = document.frm;
		if(validateForm(document.frm) == false) {  
			return;
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
    	frm.page_gbn.value = "C";

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
    	if("<c:out value='${command.display_yn}'/>" == "Y" ){
    		enableView1();
    	}   		
    }	
	
	function showMessage(msg){
		if(msg != null && msg.length > 1) {
			alert(msg);
		}
	}
	
//-->
</script>

</head>
<body onLoad="showMessage('<%=returnMessage %>');initPage();">  
	
<div id="wrap">
    <div id="container">	
	<div class="location"><c:out value='${menuNavi}' escapeXml="false"/></div>

		<!-- Title -->
	<div class="title">
	<h3><spring:message code="las.page.field.board.weekSchedule.header" /></h3>
	</div> 		
	
	<div id="content">
	<div id="content_in">
		<form:form id="frm" name="frm" method="post" autocomplete="off">
		<!--**************************** Form Setting ****************************-->
		<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
		<!-- 페이지 공통 -->	
		<input type="hidden" name="method"   value="">
		<input type="hidden" name="doSearch" value="<c:out value='${command.doSearch}'/>">
		<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>">	
		<input type="hidden" name="transfer" value="<c:out value='${command.transfer}'/>">
		<input type="hidden" name="page_gbn" value="<c:out value='${command.page_gbn}'/>">
		<input type="hidden" name="display_yn" value="">
		<!-- 이중등록 방지 -->
		<input type="hidden" name="TOKEN"  value="<%=Token.set(request) %>">
		
		<input type="hidden" name="weekFirstDay" value="<c:out value='${command.weekFirstDay}'/>" />
		<input type="hidden" name="weekLastDay"	value="<c:out value='${command.weekLastDay}'/>" />	
		<input type="hidden" name="dmstfrgn_gbn" value="<c:out value='${command.dmstfrgn_gbn}'/>">
		<!-- //*************************** Form Setting ****************************-->

		<div class="newstyle-area">
		<!-- tab01 -->
		<ul class="tab_basic">
			<li class="on"><a href="#"><spring:message code="las.page.field.statistics.contract" /></a></li>
			<li><a href="javascript:moveTab('2');"><spring:message code="las.page.field.statistics.consult" /></a></li>
			<li><a href="javascript:moveTab('4');"><spring:message code="las.page.field.statistics.etc" /></a></li>				
		</ul>
		<!-- 두줄라인 추가 --><div class="tal_topLine"></div><!-- //두줄라인 추가 -->
		<br>
		<div class="t_titBtn">
			<div class="Sel_aw fL">
	  			<select name="result_yn" id="result_yn" onchange="javascript:moveTab('1')" style="width:100px;">
	  			<option value='R' <c:if test="${command.result_yn=='R'}">selected</c:if>><spring:message code="las.page.field.statistics.record"/></option>
	  			<option value='P' <c:if test="${command.result_yn=='P'}">selected</c:if>><spring:message code="las.page.field.statistics.plan"/></option>	    			
	  			</select>			
			</div>
								
<%
	int iYear = cal.getYear()+2000-100;
	int iMonth = cal.getMonth()+1;
	int iDate = cal.getDate();
	String sDate = iYear+""+((iMonth<10)?"0"+iMonth:iMonth)+""+((iDate<10)?"0"+iDate:iDate);
	if((statisticsWeekForm.getYear() != null && Integer.parseInt(sDate)  <= Integer.parseInt(statisticsWeekForm.getWeekLastDay().replaceAll("-","")) 
			&& iYear == Integer.parseInt(statisticsWeekForm.getYear()))
				|| statisticsWeekForm.getWeekseq() == null){

%>		    

<!--  			* 수정 로직의 목적이 불분명 - 임시적으로 불필요로 판단				 -->
<!-- 		<div class="btn_wrap_t fR"> -->
<%-- 			<span class="btn_all_w"><span class="edit"></span><a href="javascript:enableView();"><spring:message code="las.page.button.modify" /></a></span> --%>
<!-- 		</div>	 -->
    	
<%		
	}	
%> 
    	</div>
		<!-- //Button -->
		<!--90% 안에서 작업하기 search-->
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
								<col width="15%"/>
								<col width="*%"/>
							</colgroup>
							<tr>						                                                                                                                                                                                                          
	    	        		<th><spring:message code='las.page.field.contract.library.reg_dt2' /></th>
							<td>
			    			<select name="year" id="year" class="select" onchange="javascript:moveTab('1')">
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
			    			<select name="weekseq" id="weekseq" class="select" onchange="javascript:moveTab('1')">		
   			
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
			    			</select> <spring:message code='las.page.field.board.weekSchedule.week' />&nbsp;   			
			    			(<c:out value='${command.weekFirstDay }' /> ~ <c:out value='${command.weekLastDay }' />)	    			 	        		
							</td>
							
							</tr>
						</table>
						</td> 
						</tr>
				</table>
			</div>
		</div>
		<!--//90% 안에서 작업하기 search-->

		<!-- //Search -->
		<!-- //Clear -->
<!-- 		<br> -->
<!-- 		<tr> -->
<!-- 		<td> -->
<!-- 		<table width="60%" align="left"> -->
<!-- 		<tr><td> -->
<%-- 		<% --%>
<!-- // 		if(locale.equals("F")){ -->
<%-- 		%> --%>
<%-- 			<b>[<spring:message code="las.page.tab.lawsuit.frgn" />]</b> --%>
<%-- 		<% --%>
<!-- // 		}else{ -->
<%-- 		%> --%>
<%-- 			<b>[<spring:message code="las.page.tab.lawsuit.dmst" />]</b> --%>
<%-- 		<% --%>
<!-- // 		} -->
<%-- 		%>			 --%>
<!-- 		</td></tr> -->
<!-- 		</table> -->
<!-- 		</td> -->
<!-- 		</tr>	 -->
		<!-- List -->
<%
			if(resultList!=null){
				for(int idx=0;idx < resultList.size();idx++){
			    
			   		ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);	
%>			   
					<table border="0" cellspacing="0" cellpadding="0" class="table-style01 mt20">
						<colgroup>
							<col width="15%" />
							<col width="35%" /> <!-- width size 변경 신성우 2014-04-24 -->
							<col width="15%" />
							<col width="35%" />
						</colgroup>
						<tbody>
						<tr>
							<th><spring:message code="las.page.field.common.req_title" /></th>
							<td colspan="3"><%=(lom != null && lom.size() > 0)?StringUtil.convertHtmlTochars((String)lom.get("req_title")):"" %></td>
						</tr>
						<tr>
							<th><spring:message code="las.page.field.common.contract_title" /></th>
							<td colspan="2"><%=(lom != null && lom.size() > 0)?lom.get("title"):"" %></td>
							<td style="border-left:0"><input type="checkbox" style="display:none;" id='seqnos1' name='seqnos1' value='<%=lom.get("seqno") %>' onClick="javascript:chkSeqnos(<%=idx %>)"></td>
							<input type="hidden" id='seqnos' name='seqnos' value='<%=lom.get("seqno") %>'>
							<input type="hidden" id='tabs' name='tabs' value='<%=lom.get("tab") %>'>
							<input type="hidden" id='weekdutyyns' name='weekdutyyns' value='<%=lom.get("weekdutyyns") %>'>				
							
						</tr>
						<tr>
							<th><spring:message code="las.page.field.mainproject.oppnt_comp" /></th>
							<td><%=(lom != null && lom.size() > 0)?lom.get("company"):"" %></td>
							<th><spring:message code="las.page.field.statistics.req_dept" /></th>
							<td><%=(lom != null && lom.size() > 0)?lom.get("dept_nm"):"" %></td>				
						</tr>
						<tr>
							<td align="left" colspan="4">
							 
			    			<div class="text_area" style="height:170px; overflow:auto;">
			    			<!-- <textarea name="conts" readonly="readonly" rows="14" cols="170" class="text_area_full" ><%=(lom != null && lom.size() > 0)?lom.get("cont"):"" %></textarea> -->
			       			<%=(lom != null && lom.size() > 0)?lom.get("cont"):"" %>
			       			
			       			</div><br /> 	
			       			</td>	
						       							
						</tr> 			
						</tbody>			
					</table>		
<%
			}
		}
%>	
	<!-- //view  -->
			<!--  Function Button  -->
			<!-- 
<% if(resultList != null && resultList.size() > 0){ %>			
			<div class="btn_wrap" id="btn1" style="display:none;">
				<span class="btn_all_b"><span class="save"></span><a href="javascript:modifyView()"><spring:message code="las.page.button.save"/></a></span>
			</div>
			<div class="btn_wrap" id="btn2" style="display:none;">
			
			</div>			
<% }else{ %>
		<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
			<colgroup>
				<col width="15%" />
				<col width="35%" /> <!-- width size 변경 신성우 2014-04-24 -->
				<col width="15%" />
				<col width="35%" />
			</colgroup>
			<tbody>
			<tr>
				<th><spring:message code="las.page.field.common.title" /></th>
				<td colspan="2">&nbsp;</td>
				<td style="border-left:0" >
					<div id="seqnos" style="display:none;"></div>				
				</td>
				<input type="hidden" id='tabs' name='tabs' value=''>
				<input type="hidden" id='weekdutyyns' name='weekdutyyns' value='Y'>
			</tr>
			<tr>
				<th><spring:message code="las.page.field.mainproject.oppnt_comp" /></th>
				<td>&nbsp;</td>
				<th><spring:message code="las.page.field.statistics.req_dept" /></th>
				<td>&nbsp;</td>				
			</tr>
			<tr>
				<td align="left" colspan="4">
				 
    			<div class="text_area" style="height:170px;">
    			<textarea name="conts" readonly rows="14" cols="170" class="text_area_full" ></textarea>
       			
       			</div><br /> 	
       			</td>	
			       							
			</tr> 			
			</tbody>			
		</table>

<% }%> -->
			<!-- //  Function Button  -->				
	
	</div>
	</form:form>
</div>
<% if(resultList != null && resultList.size() == 0){ %>	
		<div class="t_titBtn" align="center">
			<span class="tC" ><spring:message code="las.msg.succ.noResult" /></span>
		</div>
		
		</div>				
	
		<div class="btn_wrap" id="btn1" style="display:none;"></div>
		<div class="btn_wrap" id="btn2" style="display:none;"></div>	
<% 
	}else{
%>
		</div>
<%
	} 
%>
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</div>	
</div>
<!-- footer  -->
<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
<!-- // footer -->

</body>
</html>