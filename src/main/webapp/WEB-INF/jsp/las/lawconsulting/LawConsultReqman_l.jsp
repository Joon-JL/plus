<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.PageUtil"%>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
    PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
    // 메뉴 네비게이션 스트링
    String menuNavi = (String)request.getAttribute("secfw.menuNavi");
    // 2014-03-05 Kevin.
    Object returnVal = request.getAttribute("isCopied");
    boolean isCced = false;
    if(returnVal != null){
    	isCced = (Boolean)returnVal;
    }
%>
<html>
<head>
<meta charset="utf-8" />

<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet" />
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
        getCodeSelectByUpCd("frm", "srch_prgrs_status", '/common/clmsCom.do?method=listComCdByGrpCd&combo_abbr=F&combo_sys_cd=LAS&combo_grp_cd=N1_E&combo_type=S&combo_del_yn=N&combo_selected=<c:out value='${command.srch_prgrs_status}'/>');

        initCal("srch_start_ymd");   //첫번재 날짜창 설정 : 변수는 해당 태그의 id값
        initCal("srch_end_ymd");     //두번재 날짜창 설정 : 변수는 해당 태그의 id값
    });

    $(function(){

        $("#srch_respman_nm").keydown(function(event) {
            if(event.keyCode == "13") {
                search();
            }
        });
        $("#srch_title").keydown(function(event) {
            if(event.keyCode == "13") {
                search();
            }
        });
    });

    function initPage(){
    	alertMessage('<c:out value='${command.return_message}'/>');
    }
    
    function search(){
        goPage(1) ;
    }

    function goPage(page) {
        viewHiddenProgress(true) ;
        var f = document.frm ;

        var startDt = f.srch_start_ymd.value ;
        var endDt = f.srch_end_ymd.value ;
		
        
        
        if(startDt == "" && endDt != ""){
            viewHiddenProgress(false) ;
            alert("<spring:message code='las.page.field.statistics.inputFromDate'/>") ;
            f.srch_start_ymd.focus() ;

            return ;
        }
        
        if(startDt != "" && endDt == ""){
            viewHiddenProgress(false) ;
            alert("<spring:message code='las.page.field.statistics.inputToDate'/>") ;
            f.srch_end_ymd.focus() ;

            return ;
        }
        
        
        if(startDt>endDt){
            viewHiddenProgress(false) ;
            alert("<spring:message code='las.page.field.lawconsulting.setFromDateEarly'/>") ;
            f.srch_start_ymd.focus() ;

            return ;
        }
        
        f.cnslt_no.value = "";
        f.hstry_no.value = 0;
        f.curPage.value = page ;
        // 2014-03-05 Kevin. 같은 페이지에서 Cced 된 내역을 함께 보기 위해 controller 메서드를 분리함.
        var bCced = <%=isCced%>;
        f.method.value = (bCced? "listLawConsultCCed":"listLawConsultRequest");
        f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
        f.target = "_self" ;
        f.submit() ;
    }

    function goInsertForm() {
        viewHiddenProgress(true) ;
        var f = document.frm ;
        f.cnslt_no.value = "";
        f.hstry_no.value = 0;
        f.method.value = "forwardInsertLawConsult" ;
        f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
        f.target = "_self" ;
        f.submit() ;
    }

    function goDetail(cnslt_no, hstry_no) {
        viewHiddenProgress(true) ;
        var f = document.frm ;
        f.cnslt_no.value = cnslt_no;
        f.hstry_no.value = hstry_no;
        f.method.value = "forwardDetailLawConsultReqman" ;
        f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
        f.target = "_self" ;
        f.submit() ;
    }
    

    
    function excelDownLoad(){
    	// listLawconsultReviewExcel
    
    	var frm = document.frm;
		
    	
    	if(<%=pageUtil.getTotalRow()%>>1000){
			alert("<spring:message code='clm.page.msg.manage.announce180max1000'/>");
			return;
		}
    	
    	//!@#$ 2013.11.29 기존 300건 초과시 조회날짜 조정 검사에서 1000건으로 변경
		// 300row가 넘어가는 시점에서 날짜를 체크 하고 있음.
<%-- 		if(<%=pageUtil.getTotalRow()%>>300){ --%>
// 		    if(frm.srch_start_ymd.value == ''){
// 			    alert("<spring:message code='clm.page.msg.manage.announce128'/>");//의뢰일 시작을 선택해주세요.
// 			    return;
// 		    }
// 		    if(frm.srch_end_ymd.value == ''){
// 			    alert("<spring:message code='clm.page.msg.manage.announce129'/>");//의뢰일 종료를 선택해주세요.
// 			    return;
// 		    }
// 		    //날짜계산
// 		    var sd = frm.srch_start_ymd.value; //start Day
// 	        var ed = frm.srch_end_ymd.value; //end Day
// 		    sd = sd.split("-");
// 		    ed = ed.split("-");
// 		    var st = new Date(sd[0],sd[1]-1,sd[2]).getTime();
// 		    var et = new Date(ed[0],ed[1]-1,ed[2]).getTime();
// 		    var fromto = (et-st)/(1000*60*60*24)+1;

// 		    if(fromto > 90){
// 			    alert("<spring:message code='clm.page.msg.manage.announce180max90'/>");//최대 90일 기간까지 다운 할 수 있습니다.
// 			    return;			
// 		    }
// 		}

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
<body onload="initPage()" autocomplete="off">


<div id="wrap">
  <!-- container -->
  	<div id="container">
    <!-- Location -->
    <div class="location">
      	<img src="<%=IMAGE%>/icon/ico_home.gif" border="0" alt="Home" /><c:out value='${menuNavi}' escapeXml="false"/>
    </div>
    <!-- //Location -->
    <!-- title -->
    <div class="title">
      	<%-- /* <h3><spring:message code="las.page.field.lawconsult.main_title" /><!-- 자문리스트 --></h3> */ --%>
      	<h3><spring:message code="las.page.field.lawconsulting.lawReqAdv" /></h3>
    </div>
    <!-- //title -->
    <!-- content -->
    <div id="content">
    <div id="content_in">
    
    <form:form name="frm" id="frm" autocomplete="off">
	    <input type="hidden" name="method" value=""/>
		<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>"/>
		<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>"/>
		<input type="hidden" name="isForeign" value="<c:out value='${command.isForeign}'/>"/>
		<input type="hidden" name="cnslt_no">
		<input type="hidden" name="hstry_no">
		<input type="hidden" name="fwd_gbn" value="req"/>
      	<!-- search -->
      	
      	<div  class="search_box">
        <div class="search_box_inner">
        	<table class="search_tb">
            	<colgroup>
            		<col/>
              		<col width="75px"/>
            	</colgroup>
            	
            	<tr>
              		<td>
                		<table  class="search_form">
                  			<colgroup>
                    			<col width="10%"/>
			                    <col width="30%"/>
			                    <col width="10%"/>
			                    <col width="20%"/>
			                    <col width="10%"/>
			                    <col width="20%"/>
                  			</colgroup>
                  			
                  			<tr>
                    			<th><spring:message code="las.page.field.lawconsult.reg_dt" /></th>
                    			<td>
                       				<input type="text" name="srch_start_ymd" id="srch_start_ymd" value="<c:out value='${command.srch_start_ymd}'/>"  class="text_calendar02" style='width:74px'/> ~
                       				<input type="text" name="srch_end_ymd" id="srch_end_ymd" value="<c:out value='${command.srch_end_ymd}'/>" class="text_calendar02" style='width:73px'/></td>
                    			<th><spring:message code="las.page.field.lawconsult.prgrs_status" /><span class="astro"></span></th>
                    			<td>
                      				<span style="width:35%;">
                        			<select name="srch_prgrs_status" id="srch_prgrs_status"  style='width:204px'></select>
                      				</span>
                    			</td>
                    			<td>&nbsp;</td>
                    			<td>&nbsp;</td>
                  			</tr>
                  			
                  			<tr>
                    			<th><spring:message code="las.page.field.lawconsult.title" /></th>
                    			<td>
                    				<input id="srch_title" name="srch_title" value="<c:out value='${command.srch_title}' escapeXml='false'/>" type="text" class="text"  style='width:200px'/>
                    			</td>
                    			<th><spring:message code="las.page.field.manageCommon.srchLasCnsdmanNm" /><span class="astro"></span></th>
                    			<td>
                      				<span style="width:35%;">
                        			<input id="srch_respman_nm" name="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}' escapeXml='false'/>" type="text" class="text" style='width:200px'/>
                      				</span>
                    			</td>
                    			<td>&nbsp;</td>
                    			<td>&nbsp;</td>
                  			</tr>
                		</table>
              		</td>
              		
              		<td class="vb tC"><a href="javascript:search()"><img src="<%=IMAGE%>/btn/btn_search.gif" alt='<spring:message code="las.page.field.lawconsulting.search"/>' /></a></td>
            	</tr>
          	</table>
        </div>
      	</div>
      	<!-- //search -->
      	
      	
      	<div class="t_titBtn">
        <!-- button -->
        <div class="btn_wrap_t fR">
          	<span class="btn_all_w"><span class="edit"></span><a href="javascript:goInsertForm();"><spring:message code="las.page.button.lawconsult.newreq" /><!-- 의뢰신청 --></a></span>
          	<span class="btn_all_w" onclick="excelDownLoad();"><span class="excel_down"></span><a> <spring:message code='las.page.button.excelDownload' /></a></span><!-- 엑셀 다운로드 -->
        </div>
        <!-- //button -->
      	</div>
      	<!-- list -->
      	
      	<table class="list_basic">
        	<colgroup>
	          	<col width="5%" />
	          	<col width="45%"/>
	          	<col width="16%" />
	          	<col width="16%" />
	          	<col width="8%" />
	          	<col width="10%" />
        	</colgroup>
        	<thead>
	          	<tr>
		            <th><spring:message code="las.page.field.lawconsult.no" /></th>
		            <th><spring:message code="las.page.field.lawconsult.title" /></th>
		            <th><spring:message code="las.page.field.contractManager.requBy" /></th>
		            <th><spring:message code="las.page.field.manageCommon.srchLasCnsdmanNm" /></th>
		            <th><spring:message code="las.page.field.lawconsult.reg_dt" /></th>
		            <th><spring:message code="las.page.field.lawconsult.status" /></th>
	          	</tr>
        	</thead>
        
        	<tbody>
        		<c:choose>
	          		<c:when test="${pageUtil.totalRow > 0}">
	            		<c:forEach var="list" items="${command.lawconsult_list}">
	              			<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
				                <td class="tC"><c:out value='${list.rm}'/></td>
				                <td class="tL"><a href="javascript:goDetail('<c:out value="${list.cnslt_no}"/>','<c:out value="${list.hstry_no}"/>')"><c:out value='${list.title}' escapeXml="false"/></a></td>
				                <td class="tL"><c:out value='${list.reg_nm}'/></td>
				                <td class="tL"><c:out value='${list.respman_nm}'/></td>
				                <td class="tC"><c:out value='${list.reg_dt_date}'/></td>
				                <td class="tC"><c:out value='${list.prgrs_status_name}'/></td>
	              			</tr>
	            		</c:forEach>
	          		</c:when>
	          		<c:otherwise>
	            		<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
		              		<td colspan="6" class='tC'><spring:message code="las.page.msg.noResult" /></td>
		            	</tr>
	          		</c:otherwise>
        		</c:choose>
        	</tbody>
      	</table>
      	<!-- //list -->
      	<br>
      
      	<div class="total_num">
          	Total : <%= StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow())) %>
        </div>
      	
        <!-- button -->
<!--         <div class="btnwrap mt_22"> -->
<%--           	<span class="btn_all_w"><span class="edit"></span><a href="javascript:goInsertForm();"><spring:message code="las.page.button.lawconsult.newreq" /></a></span> --%>
<%--           	<span class="btn_all_w" onclick="excelDownLoad();"><span class="excel_down"></span><a> <spring:message code='las.page.button.excelDownload' /></a></span><!-- 엑셀 다운로드 --> --%>
<!--         </div> -->
        <!-- //button -->
    
      	<!-- pagination -->
      	<div class="t_titBtn">
	        <div class="pagination">
	          	<%= pageUtil.getPageNavi(pageUtil, "") %>
	        </div>
      	</div>
      	<!-- //pagination -->
	</form:form>
      
    </div>
    </div>
    <!-- //content -->
    
    <jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
  	
  	</div>
  	<!-- //container -->
</div>

<!-- footer -->
<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
<!-- // footer -->

<jsp:include page="/WEB-INF/jsp/secfw/common/DlgAlert.jsp"/>
</body>
</html>