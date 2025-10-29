<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><spring:message code="clm.main.title" /></title>
<LINK href="<%=CSS%>/main.css"   type="text/css" rel="stylesheet">
<LINK href="<%=CSS%>/popup.css"   type="text/css" rel="stylesheet">
<LINK href="<%=CSS%>/common.css"   type="text/css" rel="stylesheet">
<LINK href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/clms/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="JavaScript" type="text/JavaScript" >

</script>
</head>
<body class="pop">
<!-- header -->
<h1>Quick Guide<span class="close" onclick="javascript:self.close();" title="close"></span></h1>
<!-- //header -->
<!-- body -->
<div class="pop_body">
    <div class="pop_area">
        <!-- Popup Detail -->
        <!-- Popup Size 710*400 -->
        <div>
        	<div class="pop_content">
			<!-- title -->
		  		<div class="title">
					<h4><spring:message code="clm.page.msg.manage.contConcl" /></h4>
		  		</div>                
             	<!-- //title -->
				<div class="image"><img src="<%=IMAGE%>/contents/quickguide02.jpg" alt="contents" /></div>
        	</div>
        	<!-- //Popup Detail -->
    	</div>
	</div>
</div>
<!-- //body -->
<!--footer -->
<div class="pop_footer">
	<!-- button -->
	 <div class="btn_wrap fR">
     	 <span class="btn_all_w"><span class="cancel"></span><a href="#" onclick="javascript:self.close();"><spring:message code="clm.page.msg.common.close" /></a></span>     	 
	</div>
	<!-- //button -->
</div>
<!-- //footer -->
</body>

</html>