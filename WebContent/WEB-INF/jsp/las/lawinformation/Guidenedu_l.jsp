<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.las.lawinformation.dto.GuideneduForm" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>


<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%--
/**
 * 파  일  명 : Guidenedu_l.jsp
 * 프로그램명 : 임직원법무교육자료 목록
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 2011.09
 */
--%>
<%
	GuideneduForm command = (GuideneduForm)request.getAttribute("command");
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	List resultList = (List)request.getAttribute("resultList");
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta charset="utf-8" />

<title><spring:message code="las.main.title" /></title>
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
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
	    initCal("srch_end_dt","20110804083152685_0000000158");  
	}); 

	/**
	* 페이징 function
	*/
	function goPage(pgNum){
		
		var frm = document.frm;

		viewHiddenProgress(true) ;
		
		frm.target = "_self";
		frm.action = "<c:url value='/las/lawinformation/guidenedu.do' />";
		frm.method.value = "listGuidenedu";
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
				alert("<spring:message code='las.page.field.lawinformation.setFromDateEarly'/>");
				frm.srch_start_dt.focus();
				return;
			}
			frm.target = "_self";
			frm.action = "<c:url value='/las/lawinformation/guidenedu.do' />";
		    frm.method.value = "listGuidenedu";
			frm.curPage.value = "1";

			viewHiddenProgress(true) ;
		
			frm.submit();
			
		}else if(flag == "new"){
			frm.target = "_self";
			frm.action = "<c:url value='/las/lawinformation/guidenedu.do' />";
		    frm.method.value = "forwardInsertGuidenedu";

			viewHiddenProgress(true) ;

			frm.submit();
		}
	}
	
	/**
	* 상세내역 조회
	*/
	function detailView(seqno){
		
		var frm = document.frm;

		viewHiddenProgress(true) ;
	    
	    frm.seqno.value = seqno;
		
		frm.target = "_self";
		frm.action = "<c:url value='/las/lawinformation/guidenedu.do' />";
		frm.method.value = "detailGuidenedu";
		
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

<div id="wrap">	
	<div id="container">	
		<!-- Location -->
		<div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0" alt="Home" /><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->

		<!-- title -->
		<div class="title">
			<h3><spring:message code="las.page.title.lawinformation.empLegEdu" /></h3>
		</div>
		<!-- //title -->
					
		<!-- content -->
		<div id="content">
		<div id="content_in">
			<!--**************************** Form Setting ****************************-->
			<form:form name="frm" id='frm' method="post" autocomplete="off"> 
			<!-- search form -->
			<input type="hidden" name="method"  value="" />
			<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>" />	
			<!-- key Form -->
			<input type="hidden" name="info_gbn" value="<c:out value='${command.info_gbn}'/>" />
			<input type="hidden" name="seqno" value="<c:out value='${command.seqno}' />" />
			
			<!-- //**************************** Form Setting ****************************-->
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
										<col width="15%"/>
										<col width="30%"/>
										<col width="15%"/>
										<col width="20%"/>
										<col width="10%"/>
										<col width="10%"/>
									</colgroup>
									<tr>
										<th><spring:message code="las.page.field.bbs.condition"/></th>
										<td>
											<select name="srch_type"  class="select" id="select2" >
												<option value="title" <c:if test="${command.srch_type=='title'}">selected</c:if>><spring:message code="las.page.field.board.notice.title"/></option>
												<option value="reg_nm" <c:if test="${command.srch_type=='reg_nm'}">selected</c:if>><spring:message code="las.page.field.board.notice.reg_nm"/></option>
												<option value="cont" <c:if test="${command.srch_type=='cont'}">selected</c:if>><spring:message code="clm.page.msg.common.content"/></option>							
											</select>
									       	<input type="text" name="srch_word" class="text" style="width:150px" value="<c:out value='${command.srch_word}'/>" maxLength="100" OnKeyDown="if(event.keyCode==13) { pageAction('search'); }"/>&nbsp;&nbsp;&nbsp;&nbsp;
									    </td>
							            <th><spring:message code="las.page.field.board.notice.reg_dt"/></th>
										<td><!-- calendar -->
											<input type="text" name="srch_start_dt" id="srch_start_dt" value="<c:out value='${command.srch_start_dt}'/>" class="text_calendar02" readonly="readonly" /> ~							
											<input type="text" name="srch_end_dt" id="srch_end_dt" value="<c:out value='${command.srch_end_dt}'/>" class="text_calendar02" readonly="readonly" />
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</table>
							</td>
							<td class="vb tC">
								<a href="javascript:pageAction('search')"><img src="<%=IMAGE %>/btn/btn_search.gif" alt="<spring:message code='las.page.button.search'/>"/></a>
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
					<span class="btn_all_w"><span class="edit"></span><a href="javascript:pageAction('new');"><spring:message code="las.page.button.new"/></a></span>
					<%} %>
				</div>
			</div>
			<!-- //button -->
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
		            <th><spring:message code="las.page.field.board.notice.no" /></th>		<!-- 번호 -->
		            <th><spring:message code="las.page.field.board.notice.title" /></th>	<!-- 제목 -->
		            <th><spring:message code="las.page.field.board.notice.reg_nm" /></th>	<!-- 작성자 -->
		            <th><spring:message code="las.page.field.board.notice.reg_dt" /></th>	<!-- 작성일 -->
<%-- 		            <th><spring:message code="las.page.field.board.notice.rdcnt" /></th>	<!-- 조회수 --> --%>
		          </tr>
		        </thead>
			  	<tbody>
			  <%
				if(pageUtil.getTotalRow() > 0) {
					for(int idx=0; idx<resultList.size(); idx++) {
						ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
						pageContext.setAttribute("tmpLom",lom);
			  %>			
					<tr  <%if(idx == resultList.size()-1){%>class="end"<%}%> onMouseOut="this.className='<%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onMouseOver="this.className='on <%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'">
				    	<td class="tC"><%=lom.get("rn") %></td>
						<td  class="tL"><div style='width:280px;overflow:hidden;text-overflow:ellipsis'>
					       	<nobr><a href='javascript:detailView("<%=lom.get("seqno") %>");'>
					   		<%=lom.get("title") %></a></nobr>
					       	</div>
					    </td>
					    <td class="tL overflow" title='<%=lom.get("reg_nm") %>'><%=lom.get("reg_nm") %></td> 
					    <td class="tC"><%=lom.get("reg_dt") %></td>   
<%-- 						<td class="tR"><fmt:formatNumber value = "${tmpLom.rdcnt}" type="number"/></td> --%>
					</tr>
		      <%	}
				}else{		  
 			  %>
					<tr class="end">
						<td colspan="4" class="tC"><spring:message code="las.msg.succ.noResult" /></td>
					</tr>
			  <%}
			  %>
			  	</tbody>
	        </table>
			<div class="t_titBtn">
				<!-- //List -->
				<div class="total_num">Total : <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%></div>
				<!-- Paging  -->
				<div class="pagination"> 
				<%=pageUtil.getPageNavi(pageUtil, "") %>
				</div>
				<!-- // Paging -->
			</div>
			</form:form>
		</div>
		</div>		
		<!-- //content -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
<!-- footer  -->
<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
<!-- // footer -->
</body>
</html>