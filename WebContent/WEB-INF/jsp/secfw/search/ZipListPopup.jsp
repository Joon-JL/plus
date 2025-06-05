<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ include file="/WEB-INF/jsp/secfw/common/SessionCheck.jsp" %> 

<%--
/**
 * Subject   : ZipListPopup.jsp
 * Author    : 금현서
 * Create On : 2009.11
 * 
 */
--%>
<%
	ArrayList listZipcode = (ArrayList)request.getAttribute("listZipcode");

	//String dongnm	= request.getParameter("dongnm");
	String dongnm	= "";
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="secfw.page.field.search.zipCdPop"/></title>

<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript">
	function searchZip(dongnm)
	{
		var frm = document.frm;
	    frm.dongnm.value = dongnm;

		frm.target = "_self";
	
		frm.action = "<c:url value='/secfw/util.do' />";
		frm.method.value = "listZipcode";

		frm.submit();
	}

	function selectZip(zipcode, address){		
		opener.setZip(zipcode, address);
		window.close();
	}

</script>

</head>
<LINK href="<c:url value='/style/secfw/popup.css' />" type="text/css" rel="stylesheet">
<body class="pop">
<form:form name="frm" method="post" autocomplete="off">
<!-- 페이지 공통 -->
<input type="hidden" name="method"   value="">

<input type="hidden" name="doSearch" value="N">
<input type="hidden" name="curPage" value="${command.curPage}">

<!-- 페이지별  -->
<input type="hidden" name="empno"   value="">

<!-- Popup Top -->
<h1 class="pop_top"><spring:message code="secfw.page.field.search.zipCdList"/></h1>
<!-- //Popup Top -->
<!-- Popup Body-->
<div class="pop_body">
  <div class="pop_area">
    <!-- Popup Detail -->
      <div class="pop_content">
        <div class="bt_content_top"><spring:message code="secfw.page.field.search.regionNm"/>
        <!--<input type="text" name="textfield2" id="textfield2"  class="input_select" value="-- 전체 --"  size="12" />  -->
        <input type="text" name="dongnm" value="<%=dongnm%>" class="text" onKeyPress="if(event.keyCode==13) {searchZip(frm.dongnm.value);return false;}"/>
        
        <a href="#" onClick="javascript:searchZip(frm.dongnm.value);" style="cursor:hand;" class="bt_fn"><span><spring:message code="secfw.page.field.search.search2"/></span></a></div>
        
        <!-- List -->
        <table class="basic_list mz fix">
			<colgroup>
			<col width="80" align="center"/>
			<col align="center"/>
			<col/>
			</colgroup>
			<tr>
				<th><spring:message code="secfw.page.field.search.zipCd"/></th>
				<th class="end"><spring:message code="secfw.page.field.search.address"/></th>
				<th class="th_scroll"></th>			
			</tr>
		</table>
		<div class="dscrolly_b" style="height:320px">
		<table class="basic_list tb_non fix">
				<colgroup>
					<col width="80" align="center"/>
					<col />
				</colgroup>
			
 	<%
 		int idx = 0;
	    for(idx=0;idx < listZipcode.size();idx++){
	    
	    	ListOrderedMap lom = (ListOrderedMap)listZipcode.get(idx);
			    	
	%> 
			<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'"  onClick="javascript:selectZip('<%=lom.get("zipcode") %>','<%=lom.get("address") %>');" style="cursor:hand;">
						
				<td><%=lom.get("zipcode") %></td>
				<td><%=lom.get("address") %></td>
			</tr>
	<%
                
	    }
	%>
		</table>
        <!-- //List -->
       
      
       
       </div>
       <!-- Popup Footer -->
		<div class="pop_footer">
		  <!-- Function Button -->
		  <a href="" class="bt_all_w"><span><img src="/images/secfw/ico/ico_confirm.gif"><spring:message code="secfw.page.field.search.confirm"/></span></a><a href="javascript:self.close()" class="bt_all_w"><span><img src="/images/secfw/ico/ico_cancel.gif"><spring:message code="secfw.page.field.search.cancel"/></span></a>
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