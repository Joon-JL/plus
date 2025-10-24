<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%--
/**
 * 파  일  명 : WeekSchedule_i.jsp
 * 프로그램명 : 일정관리_등록/수정 팝업
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 2011.09
 */
--%>
<%
	ListOrderedMap lom = (ListOrderedMap)request.getAttribute("lom");
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><spring:message code="las.page.field.board.weekSchedule.header" /></title>
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet" />
<link href="<%=CSS%>/popup.css" type="text/css" rel="stylesheet" />
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/jquery-1.6.1.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/new_style.js' />"></script>

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

	/**
	* 년, 월 값 selectbox값 변경시 조회
	*/
	function changeSelect(){
		
		var frm = document.frm;
		
		frm.action = "<c:url value='/las/board/weekSchedule.do' />" ;
		frm.method.value = "detailWeekSchedule";

		frm.submit();
	}

	/**
	* 저장
	*/
	function save() {
		var f = document.frm ;		
		f.method.value = "insertWeekSchedule" ;
		f.action = "<c:url value='/las/board/weekSchedule.do' />" ;

		f.submit() ; 
	}

	/**
	* 창닫기 - 리스트를 통해 호출된경우 리스트화면 refresh
	*/
	function cancel() {

		<c:choose>
			<c:when test="${command.path_gbn=='Main'}">
				self.close();
			</c:when>
			<c:otherwise>
				var f = window.opener.document.frm ;		
				f.method.value = "listWeekSchedule" ;
				f.action = "<c:url value='/las/board/weekSchedule.do' />" ;
				f.target="_self";
				f.submit() ; 
				self.close();
			</c:otherwise>
		</c:choose>
	}

	/**
	* 하루동안 창을 열지 않기
	*/
	function closeWin(){
		var frm = document.frm ;	
		if (frm.closeEvent.checked ) {
			setCookie("weekSchedule", "no" , 1); // 1일 간 쿠키적용 
		}
		self.close();
	}
	
	/**
	* 메시지 처리
	*/
	function showMessage(msg) {
		if( msg != "" && msg != null && msg.length > 1 ) {
			alert(msg);
		}
	}

</script>
<base target="_self"></base>
</head>
<body class="pop" onLoad='showMessage("<c:out value='${returnMessage}'/>");'>
<form:form name="frm" id="frm" method="post" autocomplete="off">	
<input type="hidden" name="method" 		value="" />
<input type="hidden" name="menu_id" 	value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage" 	value="<c:out value='${command.curPage}'/>" />
<input type="hidden" name="weekFirstDay" value="<c:out value='${command.weekFirstDay}'/>" />
<input type="hidden" name="weekLastDay"	value="<c:out value='${command.weekLastDay}'/>" />
<!-- key Form -->
<input type="hidden" name="user_id" id="user_id" value="<c:out value='${lom.user_id}'/>" />
<input type="hidden" name="user_nm" id="user_nm" value="<c:out value='${lom.user_nm}'/>" />

<input type="hidden" name="path_gbn" id="path_gbn" value="<c:out value='${command.path_gbn}'/>" />
<!-- // **************************** Form Setting **************************** -->
<!-- header -->
<h1><spring:message code="las.page.title.board.WeekSchedule" /><span class="close" onClick="javascript:self.close();" title="close"></span></h1>
<!-- //header -->
<div class="pop_area">
	<!-- content -->
	<div class="pop_content"  style='height:'>
			
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
			
			<div class="title_basic">
				<h4><c:out value='${lom.user_nm }'/></h4>
			</div>
			
			<!-- table01 -->
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
				<colgroup>
					<col width="50%"/>
					<col width="50%"/>			 
				</colgroup>
				<thead>
					<tr>
					  	<th><spring:message code='las.page.field.board.weekSchedule.crntweekRslt' /></th>
					  	<th><spring:message code='las.page.field.board.weekSchedule.nextweekPlan' /></th>
					</tr>
				</thead>
				<tbody>
					<tr class="Nocol">
						<td class="tL">
							<textarea name="crntweek_rslt" id="crntweek_rslt" cols="10" rows="17" class="text_area_full"><c:out value='${command.crntweek_rslt}' escapeXml='false' /></textarea> 
						</td>
						<td class="tL">
							<textarea name="nextweek_plan" id="nextweek_plan" cols="10" rows="17" class="text_area_full"><c:out value='${command.nextweek_plan}' escapeXml='false' /></textarea> 
						</td>
					</tr>
					<tr>
						<td class="tL" colspan="2">
							<div class="tal_txt_style">
								<p class="txt_style01"><spring:message code="las.page.field.board.weekSchedule.sample1"/></p>
								<p class="txt_style02"><spring:message code="las.page.field.board.weekSchedule.sample2"/></p>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
			<!--// table01 -->
		<!-- //content -->
		</div>	
	</div>
</div>	
<!-- //body -->
<!--footer -->
<div class="pop_footer">
	<div class="btn_wrap fR">
<c:if test="${command.path_gbn=='Main' }">
		<span class="tal_txt_style"><input type="checkbox" name="closeEvent" onClick="closeWin()">&nbsp;<spring:message code="las.msg.inform.noPopup" />&nbsp;</input></span>
</c:if>
		<span class="btn_all_w"><span class="save"></span><a href="javascript:save();"><spring:message code='las.page.button.save' /></a></span>
		<span class="btn_all_w"><span class="cancel"></span><a href="javascript:cancel();" onclick="javascript:self.close();"><spring:message code='las.page.button.close' /></a></span>
	</div>
</div>
<!-- //footer -->
<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</form:form>
<!-- //container -->	
</body>
</html>
