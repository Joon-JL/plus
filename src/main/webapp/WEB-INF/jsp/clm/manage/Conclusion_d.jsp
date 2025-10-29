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
<%@ page import="com.sec.clm.manage.dto.ConsultationExecForm" %>
<%
	ListOrderedMap lomReq 	 	=  (ListOrderedMap)request.getAttribute("lomReq");
%>
<%--
/**
 * 파  일  명 	 : Conclusion_d.jsp
 * 프로그램명 : 체결본등록상세화면
 * 설      명 	 : 
 * 작  성  자 	 : 심주완
 * 작  성  일       : 2011.09
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko" >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title"/></title>
<!--계약관리일 경우  -->
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet"></link>

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/excanvas.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<!-- 달력관련추가 -->
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script>
<!--
$(document).ready(function(){
	var frm = document.frm;
	var cntrt_id = "";
	
	if("<c:out value='${command.cntrt_id}'/>" == ""){
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
	});
	
	$('#rowAddDel').attr("style","display:none");
	$('#rowAddDel2').attr("style","display:none");	
	$('#rowAddDel3').attr("style","display:none");
	setDetailTabClick();
});


/**
 *계약탭 클릭
 */
 function changeContract(cntrt_id){
	var frm = document.frm;
	frm.cntrt_id.value = cntrt_id;
	viewHiddenProgress(true) ;
	
	detailConclusionContractMaster();	//계약마스터조회
	listConclusionSpecial();			//
	listConclusionRelation();
	listConclusionDelay();				//미체결사유조회
	contractHisList();				//이력정보조회
	initCalendar();						//달력초기화
	initAttach();						//첨부파일초기화
	initRequire();						//필수입력항목체크
	setDetailTabClick();
	setInitControls();					//상태에따른 입력컨트롤 셋팅
	//체결여부에따른 필수값셋팅 및 해제
	$('input[name*=cntrt_cnclsn_yn]').bind('click', function(){
		initRequire();		
	});
	viewHiddenProgress(false) ;
	subTitleTabMove(1);
 }

/*
 * 계약마스터정보
 */
function detailConclusionContractMaster(){
	var options = {
		url: "<c:url value='/clm/manage/conclusion.do?method=detailConclusionContractMaster' />",
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

/*
 * 특화정보조회
 */
function listConclusionSpecial() {
	var options = {
		url: "<c:url value='/clm/manage/consultation.do?method=listConsultationSpecial' />",
		type: "post",
		async: false,
		dataType: "html",
		success: function (data) {
			$("#conclusion-specialinfo-list").html("");
			$("#conclusion-specialinfo-list").html(data);
		}
	};
	$("#frm").ajaxSubmit(options);
}

/*
 * 미체결사유
 */
function listConclusionDelay() {
	var options = {
		url: "<c:url value='/clm/manage/conclusion.do?method=listConclusionDelay' />",
		type: "post",
		async: false,
		dataType: "html",
		success: function (data) {
			$("#conclusion-delayinfo-list").html("");
			$("#conclusion-delayinfo-list").html(data);
		}
	};
	$("#frm").ajaxSubmit(options);
}

/*
 * 연관계약정보생성-2011.10.24추가
 */
function listConclusionRelation() {
	var options = {
			url: "<c:url value='/clm/manage/consultation.do?method=listConsultationRelation' />",
			type: "post",
			async: false,
			dataType: "html",
			success: function (data) {
				$("#conclusion-relationcontract-list").html("");
				$("#conclusion-relationcontract-list").html(data);
			}
		};
	$("#frm").ajaxSubmit(options);
}

/*
 * 계약탭클릭
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
	    document.getElementById("conclusion-relationcontract-list").style.display = "none"; //연관계약정보
	    document.getElementById("conclusion-specialinfo-list").style.display ="block";
    } else if(num==2) {	
   	  	document.getElementById("tab_contents_sub_conts1").style.display = "none";
   	 	document.getElementById("tab_contents_sub_conts1_sub").style.display = "none";
	    document.getElementById("conclusion-relationcontract-list").style.display = "none"; //연관계약정보
	    document.getElementById("conclusion-specialinfo-list").style.display ="none";
    } else if(num==4) {
    	document.getElementById("tab_contents_sub_conts1").style.display = "none";
    	document.getElementById("tab_contents_sub_conts1_sub").style.display = "none";
	    document.getElementById("conclusion-relationcontract-list").style.display = "block"; //연관계약정보
	    document.getElementById("conclusion-specialinfo-list").style.display ="none";
    } else {
    	document.getElementById("tab_contents_sub_conts1").style.display = "none";
    	document.getElementById("tab_contents_sub_conts1_sub").style.display = "none";
	    document.getElementById("conclusion-relationcontract-list").style.display = "block"; //연관계약정보
	    document.getElementById("conclusion-specialinfo-list").style.display ="none";
    }
}

/*
 * 임직원조회 팝업
 */
function searchEmployee(flag) {
	var frm 		= document.frm;
	var srchValue 	= "";
	var obj 		= new Object();
	if(flag=="sign"){
		obj = frm.signman_search_nm;
	} else if(flag=="accept"){
		obj = frm.org_hdovman_search_nm;
	} else {
		return;
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
    	PopUpWindowOpen('', 800, 450, true, "PopUpEmployee");
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
    	frm.signman_id.value = obj.epid;
    	frm.signman_nm.value = obj.cn;
    	frm.signman_search_nm.value = '';
    	frm.sign_dept_nm.value = obj.department;
    	frm.signman_jikgup_nm.value = obj.title;
    	
    	$('#signman').html('');
    	$('#signman').append(obj.cn+'/'+obj.title+'/'+obj.department);
    	
    } else if(srch_user_target=="accept") {
    	frm.org_hdovman_id.value = obj.epid;
    	frm.org_hdovman_nm.value = obj.cn;
    	frm.org_hdovman_search_nm.value = obj.cn;
    	frm.org_hdov_dept_nm.value = obj.department;
    	frm.org_hdovman_jikgup_nm.value = obj.title;
    }
}

/*
 * 첨부파일초기화
 */
function initAttach(){
	
  //계약-계약서파일
	frm.target          		= "fileConsultationContract";
    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
    frm.method.value    		= "forwardClmsFilePage";
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
    frm.file_midclsfcn.value 	= "F01202";
    frm.file_smlclsfcn.value 	= "F0120212";
    frm.view_gbn.value  		= "download";
    frm.ref_key.value   		= frm.cntrt_id.value + "@" + frm.cnsdreq_id.value;
    frm.fileInfoName.value 		= "fileInfos2";
    frm.fileFrameName.value 	= "fileConsultationEtc2";
    frm.multiYn.value 			= "Y";
    getClmsFilePageCheck('frm');   
    
  //단가내역-첨부
  <%--if("<c:out value='${lomReq.cntrt_untprc_expl}' />" != "") {--%>
  if(<%=StringUtil.isNull((String)lomReq.get("cntrt_untprc_expl"))%> == false) {		 
    frm.target          		= "fileConsultationUntPrc";
    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
    frm.method.value    		= "forwardClmsFilePage";
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
	if(frm.depth_status.value == "C02641") {    	
    	frm.view_gbn.value  	= "modify";
    } else {
    	frm.view_gbn.value  	= "download";
    }	
    frm.file_midclsfcn.value	= "F01203";
    frm.file_smlclsfcn.value 	= "";
    frm.fileFrameName.value		= "fileContractCopy";
    frm.fileInfoName.value 		= "fileInfos6";
    frm.preAllowFileList.value  = "PDF,P7M,"; //체결본은 pdf만 등록하게끔 한다.
    frm.multiYn.value 			= "N";
    frm.ref_key.value			= frm.cntrt_id.value;
    if(frm.depth_status.value == "C02641") {
    	if("<c:out value='${command.session_user_id}'/>" == frm.cntrt_respman_id.value) {
    		frm.submit();	
    	} else {
    		getClmsFilePageCheck('frm');
    	}	
    		
    } else {    	
    	getClmsFilePageCheck('frm');
    }	
}

/*
 * 체결여부에따른 입력필수값설정 및 초기화
 */
function initRequire() {
	var cnclsn_yn = $('input[name*=cntrt_cnclsn_yn]:checked').val();
	var seal_mthd = $('input[name*=seal_mthd1]:checked').val();
	var depth_status = $('input[name=depth_status]').val();
	
	if(depth_status == "C02641") {			//미등록상태
		if(cnclsn_yn =="Y"){
			if(seal_mthd=="C02101" || seal_mthd=="C02103") { //직인
				$('input[name=signman_id]').removeAttr("required");				//당사서명자아이디
				$('input[name=signman_nm]').removeAttr("required");				//당사서명자명
				$('input[name=sign_dept_nm]').removeAttr("required");			//당사서명자부서
				$('input[name=signday]').removeAttr("required");				//당사서명일
			} else { //서명
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
			//if($('#exprt_notidayCal_calIcon').attr("style") == "DISPLAY: none"){
				
				//initCal('exprt_notiday'); 	//만료사전알림일	신성우 주석처리 2014-04-01
				initCal('cntrt_cnclsnday'); //계약체결일
				
				if(seal_mthd=="C02102") { //서명
					initCal('oppnt_signday'); 	//서명일(상대)
					initCal('signday'); 		//서명일(당사)
				}
			//}
			
			$('input[name=oppnt_signman_nm]').parent().removeAttr("style");			//상대서명자아이디
			$('input[name=oppnt_signman_jikgup]').parent().removeAttr("style");		//상대서명자명
			$('input[name=oppnt_signman_dept]').parent().removeAttr("style");		//상대서명자부서
			$('input[name=oppnt_signday]').parent().removeAttr("style");			//상대서명일
			$('input[name=cntrt_cnclsnday]').parent().removeAttr("style");			//계약체결일
			//$('input[name=exprt_notiday]').parent().removeAttr("style");			//계약만료사전알림일	신성우 주석처리 2014-04-01
			$('#conclusion-delayinfo-list').attr("style", "display:none");
		} else {
			if(seal_mthd == "C02102") { //서명일때
				$('input[name=signman_id]').attr("disabled","disabled");		//당사서명자아이디
				$('input[name=signman_nm]').attr("disabled","disabled");		//당사서명자명
				$('input[name=sign_dept_nm]').attr("disabled","disabled");		//당사서명자부서
				$('input[name=signman_jikgup_nm]').attr("disabled","disabled");	//당사서명자직급
				$('input[name=signday]').attr("disabled","disabled");			//당사서명일
				$('#signdayCal_calIcon').attr("style","display:none").prev().css("border-right","#7f9db9 1px solid");
				
				$('input[name=signman_id]').parent().attr("style", "background:#F4F4F4");
				$('input[name=signman_nm]').parent().attr("style", "background:#F4F4F4");
				$('input[name=sign_dept_nm]').parent().attr("style", "background:#F4F4F4");
				$('input[name=signman_jikgup_nm]').parent().attr("style", "background:#F4F4F4");
				$('input[name=signday]').parent().attr("style", "background:#F4F4F4");
			}
			
			$('input[name=oppnt_signman_nm]').attr("disabled", "disabled");		//상대서명자아이디
			$('input[name=oppnt_signman_jikgup]').attr("disabled","disabled");	//상대서명자명
			$('input[name=oppnt_signman_dept]').attr("disabled","disabled");	//상대서명자부서
			$('input[name=oppnt_signday]').attr("disabled","disabled");			//상대서명일
			$('#oppnt_signdayCal_calIcon').attr("style","display:none").prev().css("border-right","#7f9db9 1px solid");
			$('input[name=cntrt_cnclsnday]').attr("disabled","disabled");		//계약체결일
			$('#cntrt_cnclsndayCal_calIcon').attr("style","display:none").prev().css("border-right","#7f9db9 1px solid");
			//$('input[name=exprt_notiday]').attr("disabled","disabled");			//계약만료사전알림일	신성우 주석처리 2014-04-01
			//$('#exprt_notidayCal_calIcon').attr("style","display:none").prev().css("border-right","#7f9db9 1px solid");	신성우 주석처리 2014-04-01
			
			$('input[name=oppnt_signman_nm]').parent().attr("style", "background:#F4F4F4");
			$('input[name=oppnt_signman_jikgup]').parent().attr("style", "background:#F4F4F4");
			$('input[name=oppnt_signman_dept]').parent().attr("style", "background:#F4F4F4");
			$('input[name=oppnt_signday]').parent().attr("style", "background:#F4F4F4");
			$('input[name=cntrt_cnclsnday]').parent().attr("style", "background:#F4F4F4");
			//$('input[name=exprt_notiday]').parent().attr("style", "background:#F4F4F4");	신성우 주석처리 2014-04-01
			$('#conclusion-delayinfo-list').attr("style", "display:");
		}	
	}
}	

/*
 * 날짜입력컨트롤 달력셋팅
 */
function initCalendar() {
	$('input[type=text]').each(function(){
		if($(this).hasClass('text_calendar02')) {
			initCal($(this).attr("id"));	
		}
	});
}

/*
 *체결본등록에 따른 화면 컨트롤 셋팅 
 */
function setInitControls() {
	
	if($('input[name=depth_status]').val()=='C02641') {			//미등록
		if($('input[name=cntrt_respman_id]').val() != "<c:out value='${command.session_user_id}'/>"){
			$('div[id^=conclusion_btn]').attr("style", "display:none");
			$('div[id^=conclusion_btn_list]').attr("style", "display:");
	
		} else {
			$('div[id^=conclusion_btn]').attr("style", "display:");
			$('div[id^=conclusion_btn_list]').attr("style", "display:none");
		}
	} else if ($('input[name=depth_status]').val()=='C02642') {	//사본등록
			$('div[id^=conclusion_btn]').attr("style", "display:none");
			$('div[id^=conclusion_btn_list]').attr("style", "display:");
	} else if($('input[name=depth_status]').val()=='C02662') {	//원본등록
		$('div[id^=conclusion_btn]').attr("style", "display:none");
		$('div[id^=conclusion_btn_list]').attr("style", "display:");
	}
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
<%--버튼이벤트--%>
/*
 * 임시저장
 */
function tempSave() {
	var frm = document.frm;
	var depth_status = $('input[name=depth_status]').val();
	var cnclsn_yn = $('input[name*=cntrt_cnclsn_yn]:checked').val();
	
	if(!confirm("<spring:message code="clm.page.msg.manage.announce137" />")) return;
	
	if(cnclsn_yn=="Y") { //임시저장일 때도 체결여부가 Yes일 경우 계약체결일 정보를 받도록 한다. 체결본사본의 파일명뒤의 일련번호를 따기 위해서
		if($('#cntrt_cnclsnday').val() == ''){
			alert("<spring:message code="clm.page.msg.manage.announce060" />");
	        return;	
		}
  	}
	
	frm.chgebfr_cnclsn_plndday.value = frm.chgeaft_cnclsn_plndday.value;
	
	frm.work_type.value="temp";
	fileContractCopy.UploadFile(function(uploadCount){
       	//첨부파일 업로드 결과 잘못된 형식의 파일이 올라간 경우
	    if(uploadCount == -1){
	    	initAttach(); //첨부파일창 Reload
	        alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
	        return;
	    }
	        
	   	viewHiddenProgress(true) ;
	    frm.method.value = "modifyConclusion";
        frm.target = "_self";		 
        frm.action = "<c:url value='/clm/manage/conclusion.do' />";	    
        frm.submit();
        });
	
}

/*
 * 저장
 */
function Save() {
	var frm = document.frm;
	var depth_status = $('input[name=depth_status]').val();
	var cnclsn_yn = $('input[name*=cntrt_cnclsn_yn]:checked').val();
	var cntrt_cnclsnday = $('#cntrt_cnclsnday').val();
	var today = new Date();
	var year = today.getFullYear();
	var month = today.getMonth()+1;
	var day = today.getDate();
	
	if(month < 10)
		month = "0"+month;
	if(day < 10)
		day = "0"+day;
	
	var todayDate = year + "-" + month + "-" + day;
	
	if(cnclsn_yn=="N") {
        alert("<spring:message code='clm.msg.alert.contract.conclusion.checkcnclsnyn'/>");
        return;
  	}
	else if(cnclsn_yn=="Y") { 
		if(cntrt_cnclsnday > todayDate){
			alert("<spring:message code="clm.page.msg.manage.announce173" />");
	        return;	
		}
  	}
	
	if(!confirm("<spring:message code="clm.page.msg.manage.announce148" />")) return;
	
	if(frm.seal_mthd.value == "C02102"){ //서명일때
		if(frm.signman_id.value == ''){
			alert("<spring:message code="clm.page.msg.manage.announce106" />");
			return;
		}
	}
	
	frm.work_type.value="";
	if(validateForm(frm)){
    	fileContractCopy.UploadFile(function(uploadCount){
        
	    	//첨부파일 업로드 결과 잘못된 형식의 파일이 올라간 경우
	        if(uploadCount == -1){
	        	initAttach(); //첨부파일창 Reload
	            alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
	            return;
	        }
	
	    	//첨부파일이 필수 인 경우 : 사용자 선택사항
        	if(uploadCount == 0){
        		alert("<spring:message code='clm.msg.alert.contract.conclusion.nofile' />");
            	return;
        	}
	        frm.page_gbn.value = "";
	    	viewHiddenProgress(true) ;
	        frm.method.value = "modifyConclusion";
        	frm.target = "_self";		 
        	frm.action = "<c:url value='/clm/manage/conclusion.do' />";	    
            frm.submit();
            
    	
        });
	}
}

/*
 * 목록
 */
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


/*
 * 해당 계약건 Drop!!
 */
function goDrop(){
	if(confirm("<spring:message code="clm.page.msg.manage.announce202" />")){
		opnnConsideration();
	}
}

/**
 * 계약 Drop 팝업 
 */
function opnnConsideration(){
	
 	var frm = document.frm;
 	
	PopUpWindowOpen('', "530", "200", true, "popUpDrop");
    frm.action = "<c:url value='/clm/manage/conclusion.do?' />";
    frm.method.value="opnnConclusion";
    frm.target = "popUpDrop";
    frm.submit();	
}

/**
 * 계약 Drop 후 리스트로 이동! 
 */
function setOpnnConsideration(obj) {    
	var frm = document.frm;
	
	goList();
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

//인쇄팝업
function openPrint(){
	
	var frm = document.frm;
	
	PopUpWindowOpen('', '1024','768',true,'popUpPrint');
	
	frm.action	= "<c:url value='/clm/manage/consultation.do' />";
	frm.method.value = "forwardHistoryPop";
	frm.buGubn.value = "Y";
	frm.target = "popUpPrint";
	frm.submit();
}


//-->	
</script>
</head>
<body>
<!-- container -->
<div id="wrap">
	<div id="container">
		<!-- location -->
		<!-- <div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0" alt="Home">계약관리 > 계약체결 ><strong>계약등록</strong></div>-->
		<div class="location">
	    	<img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0"/><c:out value='${menuNavi}' escapeXml="false"/>
	    </div>
		<!-- //location -->
		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.msg.manage.regCont" /></h3>
		</div>
		<!-- content -->
		<div id="content">
			<div class="content-step t_titBtn">
				<ol>
					<li><img src="<%=IMAGE %>/common/step01.gif"	 alt="<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" />" /></li>
					<li><img src="<%=IMAGE %>/common/step02.gif"     alt="<spring:message code="clm.page.msg.manage.step02" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step02" htmlEscape="true" />" /></li>
					<li class='step_on'>
						<img src="<%=IMAGE %>/common/step03_on.gif"  alt="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" />
						<img src="<%=IMAGE %>/common/step_arr01.gif" alt="Next" title="Next" />
						<div class='step_link'><a href="javascript:open_window('win', '../../step03<%=langCd.equals("ko")?"":"_"+langCd %>.html', 0, 0, 696, 588, 0, 0, 0, 0, 0)" ><img src="<%=IMAGE %>/common/step_q.gif" alt="<spring:message code="clm.page.msg.manage.procStep"/>" title="<spring:message code="clm.page.msg.manage.procStep"/>" /></a></div>
					</li>
					<li><img src="<%=IMAGE %>/common/step04.gif"     alt="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" /></li>
					<li><img src="<%=IMAGE %>/common/step05.gif"     alt="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" /></li>
				</ol>
			</div>
			<!-- //tab01 -->
			<form:form name="frm" id='frm' method="post" autocomplete="off">
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
			<input type="hidden" name="srch_step" value="<c:out value='${command.srch_step}' escapeXml='false'/>" />						<!-- 상태 -->
			<input type="hidden" name="ismycontract" id="ismycontract" value="<c:out value='${command.ismycontract}'/>"/>
			
			<input type="hidden" name="method" id="method" value=""/>
			<input type="hidden" name="srch_user_cntnt_type" value=""/>
			<input type="hidden" name="srch_user_cntnt" value=""/>
			<input type="hidden" name="srch_user_cntnt_target" value=""/>
			<input type="hidden" name="user_role" value="<c:out value='${command.user_role}'/>"/>
			<!-- <input type="hidden" name="user_role" value="RD01"/>-->
			<input type="hidden" name="user_orgnz" value="<c:out value='${command.user_orgnz}'/>"/>
			<input type="hidden" name="menu_id" id="menu_id" value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="curPage" id="curPage" value="<c:out value='${command.curPage}'/>"/>
			<input type="hidden" name="work_type" id="work_type" value=""/>
			<input type="hidden" name="page_gbn" id="page_gbn" value="<c:out value='${command.page_gbn}'/>"/>
			
		    <!-- 계약추가 증가데이타 -->	    
		    <input type="hidden" name="cnsdreq_id" id="cnsdreq_id" value="<c:out value='${lomReq.cnsdreq_id}'/>" />	 
		    <input type="hidden" name="cntrt_id"   id="cntrt_id"  value="<c:out value='${lomReq.cntrt_id}'/>" />
		    <input type="hidden" name="cntrt_untprc_expl" id="cntrt_untprc_expl"  value="<c:out value='${lomReq.cntrt_untprc_expl}'/>" />
		    <input type="hidden" name="prgrs_status" id="prgrs_status"  value="<c:out value='${lomReq.prgrs_status}'/>" />
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
			<input type="hidden" name="preAllowFileList"  value="" /> 		<!-- 별도확장자의 첨부파일만 업로드 할 때 -->
			
			<input type="hidden" name="mis_id" />
			<!-- title -->
		  	<div class="title_basic03">
				<h4><spring:message code="clm.page.title.contract.consultation.detail.contracttitle"/> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'contract-master');"/></h4>
			</div>
			<!-- //title -->
			<!-- tab01 -->
			<div class="t_titBtn">
				<ul class="tab_basic02" id="contractTab">
					<c:forEach var="list" items="${lomMaster}">
					<!-- <li class=<c:if test="${list.rn=='1'}">on</c:if>><a href="#contract-view" onClick="javascript:changeContract('<c:out value='${list.cntrt_id}'/>');">계약<c:out value='${list.rn}'/></a></li>-->
					<li id="<c:out value='${list.cntrt_id}'/>"><a href="#contract-view" onclick="javascript:changeContract('<c:out value='${list.cntrt_id}'/>');"><spring:message code="clm.page.msg.common.contract" /><c:out value='${list.rn}'/></a></li>
					</c:forEach>		
				</ul>
				<div class="btn_wrap_t03" id="conclusion_btn">
					<span class="btn_all_w"><span class="tsave"></span><a href="javascript:tempSave();"><spring:message code='clm.page.button.contract.imsisave' /></a></span>
					<span class="btn_all_b"><span class="save"></span><a href="javascript:Save();"><spring:message code='clm.page.button.contract.save' /></a></span>
					<span class="btn_all_w"><span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /></a></span>
					<!-- <span class="btn_all_w"><span class="reset"></span><a href="javascript:goDrop();"><spring:message code='clm.page.button.contract.drop' /></a></span> -->
				</div>
				<div class="btn_wrap_t03" id="conclusion_btn_list">
				<span class="btn_all_w"><span class="preview"></span><a href="javascript:openPrint();"><spring:message code='las.page.button.lawconsult.print' /></a></span>
					<span class="btn_all_w"><span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /></a></span>
				</div>
			</div>
			<!-- //tab01 -->
			<!-- 두줄라인 추가 --><div class="tal_topLine"></div><!-- //두줄라인 추가 -->
			<!-- toptable-->
			<div id="contract-master-info"></div>
			<!-- 이력정보start -->
			<div id="contractHis-list">
			</div>
			<div class="t_titBtn">
				<div class="btn_wrap_c" id="conclusion_btn">
					<span class="btn_all_w"><span class="tsave"></span><a href="javascript:tempSave();"><spring:message code='clm.page.button.contract.imsisave' /></a></span>
					<span class="btn_all_b"><span class="save"></span><a href="javascript:Save();"><spring:message code='clm.page.button.contract.save' /></a></span>
					<span class="btn_all_w"><span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /></a></span>
					<!-- <span class="btn_all_w"><span class="reset"></span><a href="javascript:goDrop();"><spring:message code='clm.page.button.contract.drop' /></a></span> -->			
				</div>
				<div class="btn_wrap_c" id="conclusion_btn_list">
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
</div>
</body>
</html>