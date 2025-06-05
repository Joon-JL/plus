<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%
		List roleList = (List)session.getAttribute("secfw.session.role");
		String commonRolCd = "";
		if(roleList != null && roleList.size() > 0) {
			for(int ri = 0; ri < roleList.size(); ri++) {
				HashMap roleHm = (HashMap)roleList.get(ri);
				if("RA02".equals((String)roleHm.get("role_cd"))) {
					commonRolCd = "RA02";
				}
			}
		}
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%
	if(commonRolCd == "RA02"){
%>	
	<meta http-equiv="Refresh" content="300;url=/common/clmsCom.do?method=getAdminAlert">
<%	}  %>	
	<title></title>
</head>

<script language="javascript">
var cntrt_reqs_cnt 		= "<c:out value='${cntrt_reqs_cnt}'/>";
var cntrt_returns_cnt 	= "<c:out value='${cntrt_returns_cnt}'/>";
var cnslt_reqs_cnt 		= "<c:out value='${cnslt_reqs_cnt}'/>";
var cnslt_returns_cnt 	= "<c:out value='${cnslt_returns_cnt}'/>";

var str1 = "<spring:message code='common.page.field.AlertPopup.contNewAllc'/>"+cntrt_reqs_cnt+"<spring:message code='common.page.field.AlertPopup.case'/>"+", ";
var str2 = "<spring:message code='common.page.field.AlertPopup.reject'/>"+cntrt_returns_cnt+"<spring:message code='common.page.field.AlertPopup.case'/>"+"\n";
var str3 = "<spring:message code='common.page.field.AlertPopup.consultNewAllc'/>"+cnslt_reqs_cnt+"<spring:message code='common.page.field.AlertPopup.case'/>"+", ";
var str4 = "<spring:message code='common.page.field.AlertPopup.reject'/>"+cnslt_returns_cnt+"<spring:message code='common.page.field.AlertPopup.isCase'/>";

if(cntrt_reqs_cnt > 0 || cntrt_returns_cnt > 0  || cnslt_reqs_cnt > 0 || cnslt_returns_cnt > 0){
	// 프로퍼티 적용전 메세지 
	// alert("계약 신규 배정 "+cntrt_reqs_cnt+"건, 반려 "+cntrt_returns_cnt+"건,\n자문 신규 배정 "+cnslt_reqs_cnt+"건, 반려 "+cnslt_returns_cnt+"건이 있습니다.");
	
	// 프로퍼티 적용
	alert(str1+str2+str3+str4);
}
</script>
<body>
</body>
</html>