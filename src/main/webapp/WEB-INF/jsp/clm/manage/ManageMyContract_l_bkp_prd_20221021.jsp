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
 * 파  일  명 : ManageMyContract_l.jsp
 * 프로그램명 : MyContract목록
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2013.03
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	List resultList = (List)request.getAttribute("resultList");
	ManageForm command = (ManageForm)request.getAttribute("command");
%>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html><!-- ManageMyContract_l.jsp -->
<head>
<meta sci="ManageMyContract_l.jsp" />
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />

<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet" />

<link rel="stylesheet" href="/style/jquery.jgrowl.min.css" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/menu.js'/>"></script>
<script src="/script/secfw/jquery/jquery.blockUI.js"></script>
<!--  <script src="/script/secfw/jquery/jquery.jgrowl.min.js"></script> -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-jgrowl/1.2.12/jquery.jgrowl.min.js"></script>
<script language="javascript">

	$(function() {
		
		//Step 세팅            search_state
		getCodeSelectByUpCd("frm", "srch_step", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=C025&useman_mng_itm2=clmsList&combo_type=T&combo_del_yn=N&combo_selected='+'<c:out value='${command.srch_step}'/>');
		getCodeSelectByUpCd("frm", "srch_state", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=' + '<c:out value='${command.srch_step}'/>' +'&combo_type=T&combo_del_yn=N&combo_selected='+'<c:out value='${command.srch_state}'/>');
		//비즈니스 단계 
		getCodeSelectByUpCd("frm", "srch_biz_clsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=T01&combo_abbr=F&combo_type=S&combo_del_yn=N&combo_selected="+"<c:out value='${command.srch_biz_clsfcn}'/>");   
		//회사선택
		getCodeSelectByUpCd("frm", "sElComp", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=USERCOMP&combo_type=T&combo_del_yn=N&sel_grd=<c:out value='${command.sSel_grd}'/>&combo_selected='+'<c:out value='${command.sElComp}'/>');
		
	 	initCal("srch_start_reqday");   //첫번재 의뢰일 설정
	    initCal("srch_end_reqday");     //두번재 의뢰일 설정
	 	initCal("srch_start_cnlsnday");   //첫번재 체결일 설정
	    initCal("srch_end_cnlsnday");     //두번재 체결일 설정
	    
		/// Fernando 08-09-2022 (start) ///
		//var l_srch_end_reqday               = new Date( $("#srch_end_reqday").val() );
		//var l_new_srch_end_reqday           = new Date( 0 + Math.abs(l_srch_end_reqday) + (1000 * 60 * 60 * 24/*1 day*/) );
		//var l_new_srch_end_reqday_as_string = l_new_srch_end_reqday.getFullYear() + "-" + ("0"+(l_new_srch_end_reqday.getMonth()+1)).slice(-2) + "-" + ("0" + l_new_srch_end_reqday.getDate()).slice(-2);
		//$("#srch_end_reqday").val(l_new_srch_end_reqday_as_string);
		/// Fernando 08-09-2022 (end) ///

	    <% if("cnsdreq".equals(command.getList_mode())) {%>
	    initCal("srch_str_org_acptday");   //첫번재 원본접수일 설정
        initCal("srch_end_org_acptday");     //두번재 원본접수일 설정
	    <%}%>
	    
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

        /* 2014-05-08 Kevin added. GERP link */	
        $("#btnLinkToGERP").click(function(){

    		if($("#c_cnsdreq_id").val() == "") {
    			alert("<spring:message code='clm.page.msg.manage.announceSelectAcase' />");
    			return; 	
    		}
    		
    		$("#frm").block({
    			message:"<h1>Linking the request to GERP ...  <img src='/images/secfw/common/loading_a.gif' /></h1>"
    		});
    		
    		$.ajax({
    			url:"/clm/manage/contract.do?method=LinkToGERP",
    			data:{cnsdreqid:$("#c_cnsdreq_id").val(), menu_id:"20130319154642301_0000000355"},
    			dataType:"json",
    			success: function(data){
    				if(data.result != "undefined" && data.result != null){
        				if(parseInt(data.result) > 0){
        					alert("<spring:message code='clm.page.message.successLinkToGERP' />");
        				} else {
        					var failureMsg = "<spring:message code='clm.page.message.failureLinkToGERP' />";
        					alert(failureMsg+"'["+data.result.toString() +"]");
        				}
        			} else {
        				var errorMsg = "<spring:message code='clm.page.message.errorLinkToGERP' />";
        				alert(errorMsg+"["+$("#c_cnsdreq_id").val()+"]");
        			}
    			},
    			complete: function(xhr, status){
    				$("#frm").unblock();
    			}
    		});
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
	* 조회(검색)버튼 function
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
		
		/// Fernando 30-08-2022 (start) ///
		//console.log("test start...");
		
		<%
			boolean l_isAdmin = false;
			java.util.List roleList = (java.util.List)session.getAttribute("secfw.session.role");
	
			if(roleList != null && roleList.size() > 0) {
				for(int ri = 0; ri < roleList.size(); ri++) {
					java.util.HashMap roleHm = (java.util.HashMap)roleList.get(ri);
					if("RD01".equals((String)roleHm.get("role_cd"))) {
						l_isAdmin = true;
						break;
					}
				}
			}
		%>
		
		var l_user_name = "<%=StringUtil.bvl((String)session.getAttribute("secfw.session.user_nm_en"), "")%>".toUpperCase();
		//console.log(l_user_name);

		var l_user_admin = "<%=l_isAdmin%>".toUpperCase();
		//console.log(l_user_admin);
		
		var l_free = false;
		if ((l_user_name.indexOf("FERNANDO CERDEIRA DA SILVA")>=0)||
			(l_user_name.indexOf("KATIE HUGHES")>=0)||
			(l_user_name.indexOf("BAO-UYEN TON")>=0)||
			(l_user_name.indexOf("xxx")>=0)){
			l_free = true;			
		}
		
		if (l_free === false)
		{
			var l_srch_start_reqday    = new Date( $("#srch_start_reqday").val() );
			var l_srch_end_reqday      = new Date( $("#srch_end_reqday").val() );
			var l_srch_interval_reqday = Math.abs(l_srch_end_reqday - l_srch_start_reqday);
			if (l_srch_interval_reqday > (31536000000/*year*/+(1000 * 60 * 60 * 24 * 31/*month*/))) {
				alert("You cannot set an Request Date interval longer than 1 year.");
				return;
			}
			
			//var l_new_srch_end_reqday = new Date( 0 + Math.abs(l_srch_end_reqday) + (1000 * 60 * 60 * 24/*1 day*/) );
			//var l_new_srch_end_reqday_as_string = l_new_srch_end_reqday.getFullYear() + "-" + ("0"+(l_new_srch_end_reqday.getMonth()+1)).slice(-2) + "-" + ("0" + l_new_srch_end_reqday.getDate()).slice(-2);
			//$("#srch_end_reqday").val(l_new_srch_end_reqday_as_string);
		}
		//console.log("test end...");
		/// Fernando 30-08-2022 (end) ///
		
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
		
		//Sungwoo Added srch_vendor_type_detail 2014-06-10
		if($(":radio[name='srch_vendor_type']:checked").attr("checked") != null && $(":radio[name='srch_vendor_type']:checked").attr("checked") == true && $("#srch_vendor_type_detail").val() == ""){
			alert("<spring:message code='las.page.field.report.inputVendorType' />");//Enter search CounterParty G-ERP Code
	  		return;
		}
		if($(":radio[name='srch_vendor_type']:checked").attr("checked") == null && $("#srch_vendor_type_detail").val() != ""){
			alert("<spring:message code='las.page.field.report.checkVendorType' />");//Check search CounterParty G-ERP Code
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
		frm.action = "<c:url value='/clm/manage/myContract.do' />";
	    frm.method.value = "listMyContract";
	    frm.curPage.value = "1";
	    frm.submit();
	}
	
	/**
	* 엑셀 다운로드 function
	*/ 
	function excelDownLoad(){
		var frm = document.frm;
		
		// 2014-07-08 Kevin commented.
		// According to many requests regarding excel download, 1000-row limit is eliminated.
		// 1000row가 넘어가면 제한 메세지
		//DE_The number of data which will be downloaded is over 1000. Please re-select search condition to reduce the amount of data.
		<%-- if(<%=pageUtil.getTotalRow()%>>1000){
		    	alert("<spring:message code='clm.page.msg.manage.announce180max1000' />");
			    return;			
		} --%>

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
		
		// 2014-07-10 Kevin added.
		// Alam modal comes up when excel download.
		// 2019-07-30 Brice added if to check indefined because jGrowl is blocked from some subsidiary
		if (typeof $.jGrowl !== 'undefined'){
		$.jGrowl("Excel download started and it may take time depending on number of contracts.", {
			position: "bottom-right",
			life: 4000,
			closeDuration: "slow"
		});
		} else {
			alert("Excel download started and it may take time depending on number of contracts.");
		}
		
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
		frm.action = "<c:url value='/clm/manage/myContract.do' />";
		frm.method.value = "listMyContract";
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
		frm.method.value = "forwardInsertConsiderationNew" ;
		frm.submit() ;
	}
	
	/**
	* 관리(상세)페이지 이동
	*/	
	function detailAction(id, prcs, depth_status){
		
		//alert("id = "+id+"  prcs = "+prcs+"  depth_status = "+depth_status);
		
		var frm = document.frm;
		var formId = "frm";
		var target = "_top";
		var menuId2 = "";
		var initUrl = "";
		var myAction = "";
		var myMethod = "";
		
		frm.ismycontract.value = "Y";

		//2012-05-17 추가. 사업부계약관리자 && 원본미등록 일시 myapproval 로 넘어간다.
		if(<%=ClmsBoardUtil.searchRole(request, "RD01")%> == true && (depth_status == 'C02642' || depth_status == 'C02685')){
			
			viewHiddenProgress(true);
			frm.cnsdreq_id.value = id;
			frm.target = "_self";
			frm.action = "<c:url value='/clm/manage/myApproval.do' />";
			frm.method.value ="detailMyApproval";
			frm.submit();
		} else if(prcs == "C02506" || prcs == "C02507" || prcs == "C02509") {  // 사별이관
			
			frm.cnsdreq_id.value = id;
			frm.target = "_self";
			frm.action = "<c:url value='/clm/review/consideration.do' />";
			frm.method.value = "detailConsideration_old";
			viewHiddenProgress(true);
			frm.submit();
			
		} else {
			
			if(prcs == "C02501"){   // 계약 검토/최종회신
				initUrl = "/clm/manage/consideration.do?method=detailConsiderationNew&cnsdreq_id="+id;
				myAction = "/clm/manage/consideration.do";
				myMethod = "detailConsiderationNew";
				
				// 회신일 경우 위 메소드에서 다시 분기 함.
				
			}else if(prcs == "C02502"){  // 계약 체결
				initUrl = "/clm/manage/consultation.do?method=detailConsultationNew&cnsdreq_id="+id;
				myAction = "/clm/manage/consultation.do";
				myMethod = "detailConsultationNew";
			}else if(prcs == "C02503"){ // 계약 등록
				initUrl = "/clm/manage/conclusion.do?method=detailConclusionNew&cnsdreq_id="+id;
				myAction = "/clm/manage/conclusion.do";
				myMethod = "detailConclusionNew";
			}else if(prcs == "C02504"){ // 계약 이행
				initUrl = "/clm/manage/execution.do?method=listContract&cnsdreq_id="+id;
				myAction = "/clm/manage/execution.do";
				myMethod = "listContract";
			}else if(prcs == "C02505"){ // 계약 종료
				initUrl = "/clm/manage/completion.do?method=listContract&cnsdreq_id="+id;
				myAction = "/clm/manage/completion.do";
				myMethod = "listContract";
			}
			viewHiddenProgress(true);
			frm.target = "_self";
			frm.action = myAction;
			frm.method.value = myMethod;
			frm.cnsdreq_id.value = id;
			frm.submit();

		}
	}

	/**
	*	계약상대방 팝업
	*/
	function popOppnt(){
		 var frm = document.frm;
		 
		PopUpWindowOpen('', 900, 600, false);
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
		 
		 PopUpWindowOpen('', 640, 640, false);
		 frm.target = "PopUpWindow";
		 frm.action = "<c:url value='/common/clmsCom.do' />";
		 frm.method.value = "listDeptTreeEsb";
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
    	
		PopUpWindowOpen('', 750, 680, false);
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
	
	
	// 사별이관 계약등록
	function goOldConsiderationInsert(){
		var frm = document.frm;
		viewHiddenProgress(true) ;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/review/consideration.do' />";
		frm.method.value = "forwardInsertyOldConsideration";
		frm.submit();
	}
	
	/**   
	*검토 의뢰 복사하기
	*/
	function goInsertCnsd(){
		var frm = document.frm;	
		if(frm.c_cnsdreq_id.value == "") {
			alert("<spring:message code='clm.page.msg.manage.announce092' />");
			return; 	
		}
		
		if(frm.c_prcs_depth.value == "C02506" || frm.c_prcs_depth.value == "C02507" || frm.c_prcs_depth.value == "C02509"){
			alert("<spring:message code='clm.page.msg.manage.announce065' />");
			return;
		}
		
		var notice = '<spring:message code="clm.page.msg.manage.copyReqReview" />\n\n<spring:message code="clm.page.msg.manage.announce013" />'      ;
		
		if(!confirm(notice)) return;  	
			viewHiddenProgress(true);
			
			
			frm.target = "_self";		 
			frm.action = "<c:url value='/clm/manage/consideration.do' />";
			frm.method.value = "insertCopyCnsd";			
			frm.submit();	
	}
	
	/**   
	*검토 의뢰 복사선택 시
	*/
	function CnsdSelect(arg1, arg2){
		var frm = document.frm;	
		frm.c_cnsdreq_id.value		= arg1;				// 검토의뢰ID
		frm.c_prcs_depth.value		= arg2;				// 프로세스단계
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
</head>
<html>
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
			<h3><spring:message code="clm.page.title.manageMyContract.listTitle" /></h3>
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
	<!-- Copy Page Param -->
	<input type="hidden" name="c_cnsdreq_id" id="c_cnsdreq_id" value="" />
	<input type="hidden" name="c_prcs_depth" value="" />
	
	<!-- closed_yn 추가 -->
	<input type="hidden" name="cntrt_id" id="cntrt_id"  value="" />
		
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
                                        <col width="*%"/>
                                        <col width="*%"/>
                                        <col width="*%"/>
                                        <col width="*%"/>
                                        <col width="*%"/>
                                    </colgroup>
									<tr>
										<th><spring:message code="clm.page.field.manageCommon.srchReqTitle" /></th>
										<td>
											<input class="text" style="width:179px" type="text" name="srch_review_title"  id="srch_review_title" value="<c:out value='${command.srch_review_title}'/>" />
										</td>
										<th><spring:message code="clm.page.field.manageCommon.srchCntrtNm" /></th>
                                        <td>
                                            <input class="text" style="width:179px" type="text" name="srch_cntrt_nm"  id="srch_cntrt_nm" value="<c:out value='${command.srch_cntrt_nm}'/>"/>
                                        </td>
                                        <th><spring:message code="clm.page.field.manageCommon.srchCntrtId"/></th>
                                        <td>
                                            <input class="text" style="width:179px" type="text" name="srch_cntrt_no"  id="srch_cntrt_no" value="<c:out value='${command.srch_cntrt_no}'/>"/>
                                        </td>
                                    </tr>
									<tr>
                                        
									
									   <th><spring:message code="clm.page.field.manageCommon.srchReqmanNm" /></th>
                                        <td>
                                            <input class="text" style="width:179px" type="text" name="srch_reqman_nm" id="srch_reqman_nm" value="<c:out value='${command.srch_reqman_nm}'/>"/>
                                        </td>
                                    
                                        <th><spring:message code="clm.page.field.manageCommon.srchLasCnsdmanNm" /></th>
                                        <td>
                                            <input class="text" style="width:179px" type="text" name="srch_cnsdman_nm" id="srch_cnsdman_nm" value="<c:out value='${command.srch_cnsdman_nm}'/>" />
                                        </td>
                                        <th><spring:message code="clm.page.field.manageCommon.srchOppntCd" /></th>
										<td>
											<input class="text" style="width:179px"  type="text" name="srch_oppnt_nm" id="srch_oppnt_nm" value="<c:out value='${command.srch_oppnt_nm}'/>"/>
										</td>
									</tr>
									
									<tr>
									   <th><spring:message code="clm.page.field.manageCommon.srchReqDay" /></th>
                                        <td>
                                            <input type="text" name="srch_start_reqday" id="srch_start_reqday" value="<c:out value='${command.srch_start_reqday}'/>" class="text_calendar02" readonly="readonly" />
                                            ~
                                            <input type="text" name="srch_end_reqday" id="srch_end_reqday" value="<c:out value='${command.srch_end_reqday}'/>" class="text_calendar02" readonly="readonly" />
                                        </td>
                                       
                                       <th><spring:message code="clm.page.field.manageCommon.srchCnlsnday" /></th>
                                        <td>
                                            <input type="text" name="srch_start_cnlsnday" id="srch_start_cnlsnday" value="<c:out value='${command.srch_start_cnlsnday}'/>"  class="text_calendar" />
                                            ~
                                            <input type="text" name="srch_end_cnlsnday" id="srch_end_cnlsnday" value="<c:out value='${command.srch_end_cnlsnday}'/>" class="text_calendar" />
                                        </td>
                                   
                                        <th><spring:message code="clm.page.field.contract.conclusion.detail.conclusionorgacceptday" /></th>
                                        <td>
                                            <input type="text" name="srch_str_org_acptday" id="srch_str_org_acptday" value="<c:out value='${command.srch_str_org_acptday}'/>"  class="text_calendar" />
                                            ~
                                            <input type="text" name="srch_end_org_acptday" id="srch_end_org_acptday" value="<c:out value='${command.srch_end_org_acptday}'/>" class="text_calendar" />
                                            
                                        </td>
                                    </tr>
									<tr>
										<!-- Sungwoo added Search option G-ERP DIVISION 2014-06-09 -->
										<th><spring:message code="clm.page.field.contract.gerp.headDivisionCode"/></th>
										<td><input style="width:179px"  type="text" name="srch_division" id="srch_division" value="<c:out value='${command.srch_division}'/>"/></td>
										<th><spring:message code="clm.page.field.contract.gerp.counterpartyGerpCode"/></th>
										<td colspan="3">
											<input type="radio" name="srch_vendor_type" value="V" <c:if test="${command.srch_vendor_type eq 'V'}"> checked</c:if>/> <spring:message code="las.page.field.vendor"/> 
											&nbsp;<input type="radio" name="srch_vendor_type" value="C" <c:if test="${command.srch_vendor_type eq 'C'}"> checked</c:if>/> <spring:message code="las.page.field.customer"/> 
											&nbsp;<input style="width:179px" type="text" name="srch_vendor_type_detail" id="srch_vendor_type_detail" value="<c:out value='${command.srch_vendor_type_detail}'/>"/>
										</td>
									</tr>
									<tr>  
									   <th><spring:message code="clm.page.field.manageCommon.srchStep" /> / <spring:message code="clm.page.field.manageCommon.srchStatus" /></th>
                                        <td>
                                            <select class="srch" name="srch_step" id="srch_step" value="<c:out value='${command.srch_step}'/>" style='width:85px'/>
                                            </select>
                                            /           
                                            <select class="srch" name="srch_state"  id="srch_state" style='width:84px'/>
                                            </select>
                                        </td>   
                                        <th><spring:message code="clm.page.field.qna.pubYnN" /></th>
                                        <td>
                                            <select class="srch" name="closed_yn" id="closed_yn" >
                                            	<option value="" >--<spring:message code="clm.page.field.admin.subject.select_yn" />--</option>
                                            	<option value="Y" <c:if test="${command.closed_yn eq 'Y'}"> selected</c:if>>Y</option>
											    <option value="N" <c:if test="${command.closed_yn eq 'N'}"> selected</c:if>>N</option>
					     					</select>
                                        </td>                                        
                                        <th><spring:message code="clm.page.field.customer.tncno" /></th>    
                                        <td><input style="width:179px"  type="text" name="srch_tnc_no" id="srch_tnc_no" value="<c:out value='${command.srch_tnc_no}'/>"/></td>
									
										<td class="tR">
											<a href="javascript:searchAction('');"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="clm.page.msg.common.search" htmlEscape="true" />" class='mt5' style='margin-right:9%' /></a>
											<!-- 구주 관련은 상세 검색 없음 <p class="mt5"><a href="javascript:popDetailSrh();"><img src="<%//=IMAGE%>/btn/btn_search2.gif" alt="<spring:message code="clm.page.msg.manage.detailSearch" htmlEscape="true" />"/></a></p> -->
										</td>
									</tr>
								</table>
							<!-- 계약 기준 조회일 경우 -->
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!--//search-->
			<!-- button -->
			<div class="t_titBtn">
				<div class="btn_wrap_t">
					<!-- 2014-05-08 Kevin added. Link to GERP -->
					<% if(compCd.equals("SEF")) { %>
						<span class="btn_all_w" id="linkToGERPWrapper"><span class=" new_insert"></span><a href="#" id="btnLinkToGERP"><spring:message code="clm.page.msg.manage.linkToGERP" /></a></span> 
					<%} %>
					<span class="btn_all_w"><span class=" new_insert"></span><a href="javascript:goInsertForm();"><spring:message code="clm.page.msg.manage.createRevReq" /></a></span>   <!-- 검토의뢰작성 -->
					<span class="btn_all_w" title="<spring:message code="clm.page.msg.manage.announce081" htmlEscape="true" />"><span class="copy"></span><a href="javascript:goInsertCnsd();"><spring:message code="clm.page.msg.manage.copyRevReq" /></a></span> <!-- 검토의뢰 복사 -->
					<c:if test="${viewOldContractBtnFlag}">
						<span class="btn_all_w" ><span class="contract"></span><a href="javascript:goOldConsiderationInsert();"><spring:message code="clm.page.msg.manage.deptTranLiteContReg" /></a></span> <!-- 사별이관 계약등록 -->
					</c:if>
					<span class="btn_all_w">
						<span class="excel_down"></span>
						<a href="javascript:excelDownLoad();"> <spring:message code='las.page.button.excelDownload' /></a>
					</span><!-- 엑셀 다운로드 -->
				</div>
		  	</div>
		  	<!-- //button -->
			<!-- list -->
			<!-- 의뢰기준일 경우 -->
		   
			<table class="list_basic">
				<colgroup>
				    <col width="45px" />
					<col />
					<col width="9%" />
					<col width="9%" />
					<col width="10%" />
					<col width="9%" />
					<col width="9%" />
					<col width="8%" />
					<col width="60px" />
					<col width="13%" />
					<col width="55px" />
					<col width="55px" />
			  	</colgroup>
			  	<thead>
					<tr>
						<!-- 2014-05-08 Kevin changed Copy -> Select. -->
					    <th><spring:message code="clm.page.msg.manage.select" /></th>
						<th title='<spring:message code="clm.page.field.manageCommon.reqTitle" />&#13;(<spring:message code="clm.page.field.manageCommon.srchCntrtNm" />)'><spring:message code="clm.page.field.manageCommon.reqTitle" /><br>(<spring:message code="clm.page.field.manageCommon.srchCntrtNm" />)</th>
						<th class="overflow" title="<spring:message code="clm.page.msg.manage.announce032" htmlEscape="true" />"><spring:message code="clm.page.field.manageCommon.reqmanNm" /><br/><spring:message code="clm.page.field.manageCommon.respmanNm" /></th>
						<th class="overflow" title='<spring:message code="las.page.field.contractmanager.consideration.first"/> <spring:message code="clm.page.field.manageCommon.reqDay" />&#13;(<spring:message code="clm.page.field.contract.conclusion.detail.conclusionorgacceptday" />)'>
						    <spring:message code="las.page.field.contractmanager.consideration.first"/> <spring:message code="clm.page.field.manageCommon.reqDay" /><br/>
						    (<spring:message code="clm.page.field.contract.conclusion.detail.conclusionorgacceptday" />)
						</th>
						<th><spring:message code="clm.page.field.manageCommon.respDept" /></th>
						<th><spring:message code="clm.page.field.manageCommon.oppntCd" /></th>
						<th class='overflow'><spring:message code="clm.page.field.manageCommon.srchLasCnsdmanNm_br" /></th>
						<th><spring:message code="clm.page.field.manageCommon.srchCntrtId" /><br/>(<spring:message code="clm.page.field.customer.tncno" />)</th>
						<th><spring:message code="clm.page.field.manageCommon.step" /></th>
						<th><spring:message code="clm.page.field.manageCommon.status" /></th>
						<th><spring:message code="clm.page.field.contract.gerp.headDivisionCode" /></th>
						<th><spring:message code="clm.page.field.qna.pubYnN" /></th>
					</tr>
				</thead>
			 	<tbody>
			  <%
				if(pageUtil.getTotalRow() > 0) {
					String sover_title = "";
					for(int idx=0; idx<resultList.size(); idx++) {
						ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
				%>			
				    <tr  <%if(idx == resultList.size()-1){%>class="end"<%}%> onmouseout="this.className='<%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onmouseover="this.className='on <%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'">
				      <td class='tC'>
				      		<input name="cnsd_no" type="radio" class="radio" onclick="javascript:CnsdSelect('<%=lom.get("cnsdreq_id") %>', '<%=lom.get("prcs_depth")%>');" />
				      </td>
				      <td class="tL txtover overflow" style="line-height:150%; border-left:1px solid #CADBE2;" title="<%=StringUtil.convertHtmlTochars((String)lom.get("req_title")) %>&#13(<%=StringUtil.convertHtmlTochars((String)lom.get("cntrt_nm")) %>)">
				        <a onclick="javascript:detailAction('<%=lom.get("cnsdreq_id") %>', '<%=lom.get("prcs_depth")%>','<%=lom.get("depth_status")%>');">
					        <span style='color:#485B91'><%=StringUtil.convertHtmlTochars((String)lom.get("req_title")) %></span></a><br/>
					        <span style='color:#A9B4BC'>(<%=StringUtil.convertHtmlTochars((String)lom.get("cntrt_nm")) %>)</span>
				      </td>
				      <% sover_title = lom.get("cntrt_respman_nm")!=null && !lom.get("cntrt_respman_nm").equals("")? "&#13;" + lom.get("cntrt_respman_nm") : ""; %>
				      <td class="txtover tC" title="<%=lom.get("reqman_nm")+sover_title %>">
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
				      <td class="txtover tC" title="<%=lom.get("cntrt_no") %>"><%=lom.get("cntrt_no") %>				      
				            <%if(lom.get("tnc_no")!=null && !lom.get("tnc_no").equals("")) { %>
				            <br/><span style='color:#A9B4BC;'>(<%=lom.get("tnc_no") %>)</span>
				            <%  } %>
				            
				      </td>
				      <td class="txtover tC" title="<%=lom.get("prcs_depth_nm") %>"><%=lom.get("prcs_depth_nm") %></td>
				      <td class="txtover tC" title="<%=lom.get("depth_status_nm") %>"><%=lom.get("depth_status_nm") %><%=lom.get("hq_cnsd_status_nm") %></td>
				      <td class="txtover tC" title="<%=lom.get("gerp_code_desc") %>"><%=lom.get("gerp_code_desc") %></td>
				      <td class="txtover tC" title=""> 
				      	<%if("Y".equals(lom.get("close_yn"))) { %>
							<a href='javascript:popCloseCont("<%=lom.get("cntrt_id") %>");'><spring:message code="clm.page.field.qna.pubYnN"/></a>
				      	<%  }else{ %><%  } %>
				      </td>
			     </tr>
		      <%	}
				}else{		  
 			  %> 
				<tr>
				  <td colspan="11" class='tC'><spring:message code="secfw.msg.succ.noResult" /></td>
				</tr>
			  <%}%>			
			  </tbody>
			</table>
			<!-- //list -->
		
				<!-- total data -->
				<div class="total_num">
				<%if("cnsdreq".equals(command.getList_mode())) {%>
					Total
				<%} else { %>
					Total
				<%} %>
					: <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%>
				</div>
				<!-- //total data -->
				
				<!-- pagination  -->
				<div class="pagination mt_22">
				<%=pageUtil.getPageNavi(pageUtil, "") %>
				</div>
				<!-- //pagination -->
			</form:form>
		</div>
		</div>
		<!-- //content -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
<!-- footer -->
<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
<!-- //footer -->

</body>
</html>

