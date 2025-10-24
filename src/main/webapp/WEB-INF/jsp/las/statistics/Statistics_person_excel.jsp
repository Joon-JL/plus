<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@page import="com.sds.secframework.common.util.DateUtil"%>
<%@page import="com.sds.secframework.common.util.StringUtil"%>
<%@page import="com.sec.las.statistics.dto.StatisticsForm"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>


<%
    StatisticsForm command = (StatisticsForm)request.getAttribute("command") ;
    int year = Integer.parseInt(DateUtil.year()) ;
    int cnt = command.getSrch_type().equals("C") ? 7 : 4 ;
    int colspan = command.getSrch_period_gbn1().equals("1") ? cnt * 5 : cnt * 1 ; 
%>


<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=8;" />
    <style>
        .search_title {background:#CCCCCC; color:#5B4C43}
    </style>
</head>
<body>
<table width="100%">
    <tr>
        <td colspan="<%=colspan%>" align="left"><h3><spring:message code="las.page.field.statistics.eachStats"/></h3></td><td align="right"><span style="color:red"><b><spring:message code="las.page.field.lawservice.classified"/></b></span></td>
    </tr>
</table>

<%-- <table border="1">
    <tr>
        <th class="search_title"><spring:message code="las.page.field.statistics.sort"/></th>
        <td>
            <c:if test="${command.srch_type=='C'}"><spring:message code="las.page.field.statistics.reviewCont"/></c:if>
            <c:if test="${command.srch_type=='L'}"><spring:message code="las.page.field.statistics.legalConsult"/></c:if>
        </td>
        <th class="search_title"><spring:message code="las.page.field.statistics.mainDept"/></th>
        <td>
            <c:if test="${command.srch_dmstfrgn_gbn=='H'}"><spring:message code="las.page.field.statistics.domestic"/></c:if>
            <c:if test="${command.srch_dmstfrgn_gbn=='F'}"><spring:message code="las.page.field.statistics.abroad"/></c:if>
            <c:if test="${command.srch_dmstfrgn_gbn=='IP'}">IP</c:if>
        </td>                           
    </tr>
    <tr>
        <th class="search_title"><spring:message code="las.page.field.statistics.searchPeriod"/></th>
        <td colspan="3">
            <c:if test="${command.srch_period_gbn1=='1'}"><spring:message code="las.page.field.statistics.prStnd"/></c:if>
            <c:if test="${command.srch_period_gbn1=='2'}">
                <c:if test="${command.srch_period_gbn2!='4'}"><c:out value="${command.srch_year}"/><spring:message code="las.page.field.statistics.year"/></c:if>
                <c:if test="${command.srch_period_gbn2=='2'}"><c:out value="${command.srch_quarter}"/><spring:message code="las.page.field.statistics.quarter"/></c:if>
                <c:if test="${command.srch_period_gbn2=='3'}"><c:out value="${command.srch_month}"/><spring:message code="las.page.field.statistics.mm"/></c:if>
                <c:if test="${command.srch_period_gbn2=='4'}"><c:out value="${command.srch_start_dt}"/> ~ <c:out value="${command.srch_end_dt}"/></c:if>
            </c:if>
        </td>
    </tr>
</table>
<br/> --%>

<table border="1">
    <tr>
        <th rowspan="2" class="search_title"><spring:message code="las.page.field.statistics.reviewer.title"/></th><!-- Reviewer -->
        <th colspan="<%=cnt %>" class="search_title"><c:out value="${title_srch}"/></th>
        <c:if test="${command.srch_period_gbn1=='1' }">
            <th colspan="<%=cnt %>" class="search_title"><spring:message code="las.page.field.statistics.resultThisW"/></th>
            <th colspan="<%=cnt %>" class="search_title"><spring:message code="las.page.field.statistics.recordPrevMm"/></th>
            <th colspan="<%=cnt %>" class="search_title"><spring:message code="las.page.field.statistics.resultThisM"/></th>
            <th colspan="<%=cnt %>" class="search_title"><spring:message code="las.page.field.statistics.resultThisY"/></th>
        </c:if>
    </tr>
    <tr>
        <th class="search_title"><spring:message code="las.page.field.statistics.req"/><c:if test="${command.srch_type=='C' }">(<spring:message code="las.page.field.statistics.,mainTh"/>)</c:if></th>
        <c:if test="${command.srch_type=='C' }"><th class="search_title"><spring:message code="las.page.field.statistics.req"/>(<spring:message code="las.page.field.statistics.vice"/>)</th></c:if>
        <th class="search_title"><spring:message code="las.page.field.statistics.Proc"/><c:if test="${command.srch_type=='C' }">(<spring:message code="las.page.field.statistics.,mainTh"/>)</c:if></th>
        <c:if test="${command.srch_type=='C' }"><th class="search_title"><spring:message code="las.page.field.statistics.Proc"/>(<spring:message code="las.page.field.statistics.vice"/>)</th></c:if>
        <th class="search_title"><spring:message code="las.page.field.statistics.return"/><c:if test="${command.srch_type=='C' }">(<spring:message code="las.page.field.statistics.,mainTh"/>)</c:if></th>
        <c:if test="${command.srch_type=='C' }"><th class="search_title"><spring:message code="las.page.field.statistics.return"/>(<spring:message code="las.page.field.statistics.vice"/>)</th></c:if>
        <th class="search_title">L/T</th>
        
        <c:if test="${command.srch_period_gbn1=='1' }">
            <th class="search_title"><spring:message code="las.page.field.statistics.req"/><c:if test="${command.srch_type=='C' }">(<spring:message code="las.page.field.statistics.,mainTh"/>)</c:if></th>
	        <c:if test="${command.srch_type=='C' }"><th class="search_title"><spring:message code="las.page.field.statistics.req"/>(<spring:message code="las.page.field.statistics.vice"/>)</th></c:if>
	        <th class="search_title"><spring:message code="las.page.field.statistics.Proc"/><c:if test="${command.srch_type=='C' }">(<spring:message code="las.page.field.statistics.,mainTh"/>)</c:if></th>
	        <c:if test="${command.srch_type=='C' }"><th class="search_title"><spring:message code="las.page.field.statistics.Proc"/>(<spring:message code="las.page.field.statistics.vice"/>)</th></c:if>
	        <th class="search_title"><spring:message code="las.page.field.statistics.return"/><c:if test="${command.srch_type=='C' }">(<spring:message code="las.page.field.statistics.,mainTh"/>)</c:if></th>
	        <c:if test="${command.srch_type=='C' }"><th class="search_title"><spring:message code="las.page.field.statistics.return"/>(<spring:message code="las.page.field.statistics.vice"/>)</th></c:if>
	        <th class="search_title">L/T</th>
	        <th class="search_title"><spring:message code="las.page.field.statistics.req"/><c:if test="${command.srch_type=='C' }">(<spring:message code="las.page.field.statistics.,mainTh"/>)</c:if></th>
            <c:if test="${command.srch_type=='C' }"><th class="search_title"><spring:message code="las.page.field.statistics.req"/>(<spring:message code="las.page.field.statistics.vice"/>)</th></c:if>
            <th class="search_title"><spring:message code="las.page.field.statistics.Proc"/><c:if test="${command.srch_type=='C' }">(<spring:message code="las.page.field.statistics.,mainTh"/>)</c:if></th>
            <c:if test="${command.srch_type=='C' }"><th class="search_title"><spring:message code="las.page.field.statistics.Proc"/>(<spring:message code="las.page.field.statistics.vice"/>)</th></c:if>
            <th class="search_title"><spring:message code="las.page.field.statistics.return"/><c:if test="${command.srch_type=='C' }">(<spring:message code="las.page.field.statistics.,mainTh"/>)</c:if></th>
            <c:if test="${command.srch_type=='C' }"><th class="search_title"><spring:message code="las.page.field.statistics.return"/>(<spring:message code="las.page.field.statistics.vice"/>)</th></c:if>
            <th class="search_title">L/T</th>
            <th class="search_title"><spring:message code="las.page.field.statistics.req"/><c:if test="${command.srch_type=='C' }">(<spring:message code="las.page.field.statistics.,mainTh"/>)</c:if></th>
            <c:if test="${command.srch_type=='C' }"><th class="search_title"><spring:message code="las.page.field.statistics.req"/>(<spring:message code="las.page.field.statistics.vice"/>)</th></c:if>
            <th class="search_title"><spring:message code="las.page.field.statistics.Proc"/><c:if test="${command.srch_type=='C' }">(<spring:message code="las.page.field.statistics.,mainTh"/>)</c:if></th>
            <c:if test="${command.srch_type=='C' }"><th class="search_title"><spring:message code="las.page.field.statistics.Proc"/>(<spring:message code="las.page.field.statistics.vice"/>)</th></c:if>
            <th class="search_title"><spring:message code="las.page.field.statistics.return"/><c:if test="${command.srch_type=='C' }">(<spring:message code="las.page.field.statistics.,mainTh"/>)</c:if></th>
            <c:if test="${command.srch_type=='C' }"><th class="search_title"><spring:message code="las.page.field.statistics.return"/>(<spring:message code="las.page.field.statistics.vice"/>)</th></c:if>
            <th class="search_title">L/T</th>
            <th class="search_title"><spring:message code="las.page.field.statistics.req"/><c:if test="${command.srch_type=='C' }">(<spring:message code="las.page.field.statistics.,mainTh"/>)</c:if></th>
            <c:if test="${command.srch_type=='C' }"><th class="search_title"><spring:message code="las.page.field.statistics.req"/>(<spring:message code="las.page.field.statistics.vice"/>)</th></c:if>
            <th class="search_title"><spring:message code="las.page.field.statistics.Proc"/><c:if test="${command.srch_type=='C' }">(<spring:message code="las.page.field.statistics.,mainTh"/>)</c:if></th>
            <c:if test="${command.srch_type=='C' }"><th class="search_title"><spring:message code="las.page.field.statistics.Proc"/>(<spring:message code="las.page.field.statistics.vice"/>)</th></c:if>
            <th class="search_title"><spring:message code="las.page.field.statistics.return"/><c:if test="${command.srch_type=='C' }">(<spring:message code="las.page.field.statistics.,mainTh"/>)</c:if></th>
            <c:if test="${command.srch_type=='C' }"><th class="search_title"><spring:message code="las.page.field.statistics.return"/>(<spring:message code="las.page.field.statistics.vice"/>)</th></c:if>
            <th class="search_title">L/T</th>
        </c:if>
    </tr>
    
    <c:forEach var="list" items="${result_list }">
        <tr>
            <td><c:out value="${list.respman_nm }"/></td><!-- 담당자 -->
            <td style="text-align:right"><c:out value="${list.req_jung1 }"/></td>
            <c:if test="${command.srch_type=='C' }"><td style="text-align:right"><c:out value="${list.req_bu1 }"/></td></c:if>
            <td style="text-align:right"><c:out value="${list.chuli_jung1 }"/></td>
            <c:if test="${command.srch_type=='C' }"><td style="text-align:right"><c:out value="${list.chuli_bu1 }"/></td></c:if>
            <td style="text-align:right"><c:out value="${list.reply_jung1 }"/></td>
            <c:if test="${command.srch_type=='C' }"><td style="text-align:right"><c:out value="${list.reply_bu1 }"/></td></c:if>
            <td style="text-align:right">
                    <c:if test="${list.reply_jung1 != '0' || list.reply_bu1 != '0'}"><c:out value="${list.lt1 }"/></c:if>
                    <c:if test="${list.reply_jung1 == '0' && list.reply_bu1 == '0' }">-</c:if>
                </td>
            
            <c:if test="${command.srch_period_gbn1=='1' }">
                <td style="text-align:right"><c:out value="${list.req_jung2 }"/></td>
	            <c:if test="${command.srch_type=='C' }"><td style="text-align:right"><c:out value="${list.req_bu2 }"/></td></c:if>
	            <td style="text-align:right"><c:out value="${list.chuli_jung2 }"/></td>
	            <c:if test="${command.srch_type=='C' }"><td style="text-align:right"><c:out value="${list.chuli_bu2 }"/></td></c:if>
	            <td style="text-align:right"><c:out value="${list.reply_jung2 }"/></td>
	            <c:if test="${command.srch_type=='C' }"><td style="text-align:right"><c:out value="${list.reply_bu2 }"/></td></c:if>
	            <td style="text-align:right">
                    <c:if test="${list.reply_jung2 != '0' || list.reply_bu2 != '0'}"><c:out value="${list.lt2 }"/></c:if>
                    <c:if test="${list.reply_jung2 == '0' && list.reply_bu2 == '0' }">-</c:if>
                </td>
                
                <td style="text-align:right"><c:out value="${list.req_jung3 }"/></td>
                <c:if test="${command.srch_type=='C' }"><td style="text-align:right"><c:out value="${list.req_bu3 }"/></td></c:if>
                <td style="text-align:right"><c:out value="${list.chuli_jung3 }"/></td>
                <c:if test="${command.srch_type=='C' }"><td style="text-align:right"><c:out value="${list.chuli_bu3 }"/></td></c:if>
                <td style="text-align:right"><c:out value="${list.reply_jung3 }"/></td>
                <c:if test="${command.srch_type=='C' }"><td style="text-align:right"><c:out value="${list.reply_bu3 }"/></td></c:if>
                <td style="text-align:right">
                    <c:if test="${list.reply_jung3 != '0' || list.reply_bu3 != '0'}"><c:out value="${list.lt3 }"/></c:if>
                    <c:if test="${list.reply_jung3 == '0' && list.reply_bu3 == '0' }">-</c:if>
                </td><!-- 전월실적 - L/T -->
                
               <td style="text-align:right"><c:out value="${list.req_jung4 }"/></td>
                <c:if test="${command.srch_type=='C' }"><td style="text-align:right"><c:out value="${list.req_bu4 }"/></td></c:if>
                <td style="text-align:right"><c:out value="${list.chuli_jung4 }"/></td>
                <c:if test="${command.srch_type=='C' }"><td style="text-align:right"><c:out value="${list.chuli_bu4 }"/></td></c:if>
                <td style="text-align:right"><c:out value="${list.reply_jung4 }"/></td>
                <c:if test="${command.srch_type=='C' }"><td style="text-align:right"><c:out value="${list.reply_bu4 }"/></td></c:if>
                <td style="text-align:right">
                    <c:if test="${list.reply_jung4 != '0' || list.reply_bu4 != '0'}"><c:out value="${list.lt4 }"/></c:if>
                    <c:if test="${list.reply_jung4 == '0' && list.reply_bu4 == '0' }">-</c:if>
                </td><!-- 금월실적 - L/T -->
                
                <td style="text-align:right"><c:out value="${list.req_jung5 }"/></td>
                <c:if test="${command.srch_type=='C' }"><td style="text-align:right"><c:out value="${list.req_bu5 }"/></td></c:if>
                <td style="text-align:right"><c:out value="${list.chuli_jung5 }"/></td>
                <c:if test="${command.srch_type=='C' }"><td style="text-align:right"><c:out value="${list.chuli_bu5 }"/></td></c:if>
                <td style="text-align:right"><c:out value="${list.reply_jung5 }"/></td>
                <c:if test="${command.srch_type=='C' }"><td style="text-align:right"><c:out value="${list.reply_bu5 }"/></td></c:if>
                <td style="text-align:right">
                    <c:if test="${list.reply_jung5 != '0' || list.reply_bu5 != '0'}"><c:out value="${list.lt5 }"/></c:if>
                    <c:if test="${list.reply_jung5 == '0' && list.reply_bu5 == '0' }">-</c:if>
                </td><!-- 금년실적 - L/T -->
            </c:if>
        </tr>
    </c:forEach>
    
</table>

</body>
</html>