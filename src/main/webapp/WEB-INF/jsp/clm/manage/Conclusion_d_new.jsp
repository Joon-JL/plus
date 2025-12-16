<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="com.sec.clm.manage.dto.ConsultationExecForm" %>
<%
    ListOrderedMap lomReq       =  (ListOrderedMap)request.getAttribute("lomReq");
    ListOrderedMap contractLom  =  (ListOrderedMap)request.getAttribute("contractLom");

    ArrayList review	= (ArrayList)request.getAttribute("review");
    ArrayList approve   = (ArrayList)request.getAttribute("approve");
    ArrayList sign      = (ArrayList)request.getAttribute("sign");
    ArrayList info      = (ArrayList)request.getAttribute("info");
    ArrayList defer		= (ArrayList)request.getAttribute("defer");

    ArrayList authReqList = (ArrayList)request.getAttribute("authReqList");  // 관련자
%>
<%--
/**
 * 파  일  명 	 : Conclusion_d_new.jsp
 * 프로그램명      : 체결본등록상세화면
 * 설      명 	 : 
 * 작  성  자 	 : 박정훈
 * 작  성  일      : 2013.05
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="clm.main.title"/></title>
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
	
	// 2014-07-24 Kevin added.
	// Following codes retrive step/status information and show them.
	var considerationFunc = new ConsiderationFunc($);
	considerationFunc.getStepStatusInfoPerCnsdreqid($("#cnsdreq_id").val(), function(data){
		$("#spStep").text(data.step);
		$("#spStatus").text(data.status+" "+data.status_depth);
	});
	
    // 이력 첨부파일
    contractHisList();	//Sungwoo replaced 2014-09-16
    
    initCalendar();                     //달력초기화
    
    if(  $('#depth_status').val() == 'C02641'){
    	$('.attach').each(function(i){
    		$(this).css({ display: "none" });
    	});
    }
    initAttach();
    initGerpInfo();
    
	setInitControls();                 //상태에따른 입력컨트롤 셋팅
	
	$('input[name*=cntrt_cnclsn_yn]').bind('click', function(){
        initRequire();      
    });

    var dateInputId = '#cntrt_cnclsnday';

    // 1. Retain the change handler for manual typing
    $(dateInputId).on('change', validateSelectedDate);

    // 2. Attach a global document click listener (as requested by the user).
    // This attempts to catch the moment the user clicks outside the calendar/input.
    // We use a short timeout (200ms) to ensure the calendar script has written the value.
    $(document).on('click', function(event) {
        var $target = $(event.target);

        // Optimization: Only run validation if the target is NOT the calendar input itself
        // AND NOT part of the calendar modal (we use a common class name 'divBody' found
        // in the ahmax script for the calendar container ID).

        if (!$target.is(dateInputId) && !$target.closest('.divBody').length) {
            // If user clicks elsewhere, check the date input value with a short delay.
            // Delay set to 200ms to allow the calendar's internal script to finish writing the value.
            setTimeout(validateSelectedDate, 200);
        }
    });

});

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
    frm.file_midclsfcn.value 	= "F01202";
    frm.file_smlclsfcn.value 	= "F0120212";
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
	if(frm.depth_status.value == "C02641") {    	
    	frm.view_gbn.value  	= "modify";
    } else {
    	frm.view_gbn.value  	= "download";
    }	
    frm.file_bigclsfcn.value 	= "F012";
    frm.file_midclsfcn.value	= "F01203";
    frm.file_smlclsfcn.value 	= "";
    frm.fileFrameName.value		= "fileContractCopy";
    frm.fileInfoName.value 		= "fileInfos6";
    frm.preAllowFileList.value  = "PDF,P7M,"; //체결본은 pdf만 등록하게끔 한다.
    frm.multiYn.value 			= "N";
    frm.ref_key.value			= frm.cntrt_id.value;
    if(frm.depth_status.value == "C02641") {
    	if("<c:out value='${command.session_user_id}'/>" == frm.cntrt_respman_id.value || "RA01" == "<c:out value='${ command.top_role }' />" ) {
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
/*
 * 체결여부에따른 입력필수값설정 및 초기화
 */
function initRequire() {
	var cnclsn_yn = $('input[name*=cntrt_cnclsn_yn]:checked').val();
	var seal_mthd = "<c:out value='${contractLom.seal_mthd}'/>";
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
				$('input[name=signman_id]').parent().removeAttr("style");		        //당사서명자아이디
				$('input[name=signman_nm]').parent().removeAttr("style");				//당사서명자명
				$('input[name=sign_dept_nm]').parent().removeAttr("style");			//당사서명자부서
				$('input[name=signday]').parent().removeAttr("style");				//당사서명일
				$('input[name=signman_search_nm]').removeAttr("disabled");			//당사서명아이디
				$('input[name=signman_search_nm]').parent().removeAttr("style");	
				$('#signman_search_nmImg').show();			//당사서명아이디
			}
	
			$('input[name=oppnt_signman_nm]').removeAttr("disabled");			//상대서명자아이디
			$('input[name=oppnt_signman_jikgup]').removeAttr("disabled");		//상대서명자명
			$('input[name=oppnt_signman_dept]').removeAttr("disabled");			//상대서명자부서
			$('input[name=oppnt_signday]').removeAttr("disabled");				//상대서명일
			$('input[name=cntrt_cnclsnday]').removeAttr("disabled");			//계약체결일
			initCal('cntrt_cnclsnday'); //계약체결일
			
			if(seal_mthd=="C02102") { //서명
				initCal('oppnt_signday'); 	//서명일(상대)
				initCal('signday'); 		//서명일(당사)
			}
			
			$('input[name=oppnt_signman_nm]').parent().removeAttr("style");			//상대서명자아이디
			$('input[name=oppnt_signman_jikgup]').parent().removeAttr("style");		//상대서명자명
			$('input[name=oppnt_signman_dept]').parent().removeAttr("style");		//상대서명자부서
			$('input[name=oppnt_signday]').parent().removeAttr("style");			//상대서명일
			$('input[name=cntrt_cnclsnday]').parent().removeAttr("style");			//계약체결일
			$('#conclusion-delayinfo-list').hide();
		} else {
			if(seal_mthd == "C02102") { //서명일때
				$('input[name=signman_id]').attr("disabled","disabled");		//당사서명자아이디
				$('input[name=signman_nm]').attr("disabled","disabled");		//당사서명자명
				$('input[name=sign_dept_nm]').attr("disabled","disabled");		//당사서명자부서
				$('input[name=signman_jikgup_nm]').attr("disabled","disabled");	//당사서명자직급
				$('input[name=signday]').attr("disabled","disabled");			//당사서명일
				$('input[name=signman_search_nm]').attr("disabled","disabled");	//당사서명
				$('#signman_search_nmImg').prev().css("border-right","#7f9db9 1px solid");
				$('#signman_search_nmImg').hide();	//당사서명
				$('#signdayCal_calIcon').prev().css("border-right","#7f9db9 1px solid");
				$('#signdayCal_calIcon').hide();
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
			
			$('#oppnt_signdayCal_calIcon').prev().css("border-right","#7f9db9 1px solid");
			$('#oppnt_signdayCal_calIcon').hide();
			$('input[name=cntrt_cnclsnday]').attr("disabled","disabled");		//계약체결일
			$('#cntrt_cnclsndayCal_calIcon').prev().css("border-right","#7f9db9 1px solid");
			$('#cntrt_cnclsndayCal_calIcon').hide();
			
			$('input[name=oppnt_signman_nm]').parent().attr("style", "background:#F4F4F4");
			$('input[name=oppnt_signman_jikgup]').parent().attr("style", "background:#F4F4F4");
			$('input[name=oppnt_signman_dept]').parent().attr("style", "background:#F4F4F4");
			$('input[name=oppnt_signday]').parent().attr("style", "background:#F4F4F4");
			$('input[name=cntrt_cnclsnday]').parent().attr("style", "background:#F4F4F4");
			$('#conclusion-delayinfo-list').show();
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
 * 권한에따른 페이지영역 Toggle셋팅
 */
function initAcptInfo() {
	//사용자권한에 따른 페이지내용 Display Toggle
	if($('input[name=user_role]').val() == "RD01") {
		$('#tab_orgaccept_info').attr("style", "display:");
	} else {
		$('#tab_orgaccept_info').attr("style", "display:none");
	}
}

/*
 *체결본등록에 따른 화면 컨트롤 셋팅 
 */
function setInitControls() {
	
	if($('input[name=depth_status]').val()=='C02641') {			//미등록
		if("RA01" == "<c:out value='${ command.top_role }' />"){
			$('div[id^=conclusion_btn]').attr("style", "display:;margin-top:-26px;margin-bottom:5px");
			$('div[id^=conclusion_btn_list]').attr("style", "display:none");
			$('div[id^=conclusion_btn2]').attr("style", "display:");
            $('div[id^=conclusion_btn_list2]').attr("style", "display:none");
		} else if($('input[name=cntrt_respman_id]').val() != "<c:out value='${command.session_user_id}'/>"){
			$('div[id^=conclusion_btn]').attr("style", "display:none");
			$('div[id^=conclusion_btn_list]').attr("style", "display:;margin-top:-26px;margin-bottom:5px");
			$('div[id^=conclusion_btn2]').attr("style", "display:none");
            $('div[id^=conclusion_btn_list2]').attr("style", "display:");
		} else {
			$('div[id^=conclusion_btn]').attr("style", "display:;margin-top:-26px;margin-bottom:5px");
			$('div[id^=conclusion_btn_list]').attr("style", "display:none");
			$('div[id^=conclusion_btn2]').attr("style", "display:");
            $('div[id^=conclusion_btn_list2]').attr("style", "display:none");
		}
	} else if ($('input[name=depth_status]').val()=='C02642') {	//사본등록
		$('div[id^=conclusion_btn]').attr("style", "display:none");
		$('div[id^=conclusion_btn_list]').attr("style", "display:;margin-top:-26px;margin-bottom:5px");
		$('div[id^=conclusion_btn2]').attr("style", "display:none");
        $('div[id^=conclusion_btn_list2]').attr("style", "display:;");
	} else if($('input[name=depth_status]').val()=='C02662') {	//원본등록
		$('div[id^=conclusion_btn]').attr("style", "display:none");
		$('div[id^=conclusion_btn_list]').attr("style", "display:;margin-top:-26px;margin-bottom:5px");
		$('div[id^=conclusion_btn2]').attr("style", "display:none");
        $('div[id^=conclusion_btn_list2]').attr("style", "display:");
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
	
	var Feture = "toolbar=0, location=0, status=1, menubar=0, scrollbars=no, resizable=yes, top="+Top+", left="+Left+", width="+popupwidth+", height="+popupheight;
	PopUpWindow = window.open(surl, popName , Feture);

	PopUpWindow.focus();
}

/*
 * 관련자팝업
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

    PopUpWindowOpen('', "530", "470", true, "PopUpChooseClient");
    frm.action = "<c:url value='/clm/manage/chooseClient.do' />";
    frm.method.value="forwardChooseClientPopup";
    frm.target = "PopUpChooseClient";
    frm.submit();
 }

//관련자정보세팅...
function setListClientInfo(returnValue) {
    var arrReturn = returnValue.split("!@#$");
    var innerHtml ="";
    var isListEmpty = (arrReturn.length === 1 && arrReturn[0] === ""); // True if result is just "" or if no separator was found

    //if(arrReturn[0]=="") { return ; }

    $('#id_trgtman_nm').html("");

    // Start building hidden fields for AJAX submission
    var trgtmanIds = []; // Array to collect IDs

    if (!isListEmpty) {

        for (var i = 0; i < arrReturn.length; i++) {
            var arrInfo = arrReturn[i].split("|");

            if (arrInfo.length >= 5) {
                // Add the ID to our array for later handling
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

    var options = {
        url: "<c:url value='/clm/review/consideration.do?method=modifyRefCCAJAX' />",
        type: "post",
        dataType: "json"
    };

    $("#frm").ajaxSubmit(options);
 }
<%--버튼이벤트--%>
/*
 * 임시저장
 */
function tempSave() {
	var frm = document.frm;
	var cnclsn_yn = $('input[name*=cntrt_cnclsn_yn]:checked').val();
	if(!confirm("<spring:message code="clm.page.msg.manage.announce137" />")) return;
	
	if(cnclsn_yn=="Y") { //임시저장일 때도 체결여부가 Yes일 경우 계약체결일 정보를 받도록 한다. 체결본사본의 파일명뒤의 일련번호를 따기 위해서
		if($('#cntrt_cnclsnday').val() == ''){
			alert("<spring:message code="clm.page.msg.manage.announce060" />");
	        return;	
		}
  	}
	
	//frm.chgebfr_cnclsn_plndday.value = frm.chgeaft_cnclsn_plndday.value;
	
	frm.work_type.value="temp";
	fileContractCopy.UploadFile(function(uploadCount){
       	//첨부파일 업로드 결과 잘못된 형식의 파일이 올라간 경우
	    if(uploadCount == -1){
	    	initAttach(); //첨부파일창 Reload
	        alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
	        return;
	    }
	        
	   	viewHiddenProgress(true) ;
	    frm.method.value = "modifyConclusionNew";
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
		if($('#cntrt_cnclsnday').val() == ''){
            alert("<spring:message code="clm.page.msg.manage.announce060" />");
            return; 
        }
		
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
		viewHiddenProgress(true) ;

    	fileContractCopy.UploadFile(function(uploadCount){
        
	    	//첨부파일 업로드 결과 잘못된 형식의 파일이 올라간 경우
	        if(uploadCount == -1){
	        	initAttach(); //첨부파일창 Reload
	            alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
	            viewHiddenProgress(false) ;
	            return;
	        }
	
	    	//첨부파일이 필수 인 경우 : 사용자 선택사항
        	if(uploadCount == 0){
        		alert("<spring:message code='clm.msg.alert.contract.conclusion.nofile' />");
        		viewHiddenProgress(false) ;
            	return;
        	}
	        frm.page_gbn.value = "";
	        frm.method.value = "modifyConclusionNew";
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

	// TOP 30 팝업
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
     * Helper function to get the current date in YYYY-MM-DD format.
     * This format allows for reliable string comparison.
     * Joon Dec, 2025
     */
    function getTodayString() {
        var today = new Date();
        var yyyy = today.getFullYear();
        var mm = today.getMonth() + 1; // Months start at 0
        var dd = today.getDate();

        // Pad month/day with leading zero if necessary
        var mmStr = (mm < 10) ? '0' + mm : mm;
        var ddStr = (dd < 10) ? '0' + dd : dd;

        return ddStr + '-' + mmStr + '-' + yyyy;
    }

    /**
     * Validates the date input field to ensure the date is no later than today.
     * This function is triggered by the input's onChange event.
     * Joon Dec, 2025
     */
    function validateSelectedDate() {
        var dateInput = document.getElementById('cntrt_cnclsnday');
        var selectedDate = dateInput.value;

        // Skip validation if the field is empty or was just cleared
        if (!selectedDate) {
            return;
        }

        var todayStr = getTodayString();

        // String comparison works because both dates are in YYYY-MM-DD format.
        if (selectedDate > todayStr) {

            // 1. Alert the user (using alert() as requested)
            alert("The selected date cannot be later than today's date (" + todayStr + ").");

            // 2. Clear the invalid date
            dateInput.value = "";

            // 3. Optional: Re-focus the field
            dateInput.focus();
        }
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
			<div id="content_in">
			<form:form name="frm" id='frm' method="post" autocomplete="off">
			<input type="hidden" name="contents" id="contents" />
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
			<!-- 13/10/29 검색 조건 유지 추가 -->
			<input type="hidden" name="srch_cntrt_nm" value="<c:out value='${command.srch_cntrt_nm}'/>" />						<!-- Contract Title -->
			<input type="hidden" name="srch_cntrt_no" value="<c:out value='${command.srch_cntrt_no}'/>" />						<!-- Contract ID -->
			<input type="hidden" name="srch_str_org_acptday" value="<c:out value='${command.srch_str_org_acptday}'/>" />		<!-- Original Copy -->
			<input type="hidden" name="srch_end_org_acptday" value="<c:out value='${command.srch_end_org_acptday}'/>" />		<!-- Original Copy -->
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
			<!-- Conclusion_d_master.jsp 에 있던 hiiden -->
            <input type="hidden" name="prcs_depth" id="prcs_depth" value="<c:out value='${contractLom.prcs_depth}'/>"   />   <!-- 프로세스단계 파일 정보 -->
            <input type="hidden" name="depth_status" id="depth_status" value="<c:out value='${contractLom.depth_status}'/>" />  <!-- 단계상태 -->
            <input type="hidden" name="cntrt_status" id="cntrt_status"  value="<c:out value='${contractLom.cntrt_status}'/>" />     <!-- 계약상태-->
            <input type="hidden" name="cntrt_respman_id" value="<c:out value='${contractLom.cntrt_respman_id}'/>"/>
            <input type="hidden" name="master_cntrt_id" id="master_cntrt_id" value="<c:out value='${contractLom.cntrt_id}'/>"  />
            <input type="hidden" name="client_modify_div" id="client_modify_div"  />
            <input type="hidden" name="chose_client" id="chose_client"  />
            <input type="hidden" name="authreq_client" value="<c:out value='${reqAuthSvcInfo}'/>" />
            <input type="hidden" name="srch_type_gbn" id="srch_type_gbn" value="SC0101" /> <!-- SC0101:날인담당자  /SC0102:증명서류발급담당자-->
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
						<div class='step_link'><a href="javascript:open_window('win', '../../step03<%=langCd.equals("ko")?"":"_"+langCd %>.html', 0, 0, 696, 588, 0, 0, 0, 0, 0)" ><img src="<%=IMAGE %>/common/step_q.gif" alt="<spring:message code="clm.page.msg.manage.procStep"/>" title="<spring:message code="clm.page.msg.manage.procStep"/>" /></a></div>
					</li>
					<li><img src="<%=IMAGE %>/common/step04.gif"     alt="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" /></li>
					<li><img src="<%=IMAGE %>/common/step05.gif"     alt="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" /></li>
				</ol>
			</div>
			<!-- //tab01 -->
			
			<!-- title -->
		  	<div class="title_basic">
				<h4><spring:message code="clm.page.title.contract.consultation.detail.contracttitle"/> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'contract-master');"/></h4>
			</div>
			<!-- //title -->
			<!-- tab01 -->
			<div class="t_titBtn">
				<div class='title_basic3'><spring:message code="clm.page.msg.manage.bscInf" /></div>
				<div class="btn_wrap_t03" id="conclusion_btn" style="display:none">
					<span class="btn_all_w"><span class="save"></span><a href="javascript:Save();"><spring:message code="clm.page.msg.manage.registrate" /></a></span>
					<span class="btn_all_w"><span class="tsave"></span><a href="javascript:tempSave();"><spring:message code='clm.page.button.contract.imsisave' /></a></span>
					<span class="btn_all_p"><span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /></a></span>
					<!-- <span class="btn_all_w"><span class="reset"></span><a href="javascript:goDrop();"><spring:message code='clm.page.button.contract.drop' /></a></span> -->
				</div>
				<div class="btn_wrap_t03" id="conclusion_btn_list" style="display:none">
				    <span class="btn_all_p"><span class="print"></span><a href="javascript:openPrint();"><spring:message code='las.page.button.lawconsult.print' /></a></span>
					<span class="btn_all_p"><span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /></a></span>
				</div>
			</div>
			<!-- //tab01 -->
			
			<table class="table-style01">
                <colgroup>
                    <col width="16%" />
                    <col width="11%" />
                    <col width="16%" />
                    <col width="14%" />
                    <col width="15%" />
                    <col width="16%" />
                    <col width="15%" />
                </colgroup>
                <tr>
                    <th><spring:message code="clm.page.msg.manage.reqName" /></th>
                    <td colspan="7"><c:out value="${contractLom.req_title}" escapeXml="false"/></td>
                </tr>
                <tr>
                    <th><spring:message code='clm.page.field.contract.basic.name' /></th>
                    <td colspan="4"><c:out value="${contractLom.cntrt_nm}" /></td>
                    <th><spring:message code="clm.page.msg.manage.contId" /></th>
                    <td><c:out value="${contractLom.cntrt_no}" /></td>
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
                    <th>
                    	<!-- 2014-04-17 Kevin commented. -->
                    	<%-- <spring:message code="clm.page.field.customer.contractRequired" /> --%>
                    </th>
                    <td>
                    	<!-- 2014-04-17 Kevin commented. -->
                    	<%-- <c:out value="${contractLom.cntrt_oppnt_type_nm}" /> --%>
                    </td>
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
                    <td colspan="2"><c:out value="${contractLom.cntrt_trgt_nm}" /></td>
                    <th><spring:message code="clm.page.field.contract.basic.detail" /></th>
                    <td colspan="3"><c:out value="${contractLom.cntrt_trgt_det}" escapeXml="false"/></td>
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
                    <td><c:out value="${contractLom.secret_keepperiod}" /></td>
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
                    <td class="tal_lineL"><span class="blueD"><spring:message code='clm.page.field.contract.basic.filename2' /></span></td><!-- 첨부파일 - 기타 -->
                    <td colspan="6">
                    <iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationEtc2" name="fileConsultationEtc2" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
                    </td>
                </tr>
                <tr class="attach">
                    <td class="tal_lineL"><span class="blueD"><spring:message code='clm.page.msg.common.outlook' /></span></td><!-- outlook -  -->
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
                    <td><c:out value='${contractLom.bfhdcstn_apbtday}'/></td>
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
	                                <td><c:out value='${list.rel_type_nm}' escapeXml="false"/></td>
	                                <td>
	                                    <!-- 신성우 변경처리 CNTRT_ID -> CNSDREQ_ID 2014-04-10 POPUP 호출시 REQ_ID 전달안함-->
                                        <a href="javascript:goDetail('<c:out value='${list.cnsdreq_id}'/>');"><c:out value='${list.relation_cntrt_nm}'/></a>
	                                </td>
	                                <td><c:out value='${list.rel_defn}'/></td>
	                                <td><c:out value='${list.expl}' escapeXml="false"/></td>
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
                <h4><spring:message code="clm.page.title.contract.conclusion.detail.title"/> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'tab_contents_sub_conts3');" style="cursor:pointer"/></h4>
            </div>
            <table id="tab_contents_sub_conts4" cellspacing="0" cellpadding="0" border="0" class="table-style01" style="margin-top:0;display:">
	            <colgroup>
	                <col width="15%" />
	                <col width="13%" />
	                <col width="11%" />
	                <col width="11%" />
	                <col width="11%" />
	                <col width="12%" />
	                <col width="14%" />
	                <col />
	            </colgroup>
	            <tr>
	                <th><input name="cntrt_cnclsn_yn_val" type="hidden" value="Y"/>
	                	<input name="cntrt_cnclsn_yn" type="radio" value="Y" checked="checked" style="display: none;"/>
	                	<spring:message code="clm.page.field.contract.conclusion.detail.conclusionplanday"/></th> <!-- 체결예정일 -->
	                <td colspan="3"><c:out value='${contractLom.cnclsn_plndday}'/></td>
	                <th><spring:message code="clm.page.field.contract.conclusion.detail.conclusionday"/><span class="astro">*</span></th> <!-- 계약체결일 -->
	                <td colspan="3">
						<input type="text" name="cntrt_cnclsnday" id="cntrt_cnclsnday" onChange="validateSelectedDate();" value="<c:out value='${contractLom.cntrt_cnclsnday}'/>"  class="text_calendar02" required alt="<spring:message code="clm.page.field.contract.conclusion.detail.conclusionday"/>" />
	                    ※ <spring:message code="clm.page.msg.manage.signCompl" />  
	                </td>
	            </tr>
	            <tr>
	                <th><spring:message code="clm.page.field.contract.consultation.detail.methodgbn"/></th> <!-- 직인/서명 구분 -->
	                <td colspan="3">
		                <c:if test="${contractLom.seal_mthd=='C02101'}"><spring:message code="clm.page.msg.manage.userSign" /></c:if> <!-- 사용인감날인 -->
                        <c:if test="${contractLom.seal_mthd=='C02103'}"><spring:message code="clm.page.msg.manage.signCorp" /></c:if> <!-- 법인인감날인 -->
                        <c:if test="${contractLom.seal_mthd=='C02102'}"><spring:message code="clm.page.msg.manage.sign" /></c:if> <!-- 서명 -->
                        <input type="hidden" name="seal_mthd" value="<c:out value="${contractLom.seal_mthd }"/>"/>
	                </td>
	                <c:choose>
	                    <c:when test="${contractLom.seal_mthd=='C02101' || contractLom.seal_mthd=='C02103'}"> <!-- 인감날인 -->
	                        <th><spring:message code="clm.page.field.contract.consultation.detail.sealffmtmannm"/></th> <!-- 날인담당자 -->
	                        <td colspan="3">
	                            <input type="hidden" name="seal_ffmtman_id" id="seal_ffmtman_id" value="<c:out value='${contractLom.seal_ffmtman_id}'/>" /> 
	                            <input type="hidden" name="seal_ffmtman_nm" id="seal_ffmtman_nm" value="<c:out value='${contractLom.seal_ffmtman_nm}'/>" />
	                            <input type="hidden" name="seal_ffmt_dept_cd" id="seal_ffmt_dept_cd" value="<c:out value='${contractLom.seal_ffmt_dept_cd}'/>"/>
	                            <input type="hidden" name="seal_ffmt_dept_nm" id="seal_ffmt_dept_nm" value="<c:out value='${contractLom.seal_ffmt_dept_nm}'/>"/>
	                            <input type="hidden" name="seal_ffmtman_jikgup_nm" id="seal_ffmtman_jikgup_nm" value="<c:out value='${contractLom.seal_ffmtman_jikgup_nm}'/>"/>
	                            <input type="text"   name="seal_ffmtman_search_nm" id="seal_ffmtman_search_nm" value="" style="width:110px" class="text_search" onkeydown="if(event.keyCode==13){event.returnValue = false;javascript:searchSealPerson();}"/><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchSealPerson();" class="cp" alt="search" />
	                            <span id="ffmtman">&nbsp;&nbsp;<c:out value='${contractLom.seal_ffmtman_nm}'/>/<c:out value='${contractLom.seal_ffmtman_jikgup_nm}'/>/<c:out value='${contractLom.seal_ffmt_dept_nm}'/></span>
                            </td>
	                    </c:when>
	                    <c:otherwise>
	                        <th><spring:message code="clm.page.field.contract.consultation.detail.signplannernm"/></th> <!-- 날인담당자 -->
	                        <td colspan="3"><c:out value='${contractLom.sign_plndman_nm}'/>/<c:out value='${contractLom.sign_plndman_jikgup_nm}'/>/<c:out value='${contractLom.sign_plnd_dept_nm}'/></td>
	                    </c:otherwise>
	                </c:choose>     
	            </tr>
	            <c:if test="${contractLom.seal_mthd=='C02102'}"> <!-- 서명일 떄 -->
		            <tr>
		                <th><spring:message code="clm.page.field.contract.conclusion.detail.signmannm"/><span class="astro">*</span></th> <!-- 서명자(당사) -->
		                <td colspan="5">
	                        <input type="hidden" name="signman_id" id="signman_id" value="<c:out value='${contractLom.signman_id}'/>" alt="<spring:message code="clm.page.field.contract.conclusion.detail.signmannm"/>"/>
	                        <input type="hidden" name="signman_jikgup_nm" id="signman_jikgup_nm" value="<c:out value='${contractLom.signman_jikgup_nm}'/>" />
	                        <input type="hidden" name="signman_nm" id="signman_nm" value="<c:out value='${contractLom.signman_nm}'/>" alt="<spring:message code="clm.page.field.contract.conclusion.detail.signmannm"/>"/>
	                        <input type="hidden" name="sign_dept_nm" id="sign_dept_nm" value="<c:out value='${contractLom.sign_dept_nm}'/>" />
	                        <input type="text" name="signman_search_nm" id="signman_search_nm" value="" style="width:102px" class="text_search" alt="<spring:message code="clm.page.field.contract.conclusion.detail.signmannm"/>" onKeyDown="if(event.keyCode==13){event.returnValue = false;javascript:searchEmployee('sign');}"/><img id="signman_search_nmImg" src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchEmployee('sign');" class="cp" alt="search" />
	                        <span id="signman">&nbsp;&nbsp;<c:out value='${contractLom.signman_nm}'/>/<c:out value='${contractLom.signman_jikgup_nm}'/>/<c:out value='${contractLom.sign_dept_nm}'/></span>
	        
		                </td>
		                <th><spring:message code="clm.page.field.contract.conclusion.detail.signday"/></th> <!-- 서명일(당사) -->
		                <td>
		                   <input type="text" name="signday" id="signday" value="<c:out value='${contractLom.signday}'/>"  class="text_calendar02" alt="<spring:message code="clm.page.field.contract.conclusion.detail.signday"/>"/>
		                </td>
		            </tr>
		<%--            </c:if> --%>
		            <tr>
		                <th><spring:message code="clm.page.field.contract.conclusion.detail.oppntsignmannm"/></th> <!-- 서명자(상대) -->
		                <td>
                            <input type="text" name="oppnt_signman_nm" id="oppnt_signman_nm" value="<c:out value='${contractLom.oppnt_signman_nm}' escapeXml="false"/>"  required maxlength="100" class="text_full" alt="<spring:message code="clm.page.field.contract.conclusion.detail.oppntsignmannm"/>"/>
                        </td>   
		                <th><spring:message code="clm.page.field.choosesealperson.jikgupnm"/></th> <!-- 직급 -->
		                <td>
                            <input type="text" name="oppnt_signman_jikgup" id="oppnt_signman_jikgup" value="<c:out value='${contractLom.oppnt_signman_jikgup}' escapeXml="false"/>" required class="text_full" maxlength="50" alt="<spring:message code="clm.page.field.choosesealperson.jikgupnm"/>" />
		                </td>
		                <th><spring:message code="clm.page.field.contract.conclusion.detail.signdeptnm"/></th> <!-- 부서명 -->
		                <td>
		                    <input type="text" name="oppnt_signman_dept" id="oppnt_signman_dept" value="<c:out value='${contractLom.oppnt_signman_dept}' escapeXml="false"/>" required class="text_full" maxlength="50" alt="<spring:message code="clm.page.field.contract.conclusion.detail.signdeptnm"/>" />
		                </td>
		                <th><spring:message code="clm.page.field.contract.conclusion.detail.oppntsignday"/></th> <!-- 서명일(상대) -->
		                <td>
		                    <input type="text" name="oppnt_signday" id="oppnt_signday" value="<c:out value='${contractLom.oppnt_signday}'/>" required class="text_calendar02" alt="<spring:message code="clm.page.field.contract.conclusion.detail.oppntsignday"/>"/>
		                </td>
		            </tr>
	            </c:if>
	            <tr>
	                <th><spring:message code="clm.page.field.contract.consultation.detail.contractrespmannm"/></th>
	                <td colspan="5"><c:out value='${contractLom.cntrt_respman_nm}'/>/<c:out value='${contractLom.cntrt_respman_jikgup_nm}'/>/<c:out value='${contractLom.cntrt_resp_dept_nm}'/></td>
	            </tr>
	            <tr>
	                <th><spring:message code="clm.page.field.contract.conclusion.detail.cpyregnm"/></th><!-- 사본등록자 -->
	                <!-- <td colspan="5"><c:out value='${contractCommand.session_user_nm}'/>/<c:out value='${contractCommand.session_jikgup_nm}'/>/<c:out value='${contractCommand.session_dept_nm}'/></td>//-->
	                <!-- 사본등록자가 로그인 세션정보로 되어있어 DB 정보로 변경함 chahyunjin 2012.02.14 -->
	                <td colspan="5"><c:out value='${contractLom.cpy_regman_nm}'/>/<c:out value='${contractLom.cpy_regman_jikgup_nm}'/>/<c:out value='${contractLom.cpy_reg_dept_nm}'/></td>
	                <th><spring:message code="clm.page.field.contract.conclusion.detail.cpyregday"/></th>
	                <td><c:out value='${contractLom.cpy_regday}'/><input type="hidden" name="cpy_reqday" value="<c:out value='${contractLom.cpy_regday}'/>"/></td>
	            </tr>
	            <tr>
	                <th><spring:message code="clm.page.field.contract.conclusion.detail.coclusioncopy"/></th>
	                <td colspan="7">※ <spring:message code="clm.page.msg.manage.announce007" />
	                    <iframe src="<c:url value='/clm/blank.do' />" id="fileContractCopy" name="fileContractCopy" frameborder="0" width="100%" height="50px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
	                </td>
	            </tr>
	            <tr>
	                <th>
                        <spring:message code="clm.page.field.myapproval.etcinfo"/>  <img src="<%=IMAGE %>/common/step_q.gif" alt="info" title="<spring:message code="clm.page.msg.manage.announce064" htmlEscape="true" />" />
                        <span class="tL">
                            <span class="btn_all_b" onclick='javascript:openChooseClient();'><span class="add"></span><a><spring:message code='clm.page.msg.manage.add' /></a></span>
                       </span>
                    </th>
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
			
			<div class="t_titBtn">
				<div class="btn_wrap_c" id="conclusion_btn2">
					<span class="btn_all_w"><span class="save"></span><a href="javascript:Save();"><spring:message code="clm.page.msg.manage.registrate" /></a></span>
					<span class="btn_all_w"><span class="tsave"></span><a href="javascript:tempSave();"><spring:message code='clm.page.button.contract.imsisave' /></a></span>
					<span class="btn_all_p"><span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /></a></span>
					<!-- <span class="btn_all_w"><span class="reset"></span><a href="javascript:goDrop();"><spring:message code='clm.page.button.contract.drop' /></a></span> -->			
				</div>
				<div class="btn_wrap_c" id="conclusion_btn_list2">
				    <span class="btn_all_p"><span class="print"></span><a href="javascript:openPrint();"><spring:message code='las.page.button.lawconsult.print' /></a></span>
					<span class="btn_all_p"><span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /></a></span>			
				</div>
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