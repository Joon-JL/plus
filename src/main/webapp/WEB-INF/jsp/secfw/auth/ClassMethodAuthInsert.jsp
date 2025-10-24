<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.sds.secframework.common.util.BoardPageUtil"%>
<%@ page import="com.sds.secframework.common.util.Token"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="secfw.page.title.auth.AuthList" /></title>

<link href="<%=CSS%>/clm.css"    type="text/css" rel="stylesheet">
<LINK href="<c:url value="/style/secfw/common.css"/>" type="text/css" rel="stylesheet">
<script language="javascript" src="/script/secfw/jquery/jquery.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.form.js"></script>
<script language="javascript">

	function init(){
		var f = document.frm;
		var message = "<c:out value='${command.return_message}'/>";

		if(message!=null && message!=""){
			alert(message);
		}
	}

	/* 권한전체를  설정가능한 권한으로 세팅*/
	function allAvailableAuth(){
		var f = document.frm;
		var length = f.sel_assigned_auth_list.options.length;
		if(f.sel_assigned_auth_list == null){
			return;
		}
		
		for(var i=0; length>0; i++){
			f.sel_available_auth_list.add(new Option(f.sel_assigned_auth_list[0].text, f.sel_assigned_auth_list[0].value));
			f.sel_assigned_auth_list.options[0] = null;
			length = f.sel_assigned_auth_list.options.length ;
		}
	}

	/* 권한전체를 설정된 권한으로 세팅 */
	function allAssignedAuth(){         
		var f = document.frm;
		var length = f.sel_available_auth_list.options.length;
		if(f.sel_available_auth_list == null){
			return;
		}

		for(var i=0; length>0; i++){
			f.sel_assigned_auth_list.add(new Option(f.sel_available_auth_list[0].text, f.sel_available_auth_list[0].value));
			f.sel_available_auth_list.options[0] = null;
			length = f.sel_available_auth_list.options.length ;
		}
	}

	/* 선택한 권한을 설정가능한 권한으로 세팅 */
	function moveAvailableAuth(){
		var f = document.frm;
		var length = f.sel_assigned_auth_list.options.length;

		for(var i=0; i<length ; i++){
			if(f.sel_assigned_auth_list[i].selected){
				f.sel_available_auth_list.add(new Option(f.sel_assigned_auth_list[i].text, f.sel_assigned_auth_list[i].value));				
				f.sel_assigned_auth_list.options[i] = null;
				i -= 1;
				length -= 1;
			}
		}
	}

	/* 선택한 권한을 설정된 권한으로 세팅 */
	function moveAssignedAuth(){
		var f = document.frm;
		var length = f.sel_available_auth_list.options.length;

		for(var i=0; i<length ; i++){
			if(f.sel_available_auth_list[i].selected){
				f.sel_assigned_auth_list.add(new Option(f.sel_available_auth_list[i].text, f.sel_available_auth_list[i].value));				
				f.sel_available_auth_list.options[i] = null;
				i -= 1;
				length -= 1;
			}
		}
	}
	
	function goList() {
		viewHiddenProgress(true) ;
		var f = document.frm;
		f.method.value = "listPage";
		f.action = "<c:url value='/secfw/classMethodAuth.do' />";
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
		allAvailableAuth();
		f.method.value = "forwardInsertForm";
		f.action = "<c:url value='/secfw/classMethodAuth.do' />";
		f.target="_self";
		f.submit();
	}

	
	/* 수정 기능 수행시 초기화 */
	function refresh_modify(){
		viewHiddenProgress(true) ;
		var f = document.frm;
		f.method.value = "forwardModifyForm";
		f.action = "<c:url value='/secfw/classMethodAuth.do' />";
		f.target="_self";
		f.submit();
	}
	
	function del() {
		
		var f = document.frm;
		var confirmMessage = "<spring:message code='secfw.msg.ask.delete' />";

		if(confirm(confirmMessage)){
			viewHiddenProgress(true) ;
			f.method.value = "delete";
			f.action = "<c:url value='/secfw/classMethodAuth.do' />";
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
			for(var i=0; i<f.sel_assigned_auth_list.options.length ; i++){
				if(f.sel_assigned_auth_list.options[i].selected == false){
					f.sel_assigned_auth_list.options[i].selected = true;
				}
			}
			for(var j=0; j<f.sel_available_auth_list.options.length ; j++){
				if(f.sel_available_auth_list.options[j].selected == false){
					f.sel_available_auth_list.options[j].selected = true;
				}
			}
	
			f.method.value = "insert";
			f.action = "<c:url value='/secfw/classMethodAuth.do' />";
			f.target = "_self";
			f.submit();
		}
	}

	function modify() {
		var f = document.frm;
		var confirmMessage = "<spring:message code='secfw.msg.ask.update' />";

		if(confirm(confirmMessage)){
			viewHiddenProgress(true) ;
			for(var i=0; i<f.sel_assigned_auth_list.options.length ; i++){
				if(f.sel_assigned_auth_list.options[i].selected == false){
					f.sel_assigned_auth_list.options[i].selected = true;
				}
			}
			for(var j=0; j<f.sel_available_auth_list.options.length ; j++){
				if(f.sel_available_auth_list.options[j].selected == false){
					f.sel_available_auth_list.options[j].selected = true;
				}
			}
			
			f.method.value = "modify";
			f.action = "<c:url value='/secfw/classMethodAuth.do' />";
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
		<div class="location"><IMG SRC="<%=IMAGE%>/icon/ico_home.gif" BORDER="0" alt="Home" title="Home"></IMG><c:out value='${menuNavi}' escapeXml="false"/></div>
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
				<input type="hidden" name="available_auth_list">
				<input type="hidden" name="assigned_auth_list">
				<input type="hidden" name="isModify" value="<c:out value='${command.isModify}'/>">
				
				<!-- 이중등록 방지 -->
				<input type="hidden" name="TOKEN"  value="<%=Token.set(request) %>">

	
	

				<!-- 검색  -->
				<div class="search_box">
					<div class="search_box_in">
						<table class="search_in">
							<colgroup>
								<col width="5%">
								<col width="20%">
								<col width="5%">
								<col width="20%">
								<col width="5%">
								<col width="20%">
							</colgroup>
							<tr>
								<th><spring:message code="secfw.page.field.auth.classNm"/> :</th>
								<c:choose>
									<c:when test="${command.isModify == 'false'}">
										<td><input name="class_nm" id="class_nm" type="text"
								value="<c:out value='${command.class_nm}'/>" onKeyDown="if(event.keyCode==13){search()}" class="text" style="width: 80%; ime-mode:disabled;"></td>
									</c:when>
									<c:otherwise>
										<td><input name="class_nm" id="class_nm" type="text"
								value="<c:out value='${command.class_nm}'/>" onKeyDown="if(event.keyCode==13){search()}" class="text" style="width: 80%; ime-mode:disabled; background-color:#dddddd" readonly="readonly"></td>
									</c:otherwise>
								</c:choose>
								<th><spring:message code="secfw.page.field.auth.methodNm"/> :</th>
								<c:choose>
									<c:when test="${command.isModify == 'false'}">
										<td><input name="method_nm" id="method_nm" type="text" value="<c:out value='${command.method_nm}'/>" onKeyDown="if(event.keyCode==13){search()}" class="text" style="width: 80%; ime-mode:disabled"></td>
									</c:when>
									<c:otherwise>
										<td>	<input name="method_nm" id="method_nm" type="text" value="<c:out value='${command.method_nm}'/>" onKeyDown="if(event.keyCode==13){search()}" class="text" style="width: 80%; ime-mode:disabled; background-color:#dddddd" readonly="readonly"></td>
									</c:otherwise>
								</c:choose>
								<th><spring:message code="secfw.page.field.auth.authChk"/> :</th>
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
				<div class="bt_content_top" style='margin-right:10px;'>
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
				<table class="basic_list">
					<colgroup>
						<col width="48%">
						<col width="4%"  >
						<col width="48%">
					</colgroup>
					<tr>
						<th><span class="title"><spring:message code="secfw.page.field.auth.setAvailAuth"/></span></th>
						<th></th>
						<th class='end'><span class="title"><spring:message code="secfw.page.field.auth.setupAuth"/></span></th>
					</tr>
					<tr>
						<td><select name="sel_available_auth_list" size="5"
							multiple="multiple" style='height:120px;width:100%'>
							<c:forEach var="list" items="${command.available_auth_list}">
								<option value="<c:out value='${list.auth_cd}'/>" text="<c:out value='${list.auth_nm}'/>"><c:out
									value='${list.auth_nm}' /></option>
							</c:forEach>
						</select></td>
						
						<td align="center"><img src="<%=IMAGE %>/btn/page-last.gif" onclick="allAssignedAuth()"
							style="cursor: hand; margin-bottom: 7px" />
						<img src="<%=IMAGE %>/btn/page-next.gif"
							/ class='schbox' align="absmiddle" onclick="moveAssignedAuth()"
							style="cursor: hand; margin-bottom: 7px" />
						<img src="<%=IMAGE %>/btn/page-prev.gif"
							/ class='schbox' align="absmiddle" onclick="moveAvailableAuth()"
							style="cursor: hand; margin-bottom: 7px" />
						<img src="<%=IMAGE %>/btn/page-first.gif"
							/ class='schbox' align="absmiddle" onclick="allAvailableAuth()"
							style="cursor: hand; margin-bottom: 7px" /></td>
						
						
						<td class='end'><select name="sel_assigned_auth_list" size="5"
							multiple="multiple" style='height:120px;width:100%'>
							<c:forEach var="list" items="${command.assigned_auth_list}">
								<option value="<c:out value='${list.auth_cd}'/>" text="<c:out value='${list.auth_nm}'/>"><c:out
									value='${list.auth_nm}' /></option>
							</c:forEach>
						</select></td>
					</tr>
				</table>
				<!-- // 권한 설정 테이블 -->
				
			</div>
		</div>
		<!-- footer -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
</div>
</form:form>
</body>
</html>