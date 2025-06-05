<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%-- 
/**
 * 파  일  명 : DecideArbitrarilyRe_l.jsp
 * 프로그램명 : 전결규정 목록
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
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
	/**
	* 페이징 function
	*/
	function goPage(pgNum){
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/admin/decideArbitrarilyRe.do' />";
		frm.method.value = "listDecideArbitrarilyRe";
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
		frm.action = "<c:url value='/clm/admin/decideArbitrarilyRe.do' />";
		
		if(flag == "search"){//조회
			frm.method.value = "listDecideArbitrarilyRe";
			frm.curPage.value = "1";
			viewHiddenProgress(true);
		    frm.submit();
		}else if(flag == "new"){//등록
			frm.method.value = "forwardInsertDecideArbitrarilyRe";
			viewHiddenProgress(true);
		    frm.submit();
		}
	}
	
	/**
	* 상세내역 조회
	*/
	function detailView(seqno){
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/admin/decideArbitrarilyRe.do' />";
		frm.seqno.value = seqno;
		frm.method.value = "detailDecideArbitrarilyRe";
		viewHiddenProgress(true);
		frm.submit();
	}
	
	/**
	* 메시지 처리
	*/
	function showMessage(msg){
		if( msg != "" && msg != null && msg.length > 1 ) {
			alert(msg);
		}
	}
	
</script>
</head>

<body onLoad='showMessage("<c:out value='${returnMessage}'/>");'>
<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id='frm' method="post" autocomplete="off"> 
<!-- search form -->
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />
<!-- Detail Page Param -->
<!-- key Form -->
<input type="hidden" name="seqno"    value="<c:out value='${command.seqno}'/>" />
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
		<h3><spring:message code="clm.page.title.decidearbitrarilyre.listTitle" /></h3>
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
										<th><spring:message code="clm.page.field.decidearbitrarilyre.srchOperdivCd" /></th>
										<td>
											<select name="srch_operdiv_cd" id="srch_operdiv_cd">
										    <option value="">-- <spring:message code="clm.page.msg.common.all" /> --</option>
										    <c:forEach var="list" items="${operdivCdList}">
										        <option value="<c:out value='${list.orgnz_cd}' />"<c:if test="${list.orgnz_cd eq command.srch_operdiv_cd}">selected</c:if>><c:out value='${list.orgnz_nm}' /></option>
										    </c:forEach>
										</select>
										</td>
										<th><spring:message code="clm.page.field.decidearbitrarilyre.srchBizClsfcn" /></th>
										<td>
											<select name="srch_biz_clsfcn" id="srch_biz_clsfcn">
										    <option value="">-- <spring:message code="clm.page.msg.common.all" /> --</option>
										    <c:forEach var="list" items="${bizClsfcnList}">
										        <option value="<c:out value='${list.type_cd}' />"<c:if test="${list.type_cd eq command.srch_biz_clsfcn}">selected</c:if>><c:out value='${list.cd_nm}' /></option>
										    </c:forEach>
										</select>
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
	
			<!-- button -->
			<c:if test="${roleCd.role_cd != 'RC01'}"><!-- CP관리자는 조회만 가능. -->
				<div class="btn_wrap_t">
					<span class="btn_all_b"><span class="save"></span><a href="javascript:pageAction('new');"><spring:message code="secfw.page.button.new" /></a></span>
				</div>
			</c:if>
			<!-- //button -->
			
			<!-- list -->
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
				<colgroup>
				    <col width="30%"/>
					<col width="30%"/>
					<col width="30%"/>
			  	</colgroup>
			  	<thead>
					<tr>
					    <th><spring:message code="clm.page.field.decidearbitrarilyre.bizClsfcn" /></th>
						<th><spring:message code="clm.page.field.decidearbitrarilyre.operdivCd" /></th>
						<th><spring:message code="clm.page.field.decidearbitrarilyre.dcdAuthman" /></th>
					</tr>
				</thead>
			 	<tbody>
			 	    <c:choose>
			 	    <c:when test="${pageUtil.totalRow > 0}">
				 	    <c:forEach var="list" items="${resultList}">
				 	        <tr onmouseout="this.className='';this.style.cursor='pointer';" onmouseover="this.className='on';this.style.cursor='pointer';" onclick="detailView(<c:out value='${list.seqno}'/>);">
				 	            <td><c:out value="${list.biz_clsfcn_nm}" /></td>
				 	            <td><c:out value="${list.operdiv_nm}" /></td>
				 	            <td><c:out value="${list.dcd_authman_nm}" /></td>
				 	        </tr>
				 	     </c:forEach>
		 	         </c:when>
				 	 <c:otherwise>
				 	    <tr>
					        <td colspan="3" class="td_ac" align="center"><spring:message code="secfw.msg.succ.noResult" /></td>
					    </tr>
				 	 </c:otherwise>
			 	     </c:choose>
			    </tbody>
			</table>
			<!-- //list -->
			<div class="total_num">
				<spring:message code="secfw.page.field.common.totalData" />: <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%><spring:message code="clm.page.msg.common.case" />
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