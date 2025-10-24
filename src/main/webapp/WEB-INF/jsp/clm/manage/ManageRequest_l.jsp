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
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%-- 
/**
 * 파  일  명 : ManageRequest_l.jsp
 * 프로그램명 : 계약서 담당자변경요청목록
 * 설      명 : 
 * 작  성  자 : 신남원
 * 작  성  일 : 2011.10.04
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	List resultList = (List)request.getAttribute("resultList");
	ManageForm command = (ManageForm)request.getAttribute("command");
%>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<link href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet"/>

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>

<script language="javascript">

	$(document).ready(function(){
		//Step 세팅            search_state
		getCodeSelectByUpCd("frm", "srch_step", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=C025&useman_mng_itm2=clmsList&combo_type=T&combo_del_yn=N&combo_selected='+'<c:out value='${command.srch_step}'/>');
		getCodeSelectByUpCd("frm", "srch_state", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=' + '<c:out value='${command.srch_step}'/>' +'&combo_type=T&combo_del_yn=N&combo_selected='+'<c:out value='${command.srch_state}'/>');
		//비즈니스 단계 
		getCodeSelectByUpCd("frm", "srch_biz_clsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=T01&combo_abbr=F&combo_type=S&combo_del_yn=N&combo_selected="+"<c:out value='${command.srch_biz_clsfcn}'/>");   
		//계약유형1
		getCodeSelectByUpCd("frm", "srch_cnclsnpurps_bigclsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=T03&combo_abbr=F&combo_type=S&combo_del_yn=N&combo_selected="+"<c:out value='${command.srch_cnclsnpurps_bigclsfcn}'/>");   
		//요청구분
	//	getCodeSelectByUpCd("frm", "srch_demand_status", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=F&combo_grp_cd=C037&combo_type=T&combo_del_yn=N&combo_selected='+'<c:out value='${command.srch_demand_status}'/>');
		
	 	initCal("srch_start_reqday");   //첫번재 의뢰일 설정
	    initCal("srch_end_reqday");     //두번재 의뢰일 설정
		<%if(!"cnsdreq".equals(command.getList_mode())){%>
	 	initCal("srch_start_cnlsnday");   //첫번재 체결일 설정
	    initCal("srch_end_cnlsnday");     //두번재 체결일 설정
	    <%}%>

	});
		
	$(function() {
		$("#srch_step").change(function() {
			var grpCd = $("#srch_step").val();
			if(grpCd == "C02502") {
				getCodeSelectByUpCd("frm", "srch_state", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&useman_mng_itm1=' + grpCd +'&combo_type=T&combo_del_yn=N&combo_selected=');
			} else if(grpCd == "C02503") {
				getCodeSelectByUpCd("frm", "srch_state", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&useman_mng_itm2=' + grpCd +'&combo_type=T&combo_del_yn=N&combo_selected=');
			} else {
				getCodeSelectByUpCd("frm", "srch_state", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=' + grpCd +'&combo_type=T&combo_del_yn=N&combo_selected=');
			}
		});
	
		$("input[name=srch_demand_gbn]").click(function() {
			searchAction('');	
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
			
			var width = 400;
			var left = Obj.right;
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
		
		viewHiddenProgress(true) ;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/manage/request.do' />";
	    frm.method.value = "listRequest";
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
		frm.action = "<c:url value='/clm/manage/request.do' />";
		frm.method.value = "listRequest";
		frm.curPage.value = pgNum;
		
		frm.submit();
	}
	
	/**
	* 관리페이지 이동
	*/	
	function detailAction(id, prcsDepth){
		var frm = document.frm;
		var dmdGbn = "<c:out value='${command.srch_demand_gbn}'/>";
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/manage/request.do' />";
		
		frm.p_demnd_gbn.value = dmdGbn;
		
		/*
		if("<c:out value='${command.list_mode}'/>" == "cnsdreq" && prcsDepth != "C02501"){ // 의뢰별
			alert("계약검토 이후에는 계약별 리스트에서 진행할 수 있습니다.");
			return;
		}else if("<c:out value='${command.list_mode}'/>" == "contract" && prcsDepth == "C02501"){ // 계약별
			alert("계약검토 단계는 의뢰별리스트에서 진행할 수 있습니다.");
			return;
		}*/
		
		//2012-05-17 
		//검토의뢰,체결단계 -> 의뢰자, 계약담당자 둘다 변경, 의뢰별 리스트에서만 처리가능
		//체결이후 단계 -> 계약담당자만 변경, 계약별 리스트에서만 처리가능
		if("<c:out value='${command.list_mode}'/>" == "cnsdreq" && (prcsDepth == "C02503" || prcsDepth == "C02504" || prcsDepth == "C02505")){ //의뢰별
			alert("<spring:message code="clm.page.msg.manage.announce035" />");
			return;
		}else if("<c:out value='${command.list_mode}'/>" == "contract" && (prcsDepth == "C02501" || prcsDepth == "C02502")){ //계약별
			alert("<spring:message code="clm.page.msg.manage.announce033" />");
			return;			
		}
		
		if("<c:out value='${command.list_mode}'/>" == "cnsdreq"){
			frm.cnsdreq_id.value = id;
		}else{
			frm.cntrt_id.value = id;
		}
		
		frm.p_prcs_depth.value = prcsDepth;
		frm.method.value = "forwardModifyRequest";
		//alert(frm.p_demnd_gbn.value);
		viewHiddenProgress(true) ;
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
		 
		 PopUpWindowOpen('', 850, 440, true);
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
</script>
</head>

<body onload='showMessage("<c:out value='${returnMessage}'/>");'>
<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id='frm' method="post" autocomplete="off"> 
	<!-- search form -->
	<input type="hidden" name="method"   value="" />
	<input type="hidden" name="menu_id"   value="<c:out value='${command.menu_id}'/>" />

	<input type="hidden" name="list_mode" value="<c:out value='${command.list_mode}'/>"/>
	<input type="hidden" id="srch_resp_dept" name="srch_resp_dept" value="<c:out value='${command.srch_resp_dept}'/>" class="srch"/>
	<input type="hidden" id="srch_oppnt_cd" name="srch_oppnt_cd" value="<c:out value='${command.srch_oppnt_cd}'/>" class="srch"/>

	<input type="hidden" id="p_demnd_gbn" name="p_demnd_gbn" value=""/>
	<input type="hidden" name="p_prcs_depth" value="" />
	<input type="hidden" name="cnsdreq_id" value="" />
	<input type="hidden" name="cntrt_id" value="" />
	
	<!-- PopUp Param -->
	<input type="hidden" name="customer_gb" value=""/>
	<input type="hidden" name="dept_nm" value=""/>
	<input type="hidden" name="page_gbn" value="one"/>
	<input type="hidden" name="srch_customer" value=""/>
	
	<!-- Detail Page Param -->
	<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>"/>

	
<!-- // **************************** Form Setting **************************** -->
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
			<h3><spring:message code="clm.page.title.manageRequest.listTitle" /></h3>
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
								<table class="search_form">
									<colgroup>
										<col width="10%"/>
										<col width="25%"/>
										<col width="10%"/>
										<col width="10%"/>
										<col width="10%"/>
										<col width="35%"/>
									</colgroup>
									<tr>
										<th><spring:message code="clm.page.field.manageCommon.srchDemandGbn" /></th>
										<td colspan="5">
											<input type="radio" name="srch_demand_gbn" value="D" <%if("D".equals(command.getSrch_demand_gbn())){%>checked<%}%> ><spring:message code="clm.page.field.manageCommon.srchDemandGbn1" /></input>
											<input type="radio" name="srch_demand_gbn" value="E" <%if("E".equals(command.getSrch_demand_gbn())){%>checked<%}%> ><spring:message code="clm.page.field.manageCommon.srchDemandGbn3" /></input>
											<input type="radio" name="srch_demand_gbn" value="T" <%if("T".equals(command.getSrch_demand_gbn())){%>checked<%}%> ><spring:message code="clm.page.field.manageCommon.srchDemandGbn2" /></input>
										</td>
<%-- 										<th><spring:message code="clm.page.field.manageCommon.srchDemandStatus" /></th>
										<td>
											<select name="srch_demand_status"	id="srch_demand_status" class="srch">
											</select>
										</td> --%>
									</tr>
									<tr>
										<th><spring:message code="clm.page.field.manageCommon.srchReqTitle" /></th>
										<td>
											<input class="srch" type="text" name="srch_review_title"  id="srch_review_title" value="<c:out value='${command.srch_review_title}'/>" style="width:100%"/>
										</td>
										<th><spring:message code="clm.page.field.manageCommon.srchReqmanNm" /></th>
										<td>
											<input class="srch" type="text" name="srch_reqman_nm" id="srch_reqman_nm" value="<c:out value='${command.srch_reqman_nm}'/>" style="width:80px" />
										</td>
										<th><spring:message code="clm.page.field.manageCommon.srchReqDay" /></th>
										<td>
											<input type="text" name="srch_start_reqday" id="srch_start_reqday" value="<c:out value='${command.srch_start_reqday}'/>"  class="text_calendar02 srch"/>
											~
	  										<input type="text" name="srch_end_reqday" id="srch_end_reqday" value="<c:out value='${command.srch_end_reqday}'/>" class="text_calendar02 srch"/>
										</td>
									</tr>
									<tr>
										<th><spring:message code="clm.page.field.manageCommon.srchRespDept" /></th>
										<td>
											<input type="text" name="srch_resp_dept_nm" id="srch_resp_dept_nm" value="<c:out value='${command.srch_resp_dept_nm}'/>" style="width:130px;" class="text_search srch" onKeypress="if(event.keyCode == 13){popDept();return false;}"/><a href="javascript:popDept();"><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search" />
										</td>
										<th><spring:message code="clm.page.field.manageCommon.srchRespmanNm" /></th>
										<td>
											<input class="srch" type="text" name="srch_respman_nm" id="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}'/>" style="width:80px" />
										</td>
										<th><spring:message code="clm.page.field.manageCommon.srchCnsdmanNm" /></th>
										<td>
											<input type="text" name="srch_cnsdman_nm" id="srch_cnsdman_nm" value="<c:out value='${command.srch_cnsdman_nm}'/>" style="width:80px" />
										</td>
									</tr>
									<tr>
										<th><spring:message code="clm.page.field.manageCommon.srchOppntCd" /></th>
										<td>
											<input type="text" name="srch_oppnt_nm" id="srch_oppnt_nm" value="<c:out value='${command.srch_oppnt_nm}'/>" style="width:100px" class="text_search srch" onKeypress="if(event.keyCode == 13){popOppnt();return false;}"/><a href="javascript:popOppnt();"><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search" /></a>
										</td>										
										<th><spring:message code="clm.page.field.manageCommon.srchStep" /></th>
										<td>
											<select class="srch" name="srch_step" id="srch_step" value="<c:out value='${command.srch_step}'/>">
											</select>			
										</td>							
										<th><spring:message code="clm.page.field.manageCommon.srchStatus" /></th>
										<td>
											<select class="srch" name="srch_state"	id="srch_state">
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
										<th><spring:message code="clm.page.field.manageCommon.srchDemandGbn" /></th>
										<td colspan="5">
											<input type="radio" name="srch_demand_gbn" value="D" <%if("D".equals(command.getSrch_demand_gbn())){%>checked<%}%> ><spring:message code="clm.page.field.manageCommon.srchDemandGbn1" /></input>
											<input type="radio" name="srch_demand_gbn" value="E" <%if("E".equals(command.getSrch_demand_gbn())){%>checked<%}%> ><spring:message code="clm.page.field.manageCommon.srchDemandGbn3" /></input>
											<input type="radio" name="srch_demand_gbn" value="T" <%if("T".equals(command.getSrch_demand_gbn())){%>checked<%}%> ><spring:message code="clm.page.field.manageCommon.srchDemandGbn2" /></input>
										</td>
<%-- 										<th><spring:message code="clm.page.field.manageCommon.srchDemandStatus" /></th>
										<td>
											<select name="srch_demand_status"	id="srch_demand_status" class="srch">
											</select>
										</td> --%>
									</tr>
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
										</td>									</tr>
									<tr>
										<th><spring:message code="clm.page.field.manageCommon.srchOppntCd" /></th>
										<td>
											<input type="text" name="srch_oppnt_nm" id="srch_oppnt_nm" value="<c:out value='${command.srch_oppnt_nm}'/>" style="width:130px" class="text_search srch" onKeypress="if(event.keyCode == 13){popOppnt();return false;}"/><a href="javascript:popOppnt();"><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search" /></a>
										</td>										
										<th><spring:message code="clm.page.field.manageCommon.srchStep" /></th>
										<td>
											<select class="srch" name="srch_step" id="srch_step">
											</select>	
										</td>									
										<th><spring:message code="clm.page.field.manageCommon.srchStatus" /></th>
										<td>
											<select class="srch" name="srch_state"	id="srch_state">
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
	
			<!-- list -->
			<!-- 의뢰기준일 경우 -->
		<%if("cnsdreq".equals(command.getList_mode())){%>
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
				<colgroup>
					<col />
					<col width="6%" />
					<col width="7%" />
					<col width="10%" />
					<col width="7%" />
					<col width="9%" />
					<col width="10%" />
					<col width="9%" />
					<col width="9%" />
					<col width="9%" />
			  	</colgroup>
			  	<thead>
					<tr>
						<th><nobr><spring:message code="clm.page.field.manageCommon.reqTitle" /></nobr></th>
						<th><nobr><spring:message code="clm.page.field.manageCommon.cntrtCnt" /></nobr></th>
						<th><nobr><spring:message code="clm.page.field.manageCommon.reqmanNm" /></nobr></th>
						<th><nobr><spring:message code="clm.page.field.manageCommon.reqDay" /></nobr></th>
						<th><nobr><spring:message code="clm.page.field.manageCommon.respmanNm" /></nobr></th>
						<th><nobr><spring:message code="clm.page.field.manageCommon.respDept" /></nobr></th>
						<th><nobr><spring:message code="clm.page.field.manageCommon.oppntCd" /></nobr></th>
						<th><nobr><spring:message code="clm.page.field.manageCommon.cnsdmanNm" /></nobr></th>
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
				    <tr  <%if(idx == resultList.size()-1){%>class="end"<%}%> onmouseout="this.className='<%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onmouseover="this.className='on <%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onclick="javascript:detailAction('<%=lom.get("cnsdreq_id") %>', '<%=lom.get("prcs_depth")%>');">
				      <td class="tL txtover" title="<%=StringUtil.convertHtmlTochars((String)lom.get("req_title")) %>"><nobr><%=StringUtil.convertHtmlTochars((String)lom.get("req_title")) %></nobr></td>
				      <td id="cndreq<%=idx%>" onmouseover="javascript:listCntrt(this.id, '<%=lom.get("cnsdreq_id") %>', 'show');" onmouseout="javascript:listCntrt(this.id, '<%=lom.get("cnsdreq_id") %>','hide');"><nobr><%=lom.get("cntrt_cnt") %></nobr></td>
				      <td title="<%=lom.get("reqman_nm") %>"><nobr><%=lom.get("reqman_nm") %></nobr></td>
				      <td><nobr><%=DateUtil.formatDate((String)(lom.get("req_dt")), "-") %></nobr></td>
				      <td title="<%=lom.get("cntrt_respman_nm") %>"><nobr><%=lom.get("cntrt_respman_nm") %></nobr></td>
				      <td title="<%=lom.get("cntrt_resp_dept_nm") %>"><nobr><%=lom.get("cntrt_resp_dept_nm") %></nobr></td>
				      <td title="<%=lom.get("cntrt_oppnt_nm") %>"><nobr><%=lom.get("cntrt_oppnt_nm") %></nobr></td>
				      <td title="<%=StringUtil.replace((String)lom.get("cnsdmans"),"<br>","&#13;") %>"><%=lom.get("cnsdman") %></td>
				      <td title="<%=lom.get("prcs_depth_nm") %>"><nobr><%=lom.get("prcs_depth_nm") %></nobr></td>
				      <td title="<%=lom.get("depth_status_nm") %>"><nobr><%=lom.get("depth_status_nm") %></nobr></td>
			        </tr>
		      <%	}
				}else{		  
 			  %>
					<tr class="end">
					  <td colspan="10"><spring:message code="secfw.msg.succ.noResult" /></td>
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
				    <tr  <%if(idx == resultList.size()-1){%>class="end"<%}%> onmouseout="this.className='<%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onmouseover="this.className='on <%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onclick="javascript:detailAction('<%=lom.get("cntrt_id") %>', '<%=lom.get("prcs_depth")%>');">
				      <td class="tL txtover" title="<%=lom.get("cntrt_nm") %>"><nobr><%=lom.get("cntrt_nm") %></nobr></td>
				      <td title="<%=lom.get("cntrt_respman_nm") %>"><nobr><%=lom.get("cntrt_respman_nm") %></nobr></td>
				      <td title="<%=lom.get("cntrt_resp_dept_nm") %>"><nobr><%=lom.get("cntrt_resp_dept_nm") %></nobr></td>
				      <td><nobr><%=DateUtil.formatDate((String)(lom.get("req_dt")), "-") %></nobr></td>
				      <td title="<%=lom.get("cnsdmans") %>"><nobr><%=lom.get("cnsdman") %></nobr></td>
				      <td><nobr><%=DateUtil.formatDate((String)(lom.get("cntrt_cnclsnday")), "-") %></nobr></td>
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

