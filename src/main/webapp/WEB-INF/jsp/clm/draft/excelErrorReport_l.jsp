<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.draft.dto.ExcelForm" %>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sec.common.util.ClmsBoardUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%-- 
/**
 * 파  일  명 : excelErrorReport_l.jsp
 * 프로그램명 : excel Error 목록
 * 설      명 : 
 * 작  성  자 : 홍성현
 * 작  성  일 : 2014.01
 */
--%>
<%
// 	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	List resultList = (List)request.getAttribute("resultList");
	ExcelForm command = (ExcelForm)request.getAttribute("command");
%>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />

<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet" />
<link href="<%=CSS%>/popup.css"  type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/menu.js'/>"></script>

<script language="javascript">

	
	
	$(function() {
		initCal("srch_start_dt");   
	    initCal("srch_end_dt","20110804083442204_0000000163"); 
	});
	
	/**
	* 메시지 처리
	*/
	function showMessage(msg) {
		if( msg != "" && msg != null && msg.length > 1 ) {
			alert(msg);
		}
	}
	
		
	/**
	* 조회(검색)버튼 function
	*/ 
	function searchAction(){
		var frm = document.frm;
		
		var startDt = frm.srch_start_dt.value;
		var endDt = frm.srch_end_dt.value;
		
		if(startDt>endDt){
			alert("<spring:message code='clm.msg.alert.board.srchAnnceDt'/>");
			frm.srch_start_dt.focus();
			return;
		}
		
		viewHiddenProgress(true) ;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/draft/excelUpload.do' />";
	    frm.method.value = "listExcelErrorReport";
	    frm.curPage.value = "1";
	    frm.submit();
	}
	
	/**
	* 엑셀 다운로드 function
	*/ 
	function excelDownLoad(){
		var frm = document.frm;
		// 1000row가 넘어가면 제한 메세지
		//DE_The number of data which will be downloaded is over 1000. Please re-select search condition to reduce the amount of data.
		if(<%=pageUtil.getTotalRow()%>>1000){
		    	alert("<spring:message code='clm.page.msg.manage.announce180max1000' />");
			    return;			
		}

				
		viewHiddenProgress(true) ;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/manage/contract.do' />";
	    frm.method.value = "listManageExcel";
	    frm.curPage.value = "1";
	    frm.submit();
	}
	
	
	/**
	* 조회 페이지 이동
	*/	
	function goPage(pgNum){
		var frm = document.frm;
		viewHiddenProgress(true) ;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/draft/excelUpload.do' />";
		frm.method.value = "listExcelErrorReport";
		frm.curPage.value = pgNum;
		
		frm.submit();
	}
	
	/**
	* 상세 페이지 이동
	*/	
	function detailAction(tableNm){
		
		var frm = document.frm;

		frm.tableNm.value = tableNm;
		viewHiddenProgress(true);
		frm.target = "_self";
		frm.action = "<c:url value='/clm/draft/excelUpload.do' />";
		frm.method.value = "listExcelErrorDetail";
		frm.curPage.value = "1";
		frm.submit();
		
	}
	
	// 취소하기
	function can(){
		window.close();
	}
</script>
</head>
<html>
<body class="pop">
	<!-- header -->
	<h1>
		<spring:message code="las.page.field.excelErrorReport"/>
		<span class="close" onclick="javascript:self.close();" title="close"></span>
	</h1>
	<!-- //header -->
	<!-- body -->
	<div class="pop_area">
		<div class="pop_content">

		
<form:form name="frm" id='frm' method="post" autocomplete="off"> 
	<!-- search form -->
	<input type="hidden" name="method"   value="" />
	<input type="hidden" name="menu_id"   value="<c:out value='${command.menu_id}'/>" />
	<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>" />
	<input type="hidden" name="tableNm"  id="tableNm" value="" />

			
<!-- // **************************** Form Setting **************************** -->				
			<!-- search-->		    
			<div class="search_box">
				<div class="search_box_inner">
					<table class="search_tb">
					
						<tr>
							<td>
								<table class="search_form" style='min-width:700px;'>
									<colgroup>
                                        <col width="*%"/>
                                        <col width="*%"/>                                        
                                    </colgroup>
									<tr>
										<th><spring:message code="las.page.field.excelError.tableNm"/></th>
										<td>
											<input class="text" style="width:179px" type="text" name="srch_word3"  id="srch_word3" value="<c:out value='${command.srch_word3}'/>" maxLength="100" OnKeyDown="if(event.keyCode==13) { searchAction(); }"/>
										</td>
										<th><spring:message code="clm.page.field.refcontract.regDt"/></th>
										<td><!-- calendar -->
											<input type="text" name="srch_start_dt" id="srch_start_dt" value="<c:out value='${command.srch_start_dt}'/>"  class="text_calendar02" readonly="readonly"/> ~							
											<input type="text" name="srch_end_dt" id="srch_end_dt" value="<c:out value='${command.srch_end_dt}'/>" class="text_calendar02" readonly="readonly" />
										</td>                                      
										<td class="tR">
											<a href="javascript:searchAction();"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="clm.page.msg.common.search" htmlEscape="true" />" class='mt5' style='margin-right:9%' /></a>
											<!-- 구주 관련은 상세 검색 없음 <p class="mt5"><a href="javascript:popDetailSrh();"><img src="<%//=IMAGE%>/btn/btn_search2.gif" alt="<spring:message code="clm.page.msg.manage.detailSearch" htmlEscape="true" />"/></a></p> -->
										</td>
									</tr>
								</table>
							</td>
							
						</tr>
					</table>
				</div>
			</div>
			<!--//search-->
			<!-- list -->
		   
			<table class="list_basic mt25">
				<colgroup>
					<col width="40%" />					
				    <col width="35%" />
					<col width="25%" />
			  	</colgroup>

			  	<thead>
					<tr>
						<th><spring:message code="las.page.field.excelError.tableNm"/></th>
						<th><spring:message code="clm.page.field.refcontract.regDt"/></th>
						<th><spring:message code="las.page.field.excelError.errorNum"/></th>
					</tr>
				</thead>
			 	<tbody>
			 	<%
			 	if(pageUtil.getTotalRow() > 0) {
			 		for(int idx=0; idx<resultList.size(); idx++) {
			 			ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
				%>		
					<tr>
						<td class="tC">
						<a onclick="javascript:detailAction('<%=lom.get("tab_name") %>');"><%=lom.get("tab_name")%></a>
						</td>
						<td class="tC"><%=lom.get("reg_dt")%></td>
						<td class="tC"><%=lom.get("ex_cnt")%></td>
					</tr>
			 	<%	}
				}else{
				%>
					<tr>
						<td colspan="3" class='tC'><spring:message code="secfw.msg.succ.noResult" /></td>
					</tr>
				<%}%>		
			 	</tbody>
			  	
			</table>
			<!-- //list -->		
			
			<!-- total data -->
			<div class="total_num">
			Total : <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%>
			</div>
			<!-- //total data -->
			
			<!-- pagination  -->
			<div class="pagination mt_22">
			<%=pageUtil.getPageNavi(pageUtil, "") %>
			</div>
			<!-- //pagination -->
			
			</form:form>
			
			
		</div>
		</div>
		<!-- //content -->
		
		
		<!--footer -->
		<div class="pop_footer">
			<!-- button -->
			<div class="btn_wrap tR">
				<span class="btn_all_w"><span class="cancel"></span><a href="javascript:can()"><spring:message code="clm.page.msg.common.close" /></a></span>
			</div>
			<!-- //button -->
		</div>
		<!-- //footer -->
		
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>

</body>
</html>

