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
    
    List listRow = (List)request.getAttribute("result_list") ;
    List listCol = (List)request.getAttribute("title_list") ;

    int colspan = (listCol.size()+1) * 3 ;
%>

<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=8;" />
    <style>
        .search_title {background:#CCCCCC; color:#5B4C43}
        .tbl_wrap {overflow-x:auto;overflow-y:hidden;width:100%}
    </style>
</head>
<body>
<table width="100%">
    <tr>
        <td colspan="<%=colspan-1%>" align="left"><h3><spring:message code="las.page.field.statistics.divsStats"/></h3></td><td align="right"><span style="color:red"><b><spring:message code="las.page.field.lawservice.classified"/></b></span></td>
    </tr>
</table>
    
<%--     <table border="1">
         <tr>
             <th class="search_title"><spring:message code="las.page.field.statistics.sort"/></th>
             <td colspan="2">
                 <c:if test="${command.srch_type=='C'}"> <spring:message code="las.page.field.statistics.reviewCont"/></c:if>
                 <c:if test="${command.srch_type=='L'}"><spring:message code="las.page.field.statistics.legalConsult"/></c:if>
             </td>
             <th class="search_title"><spring:message code="las.page.field.statistics.mainDept"/></th>
             <td colspan="2">
                 <c:if test="${command.srch_dmstfrgn_gbn=='H'}"><spring:message code="las.page.field.statistics.domestic"/></c:if>
                 <c:if test="${command.srch_dmstfrgn_gbn=='F'}"><spring:message code="las.page.field.statistics.abroad"/></c:if>
                 <c:if test="${command.srch_dmstfrgn_gbn=='IP'}">IP</c:if>
             </td>                           
         </tr>
         <c:if test="${command.srch_type=='C'}">
             <tr>
                 <th class="search_title"><spring:message code="las.page.field.statistics.arrange"/></th>
                 <td colspan="5"><c:if test="${command.srch_div=='T03'}"> <spring:message code="las.page.field.statistics.signStnd"/></c:if>
                     <c:if test="${command.srch_div=='T02'}"> <spring:message code="las.page.field.statistics.contStep"/></c:if>
                     <c:if test="${command.srch_div=='T01'}"> <spring:message code="las.page.field.statistics.busStep"/></c:if>
                 </td>
             </tr>
         </c:if>
         <tr>
             <th class="search_title"><spring:message code="las.page.field.statistics.searchPeriod"/></th>
             <td colspan="5">
                <c:if test="${command.srch_period_gbn2!='4'}"><c:out value="${command.srch_year}"/><spring:message code="las.page.field.statistics.year"/></c:if>
                <c:if test="${command.srch_period_gbn2=='2'}"><c:out value="${command.srch_quarter}"/><spring:message code="las.page.field.statistics.quarter"/></c:if>
                <c:if test="${command.srch_period_gbn2=='3'}"><c:out value="${command.srch_month}"/><spring:message code="las.page.field.statistics.mm"/></c:if>
                <c:if test="${command.srch_period_gbn2=='4'}"><c:out value="${command.srch_start_dt}"/> ~ <c:out value="${command.srch_end_dt}"/></c:if>
            </td>
        </tr>
    </table>
    <br/> --%>
    <table border="1">
        <tr>
             <th class="search_title" colspan="3" rowspan="3"><spring:message code="las.page.field.statistics.requesttitle.subsidiary"/></th><!-- Subsidiary -->             
             <c:if test="${command.srch_type=='C' || command.srch_type=='L'}">
              	 	<th class="search_title" colspan="<%=colspan%>"><c:out value="${title_srch }"/></th>
              </c:if>
<%--               <c:if test="${command.srch_type=='P' }"> --%>
<%--               		<th class="search_title" colspan="<%=colspan%>"><spring:message code="clm.page.msg.manage.stdCont"/><!-- 표준계약서 --></th> --%>
<%--               </c:if> --%>
        </tr>
        <tr>
            <th class="search_title" colspan="3"><spring:message code="las.page.field.statistics.total"/></th>
            <c:if test="${command.srch_type=='C' }">
                <c:forEach var="list" items="${title_list }">
                    <th colspan="3"><c:out value="${list.cd_nm}"/></th>
                </c:forEach>
            </c:if>
        </tr>
        <tr>
            <th class="search_title"><spring:message code="las.page.field.statistics.req"/></th>
            <th class="search_title"><spring:message code="las.page.field.statistics.Proc"/></th>
            <th class="search_title">L/T</th>
            
            <c:if test="${command.srch_type=='C' }">
                <c:forEach var="list" items="${title_list }">
                    <th class="search_title"><spring:message code="las.page.field.statistics.req"/></th>
                    <th class="search_title"><spring:message code="las.page.field.statistics.Proc"/></th>
                    <th class="search_title">L/T</th>
                </c:forEach>
            </c:if>
        </tr>
        
        
                        
<%  for(int i=0; listRow!=null && i<listRow.size(); i++){ 
        Map rowMap = (Map)listRow.get(i) ;
%>      <tr>
           <td style="text-align:center" colspan="3" ><%=rowMap.get("req_dept") %></td>
           <td style="text-align:right"><%=StringUtil.commaIn(String.valueOf(rowMap.get("req_total"))) %></td>
           <td style="text-align:right"><%=StringUtil.commaIn(String.valueOf(rowMap.get("chuli_total"))) %></td>
           
        <% 
        String rowMapCnt = String.valueOf(rowMap.get("reply_total")); 
        String rowMapCnt_lt = String.valueOf(rowMap.get("lt_total"));
        %>
           
           <td style="text-align:right"><%= rowMapCnt=="0" ? "-" : rowMapCnt_lt %></td>
                            
<%      for(int j=0; j<listCol.size(); j++){ 
            Map colMap = (Map)listCol.get(j) ;
%>         <td style="text-align:right"><%=StringUtil.commaIn(String.valueOf(rowMap.get("req_" + colMap.get("cd")))) %></td>
           <td style="text-align:right"><%=StringUtil.commaIn(String.valueOf(rowMap.get("chuli_" + colMap.get("cd")))) %></td>           
           
			<% 
			String colMapCnt = String.valueOf(colMap.get("reply_" + colMap.get("cd"))); 
			String colMapCnt_cd = String.valueOf(colMap.get("lt_" + colMap.get("cd")));
			%>           
           
           <td style="text-align:right"><%=colMapCnt=="0" ? "-" : rowMap.get("lt_" + colMap.get("cd")) %></td>
<%
        }
%>      </tr>
<%  } %>
    </table>

</body>
</html>