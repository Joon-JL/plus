<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%
	ListOrderedMap tnclom = (ListOrderedMap)request.getAttribute("tnclom");
%>
<script language="javascript">
  
function gotoUrl1(url){
// 	parent.parent.location.href=url;
// 	PopUpWindowOpen2(url, 800, 800, false, "TnCInformation");
	
	window.open(url);
}

function gotoUrl2(url){
// 	parent.parent.location.href=url;
// 	PopUpWindowOpen2(url, 800, 800, false, "TnCInformation");
	
	window.open(url,'TnCInformation','height=' + screen.height + ',width=' + screen.width + 'fullscreen=yes');
}

</script>

<c:if test="${tnclom.sys_nm == 'TNC'}">
<div class='title_basic3'><spring:message code="las.page.field.tnc.urlInformation" /></div>
<table class="table-style01">
	<colgroup>
		<col width="12%" />
		<col width="*%" />				
	</colgroup>
	<tr>
		<th><spring:message code="las.page.field.tnc.approvalInformation" /></th>
		<td><a href="" onclick="javascript:gotoUrl1('<%=tnclom.get("tnc_app_link") %>'); return false;"><spring:message code="las.page.field.tnc.approvalInformation" /></a></td>
	</tr>
</table>
</c:if>