<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%--
/**
 * 파     일     명 : FileDownLoadAlert.jsp
 * 프로그램명 : 첨부파일 다운로드 경고페이지
 * 설               명 : 
 * 작     성     자 : 금현서
 * 작     성     일 : 2010.08
 */
--%>

<%
%>

<html xmlns='http://www.w3.org/1999/xhtml'>
<head>
<title>File DownLoad Alert</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<c:url value='/style/secfw/pop.css' />" rel='stylesheet' type='text/css'>
<BASE target="_self"> 
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript">
<!--

/**
* - 페이지 초기화 
*/
$(document).ready(function(){

	var confirmValue = "F";
	
	// 취소버튼 클릭시
	$('#closeBtn').click(function(){

		confirmValue = "F";
		window.returnValue = confirmValue;
		
		window.close();
	});

	// 확인버튼 클릭시
	$('#agreeBtn').click(function(){

		confirmValue = "T";
		window.returnValue = confirmValue;
		window.close();
	});

	// 확인버튼 클릭시
	$('#agreeCheck').click(function(){

		if($(this).attr('checked')) {
			$('#agreeBtn').attr('disabled', false);
		} else {
			$('#agreeBtn').attr('disabled', 'disabled');
		}
	});

	$('#agreeBtn').attr('disabled', 'disabled');
});

//-->
</script>
</head>
<body>
<form:form id="frm" name="frm" method="post" autocomplete="off">
<table width='100%' height='100%' align="center" cellpadding='0' cellspacing='0'>
	<tr>
		<td valign="top">			
			<div id='agree'>
				<div id='top'></div>
				<div id='mid'>
					<div id='box'>
						<div class='lf'>
							<spring:message code="System.page.msg.fileDown.alert" />
						</div>
						<div class='ri'><input type='checkbox' id="agreeCheck"> <spring:message code="secfw.page.button.agree" /></div>
					</div>
				</div>
				<div id='btm'>
					<div id="closeBtn" class='btn'>
						<div class='head_close'></div>
						<div class='cen'><spring:message code="secfw.page.button.close" /></div>
						<div class='tail'></div>
					</div>
					<div id="agreeBtn" class='btn'>
						<div class='head_accept'></div>
						<div class='cen'><spring:message code="secfw.page.button.accept" /></div>
						<div class='tail'></div>
					</div>
				</div>
			</div>
		</td>
	</tr>
</table>
</form:form>
</body>
</html>
