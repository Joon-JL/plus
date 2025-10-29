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
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%
	ListOrderedMap lomReq 	 	=  (ListOrderedMap)request.getAttribute("lomReq");
%>
<%--
/**
 * 파  일  명 	 : Consultation_d.jsp
 * 프로그램명 : 체결품의상세화면
 * 설      명 	 : 
 * 작  성  자 	 : 심주완
 * 작  성  일       : 2011.00
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title"/></title>
<!--계약관리일 경우  -->
<link href="<%=CSS%>/clm.css" type="text/css" rel="stylesheet" />

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

var tx = true;

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
		if(tx){
			$('#contractTab li').removeClass('on');
			$(this).addClass('on');
		}	
	});
	
	setDetailTabClick();
	
	//사전승인 승인일
	initCal("bfhdcstn_apbtday");
});

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
		};
	$("#frm").ajaxSubmit(options);
}

/**
 *계약탭 클릭
 */
 function changeContract(cntrt_id){
	var frm = document.frm;
	if(frm.progressapprovalYN.value == "Y") {
		alert("<spring:message code='clm.msg.alert.contract.consultation.progressapproval'/>");
		return;
	}
	frm.cntrt_id.value = cntrt_id;
	viewHiddenProgress(true) ;
	detailConsultationContractMaster();	//	
	subTitleTabMove(1);
	listConsultationRelation();
	listConsultationSpecial();
	
	
	$('#executionTbody tr').each(function(index){
		var originVal = "";
		var replaceVal = "";
		
		originVal = $(this).contents().find('input[name=exec_amt_arr]').val();
		if (originVal != undefined){
			replaceVal = comTrim(originVal.replace(/,/g, ""));
	   	}
	});
	
	$('#executionTbody2 tr').each(function(index){
		var originVal = "";
		var replaceVal = "";
		
		originVal = $(this).contents().find('input[name=exec_amt_arr]').val();
		if (originVal != undefined){
			replaceVal = comTrim(originVal.replace(/,/g, ""));
	   	}	
	});
	
	contractHisList();	//Sungwoo replaced 2014-09-12
	setDetailTabClick();
	if(frm.prgrs_status.value == "C04211" || frm.prgrs_status.value == "C04207" || frm.prgrs_status.value == "C04212"){
		//initExecCalendar();
		//initCal("exprt_notiday");		//신성우 주석처리 2014-03-31
		initCal("cnclsn_plndday");
		initCal("cnclsn_plndday1");
		initCal("cntrtperiod_startday");
		initCal("cntrtperiod_endday");
	}
	initAttach();
	setProgressApprovalInfo("N");
	setInitControl();
	
	tit_Toggle(this, 'master-table'); 
	
	//prompt("", $('#contract-master-info').html());
	
	//연관계약 관계유형		
	getCodeSelectByUpCd("frm", "rel_type", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=A&combo_grp_cd=C032&combo_type=S&combo_del_yn=N&combo_select=");
	
	viewHiddenProgress(false) ;
 }
 
 /**
  *계약탭 클릭시에 기존것 임시저장 후에 이동한다.
  */
  function changeContractAfterTempSave(cntrt_id){
	var frm = document.frm;
	
	tempSaveByTab(cntrt_id);	
 }
 
 function callbackF(cntrt_id){
	var frm = document.frm;
	
	//탭이동시 첨부파일 저장 후 첨부파일 초기화
	frm.fileInfos.value = ""; //계약서 파일 정보
	frm.fileInfos2.value = ""; //기타 파일 정보
	frm.fileInfos4.value = ""; //단가 파일 정보
	frm.fileInfos5.value = ""; //별첨 파일 정보 
	frm.file_bigclsfcn.value = "F012"; //대분류
	frm.file_midclsfcn.value = ""; //중분류
	frm.file_smlclsfcn.value = ""; //소분류
	frm.ref_key.value = ""; //키값
	frm.view_gbn.value = "download"; //첨부파일 화면
	frm.fileInfoName.value = ""; //저장될 파일 정보
	frm.fileFrameName.value = ""; //소분류
	
	
	
	if(frm.progressapprovalYN.value == "Y") {
		alert("<spring:message code='clm.msg.alert.contract.consultation.progressapproval'/>");
		return;
	}
	frm.cntrt_id.value = cntrt_id;
	//viewHiddenProgress(true) ;
	detailConsultationContractMaster();	//	
 	subTitleTabMove(1);
 	listConsultationRelation();
 	listConsultationSpecial();
	 	
 	$('#executionTbody tr').each(function(index){
 		var originVal = "";
 		var replaceVal = "";
 		
 		originVal = $(this).contents().find('input[name=exec_amt_arr]').val();
 		if (originVal != undefined){
 			replaceVal = comTrim(originVal.replace(/,/g, ""));
 	   	}
 	});
 	
 	$('#executionTbody2 tr').each(function(index){
 		var originVal = "";
 		var replaceVal = "";
 		
 		originVal = $(this).contents().find('input[name=exec_amt_arr]').val();
 		if (originVal != undefined){
 			replaceVal = comTrim(originVal.replace(/,/g, ""));
 	   	}
 	});
 	
 	contractHisList();	//Sungwoo replaced 2014-09-12
 	setDetailTabClick();
 	if(frm.prgrs_status.value == "C04211" || frm.prgrs_status.value == "C04207" || frm.prgrs_status.value == "C04212"){
 		//initExecCalendar();
 		//initCal("exprt_notiday");		//신성우 주석처리 2014-03-31
 		initCal("cnclsn_plndday");
 		initCal("cnclsn_plndday1");
 		initCal("cntrtperiod_startday");
 		initCal("cntrtperiod_endday");
 	}
 	initAttach();
 	setProgressApprovalInfo("N");
 	setInitControl();
 	
 	tit_Toggle(this, 'master-table'); 
 	
 	//연관계약 관계유형		
 	getCodeSelectByUpCd("frm", "rel_type", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=A&combo_grp_cd=C032&combo_type=S&combo_del_yn=N&combo_select=");
 	
 	viewHiddenProgress(false) ;
 	
  }
 
/*
연관계약정보생성-2011.10.24추가
*/
 function listConsultationRelation() {
	var frm = document.frm;
	frm.submit_status.value="edit";
	var options = {
 			url: "<c:url value='/clm/manage/consultation.do?method=listConsultationRelation' />",
 			type: "post",
 			async: false,
 			dataType: "html",
 			success: function (data) {
 				$("#consultation-relationcontract-list").html("");
 				$("#consultation-relationcontract-list").html(data);
 			}
 		};
 	$("#frm").ajaxSubmit(options);
 }
 
/* 
계약특화정보영역생성
*/
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
		};
	$("#frm").ajaxSubmit(options);
}

/*
계약상세탭클릭
*/
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
 
/*
 *	임직원조회팝업 
 */
function searchEmployee(flag) {
	var frm 		= document.frm;
	var srchValue 	= "";
	var obj 		= new Object();
	if(flag=="sign"){
		obj = frm.sign_plndman_search_nm;
	}else if(flag=="contract") {
		obj = frm.cntrt_respman_search_nm;
	}else if(flag == "mtn"){ //사전승인발의자
		obj = frm.bfhdcstn_mtnman_nm;
	}else if(flag == "apbt"){ //사전승인 승인자
		obj = frm.bfhdcstn_apbtman_nm;
	}
	
	srchValue = comTrim(obj.value);
    frm.target = "PopUpEmployee";
    frm.srch_user_cntnt_type.value = "userName"; //이름으로 검색  - 보통 이름으로 검색함
    frm.srch_user_cntnt_target.value = flag;
    frm.action = "<c:url value='/secfw/esbOrg.do' />";
    frm.method.value = "listEsbEmployee";
    frm.srch_user_cntnt.value = srchValue;
    if (srchValue == "" || srchValue.length < 4) { //최소 2자리 이상 입력받게 한다.
        alert('<spring:message code='secfw.msg.error.nameMinByte' />');
        obj.focus();
    } else {
    	PopUpWindowOpen('', 800, 450, true,"PopUpEmployee");
    	frm.submit();
    }    	
} 

/**
* 임직원 조회정보 결과 setting
*/
function setUserInfos(obj) {
	var frm = document.frm;
	var srch_user_target = frm.srch_user_cntnt_target.value;
    var userInfo = obj;   
    if(srch_user_target=="sign") {
    	frm.sign_plndman_id.value 		= obj.epid;
    	frm.sign_plndman_nm.value 		= obj.cn;
    	//frm.sign_plndman_search_nm.value= obj.cn;
    	frm.sign_plndman_search_nm.value= '';
    	frm.sign_plnd_dept_nm.value 	= obj.department;
    	frm.sign_plndman_jikgup_nm.value = obj.title;
    	
    	$('#plndman').html('');
    	$('#plndman').append('&nbsp;&nbsp;' + obj.cn+'/'+obj.title+'/'+obj.department);
    	
    } else if(srch_user_target=="contract"){
    	frm.cntrt_respman_id.value 			= obj.epid;
    	frm.cntrt_respman_nm.value 			= obj.cn;
    	frm.cntrt_respman_search_nm.value	= '';
    	frm.cntrt_respman_jikgup_nm.value 	= obj.title;
    	frm.cntrt_resp_dept_nm.value 		= obj.department;
    	frm.cntrt_resp_dept.value			= obj.departmentnumber;
    	
    	$('#respman').html('');
    	$('#respman').append('&nbsp;&nbsp;'+ obj.cn+'/'+obj.title+'/'+obj.department);
    }else if(srch_user_target=="mtn"){ //사전승인 발의자
    	frm.bfhdcstn_mtnman_id.value 			= userInfo.epid;		//사전승인발의자 id
	    frm.bfhdcstn_mtnman_nm.value 			= userInfo.cn;; 		//발의자 이름
	    frm.bfhdcstn_mtnman_jikgup_nm.value 	= userInfo.title;  		//직급
	    frm.bfhdcstn_mtn_dept_nm.value			= userInfo.department;	//부서
	    frm.bfhdcstn_mtnman_email.value		= userInfo.mail;		//이메일
	     
	    $('#bfhdcstn_mtnman_jikgup_nm_span').html(userInfo.title);
	    $('#bfhdcstn_mtn_dept_nm_span').html(userInfo.department);
	    $('#bfhdcstn_mtn_email_span').html(userInfo.mail);
    }else if(srch_user_target=="apbt"){ //사전승인 승인자
    	frm.bfhdcstn_apbtman_id.value          = userInfo.epid;		//사전승인승인자정id
   	 	frm.bfhdcstn_apbtman_nm.value          = userInfo.cn;; 		//발의자 이름
   	 	frm.bfhdcstn_apbtman_jikgup_nm.value   = userInfo.title;  		//직급
   	 	frm.bfhdcstn_apbt_dept_nm.value        = userInfo.department;	//부서
   	 
   	 	$('#bfhdcstn_apbtman_jikgup_nm_span').html(userInfo.title);
	    $('#bfhdcstn_apbt_dept_nm_span').html(userInfo.department);
    }
}

/*
 * 날인자 검색팝업 
 */
function searchSealPerson() {
	var frm 		= document.frm;
	PopUpWindowOpen('', 800, 450, true, "PopUpSealPerson");
    frm.target = "PopUpSealPerson";
    frm.action = "<c:url value='/clm/manage/chooseSealPerson.do' />";
    frm.method.value = "forwardChooseSealPersonPopup";
    frm.submit();   
}
//날인자 셋팅
function setSealPerson(obj) {
	$('#ffmtman').html('');
	$('#ffmtman').append('&nbsp;&nbsp;' + obj);
}

/*
 * 결재창팝업(사용안함)
 */
function openApproval(){
	var frm = document.frm;
	
	PopUpWindowOpen('', "1024", "768", true, "popUpChooseContract");
    frm.action="<c:url value='/clm/manage/consultation.do' />";
    frm.method.value="makeApprovalHtml";
    frm.target = "PopUpApproval";
    frm.submit();  
}

/*
 * //체결품의시 계약선택(현재사용안함)
 */
function openChooseContract(){
	var frm = document.frm;
	if(!validateForm(frm)) return;
	if(!confirm("<spring:message code='clm.msg.confirm.consultation.approval'/>")) return;
	if(frm.progressapprovalYN.value == "Y") {
		alert("<spring:message code='clm.msg.alert.contract.consultation.progressapproval'/>");
		return;
	}
    PopUpWindowOpen('', "800", "300", true, "popUpChooseContract");
    setProgressApprovalInfo("Y");
    
		fileConsultationEtc.UploadFile(function(uploadCount){ //첨부/별첨
			if(uploadCount == -1){
				initAttach(); //첨부파일창 Reload
			    alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
			    return;
			}
			
			fileConsultationEtc2.UploadFile(function(uploadCount){ //기타
				if(uploadCount == -1){
					initAttach(); //첨부파일창 Reload
				    alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
				    return;
				}
			
				fileConsultationPre.UploadFile(function(uploadCount){ //사전검토정보 첨부파일
					if(uploadCount == -1){
						initAttach(); //첨부파일창 Reload
					    alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
					    return;
					}
				
					if(<%=StringUtil.isNull((String)lomReq.get("cntrt_untprc_expl"))%> == false) {					
						fileConsultationUntPrc.UploadFile(function(uploadCount){ //단가내역
							if(uploadCount == -1){
							   	initAttach(); //첨부파일창 Reload
							    alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
							    return;
							}
							frm.action 					 = "<c:url value='/clm/manage/consultation.do' />";
						    frm.method.value			 = "modifyConsultation";
						    frm.target 					 = "popUpChooseContract";
						    frm.work_flag.value			 = "APPROVAL";
						    frm.worktype.value			 = "CONSULTATION";
						    frm.submit();
						});					
					}else{
						frm.action 					 = "<c:url value='/clm/manage/consultation.do' />";
					    frm.method.value			 = "modifyConsultation";
					    frm.target 					 = "popUpChooseContract";
					    frm.work_flag.value			 = "APPROVAL";
					    frm.worktype.value			 = "CONSULTATION";
					    frm.submit();
					}			
				});
			});
		});
	
}

/*
 * 팝업오픈-모달리스
 */
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

/*
 * 모달팝업
 */
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
	var sFeatures = "dialogWidth:" + popupwidth +"px;dialogHeight:" + popupheight+"px;scroll:" + (bScroll ? "on":"off")+ ";status:0ff;resizable:no;";
    window.showModalDialog(surl, obj, sFeatures);

}

/*
 * 첨부파일초기화
 */
function initAttach(){
	//계약-계약서파일(회신계약서파일(종합회신))
	frm.target          		= "fileConsultationContract";
    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
    frm.method.value    		= "forwardClmsFilePage";
    frm.file_midclsfcn.value 	= "F01202";
    frm.file_smlclsfcn.value	= "F0120201";
    frm.ref_key.value   		= frm.cntrt_id.value + "@" + frm.cnsdreq_id.value;
    frm.fileInfoName.value 		= "fileInfos";
    frm.view_gbn.value			= "download";
    frm.fileFrameName.value 	= "fileConsultationContract";
    //getClmsFilePageCheck('frm');   
    frm.submit();
	
  	//계약-별첨
    frm.target          		= "fileConsultationEtc";
    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
    frm.method.value   		 	= "forwardClmsFilePage";
    frm.file_midclsfcn.value 	= "F01202";
    frm.file_smlclsfcn.value 	= "F0120208";
    frm.ref_key.value   		= frm.cntrt_id.value + "@" + frm.cnsdreq_id.value;
    frm.fileInfoName.value 		= "fileInfos5";
    frm.view_gbn.value			= "download";
    frm.fileFrameName.value 	= "fileConsultationEtc";
    frm.submit();
    //getClmsFilePageCheck('frm');
    
    //계약-기타_체결본
    frm.target          		= "fileConsultationEtc2";
    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
    frm.method.value   		 	= "forwardClmsFilePage";
    frm.file_midclsfcn.value 	= "F01202";
    frm.file_smlclsfcn.value 	= "F0120212";
    frm.ref_key.value   		= frm.cntrt_id.value + "@" + frm.cnsdreq_id.value;
    frm.fileInfoName.value 		= "fileInfos2";
    // 2012.04.04 첨부파일 수정하도록 수정 modified by hanjihoon
    frm.view_gbn.value			= "modify";
    frm.fileFrameName.value 	= "fileConsultationEtc2";
    frm.submit();
    //getClmsFilePageCheck('frm');   
    
    
    //사전검토정보-첨부
    frm.target          		= "fileConsultationPre";
    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
    frm.method.value    		= "forwardClmsFilePage";
    frm.file_midclsfcn.value 	= "F01201";
    frm.file_smlclsfcn.value 	= "F0120101";
    frm.ref_key.value   		= frm.cntrt_id.value;
    frm.fileInfoName.value 		= "fileInfos3";
    frm.view_gbn.value			= "modify";
    frm.fileFrameName.value 	= "fileConsultationPre";
    frm.submit();
    
    //단가내역-첨부
    <%--if("<c:out value='${lomReq.cntrt_untprc_expl}' />" != "") {--%>
	    frm.target          		= "fileConsultationUntPrc";
	    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    		= "forwardClmsFilePage";
	    frm.file_midclsfcn.value 	= "F01202";
	    frm.file_smlclsfcn.value 	= "F0120211";
	    frm.ref_key.value   		= frm.cntrt_id.value;
	    frm.fileInfoName.value 		= "fileInfos4";
	    frm.view_gbn.value			= "modify";
	    frm.fileFrameName.value 	= "fileConsultationUntPrc";
	    //getClmsFilePageCheck('frm');
	    frm.submit();
    <%--}--%>
}

/*
 * 체결품의에 선택된 계약정보
 */
function setApprovalContractInfo(val) {
	var frm = document.frm;
	frm.approval_cntrt_info.value = val;
}

/**
 * 결재진행여부설정
 */
function setProgressApprovalInfo(val){
	var frm = document.frm;
	frm.progressapprovalYN.value = val;
}

/**
 * 결재상신 성공여부
 */
function setApprovalYN(yn){
	var frm = document.frm;
	setProgressApprovalInfo("N");
	
	if(yn == "Y") {
		//alert(frm.approval_cntrt_info.value);
    	frm.action="<c:url value='/clm/manage/consultation.do' />";
    	frm.target="_self";
    	frm.method.value="modifyConsultationStatus";
    	frm.page_gbn.value="download";
    	frm.submit();
	}
}

/**
 * 화면컨트롤초기화 
 */
function setInitControl() {
	var frm = document.frm;
	var status_cd = frm.prgrs_status.value;
	if(status_cd != "C04211" && status_cd != "C04207" && status_cd != "C04212") {	//작성중이 아니면
		$('div[id^=consultation_btn]').attr("style", "display:none");
		$('div[id^=consultation_btn_list]').attr("style", "display:block");
		$('#rowAddDel').attr("style","display:none");
		$('#rowAddDel2').attr("style","display:none");	
		$('#rowAddDel3').attr("style","display:none");
	} else {
		if("<c:out value='${command.session_user_id}'/>" == frm.reqman_id.value) {
			$('div[id^=consultation_btn]').attr("style", "display:block");
			$('div[id^=consultation_btn_list]').attr("style", "display:none");
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

/**
 * 직인서명구분클릭시  이벤트
 */
function setSealMethod(val) {
	var frm = document.frm;
	if(val=="C02101") { //직인(사용인감날인)
		$('#seal-tr').attr("style", "display:block");
		$('#seal-tr1').attr("style", "display:block");
		$('#sign-tr').attr("style", "display:none");
		$('#sign-tr1').attr("style", "display:none");
		
		//$('#seal_ffmtman_id').attr("required", "");
		//$('#seal_ffmtman_nm').attr("required", "");
		//$('#seal_ffmtman_search_nm').attr("required", "");
		
		//$('#sign_plndman_id').removeAttr("required");
		//$('#sign_plndman_nm').removeAttr("required");
		//$('#sign_plndman_search_nm').removeAttr("required");
		
		$('#seal_ffmtman_id').val('');
		$('#seal_ffmtman_nm').val('');
		$('#seal_ffmt_dept_cd').val('');
		$('#seal_ffmt_dept_nm').val('');
		$('#seal_ffmtman_jikgup_nm').val('');
		$('#seal_ffmtman_search_nm').val('');
		$('#ffmtman').html('');
		
		$('input[name=seal_ffmtman_search_nm]').removeAttr("disabled");
		$('#seal_ffmtman_search_img').attr("style","display:").prev().css("border-right","");
		
	}else if(val=="C02103") { //직인(법인인감날인)
		$('#seal-tr').attr("style", "display:block");
		$('#seal-tr1').attr("style", "display:block");
		$('#sign-tr').attr("style", "display:none");
		$('#sign-tr1').attr("style", "display:none");
		
		//$('#seal_ffmtman_id').attr("required", "");
		//$('#seal_ffmtman_nm').attr("required", "");
		//$('#seal_ffmtman_search_nm').attr("required", "");
		
		//$('#sign_plndman_id').removeAttr("required");
		//$('#sign_plndman_nm').removeAttr("required");
		//$('#sign_plndman_search_nm').removeAttr("required");
		
		$('#seal_ffmtman_id').val('');
		$('#seal_ffmtman_nm').val('');
		$('#seal_ffmt_dept_cd').val('');
		$('#seal_ffmt_dept_nm').val('');
		$('#seal_ffmtman_jikgup_nm').val('');
		$('#seal_ffmtman_search_nm').val('');
		$('#ffmtman').html('');
		
		$('input[name=seal_ffmtman_search_nm]').attr("disabled","disabled");	
		$('#seal_ffmtman_search_img').attr("style","display:none").prev().css("border-right","#7f9db9 1px solid");
		
		//법인인감날인 일 경우 특정사람으로 강제 셋팅한다.
		$('#seal_ffmtman_id').val('M060801010146C104961');
		$('#seal_ffmtman_nm').val('김혜영');
		$('#seal_ffmt_dept_cd').val('C10S5231');
		$('#seal_ffmt_dept_nm').val('총무그룹(서초)');
		$('#seal_ffmtman_jikgup_nm').val('G3(사원)');
		$('#ffmtman').html('김혜영/G3(사원)/총무그룹(서초)');
	
	}else { //서명
		$('#seal-tr').attr("style", "display:none");
		$('#seal-tr1').attr("style", "display:none");
		$('#sign-tr').attr("style", "display:block");
		$('#sign-tr1').attr("style", "display:block");
		
		//$('#seal_ffmtman_id').removeAttr("required");
		//$('#seal_ffmtman_nm').removeAttr("required");
		//$('#seal_ffmtman_search_nm').removeAttr("required");
		
		//$('#sign_plndman_id').attr("required", "");
		//$('#sign_plndman_nm').attr("required", "");
		//$('#sign_plndman_search_nm').attr("required", "");
		
		$('#sign_plndman_id').val('');
		$('#sign_plndman_nm').val('');
		$('#sign_plndman_jikgup_nm').val('');
		$('#sign_plnd_dept_nm').val('');
		$('#sign_plndman_search_nm').val('');
		$('#plndman').html('');
	}
}

/*
 * 관련자팝업
 */
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
    var arrReturn 			= returnValue.split("!@#$");
    
    var frm 				= document.frm;
    var innerHtml 			= "";
    var arrSeqno			= "";
	var arrTrgrtman_id 		= "";
	var arrTrgrtman_nm  	= "";
	var arrTrgrtman_jikgup 	= "";
	var arrTrgrtman_dept    = "";
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
        if(i > 0) {
        	arrSeqno			= arrSeqno + "," + arrInfo[0];
        	arrTrgrtman_id 		= arrTrgrtman_id + "," + arrInfo[1];
        	arrTrgrtman_nm  	= arrTrgrtman_nm + "," + arrInfo[2];
        	arrTrgrtman_jikgup 	= arrTrgrtman_jikgup + "," + arrInfo[3];
        	arrTrgrtman_dept    = arrTrgrtman_dept + "," + arrInfo[4];
        } else {
        	arrSeqno			= arrInfo[0];
        	arrTrgrtman_id 		= arrInfo[1];
        	arrTrgrtman_nm  	= arrInfo[2];
        	arrTrgrtman_jikgup 	= arrInfo[3];
        	arrTrgrtman_dept    = arrInfo[4];
        }
    	innerHtml += arrInfo[2] +"/"+arrInfo[3] + "/" + arrInfo[4] ;
    		
    	$('#id_trgtman_nm').html(innerHtml);
    }
    frm.authreq_client.value = returnValue;
    frm.chose_client.value = arrSeqno + "!@#$" + arrTrgrtman_id + "!@#$" + arrTrgrtman_nm + "!@#$" + arrTrgrtman_jikgup + "!@#$" + arrTrgrtman_dept;
 }

<%--버튼액션시작--%>
/**
 * 임시저장
 */
function tempSave() {
	var frm = document.frm;
	//frm.work_flag.value="";
	frm.work_flag.value="LIST";
	if(frm.progressapprovalYN.value=="Y") {
		alert("<spring:message code='clm.msg.alert.contract.consultation.progressapproval'/>");
		return;
	}
	
	if(!confirm("<spring:message code="clm.page.msg.manage.announce137" />")) return;
	
	var exe_cnt = 0;
	if($('#payment_gbn > option:selected').val() =="C02001") {	//지불/수금
		
		if($('#cntrt_amt').val() == ''){
			
			if(!$("[name=cntrt_untprc]").is(":checked")){
				alert("<spring:message code="clm.page.msg.manage.announce038" />");	
				$('#cntrt_amt').focus();
				return;
			}
		}
		
		exe_cnt = $('#executionTbody tr').length + $('#executionTbody2 tr').length + $('#executionTbody3 tr').length;
		if(exe_cnt==0){
			alert("<spring:message code="clm.page.msg.manage.announce152" />");
			return;
		}
	} else if($('#payment_gbn > option:selected').val() =="C02002") {	//지불
		
		if($('#cntrt_amt').val() == ''){
			if(!$("[name=cntrt_untprc]").is(":checked")){
				alert("<spring:message code="clm.page.msg.manage.announce038" />");
				$('#cntrt_amt').focus();
				return;
			}
		}
	} else if($('#payment_gbn > option:selected').val() =="C02003") {	//수금
		
		if($('#cntrt_amt').val() == ''){
			if(!$("[name=cntrt_untprc]").is(":checked")){
				alert("<spring:message code="clm.page.msg.manage.announce038" />");	
				$('#cntrt_amt').focus();
				return;
			}
		}
	}
	
	frm.body_mime.value = frm.wec.MIMEValue;
	
	var seal_mthd = $('input[name*=seal_mthd]:checked').val();
	if(seal_mthd == "C02101" || seal_mthd == "C02103"){ //직인
		if($('#seal_ffmtman_id').val() == ''){
			alert("<spring:message code="clm.page.msg.manage.announce072" />");
			return;
		}		
	}else{ //서명일 때
		if($('#sign_plndman_id').val() == ''){
			alert("<spring:message code="clm.page.msg.manage.announce105" />");
			return;
		}
	}
	
	if($('#cntrt_respman_id').val() == ''){
		alert("<spring:message code="clm.page.msg.manage.announce043" />");
		return;
	}
	
	if(!validateForm(frm)) return;
	
		fileConsultationEtc.UploadFile(function(uploadCount){ //첨부/별첨
			if(uploadCount == -1){
				initAttach(); //첨부파일창 Reload
			    alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
			    return;
			}
			
			fileConsultationEtc2.UploadFile(function(uploadCount){ //기타
				if(uploadCount == -1){
					initAttach(); //첨부파일창 Reload
				    alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
				    return;
				}
			
				fileConsultationPre.UploadFile(function(uploadCount){ //사전검토정보 첨부파일
					if(uploadCount == -1){
					   	initAttach(); //첨부파일창 Reload
					    alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
					    return;
					}
				
					if(<%=StringUtil.isNull((String)lomReq.get("cntrt_untprc_expl"))%> == false) {	
						fileConsultationUntPrc.UploadFile(function(uploadCount){ //단가내역
							if(uploadCount == -1){
							   	initAttach(); //첨부파일창 Reload
							    alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
							    return;
							}
							frm.method.value = "modifyConsultation";	
							frm.target = "_self";		
							frm.tempsave_flag.value='button';
							frm.action = "<c:url value='/clm/manage/consultation.do' />";
						    frm.submit();
						    
						    viewHiddenProgress(true) ;
						});					
					}else{
						frm.method.value = "modifyConsultation";	
						frm.target = "_self";
						frm.tempsave_flag.value='button';
						frm.action = "<c:url value='/clm/manage/consultation.do' />";
					    frm.submit();
					    
					    viewHiddenProgress(true) ;
					}					
				});
			});
		});
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
	
	/**
	 * 임시저장
	 */
	function tempSaveByTab(cntrt_id) {
		
		var frm = document.frm;
		frm.work_flag.value="";
		if(frm.progressapprovalYN.value=="Y") {
			alert("<spring:message code='clm.msg.alert.contract.consultation.progressapproval'/>");
			
			$('#contractTab li').removeClass('on');
			$('#' + frm.cntrt_id.value).addClass('on');
			tx = false;
			
			return;
		}
		
		var exe_cnt = 0;
		if($('#payment_gbn > option:selected').val() =="C02001") {	//지불/수금
			
			if($('#cntrt_amt').val() == ''){
				if(!$("[name=cntrt_untprc]").is(":checked")){
					alert("<spring:message code="clm.page.msg.manage.announce038" />");	
					$('#cntrt_amt').focus();
					return;
				}
			}
			
			exe_cnt = $('#executionTbody tr').length + $('#executionTbody2 tr').length + $('#executionTbody3 tr').length;
			if(exe_cnt==0){
				alert("<spring:message code="clm.page.msg.manage.announce152" />");
				
				$('#contractTab li').removeClass('on');
				$('#' + frm.cntrt_id.value).addClass('on');
				tx = false;
				
				return;
			}
		} else if($('#payment_gbn > option:selected').val() =="C02002") {	//지불
			
			if($('#cntrt_amt').val() == ''){
				if(!$("[name=cntrt_untprc]").is(":checked")){
					alert("<spring:message code="clm.page.msg.manage.announce038" />");	
					$('#cntrt_amt').focus();
					return;
				}
			}
			/*
			exe_cnt = $('#executionTbody tr').length;
			if(exe_cnt==0) {
				alert("<spring:message code="clm.page.msg.manage.announce153" />");
				
				$('#contractTab li').removeClass('on');
				$('#' + frm.cntrt_id.value).addClass('on');
				tx = false;
				
				return;
			}*/
		} else if($('#payment_gbn > option:selected').val() =="C02003") {	//수금
			
			if($('#cntrt_amt').val() == ''){
				if(!$("[name=cntrt_untprc]").is(":checked")){
					alert("<spring:message code="clm.page.msg.manage.announce038" />");	
					$('#cntrt_amt').focus();
					return;
				}
			}
			/*
			exe_cnt = $('#executionTbody2 tr').length;
			if(exe_cnt==0) {
				alert("<spring:message code="clm.page.msg.manage.announce151" />");
				
				$('#contractTab li').removeClass('on');
				$('#' + frm.cntrt_id.value).addClass('on');
				tx = false;
				
				return;
			}	*/
		}
		
		frm.body_mime.value = frm.wec.MIMEValue;
		
		var seal_mthd = $('input[name*=seal_mthd]:checked').val();
		if(seal_mthd == "C02101" || seal_mthd == "C02103"){ //직인
			if($('#seal_ffmtman_id').val() == ''){
				alert("<spring:message code="clm.page.msg.manage.announce072" />");
				
				$('#contractTab li').removeClass('on');
				$('#' + frm.cntrt_id.value).addClass('on');
				tx = false;
				
				return;
			}		
		}else{ //서명일 때
			if($('#sign_plndman_id').val() == ''){
				alert("<spring:message code="clm.page.msg.manage.announce105" />");
				
				$('#contractTab li').removeClass('on');
				$('#' + frm.cntrt_id.value).addClass('on');
				tx = false;
				
				return;
			}
		}
		
		if($('#cntrt_respman_id').val() == ''){
			alert("<spring:message code="clm.page.msg.manage.announce043" />");
			
			$('#contractTab li').removeClass('on');
			$('#' + frm.cntrt_id.value).addClass('on');
			tx = false;
			
			return;
		}
		
		if(!validateForm(frm)){ 
			$('#contractTab li').removeClass('on');
			$('#' + frm.cntrt_id.value).addClass('on');
			tx = false;
			
			return;
		}
		
			fileConsultationEtc.UploadFile(function(uploadCount){ //첨부/별첨
				if(uploadCount == -1){
					initAttach(); //첨부파일창 Reload
				    alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
					
				    $('#contractTab li').removeClass('on');
					$('#' + frm.cntrt_id.value).addClass('on');
					tx = false;
					
				    return;
				}
				
				fileConsultationEtc2.UploadFile(function(uploadCount){ //기타
					if(uploadCount == -1){
						initAttach(); //첨부파일창 Reload
					    alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
					    tx = false;
					    
					    return;
					}
				
					fileConsultationPre.UploadFile(function(uploadCount){ //사전검토정보 첨부파일
						if(uploadCount == -1){
							initAttach(); //첨부파일창 Reload
						    alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
						    tx = false;
						    
						    return;
						}
					
						if(<%=StringUtil.isNull((String)lomReq.get("cntrt_untprc_expl"))%> == false) {	
							fileConsultationUntPrc.UploadFile(function(uploadCount){ //단가내역
								if(uploadCount == -1){
								   	initAttach(); //첨부파일창 Reload
								    alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
								   	
								    $('#contractTab li').removeClass('on');
									$('#' + frm.cntrt_id.value).addClass('on');
									tx = false;
									
								    return;
								}
							
								frm.tempsave_flag.value='tab';
								
							    tx = true;
							    var options = {
							    	url:"<c:url value='/clm/manage/consultation.do?method=modifyConsultationByTab' />",
							    	type:"post",
							    	dataType:"json",
							    	success:function(responseText, statusText){
							    		if(responseText.tabSaveYN == undefined){
							    			alert("<spring:message code="clm.page.msg.manage.announce157" />");						    			
							    		}else{
							    			callbackF(cntrt_id);
							    		}
							    	}
							    };
							    $("#frm").ajaxSubmit(options);
							    viewHiddenProgress(true) ;
							});					
						}else{
							frm.tempsave_flag.value='tab';

						    tx = true;
						    var options = {
							    url:"<c:url value='/clm/manage/consultation.do?method=modifyConsultationByTab' />",
								type:"post",
								dataType:"json",
								success:function(responseText, statusText){
									if(responseText.tabSaveYN == undefined){
										alert("<spring:message code="clm.page.msg.manage.announce157" />");
									}else{
										callbackF(cntrt_id);
									}
								}
							};
							$("#frm").ajaxSubmit(options);	
						    viewHiddenProgress(true) ;
						}	
					});
				});
			});		
	}

/**
 *체결품의버튼 
 */
function checkApprovalValidation() {
	var frm = document.frm;
	
	if(frm.progressapprovalYN.value == "Y") {
		alert("<spring:message code='clm.msg.alert.contract.consultation.progressapproval'/>");
		return;
	}
	var exe_cnt = 0;
	if($('#payment_gbn > option:selected').val() == "C02001") {	//지불/수금
		if($('#cntrt_amt').val() == ''){
			if(!$("[name=cntrt_untprc]").is(":checked")){
				alert("<spring:message code="clm.page.msg.manage.announce038" />");	
				$('#cntrt_amt').focus();
				return;
			}
		}
		
		exe_cnt = $('#executionTbody tr').length + $('#executionTbody2 tr').length + $('#executionTbody3 tr').length;
		if(exe_cnt==0){
			alert("<spring:message code="clm.page.msg.manage.announce152" />");
			return;
		}
	} else if($('#payment_gbn > option:selected').val() == "C02002") {	//지불
		
		if($('#cntrt_amt').val() == ''){
			if(!$("[name=cntrt_untprc]").is(":checked")){
				alert("<spring:message code="clm.page.msg.manage.announce038" />");	
				$('#cntrt_amt').focus();
				return;
			}
		}
		
	} else if($('#payment_gbn > option:selected').val() == "C02003") {	//수금
		
		if($('#cntrt_amt').val() == ''){
			if(!$("[name=cntrt_untprc]").is(":checked")){
				alert("<spring:message code="clm.page.msg.manage.announce038" />");	
				$('#cntrt_amt').focus();
				return;
			}
		}
	}else if($('#payment_gbn > option:selected').val() =="C02004"){ //해당없음일때 
		
		exe_cnt = $('#executionTbody3 tr').length;
		if(exe_cnt==0) {
			alert("<spring:message code="clm.page.msg.manage.announce150" />");
			
			$('#contractTab li').removeClass('on');
			$('#' + frm.cntrt_id.value).addClass('on');
			tx = false;
			
			return;
		}
	}
	
	
	frm.body_mime.value = frm.wec.MIMEValue;
	
	
	var seal_mthd = $('input[name*=seal_mthd]:checked').val();
	if(seal_mthd == "C02101" || seal_mthd == "C02103"){ //직인
		if($('#seal_ffmtman_id').val() == ''){
			alert("<spring:message code="clm.page.msg.manage.announce072" />");
			return;
		}		
	}else{ //서명일 때
		if($('#sign_plndman_id').val() == ''){
			alert("<spring:message code="clm.page.msg.manage.announce105" />");
			return;
		}
	}
	
	if($('#cntrt_respman_id').val() == ''){
		alert("<spring:message code="clm.page.msg.manage.announce043" />");
		return;
	}
	
	if(!validateForm(frm)) return;
	viewHiddenProgress(true);
	frm.method.value = "listConsultationApprovalEsbValidation";
	var returnString = "";
	var options = {
		url: "<c:url value='/clm/manage/consultation.do' />",
		type: "post",
		dataType: "json",
		success: function(returnJson,returnMessage) {
			if(returnJson.result == "Y") {
				viewHiddenProgress(false);
				openChooseContract();
			} else {
				viewHiddenProgress(false);
				alert(returnJson.message);				
			}
		}
	};
	
	$("#frm").ajaxSubmit(options);
}

/**
 * 미리보기
 */
function openPreview() {
	alert("<spring:message code="clm.page.msg.manage.announce138" />");
	var frm = document.frm;
	
    PopUpWindowOpen('', "1024", "768", true, "popUpPreview");
    
    frm.action 					 = "<c:url value='/clm/manage/consultation.do' />";
    frm.method.value			 = "forwardPreviewPop";
    frm.target 					 = "popUpPreview";
    frm.submit();
}

/**
 * 목록
 */
function goList() {
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

// 금액에 숫자만 입력하게 합니다. 숫자값이 아니면 그냥 빈칸으로 만들어 버리죠.
function olnyNum(obj){
	var str = obj.value;
	str = new String(str);
	var Re = /[^0-9]/g;
	str = str.replace(Re,'');
	
	// 금액 100,000 형태로 변환 추가 (2011/10/15)
	obj.value = Comma(str);
}

//글자수 제한
function f_chk_byte(aro_name,ari_max) {   
    var ls_str     = aro_name.value;
    var li_str_len = ls_str.length;
    var li_max      = ari_max;
    var i           = 0;
    var li_byte     = 0;
    var li_len      = 0;
    var ls_one_char = "";
    var ls_str2     = "";

    for(i=0; i< li_str_len; i++) {
        ls_one_char = ls_str.charAt(i);
        if (escape(ls_one_char).length > 4) 
            li_byte += 2;
        else 
            li_byte++;
        
        if (li_byte <= li_max) li_len = i + 1;
    }

    if(li_byte > li_max) {
        alert("<spring:message code="clm.page.msg.manage.announce139" />");
        ls_str2 = ls_str.substr(0, li_len);
        aro_name.value = ls_str2;
    }
    aro_name.focus();   
}

/**
 * 연관계약 정보 actionRelContract
 */
 function actionRelationContract(arg,id){
	  
	  var frm = document.frm;
	  frm.submit_status.value=arg;
	  
	  if("delete" == arg){
		  frm.parent_cntrt_id.value = id;
	  }else{
		  if(!$("#parent_cntrt_id").val()){
			  alert("<spring:message code='clm.page.msg.manage.announce118' />");
			  $("#rel_type").focus();
			  return;
		  }else if(!$("#rel_type").val()){
			  alert("<spring:message code="clm.page.msg.manage.announce063" />");
			  $("#rel_type").focus();
			  return;
		  }
	  }
	  
	  var options = {
		url: "<c:url value='/clm/manage/consideration.do?method=actionRelationContract' />",
		type: "post",
		dataType: "json",			
		success: function(responseText, statusText) {
			
			if(responseText.returnVal == 1){
				alert("<spring:message code="clm.page.msg.manage.announce156" />");
				
				//입력폼 초기화 
				$("#rel_type").val("");
				$("#parent_cntrt_id").val("");
				$("#parent_cntrt_name").val("");
				$("#rel_defn").html("<option>--선택--</option>");
				$("#expl").val("");
				listRelationContract();
			}else if(responseText.returnVal == 2){
				alert("<spring:message code="clm.page.msg.manage.announce162" />");
			}else{
				alert("<spring:message code="clm.page.msg.manage.announce157" />");
			}
		}
	};
	$("#frm").ajaxSubmit(options);
 }
 
 
 /**
  * 연관계약 타입 선택
  */
  function reltypeChange(){
	  var type = $("#rel_type").val();  
	  if("C03201" == type){
			$("#rel_defn").html("<option value=NDA>NDA</option><option value=MOU/LOI>MOU/LOI</option>");
		}else if("C03202" == type){
			$("#rel_defn").html("<option value=Master>Master</option><option value=Sub>Sub</option>");
		}else if("C03203" == type){
			$("#rel_defn").html("<option value=<spring:message code='clm.page.msg.manage.preChg' />><spring:message code='clm.page.msg.manage.preChg' /></option><option value=<spring:message code='clm.page.msg.manage.befCalncel' />><spring:message code='clm.page.msg.manage.befCalncel' /></option>");	
		}else{
			$("#rel_defn").html("<option value=><spring:message code='clm.page.msg.manage.noSelect' /></option>");
		}
  }
 
  	/**
	  * 연관계약 정보 reload
	  */
	  function listRelationContract(){
  		  var frm = document.frm;
		  frm.submit_status.value="edit";
		  var options = {
				url: "<c:url value='/clm/manage/consideration.do?method=listRelationContract' />",
				type: "post",
				async: false,
				dataType: "html",
				success: function (data) { 
					if(data.indexOf('<html>') == -1){  
	  					$("#trRelationContract1").nextAll().remove();
	  					$("#trRelationContractCont").nextAll().remove();
	  					$("#trRelationContract1").after(data);
					}
				}
			};
	  		$("#frm").ajaxSubmit(options);
	  }
  	
		/**
		* 연관계약 계약 목록 팝업
		*/
		function popupListContract(arg){
			var frm = document.frm;
			arg=$("#rel_type").val();
			var parent_cntrt_id = "";
			var parent_cntrt_nm = "";
			var rel_type1 = "";
			//C03204
			var result = new retRC(parent_cntrt_id ,parent_cntrt_nm ,rel_type1);
			
			if(frm.cntrt_nm.value != ""){
				// 계약체결 품의작성에서 담당자가 작성시, jsp, java의 form객체 casting 에러로 인해 팝업경로를 수정 modified by hanjihoon
				var v_except_cntrt_id = frm.cntrt_id.value;
				PopUpWindowOpen("/clm/manage/myContractPop.do?method=listMyContract&status_mode=rel&except_cntrt_id="+v_except_cntrt_id , 840, 600, true);
			} else {
			 alert("  연관계약을 입력하기 위해서는 먼저 의뢰하려는\n 계약 정보의 계약명이 먼저 입력이 되어야 합니다.\n 계약 유형과 거래선을 먼저 선택하여 주세요.");
			 // frm.parent_cntrt_nm.focus();
			}
		}
		
		/**
		* 연관계약 팝업 리턴 처리
		*/	  
		function setContract(id,name,biz,sArg){
			var fm = document.frm;			
			
			//리턴값 변수에 저장
			var cntrt_id = id;
			var cntrt_name = name;
			var cntrt_biz_clsfcn = biz;
			var srch_arg = sArg;
			
			$("#parent_cntrt_id").val(cntrt_id);
			$("#rel_type").val(srch_arg);
			$("#parent_cntrt_name").val(cntrt_name);
		}  	
		
		  /**
		  * Obj init
		  */
		  function retRC(parent_cntrt_id ,parent_cntrt_nm ,rel_type1){
			  this.parent_cntrt_id = parent_cntrt_id;
			  this.parent_cntrt_nm = parent_cntrt_nm;
			  this.rel_type1 = rel_type1;
		  }
 		
	//이행관리 지불계획 완료일 제거기능
	function clearDate(val){
		document.getElementById('exec_cmpltday_arr'+val).value = '';
	}
		  
	//이행관리 수불계획 완료일 제거기능
	function clearDate2(val){
		document.getElementById('exec_cmpltday_arr2'+val).value = '';
	}
		  
	//이행관리 기타이행계획 완료일 제거기능
	function clearDate3(val){
		document.getElementById('exec_cmpltday_arr3'+val).value = '';
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
	  
	  function openArbitraryPop(){
		  var frm = document.frm;
		  
		  PopUpWindowOpen("/las/board/notice.do?method=detailDecideArbitrarilyRe&menu_id=20130319155746339_0000000377", 840, 600, true);

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
		<input type="hidden" name="page_gbn"  value="<c:out value='${command.page_gbn}'/>"/>
	    <!-- 계약추가 증가데이타 -->	    
	    <input type="hidden" name="cnsdreq_id" id="cnsdreq_id" value="<c:out value='${lomReq.cnsdreq_id}'/>" />
	    <input type="hidden" name="approval_key" id="approval_key" value="<c:out value='${lomReq.cnsdreq_id}'/>" />
	    <input type="hidden" name="prgrs_status" id="prgrs_status" value="<c:out value='${lomReq.prgrs_status}'/>" />
	    <input type="hidden" name="cntrt_id" id="cntrt_id"  value="<c:out value='${lomReq.cntrt_id}'/>" />
	    <!-- <input type="hidden" name="cntrt_untprc_expl" id="cntrt_untprc_expl"  value="<c:out value='${lomReq.cntrt_untprc_expl}'/>" /> -->
	    <input type="hidden" name="reqman_id" id="reqman_id"  value="<c:out value='${lomReq.reqman_id}'/>" />
	    <input type="hidden" name="chose_client" value="<c:out value='${reqAuthFormInfo}'/>" />
	    <input type="hidden" name="authreq_client" value="<c:out value='${reqAuthSvcInfo}'/>" />
	    
	    <input type="hidden" name="submit_status" id="submit_status" />
	    <input type="hidden" name="tempsave_flag" id="tempsave_flag" />
	    
	    <!-- 첨부파일정보 시작 -->
		<input type="hidden" name="fileInfos"       value="" /> 		<!-- 계약서 파일 정보 -->
		<input type="hidden" name="fileInfos2"      value="" /> 		<!-- 기타 파일 정보 -->
		<input type="hidden" name="fileInfos3" 		value="" />				<!-- 사전검토 파일 정보 -->
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
	    	<img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0"/><c:out value='${menuNavi}' escapeXml="false"/>
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
					<li><img src="<%=IMAGE %>/common/step01.gif"	 alt="<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" />" /></li>
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
				<div class="btn_wrap_t02" id="consultation_btn" style="display:none;">
				<span class="btn_all_w"><span class="preview"></span><a href="javascript:openArbitraryPop();"><spring:message code="clm.page.msg.manage.conclRule" /></a></span>
					<span class="btn_all_w"><span class="tsave"></span><a href="javascript:tempSave('button');"><spring:message code='clm.page.button.contract.imsisave' /></a></span>
					<span class="btn_all_w"><span class="delete"></span><a href="javascript:processCancel();"><spring:message code="clm.page.button.contract.rollback" /></a></span>
					<span class="btn_all_w"><span class="preview"></span><a href="javascript:openPreview();"><spring:message code='clm.page.button.contract.review' /></a></span>
					<span class="btn_all_w"><span class="reply"></span><a href="javascript:checkApprovalValidation();"><spring:message code="clm.page.button.contract.cosultationapproval"/></a></span>
					<span class="btn_all_w"><span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /></a></span>			
				</div>
				<div class="btn_wrap_t02" id="consultation_btn_list" style="display:none;">
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
				<tr class="lineAdd">
					<th><spring:message code="clm.page.field.myapproval.etcinfo"/></th>
					<c:choose>
						<c:when test="${command.prgrs_status =='C04211' or command.prgrs_status=='C04207' or command.prgrs_status=='C04212'}">
							<c:choose>
								<c:when test="${command.session_user_id == lomReq.reqman_id}">
									<td colspan="4" id="id_trgtman_nm"><c:out value="${reqAuthInfo}" /></td>
									<td><span class="btn_all_b" onclick='javascript:openChooseClient();'><span class="add"></span><a><spring:message code='clm.page.msg.manage.add' /></a></span></td>
								</c:when>
								<c:otherwise>
									<td colspan="5" id="id_trgtman_nm"><c:out value="${reqAuthInfo}" /></td>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<td colspan="5" id="id_trgtman_nm"><c:out value="${reqAuthInfo}" /></td>
						</c:otherwise>
					</c:choose>
				</tr>
			</table>
			<!-- title -->
		  	<div class="title_basic03">
				<h4><spring:message code="clm.page.title.contract.consultation.detail.contracttitle"/> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'contract-master');"/></h4>
			</div>
			<!-- //title -->
			<!-- tab01 -->
			<ul class="tab_basic02" id="contractTab">
				<c:forEach var="list" items="${lomMaster}">
				<!-- <li class=<c:if test="${list.rn=='1'}">on</c:if>><a href="#contract-view" onClick="javascript:changeContract('<c:out value='${list.cntrt_id}'/>');"><spring:message code="clm.page.msg.common.contract" /><c:out value='${list.rn}'/></a></li>-->
				<li id="<c:out value='${list.cntrt_id}'/>"><a href="#contract_view" onclick="javascript:changeContractAfterTempSave('<c:out value='${list.cntrt_id}'/>');"><spring:message code="clm.page.msg.common.contract" /><c:out value='${list.rn}'/></a></li>
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
			<!-- 이력정보end -->
			<div class="t_titBtn">
				<div class="btn_wrap_c" id="consultation_btn" style="display:none;">
					<span class="btn_all_w"><span class="tsave"></span><a href="javascript:tempSave('button');"><spring:message code='clm.page.button.contract.imsisave' /></a></span>
					<span class="btn_all_w"><span class="delete"></span><a href="javascript:processCancel();"><spring:message code="clm.page.button.contract.rollback" /></a></span>
					<span class="btn_all_w"><span class="preview"></span><a href="javascript:openPreview();"><spring:message code='clm.page.button.contract.review' /></a></span>
					<span class="btn_all_w"><span class="reply"></span><a href="javascript:checkApprovalValidation();"><spring:message code="clm.page.button.contract.cosultationapproval"/></a></span>
					<span class="btn_all_w"><span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /></a></span>			
				</div>
				<div class="btn_wrap_c" id="consultation_btn_list" style="display:none;">
					<span class="btn_all_w"><span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /></a></span>			
				</div>
				
			</div>
			</form:form>
		</div>
		<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- // footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
</body>
<!-- 나모 웹에디터 관련 추가 -->
	<c:choose>
		<c:when test="${command.page_gbn=='modify'}">
			<c:choose>
			<c:when test="${command.prgrs_status =='C04211' or command.prgrs_status=='C04207' or command.prgrs_status=='C04212'}">
				<c:choose>
					<c:when test="${command.session_user_id == lomReq.reqman_id}">
						<script language="javascript" for="wec" event="OnInitCompleted()">
							var wecObj = document.wec;
							wecObj.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
							wecObj.SetDefaultFontSize("9");
							wecObj.EditMode = 1;
							wecObj.Value = document.frm.body_mime.value;
						</script>
					</c:when>
				</c:choose>	
			</c:when>
		</c:choose>
		</c:when>
	</c:choose>
<!-- 나모 웹에디터 관련 추가 -->
</html>