<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@page import="com.sds.secframework.common.util.Token"%>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.sec.clm.share.dto.LawTermsForm" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%@ include file= "/WEB-INF/jsp/common/common.jsp" %>

<%--
/**
 * 파  일  명 : LawTerms_u.jsp
 * 프로그램명 : 계약용어집 수정
 * 설      명 : 
 * 작  성  자 :  
 * 작  성  일 : 2011.08
 */
 --%>
<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	LawTermsForm command = (LawTermsForm)request.getAttribute("command");   
%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/clm.css" type="text/css" rel="stylesheet" />
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/util/fckeditor/fckeditor.js' />"></script>
<script language="javascript">


/**
* 버튼 동작 부분
*/
function pageAction(flag){
	var frm = document.frm;
	if(flag == "modify"){
		//나모웹에디터 관련
	  	frm.body_mime.value = frm.wec.MIMEValue;
		frm.method.value = "modifyLawTerms" ;
		frm.action = "<c:url value='/clm/share/LawTerms.do' />";
		frm.target = "_self" ;
		frm.submit() ;  
	
    }else if(flag == "cancel"){
		
		if (confirm("<spring:message code='secfw.msg.ask.cancel' />")){	

			frm.target = "_self";
			frm.action = "<c:url value='/clm/share/LawTerms.do' />";
		    frm.method.value = "listLawTerms";

			viewHiddenProgress(true) ;
			frm.submit();
		}
	}
}
	

	/**
	* 메시지 처리
	*/
	function showMessage(msg){
		if(msg != null && msg.length > 1) {
			alert(msg);
		}
	}
	

//-->
</script>
  
</head>
<body>
<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id="frm" method="post" autocomplete="off">
<!-- srch form -->
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />

<input type="hidden" name="seqno"    id="seqno"    value="<c:out value='${command.seqno}'/>" />

<!-- 나모 웹 에디터 관련 -->
<input type="hidden" name="body_mime" id="body_mime" value="<c:out value='${lom.terms_expl}' />" />
<!-- // **************************** Form Setting **************************** -->

<div id="wrap">
	

	<!-- container -->
	<div id="container">
	<!-- Location -->
	<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
	<!-- //Location -->	
		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.title.lawterms.listTitle" /></h3>
		</div>
		<!-- //title -->
		<!-- content -->
		<div id="content">
			<!-- view -->
			<table  cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
					<col class="tal_w04" />
					<col />
					<col class="tal_w04" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th><spring:message code="clm.page.field.lawterms.terms" /><span class="astro">*</span></th>
						<td colspan="3">            
							<input type="text" name="terms_nm" id="terms_nm"   value="<c:out value='${lom.terms_nm}'/>"  maxLength="128" class="text_full" style="width:300px" />
						</td>
					
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.lawterms.terms_ab" /><span class="astro">*</span></th>
						<td colspan="3">           
							<input type="text" name="terms_abbr_nm" id="terms_abbr_nm" value="<c:out value='${lom.terms_abbr_nm}' />" maxLength="128" class="text_full" style="width:300px" />
						</td>
					
						
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.lawterms.expl" /><span class="astro">*</span></th>
						<td  colspan="3">
							<%@ include file="/WEB-INF/jsp/secfw/common/NamoEditInclude.jspf"%>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- //view -->
	
			<div class="btn_wrap">
		<%if(command.isAuth_modify()){ %>
				<span class="btn_all_b"><span class="save"></span><a href="javascript:pageAction('modify');"><spring:message code="secfw.page.button.insert" /></a></span>
		<%} %>
				<span class="btn_all_w"><span class="cancel"></span><a href="javascript:pageAction('cancel');"><spring:message code="secfw.page.button.cancel" /></a></span>
			</div>
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
<!-- 나모 웹에디터 관련 추가 -->   
<SCRIPT language="javascript" FOR="wec" EVENT="OnInitCompleted()">
	var wecObj = document.wec;
	wecObj.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
	wecObj.SetDefaultFontSize("9");
	wecObj.EditMode = 1;
	wecObj.Value = document.frm.body_mime.value;
</SCRIPT>
</html>
