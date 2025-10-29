<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="java.util.List" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%--
/**
 * 파  일  명 : CustomerView.jsp
 * 프로그램명 : 계약상대방 리스트
 * 설      명 : 
 * 작  성  자 : dawn
 * 작  성  일 : 2011.11.15
 */
--%>
<%
    String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><spring:message code="clm.main.title" /></title>
<link href="<%=CSS%>/clm.css"    type="text/css" rel="stylesheet">


<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript">

	/**
	* 페이징 function
	*/
	function goPage(pgNum){
		viewHiddenProgress(true);
		
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/draft/customerTest.do' />";
		frm.method.value = "listCustomerShare";
		frm.curPage.value = pgNum;
		
		
		frm.submit();
	}
	
	/**
	* 버튼 동작 부분
	*/
	function pageAction(){
		var frm = document.frm;
		
		var srchValue = comTrim(frm.srch_customer.value);
		
		//최소 2글자 이상 넣을 경우만 조회가능하게.
		if (srchValue == "" || srchValue.length < 4) {
			alert('<spring:message code='secfw.msg.error.nameMinByte' />');
			return;
		}
		frm.target = "_self";
		frm.action = "<c:url value='/clm/draft/customerTest.do'/>";
	    frm.method.value = "listCustomerShare";
		frm.curPage.value = "1";
		
		viewHiddenProgress(true);
		
		frm.submit();	
	}
	
	//리스트 click 시 - 상세 페이지로 이동하게 변경 하기
	function rowOnClick(customerCd, dodun){
		var frm = document.frm;
		
		frm.customer_cd.value = customerCd;
		frm.dodun.value       = dodun;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/draft/customerTest.do'/>";
		
		frm.method.value = "detailCustomerShare";
		frm.submit();	
		
	}
    
</script>
</head>
<body>

<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id="frm" method="post" autocomplete="off">

<!-- search form -->
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />
<!-- key form-->
<input type="hidden" name="customer_gb" id="customer_gb" value="<c:out value='${command.customer_gb}'/>" />
<input type="hidden" name="selectCnt"   id="selectCnt"   value="<c:out value='${command.selectCnt}'/>" />
<input type="hidden" name="selectRow"   id="selectRow"   value="<c:out value='${command.selectRow}'/>" />

<input type="hidden" name="customer_cd" />
<input type="hidden" name="dodun" />

<!-- 첨부파일정보 -->

<!-- // **************************** Form Setting **************************** -->
<!-- header -->
<div id="wrap">
<!-- container -->
	<div id="container">
		<!-- Location -->
	<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
	<!-- //Location -->
		<!-- Title -->
		<div class="title">
		<h3><spring:message code="clm.page.field.share.customerList" /></h3>
		</div>

<!-- //header -->
<!-- body -->
		<!-- search-->
		<div id="content">
		
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
									<col width="90%"/>
								</colgroup>
								<tr>
									<th><spring:message code="clm.page.field.customer.customerNm1" /></th>
									<td> 
									    <input type="text" id="srch_customer" name="srch_customer" style="width:200px" value="<c:out value='${command.srch_customer}' />" onKeyPress="if(event.keyCode==13) {pageAction();return false;}"  />
									</td>
								</tr>
							</table>
						</td>
						<td class="vb tC">
							<a href="javascript:pageAction();"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="clm.page.msg.common.inquire" htmlEscape="true" />"/></a>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<!--//search-->
		<!-- list -->
		<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
			<colgroup>
				    <col width="20%"/>
				    <col width="20%"/>
				    <col width="19%"/>
				    <col width="9%"/>
				    <col width="18%"/>
				    <col width="14%"/>
		  	</colgroup>
		  	<thead>
				<tr>
				    <th><spring:message code="clm.page.field.customer.gunam"/></th>
				    <th><spring:message code="clm.page.field.customer.donam"/></th>
				    <th><spring:message code="clm.page.field.customer.linbu"/></th>
				    <th><spring:message code="clm.page.field.customer.nation"/></th>
				    <th><spring:message code="clm.page.field.customer.street"/></th>
				    <th><spring:message code="clm.page.field.customer.taxNo"/></th>
				</tr>
			</thead>
		 	<tbody>
		 	    <c:choose>
				 	<c:when test="${pageUtil.totalRow > 0}">
				 	    <c:forEach var="list" items="${customerList}" varStatus="listCnt">
				 	        <tr onMouseOut="this.className='';this.style.cursor='pointer';" onMouseOver="this.className='on';this.style.cursor='pointer';">
				 	            <c:choose>
			                    <c:when test="${list.gudun == list.dodun}">
							 	            <td class="tL" onClick="javascript:rowOnClick('<c:out value='${list.customer_cd}'/>','<c:out value='${list.dodun}'/>');"><font color="0061b6"><c:out value="${list.gunam}"/></font></td>
				 	                        <td class="tL" onClick="javascript:rowOnClick('<c:out value='${list.customer_cd}'/>','<c:out value='${list.dodun}'/>');"><font color="0061b6"><c:out value="${list.donam}"/></font></td>
						 	    </c:when>
						 	    <c:when test="${list.gudun != list.dodun}">
							 	            <td class="tL" onClick="javascript:rowOnClick('<c:out value='${list.customer_cd}'/>','<c:out value='${list.dodun}'/>');"><c:out value="${list.gunam}"/></td>
				 	                        <td class="tL" onClick="javascript:rowOnClick('<c:out value='${list.customer_cd}'/>','<c:out value='${list.dodun}'/>');"><c:out value="${list.donam}"/></td>
						 	    </c:when>
						 	    </c:choose>
				 	            <td class="tL txtover" title="<c:out value="${list.linbu}"/>" onClick="javascript:rowOnClick('<c:out value='${list.customer_cd}'/>','<c:out value='${list.dodun}'/>');"><nobr><c:out value="${list.linbu}"/></nobr></td>
				 	            <td class="tC" onClick="javascript:rowOnClick('<c:out value='${list.customer_cd}'/>','<c:out value='${list.dodun}'/>');"><c:out value="${list.nation_nm}"/></td>
				 	            <td class="tL txtover" title="<c:out value="${list.street}"/>" onClick="javascript:rowOnClick('<c:out value='${list.customer_cd}'/>','<c:out value='${list.dodun}'/>');"><nobr><c:out value="${list.street}"/></nobr></td>
				 	            <td class="tC" onClick="javascript:rowOnClick('<c:out value='${list.customer_cd}'/>','<c:out value='${list.dodun}'/>');"><c:out value="${list.tax_no}"/></td>
				 	        </tr>
				 	    </c:forEach>
				 	</c:when>
				    <c:otherwise>
				 	    <tr>
					        <td colspan="7" class="td_ac" align="center"><spring:message code="secfw.msg.succ.noResult" /></td>
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
	  <script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
	  <jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //Popup Detail -->
</div>
<!-- //body -->
</form:form>
</body>
</html>