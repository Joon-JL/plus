<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil"%>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap"%>
<%-- 
/**
 * 파  일  명 : AboutClmManage_d.jsp
 * 프로그램명 : About CLM 관리 상세
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%
    String menuNavi = (String) request.getAttribute("secfw.menuNavi");

    ListOrderedMap lom = (ListOrderedMap) request.getAttribute("lom");
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="clm.main.title" />
</title>

<!-- style -->
<link href="<%=CSS%>/clm.css" type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript">
	$(document).ready(function() {
		//Lang 셋팅
// 		getCodeSelectByUpCd("frm","sel_lang",'/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=MULTI_LANG&combo_type=NULL&combo_del_yn=N&combo_selected=');
		showMessage('<c:out value="${returnMessage}"/>');
<%-- 		chglangContent("<%=langCd%>"); --%>
<%-- 		frm.sel_lang.value = "<%=langCd%>"; --%>
	});

	/**
	 * 버튼 동작 부분
	 */
	function pageAction(flag) {
		var frm = document.frm;

		frm.target = "_self";
		frm.action = "<c:url value='/clm/admin/aboutClmManage.do' />";
		if (flag == "modify") {
			viewHiddenProgress(true);
			frm.method.value = "forwardModifyAboutClmManage";
			frm.submit();

		} else if (flag == "list") {
			viewHiddenProgress(true);
			frm.method.value = "listAboutClmManage";
			frm.submit();
		}
	}

	/**
	 * 메시지 처리
	 */
	function showMessage(msg) {
		if (msg != "" && msg != null && msg.length > 1) {
			alert(msg);
		}
	}

	function chglangContent(v_sel_flag) {
		var vObj = document.all.td_cont;
		var vObj2 = document.all.td_title;
		var vObj3 = document.all.td_menu_pos;

		if (v_sel_flag == 'ko') {
			vObj.innerHTML = document.frm.cont.value;
			vObj2.innerHTML = document.frm.title.value;
			vObj3.innerHTML = document.frm.menu_pos.value;

		}else if (v_sel_flag == 'fr') {
			vObj.innerHTML = document.frm.cont_fr.value;
			vObj2.innerHTML = document.frm.title_fr.value;
			vObj3.innerHTML = document.frm.menu_pos_en.value;

		} else if (v_sel_flag == 'de') {
			vObj.innerHTML = document.frm.cont_de.value;
			vObj2.innerHTML = document.frm.title_de.value;
			vObj3.innerHTML = document.frm.menu_pos_en.value;

		} else {
			vObj.innerHTML = document.frm.cont_en.value;
			vObj2.innerHTML = document.frm.title_en.value;
			vObj3.innerHTML = document.frm.menu_pos_en.value;
		}

	}

</script>

</head>
<body>



	<!-- // **************************** Form Setting **************************** -->
	<div id="wrap">
		<!-- container -->
		<div id="container">

			<!-- Location -->
			<div class="location">
				<img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" />
				<c:out value='${menuNavi}' escapeXml="false" />
			</div>
			<!-- //Location -->

			<!-- title -->
			<div class="title">
				<h3>
					<spring:message code="clm.page.title.aboutClmManage.detailTitle" />
				</h3>
			</div>
			<!-- //title -->

			<!-- content -->
			<div id="content">
				<!-- content in -->
				<div id="content_in">
					<!-- **************************** Form Setting **************************** -->
					<form:form name="frm" id="frm" method="post" autocomplete="off">

						<!-- search form -->
						<input type="hidden" name="method" value="" />
						<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
						<!-- key form-->
						<input type="hidden" name="seqno" value="<c:out value='${command.seqno}'/>" />

						<input type="hidden" name="title" value="<c:out value='${lom.title}'/>" />
						<input type="hidden" name="title_en" value="<c:out value='${lom.title_en}'/>" />
						<input type="hidden" name="title_fr" value="<c:out value='${lom.title_fr}'/>" />
						<input type="hidden" name="title_de" value="<c:out value='${lom.title_de}'/>" />
						<input type="hidden" name="menu_pos" value="<c:out value='${lom.menu_pos}'/>" />
						<input type="hidden" name="menu_pos_en" value="<c:out value='${lom.menu_pos_en}'/>" />
						<input type="hidden" name="cont" id="cont" 	value="<c:out value='${lom.cont}'/>" />
<%-- 						<textarea id="cont" name="cont" style="display: none"><%=StringUtil.convertNamoCharsToHtml((String) lom.get("cont"))%></textarea> --%>
						<textarea id="cont_en" name="cont_en" style="display: none"><%=StringUtil.convertNamoCharsToHtml((String) lom.get("cont_en"))%></textarea>
						<textarea id="cont_fr" name="cont_fr" style="display: none"><%=StringUtil.convertNamoCharsToHtml((String) lom.get("cont_fr"))%></textarea>
						<textarea id="cont_de" name="cont_de" style="display: none"><%=StringUtil.convertNamoCharsToHtml((String) lom.get("cont_de"))%></textarea>
						
						<div class="btnwrap">
							<span class="btn_all_w"><span class="edit"></span><a href="javascript:pageAction('modify');"><spring:message code="secfw.page.button.modify"/></a></span> 
							<span class="btn_all_w"><span class="list"></span><a href="javascript:pageAction('list');"><spring:message code="secfw.page.button.list" /></a></span>
						</div>
						
						<!-- view -->
						<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
							<colgroup>
								<col width="10%" />
								<col width="*" />
							</colgroup>
							<tbody>
								<tr>
									<th><spring:message code="clm.page.field.aboutClmManage.title" />
									</th>
									<%
									    if ("fr".equals(langCd)) {
									%>
									<td id='td_title' name='td_title'><%=lom.get("title_fr")%></td>
									<%
									    } else if ("de".equals(langCd)) {
									%>
									<td id='td_title' name='td_title'><%=lom.get("title_de")%></td>
									<%
									    } else {
									%>
									<td id='td_title' name='td_title'><%=lom.get("title_en")%></td>
									<%
									    }
									%>									
								</tr>
								<tr>
									<th><spring:message code="clm.page.field.aboutClmManage.menuPos" />
									</th>
									<%
									    if ("fr".equals(langCd)) {
									%>
									<td id='td_menu_pos'><%=lom.get("menu_pos_fr")%></td>
									<%
									    } else if ("de".equals(langCd)) {
									%>
									<td id='td_menu_pos'><%=lom.get("menu_pos_de")%></td>
									<%
									    } else {
									%>
									<td id='td_menu_pos'><%=lom.get("menu_pos_en")%></td>
									<%
									    }
									%>
									
								</tr>
<!-- 								<tr> -->
<%-- 									<th><spring:message code="clm.page.field.aboutClmManage.language" /> --%>
<!-- 									</th> -->
<!-- 									언어 -->
<!-- 									<td><select class="srch" name="sel_lang" id="sel_lang" onchange="chglangContent(this.value);"></select></td> -->
<!-- 								</tr> -->
								<tr>
									<th><spring:message code="clm.page.field.aboutClmManage.cont" />
									</th>
									<td><%=StringUtil.convertNamoCharsToHtml((String) lom.get("cont"))%></td>
								</tr>
								<tr class="end">
									<th><spring:message code="clm.page.field.aboutClmManage.srchCont" />
									</th>
									<td>
										<%=StringUtil.convertEnterToBR((String)lom.get("srch_cont"))%>
<%-- 										<%=StringUtil.bvl(lom.get("srch_cont"), "")%> --%>
									</td>
								</tr>
							</tbody>
						</table>
						<!-- //view -->

						<div class="btnwrap mt20">
							<span class="btn_all_w"><span class="edit"></span><a href="javascript:pageAction('modify');"><spring:message code="secfw.page.button.modify" />
							</a>
							</span> <span class="btn_all_w"><span class="list"></span><a href="javascript:pageAction('list');"><spring:message code="secfw.page.button.list" />
							</a>
							</span>
						</div>
				</div>
				</form:form>
				<!-- //content in -->
			</div>
			<!-- //content -->

			<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp" />
		</div>
		<!-- //container -->
	</div>
	<!-- footer  -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
	<!-- // footer -->
</body>
</html>