<!DOCTYPE html>
<html><!-- Consideration_l.jsp -->
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

<%--
/**
 * 파  일  명 : Consideration_l.jsp
 * 프로그램명 : 의뢰내역 - 검토의뢰 목록
 * 설      명 : 
 * 작  성  자 : 한지훈
 * 작  성  일 : 2011.09.08
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	
	boolean authYN_RA02 = false;
	
	ArrayList tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.roleList");
		
	if(tmpSessionRoleList.contains("RA02")){
		authYN_RA02 = true;
	}
	
%>
<head>
<meta sci="Consideration_l.jsp" />
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css"    type="text/css" rel="stylesheet">
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script language="javascript">

$(document).ready(function(){
    //의뢰상태
	getCodeSelectByUpCd("frm", "srch_prgrs_status", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=A&combo_grp_cd=C042&combo_type=S&combo_del_yn=N&useman_mng_itm2=REQ&combo_selected='+'<c:out value='${command.srch_prgrs_status}'/>');
    //법무상태
	getCodeSelectByUpCd("frm", "srch_law_status", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=A&combo_grp_cd=C042&combo_type=S&combo_del_yn=N&useman_mng_itm3=REQ&combo_selected='+'<c:out value='${command.srch_law_status}'/>');
	//비즈니스단계
	getCodeSelectByUpCd("frm", "srch_biz_depth", '/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=T01&combo_abbr=F&combo_type=S&combo_del_yn=N&combo_selected='+'<c:out value='${command.srch_biz_depth}'/>');
	//체결목적
// 	getCodeSelectByUpCd("frm", "srch_cnclsnpurps", '/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=T03&combo_abbr=F&combo_type=S&combo_del_yn=N&combo_selected='+'<c:out value='${command.srch_cnclsnpurps}'/>');
	//회사선택	
	getCodeSelectByUpCd("frm", "sElComp", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=USERCOMP&combo_type=T&combo_del_yn=N&sel_grd=<c:out value='${command.sSel_grd}'/>&combo_selected='+'<c:out value='${command.sElComp}'/>');
	
	initCal("srch_start_dt");   //첫번재 날짜창 설정 : 변수는 해당 태그의 id값
    initCal("srch_end_dt");     //두번재 날짜창 설정 : 변수는 해당 태그의 id값
    // 2012.04.17 내가할일 Count 조회 added by hanjihoon
    // 2014-02-10 Kevin. TO-DO 리스트의 값 로드가 중복으로 발생한다. 페이지 로딩 시간이 오래 걸리는 상황에서는 응답시간 지연이 발생하여 주석처리 함. 데이터 Refresh는 메뉴 클릭으로 대신한다.
    //window.parent.frames['leftFrame'].refreshCount();
});

$(function() {
	$("#allCheck")
	.click(function() {
		if(($(this).attr("checked"))) {			
			$("input:checkbox[name='check_item']")
				.each(function(){
					$(this).attr("checked", true);
				});
		} else {
			$("input:checkbox[name='check_item']")
			.each(function(){
				$(this).attr("checked", false);
			});
		}
	});

	$("#srch_reqman_nm").keydown(function(event){
		$("#srch_reqman_id").val('');
		if(event.keyCode == "13") {
			popReqman();
			$("#srch_reqman_nm").val('');
			return false;			
		}
	});

	$("#srch_respman_nm").keydown(function(event){
		$("#srch_respman_id").val('');
		if(event.keyCode == "13") {
			popRespman();
			$("#srch_respman_nm").val('');
			return false;
		}
	});
	
	$("#srch_cntrt_oppnt_nm").keydown(function(event){
		if(event.keyCode == "13") {
			search();
		}
	});
	
	$("#srch_req_title").keydown(function(event){
		if(event.keyCode == "13") {
			search();
		}
	});

	$("#srch_cnsd_cont").keydown(function(event){
		if(event.keyCode == "13") {
			search();
		}
	});
});
	
	/**
	* 모달팝업
	
	function PopUpWindowModalOpen(surl, popupwidth, popupheight, bScroll, obj){
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
	
	    //Future = "fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=" + (bScroll ? "yes" : "no") + ",resizable=no,left=" + Left + ",top=" + Top + ",width=" + popupwidth + ",height=" + popupheight;
		var sFeatures = "dialogWidth:" + popupwidth +"px;dialogHeight:" + popupheight+"px;scroll:" + (bScroll ? "on":"off")+ ";status:0ff;resizable:no;";
	    window.showModalDialog(surl, obj, sFeatures);
	
	}
	
	function changeSubCd(selObjId, gbn, upCd) {
	    if(upCd == "") {
	        upCd = "XXX";
	    }
	    getCodeSelectByUpCd('frm', selObjId, '/common/clmsCom.do?method=getComboHTML&combo_gbn=' + gbn + '&combo_up_cd=' + upCd + '&combo_abbr=F&combo_type=T&combo_del_yn=N&combo_selected=');
	}*/
	
	/**
	* 계약유형선택에서 사용할 오브젝트 초기화
	function retObj(biz_clsfcn, biz_clsfcn_nm,  depth_clsfcn, depth_clsfcn_nm, cnclsnpurps_bigclsfcn, cnclsnpurps_bigclsfcn_nm,cnclsnpurps_midclsfcn,cnclsnpurps_midclsfcn_nm){
	    this.biz_clsfcn        = biz_clsfcn;
	    this.biz_clsfcn_nm     = biz_clsfcn_nm;
	    this.depth_clsfcn      = depth_clsfcn;
	    this.depth_clsfcn_nm   = depth_clsfcn_nm;
	    this.cnclsnpurps_bigclsfcn    = cnclsnpurps_bigclsfcn;
	    this.cnclsnpurps_bigclsfcn_nm = cnclsnpurps_bigclsfcn_nm;
	    this.cnclsnpurps_midclsfcn = cnclsnpurps_midclsfcn;
	    this.cnclsnpurps_midclsfcn_nm = cnclsnpurps_midclsfcn_nm;
	}
	*/
	
	/**
	* 모달팝업
	function popModalOpen(surl, popupwidth, popupheight, bScroll, obj){
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
	
	    //Future = "fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=" + (bScroll ? "yes" : "no") + ",resizable=no,left=" + Left + ",top=" + Top + ",width=" + popupwidth + ",height=" + popupheight;
		var sFeatures = "dialogWidth:" + popupwidth +"px;dialogHeight:" + popupheight+"px;scroll:" + (bScroll ? "on":"off")+ ";status:0ff;resizable:no;";
	    window.showModalDialog(surl, obj, sFeatures);
	
	}
	*/
	
	/**
	* 기본 검색
	*/
	function search(){
		if($("#srch_start_dt").val() > $("#srch_end_dt").val()){
			alert("<spring:message code='clm.msg.alert.board.srchAnnceDt'/>");//종료일이 시작일 보다 이전일 수 없습니다.
			$("#srch_start_dt").focus();
			return;
		}
		$("#page_flag").val("3");
		goPage(1);
	}
	
	/**
	* 검색
	*/
	function goPage(pgNum) { 
		var frm = document.frm;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/review/consideration.do' />";
	    frm.method.value = "listConsideration";
	    frm.curPage.value = pgNum;
	    viewHiddenProgress(true);
	    frm.submit();
	}

	/**
	*검토의뢰건 상세 보기 
	*/
	function goDetail(cnsdreq_id, cntrt_oppnt_nm){
		var frm = document.frm;	
		//cnsdreq_id
		frm.cnsdreq_id.value = cnsdreq_id;				// 검토의뢰ID
		frm.cntrt_oppnt_nm.value = cntrt_oppnt_nm;		// 상대회사명

		//if(plndbn_req_yn == "Y"){
		//	frm.method.value = "detailConsiderationPlndbn";	
		//}else{
			frm.method.value = "detailConsideration";
		//}
		frm.action = "<c:url value='/clm/review/consideration.do' />";	
		frm.target = "_self";
		viewHiddenProgress(true);
		frm.submit();		
	}	
	
	/**
	* 의뢰자검색
	*/
	function popReqman() {
		var frm = document.frm;
		var srchValue = comTrim($("#srch_reqman_nm").val());
    	$("#srch_man_div").val("REQ");
    	
        if (srchValue == "" || getByteLength(srchValue) < 4) { 	//최소 2자리 이상 입력받게 한다.
            alert('<spring:message code='secfw.msg.error.nameMinByte' />'); 
            frm.srch_reqman_nm.focus();
            
            return;
        }
        
		frm.srch_user_cntnt.value = $("#srch_reqman_nm").val();

		PopUpWindowOpen('', 800, 450, true);
		frm.target = "PopUpWindow";
		frm.srch_user_cntnt.value = srchValue;
		frm.action = "<c:url value='/secfw/esbOrg.do' />";
		frm.method.value = "listEsbEmployee";
		//viewHiddenProgress(true);
        frm.submit();
        frm.target = "";
    }
	
	/**
	* 법무담당자검색
	*/
	function popRespman() {
		var frm = document.frm;
    	var srchValue = comTrim($("#srch_respman_nm").val());
    	$("#srch_man_div").val("RESP");
    	
        if (srchValue == "" || getByteLength(srchValue) < 4) { 	//최소 2자리 이상 입력받게 한다.
            alert('<spring:message code='secfw.msg.error.nameMinByte' />'); 
            frm.srch_respman_nm.focus();
            
            return;
        }
        
		frm.srch_user_cntnt.value = $("#srch_respman_nm").val();

		PopUpWindowOpen('', 800, 450, true);
		frm.target = "PopUpWindow";
		frm.srch_user_cntnt.value = srchValue;
		frm.action = "<c:url value='/secfw/esbOrg.do' />";
		frm.method.value = "listEsbEmployee";
		//viewHiddenProgress(true);
        frm.submit();
        frm.target = "";
    }
	
	/**
	* 임직원정보 Setting
	*/
	function setUserInfos(obj) {
		var userInfo = obj;
		
		if($("#srch_man_div").val() == "REQ"){
			$("#srch_reqman_nm").val(userInfo.cn);
			$("#srch_reqman_id").val(userInfo.epid);		
		}else if($("#srch_man_div").val() == "RESP"){
			$("#srch_respman_nm").val(userInfo.cn);
			$("#srch_respman_id").val(userInfo.epid);	
		}
	}
	
	/**
	* 메시지 처리
	*/
	function showMessage(msg) {
		if(msg != "" && msg != null && msg.length > 1) {
			alert(msg);
		}
	}
	/**
	* 엑셀 다운로드 function
	*/ 
	function excelDownLoad(){
		
		alert('This list includes personal information. Please dont send it to out of Samsung and be careful to manage.');
		
		var frm = document.frm;
		
		// 300row가 넘어가는 시점에서 날짜를 체크 하고 있음.
		if(<%=pageUtil.getTotalRow()%>>300){
		    if(frm.srch_start_dt.value == ''){
			    alert("<spring:message code='las.page.field.lawconsideration.mustDoFromDate'/>");//계약기간 시작일은 필수 입력 항목입니다.
			    return;
		    }
		    if(frm.srch_end_dt.value == ''){
			    alert("<spring:message code='las.page.field.lawconsideration.mustDoToDate'/>");//계약기간 종료일은 필수 입력 항목입니다.
			    return;
		    }
		    //날짜계산
		    var sd = frm.srch_start_dt.value; //start Day
	        var ed = frm.srch_end_dt.value; //end Day
		    sd = sd.split("-");
		    ed = ed.split("-");
		    var st = new Date(sd[0],sd[1]-1,sd[2]).getTime();
		    var et = new Date(ed[0],ed[1]-1,ed[2]).getTime();
		    var fromto = (et-st)/(1000*60*60*24)+1;

		    if(fromto > 90){
			    alert("<spring:message code='clm.page.msg.manage.announce180max90'/>");//최대 90일 기간까지 다운 할 수 있습니다.
			    return;			
		    }
		}

		//$(".srch").val("");
		
		//법무담당자명 없을 경우 코드값 초기화
		if($("#srch_respman_nm").val() == ""){
			$("#srch_respman_id").val("");
		}

		var startDt = frm.srch_start_dt.value;
		var endDt = frm.srch_end_dt.value;

		if((startDt != "" && endDt != "") && (startDt > endDt)){
	  		alert("<spring:message code='clm.msg.alert.board.srchAnnceDt' />");
	  		return;
	  	}
		
		//viewHiddenProgress(true) ;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/review/consideration.do' />";
	    frm.method.value = "listConsiderationExcel";
	    frm.curPage.value = "1";
	    frm.submit();
	}
	
    
	/**
	* 해당 의뢰의 중요도를 체크 처리 한다.
	*/
	function setMarkUpAJAX(cntrt_id) {
		
		var frm = document.frm;
		var confirmMessage = "";
		var mark_src = "";
		var flag= "";		
		
		var msg_chk = "<spring:message code='las.msg.considertaton.mark001' />";   // 중요도를 체크 처리 하시겠습니까?
		var msg_unchk = "<spring:message code='las.msg.considertaton.mark002' />";   // 해제 처리 하시겠습니까?
		var msg_comp = "<spring:message code='las.msg.succ.process' />";   // 처리 되었습니다.
				
		frm.mark_cntrt_id.value = cntrt_id;
		
		if($('#icon_' + cntrt_id).attr('alt')=='3'){
			confirmMessage = msg_unchk;		
			mark_src = "/images/las/en/icon/icon_b_white.gif";
			frm.mark_num.value = "1";
			flag = "unchk";			
		} else {
			confirmMessage = msg_chk;
			mark_src = "/images/las/en/icon/icon_b_red.gif";
			frm.mark_num.value = "3";			
		}
			
		// if(confirm(confirmMessage)){		
			
			var options = {   
					url: "<c:url value='/clm/review/consideration.do?method=setMarkUpAJAX' />",
					type: "post",
					dataType: "json",
					success: function(responseText, statusText) {
						if(responseText.returnCnt != 0) {
							var html = "";
							$.each(responseText, function(entryIndex, entry) {
								
								var return_val = entry['return_val'];
								
								if(return_val == '1'){
									//alert($('#icon_' + cntrt_id).attr('src') + '//////' + mark_src);
									
									if(flag=='unchk'){
										$('#icon_' + cntrt_id).attr('alt','1');
									} else {
										$('#icon_' + cntrt_id).attr('alt','3');
									}
									
									$('#icon_' + cntrt_id).attr('src',mark_src);
									//alert(msg_comp); // 처리하였습니다.
								}									

							});						
						}	 
						
						// viewHiddenProgress(false) ;	
					}
			};		
			$("#frm").ajaxSubmit(options);	
		// }		
	}	
	
	/**
	* CLOSE 사유  Popup
	*/
	function popCloseCont(cntrt_id){
		var frm = document.frm;
		
		frm.cntrt_id.value = cntrt_id;
		
		PopUpWindowOpen('', 600, 230, true);
		frm.method.value = "popCloseCont";
		frm.action = "<c:url value='/clm/review/consideration.do' />",
		frm.target = "PopUpWindow";
		
		frm.submit();
	}
	
</script>
<!-- **************************** Calendar 관련 추가 영역 **************************** -->
<div id='popCal' style='position: absolute; visibility: hidden; ridge; width: 10; z-index:1;'>
<iframe name="popFrame" src="<c:url value='/secfw/popCalendar.do?method=goPopCalendar' />" frameborder="0" scrolling="no" width=196 height=188></iframe></div>
<script event=onclick() for=document> 
if(popCal.style.visibility == "visible"){
  popCal.style.visibility="hidden";
}
</script>
<script language="javascript">
	function blockRightClickfunction() 
	{
		//alert('Data copying is blocked due to security reasons.');
		return true;
	}
</script>
</head>
<body oncopy="return true;" oncut="return true;" oncontextmenu='return blockRightClickfunction();' onLoad='showMessage("<c:out value='${returnMessage}'/>");'>

<div id="wrap">	
	<!-- container -->
	<div id="container">
		<!-- location -->
		<div class="location"><IMG SRC="<%=IMAGE %>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //location -->
		
		<!-- title -->
		<div class="title">
			<h3><spring:message code="las.page.field.lawconsideration.reviewCont"/></h3><!-- 계약검토 -->
		</div>
		<!-- //title -->
		
		<!-- content -->
		<div id="content">
			<!-- content_in-->
			<div id="content_in">
			<form:form name="frm" id='frm' method="post" autocomplete="off"> 
			<input type="hidden" name="method"  >
			<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>">
			<input type="hidden" name="cnsdreq_id" id="cnsdreq_id" />
			<input type="hidden" name="cntrt_oppnt_nm" id="cntrt_oppnt_nm" />
			<input type="hidden" name="dmstfrgn" id="dmstfrgn" value="<c:out value='${command.dmstfrgn}'/>" />
			
			<input type="hidden" name="srch_reqman_id" value="<c:out value='${command.srch_reqman_id}'/>" />
			<input type="hidden" name="srch_respman_id" value="<c:out value='${command.srch_respman_id}'/>" />
			<input type="hidden" name="srch_user_cntnt" id="srch_user_cntnt"/>
			<input type="hidden" name="srch_user_cntnt_type" id="srch_user_cntnt_type" value="userName"/>
			<input type="hidden" name="srch_man_div" id="srch_man_div"/>
			
			<input type="hidden" name="blngt_orgnz" id="blngt_orgnz" value="<c:out value='${command.blngt_orgnz}'/>" />	<!-- 소속조직 -->
			<input type="hidden" name="top_role" 	id="top_role" 	 value="<c:out value='${command.top_role}'/>" />	<!-- ROLL -->
			<input type="hidden" name="page_flag" id="page_flag" value="<c:out value='${command.page_flag}'/>" />
			
			<input type="hidden" name="srch_cnclsnpurps" id="srch_cnclsnpurps" value="" /> <!-- 체결목적-->		
			<input type="hidden" name="srch_solo_yn" id="srch_solo_yn" value="" />	
			
			<input type="hidden" name="mark_cntrt_id" id="mark_cntrt_id" value="" />	<!-- 중요도 체크용-->	
			<input type="hidden" name="mark_num" id="mark_num" value="" />	<!-- 중요도 체크용-->		
			
			<input type="hidden" name="cntrt_id" id="cntrt_id" value="" />	<!-- CLOSE 사유 조회용 -->					
				
			<div class="search_box">
				<div class="search_box_inner">
					<table class="search_tb">
						
						<tr>
							<td>
								<table class="search_form">
									<colgroup>
										<col width="*%"/>
										<col width="*%"/>
										<col width="*%"/>
										<col width="*%"/>
										<col width="*%"/>
										<col width="*%"/>
									</colgroup>
									<tr>
										<%-- <th>
											<spring:message code="las.page.field.lawconsult.solo_yn" /><!-- 검토방식 -->
										</th>
										<td>
											<select name="srch_solo_yn" id="srch_solo_yn" style="width:182px" />
												<option value=""><spring:message code="las.page.field.contractManager.selectL"/></option><!-- -- 선택 -- -->
												<option value="1" <c:if test="${command.srch_solo_yn eq '1'}">selected</c:if>><spring:message code="las.page.field.lawconsult.cavity.dmst"/></option><!-- 공동(국내) -->
												<option value="2" <c:if test="${command.srch_solo_yn eq '2'}">selected</c:if>><spring:message code="las.page.field.lawconsult.cavity.frgn"/></option><!-- 공동(해외) -->
												<option value="3" <c:if test="${command.srch_solo_yn eq '3'}">selected</c:if>><spring:message code="las.page.field.lawconsult.alone"/></option><!-- 단독 -->
											</select>
										</td>  --%>
										<th>
											<spring:message code="las.page.field.contractmanager.consideration.req_title" /><!-- 의뢰명 -->
										</th>
										<td>
											<input type="text" name="srch_req_title" id="srch_req_title" value="<c:out value='${command.srch_req_title}'/>" maxLength="75" class="text" style='width:179px' />
										</td>
										<th>
											<spring:message code="clm.page.msg.common.content" /><!-- 내용 -->
										</th>
										<td>
											<input type="text" name="srch_cnsd_cont" id="srch_cnsd_cont" value="<c:out value='${command.srch_cnsd_cont}'/>" maxLength="100" class="text" style='width:179px'/>
										</td>
									
										<th>
											<spring:message code="las.page.field.contractmanager.consideration.reqday" /><!-- 의뢰일 -->
										</th>
										<td>
											<input type="text" name="srch_start_dt" id="srch_start_dt" value="<c:out value='${command.srch_start_dt}'/>" class="text_calendar02" readonly="readonly" /> ~
											<input type="text" name="srch_end_dt" id="srch_end_dt" value="<c:out value='${command.srch_end_dt}'/>" class="text_calendar02" readonly="readonly" />
										</td>
									</tr>
									<tr>
										<th>
											<spring:message code="las.page.field.contractmanager.consideration.reqman_nm" /><!-- 의뢰자 -->
										</th>
										<td>
											<input type="text" name="srch_reqman_nm" id="srch_reqman_nm" value="<c:out value='${command.srch_reqman_nm}'/>" maxLength="100" class='text' style='width:179px'/>
										</td>
										<th>
											<spring:message code="las.page.field.manageCommon.srchLasCnsdmanNm" /><!-- 법무검토자 -->
										</th>
										<td>
											<input type="text" name="srch_respman_nm" id="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}'/>" maxLength="100" class='text' style='width:179px'/>
										</td>
									
										<th>
											<spring:message code="las.page.field.contractmanager.consideration.law_status" /><!-- 법무상태 -->
										</th>
										<td>
											<select name="srch_law_status" id="srch_law_status" style="width:182px" />
											</select>
										</td>
									</tr>
									<tr>
										<th>
											<spring:message code="las.page.field.contractmanager.consideration.biz_depth" /><!-- 비즈니스단계 -->
										</th>
										<td>
											<select name="srch_biz_depth" id="srch_biz_depth"  style="width:182px"/>
											</select>
										</td>
										<th>
											<spring:message code="las.page.field.contractmanager.consideration.cntrt_oppnt_nm" /><!-- 계약상대방 -->
										</th>
										<td>
											<input type="text" name="srch_cntrt_oppnt_nm" id="srch_cntrt_oppnt_nm" value="<c:out value='${command.srch_cntrt_oppnt_nm}'/>" maxLength="100" class="text"  style='width:179px'/>
										</td>
									
<!-- 										<th> -->
<%-- 											<spring:message code="las.page.field.contractmanager.consideration.cnclsnpurps" /><!-- 체결목적 --> --%>
<!-- 										</th> -->
<!-- 										<td> -->
<!-- 											<select name="srch_cnclsnpurps" id="srch_cnclsnpurps" style="width:200px"/> -->
<!-- 											</select> -->
<!-- 										</td> -->
										<th>
											<spring:message code="las.page.field.contractmanager.consideration.prgrs_status" /><!-- 의뢰상태 -->
										</th>
										<td>
											<select name="srch_prgrs_status" id="srch_prgrs_status" style="width:182px" />
											</select>
										</td>
									</tr>
									<tr>
										<th><spring:message code="clm.page.field.qna.pubYnN" /></th>
										 <td> 
										<select name="srch_closed_yn" id="srch_closed_yn" style="width:182px" />
											<option value=""><spring:message code="las.page.field.contractManager.selectL"/></option><!-- -- 선택 -- -->
											<option value="Y" <c:if test="${command.srch_closed_yn eq 'Y'}">selected</c:if>>Y</option>
											<option value="N" <c:if test="${command.srch_closed_yn eq 'N'}">selected</c:if>>N</option>
										</select>
										</td>
										<!-- 전자변호가 들어 올 경우 회사를 조회 할 수 있는 콤보 리스트가 나타나게 된다. -->
										<c:choose>
											<c:when test="${elUserlYn}">
												<th><spring:message code="clm.page.msg.common.selCompNm" /></th>
											    <td> 
												    <select class="srch" name="sElComp"	id="sElComp"> </select>
											    </td>
											</c:when>
											<c:otherwise>
												<td></td>
												<td></td>
											</c:otherwise>
										</c:choose>
										<th><spring:message code="clm.page.field.manageCommon.srchCntrtId"/></th>
                                        <td>
                                            <input class="text" style="width:179px" type="text" name="srch_cntrt_no"  id="srch_cntrt_no" value="<c:out value='${command.srch_cntrt_no}'/>"/>
                                        </td>
										<td class="tR"><a href="javascript:search();"><img src="<%=IMAGE %>/btn/btn_search.gif" alt="<spring:message code="las.page.button.search" />"  style='margin-right:9%' /></a></td>
									</tr>
								</table>
							</td>
							
						</tr>
					</table>
				</div>
			</div>
          	<!-- search-->
          	<!-- button -->
			<div class="t_titBtn">
				<div class="btn_wrap_t">
					<span class="btn_all_w" onclick="excelDownLoad();"><span class="excel_down"></span><a> <spring:message code='las.page.button.excelDownload' /></a></span><!-- 엑셀 다운로드 -->
				</div>
		  	</div>
		  	<!-- //button -->
          	<!-- list -->
          	<c:set var="col_cnt" value="9" />
			<table class="list_basic">
				<colgroup>
					<c:if test="${elUserlYn}">
						<col width="*%" />
					</c:if>
			<%-- 			<col width="*%" /> --%>
						<col width="30%"/>
						<col width="10%" />
						<col width="10%" />
						<col width="*" />
						<col width="9%" />
						<col width="*" />
						<col width="8%" />
						<col width="8%" />
						<col width="5%" />
				</colgroup>
				<thead>
					<tr>
						<c:if test="${elUserlYn}">
							<th rowspan="2"><spring:message code="las.page.field.contractmanager.consideration.company_nm" /></th><!-- 회사명 -->
						</c:if>
<%-- 						<th rowspan="2"><spring:message code="las.page.field.lawconsult.solo_yn" /></th>		<!-- 검토방법 -->	 --%>
			            <th rowspan="2"><spring:message code="las.page.field.contractmanager.consideration.req_title" /></th>		<!-- 의뢰명 -->
			            <th rowspan="2"><spring:message code="las.page.field.contractmanager.consideration.cntrt_oppnt_nm" /></th>      <!-- 계약상대방 -->
			            <th rowspan="2" class='overflow' title='<spring:message code="las.page.field.contractmanager.consideration.req_dept_nm" />'><spring:message code="las.page.field.contractmanager.consideration.req_dept_nm_th" /></th>		<!-- 의뢰부서 -->
			            <th rowspan="2"><spring:message code="las.page.field.contractmanager.consideration.reqman_nm" /></th>		<!-- 의뢰자 -->
			            <th rowspan="2" class='rightline' title='<spring:message code="las.page.field.contractmanager.consideration.first" /> <spring:message code="las.page.field.contractmanager.consideration.reqday" />'>
			            	<div class='overflow tC' style='width:100%'><spring:message code="las.page.field.contractmanager.consideration.first" /></div>
			            	<div class='overflow tC' style='width:100%'><spring:message code="las.page.field.contractmanager.consideration.reqday" /></div>
			            </th>			<!-- 의뢰일 -->
			            <th colspan="2"><spring:message code="las.page.field.contractManager.legal"/></th>	<!-- 법무담당자 -->
			            <th rowspan="2"><spring:message code="las.page.field.contractmanager.consideration.prgrs_status" /></th>	<!-- 의뢰상태 -->
			            <th rowspan="2"><spring:message code="clm.page.field.qna.pubYnN" /></th><!-- CLOSED -->
					</tr>
					<tr>
			            <th class="sub01"><spring:message code="las.page.field.contractManager.reviewer"/></th>	<!-- 법무담당자 -->
			            <th class="sub01"><spring:message code="las.page.field.contractManager.state"/></th><!-- 법무상태 -->			            
					</tr>
				</thead>
				<tbody>
						<c:choose>
							<c:when test="${pageUtil.totalRow > 0}">
								<c:forEach var="list" items="${lom}">
								<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
									<c:if test="${elUserlYn}">
										<td class="tC"><c:out value='${list.comp_nm}' escapeXml='false' /></td>
									</c:if>
<%-- 									<td class="tC">
										<c:choose>
										<c:when test="${list.solo_yn eq '1'}">
											<spring:message code="las.page.field.lawconsult.cavity.dmst"/><!-- 공동(국내) -->
										</c:when>
										<c:when test="${list.solo_yn eq '2'}">
											<spring:message code="las.page.field.lawconsult.cavity.frgn"/><!-- 공동(해외) -->
										</c:when>
										<c:when test="${list.solo_yn eq '3'}">
											<spring:message code="las.page.field.lawconsult.alone"/><!-- 단독 -->
										</c:when>
										</c:choose>
									</td> --%>
									
									<td title="<c:out value='${list.req_title}' escapeXml='false'/>">
									
										<div style="overflow:hidden; height:40px;">
											
																					
											<% if(authYN_RA02){ %>
												<c:choose>
													<c:when test="${list.mark_num eq '3'}">
														<img  id="icon_<c:out value='${list.CNTRT_ID}'/>" src="/images/las/en/icon/icon_b_red.gif" class='img_align'  onclick="javascript:setMarkUpAJAX('<c:out value='${list.CNTRT_ID}'/>');" alt="<c:out value='${list.mark_num}'/>" style="cursor:pointer"/>&nbsp;					
													</c:when>
													<c:otherwise>
														<img  id="icon_<c:out value='${list.CNTRT_ID}'/>" src="/images/las/en/icon/icon_b_white.gif" class='img_align' onclick="javascript:setMarkUpAJAX('<c:out value='${list.CNTRT_ID}'/>');" alt="<c:out value='${list.mark_num}'/>"  style="cursor:pointer" />&nbsp;															
													</c:otherwise>
												</c:choose>         		
											<%} %>									
										
						            		<a href='javascript:goDetail("<c:out value='${list.cnsdreq_id}'/>", "<c:out value='${list.cntrt_oppnt_nm}'/>");'>
				          						<c:out value='${list.req_title}' escapeXml='false'/>	          		
						          			</a>
							          	</div>
						            </td>
						            <td class="tC txtover" title="<c:out value='${list.cntrt_oppnt_nm}'/>"><c:out value='${list.cntrt_oppnt_nm}' escapeXml='false' /></td>
						            <td class="tC txtover" title="<c:out value='${list.req_dept_nm}'/>"><c:out value='${list.req_dept_nm}' escapeXml='false' /></td>
						            <td class="tC txtover" title="<c:out value='${list.reqman_nm}'/>"><c:out value='${list.reqman_nm}' escapeXml='false' /></td>
						            <td class="tC"><c:out value='${list.req_dt}' escapeXml='false' /></td>
						            <c:choose>
						            	<c:when test="${list.law_status_nm eq ''}">
						            		<td class="tC txtover" title="">&nbsp;</td>
						            	</c:when>
						            	<c:when test="${list.law_respman_cnt > 1}">
						            		<td class="tC txtover" title="<c:out value='${list.law_respman_nm}'/>"><c:out value='${list.law_respman_fnm}' escapeXml='false' /><br><spring:message code="las.page.field.contractManager.etc"/><c:out value='${list.law_respman_cnt}' escapeXml='false' /><spring:message code="las.page.field.contractManager.nmC"/>
						            		</td>
						            	</c:when>
						            	<c:otherwise>
						            		<td class="tC txtover" title="<c:out value='${list.law_respman_nm}'/>"><c:out value='${list.law_respman_fnm}' escapeXml='false' /></td>
						            	</c:otherwise>
						            </c:choose>
						            <td class="tC"><c:out value='${list.law_status_nm}' escapeXml='false' /><c:out value='${list.hq_cnsd_status_nm}' escapeXml='false' /></td>
							            <c:choose>
								            <c:when test="${list.prgrs_status eq 'C04202'}"><td class="green_cor tC"></c:when>
								            <c:when test="${list.prgrs_status eq 'C04203'}"><td class="green_cor tC"></c:when>
								            <c:when test="${list.prgrs_status eq 'C04206'}"><td class="blue_cor tC"></c:when>
								            <c:when test="${list.prgrs_status eq 'C04204'}"><td class="bora_cor tC"></c:when>
								            <c:when test="${list.prgrs_status eq 'C04205'}"><td class="yellow_cor tC"></c:when>
								            <c:when test="${list.prgrs_status eq 'C04209'}"><td class="red_cor tC"></c:when>
											<c:otherwise><td class="tC"></c:otherwise>
										</c:choose>
									<c:out value='${list.prgrs_status_nm}' escapeXml='false' /></td>							
									<c:choose>
							            <c:when test="${list.CLOSE_YN eq 'Closed'}">
							            	<td class="tC">
							            		<a href='javascript:popCloseCont("<c:out value='${list.CNTRT_ID}'/>");'><spring:message code="clm.page.field.qna.pubYnN"/></a>
							            	</td>
							            </c:when>
										<c:otherwise><td class="tC"></td></c:otherwise>
									</c:choose>									
								</tr>
								</c:forEach>
						    </c:when>
						    <c:otherwise>
								<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
									<td colspan="9" align="center" class="tC"><spring:message code="las.msg.succ.noResult" /></td>
								</tr>
						    </c:otherwise>
						</c:choose>
					
				</tbody>
			</table>
			<!-- //list -->

			<!-- pagination -->
			<div class="t_titBtn">
				<div class="total_num">
					Total : <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%> 
				</div>

				<div class="pagination">
					<%=pageUtil.getPageNavi(pageUtil, "") %>
				</div>
			</div>
			<!-- //pagination -->
		
			</form:form>		
			</div>
			<!-- //content in -->	
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