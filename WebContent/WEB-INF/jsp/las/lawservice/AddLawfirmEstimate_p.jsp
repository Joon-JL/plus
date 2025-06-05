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

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%--
/**
 * 파  일  명 : addLawfirmEstimate.jsp
 * 프로그램명 : 로펌관리 - 로펌 평가 팝업
 * 설      명 : 
 * 작  성  자 : 박병주
 * 작  성  일 : 2011.09
 */
 --%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.main.title" /></title>
<LINK href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">
<LINK href="<%=CSS%>/popup.css" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript">
<!--
	/**
	* 초기화면 로딩 
	*/
	$(document).ready(function(){		
		
		var frm = document.frm;
		
		//첨부파일창 load
		initPage();
	
	});
	
	/**
	* 첨부파일창 load
	*/
	function initPage(){
	
	    frm.target          = "fileList";
	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    = "forwardClmsFilePage";
	    frm.submit();   
	}
	
	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		var popup_frm = document.frm;
		var openner_frm = opener.frm;	
	
		//저장
		if(flag == "insert"){
			
		    //유효성 체크
		    if(validateForm(frm)){
		    	
		    	fileList.UploadFile(function(uploadCount){

	                //첨부파일 업로드 결과 잘못된 형식의 파일이 올라간 경우
	
	                if(uploadCount == -1){
	
	                    initPage(); //첨부파일창 Reload
	                    alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
	                    return;
	
	                } 		
		
			    	if(confirm("<spring:message code='las.msg.ask.insert'/>")){
			    		
			    		viewHiddenProgress(true);
		
			    		popup_frm.target = "bodyFrame";
						frm.method.value = "insertLawfirmEstimate";
						frm.action = "<c:url value='/las/lawservice/lawfirmManage.do' />";
				    	frm.submit();
				    	self.close();
				    	
			    	} else {
			    		return;	
			    	}
		    	 });
		    }		    
			
		//취소
		} else if(flag == "cancel"){	

			self.close();
		} 
		
		function checkForm(){
			
			var frm = document.frm;
			var check_tag = true;
			
			if (frm.estmt_name.value=="")
			{
				alert("<spring:message code="las.page.msg.lawservice.estmtnm"/>");
				frm.estmt_name.focus();
				return false;
			} 
			
			if (frm.estmt_cont.value=="")
			{
				alert("<spring:message code="las.page.msg.lawservice.estmtcont"/>");
				frm.estmt_cont.focus();
				return false;
			} 
			
			return check_tag;
	    	
		}
	}
	
//-->
</script>

</head>
<body class="pop">
<form:form name="frm" id="frm" method="post" autocomplete="off" >
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id"  	 		value="<c:out value='${command.menu_id}'/>" />

<input type="hidden" name="estmt_no"   value="<c:out value='${command.estmt_no}'/>" />
<input type="hidden" name="lawfirm_id"   value="<c:out value='${command.lawfirm_id}'/>" />

<!-- 첨부파일정보 시작 -->
<input type="hidden" name="fileInfos"   value="" />
<input type="hidden" name="file_bigclsfcn"  value="F005" />
<input type="hidden" name="file_midclsfcn" 	value="F00506" />
<input type="hidden" name="file_smlclsfcn"  value="" />
<input type="hidden" name="ref_key"     value="<c:out value='${command.estmt_no}'/>" />
<input type="hidden" name="view_gbn" 	value="upload" />
<!-- header -->
<h1><spring:message code="las.page.field.lawservice.estmtlawfirm"/><span class="close" onclick="javascript:self.close();" title="close"></span></h1>
<!-- //header -->
<div class="pop_area">
	<div class="h_860">
		<!-- pop_content -->
		<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
			<colgroup>
				<col class="tal_w04" />
				<col />
				<col class="tal_w04" />
				<col />
			</colgroup>
			<tbody>
				<tr>
					<th><spring:message code="las.page.field.lawservice.maniftree"/></th>
					<td colspan="3">
						<ul class="list_assess">
							<li>
								<input type="text" id="estmt_name" name="estmt_name" id="text_field" value="" class="text" required alt="<spring:message code="las.page.field.lawservice.estmtnm"/>" maxLength="32" /> 
							</li>
							<li>	
								<textarea name="estmt_cont" id="estmt_cont" cols="10" rows="7" class="text_area_01" required alt="<spring:message code="las.page.field.lawservice.estmtcont"/>" maxLength="1000" ></textarea>
							</li>
						</ul>
					</td>
				</tr>
				<tr class="end">
					<th><spring:message code="las.page.field.lawservice.attach"/></th>
					<td colspan="3">
						<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="80px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
					</td>
				</tr>
			</tbody>
		</table>
		<!-- //view -->

		<div class="btn_wrap">
			<span class="btn_all_b"><span class="save"></span><a href="javascript:pageAction('insert')"><spring:message code="las.page.button.lawservice.save"/></a></span>
			<span class="btn_all_w"><span class="cancel"></span><a href="javascript:pageAction('cancel')"><spring:message code="las.page.button.lawservice.cancel"/></a></span>
		</div>
	</div>
	<!-- //content -->
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</div>
<!-- //container -->
</form:form>
</body>
</html>
