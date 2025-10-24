<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil"%>
<%@ page import="com.sds.secframework.common.util.DateUtil"%>
<%@ page import="com.sds.secframework.common.util.WebUtil"%>
<%@ page import="com.sds.secframework.common.util.Token"%>

<%@ page import="java.util.Date"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap"%>

<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<%-- 
/**
 * 파  일  명 : MainImgView.jsp
 * 프로그램명 : 메인화면관리
 * 설      명 : 
 * 작  성  자 : 이준석
 * 작  성  일 : 2013.04.16
 */
--%>
<%
    //메뉴네비게이션 스트링
			String menuNavi = (String) request.getAttribute("secfw.menuNavi");
			String existCompYn = StringUtil.bvl((String) session.getAttribute("secfw.session.exist_comp_yn"), "");
			String imgCompCd = existCompYn.equals("Y") ? "_" + compCd : "";
%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="secfw.page.title.mainImgMng.title" />
</title>
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script type="text/javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script type="text/javascript"> 
	/**
	 * 버튼 동작 부분
	 */
	function pageAction(flag) {

		var frm = document.frm;
		//저장
		if (flag == "insert") {
			//유효성 체크
			if (validateForm(frm)) {

				var confirmMessage = "<spring:message code='secfw.msg.ask.update' />";

				if (confirm(confirmMessage)) {

					var fileNmKo = frm.mainImgFileKo.value;
					var fileNmEn = frm.mainImgFileEn.value;

					if ((isAllowImg(fileNmKo) || fileNmKo == "")
							&& (isAllowImg(fileNmEn) || fileNmEn == "")) {
						viewHiddenProgress(true);
						frm.method.value = "updateMainImage";
						frm.target = "_self";
						frm.action = "<c:url value='/secfw/mainPageMng.do' />";
						frm.submit();
					} else {
						alert("<spring:message code='secfw.page.msg.mainImgMng.announce001' />");
					}
				}
			}

			//취소
		} else if (flag == "cancel") {

			viewHiddenProgress(true);
			frm.method.value = frm.remitcert_no.value == ("") ? "listRemitCert"
					: "detailLawfirmRemitCert";
			frm.action = "<c:url value='/las/lawservice/lawfirmRemitCert.do' />";
			frm.target = "_self";
			frm.submit();
		}
	}

	function isAllowImg(fileNm) {
		var fileExt = fileNm.substring(fileNm.lastIndexOf(".") + 1).toLowerCase();
		return (fileExt == "png");
  }
</script>
</head>
<body>

	<div id="wrap">
		<!-- container -->
		<div id="container">
			<!-- location -->
			<div class="location">
				<img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" />
				<c:out value='${menuNavi}' escapeXml="false" />
			</div>
			<!-- //location -->
			<!-- title -->
			<div class="title">
				<h3>
					<spring:message code="secfw.page.title.mainImgMng.title" />
				</h3>
			</div>
			<!-- //title -->

			<!-- content -->
			<div id="content">
				<div id="content_in">
					<form:form name="frm" id="frm" method="post" autocomplete="off" enctype="multipart/form-data">
						<input type="hidden" name="method" value="" />
						<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
						<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>" />
						<input type="hidden" name="forward_url" value="" />
						<table class="list_basic tr_nobg" cellspacing="0" cellpadding="0">
							<tbody>
							</tbody>
							<col width="150px" />
							<col /> 
							<thead>
								<tr>
									<th width="10%"><spring:message code="clm.page.msg.common.div" />
									</th>
									<th width="90%"><spring:message code="secfw.page.image.mainImgMng.image" />
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="sub vm" align="center" rowspan="2"><spring:message code="secfw.page.field.mainImgMng.ko" /></td>
									<td style="background:url(/attach/main/ko/main_img<%=imgCompCd%>.png) no-repeat left top;" height="260"></td>
								</tr>
								<tr>
									<td><input class="text" style="position: relative; text-align: right; -moz-opacity: 0; filter: alpha(opacity :   100); opacity: 100;" type="file"
										id="mainImgFileKo" name="mainImgFileKo" /><br>
									</td>
								</tr>
								<tr>
									<td class="sub vm" align="center" rowspan="2"><spring:message code="secfw.page.field.mainImgMng.en" /></td>
									<td style="background:url(/attach/main/en/main_img<%=imgCompCd%>.png) no-repeat left top;" height="260"></td>
								</tr>
								<tr class="end">
									<td><input class="text" style="position: relative; text-align: right; -moz-opacity: 0; filter: alpha(opacity :   100); opacity: 100;" type="file"
										id="mainImgFileEn" name="mainImgFileEn" /> <br>
									</td>
								</tr>
							</tbody>
							<tbody>
							</tbody>
						</table>
						<div class="btn_wrap">
							<span class="btn_all_w"><span class="save"></span><a href="javascript:pageAction('insert');"><spring:message code="secfw.page.button.insert" />
							</a>
							</span>
						</div>
					</form:form>
				</div>
			</div>
			<!-- //content -->

			<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp" />
		</div>
		<!-- //container -->
	</div>
	</div>
	<!-- footer  -->
	<script type="text/javascript" src="/script/clms/footer.js"></script>
	<!-- // footer -->
</body>
</html>