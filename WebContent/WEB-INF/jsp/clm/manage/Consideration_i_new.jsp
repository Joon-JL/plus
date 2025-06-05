<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sec.clm.manage.dto.ConsultationForm" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%--
/**
 * 파  일  명 : Consideration_i_new.jsp
 * 프로그램명 : 검토의뢰 최초 작성
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2013.03
 * 수  정  일 : 
 * 수  정  자 : 
 * 수정  내용 :
 */
--%>
<% 
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	ConsultationForm command = (ConsultationForm)request.getAttribute("command");
	ListOrderedMap lomRq = (ListOrderedMap)request.getAttribute("lomRq");
	ArrayList listDc = (ArrayList)request.getAttribute("listDc");	
	ArrayList listCa = (ArrayList)request.getAttribute("listCa");	
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="clm.main.title" /></title>

<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="/script/clms/common.js"></script>
<script language="javascript" src="/script/secfw/common/common.js"></script>                                                                                                                                                
<script language="javascript" src="/script/secfw/common/CommonProgress.js"></script>
<script language="javascript" src="/script/clms/clms_common.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.form.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.flot.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="/script/secfw/jquery/calendar/jquery.mousewheel.min.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script>
var chk = 0;
var chk_flag = false;

    $(document).ready(function(){
    	
    	if("<c:out value='${lomRq.cnclsnpurps_midclsfcn}'/>" != "" ){
		
			if(!("<c:out value='${lomRq.cnclsnpurps_midclsfcn}'/>" == "T030705" || "<c:out value='${lomRq.cnclsnpurps_midclsfcn}'/>" == "T030706") ){
				tncfunc();
			}
		
		}
	
    	$("#type_sel21").click(function(){
    		
    		re_openChooseContractType();
    		
    	});
    	$("#trCntrtUntprc").hide();
    	
    	
    	paymentGbnChange();
    	init();
    	
        tncOpenYn();

    });
    
    function tncOpenYn(){
    	
    	var frm = document.frm;
    	
    	//alert("frm.cnclsnpurps_midclsfcn.value = " +frm.cnclsnpurps_midclsfcn.value);
    	//alert("frm.biz_clsfcn.value = " +frm.biz_clsfcn.value);
    	//alert("frm.depth_clsfcn.value = " +frm.depth_clsfcn.value);
    	
//     	alert("frm.cntrt_trgt.value = "+frm.cntrt_trgt.value);
//     	alert("<c:out value='${lomRq.sys_nm}'/>");
    	
    	// if( ($(":input:radio[name=type1]:checked").val() == 'T0103') && ($(":input:radio[name=type2]:checked").val() == 'T0201') && ($(":input:radio[name=type3]:checked").val() == "T030705" || $(":input:radio[name=type3]:checked").val() == "T030706" )){
    	if( frm.biz_clsfcn.value == 'T0103' &&  frm.depth_clsfcn.value == 'T0201' && ( frm.cnclsnpurps_midclsfcn.value == "T030705" ||  frm.cnclsnpurps_midclsfcn.value == "T030706") ){
    		if("<c:out value='${lomRq.sys_nm}'/>" == "TNC" && (frm.cntrt_trgt.value == "T030705006" || frm.cntrt_trgt.value == "T030705003" || frm.cntrt_trgt.value == "T030705002") ){
    			tncfunc();
    		}else{
    			tncfunc_false();
    		}
    	} else {
    		tncfunc();
    	}
    }
	
    function tncfunc(){
    
    	//alert("1");
    	
    	$("#cntrt_top30_cus").attr('disabled', true);
		//$("#cntrt_top30_cus").val("C05606");
		$("#cntrt_src_cont_drft").attr('disabled', true);
		//$("#cntrt_src_cont_drft").val("SOC10");
    }
    
    
    function tncfunc_false(){
    	
    	//alert("2");
    	
    	$("#cntrt_top30_cus").attr('disabled', false);
		//$("#cntrt_top30_cus").val("C05606");
		$("#cntrt_src_cont_drft").attr('disabled', false);
		//$("#cntrt_src_cont_drft").val("SOC10");
    }
    
	function init(){
		
		var frm = document.frm;
		
		initCal("re_demndday");
		initCal("cntrtperiod_startday");
		initCal("cntrtperiod_endday");
		initCal("bfhdcstn_apbtday");
		
		//계약단가요약에 데이터가 있으면 단가내역요약 보이게 수정.
		if(frm.cntrt_untprc_chk.value != ""){
			document.getElementById("cntrt_untprc").checked = true;
			$("#trCntrtUntprc").show();
		}
		
		<%	if("Y".equals(lomRq.get("plndbn_req_yn"))) { %>
		    frmChkLen(frm.plndbn_chge_cont,2000,'curByteExpl2');
		<%	} %>
		
		// 수정화면인 경우 화면로드시 금액 100,000 형태로 변환
        var amt = frm.cntrt_amt.value;
		
		if(amt.indexOf(".00") != -1){
			amt = amt.substring(0, amt.indexOf(".00"));
		}
		
        frm.cntrt_amt.value = Comma(amt);
		
		if("<c:out value='${lomRq.crrncy_unit}'/>" == ""){
            $("#cntrt_amt").val("");
            $("input[name=cntrt_amt]").attr('disabled', true);
            $("input[name=cntrt_untprc]").attr('checked', false);
            $("#crrncy_unit").attr('disabled', true);
            $("#crrncy_unit").html("<option value=><spring:message code='clm.page.field.srch.payment_gbn_value4' /></option>");
            $("input[name=cntrt_untprc]").attr('disabled', true);
            $("#trCntrtUntprc").hide();
            $("#cntrt_untprc_expl").val("");
            //단가내역 요약 첨부파일 초기화
            initCntrtUntprcExpl();
        }else{
            
            if($("#cntrt_untprc_expl").val()){
                $("#trCntrtUntprc").show();
                $("[name=cntrt_untprc]").attr('checked', true);
            }
            //Currency unit 초기 불러오기 처리 2014-03-20 신성우
            var currency = "<c:out value='${lomRq.crrncy_unit}'/>";
            $("#crrncy_unit").val(currency).attr("selected", "selected");
        }
		
		var collapseIcon = '<%=IMAGE%>/icon/ico_plus.gif';
        var expandIcon = '<%=IMAGE%>/icon/ico_minus.gif';
        
        //검토이력
        $('img[alt$=show]').toggle(function(){
            $(this).removeAttr().attr("src",expandIcon);
            $(this).parent().parent().parent().next('#tr_show').show();     
        }, function(){
            $(this).removeAttr().attr("src",collapseIcon);
            $(this).parent().parent().parent().next('#tr_show').hide();
        });
        //승인이력
        $('img[alt$=show01]').toggle(function(){
            $(this).removeAttr().attr("src",expandIcon);
            $(this).parent().parent().parent().next('#tr_show01').show();
            
        }, function(){
            $(this).removeAttr().attr("src",collapseIcon);
            $(this).parent().parent().parent().next('#tr_show01').hide();
        });
        //체결이력
        $('img[alt$=show02]').toggle(function(){
            $(this).removeAttr().attr("src",expandIcon);
            $(this).parent().parent().parent().next('#tr_show02').show();
            
        }, function(){
            $(this).removeAttr().attr("src",collapseIcon);
            $(this).parent().parent().parent().next('#tr_show02').hide();
        });

	    contractHisList();	//Sungwoo added 2014-09-08
        
        initPage();
        
        //신규등록시 계약유형팝업을 뛰운다
        if(document.frm.status.value=="forwardInsert") {
    		openChooseContractType();
    	}
	}
	
	function initPage(){	
		var frm = document.frm;
		
		$('input[id^=fileInfos]').val("");
		var view = "";
		var file_key = "";
		
		<% if("forwardInsert".equals(lomRq.get("status"))){%>
			view="modify";
			file_key = frm.mod_cntrt_id.value+"@"+frm.cnsdreq_id.value;
		<% }else if("forwardReq".equals(lomRq.get("status")) || "forwardLast".equals(lomRq.get("status"))){%>
			view="modify";
			file_key = frm.mod_cntrt_id.value+"@"+frm.cnsdreq_id.value;
		<% }else{%>
			view="modify";
			file_key = frm.mod_cntrt_id.value+"@"+frm.cnsdreq_id.value;
		<% }%>
		
	    //계약관련 #1 계약서 - 계약서
	     frm.target          = "fileList1";
	     frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	     frm.method.value    = "forwardClmsFilePage";
	     frm.file_bigclsfcn.value = "F012";
	     frm.file_midclsfcn.value = "F01202";
	     frm.file_smlclsfcn.value = "F0120201";
		 frm.ref_key.value = file_key;
		 frm.view_gbn.value = view;
		 frm.multiYn.value = "N";
	     frm.fileInfoName.value = "fileInfos1";
	     frm.fileFrameName.value = "fileList1";
	     frm.submit(); 
	    
	    //fileListEtc-첨부파일 첨부/별첨
	     frm.target          = "fileListEtc";
	     frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	     frm.method.value    = "forwardClmsFilePage";
	     frm.file_bigclsfcn.value = "F012";
	     frm.file_midclsfcn.value = "F01202";
	     frm.file_smlclsfcn.value = "F0120208";
	     frm.ref_key.value = file_key;
	     frm.view_gbn.value = view;
		 frm.multiYn.value = "Y";
	     frm.fileInfoName.value = "fileInfosEtc";
	     frm.fileFrameName.value = "fileListEtc";
	     frm.submit();
	     
	   //계약관련 #2 계약서- 기타
	     frm.target          = "fileList2";
	     frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	     frm.method.value    = "forwardClmsFilePage";
	     frm.file_bigclsfcn.value = "F012";
	     frm.file_midclsfcn.value = "F01202";
	     frm.file_smlclsfcn.value = "F0120205";
	     frm.ref_key.value = file_key;
	     frm.view_gbn.value = view;
	     frm.multiYn.value = "Y";
	     frm.fileInfoName.value = "fileInfos2";
	     frm.fileFrameName.value = "fileList2";
	     frm.submit();
	     
	   //계약관련 #3 단가내역 
	     frm.target          = "fileList3";
	     frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	     frm.method.value    = "forwardClmsFilePage";
	     frm.file_bigclsfcn.value = "F012";
	     frm.file_midclsfcn.value = "F01202";
	     frm.file_smlclsfcn.value = "F0120211";
	     frm.ref_key.value = frm.mod_cntrt_id.value;
	     frm.view_gbn.value = view;
	     frm.fileInfoName.value = "fileInfos3";
	     frm.fileFrameName.value = "fileList3";
	     frm.submit();
	     
	   //계약관련 #4 사전검토정보 
	     frm.target          = "fileList4";
	     frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	     frm.method.value    = "forwardClmsFilePage";
	     frm.file_bigclsfcn.value = "F012";
	     frm.file_midclsfcn.value = "F01201";
	     frm.file_smlclsfcn.value = "F0120101";
	     frm.ref_key.value = frm.mod_cntrt_id.value ;
	     frm.view_gbn.value = view;
	     frm.multiYn.value = "Y";
	     frm.fileInfoName.value = "fileInfos4";
	     frm.fileFrameName.value = "fileList4";
	     frm.submit();
	     
	  	// 2014-04-08 Kevin Added.
		// GERP readonly iframe load. 
		frm.target          = "iframeGERP";
		frm.action          = "<c:url value='/clm/manage/consideration.do' />";
		frm.gerpPageType.value = "I";		// Input form
		frm.method.value    = "forwardGERPDetail";
		frm.submit();
	}	
  	
	function forwardConsideration(arg){
		var frm = document.frm;	
		frm.target = "_self";		 
		frm.action = "<c:url value='/clm/manage/consideration.do' />";
		frm.method.value = "listManageConsideration";			
		frm.submit();		
	}
	
	// 버튼 영역
	function forwardInsertConsideration(arg){
		requiredValidation(arg);
	}
	
	// 2014-04-14 Kevin. iframeGERP에 있는 gerp 정보를  hidden 필드에 바인딩 한다.
	function getGERPInfo(){
		var doc = window.frames["iframeGERP"].document;
		var selectedCostType = $(doc).find("#sCostType").val();
		var seelctedContractType = $(doc).find("#sContractType").val();
		var selectedDivCode = $(doc).find("#sGERPDivList").val();
		
		$("#gerpCostType").val((selectedCostType == null ? "" : selectedCostType));
		$("#gerpContractType").val((seelctedContractType == null ? "" : seelctedContractType));
		$("#gerpDivCode").val((selectedDivCode == null ? "" : selectedDivCode));
	}
	
	// 계약정보 입력
	function insertConsideration(arg){

		viewHiddenProgress(true);
		// 2014-04-14 Kevin added.
		getGERPInfo();
		
		var options = {
			url: "<c:url value='/clm/manage/consideration.do?method=insertConsideration' />",
			type: "post",
			async: false,
			dataType: "json",			
			success: function(responseText, statusText) {  //undefined
	    		//계약서 필수 에서 빼기 최종 의뢰 ,,,,,,재의뢰시 에 .....최종본
	    		viewHiddenProgress(false);
	    		
				if(responseText.returnValue == undefined){
					
					alert("<spring:message code="clm.page.msg.manage.announce157" />");
					forwardInsertContractMaster();  
					tit_Toggle(this, 'tr_down02');
				}else if(("save" == arg || "last" == arg) && responseText.returnValue == "N"){				
					alert(responseText.msg);//필수 입력 항목 안내문
					//현재 계약 탭 load 하기		
					forwardInsertContractMaster();
					tit_Toggle(this, 'tr_down02');					
					
				}else if(("again" == arg || "save" == arg || "last" == arg || "reqIF"==arg) && responseText.returnValue == "Y"){					
					alert(responseText.msg);//정상처리 되었습니다. 					
					forwardConsideration('list');//리스트로 이동 
					
				}else if("req" == arg && responseText.returnApproval == "N"){
					alert("<spring:message code="clm.page.msg.manage.announce019" />");
					setApprovalYN("N");
				}else if("req" == arg && responseText.returnValue == "Y"){				
					alert("<spring:message code="clm.page.msg.manage.announce167" />");	
					forwardConsideration('list');//리스트로 이동 
				}else if(responseText.returnValue == "N"){   // N으로의 값이 넘어와서 N만 처리하는 항목 추가하였습니다.
					alert(responseText.msg);
					forwardInsertContractMaster();
					tit_Toggle(this, 'tr_down02');
				}				
			}
		};
		
		//계약관련#0
		fileList1.UploadFile(function(uploadCount){
			
			if(uploadCount == -1){
				initPage();
				alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
				viewHiddenProgress(true);
				return; 					
			}
			if(uploadCount == 0 && "Y" == $('#plndbn_req_yn').val() && arg != "save"){
				alert("<spring:message code="clm.page.msg.manage.contract" /> <spring:message code='secfw.msg.error.fileNon' />");
				viewHiddenProgress(false);
                  return;
			}
			//계약서 첨부/별첨
			fileListEtc.UploadFile(function(uploadCount){
  				if(uploadCount == -1){
  					initPage();
  					alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
  					viewHiddenProgress(false);
  					return; 					
  				}
  					
				 //계약관련#1
	  			 fileList2.UploadFile(function(uploadCount){
	  				if(uploadCount == -1){
	  					initPage();
	  					alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
	  					viewHiddenProgress(false);
	  					return; 					
	  				}
	  				
	  			 	fileList3.UploadFile(function(uploadCount){
	  					if(uploadCount == -1){
	  						alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
	  						viewHiddenProgress(false);						
	  						return; 					
	  					}
	  					
	  					//계약관련#3
	 	    			 fileList4.UploadFile(function(uploadCount){
	 	    				if(uploadCount == -1){
	 	    					alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
	 	    					viewHiddenProgress(false);
	 	    					return; 					
	 	    				}	 	 	    					 	 	    				
	    	  	  				
 	    	  	  			$("#frm").ajaxSubmit(options);
 	    	  	  			
	  			 		});//end fileList4
	  			 	});//end fileList3 				
			 	});//end fileList2
			});//end fileListEtc  		
		 });//end fileList1 	
	}
	
	/**
	* validation check function
	*/
	function requiredValidation(arg){
  		var frm = document.frm;  		
		frm.submit_status.value = arg;
  		frm.target = "_self";
  		var notice = "";
  		
  		if(frm.parent_cntrt_name.value.length > 0){
  			alert("<spring:message code="clm.page.msg.manage.announce300" />");
  			return;
  		}
  		
  		if(arg == "save"){	//임시저장  			
  			if(!confirm("<spring:message code="clm.page.msg.manage.announce137" />")) return;  			
  		}else if(arg == "req" && "IF" !=$('#req_reg_id').val()){ //검토의뢰(상신)
			notice = "<spring:message code="clm.page.msg.manage.announce017" />";
		    if(!confirm(notice)) return;
  		}else if(arg == "req" && "IF" ==$('#req_reg_id').val()){ //검토의뢰(상신)
  			arg = "reqIF";
  			frm.submit_status.value = arg;  	
  			notice = "<spring:message code="clm.page.msg.manage.announce017" />";
		    if(!confirm(notice)) return;		
  		}else if(arg == "again"){	//재검토의뢰 	
  			notice = "<spring:message code="clm.page.msg.manage.announce144" />";
		    if(!confirm(notice)) return;	
  		}else if(arg == "last"){	//최종본	
  			if(!confirm("<spring:message code="clm.page.msg.manage.announce184" />")) return;  					
  		}else{	//목록  			
  			forwardConsideration('list');//리스트로 이동 
  			return;
  		}
  		
  		if(validateTempSave(frm)){ 	
  		
  	  		if(!frm.req_title.value){//의뢰제목REQ_TITLE
  	  			alert("<spring:message code="clm.page.msg.manage.announce022" />");
  	  			return;  	  			
  	  		}
  	  		
  	  		if(!frm.body_mime.value){//검토요청 내용 CNSD_DEMND_CONT
	  			alert("<spring:message code="clm.page.msg.manage.announce018" />");
	  			$("#body_mime").focus();
  	  			return;  	  			
	  		}
  	  		if(!frm.cntrt_oppnt_cd.value){//계약상대방
	  			alert("<spring:message code="clm.page.msg.manage.announce012" />");
  	  			return;  	  			
	  		}
  	  		if(!frm.biz_clsfcn.value){//계약유형
  				alert("<spring:message code="clm.page.msg.manage.announce052" />");
  	  			return;  	  			
  			}  			
  		}
		
  		// 단가로체결일경우 단가내역요약 필수
  		if(frm.cntrt_untprc.checked){
  			if(frm.cntrt_untprc_expl.value==""){
  				alert("<spring:message code="clm.page.msg.manage.announce074" />") ;
  				return ;
  			}
  		}
  		

		//Sungwoo added 2014-11-10 validation top 30
		if(!frm.cntrt_top30_cus.disabled && frm.cntrt_top30_cus.value == ""){
			alert("<spring:message code="clm.page.msg.manage.top30Cus" />" + "<spring:message code="las.msg.alert.isRequired" />");
			return;
		}

  		
  		// 김재원 체크 로직 추가 함.
		if(frm.cntrt_rel_prd.value  == ""){
			alert("<spring:message code="clm.page.msg.manage.announce215" />");
			return;
		}
		
		//Sungwoo added 2014-11-10 validation source of contract draft
		if(!frm.cntrt_src_cont_drft.disabled && frm.cntrt_src_cont_drft.value == ""){
			alert("<spring:message code="clm.page.msg.manage.srcContDraft" />" + "<spring:message code="las.msg.alert.isRequired" />");
			return;
		}

  	  	// Sungwoo validation 수정 2014-05-19 
  		if(frm.cntrtperiod_startday.value == "" || frm.cntrtperiod_endday.value == ""){
			alert("<spring:message code="clm.page.msg.manage.announce041" />");
			return;
		}

		// 김재원 체크 로직 추가(payment/collection이 임시저장(arg=sava인 경우)인 경우 필수 체크 하지 않음.)
		if(arg != "save"){
			if(frm.payment_gbn.value==""){
	  			alert("<spring:message code="clm.page.msg.manage.announce163" />") ;
	  			return ;
	  		}
		}
		
  		if(validateClmForm(frm)){
  			if(arg == "req" || arg == "again" || arg == "last" ){
  				  				
		  		//의뢰일과 회신요청일 
	 			var str_req_dt = frm.req_dt.value.replace('-','').replace('-','');
	 			var str_re_demndday = frm.re_demndday.value.replace('-','').replace('-','');
	 			var str_db_demndday = "<c:out value='${lomRq.re_demndday}' />".replace('-','').replace('-','');
		 			
	 			if(eval(str_req_dt) > eval(str_re_demndday)){
	 				alert("<spring:message code="clm.page.msg.manage.announce211" />");
	 				return;
	 			}
	
	 			if(eval(str_db_demndday) > eval(str_re_demndday)){
	 				alert("<spring:message code="clm.page.msg.manage.announce210" />");
	 				return;
	 			}
	 			  
	  			if(!frm.cntrt_trgt.value){
	  				alert("<spring:message code="clm.page.msg.manage.announce028" />");return;
	  			}
	  			
	  			//계약 유형 Other일 경우 비고 내용 체크
	  			var chkCode ="<spring:message code="las.page.validation.CodeMngList" />";
	  			var arrchkCode = chkCode.split("|");

	  			for(var i =0;i < arrchkCode.length;i++){
	  				if(frm.cntrt_trgt.value == arrchkCode[i] && frm.cntrt_trgt_det.value == '' ){
		  				alert("<spring:message code="clm.page.msg.manage.announceDetails028" />");
		  				return;
		  			}
	  			}
	  			
	 			//계약기간 Chk
	 			var str_from = frm.cntrtperiod_startday.value.replace('-','').replace('-','');
	 			var str_to = frm.cntrtperiod_endday.value.replace('-','').replace('-','');
	 			var cue = frm.cntrt_untprc_expl.value.replace(/^\s+/,""); // lpad 하는 것입니다.
	 			
	 			if(eval(str_from) > eval(str_to)){
	 				alert("<spring:message code="clm.page.msg.manage.announce039" />");
	 				return;
	 			}
	 			
	 			//지불/수금 구분,
				if(!frm.payment_gbn.value){
					// 김재원 체크 로직 추가(payment/collection이 임시저장(arg=sava인 경우)인 경우 필수 체크 하지 않음.)
					if(arg != "save"){
						alert("<spring:message code="clm.page.msg.manage.announce163" />");
						return;
					} 
				}else{
	  			//지불 or 수불 일때 계약 금액 필수
		  			if("C02002" == frm.payment_gbn.value || "C02003" == frm.payment_gbn.value || "C02001" == frm.payment_gbn.value ){
		  				if($("[name=cntrt_untprc]").is(":checked")){
		  					if("" == frm.cntrt_untprc_expl.value){  			
		  		  				//cntrt_untprc_expl
		  		  				alert("<spring:message code="clm.page.msg.manage.announce027" />");  				
		  		  				return;
		  					}
		  				}else{
		  					//계약금액은 필수 입력 항목 입니다. 
							if(frm.cntrt_amt.value == "" || eval(frm.cntrt_amt.value) < 1 ){
								alert("<spring:message code="clm.page.msg.manage.announce038" />");
								return;
							}
							if( frm.crrncy_unit.value == ""){
								alert("<spring:message code="clm.page.msg.manage.announce189" />");
								return;
							}
		  				}
		  			}
	  			}
	 		
	 			if($("[name=cntrt_untprc]").is(":checked")){
	 				if("" == frm.cntrt_untprc_expl.value){  			
	  				//cntrt_untprc_expl
	  				alert("<spring:message code="clm.page.msg.manage.announce027" />");  				
	  				return;
	 				} 				
	 				if(cue.length == 0){
	 	  				alert("<spring:message code="clm.page.msg.manage.announce061" />");
	 	  				return;
	 	  			}
	 			} 
	
	  		  	//사전품의발의자
	 			if(!frm.bfhdcstn_mtnman_id.value){
	 				alert("<spring:message code="clm.page.msg.manage.announce098" />");
	 				return;
	 			}
	  			//사전품의승인자
	 			if(!frm.bfhdcstn_apbtman_id.value){
	 				alert("<spring:message code="clm.page.msg.manage.announce101" />");
	 				return;
	 			}
	  			//사전품의승인일
	 			if(!frm.bfhdcstn_apbtday.value){
	 				alert("<spring:message code="clm.page.msg.manage.announce100" />");
	 				return;
	 			}
	  			//사전품의승인방식
	 			if(!frm.bfhdcstn_apbt_mthd.value){
	 				alert("<spring:message code="clm.page.msg.manage.announce099" />");
	 				return;
	 			}
				if("Y" == $('#file_validate').val() && $("#cnsdreq_id").val() != "" && "Y" == $('#plndbn_req_yn').val()){
		 				alert("<spring:message code='clm.page.msg.manage.announce009' />");
		 				return;
		 		}
  			}
  			
  			//임시저장시에도 필수로 해줘야 함. 왜냐하면 페이지 로딩시 crrncy_unit값이 없을 경우 cntrt_amt를 삭제하기때문에 필수로 함.
			if("C02002" == frm.payment_gbn.value || "C02003" == frm.payment_gbn.value || "C02001" == frm.payment_gbn.value ){
					//계약금액은 필수 입력 항목 입니다. 
					if(frm.cntrt_amt.value != "" && frm.crrncy_unit.value == ""){
						alert("<spring:message code="clm.page.msg.manage.announce189" />");
						return;
						}
				}
				
			//TNC 데이타일 경우 Contract matter값이 OTC+CTC, OTC only, CTC only를 선택하면,연관계약이 필수가 되어야 함
 			if("TNC" == frm.sys_nm.value && (frm.cntrt_trgt.value == "T030705006" || frm.cntrt_trgt.value == "T030705003" || frm.cntrt_trgt.value == "T030705002")){
 				
 				if($("#db_parent_cntrt_id").val() == ""){
 					alert("<spring:message code="clm.page.msg.manage.announce300" />");
 	 				$("#rel_type").focus();
 	 				return;
 				}
 				
 			}
		
  				
	  		// 검토의뢰, 재검토의뢰, 최종본의뢰인 경우 활성화 되지 않은 계약건의 필수입력 체크
	  		if(arg == "req" || arg == "again" || arg == "last"){
				var options = {
					url: "<c:url value='/clm/manage/consideration.do?method=requiredValidation' />",
					type: "post",
					dataType: "json",
					success: function(returnJson,returnMessage) {
						if(returnJson.result == "Y") {
							if(arg== "again"){
								var options = {
										url: "<c:url value='/clm/manage/consideration.do?method=checkVacation' />",
										type: "post",
										dataType: "json",
										success: function(returnJson,returnMessage) {
											if(returnJson.vacation == "V"){
												alert('<spring:message code="las.msg.alert.reRequest.onVaction"/>\n\n'+returnJson.result);
											}else if(returnJson.vacation == "E"){
												alert('<spring:message code="las.msg.alert.reRequest.Conference"/>\n\n'+returnJson.result);
											}else if(returnJson.vacation == "B"){
												alert('<spring:message code="las.msg.alert.reRequest.BusinessTrip"/>\n\n'+returnJson.result);
											}
												insertConsideration(arg);
										}
								    };
									$("#frm").ajaxSubmit(options);
							}else{
								insertConsideration(arg);
							}
								
						}else{
							alert(returnJson.message);
							return;
						}
					}
			    };
				$("#frm").ajaxSubmit(options);
	  		}else{
	  			insertConsideration(arg);
	  		}
		}
	}
	
  	
  	//글자수 제한
  	function f_chk_byte(aro_name,ari_max) {   
        var ls_str     = aro_name.value;
        var li_str_len = ls_str.length;
        var li_max      = ari_max;
        var i           = 0;
        var li_byte     = 0;
        var li_len      = 0;
        var ls_one_char = "";
        var ls_str2     = "";
    
        for(i=0; i< li_str_len; i++) {
            ls_one_char = ls_str.charAt(i);
            if (escape(ls_one_char).length > 4) 
                li_byte += 2;
            else 
                li_byte++;
            
            if (li_byte <= li_max) li_len = i + 1;
        }

        if(li_byte > li_max) {
            alert("<spring:message code="clm.page.msg.manage.announce139" />");
            ls_str2 = ls_str.substr(0, li_len);
            aro_name.value = ls_str2;
        }
        aro_name.focus();   
    }

  	/**
  	* 결재자 정보 / 승인자 정보 가져올대 그룹장 이하이면 그룹장 이상을 지정 하시기 바랍니다. 
  	* 직급 이하 유무 확인 
  	*/
  	function listTbComCd(arg){
  	    var frm = document.frm;
  	    var options = {
  	        url: "/common/clmsCom.do?method=listTbComCd&grp_cd=C061&cd="+arg,
  	        type: "post",
  	        dataType: "json",
  	        success: function(responseText,returnMessage) {
  	            if(responseText != null && responseText.length != 0) {
  	                $.each(responseText, function(entryIndex, entry) {
  	                    //entry['DB컬럼명'] : TB_COM_CD 에서 조회된 "DB컬럼" 값
  	                    if(entry['useman_mng_itm1'] =="" || entry['useman_mng_itm1'] == "N"){  	                    	
  	                    	alert("<spring:message code="clm.page.msg.manage.announce062" />");  	                    	
  	                    }
  	                });
  	            }
  	        }
  	    };
  	    $("#frm").ajaxSubmit(options);
  	}

  	function paymentGbnChange(){  		
  	
  		//payment_gbn 지수불 구분이 해당사항 없음이면 계약 금액 은 입력 불가 처리
  		//계약금액,통화단위 ,계약단가 비활성화
  		if($('#payment_gbn > option:selected').val() == "C02004" ){
			//cntrt_amt 계약금액			
			$("#cntrt_amt").val("");
			$("input[name=cntrt_amt]").attr('disabled', true);
			$("input[name=cntrt_untprc]").attr('checked', false);
			$("#crrncy_unit").attr('disabled', true);
			$("#crrncy_unit").html("<option value=><spring:message code='clm.page.field.srch.payment_gbn_value4' /></option>");
			$("input[name=cntrt_untprc]").attr('disabled', true);
			$("#trCntrtUntprc").hide();
			$("#cntrt_untprc_expl").val("");
			// 김재원 추가(필수 체크 여부 삭제)
			$("#view_astro").hide();
			//단가내역 요약 첨부파일 초기화
			initCntrtUntprcExpl();
		}else{			
			$("input[name=cntrt_amt]").attr('disabled', false);	

			//invisible상태에서 재 로드시 리스트 다시 가져와야 해서 주석 해제처리 신성우 2014-03-20
			getCodeSelectByUpCd("frm", "crrncy_unit", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=A&combo_grp_cd=C5&combo_type=S&combo_del_yn=N');
			$("input[name=cntrt_untprc]").attr('disabled', false);
			$("#crrncy_unit").attr('disabled', false);
			// 김재원 추가(필수 체크 여부 표시)			
			$("#view_astro").show();
		}
  	}
  	
  	function paymentGbnChange2(){
  		$("input[name=cntrt_untprc]").attr('checked', false);
  	}
  	
  	//단가내역 요약 첨부파일 초기화
  	function initCntrtUntprcExpl(){
  		
  		frm.target          = "fileList3";
	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    = "forwardClmsFilePage";
	    frm.file_bigclsfcn.value = "F012";
	    frm.file_midclsfcn.value = "F01202";
	    frm.file_smlclsfcn.value = "F0120211";
	    frm.ref_key.value = "";
	    frm.view_gbn.value = "upload";
	    frm.fileInfoName.value = "fileInfos3";
	    frm.fileFrameName.value = "fileList3";
	    frm.submit();
  	}
  	
  	 /**
	  *계약 건 데이탕 저장   - master tb insert or update submit
	  */	  
	  function insertContractMaster(){
  		var frm = document.frm;
  		
  			var options = {
				url: "<c:url value='/clm/manage/consideration.do?method=insertContractMaster' />",
				type: "post",
				async: false,
				dataType: "json",			
				success: function(responseText, statusText) {  //undefined
					if(responseText.returnCd == undefined){
						alert("<spring:message code="clm.page.msg.manage.announce157" />");
					}else{						
						if("update" != responseText.exeStatus){
							var html = "<input type=\"hidden\" value=\""+responseText.returnCntrtId+"\" name=\"arr_cntrt_id\" id=\"arr_cntrt_id\" />";
							$("#cnsdreq_id").before(html);
							//cnsdreqId 새로운 포맷에 의뢰 아이디 설정  11.01 chaahyunjin
							
							if(!$('#cnsdreq_id').val()){
								$('#cnsdreq_id').val(responseText.returnCnsdReqId);
							}
						}
						addTabCnt();
						var tabCnt = getTabCnt();
						
						var tabTitle = document.getElementById("tab_title"); 
						var tabTitleLi = document.createElement("li"); 
						tabTitle.appendChild(tabTitleLi);	 
						tabTitleLi.id="tab_li"+tabCnt+"";
						tabTitleLi.innerHTML = "<a href=\"javascript:titleTabClick('"+tabCnt+"','');\">계약"+tabCnt+"</a>";
				  		
						for(var i=1; i<=tabCnt; i++){			
							if(tabCnt==i){
								document.getElementById("tab_li"+i+"").className = "on";
						    }else{
							    document.getElementById("tab_li"+i+"").className = "";
						    }		  
						}
						forwardInsertContractMaster();
						
						tit_Toggle(this, 'tr_down02');
												
						if("T0103" == frm.biz_clsfcn.value){//본계약인 경우만 특화속성 생성
						listSpecialAttr(frm.cnclsnpurps_bigclsfcn.value,frm.cnclsnpurps_midclsfcn.value);
						}
						
					}
				}
			};
  			
  			//계약관련#1
			 fileList1.UploadFile(function(uploadCount){
				if(uploadCount == -1){
					initPage();
					alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
					return; 					
				}
				if(uploadCount == 0 && "Y" == $('#plndbn_req_yn').val() ){
	 					alert(" <spring:message code="clm.page.msg.manage.contract" /> <spring:message code='secfw.msg.error.fileNon' />");
	                    return;
	 			}
				//계약서 첨부/별첨
 				fileListEtc.UploadFile(function(uploadCount){
 	  				if(uploadCount == -1){
 	  					initPage();
 	  					alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
 	  					return; 					
 	  				}
 	  				
					 //계약관련#2
		  			 fileList2.UploadFile(function(uploadCount){
		  				if(uploadCount == -1){
		  					initPage();
		  					alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
		  					return; 					
		  				}
		  				//계약관련#3
		  			 	fileList3.UploadFile(function(uploadCount){
		  					if(uploadCount == -1){
		  						alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
		  						return; 					
		  					}		  					
		  					//계약관련#4
		 	    			 fileList4.UploadFile(function(uploadCount){
		 	    				if(uploadCount == -1){
		 	    					alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
		 	    					return; 					
		 	    				}
		 	    				viewHiddenProgress(true);
		 	    				$("#frm").ajaxSubmit(options);
		 	    						  			 
		  			 		});//end fileList4
		  			 	});//end fileList3					
				 	});//end fileList2
 				});//end fileListEtc 		
 			 });//end fileList1
  		//contract tab add
	  }
  	 /**
  	 * 파일얼로드 저장
  	 */
  	 function saveFileUpload(){
  		 var frm = document.frm;
  		 
  		 frm.target = "_self";
		 frm.action = "<c:url value='/clm/manage/consideration.do' />";
		 frm.method.value = "saveFileUpload";
		 	//#1
  			fileList1.UploadFile(function(uploadCount){
  				if(uploadCount == -1){
  					initPage();
  					alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
  					return; 					
  				}  				
  			 });  					 
  	 }
	 
	  /**
	  * 연관계약정보 RoW ADD
	  */
	  function addRelCntrt(){
		  var html ="<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\">  "							
			  +	"<td class=\"tal_lineL\"><span class=\"th-color\"><spring:message code='clm.page.field.contract.detail.relation.name' /></span></td>  "	
			  +	"<td colspan=\"5\">  "	
			  +	"<input type=\"hidden\" name=\"arr_parent_cntrt_id\" id=\"parent_cntrt_id\" value=\"12345678\" /><!-- 모 계약 Id -->  "	
			  +	"<input type=\"hidden\" name=\"arr_parent_cntrt_nm\" id=\"parent_cntrt_nm\" value=\"\" style=\"width:400px\" class=\"text_search\"/><img src=\"<%=IMAGE%>/icon/ico_search.gif\" onclick=\"javascript:open_window('win', '../popup/project_proccess_pop.html', 0, 0, 600, 395, 0, 0, 0, 0, 0);\" class=\"cp\" alt=\"search\" />  "	
			  +	"</td>  "	
			  +	"</tr>  "	
			  +	"<tr>  "							
			  +	"<td class=\"tal_lineL\"><span class=\"th-color\"><spring:message code='clm.page.field.contract.detail.relation.kind' /></span></td>  "	
			  +	"<td colspan=\"5\">  "	
			  +	"<select name=\"arr_rel_type\" id=\"rel_type"+($('#tab_contents_sub_conts1 tr').length+1)+"\" class=\"all\" style=\"width:100px\">  "							
			  +	"</select>  "	
			  +	"<select name=\"arr_rel_type_etc\" id=\"rel_type_etc"+($('#tab_contents_sub_conts1 tr').length+1)+"\" class=\"all\" style=\"width:100px\">  "							
			  +	"</select>  "	
			  +	"<input type=\"text\" name=\"arr_expl\" id=\"expl\" value=\"<spring:message code='clm.page.msg.manage.relContExp' />  >>>>>>> <<<<<<<<  \" style=\"width:600px\" class=\"text_search\"/>  "	
			  +	"<a href=\"javascript:minusRelCntrt();\"><img src=\"<%=IMAGE%>/icon/ico_minus.gif\" /></a>  "	
			  +	"</td>  "	
			  +	"</tr>  "	;
		  //trRelationContract
		  $("#trRelationContract").after(html);	
		  
		  getCodeSelectByUpCd("frm", "rel_type"+($('#tab_contents_sub_conts1 tr').length-1), '/common/clmsCom.do?combo_sys_cd=LAS&method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=A&combo_grp_cd=C032&combo_type=S&combo_del_yn=N&combo_selected=');
		  
	  }	
	  /**
	  * 연관계약 타입 선택
	  */
	  function reltypeChange(langCd){
		  var type = $("#rel_type").val();  
		  if("C03201" == type){
				$("#rel_defn").html("<option value=NDA>NDA</option><option value=MOU/LOI>MOU/LOI</option>");
			}else if("C03202" == type){
				$("#rel_defn").html("<option value=Master>Master</option><option value=Sub>Sub</option>");
			}else if("C03203" == type){
				$("#rel_defn").html("<option value=Before Revision>Before Revision</option><option value=Before Termination>Before Termination</option>");	
			}else{
					$("#rel_defn").html("<option value=>Nonapplicable</option>");
			}
	  }
	  
	  /**
	  * 연관계약 정보 actionRelContract
	  */
	  function actionRelationContract(arg,id){
		  
		  var frm = document.frm;
		  frm.submit_status.value=arg;
		  
		  if("delete" == arg){
			  frm.parent_cntrt_id.value = id;
		  }else{
			  if(!$("#parent_cntrt_id").val()){
				  alert("<spring:message code='clm.page.msg.manage.announce118' />");

				  $("#rel_type").focus();
				  return;
			  }else if(!$("#rel_type").val()){
				  alert("<spring:message code="clm.page.msg.manage.announce063" />");
				  $("#rel_type").focus();
				  return;
			  }
		  }
		  
		  var options = {
			url: "<c:url value='/clm/manage/consideration.do?method=actionRelationContract' />",
			type: "post",
			dataType: "json",			
			success: function(responseText, statusText) {
				
				if(responseText.returnVal == 1){
					alert("<spring:message code="clm.page.msg.manage.announce156" />");
					
					//입력폼 초기화 
					$("#rel_type").val("");
					$("#parent_cntrt_id").val("");
					$("#parent_cntrt_name").val("");
					$("#rel_defn").html("<option>--<spring:message code='clm.page.msg.common.select' />--</option>");
					$("#expl").val("");
					listRelationContract();
				}else if(responseText.returnVal == 2){
					alert("<spring:message code="clm.page.msg.manage.announce162" />");
				}else{
					alert("<spring:message code="clm.page.msg.manage.announce157" />");
				}
			}
		};
		$("#frm").ajaxSubmit(options);
	  }	 
	  /**
	  * 연관계약 정보 reload;
	  */
	  function listRelationContract(){
		  var frm = document.frm;
		  frm.submit_status.value="edit";
		  var options = {
  				url: "<c:url value='/clm/manage/consideration.do?method=listRelationContract' />",
  				type: "post",
  				async: false,
  				dataType: "html",
  				success: function (data) { 
  					if(data.indexOf('<html>') == -1){  
	  					$("#trRelationContract").nextAll().remove();
	  					$("#trRelationContract").after(data);
  					}
  				}
  			};
	  		$("#frm").ajaxSubmit(options);
	  }
	  
	  /**
	  *계약 갯수 
	  */
	  function getTabCnt(){ 	  
		  return document.frm.tab_cnt.value;
	  }
	  /**
	  *계약갯수 추가
	  */
	  function addTabCnt(){
		  var frm = document.frm;
		  if(!getTabCnt()){
			  frm.tab_cnt.value=1;
		  }else{
			  frm.tab_cnt.value++;
		  }	  
	  }
	  
	  /**
	  *계약 탭 추가 및 계약 컨테츠 생성 데이타 저장 
	  */
	  function contractAction(arg){
		  var frm = document.frm;
		   
		  //앞전 계약의 계약 상대방 정보 가져오기
		  $("#tmp_cntrt_oppnt_cd").val($("#cntrt_oppnt_cd").val());		  
		  $("#tmp_region_gbn").val($("#region_gbn").val());  //master tb
		  $("#tmp_cntrt_oppnt_nm").val($("#cntrt_oppnt_nm").val());
		  $("#tmp_cntrt_oppnt_rprsntman").val($("#cntrt_oppnt_rprsntman").val());
		  $("#tmp_cntrt_oppnt_respman").val($("#cntrt_oppnt_respman").val());
		  $("#tmp_cntrt_oppnt_telno").val($("#cntrt_oppnt_telno").val());
		  $("#tmp_cntrt_oppnt_email").val($("#cntrt_oppnt_email").val());		  
			  
			  
		  //arg ADD DEL
		  if("ADD" == arg){	
			  
			  frm.mod_cntrt_id.value = ""; //계약추가 시 초기화
			  frm.submit_status.value="tab_save";
			  
			  if(validateClmForm(frm)){
				//의뢰일과 회신요청일 
	  			var str_req_dt = frm.req_dt.value.replace('-','').replace('-','');
	  			var str_re_demndday = frm.re_demndday.value.replace('-','').replace('-','');
	  			
	  			if(eval(str_req_dt) > eval(str_re_demndday)){
	  				alert("<spring:message code="clm.page.msg.manage.announce211" />");
	  				return;
	  			}	  			
	  			//계약기간 Chk
	  			var str_from = frm.cntrtperiod_startday.value.replace('-','').replace('-','');
	  			var str_to = frm.cntrtperiod_endday.value.replace('-','').replace('-','');
	  			
	  			if(eval(str_from) > eval(str_to)){
	  				alert("<spring:message code="clm.page.msg.manage.announce039" />");
	  				return;
	  			}				  
			  	insertContractMaster();
			  }
			  frm.cntrtOppntChk.value="";
		  }else if("deleteContract" == arg){
			  if(frm.tab_cnt.value == 1){
			  	alert("<spring:message code="clm.page.msg.manage.announce029" />");
			  	return;
			  }			  
			  msg="<spring:message code="clm.page.msg.manage.announce199" />";
			  if(confirm(msg) && "deleteContract" == arg){
				  deleteContract();
			  }
		  }
	  }  
	  
	/**
	* 데이타 삭제 계약 건 삭제 -계약삭제 
	*/
	function deleteContract(arg){
		var frm = document.frm;		
		frm.submit_status.value="deleteContract";
		viewHiddenProgress(true);
		var options = {
			url: "<c:url value='/clm/manage/consideration.do?method=deleteContract' />",
			type: "post",
			dataType: "json",			
			success: function(responseText, statusText) {
				viewHiddenProgress(false);
				if(responseText.returnCd == "Y"){
					forwardModifyConsideration();
					alert("<spring:message code="clm.page.msg.manage.announce158" />");					
				}else{
					alert("<spring:message code="clm.page.msg.manage.announce157" />");
				}
			}
		};
		$("#frm").ajaxSubmit(options);
	}
	  
	/**
  	* Reload
  	*/
  	/**
	* 관리페이지 이동
	*/
	function forwardModifyConsideration(){
		var frm = document.frm;
		frm.target = "_self";
		frm.action = "/clm/manage/consideration.do";
		frm.status.value = "forwardModify";
		frm.method.value = "forwardModifyConsideration";		
		frm.submit();
	}
	  /**
	  *계약탭 클릭
	  */
	  
	  function titleTabClick(tab,cntrt_id){		  
		
		  var frm = document.frm;
		  frm.submit_status.value = "tab_save";//임시저장
		  
		  if(validateClmForm(frm)){ 
			  
			  var str_from = frm.cntrtperiod_startday.value.replace('-','').replace('-','');
	  		  var str_to = frm.cntrtperiod_endday.value.replace('-','').replace('-','');
	  			
	  		  if(eval(str_from) > eval(str_to)){
	  			alert("<spring:message code="clm.page.msg.manage.announce039" />");
	  			return;
	  		  }
		  
			  //alert("저장하고자 하는 마스터 아이디 "+frm.cntrt_id.value);
			  var options = {				  
					url: "<c:url value='/clm/manage/consideration.do?method=insertContractMaster' />",
					type: "post",
					dataType: "json",			
					success: function(responseText, statusText) {
					
						if(responseText.returnCd == undefined){
							alert("<spring:message code="clm.page.msg.manage.announce157" />");
						}else{
							if(responseText.exeStatus =="insert"){
								var html = "<input type=\"hidden\" value=\""+responseText.returnCntrtId+"\" name=\"arr_cntrt_id\" id=\"arr_cntrt_id\" />";
								$("#cnsdreq_id").before(html);
							} 
							//cnsdreqId 새로운 포맷에 의뢰 아이디 설정  11.01 chaahyunjin
							if(!$('#cnsdreq_id').val()){
								$('#cnsdreq_id').val(responseText.returnCnsdReqId);
							}
						 titleTabClickView(tab,cntrt_id);
						}
						
					}
				};
			//계약관련#1
				 fileList1.UploadFile(function(uploadCount){
					if(uploadCount == -1){
						initPage();
						alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
						return; 					
					}
					
					//계약서 첨부/별첨
	 				fileListEtc.UploadFile(function(uploadCount){
	 	  				if(uploadCount == -1){
	 	  					initPage();	 	  					
	 	  					alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
	 	  					return; 					
	 	  				}
	 	  				
						 //계약관련#2
			  			 fileList2.UploadFile(function(uploadCount){
			  				if(uploadCount == -1){
			  					initPage();
			  					alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
			  					return; 					
			  				}
			  				//계약관련#3
			  			 	fileList3.UploadFile(function(uploadCount){
			  					if(uploadCount == -1){
			  						alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
			  						return; 					
			  					}		  					
			  					//계약관련#4
			 	    			 fileList4.UploadFile(function(uploadCount){
			 	    				if(uploadCount == -1){
			 	    					alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
			 	    					return; 					
			 	    				}
			 	    				viewHiddenProgress(true);
			 	    				$("#frm").ajaxSubmit(options);
			 	    						  			 
			  			 		});//end fileList4
			  			 	});//end fileList3					
					 	});//end fileList2
	 				});//end fileListEtc 		
	 			 });//end fileList1
		  }			  
	  }	  
	  
	  function titleTabClickView(tab,cntrt_id){
		var frm = document.frm;		
		var tab_cnt = getTabCnt();
		
		if(tab_cnt > 1){
			
			<%if("forwardInsert".equals(lomRq.get("status"))){%>				
				if(tab_cnt > 1){					
					frm.mod_cntrt_id.value = frm.arr_cntrt_id[tab-1].value;					
				}else{
					frm.mod_cntrt_id.value = frm.arr_cntrt_id.value;	
				}
			<%}else if("forwardModify".equals(lomRq.get("status"))){%>
				if(tab_cnt > 1){					
					frm.mod_cntrt_id.value = frm.arr_cntrt_id[tab-1].value;					
				}else{
					frm.mod_cntrt_id.value = frm.arr_cntrt_id.value;	
				}
		
			<%}else{%>
				frm.mod_cntrt_id.value = cntrt_id;
			<%}%>	
			
			for(var i=1; i<=tab_cnt; i++){			
				if(tab==i){
					document.getElementById("tab_li"+i+"").className = "on";
			    }else{
				    document.getElementById("tab_li"+i+"").className = "";
			    }		  
			}
			forwardInsertContractMaster();
			tit_Toggle(this, 'tr_down02');
			
			}
	  }
	  
	  /**
	  * 계약검토의뢰등록 폼  inner View 
	  */
	  	function forwardInsertContractMaster(){
	  	    var options = {
	  				url: "<c:url value='/clm/manage/consideration.do?method=forwardInsertContractMaster' />",
	  				type: "post",
	  				async: false,
	  				dataType: "html",
	  				success: function (data) {
	  					$("#tab_contents").html("");
	  					$("#tab_contents").html(data);

	  					// 2012.03.07 최초의뢰도 검토이력 출력 modified by hanjihoon
	  					contractHisList();
	  				}
	  			};
	  		$("#frm").ajaxSubmit(options);
	  		viewHiddenProgress(false);
	  		
	  	}
	  
		/**
		*계약상세 탭 클릭 
		*/
		function subTitleTabMove(num){		 	
		 if(num==1){						    
		     document.getElementById("tab_contents_sub_conts1").style.display = "block"; 
		     document.getElementById("tab_contents_sub_conts2").style.display = "none";
		     
		     document.getElementById("tab_contents_sub_css1").className = "on";
		     document.getElementById("tab_contents_sub_css2").className = "";		      
		     document.getElementById("table_mt_rc").style.display = "none";
		     document.getElementById("li_mt_rc").className = "";
		    }else if(num==2){	
		  	  document.getElementById("tab_contents_sub_conts1").style.display = "none";
		     document.getElementById("tab_contents_sub_conts2").style.display = "block";
		     document.getElementById("tab_contents_sub_css1").className = "";
		     document.getElementById("tab_contents_sub_css2").className = "on";
		     document.getElementById("table_mt_rc").style.display = "none";
		     document.getElementById("li_mt_rc").className = "";
		    }else if(num==4){
		    	document.getElementById("tab_contents_sub_conts1").style.display = "none";
			     document.getElementById("tab_contents_sub_conts2").style.display = "none";
			     document.getElementById("tab_contents_sub_css1").className = "";
			     document.getElementById("tab_contents_sub_css2").className = "";
			     	    	
		    	$("#table_mt_rc").show();
		    	$("#li_mt_rc").addClass("on");			    
		    }else{
		  	  document.getElementById("tab_contents_sub_conts1").style.display = "none";
		     document.getElementById("tab_contents_sub_conts2").style.display = "none";
		     document.getElementById("tab_contents_sub_css1").className = "";
		     document.getElementById("tab_contents_sub_css2").className = "";
		     document.getElementById("table_mt_rc").style.display = "none";
		     document.getElementById("li_mt_rc").className = "";
		    }	
		}
		
		/**
		* 연관계약 계약 목록 팝업
		*/
		function popupListContract(arg){
			var frm = document.frm;
			arg=$("#rel_type").val();
			var parent_cntrt_id = "";
			var parent_cntrt_nm = "";
			var rel_type1 = frm.rel_type.value;
			
			//C03204
			var result = new retRC(parent_cntrt_id ,parent_cntrt_nm ,rel_type1);
			
			if(frm.cntrt_nm.value != ""){
			 
			var rel_type1 = 'C03204';
			var v_except_cntrt_id = frm.cntrt_id.value;
			PopUpWindowOpen2("/clm/manage/myContractPop.do?method=listMyContract&status_mode=rel&except_cntrt_id="+v_except_cntrt_id , 1050, 550, false, "PopUpRelInfo");  // 계약별 - 계약
			} else {
			 alert("<spring:message code='clm.page.msg.manage.announce119' />");
			}
		}
		
		/**
		* 연관계약 팝업 리턴 처리
		*/	  
		function setContract(id,name,biz,sArg, reason){
			var fm = document.frm;			
			
			//리턴값 변수에 저장
			var cntrt_id = id;
			var cntrt_name = name;
			var cntrt_biz_clsfcn = biz;
			var srch_arg = sArg;
			
			$("#parent_cntrt_id").val(cntrt_id);
			$("#rel_type").val(srch_arg);
			$("#parent_cntrt_name").val(cntrt_name);
			$("#rel_yn").val("Y");
			
			$("#expl").val(reason);
			
			if("" != reason){
				$("#expl").val(reason);
				$("#parent_cntrt_id").val("20141010414540080");
				
				//$("#rel_type").html("<option value='C03202' >Master-Sub Relations</option>");
				$("#rel_type").val("C03202");
				$("#rel_defn").html("<option value='Master' >Master</option>");
			}
			/*
		    if("T0101" == biz || "T0102" == biz){
		    	changetItem(biz);
		    } else {
		    	$("#rel_defn").html("");
		    }
			*/
			
		}
		
	  /**
	  * Obj init
	  */
	  function retRC(parent_cntrt_id ,parent_cntrt_nm ,rel_type1){
		  this.parent_cntrt_id = parent_cntrt_id;
		  this.parent_cntrt_nm = parent_cntrt_nm;
		  this.rel_type1 = rel_type1;
	  }
	  
	  /**
	  *계약유형선택에서 사용할 오브젝트 초기화
	  */
	  function retObj(biz_clsfcn, biz_clsfcn_nm,  depth_clsfcn, depth_clsfcn_nm, cnclsnpurps_bigclsfcn, cnclsnpurps_bigclsfcn_nm,cnclsnpurps_midclsfcn,cnclsnpurps_midclsfcn_nm){ 
	     
	     this.biz_clsfcn_nm     = biz_clsfcn_nm;
	     this.depth_clsfcn_nm   = depth_clsfcn_nm;
	     this.cnclsnpurps_bigclsfcn_nm 	= cnclsnpurps_bigclsfcn_nm;
	     this.cnclsnpurps_midclsfcn_nm 	= cnclsnpurps_midclsfcn_nm;
	     
	      this.biz_clsfcn  		 = $("#biz_clsfcn").val(); //"";	      
	      this.depth_clsfcn       = $("#depth_clsfcn").val(); // "";	 	   	
	 	
	      this.cnclsnpurps_bigclsfcn     	= $("#cnclsnpurps_bigclsfcn").val(); // "";	 	  
	 	  this.cnclsnpurps_midclsfcn 		= $("#cnclsnpurps_midclsfcn").val(); // "";
	 	  
	 	  this.prgrs_status = $("#tmp_prgrs_status").val() ;
	      this.cnsd_level = $("#tmp_cnsd_level").val() ;
	 	  
		
	  }		  
	  /**
	  *계약유형선택팝업 오픈
	  */
	  
	  var v_name_cnt = 0;
	  
	  function openChooseContractType() {	  
		  
		  v_name_cnt ++;
		  if(v_name_cnt>=2) return;
		  
		  var frm = document.frm;   
	   
	 	  var biz_clsfcn  		 = $("#biz_clsfcn").val(); //"";
	      var biz_clsfcn_nm      = "";
	      var en_biz_clsfcn_nm      = "";
	 	  var depth_clsfcn       = $("#depth_clsfcn").val(); // "";
	 	  var depth_clsfcn_nm    = ""; 	
	 	  var en_depth_clsfcn_nm    = "";
	 	
	 	  var cnclsnpurps_bigclsfcn     	= $("#cnclsnpurps_bigclsfcn").val(); // "";
	 	  var cnclsnpurps_bigclsfcn_nm  	= "";
	 	  var en_cnclsnpurps_bigclsfcn_nm  	= "";
	 	  var cnclsnpurps_midclsfcn 		= $("#cnclsnpurps_midclsfcn").val(); // "";
	 	  var cnclsnpurps_midclsfcn_nm 		= "";
	 	  var en_cnclsnpurps_midclsfcn_nm 		= "";
	
	 	  var result = new retObj(biz_clsfcn, biz_clsfcn_nm, en_biz_clsfcn_nm,  depth_clsfcn, depth_clsfcn_nm, en_depth_clsfcn_nm, cnclsnpurps_bigclsfcn, cnclsnpurps_bigclsfcn_nm, en_cnclsnpurps_bigclsfcn_nm, cnclsnpurps_midclsfcn, cnclsnpurps_midclsfcn_nm, en_cnclsnpurps_midclsfcn_nm);
	 	  
	      PopUpWindowOpen("/clm/manage/chooseContractType.do?method=forwardChooseContractTypePopup", 1000, 630, false);
	
	      
	      if(result.biz_clsfcn != "" && result.depth_clsfcn != "" &&  result.cnclsnpurps_bigclsfcn != "" &&result.cnclsnpurps_midclsfcn != "") { 
		      frm.biz_clsfcn.value 				= result.biz_clsfcn;                    // 비즈니스 분류
		      frm.biz_clsfcn_nm.value 			= result.biz_clsfcn_nm;                    // 비즈니스 분류     
		      frm.depth_clsfcn.value 			= result.depth_clsfcn;                      //	단계분류
		      frm.depth_clsfcn_nm.value 		= result.depth_clsfcn_nm;                      //	단계분류     
		      frm.cnclsnpurps_bigclsfcn.value 	= result.cnclsnpurps_bigclsfcn;    //	체결목적대분류
		      frm.cnclsnpurps_bigclsfcn_nm.value= result.cnclsnpurps_bigclsfcn_nm;    //	체결목적대분류     
		      frm.cnclsnpurps_midclsfcn.value 	= result.cnclsnpurps_midclsfcn;    //	체결목적중분류 */      
		      frm.cnclsnpurps_midclsfcn_nm.value= result.cnclsnpurps_midclsfcn_nm;    //	체결목적중분류 */
		      
		      frm.en_biz_clsfcn_nm.value 			= result.en_biz_clsfcn_nm;                    // 비즈니스 분류
		      frm.en_depth_clsfcn_nm.value 		    = result.en_depth_clsfcn_nm;                      //	단계분류
		      frm.en_cnclsnpurps_bigclsfcn_nm.value = result.en_cnclsnpurps_bigclsfcn_nm;    //	체결목적대분류  
		      frm.en_cnclsnpurps_midclsfcn_nm.value = result.en_cnclsnpurps_midclsfcn_nm;    //	체결목적중분류 */
		     
		     
		      changeSubCd("cntrt_trgt", "CONTRACTTYPE", result.cnclsnpurps_midclsfcn);	      

		      if("T0103" == result.biz_clsfcn){//본계약인 경우만 특화속성 생성
				listSpecialAttr(result.cnclsnpurps_bigclsfcn,result.cnclsnpurps_midclsfcn);
		      }
		      //계약명 return
			  returnCntrtNm();
		      
	      }
	   }
	  
	  // 최초 로딩 이후 버튼 클릭 후 팝업 띄우기
	  // 최초 로딩의 경우 나모가 2개인 관계로 인하여 한번만 뜨게 처리가 되어 있어 부득이하게 스크립트를 제 정의 함.
	  function re_openChooseContractType() {
		  
		  var frm = document.frm;   
	   
	 	  var biz_clsfcn  		 = $("#biz_clsfcn").val(); //"";
	      var biz_clsfcn_nm      = "";
	      var en_biz_clsfcn_nm      = "";
	 	  var depth_clsfcn       = $("#depth_clsfcn").val(); // "";
	 	  var depth_clsfcn_nm    = ""; 	
	 	  var en_depth_clsfcn_nm    = "";
	 	
	 	  var cnclsnpurps_bigclsfcn     	= $("#cnclsnpurps_bigclsfcn").val(); // "";
	 	  var cnclsnpurps_bigclsfcn_nm  	= "";
	 	  var en_cnclsnpurps_bigclsfcn_nm  	= "";
	 	  var cnclsnpurps_midclsfcn 		= $("#cnclsnpurps_midclsfcn").val(); // "";
	 	  var cnclsnpurps_midclsfcn_nm 		= "";
	 	  var en_cnclsnpurps_midclsfcn_nm 		= "";
	
	 	  var result = new retObj(biz_clsfcn, biz_clsfcn_nm, en_biz_clsfcn_nm,  depth_clsfcn, depth_clsfcn_nm, en_depth_clsfcn_nm, cnclsnpurps_bigclsfcn, cnclsnpurps_bigclsfcn_nm, en_cnclsnpurps_bigclsfcn_nm, cnclsnpurps_midclsfcn, cnclsnpurps_midclsfcn_nm, en_cnclsnpurps_midclsfcn_nm);
	 	  
	      PopUpWindowOpen("/clm/manage/chooseContractType.do?method=forwardChooseContractTypePopup", 1000, 630, false, result);
	      
	      if(result.biz_clsfcn != "" && result.depth_clsfcn != "" &&  result.cnclsnpurps_bigclsfcn != "" &&result.cnclsnpurps_midclsfcn != "") { 
		      frm.biz_clsfcn.value 				 = result.biz_clsfcn;                    // 비즈니스 분류
		      frm.biz_clsfcn_nm.value 			 = result.biz_clsfcn_nm;                    // 비즈니스 분류     
		      frm.depth_clsfcn.value 			 = result.depth_clsfcn;                      //	단계분류
		      frm.depth_clsfcn_nm.value 		 = result.depth_clsfcn_nm;                      //	단계분류     
		      frm.cnclsnpurps_bigclsfcn.value 	 = result.cnclsnpurps_bigclsfcn;    //	체결목적대분류
		      frm.cnclsnpurps_bigclsfcn_nm.value = result.cnclsnpurps_bigclsfcn_nm;    //	체결목적대분류     
		      frm.cnclsnpurps_midclsfcn.value 	 = result.cnclsnpurps_midclsfcn;    //	체결목적중분류 */      
		      frm.cnclsnpurps_midclsfcn_nm.value = result.cnclsnpurps_midclsfcn_nm;    //	체결목적중분류 */
		      
		      frm.en_biz_clsfcn_nm.value 			= result.en_biz_clsfcn_nm;                    // 비즈니스 분류
		      frm.en_depth_clsfcn_nm.value 		    = result.en_depth_clsfcn_nm;                      //	단계분류
		      frm.en_cnclsnpurps_bigclsfcn_nm.value = result.en_cnclsnpurps_bigclsfcn_nm;    //	체결목적대분류  
		      frm.en_cnclsnpurps_midclsfcn_nm.value = result.en_cnclsnpurps_midclsfcn_nm;    //	체결목적중분류 */
		     
		     
		      changeSubCd("cntrt_trgt", "CONTRACTTYPE", result.cnclsnpurps_midclsfcn);	      

		      if("T0103" == result.biz_clsfcn){//본계약인 경우만 특화속성 생성
				listSpecialAttr(result.cnclsnpurps_bigclsfcn,result.cnclsnpurps_midclsfcn);
		      }
		      //계약명 return
			  returnCntrtNm();
		      
	      }
	      
	   }
	  
	  /*
	  * 특화 속성 정보 가져오기 
	  */
	  function listSpecialAttr(bigClsFcn,midClsFcn){
		  var options = {
  				url: "<c:url value='/clm/manage/consideration.do?method=listSpecialAttr&bigclsfcn="+bigClsFcn+"&midclsfcn="+midClsFcn+"' />",
  				type: "post",
  				async: false,
  				dataType: "html",
  				success: function (data) {
  					$("#trSpecialAttr").next().remove();
  					$("#trSpecialAttr").after(data);  					
  				}
  			};
  		  $("#frm").ajaxSubmit(options);
	  }
	 	 
	 /*
	 *계약대상
	 */
	 function changeSubCd(selObjId, gbn, upCd) {
		 if(upCd == "") {
			 upCd = "XXX";
		 } 
		 getCodeSelectByUpCd("frm", selObjId, '/common/clmsCom.do?method=getComboHTML&combo_gbn=' + gbn + '&combo_up_cd=' + upCd + '&combo_abbr=F&combo_type=T&combo_del_yn=N');
	 }
		 
	 /**
	 * 사전품의발의자  사전품의 승인자 정보	 /clm/manage/chooseClient.do   forwardChooseClientPopup
	 */
	 function searchEmployee(srcFrm, gb){
		 var frm = document.frm;
		 var srchValue = comTrim(srcFrm.value); //입력받은 값 
		 
    	 //테스트를 위해서 잠시 막아 둡니다.김재원.!@#.20130506
		 if (srchValue == "" || srchValue.length < 4) { //최소 2자리 이상 입력받게 한다.
	     	 alert('<spring:message code='secfw.msg.error.nameMinByte' />');
	     	 srcFrm.focus();
	         return;
	     }
		 
		 //사전품의발의자인지 사전품의승인자정보에서 호출했는지 구분해주기 위해.
	     frm.searchEmployeeGb.value = gb;
		 
		 PopUpWindowOpen('', 800, 450, true);
		 
		 frm.target = "PopUpWindow";
		 frm.srch_user_cntnt_type.value = "userName"; //이름으로 검색  - 보통 이름으로 검색함
		 frm.srch_user_cntnt.value = srchValue;
         frm.action = "/secfw/esbOrg.do";
         frm.method.value = "listEsbEmployee";
         frm.submit();
         
	 }
	 	 
	 /**
	 * 임직원 조회정보 결과 setting
	 */
	 function setUserInfos(obj) {
	     var userInfo = obj; 
	     var frm = document.frm;

	     //사전품의발의자
	     if(frm.searchEmployeeGb.value == "MTN"){
	    	 
		     frm.bfhdcstn_mtnman_id.value 			= userInfo.epid;		//사전품의발의자 id
		     frm.bfhdcstn_mtnman_nm.value 			= userInfo.cn; 		//발의자 이름
		     frm.bfhdcstn_mtnman_jikgup_nm.value 	= userInfo.title;  		//직급
		     frm.bfhdcstn_mtn_dept_nm.value			= userInfo.department;	//부서
		     $('#bfhdcstn_mtnman_nm_span').html(userInfo.cn);
		     $('#bfhdcstn_mtnman_jikgup_nm_span').html(userInfo.title=="" ? "" : " / " + userInfo.title);
		     $('#bfhdcstn_mtn_dept_nm_span').html(userInfo.department=="" ? "" : " / " + userInfo.department);
		     
	     } else if(frm.searchEmployeeGb.value == "APPR") {		//검토의뢰시 결재자 ID
	    	//eptitlenumber 직급코드 
			 
	     } else{//사전품의승인자정	    	 
	    	 frm.bfhdcstn_apbtman_id.value          = userInfo.epid;		//사전품의승인자정id
	    	 frm.bfhdcstn_apbtman_nm.value          = userInfo.cn; 		//발의자 이름
	    	 frm.bfhdcstn_apbtman_jikgup_nm.value   = userInfo.title;  		//직급
	    	 frm.bfhdcstn_apbt_dept_nm.value        = userInfo.department;	//부서
	    	 
	    	 $('#bfhdcstn_bfhdcstn_apbtman_nm_span').html(userInfo.cn);
	    	 $('#bfhdcstn_apbtman_jikgup_nm_span').html(userInfo.title=="" ? "" : " / " + userInfo.title);
		     $('#bfhdcstn_apbt_dept_nm_span').html(userInfo.department=="" ? "" : " / " + userInfo.department);
	     }

   	 }
	 
	 /**
	 * 계약단가 활성화시 
	 */
	 function clickCntrtUntprc(){
		 var frm = document.frm;
		 if($("[name=cntrt_untprc]").is(":checked")){
			 $("#trCntrtUntprc").show();
		 }else{
			 frm.cntrt_untprc_expl.value = "";
			 $("#trCntrtUntprc").hide();
		 }		 
	 }
	 
	
	 /************************************************************************
	 * 의뢰인관리 팝업
	 */
	 function openChooseClient(){
        var frm = document.frm;
        
        var items_str1 = $("input[name=arr_demnd_seqno]").map(function(){return this.value;}).get();
        var items_str2 = $("input[name=arr_trgtman_id]").map(function(){return this.value;}).get();
        var items_str3 = $("input[name=arr_trgtman_nm]").map(function(){return this.value;}).get();
        var items_str4 = $("input[name=arr_trgtman_jikgup_nm]").map(function(){return this.value;}).get();
        var items_str5 = $("input[name=arr_trgtman_dept_nm]").map(function(){return this.value;}).get();
        
        var items = items_str1+"!@#$"+ items_str2  +"!@#$"+  items_str3  +"!@#$"+  items_str4 +"!@#$"+items_str5;
        frm.chose_client.value = items;
                
        PopUpWindowOpen('', "530", "480", false);
        frm.action = "<c:url value='/clm/manage/chooseClient.do' />";
        frm.method.value="forwardChooseClientPopup";
        frm.target = "PopUpWindow";
        frm.submit();
     }
	 /**
	 *
	 */
	 function PopUpWindowOpen(surl, popupwidth, popupheight, bScroll) {
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
	     
	     
	     //팝업창  주소표시줄 검색 제공 자 없애기 위해서 수정 
	     var Feture = "toolbar=0, location=0, status=1, menubar=0, scrollbars="+(bScroll ? "yes" : "no")+", resizable=yes, top="+Top+", left="+Left+", width="+popupwidth+", height="+popupheight;
		 PopUpWindow = window.open(surl, "PopUpWindow" , Feture);
		 PopUpWindow.focus();
	 }
	 
	//각 팝업 오픈시 target을 달리 주고 싶을 때
	 function PopUpWindowOpen2(surl, popupwidth, popupheight, bScroll, popName) {
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
	 	
	 	
	 	var Feture = "toolbar=0, location=0, status=1, menubar=0, scrollbars=" + (bScroll ? "yes" : "no") + ", resizable=no, top="+Top+", left="+Left+", width="+popupwidth+", height="+popupheight;
	 	PopUpWindow = window.open(surl, popName , Feture);

	 	PopUpWindow.focus();
	 	
	 } 
	
	 /************************************************************************
	 * 거래상대방 팝업 띄우기
	 */ 
	 function openNegoCustomer(){
		
		 var frm = document.frm;
		 
		 PopUpWindowOpen('', 910, 640, false);
 		 frm.target = "PopUpWindow";
 		 frm.action = "/clm/draft/customerTest.do";
 		 frm.method.value = "listCustomerTest"; 		
 		 frm.customer_gb.value   = "O";
 		 frm.submit();
	 }

	 /**
	 *의뢰자 리턴 객체 받기 심주완수정-2011.10.15
	 */
	 function setListClientInfo(returnValue) {
         
        var arrReturn = returnValue.split("!@#$");
        var innerHtml ="";	
        
        $('#id_trgtman_nm').html("");
        
        if(arrReturn[0]=="") {
        	return ;
        }
        
        for(var i=0; i < arrReturn.length;i++) {
        	var arrInfo = arrReturn[i].split("|");
        	if((i != 0 && i != 1) && (i % 2 == 0)){
    			innerHtml += "<br/>";
        	}
        	if(i != 0 && (i % 2 != 0)){
        		innerHtml += ",";
        	}
        		innerHtml += "<input type='hidden' name='arr_demnd_seqno' id='arr_demnd_seqno' value='"+ arrInfo[0] +"' />";
        		innerHtml += "<input type='hidden' name='arr_trgtman_id' id='arr_trgtman_id' value='"+ arrInfo[1] +"' />";
        		innerHtml += "<input type='hidden' name='arr_trgtman_nm' id='arr_trgtman_nm' value='"+ arrInfo[2] +"' />";		        	
        		innerHtml += "<input type='hidden' name='arr_trgtman_jikgup_nm' id='arr_trgtman_jikgup_nm' value='"+ arrInfo[3] +"' />";
        		innerHtml += "<input type='hidden' name='arr_trgtman_dept_nm' id='arr_trgtman_dept_nm' value='"+ arrInfo[4] +"' />";
        		
        		innerHtml += arrInfo[2] +"/"+arrInfo[3] + "/" + arrInfo[4] ;
        		
        	$('#id_trgtman_nm').html(innerHtml);
        	
        }
	 }
	
	/**
	* 계약상대방 객체 리턴
	*/
	function returnCustomer(obj){
		chk++;	
		
		$('#cntrt_oppnt_cd').val(obj.customer_cd);
		$('#cntrt_oppnt_nm').val(obj.customer_nm1);
		$('#cntrt_oppnt_rprsntman').val(obj.tax_no);		
		
		document.getElementById("cntrtOppntChk").value = obj.index;
		
		// 2014-04-10 Kevin added. To put gerp information on the GERP Information section.		
		window.frames["iframeGERP"].fnSetCounterpartyNameNCode(obj.customer_nm1, obj.gerp_cd, obj.contract_required, obj.vendorType);
		
		//계약명 return
		returnCntrtNm();
	}
	
	/************************************************************************
	*  계약명을 만들어 주는 부분임. 법무시스템 Lite 버젼은 회사영문약어+년월일6자리(130415)+_+시퀀스4자리로 구분이 됨.
	*/
	function returnCntrtNm(){
		
		if($('#type_sel21').val() != null){
			$('#type_sel21').hide();
		}
		
		var tmpNmAbbr ="";
		
		if(!$('#orgnz_nm_abbr').val()){			
			tmpNmAbbr = $('#req_dept_nm').val();
		}else{
			tmpNmAbbr = $('#orgnz_nm_abbr').val();
		}
		
		var tmpCntrtOppntNm = chkLength($('#cntrt_oppnt_nm').val(), '16');
				
		//사업부명으로 변경 사업부명이 없으면 부서명 으로 -1129일 수정 - orgnz_nm_abbr
		var cntrtNm = tmpNmAbbr + "_" + tmpCntrtOppntNm + "_" + $('#biz_clsfcn_nm').val() + "_" + $('#depth_clsfcn_nm').val() + "_" + $('#cnclsnpurps_bigclsfcn_nm').val() + "_" + $('#cnclsnpurps_midclsfcn_nm').val();
		var en_cntrtNm = tmpNmAbbr + "_" + tmpCntrtOppntNm + "_" + $('#en_biz_clsfcn_nm').val() + "_" + $('#en_depth_clsfcn_nm').val() + "_" + $('#en_cnclsnpurps_bigclsfcn_nm').val() + "_" + $('#en_cnclsnpurps_midclsfcn_nm').val();
		
		$('#cntrt_nm').val(cntrtNm);
		$('#en_cntrt_nm').val(en_cntrtNm);
		$('#div_cntrt_nm').html("");
		$('#div_cntrt_nm').html(cntrtNm);
		
	}
	
	//결재상신리턴결과
	function setApprovalYN(yn){
		var frm = document.frm;
		if(yn=="Y") {
			frm.action="<c:url value='/clm/manage/consideration.do' />";
	    	frm.target="_self";
	    	frm.method.value="modifyConsiderationStatus";
	    	frm.submit();
		} else {
			frm.action="<c:url value='/clm/manage/consideration.do' />";
	    	frm.target="_self";
	    	frm.method.value="listManageConsideration";
	    	frm.submit();
		}
	}  	
	
	function changetItem(biz){
		$("#rel_type > option[value='C03201']").attr("selected","true");
		if("T0101" == biz){
			$("#rel_defn").html("<option value=NDA selected>NDA</option><option value=MOU/LOI>MOU/LOI</option>"); 
		} else {
			$("#rel_defn").html("<option value=NDA>NDA</option><option value=MOU/LOI selected>MOU/LOI</option>");
		}
	}
		 
    function olnyNum(obj){   	
    	           	
    	var str = obj.value;
        str = new String(str);
        // 2014-02-05 Kevin. 소수점 구분 위해 "." 입력 허용함.
        var Re = /[^0-9.]/g;
        str = str.replace(Re,'');
       
        // 금액 100,000 형태로 변환 추가 (2011/10/15)
        obj.value = Comma(str);
    }
    
    function viewHideChgeCont(val) {
    	if(val=="N") {
    		$("#tr_plndbn_chge_cont").css("display", "none") ;
    	} else {
    		$("#tr_plndbn_chge_cont").css("display", "block") ;
    	}
    }

	function setReqTitleView(val) {
		document.getElementById("reqtitle_view").innerHTML = val.replace("</", "&lt;/").replace('<', '&lt;') ; 
	}
	 
	/*Sample Contract*/
	function openSampleContract(){
		PopUpTargetWindowOpen("/clm/draft/refContract.do?method=listRefContract&menu_id=20130319155933123_0000000378", 1100, 500, false, "PopUpSampleContract");  //
	}
	
	function GoTnc(){
		
		var loc = unescape(document.location.href);
		var lowercase =  loc.toLowerCase(loc);
		var div = "";
		
		if (lowercase.indexOf("http://") == 0) {
			div =  loc.substr(0,loc.lastIndexOf("/") -6 );//BasePath 생성
		} else {
		    var path;
			    path = loc.replace(/.{2,}:\/{2,}/, ""); // file:/// 를 지워버린다.
			    div =  path.substr(0,path.lastIndexOf("/") -6 );//BasePath 생성
		}
		
		//alert("div = " +div);
		
		if("http://selmsplus.sec.samsung.net/clm/" == div ){
			parent.parent.location.href="http://tcms.sec.samsung.net/TNC/system/main.do";
		} else {
			parent.parent.location.href="http://10.40.65.96:6060/TNC/";
		}
		
		
		
		//parent.parent.location.href='http://10.40.65.96:6060/TNC/';
	}
	
	// TOP 30 팝업
	function openTop30Customer(){
		   PopUpWindowOpen2("/clm/draft/customerTest.do?method=listTop30Customer", 500, 540, false, "Top30Customer");
	}
	
</script>
</head>
<body>


<c:if test="${lomRq.status eq 'forwardInsert'}">
  <div class='type_sel2' id="type_sel21" name="type_sel21"></div>
</c:if>

<div id="wrap">
	<!-- container -->
	<div id="container">
	    <div id="content_in">
		<form:form name="frm" id="frm" method="post" autocomplete="off">
		<input type="hidden" name="method" id="method" value="" />
		<input type="hidden" name="menu_id" id="menu_id" value="<c:out value='${command.menu_id}'/>" />
		<input type="hidden" name="curPage" id="curPage" value="<c:out value='${command.curPage}'/>" />
		<input type="hidden" name="status" id="status" value="<c:out value='${lomRq.status}'/>" />
		<input type="hidden" name="submit_status" id="submit_status" />
		<input type="hidden" name="srch_user_cntnt_type" value=""/> 
		<input type="hidden" name="srch_user_cntnt" value=""/>
	    <!-- 첨부파일정보 -->
	    <input type="hidden" name="fileInfos"  id="fileInfos" /> <!-- 저장될 파일 정보 -->
		<input type="hidden" name="fileInfos1"  id="fileInfos1" value="1" /> <!-- 저장될 파일 정보 -->
		<input type="hidden" name="fileInfos2" id="fileInfos2" value="2" /> <!-- 저장될 파일 정보 -->	
		<input type="hidden" name="fileInfosEtc" id="fileInfosEtc"  value="3" /> <!-- 저장될 파일 정보 -->		
		<input type="hidden" name="fileInfos3" id="fileInfos3"  value="4" /> <!-- 저장될 파일 정보 -->	
		<input type="hidden" name="fileInfos4" id="fileInfos4"  value="5" /> <!-- 저장될 파일 정보 -->	
		<input type="hidden" name="fileInfos5" id="fileInfos5"  value="6" /> <!-- 저장될 파일 정보 -->	
		<input type="hidden" name="fileInfoName"    value="" /> <!-- 첨부파일 정보가 리턴될 객체명 -->
	    <input type="hidden" name="fileFrameName"    value="" /> <!-- 첨부파일 화면 iFrame 명 -->
		<input type="hidden" name="file_bigclsfcn"  value="" /> <!-- 대분류 -->	
		<input type="hidden" name="file_midclsfcn"  value="" /> <!-- 중분류 -->	
		<input type="hidden" name="file_smlclsfcn"  value="" /> <!-- 소분류 -->	
		<input type="hidden" name="ref_key"     value="" /> <!-- 키값 -->				
		<input type="hidden" name="view_gbn"    value="upload" /> <!-- 첨부파일 화면 -->
		<input type="hidden" name="multiYn" value="" /><!-- 멀티 -->
		<input type="hidden" name="customer_gb" value="" />
	   <!-- 계약추가 증가데이타 -->	    
	    <input type="hidden" name="tab_cnt" id="tab_cnt" value="<c:out value='${lomRq.total_cnt}'/>" /><!-- Tab 계약 증가 값 -->    
	   <!-- tmp_cntrt_oppnt_cd 계약 상대방  -->
	   <input type="hidden" name="tmp_cntrt_oppnt_cd" id="tmp_cntrt_oppnt_cd"/>
	   <input type="hidden" name="tmp_region_gbn" id="tmp_region_gbn"/>
	   <input type="hidden" name="tmp_cntrt_oppnt_nm" id="tmp_cntrt_oppnt_nm"/>
	   <input type="hidden" name="tmp_cntrt_oppnt_rprsntman" id="tmp_cntrt_oppnt_rprsntman"/>
	   <!-- 2014-04-17 Kevin commented. -->
	   <!-- <input type="hidden" name="tmp_cntrt_oppnt_type" id="tmp_cntrt_oppnt_type"/> -->
	   <input type="hidden" name="tmp_cntrt_oppnt_respman" id="tmp_cntrt_oppnt_respman"/>
	   <input type="hidden" name="tmp_cntrt_oppnt_telno" id="tmp_cntrt_oppnt_telno"/>
	   <input type="hidden" name="tmp_cntrt_oppnt_email" id="tmp_cntrt_oppnt_email"/>
	   <!-- 계약 의뢰 에 필요한 Master 정보-->
	   <input type="hidden" name="req_biz_clsfcn" id="req_biz_clsfcn"/>
	   <input type="hidden" name="req_depth_clsfcn" id="req_depth_clsfcn"/>
	   <input type="hidden" name="req_cnclsnpurps_midclsfcn" id="req_cnclsnpurps_midclsfcn"/>
	   <input type="hidden" name="req_cnclsnpurps_bigclsfcn" id="req_cnclsnpurps_bigclsfcn"/>
	   <input type="hidden" name="C03204_pcid_cp" id="C03204_pcid_cp"/>	   
	   <!-- 관련자 데이타 설정  -->
	   <input type="hidden" name="chose_client" id="chose_client" />
	   <!-- 기존 데이터가 있을 경우에 데이터 삭제해주기 위해서. -->
	   <input type="hidden" name="cntrtOppntChk" id="cntrtOppntChk" value="" />
	   <!-- 임직원조회 팝업 시 리턴값 사전품의발의자인지 사전품의승인자정보 인지 구분하기 위해 -->
	   <input type="hidden" name="searchEmployeeGb" id="searchEmployeeGb" value="" />
	   <!-- 계약단가요약에 데이터가 있는지 체크하기 위해 -->
	   <input type="hidden" name="cntrt_untprc_chk" id="cntrt_untprc_chk" value="<c:out value='${lomRq.cntrt_untprc_expl}'/>" />
	   
	   <!-- TNC value -->
	   <input type="hidden" name="sys_nm" id="sys_nm" value="<c:out value='${lomRq.sys_nm}'/>" />
	   
	   <!-- cntrt_id 배열 표시  -->
	   <%if(listDc != null && !"forwardInsert".equals(lomRq.get("status"))){	
	   		for(int j=0; j<listDc.size(); j++){ 					
	   			Map lom = (Map)listDc.get(j);								%>
	   <input type="hidden" name="arr_cntrt_id" id="arr_cntrt_id" value="<%=(String)lom.get("cntrt_id") %>" />
	   <%	} 														
	   	  }																%>
	   <!-- cntrt_id 배열 표시  -->	
	   <input type="hidden" name="cnsdreq_id" id ="cnsdreq_id" value="<c:out value='${lomRq.cnsdreq_id}'/>" />
	   <input type="hidden" name="approval_key" id="approval_key" value="<c:out value='${lomReq.cnsdreq_id}'/>" />
	   <!-- 재의뢰 OR 최종본 의뢰시 사용  -->
	   <input type="hidden" name="prev_cnsdreq_id" id ="prev_cnsdreq_id" value="<c:out value='${lomRq.prev_cnsdreq_id}'/>" />	   
	   <input type="hidden" name="prev_mn_respman_apnt_yn" id ="prev_mn_respman_apnt_yn" value="<c:out value='${lomRq.mn_respman_apnt_yn}'/>" />
	   <input type="hidden" name="prev_vc_respman_apnt_yn" id ="prev_vc_respman_apnt_yn" value="<c:out value='${lomRq.vc_respman_apnt_yn}'/>" />
	   <input type="hidden" name="mn_cnsd_dept" id ="mn_cnsd_dept" value="<c:out value='${lomRq.mn_cnsd_dept}'/>" />
	   <input type="hidden" name="vc_cnsd_dept" id ="vc_cnsd_dept" value="<c:out value='${lomRq.vc_cnsd_dept}'/>" />
	   	   
	   <input type="hidden" name="plndbn_req_yn" id ="plndbn_req_yn" value="<c:out value='${lomRq.plndbn_req_yn}'/>" />
	   <input type="hidden" name="mod_cntrt_id" id ="mod_cntrt_id" value="<c:out value='${lomRq.cntrt_id}'/>" />
	   <input type="hidden" name="req_reg_id" id ="req_reg_id" value="<c:out value='${lomRq.req_reg_id}'/>" />
	   <input type="hidden" name="fileYn" id="fileYn" value="N" />
	   <input type="hidden" name="orgnz_nm_abbr" id="orgnz_nm_abbr" value="<c:out value='${command.abbr_comp_nm}'/>" />
	   

        <!-- 기존 Consideration_inner.jsp 에 있던 hidden 값 -->
        <input type="hidden" name="cntrt_id" id="cntrt_id" value="<c:out value='${lomRq.cntrt_id}'/>" />
        <input type="hidden" name="file_validate" id="file_validate" value="<c:out value='${command.file_validate}'/>" />
        <input type="hidden" name="rel_up_cntrt_id" id="rel_up_cntrt_id" value="<c:out value='${lomRq.cntrt_id}'/>" />
        <input type="hidden" name="rel_yn" id="rel_yn"/>
        
        <!-- 계약 유형 수정 가능여부를 체크하기 위한 변수 -->
        <input type="hidden" name="tmp_cnsd_level" id="tmp_cnsd_level" value="<c:out value='${lomRq.cnsd_level}'/>" />
        <input type="hidden" name="tmp_prgrs_status" id="tmp_prgrs_status" value="<c:out value='${lomRq.prgrs_status}'/>" />
        
        <!-- 2014-04-08 Kevin added. GERP 관련 페이지 형태 구분.(R/I) -->
        <input type="hidden" name="gerpPageType" id="gerpPageType" />
        <!-- 2014-04-14 Kevin added.GERP 정보 hidden fields. -->
        <input type="hidden" name="gerpCostType" id="gerpCostType" />
        <input type="hidden" id="gerpContractType" name="gerpContractType" />
        <input type="hidden" id="gerpDivCode" name="gerpDivCode" />
        
		<!-- location -->
		<div class="location">
			<img src="<%=IMAGE %>/icon/ico_home.gif" border="0" alt="Home" /> <spring:message code="clm.page.msg.common.contractManagement" /> > <spring:message code="clm.page.button.contract.request" /> > <strong><c:out value='${command.req_status_title}'/><spring:message code="clm.page.msg.manage.reqMake" /></strong>
		</div>
		<!-- //location -->
		<!-- title -->
		<div class="title">
			<h3><c:out value='${command.req_status_title}'/></h3>
		</div>
		<!-- //title-->
		<!-- content -->
		<div id="content">
			<div class="content-step t_titBtn">
				<ol>
					<li class='step_on'>
						<img src="<%=IMAGE %>/common/step01_on.gif"  alt="<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" />" />
						<img src="<%=IMAGE %>/common/step_arr01.gif" alt="Next" title="Next" />
						<div class='step_link'><a href="javascript:open_window('win', '../../step01<%=langCd.equals("ko")?"":"_"+langCd %>.html', 0, 0, 696, 588, 0, 0, 0, 0, 0)" ><img src="<%=IMAGE %>/common/step_q.gif" alt="<spring:message code="clm.page.msg.manage.procStep"/>" title="<spring:message code="clm.page.msg.manage.procStep"/>" /></a></div>
					</li>
					<li><img src="<%=IMAGE %>/common/step02.gif"     alt="<spring:message code="clm.page.msg.manage.step02" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step02" htmlEscape="true" />" /></li>
					<li><img src="<%=IMAGE %>/common/step03.gif"     alt="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" /></li>
					<li><img src="<%=IMAGE %>/common/step04.gif"     alt="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" /></li>
					<li><img src="<%=IMAGE %>/common/step05.gif"     alt="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" /></li>
				</ol>
			</div>
			<div class="t_titBtn">
				<!-- title -->
				<div class="title_basic">
					<h4><c:out value='${command.req_status_title}'/>&nbsp;<spring:message code="clm.jsp.consideration.info" /> </h4>
				</div>
				<!-- //title -->				
				<!-- button -->
				<div class="btnwrap mt_22">
					<!-- 의뢰시  -->
					<!-- 미리보기/임시저장/검토의뢰/목록 -->
				<%	if("forwardInsert".equals(lomRq.get("status"))){%>	
					<span class="btn_all_w"><span class="check2"></span><a href="javascript:forwardInsertConsideration('req');"><spring:message code="clm.page.button.contract.request" /></a></span>					
					<span class="btn_all_w"><span class="tsave"></span><a href="javascript:forwardInsertConsideration('save');"><spring:message code="clm.page.msg.manage.tmpSave" /></a></span>			
				<%	} %>
					<!-- 미리보기/임시저장/검토의뢰/목록 -->
				<%	//이전 의뢰ID가 없을 경우 : 의뢰건
		   			//이전 의뢰ID가 있을 경우 : 재의뢰 OR 최종본의뢰 OR 재의뢰중 임시저장 OR 최종본의뢰중 임시저장
					String sprev_cnsdreq_id = lomRq.get("prev_cnsdreq_id") == null ? "" : ((String)lomRq.get("prev_cnsdreq_id")).trim();
				
		   			if("forwardModify".equals(lomRq.get("status")) || "forwardReq".equals(lomRq.get("status"))){%>				
			   		<%if(sprev_cnsdreq_id.equals("") && "C02601".equals(lomRq.get("depth_status"))){%>			   		
					<span class="btn_all_w"><span class="check2"></span><a href="javascript:forwardInsertConsideration('req');"><spring:message code="clm.page.button.contract.request" /></a></span>
					<span class="btn_all_w"><span class="tsave"></span><a href="javascript:forwardInsertConsideration('save');"><spring:message code="clm.page.msg.manage.tmpSave" /></a></span>
			   		<% }else if(! sprev_cnsdreq_id.equals("") && ("C04201".equals(lomRq.get("prgrs_status")) || "C04305".equals(lomRq.get("cnsd_status")))){ %>
                    <% if(! sprev_cnsdreq_id.equals("") && "N".equals(lomRq.get("plndbn_req_yn"))){%>
                        <span class="btn_all_w"><span class="check2"></span><a href="javascript:forwardInsertConsideration('again');"><spring:message code="clm.page.msg.manage.reply" /></a></span>
                        <% }else if(! sprev_cnsdreq_id.equals("") && "Y".equals(lomRq.get("plndbn_req_yn")) && "".equals(lomRq.get("cnslt_no"))){%>
                        <span class="btn_all_w"><span class="check3"></span><a href="javascript:forwardInsertConsideration('last');"><spring:message code="clm.page.msg.manage.finalVerReq" /></a></span>
                        <% }%>     
                        <span class="btn_all_w"><span class="tsave"></span><a href="javascript:forwardInsertConsideration('save');"><spring:message code="clm.page.msg.manage.tmpSave" /></a></span>   
                    <% }%>				
				<%	} %>					
					<!-- 최종본의뢰시 -->
					<!-- 미리보기/예정보의뢰/목록 -->
				<%	if("forwardLast".equals(lomRq.get("status"))){%>
					<span class="btn_all_w"><span class="check3"></span><a href="javascript:forwardInsertConsideration('last');"><spring:message code="clm.page.msg.manage.finalVerReq" /></a></span>	
					<span class="btn_all_w"><span class="tsave"></span><a href="javascript:forwardInsertConsideration('save');"><spring:message code="clm.page.msg.manage.tmpSave" /></a></span>									
				<%	} %>				
				<span class="btn_all_p"><span class="list"></span><a href="javascript:forwardConsideration('list');"><spring:message code="clm.page.msg.manage.list" /></a></span>
				</div>
				<!-- //button -->
			</div>
	
			<!-- toptable -->
			<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
				<colgroup>				
					<col width="12%" />
					<col width="10%" />
					<col width="20%" />
					<col width="13%" />
					<col width="16%" />
					<col width="13%" />
					<col width="16%" />					 
				</colgroup>
				<tr>
					<th><spring:message code="clm.page.msg.manage.reqName" /></th>
				  <td colspan="6"><input type="text" name="req_title" name="id" alt="<spring:message code="clm.page.msg.manage.reqName" htmlEscape="true" />" required value="<c:out value="${lomRq.req_title}"  escapeXml="false"/>" class="text_full" maxlength="66" onblur="setReqTitleView(this.value)"/></td>
				</tr>
				<tr>
					<th><spring:message code="clm.page.msg.manage.reqPerson" /></th>
					<td colspan="2"><input type="hidden" name="reqman_id" id="reqman_id" alt="<spring:message code="clm.page.msg.manage.reqPerson" htmlEscape="true" />" value="<c:out value="${lomRq.reqman_id}" />" />
						<input type="hidden" name="reqman_nm" id="reqman_nm"  value="<c:out value="${lomRq.reqman_nm}" />" />
						<input type="hidden" name="req_dept_nm" id="req_dept_nm" alt="<spring:message code="clm.page.msg.common.department" htmlEscape="true" />" value="<c:out value="${lomRq.req_dept_nm}" />" />
						<c:out value="${lomRq.reqman_nm}" />/
						<c:out value="${lomRq.reqman_jikgup_nm}" />/
						<c:out value="${lomRq.req_dept_nm}" />
					</td>					
					<th><spring:message code="clm.page.msg.manage.requDate" /></th>
					<td class="tC"><input type="hidden" name="req_dt" alt="<spring:message code="clm.page.msg.manage.requDate" htmlEscape="true" />" value="<c:out value="${lomRq.req_dt}" />" />
						<c:out value="${lomRq.req_dt}" /></td>
					<th><spring:message code="clm.page.msg.manage.replyReqDate" /></th>
					<td><input type="text" name="re_demndday" id="re_demndday" required alt="<spring:message code="clm.page.msg.manage.replyReqDate" htmlEscape="true" />" value="<c:out value="${lomRq.re_demndday}" />" class="text_calendar02"/></td>
				</tr>
				<tr class="lineAdd">
					<th><spring:message code="clm.page.msg.manage.relPerson" /> <img src="<%=IMAGE %>/common/step_q.gif" alt="info" title="<spring:message code="clm.page.msg.manage.announce064" htmlEscape="true" />" /></th>
					<td colspan="6">
					
					<span class="btn_all_b" onclick="javascript:openChooseClient();"><span class="add"></span><a> <spring:message code="clm.page.msg.manage.add" htmlEscape="true" /></a></span><!-- 추가 -->									
                    
					<span id="id_trgtman_nm">
					
					<% for(int j=0;j<listCa.size();j++){%>
					<% ListOrderedMap lom = (ListOrderedMap)listCa.get(j);%>
					<% if((j !=0 && j !=1) && (j % 2 == 0 )){%><br/><%}%>
					<% if(j != 0 && (j % 2 !=0 )){%>,<% }%>	
					<input type="hidden" name="arr_demnd_seqno" id="arr_demnd_seqno" value="<%=lom.get("demnd_seqno") %>" /><input type="hidden" name="arr_trgtman_id" id="arr_trgtman_id" value="<%=lom.get("trgtman_id") %>" /><input type="hidden" name="arr_trgtman_nm" id="arr_trgtman_nm" value="<%=lom.get("trgtman_nm") %>" /><input type="hidden" name="arr_trgtman_jikgup_nm" id="arr_trgtman_jikgup_nm" value="<%=lom.get("trgtman_jikgup_nm") %>" /><input type="hidden" name="arr_trgtman_dept_nm" id="arr_trgtman_dept_nm" value="<%=lom.get("trgtman_dept_nm") %>" />
					<%=lom.get("trgtman_nm") %>/<%=lom.get("trgtman_jikgup_nm") %>/<%=lom.get("trgtman_dept_nm") %>					
					<% }%></span>
					
					</td>
					
				</tr>						
				<tr>
					<!-- 2012.03.19 "최종본 요청내용" => "최종본 검토의뢰내용" 으로 명칭 변경 modified by hanjihoon -->
					<c:choose>
						<c:when test="${lomRq.plndbn_req_yn == 'Y'}">
							<th><spring:message code="clm.page.msg.manage.finalVer" /><br/><spring:message code="clm.page.msg.manage.reviewReqCont" /><span class='astro'>*</span></th>
						</c:when>
						<c:otherwise>
							<th style='border-left:0'><spring:message code="clm.page.msg.manage.purposeDetails" /><span class='astro'>*</span></th>
						</c:otherwise>
					</c:choose>
					<td colspan="6">
					<input type="hidden" name="cnsd_demnd_cont" id="cnsd_demnd_cont" alt="<spring:message code="clm.page.msg.manage.reqContent" htmlEscape="true" />" value="<c:out value="${lomRq.cnsd_demnd_cont}" />" />
					<!--  2014-02-24 Kevin. Purpose and Background의 입력 크기를 2000 -> 4000으로 변경 -->
					<span id="curByteExpl_body_mine">0</span>/<spring:message code="clm.page.msg.manage.stringLen4000" /><br>
                    <textarea name="body_mime" id="body_mime" cols="100%" rows="20" maxLength="4000" onkeyup="frmChkLenLang(this,4000,'curByteExpl_body_mine','<%=langCd%>')"  required class="text_area_full"><c:out value="${lomRq.cnsd_demnd_cont}" escapeXml="" /> </textarea>
					<!-- /div-->
					</td>
				</tr>
				<%	if("Y".equals(lomRq.get("plndbn_req_yn"))) {		//PREV_CNSDREQ_ID  이전검토아이디 %>
				<!-- 최종본의뢰시 표시 -->				
				<tr>
					<th><spring:message code="clm.page.msg.manage.chgRevVerYn" /> </th><!-- lastbn_chge_yn -->
					<td colspan="6">
					    <select name="lastbn_chge_yn" id="lastbn_chge_yn" onchange="viewHideChgeCont(this.value)">
						    <option value="Y" <% if("Y".equals(lomRq.get("lastbn_chge_yn"))){out.println(" selected");}%>>YES</option>
						    <option value="N" <% if("N".equals(lomRq.get("lastbn_chge_yn"))){out.println(" selected");}%>>NO</option>
					     </select>  ※ <spring:message code="clm.page.msg.manage.announce182" />
					</td>
					
				</tr>
				<tr id="tr_plndbn_chge_cont" <%="N".equals(lomRq.get("lastbn_chge_yn")) ? "style='display:none'" : "" %>>
					<th><spring:message code="clm.page.msg.manage.rsnBrkChange" /></th>
					<td colspan="6">
						<span id="curByteExpl2">0</span>/<spring:message code="clm.page.msg.manage.stringLen2000" /><br>
						<textarea name="plndbn_chge_cont" id="plndbn_chge_cont" alt="<spring:message code="clm.page.msg.manage.rsnBrkChange" htmlEscape="true" />" cols="30" rows="4" maxLength="2000" onkeyup="frmChkLenLang(this,2000,'curByteExpl2','<%=langCd%>')" class="text_area_full"><c:out value="${lomRq.plndbn_chge_cont}" escapeXml="false"/></textarea>
					</td>
				</tr>
				<!-- 최종본의뢰시 표시 ** -->
				<%	}%>
				
				<!-- 첨부파일 -->
				<tr class="slide-target02 slide-area">
	                <th rowspan="3"><c:out value='${lomRq.req_status_title}'/><br><spring:message code="clm.page.msg.common.attachment" /> <img src="/images/las/ko/common/step_q.gif" alt="info" title="DOC, DOCX, GUL, PDF, OCX, XLS, XLSX, PPT, PPTX,&#13;JPG, HWP, TIF, RTF, ZIP, PTX, TML, TXT, LSX, EML, BMP, GIF, MSG, ALZ, DOT &#13;&#13;<spring:message code='las.msg.com.fileFilter'/>" /></th>
	                <td class="blueD"><span class="blueD"><c:if test="${lomRq.plndbn_req_yn == 'Y'}"><spring:message code="clm.page.msg.manage.finVr" /><br/></c:if><spring:message code="clm.page.msg.manage.contract" /></span></td>
	                <td  colspan="5">
		                <div style='clear:both'>
			                <c:choose>
			                    <c:when test="${lomRq.plndbn_req_yn == 'Y'}">
			                        <span><spring:message code="clm.page.msg.manage.announce112" /><br/><spring:message code="clm.page.msg.manage.announce168" /></span>
			                    </c:when>
			                    <c:otherwise>
			                        <span><spring:message code="clm.page.msg.manage.announce004" /></span>
			                    </c:otherwise>
			                </c:choose>
			            </div>    
		                <div class='ico_maxsize fR'>Max Size : <span>50MB</span>   </div>
						<iframe src="<c:url value='/clm/blank.do' />" id="fileList1" name="fileList1" frameborder="0" class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>
		            </td>
	            </tr> 
	            <tr class="slide-target02 slide-area">
	                <td class="tal_lineL"><span class="blueD"><c:if test="${lomRq.plndbn_req_yn == 'Y'}"><spring:message code="clm.page.msg.manage.finVr" /><br></c:if><spring:message code="clm.page.msg.manage.attachment_br" /></span></td>
	                <td colspan="5">
		               	<div style='clear:both'>
			                <c:choose>
			                    <c:when test="${lomRq.plndbn_req_yn == 'Y'}">
			                        <span><spring:message code="clm.page.msg.manage.announce050" /><br/><spring:message code="clm.page.msg.manage.announce168" /></span>
			                    </c:when>
			                    <c:otherwise>
			                        <span><spring:message code="clm.page.msg.manage.announce051" /></span>
			                    </c:otherwise>
			                </c:choose>
			            </div>
			            <div class='ico_maxsize fR'>Max Size : <span>50MB</span></div> 
			            <!-- Sungwoo 2014-07-21 scrolling changed -->
		                <iframe src="<c:url value='/clm/blank.do' />" id="fileListEtc" name="fileListEtc" frameborder="0" class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>
		            </td>
	            </tr>
	            <tr class="slide-target02 slide-area">
	                <td class="tal_lineL">
	                    <c:choose>
	                        <c:when test="${lomRq.plndbn_req_yn == 'Y'}">
	                            <span class="blueD tC"><spring:message code="clm.page.msg.manage.chgMrkd" /><br/><spring:message code="clm.page.msg.manage.contract" />,<br/><spring:message code="clm.page.msg.manage.attach" /></span>
	                        </c:when>
	                        <c:otherwise>
	                            <span class="blueD tC"><spring:message code="clm.page.msg.common.etc" /></span>
	                        </c:otherwise>
	                    </c:choose>
	                </td>
	                <td colspan="5">
	                	<div style='clear:both'>
		                <c:choose>
		                    <c:when test="${lomRq.plndbn_req_yn == 'Y'}">
		                        <span><spring:message code="clm.page.msg.manage.announce183" /></span>
		                    </c:when>
		                    <c:otherwise>
		                        <span><spring:message code="clm.page.msg.manage.announce046" /></span>
		                    </c:otherwise>
		                </c:choose>
		                </div>
		                <div class='ico_maxsize fR'>Max Size : <span>50MB</span></div> 
		                <iframe src="<c:url value='/clm/blank.do' />" id="fileList2" name="fileList2" frameborder="0" class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>             
		            </td>
	            </tr>
	                    <input type="hidden" name="std_hstry_no" id="std_hstry_no"  alt="<spring:message code="clm.page.msg.manage.counselNo" htmlEscape="true" />" value="<c:out value="${lomRq.hstry_no}" />" />
						<input type="hidden" name="std_cnslt_no" id="std_cnslt_no" value="<c:out value="${lomRq.cnslt_no}" />" />
	            <tr> <!-- 샘플계약서 부분 -->
					<th style='border-left:0'>
						<spring:message code="clm.page.field.samplecontract.title" />
					</th>	
                    <td class='tC'>
                    	<a href="javascript:openSampleContract();"><img src="<%=IMAGE %>/icon/ico_search_g.gif" class="cp" alt="search" /></a>
					</td>
					<td class='tC' colspan='5'>
					</td>
				</tr>	
						
	            <%if("C02406".equals(lomRq.get("cntrt_status"))){//DROP %>         
                <tr id="tr_dropContractOpnn" class="slide-target02 slide-area">
                    <th><spring:message code="clm.page.msg.manage.rsnForDrop" /></th><!-- out  -->
                    <td id="td_dropContractOpnn" colspan="6"><c:out escapeXml="false" value="${lomRq.cntrt_chge_demnd_cause}" />                                
                    </td>
                </tr>           
                <%  } %>
			</table>
			<div style="display:none;"><iframe src="<c:url value='/clm/blank.do' />" id="fileList" name="fileList" frameborder="0" class="addfile_iframe_d" scrolling="auto" allowTransparency="true"></iframe></div>
		
			<!-- //top table -->
			<!-- table001 -->
			<!-- //able001 -->
			<!-- title -->
            <div class="title_basic">
				<h4><spring:message code="clm.page.msg.manage.contInfo" /></h4>
			</div>
			<!-- //title -->
			<div class='title_basic3'><spring:message code="clm.page.msg.manage.bscInf" /></div>
			<table class="table-style01">
	            <colgroup>
	                <col width="12%" />
	                <col width="22%" />
	                <col width="14%" />
                    <col width="21%" />
                    <col width="12%" />
                    <col width="19%" />
	            </colgroup>
	            <tr>
	                <th><spring:message code="clm.page.msg.manage.reqName" /><span class='astro'>*</span></th>
	                <td colspan="5"><span id="reqtitle_view"><c:out value="${lomRq.req_title}"  escapeXml="false"/></span></td>
	            </tr>
	            <tr>
	                <th><spring:message code="clm.page.msg.manage.contName" /><span class='astro'>*</span></th>
	                <td colspan="3"> 
                        <input type="hidden" name="cntrt_nm" id="cntrt_nm" alt="<spring:message code="clm.page.msg.manage.contName" htmlEscape="true" />"  value="<c:out value='${lomRq.cntrt_nm}'/>" />
                        <input type="hidden" name="en_cntrt_nm" id="en_cntrt_nm" htmlEscape="true" />
                        <span id="div_cntrt_nm">
		                <!-- 2012.02.22 계약을 추가할때 계약명 초기화 modified by hanjihoon -->
		                <% if(lomRq.get("biz_clsfcn") != null){%>
		                    <%=lomRq.get("cntrt_nm") %>
		                <% }else{%>
		                    <spring:message code="clm.page.msg.manage.announce045" />
		                <% }%>               
		                </span>
	                </td>
	                <th><spring:message code="clm.page.msg.manage.contId" /></th>
	                <td></td>
	            </tr>
	            <tr>
                    <th><spring:message code="clm.page.msg.manage.reqPerson" /><span class='astro'>*</span></th>
                    <td>
                        <c:out value="${lomRq.reqman_nm}" />/
                        <c:out value="${lomRq.reqman_jikgup_nm}" />/
                        <c:out value="${lomRq.req_dept_nm}" />
                    </td>
                    <th><spring:message code="clm.page.msg.manage.chrgPerson" /><span class='astro'>*</span></th>
                    <td>
                        <c:out value="${lomRq.reqman_nm}" />/
                        <c:out value="${lomRq.reqman_jikgup_nm}" />/
                        <c:out value="${lomRq.req_dept_nm}" />
                    </td>
                    <th><spring:message code="clm.page.msg.manage.reviewer" /></th>
                    <td></td>
                </tr>
	            <tr>
	                <th><spring:message code="clm.page.msg.common.otherParty" /><span class='astro'>*</span></th>
	                <td>
                        <input type="hidden" name="cntrt_oppnt_cd" id="cntrt_oppnt_cd" required alt="<spring:message code="clm.page.msg.manage.othPartyCode" htmlEscape="true" />" value="<c:out value='${lomRq.cntrt_oppnt_cd}'/>" />
                        <input type="hidden" name="region_gbn" id="region_gbn" value="<c:out value='${lomRq.region_gbn}'/>" /><!-- javascript:customerPop('O');">< -->
 					  <input type="text" name="cntrt_oppnt_nm" id="cntrt_oppnt_nm" required alt="<spring:message code="clm.page.msg.manage.othPartyName" htmlEscape="true" />" value="<c:out value='${lomRq.cntrt_oppnt_nm}'/>" readonly="readonly" class="text_search" style="width:80%" /><%  if("1".equals(lomRq.get("total_cnt"))){ %><img src="<%=IMAGE %>/icon/ico_search.gif" class="cp" onclick="javascript:openNegoCustomer('O');" alt="search" />
<%  }%>             </td>
	                <th><spring:message code="clm.page.field.customer.registerNo" /></th>
	                <td><input type="text" name="cntrt_oppnt_rprsntman" id="cntrt_oppnt_rprsntman"  alt="<spring:message code="clm.page.msg.manage.announce212" htmlEscape="true" />" value="<c:out value='${lomRq.cntrt_oppnt_rprsntman}' escapeXml="false"/>" maxlength="60" class="text_full" style="width:80%; border:1px solid #fff" readonly="readonly" /></td>
	             </tr>
	             
	            <tr>
                    <th><spring:message code="clm.page.msg.manage.contType" /><span class='astro'>*</span></th>
                    <td colspan="5">
	                    <input type="hidden" name="biz_clsfcn" id="biz_clsfcn" value="<c:out value='${lomRq.biz_clsfcn}'/>" />
	                    <input type="hidden" name="depth_clsfcn" id="depth_clsfcn" value="<c:out value='${lomRq.depth_clsfcn}'/>" />
	                    <input type="hidden" name="cnclsnpurps_midclsfcn" id="cnclsnpurps_midclsfcn" value="<c:out value='${lomRq.cnclsnpurps_midclsfcn}'/>"  />
	                    <input type="hidden" name="cnclsnpurps_bigclsfcn" id="cnclsnpurps_bigclsfcn" value="<c:out value='${lomRq.cnclsnpurps_bigclsfcn}'/>" />
	                    
	                    <input type="hidden" name="en_biz_clsfcn_nm" id="en_biz_clsfcn_nm" value="<c:out value='${lomRq.biz_clsfcn}'/>" />
	                    <input type="hidden" name="en_depth_clsfcn_nm" id="en_depth_clsfcn_nm" value="<c:out value='${lomRq.depth_clsfcn}'/>" />
	                    <input type="hidden" name="en_cnclsnpurps_midclsfcn_nm" id="en_cnclsnpurps_midclsfcn_nm" value="<c:out value='${lomRq.cnclsnpurps_midclsfcn}'/>"  />
	                    <input type="hidden" name="en_cnclsnpurps_bigclsfcn_nm" id="en_cnclsnpurps_bigclsfcn_nm" value="<c:out value='${lomRq.cnclsnpurps_bigclsfcn}'/>" />         
	                    
                        <input readonly="readonly" type="text" name="biz_clsfcn_nm" id="biz_clsfcn_nm" required alt="<spring:message code="clm.page.msg.manage.contType" htmlEscape="true" />" value="<c:out value='${lomRq.biz_clsfcn_nm}'/>" class="text_full" style="width:30%" />
                        <input readonly="readonly" type="text" name="depth_clsfcn_nm" id="depth_clsfcn_nm" required alt="<spring:message code="clm.page.msg.manage.contType" htmlEscape="true" />" value="<c:out value='${lomRq.depth_clsfcn_nm}'/>" class="text_full" style="width:20%" />
                        <input readonly="readonly" type="text" name="cnclsnpurps_bigclsfcn_nm" id="cnclsnpurps_bigclsfcn_nm" required alt="<spring:message code="clm.page.msg.manage.contType" htmlEscape="true" />" value="<c:out value='${lomRq.cnclsnpurps_bigclsfcn_nm}'/>" class="text_full" style="width:20%" />
                        <input readonly="readonly" type="text" name="cnclsnpurps_midclsfcn_nm" id="cnclsnpurps_midclsfcn_nm" required alt="<spring:message code="clm.page.msg.manage.contType" htmlEscape="true" />" value="<c:out value='${lomRq.cnclsnpurps_midclsfcn_nm}'/>" class="text_full" style="width:20%" />
                        <a href="javascript:re_openChooseContractType();"><img src="<%=IMAGE %>/icon/ico_search_g.gif" /></a>  
                    </td>
                    
	            </tr>
	            <tr>
                    <th><spring:message code="clm.page.msg.manage.contItm" /><span class='astro'>*</span></th>
                    <td>
                        <select name="cntrt_trgt" id="cntrt_trgt" class="all" style="width:95%">
                           <c:out value="${combo.cntrtTrgt}" escapeXml="false"/>
                        </select>
                    </td>
                    <th><spring:message code="clm.page.msg.manage.contItmDtl" /></th>
                    <td colspan="3"><input type="text" name="cntrt_trgt_det" id="cntrt_trgt_det" value="<c:out value='${lomRq.cntrt_trgt_det}' escapeXml="false"/>" maxlength="166" class="text_full" /><!-- 500 --></td>
                </tr>
                <tr id="trCntrtInfo">
                    <th><spring:message code="clm.page.msg.manage.top30Cus" /><span class='astro'>*</span><img src="<%=IMAGE %>/icon/ico_search2.gif" class="cp" onClick="javascript:openTop30Customer();" alt="search" style="padding-left:5px" title="Top 30 Customer List"/></th>
                    <td>
                        <select name="cntrt_top30_cus" id="cntrt_top30_cus" class="all" style="width:95%">
                           <c:out value="${combo.cntrt_top30_cus }" escapeXml="false"/>
                        </select>
                    </td>
                    <th><spring:message code="clm.page.msg.manage.relatedPrd" /><span class='astro'>*</span></th>
                    <td>
                    	<select name="cntrt_rel_prd" id="cntrt_rel_prd" class="all" style="width:95%">
                           <c:out value="${combo.relPrdCombo}" escapeXml="false"/>
                        </select>                   
                    </td>
                    <th><spring:message code="clm.page.msg.manage.srcContDraft" /><span class='astro'>*</span></th>
                    <td>
                        <select name="cntrt_src_cont_drft" id="cntrt_src_cont_drft" class="all" style="width:95%">
                           <c:out value="${combo.srcConDraft }" escapeXml="false"/>
                        </select>
                    </td>
                </tr>
	            <tr>
                    
                    <th><spring:message code="clm.page.msg.manage.contPeriod" /><span class='astro'>*</span></th>
                    <td colspan="3">
                        <input type="text" name="cntrtperiod_startday" id="cntrtperiod_startday" required alt="<spring:message code="clm.page.msg.manage.contPerStrtDt" htmlEscape="true" />" value="<c:out value='${lomRq.cntrtperiod_startday}'/>" class="text_calendar02"/>
                        <c:if test="${lomRq.cntrtperiod_startday ne ''}">~</c:if>
                        <input type="text" name="cntrtperiod_endday" id="cntrtperiod_endday" required alt="<spring:message code="clm.page.msg.manage.contPerEndDt" htmlEscape="true" />" value="<c:out value='${lomRq.cntrtperiod_endday}'/>" class="text_calendar02" />
                    </td>
                    <th><spring:message code="clm.page.msg.manage.sendRcvDiv" /><span class='astro'>*</span></th>
                    <td>
                        <select name="payment_gbn" id="payment_gbn" class="all" style="width:150px" onchange="paymentGbnChange();">
                            <c:out value="${combo.paymentGbn}" escapeXml="false"/>
                        </select>
                    </td>
                </tr>
	            <tr>
                    <th><spring:message code="clm.page.msg.manage.contAmt" /></th>
                    <td colspan="3">
                        <input type="text" name="cntrt_amt" id="cntrt_amt" value="<c:out value='${lomRq.cntrt_amt}' />" onclick="javascript:paymentGbnChange2();" onkeyup="olnyNum(this)";  style="width: 150px; text-align: right; margin-right: 1px;" class="text_full" maxlength="19" />
                        <input type="checkbox" name="cntrt_untprc" id="cntrt_untprc" value="Y" onClick="clickCntrtUntprc();" /><label for="" > <spring:message code="clm.page.msg.manage.conclSingleAmt" /></label>
                    </td>
                    <th><spring:message code="clm.page.msg.manage.curr" /><span class='astro' id='view_astro' name='view_astro'>*</span></th>
                    <td>
                        <select name="crrncy_unit" id="crrncy_unit">
                            <c:out value="${combo.crrncyUnit}" escapeXml="false"/>
                        </select>
                    </td>
                </tr>
                <tr id="trCntrtUntprc">
                    <th><spring:message code="clm.page.msg.manage.singleAmtSum" /><span class='astro'>*</span></th>
                    <td colspan="5">
                        <span id="cntrt_untprc_expl">0</span>/<spring:message code="clm.page.msg.manage.stringLen300" /><br>
                        <textarea name="cntrt_untprc_expl" id="cntrt_untprc_expl" cols="30" rows="3" onkeyup="frmChkLenLang(this,300,'cntrt_untprc_expl','<%=langCd%>')" class="text_area_full"><c:out value='${lomRq.cntrt_untprc_expl}' escapeXml=""/></textarea></br></br>
                        <iframe src="<c:url value='/clm/blank.do' />" id="fileList3" name="fileList3" frameborder="0" width="100%" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
                    </td>
                </tr>
                <tr>
                    <th><spring:message code="clm.page.msg.manage.bg" /></th>
                    <td colspan="5">                                         
                    <span id="curByteExpl_body_mine1">0</span>/<spring:message code="clm.page.msg.manage.stringLen2000" /><br>
                    <textarea rows="10" cols="100%" name="body_mime1" id="body_mime1" maxLength="2000" onkeyup="frmChkLenLang(this,2000,'curByteExpl_body_mine1','<%=langCd%>')" class="text_area_full"><c:out value="${lomRq.pshdbkgrnd_purps}" escapeXml="" /></textarea>                            
                    </td>
                </tr>
                
                <tr id="trSpecialAttr">
                    <th><spring:message code="clm.page.msg.manage.etcMain" /></th>
                    <td colspan="5">
                        <span id="etc_main_cont">0</span>/<spring:message code="clm.page.msg.manage.stringLen800" /><br>
                        <textarea name="etc_main_cont" id="etc_main_cont" alt="<spring:message code="clm.page.msg.manage.etcMain" htmlEscape="true" />" cols="30" rows="4" onkeyup="frmChkLenLang(this,800,'etc_main_cont','<%=langCd%>')" class="text_area_full" maxLength="800"><c:out value="${lomRq.etc_main_cont}" escapeXml=""/></textarea>
                    </td>
                </tr>            
	        </table>
	        
	        <!-- 2014-04-08 Kevin Added. GERP Information Input form -->
            <iframe id="iframeGERP" name="iframeGERP" src="/clm/blank.do" style="border:0; width:100%; height:120px; margin:10px 0 5px 0;" scrolling="no"></iframe>
            
	        <div class='title_basic3'><spring:message code="clm.page.msg.manage.preApprInf" /> <img src="<%=IMAGE %>/common/step_q.gif" alt="info" title="<spring:message code="clm.page.msg.manage.announce093" htmlEscape="true" />" /></div>
	        <table class="table-style01">
                <colgroup>
                    <col width="12%" />
                    <col width="38%" />
                    <col width="12%" />
                    <col width="38%" />
                </colgroup>
                <tr>
                    <th><spring:message code="clm.page.msg.manage.apprDt" /><span class='astro'>*</span></th>
                    <td>
                        <input type="text" name="bfhdcstn_apbtday" id="bfhdcstn_apbtday" class="text_calendar02" value="<c:out value='${lomRq.bfhdcstn_apbtday}'/>" />
                    </td>
                    <th><spring:message code="clm.page.msg.manage.apprType" /><span class='astro'>*</span></th>
                    <td>
                        <select name="bfhdcstn_apbt_mthd" id="bfhdcstn_apbt_mthd">
                            <c:out value="${combo.bfhdcstnApbtMthd}" escapeXml="false"/>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th><spring:message code="clm.page.msg.manage.proposer" /><span class='astro'>*</span></th>
                    <td>
                        <input type="hidden" name="bfhdcstn_mtnman_id" value="<c:out value='${lomRq.bfhdcstn_mtnman_id}'/>" />
                        <input type="hidden" name="bfhdcstn_mtn_dept_nm" value="<c:out value='${lomRq.bfhdcstn_mtn_dept_nm}'/>" />
                        <input type="hidden" name="bfhdcstn_mtnman_jikgup_nm" value="<c:out value='${lomRq.bfhdcstn_mtnman_jikgup_nm}'/>" />
                        <input type="text" name="bfhdcstn_mtnman_nm" value="<c:out value='${lomRq.bfhdcstn_mtnman_nm}'/>" style="width:120px" class="text_search" onkeypress="if(event.keyCode==13) {searchEmployee(document.frm.bfhdcstn_mtnman_nm, 'MTN');return false;}" /><img src="<%=IMAGE %>/icon/ico_search.gif" class="cp" alt="search" onclick="searchEmployee(document.frm.bfhdcstn_mtnman_nm, 'MTN')" style="cursor:pointer"/>
                        <span id="bfhdcstn_mtnman_nm_span"><c:out value='${lomRq.bfhdcstn_mtnman_nm}'/></span>
                        <span id="bfhdcstn_mtnman_jikgup_nm_span"><c:out value='${lomRq.bfhdcstn_mtnman_jikgup_nm}'/></span> 
                        <span id="bfhdcstn_mtn_dept_nm_span"><c:out value='${lomRq.bfhdcstn_mtn_dept_nm}'/></span>
                    </td>
                    <th><spring:message code="clm.page.msg.manage.apprPer" /><span class='astro'>*</span></th>
                    <td>
                        <input type="hidden" name="bfhdcstn_apbtman_id" id="bfhdcstn_apbtman_id"  value="<c:out value='${lomRq.bfhdcstn_apbtman_id}'/>" />
                        <input type="text" name="bfhdcstn_apbtman_nm" id="bfhdcstn_apbtman_nm" value="<c:out value='${lomRq.bfhdcstn_apbtman_nm}'/>" style="width:120px" class="text_search" onkeypress="if(event.keyCode==13) {searchEmployee(document.frm.bfhdcstn_apbtman_nm, 'APBT');return false;}"/><img src="<%=IMAGE %>/icon/ico_search.gif" class="cp" alt="search" onclick="searchEmployee(document.frm.bfhdcstn_apbtman_nm, 'APBT')" style="cursor:pointer"/>
                        <input type="hidden" name="bfhdcstn_apbtman_jikgup_nm" id="bfhdcstn_apbtman_jikgup_nm" value="<c:out value='${lomRq.bfhdcstn_apbtman_jikgup_nm}'/>" />
                        <input type="hidden" name="bfhdcstn_apbt_dept_nm" id="bfhdcstn_apbt_dept_nm" value="<c:out value='${lomRq.bfhdcstn_apbt_dept_nm}'/>" />
                        <span id="bfhdcstn_bfhdcstn_apbtman_nm_span"><c:out value='${lomRq.bfhdcstn_bfhdcstn_apbtman}'/></span>
                        <span id="bfhdcstn_apbtman_jikgup_nm_span"><c:out value='${lomRq.bfhdcstn_apbtman_jikgup_nm}'/></span>
                        <span id="bfhdcstn_apbt_dept_nm_span"><c:out value='${lomRq.bfhdcstn_apbt_dept_nm}'/></span>
                    </td>
                </tr>
                
                <tr>
                    <th><spring:message code="clm.page.msg.manage.attachData" /> <img src="/images/las/ko/common/step_q.gif" alt="info" title="DOC, DOCX, GUL, PDF, OCX, XLS, XLSX, PPT, PPTX,&#13;JPG, HWP, TIF, RTF, ZIP, PTX, TML, TXT, LSX, EML, BMP, GIF, MSG, ALZ, DOT &#13;&#13;<spring:message code='las.msg.com.fileFilter'/>" /></th>
                    <td colspan="3">
                    	<div style='clear:both'>
		                	<spring:message code="clm.page.msg.manage.announce097" /> 
		                </div>
		                <div class='ico_maxsize fR'>Max Size : <span>10MB</span></div> 
                        <!-- Sungwoo replacement height size 2014-07-03-->
						<iframe src="<c:url value='/clm/blank.do' />" id="fileList4" name="fileList4" frameborder="0" class='addfile_iframe_d'  width="100%" scrolling="auto" allowTransparency="true"></iframe>            
                    </td>
                </tr>
            </table>
            
            <!-- tnc include JSP -->
			<jsp:include page="/clm/manage/completion.do">
				<jsp:param name="method" value="getTncLink" />
				<jsp:param name="cntrt_id4" value='<%=lomRq.get("cntrt_id") %>'/>
			</jsp:include>
            
            <div class='title_basic3'><spring:message code="clm.page.msg.manage.relContInf" /> 
                <img src="<%=IMAGE %>/common/step_q.gif" alt="info" title="<spring:message code="clm.page.msg.manage.announce069" htmlEscape="true" />" />
                 <img src="<%=IMAGE %>/btn/btn_up.gif" alt="up" onclick="tit_Toggle(this,'div_rel_contract');" class='cp'/></div>
	            <div id="div_rel_contract">
	            <table cellspacing="0" cellpadding="0" border="0" class="table-style01">
	                <colgroup>
	                    <col width="20%" />
	                    <col width="50%" />
	                    <col width="10%" />
	                    <col/>
	                </colgroup>
	                <tr>
	                    <th class="tC"><spring:message code="clm.page.msg.manage.relation" /></th>
	                    <th class="tC"><spring:message code="clm.page.msg.manage.relCont" /></th> <!-- 연관계약 -->
	                    <th class="tC"><spring:message code="clm.page.msg.manage.define" /></th>
	                    <th class="tC"><spring:message code="clm.page.msg.manage.relDetail" /></th>
	                    <th class="tC"></th><!-- Sungwoo added 2014-07-14 -->                   
	                </tr>               
	                <tr id="trRelationContract">
	                     <td class='tC'>
	                        <select name="rel_type" id="rel_type" onChange="reltypeChange('<%=langCd%>');">
	                            <c:out value="${combo.relType}" escapeXml="false"/>
	                        </select>
	                     </td>
	                     <td class='tC'>
	                     	 <input type="hidden" name="parent_cntrt_id" id="parent_cntrt_id" />
	                         <input type="text" name="parent_cntrt_name" id="parent_cntrt_name" class="text_search" style="width:80%" readonly="readonly" /><img src="<%=IMAGE %>/icon/ico_search.gif" onclick="javascript:popupListContract('C03201');" class="cp" alt="search" />
	                     </td>
	                     <td class='tC'><select name="rel_defn" id="rel_defn"></select></td>
	                     <td class='tC'>
	                     	<input type="text" name="expl" id="expl" class="text" style="width:50%" maxlength="333" /> <a href="javascript:actionRelationContract('insert','','');"><img src="<%=IMAGE %>/btn/btn_regist.gif" /></a>
	                     </td>
	                     <td class='tC'></td>
	                </tr>
	                <c:out value="${contRc}" escapeXml="false"/>               
	            </table>
	            <table cellspacing="0" cellpadding="0" border="0" class="table-style01 borz01">
	            <tr>
	                <td>
	                    ※ <spring:message code="clm.page.msg.manage.announce130" /> <br/>
	                    &nbsp;<spring:message code="clm.page.msg.manage.forRel" />  <br/>
	                    &nbsp;<spring:message code="clm.page.msg.manage.smRel" /> <br/>
	                    &nbsp;<spring:message code="clm.page.msg.manage.chgCancel" /> <br/>
	                    &nbsp;<spring:message code="clm.page.msg.manage.announce005" />  
	                </td>
	                </tr>
	            </table>
            </div>
			
			<!-- 이력정보 -->
			<div id="contractHis-list"></div>
			<!-- //이력정보 -->	
			
		<!-- button -->
				<div class="t_titBtn">
      		<div class="btn_wrap_c">
					<!-- 의뢰시  -->
					<!-- 미리보기/임시저장/검토의뢰/목록 -->
				<%	if("forwardInsert".equals(lomRq.get("status"))){%>
					<span class="btn_all_w"><span class="check2"></span><a href="javascript:forwardInsertConsideration('req');"><spring:message code="clm.page.button.contract.request" /></a></span>
					<span class="btn_all_w"><span class="tsave"></span><a href="javascript:forwardInsertConsideration('save');"><spring:message code="clm.page.msg.manage.tmpSave" /></a></span>
				<%	} %>
					<!-- 수정시 -->
					<!-- 미리보기/임시저장/검토의뢰/목록 -->
				<%	if("forwardModify".equals(lomRq.get("status")) || "forwardReq".equals(lomRq.get("status"))){%>				
			   		<% 
			   		//이전 의뢰ID가 없을 경우 : 의뢰건
			   		//이전 의뢰ID가 있을 경우 : 재의뢰 OR 최종본의뢰 OR 재의뢰중 임시저장 OR 최종본의뢰중 임시저장
			   		if(sprev_cnsdreq_id.equals("") && "C02601".equals(lomRq.get("depth_status"))){%>
					<span class="btn_all_w"><span class="check2"></span><a href="javascript:forwardInsertConsideration('req');"><spring:message code="clm.page.button.contract.request" /></a></span>
					<span class="btn_all_w"><span class="tsave"></span><a href="javascript:forwardInsertConsideration('save');"><spring:message code="clm.page.msg.manage.tmpSave" /></a></span>
			   		<% }else if(! sprev_cnsdreq_id.equals("") && ("C04201".equals(lomRq.get("prgrs_status")) || "C04305".equals(lomRq.get("cnsd_status")))){ %>
					<% if(! sprev_cnsdreq_id.equals("") && "N".equals(lomRq.get("plndbn_req_yn"))){%>
						<span class="btn_all_w"><span class="check2"></span><a href="javascript:forwardInsertConsideration('again');"><spring:message code="clm.page.msg.manage.reply" /></a></span>
						<% }else if(! sprev_cnsdreq_id.equals("") && "Y".equals(lomRq.get("plndbn_req_yn")) && "".equals(lomRq.get("cnslt_no"))){%>	
						<span class="btn_all_w"><span class="check3"></span><a href="javascript:forwardInsertConsideration('last');"><spring:message code="clm.page.msg.manage.finalVerReq" /></a></span>
						<% }%>	
						<span class="btn_all_w"><span class="tsave"></span><a href="javascript:forwardInsertConsideration('save');"><spring:message code="clm.page.msg.manage.tmpSave" /></a></span>					
			   		<% }%>			
				<%	} %>					
					<!-- 최종본의뢰시 -->
					<!-- 미리보기/예정보의뢰/목록 -->
				<%	if(lomRq.get("prev_cnsdreq_id")!= null && "forwardLast".equals(lomRq.get("status"))){%>
					<span class="btn_all_w"><span class="check3"></span><a href="javascript:forwardInsertConsideration('last');"><spring:message code="clm.page.msg.manage.finalVerReq" /></a></span>	
					<span class="btn_all_w"><span class="tsave"></span><a href="javascript:forwardInsertConsideration('save');"><spring:message code="clm.page.msg.manage.tmpSave" /></a></span>									
				<%	} %>
				
				<!-- 2011.10.12 주석처리함. 김재원 -->
				<span class="btn_all_p"><span class="list"></span><a href="javascript:forwardConsideration('list');"><spring:message code="clm.page.msg.manage.list" /></a></span>
			</div>
		</div>
				<!-- //button -->
	</form:form>
	</div>
	</div>
<!-- //container -->
</div>

<%if("forwardReq".equals(lomRq.get("status")) || "forwardLast".equals(lomRq.get("status"))){		//재의뢰 or 최종본의뢰%>
<script language="javascript" for="wec" event="OnInitCompleted();">
</script>
<%}else if("forwardModify".equals(lomRq.get("status"))){				//수정%>
<script language="javascript" for="wec" event="OnInitCompleted();">
</script>
<%}else{ %>
<script language="javascript" for="wec" event="OnInitCompleted();">
	if(document.frm.status.value=="forwardInsert") {
		openChooseContractType();
	}
</script>
<%} %>
		<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- // footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</body>
</html>