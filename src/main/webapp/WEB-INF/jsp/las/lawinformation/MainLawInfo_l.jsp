<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.las.lawinformation.dto.MainLawInfoForm" %>
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
 * 파     일     명 : MaiLawInfo.jsp
 * 프로그램명 : 주요입법동향/주요법무사례 목록
 * 설               명 : 
 * 작     성     자 : 
 * 작     성     일 : 2011.08   
 */
--%>
<%
	MainLawInfoForm command = (MainLawInfoForm)request.getAttribute("command");
    String menuNavi = (String)request.getAttribute("secfw.menuNavi");
    PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil"); 
	List resultList = (List)request.getAttribute("resultList");
%>
	
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta charset="utf-8" />

<title><spring:message code="las.main.title" /></title>
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet" />
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
		var frm = document.frm;
		
		//분류 세팅
	    getCodeSelectByUpCd("frm", "srch_nation", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=L2&combo_type=S&combo_del_yn=N&combo_selected='+'<c:out value='${command.srch_nation}'/>');
	    getCodeSelectByUpCd("frm", "srch_mainlawexam", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=C00406&combo_type=S&combo_del_yn=N&combo_selected='+'<c:out value='${command.srch_mainlawexam}'/>');
	    getCodeSelectByUpCd("frm", "srch_dmstfrgn_gbn", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=C002&combo_type=S&combo_del_yn=N&combo_selected='+'<c:out value='${command.srch_dmstfrgn_gbn}'/>');

	    <c:if test="${command.srch_dmstfrgn_gbn=='F'}">
	    	frm.srch_nation.style.visibility = "visible";
	    </c:if>
	    
		// 달력 세팅
	    initCal("srch_start_dt");   
	    initCal("srch_end_dt","20110804083152197_0000000154");  
	});
	
	/**
	* 페이징 function
	*/  
	function goPage(pgNum){
		var frm = document.frm;

		viewHiddenProgress(true) ;
		frm.target = "_self";
		frm.action = "<c:url value='/las/lawinformation/mainLawInfo.do' />";
		frm.method.value = "listMainLawInfo";
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
			frm.action = "<c:url value='/las/lawinformation/mainLawInfo.do' />";
		    frm.method.value = "listMainLawInfo";		    
			frm.curPage.value = "1";
			frm.seqno.value=0;

			viewHiddenProgress(true) ;
			frm.submit();
			
		}else if(flag == "new"){	
			frm.target = "_self";
			frm.action = "<c:url value='/las/lawinformation/mainLawInfo.do' />";
		    frm.method.value = "forwardInsertMainLawInfo";
		    frm.seqno.value=0;

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
		frm.action = "<c:url value='/las/lawinformation/mainLawInfo.do' />";
		frm.method.value = "detailMainLawInfo";

		viewHiddenProgress(true) ;
		frm.submit();		
	}

	function selectOptions(select){
		var frm = document.frm;
		
		var gbnValue;
		gbnValue = select.options[select.selectedIndex].value;
		
		if (gbnValue=="F"){
			frm.srch_nation.style.visibility = "visible";
		} else {
			frm.srch_nation.style.visibility = "hidden";
		}
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

<div id="wrap">	
	<div id="container">
		<!-- location -->
		<div class="location"><img src="<%=IMAGE %>/icon/ico_home.gif" width="13" height="11" border="0" alt="Home" /><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //location -->
	
		<!-- title -->
	    <div class="title">
			<c:choose>
				<c:when test="${command.info_gbn=='C00405'}">
			   		<h3><spring:message code="las.page.title.lawinformation.mayorLegTrends" /></h3>
				</c:when>
				<c:when test="${command.info_gbn=='C00406'}">
					<h3><spring:message code="las.page.title.lawinformation.mayorLagCases" /></h3>
				</c:when>
			</c:choose>
		</div>
		<!-- //title -->
		
		<!-- content -->
		<div id="content">
		<div id="content_in">
		<!--**************************** Form Setting ****************************-->
			<form:form name="frm" id='frm' method="post" autocomplete="off"> 
			<!-- search form -->
			<input type="hidden" name="method"   value="" />
			<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>" />
			<!-- key Form -->
			<input type="hidden" name="info_gbn" id="info_gbn" value="<c:out value='${command.info_gbn}'/>" />
			<input type="hidden" name="seqno" id="seqno" value="<c:out value='${command.seqno}'/>" />
			<!--**************************** Form Setting ****************************-->
			
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
										<col width="25%"/>
										<col width="10%"/>
										<col width="45%"/>
										<col width="5%"/>
										<col width="5%"/>
									</colgroup>
									<tr>
										<th><spring:message code="las.page.field.board.notice.reg_dt"/></th>
										<td>
											<!-- calendar -->
											<input type="text" name="srch_start_dt" id="srch_start_dt" value="<c:out value='${command.srch_start_dt}'/>" class="text_calendar02" readonly="readonly" /> ~							
											<input type="text" name="srch_end_dt" id="srch_end_dt" value="<c:out value='${command.srch_end_dt}'/>" class="text_calendar02" readonly="readonly" />
											<!-- //calendar -->
										</td>
										<c:choose>
											<c:when test="${command.info_gbn=='C00405'}">
												<th><spring:message code="las.page.field.lawinformation.country" /></th>
											   	<td>
											   		<select name="srch_nation" id="srch_nation"></select>   
												</td>  
												</c:when>
											<c:when test="${command.info_gbn=='C00406'}">
												<th><spring:message code="las.page.field.lawinformation.law_gbn" /></th>
												<td>
													<select name="srch_mainlawexam" style="width:180px" id="srch_mainlawexam"></select>
													<select name="srch_dmstfrgn_gbn" style="width:80px" id="srch_dmstfrgn_gbn" onChange="javascript:selectOptions(this)"></select>
													<select name="srch_nation" id="srch_nation" style="visibility:hidden"></select>
												</td>
											</c:when>
										</c:choose>
<!-- 										<td>&nbsp;</td> -->
<!-- 										<td>&nbsp;</td> -->
									</tr>
									<tr>
							            <th><spring:message code="las.page.field.bbs.condition"/></th>
									    <td colspan="3">
										    <select name="srch_combo" id="srch_combo">
												<option value="title" <c:if test="${command.srch_combo=='title'}">selected</c:if>><spring:message code="las.page.field.board.notice.title" /></option>
												<option value="reg_nm" <c:if test="${command.srch_combo=='reg_nm'}">selected</c:if>><spring:message code="las.page.field.board.notice.reg_nm" /></option>
												<option value="cont" <c:if test="${command.srch_combo=='cont'}">selected</c:if>><spring:message code="las.page.field.contract.library.cont" /></option>
											</select>  
										 	<input type="text" name="srch_name" id="srch_name" class="text_full w_80"  value="<c:out value='${command.srch_name}'/>" maxLength="64" OnKeyDown="if(event.keyCode==13) { pageAction('search');}" />
										</td>  
<!-- 										<td>&nbsp;</td> -->
<!-- 										<td>&nbsp;</td> -->
									</tr>
								</table>
							</td>
							<td class="vb tC">
								<a href="javascript:pageAction('search');"><img src='<%=IMAGE%>/btn/btn_search.gif' alt="<spring:message code='las.page.button.search' />" /></a>
							</td>
						</tr>
					</table>
				</div>   
			</div>
			<!-- //Search -->	
			<!-- Button -->
			<div class="t_titBtn">
				<div class="btn_wrap_t">
					<%if(command.isAuth_insert()){ %> 
					<span class="btn_all_w"><span class="edit"></span><a href="javascript:pageAction('new');"><spring:message code='las.page.button.new' /></a></span>
					<%} %>
				</div>
			</div>
			<!-- //Button -->
			<!-- List -->
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
		        <colgroup>
					<col width="7%" />
					<col width="57%" />
					<col width="12%" />
					<col width="12%" />
<%-- 					<col width="12%" /> --%>
		        </colgroup>
		   		<thead>
			        <tr>
			        	<!-- 번호 --><th><spring:message code="las.page.field.board.notice.no" /></th>
			            <!-- 제목 --><th><spring:message code="las.page.field.board.notice.title" /></th>
			            <!-- 작성자 --><th><spring:message code="las.page.field.board.notice.reg_nm" /></th>
			            <!-- 작성일 --><th><spring:message code="las.page.field.board.notice.reg_dt" /></th>
<%-- 			            <!-- 조회수 --><th><spring:message code="las.page.field.contract.library.rdcnt" /></th> --%>
			        </tr>
		   		</thead>
		   		<tbody>
			  <%
				if(pageUtil.getTotalRow() > 0) {
					for(int idx=0; idx<resultList.size(); idx++) {
						ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
						pageContext.setAttribute("tmpLom", lom);
			  %>			
					<tr  <%if(idx == resultList.size()-1){%>class="end"<%}%> onMouseOut="this.className='<%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onMouseOver="this.className='on <%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'">
				    	<td class="tC"><%=lom.get("rn") %></td>
						<td class="tL"><div style='width:280px;overflow:hidden;text-overflow:ellipsis'>
					       	<nobr><a href='javascript:detailView("<%=lom.get("info_gbn")%>","<%=lom.get("seqno")%>");'>
					    	<%=lom.get("title")%></nobr>
					       	</div>
					    </td>
					    <td class="tL overflow" title='<%=lom.get("reg_nm") %>'><%=lom.get("reg_nm") %></td> 
					    <td class="tC"><%=lom.get("reg_dt") %></td>   
<%-- 						<td class="tR"><fmt:formatNumber value="${tmpLom.rdcnt}" type="number"/></td> --%>
					</tr>
		      <%	}
				}else{		  
 			  %>
					<tr class="end">
						<td colspan="4" align="center" class="tC"><spring:message code="las.msg.succ.noResult" /></td>
					</tr>
			  <%}
			  %>
		    	</tbody>
		  	</table>
			<!-- //List -->
			<div class="t_titBtn">
				<!-- Total -->
				<div class="total_num">Total : <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%></div>
				<!-- //Total -->
			    <!-- pagination  -->
			   	<div class="pagination"><%=pageUtil.getPageNavi(pageUtil, "") %></div>
				<!-- //pagination -->
			</div>
			</form:form>
		</div>
		</div>
		<!-- //content -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>

</div>
<!-- footer  -->
<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
<!-- // footer -->

</body>
</html>