<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.manage.dto.ManageForm" %>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%-- 
/**
 * 파  일  명 : ManageConclusion_l.jsp
 * 프로그램명 : 계약등록목록
 * 설      명 : 
 * 작  성  자 : 신남원
 * 작  성  일 : 2011.09.29
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	List resultList = (List)request.getAttribute("resultList");
	ManageForm command = (ManageForm)request.getAttribute("command");
%>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<LINK href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet">

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>

<script language="javascript">

	var cpFlag = false;
<%
	List roleList = (List)session.getAttribute("secfw.session.role");

	if(roleList != null && roleList.size() > 0) {
		for(int ri = 0; ri < roleList.size(); ri++) {
			HashMap roleHm = (HashMap)roleList.get(ri);
			if("RD01".equals((String)roleHm.get("role_cd"))) {
%>
				cpFlag = true;
<%
				break;
			}
		}
	}
%>
	
	$(document).ready(function(){
		//Step 세팅            search_state
		getCodeSelectByUpCd("frm", "srch_state", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=F&useman_mng_itm2=' + '<c:out value='${command.srch_step}'/>' +'&combo_type=T&combo_del_yn=N&combo_selected='+'<c:out value='${command.srch_state}'/>');
		//비즈니스 단계 
		getCodeSelectByUpCd("frm", "srch_biz_clsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=T01&combo_abbr=F&combo_type=S&combo_del_yn=N&combo_selected="+"<c:out value='${command.srch_biz_clsfcn}'/>");   
		//계약유형1
		//getCodeSelectByUpCd("frm", "srch_cnclsnpurps_bigclsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=T03&combo_abbr=F&combo_type=S&combo_del_yn=N&combo_selected="+"<c:out value='${command.srch_cnclsnpurps_bigclsfcn}'/>");   
		//연계시스템 세팅
		getCodeSelectByUpCd("frm", "srch_if_sys_cd", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=F&combo_grp_cd=C066&combo_type=T&combo_del_yn=N&combo_selected='+'<c:out value='${command.srch_if_sys_cd}'/>');
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
		$("#srch_review_title").keydown(function(event) {
			if(event.keyCode == "13") {
				searchAction('');
			}
		});
		$("#srch_cntrt_nm").keydown(function(event) {
			if(event.keyCode == "13") {
				searchAction('');
			}
		});
		$("#srch_cntrt_no").keydown(function(event) {
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
			}
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
	
		
		//날짜입력은 반드시 둘다 입력되어야한다(범위검색)2013.11
		if($("#srch_start_reqday").val() != "" && $("#srch_end_reqday").val() == ""){
			alert("<spring:message code='las.page.field.report.inputToDate' />");//검색종료일을 입력하세요
	  		return;
		}
		if($("#srch_start_reqday").val() == "" && $("#srch_end_reqday").val() != ""){
			alert("<spring:message code='las.page.field.report.inputFromDate' />");//검색시작일을 입력하세요
	  		return;
		}
		
		//날짜입력은 반드시 둘다 입력되어야한다(범위검색)2013.11
		if($("#srch_start_cnlsnday").val() != "" && $("#srch_end_cnlsnday").val() == ""){
			alert("<spring:message code='las.page.field.report.inputToDate' />");//검색종료일을 입력하세요
	  		return;
		}
		if($("#srch_start_cnlsnday").val() == "" && $("#srch_end_cnlsnday").val() != ""){
			alert("<spring:message code='las.page.field.report.inputFromDate' />");//검색시작일을 입력하세요
	  		return;
		}
		
		
		//날짜입력은 반드시 둘다 입력되어야한다(범위검색)2013.11
		if($("#srch_str_org_acptday").val() != "" && $("#srch_end_org_acptday").val() == ""){
			alert("<spring:message code='las.page.field.report.inputToDate' />");//검색종료일을 입력하세요
	  		return;
		}
		if($("#srch_str_org_acptday").val() == "" && $("#srch_end_org_acptday").val() != ""){
			alert("<spring:message code='las.page.field.report.inputFromDate' />");//검색시작일을 입력하세요
	  		return;
		}
		
	<%if("cnsdreq".equals(command.getList_mode())){%>
		var startDt = frm.srch_start_reqday.value;
		var endDt = frm.srch_end_reqday.value;
	
		if((startDt != "" && endDt != "") && (startDt > endDt)){
	  		alert("<spring:message code='clm.msg.alert.board.srchAnnceDt' />");
	  		return;
	  	}
	<%}%>
		
		viewHiddenProgress(true) ;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/manage/conclusion.do' />";
	    frm.method.value = "listManageConclusion";
	    frm.curPage.value = "1";
	    frm.submit();
	}
	
	
	/**
	* 조회버튼 function
	*/ 
	function excelDownLoad(){
		var frm = document.frm;
		
		/*
		if(frm.srch_start_reqday.value == ''){
			alert("의뢰일 시작을 선택해주세요.");
			return;
		}
		if(frm.srch_end_reqday.value == ''){
			alert("의뢰일 종료를 선택해주세요.");
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

		if(fromto > 60){
			alert('최대 60일 기간까지 다운 할 수 있습니다.');
			return;			
		}
		*/
	
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
		frm.action = "<c:url value='/clm/manage/conclusion.do' />";
		frm.method.value = "listManageConclusion";
		frm.curPage.value = pgNum;
		
		frm.submit();
	}
	
	/**
	* 관리페이지 이동
	*/	
	function detailAction(id, depthStatus){
		var frm = document.frm;
		viewHiddenProgress(true) ;
		frm.target = "_self";
		
		if(depthStatus == "C02642" && cpFlag) {
			frm.action = "<c:url value='/clm/manage/myApproval.do' />";
			frm.method.value = "detailMyApproval";
		} else {
			frm.action = "<c:url value='/clm/manage/conclusion.do' />";
			frm.method.value = "detailConclusionNew";
		}
		frm.cnsdreq_id.value = id;		
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
		 //PopUpWindowOpen('', 850, 440, true);
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
	
	//퀵가이드
	function ConclusionGuidePop(){
		var frm = document.frm;
		
		PopUpWindowOpen2('', "686", "560", true,"PopUpWindow2");
		frm.action = "/secfw/main.do";
		frm.method.value="forwardPage";
		frm.target = "PopUpWindow2";
		frm.forward_url.value="/step03<%=langCd.equals("ko")?"":"_"+langCd %>.html";
		frm.submit();
	}
</script>
</head>

<body onLoad='showMessage("<c:out value='${returnMessage}'/>");'>
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
	<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>">
	
	<input type="hidden" name="forward_url" value="" />
	
	<input type="hidden" name="status_mode" value="<c:out value='${command.srch_step}'/>"/>
	
<!-- // **************************** Form Setting **************************** -->
<div id="cntrtLayer" style="display:none;">
</div>
<div id="wrap">
	<!-- container -->
	<div id="container">

		<!-- Location -->
		<div class="location">
			<img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/>
		</div>
		<!-- //Location -->

		<!-- Title -->
		<div class="title">
			<h3><spring:message code="clm.page.title.manageConclusion.listTitle" /></h3>
		</div>
		<!-- //title -->
		
		<!-- content -->
		<div id="content">
			<!-- tab -->
		  	<div class="tab_box">
				<ul class="tab_basic ptz">
				<%if("cnsdreq".equals(command.getList_mode())){%>
			      <li class="on"><a><spring:message code="clm.page.field.manageCommon.srchListMode1" /></a></li>
			      <li><a href="javascript:searchAction('contract')"><spring:message code="clm.page.field.manageCommon.srchListMode2" /></a></li>
			     <%}else{%>
			      <li><a href="javascript:searchAction('cnsdreq')"><spring:message code="clm.page.field.manageCommon.srchListMode1" /></a></li>
			      <li class="on"><a><spring:message code="clm.page.field.manageCommon.srchListMode2" /></a></li>
			     <%}%>
			    </ul>
			    <span style="float:right;"><!-- <a href="javascript:ConclusionGuidePop();"> --><a href="javascript:open_window('win', '../../step03<%=langCd.equals("ko")?"":"_"+langCd %>.html', 0, 0, 696, 588, 0, 0, 0, 0, 0)"><img src="<%=IMAGE%>/btn/btn_quick.gif" alt="Quick Guide"/></a></span>
			</div>
			<!-- //tab -->
						
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
                                            <input class="text" style="width:80%" type="text" name="srch_review_title"  id="srch_review_title" value="<c:out value='${command.srch_review_title}'/>" />
                                        </td>
                                        <th><spring:message code="clm.page.field.manageCommon.srchCntrtNm" /></th>
                                        <td>
                                            <input class="text" style="width:80%" type="text" name="srch_cntrt_nm"  id="srch_cntrt_nm" value="<c:out value='${command.srch_cntrt_nm}'/>"/>
                                        </td>
                                        <th><spring:message code="clm.page.field.manageCommon.srchCntrtId"/></th>
                                        <td>
                                            <input class="text" style="width:80%" type="text" name="srch_cntrt_no"  id="srch_cntrt_no" value="<c:out value='${command.srch_cntrt_no}'/>"/>
                                        </td>
                                    </tr>
                                    <tr>
                                       <th><spring:message code="clm.page.field.manageCommon.srchReqmanNm" /></th>
                                        <td>
                                            <input class="text" style="width:80%" type="text" name="srch_reqman_nm" id="srch_reqman_nm" value="<c:out value='${command.srch_reqman_nm}'/>"/>
                                        </td>
                                        <th><spring:message code="clm.page.field.manageCommon.srchRespmanNm" /></th>
                                        <td>
                                            <input class="text" style="width:80%" type="text" name="srch_respman_nm" id="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}'/>" />
                                        </td>
                                        
                                        <th><spring:message code="clm.page.field.manageCommon.srchCnsdmanNm" /></th>
                                        <td>
                                            <input class="text" style="width:80%" type="text" name="srch_cnsdman_nm" id="srch_cnsdman_nm" value="<c:out value='${command.srch_cnsdman_nm}'/>" />
                                        </td>
                                        
                                        
                                    </tr>
                                    <tr>
                                       <th><spring:message code="clm.page.field.manageCommon.srchRespDept" /></th>
                                        <td>
                                            <input class="text" style="width:80%" type="text" name="srch_resp_dept_nm" id="srch_resp_dept_nm" value="<c:out value='${command.srch_resp_dept_nm}'/>" onKeypress="if(event.keyCode == 13){popDept();return false;}"/><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search" onclick="popDept()" style="cursor:pointer;margin-left:-1px" />
                                        </td>
                                        <th><spring:message code="clm.page.field.manageCommon.srchBizclsfcn" /></th>
                                        <td>
                                            <select class="srch" name="srch_biz_clsfcn" id="srch_biz_clsfcn">
                                            </select>   
                                        </td>
                                        <!-- <th><spring:message code="clm.page.field.manageCommon.srchCnclsnpurpsBigclsfcn" /></th>
                                        <td>
                                            <select class="srch" name="srch_cnclsnpurps_bigclsfcn" id="srch_cnclsnpurps_bigclsfcn">
                                            </select>   
                                        </td>-->
                                        <th><spring:message code="clm.page.field.manageCommon.srchOppntCd" /></th>
                                        <td>
                                            <input class="text" style="width:80%" type="text" name="srch_oppnt_nm" id="srch_oppnt_nm" value="<c:out value='${command.srch_oppnt_nm}'/>" onKeypress="if(event.keyCode == 13){popOppnt();return false;}"/><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search"  onclick="popOppnt()" style="cursor:pointer; margin-left:-1px"/>
                                        </td>    
                                    </tr>
                                    <tr>
                                       <th><spring:message code="clm.page.field.manageCommon.srchReqDay" /></th>
                                        <td>
                                            <input type="text" name="srch_start_reqday" id="srch_start_reqday" value="<c:out value='${command.srch_start_reqday}'/>"  class="text_calendar" style='width:35%'/>
                                            ~
                                            <input type="text" name="srch_end_reqday" id="srch_end_reqday" value="<c:out value='${command.srch_end_reqday}'/>" class="text_calendar" style='width:35%'/>
                                        </td>
                                       
                                       <th><spring:message code="clm.page.field.manageCommon.srchCnlsnday" /></th>
                                        <td>
                                            <input type="text" name="srch_start_cnlsnday" id="srch_start_cnlsnday" value="<c:out value='${command.srch_start_cnlsnday}'/>"  class="text_calendar" style='width:35%'/>
                                            ~
                                            <input type="text" name="srch_end_cnlsnday" id="srch_end_cnlsnday" value="<c:out value='${command.srch_end_cnlsnday}'/>" class="text_calendar" style='width:35%'/>
                                        </td>
                                        
                                        <th><spring:message code="clm.page.field.contract.conclusion.detail.conclusionorgacceptday" /></th>
                                        <td colspan="3">
                                            <input type="text" name="srch_str_org_acptday" id="srch_str_org_acptday" value="<c:out value='${command.srch_str_org_acptday}'/>"  class="text_calendar" style='width:35%'/>
                                            ~
                                            <input type="text" name="srch_end_org_acptday" id="srch_end_org_acptday" value="<c:out value='${command.srch_end_org_acptday}'/>" class="text_calendar" style='width:35%'/>
                                            
                                        </td>
                                     </tr>
                                     <tr>   
                                        <th><spring:message code="clm.page.field.manageCommon.srchStatus" /></th>
                                        <td>
                                            <select class="srch" name="srch_state"  id="srch_state"></select>
                                        </td>       
                                        <th><spring:message code="clm.page.msg.common.connSys" /></th>
                                        <td>
                                            <select class="srch" name="srch_if_sys_cd"  id="srch_if_sys_cd">
                                            </select>
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
											<input type="text" name="srch_oppnt_nm" id="srch_oppnt_nm" value="<c:out value='${command.srch_oppnt_nm}'/>" style="width:130px" class="text_search srch" onKeypress="if(event.keyCode == 13){popOppnt();return false;}"/><a href="javascript:popOppnt();"><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search" /></a>
										</td>										
										<th><spring:message code="clm.page.field.manageCommon.srchStatus" /></th>
										<td>
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
							<td class="vb tC">
								<a href="javascript:searchAction('');"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="clm.page.msg.common.inquire" htmlEscape="true" />"/></a>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!--//search-->
			
			<!-- button -->
			<div class="t_titBtn">
                <div class="btn_wrap_t">
                    <span class="btn"><span class="excel_down"></span><a href="javascript:excelDownLoad()"> <spring:message code='las.page.button.excelDownload' /></a></span>
                </div>
            </div>
			<!-- //button -->
	
			<!-- list -->
			<!-- 의뢰기준일 경우 -->
		<%if("cnsdreq".equals(command.getList_mode())){%>
			<style>.list_basic td, .list_basic th {padding:4px}</style>
            <table border="0" cellspacing="0" cellpadding="0" class="list_basic">

                <colgroup>
                    <col />
                    <col width="7%" />
                    <col width="85px" />
                    <col width="9%" />
                    <col width="9%" />
                    <col width="7%" />
                    <col width="9%" />
                    <col width="70px" />
                    <col width="95px" />
                </colgroup>

                <thead>
                    <tr>
                        <th><nobr><spring:message code="clm.page.field.manageCommon.reqTitle" /><br>(<spring:message code="clm.page.field.manageCommon.srchCntrtNm" />)</br></nobr></th>
                        <th  title="<spring:message code="clm.page.msg.manage.announce032" htmlEscape="true" />"><nobr><spring:message code="clm.page.field.manageCommon.reqmanNm" /><br/><spring:message code="clm.page.field.manageCommon.respmanNm" /></nobr></th>
                        <th>
                          <nobr>
                            <spring:message code="las.page.field.contractmanager.consideration.first"/> <spring:message code="clm.page.field.manageCommon.reqDay" /><br/>
                            (<spring:message code="clm.page.field.contract.conclusion.detail.conclusionorgacceptday" />)
                          </nobr>
                        </th>
                        <th><nobr><spring:message code="clm.page.field.manageCommon.respDept" /></nobr></th>
                        <th><nobr><spring:message code="clm.page.field.manageCommon.oppntCd" /></nobr></th>
                        <th><nobr><spring:message code="clm.page.field.manageCommon.cnsdmanNm" /></nobr></th>
                        <th><nobr><spring:message code="clm.page.field.manageCommon.srchCntrtId" /></nobr></th>
                        <th><nobr><spring:message code="clm.page.field.manageCommon.step" /></nobr></th>
                        <th><nobr><spring:message code="clm.page.field.manageCommon.status" /></nobr></th>
                    </tr>
                </thead>
                <tbody>
              <%
                if(pageUtil.getTotalRow() > 0) {
                    for(int idx=0; idx<resultList.size(); idx++) {
                        ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
                %>          
                    <tr  <%if(idx == resultList.size()-1){%>class="end"<%}%> onMouseOut="this.className='<%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onMouseOver="this.className='on <%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onClick="javascript:detailAction('<%=lom.get("cnsdreq_id") %>', '<%=lom.get("depth_status")%>');">
                      <td class="tL txtover overflow" style="line-height:150%" title="<%=StringUtil.convertHtmlTochars((String)lom.get("req_title")) %>&#13(<%=StringUtil.convertHtmlTochars((String)lom.get("cntrt_nm")) %>)">
                        <span style='color:#485B91'><%=StringUtil.convertHtmlTochars((String)lom.get("req_title")) %></span><br/>
                        <span style='color:#A9B4BC'>(<%=StringUtil.convertHtmlTochars((String)lom.get("cntrt_nm")) %>)</span>
                      </td>
                      <td class="txtover" title="<%=lom.get("reqman_nm") %>">
                        <%=lom.get("reqman_nm") %>
                        <%=lom.get("cntrt_respman_nm")!=null && !lom.get("cntrt_respman_nm").equals("")? "<br>" + lom.get("cntrt_respman_nm") : "" %>
                      </td>
                      <td class="txtover tC" style='text-align:center'>
                        
                            <%=lom.get("req_dt") %><br/>
                            <%if(lom.get("org_acptday")!=null && !lom.get("org_acptday").equals("")) { %>
                            <span style='color:#A9B4BC;'>(<%=lom.get("org_acptday") %>)</span>
                            <%  } %>
                        </td>
                      <td class="txtover tL" title="<%=lom.get("cntrt_resp_dept_nm") %>"><%=lom.get("cntrt_resp_dept_nm") %></td>
                      <td class="txtover tL" title="<%=lom.get("cntrt_oppnt_nm") %>"><%=lom.get("cntrt_oppnt_nm") %></td>
                      <td class="txtover"><%=lom.get("cnsdmans") %></td>
                      <td class="txtover" title="<%=lom.get("cntrt_no") %>" onclick="event.cancelBubble = true;"><%=lom.get("cntrt_no") %></td>
                      <td class="txtover" title="<%=lom.get("prcs_depth_nm") %>"><%=lom.get("prcs_depth_nm") %></td>
                      <td class="txtover" title="<%=lom.get("depth_status_nm") %>"><%=lom.get("depth_status_nm") %></td>
                    </tr>
              <%    }
                }else{        
              %>
                    <tr class="end">
                      <td colspan="9"><spring:message code="secfw.msg.succ.noResult" /></td>
                    </tr>
              <%}%>         
              </tbody>
            </table>
			
			<!-- 계약기준일 경우 -->
		<%}else{%>
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
				<colgroup>
					<col/>
					<col width="7%" />
					<col width="10%" />
					<col width="10%" />
					<col width="8%" />
					<col width="10%" />
					<col width="9%" />
					<col width="9%" />
			  	</colgroup>
			  	<thead>
					<tr>
						<th><nobr><spring:message code="clm.page.field.manageCommon.cntrtNm" /></nobr></th>
						<th><nobr><spring:message code="clm.page.field.manageCommon.respmanNm" /></nobr></th>
						<th><nobr><spring:message code="clm.page.field.manageCommon.respDept" /></nobr></th>
						<th><nobr><spring:message code="clm.page.field.manageCommon.reqDay" /></nobr></th>
						<th><nobr><spring:message code="clm.page.field.manageCommon.cnsdmanNm" /></nobr></th>
						<th><nobr><spring:message code="clm.page.field.manageCommon.cntrtCnclsnDay" /></nobr></th>
						<th><nobr><spring:message code="clm.page.field.manageCommon.step" /></nobr></th>
						<th><nobr><spring:message code="clm.page.field.manageCommon.status" /></nobr></th>
					</tr>
				</thead>
			 	<tbody>
			  <%
				if(pageUtil.getTotalRow() > 0) {
					for(int idx=0; idx<resultList.size(); idx++) {
						ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
				%>			
				    <tr  <%if(idx == resultList.size()-1){%>class="end"<%}%> onMouseOut="this.className='<%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onMouseOver="this.className='on <%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onClick="javascript:detailAction('<%=lom.get("cnsdreq_id") %>', '<%=lom.get("depth_status")%>');">
				      <td class="tL txtover" title="<%=lom.get("cntrt_nm") %>"><nobr><%=lom.get("cntrt_nm") %></nobr></td>
				      <td title="<%=lom.get("cntrt_respman_nm") %>"><nobr><%=lom.get("cntrt_respman_nm") %></nobr></td>
				      <td title="<%=lom.get("cntrt_resp_dept_nm") %>"><nobr><%=lom.get("cntrt_resp_dept_nm") %></nobr></td>
				      <td><nobr><%=lom.get("req_dt") %></nobr></td>
				      <td title="<%=lom.get("cnsdmans") %>"><nobr><%=lom.get("cnsdman") %></nobr></td>
				      <td><nobr><%=lom.get("cntrt_cnclsnday") %></nobr></td>
				      <td title="<%=lom.get("prcs_depth_nm") %>"><nobr><%=lom.get("prcs_depth_nm") %></nobr></td>
				      <td title="<%=lom.get("depth_status_nm") %>"><nobr><%=lom.get("depth_status_nm") %></nobr></td>
			        </tr>
		      <%	}
				}else{		  
 			  %>
					<tr class="end">
					  <td colspan="8"><spring:message code="secfw.msg.succ.noResult" /></td>
					</tr>
			  <%}%>			
			  </tbody>
			</table>
			<%}%>	
			<!-- //list -->

			<div class="t_titBtn">	
				<!-- total data -->
				<div class="total_num">
				<%if("cnsdreq".equals(command.getList_mode())) {%>
					<spring:message code="clm.page.msg.common.totalReq" />
				<%} else { %>
					<spring:message code="clm.page.msg.manage.totCont" />
				<%} %>
					: <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%><spring:message code="clm.page.msg.common.case" />
				</div>
				<!-- //total data -->
				
				<!-- pagination  -->
				<div class="pagination">
				<%=pageUtil.getPageNavi(pageUtil, "") %>
				</div>
				<!-- //pagination -->
			</div>
		</div>
		<!-- //content -->
		
		<!-- footer -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- //footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
</form:form>
</body>
</html>

