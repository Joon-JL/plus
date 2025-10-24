<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.sds.secframework.common.util.Token" %>
<%@ page import="com.sec.las.statistics.dto.StatisticsWeekForm" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.HashMap" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>   
<%--
/**
 * 파     일     명 : StatisticsWeek_l.jsp
 * 프로그램명 	  : 주간업무 전체 조회
 * 설            명 : 
 * 작     성     자 : 김재원
 * 작     성     일 : 
 * 수     정     자 : 황민우
 * 수     정     일 : 2013.06
 */
--%>
<%
	//메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	ArrayList respmanList = (ArrayList)request.getAttribute("respmanList");
	ArrayList resultList = (ArrayList)request.getAttribute("resultList");
	ArrayList resultList1 = (ArrayList)request.getAttribute("resultList1");
	ArrayList resultList2 = (ArrayList)request.getAttribute("resultList2");
	ArrayList resultList3 = (ArrayList)request.getAttribute("resultList3");
	ArrayList resultList4 = (ArrayList)request.getAttribute("resultList4");
	ArrayList resultList5 = (ArrayList)request.getAttribute("resultList5");
	//처리결과 메시지
	Date cal= new Date();
	String returnMessage = (String)request.getAttribute("returnMessage");	
	StatisticsWeekForm statisticsWeekForm = (StatisticsWeekForm)request.getAttribute("command") ;
	HttpSession hs = request.getSession(false);
	ArrayList roleList = (ArrayList)hs.getAttribute("secfw.session.role"); //session 사용할 경우
	ArrayList userRoleList = new ArrayList(); //role_cd만 꺼내기 위한 arrayList
	String locale = statisticsWeekForm.getDmstfrgn_gbn();	
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
	    if(userRoleList.contains("RM00") || userRoleList.contains("RA00") || userRoleList.contains("RA01")){
	        accessLevel = "A";
	    }else if(userRoleList.contains("RD01") || userRoleList.contains("RD02")){
	        accessLevel = "D";
	    }else if(userRoleList.contains("RC01")){
	        accessLevel = "B";
	    }else{
	        accessLevel = "C";
	    }
	}	
	/*
	String locale = (String)hs.getAttribute("secfw.session.blngt_orgnz");
	if(locale != null && locale.equals("A00000001")){ // 국내
		locale = "H";
	}else if(locale != null && locale.equals("A00000002")){ // 해외
		locale = "F";
	}else{
		locale = "";
	}	
	*/
	String sYear ="";
	int idx1 = 0;
%>

<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="las.page.title.statistics.StatisticsMonthList" /></title>

<LINK href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet">  
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value="/script/clms/jquery-1.6.2.min.js"/> type="text/javascript"></script>
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
					return false;
				}
			}
		});
	});
	/**
	* 수정
	*/
	function enableView1(){
		var i = 0;
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
		frm.method.value = "listStatisticsWeek";
		if(frm.reg_id.value == "")
			frm.reg_id.value = "ALL";
		
		frm.display_conf_yn.value = "Y";
		frm.submit();	
	}
		
	function chkSeqnos(idx){
		obj = document.getElementsByName("weekdutyyns");
		obj1 = document.getElementsByName("seqnos1");
		obj2 = document.getElementsByName("tabs");
		obj3 = document.getElementsByName("seqnos");
		if(obj1[idx].checked){
			obj[idx].value = "Y";
		}else{
			obj[idx].value = "N";
		}
		chkSeqnos1(obj2[idx].value, obj3[idx].value, obj[idx].value); // 담당자가 여러명일 경우 체크
	}
	function chkSeqnos1(tab, seqno, monthdutyyn){
		var i = 0;
		obj = document.getElementsByName("seqnos1");
		obj1 = document.getElementsByName("weekdutyyns");
		obj2 = document.getElementsByName("tabs");
		obj3 = document.getElementsByName("seqnos");
		while(obj[i]){

			if(obj3[i].value == seqno &&  obj2[i].value == tab){
				if(monthdutyyn == 'Y'){
					obj[i].checked = true;
				}else{
					obj[i].checked = false;
				}
				obj1[i].value = monthdutyyn;
			}
			i++;
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
		frm.display_conf_yn.value = "N";
		frm.page_gbn.value = "A";
		frm.transfer.value = "A";
		
		if(confirm("<spring:message code='secfw.msg.ask.update' />")){
			frm.submit();
		}
	}	
	/**
	* 상세
	*/
	function detailView(regid){
		var frm = document.frm;
	    //frm.seqno.value = seqno;
		frm.target = "_self";
		frm.action = "<c:url value='/las/statistics/statisticsWeek.do' />";
		frm.method.value = "listStatisticsWeek";
		frm.display_conf_yn.value = "";
		frm.reg_id.value = regid;
		frm.submit();
	}	
	function searchSta(){
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
		frm.method.value = "listStatisticsWeek";
		if("<c:out value='${command.reg_id}'/>" == "")
			frm.reg_id.value = "ALL";
		
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
    	if("<c:out value='${command.display_conf_yn}'/>" == "Y" ){
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

	<div class="location">
		<img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/>		
		<c:out value='${menuNavi}' escapeXml="false"/>
	</div>
		<!-- Title -->
	<div class="title">
		<h3><spring:message code="las.page.title.statistics.StatisticsWeekhList" /></h3>
	</div>
	 		
	<div id="content">
	<div id="content_in">
		<!-- **************************** Form Setting **************************** -->
		<form:form id="frm" name="frm" method="post" autocomplete="off">
		<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
		<!-- 페이지 공통 -->	
		<input type="hidden" name="method"   value=""/>
		<input type="hidden" name="doSearch" value="<c:out value='${command.doSearch}'/>"/>
		<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>"/>
		
		<input type="hidden" name="transfer" value="<c:out value='${command.transfer}'/>"/>
		<input type="hidden" name="page_gbn" value="<c:out value='${command.page_gbn}'/>"/>
		<input type="hidden" name="display_yn" value="<c:out value='${command.display_yn}'/>"/>
		<input type="hidden" name="display_conf_yn" value="<c:out value='${command.display_conf_yn}'/>"/>
		<!-- 이중등록 방지 -->
		<input type="hidden" name="TOKEN"  value="<%=Token.set(request) %>"/>
		<input type="hidden" name="reg_id"   value="<c:out value='${command.reg_id}'/>"/>
		
		<!-- 주단위 날짜 관련 -->
	    <input type="hidden" name="weekFirstDay" value="<c:out value='${command.weekFirstDay}'/>" />
	    <input type="hidden" name="weekLastDay"	value="<c:out value='${command.weekLastDay}'/>" />
		<!-- //**************************** Form Setting **************************** -->
		
	
		
		<div class="btnwrap">
<!-- 			<div class="Sel_aw fL"> -->
<!-- 	  			<select name="dmstfrgn_gbn" id="dmstfrgn_gbn" onchange="javascript:searchSta()" style="width:100px;"> -->
<%-- 	  			<option value='F' <c:if test="${command.dmstfrgn_gbn=='F'}">selected</c:if>><spring:message code="las.page.tab.lawsuit.frgn" /></option> --%>
<%-- 	  			<option value='H' <c:if test="${command.dmstfrgn_gbn=='H'}">selected</c:if>><spring:message code="las.page.tab.lawsuit.dmst" /></option>	    			 --%>
<!-- 	  			</select>			 -->
<!-- 			</div> -->

<%
			int iYear = cal.getYear()+2000-100;
			int iMonth = cal.getMonth()+1;
			int iDate = cal.getDate();
			String sDate = iYear+""+((iMonth<10)?"0"+iMonth:iMonth)+""+((iDate<10)?"0"+iDate:iDate);
			
			if((statisticsWeekForm.getYear() != null && Integer.parseInt(sDate) <= Integer.parseInt(statisticsWeekForm.getWeekLastDay().replaceAll("-","")) 
					&& iYear == Integer.parseInt(statisticsWeekForm.getYear()))
						|| statisticsWeekForm.getWeekseq() == null){
	
				if(accessLevel.equals("A")){
%>		    
					
					<div class="tR">
						<span class="btn_all_w"><span class="edit"></span><a href="javascript:enableView();"><spring:message code="las.page.button.modify" /></a></span>
					</div>	
<%				
				}
			}
%>    	
		</div>

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
			    			<select name="year" id="year" class="select" onchange="javascript:searchSta()">
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
			    			<select name="weekseq" id="weekseq" class="select" onchange="javascript:searchSta()">		
   			
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
			    			</select> <spring:message code='las.page.field.board.weekSchedule.week' />&nbsp;(<c:out value='${command.weekFirstDay }' /> ~ <c:out value='${command.weekLastDay }' />)			    			 	        		
							</td>
							
							</tr>
						</table>
						</td> 
						</tr>
				</table>
			</div>
		</div>
		<!-- //Search -->
		
		<%

		if(respmanList.size() >0 && accessLevel.equals("A")){ %>	
<!-- 
		<div class="t_titBtn">
			<span class="gray_t_arw"><spring:message code="las.page.field.speakconsult.respman_nm" /></span>
		</div>
 -->		
		
		<div class='title_basic'>
			<h4><spring:message code="las.page.field.speakconsult.respman_nm" /></h4>
		</div>
		
		
		<table width="100%" align="center" >
			<tr>
				<td>
					<table class="list_basic" >
					  <colgroup>	
					
					    <col width="25%"/>
					    <col width="25%"/>	
					    <col width="25%"/>
					    <col width="25%"/>		    		    	 		
				      </colgroup>
					  <thead>
					  <%
					  if(statisticsWeekForm.getReg_id() == null || statisticsWeekForm.getReg_id().equals("ALL")){
					  %>
						<tr>
							<th  colspan="4"><nobr><b><a href="javascript:detailView('ALL');"><spring:message code="las.page.field.common.total" /></a></b></nobr></th>
						</tr>
					  <%
					  }else{%>	
						<tr>
							<th  colspan="4"><nobr><a href="javascript:detailView('ALL');"><spring:message code="las.page.field.common.total" /></a></nobr></th>
						</tr>		  	
			<%
					  }
						//String trStart 	= "<tr>";
						//String trEnd 	= "</tr>";
						String respman 	= "";			
						String html 	= "";
						String chk 		= "y";
						String chklast 	= "";
						String lastid 	= "";
						String lastnm 	= "";
						String sBold 	= "";
						String eBold 	= "";
						int chkidx 		= 0;
						for(int idx=0;idx < respmanList.size();idx++){
			
							ListOrderedMap lom = (ListOrderedMap)respmanList.get(idx);
							if(statisticsWeekForm.getReg_id() != null && statisticsWeekForm.getReg_id().equals(lom.get("respman_id"))){
								sBold = "<b>";
								eBold = "</b>";
							}else{
								sBold = "";
								eBold = "";					
							}
							respman = "<td class='tC'>"
									+ sBold
									+ "<nobr><a href=javascript:detailView('" + lom.get("respman_id") + "');>"+ lom.get("respman_nm") + "</a>"
									+ eBold
									+ "</nobr></td>";
							chkidx++;
							if(chk == "y"){
								html 	= html + respman;
								chk 	= "n";
								chklast = "n";
								lastid 	= lom.get("respman_id")+"";
								lastnm 	= lom.get("respman_nm")+"";
							}else{
								chk 	= "y";
								html 	= html + respman;
								
								if(chkidx == 4){
									chklast = "y";
									html = "<tr>" + html + "</tr>";
									out.println(html);
									html = "";
								}
							}	
							if(chkidx == 4)
								chkidx = 0;
						}
						
						if(chklast == "n"){
							html = "<tr>" 
								  + html
								  + "<td colspan='"+(4-chkidx)+"'  class='tC'></td>" 
								  + "</tr>";
					
							out.println(html);
						}
			%>		
						</thead>
						</table>
		</td>
		</tr>	
		</table>
		
	  
	
		
		
	<%	}else if(accessLevel.equals("A")) {
			String html = "<tr id='resultNotFoundRow'><td colspan=2 align=center><spring:message code='secfw.msg.succ.noResult' /></td></tr>";
			out.println(html);
		}
%>		
		<!-- List--> 
		<table border="0" cellspacing="0" cellpadding="0" class="tal_las01 mt20">
			<colgroup>
				<c:choose>
				<c:when test="${langCd=='fr'}">
					<col width="14%"/>	
					<col width="86%"/>
				</c:when>
				<c:otherwise>
					<col width="11%"/>	
					<col width="89%"/>
				</c:otherwise>
			</c:choose>
		  	<thead>
				<tr>
					<th><spring:message code="las.page.field.statistics.schedule" /></th>
				  	<th><spring:message code="las.page.field.statistics.perform" /></th>
				</tr>
		  	</thead>
		  	<tbody>
<%		
			if(resultList.size() >0){ 
%>				  
				<tr>
				<td colspan="2" style='padding:4px 10px;' class='tL'>
					<span class='gray_t_arw2'><spring:message code="las.page.field.statistics.contract" /></span>
				</td>
				</tr>					  
<%		
			}
			// 계약 실적
			for(int idx=0;idx < resultList.size();idx++){
			    
			   ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);			    	
%>									
					<tr>
						<td class="cor_th01 topLz01">
						<%=((String)lom.get("reg_dt")).substring(0,4)+"-"+((String)lom.get("reg_dt")).substring(4,6)+"-"+((String)lom.get("reg_dt")).substring(6,8) %>
						<input type="checkbox" style="display:none;" id='seqnos1' name='seqnos1' value='<%=lom.get("seqno") %>' onClick="javascript:chkSeqnos(<%=idx1 %>)"/>
					    <input type="hidden" id='seqnos' name='seqnos' value='<%=lom.get("seqno") %>'/>
						<input type="hidden" id='tabs' name='tabs' value='<%=lom.get("tab")%>@R'/>
						<input type="hidden" id='weekdutyyns' name='weekdutyyns' value='<%=lom.get("conf_weekdutyyn") %>'/>								
						</td>
						<td class="tL topLz01">
							<!-- 테이블안에 테이블01 -->
							<div class="">
							<table border="0" cellspacing="0" cellpadding="0" class="tal_las01_sub">
								<colgroup>
									<col width="15%" />
									<col width="35%" /> <!-- width size 변경 신성우 2014-04-24 -->
									<col width="15%" />
									<col width="35%" />
								</colgroup>
								<tbody>
								<tr>
									<th><spring:message code="las.page.field.common.title" /></th>
									<td colspan="3"><%=(lom != null && lom.size() > 0)?lom.get("title"):"" %></td>								
								</tr>
								<tr>
									<th><spring:message code="las.page.field.mainproject.oppnt_comp" /></th>
									<td><%=(lom != null && lom.size() > 0)?lom.get("company"):"" %></td>
									<th><spring:message code="las.page.field.statistics.req_dept" /></th>
									<td><%=(lom != null && lom.size() > 0)?lom.get("dept_nm"):"" %></td>				
								</tr>
								<tr>
									<th><spring:message code="las.page.field.speakconsult.respman_nm" /></th>
									<td colspan="3"><%=(lom != null && lom.size() > 0)?lom.get("respman_nm"):"" %></td>
								</tr>								
								<tr>
									<td align="left" colspan="4">
									 
					    			<div class="text_area" style="height:100px; overflow:auto;">
					    			<!-- <textarea name="conts" rows="14" cols="170" readonly class="text_area_full" ><%=(lom != null && lom.size() > 0)?lom.get("cont"):"" %></textarea> -->
					       			<%=(lom != null && lom.size() > 0)?lom.get("cont"):"" %>
					       			</div>
					       			</td>	
								       							
								</tr> 			
								</tbody>			
							</table>
							</div>
							<!-- 테이블안에 테이블01 -->
						</td>
						
					</tr>
	<%
			idx1++;
			}
			

			if(resultList2.size() > 0){
	%>	
				  <tr>
				  <td colspan="2" style='padding:4px 10px;' class='tL'>
						<span class="gray_t_arw2"><spring:message code="las.page.field.statistics.consult" /></span>
				  </td>
				  </tr>		
	<%		}
			// 자문 실적
			for(int idx=0;idx < resultList2.size();idx++){
			    
			   ListOrderedMap lom = (ListOrderedMap)resultList2.get(idx);			    	
	%>									
						  
					<tr>
						<td class="cor_th01 topLz01">
						<%=((String)lom.get("reg_dt")).substring(0,4)+"-"+((String)lom.get("reg_dt")).substring(4,6)+"-"+((String)lom.get("reg_dt")).substring(6,8) %>
						<input type="checkbox" style="display:none;" id='seqnos1' name='seqnos1' value='<%=lom.get("seqno") %>' onClick="javascript:chkSeqnos(<%=idx1 %>)"/>
						<input type="hidden" id='seqnos' name='seqnos' value='<%=lom.get("seqno") %>'/>
						<input type="hidden" id='tabs' name='tabs' value='<%=lom.get("tab")%>@R'/>
						<input type="hidden" id='weekdutyyns' name='weekdutyyns' value='<%=lom.get("conf_weekdutyyn") %>'/>						
						</td>
						<td class="tL topLz01">
							<!-- 테이블안에 테이블01 -->
							<div class="">
							<table border="0" cellspacing="0" cellpadding="0" class="tal_las01_sub">
								<colgroup>
									<col width="15%" />
									<col width="35%" />
									<col width="15%" />
									<col width="35%" />
								</colgroup>
								<tbody>
								<tr>
									<th><spring:message code="las.page.field.common.title" /></th>
									<td colspan="3"><%=(lom != null && lom.size() > 0)?lom.get("title"):"" %></td>
						
								</tr>
								<tr>
									<th><spring:message code="las.page.field.statistics.req_dept" /></th>
									<td><%=(lom != null && lom.size() > 0)?lom.get("dept_nm"):"" %></td>
									<th><spring:message code="las.page.field.speakconsult.respman_nm" /></th>
									<td><%=(lom != null && lom.size() > 0)?lom.get("respman_nm"):"" %></td>				
								</tr>
								<tr>
									<td align="left" colspan="4">
									 
					    			<div class="text_area" style="height:100px; overflow:auto;">
					    			<!-- <textarea name="conts" rows="14" cols="170" readonly class="text_area_full" ><%=(lom != null && lom.size() > 0)?lom.get("cont"):"" %></textarea>-->
					       			<%=(lom != null && lom.size() > 0)?lom.get("cont"):"" %>
					       			</div>
					       			</td>	
								       							
								</tr> 			
								</tbody>			
							</table>	
	
							</div>
							<!-- 테이블안에 테이블01 -->
						</td>
						
					</tr>
	<%		idx1++;
			}

			
			if(resultList4.size() >0){ 
	%>				  
			  
			  <tr>
				<td colspan="2" style='padding:4px 10px;' class='tL'>
					<span class='gray_t_arw2'><spring:message code="las.page.field.statistics.etc" /></span>
				</td>
			  </tr>		
			  				  
<%			}	
			// 기타 실적
			for(int idx=0;idx < resultList4.size();idx++){
			    
			   ListOrderedMap lom = (ListOrderedMap)resultList4.get(idx);			    	
	%>									
						  
					<tr>
						<td class="cor_th01 topLz01">
						<%=((String)lom.get("reg_dt")).substring(0,4)+"-"+((String)lom.get("reg_dt")).substring(4,6)+"-"+((String)lom.get("reg_dt")).substring(6,8) %>	
						<input type="checkbox" style="display:none;" id='seqnos1' name='seqnos1' value='<%=lom.get("seqno") %>' onClick="javascript:chkSeqnos(<%=idx1 %>)"/>
			  		    <input type="hidden" id='seqnos' name='seqnos' value='<%=lom.get("seqno") %>'/>
						<input type="hidden" id='tabs' name='tabs' value='<%=lom.get("tab")%>@R'/>
						<input type="hidden" id='weekdutyyns' name='weekdutyyns' value='<%=lom.get("conf_weekdutyyn") %>'/>						
						</td>
						<td class="tL topLz01">
							<!-- 테이블안에 테이블01 -->
							<div class="">
							<table border="0" cellspacing="0" cellpadding="0" class="tal_las01_sub">
								<colgroup>
									<col width="15%" />
									<col width="35%" /> <!-- width size 변경 신성우 2014-04-24 -->
									<col width="15%" />
									<col width="35%" />
								</colgroup>
								<tbody>
								<tr>
									<th><spring:message code="las.page.field.speakconsult.respman_nm" /></th>
									<td colspan="2"><%=(lom != null && lom.size() > 0)?lom.get("dept_nm"):"" %></td>											
								</tr>
								<tr>
									<td align="left" colspan="4">
									 
					    			<div class="text_area" style="height:100px; overflow:auto;" >
					    			<%=(lom != null && lom.size() > 0)?lom.get("cont"):"" %>
					       			<!-- <textarea name="conts" readonly style="display:none;" rows="0" cols="0"  class="text_area_full" ></textarea>-->
					       			</div>
					       			</td>	
								       							
								</tr> 			
								</tbody>			
							</table>	
	
							</div>
							<!-- 테이블안에 테이블01 -->
						</td>
						
					</tr>
	<%		
			idx1++;
			} 
	%>
				
		  	</tbody>
		</table>			
	 
		<!-- List -->
		<table border="0" cellspacing="0" cellpadding="0" class="tal_las01 mt20">
			<colgroup>
			<c:choose>
				<c:when test="${langCd=='fr'}">
					<col width="14%"/>	
					<col width="86%"/>
				</c:when>
				<c:otherwise>
					<col width="11%"/>	
					<col width="89%"/>
				</c:otherwise>
			</c:choose>
			<thead>
				<tr>
				<th><spring:message code="las.page.field.statistics.schedule" /></th>
				<th><spring:message code="las.page.field.statistics.monthplan1" /></th>
					
				</tr>
			</thead>
			<tbody>
	
	<%		if(resultList1.size() > 0){ %>				  
				<tr>
				<td colspan="2" style='padding:4px 10px;' class='tL'>
					<span class="gray_t_arw2"><spring:message code="las.page.field.statistics.contract" /></span>
				</td>
				</tr>					  
	<%		}
			// 계약 계획
			for(int idx=0;idx < resultList1.size();idx++){
			    
			   ListOrderedMap lom = (ListOrderedMap)resultList1.get(idx);			    	
	%>									
						  
					<tr>
						<td class="cor_th01 topLz01">
						<%=((String)lom.get("reg_dt")).substring(0,4)+"-"+((String)lom.get("reg_dt")).substring(4,6)+"-"+((String)lom.get("reg_dt")).substring(6,8) %>
						<input type="checkbox" style="display:none;" id='seqnos1' name='seqnos1' value='<%=lom.get("seqno") %>' onClick="javascript:chkSeqnos(<%=idx1 %>)"/>
						<input type="hidden" id='seqnos' name='seqnos' value='<%=lom.get("seqno") %>'/>
						<input type="hidden" id='tabs' name='tabs' value='<%=lom.get("tab")%>@P'/>
						<input type="hidden" id='weekdutyyns' name='weekdutyyns' value='<%=lom.get("conf_weekdutyyn") %>'/>							
						</td>
						<td class="tL topLz01">
							<!-- 테이블안에 테이블01 -->
							<div class="">
							<table border="0" cellspacing="0" cellpadding="0" class="tal_las01_sub">
								<colgroup>
									<col width="15%" />
									<col width="35%" /> <!-- width size 변경 신성우 2014-04-24 -->
									<col width="15%" />
									<col width="35%" />
								</colgroup>
								<tbody>
								<tr>
									<th><spring:message code="las.page.field.common.title" /></th>
									<td colspan="3"><%=(lom != null && lom.size() > 0)?lom.get("title"):"" %></td>					
								</tr>
								<tr>
									<th><spring:message code="las.page.field.mainproject.oppnt_comp" /></th>
									<td><%=(lom != null && lom.size() > 0)?lom.get("company"):"" %></td>
									<th><spring:message code="las.page.field.statistics.req_dept" /></th>
									<td><%=(lom != null && lom.size() > 0)?lom.get("dept_nm"):"" %></td>				
								</tr>
								<tr>
									<th><spring:message code="las.page.field.speakconsult.respman_nm" /></th>
									<td colspan="3"><%=(lom != null && lom.size() > 0)?lom.get("respman_nm"):"" %></td>
								</tr>									
								<tr>
									<td align="left" colspan="4">
									 
					    			<div class="text_area" style="height:100px; overflow:auto;">
					    			<!-- <textarea name="conts" rows="14" cols="170" readonly class="text_area_full" ><%=(lom != null && lom.size() > 0)?lom.get("cont"):"" %></textarea> -->
					    			<%=(lom != null && lom.size() > 0)?lom.get("cont"):"" %>
					       			
					       			</div>
					       			</td>	
								       							
								</tr> 			
								</tbody>			
							</table>	
	
							</div>
							<!-- 테이블안에 테이블01 -->
						</td>
						
					</tr>
	<%		idx1++;
			}
			
			
			if(resultList3.size() > 0){
	%>	
				  <tr>
				  <td colspan="2" style='padding:4px 10px;' class='tL'>
					<span class="gray_t_arw2"><spring:message code="las.page.field.statistics.consult" /></span>
				  </td>
				  </tr>		
	<%		}
			// 자문 계획
			for(int idx=0;idx < resultList3.size();idx++){
			    
			   ListOrderedMap lom = (ListOrderedMap)resultList3.get(idx);			    	
	%>									
						  
					<tr>
						<td class="cor_th01 topLz01">
						<%=((String)lom.get("reg_dt")).substring(0,4)+"-"+((String)lom.get("reg_dt")).substring(4,6)+"-"+((String)lom.get("reg_dt")).substring(6,8) %>
						<input type="checkbox" style="display:none;" id='seqnos1' name='seqnos1' value='<%=lom.get("seqno") %>' onClick="javascript:chkSeqnos(<%=idx1 %>)"/>									
						<input type="hidden" id='seqnos' name='seqnos' value='<%=lom.get("seqno") %>'/>
						<input type="hidden" id='tabs' name='tabs' value='<%=lom.get("tab")%>@P'/>
						<input type="hidden" id='weekdutyyns' name='weekdutyyns' value='<%=lom.get("conf_weekdutyyn") %>'/>						
						</td>
						<td class="tL topLz01">
							<!-- 테이블안에 테이블01 -->
							<div class="">
							<table border="0" cellspacing="0" cellpadding="0" class="tal_las01_sub">
								<colgroup>
									<col width="15%" />
									<col width="35%" /> <!-- width size 변경 신성우 2014-04-24 -->
									<col width="15%" />
									<col width="35%" />
								</colgroup>
								<tbody>
								<tr>
									<th><spring:message code="las.page.field.common.title" /></th>
									<td colspan="3"><%=(lom != null && lom.size() > 0)?lom.get("title"):"" %></td>							
								</tr>
								<tr>
									<th><spring:message code="las.page.field.statistics.req_dept" /></th>
									<td><%=(lom != null && lom.size() > 0)?lom.get("dept_nm"):"" %></td>
									<th><spring:message code="las.page.field.speakconsult.respman_nm" /></th>
									<td><%=(lom != null && lom.size() > 0)?lom.get("respman_nm"):"" %></td>													
								</tr>
								<tr>
									<td align="left" colspan="4">
									 
					    			<div class="text_area" style="height:100px; overflow:auto;">
					    			<!-- <textarea name="conts" rows="14" cols="170" readonly class="text_area_full" ><%=(lom != null && lom.size() > 0)?lom.get("cont"):"" %></textarea>-->
					       			<%=(lom != null && lom.size() > 0)?lom.get("cont"):"" %>
					       			</div>
					       			</td>	
								       							
								</tr> 			
								</tbody>			
							</table>	
	
							</div>
							<!-- 테이블안에 테이블01 -->
						</td>
						
					</tr>
	<%		idx1++;
			}
			
			
			if(resultList5.size() >0){ %>				  
			  <tr>
			  <td colspan="2" style='padding:4px 10px;' class='tL'>
				 <span class="gray_t_arw2"><spring:message code="las.page.field.statistics.etc" /></span>
			  </td>
			  </tr>					  
<%			}	
			// 기타 계획
			for(int idx=0;idx < resultList5.size();idx++){
			    
			   ListOrderedMap lom = (ListOrderedMap)resultList5.get(idx);			    	
%>									
						  
					<tr>
						<td class="cor_th01 topLz01">
						<%=((String)lom.get("reg_dt")).substring(0,4)+"-"+((String)lom.get("reg_dt")).substring(4,6)+"-"+((String)lom.get("reg_dt")).substring(6,8) %>
						<input type="checkbox" style="display:none;" id='seqnos1' name='seqnos1' value='<%=lom.get("seqno") %>' onClick="javascript:chkSeqnos(<%=idx1 %>)"/>
						<input type="hidden" id='seqnos' name='seqnos' value='<%=lom.get("seqno") %>'/>
						<input type="hidden" id='tabs' name='tabs' value='<%=lom.get("tab")%>@P'/>
						<input type="hidden" id='weekdutyyns' name='weekdutyyns' value='<%=lom.get("conf_weekdutyyn") %>'/>							
						</td>
						<td class="tL topLz01">
							<!-- 테이블안에 테이블01 -->
							<div class="">
							<table border="0" cellspacing="0" cellpadding="0" class="tal_las01_sub">
								<colgroup>
									<col width="15%" />
									<col width="35%" /> <!-- width size 변경 신성우 2014-04-24 -->
									<col width="15%" />
									<col width="35%" />
								</colgroup>
								<tbody>

								<tr>
									<th><spring:message code="las.page.field.speakconsult.respman_nm" /></th>
									<td colspan="2"><%=(lom != null && lom.size() > 0)?lom.get("dept_nm"):"" %></td>							
								</tr>
								<tr>
									<td align="left" colspan="4">
									 
					    			<div class="text_area" style="height:100px; overflow:auto;" name="conts">
					    			<%=(lom != null && lom.size() > 0)?lom.get("cont"):"" %>
					       			<!-- <textarea name="conts" readonly style="display:none;" rows="0" cols="0"  class="text_area_full" ></textarea> -->
					       			</div>
					       			</td>	
								       							
								</tr> 			
								</tbody>			
							</table>	
	
							</div>
							<!-- 테이블안에 테이블01 -->
						</td>
						
					</tr>
	<%		idx1++;
			} %>					 
		  	</tbody>
		 	</table>				
		 	<!-- //view  -->
			
			<!--  Function Button  -->
			<div class="btn_wrap" id="btn1" style="display:none;">
				<span class='ico_alert02'><spring:message code="las.page.field.statistics.checksave"/></span> &nbsp;
				<span class="btn_all_w"><span class="save"></span><a href="javascript:modifyView()"><spring:message code="las.page.button.save"/></a></span>
			</div>
			
			<div class="btn_wrap" id="btn2" style="display:none;"></div>	
			<!-- //  Function Button  -->
			
			</form:form>
			<!-- form:form end -->
	</div>
	<!-- content_in -->
	</div>
	<!-- //content -->
	
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- container -->
	</div>
	<!-- wrap -->
	
	<!-- footer -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
	<!-- // footer -->
</body>
</html>