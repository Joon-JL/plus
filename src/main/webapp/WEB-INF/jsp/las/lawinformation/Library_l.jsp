<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.las.lawinformation.dto.LibraryForm" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%@ include file= "/WEB-INF/jsp/common/common.jsp" %>
<%-- 
/**
 * 파  일  명 : ContractLibrary_l.jsp
 * 프로그램명 : 국내/해외 계약서 현황
 * 설      명 : 
 * 작  성  자 : 신승완
 * 작  성  일 : 2011.08
 */
--%>
<%
	LibraryForm command = (LibraryForm)request.getAttribute("command");
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	List resultList = (List)request.getAttribute("resultList");
%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.main.title" /></title>
<LINK href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/excanvas.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script language="javascript">

	$(document).ready(function(){
		//alert("TEST");
		//분류 세팅
		getCodeSelectByUpCd2("frm", "srch_lib_gbn", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_grp_cd="+"<c:out value='${command.info_gbn}'/>"+"&combo_abbr=F&combo_type=T&combo_use_yn=Y&combo_selected="+"<c:out value='${command.srch_lib_gbn}'/>");

		// 달력 세팅
	    initCal("srch_start_dt");   
	    initCal("srch_end_dt");  
	    
		$('#srch_name').focus();

	});

	/**
	* 페이징 function
	*/
	function goPage(pgNum){
		var frm = document.frm;

		viewHiddenProgress(true) ;
		
		frm.target = "_self";
		frm.action = "<c:url value='/las/lawinformation/library.do' />";
		frm.method.value = "listLibrary";
		frm.curPage.value = pgNum;
		
		frm.submit();
	}
  
	/**
	* 버튼 동작 부분
	*/ 
	function pageAction(flag){
		var frm = document.frm;

		if(flag == "list") {
			frm.target = "_self";
			frm.action = "<c:url value='/las/lawinformation/library.do' />";
		    frm.method.value = "listLibrary";
		    frm.curPage.value = "1";
		    frm.srch_name.value = "";

			viewHiddenProgress(true) ;
		    frm.submit();

		}else if(flag == "search"){
			var startDt = frm.srch_start_dt.value;
			var endDt = frm.srch_end_dt.value;

			if(startDt>endDt){
				alert("<spring:message code='clm.msg.alert.board.srchAnnceDt'/>");
				frm.srch_start_dt.focus();
				return;
			}
			frm.target = "_self";
			frm.action = "<c:url value='/las/lawinformation/library.do' />";
		    frm.method.value = "listLibrary";		    
			frm.curPage.value = "1";

			viewHiddenProgress(true) ;
			frm.submit();
		}else if(flag == "new"){	
			frm.target = "_self";
			frm.action = "<c:url value='/las/lawinformation/library.do' />";
		    frm.method.value = "forwardInsertLibrary";

			viewHiddenProgress(true) ;
			frm.submit();
		}
	}
	
	/**
	* 상세내역 조회
	*/
	function detailView(info_gbn, seqno){
		var frm = document.frm;

	    frm.info_gbn.value	= info_gbn;
	    frm.seqno.value		= seqno;

		frm.target = "_self";
		frm.action = "<c:url value='/las/lawinformation/library.do' />";
		frm.method.value = "detailLibrary";

		viewHiddenProgress(true) ;
		frm.submit();		
	}	
	
	/**
	* Tab 이동
	*/
 
	function moveTab(){
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "/las/lawinformation/guidenedu.do";
		frm.method.value = "listGuideline";
				
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
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>" />
	
<!-- key Form -->
<input type="hidden" name="info_gbn" id="info_gbn" value="<c:out value='${command.info_gbn}'/>" />
<input type="hidden" name="seqno"    id="seqno"    value="<c:out value='${command.seqno}'/>" />
<!-- //
**************************** Form Setting **************************** 
-->
<div id="wrap">
	<!-- container -->
	<div id="container">
		<!-- Location -->
		<div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0" alt="Home" /><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->
		<!-- content -->
		<div id="content">
			<!-- Title -->
			<div class="title">
				<c:choose>
					<c:when test="${command.info_gbn=='C00403'}">
					<h3><spring:message code="las.page.title.contract.local.Library" /></h3>
					</c:when>
					<c:otherwise>
					<h3><spring:message code="las.page.title.contract.foreign.Library" /></h3>
					</c:otherwise>
				</c:choose>
			</div>
			<!-- //title -->

			<c:if test="${command.info_gbn=='C00404'}">
			<!-- tab01 -->
			<ul class="tab_basic04">
				<li><a href="javascript:moveTab();"><spring:message code="las.page.field.lawinformation.tab.guideline"/></a></li>
				<li class="on"><a href="#"><spring:message code="las.page.field.lawinformation.tab.samplecont"/></a></li>
			</ul>
			<!-- 두줄라인 추가 --><div class="tal_topLine"></div><!-- //두줄라인 추가 -->
			</c:if>
			<!-- Search -->
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
										<col width="50%"/>
										<col width="10%"/>
										<col width="20%"/>
										<col width="5%"/>
										<col width="5%"/>
									</colgroup>
									<tr>
										<th><spring:message code="las.page.field.contract.library.lib_gbn" /></th>
									    <td>
											<c:choose>
												<c:when test="${command.info_gbn=='C00403'}">
													<select name="srch_lib_gbn" id="srch_lib_gbn" style="width:120px">
													</select>
												</c:when>
												<c:otherwise>
													<select name="srch_lib_gbn" id="srch_lib_gbn" style="width:180px">
													</select>
												</c:otherwise>
											</c:choose>
											<select name="srch_combo"  class="select" id="select2" >
												<option value="title" <c:if test="${command.srch_combo=='title'}">selected</c:if>><spring:message code='las.page.field.contract.library.title' /></option>
												<option value="cont" <c:if test="${command.srch_combo=='cont'}">selected</c:if>><spring:message code='las.page.field.contract.library.cont' /></option>
											</select>
											<input type="text" name="srch_name" id="srch_name" class="text" style="width:250px" value="<c:out value='${command.srch_name}'/>" OnKeyDown="if(event.keyCode==13) { pageAction('search'); }"/>
										</td>
							            <th><spring:message code="secfw.page.field.log.period" /></th>
										<td>
											<!-- calendar -->
											<input type="text" name="srch_start_dt" id="srch_start_dt" value="<c:out value='${command.srch_start_dt}'/>" class="text_calendar02" readonly="readonly" /> ~							
											<input type="text" name="srch_end_dt" id="srch_end_dt" value="<c:out value='${command.srch_end_dt}'/>" class="text_calendar02" readonly="readonly" />
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</table>
							</td>
							<td class="vb tC">
								<a href="javascript:pageAction('search');"><img src="<%=IMAGE %>/btn/btn_search.gif" alt="<spring:message code='las.page.button.search' />" /></a>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- //Search -->
			<div class="t_titBtn">
				<div class="btn_wrap_t">
					<%if(command.isAuth_insert()){ %> 	
					<span class="btn_all_b"><span class="save"></span><a href="javascript:pageAction('new');"><spring:message code='las.page.button.new' /></a></span>
					<%} %>
				</div>
			</div>
			<!-- List -->
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
				<colgroup>
					<col width="7%" />
					<col width="57%" />
					<col width="12%" />
					<col width="12%" />
					<col width="12%" />
				</colgroup>
				<thead>
					<tr>
						<th><spring:message code="las.page.field.board.notice.no" /></th>
						<c:choose>
							<c:when test="${command.info_gbn=='C00403'}">																							
								<th><spring:message code="las.page.field.contract.library.title" /></th>
							</c:when>
							<c:otherwise>
								<th><spring:message code="las.page.field.contract.library.usage" /></th>
							</c:otherwise>				
						</c:choose>
						<th><spring:message code="las.page.field.contract.library.reg_nm" /></th>
						<th><spring:message code="las.page.field.contract.library.reg_dt" /></th>
						<th><spring:message code="las.page.field.contract.library.rdcnt" /></th>
					</tr>
				</thead>
				<tbody>
			  <%
				if(pageUtil.getTotalRow() > 0) {
					for(int idx=0; idx<resultList.size(); idx++) {
						ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
			  %>			
					<tr  <%if(idx == resultList.size()-1){%>class="end"<%}%> onMouseOut="this.className='<%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onMouseOver="this.className='on <%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'">
						<td><%=lom.get("rn") %></td>
						<td class="tL"><div style='width:280px;overflow:hidden;text-overflow:ellipsis'>
					    	<nobr><a href='javascript:detailView("<%=lom.get("info_gbn") %>","<%=lom.get("seqno") %>");'>
		         		<c:choose>
		         			<c:when test="${command.info_gbn=='C00403'}">
			          			<%=lom.get("title") %>
							</c:when>
							<c:otherwise>
								<%=lom.get("usage") %>
							</c:otherwise>
						</c:choose>		          		
			          		</a></nobr>
			            	</div>
					    </td>
					    <td  class="tC"><%=lom.get("reg_nm") %></td> 
					    <td class="tC"><%=lom.get("reg_dt") %></td>   
						<td class="tC"><%=lom.get("rdcnt") %></td>
					</tr>
		      <%	}
				}else{		  
 			  %>
					<tr class="end">
						<td colspan="5"><spring:message code="las.msg.succ.noResult" /></td>
					</tr>
			  <%}
			  %>
				</tbody>
		    </table>
			<!-- //List -->
			<div class="t_titBtn">
				<!-- Total -->
				<div class="total_num">Total : <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%><spring:message code="las.page.field.lawinformation.case"/></div>
				<!-- pagination  -->
				<div class="pagination"><%=pageUtil.getPageNavi(pageUtil, "") %></div>
				<!-- //pagination -->
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