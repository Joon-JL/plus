<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.log.dto.LogForm" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%--
/**
 * 파    일    명 : LoginLogList.jsp
 * 프 로 그 램 명 : 로그인현황
 * 작    성    자 : 금현서
 * 작    성    일 : 2009.12
 * 설          명 : 사이트에 접속한 로그인 현황에 대한 데이터를 보여준다.
 *                  2013.04 법무Lite 버전에 맞게 수정(부서가 아닌 회사로 검색) by 전종효
 */
--%>
<%
    String menuNavi      = (String) request.getAttribute("secfw.menuNavi");
    ArrayList resultList = (ArrayList) request.getAttribute("resultList");
    LogForm command      = (LogForm) request.getAttribute("command");

    // PageUtil 객체를 읽어들임
    PageUtil pageUtil = (PageUtil) request.getAttribute("pageUtil");
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="secfw.page.title.log.LoginLogList" /></title>
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/secfw/jquery/excanvas.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>

<script language="javascript">
<!--

/**
 * 달력 setting
 */
 $(document).ready(function(){
     initCal("srch_start_dt");
     initCal("srch_end_dt", "SECFW_ADMIN_LOGIN_LOG_L");   
 }); 
    /**
    * 페이징 function
    */
    function goPage(pgNum){
        var frm = document.frm;

        viewHiddenProgress(true);
        frm.start_datetime.value = frm.srch_start_dt.value + " " + frm.start_time.value ;
        frm.end_datetime.value = frm.srch_end_dt.value + " " + frm.end_time.value ;
        frm.target = "_self";
        frm.action = "<c:url value='/secfw/log.do' />";
        frm.method.value = "listLoginLog";
        frm.curPage.value = pgNum;
        frm.submit();
    }

    /**
    * 버튼 동작 부분
    */
    function pageAction(flag){
        var frm = document.frm;

        viewHiddenProgress(true);

        if(flag == "list") {
            frm.target = "_self";
            frm.action = "<c:url value='/secfw/log.do' />";
            frm.method.value = "listLoginLog";
            frm.curPage.value = "1";
            frm.search_name.value = "";
            frm.search_combo.value = "comp_nm";
            frm.srch_start_dt.value = comKeyUpDate(comToDay());
            frm.srch_end_dt.value = comKeyUpDate(comToDay());
            frm.submit();

        }else if(flag == "search"){
            frm.start_datetime.value = frm.srch_start_dt.value + " " + frm.start_time.value ;
            frm.end_datetime.value = frm.srch_end_dt.value + " " + frm.end_time.value ;
            frm.target = "_self";
            frm.action = "<c:url value='/secfw/log.do' />";
            frm.method.value = "listLoginLog";
            frm.curPage.value = "1";
            frm.submit();
        }
    }

    /**
    * 메시지 처리
    */
    function showMessage(msg) {
        if(msg != null && msg.length > 1) {
            alert(msg);
        }
    }

    /**
    * 엑셀다운로드 팝업
    */
    function excelPopLoad() {
        var frm = document.frm;

        PopUpWindowOpen('', 300, 250, false);
        frm.target = "PopUpWindow";
        //파일을 다운 팝업 필드 선택값
        frm.exel_nm_pop.value= "<spring:message code='secfw.page.field.log.etcTtInfo'/>";
        frm.exel_vel_pop.value = "emp_no,user_nm,comp_nm,dept_nm,jikgup_nm,single_id,sys_cd,login_dt,logout_dt,ip_address,browser_type,year,month,day,hour";
        frm.action = "<c:url value='/secfw/log.do' />";
        frm.method.value = "ExcelPopup";
        frm.submit();
    }

    /**
    * 엑셀다운로드
    */
    function excelDownLoad(Infos) {
        var frm = document.frm;

        frm.target = "_self";
        frm.action = "<c:url value='/secfw/log.do' />";
        frm.method.value = "excelDownLoginLog";
        frm.exel_nm.value =Infos.excel_nm;
        frm.exel_vel.value=Infos.excel_vel;
        frm.submit();
    }

    function saveLogExcel() {
        var frm = document.frm;
        frm.start_datetime.value = frm.srch_start_dt.value + " " + frm.start_time.value ;
        frm.end_datetime.value = frm.srch_end_dt.value + " " + frm.end_time.value ;
        frm.target = "_self";
        frm.action = "<c:url value='/secfw/log.do' />";
        frm.method.value = "excelDownLoadLoginLog";
        frm.submit();
    }
//-->
</script>
</head>
<body>
<div id="wrap">
  <div id="container">
    <!-- location -->
    <div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" border="0" alt="Home" title="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
    <!-- //location -->
    <!-- title -->
    <div class="title">
      <h3><spring:message code="secfw.page.field.log.logStatus"/></h3>
    </div>
    <!-- //title -->
    <!-- content -->
    <div id="content">
      <!-- content_in -->
      <div id="content_in">
        <!-- **************************** Form Setting **************************** -->
        <form:form name="frm" method="post" autocomplete="off">
          <input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>">
          <input type="hidden" name="method"   value=""><!-- 페이지 공통(리스트성조회) -->
          <input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>">
          <!-- EXCEL 관련 -->
          <input type="hidden" id="exel_nm_pop"       name="exel_nm_pop"      value="">
          <input type="hidden" id="exel_vel_pop"      name="exel_vel_pop"     value="">
          <input type="hidden" id="exel_nm"           name="exel_nm"          value="">
          <input type="hidden" id="exel_vel"          name="exel_vel"         value="">
          <input type="hidden" id="start_datetime"    name="start_datetime"   value="">
          <input type="hidden" id="end_datetime"      name="end_datetime"     value="">
          
            <!-- search-->
	            <div class="search_box">
					<div class="search_box_inner">
						<table class="search_tb">
							<colgroup>
								<col width="*px"/>
								<col width="78px"/>
							</colgroup>
							<tr>
								<td>
									
									<table class="search_form">
					                  <colgroup>
					                    <col width='10%'/>
					                    <col width='40%'/>
					                    <col width='10%'/>
					                    <col width='*%'/>
					                  </colgroup>
					                  <tr>
					                    <th><spring:message code="secfw.page.field.log.period" /></th>
					                    <td>
					                      <input type="text" name="srch_start_dt" id="srch_start_dt" value="<c:out value='${command.srch_start_dt}'/>" class="text_calendar02" readonly="readonly" />
					                      <select name="start_time">
					<%
					    for (int i=0; i<24; i++) {
					%>
					                        <option value="<%=i<10?"0"+i:String.valueOf(i)%>:00" <%=(i<10?"0"+i+":00":i+":00").equals(command.getStart_time())?" selected" : ""%>><%=i<10?"0"+i:String.valueOf(i)%>:00</option>
					<%
					    }
					%>
					                      </select>
					                      ~
					                      <input type="text" name="srch_end_dt" id="srch_end_dt" value="<c:out value='${command.srch_end_dt}'/>" class="text_calendar02" readonly="readonly" />
					                      <select name="end_time">
					<%
					    for (int i=0; i<24; i++) {
					%>
					                        <option value="<%=i<10?"0"+i:String.valueOf(i)%>:00" <%=(i<10?"0"+i+":00":i+":00").equals(command.getEnd_time())?" selected" : ""%>><%=i<10?"0"+i:String.valueOf(i)%>:00</option>
					<%
					    }
					%>
					                      </select>
					                    </td>
					                    <th>
					                      <select name="search_combo"  class="select" id="select2" >
					                        <option value="comp_nm" <%="comp_nm".equals(command.getSearch_combo()) ? "selected" : "" %>><spring:message code="secfw.page.field.common.comNm"/></option>
					                        <option value="user_nm" <%="user_nm".equals(command.getSearch_combo()) ? "selected" : "" %>><spring:message code="secfw.page.field.log.name"/></option>
					                      </select>
					                    </th>
					                    <td>
					                      <input type="text" name="search_name" class="text"value="<c:out value='${command.search_name}'/>" OnKeyDown="if(event.keyCode==13) { pageAction('search'); }"/>
					                    </td>
					                  </tr>
					                </table>
					            </td>
								<td class='tC'><a href="javascript:pageAction('search');"><img src="../../images/las/ko/btn/btn_search.gif" alt="Search" title="Search" align="absmiddle" /></a></td>
							</tr>
						</table>
					</div>
	            </div>     
				<!-- // search-->	
             
           
           
            
            
            <!-- button -->
			<div class="btnwrap">
				<span class="btn_all_w" onclick='javascript:saveLogExcel()'><span class="excel_down"></span><a><spring:message code="secfw.page.field.log.excelDown"/></a></span>
			</div>
			<!-- //button -->
            
            
            <table class="list_basic">
              <colgroup>
                <col width="25%"/>
                <col width="25%"/>
                <col width="25%"/>
                <col width="25%"/>
              </colgroup>
              <tr>
                <th><spring:message code="secfw.page.field.log.comp_nm" /><!-- 회사명 --></th>
                <th><spring:message code="secfw.page.field.log.user_nm" /><!-- 이름 --></th>
                <th><spring:message code="secfw.page.field.log.login_dt" /><!-- 로그인일시 --></th>
                <th class='end'><spring:message code="secfw.page.field.log.ip_address" /><!-- IP주소 --></th>
              </tr>
<%
    if (pageUtil.getTotalRow() > 0) {
        int idx = (pageUtil.getThisPage() -1)*pageUtil.getRowPerPage()+1;

        for (idx=0;idx < resultList.size();idx++) {
            ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
%>
              <tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
                <td class='tC'><%=lom.get("comp_nm") %></td>
                <td class='tC'><%=lom.get("user_nm") %></td>
                <td class='tC'><%=lom.get("login_dt") %></td>
                <td  class='tC'><%=lom.get("ip_address")%></td>
              </tr>
<%
        }
    } else { // 조회된 데이타가 없을경우
%>
              <tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
                <td colspan="4" align="center" class='end'><spring:message code="secfw.msg.succ.noResult" /></td>
              </tr>
<%
    }
%>
            </table>
            
            <!-- List Sum-->
			<div class="total_num ">
				Total : <%= StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow())) %>
			</div>
			<!-- //List Sum-->
            
            <!-- paging -->
            <div class="pagination"><%= pageUtil.getPageNavi(pageUtil, "") %></div>
            <!-- //paging -->
          
        </form:form>
        <!-- //**************************** Form Setting **************************** -->
        
      </div>
    </div>
  </div>
</div>
<!-- footer -->
<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
<!-- //footer -->
<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</body>
<!-- 챠트 설정 관련 폼 -->
<form:form id="frmAjax" name="frmAjax" method="post">
  <input type="hidden" name="range_date" value="10">
</form:form>
<!-- 챠트 설정 관련 폼 -->
</html>
<script>
viewHiddenProgress(false);
</script>