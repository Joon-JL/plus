<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
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
	
	ArrayList array = (ArrayList)request.getAttribute("lom");
	
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="las.page.field.board.weekSchedule.header" /></title>
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->

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
		    
		    PopUpWindowOpen2('', 800, 600, false);
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
<body onLoad='showMessage("<c:out value='${returnMessage}'/>");' id='top'>

<div id="wrap">	
	<div id="container">
		<!-- location -->
		<div class="location"><IMG SRC="<%=IMAGE %>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //location -->
				
		<!-- title -->
		<div class="title">
			<h3><spring:message code="las.page.title.board.WeekSchedule" /></h3>
		</div>
		<!-- //title -->
		
		<!-- content -->
		<div id="content">
		<div id="content_in">
		
		<!--**************************** Form Setting ****************************-->
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
			
			<!-- Search -->
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
			<!-- button -->
			<div class="t_titBtn">
				<div class="btn_wrap_t">
					<span class="btn_all_w"><span class="excel_down"></span><a href="javascript:excelDownLoad()"> <spring:message code='las.page.button.excelDownload' /></a></span>
				</div>
			</div>
			<!-- //button -->
			<!-- List -->
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
						    <td class='tC'>
						    <c:if test="${command.session_user_id==list.user_id || command.auth_modify==true}"><font color=blue><a href='javascript:forwardModify("<c:out value='${list.user_id }'/>");'></c:if><c:out value='${list.user_nm}' default="" /></td>
							<td class="tL"><c:out value="${list.crntweek_rslt}" escapeXml="false" /></td>						
							<td class="tL"><c:out value="${list.nextweek_plan}" escapeXml="false" /></td>			          			
						</tr>
					</c:forEach>
<%-- 					<%for(int i=0; array.size()<i; i++){ --%>
<!-- 					ListOrderedMap lom = (ListOrderedMap) array.get(i); -->
<!--  					%> --%> -->
<%-- 						<td class="tL"><%=StringUtil.convertEnterToBR((String)lom.get("crntweek_rslt"))%></td> --%>
						
<%-- 					<%}%> --%>
				</tbody>					
			</table>
		    <!-- //list -->
		    <div class='tR mt20'><a href="#top"><img src="<%=IMAGE%>/icon/ico_top.gif" alt="Top"></a></div>
		    </form:form>
		
		
			</div>
		</div>
	</div>
</div>

<!-- footer  -->
<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
<!-- // footer -->
<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
		
</body>
</html>