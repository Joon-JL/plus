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
            <td rowspan="3" colspan="2" align="left"><h3><spring:message code="las.page.field.statistics.listStatisticsExcelDown07"/></h3></td><!-- Contract & Legal Advice Statistics -->
        </tr>
    </table>
    
    <c:if test="${command.srch_type=='C' || command.srch_type=='L'}"> 
    <table border="1">
    	<!-- SELMS C, L -->
		<colgroup>
		<col width="10%" />
		<col width="10%" />
		<col width="5%" />
		<col width="10%" />
		<col width="10%" />
		<col width="10%" />
		<col width="10%" />
		<col width="10%" />
		<col width="10%" />
		<col width="10%" />
		<col width="5%" />
		</colgroup>	
		<thead>
			<tr>
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.contries"/></th>     <!-- Contries        -->
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tcount"/></th> <!-- Total Count     -->
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.pending"/></th>      <!-- Pending         -->
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.replied"/></th>      <!-- Replied         -->
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.rerequest"/></th>    <!-- Rerequest       -->
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.holding"/></th>      <!-- Holding         -->
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.executed"/></th>     <!-- Executed        -->
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.returned"/> </th>    <!-- Returned        -->
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.dnp"/></th>          <!-- DNP             -->
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.leftbiz"/></th><!-- Left Business   -->
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.etc"/></th>          <!-- Etc             -->
			</tr>
		</thead>
		<!-- SELMS C, L END-->
		</c:if>
		
	<c:if test="${command.srch_type=='PC'}">
	<table border="1">
		<colgroup>
		<col width="120px" />
		<col width="120px" />
		<col width="120px" />
		<col width="120px" />
		<col width="120px" />
		<col width="120px" /><!-- Admin Replied -->
		<col width="120px" />
		<col width="120px" />
		<col width="120px" />
		<col width="120px" />
		<col width="120px" />
		<col width="120px" />
		<col width="120px" />
		<col width="120px" />
		<col width="120px" />
		<col width="120px" />
		<col width="120px" />
		<col width="120px" />
		<col width="120px" />
		<col width="120px" /><!-- Executed Contract -->
		</colgroup>
		<thead>
			<tr>
				<th rowspan="2" class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle03"/></th><!-- Subsidiary -->
				<th rowspan="2" class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle04"/></th><!-- Total Count -->
				<th colspan="5" class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle18"/></th><!-- Review -->
				<th colspan="4" class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle19"/></th><!-- Conclusion -->
				<th colspan="2" class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle20"/></th><!-- Registration -->
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle21"/></th><!-- Execution -->
				<th colspan="5" class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle22"/></th><!-- Termination -->
				<th rowspan="2" class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle24"/></th><!-- Executed Contract -->
			</tr>
             <tr>
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle01"/></th><!-- Draft Saved -->
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle02"/></th><!-- Review in Progress -->
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle05"/></th><!-- DNP -->
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle23"/></th><!-- Admin Replied -->
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle06"/></th><!-- Replied -->
				
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle07"/></th><!-- Approval in Progress -->
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle01"/></th><!-- Draft Saved -->
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle05"/></th><!-- DNP -->
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle08"/></th><!-- Cancel Approval -->
				
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle09"/></th><!-- Signing -->
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle10"/></th><!-- Hard copy not Delivered -->
				
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle11"/></th><!-- In Progress -->
				
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle12"/></th><!-- Cancelled -->
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle13"/></th><!-- Expired -->
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle14"/></th><!-- Will be terminated -->
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle07"/></th><!-- Approval in Progress -->
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle16"/></th><!-- Extension Review -->
             </tr>
         </thead>
		</c:if>
		
		<c:if test="${command.srch_type=='PL'}">
		<table border="1">
			<colgroup>
			<col width="10%" />
			<col width="10%" />
			<col width="5%" />
			<col width="10%" />
			<col width="10%" />
			<col width="10%" />
			<col width="10%" />
			<col width="10%" />
			</colgroup>	
			<thead>
			<tr>
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle03"/></th><!-- Subsidiary -->
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle04"/></th><!-- Total Count -->
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle17"/></th><!-- Not Assigned -->
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle02"/></th><!-- Review in Progress -->
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle06"/></th><!-- Replied -->
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle05"/></th><!-- DNP -->
				<th class="search_title"><spring:message code="las.page.field.statistics.contlegaladvice.returned"/></th><!-- Returned -->
				<th class="search_title"><spring:message code="las.msg.succ.tempSave"/></th><!-- Saved -->
			</tr>
		</thead>
		</c:if>
		<tbody>
		
			
	<%  for(int i=0; listRow!=null && i<listRow.size(); i++){ 
	        Map rowMap = (Map)listRow.get(i) ;
	%>
		<c:if test="${command.srch_type=='C' || command.srch_type=='L'}">
			<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
				<td class="tC"><%=rowMap.get("comp_cd") %></td>
				<td class="tC overflow"><%=rowMap.get("total") %></td>
				<td class="tC"><%=rowMap.get("pending") %></td>
				<td class="tC"><%=rowMap.get("replied") %></td>
				<td class="tC"><%=rowMap.get("rerequest") %></td>
				<td class="tC"><%=rowMap.get("holding") %></td>
				<td class="tC"><%=rowMap.get("executed") %></td>
				<td class="tC"><%=rowMap.get("returned") %></td>
				<td class="tC"><%=rowMap.get("dnp") %></td>
				<td class="tC"><%=rowMap.get("left business") %></td>
				<td class="tC"><%=rowMap.get("etc_total") %></td>
			</tr>
		</c:if>
		<c:if test="${command.srch_type=='PC'}">
		<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
				<td class="tC"><%=rowMap.get("comp_cd") %></td>
				<td class="tC overflow"><%=rowMap.get("total") %></td>
				<td class="tC"><%=rowMap.get("review1") %></td>
				<td class="tC"><%=rowMap.get("review2") %></td>
				<td class="tC"><%=rowMap.get("review3") %></td>
				<!-- 2014-02-03 Kevin added. -->
				<td class="tC"><%=rowMap.get("AdminReplied") %></td>
				<td class="tC"><%=rowMap.get("review4") %></td>
				<td class="tC"><%=rowMap.get("conclusion1") %></td>
				<td class="tC"><%=rowMap.get("conclusion2") %></td>
				<td class="tC"><%=rowMap.get("conclusion3") %></td>
				<td class="tC"><%=rowMap.get("conclusion4") %></td>
				<td class="tC"><%=rowMap.get("registration1") %></td>
				<td class="tC"><%=rowMap.get("registration2") %></td>
				<td class="tC"><%=rowMap.get("execution1") %></td>
				<td class="tC"><%=rowMap.get("termination1") %></td>
				<td class="tC"><%=rowMap.get("termination2") %></td>
				<td class="tC"><%=rowMap.get("termination3") %></td>
				<td class="tC"><%=rowMap.get("termination4") %></td>
				<td class="tC"><%=rowMap.get("termination5") %></td>
				<!-- 2014-02-03 Kevin added. -->
				<td class="tC"><%=rowMap.get("ExecutedContract") %></td>
		</tr>
		</c:if>
		
		<c:if test="${command.srch_type=='PL'}">
		<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
				<td class="tC"><%=rowMap.get("comp_cd") %></td>
				<td class="tC overflow"><%=rowMap.get("total") %></td>
				<td class="tC"><%=rowMap.get("col1") %></td>
				<td class="tC"><%=rowMap.get("col2") %></td>
				<td class="tC"><%=rowMap.get("col3") %></td>
				<td class="tC"><%=rowMap.get("col4") %></td>
				<td class="tC"><%=rowMap.get("col5") %></td>
				<td class="tC"><%=rowMap.get("col6") %></td>
		</tr>
		</c:if>
	<%  } %>
	 	
		 
		
	</tbody>	
	
	</table>
    


</body>
</html>
