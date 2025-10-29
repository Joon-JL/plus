<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%-- 
/**
 * 파  일  명 : AboutClmManage_l.jsp
 * 프로그램명 : AboutCLM관리
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 :
 * 수  정  자 : 이준석 
 * 수  정  일 : 2013.05.03
 */
--%>
<%
    String menuNavi = (String) request.getAttribute("secfw.menuNavi");
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<link href="<%=CSS%>/clm.css" type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript">
	
	/**
	* 상세내역 조회
	*/
	function detailView(seqNo){
		var frm = document.frm;
		
		viewHiddenProgress(true);
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/admin/aboutClmManage.do' />";
		frm.method.value = "detailAboutClmManage";
		frm.seqno.value = seqNo;
		frm.submit();
	}

</script>
</head>

<body>
	<div id="wrap">
		<!-- container -->
		<div id="container">
			<!-- Location -->
			<div class="location">
				<img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0" />
				<c:out value='${menuNavi}' escapeXml="false" />
			</div>
			<!-- //Location -->
			<!-- Title -->
			<div class="title">
				<h3>
					<spring:message code="clm.page.title.aboutClmManage.listTitle" />
				</h3>
				<!-- //title -->
			</div>
			<!-- //title -->

			<!-- content -->
			<div id="content">
				<!-- content in -->
				<div id="content_in">

					<form:form name="frm" id='frm' method="post" autocomplete="off">
						<!-- **************************** Form Setting **************************** -->
						<!-- search form -->
						<input type="hidden" name="method" value="" />
						<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
						<!-- Detail Page Param -->
						<!-- key Form -->
						<input type="hidden" name="seqno" value="<c:out value='${command.seqno}'/>" />
						<!-- **************************** Form Setting **************************** -->

						<!-- list -->
						<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
							<colgroup>
								<col width="5%" />
								<col width="30%" />
								<col width="55%" />
							</colgroup>
							<thead>
								<tr>
									<th><spring:message code='clm.page.field.aboutClmManage.seqNo' /></th>
									<th><spring:message code='clm.page.field.aboutClmManage.title' /></th>
									<th><spring:message code='clm.page.field.aboutClmManage.menuPos' /></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="list" items="${resultList}" varStatus="status">
									<tr onmouseout="this.className='';this.style.cursor='pointer';" onmouseover="this.className='on';this.style.cursor='pointer';"
										onclick="detailView(<c:out value='${list.seqno}'/>);">
										<td><c:out value="${status.count}" /></td>
										<%
										    if ("fr".equals(langCd)) {
										%>
										<td><c:out value="${list.title_fr}" /></td>
										<td><c:out value="${list.menu_pos_fr}" /></td>
										<%
										    } else if ("de".equals(langCd)) {
										%>
										<td><c:out value="${list.title_de}" /></td>
										<td><c:out value="${list.menu_pos_de}" /></td>
										<%
										    } else {
										%>
										<td><c:out value="${list.title_en}" /></td>
										<td><c:out value="${list.menu_pos_en}" /></td>
										<%
										    }
										%>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<!-- //list -->
					</form:form>
				</div>
				<!-- //content in-->
			</div>
			<!-- //content -->
			<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp" />

		</div>
		<!-- //container -->
	</div>
	<!-- footer -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
	<!-- //footer -->
</body>
</html>