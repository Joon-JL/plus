<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.sec.las.statistics.dto.StatisticsForm"%>
<%@page import="com.sds.secframework.common.util.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<%
    StatisticsForm command = (StatisticsForm)request.getAttribute("command") ;
    int year = Integer.parseInt(DateUtil.year()) ;
    int size = 10 ;
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
    function f_scroll() {
        var x = document.all["list"].scrollLeft;
        document.all["intitle"].style.left = 0 - x; 
    }
    
    $(document).ready(function(){
    	
    	var limit_cd = "<c:out value="${command.session_auth_comp_cd}" escapeXml="false" />";
    	limit_cd = replaceStr(limit_cd,"'","");
    	  
    	getCodeSelectByUpCd("frm", "srch_comp_cd", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=A&combo_grp_cd=COMP2&combo_type=&combo_del_yn=N&combo_selected=<c:out value='${command.srch_comp_cd}'/>&limit_cd='+limit_cd);
    });
    
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
    
    
</script>
<script language="javascript">
function Layer2_2_onscroll() {
	Layer1_2.scrollLeft  =  Layer2_2.scrollLeft;
	Layer2_1.scrollTop = Layer2_2.scrollTop;
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
        <h3><spring:message code="las.page.field.statistics.lap.title"/></h3><!-- Lapsed time by Request -->
    </div>
    <!-- //title -->
        
        <!-- content -->
        <div id="content">
            <div class="content_in">
             <form name="frm" id="frm" method="post">
	        <input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />        
	        <input type="hidden" name="method" id="method" value=""/>
	        <input type="hidden" name="gubun" id="gubun" value="<c:out value="${command.gubun}"/>"/>

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
                                            <th><spring:message code="las.page.field.statistics.lap.srchsubsidiary"/></th><!-- Search Subsidiary -->
                                            <td>
                                            	<select name="srch_comp_cd" id="srch_comp_cd" >
                                                </select>
                                                
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

               <!-- 좌우상하스크롤테이블 -->
				<div class="px_wrap">
					<table class='table1'>
						<colgroup>
							<col width="50%" />
							<col width="50%" />
						</colgroup>
						<tr>
							<td>
								<!-- Title 앞부분-->
								<div id="Layer1_1" class="px_Lt_wrap">
									<table class="px_Lt list_table_st">
										<colgroup>
											<col width="15%"/>
											<col width="15%"/>
											<col width="15%"/>
											<col width="15%"/>
											<col width="15%"/>
											<col width="25%"/>
										</colgroup>	  
										<tr>
											<th class='overflow'><spring:message code="las.page.field.statistics.lap.subsidiary"/></th><!-- Subsidiary -->      
											<th class='overflow'><spring:message code="las.page.field.statistics.lap.legteam"/></th><!-- Legal team -->         
											<th class='overflow'><spring:message code="las.page.field.statistics.lap.reviewer"/></th><!-- Reviewer -->          
											<th class='overflow'><spring:message code="las.page.field.statistics.lap.os"/></th><!-- (O/S) -->                   
											<th class='overflow'><spring:message code="las.page.field.statistics.lap.requester"/></th><!-- Requester -->        
											<th class='overflow'><spring:message code="las.page.field.statistics.lap.request_title"/></th><!-- Request Title -->
										</tr>
									</table>
								</div>
								<!-- // Title 앞부분-->
							</td>
							<td>
								<!-- Title 뒷부분-->
								<div id="Layer1_2" class="px_Rt_wrap">
									<table class="px_Rt list_table_st">
										<tr> 
											<th class='w_100'><spring:message code="las.page.field.statistics.lap.rviewinproc"/></th><!-- Review in progress -->
				                            <th class='w_100'><spring:message code="las.page.field.statistics.lap.firstreplied"/></th><!-- First replied -->    
				                            <th class='w_100'><spring:message code="las.page.field.statistics.lap.finalreplied"/></th><!-- Final replied -->    
				                            <th class='w_100'><spring:message code="las.page.field.statistics.lap.approval"/></th><!-- Approval -->             
				                            <th class='w_100'><spring:message code="las.page.field.statistics.lap.regist"/></th><!-- Registration -->           
				                            <th class='w_100'><spring:message code="las.page.field.statistics.lap.executed"/></th><!-- Executed -->             
				                            <th class='w_100'><spring:message code="las.page.field.statistics.lap.termination"/></th><!-- Termination -->       
				                            <th class='w_100'><spring:message code="las.page.field.statistics.lap.hoiding"/></th><!-- Holding -->               
				                            <th class='w_100'><spring:message code="las.page.field.statistics.lap.admreplied"/></th><!-- Admin Replied -->      
				                            <th class='w_100'><spring:message code="las.page.field.statistics.lap.closed"/></th><!-- Closed -->                 
				                            <th class='w_100'><spring:message code="las.page.field.statistics.lap.request"/></th><!-- Request -->               
				                            <th class='w_100'><spring:message code="las.page.field.statistics.lap.firstreplied"/></th><!-- First replied -->    
				                            <th class='w_100'><spring:message code="las.page.field.statistics.lap.finalreplied"/></th><!-- Final replied -->    
				                            <th class='w_100'><spring:message code="las.page.field.statistics.lap.totallt"/></th><!-- Total L/T -->             
                     						<th class='w_100'>&nbsp;</th>
										</tr>
									</table>
								</div>
								<!-- // Title 뒷부분-->
							</td>
						</tr>
						<tr>
							<td>
								<!-- list 앞부분-->
								<div id="Layer2_1" class="px_Lt02_wrap">
									<table class="px_Lt02 list_table_st" style='border-top:0; '>
										<colgroup>
											<col width="15%"/>
											<col width="15%"/>
											<col width="15%"/>
											<col width="15%"/>
											<col width="15%"/>
											<col width="25%"/>
										</colgroup>
										<c:forEach var="list" items="${result_list }" varStatus="t">
				                        	<c:set var="css_chg" value="" />
				                        	<c:set var="compcd_chg" value="${list.comp_cd }" />
				                        	<c:if test="${list.dept_cd eq null}">
				                        		<c:set var="css_chg" value="background:#F7F7F7; color:#4c382a;" />
				                        		<c:set var="compcd_chg" value="Total" />
				                          	</c:if>	
						                  	<c:if test="${list.comp_cd eq null}">
				                        		<c:set var="css_chg" value="background:#F7F7F7; color:#4c382a;" />
				                        		<c:set var="compcd_chg" value="SubTotal" />
						                  	</c:if>	
							 				
							 				<c:set var="v_chk1" value="V" /><c:set var="v_chk2" value="V" /><c:set var="v_chk3" value="V" />
				                            <c:set var="v_chk4" value="V" /><c:set var="v_chk5" value="V" /><c:set var="v_chk6" value="V" />
				                            <c:set var="v_chk7" value="V" /><c:set var="v_chk8" value="V" /><c:set var="v_chk9" value="V" />
				                            <c:set var="v_chk10" value="V" />
				                            
				                            <c:if test="${list.review_in_proc eq 0}"><c:set var="v_chk1" value="" /></c:if>
				                            <c:if test="${list.f_replied eq 0}"><c:set var="v_chk2" value="" /></c:if>
				                            <c:if test="${list.last_replied eq 0}"><c:set var="v_chk3" value="" /></c:if>
				                            <c:if test="${list.approval eq 0}"><c:set var="v_chk4" value="" /></c:if>
				                            <c:if test="${list.registration eq 0}"><c:set var="v_chk5" value="" /></c:if>
				                            <c:if test="${list.executed eq 0}"><c:set var="v_chk6" value="" /></c:if>
				                            <c:if test="${list.termination eq 0}"><c:set var="v_chk7" value="" /></c:if>
				                            <c:if test="${list.holding eq 0}"><c:set var="v_chk8" value="" /></c:if>
				                            <c:if test="${list.adm_replied eq 0}"><c:set var="v_chk9" value="" /></c:if>
				                            <c:if test="${list.closed eq 0}"><c:set var="v_chk10" value="" /></c:if>
				                           
				                            <c:if test="${list.dept_cd eq null || list.comp_cd eq null}">
				                        		<c:set var="v_chk1" value="${list.review_in_proc}" />
				                        		<c:set var="v_chk2" value="${list.f_replied}" />
				                        		<c:set var="v_chk3" value="${list.last_replied}" />
				                        		<c:set var="v_chk4" value="${list.approval}" />
				                        		<c:set var="v_chk5" value="${list.registration}" />
				                        		<c:set var="v_chk6" value="${list.executed}" />
				                        		<c:set var="v_chk7" value="${list.termination}" />
				                        		<c:set var="v_chk8" value="${list.holding}" />
				                        		<c:set var="v_chk9" value="${list.adm_replied}" />
				                        		<c:set var="v_chk10" value="${list.closed}" />
				                          	</c:if>	
				                          	
				                            <tr>
				                                <td class='tC overflow' ><c:out value="${compcd_chg }"/></td><!-- Subsidiary -->
				                                <td class='tC overflow' style='<c:out value="${css_chg}"/>'><c:out value="${list.dept_cd }"/></td><!-- Legal team -->
				                                <td class='tC overflow' style='<c:out value="${css_chg}"/>'><c:out value="${list.respman_nm }"/></td><!-- Reviewer -->
				                                <td class='tC overflow' style='<c:out value="${css_chg}"/>'><c:out value="${list.main_cnsd_yn }"/></td><!-- (O/S) -->
				                                <td class='tC overflow' style='<c:out value="${css_chg}"/>'><c:out value="${list.reqman_nm }"/></td><!-- Requester -->
				                                <td class='tL overflow' style='<c:out value="${css_chg}"/>'><c:out value="${list.req_title }"/></td><!-- Request Title -->			                                
				                            </tr>
				                        </c:forEach>	  
										
									</table>		
				                </div>
								<!-- // list 앞부분-->
							</td>
							<td>
								<!-- list 뒷부분-->
								<div id="Layer2_2" class="px_Rt02_wrap" onScroll="return Layer2_2_onscroll();" >
									<table class="px_Rt02 list_table_st" style='border-top:0'>
										<c:forEach var="list" items="${result_list }" varStatus="t">
				                        	<c:set var="css_chg" value="" />
				                        	<c:set var="compcd_chg" value="${list.comp_cd }" />
				                        	<c:if test="${list.dept_cd eq null}">
				                        		<c:set var="css_chg" value="background:#F7F7F7; color:#4c382a;" />
				                        		<c:set var="compcd_chg" value="Total" />
				                          	</c:if>	
						                  	<c:if test="${list.comp_cd eq null}">
				                        		<c:set var="css_chg" value="background:#F7F7F7; color:#4c382a;" />
				                        		<c:set var="compcd_chg" value="SubTotal" />
						                  	</c:if>	
							 				
							 				<c:set var="v_chk1" value="V" /><c:set var="v_chk2" value="V" /><c:set var="v_chk3" value="V" />
				                            <c:set var="v_chk4" value="V" /><c:set var="v_chk5" value="V" /><c:set var="v_chk6" value="V" />
				                            <c:set var="v_chk7" value="V" /><c:set var="v_chk8" value="V" /><c:set var="v_chk9" value="V" />
				                            <c:set var="v_chk10" value="V" />
				                            
				                            <c:if test="${list.review_in_proc eq 0}"><c:set var="v_chk1" value="" /></c:if>
				                            <c:if test="${list.f_replied eq 0}"><c:set var="v_chk2" value="" /></c:if>
				                            <c:if test="${list.last_replied eq 0}"><c:set var="v_chk3" value="" /></c:if>
				                            <c:if test="${list.approval eq 0}"><c:set var="v_chk4" value="" /></c:if>
				                            <c:if test="${list.registration eq 0}"><c:set var="v_chk5" value="" /></c:if>
				                            <c:if test="${list.executed eq 0}"><c:set var="v_chk6" value="" /></c:if>
				                            <c:if test="${list.termination eq 0}"><c:set var="v_chk7" value="" /></c:if>
				                            <c:if test="${list.holding eq 0}"><c:set var="v_chk8" value="" /></c:if>
				                            <c:if test="${list.adm_replied eq 0}"><c:set var="v_chk9" value="" /></c:if>
				                            <c:if test="${list.closed eq 0}"><c:set var="v_chk10" value="" /></c:if>
				                           
				                            <c:if test="${list.dept_cd eq null || list.comp_cd eq null}">
				                        		<c:set var="v_chk1" value="${list.review_in_proc}" />
				                        		<c:set var="v_chk2" value="${list.f_replied}" />
				                        		<c:set var="v_chk3" value="${list.last_replied}" />
				                        		<c:set var="v_chk4" value="${list.approval}" />
				                        		<c:set var="v_chk5" value="${list.registration}" />
				                        		<c:set var="v_chk6" value="${list.executed}" />
				                        		<c:set var="v_chk7" value="${list.termination}" />
				                        		<c:set var="v_chk8" value="${list.holding}" />
				                        		<c:set var="v_chk9" value="${list.adm_replied}" />
				                        		<c:set var="v_chk10" value="${list.closed}" />
				                          	</c:if>	
				                          	
				                            <tr>                         
				                                <td class='tC_100' style='<c:out value="${css_chg}"/>'><c:out value="${v_chk1}"/></td><!-- Review in progress -->
				                                <td class='tC_100' style='<c:out value="${css_chg}"/>'><c:out value="${v_chk2 }"/></td><!-- First replied -->
				                                <td class='tC_100' style='<c:out value="${css_chg}"/>'><c:out value="${v_chk3 }"/></td><!-- Final replied -->
				                                <td class='tC_100' style='<c:out value="${css_chg}"/>'><c:out value="${v_chk4 }"/></td><!-- Approval -->
				                                <td class='tC_100' style='<c:out value="${css_chg}"/>'><c:out value="${v_chk5 }"/></td><!-- Registration -->
				                                <td class='tC_100' style='<c:out value="${css_chg}"/>'><c:out value="${v_chk6 }"/></td><!-- Executed -->
				                                <td class='tC_100' style='<c:out value="${css_chg}"/>'><c:out value="${v_chk7 }"/></td><!-- Termination -->
				                                <td class='tC_100' style='<c:out value="${css_chg}"/>'><c:out value="${v_chk8 }"/></td><!-- Holding -->
				                                <td class='tC_100' style='<c:out value="${css_chg}"/>'><c:out value="${v_chk9 }"/></td><!-- Admin Replied -->
				                                <td class='tC_100' style='<c:out value="${css_chg}"/>'><c:out value="${v_chk10}"/></td><!-- Closed -->
				                                                   
				                                <td class='tC_100' style='<c:out value="${css_chg}"/>'><c:out value="${list.req_dt }"/></td><!-- Request -->
				                                <td class='tC_100' style='<c:out value="${css_chg}"/>'><c:out value="${list.f_replied_dt }"/></td><!-- First replied -->
				                                <td class='tC_100' style='<c:out value="${css_chg}"/>'><c:out value="${list.last_replied_dt }"/></td><!-- Final replied -->
				                                <td class='tC_100'><c:out value="${list.lt1 }"/></td><!-- Total L/T -->
				                                <td>&nbsp;</td>
				                            </tr>
				                             
				                        </c:forEach>
									</table>
								</div>
								<!-- // list 뒷부분-->
								
							</td>                           
						</tr>
					</table>
				</div>
				<!-- // 좌우상하스크롤테이블 -->
        		
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
<br>
<!-- footer -->
<script language="JavaScript" src="/script/clms/footer.js"></script>
<!-- //footer -->
</body>
</html>