<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

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


	//String empnm	= request.getParameter("empnm");
	String empnm = "";
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="secfw.page.field.search.empPopup"/></title>

<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript">
	function searchEmployee(empnm)
	{
		var frm = document.frm;
	    frm.empnm.value = empnm;
	
		frm.target = "_self";
	
		frm.action = "<c:url value='/secfw/user.do' />";
		frm.method.value = "listUser";
	
		frm.submit();
	}

</script>

</head>
<LINK href="<c:url value='/style/secfw/page.css' />" type="text/css" rel="stylesheet">
<body class="pop">
<form:form name="frm" method="post" autocomplete="off">
<!-- Popup Top -->
<h1 class="pop_top"><spring:message code="secfw.page.field.search.empList"/></h1>
<!-- //Popup Top -->
<!-- Popup Body-->
<div class="pop_body">
  <div class="pop_area">
    <!-- Popup Detail -->
    <div class="dscrolly" style="height:500px;">
      <div class="pop_content">
        <div class="detail_search_box top_space_search"><spring:message code="secfw.page.field.search.status"/>
        <input type="text" name="textfield2" id="textfield2"  class="input_select" value="<spring:message code="secfw.page.field.search.allL"/>"  size="12" /><a href="#" class="bt_fn">
        <input type="text" name="empnm" value="<%=empnm%>" class="text" />
        
        <span><a href="#" onClick="javascript:searchEmployee(frm.empnm.value);" style="cursor:hand;"><spring:message code="secfw.page.field.search.search2"/></span></a></div>
        
        <!-- List -->
        <table class="basic_list">
			<colgroup>
			<col width="10%" align="center"/>
			<col />
			<col width="10%" align="center"/>
			<col width="10%" align="center"/>
			<col width="10%" align="center"/>
			<col width="10%" align="center"/>
			</colgroup>
			<tr>
				<th><spring:message code="secfw.page.field.search.name"/></th>
				<th><spring:message code="secfw.page.field.search.department"/></th>
				<th><spring:message code="secfw.page.field.search.position"/></th>
				<th><spring:message code="secfw.page.field.search.phoneNo"/></th>
				<th><spring:message code="secfw.page.field.search.comNo"/></th>
				<th><spring:message code="secfw.page.field.search.company"/></th>
			</tr>
			
 	<%
 		int idx = 0;
	    for(idx=0;idx < listUser.size();idx++){
	    
	    	ListOrderedMap lom = (ListOrderedMap)listUser.get(idx);
			    	
	%> 
			<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
				<td><%=lom.get("user_nm") %></td>
				<td><%=lom.get("dept_nm") %></td>
				<td><%=lom.get("jikgup_nm") %></td>
				<td><%=lom.get("mobile_no") %></td>
				<td><%=lom.get("office_tel_no") %></td>
				<td><%=lom.get("comp_nm") %></td>
			</tr>
	<%
                
	    }
	%>
		</table>
        <!-- //List -->
       
      
       
       
      </div>
    </div>
    <!-- //Popup Detail -->
  </div>
</div>
<!-- //Popup Body -->
<!-- Popup Footer -->
<div class="pop_footer">
  <!-- Function Button -->
  <a href="" class="bt_all_b"><span><img src="/images/secfw/ico/ico_confirm.gif"><spring:message code="secfw.page.field.search.confirm"/></span></a><a href="javascript:self.close()" class="bt_all_w"><span><img src="/images/secfw/ico/ico_cancel.gif"><spring:message code="secfw.page.field.search.cancel"/></span></a>
  <!-- // Function Button -->
</div>
<!-- //Popup Footer -->
</form:form>
</body>
</html>