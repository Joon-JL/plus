<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>
<%@ page import="com.sds.secframework.common.util.Token"%>

<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%--
/**
 * 파  일  명 : Sign_d.jsp
 * 프로그램명 : 날인 상세
 * 설      명 : 
 * 작  성  자 : 박병주
 * 작  성  일 : 2013.05
 */
--%>
<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>
<%
// 결재 이미지 
  String strUrl = (request.getRequestURL()).toString();
  String strServer = strUrl.substring(0, strUrl.indexOf("/", 7) + 1);
%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="las.main.title" /></title>
<LINK href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script language="javascript">
<!--
	/**
	* 초기화면 로딩
	*/
	$(document).ready(function(){
		
		// alert("<%=strServer%>");
	
		var frm = document.frm;
		
		var yn = "<c:out value='${command.makeHtml}'/>";
		
		// 날인등록시 상신버튼을 클릭한 경우 로딩하면서상신 팝업 처리
		
		// alert("품의 팝업 시전 전의 값 = " + "<c:out value='${command.go_pumui}'/>" );
		
		if("<c:out value='${command.go_pumui}'/>"=="Y"){
			
			//alert("makeHtml = " +"<c:out value='${command.makeHtml}'/>");
			
			//makeApprovalHTML();
			
			//PopUpWindowOpen('', 1000, 800, true);
			PopUpWindowOpenTargetAppl('', 1000, 800, true,'esbApprWindow');
			frm.target = "esbApprWindow";			
			frm.action = "<c:url value='/secfw/esbApproval.do' />";
			frm.method.value = "forwardApproval";
			frm.submit();
			
		}

		//첨부파일창 load
		initPage();

		// 캘린더 초기화
		$(".text_calendar").each(function(index){    
			initCal($(this).attr("id"));  	
		});

		// 반출일을 반출신청 기간으로 미리 세팅
		$("#prc_ymd_from").val("<c:out value='${lom.apl_ymd_from}' />");
		$("#prc_ymd_to").val("<c:out value='${lom.apl_ymd_to}' />");
		
		//반납자 검색-ESB임직원 검색
		$("input[name=srchReceiverNm]").keypress(function(event){
			if(event.keyCode == "13") {
				searchUser();
				$("input[name=srchReceiverNm]").val('');
				return false;				
			}
		});
		
		//반납자 검색-ESB임직원 검색
		$('#srchReceiverBtn').click(function(){
			searchUser();
			return false;
		});
	});
	
	/**
	* 첨부파일창 load
	*/
	function initPage(){
	    frm.target          = "fileList";
	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    = "forwardClmsFilePage";
	    frm.submit();   
	}
	
	/**
	* 목록으로 가기
	*/
	function goList() {
		var frm = document.frm;
		frm.action = "<c:url value='/clm/sign/signManage.do' />";
		frm.method.value = "<c:out value='${command.forwardFrom}'/>";
		viewHiddenProgress(true);
		frm.target = "_self";
		frm.submit();
	}
	
	/**
	* 수정처리
	*/
	function modify() {
		var frm = document.frm;
		viewHiddenProgress(true);
		frm.method.value = "forwardSignModify";		
		frm.action = "<c:url value='/clm/sign/signManage.do' />";
		frm.target = "_self";
		frm.submit();
	}
	
	/**
	* 삭제처리
	*/
	function remove(){
		var frm = document.frm;
		
		if(confirm("<spring:message code='secfw.msg.ask.delete' />")) {			
			viewHiddenProgress(true);
			frm.method.value = "deleteSign";
			frm.action = "<c:url value='/clm/sign/signManage.do' />";
			frm.target = "_self";
			frm.submit();
		}
	}
	
	/**
	* 메세지 표시 
	*/
	function showMessage(msg){
		if( msg != "" && msg != null && msg.length > 1 ) {
			alert(msg);
		}
	}		
	
	// 날인 / 증명서류발급 / 반납처리
	function prcActioin(flag) {
		var frm = document.frm;		
		
		if(flag == "PRC_SIGN"){	
			
			if($("#prc_seal_no").val()==''){
				alert('<spring:message code="clm.field.signmng.alertmsg16" />');  // 처리인장번호를 입력해 주십시오.
				$("#prc_seal_no").focus();
				return;
			}
						
			<c:if test="${lom.apl_out_yn eq 'Y' && lom.rtn_yn eq 'N'}">
			// 인장반출요청이 있었을 경우
			
				if($("#rtn_seal_no").val()==''){
					alert('<spring:message code="clm.field.signmng.alertmsg17" />');  // 반출인장번호를 입력해 주십시오
					$("#rtn_seal_no").focus();
					return;
				}
				
				if($("#prc_ymd_from").val()==''){
					alert('<spring:message code="clm.field.signmng.alertmsg18" />');  // 반출 기간을 입력해 주십시오
					$("#prc_ymd_from").focus();
					return;					
				}
				
				if($("#prc_ymd_to").val()==''){
					alert('<spring:message code="clm.field.signmng.alertmsg18" />');  // 반출 기간을 입력해 주십시오
					$("#prc_ymd_to").focus();
					return;	
				}	
			
			</c:if>			
			
			var confirmMessage = "<spring:message code='clm.field.signmng.alertmsg19' />"; //날인처리 하시겠습니까?";
			
			if(confirm(confirmMessage)){                    	
			
				viewHiddenProgress(true) ;
				frm.target = "_self";
				frm.action = "<c:url value='/clm/sign/signManage.do' />";
				frm.method.value = "signAction";
				frm.curPage.value = "1";		
				frm.submit();			
			}

		} else if(flag == "PRC_RTN"){
			
			if($("#seal_rtnman_id").val()==''){
				alert('<spring:message code="clm.field.signmng.alertmsg20" />');  // 반납자를 입력해 주십시오.
				$("#seal_rtnman_id").focus();
				return;					
			}
			
			if($("#rtn_ymd").val()==''){
				alert('<spring:message code="clm.field.signmng.alertmsg21" />');  // 반납일을 입력해 주십시오.
				$("#rtn_ymd").focus();
				return;	
			}				
			
			var confirmMessage = "<spring:message code='clm.field.signmng.alertmsg22' />"; //반납처리 하시겠습니까?
			
			if(confirm(confirmMessage)){                    	
			
				viewHiddenProgress(true) ;
				frm.target = "_self";
				frm.action = "<c:url value='/clm/sign/signManage.do' />";
			    frm.method.value = "signAction";
				frm.submit();		
			}			
		
		} else if(flag == "PRC_DOC"){
			
			var confirmMessage = "<spring:message code='clm.field.signmng.alertmsg23' />"; //증명서류 발급처리 하시겠습니까?";
			
			if(confirm(confirmMessage)){                    	
			
				viewHiddenProgress(true) ;
				frm.target = "_self";
				frm.action = "<c:url value='/clm/sign/signManage.do' />";
				frm.method.value = "docAction";
				frm.curPage.value = "1";		
				frm.submit();		
			}					
		}
	}	
	
	/**
	* 임직원 조회 function
	*/	
	var searchUser = function() {

		var frm = document.frm;

		var srchValue = comTrim(frm.srchReceiverNm.value);

		if(srchValue == '' || getByteLength(srchValue) < 4) {
			alert('<spring:message code="secfw.msg.error.nameMinByte" />');  // 최소2글자이상 입력하시기 바랍니다.
			frm.srchReceiverNm.focus();
			return false;
		}
		
		PopUpWindowOpen2('', 850, 450, true);
		frm.target = "PopUpWindow2";
		frm.srch_user_cntnt_type.value = 'userName';
		frm.srch_user_cntnt.value = srchValue;		
		frm.action = "/secfw/esbOrg.do";
		frm.method.value = "listEsbEmployee";
		frm.submit();
				
	};
	
	/**
	* 임직원정보 Setting
	*/	
	function setUserInfos(obj) {

		var name     	= obj.cn;					//이름
		var jikgupCd 	= obj.eptitlenumber;		//직급코드
		var jikgupNm 	= obj.title;				//직급명
		var deptCd	 	= obj.departmentnumber;		//부서코드 	
		var deptNm   	= obj.department;			//부서명
		var compCd	= obj.eporganizationnumber; //회사코드
		var compNm  	= obj.o;					//회사명
		var grpCd       = obj.epsuborgcode;			//총괄코드
		var grpNm		= obj.epsuborgname;			//총괄명
		var email    	= obj.mail;					//메일주소
		var userId   	= obj.epid;					//epid-userid
		var userNm   	= obj.cn; 					//사용자명

		$('#seal_rtnman_id').val(userId);
		$('#seal_rtnman_nm').val(userNm);
		$('#seal_rtnman_dept_nm').val(deptNm);
		$('#seal_rtnman_jikgup_nm').val(jikgupNm);
		
		$('#rtnman').html(userNm + "/" + jikgupNm + "/" +deptNm);		
		$('#srchReceiverNm').val('');
	}
	
	/*
	 * 날인담당 /증명서류 발급담당 팝업 
	 */
	function searchSealPerson(srch_type_gbn,srch_cntnt) {
		var frm 		= document.frm;
		// PopUpWindowOpen('', 800, 450, true, "PopUpSealPerson");
		var top_xp  = window.screenLeft;
        var top_yp  = window.screenTop;
		window.open("", "PopUpSealPerson", "left="+top_xp+ ", top="+top_yp+", width=800, height=450, menubar=no, directories=no, resizeble=no, status=no, scrollbars=no");
		
		frm.srch_type_gbn.value = srch_type_gbn;
		frm.srch_cntnt.value = srch_cntnt;	
		frm.target = "PopUpSealPerson";
	    frm.action = "<c:url value='/clm/manage/chooseSealPerson.do' />";
	    frm.method.value = "forwardChooseSealPersonPopup";
	    frm.submit();   
	}

	/*
	 * 날인자 세팅
	 */
	function setSealPerson(obj) {
		$('#ffmtman').html('');
		$('#ffmtman').append('&nbsp;&nbsp;' + obj);
		$('#gubn_cd').val('SC0101');
	}
	
	/*
	 * 증명서류 발급자 세팅 
	 */
	function setDocPerson(obj) {
		$('#docman').html('');
		$('#docman').append('&nbsp;&nbsp;' + obj);
		$('#gubn_cd').val('SC0102');
	}
	
	/*
	 * 담당자 변경처리 
	 */
	function modifySignMan(gubn_cd) {
		
		if($('#gubn_cd').val()==''){
			alert('<spring:message code="clm.field.signmng.alertmsg24" />');  // 담당자를 변경해 주십시오.
			return;
		} 
		
		if(confirm("<spring:message code='secfw.msg.ask.update' />")) {		
			var frm = document.frm;
			viewHiddenProgress(true);
			frm.method.value = "modifySignMan";		
			frm.action = "<c:url value='/clm/sign/signManage.do' />";
			frm.target = "_self";
			frm.submit();
		}
	}

	/*
	 * 상신 팝업
	 */
	function forwardApproval(){

		var frm = document.frm;
		var top_xp  = window.screenLeft;
	    var top_yp  = window.screenTop;
	    
	    var yn = "<c:out value='${command.makeHtml}'/>";

		var confirmMessage = "<spring:message code='clm.field.signmng.alertmsg26' />"; //품의작성 하시겠습니까? ※향후 결재상태 조회는 mySingle에서 확인하실 수 있습니다.";
			
		if("Y" == yn){
			
			PopUpWindowOpen('', 1000, 800, false);
			frm.target = "PopUpWindow";			
			frm.action = "<c:url value='/secfw/esbApproval.do' />";
			frm.method.value = "forwardApproval";						
			frm.submit();
			
		} else {
			
			if(confirm(confirmMessage)){			
				PopUpWindowOpen('', 1000, 800, false);
				frm.target = "PopUpWindow";			
				frm.action = "<c:url value='/secfw/esbApproval.do' />";
				frm.method.value = "forwardApproval";						
				frm.submit();	
			}
		}
	}
	
	/*
	 * 상신 완료 후처리
	 */
	function setApprovalYN(yn){
		var frm = document.frm;
		if(yn=="Y") {
			frm.action="<c:url value='/clm/sign/signManage.do' />";
	    	frm.target="_self";
	    	frm.method.value="modifySignStatus";
	    	frm.submit();
		} 	
	}
	
	// 팝업 띄우기
	function PopUpWindowOpenTargetAppl(surl, popupwidth, popupheight, bScroll,target)
	{
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
		popupheight = parseInt(popupheight) + 30 ;
		
		Future = "fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,left=" + Left + ",top=" + Top + ",width=" + popupwidth + ",height=" + popupheight;

		PopUpWindow = window.open(surl, target , Future)
		PopUpWindow.resizeTo(parseInt(popupwidth)+10, parseInt(popupheight)+30);
		PopUpWindow.focus();
		
	}//" + (bScroll ? "yes" : "no") + "
	
	//-->
</script>
</head>
<body onLoad='showMessage("<c:out value='${returnMessage}'/>");'>
<!-- Calendar  -->
<div id='popCal' style='position: absolute; visibility: hidden; ridge; width: 10; z-index:1'>
<iframe name="popFrame" src="<c:url value='/secfw/popCalendar.do?method=goPopCalendar' />" frameborder="0" scrolling="no" width="196" height="188"></iframe></div>
<script event=onclick() for=document> 
    if(popCal.style.visibility == "visible"){
      popCal.style.visibility="hidden";
    }
</script>

<div id="wrap">
<!-- container -->
<div id="container">
	<!-- Location -->
	<div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" border="0" alt="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
	<!-- //Location -->	
	
	<!-- title -->
	<div class="title">
		<c:choose>
			<c:when test="${command.forwardFrom eq 'listSign'}">
				<h3><spring:message code="clm.field.signmng.apltab" /><!-- 날인/증명서류 신청 --></h3>
			</c:when>
		<c:otherwise>
			<h3><spring:message code="clm.field.signmng.prctab" /><!-- 날인/증명서류 처리 --></h3>
				</c:otherwise>
		</c:choose>		
	</div>
	<!-- //title -->

	<!-- content -->
	<div id="content">
		<div id="content_in">
		<form:form name="frm" id="frm" autocomplete="off">
		<input type="hidden" id="method" name="method" value="" />
		<input type="hidden" id="menu_id" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
		<input type="hidden" id="curPage" name="curPage" value="<c:out value='${command.curPage}'/>" />
		<input type="hidden" id="forwardFrom" name="forwardFrom" value="<c:out value='${command.forwardFrom}'/>" />
		
		<!-- 상신관련  -->
		<input type="hidden" id="approval_title" name="approval_title" value="<c:out value='${command.app_title}'/>" />
		<input type="hidden" id="approval_content" name="approval_content" value="<c:out value='${command.app_contents}'/>" />		
		<input type="hidden" id="approval_html_gbn" name="approval_html_gbn" value="1" />		
		<input type="hidden" id="approval_option" name="approval_option" value="B" />
		<input type="hidden" id="approval_post_process" name="approval_post_process" value="postProcessSign" />
		<input type="hidden" id="apprvl_clsfcn" name="apprvl_clsfcn" value="N00501" />
		
		<!-- search form -->
		<input type="hidden" name="srch_apl_y"   value="<c:out value='${command.srch_apl_y}'/>" />  
		<input type="hidden" name="srch_apl_m"   value="<c:out value='${command.srch_apl_m}'/>" />
		<input type="hidden" name="srch_apl_prev30d"   value="<c:out value='${command.srch_apl_prev30d}'/>" />
		<input type="hidden" name="srch_seal_rqst_status"   value="<c:out value='${command.srch_seal_rqst_status}'/>" />
		<input type="hidden" name="srch_seal_knd_cd"   value="<c:out value='${command.srch_seal_knd_cd}'/>" />  
		<input type="hidden" name="srch_seal_ffmt_status"   value="<c:out value='${command.srch_seal_ffmt_status}'/>" />
		<input type="hidden" name="srch_title"   value="<c:out value='${command.srch_title}'/>" />
		<input type="hidden" name="srch_apl_cls"   value="<c:out value='${command.srch_apl_cls}'/>" />
		<input type="hidden" name="srch_doc_no"   value="<c:out value='${command.srch_doc_no}'/>" />  
		<input type="hidden" name="srch_doc_issue_status"   value="<c:out value='${command.srch_doc_issue_status}'/>" />
		<input type="hidden" name="srch_sbm"   value="<c:out value='${command.srch_sbm}'/>" />
		<input type="hidden" name="srch_seal_rqstman_nm"   value="<c:out value='${command.srch_seal_rqstman_nm}'/>" />
		<input type="hidden" name="srch_seal_ffmtman_nm"   value="<c:out value='${command.srch_seal_ffmtman_nm}'/>" />  
		<input type="hidden" name="srch_seal_ffmtday_start"   value="<c:out value='${command.srch_seal_ffmtday_start}'/>" />
		<input type="hidden" name="srch_seal_ffmtday_end"   value="<c:out value='${command.srch_seal_ffmtday_end}'/>" />
		<input type="hidden" name="srch_apl_seal_no"   value="<c:out value='${command.srch_apl_seal_no}'/>" />
		
		<!-- key form-->
		<input type="hidden" name="apl_sqn"     value="<c:out value='${lom.apl_sqn}'/>" />		
		<input type="hidden" name=cntrt_id     value="<c:out value='${lom.cntrt_id}'/>" />
		<input type="hidden" name="cnsdreq_id"     value="<c:out value='${lom.cnsdreq_id}'/>" />
		
		<!-- 첨부파일정보 의뢰아이디가 존재 할 경우 계약관리에서 의뢰시에 첨부한 계약서 파일을 가져온다. -->
		<input type="hidden" name="fileInfos"   value="" />
		<c:choose>
			<c:when test="${!empty command.cnsdreq_id }">
				<input type="hidden" name="file_bigclsfcn"  value="F012" />
				<input type="hidden" name="file_midclsfcn" 	value="F01202" />
			 	<input type="hidden" name="file_smlclsfcn" 	value="F0120201" /> 
			 	<input type="hidden" name="ref_key"     value="<c:out value='${command.ref_key}'/>" />
			</c:when>
			<c:otherwise>
				<input type="hidden" name="file_bigclsfcn"  value="N005" />
				<input type="hidden" name="file_midclsfcn" 	value="N00501" />
				<input type="hidden" name="file_smlclsfcn" 	value="" /> 
				<input type="hidden" name="ref_key"     value="<c:out value='${command.apl_sqn}'/>" />
			</c:otherwise>
		</c:choose>				
		
		<input type="hidden" name="view_gbn" 		value="download" />			
		
		<!-- 날인담당자 팝업-->
		<input type="hidden" name="srch_type_gbn" id="srch_type_gbn" value="" />
		<input type="hidden" name="srch_cntnt_type" id="srch_cntnt_type" value="user_nm" />
		<input type="hidden" name="srch_cntnt" id="srch_cntnt" value="" />
		
		<!-- 상신관련-->
		<input type="hidden" name="approval_key" id="approval_key" value="<c:out value='${command.apl_sqn}'/>"  />
				
		<!-- ESB 유저 검색 팝업-->
		<input type="hidden" name="srch_user_cntnt_type" id="srch_user_cntnt_type" value="userName" />
		<input type="hidden" name="srch_user_cntnt" id="srch_user_cntnt" value="" />
						
		<!-- 담당자 변경 처리  -->
		<input type="hidden" name="comp_cd"     value="<c:out value='${lom.comp_cd}'/>" />
		<input type="hidden" name="comp_nm"     value="<c:out value='${lom.comp_nm}'/>" />
		<input type="hidden" name="apl_ym"     value="<c:out value='${lom.apl_ym}'/>" />
		<input type="hidden" name="gubn_cd" id="gubn_cd"   value="" />
		
		<!-- 날인 처리  -->
		<input type="hidden" name="apl_out_yn"     value="<c:out value='${lom.apl_out_yn}'/>" />
	
	<!-- button -->
	<div class="tR mt5">
		<c:if test="${!auth_sign_process && !auth_doc_process && auth_modify}">
			<c:if test="${lom.seal_rqst_status eq 'AP0101'}">
				<span class="btn_all_w"><span class="sangsin"></span><a href="javascript:forwardApproval();"><spring:message code="clm.page.button.contract.cosultationapproval" /><!-- 품의상신 --></a></span>
				<span class="btn_all_w"><span class="edit"></span><a href="javascript:modify()"><spring:message code="las.page.button.modify" /></a></span>
				<span class="btn_all_w"><span class="delete"></span><a href="javascript:remove()"><spring:message code="las.page.button.delete" /></a></span>
			</c:if>
			<c:if test="${lom.seal_rqst_status eq 'AP0103'}">
				<span class="btn_all_w"><span class="delete"></span><a href="javascript:remove()"><spring:message code="las.page.button.delete" /><!-- 삭제 --></a></span>
			</c:if>
		</c:if>
		<c:if test="${auth_sign_process}">
			<c:if test="${lom.seal_ffmt_status eq 'SL0101' }">
				<c:if test="${lom.seal_rqst_status eq 'AP0101'}">
					<span class="btn_all_w"><span class="sangsin"></span><a href="javascript:forwardApproval();"><spring:message code="clm.page.button.contract.cosultationapproval" /><!-- 품의상신 --></a></span>
				</c:if>			
				<c:if test="${lom.seal_rqst_status eq 'AP0103'}">
					<span class="btn_all_w"><span class="edit"></span><a href="javascript:prcActioin('PRC_SIGN');"><spring:message code="clm.field.signmng.addprcsign" /><!-- 날인처리 --></a></span>
					<span class="btn_all_w"><span class="edit"></span><a href="javascript:modifySignMan('SC0101')"><spring:message code="clm.field.signmng.addprcchgman" /><!-- 담당자변경처리 --></a></span>
				</c:if>			
			</c:if>
			<c:if test="${lom.seal_ffmt_status eq 'SL0102' }">
				<span class="btn_all_w"><span class="edit"></span><a href="javascript:prcActioin('PRC_RTN')"><spring:message code="clm.field.signmng.addprcrtncal" /><!-- 반납처리 --></a></span>
			</c:if>
		</c:if>
		<c:if test="${auth_doc_process}">
			<c:if test="${lom.doc_issue_status eq 'DC0101' }">
				<c:if test="${lom.seal_rqst_status eq 'AP0101'}">
					<span class="btn_all_w"><span class="sangsin"></span><a href="javascript:forwardApproval();"><spring:message code="clm.page.button.contract.cosultationapproval" /><!-- 품의상신 --></a></span>
				</c:if>	
				<c:if test="${lom.seal_rqst_status eq 'AP0103'}">
					<span class="btn_all_w"><span class="edit"></span><a href="javascript:prcActioin('PRC_DOC');"><spring:message code="clm.field.signmng.addprcdocissue" /><!-- 증명발급처리 --></a></span>
					<span class="btn_all_w"><span class="edit"></span><a href="javascript:modifySignMan('SC0102')"><spring:message code="clm.field.signmng.addprcchgman" /><!-- 담당자변경처리 --></a></span>
				</c:if>	
			</c:if>
		</c:if>
		<span class="btn_all_w"><span class="list"></span><a href="javascript:goList();"><spring:message code="las.page.button.lawservice.golist" /><!-- 목록 --></a></span>
	</div>
	<!-- //button -->		
		
		<table id="info_tb" >
					<colgroup>
						<col width="48%" />
						<col width="2%" />
						<col width="50%" />
					</colgroup>
					<tr>
					    <td id="l_tbl" class='vt'>
					    
					    <!-- 기본내역 -->
						<div class="title_basic mt0">
							<h4><spring:message code="clm.field.signmng.basicinfo" /><!-- 기본내역 --></h4>
						</div>
						
						<table class="list_basic" id="lom_basic_info">
							<colgroup>
								<col width="27%" />
								<col width="73%" /> <!-- width size 변경 신성우 2014-04-24 -->
							</colgroup>							
							<tr>
								<th><spring:message code="clm.field.signmng.title" /><!-- 건명 --></th>
								<td class='overflow' id="lom_title" ><c:out value='${lom.title}' escapeXml="false" /></td>
							</tr>
							<tr>
								<th><spring:message code="clm.field.signmng.towhere" /><!-- 제출처 --></th>
								<td class='overflow'><c:out value='${lom.sbm}' escapeXml="false" /></td>
							</tr>
							<tr>
								<th><spring:message code="clm.field.signmng.reqman" /><!-- 신청자 --></th>
							    <td><c:out value='${lom.seal_rqstman_txt}'/></td>
							</tr>
							<tr>
							    <th><spring:message code="clm.field.signmng.reqday" /><!-- 신청일자 --></th>
								<td><c:out value='${lom.reg_dt}'/></td>
							</tr>
							<tr>
								<th><spring:message code="clm.field.signmng.apltype" /><!-- 신청유형 --></th>
								<td>
									<c:out value='${lom.apl_cls_nm}'/>
								</td>
							</tr>
							<tr>
								<th><spring:message code="clm.field.signmng.detaildsc" /><!-- 세부내용 --></th>
								<td style='height:89px'><textarea id="txt"  name="txt"  class="text_area_full01" style="height:80px;" readonly><c:out value='${lom.txt}' escapeXml="false" /></textarea>
								<input type="hidden" name="rel_txt" id="rel_txt"  value="<c:out value='${command.txt}'/>" /> 									
								</td>
							</tr>
 							<tr id="tr_attach">
								<th ><spring:message code="las.page.field.lawservice.attach" /><!-- 첨부파일--></th>
								<td class="vt" style='height:101px'>
									<iframe src="<c:url value='/clm/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="90px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
								</td>
							</tr> 
						</table>
						<!-- // 기본내역 -->
					    
					    </td>
					    <td> &nbsp;</td>
						 <td id="r_tbl"  class='vt'>
						 
						 	<!-- 신청내역 -->
						<div class="title_basic mt0">
							<h4><spring:message code="clm.field.signmng.aplinfo" /><!-- 신청내역 --></h4>
						</div>
						
						<table class="list_basic" id="lom_apl_info">
							<colgroup>
								<col width="26%" />
								<col width="74%" /> <!-- width size 변경 신성우 2014-04-24 -->
							</colgroup>
							<tr>
								<th ><c:if test="${lom.seal_yn eq 'Y'}"><img src="<%=IMAGE%>/icon/icon_check01.gif" /></c:if> <spring:message code="clm.field.signmng.apl" /><!-- 날인신청 --></th>
								<td><c:out value='${lom.seal_knd_nm}' /></td>
							</tr>
							<tr>
								<th ><c:if test="${lom.apl_out_yn eq 'Y'}"><img src="<%=IMAGE%>/icon/icon_check01.gif" /></c:if> <spring:message code="clm.field.signmng.aploutreq" /><!-- 인장반출신청 --></th>
								<td style='height:45px'>
									 <spring:message code="clm.field.signmng.aplno" /><!-- 반출인장번호 --> <c:out value='${lom.apl_seal_no}' escapeXml="false" /><br>
									<div class='ico_alert01 mt5'><spring:message code="clm.field.signmng.apprmsg3" /><!-- 회사 외부로의 인장반출을 신청합니다.[임원결재필요] --></div>
								</td>
							</tr>
							<tr>
								<th ><spring:message code="clm.field.signmng.outfromto" /><!-- 반출기간 --></th>
								<td><c:out value='${lom.apl_ymd_txt}' /></td>
							</tr>
							<tr>
								<th class='rightline'><c:if test="${lom.doc_yn eq 'Y'}"><img src="<%=IMAGE%>/icon/icon_check01.gif" /></c:if> <spring:message code="clm.field.signmng.doc" /><!-- 증명서류 --></th>
								<td class='zero' >
									<div class='seal_scr' id="seal_scr" style='height:124px'>
										<dl class='seal_doc'>
											<dt><spring:message code="clm.field.signmng.cert1" /><!-- 법인인감증명서 --></dt>
											<dd><c:if test="${!empty lom.doc1 }"><c:out value='${lom.doc1}' /> <spring:message code="clm.field.signmng.adddoccnt" /></c:if></dd><!-- 부 -->
										</dl>
										<dl class='seal_doc'>
											<dt><spring:message code="clm.field.signmng.cert2" /><!-- 등기부등본 --></dt>
											<dd><c:if test="${!empty lom.doc2 }"><c:out value='${lom.doc2}' /> <spring:message code="clm.field.signmng.adddoccnt" /></c:if></dd><!-- 부 -->
										</dl>
										<dl class='seal_doc'>
											<dt><spring:message code="clm.field.signmng.cert3" /><!-- 등기부초본 --></dt>
											<dd><c:if test="${!empty lom.doc3 }"><c:out value='${lom.doc3}' /> <spring:message code="clm.field.signmng.adddoccnt" /></c:if></dd><!-- 부 -->
										</dl>
										<dl class='seal_doc'>
											<dt>
												<spring:message code="clm.field.signmng.cert4" /><!-- 사용인감계 --> 
												<c:if test="${!empty lom.doc4 }">
													<span><spring:message code="clm.field.signmng.apprmsg5" /><!-- 날인신청이 필요합니다. --></span><br>												
													<span class='use '><spring:message code="clm.field.signmng.usem" /><!-- 사용용도 --> :<c:out value='${lom.use_summ}' escapeXml="false" /></span>
												</c:if> 
											</dt>
											<dd><c:if test="${!empty lom.doc4 }"><c:out value='${lom.doc4}' /> <spring:message code="clm.field.signmng.adddoccnt" /></c:if></dd><!-- 부 -->
										</dl>
										<dl class='seal_doc'>
											<dt><spring:message code="clm.field.signmng.cert5" /><!-- 일반위임장 --></dt>
											<dd><c:if test="${!empty lom.doc5 }"><c:out value='${lom.doc5}' /> <spring:message code="clm.field.signmng.adddoccnt" /></c:if></dd><!-- 부 -->
										</dl>
										<dl class='seal_doc'>
											<dt><spring:message code="clm.field.signmng.cert6" /><!-- 공증위임장 --></dt>
											<dd><c:if test="${!empty lom.doc6 }"><c:out value='${lom.doc6}' /> <spring:message code="clm.field.signmng.adddoccnt" /></c:if></dd><!-- 부 -->
										</dl>
										<dl class='seal_doc bottom_noline'>
											<dt><spring:message code="clm.field.signmng.cert7" /><!-- 대표이사신분증 --></dt>
											<dd><c:if test="${!empty lom.doc7 }"><c:out value='${lom.doc7}' /> <spring:message code="clm.field.signmng.adddoccnt" /></c:if></dd><!-- 부 -->
										</dl>
									</div>
								</td>	
							</tr>
							<tr>
								<th ><spring:message code="clm.field.signmng.doc" /><!-- 증명서류 --> <spring:message code="clm.page.field.decidearbitrarilyre.note" /><!-- 비고 --></th>
								<td>
									<c:out value='${lom.doc_scrtxt}' escapeXml="false" />
								</td>
							</tr>
							<tr>
								<th ><spring:message code="clm.field.signmng.desc" /><!-- 용어설명 --></th>
								<td class='zero'>
								<div class='seal_scr font_11' style='padding:4px 10px; height:77px;'>
									<span class='title01'>1. <spring:message code="clm.field.signmng.adddoc1" /><!-- 법인인감 --></span> - <spring:message code="clm.field.signmng.apprmsg6" /><!-- 법원에 등록한 대표이사 인장. 법인인감증명서로 진위를 증명함. --><br> 
									<span class='title01'>2. <spring:message code="clm.field.signmng.adddoc2" /><!-- 사용인감 --></span> -<spring:message code="clm.field.signmng.apprmsg7" /><!-- 법원에 등록하지 않은 대표이사 인장.사용인감계 및 법인인감증명서로 진위 증명 시 법인인감과 동일한 효력.  --><br>
									<span class='title01'>3. <spring:message code="clm.field.signmng.adddoc3" /><!-- 사인 --></span> - <spring:message code="clm.field.signmng.apprmsg8" /><!-- 회사 명의의 사각형 인장. 주로 회사 명의의 임명장, 상장 등에 사용. --><br>
								</div>
								</td>
							</tr>
						</table>
						<!-- // 신청내역 -->
						 
						 </td>
					</tr>
				</table>	
				
				
				<!-- 처리 내역 -->
				<!-- 처리 내역 -->
				
				<!-- 날인담당자  -->
				<c:if test="${auth_sign_process}">				
				<div id="acl_out_cont" >
					
					<!-- 날인처리내역 -->
					<div class="title_basic mt20">
						<h4><spring:message code="clm.field.signmng.addprcsignhis" /><!-- 날인처리내역 --></h4>
					</div>				
					
					<table class="list_basic">
						<colgroup>
							<col width="13%" />
							<col width="37%" />
							<col width="13%" />
							<col width="37%" />
						</colgroup>
							<!-- 날인/증명서류를 처리 했을 경우  -->
							<c:if test="${lom.seal_ffmt_status eq 'SL0103' || lom.seal_ffmt_status eq 'SL0102' || lom.doc_issue_status eq 'DC0102'}">
								<tr>
									<th><spring:message code="clm.field.signmng.signprcday" /><!-- 날인처리일 --></th>
									<td ><c:out value='${lom.seal_ffmtday_txt}'  /></td>
									<th><spring:message code="clm.field.signmng.docissueday" /><!-- 증명서류 발급처리일 --></th>
									<td ><c:out value='${lom.doc_issueday_txt}' /></td>
								</tr>
							</c:if>
							<!-- // 날인/증명서류를 처리 했을 경우  -->
							<c:if test="${lom.seal_ffmt_status eq 'SL0101'}">
								<tr>
									<th><spring:message code="clm.field.signmng.prcaplno" /><!-- 처리인장번호 --></th>
									<td colspan="3"><input type='text' class='text w_50 mL10' id="prc_seal_no" name="prc_seal_no" mexlength="30" value="<c:out value='${lom.apl_seal_no}' />" /></td>							
								<tr>
							</c:if>
							<!-- 날인처리나 반출처리를 처리 했을 경우  -->								
							<c:if test="${lom.seal_ffmt_status eq 'SL0102' || lom.seal_ffmt_status eq 'SL0103' }">
								<tr>
									<th><spring:message code="clm.field.signmng.prcaplno" /><!-- 처리인장번호 --></th>
									<td colspan="3" ><c:out value='${lom.prc_seal_no}' escapeXml="false" /></td>
								</tr>
							</c:if>
							<!-- //날인처리나 반출처리를 처리 했을 경우    -->								
					</table>
					<!-- // 날인처리내역 -->					
					
					<!-- 인장반출내역 -->
					<c:if test="${lom.apl_out_yn eq 'Y'}">
					<div class="title_basic mt20">
						<h4><spring:message code="clm.field.signmng.aplouttab" /><!-- 인장반출 내역 --></h4>
					</div>	
					
					<table class="list_basic">
						<colgroup>
							<col width="13%" />
							<col width="37%" />
							<col width="13%" />
							<col width="37%" />
						</colgroup>
						
						<!-- 반출전 일 경우  -->
						<c:if test="${lom.seal_ffmt_status eq 'SL0101' }">
							<tr>
								<th><spring:message code="clm.field.signmng.aplno" /><!-- 반출인장번호 --></th>
								<td ><input type='text' class='text w_50 mL10' id="rtn_seal_no" name="rtn_seal_no" mexlength="30" value="<c:out value='${lom.apl_seal_no}' />" /></td>
								<th><spring:message code="clm.field.signmng.outfromto" /><!-- 반출기간 --></th>
								<td >
									<div id="apl_out_div" >
										<input type="text" id="prc_ymd_from" name="prc_ymd_from" value=""  class="text_calendar" value="<c:out value='${lom.apl_ymd_from}' />" /> ~
										<input type="text" id="prc_ymd_to" name="prc_ymd_to" value=""  class="text_calendar" value="<c:out value='${lom.apl_ymd_to}' />" />
									</div>
								</td>
							</tr>
						</c:if>
						<!-- // 반출중 일 경우  -->
						
						<!-- 반출중 일 경우  -->
						<c:if test="${lom.seal_ffmt_status eq 'SL0102' }">
							<tr>
								<th><spring:message code="clm.field.signmng.aplno" /><!-- 반출인장번호 --></th>
								<td ><c:out value='${lom.rtn_seal_no}' escapeXml="false" /></td>
								<th><spring:message code="clm.field.signmng.outfromto" /><!-- 반출기간 --></th>
								<td ><c:out value='${lom.prc_ymd_txt}' /></td>
							</tr>
							<tr>
								<th><spring:message code="clm.field.signmng.manrtn" /><!-- 반납자 --></th>
								<td ><input type="text"   name="srchReceiverNm" id="srchReceiverNm" value="" style="width:110px" class="text_search" /><img id="srchReceiverBtn" src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search" />
								<span id="rtnman">&nbsp;&nbsp;<c:out value='${lom.seal_rtnman_nm}' /></span>				
								</td>
								<th><spring:message code="clm.field.signmng.rtnday" /><!-- 반납일 --></th>
								<td ><input type="text" id="rtn_ymd" name="rtn_ymd" value=""  class="text_calendar" value="<c:out value='${lom.rtn_ymd}' />" /></td>
							</tr>
						</c:if>
						<!-- // 반출중 일 경우  -->
						
						<!-- 반납완료 일 경우  -->
						<c:if test="${lom.seal_ffmt_status eq 'SL0103' }">
							<tr>
								<th><spring:message code="clm.field.signmng.aplno" /><!-- 반출인장번호 --></th>
								<td ><c:out value='${lom.rtn_seal_no}' escapeXml="false" /></td>
								<th><spring:message code="clm.field.signmng.outfromto" /><!-- 반출기간 --></th>
								<td ><c:out value='${lom.prc_ymd_txt}' /></td>
							</tr>
							<tr>
								<th><spring:message code="clm.field.signmng.manrtn" /><!-- 반납자 --></th>
								<td ><c:out value='${lom.seal_rtnman_txt}' /></td>
								<th><spring:message code="clm.field.signmng.rtnday" /><!-- 반납일 --></th>
								<td ><c:out value='${lom.rtn_ymd_txt}' /></td>
							</tr>
						</c:if>
						<!-- // 반납완료 일 경우  -->
						
					</table>
					</c:if>
					<!-- // 인장반출내역 -->
					
				</div>
				</c:if>
				<!-- // 날인담당자 -->
				
				<!-- 일반사용자  -->
				<c:if test="${!auth_sign_process}">
					<!--  날인처리 내역이 반출중 / 처리완료 일 경우 -->
					<c:if test="${lom.seal_ffmt_status eq 'SL0102' || lom.seal_ffmt_status eq 'SL0103'  }">
						<div id="acl_out_cont" >
							<div class="title_basic mt20">
								<h4><spring:message code="clm.field.signmng.addprcsignhis" /><!-- 날인처리내역 --></h4>
							</div>
							
							<table class="list_basic">
								<colgroup>
									<col width="13%" />
									<col width="37%" />
									<col width="13%" />
									<col width="37%" />
								</colgroup>
								<c:if test="${lom.seal_ffmt_status eq 'SL0103' || lom.seal_ffmt_status eq 'SL0102' || lom.doc_issue_status eq 'DC0102'}">
									<tr>
										<th><spring:message code="clm.field.signmng.signprcday" /><!-- 날인처리일 --></th>
										<td ><c:out value='${lom.seal_ffmtday_txt}'  /></td>
										<th><spring:message code="clm.field.signmng.docissueday" /><!-- 증명서류 발급처리일 --></th>
										<td ><c:out value='${lom.doc_issueday_txt}' /></td>
									</tr>
								</c:if>
								
								<tr>
									<th><spring:message code="clm.field.signmng.prcaplno" /><!-- 처리인장번호 --></th>
									<td colspan="3"><c:out value='${lom.prc_seal_no}' escapeXml="false" /></td>
								</tr>
							</table>
							
							<!-- 인장반출 VIEW  -->
							<c:if test="${lom.apl_out_yn eq 'Y' }">
							<div class="title_basic mt20">
								<h4><spring:message code="clm.field.signmng.aplouttab" /><!-- 인장반출 내역 --></h4>
							</div>
							
							<table class="list_basic">
								<colgroup>
									<col width="13%" />
									<col width="37%" />
									<col width="13%" />
									<col width="37%" />
								</colgroup>								
								<tr>
									<th><spring:message code="clm.field.signmng.aplno" /><!-- 반출인장번호 --></th>
									<td ><c:out value='${lom.rtn_seal_no}' escapeXml="false" /></td>
									<th><spring:message code="clm.field.signmng.outfromto" /><!-- 반출기간 --></th>
									<td ><c:out value='${lom.prc_ymd_txt}' /></td>
								</tr>
								<tr>
									<th><spring:message code="clm.field.signmng.manrtn" /><!-- 반납자 --></th>
									<td ><c:out value='${lom.seal_rtnman_txt}'/></td>
									<th><spring:message code="clm.field.signmng.rtnday" /><!-- 반납일 --></th>
									<td ><c:out value='${lom.rtn_ymd_txt}' /></td>
								</tr>
							</table>
							</c:if>	
							<!-- // 인장반출 VIEW  -->
							
						</div>
					</c:if>								
					<!-- // 일반사용자 -->	
				</c:if>								
				<!-- // 일반사용자 -->	

				
				<input type="hidden" name="seal_rtnman_id" id="seal_rtnman_id" value="<c:out value='${lom.seal_rtnman_id}' />" /> 
				<input type="hidden" name="seal_rtnman_nm" id="seal_rtnman_nm" value="<c:out value='${lom.seal_rtnman_nm}' />" />
				<input type="hidden" name="seal_rtnman_dept_nm" id="seal_rtnman_dept_nm" value="<c:out value='${lom.seal_rtnman_dept_nm}' />"/>
				<input type="hidden" name="seal_rtnman_jikgup_nm" id="seal_rtnman_jikgup_nm" value="<c:out value='${lom.seal_rtnman_jikgup_nm}' />"/>
				
				<!-- //처리 내역 -->
				<!-- ///처리 내역 -->
				
				<!-- 담당자내역 -->
				<div class="title_basic mt20">
					<h4><spring:message code="clm.field.signmng.manhis" /><!-- 담당자내역 --></h4>
				</div>
				
				<table class="list_basic">
					<colgroup>
						<col width="13%" />
						<col width="87%" />
					</colgroup>
					<tr>
						<th><spring:message code="clm.field.signmng.aplprcman" /><!-- 날인담당자 --></th>
						<td id="ffmtman_td">
												
							<c:choose>
								<c:when test="${auth_sign_process && lom.seal_ffmt_status eq 'SL0101' && lom.seal_rqst_status eq 'AP0103' }"> 
									<input type="text"   name="seal_ffmtman_search_nm" id="seal_ffmtman_search_nm" value="" style="width:110px" class="text_search" onKeyDown="if(event.keyCode==13){event.returnValue = false;javascript:searchSealPerson('SC0101',this.value);}"/><img id="seal_ffmtman_search_img" src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search" onclick="javascript:searchSealPerson('SC0101','');"/>
									<span id="ffmtman">&nbsp;&nbsp;<c:out value='${lom.seal_ffmtman_nm}'/>/<c:out value='${lom.seal_ffmtman_jikgup_nm}'/>/<c:out value='${lom.seal_ffmt_dept_nm}'/></span>
										<input type="hidden" name="seal_ffmtman_id" id="seal_ffmtman_id" value="<c:out value='${lom.seal_ffmtman_id}' />" /> 
										<input type="hidden" name="seal_ffmtman_nm" id="seal_ffmtman_nm" value="<c:out value='${lom.seal_ffmtman_nm}' />" />
										<input type="hidden" name="seal_ffmt_dept_cd" id="seal_ffmt_dept_cd" value="<c:out value='${lom.seal_ffmt_dept_cd}' />"/>
										<input type="hidden" name="seal_ffmt_dept_nm" id="seal_ffmt_dept_nm" value="<c:out value='${lom.seal_ffmt_dept_nm}' />"/>
										<input type="hidden" name="seal_ffmtman_jikgup_nm" id="seal_ffmtman_jikgup_nm" value="<c:out value='${lom.seal_ffmtman_jikgup_nm}' />"/>
								</c:when>
								<c:otherwise>
									<c:out value='${lom.seal_ffmtman_txt}' />
								</c:otherwise>
							</c:choose>
						
						</td>
					</tr>
					
					<tr>
						<th><spring:message code="clm.field.signmng.docissuer" /><!-- 증명서류 발급담당자 --></th>
						<td id="doc_issuer_td">
						
							<c:choose>
								<c:when test="${auth_doc_process && lom.doc_issue_status eq 'DC0101'}">
									<input type="text"   name="doc_issuer_search_nm" id="doc_issuer_search_nm" value="" style="width:110px" class="text_search" onKeyDown="if(event.keyCode==13){event.returnValue = false;javascript:searchSealPerson('SC0102',this.value);}"/><img id="doc_issuer_search_img" src="<%=IMAGE%>/icon/ico_search.gif"  class="cp" alt="search" onclick="javascript:searchSealPerson('SC0102','');" />
									<span id="docman">&nbsp;&nbsp;<c:out value='${lom.doc_issuer_nm}'/>/<c:out value='${lom.doc_issuer_jikgup_nm}'/>/<c:out value='${lom.doc_issuer_dept_nm}'/></span>
									<input type="hidden" name="doc_issuer_id" id="doc_issuer_id" value="<c:out value='${lom.doc_issuer_id}' />"  />
									<input type="hidden" name="doc_issuer_nm" id="doc_issuer_nm" value="<c:out value='${lom.doc_issuer_nm}' />"  />
									<input type="hidden" name="doc_issuer_dept_cd" id="doc_issuer_dept_cd" value="<c:out value='${lom.doc_issuer_dept_cd}' />"/>
									<input type="hidden" name="doc_issuer_dept_nm" id="doc_issuer_dept_nm" value="<c:out value='${lom.doc_issuer_dept_nm}' />"/>
									<input type="hidden" name="doc_issuer_jikgup_nm" id="doc_issuer_jikgup_nm" value="<c:out value='${lom.doc_issuer_jikgup_nm}' />"/>				
								</c:when>
								<c:otherwise>
									<c:out value='${lom.doc_issuer_txt}' />
								</c:otherwise>
							</c:choose>
						
						</td>
					</tr>					

				</table>
				<!-- // 담당자내역 -->
				
				
				<c:if test="${lom.seal_rqst_status eq 'AP0103' || lom.seal_rqst_status eq 'AP0104' }">
				<!-- 승인이력 -->
				<div class="title_basic mt20">
					<h4><spring:message code="clm.field.signmng.apprlst" /><!-- 승인이력 --></h4>
				</div>
				
				<table class="list_basic">
					<colgroup>
						<col width="13%" />
						<col width="37%" />
						<col width="10%" />
						<col width="10%" />
						<col width="32%" />
					</colgroup>
					<tr>
						<th colspan='2'><spring:message code="clm.page.msg.manage.itmName" /><!-- 품의명 --></th>
						<th><spring:message code="clm.page.field.manageCommon.status" /><!-- 상태 --></th>
						<th><spring:message code="clm.field.signmng.prcday" /><!-- 처리일자 --></th>
						<th><spring:message code="clm.field.signmng.manappr" /><!-- 승인자 --></th>
					</tr>
					<c:choose>
						<c:when test="${!empty listSignAppr }">
							<c:forEach var="list" items="${listSignAppr}">
							<tr>
								<td colspan='2' class="overflow" title="<c:out value='${list.title}'/>"><nobr><c:out value='${list.title}'/></nobr></td>
								<td class="tC"><nobr><c:out value='${list.appr_status}'/></nobr></td>
							    <td class="tC"><nobr><c:out value='${list.appr_create_date}'/></nobr></td>
								<td class="overflow tC" title="<c:out value='${list.appr_mans}' />"><c:out value='${list.appr_mans}' escapeXml="false" /></td>
							</tr>
							<tr>
								<th class='sub01 rightline' rowspan='2'><spring:message code="secfw.page.field.approval.approval" /><!-- 결재 --></th>
								<td colspan='4'>[<spring:message code="secfw.page.field.approval.draft" /><!-- 기안 -->]<c:out value='${list.appr_gianja}'/></td>
							</tr>
							<tr>
								<td colspan='4'>[<spring:message code="secfw.page.field.approval.approval" /><!-- 결재 -->]<c:out value='${list.appr_mans}' escapeXml="false" /></td>
							</tr> 
							</c:forEach>
					    </c:when>
					    <c:otherwise>
							<tr>
								<td colspan="5" align="center"><spring:message code="las.msg.succ.noResult" /></td>
							</tr>
					    </c:otherwise>
				</c:choose>

				</table>
				<!-- // 승인이력 -->
				</c:if>
			
			</form:form>
			</div>
			<!-- //content_in -->
	</div>
	<!-- //content -->

	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>

</div>
<!-- //container -->
</div>
<!-- footer  -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
<!-- // footer -->
</body>
</html>