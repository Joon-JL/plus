<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%--
/**
 * 파     일     명 : BBSMngList.jsp
 * 프로그램명 : 게시판 관리 목록
 * 설               명 : 
 * 작     성     자 : 금현서
 * 작     성     일 : 2009.11
 */
--%>
<%

	//메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");

	//게시판관리 목록
	ArrayList resultList = (ArrayList)request.getAttribute("resultList");

	//처리결과 메시지
    String returnMessage = (String)request.getAttribute("returnMessage");
	
	//PageUtil 객체
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="secfw.page.title.bbs.BBSMngList" /></title>

<LINK href="<c:url value='/style/secfw/common.css' />" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript">
<!--

	/**
	* 페이징 function
	*/
	function goPage(pgNum){
		var frm = document.frm;

		frm.target = "_self";
		frm.action = "<c:url value='/secfw/bbsMng.do' />";
		frm.method.value = "listBBSMasterPage";
		frm.doSearch.value= "Y";
		frm.curPage.value = pgNum;
		frm.submit();
	}

	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		var frm = document.frm;
	
		if(flag == "search"){
			
			frm.target = "_self";
			frm.action = "<c:url value='/secfw/bbsMng.do' />";
		    frm.method.value = "listBBSMasterPage";
		    
			frm.doSearch.value = "Y";
			frm.curPage.value = "1";

			frm.submit();

		} else if(flag == "new"){	
		    PopUpWindowOpen('', 600, 450, false);
			frm.target = "PopUpWindow";

			frm.action = "<c:url value='/secfw/bbsMng.do' />";
			frm.method.value = "goInsertBBSMaster";

			frm.submit();
		}
	}

	/**
	* 상세내역 조회
	*/
	function detailView(bbs_id){
		var frm = document.frm;

	    frm.bbs_id.value = bbs_id;

	    PopUpWindowOpen('', 600, 450, false);
		frm.target = "PopUpWindow";

		frm.action = "<c:url value='/secfw/bbsMng.do' />";
		frm.method.value = "detailBBSMaster";

		frm.submit();
	}

	/**
	* 메시지 처리
	*/
	function showMessage(msg){

		if(msg != null && msg.length > 1) {
			alert(msg);
		}
	}

	function reload() {
		pageAction('search');
	}
//window.name='secfw.BBSMng';	
//-->
</script>

</head>
<body onLoad="showMessage('<%=returnMessage %>')">
<div class="contentWrap">
	<div class="content_area">
<!-- 
**************************** Form Setting **************************** 
-->
<form:form name="frm" method="post" autocomplete="off">
	<!-- 페이지 공통 -->
	<input type="hidden" name="method"   value="">
	
	<input type="hidden" name="doSearch" value="N">
	<input type="hidden" name="curPage" <c:out value="${command.curPage}"/> >
	
	<!-- 페이지별  -->
	<input type="hidden" name="bbs_id" value="">
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
			              <th><spring:message code="secfw.page.field.bbs.bbsName"/> :</th>
			              <td><input type="text" name="srch_bbs_nm" class="text"  <c:out value="${command.srch_bbs_nm}"/> OnKeyDown="if(event.keyCode==13) { pageAction('search'); }"/></td>
			              <!-- 사용여부 -->
			              <th><spring:message code="secfw.page.field.bbs.useYesno"/> :</th>
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
		<div class="bt_content_top"><a href="javascript:pageAction('new')" class="bt_fn"><span><spring:message code="secfw.page.button.new" /><img src="<c:url value='/images/secfw/ico/ico_tri.gif' />"></span></a></div>
		<!-- //Button -->

		<!-- //Clear -->
		<br>
		
		<!-- List -->
		<div class="dscrollx" style="height:293px;">
			<table class="basic_list mz fix" style="width:1280px;" >
	          <colgroup>
	          <col align="center" width="40px"/>
	          <col align="center" width="200px"/>
	          <col align="left" />
	          <col align="center" width="90px"/>
	          <col align="center" width="70px"/>
	          <col align="center" width="90px"/>
	          <col align="center" width="90px"/>
	          <col align="center" width="70px"/>
	          <col align="center" width="70px"/>
	          <col align="center" width="120px"/>
	          <col align="center" width="70px"/>
	          <col align="center" width="120px"/>
	          </colgroup>
	          <tr>
	            <th><spring:message code="secfw.page.field.common.no" /></th><!-- 순번 -->
	            <th><spring:message code="secfw.page.field.bbs.bbs_id" /></th><!-- 게시판아이디 -->
	            <th><spring:message code="secfw.page.field.bbs.bbs_nm" /></th><!-- 게시판명 -->
	            <th><spring:message code="secfw.page.field.bbs.new_hold_term" /></th><!-- 새글유지일자 -->
	            <th><spring:message code="secfw.page.field.bbs.reply_yn" /></th><!-- 댓글 -->
	            <th><spring:message code="secfw.page.field.bbs.append_yn" /></th><!-- 덧글 -->
	            <th><spring:message code="secfw.page.field.bbs.file_yn" /></th><!-- 첨부파일 -->
	            <th><spring:message code="secfw.page.field.bbs.urgency_yn" /></th><!-- 긴급 -->
	            <th><spring:message code="secfw.page.field.bbs.recommend_yn" /></th><!-- 추천 -->
	            <th><spring:message code="secfw.page.field.bbs.hold_term_yn" /></th><!-- 게시기간설정 -->
	            <th><spring:message code="secfw.page.field.bbs.use_yn" /></th><!-- 사용여부 -->
	            <th><spring:message code="secfw.page.field.common.mod_dt" /></th><!-- 수정일시 -->
	          </tr>
	<%
		if(pageUtil.getTotalRow() > 0){
			int idx = (pageUtil.getThisPage() -1)*pageUtil.getRowPerPage()+1;
			    for(idx=0;idx < resultList.size();idx++){
			    
			    	ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
			    	
	%>
	          <tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
	            <td><%=lom.get("rn") %></td>
	            <td><%=lom.get("bbs_id") %></td>
	            <td class="overflow"><a href="javascript:detailView('<%=lom.get("bbs_id") %>');"><%=lom.get("bbs_nm") %></a></td>
	            <td><%=lom.get("new_hold_term") %></td>
	            <td><%=lom.get("reply_yn") %></td>
	            <td><%=lom.get("append_yn") %></td>
	            <td><%=lom.get("file_yn") %></td>
	            <td><%=lom.get("urgency_yn") %></td>
	            <td><%=lom.get("recommend_yn") %></td>
	            <td><%=lom.get("hold_term_yn") %></td>
	            <td><%=lom.get("use_yn") %></td>
	            <td><%=lom.get("reg_dt") %></td>
	          </tr>

	<%
			    }
		} else { // 조회된 데이타가 없을경우
	%>
	          <tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
	            <td colspan="12"><spring:message code="secfw.msg.succ.noResult" /></td>
	          </tr>
	<%
		}
	%>
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