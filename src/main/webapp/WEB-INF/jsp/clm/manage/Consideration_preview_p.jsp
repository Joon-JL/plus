<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%--
/**
 * 파  일  명 : Consideration_preview_p.jsp
 * 프로그램명 : 검토의뢰 인쇄 화면
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2013.05
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title"/></title>
<link href="<%=CSS%>/main.css"   type="text/css" rel="stylesheet"></link>
<link href="<%=CSS%>/popup.css"   type="text/css" rel="stylesheet"></link>
<link href="<%=CSS%>/common.css"   type="text/css" rel="stylesheet"></link>
<link href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet"></link>
<link href="<c:url value='/style/secfw/approval.css'/>" rel='stylesheet' type='text/css'></link>

<script type="text/javascript"  src="<c:url value='/script/clms/clms_common.js' />"></script>
<script type="text/javascript"  src="<c:url value='/script/secfw/common/common.js' />"></script>
<script type="text/javascript"  src="<c:url value='/script/secfw/common/CommonProgress.js' />"></script>
<script type="text/javascript"  src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script type="text/javascript"  src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script type="text/javascript"  src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script type="text/javascript" src="/crosseditor/js/namo_scripteditor.js"></script>

<script type="text/javascript">

function setResize() {
	document.getElementById("wec").style.height = "700px";
}
</script>

</head>

<body class="pop" onload="javascript:setResize();">
<form:form name="frm" id="frm" method="post" autocomplete="off">
	<input type="hidden" name="method" id="method" value=""/>
	<input type="hidden" name="menu_id" id="menu_id" value="<c:out value='${command.menu_id}'/>" />
    <!-- 계약추가 증가데이타 -->	    
   
<!-- header -->
<h1><spring:message code="clm.page.msg.manage.previewRevReq" /></h1>
<!-- //header -->
<!-- body -->
    <div class="pop_area">
        <!-- Popup Detail -->
        <!-- Popup Size 840*600 -->
    	<div>
        	<div class="pop_content mt10">
        	<table cellspacing="0" id='tbl' cellpadding="0" width="100%">
        		<tr>
					<td class='con2'>
          				 
          				<input type="hidden" name="body_mime" id="body_mime" value="<c:out value='${content}' />" />		
		  				<script type="text/javascript" type="text/javascript">
							
						    var CrossEditor = new NamoSE('wec');
						    CrossEditor.params.Height = "700";	
							CrossEditor.params.Width = "1014";
        					CrossEditor.params.UserLang = "enu";
        					CrossEditor.params.FullScreen = false;
        					CrossEditor.ShowTab(false);
        					CrossEditor.ShowToolbar(0, 1);
        					CrossEditor.ShowToolbar(1, 0);
        					CrossEditor.ShowToolbar(2, 0);
        					CrossEditor.UserToolbar = true;
        					CrossEditor.params.CreateToolbar = "print";
        					CrossEditor.EditorStart();
        					function OnInitCompleted(e){
        						CrossEditor.SetValue(document.frm.body_mime.value);
        					}
	        					
                        </script>
		  			</td>
		  		</tr>
		  	</table>	
		  </div>
		</div>
		<div class="btn_wrap fR">
     		<span class="btn_all_w"><span class="close"></span><a href="#" onclick="javascript:self.close();"><spring:message code="clm.page.msg.common.close" /></a></span>
	 	</div>
	</div>	 
</form:form>	

</body>
</html>
