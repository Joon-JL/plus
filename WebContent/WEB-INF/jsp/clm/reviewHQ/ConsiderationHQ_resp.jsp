<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%--
/**
 * 파  일  명 : ConsiderationHQ_resp.jsp
 * 프로그램명 : 본사 검토의뢰 상세정보 리턴 - 담당자 선택 리스트
 * 설      명 : 
 * 작  성  자 :
 * 작  성  일 : 2014.05.15
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
%>
	
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="x-ua-compatible" content="IE=edge" >
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css"    type="text/css" rel="stylesheet">
<link href="<%=CSS%>/popup.css" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="JavaScript" type="text/JavaScript" >

	$(document).ready(function(){
		
		var frm = document.frm;
			frmChkLen(frm.apbt_memo,2000,'curByteExpl');
	});

	$(function() {
		$("#allCheck")
		.click(function() {
			if(($(this).attr("checked"))) {			
				$("input:checkbox[name='check_resp']")
					.each(function(){
						$(this).attr("checked", true);
					});
			} else {
				$("input:checkbox[name='check_resp']")
				.each(function(){
					$(this).attr("checked", false);
				});
			}
		});
	});
	
	/**
	* 저장
	*/
	function save() {
		var msg = "";
		var respId = new Array();
		var respNm = new Array();

		// 담당자 지정 유무 확인
		// 담당자 3명이상 유무 확인              
		if($('input[name=check_resp]:checked').length > 3){
			alert("<spring:message code='las.msg.alert.consideration.respmanSelect2' />");
			return;
		}
		
		if($('input[name=check_main]:checked').val() == "" || $('input[name=check_main]:checked').val() == undefined ||$('input[name=check_main]:checked').val() == "undefined"){
			alert("<spring:message code='las.jsp.msg.alert.elecResp' />");//정 검토자를 선택하셔야 합니다.
			return;
		}
		$('input[name=check_resp]:checked').each(
			function(){
				
				if($(this).attr("value") == $('input[name=check_main]:checked').val()){
					$(this).attr("title","["+"<spring:message code='las.page.field.contractManager.mainThC'/>"+"]" + $(this).attr("title"));
				}else{
					$(this).attr("title","["+"<spring:message code='las.page.field.contractManager.viceC'/>"+"]" + $(this).attr("title"));
				}
				respId.push($(this).attr("value"));
				respNm.push($(this).attr("title"));
				
			}
		);

		if(msg != ""){
			msg += "============================= \n";
		}
		
		if($('input[name=check_resp]:checked').length == 0){
			msg += "<spring:message code='las.msg.ask.appoint_null' />";
		}else{
			msg += "<spring:message code='las.msg.ask.appoint' />";
		}
		
		if(confirm(msg)){
			// 부모창 HQ Reviewer  의 리스트 박스 세팅 처리
			opener.returnResp(respId, respNm, $("#apbt_memo").val());
			// opener.confirmRespmanHQ($('input[name=check_main]:checked').val(), $('input[name=auto_apbt_yn]:checked').val(), $('input[name=solo_yn]:checked').val());
			opener.confirmRespmanHQ($('input[name=check_main]:checked').val(), 'Y', '1'); // 2013-11-05 무조건 전결 처리 solo_yn = 단독: 3으로 일괄 처리
			window.close();
		}
	}
	
	/**
	* 메시지 처리
	*/
	function showMessage(msg) {
		if(msg != "" && msg != null && msg.length > 1) {
			alert(msg);
		}
	}

	/**
	* 체크박스 변경에따라 正,副 변환
	*/
	function changeCheck(val){
		if($('input[name=check_resp]:checked').length > 3){
			alert("<spring:message code='las.msg.alert.consideration.respmanSelect2' />");
			$("#ck_m_"+val).removeAttr("checked");
			$("#check_"+val).removeAttr("checked");
			return;
		}
		
		if($("#check_"+val).attr("checked") == false){
			$("#ck_m_"+val).removeAttr("checked");
			$("#div_"+val).text("");
		}
		
		if($('input[name=check_resp]:checked').length == 1 && $("#check_"+val).attr("checked") == true){
			$("#ck_m_"+val).attr("checked","true");
			$("#div_"+val).text("<spring:message code='las.page.field.contractManager.mainThC'/>");
		}else if($('input[name=check_resp]:checked').length == 1 && $("#check_"+val).attr("checked") == false){
			$("#ck_m_" + $('input[name=check_resp]:checked').val()).attr("checked","true");
			$("#div_" + $('input[name=check_resp]:checked').val()).text("<spring:message code='las.page.field.contractManager.mainThC'/>");
		}else if($('input[name=check_resp]:checked').length > 1 && $("#check_"+val).attr("checked") == true){
			$("#ck_m_"+val).removeAttr("checked");
			$("#div_"+val).text("<spring:message code='las.page.field.contractManager.viceC'/>");//副
		}else if($('input[name=check_resp]:checked').length > 1 && $('input[name=check_main]:checked').length == 0){
			$("#ck_m_" + $('input[name=check_resp]:checked:first').val()).attr("checked","true");
			$("#div_" + $('input[name=check_resp]:checked:first').val()).text("<spring:message code='las.page.field.contractManager.mainThC'/>");
		}
	}
</script>
</head>
<body class="pop">


<!-- header -->
<h1><spring:message code="las.page.field.contractManager.selectPic"/> HQ <span class="close" onclick="javascript:self.close();" title="close"></span></h1>
<!-- //header -->
<div class="pop_area">
<!-- Popup Detail -->
		<!-- pop_content --><div class="pop_content" style="height:490px">
		
		<form:form name="frm" id="frm" method="post" autocomplete="off">
		<input type="hidden" name="method" value="">
		<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>">		
		
		<!-- button -->
		<table cellspacing="0" cellpadding="0" class="table-style01" border="0">
			<colgroup>
				<col width="20%">
				<col width="80%">
			</colgroup>
			<tr class="space">
				<td></td>
				<td></td>
			</tr>
			<tr>
				<th><spring:message code="las.page.field.contractManager.gcMsg" /></th>
				<td>
				<span id="curByteExpl">0</span>/<spring:message code="clm.page.msg.manage.stringLen2000" /><br>
				<textarea name="apbt_memo" id="apbt_memo" alt='<spring:message code="las.page.field.contractManager.gcMsg"/>' cols="30" rows="7" maxLength="2000" onkeyup="frmChkLenLang(this,2000,'curByteExpl','<%=langCd%>')" class="text_area_full"><c:out value="${lomAm.apbt_memo}" escapeXml="false" /></textarea></td>
			</tr>
		</table>
		
		<br>
			
			
			<!-- list -->
			<table class="list_basic mz">
	             <colgroup>
	              <col width="5%">
	              <col width="10%">
	              <col width="5%">
	              <col width="/">
	              <col width="16%">
	              <col width="10%">
	              <col width="10%">
	              <col width="10%">
	              <col width="10%">
	             </colgroup>
		    	<thead>
		          <tr>
		            <th rowspan="2">∨</th>
		            <th rowspan="2"><spring:message code="las.page.field.contractManager.mainThTitle" /></th>
		            <th rowspan="2">&nbsp;</th>
		            <th rowspan="2" class="rightline"><spring:message code="clm.page.msg.manage.reviewer" /></th><!-- Reviewer -->
		            <th colspan="5"><spring:message code="las.page.field.contractManager.assignState" /></th>
		          </tr>
		          <tr>
		            <th><spring:message code="las.page.field.contractManager.inReviewC" /></th><!-- 검토중 -->
		            <th><spring:message code="las.page.field.contractManager.ddC" /></th>
		            <th><spring:message code="las.page.field.contractManager.weekC" /></th>
		            <th><spring:message code="las.page.field.contractManager.mmC" /></th>
		            <th><spring:message code="las.page.field.contractManager.yearC" /></th>		
		          </tr>
		        </thead>
		        <tbody>
		        	<c:forEach var="list" items="${resultList}" varStatus="index"> 		        
					<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
						<td class="tC">
						   	<input type="checkbox" id="check_<c:out value='${list.respman_id}'/>" name="check_resp" title="<c:out value='${list.respman_nm}'/>" onClick="javascript:changeCheck(this.value)" value="<c:out value='${list.respman_id}'/>" <c:if test="${list.check_yn == 'Y'}"> checked </c:if>="">
						</td>
						<td class="tC">
							<input type="checkbox" id="ck_m_<c:out value='${list.respman_id}'/>" name="check_main" value="<c:out value='${list.respman_id}'/>" <c:if test="${list.main_cnsd_yn == 'Y'}"> checked </c:if>="" style="display:none;">
							<div id="div_<c:out value='${list.respman_id}'/>"><c:out value="${list.main_cnsd_yn_dp}" /></div>
						</td>
						<td class="tC"><c:out value="${list.RELATED_PRODUCTS_NM}" /></td>
					    <td class="tC"><c:out value="${list.respman_nm}" /></td>
					    <td class="tR"><c:out value="${list.cnsd_cnt}" /></td>
					    <td class="tR"><c:out value="${list.day_cnt}" /></td>
					    <td class="tR"><c:out value="${list.week_cnt}" /></td>
					    <td class="tR"><c:out value="${list.month_cnt}" /></td>
					    <td class="tR"><c:out value="${list.year_cnt}" /></td>
						<input type="hidden" name="roleCd" id="role_<c:out value='${list.respman_id}'/>" value="<c:out value='${list.role_cd}'/>">
					</tr>
					</c:forEach>
				</tbody>
	        </table>
	        <!-- //list -->
	        <br><h4><spring:message code="las.page.field.contractManager.leadAttny" /></h4>
			<!-- //pop_content -->
	        </form:form>
		</div>
		
		</div>
	<!--footer -->
<div class="pop_footer">
	<div class="btn_wrap fR" >
		<span class="btn_all_w" onclick="save();"><span class="save"></span><a href="#"><spring:message code="las.page.field.contractManager.dsnt"/></a></span>
		<span class="btn_all_w mR5" onclick="self.close();"><span class="cancel"></span><a href="#"><spring:message code='las.page.button.close' /></a></span>
	</div>
</div>
<!-- //footer -->	

</body>
</html>