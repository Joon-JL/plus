<!DOCTYPE html>
<%@page import="java.math.BigDecimal"%>
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
 * 프로그램명 : Q&A / 자유게시판 / 자료실 / 법무시스템매뉴얼 
 * 설      명 : 	단순 CRUD 특성만 갖는 게시판성 메뉴
 *				Q&A 			- C01201 
 *				자유게시판 		- C01214 
 *				자료실			- C01215
 *				법무시스템매뉴얼 	- C01216
 *				VOC				- C01217
 * 작  성  자 : 황민우(수정) 
 * 작  성  일 : 2013.05
 */
--%>
<%
	BbsForm command = (BbsForm)request.getAttribute("command");
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	List resultList = (List)request.getAttribute("resultList");
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
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
		
		<c:choose>
			<c:when test="${command.bbs_knd=='C01201' || command.bbs_knd=='C01217'}">
				frm.method.value = "listSysQnA";
			</c:when>
			<c:otherwise>
				frm.method.value = "listOpenBbs";
			</c:otherwise>
		</c:choose>
		
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
			<c:choose>
				<c:when test="${command.bbs_knd=='C01201'|| command.bbs_knd=='C01217'}">
					frm.method.value = "listSysQnA";
				</c:when>
				<c:otherwise>
					frm.method.value = "listOpenBbs";
				</c:otherwise>
			</c:choose>
			frm.curPage.value = "1";

			viewHiddenProgress(true) ;
		
			frm.submit();
			
		}else if(flag == "new"){
			frm.grp_seqno.value = "0";
			frm.target = "_self";
			frm.action = "<c:url value='/las/board/bbs.do' />";
			<c:choose>
				<c:when test="${command.bbs_knd=='C01201'|| command.bbs_knd=='C01217'}">
					frm.method.value = "forwardInsertSysQnA";
				</c:when>
				<c:otherwise>
					frm.method.value = "forwardInsertOpenBbs";
				</c:otherwise>
			</c:choose>

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
		<c:choose>
			<c:when test="${command.bbs_knd=='C01201'|| command.bbs_knd=='C01217'}">
				frm.method.value = "detailSysQnA";
			</c:when>
			<c:otherwise>
				frm.method.value = "detailOpenBbs";
			</c:otherwise>
		</c:choose>
		
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
			<c:choose>
				<c:when test="${command.bbs_knd=='C01201'}">
				<h3><spring:message code="las.page.title.board.SysQnA" /></h3>
				</c:when>
				<c:when test="${command.bbs_knd=='C01214'}">
				<h3><spring:message code="las.page.title.board.freeBoard" /></h3>
				</c:when>
				<c:when test="${command.bbs_knd=='C01215'}">
				<h3><spring:message code="las.page.title.board.pds_5" /></h3>
				</c:when>
				<c:when test="${command.bbs_knd=='C01216'}">
				<h3><spring:message code="las.page.field.board.lasMenual" /></h3>
				</c:when>
				<c:when test="${command.bbs_knd=='C01217'}">
				<h3><spring:message code="las.page.title.board.voc" /></h3>
				</c:when>
			</c:choose>
		</div>
		<!-- title -->
	
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
			<input type="hidden" name="bbs_knd" value="<c:out value='${command.bbs_knd}'/>" />
			<input type="hidden" name="insert_kbn" value="0" />
			<input type="hidden" name="grp_no" value="<c:out value='${command.grp_no}' />" />
			<input type="hidden" name="grp_seqno" value="<c:out value='${command.grp_seqno}' />" />
			<input type="hidden" name="postup_srt" value="<c:out value='${command.postup_srt}' />" />
			<input type="hidden" name="postup_depth" value="<c:out value='${command.postup_depth}' />" />
			<input type="hidden" name="total_seqno" value="<c:out value='${command.total_seqno}' />" />
			
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
										<col width="10%"/>
										<col width="50%"/>
										<col width="10%"/>
										<col width="30%"/>
									</colgroup>
									<tr>
										<th><spring:message code="las.page.field.bbs.condition"/></th>
										<td>
											<select name="srch_type"  class="select" id="select2" >
												<option value="title" <c:if test="${command.srch_type=='title'}">selected</c:if>><spring:message code="las.page.field.board.notice.title"/></option>
												<option value="reg_nm" <c:if test="${command.srch_type=='reg_nm'}">selected</c:if>><spring:message code="las.page.field.board.notice.reg_nm"/></option>
												<option value="cont" <c:if test="${command.srch_type=='cont'}">selected</c:if>><spring:message code="clm.page.msg.common.content"/></option>							
											</select>
											<input type="text" name="srch_word" class="text w_50" value="<c:out value='${command.srch_word}'/>" maxLength="100" OnKeyDown="if(event.keyCode==13) { pageAction('search'); }"/>
										</td>
									    <th><spring:message code="las.page.field.board.notice.reg_dt"/></th>
										<td><!-- calendar -->
											<input type="text" name="srch_start_dt" id="srch_start_dt" value="<c:out value='${command.srch_start_dt}'/>"  class="text_calendar02" readonly="readonly"/> ~							
											<input type="text" name="srch_end_dt" id="srch_end_dt" value="<c:out value='${command.srch_end_dt}'/>" class="text_calendar02" readonly="readonly" />
										</td>
										
									</tr>
								</table>
							</td>
							<td class="tC">
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
					<span class="btn_all_w"><span class="edit"></span><a href="javascript:pageAction('new');"><spring:message code="las.page.button.new"/></a></span>
					<%} %>
				</div>
			</div>
			<!-- //button -->
			<!-- List -->
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
				<colgroup>					
					<col width="5%" />
					<col width="45%" />
					<col width="18%" />
					<col width="22%" />
					<col width="10%" />
<%-- 					<col width="10%" /> --%>
				</colgroup>
			  	<thead>
					<tr>
					  <th><spring:message code="las.page.field.board.notice.no" /></th>	<!-- 번호 -->
					  <th><spring:message code="las.page.field.board.notice.title" /></th>	<!-- 제목 -->
					  <th><spring:message code="las.page.field.board.notice.reg_nm" /></th>	<!-- 작성자 -->
					  <th><spring:message code="las.page.field.dept.dept" /></th>			<!-- 부서 -->
					  <th><spring:message code="las.page.field.board.notice.reg_dt" /></th>	<!-- 작성일 -->
<%-- 					  <th><spring:message code="las.page.field.contract.library.rdcnt" /></th>	<!-- 조회수 --> --%>
					</tr>
			  	</thead>
			  	<tbody>
			  <%
				if(pageUtil.getTotalRow() > 0) {
					for(int idx=0; idx<resultList.size(); idx++) {
						ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
						pageContext.setAttribute("lom", lom);
			  %>			
					<tr  <%if(idx == resultList.size()-1){%>class="end"<%}%> onMouseOut="this.className='<%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onMouseOver="this.className='on <%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'">
				    	<td class="tC"><%=lom.get("rn") %></td>
						<td class="tL overflow" title='<%=lom.get("title")%>'>
							<%
							//14일 이내의 글일 경우 new 이모티콘 표시						
							if ("Y".equals(lom.get("new_yn"))) { 
%>
<%-- 					         		<img src="<%=IMAGE%>/icon/ico_new_m.gif" alt="new"/> --%>
					         		<span class="iconBox icon020"></span>
<%
					         	}
%>
					    	<a href='javascript:detailView("<%=lom.get("grp_no") %>","<%=lom.get("grp_seqno") %>","<%=lom.get("postup_srt") %>","<%=lom.get("postup_depth") %>","<%=lom.get("total_seqno")%>");'>
							<%if ((Integer.parseInt(lom.get("postup_depth").toString()))>0){ %>
								<c:forEach var="x" begin="1" end="${list.postup_depth}" step="1" >&nbsp;</c:forEach>
					          	<img src="<%=IMAGE%>/icon/icon_reply.gif" />
							<%} %>
					        <%=lom.get("title")%> 
						

					        </a>
					        </nobr></div>
					    </td>
					    <td  class="tL overflow" title='<%=lom.get("reg_nm")%>'><%=lom.get("reg_nm") %></td> 
					    <td  class="tL overflow" title='<%=lom.get("reg_dept_nm")%>'><%=lom.get("reg_dept_nm") %></td> 
					    <td class="tC overflow" title='<%=lom.get("reg_dt") %>'><%=lom.get("reg_dt") %></td>   
<%-- 						<td class="tR"><fmt:formatNumber value="${lom.rdcnt}" type="number"/></td> --%>
					</tr>
		      <%	}
				}else{		  
 			  %>
					<tr>
						<td colspan="5" align="center"><spring:message code="las.msg.succ.noResult" /></td>
					</tr>
			  <%}
			  %>
				 </tbody>
			</table>
			<!-- //List -->
			<div class="t_titBtn">
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
