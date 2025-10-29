<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page import="com.sds.secframework.user.dto.UserForm" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ include file="/WEB-INF/jsp/secfw/common/SessionCheck.jsp" %> 
<%--
/**
 * 파     일     명 : UserListByDept.jsp
 * 프로그램명 : 클릭한 부서의 사용자 리스트 아이프레임
 * 설               명 : 
 * 작     성     자 : 김형석
 * 작     성     일 : 2009.11
 */
--%>
<%
	
	String findUser = (String)StringUtil.nvl(request.getAttribute("findUser"),"");
	ArrayList listUser = (ArrayList)request.getAttribute("listUser");
	//PageUtil 객체를 읽어들임
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	UserForm command = (UserForm)request.getAttribute("command");

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>DeptTreePopup</title>
<LINK href="<c:url value='/style/secfw/popup.css' />" type="text/css" rel="stylesheet">
<script language="javascript">

	var popDeptTree = null;
	var xmlDoc = null;
	
	/* 트리 */
	function show_tree() {


	}
		
	function clickNode(value, text) {
		//opener.setDept(value,text);
		alert(value + ":" + text);
	}

	

	function searchEmployee()
	{
		var frm = document.frm;
		if(frm.userDeptCheck[0].checked){
	    	frm.userDept.value = frm.userDeptCheck[0].value;
		}else{
			frm.userDept.value = frm.userDeptCheck[1].value;
		}
	    
		frm.target = "_self";
		
		frm.action = "<c:url value='/secfw/user.do' />";
		frm.method.value = "listUserDept";
	
		frm.submit();
	}

	function includeCheck()
	{
		var frm = document.frm;
		frm.includeChk.value = includeChk.checked;
		frm.action = "/WEB-INF/jsp/secfw/search/DeptUserMain.jsp";
		frm.submit();
	}

	function checkAll() {

		var frm = document.frm;
		var is_check = frm.allCheck.checked? true : false;

		if(frm.chk.length == undefined){
			frm.chk.checked= is_check;
		}

		if(frm.chk.length > 1){
			for(var i=0;  i< frm.chk.length ;i++){
				frm.chk[i].checked = is_check;
			}
		}

	}

	function selectEmp(){ 

		var frm = document.frm;

		var listSize = <%=listUser.size()%>;
		
		if(listSize == 1){
			var user_nm = frm.user_nm0.value;
    		var dept_nm = frm.dept_nm0.value;
    		var emp_no = frm.emp_no0.value;
    		var dept_cd = frm.dept_cd0.value;
    		var division_cd = frm.division_cd0.value;
    		var division_nm = frm.division_nm0.value;
    		var jikgup_cd = frm.jikgup_cd0.value;
    		var jikgup_nm = frm.jikgup_nm0.value;
    		var birth_ymd = frm.birth_ymd0.value;

    		parent.opener.add_tr_common_multi("100", (user_nm == null) ? "." : user_nm
	                , (dept_nm == "null" || dept_nm == "") ? "." : dept_nm
	                , (emp_no == "null" || emp_no == "") ? "." : emp_no
	                , (dept_cd == "null" || dept_cd == "") ? "." : dept_cd
	                , (division_cd == "null" || division_cd == "") ? "." : division_cd
	                , (division_nm == "null" || division_nm == "") ? "." : division_nm
	                , (jikgup_cd == "null" || jikgup_cd == "") ? "." : jikgup_cd
	                , (jikgup_nm == "null" || jikgup_nm == "") ? "." : jikgup_nm
	                , (birth_ymd == "null" || birth_ymd == "") ? "." : birth_ymd);
		}else{
		    for(var idx=0; idx < <%=listUser.size()%> ;idx++){
		    	if(frm.chk[idx].checked == true){
	
		    		var user_nm = eval("frm.user_nm"+idx+".value");
		    		var dept_nm = eval("frm.dept_nm"+idx+".value");
		    		var emp_no = eval("frm.emp_no"+idx+".value");
		    		var dept_cd = eval("frm.dept_cd"+idx+".value");
		    		var division_cd = eval("frm.division_cd"+idx+".value");
		    		var division_nm = eval("frm.division_nm"+idx+".value");
		    		var jikgup_cd = eval("frm.jikgup_cd"+idx+".value");
		    		var jikgup_nm = eval("frm.jikgup_nm"+idx+".value");
		    		var birth_ymd = eval("frm.birth_ymd"+idx+".value");
	
		    		parent.opener.add_tr_common_multi("100", (user_nm == null) ? "." : user_nm
			                , (dept_nm == "null" || dept_nm == "") ? "." : dept_nm
			                , (emp_no == "null" || emp_no == "") ? "." : emp_no
			                , (dept_cd == "null" || dept_cd == "") ? "." : dept_cd
			                , (division_cd == "null" || division_cd == "") ? "." : division_cd
			                , (division_nm == "null" || division_nm == "") ? "." : division_nm
			                , (jikgup_cd == "null" || jikgup_cd == "") ? "." : jikgup_cd
			                , (jikgup_nm == "null" || jikgup_nm == "") ? "." : jikgup_nm
			                , (birth_ymd == "null" || birth_ymd == "") ? "." : birth_ymd);
			    	
		    	}
		    }
		}

	    parent.window.close();
	} 


	function searchUser() {
		
		var frm = document.frm;

		frm.target = "_self";
			
		frm.action = "<c:url value='/secfw/user.do' />";
		frm.method.value = "searchDeptList";
		
		frm.submit();
		//opener.setDept(value,text);
	}

</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow-x:hidden;overflow-y:hidden">
<form:form name="frm" method="post" autocomplete="off">
<input type="hidden" name="userDept" value="" />
<input type="hidden" name="method" value="" />
	<h5><spring:message code="secfw.page.field.search.userList"/></h5>
	<br>
	<!-- Search -->
		<div class="gray_top_box">
		<div class="gray_top_box_in">
		<select name="search_combo" class="select" >
			<option value="dept_nm" <%="user_nm".equals(command.getSearch_combo()) ? "selected" : "" %>><spring:message code="secfw.page.field.search.department"/> </option>
			<option value="user_nm" <%="user_nm".equals(command.getSearch_combo()) ? "selected" : "" %>><spring:message code="secfw.page.field.search.name"/> </option>
			<option value="single_id" <%="single_id".equals(command.getSearch_combo()) ? "selected" : "" %>><spring:message code="secfw.page.field.search.singleId"/></option>
			<option value="mobile_no" <%="mobile_no".equals(command.getSearch_combo()) ? "selected" : "" %>><spring:message code="secfw.page.field.search.phoneNo"/></option>
		</select>
		<input type="text" name="search_name" class="text" value="${command.search_name}" OnKeyDown="if(event.keyCode==13) { searchUser(); }" />
		<a href="#" onclick="javascript:searchUser();" style="cursor:hand;" class="bt_search_s"><span><spring:message code="secfw.page.field.search.search2"/></span></a> 
		</div>
		</div>
	<!-- //Search -->
	<!-- Total -->
	<div class="total">
		<p>Total : 
		 <strong><%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%></strong>
		 </p>
	</div>
	<!-- List -->
	<table class="basic_list mz fix">
		<colgroup>
			<col width="30" align="center"/>
			<col width="60" align="center"/>
			<col width="70" align="center"/>
			<col width="60" align="center"/>
			<col width="60" align="center"/>
			<col width="60" align="center"/>
			<col width="60" align="center"/>
			<col align="center"/>
			<col/>
			</colgroup>
			<tr>
				<th><input type="checkbox" name="allCheck" class="checkbox" onClick="javascript:checkAll();"></th>
				<th><spring:message code="secfw.page.field.search.nm"/></th>
				<th>ID</th>
				<th><spring:message code="secfw.page.field.search.company"/></th>
				<th><spring:message code="secfw.page.field.search.department"/></th>
				<th><spring:message code="secfw.page.field.search.position"/></th>
				<th><spring:message code="secfw.page.field.search.comPhone"/></th>
				<th class="end"><spring:message code="secfw.page.field.search.cellphone"/></th>
				<th class="th_scroll"></th>
			</tr>
	</table>
	<div class="dscrolly_mb" style="height:350px">
		<table class="basic_list tb_non fix">
				<colgroup>
					<col width="30" align="center"/>
					<col width="60" align="center"/>
					<col width="70" align="center"/>
					<col width="60" align="center"/>
					<col width="60" align="center"/>
					<col width="60" align="center"/>
					<col width="60" align="center"/>
					<col align="center"/>									
				</colgroup>
	<%
 		int idx = 0;
			for(idx=0;idx < listUser.size();idx++){
		    
		    	ListOrderedMap lom = (ListOrderedMap)listUser.get(idx);
		    	
	%> 
			<tr>
				<td><input type="checkbox" name="chk" value="<%=lom.get("user_id") %>" class="checkbox"></td>				
				<td><%=lom.get("user_nm") %></td>
				<td title="<%=lom.get("email")%>"><div style='width:50;overflow:hidden;text-overflow:ellipsis'><nobr><%=lom.get("email") %></nobr></div></td>
				<td title="<%=lom.get("comp_nm")%>"><div style='width:50;overflow:hidden;text-overflow:ellipsis'><nobr><%=lom.get("comp_nm") %></nobr></div></td>
				<td title="<%=lom.get("dept_nm")%>"><div style='width:50;overflow:hidden;text-overflow:ellipsis'><nobr><%=lom.get("dept_nm") %></nobr></div></td>
				<td><%=lom.get("jikgup_nm") %></td>
				<td><%=lom.get("office_tel_no") %></td>
				<td><%=lom.get("mobile_no") %></td>
				
				<input type=hidden name="user_nm<%=idx%>" value='<%=lom.get("user_nm") %>'>
				<input type=hidden name="dept_nm<%=idx%>" value='<%=lom.get("dept_nm")%>'>
				<input type=hidden name="emp_no<%=idx%>" value='<%=lom.get("emp_no")%>'>
				<input type=hidden name="dept_cd<%=idx%>" value='<%=lom.get("dept_cd")%>'>
				<input type=hidden name="division_cd<%=idx%>" value='<%=lom.get("division_cd")%>'>
				<input type=hidden name="division_nm<%=idx%>" value='<%=lom.get("division_nm")%>'>
				<input type=hidden name="jikgup_cd<%=idx%>" value='<%=lom.get("jikgup_cd")%>'>
				<input type=hidden name="jikgup_nm<%=idx%>" value='<%=lom.get("jikgup_nm")%>'>
				<input type=hidden name="birth_ymd<%=idx%>" value='<%=lom.get("birth_ymd")%>'>
			</tr>
	<%
                
	    	}
	%>
		</table>
	</div>

<!-- Popup Footer -->

<!-- //Popup Footer -->
</form:form>
</body>
</html>

