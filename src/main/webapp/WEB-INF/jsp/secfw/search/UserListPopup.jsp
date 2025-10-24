<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.user.dto.UserForm" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ include file="/WEB-INF/jsp/secfw/common/SessionCheck.jsp" %> 
<%@ page import="com.sds.secframework.common.util.PageUtil" %>

<%--
/**
 * Subject   : UserListPopup.jsp
 * Author    : 금현서
 * Create On : 2009.11
 * 
 */
--%>
<%

	ArrayList listUser = (ArrayList)request.getAttribute("listUser");
	UserForm command = (UserForm)request.getAttribute("command");
	
	int listSize = 0;
	
	if(listUser != null && listUser.size()>0) {
		listSize = listUser.size();
	}

%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="secfw.page.field.search.empPopup"/></title>

<LINK href="<c:url value='/style/secfw/popup.css' />" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript">
<!--
	$(function() {
		if(<%=listSize%> == 1){
			setUserInfo(0);
			window.close();
		}
	});

	function listUser()
	{
		var frm = document.frm;
	    
		frm.target = "_self";
		frm.flag.value = "<%=command.getFlag()%>";
	
		frm.action = "<c:url value='/secfw/user.do' />";
		frm.method.value = "listUser";
	
		frm.submit();
	}

	function setUserInfo(index) {

		var userInfo = new Object();

		userInfo.user_id           = $("#user_id_" + index).val();
		userInfo.emp_no            = $("#emp_no_" + index).val();
		userInfo.user_nm           = $("#user_nm_" + index).val();
		userInfo.user_nm_eng       = $("#user_nm_eng_" + index).val();
		userInfo.chinese_nm        = $("#chinese_nm_" + index).val();
		userInfo.single_id         = $("#single_id_" + index).val();
		userInfo.single_epid       = $("#single_epid_" + index).val();
		userInfo.comp_cd           = $("#comp_cd_" + index).val();
		userInfo.comp_nm           = $("#comp_nm_" + index).val();
		userInfo.comp_nm_eng       = $("#comp_nm_eng_" + index).val();
		userInfo.business_cd       = $("#business_cd_" + index).val();
		userInfo.business_nm       = $("#business_nm_" + index).val();
		userInfo.business_nm_eng   = $("#business_nm_eng_" + index).val();
		userInfo.dept_cd           = $("#dept_cd_" + index).val();
		userInfo.dept_nm           = $("#dept_nm_" + index).val();
		userInfo.origin_dept_cd    = $("#origin_dept_cd_" + index).val();
		userInfo.origin_dept_nm    = $("#origin_dept_nm_" + index).val();
		userInfo.jikgun_cd         = $("#jikgun_cd_" + index).val();
		userInfo.jikgun_nm         = $("#jikgun_nm_" + index).val();
		userInfo.jikgun_nm_eng     = $("#jikgun_nm_eng_" + index).val();
		userInfo.jikgup_cd         = $("#jikgup_cd_" + index).val();
		userInfo.jikgup_nm         = $("#jikgup_nm_" + index).val();
		userInfo.jikgup_nm_eng     = $("#jikgup_nm_eng_" + index).val();
		userInfo.grp_jikgup_cd     = $("#grp_jikgup_cd_" + index).val();
		userInfo.grp_jikgup_nm     = $("#grp_jikgup_nm_" + index).val();
		userInfo.grp_jikgup_nm_eng = $("#grp_jikgup_nm_eng_" + index).val();
		userInfo.jikmu_cd          = $("#jikmu_cd_" + index).val();
		userInfo.jikmu_nm          = $("#jikmu_nm_" + index).val();
		userInfo.jikmu_nm_eng      = $("#jikmu_nm_eng_" + index).val();
		userInfo.jikchek_cd        = $("#jikchek_cd_" + index).val();
		userInfo.birth_ymd         = $("#birth_ymd_" + index).val();
		userInfo.solar_yn          = $("#solar_yn_" + index).val();
		userInfo.email             = $("#email_" + index).val();
		userInfo.office_tel_no     = $("#office_tel_no_" + index).val();
		userInfo.mobile_no         = $("#mobile_no_" + index).val();
		userInfo.status            = $("#status_" + index).val();
		userInfo.last_locale       = $("#last_locale_" + index).val();
				
		opener.setUserInfo(userInfo);
	}
	
//-->
</script>

</head>
<body class="pop">
<form:form name="frm" method="post" autocomplete="off">
<!-- 페이지 공통 -->
<input type="hidden" name="method"   value="">
<input type="hidden" name="doUserSearch" value="Y">

<!-- 페이지별  -->
<input type="hidden" name="flag" value="">

<!-- Popup Top -->
<h1 class="pop_top"><spring:message code="secfw.page.field.search.empSearch"/></h1>
<!-- //Popup Top -->
<!-- Popup Body-->
<div class="pop_body">
  <div class="pop_area">
    <!-- Popup Detail -->
      <div class="pop_content">
        <div class="bt_content_top">
        	<select name="srch_user_cntnt_type" class="select" >
				<option value="user_nm" <%="user_nm".equals(command.getSrch_user_cntnt_type()) ? "selected" : "" %>><spring:message code="secfw.page.field.search.name"/></option>
				<option value="single_id" <%="single_id".equals(command.getSrch_user_cntnt_type()) ? "selected" : "" %>><spring:message code="secfw.page.field.search.singleId"/></option>
				<option value="mobile_no" <%="mobile_no".equals(command.getSrch_user_cntnt_type()) ? "selected" : "" %>><spring:message code="secfw.page.field.search.phoneNo"/></option>
			</select>
       	    <input type="text" name="srch_user_cntnt"  value="${command.srch_user_cntnt}" class="text" onKeyPress="if(event.keyCode==13) {listUser();return false;}"/>        
        	<a href="#" class="bt_fn" onClick="javascript:listUser();" style="cursor:hand;"><span><spring:message code="secfw.page.field.search.search2"/></span></a></div>
        	
        
        <!-- List -->
        <table class="basic_list mz fix">
			<colgroup>
			<col width="60" align="center"/>
			<col width="80" align="center"/>
			<col width="80" align="center"/>
			<col width="80" align="center"/>
			<col width="70" align="center"/>
			<col width="90" align="center"/>
			<col align="center"/>
			<col/>
			</colgroup>
			<tr>
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
		<div class="dscrolly_b" style="height:300px">
		<table id="userListTable" class="basic_list tb_non fix">
				<colgroup>
					<col width="60" align="center"/>
					<col width="80" align="center"/>
					<col width="80" align="center"/>
					<col width="80" align="center"/>
					<col width="70" align="center"/>
					<col width="90" align="center"/>
					<col align="center"/>									
				</colgroup>
			
 	<%
 	if(listUser != null && listUser.size()>0) {
	    for(int idx=0;idx < listUser.size();idx++){
	    
	    	ListOrderedMap lom = (ListOrderedMap)listUser.get(idx);
			    	
	%> 
			<tr onMouseOver="this.className='selected'" onMouseOut="this.className='';" >
						
				<td><a href="javascript:setUserInfo('<%=idx%>');"><%=lom.get("user_nm") %></a></td>
				<td title="<%=lom.get("email") %>"><div style='width:70;overflow:hidden;text-overflow:ellipsis'><nobr><%=lom.get("email") %></nobr></div></td>
				<td><%=lom.get("comp_nm") %></td>
				<td title="<%=lom.get("dept_nm") %>"><div style='width:70;overflow:hidden;text-overflow:ellipsis'><nobr><%=lom.get("dept_nm") %></nobr></div></td>
				<td><%=lom.get("jikgup_nm") %></td>
				<td><%=lom.get("office_tel_no") %></td>
				<td><%=lom.get("mobile_no") %></td>
				
				<input type=hidden id="user_id_<%=idx%>" value='<%=lom.get("user_id") %>'>
				<input type=hidden id="emp_no_<%=idx%>" value='<%=lom.get("emp_no") %>'>
				<input type=hidden id="user_nm_<%=idx%>" value='<%=lom.get("user_nm") %>'>
				<input type=hidden id="user_nm_eng_<%=idx%>" value='<%=lom.get("user_nm_eng") %>'>
				<input type=hidden id="chinese_nm_<%=idx%>" value='<%=lom.get("chinese_nm") %>'>
				<input type=hidden id="single_id_<%=idx%>" value='<%=lom.get("single_id") %>'>
				<input type=hidden id="single_epid_<%=idx%>" value='<%=lom.get("single_epid") %>'>
				<input type=hidden id="comp_cd_<%=idx%>" value='<%=lom.get("comp_cd") %>'>
				<input type=hidden id="comp_nm_<%=idx%>" value='<%=lom.get("comp_nm") %>'>
				<input type=hidden id="comp_nm_eng_<%=idx%>" value='<%=lom.get("comp_nm_eng") %>'>
				<input type=hidden id="business_cd_<%=idx%>" value='<%=lom.get("business_cd") %>'>
				<input type=hidden id="business_nm_<%=idx%>" value='<%=lom.get("business_nm") %>'>
				<input type=hidden id="business_nm_eng_<%=idx%>" value='<%=lom.get("business_nm_eng") %>'>
				<input type=hidden id="dept_cd_<%=idx%>" value='<%=lom.get("dept_cd") %>'>
				<input type=hidden id="dept_nm_<%=idx%>" value='<%=lom.get("dept_nm") %>'>
				<input type=hidden id="origin_dept_cd_<%=idx%>" value='<%=lom.get("origin_dept_cd") %>'>
				<input type=hidden id="origin_dept_nm_<%=idx%>" value='<%=lom.get("origin_dept_nm") %>'>
				<input type=hidden id="jikgun_cd_<%=idx%>" value='<%=lom.get("jikgun_cd") %>'>
				<input type=hidden id="jikgun_nm_<%=idx%>" value='<%=lom.get("jikgun_nm") %>'>
				<input type=hidden id="jikgun_nm_eng_<%=idx%>" value='<%=lom.get("jikgun_nm_eng") %>'>
				<input type=hidden id="jikgup_cd_<%=idx%>" value='<%=lom.get("jikgup_cd") %>'>
				<input type=hidden id="jikgup_nm_<%=idx%>" value='<%=lom.get("jikgup_nm") %>'>
				<input type=hidden id="jikgup_nm_eng_<%=idx%>" value='<%=lom.get("jikgup_nm_eng") %>'>
				<input type=hidden id="grp_jikgup_cd_<%=idx%>" value='<%=lom.get("grp_jikgup_cd") %>'>
				<input type=hidden id="grp_jikgup_nm_<%=idx%>" value='<%=lom.get("grp_jikgup_nm") %>'>
				<input type=hidden id="grp_jikgup_nm_eng_<%=idx%>" value='<%=lom.get("grp_jikgup_nm_eng") %>'>
				<input type=hidden id="jikmu_cd_<%=idx%>" value='<%=lom.get("jikmu_cd") %>'>
				<input type=hidden id="jikmu_nm_<%=idx%>" value='<%=lom.get("jikmu_nm") %>'>
				<input type=hidden id="jikmu_nm_eng_<%=idx%>" value='<%=lom.get("jikmu_nm_eng") %>'>
				<input type=hidden id="jikchek_cd_<%=idx%>" value='<%=lom.get("jikchek_cd") %>'>
				<input type=hidden id="birth_ymd_<%=idx%>" value='<%=lom.get("birth_ymd") %>'>
				<input type=hidden id="solar_yn_<%=idx%>" value='<%=lom.get("solar_yn") %>'>
				<input type=hidden id="email_<%=idx%>" value='<%=lom.get("email") %>'>
				<input type=hidden id="office_tel_no_<%=idx%>" value='<%=lom.get("office_tel_no") %>'>
				<input type=hidden id="mobile_no_<%=idx%>" value='<%=lom.get("mobile_no") %>'>
				<input type=hidden id="status_<%=idx%>" value='<%=lom.get("status") %>'>
				<input type=hidden id="last_locale_<%=idx%>" value='<%=lom.get("last_locale") %>'>
				
			</tr>
	<%
	    }
 	}
	%>
			
		</table>
        <!-- //List -->
        </div>
        <!-- Popup Footer -->
		<div class="pop_footer">
		  <!-- Function Button -->
		  <a href="javascript:self.close()" class="bt_all_w"><span><img src="<c:url value='/images/secfw/ico/ico_cancel.gif' />"><spring:message code="secfw.page.field.search.cancel"/></span></a>
		  <!-- // Function Button -->
		</div>
		<!-- //Popup Footer -->
       
      </div>
    <!-- //Popup Detail -->
  </div>
</div>
<!-- //Popup Body -->
</form:form>
</body>
</html>