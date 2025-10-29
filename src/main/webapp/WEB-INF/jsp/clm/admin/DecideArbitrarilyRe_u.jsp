<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%--
/**
 * 파  일  명 : DecideArbitrarilyRe_u.jsp
 * 프로그램명 : 전결 규정 수정
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
 --%>
<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi"); 
	
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%@ include file="/WEB-INF/jsp/common/messageUtil.jsp" %>

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
	
	$(document).ready(function(){
	  	frmChkLenMe(frm.note,1000,'curByteExpl');
	});

	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/admin/decideArbitrarilyRe.do'/>";
		//저장
		if(flag == "insert"){
			if(confirm("<spring:message code='secfw.msg.ask.update' />")){
			    frm.method.value = "ModifyDecideArbitrarilyRe";
			    viewHiddenProgress(true);
		    	frm.submit();
			}
			
		//초기화
		} else if(flag == "cancel"){	
			if(confirm("<spring:message code='secfw.msg.ask.cancel' />")){
				frm.seqno.value  = "0";
			    frm.method.value = "listDecideArbitrarilyRe";
			    viewHiddenProgress(true);
				frm.submit();			
			}
		}
	}
	
</script>

</head>
<body>
<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id="frm" method="post" autocomplete="off">
<!-- search form -->
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
<!-- key form-->
<input type="hidden" name="seqno"	 value="<c:out value='${command.seqno}'/>" />
<!-- 첨부파일정보 -->

<!-- // **************************** Form Setting **************************** -->
<div id="wrap">
	<!-- container -->
	<div id="container">
	    <!-- Location -->
		<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->	
		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.title.decidearbitrarilyre.modifyTitle" /></h3>
		</div>
		<!-- //title -->
		<!-- content -->
		<div id="content">
			<!-- view -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
					<col class="tal_w04" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th><spring:message code="clm.page.field.decidearbitrarilyre.operdivCd" /></th>
						<td>
							<select name="operdiv_cd" id="operdiv_cd">
							    <c:forEach var="list" items="${operdivCdList}">
							        <option value="<c:out value='${list.orgnz_cd}' />"<c:if test="${list.orgnz_cd eq lom.operdiv_cd}">selected</c:if>><c:out value='${list.orgnz_nm}' /></option>
							    </c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.decidearbitrarilyre.bizClsfcn" /></th>
						<td>
						    <select name="biz_clsfcn" id="biz_clsfcn">
							    <c:forEach var="list" items="${bizClsfcnList}">
							        <option value="<c:out value='${list.type_cd}' />"<c:if test="${list.type_cd eq lom.biz_clsfcn}">selected</c:if>><c:out value='${list.cd_nm}' /></option>
							    </c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.decidearbitrarilyre.dcdAuthmanNm" /></th>
						<td>
						    <c:forEach var="list" items="${dcdAuthmanList}">
						        <input type="radio" name="dcd_authman" id="dcd_authman" size="90" value="<c:out value='${list.cd}' />" <c:if test="${list.cd eq lom.dcd_authman}">checked</c:if>/><c:out value="${list.cd_nm}" />
				           	</c:forEach>
						</td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.decidearbitrarilyre.note" /></th>
						<td>
							<span id="curByteExpl">0</span>/<spring:message code="clm.page.msg.common.max1000byte" /><br/>
							<textarea name="note" id="note" rows="10" cols="98" maxLength="1000" onkeyup="frmChkLenMe(this,1000,'curByteExpl')"><c:out value="${lom.note}" /></textarea>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- //view -->
	
			<div class="btn_wrap">
				<span class="btn_all_b"><span class="save"></span><a href="javascript:pageAction('insert');"><spring:message code="secfw.page.button.insert" /></a></span>
				<span class="btn_all_w"><span class="cancel"></span><a href="javascript:pageAction('cancel');"><spring:message code="secfw.page.button.cancel" /></a></span>
			</div>
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
</html>
