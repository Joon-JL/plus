<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@page import="com.sds.secframework.common.util.PageUtil"%>
<%@page import="com.sds.secframework.common.util.DateUtil"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%@page import="com.sds.secframework.common.util.Token"%>


<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.main.title" /></title>
<LINK href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet">
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->

<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/CommonProgress.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/ui.core.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script type="text/javascript" src="/crosseditor/js/namo_scripteditor.js"></script>
<script language="javascript">
	function init(){
		alertMessage('<c:out value='${command.return_message}'/>') ;
	}

	function goList() {
		viewHiddenProgress(true) ;
		var f = document.frm ;
		f.method.value = "listSpeakConsult" ;
		f.action = "<c:url value='/las/lawconsulting/speakConsult.do' />" ;
		f.target = "_self" ;
		f.submit() ;
	}
	
	function refresh(){
		viewHiddenProgress(true) ;
		var f = document.frm ;
		f.reset() ;

		f.method.value = f.seqno.value == -1 ? "forwardInsert" : "forwardModify";
		f.action = "<c:url value='/las/lawconsulting/speakConsult.do' />" ;
		f.target = "_self" ;
		f.submit() ;
	}
	
	function save() {
		var f = document.frm ;
		
		if(f.reqman_id.value == ""){
			alert("<spring:message code='las.page.field.lawconsulting.chooseReq'/>");
			return;
		}
		
		if(validateForm(document.frm)) {		
			var confirmMessage = f.seqno.value == -1 ? "<spring:message code='secfw.msg.ask.insert' />" : "<spring:message code='secfw.msg.ask.update' />" ;
			if(confirm(confirmMessage)){
				viewHiddenProgress(true) ;
				
				fileList.UploadFile(function(uploadCount){
	                //첨부파일 업로드 결과 잘못된 형식의 파일이 올라간 경우
	                if(uploadCount == -1){
	                    initPage(); //첨부파일창 Reload
	                    alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
	                    viewHiddenProgress(false) ;
	                    return;
	                }
	                
	               	f.method.value = f.seqno.value == -1 ? "insertSpeakConsult" : "modifySpeakConsult";
	               	f.action = "<c:url value='/las/lawconsulting/speakConsult.do' />" ;
					f.target = "_self" ;
					//나모웹에디터 관련
					f.body_mime.value = CrossEditor.GetBodyValue('wec');
	                
	            	frm.submit();   
                
            	});
			}
		}
	}
	
	function initPage(){
	    frm.target			= "fileList";
	    frm.action = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value  	= "forwardClmsFilePage";
		
		frm.submit();	
	}	

	/**
     * ESB 임직원 조회
	 */
	function searchEmployee() {
	    var frm = document.frm;
	    frm.srch_user_cntnt.value = frm.reqman_nm_show.value;
	    var srchValue = comTrim(frm.srch_user_cntnt.value); //입력받은 값

	    if (srchValue == "" || getByteLength(srchValue) < 4) { //최소 2자리 이상 입력받게 한다.
	        alert('<spring:message code='secfw.msg.error.nameMinByte' />');
	        frm.reqman_nm_show.focus();
	        return;
	    }

	    PopUpWindowOpen('', 800, 450, true);
	    frm.target = "PopUpWindow";

	    frm.srch_user_cntnt_type.value = "userName"; //이름으로 검색  - 보통 이름으로 검색함
	    //frm.srch_user_cntnt_type.value = "userId"; //마이싱글아이디로 검색(tb_com_user의 user_id 아님, tb_com_user의 single_id 임)
	    //frm.srch_user_cntnt_type.value = "epid"; //EPID로 검색(tb_com_user의 user_id임)

	    frm.action = "<c:url value='/secfw/esbOrg.do' />";
	    frm.method.value = "listEsbEmployee";
	    frm.submit();

	    frm.target = "";
	}
	
	function setUserInfos(obj) {
		var f = document.frm;
	    var userInfo = obj;
	    
	    f.reqman_nm.value = userInfo.cn;	//의뢰자 이름
	    f.reqman_nm_show.value = "";	//의뢰자 이름
	    f.reqman_id.value = userInfo.epid; //의뢰자 id
	    f.reqman_dept_nm.value = userInfo.department; //의뢰자 부서
	    f.reqman_dept.value = userInfo.departmentnumber; //의뢰자 부서
	  	f.reqman_mail.value = userInfo.mail;
	}
</script>

</head>
<!--  2012.04.26 Alert 수정 modified by hanjihoon  -->
<body onLoad="initPage();">
<div id ="wrap">
<!-- container -->
<div id="container">
	<!-- Location -->
        <div class="location">
            <img SRC="<%=IMAGE%>/icon/ico_home.gif"border="0" alt="Home"/><c:out value='${menuNavi}' escapeXml="false"/>
        </div>
    <!-- //Location -->
    
    <div class="title">
		<h3><spring:message code="las.page.title.lawconsulting.SpeakConsult" /></h3>
	</div> 	
	
	<!-- button -->
    <div class="t_titBtn">
       	<div class="btn_wrap">
			<span class="btn_all_w"><span class="edit"></span><a href="javascript:save();"><spring:message code="las.page.button.save" /></a></span>
    		<span class="btn_all_w"><span class="save"></span><a href="javascript:goList();"><spring:message code="las.page.button.cancel" /></a></span>
		</div>
	</div>
	
	<!-- content -->
	<div id="content">
	<div id="content_in">
	
		<form:form name="frm" id="frm" autocomplete="off">
		<input type="hidden" name="method" value=""/>
		<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>"/>
		<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>"/>
		<input type="hidden" name="srch_title" value="<c:out value='${command.srch_title}'/>">
		<input type="hidden" name="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}'/>">
		<input type="hidden" name="srch_respman_id" value="<c:out value='${command.srch_respman_id}'/>">
		<input type="hidden" name="srch_start_ymd" value="<c:out value='${command.srch_start_ymd}'/>">
		<input type="hidden" name="srch_end_ymd" value="<c:out value='${command.srch_end_ymd}'/>">
		<input type="hidden" name="row_cnt" value="<c:out value='${command.row_cnt}'/>">
		<input type="hidden" name="seqno" value="<c:out value='${command.seqno}'/>"/>
		<input type="hidden" name="srch_user_cntnt" value=""/>
		<input type="hidden" name="srch_user_cntnt_type" value=""/>
		<input type="hidden" name="status" value=""/>
		<input type="hidden" name="reqman_id" value="<c:out value='${command.reqman_id}'/>"/>
		<input type="hidden" name="reqman_dept" value="<c:out value='${command.reqman_dept}'/>"/>
		<input type="hidden" name="isForeign" value="<c:out value='${command.isForeign}'/>"/>
		<input type="hidden" name="reqman_mail" value=""/>
		
		<!-- 첨부파일정보 -->
		<input type="hidden" name="fileInfos" 	value="" />
		<input type="hidden" name="file_bigclsfcn" 	value="F003" />
		<input type="hidden" name="file_midclsfcn" 	value="F00303" />
		<input type="hidden" name="file_smlclsfcn" 	value="" />
		<input type="hidden" name="ref_key" 	value="<c:out value='${command.seqno}'/>" />
		
		<c:choose>
			<c:when test="${command.seqno == -1 }">
				<input type="hidden" name="view_gbn" 	value="upload" />
				<!-- 나모 웹 에디터 관련 -->
				<input type="hidden" name="body_mime" id="body_mime" value="" />
			</c:when>
			<c:otherwise>
				<input type="hidden" name="view_gbn" 	value="modify" />
				<!-- 나모 웹 에디터 관련 -->
				<input type="hidden" name="body_mime" id="body_mime" value="<c:out value='${command.re_cont}'/>" />
			</c:otherwise>		
		</c:choose>
		
		<!-- 이중등록 방지 -->
		<input type="hidden" name="TOKEN"  value="<%=Token.set(request) %>" />
 
		<!-- view -->
		<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
			<colgroup>
				<col width="13%"/>
				<col width="37%"/>
				<col width="13%"/>
				<col width="37%"/>
			</colgroup>
			<tbody>
				<tr>
					<th><spring:message code="las.page.field.speakcontract.reg_dt" /></th>
					<td><%=DateUtil.formatDate(DateUtil.today(), "-")%></td>
					<th><spring:message code="las.page.field.speakcontract.respman_nm" /></th>
					<td>
						<c:out value='${command.respman_nm}'/> 
					</td>
				</tr>
				<tr>
				 	<th><spring:message code="las.page.field.speakcontract.reqman_nm" /></th>
					<td>
						<input name="reqman_nm_show" id="reqman_nm_show" type="text" value=""  alt="<spring:message code="las.page.field.lawconsulting.requClient"/>" required="*"  class="text_search" maxLength="100" onkeydown="if(event.keyCode==13){event.returnValue = false;javascript:searchEmployee();}"><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchEmployee();" class="cp" alt="search"/>
						<input name="reqman_nm" id="reqman_nm" type="text" value="<c:out value='${command.reqman_nm}' escapeXml='false'/>" readonly="readonly" style="width: 200px;border:0px;">
					</td>
					<th><spring:message code="las.page.field.speakcontract.reqman_dept_nm" /></th>
					<td><input name="reqman_dept_nm" id="reqman_dept_nm" type="text" value="<c:out value='${command.reqman_dept_nm}' escapeXml='false'/>" readonly="readonly" style="width: 200px;border:0px;"></td>
				</tr>
				
<!-- 				<tr> -->
<%-- 					<th><spring:message code="las.page.field.speakcontract.send_mailyn" /></th> --%>
<!-- 					<td colspan="3"> -->
<%-- 					<spring:message code="las.page.field.lawconsult.yes" /><input type="radio" name="send_mailyn" value="Y" <c:if test="${command.send_mailyn=='Y'}"> checked="checked"</c:if>/> --%>
<%-- 					<spring:message code="las.page.field.lawconsult.no2" /><input type="radio" name="send_mailyn" value="N" <c:if test="${command.send_mailyn=='N'}"> checked="checked"</c:if>/> --%>
<!-- 					</td> -->
<!-- 				</tr> -->
				
				<tr>
					<th><spring:message code="las.page.field.speakcontract.title" /></th>
					<td colspan="3"><input name="title" id="title" type="text" value="<c:out value='${command.title}' escapeXml='false'/>" class="text_full" alt="<spring:message code="las.page.field.lawconsulting.title"/>" required="*" maxLength="128"/></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.speakcontract.re_cont" /></th>
					<td colspan="3">
					
						<script type="text/javascript" language="javascript">
											
							var CrossEditor = new NamoSE('wec');
							CrossEditor.params.Width = "100%";
							CrossEditor.params.UserLang = "enu";
							CrossEditor.params.FullScreen = false;
							CrossEditor.ShowTab(false);
							CrossEditor.params.CreateToolbar = "newdoc|saveas|print|spacebar|undo|redo|cut|copy|paste|pastetext|search|replace|selectall|spacebar|word_style|space|word_color|space|cancelattribute|spacebar|word_justify|space|word_indentset|spacebar|word_listset|spacebar|tableinsert|spacebar|tablerowinsert|tablerowdelete|spacebar|tablecolumninsert|tablecolumndelete|spacebar|tablecellmerge|tablecellsplit|spacebar|tablecellattribute|formatblock|space|fontname|space|fontsize|space|lineheight|spacebar|fullscreen";			
							CrossEditor.EditorStart();
							  					
							function OnInitCompleted(e){
								CrossEditor.SetValue(document.frm.body_mime.value);
							}
							
						</script> 
					</td>
				</tr>
				<tr class="end">
	            	<th><spring:message code="las.page.field.lawconsult.attachfile" /><img src="/images/las/ko/common/step_q.gif" alt="info" title="DOC, DOCX, GUL, PDF, OCX, XLS, XLSX, PPT, PPTX,&#13;JPG, HWP, TIF, RTF, ZIP, PTX, TML, TXT, LSX, EML, BMP, GIF, MSG, ALZ, DOT &#13;&#13;<spring:message code='las.msg.com.fileFilter'/>" /></th>
					<td colspan="3">
						<div class='ico_maxsize fR'>Max Size : <span>10MB</span></div>
						<!-- Sungwoo Replaced scroll option 2014/08/04 -->
						<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>
					</td>
	          	</tr>
			</tbody>
		</table>
		<!-- //view -->
		<!-- button -->
		<div class="t_titBtn">
        	<div class="btn_wrap">
				<span class="btn_all_w"><span class="edit"></span><a href="javascript:save();"><spring:message code="las.page.button.save" /></a></span>
	    		<span class="btn_all_w"><span class="save"></span><a href="javascript:goList();"><spring:message code="las.page.button.cancel" /></a></span>
			</div>
		</div>
		<!-- //button -->
	</form:form>
	</div>
	</div>
	<!-- //content -->
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</div>
<!-- //container -->
</div>
<jsp:include page="/WEB-INF/jsp/secfw/common/DlgAlert.jsp"/>

<!-- footer  -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
	<!-- // footer -->
</body>

<SCRIPT language="javascript" FOR="wec" EVENT="OnInitCompleted()">
	
</SCRIPT>
</html>
