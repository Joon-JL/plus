<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.sds.secframework.common.util.DateUtil"%>
<%@page import="com.sec.las.statistics.dto.StatisticsForm"%>
<%
	List listRow = (List)request.getAttribute("result_list") ;
	List listCol = (List)request.getAttribute("title_list") ;
	int size = 90/(listCol.size()+1) ;
	int year = Integer.parseInt(DateUtil.year());
	
	request.setAttribute("year", year);

%>

<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="las.main.title" /></title>
<LINK href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.form.js"></script>
<script language="javascript" src="/script/secfw/jquery/calendar/jquery.mousewheel.min.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>

<script language=javascript>
	//조회
    function search() {
    	viewHiddenProgress(true);
    	
    	var f = document.frm ;
    	
    	f.method.value = "listStatisticsContract" ;
    	f.action = "/las/statistics/statistics.do" ; 
    	f.submit() ;
    }
    //엑셀 다운
    function excelDownload() {
    	var f = document.frm ;
    	f.target = "_self";
        f.method.value = "listStatisticsContractExcel" ;
        f.action = "/las/statistics/statistics.do" ; 
        f.submit() ;
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
        <h3><spring:message code='las.page.field.statistics.contract01'/></h3><!-- 년간 부서별현황 -->
    </div>
    <!-- //title -->
        
        <!-- content -->
        <div id="content">
        	<div id="content_in">
            <form name="frm" id="frm" method="post">
	        <input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />	        
	        <input type="hidden" name="method" id="method" value=""/>
	        <input type="hidden" name="srch_gbn" id="srch_gbn"  value="C" >
	        <input type="hidden" name="srch_type" id="srch_type" value="<c:out value="${command.srch_type}"/>"/>	        
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
                                            <th><spring:message code="las.page.field.statistics.searchPeriod"/><!-- 기간 --></th>
                                            <td>
	                                            <select id="srch_year" name="srch_year">
													<c:forEach var="i" begin="${year-5 }" end="${year }" step="1">
														<option value="<c:out value='${i }' />" <c:if test="${i eq command.srch_year }">selected</c:if> ><c:out value='${i }' /></option>							
													</c:forEach>
												</select> <spring:message code="las.page.field.statistics.year"/>&nbsp;&nbsp;
												<select id="srch_month" name="srch_month">
													<option value=""><spring:message code="las.page.field.common.total" /></option>						
													<c:forEach var="i" begin="1" end="12" step="1">
														<option value="<c:out value='${i }' />" <c:if test="${i eq command.srch_month }">selected</c:if> ><c:out value='${i }' /></option>
													</c:forEach>
												</select> <spring:message code="las.page.field.statistics.mm"/>
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
                        	<col width="10%"/><!-- 부서 -->
                        	<col width="<%=size%>%"/><!-- 계 -->
                        	<c:forEach var="list" items="${title_list }" varStatus="status">
                            	<col width="<%=size%>%"/>
                        	</c:forEach>
	                    </colgroup>
	                    <tr>
	                    	<th rowspan="2"><spring:message code="las.page.field.mainproject.operdiv_respman_dept_nm"/></th>
	                        <th rowspan="2"><spring:message code="las.page.field.statistics.total"/></th>	                        
 							<th colspan="<c:out value="${title_colspan }"/>"><spring:message code="las.page.field.contractmanager.consideration.cnclsnpurps"/></th>
	                    </tr>
	                    <tr>
	                    	<c:forEach var="list" items="${title_list }" varStatus="status">
		                            <th class="<c:if test="${status.index==0}">tal_lineL </c:if>sub01"><c:out value="${list.cd_nm }"/></th>
		                    </c:forEach>
		                 </tr>
					<%  for(int i=0; listRow!=null && i<listRow.size(); i++){ 
					        Map rowMap = (Map)listRow.get(i) ;
					%>
						                    
					                        <tr>
					                            <td class="<%if(i==0){%>total <%}%>tL"><%=rowMap.get("req_dept_nm") %></td>
					                            <td class="<%if(i==0){%>total <%}%>tR"><%=StringUtil.commaIn(String.valueOf(rowMap.get("total_cnt"))) %></td>
					<%      for(int j=0; j<listCol.size(); j++){ 
					            Map colMap = (Map)listCol.get(j) ;
					%>
					                            <td class="<%if(i==0){%>total <%}%>tR">
					                                <%=StringUtil.commaIn(String.valueOf(rowMap.get(colMap.get("cd")))) %>
					                            </td>
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
