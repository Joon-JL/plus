<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.control.CommonController" %>
<%@ page import="com.sds.secframework.user.dto.UserSortingManagerForm" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@page  import="java.util.Locale"%>
<%@ page import="java.util.List" %>
<%@page  import="java.net.URLEncoder" %>
<%@page  import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@page  import="org.springframework.web.servlet.support.RequestContext"%>
<%--
/**
 * 파     일     명 : UserSortingMngList.jsp
 * 프로그램명 		: 변호사관리 목록
 * 설            명 : 변호사들의 정렬 순서를 정한다.
 * 작     성     자 : 김형준
 * 작     성     일 : 2011.11.10
 */
--%>
<%

    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("menuNavi");

	UserSortingManagerForm command  = ((UserSortingManagerForm)request.getAttribute("command") == null ? new UserSortingManagerForm() : (UserSortingManagerForm)request.getAttribute("command"));
	
    //사용자 조회목록 
    List listSortingUser = (List)request.getAttribute("listSortingUser");
    String returnMessage	= StringUtil.bvl((String)request.getAttribute("returnMessage"), "");
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="las.main.title" /></title>
<LINK href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/shri/shri.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/ui.core.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.dynatree.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.tablednd_0_5.js' />"></script>
<script language="javascript">

function init(){
	$("#CodeTableInfo tbody").tableDnD();
}

function pageAction(div){

	if(div == "search"){
		
		var frm = document.frm;
		frm.target		 = "_self";
		frm.action       = "<c:url value='/secfw/userSortingMng.do' />";
		frm.method.value = "listUserSortingMng";
		frm.submit();
		frm.target = "_self";
		
	}else if(div == "save"){
		var frm = document.frm;
		frm.target		 = "_self";
		frm.action       = "<c:url value='/secfw/userSortingMng.do' />";
		frm.method.value = "saveUserSortingMng";
		frm.submit();
		frm.target = "_self";
	}
}	

function showMessage() {
<%
	if(!returnMessage.equals("")) {
%>
		alert("<%=returnMessage%>");
<%
	}
%>
}

</script>

</head>
<body onload="init();showMessage();">
<div id="wrap">
	<div id="container">

		<!-- Location -->
		<div class="location"><IMG SRC="<%=IMAGE%>/icon/ico_home.gif" BORDER="0" alt="Home" title="Home"><%=menuNavi %></div>
		<!-- Title -->
		<div class="title">
			<h3><spring:message code="secfw.page.field.user.lwManage"/></h3>
		</div>
		
		<div id="content">
		<div id="content_in">
		<form:form id="frm" name="frm" method="post" autocomplete="off">
		<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>">
		<input type="hidden" name="method"  value="">
		
		
		<div class='tR'>
			<span class="ico_alert02"><spring:message code="secfw.page.field.user.dragLinetoSort"/></span>
		</div>
		
		<div class='tableWrap'>
		<div class='tableone'>
			<table class="list_basic" id="CodeTableInfo" >
			<colgroup>
				<col width="10%"/>
				<col width="40%"/>
				<col width="25%"/>
				<col width="25%"/>
			</colgroup>
			<tr id="UserListTitle">
				<th><spring:message code="secfw.page.field.user.sequence"/></th>
				<th><spring:message code="secfw.page.field.user.nm"/></th>
				<th>SingleID</th>
				<th><spring:message code="secfw.page.field.user.mail"/></th>
			</tr> 			
		</table>
		</div>
		</div>
		
 		<style>
			.h_120 {height:120px;}
			*html .h_120 {height:120px;}
		</style>
		<div class='tabletwo h_120'>
		<table class="list_scr" id="CodeTableInfo" >
			<colgroup>
				<col width="10%"/>
				<col width="40%"/>
				<col width="25%"/>
				<col width="25%"/>
			</colgroup>
			
			<%
			 for(int idx=0;idx< listSortingUser.size();idx++){
			    	ListOrderedMap lom = (ListOrderedMap)listSortingUser.get(idx);
			%>
			
			<tr>
				<td class='tC'> <%=idx+1%></td>
				<td class='overflow'><%=lom.get("user_info")%></td>			
				<td class='tC'><%=lom.get("single_id")%></td>			
				<td class='tC'><%=lom.get("email")%><input type="hidden" id="user_id_arr" name="user_id_arr" value="<%=lom.get("user_id")%>">		</td>	
				
			</tr>			
			<% 
			 }
			if(listSortingUser==null||listSortingUser.size()==0){
			%>
	          <tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
	            <td colspan="4" align="center"><spring:message code="secfw.msg.succ.noResult" /></td>
	          </tr>
			<%} %>
			
			</table>         
       </div>
       <div class="total_num">Total : <%=listSortingUser.size()%></div>	
       
       <div class='btnwrap mt_m12'>
       		<span class="btn_all_w" onclick="javascript:pageAction('save')"><span class="save_s2"></span><a><spring:message code="secfw.page.field.user.save"/></a></span>
	   </div>
		
		
			</form:form>
			</div>
		</div>
	</div>
</div>
<!-- footer -->
<script language="JavaScript" src="/script/clms/footer.js"></script>
<!-- //footer -->
<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</body>
</html>