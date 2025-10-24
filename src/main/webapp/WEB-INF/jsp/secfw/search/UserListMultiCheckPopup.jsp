<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.user.dto.UserForm" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%--
/**
 * Subject   : UserList.jsp
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

		$("#allCheck")
		.click(function() {
			if(($(this).attr("checked"))) {			
				$("input:checkbox[name^='chkValues']")
					.each(function(){
						$(this).attr("checked", true);
					});
			} else {
				$("input:checkbox[name^='chkValues']")
				.each(function(){
					$(this).attr("checked", false);
				});
			}
		}); 

		if(<%=listSize %> == 1) {
			setUserInfos();
		}
	});
	
	function listUser()
	{
		var frm = document.frm;
	    
		frm.target = "_self";
	
		frm.action = "<c:url value='/secfw/user.do' />";
		frm.method.value = "listUser";
	
		frm.submit();
	}
		
	function setUserInfos(){
		
		var frm = document.frm;
		var listSize = <%=listSize%>;
		var userList = new Array();
		
		if(listSize == 1) {
			var userInfo = new Object();
			var index = 0;

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
			userInfo.office_tel_no     = $("#office_tel_no_" + index).val();
			userInfo.mobile_no         = $("#mobile_no_" + index).val();
			userInfo.status            = $("#status_" + index).val();
			userInfo.last_locale       = $("#last_locale_" + index).val();

			userList.push(userInfo);
			opener.setUserInfos(userList);
			
		} else if(listSize > 1 ) {

		    for(var index=0; index < listSize ;index++){
			    
		    	if(frm.chkValues[index].checked == true){
			
					var userInfo = new Object();

					userInfo.user_id           = eval("frm.user_id_" + index+".value");
					userInfo.emp_no            = eval("frm.emp_no_" + index+".value");
					userInfo.user_nm           = eval("frm.user_nm_" + index+".value");
					userInfo.user_nm_eng       = eval("frm.user_nm_eng_" + index+".value");
					userInfo.chinese_nm        = eval("frm.chinese_nm_" + index+".value");
					userInfo.single_id         = eval("frm.single_id_" + index+".value");
					userInfo.single_epid       = eval("frm.single_epid_" + index+".value");
					userInfo.comp_cd           = eval("frm.comp_cd_" + index+".value");
					userInfo.comp_nm           = eval("frm.comp_nm_" + index+".value");
					userInfo.comp_nm_eng       = eval("frm.comp_nm_eng_" + index+".value");
					userInfo.business_cd       = eval("frm.business_cd_" + index+".value");
					userInfo.business_nm       = eval("frm.business_nm_" + index+".value");
					userInfo.business_nm_eng   = eval("frm.business_nm_eng_" + index+".value");
					userInfo.dept_cd           = eval("frm.dept_cd_" + index+".value");
					userInfo.dept_nm           = eval("frm.dept_nm_" + index+".value");
					userInfo.origin_dept_cd    = eval("frm.origin_dept_cd_" + index+".value");
					userInfo.origin_dept_nm    = eval("frm.origin_dept_nm_" + index+".value");
					userInfo.jikgun_cd         = eval("frm.jikgun_cd_" + index+".value");
					userInfo.jikgun_nm         = eval("frm.jikgun_nm_" + index+".value");
					userInfo.jikgun_nm_eng     = eval("frm.jikgun_nm_eng_" + index+".value");
					userInfo.jikgup_cd         = eval("frm.jikgup_cd_" + index+".value");
					userInfo.jikgup_nm         = eval("frm.jikgup_nm_" + index+".value");
					userInfo.jikgup_nm_eng     = eval("frm.jikgup_nm_eng_" + index+".value");
					userInfo.grp_jikgup_cd     = eval("frm.grp_jikgup_cd_" + index+".value");
					userInfo.grp_jikgup_nm     = eval("frm.grp_jikgup_nm_" + index+".value");
					userInfo.grp_jikgup_nm_eng = eval("frm.grp_jikgup_nm_eng_" + index+".value");
					userInfo.jikmu_cd          = eval("frm.jikmu_cd_" + index+".value");
					userInfo.jikmu_nm          = eval("frm.jikmu_nm_" + index+".value");
					userInfo.jikmu_nm_eng      = eval("frm.jikmu_nm_eng_" + index+".value");
					userInfo.jikchek_cd        = eval("frm.jikchek_cd_" + index+".value");
					userInfo.birth_ymd         = eval("frm.birth_ymd_" + index+".value");
					userInfo.solar_yn          = eval("frm.solar_yn_" + index+".value");
					userInfo.office_tel_no     = eval("frm.office_tel_no_" + index+".value");
					userInfo.mobile_no         = eval("frm.mobile_no_" + index+".value");
					userInfo.status            = eval("frm.status_" + index+".value");
					userInfo.last_locale       = eval("frm.last_locale_" + index+".value");   

					userList[index] = userInfo;
		    	}
		    }

			opener.setUserInfos(userList);
			window.close();
		}
	}
		
//-->
</script>

</head>
<body class="pop">
<form:form name="frm" method="post" autocomplete="off">
<!-- 페이지 공통 -->
<input type="hidden" name="method"   value="">
<input type="hidden" name="multiChk"   value="Y">
<input type="hidden" name="doUserSearch"   value="Y">

<!-- 페이지별  -->

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
				<option value="user_nm" <%="user_nm".equals(command.getSrch_user_cntnt_type()) ? "selected" : "" %>><spring:message code="secfw.page.field.search.name"/> </option>
				<option value="single_id" <%="single_id".equals(command.getSrch_user_cntnt_type()) ? "selected" : "" %>><spring:message code="secfw.page.field.search.singleId"/></option>
				<option value="mobile_no" <%="mobile_no".equals(command.getSrch_user_cntnt_type()) ? "selected" : "" %>><spring:message code="secfw.page.field.search.phoneNo"/></option>
			</select>
			<input type="text" name="srch_user_cntnt"  value="${command.srch_user_cntnt}" class="text" onKeyPress="if(event.keyCode==13) {listUser();return false;}"/>
			<a href="#" class="bt_fn" onClick="javascript:listUser();" style="cursor:hand;"><span><spring:message code="secfw.page.field.search.search2"/></span></a></div>
			
        <!-- List -->
        <table class="basic_list mz fix">
			<colgroup>
			<col width="30" align="center"/>
			<col width="60" align="center"/>
			<col width="70" align="center"/>
			<col width="70" align="center"/>
			<col width="80" align="center"/>
			<col width="70" align="center"/>
			<col width="90" align="center"/>
			<col align="center"/>
			<col/>
			</colgroup>
			<tr>
				<th><input type="checkbox" id="allCheck" name="allCheck" class="checkbox" onClick="javascript:checkAll();"></th>
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
		<table class="basic_list tb_non fix">
				<colgroup>
					<col width="30" align="center"/>
					<col width="60" align="center"/>
					<col width="70" align="center"/>
					<col width="70" align="center"/>
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
			<tr>
				<td><input type="checkbox" id="<%=idx %>" name="chkValues" value="<%=lom.get("user_id") %>" class="checkbox"></td>		
				<td><%=lom.get("user_nm") %></td>
				<td title="<%=lom.get("email") %>"><div style='width:60;overflow:hidden;text-overflow:ellipsis'><nobr><%=lom.get("email") %></nobr></div></td>
				<td><%=lom.get("comp_nm") %></td>
				<td title="<%=lom.get("dept_nm") %>"><div style='width:70;overflow:hidden;text-overflow:ellipsis'><nobr><%=lom.get("dept_nm") %></nobr></div></td>
				<td><%=lom.get("jikgup_nm") %></td>
				<td><%=lom.get("office_tel_no") %></td>
				<td><%=lom.get("mobile_no") %></td>
				
				<input type=hidden name="user_id_<%=idx%>"           id="user_id_<%=idx%>" value='<%=lom.get("user_id") %>'>
				<input type=hidden name="emp_no_<%=idx%>"            id="emp_no_<%=idx%>" value='<%=lom.get("emp_no") %>'>
				<input type=hidden name="user_nm_<%=idx%>"           id="user_nm_<%=idx%>" value='<%=lom.get("user_nm") %>'>
				<input type=hidden name="user_nm_eng_<%=idx%>"       id="user_nm_eng_<%=idx%>" value='<%=lom.get("user_nm_eng") %>'>
				<input type=hidden name="chinese_nm_<%=idx%>"        id="chinese_nm_<%=idx%>" value='<%=lom.get("chinese_nm") %>'>
				<input type=hidden name="single_id_<%=idx%>"         id="single_id_<%=idx%>" value='<%=lom.get("single_id") %>'>
				<input type=hidden name="single_epid_<%=idx%>"       id="single_epid_<%=idx%>" value='<%=lom.get("single_epid") %>'>
				<input type=hidden name="comp_cd_<%=idx%>"           id="comp_cd_<%=idx%>" value='<%=lom.get("comp_cd") %>'>
				<input type=hidden name="comp_nm_<%=idx%>"           id="comp_nm_<%=idx%>" value='<%=lom.get("comp_nm") %>'>
				<input type=hidden name="comp_nm_eng_<%=idx%>"       id="comp_nm_eng_<%=idx%>" value='<%=lom.get("comp_nm_eng") %>'>
				<input type=hidden name="business_cd_<%=idx%>"       id="business_cd_<%=idx%>" value='<%=lom.get("business_cd") %>'>
				<input type=hidden name="business_nm_<%=idx%>"       id="business_nm_<%=idx%>" value='<%=lom.get("business_nm") %>'>
				<input type=hidden name="business_nm_eng_<%=idx%>"   id="business_nm_eng_<%=idx%>" value='<%=lom.get("business_nm_eng") %>'>
				<input type=hidden name="dept_cd_<%=idx%>"           id="dept_cd_<%=idx%>" value='<%=lom.get("dept_cd") %>'>
				<input type=hidden name="dept_nm_<%=idx%>"           id="dept_nm_<%=idx%>" value='<%=lom.get("dept_nm") %>'>
				<input type=hidden name="origin_dept_cd_<%=idx%>"    id="origin_dept_cd_<%=idx%>" value='<%=lom.get("origin_dept_cd") %>'>
				<input type=hidden name="origin_dept_nm_<%=idx%>"    id="origin_dept_nm_<%=idx%>" value='<%=lom.get("origin_dept_nm") %>'>
				<input type=hidden name="jikgun_cd_<%=idx%>"         id="jikgun_cd_<%=idx%>" value='<%=lom.get("jikgun_cd") %>'>
				<input type=hidden name="jikgun_nm_<%=idx%>"         id="jikgun_nm_<%=idx%>" value='<%=lom.get("jikgun_nm") %>'>
				<input type=hidden name="jikgun_nm_eng_<%=idx%>"     id="jikgun_nm_eng_<%=idx%>" value='<%=lom.get("jikgun_nm_eng") %>'>
				<input type=hidden name="jikgup_cd_<%=idx%>"         id="jikgup_cd_<%=idx%>" value='<%=lom.get("jikgup_cd") %>'>
				<input type=hidden name="jikgup_nm_<%=idx%>"         id="jikgup_nm_<%=idx%>" value='<%=lom.get("jikgup_nm") %>'>
				<input type=hidden name="jikgup_nm_eng_<%=idx%>"     id="jikgup_nm_eng_<%=idx%>" value='<%=lom.get("jikgup_nm_eng") %>'>
				<input type=hidden name="grp_jikgup_cd_<%=idx%>"     id="grp_jikgup_cd_<%=idx%>" value='<%=lom.get("grp_jikgup_cd") %>'>
				<input type=hidden name="grp_jikgup_nm_<%=idx%>"     id="grp_jikgup_nm_<%=idx%>" value='<%=lom.get("grp_jikgup_nm") %>'>
				<input type=hidden name="grp_jikgup_nm_eng_<%=idx%>" id="grp_jikgup_nm_eng_<%=idx%>" value='<%=lom.get("grp_jikgup_nm_eng") %>'>
				<input type=hidden name="jikmu_cd_<%=idx%>"          id="jikmu_cd_<%=idx%>" value='<%=lom.get("jikmu_cd") %>'>
				<input type=hidden name="jikmu_nm_<%=idx%>"          id="jikmu_nm_<%=idx%>" value='<%=lom.get("jikmu_nm") %>'>
				<input type=hidden name="jikmu_nm_eng_<%=idx%>"      id="jikmu_nm_eng_<%=idx%>" value='<%=lom.get("jikmu_nm_eng") %>'>
				<input type=hidden name="jikchek_cd_<%=idx%>"        id="jikchek_cd_<%=idx%>" value='<%=lom.get("jikchek_cd") %>'>
				<input type=hidden name="birth_ymd_<%=idx%>"         id="birth_ymd_<%=idx%>" value='<%=lom.get("birth_ymd") %>'>
				<input type=hidden name="solar_yn_<%=idx%>"          id="solar_yn_<%=idx%>" value='<%=lom.get("solar_yn") %>'>
				<input type=hidden name="office_tel_no_<%=idx%>"     id="office_tel_no_<%=idx%>" value='<%=lom.get("office_tel_no") %>'>
				<input type=hidden name="mobile_no_<%=idx%>"         id="mobile_no_<%=idx%>" value='<%=lom.get("mobile_no") %>'>
				<input type=hidden name="status_<%=idx%>"            id="status_<%=idx%>" value='<%=lom.get("status") %>'>
				<input type=hidden name="last_locale_<%=idx%>"       id="last_locale_<%=idx%>" value='<%=lom.get("last_locale") %>'>			

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
		  <a href="javascript:setUserInfos();" class="bt_all_b" style="cursor:hand;"><span><img src="<c:url value='/images/secfw/ico/ico_confirm.gif' />" /><spring:message code="secfw.page.field.search.confirm"/></span></a>
		  <a href="javascript:self.close()" class="bt_all_w" style="cursor:hand;"><span><img src="<c:url value='/images/secfw/ico/ico_cancel.gif' />"><spring:message code="secfw.page.field.search.cancel"/></span></a>
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