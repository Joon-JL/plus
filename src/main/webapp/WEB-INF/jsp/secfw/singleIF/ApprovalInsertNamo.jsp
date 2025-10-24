<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.Token" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%--
/**
 * 파     일     명 : ApprovalInsert.jsp
 * 프로그램명 : 결재내역  등록 페이지
 * 설               명 : 
 * 작     성     자 : 금현서
 * 작     성     일 : 2009.11
 */
--%>
<html>
<head>
<meta charset="utf-8" />

<title><spring:message code="secfw.page.title.singleIF.ApprovalInsert" /></title>

<link href="<c:url value='/style/secfw/popup.css' />" type="text/css" rel="stylesheet">
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript">
<!--

	/**
	* - 페이지 초기화 
	*/
	$(document).ready(function(){

	    var frm = document.frm;
	    
	    frm.target = "fileList";
	    frm.action = "<c:url value='/secfw/approval.do' />";
		frm.method.value = "goFileUploadPage";
		frm.submit();
		 
		$('#rcvTypeT').attr('checked', true);

		$("input[name='receiver_nm']").keydown(function(event){
			if(event.keyCode == "13") {
				searchUser();
				return false;				
			}
		});
		
		$('input:radio').click(function(event){
			var target = event.target;
			if(target.id == "rcvTypeT") {
				$('#receivers option:selected')
					.each(function(){
						var originValue = $(this).val();
						var originText  = $(this).text();

						var newValue;
						var newText;

						if(originValue.indexOf("c|") >= 0) {
							newValue = comStrReplace(originValue, "c|", "t|");
							newText  = comStrReplace(originText,  "/ 참조 /", "/ 수신 /");
						} else if(originValue.indexOf("b|") >= 0) {
							newValue = comStrReplace(originValue, "b|", "t|");
							newText  = comStrReplace(originText,  "/ 비밀참조 /", "/ 수신 /");
						} else {
							newValue = originValue;
							newText  = originText;
						}
						
						$(this).val(newValue);
						$(this).text(newText);
					});
			} else if(target.id == "rcvTypeC") {
				$('#receivers option:selected')
				.each(function(){
					var originValue = $(this).val();
					var originText  = $(this).text();
					
					var newValue;
					var newText;

					if(originValue.indexOf("t|") >= 0) {
						newValue = comStrReplace(originValue, "t|", "c|");
						newText  = comStrReplace(originText,  "/ 수신 /", "/ 참조 /");
					} else if(originValue.indexOf("b|") >= 0) {
						newValue = comStrReplace(originValue, "b|", "c|");
						newText  = comStrReplace(originText,  "/ 비밀참조 /", "/ 참조 /");
					} else {
						newValue = originValue;
						newText  = originText;
					}
					$(this).val(newValue);
					$(this).text(newText);
				});
			} else if(target.id == "rcvTypeB") {
				$('#receivers option:selected')
				.each(function(){
					var originValue = $(this).val();
					var originText  = $(this).text();

					var newValue;
					var newText;

					if(originValue.indexOf("t|") >= 0) {
						newValue = comStrReplace(originValue, "t|", "b|");
						newText  = comStrReplace(originText,  "/ 수신 /", "/ 비밀참조 /");
					} else if(originValue.indexOf("c|") >= 0) {
						newValue = comStrReplace(originValue, "c|", "b|");
						newText  = comStrReplace(originText,  "/ 참조 /", "/ 비밀참조 /");
					} else {
						newValue = originValue;
						newText  = originText;
					}
					
					$(this).val(newValue);
					$(this).text(newText);
				});
			}

			$('#receivers').focus();
		});
		
	});

	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		var frm = document.frm;
	
		if(flag == "insert"){

			//결재경로 정합성 체크
			if(!checkApprovalPath()) return;
			
			//FORM Validation
			if(validateForm(document.frm) != false) { 
	
				//첨부파일 업로드
				var uploadCount = fileList.UploadFile();
	
				$('#receivers option')
				.each(function(){
					$(this).attr("selected", true);
				});

				frm.target = '_self';
				frm.action = "<c:url value='/secfw/approval.do' />";
			    frm.method.value = "insertApproval";
				frm.submit();
				
			}
		} else if(flag == "refresh"){

			frm.action = "<c:url value='/secfw/approval.do' />";
			frm.method.value = "goInsertApproval"
			frm.submit();

		} else if(flag == "temp"){

			if(!checkApprovalPath()) return;

			//Form Validation
			if(validateForm(document.frm) != false) { 
				
				//첨부파일 업로드
				var uploadCount = fileList.UploadFile();
	
				$('#receivers option')
				.each(function(){
					$(this).attr("selected", true);
				});
						
				frm.tempSave.value = 'Y';
	
				$('#receivers option')
				.each(function(){
					$(this).attr("selected", true);
				});

				frm.target = '_self';
				frm.action = "<c:url value='/secfw/approval.do' />";
			    frm.method.value = "insertApproval";
				frm.submit();
				
			}
		} 
		// 팝업으로 사용시 주석해제
		//else if(flag == "close"){
		//	self.close();
		//} 
	}

	/**
	* 임직원 조회 function
	*/	
	function searchUser()
	{
		var frm = document.frm;
		PopUpWindowOpen('', 600, 450, 'searchUser');
		frm.target = "PopUpWindow";

		frm.action = "<c:url value='/secfw/user.do' />";
		frm.method.value = "listUser";

		frm.srch_user_cntnt.value = frm.receiver_nm.value;
		frm.multiChk.value = "N";
		frm.doUserSearch.value = "Y";
	
		frm.submit();
	}

	/**
	* 임직원정보 Setting
	*/	
	function setUserInfo(obj) {
		
		var name     = obj.user_nm;
		var type;
		var jikgupCd = obj.jikgup_cd;
		var jikgupNm = obj.jikgup_nm;
		var deptNm   = obj.dept_nm;
		var compNm   = obj.comp_nm;
		var email    = obj.email;
		var typeNm;
	
		type = $('input:radio:checked').val();
	
		if(type == '1') {
			typeNm = '결재';
		} else if(type == '2') {
			typeNm = '합의';
		} else if(type == '3') {
			typeNm = '후결';
		} else if(type == '9') {
			typeNm = '통보';
		}
		
		if(email == null || comTrim(email) == '') {
			alert("<spring:message code='secfw.page.field.singleIF.noEmailApprAccount'/>");
			return;
		}
	
		var flag = true;
		
		//기존 추가된 사용자 인지 체크
		$('#receivers option')
		.each(function(){
			var originValue = $(this).val();
			if(originValue.indexOf(email) >= 0) {
				flag = false;
			}
		});
	
		if(!flag) {
			alert("<spring:message code='secfw.page.field.singleIF.regsteredUser'/>");
			return;
		} else {
			var html = "<option value='" + type + "|" + email + "'>"
	         + name + " / " + typeNm + " / " + jikgupCd + "(" + jikgupNm + ")" + " / " + deptNm + " / " + compNm + " / " + email
	         + "</option>";
	
			$('#receivers').append(html);
		}
	
	}

	/**
	* 병렬지정
	*/	
	function setParallel(upDown)
	{

		var receiverCnt = 0;

		$('#receivers option')
		.each(function(){
			receiverCnt = receiverCnt + 1;
		});

		if(!(receiverCnt > 1)) {
			alert("<spring:message code='secfw.page.field.singleIF.pickParallelAppr'/>");
			return;
		}

		
		$('#receivers option:selected')
		.each(function(){
			var originValue = $(this).val();
			var originText  = $(this).text();

			var newValue;
			var newText;

			//결재, 합의만 병렬가능
			if(originValue.indexOf("1|") >= 0 || originValue.indexOf("2|") >= 0) {
			} else {
				alert("<spring:message code='secfw.page.field.singleIF.onlyForRepr'/>");
				return;
			}

			
			if(originValue.indexOf("t|") >= 0) {
				newValue = comStrReplace(originValue, "t|", "b|");
				newText  = comStrReplace(originText,  "/ 수신 /", "/ 비밀참조 /");
			} else if(originValue.indexOf("c|") >= 0) {
				newValue = comStrReplace(originValue, "c|", "b|");
				newText  = comStrReplace(originText,  "/ 참조 /", "/ 비밀참조 /");
			} else {
				newValue = originValue;
				newText  = originText;
			}
			
			$(this).val(newValue);
			$(this).text(newText);
		});

		$('#receivers').focus();
	}

	/**
	* 병렬해제
	*/	
	function cancelParallel(upDown)
	{

		$('#receivers option:selected')
		.each(function(){
			var originValue = $(this).val();
			var originText  = $(this).text();

			var newValue;
			var newText;

			if(originValue.indexOf("t|") >= 0) {
				newValue = comStrReplace(originValue, "t|", "b|");
				newText  = comStrReplace(originText,  "/ 수신 /", "/ 비밀참조 /");
			} else if(originValue.indexOf("c|") >= 0) {
				newValue = comStrReplace(originValue, "c|", "b|");
				newText  = comStrReplace(originText,  "/ 참조 /", "/ 비밀참조 /");
			} else {
				newValue = originValue;
				newText  = originText;
			}
			
			$(this).val(newValue);
			$(this).text(newText);
		});

		$('#receivers').focus();
	}
		
	
	/**
	* 결재순서조정
	*/	
	function moveOrder(upDown)
	{
		var rightDocLen = frm.receivers.options.length;

		var tempName = "" ;
		var tempValue = "" ;

		if(upDown == "U") {
			for ( i = 0; i < rightDocLen; i++)
			{
				if( frm.receivers.options[i].selected && i != 0 && !frm.receivers.options[i - 1].selected)
				{
					tempName = frm.receivers.options[i - 1].value ;
					tempValue = frm.receivers.options[i - 1].text ;
	
					frm.receivers.options[i - 1].value = frm.receivers.options[i].value ;
					frm.receivers.options[i - 1].text  = frm.receivers.options[i].text;
	
					frm.receivers.options[i].value = tempName ;
					frm.receivers.options[i].text  = tempValue;
	
					frm.receivers.options[i - 1].selected = true ;
					frm.receivers.options[i].selected = false ;
				}
				else if( i == 0 && frm.receivers.options[i].selected)
				{
					frm.receivers.options[i].selected = true ;
				}
			}
		} else {
			for ( i = (rightDocLen - 1); i >= 0; i--)
			{
				if( frm.receivers.options[i].selected && i != (rightDocLen - 1) && !frm.receivers.options[i + 1].selected)
				{
					tempName = frm.receivers.options[i + 1].value ;
					tempValue = frm.receivers.options[i + 1].text ;
	
					frm.receivers.options[i + 1].value = frm.receivers.options[i].value ;
					frm.receivers.options[i + 1].text  = frm.receivers.options[i].text;
	
					frm.receivers.options[i].value = tempName ;
					frm.receivers.options[i].text  = tempValue;
	
					frm.receivers.options[i + 1].selected = true ;
					frm.receivers.options[i].selected = false ;
				}
				else if( i == (rightDocLen - 1) && frm.receivers.options[i].selected)
				{
					frm.receivers.options[i].selected = true ;
				}
			}
		}
	}

	/**
	* 수신자 삭제
	*/
	function deleteReceiver() {
		$('#receivers option:selected').remove();
	}

	/**
	* 결재경로 정합성 체크
	*/
	function checkApprovalPath() {

		//수신인여부 체크
		var receiverCnt = 0;

		$('#receivers option')
		.each(function(){
			receiverCnt = receiverCnt + 1;
		});

		if(!(receiverCnt > 0)) {
			alert("<spring:message code='secfw.page.field.singleIF.noRecipient'/>");
			return;
		}

		
	// 결재/합의자가 한명은 있어야 한다.

	// 후결자는 한명만 지정

	}
//-->

</script>

</head>
<body class="pop">
<!-- **************************** Form Setting **************************** -->
<form:form id="frm" name="frm" method="post" autocomplete="off">
	<!-- 페이지 공통 -->
	<input type="hidden" name="method" value="">
	
	<!-- 첨부파일정보 -->
	<input type="hidden" name="fileInfos" 	value="" />
	<input type="hidden" name="view_gbn" 	value="upload" />
	
	<!-- 사용자검색 -->
	<input type="hidden" name="multiChk" value="" />
	<input type="hidden" name="srch_user_cntnt" value="" />
	<input type="hidden" name="srch_user_cntnt_type" value="user_nm" />
	<input type="hidden" name="doUserSearch" value="" />
	
	<!-- 이중등록 방지 -->
	<input type="hidden" name="tempSave" 	value="" />
	<input type="hidden" name="TOKEN"  value="<%=Token.set(request) %>">
	
	<!-- 각 업무단 분류 및 키값 생성(해당 업무별로 수정후 작업바람) -->
	<!-- 1.module_id는 기본적으로 property에 설정. -->
	<!-- 2.mis_id는 해당 업무분류 별 중복발생하지 않는 값으로 설정 -->
	<input type="hidden" name="module_id" 	value="KUM_MODULE" />
	<input type="hidden" name="mis_id"      value="<%=DateUtil.getTime("yyyyMMddHHmmss") %>">
	
	
<!-- //**************************** Form Setting **************************** -->
<!-- Popup Top -->
<h1 class="pop_top"><spring:message code="secfw.page.title.singleIF.ApprovalInsert" /></h1>
<!-- //Popup Top -->
<!-- Popup Body-->
<div class="pop_body">
	<div class="pop_area">
		<!-- Popup Detail -->
		<div class="dscrolly 650">
			<div class="pop_content">
				<!-- List -->
				<table class="detail_form">
					<colgroup>
					<col width="10%"/>
					<col/>
					</colgroup>
					<tr>
						<th class="strong_b"><spring:message code="secfw.page.field.approval.subject" /></th>
					  <td  class="strong_g"><input type="text" name="subject" class="text all" value="" fieldTitle="<spring:message code="secfw.page.field.approval.subject" />" required="*" maxLength="100" /></td>
					</tr>
					<tr>
						<th><spring:message code="secfw.page.field.approval.to" /></th>
						<td colspan="2" class="pz">
							<table class="basic_form_inner">
								<colgroup>
								<col width="50%"/>
								<col width="37%"/>
								<col width="70px"/>
								</colgroup>
								<tr>
									<td><!-- search -->
										<input name="receiver_nm" type="text"  class="input_search" value="" /><img src="<c:url value='/images/secfw/ico/ico_input_search.gif' />" onClick="javascript:searchUser();" class="cp">
										<!-- //search --></td>
									<td class="tR">
										<a href="javascript:setParallel();" class="bt_option"><span><img src="<c:url value='/images/secfw/ico/row_up.gif' />"><spring:message code="secfw.page.field.approval.parallel" /></span></a> 
										<a href="javascript:cancelParallel();" class="bt_option"><span><img src="<c:url value='/images/secfw/ico/row_cancel_up.gif' />"><spring:message code="secfw.page.field.approval.releaseParallel" /></span></a> 
										<a href="javascript:moveOrder('U');" class="bt_option"><span><img src="<c:url value='/images/secfw/ico/ico_up.gif' />"><spring:message code="secfw.page.field.approval.moveUp" /></span></a> 
										<a href="javascript:moveOrder('D');" class="bt_option"><span><img src="<c:url value='/images/secfw/ico/ico_down.gif' />"><spring:message code="secfw.page.field.approval.moveDown" /></span></a> 
										<a href="javascript:deleteReceiver();" class="bt_option"><span><img src="<c:url value='/images/secfw/ico/ico_delete.gif' />"><spring:message code="secfw.page.field.approval.deleteReceiver" /></span></a>
									</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td colspan="2">
										<select id="receivers" name="receivers" size="6" multiple="MULTIPLE" class="approval_select_multi">
										</select>
									</td>
									<td valign="top">
										<p><input id="activity1" type="radio" name="rdSendItem" value="1" class="radio"><spring:message code="secfw.page.field.approval.approval" /> <span id="activity1Cnt"></span></p>
										<p><input id="activity2" type="radio" name="rdSendItem" value="2" class="radio"><spring:message code="secfw.page.field.approval.consent" /> <span id="activity2Cnt"></span></p>
										<p><input id="activity3" type="radio" name="rdSendItem" value="3" class="radio"><spring:message code="secfw.page.field.approval.postApproval" /> <span id="activity3Cnt"></span></p>
										<p><input id="activity9" type="radio" name="rdSendItem" value="9" class="radio"><spring:message code="secfw.page.field.approval.notify" /> <span id="activity9Cnt"></span></p>
									</td>
								</tr>
							</table>
						</td>
					</tr>					
					<tr>
						<th><spring:message code="secfw.page.field.approval.approvalCntnt" /></th>
						<td>
						<%-- Namo Active Square 7 --%>
						<%@ include file="/WEB-INF/jsp/secfw/common/NamoEditInclude.jspf"%>
						<%-- //Namo Active Square 7 --%>
						</td>
					</tr>
					<tr>
	            		<th><spring:message code="secfw.page.field.singleIF.attachedFile"/><span class="astro"></span></th>
	            		<td colspan="5">
	            		<iframe src="<c:url value='/las/blank.do' />" name="fileList" frameborder="0" width="100%" height="98px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
	            		</td>
	          		</tr>
				</table>
			  <!-- //List -->
			</div>
		</div>
		<!-- //Popup Detail -->
	</div>
</div>
</form:form>
<!-- //Popup Body -->
<!-- Popup Footer -->
<div class="pop_footer">
	<!-- Function Button -->
    <a href="javascript:pageAction('insert');" class="bt_all_b"><span><img src="<c:url value='/images/secfw/ico/ico_save.gif' />"><spring:message code="secfw.page.button.send" /></span></a>
    <a href="javascript:pageAction('temp');" class="bt_all_b"><span><img src="<c:url value='/images/secfw/ico/ico_save.gif' />"><spring:message code="secfw.page.button.tempSave" /></span></a>
    <a href="javascript:pageAction('refresh');" class="bt_all_w"><span><img src="<c:url value='/images/secfw/ico/ico_reset.gif' />"><spring:message code="secfw.page.button.refresh" /></span></a>
	<!-- 팝업으로 사용시 주석해제 -->
    <!-- <a href="javascript:pageAction('close');" class="bt_all_w"><span><img src="<c:url value='/images/secfw/ico/ico_cancel.gif' />">닫기</span></a> -->
	<!-- // Function Button -->
</div>
<!-- //Popup Footer -->
</body>
</html>