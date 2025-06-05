<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.sec.common.util.ClmsBoardUtil"%>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>

<%-- 
/**
 * 파  일  명 : SearchDetail_l.jsp
 * 프로그램명 : 계약문서 검색화면(search)
 * 설      명 : 계약문서 검색화면 페이지(개별)
 */
--%>

<%
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");	
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/new_style.js'/>" type="text/javascript"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/menu.js'/>"></script>

<script language="javascript">

	$(document).ready(function(){
		initCal("srch_start_dt"); //달력세팅	
		initCal("srch_end_dt"); //달력세팅
		getCodeSelectByUpCd("frm", "biz_clsfcn", '/clm/search/search.do?method=getSrchDetailCombo&comboflag=getBiz_clsfcn&flag=L'); //계약유형1콤보박스 세팅
		getCodeSelectByUpCd("frm", "depth_clsfcn", '/clm/search/search.do?method=getSrchDetailCombo&comboflag=getDepth_clsfcn&flag=L'); //계약유형2콤보박스 세팅
		getCodeSelectByUpCd("frm", "cnclsnpurps_bigclsfcn", '/clm/search/search.do?method=getSrchDetailCombo&comboflag=getCnclsnpurps_bigclsfcn&flag=L'); //계약유형3콤보박스 세팅
		getCodeSelectByUpCd("frm", "cnclsnpurps_midclsfcn", '/clm/search/search.do?method=getSrchDetailCombo&comboflag=getCnclsnpurps_midclsfcn&flag=L'); //계약유형4콤보박스 세팅
		
		viewHiddenProgress(false) ;
		
	});

	$(function() {
		$("#cnclsnpurps_bigclsfcn").change(function() {
			var upCd = $("#cnclsnpurps_bigclsfcn").val();
			getCodeSelectByUpCd("frm", "cnclsnpurps_midclsfcn", '/clm/search/search.do?method=getSrchDetailCombo&comboflag=getCnclsnpurps_midclsfcn&flag=C&upCd=' + upCd);
		});

	});
		
	/**
	* 검색결과 더보기 function
	*/
	function goMore(indexDb){
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/search/search.do' />"; //package com.sec.clm.search.control SearchController.java
		frm.method.value = "listDetailSearch";
		frm.curPage.value = "1";
		frm.srch_index_db.value = indexDb;
		frm.flag.value = "L"; //Url통합관련(계약)
		
		frm.submit();
		viewHiddenProgress(true) ;
	}
	
	/**
	* 페이징 function
	*/
	function goPage(pgNum){
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/search/search.do' />"; //package com.sec.clm.search.control SearchController.java
		frm.method.value = "listDetailSearch";
		frm.curPage.value = pgNum;
		frm.flag.value = "L"; //Url통합관련(계약)
		
		frm.submit();
		viewHiddenProgress(true) ;
	}
	
	/**
	* 검색결과 더보기 function(계약그룹)
	*/
	function goGroupMore(indexDb){
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/search/search.do' />"; //package com.sec.clm.search.control SearchController.java
		frm.method.value = "listSearch";
		frm.curPage.value = "1";
		frm.srch_index_db.value = indexDb;
		frm.flag.value = "L"; //Url통합관련(계약)
		
		frm.submit();
		viewHiddenProgress(true) ;
	}
  
	/**
	* 버튼 동작 부분
	*/ 
	function pageAction(){
		
		var frm = document.frm;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/search/search.do' />"; //package com.sec.clm.search.control SearchController.java
		frm.flag.value = "L"; //Url통합관련
		
		var flag = "";
		//검색체크박스 활성화 여부를 통해 상세검색인지 판단한다.
		if(frm.dbsrh[0].disabled == false) {
			flag = "searchdtl";
		} else {
			flag = "search";
		}
 
		if(flag == "search"){ //통합검색
			frm.queryText.value = frm.query.value;
			tmpquery = frm.query.value;
			if(frm.queryText.value == "") { //입력된 검색어가 없을경우
				alert("<spring:message code="clm.msg.alert.srch.noQuery" />");
				return;
			} else if(tmpquery.length > 100) { //입력된 검색어가 너무 길경우
				alert("<spring:message code="clm.msg.alert.srch.valQuery" />");
				return;
			}
		    frm.method.value = "listSearch";		    
			frm.curPage.value = "1";
			//frm.srch_index_db.value = "clm_master_consideration,clm_master_consultation,clm_master_conclusion,clm_master_execution,clm_master_completion,clm_about,clm_rule_regulation,clm_share_terms,clm_counsel_notice,clm_counsel_faq,clm_counsel_qna";
			//카테고리 체크에 따라 검색할 색인DB를 세팅한다.
			for(i = 0; i < frm.dbsrh.length; i++)
			{
				if(frm.dbsrh[i].checked == true)
				{
					if(frm.srch_index_db.value == "") {
						frm.srch_index_db.value = frm.dbsrh[i].value;
					} else {
						frm.srch_index_db.value = frm.srch_index_db.value + "," + frm.dbsrh[i].value;
					}
				}	
			}
			frm.queryField.value = "";
			frm.fieldText.value = "";
			frm.srchMinDate.value = "";
			frm.srchMaxDate.value = "";
			frm.submit();
			viewHiddenProgress(true) ;
		} else if(flag == "searchdtl"){ //상세검색
			frm.queryText.value = frm.query.value;
			tmpquery = frm.query.value;
			if(frm.queryText.value == "") { //입력된 검색어가 없을경우
				alert("<spring:message code="clm.msg.alert.srch.noQuery" />");
				return;
			} else if(tmpquery.length > 100) { //입력된 검색어가 너무 길경우
				alert("<spring:message code="clm.msg.alert.srch.valQuery" />");
				return;
			}
		    frm.method.value = "listSearch";		    
			frm.curPage.value = "1";
			frm.srch_index_db.value = "";
			frm.queryField.value = "";
			frm.fieldText.value = "";
			frm.srchMinDate.value = "";
			frm.srchMaxDate.value = "";
			
			var checkdbnum = 0;
			//카테고리 체크에 따라 검색할 색인DB를 세팅한다.
			for(i = 0; i < frm.dbsrh.length; i++)
			{
				if(frm.dbsrh[i].checked == true)
				{
					if(frm.srch_index_db.value == "") {
						frm.srch_index_db.value = frm.dbsrh[i].value;
					} else {
						frm.srch_index_db.value = frm.srch_index_db.value + "," + frm.dbsrh[i].value;
					}
					checkdbnum++;
				}	
			}
			
			//카테고리 체크가 하나도 없을경우
			if(checkdbnum < 1 ) {
				alert("<spring:message code="clm.msg.alert.srch.noCate" />");
				return;
			}
			
			//계약서, 자문 혹은 공지 및 Q&A 카테고리 검색이 아니고 카테고리가 하나인경우 개별검색을 한다.
			if(frm.dbsrh[0].checked != true & frm.dbsrh[1].checked != true & frm.dbsrh[5].checked != true) {
				if(checkdbnum == 1) frm.method.value = "listDetailSearch";
			}
			
			//아래의 경우는 없지만 에러를 방지하기 위해 검색할 색인DB가 하나도 없을경우 전체 색인DB를 세팅한다.
			if(frm.srch_index_db.value == "") {
				frm.srch_index_db.value = "clm_master_consideration,clm_master_consultation,clm_master_conclusion,clm_master_execution,clm_master_completion,clm_about,clm_rule_regulation,clm_share_terms,clm_counsel_notice,clm_counsel_faq,clm_counsel_qna";
			}
			
			var checknum = 0;
			//계약서 검색분류
			for(i = 0; i < frm.zsrh.length; i++)
			{
				if(frm.zsrh[i].checked == true)
				{
					if(frm.queryField.value == "") {
						frm.queryField.value = frm.zsrh[i].value;
					} else {
						frm.queryField.value = frm.queryField.value + ":" + frm.zsrh[i].value;
					}
					checknum++;
				}
			}
			
			if(checknum == 2) frm.queryField.value = "";
			
			//검색분류에서 정보가 체크됬을경우
			if(frm.finfo.checked == true) {
				var fieldText = "";
				//상대방지역
				//if(frm.region_gbn.options[frm.region_gbn.selectedIndex].value != "") {
					//fieldText = "MATCH{"+frm.region_gbn.options[frm.region_gbn.selectedIndex].value+"}:MF_REGION_GBN";
				//}
				//계약유형1
				if(frm.biz_clsfcn.options[frm.biz_clsfcn.selectedIndex].value != "") {
					if(fieldText != "") {
						fieldText = fieldText + " AND MATCH{" + frm.biz_clsfcn.options[frm.biz_clsfcn.selectedIndex].value + "}:MF_BIZ_CLSFCN";
					} else {
						fieldText = "MATCH{" + frm.biz_clsfcn.options[frm.biz_clsfcn.selectedIndex].value + "}:MF_BIZ_CLSFCN";
					}
				}
				//계약유형2
				if(frm.depth_clsfcn.options[frm.depth_clsfcn.selectedIndex].value != "") {
					if(fieldText != "") {
						fieldText = fieldText + " AND MATCH{" + frm.depth_clsfcn.options[frm.depth_clsfcn.selectedIndex].value + "}:MF_DEPTH_CLSFCN";
					} else {
						fieldText = "MATCH{" + frm.depth_clsfcn.options[frm.depth_clsfcn.selectedIndex].value + "}:MF_DEPTH_CLSFCN";
					}
				}
				//계약유형3
				if(frm.cnclsnpurps_bigclsfcn.options[frm.cnclsnpurps_bigclsfcn.selectedIndex].value != "") {
					if(fieldText != "") {
						fieldText = fieldText + " AND MATCH{" + frm.cnclsnpurps_bigclsfcn.options[frm.cnclsnpurps_bigclsfcn.selectedIndex].value + "}:MF_CNCLSNPURPS_BIGCLSFCN";
					} else {
						fieldText = "MATCH{" + frm.cnclsnpurps_bigclsfcn.options[frm.cnclsnpurps_bigclsfcn.selectedIndex].value + "}:MF_CNCLSNPURPS_BIGCLSFCN";
					}
				}
				//계약유형4
				if(frm.cnclsnpurps_midclsfcn.options[frm.cnclsnpurps_midclsfcn.selectedIndex].value != "") {
					if(fieldText != "") {
						fieldText = fieldText + " AND MATCH{" + frm.cnclsnpurps_midclsfcn.options[frm.cnclsnpurps_midclsfcn.selectedIndex].value + "}:MF_CNCLSNPURPS_MIDCLSFCN";
					} else {
						fieldText = "MATCH{" + frm.cnclsnpurps_midclsfcn.options[frm.cnclsnpurps_midclsfcn.selectedIndex].value + "}:MF_CNCLSNPURPS_MIDCLSFCN";
					}
				}
				//지불/수금구분
				if(frm.payment_gbn.options[frm.payment_gbn.selectedIndex].value != "") {
					if(fieldText != "") {
						fieldText = fieldText + " AND MATCH{" + frm.payment_gbn.options[frm.payment_gbn.selectedIndex].value + "}:MF_PAYMENT_GBN";
					} else {
						fieldText = "MATCH{" + frm.payment_gbn.options[frm.payment_gbn.selectedIndex].value + "}:MF_PAYMENT_GBN";
					}
				}
				//계약체결여부
				if(frm.cntrt_cnclsn_yn.options[frm.cntrt_cnclsn_yn.selectedIndex].value != "") {
					if(fieldText != "") {
						fieldText = fieldText + " AND MATCH{" + frm.cntrt_cnclsn_yn.options[frm.cntrt_cnclsn_yn.selectedIndex].value + "}:MF_CNTRT_CNCLSN_YN";
					} else {
						fieldText = "MATCH{" + frm.cntrt_cnclsn_yn.options[frm.cntrt_cnclsn_yn.selectedIndex].value + "}:MF_CNTRT_CNCLSN_YN";
					}
				}
				//계약단계
				if(frm.cntrt_status.options[frm.cntrt_status.selectedIndex].value != "") {
					if(fieldText != "") {
						fieldText = fieldText + " AND MATCH{" + frm.cntrt_status.options[frm.cntrt_status.selectedIndex].value + "}:MF_PRCS_DEPTH";
					} else {
						fieldText = "MATCH{" + frm.cntrt_status.options[frm.cntrt_status.selectedIndex].value + "}:MF_PRCS_DEPTH";
					}
				}
				if(fieldText != "") frm.fieldText.value = fieldText;
				//날짜체크
				if(frm.srch_start_dt.value > frm.srch_end_dt.value & frm.srch_start_dt.value != "" & frm.srch_end_dt.value != "") {
					alert("<spring:message code="clm.msg.alert.srch.valDate" />");
					frm.srch_end_dt.value = "";
					return;
				}
				if(frm.srch_start_dt.value != "") frm.srchMinDate.value = frm.srch_start_dt.value;
				if(frm.srch_end_dt.value != "") frm.srchMaxDate.value = frm.srch_end_dt.value;
				
			}
			
			frm.submit();
			viewHiddenProgress(true) ;
		}
	}
	
	/**
	* 계약서 상세선택항목 활성화
	*/
	function actsrh(_elm){		
		var frm = document.frm;
		if(frm.dbsrh[0].checked == true) {
			//계약서 카테고리가 체크됐을경우
			$('#detailSearch').show();
			frm.zsrh[0].checked = true;
			frm.zsrh[1].checked = true;
			frm.zsrh[0].disabled = false;
			frm.zsrh[1].disabled = false;
			frm.finfo.checked = false;
			frm.finfo.disabled = false;
			
			actsrhinfo();
		} else {
			$('#detailSearch').hide();
			frm.zsrh[0].checked = false;
			frm.zsrh[1].checked = false;
			frm.zsrh[0].disabled = true;
			frm.zsrh[1].disabled = true;
			frm.finfo.checked = false;
			frm.finfo.disabled = true;
			
			actsrhinfo();
		}
	}
	
	/**
	* 계약서 정보 세부선택항목 활성화
	*/
	function actsrhinfo(){		
		var frm = document.frm;
		if(frm.finfo.checked == true) {
			//frm.region_gbn.disabled = false;
			frm.biz_clsfcn.disabled = false;
			frm.depth_clsfcn.disabled = false;
			frm.cnclsnpurps_bigclsfcn.disabled = false;
			frm.cnclsnpurps_midclsfcn.disabled = false;
			frm.payment_gbn.disabled = false;
			frm.cntrt_cnclsn_yn.disabled = false;
			frm.cntrt_status.disabled = false;
			frm.srch_start_dt.disabled = false;
			frm.srch_end_dt.disabled = false;			
		} else {
			//frm.region_gbn.disabled = true;
			frm.biz_clsfcn.disabled = true;
			frm.depth_clsfcn.disabled = true;
			frm.cnclsnpurps_bigclsfcn.disabled = true;
			frm.cnclsnpurps_midclsfcn.disabled = true;
			frm.payment_gbn.disabled = true;
			frm.cntrt_cnclsn_yn.disabled = true;
			frm.cntrt_status.disabled = true;
			frm.srch_start_dt.disabled = true;
			frm.srch_end_dt.disabled = true;
		}
		//frm.region_gbn.options[0].selected = true;
		frm.biz_clsfcn.options[0].selected = true;
		frm.depth_clsfcn.options[0].selected = true;
		frm.cnclsnpurps_bigclsfcn.options[0].selected = true;
		frm.cnclsnpurps_midclsfcn.options[0].selected = true;
		frm.payment_gbn.options[0].selected = true;
		frm.cntrt_cnclsn_yn.options[0].selected = true;
		frm.cntrt_status.options[0].selected = true;
		frm.srch_start_dt.value= "";
		frm.srch_end_dt.value= "";
	}
	
	/**
	* 카테고리 영역활성화
	*/
	function actdetailSearch(){		
		var frm = document.frm;
		if(frm.dbsrh[0].disabled == true) {
			frm.dbsrh[0].checked = false;
			frm.dbsrh[1].checked = false;
			frm.dbsrh[2].checked = false;
			frm.dbsrh[3].checked = false;
			frm.dbsrh[4].checked = false;
			frm.dbsrh[5].checked = false;
			frm.dbsrh[0].disabled = false;
			frm.dbsrh[1].disabled = false;
			frm.dbsrh[2].disabled = false;
			frm.dbsrh[3].disabled = false;
			frm.dbsrh[4].disabled = false;
			frm.dbsrh[5].disabled = false;
		} else {
			frm.dbsrh[0].checked = true;
			frm.dbsrh[1].checked = true;
			frm.dbsrh[2].checked = true;
			frm.dbsrh[3].checked = true;
			frm.dbsrh[4].checked = true;
			frm.dbsrh[5].checked = true;
			frm.dbsrh[0].disabled = true;
			frm.dbsrh[1].disabled = true;
			frm.dbsrh[2].disabled = true;
			frm.dbsrh[3].disabled = true;
			frm.dbsrh[4].disabled = true;
			frm.dbsrh[5].disabled = true;
		}
		
		var searchTarget = $('#searchTarget');
		if (searchTarget.is(":visible")) {
			searchTarget.hide();
		} else {
			$('#detailSearch').hide();
			searchTarget.show();
		}
	}
</script>
</head>

<body class='search_body'>


<div id="wrap">
	<!-- container -->
	<div id="container" class="search_viewlist_area">
		
		<!-- **************************** Form Setting **************************** -->
		<form:form name="frm" id='frm' method="post" autocomplete="off"> 
			<!-- search form -->
			<input type="hidden" name="method"   value="" />
			<input type="hidden" name="menu_id"   value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" id="curPage" name="curPage" value="<c:out value='${command.curPage}'/>">
			<input type="hidden" id="srch_index_db" name="srch_index_db" value="<c:out value='${command.srch_index_db}'/>">
			<input type="hidden" id="queryText" name="queryText" value="<c:out value='${command.queryText}'/>">
			<input type="hidden" id="queryField" name="queryField" value="<c:out value='${command.queryField}'/>">
			<input type="hidden" id="fieldText" name="fieldText" value="<c:out value='${command.fieldText}'/>">
			<input type="hidden" id="srchMinDate" name="srchMinDate" value="<c:out value='${command.srchMinDate}'/>">
			<input type="hidden" id="srchMaxDate" name="srchMaxDate" value="<c:out value='${command.srchMaxDate}'/>">
			
			<!-- key Form -->
			
			<!-- 상단 메뉴관련 -->
			<!-- targetMenuId -->
			<input type="hidden" name="targetMenuId">
			<input type="hidden" name="target_menu_id">
			
			<!-- selected_menu_id -->
			<input type="hidden" name="selected_menu_id">
			<input type="hidden" name="selected_menu_nm">
			<input type="hidden" name="selected_menu_detail_id">
			<!--상세내역에 띄워줄 URL 정보.-->
			<input type="hidden" name="set_init_url">
			
			<input type="hidden" name="menuLogEnable" value="Y">
			<input type="hidden" name="chgLangflag">		
			
			<input type="hidden" id="flag" name="flag" value=""/>
			<!-- //상단 메뉴관련 -->
		
		<!-- //
		**************************** Form Setting **************************** 
		-->
		<!-- lnb -->
		<div id="lnbColum">
			<h2 class="lnb_title"><spring:message code="clm.page.field.srch.searchResult" /></h2>
			<div class="lnb_menu">
			<ul class="lnb_menu"><a href="javascript:goGroupMore('clm_master_consideration,clm_master_consultation,clm_master_conclusion,clm_master_execution,clm_master_completion');"><spring:message code="clm.page.field.srch.contract" /></a>
				<li><a href="javascript:goMore('clm_master_consideration');"><spring:message code="clm.page.field.srch.consideration" /> <c:if test="${command.srch_index_db == 'clm_master_consideration'}">(<c:out value='${totalSearchCount}'/>)</c:if></a></li>
				<li><a href="javascript:goMore('clm_master_consultation');"><spring:message code="clm.page.field.srch.consultation" /> <c:if test="${command.srch_index_db == 'clm_master_consultation'}">(<c:out value='${totalSearchCount}'/>)</c:if></a></li>
				<li><a href="javascript:goMore('clm_master_conclusion');"><spring:message code="clm.page.field.srch.conclusion" /> <c:if test="${command.srch_index_db == 'clm_master_conclusion'}">(<c:out value='${totalSearchCount}'/>)</c:if></a></li>
				<li><a href="javascript:goMore('clm_master_execution');"><spring:message code="clm.page.field.srch.execution" /> <c:if test="${command.srch_index_db == 'clm_master_execution'}">(<c:out value='${totalSearchCount}'/>)</c:if></a></li>
				<li><a href="javascript:goMore('clm_master_completion');"><spring:message code="clm.page.field.srch.completion" /> <c:if test="${command.srch_index_db == 'clm_master_completion'}">(<c:out value='${totalSearchCount}'/>)</c:if></a></li>
			</ul>
			<ul class="lnb_menu"><a href="javascript:goGroupMore('clm_advice_review,clm_advice_request,clm_advice_ntts');"><spring:message code="clm.page.field.srch.advice" /></a></li>
				<%
				    if (ClmsBoardUtil.searchRole(request, "RA00") || ClmsBoardUtil.searchRole(request, "RA01") || ClmsBoardUtil.searchRole(request, "RD01") || ClmsBoardUtil.searchRole(request, "RM00") || ClmsBoardUtil.searchRole(request, "RA02")) {
				%>
				<li><a href="javascript:goMore('clm_advice_review');"><spring:message code="clm.page.field.srch.advicereview" /> <c:if test="${command.srch_index_db == 'clm_advice_review'}">(<c:out value='${totalSearchCount}'/>)</c:if></a></li>
				<%
				    }
				%>
				<li><a href="javascript:goMore('clm_advice_request');"><spring:message code="clm.page.field.srch.advicerequest" /> <c:if test="${command.srch_index_db == 'clm_advice_request'}">(<c:out value='${totalSearchCount}'/>)</c:if></a></li>
				<%
				    if (ClmsBoardUtil.searchRole(request, "RA00") || ClmsBoardUtil.searchRole(request, "RA01") || ClmsBoardUtil.searchRole(request, "RD01") || ClmsBoardUtil.searchRole(request, "RM00") || ClmsBoardUtil.searchRole(request, "RA02")) {
				%>
				<li><a href="javascript:goMore('clm_advice_ntts');"><spring:message code="clm.page.field.srch.advicentts" /> <c:if test="${command.srch_index_db == 'clm_advice_ntts'}">(<c:out value='${totalSearchCount}'/>)</c:if></a></li>
				<%
				    }
				%>
			</ul>
			<ul class="lnb_menu"><a href="javascript:goMore('clm_about');"><spring:message code="clm.page.field.srch.about" /> <c:if test="${command.srch_index_db == 'clm_about'}">(<c:out value='${totalSearchCount}'/>)</c:if></a></ul>
			<ul class="lnb_menu"><a href="javascript:goMore('clm_rule_regulation');"><spring:message code="clm.page.field.srch.rule" /> <c:if test="${command.srch_index_db == 'clm_rule_regulation'}">(<c:out value='${totalSearchCount}'/>)</c:if></a></ul>
			<ul class="lnb_menu"><a href="javascript:goMore('clm_share_terms');"><spring:message code="clm.page.field.srch.share" /> <c:if test="${command.srch_index_db == 'clm_share_terms'}">(<c:out value='${totalSearchCount}'/>)</c:if></a></ul>
			<ul class="lnb_menu"><a href="javascript:goGroupMore('clm_counsel_notice,clm_counsel_faq,clm_counsel_qna');"><spring:message code="clm.page.field.srch.counsel" /></a>
				<li><a href="javascript:goMore('clm_counsel_notice');"><spring:message code="clm.page.field.srch.notice" /> <c:if test="${command.srch_index_db == 'clm_counsel_notice'}">(<c:out value='${totalSearchCount}'/>)</c:if></a></li>
				<li><a href="javascript:goMore('clm_counsel_faq');"><spring:message code="clm.page.field.srch.faq" /> <c:if test="${command.srch_index_db == 'clm_counsel_faq'}">(<c:out value='${totalSearchCount}'/>)</c:if></a></li>
				<li><a href="javascript:goMore('clm_counsel_qna');"><spring:message code="clm.page.field.srch.qna" /> <c:if test="${command.srch_index_db == 'clm_counsel_qna'}">(<c:out value='${totalSearchCount}'/>)</c:if></a></li>
			</ul>
			</div>
		</div>
		<!-- //lnb -->
		
		<!-- content -->	
		<div id="content_search">
		<div id="content_in">
			
		<!-- Location -->
		<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"> Home > <spring:message code="clm.page.field.srch.search" /></div>
		<!-- //Location -->
		
		<!-- title -->
	  	<div class="title">
			<h3><spring:message code="clm.page.field.srch.search" /></h3>
		</div>
		<!-- //title -->
		<div class='search_area'> 
			<table>
				<tr>
					<td style='height:50px; width:130px'><img src='<%=IMAGE%>/common/search_img.gif'></td>
					<td>
						<input id="query" name="query" type="text" maxlength=100 onkeypress="if(event.keyCode==13) {pageAction();}" value="<c:out value='${command.queryText}'/>" class="text_search_d" />
						<span class="btn_all_g"><a href="javascript:;"  onclick="pageAction();"><spring:message code="clm.page.field.srch.search" /></a></span>  
						<span class="btn_all_gd"><a href="javascript:;" onclick="actdetailSearch();" class="detail_search"><spring:message code="clm.page.msg.manage.detailSearch" /></a></span>    
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<label><input type="checkbox" name="dbsrh" id="dbsrh" onclick="actsrh(this)" value="clm_master_consideration,clm_master_consultation,clm_master_conclusion,clm_master_execution,clm_master_completion" checked disabled/> <spring:message code="clm.page.field.srch.main.contract" /></label>
						<label><input type="checkbox" name="dbsrh" id="dbsrh" value="clm_advice_review,clm_advice_request,clm_advice_ntts" checked disabled/> <spring:message code="clm.page.field.srch.main.advice" /></label>
						<label><input type="checkbox" name="dbsrh" id="dbsrh" value="clm_about" checked disabled/> <spring:message code="clm.page.field.srch.main.about" /></label>
						<label><input type="checkbox" name="dbsrh" id="dbsrh" value="clm_rule_regulation" checked disabled/> <spring:message code="clm.page.field.srch.main.rule" /></label>
						<label><input type="checkbox" name="dbsrh" id="dbsrh" value="clm_share_terms" checked disabled/> <spring:message code="clm.page.field.srch.main.share" /></label>
						<label><input type="checkbox" name="dbsrh" id="dbsrh" value="clm_counsel_notice,clm_counsel_faq,clm_counsel_qna" checked disabled/> <spring:message code="clm.page.field.srch.main.counsel" /></label>
					</td>
				</tr>
			</table>
		</div>
			
			<!--계약상세검색-->
			<div class="search_box search_sub_list" id="detailSearch" style="display: none;">
					<div class="search_box_inner">
					<h1 class="search_box_h1"> <spring:message code="clm.page.field.srch.main.contract" /> <span class='close_s' onclick="divHidden(detailSearch)"></span></h1>
							<table class="search_tb">
									<tr> 
											<td><table class="search_form">
															<colgroup>
															<col width="150px"/>
															<col width="250px"/>
															</colgroup>
															<tr>
																	<th><spring:message code="clm.page.field.srch.contractField" /> </th>
																	<td>
																		<input type="checkbox" name="zsrh" id="zsrh" value="DRETITLE:IF_REQ_TITLE" checked /><label for="option01"><spring:message code="clm.page.field.srch.title" /></label> 
																		<input type="checkbox" name="zsrh" id="zsrh" value="DREFILECONTENT:IF_ORG_FILE_NM" checked /><label for="option02"><spring:message code="clm.page.field.srch.attach" /></label> 
																		<input type="checkbox" name="finfo" id="finfo" onclick="actsrhinfo()" value="" /><label for="option03"><spring:message code="clm.page.field.srch.info" /></label> 
																	</td>
															</tr>
															<tr>
																	<th><spring:message code="clm.page.field.srch.dredate" /> </th>
																	<td><input type="text" name="srch_start_dt" id="srch_start_dt" value="" class="text_search" style="width:72px" readonly /> ~ <input type="text" name="srch_end_dt" id="srch_end_dt" value="" disabled class="text_search" style="width:73px" readonly /></td>
															</tr>
															<tr>
																	<th><spring:message code="clm.page.field.srch.biz_clsfcn" /> </th>
																	<td><select class="all" style="width:199px" name="biz_clsfcn" id="biz_clsfcn" disabled>
																			</select></td>
															</tr>
															<tr>
																	<th><spring:message code="clm.page.field.srch.depth_clsfcn" /> </th>
																	<td><select class="all" style="width:199px" name="depth_clsfcn" id="depth_clsfcn" disabled>
																			</select></td>
															</tr>
															<tr>
																	<th><spring:message code="clm.page.field.srch.cnclsnpurps_bigclsfcn" /> </th>
																	<td><select class="all" style="width:199px" name="cnclsnpurps_bigclsfcn" id="cnclsnpurps_bigclsfcn" disabled>
																			</select></td>
															</tr>
															<tr>
																	<th><spring:message code="clm.page.field.srch.cnclsnpurps_midclsfcn" /> </th>
																	<td><select class="all" style="width:199px" name="cnclsnpurps_midclsfcn" id="cnclsnpurps_midclsfcn" disabled>
																			</select></td>
															</tr>
															<tr>
																	<th><spring:message code="clm.page.field.srch.payment_gbn" /> </th>
																	<td><select class="all" style="width:199px" name="payment_gbn" id="payment_gbn" disabled>
																			<option value = "" selected>-- ALL --</option>
																			<option value = "C02001" ><spring:message code="clm.page.field.srch.payment_gbn_value1" /></option>
																			<option value = "C02002" ><spring:message code="clm.page.field.srch.payment_gbn_value2" /></option>
																			<option value = "C02003" ><spring:message code="clm.page.field.srch.payment_gbn_value3" /></option>
																			<option value = "C02004" ><spring:message code="clm.page.field.srch.payment_gbn_value4" /></option>
																			</select></td>
															</tr>
															<tr>
																	<th><spring:message code="clm.page.field.srch.cntrt_cnclsn_yn" /> </th>
																	<td><select class="all" style="width:199px" name="cntrt_cnclsn_yn" id="cntrt_cnclsn_yn" disabled>
																			<option value = "" selected>-- ALL --</option>
																			<option value = "Y" ><spring:message code="clm.page.field.srch.cntrt_cnclsn_yn_value1" /></option>
																			<option value = "N" ><spring:message code="clm.page.field.srch.cntrt_cnclsn_yn_value2" /></option>
																			</select></td>
															</tr>
															<tr>
																	<th><spring:message code="clm.page.field.srch.cntrt_status" /> </th>
																	<td><select class="all" style="width:199px" name="cntrt_status" id="cntrt_status" disabled>
																			<option value = "" selected>-- ALL --</option>
																			<option value = "C02501" ><spring:message code="clm.page.field.srch.cntrt_status_value1" /></option>
																			<option value = "C02502" ><spring:message code="clm.page.field.srch.cntrt_status_value2" /></option>
																			<option value = "C02503" ><spring:message code="clm.page.field.srch.cntrt_status_value3" /></option>
																			<option value = "C02504" ><spring:message code="clm.page.field.srch.cntrt_status_value4" /></option>
																			<option value = "C02505" ><spring:message code="clm.page.field.srch.cntrt_status_value5" /></option>
																			</select></td>
															</tr>
															
													</table></td>
									</tr>
							</table>
						
					 </div>
			</div>
			<!----// 계약상세검색-----> 

			<!-- search sum -->
			<div class="search_sum">
				<spring:message code="clm.page.msq.srch.searchResult" arguments="${command.queryText},${totalSearchCount}" />
			</div>
			<!-- //search sum -->
			<!-- sub title and more -->
			<div class="t_titBtn">
				<div class="title_basic">
					<h4><spring:message code="${indexSysName}" /></h4>
				</div>				
			</div>
			<!-- //sub title and more -->
			<c:choose>
				<c:when test="${totalSearchCount > 0}">
					<!-- search view -->
					<dl class="search_view_list">
					<c:forEach var="list" items="${resultList}" varStatus="status">
					<dt>
					<a href="javascript:Menu.detail2('frm', 'frTop', '<c:out value='${list.lf_up_menu_id}' escapeXml="false" />', '<c:out value='${list.lf_menu_id}' escapeXml="false" />',  '<c:out value='${list.link_url}' escapeXml="false" />');"><c:out value='${list.dretitle}' escapeXml="false" /></a>
					</dt>
					<c:if test="${list.dretitle2 != null}">
						<dd class="view_text">
							(<c:out value='${list.dretitle2}' escapeXml="false" />)
						</dd>
					</c:if>
					<dd class="view_text">
					<c:out value='${list.drecontent}' escapeXml="false" />
					</dd>
					<dd class="date_name">
						(<spring:message code="clm.page.field.srch.createDate" /> : <c:out value='${list.dredate}'/><c:if test="${writerText != null}"> | <spring:message code="${writerText}" /> : <c:out value='${list.if_writer}'/></c:if>)
					</dd>
					</c:forEach>
					</dl>
					<!-- //search view -->
					<!-- pagination  -->
					<div class="pagination">
					<%=pageUtil.getPageNavi(pageUtil, "") %>
					</div>
					<!-- //pagination -->
				</c:when>
				<c:otherwise>
				<!-- search view -->
				<dl class="search_view_list">
				<dt style="text-align:center"><img src="<%=IMAGE%>/common/nosearch.jpg"/></dt>
				</dl>
				</br></br></br></br></br></br></br></br></br></br></br>
				<!-- //search view -->
				</c:otherwise>
			</c:choose>		
		</div>
		</div>
		<!-- //content -->
		
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
		</form:form>
	</div>
</div>

<!-- footer  -->
<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
<!-- // footer -->
</body>
</html>