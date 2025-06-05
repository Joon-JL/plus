<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%--
/**
 * 파     일     명 : BoardSMngList.jsp
 * 프로그램명 : 게시판 관리 목록
 * 설               명 : 
 * 작     성     자 : 강성문
 * 작     성     일 : 2011.03.16
 */
--%>
<%

	//메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");

	//페이징처리
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil") ;
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="secfw.page.title.bbs.BBSMngList" /></title>

<LINK href="<c:url value='/style/secfw/common.css' />" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/shri/shri.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript">
	function goPage(page) {
		var f = document.frm ;
		f.board_id.value ="" ;
		f.curPage.value = page ;
		f.method.value = "listBoardMng" ;
		f.action = "<c:url value='/secfw/boardMng.do' />" ;
		f.submit() ; 
	}

	function pageAction(flag, boardId) {
		var f = document.frm ;

		// 조회
		if(flag=="search") {
			goPage(1) ;
		}
		// 등록
		else if(flag=="insertForm") {
			f.method.value = "forwardInsertBoardMngForm" ;
			f.action = "<c:url value='/secfw/boardMng.do' />" ;
			f.submit() ;
		}
		// 상세
		else if(flag=="modifyForm"){
			f.board_id.value = boardId ;
			f.method.value = "forwardModifyBoardMngForm" ;
			f.action = "<c:url value='/secfw/boardMng.do' />" ;
			f.submit() ;
		}
	}
</script>

</head>
<body onLoad="alertMessage('<c:out value="${command.return_message}"/>')">
<div class="contentWrap">
	<div class="content_area">
<!-- 
**************************** Form Setting **************************** 
-->
<form:form name="frm" method="post" autocomplete="off">
	<!-- 페이지 공통 -->
	<input type="hidden" name="method" value="">
	<input type="hidden" name="curPage" value="${command.curPage}">
	
	<!-- 페이지별  -->
	<input type="hidden" name="board_id">
<!-- //
**************************** Form Setting **************************** 
-->
		<!-- Location -->
		<div class="location"><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- Title -->
		<h3><spring:message code="secfw.page.title.bbs.BBSMngList" /></h3>
		<!-- Search -->
		<div class="search_box">
			<div class="search_box_in">
				<table class="search_in">
					<colgroup>
					<col/>
					<col/>
					<col/>
					<col/>
					<col/>
					</colgroup>
					<tr>
			              <!-- 게시판명 -->
			              <th><spring:message code="secfw.page.field.board.bbsName"/> :</th>
			              <td><input type="text" name="srch_board_nm" class="text"  value="<c:out value='${command.srch_board_nm}'/>" onKeyDown="if(event.keyCode==13) { pageAction('search'); }"/></td>
			              <!-- 사용여부 -->
			              <th><spring:message code="secfw.page.field.board.useYesno"/> :</th>
			              <td>
			                <select name="srch_use_yn" id="use_yn" class="select">
			                  <option value=""  <c:if test="${command.srch_use_yn eq ''}">selected</c:if>><spring:message code="secfw.page.field.common.total" /></option>
			                  <option value="Y" <c:if test="${command.srch_use_yn eq 'Y'}">selected</c:if>><spring:message code="secfw.page.field.common.use" /></option>
			                  <option value="N" <c:if test="${command.srch_use_yn eq 'N'}">selected</c:if>><spring:message code="secfw.page.field.common.not_use" /></option>
			                </select>
			              </td>
			              <td>
			                <!-- 조회 -->
			              	<a href="javascript:pageAction('search');" class="bt_search"><span><spring:message code="secfw.page.button.search" /></span></a>
			              </td>
					</tr>
				</table>
			</div>
		</div>
		<!-- //Search -->
		
		<!-- Total -->
		<div class="total"><p>Total : <strong><%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%></strong></p></div>
		<!-- Button -->
		<div class="bt_content_top"><a href="javascript:pageAction('insertForm')" class="bt_fn"><span><spring:message code="secfw.page.button.new" /><img src="<c:url value='/images/secfw/ico/ico_tri.gif' />"></span></a></div>
		<!-- //Button -->

		<!-- //Clear -->
		<br>
		
		<!-- List -->
		<div class="dscrollx" style="height:293px;">
			<table class="basic_list mz fix" style="width:1210px;" >
	          <colgroup>
	          <col align="center" width="40px"/>
	          <col align="center" width="200px"/>
	          <col align="left" />
	          <col align="center" width="90px"/>
	          <col align="center" width="90px"/>
	          <col align="center" width="90px"/>
	          <col align="center" width="120px"/>
	          <col align="center" width="70px"/>
	          <col align="center" width="120px"/>
	          </colgroup>
	          <tr>
	            <th><spring:message code="secfw.page.field.common.no" /></th><!-- 순번 -->
	            <th><spring:message code="secfw.page.field.bbs.bbs_id" /></th><!-- 게시판아이디 -->
	            <th><spring:message code="secfw.page.field.bbs.bbs_nm" /></th><!-- 게시판명 -->
	            <th><spring:message code="secfw.page.field.bbs.bbs_type" /></th><!-- 게시판타입 -->
	            <th><spring:message code="secfw.page.field.bbs.reply_yn" /></th><!-- 댓글 -->
	            <th><spring:message code="secfw.page.field.bbs.append_yn" /></th><!-- 덧글 -->
	            <th><spring:message code="secfw.page.field.bbs.file_yn" /></th><!-- 첨부파일 -->
	            <th><spring:message code="secfw.page.field.bbs.use_yn" /></th><!-- 사용여부 -->
	            <th><spring:message code="secfw.page.field.common.reg_dt" /></th><!-- 등록일시 -->
	          </tr>
	<c:choose>
		<c:when test="${pageUtil.totalRow > 0}">
			<c:forEach var="list" items="${command.resultList}">
	          <tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
	            <td class="overflow"><c:out value="${list.num}"/></td>
	            <td><c:out value="${list.board_id}"/></td>
	            <td><a href="javascript:pageAction('modifyForm','<c:out value="${list.board_id}"/>')"><c:out value="${list.board_nm}"/></a></td>
	            <td><c:out value="${list.board_type_nm}"/></td>
	            <td><c:out value="${list.reply_yn}"/></td>
	            <td><c:out value="${list.append_yn}"/></td>
	            <td><c:out value="${list.file_yn}"/></td>
	            <td><c:out value="${list.use_yn}"/></td>
	            <td><c:out value="${list.reg_dt}"/></td>
	          </tr>
	       </c:forEach>
	    </c:when>
	    <c:otherwise>
	          <tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
	            <td colspan="11"><spring:message code="secfw.msg.succ.noResult" /></td>
	          </tr>
	    </c:otherwise>
	</c:choose>
	        </table>
        </div>
		<!-- //List -->
		<!-- Paging  -->
      <div class="paging">
		<%=pageUtil.getPageNavi(pageUtil, "") %>
      </div>
		<!-- // Paging -->
	</form:form>
	<!-- Footer -->
	<address>
		<spring:message code="secfw.page.copyright.content.bottom" />
	</address>
	<!-- //Footer -->
	</div>
</div>
</body>
</html>