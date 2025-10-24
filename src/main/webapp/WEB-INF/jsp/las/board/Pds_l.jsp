<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%@ page import="com.sec.las.board.dto.BbsForm" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%--
/**
 * 파  일  명 : Bbs_l.jsp
 * 프로그램명 : 열린마당 목록
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 2011.08
 */
--%>
<%
	BbsForm command = (BbsForm)request.getAttribute("command");
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	List resultList = (List)request.getAttribute("resultList");
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.main.title" /></title>
<LINK href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value="/script/secfw/common/common.js"/>"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script language="javascript">

	/**
	* 달력 setting
	*/
	$(document).ready(function(){
	    initCal("srch_start_dt");   
	    initCal("srch_end_dt","20110804083442204_0000000163");  
	}); 

	/**
	* 페이징 function
	*/
	function goPage(pgNum){
		
		var frm = document.frm;
		viewHiddenProgress(true) ;
		frm.method.value = "listPds";
		frm.target = "_self";
		frm.action = "<c:url value='/las/board/bbs.do' />";
		frm.curPage.value = pgNum;
		
		frm.submit();
	}
  
	/**
	* 버튼 동작 부분
	*/ 
	function pageAction(flag){
		var frm = document.frm;

		if(flag == "search"){

			var startDt = frm.srch_start_dt.value;
			var endDt = frm.srch_end_dt.value;

			if(startDt>endDt){
				alert("<spring:message code='clm.msg.alert.board.srchAnnceDt'/>");
				frm.srch_start_dt.focus();
				return;
			}
			frm.target = "_self";
			frm.action = "<c:url value='/las/board/bbs.do' />";
			frm.method.value = "listPds";
			frm.curPage.value = "1";
			viewHiddenProgress(true) ;
			frm.submit();
			
		}else if(flag == "new"){
			frm.target = "_self";
			frm.action = "<c:url value='/las/board/bbs.do' />";
			frm.method.value = "forwardInsertPds";
		    viewHiddenProgress(true) ;
			frm.submit();
		}
	}
	
	/**
	* 상세내역 조회
	*/
	function detailView(grp_no,grp_seqno,postup_srt,postup_depth,total_seqno){
		
		var frm = document.frm;
		
		viewHiddenProgress(true) ;
		
		frm.grp_no.value		= grp_no;
	    frm.grp_seqno.value		= grp_seqno;
	    frm.postup_srt.value	= postup_srt;
	    frm.postup_depth.value	= postup_depth;
	    frm.total_seqno.value	= total_seqno;

		frm.target = "_self";
		frm.action = "<c:url value='/las/board/bbs.do' />";
		frm.method.value = "detailOpenBbs";
		frm.submit();		
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
</head>

<body onLoad='showMessage("<c:out value='${returnMessage}'/>");'>
<!-- 
**************************** Form Setting **************************** 
-->
<form:form name="frm" id='frm' method="post" autocomplete="off"> 
<!-- search form -->
<input type="hidden" name="method"  value="" />
<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>" />	
<!-- key Form -->
<input type="hidden" name="bbs_knd" value="<c:out value='${command.bbs_knd}'/>" />
<input type="hidden" name="insert_kbn" value="0" />
<input type="hidden" name="grp_no" value="<c:out value='${command.grp_no}' />" />
<input type="hidden" name="grp_seqno" value="<c:out value='${command.grp_seqno}' />" />
<input type="hidden" name="postup_srt" value="<c:out value='${command.postup_srt}' />" />
<input type="hidden" name="postup_depth" value="<c:out value='${command.postup_depth}' />" />
<input type="hidden" name="total_seqno" value="<c:out value='${command.total_seqno}' />" />
<input type="hidden" name="isPds" id="isPds" value="<c:out value='${command.isPds}' />" />

<!-- //
**************************** Form Setting **************************** 
-->
<div id="wrap">	
	<div id="container">
		<!-- Location -->
		<div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0" alt="Home" /><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->
		<!-- content -->
		<div id="content">
			<!-- title -->
			<div class="title">
				<c:choose>
					<c:when test="${command.bbs_knd=='C01204' || command.bbs_knd=='C01205'}">
					<h3><spring:message code="las.page.title.board.pds_1" /></h3>
					</c:when>
					<c:when test="${command.bbs_knd=='C01206' || command.bbs_knd=='C01207'}">
					<h3><spring:message code="las.page.title.board.pds_2" /></h3>
					</c:when>
					<c:when test="${command.bbs_knd=='C01208' || command.bbs_knd=='C01209'}">
					<h3><spring:message code="las.page.title.board.pds_3" /></h3>
					</c:when>
					<c:when test="${command.bbs_knd=='C01210' || command.bbs_knd=='C01211'}">
					<h3><spring:message code="las.page.title.board.pds_4" /></h3>
					</c:when>
					<c:when test="${command.bbs_knd=='C01212' || command.bbs_knd=='C01213'}">
					<h3><spring:message code="las.page.title.board.pds_5" /></h3>
					</c:when>
				</c:choose>
			</div>
			<!-- search-->
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
										<col width="5%"/>
										<col width="10%"/>
										<col width="35%"/>
										<col width="5%"/>
										<col width="30%"/>
										<col width="5%"/>
									</colgroup>
									<tr>
										<!--  국내 해외 검색 -->
										<th><spring:message code="las.page.field.bbs.srch_cond"/></th>
										<td>
											<select name="srch_dmstfrgn_gbn"  class="select" id="srch_dmstfrgn_gbn" >
												<option value="dmst" <c:if test="${command.srch_dmstfrgn_gbn == 'dmst'}">selected</c:if>><spring:message code="las.page.field.board.pds.dmst"/></option>
												<option value="frgn" <c:if test="${command.srch_dmstfrgn_gbn == 'frgn'}">selected</c:if>><spring:message code="las.page.field.board.pds.frgn"/></option>
												<option value="all" <c:if test="${command.srch_dmstfrgn_gbn == 'all'}">selected</c:if>><spring:message code="las.page.field.board.pds.all"/></option>							
											</select>
										</td>
										
										<th><spring:message code="las.page.field.bbs.srch_cond"/></th>
										<td>
											<select name="srch_type"  class="select" id="select2" >
												<option value="title" <c:if test="${command.srch_type=='title'}">selected</c:if>><spring:message code="las.page.field.board.notice.title"/></option>
												<option value="reg_nm" <c:if test="${command.srch_type=='reg_nm'}">selected</c:if>><spring:message code="las.page.field.board.notice.reg_nm"/></option>
												<option value="cont" <c:if test="${command.srch_type=='cont'}">selected</c:if>><spring:message code="clm.page.msg.common.content"/></option>							
											</select>
											<input type="text" name="srch_word" class="text" style="width:150px" value="<c:out value='${command.srch_word}'/>" maxLength="100" OnKeyDown="if(event.keyCode==13) { pageAction('search'); }"/>
										</td>
									    <th><spring:message code="las.page.field.bbs.date"/></th>
										<td><!-- calendar -->
											<input type="text" name="srch_start_dt" id="srch_start_dt" value="<c:out value='${command.srch_start_dt}'/>"  class="text_calendar02" readonly="readonly"/> ~							
											<input type="text" name="srch_end_dt" id="srch_end_dt" value="<c:out value='${command.srch_end_dt}'/>" class="text_calendar02" readonly="readonly" />
										</td>
										<td>&nbsp;</td>
									</tr>
								</table>
							</td>
							<td class="vb tC">
								<a href="javascript:pageAction('search');"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="las.page.button.search"/>" /></a>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- //search-->
			<!-- button -->
			<div class="t_titBtn">
				<div class="btn_wrap_t">
					<%if(command.isAuth_insert()){ %> 	
					<span class="btn_all_b"><span class="save"></span><a href="javascript:pageAction('new');"><spring:message code="las.page.button.new"/></a></span>
					<%} %>
				</div>
			</div>
			<!-- //button -->
			<!-- List -->
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
				<colgroup>					
					<col width="6%" />
					<col width="6%" />
					<col width="45%" />
					<col width="21%" />
					<col width="12%" />
					<col width="10%" />
				</colgroup>
			  	<thead>
					<tr>
					  <th><spring:message code="las.page.field.board.notice.no" /></th>	<!-- 번호 -->
					  <th><spring:message code="las.page.field.contract.library.dmstfrgn" /></th>	<!-- 구분 -->
					  <th><spring:message code="las.page.field.contract.library.title" /></th>	<!-- 제목 -->
					  <th><spring:message code="las.page.field.contract.library.reg_nm" /></th>	<!-- 작성자 -->
					  <th><spring:message code="las.page.field.contract.library.reg_dt" /></th>	<!-- 작성일 -->
					  <th><spring:message code="las.page.field.contract.library.rdcnt" /></th>	<!-- 조회수 -->
					</tr>
			  	</thead>
			  	<tbody>
			  	
			  	<c:choose>
					<c:when test="${pageUtil.totalRow > 0}">				
						<c:forEach var="list" items="${resultList}">
						<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
							<td><c:out value='${list.rn}'/></td>
							<td>
							<c:choose>
								<c:when test="${list.dmstfrgn_gbn == 'Y' }">
									<spring:message code="las.page.field.board.pds.dmst" />	
								</c:when>
								<c:otherwise>
									<spring:message code="las.page.field.board.pds.frgn" />
								</c:otherwise>
							</c:choose>
							</td>
						    <td class="tL"><a href="javascript:detailView('<c:out value="${list.grp_no}"/>','<c:out value="${list.grp_seqno}"/>','<c:out value="${list.postup_srt}"/>','<c:out value="${list.postup_depth}"/>','<c:out value="${list.total_seqno}"/>')">
						    <c:if test="${list.postup_depth > 0 }">
						    <c:forEach var="x" begin="1" end="${list.postup_depth}" step="1" >&nbsp;</c:forEach>
						    <img src="<%=IMAGE%>/icon/icon_reply.gif" />
						    </c:if>
						    <c:out value='${list.title}' escapeXml="false"/></a></td>
						    <td class="tL"><c:out value='${list.reg_nm}'/>/<c:out value='${list.reg_jikgup_nm}'/>/<c:out value='${list.reg_dept_nm}'/></td>
							<td><c:out value='${list.reg_dt}'/></td>
							<td><c:out value='${list.rdcnt}'/></td>
						</tr>
						</c:forEach>
			   		 </c:when>
			   		 <c:otherwise>
			     	     <tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
			      	      <td colspan="7"><spring:message code="las.page.msg.noResult" /></td>
			      	    </tr>
			   		 </c:otherwise>
				</c:choose>
				 </tbody>
			</table>
			<!-- //List -->
			<div class="t_titBtn">
				<div class="total_num">Total: <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%><spring:message code="las.page.field.board.case"/></div>
				<!-- Paging  -->
				<div class="pagination"> 
					<%=pageUtil.getPageNavi(pageUtil, "") %>
				</div>
				<!-- // Paging -->
			</div>
		</div>		
		<!-- //content -->
		<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- // footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
</form:form>
</body>
</html>
