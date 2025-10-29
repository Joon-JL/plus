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
 * 파  일  명 : Consideration_preview_p.jsp
 * 프로그램명 : 검토의뢰품의 인쇄 화면
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2013.04
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="clm.main.title"/>-123</title>
<link href="<%=CSS%>/popup.css"   type="text/css" rel="stylesheet"></link>
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet"></link>

<script type="text/javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/common/CommonProgress.js' />"></script>

<script type="text/javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script type="text/javascript" src="/crosseditor/js/namo_scripteditor.js"></script>

</head>
<body class="pop" onload="javascript:setResize();">

    <!-- 계약추가 증가데이타 -->	    
   
<!-- header -->
<h1><spring:message code="clm.page.msg.manage.prevContTerm" /></h1>
<!-- //header -->
<!-- body -->
    <div class="pop_area">
        	<div class="pop_content">
        	<form:form name="frm" id="frm" method="post" autocomplete="off">
	             <input type="hidden" name="method" id="method" value=""/>
	             <input type="hidden" name="menu_id" id="menu_id" value="<c:out value='${command.menu_id}'/>" />
	
        				<input type="hidden" name="body_mime" id="body_mime" value="<c:out value='${content}' />" />		
		  				<script type="text/javascript" language="javascript">
							
							    var CrossEditor = new NamoSE('wec');
							    CrossEditor.params.Height = "700";	
								CrossEditor.params.Width = "1014";
	        					CrossEditor.params.UserLang = "enu";
	        					CrossEditor.params.FullScreen = false;
	        					CrossEditor.ShowTab(false);
	        					CrossEditor.UserToolbar = true;
	        					CrossEditor.ShowToolbar(0, 1);
	        					CrossEditor.ShowToolbar(1, 0);
	        					CrossEditor.ShowToolbar(2, 0);
	        					CrossEditor.params.CreateToolbar = "print";
	        					CrossEditor.EditorStart();
	        					function OnInitCompleted(e){
	        						CrossEditor.SetValue(document.frm.body_mime.value);
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
