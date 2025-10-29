<!DOCTYPE html>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@page import="com.sds.secframework.common.util.PageUtil"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%@page import="com.sds.secframework.common.util.Token"%>
<%--
/**
 * 파  일  명 : LawStaConsultGrpmgr_d.jsp
 * 프로그램명 : 표준계약서 상세 (담당자 페이지)
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
		
		getCodeSelectByUpCd("frm", "user_list", '/common/clmsCom.do?method=getLasPersonComboHTML&combo_sys_cd=LAS&combo_type=T');
		initPage();
	});

	function initPage(){
		
		if(frm.prgrs_status.value == 'S02' || frm.prgrs_status.value == 'S04'){
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
	
	function goModifyForm() {
		viewHiddenProgress(true) ;
		var f = document.frm ;
		f.method.value = "forwardModifyStaLawConsult" ;
		f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
		f.target = "_self" ;
		f.submit() ; 
	}
	
	function goReturnForm() {
		viewHiddenProgress(true) ;
		var f = document.frm ;
		f.isGrpmgr.value = "Y";
		f.method.value = "forwardHoldLawConsult" ;
		f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
		f.target = "_self" ;
		f.submit() ; 
	}
	
	function send(){
		var f = document.frm;
		var confirmMessage = "<spring:message code='secfw.msg.ask.send' />";
		
		if(f.grpmgr_re_yn.value == 'Y'){
			f.check_prgrs_status.value = "undecided";
		}
		else
			f.check_prgrs_status.value = "reply"; 
		
		if(confirm(confirmMessage)){
			f.method.value = "modifyStatusStaLawConsult" ;
			f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
			f.target = "_self" ;
			f.submit();
		}
	}
	
	function saveOrdrCont(){
		var f = document.frm;
		if(validateForm(document.frm)) {
			
		f.method.value = "modifyStatusStaLawConsult" ;
		f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
		f.target = "_self" ;
		f.submit();
		
		}
	}
	
	function goReviewForm(){
		viewHiddenProgress(true) ;
		var f = document.frm ;
		f.method.value = "forwardStaReviewLawConsult" ;
		f.isGrpmgr.value = "N";
		f.isReview.value = "Y";
		f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
		f.target = "_self" ;
		f.submit() ;
	}
	
	
	
	function goList() {
		
		viewHiddenProgress(true) ;
		var f = document.frm ;
		f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
		f.method.value = "listStaLawConsult";
		f.target = "_self" ;
		f.submit() ;
	}
	
	function goPrint() {
		var f = document.frm ;
		f.method.value = "forwardPrintDetailLawConsult" ;
		f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
		PopUpWindowOpen('', 800, 500, true);
		frm.target = "PopUpWindow";
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
	
	function cancelReview(){
		var f = document.frm;
		var confirmMessage = "<spring:message code='secfw.msg.ask.cancel' />";
		
		f.check_prgrs_status.value = 'tempSave';
		
		if(confirm(confirmMessage)){
			f.method.value = "modifyStatusStaLawConsult" ;
			f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
			f.target = "_self" ;
			f.submit();
		}
		
	}
	
	function searchFile(){
		alert($('#fileList2').contents().find('#file_uploadREAD0').length);
	}	
	
</script>
</head>
<!--  2012.04.26 Alert 수정 modified by hanjihoon  -->
<body onload="init()" autocomplete="off">
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
	<input type="hidden" name="if_key_no" value="<c:out value='${lom.if_key_no}'/>"/>
	<input type="hidden" name="if_flag" value="<c:out value='${lom.if_flag}'/>"/>
	<input type="hidden" name="consult_type" value=""/>
	<input type="hidden" name="prgrs_status" value="<c:out value='${lom.prgrs_status}'/>"/>
	<input type="hidden" name="extnl_cnsltyn" value="<c:out value='${lom.extnl_cnsltyn}'/>"/>
	<input type="hidden" name="isGrpmgr" value=""/>
	<input type="hidden" name="isReview" value=""/>
	<input type="hidden" name="check_prgrs_status" value=""/>
	<input type="hidden" name="grpmgr_re_yn" value="<c:out value='${lom.grpmgr_re_yn}'/>"/>
	<input type="hidden" name="solo_yn" value="<c:out value='${lom.solo_yn}'/>"/>
	<input type="hidden" name="cnsd_dt" value="<c:out value='${lom.cnsd_dt}'/>"/>
	
	<input type="hidden" name="reg_id" value="<c:out value='${lom.reg_id}'/>"/>
	<input type="hidden" name="otherModify" id="otherModify" value="Y"/>
	
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
	
	<!-- 수정후 페이지 포워딩 구분 -->
	<input type="hidden" name="fwd_gbn" value="resp"/>
	
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
	
	<!-- title -->
	<div class="t_titBtn">
	
		<div class="btn_wrap">
		<c:if test="${isRespman == 'Y'}">
			<c:if test="${historyList[1] == null}">
			<c:if test="${lom.prgrs_status == 'S02' && command.top_role == 'respman'}">
					<span class="btn_all_w"><span class="check"></span><a href="javascript:goReviewForm()"><spring:message code="las.page.button.lawconsult.review" /></a></span>
					<span class="btn_all_w"><span class="edit"></span><a href="javascript:goModifyForm()"><spring:message code="las.page.button.lawconsult.modify" /></a></span>
			</c:if>
			</c:if>
			
			<!-- 상태가 '재의뢰'시 나타나는 버튼 (검토회신, 수정) -->
			<c:if test="${lom.prgrs_status == 'S04' && command.top_role == 'respman'}">
			<span class="btn_all_w"><span class="check"></span><a href="javascript:goReviewForm()"><spring:message code="las.page.button.lawconsult.review" /></a></span>
			<span class="btn_all_w"><span class="edit"></span><a href="javascript:goModifyForm()"><spring:message code="las.page.button.lawconsult.modify" /></a></span>
			</c:if>
			
			<!-- 상태가 '회신'시 나타나는 버튼 (검토회신) -->			
			<c:if test="${lom.prgrs_status == 'S03' && command.top_role == 'respman'}">
			<span class="btn_all_w"><span class="check"></span><a href="javascript:goReviewForm()"><spring:message code="las.page.button.lawconsult.review" /></a></span>
			</c:if>
		
			<c:if test="${lom.prgrs_status == 'S11' && command.top_role == 'respman'}">
			<span class="btn_all_w"><span class="check"></span><a href="javascript:goReviewForm()"><spring:message code="las.page.button.lawconsult.review" /></a></span>
			</c:if>
		
			<c:if test="${lom.prgrs_status == 'S09' && command.top_role == 'respman'}">
			<!-- <span class="btn_all_w"><span class="mail"></span><a href="javascript:send();"><spring:message code="las.page.button.lawconsult.send" /></a></span> -->
			<span class="btn_all_w"><span class="edit"></span><a href="javascript:goReviewForm()"><spring:message code="las.page.button.lawconsult.review" /></a></span>
			</c:if>
			
			<c:if test="${lom.prgrs_status == 'S08'}">
			<span class="btn_all_w"><span class="cancel"></span><a href="javascript:cancelReview()"><spring:message code="las.page.field.lawconsulting.cnclSend"/></a></span>
			</c:if>
		</c:if>			
			<span class="btn_all_w"><span class="print"></span><a href="javascript:goPrint()"><spring:message code="las.page.button.lawconsult.print" /></a></span>
			<span class="btn_all_w"><span class="list"></span><a href="javascript:goList()"><spring:message code="las.page.button.lawconsult.list" /></a></span>
			
		</div>
	</div>
	<!-- //title -->
		<!-- view -->
		<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
			<colgroup>
				<col width="15%" />
				<col width="35%" />
				<col width="15%" />
				<col width="35%" />
			</colgroup>
				
				<c:choose>
				<c:when test="${lom.prgrs_status == 'S05'}">
				<tr>
					<th><spring:message code="las.page.field.lawconsult.prgrs_status" /></th>
					<td colspan="3">
						<c:out value='${lom.prgrs_status_name}'/>
					</td> 
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawconsult.holdcont" /></th>
					<td colspan="3">
						<c:out value='${lom.hold_cause}' escapeXml="false"/>	
					</td>
				</tr>
				</c:when>
				
				<c:otherwise>
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
					<td><c:out value='${lom.prgrs_status_name}'/></td>
					<th><spring:message code="las.page.field.lawconsult.consulttype" /></th>
					<td><c:out value='${lom.consult_type_name}'/></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawconsult.respman_nm" /></th>
					<td><c:out value='${lom.respman_nm}'/></td>
					<th><spring:message code="las.page.field.lawconsult.cnsdman_nm" /></th>
					<td><c:out value='${lom.cnsdman_nm}'/></td>
				</tr>
				
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
				</c:if>

			<c:if test="${lom.prgrs_status == 'S03' || lom.prgrs_status == 'S09' || lom.prgrs_status == 'S08'}">
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
			

			<c:if test="${lom.prgrs_status == 'S03'}">
				<c:if test="${lom.grpmgr_re_yn == Y}">
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
			
			<!-- 
			<c:if test="${lom.prgrs_status == 'S02' || lom.prgrs_status == 'S04'}">
			<c:if test="${lom.ordr_cont != null &&  lom.ordr_cont != ''}">
				<tr>
					<th><spring:message code="las.page.field.lawconsult.leaderMsg" /></th>
					<td colspan="7" >
					<c:choose>
						<c:when test="${isRespman == 'Y'  && auth == 'Y'}">
						<textarea name="ordr_cont" id="ordr_cont" cols="10" rows="7" class="text_area_01" alt="메모" maxLength="2000"><c:out value='${command.ordr_cont}' escapeXml="false"/></textarea>
						<span class="btn_all_w vb"><span class="check"></span><a href="javascript:saveOrdrCont();"><spring:message code="las.page.button.lawconsult.save" /></a></span>
						</c:when>
						<c:otherwise>
						<td colspan="7"><c:out value='${lom.ordr_cont}'  escapeXml="false"/>	</td>
						</c:otherwise>
					</c:choose>
						
					</td>
				</tr>
			</c:if>
			</c:if>
			 -->
			
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
			

			<c:if test="${lom.prgrs_status != 'S05' && lom.prgrs_status != 'S07' && lom.prgrs_status != 'S11'}">
			<c:if test="${lom.ordr_cont != null &&  lom.ordr_cont != ''}">
			<tr>
				<th><spring:message code="las.page.field.lawconsult.leaderMsg" /></th>
				<td colspan="7"><c:out value='${lom.ordr_cont}'  escapeXml="false"/>	</td>
			</tr>
			</c:if>
			</c:if>
			

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
									 <!--<td><c:out value='${list.cnslt_amt}'/></td>--> 
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
					<iframe src="<c:url value='/las/blank.do' />" id="fileList3" name="fileList3" frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe>
				</td>
				</tr>
			</tr>
			</c:if>
			</c:if>
			</c:otherwise>
			</c:choose>
			
		</table>
		
		
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
					<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
						<td class="tL">
						<c:choose>
							<c:when test="${list.cnslt_pos == 0 }">
								
							</c:when>
							<c:otherwise>
								
								<c:forEach begin="1" end="${list.cnslt_pos}">
					    			&nbsp;
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

<jsp:include page="/WEB-INF/jsp/secfw/common/DlgAlert.jsp"/>
<!-- footer  -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
	<!-- // footer -->

</body>
</html>
