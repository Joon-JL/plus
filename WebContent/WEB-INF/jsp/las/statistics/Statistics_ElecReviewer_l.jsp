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
<%@ page import="java.util.Calendar"%>
<%@ page import="java.text.DecimalFormat"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.sec.las.statistics.dto.StatisticsForm"%>


<%-- 
/**
 * 파  일  명 : Statistics_consideration_l.jsp
 * 프로그램명 : 검토의뢰건수 통계화면 
 * 설      명 : 관련사통계 검토의뢰 건수 통계 조회 화면
 * 작  성  자 : 박정훈
 * 작  성  일 : 2013.06
 */
--%>
<%
StatisticsForm command = (StatisticsForm)request.getAttribute("command") ;
int year = Integer.parseInt(DateUtil.year()) ;
String localeCode = (String)session.getAttribute("secfw.session.language_flag");
%>

<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="las.page.field.statistics.elecreviewer" text="noMsg"/></title>
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
	    initCal("srch_regdt1");   //첫번재 날짜창 설정 : 변수는 해당 태그의 id값
	    initCal("srch_regdt2");     //두번재 날짜창 설정 : 변수는 해당 태그의 id값
	    
	    changePeriodGbn1() ;
	
	});

	function changePeriodGbn1() {
		var val = $("#srch_period_gbn1").val() ;
		
		if(val=="2") {
			$("#span_period1").show() ;
			changePeriodGbn2() ;
		} else {
			$("#span_period1").hide() ;
			hidePeriod() ;
		}
	}
	
	function changePeriodGbn2() {
		var val = $("#srch_period_gbn2").val() ;
		hidePeriod() ;
		        	
		// 연도별
		if(val=="1") {
		    $("#span_period2").show() ; // 연도 
		}
		// 분기별
		else if(val=="2") {
		    $("#span_period2").show() ; // 연도
		    $("#span_period3").show() ; // 분기
		}
		// 월별
	    else if(val=="3") {
	        $("#span_period2").show() ; // 연도
	        $("#span_period4").show() ; // 월
	    }
		// 기간별
	    else if(val=="4") {
	        $("#span_period5").show() ; // 기간
	    }
		
	}

	function hidePeriod() {
	    $("#span_period2").hide() ;
	    $("#span_period3").hide() ;
	    $("#span_period4").hide() ;
	    $("#span_period5").hide() ;
	}

    function f_scroll() {
        var x = document.all["list"].scrollLeft;
        document.all["intitle"].style.left = 0 - x; 
    }
    

    
    function search() {
    	viewHiddenProgress(true);
    	
    	var f = document.frm ;
    	f.method.value = "listStatisticsElecReviewer" ;
    	f.action = "/las/statistics/statistics.do" ; 
    	f.submit() ;
    }
    
    function excelDownload() {
    	var f = document.frm ;
    	f.target = "_self";
        f.method.value = "listStatisticsExcelDown" ;
        f.action = "/las/statistics/statistics.do" ; 
        f.submit() ;
    }   

  
</script>
</head>
<body>
<c:set var="localeCode"><%=localeCode %></c:set>
<div id="wrap">
    <div id="container">        
    <!-- Location -->
	<div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0" alt="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
	<!-- //Location -->	 
        
    <!-- title -->
    <div class="title">
        <h3><spring:message code="las.page.field.statistics.elecreviewer" text="noMsg"/></h3>
    </div>
    <!-- //title -->
        
        <!-- content -->
        <div id="content">
        	<div id="content_in">
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
                                	<form:form commandName="command" id="frm" name="frm" method="post" >
						   				<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />	        
						    			<input type="hidden" name="method" id="method" value=""/>
						    			<input type="hidden" name="gubun" id="gubun" value="<c:out value="ElecReviewer"/>"/>
		        						<input type="hidden" name="srch_div" id="srch_div" value="<c:out value='${command.srch_div}'/>" />	        
                                    <table class="search_form">
                                        <colgroup>
                                            <col width="10%"/>
                                            <col width="30%"/>
											<col width="10%"/>
                                            <col width="*%"/>                                       
                                        </colgroup>
                                        <tr>
                                        <!-- 
                                            <th><spring:message code="las.page.field.statistics.sort"/></th>
                                            <td>
                                                <input name="srch_dmstfrgn_gbn" id="srch_dmstfrgn_gbn" type="radio" value="H" <c:if test="${command.srch_dmstfrgn_gbn eq 'H'}"> checked</c:if> checked > <spring:message code="las.page.tab.lawsuit.dmst" text="noMsg"/>&nbsp;&nbsp;&nbsp;
                                                <input name="srch_dmstfrgn_gbn" id="srch_dmstfrgn_gbn" type="radio" value="F" <c:if test="${command.srch_dmstfrgn_gbn eq 'F'}"> checked</c:if> > <spring:message code="las.page.tab.lawsuit.frgn" text="noMsg"/>&nbsp;&nbsp;&nbsp;
                                            </td>
                                             -->
                                            <th><spring:message code="las.page.field.statistics.searchPeriod"/></th>
                                            <td colspan="3">
                                                <select name="srch_period_gbn1" id="srch_period_gbn1" onChange="changePeriodGbn1()">
                                                    <option value="1"<c:if test="${command.srch_period_gbn1 eq '1'}"> selected</c:if>><spring:message code="las.page.field.statistics.prStnd" text="noMsg"/></option>
                                                    <option value="2"<c:if test="${command.srch_period_gbn1 eq '2'}"> selected</c:if>><spring:message code="las.page.field.statistics.resultPeriod" text="noMsg"/></option>
                                                </select>
                                                <span id="span_period1" style="display:none">
	                                                <select name="srch_period_gbn2" id="srch_period_gbn2" onchange="changePeriodGbn2()">
	                                                    <option value="1"<c:if test="${command.srch_period_gbn2 eq '1'}"> selected</c:if>><spring:message code="las.page.field.statistics.eachYear" text="noMsg"/></option>
	                                                    <option value="2"<c:if test="${command.srch_period_gbn2 eq '2'}"> selected</c:if>><spring:message code="las.page.field.statistics.eachQuarter" text="noMsg"/></option>
	                                                    <option value="3"<c:if test="${command.srch_period_gbn2 eq '3'}"> selected</c:if>><spring:message code="las.page.field.statistics.eachMonth" text="noMsg"/></option>
	                                                    <option value="4"<c:if test="${command.srch_period_gbn2 eq '4'}"> selected</c:if>><spring:message code="las.page.field.statistics.resultPeriod" text="noMsg"/></option>
                                                    </select>&nbsp;&nbsp;&nbsp;
                                                </span>
                                                <span id="span_period2" style="display:none">
                                                    <select name="srch_year" id="srch_year">
                                                    <%  for(int i=year; i>=2005; i--) {%> 
                                                    <option value="<%=i%>"<%= String.valueOf(i).equals(command.getSrch_year()) ? " selected" : ""%>><%=i%></option>
                                                    <%  } %>
                                                    </select> <spring:message code="las.page.field.statistics.year" text="noMsg"/>
                                                </span>
                                                <span id="span_period3" style="display:none">
                                                    <select name="srch_quarter" id="srch_quarter">
													<%  for(int i=1; i<=4; i++) {%>                         
													<option value="<%=i%>"<%= String.valueOf(i).equals(command.getSrch_quarter()) ? " selected" : ""%>><%=i%></option>
													<%  } %>    
                                       				</select> <spring:message code="las.page.field.statistics.quarter" text="noMsg"/>
                                                </span>
                                                <span id="span_period4" style="display:none">
                                                    <select name="srch_month" id="srch_month">
													<%  for(int i=1; i<=12; i++) {%>
													<option value="<%=i<10 ? "0" + i : i%>"<%= i==Integer.parseInt(command.getSrch_month()) ? " selected" : ""%>><%=i%></option>
													<%  } %>
	                                            </select> <spring:message code="las.page.field.statistics.mm" text="noMsg"/>
                                                </span>
                                                <span id="span_period5" style="display:none">
                                                    <input type="text" name="srch_start_dt" id="srch_regdt1" value="<c:out value="${command.srch_start_dt}"/>" class="text_calendar02" readonly="readonly" /> ~
                                                    <input type="text" name="srch_end_dt" id="srch_regdt2" value="<c:out value="${command.srch_end_dt}"/>" class="text_calendar02" readonly="readonly" />
                                                </span>                                                
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
                
                <div class='tbl_wrap'>
	                <table class="list_basic">
	                    <colgroup>
                            <col width="150px"/>
                            <col width="*%"/> 
	                    </colgroup>
	                    <tr>
	                    	<th rowspan="2"><spring:message code="las.page.field.user.comp" text="noMsg"/></th>
	                        <th rowspan="2"><spring:message code="las.page.field.statistics.pic" text="noMsg"/></th>	                        
	                        <th colspan="3"><c:out value="${title_srch}"/></th>	
	                        <c:if test="${command.srch_period_gbn1=='1' }">	                     
	                        <th colspan="3"><spring:message code="las.page.field.statistics.perform4" text="noMsg"/></th>
	                        <th colspan="3"><spring:message code="las.page.field.statistics.perform5" text="noMsg"/></th>	                       
	                        </c:if>
	                    </tr>
	                    <tr>
	                    	<th class="tal_lineL sub01"><spring:message code="las.page.field.statistics.req" text="noMsg"/></th>
	                        <th class="sub01"><spring:message code="las.page.field.statistics.reply" text="noMsg"/></th>	                        
	                        <th class="sub01"><spring:message code="las.page.field.statistics.leadtime" text="noMsg"/></th>
	                        <c:if test="${command.srch_period_gbn1=='1' }">	           
	                        <th class="sub01"><spring:message code="las.page.field.statistics.req" text="noMsg"/></th>
	                        <th class="sub01"><spring:message code="las.page.field.statistics.reply" text="noMsg"/></th>	                        
	                        <th class="sub01"><spring:message code="las.page.field.statistics.leadtime" text="noMsg"/></th>
	                        <th class="sub01"><spring:message code="las.page.field.statistics.req" text="noMsg"/></th>
	                        <th class="sub01"><spring:message code="las.page.field.statistics.reply" text="noMsg"/></th>	                        
	                        <th class="sub01"><spring:message code="las.page.field.statistics.leadtime" text="noMsg"/></th>
	                        </c:if>	                       
	                    </tr>
			   		<c:forEach var="list" items="${resultList}" varStatus="status">
	                        <tr>
	                        <c:choose>
		                        <c:when test="${status.first}">
		                        	<td class='total tC' colspan="2"><spring:message code="las.page.field.statistics.total" text="noMsg"/></td>
		                        </c:when>
		                        <c:otherwise>
		                        	<td class='tC'>
		                        	<c:choose>
		                        		<c:when test="${localeCode eq 'ko' }"><c:out value="${list.cd_abbr_nm}"/></c:when>
		                        		<c:when test="${localeCode eq 'en' }"><c:out value="${list.cd_abbr_nm_eng}"/></c:when> 
		                        	</c:choose>
		                        	</td>
	                            	<td class='tC'>
	                            	<c:choose>
		                        		<c:when test="${localeCode eq 'ko' }"><c:out value="${list.user_real_nm}"/></c:when>
		                        		<c:when test="${localeCode eq 'en' }"><c:out value="${list.user_real_nm_eng}"/></c:when> 
		                        	</c:choose>
	                            	</td><!-- 담당자 -->
		                        </c:otherwise>
	                        </c:choose>
	                      		    <td class='<c:if test="${status.first}">total </c:if>tR'><c:out value="${list.req1}"/></td><!-- 금주실적 - 의뢰 -->
	                                <td class='<c:if test="${status.first}">total </c:if>tR'><c:out value="${list.reply1}"/></td><!-- 금주실적 - 회신 -->
	                                <td class='<c:if test="${status.first}">total </c:if>tR'>
                                        <c:if test="${list.reply1 ne '0'}"><c:out value="${list.lt1 }"/></c:if>
                                        <c:if test="${list.reply1 eq '0' && list.reply1 eq '0' }">-</c:if>
                                    </td><!-- 금주실적 - L/T -->
	                              <c:if test="${command.srch_period_gbn1=='1' }">
                                    <td class='<c:if test="${status.first}">total </c:if>tR'><c:out value="${list.req2}"/></td><!-- 금주실적 - 의뢰 -->
	                                <td class='<c:if test="${status.first}">total </c:if>tR'><c:out value="${list.reply2}"/></td><!-- 금주실적 - 회신 -->
	                                <td class='<c:if test="${status.first}">total </c:if>tR'>
                                        <c:if test="${list.reply2 ne '0'}"><c:out value="${list.lt2 }"/></c:if>
                                        <c:if test="${list.reply2 eq '0' && list.reply2 eq '0' }">-</c:if>
                                    </td><!-- 금월실적 - L/T -->
                                    
                                    <td class='<c:if test="${status.first}">total </c:if>tR'><c:out value="${list.req3}"/></td><!-- 금주실적 - 의뢰 -->
	                                <td class='<c:if test="${status.first}">total </c:if>tR'><c:out value="${list.reply3}"/></td><!-- 금주실적 - 회신 -->
	                                <td class='<c:if test="${status.first}">total </c:if>tR'>
                                        <c:if test="${list.reply3 ne '0'}"><c:out value="${list.lt3 }"/></c:if>
                                        <c:if test="${list.reply3 eq '0' && list.reply3 eq '0' }">-</c:if>
                                    </td><!-- 금년실적 - L/T -->
                                  </c:if>
	                        </tr>
                        </c:forEach>                     	
	                </table>
	            </div>
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
