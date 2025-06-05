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
 * 파  일  명 : ConsiderationHQ_resp2.jsp
 * 프로그램명 : 본사 검토의뢰 상세정보 리턴 - 담당자 선택 리스트
 * 설      명 : 
 * 작  성  자 : 박 병주 
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

	var ses__related_products = "<c:out value='${command.session_related_products}' />";

	$(document).ready(function(){
		// alert("rel_pro : " + "<c:out value="${command.hq_rel_pro}" />" + " / session:" + "<c:out value="${command.session_related_products}" />");
		
		// 로그인자의 담당이 아닌 배정은 비활성화
		$("input:checkbox[name='check_resp']")
		.each(function(){
			if( $(this).attr("alt")!=ses__related_products){
				$(this).attr("disabled", true);
			}
		});
		
		// CE인 경우 IM 체크 박스 비활성화
		<c:if test="${'RP0201' eq command.session_related_products }">
			$("#ck_im_resp_yn").attr("disabled", true);
			
			// 배정포기 한 경우 체크 박스 비활성화
			<c:if test="${'2' eq command.ce_resp_yn }">			
				$("input:checkbox[name='check_resp']").each(function(){	$(this).attr("disabled", true);	});			
			</c:if>
			
			// 배정포기 체크 박스 체크 이벤트 바인딩 처리
			$(function() {
				$("#ck_ce_resp_yn")
				.click(function() {
					if(($(this).attr("checked"))) {			
						$("input:checkbox[name='check_resp']")
							.each(function(){
								if( $(this).attr("alt")==ses__related_products){
									$(this).attr("checked", false);
									$(this).attr("disabled", true);
								}
							});
						
							$("#data_body div").each(function(){
								if( $(this).attr("alt")==ses__related_products){
									$(this).html("");
								}
							});
						
					} else {
						$("input:checkbox[name='check_resp']")
						.each(function(){
							if( $(this).attr("alt")==ses__related_products){	
								$(this).attr("disabled", false);
							}
						});
					}
				});
			});			
			
		</c:if>
		
		// IM인 경우 CE 체크 박스 비활성화
		<c:if test="${'RP0202' eq command.session_related_products }">
			$("#ck_ce_resp_yn").attr("disabled", true);
			
			<c:if test="${'2' eq command.im_resp_yn }">			
				$("input:checkbox[name='check_resp']").each(function(){	$(this).attr("disabled", true);	});			
			</c:if>
			
			$(function() {
				$("#ck_im_resp_yn")
				.click(function() {
					if(($(this).attr("checked"))) {			
						$("input:checkbox[name='check_resp']")
							.each(function(){
								if( $(this).attr("alt")==ses__related_products){	
									$(this).attr("checked", false);
									$(this).attr("disabled", true);
								}
							});
						
						$("#data_body div").each(function(){
							if( $(this).attr("alt")==ses__related_products){
								$(this).html("");
							}
						});						
						
					} else {
						$("input:checkbox[name='check_resp']")
						.each(function(){
							if( $(this).attr("alt")==ses__related_products){	
								$(this).attr("disabled", false);
							}
						});
					}
				});
			});	
			
		</c:if>
		
		var frm = document.frm;
			frmChkLen(frm.apbt_memo,2000,'curByteExpl');
	}); 
	//   $(document).ready  끝
		
	/**
	* 저장
	*/
	function Save() {
		
		// alert(ses__related_products);
		
		var msg = "";
		var respId = new Array();
		var respNm = new Array();	
		var resp_yn_chk = false;
		var owner_flag = "";
		
		if($('input[name=ck_ce_resp_yn]:checked').length > 0 && $('input[name=ck_im_resp_yn]:checked').length > 0){
			alert("CE/ IM 모두 배정 포기 할 수 없습니다.");
			return;
		}
		
		// 로그인자가 CE인 경우
		<c:if test="${'RP0201' eq command.session_related_products }">
			
			if($('input[name=ck_ce_resp_yn]:checked').length > 0){
				$("#ce_resp_yn").val("2");
			} else {
				$("#ce_resp_yn").val("1");
			}
		
			if($('input[name=ck_ce_resp_yn]:checked').length == 1){
				resp_yn_chk = true;
			}
		</c:if>
		
		//  로그인자가 IM인 경우
		<c:if test="${'RP0202' eq command.session_related_products }">
			
			if($('input[name=ck_im_resp_yn]:checked').length > 0){
				$("#im_resp_yn").val("2");
			} else {
				$("#im_resp_yn").val("1");
			}		
		
			if($('input[name=ck_im_resp_yn]:checked').length == 1){
				resp_yn_chk = true;
			}
		</c:if>

		if(!resp_yn_chk){
		   // 담당자 지정 유무 확인 	// 담당자 3명이상 유무 확인              
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
					if($(this).attr("disabled") == false && $(this).attr("value") == $('input[name=check_main]:checked').val()){ //  OWNER 를 체크하여 선택 한 경우  
						owner_flag = $(this).attr("alt");					
					} else { //  SUPPORT 인 경우 
						//alert($(this).attr("alt"));
					}					
				}
			);
			
			if(owner_flag!="" && owner_flag!=$("#sesssion_rel_pro").val()){
				alert("Assigning ownership to another side is not acceptable.");
				return;
			}
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
			// 부모창 HQ Reviewer  의 리스트 박스 세팅 처리 hq_apbt_memo
			opener.returnResp(respId, respNm, $("#apbt_memo").val(), $("#ce_resp_yn").val() , $("#im_resp_yn").val() , $("#hq_apbt_memo").val());
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
		
		<input type="hidden" name="sesssion_rel_pro"  id="sesssion_rel_pro"  value="<c:out value="${command.session_related_products}" />">		
	 	<input type="hidden" name="ce_resp_yn" id="ce_resp_yn"  value="<c:out value='${command.ce_resp_yn}'/>" >		
		<input type="hidden" name="im_resp_yn" id="im_resp_yn"  value="<c:out value='${command.im_resp_yn}'/>" >				
		
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
				<textarea name="hq_apbt_memo" id="hq_apbt_memo" alt='<spring:message code="las.page.field.contractManager.gcMsg"/>' cols="30" rows="7" maxLength="2000" onkeyup="frmChkLenLang(this,2000,'curByteExpl','<%=langCd%>')" class="text_area_full"><c:out value="${command.hq_apbt_memo}" escapeXml="false" /></textarea></td>
			</tr>
		</table>
		
		<br>
		<input type="checkbox" id="ck_ce_resp_yn" name="ck_ce_resp_yn" value="2" <c:if test="${command.ce_resp_yn == '2'}"> checked </c:if> /> CE DO NOT ASSIGN 		&nbsp;
		<input type="checkbox" id="ck_im_resp_yn" name="ck_im_resp_yn" value="2" <c:if test="${command.im_resp_yn == '2'}"> checked </c:if> /> IM DO NOT ASSIGN 
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
						   	<input type="checkbox" id="check_<c:out value='${list.respman_id}'/>" name="check_resp" title="<c:out value='${list.respman_nm}'/>" onClick="javascript:changeCheck(this.value)" value="<c:out value='${list.respman_id}'/>" <c:if test="${list.check_yn == 'Y'}"> checked </c:if>=""  alt="<c:out value="${list.RELATED_PRODUCTS}" />" >
						</td>
						<td class="tC" id="data_body"  alt="<c:out value="${list.RELATED_PRODUCTS}" />" >
							<input type="checkbox" id="ck_m_<c:out value='${list.respman_id}'/>" name="check_main" value="<c:out value='${list.respman_id}'/>" <c:if test="${list.main_cnsd_yn == 'Y'}"> checked </c:if>="" style="display:none;" alt="<c:out value='${list.RELATED_PRODUCTS_NM}' />" >
							<div id="div_<c:out value='${list.respman_id}'/>" alt="<c:out value="${list.RELATED_PRODUCTS}" />"  ><c:out value="${list.main_cnsd_yn_dp}" /></div>
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
		
		<c:if test="${ (command.session_related_products eq 'RP0201') and !(command.im_resp_yn eq '0') }">
			<span class="btn_all_w" onclick="Javascript:Save();"><span class="list"></span><a href="#"><spring:message code="las.page.field.contractManager.dsnt"/></a></span>
		</c:if>
		
		<c:if test="${ (command.session_related_products eq 'RP0201') and (command.im_resp_yn eq '0') }">
			<span class="btn_all_w" onclick="Javascript:Save();"><span class="save"></span><a href="#"><spring:message code="secfw.page.field.user.save"/></a></span>
		</c:if>
		
		<c:if test="${ (command.session_related_products eq 'RP0202') and !(command.ce_resp_yn eq '0') }">
			<span class="btn_all_w" onclick="Javascript:Save();"><span class="list"></span><a href="#"><spring:message code="las.page.field.contractManager.dsnt"/></a></span>
		</c:if>
		
		<c:if test="${ (command.session_related_products eq 'RP0202') and (command.ce_resp_yn eq '0') }">
			<span class="btn_all_w" onclick="Javascript:Save();"><span class="save"></span><a href="#"><spring:message code="secfw.page.field.user.save"/></a></span>
		</c:if>		
		

		<span class="btn_all_w mR5" onclick="self.close();"><span class="cancel"></span><a href="#"><spring:message code='las.page.button.close' /></a></span>
	</div>
</div>
<!-- //footer -->	

</body>
</html>