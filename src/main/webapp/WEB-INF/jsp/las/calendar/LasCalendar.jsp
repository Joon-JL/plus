<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.Map" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%--
/**
 * 파  일  명 : LasCalendar.jsp
 * 프로그램명 : 법무캘린더
 * 설      명 : 
 * 작  성  자 : 박병주
 * 작  성  일 : 2013.10           
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
<meta charset="utf-8" />

<title><spring:message code="las.page.field.contractManager.requUpdate"/></title>
<LINK href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet">
<link href="<%=CSS%>/popup.css" type="text/css" rel="stylesheet">
<script src="/script/clms/common.js"></script>
<script src="/script/secfw/common/common.js"></script>
<script src="/script/clms/clms_common.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.form.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.flot.js"></script>
<script language="javascript" >	
	
	var yoil = new Array(	"<spring:message code='las.page.field.cal.sun'/>",	//일
		"<spring:message code='las.page.field.cal.mon'/>",	//월 
		"<spring:message code='las.page.field.cal.tue'/>", 	//화
		"<spring:message code='las.page.field.cal.wed'/>", 	//수
		"<spring:message code='las.page.field.cal.thr'/>", 	//목
		"<spring:message code='las.page.field.cal.fri'/>", 	//금
		"<spring:message code='las.page.field.cal.sat'/>");	//토

	/**
	 * 초기화
	 */
	$(document).ready(function() {			
		
		/* 		
		alert($.getDateAdd(1, "Y","2012-10-01"))
		 alert($.getDateAdd(5, "M","2012-10-01"));
		 alert($.getDateAdd("-1", "D","2012-10-01")); */
		
 		//alert("<c:out value='${command.tab_mode}'/>"); 
		
		// TAB 초기화
		$('#tab_month').removeClass();
		$('#tab_day').removeClass();
		$('#tab_week').removeClass();
		$('#div_month').hide();
		$('#div_day').hide();
		$('#div_week').hide();
		
		// TAB 처리 MONTH
		if('tab_month'=="<c:out value='${command.tab_mode}'/>"){
						
			// 개인 일정 데이타를 미리 준비, PREV NEXT 버튼 초기화
			setInitInfo();
			
			$('#tab_month').addClass("on");
			$('#div_month').show();
		
			if(''!="<c:out value='${command.srch_year}'/>"){
				cal("<c:out value='${command.srch_year}'/>","<c:out value='${command.srch_month}'/>");
			} else {
				var NowDate = new Date();
				var nYear = NowDate.getFullYear();		
				var nMonth = NowDate.getMonth();	
				cal(nYear,nMonth);
			}
		
			// TAB 처리 DAY
		} else if('tab_day'=="<c:out value='${command.tab_mode}'/>"){
			
			// PREV NEXT 버튼 초기화
			init_day(formatD("<c:out value='${command.srch_yyyymmdd2}'/>"));
			
			$('#tab_day').addClass("on");
			$('#div_day').show();			
			
			// TAB 처리 WEEK
		} else if('tab_week'=="<c:out value='${command.tab_mode}'/>"){
			
			// PREV NEXT 버튼 초기화
			init_week(formatD("<c:out value='${command.srch_yyyymmdd}'/>"));
			
			$('#tab_week').addClass("on");
			$('#div_week').show();			
		
		} else {
			alert("error");
		}
	});
	
	
	/**
     * Parses the ISO 8601 formated date into a date object, ISO 8601 is YYYY-MM-DD
     * 
     * @param {String} date the date as a string eg 1971-12-15
     * @returns {Date} Date object representing the date of the supplied string
     */
    Date.prototype.parseISO8601 = function(date){
        var matches = date.match(/^\s*(\d{4})-(\d{2})-(\d{2})\s*$/);

        if(matches){
            this.setFullYear(parseInt(matches[1]));    
            this.setMonth(parseInt(matches[2]) - 1);    
            this.setDate(parseInt(matches[3]));    
        }

        return this;
    };

	
	/**
	 * DATEDIFF 스크립트
	 */		
	function getDateAdd(number, dateType,day){
		
		try{
			
			var today = new Date().parseISO8601(day);
			 
			 var dateAdd = "";
			 number = parseInt(number);
		
			 switch (dateType){
			 case "Y" :
			 dateAdd = new Date(today.setYear(today.getFullYear() + number));
			 break;
			 case "M" :
			 dateAdd = new Date(today.setMonth(today.getMonth() + number));
			 break;
			 case "D" :
			 dateAdd = new Date(today.setDate(today.getDate() + number));
			 break;
			 } 
		
			 return dateAdd.getFullYear() + "-" + addZero(dateAdd.getMonth() + 1) + "-" + addZero(dateAdd.getDate());			
			
		} catch (e) {
			alert(e);
		}	

	}
	
	/**
	 * 1, 2, 3, -> 01, 02, 03
	 */
	function addZero(number){		 
		 return parseInt(number, 10) < 10 ? "0" + number : number;
	}
	
	/**
	 * YYYYMMDD  -> YYYY-MM-DD
	 */
	function formatD(str_day) {

		var yyyy = str_day.substring(0,4);
		var mm = str_day.substring(4,6);
		var dd = str_day.substring(6,8);			
		return yyyy + "-" + mm +"-" + dd;
	}
	
	/**
	 * 오늘날짜 구하기
	 */
	function getToday_YYYYMMDD(mode) {
		
		var NowDate = new Date();
		yyyy = NowDate.getFullYear();
		mm = Number(NowDate.getMonth()) + 1;
		dd =  NowDate.getDate();
		
		str_yyyy = yyyy;
		str_mm = "";
		str_dd = "";
		
		var str_yyyymmdd = "";		
		
		if ( mm < 10) {
			str_mm = "0" + mm;
		} else {
			str_mm = String(mm);
		}
		
	  	if (dd < 10) {
	  		str_dd = "0"+ dd;
		} else {
			str_dd = String(dd);
		}
		
		if(mode=="yyyymmdd"){			  
		  	str_yyyymmdd = str_yyyy + str_mm + str_dd;		
		} else if(mode=="yyyy.mm.dd"){
			str_yyyymmdd = str_yyyy + "." + mm +"."  + dd;			
		} else if(mode=="yyyy/mm/dd"){
			str_yyyymmdd = str_yyyy + "/" + mm +"/"  + dd;			
		} else if(mode=="yyyy-mm-dd"){
			str_yyyymmdd = str_yyyy + "-" + str_mm +"-"  + str_dd;			
		} else if(mode=="yyyymm"){
			str_yyyymmdd = str_yyyy + str_mm;		
		}

		return str_yyyymmdd;
	}
	
	/**
	 * 오늘날짜로 돌아가기
	 */
	function goToday() {
		
		var NowDate = new Date();
		var nYear = NowDate.getFullYear();		
		var nMonth = Number(NowDate.getMonth());			
		
		$('#srch_month').val(nMonth);
		$('#srch_year').val(nYear);
		$('#srch_yyyymm').val(getToday_YYYYMMDD('yyyymm'));

		viewHiddenProgress(true) ;	
		frm.action = "<c:url value='/las/cal.do' />";
		frm.method.value = "lasCalendar";
		frm.submit();
	}

	/**
	 * 달력 호출
	 */
	function cal(iYear, iMonth) {
		
		var str = "";
		var frm = document.frm;
		var str_today_yyyymmdd = getToday_YYYYMMDD("yyyymmdd");

		var yoil = new Array(	
								"<spring:message code='las.page.field.cal.mon'/>",	//월 
								"<spring:message code='las.page.field.cal.tue'/>", 	//화
								"<spring:message code='las.page.field.cal.wed'/>", 	//수
								"<spring:message code='las.page.field.cal.thr'/>", 	//목
								"<spring:message code='las.page.field.cal.fri'/>", 	//금
								"<spring:message code='las.page.field.cal.sat'/>", //토
								"<spring:message code='las.page.field.cal.sun'/>"	); //일	
		
		var endDay = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
								
		//윤달계산								
		if(iYear % 400 == 0 || (iYear % 4 == 0 && iYear % 100 !=0)){endDay[1] = 29;}
		
		var c = 0;		
		
		eDate = new Date();
		eDate.setFullYear(iYear);
		eDate.setDate(1);
		eDate.setMonth(iMonth);
		
		$('#span_year').html('');
		$('#span_month').html('');
		
		$('#span_year').html(eDate.getFullYear());
		$('#span_month').html(eDate.getMonth() + 1);

		var fDay = eDate.getDay();
		
		if(fDay==0){
			fDay = 6;
		} else {
			fDay = fDay - 1;
		}
		
		var lDay = endDay[eDate.getMonth()];

		$('#prev_mon').bind('click', function() {
			
			if((Number(iMonth) - 1) == -1){
				iMonth = 12;
				iYear = Number(iYear) - 1;
			}		
			month = Number(iMonth);
 		  	if ( month < 10) {
			    str_month = "0" + month;
			} else {
				str_month = String(month);
			}
			
			$('#srch_month').val(Number(iMonth) - 1);
			$('#srch_year').val(iYear);			
			$('#srch_yyyymm').val(iYear+str_month);
			
			viewHiddenProgress(true) ;	
			
			frm.action = "<c:url value='/las/cal.do' />";
			frm.method.value = "lasCalendar";
			frm.submit();
		
		});

		$('#next_mon').bind('click', function() {			
			
		    if((Number(iMonth) + 1) > 11){
				iMonth = -1;
				iYear = Number(iYear) + 1;
			}			

		    month = Number(iMonth) + 2;		    	    
			
 		  	if ( month < 10) {
			    str_month = "0" + month;
			} else {
				str_month = String(month);
			}
			
			$('#srch_month').val(Number(iMonth) + 1);			
			$('#srch_year').val(iYear);
			$('#srch_yyyymm').val(iYear+str_month);			
			
			viewHiddenProgress(true) ;	
			
			frm.action = "<c:url value='/las/cal.do' />";
			frm.method.value = "lasCalendar";
			frm.submit();
		
		});

		str += "<table class='form_cal'>";
		
		str += "<tr>";
		str += "<th><spring:message code='las.page.field.cal.mon'/></th>";  // 월
		str += "<th><spring:message code='las.page.field.cal.tue'/></th>";  // 화
		str += "<th><spring:message code='las.page.field.cal.wed'/></th>";  // 수
		str += "<th><spring:message code='las.page.field.cal.thr'/></th>";  // 목
		str += "<th><spring:message code='las.page.field.cal.fri'/></th>";  // 금
		str += "<th class='sat'><spring:message code='las.page.field.cal.sat'/></th>"; // 토
		str += "<th class='sun'><spring:message code='las.page.field.cal.sun'/></th>"; // 일
		str += "</tr>";

		str += "</tr><tr>";
		
		//alert("fDay:" + fDay);
		//alert("lDay:" + lDay);

		// 전달 공백 채우기
		for ( var i = 0; i < fDay; i++) {
			str += "<td class='no_momth' >&nbsp</td>";
			c++;
		}
		
		for ( var i = 1; i <= lDay; i++) {
			
			str_month = "";
			str_day = "";
			
			c++;
			str_year = eDate.getFullYear();
			month = eDate.getMonth()+1;

 		  	if ( month < 10) {
			    str_month = "0" + month;
			} else {
				str_month = String(month);
			}
			
 		  	if ( i < 10) {
			    str_day = "0"+ i;
			} else {
				 str_day = String(i);
			}
 		  	
 		  	var str_yyyymm = str_year + str_month;
 		  	var str_yyyymmdd = str_year + str_month + str_day;
 		 	var str_yyyymmdd2 = str_year +"-"+ str_month  +"-"+ str_day;
 		  	
 		  	var td_html = "";
 		  	
 		  	//미리 준비해 두었던 날짜별 데이타를  세팅한다.
 		  	if($("#div_" + str_yyyymmdd).html()!=null)
 		  		td_html = $("#div_" + str_yyyymmdd).html();
 		  	
 		  	// 오늘 인 경우 
/*   		  	if(str_yyyymmdd==str_today_yyyymmdd){
 		  		td_html = "<div class='day'>" + td_html + "</div>";
 		  	}  */
 		  	
			if (c == 7) {
				str += "<td id='" + str_yyyymmdd + "' onclick=\"javascript:goDayTap('" + str_yyyymmdd +"');\" style='cursor:pointer;' ><div class='day_sun'>" + i + "<span class='iconBox icon015' onclick=\"javascript:popInsertSKDL('" + str_yyyymmdd2 +"');\" id='test_" + str_yyyymmdd + "' style='cursor:pointer;'></span>" +  td_html + "</div></td>";
				
				if(i!=lDay)
					str += "</tr><tr>";
				
				c = 0;

			} else if (c==6){
				str += "<td id='" + str_yyyymmdd + "' onclick=\"javascript:goDayTap('" + str_yyyymmdd +"');\" style='cursor:pointer;' ><div class='day_sat'>" + i + "<span class='iconBox icon015' onclick=\"javascript:popInsertSKDL('" + str_yyyymmdd2 +"');\" id='test_" + str_yyyymmdd + "' style='cursor:pointer;'></span>" +  td_html + "</div></td>";
			
			} else {
				str += "<td id='" + str_yyyymmdd + "' onclick=\"javascript:goDayTap('" + str_yyyymmdd +"');\" style='cursor:pointer;' ><div class='day'>" + i + "<span class='iconBox icon015' onclick=\"javascript:popInsertSKDL('" + str_yyyymmdd2 +"');\" id='test_" + str_yyyymmdd + "' style='cursor:pointer;'></span>" +  td_html + "</div></td>";
			} 		  	
					
		}
		
		// 다음달 공백 채우기
		if (c != 0){ 			
			for (i = c; i < yoil.length; i++) {
				str += "<td class='no_momth' >&nbsp;</td>";
			}		
		}

		str += "</tr>";
		str += "</table>";		
	
		document.getElementById('cal').innerHTML = str;
 		
 		//$('#' + str_today_yyyymmdd).css({	background : '#FFFBF3',border:'1px solid  red'});
 		$('#' + str_today_yyyymmdd).removeClass();
 		$('#' + str_today_yyyymmdd).addClass("today");
	
	}
	
	/**
	 * 버튼 및 화면 초기화 (DAY)
	 */
	function init_day(day) {
		
		$('#span_year').html('');
		$('#span_month').html('');			 
		$('#span_day').html(day);
		
		$('#prev_mon').bind('click', function() {			
			
			$("#srch_yyyymmdd2").val(getDateAdd("-1", "D", day));		
			
 			viewHiddenProgress(true) ;				
			frm.action = "<c:url value='/las/cal.do' />";
			frm.method.value = "lasCalendarDay";
			frm.submit();		
		});

		$('#next_mon').bind('click', function() {			
			$("#srch_yyyymmdd2").val(getDateAdd("1", "D", day));			 
			
 			viewHiddenProgress(true) ;				
			frm.action = "<c:url value='/las/cal.do' />";
			frm.method.value = "lasCalendarDay";
			frm.submit();		
		});
	}
	
	/**
	 * 버튼 및 화면 초기화 (WEEK)
	 */
	function init_week(day) {
				
		var str = "";
		var frm = document.frm;
		var str_today_yyyymmdd = getToday_YYYYMMDD("yyyymmdd");

		var yoil = new Array(	"<spring:message code='las.page.field.cal.sun'/>",	//일
				"<spring:message code='las.page.field.cal.mon'/>",	//월 
				"<spring:message code='las.page.field.cal.tue'/>", 	//화
				"<spring:message code='las.page.field.cal.wed'/>", 	//수
				"<spring:message code='las.page.field.cal.thr'/>", 	//목
				"<spring:message code='las.page.field.cal.fri'/>", 	//금
				"<spring:message code='las.page.field.cal.sat'/>");	//토
		
		$('#span_year').html('');
		$('#span_month').html('');
		
		<fmt:parseDate value="${weekList[0].adate}" var="dateFrom" pattern="yyyyMMdd"/>
		<fmt:parseDate value="${weekList[6].adate}" var="dateTo" pattern="yyyyMMdd"/>
			 
		$('#span_day').html("<fmt:formatDate value="${dateFrom}" pattern="yyyy-MM-dd" />" + " ~ " + "<fmt:formatDate value="${dateTo}" pattern="yyyy-MM-dd" />"); // weekList

		$('#prev_mon').bind('click', function() {			
			
			$("#srch_yyyymmdd").val(getDateAdd("-7", "D", day));			 
 			viewHiddenProgress(true) ;				
			frm.action = "<c:url value='/las/cal.do' />";
			frm.method.value = "lasCalendarWeek";
			frm.submit();		
		});

		$('#next_mon').bind('click', function() {			
			$("#srch_yyyymmdd").val(getDateAdd("7", "D", day));			 
 			viewHiddenProgress(true) ;				
			frm.action = "<c:url value='/las/cal.do' />";
			frm.method.value = "lasCalendarWeek";
			frm.submit();		
		});	
	}
	
	/**
	 * 달력 데이타 호출 (MONTH)
	 */
	function setInitInfo() {
		
		var html = "";
		
		<c:if test="${'tab_month' eq command.tab_mode}">
		
			<c:forEach var='list' items='${resultList}' varStatus='k'>
				
					var str_day = "<c:out value='${list.START_DT}'/>";
					var pln = <c:out value='${list.PLN_SUM}'/>;
					var exe = <c:out value='${list.EXE_SUM}'/>;
					var conc = <c:out value='${list.CONC_SUM}'/>;				
					
					html =  html + "<div id='div_" + str_day + "' ><BR>";									
					
					// 개인일정
					if(Number(pln) > 0){				
						html =  html +  "<span class='iconBox icon012'></span> <span class='num' style='margin-top:5px;display:inline-block; height:17px; '>" + pln + "</span>";
					}			
					
					// 이행
					if(Number(exe) > 0){
						html =  html + "<span class='iconBox icon013' ></span> <span class='num' style=' margin-top:5px;display:inline-block; height:17px; '>" + exe + "</span>";
								}			
					
					// 종료
					if(Number(conc) > 0){
						html =  html + "<span class='iconBox icon014' ></span> <span class='num' style='margin-top:5px;display:inline-block; height:17px;'>" + conc + "</span>";
								}	
										
					html =  html +  "</div>";		
				
			</c:forEach>	
			
		</c:if>
		
		//alert("OUT:" + html);
		
		$("#initInfo").html(html);
	}

	/**
	 * TAB 처리
	 */
	function tabAction(flag) {
		
		var frm = document.frm;		
		$("#tab_mode").val(flag);
		
		frm.target = "_self";
 		frm.action = "/las/cal.do";
		
		if (flag == "tab_month") { 		// 월별
			frm.method.value = "lasCalendar";	
		} else if (flag == "tab_day") { // 일별
	 		frm.method.value = "lasCalendarDay";
		} else if (flag == "tab_week") { // 주별			
	 		frm.method.value = "lasCalendarWeek";		 		
		}
		
		viewHiddenProgress(true) ;		
		frm.submit();		
	}	
	
	/**
	* 일정추가 팝업
	*/
	function popInsertSKDL(day){
		
		$("#write_mode").val("INSERT");		
		
		if(day=='') {
			if($("#srch_yyyymmdd2").val()!=''){
				day = formatD($("#srch_yyyymmdd2").val());
			} else {
				day = getToday_YYYYMMDD("yyyy-mm-dd");
			}
		}		
				
		$("#pop_up_day").val(day);		
 		
		var frm = document.frm2;	 //주의!!! FRM2 임
		
 		PopUpWindowOpen('', 700, 385, false);
		frm.method.value = "popInsertSKDL"; // 
		frm.action = "<c:url value='/las/cal.do' />";
		frm.target = "PopUpWindow";		
		frm.submit(); 		
	}
	
	/**
	* 일정수정 팝업
	*/
	function popMoidfySKDL(seqno){
		$("#seqno").val(seqno);	
		$("#write_mode").val("MODIFY");		
 		
		var frm = document.frm2;	 //주의!!! FRM2 임
		
 		PopUpWindowOpen('', 700, 385, false);
		frm.method.value = "popInsertSKDL"; // 
		frm.action = "<c:url value='/las/cal.do' />";
		frm.target = "PopUpWindow";		
		frm.submit(); 		
	}	
	
	/**
	 * 날짜 상세 보기
	 */
	function goDayTap(str_yyymmdd) {
		
		var frm = document.frm;		
		$("#srch_yyyymmdd2").val(str_yyymmdd);
		
		frm.target = "_self";
 		frm.action = "/las/cal.do";
 		frm.method.value = "lasCalendarDay";	
		viewHiddenProgress(true) ;		
		frm.submit();		
	}	
	
	/**
	 * 메세지 보기
	 */
	function showMessage(msg) {
		if (msg != "" && msg != null && msg.length > 1) {
			alert(msg);
		}
	}	
	
	/**
	 * 조부모창 컨트롤 (새로고침)
	 */
	function grandFatherRefresh() {	
		
		var srch_yyyymmdd = $("#srch_yyyymmdd2").val();
		var srch_yyyymm = srch_yyyymmdd.substr(0,6);

		$('#srch_yyyymm', opener.document).val(srch_yyyymm);
		$(opener.location).attr("href", "javascript:setInitInfoAJAX('')" );
		self.close();
	}	
	
</script>
<body class="pop" onLoad='showMessage("<c:out value='${returnMessage}'/>");' >

		
		<!-- header -->
			<h1><spring:message code="las.page.field.cal.skdl" /><!-- 법무일정관리 --></h1>
			<!-- //header -->
			<div class="pop_area">
			<!-- Popup Detail -->
		
		<!-- pop_content -->
		<div class="pop_content" style='height:470px'>
			
			<form name="frm" id='frm' method="post" autocomplete="off" >
			<input type="hidden" id="menu_id" name="menu_id" value="<c:out value='${command.menu_id}'/>">
			<input type="hidden" id="method" name="method" value="">
			<input type="hidden" name="curPage" id="curPage" value="<c:out value='${command.curPage}'/>">
		
			<!-- 달력 키 필드-->
			<input type="hidden" id="srch_year" name="srch_year" value="<c:out value='${command.srch_year}'/>">
			<input type="hidden" id="srch_month" name="srch_month" value="<c:out value='${command.srch_month}'/>">
			<input type="hidden" id="srch_yyyymm" name="srch_yyyymm" value="<c:out value='${command.srch_yyyymm}'/>">
			<input type="hidden" id="srch_yyyymmdd" name="srch_yyyymmdd" value="<c:out value='${command.srch_yyyymmdd}'/>">
			<input type="hidden" id="srch_yyyymmdd2" name="srch_yyyymmdd2" value="<c:out value='${command.srch_yyyymmdd2}'/>">
			<input type="hidden" id="srch_mode" name="srch_mode" value="">
			<input type="hidden" id="tab_mode" name="tab_mode" value="">
	 				
            <div class='date_cal'>
			       	<a><img id="prev_mon"  src="/images/las/en/btn/month_prev.gif" class='img_align'/></a>&nbsp;			
						<c:choose>
							<c:when test="${'tab_month' eq command.tab_mode}">
									<span id="span_year"></span>.<span id="span_month"></span> <span id="span_day"></span>
							</c:when>
							<c:otherwise>
									<span id="span_day"></span>
							</c:otherwise>
						</c:choose>
			    	&nbsp;<a><img  id="next_mon" src="/images/las/en/btn/month_next.gif" class='img_align' /></a> 
			    	
			    	<c:if test="${'tab_month' eq command.tab_mode}">
			    		<a href="javascript:goToday();"" class="btn_today"><strong><spring:message code="secfw.page.field.common.today" /></strong></a> <!-- today-->
			    	</c:if>
			    	
			</div> 
            
            <div class="cal_divider"></div>                          
                
             <!-- 범례 -->
			<div class="exWrap2 mt10" style='margin-bottom:-18px'> 
				<span class="iconBox icon016"></span><spring:message code="las.page.field.cal.Personal" /><!-- 개인 -->
				<span class="iconBox icon017"></span> <spring:message code="las.page.field.cal.Execution" /><!-- 이행 -->
				<span class="iconBox icon018"></span><spring:message code="las.page.field.cal.Termination" /><!-- 종료 -->
 			</div>
			<!-- //범례 -->
                
            <!-- TAB_MENU -->
			<div id="tab01" >
					<div class="tab_box">
							<ul class="tab_basic_cal">
								<li id="tab_month" onclick="javascript:tabAction('tab_month');" class="on" ><a><spring:message code="las.page.field.cal.Month" /><!-- 월 --></a></li>
								<li id="tab_day" onclick="javascript:tabAction('tab_day');" ><a><spring:message code="las.page.field.cal.Day" /><!-- 일 --></a></li>
								<li id="tab_week" onclick="javascript:tabAction('tab_week');" ><a><spring:message code="las.page.field.cal.Week" /><!-- 주 --></a></li>
							</ul>
					</div>			
			</div>
			<!-- // TAB_MENU -->
				
			<!-- 월별 테이블 -->
			<div id="div_month" >                                    
                  <table align="center">
 						<tr><td id="cal"></td></tr>
 					</table>			
		    </div>
			<!-- // 월별 테이블 -->
			
			<!---일별 테이블 -->
			<div id="div_day" >
				<table class="list_basic_new">
						<colgroup>
						<col width="15%" />
						<col width="15%" />
						<col width="10%" />
						<col width="45%" />
						<col width="10%" />						
						</colgroup>
						<thead>
								<tr>
										<th><spring:message code="las.page.field.cal.Start" /><!-- 시작 --></th>
										<th><spring:message code="las.page.field.cal.End" /><!-- 끝 --></th>
										<th><spring:message code="las.page.field.cal.Type" /><!-- 일정타입 --></th>
										<th><spring:message code="las.page.field.cal.Subject" /><!-- 제목 --></th>
										<th><spring:message code="las.page.field.cal.Location" /><!-- 장소 --></th>										
								</tr>
						</thead>
						<tbody>
							<c:if test="${not empty dayList}">
								<c:forEach items="${dayList}" var="list"  varStatus="x">
									<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
										<td class="tC"><c:out value='${list.START_D}'/>
											<%-- 	<fmt:parseDate value="${list.START_D}" var="start_dt" pattern="yyyyMMdd"/>
												<fmt:formatDate value="${start_dt}" pattern="yyyy-MM-dd" />  --%>
										</td>
										<td class="tC"><c:out value='${list.END_D}'/>
											<%-- 	<fmt:parseDate value="${list.END_D}" var="end_dt" pattern="yyyyMMdd"/>
												<fmt:formatDate value="${end_dt}" pattern="yyyy-MM-dd" />  --%>
										</td>
										<td class="tC overflow">
											<c:choose>
												<c:when test="${'P' eq list.S_TYPE}"><c:out value='${list.TYPE1_NM}'/></c:when>
												<c:when test="${'E' eq list.S_TYPE}"><spring:message code="las.page.field.cal.Execution" /><!-- 이행 --></c:when>
												<c:when test="${'T' eq list.S_TYPE}"><spring:message code="las.page.field.cal.Termination" /><!-- 종료 --></c:when>
												<c:otherwise></c:otherwise>
											</c:choose>								
										</td>
										<td class="tL overflow" title="<c:out value='${list.TITLE}'/>">
											<c:choose>
												<c:when test="${'P' eq list.S_TYPE}"><span class='iconBox icon012' ></span><a onclick="javascript:popMoidfySKDL('<c:out value='${list.SEQNO}'/>');"><c:out value='${list.TITLE}'/></a></c:when>
												<c:when test="${'E' eq list.S_TYPE}"><span class='iconBox icon013' ></span><c:out value='${list.TITLE}'/></c:when>
												<c:when test="${'T' eq list.S_TYPE}"><span class='iconBox icon014' ></span><c:out value='${list.TITLE}'/></c:when>
												<c:otherwise></c:otherwise>
											</c:choose>												
										</td>
										<td class="tC overflow" title="<c:out value='${list.LOCA}'/>" ><c:out value='${list.LOCA}'/></td>										
								</tr>
								</c:forEach>		
							</c:if>						
						</tbody>
				</table>
			</div>
			<!---// 일별 테이블 --> 
			
			<!-- 주별 테이블 -->
			<div id="div_week" >				                                    
                <table class="list_basic_new">
								<colgroup>
								<col width="20%" />
								<col width="7%" />
								<col width="73%" /> <!-- width size 변경 신성우 2014-04-24 -->
								</colgroup>
								<thead>
									<tr>
										<th><spring:message code="las.page.field.cal.Notes" /><!-- 날짜 --></th>
										<th><spring:message code="las.page.field.cal.yoil" /><!-- 요일 --></th>
										<th><spring:message code="las.page.field.cal.Schedule" /><!-- Schedule --></th>
									</tr>
								<tbody id="week_tbody" >
									<c:if test="${not empty weekList}">
										<c:forEach items="${weekList}" var="list"  varStatus="x">
											<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
												<td class="tC">
														<fmt:parseDate value="${list.ADATE}" var="dateFmt" pattern="yyyyMMdd"/>
											 			<fmt:formatDate value="${dateFmt}" pattern="yyyy-MM-dd" />
												</td>
												<td class="tC"><script>document.write(yoil['<c:out value='${x.index}'/>']);</script></td>
												<td class="tL overflow">
													<c:if test="${not empty list.CONTLIST}">
														<c:forEach items="${list.CONTLIST}" var="list2"  varStatus="x2">
															<c:choose>
																<c:when test="${'P' eq list2.S_TYPE}"><span class='iconBox icon012' ></span><c:out value='${list2.TITLE}'/></c:when>
																<c:when test="${'E' eq list2.S_TYPE}"><span class='iconBox icon013' ></span><c:out value='${list2.TITLE}'/></c:when>
																<c:when test="${'T' eq list2.S_TYPE}"><span class='iconBox icon014' ></span><c:out value='${list2.TITLE}'/></c:when>
																<c:otherwise></c:otherwise>
															</c:choose>	
															<BR>
														</c:forEach>		
													</c:if>													
												</td>
											</tr>
										</c:forEach>		
									</c:if>																
								</tbody>
						</table>
		    </div>
			<!-- // 주별 테이블 -->
 			
 			</form>
 			
 			<form name="frm2" id='frm2' method="post" autocomplete="off">
						<input type="hidden" id="menu_id" name="menu_id" value="<c:out value='${command.menu_id}'/>">
						<input type="hidden" id="method" name="method" value="">
						<input type="hidden" name="curPage" id="curPage" value="<c:out value='${command.curPage}'/>">
						<input type="hidden" id="write_mode" name="write_mode" value="">	
						<input type="hidden" id="pop_up_day" name="pop_up_day" value="">
						<input type="hidden" id="seqno" name="seqno" value="">				
			</form>					

			<div id="initInfo" style="display: none;  position: 1000,1000; z-index: 9999; width: 352px; height: 240px; left: 0px; top: 0px; background: #FFF;"></div> 	
			
			<br>
			<br>							
            
            </div>			
	</div>	

<!--footer -->
<div class="pop_footer">
	<div class="btn_wrap fR" >
		<span class="btn_all_w" onclick="javascript:popInsertSKDL('');"><span class="save"></span><a href="#"><spring:message code="las.page.field.cal.Register" /></a></span>
		<span class="btn_all_w mR5" onclick="javascript:grandFatherRefresh();"><span class="cancel"></span><a href="#"><spring:message code='las.page.button.close' /></a></span>
	</div>
</div>
<!-- //footer -->	
<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</body>
</html>