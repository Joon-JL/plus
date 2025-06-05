<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@page import="com.sds.secframework.common.util.StringUtil"%>
<%@page import="com.sec.las.statistics.dto.StatisticsForm"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%
	List listRow = (List)request.getAttribute("result_list") ;
	
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
    		<td align="left"><span style="color:red"><b><spring:message code="secfw.page.field.approval.approvalType.confidential"/></b></span></td>
    	</tr>
        <tr>
            <td rowspan="3" colspan="2" align="left"><h3><spring:message code="las.page.field.statistics.listStatisticsExcelDown05"/></h3></td>
        </tr>
    </table>
     
    <table border="1">
	
		<c:if test="${command.srch_type=='C'}">
						<colgroup>
						<c:choose>
							<c:when test="${langCd=='en'}">
								<col width="180px"/>
								<col width="120px"/>
								<col width="120px"/>
								<col width="120px"/>
								<col width="70px"/>
								<col width="120px"/>
								<col width="120px"/>
								<col width="140px"/>
								<col width="140px"/>
								<col width="140px"/>
								
								<col width="130px"/>
								<col width="160px"/>
								<col width="165px"/>
								<col width="140px"/>
								<col width="140px"/>
								<col width="110px"/>
								<col width="163px"/>
								<col width="170px"/>
								<col width="240px"/>
								<col width="210px"/>
							</c:when>
							<c:when test="${langCd=='fr'}">
								<col width="180px"/>
								<col width="120px"/>
								<col width="120px"/>
								<col width="120px"/>
								<col width="70px"/>
								<col width="120px"/>
								<col width="120px"/>
								<col width="140px"/>
								<col width="140px"/>
								<col width="140px"/>
								
								<col width="130px"/>
								<col width="160px"/>
								<col width="175px"/>
								<col width="170px"/>
								<col width="215px"/>
								<col width="140px"/>
								<col width="300px"/>
								<col width="200px"/>
								<col width="250px"/>
								<col width="200px"/>
							</c:when>
							<c:when test="${langCd=='de'}">
								<col width="180px"/>
								<col width="120px"/>
								<col width="120px"/>
								<col width="120px"/>
								<col width="70px"/>
								<col width="120px"/>
								<col width="120px"/>
								<col width="140px"/>
								<col width="140px"/>
								<col width="140px"/>
								
								<col width="130px"/>
								<col width="160px"/>
								<col width="165px"/>
								<col width="140px"/>
								<col width="140px"/>
								<col width="110px"/>
								<col width="163px"/>
								<col width="170px"/>
								<col width="240px"/>
								<col width="210px"/>
							</c:when>
							<c:otherwise>
								<col width="180px"/>
								<col width="120px"/>
								<col width="120px"/>
								<col width="120px"/>
								<col width="70px"/>
								<col width="120px"/>
								<col width="120px"/>
								<col width="140px"/>
								<col width="140px"/>
								<col width="140px"/>
								
								<col width="130px"/>
								<col width="160px"/>
								<col width="165px"/>
								<col width="140px"/>
								<col width="140px"/>
								<col width="110px"/>
								<col width="163px"/>
								<col width="170px"/>
								<col width="240px"/>
								<col width="210px"/>
							</c:otherwise>
						</c:choose>
						</colgroup>
					
						<tr>
							<th rowspan="2" class="search_title" style='height:49px'><spring:message code="las.page.field.statistics.requesttitle.title"/></th><!-- Request title -->
							<th rowspan="2" class="search_title"><spring:message code="las.page.field.statistics.requesttitle.requester"/></th><!-- Requestor -->
							<th rowspan="2" class="search_title"><spring:message code="las.page.field.statistics.requesttitle.reviewer"/></th><!-- Reviewer -->
							<th rowspan="2" class="search_title"><spring:message code="las.page.field.statistics.requesttitle.legalteam"/></th><!-- Legal Team -->
							<th rowspan="2" class="search_title"><spring:message code="las.page.field.statistics.requesttitle.subsidiary"/></th><!-- Subsidiary -->
							<th colspan="3" class="search_title"><spring:message code="las.page.field.statistics.requesttitle.typeofcont"/></th><!-- Contract Type-->
							<th rowspan="2" class="search_title"><spring:message code="clm.page.title.customer.title"/></th><!-- Counterparty -->
							<th rowspan="2" class="search_title"><spring:message code="las.page.field.statistics.requesttitle.contterm"/></th><!-- Contract Term -->
							<th rowspan="2" class="search_title"><spring:message code="las.page.field.statistics.requesttitle.value"/></th><!-- Value -->
							<th rowspan="2" class="search_title"><spring:message code="las.page.field.statistics.requesttitle.1strqstdate"/></th><!-- First Request date -->
							<th rowspan="2" class="search_title"><spring:message code="las.page.field.statistics.requesttitle.reqdateforreply"/></th><!-- Requested Reply date -->
							<th rowspan="2" class="search_title"><spring:message code="las.page.field.statistics.requesttitle.reqalloc"/></th><!-- Request → Assignment -->
							<th rowspan="2" class="search_title"><spring:message code="las.page.field.statistics.requesttitle.allocresp"/></th><!-- Assignment → Final Reply -->
							<th rowspan="2" class="search_title"><spring:message code="las.page.field.statistics.requesttitle.reqreply"/></th><!-- Request → Final Reply -->
							<th rowspan="2" class="search_title"><spring:message code="las.page.field.statistics.requesttitle.lastreplyapp"/></th><!-- Final Reply → Approval -->
							<th rowspan="2" class="search_title"><spring:message code="las.page.field.statistics.requesttitle.appuploadcopy"/></th><!-- Approval → Upload Hard copy -->
							<th rowspan="2" class="search_title"><spring:message code="las.page.field.statistics.requesttitle.uploadcopy"/></th><!-- Upload Hard copy → Original contract Received -->
							<th rowspan="2" class="search_title"><spring:message code="las.page.field.statistics.requesttitle.reqgetorg"/></th><!-- Request → Origianl contract Received -->
						</tr>
						<tr>
							<th class="search_title"><spring:message code="las.page.field.statistics.requesttitle.busistage"/></th><!-- Business -->
							<th class="search_title"><spring:message code="las.page.field.statistics.requesttitle.contstage"/></th><!-- Contract -->
							<th class="search_title"><spring:message code="las.page.field.statistics.requesttitle.purpofcont"/></th><!-- Purpose of Contract -->
						</tr>
					</c:if>	
					
					<c:if test="${command.srch_type=='L'}">
						<colgroup>
						<c:choose>
							<c:when test="${langCd=='en'}">
								<col width="300px"/>
								<col width="120px"/>
								<col width="120px"/>
								<col width="120px"/>
								<col width="70px"/>
								<col width="170px"/>
								<col width="140px"/>
								
								<col width="140px"/>
								<col width="160px"/>
								<col width="165px"/>
								<col width="170px"/>
							</c:when>
							<c:when test="${langCd=='fr'}">
								<col width="300px"/>
								<col width="120px"/>
								<col width="120px"/>
								<col width="120px"/>
								<col width="70px"/>
								<col width="170px"/>
								<col width="140px"/>
								
								<col width="140px"/>
								<col width="160px"/>
								<col width="175px"/>
								<col width="170px"/>
							</c:when>
							<c:when test="${langCd=='de'}">
								<col width="300px"/>
								<col width="120px"/>
								<col width="120px"/>
								<col width="120px"/>
								<col width="70px"/>
								<col width="170px"/>
								<col width="140px"/>
								
								<col width="140px"/>
								<col width="160px"/>
								<col width="165px"/>
								<col width="170px"/>
							</c:when>
							<c:otherwise>
								<col width="300px"/>
								<col width="120px"/>
								<col width="120px"/>
								<col width="120px"/>
								<col width="70px"/>
								<col width="170px"/>
								<col width="140px"/>
								
								<col width="140px"/>
								<col width="160px"/>
								<col width="165px"/>
								<col width="170px"/>
							</c:otherwise>
						</c:choose>
						</colgroup>
						<tr>
							<th class="search_title" style='height:49px'><spring:message code="las.page.field.statistics.requesttitle.title"/></th><!-- Request title -->
							<th class="search_title"><spring:message code="las.page.field.statistics.requesttitle.requester"/></th><!-- Requestor -->
							<th class="search_title"><spring:message code="las.page.field.statistics.requesttitle.reviewer"/></th><!-- Reviewer -->
							<th class="search_title"><spring:message code="las.page.field.statistics.requesttitle.legalteam"/></th><!-- Legal Team -->
							<th class="search_title"><spring:message code="las.page.field.statistics.requesttitle.subsidiary"/></th><!-- Subsidiary -->
							<th class="search_title"><spring:message code="las.page.field.statistics.requesttitle.legaltype"/></th><!-- Legal advice Type -->
							<th class="search_title"><spring:message code="las.page.field.statistics.requesttitle.1strqstdate"/></th><!-- First Request date -->
							<th class="search_title"><spring:message code="las.page.field.statistics.requesttitle.reqalloc"/></th><!-- Request → Assignment -->
							<th class="search_title"><spring:message code="las.page.field.statistics.requesttitle.allocresp"/></th><!-- Assignment → Final Reply -->
							<th class="search_title"><spring:message code="las.page.field.statistics.requesttitle.reqreply"/></th><!-- Request → Final Reply -->
							<th class="search_title"><spring:message code="las.page.field.statistics.requesttitle.reqgetorg"/></th><!-- Request → Origianl contract Received -->
						</tr>
						
					</c:if>	
						
							
					<%  for(int i=0; listRow!=null && i<listRow.size(); i++){ 
					        Map rowMap = (Map)listRow.get(i) ;
					%>
						<tr>
							<td class="tC" title="<%=rowMap.get("req_title") %>"><%=rowMap.get("req_title") %></td>
							<td class="tC" title="<%=rowMap.get("reqman_nm") %>"><%=rowMap.get("reqman_nm") %></td>
							<td class="tC" title="<%=rowMap.get("cnsdmans") %>"><%=rowMap.get("cnsdmans") %></td>
							<td class="tC"><%=rowMap.get("cnsd_dept_nm") %></td>
							<td class="tC"><%=rowMap.get("comp_nm") %></td>
							<td class="tC"><%=rowMap.get("cnt_type1") %></td>
							<c:if test="${command.srch_type=='C'}">
								<td class="tC"><%=rowMap.get("cnt_type2") %></td>
								<td class="tC"><%=rowMap.get("cnt_type3") %></td>
								<td class="tC"><%=rowMap.get("cntrt_oppnt_nm") %></td>
								<td class="tC"><%=rowMap.get("cntrtperiod_startday") %> ~ <br><%=rowMap.get("cntrtperiod_endday") %></td>
								<td class="tR"><%=StringUtil.commaIn(String.valueOf(rowMap.get("cntrt_amt")))  %></td>
							</c:if>
							<td class="tC"><%=rowMap.get("first_req_dt2") %></td>
							<c:if test="${command.srch_type=='C'}">
								<td class="tC"><%=rowMap.get("re_dt2") %></td>
							</c:if>
							<td class="tC"><%=rowMap.get("lt1") %></td>
							<td class="tC"><%=rowMap.get("lt2") %></td>
							<td class="tC"><%=rowMap.get("lt3") %></td>
							<c:if test="${command.srch_type=='C'}">
								<td class="tC"><%=rowMap.get("lt4") %></td>
								<td class="tC"><%=rowMap.get("lt5") %></td>
								<td class="tC"><%=rowMap.get("lt6") %></td>
							</c:if>
							<td class="tC"><%=rowMap.get("lt7") %></td>                    
						</tr>
					<%  } %>
					 	
						</tr>
	
	</table>
    


</body>
</html>
