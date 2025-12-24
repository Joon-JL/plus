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
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="com.sec.clm.manage.dto.ConsultationExecForm" %>
<%
	ListOrderedMap lomReq 	 	=  (ListOrderedMap)request.getAttribute("lomReq");
    ListOrderedMap contractLom  =  (ListOrderedMap)request.getAttribute("contractLom");
    
    ArrayList authReqList = (ArrayList)request.getAttribute("authReqList");  // 관련자 
%>
<%--
/**
 * 파  일  명 	 :Conclusion_d_view_new.jsp
 * 프로그램명      : 체결 본 등록 상세 화면
 * 설      명 	 : 
 * 작  성  자 	 : 박정훈
 * 작  성  일      : 2013.04
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="clm.main.title"/></title>
<!--[J15001]-->
<!--계약관리일 경우  -->
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet"></link>
<!-- [if IE]><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/excanvas.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery-1.7.2.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<!-- 달력관련추가 -->
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script src="/script/func/ConsiderationFunc.js"></script><!-- 2014-07-28 Kevin added -->
<script>
<!--
$(document).ready(function(){
	
	// 2014-07-28 Kevin added.
	// Following codes retrive step/status information and show them.
	var considerationFunc = new ConsiderationFunc($);
	considerationFunc.getStepStatusInfoPerCnsdreqid($("#cnsdreq_id").val(), function(data){
		$("#spStep").text(data.step);
		$("#spStatus").text(data.status+" "+data.status_depth);
	});
	
    // 이력 첨부파일
    contractHisList();		//Sungwoo added 2014-09-01
    
    var collapseIcon = '<%=IMAGE%>/icon/ico_plus.gif';
    var expandIcon = '<%=IMAGE%>/icon/ico_minus.gif';
    
    //검토이력
    $('img[alt$=show]').toggle(function(){
        $(this).removeAttr().attr("src",expandIcon);
        $(this).parent().parent().parent().next('#tr_show').show();   
        
        $("iframe[id^='final_cont']").each(function(i,o){
			var is_chrome = navigator.userAgent.toLowerCase().indexOf('chrome') > -1;
			if (is_chrome) {
				o.height = o.contentDocument.documentElement.scrollHeight + 30;	
			} else {
				o.height = o.contentWindow.document.body.scrollHeight + 30;	
			}
		});
    }, function(){
        $(this).removeAttr().attr("src",collapseIcon);
        $(this).parent().parent().parent().next('#tr_show').hide();
    });
    //승인이력
    $('img[alt$=show01]').toggle(function(){
        $(this).removeAttr().attr("src",expandIcon);
        $(this).parent().parent().parent().next('#tr_show01').show();
        
    }, function(){
        $(this).removeAttr().attr("src",collapseIcon);
        $(this).parent().parent().parent().next('#tr_show01').hide();
    });
    //체결이력
    $('img[alt$=show02]').toggle(function(){
        $(this).removeAttr().attr("src",expandIcon);
        $(this).parent().parent().parent().next('#tr_show02').show();
        
    }, function(){
        $(this).removeAttr().attr("src",collapseIcon);
        $(this).parent().parent().parent().next('#tr_show02').hide();
    });
    
    if(  $('#depth_status').val() == 'C02641'){
    	$('.attach').each(function(i){
    		$(this).css({ display: "none" });
    	});
    }
    initAttach();
    initGerpInfo();
    initRequire();
    initButton();
});

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
	//GSEMS로 계약서를 전송하시겠습니까? 10분 후에 GSEMS에서 확인하시기 바랍니다.
	if(confirm("<spring:message code="clm.page.msg.manage.announce008" />" + "<spring:message code="clm.page.msg.manage.announce003" />")){			
		$("#frm").ajaxSubmit(options);		
	}
}

//첨부파일초기화
function initAttach(){
	
  //계약-계약서파일
	frm.target          		= "fileConsultationContract";
    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
    frm.method.value    		= "forwardClmsFilePage";
    frm.file_bigclsfcn.value 	= "F012";
    frm.file_midclsfcn.value 	= "F01202";
    frm.file_smlclsfcn.value	= "F0120201";
    frm.view_gbn.value  		= "download";
    frm.ref_key.value   		= frm.cntrt_id.value + "@" + frm.cnsdreq_id.value;
    frm.fileInfoName.value 		= "fileInfos";
    frm.fileFrameName.value 	= "fileConsultationContract";
    frm.multiYn.value 			= "N";
    getClmsFilePageCheck('frm');   
	
  //계약-별첨
    frm.target          		= "fileConsultationEtc";
    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
    frm.method.value   		 	= "forwardClmsFilePage";
    frm.file_bigclsfcn.value 	= "F012";
    frm.file_midclsfcn.value 	= "F01202";
    frm.file_smlclsfcn.value 	= "F0120208";
    frm.view_gbn.value  		= "download";
    frm.ref_key.value   		= frm.cntrt_id.value + "@" + frm.cnsdreq_id.value;
    frm.fileInfoName.value 		= "fileInfos5";
    frm.fileFrameName.value 	= "fileConsultationEtc";
    frm.multiYn.value 			= "Y";
    getClmsFilePageCheck('frm');
    
    //계약-기타_체결본
    frm.target          		= "fileConsultationEtc2";
    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
    frm.method.value   		 	= "forwardClmsFilePage";
    frm.file_bigclsfcn.value 	= "F012";
    frm.file_midclsfcn.value 	= "F01202";
    frm.file_smlclsfcn.value 	= "F0120205";
    frm.view_gbn.value  		= "download";
    frm.ref_key.value   		= frm.cntrt_id.value + "@" + frm.cnsdreq_id.value;
    frm.fileInfoName.value 		= "fileInfos2";
    frm.fileFrameName.value 	= "fileConsultationEtc2";
    frm.multiYn.value 			= "Y";
    getClmsFilePageCheck('frm');   
    
    //사전검토정보-첨부
    frm.target          		= "fileConsultationPre";
    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
    frm.method.value    		= "forwardClmsFilePage";
    frm.file_bigclsfcn.value 	= "F012";
    frm.file_midclsfcn.value 	= "F01201";
    frm.file_smlclsfcn.value 	= "F0120101";
    frm.view_gbn.value  		= "download";
    frm.ref_key.value   		= frm.cntrt_id.value;
    frm.fileInfoName.value 		= "fileInfos3";
    frm.fileFrameName.value 	= "fileConsultationPre";
    //frm.submit();
    getClmsFilePageCheck('frm'); 
    
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
    getClmsFilePageCheck('frm');
    
  //단가내역-첨부
  	if(<%=StringUtil.isNull((String)lomReq.get("cntrt_untprc_expl"))%> == false) {	
  		frm.target          		= "fileConsultationUntPrc";
	    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    		= "forwardClmsFilePage";
	    frm.file_bigclsfcn.value 	= "F012";
	    frm.file_midclsfcn.value 	= "F01202";
	    frm.file_smlclsfcn.value 	= "F0120211";
	    frm.view_gbn.value  		= "download";
	    frm.ref_key.value   		= frm.cntrt_id.value;
	    frm.fileInfoName.value 		= "fileInfos4";
	    frm.fileFrameName.value 	= "fileConsultationUntPrc";
	    frm.multiYn.value 			= "Y";
	    getClmsFilePageCheck('frm');
  	}
  //체결본사본
    frm.target          		= "fileContractCopy";
    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
    frm.method.value    		= "forwardClmsFilePage";
    frm.preAllowFileList.value  = "PDF,P7M,"; 
    if(frm.depth_status.value == "C02642") {
    	frm.view_gbn.value  		= "modify";
    } else {
    	frm.view_gbn.value  		= "download";	
    }
    frm.file_bigclsfcn.value 	= "F012";
    frm.file_midclsfcn.value	= "F01203";
    frm.file_smlclsfcn.value 	= "";
    frm.fileFrameName.value		= "fileContractCopy";
    frm.fileInfoName.value 		= "fileInfos6";
    frm.multiYn.value 			= "N";
    frm.ref_key.value			= frm.cntrt_id.value;
    
    if(frm.depth_status.value == "C02642") {
    	if("<c:out value='${command.session_user_id}'/>" == frm.cntrt_respman_id.value ) {
    		frm.submit();	
    	} else {
    		getClmsFilePageCheck('frm');
    	}	
    		
    } else {    	
    	getClmsFilePageCheck('frm');
    }	
    
 
}

function initGerpInfo(){
	// 2014-04-15 Kevin Added.
    // GERP readonly iframe load. 
    frm.target          = "iframeGERP";
    frm.action          = "<c:url value='/clm/manage/consideration.do' />";
    frm.gerpPageType.value = "R";		// detail readonly
    frm.method.value    = "forwardGERPDetail";
	frm.submit();
}

//체결여부에따른 입력필수값 초기화
function initRequire() {
	var cnclsn_yn = $('input[name*=cntrt_cnclsn_yn]:checked').val();
	var seal_mthd = $('input[name*=seal_mthd]:checked').val();
	var depth_status = $('input[name=depth_status]').val();
	
	if(depth_status == "C02641") {			//미등록상태
		if(cnclsn_yn =="Y"){
			if(seal_mthd=="C02101") {
				$('input[name=signman_id]').removeAttr("required");				//당사서명자아이디
				$('input[name=signman_nm]').removeAttr("required");				//당사서명자명
				$('input[name=sign_dept_nm]').removeAttr("required");			//당사서명자부서
				$('input[name=signday]').removeAttr("required");				//당사서명일
			} else {
				$('input[name=signman_id]').attr("required","");				//당사서명자아이디
				$('input[name=signman_nm]').attr("required","");				//당사서명자명
				$('input[name=sign_dept_nm]').attr("required","");				//당사서명자부서
				$('input[name=signday]').attr("required","");					//당사서명일
				
				$('input[name=signman_id]').removeAttr("disabled");		        //당사서명자아이디
				$('input[name=signman_nm]').removeAttr("disabled");				//당사서명자명
				$('input[name=sign_dept_nm]').removeAttr("disabled");			//당사서명자부서
				$('input[name=signday]').removeAttr("disabled");				//당사서명일
				if($('#signday_notidayCal_calIcon').attr("style") == "DISPLAY: none"){
					initCal('signday');
				}
				$('input[name=signman_id]').parent().removeAttr("style");		        //당사서명자아이디
				$('input[name=signman_nm]').parent().removeAttr("style");				//당사서명자명
				$('input[name=sign_dept_nm]').parent().removeAttr("style");			//당사서명자부서
				$('input[name=signday]').parent().removeAttr("style");				//당사서명일
				
			}
			
			$('input[name=oppnt_signman_nm]').removeAttr("disabled");			//상대서명자아이디
			$('input[name=oppnt_signman_jikgup]').removeAttr("disabled");		//상대서명자명
			$('input[name=oppnt_signman_dept]').removeAttr("disabled");			//상대서명자부서
			$('input[name=oppnt_signday]').removeAttr("disabled");				//상대서명일
			$('input[name=cntrt_cnclsnday]').removeAttr("disabled");			//계약체결일
			//$('input[name=exprt_notiday]').removeAttr("disabled");				//계약만료사전알림일	신성우 주석처리 2014-04-01
			
			//	신성우 주석처리 2014-04-01
			 
			//if($('#exprt_notidayCal_calIcon').attr("style") == "DISPLAY: none"){
				//initCal('exprt_notiday');	신성우 주석처리 2014-04-01
				initCal('oppnt_signday');
				initCal('cntrt_cnclsnday');
			//}
			$('input[name=oppnt_signman_nm]').parent().removeAttr("style");			//상대서명자아이디
			$('input[name=oppnt_signman_jikgup]').parent().removeAttr("style");		//상대서명자명
			$('input[name=oppnt_signman_dept]').parent().removeAttr("style");		//상대서명자부서
			$('input[name=oppnt_signday]').parent().removeAttr("style");			//상대서명일
			//$('input[name=exprt_notiday]').parent().removeAttr("style");			//계약만료사전알림일	신성우 주석처리 2014-04-01
			$('input[name=cntrt_cnclsnday]').parent().removeAttr("style");			//계약체결일
			$('#conclusion-delayinfo-list').attr("style", "display:none");
		} else {
			if(seal_mthd=="C02102") {
				$('input[name=signman_id]').attr("disabled","disabled");		//당사서명자아이디
				$('input[name=signman_nm]').attr("disabled","disabled");		//당사서명자명
				$('input[name=sign_dept_nm]').attr("disabled","disabled");		//당사서명자부서
				$('input[name=signday]').attr("disabled","disabled");			//당사서명일
				$('#signdayCal_calIcon').attr("style","display:none").prev().css("border-right","#7f9db9 1px solid");
				
				$('input[name=signman_id]').parent().attr("style", "background:#F4F4F4");
				$('input[name=signman_nm]').parent().attr("style", "background:#F4F4F4");
				$('input[name=sign_dept_nm]').parent().attr("style", "background:#F4F4F4");
				$('input[name=signday]').parent().attr("style", "background:#F4F4F4");
			}
			
			$('input[name=oppnt_signman_nm]').attr("disabled", "disabled");		//상대서명자아이디
			$('input[name=oppnt_signman_jikgup]').attr("disabled","disabled");	//상대서명자명
			$('input[name=oppnt_signman_dept]').attr("disabled","disabled");	//상대서명자부서
			$('input[name=oppnt_signday]').attr("disabled","disabled");			//상대서명일
			$('#oppnt_signdayCal_calIcon').attr("style","display:none").prev().css("border-right","#7f9db9 1px solid");
			$('input[name=cntrt_cnclsnday]').attr("disabled","disabled");		//계약체결일
			$('#cntrt_cnclsndayCal_calIcon').attr("style","display:none").prev().css("border-right","#7f9db9 1px solid");

			$('input[name=oppnt_signman_nm]').parent().attr("style", "background:#F4F4F4");
			$('input[name=oppnt_signman_jikgup]').parent().attr("style", "background:#F4F4F4");
			$('input[name=oppnt_signman_dept]').parent().attr("style", "background:#F4F4F4");
			$('input[name=oppnt_signday]').parent().attr("style", "background:#F4F4F4");
			$('input[name=cntrt_cnclsnday]').parent().attr("style", "background:#F4F4F4");
			$('#conclusion-delayinfo-list').attr("style", "display:");
		}	
	}
}	

function initButton() {
	var frm = document.frm;
	var depth_status = $('input[name=depth_status]').val();
	if("<c:out value='${command.session_user_id}'/>" == frm.cntrt_respman_id.value || "RA01" == "<c:out value='${command.top_role}' />") {
		if(depth_status == "C02642") {
			$('span[id^=coclusioncopy_btn]').attr("style", "display:");
		}
		else {
			
			$('span[id^=coclusioncopy_btn]').attr("style", "display:none");
		}
	
		if(depth_status == "C02641") {			//미등록상태
			$('div[id^=conclusion_btn]').attr("style", "display:;margin-top:-23px;margin-bottom:0px");
			$('div[id^=conclusion_btn_list]').attr("style", "display:none");
			
			$('div[id^=conclusion_btn2]').attr("styldisplay:");
            $('div[id^=conclusion_btn_list2]').attr("style", "display:none");
		} else {
			$('div[id^=conclusion_btn]').attr("style", "display:none");
			$('div[id^=conclusion_btn_list]').attr("style", "display:;margin-top:-23px;margin-bottom:0px");
			
			$('div[id^=conclusion_btn2]').attr("style", "display:none");
            $('div[id^=conclusion_btn_list2]').attr("style", "display:;");
		}	
	} else {
		$('div[id^=conclusion_btn]').attr("style", "display:none");
		$('div[id^=conclusion_btn_list]').attr("style", "display:;margin-top:-23px;margin-bottom:0px");
		
		$('div[id^=conclusion_btn2]').attr("style", "display:none");
        $('div[id^=conclusion_btn_list2]').attr("style", "display:;");
        
        $('span[id^=coclusioncopy_btn]').attr("style", "display:none");
	}
}

function goModify() {
	var frm = document.frm;
	
	frm.action			= "<c:url value='/clm/manage/conclusion.do' />";
	frm.target			= "_self";
	frm.page_gbn.value	= "modify";
	frm.method.value	= "detailConclusionNew";
	viewHiddenProgress(true);
	frm.submit();
}

function goList() {
	
	viewHiddenProgress(true);	

	if(frm.ismycontract.value == 'Y'){
		frm.target = "_self";		 
		frm.action = "<c:url value='/clm/manage/myContract.do' />";
		frm.method.value = "listMyContract";
		frm.submit();
	}
	else{
		frm.action="<c:url value='/clm/manage/conclusion.do' />";
		frm.target="_self";
		frm.method.value="listManageConclusion";
		frm.submit();
	}
}

//팝업오픈-모달리스
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
	
	var Feture = "toolbar=0, location=0, status=1, menubar=0, scrollbars=" + (bScroll ? "yes" : "no") + ", resizable=yes, top="+Top+", left="+Left+", width="+popupwidth+", height="+popupheight;
	PopUpWindow = window.open(surl, popName , Feture);
	PopUpWindow.focus();
	
}

/*
 * 해당 계약건 Drop!! -> 보류로 수정 12/06/29 
 */
function goDefer(type){
	var str = "";	
		
	if(type == 'deferRequest'){
		str = "<spring:message code="clm.page.msg.manage.announce203" />";		
	}
	else if(type == 'deferCancelRequest'){
		str = "<spring:message code="clm.page.msg.manage.announce204" />";
	}
	
	if(confirm(str)){
		opnnConsideration(type);
	}
}
		

/**
 * 계약 Drop 팝업 
 */
function opnnConsideration(type){
	
 	var frm = document.frm;
 	
	PopUpWindowOpen('', "530", "200", true, "popUpDrop");
	frm.submit_status.value = type;
    frm.action = "<c:url value='/clm/manage/conclusion.do?' />";
    frm.method.value="opnnConclusion";
    frm.target = "popUpDrop";
    frm.submit();	
}

//표지인쇄 팝업
function openCoverPrint(){
    
	var frm = document.frm;
	
	PopUpWindowOpen('', '1000','768',true, "popupMyApproval");
	
	frm.action	= "<c:url value='/clm/manage/myApproval.do' />";
	frm.method.value = "forwardPrintPop";
	frm.target = "popupMyApproval";
	frm.submit();
}

/**
 * 회신상태로 프로세스 복원
 */
function processCancel(){
	if(!confirm("<spring:message code="clm.page.msg.manage.announce175" />")) return;
	
	frm.method.value = "cancelConclusion";	
	frm.target = "_self";
	frm.action = "<c:url value='/clm/manage/conclusion.do' />";
    frm.submit();
    
    viewHiddenProgress(true) ;
}

/**
 * 원본미접수 상태에서 체결본사본 수정 버튼 클릭 시 호출
 */
function modifyCoclusionCopy(){
	var frm = document.frm;
	
	if(!confirm("<spring:message code="clm.page.msg.manage.announce170" />")) return;
	
	
	fileContractCopy.UploadFile(function(uploadCount){
	//첨부파일 업로드 결과 잘못된 형식의 파일이 올라간 경우
    if(uploadCount == -1){
    	initAttach();
        alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
        return;
    }

	//첨부파일이 필수 인 경우 : 사용자 선택사항
  	if(uploadCount == 0){
  		alert("<spring:message code='clm.msg.alert.contract.conclusion.nofile' />");
      	return;
  	}
        
	frm.method.value = "modifyCoclusionCopy";	
	frm.target = "_self";
	frm.action = "<c:url value='/clm/manage/conclusion.do' />";
    frm.submit();
	});
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


// To receive user search result who will be added as CC.
function setListClientInfo(returnValue) {
    var arrReturn = returnValue.split("!@#$");
    var innerHtml ="";
    var isListEmpty = (arrReturn.length === 1 && arrReturn[0] === "");
    //if(arrReturn[0]=="") { return ; }

    $('#id_trgtman_nm').html("");

    var trgtmanIds = [];

    if(!isListEmpty) {
        for(var i=0; i < arrReturn.length;i++) {
            var arrInfo = arrReturn[i].split("|");

            if (arrInfo.length >=5) {
                trgtmanIds.push(arrInfo[1]);
                if ((i != 0 && i != 1) && (i % 2 == 0)) {
                    innerHtml += "<br/>";
                }
                if (i != 0 && (i % 2 != 0)) {
                    innerHtml += ",";
                }
                innerHtml += "<input type='hidden' name='arr_demnd_seqno' id='arr_demnd_seqno' value='" + arrInfo[0] + "' />";
                innerHtml += "<input type='hidden' name='arr_trgtman_id' id='arr_trgtman_id' value='" + arrInfo[1] + "' />";
                innerHtml += "<input type='hidden' name='arr_trgtman_nm' id='arr_trgtman_nm' value='" + arrInfo[2] + "' />";
                innerHtml += "<input type='hidden' name='arr_trgtman_jikgup_nm' id='arr_trgtman_jikgup_nm' value='" + arrInfo[3] + "' />";
                innerHtml += "<input type='hidden' name='arr_trgtman_dept_nm' id='arr_trgtman_dept_nm' value='" + arrInfo[4] + "' />";
                innerHtml += arrInfo[2] + "/" + arrInfo[3] + "/" + arrInfo[4];
            }
        }
        $('#id_trgtman_nm').html(innerHtml);
    }

    // 관련자 리스트 수정 여부 저장
    $("#client_modify_div").val("Y");

    // 여기 부터 AJAX 로 실시간 DB 저장 처리   메소드 명 modifyRefCCAJAX
    var options = {
        url: "<c:url value='/clm/review/consideration.do?method=modifyRefCCAJAX' />",
        type: "post",
        dataType: "json"
    };

    $("#frm").ajaxSubmit(options);
}

//TOP 30 팝업
	function openTop30Customer(){
		   PopUpWindowOpen2("/clm/draft/customerTest.do?method=listTop30Customer", 500, 540, false, "Top30Customer");
	}
	
	function PopUpWindowOpen2(surl, popupwidth, popupheight, bScroll, popName) {
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
		 	
		 	var Feture = "toolbar=0, location=0, status=1, menubar=0, scrollbars=" + (bScroll ? "yes" : "no") + ", resizable=no, top="+Top+", left="+Left+", width="+popupwidth+", height="+popupheight;
		 	PopUpWindow = window.open(surl, popName , Feture);

		 	PopUpWindow.focus();
		 	
		 } 
		 

/**
 * 계약 Cancel Drop 후 리스트로 이동! 
 */
function setOpnnConsideration(obj) {    
	var frm = document.frm;
	
	goList();
}

		 
//-->	
</script>
</head>
<body>
<!-- container -->
<div id="wrap">
	<div id="container">
		<!-- location -->
		<div class="location">
	    	<img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0"/><c:out value="${menuNavi}" escapeXml="false"/>
	    </div>
		<!-- //location -->
		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.msg.manage.regCont" /></h3>
		</div>
		<!-- content -->
		<div id="content">
			<div id="content_in">
			<form:form name="frm" id='frm' method="post" autocomplete="off">
			<input type="hidden" name="contents" id="contents" />
			<!-- 리스트 검색 조건시작  -->
			<input type="hidden" name="list_mode" value="<c:out value='${command.list_mode}' escapeXml='false'/>"/>											<!-- 검색구분(의뢰별/계약별)-->
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
			<!-- 13/10/29 검색 조건 유지 추가 -->
			<input type="hidden" name="srch_cntrt_nm" value="<c:out value='${command.srch_cntrt_nm}'/>" />						<!-- Contract Title -->
			<input type="hidden" name="srch_cntrt_no" value="<c:out value='${command.srch_cntrt_no}'/>" />						<!-- Contract ID -->
			<input type="hidden" name="srch_tnc_no" value="<c:out value='${command.srch_tnc_no}'/>" />						<!-- tnc_no -->
			<input type="hidden" name="srch_str_org_acptday" value="<c:out value='${command.srch_str_org_acptday}'/>" />		<!-- Original Copy -->
			<input type="hidden" name="srch_end_org_acptday" value="<c:out value='${command.srch_end_org_acptday}'/>" />		<!-- Original Copy -->
			<input type="hidden" name="method" id="method" value=""/>
			<input type="hidden" name="srch_user_cntnt_type" value=""/>
			<input type="hidden" name="srch_user_cntnt" value=""/>
			<input type="hidden" name="srch_user_cntnt_target" value=""/>
			<input type="hidden" name="user_role" value="<c:out value='${command.user_role}'/>"/>
			<input type="hidden" name="user_orgnz" value="<c:out value='${command.user_orgnz}'/>"/>
			<input type="hidden" name="menu_id" id="menu_id" value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="curPage" id="curPage" value="<c:out value='${command.curPage}'/>"/>
			<input type="hidden" name="work_type" id="work_type" value=""/>
			<input type="hidden" name="page_gbn" id="page_gbn" value="view"/>
		    <!-- 계약추가 증가데이타 -->	    
		    <input type="hidden" name="cnsdreq_id" id="cnsdreq_id" value="<c:out value='${lomReq.cnsdreq_id}'/>" />	 
		    <input type="hidden" name="cntrt_id"   id="cntrt_id"  value="<c:out value='${lomReq.cntrt_id}'/>" />
		    <input type="hidden" name="cntrt_untprc_expl" id="cntrt_untprc_expl"  value="<c:out value='${lomReq.cntrt_untprc_expl}'/>" />
		    <input type="hidden" name="prgrs_status" id="prgrs_status"  value="<c:out value='${lomReq.prgrs_status}'/>" /> 
		    <input type="hidden" name="cntrt_no"   id="cntrt_no"  value="<c:out value='${cntrt_no}'/>" />
		    <input type="hidden" name="ssems_oppnt_cd"   id="ssems_oppnt_cd"  value="<c:out value='${ssems_oppnt_cd}'/>" />
		    <!-- 첨부파일정보 시작 -->
			<input type="hidden" name="fileInfos"       value="" /> 		<!-- 계약서 파일 정보 -->
			<input type="hidden" name="fileInfos2"      value="" /> 		<!-- 기타 파일 정보 -->
			<input type="hidden" name="fileInfos3"      value="" /> 		<!-- 사전검토 파일 정보 -->
			<input type="hidden" name="fileInfos4"      value="" /> 		<!-- 단가 파일 정보 -->
			<input type="hidden" name="fileInfos5"      value="" /> 		<!-- 별첨 파일 정보 -->
			<input type="hidden" name="fileInfos6"      value="" /> 		<!-- 체결본사본 -->
			<input type="hidden" name="file_bigclsfcn"  value="F012" /> 	<!-- 대분류 -->
			<input type="hidden" name="file_midclsfcn"  value="" /> 		<!-- 중분류 -->
			<input type="hidden" name="file_smlclsfcn"  value="" /> 		<!-- 소분류 -->
			<input type="hidden" name="ref_key"     	value="" /> 		<!-- 키값 -->
			<input type="hidden" name="view_gbn"    	value="download" />	<!-- 첨부파일 화면 -->
			<input type="hidden" name="fileInfoName"    value="" /> 		<!-- 저장될 파일 정보 -->
			<input type="hidden" name="fileFrameName"   value="" /> 		<!-- 소분류 -->
			<input type="hidden" name="multiYn"   		value="" /> 		<!-- 다중파일등록 -->
			<input type="hidden" name="buGubn" value=""/>
			<input type="hidden" name="mis_id" />
			<input type="hidden" name="submit_status" id="submit_status" /> <!-- 12/06/29 보류/보류해제 구분을 위한 추가 -->
			<!-- Conclusion_d_master.jsp 에 있던 hiiden -->
			<input type="hidden" name="prcs_depth" id="prcs_depth" value="<c:out value='${contractLom.prcs_depth}'/>"   />   <!-- 프로세스단계 파일 정보 -->
		    <input type="hidden" name="depth_status" id="depth_status" value="<c:out value='${contractLom.depth_status}'/>" />  <!-- 단계상태 -->
		    <input type="hidden" name="cntrt_status" id="cntrt_status"  value="<c:out value='${contractLom.cntrt_status}'/>" />     <!-- 계약상태-->
		    <input type="hidden" name="cntrt_respman_id" value="<c:out value='${contractLom.cntrt_respman_id}'/>"/>
		    <input type="hidden" name="authreq_client" value="<c:out value='${reqAuthSvcInfo}'/>" />
			<input type="hidden" name="preAllowFileList"  value="" /> 
			<input type="hidden" name="maxCount" id="maxCount" value=""/>
			<input type="hidden" name="maxCount2" id="maxCount2" value=""/>
			<input type="hidden" name="maxCount3" id="maxCount3" value=""/>
			<!-- 관련자 데이타 설정(구주추가요청 14.01)  -->
			<input type="hidden" name="master_cntrt_id" id="master_cntrt_id"  value="<c:out value='${lomReq.cntrt_id}'/>" />
			<input type="hidden" name="client_modify_div" id="client_modify_div" />
		    <input type="hidden" name="chose_client" id="chose_client" />
		    <!-- 2014-04-15 Kevin added. GERP 관련 페이지 형태 구분.(R/I) -->
			<input type="hidden" name="gerpPageType" id="gerpPageType" />
			    
			<!-- Sungwoo added search parameter 2014-06-12 -->
			<input type="hidden" name="srch_division" value="<c:out value='${command.srch_division}'/>" />					<!-- Original Copy -->
			<input type="hidden" name="srch_vendor_type" value="<c:out value='${command.srch_vendor_type}'/>" />					<!-- Original Copy -->
			<input type="hidden" name="srch_vendor_type_detail" value="<c:out value='${command.srch_vendor_type_detail}'/>" />		<!-- Original Copy -->
			
			<div class="content-step t_titBtn">
				<ol>
					<li><img src="<%=IMAGE %>/common/step01.gif"	 alt="<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" />" /></li>
					<li><img src="<%=IMAGE %>/common/step02.gif"     alt="<spring:message code="clm.page.msg.manage.step02" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step02" htmlEscape="true" />" /></li>
					<li class='step_on'>
						<img src="<%=IMAGE %>/common/step03_on.gif"  alt="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" />
						<img src="<%=IMAGE %>/common/step_arr01.gif" alt="Next" title="Next" />
						<div class='step_link'><a href="javascript:open_window('win', '../../step03<%=langCd.equals("ko")?"":"_"+langCd %>.html', 0, 0, 696, 588, 0, 0, 0, 0, 0)" ><img src="<%=IMAGE %>/common/step_q.gif" alt="<spring:message code="clm.page.msg.manage.procStep"/>" title="<spring:message code="clm.page.msg.manage.procStep"/>" style="padding-top:5px; padding-left:3px;" /></a></div>
					</li>
					<li><img src="<%=IMAGE %>/common/step04.gif"     alt="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" /></li>
					<li><img src="<%=IMAGE %>/common/step05.gif"     alt="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" /></li>
				</ol>
			</div>
			<!-- //tab01 -->
			
			<!-- title -->
		  	<div class="title_basic">
				<h4><spring:message code="clm.page.msg.manage.contInfo" /></h4>
			</div>
			<!-- //title -->
			<!-- tab01 --> 
			<div class="t_titBtn">
				<div class='title_basic3'><spring:message code="clm.page.msg.manage.bscInf" /></div>
				
				<div class="btn_wrap_t03" id="conclusion_btn" style="display:none;">
					<span class="btn_all_w"><span class="list"></span><a href="javascript:goModify();"><spring:message code="clm.page.msg.manage.conclCopyReg" /></a></span>
					<c:if test="${contractLom.if_sys_cd != 'MTECH' && contractLom.if_sys_cd != 'CMS' && contractLom.if_sys_cd != 'CPCEX' && contractLom.if_sys_cd != 'myProject'}">
					    <c:if test="${ command.top_role != 'RA01' }">
					        <span class="btn_all_w"><span class="reset"></span><a href="javascript:processCancel();"><spring:message code="clm.page.button.contract.rollback2" /></a></span>
					    </c:if>
					</c:if>
					<span id ="id_defer" class="btn_all_w"><span class="defer"></span><a href="javascript:goDefer('deferRequest');"><spring:message code='clm.page.button.contract.defer' /></a></span>
					<span class="btn_all_w ml_10"><span class="print"></span><a href="javascript:openPrint();"><spring:message code='las.page.button.lawconsult.print' /></a></span>
					<span class="btn_all_w"><span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /></a></span>
				</div>
				<div class="btn_wrap_t03" id="conclusion_btn_list" style="display:none;">	
					
					<c:if test="${lomReq.depth_status == 'C02642' && ssems_oppnt_cd != null && ssems_oppnt_cd != ''}">
						<!-- GSEMS로 계약서 가져오기 -->
						<span class="btn_all_w"><span class="list"></span><a href="javascript:listSSemsIF();"><spring:message code="clm.page.msg.manage.ssemsCont" /></a></span>
					</c:if>
					
					<c:if test="${lomReq.depth_status == 'C02647' }">
						<!-- Hold release -->						
						<span id ="id_defer" class="btn_all_w"><span class="defer"></span><a href="javascript:goDefer('deferCancelRequest');"><spring:message code='clm.page.button.contract.defernone' /></a></span>
					</c:if>
					
					<span class="btn_all_w ml_10">
					<c:choose>
						<c:when test="${lomReq.depth_status == 'C02642'}">
						    <a href="javascript:openCoverPrint();"><span class="print_title"></span><spring:message code="clm.page.msg.manage.printCover" /></a>
						</c:when>
						<c:otherwise>
							<a href="javascript:openPrint();"><span class="print"></span><spring:message code="clm.page.msg.manage.print" /></a>
						</c:otherwise>
					</c:choose>
					</span>
					<span class="btn_all_w"><span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /></a></span>
				</div>
			</div>
			
			<table class="table-style01">
		        <colgroup>
                    <col width="16%" />
                    <col width="11%" />
                    <col width="14%" />
                    <col width="16%" />
                    <col width="15%" />
                    <col width="14%" />
                    <col width="16%" />
                </colgroup>
		        <tr>
                    <th><spring:message code="clm.page.msg.manage.reqName" /></th>
                    <td colspan="7"><c:out value="${contractLom.req_title}" escapeXml="false"/></td>
                </tr>
                <tr>
		            <th><spring:message code='clm.page.field.contract.basic.name' /></th>
		            <td colspan="4"><c:out value="${contractLom.cntrt_nm}" /></td>
		            <th><spring:message code="clm.page.msg.manage.contId" /></th>
                    <td><c:out value="${contractLom.cntrt_no}" />                    
                    	<%if(contractLom.get("tnc_no")!=null && !contractLom.get("tnc_no").equals("")) { %>
				            <span style='color:#A9B4BC;'>(<%=contractLom.get("tnc_no") %>)</span>
				            <%  } %>
                    </td>
		        </tr>
		        <tr>
                    <th><spring:message code="clm.page.msg.manage.reqPerson" /></th>
                    <td colspan="2">
                        <c:out value="${contractLom.reqman_nm}" />/
                        <c:out value="${contractLom.reqman_jikgup_nm}" />/
                        <c:out value="${contractLom.req_dept_nm}" />
                    </td>
                    <th><spring:message code="clm.page.msg.manage.chrgPerson" /></th>
                    <td>
                        <c:out value="${contractLom.cntrt_respman_nm}" />/
                        <c:out value="${contractLom.cntrt_respman_jikgup_nm}" />/
                        <c:out value="${contractLom.cntrt_resp_dept_nm}" />
                    </td>
                    <th><spring:message code="clm.page.msg.manage.reviewer" /></th>
                    <td><c:out value="${contractLom.cnsdmans}"  escapeXml="false"/></td>
                </tr>
                <!-- 2014-07-28 Kevin added. Step / Status -->
				<tr class="lineAdd">
					<th class="borTz02"><spring:message code="clm.page.field.manageCommon.step" /></th>
					<td colspan="2"><span id="spStep"></span></td>
					<th><spring:message code="clm.page.field.manageCommon.status" /></th>
					<td colspan="3"><span id="spStatus"></span></td>
				</tr>
                <tr>
		            <th><spring:message code='clm.page.msg.common.otherParty' /></th>
		            <td colspan="2">
		                <input type="hidden" name="customer_cd" id="customer_cd" value="" />
		                <input type="hidden" name="dodun" id="dodun" value="" />
		                <a href="javascript:customerPop('<c:out value="${contractLom.cntrt_oppnt_cd}" />','<c:out value="${contractLom.cntrt_oppnt_cd}" />');">
		                    <c:out value="${contractLom.cntrt_oppnt_nm}" />
		                </a>
		            </td>
		            <th><spring:message code='clm.page.field.customer.registerNo' /></th>
		            <td class="last-td"><c:out value="${contractLom.cntrt_oppnt_rprsntman}" escapeXml="false"/></td>
		            <th></th>
		            <td></td>
		        </tr>
		        <tr>
                    <th><spring:message code='clm.page.field.contract.basic.type' /></th>
                    <td colspan="4">
                      <c:out value='${contractLom.biz_clsfcn_nm}'/> / <c:out value='${contractLom.depth_clsfcn_nm}'/> / <c:out value='${contractLom.cnclsnpurps_bigclsfcn_nm}'/> / <c:out value='${contractLom.cnclsnpurps_midclsfcn_nm}'/>
                    </td>
                    <th><spring:message code="clm.page.field.contract.consultation.detail.autorenew" /></th> <!-- 자동갱신여부 -->
                    <td>
                        <c:choose>
                            <c:when test="${contractLom.auto_rnew_yn=='Y'}">
                               Yes
                            </c:when>
                            <c:otherwise>                   
                               No    
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
		        <tr>
                    <th><spring:message code="clm.page.field.contract.basic.thing" /></th>
                    <td colspan="4"><c:out value="${contractLom.cntrt_trgt_nm}" /></td>
                    <th><spring:message code="clm.page.field.contract.basic.detail" /></th>
                    <td><c:out value="${contractLom.cntrt_trgt_det}" escapeXml="false"/></td>
                </tr>
                <tr>
                    <th><spring:message code="clm.page.msg.manage.top30Cus" /><img src="<%=IMAGE %>/icon/ico_search2.gif" class="cp" onClick="javascript:openTop30Customer();" alt="search" style="padding-left:5px" title="Top 30 Customer List"/></th>
                    <td colspan="2"><c:out value="${contractLom.top_30_cus_nm}" /></td>
                    <th><spring:message code="clm.page.msg.manage.relatedPrd" /></th>
                    <td><c:out value="${contractLom.related_products_nm}" escapeXml="false"/></td>
                    <th><spring:message code="clm.page.msg.manage.srcContDraft" /></th>
		            <td><c:out value="${contractLom.cont_draft_nm}" /></td>
                </tr>
		         
		        <tr>
                    <th><spring:message code="clm.page.msg.manage.contPeriod" /></th>
                    <td colspan="2">
                        <c:out value='${contractLom.cntrtperiod_startday}'/> ~ <c:out value='${contractLom.cntrtperiod_endday}'/>
                        <input type="hidden" name="cntrtperiod_startday" value="<c:out value='${contractLom.cntrtperiod_startday}' />"/>
                        <input type="hidden" name="cntrtperiod_endday" value="<c:out value='${contractLom.cntrtperiod_endday}' />"/>    
                    </td>
                    <th><spring:message code='clm.page.field.contract.detail.secret' /></th><!-- 비밀유지기간 -->
                    <td><c:out value="${contractLom.secret_keepperiod}" escapeXml="false"/></td>
                    <th><spring:message code='clm.page.field.contract.detail.paygubun' /></th><!-- 지불/수금 구분 -->
                    <td>
                        <c:out value='${contractLom.payment_gbn_nm}'/>
                    </td>
                </tr>
                <c:if test='${!empty contractLom.payment_cond}'>
                    <tr>
                        <th><spring:message code='clm.page.field.contract.detail.payment' /></th><!-- 지불/수금 조건 -->
                        <td class="tal_lineL" colspan="6"><c:out value="${contractLom.payment_cond}" /></td>
                    </tr>
                </c:if>
                <tr>
                    <th><spring:message code="clm.page.msg.manage.contAmt" /></th>
                    <td colspan="4">
                        <c:out value="${contractLom.cntrt_amt}" />
                        <c:if test="${contractLom.cntrt_untprc_expl!=''}"> &nbsp;&nbsp;&nbsp;<spring:message code="clm.page.msg.manage.unitPrice" /></c:if>
                     </td>
                    <th><spring:message code='clm.page.field.contract.detail.money' /></th><!-- 통화(단위) -->
                    <td><c:out value="${contractLom.crrncy_unit}" /></td>
                </tr>
                <c:if test='${!empty contractLom.cntrt_untprc_expl}'>
	            <tr>
	                <th rowspan="2"><spring:message code='clm.page.field.contract.detail.unitcont' /></th><!-- 단가내역 요약 -->
	                <td colspan="6">
	                    <%=StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String)lomReq.get("cntrt_untprc_expl")))%>
	                </td>
	            </tr>
	            <tr>
	                <td colspan="6" class="tal_lineL">
	                    <iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationUntPrc" name="fileConsultationUntPrc" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>                          
	                </td>
	            </tr>
	            </c:if>
		        <tr>
		            <th><spring:message code='clm.page.field.contract.detail.object' /></th><!-- 추진목적 및 배경 -->
		            <td colspan="6"><c:out value="${contractLom.pshdbkgrnd_purps}" escapeXml="false"/></td>
		        </tr>
		        <tr>
		            <th><spring:message code="clm.page.msg.manage.etcMain" /></th>
		            <td colspan="6"><%=StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String)contractLom.get("etc_main_cont")))%></td>
		        </tr>
	            <!-- 특화정보 -->
	            <c:forEach var="list" items="${considerationSpecialList}">
		            <c:if test="${!empty list.attr_value}">
		            <tr>    
		                <th><c:out value="${list.attr_nm}"/></th>
		                <td colspan="6"><c:out value="${list.attr_value}" escapeXml="false"/></td>
		            </tr>
		            </c:if> 
		        </c:forEach>
		    
		        <c:forEach var="list" items="${consultationSpecialList}">
		            <c:if test="${!empty list.attr_value}">
		            <tr>
		                <th><c:out value="${list.attr_nm}"/></th>
		                <td colspan="6"><c:out value="${list.attr_value}" escapeXml="false"/></td>
		            </tr>
		            </c:if>
		        </c:forEach>
	            <!-- 끝 -->
	            <c:if test='${!empty contractLom.oblgt_lmt}'>
		            <tr>
		                <th><spring:message code="clm.page.field.contract.consultation.detail.obligationlimit"/></th> <!-- 책임한도(LOL) -->
		                <td colspan="6" class="tal_lineL"><c:out value='${contractLom.oblgt_lmt}' escapeXml="false"/></td>
		            </tr>
		            </c:if>
		            <c:if test='${!empty contractLom.spcl_cond}'>
		            <tr>
		                <th><spring:message code="clm.page.msg.manage.etcSpecial" /></th>
		                <td colspan="6"><c:out value='${contractLom.spcl_cond}' escapeXml="false"/></td>
		            </tr>
		            </c:if> 
		            <tr>
		                <th><spring:message code="clm.page.field.contract.detail.loac"/></th>
		                <td colspan="2"><c:out value='${contractLom.loac}'/></td>
		                <th><spring:message code="clm.page.field.contract.detail.loacdetail"/></th>
		                <td colspan="3"><c:out value='${contractLom.loac_etc}' escapeXml="false"/></td>
		            </tr>
		            <tr>
		                <th><spring:message code="clm.page.field.contract.detail.solutionmethod"/></th>
		                <td colspan="2"><c:out value='${contractLom.dspt_resolt_mthd}'/></td>
		                <th><spring:message code="clm.page.field.contract.detail.solutionmethoddetail"/></th>
		                <td colspan="3"><c:out value='${contractLom.dspt_resolt_mthd_det}' escapeXml="false"/></td>
		            </tr>
		        <tr class="attach">
		            <th rowspan="4"><spring:message code="clm.page.field.contract.basic.filename" /></th><!-- 첨부파일 - 계약서 -->
		            <td><span class="blueD"><spring:message code='clm.page.field.contract.basic.filename1' /></span></td>
		            <td colspan="6">
		            <iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationContract" name="fileConsultationContract" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
		            </td>
		        </tr>
		        <tr class="attach">
		            <td class="tal_lineL"><span class="blueD"><spring:message code='clm.page.msg.manage.attachment_br' /></span></td><!-- 첨부파일 - 첨부/별첨 -->
		            <td colspan="6">
		            <iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationEtc" name="fileConsultationEtc" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
		            </td>
		        </tr> 
		        <tr class="attach">
		            <td class="tal_lineL"><span class="blueD"><spring:message code='clm.page.msg.common.etc' /></span></td><!-- 첨부파일 - 첨부/별첨 -->
		            <td colspan="6">
		            <iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationEtc2" name="fileConsultationEtc2" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
		            </td>
		        </tr> 
		        <tr class="attach">
		            <td class="tal_lineL"><span class="blueD"><spring:message code='clm.page.msg.common.outlook' /></span></td><!-- outlook - 기타 -->
		            <td colspan="6">
		            <iframe src="<c:url value='/clm/blank.do' />" id="fileListOut" name="fileListOut" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
		            </td>
		        </tr>
		    </table>
		    
		    <!-- 2014-04-16 Kevin Added. GERP Information -->
            <iframe id="iframeGERP" name="iframeGERP" src="/clm/blank.do" style="border:0; width:100%; height:115px; margin:10px 0 10px 0;" scrolling="no"></iframe>
		    
		    <div class='title_basic3'><spring:message code="clm.page.msg.manage.preApprInf" /></div>
            <table class="table-style01">
                <colgroup>
                    <col width="12%" />
                    <col width="38%" />
                    <col width="12%" />
                    <col width="38%" />
                </colgroup>
                <tr>
                    <th><spring:message code="clm.page.msg.manage.apprDt" /></th>
                    <td class="tL"><c:out value='${contractLom.bfhdcstn_apbtday}'/></td>
                    <th><spring:message code="clm.page.msg.manage.apprType" /></th>
                    <td><c:out value="${contractLom.bfhdcstn_apbt_mthd_nm}"/></td>
                </tr>
                <tr>
                    <th><spring:message code="clm.page.msg.manage.proposer" /></th>
                    <td>
                        <c:if test="${contractLom.bfhdcstn_mtnman_nm != ''}">
                            <c:out value="${contractLom.bfhdcstn_mtnman_nm}" />/ <c:out value='${contractLom.bfhdcstn_mtnman_jikgup_nm}'/>/ <c:out value='${contractLom.bfhdcstn_mtn_dept_nm}'/>
                        </c:if>
                    </td>
                    <th><spring:message code="clm.page.msg.manage.apprPer" /></th>
                    <td>
                        <c:if test="${contractLom.bfhdcstn_apbtman_nm != ''}">
                            <c:out value='${contractLom.bfhdcstn_apbtman_nm}'/>/ <c:out value='${contractLom.bfhdcstn_apbtman_jikgup_nm}'/>/ <c:out value='${contractLom.bfhdcstn_apbt_dept_nm}'/>
                        </c:if>
                    </td>
                </tr>
                
                <tr>
                    <th><spring:message code="clm.page.msg.manage.attachData" /></th>
                    <td colspan="3">
                    <!-- 2014-07-03 Sungwoo replacement height size -->
                        <iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationPre" name="fileConsultationPre" frameborder="0" width="100%" height="100%" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
                    </td>
                </tr>
            </table>
            
            <!-- tnc include JSP -->
			<jsp:include page="/clm/manage/completion.do">
				<jsp:param name="method" value="getTncLink" />
				<jsp:param name="cntrt_id4" value='<%=contractLom.get("cntrt_id") %>'/>
			</jsp:include>
            
            <div class='title_basic3'><spring:message code="clm.page.msg.manage.relContInf" /> <img src="<%=IMAGE %>/btn/btn_up.gif" alt="up" onclick="tit_Toggle(this,'div_rel_contract');" style="cursor:pointer"/></div>
                <div id="div_rel_contract">
                    <table cellspacing="0" cellpadding="0" border="0" class="table-style01">
                        <colgroup>
                            <col width="12%" />
                            <col width="50%" />
                            <col width="10%" />
                            <col/>                  
                        </colgroup>
                        <tbody id="relationTbody">
                        <tr id="trRelationContract">
                            <th><spring:message code="clm.page.msg.manage.relation" /></th>
                            <th><spring:message code="clm.page.msg.manage.relCont" /></th>
                            <th><spring:message code="clm.page.msg.manage.define" /></th>
                            <th><spring:message code="clm.page.msg.manage.relDetail" /></th>
                        </tr>
                        <c:choose>
                        <c:when test ="${relationListSize > 0}">
                            <c:forEach var="list" items="${relationList}" varStatus="i">
                                <tr id="trRelationContractCont">
                                    <td class='tC'><c:out value='${list.rel_type_nm}'/></td>
                                    <td>
                                        <!-- 신성우 변경처리 CNTRT_ID -> CNSDREQ_ID 2014-04-10 POPUP 호출시 REQ_ID 전달안함-->
										<a href="javascript:goDetail('<c:out value='${list.cnsdreq_id}'/>');"><c:out value='${list.relation_cntrt_nm}'/></a>
                                    </td>
                                    <td class='tC'><c:out value='${list.rel_defn}'/></td>
                                    <td class='tC'><c:out value='${list.expl}' escapeXml="false"/></td>
                                </tr>       
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr id="trRelationContractCont"><td colspan="4" align="center">No Data Found</td></tr>
                        </c:otherwise>
                        </c:choose>   
                        </tbody>      
                    </table>
                </div>
            
            <div class="title_basic">
                <h4><spring:message code="clm.page.title.contract.conclusion.detail.title"/> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'tab_contents_sub_conts4');" style="cursor:pointer"/></h4>
            </div>
            <table id="tab_contents_sub_conts4" cellspacing="0" cellpadding="0" border="0" class="table-style01" style="margin-top:0;display:">
	            <colgroup>
	                <col width="12%" />
	                <col width="14%" />
	                <col width="13%" />
	                <col width="11%" />
	                <col width="11%" />
	                <col width="12%" />
	                <col width="14%" />
	                <col />
	            </colgroup>
	            <tr>
	                <th><spring:message code="clm.page.msg.manage.conclConf" /></th><!-- 체결여부 -->
	                <td>
	                    <input name="cntrt_cnclsn_yn_val" type="hidden" value="<c:out value='${contractLom.cntrt_cnclsn_yn}'/>"/>
	                    <c:if test="${contractLom.cntrt_cnclsn_yn == 'Y'}">Yes</c:if>
	                    <c:if test="${contractLom.cntrt_cnclsn_yn != 'Y'}">No</c:if>
	                </td>
	                <th><spring:message code="clm.page.field.contract.conclusion.detail.conclusionplanday"/></th> <!-- 체결예정일 -->
	                <td class="tC"><c:out value='${contractLom.cnclsn_plndday}'/></td>
	                <th><spring:message code="clm.page.field.contract.conclusion.detail.conclusionday"/></th> <!-- 계약체결일 -->
	                <td colspan="3">
	                   <c:out value='${contractLom.cntrt_cnclsnday}'/>
	                   ※ <spring:message code="clm.page.msg.manage.signCompl" />  
	                </td>
	            </tr>
	            <tr>
	                <th><spring:message code="clm.page.field.contract.consultation.detail.methodgbn"/></th> <!-- 직인/서명 구분 -->
	                <td colspan="7">
	                   <c:if test="${contractLom.seal_mthd=='C02101'}"><spring:message code="clm.page.msg.manage.userSign" /></c:if> <!-- 사용인감날인 -->
	                   <c:if test="${contractLom.seal_mthd=='C02103'}"><spring:message code="clm.page.msg.manage.signCorp" /></c:if> <!-- 법인인감날인 -->
	                   <c:if test="${contractLom.seal_mthd=='C02102'}"><spring:message code="clm.page.msg.manage.sign" /></c:if> <!-- 서명 -->
	                </td>
	            </tr>
	            <c:if test="${contractLom.seal_mthd=='C02102'}"> <!-- 서명일 떄 -->
		            <tr>
		                <th><spring:message code="clm.page.field.contract.conclusion.detail.signmannm"/></th> <!-- 서명자(당사) -->
		                <td colspan="5"><c:out value='${contractLom.signman_nm}'/>/<c:out value='${contractLom.signman_jikgup_nm}'/>/<c:out value='${contractLom.sign_dept_nm}'/></td>
		                <th><spring:message code="clm.page.field.contract.conclusion.detail.signday"/></th> <!-- 서명일(당사) -->
		                <td class="tC"><c:out value='${contractLom.signday}'/></td>
		            </tr>
		            <tr>
		                <th><spring:message code="clm.page.field.contract.conclusion.detail.oppntsignmannm"/></th> <!-- 서명자(상대) -->
		                <td><c:out value='${contractLom.oppnt_signman_nm}' escapeXml="false"/></td>   
		                <th><spring:message code="clm.page.field.choosesealperson.jikgupnm"/></th> <!-- 직급 -->
		                <td><c:out value='${contractLom.oppnt_signman_jikgup}' escapeXml="false"/></td>
		                <th><spring:message code="clm.page.field.contract.conclusion.detail.signdeptnm"/></th> <!-- 부서명 -->
		                <td><c:out value='${contractLom.oppnt_signman_dept}' escapeXml="false"/></td>
		                <th><spring:message code="clm.page.field.contract.conclusion.detail.oppntsignday"/></th> <!-- 서명일(상대) -->
		                <td class="tC"><c:out value='${contractLom.oppnt_signday}'/></td>
		            </tr>
	            </c:if>
	            <tr>
	                <th><spring:message code="clm.page.field.contract.consultation.detail.contractrespmannm"/></th><!-- 계약담당자 -->
	                <td colspan="5"><c:out value='${contractLom.cntrt_respman_nm}'/>/<c:out value='${contractLom.cntrt_respman_jikgup_nm}'/>/<c:out value='${contractLom.cntrt_resp_dept_nm}'/></td>
	                <%--	신성우 주석처리 2014-04-01 
	                <th><spring:message code="clm.page.field.contract.consultation.detail.expirenotifyday"/></th> <!-- 만료사전알림일-->
	                <td class="tC"><c:out value='${contractLom.exprt_notiday}'/></td>    --%>
	            </tr>
	            <tr>
	                <th><spring:message code="clm.page.field.contract.conclusion.detail.cpyregnm"/></th><!-- 사본등록자 -->
	                <td colspan="5"><c:out value='${contractLom.cpy_regman_nm}'/>/<c:out value='${contractLom.cpy_regman_jikgup_nm}'/>/<c:out value='${contractLom.cpy_reg_dept_nm}'/></td>
	                <th><spring:message code="clm.page.field.contract.conclusion.detail.cpyregday"/></th><!-- 계약사본등록일 -->
	                <td class="tC"><c:out value='${contractLom.cpy_regday}'/><input type="hidden" name="cpy_reqday" value="<c:out value='${contractLom.cpy_regday}'/>"/></td>
	            </tr>
	            <tr>
	                <th><spring:message code="clm.page.field.contract.conclusion.detail.coclusioncopy"/></th><!-- 체결본사본 -->
	                <td colspan="7">※ <spring:message code="clm.page.msg.manage.announce191" />
	                    <iframe src="<c:url value='/clm/blank.do' />" id="fileContractCopy" name="fileContractCopy" frameborder="0" width="100%" height="50px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
	                    <span id="coclusioncopy_btn"><span class="btn_all_b"><span class="edit"></span><a href="javascript:modifyCoclusionCopy();"><spring:message code="clm.page.msg.manage.conclCopyModReg" /></a></span> ※<spring:message code="clm.page.msg.manage.announce169" /></span>
	                </td>
	            </tr>
	            
	            <tr id="deferOpnnArea" class="lineAdd">
					<th class="borTz02"><spring:message code="clm.page.msg.manage.relPerson" />
                        <c:if test="${command.session_user_id == contractLom.reqman_id or 'RA01' eq command.top_role }">
                            <span class="btn_all_b" onclick="javascript:openChooseClient();"><span class="add"></span><a> <spring:message code="clm.page.msg.manage.add" htmlEscape="true" /></a></span><!-- 추가 -->
                        </c:if>
                    </th><!-- 관련자 RELATION_MAN  relation_man -->
					<td colspan="7">
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
            
			<!-- 이력정보 -->
			<div id="contractHis-list"></div>
			<!-- //이력정보 -->	
						
				<div class="btnwrap tC mt10" id="conclusion_btn2" style="display:none;">
					<span class="btn_all_w"><span class="list"></span><a href="javascript:goModify();"><spring:message code="clm.page.msg.manage.conclCopyReg" /></a></span>
					<c:if test="${contractLom.if_sys_cd != 'MTECH' && contractLom.if_sys_cd != 'CMS' && contractLom.if_sys_cd != 'CPCEX' && contractLom.if_sys_cd != 'myProject'}">
					  <c:if test="${ command.top_role != 'RA01' }">
					      <span class="btn_all_w"><span class="reset"></span><a href="javascript:processCancel();"><spring:message code="clm.page.button.contract.rollback2" /></a></span>
					  </c:if>
					</c:if>
					<span id ="id_defer" class="btn_all_w"><span class="defer"></span><a href="javascript:goDefer('deferRequest');"><spring:message code='clm.page.button.contract.defer' /></a></span>
					<span class="btn_all_w ml_10"><span class="print"></span><a href="javascript:openPrint();"><spring:message code='las.page.button.lawconsult.print' /></a></span>
					<span class="btn_all_w"><span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /></a></span>
					<!--<span class="btn_all_w"><span class="reset"></span><a href="javascript:goDrop();"><spring:message code='clm.page.button.contract.drop' /></a></span>-->
						
				</div>
				<div class="btnwrap tC mt10" id="conclusion_btn_list2" style="dis play:none;">
					
					<c:if test="${lomReq.depth_status == 'C02647' }">
						<!-- Hold release -->						
						<span id ="id_defer" class="btn_all_w"><span class="defer"></span><a href="javascript:goDefer('deferCancelRequest');"><spring:message code='clm.page.button.contract.defernone' /></a></span>
					</c:if>
					<span class="btn_all_w ml_10">
                    <c:choose>
                        <c:when test="${lomReq.depth_status == 'C02642'}">
                            <a href="javascript:openCoverPrint();"><span class="print_title"></span><spring:message code="clm.page.msg.manage.printCover" /></a>
                        </c:when>
                        <c:otherwise>
                            <a href="javascript:openPrint();"><span class="print"></span><spring:message code="clm.page.msg.manage.print" /></a>
                        </c:otherwise>
                    </c:choose>
                    </span>
					<span class="btn_all_w"><span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /></a></span>			
				</div>
			
			</form:form>
			</div>
			</div>
		</div>	
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- footer  -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
	<!-- // footer -->
</body>
</html>