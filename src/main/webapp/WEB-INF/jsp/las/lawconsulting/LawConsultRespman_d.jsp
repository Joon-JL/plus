<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="com.sds.secframework.common.util.PageUtil"%>
<%@ page import="com.sds.secframework.common.util.Token"%>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>


<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");

	//자문 권한 관련자
	ArrayList listCa = (ArrayList)request.getAttribute("listCa");
	
	boolean authYN_RA02 = false;
	
	ArrayList tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.roleList");
		
	if(tmpSessionRoleList.contains("RA02")){
		authYN_RA02 = true;
	}

%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.main.title" /></title>


<!--  
	2013.10.23
** 	변경사항 - 자문 검토 (검토상세 검토자 화면)
** 	권한 변경후 검토작성과 의뢰내용 수정은 RA02(respman)만 가능함.
-->


<LINK href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet">
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
		
		if("<c:out value='${lom.prgrs_status}'/>" == 'S03' || "<c:out value='${lom.prgrs_status}'/>" == 'S09' || "<c:out value='${lom.prgrs_status}'/>" == 'S08'){
			
			setTimeout(function(){
	        	//사안개요
	            document.getElementById('main_matr_cont').contentWindow.document.body.innerHTML = document.getElementById('main_matr_cont_value').value;
	
	            // 추진목적 및 배경
	            document.getElementById('cnsd_opnn').contentWindow.document.body.innerHTML = document.getElementById('cnsd_opnn_value').value;
	            document.getElementById('cnsd_opnn').width = document.getElementById('cnsd_opnn').contentWindow.document.body.scrollWidth+10+'px';
	            
	        
	        }, 1000*0.5);
		}
	});

	//Sungwoo added textarea 2014-08-01
	$(window).load(function(){	
		namoView();
	});
	
	function namoView(){
		
		//나모 내용 display

		//1. 검토요청내용
		if (document.getElementById('if_cont') != null){
	    	document.getElementById('if_cont').contentWindow.document.body.innerHTML = document.getElementById('if_cont_textarea').value;
		}
	}
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
			f.method.value = "deleteLawConsult" ;
			f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
			f.target = "_self" ;
			f.submit() ;
		}
	}
	
	function goModifyForm() {
		viewHiddenProgress(true) ;
		var f = document.frm ;
		f.method.value = "forwardModifyLawConsult" ;
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
			f.method.value = "modifyStatusLawConsult" ;
			f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
			f.target = "_self" ;
			f.submit();
		}
	}
	
	function saveOrdrCont(){
		var f = document.frm;
		if(validateForm(document.frm)) {
			
		f.method.value = "modifyStatusLawConsult" ;
		f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
		f.target = "_self" ;
		f.submit();
		
		}
	}
	
	function goReviewForm(){
		viewHiddenProgress(true) ;
		var f = document.frm ;
		f.method.value = "forwardReviewLawConsult" ;
		f.isGrpmgr.value = "N";
		f.isReview.value = "Y";
		f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
		f.target = "_self" ;
		f.submit() ;
	}
	
	
	
	function goList() {
		
		viewHiddenProgress(true) ;
		var f = document.frm ;
		f.method.value = "listLawConsult" ;
		f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
		f.target = "_self" ;
		f.submit() ;
	}
	
	function goDetail(cnslt_no, hstry_no) {
		viewHiddenProgress(true) ;
		var f = document.frm ;
		f.cnslt_no.value = cnslt_no;
		f.hstry_no.value = hstry_no;
		f.method.value = "forwardDetailLawConsult" ;
		f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
		f.target = "_self" ;
		f.submit() ; 
	}
	
	function cancelReview(){
		var f = document.frm;
		var confirmMessage = "<spring:message code='secfw.msg.ask.cancel' />";
		
		f.check_prgrs_status.value = 'tempSave';
		
		if(confirm(confirmMessage)){
			viewHiddenProgress(true) ;
			f.method.value = "modifyStatusLawConsult" ;
			f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
			f.target = "_self" ;
			f.submit();
		}
		
	}
	
	function searchFile(){
		alert($('#fileList2').contents().find('#file_uploadREAD0').length);
	}	
	
	
	
	// 자문요청 완료상태 <-> 미완료상태 변경 (자문요청 담당변호사 판단에 의한 완료상태 변경)
	function goUpdateComplete(value){
		
		var frm = document.frm;
		var cfmMsg = "";
		
		// Y - 완료로 변경
		// N - 미완료로 변경
		
		if(value == "Y"){
			frm.complete_yn.value = "Y";
			cfmMsg = "<spring:message code='las.msg.alert.chkComplete'/>";
			
		}else{
			frm.complete_yn.value = "N";
			cfmMsg = "<spring:message code='las.msg.alert.chkUnComplete'/>";
			
		}		
		
		if(confirm(cfmMsg)){
			viewHiddenProgress(true);
			frm.method.value="updateCompleteStatusLawConsult";
			frm.action="<c:url value='/las/lawconsulting/lawConsult.do' />";
			frm.target="_self";
			frm.submit();
		}
		
	}
	
	function setMarkUpAJAX(cnslt_no, hstry_no) {
		
		var frm = document.frm;
		var confirmMessage = "";
		var mark_src = "";
		var flag= "";		
		
		var msg_chk = "<spring:message code='las.msg.considertaton.mark001' />";   // 중요도를 체크 처리 하시겠습니까?
		var msg_unchk = "<spring:message code='las.msg.considertaton.mark002' />";   // 해제 처리 하시겠습니까?
		var msg_comp = "<spring:message code='las.msg.succ.process' />";   // 처리 되었습니다.
		
				
		frm.cnslt_no.value = cnslt_no;
		frm.hstry_no.value = hstry_no;
		
		
		if($('#icon_' + cnslt_no).attr('alt')=='3'){
			confirmMessage = msg_unchk;		
			mark_src = "/images/las/en/icon/icon_b_white.gif";
			frm.mark_num.value = "1";
			flag = "unchk";			
		} else {
			confirmMessage = msg_chk;
			mark_src = "/images/las/en/icon/icon_b_red.gif";
			frm.mark_num.value = "3";			
		}
			
		//if(confirm(confirmMessage)){		
			
			var options = {   
					url: "<c:url value='/las/lawconsulting/lawConsult.do?method=setMarkUpAJAX' />",
					type: "post",
					dataType: "json",
					success: function(responseText, statusText) {
						if(responseText.returnCnt != 0) {
							var html = "";
							$.each(responseText, function(entryIndex, entry) {
								
								var return_val = entry['return_val'];
								
								if(return_val != '0'){
									//alert($('#icon_' + cnslt_no).attr('src') + '//////' + mark_src);
									
									if(flag=='unchk'){
										$('#icon_' + cnslt_no).attr('alt','1');
									} else {
										$('#icon_' + cnslt_no).attr('alt','3');
									}
									
									$('#icon_' + cnslt_no).attr('src',mark_src);
									//alert(msg_comp); // 처리하였습니다.
								}									

							});						
						}	 
						
						// viewHiddenProgress(false) ;	
					}
			};		
			$("#frm").ajaxSubmit(options);	
		//}		
	}	
	
	
</script>
</head>
<!--  2012.04.26 Alert 수정 modified by hanjihoon  -->
<body onLoad="init()" autocomplete="off">

<div id="wrap">
<!-- container -->
<div id="container">
	<!-- Location -->
    <div class="location">
        <img SRC="<%=IMAGE%>/icon/ico_home.gif" border="0" alt="Home" /><c:out value='${menuNavi}' escapeXml="false"/>
    </div>
  	<!-- //Location -->	
 
 	<div class="title">
      <%-- /* <h3><spring:message code="las.page.field.lawconsult.main_title" /></h3> */ --%>
      <h3><spring:message code="las.page.field.lawconsulting.lawAdvReview"/></h3>
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
	<input type="hidden" name="complete_yn" value="<c:out value='${lom.complete_yn}'/>"/>
	<input type="hidden" name="req_reply_dt" value="<c:out value='${lom.req_reply_dt}'/>"/>
	
	<input type="hidden" name="cnsd_dt" value="<c:out value='${lom.cnsd_dt}'/>"/>
	<input type="hidden" name="contents" value=""/>
	
	<input type="hidden" name="reg_id" value="<c:out value='${lom.reg_id}'/>"/>
	<input type="hidden" name="otherModify" id="otherModify" value="Y"/>
	<input type="hidden" name="mark_num" id="mark_num" value="" />	<!-- 중요도 체크용-->
	<!-- 작성완료 후 페이지 포워딩 구분을 위한 페이지 구분정보 -->
	<input type="hidden" name="fwd_gbn" value="resp"/>
		
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
	<input type="hidden" name="TOKEN"  value="<%=Token.set(request) %>">
	
	<!-- title -->
	<div class="t_titBtn">
		<div class="btn_wrap">
		<c:if test="${isRespman == 'Y'}">
			<c:if test="${historyList[1] == null}">
			<c:if test="${lom.prgrs_status == 'S02'}">
			<!--!@#$ 검토회신은 respman 에게만 활성화로 변경 -->
	            <c:if test="${command.top_role == 'respman'}">
					<span class="btn_all_w"><span class="check"></span><a href="javascript:goReviewForm()"><spring:message code="las.page.button.lawconsult.review" /></a></span>
					<span class="btn_all_w"><span class="edit"></span><a href="javascript:goModifyForm()"><spring:message code="las.page.button.lawconsult.modify" /></a></span>
				</c:if>
			</c:if>
			</c:if>
			
			<!-- 상태가 '재의뢰'시 나타나는 버튼 (검토회신, 수정) -->
			<c:if test="${lom.prgrs_status == 'S04'}">
			<!--!@#$ 검토회신은 respman 에게만 활성화로 변경 -->
	            <c:if test="${command.top_role == 'respman'}">
					<span class="btn_all_w"><span class="check"></span><a href="javascript:goReviewForm()"><spring:message code="las.page.button.lawconsult.review" /></a></span>
					<span class="btn_all_w"><span class="edit"></span><a href="javascript:goModifyForm()"><spring:message code="las.page.button.lawconsult.modify" /></a></span>
				</c:if>
			</c:if>
			
			<!-- 상태가 '회신'시 나타나는 버튼 (검토회신) -->			
			<c:if test="${lom.prgrs_status == 'S03'}">
				<!--!@#$ 검토회신은 respman 에게만 활성화로 변경 -->
	            <c:if test="${command.top_role == 'respman'}">
					<span class="btn_all_w"><span class="check"></span><a href="javascript:goReviewForm()"><spring:message code="las.page.button.lawconsult.review" /></a></span>
					<c:choose>
						<c:when test="${lom.complete_yn == 'Y'}">
							<span class="btn_all_w"><span class="check"></span><a href="javascript:goUpdateComplete('N')"><spring:message code="las.page.lawconsult.button.uncomplt"/></a></span>
						</c:when>
						<c:otherwise>
							<span class="btn_all_w"><span class="check"></span><a href="javascript:goUpdateComplete('Y')"><spring:message code="las.page.field.option.complete"/></a></span>
						</c:otherwise>
					</c:choose>
					
					
				</c:if>
			</c:if>
					
			<c:if test="${lom.prgrs_status == 'S11'}">
			<!--!@#$ 검토회신은 respman 에게만 활성화로 변경 -->
	            <c:if test="${command.top_role == 'respman'}">
					<span class="btn_all_w"><span class="check"></span><a href="javascript:goReviewForm()"><spring:message code="las.page.button.lawconsult.review" /></a></span>
				</c:if>
			</c:if>
		
			<c:if test="${lom.prgrs_status == 'S09'}">
			<!--!@#$ 검토회신은 respman 에게만 활성화로 변경 -->
	            <c:if test="${command.top_role == 'respman'}">
				<!-- <span class="btn_all_w"><span class="mail"></span><a href="javascript:send();"><spring:message code="las.page.button.lawconsult.send" /></a></span> -->
				<span class="btn_all_w"><span class="edit"></span><a href="javascript:goReviewForm()"><spring:message code="las.page.button.lawconsult.review" /></a></span>
				</c:if>
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
					<td>
						
						<!--  검토권한자에 한해서 중요 마크 보여줌  -->
                    	<% if(authYN_RA02){ %>
						<c:choose>
							<c:when test="${lom.mark_num eq '3'}">
								<img id="icon_<c:out value='${lom.cnslt_no}'/>" src="/images/las/en/icon/icon_b_red.gif" class='img_align'  onclick="javascript:setMarkUpAJAX('<c:out value='${lom.cnslt_no}'/>', '<c:out value='${lom.hstry_no}'/>');" alt="<c:out value='${lom.mark_num}'/>" style="cursor:pointer"/>&nbsp;					
							</c:when>
							<c:otherwise>
								<img id="icon_<c:out value='${lom.cnslt_no}'/>" src="/images/las/en/icon/icon_b_white.gif" class='img_align' onclick="javascript:setMarkUpAJAX('<c:out value='${lom.cnslt_no}'/>', '<c:out value='${lom.hstry_no}'/>');" alt="<c:out value='${lom.mark_num}'/>"  style="cursor:pointer" />&nbsp;															
							</c:otherwise>
						</c:choose>
						<%} %>
						
						
						<c:out value='${lom.title}' escapeXml='false'/>
					</td>
					<th><spring:message code="las.page.field.lawconsult.completYn"/></th>
					<td>
						<c:choose>
							<c:when test="${lom.complete_yn=='Y'}">
								<spring:message code="las.page.field.option.complete"/>&nbsp;&nbsp;
							</c:when>
							<c:otherwise>
								<spring:message code="las.page.field.option.uncompleted"/>&nbsp;&nbsp;
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				
				<!-- 의뢰자 / 의뢰자 부서 -->
				<tr>
					<th><spring:message code="las.page.field.speakconsult.reqman_nm" /></th>
					<td><c:out value='${lom.reg_nm}'/>/<c:out value='${lom.regman_jikgup_nm}'/></td>
					<th><spring:message code="las.page.field.lawconsult.department" /></th>
					<td><c:out value='${lom.reg_dept_nm}'/></td>
				</tr>
				
				<!-- 전화번호 / 의뢰일 -->
				<tr>
					<th><spring:message code="las.page.field.lawconsult.telno" /></th>
					<td><c:out value='${lom.reg_telno}'/></td>
					<th><spring:message code="las.page.field.lawconsult.reg_dt" /></th>
					<td><c:out value='${lom.reg_dt}'/></td>
				</tr>
				
				<!-- 의뢰상태 / 회신요청일 -->
				<tr>
					<th><spring:message code="las.page.field.lawconsult.prgrs_status" /></th>
					<td><c:out value='${lom.prgrs_status_name}'/></td>
					<th><spring:message code="clm.page.field.contract.request.returndt"/></th> <!-- 회신요청일 -->
	          	  	<td><c:out value='${lom.req_reply_dt}'/></td>
				</tr>
				
				<!-- 검토자 / 작성자 -->
				<tr>
					<th><spring:message code="las.page.field.lawconsult.respman_nm" /></th>
					<td><c:out value='${lom.respman_nm}'/></td>
					<th><spring:message code="las.page.field.lawconsult.cnsdman_nm" /></th>
					<td><c:out value='${lom.cnsdman_nm}'/></td>
				</tr>
				
				<!-- 자문유형 -->
				<tr>
					<th><spring:message code="las.page.field.lawconsult.consulttype" /></th>
					<td colspan="3"><c:out value='${lom.consult_type_name}'/></td>
				</tr>
				
				<!-- 참조인 -->
				<tr class="lineAdd">
					<th><spring:message code="clm.page.msg.manage.relPerson" /> <img src="<%=IMAGE %>/common/step_q.gif" alt="info" title="<spring:message code="clm.page.msg.manage.announce064" htmlEscape="true" />" /></th>
					<td colspan="3">
<% 
						if(listCa !=null){
							for(int j=0;j<listCa.size();j++){	
								ListOrderedMap lom = (ListOrderedMap)listCa.get(j);
%>
								<input type="hidden" name="arr_demnd_seqno" id="arr_demnd_seqno" value="<%=lom.get("seqno") %>" /><input type="hidden" name="arr_trgtman_id" id="arr_trgtman_id" value="<%=lom.get("respman_id") %>" /><input type="hidden" name="arr_trgtman_nm" id="arr_trgtman_nm" value="<%=lom.get("respman_nm") %>" /><input type="hidden" name="arr_trgtman_jikgup_nm" id="arr_trgtman_jikgup_nm" value="<%=lom.get("respman_jikgup") %>" /><input type="hidden" name="arr_trgtman_dept_nm" id="arr_trgtman_dept_nm" value="<%=lom.get("resp_dept") %>" />
								- <%=lom.get("respman_nm") %>/<%=lom.get("respman_jikgup") %>/<%=lom.get("resp_dept")%><BR/>					
<% 
							}
						}
%>
					</td>
				</tr>
				
				<c:if test="${lom.prgrs_status == 'S02' || lom.prgrs_status == 'S04'}">
				<tr>
					<th><spring:message code="las.page.field.lawconsult.cont" /></th>
					<td colspan=3>
						<!-- Sungwoo Replaced textarea 2014-08-01 -->
						<textarea id="if_cont_textarea" name="cont_textarea" style="display: none;"><c:out value="${lom.cont}" /></textarea>
						<iframe id="if_cont" name="if_cont" src="<c:url value='/clm/blank.do' />" frameborder="0"  scrolling="auto" style="width:100%;" leftmargin="0" topmargin="0"></iframe>
					</td>
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
					<td colspan="3">
						<textarea id="main_matr_cont_value" name="main_matr_cont_value" style="display: none;"><c:out value='${lom.main_matr_cont}'/></textarea>
					    <iframe id="main_matr_cont" name="main_matr_cont" src="<c:url value='/clm/blank.do' />" frameborder="0"  style="width:100%"></iframe>
					</td>
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
					<td colspan="3">
						<textarea id="cnsd_opnn_value" name="cnsd_opnn_value" style="display: none;"><c:out value='${lom.cnsd_opnn}'/></textarea>
					    <iframe id="cnsd_opnn" name="cnsd_opnn" src="<c:url value='/clm/blank.do' />" frameborder="0"  style="width:100%"></iframe>
					</td>
				</tr>
			
				<c:if test="${lom.prgrs_status == 'S03'}">
					<c:if test="${lom.grpmgr_re_yn == Y}">
						<tr>
							<th><spring:message code="las.page.field.lawconsult.apbt_opnn" /></th>
							<td colspan="3">
								<c:out value='${lom.apbt_opnn}' escapeXml="false"/>
							</td>
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
			

			</c:otherwise>
			</c:choose>
			
		</table>
		
		
		<div class="title_basic">
				<h4><spring:message code="las.page.field.lawconsult.history" /></h4>
		</div>

		<!-- 의뢰회신 list -->
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
			 <colgroup>
			   <col width="50%">
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
<!-- footer  -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
	<!-- // footer -->

<jsp:include page="/WEB-INF/jsp/secfw/common/DlgAlert.jsp"/>


</body>
</html>
