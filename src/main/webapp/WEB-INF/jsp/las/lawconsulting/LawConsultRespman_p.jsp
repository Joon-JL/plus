<!DOCTYPE html>

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
 * 파  일  명 : 
 * 프로그램명 : 법률자문 상세정보 리턴 - 담당자 선택 리스트
 * 설      명 : 
 * 작  성  자 : 김현구
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
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css"    type="text/css" rel="stylesheet">
<link href="<%=CSS%>/popup.css" type="text/css" rel="stylesheet">
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->

<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="JavaScript" type="text/JavaScript" >

$(document).ready(function(){
	
	//if(frm.grpmgr_re_yn.value == "Y")
	//	frm.grpmgr_re_yn.checked = true;
	//else
	//	frm.grpmgr_re_yn.checked = false;
	
	//alert('<c:out value="${resultList}"/>');

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
		/*
		$('#grpmgr_re_yn').click(function() {
			if($('#grpmgr_re_yn').attr('checked')){
				frm.grpmgr_re_yn.value = "Y";
			}
			else{
				frm.grpmgr_re_yn.value = "N";
			}
		});*/
	});
	
	function save() {
		var frm = document.frm;
		//var confirmMessage = "<spring:message code='secfw.msg.ask.assign' />";
		
		//alert($('input[name=grpmgr_re_yn]:checked').val());
		//return;
		
		
		// 결재방법 선택 확인
// 		if($('input:radio[name=grpmgr_re_yn]:checked').val() == null){
// 			alert("<spring:message code='las.page.lawconsult.chk0001' />");
// 			return;
// 		}
		

		
		// Review Method 삭제요청 2013.11.08
// 		if($('input:radio[name=solo_yn]:checked').val() == null){
// 			alert("<spring:message code='las.page.lawconsult.chk0002' />");
// 			return;
			
// 		}
		
		// 담당자 지정 유무 확인
		if($('input[name=check_resp]:checked').length == 0){
			alert("<spring:message code='las.page.field.contractManager.selectPic' />");
			return;
		}
		
		// 담당자 3명이상 유무 확인
		if($('input[name=check_resp]:checked').length > 3){
			alert("<spring:message code='las.msg.alert.consideration.respmanSelect2' />");
			return;	
		}
		
		var rb01Cnt = 0;
		var modCnt = 0;
		$('[id^=role_]').each(function(){
			if($('#'+this.id).prev().attr('checked')){
				if($('#'+this.id).val() == 'RB01'){
					rb01Cnt++;
				}
				else{
					modCnt++;
				} 
			}
		});
		
		//전자검토자만 배정하려고 할 시 에러
		if(rb01Cnt > 0 && modCnt == 0){
			//메세지 출력
			alert("<spring:message code='las.jsp.msg.alert.elecResp' />");
			return;
		}
		
		//전자검토자가 포함된 배정에서는 전결 처리
// 		if(rb01Cnt > 0 && modCnt > 0){
// 			confirmMessage = "<spring:message code='las.page.field.review.consideration01' />";
// 			//전결 처리 메시지 출력
// 			if(confirm(confirmMessage)){
// 				$('input:radio[name=grpmgr_re_yn][value="N"]').attr("checked","checked");
// 				//frm.grpmgr_re_yn.value = "N";
// 			}
// 			else{
// 				return;
// 			}
// 		}
		//전자검토자가 포함되어있지 않으면
// 		else{
// 			if(!confirm(confirmMessage)){
// 				return;
// 			}	
// 		}
		
		var respId = new Array();
		var respNm = new Array();
		
		$('input[name=check_resp]:checked').each(
			function(){
				respId.push($(this).attr("value"));
				respNm.push($(this).attr("title"));
			}
		);
		
		opener.returnResp(respId, respNm);
		//opener.assign(frm.grpmgr_re_yn.value, $('input[name=solo_yn]:checked').val(), frm.ordr_cont.value);
		opener.assign(frm.grpmgr_re_yn.value, frm.ordr_cont.value);
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

<!-- header -->
<h1><spring:message code="las.page.field.lawconsulting.selectPic"/><span class="close" onclick="javascript:self.close();" title="close"></span></h1>
<!-- //header -->
<div class="pop_area">
	<div class="pop_content">		
		<!-- pop_content -->
		<form:form name="frm" id="frm" method="post" autocomplete="off">
		<input type="hidden" name="method" 			value="">
		<input type="hidden" name="menu_id" 		value="<c:out value='${command.menu_id}'/>" />
		<input type="hidden" name="grpmgr_re_yn" 	id="grpmgr_re_yn" 	value="N" />
			<!-- button -->
<!-- 		 	<div class="btnwrap tR"> -->
<%-- 				<span class="btn_all_w"><span class="confirm"></span><a href="javascript:save()"><spring:message code='las.page.button.lawsuit.appoint' /></a></span> --%>
<%-- 				<span class="btn_all_w"><span class="cancel"></span><a href="javascript:self.close();"><spring:message code='las.page.button.cancel' /></a></span> --%>
<!-- 		 	</div> -->
		 	<!-- //button -->
		
			<table class='table-style01'>
	        	<colgroup>
	              <col width="17%" />
	              <col />
	            </colgroup>
<!-- 	            <tr> -->
<%-- 	            	<th><spring:message code="las.page.field.lawconsult.grpmgr_re_yn" />&nbsp;<img src="<%=IMAGE %>/common/step_q.gif" alt="info" title="<spring:message code="las.page.field.contractManager.msgArbitrary"/>" /></th> --%>
<!-- 	            	<td> -->
<%-- 	            		<input type="radio" name="grpmgr_re_yn" value="Y" <c:if test="${command.grpmgr_re_yn=='Y'}"> checked="checked"</c:if> /> <spring:message code="las.page.field.contractManager.general" /> --%>
<%-- 	            		<input type="radio" name="grpmgr_re_yn" value="N" <c:if test="${command.grpmgr_re_yn=='N'}"> checked="checked"</c:if> /> <spring:message code="las.page.field.contractManager.arbitrary" /> --%>
<!-- 	            	</td> -->
<!-- 	            </tr> -->

					<!-- Review Method 삭제요청 2013.11.08 -->
<!-- 	            <tr> -->
<%-- 	            	<th><spring:message code="las.page.field.lawconsult.solo_yn" /></th> --%>
<!-- 	            	<td> -->
<%-- 	            		<input type="radio" name="solo_yn" value="1" <c:if test="${command.solo_yn=='1'}"> checked="checked"</c:if> /> <spring:message code="las.page.field.lawconsult.cavity.dmst"/> --%>
<%-- 	            		<input type="radio" name="solo_yn" value="2" <c:if test="${command.solo_yn=='2'}"> checked="checked"</c:if> /> <spring:message code="las.page.field.lawconsult.cavity.frgn"/> --%>
<%-- 	            		<input type="radio" name="solo_yn" value="3" <c:if test="${command.solo_yn=='3'}"> checked="checked"</c:if> /> <spring:message code="las.page.field.lawconsult.alone" /> --%>
<!-- 	            	</td> -->
<!-- 	            </tr> -->
	            
	            <tr>
	            	<th><spring:message code="las.page.field.lawconsult.leaderMsg" /></th>
	            	
	            	<td>
	            		<span id="curByteExpl_body_mine">0</span>/<spring:message code="clm.page.msg.manage.stringLen2000" /><br>
	            		<textarea name="ordr_cont" id="ordr_cont" alt="<spring:message code="las.page.field.lawconsult.leaderMsg"/>" cols="30" rows="7" maxLength="2000" onkeyup="frmChkLenLang(this,2000,'curByteExpl_body_mine','<%=langCd%>')" class="text_area_full"><c:out value='${command.ordr_cont}' escapeXml="false"/></textarea>
	            	</td>
	            </tr>
	        </table>
			
			<!-- list -->
			<div class='tableWrap mt20'>
					<div class='tableone'>
			
						<table class="list_basic">
				            <colgroup>
				              <col width="10%" />
				              <col width="15%" />
				              <col width="15%" />
				              <col width="15%" />
				              <col width="15%" />
				              <col width="15%" />
				              <col width="15%" />	              
				            </colgroup>
					    	<thead>
					          <tr>
					            <th rowspan="2">∨</th>
					            <th rowspan="2" class="rightline"><spring:message code="las.page.field.lawconsult.respman_nm"/></th>
					            <th colspan="5"><spring:message code="las.page.field.lawconsulting.assignState"/></th>
					          </tr>
					          <tr>
					            <th class="sub01"><spring:message code="las.page.field.lawconsulting.inReviewC"/></th>
					            <th class="sub01"><spring:message code="las.page.field.lawconsulting.ddC"/></th>
					            <th class="sub01"><spring:message code="las.page.field.lawconsulting.weekC"/></th>
					            <th class="sub01"><spring:message code="las.page.field.lawconsulting.mmC"/></th>
					            <th class="sub01"><spring:message code="las.page.field.lawconsulting.yearC"/></th>		            
					          </tr>
					        </thead>
					         </table>
					         </div></div>
				<style>
					.h_120 {max-height:230px;}
					*html .h_120 {height:120px;}
				</style>
				<div class='tabletwo h_120'>
				<table class="list_scr">
				      		<colgroup>
				              <col width="10%" />
				              <col width="15%" />
				              <col width="15%" />
				              <col width="15%" />
				              <col width="15%" />
				              <col width="15%" />
				              <col width="15%" />	              
				            </colgroup>
					        <tbody>
					        	<c:forEach var="list" items="${resultList}" varStatus="index">		   
					        	<tr>						   		 
								<td class="tC">
								   	<input type="checkbox" id="check_resp" name="check_resp" title="<c:out value='${list.respman_nm_eng}'/>" value="<c:out value='${list.respman_id}'/>" <c:if test="${list.check_yn == 'Y'}"> checked </c:if> />
								   	<input type="hidden" id="role_<c:out value='${index.count}'/>" value="<c:out value='${list.role_cd}'/>"/>
								</td>
							    <td class="tC"><c:out value='${list.respman_nm_eng}'/></td>
							    <td class="tC"><c:out value='${list.pending_cnt}'/></td>
							    <td class="tC"><c:out value='${list.day_cnt}'/></td>
							    <td class="tC"><c:out value='${list.week_cnt}'/></td>
							    <td class="tC"><c:out value='${list.month_cnt}'/></td>
							    <td class="tC"><c:out value='${list.year_cnt}'/></td>
							    </tr>
								</c:forEach>
							</tbody>
				        </table>
				        </div>
				        <!-- //list -->
				        
				        <!-- button -->
<!-- 					 	<div class="btnwrap tR mt10"> -->
<%-- 							<span class="btn_all_w"><span class="confirm"></span><a href="javascript:save()"><spring:message code='las.page.button.lawsuit.appoint' /></a></span> --%>
<%-- 							<span class="btn_all_w"><span class="cancel"></span><a href="javascript:self.close();"><spring:message code='las.page.button.cancel' /></a></span> --%>
<!-- 					 	</div> -->
					 	<!-- //button -->
								        
				        
	        </form:form>
		</div>
</div>

<!--footer -->
<div class="pop_footer">
	<div class="btn_wrap fR" >
		<span class="btn_all_w"><span class="confirm"></span><a href="javascript:save()"><spring:message code='las.page.button.lawsuit.appoint' /></a></span>
		<span class="btn_all_w mR5"><span class="cancel"></span><a href="#" onclick="javascript:self.close();"><spring:message code='las.page.button.close' /></a></span>
	</div>
</div>
<!-- //footer -->
</body>
</html>