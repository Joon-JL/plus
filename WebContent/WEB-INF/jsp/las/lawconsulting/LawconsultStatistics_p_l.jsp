<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@page import="com.sds.secframework.common.util.PageUtil"%>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%@ page import="com.sec.common.util.ClmsBoardUtil" %>
<%
    PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil") ;
    session.removeAttribute("main_approach");
%>
<html>
<head>
	<meta charset="utf-8" />

	<title><spring:message code="las.main.title" /></title>
	<LINK href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet">
	<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->


<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/CommonProgress.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/ui.core.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script language="javascript">
	$(document).ready(function(){
		
    });

	//팝업오픈-모달리스
	function PopUpWindowOpen(surl, popupwidth, popupheight, bScroll, popName) {
		if( popupwidth  > window.screen.width )
			popupwidth = window.screen.width;
		if( popupheight > window.screen.height )
			popupheight = window.screen.height; 
			
		if( isNaN(parseInt(popupwidth)) ){
			Top  = (window.screen.availHeight - 600) / 2 + 30;
			Left = (window.screen.availWidth  - 800) / 2 + 30;
		} else {
			Top  = (window.screen.availHeight - popupheight)  / 2+ 30;
			Left = (window.screen.availWidth  - popupwidth) / 2+ 30;
		}

		if (Top < 0) Top = 0;
		if (Left < 0) Left = 0;
		
		popupwidth = parseInt(popupwidth) + 10 ;
		popupheight = parseInt(popupheight) + 29 ;
		
		//var Feture = "fullscreen=no,toolbar=0,location=0,directories=no,status=yes,menubar=no,scrollbars=" + (bScroll ? "yes" : "no") + ",resizable=no,left=" + Left + ",top=" + Top + ",width=" + popupwidth + ",height=" + popupheight;
		var Feture = "toolbar=0, location=0, status=1, menubar=0, scrollbars=yes, resizable=no, top="+Top+", left="+Left+", width="+popupwidth+", height="+popupheight;
		PopUpWindow = window.open(surl, popName , Feture);
		//PopUpWindow.resizeTo(parseInt(popupwidth)+10, parseInt(popupheight)+29);
		PopUpWindow.focus();
		
	}
  
    function goPage(page) {
        viewHiddenProgress(true) ;
        var f = document.frm ;

        f.cnslt_no.value = "";
        f.hstry_no.value = 0;
        f.curPage.value = page ;
        f.method.value = "listLawConsultStatistics" ;
        f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
        f.target = "_self" ;
        f.submit() ;
    }

    function goDetail(cnslt_no, hstry_no) {
        var f = document.frm ;
        f.cnslt_no.value = cnslt_no;
        f.hstry_no.value = hstry_no;
        f.method.value = "detailLawConsultStatistics" ;
        f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
        PopUpWindowOpen('', 1024, 610, true,'LawConsultDetail');
        f.target = "LawConsultDetail" ;
        f.submit();
    }

    
    function excelDownLoad(){
    	// listLawconsultReviewExcel
    
    	var frm = document.frm;
		
		// 1000row가 넘어가는 시점에서 날짜를 체크 하고 있음.
		if(<%=pageUtil.getTotalRow()%>>1000){
			alert("<spring:message code='clm.page.msg.manage.announce180max1000'/>");
			return;
		}
		//viewHiddenProgress(true) ;
		frm.cnslt_no.value = "";
        frm.hstry_no.value = 0;
		frm.target = "_self";
		frm.action = "<c:url value='/las/lawconsulting/lawConsult.do' />";
	    frm.method.value = "listLawconsultExcel";
	    frm.curPage.value = "1";
	    frm.submit();
    }
    
    
    
    
</script>
</head>
<!--  2012.04.26 Alert 수정 modified by hanjihoon  -->
<body autocomplete="off">

<div id="wrap">
  <!-- container -->
  <div id="container"  style="padding-left:0px;padding-right:0px;padding-bottom:0px">
    <!-- Location -->
    <div class="location">
      <img src="<%=IMAGE%>/icon/ico_home.gif" border="0" alt="Home" /><c:out value='${menuNavi}' escapeXml="false"/>
    </div>
    <!-- //Location -->
    <!-- title -->
    <div class="title">
      <%-- /* <h3><spring:message code="las.page.field.lawconsult.main_title" /><!--접수현황조회--></h3> */ --%>
      <h3><spring:message code="las.page.field.lawconsulting.lawAdvReview"/></h3>
    </div>
    <!-- //title -->
    <!-- content -->
    <div id="content" >
    <div id="content_in" style="padding-bottom:0px">
    <form:form name="frm" id="frm" autocomplete="off">
	<input type="hidden" name="method" value=""/>
	<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>"/>
	<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>"/>
	<input type="hidden" name="isForeign" value="<c:out value='${command.isForeign}'/>"/>
	<input type="hidden" name="cnslt_no" />
	<input type="hidden" name="hstry_no" />
	<input type="hidden" name="srch_consult_type" value="<c:out value='${command.srch_consult_type}'/>"/>
	<input type="hidden" name="srch_user_cntnt_type" value=""/>
	<input type="hidden" name="srch_user_cntnt" value=""/>
	<input type="hidden" name="srch_reg_id" value="<c:out value='${command.srch_reg_id}'/>"/>
	<input type="hidden" name="srch_prgrs_status" value="<c:out value='${command.srch_prgrs_status}'/>"/>
	<input type="hidden" name="srch_prgrs_status_cd" value="<c:out value='${command.srch_prgrs_status_cd}'/>"/>
	
	
	<input type="hidden" name="srch_comp_cd" value="<c:out value='${command.srch_comp_cd}'/>"/>
	
	
	<!-- input type="hidden" name="srch_reception" value="<c:out value='${command.srch_reception}'/>"/ -->
	
	<input type="hidden" id="isStdCont" name="isStdCont" value="N"/>
	<input type="hidden" name="fwd_gbn" value="grpmgr"/>
	<input type="hidden" name="mark_num" id="mark_num" value="" />	<!-- 중요도 체크용-->
   
	<div class="search_box" style="display:none">
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
								<col width="*"/>
								<col width="*"/>
								<col width="*"/>
								<col width="*"/>
								<col width="*"/>
								<col width="*"/>
							</colgroup>
	                    	
	                    	<tr>
		                      	<th><spring:message code="las.page.field.lawconsult.reg_dt"/><!--의뢰일--></th>
		                      	<td>
		                         	<input type="text" name="srch_start_ymd" id="srch_start_ymd" value="<c:out value='${command.srch_start_ymd}'/>"  class="text_calendar02" /> ~
		                         	<input type="text" name="srch_end_ymd" id="srch_end_ymd" value="<c:out value='${command.srch_end_ymd}'/>" class="text_calendar02" /></td>
		                      	<th><spring:message code="las.page.field.manageCommon.srchLasCnsdmanNm" /><span class="astro"></span></th>
		                      	<td>
		                          	<input id="srch_respman_nm" name="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}' escapeXml='false'/>" type="text" class="text" style='width:179px'/>
		                      	</td>
		                      	<th><spring:message code="las.page.field.lawconsult.prgrs_status" /><!--진행상황--><span class="astro"></span></th>
		                      	<td>
<!-- 		                          	<select name="srch_prgrs_status" id="srch_prgrs_status" style='width:182px'></select> -->
										
		                      	</td>
	                    	</tr>
	                    	<tr>
								 
								<!-- 의뢰명 : Subject (Title ) -->
								<th><spring:message code="las.page.field.lawconsult.title" /></th>
								<td><input id="srch_title" name="srch_title" value="<c:out value='${command.srch_title}' escapeXml='false'/>" type="text" class="text" style='width:179px'/></td>
								
								<!-- 의뢰자 : Requester  -->
								<th><spring:message code="las.page.field.contractManager.requBy" /></th>
								<td>
									<input id="srch_reg_nm" name="srch_reg_nm" value="<c:out value='${command.srch_reg_nm}' escapeXml='false'/>" type="text"  class="text_search" style='width:163px'/><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchEmployee();" class="cp" alt="search" />
								</td>
								
								<!-- 자문유형 : type of Legal advice  -->
								<th><spring:message code="las.page.field.lawconsult.consulttype" /></th>
								<td>
									<input id="srch_consult_type_name" name="srch_consult_type_name" value="<c:out value='${command.srch_consult_type_name}' escapeXml='false' />" type="text" class="text" style='width:178px'/>
								</td>
							</tr>
	                    	<tr>
	                      		<th><spring:message code="clm.page.msg.common.content" /></th>
	                      		<td>
	                      			<input id="srch_cont" name="srch_cont" value="<c:out value='${command.srch_cont}' escapeXml='false'/>" type="text" class="text" style='width:179px'/>  
	                      		</td>
		                	</tr>
					</table>
				</td>     
				</tr>
			</table>
    	</div>
    </div>
        <!-- //90% 안에서 작업하기 search -->
        <!-- list -->
        <table class="list_basic mt20" >
        
        <!-- button -->
		<div class="t_titBtn" style="display:none">
			<div class="btn_wrap_t">
				<span class="btn_all_w" onclick="excelDownLoad();"><span class="excel_down"></span><a> <spring:message code='las.page.button.excelDownload' /></a></span><!-- 엑셀 다운로드 -->
			</div>
	  	</div>
		<!-- //button -->
        
        <c:choose>
              	<c:when test="${elUserYn == 'Y'}">
        			 <colgroup>
			            <col width="10%" />
<%-- 			            <col width="10%" /> --%>
			            <col width="*%"/>
			            <col width="9%" />
			            <col width="9%" />
			            <col width="20%" />
			            <col width="10%" />
			            <col width="10%" />
<%-- 			            <col width="9%" /> --%>
			         </colgroup>      		
              	</c:when>
              	<c:otherwise>
              	    <colgroup>
			            <col width="5%" />
<%-- 			            <col width="7%" /> --%>
			            <col width="*%"/>
			            <col width="14%" />
			            <col width="14%" />
			            <col width="14%" />
			            <col width="10%" />
			            <col width="10%" />
<%-- 			            <col width="9%" /> --%>
			          </colgroup>
              	</c:otherwise>
              </c:choose>
        
          <thead>
            <tr>
              <c:choose>
              	<c:when test="${elUserYn == 'Y'}">
              		<th><spring:message code="secfw.page.field.user.comp_nm" /></th>
              	</c:when>
              	<c:otherwise>
              		<th><spring:message code="las.page.field.lawconsult.no" /></th>
              	</c:otherwise>
              </c:choose>
<%--              <th><spring:message code="las.page.field.lawconsult.solo_yn_list" /></th> --%>
	              <th><spring:message code="las.page.field.lawconsult.title" /></th>
	              <th><spring:message code="las.page.field.lawconsideration.dept"/></th>
	              <th><spring:message code="las.page.field.contractManager.requBy" /></th>
	              <th><spring:message code="las.page.field.manageCommon.srchLasCnsdmanNm" /></th>
	              <th><spring:message code="las.page.field.lawconsult.reg_dt" /></th>
	              <th><spring:message code="las.page.field.lawconsult.status" /></th>
<%--              <th><spring:message code="las.page.field.lawconsult.completYnList"/></th> --%>
          	</tr>
          </thead>
          <tbody>
            <c:choose>
              <c:when test="${pageUtil.totalRow > 0}">
                <c:forEach var="list" items="${command.lawconsult_list}">
                  <tr onmouseout="this.className='';" onmouseover="this.className='selected'">
                  	<c:choose>
		              	<c:when test="${elUserYn == 'Y'}">
		              		<td class='tC'><c:out value='${list.comp_nm}'/></td>
		              	</c:when>
		              	<c:otherwise>
		              		<td class='tC'><c:out value='${list.rm}'/></td>
		              	</c:otherwise>
		            </c:choose>
                    <td class="tL">
                    	<a href="javascript:goDetail('<c:out value="${list.cnslt_no}"/>','<c:out value="${list.hstry_no}"/>')">
                    		<c:out value='${list.title}' escapeXml="false"/>
                    	</a>
                    </td>
                    <td class='tL'><c:out value='${list.reg_dept_nm}'/></td>
                    <td class='tL'><c:out value='${list.reg_nm}'/></td>
                    <td class='tL'><c:out value='${list.respman_nm}'/></td>
                    <td class='tC'><c:out value='${list.reg_dt_date}'/></td>
                    <td class='tC'><c:out value='${list.prgrs_status_name100}'/></td>
                    
                  </tr>
                </c:forEach>
              </c:when>
              <c:otherwise>
                <tr onmouseout="this.className='';" onmouseover="this.className='selected'">
                  <td colspan="8" class="tC"><spring:message code="las.page.msg.noResult" /></td>
                </tr>
              </c:otherwise>
            </c:choose>
          </tbody>
        </table>
        <!-- //list -->
        <!-- pagination -->
        <div class="t_titBtn">
          <div class="total_num">
            Total : <%= StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow())) %>
          </div>
          <div class="pagination">
            <%= pageUtil.getPageNavi(pageUtil, "") %>
          </div>
        </div>
        <!-- //pagination -->
      </div>
      
      </form:form>
    
    </div>
    <!-- //content -->
    
    <jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
  </div>
  <!-- //container -->
</div>


<jsp:include page="/WEB-INF/jsp/secfw/common/DlgAlert.jsp"/>
</body>
</html>