<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%--
/**
 * 파  일  명 : Consultation_preview_p.jsp
 * 프로그램명 : 체결품의 미리보기
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2013.05
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="clm.main.title"/></title>
<link href="<%=CSS%>/popup.css"   type="text/css" rel="stylesheet" />
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet" />
<link href="<c:url value='/style/secfw/approval.css'/>" rel='stylesheet' type='text/css' />

<script type="text/javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/common/CommonProgress.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script type="text/javascript" src="/crosseditor/js/namo_scripteditor.js"></script>
<script type="text/javascript">

function setResize() {
	document.getElementById("wec").style.height = "700px";
}
</script>

</head>
<body class="pop" onload="javascript:setResize();">


<!-- body -->
    <div class="pop_area">
        	<div class="pop_content">
        	<form:form name="frm" id="frm" method="post" autocomplete="off">
	            <input type="hidden" name="method" id="method" value=""/>
	            <input type="hidden" name="menu_id" id="menu_id" value="<c:out value='${command.menu_id}'/>" />
          				<input type="hidden" name="body_mime" id="body_mime" value="<c:out value='${content}' />" />		
		  				<script type="text/javascript" type="text/javascript">							
		  			  		var CrossEditor = new NamoSE('wec');
		  		      		CrossEditor.params.Height = "700";	
		  					CrossEditor.params.Width = "1014";
				  	        CrossEditor.params.UserLang = "enu";
				  	        CrossEditor.params.FullScreen = false;		  	        					
				  	        CrossEditor.params.Css = "<%=CSS%>/las.css";		  	        					
				  	        CrossEditor.UserToolbar = true;
				  	        CrossEditor.params.CreateToolbar = "print";		  	        					
				  	        CrossEditor.EditorStart();		  	        					
		  	        					
				  	        function OnInitCompleted(e){		  	        						
				  	            CrossEditor.ShowTab(false);		  	        						
				  	        	CrossEditor.SetValue(document.frm.body_mime.value);		  	        						
				  	        	CrossEditor.SetActiveTab(2);
				  	        		
				  	        }
                        </script>
            </form:form>
		    </div>
    </div>
	
	<div class="pop_footer">
		<div class="btn_wrap tR" >
			<span class="btn_all_w mR5" onclick="javascript:self.close();"><span class="cancel"></span><a href="#"><spring:message code="clm.page.msg.common.close" /></a></span>
		</div>
	</div>
	

</body>
</html>
