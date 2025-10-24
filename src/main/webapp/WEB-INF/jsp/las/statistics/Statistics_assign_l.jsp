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
<%@page import="com.sec.las.statistics.dto.StatisticsForm"%>
<%
    List listRow = (List)request.getAttribute("result_list") ;
	List listCol = (List)request.getAttribute("title_list") ;
	StatisticsForm command = (StatisticsForm)request.getAttribute("command") ;
	int size = 100/(listCol.size()+2) ;
	
	String searchAuth = (String)request.getAttribute("searchAuth") ;	
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
 $(document).ready(function(){
     initCal("srch_start_dt");   //첫번재 날짜창 설정 : 변수는 해당 태그의 id값
     initCal("srch_end_dt");     //두번재 날짜창 설정 : 변수는 해당 태그의 id값
 }); 

    function f_scroll() {
        var x = document.all["list"].scrollLeft;
        document.all["intitle"].style.left = 0 - x; 
    }
    
    function chkType(value) {
    	if(value=="C") {
    		$("#tr_srch_div").show() ;
    	} else {
    		$("#tr_srch_div").hide() ;
    	}
    }
    
    function search() {
    	viewHiddenProgress(true);
    	
    	var f = document.frm ;
    	f.method.value = "listStatisticsNew" ;
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
    
    //*************** 라디오 버튼용 시작 ***************//
    function setRadioCl(e){ 
        var srcEl = getSrc(e);
        var ra = srcEl.form[srcEl.name]
        for(var i=0;i<ra.length;i++){
            if(ra[i].checked) ra[i].onpropertychange = function(e){getSrc(e).click()}
            else ra[i].onclick = function(){return false};
        }
    }
    function getSrc(e)
    {
        return e? e.target || e.srcElement : event.srcElement;
    }
    
    function setDay(flag){
    	var f = document.frm ;
    	if(flag=='T1'){
    		f.srch_start_dt.value = f.this_mon0.value;
    		f.srch_end_dt.value = f.this_mon1.value;
    	}else if(flag=='T2'){
    		f.srch_start_dt.value = f.one_mon0.value;
    		f.srch_end_dt.value = f.one_mon1.value;
    	}else if(flag=='T3'){
    		f.srch_start_dt.value = f.three_mon0.value;
    		f.srch_end_dt.value = f.three_mon1.value;
    	}else if(flag=='T4'){
    		f.srch_start_dt.value = f.one_year0.value;
    		f.srch_end_dt.value = f.one_year1.value;
    	}
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
        <h3><spring:message code="las.page.field.statistics.picState"/></h3>
    </div>
    <!-- //title -->
        
        <!-- content -->
        <div id="content">
        	<div id="content_in">
            <form name="frm" id="frm" method="post">
	        <input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />	        
	        <input type="hidden" name="method" id="method" value=""/>
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
                                            <th><spring:message code="las.page.field.statistics.sort"/></th>
                                            <td>
                                                <input name="srch_type" id="srch_type" type="radio" value="C" onclick="chkType(this.value)" <c:if test="${command.srch_type=='C'}"> checked</c:if>> <spring:message code="las.page.field.statistics.reviewCont"/>&nbsp;&nbsp;&nbsp;
                                                <input name="srch_type" id="srch_type" type="radio" value="L" onclick="chkType(this.value)" <c:if test="${command.srch_type=='L'}"> checked</c:if> > <spring:message code="las.page.field.statistics.legalConsult"/>&nbsp;&nbsp;&nbsp;
                                            </td>
                                        </tr>
                                        <tr id="tr_srch_div" style="display:<c:if test="${command.srch_type=='C'}">block</c:if><c:if test="${command.srch_type=='L' || command.srch_type=='P'}">none</c:if>">
                                            <th><spring:message code="las.page.field.statistics.arrange"/></th>
                                            <td>
                                                <input name="srch_div" id="srch_div" type="radio" value="T03"<c:if test="${command.srch_div=='T03'}"> checked</c:if>> <spring:message code="las.page.field.statistics.signStnd"/>&nbsp;&nbsp;&nbsp;
                                                <input name="srch_div" id="srch_div" type="radio" value="T02"<c:if test="${command.srch_div=='T02'}"> checked</c:if>> <spring:message code="las.page.field.statistics.contStep"/>&nbsp;&nbsp;&nbsp;
                                                <input name="srch_div" id="srch_div" type="radio" value="T01"<c:if test="${command.srch_div=='T01'}"> checked</c:if>> <spring:message code="las.page.field.statistics.busStep"/>
                                            </td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <th><spring:message code="las.page.field.statistics.searchPeriod"/></th>
                                            <td>
                                                <input type="text" name="srch_start_dt" id="srch_start_dt" value="<c:out value="${command.srch_start_dt}"/>" class="text_calendar02" readonly="readonly" /> ~
                                                <input type="text" name="srch_end_dt" id="srch_end_dt" value="<c:out value="${command.srch_end_dt}"/>" class="text_calendar02" readonly="readonly" />
                                                <input name="day_set" id="day_set" type="radio" value="T1" onClick="javascript:setDay(this.value);" <c:if test="${command.day_set=='T1'}">checked</c:if>> This month&nbsp;&nbsp;&nbsp;
                                                <input name="day_set" id="day_set" type="radio" value="T2" onClick="javascript:setDay(this.value);" <c:if test="${command.day_set=='T2'}">checked</c:if>> 1 Month&nbsp;&nbsp;&nbsp;
                                                <input name="day_set" id="day_set" type="radio" value="T3" onClick="javascript:setDay(this.value);" <c:if test="${command.day_set=='T3'}">checked</c:if>> 3 Month&nbsp;&nbsp;&nbsp;
                                                <input name="day_set" id="day_set" type="radio" value="T4" onClick="javascript:setDay(this.value);" <c:if test="${command.day_set=='T4'}">checked</c:if>> 1 Year
                                                <input name="this_mon0" id="this_mon0" type="hidden" value="<c:out value="${command.this_mon[0]}"/>">
                                                <input name="this_mon1" id="this_mon1" type="hidden" value="<c:out value="${command.this_mon[1]}"/>">
                                                <input name="one_mon0" id="one_mon0" type="hidden" value="<c:out value="${command.one_mon[0]}"/>">
                                                <input name="one_mon1" id="one_mon1" type="hidden" value="<c:out value="${command.one_mon[1]}"/>">
                                                <input name="three_mon0" id="three_mon0" type="hidden" value="<c:out value="${command.three_mon[0]}"/>">
                                                <input name="three_mon1" id="three_mon1" type="hidden" value="<c:out value="${command.three_mon[1]}"/>">
                                                <input name="one_year0" id="one_year0" type="hidden" value="<c:out value="${command.one_year[0]}"/>">
                                                <input name="one_year1" id="one_year1" type="hidden" value="<c:out value="${command.one_year[1]}"/>">
                                            </td>
                                            <td></td>
                                            <td></td>
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
	                        
	                        <c:if test="${command.srch_type=='C' || command.srch_type=='L'}">
	                        	<col width="<%=size%>%"/>
	                        	<col width="<%=size%>%"/><!-- 담당자 -->
	                        	<col width="<%=size%>%"/><!-- 계 -->
	                        	<c:forEach var="list" items="${title_list }" varStatus="status">
	                            	<col width="<%=size%>%"/>
	                        	</c:forEach>
	                        </c:if>
	                       	<!-- 표준계약서 -->
	                       	<c:if test="${command.srch_type=='P'}">
                            	<col width="30%"/>
                            	<col width="30%"/>
                            	<col width="40%"/>
	                        </c:if>	                        
	                    </colgroup>
	                    <tr>
	                    	<c:if test="${command.srch_type=='C' || command.srch_type=='L'}">
	                    		<th rowspan="2" class="search_title">Company</th><!-- Reviewer -->
	                        	<th rowspan="2"><spring:message code="las.page.field.statistics.reviewer.title"/></th><!-- Reviewer -->
	                        	<th rowspan="2"><spring:message code="las.page.field.statistics.total"/></th>	                        
 								<th colspan="<c:out value="${title_colspan }"/>"><c:out value="${title_srch }"/> </th>
	                        </c:if>   
	                    </tr>
	                    <c:if test="${command.srch_type=='C' || command.srch_type=='L'}">
		                    <tr>
		                    	<c:forEach var="list" items="${title_list }" varStatus="status">
			                            <th class="<c:if test="${status.index==0}">tal_lineL </c:if>sub01" style="word-wrap:break-word;"><c:out value="${list.cd_nm }"/></th>
			                    </c:forEach>
			                 </tr>
	                     </c:if>
	          		 <c:if test="${command.srch_type=='C' || command.srch_type=='L'}">
					<%  for(int i=0; listRow!=null && i<listRow.size(); i++){ 
					        Map rowMap = (Map)listRow.get(i) ;
					%>
					                        <tr>
					                        	<td class="<%if(rowMap.get("comp_cd")==null){%>total <%}%>tC"><%=StringUtil.bvl(rowMap.get("comp_cd"),"Grand Total") %></td>
					                            <td class="<%if(rowMap.get("comp_cd")==null || rowMap.get("user_nm")==null  ){%>total <%}%>tC"><%=StringUtil.bvl(rowMap.get("user_nm"),"Total") %></td>
					                            <td class="<%if(rowMap.get("comp_cd")==null || rowMap.get("user_nm")==null ){%>total <%}%>tR"><%=StringUtil.commaIn(String.valueOf(rowMap.get("total_cnt"))) %>
					                            <%          if(command.getSrch_type().equals("C")){ %>
					                             (<%=StringUtil.commaIn(String.valueOf(rowMap.get("total_cnt_bu"))) %>)
					                             <%          }%>
					                            </td>
					<%      for(int j=0; j<listCol.size(); j++){ 
					            Map colMap = (Map)listCol.get(j) ;
					%>
					                            <td class="<%if(rowMap.get("comp_cd")==null || rowMap.get("user_nm")==null ){%>total <%}%>tR">
					                                <%=StringUtil.commaIn(String.valueOf(rowMap.get(colMap.get("cd")))) %>
					<%          if(command.getSrch_type().equals("C")){ %>
					                                (<%=StringUtil.commaIn(String.valueOf(rowMap.get(colMap.get("cd")+"_bu"))) %>)
					                            </td>
					<%          }
					        }
					%>                                
					                        </tr>
					<%  } %>
			   		 </c:if>		   		 
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
