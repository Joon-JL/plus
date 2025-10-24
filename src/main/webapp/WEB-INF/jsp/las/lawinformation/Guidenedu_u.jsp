<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.las.lawinformation.dto.GuideneduForm" %>
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
 * 파  일  명 : Guidenedu_u.jsp
 * 프로그램명 : 임직원법무교육자료 수정
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 2011.09
 */
 --%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%@ include file="/WEB-INF/jsp/common/messageUtil.jsp" %>
<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta charset="utf-8" />

<title><spring:message code="las.main.title" /></title>
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<LINK href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">

<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/util/fckeditor/fckeditor.js' />"></script>
<script language="javascript">

	function save() {
		var frm = document.frm;

		if(frm.title.value == ""){
			alert("<spring:message code='las.msg.alert.titleIsReq'/>");	
		}else{

	    	if(confirm("<spring:message code='las.msg.ask.update'/>")){

	    		fileList.UploadFile(function(uploadCount){
	    			//첨부파일 업로드 결과 잘못된 형식의 파일이 올라간 경우
	    			if(uploadCount == -1){
	    				initPage();	//첨부파일창 Reload
	    				alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
	    				return;
	    			}
	    			
	    			//첨부파일이 필수인 경우: 사용자 선택사항
	    			//if(uploadCount==0){
	    			//	alert("<spring:message code='secfw.msg.error.fileNon' />");
	    			//	return;
	    			//}
		    	
// 				    frm.body_mime.value = frm.wec.MIMEValue;
					
					frm.action = "<c:url value='/las/lawinformation/guidenedu.do' />";
					frm.method.value = "modifyGuidenedu";
					frm.target = "_self";
	
					viewHiddenProgress(true) ;
					frm.submit();
	    		});
	    	}
		}
	}
	
	function cancel(){

		var frm = document.frm;

    	if(confirm("<spring:message code='secfw.msg.ask.cancel' />")){

			frm.target = "_self";
			frm.action = "<c:url value='/las/lawinformation/guidenedu.do' />";
			frm.method.value ="detailGuidenedu";

			viewHiddenProgress(true) ;
			frm.submit();
    	}
	}
	
	/**
	* - Namo Editor 세팅 
	*/
	$(document).ready(function(){
		var frm = document.frm;
		
		//첨부파일창 load
		initPage();
		
		var $inputTxt=$('#title');
		if($inputTxt.val()==''){
			$inputTxt.focus();
		}
	});
	
	function initPage(){
		//첨부파일창 load
		frm.target = "fileList";
	    frm.action = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value = "forwardClmsFilePage";
		
		frm.submit();	
	}

</script>
</head>
<body>

<div id="wrap">	
	<div id="container">
		<!-- location -->
		<div class="location"><IMG SRC="<%=IMAGE %>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //location -->
		
		<!-- title -->
		<div class="title">
			<h3><spring:message code="las.page.title.lawinformation.empLegEdu" /></h3>
		</div>
		<!-- //title -->
		
		<!-- content -->
		<div id="content">
		<div id="content_in">
			<!-- *********************** Form Setting ******************************** -->
			<form:form name="frm" id="frm" method="post" autocomplete="off">
			<input type="hidden" name="method"   	 	value="" />
			<input type="hidden" name="menu_id"  	 	value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="curPage"  	 	value="<c:out value='${command.curPage}'/>" />
			<input type="hidden" name="srch_word"  		value="<c:out value='${command.srch_word}'/>" />
			<input type="hidden" name="srch_type" 		value="<c:out value='${command.srch_type}'/>" />
			<input type="hidden" name="srch_start_dt" 	value="<c:out value='${command.srch_start_dt}'/>" />
			<input type="hidden" name="srch_end_dt" 	value="<c:out value='${command.srch_end_dt}'/>" />
			
			<input type="hidden" name="info_gbn"  id="info_gbn"  value="<c:out value='${command.info_gbn}'/>" />
			<input type="hidden" name="seqno"     id="seqno"     value="<c:out value='${command.seqno}'/>" />
			<!-- 첨부파일정보 -->
			<input type="hidden" name="fileInfos" 		value="" />
			<input type="hidden" name="file_bigclsfcn" 	value="F004" />
			<input type="hidden" name="file_midclsfcn" 	value="<c:out value='${command.info_gbn}'/>" />
			<input type="hidden" name="file_smlclsfcn" 	value="" />
			<input type="hidden" name="ref_key" 		value="<c:out value='${command.seqno}'/>" />
			<input type="hidden" name="view_gbn" 		value="modify" />
			
			<!-- 나모 웹 에디터 관련 -->
<%-- 			<input type="hidden" name="body_mime" id="body_mime" value="<c:out value='${lom.cont}' />" /> --%>
			<!-- // *********************** Form Setting ******************************** -->
			
			<div class="btnwrap">
				<span class="btn_all_w"><span class="save"></span><a href="javascript:save()"><spring:message code='las.page.button.save' /></a></span>
				<span class="btn_all_w"><span class="cancel"></span><a href="javascript:cancel()"><spring:message code='las.page.button.cancel' /></a></span>
			</div>
			
			<!--  view  -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
					<col width="15%"/>
					<col width="35%"/>
					<col width="15%"/>
					<col width="35%"/>
				</colgroup>
				<tbody>
					<tr>
			            <th><spring:message code='las.page.field.board.notice.title' /></th>
			            <td colspan="3">
			            	<span><input type="text" name="title" id="title" class="text_full" maxLength="128" value="<c:out value='${lom.title}' />" alt="<spring:message code='las.page.field.board.notice.title' />" maxLength="64" required/></span>
			            </td>
		        	</tr>
					<tr>
						<th><spring:message code='las.page.field.board.notice.reg_nm' /></th>
						<td>
							<c:out value='${lom.reg_nm}' default="" />
						</td>
						<th><spring:message code='las.page.field.board.notice.reg_dt' /></th>
						<td>
							<c:out value='${lom.reg_dt}' default="" />
						</td>
					</tr>
					<tr>
						<th><spring:message code="las.page.field.mainmatrhist.cont"/></th>
						<td colspan="3">
							<span id="cont">0</span>/<spring:message code="clm.page.msg.manage.stringLen2000" /><br>
						    <textarea id="cont" name="cont" cols="100%" rows="10" maxLength="2000" onkeyup="frmChkLenMe(this,2000,'cont')" class="text_area_full"><c:out value="${lom.cont}" escapeXml="" /></textarea>
							<%//@ include file="/WEB-INF/jsp/secfw/common/NamoEditInclude.jspf"%>
						</td>
					</tr>
					<tr class="end">
		            	<th><spring:message code='las.page.field.board.notice.attach_file' /> <img src="/images/las/ko/common/step_q.gif" alt="info" title="DOC, DOCX, GUL, PDF, OCX, XLS, XLSX, PPT, PPTX,&#13;JPG, HWP, TIF, RTF, ZIP, PTX, TML, TXT, LSX, EML, BMP, GIF, MSG, ALZ, DOT &#13;&#13;<spring:message code='las.msg.com.fileFilter'/>" /></th>
		            	<td colspan="3">
		            		<div class='ico_maxsize fR'>Max Size : <span>10MB</span></div>
		            		<!-- Sungwoo Replaced scroll option 2014/08/04 -->
		            		<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>
		            	</td>
		          	</tr>
				</tbody>
			</table>
			<!-- //view  -->
			<!-- Function Button  -->
			<div class="btnwrap mt20">
				<span class="btn_all_w"><span class="save"></span><a href="javascript:save()"><spring:message code='las.page.button.save' /></a></span>
				<span class="btn_all_w"><span class="cancel"></span><a href="javascript:cancel()"><spring:message code='las.page.button.cancel' /></a></span>
			</div>
			<!-- //Function Button  -->
			</form:form>
		</div>
		<!-- //content -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
<!-- footer  -->
<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
<!-- // footer -->
</body>
<!-- 나모 웹에디터 관련 추가 -->
<SCRIPT language="javascript" FOR="wec" EVENT="OnInitCompleted()">
	var wecObj = document.wec;
	wecObj.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
	wecObj.SetDefaultFontSize("9");
	wecObj.EditMode = 1;
	wecObj.Value = document.frm.body_mime.value;
</SCRIPT>
</html>