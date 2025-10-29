<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.las.board.dto.OpinionAcceptanceForm" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%--
/**
 * 파  일  명 : OpinionAcceptance_l.jsp
 * 프로그램명 : 일정관리 리스트
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 2011.09
 */
--%>
<%
	OpinionAcceptanceForm command = (OpinionAcceptanceForm)request.getAttribute("command");
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	List resultList = (List)request.getAttribute("resultList");
	
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message
		code="las.page.field.board.weekSchedule.header" />
</title>
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->

<script language="javascript"	src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript"	src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript"	src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript"	src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript"	src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript"	src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript"	src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script language="javascript">
	/**
	 * 달력 setting
	 */
	$(document).ready(function() {
		initCal("srch_start_dt");
		initCal("srch_end_dt", "20110804083441819_0000000160");
	});

	function search() {
		goPage(1);
	}

	function goPage(page) {

		var frm = document.frm;

		var startDt = frm.srch_start_dt.value;
		var endDt = frm.srch_end_dt.value;

		if (startDt > endDt) {
			alert("<spring:message code='clm.msg.alert.board.srchAnnceDt'/>");
			frm.srch_start_dt.focus();
			return;
		}

		frm.curPage.value = page;
		frm.method.value = "listOpinionAcceptance";

		

		frm.action = "<c:url value='/las/board/opinionAcceptance.do' />";
		frm.target = "_self";
		frm.submit();
	}

	/**
	* 상세내역 조회
	*/
	function goInsertForm() {

		var frm = document.frm;
		
		frm.method.value = "forwardInsertOpinionAcceptance";
		frm.action = "<c:url value='/las/board/opinionAcceptance.do' />";
		frm.target = "_self";

		viewHiddenProgress(true);

		frm.submit();
	}

	/**
	* 상세내역 조회
	*/
	function goDetail(seqno,acc_seqno,postup_srt,postup_depth,total_seqno) {
		var frm = document.frm;
		frm.method.value = "detailOpinionAcceptance";
		viewHiddenProgress(true);

		frm.seqno.value		= seqno;

		frm.action = "<c:url value='/las/board/opinionAcceptance.do' />";
		frm.target = "_self";

		frm.submit();
	}

	/**
	 * 메시지 처리
	 */
	function showMessage(msg) {
		if (msg != "" && msg != null && msg.length > 1) {
			alert(msg);
		}
	}
</script>
</head>
<body onLoad='showMessage("<c:out value='${returnMessage}'/>");'
	id='top'>

	<div id="wrap">
		<div id="container">
			<!-- location -->
			<div class="location">
				<IMG SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11"
					BORDER="0" ALT="Home">
				<c:out value='${menuNavi}' escapeXml="false" />
			</div>
			<!-- //location -->

			<!-- title -->
			<div class="title">
				<h3>
					<spring:message code="las.page.title.board.OpinionAcceptance" />
				</h3>
			</div>
			<!-- //title -->

			<!-- content -->
			<div id="content">
				<div id="content_in">

					<!--**************************** Form Setting ****************************-->
					<form:form name="frm" id="frm" method="post" autocomplete="off">
						<!-- search form -->
						<input type="hidden" name="method" value="" />
						<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
						<input type="hidden" name="curPage" 	value="<c:out value='${command.curPage}'/>" />
						<input type="hidden" name="insert_kbn" value="0" />
						<input type="hidden" name="seqno"     id="seqno"     value="<c:out value='${command.seqno}'/>" />

						<!-- key Form -->
						<input type="hidden" name="user_id" id="user_id" value="" />

						<!-- // **************************** Form Setting **************************** -->

						<!--search-->
						<div class="search_box">
							<div class="search_box_inner">
								<table class="search_tb">
									<colgroup>
										<col />
										<col width="75px" />
									</colgroup>
									<tr>
										<td><table class="search_form">
												<colgroup>
													<col width="10%" />
													<col width="40%" />
												</colgroup>
												<th><spring:message code="las.page.field.bbs.condition" />
												</th>
												<td>
													<select name="srch_combo" id="select">
														<option value="title"
															<c:if test="${command.srch_combo=='title'}">selected</c:if>>
															<spring:message code="las.page.field.board.notice.title" />
														</option>
														<option value="reg_nm"
															<c:if test="${command.srch_combo=='reg_nm'}">selected</c:if>>
																<spring:message code="secfw.page.field.bbs.writer" />
														</option>
														<%-- <option value="cont" <c:if test="${command.srch_combo=='cont'}">selected</c:if>> <spring:message code="las.page.field.board.notice.cont" /></option> --%>
													</select> 
												<input type="text" name="srch_word" class="text" style="width: 70%" 	value="<c:out value='${command.srch_word}'/>" maxLength="100" OnKeyDown="if(event.keyCode==13) { search(); }" />
													</td>
												<th>
													<spring:message code="las.page.field.board.notice.reg_dt" />
												</th>
												<td>
													<input type="text" name="srch_start_dt" id="srch_start_dt" value="<c:out value='${command.srch_start_dt}'/>" class="text_calendar02" readonly="readonly"/ >
													 ~ <input 	type="text" name="srch_end_dt" id="srch_end_dt" value="<c:out value='${command.srch_end_dt}'/>" class="text_calendar02" readonly="readonly" />
												</td>
											</table>
										</td>
										<td class="vb tC">
											<a href="javascript:search();"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="las.page.button.search"/>" /></a>
										</td>
									</tr>
								</table>
							</div>
						</div>
						<!--//search-->
						<!-- button -->
						<!-- button -->
						<div class="btn_wrap_t">
						<div class="btnWrap">
							<span class="btn_all_w"><span class="edit"></span><a href="javascript:goInsertForm('new');"><spring:message code='las.page.button.new' /></a></span>
						</div>
						</div>
						<!-- //button -->
						<!-- list -->
						<table class="list_basic_new">
							<colgroup>
								<col width="5%" />
								<col width="45%" /> <!-- width size 변경 신성우 2014-04-24 -->
								<col width="30%" />
								<col width="10%" />
								<col width="10%" />
							</colgroup>
							<thead>
								<tr>
									<th><spring:message code="secfw.page.field.common.no" /><!--  NO--></th>
									<th><spring:message code="secfw.page.field.common.title" /><!--  Title--></th>
									<th><spring:message code="secfw.page.field.bbs.writer" /><!--  Writer--></th>
									<th><spring:message code="secfw.page.field.log.period" /><!--  Date--></th>
									<th><spring:message code="las.msg.field.opnion.filed002" /><!--  Participant--></th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
								<c:when test="${!empty resultList}">
								<c:forEach var="list" items="${resultList}">
								  	<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
												<td class="tC"><c:out value="${list.rn}"  /></td>
												<td class="tL overflow"  title="<c:out value="${list.title}" />"  >
										    		<a href='javascript:goDetail("<c:out value='${list.seqno}'/>");'><c:out value="${list.title}" /></a>
										        </td>
												<td class="tC overflow" title="<c:out value="${list.reg_nm}" /> / <c:out value="${list.REG_JIGYUP_NM}" /> / <c:out value="${list.REG_DEPT_NM}" />" ><c:out value="${list.reg_nm}" /> / <c:out value="${list.REG_JIGYUP_NM}" /> / <c:out value="${list.REG_DEPT_NM}" /></td>
												<td class="tC"><c:out value="${list.reg_dt}"  /></td>
												<td class="tC"><c:out value="${list.REPLY_CNT}" />/<c:out value="${list.PART_MEMBER_CNT}" /></td>				 
									</tr>
								</c:forEach>
								</c:when>
							    	<c:otherwise>
									<tr>
										<td colspan="5" align="center"><spring:message code="las.msg.succ.noResult" /></td>
									</tr>
							    	</c:otherwise>
								</c:choose>		
							</tbody>				
						</table>
						<!-- //list -->
						<div class="total_num"> Total : 	<%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%></div>
						<!----pagination----->
						<div class="pagination"><%=pageUtil.getPageNavi(pageUtil, "")%></div>
						<!----// pagination----->
				
				</form:form>
				
				</div>
				<!-- // content_in -->
				
			</div>			
			<!-- //content -->
			
			<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
			</div>
		</div>
			<!-- footer  -->
			<script language="javascript" src="/script/clms/footer.js"
				type="text/javascript"></script>
			<!-- // footer -->

		
</body>
</html>