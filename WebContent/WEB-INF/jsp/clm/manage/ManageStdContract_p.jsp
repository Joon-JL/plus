

<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
 
<%@ page import="com.sds.secframework.common.util.Token" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
 
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %> 
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<%--
/**
 * 파     일     명 : ManageStdContract_p.jsp
 * 프로그램명 : 반려 
 * 설               명 : 
 * 작     성     자 : 
 * 작     성     일 : 2012.07
 */
--%>
 
<html>
<head>
 <meta charset="utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
 <title><spring:message code="clm.page.msg.manage.rsnOfReturn" /></title>
 
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet"/>
<link href="<%=CSS%>/popup.css" type="text/css" rel="stylesheet"/>
 <!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript">
<!--
 
/**
* - 페이지 초기화 --
*/
$(document).ready(function(){
 
 /**
 * 버튼 동작 부분
 */
 // 취소
 $('#cancelBtn').click(function(){
  self.close();
 });
 
 // 반려
 $('#returnBtn').click(function(){
 
  if($('textarea[name=rejct_opnn]').val() == '') {alert("<spring:message code="clm.page.msg.manage.announce087" />");return;}
  
  opener.document.frm.rejct_opnn.value = $('textarea[name=rejct_opnn]').val();
  opener.forwardRejctOpnn();
  
  window.close();
 
 });
});
 
//-->
</script>
</head>
<body class="pop">
<!-- header -->
<h1><spring:message code="clm.page.msg.manage.rsnOfReturn" /></h1>
<!-- //header -->
 
<div class="pop_area">
 <div class="pop_content">
 

  <form:form id='frm' name='frm' autocomplete="off">
 

            <!-- 반려 table -->
   <table class="table-style01">
    <colgroup>              
                    <col width="20%" />
                    <col width="80%" />
                 </colgroup>
    <tr>
     <th class='title'><spring:message code="clm.page.msg.manage.rsnOfReturn" /></th>
     <td>
       <textarea name='rejct_opnn' rows="30" class='IpTextLe' style='width:98%; height:70px;' fieldTitle="<spring:message code="clm.page.msg.manage.rsnOfReturn" htmlEscape="true" />" maxLength="1024"></textarea>
     </td>
    </tr>
   </table>
   <!-- // 반려 table -->
  </form:form>
  
  
 </div>
</div>
 
<!--footer -->
<div class="pop_footer">
 <div class="btn_wrap fR" >
  <span class="btn_all_w"><a id="returnBtn"><span class="save"></span><spring:message code="clm.page.msg.common.save" /></a></span>   
  <span class="btn_all_w mR5"><a id="cancelBtn"><span class="cancel"></span><spring:message code="clm.page.button.cancel"/></a></span>
 </div>
</div>
<!-- //footer -->
 
</body>
</html>

 















