<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>
<%@ page import="com.sds.secframework.common.util.Token"%>

<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%--
/**
 * 파  일  명 : LawyerProfilePhoto_p.jsp
 * 프로그램명 : 법무시스템 : 변호사 상세 사진 등록 팝업
 * 설      명 : 
 * 작  성  자 : 박병주
 * 작  성  일 : 2011.09
 */
 --%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><spring:message code="las.main.title" /></title>
<LINK href="<%=CSS%>/popup.css" type="text/css" rel="stylesheet">
<LINK href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">

<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="JavaScript" type="text/JavaScript" >
<!--
	
	function CmIsExistCheckError(){
		
		var popup_frm = document.popup_frm;
		var check_flag = false;		
		
		if (popup_frm.lawyer_photo.value.length != 0){
			sTemp = popup_frm.lawyer_photo.value;		
			var fnameExt = sTemp.substring(sTemp.lastIndexOf('.')+1);				
			if (fnameExt == 'jpg' || fnameExt == 'JPG'|| fnameExt == 'gif' || fnameExt == 'GIF') {					
				check_flag = true;				
			} else {				    
				alert("<spring:message code='las.page.msg.lawservice.photofiletype' />");				    
				popup_frm.lawyer_photo.value = "";				    
				check_flag = false;					
			}				
		}			
		return check_flag;
	}
	
	function save(){
		
		var popup_frm = document.popup_frm;
		
		fileList.UploadFile(function(uploadCount){
			if(uploadCount == -1){
				initPage();
				alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
				return;
			}
            if (uploadCount == 0) {
                alert("<spring:message code='secfw.msg.error.fileNon' />");
                return;
            }
          
    		viewHiddenProgress(true);
    		
            popup_frm.lwr_no.value = opener.frm.lwr_no.value;  
    		popup_frm.target = "bodyFrame";
    		popup_frm.method.value = "forwardDetailLawywerFromPopUp";
    		popup_frm.action = "<c:url value='/las/lawservice/lawyerManage.do' />";
    		popup_frm.submit();
	    	self.close();
            
		});
	}	

	function initPage(){
		var frm = document.popup_frm;
	    frm.target			= "fileList";
 	    frm.action = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value  	= "forwardPopFile";
		frm.submit();	
	}
//-->
</script>
</head>
<body class="pop" onLoad="initPage();">
<form:form name="popup_frm" id="popup_frm" method="post" autocomplete="off">
<input type="hidden" name="method"   	 	value="" />
<input type="hidden" name="menu_id"  	 	value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="screen_type"  value="insert" />

<!--key -->
<input type="hidden" name="lwr_no" 		value="<c:out value='${command.lwr_no}'/>" />

<!-- 첨부파일정보 -->

<input type="hidden" name="fileType" 		value="image" />
<input type="hidden" name="fileNm" 		value="<c:out value='${command.lwr_no}'/>" />
<input type="hidden" name="folderNm" 	value="lawyer" />

<!-- header -->
<h1><spring:message code="las.page.field.lawservice.lwrdetail" />&nbsp;<spring:message code="las.page.field.lawservice.profileup" /></h1>
<!-- //header -->
<div class="pop_area">
<!-- pop_content -->
<div class="pop_content">
	<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="2px" width="100%" height="80px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
</div>	
	 *<spring:message code="las.page.msg.lawservice.photosize" />
<!--// pop_content -->

</div>
<!--footer -->
<div class="pop_footer">
	<!-- button -->
	 <div class="btn_wrap fR mt20">
     <span class="btn_all_b"><span class="save"></span><a href="#" onclick="javascript:save()"><spring:message code="las.page.button.lawservice.save"/></a></span>
	 <span class="btn_all_w"><span class="cancel"></span><a href="#" onclick="javascript:self.close();"><spring:message code="las.page.button.lawservice.close"/></a></span>

	 </div>
<!-- //button -->
</div>
<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</form:form>
<!-- //footer -->
</body>
</html>