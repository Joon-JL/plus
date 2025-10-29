<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@page import="com.sds.secframework.common.util.PageUtil"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%@page import="com.sds.secframework.common.util.Token"%>
<%--
/**
 * 파  일  명 : LawStaConsultGrpmgr_d.jsp
 * 프로그램명 : 표준계약서 상세 (그룹장/담당자 페이지)
 * 설      명 : 표준계약서의 상세만 보여주게 됩니다.
 * 작  성  자 : 
 * 작  성  일 : 2013.04
 */
--%>
<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->

<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/ui.core.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>

<script language="javascript">

	$(document).ready(function(){
		// 2014-02-10 Kevin. TO-DO 리스트의 값 로드가 중복으로 발생한다. 페이지 로딩 시간이 오래 걸리는 상황에서는 응답시간 지연이 발생하여 주석처리 함. 데이터 Refresh는 메뉴 클릭으로 대신한다.
		/* window.parent.frames['leftFrame'].refreshCount(); */
		
		//getCodeSelectByUpCd("frm", "user_list", '/common/clmsCom.do?method=getLasPersonComboHTML&combo_sys_cd=LAS&combo_type=T&stats=Y');
		//alert('<c:out value='${lom.grpmgr_re_yn}'/>');
	});

	function initPage(){
		
		if(frm.prgrs_status.value == 'S02' || frm.prgrs_status.value == 'S04'){
			
		/*
		if(frm.prgrs_status.value == 'S02') {
		
		if('<c:out value="${auth}"/>' == 'Y'){	
		if(frm.grpmgr_re_yn.value == "Y")
			frm.grpmgr_re_yn.checked = true;
		else
			frm.grpmgr_re_yn.checked = false;
		}
		}*/
		
	    frm.target			= "fileList";
	    frm.action = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value  	= "forwardClmsFilePage";
		frm.fileInfoName.value = "fileInfos";
        frm.fileFrameName.value = "fileList";
        getClmsFilePageCheck('frm');
        //frm.submit();	
		}
		
        if(frm.prgrs_status.value == 'S03' || frm.prgrs_status.value == 'S08' || frm.prgrs_status.value == 'S09'){
        	
        frm.target			= "fileList";
        frm.file_midclsfcn.value = "F00301";
	    frm.action = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value  	= "forwardClmsFilePage";
		frm.fileInfoName.value = "fileInfos";
        frm.fileFrameName.value = "fileList";
        getClmsFilePageCheck('frm');
        //frm.submit();	
        
        /*
        	if(frm.extnl_cnsltyn.value == 'Y'){
	        frm.target			= "fileList3";
	        frm.file_midclsfcn.value = "F00302";
		    frm.action = "<c:url value='/clms/common/clmsfile.do' />";
			frm.method.value  	= "forwardClmsFilePage";
			frm.fileInfoName.value = "fileInfos3";
	        frm.fileFrameName.value = "fileList3";
	        getClmsFilePageCheck('frm');
	        //frm.submit();
	        }
        */
        }
	}
	
	function init(){
		
		alertMessage('<c:out value='${command.return_message}'/>') ;
	}
	
	function remove(){
		if(confirm("<spring:message code="secfw.msg.ask.delete" />")) {
			viewHiddenProgress(true) ;
			var f = document.frm ;
			f.method.value = "deleteStaLawConsult" ;
			f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
			f.target = "_self" ;
			f.submit() ;
		}
	}
	
	function removeAll(){
		if(confirm("<spring:message code="secfw.msg.ask.delete" />")) {
			viewHiddenProgress(true) ;
			var f = document.frm ;
			f.method.value = "deleteAllLawConsult" ;
			f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
			f.target = "_self" ;
			f.submit() ;
		}
	}
	
	function goModifyForm() {
		viewHiddenProgress(true) ;
		var f = document.frm ;
		f.method.value = "forwardModifyStaLawConsult" ;
		f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
		f.target = "_self" ;
		f.submit() ; 
	}
	
	function goReturnForm(check_prgrs_status) {
		viewHiddenProgress(true) ;
		var f = document.frm ;
		f.check_prgrs_status.value = check_prgrs_status;
		f.isGrpmgr.value = "Y";
		f.method.value = "forwardHoldStaLawConsult" ;
		f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
		f.target = "_self" ;
		f.submit() ; 
	}
	
	//발신(회신)
	function send(){
		var f = document.frm;
		var confirmMessage = "<spring:message code='secfw.msg.ask.send' />";
		f.check_prgrs_status.value = "reply";
		f.isGrpmgr.value = "Y";
		f.isReview.value = "N";
		
		if(validateForm(document.frm)) {
			if(confirm(confirmMessage)){
				f.method.value = "modifyStatusStaLawConsult" ;
				f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
				f.target = "_self" ;
	            
				//미결일 때 나모웹에디터 내용 반영				
				if(f.prgrs_status.value == 'S08'){
				 f.main_matr_cont.value = frm.wec[0].MIMEValue;
				 f.cnsd_opnn.value = frm.wec[1].MIMEValue;
				 f.cnsd_opnn_body.value = frm.wec[1].BodyValue +"<br>[<spring:message code='las.page.field.lawconsulting.lgChiefCmt'/>]<br>"+f.apbt_opnn.value;
				}
				
	            viewHiddenProgress(true) ;
	       	 f.submit();   
	        }
		}
	}
	
	//담당자 지정
	function assign(grpmgr_re_yn, solo_yn, ordr_cont){
		$("#respman_list > option").attr("selected","selected");
		
		var confirmMessage = "<spring:message code='secfw.msg.ask.assign' />";
		var f = document.frm;
		
		if(validateForm(document.frm)) {		
			//회신전 결제확인 체크박스 값 세팅
			
			if(grpmgr_re_yn == 'Y'){
				f.grpmgr_re_yn.value = 'Y';
				f.grpmgr_re_yn_value.value = 'Y';
			}
			else{
				f.grpmgr_re_yn.value = 'N';
				f.grpmgr_re_yn_value.value = 'N';
			}
			
			frm.solo_yn.value = solo_yn;
			frm.ordr_cont.value = ordr_cont;
						
			//if(confirm(confirmMessage)){
				f.method.value = "insertRespmanStaLawConsult" ;
				f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
				f.target = "_self" ;
				f.submit();
		   // }
		}
	}
	
	//이관
	function transfer(){
		
		var f = document.frm;
		var confirmMessage = "<spring:message code='secfw.msg.ask.transfer' />";
		var nvalue = $("#transferSel option:selected").val();	
		
		if(nvalue == ""){
			alert("<spring:message code='las.page.field.lawconsulting.chkItem'/>");
			return;
		}
		
		f.check_prgrs_status.value = "transfer";
		f.dmstfrgn_gbn.value = nvalue;

		if($("#fileList").length > 0){
		var oldFileInfos = fileList.POGC.totalFileInfos.value;
		oldFileInfos =replaceFileInfo(oldFileInfos);
		f.fileInfos.value = oldFileInfos;
		}
		
		//if(confirm(confirmMessage)){
         	f.method.value = "transferLawConsult" ;
        	f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
			f.target = "_self" ;
        	f.submit();   
         //}
	}
	
	/*이관시 파일 옮김*/
	function replaceFileInfo(info){
		info =  replace(info, "old|", "add|");
		
		var temp = info.split("|");
		
		for(var i=0; i < temp.length-1; i++ ) {
			tempCol = temp[i].split("*");
			var tempId = tempCol[0];
			info = replace(info, tempId+"*", tempId+"_1*");
		}
		
		return info;
		
	}
	
	function goReviewForm(){
		viewHiddenProgress(true) ;
		
		var f = document.frm ;
		f.method.value = "forwardStaReviewLawConsult" ;
		f.isGrpmgr.value = "Y";
		f.isReview.value = "Y";
		f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
		f.target = "_self" ;
		f.submit() ;
	}
	
	function goList() {
		viewHiddenProgress(true) ;
		var f = document.frm ;
		f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
		f.method.value = "listStaLawConsult" ;
		f.target = "_self" ;
		f.submit() ;
	}
	
	function goDetail(cnslt_no, hstry_no) {
		viewHiddenProgress(true) ;
		var f = document.frm ;
		f.cnslt_no.value = cnslt_no;
		f.hstry_no.value = hstry_no;
		f.method.value = "forwardDetailStaLawConsult" ;
		f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
		f.target = "_self" ;
		f.submit() ; 
	}
	
	function removeRow() {	
		
		var respman_id = $("#respman_list option:selected").val();		
		if ( typeof(respman_id) == 'undefined' ) alert("<spring:message code='las.page.field.lawconsulting.DoLgPic'/>");
		
		$("#respman_list option:selected").remove();   
	}

	/* ====================================================================
	   기       능     : 법무담당자 지정
	   최초 작성일 : 2011-09-26
	   작   성  자    : 심우규	  
	====================================================================== */
	
	function addRow() {
		var nvalue = $("#user_list option:selected").val();	
		var ntext = $("#user_list option:selected").text();	
		
		//표시되는 text에 현재 현황 카운트를 제거하고 이름만 보여주기 위한 처리
		var strArray = ntext.split("(");
		ntext = strArray[0];
		
		var IsUserExist = true;
		$.each($('#respman_list').attr("options"),function(){
			if(nvalue==$(this).val()) IsUserExist = false;
		});
		
		if(IsUserExist){
			$('#respman_list').append("<option value=" + nvalue + " id='" + nvalue + "'>" + ntext + "</option>\n");
		} else {
			alert("<spring:message code='las.page.field.lawconsulting.alreadyAdded'/>");		
			return;	
		}
	}
	
	/**
	* 담당자 팝업
	*/
	function popRespman(){
		var frm = document.frm;
		
		PopUpWindowOpen('', 800, 450, false);
		frm.method.value = "forwardRespmanPopupLawConsult";
		frm.action = "<c:url value='/las/lawconsulting/lawConsult.do' />",
		frm.target = "PopUpWindow";
		
		frm.submit();
	}
	
	/**
	* 담당자리스트 팝업 - 확인
	*/
	function returnResp(respId, respNm) {
		var nvalue;
		
		$('#respman_list > option').remove();
			
	    for(var i=0; i<respId.length; i++){
	    	$('#respman_list').append("<option value=" + respId[i] + ">" + respNm[i] + "</option>");
	    }
	}
	
	function cancelReview(){
		var f = document.frm;
		var confirmMessage = "<spring:message code='secfw.msg.ask.cancel' />";
		
		f.check_prgrs_status.value = 'tempSave';
		
		if(confirm(confirmMessage)){
			f.method.value = "modifyStatusLawConsult" ;
			f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
			f.target = "_self" ;
			f.submit();
		}
		
	}
	
</script>
</head>
<!--  2012.04.26 Alert 수정 modified by hanjihoon  -->
<body onload="init();initPage();" autocomplete="off">

<div id="wrap">
<!-- container -->
<div id="container">
	<!-- Location -->
    <div class="location">
        <img src="<%=IMAGE%>/icon/ico_home.gif" border="0" alt="Home"/><c:out value='${menuNavi}' escapeXml="false"/>
    </div>
  	<!-- //Location -->	
 
 	<div class="title">
			<h3><spring:message code="las.page.field.lawconsulting.stdConReview"/></h3>
	</div>
		
	<!-- content -->
	<div id="content">
	<div id="content_in">
	<form:form name="frm" id="frm" autocomplete="off">
	<input type="hidden" name="method" value=""/>
	<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>"/>
	<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>"/>
	<input type="hidden" name="isForeign" value="<c:out value='${command.isForeign}'/>"/>
	<input type="hidden" name="cnslt_no" value="<c:out value='${command.cnslt_no}'/>"/>
	<input type="hidden" name="hstry_no" value="<c:out value='${command.hstry_no}'/>"/>
	<input type="hidden" name="consult_type" value="<c:out value='${lom.consult_type}'/>"/>
	<input type="hidden" name="prgrs_status" value="<c:out value='${lom.prgrs_status}'/>"/>
	<input type="hidden" name="extnl_cnsltyn" value="<c:out value='${lom.extnl_cnsltyn}'/>"/>
	<input type="hidden" name="if_key_no" value="<c:out value='${lom.if_key_no}'/>"/>
	<input type="hidden" name="if_flag" value="<c:out value='${lom.if_flag}'/>"/>
	<input type="hidden" name="cnsd_opnn_body" id="cnsd_opnn_body" value="" />
	<input type="hidden" name="isGrpmgr" value=""/>
	<input type="hidden" name="grpmgr_re_yn_value" value=""/>
	<input type="hidden" name="grpmgr_re_yn" value="<c:out value='${lom.grpmgr_re_yn}'/>"/>
	<input type="hidden" name="solo_yn" value="<c:out value='${lom.solo_yn}'/>"/>
	<input type="hidden" name="ordr_cont" value="<c:out value='${lom.ordr_cont}'/>"/>
	<input type="hidden" name="isReview" value="" />
	<input type="hidden" name="check_prgrs_status" value="" />
	<input type="hidden" name="main_matr_cont" id="main_matr_cont" value="<c:out value='${lom.main_matr_cont}'/>" />
	<input type="hidden" name="cnsd_opnn" id="cnsd_opnn" value="<c:out value='${lom.cnsd_opnn}'/>" />
	<input type="hidden" name="dmstfrgn_gbn" value=""/>
	<input type="hidden" name="req_dept" id="req_dept" value="<c:out value='${lom.reg_dept}'/>" />
	<input type="hidden" name="reg_operdiv" id="reg_operdiv" value="<c:out value='${lomRq.reg_operdiv}'/>" />
	<input type="hidden" name="otherModify" id="otherModify" value="Y"/>
	
	<!-- 이관시 정보 -->
	<input type="hidden" name="reg_id" value="<c:out value='${lom.reg_id}'/>"/>
	<input type="hidden" name="reg_nm" value="<c:out value='${lom.reg_nm}'/>"/>
	<input type="hidden" name="title" value="<c:out value='${lom.title}'/>"/>
	<input type="hidden" name="reg_dept" value="<c:out value='${lom.reg_dept}'/>"/>
	<input type="hidden" name="reg_dept_nm" value="<c:out value='${lom.reg_dept_nm}'/>"/>
	<input type="hidden" name="reg_telno" value="<c:out value='${lom.reg_telno}'/>"/>
	<input type="hidden" name="regman_jikgup_nm" value="<c:out value='${lom.regman_jikgup_nm}'/>"/>
	<input type="hidden" name="reg_dt" value="<c:out value='${lom.reg_dt}'/>"/>
	<input type="hidden" name="cont" value="<c:out value='${lom.cont}'/>"/>
	<input type="hidden" name="cnsd_dt" value="<c:out value='${lom.cnsd_dt}'/>"/>
	<input type="hidden" name="to_transfer" value="<c:out value='${lom.to_transfer}'/>"/>
	
	<!-- 검색 정보 -->
	<input type="hidden" name="srch_start_ymd" value="<c:out value='${command.srch_start_ymd}'/>"/>
	<input type="hidden" name="srch_end_ymd" value="<c:out value='${command.srch_end_ymd}'/>"/>
	<input type="hidden" name="srch_prgrs_status" value="<c:out value='${command.srch_prgrs_status}'/>"/>
	<input type="hidden" name="srch_title" value="<c:out value='${command.srch_title}'/>"/>
	<input type="hidden" name="srch_cont" value="<c:out value='${command.srch_cont}'/>"/>
	<input type="hidden" name="srch_reg_nm" value="<c:out value='${command.srch_reg_nm}'/>"/>
	<input type="hidden" name="srch_reg_dept" value="<c:out value='${command.srch_reg_dept}'/>"/>
	<input type="hidden" name="srch_consult_type" value="<c:out value='${command.srch_consult_type}'/>"/>
	<input type="hidden" name="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}'/>"/>
	<input type="hidden" name="srch_reg_id" value="<c:out value='${command.srch_reg_id}'/>"/>
	<input type="hidden" name="srch_reception" value="<c:out value='${command.srch_reception}'/>"/>
	<input type="hidden" name="srch_elcomp" value="<c:out value='${command.srch_elcomp}'/>"/>
	
	<!-- 첨부파일정보 -->
	<input type="hidden" name="fileInfos" 	value="" />
	<input type="hidden" name="fileInfos2" 	value="" />
	<input type="hidden" name="fileInfos3" 	value="" />
	<input type="hidden" name="file_bigclsfcn" 	value="F003" />
	<input type="hidden" name="file_midclsfcn" 	value="F00301" />
	<input type="hidden" name="file_smlclsfcn" 	value="" />
	<input type="hidden" name="ref_key" 	value="<c:out value='${command.cnslt_no}'/>@<c:out value='${command.hstry_no}'/>"/>
	<input type="hidden" name="view_gbn" 	value="download" />
	<input type="hidden" name="fileInfoName"    value="" /> <!-- 첨부파일 정보가 리턴될 객체명 -->
	<input type="hidden" name="fileFrameName"    value="" /> <!-- 첨부파일 화면 iFrame 명 -->
	
	<!-- 이중등록 방지 -->
	<input type="hidden" name="TOKEN"  value="<%=Token.set(request) %>" />
	
	<input type="hidden" id="contents" name="contents"   value="" />
	
	<input type="hidden" id="isStdCont" name="isStdCont" value="Y"/>
	
	<!-- title -->
	<div class="t_titBtn">
	
		<div class="btn_wrap">	
		
			<c:if test="${lom.prgrs_status == 'S08' && btnAuthYn == 'Y'}">
			<span class="btn_all_w"><span class="cancel"></span><a href="javascript:cancelReview()"><spring:message code="las.page.field.lawconsulting.cnclSend"/>
</a></span>
			</c:if>
			
			<!-- 상태가 '임시저장(의뢰인)'시 나타나는 버튼 -->
			<c:if test="${lom.prgrs_status == 'S01' && btnAuthYn == 'Y'}">
			<span class="btn_all_w"><span class="edit"></span><a href="javascript:goModifyForm()"><spring:message code="las.page.field.lawconsulting.requUpdate"/></a></span>
			<span class="btn_all_w"><span class="delete"></span><a href="javascript:remove()"><spring:message code="las.page.button.lawconsult.delete" /></a></span>
			</c:if>
			
			<!-- 상태가 '의뢰' 시 나타나는 버튼 (검토회신, 수정), 단 첫의뢰가 아니면 나타나지 않는다 -->
			<c:if test="${historyList[1] == null}">
			<c:if test="${lom.prgrs_status == 'S02' && btnAuthYn == 'Y' && command.top_role == 'respman'}">
				<c:if test="${elUserYn == 'N'}">
	              	<span class="btn_all_w"><span class="edit"></span><a href="javascript:goReviewForm()"><spring:message code="las.page.button.lawconsult.review" /></a></span>
	            </c:if>
				<span class="btn_all_w"><span class="edit"></span><a href="javascript:goModifyForm()"><spring:message code="las.page.button.lawconsult.modify" /></a></span>
			</c:if>
			</c:if>
			
			<!-- 상태가 '재의뢰'시 나타나는 버튼 (검토회신, 수정) -->
			<c:if test="${lom.prgrs_status == 'S04' && btnAuthYn == 'Y' && command.top_role == 'respman'}">
			<c:if test="${elUserYn == 'N'}">
              	<span class="btn_all_w"><span class="edit"></span><a href="javascript:goReviewForm()"><spring:message code="las.page.button.lawconsult.review" /></a></span>
              </c:if>
			<span class="btn_all_w"><span class="check"></span><a href="javascript:goReturnForm('refer')"><spring:message code="las.page.field.lawconsulting.reject"/></a></span>
			<span class="btn_all_w"><span class="edit"></span><a href="javascript:goModifyForm()"><spring:message code="las.page.button.lawconsult.modify" /></a></span>
			</c:if>
			
			<!-- 상태가 '회신', '법무장 반려'시 나타나는 버튼 (검토회신) -->			
			<c:if test="${lom.prgrs_status == 'S03'|| lom.prgrs_status == 'S11'|| lom.prgrs_status == 'S07'}">
			<c:if test="${elUserYn == 'N' && btnAuthYn == 'Y' && command.top_role == 'respman'}">
              	<span class="btn_all_w"><span class="edit"></span><a href="javascript:goReviewForm()"><spring:message code="las.page.button.lawconsult.review" /></a></span>
              </c:if>
			</c:if>
		
			<!-- 상태가 '임시저장' 시 나타나는 버튼 (검토수정) -->
			<c:if test="${lom.prgrs_status == 'S09' && btnAuthYn == 'Y' && command.top_role == 'respman'}">
			<c:if test="${elUserYn == 'N'}">
              	<span class="btn_all_w"><span class="edit"></span><a href="javascript:goReviewForm()"><spring:message code="las.page.button.lawconsult.review" /></a></span>
              </c:if>
			</c:if>
			
			<!-- 해당 건의 첫 데이터일시 전체삭제, 그렇지 않을시 삭제 버튼 -->
			
			<c:choose>
				<c:when test="${lom.hstry_no == 0 && historyList[1] != null && btnAuthYn == 'Y'}">
					<!-- <span class="btn_all_w"><span class="delete"></span><a href="javascript:removeAll()"><spring:message code="las.page.button.lawconsult.delete_all" /></a></span> -->				
				</c:when>
				<c:otherwise>
					<!-- <span class="btn_all_w"><span class="delete"></span><a href="javascript:remove()"><spring:message code="las.page.button.lawconsult.delete" /></a></span> -->
				</c:otherwise>
			</c:choose>
			
			
			<span class="btn_all_w"><span class="print"></span><a href="javascript:goPrint()"><spring:message code="las.page.button.lawconsult.print" /></a></span>
			<span class="btn_all_w"><span class="list"></span><a href="javascript:goList()"><spring:message code="las.page.button.lawconsult.list" /></a></span>
			
		</div>
	</div>
	<!-- //title -->
		<!-- view -->
		<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
			<colgroup>
				<col width="15%" />
				<col width="37%" />
				<col width="10%" />
				<col width="38%" />
			</colgroup>
			
				<tr>
					<th><spring:message code="las.page.field.lawconsult.title" /></th>
					<td colspan="3">
						<c:out value='${lom.title}' escapeXml='false'/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.speakconsult.reqman_nm" /></th>
					<td><c:out value='${lom.reg_nm}'/>/<c:out value='${lom.regman_jikgup_nm}'/></td>
					<th><spring:message code="las.page.field.lawconsult.department" /></th>
					<td><c:out value='${lom.reg_dept_nm}'/></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawconsult.telno" /></th>
					<td><c:out value='${lom.reg_telno}'/></td>
					<th><spring:message code="las.page.field.lawconsult.reg_dt" /></th>
					<td><c:out value='${lom.reg_dt}'/></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawconsult.prgrs_status" /></th>
					<td>
						<c:out value='${lom.prgrs_status_name}'/>
						<c:choose>
							<c:when test="${lom.to_transfer == 'H'}">
							(<spring:message code="las.page.field.lawconsulting.domestic"/>)
							</c:when>	
							<c:when test="${lom.to_transfer == 'F'}">
							(<spring:message code="las.page.field.lawconsulting.abroad"/>)
							</c:when>
							<c:when test="${lom.to_transfer == 'IP'}">
							(IP)
							</c:when>
						</c:choose>
					</td>
					<th><spring:message code="las.page.field.mainproject.mainmatr_type" /></th>
					<td><c:out value='${lom.consult_type_name}'/></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawconsult.respman_nm" /></th>
					<td><c:out value='${lom.respman_nm}'/></td>
					<th><spring:message code="las.page.field.lawconsult.cnsdman_nm" /></th>
					<td><c:out value='${lom.cnsdman_nm}'/></td>
				</tr>
				<!-- 의뢰시 나타나는 정보 -->
				<c:if test="${lom.prgrs_status == 'S02' || lom.prgrs_status == 'S04'}">
				<tr>
					<th><spring:message code="clm.page.msg.common.content" /></th>
					<td colspan=3><c:out value='${lom.cont}' escapeXml="false"/></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawconsult.attachfile" /></th>
					<td colspan="3">
						<!-- Sungwoo Replaced scroll option 2014/08/04 -->
						<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe>
					</td>
				</tr>
				
			<c:if test="${lom.prgrs_status == 'S02'}">
			<c:if test="${btnAuthYn=='Y' }">
			<tr>
			<th><spring:message code="las.page.button.lawconsult.select" /></th>
				<td colspan="3">
					<div>
						<span class="bul_txt"><spring:message code="las.page.field.lawconsult.return_select" /></span>
						<!-- <input type="checkbox" id="grpmgr_re_yn" name="grpmgr_re_yn" value="<c:out value='${lom.grpmgr_re_yn}'/>"/> <label for="" ><spring:message code="las.page.field.lawconsult.grpmgr_re_yn" /></label>
						<a href="javascript:assign();"><img src="<%=IMAGE%>/btn/btn_appoint.gif" alt="지정" /></a> -->
						<a href="javascript:goReturnForm('refer');"><img src="<%=IMAGE%>/btn/btn_return.gif" alt="<spring:message code="las.page.field.lawconsulting.reject"/>" /></a>
					</div>
					<!-- 계약관리 라이트 버젼에선 이관이 없습니다. 무조건 국내만 처리가 됩니다.
					<div>
					
						<span class="bul_txt"><spring:message code="las.page.field.lawconsult.transfer_select" /></span>
						<select id="transferSel" class="checkSel">
						<option value=""><spring:message code="las.page.field.lawconsulting.selectL"/></option>
						<c:if test="${lom.dmstfrgn_gbn == 'H'}">
							<option value="F"><spring:message code="las.page.field.lawconsulting.domestic"/>-><spring:message code="las.page.field.lawconsulting.abroad"/></option>
							<option value="IP"><spring:message code="las.page.field.lawconsulting.domestic"/>->IP</option>
						</c:if>
						<c:if test="${lom.dmstfrgn_gbn == 'F'}">
							<option value="H"><spring:message code="las.page.field.lawconsulting.abroad"/>-><spring:message code="las.page.field.lawconsulting.domestic"/></option>
							<option value="IP"><spring:message code="las.page.field.lawconsulting.abroad"/>->IP</option>
						</c:if>
						<c:if test="${lom.dmstfrgn_gbn == 'IP'}">
							<option value="H">IP-><spring:message code="las.page.field.lawconsulting.domestic"/></option>
							<option value="F">IP-><spring:message code="las.page.field.lawconsulting.abroad"/></option>
						</c:if>
						</select>
						
						<a href="javascript:transfer();"><img src="<%//=IMAGE%>/btn/btn_transfer.gif" alt="<spring:message code="las.page.field.lawconsulting.trans"/>" /></a>
						
					</div>
					 -->
				</td>
			</tr>	
			</c:if>
			<c:if test="${elUserYn == 'N' && btnAuthYn=='Y'}">
			<tr>
				<th><spring:message code="las.page.field.manageCommon.srchLasCnsdmanNm" /></th>
				<td colspan="3">
					<select id="respman_list" name="respman_list" size="4" multiple style='width:100px;height:60px;overflow-y : auto'>
						<c:forEach var="list" items="${respmanList}">
							<option value="<c:out value='${list.respman_id}'/>">
								<c:out value='${list.respman_nm}'  escapeXml='false'/>
							</option>
						</c:forEach>
					</select>						
					<a href='javascript:popRespman()'><img src='<%=IMAGE%>/btn/btn_select.gif' alt="<spring:message code="las.page.field.lawconsulting.select"/>" /></a>
					<!-- <a href="javascript:removeRow();"><img src="<%=IMAGE%>/btn/btn_expect.gif" alt="<spring:message code="las.page.field.lawconsulting.except"/>" /></a> -->
				</td>
			</tr>
			</c:if>
			</c:if>
			</c:if>
			
			<!-- 회신, 임시저장 시 나타나는 정보 -->
			<c:if test="${lom.prgrs_status == 'S03' || lom.prgrs_status == 'S09'}">
			<tr>
			<th><spring:message code="las.page.field.lawconsult.main_matr_cont" /></th>
			<td colspan="3"><c:out value='${lom.main_matr_cont}' escapeXml="false"/></td>
			</tr>
			<tr>
			<th>
				<c:if test="${lom.dmstfrgn_gbn == 'H'}">
					<spring:message code="las.page.field.lawconsult.lasopnn" />
				</c:if>
				<c:if test="${lom.dmstfrgn_gbn == 'F'}">
					<spring:message code="las.page.field.lawconsulting.lgAbGrCmt"/>
				</c:if>
				<c:if test="${lom.dmstfrgn_gbn == 'IP'}">
					<spring:message code="las.page.field.lawconsulting.ipCntOpi"/>
				</c:if>
			</th>
			<td colspan="3"><c:out value='${lom.cnsd_opnn}' escapeXml="false"/></td>
			</tr>
			
			<!-- 회신 시 법무담당임원 의견  -->
			<c:if test="${lom.apbt_opnn != ''}">
			<c:if test="${lom.prgrs_status == 'S03'}">
				<tr>
				<th><spring:message code="las.page.field.lawconsult.apbt_opnn" /></th>
				<td colspan="3"><c:out value='${lom.apbt_opnn}' escapeXml="false"/></td>
				</tr>
			</c:if>
			</c:if>
			
			<tr>
			<th><spring:message code="las.page.field.lawconsult.las_attachfile" /></th>
			<td colspan="3">	
				<!-- Sungwoo Replaced scroll option 2014/08/04 -->
				<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe>
			</td>
			</tr>
			</c:if>
			
			<!-- 미결시 나타나는 정보 -->
			<c:if test="${lom.prgrs_status == 'S08'}">
			<tr>
			<th><spring:message code="las.page.field.lawconsult.main_matr_cont" /></th>
			<td colspan="3"><%@ include file="/WEB-INF/jsp/secfw/common/NamoEditInclude2.jspf"%></td>
			</tr>
			<tr>
			<th>
				<c:if test="${lom.dmstfrgn_gbn == 'H'}">
					<spring:message code="las.page.field.lawconsult.lasopnn" />
				</c:if>
				<c:if test="${lom.dmstfrgn_gbn == 'F'}">
					<spring:message code="las.page.field.lawconsulting.lgAbGrCmt"/>
				</c:if>
				<c:if test="${lom.dmstfrgn_gbn == 'IP'}">
					<spring:message code="las.page.field.lawconsulting.ipCntOpi"/>
				</c:if>
			</th>
			<td colspan="3"><%@ include file="/WEB-INF/jsp/secfw/common/NamoEditInclude.jspf"%></td>
			</tr>
			<tr>
			<th><spring:message code="las.page.field.lawconsult.apbt_opnn" /></th>
			<td colspan="3"><textarea name="apbt_opnn" id="apbt_opnn" cols="10" rows="7" class="text_area_full" alt="<spring:message code="las.page.field.lawconsulting.lgChiefCmt"/>"  maxLength="2000"><c:out value='${lom.apbt_opnn}' escapeXml="false"/></textarea></td>
			</tr>
			<tr>
			<th><spring:message code="las.page.field.lawconsult.las_attachfile" /></th>
			<td colspan="3">	
				<!-- Sungwoo Replaced scroll option 2014/08/04 -->
				<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe>
			</td>
			</tr>
			</c:if>
			

			<!-- 			
			<c:if test="${lom.ordr_cont != ''}">
			<c:if test="${lom.prgrs_status != 'S02' && lom.prgrs_status != 'S04' && lom.prgrs_status != 'S05' && lom.prgrs_status != 'S07' && lom.prgrs_status != 'S11'}">
			<tr>
				<th><spring:message code="las.page.field.lawconsult.memo" /></th>
				<td colspan="7"><c:out value='${command.ordr_cont}' escapeXml="false"/>	</td>
			</tr>
			</c:if>
			</c:if>
			 -->
			
			<!-- 보류시 나타나는 정보 -->
			<c:if test="${lom.prgrs_status == 'S05'}">
				<tr>
					<th><spring:message code="las.page.field.lawconsult.holdcont" /></th>
					<td colspan="3">
						<c:out value='${lom.hold_cause}' escapeXml="false"/>	
					</td>
				</tr>
			</c:if>
			
			<!-- 반려시 나타나는 정보 -->
			<c:if test="${lom.prgrs_status == 'S07'}">
				<tr>
					<th><spring:message code="las.page.field.lawconsulting.rejectBy"/></th>
					<td colspan="3">
						<c:out value='${lom.mod_nm}' escapeXml="false"/>	
					</td>
				</tr>				
				<tr>
					<th><spring:message code="las.page.field.lawconsult.rejctcont" /></th>
					<td colspan="3">
						<c:out value='${lom.rejct_cause}' escapeXml="false"/>	
					</td>
				</tr>
			</c:if>
			
			<c:if test="${lom.prgrs_status == 'S11'}">
				<tr>
					<th>
						<c:if test="${lom.dmstfrgn_gbn == 'H'}">
							<spring:message code="las.page.field.lawconsult.lasopnn" />
						</c:if>
						<c:if test="${lom.dmstfrgn_gbn == 'F'}">
							<spring:message code="las.page.field.lawconsulting.lgAbGrCmt"/>
						</c:if>
						<c:if test="${lom.dmstfrgn_gbn == 'IP'}">
							<spring:message code="las.page.field.lawconsulting.ipCntOpi"/>
						</c:if>
					</th>
					<td colspan="3"><c:out value='${lom.cnsd_opnn}' escapeXml="false"/></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawconsult.rejctcont" /></th>
					<td colspan="3">
						<c:out value='${lom.rejct_cause}' escapeXml="false"/>	
					</td>
				</tr>
			</c:if>
			
			<!-- 외부기관 정보 (미결 / 회신 / 임시저장 시)-->
			<!-- 회신, 미결, 임시저장 시 나타나는 정보 -->
			<!-- 
			<c:if test="${lom.prgrs_status == 'S03' || lom.prgrs_status == 'S08' || lom.prgrs_status == 'S09' }">
			<c:if test="${lom.extnl_cnsltyn == 'Y'}">
			<tr>
				<th><spring:message code="las.page.field.lawconsult.extnlconsult" /></th>
				<td colspan="3">
					<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
						<colgroup>
						<col width="50%" />
						<col width="25%" />
						<col width="25%" />
						</colgroup>
						 <thead>
					     <tr>
					      <th><spring:message code="las.page.field.lawconsult.comp_nm" /></th>
					      <th><spring:message code="las.page.field.lawconsult.cnslt_amt" /></th>
					      <th><spring:message code="las.page.field.lawconsult.crrncy_unit" /></th>
						 </tr>
				         </thead>
				         <tbody>
				         	<c:forEach var="list" items="${extnlcompList}" varStatus="cnt">
								<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
									<td class="tL">								    
								   		<c:out value='${list.comp_nm}' escapeXml="false"/>
								    </td> 
									<td><fmt:formatNumber value="${list.cnslt_amt}" pattern="#,###"/></td>
									<td><c:out value='${list.crrncy_unit}'/></td>
								</tr>
							</c:forEach>
				         </tbody>
					</table>	
				</td>
				<tr>
				<th><spring:message code="las.page.field.lawconsult.extnl_attachfile" /></th>
				<td colspan="3">
					<!-- Sungwoo Replaced scroll option 2014/08/04 -->
					<iframe src="<c:url value='/las/blank.do' />" id="fileList3" name="fileList3" frameborder="0" width="100%" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
				</td>
				</tr>
			</c:if>
			</c:if>
			  -->
		</table>
		
		<div align="center">
			<br/>
			<c:if test="${lom.prgrs_status == 'S08' || lom.prgrs_status == 'S09'}">
			<c:if test="${btnAuthYn == 'Y' }">
			<span class="btn_all_w"><span class="mail"></span><a href="javascript:send();"><spring:message code="las.page.button.lawconsult.reply" /></a></span>
			</c:if>
			</c:if>
		
			<c:if test="${lom.prgrs_status == 'S08' && btnAuthYn == 'Y'}">
			<span class="btn_all_w"><span class="edit"></span><a href="javascript:goReturnForm('referGrpmgr')"><spring:message code="las.page.button.lawconsult.reject" /></a></span>
			</c:if>
		</div>
		
		
		
		
		<div class="title_basic">
				<h4><spring:message code="las.page.field.lawconsult.history" /></h4>
		</div>

		<!-- 의뢰회신 list -->
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
			 <colgroup>
			   <col width="50%" />
			   <col width="21%" />
			   <col width="21%" />
			   <col width="8%" />
		     </colgroup>
			  <thead>
			    <tr>
			      <th><spring:message code="las.page.field.lawconsult.title" /></th>
			      <th><spring:message code="las.page.field.speakconsult.reqman_nm" />/<spring:message code="las.page.field.lawconsult.cnsdman_nm" /></th>
			      <th><spring:message code="las.page.field.lawconsult.respman_nm" /></th>
			      <th><spring:message code="las.page.field.lawconsult.dt" /></th>
				</tr>
		      </thead>
			  <tbody>
				<c:forEach var="list" items="${historyList}" varStatus="cnt">
					<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
						<td class="tL">
						<c:choose>
							<c:when test="${list.cnslt_pos == 0 }">
								
							</c:when>
							<c:otherwise>
								
								<c:forEach begin="1" end="${list.cnslt_pos}">
					    			&nbsp;&nbsp;
						   		 </c:forEach>
						   		 <img src="<%=IMAGE%>/icon/icon_reply.gif" alt="" />
							</c:otherwise>
						</c:choose>
						
					    <a href="javascript:goDetail('<c:out value="${list.cnslt_no}"/>','<c:out value="${list.hstry_no}"/>')">
					    
					    <c:if test="${command.hstry_no == list.hstry_no}"><b></c:if>    
					    [<c:out value='${list.prgrs_status_name}'/>] <c:out value='${list.title}' escapeXml="false"/></a>
					    <c:if test="${command.hstry_no == list.hstry_no}"></b></c:if>
					    </td>
						<td class="tC"><c:out value='${list.reg_nm}'/></td>
						<td class="tC"><c:out value='${list.respman_nm}'/></td>
						<td class="tC"><c:out value='${list.reg_dt_date}'/></td>
					</tr>
				</c:forEach>
		      </tbody>
		  </table>
			<!-- //list -->
			<!-- //view -->
	</form:form>		
	</div>
	</div>
	<!-- //content -->
	
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</div>
<!-- //container -->
</div>

<!-- footer  -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
<!-- // footer -->

<jsp:include page="/WEB-INF/jsp/secfw/common/DlgAlert.jsp"/>
<script language="javascript" for="wec" event="OnInitCompleted()">
	var wecObj0 = document.wec[0];
	wecObj0.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
	wecObj0.SetDefaultFontSize("9");
	wecObj0.EditMode = 1;
	wecObj0.Value = document.frm.main_matr_cont.value; //namo 에 값 셋팅
	
	var wecObj1 = document.wec[1];
	wecObj1.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
	wecObj1.SetDefaultFontSize("9");
	wecObj1.EditMode = 1;
	wecObj1.Value = document.frm.cnsd_opnn.value; //namo 에 값 셋팅
</script>
</body>
</html>
