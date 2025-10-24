<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@page import="com.sds.secframework.common.util.StringUtil"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.sec.las.statistics.dto.StatisticsForm"%>
<%@page import="com.sds.secframework.common.util.DateUtil"%>
<%@page import="java.math.BigDecimal"%>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<%
    StatisticsForm command = (StatisticsForm)request.getAttribute("command") ;
    int year 	= Integer.parseInt(DateUtil.year()) ;
    int month 	= Integer.parseInt(DateUtil.month());
    
    //List result = (List)request.getAttribute("result_list") ;
    
  	
%>
    
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="las.main.title" /></title>
<LINK href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="/script/clms/common.js" type="text/javascript"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.form.js"></script>
<script language="javascript" src="/script/secfw/jquery/calendar/jquery.mousewheel.min.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>    
    
<script language=javascript>

    
    $(document).ready(function(){
     	
    });
    
    function search() {
        viewHiddenProgress(true);
        
        var f = document.frm ;
        f.method.value = "listStatisticsLeadtimeByStep" ;
        f.action = "/las/statistics/statistics.do" ; 
        f.submit() ;
    }
              
    function excelDownload() {
    	var f = document.frm ;
    	f.target = "_self";
        f.method.value = "listStatisticsLeadTimeExcel" ;
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
        <h3><spring:message code="las.page.field.statistics.leadTimeByStep"/></h3>
    </div>
    <!-- //title -->
        
        <!-- content -->
        <div id="content">
            <div id="content_in">
            
            <!-- form -->
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
                                            <col width="10%"/>
                                            <col width="*" />
                                        </colgroup>
                                         <tr>
                                            <th><spring:message code="las.page.field.statistics.searchPeriod"/></th>
                                            <td >
                                                    <select name="srch_year" id="srch_year">
<%  for(int i=year; i>=2005; i--) {%>                   <option value="<%=i%>"<%= String.valueOf(i).equals(command.getSrch_year()) ? " selected" : ""%>><%=i%></option>
<%  } %>                                            </select>&nbsp; <spring:message code="las.page.field.statistics.year"/>
                                            &nbsp;&nbsp;
                                            <select name="srch_month" id="srch_month" onchange="javascript:search()">
                                            	<option value=""><spring:message code='las.page.field.common.total'/></option>
<%for(int i=1; i<13; i++){ %>						<option value="<%=i%>" <%=String.valueOf(i).equals(command.getSrch_month())?"selected" : "" %>><%=i%></option>
<%} %>
                                            	</select>&nbsp; <spring:message code="las.page.field.statistics.mm"/>
                                            </td>
                                        </tr>

                                    </table>
                                </td>
                                <td class="tC">
                                    <a href="javascript:search();"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="las.page.field.statistics.search"/>"/></a>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
                <!-- // search -->

				<div class="btnwrap tR">
                    <span class="btn_all_w"><span class="excel_down"></span><a href="javascript:excelDownload();"><spring:message code="las.page.field.statistics.excelDw"/></a></span>
                </div>

                <style>
                .list_basic .sub01 {background:#FFFEEB; color:#5B4C43}
                .tbl_wrap {overflow-x:auto;overflow-y:hidden;width:100%}
                .icon_org2 {color:#CD5221; background:url(../../../images/las/ko/icon/bu_dot_org3.gif) no-repeat 0px 3px; padding-left:8px}
                </style>
        
                <div class='tbl_wrap'>
                    <table class="list_basic">
                        <colgroup>
                            <col width="31%"/>
                            <col width="23%"/>
                            <col width="23%"/>
                            <col width="23%"/>
                        </colgroup>

						<tr>
							<th class="tC"><spring:message code="las.page.field.user.comp_nm" /></th>
							<th class="tC"><spring:message code="clm.page.title.aboutCntrtReview.listTitle" /></th>
							<th class="tC"><spring:message code="clm.page.title.aboutCntrtConsultation.listTitle" /></th>
							<th class="tC"><spring:message code="clm.page.title.manageConclusion.listTitle" /></th>
						</tr>
<!-- resultList spread here -->
						<c:forEach var="list" items="${lom}">
							<tr>
								<c:choose>
									<c:when test="${command.session_user_locale=='ko'}">
										<td class="tL sub2"><c:out value="${list.comp_nm}"/></td>
									</c:when>
									<c:otherwise>
										<td class="tL"><c:out value="${list.comp_nm_eng}"/></td>
									</c:otherwise>
								</c:choose>
								
								<c:choose>
									<c:when test="${list.req_lt == null}">
										<td class="tR">-</td>
									</c:when>
									<c:otherwise>
										<td class="tR"><c:out value="${list.req_lt}"/></td>
									</c:otherwise>
								</c:choose>
								
								<c:choose>
									<c:when test="${list.cnt_lt == null}">
										<td class="tR">-</td>
									</c:when>
									<c:otherwise>
										<td class="tR"><c:out value="${list.cnt_lt}"/></td>
									</c:otherwise>
								</c:choose>
											
								<c:choose>
									<c:when test="${list.reg_lt == null}">
										<td class="tR">-</td>
									</c:when>
									<c:otherwise>
										<td class="tR"><c:out value="${list.reg_lt}"/></td>
									</c:otherwise>
								</c:choose>
							</tr>						
						</c:forEach>
                    </table>
                </div>
                <br />
                <table class="table-style01 borz01">
					<tr>
						<td>
							<span class='icon_org2'><spring:message code="clm.page.title.manageConsideration.listTitle"/> :</span> <spring:message code="las.page.field.statistics.guide01"/><br>
							<span class='icon_org2'><spring:message code="clm.page.title.manageConsultation.listTitle"/> :</span> <spring:message code="las.page.field.statistics.guide02"/><br>
							<span class='icon_org2'><spring:message code="clm.page.title.manageConclusion.listTitle" /> :</span> <spring:message code="las.page.field.statistics.guide03"/><br>
						</td>
					</tr>
				</table>	
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