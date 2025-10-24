<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%--
/**
 * 파  일  명 : Qna_u.jsp
 * 프로그램명 : QNA 수정
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
 --%>
<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	ListOrderedMap lom = (ListOrderedMap)request.getAttribute("lom"); 
	
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<link href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet" />

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>

<script language="javascript">

	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/counsel/qna.do' />";
		//저장
		if(flag == "insert"){
			if(confirm("<spring:message code='secfw.msg.ask.update' />")){
				
			    frm.method.value = "modifyQna";
			    
			    //나모웹에디터 관련
			    frm.cont.value = frm.wec.MIMEValue;
				
			    //Validation Check2
			    if(validateForm(frm)){
			    	//첨부파일 업로드 실행
					fileList.UploadFile(function(uploadCount){
						if(uploadCount == -1){
							initPage();
							alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
							return;
						}
						viewHiddenProgress(true) ;
				    	frm.submit();	
					});
			    }
			}
			
		//초기화
		} else if(flag == "cancel"){	
			if(confirm("<spring:message code='secfw.msg.ask.cancel' />")){
				frm.seqno.value  = "0"; 
			    frm.method.value = "listQna";
			    viewHiddenProgress(true);
				frm.submit();			
			}
		}
	}
	
	$(document).ready(function(){
		initPage();
	});
	
	function initPage(){
	    frm.target			= "fileList";
	    frm.action 			= "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value  	= "forwardClmsFilePage";
		frm.submit();	
	}

</script>

</head>
<body onLoad="alertMessage('<c:out value='${command.return_message}'/>')" autocomplete="off">
<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id="frm" method="post" autocomplete="off">

<!-- search form -->
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />

<!-- key form-->
<input type="hidden" name="seqno"	     value="<c:out value='${command.seqno}'/>" />
<input type="hidden" name="up_seqno"	 value="<c:out value='${command.up_seqno}'/>" />
<input type="hidden" name="postup_srt"	 value="<c:out value='${command.postup_srt}'/>" />
<input type="hidden" name="postup_depth" value="<c:out value='${command.postup_depth}'/>" />
<input type="hidden" name="cont"	     value="<c:out value='${lom.cont}' />" />

<!-- 첨부파일정보 -->
<input type="hidden" name="fileInfos" 	value="" />
<input type="hidden" name="file_bigclsfcn" 	value="F009" />
<input type="hidden" name="file_midclsfcn" 	value="" />
<input type="hidden" name="file_smlclsfcn" 	value="" />
<input type="hidden" name="ref_key" 	value="<c:out value='${command.seqno}'/>" />
<input type="hidden" name="view_gbn" 	value="modify" />

<!-- // **************************** Form Setting **************************** -->
<div id="wrap">
	<!-- container -->
	<div id="container">
	    <!-- Location -->
		<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->	
		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.title.qna.modifyTitle" /></h3>
		</div>
		<!-- //title -->
		
		<div class="btn_wrap">
				<span class="btn_all_b"><span class="save"></span><a href="javascript:pageAction('insert');"><spring:message code="secfw.page.button.insert" /></a></span>
				<span class="btn_all_w"><span class="cancel"></span><a href="javascript:pageAction('cancel');"><spring:message code="secfw.page.button.cancel" /></a></span>
		</div>
		
		<!-- content -->
		<div id="content">
			<!-- view -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
					<col class="tal_w04" />
					<col />
					<col class="tal_w04" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th><spring:message code="clm.page.field.qna.title" /></th>
						<td colspan=3>
							<input type="text" name="title" id="title" value="<c:out value='${lom.title}'/>" required alt="<spring:message code="clm.page.field.qna.title" />" maxLength="256" class="text_full" style="width:600px" />
						</td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.qna.regNm" /></th>
						<td><c:out value="${lom.reg_nm}" /></td>
						<th><spring:message code="clm.page.field.qna.regDt" /></th>
						<td><c:out value="${lom.reg_dt}" /></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.qna.cont" /><span class="astro">*</span></th>
						<td colspan="3">
							<%@ include file="/WEB-INF/jsp/secfw/common/NamoEditInclude.jspf"%>
						</td>
					</tr>
					<tr class="end">
		            	<th><spring:message code="secfw.page.field.bbs.file" /></th>
		            	<td colspan="3">
		            	<iframe src="<c:url value='/clm/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="70px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
		            	</td>
		          	</tr>
				</tbody>
			</table>
			<!-- //view -->
	
		</div>
		<!-- //content -->
		<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- // footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>	
</form:form>
</body>
<!-- 나모 웹에디터 관련 추가 -->
<SCRIPT language="javascript" FOR="wec" EVENT="OnInitCompleted()">
	var wecObj = document.wec;
	wecObj.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
	wecObj.SetDefaultFontSize("9");
	wecObj.EditMode = 1;	
	wecObj.Value = document.frm.cont.value;
</SCRIPT>
</html>
