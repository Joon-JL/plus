<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.BoardPageUtil"%>


<%@ page import="com.sds.secframework.common.util.Token"%>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="secfw.page.title.auth.AuthList" /></title>

<link href="<%=CSS%>/clm.css"    type="text/css" rel="stylesheet">
<LINK href="<c:url value='/style/secfw/common.css' />" type="text/css" rel="stylesheet">
<script language="javascript" src="/script/secfw/jquery/jquery.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.form.js"></script>
<script language="javascript" src="/script/secfw/common/CommonProgress.js"></script>
<script language="javascript">

	function init(){
		var f = document.frm;
		var message = "<c:out value='${command.return_message}'/>";

		if(message!=null && message!=""){
			alert(message);
		}
	}

	/* 권한전체를  설정가능한 권한으로 세팅*/
	function allAvailableRole(){
		var f = document.frm;
		var length = f.sel_assigned_role_list.options.length;
		if(f.sel_assigned_role_list == null){
			return;
		}
		
		for(var i=0; length>0; i++){
			f.sel_available_role_list.add(new Option(f.sel_assigned_role_list[0].value, f.sel_assigned_role_list[0].value));
			f.sel_assigned_role_list.options[0] = null;
			length = f.sel_assigned_role_list.options.length ;
		}
	}

	/* 권한전체를 설정된 권한으로 세팅 */
	function allAssignedRole(){
		var f = document.frm;
		var length = f.sel_available_role_list.options.length;

		for(var i=0; length>0; i++){
			f.sel_assigned_role_list.add(new Option(f.sel_available_role_list[0].value, f.sel_available_role_list[0].value));
			f.sel_available_role_list.options[0] = null;
			length = f.sel_available_role_list.options.length ;
		}
	}

	/* 선택한 권한을 설정가능한 권한으로 세팅 */
	function moveAvailableRole(){
		var f = document.frm;
		var length = f.sel_assigned_role_list.options.length;

		for(var i=0; i<length ; i++){
			if(f.sel_assigned_role_list[i].selected){
				f.sel_available_role_list.add(new Option(f.sel_assigned_role_list[i].value, f.sel_assigned_role_list[i].value));
				f.sel_assigned_role_list.options[i] = null;
				i -= 1;
				length -= 1;
			}
		}
	}

	/* 선택한 권한을 설정된 권한으로 세팅 */
	function moveAssignedRole(){
		var f = document.frm;
		var length = f.sel_available_role_list.options.length;

		for(var i=0; i<length ; i++){
			if(f.sel_available_role_list[i].selected){
				f.sel_assigned_role_list.add(new Option(f.sel_available_role_list[i].value, f.sel_available_role_list[i].value));
				f.sel_available_role_list.options[i] = null;
				i -= 1;
				length -= 1;
			}
		}
	}
	
	function goList() {
		viewHiddenProgress(true) ;
		var f = document.frm;
		f.method.value = "listPage";
		f.action = "<c:url value='/secfw/classMethodRole.do' />";
		f.target = "_self";
		f.submit();
	}

	
	/* 등록(삽입) 기능 수행시 초기화 */
	function refresh_insert(){
		viewHiddenProgress(true) ;
		var f = document.frm;
		f.class_nm.value = "";
		f.method_nm.value="";
		f.check_yn.value = "";
		allAvailableRole();
		f.method.value = "forwardInsertForm";
		f.action = "<c:url value='/secfw/classMethodRole.do' />";
		f.target="_self";
		f.submit();
	}

	
	/* 수정 기능 수행시 초기화 */
	function refresh_modify(){
		viewHiddenProgress(true) ;
		var f = document.frm;
		f.method.value = "forwardModifyForm";
		f.action = "<c:url value='/secfw/classMethodRole.do' />";
		f.target="_self";
		f.submit();
	}
	
	function del() {
		
		var f = document.frm;
		var confirmMessage = "<spring:message code='secfw.msg.ask.delete' />";

		if(confirm(confirmMessage)){
			viewHiddenProgress(true) ;
			f.method.value = "delete";
			f.action = "<c:url value='/secfw/classMethodRole.do' />";
			f.target = "_self";
			f.submit();
		}
	}

	function insert() {
		var f = document.frm;
		var confirmMessage = "<spring:message code='secfw.msg.ask.insert' />";

		if(f.class_nm.value == ""){
			alert("<spring:message code='secfw.page.field.auth.inputClassNm'/>");
			viewHiddenProgress(false) ;
			return;
		}
		else if(f.method_nm.value == ""){
			alert("<spring:message code='secfw.page.field.auth.inputMethodNm'/>");
			viewHiddenProgress(false) ;
			return;
		}

		if(f.class_nm.value.length > 200){
			alert("<spring:message code='secfw.page.field.auth.notOver200letterClassNm'/>");
			viewHiddenProgress(false) ;
			return;
		}

		if(f.method_nm.value.length > 100){
			alert("<spring:message code='secfw.page.field.auth.notOver100letters'/>");
			viewHiddenProgress(false) ;
			return;
		}
		
		if(confirm(confirmMessage)){
			viewHiddenProgress(true) ;
			for(var i=0; i<f.sel_assigned_role_list.options.length ; i++){
				if(f.sel_assigned_role_list.options[i].selected == false){
					f.sel_assigned_role_list.options[i].selected = true;
				}
			}
			for(var j=0; j<f.sel_available_role_list.options.length ; j++){
				if(f.sel_available_role_list.options[j].selected == false){
					f.sel_available_role_list.options[j].selected = true;
				}
			}
	
			f.method.value = "insert";
			f.action = "<c:url value='/secfw/classMethodRole.do' />";
			f.target = "_self";
			f.submit();
		}
	}

	function modify() {
		var f = document.frm;
		var confirmMessage = "<spring:message code='secfw.msg.ask.update' />";

		if(confirm(confirmMessage)){
			viewHiddenProgress(true) ;
			for(var i=0; i<f.sel_assigned_role_list.options.length ; i++){
				if(f.sel_assigned_role_list.options[i].selected == false){
					f.sel_assigned_role_list.options[i].selected = true;
				}
			}
			for(var j=0; j<f.sel_available_role_list.options.length ; j++){
				if(f.sel_available_role_list.options[j].selected == false){
					f.sel_available_role_list.options[j].selected = true;
				}
			}
			
			f.method.value = "modify";
			f.action = "<c:url value='/secfw/classMethodRole.do' />";
			f.target = "_self";
			f.submit();
		}
	}

</script>
</head>

<body onLoad="init()">

<div id="wrap">
	<div id="container">
	
		<!-- location -->
		<div class="location"><IMG SRC="<%=IMAGE%>/icon/ico_home.gif" BORDER="0" alt="Home" title="Home"> Home > System > <spring:message code="secfw.page.field.auth.authManage"/> > <span><spring:message code="secfw.page.field.auth.setMethodAuth"/></span></div>
		<!-- //location -->
		
		<!-- title -->
		<div class="title">
			<h3><spring:message code="secfw.page.field.auth.setMethodAuth"/></h3>
		</div>
		<!-- //title -->

	
		<!-- content -->
		<div id="content">
			<div class="newstyle-area">

				<form:form name="frm" id="frm" autocomplete="off">
				<input type="hidden" name="method" value="">
				<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>">
				<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>">
				<input type="hidden" name="delete_type">
				<input type="hidden" name="refresh" value=false>
				<input type="hidden" name="class_srch_word" value="<c:out value='${command.class_srch_word}'/>">
				<input type="hidden" name="method_srch_word" value="<c:out value='${command.method_srch_word}'/>">
				<input type="hidden" name="srch_type" value="<c:out value='${command.srch_type}'/>">
				<input type="hidden" name="available_role_list">
				<input type="hidden" name="assigned_role_list">
				<input type="hidden" name="isModify" value="<c:out value='${command.isModify}'/>">
				
				<!-- 이중등록 방지 -->
				<input type="hidden" name="TOKEN"  value="<%=Token.set(request) %>">

	
	

				<!-- 검색  -->
				<div class="search_box">
					<div class="search_box_in">
						<table class="search_in">
							<tr>
								<td><b><spring:message code="secfw.page.field.auth.classNm"/> :</b></td>
								<c:choose>
									<c:when test="${command.isModify == 'false'}">
										<td><input name="class_nm" id="class_nm" type="text"
								value="<c:out value='${command.class_nm}'/>" onKeyDown="if(event.keyCode==13){search()}" class="text" style="width:80%; ime-mode:disabled;"></td>
									</c:when>
									<c:otherwise>
										<td><input name="class_nm" id="class_nm" type="text"
								value="<c:out value='${command.class_nm}'/>" onKeyDown="if(event.keyCode==13){search()}" class="text" style="width:80%; ime-mode:disabled; background-color:#dddddd" readonly="readonly"></td>
									</c:otherwise>
								</c:choose>
								<td><b><spring:message code="secfw.page.field.auth.methodNm"/> :</b></td>
								<c:choose>
									<c:when test="${command.isModify == 'false'}">
										<td><input name="method_nm" id="method_nm" type="text" value="<c:out value='${command.method_nm}'/>" onKeyDown="if(event.keyCode==13){search()}" class="text" style="width:80%; ime-mode:disabled"></td>
									</c:when>
									<c:otherwise>
										<td>	<input name="method_nm" id="method_nm" type="text" value="<c:out value='${command.method_nm}'/>" onKeyDown="if(event.keyCode==13){search()}" class="text" style="width:80%; ime-mode:disabled; background-color:#dddddd" readonly="readonly"></td>
									</c:otherwise>
								</c:choose>
								<td><b><spring:message code="secfw.page.field.auth.authChk"/>:</b></td>
								<td>
									<select name="check_yn" id="check_yn"	value='$command.check_yn'>
										<option value="Y"
											<c:if test="${command.check_yn=='Y'}"> selected</c:if>>Y</option>
										<option value="N"
											<c:if test="${command.check_yn=='N'}"> selected</c:if>>N</option>
									</select>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<!-- // 검색  -->

				<!-- 버튼 -->
				<div class="bt_content_top">
				<a href="javascript:goList()" class="bt_fn ico_see_all"><span><spring:message code="secfw.page.field.auth.list"/></span></a>
				<c:choose>
					<c:when test="${command.isModify == 'true'}">
						<a href="javascript:refresh_modify()" class="bt_fn ico_config"><span><spring:message code="secfw.page.field.auth.init"/></span></a>
					</c:when>
					<c:otherwise>
						<a href="javascript:refresh_insert()" class="bt_fn ico_config"><span><spring:message code="secfw.page.field.auth.init"/></span></a>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${command.isModify == 'true'}">		
						<a href="javascript:modify()" class="bt_fn ico_register"><span><spring:message code="secfw.page.field.auth.save"/></span></a>
					</c:when>
					<c:otherwise>
						<a href="javascript:insert()" class="bt_fn ico_register"><span><spring:message code="secfw.page.field.auth.registration"/></span></a>
					</c:otherwise>
				</c:choose>
				
				<c:choose>
					<c:when test="${command.isModify == 'true'}">
						<a href="javascript:del()" class="bt_fn ico_delete"><span><spring:message code="secfw.page.field.auth.delete"/></span></a>
					</c:when>
				</c:choose>
				</div>
				<!-- // 버튼 -->
	
				<!-- 권한 설정 테이블 -->
				<table class="basic_list" align="center">
					<colgroup>
						<col width="48%">
						<col width="4%"  >
						<col width="48%">
					</colgroup>
					<tr>
						<th><span class="title"><spring:message code="secfw.page.field.auth.setAvailAuth"/></span></th>
						<th></th>
						<th><span class="title"><spring:message code="secfw.page.field.auth.setupAuth"/></span></th>
					</tr>
					<tr>
						<td><select name="sel_available_role_list" size="5"
							multiple="multiple" style='height:120px;width:100%'>
							<c:forEach var="list" items="${command.available_role_list}">
								<option value="<c:out value='${list.role_cd}'/>"><c:out
									value='${list.role_cd}' /></option>
							</c:forEach>
						</select></td>
						
						<td><img src="<%=IMAGE %>/btn/page-last.gif" onclick="allAssignedRole()"
							style="cursor: hand; margin-bottom: 7px" />
						<img src="<%=IMAGE %>/btn/page-next.gif"
							/ class='schbox' align="absmiddle" onclick="moveAssignedRole()"
							style="cursor: hand; margin-bottom: 7px" />
						<img src="<%=IMAGE %>/btn/page-prev.gif"
							/ class='schbox' align="absmiddle" onclick="moveAvailableRole()"
							style="cursor: hand; margin-bottom: 7px" />
						<img src="<%=IMAGE %>/btn/page-first.gif"
							/ class='schbox' align="absmiddle" onclick="allAvailableRole()"
							style="cursor: hand; margin-bottom: 7px" /></td>
						
						
						<td><select name="sel_assigned_role_list" size="5"
							multiple="multiple" style='height:120px;width:100%'>
							<c:forEach var="list" items="${command.assigned_role_list}">
								<option value="<c:out value='${list.role_cd}'/>"><c:out
									value='${list.role_cd}' /></option>
							</c:forEach>
						</select></td>
					</tr>
				</table>
				<!-- // 권한 설정 테이블 -->
			</div>
		</div>

		<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- // footer -->
	</div>
</form:form></div>

<script language="javascript"> 
function initProgress() {
	var html = "" ;
	html += '    <table border="0" cellspacing="0" cellpadding="0">\r\n' ;
	html += '    	<tr>\r\n' ;
	html += '    		<td align="center" valign="top">\r\n' ;
	html += '    			<table border="0" cellspacing="0" cellpadding="0">\r\n' ;
	html += '    				<tr>\r\n' ;
	html += '    					<td>\r\n' ;
	html += '    			  			<div class="progress" style="display:;z-index:1; left: 29px; top: 27px;">\r\n' ;
	html += '                  				<p class="fL"><img src="/images/secfw/common/progress_bar.gif"></p>\r\n' ;
	html += '                  				<p class="text"><strong><spring:message code="secfw.page.field.auth.isWorking"/></strong><br><spring:message code="secfw.page.field.auth.plzWait"/>.</p>\r\n' ;
	html += '                  			</div>\r\n' ;
	html += '    					</td>\r\n' ;
	html += '    				</tr>\r\n' ;
	html += '    			</table>\r\n' ;
	html += '    		</td>\r\n' ;
	html += '    	</tr>\r\n' ;
	html += '    </table>\r\n' ;
	html += '<LINK href="/style/secfw/common.css" type="text/css" rel="stylesheet">\r\n' ;
	progressFrame.document.body.innerHTML = html ;
}
 
function viewHiddenProgress(view) {
	var obj = document.getElementById("progress") ;
	if(view){
		obj.style.display = "" ;
		document.getElementById("progressFrame").height = 109 ;
		document.getElementById("progressFrame").width = 331 ;
		
		var ltop_xp	= document.body.clientHeight/2+document.body.scrollTop;
		var ltop_yp	= document.body.clientWidth/2;
		
		document.getElementById('progress').style.top = ltop_xp-150;
		document.getElementById('progress').style.left = ltop_yp-250;
	} else {
		obj.style.display = "none" ;
	}
}
</script>
 
<div id='progress' style='position: absolute; display: none; ridge; z-index:1; top:0; left:0'>
	<iframe name="progressFrame" id="progressFrame" src="" frameborder="0" scrolling="no" width="0" height="0" onLoad="initProgress()"></iframe>
</div>
</body>
</html>