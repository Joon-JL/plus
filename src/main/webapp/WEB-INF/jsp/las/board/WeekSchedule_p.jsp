<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%--
/**
 * 파  일  명 : WeekSchedule_l.jsp
 * 프로그램명 : 일정관리 리스트
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 2011.09
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
	
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><spring:message code="las.page.field.board.weekSchedule.header" /></title>
<link href="<%=CSS%>/las.css"    type="text/css" rel="stylesheet">
<link href="<%=CSS%>/popup.css"    type="text/css" rel="stylesheet">

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript">

	//년도 selectbox 만들기
	function getYearSelect() {
		
		var today = new Date();
		var i = today.getFullYear();
	   
	  	for(i=today.getFullYear();i >= today.getFullYear()-5; i--) {
	   		if("<c:out value='${command.year}' />" != i) {
	    		document.write('<option value="'+i+'">'+i);
	   		} else {
	    		document.write('<option value="'+i+'" selected>'+i);
	   		}
	  	}
	 }
	
	function forwardModify(user_id) {

		if(confirm("<spring:message code='las.msg.ask.update'/>")) {

		    var frm = document.frm;
		    frm.user_id.value = user_id;
		    
		    PopUpWindowOpen2('', 800, 600, true);
		    frm.target = "PopUpWindow2";
			
			frm.action = "<c:url value='/las/board/weekSchedule.do' />";
			frm.method.value = "forwardInsertWeekSchedule";
			frm.submit();
		}
	}

	function excelDownLoad() {
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/las/board/weekSchedule.do' />" ;
		frm.method.value = "excelDownLoad";
		frm.submit();
	}

	function changeSelect(){
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/las/board/weekSchedule.do' />" ;
		frm.method.value = "listWeekSchedule";
		frm.submit();
	}

	/**
	* 메시지 처리
	*/
	function showMessage(msg) {
		if(msg != "" && msg != null && msg.length > 1) {
			alert(msg);
		}
	}

</script>
</head>
<body class="pop">
<!-- 
**************************** Form Setting **************************** 
-->
<form:form name="frm" id="frm" method="post" autocomplete="off">
<!-- search form -->
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>" />	
<input type="hidden" name="weekFirstDay" value="<c:out value='${command.weekFirstDay}'/>" />	
<input type="hidden" name="weekLastDay" value="<c:out value='${command.weekLastDay}'/>" />	
	
<!-- key Form -->
<input type="hidden" name="user_id" id="user_id" value="" />

<!-- EXCEL 관련-->
<input type="hidden" id="exel_nm_pop" 		name="exel_nm_pop" 		value="" />
<input type="hidden" id="exel_vel_pop" 		name="exel_vel_pop" 	value="" />	
<input type="hidden" id="exel_nm" 			name="exel_nm" 			value="" />
<input type="hidden" id="exel_vel" 			name="exel_vel" 		value="" />
<input type="hidden" id="start_datetime"	name="start_datetime" 	value="" />
<input type="hidden" id="end_datetime" 		name="end_datetime" 	value="" />	
<!-- // **************************** Form Setting **************************** -->

<!-- header -->
<h1><spring:message code="las.page.title.board.WeekSchedule" /><span class="close" onclick="javascript:self.close();" title="close"></span></h1>
<!-- //header -->
<!-- body -->
<div class="pop_area">
	<!-- Popup Size 840*600 -->
	<div>		
		<!-- container -->	
		<div class="pop_content">
			<!-- search-->
			<div class="search_box">
				<div class="search_box_inner">
					<table class="search_tb">
						<colgroup>
							<col />
							<col width="75px" />
						</colgroup>
						<tr>
							<td>
								<table class="search_form">
									<colgroup>
										<col width="10%"/>
										<col width="47%"/>
										<col width="10%"/>
										<col width="23%"/>
										<col width="5%" />
										<col width="5%" />
									</colgroup>
									<tr>
										<th><spring:message code='las.page.field.statistics.schedule' /></th>
										<td>
											<select name="year" id ="year" onChange="javascript:changeSelect();">
												<script>getYearSelect();</script> 
											</select> <spring:message code='las.page.field.log.year' />
											<select name="weekseq" id ="weekseq" onChange="javascript:changeSelect();">
												<c:forEach var="i" begin="1" end="53">
													<option value="<c:out value='${i}'/>" <c:if test="${command.weekseq==i}">selected</c:if>><c:out value='${i}'/></option>
												</c:forEach>
											</select> <spring:message code='las.page.field.board.weekSchedule.week' /> (<c:out value='${command.weekFirstDay }' /> ~ <c:out value='${command.weekLastDay }' />)
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</table>
							</td>
							<td class="vb tC">
								<a href="javascript:changeSelect();"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="las.page.button.search"/>" /></a>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- //search -->
			<!-- button -->
			<div class="btn_wrap_t fR">
				<span class="btn"><span class="excel_down"></span><a href="javascript:excelDownLoad()"> <spring:message code='las.page.button.excelDownload' /></a></span>
			</div>
			<!-- //button -->
			<!-- table01 -->
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
				<colgroup>
					<col width="14%"/>
					<col width="43%"/>
					<col width="43%"/>			 
				</colgroup>
				<thead>
					<tr>
						<th><spring:message code='las.page.field.board.weekSchedule.name' /></th>
						<th><spring:message code='las.page.field.board.weekSchedule.crntweekRslt' /></th>
						<th><spring:message code='las.page.field.board.weekSchedule.nextweekPlan' /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="list" items="${lom}">
						<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
						    <td>
						    <c:if test="${command.session_user_nm==list.user_nm || command.auth_modify==true}"><font color=blue><a href='javascript:forwardModify("<c:out value='${list.user_id }'/>");'></c:if><c:out value='${list.user_nm}' default="" /></td>
							<td class="tL"><c:out value="${list.crntweek_rslt}" escapeXml="false" /></td>						
							<td class="tL"><c:out value="${list.nextweek_plan}" escapeXml="false" /></td>			          			
						</tr>
					</c:forEach>
				</tbody>					
			</table>
			<!--// table01 -->
		</div>
	</div>
	<!-- //Popup Detail -->
</div>
<!-- //body -->
<!--footer -->
<div class="pop_footer">
	<div class="btn_wrap fR">
		<span class="btn_all_w"><span class="cancel"></span><a href="#" onclick="javascript:self.close();"><spring:message code='las.page.button.close' /></a></span>
	</div>
</div>
<!-- //footer -->
<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</form:form>
</body>
</html>