<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.sec.las.statistics.dto.StatisticsForm"%>
<%
StatisticsForm command = (StatisticsForm)request.getAttribute("command") ;
String localeCode = (String)session.getAttribute("secfw.session.language_flag");
%>

<%-- 
/**
 * 파  일  명 : Statistics_consideration_l.jsp
 * 프로그램명 : 검토의뢰건수 통계화면 
 * 설      명 : 관련사통계 검토의뢰 건수 통계 조회 화면
 * 작  성  자 : 박정훈
 * 작  성  일 : 2013.06
 */
--%>

<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="las.page.field.statistics.consider" text="noMsg"/></title>
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">

<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.form.js"></script>
<script language="javascript" src="/script/secfw/jquery/calendar/jquery.mousewheel.min.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>

<script language=javascript>
 $(document).ready(function(){
	 
	 $('#tab2').hide();
     $('#wTab').hide();
     $('#wTab2').hide();
	 
	 if("<c:out value='${command.srch_div}'/>" == "M"){
		 $('#DetailTab2').removeClass('on');
         $('#DetailTab1').addClass('on');
         $('#tab1').show();
         $('#tab2').hide();
         
         $('#wTab').hide();
         $('#wTab2').hide();
         
	 }else if("<c:out value='${command.srch_div}'/>" == "W"){
		 $('#DetailTab1').removeClass('on');
         $('#DetailTab2').addClass('on');
         
         $('#tab2').show();
         $('#tab1').hide();
         
         $('#wTab').show();
         $('#wTab2').show();
	 }
     
     
     //월별TAB click function
     $('#DetailTab1').bind('click', function(){
         $('#DetailTab2').removeClass('on');
         $(this).addClass('on');
         $('#tab1').show();
         $('#tab2').hide();
         
         $('#wTab').hide();
         $('#wTab2').hide();
         
         document.frm.srch_div.value = "M" ;
     });
   	//주차별TAB click function
     $('#DetailTab2').bind('click', function(){
         $('#DetailTab1').removeClass('on');
         $(this).addClass('on');
         
         $('#tab2').show();
         $('#tab1').hide();
         
         $('#wTab').show();
         $('#wTab2').show();
         document.frm.srch_div.value = "W" ;
         $("#srch_start_y > option[value=<c:out value='${currentW -4 }'/>]").attr("selected", "true");
     });
  
     $("#srch_end_y > option[value=<c:out value='${currentW}'/>]").attr("selected", "ture");

     
     /*
     $('#srch_start_y').change(function(){
    	 if($("#srch_start_y option:selected").val() > $("#srch_end_y option:selected").val()){
        	 alert("조회주차는 시작주차가 종료주차보다 클 수 없습니다");
         }
     });
     
     $('#srch_end_y').change(function(){
         if($("#srch_start_y option:selected").val() > $("#srch_end_y option:selected").val()){
        	 alert("조회주차는 시작주차가 종료주차보다 클 수 없습니다");
        	 $("#srch_end_y > option[value="+$('#srch_start_y option:selected').val()+"]").attr("selected", "ture");
        	 //$("#srch_end_y > option[value=2]").attr("selected", "ture");
         }
     });
   	*/
 }); 

    function f_scroll() {
        var x = document.all["list"].scrollLeft;
        document.all["intitle"].style.left = 0 - x; 
    }
    

    
    function search() {
    	viewHiddenProgress(true);
    	
    	var f = document.frm ;
    	f.method.value = "listStatisticsConsideration" ;
    	f.action = "/las/statistics/statistics.do" ; 
    	f.submit() ;
    }
    
    function excelDownload() {
    	var f = document.frm ;
        f.method.value = "listStatisticsExcel" ;
        f.action = "/las/statistics/statistics.do" ; 
        f.submit() ;
    }   

  
</script>
</head>
<body>
<div id="wrap">
    <div id="container">        
    <!-- Location -->
	<div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0" alt="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
	<!-- //Location -->	 
        
    <!-- title -->
    <div class="title">
        <h3><spring:message code="las.page.field.statistics.consider" text="noMsg"/></h3>
    </div>
    <!-- //title -->
        
        <!-- content -->
        <div id="content">
        	<div id="content_in">
                <!-- search -->
                <div class="tab_box">
	                <ul id="executionDetail" class="tab_basic">
						<li id="DetailTab1"  class="on" ><a><spring:message code="las.page.field.statistics.eachMonth" text="noMsg"/></a></li>	
						<li id="DetailTab2" ><a><spring:message code="las.page.field.statistics.eachweek" text="noMsg"/></a></li>
					</ul>
                </div>
                <div class="search_box">
                    <div class="search_box_inner">
                        <table class="search_tb">
                            <colgroup>
                                <col/>
                                <col width="75px"/>
                            </colgroup>
                            <tr>
                                <td>
                                	<form:form commandName="command" id="frm" name="frm" method="post" >
					   				<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />	        
					    			<input type="hidden" name="method" id="method" value=""/>
					    			<input type="hidden" name="gubun" id="gubun" value="<c:out value="consideration"/>"/>
	        						<input type="hidden" name="srch_div" id="srch_div" value="<c:out value='${command.srch_div}'/>" />	        
                                    <table class="search_form">
                                        <colgroup>
                                            <col width="10%"/>
                                            <col width="23%"/>   
                                            <col width="10%"/>
                                            <col width="*%"/>   
                                            <col width="10%"/>
                                            <col width="24%"/>                     
                                        </colgroup>
                                        <tr>
                                            <th><spring:message code="las.page.field.statistics.sort" text="noMsg"/></th>
                                            <td>
                                                <input name="srch_type" id="srch_type" type="radio" value="C" <c:if test="${command.srch_type eq 'C'}"> checked</c:if> checked > <spring:message code="las.page.field.statistics.reviewCont"/>&nbsp;&nbsp;&nbsp;
                                                <input name="srch_type" id="srch_type" type="radio" value="L" <c:if test="${command.srch_type eq 'L'}"> checked</c:if> > <spring:message code="las.page.field.statistics.legalConsult"/>&nbsp;&nbsp;&nbsp;
                                            </td>
                         
                                      
                                            <th><spring:message code="las.page.field.statistics.theYear" text="noMsg"/></th>
                                            <td>
                                                <form:select path="srch_year" id="srch_year">
	                                                <form:options items="${ylist}"/>
                                                </form:select>
                                            </td>
                                            <th id="wTab"><spring:message code="las.page.field.board.weekSchedule.week" text="noMsg"/></th>
                                            <td id="wTab2">
                                             	<form:select path="srch_start_y" id="srch_start_y">
	                                                <form:options items="${wlist}" />
                                                </form:select>
                                            	 ~
                                            	<form:select path="srch_end_y" id="srch_end_y">
	                                                <form:options items="${wlist}"/>
                                                </form:select>
                                            </td>
                                        </tr>
                                    </table>
                                    </form:form>
                                </td>
                                <td class="tC">
                                    <a href="javascript:search();"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="las.page.field.statistics.search"/>"/></a>
                                </td>
                            </tr>
                        </table>
                        </div>
                        
                    </div>
                    <!-- search End-->
                     <!-- btn -->
                <div class="btnwrap tR">
                    <span class="btn_all_w"><span class="excel_down"></span><a href="javascript:excelDownload();"><spring:message code="las.page.field.statistics.excelDw"/></a></span>
                </div>
                <!-- // btn -->

        
                <style>
                .list_basic .sub01 {background:#FFFEEB; color:#5B4C43}
                .tbl_wrap {overflow-x:auto;overflow-y:hidden;width:100%}
                </style>
                
                <!-- 월별 -->
                <div id="tab1">
                <div class='tbl_wrap'>
	                <table class="list_basic">
	                    <colgroup>
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
                            <col width="7%"/>
                            <col width="7%"/>       
	                    </colgroup>
	                    <tr>
	                    	<th><spring:message code="las.page.field.statistics.gubun"/></th>
	                        <th><spring:message code="las.page.field.statistics.seri"/></th>
	                        <th><spring:message code="las.page.field.statistics.lions"/></th>
	                        <th><spring:message code="las.page.field.statistics.selc"/></th>
	                        <th><spring:message code="las.page.field.statistics.livingplaza"/></th>
	                        <th><spring:message code="las.page.field.statistics.medison"/></th>
	                        <th><spring:message code="las.page.field.statistics.sbl"/></th>
	                        <th><spring:message code="las.page.field.statistics.sbe"/></th>
	                        <th><spring:message code="las.page.field.statistics.sem"/></th>
	                        <th><spring:message code="las.page.field.statistics.steco"/></th>
	                        <th><spring:message code="las.page.field.statistics.sns"/></th>
	                        <th><spring:message code="las.page.field.statistics.svc"/></th>
	                        <th><spring:message code="las.page.field.statistics.sefc"/></th>
	                        <th><spring:message code="las.page.field.lawservice.sum"/></th>
	                    </tr>
	                   
	                   <c:forEach var="item" items="${MresultList}" begin="12" end="12"  varStatus="cnt">
			   		 	<tr>
			   		 		<td class="total tC"><spring:message code="las.page.field.statistics.total"  text="noMsg"/></td>
			   		 		<td class="total tC"><c:out value="${item.a1}"/></td>
			   		 		<td class="total tC"><c:out value="${item.b2}"/></td>
			   		 		<td class="total tC"><c:out value="${item.c3}"/></td>
			   		 		<td class="total tC"><c:out value="${item.d4}"/></td>
			   		 		<td class="total tC"><c:out value="${item.e5}"/></td>
			   		 		<td class="total tC"><c:out value="${item.f6}"/></td>
			   		 		<td class="total tC"><c:out value="${item.g7}"/></td>
			   		 		<td class="total tC"><c:out value="${item.h8}"/></td>
			   		 		<td class="total tC"><c:out value="${item.i9}"/></td>
			   		 		<td class="total tC"><c:out value="${item.j10}"/></td>
			   		 		<td class="total tC"><c:out value="${item.k11}"/></td>
			   		 		<td class="total tC"><c:out value="${item.l12}"/></td>
			   		 		<td class="total tC"><c:out value="${item.total}"/></td>
			   			</tr>	                            	
	       			</c:forEach>
	       			
			   		<c:forEach var="item" items="${MresultList}" begin="0" end="11"  varStatus="cnt">
			   		 	<tr>
			   		 		<td class="tC"><c:out value="${item.gubun}"/><spring:message code="las.page.field.statistics.Month"  text="noMsg"/></td>
			   		 		<td class="tC"><c:out value="${item.a1}"/></td>
			   		 		<td class="tC"><c:out value="${item.b2}"/></td>
			   		 		<td class="tC"><c:out value="${item.c3}"/></td>
			   		 		<td class="tC"><c:out value="${item.d4}"/></td>
			   		 		<td class="tC"><c:out value="${item.e5}"/></td>
			   		 		<td class="tC"><c:out value="${item.f6}"/></td>
			   		 		<td class="tC"><c:out value="${item.g7}"/></td>
			   		 		<td class="tC"><c:out value="${item.h8}"/></td>
			   		 		<td class="tC"><c:out value="${item.i9}"/></td>
			   		 		<td class="tC"><c:out value="${item.j10}"/></td>
			   		 		<td class="tC"><c:out value="${item.k11}"/></td>
			   		 		<td class="tC"><c:out value="${item.l12}"/></td>
			   		 		<td class="tC"><c:out value="${item.total}"/></td>
			   			</tr>	                            	
	       			</c:forEach>                       	
	       
					
	                </table>
	            </div>
                </div>
       
        
                <style>
                .list_basic .sub01 {background:#FFFEEB; color:#5B4C43}
                .tbl_wrap {overflow-x:auto;overflow-y:hidden;width:100%}
                </style>
                
                <div id="tab2">
                <div class='tbl_wrap'>
	                <table class="list_basic">
	                    <colgroup>
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
	                        <col width="7%"/>
	                        <col width="7%"/>         
	                    </colgroup>
	                    <tr>
	                    	<th><spring:message code="las.page.field.statistics.gubun"/></th>
	                        <th><spring:message code="las.page.field.statistics.seri"/></th>
	                        <th><spring:message code="las.page.field.statistics.lions"/></th>
	                        <th><spring:message code="las.page.field.statistics.selc"/></th>
	                        <th><spring:message code="las.page.field.statistics.livingplaza"/></th>
	                        <th><spring:message code="las.page.field.statistics.medison"/></th>
	                        <th><spring:message code="las.page.field.statistics.sbl"/></th>
	                        <th><spring:message code="las.page.field.statistics.sbe"/></th>
	                        <th><spring:message code="las.page.field.statistics.sem"/></th>
	                        <th><spring:message code="las.page.field.statistics.steco"/></th>
	                        <th><spring:message code="las.page.field.statistics.sns"/></th>
	                        <th><spring:message code="las.page.field.statistics.svc"/></th>
	                        <th><spring:message code="las.page.field.statistics.sefc"/></th>
	                        <th><spring:message code="las.page.field.lawservice.sum"/></th>
	                    </tr>
	                  
			   			<c:forEach var="item" items="${WresultList}" varStatus="cnt">
			   		 		<tr>
			   		 			<td class="<c:if test="${cnt.first}">total </c:if>tC"><c:out value="${item.gubun}"/></td>
				   		 		<td class="<c:if test="${cnt.first}">total </c:if>tC"><c:out value="${item.a1}"/></td>
				   		 		<td class="<c:if test="${cnt.first}">total </c:if>tC"><c:out value="${item.b2}"/></td>
				   		 		<td class="<c:if test="${cnt.first}">total </c:if>tC"><c:out value="${item.c3}"/></td>
				   		 		<td class="<c:if test="${cnt.first}">total </c:if>tC"><c:out value="${item.d4}"/></td>
				   		 		<td class="<c:if test="${cnt.first}">total </c:if>tC"><c:out value="${item.e5}"/></td>
				   		 		<td class="<c:if test="${cnt.first}">total </c:if>tC"><c:out value="${item.f6}"/></td>
				   		 		<td class="<c:if test="${cnt.first}">total </c:if>tC"><c:out value="${item.g7}"/></td>
				   		 		<td class="<c:if test="${cnt.first}">total </c:if>tC"><c:out value="${item.h8}"/></td>
				   		 		<td class="<c:if test="${cnt.first}">total </c:if>tC"><c:out value="${item.i9}"/></td>
				   		 		<td class="<c:if test="${cnt.first}">total </c:if>tC"><c:out value="${item.j10}"/></td>
				   		 		<td class="<c:if test="${cnt.first}">total </c:if>tC"><c:out value="${item.k11}"/></td>
				   		 		<td class="<c:if test="${cnt.first}">total </c:if>tC"><c:out value="${item.l12}"/></td>
				   		 		<td class="<c:if test="${cnt.first}">total </c:if>tC"><c:out value="${item.total}"/></td>
			   		 		</tr>	                            	
	       				</c:forEach>	       				
	                </table>
	            </div>
                </div>
                <div class="ico_alert01 mt10 fR"><spring:message code="las.page.field.statistics.firstRequestStand"/></div>
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
