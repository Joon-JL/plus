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
    <tr> <!-- Lapsed time by Request -->
        <td colspan="19" align="left"><h3><spring:message code="las.page.field.statistics.lap.title"/></h3></td><td align="right"><span style="color:red"><b><spring:message code="las.page.field.lawservice.classified"/></b></span></td>
    </tr>
</table>

<table border="1">
    <tr>
       <th class="search_title"><spring:message code="las.page.field.statistics.lap.subsidiary"/></th><!-- Subsidiary -->
       <th class="search_title"><spring:message code="las.page.field.statistics.lap.legteam"/></th><!-- Legal team -->
       <th class="search_title"><spring:message code="las.page.field.statistics.lap.reviewer"/></th><!-- Reviewer -->
       <th class="search_title"><spring:message code="las.page.field.statistics.lap.os"/></th><!-- (O/S) -->
       <th class="search_title"><spring:message code="las.page.field.statistics.lap.requester"/></th><!-- Requester -->
       <th class="search_title"><spring:message code="las.page.field.statistics.lap.request_title"/></th><!-- Request Title -->
       <th class="search_title"><spring:message code="las.page.field.statistics.lap.rviewinproc"/></th><!-- Review in progress -->
       <th class="search_title"><spring:message code="las.page.field.statistics.lap.firstreplied"/></th><!-- First replied -->
       <th class="search_title"><spring:message code="las.page.field.statistics.lap.finalreplied"/></th><!-- Final replied -->
       <th class="search_title"><spring:message code="las.page.field.statistics.lap.approval"/></th><!-- Approval -->
       <th class="search_title"><spring:message code="las.page.field.statistics.lap.regist"/></th><!-- Registration -->
       <th class="search_title"><spring:message code="las.page.field.statistics.lap.executed"/></th><!-- Executed -->
       <th class="search_title"><spring:message code="las.page.field.statistics.lap.termination"/></th><!-- Termination -->
       <th class="search_title"><spring:message code="las.page.field.statistics.lap.hoiding"/></th><!-- Holding -->
       <th class="search_title"><spring:message code="las.page.field.statistics.lap.admreplied"/></th><!-- Admin Replied -->
       <th class="search_title"><spring:message code="las.page.field.statistics.lap.closed"/></th><!-- Closed -->
       <th class="search_title"><spring:message code="las.page.field.statistics.lap.request"/></th><!-- Request -->
       <th class="search_title"><spring:message code="las.page.field.statistics.lap.firstreplied"/></th><!-- First replied -->
       <th class="search_title"><spring:message code="las.page.field.statistics.lap.finalreplied"/></th><!-- Final replied -->
       <th class="search_title"><spring:message code="las.page.field.statistics.lap.totallt"/></th><!-- Total L/T -->
   </tr>
    
    <c:forEach var="list" items="${result_list }" varStatus="t">
      	<c:set var="css_chg" value="" />
      	<c:set var="compcd_chg" value="${list.comp_cd }" />
      	<c:if test="${list.dept_cd eq null}">
      		<c:set var="css_chg" value="background:#F7F7F7; color:#4c382a;" />
      		<c:set var="compcd_chg" value="Total" />
       	</c:if>	
     	<c:if test="${list.comp_cd eq null}">
         		<c:set var="css_chg" value="background:#F7F7F7; color:#4c382a;" />
         		<c:set var="compcd_chg" value="SubTotal" />
     	</c:if>	

		<c:set var="v_chk1" value="V" /><c:set var="v_chk2" value="V" /><c:set var="v_chk3" value="V" />
        <c:set var="v_chk4" value="V" /><c:set var="v_chk5" value="V" /><c:set var="v_chk6" value="V" />
        <c:set var="v_chk7" value="V" /><c:set var="v_chk8" value="V" /><c:set var="v_chk9" value="V" />
        <c:set var="v_chk10" value="V" />
        
        <c:if test="${list.review_in_proc eq 0}"><c:set var="v_chk1" value="" /></c:if>
        <c:if test="${list.f_replied eq 0}"><c:set var="v_chk2" value="" /></c:if>
        <c:if test="${list.last_replied eq 0}"><c:set var="v_chk3" value="" /></c:if>
        <c:if test="${list.approval eq 0}"><c:set var="v_chk4" value="" /></c:if>
        <c:if test="${list.registration eq 0}"><c:set var="v_chk5" value="" /></c:if>
        <c:if test="${list.executed eq 0}"><c:set var="v_chk6" value="" /></c:if>
        <c:if test="${list.termination eq 0}"><c:set var="v_chk7" value="" /></c:if>
        <c:if test="${list.holding eq 0}"><c:set var="v_chk8" value="" /></c:if>
        <c:if test="${list.adm_replied eq 0}"><c:set var="v_chk9" value="" /></c:if>
        <c:if test="${list.closed eq 0}"><c:set var="v_chk10" value="" /></c:if>
       
        <c:if test="${list.dept_cd eq null || list.comp_cd eq null}">
    		<c:set var="v_chk1" value="${list.review_in_proc}" />
    		<c:set var="v_chk2" value="${list.f_replied}" />
    		<c:set var="v_chk3" value="${list.last_replied}" />
    		<c:set var="v_chk4" value="${list.approval}" />
    		<c:set var="v_chk5" value="${list.registration}" />
    		<c:set var="v_chk6" value="${list.executed}" />
    		<c:set var="v_chk7" value="${list.termination}" />
    		<c:set var="v_chk8" value="${list.holding}" />
    		<c:set var="v_chk9" value="${list.adm_replied}" />
    		<c:set var="v_chk10" value="${list.closed}" />
      	</c:if>	
              	
         <tr>
             <td class='tC' style='<c:out value="${css_chg}"/>'><c:out value="${compcd_chg }"/></td><!-- Subsidiary -->
             <td class='tC' style='<c:out value="${css_chg}"/>'><c:out value="${list.dept_cd }"/></td><!-- Legal team -->
             <td class='tC' style='<c:out value="${css_chg}"/>'><c:out value="${list.respman_nm }"/></td><!-- Reviewer -->
             <td class='tC' style='<c:out value="${css_chg}"/>'><c:out value="${list.main_cnsd_yn }"/></td><!-- (O/S) -->
             <td class='tC' style='<c:out value="${css_chg}"/>'><c:out value="${list.reqman_nm }"/></td><!-- Requester -->
             <td class='tL' style='<c:out value="${css_chg}"/>'><c:out value="${list.req_title }"/></td><!-- Request Title -->
             
             <td class='tC' style='<c:out value="${css_chg}"/>'><c:out value="${v_chk1}"/></td><!-- Review in progress -->
             <td class='tC' style='<c:out value="${css_chg}"/>'><c:out value="${v_chk2 }"/></td><!-- First replied -->
             <td class='tC' style='<c:out value="${css_chg}"/>'><c:out value="${v_chk3 }"/></td><!-- Final replied -->
             <td class='tC' style='<c:out value="${css_chg}"/>'><c:out value="${v_chk4 }"/></td><!-- Approval -->
             <td class='tC' style='<c:out value="${css_chg}"/>'><c:out value="${v_chk5 }"/></td><!-- Registration -->
             <td class='tC' style='<c:out value="${css_chg}"/>'><c:out value="${v_chk6 }"/></td><!-- Executed -->
             <td class='tC' style='<c:out value="${css_chg}"/>'><c:out value="${v_chk7 }"/></td><!-- Termination -->
             <td class='tC' style='<c:out value="${css_chg}"/>'><c:out value="${v_chk8 }"/></td><!-- Holding -->
             <td class='tC' style='<c:out value="${css_chg}"/>'><c:out value="${v_chk9 }"/></td><!-- Admin Replied -->
             <td class='tC' style='<c:out value="${css_chg}"/>'><c:out value="${v_chk10}"/></td><!-- Closed -->
             
             <td class='tC' style='<c:out value="${css_chg}"/>'><c:out value="${list.req_dt }"/></td><!-- Request -->
             <td class='tC' style='<c:out value="${css_chg}"/>'><c:out value="${list.f_replied_dt }"/></td><!-- First replied -->
             <td class='tC' style='<c:out value="${css_chg}"/>'><c:out value="${list.last_replied_dt }"/></td><!-- Final replied -->
             <td class='tR' style='<c:out value="${css_chg}"/>'><c:out value="${list.lt1 }"/></td><!-- Total L/T -->
         </tr>
          
     </c:forEach>
    
</table>
</body>
</html>