<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap"%>
<%@ page import="com.sds.secframework.common.util.Token" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sec.common.clmscom.dto.CLMSCommonForm" %>
<%@ page import="model.outldap.samsung.net.Organization" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%--
/**
 * 파     일     명 	: DeptSearchList.jsp
 * 프로그램명 			: 부서선택 (팝업)
 * 설               명 	: 일부이름으로 부서명을 검색하여 선택하는 팝업
 * 작     성     자 	: 김형준
 * 작     성     일 	: 2011.09.16
 */
--%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet"/>  
<link href="<%=CSS%>/popup.css" type="text/css" rel="stylesheet"/>

<script language="javascript" src="/script/secfw/jquery/jquery.js"></script>
<script language="javascript" src="/script/secfw/jquery/ui.core.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.form.js"></script>
<script language="javascript" src="/script/secfw/common/common.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="/script/secfw/common/CommonProgress.js"></script>
<script language='javascript' src='/script/clms/event.js'></script>
<script language="javascript" src="/script/clms/clms_common.js"></script>
<%
Organization resultList[] = (Organization[])request.getAttribute("resultList");
CLMSCommonForm command = ((CLMSCommonForm)request.getAttribute("command") == null ? new CLMSCommonForm() : (CLMSCommonForm)request.getAttribute("command"));
%>
<script language="javascript">
<!--
	function retValue(retDept) {
		opener.setDeptInfo(retDept);
		window.close();
	}
//-->
</script>
</head>
<body class="pop">
<!-- header -->
<h1><spring:message code="common.page.field.DeptSearchList.serDept"/></h1>
<!-- //header -->
<div class="pop_area">
	
		<div class="pop_content">
		<!-- container -->	
					<!-- table01 -->
					<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
					  <thead>
						<tr>
						  <th><spring:message code="common.page.field.DeptSearchList.department"/></th>
					    </tr>
					  </thead>
					  <tbody>

<%
	if(resultList != null && resultList.length > 0) {
		for(int idx = 0; idx < resultList.length; idx++) {
			Organization org = (Organization)resultList[idx];
%>
						<tr>
							<td class="tL"><a href='javascript:retValue("<%=org.getOu() %>")'><%=org.getOu() %></a></td>
						</tr>
<%
		}
	} else {
%>
						<script>
						alert('<spring:message code="secfw.msg.succ.noResult" />');
						window.close();
						</script>
<%
	}
%>					  
						
					  </tbody>
					 </table>
					<!--// table01 -->
				<!-- //content -->
		</div>
	</div>

<!-- //body -->
<!--footer -->
<div class="pop_footer">
	<div class="btn_wrap tR">
	 <span class="btn_all_w"><span class="cancel"></span><a href="#" onclick="javascript:self.close();"><spring:message code="common.page.field.DeptSearchList.close"/></a></span>
	</div>
</div>
<!-- //footer -->
</body>

</html>