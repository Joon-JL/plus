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
    	
    	f.method.value = "listStatisticsContractByMonth" ;
    	f.action = "/las/statistics/statistics.do" ; 
    	f.submit() ;
    }
    //엑셀 다운
    function excelDownload() {
    	var f = document.frm ;
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
        <h3><spring:message code='las.page.field.statistics.contract02'/></h3><!-- 월간 부서별현황 -->
    </div>
    <!-- //title -->
        
        <!-- content -->
        <div id="content">
        	<div id="content_in">
            <form name="frm" id="frm" method="post">
	        <input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />	        
	        <input type="hidden" name="method" id="method" value=""/>
	        <input type="hidden" name="srch_gbn" id="srch_gbn"  value="M" >
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
                                            <th><spring:message code="las.page.field.statistics.searchPeriod"/><!-- 기간 --></th>
                                            <td>
	                                            <select id="srch_year" name="srch_year">
													<c:forEach var="i" begin="${year-5 }" end="${year }" step="1">
														<option value="<c:out value='${i }' />" <c:if test="${i eq command.srch_year }">selected</c:if> ><c:out value='${i }' /></option>							
													</c:forEach>
												</select> <spring:message code="las.page.field.statistics.year"/>&nbsp;&nbsp;
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
                        	<col width="9%"/><!-- 부서 -->
                        	<col width="7%"/><!-- 계 -->
                            <col width="7%"/>
                            <col width="7%"/>
                            <col width="7%"/>
                            <col width="7%"/>
                            <col width="7%"/>
                            <col width="7%"/>
                            <col width="7%"/>
                            <col width="7%"/>
                            <col width="7%"/>
                            <col width="7%"/>
                            <col width="7%"/>
                            <col width="7%"/>
	                    </colgroup>
	                    <tr>
	                    	<th><spring:message code="las.page.field.mainproject.operdiv_respman_dept_nm"/></th>
	                        <th><spring:message code="las.page.field.statistics.total"/></th>
	                    	<th><spring:message code="clm.field.signmng.m1"/><!-- 1월 --></th>
	                    	<th><spring:message code="clm.field.signmng.m2"/><!-- 2월 --></th>
	                    	<th><spring:message code="clm.field.signmng.m3"/><!-- 3월 --></th>
	                    	<th><spring:message code="clm.field.signmng.m4"/><!-- 4월 --></th>
	                    	<th><spring:message code="clm.field.signmng.m5"/><!-- 5월 --></th>
	                    	<th><spring:message code="clm.field.signmng.m6"/><!-- 6월 --></th>
	                    	<th><spring:message code="clm.field.signmng.m7"/><!-- 7월 --></th>
	                    	<th><spring:message code="clm.field.signmng.m8"/><!-- 8월 --></th>
	                    	<th><spring:message code="clm.field.signmng.m9"/><!-- 9월 --></th>
	                    	<th><spring:message code="clm.field.signmng.m10"/><!-- 10월 --></th>
	                    	<th><spring:message code="clm.field.signmng.m11"/><!-- 11월 --></th>
	                    	<th><spring:message code="clm.field.signmng.m12"/><!-- 12월 --></th>
		                 </tr>
					     <c:choose>
							<c:when test="${!empty list }" >
								<c:forEach var="list" items="${list}" varStatus="i">
								     <tr>
								        <td <c:choose><c:when test="${i.count == 1 }">class="total tL"</c:when><c:otherwise>class="tL"</c:otherwise></c:choose>><c:out value='${list.req_dept_nm}'/></td>
								        <td <c:choose><c:when test="${i.count == 1 }">class="total tR"</c:when><c:otherwise>class="tR"</c:otherwise></c:choose>><c:out value='${list.total_cnt}'/></td>
								        <td <c:choose><c:when test="${i.count == 1 }">class="total tR"</c:when><c:otherwise>class="tR"</c:otherwise></c:choose>><c:out value='${list.mon_01}'/></td>
								        <td <c:choose><c:when test="${i.count == 1 }">class="total tR"</c:when><c:otherwise>class="tR"</c:otherwise></c:choose>><c:out value='${list.mon_02}'/></td>
								        <td <c:choose><c:when test="${i.count == 1 }">class="total tR"</c:when><c:otherwise>class="tR"</c:otherwise></c:choose>><c:out value='${list.mon_03}'/></td>
								        <td <c:choose><c:when test="${i.count == 1 }">class="total tR"</c:when><c:otherwise>class="tR"</c:otherwise></c:choose>><c:out value='${list.mon_04}'/></td>
								        <td <c:choose><c:when test="${i.count == 1 }">class="total tR"</c:when><c:otherwise>class="tR"</c:otherwise></c:choose>><c:out value='${list.mon_05}'/></td>
								        <td <c:choose><c:when test="${i.count == 1 }">class="total tR"</c:when><c:otherwise>class="tR"</c:otherwise></c:choose>><c:out value='${list.mon_06}'/></td>
								        <td <c:choose><c:when test="${i.count == 1 }">class="total tR"</c:when><c:otherwise>class="tR"</c:otherwise></c:choose>><c:out value='${list.mon_07}'/></td>
								        <td <c:choose><c:when test="${i.count == 1 }">class="total tR"</c:when><c:otherwise>class="tR"</c:otherwise></c:choose>><c:out value='${list.mon_08}'/></td>
								        <td <c:choose><c:when test="${i.count == 1 }">class="total tR"</c:when><c:otherwise>class="tR"</c:otherwise></c:choose>><c:out value='${list.mon_09}'/></td>
								        <td <c:choose><c:when test="${i.count == 1 }">class="total tR"</c:when><c:otherwise>class="tR"</c:otherwise></c:choose>><c:out value='${list.mon_10}'/></td>
								        <td <c:choose><c:when test="${i.count == 1 }">class="total tR"</c:when><c:otherwise>class="tR"</c:otherwise></c:choose>><c:out value='${list.mon_11}'/></td>
								        <td <c:choose><c:when test="${i.count == 1 }">class="total tR"</c:when><c:otherwise>class="tR"</c:otherwise></c:choose>><c:out value='${list.mon_12}'/></td>
								     </tr>
								 </c:forEach>
							</c:when>
						</c:choose>
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
