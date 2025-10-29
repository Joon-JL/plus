<!DOCTYPE html>
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
 * 파  일  명 : Consideration_old_i.jsp
 * 프로그램명 : 사별이관 계약 등록
 * 설      명 : (구)법무시스템에서 이관된 검토정보에 대해 입력하는 페이지
 * 작  성  자 : 김재원
 * 작  성  일 : 2013.04
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
	String menuNavi = (String) request.getAttribute("secfw.menuNavi");

	ConsultationForm command = (ConsultationForm) request.getAttribute("command");
%>

<html>
<head>
<meta sci="Consideration_old_i.jsp" />
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="clm.main.title" /></title>
<link href="<%=CSS%>/las.css"    type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
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
		
// 		$('#sign-tr').hide();
		initPage('');
		viewHiddenProgress(false);
		
		var amt = frm.cntrt_amt.value;
		frm.cntrt_amt.value = Comma(amt);
		initCal("cntrtperiod_startday");
		initCal("cntrtperiod_endday");
		//initCal("cnclsn_plndday");    //체결예정일
		initCal("cntrt_cnclsnday");    //계약체결일
		
		//비즈니스 분류
		getCodeSelectByUpCd("frm", "biz_clsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=T01&combo_abbr=F&combo_type=S&combo_del_yn=N&combo_selected=");
		//단계
		getCodeSelectByUpCd("frm", "depth_clsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=T02&combo_abbr=F&combo_type=S&combo_del_yn=N&combo_selected=");
		//체결목적 대분류
		getCodeSelectByUpCd("frm", "cnclsnpurps_bigclsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=T03&combo_abbr=F&combo_type=S&combo_del_yn=N&combo_selected=");
		//체결목적 중분류
		getCodeSelectByUpCd("frm", "cnclsnpurps_midclsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=&combo_abbr=F&combo_type=S&combo_del_yn=N&combo_selected=");

		//거래상재방 업체 Type - cntrt_oppnt_type	
		getCodeSelectByUpCd("frm", "cntrt_oppnt_type", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=C056&combo_type=S&combo_del_yn=N&combo_selected=');
		//계약대상  - cntrt_trgt		
		getCodeSelectByUpCd("frm", "cntrt_trgt", '/common/clmsCom.do?method=getComboHTML&combo_sys_cd=LAS&combo_gbn=CONTRACTTYPE&combo_up_cd=&combo_abbr=F&combo_type=S&combo_del_yn=N&combo_selected=');
		//지불구분
		getCodeSelectByUpCd("frm", "payment_gbn", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=C020&combo_type=&combo_del_yn=N&combo_selected=');
		//화페단위
		getCodeSelectByUpCd("frm", "crrncy_unit", '/common/clmsCom.do?method=listComCdByGrpCd&combo_abbr=A&combo_grp_cd=C5&combo_type=S&combo_del_yn=N&combo_selected=');
		
	});
	
	$(function() {
		$('#payment_gbn').change(function(){			
			if($('#payment_gbn').val() == 'C02004'){
				$('#cntrt_amt').attr("disabled","disabled");
				$('#crrncy_unit').attr("disabled","disabled");
			}
			else{
				$('#cntrt_amt').removeAttr("disabled");
				$('#crrncy_unit').removeAttr("disabled");
			}
		});
	});
	
	$(function() {
		$('#biz_clsfcn').change(function(){			
			if($('#biz_clsfcn').val() == 'T0101'){
				$('#cntrt_amt').attr('value','0');
				$('#crrncy_unit').attr('value','KRW');
			}
			else{
				$('#cntrt_amt').attr('value','');
			}
		});
	});
	
	function changeSubCd(selObjId, gbn, upCd) {
		if(upCd == "") {
	    	upCd = "XXX";
		}
		getCodeSelectByUpCd('frm', selObjId, '/common/clmsCom.do?method=getComboHTML&combo_gbn=' + gbn + '&combo_up_cd=' + upCd + '&combo_abbr=F&combo_type=T&combo_del_yn=N');
	}

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
	
	function initPage(arg){
		var frm = document.frm;		
		var	file_key = "";
		
	   //계약관련 #1 계약서
	    frm.target          = "fileList1";
	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    = "forwardClmsFilePage";
	    frm.file_bigclsfcn.value = "F012";
	    frm.file_midclsfcn.value = "F01202";
	    frm.file_smlclsfcn.value = "F0120201";
		frm.ref_key.value = file_key;
		frm.view_gbn.value = "modify";
		frm.multiYn.value = "N";
	    frm.fileInfoName.value = "fileInfos1";
	    frm.fileFrameName.value = "fileList1";
	    frm.submit(); 
	}
	
	/*
	 *	임직원조회팝업 
	 */
	function searchEmployee(flag) {
		
// 		setUserInfos('contract');
		
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

		if(srch_user_target=="sign") {
	    	frm.signman_id.value 		= obj.epid;
	    	frm.signman_nm.value 		= obj.cn;
	    	frm.sign_dept_nm.value 	= obj.department;
	    	frm.signman_jikgup_nm.value = obj.title;
	    	
	    	frm.sign_search_nm.value= '';
	    	
	    	$('#plndman').html('');
	    	$('#plndman').append('&nbsp;&nbsp;' + obj.cn+'/'+obj.title+'/'+obj.department);
	    	
	    } else if(srch_user_target=="contract"){
	    	
	    	/*
	    	frm.cntrt_respman_id.value 			= "D091230221722C100767";		
			frm.cntrt_respman_nm.value 			= "박태준";
			frm.cntrt_respman_search_nm.value   = "박태준";
			*/
			
			frm.cntrt_respman_id.value 			= obj.epid;
	    	frm.cntrt_respman_nm.value 			= obj.cn;
	    	frm.cntrt_respman_jikgup_nm.value 	= obj.title;
	    	frm.cntrt_resp_dept.value			= obj.departmentnumber;
	    	frm.cntrt_resp_dept_nm.value 		= obj.department;
	    	frm.reg_intnl_dept_cd.value		= obj.epindeptcode; //내부부서코드
	    	
	    	frm.cntrt_respman_search_nm.value	= '';
	    	
	    	$('#cntrt_respman').html('');
	    	$('#cntrt_respman').append('&nbsp;&nbsp;'+ obj.cn+'/'+obj.title+'/'+obj.department);
	    	
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
		
		if(val=="C02101") { //직인
			//$('#seal-tr').attr("style", "display:none");
			//$('#seal-tr').show();
			//$('#sign-tr').hide();
			//$('#sign-tr').attr("style", "display:none");
			
			
			//$('#seal_ffmtman_id').val('');
			//$('#seal_ffmtman_nm').val('');
			//$('#seal_ffmt_dept_cd').val('');
			//$('#seal_ffmt_dept_nm').val('');
			//$('#seal_ffmtman_jikgup_nm').val('');
			//$('#seal_ffmtman_search_nm').val('');
			//$('#ffmtman').html('');
		} else { //서명
			//$('#seal-tr').attr("style", "display:none");
			//$('#seal-tr').hide();
			$('#sign-tr').show();
			//$('#sign-tr').attr("style", "display:");
			
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
		
		
		if($('#req_title').val() == ''){
			alert("<spring:message code='las.page.field.contractManager.mustDoTitle'/>");return;			
		}
		
		if($('#cntrt_respman_id').val() == ''){
			alert("<spring:message code='las.page.field.contractManager.mustDoRequby'/>");return;
		}
		
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
		
		if(frm.cntrtperiod_startday.value == ''){
			alert("<spring:message code='las.page.field.contractManager.mustDoFromDate'/>"); return;
		}
		
		if(frm.cntrtperiod_endday.value == ''){
			alert("<spring:message code='las.page.field.contractManager.mustDoToDate'/>"); return;
		}

		var str_from = frm.cntrtperiod_startday.value.replace('-','').replace('-','');
	 	var str_to = frm.cntrtperiod_endday.value.replace('-','').replace('-','');
	 			
	 	if(eval(str_from) > eval(str_to)){
	 		alert("<spring:message code='las.page.field.contractManager.wrongPeriod'/>");return;
	 	}

		if($('#payment_gbn').val() == ''){
			alert("<spring:message code='las.page.field.contractManager.mustDoChkPay'/>");return; 		
		}
		
		if($('#payment_gbn').val() != 'C02004'){
			if($('#cntrt_amt').val() == ''){
				alert("<spring:message code='las.page.field.contractManager.mustDoContAmt'/>");return; 		
			}
	
			if($('#crrncy_unit').val() == ''){
				alert("<spring:message code='las.page.field.contractManager.mustDoCu'/>");return; 		
			}
		}
		
		//if($('#cnclsn_plndday').val() == ''){
		//	alert('체결예정일는 필수 입력 항목 입니다.');return;
		//}
		
		if($('#cntrt_cnclsnday').val() == ''){
			alert("<spring:message code='las.page.field.contractManager.mustDoContDate'/>");return;
		}
		
		var seal_mthd = $('#seal_mthd').val();
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
			frm.prcs_depth.value 	= 'C02507'; //사업부이관 고정
			frm.depth_status.value 	= 'C02662'; //이행중
			frm.prgrs_status.value 	= 'C04219'; //이행중
			frm.cnsd_status.value 	= 'C04305';
		}else{
			frm.cntrt_status.value 	= 'C02404'; //만료
			frm.prcs_depth.value 	= 'C02507'; //사업부이관 고정
			frm.depth_status.value 	= 'C02686'; //계약종료(만료)
			frm.prgrs_status.value 	= 'C04223'; //계약종료
			frm.cnsd_status.value 	= 'C04305';
		}
		
		viewHiddenProgress(true) ;

		//파일 업로드
		fileList1.UploadFile(function(uploadCount){
			if(uploadCount == -1){
				initPage();
				alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
				
				viewHiddenProgress(false) ;
				
				return;
			}

			frm.target = "_self";
			frm.pdf_gbn.value = 'Y';  // pdf 변환을 위한 기본값 세팅을 위한 내용입니다.
			frm.action = "<c:url value='/clm/review/consideration.do'/>";
			frm.method.value = "insertOldConsideration";
			frm.submit();

			
		});
	}
	
	//금액에 숫자만 입력하게 합니다. 숫자값이 아니면 그냥 빈칸으로 만들어 버리죠.
	function olnyNum(obj){
		var str = obj.value;
		str = new String(str);
		var Re = /[^0-9]/g;
		str = str.replace(Re,'');
		
		// 금액 100,000 형태로 변환 추가 (2011/10/15)
		obj.value = Comma(str);
	}
	
	function leadingZeros(n, digits) {
		var zero = '';
		n = n.toString();

		if (n.length < digits) {
			for (var i = 0; i < digits - n.length; i++)
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
				
		<!-- location -->
		<div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0" alt="Home" /><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //location -->
		<!-- title -->
		<div class="title">
			<h3><spring:message code="las.page.field.ExecutedcontractManager.regist"/></h3>
		</div>
		<!-- //title -->		
		<!-- content -->	
		<div id="content">
		<div id="content_in">
		    <form:form name="frm" id='frm' method="post" autocomplete="off">
		<input type="hidden" name="method" id="method" value="" />
		<input type="hidden" name="menu_id" id="menu_id" value="<c:out value='${command.menu_id}'/>" />
		<input type="hidden" name="curPage" id="curPage" value="<c:out value='${command.curPage}'/>" />
		<input type="hidden" name="status" id="status" value="" />
		<input type="hidden" name="stat_flag" id="stat_flag" value="<c:out value='${command.stat_flag}'/>" />
		<input type="hidden" name="page_flag" id="page_flag" value="<c:out value='${command.page_flag}'/>" />
		<input type="hidden" name="dmstfrgn" id="dmstfrgn" value="<c:out value='${command.dmstfrgn}'/>" />
			
		<!-- 첨부파일정보 -->
		<input type="hidden" name="fileInfos1"   value="" /> <!-- 저장될 파일 정보 -->	
		
		<input type="hidden" name="fileInfoName"    value="" /> <!-- 첨부파일 정보가 리턴될 객체명 -->
		<input type="hidden" name="fileFrameName"    value="" /> <!-- 첨부파일 화면 iFrame 명 -->
			
		<input type="hidden" name="file_bigclsfcn"  value="" /> <!-- 대분류 -->	
		<input type="hidden" name="file_midclsfcn"  value="" /> <!-- 중분류 -->	
		<input type="hidden" name="file_smlclsfcn"  value="" /> <!-- 소분류 -->	
		<input type="hidden" name="ref_key"     value="" /> <!-- 키값 -->	
		<input type="hidden" name="view_gbn"    value="modify" /> <!-- 첨부파일 출력 화면 종류 : upload, modify, download -->	
		
		<input type="hidden" name="file_yn" id="file_yn" value="" />
		<input type="hidden" name="multiYn" value="" /><!-- 멀티 -->
		
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
		
		<input type="hidden" name="contents" id="contents" value=""/>
		
		<input type="hidden" name="srch_user_cntnt_type" value=""/>
		<input type="hidden" name="srch_user_cntnt" value=""/>
		<input type="hidden" name="srch_user_cntnt_target" value=""/>
		
		<input type="hidden" name="reg_intnl_dept_cd" id="reg_intnl_dept_cd" value=""/>
		<input type="hidden" name="cntrt_status" id="cntrt_status" value=""/>
		<input type="hidden" name="prcs_depth" id="prcs_depth" value=""/>
		<input type="hidden" name="depth_status" id="depth_status" value=""/>
		<input type="hidden" name="prgrs_status" id="prgrs_status" value=""/>
		<input type="hidden" name="cnsd_status" id="cnsd_status" value=""/>
		<input type="hidden" name="cntrt_cnclsn_yn" id="cntrt_cnclsn_yn" value="Y"/>
		
		<input type="hidden" name="pdf_gbn" id="pdf_gbn"/>
			<div class="t_titBtn">
				<!-- title -->
				<div class="title_basic">
					<h4><spring:message code="las.page.field.contractmanager.consideration_d.title_cnsdinfo" /></h4>
				</div>
				<!-- //title -->
				<!--View -->
				<!-- 수정/검토의뢰/최종본작성/목록 -->			
				<!-- button -->
				<div class="btnwrap">
                	<span id="btn_up5" class="btn_all_w"><span class="tsave"></span><a href="javascript:Save();"><spring:message code="las.page.field.contractManager.save"/></a></span>
                	<span id="btn_up5" class="btn_all_w"><span class="list"></span><a href="javascript:forwardConsideration('LIST');"><spring:message code="las.page.button.contractmanager.consideration_d.list" /></a></span>
				</div>
				<!-- //button -->
			</div>
			<!-- toptable -->
			<table cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
				<col width="15%" />
				<col width="85%" />
				
				</colgroup>
				<tr>
					<th><spring:message code="las.page.field.contractmanager.consideration_d.cnsd_title" /></th>
				  	<td>
				  		<input type="text" name="req_title" id="req_title" class="text_full" value="" />
				  	</td>
				</tr>
				<tr class="lineAdd">
					<th><spring:message code="las.page.field.contractmanager.consideration_d.reqman_nm" /></th>
					<td>
						<input type="hidden" name="cntrt_respman_id" 	id="cntrt_respman_id" value="" />
						<input type="hidden" name="cntrt_respman_nm" 	id="cntrt_respman_nm" value="" />
						<input type="hidden" name="cntrt_resp_dept" 	id="cntrt_resp_dept" value="" />
						<input type="hidden" name="cntrt_resp_dept_nm" 	id="cntrt_resp_dept_nm" value="" />
						<input type="hidden" name="cntrt_resp_up_dept" 	id="cntrt_resp_up_dept" value="" />
						<input type="hidden" name="cntrt_respman_jikgup_nm" id="cntrt_respman_jikgup_nm" value="" />
						<input type="text"   name="cntrt_respman_search_nm" id="cntrt_respman_search_nm" value="" style="width:110px" class="text_search" onkeydown="if(event.keyCode==13){event.returnValue = false;javascript:searchEmployee('contract');}"/><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchEmployee('contract')" class="cp" alt="search" />
				  		<span id="cntrt_respman"></span>
					</td>
				</tr>
			</table>
			
			
			<!-- title -->
		   <div class="title_basic">
				<h4><spring:message code="las.page.field.contractmanager.consideration_d.cnsd_default_info" /></h4>
			</div>
			<!-- //title -->
			
			
			
			<!-- middle table -->
			<table cellspacing="0" cellpadding="0" class="table-style01">
			   <colgroup>
					<col width="15%" />
					<col width="9%" />
					<col width="21%" />
					<col width="14%" />
					<col width="12%" />
					<col width="12%" />
					<col width="17%" />
				</colgroup>
				
				<%--
				<tr>
					<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.cntrt_nm" /></th>
					<td colspan="6"> 
						<input type="text" name="cntrt_nm" id="cntrt_nm" class="text_full" value="" />
					</td>
				</tr>
	 			--%>
	 			
				<tr>
					<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.cntrt_oppnt_nm" /></th>
					<td colspan="2">
						<input type="text" name="cntrt_oppnt_nm" id="cntrt_oppnt_nm" value="" class="text_full" style="width:80%" />
					</td>
					<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.cntrt_oppnt_rprsntman" /></th>
					<td>
						<input type="text" name="cntrt_oppnt_rprsntman" id="cntrt_oppnt_rprsntman" value="" maxlength="60" class="text_full" style="width:80%" />
					</td>
					<th><spring:message code="clm.page.msg.common.otherParty" /></th>
					<td>
						<select name="cntrt_oppnt_type" id="cntrt_oppnt_type"></select>
					</td>
				</tr>
				<tr class="slide-target02 slide-area">			
					<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.cntrt_type" /></th>				
					<td colspan="6">
						<select style="width:140px;" name="biz_clsfcn" id="biz_clsfcn" >
						</select> 
						<select style="width:140px" name="depth_clsfcn" id="depth_clsfcn" >
						</select>
						<select style="width:140px" name="cnclsnpurps_bigclsfcn" id="cnclsnpurps_bigclsfcn" onchange='javascript:changeSubCd("cnclsnpurps_midclsfcn", "CONTRACTTYPE", this.value); changeSubCd("cntrt_trgt", "CONTRACTTYPE", "");'>
						</select>
						<select style="width:140px;" name="cnclsnpurps_midclsfcn" id="cnclsnpurps_midclsfcn" onchange='javascript:changeSubCd("cntrt_trgt", "CONTRACTTYPE", this.value);'>
						</select>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.cntrt_trgt_nm" /></th>
					<td colspan="6">
						<select name="cntrt_trgt" id="cntrt_trgt" class="all" style="width:140px">
						</select>
					</td>
				</tr>
				<tr class="slide-target02 slide-area">
					<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.cntrtperiod" /></th>
					<td colspan="2">
						<input type="text" name="cntrtperiod_startday" id="cntrtperiod_startday" alt="<spring:message code="las.page.field.contractManager.contFromDate"/>" value="" class="text_calendar02" readonly="readonly"/> ~ 
						<input type="text" name="cntrtperiod_endday" id="cntrtperiod_endday" alt="<spring:message code="las.page.field.contractManager.contToDate"/>" value="" class="text_calendar02" readonly="readonly"/>
					</td>
					<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.payment_gbn_nm" /></th>
					<td colspan="3">
						<select name="payment_gbn" id="payment_gbn" class="all" style="width:150px">						
						</select>
					</td>
				</tr>
				
				<tr class="slide-target02 slide-area" id="change_tr">
					<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.cntrt_amt" /></th>
					<td colspan="2">
						<input type="text" name="cntrt_amt" id="cntrt_amt" value="" onkeyup='olnyNum(this)' style="width: 150px; text-align: right; margin-right: 1px; IME-MODE: disabled;" class="text_full" maxlength="19" />
					</td>
					<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.currency" /></th>
					<td colspan="3">
						<select name="crrncy_unit" id="crrncy_unit">						
						</select>
					</td>
				</tr>
	
				<tr class="slide-target02 slide-area">
					<th><spring:message code="clm.page.field.contract.consultation.detail.autorenew" /></th>
					<td colspan="2">
						<select name="auto_rnew_yn" id="auto_rnew_yn" style="width:100px;">
							<option value="Y" >Yes</option>
							<option value="N" selected="selected">No</option>
						</select>
					</td>
					<!-- 
					<th><spring:message code="clm.page.field.contract.conclusion.detail.conclusionyn" /></th>
					<td colspan="3">
						<select name="cntrt_cnclsn_yn" id="cntrt_cnclsn_yn" style="width:100px;">
							<option value="Y" >Yes</option>
							<option value="N" >No</option>
						</select>
					</td>
					 -->
					<th><spring:message code="clm.page.field.contract.conclusion.detail.conclusionday" /></th>
					<td colspan="3">
						<input type="text" name="cntrt_cnclsnday" id="cntrt_cnclsnday" value="" class="text_calendar02" readonly="readonly"/>
					</td>					 
				</tr>

				<!-- 	
				<tr class="slide-target02 slide-area">
					<th><spring:message code="clm.page.field.contract.consultation.detail.conclusionday" /></th>
					<td colspan="2">
						<input type="text" name="cnclsn_plndday" id="cnclsn_plndday" value="" class="text_calendar02" readOnly/>
					</td>
				</tr>
				 -->
	
				<tr >
					<th><spring:message code="clm.page.msg.manage.sign"/></th>
					<td colspan="6">
						<input type="hidden" name="seal_mthd" id="seal_mthd" value="C02102" />
						<spring:message code="clm.page.field.contract.consultation.detail.sign"/>
<%-- 						<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02101" checked onclick="javascript:setSealMethod(this.value);"><spring:message code="clm.page.field.contract.consultation.detail.seal"/></input> --%>
<%-- 						<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02102" onclick="javascript:setSealMethod(this.value);"><spring:message code="clm.page.field.contract.consultation.detail.sign"/></input> --%>
					</td>
				</tr>
				
				<!-- 				
				<tr id="seal-tr" class="slide-target02 slide-area" style="display:none">
					<th><spring:message code="clm.page.field.contract.consultation.detail.sealffmtmannm"/></th>
					<td colspan="6">		
						<input type="hidden" name="seal_ffmtman_id" id="seal_ffmtman_id" value="" /> 
				  		<input type="hidden" name="seal_ffmtman_nm" id="seal_ffmtman_nm" value="" />
				  		<input type="hidden" name="seal_ffmt_dept_cd" id="seal_ffmt_dept_cd" value="" />
					  	<input type="hidden" name="seal_ffmt_dept_nm" id="seal_ffmt_dept_nm" value="" />
					  	<input type="hidden" name="seal_ffmtman_jikgup_nm" id="seal_ffmtman_jikgup_nm" value="" />
				  		<input type="text"   name="seal_ffmtman_search_nm" id="seal_ffmtman_search_nm" value="" style="width:110px" class="text_search" onKeyDown="if(event.keyCode==13){event.returnValue = false;javascript:searchSealPerson();}"/><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchSealPerson();" class="cp" alt="search" />
				  		<span id="ffmtman"></span>
				  		<br><br>
				  		※ 사용자의 정보가 검색이 안될 경우 수동으로 입력해 주세요.
				  		<a href="javascript:selfInsertFfmtman();">직접입력</a>
				  		<input type="hidden" name="ffmtman_self_yn" id="ffmtman_self_yn" value=""/>
				  		<div id="ffmtman_self" style="display:none">
				  			이름:<input type="text" name="seal_ffmtman_nm_t" id="seal_ffmtman_nm_t" value="" maxlength="60" class="text_full" style="width:110px">
				  			직급:<input type="text" name="seal_ffmt_dept_nm_t" id="seal_ffmt_dept_nm_t" value="" maxlength="60" class="text_full" style="width:110px">
				  			부서:<input type="text" name="seal_ffmtman_jikgup_nm_t" id="seal_ffmtman_jikgup_nm_t" value="" maxlength="60" class="text_full" style="width:110px">
				  		</div>
					</td>
				</tr>
				 -->
				
				<tr id="sign-tr" class="slide-target02 slide-area">
					<th><spring:message code="las.page.field.contractManager.theSigner"/></th>
					<td colspan="6">
						<input type="hidden" name="signman_id" id="signman_id" value="" />
				  		<input type="hidden" name="signman_nm" id="signman_nm" value="" />
				  		<input type="hidden" name="signman_jikgup_nm" id="signman_jikgup_nm" value="" />
				  		<input type="hidden" name="sign_dept_nm" id="sign_dept_nm" value="" />
				  		<input type="text" name="sign_search_nm" id="sign_search_nm" value="" style="width:110px" class="text_search" alt="<spring:message code='clm.page.field.contract.consultation.detail.signplannernm'/>" onkeydown="if(event.keyCode==13){event.returnValue = false;javascript:searchEmployee('sign');}"/><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchEmployee('sign');" class="cp" alt="search" />
				  		<span id="plndman"></span>
				  		<br/><br/>
				  		<spring:message code="las.page.field.contractManager.ifManualInput"/>
				  		<a href="javascript:selfInsertPlndman();">(<spring:message code="las.page.field.contractManager.manualInput"/>)</a>
				  		<input type="hidden" name="plndman_self_yn" id="plndman_self_yn" value=""/>
				  		<div id="plndman_self" style="display:none">
				  			<spring:message code="las.page.field.contractManager.nm"/>:<input type="text" name="signman_nm_t" id="signman_nm_t" value="" maxlength="60" class="text_full" style="width:110px" />
				  			<spring:message code="las.page.field.contractManager.rank"/>:<input type="text" name="signman_jikgup_nm_t" id="signman_jikgup_nm_t" value="" maxlength="60" class="text_full" style="width:110px" />
				  			<spring:message code="las.page.field.contractManager.dept"/>:<input type="text" name="sign_dept_nm_t" id="sign_dept_nm_t" value="" maxlength="60" class="text_full" style="width:110px" />
				  		</div>
					</td>			
				</tr>			
							
				<tr class="slide-target02 slide-area">
					<th><spring:message code="las.page.field.contractManager.keepOrgnYn"/></th>
					<td colspan="6">
						<select name="org_strg_pos" id="org_strg_pos" style="width:100px;">
							<option value="Y" >Yes</option>
							<option value="N" >No</option>
						</select>
					</td>
				</tr>
				<tr class="slide-target02 slide-area">
					<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.attached_file" /></th>
					<th class='sub01'><spring:message code="las.page.field.contractmanager.consideration_inner_d.contract" /></th>
					<td colspan="5">
						<div class='ico_maxsize fR'>Max Size : <span>10MB</span>   </div> 
  	                    <iframe src="<c:url value='/clm/blank.do' />" id="fileList1" name="fileList1" frameborder="0" class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>
					</td>
				</tr>
			</table>
			
			<div class="btnwrap mt20">
				<span id="btn_up5" class="btn_all_w"><span class="tsave"></span><a href="javascript:Save();"><spring:message code="las.page.field.contractManager.save"/></a></span>
				<span id="btn_down5" class="btn_all_w"><span class="list"></span><a href="javascript:forwardConsideration('LIST');"><spring:message code="las.page.button.contractmanager.consideration_d.list" /></a></span>
			</div>
			
			
			
 
 		</div>
		
		<div id="detailInfoHtmlDown">
		</div>
		<!-- button -->
		
		<!-- //button -->
		<!-- //content -->	
		
	</form:form>
	</div>
	</div>
	<!-- //container -->
</div>
</div>
<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- // footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</body>
</html>
 
 