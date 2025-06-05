<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%--
/**
 * 파  일  명 : Regulation_i.jsp
 * 프로그램명 : 프로세스 & 규정 지침서 등록
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
 --%>
<%

	//나모에디터 스트링
	String wec_set_id = ""; 
	String wec_mode = ""; 
	String wec_com_script_yn = "";
	
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");

%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%@ include file="/WEB-INF/jsp/common/messageUtil.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet" />

<!-- style -->
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>

<script language="javascript">

	$(document).ready(function(){
	
		initPage();

		document.getElementById("one_rule_title").innerHTML = "<input type='text' id='one_rule_title_input' name='one_rule_title_input' value='' maxLength='100' class='text_full' style='width:310px' />";
		
    /*    frm.target          = "fileList";
        frm.action = "<c:url value='/clms/common/clmsfile.do' />";
        frm.method.value    = "forwardClmsFilePage";

        frm.submit(); */
	});
	
	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		var frm = document.frm;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/admin/regulation.do'/>";
		if(flag == "insert"){
			if(confirm("<spring:message code='secfw.msg.ask.insert' />")){
				if(frm.srch_rule_choice.value == "up"){//상위 selectbox 최상위 선택 시 null 체크.
				    if(frm.one_rule_title_input.value == ""){
				    	alert("<spring:message code='clm.msg.alert.regulation.upNoTitle' />");
				    	frm.one_rule_title_input.focus();
				    	return;
				    }	
				}
				
				if(frm.rule_title_en.value == ""){//제목  null 체크.
					alert("<spring:message code='clm.msg.alert.regulation.noTitle' />");
					frm.rule_title_en.focus();
					return;
				}
				
				frm.method.value = "insertRegulation";
			    
			    //나모웹에디터 관련
// 			    frm.rule_cont.value = frm.wec.MIMEValue;
// 			    frm.rule_cont_en.value = frm.wecEn.MIMEValue;
			    frm.submit();
			    
			    //Validation Check2
// 			    if(validateForm(frm)){
// 			    	//첨부파일 업로드 실행
// 					fileList.UploadFile(function(uploadCount){
// 						if(uploadCount == -1){
// 							initPage();
// 							alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
// 							return;
// 						}
// 						viewHiddenProgress(true) ;
// 				    	frm.submit();
// 					});
// 			    }
			}
		}else if(flag == "cancel"){
			if(confirm("<spring:message code='secfw.msg.ask.cancel' />")){
			    frm.method.value = "listRegulation";
			    viewHiddenProgress(true);
				frm.submit();
			}
		}
	}
	
	function initPage(){
// 		var frm = document.frm;
// 	    frm.target			= "fileList";
// 	    frm.action 			= "<c:url value='/clms/common/clmsfile.do' />";
// 	    frm.method.value  	= "forwardClmsFilePage";
// 		frm.submit();	
	}
	
	//상위 selectbox 변경 시
	function ruleChoiceChg(val){
		if(val == "up"){
			document.getElementById("one_rule_title").innerHTML = "<input type='text' id='one_rule_title_input' name='one_rule_title_input' value='' maxLength='100' class='text_full' style='width:310px' />";
		}else{
			document.getElementById("one_rule_title").innerHTML = "";
		}
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
			<input type="hidden" name="method"       value="" />
			<input type="hidden" name="menu_id"      value="<c:out value='${command.menu_id}'/>" />
			<!-- key form-->
			<input type="hidden" name="rule_title" value="<c:out value='${command.rule_title_en}'/>" />
			<input type="hidden" name="rule_cont" value="" />
<!-- 			<input type="hidden" name="rule_cont_en" value = ""/> -->
			<!-- 첨부파일정보 -->
			<input type="hidden" name="fileInfos" 	    value="" />
			<input type="hidden" name="file_bigclsfcn" 	value="F015" />
			<input type="hidden" name="file_midclsfcn" 	value="" />
			<input type="hidden" name="file_smlclsfcn" 	value="" />
			<input type="hidden" name="ref_key" 	    value="<c:out value='${command.rule_no}'/>" />
			<input type="hidden" name="view_gbn" 	    value="upload" />
			<!-- // **************************** Form Setting **************************** -->

			<div class="btnwrap ">
				<span class="btn_all_w"><span class="save"></span><a href="javascript:pageAction('insert');"><spring:message code="secfw.page.button.insert" /></a></span>
				<span class="btn_all_w"><span class="cancel"></span><a href="javascript:pageAction('cancel');"><spring:message code="secfw.page.button.cancel" /></a></span>
			</div>
			
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
				        <th><spring:message code="clm.page.field.regulation.upRuleNo" /><span class="astro">*</span></th>
						<td colspan="3">
							<select id="srch_rule_choice" name="srch_rule_choice" onChange="ruleChoiceChg(this.value);">
							    <option value="up"><spring:message code="clm.page.msg.admin.mostSignificant" /></option>
							    <c:forEach var="list" items="${upRuleList}">
							        <option value="<c:out value='${list.rule_no}'/>"><c:out value='${list.rule_title}'/></option>
							    </c:forEach>
							</select>
							<span id="one_rule_title">
							</span>
						</td>
				    </tr>
<!-- 					<tr> -->
<%-- 						<th><spring:message code="clm.page.field.board.titleKr" /><span class="astro">*</span></th> --%>
<!-- 						<td colspan="3"> -->
<%-- 							<input type="text" name="rule_title" id="rule_title" value="<c:out value='${command.rule_title}'/>" required alt="<spring:message code="clm.page.field.regulation.ruleTitle" />" maxLength="100" class="text_full" style="width:600px" /> --%>
<!-- 						</td> -->
<!-- 					</tr> -->
					<tr>
						<th><spring:message code="clm.page.field.board.title" /><span class="astro">*</span></th>
						<td colspan="3">
							<input type="text" name="rule_title_en" id="rule_title_en" value="<c:out value='${command.rule_title_en}'/>" required alt="<spring:message code="clm.page.field.regulation.ruleTitle" />" maxLength="100" class="text_full" style="width:600px" />
						</td>
					</tr>
<!-- 					<tr> -->
<%-- 						<th><spring:message code="clm.page.msg.common.content" /></th> --%>
<!-- 						<td colspan="3"> -->
<%-- 							<% 
// 								request.setAttribute("wec_set_id","wec"); 
// 			  					request.setAttribute("wec_mode","EDIT");	
// 			  					request.setAttribute("wec_com_script_yn","Y");
<%-- 							%> --%>
<%-- 							<%@ include file="/WEB-INF/jsp/secfw/common/NamoAutoInclude.jspf"%> --%>
<!-- 						</td> -->
<!-- 					</tr> -->
					<tr class="end">
						<th><spring:message code="clm.page.field.board.cont" /></th>
						<td colspan="3">
							<span id="rule_cont_en">0</span>/<spring:message code="clm.page.msg.manage.stringLen2000" /><br>
						    <textarea id="rule_cont_en" name="rule_cont_en" cols="100%" rows="10" maxLength="2000" onkeyup="frmChkLenMe(this,2000,'rule_cont_en')" class="text_area_full"></textarea>
<%-- 							<% 
// 								request.setAttribute("wec_set_id","wecEn"); 
// 			  					request.setAttribute("wec_mode","EDIT");	
// 			  					request.setAttribute("wec_com_script_yn","Y");
 							%> --%>
<%-- 							<%@ include file="/WEB-INF/jsp/secfw/common/NamoAutoInclude.jspf"%> --%>
						</td>
					</tr>
<!-- 					전사규정은 첨부파일 기능 없음 // 주석처리 -->
<!-- 					<tr class="end"> -->
<%-- 		            	<th><spring:message code="secfw.page.field.bbs.file" /></th> --%>
<!-- 		            	<td colspan="3"> -->
<%-- 		            	<iframe src="<c:url value='/clm/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="150px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe> --%>
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
</body>
<!-- 나모 웹에디터 관련 추가 -->
</html>
