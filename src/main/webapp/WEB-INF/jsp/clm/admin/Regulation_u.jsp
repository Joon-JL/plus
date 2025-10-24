<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%--
/**
 * 파  일  명 : Regulation_u.jsp
 * 프로그램명 : 프로세스 & 규정 지침서 수정
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
<%@ include file="/WEB-INF/jsp/common/messageUtil.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="las.main.title" /></title>
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<LINK href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">

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
		frm.action = "<c:url value='/clm/admin/regulation.do' />";
		//저장
		if(flag == "insert"){
			if(confirm("<spring:message code='secfw.msg.ask.update' />")){
				
			    frm.method.value = "modifyRegulation";
			    
			    //나모웹에디터 관련
// 			    frm.rule_cont.value = frm.wec[0].MIMEValue;
// 			    frm.rule_cont_en.value = frm.wec[1].MIMEValue;
				
			    //Validation Check2
			    if(validateForm(frm)){
	
			    	viewHiddenProgress(true);
			    	frm.submit();	
			    }
			}
			
		//초기화
		} else if(flag == "cancel"){	
			if(confirm("<spring:message code='secfw.msg.ask.cancel' />")){
				frm.rule_no.value  = "0";
			    frm.method.value = "listRegulation";
			    viewHiddenProgress(true);
				frm.submit();			
			}
		}
	}
	
	$(document).ready(function(){
		//첨부파일창 load
		initPage();
	});
	
	function initPage(){
// 	    frm.target = "fileList";
// 	    frm.action = "<c:url value='/clms/common/clmsfile.do' />";
// 	    frm.method.value = "forwardClmsFilePage";
// 		frm.submit();
	
	}

</script>

</head>
<body>

<div id="wrap">
	<!-- container -->
	<div id="container">
	    <!-- Location -->
		<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->	
		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.title.regulation.listTitle" /></h3>
		</div>
		<!-- //title -->
		<!-- content -->
		<div id="content">
		<div id="content_in">
		<!-- **************************** Form Setting **************************** -->
			<form:form name="frm" id="frm" method="post" autocomplete="off">
			
			<!-- search form -->
			<input type="hidden" name="method"   value="" />
			<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
			
			<!-- key form-->
			<input type="hidden" name="rule_no"	     value="<c:out value='${command.rule_no}'/>" />
<%-- 			<input type="hidden" name="rule_cont"	 value="<c:out value='${lom.rule_cont}' />" /> --%>
<%-- 			<input type="hidden" name="rule_cont_en"	 value="<c:out value='${lom.rule_cont_en}' />" /> --%>
			
			<!-- 첨부파일정보 -->
			<input type="hidden" name="fileInfos" 	value="" />
			<input type="hidden" name="file_bigclsfcn" 	value="F015" />
			<input type="hidden" name="file_midclsfcn" 	value="" />
			<input type="hidden" name="file_smlclsfcn" 	value="" />
			<input type="hidden" name="ref_key" 	value="<c:out value='${command.rule_no}'/>" />
			<input type="hidden" name="view_gbn" 	value="modify" />

<!-- // **************************** Form Setting **************************** -->
			
			<div class="btnwrap">
				<span class="btn_all_w"><span class="save"></span><a href="javascript:pageAction('insert');"><spring:message code="secfw.page.button.insert" /></a></span>
				<span class="btn_all_w"><span class="cancel"></span><a href="javascript:pageAction('cancel');"><spring:message code="secfw.page.button.cancel" /></a></span>
			</div>
			
			<!-- view -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
					<col width="10%">
					<col width="90%">
				</colgroup>
				<tbody>
<!-- 					<tr> -->
<%-- 						<th><spring:message code="clm.page.field.board.titleKr" /></th> --%>
<%-- 						<td><c:out value="${lom.rule_title}" /></td> --%>
<!-- 					</tr> -->
					<tr>
						<th><spring:message code="clm.page.field.board.title" /></th>
						<td><c:out value="${lom.rule_title_en}" /></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.regulation.regNm" /></th>
						<td><c:out value="${lom.reg_nm}" /></td>
					</tr>
<!-- 					<tr> -->
<%-- 						<th><spring:message code="clm.page.msg.common.content" /></th> --%>
<!-- 						<td colspan="3"> -->
<%-- 							<%@ include file="/WEB-INF/jsp/secfw/common/NamoEditInclude.jspf"%> --%>
<!-- 						</td> -->
<!-- 					</tr> -->
					<tr class="end">
						<th><spring:message code="clm.page.field.board.cont" /></th>
						<td colspan="3">
							<span id="rule_cont_en">0</span>/<spring:message code="clm.page.msg.manage.stringLen2000" /><br>
						    <textarea id="rule_cont_en" name="rule_cont_en" cols="100%" rows="10" maxLength="2000" onkeyup="frmChkLenMe(this,2000,'rule_cont_en')" class="text_area_full"><c:out value="${lom.rule_cont_en}" escapeXml="" /></textarea>
							<%//@ include file="/WEB-INF/jsp/secfw/common/NamoEditInclude.jspf"%>
						</td>
					</tr>
<!-- 		          	<tr class="end"> -->
<%-- 		            	<th><spring:message code='secfw.page.field.bbs.file' /></th> --%>
<!-- 		            	<td colspan="3"> -->
<%-- 		            		<iframe src="<c:url value='/clm/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="100px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe> --%>
<!-- 		            	</td> -->
<!-- 		          	</tr> -->
				</tbody>
			</table>
			<!-- //view -->
	
			<div class="btnwrap mt20">
				<span class="btn_all_w"><span class="save"></span><a href="javascript:pageAction('insert');"><spring:message code="secfw.page.button.insert" /></a></span>
				<span class="btn_all_w"><span class="cancel"></span><a href="javascript:pageAction('cancel');"><spring:message code="secfw.page.button.cancel" /></a></span>
			</div>
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


<SCRIPT language="javascript" FOR="wec" EVENT="OnInitCompleted()">
	var wecObj0 = document.wec[0];
	wecObj0.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
	wecObj0.SetDefaultFontSize("9");
	wecObj0.EditMode = 1;
	wecObj0.Value = document.frm.rule_cont.value; //namo 에 값 셋팅
	
	var wecObj1 = document.wec[1];
	wecObj1.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
	wecObj1.SetDefaultFontSize("9");
	wecObj1.EditMode = 1;
	wecObj1.Value = document.frm.rule_cont_en.value; //namo 에 값 셋팅
</SCRIPT>


</body>
</html>
