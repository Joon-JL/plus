<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.manage.dto.ConsiderationForm" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%--
/**
 * 파  일  명 : Consideration_opnn_p.jsp
 * 프로그램명 : 의뢰/계약 drop 보류  내역 입력 
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2013.04
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title" /></title>
<link href="<%=CSS%>/popup.css" type="text/css" rel="stylesheet" />
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet" />

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript">
<!--

// 보류 저장을 합니다.
function save() {
	var frm = document.frm;
	var strMethod = frm.submit_status.value;
	var msg = "";
	var returnInfo = new Object();	
	
	if(frm.submit_status.value == "dropRequest"){
		msg ="<spring:message code="clm.page.msg.manage.announce206" />";
	}else if(frm.submit_status.value == "deferRequest"){  // 보류입니다.
		msg ="<spring:message code="clm.page.msg.manage.announce021" />";
	}else if(frm.submit_status.value == "dropContract"){
		msg ="<spring:message code="clm.page.msg.manage.announce108" />";
	}
	
	if(confirm(msg)){ 	
		
		var options = {
			url: "<c:url value='/clm/manage/consideration.do?method="+strMethod+"' />",
			type: "post",
			dataType: "json",			
			success: function(responseText, statusText) {
				
				if(responseText.returnCd == "Y"){
					
					alert("<spring:message code="clm.page.msg.manage.announce158" />");	
					returnInfo.Cd   = responseText.returnCd;
					returnInfo.Msg = responseText.returnMsg;
					returnInfo.strMethod = strMethod;
					
					opener.setOpnnConsideration(returnInfo);	
					self.close();
				}else{
					
					alert("<spring:message code="clm.page.msg.manage.announce157" />");
				}
			}
		};
		$("#frm").ajaxSubmit(options);	
	}
}
//-->
</script>
</head>
<body class="pop">
<form:form name="frm" id='frm' method="post" autocomplete="off"> 
	<!-- search form -->
	<input type="hidden" name="submit_status"   value="<c:out value='${command.submit_status}'/>" />
	<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
	<input type="hidden" name="cntrt_id" value="<c:out value='${command.cntrt_id}'/>" />
	<input type="hidden" name="cnsdreq_id" value="<c:out value='${command.cnsdreq_id}'/>" />
	
	<c:forEach var="cntrtMt" items="${listDc}">
	    <input type="hidden" name="arr_cntrt_id"  value="<c:out value='${cntrtMt.cntrt_id}' />" /> <!-- 계약_마스터_계약_ID -->	
	</c:forEach>
	
<!-- header -->
<h1>
<c:if test = "${command.submit_status == 'dropRequest'}">
    <spring:message code="clm.page.msg.manage.reqDrop" />
</c:if>
<c:if test = "${command.submit_status == 'deferRequest'}">
    <spring:message code="clm.page.msg.manage.reqDelay" />
</c:if>
<c:if test = "${command.submit_status == 'dropContract'}">
    <spring:message code="clm.page.msg.manage.dropCont" />
</c:if>
<span class="close" onclick="javascript:self.close();" title="close"></span></h1>
<!-- //header -->
<!-- body -->
<div class="pop_body">
    <div class="pop_area">
        <!-- Popup Detail -->
        <!-- Popup Size 600*600 -->
        <div class="h_250">
          <div class="pop_content">
				
<!-- form -->
<div id="clientlist">
<table class="table-style01">
	<colgroup>
		<col width="65px" />
		<col />
  	</colgroup>
 	<tbody id="client-body"> 
 	<tr>
 	<td><spring:message code="clm.page.msg.manage.reason" /></td> 
 	<td>
 		<span id="chage_cause">0</span>/<spring:message code="clm.page.msg.manage.stringLen1000" /><br>
 		<textarea name="chage_cause" id="chage_cause" cols="30" rows="5" maxLength="1000"  onkeyup="frmChkLenLang(this,1000,'chage_cause','<%=langCd%>')" class="text_area_full"></textarea> 			
   	</td>
   	</tr>		
     </tbody>
</table>
</div>
<p align="center">&nbsp;</p>
<!-- //form -->
        
          </div>
        </div>
        <!-- //Popup Detail -->
    </div>
</div>
</form:form>

<!-- //body -->
<!--footer -->
<div class="pop_footer">
	<!-- button -->
	 <div class="btn_wrap fR">
	     <span class="btn_all_b"><span class="save"></span><a href="javascript:save();"><spring:message code="clm.page.msg.common.confirm" /></a></span>
	     <span class="btn_all_w"><span class="cancel"></span><a href="#" onclick="javascript:self.close();"><spring:message code="clm.page.msg.common.close" /></a></span>
	 </div>
<!-- //button -->
</div>
<!-- //footer -->
</body>
</html>
