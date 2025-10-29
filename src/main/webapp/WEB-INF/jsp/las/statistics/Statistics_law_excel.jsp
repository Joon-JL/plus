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
<table>
    <tr>
        <td colspan="19" align="left"><h3><spring:message code="las.page.field.statistics.lgTeamStats"/></h3></td><td align="right"><span style="color:red"><b><spring:message code="las.page.field.lawservice.classified"/></b></span></td>
    </tr>
</table>

<%-- <table border="1">
    <tr>
        <th class="search_title"><spring:message code="las.page.field.statistics.sort"/></th>
        <td colspan="3">
            <c:if test="${command.srch_type=='C'}"><spring:message code="las.page.field.statistics.reviewCont"/></c:if>
            <c:if test="${command.srch_type=='L'}"><spring:message code="las.page.field.statistics.legalConsult"/></c:if>
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
        <th rowspan="2" class="search_title"><spring:message code="las.page.field.statistics.requesttitle.legalteam"/></th><!-- Legal Team -->
        <th colspan="4" class="search_title"><c:out value="${title_srch}"/></th>
        <c:if test="${command.srch_period_gbn1=='1' }">
            <th colspan="4" class="search_title"><spring:message code="las.page.field.statistics.resultThisW"/></th>
            <th colspan="4" class="search_title"><spring:message code="las.page.field.statistics.recordPrevMm"/></th>
            <th colspan="4" class="search_title"><spring:message code="las.page.field.statistics.resultThisM"/></th>
            <th colspan="4" class="search_title"><spring:message code="las.page.field.statistics.resultThisY"/></th>
        </c:if>
    </tr>
    <tr>
        <th class="search_title"><spring:message code="las.page.field.statistics.req"/></th>
        <th class="search_title"><spring:message code="las.page.field.statistics.Proc"/></th>
        <th class="search_title"><spring:message code="las.page.field.statistics.return"/></th>
        <th class="search_title">L/T</th>
        
        <c:if test="${command.srch_period_gbn1=='1' }">
            <th class="search_title"><spring:message code="las.page.field.statistics.req"/></th>
            <th class="search_title"><spring:message code="las.page.field.statistics.Proc"/></th>
            <th class="search_title"><spring:message code="las.page.field.statistics.return"/></th>
            <th class="search_title">L/T</th>
            <th class="search_title"><spring:message code="las.page.field.statistics.req"/></th>
            <th class="search_title"><spring:message code="las.page.field.statistics.Proc"/></th>
            <th class="search_title"><spring:message code="las.page.field.statistics.return"/></th>
            <th class="search_title">L/T</th>
            <th class="search_title"><spring:message code="las.page.field.statistics.req"/></th>
            <th class="search_title"><spring:message code="las.page.field.statistics.Proc"/></th>
            <th class="search_title"><spring:message code="las.page.field.statistics.return"/></th>
            <th class="search_title">L/T</th>
            <th class="search_title"><spring:message code="las.page.field.statistics.req"/></th>
            <th class="search_title"><spring:message code="las.page.field.statistics.Proc"/></th>
            <th class="search_title"><spring:message code="las.page.field.statistics.return"/></th>
        <th class="search_title">L/T</th>
        </c:if>
    </tr>
    
    <c:forEach var="list" items="${result_list }" varStatus="t">
        <tr>
            <td><c:out value="${list.req_dept }"/></td><!-- 담당자 -->
            <td class='tR'><c:out value="${list.req1 }"/></td><!-- 전주실적 - 의뢰 -->
            <td class='tR'><c:out value="${list.chuli1 }"/></td><!-- 전주실적 - 처리 -->
            <td class='tR'><c:out value="${list.reply1 }"/></td><!-- 전주실적 - 회신 -->
            <td class='tR'>
                    <c:if test="${list.reply1 != '0' || list.reply_bu1 != '0'}"><c:out value="${list.lt1 }"/></c:if>
                    <c:if test="${list.reply1 == '0' && list.reply_bu1 == '0' }">-</c:if>
                </td><!-- 전주실적 - L/T -->
            
            <c:if test="${command.srch_period_gbn1=='1' }">
                <td class='tR'><c:out value="${list.req2 }"/></td><!-- 금주실적 - 의뢰 -->
                <td class='tR'><c:out value="${list.chuli2 }"/></td><!-- 금주실적 - 처리 -->
                <td class='tR'><c:out value="${list.reply2 }"/></td><!-- 금주실적 - 회신 -->
                <td class='tR'>
                    <c:if test="${list.reply2 != '0' || list.reply_bu2 != '0'}"><c:out value="${list.lt2 }"/></c:if>
                    <c:if test="${list.reply2 == '0' && list.reply_bu2 == '0' }">-</c:if>
                </td><!-- 금주실적 - L/T -->
                
                <td class='tR'><c:out value="${list.req3 }"/></td><!-- 전월실적 - 의뢰 -->
                <td class='tR'><c:out value="${list.chuli3 }"/></td><!-- 전월실적 - 처리 -->
                <td class='tR'><c:out value="${list.reply3 }"/></td><!-- 전월실적 - 회신 -->
                <td class='tR'>
                    <c:if test="${list.reply3 != '0' || list.reply_bu3 != '0'}"><c:out value="${list.lt3 }"/></c:if>
                    <c:if test="${list.reply3 == '0' && list.reply_bu3 == '0' }">-</c:if>
                </td><!-- 전월실적 - L/T -->
                
                <td class='tR'><c:out value="${list.req4 }"/></td><!-- 금월실적 - 의뢰 -->
                <td class='tR'><c:out value="${list.chuli4 }"/></td><!-- 금월실적 - 처리 -->
                <td class='tR'><c:out value="${list.reply4 }"/></td><!-- 금월실적 - 회신 -->
                <td class='tR'>
                    <c:if test="${list.reply4 != '0' || list.reply_bu4 != '0'}"><c:out value="${list.lt4 }"/></c:if>
                    <c:if test="${list.reply4 == '0' && list.reply_bu4 == '0' }">-</c:if>
                </td><!-- 금월실적 - L/T -->
                
                <td class='tR'><c:out value="${list.req5 }"/></td><!-- 금년실적 - 의뢰 -->
                <td class='tR'><c:out value="${list.chuli5 }"/></td><!-- 금년실적 - 처리 -->
                <td class='tR'><c:out value="${list.reply5 }"/></td><!-- 금년실적 - 회신 -->
                <td class='tR'>
                    <c:if test="${list.reply5 != '0' || list.reply_bu5 != '0'}"><c:out value="${list.lt5 }"/></c:if>
                    <c:if test="${list.reply5 == '0' && list.reply_bu5 == '0' }">-</c:if>
                </td><!-- 금년실적 - L/T -->
            </c:if>
        </tr>
    </c:forEach>
    
</table>
</body>
</html>