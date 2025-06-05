<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@page import="com.sds.secframework.common.util.PageUtil"%>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%--
/**
 * 파  일  명 : LawStaConsultReqman_l.jsp
 * 프로그램명 : 표준계약서 리스트 (의뢰인 페이지)
 * 설      명 : 표준계약서의 리스트만을 보여주게 됩니다.
 * 작  성  자 : 
 * 작  성  일 : 2013.04
 */
--%>
<%
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil") ;
	//메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms.js' />"></script>

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
	
	function search(){
		goPage(1) ;
	}
	
	function init(){
    	alertMessage('<c:out value='${command.return_message}'/>');
    }
	
	function goPage(page) {
		viewHiddenProgress(true) ;
		var f = document.frm ;
	
		var startDt = f.srch_start_ymd.value ;
		var endDt = f.srch_end_ymd.value ;
	
		if(startDt>endDt){
			viewHiddenProgress(false) ;
			alert("<spring:message code='las.page.field.lawconsulting.setFromDateEarly'/>") ;
			f.srch_start_ymd.focus() ;
			
			return ;
		}
		
		f.cnslt_no.value = "";
		f.hstry_no.value = 0;
		f.curPage.value = page ;
		f.method.value = "listStaLawConsultRequest" ;
		f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
		f.target = "_self" ;
		f.submit() ;
	}
	
	function goInsertForm() {
		viewHiddenProgress(true) ;
		var f = document.frm ;
		f.cnslt_no.value = "";
		f.hstry_no.value = 0;
		f.method.value = "forwardInsertStaLawConsult" ;
		f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
		f.target = "_self" ;
		f.submit() ; 
	}
	
	function goDetail(cnslt_no, hstry_no) {
		viewHiddenProgress(true) ;
		var f = document.frm ;
		f.cnslt_no.value = cnslt_no;
		f.hstry_no.value = hstry_no;
		f.method.value = "forwardDetailStaLawConsultReqman" ;
		f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
		f.target = "_self" ;
		f.submit() ; 
	}

</script>

</head>
<!--  2012.04.26 Alert 수정 modified by hanjihoon  -->
<body onload="init()" autocomplete="off">
<div id="wrap">
	
	<!-- container -->
	<div id="container">
		
		<!-- Location -->
        <div class="location">
            <img src="<%=IMAGE%>/icon/ico_home.gif" border="0" alt="Home"/><c:out value='${menuNavi}' escapeXml="false"/>
        </div>
        <!-- //Location -->
		
	
		<!-- title -->
		<div class="title">
			<h3><spring:message code="las.page.field.lawconsulting.stndFormReq"/></h3>
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
			<input type="hidden" name="cnslt_no" />
			<input type="hidden" name="hstry_no" />
		
				<!-- search-->
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
									<col width="25%"/>
									<col width="15%"/>
									<col width="*%"/>
								</colgroup>
								<tr>
									<th><spring:message code="las.page.field.lawconsult.reg_dt" /></th>
									<td>
										 <input type="text" name="srch_start_ymd" id="srch_start_ymd" value="<c:out value='${command.srch_start_ymd}'/>"  class="text_calendar02" style="width:89px;"/> ~
    									 <input type="text" name="srch_end_ymd" id="srch_end_ymd" value="<c:out value='${command.srch_end_ymd}'/>" class="text_calendar02" style="width:89px;"/></td>
									<th><spring:message code="las.page.field.lawconsult.prgrs_status" /><span class="astro"></span></th>
									<td>
									   <select name="srch_prgrs_status" id="srch_prgrs_status"  style="width:203px;">
								      </select>
									  </td>
									
								</tr>
								<tr>
									<th><spring:message code="las.page.field.lawconsult.title" /></th>
									<td><input id="srch_title" name="srch_title" value="<c:out value='${command.srch_title}' escapeXml='false'/>" type="text" class="text"  style="width:230px;"/></td>
									<th><spring:message code="las.page.field.manageCommon.srchLasCnsdmanNm" /><span class="astro"></span></th>
							    	<td>
							        	<input id="srch_respman_nm" name="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}' escapeXml='false'/>" type="text" class="text" style="width:200px;"/>
							        </td>
							    	
								</tr>
							</table>
						</td>
						
						<td class="vb tC"><a href="javascript:search()"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="las.page.field.lawconsulting.search"/>"/></a></td>
						</tr>
					</table>
				</div>		
			</div>

			<div class="t_titBtn">
					<!-- button -->
					<div class="btn_wrap_t fR">
						<span class="btn_all_w"><span class="edit"></span><a href="javascript:goInsertForm();"><spring:message code="las.page.button.lawconsult.newStareq" /></a></span>
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
						<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
							<td class='tC'><c:out value='${list.rm}'/></td>
						    <td class="tL"><a href="javascript:goDetail('<c:out value="${list.cnslt_no}"/>','<c:out value="${list.hstry_no}"/>')"><c:out value='${list.title}' escapeXml="false"/></a></td>
							<td class='tL'><c:out value='${list.reg_nm}'/></td>
							<td class='tL'><c:out value='${list.respman_nm}'/></td>
							<td class='tC'><c:out value='${list.reg_dt_date}'/></td>
							<td class='tC'><c:out value='${list.prgrs_status_name}'/></td>
						</tr>
						</c:forEach>
			   		 </c:when>
			   		 <c:otherwise>
			     	     <tr onmouseout="this.className='';" onmouseover="this.className='selected'">
			      	      <td colspan="6" class="tC"><spring:message code="las.page.msg.noResult" /></td>
			      	    </tr>
			   		 </c:otherwise>
				</c:choose>
		      </tbody>
		  </table>
			<!-- //list -->
			<br/>
			
					
			
			
			<!-- pagination -->
			<div class="total_num">
				Total : <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%>
			</div>
				
				
			<!-- button -->
			<div class="btnwrap mt_22">
				<span class="btn_all_w"><span class="edit"></span><a href="javascript:goInsertForm();"><spring:message code="las.page.button.lawconsult.newStareq" /></a></span>
			</div>
			<!-- //button --> 
			
				
				
			<div class="pagination">
				<%=pageUtil.getPageNavi(pageUtil, "") %>	
			</div>
			
			<!-- //pagination -->
			
		
			
			</form:form>
		</div>
		</div>
		<!-- //content -->
		
		
	</div>
	<!-- //container -->
</div>

<jsp:include page="/WEB-INF/jsp/secfw/common/DlgAlert.jsp"/>
<!-- footer  -->
<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
<!-- // footer -->
<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
<!-- //container -->
</body>
</html>
