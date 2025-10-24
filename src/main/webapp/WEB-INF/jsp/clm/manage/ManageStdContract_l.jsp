<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sec.clm.manage.dto.ManageForm" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sec.common.util.ClmsBoardUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%-- 
/**
 * 파  일  명 : ManageStdContract_l.jsp
 * 프로그램명 : MyContract목록
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2013.05
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	List resultList = (List)request.getAttribute("resultList");
	ManageForm command = (ManageForm)request.getAttribute("command");
%>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet"></link>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/menu.js'/>"></script>

<script language="javascript">

	$(document).ready(function(){
		//Step 세팅            search_state
		getCodeSelectByUpCd("frm", "srch_step", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=C025&useman_mng_itm2=clmsList&combo_type=T&combo_del_yn=N&combo_selected='+'<c:out value='${command.srch_step}'/>');
		getCodeSelectByUpCd("frm", "srch_state", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=' + '<c:out value='${command.srch_step}'/>' +'&combo_type=T&combo_del_yn=N&combo_selected='+'<c:out value='${command.srch_state}'/>');
		//비즈니스 단계 
		getCodeSelectByUpCd("frm", "srch_biz_clsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=T01&combo_abbr=F&combo_type=S&combo_del_yn=N&combo_selected="+"<c:out value='${command.srch_biz_clsfcn}'/>");   
		//계약유형1
		getCodeSelectByUpCd("frm", "srch_cnclsnpurps_bigclsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=T03&combo_abbr=F&combo_type=S&combo_del_yn=N&combo_selected="+"<c:out value='${command.srch_cnclsnpurps_bigclsfcn}'/>");   
		//연계시스템 세팅
		getCodeSelectByUpCd("frm", "srch_if_sys_cd", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=C066&combo_type=T&combo_del_yn=N&combo_selected='+'<c:out value='${command.srch_if_sys_cd}'/>');
		$("#srch_if_sys_cd").append("<option value='NO'<c:if test="${command.srch_if_sys_cd eq 'NO'}"> selected</c:if>><spring:message code="clm.page.msg.manage.notIf" /></option>") ;
		
		
	 	initCal("srch_start_reqday");   //첫번재 의뢰일 설정
	    initCal("srch_end_reqday");     //두번재 의뢰일 설정
	 	initCal("srch_start_cnlsnday");   //첫번재 체결일 설정
	    initCal("srch_end_cnlsnday");     //두번재 체결일 설정
	    
	    <% if("cnsdreq".equals(command.getList_mode())) {%>
	    initCal("srch_str_org_acptday");   //첫번재 원본접수일 설정
        initCal("srch_end_org_acptday");     //두번재 원본접수일 설정
	    <%}%>
	    
	});
	
	$(function() {
		$("#srch_step").change(function() {
			var grpCd = $("#srch_step").val();
			if(grpCd == "C02502") {
				getCodeSelectByUpCd("frm", "srch_state", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=F&useman_mng_itm1=' + grpCd +'&combo_type=T&combo_del_yn=N&combo_selected=');
			} else if(grpCd == "C02503") {
				getCodeSelectByUpCd("frm", "srch_state", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=F&useman_mng_itm2=' + grpCd +'&combo_type=T&combo_del_yn=N&combo_selected=');
			} else {
				getCodeSelectByUpCd("frm", "srch_state", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=F&combo_grp_cd=' + grpCd +'&combo_type=T&combo_del_yn=N&combo_selected=');
			}
		});
		
		$("#srch_review_title").keydown(function(event) {
			if(event.keyCode == "13") {
				searchAction('');
			}
		});

		$("#srch_reqman_nm").keydown(function(event) {
			if(event.keyCode == "13") {
				searchAction('');
			}
		});

		$("#srch_respman_nm").keydown(function(event) {
			if(event.keyCode == "13") {
				searchAction('');
			}
		});

		$("#srch_cnsdman_nm").keydown(function(event) {
			if(event.keyCode == "13") {
				searchAction('');
			}
		});

	});
	
	/**
	* 메시지 처리
	*/
	function showMessage(msg) {
		if( msg != "" && msg != null && msg.length > 1 ) {
			alert(msg);
		}
	}
	
	/** 계약정보 레이어 호출 **/
	function listCntrt(id, reqId, display){
		var frm = document.frm;
		$('#cntrtLayer').html("");
		$("#Layer1").attr('style', 'display:none;');
		
//		alert(display);
		if("show" == display){
			frm.cnsdreq_id.value = reqId;
			var Obj = getBounds(document.getElementById(id));
			
			var left = Obj.right+2;
			var top = Obj.top;
			//alert(width+' '+left+' '+top);
			var options = {
					url: "<c:url value='/clm/manage/contract.do?method=getCntrtHTML' />",
					type: "post",
					success: function(responseText,returnMessage) {
						$('#cntrtLayer').html("");
						$('#cntrtLayer').html(responseText);
						//alert(responseText);
					}
			};
			$("#frm").ajaxSubmit(options);		

			$("#cntrtLayer").attr('style', 'position:absolute; left:'+left+'px; top:'+top+'px; z-index:1; display:block;');
		}else{
			$("#cntrtLayer").attr('style', 'display:none;');
		}
	}
	
	/** 현재 위치 구하기 **/
	function getBounds(obj) {
		var ret = new Object();
		if (document.all) {
			var rect = obj.getBoundingClientRect();
			ret.left = rect.left + (document.documentElement.scrollLeft || document.body.scrollLeft);
			ret.right = rect.right;
			ret.top = rect.top + (document.documentElement.scrollTop || document.body.scrollTop);
		} else {
			var box = document.getBoxObjectFor(obj);
			ret.left = box.x;
			ret.top = box.y;
			ret.width = box.width;
			ret.height = box.height;
		}
		return ret;
	}	
	
	/**
	* 조회버튼 function
	*/ 
	function searchAction(gbn){
		var frm = document.frm;

		if(gbn != ""){
			frm.list_mode.value = gbn;
			$(".srch").val("");
		}
		
		//상대방명 없을 경우 코드값 초기화
		if($("#srch_oppnt_nm").val() == ""){
			$("#srch_oppnt_cd").val("");
		}

		//부서명 없을 경우 코드값 초기화
		if($("#srch_resp_dept_nm").val() == ""){
			$("#srch_resp_dept").val("");
		}

	<%if("cnsdreq".equals(command.getList_mode())){%>
		var startDt = frm.srch_start_reqday.value;
		var endDt = frm.srch_end_reqday.value;

		if((startDt != "" && endDt != "") && (startDt > endDt)){
	  		alert("<spring:message code='clm.msg.alert.board.srchAnnceDt' />");
	  		return;
	  	}
	<%}%>
	
	    if(gbn != "Detail"){
		    // 상세 검색 후 일반 검색 일 경우 해당 내용의 클리어 필요
		    $('#lSrch_Ltype_cd').val(""); 
			$('#lSrch_Mtype_cd').val(""); 
			$('#lSrch_Stype_cd').val("");
			$('#sSrch_Ltype_cd').val(""); 
			$('#sSrch_Mtype_cd').val(""); 
			$('#sSrch_Stype_cd').val("");
			$('#cntrt_trgt_det2').val(""); 
			$('#sPayment_gbn').val(""); 
			$('#bfhdcstn_apbtman_nm').val(""); 
			$('#sMn_cnsd_dept').val(""); 
			$('#str_org_acptday').val(""); 
			$('#end_org_acptday').val(""); 
			$('#auto_rnew_yn').val("");
			$('#sSeal_mthd').val("");
			$('#signman_nm').val(""); 
			$('#sLoac').val(""); 
			$('#sDesp_resolt_mthd').val(""); 
	    }
		
		viewHiddenProgress(true) ;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/manage/myApproval.do' />";
	    frm.method.value = "listStdContract";
	    frm.curPage.value = "1";
	    frm.submit();
	}
	
	/**
	* 조회버튼 function
	*/ 
	function excelDownLoad(){
		var frm = document.frm;

		//$(".srch").val("");
		
		//상대방명 없을 경우 코드값 초기화
		if($("#srch_oppnt_nm").val() == ""){
			$("#srch_oppnt_cd").val("");
		}

		//부서명 없을 경우 코드값 초기화
		if($("#srch_resp_dept_nm").val() == ""){
			$("#srch_resp_dept").val("");
		}

	<%if("cnsdreq".equals(command.getList_mode())){%>
		var startDt = frm.srch_start_reqday.value;
		var endDt = frm.srch_end_reqday.value;

		if((startDt != "" && endDt != "") && (startDt > endDt)){
	  		alert("<spring:message code='clm.msg.alert.board.srchAnnceDt' />");
	  		return;
	  	}
	<%}%>
	
	    // 상세 검색 후 일반 검색 일 경우 해당 내용의 클리어 필요
	    $('#lSrch_Ltype_cd').val(""); 
		$('#lSrch_Mtype_cd').val(""); 
		$('#lSrch_Stype_cd').val("");
		$('#sSrch_Ltype_cd').val(""); 
		$('#sSrch_Mtype_cd').val(""); 
		$('#sSrch_Stype_cd').val("");
		$('#cntrt_trgt_det2').val(""); 
		$('#sPayment_gbn').val(""); 
		$('#bfhdcstn_apbtman_nm').val(""); 
		$('#sMn_cnsd_dept').val(""); 
		$('#str_org_acptday').val(""); 
		$('#end_org_acptday').val(""); 
		$('#auto_rnew_yn').val("");
		$('#sSeal_mthd').val("");
		$('#signman_nm').val(""); 
		$('#sLoac').val(""); 
		$('#sDesp_resolt_mthd').val(""); 
		
		//viewHiddenProgress(true) ;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/manage/contract.do' />";
	    frm.method.value = "listManageExcel";
	    frm.curPage.value = "1";
	    frm.submit();
	}
	
	
	/**
	* 조회 페이지 이동
	*/	
	function goPage(pgNum){
		var frm = document.frm;
		viewHiddenProgress(true) ;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/manage/myApproval.do' />";
	    frm.method.value = "listStdContract";
		frm.curPage.value = pgNum;
		
		frm.submit();
	}
	
	/**   
	*검토 의뢰 작성하기 
	*/
	function goInsertForm() {		
		var frm = document.frm ;	

		frm.target = "_self" ;
		frm.action = "<c:url value='/clm/manage/consideration.do' />" ;   
		frm.method.value = "forwardInsertConsideration" ;
		frm.submit() ;
	}
	
	/**
	* 관리페이지 이동
	*/	
	function detailAction(id, prcs, depth_status){
		
		var frm = document.frm;

		var formId = "frm";
		var target = "_top";
		var menuId2 = "";
		var initUrl = "";
		var myAction = "";
		var myMethod = "";
		
		frm.ismycontract.value = "Y";

		viewHiddenProgress(true);
		frm.cnsdreq_id.value = id;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/manage/myApproval.do' />";
		frm.method.value ="detailStdContract";
		frm.submit();
		
	}

	/**
	*	계약상대방 팝업
	*/
	function popOppnt(){
		 var frm = document.frm;
		 
		PopUpWindowOpen('', 900, 600, true);
		frm.target = "PopUpWindow";
		frm.action = "/clm/draft/customerTest.do";
		frm.method.value = "listCustomerTest";
		frm.srch_customer.value = frm.srch_oppnt_nm.value;
		frm.customer_gb.value   = "O";
		frm.submit();
	}
	
	/**
	* 거래상대방 객체 리턴
	*/
	function returnCustomer(obj){
		$('#srch_oppnt_cd').val(obj.customer_cd);
		$('#srch_oppnt_nm').val(obj.customer_nm1);
	}	
	/**
	*	담당부서 팝업
	*/
	function popDept(){
		 var frm = document.frm;
		 
		 PopUpWindowOpen('', 640, 640, true);
		 frm.target = "PopUpWindow";
		 frm.action = "<c:url value='/common/clmsCom.do' />";
		 frm.method.value = "listDeptTree";
		 frm.dept_nm.value = frm.srch_resp_dept_nm.value;
		 frm.submit();
	}
	
	function setDeptInfo(retDeptCd, retDeptNm){
		$('#srch_resp_dept').val(retDeptCd);
		$('#srch_resp_dept_nm').val(retDeptNm);	
	}
	
	/**
	* 상세 검색 POPUP
	*/
	function popDetailSrh(){
    	
		PopUpWindowOpen('', 750, 680, true);
		frm.target = "PopUpWindow";
		frm.action = "<c:url value='/clm/manage/myContract.do' />";
		frm.method.value = "detailTypePopup";
		frm.submit();
    	
    }
	
	/**
	* 상세 검색 팝업에서 리턴 받기
	*/
	function returnDetailSrh(obj){
		
		//alert("ptype = "+obj.lSrch_LType_cd);
		//alert("p_lseal_mthd = "+obj.lseal_mthd);
		$('#srch_lSrch_Ltype_cd').val(obj.srch_lSrch_LType_cd); 
		$('#srch_lSrch_Mtype_cd').val(obj.srch_lSrch_MType_cd); 
		$('#srch_lSrch_Stype_cd').val(obj.srch_lSrch_SType_cd);
		$('#srch_sSrch_Ltype_cd').val(obj.srch_lSrch_LType_cd); 
		$('#srch_sSrch_Mtype_cd').val(obj.srch_lSrch_MType_cd); 
		$('#srch_sSrch_Stype_cd').val(obj.srch_lSrch_SType_cd);
		$('#srch_cntrt_trgt_det2').val(obj.srch_cntrt_trgt_det2); 
		$('#srch_sPayment_gbn').val(obj.srch_lPayment_gbn); 
		$('#srch_bfhdcstn_apbtman_nm').val(obj.srch_bfhdcstn_apbtman_nm); 
		$('#srch_sMn_cnsd_dept').val(obj.srch_mn_cnsd_dept); 
		//$('#srch_str_org_acptday').val(obj.srch_str_org_acptday); 
		//$('#srch_end_org_acptday').val(obj.srch_end_org_acptday); 
		$('#srch_auto_rnew_yn').val(obj.srch_auto_rnew_yn);
		$('#srch_sSeal_mthd').val(obj.srch_lseal_mthd);
		$('#srch_signman_nm').val(obj.srch_signman_nm); 
		$('#srch_sLoac').val(obj.srch_lLoac); 
		$('#srch_sDesp_resolt_mthd').val(obj.srch_lDesp_resolt_mthd); 
		
		searchAction('Detail');
	}
	
	function goOldConsiderationInsert(){
		var frm = document.frm;
		viewHiddenProgress(true) ;
		frm.target = "_self";
		frm.action = "<c:url value='/las/contractmanager/consideration.do' />";
		frm.method.value = "forwardInsertyOldConsideration";
		frm.submit();
	}

	/**
	* 엑셀 다운로드 function
	*/ 
	function excelDownLoad(){
		var frm = document.frm;
		
		// 300row가 넘어가는 시점에서 날짜를 체크 하고 있음.
		if(<%=pageUtil.getTotalRow()%>>300){
			if(frm.srch_start_reqday.value == ''){
			    alert("<spring:message code='clm.page.msg.manage.announce128' />");
			    return;
		    }
		    if(frm.srch_end_reqday.value == ''){
		    	alert("<spring:message code='clm.page.msg.manage.announce129' />");
			    return;
		    }
		    //날짜계산
		    var sd = frm.srch_start_reqday.value; //start Day
	        var ed = frm.srch_end_reqday.value; //end Day
		    sd = sd.split("-");
		    ed = ed.split("-");
		    var st = new Date(sd[0],sd[1]-1,sd[2]).getTime();
		    var et = new Date(ed[0],ed[1]-1,ed[2]).getTime();
		    var fromto = (et-st)/(1000*60*60*24)+1;

		    if(fromto > 90){
		    	alert("<spring:message code='clm.page.msg.manage.announce180max90' />");
			    return;			
		    }
		}

		//$(".srch").val("");
		
		//상대방명 없을 경우 코드값 초기화
		if($("#srch_oppnt_nm").val() == ""){
			$("#srch_oppnt_cd").val("");
		}

		//부서명 없을 경우 코드값 초기화
		if($("#srch_resp_dept_nm").val() == ""){
			$("#srch_resp_dept").val("");
		}

	<%if("cnsdreq".equals(command.getList_mode())){%>
		var startDt = frm.srch_start_reqday.value;
		var endDt = frm.srch_end_reqday.value;

		if((startDt != "" && endDt != "") && (startDt > endDt)){
	  		alert("<spring:message code='clm.msg.alert.board.srchAnnceDt' />");
	  		return;
	  	}
	<%}%>
	
	    // 상세 검색 후 일반 검색 일 경우 해당 내용의 클리어 필요
	    $('#lSrch_Ltype_cd').val(""); 
		$('#lSrch_Mtype_cd').val(""); 
		$('#lSrch_Stype_cd').val("");
		$('#sSrch_Ltype_cd').val(""); 
		$('#sSrch_Mtype_cd').val(""); 
		$('#sSrch_Stype_cd').val("");
		$('#cntrt_trgt_det2').val(""); 
		$('#sPayment_gbn').val(""); 
		$('#bfhdcstn_apbtman_nm').val(""); 
		$('#sMn_cnsd_dept').val(""); 
		$('#str_org_acptday').val(""); 
		$('#end_org_acptday').val(""); 
		$('#auto_rnew_yn').val("");
		$('#sSeal_mthd').val("");
		$('#signman_nm').val(""); 
		$('#sLoac').val(""); 
		$('#sDesp_resolt_mthd').val(""); 
		
		//viewHiddenProgress(true) ;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/manage/contract.do' />";
	    frm.method.value = "listManageExcel";
	    frm.curPage.value = "1";
	    frm.submit();
	}
	
</script>
</head>
<body onload='showMessage("<c:out value='${returnMessage}'/>");'>

<div id="cntrtLayer" style="display:none;">
</div>

<div id="wrap">
	<!-- container -->
	<div id="container">

		<!-- Location -->
		<div class="location">
			<img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0"/><c:out value='${menuNavi}' escapeXml="false"/>
		</div>
		<!-- //Location -->

		<!-- Title -->
		<div class="title">
			<h3><spring:message code="clm.page.msg.manage.stdContApprList" /></h3>
		</div>
		<!-- //title -->
		
		<!-- content -->
		<div id="content">
		<div id="content_in">
		<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id='frm' method="post" autocomplete="off"> 
	<!-- search form -->
	<input type="hidden" name="method"   value="" />
	<input type="hidden" name="menu_id"   value="<c:out value='${command.menu_id}'/>" />

	<input type="hidden" name="list_mode" value="<c:out value='${command.list_mode}'/>"/>
	<input type="hidden" id="srch_resp_dept" name="srch_resp_dept" value="<c:out value='${command.srch_resp_dept}'/>" class="srch"/>
	<input type="hidden" id="srch_oppnt_cd" name="srch_oppnt_cd" value="<c:out value='${command.srch_oppnt_cd}'/>" class="srch"/>

	<!-- PopUp Param -->
	<input type="hidden" name="customer_gb" value=""/>
	<input type="hidden" name="dept_nm" value=""/>
	<input type="hidden" name="page_gbn" value="one"/>
	<input type="hidden" name="srch_customer" value=""/>
				
	<!-- Detail Page Param -->
	<input type="hidden" name="cnsdreq_id" value="" />
	<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>" />
	<input type="hidden" name="status_mode" value="<c:out value='${command.status_mode}'/>" />

	<!-- Detail View -->
	<input type="hidden" name="targetMenuId" />
	<input type="hidden" name="target_menu_id" />
	<input type="hidden" name="selected_menu_id" />
	<input type="hidden" name="selected_menu_nm" />
	<input type="hidden" name="selected_menu_detail_id" />
	<input type="hidden" name="set_init_url" />
	
	<!-- Detail hidden value -->
	<input type="hidden" name="srch_lsrch_Ltype_cd"      id="srch_lsrch_Ltype_cd"      value="<c:out value='${command.srch_lsrch_Ltype_cd}'/>" />        
	<input type="hidden" name="srch_lsrch_Mtype_cd"      id="srch_lsrch_Mtype_cd"      value="<c:out value='${command.srch_lsrch_Mtype_cd}'/>" />
	<input type="hidden" name="srch_lsrch_Stype_cd"      id="srch_lsrch_Stype_cd"      value="<c:out value='${command.srch_lsrch_Stype_cd}'/>" />
	<input type="hidden" name="srch_sSrch_Ltype_cd"      id="srch_sSrch_Ltype_cd"      value="<c:out value='${command.srch_sSrch_Ltype_cd}'/>" />        
	<input type="hidden" name="srch_sSrch_Mtype_cd"      id="srch_sSrch_Mtype_cd"      value="<c:out value='${command.srch_sSrch_Mtype_cd}'/>" />
	<input type="hidden" name="srch_sSrch_Stype_cd"      id="srch_sSrch_Stype_cd"      value="<c:out value='${command.srch_sSrch_Stype_cd}'/>" />
	<input type="hidden" name="srch_cntrt_trgt_det2"     id="srch_cntrt_trgt_det2"     value="<c:out value='${command.srch_cntrt_trgt_det2}'/>" />
	<input type="hidden" name="srch_sPayment_gbn"        id="srch_sPayment_gbn"        value="<c:out value='${command.srch_sPayment_gbn}'/>" />
	<input type="hidden" name="srch_bfhdcstn_apbtman_nm" id="srch_bfhdcstn_apbtman_nm" value="<c:out value='${command.srch_bfhdcstn_apbtman_nm}'/>" />
	<input type="hidden" name="srch_sMn_cnsd_dept"       id="srch_sMn_cnsd_dept"       value="<c:out value='${command.srch_sMn_cnsd_dept}'/>" />
	<!-- 
    <input type="text" name="srch_str_org_acptday"     id="srch_str_org_acptday"     value="<c:out value='${command.srch_str_org_acptday}'/>">
    <input type="text" name="srch_end_org_acptday"     id="srch_end_org_acptday"     value="<c:out value='${command.srch_end_org_acptday}'/>">
     -->
	<input type="hidden" name="srch_auto_rnew_yn"        id="srch_auto_rnew_yn"        value="<c:out value='${command.srch_auto_rnew_yn}'/>" />
	<input type="hidden" name="srch_sSeal_mthd"          id="srch_sSeal_mthd"          value="<c:out value='${command.srch_sSeal_mthd}'/>" />
	<input type="hidden" name="srch_signman_nm"          id="srch_signman_nm"          value="<c:out value='${command.srch_signman_nm}'/>" />
	<input type="hidden" name="srch_sLoac"               id="srch_sLoac"               value="<c:out value='${command.srch_sLoac}'/>" />
	<input type="hidden" name="srch_sDesp_resolt_mthd"   id="srch_sDesp_resolt_mthd"   value="<c:out value='${command.srch_sDesp_resolt_mthd}'/>" />
	
	<input type="hidden" name="ismycontract"	id="ismycontract" value="" />
	
<!-- // **************************** Form Setting **************************** -->
		
			<!-- search-->		    
			<div class="search_box">
				<div class="search_box_inner">
					<table class="search_tb">
						<colgroup>
							<col/>
							<col width="75px"/>
						</colgroup>
						<tr>
							<td>
							<!-- 의뢰 기준 조회일 경우 -->
							<%if("cnsdreq".equals(command.getList_mode())){%>
								<table class="search_form" style='min-width:700px;'>
									<colgroup>
                                        <col width="10%"/>
                                        <col width="23%"/>
                                        <col width="10%"/>
                                        <col width="23%"/>
                                        <col width="10%"/>
                                        <col width="24%"/>
                                    </colgroup>
									<tr>
										<th><spring:message code="clm.page.field.manageCommon.srchReqTitle" /></th>
										<td>
											<input class="text" style="width:200px;" type="text" name="srch_review_title"  id="srch_review_title" value="<c:out value='${command.srch_review_title}'/>" />
										</td>
										<th><spring:message code="clm.page.field.manageCommon.srchCntrtNm" /></th>
                                        <td>
                                            <input class="text" style="width:200px;" type="text" name="srch_cntrt_nm"  id="srch_cntrt_nm" value="<c:out value='${command.srch_cntrt_nm}'/>"/>
                                        </td>
                                        <th><spring:message code="clm.page.field.manageCommon.srchCntrtId"/></th>
                                        <td>
                                            <input class="text" style="width:200px;" type="text" name="srch_cntrt_no"  id="srch_cntrt_no" value="<c:out value='${command.srch_cntrt_no}'/>"/>
                                        </td>
									</tr>
									<tr>
									   <th><spring:message code="clm.page.field.manageCommon.srchReqmanNm" /></th>
                                        <td>
                                            <input class="text" style="width:200px;" type="text" name="srch_reqman_nm" id="srch_reqman_nm" value="<c:out value='${command.srch_reqman_nm}'/>"/>
                                        </td>
                                        <%--
                                        <th><spring:message code="clm.page.field.manageCommon.srchRespmanNm" /></th>
                                        <td>
                                            <input class="text" style="width:200px;" type="text" name="srch_respman_nm" id="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}'/>" />
                                        </td>
                                         --%>
                                        
                                        <th><spring:message code="clm.page.field.manageCommon.srchCnsdmanNm" /></th>
                                        <td>
                                            <input class="text" style="width:200px;" type="text" name="srch_cnsdman_nm" id="srch_cnsdman_nm" value="<c:out value='${command.srch_cnsdman_nm}'/>" />
                                        </td>
                                         <th><spring:message code="clm.page.field.manageCommon.srchCnclsnpurpsBigclsfcn" /></th>
                                        <td>
                                            <select class="srch" name="srch_cnclsnpurps_bigclsfcn" id="srch_cnclsnpurps_bigclsfcn" style="width:200px;">
                                            </select>   
                                        </td>                                        
									</tr>
									
									<%--
									<tr>
									   <th><spring:message code="clm.page.field.manageCommon.srchRespDept" /></th>
                                        <td>
                                            <input class="text" style="width:183px;" type="text" name="srch_resp_dept_nm" id="srch_resp_dept_nm" value="<c:out value='${command.srch_resp_dept_nm}'/>" onkeypress="if(event.keyCode == 13){popDept();return false;}"/><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search" onclick="popDept()" style="cursor:pointer;margin-left:-1px" />
                                        </td>
                                        <th><spring:message code="clm.page.field.manageCommon.srchBizclsfcn" /></th>
                                        <td>
                                            <select class="srch" name="srch_biz_clsfcn" id="srch_biz_clsfcn" style="width:200px;">
                                            </select>   
                                        </td>
                                        <th><spring:message code="clm.page.field.manageCommon.srchCnclsnpurpsBigclsfcn" /></th>
                                        <td>
                                            <select class="srch" name="srch_cnclsnpurps_bigclsfcn" id="srch_cnclsnpurps_bigclsfcn" style="width:200px;">
                                            </select>   
                                        </td>
									</tr>
									 --%>
									 
									<tr>
									   <th><spring:message code="clm.page.field.manageCommon.srchReqDay" /></th>
                                        <td>
                                            <input type="text" name="srch_start_reqday" id="srch_start_reqday" value="<c:out value='${command.srch_start_reqday}'/>"  class="text_calendar" style='width:73px;'/>
                                            ~
                                            <input type="text" name="srch_end_reqday" id="srch_end_reqday" value="<c:out value='${command.srch_end_reqday}'/>" class="text_calendar" style='width:73px;'/>
                                        </td>
                                       
                                       <th><spring:message code="clm.page.field.manageCommon.srchCnlsnday" /></th>
                                        <td>
                                            <input type="text" name="srch_start_cnlsnday" id="srch_start_cnlsnday" value="<c:out value='${command.srch_start_cnlsnday}'/>"  class="text_calendar" style='width:73px;'/>
                                            ~
                                            <input type="text" name="srch_end_cnlsnday" id="srch_end_cnlsnday" value="<c:out value='${command.srch_end_cnlsnday}'/>" class="text_calendar" style='width:73px;'/>
                                        </td>
                                        
                                        <th><spring:message code="clm.page.field.contract.conclusion.detail.conclusionorgacceptday" /></th>
                                        <td colspan="3">
                                            <input type="text" name="srch_str_org_acptday" id="srch_str_org_acptday" value="<c:out value='${command.srch_str_org_acptday}'/>"  class="text_calendar" style='width:73px;'/>
                                            ~
                                            <input type="text" name="srch_end_org_acptday" id="srch_end_org_acptday" value="<c:out value='${command.srch_end_org_acptday}'/>" class="text_calendar" style='width:73px;'/>
                                            
                                        </td>
                                     </tr>
                                     <tr>   
									   <th><spring:message code="clm.page.field.manageCommon.srchStep" /> / <spring:message code="clm.page.field.manageCommon.srchStatus" /></th>
                                        <td>
                                            <select class="srch" name="srch_step" id="srch_step" value="<c:out value='${command.srch_step}'/>" style='width:95px'>
                                            </select>
                                            /           
                                            <select class="srch" name="srch_state"  id="srch_state" style='width:95px'>
                                            </select>
                                        </td>       
										<th><spring:message code="clm.page.field.manageCommon.srchOppntCd" /></th>
										<td>
											<input class="text" style="width:183px;" type="text" name="srch_oppnt_nm" id="srch_oppnt_nm" value="<c:out value='${command.srch_oppnt_nm}'/>" onKeypress="if(event.keyCode == 13){popOppnt();return false;}"/><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search"  onclick="popOppnt()" style="cursor:pointer; margin-left:-1px"/>
										</td>										
									</tr>
								</table>
							
							<!-- 계약 기준 조회일 경우 -->
							<%}else{%>
								<table class="search_form">
									<colgroup>
										<col width="12%"/>
										<col width="29%"/>
										<col width="10%"/>
										<col width="29%"/>
										<col width="10%"/>
										<col width="10%"/>
									</colgroup>
									<tr>
										<th><spring:message code="clm.page.field.manageCommon.srchCntrtNm" /></th>
										<td>
											<input class="srch" type="text" name="srch_review_title"  id="srch_review_title" value="<c:out value='${command.srch_review_title}'/>" style="width:100%"/>
										</td>
										<th><spring:message code="clm.page.field.manageCommon.srchRespDept" /></th>
										<td>
											<input type="text" name="srch_resp_dept_nm" id="srch_resp_dept_nm" value="<c:out value='${command.srch_resp_dept_nm}'/>" style="width:130px" class="text_search srch" onKeypress="if(event.keyCode == 13){popDept();return false;}"/><a href="javascript:popDept();"><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search" />
										</td>
										<th><spring:message code="clm.page.field.manageCommon.srchRespmanNm" /></th>
										<td>
											<input class="srch" type="text" name="srch_respman_nm" id="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}'/>" style="width:80px" />
										</td>
									</tr>
									<tr>
										<th><spring:message code="clm.page.field.manageCommon.srchBizclsfcn" /></th>
										<td>
											<select class="srch" name="srch_biz_clsfcn" id="srch_biz_clsfcn">
											</select>	
										</td>										
										<th><spring:message code="clm.page.field.manageCommon.srchCnclsnpurpsBigclsfcn" /></th>
										<td>
											<select class="srch" name="srch_cnclsnpurps_bigclsfcn" id="srch_cnclsnpurps_bigclsfcn">
											</select>	
										</td>										
										<th><spring:message code="clm.page.field.manageCommon.srchCnsdmanNm" /></th>
										<td>
											<input type="text" name="srch_cnsdman_nm" id="srch_cnsdman_nm" value="<c:out value='${command.srch_cnsdman_nm}'/>" style="width:80px" />
										</td>
									</tr>
									<tr>
										<th><spring:message code="clm.page.field.manageCommon.srchOppntCd" /></th>
										<td>
											<input type="text" name="srch_oppnt_nm" id="srch_oppnt_nm" value="<c:out value='${command.srch_oppnt_nm}'/>" style="width:130px" class="text_search srch" onkeypress="if(event.keyCode == 13){popOppnt();return false;}"/><a href="javascript:popOppnt();"><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search" /></a>
										</td>										
										<th><spring:message code="clm.page.field.manageCommon.srchStep" /> / <spring:message code="clm.page.field.manageCommon.srchStatus" /></th>
										<td>
											<select class="srch" name="srch_step" id="srch_step">
											</select>
											/	
											<select class="srch" name="srch_state"	id="srch_state">
											</select>
										</td>									
										<th><spring:message code="clm.page.msg.common.connSys" /></th>
										<td>
											<select class="srch" name="srch_if_sys_cd"	id="srch_if_sys_cd">
											</select>
										</td>										
									</tr>
									<tr>
										<th><spring:message code="clm.page.field.manageCommon.srchReqDay" /></th>
										<td><nobr>
											<input type="text" name="srch_start_reqday" id="srch_start_reqday" value="<c:out value='${command.srch_start_reqday}'/>"  class="text_calendar02 srch"/>
											~
	  										<input type="text" name="srch_end_reqday" id="srch_end_reqday" value="<c:out value='${command.srch_end_reqday}'/>" class="text_calendar02 srch"/>
											</nobr>
										</td>
										<th><spring:message code="clm.page.field.manageCommon.srchCnlsnday" /></th>
										<td colspan="3">
											<input type="text" name="srch_start_cnlsnday" id="srch_start_cnlsnday" value="<c:out value='${command.srch_start_cnlsnday}'/>"  class="text_calendar02 srch"/>
											~
	  										<input type="text" name="srch_end_cnlsnday" id="srch_end_cnlsnday" value="<c:out value='${command.srch_end_cnlsnday}'/>" class="text_calendar02 srch"/>
										</td>
									</tr>
								</table>
							<%}%>
							</td>
							<td class="tC" valign="bottom">
								<a href="javascript:searchAction('');"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="clm.page.msg.common.search" htmlEscape="true" />"/></a>
								<%-- <p class="mt5"><a href="javascript:popDetailSrh();"><img src="<%=IMAGE%>/btn/btn_search2.gif" alt="<spring:message code="clm.page.msg.manage.detailSearch" htmlEscape="true" />"/></a></p> --%>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!--//search-->
			<!-- button -->
			<div class="t_titBtn">
				<div class="btn_wrap_t">
					<span class="btn_all_w"><span class="excel_down"></span><a href="javascript:excelDownLoad();"> <spring:message code='las.page.button.excelDownload' /></a></span><!-- 엑셀 다운로드 -->
				</div>
		  	</div>
		  	<!-- //button -->
			<!-- list -->
			<!-- 의뢰기준일 경우 -->
		
		   
			<!-- table class="list_basic mt20" -->
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic">

				<colgroup>
					<col />
					<col width="7%" />
					<col width="93px" />
					<col width="9%" />
					<col width="9%" />
					<col width="7%" />
					<col width="9%" />
					<col width="70px" />
					<col width="95px" />
			  	</colgroup>

			  	<thead>
					<tr>
						<th><spring:message code="clm.page.field.manageCommon.reqTitle" /><br>(<spring:message code="clm.page.field.manageCommon.srchCntrtNm" />)</br></th>
						<th title="<spring:message code="clm.page.msg.common.contractRvRqster" htmlEscape="true" />"><spring:message code="clm.page.field.manageCommon.reqmanNm" /><br/><spring:message code="clm.page.field.manageCommon.respmanNm" /></th>
						<th>						  
						    <spring:message code="las.page.field.contractmanager.consideration.first"/><br/><spring:message code="clm.page.field.manageCommon.reqDay" /><br/>
						    (<spring:message code="clm.page.field.contract.conclusion.detail.conclusionorgacceptday" />)						 
						</th>
						<th><spring:message code="clm.page.field.manageCommon.respDept" /></th>
						<th><spring:message code="clm.page.field.manageCommon.oppntCd" /></th>
						<th><spring:message code="clm.page.field.manageCommon.cnsdmanNm" /></th>
						<th><spring:message code="clm.page.field.manageCommon.srchCntrtId" /></th>
						<th><spring:message code="clm.page.field.manageCommon.step" /></th>
						<th><spring:message code="clm.page.field.manageCommon.status" /></th>
					</tr>
				</thead>
			 	<tbody>
			  <%
				if(pageUtil.getTotalRow() > 0) {
					for(int idx=0; idx<resultList.size(); idx++) {
						ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
				%>			
				    <tr  <%if(idx == resultList.size()-1){%>class="end"<%}%> onMouseOut="this.className='<%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onMouseOver="this.className='on <%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onClick="javascript:detailAction('<%=lom.get("cnsdreq_id") %>', '<%=lom.get("prcs_depth")%>','<%=lom.get("depth_status")%>');">
				      <td class="tL txtover overflow" style="line-height:150%" title="<%=StringUtil.convertHtmlTochars((String)lom.get("req_title")) %>&#13(<%=StringUtil.convertHtmlTochars((String)lom.get("cntrt_nm")) %>)">
				        <span style='color:#485B91'><%=StringUtil.convertHtmlTochars((String)lom.get("req_title")) %></span><br/>
				        <span style='color:#A9B4BC'>(<%=StringUtil.convertHtmlTochars((String)lom.get("cntrt_nm")) %>)</span>
				      </td>
				      <td class="txtover tC" title="<%=lom.get("reqman_nm") %>">
				        <%=lom.get("reqman_nm") %>
				        <%=lom.get("cntrt_respman_nm")!=null && !lom.get("cntrt_respman_nm").equals("")? "<br>" + lom.get("cntrt_respman_nm") : "" %>
				      </td>
				      <td class="txtover tC" style='text-align:center'>
				        
				            <%=lom.get("req_dt") %><br/>
				            <%if(lom.get("org_acptday")!=null && !lom.get("org_acptday").equals("")) { %>
				            <span style='color:#A9B4BC;'>(<%=lom.get("org_acptday") %>)</span>
				            <%  } %>
				        </td>
				      <td class="txtover tC" title="<%=lom.get("cntrt_resp_dept_nm") %>"><%=lom.get("cntrt_resp_dept_nm") %></td>
				      <td class="txtover tC" title="<%=lom.get("cntrt_oppnt_nm") %>"><%=lom.get("cntrt_oppnt_nm") %></td>
				      <td class="txtover tC" title="<%=StringUtil.replace((String)lom.get("cnsdmans"),"<br>","&#13;") %>"><%=lom.get("cnsdmans") %></td>
				      <td class="txtover tC" title="<%=lom.get("cntrt_no") %>"><%=lom.get("cntrt_no") %></td>
				      <td class="txtover tC" title="<%=lom.get("prcs_depth_nm") %>"><%=lom.get("prcs_depth_nm") %></td>
				      <td class="txtover tC" title="<%=lom.get("depth_status_nm") %>"><%=lom.get("depth_status_nm") %></td>
			        </tr>
		      <%	}
				}else{		  
 			  %>
					<tr>
					  <td colspan="9" class="tC"><spring:message code="secfw.msg.succ.noResult" /></td>
					</tr>
			  <%}%>			
			  </tbody>
			</table>
			
			<!-- //list -->

			<div class="t_titBtn">	
				<!-- total data -->
				<div class="total_num">
					Total : <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%>
				</div>
				<!-- //total data -->
				
				<!-- pagination  -->
				<div class="pagination">
				<%=pageUtil.getPageNavi(pageUtil, "") %>
				</div>
				<!-- //pagination -->
			</div>
			</form:form>
      </div>
		</div>
		<!-- //content -->
	</div>
	<!-- //container -->
</div>
<!-- footer -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- //footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</body>
</html>

