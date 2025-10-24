<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="com.sec.clm.review.dto.ConsultationForm" %>

<%--
/**
 * 파  일  명 : Consideration_old_u.jsp
 * 프로그램명 : 의뢰내역 - 검토의뢰 - 계약검토[일반의뢰]
 * 설      명 : (구)법무시스템에서 이관된 검토정보에 대해 수정하는 페이지
 * 작  성  자 : 김형준
 * 작  성  일 : 2012.01.30
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
	String menuNavi = (String) request.getAttribute("secfw.menuNavi");

	ArrayList listDc = (ArrayList) request.getAttribute("listDc");

	ConsultationForm command = (ConsultationForm) request.getAttribute("command");
	ListOrderedMap lomRq = (ListOrderedMap) request.getAttribute("lomRq");
	ListOrderedMap lomMn = (ListOrderedMap) request.getAttribute("lomMn");
	ListOrderedMap lomVc = (ListOrderedMap) request.getAttribute("lomVc");
	
	
%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta sci="Consideration_old_u.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><spring:message code="clm.main.title" /></title>
<link href="<%=CSS%>/las.css"    type="text/css" rel="stylesheet">

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="/script/clms/common.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="/script/secfw/jquery/calendar/jquery.mousewheel.min.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>

<script language="javascript">

	$(document).ready(function(){
		
		detailContractMaster();
		
		initPage('');
		
		viewHiddenProgress(false);
		
		<c:choose>
		<c:when test="${lomRq.seal_mthd=='C02101'}">
			$('#sign-tr').hide();
			$('input[name=seal_mthd][value="C02101"]').attr('checked','checked');
		</c:when>
		<c:otherwise>
			$('#sign-tr').show();
			$('input[name=seal_mthd][value="C02102"]').attr('checked','checked');
		</c:otherwise>
	</c:choose>
	});

	/**
	* page Action
	*/
	function forwardConsideration(arg){
		var frm = document.frm;
		
		if(arg == "LIST"){									//목록
<%
			if(command.getStatus_mode() != null && "myContract".equals(command.getStatus_mode())) {
%>
			frm.target = "_self";		 
			frm.action = "<c:url value='/clm/manage/myContract.do' />";
			frm.method.value = "listMyContract";		
			frm.submit();
<%
			} else {
%>
			frm.target = "_self";		 
			frm.action = "<c:url value='/las/contractmanager/consideration.do' />";
			frm.method.value = "listConsideration";		
			frm.submit();
<%
			}
%>
		}
	}
	
	/**
	* 계약검토의뢰등록 폼  inner View 
	*/
	function detailContractMaster(){
		var options = {
			url: "<c:url value='/las/contractmanager/consideration.do?method=detailOldContractMasterForUpdate' />",
			type: "post",
			async: false,
			dataType: "html",
			success: function (data) {
				$("#tab_contents").html("");
				$("#tab_contents").html(data);
				
			}
		};
		$("#frm").ajaxSubmit(options);
	}

	function initPage(arg){
		var frm = document.frm;		
		var	file_key = frm.cntrt_id.value +"@"+ frm.cnsdreq_id.value;
		
	   //계약관련 #1 계약서
	    frm.target          = "fileList1";
	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    = "forwardClmsFilePage";
	    frm.file_bigclsfcn.value = "F012";
	    frm.file_midclsfcn.value = "F01202";
	    frm.file_smlclsfcn.value = "F0120201";
		frm.ref_key.value = file_key;
		frm.view_gbn.value = "modify";
	    frm.fileInfoName.value = "fileInfos1";
	    frm.fileFrameName.value = "fileList1";
	    frm.submit(); 
	}
	
	/*
	 *	임직원조회팝업 
	 */
	function searchEmployee(flag) {
		var frm 		= document.frm;
		var srchValue 	= "";
		var obj 		= new Object();
		if(flag=="sign"){
			obj = frm.sign_search_nm;
		} else if(flag=="contract") {
			obj = frm.cntrt_respman_search_nm;
		}	
		
		srchValue = comTrim(obj.value);
		frm.target = "PopUpEmployee";
	    frm.srch_user_cntnt_type.value = "userName"; //이름으로 검색  - 보통 이름으로 검색함
	    frm.srch_user_cntnt_target.value = flag;
	    frm.action = "<c:url value='/secfw/esbOrg.do' />";
	    frm.method.value = "listEsbEmployee";
	    frm.srch_user_cntnt.value = srchValue;
	    if (srchValue == "" || srchValue.length < 4) { //최소 2자리 이상 입력받게 한다.
	        alert("<spring:message code='secfw.msg.error.nameMinByte'/>");
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
	    	frm.signman_id.value 		= obj.epid;
	    	frm.signman_nm.value 		= obj.cn;
	    	frm.sign_dept_nm.value 	= obj.department;
	    	frm.signman_jikgup_nm.value = obj.title;
	    	
	    	frm.sign_search_nm.value= '';
	    	
	    	$('#plndman').html('');
	    	$('#plndman').append('&nbsp;&nbsp;' + obj.cn+'/'+obj.title+'/'+obj.department);
	    	
	    } else if(srch_user_target=="contract"){
	    	frm.cntrt_respman_id.value 			= obj.epid;
	    	frm.cntrt_respman_nm.value 			= obj.cn;
	    	//frm.cntrt_respman_search_nm.value	= obj.cn;
	    	frm.cntrt_respman_search_nm.value	= '';
	    	frm.cntrt_respman_jikgup_nm.value 	= obj.title;
	    	frm.cntrt_resp_dept_nm.value 		= obj.department;
	    	frm.cntrt_resp_dept.value			= obj.departmentnumber;
	    	
	    	$('#respman').html('');
	    	$('#respman').append('&nbsp;&nbsp;'+ obj.cn+'/'+obj.title+'/'+obj.department);
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
	
	/**
	 * 직인서명구분클릭시  이벤트
	 */
	function setSealMethod(val) {
		var frm = document.frm;
		if(val=="C02101") { //직인
			//$('#seal-tr').attr("style", "display:");
			//$('#sign-tr').attr("style", "display:none");
			$('#sign-tr').hide();
			
			//$('#seal_ffmtman_id').val('');
			//$('#seal_ffmtman_nm').val('');
			//$('#seal_ffmt_dept_cd').val('');
			//$('#seal_ffmt_dept_nm').val('');
			//$('#seal_ffmtman_jikgup_nm').val('');
			//$('#seal_ffmtman_search_nm').val('');
			//$('#ffmtman').html('');
		} else { //서명
			//$('#seal-tr').attr("style", "display:none");
			//$('#sign-tr').attr("style", "display:");
			$('#sign-tr').show();
			$('#signman_id').val('');
			$('#signman_nm').val('');
			$('#signman_jikgup_nm').val('');
			$('#sign_dept_nm').val('');
			$('#sign_search_nm').val('');
			$('#plndman').html('');
		}
	}
	
	var ffmtman_self_yn = 'N';
	function selfInsertFfmtman(){
		if(ffmtman_self_yn == 'N'){
			$('#ffmtman').text('');
			
			$('#ffmtman_self').attr("style", "display:");
			
			$('#seal_ffmtman_nm_t').val('');
			$('#seal_ffmt_dept_nm_t').val('');
			$('#seal_ffmtman_jikgup_nm_t').val('');
			$('#ffmtman_self_yn').val('Y');
			ffmtman_self_yn = "Y";
		}else{
			$('#ffmtman_self').attr("style", "display:none");
			
			$('#seal_ffmtman_nm_t').val('');
			$('#seal_ffmt_dept_nm_t').val('');
			$('#seal_ffmtman_jikgup_nm_t').val('');
			$('#ffmtman_self_yn').val('Y');
			ffmtman_self_yn = "N";
		}
	}
	
	var plndman_self_yn = 'N';
	function selfInsertPlndman(){
		if(plndman_self_yn == 'N'){
			$('#plndman').text('');
			
			$('#plndman_self').attr("style", "display:");
			
			$('#signman_nm_t').val('');
			$('#sign_dept_nm_t').val('');
			$('#signman_jikgup_nm_t').val('');
			$('#plndman_self_yn').val('Y');
			plndman_self_yn = 'Y';
		}else{
			$('#plndman_self').attr("style", "display:none");
			
			$('#signman_nm_t').val('');
			$('#sign_dept_nm_t').val('');
			$('#signman_jikgup_nm_t').val('');
			$('#plndman_self_yn').val('N');
			plndman_self_yn = 'N';
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
	
	function Save(){
		var frm = document.frm;
		
		<%--
		if($('#cntrt_nm').val() == ''){
			alert('계약명은 필수 입력 항목입니다.');return;
		}
		--%>
		

		if($('#cntrt_oppnt_nm').val() == ''){
			alert("<spring:message code='las.page.field.contractManager.mustDoContP'/>");return;
		}

		if($('#cntrt_oppnt_rprsntman').val() == ''){
			alert("<spring:message code='las.page.field.contractManager.mustDoRpnm'/>");return;
		}

		if($('#cntrt_oppnt_type').val() == ''){
			alert("<spring:message code='las.page.field.contractManager.mustDoEntype'/>");return;
		}
		
		if($('#biz_clsfcn').val() == ''){
			alert("<spring:message code='las.page.field.contractManager.classifyBus'/>");return;
		}

		if($('#depth_clsfcn').val() == ''){
			alert("<spring:message code='las.page.field.contractManager.mustDoStep'/>");return;
		}

		if($('#cnclsnpurps_bigclsfcn').val() == ''){
			alert("<spring:message code='las.page.field.contractManager.mustDoBigLv'/>");return;
		}
		
		if($('#cnclsnpurps_midclsfcn').val() == ''){
			alert("<spring:message code='las.page.field.contractManager.mustDoMdLv'/>");return;
		}
		
		if($('#cntrt_trgt').val() == ''){
			alert("<spring:message code='las.page.field.contractManager.mustDoTarget'/>");return;
		}

		var str_from = frm.cntrtperiod_startday.value.replace('-','').replace('-','');
	 	var str_to = frm.cntrtperiod_endday.value.replace('-','').replace('-','');
	 			
	 	if(eval(str_from) > eval(str_to)){
	 		alert("<spring:message code='las.page.field.contractManager.wrongPeriod'/>");return;
	 	}

		if($('#payment_gbn').val() == ''){
			alert("<spring:message code='las.page.field.contractManager.mustDoChkPay'/>");return; 		
		}
		
		if($('#cntrt_amt').val() == ''){
			alert("<spring:message code='las.page.field.contractManager.mustDoContAmt'/>");return; 		
		}

		if($('#crrncy_unit').val() == ''){
			alert("<spring:message code='las.page.field.contractManager.mustDoCu'/>");return; 		
		}
		
		if($('#cnclsn_plndday').val() == ''){
			alert('체결예정일는 필수 입력 항목 입니다.');return;
		}
		
		if($('#cntrt_cnclsnday').val() == ''){
			alert("<spring:message code='las.page.field.contractManager.mustDoContDate'/>");return;
		}
		
		var seal_mthd = $('input[name*=seal_mthd]:checked').val();
		if(seal_mthd == "C02101"){ //직인
			/*
			//직접 입력한 건!!
			if(ffmtman_self_yn == 'Y'){

				if($('#seal_ffmtman_nm_t').val() == ''){
					alert('날인담당자 이름은 필수 입력 항목입니다.');return;
				}	
				if($('#seal_ffmt_dept_nm_t').val() == ''){
					alert('날인담당자 부서는 필수 입력 항목입니다.');return;
				}	
				if($('#seal_ffmtman_jikgup_nm_t').val() == ''){
					alert('날인담당자 직급은 필수 입력 항목입니다.');return;
				}
				
				frm.seal_ffmtman_nm.value = frm.seal_ffmtman_nm_t.value;
				frm.seal_ffmt_dept_nm.value = frm.seal_ffmt_dept_nm_t.value;
				frm.seal_ffmtman_jikgup_nm.value = frm.seal_ffmtman_jikgup_nm_t.value;
			
			}else{
				if($('#seal_ffmtman_id').val() == '' && $('#seal_ffmtman_nm').val() == ''){
					alert('날인담당자는 필수 입력 항목입니다.');return;
				}	
			}
			*/
			
		}else{ //서명일 때
			
			//직접 입력한 건!!
			if(plndman_self_yn == 'Y'){
				if($('#signman_nm_t').val() == ''){
					alert("<spring:message code='las.page.field.contractManager.mustDoSignNm'/>");return;
				}	
				if($('#sign_dept_nm_t').val() == ''){
					alert("<spring:message code='las.page.field.contractManager.mustDoSignDept'/>");return;
				}	
				if($('#signman_jikgup_nm_t').val() == ''){
					alert("<spring:message code='las.page.field.contractManager.mustDoSignPos'/>");return;
				}

				frm.signman_nm.value = frm.signman_nm_t.value;
				frm.sign_dept_nm.value = frm.sign_dept_nm_t.value;
				frm.signman_jikgup_nm.value = frm.signman_jikgup_nm_t.value;

			}else{
				if($('#signman_id').val() == '' && $('#signman_nm').val() == ''){
					alert("<spring:message code='las.page.field.contractManager.mustDoSignature'/>");return;
				}
			}		
		}
		
		var d = new Date();
		var today = leadingZeros(d.getFullYear(), 4) + leadingZeros(d.getMonth() + 1, 2) + leadingZeros(d.getDate(), 2); //오늘 날짜
		
		//현재일이 계약기간 내에 있으면 
		if(eval(str_from) < eval(today) && eval(today) < eval(str_to) ){
			frm.cntrt_status.value 	= 'C02402'; //체결
			frm.prcs_depth.value 	= 'C02507'; 
			frm.depth_status.value 	= 'C02662'; //이행중
		}else{
			frm.cntrt_status.value 	= 'C02404'; //만료
			frm.prcs_depth.value 	= 'C02507'; 
			frm.depth_status.value 	= 'C02686'; //계약종료(만료)
		}

		//파일 업로드
		fileList1.UploadFile(function(uploadCount){
			if(uploadCount == -1){
				initPage();
				alert("<spring:message code='secfw.msg.error.fileUploadFail'/>");
				return;
			}

			frm.method.value = "modifyOldConsideration";
			frm.target = "_self";
			frm.action = "<c:url value='/las/contractmanager/consideration.do'/>";
			frm.submit();

			viewHiddenProgress(true) ;
		});
	}
	
	function leadingZeros(n, digits) {
		var zero = '';
		n = n.toString();

		if (n.length < digits) {
			for (i = 0; i < digits - n.length; i++)
				zero += '0';
		}
		return zero + n;
	}	
	
	
</script>


</head>
<body>
<div id="wrap">
	<!-- container -->
	<div id="container">
		<form:form name="frm" id='frm' method="post" autocomplete="off">
		<input type="hidden" name="method" id="method" value="">
		<input type="hidden" name="menu_id" id="menu_id" value="<c:out value='${command.menu_id}'/>" />
		<input type="hidden" name="curPage" id="curPage" value="<c:out value='${command.curPage}'/>">
		<input type="hidden" name="status" id="status" value="" />
		<input type="hidden" name="tab_cnt" id="tab_cnt" value="<c:out value='${lomRq.total_cnt}'/>" /><!-- Tab 계약 증가 값 -->
		<input type="hidden" name="cnsdreq_id" id="cnsdreq_id" value="<c:out value='${lomRq.cnsdreq_id}'/>" />		 
		<input type="hidden" name="cntrt_id" id="cntrt_id"  value="<c:out value='${lomRq.cntrt_id}'/>" />		
		<input type="hidden" name="prev_cnsdreq_id" id="prev_cnsdreq_id" value="<c:out value='${lomRq.prev_cnsdreq_id}'/>" />
		<input type="hidden" name="stat_flag" id="stat_flag" value="<c:out value='${command.stat_flag}'/>" />
		<input type="hidden" name="page_flag" id="page_flag" value="<c:out value='${command.page_flag}'/>" />
		<input type="hidden" name="dmstfrgn" id="dmstfrgn" value="<c:out value='${command.dmstfrgn}'/>" />
		<input type="hidden" name="req_title" id="req_title" value="<c:out value='${lomRq.req_title}'/>" />
		<!-- <input type="hidden" name="cntrt_oppnt_nm" id="cntrt_oppnt_nm" value="<c:out value='${command.cntrt_oppnt_nm}'/>" /> -->
			
		<!-- 첨부파일정보 -->
		<input type="hidden" name="fileInfos"   value="" /> <!-- 저장될 파일 정보 -->
		<input type="hidden" name="fileInfos1"   value="" /> <!-- 저장될 파일 정보 -->	
		<input type="hidden" name="fileInfos2"   value="" /> <!-- 저장될 파일 정보 -->	
		<input type="hidden" name="fileInfos3"   value="" /> <!-- 저장될 파일 정보 -->	
		<input type="hidden" name="fileInfos4"   value="" /> <!-- 저장될 파일 정보 -->
		<input type="hidden" name="fileInfos6"   value="" /> <!-- 저장될 파일 정보 -->	
		<input type="hidden" name="fileInfos5"   value="" /> <!-- 저장될 파일 정보 -->	
		
		<input type="hidden" name="fileInfoName"    value="" /> <!-- 첨부파일 정보가 리턴될 객체명 -->
		<input type="hidden" name="fileFrameName"    value="" /> <!-- 첨부파일 화면 iFrame 명 -->
			
		<input type="hidden" name="file_bigclsfcn"  value="" /> <!-- 대분류 -->	
		<input type="hidden" name="file_midclsfcn"  value="" /> <!-- 중분류 -->	
		<input type="hidden" name="file_smlclsfcn"  value="" /> <!-- 소분류 -->	
		<input type="hidden" name="ref_key"     value="<c:out value='${lomRq.cnsdreq_id}'/>" /> <!-- 키값 -->	
		<input type="hidden" name="view_gbn"    value="download" /> <!-- 첨부파일 출력 화면 종류 : upload, modify, download -->	
		
		<input type="hidden" name="reg_operdiv" value="<c:out value='${lomRq.reg_operdiv}'/>"/>
		<input type="hidden" name="cntrt_oppnt_cd" value="<c:out value='${lomRq.cntrt_oppnt_cd}'/>"/>	
		
		<input type="hidden" name="blngt_orgnz" 	id="blngt_orgnz" 	value="<c:out value='${command.blngt_orgnz}'/>" />	<!-- 소속조직 -->
		<input type="hidden" name="top_role" 		id="top_role" 		value="<c:out value='${command.top_role}'/>" />		<!-- ROLL -->	
		<input type="hidden" name="file_yn" id="file_yn" value="" />
		<input type="hidden" name="multiYn" value="" /><!-- 멀티 -->
		<input type="hidden" name="plndbn_req_yn" id="plndbn_req_yn" value="<c:out value="${lomRq.plndbn_req_yn}" />" /><!-- 최종본 의뢰 여부  -->
		
		<input type="hidden" name="srch_type_cd" value="<c:out value='${command.srch_type_cd}'/>" />
		<input type="hidden" name="srch_req_title" value="<c:out value='${command.srch_req_title}'/>" />
		<input type="hidden" name="srch_orgnz" value="<c:out value='${command.srch_orgnz}'/>" />
		<input type="hidden" name="srch_start_dt" value="<c:out value='${command.srch_start_dt}'/>" />
		<input type="hidden" name="srch_end_dt" value="<c:out value='${command.srch_end_dt}'/>" />
		<input type="hidden" name="srch_reqman_nm" value="<c:out value='${command.srch_reqman_nm}'/>" />
		<input type="hidden" name="srch_prgrs_status" value="<c:out value='${command.srch_prgrs_status}'/>" />
		<input type="hidden" name="srch_owner_dept" value="<c:out value='${command.srch_owner_dept}'/>" />
		<input type="hidden" name="srch_law_status" value="<c:out value='${command.srch_law_status}'/>" />
		<input type="hidden" name="srch_ip_status" value="<c:out value='${command.srch_ip_status}'/>" />
		<input type="hidden" name="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}'/>" />
		<input type="hidden" name="srch_biz_depth" value="<c:out value='${command.srch_biz_depth}'/>" />
		<input type="hidden" name="srch_cnclsnpurps" value="<c:out value='${command.srch_cnclsnpurps}'/>" />
		<input type="hidden" name="srch_cntrt_oppnt_nm" value="<c:out value='${command.srch_cntrt_oppnt_nm}'/>" />
		<input type="hidden" name="srch_cnsd_cont" value="<c:out value='${command.srch_cnsd_cont}'/>" />
		<input type="hidden" name="srch_if_sys_cd" value="<c:out value='${command.srch_if_sys_cd}'/>" />
		
		<!-- 계약관리의 My Contract로 돌아갈때 필요한 파라미터 -->
		<input type="hidden" name="status_mode" value="<c:out value='${command.status_mode}'/>" />
		<input type="hidden" name="list_mode" value="<c:out value='${command.list_mode}'/>" />
		<input type="hidden" name="srch_resp_dept" value="<c:out value='${command.srch_resp_dept}'/>" />
		<input type="hidden" name="srch_oppnt_cd" value="<c:out value='${command.srch_oppnt_cd}'/>" />
		<input type="hidden" name="srch_review_title" value="<c:out value='${command.srch_review_title}'/>" />
		<input type="hidden" name="srch_start_reqday" value="<c:out value='${command.srch_start_reqday}'/>" />
		<input type="hidden" name="srch_end_reqday" value="<c:out value='${command.srch_end_reqday}'/>" />
		<input type="hidden" name="srch_resp_dept_nm" value="<c:out value='${command.srch_resp_dept_nm}'/>" />
		<input type="hidden" name="srch_cnsdman_nm" value="<c:out value='${command.srch_cnsdman_nm}'/>" />
		<input type="hidden" name="srch_oppnt_nm" value="<c:out value='${command.srch_oppnt_nm}'/>" />
		<input type="hidden" name="srch_step" value="<c:out value='${command.srch_step}'/>" />
		<input type="hidden" name="srch_state" value="<c:out value='${command.srch_state}'/>" />
		<input type="hidden" name="srch_biz_clsfcn" value="<c:out value='${command.srch_biz_clsfcn}'/>" />
		<input type="hidden" name="srch_cnclsnpurps_bigclsfcn" value="<c:out value='${command.srch_cnclsnpurps_bigclsfcn}'/>" />
		<input type="hidden" name="srch_start_cnlsnday" value="<c:out value='${command.srch_start_cnlsnday}'/>" />
		<input type="hidden" name="srch_end_cnlsnday" value="<c:out value='${command.srch_end_cnlsnday}'/>" />
		<input type="hidden" name="cntrt_cnclsn_yn" id="cntrt_cnclsn_yn" value="<c:out value='${lomRq.cntrt_cnclsn_yn}'/>"/>
		 
		<c:forEach var="cntrtMt" items="${listDc}">
			<input type="hidden" name="master_cntrt_ids"  value="<c:out value='${cntrtMt.cntrt_id}' />" /> <!-- 계약_마스터_계약_ID -->	
		</c:forEach>	
		
		<input type="hidden" name="contents" id="contents" value=""/>
		
		<input type="hidden" name="srch_user_cntnt_type" value=""/>
		<input type="hidden" name="srch_user_cntnt" value=""/>
		<input type="hidden" name="srch_user_cntnt_target" value=""/>
		
		<input type="hidden" name="cntrt_status" id="cntrt_status" value=""/>
		<input type="hidden" name="prcs_depth" id="prcs_depth" value=""/>
		<input type="hidden" name="depth_status" id="depth_status" value=""/>
		
		<!-- location -->
		<div class="location"><IMG SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //location -->
		<!-- title -->
		<div class="title">
			<h3><spring:message code="las.page.field.contractmanager.consideration_d.title_cnsdreq" /> <spring:message code="las.page.field.contractManager.update"/></h3>
		</div>
		<!-- //title -->		
		<!-- content -->	
		<div id="content">
			<div class="t_titBtn">
				<!-- title -->
				<div class="title_basic">
					<h4><spring:message code="las.page.field.contractmanager.consideration_d.title_cnsdinfo" /></h4>
				</div>
				<!-- //title -->
				<!--View -->
				<!-- 수정/검토의뢰/최종본작성/목록 -->			
				<!-- button -->
				<div class="btn_wrap_t02">
                	<span id="btn_up5" class="btn_all_w"><span class="list"></span><a href="javascript:forwardConsideration('LIST');"><spring:message code="las.page.button.contractmanager.consideration_d.list" /></a></span>
                	<span id="btn_up5" class="btn_all_w"><span class="save"></span><a href="javascript:Save();"><spring:message code="las.page.field.contractManager.save"/></a></span>
                </div>
				<!-- //button -->
			</div>
			<!-- toptable -->
			<table cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
				<col width="15%" />
				<col width="7%" />
				<col width="20%" />
				<col width="13%" />
				<col width="16%" />
				<col width="13%" />
				<col width="16%" />
				</colgroup>
				<tr>
					<th><spring:message code="las.page.field.contractmanager.consideration_d.cnsd_title" /></th>
				  	<td colspan="6"><c:out value='${lomRq.req_title}' default="" /></td>
				</tr>
				<tr class="lineAdd">
					<th><spring:message code="las.page.field.contractmanager.consideration_d.reqman_nm" /></th>
					<td colspan="6"><c:out value="${lomRq.reqman_nm}" /> / <c:out value="${lomRq.reqman_jikgup_nm}" /> / <c:out value="${lomRq.req_dept_nm}" /></td>
				</tr>
			</table>
			<div id="tr_down01" class="border-top-no">
				<table cellspacing="0" cellpadding="0" class="table-style01">
					<colgroup>
						<col width="15%" />
						<col width="11%" />
						<col width="6%" />
						<col width="8%" />
						<col width="6%" />
						<col width="8%" />
						<col width="8%" />
						<col width="15%" />
						<col width="8%" />
						<col width="15%" />
					</colgroup>
					<tr class="lineAdd">
						<th class="borTz02"><spring:message code="las.page.field.contractmanager.consideration_d.cnsd_demnd_cont" /></th>
						<td colspan="9">									 
						<c:out value='${lomRq.cnsd_demnd_cont}' escapeXml="false" /></td>
					</tr>
				</table>
				<!-- //top table -->
					
				<!-- 법무 시스템 - 계약검토 -->
				<div id="detailInfoScript">
				</div>
				
				<div id="detailInfoHtmlUp" class="border-top-no">
				</div>
			</div>
 		
			<!-- //법무 시스템 - 계약검토 -->
			
			<!-- title -->
		   <div class="title_basic">
				<h4><spring:message code="las.page.field.contractmanager.consideration_d.cnsd_default_info" /></h4>
			</div>
			<!-- //title -->
			<!-- tab01 -->
			<ul id="tab_title" class="tab_basic04">
				<c:forEach var="list" items="${listDc}">
				<c:choose>
				<c:when test="${list.rn=='1'}">
		          		<li id="tab_li<c:out value='${list.rn}'/>" class="on"><a href="javascript:titleTabClick('<c:out value='${list.rn}'/>','<c:out value='${list.cntrt_id}'/>');"><spring:message code="las.page.field.contractmanager.consideration_d.cntrt" /><c:out value='${list.rn}'/></a></li>	
				</c:when>
				<c:otherwise>
						<li id="tab_li<c:out value='${list.rn}'/>"><a href="javascript:titleTabClick('<c:out value='${list.rn}'/>','<c:out value='${list.cntrt_id}'/>');"><spring:message code="las.page.field.contractmanager.consideration_d.cntrt" /><c:out value='${list.rn}'/></a></li>
				</c:otherwise>	
				</c:choose>				
				</c:forEach>						
			</ul>
			<!-- 두줄라인 추가 --><div class="tal_topLine"></div><!-- //두줄라인 추가 -->
			<!-- middle table -->
			<div id="tab_contents"></div>
			<!-- //tab_content -->
			
			<!-- 이력정보 -->
			<div id="contractHis-list"></div>
			<!-- //이력정보 -->
 
 		</div>
		
		<div id="detailInfoHtmlDown">
		</div>
		<!-- button -->
		<div class="btn_wrap_c">
			<span id="btn_down5" class="btn_all_w"><span class="list"></span><a href="javascript:forwardConsideration('LIST');"><spring:message code="las.page.button.contractmanager.consideration_d.list" /></a></span>
			<span id="btn_up5" class="btn_all_w"><span class="save"></span><a href="javascript:Save();"><spring:message code="las.page.field.contractManager.save"/></a></span>
		</div>
		<!-- //button -->
		<!-- //content -->	
		<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- // footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</form:form>
	</div>
	<!-- //container -->
</div>
</body>
</html>
 
 