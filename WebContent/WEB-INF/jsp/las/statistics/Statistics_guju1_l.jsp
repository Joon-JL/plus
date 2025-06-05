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
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil") ;

	List listRow = (List)request.getAttribute("result_list") ;
	List listCol = (List)request.getAttribute("title_list") ;
	StatisticsForm command = (StatisticsForm)request.getAttribute("command") ;
	
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
     initCal("srch_cntrtperiod_startday");   //첫번재 날짜창 설정 : 변수는 해당 태그의 id값
     initCal("srch_cntrtperiod_endday");     //두번재 날짜창 설정 : 변수는 해당 태그의 id값
     
     <c:if test="${command.srch_type=='L'}">chkType('L');</c:if>
 }); 

    function f_scroll() {
        var x = document.all["list"].scrollLeft;
        document.all["intitle"].style.left = 0 - x; 
    }
    
    function chkType(value) {
    	if(value=="C") {
    		$("#tr_srch_div").show() ;
    		$("#tr_oppnt").show() ;
    	} else {
    		$("#tr_srch_div").hide() ;
    		$("#tr_oppnt").hide() ;
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
    //*************** 라디오 버튼용 끝   ***************//
    
    function goPage(page) {
        viewHiddenProgress(true) ;
        var f = document.frm ;

        f.curPage.value = page ;
        f.method.value = "listStatisticsNew" ;
        f.action = "<c:url value='/las/statistics/statistics.do' />" ;
        f.target = "_self" ;
        f.submit() ;
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
        <h3><spring:message code="las.page.field.statistics.listStatisticsExcelDown05"/></h3><!-- Run-Time by Request -->
    </div>
    <!-- //title -->
        
        <!-- content -->
        <div id="content">
        	<div id="content_in">
            <form name="frm" id="frm" method="post">
            
            <input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>"/>
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
                                      	<col width="15%"/>
										<col width="35%"/>
										<col width="15%"/>
										<col width="35%"/>
										</colgroup>
										<tr>
                                            <th><spring:message code="las.page.field.statistics.sort"/></th>
                                            <td>
                                                <input name="srch_type" id="srch_type" type="radio" value="C" onclick="chkType(this.value)" <c:if test="${command.srch_type=='C'}"> checked</c:if>> <spring:message code="las.page.field.statistics.reviewCont"/>&nbsp;&nbsp;&nbsp;
                                                <input name="srch_type" id="srch_type" type="radio" value="L" onclick="chkType(this.value)" <c:if test="${command.srch_type=='L'}"> checked</c:if> > <spring:message code="las.page.field.statistics.legalConsult"/>&nbsp;&nbsp;&nbsp;
                                            </td>
                                            <th><spring:message code="las.page.field.statistics.requesttitle.title"/></th><!-- Request Title -->
                                            <td>
                                                <input type="text" name="srch_req_title" id="srch_req_title" value="<c:out value="${command.srch_req_title}"/>" class="text w_70" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <th><spring:message code="las.page.field.statistics.requesttitle.requester"/></th><!-- Requester -->
                                            <td>
                                                <input type="text" name="srch_requester" id="srch_requester" value="<c:out value="${command.srch_requester}"/>" class="text w_70" />
                                        	</td>
                                        	
                                            <th><spring:message code="las.page.field.statistics.requesttitle.reviewer"/></th><!-- Reviewer -->
                                            <td>
                                            	<input type="text" name="srch_reviewer" id="srch_reviewer" value="<c:out value="${command.srch_reviewer}"/>" class="text w_70" />
                                            </td>
                                        </tr>
                                      
                                        <tr id="tr_oppnt">
                                            <th><spring:message code="clm.page.title.customer.title"/></th><!-- Counterparty -->
                                            <td>
                                                <input type="text" name="srch_cntrt_oppnt_nm" id="srch_cntrt_oppnt_nm" value="<c:out value="${command.srch_cntrt_oppnt_nm}"/>" class="text w_70" />
                                        	</td>
                                        	
                                            <th><spring:message code="las.page.field.statistics.requesttitle.contterm"/></th><!-- Contract Term -->
                                            <td>
                                            	<input type="text" name="srch_cntrtperiod_startday" id="srch_cntrtperiod_startday" value="<c:out value="${command.srch_cntrtperiod_startday}"/>" class="text_calendar02"  /> ~
                                                <input type="text" name="srch_cntrtperiod_endday" id="srch_cntrtperiod_endday" value="<c:out value="${command.srch_cntrtperiod_endday}"/>" class="text_calendar02" />
                                            </td>
                                        </tr>
                                      
                                        <tr>
                                            <th><spring:message code="las.page.field.statistics.searchPeriod"/></th>
                                            <td colspan=3>
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
                                        </tr>
                                    </table>
                                </td>
                                <td class="tC">
                                    <a href="javascript:frm.curPage.value=1;search();"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="las.page.field.statistics.search"/>"/></a>
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


				<!----통계테이블 ---->
		
				<div class="" style="width:100%;overflow-x:scroll;overflow-y:hidden;">						
					<table class="list_table2" style=''>
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
							<th rowspan="2" style='height:49px'><spring:message code="las.page.field.statistics.requesttitle.title"/></th><!-- Request title -->
							<th rowspan="2"><spring:message code="las.page.field.statistics.requesttitle.requester"/></th><!-- Requestor -->
							<th rowspan="2"><spring:message code="las.page.field.statistics.requesttitle.reviewer"/></th><!-- Reviewer -->
							<th rowspan="2"><spring:message code="las.page.field.statistics.requesttitle.legalteam"/></th><!-- Legal Team -->
							<th rowspan="2" style="border-right:1px solid #CBDCE4;"><spring:message code="las.page.field.statistics.requesttitle.subsidiary"/></th><!-- Subsidiary -->
							<th colspan="3"><spring:message code="las.page.field.statistics.requesttitle.typeofcont"/></th><!-- Contract Type-->
							<th rowspan="2"><spring:message code="clm.page.title.customer.title"/></th><!-- Counterparty -->
							<th rowspan="2"><spring:message code="las.page.field.statistics.requesttitle.contterm"/></th><!-- Contract Term -->
							<th rowspan="2"><spring:message code="las.page.field.statistics.requesttitle.value"/></th><!-- Value -->
							<th rowspan="2"><spring:message code="las.page.field.statistics.requesttitle.1strqstdate"/></th><!-- First Request date -->
							<th rowspan="2"><spring:message code="las.page.field.statistics.requesttitle.reqdateforreply"/></th><!-- Requested Reply date -->
							<th rowspan="2"><spring:message code="las.page.field.statistics.requesttitle.reqalloc"/></th><!-- Request → Assignment -->
							<th rowspan="2"><spring:message code="las.page.field.statistics.requesttitle.allocresp"/></th><!-- Assignment → Final Reply -->
							<th rowspan="2"><spring:message code="las.page.field.statistics.requesttitle.reqreply"/></th><!-- Request → Final Reply -->
							<th rowspan="2"><spring:message code="las.page.field.statistics.requesttitle.lastreplyapp"/></th><!-- Final Reply → Approval -->
							<th rowspan="2"><spring:message code="las.page.field.statistics.requesttitle.appuploadcopy"/></th><!-- Approval → Upload Hard copy -->
							<th rowspan="2"><spring:message code="las.page.field.statistics.requesttitle.uploadcopy"/></th><!-- Upload Hard copy → Original contract Received -->
							<th rowspan="2"><spring:message code="las.page.field.statistics.requesttitle.reqgetorg"/></th><!-- Request → Origianl contract Received -->
						</tr>
						<tr>
							<th class='th_line sub01'><spring:message code="las.page.field.statistics.requesttitle.busistage"/></th><!-- Business -->
							<th class='sub01'><spring:message code="las.page.field.statistics.requesttitle.contstage"/></th><!-- Contract -->
							<th class='sub01'><spring:message code="las.page.field.statistics.requesttitle.purpofcont"/></th><!-- Purpose of Contract -->
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
							<th style='height:49px'><spring:message code="las.page.field.statistics.requesttitle.title"/></th><!-- Request title -->
							<th><spring:message code="las.page.field.statistics.requesttitle.requester"/></th><!-- Requestor -->
							<th><spring:message code="las.page.field.statistics.requesttitle.reviewer"/></th><!-- Reviewer -->
							<th><spring:message code="las.page.field.statistics.requesttitle.legalteam"/></th><!-- Legal Team -->
							<th><spring:message code="las.page.field.statistics.requesttitle.subsidiary"/></th><!-- Subsidiary -->
							<th><spring:message code="las.page.field.statistics.requesttitle.legaltype"/></th><!-- Legal advice Type -->
							<th><spring:message code="las.page.field.statistics.requesttitle.1strqstdate"/></th><!-- First Request date -->
							<th><spring:message code="las.page.field.statistics.requesttitle.reqalloc"/></th><!-- Request → Assignment -->
							<th><spring:message code="las.page.field.statistics.requesttitle.allocresp"/></th><!-- Assignment → Final Reply -->
							<th><spring:message code="las.page.field.statistics.requesttitle.reqreply"/></th><!-- Request → Final Reply -->
							<th><spring:message code="las.page.field.statistics.requesttitle.reqgetorg"/></th><!-- Request → Origianl contract Received -->
						</tr>
						
					</c:if>	
							
					<%  for(int i=0; listRow!=null && i<listRow.size(); i++){ 
					        Map rowMap = (Map)listRow.get(i) ;
					%>
						<tr>
							<td class="overflow" title="<%=rowMap.get("req_title") %>"><%=rowMap.get("req_title") %></td>
							<td class="overflow" title="<%=rowMap.get("reqman_nm") %>"><%=rowMap.get("reqman_nm") %></td>
							<td class="overflow" title="<%=rowMap.get("cnsdmans") %>"><%=rowMap.get("cnsdmans") %></td>
							<td class="overflow"><%=rowMap.get("cnsd_dept_nm") %></td>
							<td class="tC"><%=rowMap.get("comp_nm") %></td>
							<td class="tC"><%=rowMap.get("cnt_type1") %></td>
							<c:if test="${command.srch_type=='C'}">
								<td class="tC"><%=rowMap.get("cnt_type2") %></td>
								<td class="tC"><%=rowMap.get("cnt_type3") %></td>
								<td class="tC"><%=rowMap.get("cntrt_oppnt_nm") %></td>
								<td class="tC"><%=rowMap.get("cntrtperiod_startday") %>~<%=rowMap.get("cntrtperiod_endday") %></td>
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
							
							<!--FIRST_REQ_DT:<%=rowMap.get("FIRST_REQ_DT") %>, FIRST_ASGN_DT:<%=rowMap.get("FIRST_ASGN_DT") %>,
							LAST_REQ_DT:<%=rowMap.get("LAST_REQ_DT") %>, APBT_DT:<%=rowMap.get("APBT_DT") %>,
							LAST_ASGN_DT:<%=rowMap.get("LAST_ASGN_DT") %>, APPROVED:<%=rowMap.get("APPROVED") %>,
							CPY_REGDAY:<%=rowMap.get("CPY_REGDAY") %>, ORG_ACPTDAY:<%=rowMap.get("ORG_ACPTDAY") %>  -->	                    
						</tr>
					<%  } %>
					 	
						</tr>
					</table>
				</div>
			<!----// 통계테이블 ---->
             </form>
             
             <!-- pagination -->
					<div class="t_titBtn">
						<div class="total_num">
							Total : <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%>
						</div>
						<div class="pagination">
							<%=pageUtil.getPageNavi(pageUtil, "") %>	
						</div>
					</div>
					<!-- //pagination -->
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
