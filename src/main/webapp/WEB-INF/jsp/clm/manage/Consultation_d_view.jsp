<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sec.clm.manage.dto.ConsultationForm" %>
<%@ page import="com.sec.common.util.ClmsDataUtil" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
	ListOrderedMap lomReq 	 	=  (ListOrderedMap)request.getAttribute("lomReq");
	ConsultationForm command = (ConsultationForm)request.getAttribute("command");
	
	
	//연계시스템에서 넘어왔을경우 의뢰상신 버튼을 없앤다
	//CMS, CPCEX
	String infsysnm = command.getSession_infsysnm();
	
	//해당건이 연계건일 경우에도 의뢰상신 버튼을 없앤다
	String sysNm = StringUtil.bvl((String)lomReq.get("sys_nm"), "");
	
%>
<%--
/**
 * 파  일  명 	 : Consultation_d_view.jsp
 * 프로그램명 : 체결품의상세화면(조회)
 * 설      명 	 : 
 * 작  성  자 	 : 심주완
 * 작  성  일       : 2011.00
 */
--%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title"/></title>
<!--계약관리일 경우  -->
<LINK href="<%=CSS%>/clm.css" type="text/css" rel="stylesheet">

<script language="javascript" src="/script/clms/common.js"></script>
<script language="javascript" src="/script/secfw/common/common.js"></script>
<script language="javascript" src="/script/clms/clms_common.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.form.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.flot.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>

<!-- 달력관련추가 -->
<script language="javascript" src="/script/secfw/jquery/calendar/jquery.mousewheel.min.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script>
<!--
$(document).ready(function(){
	var frm = document.frm;
	var cntrt_id = "";
	var status_cd = frm.prgrs_status.value;
	
	if("<c:out value='${command.cntrt_id}'/>" == ""){
		frm.cntrt_id.value="<c:out value='${lomReq.cntrt_id}'/>";
		changeContract("<c:out value='${lomReq.cntrt_id}'/>");
		cntrt_id = "<c:out value='${lomReq.cntrt_id}'/>";
	} else {
		changeContract("<c:out value='${command.cntrt_id}'/>");
		cntrt_id = "<c:out value='${command.cntrt_id}'/>";
	}
	
	$('#contractTab li').removeClass('on');
	$('#' + cntrt_id).addClass('on');
	
	
	$('#contractTab li').bind('click', function(){
		$('#contractTab li').removeClass('on');
		$(this).addClass('on');
		//$('#executionTab li').removeClass('on');
		//$('#executionTab li:eq(0)').addClass('on');
	});
	
	$('#rowAddDel').attr("style","display:none");
	$('#rowAddDel2').attr("style","display:none");	
	$('#rowAddDel3').attr("style","display:none");
	setDetailTabClick();
	
	if(status_cd == "C04215" || status_cd == "C04216") {
		if("<c:out value='${lomReq.reqman_id}'/>"=="<c:out value='${command.session_user_id}'/>") {	//요청자와 현사용자가 같으면
			openChooseStatus();
		}	
	}	
	
});

/**
 *계약탭 클릭
 */
 function changeContract(cntrt_id){
	//var tab_cnt = document.frm.tab_cnt.value;
	
	var frm = document.frm;
	if(frm.progressapprovalYN.value == "Y") {
		alert("<spring:message code='clm.msg.alert.contract.consultation.progressapproval'/>");
		return;
	}
	frm.cntrt_id.value = cntrt_id;
	
	viewHiddenProgress(true) ;
	
	detailConsultationContractMaster();	// 
	listConsultationRelation();
	listConsultationSpecial();
	contractHisList();	//Sungwoo replaced 2014-09-12
	initAttach();
	setProgressApprovalInfo("N");
	subTitleTabMove(1);
	setDetailTabClick();
	setInitControl();
	
	tit_Toggle(this, 'master-table'); 
	
	viewHiddenProgress(false) ;
 }
 
/**
* 계약검토의뢰등록 폼  inner View 
*/
function detailConsultationContractMaster(){
	var options = {
			url: "<c:url value='/clm/manage/consultation.do?method=detailConsultationContractMaster' />",
			type: "post",
			async: false,
			dataType: "html",
			success: function (data) {
				$("#contract-master-info").html("");
				$("#contract-master-info").html(data);
			}
		}
	$("#frm").ajaxSubmit(options);
}



//연관계약정보생성-2011.10.24추가
 function listConsultationRelation() {
 	var options = {
 			url: "<c:url value='/clm/manage/consultation.do?method=listConsultationRelation' />",
 			type: "post",
 			async: false,
 			dataType: "html",
 			success: function (data) {
 				$("#consultation-relationcontract-list").html("");
 				$("#consultation-relationcontract-list").html(data);
 			}
 		}
 	$("#frm").ajaxSubmit(options);
 }
 
//계약특화정보영역생성
function listConsultationSpecial() {
	var options = {
			url: "<c:url value='/clm/manage/consultation.do?method=listConsultationSpecial' />",
			type: "post",
			async: false,
			dataType: "html",
			success: function (data) {
				$("#consultation-specialinfo-list").html("");
				$("#consultation-specialinfo-list").html(data);
			}
		}
	$("#frm").ajaxSubmit(options);
}

//계약상세탭클릭
function setDetailTabClick() {
	$('#tab_contents_sub li').bind('click', function(){
		$('#tab_contents_sub li').removeClass('on');
		$(this).addClass('on');
	});
}
/**
 *계약상세 탭 클릭 
 */
function subTitleTabMove(num){
	var frm = document.frm;
	if(num==1) {
		document.getElementById("tab_contents_sub_conts1").style.display = "block"; 
		document.getElementById("tab_contents_sub_conts1_sub").style.display = "block";
	    document.getElementById("consultation-relationcontract-list").style.display = "none"; //연관계약정보
	    document.getElementById("consultation-specialinfo-list").style.display ="block";
	    
	    document.getElementById("consultation-before-info").style.display = "none"; //사전검토정보
    } else if(num==2) {	
   	  	document.getElementById("tab_contents_sub_conts1").style.display = "none";
   	 	document.getElementById("tab_contents_sub_conts1_sub").style.display = "none";
	    document.getElementById("consultation-relationcontract-list").style.display = "none"; //연관계약정보
	    document.getElementById("consultation-specialinfo-list").style.display ="none";
	    
	    document.getElementById("consultation-before-info").style.display = "block"; //사전검토정보
    } else if(num==3) {
    	document.getElementById("tab_contents_sub_conts1").style.display = "none";
    	document.getElementById("tab_contents_sub_conts1_sub").style.display = "none";
	    document.getElementById("consultation-relationcontract-list").style.display = "none"; //연관계약정보
	    document.getElementById("consultation-specialinfo-list").style.display ="none";
	    
	    document.getElementById("consultation-before-info").style.display = "none"; //사전검토정보
    } else {
    	document.getElementById("tab_contents_sub_conts1").style.display = "none";
    	document.getElementById("tab_contents_sub_conts1_sub").style.display = "none";
	    document.getElementById("consultation-relationcontract-list").style.display = "block"; //연관계약정보
	    document.getElementById("consultation-specialinfo-list").style.display ="none";
	    
	    document.getElementById("consultation-before-info").style.display = "none"; //사전검토정보
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
	
	//var Feture = "fullscreen=no,toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=" + (bScroll ? "yes" : "no") + ",resizable=no,left=" + Left + ",top=" + Top + ",width=" + popupwidth + ",height=" + popupheight;
	var Feture = "toolbar=0, location=0, status=1, menubar=0, scrollbars=" + (bScroll ? "yes" : "no") + ", resizable=yes, top="+Top+", left="+Left+", width="+popupwidth+", height="+popupheight;
	PopUpWindow = window.open(surl, popName , Feture);
	PopUpWindow.focus();
	
}

//모달팝업
function PopUpWindowModalOpen(surl, popupwidth, popupheight, bScroll, obj){
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

    //Future = "fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=" + (bScroll ? "yes" : "no") + ",resizable=no,left=" + Left + ",top=" + Top + ",width=" + popupwidth + ",height=" + popupheight;
	var sFeatures = "dialogWidth:" + popupwidth +"px;dialogHeight:" + popupheight+"px;scroll:" + (bScroll ? "on":"off")+ ";status:0ff;resizable:no;";
    window.showModalDialog(surl, obj, sFeatures);

}

//첨부파일초기화
function initAttach(){
	//계약-계약서파일(회신계약서파일(종합회신))
	frm.target          		= "fileConsultationContract";
    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
    frm.method.value    		= "forwardClmsFilePage";
    frm.file_midclsfcn.value 	= "F01202";
    frm.file_smlclsfcn.value	= "F0120201";
    frm.ref_key.value   		= frm.cntrt_id.value + "@" + frm.cnsdreq_id.value;
    frm.fileInfoName.value 		= "fileInfos";
    frm.fileFrameName.value 	= "fileConsultationContract";
    getClmsFilePageCheck('frm');   
	
  //계약-별첨
    frm.target          		= "fileConsultationEtc";
    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
    frm.method.value   		 	= "forwardClmsFilePage";
    frm.file_midclsfcn.value 	= "F01202";
    frm.file_smlclsfcn.value 	= "F0120208";
    frm.ref_key.value   		= frm.cntrt_id.value + "@" + frm.cnsdreq_id.value;
    frm.fileInfoName.value 		= "fileInfos5";
    frm.fileFrameName.value 	= "fileConsultationEtc";
    getClmsFilePageCheck('frm');
    
    //계약-기타_체결본
    frm.target          		= "fileConsultationEtc2";
    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
    frm.method.value   		 	= "forwardClmsFilePage";
    frm.file_midclsfcn.value 	= "F01202";
    frm.file_smlclsfcn.value 	= "F0120212";
    frm.ref_key.value   		= frm.cntrt_id.value + "@" + frm.cnsdreq_id.value;
    frm.fileInfoName.value 		= "fileInfos2";
    frm.fileFrameName.value 	= "fileConsultationEtc2";
    getClmsFilePageCheck('frm');	  
    
    //사전검토정보-첨부
    frm.target          		= "fileConsultationPre";
    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
    frm.method.value    		= "forwardClmsFilePage";
    frm.file_midclsfcn.value 	= "F01201";
    frm.file_smlclsfcn.value 	= "F0120101";
    frm.ref_key.value   		= frm.cntrt_id.value;
    frm.fileInfoName.value 		= "fileInfos3";
    frm.fileFrameName.value 	= "fileConsultationPre";
    frm.submit();
    
    //단가내역-첨부
    <%--if("<c:out value='${lomReq.cntrt_untprc_expl}' />" != "") {--%>
    if(<%=StringUtil.isNull((String)lomReq.get("cntrt_untprc_expl"))%> == false) {	
	    frm.target          		= "fileConsultationUntPrc";
	    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    		= "forwardClmsFilePage";
	    frm.file_midclsfcn.value 	= "F01202";
	    frm.file_smlclsfcn.value 	= "F0120211";
	    frm.ref_key.value   		= frm.cntrt_id.value;
	    frm.fileInfoName.value 		= "fileInfos4";
	    frm.fileFrameName.value 	= "fileConsultationUntPrc";
	    getClmsFilePageCheck('frm');
    }
}

//컨트롤설정
function setInitControl() {
	var frm = document.frm;
	var status_cd = frm.prgrs_status.value;
 
	if(status_cd != "C04211" && status_cd != "C04207" && status_cd != "C04212" ) {	//작성중이 아니면
		$('div[id^=consultation_btn]').attr("style", "display:none");
		$('div[id^=consultation_btn_list]').attr("style", "display:block");
		$('#rowAddDel').attr("style","display:none");
		$('#rowAddDel2').attr("style","display:none");	
		$('#rowAddDel3').attr("style","display:none");
			
	} else {
		if("<c:out value='${command.session_user_id}'/>" == frm.reqman_id.value) {
			<%
				//연계시스템에서 넘어온 경우 버튼을 막는다.
				if("CPCEX".equals(infsysnm) || "CMS".equals(infsysnm) || "CPCEX".equals(sysNm) || "CMS".equals(sysNm)){
			%>
			$('div[id^=consultation_btn]').attr("style", "display:none");
			$('div[id^=consultation_btn_list]').attr("style", "display:block");
			<%
				}else{
			%>
			$('div[id^=consultation_btn]').attr("style", "display:block");
			$('div[id^=consultation_btn_list]').attr("style", "display:none");
			<%
				}
			%>
		} else {
			$('div[id^=consultation_btn]').attr("style", "display:none");
			$('div[id^=consultation_btn_list]').attr("style", "display:block");
			$('#rowAddDel').attr("style","display:none");
			$('#rowAddDel2').attr("style","display:none");	
			$('#rowAddDel3').attr("style","display:none");
		}	
	}	
}

//신성우 주석처리 2014-03-31
//Val값만큼의 이전날짜를 가져온다.
/*
function getBeforeDay(val) {
	var frm = document.frm;
	var endday = frm.cntrtperiod_endday.value;
	val = val - (val*2);
	frm.exprt_notiday.value=shiftTime(endday.replace(/-/g, ""), 0,0,val);
	
}*/

/**
 * 주어진 Time 과 y년 m월 d일 h시 차이나는 Time을 리턴
 * ex) var time = form.time.value; //'20000101000'
 *     alert(shiftTime(time,0,0,-100,0));
 *     => 2000/01/01 00:00 으로부터 100일 전 Time
 */
function shiftTime(time,y,m,d) { //moveTime(time,y,m,d,h)
    var date = toTimeObject(time);

    date.setFullYear(date.getFullYear() + y); //y년을 더함
    date.setMonth(date.getMonth() + m);       //m월을 더함
    date.setDate(date.getDate() + d);         //d일을 더함

    return toTimeString(date);
}

 /**
  * Time 스트링을 자바스크립트 Date 객체로 변환
  * parameter time: Time 형식의 String
  */
 function toTimeObject(time) { //parseTime(time)
     var year  = time.substr(0,4);
     var month = time.substr(4,2) - 1; // 1월=0,12월=11
     var day   = time.substr(6,2);
  
     return new Date(year,month,day);
 }

 /**
  * 자바스크립트 Date 객체를 Time 스트링으로 변환
  * parameter date: JavaScript Date Object
  */
 function toTimeString(date) { //formatTime(date)
     var year  = date.getFullYear();
     var month = date.getMonth() + 1; // 1월=0,12월=11이므로 1 더함
     var day   = date.getDate();
    
     if (("" + month).length == 1) { month = "0" + month; }
     if (("" + day).length   == 1) { day   = "0" + day;   }
    
     return ("" + year + "-" + month + "-" + day);
 }

 function setProgressApprovalInfo(val){
		var frm = document.frm
		frm.progressapprovalYN.value = val;
	}

function setApprovalYN(yn){
	var frm = document.frm;
	setProgressApprovalInfo("N");
	//alert(yn);
	if(yn == "Y") {
		//alert(frm.approval_cntrt_info.value);
    	frm.action="<c:url value='/clm/manage/consultation.do' />";
    	frm.target="_self";
    	frm.method.value="modifyConsultationStatus";
    	frm.submit();
	}
}

function setSealMethod(val) {
	var frm = document.frm;
	if(val=="C02101") {
		$('#seal-tr').attr("style", "display:block");
		$('#sign-tr').attr("style", "display:none");
		$('#seal_ffmtman_id').attr("required", "");
		$('#seal_ffmtman_nm').attr("required", "");
		$('#seal_ffmtman_search_nm').attr("required", "");
		$('#sign_plndman_id').removeAttr("required");
		$('#sign_plndman_nm').removeAttr("required");
		$('#sign_plndman_search_nm').removeAttr("required");
	} else {
		$('#seal-tr').attr("style", "display:none");
		$('#sign-tr').attr("style", "display:block");
		$('#seal_ffmtman_id').removeAttr("required");
		$('#seal_ffmtman_nm').removeAttr("required");
		$('#seal_ffmtman_search_nm').removeAttr("required");
		$('#sign_plndman_id').attr("required", "");
		$('#sign_plndman_nm').attr("required", "");
		$('#sign_plndman_search_nm').attr("required", "");
	}
}
/*
 * 재상신/재검토의뢰선택팝업
 */
function openChooseStatus(){
	var frm = document.frm;
	PopUpWindowOpen('', "200", "100", false, "popUpChooseStatus");
    frm.action="<c:url value='/clm/manage/consultation.do'/>";
    frm.method.value="forwardChooseStatusPopup";
    frm.target = "popUpChooseStatus";
    frm.submit();  
}
/*
 * 
 */
function setChooseStatusResult(retValue) {
	var frm = document.frm;
	//alert(retValue);
	if(retValue != "") {
		frm.submit_status.value=retValue;
		frm.action="<c:url value='/clm/manage/consultation.do'/>";
    	frm.method.value="modifyReworkStatus";
    	if(retValue=="request") {
    		frm.target = "_top";
    	} else {
    		frm.target = "_self";
    		frm.page_gbn.value="modify";
    	}
    	frm.submit();
	}	
}

function modifyStatus(arg) {
	var frm = document.frm;
	if(arg=="dropRequest") {
		frm.action="<c:url value='/clm/manage/consultation.do' />";
		frm.target="_self";
		frm.method.value="listManageConsultation";
	} else {
		frm.action="<c:url value='/clm/manage/consultation.do' />";
		frm.target="_self";
		frm.method.value="modifyConsultationStatusDefer";
	}
	frm.submit();
}

function setOpnnConsideration(returnInfo) {
	if(returnInfo.Cd=="Y") {
		modifyStatus(returnInfo.strMethod);
	} else {
		alert(returnInfo.Msg);	
	}
}
<%--버튼액션부분시작--%>
<%--Drop,보류--%>
function actionConsideration(arg){
	var msg ="";
		//defer -보류 , drop -Drop ,delete - 삭제
		if("dropRequest" == arg){
			msg = "<spring:message code='clm.msg.confirm.contract.consultation.droprequest'/>";  			
		}else if("deferRequest" == arg){
			msg = "<spring:message code='clm.msg.confirm.contract.consultation.deferrequest'/>";  			
		}
	
		if("dropRequest" == arg || "deferRequest" == arg){
			if(confirm(msg)){
				opnnConsideration(arg);
			}
		}
}	

<%--품의작성--%>
function goModify() {
	viewHiddenProgress(true) ;
	frm.action 			= "<c:url value='/clm/manage/consultation.do' />";
	frm.target 			= "_self";
	frm.page_gbn.value 	= "modify";
	frm.method.value	= "detailConsultation";
	frm.submit();
}
<%--목록--%>
function goList() {
	viewHiddenProgress(true) ;
	//mycontract에서 접근했을 시
	if(frm.ismycontract.value == 'Y'){
		frm.target = "_self";		 
		frm.action = "<c:url value='/clm/manage/myContract.do' />";
		frm.method.value = "listMyContract";
		frm.submit();
	}
	else{
		frm.action			= "<c:url value='/clm/manage/consultation.do' />";
		frm.target			= "_self";
		frm.method.value	= "listManageConsultation";
		frm.submit();
	}
}


 /**
  * 의뢰 보류 / drop /계약 drop 
  * frm.submit_status.value = arg;
  */
  function opnnConsideration(arg){
 	
  	var frm 				= document.frm;
  	frm.submit_status.value = arg;
  	
     PopUpWindowOpen('', "530", "470", true, "popUpDrop");
     frm.action = "<c:url value='/clm/manage/consideration.do?prcsDepth=C02502' />";
     frm.method.value="opnnConsideration";
     frm.target = "popUpDrop";
     frm.submit();
  }
 
  
 /**
  * 품의서보기 
  * 
  */
// function goRdView() {
//	 var frm 				= document.frm;
//	 PopUpWindowOpen('', "1000", "600", true, "popUpRd");
//	 frm.action = "<c:url value='/clm/manage/consultation.do' />";
//	 frm.method.value="forwardConsultationApprovalPrint";
//	 frm.target = "popUpRd";
//	 frm.submit();
// }
 
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
  
  /**
	 * 회신상태로 프로세스 복원
	 */
	function processCancel(){
		if(!confirm("<spring:message code="clm.page.msg.manage.announce185" />")) return;
		
		frm.method.value = "cancelConsultation";	
		frm.target = "_self";
		frm.tempsave_flag.value='button';
		frm.action = "<c:url value='/clm/manage/consultation.do' />";
	    frm.submit();
	    
	    viewHiddenProgress(true) ;
	}
 
//-->	
</script>
</head>
<body>
<!-- container -->
<div id="wrap">
	<div id="container">
			<form:form name="frm" id="frm" method="post" autocomplete="off">
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
			<input type="hidden" name="srch_if_sys_cd" value="<c:out value='${command.srch_if_sys_cd}' escapeXml='false'/>" />						<!-- 연계시스템 -->
			<input type="hidden" name="srch_cnclsnpurps_bigclsfcn" value="<c:out value='${command.srch_cnclsnpurps_bigclsfcn}' escapeXml='false'/>" />	<!-- 체결목적-->
			<input type="hidden" name="ismycontract" id="ismycontract" value="<c:out value='${command.ismycontract}'/>"/>
			<input type="hidden" name="srch_step" value="<c:out value='${command.srch_step}' escapeXml='false'/>" />						<!-- 상태 -->
			
			<input type="hidden" name="method" id="method" value=""/>
			<input type="hidden" name="srch_user_cntnt_type" value=""/>
			<input type="hidden" name="srch_user_cntnt" value=""/>
			<input type="hidden" name="srch_user_cntnt_target" value=""/>
			<input type="hidden" name="worktype" value=""/>
			<input type="hidden" name="menu_id" id="menu_id" value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="curPage" id="curPage" value="<c:out value='${command.curPage}'/>"/>
			<input type="hidden" name="progressapprovalYN" value="N"/>
			<input type="hidden" name="approval_cntrt_info" value=""/>
			<input type="hidden" name="work_flag" value=""/>
			<input type="hidden" name="page_gbn"  value=""/>	
			<input type="hidden" name="submit_status"  value=""/>
			<input type="hidden" name="tempsave_flag" id="tempsave_flag" />
			
		    <!-- 계약추가 증가데이타 -->	    
		    <input type="hidden" name="cnsdreq_id" id="cnsdreq_id" value="<c:out value='${lomReq.cnsdreq_id}'/>" />
		    <input type="hidden" name="approval_key" id="approval_key" value="<c:out value='${lomReq.cnsdreq_id}'/>" />
		    <input type="hidden" name="prgrs_status" id="prgrs_status" value="<c:out value='${lomReq.prgrs_status}'/>" />
		    <input type="hidden" name="cntrt_id" id="cntrt_id"  value="<c:out value='${lomReq.cntrt_id}'/>" />
		    <input type="hidden" name="cntrt_untprc_expl" id="cntrt_untprc_expl"  value="<c:out value='${lomReq.cntrt_untprc_expl}'/>" />
		    <input type="hidden" name="reqman_id" id="reqman_id"  value="<c:out value='${lomReq.reqman_id}'/>" />
		    
		    <!-- 첨부파일정보 시작 -->
			<input type="hidden" name="fileInfos"       value="" /> 		<!-- 계약서 파일 정보 -->
			<input type="hidden" name="fileInfos2"      value="" /> 		<!-- 기타 파일 정보 -->
			<!-- <input type="hidden" name="fileInfos3"      value="" />-->	<!-- 사전검토 파일 정보 -->
			<input type="hidden" name="fileInfos4"      value="" /> 		<!-- 단가 파일 정보 -->
			<input type="hidden" name="fileInfos5"      value="" /> 		<!-- 별첨 파일 정보 -->
			<input type="hidden" name="file_bigclsfcn"  value="F012" /> 	<!-- 대분류 -->
			<input type="hidden" name="file_midclsfcn"  value="" /> 		<!-- 중분류 -->
			<input type="hidden" name="file_smlclsfcn"  value="" /> 		<!-- 소분류 -->
			<input type="hidden" name="ref_key"     	value="" /> 		<!-- 키값 -->
			<input type="hidden" name="view_gbn"    	value="download" />	<!-- 첨부파일 화면 -->
			<input type="hidden" name="fileInfoName"    value="" /> 		<!-- 저장될 파일 정보 -->
			<input type="hidden" name="fileFrameName"   value="" /> 		<!-- 소분류 -->
			
			<input type="hidden" name="mis_id" />
		<!-- location -->
		<!-- <div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0" alt="Home">계약관리 > 계약체결 ><strong> 쳬결품의</strong></div>-->
	    <div class="location">
	    	<img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/>
	    </div>
	    <!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.title.contract.consultation.detail.title"/></h3>
		</div>
	    <!-- //location -->
		<!-- content -->
		<div id="content">
			<div class="content-step t_titBtn">
				<ol>
					<li><img src="<%=IMAGE %>/common/step01.gif"	 alt=<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" /> title="<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" />" /></li>
					<li class='step_on'>
						<img src="<%=IMAGE %>/common/step02_on.gif"  alt="<spring:message code="clm.page.msg.manage.step02" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step02" htmlEscape="true" />" />
						<img src="<%=IMAGE %>/common/step_arr01.gif" alt="Next" title="Next" />
						<div class='step_link'><a href="javascript:open_window('win', '../../step02<%=langCd.equals("ko")?"":"_"+langCd %>.html', 0, 0, 696, 588, 0, 0, 0, 0, 0)" ><img src="<%=IMAGE %>/common/step_q.gif" alt="<spring:message code="clm.page.msg.manage.procStep"/>" title="<spring:message code="clm.page.msg.manage.procStep"/>" /></a></div>
					</li>
					<li><img src="<%=IMAGE %>/common/step03.gif"     alt="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" /></li>
					<li><img src="<%=IMAGE %>/common/step04.gif"     alt="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" /></li>
					<li><img src="<%=IMAGE %>/common/step05.gif"     alt="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" /></li>
				</ol>
			</div>
			<!-- //tab01 -->
			
			<!-- title -->
			<div class="t_titBtn">
			  	<div class="title_basic">
					<h4><spring:message code="clm.page.title.contract.consultation.detail.reqtitle"/> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'req-table');"/></h4>
				</div>
				<div class="btn_wrap_t02" id="consultation_btn" style="display:none">
					<!-- <span class="btn_all_w"><span class="reset"></span><a href="javascript:actionConsideration('dropRequest');"><spring:message code='clm.page.button.contract.drop' /></a></span>
					<span class="btn_all_w" id="sp_defer1"><span class="delete"></span><a href="javascript:actionConsideration('deferRequest');"><spring:message code='clm.page.button.contract.defer' /></a></span> -->
					<span class="btn_all_w"><span class="reset"></span><a href="javascript:processCancel();"><spring:message code="clm.page.button.contract.rollback" /></a></span>
					<span class="btn_all_w"><span class="preview"></span><a href="javascript:goModify();"><spring:message code='clm.page.button.contract.modifyconsultation' /></a></span>
					<!-- <span class="btn_all_w"><span class="preview"></span><a href="javascript:goRdView();"><spring:message code='clm.page.button.contract.rdview' /></a></span> -->
					<span class="btn_all_w"><span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /></a></span>			
				</div>
				<div class="btn_wrap_t02" id="consultation_btn_list" style="display:none">
					<!-- <span class="btn_all_w"><span class="preview"></span><a href="javascript:goRdView();"><spring:message code='clm.page.button.contract.rdview' /></a></span> -->
					<span class="btn_all_w"><span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /></a></span>			
					
				</div>
			</div>
		  	<table cellspacing="0" cellpadding="0" class="table-style01" id="req-table">
				<colgroup>
					<col width="15%" />
					<col width="34%" />
					<col width="12%" />
					<col width="15%" />
					<col width="12%" />
					<col width="12%" />
				</colgroup>
				<tr>
					<th><spring:message code="clm.page.field.manageRequest.reqTitle"/></th>
				  <td colspan="7"><c:out value='${lomReq.req_title}' default="" /></td>
				</tr>
				<tr class="lineAdd">
					<th><spring:message code="clm.page.field.manageRequest.demndmanNm"/></th>
					<td>
						<c:out value="${lomReq.reqman_nm}" />/<c:out value="${lomReq.reqman_jikgup_nm}" />/<c:out value="${lomReq.req_dept_nm}" />						
					</td>
					<th><spring:message code="clm.page.field.manageRequest.demndDt"/></th>
					<td><c:out value="${lomReq.req_dt}" /></td>
					<th><spring:message code="clm.page.field.contract.request.returndt"/></th><!-- 회신요청일 RE_DEMNDDAY -->
					<td><c:out value="${lomReq.re_dt}" /></td>
				</tr>
				
				<c:if test='${reqAuthInfo!=""}'>
				<tr class="lineAdd">
					<th><spring:message code="clm.page.field.myapproval.etcinfo"/></th><!--  -->
					<td colspan="5"><c:out value="${reqAuthInfo}" /></td>
				</tr>
				</c:if>
			</table>
			<!-- title -->
		  	<div class="title_basic03">
				<h4><spring:message code="clm.page.title.contract.consultation.detail.contracttitle"/> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'contract-master');"/></h4>
			</div>
			<!-- //title -->
			<!-- tab01 -->
			<ul class="tab_basic02" id="contractTab">
				<c:forEach var="list" items="${lomMaster}">
				<!-- <li class=<c:if test="${list.rn=='1'}">on</c:if>><a href="#contract-view" onClick="javascript:changeContract('<c:out value='${list.cntrt_id}'/>');">계약<c:out value='${list.rn}'/></a></li>-->
				<li id="<c:out value='${list.cntrt_id}'/>"><a href="#contract_view" onClick="javascript:changeContract('<c:out value='${list.cntrt_id}'/>');"><spring:message code="clm.page.msg.common.contract" /><c:out value='${list.rn}'/></a></li>
				</c:forEach>		
			</ul>
			
			<!-- //tab01 -->
			<!-- 두줄라인 추가 --><div class="tal_topLine"></div><!-- //두줄라인 추가 -->
			<!-- toptable-->
			<div id="contract-master-info"></div>
			<!-- 이행정보영역Start -->
			<div class="title_basic">
				<h5 class="ntitle05"><spring:message code="clm.page.tab.title.contractexec"/> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'consultation-execinfo-list');"/></h5>
			</div>
			<div id="consultation-execinfo-list">
			</div>
			<!-- 이행정보영역end -->
			<!-- 이력정보start -->
			<div id="contractHis-list"></div>
			<div class="t_titBtn">
				<div class="btn_wrap_c" id="consultation_btn" style="display:none;">
					<!-- <span class="btn_all_w"><span class="reset"></span><a href="javascript:actionConsideration('dropRequest');"><spring:message code='clm.page.button.contract.drop' /></a></span>
					<span class="btn_all_w" id="sp_defer2"><span class="delete"></span><a href="javascript:actionConsideration('deferRequest');"><spring:message code='clm.page.button.contract.defer' /></a></span> -->
					<span class="btn_all_w"><span class="reset"></span><a href="javascript:processCancel();"><spring:message code="clm.page.button.contract.rollback" /></a></span>
					<span class="btn_all_w"><span class="preview"></span><a href="javascript:goModify();"><spring:message code='clm.page.button.contract.modifyconsultation' /></a></span>
					<!-- <span class="btn_all_w"><span class="preview"></span><a href="javascript:goRdView();"><spring:message code='clm.page.button.contract.rdview' /></a></span> -->
					<span class="btn_all_w"><span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /></a></span>			
				</div>
				<div class="btn_wrap_c" id="consultation_btn_list" style="display:none;">
					<!-- <span class="btn_all_w"><span class="preview"></span><a href="javascript:goRdView();"><spring:message code='clm.page.button.contract.rdview' /></a></span> -->
					<span class="btn_all_w"><span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /></a></span>			
				</div>
				
			</div>
			<!-- 이력정보end -->
			</form:form>
		</div>
		<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- // footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
</body>
</html>