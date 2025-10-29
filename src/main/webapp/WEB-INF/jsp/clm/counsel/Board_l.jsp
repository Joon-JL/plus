<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.admin.dto.BoardForm" %>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%-- 
/**
 * 파  일  명 : Board_l.jsp
 * 프로그램명 : 공지사항 목록(share)
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	List resultList = (List)request.getAttribute("resultList");
	BoardForm command = (BoardForm)request.getAttribute("command");
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<link href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet" />

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>

<script language="javascript">
		
	$(document).ready(function(){
		initCal("srch_start_dt");	
		initCal("srch_end_dt","20110803092210347_0000000062");	
	});	
	
	/**
	* 페이징 function
	*/
	function goPage(pgNum){
		var frm = document.frm;

		viewHiddenProgress(true) ;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/counsel/board.do' />";
		frm.method.value = "listBoard";
		frm.curPage.value = pgNum;
		
		frm.submit();
	}
  
	/**
	* 버튼 동작 부분
	*/ 
	function pageAction(flag){
		
		var frm = document.frm;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/counsel/board.do' />";

		if(flag == "search"){
			var startDt = frm.srch_start_dt.value;
			var endDt = frm.srch_end_dt.value;

			if((startDt != "" && endDt != "") && (startDt > endDt)){
		  		alert("<spring:message code='clm.msg.alert.board.srchAnnceDt' />");
		  		return;
		  	}
			
			viewHiddenProgress(true) ;
		    frm.method.value = "listBoard";		    
			frm.curPage.value = "1";
			frm.submit();
		}
	}
	
	/**
	* 상세내역 조회
	*/
	function detailView(seqno){
		viewHiddenProgress(true) ;
		
		var frm = document.frm;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/counsel/board.do' />";
		frm.method.value = "detailBoard";
		frm.seqno.value = seqno;
		frm.submit();		
	}
	
</script>
</head>

<body>
<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id='frm' method="post" autocomplete="off"> 
	<!-- search form -->
	<input type="hidden" name="method"   value="" />
	<input type="hidden" name="menu_id"   value="<c:out value='${command.menu_id}'/>" />
	<input type="hidden" name="mode"  value="<c:out value='${command.mode}'/>" />
	<!-- Detail Page Param -->
	<input type="hidden" name="seqno" value="<c:out value='${command.seqno}'/>" />

	<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>" />
	
	<!-- key Form -->

<!-- //
**************************** Form Setting **************************** 
-->
<div id="wrap">
	<!-- container -->
	<div id="container">

		<!-- Location -->
		<div class="location">
			<img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/>
		</div>
		<!-- //Location -->

		<!-- Title -->
		<div class="title">
			<h3><spring:message code="clm.page.title.board.listTitle" /></h3>
		</div>
		<!-- //title -->
		
		<!-- content -->
		<div id="content">
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
										<col width="35%"/>
										<col width="10%"/>
										<col width="45%"/>
									</colgroup>
									<tr>
										<th><spring:message code="clm.page.field.board.srchAnnceDt" /></th>
										<td>
											<input type="text" name="srch_start_dt" id="srch_start_dt" value="<c:out value='${command.srch_start_dt}'/>"  class="text_calendar02"/>									
											~
											<input type="text" name="srch_end_dt" id="srch_end_dt" value="<c:out value='${command.srch_end_dt}'/>" class="text_calendar02"/>
										</td>
										<th><spring:message code="clm.page.field.board.srchKeyword" /></th>
								    	<td>
											<select name="srch_keyword" id="srch_keyword">
								           	  <option value="title" default <c:if test="${command.srch_keyword eq 'title'}">selected</c:if>><spring:message code="clm.page.field.board.srchTitle" /></option>
								           	  <option value="reg_nm"  <c:if test="${command.srch_keyword eq 'reg_nm'}">selected</c:if>><spring:message code="clm.page.field.board.srchRegNm" /></option>
								           	  <option value="cont"  <c:if test="${command.srch_keyword eq 'cont'}">selected</c:if>><spring:message code="clm.page.field.board.srchCont" /></option>
								        	</select>
											<input id="srch_keyword_content" name="srch_keyword_content" style="width:180px" value="<c:out value='${command.srch_keyword_content}'/>" onKeypress="if(event.keyCode == 13){pageAction('search');return false;}"/>
										</td>
									</tr>
								</table>
							</td>
							<td class="tC">
								<a href="javascript:pageAction('search');"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="clm.page.msg.common.inquire" htmlEscape="true" />"/></a>
							</td>
						</tr>
					</table>
				</div>
			</div>			
			<!--//search-->
				
			<!-- list -->
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
				<colgroup>
					<col width="10%"/>
					<col width="51%" />
					<col width="12%"/>
					<col width="15%"/>
					<col width="12%"/>
			  	</colgroup>
			  	<thead>
					<tr>
						<th><spring:message code="clm.page.field.board.no" /></th>
						<th><spring:message code="clm.page.field.board.title" /></th>
						<th><spring:message code="clm.page.field.board.regNm" /></th>
						<th><spring:message code="clm.page.field.board.regDt" /></th>
						<th><spring:message code="clm.page.field.board.rdcnt" /></th>
					</tr>
				</thead>
			 	<tbody>
				<%	if(pageUtil.getTotalRow() > 0){
						int idx = (pageUtil.getThisPage() -1)*pageUtil.getRowPerPage()+1;
						    for(idx=0;idx < resultList.size();idx++){
						    	ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
				%>
			        <tr <%if(idx == resultList.size()-1){%>class="end"<%}%> onMouseOut="this.className='<%if(idx == resultList.size()-1){%>end<%}%>';" onMouseOver="this.className='on <%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onClick="javascript:detailView(<%=lom.get("seqno")%>)"> 
			          <td><%=lom.get("rn")%></td>
			          <td class="tL txtover" title="<%=(String)lom.get("title")%>"><nobr><%=(String)lom.get("title")%></nobr>
			          <%if("NEW".equals((String)lom.get("new_yn"))){%>
			      			<img src="<%=IMAGE%>/icon/ico_new_m.gif" alt="new"/>
					  <%}%>			          
			          </td>
			          <td><%=lom.get("reg_nm")%></td>
			          <td><%=DateUtil.formatDate((String)lom.get("regdt"), "-")%></td>
			          <td><%=lom.get("rdcnt")%></td>
			        </tr>
				<%
						    }
					}else{		  
				%>
					<tr class="end">
					  <td colspan="5"><spring:message code="secfw.msg.succ.noResult" /></td>
					</tr>
				<%}%>			
			  </tbody>
			</table>
			<!-- //list -->
	
			<div class="t_titBtn">	
				<!-- total data -->
				<div class="total_num">
					<spring:message code="secfw.page.field.common.totalData" />: <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%><spring:message code="clm.page.msg.common.case" />
				</div>
				<!-- //total data -->
				
				<!-- pagination  -->
				<div class="pagination">
				<%=pageUtil.getPageNavi(pageUtil, "") %>
				</div>
				<!-- //pagination -->
			</div>
		</div>
		<!-- //content -->
		
		<!-- footer -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- //footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
</form:form>
</body>
</html>

