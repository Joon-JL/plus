<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@page import="com.sds.secframework.common.util.StringUtil"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.sec.las.statistics.dto.StatisticsForm"%>
<%@page import="com.sds.secframework.common.util.DateUtil"%>
<%@page import="java.math.BigDecimal"%>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<%
    StatisticsForm command = (StatisticsForm)request.getAttribute("command") ;
    int year = Integer.parseInt(DateUtil.year()) ;
    
    List listRow = (List)request.getAttribute("result_list") ;
    List listCol = (List)request.getAttribute("title_list") ;
    
    int colspan = (listCol.size()+1) * 3 ;
    int size = 100 / (colspan+1) ;
    
    String searchAuth = (String)request.getAttribute("searchAuth") ;	
%>
    
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="las.main.title" /></title>
<LINK href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="/script/clms/common.js" type="text/javascript"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.form.js"></script>
<script language="javascript" src="/script/secfw/jquery/calendar/jquery.mousewheel.min.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>    
    
<script language=javascript>
    function f_scroll() {
        var x = document.all["list"].scrollLeft;
        document.all["intitle"].style.left = 0 - x; 
    }
    
    $(document).ready(function(){
        initCal("srch_start_dt");   //첫번재 날짜창 설정 : 변수는 해당 태그의 id값
        initCal("srch_end_dt");     //두번재 날짜창 설정 : 변수는 해당 태그의 id값
        
        changePeriodGbn2() ;
   
    });
    
    function chkType(value) {
        if(value=="C") {
            $("#tr_srch_div").show() ;
        } else {
            $("#tr_srch_div").hide() ;
        }
    }
    
    function changePeriodGbn1() {
    	var val = $("#srch_period_gbn1").val() ;
    	
    	if(val=="2") {
    		$("#span_period1").show() ;
    		changePeriodGbn2() ;
    	} else {
    		$("#span_period1").hide() ;
    		hidePeriod() ;
    	}
    }
    
    function changePeriodGbn2() {
    	var val = $("#srch_period_gbn2").val() ;
    	hidePeriod() ;
    	        	
    	// 연도별
    	if(val=="1") {
    	    $("#span_period2").show() ; // 연도 
    	}
    	// 분기별
    	else if(val=="2") {
    	    $("#span_period2").show() ; // 연도
    	    $("#span_period3").show() ; // 분기
    	}
    	// 월별
        else if(val=="3") {
            $("#span_period2").show() ; // 연도
            $("#span_period4").show() ; // 월
        }
    	// 기간별
        else if(val=="4") {
            $("#span_period5").show() ; // 기간
        }
    	
    }
    
    function hidePeriod() {
        $("#span_period2").hide() ;
        $("#span_period3").hide() ;
        $("#span_period4").hide() ;
        $("#span_period5").hide() ;
    }
    
    function search() {
        viewHiddenProgress(true);
        
        var f = document.frm ;
        f.method.value = "listStatisticsNew" ;
        f.action = "/las/statistics/statistics.do" ; 
        f.submit() ;
    }
    
    function excelDownload() {
        var f = document.frm ;
        f.target = "_self";
        f.method.value = "listStatisticsExcelDown" ;
        f.action = "/las/statistics/statistics.do" ; 
        f.submit() ;
    }
    
    //*************** 라디오 버튼용 시작 ***************//
    function setRadioCl(e){ 
        var srcEl = getSrc(e);
        var ra = srcEl.form[srcEl.name]
        for(var i=0;i<ra.length;i++){
            if(ra[i].checked) ra[i].onpropertychange = function(e){getSrc(e).click()}
            else ra[i].onclick = function(){return false};
        }
    }
    function getSrc(e)
    {
        return e? e.target || e.srcElement : event.srcElement;
    }
    //*************** 라디오 버튼용 끝   ***************//  
    
    function setDay(flag){
    	var f = document.frm ;
    	if(flag=='T1'){
    		f.srch_start_dt.value = f.this_mon0.value;
    		f.srch_end_dt.value = f.this_mon1.value;
    	}else if(flag=='T2'){
    		f.srch_start_dt.value = f.one_mon0.value;
    		f.srch_end_dt.value = f.one_mon1.value;
    	}else if(flag=='T3'){
    		f.srch_start_dt.value = f.three_mon0.value;
    		f.srch_end_dt.value = f.three_mon1.value;
    	}else if(flag=='T4'){
    		f.srch_start_dt.value = f.one_year0.value;
    		f.srch_end_dt.value = f.one_year1.value;
    	}
    	
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
        <h3><spring:message code="las.page.field.statistics.divsStats"/></h3>
    </div>
    <!-- //title -->
        
        <!-- content -->
        <div id="content">
            <div id="content_in">
            
            <!-- form -->
            <form name="frm" id="frm" method="post">
	        <input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />        
	        <input type="hidden" name="method" id="method" value=""/>
	        <input type="hidden" name="gubun" id="gubun" value="<c:out value="${command.gubun}"/>"/>
	        <input type="hidden" name="srch_dmstfrgn_gbn" id="srch_dmstfrgn_gbn"  value="H" >

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
                                            <col width="150PX"/>
                                            <col width="*%"/>                                
                                        </colgroup>
                                        <tr>
                                            <th><spring:message code="las.page.field.statistics.sort"/></th>
                                            <td>
                                                <input name="srch_type" id="srch_type" type="radio" value="C" onclick="chkType(this.value)"<c:if test="${command.srch_type=='C'}"> checked</c:if>> <spring:message code="las.page.field.statistics.reviewCont"/>&nbsp;&nbsp;&nbsp;
                                                <input name="srch_type" id="srch_type" type="radio" value="L" onclick="chkType(this.value)"<c:if test="${command.srch_type=='L'}"> checked</c:if> > <spring:message code="las.page.field.statistics.legalConsult"/>&nbsp;&nbsp;&nbsp;
<%--                                             	<input name="srch_type" id="srch_type" type="radio" value="P" onclick="chkType(this.value)"<c:if test="${command.srch_type=='P'}"> checked</c:if> > <spring:message code="clm.page.msg.manage.stdCont"/> --%>
                                            </td>                         
                                        </tr>
                                       
                                        <tr id="tr_srch_div" style="display:<c:if test="${command.srch_type=='C'}">block</c:if><c:if test="${command.srch_type=='L' || command.srch_type=='P'}">none</c:if>">
                                            <th><spring:message code="las.page.field.statistics.arrange"/></th>
                                            <td>
                                                <input name="srch_div" id="srch_div" type="radio" value="T03"<c:if test="${command.srch_div=='T03'}"> checked</c:if>> <spring:message code="las.page.field.statistics.signStnd"/>&nbsp;&nbsp;&nbsp;
                                                <input name="srch_div" id="srch_div" type="radio" value="T02"<c:if test="${command.srch_div=='T02'}"> checked</c:if>> <spring:message code="las.page.field.statistics.contStep"/>&nbsp;&nbsp;&nbsp;
                                                <input name="srch_div" id="srch_div" type="radio" value="T01"<c:if test="${command.srch_div=='T01'}"> checked</c:if>> <spring:message code="las.page.field.statistics.busStep"/>
                                            </td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                       
                                        <tr>
                                            <th><spring:message code="las.page.field.statistics.searchPeriod"/></th>
                                            <td colspan="3">
                                            	<input type=hidden name="srch_period_gbn2" id="srch_period_gbn2" value='4' />
                                            <%--	
                                                <select name="srch_period_gbn2" id="srch_period_gbn2" onchange="changePeriodGbn2(this.value)">
                                                    <option value="1"<c:if test="${command.srch_period_gbn2=='1'}"> selected</c:if>><spring:message code="las.page.field.statistics.eachYear"/></option>
                                                    <option value="2"<c:if test="${command.srch_period_gbn2=='2'}"> selected</c:if>><spring:message code="las.page.field.statistics.eachQuarter"/></option>
                                                    <option value="3"<c:if test="${command.srch_period_gbn2=='3'}"> selected</c:if>><spring:message code="las.page.field.statistics.eachMonth"/></option>
                                                    <option value="4"<c:if test="${command.srch_period_gbn2=='4'}"> selected</c:if>><spring:message code="las.page.field.statistics.resultPeriod"/></option>
                                                </select>&nbsp;&nbsp;&nbsp;
                                             --%>    
                                                <span id="span_period2" style="display:none">
                                                    <select name="srch_year" id="srch_year">
<%  for(int i=year; i>=2005; i--) {%>                   <option value="<%=i%>"<%= String.valueOf(i).equals(command.getSrch_year()) ? " selected" : ""%>><%=i%></option>
<%  } %>                                            </select> <spring:message code="las.page.field.statistics.year"/>
                                                </span>
                                                <span id="span_period3" style="display:none">
                                                    <select name="srch_quarter" id="srch_quarter">
<%  for(int i=1; i<=4; i++) {%>                         <option value="<%=i%>"<%= String.valueOf(i).equals(command.getSrch_quarter()) ? " selected" : ""%>><%=i%></option>
<%  } %>                                            </select> <spring:message code="las.page.field.statistics.quarter"/>
                                                </span>
                                                <span id="span_period4" style="display:none">
                                                    <select name="srch_month" id="srch_month">
<%  for(int i=1; i<=12; i++) {%>                        <option value="<%=i<10 ? "0" + i : i%>"<%= i==Integer.parseInt(command.getSrch_month()) ? " selected" : ""%>><%=i%></option>
<%  } %>                                            </select> <spring:message code="las.page.field.statistics.mm"/>
                                                </span>
                                                <span id="span_period5" style="display:none">
                                                    <input type="text" name="srch_start_dt" id="srch_start_dt" value="<c:out value="${command.srch_start_dt}"/>" class="text_calendar02" readonly="readonly" /> ~
                                                    <input type="text" name="srch_end_dt" id="srch_end_dt" value="<c:out value="${command.srch_end_dt}"/>" class="text_calendar02" readonly="readonly" />
                                                </span>
                                                
                                                <input name="day_set" id="day_set" type="radio" value="T1" onClick="javascript:setDay(this.value);" <c:if test="${command.day_set=='T1'}">checked</c:if>> This month&nbsp;&nbsp;&nbsp;
                                                <input name="day_set" id="day_set" type="radio" value="T2" onClick="javascript:setDay(this.value);" <c:if test="${command.day_set=='T2'}">checked</c:if>> 1 Month&nbsp;&nbsp;&nbsp;
                                                <input name="day_set" id="day_set" type="radio" value="T3" onClick="javascript:setDay(this.value);" <c:if test="${command.day_set=='T3'}">checked</c:if>> 3 Month&nbsp;&nbsp;&nbsp;
                                                <input name="day_set" id="day_set" type="radio" value="T4" onClick="javascript:setDay(this.value);" <c:if test="${command.day_set=='T4'}">checked</c:if>> 1 Year
                                               
                                                <input name="this_mon0" id="this_mon0" type="hidden" value="<c:out value="${command.this_mon[0]}"/>">
                                                <input name="this_mon1" id="this_mon1" type="hidden" value="<c:out value="${command.this_mon[1]}"/>">
                                                <input name="one_mon0" id="one_mon0" type="hidden" value="<c:out value="${command.one_mon[0]}"/>">
                                                <input name="one_mon1" id="one_mon1" type="hidden" value="<c:out value="${command.one_mon[1]}"/>">
                                                
                                                <input name="three_mon0" id="three_mon0" type="hidden" value="<c:out value="${command.three_mon[0]}"/>">
                                                <input name="three_mon1" id="three_mon1" type="hidden" value="<c:out value="${command.three_mon[1]}"/>">
                                                <input name="one_year0" id="one_year0" type="hidden" value="<c:out value="${command.one_year[0]}"/>">
                                                <input name="one_year1" id="one_year1" type="hidden" value="<c:out value="${command.one_year[1]}"/>">                                                
                                            </td>
                                        </tr>

                                    </table>
                                </td>
                                <td class="vb tC">
                                    <a href="javascript:search();"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="las.page.field.statistics.search"/>"/></a>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
                <!-- // search -->
                
                <!-- btn -->
                <div class="btnwrap tR">
                    <span class="btn_all_w"><span class="excel_down"></span><a href="javascript:excelDownload();"><spring:message code="las.page.field.statistics.excelDw"/></a></span>
                </div>
                <!-- // btn -->

        
                <style>
                .list_basic .sub01 {background:#FFFEEB; color:#5B4C43}
                .tbl_wrap {overflow-x:auto;overflow-y:hidden;width:100%}
                </style>
        
                <div class='tbl_wrap'>
                    <table class="list_basic">
                        <colgroup>
                            <col width="100px"/><!-- 사업부 -->
                            <col width="<%=size %>%"/><!-- 계 - 의뢰 -->
                            <col width="<%=size %>%"/><!-- 계 - 처리 -->
                            <col width="<%=size %>%"/><!-- 계 - L/T -->
                            
                            <c:if test="${command.srch_type=='C' }">
                                <c:forEach var="list" items="${title_list }">
		                            <col width="<%=size %>%"/><!-- 의뢰 -->
		                            <col width="<%=size %>%"/><!-- 처리 -->
		                            <col width="<%=size %>%"/><!-- L/T -->
	                           </c:forEach>           
	                        </c:if>
                        </colgroup>
                        <tr>
                            <th rowspan="3" class='rightline'><spring:message code="las.page.field.statistics.requesttitle.subsidiary"/></th><!-- Subsidiary -->
                            <c:if test="${command.srch_type=='C' || command.srch_type=='L'}">
                            	<th colspan="<%=colspan%>"><c:out value="${title_srch }"/></th>
                            </c:if>
<%--                             <c:if test="${command.srch_type=='P' }"> --%>
<%--                             	<th colspan="<%=colspan%>"><spring:message code="clm.page.msg.manage.stdCont"/><!-- 표준계약서 --></th> --%>
<%--                             </c:if> --%>
                        </tr>
                        <tr>
                            <th colspan="3"><spring:message code="las.page.field.statistics.total"/></th>
                            <c:if test="${command.srch_type=='C' }">
	                            <c:forEach var="list" items="${title_list }">
		                            <th colspan="3"><c:out value="${list.cd_nm}"/></th>
	                            </c:forEach>
                            </c:if>
                        </tr>
                        <tr>
                            <th class="sub01"><spring:message code="las.page.field.statistics.req"/></th>
                            <th class="sub01"><spring:message code="las.page.field.statistics.Proc"/></th>
                            <th class="sub01">L/T</th>
                            
                            <c:if test="${command.srch_type=='C' }">
                                <c:forEach var="list" items="${title_list }">
                                    <th class="sub01"><spring:message code="las.page.field.statistics.req"/></th>
                                    <th class="sub01"><spring:message code="las.page.field.statistics.Proc"/></th>
                                    <th class="sub01">L/T</th>
                                </c:forEach>
                            </c:if>
                        </tr>
                        
<%  for(int i=0; listRow!=null && i<listRow.size(); i++){ 
        Map rowMap = (Map)listRow.get(i) ;
%>                      <tr>
                            <td style="text-align:center"><%=rowMap.get("req_dept") %></td>
                            <td class="tR"><%=StringUtil.commaIn(String.valueOf(rowMap.get("req_total"))) %></td>
                            <td class="tR"><%=StringUtil.commaIn(String.valueOf(rowMap.get("chuli_total"))) %></td>
                            
                            <% 
                            String rowMapCnt = String.valueOf(rowMap.get("reply_total")); 
                            String rowMapCnt_lt = String.valueOf(rowMap.get("lt_total"));
                            %>
                           
                           <td class="tR"><%= rowMapCnt=="0" ? "-" : rowMapCnt_lt %></td> 
                            
  <%      for(int j=0; j<listCol.size(); j++){ 
            Map colMap = (Map)listCol.get(j) ;
%>                          <td class="tR"><%=StringUtil.commaIn(String.valueOf(rowMap.get("req_" + colMap.get("cd")))) %></td>
                            <td class="tR"><%=StringUtil.commaIn(String.valueOf(rowMap.get("chuli_" + colMap.get("cd")))) %></td>
                            
                            <% 
                            String colMapCnt = String.valueOf(colMap.get("reply_" + colMap.get("cd"))); 
                            String colMapCnt_cd = String.valueOf(colMap.get("lt_" + colMap.get("cd")));
                            %>
                            
                            <td class="tR"><%=colMapCnt=="0" ? "-" : rowMap.get("lt_" + colMap.get("cd")) %></td>
<%
        }
%>                           
                        </tr>
<%  } %>
                    </table>
                </div>
                            
                        
             </form>   
            </div>
            <!-- //content_in -->
        </div>
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