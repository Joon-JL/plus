<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sec.clm.manage.dto.ConsultationForm" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%--
/**
 * 파  일  명 : RegConsideration_i_new.jsp
 * 프로그램명 : 체결후등록   작성
 * 설      명 : 
 * 작  성  자 : 제이남
 * 작  성  일 : 2013.04
 */
--%>
<% 
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	ConsultationForm command = (ConsultationForm)request.getAttribute("command");
	ListOrderedMap lomRq = (ListOrderedMap)request.getAttribute("lomRq");
	ArrayList listDc = (ArrayList)request.getAttribute("listDc");
	ArrayList listTs = (ArrayList)request.getAttribute("listTs");	
	ArrayList listRc = (ArrayList)request.getAttribute("listRc");	
	ArrayList listCa = (ArrayList)request.getAttribute("listCa");
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="clm.main.title" /></title>
<LINK href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet">
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="/script/clms/common.js"></script>
<script language="javascript" src="/script/secfw/common/common.js"></script>
<script language="javascript" src="/script/secfw/common/CommonProgress.js"></script>                                                                                                                                                    
<script language="javascript" src="/util/fsw/fsw.js"></script>
<script language="javascript" src="/script/clms/clms_common.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.form.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.flot.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="/script/secfw/jquery/calendar/jquery.mousewheel.min.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script>
<!--

var chk = 0;
var chk_flag = false;

	$(document).ready(function(){
		
		$("#type_sel21").click(function(){
			re_openChooseContractType();
		});
		
		tncOpenYn();
	});
	
	function tncOpenYn(){
    	
    	var frm = document.frm;
    	   	
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
	    
    	$("#cntrt_top30_cus").attr('disabled', true);
    	$("#cntrt_top30_cus").val("");
		$("#cntrt_src_cont_drft").attr('disabled', true);
    	$("#cntrt_src_cont_drft").val("");
    }
	
	function tncfunc_false(){
	    
    	$("#cntrt_top30_cus").attr('disabled', false);
		$("#cntrt_src_cont_drft").attr('disabled', false);
    }

	function init(){
		initCal("cntrtperiod_startday");
		initCal("cntrtperiod_endday");
		initCal("bfhdcstn_apbtday");
		initCal("cntrt_cnclsnday");
		
		var frm = document.frm;
		
		//계약단가요약에 데이터가 있으면 단가내역요약 보이게 수정.
		if(frm.cntrt_untprc_chk.value != ""){
			document.getElementById("cntrt_untprc").checked = true;
			$("#trCntrtUntprc").show();
		}
		//tit_Toggle(this, 'tr_down02'); // 김재원 추가 2011.10.15
		
		if("<c:out value='${lomRq.seal_mthd}'/>" == "C02102"){
			$('#ss1').attr("style", "visibility:visible;border-left:1px solid #CADBE2");
			$('#ss2').attr("style", "visibility:visible;border-left:1px solid #CADBE2");
		}else{
			//크로스브라우저 깨짐 해결
			$("seal-tr").attr("style","display:");
		}
		
		if("<c:out value='${lomRq.crrncy_unit}'/>" == ""  && "<c:out value='${lomRq.payment_gbn}'/>" ==""){
            $("#cntrt_amt").val("");
            $("input[name=cntrt_amt]").attr('disabled', true);
            $("input[name=cntrt_untprc]").attr('checked', false);
            $("#crrncy_unit").attr('disabled', true);
            $("#crrncy_unit").html("<option value=><spring:message code='clm.page.field.srch.payment_gbn_value4'/></option>");
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
        }
		
		var collapseIcon = '<%=IMAGE%>/icon/ico_plus.gif';
        var expandIcon = '<%=IMAGE%>/icon/ico_minus.gif';
        
		//페이지 시작과 함께 유형 정보가 떠야 된다.
        if(document.frm.status.value=="forwardInsert") {
            openChooseContractType();
        }
		
        initPage();
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
	     
	   //#6 체결본사본 첨부파일
	     frm.target          		= "fileList6";
	     frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
	     frm.method.value    		= "forwardClmsFilePage";
	     frm.file_bigclsfcn.value 	= "F012";
		 frm.file_midclsfcn.value	= "F01203";
		 frm.file_smlclsfcn.value 	= "";
	     frm.ref_key.value 			= frm.mod_cntrt_id.value ;
	     frm.view_gbn.value 		= view;
	     frm.preAllowFileList.value  = "PDF,P7M,"; //체결본은 pdf만 등록하게끔 한다.
	     frm.multiYn.value 			= "N";
	     frm.fileInfoName.value 	= "fileInfos6";
	     frm.fileFrameName.value 	= "fileList6";
	     frm.submit();
	}	
  	
	function forwardConsideration(arg){
		var frm = document.frm;	
		frm.target = "_self";		 
		frm.action = "<c:url value='/clm/manage/registration.do' />";
		frm.method.value = "listManageRegistration";			
		
		frm.submit();		
	}
	
	//체결후등록 처리
	function insertConsideration(arg){
  		var frm = document.frm;  		
		frm.submit_status.value = arg;
  		frm.target = "_self";
  		
  		if(arg === "save"){	//임시저장  			
  			if(!confirm("<spring:message code='clm.page.msg.manage.announce137' />")) return;  			
  		} else if(arg === "list"){	//목록  			
  			forwardConsideration('list');//리스트로 이동 
  			return;
  		}
  		
  		if(validateTempSave(frm)){ 	
  	  		if(!frm.req_title.value){//의뢰제목REQ_TITLE
  	  			alert("<spring:message code='clm.page.msg.manage.announce022' />");
	  	  		frm.req_title.focus();	
	  	  		return;
  	  		}
  	  		if(!frm.cntrt_oppnt_cd.value){//거래상대방
	  			alert("<spring:message code='clm.page.msg.manage.announce012' />");
	  			frm.cntrt_oppnt_nm.focus();
  	  			return;  	  			
	  		}
  	  		if(!frm.biz_clsfcn.value){//계약유형
  				alert("<spring:message code='clm.page.msg.manage.announce052' />");
  				frm.biz_clsfcn.focus();
  	  			return;  	  			
  			}
  			
  			/* TNC 연계 데이타 인 경우 연관계약은 필수 입력임 trRelationContract_tb trRelationContract.  */
	 		<c:if test="${'Y' eq lomRq.TNC_YN }">
	 			// alert("trRelationContractCont).length : " + $("#trRelationContractCont").length);
	 			// alert("trRelationContract_tb tr).length : " + $("#trRelationContract_tb tr").length);
	 			
	 			var valid_cnt = 0;
	 			
				$("#trRelationContract_tb tr").each(function(index){
					if($(this).attr("alt")!=null)
						valid_cnt++;
			    });
				
				// clm.page.msg.manage.relCont=연관계약    las.msg.alert.isRequired=은(는) 필수 입력 항목입니다. 
				var alertmsg = "<spring:message code='clm.page.msg.manage.relCont' />" + " <spring:message code='las.msg.alert.isRequired' />";
	 			
				if(valid_cnt == 0){
					alert(alertmsg);
					return;
				}
				
			</c:if>
			
  			/*연관계약 정보를 작성하고 등록버튼을 누르지 않으면 
  			  계약명이 남아있으므로, 등록하지 않은것으로 판단하여 등록 안내창을 띄움 / null 값이면 작성하지 않은것으로 판단하여 넘어감 */
  			if(frm.parent_cntrt_name.value != ""){
				alert("<spring:message code='las.msg.alert.postConcReg.relContInfoReq' />"); 
				frm.parent_cntrt_name.focus();
				return;
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
		
  		// 2012.03.19 계약기간 필수입력 alert 추가 added by hanjihoon
  		if(frm.cntrtperiod_startday.value == ""){
			alert("<spring:message code='clm.page.msg.manage.announce041' />");
			frm.cntrtperiod_startday.focus();
			return;
		}
  		if(frm.cntrtperiod_endday.value == ""){
			alert("<spring:message code='clm.page.msg.manage.announce041' />");
			frm.cntrtperiod_endday.focus();
			return;
		}
  		
		// 서명기본 체크 
  		$("#seal_mthd_chk:checked").each(function(){
  			frm.seal_mthd.value = $(this).val();
 		});
		
		//이행정보 필수 입력 체크
		if($('input[name="exec_itm_arr"]').length > 0){
  			//var arr = document.getElementsByName("exec_itm_arr");
 			if($('input[name="exec_itm_arr"]').length > 1){
	  			for(var j = 0; j < $('input[name="exec_itm_arr"]').length; j++){
					
	 				if(frm.exec_itm_arr[j].value == ""){
						alert("<spring:message code='las.mag.error.ask.alert001'/>");//이행항목은 필수입력 항목입니다.
						frm.exec_itm_arr[j].focus();
						return;
					}
					if(frm.exec_plndday_arr[j].value == ""){
						alert("<spring:message code='las.mag.error.ask.alert002'/>");//이행정보의 완료예정일은  필수입력 항목입니다.
						frm.exec_plndday_arr[j].focus();
						return;
					}
					if(frm.exec_amt_arr[j].value == ""){
						alert("<spring:message code='las.mag.error.ask.alert003'/>");//이행정보의 금액은  필수입력 항목입니다.
						frm.exec_amt_arr[j].focus();
						return;
					}
					if(!checkNumber(frm.exec_amt_arr[j].value,",")){
						alert("<spring:message code='las.mag.error.ask.alert007'/>");//이행정보의 금액은  숫자만 입력 가능합니다.
						frm.exec_amt_arr[j].focus();
						return;
					}
					if(frm.exec_cont_arr[j].value == ""){
						if(frm.exec_gbn_arr[j].value == "C05501"){
							alert("<spring:message code='las.mag.error.ask.alert004'/>");//이행정보의 지불조건은  필수입력 항목입니다.
							frm.exec_gbn_arr[j].focus();
							return;
						}else if(frm.exec_gbn_arr[j].value == "C05502"){//이행정보의 수금조건은  필수입력 항목입니다.
							alert("<spring:message code='las.mag.error.ask.alert005'/>");
							frm.exec_gbn_arr[j].focus();
							return;
						}else if(frm.exec_gbn_arr[j].value == "C05503"){
							alert("<spring:message code='las.mag.error.ask.alert006'/>");//이행정보의 Description은  필수입력 항목입니다.
							frm.exec_gbn_arr[j].focus();
							return;
						}
					}
				}
 			}else{
 				if(frm.exec_itm_arr.value == ""){
					alert("<spring:message code='las.mag.error.ask.alert001'/>");//이행항목은 필수입력 항목입니다.
					frm.exec_itm_arr.focus();
					return;
				}
				if(frm.exec_plndday_arr.value == ""){
					alert("<spring:message code='las.mag.error.ask.alert002'/>");//이행정보의 완료예정일은  필수입력 항목입니다.
					frm.exec_itm_arr.focus();
					return;
				}
				if(frm.exec_amt_arr.value == ""){
					alert("<spring:message code='las.mag.error.ask.alert003'/>");//이행정보의 금액은  필수입력 항목입니다.
					frm.exec_amt_arr.focus();
					return;
				}
				if(!checkNumber(frm.exec_amt_arr.value,",")){
					alert("<spring:message code='las.mag.error.ask.alert007'/>");//이행정보의 금액은  숫자만 입력 가능합니다.
					frm.exec_amt_arr.focus();
					return;
				}
				if(frm.exec_cont_arr.value == ""){
					if(frm.exec_gbn_arr.value == "C05501"){
						alert("<spring:message code='las.mag.error.ask.alert004'/>");//이행정보의 지불조건은  필수입력 항목입니다.
						frm.exec_gbn_arr.focus();
						return;
					}else if(frm.exec_gbn_arr.value == "C05502"){
						alert("<spring:message code='las.mag.error.ask.alert005'/>");//이행정보의 수금조건은  필수입력 항목입니다.
						frm.exec_gbn_arr.focus();
						return;
					}else if(frm.exec_gbn_arr.value == "C05503"){
						alert("<spring:message code='las.mag.error.ask.alert006'/>");//이행정보의 Description은  필수입력 항목입니다.
						frm.exec_gbn_arr.focus();
						return;
					}
				}
 			}
  		}
	
  		if(arg == "req"){
  			
  			if(!validateClmForm(frm)) return;
  				 		
		  		//의뢰일과 회신요청일 
	 			var str_req_dt = frm.req_dt.value.replace('-','').replace('-','');

	  			if(!frm.cntrt_oppnt_type.value){
	  				alert("<spring:message code='clm.page.msg.manage.announce034' />");
	  				frm.cntrt_oppnt_type.focus();
	  				return;
	  			}
	  			if(!frm.cntrt_trgt.value){
	  				alert("<spring:message code='clm.page.msg.manage.announce028' />");
	  				frm.cntrt_trgt.focus();
	  				return;
	  			}
	  			
	 			//계약기간 Chk
	 			var str_from = frm.cntrtperiod_startday.value.replace('-','').replace('-','');
	 			var str_to = frm.cntrtperiod_endday.value.replace('-','').replace('-','');
	 			var cue = frm.cntrt_untprc_expl.value.replace(/^\s+/,""); // lpad 하는 것입니다.
	 			if(eval(str_from) > eval(str_to)){
	 				alert("<spring:message code='clm.page.msg.manage.announce039' />");
	 				return;
	 			}

	 			//지불/수금 구분,
				if(!frm.payment_gbn.value){
					var exe_cnt = 0;
					if($('#payment_gbn > option:selected').val() =="C02001") {	//지불/수금
						
						if($('#cntrt_amt').val() == ''){
							
							if(!$("[name=cntrt_untprc]").is(":checked")){
								alert("<spring:message code='clm.page.msg.manage.announce038' />");	
								$('#cntrt_amt').focus();
								return;
							}
						}
						
						exe_cnt = $('#executionTbody tr').length + $('#executionTbody2 tr').length + $('#executionTbody3 tr').length;
						if(exe_cnt==0){
							alert("<spring:message code='clm.page.msg.manage.announce152' />");
							return;
						}
					} else if($('#payment_gbn > option:selected').val() =="C02002") {	//지불
						
						if($('#cntrt_amt').val() == ''){
							if(!$("[name=cntrt_untprc]").is(":checked")){
								alert("<spring:message code='clm.page.msg.manage.announce038' />");
								$('#cntrt_amt').focus();
								return;
							}
						}
					
						exe_cnt = $('#executionTbody tr').length;
						if(exe_cnt==0) {
							alert("<spring:message code='clm.page.msg.manage.announce153' />");
							return;
						}
					} else if($('#payment_gbn > option:selected').val() =="C02003") {	//수금
						
						if($('#cntrt_amt').val() == ''){
							if(!$("[name=cntrt_untprc]").is(":checked")){
								alert("<spring:message code='clm.page.msg.manage.announce038' />");	
								$('#cntrt_amt').focus();
								return;
							}
						}
					
						exe_cnt = $('#executionTbody2 tr').length;
						if(exe_cnt==0) {
							alert("<spring:message code='clm.page.msg.manage.announce151' />");
							return;
						}	
					}			
				}else{
	  			//지불 or 수불 일때문 계약 금액 필수
		  			if("C02002" == frm.payment_gbn.value || "C02003" == frm.payment_gbn.value || "C02001" == frm.payment_gbn.value ){
		  				if($("[name=cntrt_untprc]").is(":checked")){
		  					if(!$("#cntrt_untprc_expl").val()){  			
		  		  				//cntrt_untprc_expl
		  		  				alert("<spring:message code='clm.page.msg.manage.announce073' />");  //단가로 체결이 체크되었습니다.\\n단가내역 요약을 입력 하여 주시기 바랍니다.		
		  		  				frm.cntrt_untprc_expl.focus();
		  		  				return;
		  					}
		  				}
		  			}
	  			}
	 			if($("[name=cntrt_untprc]").is(":checked")){
	 				if(!$("#cntrt_untprc_expl").val()){  			
	  				alert("<spring:message code='clm.page.msg.manage.announce073' />"); 
	  				frm.cntrt_untprc_expl.focus();
	  				return;
	 				} 				
	 				if(cue.length == 0){
	 	  				alert("<spring:message code='clm.page.msg.manage.announce061' />");
	 	  				return;
	 	  			}
	 			} 
	  		  	//사전품의발의자
	 			if(!frm.bfhdcstn_mtnman_id.value){
	 				alert("<spring:message code='clm.page.msg.manage.announce098' />");
	 				frm.bfhdcstn_mtnman_nm.focus();
	 				return;
	 			}
	  			//사전품의승인자
	 			if(!frm.bfhdcstn_apbtman_id.value){
	 				alert("<spring:message code='clm.page.msg.manage.announce101' />");
	 				frm.bfhdcstn_apbtman_nm.focus();
	 				return;
	 			}
	  			//사전품의승인일
	 			if(!frm.bfhdcstn_apbtday.value){
	 				alert("<spring:message code='clm.page.msg.manage.announce100' />");
	 				frm.bfhdcstn_apbtday.focus();
	 				return;
	 			}
	  			//사전품의승인방식
	 			if(!frm.bfhdcstn_apbt_mthd.value){
	 				alert("<spring:message code='clm.page.msg.manage.announce099' />");
	 				frm.bfhdcstn_apbt_mthd.focus();
	 				return;
	 			}
	 			
	 			if(frm.cntrt_cnclsnday.value == ""){
					alert("<spring:message code='clm.page.msg.manage.announce058' />");	 	
					frm.cntrt_cnclsnday.focus();
	 				return;
	 			}

	 			var seal_mthd = $('input[name*=seal_mthd]:checked').val();

	 			//서명이라도 날인담당자 필수로 변경 ( 2013.05.30 jnam )
	 			if($('#seal_ffmtman_id').val() == ''){
 					alert("<spring:message code='clm.page.msg.manage.announce072' />");//날인담당자는 필수 입력 항목입니다.
 					return;
 				}
	 			if(seal_mthd == "C02102"){ //서명
	 				if($('#signman_id').val() == ''){
	 					alert("<spring:message code='clm.page.msg.manage.announce104' />");//서명담당자는 필수 입력 사항입니다.
	 					return;
	 				}
	 			}
	 			
	 			if($('#cntrt_respman_id').val() == ''){
	 				alert("<spring:message code='clm.page.msg.manage.announce043' />");//계약담당자는 필수 입력 항목입니다.
	 				return;
	 			}
		}// end if(req)
		viewHiddenProgress(true);
		
		// 2014-07-04 Kevin added.
		// Prefix "[Post-Conclusion]" eliminated then put prefix when submitted.
		var pre_fix = '<c:out value="${lomRq.prefix}" />';
		$("#req_title").val().replace(pre_fix, "");
		$("#req_title").val(pre_fix+$("#req_title").val());
		
		var options = {
			url: "<c:url value='/clm/manage/registration.do?method=insertRegistration' />",
			type: "post",
			async: false,
			dataType: "json",			
			success: function(responseText, statusText) {  //undefined

				//계약서 필수 에서 빼기 최종 의뢰 ,,,,,,재의뢰시 에 .....최종본
	    		if(responseText.returnValue == undefined){
					
					alert("<spring:message code='clm.page.msg.manage.announce157' />");
					//forwardInsertContractMaster();  // 오타로 인한 수정
					//tit_Toggle(this, 'tr_down02');
				}else if("save" == arg  && responseText.returnValue == "Y"){					
					alert(responseText.msg);//정상처리 되었습니다. 
					forwardConsideration('list');//리스트로 이동 
				}else if("req" == arg ){
					$('#cnsdreq_id').val(responseText.cnsdreq_id);
					$('#cntrt_id').val(responseText.cntrt_id);
					forwardApproval();
				}
				
			}
		};
		
	  		//사본첨부파일
   			 fileList6.UploadFile(function(uploadCount){
   				if(uploadCount == -1){
   					//initPage();
   					alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
   					viewHiddenProgress(false);
   					return; 					
   				}
   				if(uploadCount == 0 && arg != "save"){
   					alert("<spring:message code='clm.page.msg.manage.copyVer' /> <spring:message code='secfw.msg.error.fileNon' />");
   					viewHiddenProgress(false);
   					//initPage();
   	                return;
   				}
 					
 					fileList3.UploadFile(function(uploadCount){
  					if(uploadCount == -1){
  						//initPage();
  						alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
  						viewHiddenProgress(false);	  						
  						return; 					
  					}
  					
  					//계약관련#3
 	    			 fileList4.UploadFile(function(uploadCount){
 	    				if(uploadCount == -1){
 	    					//initPage();
 	    					alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
 	    					viewHiddenProgress(false);
 	    					return; 					
 	    				}	 	 	    					 	 	    				
	    				
    	  	  				$("#frm").ajaxSubmit(options);
 	    			});//end fileList4		 
 			 	});//end fileList3
 			 });//end fileList6
  
	}
	//데이터 저장 후 결재창 오픈
	function forwardApproval(){
		var frm = document.frm;	
		
		PopUpWindowOpen('', "1024", "768", false);
		frm.approval_key.value = frm.cnsdreq_id.value;
		frm.target = "PopUpWindow";		 
		frm.action = "<c:url value='/clm/manage/registration.do' />";
		frm.method.value = "makeApprovalHtmlNew";			
		
		frm.submit();	
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
			$("#crrncy_unit").html("<option value=><spring:message code='clm.page.field.srch.payment_gbn_value4'/></option>");
			$("input[name=cntrt_untprc]").attr('disabled', true);
			$("#trCntrtUntprc").hide();
			$("#cntrt_untprc_expl").val("");
			//단가내역 요약 첨부파일 초기화
			initCntrtUntprcExpl();
		}else{			
			$("input[name=cntrt_amt]").attr('disabled', false);
			getCodeSelectByUpCd("frm", "crrncy_unit", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=A&combo_grp_cd=C5&combo_type=S&combo_del_yn=N');
			$("input[name=cntrt_untprc]").attr('disabled', false);
			$("#crrncy_unit").attr('disabled', false);
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
			  +	"<input type=\"text\" name=\"arr_expl\" id=\"expl\" value=\"<spring:message code='clm.page.msg.manage.relContExp' htmlEscape='true' />  >>>>>>> <<<<<<<<  \" style=\"width:600px\" class=\"text_search\"/>  "	
			  +	"<a href=\"javascript:minusRelCntrt();\"><img src=\"<%=IMAGE%>/icon/ico_minus.gif\" /></a>  "	
			  +	"</td>  "	
			  +	"</tr>  "	;
		  $("#trRelationContract").after(html);	
		  
		  getCodeSelectByUpCd("frm", "rel_type"+($('#tab_contents_sub_conts1 tr').length-1), '/common/clmsCom.do?combo_sys_cd=CLM&method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=A&combo_grp_cd=C032&combo_type=S&combo_del_yn=N&combo_selected=');
		  
	  }	
	  /**
	  * 연관계약 타입 선택
	  */
	  function reltypeChange(){
		  var type = $("#rel_type").val();  
		  if("C03201" == type){
				$("#rel_defn").html("<option value=NDA>NDA</option><option value=MOU/LOI>MOU/LOI</option>");
			}else if("C03202" == type){
				$("#rel_defn").html("<option value=Master>Master</option><option value=Sub>Sub</option>");
			}else if("C03203" == type){
				$("#rel_defn").html("<option value='<spring:message code='clm.page.msg.manage.preChg' />'><spring:message code='clm.page.msg.manage.preChg' /></option><option value='<spring:message code='clm.page.msg.manage.befCalncel' />'><spring:message code='clm.page.msg.manage.befCalncel' /></option>");
			}else{
				$("#rel_defn").html("<option value=><spring:message code='clm.page.msg.manage.noSelect' /></option>");
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
	  * 연관계약 정보 reload
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
	  *사용 안함
	  */
	  function addContractTitle_x(){
		  var html ="<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\">  "							
			  +	"<td class=\"tal_lineL\"><span class=\"th-color\"><spring:message code='clm.page.field.contract.detail.relation.name' /></span></td>  "	;
			  
		  //tab_contents
		  $("#tab_contents").append("");
		  $("#tab_contents").append(html);
	  }
	 
	  
	/**
  	* Reload
  	*/
  	/**
	* 관리페이지 이동
	*/
	function forwardModifyConsideration(){
		var frm = document.frm;
		//viewHiddenProgress(true) ;
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
			  						//initPage();
			  						alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
			  						return; 					
			  					}		  					
			  					//계약관련#4
			 	    			 fileList4.UploadFile(function(uploadCount){
			 	    				if(uploadCount == -1){
			 	    					//initPage();
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
			if(frm.cntrt_nm.value != ""){
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
	      var en_biz_clsfcn_nm   = "";
	 	  var depth_clsfcn       = $("#depth_clsfcn").val(); // "";
	 	  var depth_clsfcn_nm    = ""; 	
	 	  var en_depth_clsfcn_nm = "";
	 	  var cnclsnpurps_bigclsfcn     	= $("#cnclsnpurps_bigclsfcn").val(); // "";
	 	  var cnclsnpurps_bigclsfcn_nm  	= "";
	 	  var en_cnclsnpurps_bigclsfcn_nm  	= "";
	 	  var cnclsnpurps_midclsfcn 		= $("#cnclsnpurps_midclsfcn").val(); // "";
	 	  var cnclsnpurps_midclsfcn_nm 		= "";
	 	  var en_cnclsnpurps_midclsfcn_nm 	= "";
	 	  var result = new retObj(biz_clsfcn, biz_clsfcn_nm, en_biz_clsfcn_nm,  depth_clsfcn, depth_clsfcn_nm, en_depth_clsfcn_nm, cnclsnpurps_bigclsfcn, cnclsnpurps_bigclsfcn_nm, en_cnclsnpurps_bigclsfcn_nm, cnclsnpurps_midclsfcn, cnclsnpurps_midclsfcn_nm, en_cnclsnpurps_midclsfcn_nm);
	 	  
	      PopUpWindowOpen("/clm/manage/chooseContractType.do?method=forwardChooseContractTypePopup", 1000, 630, false, result);
	      
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
	 *모달팝업
	 */
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
	
	 	var sFeatures = "dialogWidth:" + popupwidth +"px;dialogHeight:" + popupheight+"px;scroll:" + (bScroll ? "on":"off")+ ";status:0ff;resizable:no;";
	     window.showModalDialog(surl, obj, sFeatures);
	  }
	 
	 	 
	 /**
	 * 사전품의발의자  사전품의 승인자 정보	 /clm/manage/chooseClient.do   forwardChooseClientPopup
	 */
	 function searchEmployee(srcFrm, gb){
		 var frm = document.frm;
		 var srchValue = comTrim(srcFrm.value); //입력받은 값 
		 
		 if (srchValue == "" || srchValue.length < 4) { //최소 2자리 이상 입력받게 한다.
	     	 alert('<spring:message code='secfw.msg.error.nameMinByte' />');
	     	 srcFrm.focus();
	         return;
	     }
		 
		 //사전품의발의자인지 사전품의승인자정보에서 호출했는지 구분해주기 위해.
	     frm.searchEmployeeGb.value = gb;
		 
		 PopUpWindowOpen('', 800, 450, false);
		 
		 frm.target = "PopUpWindow";
		 frm.srch_user_cntnt_type.value = "userName"; //이름으로 검색  - 보통 이름으로 검색함
		 frm.srch_user_cntnt.value = srchValue;
         frm.action = "<c:url value='/secfw/esbOrg.do' />";
         frm.method.value = "listEsbEmployee";
         frm.submit();

	 }
	 /*
	   *	임직원조회팝업 
	   */

	  function searchEmployee2(flag) {
	  	var frm 		= document.frm;
	  	var srchValue 	= "";
	  	var obj 		= new Object();
	  	if(flag=="sign"){
	  		obj = frm.signman_search_nm;
	  	} else if(flag=="contract") {
	  		obj = frm.cntrt_respman_search_nm;
	  	} else if(flag=="appr"){
	  		obj = frm.approvalman_search_nm;
	  	} else if(flag=="MTN"){
	  		obj = frm.bfhdcstn_mtnman_nm;
	  	} else if(flag=="APBT"){
	  		obj = frm.bfhdcstn_apbtman_nm;
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
	      	PopUpWindowOpen2('', 800, 450, false,"PopUpEmployee");
	      	frm.submit();
	      } 
	  } 
	 /**
	 * 임직원 조회정보 결과 setting
	 */
	 function setUserInfos(obj) {
		var frm = document.frm;
		var srch_user_target = frm.srch_user_cntnt_target.value;
		var userInfo = obj;   
		if(srch_user_target=="sign") {
	      	frm.signman_id.value 		= obj.epid;
	      	frm.signman_nm.value 		= obj.cn;
	      	frm.signman_search_nm.value= '';
	      	frm.sign_dept_nm.value 	= obj.department;
	      	frm.signman_jikgup_nm.value = obj.title;
	      	
	      	$('#signman').html('');
	      	$('#signman').append(obj.cn+'/'+obj.title+'/'+obj.department);
		} else if(srch_user_target=="contract"){
	      	frm.cntrt_respman_id.value 			= obj.epid;
	      	frm.cntrt_respman_nm.value 			= obj.cn;
	        frm.cntrt_respman_search_nm.value	= '';
	      	frm.cntrt_respman_jikgup_nm.value 	= obj.title;
	      	frm.cntrt_resp_dept_nm.value 		= obj.department;
	      	frm.cntrt_resp_dept.value			= obj.departmentnumber;
	      	 
	      	 var options = { 
	  			    	url:"<c:url value='/clm/manage/consultation.do?method=getOrgnzCd' />",
	  			     	type:"post",
	  			    	dataType:"json",
	  			    	success:function(returnJson, returnMessage){
	  			    		$('#respman').html('');
	  			    	    $('#respman').append('&nbsp;&nbsp;'+ frm.cntrt_respman_nm.value +'/'+frm.cntrt_respman_jikgup_nm.value+'/'+frm.cntrt_resp_dept_nm.value);
	  			    	}
	      	       };
	      	    	
	      		$("#frm").ajaxSubmit(options);	

		   } else if(srch_user_target == "appr1"){      //사전품의발의자                                    
				     frm.bfhdcstn_mtnman_id.value 			= obj.epid;		//사전품의발의자 id     
				     frm.bfhdcstn_mtnman_nm.value 			= obj.cn;; 		//발의자 이름           
				     frm.bfhdcstn_mtnman_jikgup_nm.value 	= obj.title;  		//직급              
				     frm.bfhdcstn_mtn_dept_nm.value			= obj.department;	//부서              
				                                                                                    
				     $('#bfhdcstn_mtnman_jikgup_nm_span').html(userInfo.title);                     
				     $('#bfhdcstn_mtn_dept_nm_span').html(userInfo.department);                     
				                                                                                    
			   }else if(srch_user_target == "MTN"){      //사전품의발의자                                    
				     frm.bfhdcstn_mtnman_id.value 			= obj.epid;		//사전품의발의자 id     
				     frm.bfhdcstn_mtnman_nm.value 			= obj.cn;; 		//발의자 이름           
				     frm.bfhdcstn_mtnman_jikgup_nm.value 	= obj.title;  		//직급              
				     frm.bfhdcstn_mtn_dept_nm.value			= obj.department;	//부서              
				                                                                                    
				     $('#bfhdcstn_mtnman_jikgup_nm_span').html(userInfo.title);                     
				     $('#bfhdcstn_mtn_dept_nm_span').html(userInfo.department);                     
				                                                                                    
			   }else if(srch_user_target == "APBT"){     //사전품의승인자정	                               
				   frm.bfhdcstn_apbtman_id.value          = obj.epid;		//사전품의승인자정idf
			    	 frm.bfhdcstn_apbtman_nm.value          = obj.cn;; 		//발의자 이름       
			    	 frm.bfhdcstn_apbtman_jikgup_nm.value   = obj.title;  		//직급            
			    	 frm.bfhdcstn_apbt_dept_nm.value        = obj.department;	//부서          
			    	                                                                                
			    	 $('#bfhdcstn_apbtman_jikgup_nm_span').html(obj.title);                    
				     $('#bfhdcstn_apbt_dept_nm_span').html(obj.department);                               
				                                                                                    
			   } else if(srch_user_target == "appr") {		//검토의뢰시 결재자 ID      
			    	//eptitlenumber 직급코드                                   
			    	 listTbComCd(obj.eptitlenumber);                                           
			                                                                                      
			    	 frm.approvalman_id.value = obj.epid;                                      
					 frm.approvalman_nm.value = obj.cn;                                          
					 frm.approvalman_search_nm.value = obj.cn;                                   
					 frm.approvalman_dept_nm.value = obj.department;                             
					 $('#approvalman_dept_nm').html(obj.department);                             
					 frm.approvalman_jikgup_nm.value=obj.title;                                  
					 $('#approvalman_jikgup_nm').html(obj.title);			                          
					                                                                                  
					                                                                                  
			     } else{   	                                                
			    	alert("<spring:message code="clm.page.msg.manage.announce096" />");                 
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
        //alert($("input[name=arr_trgtman_id]").length);
        
        var items_str1 = $("input[name=arr_demnd_seqno]").map(function(){return this.value;}).get();
        var items_str2 = $("input[name=arr_trgtman_id]").map(function(){return this.value;}).get();
        var items_str3 = $("input[name=arr_trgtman_nm]").map(function(){return this.value;}).get();
        var items_str4 = $("input[name=arr_trgtman_jikgup_nm]").map(function(){return this.value;}).get();
        var items_str5 = $("input[name=arr_trgtman_dept_nm]").map(function(){return this.value;}).get();
        
        var items = items_str1+"!@#$"+ items_str2  +"!@#$"+  items_str3  +"!@#$"+  items_str4 +"!@#$"+items_str5;
        frm.chose_client.value = items;
                
        PopUpWindowOpen('', "530", "470", false,"PopUpWindow");
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
	 /************************************************************************
	 * 거래상대방 팝업 띄우기
	 */ 
	 function openNegoCustomer(){
		 var frm = document.frm;
		 
		 var customer = frm.cntrt_oppnt_nm.value;
		 
		PopUpWindowOpen('', 900, 600, false);
 		frm.target = "PopUpWindow";
 		frm.action = "/clm/draft/customerTest.do";
 		frm.method.value = "listCustomerTest"; 		
 		frm.customer_gb.value   = "O";
 		frm.submit();
	 }
	/**
	* 거래상대방 객체 리턴
	*/
	function returnCustomer(obj){
		chk++;	
		
		var dmstfrgn_gbn ="";
		var cntrt_oppnt_region_gbn = "";
		$('#cntrt_oppnt_cd').val(obj.customer_cd);
		
		<% if(langCd.equals("ko")){ %>
			$('#cntrt_oppnt_nm').val(obj.customer_nm1); //한국어 사용자가 등록할 경우
		<% }
		else{ %>
			$('#cntrt_oppnt_nm').val(obj.iv_nm1); //영어 사용자가 등록할 경우
		<% } %>
		if("KR" == obj.nation){
			dmstfrgn_gbn="H";
			cntrt_oppnt_region_gbn="C01801";
		}else{
			dmstfrgn_gbn="F";
			cntrt_oppnt_region_gbn="C01802";
		}
				
		$('input[name="dmstfrgn_gbn"]:radio:input[value="' + dmstfrgn_gbn + '"]').attr('checked', 'checked') ;
		$("#region_gbn").val(cntrt_oppnt_region_gbn);
		$('#cntrt_oppnt_rprsntman').val(obj.tax_no);		
		var cntrtOppntKind = "";
		var cntrtOppntCeo  = "";		
		
		document.getElementById("cntrtOppntChk").value = obj.index;	
		
		//계약명 return
		returnCntrtNm();
	}
	
	/************************************************************************
	*
	*/
	function returnCntrtNm(){
		
		document.getElementById("type_sel21").style.display = "none";
		
		var tmpNmAbbr ="";
		if(!$('#orgnz_nm_abbr').val()){			
			tmpNmAbbr = $('#req_dept_nm').val();
		}else{
			tmpNmAbbr = $('#orgnz_nm_abbr').val();
		}
		var tmpCntrtOppntNm ="";
		
		var dmstfrgn_gbn = $('input[name="dmstfrgn_gbn"]:radio:checked').val();
		
		//if("H" == $('#dmstfrgn_gbn').val()){
		if(dmstfrgn_gbn=="H") {
			tmpCntrtOppntNm = chkLength($('#cntrt_oppnt_nm').val(), '16'); //한국일경우 8자리까지 김재원 값 16으로 변경
		}else{
			tmpCntrtOppntNm = chkLength($('#cntrt_oppnt_nm').val(), '12'); ///해외일경우 12자리까지
		}
				
		//사업부명으로 변경 사업부명이 없으면 부서명 으로 -1129일 수정 - orgnz_nm_abbr
		var cntrtNm = tmpNmAbbr + "_" + tmpCntrtOppntNm + "_" + $('#biz_clsfcn_nm').val() + "_" + $('#depth_clsfcn_nm').val() + "_" + $('#cnclsnpurps_bigclsfcn_nm').val() + "_" + $('#cnclsnpurps_midclsfcn_nm').val();
		var en_cntrtNm = tmpNmAbbr + "_" + tmpCntrtOppntNm + "_" + $('#en_biz_clsfcn_nm').val() + "_" + $('#en_depth_clsfcn_nm').val() + "_" + $('#en_cnclsnpurps_bigclsfcn_nm').val() + "_" + $('#en_cnclsnpurps_midclsfcn_nm').val();
		$('#cntrt_nm').val(cntrtNm);
		$('#en_cntrt_nm').val(en_cntrtNm);
		$('#div_cntrt_nm').html("");
		$('#div_cntrt_nm').html(cntrtNm);
		
		changeSubCd("cntrt_trgt", "CONTRACTTYPE", $('#cnclsnpurps_midclsfcn').val());	      
	}
		
	//결재상신리턴결과
	function setApprovalYN(yn){
		var frm = document.frm;
		if(yn=="Y") {
			frm.action="<c:url value='/clm/manage/registration.do' />";
	    	frm.target="_self";
	    	frm.method.value="modifyRegistrationStatus";
	    	frm.submit();
		} else {
			frm.action="<c:url value='/clm/manage/registration.do' />";
	    	frm.target="_self";
	    	frm.method.value="listManageRegistration";
	    	frm.submit();
		}
	}  	
		 //미리보기팝업
		 function considerationPreview() {
			 alert("<spring:message code="clm.page.msg.manage.announce138" />");
			 var frm = document.frm;
				
			 PopUpWindowOpen('', "1024", "768", false);
			    
			 frm.action 		= "<c:url value='/clm/manage/consideration.do' />";
			 frm.method.value	= "forwardPreviewPop";
			 frm.target 		= "PopUpWindow";
			 frm.submit(); 
		 }
		 
		 function changetItem(biz){
			 $("#rel_type > option[value='C03201']").attr("selected","true");
			 if("T0101" == biz){
				 //alert("biz1 = " +biz);
				 $("#rel_defn").html("<option value=NDA selected>NDA</option><option value=MOU/LOI>MOU/LOI</option>"); 
			 } else {
				 //alert("biz2 = " +biz);
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
    
    /**
    * 지불계획추가
    */
    function newExecution() {
 
    	$('#notFoundList123').remove();
    	
    	var maxCount = $('#maxCount').val();
    	if(maxCount == ''){
    		maxCount = $('#executionTbody tr').length+1;
    	}else{
    		maxCount = Number(maxCount)+1;
    	}
    	$('#maxCount').val(maxCount);
    	
    	var html = "<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\">"
    		 +	"<td><input type=\'text\' name=\'exec_itm_arr\' id=\'exec_itm_arr\' class=\'text_full\' value=\'\' size=\'20\' maxLength=\'60\' required alt=\'<spring:message code='clm.page.msg.manage.exeItm' htmlEscape='true' />\'  /></td>"
             +	"<td><input type=\'text\' name=\'exec_plndday_arr\' id=\'exec_plndday_arr"+($('#maxCount').val())+"\' class=\'text_calendar02\' required readonly=\'readonly\' value=\'\' size=\'10\' alt=\'<spring:message code='clm.page.msg.manage.compResDate' htmlEscape='true' />\'  /></td>"
             +	"<td><input type=\'text\' name=\'exec_amt_arr\' id=\'exec_amt_arr\' class=\'text_full\' value=\'\' onkeyPress=\'olnyNum(this)\' required style=\'IME-MODE: disabled\' size=\'10\' maxLength=\'15\' alt=\'<spring:message code='clm.page.msg.manage.amount' htmlEscape='true' />\'  /></td>"
             +	"<td><input type=\'text\' name=\'exec_cont_arr\' id=\'exec_cont_arr\' class=\'text_full\' value=\'\' size=\'20\' maxLength=\'60\' alt=\'Description\'  /></td>"
             +	"<td><span class=\"ico_uncom\"><spring:message code='clm.page.msg.manage.noConfirm' /></span></td>"
             +	"<td><input type=\'checkbox\' name=\'exec_chk_arr\' id=\'exec_chk_arr\' value=-1 /><input type=\'hidden\' id=\'exec_seqno_arr\' name=\'exec_seqno_arr\' value=-1 ><input type=\'hidden\' id=\'exec_gbn_arr\' name=\'exec_gbn_arr\' value=\'C05501\' ></td>"
    	     + "</tr>"
             + "<script language='javascript'>"
             + "initCal(\'exec_plndday_arr"+($('#maxCount').val())+"\');"
             + "<"+"/script>";
    	$('#executionTbody').append(html);
    	$('#executionTbody tr:last td :input[name=exec_cont_arr]').focus();
    	
    }
    /**
    * 수금계획추가
    */
    function newExecution2() {
    	
    	$('#notFoundList2').remove();
    	
    	var maxCount = $('#maxCount2').val();
    	if(maxCount == ''){
    		maxCount = $('#executionTbody2 tr').length+1;
    	}else{
    		maxCount = Number(maxCount)+1;
    	}
    	$('#maxCount2').val(maxCount);
    	
    	var html = "<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\">"
    		 +	"<td><input type=\'text\' name=\'exec_itm_arr\' id=\'exec_itm_arr\' class=\'text_full\' value=\'\' size=\'20\' required maxLength=\'60\' alt=\'<spring:message code='clm.page.msg.manage.exeItm' htmlEscape='true' />\'  /></td>"
             +	"<td><input type=\'text\' name=\'exec_plndday_arr\' id=\'exec_plndday_arr2"+($('#maxCount2').val())+"\' class=\'text_calendar02\' required readonly=\'readonly\' value=\'\' size=\'10\' alt=\'<spring:message code='clm.page.msg.manage.compResDate' htmlEscape='true' />\'  /></td>"
             +	"<td><input type=\'text\' name=\'exec_amt_arr\' id=\'exec_amt_arr\' class=\'text_full\' value=\'\'  onkeyPress=\'olnyNum(this)\' required style=\'IME-MODE: disabled\' size=\'10\' maxLength=\'15\' alt=\'<spring:message code='clm.page.msg.manage.amount' htmlEscape='true' />\'  /></td>"
             +	"<td><input type=\'text\' name=\'exec_cont_arr\' id=\'exec_cont_arr\' class=\'text_full\' value=\'\' size=\'20\' maxLength=\'60\' required alt=\'Description\'  /></td>"
             +	"<td><span class=\"ico_uncom\"><spring:message code='clm.page.msg.manage.noConfirm' /></span></td>"
             +	"<td><input type=\'checkbox\' name=\'exec_chk_arr\' id=\'exec_chk_arr\' value=-1 /><input type=\'hidden\' id=\'exec_seqno_arr\' name=\'exec_seqno_arr\' value=-1 ><input type=\'hidden\' id=\'exec_gbn_arr\' name=\'exec_gbn_arr\' value=\'C05502\' ></td>"
    	     + "</tr>"
             + "<script language='javascript'>"
             + "initCal(\'exec_plndday_arr2"+($('#maxCount2').val())+"\');"
             + "<"+"/script>";
    	$('#executionTbody2').append(html);
    	$('#executionTbody2 tr:last td :input[name=exec_cont_arr]').focus();
    	
    }
    /**
    * 기타 이행계획추가
    */
    function newExecution3() {
    	
    	$('#notFoundList3').remove();
    	
    	var maxCount = $('#maxCount3').val();
    	if(maxCount == ''){
    		maxCount = $('#executionTbody3 tr').length+1;
    	}else{
    		maxCount = Number(maxCount)+1;
    	}
    	$('#maxCount3').val(maxCount);
    	
    	
    	var html = "<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\">"
    		 +	"<td><input type=\'text\' name=\'exec_itm_arr\' id=\'exec_itm_arr\' class=\'text_full\' value=\'\' required size=\'20\' maxLength=\'60\' alt=\'<spring:message code='clm.page.msg.manage.exeItm' htmlEscape='true' />\'  /></td>"
             +	"<td><input type=\'text\' name=\'exec_plndday_arr\' id=\'exec_plndday_arr3"+($('#maxCount3').val())+"\' required class=\'text_calendar02\' readonly=\'readonly\' value=\'\' size=\'10\' alt=\'<spring:message code='clm.page.msg.manage.compResDate' htmlEscape='true' />\'  /></td>"
             +	"<td><input type=\'text\' name=\'exec_cont_arr\' id=\'exec_cont_arr\' class=\'text_full\' value=\'\' required size=\'40\' maxLength=\'60\' alt=\'Description\'  /></td>"
             +	"<td><span class=\"ico_uncom\"><spring:message code='clm.page.msg.manage.noConfirm' /></span></td>"
             +	"<td><input type=\'checkbox\' name=\'exec_chk_arr\' id=\'exec_chk_arr\' value=-1 /><input type=\'hidden\' id=\'exec_seqno_arr\' name=\'exec_seqno_arr\' value=-1 ><input type=\'hidden\' id=\'exec_gbn_arr\' name=\'exec_gbn_arr\' value=\'C05503\' ></td>"
    	     + "<input type=\'hidden\' id=\'exec_amt_arr\' name=\'exec_amt_arr\' value=0 ></tr>"
             + "<script language='javascript'>"
             + "initCal(\'exec_plndday_arr3"+($('#maxCount3').val())+"\');"
             + "<"+"/script>";
    	$('#executionTbody3').append(html);
    	$('#executionTbody3 tr:last td :input[name=exec_cont_arr]').focus();
    	
    }
    /**
    * 이행 삭제
    */
    function deleteExecution() {
    	
    	var exec_chk_arr = document.getElementsByName("exec_chk_arr");
     	var j=0;
     	for(var i=0; i<exec_chk_arr.length; i++){
    		if(exec_chk_arr[i].checked == true){
    			j++;
    		}
    	}
    	
    	if(j < 1){
    		alert("<spring:message code='secfw.msg.ask.noSelect' />");
    	} else {
    		$('input[name=exec_chk_arr]:checked').each(function(){
    			$(this).parent().parent().remove();
    		});
    		
    	}
    }
  //Val값만큼의 이전날짜를 가져온다.
	 function getBeforeDay(val) {

	  	var frm = document.frm;
	 	var endday = frm.cntrtperiod_endday.value;
	 	
	 	
	 	if(frm.cntrtperiod_endday.value == ""){
	 		alert("<spring:message code='las.msg.alert.postConcReg.ContPeriod'/>");
	 		return;
	 	}
	 }
  
	 /**
	  * 주어진 Time 과 y년 m월 d일 h시 차이나는 Time을 리턴
	  * ex) var time = form.time.value; //'20000101000'
	  *     alert(shiftTime(time,0,0,-100,0));
	  *     => 2000/01/01 00:00 으로부터 100일 전 Time
	  */
	 function shiftTime(time,y,m,d) { //moveTime(time,y,m,d,h)
	     var date = toTimeObject(time);
	 
	     date.setFullYear(date.getFullYear() + y); //y년을 더함
	     date.setMonth(date.getMonth() + m);       //m월을 더함
	     date.setDate(date.getDate() + d);         //d일을 더함

	     return toTimeString(date);
	 }

	  /**
	   * Time 스트링을 자바스크립트 Date 객체로 변환
	   * parameter time: Time 형식의 String
	   */
	  function toTimeObject(time) { //parseTime(time)
	      var year  = time.substr(0,4);
	      var month = time.substr(4,2) - 1; // 1월=0,12월=11
	      var day   = time.substr(6,2);
	      
	      return new Date(year,month,day);
	  }

	  /**
	   * 자바스크립트 Date 객체를 Time 스트링으로 변환
	   * parameter date: JavaScript Date Object
	   */
	  function toTimeString(date) { //formatTime(date)
	      var year  = date.getFullYear();
	      var month = date.getMonth() + 1; // 1월=0,12월=11이므로 1 더함
	      var day   = date.getDate();
	     
	      if (("" + month).length == 1) { month = "0" + month; }
	      if (("" + day).length   == 1) { day   = "0" + day;   }
	     
	      return ("" + year + "-" + month + "-" + day);
	  }
	// 체결정보에 필요한 스크립트
	function setSealMethod(val) {
	
	var frm = document.frm;
	if(val != "C02102") { //직인(사용인감,법인인감날인)

		$('#seal_ffmtman_id').val('');
		$('#seal_ffmtman_nm').val('');
		$('#seal_ffmt_dept_cd').val('');
		$('#seal_ffmt_dept_nm').val('');
		$('#seal_ffmtman_jikgup_nm').val('');
		$('#seal_ffmtman_search_nm').val('');
		$('#ffmtman').html('');
		
		$('#signman_id').val('');
		$('#signman_nm').val('');
		$('#signman_jikgup_nm').val('');
		$('#sign_dept_nm').val('');
		$('#signman_search_nm').val('');
		$('#signman').html('');
		
		$('input[name=seal_ffmtman_search_nm]').removeAttr("disabled");
		$('#seal_ffmtman_search_img').attr("style","display:").prev().css("border-right","");
		

	}else { //서명
		//$('#ss0').attr("colspan", "1");
		$('#ss1').attr("style", "visibility:visible;border-left:1px solid #CADBE2");
		$('#ss2').attr("style", "visibility:visible;border-left:1px solid #CADBE2");
		
		$('#seal_ffmtman_id').val('');
		$('#seal_ffmtman_nm').val('');
		$('#seal_ffmt_dept_cd').val('');
		$('#seal_ffmt_dept_nm').val('');
		$('#seal_ffmtman_jikgup_nm').val('');
		$('#seal_ffmtman_search_nm').val('');
		$('#ffmtman').html('');
		
		
		$('#signman_id').val('');
		$('#signman_nm').val('');
		$('#signman_jikgup_nm').val('');
		$('#sign_dept_nm').val('');
		$('#signman_search_nm').val('');
		$('#signman').html('');
		
		$('input[name=seal_ffmtman_search_nm]').removeAttr("disabled");
		$('#seal_ffmtman_search_img').attr("style","display:").prev().css("border-right","");
	}
}
	/*
	 * 날인자 검색팝업 
	 */
	function searchSealPerson() {
		var frm 		= document.frm;
		
		var top_xp  = window.screenLeft;
	    var top_yp  = window.screenTop;
		window.open("", "PopUpSealPerson", "left="+top_xp+ ", top="+top_yp+", width=800, height=450, menubar=no, directories=no, resizeble=no, status=no, scrollbars=no");
		
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
	
	// 추진 목적 및 배경
	function purpClean(){
		var frm = document.frm;
		if(frm.body_mime1.value == "<spring:message code='clm.page.msg.registration.reason.message' />"){
			frm.body_mime1.value = "";
		}
	}
	
	// TOP 30 팝업
	function openTop30Customer(){
		   PopUpWindowOpen2("/clm/draft/customerTest.do?method=listTop30Customer", 500, 540, false, "Top30Customer");
	}
</script>
</head>
<body onLoad="init();">

<c:choose>
	<c:when test="${lomRq.biz_clsfcn eq null && lomRq.depth_clsfcn eq null}">
		<div class='type_sel2' id="type_sel21" name="type_sel21"></div>
	</c:when>
	<c:otherwise>
		<div class='type_sel2' id="type_sel21" name="type_sel21" style="display:none"></div>
	</c:otherwise>
</c:choose>

<div id="wrap">
	<!-- container -->
	<div id="container">
		
		<!-- location -->
		<div class="location">
			<img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/>
			
		</div>
		<!-- //location -->
		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.msg.manage.aftConclRegWrite" /></h3>
		</div>
		<!-- //title-->
		
		<!-- content -->
		<div id="content">	
		<!-- content_in -->
		<div id="content_in">	
			<form:form name="frm" id="frm" method="post" autocomplete="off">
			<input type="hidden" name="method" id="method" value="">
			<input type="hidden" name="menu_id" id="menu_id" value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="curPage" id="curPage" value="<c:out value='${command.curPage}'/>" />
			<input type="hidden" name="status" id="status" value="<c:out value='${lomRq.status}'/>" />
			<input type="hidden" name="submit_status" id="submit_status" />
			<!-- 나모 웹 에디터 관련 -->
			<!--  //입력일 때-->				
			<input type="hidden" name="srch_user_cntnt_target" value=""/>
			<input type="hidden" name="srch_user_cntnt_type" value=""/>
			<input type="hidden" name="srch_user_cntnt" value=""/>
			
		    <!-- 첨부파일정보 -->
			<input type="hidden" name="fileInfos3" id="fileInfos3"  value="4" /> <!-- 저장될 파일 정보 -->	
			<input type="hidden" name="fileInfos4" id="fileInfos4"  value="5" /> <!-- 저장될 파일 정보 -->	
			<input type="hidden" name="fileInfos5" id="fileInfos5"  value="6" /> <!-- 저장될 파일 정보 -->
			<input type="hidden" name="fileInfos6" id="fileInfos6"  value="" /> <!-- 저장될 파일 정보 -->		
			
			<input type="hidden" name="fileInfoName"    value="" /> <!-- 첨부파일 정보가 리턴될 객체명 -->
		    <input type="hidden" name="fileFrameName"    value="" /> <!-- 첨부파일 화면 iFrame 명 -->
				
			<input type="hidden" name="file_bigclsfcn"  value="" /> <!-- 대분류 -->	
			<input type="hidden" name="file_midclsfcn"  value="" /> <!-- 중분류 -->	
			<input type="hidden" name="file_smlclsfcn"  value="" /> <!-- 소분류 -->	
			<input type="hidden" name="ref_key"     value="" /> <!-- 키값 -->				
			<input type="hidden" name="view_gbn"    value="upload" /> <!-- 첨부파일 화면 -->
			<input type="hidden" name="multiYn" value="" /><!-- 멀티 -->
			<input type="hidden" name="preAllowFileList"  value="" /> 		<!-- 별도확장자의 첨부파일만 업로드 할 때 -->
				
			<input type="hidden" name="customer_gb" value="" />
		   <!-- 계약추가 증가데이타 -->	    
		    <input type="hidden" name="tab_cnt" id="tab_cnt" value="<c:out value='${lomRq.total_cnt}'/>" /><!-- Tab 계약 증가 값 -->    
		   <!-- tmp_cntrt_oppnt_cd 거래 상대방  -->
		   <input type="hidden" name="tmp_cntrt_oppnt_cd" id="tmp_cntrt_oppnt_cd"/>
		   <input type="hidden" name="tmp_region_gbn" id="tmp_region_gbn"/>
		   <input type="hidden" name="tmp_cntrt_oppnt_nm" id="tmp_cntrt_oppnt_nm"/>
		   <input type="hidden" name="tmp_cntrt_oppnt_rprsntman" id="tmp_cntrt_oppnt_rprsntman"/>
		   <input type="hidden" name="tmp_cntrt_oppnt_type" id="tmp_cntrt_oppnt_type"/>
		   <input type="hidden" name="tmp_cntrt_oppnt_respman" id="tmp_cntrt_oppnt_respman"/>
		   <input type="hidden" name="tmp_cntrt_oppnt_telno" id="tmp_cntrt_oppnt_telno"/>
		   <input type="hidden" name="tmp_cntrt_oppnt_email" id="tmp_cntrt_oppnt_email"/>
		   
		   <!-- 계약 의뢰 에 필요한 Master 정보-->
		   <input type="hidden" name="req_biz_clsfcn" id="req_biz_clsfcn"/>
		   <input type="hidden" name="req_depth_clsfcn" id="req_depth_clsfcn"/>
		   <input type="hidden" name="req_cnclsnpurps_midclsfcn" id="req_cnclsnpurps_midclsfcn"/>
		   <input type="hidden" name="req_cnclsnpurps_bigclsfcn" id="req_cnclsnpurps_bigclsfcn"/>
		   <input type="hidden" name="seal_mthd" id="seal_mthd" />
		   <!-- 재의뢰 OR 최종본 의뢰시 사용  -->
		   <input type="hidden" name="prev_cnsdreq_id" id ="prev_cnsdreq_id" value="<c:out value='${lomRq.prev_cnsdreq_id}'/>" />	   
		   <input type="hidden" name="prev_vc_respman_apnt_yn" id ="prev_vc_respman_apnt_yn" value="<c:out value='${lomRq.vc_respman_apnt_yn}'/>" />
		   <input type="hidden" name="mn_cnsd_dept" id ="mn_cnsd_dept" value="<c:out value='${lomRq.mn_cnsd_dept}'/>" />
		   <input type="hidden" name="vc_cnsd_dept" id ="vc_cnsd_dept" value="<c:out value='${lomRq.vc_cnsd_dept}'/>" />
		   	   
		   <input type="hidden" name="plndbn_req_yn" id ="plndbn_req_yn" value="<c:out value='${lomRq.plndbn_req_yn}'/>" />
		   <% //}%> 
		   <!--input type="hidden" name="dmstfrgn_gbn" id="dmstfrgn_gbn" value="<c:out value='${lomRq.dmstfrgn_gbn}'/>"-->
		   <input type="hidden" name="C03204_pcid_cp" id="C03204_pcid_cp"/>	   
		   <!-- 관련자 데이타 설정  -->
		   <input type="hidden" name="chose_client" id="chose_client" />
		
		   <!-- 기존 데이터가 있을 경우에 데이터 삭제해주기 위해서. -->
		   <input type="hidden" name="cntrtOppntChk" id="cntrtOppntChk" value="" />
	
		   <!-- 임직원조회 팝업 시 리턴값 사전품의발의자인지 사전품의승인자정보 인지 구분하기 위해 -->
		   <input type="hidden" name="searchEmployeeGb" id="searchEmployeeGb" value="" />
		   
		   <!-- 계약단가요약에 데이터가 있는지 체크하기 위해 -->
		   <input type="hidden" name="cntrt_untprc_chk" id="cntrt_untprc_chk" value="<c:out value='${lomRq.cntrt_untprc_expl}'/>" />
		    
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
		   <input type="hidden" name="mod_cntrt_id" id ="mod_cntrt_id" value="<c:out value='${lomRq.cntrt_id}'/>" />
		   <input type="hidden" name="req_reg_id" id ="req_reg_id" value="<c:out value='${lomRq.req_reg_id}'/>" />
		   <input type="hidden" name="fileYn" id="fileYn" value="N" />
		   <input type="hidden" name="orgnz_nm_abbr" id="orgnz_nm_abbr" value="<c:out value='${command.abbr_comp_nm}'/>" />
	
		   <!-- SC0101:날인담당자  /SC0102:증명서류발급담당자-->
		   <input type="hidden" name="srch_type_gbn" id="srch_type_gbn" value="SC0101" /> 
		   
	        <!-- 기존 Consideration_inner.jsp 에 있던 hidden 값 -->
	        <input type="hidden" name="cntrt_id" id="cntrt_id" value="<c:out value='${lomRq.cntrt_id}'/>">
	        <input type="hidden" name="file_validate" id="file_validate" value="<c:out value='${command.file_validate}'/>">
			
			<!-- 계약 유형 수정 가능여부를 체크하기 위한 변수 -->
        	<input type="hidden" name="tmp_cnsd_level" id="tmp_cnsd_level" value="<c:out value='${lomRq.cnsd_level}'/>" />
        	<input type="hidden" name="tmp_prgrs_status" id="tmp_prgrs_status" value="<c:out value='${lomRq.prgrs_status}'/>" />	
			
			
			<div class="content-step t_titBtn">
				<ol>
					<li><img src="<%=IMAGE %>/common/step01.gif"	 alt="<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" />" /></li>
					<li><img src="<%=IMAGE %>/common/step02.gif"     alt="<spring:message code="clm.page.msg.manage.step02" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step02" htmlEscape="true" />" /></li>
					<li class='step_on'>
						<img src="<%=IMAGE %>/common/step03_on.gif"  alt="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" />
						<img src="<%=IMAGE %>/common/step_arr01.gif" alt="Next" title="Next" />
						<div class='step_link'><a href="javascript:open_window('win', '../../step03<%=langCd.equals("ko")?"":"_"+langCd %>.html', 0, 0, 696, 588, 0, 0, 0, 0, 0)" ><img src="<%=IMAGE %>/common/step_q.gif" alt="<spring:message code="clm.page.msg.manage.procStep"/>" title="<spring:message code="clm.page.msg.manage.procStep"/>" /></a></div>
					</li>
					<li><img src="<%=IMAGE %>/common/step04.gif"     alt="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" /></li>
					<li><img src="<%=IMAGE %>/common/step05.gif"     alt="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" /></li>
				</ol>
			</div>
			<!-- title -->
            <div class="title_basic">
				<h4><spring:message code="clm.page.msg.manage.contInfo" /></h4>
			</div>
			<!-- button -->
			<div class="btnwrap mt_22">
				<span class="btn_all_w" onclick="insertConsideration('req');"><span class="sangsin"></span><a><spring:message code="clm.page.button.contract.cosultationapproval" /></a></span>	
				<span class="btn_all_w" onclick="insertConsideration('save');"><span class="tsave"></span><a><spring:message code="clm.page.msg.manage.tmpSave" /></a></span>				
			    <span class="btn_all_w" onclick="forwardConsideration('list');"><span class="list"></span><a><spring:message code="clm.page.msg.manage.list" /></a></span>
			</div>
			<!-- //button -->
			
			<!-- toptable -->				
			<!-- //title -->
			<div class='title_basic3'><spring:message code="clm.page.msg.manage.bscInf" /></div>
			<table class="table-style01">
	            <colgroup>
	                <col width="12%" />
	                <col width="22%" />
	                <col width="12%" />
                    <col width="21%" />
                    <col width="12%" />
                    <col width="21%" />
	            </colgroup>
	            <tr>
	                <th><spring:message code="clm.page.msg.manage.reqName" /><span class='astro'>*</span></th>
	                <td colspan="5"><input type="text" name="req_title" id="req_title" value='<c:out value="${lomRq.req_title}" />' alt="<spring:message code="clm.page.msg.manage.reqName" htmlEscape="true" />" class="text_full" maxlength="80" /></td>
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
                        <input type="hidden" name="reqman_id" id="reqman_id" value="<c:out value="${lomRq.reqman_id}" />" />
						<input type="hidden" name="reqman_nm" id="reqman_nm"  value="<c:out value="${lomRq.reqman_nm}" />" />
						<input type="hidden" name="reqman_jikgup_nm" id="reqman_jikgup_nm" value="<c:out value="${lomRq.reqman_jikgup_nm}" />" />
						<input type="hidden" name="req_dept_nm" id="req_dept_nm" value="<c:out value="${lomRq.req_dept_nm}" />" />
						<input type="hidden" name="req_dt" alt="<spring:message code="clm.page.msg.manage.requDate" htmlEscape="true" />" value="<c:out value="${lomRq.req_dt}" />" />
                    </td>
                    <th><spring:message code="clm.page.msg.manage.chrgPerson" /><span class='astro'>*</span></th>
                    <td colspan="3">
                        <c:out value="${lomRq.reqman_nm}" />/
                        <c:out value="${lomRq.reqman_jikgup_nm}" />/
                        <c:out value="${lomRq.req_dept_nm}" />
                    </td>
                </tr>
                <!--  brice add 11/09 add cc -->
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
	                <th><spring:message code="clm.page.msg.common.otherParty" /><span class='astro'>*</span></th>
                        <input type="hidden" name="cntrt_oppnt_cd" id="cntrt_oppnt_cd" required alt="<spring:message code="clm.page.msg.manage.othPartyCode" htmlEscape="true" />" value="<c:out value='${lomRq.cntrt_oppnt_cd}'/>">
                        <input type="hidden" name="region_gbn" id="region_gbn" value="<c:out value='${lomRq.region_gbn}'/>"><!-- javascript:customerPop('O');">< -->  
	                <td>                                          
<%  if("1".equals(lomRq.get("total_cnt"))){ %>
	                    <input type="text" name="cntrt_oppnt_nm" id="cntrt_oppnt_nm" required alt="<spring:message code="clm.page.msg.manage.othPartyName" htmlEscape="true" />" value="<c:out value='${lomRq.cntrt_oppnt_nm}'/>" readonly="readonly" class="text_search" style="width:80%"><img src="<%=IMAGE %>/icon/ico_search.gif" onclick="javascript:openNegoCustomer('O');" class="cp" alt="search" />
<%  }else{%>  
						<input type="text" name="cntrt_oppnt_nm" id="cntrt_oppnt_nm" required alt="<spring:message code="clm.page.msg.manage.othPartyName" htmlEscape="true" />" value="<c:out value='${lomRq.cntrt_oppnt_nm}'/>" readonly="readonly" class="text_full" style="width:80%">
<%  }%>             
					</td>
	                <th><spring:message code="clm.page.field.customer.registerNo" /></th>
	                <td><input type="text" name="cntrt_oppnt_rprsntman" id="cntrt_oppnt_rprsntman" required alt="<spring:message code="clm.page.field.customer.registerNo" htmlEscape="true" />" value="<c:out value='${lomRq.cntrt_oppnt_rprsntman}'/>" maxlength="60" class="text_full" style="width:80%"></td>
	                <th><spring:message code="clm.page.field.customer.contractRequired" /><span class='astro'>*</span></th>
                    <td>
                        <select name="cntrt_oppnt_type" id="cntrt_oppnt_type">
                            <c:out value="${combo.cntrtOppntType }" escapeXml="false"/>
                        </select>
                    </td>
	             </tr>
	             
	            <tr>
                    <th><spring:message code="clm.page.msg.manage.contType" /></th>
                    <td colspan="5">
	                    <input type="hidden" name="biz_clsfcn" id="biz_clsfcn" value="<c:out value='${lomRq.biz_clsfcn}'/>" />
	                    <input type="hidden" name="depth_clsfcn" id="depth_clsfcn" value="<c:out value='${lomRq.depth_clsfcn}'/>" />
	                    <input type="hidden" name="cnclsnpurps_midclsfcn" id="cnclsnpurps_midclsfcn" value="<c:out value='${lomRq.cnclsnpurps_midclsfcn}'/>"  />
	                    <input type="hidden" name="cnclsnpurps_bigclsfcn" id="cnclsnpurps_bigclsfcn" value="<c:out value='${lomRq.cnclsnpurps_bigclsfcn}'/>" /> <!-- 이거 -->         
	                    
	                    <input type="hidden" name="en_biz_clsfcn_nm" id="en_biz_clsfcn_nm" value="<c:out value='${lomRq.biz_clsfcn}'/>" />
	                    <input type="hidden" name="en_depth_clsfcn_nm" id="en_depth_clsfcn_nm" value="<c:out value='${lomRq.depth_clsfcn}'/>" />
	                    <input type="hidden" name="en_cnclsnpurps_midclsfcn_nm" id="en_cnclsnpurps_midclsfcn_nm" value="<c:out value='${lomRq.cnclsnpurps_midclsfcn}'/>"  />
	                    <input type="hidden" name="en_cnclsnpurps_bigclsfcn_nm" id="en_cnclsnpurps_bigclsfcn_nm" value="<c:out value='${lomRq.cnclsnpurps_bigclsfcn}'/>" />         
	                    
	                    <input readOnly type="text" name="biz_clsfcn_nm" id="biz_clsfcn_nm" required alt="<spring:message code="clm.page.msg.manage.contType" htmlEscape="true" />" value="<c:out value='${lomRq.biz_clsfcn_nm}'/>" class="text_full" style="width:30%" />
	                    <input readOnly type="text" name="depth_clsfcn_nm" id="depth_clsfcn_nm" required alt="<spring:message code="clm.page.msg.manage.contType" htmlEscape="true" />" value="<c:out value='${lomRq.depth_clsfcn_nm}'/>" class="text_full" style="width:20%" />
	                    <input readOnly type="text" name="cnclsnpurps_bigclsfcn_nm" id="cnclsnpurps_bigclsfcn_nm" required alt="<spring:message code="clm.page.msg.manage.contType" htmlEscape="true" />" value="<c:out value='${lomRq.cnclsnpurps_bigclsfcn_nm}'/>" class="text_full" style="width:20%" />
	                    <input readOnly type="text" name="cnclsnpurps_midclsfcn_nm" id="cnclsnpurps_midclsfcn_nm" required alt="<spring:message code="clm.page.msg.manage.contType" htmlEscape="true" />" value="<c:out value='${lomRq.cnclsnpurps_midclsfcn_nm}'/>" class="text_full" style="width:20%" />
	                    <a href="javascript:re_openChooseContractType();"><img src="<%=IMAGE%>/icon/ico_search_g.gif" /></a>
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
                    <td colspan="3"><input type="text" name="cntrt_trgt_det" id="cntrt_trgt_det" value="<c:out value='${lomRq.cntrt_trgt_det}'/>" maxlength="166" class="text_full" /><!-- 500 --></td>
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
                        <input type="text" name="cntrtperiod_startday" id="cntrtperiod_startday" required alt="<spring:message code="clm.page.msg.manage.contPerStrtDt" htmlEscape="true" />" value="<c:out value='${lomRq.cntrtperiod_startday}'/>" class="text_calendar02"/> ~ 
                        <input type="text" name="cntrtperiod_endday" id="cntrtperiod_endday" required alt="<spring:message code="clm.page.msg.manage.contPerEndDt" htmlEscape="true" />" value="<c:out value='${lomRq.cntrtperiod_endday}'/>" class="text_calendar02" />
                    </td>
                    <th><spring:message code="clm.page.msg.manage.sendRcvDiv" /><span class='astro'>*</span></th>
                    <td>
                        <select name="payment_gbn" id="payment_gbn" class="all" style="width:150px" onChange="paymentGbnChange();">
                            <c:out value="${combo.paymentGbn}" escapeXml="false"/>
                        </select>
                    </td>
                </tr>
	            <tr>
                    <th><spring:message code="clm.page.msg.manage.contAmt" /></th>
                    <td colspan="3">
                        <input type="text" name="cntrt_amt" id="cntrt_amt" value="<c:out value='${lomRq.cntrt_amt}' />" onclick="javascript:paymentGbnChange2();" onkeyPress='olnyNum(this)' style="width: 150px; text-align: right; margin-right: 1px; IME-MODE: disabled;" class="text_full" maxlength="18" />
                        <input type="checkbox" name="cntrt_untprc" id="cntrt_untprc" value="Y" onClick="clickCntrtUntprc();" /><label for="" > <spring:message code="clm.page.msg.manage.conclSingleAmt" /></label>
                    </td>
                    <th><spring:message code="clm.page.msg.manage.curr" /></th>
                    <td>
                        <select name="crrncy_unit" id="crrncy_unit">
                            <c:out value="${combo.crrncyUnit}" escapeXml="false"/>
                        </select>
                    </td>
                </tr>
                <tr id="trCntrtUntprc"><!-- 단가내역 요약 -->
                    <th><spring:message code="clm.page.msg.manage.singleAmtSum" /><img src="/images/las/ko/common/step_q.gif" alt="info" title="DOC, DOCX, GUL, PDF, OCX, XLS, XLSX, PPT, PPTX,&#13;JPG, HWP, TIF, RTF, ZIP, PTX, TML, TXT, LSX, EML, BMP, GIF, MSG, ALZ, DOT &#13;&#13;<spring:message code='las.msg.com.fileFilter'/>" /></th>
					<td colspan="5">
						<textarea name="cntrt_untprc_expl" id="cntrt_untprc_expl" cols="30" rows="3" onkeyup="f_chk_byte(this,300)" class="text_area_full"><c:out value='${lomRq.cntrt_untprc_expl}'/></textarea>
						<div class='ico_maxsize fR'>Max Size : <span>10MB</span></div>
						<iframe src="<c:url value='/las/blank.do' />" id="fileList3" name="fileList3" frameborder="0" class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>
					</td>
                </tr>
                <tr>
                    <th><spring:message code="clm.page.msg.manage.bg" /></th>
                    <td colspan="5">                                         
                    <input type="hidden" name="pshdbkgrnd_purps" id="pshdbkgrnd_purps" value="<c:out value='${lomRq.pshdbkgrnd_purps}'/>" />

					<c:choose>
						<c:when test="${lomRq.pshdbkgrnd_purps != null}">
							<span id="curByteExpl_body_mine">0</span>/<spring:message code="clm.page.msg.manage.stringLen2000" /><br>
							<textarea name="body_mime1" id="body_mime1" rows="10" cols="10" onkeyup="frmChkLenLang(this,2000,'curByteExpl_body_mine','<%=langCd%>')" class="text_area_full" ><c:out value='${lomRq.pshdbkgrnd_purps}'/></textarea>
						</c:when>
						<c:otherwise>
							<span id="curByteExpl_body_mine">0</span>/<spring:message code="clm.page.msg.manage.stringLen2000" /><br>
							<textarea name="body_mime1" id="body_mime1" rows="10" cols="10" onkeyup="frmChkLenLang(this,2000,'curByteExpl_body_mine','<%=langCd%>')" class="text_area_full" onclick="javascript:purpClean()"><spring:message code='clm.page.msg.registration.reason.message' /></textarea>
						</c:otherwise>
					</c:choose>
                    </td>
                </tr>
                <tr id="trSpecialAttr">
                    <th><spring:message code="clm.page.msg.manage.etcMain" /></th>
                    <td colspan="5">
                    	<span id="curByteExpl_body_mine2">0</span>/<spring:message code="clm.page.msg.manage.stringLen800" /><br>
						<textarea name="etc_main_cont" id="etc_main_cont" alt="<spring:message code="clm.page.msg.manage.etcMain" htmlEscape="true" />" cols="30" rows="4" onkeyup="frmChkLenLang(this,800,'curByteExpl_body_mine2','<%=langCd%>')" class="text_area_full" maxLength="800"><c:out value='${lomRq.etc_main_cont}'/></textarea>
                    </td>
                </tr>         
	        </table>
	        
	        <div class='title_basic3'><spring:message code="clm.page.msg.manage.preApprInf" /></div>
	        
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
                        <input type="hidden" name="bfhdcstn_mtnman_id" value="<c:out value='${lomRq.bfhdcstn_mtnman_id}'/>" />
                        <input type="hidden" name="bfhdcstn_mtn_dept_nm" value="<c:out value='${lomRq.bfhdcstn_mtn_dept_nm}'/>" />
                        <input type="hidden" name="bfhdcstn_mtnman_jikgup_nm" value="<c:out value='${lomRq.bfhdcstn_mtnman_jikgup_nm}'/>" />
                    <td>
                        <input type="text" name="bfhdcstn_mtnman_nm" value="<c:out value='${lomRq.bfhdcstn_mtnman_nm}'/>" style="width:110px" class="text_search" onKeyPress="if(event.keyCode==13) {searchEmployee2('MTN');return false;}" /><img src="<%=IMAGE %>/icon/ico_search.gif" class="cp" alt="search" onclick="searchEmployee2('MTN')" style="cursor:pointer"/>
                        <span id="bfhdcstn_mtnman_jikgup_nm_span"><c:out value='${lomRq.bfhdcstn_mtnman_jikgup_nm}'/></span>
                        <span id="bfhdcstn_mtn_dept_nm_span"><c:out value='${lomRq.bfhdcstn_mtn_dept_nm}'/></span>
                    </td>
                    <th><spring:message code="clm.page.msg.manage.apprPer" /><span class='astro'>*</span></th>
                        <input type="hidden" name="bfhdcstn_apbtman_id" id="bfhdcstn_apbtman_id"  value="<c:out value='${lomRq.bfhdcstn_apbtman_id}'/>" />
                    <td>
                        <input type="text" name="bfhdcstn_apbtman_nm" id="bfhdcstn_apbtman_nm" value="<c:out value='${lomRq.bfhdcstn_apbtman_nm}'/>" style="width:110px" class="text_search" onKeyPress="if(event.keyCode==13) {searchEmployee2('APBT'); return false;}"/><img src="<%=IMAGE %>/icon/ico_search.gif" class="cp" alt="search" onclick="searchEmployee2('APBT')" style="cursor:pointer"/>
                        <input type="hidden" name="bfhdcstn_apbtman_jikgup_nm" id="bfhdcstn_apbtman_jikgup_nm" value="<c:out value='${lomRq.bfhdcstn_apbtman_jikgup_nm}'/>" />
                        <input type="hidden" name="bfhdcstn_apbt_dept_nm" id="bfhdcstn_apbt_dept_nm" value="<c:out value='${lomRq.bfhdcstn_apbt_dept_nm}'/>" />
                        <span id="bfhdcstn_apbtman_jikgup_nm_span"><c:out value='${lomRq.bfhdcstn_apbtman_jikgup_nm}'/></span>
                        <span id="bfhdcstn_apbt_dept_nm_span"><c:out value='${lomRq.bfhdcstn_apbt_dept_nm}'/></span>
                    </td>
                </tr>
                
                <tr>
					<th><spring:message code="clm.page.msg.manage.attachData" /><img src="/images/las/ko/common/step_q.gif" alt="info" title="DOC, DOCX, GUL, PDF, OCX, XLS, XLSX, PPT, PPTX,&#13;JPG, HWP, TIF, RTF, ZIP, PTX, TML, TXT, LSX, EML, BMP, GIF, MSG, ALZ, DOT &#13;&#13;<spring:message code='las.msg.com.fileFilter'/>" /></th>
					<td colspan="3">
						<spring:message code="clm.page.msg.manage.announce097" />
						<div class='ico_maxsize fR'>Max Size : <span>10MB</span></div>
						<iframe src="<c:url value='/las/blank.do' />" id="fileList4" name="fileList4" frameborder="0" class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>
					</td>    
                </tr>
            </table>
            
            <div class='title_basic3'><spring:message code="clm.page.msg.manage.relContInf" /></div>
            <table cellspacing="0" cellpadding="0" border="0" class="table-style01" id="trRelationContract_tb">
                <colgroup>
                    <col width="12%" />
                    <col width="50%" />
                    <col width="13%" />
                    <col width="25%"/>
                </colgroup>
                <tr>
                    <th class="tC"><spring:message code="clm.page.msg.manage.relation" /></th>
                    <th class="tC"><spring:message code="clm.page.msg.manage.relCont" /></th>
                    <th class="tC"><spring:message code="clm.page.msg.manage.define" /></th>
                    <th class="tC"><spring:message code="clm.page.msg.manage.relDetail" /></th>                   
                </tr>               
                <tr id="trRelationContract">
                     <td class="tC">
                        <select name="rel_type" id="rel_type" onChange="reltypeChange();" style="width:120px;">
                            <c:out value="${combo.relType}" escapeXml="false"/>
                        </select>
                        </td>
                     <input type="hidden" name="parent_cntrt_id" id="parent_cntrt_id">
                     <td class="tC">
                     	<input type="text" name="parent_cntrt_name" id="parent_cntrt_name" class="text_search" style="width:80%" readonly="readonly"><img src="<%=IMAGE %>/icon/ico_search.gif" onclick="javascript:popupListContract('C03201');" class="cp" alt="search" /></td>
                     <td class="tC"><select name="rel_defn" id="rel_defn" ></select></td>
                     <td class="tC"><input type="text" name="expl" id="expl" class="text_full" style="width:60%"  maxlength="333">
                     <a href="javascript:actionRelationContract('insert','','');"><img src="<%=IMAGE %>/btn/btn_regist.gif"></a></td>
                </tr>
               <c:out value="${contRc}" escapeXml="false"/>               
            </table>
            <table cellspacing="0" cellpadding="0" border="0" class="table-style01 borz01">
            <tr>
                <td>
                    ※ <spring:message code="clm.page.msg.manage.announce130" /> <br>
                    &nbsp;<spring:message code="clm.page.msg.manage.forRel" />  <br>
                    &nbsp;<spring:message code="clm.page.msg.manage.smRel" /> <br>
                    &nbsp;<spring:message code="clm.page.msg.manage.chgCancel" /> <br>
                    &nbsp;<spring:message code="clm.page.msg.manage.announce005" />  
                </td>
                </tr>
            </table>
	
				<!-- 체결정보 -->
				<div class="title_basic" style='width:100%; margin:10px 0'>
					<h4><spring:message code="clm.page.msg.manage.conclInf" /></h4>
				</div>
				
				<div class='title_basic3'><spring:message code="clm.page.msg.manage.bscInf" /></div>
				<table class="table-style01">
					<colgroup>
						<col width="12%" />
			            <col width="88%" />	<!-- 신성우 변경처리 2014-04-03-->
					</colgroup>
					<tr>
		            <th width="12%"><spring:message code="clm.page.field.contract.consultation.detail.sign"/><!-- 서명 --></th>
		            <td width="38%" colspan="3">
                      <input type="radio" name="seal_mthd_chk" id="seal_mthd_chk" class="radio" value="C02102" checked onClick="javascript:setSealMethod(this.value);"<c:if test="${lomRq.seal_mthd=='C02102'}"> checked</c:if>> <spring:message code="clm.page.field.contract.consultation.detail.sign" /></input>
                            
		            </td>
		        </tr>
		        <c:set var="display1" value="visible"/>
		        <c:set var="display2" value="hidden"/>
		        <c:set var="dispcolspan" value="3"/>
		        <c:if test="${lomRq.seal_mthd eq 'C02102' }">
                    <c:set var="display2" value="visible"/>
                    <c:set var="dispcolspan" value="1"/>
		        </c:if>
                <!-- 날인인 경우 -->
                <tr id="seal-tr">
	                <th id="ss1" style="border-left:1px solid #CADBE2">
                    	<spring:message code="clm.page.msg.manage.signPer"/><span class='astro'>*</span><!-- 서명자 -->
                    </th>
		               <input type="hidden" name="signman_id" id="signman_id" value="<c:out value='${lomRq.signman_id}'/>" alt="<spring:message code="clm.page.field.contract.conclusion.detail.signmannm"/>"/>
		               <input type="hidden" name="signman_jikgup_nm" id="signman_jikgup_nm" value="<c:out value='${lomRq.signman_jikgup_nm}'/>" />
		               <input type="hidden" name="signman_nm" id="signman_nm" value="<c:out value='${lomRq.signman_nm}'/>" alt="<spring:message code="clm.page.field.contract.conclusion.detail.signmannm"/>"/>
		               <input type="hidden" name="sign_dept_nm" id="sign_dept_nm" value="<c:out value='${lomRq.sign_dept_nm}'/>" />
                    <td id="ss2" colspan = "3" style="border-left:1px solid #CADBE2">
		               <input type="text" name="signman_search_nm" id="signman_search_nm" value="" style="width:110px" class="text_search" alt="<spring:message code="clm.page.field.contract.conclusion.detail.signmannm"/>" onKeyDown="if(event.keyCode==13){event.returnValue = false;javascript:searchEmployee2('sign');}"/><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchEmployee2('sign');" class="cp" alt="search" />
		               <span id="signman">&nbsp;&nbsp;<c:out value='${lomRq.signman_nm}'/>/<c:out value='${lomRq.signman_jikgup_nm}'/>/<c:out value='${lomRq.sign_dept_nm}'/></span>
                    </td>
                </tr>
                <tr id="seal-tr1" style="visibility:<c:out value="${display1 }"/>">
		            <th><spring:message code="clm.page.msg.manage.contConclDt" /><span class='astro'>*</span></th>
		            <td colspan="3">
			            <input type="text" name="cntrt_cnclsnday" id="cntrt_cnclsnday" value="<c:out value='${lomRq.cntrt_cnclsnday}'/>"  class="text_calendar02" style='width:110px' pxrequired/>
      
		            </td>
		        </tr>
		        <tr>
		            <th><spring:message code="clm.page.field.contract.consultation.detail.contractrespmannm"/></th>
			            <input type="hidden" name="cntrt_respman_id" id="cntrt_respman_id" value="<c:out value='${lomRq.cntrt_respman_id}'/>" />
	                    <input type="hidden" name="cntrt_respman_nm" id="cntrt_respman_nm" value="<c:out value='${lomRq.cntrt_respman_nm}'/>" />
	                    <input type="hidden" name="cntrt_respman_jikgup_nm" id="cntrt_respman_jikgup_nm" value="<c:out value='${lomRq.cntrt_respman_jikgup_nm}'/>"/>
	                    <input type="hidden" name="cntrt_resp_dept" id="cntrt_resp_dept" value="<c:out value='${lomRq.cntrt_resp_dept}'/>"/>
	                    <input type="hidden" name="cntrt_resp_dept_nm" id="cntrt_resp_dept_nm" value="<c:out value='${lomRq.cntrt_resp_dept_nm}'/>"/>
		            <td colspan="3">
	                    <input type="text" name="cntrt_respman_search_nm" id="cntrt_respman_search_nm" value=""  style="width:110px" class="text_search"  alt="<spring:message code='clm.page.field.contract.consultation.detail.contractrespmannm'/>" onkeydown="if(event.keyCode==13){event.returnValue = false;javascript:searchEmployee2('contract');}"/><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchEmployee2('contract');" class="cp" alt="search"/>
	                    <span id="respman">&nbsp;&nbsp;<c:out value='${lomRq.cntrt_respman_nm}'/>/<c:out value='${lomRq.cntrt_respman_jikgup_nm}'/>/<c:out value='${lomRq.cntrt_resp_dept_nm}'/></span>
	                    <br><br><span><spring:message code="clm.msg.alert.contract.consultation.respmannotice"/></span>   
		            </td>
		        </tr>	
				</table>
				
				<div class='title_basic3'><spring:message code="clm.page.msg.manage.copyVerInf" /><!-- 사본정보 --></div>
				<table class="table-style01">
					<colgroup>
						<col width="12%" />
						<col width="54%" />
						<col width="12%" />
						<col width="22%" />
					</colgroup>
					<tr>
						<th><spring:message code="clm.page.msg.manage.copyVerRegPer" /></th>
						<td>
						<c:if test="${lomRq.cpy_regman_nm ne '' }" >
						<c:out value='${lomRq.cpy_regman_nm}'/>/<c:out value='${lomRq.cpy_regman_jikgup_nm}'/>/<c:out value='${lomRq.cpy_reg_dept_nm}'/>
						</c:if>
						</td>
						<th><spring:message code="clm.page.msg.manage.copyVerRegDate" /></th>
						<td><c:out value='${lomRq.cpy_regday}'/>
						</td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.manage.conclCopy" /><img src="/images/las/ko/common/step_q.gif" alt="info" title="PDF &#13;&#13;<spring:message code='las.msg.com.fileFilter'/>" /></th>
						<td colspan="3">
							<div class='ico_maxsize fR'>Max Size : <span>10MB</span></div>
							<iframe src="<c:url value='/las/blank.do' />" id="fileList6" name="fileList6" frameborder="0" class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>
						</td>
					</tr>
				</table>
			<!-- button -->
			<div class="t_titBtn">
      			<div class="btn_wrap_c">
					<span class="btn_all_w" onclick="insertConsideration('req');"><span class="sangsin"></span><a><spring:message code="clm.page.button.contract.cosultationapproval" /></a></span>	
					<span class="btn_all_w" onclick="insertConsideration('save');"><span class="tsave"></span><a><spring:message code="clm.page.msg.manage.tmpSave" /></a></span>				
				    <span class="btn_all_w" onclick="forwardConsideration('list');"><span class="list"></span><a><spring:message code="clm.page.msg.manage.list" /></a></span>
				</div>
			</div>
			<!-- //button -->
		</form:form>
		</div>	
		<!-- //content_in -->
		</div>	
		<!-- //content -->
	</div>
<!-- //container -->
</div>
<%if("forwardReq".equals(lomRq.get("status")) || "forwardLast".equals(lomRq.get("status"))){		//재의뢰 or 최종본의뢰%>
<SCRIPT language="javascript" FOR="wec" EVENT="OnInitCompleted()">
//재의뢰 or 최종본의뢰	
 	
 	
</SCRIPT>
<%}else if("forwardModify".equals(lomRq.get("status"))){				//수정%>
<SCRIPT language="javascript" FOR="wec" EVENT="OnInitCompleted()">
//수정
 
</SCRIPT>
<%}else{ %>
<SCRIPT language="javascript" FOR="wec" EVENT="OnInitCompleted()">
if(document.frm.status.value=="forwardInsert") {
	
	openChooseContractType();
	
}
	
</SCRIPT>
<%} %>
			
		<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- // footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</body>
</html>