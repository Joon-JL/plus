<!DOCTYPE html>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
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
 * 파  일  명 : RelMMContract_l.jsp
 * 프로그램명 : 연관계약목록
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2012.03.14
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

<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet" />
<link href="<%=CSS%>/popup.css" type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->

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
		getCodeSelectByUpCd("frm", "srch_step", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=C025&useman_mng_itm2=clmsList&useman_mng_itm3=relList&combo_type=T&combo_del_yn=N&combo_selected='+'<c:out value='${command.srch_step}'/>');
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
	    initCal("srch_str_org_acptday");   //첫번재 원본접수일 설정
        initCal("srch_end_org_acptday");     //두번재 원본접수일 설정
        
        document.frm.except_cntrt_id.value = opener.frm.cntrt_id.value;
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
		$("#srch_cntrt_no").keydown(function(event) {
            if(event.keyCode == "13") {
                searchAction('');
            }
        });
        
        $("#srch_cntrt_nm").keydown(function(event) {
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
			
			var options = {
					url: "<c:url value='/clm/manage/contract.do?method=getCntrtHTML' />",
					type: "post",
					success: function(responseText,returnMessage) {
						$('#cntrtLayer').html("");
						$('#cntrtLayer').html(responseText);
					}
			};
			$("#frm").ajaxSubmit(options);		

			$("#cntrtLayer").attr('style', 'position:absolute; left:'+left+'px; top:'+top+'px; z-index:1; display:;');
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
		//frm.action = "<c:url value='/clm/manage/myContract.do' />";
		frm.action = "<c:url value='/clm/manage/myContractPop.do?method=listMyContract&status_mode=rel' />";
	    //frm.method.value = "listMyContract";
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
		//frm.action = "<c:url value='/clm/manage/myContract.do' />";
		//frm.method.value = "listMyContract";
		frm.action = "<c:url value='/clm/manage/myContractPop.do?method=listMyContract&status_mode=rel' />";
		frm.curPage.value = pgNum;
		
		frm.submit();
	}
	

	/**
	* 관리페이지 이동
	*/	
	function detailAction(cnsdreq_id, prcs){
		
		PopUpWindowOpen('/clm/manage/completion.do?method=relatedContractDetail&menu_id=20130319154642301_0000000355&conListGu=Z1000&cnsdreq_id='+cnsdreq_id, 900, 600, true,"detailPopUp2");
	}

	/**
	*	계약상대방 팝업
	*/
	function popOppnt(){
		var frm = document.frm;
		 
		//PopUpWindowOpen('', 900, 600, true);
		PopUpWindowOpen('', 900, 600, true, "PopUpRelOppntSearch");
		frm.target = "PopUpRelOppntSearch";
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
		 
		 //PopUpWindowOpen('', 640, 640, true);
		 PopUpWindowOpen('', 640, 640, true, "PopUpRelDeptSearch");
		 
		 frm.target = "PopUpRelDeptSearch";
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
		var frm = document.frm;
		
		//PopUpWindowOpen('', 750, 720, true);
		PopUpWindowOpen('', 750, 720, true, "PopUpRelDetailSearch");
		frm.target = "PopUpRelDetailSearch";
		frm.action = "<c:url value='/clm/manage/myContract.do' />";
		frm.method.value = "detailTypePopup";
		frm.submit();
    }
	
	/**
	* 상세 검색 팝업에서 리턴 받기
	*/
	function returnDetailSrh(obj){
		
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
		$('#srch_str_org_acptday').val(obj.srch_str_org_acptday); 
		$('#srch_end_org_acptday').val(obj.srch_end_org_acptday); 
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
	
	// 부모 창으로 내용을 전달 함.
	function parentContract(id, name, biz, sArg) {
		
		var reason = "";
		
		opener.setContract(id,name,biz,sArg,reason);
		// 2014-07-15 Kevin added.
		self.close();
	}
	
	/**
	* 취소
	*/
	function cancle(){
		if(confirm("<spring:message code='secfw.msg.ask.cancel' />")){
			opener.setContract('','','','');
			self.close();
		}
	}
	
	/**
	* 확인
	*/
	function con(){
		
        var frm = document.frm;
		
		var ch_Click = $("input[name=noContract]:checked").val();
		
		if("Y" == ch_Click){
			
			$("input:radio[name='check_item']").removeAttr("checked");
			
			var id = "20140806170146519";  // 개발
			var biz = "";
			var sArg = "";
			
			var name = "No Contract Master";
			
			if("" == frm.NoMasterReason.value){
				alert("Must be less reason there is no master Contract.");
				return;
			} else {
				var reason = frm.NoMasterReason.value;
			}
			
			opener.setContract(id,name,biz,sArg,reason);
			
			window.close();
			
		} else {
			if(confirm("<spring:message code='clm.page.msg.manage.announce107' />")){
				window.close();
			}
		}
	}
	
	// 상세 페이지 팝업오픈
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
	
</script>
</head>

<body onload='showMessage("<c:out value='${returnMessage}'/>");' class="pop">

<!-- header -->
<h1><spring:message code="clm.page.msg.manage.relContInqu" /></h1>
<!-- //header -->
<div class="pop_area">
	<!-- content -->
	<div class="pop_content"  style='height:745px'>
	
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
	<input type="hidden" name="status_mode" value="rel" />

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
	<input type="hidden" name="srch_auto_rnew_yn"        id="srch_auto_rnew_yn"        value="<c:out value='${command.srch_auto_rnew_yn}'/>" />
	<input type="hidden" name="srch_sSeal_mthd"          id="srch_sSeal_mthd"          value="<c:out value='${command.srch_sSeal_mthd}'/>" />
	<input type="hidden" name="srch_signman_nm"          id="srch_signman_nm"          value="<c:out value='${command.srch_signman_nm}'/>" />
	<input type="hidden" name="srch_sLoac"               id="srch_sLoac"               value="<c:out value='${command.srch_sLoac}'/>" />
	<input type="hidden" name="srch_sDesp_resolt_mthd"   id="srch_sDesp_resolt_mthd"   value="<c:out value='${command.srch_sDesp_resolt_mthd}'/>" />
	
	<%-- Parent Cntrt_id(자신의 계약건은 조회에서 제외시킴) --%>
	<input type="hidden" name="except_cntrt_id" />
<!-- // **************************** Form Setting **************************** -->

			<!-- search-->		    
			<div class="search_box">
				<div class="search_box_inner">
					<table class="search_tb">
										
						<tr>
							<td>
							<!-- 의뢰 기준 조회일 경우 -->
					
								<table class="search_form" style='min-width:700px;'>
									<colgroup>
                                        <col width="*%"/>
                                        <col width="203px"/>
                                        <col width="*%"/>
                                        <col width="203px"/>
                                        <col width="*%"/>
                                        <col width="203px"/>
                                    </colgroup>
									<tr>
										<th><spring:message code="clm.page.field.manageCommon.srchReqTitle" /></th>
										<td>
											<input class="text" style="width:181px" type="text" name="srch_review_title"  id="srch_review_title" value="<c:out value='${command.srch_review_title}'/>" />
										</td>
										<th><spring:message code="clm.page.field.manageCommon.srchCntrtNm" /></th>
                                        <td>
                                            <input class="text" style="width:181px" type="text" name="srch_cntrt_nm"  id="srch_cntrt_nm" value="<c:out value='${command.srch_cntrt_nm}'/>"/>
                                        </td>
                                        <th><spring:message code="clm.page.field.manageCommon.srchCntrtId"/></th>
                                        <td>
                                            <input class="text" style="width:181px" type="text" name="srch_cntrt_no"  id="srch_cntrt_no" value="<c:out value='${command.srch_cntrt_no}'/>"/>
                                        </td>
									</tr>
									<tr>
									    <th><spring:message code="clm.page.field.manageCommon.srchReqmanNm" /></th>
                                        <td>
                                            <input class="text" style="width:181px" type="text" name="srch_reqman_nm" id="srch_reqman_nm" value="<c:out value='${command.srch_reqman_nm}'/>"/>
                                        </td>
<%--                                         <th><spring:message code="clm.page.field.manageCommon.srchRespmanNm" /></th> --%>
<!--                                         <td> -->
<%--                                             <input class="text" style="width:181px" type="text" name="srch_respman_nm" id="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}'/>" /> --%>
<!--                                         </td> -->                                        
                                        <th><spring:message code="clm.page.field.manageCommon.srchCnsdmanNm" /></th>
                                        <td>
                                            <input class="text" style="width:181px" type="text" name="srch_cnsdman_nm" id="srch_cnsdman_nm" value="<c:out value='${command.srch_cnsdman_nm}'/>" />
                                        </td>
                                        <th><spring:message code="clm.page.field.manageCommon.srchOppntCd" /></th>
										<td>
											<input class="text" style="width:165px" type="text" name="srch_oppnt_nm" id="srch_oppnt_nm" value="<c:out value='${command.srch_oppnt_nm}'/>" onKeypress="if(event.keyCode == 13){popOppnt();return false;}"/><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search"  onclick="popOppnt()" style="cursor:pointer; margin-left:-1px"/>
										</td>
										
										
									</tr>
<!-- 									<tr> -->
<%-- 									   <th><spring:message code="clm.page.field.manageCommon.srchRespDept" /></th> --%>
<!--                                         <td> -->
<%--                                             <input class="text" style="width:165px" type="text" name="srch_resp_dept_nm" id="srch_resp_dept_nm" value="<c:out value='${command.srch_resp_dept_nm}'/>" onKeypress="if(event.keyCode == 13){popDept();return false;}"/><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search" onclick="popDept()" style="cursor:pointer;margin-left:-1px" /> --%>
<!--                                         </td> -->
<%--                                         <th><spring:message code="clm.page.field.manageCommon.srchBizclsfcn" /></th> --%>
<!--                                         <td> -->
<!--                                             <select class="srch" name="srch_biz_clsfcn" id="srch_biz_clsfcn" style='width:184px'> -->
<!--                                             </select>    -->
<!--                                         </td> -->
<%--                                         <th><spring:message code="clm.page.field.manageCommon.srchCnclsnpurpsBigclsfcn" /></th> --%>
<!--                                         <td> -->
<!--                                             <select class="srch" name="srch_cnclsnpurps_bigclsfcn" id="srch_cnclsnpurps_bigclsfcn" style='width:184px'> -->
<!--                                             </select>    -->
<!--                                         </td> -->
<!-- 									</tr> -->
									<tr>
									   <th><spring:message code="clm.page.field.manageCommon.srchReqDay" /></th>
                                        <td>
                                            <input type="text" name="srch_start_reqday" id="srch_start_reqday" value="<c:out value='${command.srch_start_reqday}'/>"  class="text_calendar" style='width:64px'/>
                                            ~
                                            <input type="text" name="srch_end_reqday" id="srch_end_reqday" value="<c:out value='${command.srch_end_reqday}'/>" class="text_calendar" style='width:64px'/>
                                        </td>
                                       
                                       <th><spring:message code="clm.page.field.manageCommon.srchCnlsnday" /></th>
                                        <td>
                                            <input type="text" name="srch_start_cnlsnday" id="srch_start_cnlsnday" value="<c:out value='${command.srch_start_cnlsnday}'/>"  class="text_calendar" style='width:64px'/>
                                            ~
                                            <input type="text" name="srch_end_cnlsnday" id="srch_end_cnlsnday" value="<c:out value='${command.srch_end_cnlsnday}'/>" class="text_calendar" style='width:64px'/>
                                        </td>
                                        
                                        <th><spring:message code="clm.page.field.contract.conclusion.detail.conclusionorgacceptday" /></th>
                                        <td>
                                            <input type="text" name="srch_str_org_acptday" id="srch_str_org_acptday" value="<c:out value='${command.srch_str_org_acptday}'/>"  class="text_calendar" style='width:64px'/>
                                            ~
                                            <input type="text" name="srch_end_org_acptday" id="srch_end_org_acptday" value="<c:out value='${command.srch_end_org_acptday}'/>" class="text_calendar" style='width:64px'/>
                                            
                                        </td>
                                     </tr>
                                     <tr>   
									 	<th><spring:message code="clm.page.field.manageCommon.srchStep" /> / <spring:message code="clm.page.field.manageCommon.srchStatus" /></th>
                                        <td>
                                            <select style='width:86px' class="srch" name="srch_step" id="srch_step" value="<c:out value='${command.srch_step}'/>">
                                            </select>
                                            /           
                                            <select style='width:86px' class="srch" name="srch_state"  id="srch_state">
                                            </select>
                                        </td> 
                                        <td colspan='4' class='tR'><a href="javascript:searchAction('');"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="clm.page.msg.common.search" htmlEscape="true" />"  style='margin-right:11px;'/></a>  </td>
<%-- 										<th><spring:message code="clm.page.field.manageCommon.srchOppntCd" /></th> --%>
<!-- 										<td> -->
<%-- 											<input class="text" style="width:165px" type="text" name="srch_oppnt_nm" id="srch_oppnt_nm" value="<c:out value='${command.srch_oppnt_nm}'/>" onKeypress="if(event.keyCode == 13){popOppnt();return false;}"/><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search"  onclick="popOppnt()" style="cursor:pointer; margin-left:-1px"/> --%>
<!-- 										</td> -->
<!-- 										<td></td> -->
<%-- 										<td><a style='margin-left:122px;' href="javascript:searchAction('');"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="clm.page.msg.common.search" htmlEscape="true" />"/></a></td> --%>
                                    </tr>
								</table>						
							</td>
						
						</tr>
					</table>
				</div>
			</div>
			<!--//search-->
			<!-- list -->
		    <div style='text-align:left; width:100%; vertical-align:middle; margin:5px 2px 3px 0; line-height:150%'><input name="noContract" id="noContract" type="checkbox" value='Y'/> Absence of Legal(Master) Contract &nbsp;&nbsp;&nbsp;※ Reason of Absence : <input type="text" name="NoMasterReason" maxlength="100" class='text' style='width:60%; margin-bottom:2px'/></div>
		    <style>.list_basic td, .list_basic th {padding:4px}</style>
		    <div class='tableWrap2 mt20'>
		    <div class='tableone'>
		    <table border="0" cellspacing="0" cellpadding="0" class="list_basic">

				<colgroup>
				    <col width="46px" />
					<col width="*%" />
					<col width="10%" />
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
					    <th><spring:message code="clm.page.msg.common.select" /></th>
						<th><spring:message code="clm.page.field.manageCommon.reqTitle" /><br>(<spring:message code="clm.page.field.manageCommon.srchCntrtNm" />)</br></th>
						<th title="<spring:message code="clm.page.msg.manage.announce032" htmlEscape="true" />"><spring:message code="clm.page.field.manageCommon.reqmanNm" /><br/><spring:message code="clm.page.field.manageCommon.respmanNm" /></th>
						<th class='overflow' title='<spring:message code="las.page.field.contractmanager.consideration.first"/><spring:message code="clm.page.field.manageCommon.reqDay" />&#13;(<spring:message code="clm.page.field.contract.conclusion.detail.conclusionorgacceptday" />)'>
							<spring:message code="las.page.field.contractmanager.consideration.first"/> <spring:message code="clm.page.field.manageCommon.reqDay" /><br/>
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
			</table>
			</div>
			</div>
			 <div class='tabletwo' style='height:232px'>
			 <table class='list_scr'>
			 	<colgroup>
				    <col width="46px" />
					<col width="*%" />
					<col width="10%" />
					<col width="13%" />
					<col width="9%" />
					<col width="9%" />
					<col width="7%" />
					<col width="9%" />
					<col width="70px" />
					<col width="95px" />
			  	</colgroup>
			 	<tbody>
			  <%
				if(pageUtil.getTotalRow() > 0) {
					for(int idx=0; idx<resultList.size(); idx++) {
						ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
				%>			
				    <tr  <%if(idx == resultList.size()-1){%>class="end"<%}%> onmouseout="this.className='<%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onmouseover="this.className='on <%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'">
				      <td class='tC'> 
				      		<input type="radio" name="check_item" value="<%=lom.get("cntrt_id") %>" onclick="javascript:parentContract('<%=lom.get("cntrt_id") %>','<%= StringEscapeUtils.escapeJavaScript (StringUtils.trimToEmpty( (String)  lom.get("req_title")))  %>','<%=lom.get("biz_clsfcn") %>','<c:out value='${command.arg}'/>');" />
				      </td>
					      <td class="tL overflow" style="line-height:150%; border-left:1px solid #CADBE2;" title="<%=StringUtil.convertHtmlTochars((String)lom.get("req_title")) %>&#13(<%=StringUtil.convertHtmlTochars((String)lom.get("cntrt_nm")) %>)">
					        <span style='color:#485B91' onclick="javascript:detailAction('<%=lom.get("cnsdreq_id") %>', '<%=lom.get("prcs_depth")%>');"><%=StringUtil.convertHtmlTochars((String)lom.get("req_title")) %></span><br/>
					        <span style='color:#A9B4BC'>(<%=StringUtil.convertHtmlTochars((String)lom.get("cntrt_nm")) %>)</span>
					      </td>
					      <td class="overflow tC" title="<%=lom.get("reqman_nm") %>">
					        <%=lom.get("reqman_nm") %>
					        <%=lom.get("cntrt_respman_nm")!=null && !lom.get("cntrt_respman_nm").equals("")? "<br>" + lom.get("cntrt_respman_nm") : "" %>
					      </td>
					      <td class="overflow tC" style='text-align:center'>
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
					  <td colspan="10" class='tC'><spring:message code="secfw.msg.succ.noResult" /></td>
					</tr>
			  <%}%>			
			  </tbody>
			</table>
			</div>
			
			<!-- //list -->

		
				<!-- total data -->
				<div class="total_num">
				Total : <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%>
				</div>
				<!-- //total data -->
				
				<!-- pagination  -->
				<div class="pagination mt_22">
				<%=pageUtil.getPageNavi(pageUtil, "") %>
				</div>
				<!-- //pagination -->
		
			
			
					
	</div>
	<!-- //content -->
</div>



<!--footer -->
<div class="pop_footer">
	<div class="btn_wrap fR" >
		<span class="btn_all_w"><span class="confirm"></span><a href="javascript:con()"><spring:message code='clm.page.button.confirm' /></a></span>
	    <span class="btn_all_w"><span class="cancel"></span><a href="javascript:cancle()"><spring:message code='clm.page.button.cancel' /></a></span>
	</div>
</div>
<!-- //footer -->
			
<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>


</form:form>
</body>
</html>