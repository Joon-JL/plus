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
 * 파  일  명 : ManageChange_l.jsp
 * 프로그램명 : 계약서 담당자변경목록
 * 설      명 : 
 * 작  성  자 : 박정훈
 * 작  성  일 : 2013.04.16
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
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet"/>
<!-- [if IE]><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
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
		
		
	 	initCal("srch_start_reqday");   //첫번재 의뢰일 설정
	    initCal("srch_end_reqday");     //두번재 의뢰일 설정
	 	initCal("srch_start_cnlsnday");   //첫번재 체결일 설정
	    initCal("srch_end_cnlsnday");     //두번재 체결일 설정
	    initCal("srch_str_org_acptday");   //첫번재 원본접수일 설정
        initCal("srch_end_org_acptday");     //두번재 원본접수일 설정
	    

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
	    frm.method.value = "listChange";
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
		frm.method.value = "listChange";
		frm.curPage.value = pgNum;
		
		frm.submit();
	}
	
	/**
	* 관리페이지 이동
	*/	
	function detailAction(cnsdreq_id, cntrt_id, prcsDepth){
		var frm = document.frm;
		var dmdGbn = "<c:out value='${command.srch_demand_gbn}'/>";
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/manage/request.do' />";
		
		frm.p_demnd_gbn.value = dmdGbn;
		
		//if("<c:out value='${command.list_mode}'/>" == "cnsdreq" && prcsDepth != "C02501"){ // 의뢰별
		//	alert("계약검토 이후에는 계약별 리스트에서 진행할 수 있습니다.");
		//	return;
		//}else if("<c:out value='${command.list_mode}'/>" == "contract" && prcsDepth == "C02501"){ // 계약별
		//	alert("계약검토 단계는 의뢰별리스트에서 진행할 수 있습니다.");
		//	return;
		//}
		
		//2012-05-17 
		//검토의뢰,체결단계 -> 의뢰자, 계약담당자 둘다 변경, 의뢰별 리스트에서만 처리가능
		//체결이후 단계 -> 계약담당자만 변경, 계약별 리스트에서만 처리가능
		//if("<c:out value='${command.list_mode}'/>" == "cnsdreq" && (prcsDepth == "C02503" || prcsDepth == "C02504" || prcsDepth == "C02505")){ //의뢰별
		//	alert("계약검토 및 계약체결 이후단계는 계약별 리스트에서 진행할 수 있습니다.");
		//	return;
		//}else if("<c:out value='${command.list_mode}'/>" == "contract" && (prcsDepth == "C02501" || prcsDepth == "C02502")){ //계약별
		//	alert("계약검토 단계 및 계약체결 단계는 의뢰별 리스트에서 진행할 수 있습니다.");
		//	return;			
		//}
		
		//if("<c:out value='${command.list_mode}'/>" == "cnsdreq"){
			frm.cnsdreq_id.value = cnsdreq_id;
		//}else{
			frm.cntrt_id.value = cntrt_id;
		//}
		
		frm.p_prcs_depth.value = prcsDepth;
		frm.method.value = "forwardModifyChange";
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
			<h3><spring:message code="clm.page.msg.manage.chgContChrg" /></h3>
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
				<input type="hidden" name="status_mode"   value="<c:out value='${command.status_mode}'/>" />
			
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
			<!-- tab
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
								<table class="search_form" style='min-width:700px;'>
									<colgroup>
                                        <col width="*"/>
                                        <col width="*"/>
                                        <col width="*"/>
                                        <col width="*"/>
                                        <col width="*"/>
                                        <col width="*"/>
                                    </colgroup>
									<tr>
										<th><spring:message code="clm.page.field.manageCommon.srchReqTitle" /></th>
										<td>
											<input class="text" style="width:179px;" type="text" name="srch_review_title"  id="srch_review_title" value="<c:out value='${command.srch_review_title}'/>" />
										</td>
										<th><spring:message code="clm.page.field.manageCommon.srchCntrtNm" /></th>
                                        <td>
                                            <input class="text" style="width:179px;" type="text" name="srch_cntrt_nm"  id="srch_cntrt_nm" value="<c:out value='${command.srch_cntrt_nm}'/>"/>
                                        </td>
                                        <th><spring:message code="clm.page.field.manageCommon.srchCntrtId"/></th>
                                        <td>
                                            <input class="text" style="width:179px;" type="text" name="srch_cntrt_no"  id="srch_cntrt_no" value="<c:out value='${command.srch_cntrt_no}'/>"/>
                                        </td>
									</tr>
									<tr>
									   <th><spring:message code="clm.page.field.manageCommon.srchReqmanNm" /></th>
                                        <td>
                                            <input class="text" style="width:179px;" type="text" name="srch_reqman_nm" id="srch_reqman_nm" value="<c:out value='${command.srch_reqman_nm}'/>"/>
                                        </td>
                                        <%--
                                        <th><spring:message code="clm.page.field.manageCommon.srchRespmanNm" /></th>
                                        <td>
                                            <input class="text" style="width:200px;" type="text" name="srch_respman_nm" id="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}'/>" />
                                        </td>
                                         --%>
                                        <th><spring:message code="clm.page.field.manageCommon.srchCnsdmanNm" /></th>
                                        <td>
                                            <input class="text" style="width:179px;" type="text" name="srch_cnsdman_nm" id="srch_cnsdman_nm" value="<c:out value='${command.srch_cnsdman_nm}'/>" />
                                        </td>
                                        <th><spring:message code="clm.page.field.manageCommon.srchOppntCd" /></th>
										<td>
											<input class="text" style="width:163px;"  type="text" name="srch_oppnt_nm" id="srch_oppnt_nm" value="<c:out value='${command.srch_oppnt_nm}'/>" onKeypress="if(event.keyCode == 13){popOppnt();return false;}"/><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search"  onclick="popOppnt()" style="cursor:pointer; margin-left:-1px"/>
										</td>	
										<%--
                                        <th><spring:message code="clm.page.field.manageCommon.srchCnclsnpurpsBigclsfcn" /></th>
                                        <td>
                                            <select class="srch" name="srch_cnclsnpurps_bigclsfcn" id="srch_cnclsnpurps_bigclsfcn" style='width:200px;'>
                                            </select>   
                                        </td>
                                         --%>                                        
									</tr>
									<%--
									<tr>
									   <th><spring:message code="clm.page.field.manageCommon.srchRespDept" /></th>
                                        <td>
                                            <input class="text" style="width:183px" type="text" name="srch_resp_dept_nm" id="srch_resp_dept_nm" value="<c:out value='${command.srch_resp_dept_nm}'/>" onkeypress="if(event.keyCode == 13){popDept();return false;}"/><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search" onclick="popDept()" style="cursor:pointer;margin-left:-1px" />
                                        </td>
                                        <th><spring:message code="clm.page.field.manageCommon.srchBizclsfcn" /></th>
                                        <td>
                                            <select class="srch" name="srch_biz_clsfcn" id="srch_biz_clsfcn" style='width:200px;'>
                                            </select>   
                                        </td>
                                        <th><spring:message code="clm.page.field.manageCommon.srchCnclsnpurpsBigclsfcn" /></th>
                                        <td>
                                            <select class="srch" name="srch_cnclsnpurps_bigclsfcn" id="srch_cnclsnpurps_bigclsfcn" style='width:200px;'>
                                            </select>   
                                        </td>
									</tr>
									 --%>
									<tr>
									   <th><spring:message code="clm.page.field.manageCommon.srchReqDay" /></th>
                                        <td>
                                            <input type="text" name="srch_start_reqday" id="srch_start_reqday" value="<c:out value='${command.srch_start_reqday}'/>"  class="text_calendar" />
                                            ~
                                            <input type="text" name="srch_end_reqday" id="srch_end_reqday" value="<c:out value='${command.srch_end_reqday}'/>" class="text_calendar" />
                                        </td>
                                       
                                       <th><spring:message code="clm.page.field.manageCommon.srchCnlsnday" /></th>
                                        <td>
                                            <input type="text" name="srch_start_cnlsnday" id="srch_start_cnlsnday" value="<c:out value='${command.srch_start_cnlsnday}'/>"  class="text_calendar" />
                                            ~
                                            <input type="text" name="srch_end_cnlsnday" id="srch_end_cnlsnday" value="<c:out value='${command.srch_end_cnlsnday}'/>" class="text_calendar" />
                                        </td>
                                        
                                        <th><spring:message code="clm.page.field.contract.conclusion.detail.conclusionorgacceptday" /></th>
                                        <td colspan="3">
                                            <input type="text" name="srch_str_org_acptday" id="srch_str_org_acptday" value="<c:out value='${command.srch_str_org_acptday}'/>"  class="text_calendar" />
                                            ~
                                            <input type="text" name="srch_end_org_acptday" id="srch_end_org_acptday" value="<c:out value='${command.srch_end_org_acptday}'/>" class="text_calendar" />
                                            
                                        </td>
                                     </tr>
                                     <tr>   
									   <th><spring:message code="clm.page.field.manageCommon.srchStep" /> / <spring:message code="clm.page.field.manageCommon.srchStatus" /></th>
                                        <td>
                                            <select class="srch" name="srch_step" id="srch_step" value="<c:out value='${command.srch_step}'/>" style='width:85px'>
                                            </select>
                                            /           
                                            <select class="srch" name="srch_state"  id="srch_state" style='width:84px;'>
                                            </select>
                                        </td>       
                                        <td></td>
                                        <td></td>
                                        <td></td>
										<td class="tR">
											<a href="javascript:searchAction('');"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="clm.page.msg.common.inquire" htmlEscape="true" />" style='margin-right:9%' class='mt5'/></a>
										</td>								
									</tr>
								</table>
							
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
		    <style>.list_basic td, .list_basic th {padding:4px}</style>
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic">

				<colgroup>
					<col width="*" />
					<col width="9%" />
					<col width="13%" />
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
						<th title="<spring:message code="las.page.field.contractmanager.consideration.first"/> <spring:message code="clm.page.field.manageCommon.reqDay" />&#13;(<spring:message code="clm.page.field.contract.conclusion.detail.conclusionorgacceptday" />)">
						  
						    <div class='overflow tC'><spring:message code="las.page.field.contractmanager.consideration.first"/> <spring:message code="clm.page.field.manageCommon.reqDay" /></div>
						    <div class='overflow tC'>(<spring:message code="clm.page.field.contract.conclusion.detail.conclusionorgacceptday" />)</div>
						  
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
				    <tr  <%if(idx == resultList.size()-1){%>class="end"<%}%> onmouseout="this.className='<%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onmouseover="this.className='on <%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onclick="javascript:detailAction('<%=lom.get("cnsdreq_id") %>', '<%=lom.get("cntrt_id") %>', '<%=lom.get("prcs_depth")%>');">
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
				      <td class="txtover tC" title="<%=StringUtil.replace((String)lom.get("cnsdmans"),",","&#13;") %>"><%=lom.get("cnsdmans") %></td>
				      <td class="txtover tC" title="<%=lom.get("cntrt_no") %>"><%=lom.get("cntrt_no") %></td>
				      <td class="txtover tC" title="<%=lom.get("prcs_depth_nm") %>"><%=lom.get("prcs_depth_nm") %></td>
				      <td class="txtover tC" title="<%=lom.get("depth_status_nm") %>"><%=lom.get("depth_status_nm") %></td>
			        </tr>
		      <%	}
				}else{		  
 			  %>
					<tr class="end">
					  <td colspan="9" align="center"><spring:message code="secfw.msg.succ.noResult" /></td>
					</tr>
			  <%}%>			
			  </tbody>
			</table>
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

