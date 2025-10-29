<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.DateUtil"%>
<%@ page import="com.sds.secframework.common.util.PageUtil"%>
<%@ page import="com.sds.secframework.common.util.StringUtil"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap"%>
<%--
/**
 * 파  일  명 	 : OrgMgr_d_view.jsp
 * 프로그램명 	 : 계약정보수정/원본출납관리 - 상세화면
 * 설      명 	 : 
 * 작  성  자 	 : 박정훈
 * 작  성  일      : 2013.04
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="clm.main.title" />
</title>

<!--계약관리일 경우  -->
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet"/>
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
	<script>$(document).ready(function(){

	var cntrt_id = "";
	if("<c:out value='${command.cntrt_id}'/>" == ""){
		changeContract("<c:out value='${lomReq.cntrt_id}'/>");
		cntrt_id = "<c:out value='${lomReq.cntrt_id}'/>";
	} else {
		changeContract("<c:out value='${command.cntrt_id}'/>");
		cntrt_id = "<c:out value='${command.cntrt_id}'/>";
	}
	$('#agree_yn').val("<c:out value='${lomReq.agree_yn}'/>");
});

/**
* 계약검토의뢰등록 폼  inner View 
*/
function detailMyApprovalContractMaster(){	
	var options = {
			url: "<c:url value='/clm/manage/myApproval.do?method=detailMyApprovalContractMaster' />",
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
	document.frm.cntrt_id.value = cntrt_id;
	detailMyApprovalContractMaster();
	contractHisList(); //2014-09-11 Sungwoo Replaced Contract History.
	//listMyApprovalHis();	//2014-09-11 Sungwoo commented modify history list. 소스상 주석처리되어 해당내역도 제외처리.
	initAttach();
	initButton();
	
	viewHiddenProgress(false) ;
 }
 
function changeMode(mode){
	var frm = document.frm;
	frm.view_gbn.value = "download";
	frm.page_gbn.value = mode;

	viewHiddenProgress(true) ;
	// 프로그래스바표시를 위해 타이머함수 사용
	setTimeout(function() {         
		changeContract(frm.cntrt_id.value);
    }, 10);
}

//검토이력정보 2014-09-11 Sungwoo Replaced 수정된 이력에 대해서만 조회
function listMyApprovalHis() {
	var options = {
			url: "<c:url value='/clm/manage/myApproval.do?method=listMyApprovalHis' />",
			type: "post",
			async: false,
			dataType: "html",
			success: function (data) {
				$("#consultation-his-list").html("");
				$("#consultation-his-list").html(data);
			}
		};
	$("#frm").ajaxSubmit(options);
}

//첨부파일초기화
function initAttach(){
	//계약서 사전검토 파일
	frm.target          = "fileList33";
	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	frm.method.value    = "forwardClmsFilePage";
	frm.fileInfoName.value = "fileInfos33";
	frm.fileFrameName.value = "fileList33";
	frm.file_midclsfcn.value = "F01201";
	frm.file_smlclsfcn.value = "F0120101";
	frm.ref_key.value = frm.cntrt_id.value;
	frm.view_gbn.value = "download";
	getClmsFilePageCheck('frm');
	
	
	//단가내역-첨부
  if("<c:out value='${lomReq.cntrt_untprc_expl}' />" != "") {
    frm.target          		= "fileConsultationUntPrc";
    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
    frm.method.value    		= "forwardClmsFilePage";
    frm.file_midclsfcn.value 	= "F01202";
    frm.file_smlclsfcn.value 	= "F0120211";
    frm.ref_key.value   		= frm.cntrt_id.value + "@" + frm.cnsdreq_id.value;
    frm.fileInfoName.value 		= "fileInfos4";
    frm.fileFrameName.value 	= "fileConsultationUntPrc";
    getClmsFilePageCheck('frm');
  }
  
	  //계약서사본
	  frm.target          		= "fileContractCopy";
	  frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
	  frm.method.value    		= "forwardClmsFilePage";
	  frm.file_midclsfcn.value	= "F01203";
	  frm.file_smlclsfcn.value 	= "";
	  frm.fileFrameName.value	= "fileContractCopy";
	  frm.fileInfoName.value 	= "fileInfos6";
	  frm.multiYn.value 		= "N";
	  frm.ref_key.value			= frm.cntrt_id.value;
	  
	  
	  if(frm.page_gbn.value == 'modify'){
	  frm.view_gbn.value 		= "modify";
		  var options = {
				url: "/clms/common/clmsfile.do?method=getClmsFilePageCheck",
				type: "post",
				async: false,
				dataType: "json",
				success: function(responseText,returnMessage) {
					if(responseText != null && responseText.length != 0) {
						$.each(responseText, function(entryIndex, entry) {
								if(entry['exists']=='Y'){
									frm.view_gbn.value 		= "modify";
								}
								else {
									frm.view_gbn.value 		= "upload";
								}
						});
					}
				}
		  };
		  $("#frm").ajaxSubmit(options);
		  frm.submit();
	  }
	  else{
		  getClmsFilePageCheck('frm');  
	  }
}

function goList() {
	var frm = document.frm;
	viewHiddenProgress(true) ;
	
	// 2014-07-28 Kevin changed. Redirection bug fix.
	/* frm.target          = "_self";
	frm.action          = "<c:url value='/clm/manage/myApproval.do' />";
    frm.method.value    = "listMyApproval";
    frm.submit(); */
    
	frm.target = "_self";		 
	frm.action = "/clm/manage/myContract.do";
	frm.method.value = "listMyContract";
	frm.submit();
}

function goModify() {
	var frm = document.frm;	
	frm.target          = "_self";
	frm.action          = "<c:url value='/clm/manage/myApproval.do' />";
	frm.page_gbn.value	= "modify";	
    frm.method.value    = "detailMyApproval";
    viewHiddenProgress(true) ;
    frm.submit();
}

function goHistory(seqno, max_seqno) {
	
	var frm = document.frm;
	frm.view_gbn.value = "download";
	frm.seqno.value = seqno;
	if(seqno == max_seqno){
		frm.page_gbn.value	= "one";
	}
	else{
		frm.page_gbn.value	= "history";	
	}
	
	changeContract(frm.cntrt_id.value);
}

//계약정보수정
function goInsert() {
	alert("<spring:message code="clm.page.msg.manage.announce031" />");
	if(!confirm("<spring:message code="clm.page.msg.manage.announce149" />")) return;
	
	var tmpCntrtOppntNm = "";
	
	if(frm.dmstfrgn_gbn.value=="H") {
		tmpCntrtOppntNm = chkLength($('#cntrt_oppnt_nm').val(), '16'); //한국일경우 8자리까지 김재원 값 16으로 변경
	}else{
		tmpCntrtOppntNm = chkLength($('#cntrt_oppnt_nm').val(), '12'); ///해외일경우 12자리까지
	}
	
	$('#cntrt_oppnt_nm').val(tmpCntrtOppntNm);
	
	fileContractCopy.UploadFile(function(uploadCount){
        //첨부파일 업로드 결과 잘못된 형식의 파일이 올라간 경우
        if(uploadCount == -1){
        	initAttach();
            alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
            return;
        }
	
	var frm = document.frm;	
	frm.target          = "_self";
	frm.action          = "<c:url value='/clm/manage/myApproval.do' />";
	frm.page_gbn.value	= "one";
    frm.method.value    = "insertOrgMngHis";
    viewHiddenProgress(true) ;
    frm.submit();
	});
    
}

//원본출납관리 
function goInsert2() {
	var frm = document.frm;
	
	if(frm.org_mng_type.value == ''){
		alert("<spring:message code="clm.page.msg.manage.announce125" />");
		return;
	}
	
	if(frm.org_mng_reqman_id.value == ''){ // 신청자ID
		alert("<spring:message code="clm.page.msg.manage.announce213" />");
		return;
	}
		
	if(!confirm("<spring:message code="clm.page.msg.manage.announce149" />")) return;
		
	frm.target          = "_self";
	frm.action          = "<c:url value='/clm/manage/myApproval.do' />";
	frm.page_gbn.value	= "one";
    frm.method.value    = "insertOrgMngHis2";
    viewHiddenProgress(true) ;
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
	var frm = document.frm;
	frm.conListGu.value = "preview";
	var options = {
 		url: "<c:url value='/clm/manage/completion.do?method=detailPreviewApprovalPop' />",
 		type: "post",
 		async: false,
 		dataType: "html",
 			
 		success: function(data){ 			
 			frm.contents.value=data; 			
 			showCompletionPop(); 			
 		}
   };
   $("#frm").ajaxSubmit(options);
}

function showCompletionPop(){
	
	var frm = document.frm;
	
	PopUpWindowOpen('', '1024','768',true,'popUpHistory2');
	
	frm.action	= "<c:url value='/clm/manage/completion.do' />";
	frm.method.value = "forwardCompletionHisPop";
	frm.target = "popUpHistory2";
	frm.submit();
}

function olnyNum(obj){
	var str = obj.value;
	str = new String(str);
	var Re = /[^0-9]/g;
	str = str.replace(Re,'');
	
	// 금액 100,000 형태로 변환 추가 (2011/10/15)
	obj.value = Comma(str);
}

function initButton(){
	var frm = document.frm;
	
	if(frm.page_gbn.value == "modify") {
		$('div[id^=button_modify]').attr("style", "display:");
		$('div[id^=button_modify2]').attr("style", "display:none");
		$('div[id^=button_view]').attr("style", "display:none");
		$('div[id^=button_history]').attr("style", "display:none");
	}
	else if(frm.page_gbn.value == "modify2"){
		$('div[id^=button_modify]').attr("style", "display:none");
		$('div[id^=button_modify2]').attr("style", "display:");
		$('div[id^=button_view]').attr("style", "display:none");
		$('div[id^=button_history]').attr("style", "display:none");
	}
	else if(frm.page_gbn.value == "history"){
		$('div[id^=button_modify]').attr("style", "display:none");
		$('div[id^=button_modify2]').attr("style", "display:none");
		$('div[id^=button_view]').attr("style", "display:none");
		$('div[id^=button_history]').attr("style", "display:");
	}
	else{
		$('div[id^=button_modify]').attr("style", "display:none");
		$('div[id^=button_modify2]').attr("style", "display:none");
		$('div[id^=button_view]').attr("style", "display:");
		$('div[id^=button_history]').attr("style", "display:none");
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
	} else if(flag=="contract") {
		obj = frm.cntrt_respman_search_nm;
	} else if(flag=="hdovman"){
		obj = frm.org_hdovman_search_nm;
	} else if(flag=="seal"){
		obj = frm.seal_ffmtman_search_nm;
	} else if(flag=="orgmng"){
		obj = frm.org_mng_reqman_search_nm;
	} else if(flag=="sign2"){
		obj = frm.signman_search_nm;
	}
	
	srchValue = comTrim(obj.value);
	frm.target = "PopUpEmployee";
    frm.srch_user_cntnt_type.value = "userName"; //이름으로 검색  - 보통 이름으로 검색함
    frm.srch_user_cntnt_target.value = flag;
    frm.action = "<c:url value='/secfw/esbOrg.do' />";
    frm.method.value = "listEsbEmployee";
    frm.srch_user_cntnt.value = srchValue;
    if (srchValue == "" || srchValue.length < 4) { //최소 4자리 이상 입력받게 한다.
        alert('<spring:message code='secfw.msg.error.nameMinByte' />');
        obj.focus();
    } else {
    	PopUpWindowOpen('', 800, 450, true,"PopUpEmployee");
    	frm.submit();
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
	
	
	var Feture = "toolbar=0, location=0, status=1, menubar=0, scrollbars="+(bScroll ? "yes" : "no")+", resizable=yes, top="+Top+", left="+Left+", width="+popupwidth+", height="+popupheight;
	PopUpWindow = window.open(surl, popName , Feture);
	PopUpWindow.focus();
	
}	

/**
* 임직원 조회정보 결과 setting
*/
function setUserInfos(obj) {
	var frm = document.frm;
	var srch_user_target = frm.srch_user_cntnt_target.value;
    
    if(srch_user_target=="sign") {
    	
    	frm.sign_plndman_id.value 		= obj.epid;
    	frm.sign_plndman_nm.value 		= obj.cn;
    	frm.sign_plnd_dept_nm.value 	= obj.department;
    	frm.sign_plndman_jikgup_nm.value = obj.title;
    	frm.sign_plndman_search_nm.value= obj.cn;
    	
    	$('#plndman').html('');
    	$('#plndman').append('&nbsp;&nbsp;' + obj.title+'/'+obj.department);
    
    } else if(srch_user_target=="seal"){

	  	frm.seal_ffmtman_id.value 		= obj.epid;
    	frm.seal_ffmtman_nm.value 		= obj.cn;
    	frm.seal_ffmt_dept_nm.value 	= obj.department;
    	frm.seal_ffmtman_jikgup_nm.value = obj.title;
    	frm.seal_ffmtman_search_nm.value= obj.cn;
    	
    	$('#ffmtman').html('');
    	$('#ffmtman').append('&nbsp;&nbsp;' + obj.title+'/'+obj.department);
    	
    } else if(srch_user_target=="contract"){
    	frm.cntrt_respman_id.value 			= obj.epid;
    	frm.cntrt_respman_nm.value 			= obj.cn;
    	frm.cntrt_respman_jikgup_nm.value 	= obj.title;
    	frm.cntrt_resp_dept.value			= obj.departmentnumber;
    	frm.cntrt_resp_dept_nm.value 		= obj.department;
    	frm.reg_intnl_dept_cd.value		= obj.epindeptcode; //내부부서코드
    	
    	frm.cntrt_respman_search_nm.value	= obj.cn;
    	
    	$('#cntrt_respman').html('');
    	$('#cntrt_respman').append('&nbsp;&nbsp;'+obj.title+'/'+obj.department);

    } else if(srch_user_target=="hdovman"){
    	  frm.org_hdovman_id.value = obj.epid;
    	  frm.org_hdovman_nm.value = obj.cn;
    	  frm.org_hdovman_search_nm.value = obj.cn;
    	  frm.org_hdov_dept_nm.value = obj.department;
    	  frm.org_hdovman_jikgup_nm.value = obj.title;
    	  
    	  $('#hdovman').html('');
      	$('#hdovman').append('&nbsp;&nbsp;'+obj.title+'/'+obj.department);
 
    } else if(srch_user_target=="orgmng"){

	  	  frm.org_mng_reqman_id.value = obj.epid;
		  frm.org_mng_reqman_nm.value = obj.cn;
		  //frm.org_mng_reqman_search_nm.value = obj.cn;
		  frm.org_mng_reqman_search_nm.value = '';
		  frm.org_mng_req_dept_nm.value = obj.department;
		  frm.org_mng_req_jikgup_nm.value = obj.title;
		  
		  $('#orgmng').html('');
	  	$('#orgmng').append('&nbsp;&nbsp;'+obj.cn+'/'+obj.title+'/'+obj.department);
    } else if(srch_user_target=="sign2"){
    	
    	frm.signman_id.value = obj.epid;
    	frm.signman_nm.value = obj.cn;
    	frm.signman_search_nm.value = obj.cn;;
    	frm.sign_dept_nm.value = obj.department;
    	frm.signman_jikgup_nm.value = obj.title;
    	
    	$('#signman').html('');
    	$('#signman').append(obj.cn+'/'+obj.title+'/'+obj.department);
    }
}

function setSealMethod(val) {
	
	if(val=="C02101") { //직인(사용인감날인)
		$('#seal_td').attr("colspan", "3");
		$('#sign_th').css("display","none");
		$('#sign_td').css("display","none");
		$('#seal_th').css("display","block");
		$('#seal_td').css("display","block");
		$('#sign2_tr1').css("display","none");
		$('#sign2_tr2').css("display","none");
		
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
		$('#seal_td').attr("colspan", "3");
		$('#sign_th').css("display","none");
		$('#sign_td').css("display","none");
		$('#seal_th').css("display","block");
		$('#seal_td').css("display","block");
		$('#sign2_tr1').css("display","none");
		$('#sign2_tr2').css("display","none");
	
		$('#seal_ffmtman_id').val('');
		$('#seal_ffmtman_nm').val('');
		$('#seal_ffmt_dept_cd').val('');
		$('#seal_ffmt_dept_nm').val('');
		$('#seal_ffmtman_jikgup_nm').val('');
		$('#seal_ffmtman_search_nm').val('');
		$('#ffmtman').html('');
		
		$('input[name=seal_ffmtman_search_nm]').removeAttr("disabled");	
		$('#seal_ffmtman_search_img').attr("style","display:").prev().css("border-right","");
		
		
	
	}else { //서명
		
		$('#sign_th').css("display","block");
		$('#sign_td').css("display","block");
		$('#seal_th').css("display","block");
		$('#seal_td').css("display","block");
		$('#sign2_tr1').css("display","block");
		$('#sign2_tr2').css("display","block");
		
		
		$('#sign_td').attr("colspan", "1");
		$('#seal_td').attr("colspan", "1");
		
		$('#seal_ffmtman_id').val('');
		$('#seal_ffmtman_nm').val('');
		$('#seal_ffmt_dept_cd').val('');
		$('#seal_ffmt_dept_nm').val('');
		$('#seal_ffmtman_jikgup_nm').val('');
		$('#seal_ffmtman_search_nm').val('');
		$('#ffmtman').html('');
		
		
		$('#sign_plndman_id').val('');
		$('#sign_plndman_nm').val('');
		$('#sign_plndman_jikgup_nm').val('');
		$('#sign_plnd_dept_nm').val('');
		$('#sign_plndman_search_nm').val('');
		$('#plndman').html('');
		
		$('input[name=seal_ffmtman_search_nm]').removeAttr("disabled");	
		$('#seal_ffmtman_search_img').attr("style","display:").prev().css("border-right","");
	}
}


/*
 * 날인자 검색팝업 
 */
function searchSealPerson() {
	var frm 		= document.frm;
	
	var top_xp  = window.screenLeft;
    var top_yp  = window.screenTop;
	window.open("", "PopUpSealPerson", "left="+top_xp+ ", top="+top_yp+", width=800, height=450, menubar=no, directories=no, resizeble=no, status=no, scrollbars=no");
	
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
 * 인쇄 팝업 띄우기
 */
function openPrint() {
	var frm = document.frm;
	
	PopUpWindowOpen('', '1000','768',true, "popupMyApproval");
	
	frm.action	= "<c:url value='/clm/manage/myApproval.do' />";
	frm.method.value = "forwardPrintPop";
	frm.target = "popupMyApproval";
	frm.submit();
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
            
    PopUpWindowOpen('', "530", "470", true, 'popUpRelation');
    frm.action = "<c:url value='/clm/manage/chooseClient.do' />";
    frm.method.value="forwardChooseClientPopup";
    frm.target = "popUpRelation";
    frm.submit();
 }
 /**
 *의뢰자 리턴 객체 받기
 */
 function setListClientInfo(returnValue) {
     
    var arrReturn = returnValue.split("!@#$");
    var innerHtml ="";	
    
    $('#id_trgtman_nm').html("");
    
    if(arrReturn[0]=="") {
    	return ;
    }
    
    for(var i=0; i < arrReturn.length;i++) {
    	var arrInfo = arrReturn[i].split("|");

    	if((i != 0 && i != 1) && (i % 3 == 0)){
			innerHtml += "<br/>";
    	}
    	if(i != 0 && (i % 3 != 0)){
    		innerHtml += ",";
    	}
    		innerHtml += "<input type='hidden' name='arr_demnd_seqno' id='arr_demnd_seqno' value='"+ arrInfo[0] +"' />";
    		innerHtml += "<input type='hidden' name='arr_trgtman_id' id='arr_trgtman_id' value='"+ arrInfo[1] +"' />";
    		innerHtml += "<input type='hidden' name='arr_trgtman_nm' id='arr_trgtman_nm' value='"+ arrInfo[2] +"' />";		        	
    		innerHtml += "<input type='hidden' name='arr_trgtman_jikgup_nm' id='arr_trgtman_jikgup_nm' value='"+ arrInfo[3] +"' />";
    		innerHtml += "<input type='hidden' name='arr_trgtman_dept_nm' id='arr_trgtman_dept_nm' value='"+ arrInfo[4] +"' />";
    		
    		innerHtml += arrInfo[2] +"/"+arrInfo[3] + "/" + arrInfo[4] ;  		
    		
    	$('#id_trgtman_nm').html(innerHtml);	
    }
 }

//거래선 팝업
 function customerPop2(customerCd, dodun){
 	PopUpWindowOpen("/clm/draft/customerTest.do?method=detailCustomerShare&customer_cd="+customerCd + "&dodun=" + dodun, 900, 300, false);
 }
 
</script>
</head>
<body>
	<!-- container -->
	<div id="wrap">
		<div id="container">
			<!-- location -->
			<div class="location">
				<img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0" />
				<c:out value='${menuNavi}' escapeXml="false" />
			</div>
			<!-- title -->
			<div class="title">
				<h3>
					<spring:message code="clm.page.msg.manage.oriVerMange" />
				</h3>
			</div>
			<!-- //location -->
			<!-- content -->
			<div id="content">
				<div id="content_in">
				<div class="content-step t_titBtn">
					<ol>
						<li><img src="<%=IMAGE %>/common/step01.gif"	 alt="<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" />" /></li>
						<li><img src="<%=IMAGE %>/common/step02.gif"     alt="<spring:message code="clm.page.msg.manage.step02" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step02" htmlEscape="true" />" /></li>
						<li><img src="<%=IMAGE %>/common/step03.gif"     alt="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" /></li>
						<li><img src="<%=IMAGE %>/common/step04.gif"     alt="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" /></li>
						<li><img src="<%=IMAGE %>/common/step05.gif"     alt="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" /></li>
					</ol>
				</div>
				<!-- //tab01 -->
				<form:form name="frm" id='frm' method="post" autocomplete="off">
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
				<input type="hidden" name="srch_step" value="<c:out value='${command.srch_step}' escapeXml='false'/>" />			<!-- 담당자명 -->
				<input type="hidden" name="srch_biz_clsfcn" value="<c:out value='${command.srch_biz_clsfcn}' escapeXml='false'/>" />			<!-- 계약단계 -->
				<input type="hidden" name="srch_cnsdman_nm" value="<c:out value='${command.srch_cnsdman_nm}' escapeXml='false'/>" />			<!-- 검토자 -->
				<input type="hidden" name="srch_oppnt_nm" value="<c:out value='${command.srch_oppnt_nm}' escapeXml='false'/>" />				<!-- 계약상대방 -->
				<input type="hidden" name="srch_state" value="<c:out value='${command.srch_state}' escapeXml='false'/>" />						<!-- 상태 -->
				<input type="hidden" name="srch_cnclsnpurps_bigclsfcn" value="<c:out value='${command.srch_cnclsnpurps_bigclsfcn}' escapeXml='false'/>" />	<!-- 체결목적-->			
				<input type="hidden" name="isOrgMgr" value="<c:out value='${command.isOrgMgr}' escapeXml='false'/>" />	<!-- 2012/07/12 추가. 원본관리를 위한 구분자-->
				
				<!-- 13/10/29 검색 조건 유지 추가 -->
				<input type="hidden" name="srch_cntrt_nm" value="<c:out value='${command.srch_cntrt_nm}'/>" />						<!-- Contract Title -->
				<input type="hidden" name="srch_cntrt_no" value="<c:out value='${command.srch_cntrt_no}'/>" />						<!-- Contract ID -->
				<input type="hidden" name="srch_str_org_acptday" value="<c:out value='${command.srch_str_org_acptday}'/>" />		<!-- Original Copy -->
				<input type="hidden" name="srch_end_org_acptday" value="<c:out value='${command.srch_end_org_acptday}'/>" />		<!-- Original Copy -->

				<input type="hidden" name="method" id="method" value="" />
				<input type="hidden" name="menu_id" id="menu_id" value="<c:out value='${command.menu_id}'/>" />
				<input type="hidden" name="curPage" id="curPage" value="<c:out value='${command.curPage}'/>" />
				<input type="hidden" name="page_gbn" id="page_gbn" value="<c:out value='${command.page_gbn}'/>" />
				<input type="hidden" name="user_role" id="user_role" value="<c:out value='${command.user_role}'/>" />

				<!-- 계약추가 증가데이타 -->
				<input type="hidden" name="cnsdreq_id" id="cnsdreq_id" value="<c:out value='${lomReq.cnsdreq_id}'/>" />
				<input type="hidden" name="cntrt_id" id="cntrt_id" value="<c:out value='${command.cntrt_id}'/>" />
				<input type="hidden" name="agreeman_id" id="agreeman_id" value="<c:out value='${lomReq.agreeman_id}'/>" />
				<input type="hidden" name="prgrs_status" id="prgrs_status" value="<c:out value='${lomReq.prgrs_status}'/>" />
				<input type="hidden" name="cntrt_untprc_expl" id="cntrt_untprc_expl" value="<c:out value='${lomReq.cntrt_untprc_expl}'/>" />
				
				<!-- ESB 관련 -->
				<input type="hidden" name="srch_user_cntnt_type" value=""/>
				<input type="hidden" name="srch_user_cntnt" value=""/>
				<input type="hidden" name="srch_user_cntnt_target" value=""/>
				<input type="hidden" name="reg_intnl_dept_cd" id="reg_intnl_dept_cd" value=""/>
				
				<input type="hidden" name="seqno" id="seqno" value="0"/>
				<input type="hidden" name="conListGu" value="99" />
				<input type="hidden" name="contents"		id="contents"     value="" />
		
				<!-- Sungwoo added search parameter 2014-06-12 -->
				<input type="hidden" name="srch_division" value="<c:out value='${command.srch_division}'/>" />					<!-- Original Copy -->
				<input type="hidden" name="srch_vendor_type" value="<c:out value='${command.srch_vendor_type}'/>" />					<!-- Original Copy -->
				<input type="hidden" name="srch_vendor_type_detail" value="<c:out value='${command.srch_vendor_type_detail}'/>" />		<!-- Original Copy -->
				
					<!-- 첨부파일정보 시작 -->
					<input type="hidden" name="fileInfos" value="" />
					<!-- 계약서 파일 정보 -->
					<input type="hidden" name="fileInfos2" value="" />
					<!-- 기타 파일 정보 -->
					<input type="hidden" name="fileInfos3" value="" />
					<!-- 사전검토 파일 정보 -->
					<input type="hidden" name="fileInfos4" value="" />
					<!-- 단가 파일 정보 -->
					<input type="hidden" name="fileInfos5" value="" />
					<input type="hidden" name="fileInfos6" value="" />
					<input type="hidden" name="fileInfos10"  value="" /> <!-- 종료관리 첨부파일정보 -->
					<input type="hidden" name="fileInfos33" value="" />
					<!-- 별첨 파일 정보 -->
					<input type="hidden" name="file_bigclsfcn" value="F012" />
					<!-- 대분류 -->
					<input type="hidden" name="file_midclsfcn" value="" />
					<!-- 중분류 -->
					<input type="hidden" name="file_smlclsfcn" value="" />
					<!-- 소분류 -->
					<input type="hidden" name="ref_key" value="" />
					<!-- 키값 -->
					<input type="hidden" name="view_gbn" value="download" />
					<!-- 첨부파일 화면 -->
					<input type="hidden" name="fileInfoName" value="" />
					<!-- 저장될 파일 정보 -->
					<input type="hidden" name="fileFrameName" value="" />
					<!-- 소분류 -->
					<input type="hidden" name="multiYn" value="" />
					<!-- 멀티여부 -->

					<!-- 첨부파일정보 시작 -->
					<input type="hidden" name="module_id" id="module_id" value="<c:out value='${lomReq.module_id}'/>" />
					<input type="hidden" name="mis_id" id="mis_id" value="<c:out value='${lomReq.mis_id}'/>" />
				    <input type="hidden" name="mis_id2" />
				    
				    <!-- 관련자 데이타 설정  -->
	   				<input type="hidden" name="chose_client" id="chose_client" />

					<!-- title -->
					<div class="title_basic">
						<h4><spring:message code="clm.page.msg.manage.contInfo" /></h4>
					</div>
					<!-- //title -->
					<!-- button -->
					<div class="btnwrap mt_22" id='button_modify' style="display: none;">
						<span class="btn_all_w"><span class="save"></span><a href="javascript:goInsert();"><spring:message code="clm.page.msg.common.save" /></a></span>
						<span class="btn_all_w"><span class="cancel"></span><a href="javascript:changeMode('one');"><spring:message code="clm.page.msg.common.cancel" /></a></span>
					</div>
					
					<div class="btnwrap mt_22" id='button_modify2' style="display: none;">
						<span class="btn_all_w"><span class="save"></span><a href="javascript:goInsert2();"><spring:message code="clm.page.msg.common.save" /></a></span>
						<span class="btn_all_w"><span class="cancel"></span><a href="javascript:changeMode('one');"><spring:message code="clm.page.msg.common.cancel" /></a></span>
					</div>
					
					<div class="btnwrap mt_22" id='button_view' style="display: none;">
						<span class="btn_all_w"><span class="modify"></span><a href="javascript:changeMode('modify');"><spring:message code="clm.page.msg.manage.modifyContInfo" /></a></span>
						<span class="btn_all_w"><span class="confirm"></span><a href="javascript:changeMode('modify2');"><spring:message code="clm.page.msg.manage.orgInOut" /></a></span>
						<span class="btn_all_w"><span class="print"></span><a href="javascript:openPrint();"><spring:message code="clm.page.msg.manage.printCover" /></a></span>
						<span class="btn_all_w"> <span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /> </a> </span>
					</div>
					<div class="btnwrap mt_22" id='button_history' style="display: none;">
						<span class="btn_all_w"> <span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /> </a> </span>
					</div>
					
					<!-- //button -->
					<!-- toptable-->
					<div id="contract-master-info"></div>
					<!-- 종료정보 -->
					<div id="contract-CompletionInfo"></div>
					
					<!-- 이력정보start -->
					<div id="contractHis-list"></div>
					<div id="consultation-his-list"></div>
					<!-- 이력정보end -->
					
					<!-- button -->
					<div class="btnwrap tC mt20" id='button_modify' style="display: none;" >
						<span class="btn_all_w"><span class="save"></span><a href="javascript:goInsert();"><spring:message code="clm.page.msg.common.save" /></a></span>
						<span class="btn_all_w"><span class="cancel"></span><a href="javascript:changeMode('one');"><spring:message code="clm.page.msg.common.cancel" /></a></span>
					</div>
					
					<div class="btnwrap tC mt20" id='button_modify2' style="display: none;">
						<span class="btn_all_w"><span class="save"></span><a href="javascript:goInsert2();"><spring:message code="clm.page.msg.common.save" /></a></span>
						<span class="btn_all_w"><span class="cancel"></span><a href="javascript:changeMode('one');"><spring:message code="clm.page.msg.common.cancel" /></a></span>
					</div>
					
					<div class="btnwrap tC mt20" id='button_view' style="display: none;">
						<span class="btn_all_w"><span class="modify"></span><a href="javascript:changeMode('modify');"><spring:message code="clm.page.msg.manage.modifyContInfo" /></a></span>
						<span class="btn_all_w"><span class="confirm"></span><a href="javascript:changeMode('modify2');"><spring:message code="clm.page.msg.manage.orgInOut" /></a></span>
						<span class="btn_all_w"><span class="print"></span><a href="javascript:openPrint();"><spring:message code="clm.page.msg.manage.printCover" /></a></span>
						<span class="btn_all_w"> <span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /> </a> </span>
					</div>
					<div class="btnwrap tC mt20" id='button_history' style="display: none;">
						<span class="btn_all_w"> <span class="list"></span><a href="javascript:goList();"><spring:message code='clm.page.button.contract.list' /> </a> </span>
					</div>
					<!-- //button -->
				</form:form>
				</div>
			</div>
			<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
		</div>
	</div>
	<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
	<!-- // footer -->
</body>
</html>