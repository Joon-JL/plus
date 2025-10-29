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
	List listCol = (List)request.getAttribute("title_list") ;
	StatisticsForm command = (StatisticsForm)request.getAttribute("command") ;
	int rowspan = command.getSrch_type().equals("C") ? 3 : 2 ;
	int colspan = command.getSrch_type().equals("C") ? 2 : 1 ;
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
    		<c:if test="${command.srch_type=='C' || command.srch_type=='L'}">
            	<td align="left"><span style="color:red"><b><spring:message code="secfw.page.field.approval.approvalType.confidential"/></b></span></td>
        	</c:if>
        	<c:if test="${command.srch_type=='P'}">
        		<td align="left"><span style="color:red"><b><spring:message code="secfw.page.field.approval.approvalType.confidential"/></b></span></td>
        	</c:if>
    	</tr>
        <tr>
            <c:if test="${command.srch_type=='C' || command.srch_type=='L'}">
            	<td rowspan="<%=rowspan%>" colspan="<%=colspan*listCol.size()%>" align="left"><h3><spring:message code="las.page.field.statistics.picState"/></h3></td>
        	</c:if>
        	<c:if test="${command.srch_type=='P'}">
        		<td rowspan="<%=rowspan%>" colspan="2" align="left"><h3><spring:message code="las.page.field.statistics.picState"/></h3></td>
        	</c:if>
        </tr>
    </table>

<%--     <table border="1">
         <tr>
             <th class="search_title"><spring:message code="las.page.field.statistics.sort"/></th>
             <td colspan="3">
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
                 <td colspan="6"><c:if test="${command.srch_div=='T03'}"> <spring:message code="las.page.field.statistics.signStnd"/></c:if>
                     <c:if test="${command.srch_div=='T02'}"> <spring:message code="las.page.field.statistics.contStep"/></c:if>
                     <c:if test="${command.srch_div=='T01'}"> <spring:message code="las.page.field.statistics.busStep"/></c:if>
                 </td>
             </tr>
         </c:if>
         <tr>
             <th class="search_title"><spring:message code="las.page.field.statistics.searchPeriod"/></th>
             <td colspan="6">
                 <c:out value="${command.srch_start_dt}"/> ~ <c:out value="${command.srch_end_dt}"/>
            </td>
        </tr>
    </table> --%>
    <br/>
    <table border="1">
	
		<tr>
		     <c:if test="${command.srch_type=='C' || command.srch_type=='L'}">
			    <th rowspan="<%=rowspan%>" class="search_title">Company</th><!-- Reviewer -->
			    <th rowspan="<%=rowspan%>" class="search_title"><spring:message code="las.page.field.statistics.reviewer.title"/></th><!-- Reviewer -->
			    <th rowspan="<%=rowspan%>" class="search_title"><spring:message code="las.page.field.statistics.total"/></th>
			    <th colspan="<%=colspan*listCol.size()%>" class="search_title"><c:out value="${title_srch }"/> </th>
			  </c:if>
		    
<%--          <c:if test="${command.srch_type=='P'}"> --%>
<%--             <th><spring:message code="las.page.field.statistics.personInCharge"/></th> --%>
<%--         	<th><spring:message code="las.page.field.statistics.total"/></th>	                         --%>
<%--             <th><spring:message code="clm.page.msg.manage.stdCont"/><!-- 표준계약서 --></th> --%>
<%--         </c:if>	     --%>
		</tr>
		<c:if test="${command.srch_type=='C' || command.srch_type=='L'}">
		<tr>
		    <c:forEach var="list" items="${title_list }" varStatus="status">
		        <th class="search_title" colspan="<%=colspan%>"><c:out value="${list.cd_nm }"/></th>
		    </c:forEach>
		</tr>
		 </c:if>
		 <c:if test="${command.srch_type=='C' || command.srch_type=='L'}">
<%  if(command.getSrch_type().equals("C")){ %>
        <tr>
            <c:forEach var="list" items="${title_list }" varStatus="status">
                <th class="search_title"><spring:message code="las.page.field.statistics.,mainTh"/></th>
                <th class="search_title"><spring:message code="las.page.field.statistics.vice"/></th>
            </c:forEach>
        </tr>
<%  }%>		
	<%  for(int i=0; listRow!=null && i<listRow.size(); i++){ 
		   Map rowMap = (Map)listRow.get(i) ;
	%>
	
		<tr>
			<td clss="tC"><%=StringUtil.bvl(rowMap.get("comp_cd"),"Grand Total") %></td>
		    <td clss="tC"><%=StringUtil.bvl(rowMap.get("user_nm"),"Total") %></td>
		    <td class="tR"><%=StringUtil.commaIn(String.valueOf(rowMap.get("total_cnt"))) %></td>
	<%      for(int j=0; j<listCol.size(); j++){ 
		   Map colMap = (Map)listCol.get(j) ;
	%>
	       <td class="tR"><%=StringUtil.commaIn(String.valueOf(rowMap.get(colMap.get("cd")))) %></td>
<%              if(command.getSrch_type().equals("C")){ %>	       
	       <td class="tR"><%=StringUtil.commaIn(String.valueOf(rowMap.get(colMap.get("cd")+"_bu"))) %></td>
<%              } %>
	<%
            }
	%>                                
	   </tr>
	<%  } %>
	</c:if>
				   		 <!-- 표준계약서 -->
<%-- 			   		 <c:if test="${command.srch_type=='P'}"> --%>
<%-- 			   		 	<c:forEach var="list" items="${result_list}" varStatus="status"> --%>
<!-- 			   		 		<tr> -->
<%-- 			   		 			<td class="<c:if test="${status.first}">total</c:if> tC"><c:out value='${list.user_nm}'/></td> --%>
<%-- 			   		 			<td class="<c:if test="${status.first}">total</c:if> tC"><c:out value='${list.total_cnt}'/></td> --%>
<%-- 			   		 			<td class="<c:if test="${status.first}">total</c:if> tC"><c:out value='${list.a20}'/></td> --%>
<!-- 			   		 		</tr>	                            	 -->
<%-- 	                    </c:forEach> --%>
<%-- 					</c:if> --%>
	
	</table>
    


</body>
</html>
