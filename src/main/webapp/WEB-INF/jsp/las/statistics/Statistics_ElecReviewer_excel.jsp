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
    String localeCode = (String)session.getAttribute("secfw.session.language_flag");
    int colspan = command.getSrch_period_gbn1().equals("1") ? 5 : 1 ; 
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
<c:set var="localeCode"><%=localeCode %></c:set>
<table width="100%">
    <tr>
        <td colspan="<%=colspan%>" align="left"><h3><spring:message code="las.page.field.statistics.eachStats"/></h3></td><td align="right"></td>
    </tr>
</table>
<table class="list_basic" border="1">
	<tr>
		<th rowspan="2" class="search_title"><spring:message code="las.page.field.user.comp" text="noMsg"/></th>
	    <th rowspan="2" class="search_title"><spring:message code="las.page.field.statistics.pic" text="noMsg"/></th>	                        
		<th colspan="3" class="search_title"><c:out value="${title_srch}"/></th>	
	    <c:if test="${command.srch_period_gbn1=='1' }">	                     
	    <th colspan="3" class="search_title"><spring:message code="las.page.field.statistics.perform4" text="noMsg"/></th>
	    <th colspan="3" class="search_title"><spring:message code="las.page.field.statistics.perform5" text="noMsg"/></th>	                       
	    </c:if>
	</tr>
	<tr>
		<th class="search_title"><spring:message code="las.page.field.statistics.req" text="noMsg"/></th>
	    <th class="search_title"><spring:message code="las.page.field.statistics.reply" text="noMsg"/></th>	                        
	    <th class="search_title"><spring:message code="las.page.field.statistics.leadtime" text="noMsg"/></th>
	    <c:if test="${command.srch_period_gbn1=='1' }">	           
	    <th class="search_title"><spring:message code="las.page.field.statistics.req" text="noMsg"/></th>
	    <th class="search_title"><spring:message code="las.page.field.statistics.reply" text="noMsg"/></th>	                        
	    <th class="search_title"><spring:message code="las.page.field.statistics.leadtime" text="noMsg"/></th>
	    <th class="search_title"><spring:message code="las.page.field.statistics.req" text="noMsg"/></th>
	    <th class="search_title"><spring:message code="las.page.field.statistics.reply" text="noMsg"/></th>	                        
	    <th class="search_title"><spring:message code="las.page.field.statistics.leadtime" text="noMsg"/></th>
	    </c:if>	                       
	</tr>
	<c:forEach var="list" items="${resultList}" varStatus="status">
	<tr>
	<c:choose>
		<c:when test="${status.first}">
		<td style="text-align:center" colspan="2"><spring:message code="las.page.field.statistics.total" text="noMsg"/></td>
		</c:when>
		<c:otherwise>
		<td style="text-align:center">
		<c:choose>
			<c:when test="${localeCode eq 'ko' }"><c:out value="${list.cd_abbr_nm}"/></c:when>
		    <c:when test="${localeCode eq 'en' }"><c:out value="${list.cd_abbr_nm_eng}"/></c:when>
		</c:choose> 
		</td>
		<td style="text-align:center">
		<c:choose>
			<c:when test="${localeCode eq 'ko' }"><c:out value="${list.user_real_nm}"/></c:when>
		 	<c:when test="${localeCode eq 'en' }"><c:out value="${list.user_real_nm_eng}"/></c:when> 
		</c:choose>
		</td>
		</c:otherwise>
	</c:choose>
	    <td style="text-align:right"><c:out value="${list.req1}"/></td><!-- 금주실적 - 의뢰 -->
		<td style="text-align:right"><c:out value="${list.reply1}"/></td><!-- 금주실적 - 회신 -->
	    <td style="text-align:right">
        <c:if test="${list.reply1 ne '0'}"><c:out value="${list.lt1 }"/></c:if>
        <c:if test="${list.reply1 eq '0' && list.reply1 eq '0' }">-</c:if>
        </td><!-- 금주실적 - L/T -->
	    <c:if test="${command.srch_period_gbn1=='1' }">
        <td style="text-align:right"><c:out value="${list.req2}"/></td><!-- 금주실적 - 의뢰 -->
	    <td style="text-align:right"><c:out value="${list.reply2}"/></td><!-- 금주실적 - 회신 -->
	    <td style="text-align:right">
        <c:if test="${list.reply2 ne '0'}"><c:out value="${list.lt2 }"/></c:if>
        <c:if test="${list.reply2 eq '0' && list.reply2 eq '0' }">-</c:if>
        </td><!-- 금월실적 - L/T -->
        
        <td style="text-align:right"><c:out value="${list.req3}"/></td><!-- 금주실적 - 의뢰 -->
	    <td style="text-align:right"><c:out value="${list.reply3}"/></td><!-- 금주실적 - 회신 -->
	    <td style="text-align:right">
        <c:if test="${list.reply3 ne '0'}"><c:out value="${list.lt3 }"/></c:if>
        <c:if test="${list.reply3 eq '0' && list.reply3 eq '0' }">-</c:if>
        </td><!-- 금년실적 - L/T -->
        </c:if>
	    </tr>
        </c:forEach>                     	
	    </table>
</body>
</html>