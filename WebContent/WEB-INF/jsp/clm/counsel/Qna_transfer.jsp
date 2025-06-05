<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%--
/**
 * 파  일  명 : Qna_i.jsp
 * 프로그램명 : QNA 이관
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
 --%>
<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
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
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript">

	$(document).ready(function(){

	});
	
	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		var frm = document.frm;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/counsel/qna.do'/>";
		if(flag == "transfer"){
			if(confirm("<spring:message code='secfw.msg.ask.applyTrans' />")){
				frm.method.value = "transferQna";
				if(validateForm(frm)){
					viewHiddenProgress(true) ;
					frm.submit();

			    }
			}
		}else if(flag == "cancel"){
			if(confirm("<spring:message code='secfw.msg.ask.cancel' />")){
			    frm.method.value = "listQna";
			    viewHiddenProgress(true);
				frm.submit();			
			}
		}
	}

	
</script>

</head>
<body onload="alertMessage('<c:out value='${command.return_message}'/>')" autocomplete="off">
<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id="frm" method="post" autocomplete="off">
<!-- search form -->
<input type="hidden" name="method"       value="" />
<input type="hidden" name="menu_id"      value="<c:out value='${command.menu_id}'/>" />
<!-- key form-->
<input type="hidden" name="seqno"	     value="<c:out value='${command.seqno}'/>" />
<input type="hidden" name="up_seqno"     value="<c:out value='${command.up_seqno}'/>" />
<input type="hidden" name="postup_srt"   value="<c:out value='${command.postup_srt}'/>" />
<input type="hidden" name="postup_depth" value="<c:out value='${command.postup_depth}'/>" />
<input type="hidden" name="cont"	     value="" />

<!-- 첨부파일정보 -->
<input type="hidden" name="fileInfos" 	value="" />
<input type="hidden" name="file_bigclsfcn" 	value="F009" />
<input type="hidden" name="file_midclsfcn" 	value="" />
<input type="hidden" name="file_smlclsfcn" 	value="" />
<input type="hidden" name="ref_key" 	value="<c:out value='${command.seqno}'/>" />
<input type="hidden" name="view_gbn" 	value="upload" />

<!-- // **************************** Form Setting **************************** -->
<div id="wrap">
	<!-- container -->
	<div id="container">	
	    <!-- Location -->
		<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->
		<!-- title -->
		<div class="title">
	        <h3><spring:message code="clm.page.title.qna.transferTitle" /></h3>
		</div>
		<!-- //title -->
		<!-- content -->
		<div id="content">
			<!-- view -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
					<col class="tal_w04" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th><spring:message code="clm.page.field.qna.transfer" /><span class="astro">*</span></th>
						<td>
							<textarea name="trans_demnd_cont" id="trans_demnd_cont" rows="16" class="text_area all" maxLength="1000" alt="<spring:message code="clm.page.msg.admin.rsnOfTrans" htmlEscape="true" />"></textarea>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- //view -->
	
			<div class="btn_wrap">
				<span class="btn_all_b"><span class="save"></span><a href="javascript:pageAction('transfer');"><spring:message code="las.page.button.lawsuit.transfer" /></a></span>
				<span class="btn_all_w"><span class="cancel"></span><a href="javascript:pageAction('cancel');"><spring:message code="secfw.page.button.cancel" /></a></span>
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
</html>
