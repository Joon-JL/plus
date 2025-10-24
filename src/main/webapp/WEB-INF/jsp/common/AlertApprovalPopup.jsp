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
				if("RD01".equals((String)roleHm.get("role_cd"))) {
					commonRolCd = "RD01";
				}
			}
		}
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%
	if(commonRolCd == "RD01"){
%>	
	<meta http-equiv="Refresh" content="3600;url=/common/clmsCom.do?method=getApprovalCnt">
<%	}  %>	
	<title></title>
</head>

<script language="javascript">
var cntrt_cnt_1 		= "<c:out value='${cntrt_cnt_1}'/>";
var cntrt_cnt_2 		= "<c:out value='${cntrt_cnt_2}'/>";
var cntrt_cnt_3 		= "<c:out value='${cntrt_cnt_3}'/>";
var cntrt_cnt_4 		= "<c:out value='${cntrt_cnt_4}'/>";

if(cntrt_cnt_1 > 0 || cntrt_cnt_2 > 0 || cntrt_cnt_4 > 0){
	/* 프로퍼티 적용전 메세지 */ // alert("원본접수 "+cntrt_cnt_1+"건,\n자동연장 승인 "+cntrt_cnt_2+"건,\n표준계약서 승인 "+cntrt_cnt_4+"건이 있습니다.");
	alert("<spring:message code='common.page.field.AlertApprovalPopup.allmsg' arguments='${cntrt_cnt_1},${cntrt_cnt_2},${cntrt_cnt_4}' htmlEscape='false'/>");
}
</script>
<body>
</body>
</html>