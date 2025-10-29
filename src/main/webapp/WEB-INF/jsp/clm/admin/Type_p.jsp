<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%--
/**
 * 파  일  명 : Type_p.jsp
 * 프로그램명 : 계약유형관리 - Item 선택 리스트
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><spring:message code="clm.main.title" /></title>
<link href="<%=CSS%>/las.css"    type="text/css" rel="stylesheet" />
<link href="<%=CSS%>/popup.css" type="text/css" rel="stylesheet" />
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="JavaScript" type="text/JavaScript" >

	$(function() {
		$("#allCheck")
		.click(function() {
			if(($(this).attr("checked"))) {			
				$("input:checkbox[name='check_item']")
					.each(function(){
						$(this).attr("checked", true);
					});
			} else {
				$("input:checkbox[name='check_item']")
				.each(function(){
					$(this).attr("checked", false);
				});
			}
		});
	});
	
	function confirm() {
		var frm = document.frm;
		var cd = new Array();
		var cdNm = new Array();
		var cdNmAbbr = new Array();
		var cdNmEng = new Array();
		var cdNmAbbrEng = new Array();
		
		for(var i=0; i<frm.check_item.length; i++) {
			if (frm.check_item[i].checked == true){
				cd.push(frm.check_item[i].value);
				cdNm.push(frm.check_item[i].title);
				cdNmAbbr.push(frm.item_nm_abbr[i].value);
				cdNmEng.push(frm.item_nm_eng[i].value);
				cdNmAbbrEng.push(frm.item_nm_abbr_eng[i].value);
			}
		}

		opener.returnTypeItem(cd, cdNm, cdNmAbbr, cdNmEng, cdNmAbbrEng);
		
		window.close();
	}
	
	/**
	* 메시지 처리
	*/
	function showMessage(msg) {
		if(msg != "" && msg != null && msg.length > 1) {
			alert(msg);
		}
	}
</script>
</head>
<body class="pop">
<form:form name="frm" id="frm" method="post" autocomplete="off">
<input type="hidden" name="method" 		value="" />
<input type="hidden" name="menu_id" 	value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="type_cd"     id="type_cd"     value="<c:out value='${command.type_cd}'/>" />

<!-- header -->
<h1><spring:message code="clm.page.msg.admin.contItem" /><span class="close" onclick="javascript:self.close();" title="close"></span></h1>
<!-- //header -->
<div class="pop_area">
<!-- Popup Detail -->
	<div class="h_860_1">
		<!-- pop_content -->
		<div class="pop_content" style='height:400px;'>
			
			<!-- list -->
			<table class="list_basic mz">
		    	<colgroup>
		          <col align="center" width="15%"/>
		          <col align="center" width="*%"/>
		    	</colgroup>
		    	<thead>
		          <tr>
		            <th><input type="checkbox" name="allCheck" id="allCheck" class="checkbox"/></th>	<!-- 선택 -->
		            <th><spring:message code="clm.page.field.admin.type.apply_item" /></th>	<!-- 적용대상 -->
		          </tr>
		        </thead>
		        <tbody>
					<c:choose>
						<c:when test="${pageUtil.totalRow > 0}">
							<c:forEach var="list" items="${lom}">
							<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
							    <td class="tC">
							    	<input type="checkbox" id="check_item" name="check_item" title="<c:out value='${list.cd_nm}'/>" value="<c:out value='${list.cd}'/>" <c:if test="${list.check_yn == 'Y'}"> checked </c:if> />
							    	<input type="hidden" id="item_nm_abbr" name="item_nm_abbr" value="<c:out value='${list.cd_nm_abbr}'/>" />
							    	<input type="hidden" id="item_nm_eng" name="item_nm_eng" value="<c:out value='${list.cd_nm_eng}'/>" />
							    	<input type="hidden" id="item_nm_abbr_eng" name="item_nm_abbr_eng" value="<c:out value='${list.cd_nm_abbr_eng}'/>" />
							    </td>
					            <td class="tL"><c:out value='${list.cd_nm}'/></td>
							</tr>
							</c:forEach>
					    </c:when>
					    <c:otherwise>
							<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
								<td colspan="2" align="center" class="tC"><spring:message code="clm.msg.succ.noResult" /></td>
							</tr>
					    </c:otherwise>
					</c:choose>
		        
				</tbody>
	        </table>
	        <!-- //list -->
		</div>
		<!-- //pop_content -->
		<!-- button -->
	 	<div class="btn_wrap fR mt20">
			<span class="btn_all_w"><span class="save"></span><a href="javascript:confirm()"><spring:message code='clm.page.button.confirm' /></a></span>
			<span class="btn_all_w"><span class="list"></span><a href="javascript:self.close();"><spring:message code='clm.page.button.cancel' /></a></span>
	 	</div>
	 	<!-- //button -->
	</div>
</div>
</form:form>
</body>
</html>