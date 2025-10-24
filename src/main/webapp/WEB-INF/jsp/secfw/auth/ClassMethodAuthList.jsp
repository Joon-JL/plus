<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@page import="com.sds.secframework.common.util.BoardPageUtil"%>
<%@page import="com.sds.secframework.common.util.Token"%>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%
	BoardPageUtil pageUtil = (BoardPageUtil)request.getAttribute("pageUtil") ;
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="secfw.page.title.auth.AuthList" /></title>

<link href="<%=CSS%>/las.css"    type="text/css" rel="stylesheet">
<link href="<c:url value='/style/secfw/common.css' />" type="text/css" rel="stylesheet">
<script language="javascript" src="/script/secfw/jquery/jquery.js"></script>
<script language="javascript" src="/script/secfw/jquery/ui.core.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.form.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.dynatree.js"></script>
<script language="javascript">




	function init(){
		var f = document.frm;
		var message = "<c:out value='${command.return_message}'/>";
	
		if(message!=null && message!=""){
			alert(message);
		}

		
	}
	function search(){
		viewHiddenProgress(true) ;
		goPage(1);
	}

	function goPage(page){
		viewHiddenProgress(true) ;
		var f = document.frm;
		f.curPage.value = page;
		f.method.value = "listPage";
		f.action = "<c:url value='/secfw/classMethodAuth.do' />";
		f.target = "_self";
		f.submit();
	} 

	function del() {
		
		var f = document.frm;
		var ischk = false;
		var confirmMessage = "<spring:message code='secfw.msg.ask.delete' />";
		
		//체크박스가 없을 경우
		if(f.checkbox == null){
			viewHiddenProgress(false) ;
			return;
		}
		//체크박스가 여러개일 경우
		else if(f.checkbox.length > 1){
			for(var i=0 ; i<f.checkbox.length ; i++){
				if(f.checkbox[i].checked == true)
					ischk = true;
			}
		}
		//체크박스가 1개일 경우
		else {
			if(f.checkbox.checked == true){
				ischk = true;
			}
		}

		if(ischk == false){
			viewHiddenProgress(false) ;
			alert("<spring:message code='secfw.page.field.auth.chkMethodForDel'/>");
			return;
		}

		if(confirm(confirmMessage)){
			viewHiddenProgress(true) ;
			f.curPage.value = 1;
			f.method.value = "deleteMulti";
			f.action = "<c:url value='/secfw/classMethodAuth.do' />";
			f.target = "_self";
			f.submit();
		}
	}

	function insert() {
		viewHiddenProgress(true) ;
		var f = document.frm;
		f.method.value = "forwardInsertForm";
		f.action = "<c:url value='/secfw/classMethodAuth.do' />";
		f.target = "_self";
		f.isModify.value = "false";
		f.submit();
	}

	function detail(class_nm, method_nm, check_yn) {
		viewHiddenProgress(true) ;
		var f = document.frm;
		f.class_nm.value = class_nm;
		f.method_nm.value = method_nm;
		f.check_yn.value = check_yn;
		f.method.value = "forwardModifyForm";
		f.action = "<c:url value='/secfw/classMethodAuth.do' />";
		f.target = "_self";
		f.isModify.value = "true";
		f.submit();
	}
	
</script>
</head>

<body onload="init()">
<div id="wrap">
	<div id="container">
	
		<!-- location -->
		<div class="location"><IMG SRC="<%=IMAGE%>/icon/ico_home.gif" BORDER="0" alt="Home" title="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
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
				<input type="hidden" name="class_nm">
				<input type="hidden" name="method_nm">
				<input type="hidden" name="check_yn">
				<input type="hidden" name="isModify">

				<!-- 이중등록 방지 -->
				<input type="hidden" name="TOKEN"  value="<%=Token.set(request) %>">

 
				<!-- 검색  -->
				<div class="search_box">
					<div class="search_box_in">
						<table class="search_in">
							<tr>
								<th><spring:message code="secfw.page.field.auth.classNm"/> :</th>
								<td><input name="class_srch_word" id="class_srch_word" type="text" value="<c:out value='${command.class_srch_word}'/>" onKeyDown="if(event.keyCode==13){search()}" class="text" style="width:200px;">&nbsp;&nbsp;</td>
								<th><spring:message code="secfw.page.field.auth.methodNm"/> :</th>
								<td><input name="method_srch_word" id="method_srch_word" type="text" value="<c:out value='${command.method_srch_word}'/>" onKeyDown="if(event.keyCode==13){search()}" class="text" style="width:120px;">&nbsp;&nbsp;</td>
								<th><spring:message code="secfw.page.field.auth.authChk"/> :</th>
								<td>
									<select name="srch_type" id="srch_type">  
										<option value="ALL"<c:if test="${command.srch_type=='ALL'}"> selected</c:if>><spring:message code="secfw.page.field.auth.all"/></option>
										<option value="Y"<c:if test="${command.srch_type=='Y'}"> selected</c:if>>Y</option>
										<option value="N"<c:if test="${command.srch_type=='N'}"> selected</c:if>>N</option>
									</select>
								</td>
								<td>
									<a href="javascript:search()" class="bt_search"><span id="img_srch_auth"><img src="/images/secfw/ico/ico_search.gif"/><spring:message code="secfw.page.field.auth.search"/></span></a>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<!-- // 검색  -->

				<div class="total"><p>Total : <strong> <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%></strong></p></div>
				
				<!-- 버튼 -->
				<div class="bt_content_top" style='margin-right:10px'>
					<a href="javascript:insert()" class="bt_fn ico_register"><span><spring:message code="secfw.page.field.auth.registration"/></span></a>
					<a href="javascript:del()" class="bt_fn ico_delete"><span><spring:message code="secfw.page.field.auth.delete"/></span></a>
				</div>
				<!-- // 버튼 -->
		
	
				<!-- 데이터 테이블 -->
				<table class="basic_list" style="text-align:center;">
					<colgroup>
						<col width="40">
						<col width="*">
						<col width="*">
						<col width="100">
					</colgroup>
					<tr>
						<th><spring:message code="secfw.page.field.auth.select"/></th>
						<th><spring:message code="secfw.page.field.auth.classNm"/></th>
						<th><spring:message code="secfw.page.field.auth.methodNm"/></th>
						<th class='end'><spring:message code="secfw.page.field.auth.authChk"/></th>
					</tr>
			
					<c:choose>
						<c:when test="${pageUtil.totalRow > 0}">
							<c:forEach var="list" items="${command.class_method_auth_list}">
								<tr onMouseOut="this.className='';"
									onMouseOver="this.className='selected'">
									<td><input type="checkbox" name="checkbox" value="<c:out value='${list.delete_key}'/>"/></td>
									<td><c:out value='${list.class_nm}' /></td>
									<td><a href="javascript:detail('<c:out value="${list.class_nm}"/>',
																'<c:out value="${list.method_nm}"/>',
																'<c:out value="${list.check_yn}"/>')"><c:out value='${list.method_nm}' /></a></td>
									<td class='end'><c:out value='${list.check_yn}' /></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr onMouseOut="this.className='';"
								onMouseOver="this.className='selected'">
								<td colspan="4"><spring:message code="secfw.page.field.auth.noPost"/></td>
							</tr>
						</c:otherwise>
					</c:choose>
				</table>
				<!-- // 데이터 테이블 -->

				<!-- 페이징 처리 -->
				<div class="paging"><%=pageUtil.getPageNavi(pageUtil, "", langCd) %></div>
				<!-- // 페이징 처리 -->
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