<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%@ include file= "/WEB-INF/jsp/common/common.jsp" %>
<%-- 
/**
 * 파  일  명 : Guideline_d.jsp
 * 프로그램명 : 계약서 Guideline 등록
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 2011.09
 */
 --%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.page.title.contract.foreign.Library" /></title>
<LINK href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/util/fckeditor/fckeditor.js' />"></script>
<script language="javascript">

	function cancel() {
		
		if (confirm("<spring:message code='secfw.msg.ask.cancel' />")){
	
			var frm = document.frm;
	
			viewHiddenProgress(true) ;
			
			frm.action = "<c:url value='/las/lawinformation/guidenedu.do' />";
			frm.method.value = "listGuideline";
			frm.target = "_self";
			frm.submit();
		}
	}
	
	function save() {
		var frm = document.frm;
		
	    //유효성 체크
	    if(validateForm(frm)){
	    	
	    	if(confirm("<spring:message code='las.msg.ask.insert'/>")){
	
			    frm.body_mime.value = frm.wec.MIMEValue;

				frm.action = "<c:url value='/las/lawinformation/guidenedu.do' />";
				frm.method.value = "insertGuideline";
				frm.target = "_self";
	
				viewHiddenProgress(true) ;
				frm.submit();
	    	}
		}
	}
	
	$(document).ready(function(){
		
		var $inputTxt=$('#title');
		if($inputTxt.val()==''){
			$inputTxt.focus();
		}
	});
 
	function moveTab(){
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "/las/lawinformation/library.do";
		frm.method.value = "listLibrary";

		viewHiddenProgress(true) ;
		frm.submit();
	}

</script>

</head>
<body>

<!-- **************************** Form Setting **************************** -->
<form:form name="frm" method="post" autocomplete="off">
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />

<!-- key form -->
<input type="hidden" name="info_gbn" id="info_gbn" value="<c:out value='${command.info_gbn}'/>" />
<input type="hidden" name="seqno"    id="seqno"    value="<c:out value='${command.seqno}'/>" />

<!-- 나모 웹 에디터 관련 -->
<input type="hidden" name="body_mime" id="body_mime" value="" />
<!-- //**************************** Form Setting **************************** -->
<div id="wrap">	
	<div id="container">
		<!-- Location -->
		<div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0" alt="Home" /><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->
	
		<!-- content -->
		<div id="content">
			<!-- Title -->
			<div class="title">
				<h3><spring:message code="las.page.title.contract.foreign.Library" /></h3>
			</div>
			<!-- //title -->
			<!-- tab01 -->
			<ul class="tab_basic04">
				<li class="on"><a href="#"><spring:message code="las.page.field.lawinformation.tab.guideline"/></a></li>
				<li><a href="javascript:moveTab();"><spring:message code="las.page.field.lawinformation.tab.samplecont"/></a></li>
			</ul>
			<!-- 두줄라인 추가 --><div class="tal_topLine"></div><!-- //두줄라인 추가 -->
			<!--  view  -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
					<col width="15%" />
					<col width="85%" />
				</colgroup>
				<tbody>
					<tr>
						<th><spring:message code='las.page.field.contract.library.title' /></th>
						<td>
							<span><input type="text" name="title" id="title" class="text" size="50" maxLength="128" value="" alt="<spring:message code='las.page.field.board.notice.title' />" maxLength="128" required /></span>
						</td>
					</tr>
					<tr class="end">
						<th><spring:message code="clm.page.msg.common.content" /></th>
						<td>
							<%@ include file="/WEB-INF/jsp/secfw/common/NamoEditInclude.jspf"%>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- //view  -->
			<!-- Function Button  -->
			<div class="t_titBtn">
				<div class="btn_wrap">
					<span class="btn_all_b"><span class="save"></span><a href="javascript:save()"><spring:message code='las.page.button.save' /></a></span>
					<span class="btn_all_w"><span class="cancel"></span><a href="javascript:cancel()"><spring:message code='las.page.button.cancel' /></a></span>
				</div>
			</div>
		</div>
		<!-- //content -->			
		<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- // footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
</form:form>
</body>
<!-- 나모 웹에디터 관련 추가 -->
<SCRIPT language="javascript" FOR="wec" EVENT="OnInitCompleted()">
	var wecObj = document.wec;
	wecObj.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
	wecObj.SetDefaultFontSize("9");
	wecObj.EditMode = 1;
</SCRIPT>
</html>
