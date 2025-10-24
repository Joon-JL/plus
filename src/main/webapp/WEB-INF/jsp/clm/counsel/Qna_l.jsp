<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%-- 
/**
 * 파  일  명 : Qna_l.jsp
 * 프로그램명 : Qna 목록
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	List resultList = (List)request.getAttribute("resultList");
	HttpSession hs = request.getSession(false);
	String user_id = (String)hs.getAttribute("secfw.session.user_id");
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
<!-- 달력관련추가 -->
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>

<script language="javascript">
	$(document).ready(function(){
		getCodeSelectByUpCd("frm", "srch_prgrs_status", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=T&combo_grp_cd=C017&combo_type=T&combo_del_yn=N&combo_selected=');
		
		initCal("srch_start_dt");   //첫번재 날짜창 설정 : 변수는 해당 태그의 id값
        initCal("srch_end_dt","20110803092535871_0000000071");     //두번재 날짜창 설정 : 변수는 해당 태그의 id값
	});
	
	/**
	* 페이징 function
	*/
	function goPage(pgNum){
		var frm = document.frm;
		frm.seqno.value = 0;
		frm.up_seqno.value = 0;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/counsel/qna.do' />";
		frm.method.value = "listQna";
		frm.curPage.value = pgNum;
		viewHiddenProgress(true);
		frm.submit();
	}
	/**
	* 버튼 동작 부분
	*/ 
	function pageAction(flag){
		var frm = document.frm;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/counsel/qna.do'/>";
		
		var startDt  = frm.srch_start_dt.value;//등록일 start
		var endDt    = frm.srch_end_dt.value;//등록일 end
		
		if(flag == "search"){
			if(startDt>endDt){
				viewHiddenProgress(false) ;
				alert("clm.page.msg.common.announce004") ;
				f.srch_start_ymd.focus() ;
				return ;
			}
			frm.seqno.value = 0;
			frm.up_seqno.value = 0;
		    frm.method.value = "listQna";
			frm.curPage.value = "1";
			viewHiddenProgress(true);
			frm.submit();
			
		}else if(flag == "new"){
			frm.seqno.value = 0;
			frm.up_seqno.value = 0;
		    frm.method.value = "forwardInsertQna";
		    viewHiddenProgress(true);
			frm.submit();
		}
	}
	/**
	* 상세내역 조회
	*/
	function detailView(up_seqno, seqno, postup_depth){
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/counsel/qna.do' />";
		
		frm.method.value = "detailQna";
		frm.up_seqno.value = up_seqno;
		frm.seqno.value = seqno;
		frm.postup_depth.value = postup_depth;
		viewHiddenProgress(true);
		frm.submit();
	}
	
	/**
	* 메시지 처리
	*/
	function showMessage(msg){
		if( msg != "" && msg != null) {
			alert(msg);
		}
	}
</script>
</head>

<body onLoad="alertMessage('<c:out value='${returnMessage}'/>')" autocomplete="off">
<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id='frm' method="post" autocomplete="off"> 
<!-- search form -->
<input type="hidden" name="method"       value="" />
<input type="hidden" name="menu_id"      value="<c:out value='${command.menu_id}'/>" />
<!-- Detail Page Param -->
<!-- key Form -->
<input type="hidden" name="seqno"/>
<input type="hidden" name="up_seqno"/>
<input type="hidden" name="postup_srt"   value="<c:out value='${command.postup_srt}'/>" />
<input type="hidden" name="postup_depth" value="<c:out value='${command.postup_depth}'/>" />
<input type="hidden" name="curPage"      value="<c:out value='${command.curPage}'/>" />
<input type="hidden" name="roleCd"       value="<c:out value='${roleCd.role_cd}'/>" />
<!-- //
**************************** Form Setting **************************** 
-->
<div id="wrap">
	<!-- container -->
	<div id="container">
	    <!-- Location -->
		<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->
		<!-- Title -->
		<div class="title">
		<h3><spring:message code="clm.page.title.qna.listTitle" /></h3>
		<!-- //title -->
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
										<col width="40%"/>
										<col width="10%"/>
										<col width="40%"/>
									</colgroup>
									<tr>
										<th><spring:message code="clm.page.field.qna.srchRegDt" /></th>
										<td>
											<input type="text" name="srch_start_dt" id="srch_start_dt" value="<c:out value='${command.srch_start_dt}'/>"  class="text_calendar02" onKeypress="if(event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;" />
										~
										<input type="text" name="srch_end_dt" id="srch_end_dt" value="<c:out value='${command.srch_end_dt}'/>" class="text_calendar02" onKeypress="if(event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;" />
										</td>
										<th><spring:message code="clm.page.field.qna.srchPrgrsStatus" /></th>
										<td>
											<select name="srch_prgrs_status" id="srch_prgrs_status">
											</select>
										</td>
									</tr>
									<tr>										
										<th><spring:message code="clm.page.field.board.srchKeyword" /></th>
								    	<td colspan="3">
								    		<%String sel_title_width = "width:70px"; 
								    		if(!langCd.equals("ko")) sel_title_width = "width:170px";  %>
											<select id="srch_select_title" name="srch_select_title" style="<%=sel_title_width%>">
												<option value="title" default <c:if test="${command.srch_select_title eq 'title'}">selected</c:if>><spring:message code="clm.page.msg.common.title" /></option>
												<option value="regNm" <c:if test="${command.srch_select_title eq 'regNm'}">selected</c:if>><spring:message code="clm.page.msg.common.registrant" /></option>
												<option value="cont"  <c:if test="${command.srch_select_title eq 'cont'}">selected</c:if>><spring:message code="clm.page.msg.common.content" /></option>
											</select>
											<input type="text" id="srch_title" name="srch_title" style="width:300px" value="<c:out value='${command.srch_title}'/>" />
										</td>
									</tr>
								</table>
							</td>
							<td class="vb tC">
								<a href="javascript:pageAction('search');"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="clm.page.msg.common.inquire" htmlEscape="true" />"/></a>
							</td>
						</tr>
					</table>
				</div>
			</div>				
			<!--//search-->
	
			<!-- button -->
			<div class="btn_wrap_t">
				<span class="btn_all_b"><span class="save"></span><a href="javascript:pageAction('new');"><spring:message code="secfw.page.button.new" /></a></span>
			</div>
			<!-- //button -->
			<!-- list -->
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
				<colgroup>
				    <col width="5%"/>
					<col width="45%"/>
					<col width="10%"/>
					<col width="27%"/>
					<col width="8%"/>
					<col width="5%"/>
			  	</colgroup>
			  	<thead>
					<tr>
					    <th><spring:message code="las.page.field.board.notice.no" /></th>
						<th><spring:message code="clm.page.field.qna.title" /></th>
						<th><spring:message code="clm.page.field.qna.prgrsStatus" /></th>
						<th><spring:message code="clm.page.field.qna.regNm" /></th>
						<th><spring:message code="clm.page.field.qna.regDt" /></th>
						<th><spring:message code="clm.page.field.qna.rdcnt" /></th>
					</tr>
				</thead>
			 	<tbody>
				 	<c:choose>
				 	    <c:when test="${pageUtil.totalRow > 0}">
				 	        <c:forEach var="list" items="${resultList}">
							 	<tr onMouseOut="this.className='';this.style.cursor='pointer';" onMouseOver="this.className='on';this.style.cursor='pointer';" onClick="detailView(<c:out value='${list.up_seqno}'/>, <c:out value='${list.seqno}'/>,<c:out value='${list.postup_depth}'/> );">
							 	    <td class="tC"><c:if test="${list.rn != 0}"><c:out value="${list.rn}"/></c:if></td>
							 	    <td class="tL">
							 	    <c:if test="${list.up_seqno > 0}">
										<c:forEach var="x" begin="0" end="${list.postup_depth}" step="1" >
				          					&nbsp;           				
				          				</c:forEach>
				          				<img src="<%=IMAGE%>/icon/icon_reply.gif"></img>
									</c:if>
									<c:if test="${list.new_yn eq 'Y'}">
										<img src="<%=IMAGE%>/icon/ico_new_m.gif" alt="new"/>
									</c:if>
						 	        <c:out value='${list.title}'/>
						 	        </td>
						 	        
						 	        <c:choose>
							 	        <c:when test="${list.up_seqno > 0 && list.prgrs_status == 'C01702'}">
							 	        	<td class="tC">-</td>
							 	        </c:when>
							 	        <c:otherwise>
											<td class="tC"><c:out value='${list.prgrs_status_nm}'/></td>							 	        
						 	        	</c:otherwise>
						 	        </c:choose>
						 	        <td class="tL"><c:out value='${list.reg_nm}'/>/<c:out value='${list.regman_jikgup_nm}'/>/<c:out value='${list.reg_dept_nm}'/></td>
						 	        <td class="tC"><c:out value='${list.reg_dt}'/></td>
			 	        			<td class="tC"><c:out value='${list.rdcnt}'/></td>
							 	</tr>
							 </c:forEach>
					 	</c:when>
					 	<c:otherwise>
					 	    <tr>
						        <td colspan="6" class="td_ac" align="center"><spring:message code="secfw.msg.succ.noResult" /></td>
						    </tr>
					 	</c:otherwise>
				 	</c:choose>
			    </tbody>
			</table>
			<!-- //list -->

			<div class="total_num">
				<spring:message code="secfw.page.field.common.totalData" />: <c:out value="${total_cnt}"/><spring:message code="clm.page.msg.common.case" />
			</div>
			
			<!-- pagination  -->
			<div class="pagination">
			<%=pageUtil.getPageNavi(pageUtil, "") %>
			</div>
			<!-- //pagination -->
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