<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@page import="org.springframework.web.servlet.support.RequestContext"%>
<%@page import="com.sds.secframework.common.util.StringUtil" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%--
/**
 * 파    일    명 : IntroNotice.jsp
 * 프 로 그 램 명 : 법무시스템 등록 인트로
 * 작    성    자 : 홍성현
 * 작    성    일 : 2013.10
 * 설          명 : 
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
<head>
<meta charset="utf-8" />

<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="/script/clms/common.js" type="text/javascript"></script>
<script language="javascript">
$(document).ready(function(){
    
});

function goRequest(){
    
    var frm = document.frm;
    
    frm.access_yn.value="P";
	
	var options = {
        url: "<c:url value='/secfw/access.do?method=requestAccess' />",
// 		url: "<c:url value='/las/board/bbs.do?method=delCheck' />",
        type: "post",
        success: function(responseText,returnMessage) {
        	if(responseText>0){        		
        		alert('<spring:message code="las.msg.alert.requestAccess" />');
        		window.open('about:blank','_self');
       		    opener=window;
       		    window.close();
        	}
        }
    };
    $("#frm").ajaxSubmit(options);   
}

function goIt4U(){
    
    var frm = document.frm;
    
    frm.target = "_self";
	frm.action = "http://it4u.sec.samsung.net/itvoc/jsp/itvoclink/itvocLstDo.jsp?cmd=insertform&fromCmd=ListRequest&&sysCode=11P002410D0660";
	
	frm.submit();
	
// 	window.open('about:blank','_self');
//     opener=window;
//     window.close();
	
}


</script>
</head>
<body>
<form:form id="frm" name="frm" autocomplete="off">
	<input type="hidden" id="method" name="method"/>
	<input type="hidden" id="flag" name="flag"/>
	<input type="hidden" id="access_yn" name="access_yn" value="Y"/>
<div class="introWrap2"> 
		<!-----------------------------content----------------------------->
		<div class="intro_content2">
				<div class="article"><span class="iconBox icon010"></span><span class="col01"><spring:message code="las.main.intro.notice01" /><br>
						<span style="padding-left:20px"></span><spring:message code="las.main.intro.notice02" /></span> </div>
						<div class="intro_divider2"></div>
				<div class="tC"><span class="introBtn_gosys"><a id="request" href="javascript:goIt4U()"><spring:message code="las.main.intro.notice03" /></a></span></div>
				
		</div>
</div>
</form:form>
</body>
</html>