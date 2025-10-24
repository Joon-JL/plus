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
<%@ page import="com.sec.clm.manage.dto.ConsultationForm" %>
<%
	ListOrderedMap lomReq 	 	=  (ListOrderedMap)request.getAttribute("lomReq");
    String reqOperdiv = (String)lomReq.get("req_operdiv"); 
	/*
	화면별로 특화정보 <table> 구성이 틀려서 페이지 플래그로 구분하기위해
	 PG001 : 계약체결
	 PG002 : 계약등록
	*/
	HttpSession ss_special_page_flag = request.getSession(false);
	ss_special_page_flag.setAttribute("special_page_flag","PG001");
	
	ListOrderedMap contractLom      =  (ListOrderedMap)request.getAttribute("contractLom");
	ListOrderedMap lomRe = (ListOrderedMap)request.getAttribute("lomRe"); // 회신 정보
	
    ConsultationForm command = (ConsultationForm)request.getAttribute("command");
%>
<%--
/**
 * 파  일  명 	 : Consultation_d_new.jsp
 * 프로그램명 	 : 체결품의 작성화면 - 품의 상신 전 체결 정보를 입력하는 페이지.
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
<!--계약관리일 경우  -->
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet"></link>
<!-- [if IE]><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
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

$(window).load(function(){
	if( $("#cont_ifr_1").length > 0 ) {
		document.getElementById('cont_ifr_1').contentWindow.document.body.innerHTML = document.getElementById('cont_textaea_1').value;
	}  	
   });
   
   
var tx = true;

$(document).ready(function(){

	var frm = document.frm;
	var cntrt_id = "";
	var status_cd = frm.prgrs_status.value;
	
	//사전품의 승인일
    initCal("bfhdcstn_apbtday");
	
	
    /************* Consultation_d_master.jsp(계약정보)에 있던 script *****************/
    paymentGbnChange(true);
    
    // 이력 첨부파일
	contractHisList();		//Sungwoo added 2014-09-01
    
    /************* changeContract() 에 있는 script *****************/
    if(frm.progressapprovalYN.value == "Y") {
        alert("<spring:message code='clm.msg.alert.contract.consultation.progressapproval'/>");
        return;
    }
    
    if(frm.prgrs_status.value == "C04211" || frm.prgrs_status.value == "C04207" || frm.prgrs_status.value == "C04212"){
        initCal("cnclsn_plndday");
        initCal("cntrtperiod_startday");
        initCal("cntrtperiod_endday");
    }
    initAttach();
    setProgressApprovalInfo("N");
    setInitControl();
	
});

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
	}else if(flag == "mtn"){ //사전품의발의자
		obj = frm.bfhdcstn_mtnman_nm;
	}else if(flag == "apbt"){ //사전품의 승인자
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
    	frm.sign_plndman_search_nm.value= '';
    	frm.sign_plnd_dept_nm.value 	= obj.department;
    	frm.sign_plndman_jikgup_nm.value = obj.title;
    	
    	$('#plndman').html('');
    	$('#plndman').append(obj.cn+'/'+obj.title+'/'+obj.department);
    	
    } else if(srch_user_target=="contract"){
    	frm.cntrt_respman_id.value 			= obj.epid;
    	frm.cntrt_respman_nm.value 			= obj.cn;
       	frm.cntrt_respman_search_nm.value	= '';
    	frm.cntrt_respman_jikgup_nm.value 	= obj.title;
    	frm.cntrt_resp_dept_nm.value 		= obj.department;
    	frm.cntrt_resp_dept.value			= obj.departmentnumber;
    	 
    	 var options = { 
			    	url:"<c:url value='/clm/manage/consultation.do?method=getOrgnzCd' />",
			     	type:"post",
			    	dataType:"json",
			    	success:function(returnJson, returnMessage){
			    	    	$('#respman').html('');
			    	    	$('#respman').append('&nbsp;&nbsp;'+ frm.cntrt_respman_nm.value +'/'+frm.cntrt_respman_jikgup_nm.value+'/'+frm.cntrt_resp_dept_nm.value);
			    		
			    	}
    	       };
    		
    	    	
    		$("#frm").ajaxSubmit(options);	
		    
 
    	
    	    
    }else if(srch_user_target=="mtn"){ //사전품의 발의자
    	frm.bfhdcstn_mtnman_id.value 			= userInfo.epid;		//사전품의발의자 id
	    frm.bfhdcstn_mtnman_nm.value 			= userInfo.cn;; 		//발의자 이름
	    frm.bfhdcstn_mtnman_jikgup_nm.value 	= userInfo.title;  		//직급
	    frm.bfhdcstn_mtn_dept_nm.value			= userInfo.department;	//부서
	    	    
	    $('#bfhdcstn_mtnman_jikgup_nm_span').html(userInfo.title);
	    $('#bfhdcstn_mtn_dept_nm_span').html(userInfo.department);
    }else if(srch_user_target=="apbt"){ //사전품의 승인자
    	frm.bfhdcstn_apbtman_id.value          = userInfo.epid;		//사전품의승인자정id
   	 	frm.bfhdcstn_apbtman_nm.value          = userInfo.cn;; 		//발의자 이름
   	 	frm.bfhdcstn_apbtman_jikgup_nm.value   = userInfo.title;  		//직급
   	 	frm.bfhdcstn_apbt_dept_nm.value        = userInfo.department;	//부서
   	 
   	 	$('#bfhdcstn_apbtman_jikgup_nm_span').html(userInfo.title);
	    $('#bfhdcstn_apbt_dept_nm_span').html(userInfo.department);
    }
}

//validation check 후 실행
function openChooseContract_temp(){
	var frm = document.frm;

	if(!validateForm(frm)) return;
	
	if(frm.progressapprovalYN.value == "Y") {
		alert("<spring:message code='clm.msg.alert.contract.consultation.progressapproval'/>");
		return;
	}
	
	//결재 진행여부 설정
	setProgressApprovalInfo("Y");
	
	//2013-02-14 체결품의 프로세스 간소화(modify by hjkim) 계약선택팝업에서 수행사던것을 여기서 한다.
    setApprovalContractInfo(frm.cntrt_id.value);
	
	viewHiddenProgress(true);
	var options = {
		url: "<c:url value='/clm/manage/consultation.do?method=modifyConsultationNew_temp' />",
		type: "post",
    	dataType:"json",
    	success:function(responseText, statusText){
			viewHiddenProgress(false);

			if(responseText.returnCd == undefined){
				//정상적으로 처리 되지 않았습니다. 시스템 관리자에게 문의 바랍니다.
				alert("<spring:message code='clm.page.msg.manage.announce157'/>");
			}else{
				if(responseText.returnCd == "Y"){
					//결재상신팝업 호출
					openApprovalPop();
				}else{
					alert(responseText.returnMsg);
				}
			}
		}	
	};

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

						$("#frm").ajaxSubmit(options);
					});
				}else{
					$("#frm").ajaxSubmit(options);
				}
			});
		});
	});
}
//결재 팝업 호출
function openApprovalPop(){
	var frm = document.frm;
	PopUpWindowOpen('', "1024", "635", true, "popUpChooseContract");
	frm.action 					 = "<c:url value='/clm/manage/consultation.do' />";
	frm.method.value			 = "makeApprovalHtmlNew";
	frm.target 					 = "popUpChooseContract";
	frm.submit();
}

/*
 * 팝업오픈-모달리스
 */
function PopUpWindowOpen(surl, popupwidth, popupheight, bScroll, popName) {
	
	paramBScroll = ( bScroll ) ? "yes" : "no";

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
	
	
	var Feture = "toolbar=0, location=0, status=1, menubar=0, scrollbars="+paramBScroll+", resizable=yes, top="+Top+", left="+Left+", width="+popupwidth+", height="+popupheight;
	PopUpWindow = window.open(surl, popName , Feture);

	PopUpWindow.focus();
	
}


/*
 * 팝업오픈-연관계약
 */
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
	
	
	var Feture = "toolbar=0, location=0, status=1, menubar=0, scrollbars=yes, resizable=no, top="+Top+", left="+Left+", width="+popupwidth+", height="+popupheight;
	
	PopUpWindow = window.open(surl, popName , Feture);

	PopUpWindow.focus();
	
}

/*
 * 첨부파일초기화
 */
function initAttach(){
	
	var flag = "<c:out value="${lomRe.re_c_exists}"/>" ; // 회신 계약서 존재여부
		
	// 회신 계약서가 없을 경우 최종본 의뢰시 계약서와 첨부/별첨을 보여준다.
	if(flag=="N") {
		
		frm.target          		= "fileConsultationContract";
	    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    		= "forwardClmsFilePage";
	    frm.file_midclsfcn.value 	= "F01202";
	    frm.file_smlclsfcn.value	= "F0120201";
	    frm.ref_key.value   		= frm.cntrt_id.value + "@" + frm.cnsdreq_id.value;
	    frm.fileInfoName.value 		= "fileInfos";
	    frm.view_gbn.value			= "download";
	    frm.fileFrameName.value 	= "fileConsultationContract";
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
	    
	    //계약-기타_체결본
	    frm.target          		= "fileConsultationEtc2";
	    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value   		 	= "forwardClmsFilePage";
	    frm.file_midclsfcn.value 	= "F01202";
	    frm.file_smlclsfcn.value 	= "F0120205";
	    frm.ref_key.value   		= frm.cntrt_id.value + "@" + frm.cnsdreq_id.value;
	    frm.fileInfoName.value 		= "fileInfos2";
	    frm.view_gbn.value			= "download";
	    frm.fileFrameName.value 	= "fileConsultationEtc2";
	    frm.submit();
	    
	}
	// 회신 계약서가 있을 경우 최종 회신시 계약서와 첨부/별첨을 보여준다.
	else {
		//계약-계약서파일(회신계약서파일(종합회신))
        frm.target                  = "fileConsultationContract";
        frm.action                  = "<c:url value='/clms/common/clmsfile.do' />";
        frm.method.value            = "forwardClmsFilePage";
        frm.file_midclsfcn.value    = "F01202";
        frm.file_smlclsfcn.value    = "F0120202";
        frm.ref_key.value           = frm.cntrt_id.value + "@" + frm.cnsdreq_id.value;
        frm.fileInfoName.value      = "fileInfos";
        frm.view_gbn.value          = "download";
        frm.fileFrameName.value     = "fileConsultationContract";
        frm.submit();
        
        //계약-별첨
        frm.target                  = "fileConsultationEtc";
        frm.action                  = "<c:url value='/clms/common/clmsfile.do' />";
        frm.method.value            = "forwardClmsFilePage";
        frm.file_midclsfcn.value    = "F01202";
        frm.file_smlclsfcn.value    = "F0120209";
        frm.ref_key.value           = frm.cntrt_id.value + "@" + frm.cnsdreq_id.value;
        frm.fileInfoName.value      = "fileInfos5";
        frm.view_gbn.value          = "download";
        frm.fileFrameName.value     = "fileConsultationEtc";
        frm.submit();
        
        
         //계약-기타_체결본
	    frm.target          		= "fileConsultationEtc2";
	    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value   		 	= "forwardClmsFilePage";
	    frm.file_midclsfcn.value 	= "F01202";
	    frm.file_smlclsfcn.value 	= "F0120206";
	    frm.ref_key.value   		= frm.cntrt_id.value + "@" + frm.cnsdreq_id.value;
	    frm.fileInfoName.value 		= "fileInfos2";
	    frm.view_gbn.value			= "download";
	    frm.fileFrameName.value 	= "fileConsultationEtc2";
	    frm.submit();
	}
    
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
    getClmsFilePageCheck('frm');
        
    //사전검토정보-첨부
    frm.target          		= "fileConsultationPre";
    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
    frm.method.value    		= "forwardClmsFilePage";
    frm.file_bigclsfcn.value 	= "F012";
    frm.file_midclsfcn.value 	= "F01201";
    frm.file_smlclsfcn.value 	= "F0120101";
    frm.ref_key.value   		= frm.cntrt_id.value;
    frm.fileInfoName.value 		= "fileInfos3";
    frm.view_gbn.value			= "modify";
    frm.fileFrameName.value 	= "fileConsultationPre";
    frm.submit();
    
    //단가내역-첨부
	    frm.target          		= "fileConsultationUntPrc";
	    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    		= "forwardClmsFilePage";
	    frm.file_bigclsfcn.value 	= "F012";
	    frm.file_midclsfcn.value 	= "F01202";
	    frm.file_smlclsfcn.value 	= "F0120211";
	    frm.ref_key.value   		= frm.cntrt_id.value;
	    frm.fileInfoName.value 		= "fileInfos4";
	    frm.view_gbn.value			= "modify";
	    frm.fileFrameName.value 	= "fileConsultationUntPrc";
	    frm.submit();
    
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
    	frm.action="<c:url value='/clm/manage/consultation.do' />";
    	frm.target="_self";
    	frm.method.value="modifyConsultationStatusNew";
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
	} else {
		if("<c:out value='${command.session_user_id}'/>" == frm.reqman_id.value) {
			$('div[id^=consultation_btn]').attr("style", "display:block");
			$('div[id^=consultation_btn_list]').attr("style", "display:none");
		} else {
			$('div[id^=consultation_btn]').attr("style", "display:none");
			$('div[id^=consultation_btn_list]').attr("style", "display:block");
		}
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

<%--버튼액션시작--%>
/**
 * 임시저장
 */
function tempSave() {
	 
	var frm = document.frm;
	frm.work_flag.value="LIST";
	if(frm.progressapprovalYN.value=="Y") {
		alert("<spring:message code='clm.msg.alert.contract.consultation.progressapproval'/>");
		return;
	}
	
	if(!confirm("<spring:message code="clm.page.msg.manage.announce137" />")) return;
	
	// 단가로체결일경우 단가내역요약 필수
    if(frm.cntrt_untprc.checked){
        if(frm.cntrt_untprc_expl.value==""){
            alert("<spring:message code="clm.page.msg.manage.announce074" />") ;
            return ;
        }
    }
	
	var exe_cnt = 0;
	if($('#payment_gbn > option:selected').val() =="C02001") {	//지불/수금
		exe_cnt = $('#executionTbody tr').length + $('#executionTbody2 tr').length + $('#executionTbody3 tr').length;
		if(exe_cnt==0){
			alert("<spring:message code="clm.page.msg.manage.announce152" />");
			return;
		}
	} else if($('#payment_gbn > option:selected').val() =="C02002") {	//지불
	} else if($('#payment_gbn > option:selected').val() =="C02003") {	//수금
	}
	
	if($('#sign_plndman_id').val() == ''){
		alert("<spring:message code="clm.page.msg.manage.announce105" />");
		return;
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
							frm.method.value = "modifyConsultationNew";	
							frm.target = "_self";		
							frm.tempsave_flag.value='button';
							frm.action = "<c:url value='/clm/manage/consultation.do' />";
						    frm.submit();
						    
						    viewHiddenProgress(true) ;
						});					
					}else{
						frm.method.value = "modifyConsultationNew";	
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
 *체결품의버튼 
 */
function checkApprovalValidation() {
	var frm = document.frm;
	
	//김재원 수정 결재를 올리기 전에 저장이 된다는 내용으로 확인 창 뜨고 진행 함.
	if(!confirm("<spring:message code='clm.page.msg.manage.apploval1000' />")) return;

	if(frm.progressapprovalYN.value == "Y") {
		alert("<spring:message code='clm.msg.alert.contract.consultation.progressapproval'/>");
		return;
	}
	    
	// 김재원 체크 로직 추가 함.
	if(frm.cntrtperiod_startday.value != "" && frm.cntrtperiod_endday.value == ""){
		alert("<spring:message code="clm.page.msg.manage.announce1000" />");
		return;
	}
	
	// 김재원 체크 로직 추가 함.
	if(frm.cntrtperiod_startday.value == "" && frm.cntrtperiod_endday.value != ""){
		alert("<spring:message code="clm.page.msg.manage.announce1001" />");
		return;
	}
    // 단가로체결일경우 단가내역요약 필수
    if(frm.cntrt_untprc.checked){
        if(frm.cntrt_untprc_expl.value==""){
            alert("<spring:message code="clm.page.msg.manage.announce074" />") ;
            return ;
        }
    }
	
	if($('#payment_gbn > option:selected').val() == "C02001") {	//지불/수금
		if(! isCntrtAmt()) { return; }
		
		if($('#cntrt_amt').val() == ''){
			if(!$("[name=cntrt_untprc]").is(":checked")){
				alert("<spring:message code="clm.page.msg.manage.announce038" />");	
				$('#cntrt_amt').focus();
				return;
			};
		}
	} else if($('#payment_gbn > option:selected').val() == "C02002") {	//지불
		if(! isCntrtAmt()) { return; }
		
		if($('#cntrt_amt').val() == ''){
			if(!$("[name=cntrt_untprc]").is(":checked")){
				alert("<spring:message code="clm.page.msg.manage.announce038" />");	
				$('#cntrt_amt').focus();
				return;
			}
		}
	} else if($('#payment_gbn > option:selected').val() == "C02003") {	//수금
		if(! isCntrtAmt()) { return; }
		
		if($('#cntrt_amt').val() == ''){
			if(!$("[name=cntrt_untprc]").is(":checked")){
				alert("<spring:message code="clm.page.msg.manage.announce038" />");	
				$('#cntrt_amt').focus();
				return;
			}
		}
	}
	
	if($('#sign_plndman_id').val() == ''){   // 서명 예정자
		alert("<spring:message code="clm.page.msg.manage.announce105" />");
		return;
	}
	
	// 계약담당자.
	if($('#cntrt_respman_id').val() == ''){
		alert("<spring:message code="clm.page.msg.manage.announce043" />");
		return;
	}
	
	if(!validateForm(frm)) return;
	viewHiddenProgress(true);
	frm.method.value = "listConsultationApprovalEsbValidation";
	var options = {
		url: "<c:url value='/clm/manage/consultation.do' />",
		type: "post",
		dataType: "json",
		success: function(returnJson,returnMessage) {
			if(returnJson.result == "Y") {
				viewHiddenProgress(false);
				openChooseContract_temp();
			} else {
				viewHiddenProgress(false);
				alert(returnJson.message);				
			}
		}
	};
	$("#chkVal").attr("style","display:none");
	$("#chkVal2").attr("style","display:none");
	$("#frm").ajaxSubmit(options);
	
}

function isCntrtAmt() {
	if($('#cntrt_amt').val() != '' && $('#cntrt_amt').val() > 0 ){
		return true;
	} else {
		alert("<spring:message code="clm.page.msg.manage.announce038" />");	
		return false;
	}
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
	// 2014-02-05 Kevin. 소수점 구분 위해 "." 입력 허용함.
    var Re = /[^0-9.]/g;
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
		}else if("C03203" == type){//변경전/해지전
			$("#rel_defn").html("<option value=<spring:message code='clm.page.msg.manage.preChg' />><spring:message code='clm.page.msg.manage.preChg' /></option><option value=<spring:message code='clm.page.msg.manage.befCalncel' />><spring:message code='clm.page.msg.manage.befCalncel' /></option>");
		}else{//선택없음
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
			 PopUpWindowOpen2("/clm/manage/myContractPop.do?method=listMyContract&status_mode=rel&except_cntrt_id="+v_except_cntrt_id , 1100, 800, false,"popUprelated");
			 frm.target = "popUprelated";
			} else {
			 alert("<spring:message code="clm.page.msg.manage.announce119" />");
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
			$("#parent_cntrt_id").val(cntrt_id);
			if (srch_arg !=null && srch_arg != "")
			{
				$("#rel_type").val(srch_arg);
			}
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
 		
	function openArbitraryPop(){
		PopUpWindowOpen('/las/board/notice.do?method=detailDecideArbitrarilyRe&menu_id=20130319155746339_0000000377', 840, 600, true);
	}

	  
	function paymentGbnChange(init){         
		//payment_gbn 지수불 구분이 해당사항 없음이면 계약 금액 은 입력 불가 처리
		//계약금액,통화단위 ,계약단가 비활성화
        if($('#payment_gbn > option:selected').val() == "C02004" ){
            
            //cntrt_amt 계약금액            
            $("#cntrt_amt").val("");
            $("input[name=cntrt_amt]").attr('disabled', true);
            $("input[name=cntrt_untprc]").attr('checked', false);
            $("#crrncy_unit").attr('disabled', true);
            $("#crrncy_unit").html("<option value=><spring:message code='clm.page.field.srch.payment_gbn_value4'/></option>");
            $("input[name=cntrt_untprc]").attr('disabled', true);
            $("#trCntrtUntprc").hide();
            $("#cntrt_untprc_expl").val("");
        }else{
            
            $("input[name=cntrt_amt]").attr('disabled', false);
            getCodeSelectByUpCd("frm", "crrncy_unit", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=A&combo_grp_cd=C5&combo_type=S&combo_del_yn=N&combo_selected=<c:out value='${contractLom.crrncy_unit}'/>');
            $("#input[name=cntrt_untprc]").attr('disabled', false);
            
            $("#crrncy_unit").attr('disabled', false);
            $("input[name=cntrt_untprc]").attr('disabled', false);
            
            if(frm.page_gbn.value == 'modify'){
                if($("#cntrt_untprc_expl").val()){
                    $("#trCntrtUntprc").show();
                    $("[name=cntrt_untprc]").attr('checked', true);
                }
            }
        }
    }
	  
    /**
     * 계약단가 활성화시 
     */
    function clickCntrtUntprc(){
         var frm = document.frm;
         if($("[name=cntrt_untprc]").is(":checked")){ //단가로 체결
             $("#trCntrtUntprc").show();
         }else{ //계약으로 체결
             frm.cntrt_untprc_expl.value = "";
             $("#trCntrtUntprc").hide();
         }       
     }
    
    
  //첨부파일
    function attachHisPage(){
    var frm = document.frm;
    
        //의뢰-계약서파일 CONSULT
         $('input[name^=fileInfos_cntrt]').each(function(index){
            $fileInfos_cntrt = $(this).attr("name");
            $fileList_cntrt = $(this).prev().attr("name");
            $cntrt_cnsdreq_id = $(this).next().val();
            
            frm.target          = $fileList_cntrt;
            frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
            frm.method.value    = "forwardClmsFilePage";
            frm.fileInfoName.value = $fileInfos_cntrt;
            frm.fileFrameName.value = $fileList_cntrt;
            //의뢰 계약서파일
            frm.file_bigclsfcn.value = "F012";
            frm.file_midclsfcn.value = "F01202";
            frm.file_smlclsfcn.value = "F0120201";
            frm.ref_key.value = "<c:out value='${lomReq.cntrt_id}'/>@"+$cntrt_cnsdreq_id;
            getClmsFilePageCheck('frm');
            //frm.submit();
        });
         //의뢰-첨부/별첨파일 CONSULT
         $('input[name^=fileInfos_append]').each(function(index){
            $fileInfos_append = $(this).attr("name");
            $fileList_append = $(this).prev().attr("name");
            $append_cnsdreq_id = $(this).next().val();
            
            frm.target          = $fileList_append;
            frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
            frm.method.value    = "forwardClmsFilePage";
            frm.fileInfoName.value = $fileInfos_append;
            frm.fileFrameName.value = $fileList_append;
            //의뢰 계약서파일
            frm.file_bigclsfcn.value = "F012";
            frm.file_midclsfcn.value = "F01202";
            frm.file_smlclsfcn.value = "F0120208";
            frm.ref_key.value = "<c:out value='${lomReq.cntrt_id}'/>@"+$append_cnsdreq_id;
            getClmsFilePageCheck('frm');
            //frm.submit();
        });
         
       //의뢰-기타파일 CONSULT
         $('input[name^=fileInfos_other]').each(function(index){
            $fileInfos_other = $(this).attr("name");
            $fileList_other = $(this).prev().attr("name");
            $other_cnsdreq_id = $(this).next().val();
            
            frm.target          = $fileList_other;
            frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
            frm.method.value    = "forwardClmsFilePage";
            frm.fileInfoName.value = $fileInfos_other;
            frm.fileFrameName.value = $fileList_other;
            //의뢰 계약서파일
            frm.file_bigclsfcn.value = "F012";
            frm.file_midclsfcn.value = "F01202";
            frm.file_smlclsfcn.value = "F0120205";
            frm.ref_key.value = "<c:out value='${lomReq.cntrt_id}'/>@"+$other_cnsdreq_id;
            getClmsFilePageCheck('frm');
            //frm.submit();
        });
       
         //outlook-파일 CONSULT
         $('input[name^=fileInfos_fileListOut]').each(function(index){
            $fileInfos_fileListOut = $(this).attr("name");
            $fileList_fileListOut = $(this).prev().attr("name");
            $fileListOut_cnsdreq_id = $(this).next().val();
            
            frm.target          = $fileList_fileListOut;
            frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
            frm.method.value    = "forwardClmsFilePage";
            frm.fileInfoName.value = $fileInfos_fileListOut;
            frm.fileFrameName.value = $fileList_fileListOut;
            //의뢰 계약서파일
            frm.file_bigclsfcn.value = "O001";
		    frm.file_midclsfcn.value = "O0011";
		    frm.file_smlclsfcn.value = "O00111";
            frm.view_gbn.value = "download";
            frm.ref_key.value = "<c:out value='${lomRq.cntrt_id}'/>@"+$fileListOut_cnsdreq_id;
            getClmsFilePageCheck('frm');
            //frm.submit();
        });

         //회신-계약서파일 CONSULT
         $('input[name^=reply_fileInfos_cntrt]').each(function(index){
            $reply_fileInfos_cntrt = $(this).attr("name");
            $reply_fileList_cntrt = $(this).prev().attr("name");
            $reply_cntrt_cnsdreq_id = $(this).next().val();
            
            frm.target          = $reply_fileList_cntrt;
            frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
            frm.method.value    = "forwardClmsFilePage";
            frm.fileInfoName.value = $reply_fileInfos_cntrt;
            frm.fileFrameName.value = $reply_fileList_cntrt;
            //회신 계약서파일
            frm.file_bigclsfcn.value = "F012";
            frm.file_midclsfcn.value = "F01202";
            frm.file_smlclsfcn.value = "F0120202";
            frm.ref_key.value = "<c:out value='${command.cntrt_id}'/>@"+$reply_cntrt_cnsdreq_id;
            getClmsFilePageCheck('frm');
            //frm.submit();
        });
         //회신-첨부/별첨파일 CONSULT
         $('input[name^=reply_fileInfos_append]').each(function(index){
            $reply_fileInfos_append = $(this).attr("name");
            $reply_fileList_append = $(this).prev().attr("name");
            $reply_append_cnsdreq_id = $(this).next().val();
            
            frm.target          = $reply_fileList_append;
            frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
            frm.method.value    = "forwardClmsFilePage";
            frm.fileInfoName.value = $reply_fileInfos_append;
            frm.fileFrameName.value = $reply_fileList_append;
            //회신 계약서파일
            frm.file_bigclsfcn.value = "F012";
            frm.file_midclsfcn.value = "F01202";
            frm.file_smlclsfcn.value = "F0120209";
            frm.ref_key.value = "<c:out value='${command.cntrt_id}'/>@"+$reply_append_cnsdreq_id;
            getClmsFilePageCheck('frm');
            //frm.submit();
        });
         
         $('input[name^=reply_fileInfos_other]').each(function(index){
             $reply_fileInfos_other = $(this).attr("name");
             $reply_fileList_other = $(this).prev().attr("name");
             $reply_other_cnsdreq_id = $(this).next().val();
             
             frm.target          = $reply_fileList_other;
             frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
             frm.method.value    = "forwardClmsFilePage";
             frm.fileInfoName.value = $reply_fileInfos_other;
             frm.fileFrameName.value = $reply_fileList_other;
             //회신 계약서파일
             frm.file_bigclsfcn.value = "F012";
             frm.file_midclsfcn.value = "F01202";
             frm.file_smlclsfcn.value = "F0120206";
             frm.ref_key.value = "<c:out value='${lomReq.cntrt_id}'/>@"+$reply_other_cnsdreq_id;
             getClmsFilePageCheck('frm');   
             //frm.submit();
         });   


         //승인-파일 
         $('input[name^=approve_fileInfos]').each(function(index){
            $approve_fileInfos = $(this).attr("name");
            $approve_fileList = $(this).prev().attr("name");
            $approve_cnsdreq_id = $(this).next().val();
            
            frm.target          = $approve_fileList;
            frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
            frm.method.value    = "forwardClmsFilePage";
            frm.fileInfoName.value = $approve_fileInfos;
            frm.fileFrameName.value = $approve_fileList;
            //회신 계약서파일
            frm.file_bigclsfcn.value = "F012";
            frm.file_midclsfcn.value = "F01203";
            frm.file_smlclsfcn.value = "";
            frm.ref_key.value = "<c:out value='${command.cntrt_id}'/>";
            getClmsFilePageCheck('frm');
            //frm.submit();
        });
       //사전검토정보-파일 
         $('input[name^=info_fileInfos]').each(function(index){
            $info_fileInfos = $(this).attr("name");
            $info_fileList = $(this).prev().attr("name");
            
            frm.target          = $info_fileList;
            frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
            frm.method.value    = "forwardClmsFilePage";
            frm.fileInfoName.value = $info_fileInfos;
            frm.fileFrameName.value = $info_fileList;
            //회신 계약서파일
            frm.file_bigclsfcn.value = "F012";
            frm.file_midclsfcn.value = "F01201";
            frm.file_smlclsfcn.value = "F0120101";
            frm.ref_key.value = "<c:out value='${command.cntrt_id}'/>";
            getClmsFilePageCheck('frm');
            //frm.submit();
        });
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
		      	  
//-->	
</script>
</head>
<body>
<!-- container -->
<div id="wrap">
	<div id="container">
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
			<div id="content_in">
				<form:form name="frm" id="frm" method="post" autocomplete="off">
				<input type="hidden" name="contents" id="contents" />
				<!-- 리스트 검색 조건시작  -->
				<input type="hidden" name="list_mode" value="<c:out value='${command.list_mode}'/>"/>
				<input type="hidden" name="srch_resp_dept" value="<c:out value='${command.srch_resp_dept}'/>"/>				<!-- 담당부서코드 -->
				<input type="hidden" name="srch_oppnt_cd" value="<c:out value='${command.srch_oppnt_cd}'/>"/>   				<!-- 계약상대방코드 -->
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
				<input type="hidden" name="srch_if_sys_cd" value="<c:out value='${command.srch_if_sys_cd}'/>" />						<!-- 연계시스템 -->
				<input type="hidden" name="srch_cnclsnpurps_bigclsfcn" value="<c:out value='${command.srch_cnclsnpurps_bigclsfcn}'/>" />	<!-- 체결목적-->
				<input type="hidden" name="ismycontract" id="ismycontract" value="<c:out value='${command.ismycontract}'/>"/>
				<input type="hidden" name="srch_step" value="<c:out value='${command.srch_step}'/>" />						<!-- 상태 -->
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
				<input type="hidden" name="fileInfos1"       value="" /> 		<!-- 표준계약서 파일 정보 -->
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
				<!-- 이행정보 hidden(기존 Consultation_d_exe.jsp에 있던 hidden) -->
				<input type="hidden" name="exec_seqno"    id="exec_seqno"    value="0" />
		        <input type="hidden" name="exec_status" id="exec_status"    value="" />
				
				<!-- 2013-02-14 체결품의 프로세스 간소화(modify by hjkim) 계약선택팝업(ChooseContact_p.jsp) 필수 변수를 셋팅한다 -->
				<input name="cntrt_id_arr" 		id="cntrt_id_arr" 		type="hidden" value="<c:out value='${lomReq.cntrt_id}'/>" />
				<input name="approval_yn_arr" 	id="approval_yn_arr" 	type="hidden" value="Y" />
		
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
			  	<div class="title_basic">
					<h4><spring:message code="clm.page.msg.manage.revReplInf" /> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'req-table');"/></h4>
				</div>
				<div class="btnwrap mt_22" style="display:block;">
				    <span class="btn_all_w" id="chkVal"><span class="sangsin"></span><a href="javascript:checkApprovalValidation();"><spring:message code="clm.page.button.contract.cosultationapproval"/></a></span>
				    <span class="btn_all_w"><span class="preview"></span><a href="javascript:openArbitraryPop();"><spring:message code="clm.page.msg.manage.conclRule" /></a></span><!-- 2012.08.24 제외->2012.11.28 다시적용(이효은요청) -->
					<span class="btn_all_w"><span class="modify"></span><a href="javascript:processCancel();"><spring:message code="clm.page.button.contract.rollback" /></a></span>
					<span class="btn_all_w"><span class="tsave"></span><a href="javascript:tempSave('button');"><spring:message code='clm.page.button.contract.imsisave' /></a></span>
					<span class="btn_all_w"><span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /></a></span>			
				</div>
				<div class="btnwrap mt_22" id="consultation_btn_list" style="display:none;">
					<span class="btn_all_w"><span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /></a></span>			
				</div>
			
		  	<table cellspacing="0" cellpadding="0" class="table-style01" id="req-table">
				<colgroup>              
                    <col width="12%" />
                    <col width="8%" />
                    <col width="22%" />
                    <col width="13%" />
                    <col width="16%" />
                    <col width="13%" />
                    <col width="16%" />                  
                </colgroup>
				<tr>
					<th><spring:message code="clm.page.field.manageRequest.reqTitle"/></th>
				  <td colspan="7"><c:out value='${lomReq.req_title}' default="" escapeXml="false"/></td>
				</tr>
				<tr class="lineAdd">
					<th><spring:message code="clm.page.field.manageRequest.demndmanNm"/></th>
					<td colspan="2">
						<c:out value="${lomReq.reqman_nm}" />/<c:out value="${lomReq.reqman_jikgup_nm}" />/<c:out value="${lomReq.req_dept_nm}" />						
					</td>
					<th><spring:message code="clm.page.field.manageRequest.demndDt"/></th>
					<td><c:out value="${lomReq.req_dt}" /></td>
					<th><spring:message code="clm.page.field.contract.request.returndt"/></th><!-- 회신요청일 RE_DEMNDDAY -->
					<td><c:out value="${lomReq.re_dt}" /></td>
				</tr>
				<tr>
                    <th class="borTz02"><spring:message code="clm.page.msg.manage.reviewer" /></th>
                    <td colspan="6">
						<c:out value="${reqReviewerList}" escapeXml="false"/>
					</td>
                </tr>
				<tr class="lineAdd">
					<th><spring:message code="clm.page.field.myapproval.etcinfo"/>  <img src="<%=IMAGE %>/common/step_q.gif" alt="info" title="<spring:message code="clm.page.msg.manage.announce064" htmlEscape="true" />" /></th>
					<c:choose>
						<c:when test="${command.prgrs_status =='C04211' or command.prgrs_status=='C04207' or command.prgrs_status=='C04212'}">
							<c:choose>
								<c:when test="${command.session_user_id == lomReq.reqman_id}">
									<td colspan="6">
										<span class="btn_all_b" onclick='javascript:openChooseClient();'><span class="add"></span><a><spring:message code='clm.page.msg.manage.add' /></a></span>			
									    <span  id="id_trgtman_nm"><c:out value="${reqAuthInfo}" /></span>
									</td>
								</c:when>
								<c:otherwise>
									<td colspan="6" id="id_trgtman_nm"><c:out value="${reqAuthInfo}" /></td>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<td colspan="6" id="id_trgtman_nm"><c:out value="${reqAuthInfo}" /></td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
                    <th class="borTz02"><spring:message code="clm.page.msg.manage.finalVer" /><br><spring:message code="clm.page.msg.manage.reviewOpinion" /></th>
                    <td colspan="6">
						<c:out value="${lomRe.cont}" escapeXml="false"/>
					</td>
                </tr>
                <c:if test="${command.top_role ne 'ETC'}">
                <tr>
                    <th class="borTz02"><spring:message code="clm.page.msg.manage.grpMasterOp" /></th>
                    <!-- Sungwoo replaced 2014-09-29 Exception 방지처리 -->
                    <td colspan="6"><c:out value="${lomRe.cnsd_apbt}" escapeXml="cnsd_apbt"/></td>
                </tr>
                </c:if>
                <c:if test="${!empty lomReq.lastbn_chge_yn_nm}">
                <tr>
                    <th class="borTz02"><spring:message code="clm.page.msg.manage.chgRevVerYn" /></th><!-- 마지막본 변경 영부  LASTBN_CHGE_YN -->
                    <td colspan="6"><c:out value='${lomReq.lastbn_chge_yn_nm}' /></td>
                </tr>
                </c:if>
                <c:if test="${!empty lomReq.plndbn_chge_cont}">
                <tr>
                    <th class="borTz02"><spring:message code="clm.page.msg.manage.rsnBrkChange" /> </th>
                    <td colspan="6"><%=StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String)lomReq.get("plndbn_chge_cont")))%></td>
                </tr>
                </c:if>
                <tr>
		            <th rowspan="4" class='rightline'><spring:message code="clm.page.field.contract.basic.filename" /></th>
		            <td class='tL'><span class="blueD"><spring:message code='clm.page.field.contract.basic.filename1' /></span></td>
		            <td colspan="5">
		            <iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationContract" name="fileConsultationContract" frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe>
		            </td>
		        </tr>
		        <tr>
		            <td  class='tL'><span class="blueD"><spring:message code='clm.page.msg.manage.attachment_br' /></span></td>
		            <td colspan="5">
		            <iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationEtc" name="fileConsultationEtc" frameborder="0" class="addfile_iframe_d" scrolling="auto" allowTransparency="true"></iframe>
		            </td>
		        </tr>
		        <tr>
		            <td  class='tL'><span class="blueD"><spring:message code='clm.page.field.contract.basic.filename2' /></span></td>
		            <td colspan="5">
		            <iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationEtc2" name="fileConsultationEtc2" frameborder="0" class="addfile_iframe_d" scrolling="auto" allowTransparency="true"></iframe>               
		            </td>
		        </tr>
		        <tr>
		            <td  class='tL'><span class="blueD"><spring:message code='clm.page.msg.common.outlook' /></span></td>
		            <td colspan="5">
		            <iframe src="<c:url value='/clm/blank.do' />" id="fileListOut" name="fileListOut" frameborder="0" class="addfile_iframe_d" scrolling="auto" allowTransparency="true"></iframe>               
		            </td>
		        </tr>
			</table>
			
			<div class="title_basic">
                <h4><spring:message code="clm.page.msg.manage.contInfo" /></h4>
            </div>
            <!-- //title -->
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
                    <th><spring:message code="clm.page.msg.manage.reqName" /></th>
                    <td colspan="3"><c:out value='${lomReq.req_title}' default="" escapeXml="false"/></td>
                    <th><spring:message code="clm.page.msg.manage.reqPerson" /></th>
                    <td>
                        <c:out value="${lomReq.reqman_nm}" />/<c:out value="${lomReq.reqman_jikgup_nm}" />/<c:out value="${lomReq.req_dept_nm}" />                      
                    </td>
                </tr>
                <tr>
                    <th><spring:message code='clm.page.field.contract.basic.name' /></th>
		            <td colspan="3"><c:out value="${contractLom.cntrt_nm}" />
		                <input type="hidden" name="cntrt_nm" id="cntrt_nm" value="<c:out value='${contractLom.cntrt_nm}'/>" />
		            </td>
		            <th><spring:message code="clm.page.msg.manage.contId" /></th>
                    <td><c:out value="${contractLom.cntrt_no}" /></td>
		        </tr>
		        <tr>
		            <th><spring:message code='clm.page.msg.common.otherParty' /></th>
		            <td>
		                <input type="hidden" name="customer_cd" id="customer_cd" value="" />
		                <input type="hidden" name="dodun" id="dodun" value="" />
		                
		                <a href="javascript:customerPop('<c:out value="${contractLom.cntrt_oppnt_cd}" />','<c:out value="${contractLom.cntrt_oppnt_cd}" />');">
		                    <c:out value="${contractLom.cntrt_oppnt_nm}" />
		                </a>
		            </td>           
		            <th><spring:message code='clm.page.field.customer.registerNo' /></th>
		            <td><c:out value="${contractLom.cntrt_oppnt_rprsntman}"  escapeXml="false"/></td>
		            <th><spring:message code="clm.page.field.customer.contractRequired" /></th>
		            <td><c:out value="${contractLom.cntrt_oppnt_type_nm}" /></td>
		        </tr>
		        <tr>
                    <th><spring:message code="clm.page.msg.manage.top30Cus" /><img src="<%=IMAGE %>/icon/ico_search2.gif" class="cp" onClick="javascript:openTop30Customer();" alt="search" style="padding-left:5px" title="Top 30 Customer List"/></th>
                    <td><c:out value="${contractLom.top_30_cus}"  escapeXml="false"/></td>
                    <th><spring:message code="clm.page.msg.manage.relatedPrd" /></th>
                    <td><c:out value="${contractLom.related_products}"  escapeXml="false"/></td>
                    <th><spring:message code="clm.page.msg.manage.srcContDraft" /></th>
                    <td><c:out value="${contractLom.cont_draft}" /></td>
                </tr>
		        <tr>
		            <th><spring:message code='clm.page.field.contract.basic.type' /></th>
		            <td colspan="3">
		            <c:out value='${contractLom.biz_clsfcn_nm}'/> / <c:out value='${contractLom.depth_clsfcn_nm}'/> / <c:out value='${contractLom.cnclsnpurps_bigclsfcn_nm}'/> / <c:out value='${contractLom.cnclsnpurps_midclsfcn_nm}'/>
		            </td>
		            <th><spring:message code="clm.page.field.contract.consultation.detail.autorenew" /></th>
	                <td>
	                    <c:if test="${contractLom.auto_rnew_yn=='Y'}">Yes</c:if>
	                    <c:if test="${contractLom.auto_rnew_yn!='Y'}">No</c:if>
	                </td>
		        </tr>
		        <tr>
		            <th><spring:message code="clm.page.field.contract.basic.thing" /></th>
		            <td><c:out value="${contractLom.cntrt_trgt_nm}" /></td>
		            <th><spring:message code="clm.page.field.contract.basic.detail" /></th>
		            <td colspan='3'>
		            <c:choose>
	                    <c:when test="${lomReq.page_mode=='M'}">
	                        <input type="text" class="text_full" name="cntrt_trgt_det" id="cntrt_trgt_det" value="<c:out value='${contractLom.cntrt_trgt_det}' escapeXml="false"/>"/>
	                    </c:when>
	                    <c:otherwise>
	                        <c:out value="${contractLom.cntrt_trgt_det}" escapeXml="false"/>
	                    </c:otherwise>
	                </c:choose>
	             	</td>
		        </tr>
		        <tr>
			        <c:choose>
	                    <c:when test="${lomReq.page_mode == 'M'}">
	                        <th><spring:message code='clm.page.field.contract.detail.period' /></th>
	                        <td colspan="3">
	                            <input type="text" name="cntrtperiod_startday" id="cntrtperiod_startday" value="<c:out value='${contractLom.cntrtperiod_startday}'/>" class="text_calendar02" required alt="<spring:message code="clm.page.msg.manage.contPeriod" htmlEscape="true" />" />
	                            <c:if test="${contractLom.cntrtperiod_startday ne ''}">~</c:if>
	                            <input type="text" name="cntrtperiod_endday" id="cntrtperiod_endday" value="<c:out value='${contractLom.cntrtperiod_endday}'/>" class="text_calendar02" required alt="<spring:message code="clm.page.msg.manage.contPeriod" htmlEscape="true" />" />
	                        </td>
	                        <th><spring:message code='clm.page.field.contract.detail.paygubun' /></th>
	                        <td>
	                            <select name="payment_gbn" id="payment_gbn" required class="all" style="width:150px" onChange="paymentGbnChange();" >
	                                <c:out value="${combo.paymentGbn}" escapeXml="false"/>
	                            </select>
	                        </td>
	                    </c:when>
	                    <c:otherwise>
	                        <th><spring:message code='clm.page.field.contract.detail.period' /><span class='astro'>*</span></th>
	                        <td colspan="3"><c:out value="${contractLom.cntrtperiod_startday}" />
	                        <c:if test="${contractLom.cntrtperiod_startday ne ''}">~</c:if>
	                        <c:out value="${contractLom.cntrtperiod_endday}" /> 
	                        <input type="hidden" name="cntrtperiod_startday" value="<c:out value='${contractLom.cntrtperiod_startday}' />"/>
	                        <input type="hidden" name="cntrtperiod_endday" value="<c:out value='${contractLom.cntrtperiod_endday}' />"/>
	                        </td>            
	                        <th><spring:message code='clm.page.field.contract.detail.paygubun' /></th>
	                        <td>
	                            <c:out value="${contractLom.payment_gbn_nm}" />
	                            <input type="hidden" name="payment_gubun" value="<c:out value='${contractLom.payment_gbn}' />"/>
	                        </td>
	                    </c:otherwise>
	                </c:choose>                 
                </tr>
	            <tr>
                    <c:choose>
	                    <c:when test="${lomReq.page_mode=='M'}">
	                        <th><spring:message code='clm.page.field.contract.detail.price' /></th>
	                        <td colspan="3">
	                            <input type="text" name="cntrt_amt" id="cntrt_amt" value="<c:out value='${contractLom.cntrt_amt}'/>" onkeyup='olnyNum(this)' style="width: 150px; text-align: right; margin-right: 1px; IME-MODE: disabled;" class="text_full"  />
	                            <input type="checkbox" name="cntrt_untprc" id="cntrt_untprc" value="Y" onclick="clickCntrtUntprc();" /><label for="" > <spring:message code="clm.page.msg.manage.conclSingleAmt" /></label>
	                        </td>
	                        <th><spring:message code='clm.page.field.contract.detail.money' /></th>
	                        <td>
	                            <select name="crrncy_unit" id="crrncy_unit">
	                                <c:out value="${combo.crrncyUnit }" escapeXml="false"/>
	                            </select>
	                        </td>
	                    </c:when>
	                    <c:otherwise>
	                        <th><spring:message code='clm.page.field.contract.detail.price' /></th>
	                        <td colspan="3"><c:out value="${contractLom.cntrt_amt}" /></td>
	                        <th><spring:message code='clm.page.field.contract.detail.money' /></th>
	                        <td><c:out value="${contractLom.crrncy_unit}" /></td>
	                    </c:otherwise>
	                </c:choose>                
                </tr>
                <c:choose>
	                <c:when test="${lomReq.page_mode=='M'}">
	                    <tr id="trCntrtUntprc">
	                       <th><spring:message code="clm.page.msg.manage.singleAmtSum" /></th>
	                       <td colspan="5">
	                           <textarea name="cntrt_untprc_expl" id="cntrt_untprc_expl" cols="30" rows="3" onkeyup="f_chk_byte(this,300)" class="text_area_full"><c:out value='${contractLom.cntrt_untprc_expl}' escapeXml="false"/></textarea></br></br>
	                            <div class='ico_maxsize fR'>Max Size : <span>10MB</span></div>
	                           <iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationUntPrc" name="fileConsultationUntPrc" frameborder="0" class="addfile_iframe" scrolling="auto" allowTransparency="true"></iframe>
	                       </td>
	                   </tr>
	                </c:when>
	                <c:otherwise>
	                    <c:if test='${contractLom.cntrt_untprc_expl != "" }'>
	                        <tr>
	                            <th rowspan="2"><spring:message code='clm.page.field.contract.detail.unitcont' /></th>
	                            <td colspan="5">
	                                <c:out value="${contractLom.cntrt_untprc_expl}" />
	                            </td>
	                        </tr>
	                        <tr>
	                            <td colspan="5" class="tal_lineL">
	                                <iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationUntPrc" name="fileConsultationUntPrc" frameborder="0" class="addfile_iframe_d" scrolling="auto" allowTransparency="true"></iframe>                           
	                            </td>
	                        </tr>
	                    </c:if> 
	                </c:otherwise>
                </c:choose>
                <!-- 추진목적 및 배경 -->
		        <c:choose>
	                <c:when test="${lomReq.page_mode=='M'}">
	                    <tr>
	                        <th><spring:message code='clm.page.field.contract.detail.object' /></th>
	                        <td colspan="5">
	                        <input type="hidden" name="pshdbkgrnd_purps" id="pshdbkgrnd_purps" value="" />
	                        <span id="curByteExpl_body_mine">0</span>/<spring:message code="clm.page.msg.manage.stringLen2000" /><br>
	                        <textarea name="body_mime" id="body_mime" cols="100%" rows="10" maxLength="2000" onkeyup="frmChkLenLang(this,2000,'curByteExpl_body_mine','<%=langCd%>')" class="text_area_full"><c:out value="${contractLom.pshdbkgrnd_purps}" escapeXml=""/></textarea>
	                        </td>
	                    </tr>
	                </c:when>
	                <c:otherwise>
	                    <tr>
	                        <th><spring:message code='clm.page.field.contract.detail.object' /></th>
	                        <td colspan="5"><c:out value="${contractLom.pshdbkgrnd_purps}" escapeXml="false"/></td>
	                    </tr>
	                </c:otherwise>
	            </c:choose>
	            <c:choose>
                    <c:when test="${lomReq.page_mode=='M'}">
                        <tr>
                            <th><spring:message code="clm.page.msg.manage.etcMain" /></th>
                            <td colspan="5">
                                <textarea name="etc_main_cont" id="etc_main_cont" alt="<spring:message code="clm.page.msg.manage.etcMain" htmlEscape="true" />" cols="30" rows="4" onkeyup="f_chk_byte(this,300)" class="text_area_full" maxLength="800"><c:out value='${contractLom.etc_main_cont}' escapeXml="false"/></textarea>
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <th><spring:message code="clm.page.msg.manage.etcMain" /></th>
                            <td colspan="5"><c:out value="${contractLom.etc_main_cont}"/></td>
                        </tr>
                    </c:otherwise>
                </c:choose>
	            
		        <!-- 배상책임한도 -->
		        <c:if test='${!empty contractLom.oblgt_lmt}'>
	            <tr>
	                <th><spring:message code="clm.page.field.contract.consultation.detail.obligationlimit"/></th>
	                <td colspan="5">
	                    <c:out value='${contractLom.oblgt_lmt}' escapeXml="false"/>
	                </td>
	            </tr>
	            </c:if>
	            
	            <!-- 기타 특약사항 -->
	            <c:if test='${!empty contractLom.spcl_cond}'>
	            <tr>
	                <th><spring:message code="clm.page.msg.manage.etcSpecial" /></th>
	                <td colspan="5"><c:out value='${contractLom.spcl_cond}' escapeXml="false"/></td>
	            </tr>
	            </c:if>
	            <!-- 비밀유지기간 -->
                <tr>
                    <th><spring:message code="clm.page.field.contract.detail.secret" /></th>
                    <td colspan="5"><c:out value='${contractLom.secret_keepperiod}' escapeXml="false"/></td>
                </tr>
	            <!-- 준거법 / 준거법상세 -->
	            <tr>
	                <th><spring:message code="clm.page.field.contract.detail.loac"/></th>
	                <td><c:out value='${contractLom.loac}'/></td>
	                <th><spring:message code="clm.page.field.contract.detail.loacdetail"/></th>
	                <td colspan="3"><c:out value='${contractLom.loac_etc}' escapeXml="false"/></td>
	            </tr>
	            <!-- 분쟁해결방법 / 분쟁해결방법 상세 -->
	            <tr>
	                <th><spring:message code="clm.page.field.contract.detail.solutionmethod"/></th>
	                <td><c:out value='${contractLom.dspt_resolt_mthd}'/></td>
	                <th><spring:message code="clm.page.field.contract.detail.solutionmethoddetail"/></th>
	                <td colspan="3"><c:out value='${contractLom.dspt_resolt_mthd_det}' escapeXml="false"/></td>
	            </tr>
            </table>
			
			<div class='title_basic3'><spring:message code="clm.page.msg.manage.preApprInf" /></div>
            <table class="table-style01">
                <colgroup>
                    <col width="12%" />
                    <col width="38%" />
                    <col width="12%" />
                    <col width="38%" />
                </colgroup>
                <c:choose>
                    <c:when test="${lomReq.page_mode=='M'}">
                        <tr>
                            <th><spring:message code="clm.page.msg.manage.apprDt" /><span class='astro'>*</span></th>
                            <td><input type="text" name="bfhdcstn_apbtday" id="bfhdcstn_apbtday" class="text_calendar02" value="<c:out value='${contractLom.bfhdcstn_apbtday}'/>" /></td>
                            <th><spring:message code="clm.page.msg.manage.apprType" /><span class='astro'>*</span></th>
                            <td>
                                <select name="bfhdcstn_apbt_mthd" id="bfhdcstn_apbt_mthd">
		                            <c:out value="${combo.bfhdcstnApbtMthd }" escapeXml="false"/>
		                        </select>
                            </td>
                        </tr>
                        <tr>
                            <th><spring:message code="clm.page.msg.manage.proposer" /><span class='astro'>*</span></th>
                            <td>
                                <input type="hidden" name="bfhdcstn_mtnman_id" value="<c:out value='${contractLom.bfhdcstn_mtnman_id}'/>" />
                                <input type="hidden" name="bfhdcstn_mtn_dept_nm" value="<c:out value='${contractLom.bfhdcstn_mtn_dept_nm}'/>" />
                                <input type="text" name="bfhdcstn_mtnman_nm" value="<c:out value='${contractLom.bfhdcstn_mtnman_nm}'/>" style="width:120px" class="text_search" onkeypress="if(event.keyCode==13) {searchEmployee('mtn');return false;}" /><img src="<%=IMAGE %>/icon/ico_search.gif" class="cp" alt="search" onclick="searchEmployee('mtn');" style="cursor:pointer;"/>
                                <input type="hidden" name="bfhdcstn_mtnman_jikgup_nm" value="<c:out value='${contractLom.bfhdcstn_mtnman_jikgup_nm}'/>" />
		                        <span id="bfhdcstn_mtnman_jikgup_nm_span"><c:out value='${contractLom.bfhdcstn_mtnman_jikgup_nm}'/></span>
                                / <span id="bfhdcstn_mtn_dept_nm_span"><c:out value='${contractLom.bfhdcstn_mtn_dept_nm}'/></span>
                            </td>
                            <th><spring:message code="clm.page.msg.manage.apprPer" /><span class='astro'>*</span></th>
                            <td>
                                <input type="hidden" name="bfhdcstn_apbtman_id" id="bfhdcstn_apbtman_id"  value="<c:out value='${contractLom.bfhdcstn_apbtman_id}'/>" />
                                <input type="hidden" name="bfhdcstn_apbtman_jikgup_nm" id="bfhdcstn_apbtman_jikgup_nm" value="<c:out value='${contractLom.bfhdcstn_apbtman_jikgup_nm}'/>" />
                                <input type="hidden" name="bfhdcstn_apbt_dept_nm" id="bfhdcstn_apbt_dept_nm" value="<c:out value='${contractLom.bfhdcstn_apbt_dept_nm}'/>" />
                                <input type="text" name="bfhdcstn_apbtman_nm" id="bfhdcstn_apbtman_nm" value="<c:out value='${contractLom.bfhdcstn_apbtman_nm}'/>" style="width:120px" class="text_search" onkeypress="if(event.keyCode==13) {searchEmployee('apbt');return false;}"/><img src="<%=IMAGE %>/icon/ico_search.gif" class="cp" alt="search"  onclick="searchEmployee('apbt');" style="cursor:pointer"/>
                                <span id="bfhdcstn_apbtman_jikgup_nm_span"><c:out value='${contractLom.bfhdcstn_apbtman_jikgup_nm}'/></span>
                                / <span id="bfhdcstn_apbt_dept_nm_span"><c:out value='${contractLom.bfhdcstn_apbt_dept_nm}'/></span>
                            </td>
                        </tr>
                        
                        <tr>
                            <th><spring:message code="clm.page.msg.manage.attachData" /></th>
                            <td colspan="3">
                                <spring:message code="clm.page.msg.manage.announce097" />    
                                <div class='ico_maxsize fR'>Max Size : <span>10MB</span></div>
                                <!-- 2014-07-03 Sungwoo replacement height size -->
                        		<iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationPre" name="fileConsultationPre" frameborder="0" width="100%" height="100%" leftmargin="0" topmargin="0" scrolling="auto" class="addfile_iframe" allowTransparency="true"></iframe>
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
		                <tr>
		                    <th><spring:message code="clm.page.msg.manage.apprDt" /></th>
		                    <td><c:out value='${contractLom.bfhdcstn_apbtday}'/></td>
		                    <th><spring:message code="clm.page.msg.manage.apprType" /></th>
		                    <td><c:out value="${contractLom.bfhdcstn_apbt_mthd_nm}" /></td>
		                </tr>
		                <tr>
		                    <th><spring:message code="clm.page.msg.manage.proposer" /></th>
		                    <td>
		                        <c:if test="${contractLom.bfhdcstn_mtnman_nm != ''}">
			                        <c:out value="${contractLom.bfhdcstn_mtnman_nm}" /> / <c:out value='${contractLom.bfhdcstn_mtnman_jikgup_nm}'/> / <c:out value='${contractLom.bfhdcstn_mtn_dept_nm}'/>
			                    </c:if>
		                    </td>
		                    <th><spring:message code="clm.page.msg.manage.apprPer" /></th>
		                    <td>
		                        <c:if test="${contractLom.bfhdcstn_apbtman_nm != ''}">
		                            <c:out value='${contractLom.bfhdcstn_apbtman_nm}'/> / <c:out value='${contractLom.bfhdcstn_apbtman_jikgup_nm}'/> / <c:out value='${contractLom.bfhdcstn_apbt_dept_nm}'/>
		                        </c:if>
		                    </td>
		                </tr>
		                
		                <tr>
		                    <th><spring:message code="clm.page.msg.manage.attachData" /></th>
		                    <td colspan="3">
		                    	<!-- 2014-07-03 Sungwoo replacement height size -->
                        		<iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationPre" name="fileConsultationPre" frameborder="0" width="100%" height="100%" leftmargin="0" topmargin="0" scrolling="auto" class="addfile_iframe" allowTransparency="true"></iframe>                        
		                    </td>
		                </tr>
                    </c:otherwise>
                </c:choose>
            </table>
            
            <!-- tnc include JSP -->
			<jsp:include page="/clm/manage/completion.do">
				<jsp:param name="method" value="getTncLink" />
				<jsp:param name="cntrt_id4" value='<%=contractLom.get("cntrt_id") %>'/>
			</jsp:include>
            
            <div class='title_basic3'><spring:message code="clm.page.msg.manage.relContInf" /> <img src="<%=IMAGE %>/btn/btn_up.gif" alt="up" onclick="tit_Toggle(this,'div_rel_contract');" style="cursor:pointer"/></div>
                <div id="div_rel_contract">
                    <c:choose>
			            <c:when test="${lomReq.page_mode=='M'}">
							<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
							    <colgroup>
	                    			<col width="17%" />
	                    			<col width="40%" />
	                    			<col width="14%" />
	                    			<col width="20%" />    
							        <col/>                  
							    </colgroup>
							    <tbody id="relationTbody">
							    <tr id="trRelationContract">
							        <th><spring:message code="clm.page.msg.manage.relation" /></th>
							        <th><spring:message code="clm.page.msg.manage.relCont" /></th>
							        <th><spring:message code="clm.page.msg.manage.define" /></th>
							        <th><spring:message code="clm.page.msg.manage.relDetail" /></th>
							        <th class="tC"></th>
							    </tr>
							    <tr id="trRelationContract">
							        <td class='tC'><select name="rel_type" id="rel_type" onchange="reltypeChange();"><c:out value="${combo.relType }" escapeXml="false"/></select></td>
							        <td class='tC'><input type="hidden" name="parent_cntrt_id" id="parent_cntrt_id"/>
							            <input type="text" name="parent_cntrt_name" id="parent_cntrt_name" class="text_search" style="width:80%;" readonly="readonly"/><img src="<%=IMAGE %>/icon/ico_search.gif" onclick="javascript:popupListContract('C03201');" class="cp" alt="search" />
							        </td>
							        <td class='tC'><select name="rel_defn" id="rel_defn"></select></td>
							        <td class='tC'>
							        	<input type="text" name="expl" id="expl" class="text_full" style="width:90%"/>
							        </td>
							        <td>
							        	<span class="btn_all_b" onclick="javascript:actionRelationContract('insert','','');"><span class="edit"></span><a><spring:message code="clm.page.msg.manage.registrate"/></a></span>
							        </td>
							    </tr>
							    <tr id="trRelationContract1"></tr>
							    <c:choose>
							    <c:when test ="${relationListSize > 0}">
							        <c:forEach var="list" items="${relationList}" varStatus="i">
							            <tr id="trRelationContractCont">    
							                <td><c:out value='${list.rel_type_nm}'/></td>
							                <td>
												<!-- 신성우 변경처리 CNTRT_ID -> CNSDREQ_ID 2014-04-10 POPUP 호출시 REQ_ID 전달안함-->
                                                <a href="javascript:goDetail('<c:out value='${list.cnsdreq_id}'/>');"><c:out value='${list.req_title}'/></a>
							                </td>
							                <td><c:out value='${list.rel_defn}'/></td>
							                <td>
							                    <c:out value='${list.expl}' escapeXml="false"/>
							                </td>
							                <td>
							                    <a href="javascript:actionRelationContract('delete','<c:out value='${list.parent_cntrt_id}'/>');"><img src="/script/secfw/jquery/uploadify/cancel_new_en.gif"></a>
							                </td>
							            </tr>
							        </c:forEach>
							        
							    </c:when>
							    <c:otherwise>
							        <tr><td colspan="5">No Data Found. </td></tr>
							    </c:otherwise>
							    </c:choose>
							    </tr>   
							    </tbody>
							</table>    
							<table cellspacing="0" cellpadding="0" border="0" class="table-style01 borz01">
							    <tr>
							        <td>
							            ※ <spring:message code="clm.page.msg.manage.announce130" /> <br/>
							            &nbsp;<spring:message code="clm.page.msg.manage.forRel" />  <br/>
							            &nbsp;<spring:message code="clm.page.msg.manage.smRel" /> <br/>
							            &nbsp;<spring:message code="clm.page.msg.manage.chgCancel" /> <br/>
							            &nbsp;<spring:message code="clm.page.msg.manage.announce005" />    
							        </td>
							    </tr>
							</table>
			            </c:when>
			            <c:otherwise>
			                <table cellspacing="0" cellpadding="0" border="0" class="table-style01">
			                    <colgroup>
			                        <col width="12%" />
			                        <col width="50%" />
			                        <col width="10%" />
			                        <col/>                  
			                    </colgroup>
			                    <tbody id="relationTbody">
			                    <tr id="trRelationContract">
			                        <th>RelationType</th>
			                        <th><spring:message code="clm.page.msg.manage.relCont" /></th>
			                        <th><spring:message code="clm.page.msg.manage.define" /></th>
			                        <th><spring:message code="clm.page.msg.manage.relDetail" /></th>
			                    </tr>
			                    <c:choose>
			                    <c:when test ="${relationListSize > 0}">
			                        <c:forEach var="list" items="${relationList}" varStatus="i">
			                            <tr id="trRelationContractCont">
			                                <td><c:out value='${list.rel_type_nm}'/></td>
			                                <td>
												<!-- 신성우 변경처리 CNTRT_ID -> CNSDREQ_ID 2014-04-10 POPUP 호출시 REQ_ID 전달안함-->
                                                <a href="javascript:goDetail('<c:out value='${list.cnsdreq_id}'/>');"><c:out value='${list.relation_cntrt_nm}'/></a>
			                                </td>
			                                <td><c:out value='${list.rel_defn}'/></td>
			                                <td><c:out value='${list.expl}'/></td>
			                            </tr>       
			                        </c:forEach>
			                    </c:when>
			                    <c:otherwise>
			                        <tr id="trRelationContractCont"><td colspan="4" align="center">No Data Found</td></tr>
			                    </c:otherwise>
			                    </c:choose>
			                    </tbody>       
			                </table>
			            </c:otherwise>
			        </c:choose>
                </div>
            
            <!-- Conclusion Information -->
            <div class="title_basic">
                <h4><spring:message code="clm.page.title.contract.conclusion.detail.title"/> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'tab_contents_sub_conts3');" style="cursor:pointer"/></h4>
	        </div>
	        <table id="tab_contents_sub_conts3" cellspacing="0" cellpadding="0" border="0" class="table-style01">
		        <tr>
		            <th style='width:12%'>
                    	<spring:message code="clm.page.field.contract.consultation.detail.signplannernm"/><!-- 서명예정자 -->
                    	<span class='astro'>*</span>
                    </th>
                    <td>
		            	<input type="hidden" name="seal_mthd" id="seal_mthd" value="C02102" />
                    	<c:choose>
		                    <c:when test="${lomReq.page_mode=='M'}">
		                        <input type="hidden" name="sign_plndman_id" id="sign_plndman_id" value="<c:out value='${contractLom.sign_plndman_id}'/>" />
		                        <input type="hidden" name="sign_plndman_nm" id="sign_plndman_nm" value="<c:out value='${contractLom.sign_plndman_nm}'/>" />
		                        <input type="hidden" name="sign_plndman_jikgup_nm" id="sign_plndman_jikgup_nm" value="<c:out value='${contractLom.sign_plndman_jikgup_nm}'/>"/>
		                        <input type="hidden" name="sign_plnd_dept_nm" id="sign_plnd_dept_nm" value="<c:out value='${contractLom.sign_plnd_dept_nm}'/>"/>
		                        <input type="text" name="sign_plndman_search_nm" id="sign_plndman_search_nm" value="" style="width:110px" class="text_search" alt="<spring:message code='clm.page.field.contract.consultation.detail.signplannernm'/>" onkeydown="if(event.keyCode==13){event.returnValue = false;javascript:searchEmployee('sign');}"/><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchEmployee('sign');" class="cp" alt="search" />
		                        <span id="plndman"><c:out value='${contractLom.sign_plndman_nm}'/>/<c:out value='${contractLom.sign_plndman_jikgup_nm}'/>/<c:out value='${contractLom.sign_plnd_dept_nm}'/></span>
		                    </c:when>
		                    <c:otherwise>
		                        <c:out value='${contractLom.sign_plndman_nm}'/>/<c:out value='${contractLom.sign_plndman_jikgup_nm}'/>/<c:out value='${contractLom.sign_plnd_dept_nm}'/>
                            </c:otherwise>
	                    </c:choose>
                    </td>
		        </tr>
                <tr id="seal-tr1" >
		            <th><spring:message code="clm.page.field.contract.consultation.detail.conclusionday"/><span class='astro'>*</span></th>
		            <td>
			            <c:choose>
                            <c:when test="${lomReq.page_mode=='M'}">
                                <input type="text" name="cnclsn_plndday" id="cnclsn_plndday" value="<c:out value='${contractLom.cnclsn_plndday}'/>"  class="text_calendar02" required style='width:110px'/>
                            </c:when>
                            <c:otherwise>
                                <c:out value='${contractLom.cnclsn_plndday}'/>
                            </c:otherwise>
                        </c:choose>        
		            </td>
		        </tr>
		        <tr>
		            <th><spring:message code="clm.page.field.contract.consultation.detail.contractrespmannm"/><span class='astro'>*</span></th>
		            <td>
			            <c:choose>
	                        <c:when test="${lomReq.page_mode=='M'}">
	                            <input type="hidden" name="cntrt_respman_id" id="cntrt_respman_id" value="<c:out value='${contractLom.cntrt_respman_id}'/>" />
	                            <input type="hidden" name="cntrt_respman_nm" id="cntrt_respman_nm" value="<c:out value='${contractLom.cntrt_respman_nm}'/>" />
	                            <input type="hidden" name="cntrt_respman_jikgup_nm" id="cntrt_respman_jikgup_nm" value="<c:out value='${contractLom.cntrt_respman_jikgup_nm}'/>"/>
	                            <input type="hidden" name="cntrt_resp_dept" id="cntrt_resp_dept" value="<c:out value='${contractLom.cntrt_resp_dept}'/>"/>
	                            <input type="hidden" name="cntrt_resp_dept_nm" id="cntrt_resp_dept_nm" value="<c:out value='${contractLom.cntrt_resp_dept_nm}'/>"/>
	                            <input type="text" name="cntrt_respman_search_nm" id="cntrt_respman_search_nm" value=""  style="width:110px" class="text_search"  alt="<spring:message code='clm.page.field.contract.consultation.detail.contractrespmannm'/>" onkeydown="if(event.keyCode==13){event.returnValue = false;javascript:searchEmployee('contract');}"/><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchEmployee('contract');" class="cp" alt="search"/>
	                            <span id="respman">&nbsp;&nbsp;<c:out value='${contractLom.cntrt_respman_nm}'/>/<c:out value='${contractLom.cntrt_respman_jikgup_nm}'/>/<c:out value='${contractLom.cntrt_resp_dept_nm}'/></span>
	                            <br/><br/><span><spring:message code="clm.msg.alert.contract.consultation.respmannotice"/></span>
	                        </c:when>
	                        <c:otherwise>
	                            <c:out value='${contractLom.cntrt_respman_nm}'/>/<c:out value='${contractLom.cntrt_respman_jikgup_nm}'/>/<c:out value='${contractLom.cntrt_resp_dept_nm}'/>
	                        </c:otherwise>
	                    </c:choose>     
		            </td>
		        </tr>
		    </table>
		    <!-- //Conclusion Information -->
		    
		    <!-- 15개 항목 상세 -->
			<jsp:include page="/clm/review/considerationHQ.do">
				<jsp:param name="method" value="getCheckList" />
				<jsp:param name="cntrt_id3" value='<%=contractLom.get("cntrt_id") %>'/>
			</jsp:include>
			
			<!-- 이력정보 -->
			<div id="contractHis-list"></div>
			<!-- //이력정보 -->	
			
			<div class="t_titBtn">
				<div class="btn_wrap_c" id="consultation_btn" style="display:none;">
				    <span class="btn_all_w" id="chkVal2"><span class="sangsin"></span><a href="javascript:checkApprovalValidation();"><spring:message code="clm.page.button.contract.cosultationapproval"/></a></span>
				    <span class="btn_all_w"><span class="preview"></span><a href="javascript:openArbitraryPop();"><spring:message code="clm.page.msg.manage.conclRule" /></a></span>
					<span class="btn_all_w"><span class="modify"></span><a href="javascript:processCancel();"><spring:message code="clm.page.button.contract.rollback" /></a></span>
					<span class="btn_all_w"><span class="tsave"></span><a href="javascript:tempSave('button');"><spring:message code='clm.page.button.contract.imsisave' /></a></span>
					<span class="btn_all_w"><span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /></a></span>			
				</div>
				<div class="btn_wrap_c" id="consultation_btn_list" style="display:none;">
					<span class="btn_all_w"><span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /></a></span>			
				</div>
			</div>
			
			</form:form>
			</div>	
			</div>
		</div>
	</div>
	<!-- footer  -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
	<!-- // footer -->
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</body>
</html>