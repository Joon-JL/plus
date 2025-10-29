<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.las.board.dto.NoticeForm" %>
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
* 파  일  명 : DecideArbitrarilyRe_i.jsp
 * 프로그램명 : 전결규정 등록
 * 설      명 : 	기존 CLM의 전결규정의 목록타입과 다르게
 				각사별로 ADMIN-전결규정 입력폼 -> 법무관련규정 단건 상세 조회 방식으로 구현
 				입력과 업데이트
 * 작  성  자 : 황민우
 * 작  성  일 : 2013.05.20
 */
 --%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
    //메뉴네비게이션 스트링
	String menuNavi 			= (String)request.getAttribute("secfw.menuNavi");
	
	//나모에디터 스트링
	String wec_set_id 			= ""; 
	String wec_mode 			= ""; 
	String wec_com_script_yn 	= "";
%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<title><spring:message code="las.main.title" /></title>
<LINK href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">

<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script type="text/javascript" src="<c:url value='/util/fckeditor/fckeditor.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script type="text/javascript" src="/crosseditor/js/namo_scripteditor.js"></script>

<script language="javascript">

	function save() {
		var frm = document.frm;

// 		if(frm.title.value == ""){
// 			alert("<spring:message code='las.page.field.board.notice.titleKr'/>"+"<spring:message code='las.msg.alert.isRequired'/>");	
// 		}else 
		if(frm.title_en.value==""){
			alert("<spring:message code='las.page.field.board.notice.titleEn'/>"+"<spring:message code='las.msg.alert.isRequired'/>");
		}else{

	    	if(confirm("<spring:message code='las.msg.ask.insert'/>")){

	    		fileList.UploadFile(function(uploadCount){
	    			//첨부파일 업로드 결과 잘못된 형식의 파일이 올라간 경우
	    			if(uploadCount == -1){
	    				initPage();	//첨부파일창 Reload
	    				alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
	    				return;
	    			}
	    		
		    	
// 				    frm.body_mime.value 	= frm.wec[0].MIMEValue;
				    //frm.body_mime_en.value 	= frm.wec.MIMEValue; 나모 형식
				    
// 				    alert("CrossEditor.GetBodyValue() = "+CrossEditor.GetBodyValue());
				    
				    frm.body_mime_en.value 	= CrossEditor.GetBodyValue();
					
				    frm.method.value = "updateDecideArbitrarilyRe";
					frm.action = "<c:url value='/las/board/notice.do' />";
					frm.target = "_self";
	
					viewHiddenProgress(true) ;
					frm.submit();
	    		});
	    	}
		}
	}
	
	function cancel(){

		var frm = document.frm;

		frm.method.value = "detailLawNotice";
		frm.target = "_self";
		frm.action = "<c:url value='/las/board/notice.do' />";

		viewHiddenProgress(true) ;
		frm.submit();
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
			<h3><spring:message code="clm.page.title.decidearbitrarilyre.mainTitleMgmt" /></h3>
		</div>
		<!-- //title -->
		
		<!-- content -->
		<div id="content">
		<div id="content_in">
			<!-- ************************ Form Setting ********************************** -->
			<form:form name="frm" id="frm" method="post" autocomplete="off">
			<input type="hidden" name="method"   	 	value="" />
			<input type="hidden" name="menu_id"  	 	value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="curPage"  	 	value="<c:out value='${command.curPage}'/>" />
			<input type="hidden" name="srch_word"  		value="<c:out value='${command.srch_word}'/>" />
			<input type="hidden" name="srch_combo" 		value="<c:out value='${command.srch_combo}'/>" />
			<input type="hidden" name="srch_start_dt" 	value="<c:out value='${command.srch_start_dt}'/>" />
			<input type="hidden" name="srch_end_dt" 	value="<c:out value='${command.srch_end_dt}'/>" />
			
			<input type="hidden" name="annce_knd" id="annce_knd" value="<c:out value='${command.annce_knd}'/>" />
			<input type="hidden" name="seqno"     id="seqno"     value="<c:out value='${command.seqno}'/>" />
			<!-- 첨부파일정보 -->
			<input type="hidden" name="fileInfos" 		value="" />
			<input type="hidden" name="file_bigclsfcn" 	value="F007" />
			<input type="hidden" name="file_midclsfcn" 	value="<c:out value='${command.annce_knd}'/>" />
			<input type="hidden" name="file_smlclsfcn" 	value="" />
			<input type="hidden" name="ref_key" 		value="<c:out value='${command.seqno}'/>" />
			<input type="hidden" name="view_gbn" 		value="modify" />
			<!-- 나모 웹 에디터 관련 -->
<%-- 			<input type="hidden" name="body_mime" id="body_mime" value="<c:out value='${lom.cont}' />" /> --%>
			<input type="hidden" name="body_mime_en" id="body_mime_en" value="<c:out value='${lom.cont_en}' />" />
			<!-- ************************ Form Setting ********************************** -->
		
			<!-- Function Button  -->
			<div class="btnwrap">
				<span class="btn_all_w"><span class="save"></span><a href="javascript:save()"><spring:message code='las.page.button.save' /></a></span>
<%-- 				<span class="btn_all_w"><span class="cancel"></span><a href="javascript:cancel()"><spring:message code='las.page.button.cancel' /></a></span> --%>
			</div>
			<!-- //Function Button  -->
		
			<!--  view  -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
					<col width="10%"/>
					<col width="*"/>
					<col width="10%"/>
					<col width="40%"/>
				</colgroup>
				<tbody>
<!-- 					<tr> -->
<%-- 			            <th><spring:message code='las.page.field.board.notice.titleKr' /></th> --%>
<!-- 			            <td colspan="3"> -->
<%-- 			            	<span><input type="text" name="title" id="title" class="text" style="width:600px;" maxLength="128" value="<c:out value='${lom.title}' />" alt="<spring:message code='las.page.field.board.notice.title' />" maxLength="64" required/></span> --%>
<!-- 			            </td> -->
<!-- 		        	</tr> -->
		        	<tr>
			            <th><spring:message code='las.page.field.board.notice.title' /></th>
			            <td colspan="3">
			            	<span><input type="text" name="title_en" id="title_en" class="text_full" maxLength="128" value="<c:out value='${lom.title_en}' />" alt="<spring:message code='las.page.field.board.notice.title' />" maxLength="64" required/></span>
			            </td>
		        	</tr>
					<tr>
						<th><spring:message code='las.page.field.board.notice.reg_nm' /></th>
						<td>
							<c:choose>
								<c:when test="${lom.mod_nm=='' || lom.mod_nm==null}"><c:out value='${lom.reg_nm}' default="" /></c:when>
								<c:otherwise><c:out value='${lom.mod_nm}' default="" /></c:otherwise>
							</c:choose>							
<%-- 							<c:out value='${lom.reg_nm}' default="" /> --%>
						</td>
						<th><spring:message code='las.page.field.board.notice.reg_dt' /></th>
						<td>
							<c:choose>
								<c:when test="${lom.mod_dt=='' || lom.mod_dt==null}"><c:out value='${lom.reg_dt}' default="" /></c:when>
								<c:otherwise><c:out value='${lom.mod_dt}' default="" /></c:otherwise>
							</c:choose>
<%-- 							<c:out value='${lom.reg_dt}' default="" /> --%>
						</td>
					</tr>
					
<!-- 					<tr> -->
<%-- 						<th><spring:message code="clm.page.msg.common.content"/></th> --%>
<!-- 						<td colspan="3"> -->
<%-- 							<%@ include file="/WEB-INF/jsp/secfw/common/NamoEditInclude.jspf"%> --%>
<!-- 						</td> -->
<!-- 					</tr> -->
					<tr>
						<th><spring:message code="clm.page.msg.common.content"/></th>
						<td colspan="3">
						   <script type="text/javascript" language="javascript">
						   
							
							    var CrossEditor = new NamoSE('wec');
							    CrossEditor.params.Width = "100%";
								CrossEditor.params.UserLang = "enu";
								CrossEditor.params.FullScreen = false;
								CrossEditor.ShowTab(false);
								CrossEditor.UserToolbar = true;
								CrossEditor.params.CreateToolbar = "newdoc|saveas|print|spacebar|undo|redo|cut|copy|paste|pastetext|search|replace|selectall|spacebar|word_style|space|word_color|space|cancelattribute|spacebar|word_justify|space|word_indentset|spacebar|word_listset|spacebar|tableinsert|spacebar|tablerowinsert|tablerowdelete|spacebar|tablecolumninsert|tablecolumndelete|spacebar|tablecellmerge|tablecellsplit|spacebar|tablecellattribute|formatblock|space|fontname|space|fontsize|space|lineheight|spacebar|fullscreen";


	        					CrossEditor.EditorStart();
	        					//CrossEditor.params.ImageSavePath = NamoAttachUrl + "&imagePhysicalPath=" + NamoAttachSaveDir;
	        					
	        					//CrossEditor.params.UploadFileExecutePath = "http://127.0.0.1/crosseditor/websource/jsp/ImageUpload.jsp";
	        					
	        					
	        			
	        					function OnInitCompleted(e){
	        						CrossEditor.SetValue(document.frm.body_mime_en.value);
	        					}
	        					
                            </script>  
							<%//@ include file="/WEB-INF/jsp/secfw/common/NamoEditInclude.jspf"%>
						</td>
					</tr>
					<tr>
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
<%-- 				<span class="btn_all_w"><span class="cancel"></span><a href="javascript:cancel()"><spring:message code='las.page.button.cancel' /></a></span> --%>
			</div>
			<!-- //Function Button  -->
			</form:form>
		</div>
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
<SCRIPT language="javascript" FOR="wec" EVENT="OnInitCompleted()">
// 	var wecObj0 = document.wec[0];
// 	wecObj0.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
// 	wecObj0.SetDefaultFontSize("9");
// 	wecObj0.EditMode = 1;
// 	wecObj0.Value = document.frm.body_mime.value; //namo 에 값 셋팅
	
	var wecObj1 = document.wec;
	wecObj1.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
	wecObj1.SetDefaultFontSize("9");
	wecObj1.EditMode = 1;
	wecObj1.Value = document.frm.body_mime_en.value; //namo 에 값 셋팅
</SCRIPT>
</html>