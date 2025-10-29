<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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

<%@ include file= "/WEB-INF/jsp/common/common.jsp" %>
<%-- 
/**
 * 파  일  명 : Execution_l.jsp
 * 프로그램명 : 종료정보 조회 목록
 * 설      명 : 
 * 작  성  자 : 신승완
 * 작  성  일 : 2011.09
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	
%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet"></link>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="/script/clms/common.js" type="text/javascript"></script>
<script language="javascript" src="/script/clms/clms_common.js" type="text/javascript"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>  

<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>

<script language="javascript"  >

var jsCmpltStatus = "N";

$(document).ready(function(){
	
	//completionlist();
	detailContract("<c:out value='${contractCommand.cnsdreq_id}'/>","<c:out value='${contractCommand.cntrt_id}'/>");
	
	//계약 tab
	$('#contractTab li').bind('click', function(){
		
		$('#contractTab li').removeClass('on');
		$(this).addClass('on');
		//이행관리 탭 활성화
		$('#executionTab li').removeClass('on');
		$('#executionTab li:eq(3)').addClass('on');
		//계약상세내역, 사전검토정보,체결품의정보,체결본등록정보,종료관리 diplay 설정
		$('#contract-detail-content').attr("style","display:none");
		//$('#contract-cnsdInfo-content').attr("style","display:none");
		$('#contract-cnclsnInfo-content').attr("style","display:none");
		$('#contract-cnclsnIns-content').attr("style","display:none");
		$('#contract-relation-content').attr("style","display:");
		//$('#contract-completion-content').attr("style","display:");
	});
	//이행 tab
	$('#executionTab li').bind('click', function(){
		
		$('#executionTab li').removeClass('on');
		$(this).addClass('on');		
	});
	//계약상세내역 display를 위한 이벤트 처리...
	$('#executionTab li:eq(0)').bind('click', function(){
		$('#contract-detail-content').attr("style","display:");
		//$('#contract-cnsdInfo-content').attr("style","display:none");
		$('#contract-cnclsnInfo-content').attr("style","display:none");
		$('#contract-cnclsnIns-content').attr("style","display:none");
		$('#contract-relation-content').attr("style","display:none");
		//$('#contract-completion-content').attr("style","display:none");
	});
	//사전검토정보 display를 위한 이벤트 처리...
	/* $('#executionTab li:eq(1)').bind('click', function(){
		$('#contract-detail-content').attr("style","display:none");
		$('#contract-cnsdInfo-content').attr("style","display:");
		$('#contract-cnclsnInfo-content').attr("style","display:none");
		$('#contract-cnclsnIns-content').attr("style","display:none");
		$('#contract-relation-content').attr("style","display:none");
		$('#contract-completion-content').attr("style","display:none");
	}); */
	//체결품의정보 display를 위한 이벤트 처리...cnclsnInfo
	/* $('#executionTab li:eq(1)').bind('click', function(){
		$('#contract-detail-content').attr("style","display:none");
		//$('#contract-cnsdInfo-content').attr("style","display:none");
		$('#contract-cnclsnInfo-content').attr("style","display:");
		$('#contract-cnclsnIns-content').attr("style","display:none");
		$('#contract-relation-content').attr("style","display:none");
		//$('#contract-completion-content').attr("style","display:none");
	});
	//체결본등록정보 display를 위한 이벤트 처리...cnclsnInfo
	$('#executionTab li:eq(2)').bind('click', function(){
		$('#contract-detail-content').attr("style","display:none");
		//$('#contract-cnsdInfo-content').attr("style","display:none");
		$('#contract-cnclsnInfo-content').attr("style","display:none");
		$('#contract-cnclsnIns-content').attr("style","display:");
		$('#contract-relation-content').attr("style","display:none");
		//$('#contract-completion-content').attr("style","display:none");
	});*/
	//연관계약정보 display를 위한 이벤트 처리...
	$('#executionTab li:eq(1)').bind('click', function(){
		$('#contract-detail-content').attr("style","display:none");
		//$('#contract-cnsdInfo-content').attr("style","display:none");
		$('#contract-cnclsnInfo-content').attr("style","display:none");
		$('#contract-cnclsnIns-content').attr("style","display:none");
		$('#contract-relation-content').attr("style","display:");
		//$('#contract-completion-content').attr("style","display:none");
	});
	//종료관리 display를 위한 이벤트 처리...cnclsnInfo
	/* $('#executionTab li:eq(4)').bind('click', function(){
		$('#contract-detail-content').attr("style","display:none");
		//$('#contract-cnsdInfo-content').attr("style","display:none");
		$('#contract-cnclsnInfo-content').attr("style","display:none");
		$('#contract-cnclsnIns-content').attr("style","display:none");
		$('#contract-relation-content').attr("style","display:none");
		$('#contract-completion-content').attr("style","display:");
	}); */
	
	//첨부파일
	//attachPage();

});

/**
 * 관련자 세팅
 */
function setReqAuthInfo() {
	$("#id_trgtman_nm").after($("#hidden_reqAuthInfo").val());
}

/**
 * 종료관리
 */
function forwardCompletion() {

	var options = {
		url: "<c:url value='/clm/manage/completion.do?method=forwardInsertCompletion' />",
		type: "post",
		async: false,
		dataType: "html",
		success: function (data) {
			//$("#contractExeCmpltHis-list").html("");
			//$("#completion-mng").html("");
			//$("#completion-mng").html(data);
			$("#contract-CompletionInfo").html("");
			$("#contract-CompletionInfo").html(data);
			//계약상세내역 잘라내기 붙이기...
			$('#contract-detail').append($('#contract-detail-content'));
			//$('#contract-cnsdInfo').after($('#contract-cnsdInfo-content'));
			$('#contract-cnclsnInfo').append($('#contract-cnclsnInfo-content'));
			$('#contract-cnclsnIns').append($('#contract-cnclsnIns-content'));
			$('#contract-relation').append($('#contract-relation-content'));
			$('#contract-detail-content').attr("style","display:");
			//$('#contract-cnsdInfo-content').attr("style","display:none");
			$('#contract-cnclsnInfo-content').attr("style","display:none");
			$('#contract-relation-content').attr("style","display:none");
			$('#contract-cnclsnIns-content').attr("style","display:none");
			
			$('#executionTab li').removeClass('on');
			$('#executionTab li:eq(0)').addClass('on');
			
		}
	};
	$("#frm").ajaxSubmit(options);
}


/**
 * 종료관리임시저장
 */
function insertTempCompletion() {

var frm = document.frm;

frm.cntrt_chge_demndday.value="";	

if(frm.cntrt_status[0].checked == false && frm.cntrt_status[1].checked == false) {
	alert("<spring:message code="clm.page.msg.manage.announce160" />");
	return;
} else if(frm.cntrt_status[0].checked == true && jsCmpltStatus != 'Y') {
	alert("<spring:message code="clm.page.msg.manage.announce083" />");
	return;
}

//유효성 체크
    if(validateForm(frm)){
     	if(confirm("<spring:message code='las.msg.ask.insert'/>")){
     		viewHiddenProgress(true);
			fileList10.UploadFile(function(uploadCount){
			    //첨부파일 업로드 결과 잘못된 형식의 파일이 올라간 경우
			    
			    if(uploadCount == -1){
			        alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
			        return;
			    }

				var options = {
						url: "<c:url value='/clm/manage/completion.do?method=insertTempCompletion' />",
						type: "post",
						dataType: "html",
						beforeSubmit: function(formData, form){
							viewHiddenProgress(true);
						},		
						success: function(data) {
							viewHiddenProgress(false);
							alert('<spring:message code="secfw.msg.succ.insert"/>');						
							detailBaseContract(frm.cnsdreq_id.value,frm.cntrt_id.value);
							$('#contract-CompletionInfo').html("");
							$('#contract-CompletionInfo').html(data);
							
							listContractRole();
							attachReloadPage();
						}
					};
				$("#frm").ajaxSubmit(options);
			});	
    	}
    }
}

/**
 * 종료관리등록
 */
function insertCompletion() {
	var frm = document.frm;
	
	if($('#approvalman_jikgup_nm').html() == '') {
		alert("<spring:message code="clm.page.msg.manage.announce110" />");
		return;
	}
	
	if(frm.cntrt_status[0].checked == false && frm.cntrt_status[1].checked == false) {
		alert("<spring:message code="clm.page.msg.manage.announce160" />");
		return;
	} else if(frm.cntrt_status[0].checked == true && jsCmpltStatus != 'Y') {
		alert("<spring:message code="clm.page.msg.manage.announce083" />");
		return;
	}

	if(confirm("<spring:message code='las.msg.ask.insert'/>")){
		if(validateForm(frm)){

			fileList10.UploadFile(function(uploadCount){
			    //첨부파일 업로드 결과 잘못된 형식의 파일이 올라간 경우
			    
			    if(uploadCount == -1){
			        alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
			        return;
			    }
			    //첨부파일이 필수 인 경우 : 사용자 선택사항
			   /*  if(uploadCount == 0){
			        alert("<spring:message code='secfw.msg.error.fileNon' />");
			        return;
			     } */
			  //종료관리 정보 등록
				var options = {
					url: "<c:url value='/clm/manage/completion.do?method=insertCompletion' />",
					type: "post",
					dataType: "html",
					beforeSubmit: function(formData, form){
						viewHiddenProgress(true);
					},			
					success: function(data) {
						//alert('<spring:message code="secfw.msg.succ.insert"/>');
						//$("#completion-mng").html("");
						//$("#completion-mng").html(data);
						$('#contract-CompletionInfo').html("");
						$('#contract-CompletionInfo').html(data);
						
						listContractRole();
						//업로드 된 파일창 현재 화면에 다시 뿌리기...
						attachReloadPage();
						
						//상신
						detailApprovalPop();
					}
				};
				$("#frm").ajaxSubmit(options);	
				//종료관리 정보 등록 End
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
		 			//alert('<spring:message code="secfw.msg.succ.insert"/>');
					detailBaseContract(frm.cnsdreq_id.value,frm.cntrt_id.value);
				}else {
					viewHiddenProgress(false);
					alert(responseText.returnMessage);
				}
				executionDetailHislist();
	 		}
	   };
	   $("#frm").ajaxSubmit(options);
}
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
/* 
//상신팝업
function openApproval(){
	var frm = document.frm;
	
	frm.file_bigclsfcn.value = "F012";
	frm.file_midclsfcn.value = "F01204";
	frm.file_smlclsfcn.value = "";
	frm.ref_key.value = "<c:out value='${contractCommand.cntrt_id}'/>";
	
	if(confirm("<spring:message code='las.msg.ask.insert'/>")){
		if(validateForm(frm)){
		    //상신팝업
			PopUpWindowOpen('', "1024", "768", true, "PopUpApproval");
		    frm.action="<c:url value='/clm/manage/completion.do' />";
		    frm.method.value="makeApprovalHtml";
		    frm.target = "PopUpApproval";
		    frm.submit();  
		    //상신팝업 end
		}
	}
}

//팝업종료후처리
function setApprovalYN(yn){
	var frm = document.frm;
	//alert(yn);
	if(yn=="Y") {//성공일때
		insertCompletion();
	}
} */

//팝업오픈-모달리스
/*function PopUpWindowOpen(surl, popupwidth, popupheight, bScroll, popName) {
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
	
	var Feture = "fullscreen=no,toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=" + (bScroll ? "yes" : "no") + ",resizable=no,left=" + Left + ",top=" + Top + ",width=" + popupwidth + ",height=" + popupheight;

	PopUpWindow = window.open(surl, popName , Feture);

	PopUpWindow.focus();
	
}*/

/**
 * 변경확인
 */

function insertCfrmCompletion(){
	
    if(validateForm(frm)){
     	if(confirm("<spring:message code='las.msg.ask.insert'/>")){
			var options = {
				url:"<c:url value='/clm/manage/completion.do?method=insertCfrmCompletion' />",
				type:"post",
				dataType:"html",
				beforeSubmit: function(formData, form){
					viewHiddenProgress(true);
				},
				success: function(data){
					viewHiddenProgress(false);
					//alert('<spring:message code="secfw.msg.succ.insert"/>');
					//$("#completion-mng").html("");
					//$("#completion-mng").html(data);
					$('#contract-CompletionInfo').html("");
					$('#contract-CompletionInfo').html(data);
					
					attachReloadPage();
					listManageCompletion();
				}	
			};
			$('#frm').ajaxSubmit(options);
     	}
    }
}

/**
 * 이행및종료이력정보
 */
function executionHislist() {

	var options = {
		url: "<c:url value='/clm/manage/completion.do?method=listExecutionHis' />",
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

/**
* 상세내역 조회
*/
function detailContract(cnsdreq_id, cntrt_id){

	viewHiddenProgress(true);
	 //초기화 : 연계 시 이 부분을 주석처리...
	$('input[name=cnsdreq_id]').val("");
	$('input[name=cntrt_id]').val("");
	
    //설정
    $('#cnsdreq_id').val(cnsdreq_id);
    $('#cntrt_id').val(cntrt_id);
    
    var options = {
    		url: "<c:url value='/clm/manage/completion.do?method=detailContract' />",
    		type: "post",
    		async: false,
    		dataType: "html",
    			
    		success: function(data){
    			$("#contract-view").html("");
    			$("#contract-view").html(data);
    			
    			forwardCompletion();
    			if('<c:out value="${contractReqLom.cntrt_status}" />'==="C02402"){
    				executionHislist();
    			} else {
    				if('<c:out value="${contractReqLom.depth_status}" />'==='C02681'){
    					executionHislist();
    				}else{
    					executionDetailHislist();	
    				}
    			}
    			contractHisList();
    			listContractRole();
    			//달력
    			//initCal("cntrt_chge_plndday");
				//관련자 세팅
    			setReqAuthInfo();
    		}
    };
    $("#frm").ajaxSubmit(options);
    
    attachPage($('#cntrt_id').val());
    viewHiddenProgress(false);
}

/**
* 계약기본정보 조회
*/
function detailBaseContract(cnsdreq_id, cntrt_id){

	//var frm = document.frm;
	 //초기화 : 연계 시 이 부분을 주석처리...
	$('input[name=cnsdreq_id]').val("");
	$('input[name=cntrt_id]').val("");
	
    //설정
    $('#cnsdreq_id').val(cnsdreq_id);
    $('#cntrt_id').val(cntrt_id);
	    
    var options = {
    		url: "<c:url value='/clm/manage/completion.do?method=detailContract' />",
    		type: "post",
    		async: false,
    		dataType: "html",
    			
    		success: function(data){
    			$("#contract-view").html("");    			
    			$('#contract-detail').html("");
				$('#contract-cnclsnInfo').html("");
				$('#contract-cnclsnIns').html("");
				$('#contract-relation').html("");
				
				$("#contract-view").html(data);
				
				//계약상세내역 잘라내기 붙이기...
				$('#contract-detail').append($('#contract-detail-content'));
				$('#contract-cnclsnInfo').append($('#contract-cnclsnInfo-content'));
				$('#contract-cnclsnIns').append($('#contract-cnclsnIns-content'));
				$('#contract-relation').append($('#contract-relation-content'));
				$('#contract-detail-content').attr("style","display:");
				$('#contract-cnclsnInfo-content').attr("style","display:none");
				$('#contract-relation-content').attr("style","display:none");
				$('#contract-cnclsnIns-content').attr("style","display:none");
				
				$('#executionTab li').removeClass('on');
				$('#executionTab li:eq(0)').addClass('on');
    		}
    };
    $("#frm").ajaxSubmit(options);
    
  // attachPage();
    attachPage($('#cntrt_id').val());
}

//첨부파일
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
	frm.file_smlclsfcn.value = "F0120201";
	// frm.ref_key.value = "<c:out value='${contractCommand.cntrt_id}'/>@<c:out value='${contractCommand.cnsdreq_id}'/>";
	frm.ref_key.value = temp_cntrt_id + "@<c:out value='${contractCommand.cnsdreq_id}'/>";
	
	frm.view_gbn.value = "download";
	getClmsFilePageCheck('frm');
	//frm.submit();   
	
    //계약서- 첨부/별첨
    frm.target          = "fileList3";
	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	frm.method.value    = "forwardClmsFilePage";
	frm.fileInfoName.value = "fileInfos3";
	frm.fileFrameName.value = "fileList3";

	frm.file_bigclsfcn.value = "F012";
	frm.file_midclsfcn.value = "F01202";
	frm.file_smlclsfcn.value = "F0120208";
	// frm.ref_key.value = "<c:out value='${contractCommand.cntrt_id}'/>@<c:out value='${contractCommand.cnsdreq_id}'/>";
	frm.ref_key.value = temp_cntrt_id + "@<c:out value='${contractCommand.cnsdreq_id}'/>";
	frm.view_gbn.value = "download";
	getClmsFilePageCheck('frm');
	//frm.submit();	
	
	//계약서 기타_체결본 파일
	frm.target          = "fileList2";
	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	frm.method.value    = "forwardClmsFilePage";
	frm.fileInfoName.value = "fileInfos2";
	frm.fileFrameName.value = "fileList2";
	frm.file_bigclsfcn.value = "F012";
	frm.file_midclsfcn.value = "F01202";
	frm.file_smlclsfcn.value = "F0120212";
	//frm.ref_key.value = "<c:out value='${contractCommand.cntrt_id}'/>@<c:out value='${contractCommand.cnsdreq_id}'/>";
	frm.ref_key.value = temp_cntrt_id + "@<c:out value='${contractCommand.cnsdreq_id}'/>";
	frm.view_gbn.value = "download";
	getClmsFilePageCheck('frm');
	//frm.submit();
	
	//체결본사본
	frm.target          		= "fileList4";
    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
    frm.method.value    		= "forwardClmsFilePage";
    frm.fileInfoName.value 		= "fileInfos4";
    frm.fileFrameName.value 	= "fileList4";
    
    frm.file_bigclsfcn.value = "F012";
    frm.file_midclsfcn.value	= "F01203";
    frm.file_smlclsfcn.value 	= "";
    // frm.ref_key.value			= frm.cntrt_id.value;
    frm.ref_key.value			= temp_cntrt_id;
    
    getClmsFilePageCheck('frm');
	
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
        getClmsFilePageCheck('frm');
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
	// frm.ref_key.value = "<c:out value='${contractCommand.cntrt_id}'/>";
	//frm.ref_key.value = temp_cntrt_id;
	frm.ref_key.value   = temp_cntrt_id + "@<c:out value='${contractCommand.cnsdreq_id}'/>";
	
	//의뢰인, 담당자인 경우 첨부파일 유형변경
	if($('#cntrt_chge_demndman_id').length < 1){
		frm.view_gbn.value = "download";
		getClmsFilePageCheck('frm');
	}else{
		frm.view_gbn.value = "modify";
		frm.submit();
	}
	
	
	
}

function attachReloadPage(){
	var frm = document.frm;
	
	//종료관리 파일
	frm.target          = "fileList10";
	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	frm.method.value    = "forwardClmsFilePage";
	frm.fileInfoName.value = "fileInfos10";
	frm.fileFrameName.value = "fileList10";

	frm.file_bigclsfcn.value = "F012";
	frm.file_midclsfcn.value = "F01204";
	frm.file_smlclsfcn.value = "";
	frm.ref_key.value = "<c:out value='${contractCommand.cntrt_id}'/>" + "@<c:out value='${contractCommand.cnsdreq_id}'/>";;
	
	frm.view_gbn.value = "modify";

	//getClmsFilePageCheck('frm');
	//frm.submit();
	if($('#cntrt_chge_demndman_id').length < 1){
		frm.view_gbn.value = "download";
		getClmsFilePageCheck('frm');
	}else{
		frm.view_gbn.value = "modify";
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
		frm.submit();
	}
	else{
		frm.target = "_self";		 
		frm.action = "<c:url value='/clm/manage/completion.do' />";
		frm.method.value = "listManageCompletion";
		frm.submit();	
	}
}
function openPreview() {
	alert("<spring:message code="clm.page.msg.manage.announce138" />");
	var frm = document.frm;
	
    PopUpWindowOpen('', "1024", "768", true, "popUpPreview");
    
    frm.action 					 = "<c:url value='/clm/manage/completion.do' />";
    frm.method.value			 = "forwardPreviewPop";
    frm.target 					 = "popUpPreview";
    frm.submit();
}

function detailPreviewPop() {
	var frm = document.frm;
	
	viewHiddenProgress(true);
	
	var options = {
 		url: "<c:url value='/clm/manage/completion.do?method=detailPreviewPop' />",
 		type: "post",
 		async: false,
 		dataType: "html",
 		success: function(data){
 			frm.contents.value=data;
 			openPreview();
 			viewHiddenProgress(false);
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
/**
* 권한에따른 버튼제어
*/
function listContractRole(){
	var frm = document.frm;
	
	var options = {
		url: "<c:url value='/clm/manage/completion.do?method=listContractRole' />",
		type: "post",
		dataType: "json",
		success: function(responseText,returnMessage) {
			$('#contract-btn').html("");
			$('#contract-btn2').html("");
			if(responseText != null && responseText.length != 0) {
				$.each(responseText, function(entryIndex, entry) {
					var html = "<span class=\'btn_all_w\' ><span class=\'preview\'></span><a href=\"javascript:openHis2();\"><spring:message code='clm.page.msg.manage.print' /></a></span>";
					html +=	"&nbsp;<span class=\'btn_all_w\' id=\'btn_list\'><span class=\'list\'></span><a href=\"javascript:listManageCompletion();\"><spring:message code='clm.page.msg.manage.list' /></a></span>";
					//html +=  "&nbsp;<span class=\'btn_all_w\'><span class=\'preview\'></span><a href=\"javascript:detailPreviewPop()\">미리보기</a></span>";
					jsCmpltStatus = entry['execCmpltStatus'];
					//종료관리 시작상태
					if(entry['cntrt_status']=='C02402' && ( entry['prcs_depth']=='C02504' || entry['prcs_depth']=='C02505' ) && ( entry['depth_status']=='C02662' || entry['depth_status']=='C02681')){
						//계약담당자일경우
						if(entry['session_user_id']==frm.cntrt_respman_id.value){
							html +=  "&nbsp;<span class=\'btn_all_w\'><span class=\'tsave\'></span><a href=\"javascript:insertTempCompletion()\"><spring:message code='clm.page.msg.manage.tmpSave' /></a></span>";
							//html +=  "&nbsp;<span class=\'btn_all_w\'><span class=\'preview\'></span><a href=\"javascript:detailPreviewPop()\">미리보기</a></span>";
							//html +=  "&nbsp;<span class=\'btn_all_w\'><span class=\'reply\'></span><a href=\"javascript:insertCompletion();\">변경상신</a></span>";
							//if(entry['execCmpltStatus']=='Y'){
								html +=  "&nbsp;<span class=\'btn_all_w\'><span class=\'reply\'></span><a href=\"javascript:insertCompletion();\"><spring:message code='clm.page.msg.manage.repChg' /></a></span>";
							//}
						}
					}else if(entry['cntrt_status']!='C02402' && entry['prcs_depth']=='C02505' && entry['depth_status']=='C02681'){
						//임시저장 상태일경우...
						//계약담당자일경우
						if(entry['session_user_id']==frm.cntrt_respman_id.value){
							html += "&nbsp;<span class=\'btn_all_w\'><span class=\'tsave\'></span><a href=\"javascript:insertTempCompletion()\"><spring:message code='clm.page.msg.manage.tmpSave' /></a></span>";
							html += "&nbsp;<span class=\'btn_all_w\'><span class=\'preview\'></span><a href=\"javascript:detailPreviewPop()\"><spring:message code='clm.page.msg.manage.preview' /></a></span>";
							 //if(entry['execCmpltStatus']=='Y'){								 
									html +=  "&nbsp;<span class=\'btn_all_w\'><span class=\'reply\'></span><a href=\"javascript:insertCompletion();\"><spring:message code='clm.page.msg.manage.repChg' /></a></span>";
							 //}
								 //+  "&nbsp;<span class=\'btn_all_w\'><span class=\'preview\'></span><a href=\"javascript:detailPreviewPop()\">미리보기</a></span>";
						}
					}else if(entry['cntrt_status']!='C02402' && entry['prcs_depth']=='C02505' && entry['depth_status']=='C02682'){
						//상신 상태일경우... 결재중일경우..
						//계약관리자
						if(entry['session_user_id']==frm.reqman_id.value){
							 //html += "&nbsp;<span class=\'btn_all_w\'><span class=\'preview\'></span><a href=\"javascript:detailPreviewPop\">미리보기</a></span>";							
						}						
					}else if(entry['cntrt_status']!='C02402' && entry['prcs_depth']=='C02505' && entry['depth_status']=='C02685'){
						//상신 상태일경우... 확인중일경우..
						//계약관리자
						if(entry['cfrmYN']==true){
							html +=  "&nbsp;<span class=\'btn_all_w\'><span class=\'reply\'></span><a href=\"javascript:insertCfrmCompletion()\"><spring:message code='clm.page.msg.manage.chgConf' /></a></span>";							
						}						
					}
					$('#contract-btn').append(html);
					$('#contract-btn2').append(html);
				});
			} else {
				var html = "<span class=\'btn_all_w\' ><span class=\'preview\'></span><a href=\"javascript:openHis2();\"><spring:message code='clm.page.msg.manage.print' /></a></span>&nbsp;<span class=\"btn_all_w\"><span class=\"list\"></span><a href=\"javascript:listManageCompletion();\"><spring:message code='clm.page.msg.manage.list' /></a></span>";
				$('#contract-btn').append(html);
				$('#contract-btn2').append(html);
			}
		}
	};
	$("#frm").ajaxSubmit(options);
}

function openChooseClient(){
    var frm = document.frm;
    PopUpWindowOpen('', "530", "470", true, "PopUpChooseClient");
    frm.action = "<c:url value='/clm/manage/chooseClient.do' />";
    frm.method.value="forwardChooseClientPopup";
    frm.target = "PopUpChooseClient";
    frm.submit();
 }

//관련자정보세팅...
function setListClientInfo(returnValue) {
    var arrReturn 	= returnValue.split("!@#$");
    var frm 		= document.frm;
    var innerHtml 	= "";	
    frm.authreq_client.value = returnValue;
    $('#id_trgtman_nm').html("");
    for(var i=0; i < arrReturn.length;i++) {
    	var arrInfo = arrReturn[i].split("|");
    	//$('#reqman_nm').append("<option value=" + arrInfo[2]+">" + arrInfo[2]+ "</option>");
    	if((i != 0 && i != 1) && (i % 2 == 0)){
			innerHtml += "<br/>";
    	}
    	if(i != 0 && (i % 2 != 0)){
    		innerHtml += ",";
    	}
    	
    	innerHtml += arrInfo[2] +"/"+arrInfo[3] + "/" + arrInfo[4] ;
    		
    	$('#id_trgtman_nm').html(innerHtml);
    }
    
 }
 
/**
 * 인쇄버튼 
 * 
 */
function goRdView() {
	 var frm 				= document.frm;
	 // PopUpWindowOpen('', "1000", "600", true);
	 PopUpWindowOpen('', "1000", "600", true, "PopUpWindow");
	 frm.action = "<c:url value='/clm/manage/completion.do' />";
	 frm.method.value="forwardPrintPop";		 		 
	 frm.target = "PopUpWindow";
	 frm.submit();
}

/*
 * 승인이력 - [체결품의] 상세 팝업 띄우기
 */
function openHis(mis_id){
	
	var frm = document.frm;
	
	PopUpWindowOpen('', '1024','768',true,'popUpHistory');
	
	frm.action	= "<c:url value='/clm/manage/consultation.do' />";
	frm.method.value = "forwardHistoryPop";
	frm.mis_id.value = mis_id;
	frm.target = "popUpHistory";
	frm.submit();
}

/*
 * 승인이력 - [종료] 상세 팝업 띄우기
 */
function openHis2() {
	parent.bodyFrame.focus(); 
	OpenPrintPopup();
	//window.print();
	
	/* var frm = document.frm;

	var options = {
 		url: "<c:url value='/clm/manage/completion.do?method=detailPreviewApprovalPop' />",
 		type: "post",
 		async: false,
 		dataType: "html",
 			
 		success: function(data){
 			frm.contents.value=data;
 			showCompletionPop(); 			
 		}
   }
   $("#frm").ajaxSubmit(options); */
}

function OpenPrintPopup()

{

    var OLECMDID = 7;

    /* OLECMDID values:

    * 6 - print

    * 7 - print preview

    * 1 - open window

    * 4 - Save As

    */

    var PROMPT = 1; // 2 DONTPROMPTUSER 

    var WebBrowser = '<OBJECT ID="WebBrowser1" WIDTH=0 HEIGHT=0 CLASSID="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></OBJECT>';

    document.body.insertAdjacentHTML('beforeEnd', WebBrowser); 

    WebBrowser1.ExecWB(OLECMDID, PROMPT);

    WebBrowser1.outerHTML = "";

}



function showCompletionPop(){
	
	var frm = document.frm;
	
	PopUpWindowOpen('', '1024','768',true,'popUpHistory2');
	
	frm.action	= "<c:url value='/clm/manage/completion.do' />";
	frm.method.value = "forwardCompletionHisPop";
	frm.target = "popUpHistory2";
	frm.submit();
}


</script>
</head>
<body>

<!-- 

**************************** Form Setting **************************** 
-->
<form:form name="frm" id='frm' method="post" autocomplete="off">
	<!-- required Form -->
	<input type="hidden" name="method"   value=""/>
	<input type="hidden" name="menu_id" value="<c:out value='${contractCommand.menu_id}'/>" />
	<!-- key hidden Form -->
	<input type="hidden" name="cnsdreq_id"	id="cnsdreq_id"   value="<c:out value='${contractCommand.cnsdreq_id}'/>" />
	<input type="hidden" name="cntrt_id"		id="cntrt_id"     value="<c:out value='${contractCommand.cntrt_id}'/>" />
	<input type="hidden" name="reqman_id"		id="reqman_id"     value="<c:out value='${contractCommand.reqman_id}'/>" />
	
	
	
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

	<!-- forward page -->
	<input type="hidden" name="targetMenuId"/>
	<input type="hidden" name="selected_menu_id"/>
	<input type="hidden" name="selected_menu_detail_id"/>
	<input type="hidden" name="set_init_url"/>
	
	<input type="hidden" name="selected_paygbn"	id="selected_paygbn"    value="" />
	<!--// forward page -->
	
	<!-- //html Contents -->
	<input type="hidden" name="contents"		id="contents"     value="" />
	
	<!-- 리스트 검색 조건시작  -->
	<input type="hidden" name="list_mode" value="<c:out value='${command.list_mode}'/>"/>
	<input type="hidden" name="srch_resp_dept" value="<c:out value='${command.srch_resp_dept}' escapeXml='false'/>"/>				<!-- 담당부서코드 -->
	<input type="hidden" name="srch_oppnt_cd" value="<c:out value='${command.srch_oppnt_cd}' escapeXml='false'/>"/>   				<!-- 계약상대방코드 -->
	<input type="hidden" name="srch_review_title" value="<c:out value='${command.srch_review_title}' escapeXml='false'/>" />		<!-- 의뢰명 -->
	<input type="hidden" name="srch_reqman_nm" value="<c:out value='${command.srch_reqman_nm}' escapeXml='false'/>" />				<!-- 의뢰자 -->
	<input type="hidden" name="srch_start_reqday" value="<c:out value='${command.srch_start_reqday}' escapeXml='false'/>" />		<!-- 의뢰 시작일 -->
	<input type="hidden" name="srch_end_reqday" value="<c:out value='${command.srch_end_reqday}' escapeXml='false'/>" />			<!-- 의뢰 종료일 -->
	<input type="hidden" name="srch_start_cnlsnday" value="<c:out value='${command.srch_start_cnlsnday}' escapeXml='false'/>" />	<!-- 계약 시작일 -->
	<input type="hidden" name="srch_end_cnlsnday" value="<c:out value='${command.srch_end_cnlsnday}' escapeXml='false'/>" />		<!-- 계약 종료일 -->
	<input type="hidden" name="srch_resp_dept_nm" value="<c:out value='${command.srch_resp_dept_nm}' escapeXml='false'/>" />		<!-- 담당부서명 -->
	<input type="hidden" name="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}' escapeXml='false'/>" />			<!-- 담당자명 -->
	<input type="hidden" name="srch_biz_clsfcn" value="<c:out value='${command.srch_biz_clsfcn}' escapeXml='false'/>" />			<!-- 계약단계 -->
	<input type="hidden" name="srch_cnsdman_nm" value="<c:out value='${command.srch_cnsdman_nm}' escapeXml='false'/>" />			<!-- 검토자 -->
	<input type="hidden" name="srch_oppnt_nm" value="<c:out value='${command.srch_oppnt_nm}' escapeXml='false'/>" />				<!-- 계약상대방 -->
	<input type="hidden" name="srch_state" value="<c:out value='${command.srch_state}' escapeXml='false'/>" />						<!-- 상태 -->
	<input type="hidden" name="srch_cnclsnpurps_bigclsfcn" value="<c:out value='${command.srch_cnclsnpurps_bigclsfcn}' escapeXml='false'/>" />	<!-- 체결목적-->
	<input type="hidden" name="ismycontract" id="ismycontract" value="<c:out value='${command.ismycontract}'/>"/>
	<input type="hidden" name="srch_step" value="<c:out value='${command.srch_step}' escapeXml='false'/>" />						<!-- 상태 -->
	<input type="hidden" name="mis_id"/>
<!-- //
**************************** Form Setting **************************** 
-->
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
			<div class="content-step t_titBtn">
				<ol>
					<li><img src="<%=IMAGE %>/common/step01.gif"  	 alt="<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" />" /></li>
					<li><img src="<%=IMAGE %>/common/step02.gif"     alt="<spring:message code="clm.page.msg.manage.step02" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step02" htmlEscape="true" />" /></li>
					<li><img src="<%=IMAGE %>/common/step03.gif"     alt="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" /></li>
					<li><img src="<%=IMAGE %>/common/step04.gif"     alt="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" /></li>
					<li class='step_on'>
						<img src="<%=IMAGE %>/common/step05_on.gif"  alt="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" />
						<div class='step_link'><a href="javascript:open_window('win', '../../step05<%=langCd.equals("ko")?"":"_"+langCd %>.html', 0, 0, 696, 588, 0, 0, 0, 0, 0)" ><img src="<%=IMAGE %>/common/step_q.gif" alt="<spring:message code="clm.page.msg.manage.procStep"/>" title="<spring:message code="clm.page.msg.manage.procStep"/>" /></a></div>
					</li>
				</ol>
			</div>
		
		<!-- 요청의뢰 -->
		<!-- 
		<div class="t_titBtn">
			<div class="title_basic">
				<h4>검토의뢰 정보 <img src="/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'contract-cnsdReqView');"/></h4>
			</div>
			
			<div class="btn_wrap_t02" id="contract-btn"></div>
			
		</div>
		
		<table cellspacing="0" cellpadding="0" class="table-style01" id="contract-cnsdReqView">
			<colgroup>
				<col width="15%" />
				<col width="34%" />
				<col width="12%" />
				<col width="15%" />
				<col width="12%" />
				<col width="12%" />
			</colgroup>
			<tr>
				<th>검토의뢰제목</th>
			  <td colspan="7"><c:out value='${contractReqLom.req_title}' default="" /></td>
			</tr>
			<tr class="lineAdd">
				<th>의뢰자</th>
				<td>
					<c:out value="${contractReqLom.reqman_nm}" />/<c:out value="${contractReqLom.req_dept_nm}" />						
				</td>
				<th>의뢰일</th>
				<td><c:out value="${contractReqLom.req_dt}" /></td>
				<th>회신요청일</th>
				<td colspan="5"><c:out value="${contractReqLom.re_demndday}" /></td>
			</tr>
			<tr>
				<th class="borTz02">검토요청 내용</th>
				<td colspan="5"><c:out escapeXml="false" value="${contractReqLom.cnsd_demnd_cont}" />
														
				</td>
			</tr>
		</table>
		 -->
		<!-- //요청의뢰 -->
		<!-- 계약기본정보 -->
		<!--  title -->
		<div class="t_titBtn">
			<div class="title_basic03">
				<h4><spring:message code="clm.page.msg.manage.bscInfCont" /> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'contract-view-tab');"/></h4>
			</div>
			
		</div>
		<!-- //title -->
		<!-- tab01 -->
		<ul id="contractTab" class="tab_basic03">
		<c:forEach var="list" items="${contractLom}">
			<li class=<c:if test="${list.rn=='1'}">on</c:if>><a href='javascript:detailContract("<c:out value='${list.cnsdreq_id}'/>","<c:out value='${list.cntrt_id}'/>");'><spring:message code="clm.page.msg.common.contract" /> <c:out value='${list.rn}' /></a></li>
		</c:forEach>
		</ul>
		<div class="btn_wrap_t03" id="contract-btn"></div>
		<!-- //tab01 -->
		<!-- 두줄라인 추가 --><div class="tal_topLine"></div><!-- //두줄라인 추가 -->
		<!-- toptable-->
		<!-- 계약기본정보 부분 -->
		<div id="contract-view"></div>
		<!-- //계약기본정보 부분 -->
		<!-- //top table -->
		<!-- //계약기본정보 -->
		<!-- bottom table -->
		<!-- 이행관리 부분 -->
		<div class="tal_subBox" id="contract-view-tab">
			<!-- tab02 -->
			<div class="tab_box">
				<ul id="executionTab" class="tab_basic05">
					<li><a href='javascript:' ><spring:message code="clm.page.msg.manage.contDetail" /></a></li>
					<!-- <li><a href='javascript:' >사전검토정보</a></li> -->
					<!-- <li><a href='javascript:' >체결정보</a></li>
					<li><a href='javascript:' >체결본등록정보</a></li> -->
					<li class="on" id="contract-relationInfoTab"><a href='javascript:' ><spring:message code="clm.page.msg.manage.relContInf" /></a></li>
					<!-- <li class="on"><a href='javascript:'>종료관리</a></li> -->
				</ul>
			</div>
			
			<div id="contract-detail"></div>
			<!-- <div id="contract-cnsdInfo"></div> -->
			<div id="contract-cnclsnInfo"></div>
			<div id="contract-cnclsnIns"></div>
			<div id="contract-relation"></div>
			<!-- <div id="completion-mng"></div> //-->
						
		<!-- //이행관리 부분 -->
		<!-- 종료정보 -->
			<div id="contract-CompletionInfo"></div>
		<!-- //이행및종료이력-->
		<!-- 이행및종료이력 -->
			<div id="contractExeCmpltHis-list"></div>
		<!-- //이행및종료이력-->
		<!-- 이력정보 -->
			<div id="contractHis-list"></div>
		<!-- //이력정보 -->
		<!-- //button -->
			<div id="contract-btn2" align="center" style="padding:20px 0px 20px 0px;"></div>
		</div>
		<!-- //bottom table -->
	</div>
	<!-- footer  -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
	<!-- // footer -->
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</div>
</div>
</form:form>
</body>
</html>

