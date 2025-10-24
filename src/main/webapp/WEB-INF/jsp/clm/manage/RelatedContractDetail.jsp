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
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ include file= "/WEB-INF/jsp/common/common.jsp" %>
<%@ include file="/WEB-INF/jsp/common/messageUtil.jsp" %>
<!-- 
 * 파  일  명 : RelatedContractDetail.jsp
 * 프로그램명 : Termination Detail
 * 설      명 : 
 * 작  성  자 : 박정훈
 * 작  성  일 : 2013.04
-->
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	ArrayList authReqList = (ArrayList)request.getAttribute("authReqList");  // 관련자 정보
	ListOrderedMap contractMstLom = (ListOrderedMap)request.getAttribute("contractMstLom");
	//===================================================================
	//검토이력, 승인이력 관련 끝
	//===================================================================
	System.out.println("------------------------ RelatedContractDetail.jsp-------------------------------------------");
%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet"/>
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery-1.7.2.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="/script/clms/common.js" type="text/javascript"></script>
<script language="javascript" src="/script/clms/clms_common.js" type="text/javascript"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script src="/script/func/ConsiderationFunc.js"></script><!-- 2014-07-24 Kevin added -->
<script language="javascript"  >

$(document).ready(function(){
	// 2014-07-24 Kevin added.
	// Following codes retrive step/status information and show them.
	var considerationFunc = new ConsiderationFunc($);
	considerationFunc.getStepStatusInfoPerCnsdreqid($("#cnsdreq_id").val(), function(data){
		$("#spStep").text(data.step);
		$("#spStatus").text(data.status+" "+data.status_depth);
	});
	 
	listContractRole();
	
	// 2014-04-16 Kevin Added.
    // GERP readonly iframe load. 
    var frm = document.frm;
    frm.target          = "iframeGERP";
    frm.action          = "<c:url value='/clm/manage/consideration.do' />";
    frm.gerpPageType.value = "R";		// detail readonly
    frm.method.value    = "forwardGERPDetail";
	frm.submit();
	
	attachPage('<c:out value="${contractMstLom.cntrt_id}" />');
	contractHisList();	//Sungwoo added 2014-09-04
});

	//첨부파일 Sungwoo 2014-09-04 스크립트 위치이동
	function attachPage(temp_cntrt_id){
		
		var frm = document.frm;
		
	    //게약서파일
		frm.target          = "fileList";
		frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value    = "forwardClmsFilePage";
		frm.fileInfoName.value = "fileInfos";
		frm.fileFrameName.value = "fileList";
		
		frm.file_bigclsfcn.value = "F012";
		frm.file_midclsfcn.value = "F01202";
		//Sungwoo replaced 2014-09-30
		if(frm.cntrt_info_gbn.value =='C05401'){
			frm.file_smlclsfcn.value = "F0120202";
		}else{
			frm.file_smlclsfcn.value = "F0120201";
		}
		frm.ref_key.value = temp_cntrt_id + "@<c:out value='${command.cnsdreq_id}'/>";
		frm.view_gbn.value = "download";
		frm.submit();
		
	    //계약서- 첨부/별첨
	    frm.target          = "fileList3";
		frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value    = "forwardClmsFilePage";
		frm.fileInfoName.value = "fileInfos3";
		frm.fileFrameName.value = "fileList3";
		frm.file_bigclsfcn.value = "F012";
		frm.file_midclsfcn.value = "F01202";
		//Sungwoo replaced 2014-09-30
		if(frm.cntrt_info_gbn.value =='C05401'){
			frm.file_smlclsfcn.value = "F0120209";
		}else{
			frm.file_smlclsfcn.value = "F0120208";
		}
		frm.ref_key.value = temp_cntrt_id + "@<c:out value='${command.cnsdreq_id}'/>";
		frm.view_gbn.value = "download";
		frm.submit();
		
		//계약서 기타_체결본 파일
		frm.target          = "fileList2";
		frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value    = "forwardClmsFilePage";
		frm.fileInfoName.value = "fileInfos2";
		frm.fileFrameName.value = "fileList2";
		frm.file_bigclsfcn.value = "F012";
		frm.file_midclsfcn.value = "F01202";
		//Sungwoo replaced 2014-09-30
		if(frm.cntrt_info_gbn.value =='C05401'){
			frm.file_smlclsfcn.value = "F0120206";
		}else{
			frm.file_smlclsfcn.value = "F0120205";
		}
		frm.ref_key.value = temp_cntrt_id + "@<c:out value='${command.cnsdreq_id}'/>";
		frm.view_gbn.value = "download";
		frm.submit();
		
		//계약서 사전검토 파일
		frm.target          = "fileList33";
		frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value    = "forwardClmsFilePage";
		frm.fileInfoName.value = "fileInfos33";
		frm.fileFrameName.value = "fileList33";
		frm.file_bigclsfcn.value = "F012";
	    frm.file_midclsfcn.value = "F01201";
	    frm.file_smlclsfcn.value = "F0120101";
		frm.ref_key.value = temp_cntrt_id;
		frm.view_gbn.value = "download";
		frm.submit();
		
		//체결본사본
		frm.target          		= "fileList4";
	    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    		= "forwardClmsFilePage";
	    frm.fileInfoName.value 		= "fileInfos4";
	    frm.fileFrameName.value 	= "fileList4";
	    frm.file_bigclsfcn.value = "F012";
	    frm.file_midclsfcn.value	= "F01203";
	    frm.file_smlclsfcn.value 	= "";
	    frm.ref_key.value			= temp_cntrt_id;
	    
	    frm.submit();
		
	  //OutLook 첨부파일 2013.10.25 추가
	    frm.target          = "fileListOut";
	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    = "forwardClmsFilePage";
	    frm.file_bigclsfcn.value = "O001";
	    frm.file_midclsfcn.value = "O0011";
	    frm.file_smlclsfcn.value = "O00111";
	    frm.ref_key.value = frm.cntrt_id.value + "@" + frm.cnsdreq_id.value;
	    frm.fileInfoName.value = "fileListOut";
	    frm.fileFrameName.value = "fileListOut";
	    frm.view_gbn.value			= "download";
	    frm.submit();
	    
	  	//단가내역
	    if("<c:out value='${contractMstLom.cntrt_untprc_expl}' />" != "") {
	        frm.target          		= "fileConsultationUntPrc";
	        frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
	        frm.method.value    		= "forwardClmsFilePage";
	        frm.file_midclsfcn.value 	= "F01202";
	        frm.file_smlclsfcn.value 	= "F0120212";
	        frm.ref_key.value   		= frm.cntrt_id.value + "@" + frm.cnsdreq_id.value;
	        frm.fileInfoName.value 		= "fileInfos5";
	        frm.fileFrameName.value 	= "fileConsultationUntPrc";
	        frm.submit();
	    }
	    
		//종료관리 파일
		frm.target          = "fileList10";
		frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value    = "forwardClmsFilePage";
		frm.fileInfoName.value = "fileInfos10";
		frm.fileFrameName.value = "fileList10";
		frm.file_bigclsfcn.value = "F012";
		frm.file_midclsfcn.value = "F01204";
		frm.file_smlclsfcn.value = "";
		frm.ref_key.value   = temp_cntrt_id + "@<c:out value='${command.cnsdreq_id}'/>";
		
		//의뢰인, 담당자인 경우 첨부파일 유형변경
		if($('#cntrt_chge_demndman_id').length < 1){
			frm.view_gbn.value = "download";
			frm.submit();
		}else{
			frm.view_gbn.value = "modify";
			frm.submit();
		}
	}
	
	

	
	
//거래선 팝업
function customerPop2(customerCd, dodun){
	PopUpWindowOpen("/clm/draft/customerTest.do?method=detailCustomerShare&customer_cd="+customerCd + "&dodun=" + dodun, 900, 300, false);
}
//TOP 30 팝업
function openTop30Customer(){
	   PopUpWindowOpen2("/clm/draft/customerTest.do?method=listTop30Customer", 500, 540, false, "Top30Customer");
}
/**
* 권한에따른 버튼제어
*/
function listContractRole(){
	var options = {
		url: "<c:url value='/clm/manage/completion.do?method=listContractRole' />",
		type: "post",
		dataType: "json",
		success: function(responseText,returnMessage) {
			if(responseText != null && responseText.length != 0) {
				$.each(responseText, function(entryIndex, entry) {
					//2014-09-10 Sungwoo replacement script
					if("<c:out value='${completionLom.depth_status}'/>" == 'C02681' 
						&& "<c:out value='${completionLom.cntrt_status}'/>" == 'C02404' 
						|| "<c:out value='${completionLom.cntrt_status}'/>" == 'C02405'
					){
						$("#btn_15").css('display',''); // Bottom Button  : Submit for Termination Approval 
						$("#btn_13").css('display',''); // Bottom Button  : Save
						$("#btn_05").css('display','');	// Top Button     : Submit for Termination Approval
						$("#btn_03").css('display',''); // Top Button     : Save
					}else if("<c:out value='${completionLom.depth_status}'/>" != 'C02685'
							&& "<c:out value='${completionLom.cntrt_status}'/>" == 'C02402'
							|| "<c:out value='${completionLom.cntrt_status}'/>" == 'C02404' 
							|| "<c:out value='${completionLom.cntrt_status}'/>" == 'C02405'
							
					){	//Sungwoo added 2014-06-25
						if("<c:out value='${completionLom.depth_status}'/>" != 'C02685'){
							$("#btn_13").css('display','');
							$("#btn_15").css('display','');
							$("#btn_05").css('display','');	
							$("#btn_03").css('display','');
						}
					}
					//결재중이면 termination button 제거
					var doNotAllowButtonStatus=['C02682','C02685','C02686','C02688','C02689']
					if(doNotAllowButtonStatus.indexOf(entry.depth_status)>-1 ){
						//상단
						if( $('#btn_05').length ){
							$("#btn_05").css('display','');
						}
						if( $('#btn_06').length ){
							$("#btn_06").css('display','');
						}
						if( $('#btn_03').length ){
							$("#btn_03").css('display','');
						}
						
						//하단
						if( $('#btn_15').length ){
							$("#btn_15").css('display','');
						}
						if( $('#btn_16').length ){
							$("#btn_16").css('display','');
						}
						if( $('#btn_17').length ){
							$("#btn_17").css('display','');
						}
						if( $('#btn_13').length ){
							$("#btn_13").css('display','');
						}
						
						 
					}
				});
			}
		}
	};
	$("#frm").ajaxSubmit(options);	
}
//이행단계로 변경
function modifyStatus(){
	var frm = document.frm;
	if(confirm("<spring:message code="clm.page.msg.manage.announce134" />")){
		frm.target = "_self";		 
		frm.action = "<c:url value='/clm/manage/completion.do' />";
		frm.method.value = "modifyCompletionStatus";
		viewHiddenProgress(true) ;
		frm.submit();
	}
}
/**
* 목록
*/
function listManageCompletion(){
	var frm = document.frm;
		
	if(frm.ismycontract.value == 'Y'){
		frm.target = "_self";		 
		frm.action = "<c:url value='/clm/manage/myContract.do' />";
		frm.method.value = "listMyContract";
		viewHiddenProgress(true) ;
		frm.submit();
	}
	else{
		frm.target = "_self";		 
		frm.action = "<c:url value='/clm/manage/completion.do' />";
		frm.method.value = "listManageCompletion";
		viewHiddenProgress(true) ;
		frm.submit();	
	}
}

//인터페이스작업[GSEMS로 계약정보 보내기] ajax로 리턴
function listSSemsIF() {
	
	var options = {
		url: "<c:url value='/clm/manage/conclusion.do?method=listSSemsIF'/>",
		type: "post",
		async: false,
		dataType: "json",
		success: function (data) {
			if(data != null && data.length != 0) {
				$.each(data, function(entryIndex, entry) {
					alert(entry['RESULT_MSG']);					
				});
			}
		}
	};
	//GSEMS로 계약서를 전송하시겠습니까? 10분 후에 GSEMS에서 확인하시기 바랍니다
	if(confirm("<spring:message code="clm.page.msg.manage.announce008" />" + "<spring:message code="clm.page.msg.manage.announce003" />")){
		$("#frm").ajaxSubmit(options);		
	}
}

/**
 * 종료관리임시저장
 */
function insertTempCompletion() {

	var frm = document.frm;
	frm.cntrt_chge_demndday.value="";
	if(!$(frm.cntrt_status).is(':checked')){
		alert("<spring:message code="clm.page.msg.manage.announce160" />");
		return;
	}
	
	//유효성 체크
    if(validateForm(frm)){
     	if(confirm("<spring:message code='clm.page.msg.manage.announce137'/>")){
			fileList10.UploadFile(function(uploadCount){
			    //첨부파일 업로드 결과 잘못된 형식의 파일이 올라간 경우
			    if(uploadCount == -1){
			        alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
			        return;
			    }
			    
				frm.file_bigclsfcn.value = "F012";
				frm.file_midclsfcn.value = "F01204";
				frm.file_smlclsfcn.value = "";
				frm.ref_key.value = "<c:out value='${command.cntrt_id}'/>" + "@<c:out value='${command.cnsdreq_id}'/>";
			    frm.action = "<c:url value='/clm/manage/completion.do' />";
				frm.method.value = "insertTempCompletion";
				frm.target = "_self";
				viewHiddenProgress(true) ;
				frm.submit();
			});	
    	}
    }
}
/**
 * 종료관리등록
 */
function insertCompletion() {
	
	var frm = document.frm;
	if(frm.cntrt_status.length > 2){
		if(frm.cntrt_status[0].checked == false && frm.cntrt_status[1].checked == false && frm.cntrt_status[2].checked == false) {
			alert("<spring:message code="clm.page.msg.manage.announce160" />");
			return;
		}
		if(frm.cntrt_status[2].checked){
			if(frm.cntrtperiod_endday.value == ""){
				alert("<spring:message code="clm.page.msg.manage.announce091" />");
				return;
			}
		}else{
			if($('#approvalman_jikgup_nm').html() == '') {
				alert("<spring:message code="clm.page.msg.manage.announce110" />");
				return;
			}
			frm.conListGu.value = "98";
		}
	}else{
		if(frm.cntrt_status[0].checked == false && frm.cntrt_status[1].checked == false) {
			alert("<spring:message code="clm.page.msg.manage.announce160" />");
			return;
		}
		if($('#approvalman_jikgup_nm').html() == '') {
			alert("<spring:message code="clm.page.msg.manage.announce110" />");
			return;
		}
		frm.conListGu.value = "98";
	}
	
	if(confirm("<spring:message code='las.msg.ask.insert'/>")){
		if(validateForm(frm)){
			viewHiddenProgress(false);
			fileList10.UploadFile(function(uploadCount){
			    //첨부파일 업로드 결과 잘못된 형식의 파일이 올라간 경우
			    if(uploadCount == -1){
			        alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
			        return;
			    }
			    frm.file_bigclsfcn.value = "F012";
				frm.file_midclsfcn.value = "F01204";
				frm.file_smlclsfcn.value = "";
				frm.ref_key.value = "<c:out value='${command.cntrt_id}'/>" + "@<c:out value='${command.cnsdreq_id}'/>";
				frm.action = "<c:url value='/clm/manage/completion.do' />";
				frm.method.value = "insertCompletion";
				frm.target = "_self";
				viewHiddenProgress(true) ;
				frm.submit();
			});
		}
	}
}


/**
 * 종료 신청
 */
function requestTermination() {
	
	var frm = document.frm;
	if(frm.cntrt_status.length > 2){
		if(frm.cntrt_status[0].checked == false && frm.cntrt_status[1].checked == false && frm.cntrt_status[2].checked == false) {
			alert("<spring:message code="clm.page.msg.manage.announce160" />");
			return;
		}
		if(frm.cntrt_status[2].checked){
			if(frm.cntrtperiod_endday.value == ""){
				alert("<spring:message code="clm.page.msg.manage.announce091" />");
				return;
			}
		}else{
			if($('#approvalman_jikgup_nm').html() == '') {
				alert("<spring:message code="clm.page.msg.manage.announce110" />");
				return;
			}
			frm.conListGu.value = "98";
		}
	}else{
		if(frm.cntrt_status[0].checked == false && frm.cntrt_status[1].checked == false) {
			alert("<spring:message code="clm.page.msg.manage.announce160" />");
			return;
		}
		if($('#approvalman_jikgup_nm').html() == '') {
			alert("<spring:message code="clm.page.msg.manage.announce110" />");
			return;
		}
		frm.conListGu.value = "98";
	}
	
	if(confirm("<spring:message code='las.msg.ask.insert'/>")){
		if(validateForm(frm)){
			viewHiddenProgress(false);
			
			fileList10.UploadFile(function(uploadCount){
			    //첨부파일 업로드 결과 잘못된 형식의 파일이 올라간 경우
			    if(uploadCount == -1){
			        alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
			        return;
			    }
			   
			    frm.file_bigclsfcn.value = "F012";
				frm.file_midclsfcn.value = "F01204";
				frm.file_smlclsfcn.value = "";
				frm.ref_key.value = "<c:out value='${command.cntrt_id}'/>" + "@<c:out value='${command.cnsdreq_id}'/>";
				
				
			    var options = {
						url: "<c:url value='/clm/manage/completion.do?method=insertCompletion' />",
						type: "post",
						dataType: "html",
						beforeSubmit: function(formData, form){
							viewHiddenProgress(true);
						},			
						success: function(data) {
							
							$('#contract-CompletionInfo').html("");
							$('#contract-CompletionInfo').html(data);
							
							listContractRole();
							
							//업로드 된 파일창 현재 화면에 다시 뿌리기...
							//attachReloadPage();
							
							//상신
							detailApprovalPop();
						}
					};
					$("#frm").ajaxSubmit(options);	
				});
		}
	}
}

function insertApproval() {
	var frm = document.frm;
	var options = {
	 		url: "<c:url value='/clm/manage/completion.do?method=makeApprovalHtmlDirect' />",
	 		type: "post",
	 		async: false,
	 		dataType: "json",
	 			
	 		success: function(responseText,returnMessage) {
				if(responseText.returnValue=="Y"){
					viewHiddenProgress(false);
					detailBaseContract(frm.cnsdreq_id.value,frm.cntrt_id.value);
				}else {
					viewHiddenProgress(false);
					alert(responseText.returnMessage);
				}
			
				postProcessAfterTerminationApproval();
	 		}
	   };
	   $("#frm").ajaxSubmit(options);
}


function postProcessAfterTerminationApproval(){
	
	executionDetailHislist();
	listContractRole();
	contractHisList();
	location.reload();
}

/**
 * 이행및종료이력정보
 */
function executionDetailHislist() {

	var options = {
		url: "<c:url value='/clm/manage/completion.do?method=listDetailExecutionHis' />",
		type: "post",
		async: false,
		dataType: "html",
		error: function(e){
			$("#contractExeCmpltHis-list").html("ERROR");
		},
		success: function (data) {
			//$("#contractExeCmpltHis-list").html("");
			$("#contractExeCmpltHis-list").html("");
			$("#contractExeCmpltHis-list").html(data);
						
			$('#execution-mng-content').attr("style","display:");
			$('#execution-mng2-content').attr("style","display:none");
			$('#execution-mng3-content').attr("style","display:none");			
			$('#selected_paygbn').val("tab1");	
			
			// 기타이행탭 활성화
			if($('#payment_gbn').val() == 'C02004'){
				$('#execution-DetailTab3').click();
				$('#execution-mng3-content_tb').attr("style","display:");
				$('#execution-DetailTab3').addClass('on');
				$('#selected_paygbn').val("tab3");	
			}
			
		}
	};
	$("#frm").ajaxSubmit(options);
}


//상신팝업
function detailApprovalPop() {
	var frm = document.frm;
	var options = {
 		url: "<c:url value='/clm/manage/completion.do?method=detailPreviewPop' />",
 		type: "post",
 		async: false,
 		dataType: "html",
 			
 		success: function(data){
 			frm.contents.value=data;
 			insertApproval(); 			
 		}
   };
   $("#frm").ajaxSubmit(options);
}

//팝업오픈
function PopUpWindowOpen(surl, popupwidth, popupheight, bScroll, popName) {
	if( popupwidth  > window.screen.width )
		popupwidth = window.screen.width;
	if( popupheight > window.screen.height )
		popupheight = window.screen.height; 
		
	if( isNaN(parseInt(popupwidth)) ){
		Top  = (window.screen.availHeight - 600) / 2;
		Left = (window.screen.availWidth  - 800) / 2;
	} else {
		Top  = (window.screen.availHeight - popupheight)  / 2;
		Left = (window.screen.availWidth  - popupwidth) / 2;
	}

	if (Top < 0) Top = 0;
	if (Left < 0) Left = 0;
	
	popupwidth = parseInt(popupwidth) + 10 ;
	popupheight = parseInt(popupheight) + 29 ;
	
	var Feture = "toolbar=0, location=0, status=1, menubar=0, scrollbars=yes, resizable=yes, top="+Top+", left="+Left+", width="+popupwidth+", height="+popupheight;
	PopUpWindow = window.open(surl, popName , Feture);

	PopUpWindow.focus();
	
}
//의뢰자검색
function searchEmployee(obj) {
	var frm 		= document.frm;
	var srchValue 	= "";
	srchValue = comTrim(obj.value);
    frm.target = "PopUpWindow";
    frm.srch_user_cntnt_type.value = "userName"; //이름으로 검색  - 보통 이름으로 검색함
    frm.action = "<c:url value='/secfw/esbOrg.do' />";
    frm.method.value = "listEsbEmployee";
    frm.srch_user_cntnt.value = srchValue;
    if (srchValue == "" || srchValue.length < 4) { //최소 2자리 이상 입력받게 한다.
        alert('<spring:message code='secfw.msg.error.nameMinByte' />');
        obj.focus();
    } else {
    	PopUpWindowOpen('', 800, 450, true, 'PopUpWindow');	//completion_l 에 정의된 메서드
    	frm.submit();
    }    	
} 

/**
 * 임직원 조회정보 결과 setting
 */
function setUserInfos(obj) {
     var userInfo = obj; 
     var frm = document.frm;
     
    //사전품의발의자
     frm.approvalman_id.value = userInfo.epid;
	 frm.approvalman_nm.value = userInfo.cn;
	 frm.approvalman_search_nm.value = userInfo.cn;
	 frm.approvalman_dept_nm.value = userInfo.department;
	 frm.approvalman_jikgup_nm.value=userInfo.title;
	 $('#approvalman_jikgup_nm').html(userInfo.title);
	 $('#approvalman_dept_nm').html(userInfo.department);
}

//자동연장 만료일 설정 row display
function viewAutoDetail(autoYn){
	if(autoYn == "Y"){
		$("#auto_rnew_detail").css('display','');		
		$("#approval_info").css('display','none');
		frm.approvalman_id.value = "";
		frm.approvalman_nm.value = "";
		frm.approvalman_search_nm.value = "";
		frm.approvalman_dept_nm.value = "";
		frm.approvalman_jikgup_nm.value = "";
		$('#approvalman_jikgup_nm').html("");
		$('#approvalman_dept_nm').html("");
		$("#btn_03").css('display','');
		$("#btn_05").css('display','none');
		$("#btn_06").css('display','');
		$("#btn_13").css('display','');
		$("#btn_15").css('display','none');
		$("#btn_16").css('display','');
		$("#fileAutoTxt").show();
	}else{
		$("#auto_rnew_detail").css('display','none');
		$("#approval_info").css('display','');
		$("#btn_03").css('display','');
		$("#btn_05").css('display','');
		$("#btn_06").css('display','none');
		$("#btn_13").css('display','');
		$("#btn_15").css('display','');
		$("#btn_16").css('display','none');
		$("#fileAutoTxt").css('display','none');
	}
}

/*
 * 인쇄 팝업 띄우기
 */
function openPrint2() {
	var frm = document.frm;
	
	PopUpWindowOpen('', '1000','768',true, "popupMyApproval");
	
	frm.action	= "<c:url value='/clm/manage/myApproval.do' />";
	frm.method.value = "forwardPrintPop";
	frm.target = "popupMyApproval";
	frm.submit();
}

/**
* 메시지 처리
*/
function showMessage(msg) {
	if(msg != "" && msg != null && msg.length > 1) {
		alert(msg);
	}
}

/************************************************************************
 * 의뢰인관리 팝업
 */
 function openChooseClient(){
    var frm = document.frm;
    var items_str1 = $("input[name=arr_demnd_seqno]").map(function(){return this.value;}).get();
    var items_str2 = $("input[name=arr_trgtman_id]").map(function(){return this.value;}).get();
    var items_str3 = $("input[name=arr_trgtman_nm]").map(function(){return this.value;}).get();
    var items_str4 = $("input[name=arr_trgtman_jikgup_nm]").map(function(){return this.value;}).get();
    var items_str5 = $("input[name=arr_trgtman_dept_nm]").map(function(){return this.value;}).get();
    var items = items_str1+"!@#$"+ items_str2  +"!@#$"+  items_str3  +"!@#$"+  items_str4 +"!@#$"+items_str5;
    
    frm.chose_client.value = items;
            
    PopUpWindowOpen('', "530", "480",true,'PopUpWindow');
    frm.action = "<c:url value='/clm/manage/chooseClient.do' />";
    frm.method.value="forwardChooseClientPopup";
    frm.target = "PopUpWindow";
    frm.submit();
 }
 
</script>
</head>
<body onLoad='showMessage("<c:out value='${returnMessage}'/>");'>

<div id="wrap">
	<!-- container -->
	<div id="container">
		<!-- location -->
		<div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //location -->
		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.msg.common.contractTerm" /></h3>
		</div>
		<!-- //title -->
		<!-- content -->
		<div id="content">
			<div id="content_in">
			<!-- **************************** Form Setting **************************** -->
			<form:form name="frm" id='frm' method="post" autocomplete="off">
				<!-- required Form -->
				<input type="hidden" name="method"   value="" />
				<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
				<input type="hidden" name="curPage" id="curPage" value="<c:out value='${command.curPage}'/>"/>
				<!-- key hidden Form -->
				<input type="hidden" name="cnsdreq_id"	id="cnsdreq_id"   value="<c:out value='${command.cnsdreq_id}'/>" />
				<input type="hidden" name="cntrt_id"		id="cntrt_id"     value="<c:out value='${command.cntrt_id}'/>" />
				<input type="hidden" name="reqman_id"		id="reqman_id"     value="<c:out value='${command.reqman_id}'/>" />
				<input type="hidden" name="conListGu" value="99" />
				<!-- Attach confg -->
				<input type="hidden" name="fileInfos"   value="" /> <!-- 저장될 파일 정보 -->
			    <input type="hidden" name="fileInfos2"  value="" /> <!-- 두번째첨부파일정보 -->
			    <input type="hidden" name="fileInfos3"  value="" /> <!-- 두번째첨부파일정보 -->
			    <input type="hidden" name="fileInfos4"  value="" /> <!-- 체결본 사본 -->
			    <input type="hidden" name="fileInfos5"  value="" /> <!-- 단가내역 -->
			    <input type="hidden" name="fileInfos10"  value="" /> <!-- 종료관리 첨부파일정보 -->
			    <input type="hidden" name="fileInfoName"    value="" /> <!-- 첨부파일 정보가 리턴될 객체명 -->
			    <input type="hidden" name="fileFrameName"    value="" /> <!-- 첨부파일 화면 iFrame 명 -->
				<input type="hidden" name="file_bigclsfcn"  value="" /> <!-- 대분류 -->
				<input type="hidden" name="file_midclsfcn"  value="" /> <!-- 중분류 -->
				<input type="hidden" name="file_smlclsfcn"  value="" /> <!-- 소분류 -->
				<input type="hidden" name="multiYn"  value="Y" /> <!-- 멀티여부 default:Y-->
				<input type="hidden" name="ref_key"     value="" /> <!-- 키값 -->
				<input type="hidden" name="view_gbn"    value="download" /> <!-- 첨부파일 화면 -->
				<!-- //Attach confg -->
				<!-- 종료정보의 임직원검색관련 -->
				<input type="hidden" name="srch_user_cntnt_type" value=""/>
				<input type="hidden" name="srch_user_cntnt" value=""/>
				<!-- //종료정보의 임직원검색관련 -->
				<!-- 계약상세관련 -->
				<input type="hidden" name="cntrt_respman_id"	id="cntrt_respman_id"   value="<c:out value='${contractMstLom.cntrt_respman_id}'/>" />
				<input type="hidden" name="authreq_client" value="<c:out value='${reqAuthSvcInfo}'/>" />
				<input type="hidden" id="hidden_reqAuthInfo" name="hidden_reqAuthInfo" value="<c:out value='${reqAuthInfo}'/>" />
				<!-- //계약상세관련 -->
				<!-- 이행관련 -->
				<input type="hidden" name="exec_seqno"	id="exec_seqno"    value="<c:out value='${command.exec_seqno}'/>" />
				<input type="hidden" name="exec_status"	id="exec_status"    value="" />
				<input type="hidden" id="exe_count" name="exe_count"/>
				<input type="hidden" name="exec_mod_flag"	id="exec_mod_flag"    value="" />
				<input type="hidden" name="note"	id="note"    value="" />
				<input type="hidden" name="exec_cmpltday"	id="exec_cmpltday"    value="" />
				<!-- //이행관련 -->
				<!-- forward page -->
				<input type="hidden" name="targetMenuId" />
				<input type="hidden" name="selected_menu_id" />
				<input type="hidden" name="selected_menu_detail_id" />
				<input type="hidden" name="set_init_url" />
				<input type="hidden" name="selected_paygbn"	id="selected_paygbn"    value="" />
				<!--// forward page -->
				<!-- //html Contents -->
				<input type="hidden" name="contents"		id="contents"     value="" />
				<!-- 리스트 검색 조건시작  -->
				<input type="hidden" name="list_mode" value="<c:out value='${command.list_mode}'/>"/>
				<input type="hidden" name="srch_resp_dept" value="<c:out value='${command.srch_resp_dept}'/>"/>				<!-- 담당부서코드 -->
				<input type="hidden" name="srch_oppnt_cd" value="<c:out value='${command.srch_oppnt_cd}' />"/>   				<!-- 계약상대방코드 -->
				<input type="hidden" name="srch_review_title" value="<c:out value='${command.srch_review_title}'/>" />		<!-- 의뢰명 -->
				<input type="hidden" name="srch_reqman_nm" value="<c:out value='${command.srch_reqman_nm}'/>" />				<!-- 의뢰자 -->
				<input type="hidden" name="srch_start_reqday" value="<c:out value='${command.srch_start_reqday}'/>" />		<!-- 의뢰 시작일 -->
				<input type="hidden" name="srch_end_reqday" value="<c:out value='${command.srch_end_reqday}'/>" />			<!-- 의뢰 종료일 -->
				<input type="hidden" name="srch_start_cnlsnday" value="<c:out value='${command.srch_start_cnlsnday}'/>" />	<!-- 계약 시작일 -->
				<input type="hidden" name="srch_end_cnlsnday" value="<c:out value='${command.srch_end_cnlsnday}'/>" />		<!-- 계약 종료일 -->
				<input type="hidden" name="srch_resp_dept_nm" value="<c:out value='${command.srch_resp_dept_nm}'/>" />		<!-- 담당부서명 -->
				<input type="hidden" name="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}'/>" />			<!-- 담당자명 -->
				<input type="hidden" name="srch_biz_clsfcn" value="<c:out value='${command.srch_biz_clsfcn}'/>" />			<!-- 계약단계 -->
				<input type="hidden" name="srch_cnsdman_nm" value="<c:out value='${command.srch_cnsdman_nm}'/>" />			<!-- 검토자 -->
				<input type="hidden" name="srch_oppnt_nm" value="<c:out value='${command.srch_oppnt_nm}'/>" />				<!-- 계약상대방 -->
				<input type="hidden" name="srch_state" value="<c:out value='${command.srch_state}'/>" />						<!-- 상태 -->
				<input type="hidden" name="srch_cnclsnpurps_bigclsfcn" value="<c:out value='${command.srch_cnclsnpurps_bigclsfcn}'/>" />	<!-- 체결목적-->
				<input type="hidden" name="ismycontract" id="ismycontract" value="<c:out value='${command.ismycontract}'/>"/>
				<input type="hidden" name="srch_step" value="<c:out value='${command.srch_step}' />" />						<!-- 상태 -->
				<input type="hidden" name="mis_id" />
				<!-- 13/10/29 검색 조건 유지 추가 -->
				<input type="hidden" name="srch_cntrt_nm" value="<c:out value='${command.srch_cntrt_nm}'/>" />						<!-- Contract Title -->
				<input type="hidden" name="srch_cntrt_no" value="<c:out value='${command.srch_cntrt_no}'/>" />						<!-- Contract ID -->
				<input type="hidden" name="srch_tnc_no" value="<c:out value='${command.srch_tnc_no}'/>" />						<!-- TNC NO -->
				<input type="hidden" name="srch_str_org_acptday" value="<c:out value='${command.srch_str_org_acptday}'/>" />		<!-- Original Copy -->
				<input type="hidden" name="srch_end_org_acptday" value="<c:out value='${command.srch_end_org_acptday}'/>" />		<!-- Original Copy -->
				<input type="hidden" name="ssems_oppnt_cd"   id="ssems_oppnt_cd"  value="<c:out value='${ssems_oppnt_cd}'/>" />
				<!-- 관련자 데이타 설정(구주추가요청 14.01)  -->
				<input type="hidden" name="master_cntrt_id" id="master_cntrt_id"  value="<c:out value='${command.cntrt_id}'/>" />
				<input type="hidden" name="client_modify_div" id="client_modify_div" />
			    <input type="hidden" name="chose_client" id="chose_client" />
			    <!-- 2014-04-15 Kevin added. GERP 관련 페이지 형태 구분.(R/I) -->
				<input type="hidden" name="gerpPageType" id="gerpPageType" />
			
				<!-- Sungwoo added search parameter 2014-06-12 -->
				<input type="hidden" name="srch_division" value="<c:out value='${command.srch_division}'/>" />					<!-- Original Copy -->
				<input type="hidden" name="srch_vendor_type" value="<c:out value='${command.srch_vendor_type}'/>" />					<!-- Original Copy -->
				<input type="hidden" name="srch_vendor_type_detail" value="<c:out value='${command.srch_vendor_type_detail}'/>" />		<!-- Original Copy -->
				
				<!-- Sungwoo added 첨부파일 분기처리용 2014-09-30-->
				<input type="hidden" name="cntrt_info_gbn" id="cntrt_info_gbn" value="<c:out value='${contractReqLom.cntrt_info_gbn}' />" />
				
				<div class="content-step t_titBtn">
					<ol>
						<li><img src="<%=IMAGE %>/common/step01.gif"  	 alt="<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" />" /></li>
						<li><img src="<%=IMAGE %>/common/step02.gif"     alt="<spring:message code="clm.page.msg.manage.step02" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step02" htmlEscape="true" />" /></li>
						<li><img src="<%=IMAGE %>/common/step03.gif"     alt="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" /></li>
						<li><img src="<%=IMAGE %>/common/step04.gif"     alt="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" /></li>
						<li class='step_on'>
							<img src="<%=IMAGE %>/common/step05_on.gif"  alt="<spring:message code="clm.page.msg.manage.step05" />" title="<spring:message code="clm.page.msg.manage.step05" />" />
							<div class='step_link'><a href="javascript:open_window('win', '../../step05<%=langCd.equals("ko")?"":"_"+langCd %>.html', 0, 0, 696, 588, 0, 0, 0, 0, 0)" ><img src="<%=IMAGE %>/common/step_q.gif" alt="<spring:message code="clm.page.msg.manage.procStep"/>" title="<spring:message code="clm.page.msg.manage.procStep"/>" style="padding-top:5px;padding-left:10px;" /></a></div>
						</li>
					</ol>
				</div>
					
					<div style='width:100%; margin-bottom:7px; overflow:hidden'>
						<!-- 계약정보 -->
						<div class="title_basic" style='width:100%; float:left'>
							<h4><spring:message code="clm.page.msg.manage.contInfo" /></h4>
						</div>
						
						<!-- button -->
						
						<div class="btn_wrap_t02 fR" style='margin-top:-20px'>
							<c:if test="${command.conListGu != 'Z1000' }">
								<c:choose>
								
								    <c:when test="${command.session_user_id eq contractMstLom.cntrt_respman_id && (contractMstLom.depth_status eq 'C02681' || contractMstLom.depth_status eq 'C02662' )}">
								        <span class="btn_all_w" style='display:none;' id="btn_05" ><span class="reset"></span><a href="javascript:requestTermination();"><spring:message code="clm.page.msg.manage.endItm" /></a></span>
								        <span class="btn_all_w" style='display:none;' id="btn_06" ><span class="reset"></span><a href="javascript:insertCompletion();"><spring:message code="clm.page.msg.manage.autoExpReq" /></a></span>
								        <span class="btn_all_w" style='display:none;' id="btn_03" ><span class="tsave"></span><a href="javascript:insertTempCompletion();"><spring:message code="clm.page.msg.manage.tmpSave" /></a></span>
								    </c:when>
								</c:choose>
								<c:if test="${contractMstLom.prcs_depth == 'C02505' && ssems_oppnt_cd != null && ssems_oppnt_cd != ''}">
									<!-- GSEMS로 계약서 가져오기 -->
									<span class="btn_all_w"><span class="list"></span><a href="javascript:listSSemsIF();"><spring:message code="clm.page.msg.manage.ssemsCont" />(<c:out value='${ssems_oppnt_cd}'/>)</a></span>
								</c:if>
								<span class="btn_all_w ml_10"><span class="print"></span><a href="javascript:openPrint2();"><spring:message code="clm.page.msg.manage.printCover" /></a></span>
								<span class="btn_all_w" ><span class="print"></span><a href="javascript:openPrint();"><spring:message code="clm.page.msg.manage.print" /></a></span>
								<span class="btn_all_w"><span class="list"></span><a href="javascript:listManageCompletion();"><spring:message code="clm.page.msg.manage.list" /></a></span>
							</c:if>
							<c:if test="${command.conListGu eq 'Z1000' }"><span class="btn_all_w"><span class="list"></span><a href="javascript:self.close();"><spring:message code="clm.page.msg.common.close" /></a></span>
							</c:if>
						</div>
						<!-- //button -->
					</div>		
					
					<div class='title_basic3'><spring:message code="clm.page.msg.manage.bscInf" /></div>
					<table class="table-style01">
						<colgroup>
							<col width="15%" />
							<col width="11%" />
							<col width="14%" />
							<col width="16%" />
							<col width="16%" />
							<col width="12%" />
							<col width="15%" />
						</colgroup>
						<tr>
							<th><spring:message code="clm.page.msg.manage.reqName" /></th>
						    <td colspan="6"><c:out value="${contractReqLom.req_title}" /></td>
						</tr>
						<tr>
							<th><spring:message code="clm.page.msg.manage.contName" /></th>
							<td colspan='4'><c:out value="${contractReqLom.cntrt_nm}" /></td>
							<th><spring:message code="clm.page.msg.manage.contId" /></th>
							<td><c:out value="${contractMstLom.cntrt_no}" />						
							<c:if test="${not empty contractMstLom.tnc_no}">
								<span style='color:#A9B4BC;'>(<c:out value="${contractMstLom.tnc_no}" />)</span>
							</c:if>
							</td>
						</tr>
						<tr>
							<th><spring:message code="clm.page.msg.manage.reqPerson" /></th>
							<td colspan="2"><c:out value="${contractReqLom.reqman_nm}" /></td>
							<th><spring:message code="clm.page.msg.manage.chrgPerson" /></th>
							<td><c:out value="${contractReqLom.cntrt_respman_nm}" /></td>
							<th><spring:message code="clm.page.msg.manage.reviewer" /></th>
							<td><c:out value="${contractReqLom.cnsdman_nm}" /></td>
						</tr>
						<!-- 2014-07-24 Kevin added. Step / Status -->
						<tr class="lineAdd">
							<th class="borTz02"><spring:message code="clm.page.field.manageCommon.step" /></th>
							<td colspan="2"><span id="spStep"></span></td>
							<th><spring:message code="clm.page.field.manageCommon.status" /></th>
							<td colspan="3"><span id="spStatus"></span></td>
						</tr>
						<tr>
							<th><spring:message code="clm.page.msg.common.otherParty" /></th>
							<td colspan="2"><a href="javascript:customerPop2('<c:out value="${contractMstLom.cntrt_oppnt_cd}" />', '<c:out value="${contractMstLom.cntrt_oppnt_cd}" />');"><c:out value="${contractMstLom.cntrt_oppnt_nm}" escapeXml="false"/></a></td>
							<th><spring:message code="clm.page.field.customer.registerNo" /></th>
							<td colspan="3"><c:out value="${contractMstLom.cntrt_oppnt_ceo}" /></td>
						</tr>
						<tr>
							<th><spring:message code="clm.page.msg.manage.contItm" /></th>
							<td colspan="2"><c:out value="${contractMstLom.cntrt_trgt_nm}" escapeXml="false"/></td>
							<th><spring:message code="clm.page.msg.manage.contItmDtl" /></th>
							<td colspan="3"><c:out value="${contractMstLom.cntrt_trgt_det}" escapeXml="false"/></td>
						</tr>
						<tr>
							<th><spring:message code="clm.page.msg.manage.contType" /></th>
							<td colspan="6"><c:out value='${contractMstLom.biz_clsfcn_nm}'/>/<c:out value='${contractMstLom.depth_clsfcn_nm}'/>/<c:out value='${contractMstLom.cnclsnpurps_bigclsfcn_nm}'/>/<c:out value='${contractMstLom.cnclsnpurps_midclsfcn_nm}'/></td>
						</tr>
						<tr>
							<th><spring:message code="clm.page.msg.manage.top30Cus" /><img src="<%=IMAGE%>/icon/ico_search2.gif" class="cp" onClick="javascript:openTop30Customer();" alt="search" style="padding-left:5px" title="Top 30 Customer List"/></th>
							<td colspan="2"><c:out value="${contractMstLom.top_30_cus_nm}" /></td>
							<th><spring:message code="clm.page.msg.manage.relatedPrd" /></th>
							<td><c:out value="${contractMstLom.related_products_nm}" escapeXml="false"/></td>
							<th><spring:message code="clm.page.msg.manage.srcContDraft" /></th>
							<td><c:out value="${contractMstLom.cont_draft_nm}" /></td>
						</tr>
						<tr>
							<th><spring:message code="clm.page.msg.manage.contPeriod" /></th>
							<td colspan="2"><c:out value="${contractMstLom.cntrtperiod_startday}" /> ~ <c:out value="${contractMstLom.cntrtperiod_endday}" /></td>
							<th><spring:message code="clm.page.field.contract.consultation.detail.autorenew" /></th>
							<td>
							<c:if test="${contractMstLom.auto_rnew_yn=='Y'}">Yes</c:if>
							<c:if test="${contractMstLom.auto_rnew_yn=='N'}">No</c:if>
							</td>
							<th><spring:message code="clm.page.msg.manage.sendRcvDiv" /></th>
							<td><c:out value="${contractMstLom.payment_gbn_nm}"  /></td>
						</tr>
						<tr>
							<th><spring:message code="clm.page.msg.manage.contAmt" /></th>
							<td colspan='4'>
								<c:out value="${contractMstLom.cntrt_amt}" />
							 </td>
							<th><spring:message code="clm.page.msg.manage.curr" /></th>
							<td><c:out value="${contractMstLom.crrncy_unit}" /></td>
						</tr>
						<c:if test="${!empty contractMstLom.cntrt_untprc_expl}">
						<tr>
							<th><spring:message code="clm.page.msg.manage.contUnitPriceSum" /></th>
							<td colspan='6'>
								<%=StringUtil.convertEnterToBR(StringUtil.bvl((String)contractMstLom.get("cntrt_untprc_expl"),""))%>
							</td>
						</tr>
						<tr>
							<th width="100"><spring:message code="clm.page.msg.manage.contUnitPriceAttch" /></th>
							<td class="last-td" colspan="6">
								<iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationUntPrc" name="fileConsultationUntPrc" frameborder="0" style='height:39px' class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>
							</td>
						</tr>
						</c:if>
						<tr>
							<th><spring:message code="clm.page.msg.manage.bg" /></th>
							<td colspan="6"><c:out value="${contractMstLom.pshdbkgrnd_purps}" escapeXml="false" />
							</td>
						</tr>
						<tr>
							<th><spring:message code="clm.page.msg.manage.etcMain" /></th>
							<td colspan="6">
							<c:out value="${contractMstLom.etc_main_cont}" escapeXml="false"/>
							<c:if test="${!empty contractMstLom.if_sys_cd}"> [<c:out value="${contractMstLom.if_sys_cd}" />]</c:if>
							</td>
						</tr>
				        <!-- 배상책임한도 -->
				        <c:if test='${!empty contractMstLom.oblgt_lmt}'>
			            <tr>
			                <th><spring:message code="clm.page.field.contract.consultation.detail.obligationlimit"/></th>
			                <td colspan="6">
			                    <c:out value='${contractMstLom.oblgt_lmt}' escapeXml="false"/>
			                </td>
			            </tr>
			            </c:if>
			            
			            <!-- 기타 특약사항 -->
			            <c:if test='${!empty contractMstLom.spcl_cond}'>
			            <tr>
			                <th><spring:message code="clm.page.msg.manage.etcSpecial" /></th>
			                <td colspan="6"><c:out value='${contractMstLom.spcl_cond}' escapeXml="false"/></td>
			            </tr>
			            </c:if>
			            
			            <!-- 준거법 / 준거법상세 -->
			            <tr>
			                <th><spring:message code="clm.page.field.contract.detail.loac"/></th>
			                <td colspan="2"><c:out value='${contractMstLom.loac}' escapeXml="false"/></td>
			                <th><spring:message code="clm.page.field.contract.detail.loacdetail"/></th>
			                <td colspan="3"><c:out value='${contractMstLom.loac_etc}' escapeXml="false"/></td>
			            </tr>
			            <!-- 분쟁해결방법 / 분쟁해결방법 상세 -->
			            <tr>
			                <th><spring:message code="clm.page.field.contract.detail.solutionmethod"/></th>
			                <td colspan="2"><c:out value='${contractMstLom.dspt_resolt_mthd}' escapeXml="false"/></td>
			                <th><spring:message code="clm.page.field.contract.detail.solutionmethoddetail"/></th>
			                <td colspan="3"><c:out value='${contractMstLom.dspt_resolt_mthd_det}' escapeXml="false"/></td>
			            </tr>
			            <tr class="slide-target02 slide-area">
		                   <th rowspan="4"><spring:message code="clm.page.msg.common.attachment" /></th>
		                   <td class="blueD"><span class="blueD"><spring:message code="clm.page.msg.manage.contract" /></span></td>
		                   	<td  colspan="5">
		                    	<iframe src="<c:url value='/clm/blank.do' />" id="fileList" name="fileList" frameborder="0" style='height:20px' class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>
		                	</td>
		               	</tr> 
		               	
			            <tr class="slide-target02 slide-area">
		                   <td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.field.approval.makeApprovalContractInfo59" /></span></td>
		                   <td colspan="5">
			                <iframe src="<c:url value='/clm/blank.do' />" id="fileList3" name="fileList3" frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe>
		               	</td>
		               	</tr>
		          		 	<tr class="slide-target02 slide-area">
		                   <td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.common.etc" /></span></td>
		                   <td colspan="5">
			                <iframe src="<c:url value='/clm/blank.do' />" id="fileList2" name="fileList2" frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe>
		               	</td>
		               	</tr>
		           		<tr class="slide-target02 slide-area">
		                   <td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.common.outlook" /></span></td>
		                   <td colspan="5">
			                <iframe src="<c:url value='/clm/blank.do' />" id="fileListOut" name="fileListOut" frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe>
		               	</td>
		               	</tr>
					</table>	
					
					<!-- 2014-04-15 Kevin Added. GERP Information -->
		           	<iframe id="iframeGERP" name="iframeGERP" src="/clm/blank.do" style="border:0; width:100%; height:115px; margin:10px 0 10px 0;" scrolling="no"></iframe>
					
					<div class='title_basic3'><spring:message code="clm.page.msg.manage.preApprInf" /></div>
					<table class="table-style01">
						<colgroup>
							<col width="12%" />
							<col width="22%" />
							<col width="12%" />
							<col width="21%" />
							<col width="12%" />
							<col width="21%" />
						</colgroup>
						<tr>
							<th><spring:message code="clm.page.msg.manage.apprType" /></th>
							<td colspan='3'><c:out value="${contractMstLom.bfhdcstn_apbt_mthd_nm}" /></td>
							<th><spring:message code="clm.page.msg.manage.apprDate" /></th>
							<fmt:parseDate value="${contractMstLom.bfhdcstn_apbtday}" var="bfhdcstn_apbtday" pattern="yyyymmdd"/>
							<td><fmt:formatDate value="${bfhdcstn_apbtday}" pattern="yyyy-mm-dd"/></td>
						</tr>
						<tr>
							<th><spring:message code="clm.page.msg.manage.proposer" /></th>
							<td colspan='3'>
							<c:if test="${!empty contractMstLom.bfhdcstn_mtnman_nm}">
								<c:out value="${contractMstLom.bfhdcstn_mtnman_nm}" />/<c:out value="${contractMstLom.bfhdcstn_mtnman_jikgup_nm}" />/<c:out value="${contractMstLom.bfhdcstn_mtn_dept_nm}" />
							</c:if>
							</td>
							<th><spring:message code="clm.page.msg.manage.apprPer" /></th>
							<td>
							<c:if test="${!empty contractMstLom.bfhdcstn_apbtman_nm}">
								<c:out value="${contractMstLom.bfhdcstn_apbtman_nm}" />/<c:out value="${contractMstLom.bfhdcstn_apbtman_jikgup_nm}" />/<c:out value="${contractMstLom.bfhdcstn_apbt_dept_nm}" />
							</c:if>
							</td>
						</tr>
						<tr>
							<th width="100"><spring:message code='clm.page.field.contract.consult.approval.file' /></th>
							<td class="last-td" colspan="5">
								<iframe src="<c:url value='/clm/blank.do' />" id="fileList33" name="fileList33" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
							</td>
						</tr>
					</table>
						
					<div class='title_basic3'><spring:message code="clm.page.msg.manage.relContInf" /></div>
					<table class="table-style01" id="contrack-relationView" >
						<colgroup>
							<col width="12%" />
							<col width="50%" />
							<col width="10%" />
							<col/>	
						</colgroup>
						<tr>
							<th class="tC"><spring:message code="clm.page.msg.manage.relation" /></th>
							<th class="tC"><spring:message code="clm.page.msg.manage.relCont" /></th>
							<th class="tC"><spring:message code="clm.page.msg.manage.define" /></th>
							<th class="tC"><spring:message code="clm.page.msg.manage.relDetail" /></th>
						</tr>
							<c:choose>
							<c:when test ="${!empty contractRelationLom}">
								<c:forEach var="list" items="${contractRelationLom}">
									<tr>
										<td><c:out value='${list.rel_type_nm}'/></td>
										<td><c:out value='${list.relation_cntrt_nm}'/></td>
										<td><c:out value='${list.rel_defn}'/></td>
										<td><c:out value='${list.expl}'/></td>
									</tr>		
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr><td colspan="4" align="center">No Data Found</td></tr>
							</c:otherwise>
							</c:choose>	
					</table>
					<!-- // 계약정보 -->
					
					<!-- Conclusion Information -->
					<div class="title_basic" style='width:100%; margin:10px 0'>
						<h4><spring:message code="clm.page.msg.manage.conclInf" /></h4>
					</div>
					
					<div class='title_basic3'><spring:message code="clm.page.msg.manage.bscInf" /></div>
					<table class="table-style01">
						<colgroup>
							<col width="12%" />
							<col width="22%" />
							<col width="12%" />
							<col width="21%" />
							<col width="12%" />
							<col width="21%" />
						</colgroup>
						<tr>
							<th><spring:message code="clm.page.msg.manage.conclConf" /></th>
							<td>
							<c:choose>
							<c:when test="${contractMstLom.cntrt_cnclsn_yn=='Y'}">Yes</c:when>
							<c:otherwise>No</c:otherwise>
							</c:choose>	
							</td>
							<th><spring:message code="clm.page.msg.manage.conclResDate" /></th>
							<td><c:out value='${contractMstLom.cnclsn_plndday}'/></td>
							<th><spring:message code="clm.page.msg.manage.conclDate" /></th>
							<td><c:out value='${contractMstLom.cntrt_cnclsnday}'/></td>
						</tr>
						<tr>
							<th><spring:message code="clm.page.msg.manage.signDiv" /></th>
							<td><c:out value='${contractMstLom.seal_mthd_nm}'/></td>
							<c:choose>
							<c:when test="${contractMstLom.seal_mthd eq 'C02102'}">
							<th><spring:message code="clm.page.msg.manage.signChrgMan" /></th>
							<td colspan='3'>
							<c:if test="${!empty contractMstLom.sign_plndman_nm}">
								<c:out value='${contractMstLom.sign_plndman_nm}'/>/<c:out value='${contractMstLom.sign_plndman_jikgup_nm}'/>/<c:out value='${contractMstLom.sign_plnd_dept_nm}'/>
							</c:if>
							</td>
							</c:when>
							<c:otherwise>
							<th><spring:message code="clm.page.msg.manage.signManager" /></th>
							<td colspan='3'>
							<c:if test="${!empty contractMstLom.seal_ffmtman_nm}">
								<c:out value='${contractMstLom.seal_ffmtman_nm}'/>/<c:out value='${contractMstLom.seal_ffmtman_jikgup_nm}'/>/<c:out value='${contractMstLom.seal_ffmt_dept_nm}'/>
							</c:if>
							</td>
							</c:otherwise>
							</c:choose>	
						</tr>
						<tr>
							<th><spring:message code="clm.page.msg.manage.chrgCont" /></th>
							<td colspan="6">
							<c:if test="${!empty contractMstLom.cntrt_respman_nm }">
								<c:out value='${contractMstLom.cntrt_respman_nm}'/>/<c:out value='${contractMstLom.cntrt_respman_jikgup_nm}'/>/<c:out value='${contractMstLom.cntrt_resp_dept_nm}'/>
							</c:if>
							</td>
						</tr>
						
						<tr id="deferOpnnArea" class="lineAdd">
							<th class="borTz02"><spring:message code="clm.page.msg.manage.relPerson" /></th><!-- 관련자 RELATION_MAN  relation_man -->
							<td colspan="6">
							<c:if test="${command.session_user_id == contractReqLom.reqman_id or 'RA01' eq command.top_role }">
							<span class="btn_all_b" onclick="javascript:openChooseClient();"><span class="add"></span><a> <spring:message code="clm.page.msg.manage.add" htmlEscape="true" /></a></span><!-- 추가 -->
							</c:if>
							<span id="id_trgtman_nm">
							<%if(authReqList !=null && authReqList.size() >0){ %>
								<%for(int j=0;j<authReqList.size();j++){ %>
									<% ListOrderedMap lom = (ListOrderedMap)authReqList.get(j);%>
									<% if((j !=0 && j !=1) && (j % 2 == 0 )){%><br/><%}%>
									<% if(j != 0 && (j % 2 !=0 )){%>,<% }%>	
									<input type="hidden" name="arr_demnd_seqno" id="arr_demnd_seqno" value="<%=lom.get("demnd_seqno") %>" /><input type="hidden" name="arr_trgtman_id" id="arr_trgtman_id" value="<%=lom.get("trgtman_id") %>" /><input type="hidden" name="arr_trgtman_nm" id="arr_trgtman_nm" value="<%=lom.get("trgtman_nm") %>" /><input type="hidden" name="arr_trgtman_jikgup_nm" id="arr_trgtman_jikgup_nm" value="<%=lom.get("trgtman_jikgup_nm") %>" /><input type="hidden" name="arr_trgtman_dept_nm" id="arr_trgtman_dept_nm" value="<%=lom.get("trgtman_dept_nm") %>" />					
									<%=lom.get("trgtman_nm") %>/<%=lom.get("trgtman_jikgup_nm") %>/<%=lom.get("trgtman_dept_nm") %>
								<% }%>
							<% }%>
							</span>
							</td>
						</tr>
					</table>
					
					<div class='title_basic3'><spring:message code="clm.page.msg.manage.copyVerInf" /></div>
					<table class="table-style01">
						<colgroup>
							<col width="12%" />
							<col width="22%" />
							<col width="12%" />
							<col width="54%" />
						</colgroup>
						<tr>
							<th><spring:message code="clm.page.msg.manage.copyVerRegPer" /></th>
							<td class="txtover">
							<c:if test="${!empty contractMstLom.cpy_regman_nm}">
								<c:out value='${contractMstLom.cpy_regman_nm}'/>/<c:out value='${contractMstLom.cpy_regman_jikgup_nm}'/>/<c:out value='${contractMstLom.cpy_reg_dept_nm}'/>
							</c:if>
							</td>
							<th><spring:message code="clm.page.msg.manage.copyVerRegDate" /></th>
							<td><c:out value='${contractMstLom.cpy_regday}'/></td>
						</tr>
						<tr>
							<th><spring:message code="clm.page.msg.manage.conclCopy" /></th>
							<td colspan="3">
								<!-- Sungwoo replacement height size 2014-07-03-->
								<iframe src="<c:url value='/clm/blank.do' />" id="fileList4" name="fileList4" frameborder="0" class='addfile_iframe_d'  width="100%" height="100%" scrolling="auto" allowTransparency="true"></iframe>
							</td>
						</tr>
					</table>
					<div class='title_basic3'><spring:message code="clm.page.msg.manage.orgInf" /></div>
					<table class="table-style01">
						<colgroup>
							<col width="12%" />
							<col width="22%" />
							<col width="12%" />
							<col width="54%" />
						</colgroup>
						<tr>
							<th><spring:message code="clm.page.msg.manage.orgSendPer" /></th>
							<td class="txtover">
							<c:if test="${!empty contractMstLom.org_hdovman_nm }">
								<c:out value='${contractMstLom.org_hdovman_nm}'/>/<c:out value='${contractMstLom.org_hdovman_jikgup_nm}'/>/<c:out value='${contractMstLom.org_hdov_dept_nm}'/>
							</c:if>
							</td>
							<th><spring:message code="clm.page.msg.manage.orgRcvDate" /></th>
							<td><c:out value='${contractMstLom.org_acptday}'/></td>
							
						</tr>
						<tr>
							<th><spring:message code="las.page.field.contractManager.groupChief" /></th>
							<td class="txtover">
							<c:if test="${!empty contractMstLom.org_tkovman_nm }">
								<c:out value='${contractMstLom.org_tkovman_nm}'/>/<c:out value='${contractMstLom.org_tkovman_jikgup_nm}'/>/<c:out value='${contractMstLom.org_tkov_dept_nm}'/>
							</c:if>
							</td>
							<th><spring:message code="clm.page.msg.manage.orgPlace" /></th>
							<td><c:out value='${contractMstLom.org_strg_pos}'/></td>
						</tr>
						<tr>
							<th><spring:message code="clm.page.msg.common.memo" /></th>
							<td colspan='3'>
								<%=StringUtil.convertEnterToBR(StringUtil.bvl((String)contractMstLom.get("cntrt_untprc_expl"),""))%>
							</td>
						</tr>
					</table>
					<!-- //Conclusion Information -->
									
					<!-- Termination Information -->
					<div class="title_basic" style='width:100%; margin:10px 0 0 0'> 
						<h4><spring:message code="clm.page.msg.manage.endInfo" /></h4>
					</div>
					<!-- //Termination Information -->
					
					<!-- 담당자일 경우 -->
					<c:choose>
						<c:when test="${command.session_user_id eq contractMstLom.cntrt_respman_id && ( completionLom.depth_status eq 'C02662' || completionLom.depth_status eq 'C02681' ) }">
							<script  language="javascript">
							//달력
							initCal("cntrtperiod_endday");
							</script>				
							<table class="table-style01">
								<colgroup>
									<col width="12%" />
									<col width="22%" />
									<col width="12%" />
									<col width="21%" />
									<col width="12%" />
									<col width="21%" />
								</colgroup>
								<tr>
									<th><spring:message code="clm.page.msg.manage.chrgCont" /></th>
									<td><c:out value='${command.session_user_nm}' />
									<input type="hidden" id="cntrt_chge_demndman_id" name="cntrt_chge_demndman_id" value="<c:out value='${command.session_user_id}' />"/>
									<input type="hidden" id="cntrt_chge_demndman_nm" name="cntrt_chge_demndman_nm" value="<c:out value='${command.session_user_nm}' />"/></td>
									<th><spring:message code="clm.page.msg.manage.level" /></th>
									<td><c:out value='${command.session_jikgup_nm}' />
									<input type="hidden" id="cntrt_chge_demndman_jikgup_nm" name="cntrt_chge_demndman_jikgup_nm" value="<c:out value='${command.session_jikgup_nm}' />"/></td>
									<th><spring:message code="clm.page.msg.manage.deptName" /></th>
									<td><c:out value='${command.session_dept_nm}' />
									<input type="hidden" id="cntrt_chge_demnd_dept_nm" name="cntrt_chge_demnd_dept_nm" value="<c:out value='${command.session_dept_nm}' />"/></td>
								</tr>
								<tr id="approval_info">
									<th><spring:message code="clm.page.msg.manage.apprPer" /><span class="astro">*</span></th>
									<td>
										<input type="hidden" name="approvalman_id" id="approvalman_id" value="<c:out value='${approveManLom.user_id }' />" />
										<input type="hidden" name="approvalman_nm" id="approvalman_nm" value="<c:out value='${approveManLom.user_name }' />" />
										<input type="text"   name="approvalman_search_nm" id="approvalman_search_nm" value="" style="width:92px" class="text_search" onKeyPress="if(event.keyCode==13) {searchEmployee(document.frm.approvalman_search_nm);return false;}" /><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" onclick="javascript:searchEmployee(document.frm.approvalman_search_nm);" alt="<spring:message code='clm.page.msg.manage.apprPer' htmlEscape='true' />" />
									</td>
									<th><spring:message code="clm.page.msg.manage.level" /></th>
									<td>
										<input type="hidden" name="approvalman_jikgup_nm" value="<c:out value='${approveManLom.duty }' />" />
										<span id="approvalman_jikgup_nm"></span>
									</td>
									<th><spring:message code="clm.page.msg.manage.deptName" /></th>
									<td>
										<input type="hidden" name="approvalman_dept_nm" value="<c:out value='${approveManLom.group_name }' />" />
										<span id="approvalman_dept_nm"></span>
									</td>
								</tr>
								<c:if test="${!empty completionLom.cntrt_chge_demndday}">
								<input type="hidden" id="cntrt_chge_demndday" name="cntrt_chge_demndday" value="<c:out value='${completionLom.cntrt_chge_demndday}' />"/>
								<tr>
									<th><spring:message code="clm.page.msg.manage.reqDt" /></th>
									<td colspan="5"><c:out value='${completionLom.cntrt_chge_demndday}' /></td>
								</tr>
								</c:if>
								<tr>
									<th><spring:message code="clm.page.msg.manage.chgStat" /></th>
									<td colspan='5'>
									<input title="<spring:message code="clm.page.msg.manage.announce044" htmlEscape="true" />" onclick="viewAutoDetail('N');" type="radio" name="cntrt_status" id="cntrt_status" size="50" value="C02404" <c:if test="${completionLom.cntrt_status eq 'C02404'}" >checked</c:if> /><strong style="padding: 0px 30px 0px 5px" title="<spring:message code="clm.page.msg.manage.announce044" htmlEscape="true" />" ><spring:message code="clm.page.msg.manage.contTermExpi" />  </strong>
									<input title="<spring:message code="clm.page.msg.manage.announce040" htmlEscape="true" />" onclick="viewAutoDetail('N');" type="radio" name="cntrt_status" id="cntrt_status" size="50" value="C02405" <c:if test="${completionLom.cntrt_status eq 'C02405'}">checked</c:if>/><strong style="padding: 0px 30px 0px 5px" title="<spring:message code="clm.page.msg.manage.announce040" htmlEscape="true" />" ><spring:message code="clm.page.msg.manage.contTermRevo" />  </strong>
									<c:if test="${completionLom.auto_rnew_yn eq 'Y' }"><%//자동연장여부 'y'인 경우에만 보임 %>
									<input title=""  onclick="viewAutoDetail('Y');" type="radio" name="cntrt_status" id="cntrt_status" size="50" value="C02402" /><strong style="padding: 0px 30px 0px 5px" title="" ><spring:message code="clm.page.msg.manage.contAutoExp" />  </strong>
									</c:if> 
									</td>
								</tr>
								<tr id="auto_rnew_detail" style="display:none;">
									<th ><spring:message code="clm.page.msg.manage.endDateOrg" /></th>
									<td><c:out value='${completionLom.cntrtperiod_endday }' /></td>
									<th ><spring:message code="clm.page.msg.manage.endDateChg" /></th>
									<td><input type="text" name="cntrtperiod_endday" id="cntrtperiod_endday" class="text_calendar02"/></td>
								</tr>
								<input type="hidden" id="cntrt_chge_plndday" name="cntrt_chge_plndday" value="" />	
								<tr>
									<th><spring:message code="clm.page.msg.manage.chgStatRsn" /></th>
									<td colspan='5'>
									<span id="curByteExpl_body_mine">0</span>/<spring:message code="clm.page.msg.manage.stringLen1000" /><br>
									<textarea name="cntrt_chge_demnd_cause" id="cntrt_chge_demnd_cause" cols="10" rows="3" onkeyup="frmChkLenLang(this,1000,'curByteExpl_body_mine','<%=langCd%>')" class="text_area_full" maxlength="330"><c:out value='${completionLom.cntrt_chge_demnd_cause}' /></textarea>
									</td>
								</tr>
								<tr>
									<th><spring:message code="clm.page.msg.manage.fileAttach" /> <img src="/images/las/ko/common/step_q.gif" alt="info" title="DOC, DOCX, GUL, PDF, OCX, XLS, XLSX, PPT, PPTX,&#13;JPG, HWP, TIF, RTF, ZIP, PTX, TML, TXT, LSX, EML, BMP, GIF, MSG, ALZ, DOT &#13;&#13;<spring:message code='las.msg.com.fileFilter'/>" /></th>
									<td colspan='5'>
										<span id="fileAutoTxt" style="display:none;">※ <spring:message code="clm.page.msg.manage.announce140" /></span>
										<div class='ico_maxsize fR'>Max Size : <span>10MB</span></div>
										<iframe src="<c:url value='/clm/blank.do' />" id="fileList10" name="fileList10" frameborder="0" class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>
							    	</td>
								</tr>				
							</table>
						</c:when>
					
						<c:when test="${user_role eq 'RD01' && completionLom.depth_status eq 'C02685' && command.isMyApproval!='Y'}">
							<!-- //확인자일 경우-->
							<table class="table-style01">
								<colgroup>
									<col width="12%" />
									<col width="22%" />
									<col width="12%" />
									<col width="54%" />
								</colgroup>
								<tr>
									<th ><spring:message code="clm.page.msg.manage.reqPrsn" /></th>
									<td class="txtover">
									<c:out value='${completionLom.cntrt_chge_demndman_nm}' />
										<c:choose><c:when test="${!empty completionLom.cntrt_chge_demndman_jikgup_nm}">/</c:when></c:choose>
									<c:out value='${completionLom.cntrt_chge_demndman_jikgup_nm}' />
										<c:choose><c:when test="${!empty completionLom.cntrt_chge_demnd_dept_nm}">/</c:when></c:choose>
									<c:out value='${completionLom.cntrt_chge_demnd_dept_nm}' /></td>
									<th ><spring:message code="clm.page.msg.manage.apprPer" /></th>
									<td>
										<c:out value='${approveManLom.user_name }' />
											<c:choose><c:when test="${!empty approveManLom.dept_name}">/</c:when></c:choose>
										<c:out value='${approveManLom.dept_name }' />
											<c:choose><c:when test="${!empty approveManLom.duty}">/</c:when></c:choose>
										<c:out value='${approveManLom.duty }' />
									<input type="hidden" name="approvalman_nm" id="approvalman_nm" value="<c:out value='${approveManLom.user_name }' />" />
									<input type="hidden" name="approvalman_id" id="approvalman_id" value="<c:out value='${approveManLom.user_id }' />" />
									<input type="hidden" name="approvalman_jikgup_nm" value="<c:out value='${approveManLom.duty }' />" />
									<input type="hidden" name="approvalman_dept_nm" value="<c:out value='${approveManLom.dept_name }' />" />
									</td>
									
								</tr>
								<c:if test="${!empty completionLom.cntrt_chge_demndday}">
								<tr>
									<th><spring:message code="clm.page.msg.manage.reqDt" /></th>
									<td colspan="3"><c:out value='${completionLom.cntrt_chge_demndday}' /></td>
								</tr>
								</c:if>		
								<tr>
									<th ><spring:message code="clm.page.msg.manage.chgStat" /></th>
									<td colspan="3"><c:out value='${completionLom.cntrt_status_nm}' />
									<input type="hidden" name="cntrt_status" id="cntrt_status" value="<c:out value='${completionLom.cntrt_status}' />" /></td>
								</tr>
								<tr>
									<th><spring:message code="clm.page.msg.manage.chgStatRsn" /></th>
									<td colspan="3"><c:out value='${completionLom.cntrt_chge_demnd_cause}' /></td>
								</tr>
								<tr>
									<th><spring:message code="clm.page.msg.common.attachment" /></th>
									<td colspan="3">
										<iframe src="<c:url value='/clm/blank.do' />" id="fileList10" name="fileList10" frameborder="0" width="100%" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
									</td>
								</tr>
							</table>
						</c:when>	
						
						<c:otherwise>
							<!-- //그외 경우 -->
							 
							<table class="table-style01">
								<colgroup>
									<col width="12%" />
									<col width="22%" />
									<col width="12%" />
									<col width="54%" />
								</colgroup>
								<tr>
									<th ><spring:message code="clm.page.msg.manage.reqPrsn" /></th>
									<td  class="txtover">
										<c:out value='${completionLom.cntrt_chge_demndman_nm}' />
											<c:choose><c:when test="${!empty completionLom.cntrt_chge_demndman_jikgup_nm}">/</c:when></c:choose>
										<c:out value='${completionLom.cntrt_chge_demndman_jikgup_nm}' />
											<c:choose><c:when test="${!empty completionLom.cntrt_chge_demnd_dept_nm}">/</c:when></c:choose>
										<c:out value='${completionLom.cntrt_chge_demnd_dept_nm}' /></td>
									<th ><spring:message code="clm.page.msg.manage.apprPer" /></th>
									<td>
										<c:choose>
											<c:when test="${!empty approveManLom.user_name}">
												<c:out value='${approveManLom.user_name }' />
													<c:choose><c:when test="${!empty approveManLom.dept_name}">/</c:when></c:choose>
												<c:out value='${approveManLom.dept_name }' />
													<c:choose><c:when test="${!empty approveManLom.duty}">/</c:when></c:choose>
												<c:out value='${approveManLom.duty }' />
											</c:when>
											<c:otherwise>
												<c:out value='${command.approvalman_nm }' />
													<c:choose><c:when test="${!empty command.approvalman_dept_nm}">/</c:when></c:choose>
												<c:out value='${command.approvalman_dept_nm }' />
													<c:choose><c:when test="${!empty command.approvalman_jikgup_nm}">/</c:when></c:choose>
												<c:out value='${command.approvalman_jikgup_nm }' />
											</c:otherwise>
										</c:choose>
									<input type="hidden" name="approvalman_id" id="approvalman_id" value="<c:out value='${command.approvalman_id }' />" />
									<input type="hidden" name="approvalman_nm" id="approvalman_nm" value="<c:out value='${command.approvalman_nm }' />" />
									<input type="hidden" name="approvalman_jikgup_nm" value="<c:out value='${command.approvalman_jikgup_nm }' />" />
									<input type="hidden" name="approvalman_dept_nm" value="<c:out value='${command.approvalman_dept_nm }' />" />
									</td>
								</tr>
								<c:if test="${!empty completionLom.cntrt_chge_demndday}">
								<tr>
									<th><spring:message code="clm.page.msg.manage.reqDt" /></th>
									<td colspan="3"><c:out value='${completionLom.cntrt_chge_demndday}' /></td>
								</tr>
								</c:if>		
								<tr>
									<th ><spring:message code="clm.page.msg.manage.chgStat" /></th>
									<td colspan="3"><c:out value='${completionLom.cntrt_status_nm}' /></td>
								</tr>
								<tr>
									<th><spring:message code="clm.page.msg.manage.chgStatRsn" /></th>
									<td colspan="3"><c:out value='${completionLom.cntrt_chge_demnd_cause}' /></td>
								</tr>
								<tr>
									<th><spring:message code="clm.page.msg.common.attachment" /></th>
									<td colspan="3">
										<iframe src="<c:url value='/clm/blank.do' />" id="fileList10" name="fileList10" frameborder="0" width="100%" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
									</td>
								</tr>
							<c:if test="${! empty completionLom.cntrt_chge_confman_nm}">
								<tr>
									<th><spring:message code="clm.page.msg.manage.confirmPer" /></th>  
									<td><c:out value='${completionLom.cntrt_chge_confman_nm}'/>/<c:out value='${completionLom.cntrt_chge_confman_jikgup_nm}'/>/<c:out value='${completionLom.cntrt_chge_conf_dept_nm}'/></td>
									<th><spring:message code="clm.page.msg.manage.confirmYn" /></th>
									<td colspan="3">
										<c:choose>
											<c:when test="${completionLom.cntrt_chge_conf_yn=='Y'}"><spring:message code="clm.page.msg.common.confirm" /></c:when>
											<c:otherwise><spring:message code="clm.page.msg.manage.noConfirm" /></c:otherwise>					
										</c:choose>
									</td>
								</tr>
								<tr>
									<th><spring:message code="clm.page.msg.manage.confirmOpin" /></th>
									<td colspan="5"><c:out value='${completionLom.cntrt_chge_conf_cont}'/></td>
								</tr>
							</c:if>
							</table>
						</c:otherwise>
					</c:choose>
					<!-- // Termiantion Information -->
			
					<!-- 이력정보 -->
					<div id="contractHis-list"></div>
					<!-- //이력정보 -->	
							
					<!-- 자동연장이력 -->
					<c:if test="${completionLom.auto_rnew_yn eq 'Y'}"><%//자동연장여부 'y'인 경우에만 보임 %>		
					<div class="title_basic" style='width:100%; margin:10px 0 0 0;'>
						<h4><spring:message code="clm.page.msg.manage.autoExpHstr" /> </h4>
					</div>
					
					<table class="table-style01" id="contrack-autoHistoryView" style="display:">
						<colgroup>
							<col width="12%" />
							<col width="15%" />
							<col width="15%" />
							<col width="31%" />
							<col width="17%" />
						</colgroup>
						<tr>
							<th class="tC"><spring:message code="clm.page.msg.manage.seqNo" /></th>
							<th class="tC"><spring:message code="clm.page.msg.manage.expReqDate" /></th>
							<th class="tC"><spring:message code="clm.page.msg.manage.termDate" /></th>
							<th class="tC"><spring:message code="clm.page.msg.common.note" /></th>
							<th class="tC"><spring:message code="clm.page.msg.manage.apprPer" /></th>
						</tr>
							<c:choose>
							<c:when test ="${!empty orgMngHistory}">
								<c:forEach var="list" items="${orgMngHistory}" >
									<tr>
										<td><c:out value='${list.rn}'/><spring:message code="clm.page.msg.manage.seq" /></td>
										<td class="tC"><c:out value='${list.cntrtperiod_startday}'/></td>
										<td class="tC"><c:out value='${list.cntrtperiod_endday}'/></td>
										<td><c:out value='${list.mod_cause}'/></td>
										<td class="tL txtover">
										<c:if test="${list.gubun eq 'A' or list.gubun eq 'J'}">
										<c:out value='${list.mod_nm}'/>/<c:out value='${list.mod_dept_nm}'/>/<c:out value='${list.mod_jikgup_nm}'/>
										</c:if>
										</td>
									</tr>		
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr><td colspan="5" align="center"><spring:message code="clm.page.msg.manage.announce141" /> </td></tr>
							</c:otherwise>
							</c:choose>	
					</table>
					</c:if>
					<!-- 자동연장이력 -->
		
					<!-- button -->
					<div class="t_titBtn">
						<div class="btn_wrap_c">
						<c:if test="${command.conListGu != 'Z1000' }">
							<c:choose>
							    <c:when test="${command.session_user_id eq contractMstLom.cntrt_respman_id && (contractMstLom.depth_status eq 'C02681' || contractMstLom.depth_status eq 'C02662' )}">
							        <span class="btn_all_w" style='display:none;' id="btn_15" ><span class="reset"></span><a href="javascript:requestTermination();"><spring:message code="clm.page.msg.manage.endItm" /></a></span>
							        <span class="btn_all_w" style='display:none;' id="btn_16" ><span class="reset"></span><a href="javascript:insertCompletion();"><spring:message code="clm.page.msg.manage.autoExpReq" /></a></span>
							        <span class="btn_all_w" style='display:none;' id="btn_17" ><span class="reset"></span><a href="javascript:insertCfrmCompletion();"><spring:message code="clm.page.msg.manage.chgConf" /></a></span>
							        <span class="btn_all_w" style='display:none;' id="btn_13" ><span class="tsave"></span><a href="javascript:insertTempCompletion();"><spring:message code="clm.page.msg.manage.tmpSave" /></a></span>
							    </c:when>
							</c:choose>
							<span class="btn_all_w ml_10" ><span class="print"></span><a href="javascript:openPrint2();"><spring:message code="clm.page.msg.manage.printCover" /></a></span>
							<span class="btn_all_w" ><span class="print"></span><a href="javascript:openPrint();"><spring:message code="clm.page.msg.manage.print" /></a></span>
							<span class="btn_all_w"><span class="list"></span><a href="javascript:listManageCompletion();"><spring:message code="clm.page.msg.manage.list" /></a></span>
						</c:if>
						<c:if test="${command.conListGu eq 'Z1000' }"><span class="btn_all_w"><span class="list"></span><a href="javascript:self.close();"><spring:message code="clm.page.msg.common.close" /></a></span></c:if>
						</div>
					</div>
					<!-- //button -->
			</form:form>
			</div>
		</div>
		<!-- //content -->
	</div>
</div>

<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- // footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</body>
</html>

