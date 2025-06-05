<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
 * 파  일  명 : RegConsideration_i.jsp
 * 프로그램명 : 체결 후 품의 작성
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2012.01
 * 수  정  일 : 
 * 수  정  자 : 
 * 수정내용  : 
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
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title" /></title>
<LINK href="<%=CSS%>/main.css"   type="text/css" rel="stylesheet">
<LINK href="<%=CSS%>/popup.css"   type="text/css" rel="stylesheet">
<LINK href="<%=CSS%>/common.css"   type="text/css" rel="stylesheet">
<LINK href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet">
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
	
	function init(){
		forwardInsertContractMaster();
		//페이지 시작과 함께 유형 정보가 떠야 된다.
		if(document.frm.status.value=="forwardInsert") {
			openChooseContractType();
		}
		
		initCal("re_demndday");
		
		initCal("cnclsn_plndday1");
		
		//initCal("exprt_notiday");		//신성우 주석처리 2014-03-31
		
		//계약단가요약에 데이터가 있으면 단가내역요약 보이게 수정.
		if(frm.cntrt_untprc_chk.value != ""){
			document.getElementById("cntrt_untprc").checked = true;
			document.getElementById("trCntrtUntprc").style.display="block";
		}
		tit_Toggle(this, 'tr_down02'); // 김재원 추가 2011.10.15
		
		executionlist();
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
	     
	   //계약관련 #2 계약서- 기타_체결본
	     frm.target          = "fileList2";
	     frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	     frm.method.value    = "forwardClmsFilePage";
	     frm.file_bigclsfcn.value = "F012";
	     frm.file_midclsfcn.value = "F01202";
	     frm.file_smlclsfcn.value = "F0120212";
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
	}	
  	
	
	function forwardConsideration(arg){
		var frm = document.frm;	
		frm.target = "_self";		 
		frm.action = "<c:url value='/clm/manage/consideration.do' />";
		frm.method.value = "listManageConsideration";			
		frm.pageGbn.value = "registration";
		
		frm.submit();		
	}
	
	
	function insertConsideration(arg){
  		var frm = document.frm;  		
		frm.submit_status.value = arg;
  		frm.target = "_self";
  		
  		if(arg == "save"){	//임시저장  			
  			if(!confirm("<spring:message code="clm.page.msg.manage.announce137" />")) return;  			
  		}else if(arg == "req" && "IF" !=$('#req_reg_id').val()){ //검토의뢰(상신)
  			if(frm.approvalman_id.value=="") {
  				alert("<spring:message code="clm.page.msg.manage.announce026" />");
  				frm.approvalman_search_nm.focus();  				
  				viewHiddenProgress(false);  				
  				return; 				
  			}
  		    //계약 검토 의뢰건에 대해서는 계약 결재자가 책임과 권한을 가집니다
  			if(!confirm("<spring:message code="clm.page.msg.manage.announce024" />")) return;
  		}else if(arg == "req" && "IF" ==$('#req_reg_id').val()){ //검토의뢰(상신)
  			arg = "reqIF";
  			frm.submit_status.value = arg;  			
  			if(!confirm("<spring:message code="clm.page.msg.manage.announce025" />")) return;			
  		}else if(arg == "again"){	//재검토의뢰 	
  			if(!confirm("<spring:message code="clm.page.msg.manage.announce178" />")) return;  			
  		}else if(arg == "last"){	//최종본	
  			if(!confirm("<spring:message code="clm.page.msg.manage.announce184" />")) return;  					
  		}else{	//목록  			
  			forwardConsideration('list');//리스트로 이동 
  			return;
  		}
  		
  		if(validateTempSave(frm)){ 	
  		
  	  		if(!frm.req_title.value){//의뢰제목REQ_TITLE
  	  			alert("<spring:message code="clm.page.msg.manage.announce022" />");return;  	  			
  	  		}
  	  		// 2012.03.20 임시저장일 경우, 검토요청내용 필수 입력 제외 added by hanjihoon
  	  		if(arg != "save" && "" == stripHTMLtag(frm.wec[0].BodyValue)){//검토요청 내용 CNSD_DEMND_CONT
	  			alert("<spring:message code="clm.page.msg.manage.announce018" />");return;  	  			
	  		}
  	  		if(!frm.cntrt_oppnt_cd.value){//거래상대방
	  			alert("<spring:message code="clm.page.msg.manage.announce012" />");return;  	  			
	  		}
  	  		if(!frm.biz_clsfcn.value){//계약유형
  				alert("<spring:message code="clm.page.msg.manage.announce052" />");return;  	  			
  			}  			
  		}
		frm.body_mime.value = frm.wec[0].MIMEValue;
  		frm.body_mime1.value = frm.wec[1].MIMEValue;
  		
  		// 2012.03.19 계약기간 필수입력 alert 추가 added by hanjihoon
  		if(frm.cntrtperiod_startday.value == "" || frm.cntrtperiod_endday.value == ""){
			alert("<spring:message code="clm.page.msg.manage.announce041" />");
			return;
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
 			  
  			if(!frm.cntrt_oppnt_type.value){
  				alert("<spring:message code="clm.page.msg.manage.announce115" />");return;
  			}
  			if(!frm.cntrt_trgt.value){
  				alert("<spring:message code="clm.page.msg.manage.announce028" />");return;
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
				
				var exe_cnt = 0;
				if($('#payment_gbn > option:selected').val() =="C02001") {	//지불/수금
					
					if($('#cntrt_amt').val() == ''){
						
						if(!$("[name=cntrt_untprc]").is(":checked")){
							alert("<spring:message code="clm.page.msg.manage.announce038" />");	
							$('#cntrt_amt').focus();
							return;
						}
					}
					
					exe_cnt = $('#executionTbody tr').length + $('#executionTbody2 tr').length + $('#executionTbody3 tr').length;
					if(exe_cnt==0){
						alert("<spring:message code="clm.page.msg.manage.announce152" />");
						return;
					}
				} else if($('#payment_gbn > option:selected').val() =="C02002") {	//지불
					
					if($('#cntrt_amt').val() == ''){
						if(!$("[name=cntrt_untprc]").is(":checked")){
							alert("<spring:message code="clm.page.msg.manage.announce038" />");
							$('#cntrt_amt').focus();
							return;
						}
					}
				
					exe_cnt = $('#executionTbody tr').length;
					if(exe_cnt==0) {
						alert("<spring:message code="clm.page.msg.manage.announce153" />");
						return;
					}
				} else if($('#payment_gbn > option:selected').val() =="C02003") {	//수금
					
					if($('#cntrt_amt').val() == ''){
						if(!$("[name=cntrt_untprc]").is(":checked")){
							alert("<spring:message code="clm.page.msg.manage.announce038" />");	
							$('#cntrt_amt').focus();
							return;
						}
					}
				
					exe_cnt = $('#executionTbody2 tr').length;
					if(exe_cnt==0) {
						alert("<spring:message code="clm.page.msg.manage.announce151" />");
						return;
					}	
				}			
			}else{
  			//지불 or 수불 일때문 계약 금액 필수
	  			if("C02002" == frm.payment_gbn.value || "C02003" == frm.payment_gbn.value || "C02001" == frm.payment_gbn.value ){
	  				if($("[name=cntrt_untprc]").is(":checked")){
	  					if(!$("#cntrt_untprc_expl").val()){  			
	  		  				//cntrt_untprc_expl
	  		  				alert("<spring:message code="clm.page.msg.manage.announce027" />");  				
	  		  				return;
	  					}
	  				}else{
	  					//계약금액은 필수 입력 항목 입니다. 
	  					if(!frm.cntrt_amt.value){alert("<spring:message code="clm.page.msg.manage.announce037" />");return;}
	  				}
	  			}
  			}
 			
 			if("" == stripHTMLtag(frm.wec[1].BodyValue)){//
	  			alert("<spring:message code="clm.page.msg.manage.announce188" />");
	  			return;
	  		}
 			
 			if($("[name=cntrt_untprc]").is(":checked")){
 				if(!$("#cntrt_untprc_expl").val()){  			
  				//cntrt_untprc_expl
  				alert("<spring:message code="clm.page.msg.manage.announce027" />");  				
  				return;
 				} 				
 				if(cue.length == 0){
 	  				alert("<spring:message code="clm.page.msg.manage.announce061" />");
 	  				return;
 	  			}
 			} 

 			if(!frm.antcptnefct.value){//ANTCPTNEFCT
 				alert("<spring:message code="clm.page.msg.manage.announce067" />");return;//기대효과
 			}
 			
  		  	//사전승인발의자
 			if(!frm.bfhdcstn_mtnman_id.value){
 				alert("<spring:message code="clm.page.msg.manage.announce098" />");
 				
 				//viewHiddenProgress(false);
 				
 				return;
 			}
  			//사전승인승인자
 			if(!frm.bfhdcstn_apbtman_id.value){
 				alert("<spring:message code="clm.page.msg.manage.announce101" />");
 				
 				//viewHiddenProgress(false);
 				
 				return;
 			}
  			//사전승인승인일
 			if(!frm.bfhdcstn_apbtday.value){
 				alert("<spring:message code="clm.page.msg.manage.announce101" />");
 				
 				//viewHiddenProgress(false);
 				
 				return;
 			}
  			//사전승인승인방식
 			if(!frm.bfhdcstn_apbt_mthd.value){
 				alert("<spring:message code="clm.page.msg.manage.announce099" />");
 				
 				//viewHiddenProgress(false);
 				
 				return;
 			}
 			
			if("Y" == $('#file_validate').val() && $("#cnsdreq_id").val() != "" && "Y" == $('#plndbn_req_yn').val()){
	 				alert("<spring:message code="clm.page.msg.manage.announce009" />");
	 				
	 				//viewHiddenProgress(false);
	 				
	 				return;
	 		}
		}
  			
  		
		}// end if(validateClmForm(frm))
		
		viewHiddenProgress(true);
		
		var options = {
			url: "<c:url value='/clm/manage/consideration.do?method=insertConsideration&pageGbn=registration' />",
			type: "post",
			async: false,
			dataType: "json",			
			success: function(responseText, statusText) {  //undefined

				//계약서 필수 에서 빼기 최종 의뢰 ,,,,,,재의뢰시 에 .....최종본
	    		viewHiddenProgress(false);
	    		
				if(responseText.returnValue == undefined){
					
					alert("<spring:message code="clm.page.msg.manage.announce157" />");
					forwardInsertContractMaster();  // 오타로 인한 수정
					tit_Toggle(this, 'tr_down02');
				}else if(("save" == arg || "last" == arg) && responseText.returnValue == "N"){				
					alert(responseText.msg);//필수 입력 항목 안내문
					//현재 계약 탭 load 하기		
					forwardInsertContractMaster();
					tit_Toggle(this, 'tr_down02');					
					
				}else if(("save" == arg || "last" == arg || "reqIF"==arg) && responseText.returnValue == "Y"){					
					alert(responseText.msg);//정상처리 되었습니다. 
					forwardConsideration('list');//리스트로 이동 
					
					
				}else if("req" == arg && responseText.returnApproval == "Y"){				
					alert("<spring:message code="clm.page.msg.manage.announce177" />");	
					setApprovalYN("Y");
				}else if("req" == arg && responseText.returnApproval == "N"){										
					alert("<spring:message code="clm.page.msg.manage.announce176" />");
					setApprovalYN("N");
				} else if(responseText.returnValue == "N"){   // N으로의 값이 넘어와서 N만 처리하는 항목 추가하였습니다.
					alert(responseText.msg);
					forwardInsertContractMaster();
					tit_Toggle(this, 'tr_down02');
				}				
			}
		}
			//계약관련#0
		 fileList1.UploadFile(function(uploadCount){
			if(uploadCount == -1){
				initPage();
				alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
				viewHiddenProgress(true);
				return; 					
			}
			if(uploadCount == 0 && "Y" == $('#plndbn_req_yn').val() && arg != "save"){
			//if(uploadCount == 0 && arg != "save"){
				alert("<spring:message code="clm.page.msg.manage.contract" /> <spring:message code='secfw.msg.error.fileNon' />");
				viewHiddenProgress(true);
                  return;
			}
			//계약서 첨부/별첨
			fileListEtc.UploadFile(function(uploadCount){
  				if(uploadCount == -1){
  					initPage();
  					alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
  					viewHiddenProgress(true);
  					return; 					
  				}
  						
				 //계약관련#1
	  			 fileList2.UploadFile(function(uploadCount){
	  				if(uploadCount == -1){
	  					initPage();
	  					alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
	  					viewHiddenProgress(true);
	  					return; 					
	  				}
	  				
	  			 	fileList3.UploadFile(function(uploadCount){
	  					if(uploadCount == -1){
	  						//initPage();
	  						alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
	  						viewHiddenProgress(true);	  						
	  						return; 					
	  					}
	  					
	  					//계약관련#3
	 	    			 fileList4.UploadFile(function(uploadCount){
	 	    				if(uploadCount == -1){
	 	    					//initPage();
	 	    					alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
	 	    					viewHiddenProgress(true);
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
  	* 데이타 저장 상단 버튼클릭시 
  	*/
  	function insertConsiderationXXXX(arg){
  		var frm = document.frm;
  		
  		frm.submit_status.value = arg;
  		frm.target = "_self";		 
		frm.action = "<c:url value='/clm/manage/consideration.do' />";
  		
  		if(arg == "save"){	//임시저장  			
  			if(!confirm("<spring:message code="clm.page.msg.manage.announce137" />")) return;
  			frm.method.value = "insertConsideration"; 
  		}else if(arg == "req"){ //검토의뢰(상신)
  			if(frm.approvalman_id.value=="") {
  				alert("<spring:message code="clm.page.msg.manage.announce026" />");
  				frm.approvalman_search_nm.focus();  				
  				return;
  			}
  		    //계약 검토 의뢰건에 대해서는 계약 결재자가 책임과 권한을 가집니다
  			if(!confirm("<spring:message code="clm.page.msg.manage.announce179" />")) return;
  			frm.method.value = "insertConsideration";
  			
  		}else if(arg == "again"){	//재검토의뢰 	
  			if(!confirm("<spring:message code="clm.page.msg.manage.announce178" />")) return;
  			frm.method.value = "insertConsideration";
  		}else if(arg == "last"){	//최종본	
  			if(!confirm("<spring:message code="clm.page.msg.manage.announce178" />")) return;
  			frm.method.value = "insertConsideration";
  		}else if(arg == "review"){	//미리보기
  			alert("<spring:message code="clm.page.msg.manage.nessFunc" />");
  			return;
  		}else{	//목록  			
  			frm.method.value = "listManageConsideration";
  		}
  		
  		frm.body_mime.value = frm.wec[0].MIMEValue;
  		frm.body_mime1.value = frm.wec[1].MIMEValue;
  		
  		if(validateClmForm(frm)){
  			
  			if(arg == "req" || arg == "again" || arg == "last" ){  				
  				
  				if(!frm.body_mime.value){
  		  			alert("<spring:message code="clm.page.msg.manage.announce016" />");
  		  			return;
  		  		}
  		  		if(!frm.body_mime1.value){
  		  			alert("<spring:message code="clm.page.msg.manage.announce188" />");
  		  			return;
  		  		}
  		  		
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
  	  			
  	  			//계약기간 Chk
  	  			var str_from = frm.cntrtperiod_startday.value.replace('-','').replace('-','');
  	  			var str_to = frm.cntrtperiod_endday.value.replace('-','').replace('-','');
  	  			
  	  			var cue = frm.cntrt_untprc_expl.value.replace(/^\s+/,""); // lpad 하는 것입니다.
  	  			
  	  			if(eval(str_from) > eval(str_to)){
  	  				alert("<spring:message code="clm.page.msg.manage.announce039" />");
  	  				return;
  	  			}  	  			
  	  			
  	  			if($("[name=cntrt_untprc]").is(":checked")){
  	  				if(!$("#cntrt_untprc_expl").val()){  			
  		  				//cntrt_untprc_expl
  		  				alert("<spring:message code="clm.page.msg.manage.announce027" />");  				
  		  				return;
  	  				}
  	  				
  	  				if(cue.length == 0){
  	  	  				alert("<spring:message code="clm.page.msg.manage.announce061" />");
  	  	  				return;
  	  	  			}
  	  			}
  			
  				if("Y" == $('#file_validate').val() && $("#cnsdreq_id").val() != ""){
  	  				alert("<spring:message code="clm.page.msg.manage.announce009" />");
  	  				return;
  	  			}
  			}
  			
  			//계약관련#0
 			 fileList1.UploadFile(function(uploadCount){
 				if(uploadCount == -1){
 					initPage();
 					alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
 					return; 					
 				}
 				//if(uploadCount == 0 && "Y" == $('#plndbn_req_yn').val() ){
 				if(uploadCount == 0 && arg != "save"){
 					alert("<spring:message code="clm.page.msg.manage.contract" /> <spring:message code='secfw.msg.error.fileNon' />");
                    return;
 				}
 				//계약서 첨부/별첨
 				fileListEtc.UploadFile(function(uploadCount){
 	  				if(uploadCount == -1){
 	  					initPage();
 	  					alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
 	  					return; 					
 	  				}
	 				 //계약관련#1
	 	  			 fileList2.UploadFile(function(uploadCount){
	 	  				if(uploadCount == -1){
	 	  					initPage();
	 	  					alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
	 	  					return; 					
	 	  				}
	 	  				
	 	  			 	fileList3.UploadFile(function(uploadCount){
	 	  					if(uploadCount == -1){
	 	  						//initPage();
	 	  						alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
	 	  						return; 					
	 	  					}
	 	  					
	 	  					//계약관련#3
	 	 	    			 fileList4.UploadFile(function(uploadCount){
	 	 	    				if(uploadCount == -1){
	 	 	    					//initPage();
	 	 	    					alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
	 	 	    					return; 					
	 	 	    				}	 	 	    					 	 	    				
	 	 	    				if(arg=="req") {
 	 	    	  	  				openApproval();
 	 	    	  	  				return;
 	 	    	  	  			} else {
	 	    	  	  				viewHiddenProgress(true);
 	 	    	  	  				frm.submit();
 	 	    	  	  			}	 	  			 
	 	  			 		});//end fileList4
	 	  			 	});//end fileList3 				
	 			 	});//end fileList2
 				});//end fileListEtc  		
  			 });//end fileList1
			  			
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
  	    }
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
			$("#crrncy_unit").html("<option value=>해당없음</option>");
			$("input[name=cntrt_untprc]").attr('disabled', true);
			document.getElementById("trCntrtUntprc").style.display="none";
			$("#cntrt_untprc_expl").val("");
			//단가내역 요약 첨부파일 초기화
			initCntrtUntprcExpl();
		}else{			
			$("input[name=cntrt_amt]").attr('disabled', false);
			getCodeSelectByUpCd("frm", "crrncy_unit", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=A&combo_grp_cd=C5&combo_type=S&combo_del_yn=N&combo_selected=KRW');
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
	  *계약 건 데이타 저장   - master tb insert or update submit   --- 계약 탭을 추가 했을 때 마스터 저장 
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
				  		
						for(i=1; i<=tabCnt; i++){			
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
			}
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
				$("#rel_defn").html("<option value=<spring:message code='clm.page.msg.manage.preChg' />><spring:message code='clm.page.msg.manage.preChg' /></option><option value=<spring:message code='clm.page.msg.manage.befCalncel' />><spring:message code='clm.page.msg.manage.befCalncel' /></option>");
			}else{//선택없음
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
				  alert("<spring:message code='clm.page.msg.manage.announce063' />");
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
		}
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
			  +	"<td class=\"tal_lineL\"><span class=\"th-color\"><spring:message code='clm.page.field.contract.detail.relation.name' /></span></td>  "	
			  
		  //tab_contents
		  $("#tab_contents").append("");
		  $("#tab_contents").append(html);
	  }
	  /**
	  *계약 탭 추가 및 계약 컨테츠 생성 데이타 저장 
	  */
	  function contractAction(arg){
		  var frm = document.frm;

		  $("#tmp_cntrt_oppnt_cd").val($("#cntrt_oppnt_cd").val());		  
		  $("#tmp_region_gbn").val($("#region_gbn").val());  //master tb
		  $("#tmp_cntrt_oppnt_nm").val($("#cntrt_oppnt_nm").val());
		  $("#tmp_cntrt_oppnt_rprsntman").val($("#cntrt_oppnt_rprsntman").val());
		  $("#tmp_cntrt_oppnt_type").val($("#cntrt_oppnt_type").val());
		  $("#tmp_cntrt_oppnt_respman").val($("#cntrt_oppnt_respman").val());
		  $("#tmp_cntrt_oppnt_telno").val($("#cntrt_oppnt_telno").val());
		  $("#tmp_cntrt_oppnt_email").val($("#cntrt_oppnt_email").val());		  
			  
			  
		  //arg ADD DEL
		  if("ADD" == arg){	
			  frm.body_mime.value = frm.wec[0].MIMEValue;
			  frm.body_mime1.value = frm.wec[1].MIMEValue;
			  
			  //나모 값 이전탭 값 복사
			  document.frm.cnsd_demnd_cont.value = frm.wec[0].BodyValue
			  
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
			  //addTabCnt();
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
					//detailAction();
					forwardModifyConsideration();
					alert("<spring:message code="clm.page.msg.manage.announce158" />");					
				}else{
					alert("<spring:message code="clm.page.msg.manage.announce157" />");
				}
			}
		}
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
		  
		  frm.body_mime.value= frm.wec[0].MIMEValue;
		  frm.body_mime1.value = frm.wec[1].MIMEValue;
		  //의뢰내용 이동복사 나모가 이동안되는현상 제거 
		  document.frm.cnsd_demnd_cont.value = frm.wec[0].BodyValue
		  		  
		  if(validateClmForm(frm)){ 
			  
			  var str_from = frm.cntrtperiod_startday.value.replace('-','').replace('-','');
	  		  var str_to = frm.cntrtperiod_endday.value.replace('-','').replace('-','');
	  			
	  		  if(eval(str_from) > eval(str_to)){
	  			alert("<spring:message code="clm.page.msg.manage.announce039" /> ");
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
						 //initPage();
						}
						
					}
				}
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
			
			for(i=1; i<=tab_cnt; i++){			
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
	  				url: "<c:url value='/clm/manage/consideration.do?method=forwardInsertContractMaster&pageGbn=registration' />",
	  				type: "post",
	  				async: false,
	  				dataType: "html",
	  				success: function (data) {
	  					$("#tab_contents").html("");	  					
	  					$("#tab_contents").html(data);
	  					
	  					// 2012.03.07 최초의뢰도 검토이력 출력 modified by hanjihoon
	  					contractHisList();
	  				}
	  			}
	  		$("#frm").ajaxSubmit(options);
	  		viewHiddenProgress(false);
	  		
	  	}
	  
	  	function xxx(){
	  		initCal("cntrtperiod_startday");    //계약기간
	  		initCal("cntrtperiod_endday");		//계약기간
	  		initCal("bfhdcstn_apbtday");		//처리일
	  		
	  		//첨부파일창 호출
	  		initPage();	

	  		//지불구분
	  		//getCodeSelectByUpCd("frm", "payment_gbn", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=F&combo_grp_cd=C020&combo_type=&combo_del_yn=N&combo_selected=<c:out value='${lomRq.payment_gbn}'/>');				
	  		//화페단위
	  		//2011. 10. 15 mod by 김현구. 기본값 세팅 - KRW 
	  		getCodeSelectByUpCd("frm", "crrncy_unit", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=A&combo_grp_cd=C5&combo_type=S&combo_del_yn=N&combo_selected=<c:out value='${lomRq.crrncy_unit}'/>');
	        //거래상재방 업체 Type - cntrt_oppnt_type	
	  		getCodeSelectByUpCd("frm", "cntrt_oppnt_type", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=F&combo_grp_cd=C056&combo_type=S&combo_del_yn=N&combo_selected=<c:out value='${lomRq.cntrt_oppnt_type}'/>');
	  		//계약대상  - cntrt_trgt		
	  		getCodeSelectByUpCd("frm", "cntrt_trgt", '/common/clmsCom.do?method=getComboHTML&combo_sys_cd=CLM&combo_gbn=CONTRACTTYPE&combo_up_cd=<c:out value='${lomRq.cnclsnpurps_midclsfcn}'/>&combo_abbr=F&combo_type=T&combo_del_yn=N&combo_selected=<c:out value='${lomRq.cntrt_trgt}'/>');		
	  		//사전승인 승인 방법
	  		getCodeSelectByUpCd("frm", "bfhdcstn_apbt_mthd", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=A&combo_grp_cd=C028&combo_type=S&combo_del_yn=N&combo_selected=<c:out value='${lomRq.bfhdcstn_apbt_mthd}'/>');
	  		//연관계약 관계유형		
	  		getCodeSelectByUpCd("frm", "rel_type3", "/common/clmsCom.do?method=getComboHTML&combo_sys_cd=CLM&combo_gbn=CONTRACTTYPE&combo_up_cd=T02&combo_abbr=F&combo_type=T&combo_del_yn=N&combo_selected=");
	  		
	  		
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
			 
			 PopUpWindowOpen("/clm/manage/consideration.do?method=popupListContract&menu_id=20110803091536879_0000000048&arg="+arg+"&rel_type1="+rel_type1, 840, 600, true);
			 
			} else {
			 alert("  <spring:message code="clm.page.msg.manage.announce119" />");
			}
		}
		
		/**
		* 연관계약 팝업 리턴 처리
		*/	  
		function setContract(id,name,biz,sArg){
			var fm = document.frm;			
			
			//리턴값 변수에 저장
			var cntrt_id = id;
			var cntrt_name = name;
			var cntrt_biz_clsfcn = biz;
			var srch_arg = sArg;
			
			$("#parent_cntrt_id").val(cntrt_id);
			$("#rel_type").val(srch_arg);
			$("#parent_cntrt_name").val(cntrt_name);
			
			//2011.11.10 chahyunjin 연관계약 입력폼 변경으로 스크립트 변경 함
			//현재 연계계약에 동일한 계약이 존재하는 지 체크
			/* var pcid01 = $("#C03201_pcid").val();
			var pcid02 = $("#C03202_pcid").val();
			var pcid03 = $("#C03203_pcid").val();
			var pcid04 = $("#C03204_pcid").val();

			if(cntrt_id == pcid01 || cntrt_id == pcid02 || cntrt_id == pcid03 || cntrt_id == pcid04){
				alert("<spring:message code='clm.page.alert.contract.isCntrt' />");
				return;
			}

			if(srch_arg == 'C03201'){
				$("#C03201_pcid").val(cntrt_id);
				$("#C03201_pcnm").val(cntrt_name);
				$("#C03201_ct").val($("#cntrt_nm").val());
				$("#C03201_pcnm2").val(cntrt_name);
				$("#C03201_rt").val(cntrt_biz_clsfcn);  
			}else if(srch_arg == 'C03202'){
				$("#C03202_pcid").val(cntrt_id);
				$("#C03202_pcnm").val(cntrt_name);
				$("#C03202_ct").val($("#cntrt_nm").val());
				$("#C03202_pcnm2").val(cntrt_name);
			}else if(srch_arg == 'C03203'){
				$("#C03203_pcid").val(cntrt_id);
				$("#C03203_pcnm").val(cntrt_name);
				$("#C03203_ct").val($("#cntrt_nm").val());
				$("#C03203_pcnm2").val(cntrt_name);
			}else if(srch_arg == 'C03204'){
				$("#C03204_pcid").val(cntrt_id);
				$("#C03204_pcnm").val(cntrt_name);
				$("#C03204_ct").val($("#cntrt_nm").val());
				$("#C03204_pcnm2").val(cntrt_name);
			} */
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
	     
		 //this.biz_clsfcn        = biz_clsfcn;
	     this.biz_clsfcn_nm     = biz_clsfcn_nm;
	     //this.depth_clsfcn      = depth_clsfcn;
	     this.depth_clsfcn_nm   = depth_clsfcn_nm
	     //this.cnclsnpurps_bigclsfcn    	= cnclsnpurps_bigclsfcn;
	     this.cnclsnpurps_bigclsfcn_nm 	= cnclsnpurps_bigclsfcn_nm;
	     //this.cnclsnpurps_midclsfcn 	= cnclsnpurps_midclsfcn;
	     this.cnclsnpurps_midclsfcn_nm 	= cnclsnpurps_midclsfcn_nm;
	     
	      this.biz_clsfcn  		 = $("#biz_clsfcn").val() //"";	      
	      this.depth_clsfcn       = $("#depth_clsfcn").val() // "";	 	   	
	 	
	      this.cnclsnpurps_bigclsfcn     	= $("#cnclsnpurps_bigclsfcn").val() // "";	 	  
	 	  this.cnclsnpurps_midclsfcn 		= $("#cnclsnpurps_midclsfcn").val() // "";
	 	  
		
	  }		  
	  /**
	  *계약유형선택팝업 오픈
	  */
	  function openChooseContractType() {	  
		  
		  var frm = document.frm;   
	   
	 	  var biz_clsfcn  		 = $("#biz_clsfcn").val() //"";
	      var biz_clsfcn_nm      = "";
	 	  var depth_clsfcn       = $("#depth_clsfcn").val() // "";
	 	  var depth_clsfcn_nm    = ""; 	
	 	
	 	  var cnclsnpurps_bigclsfcn     	= $("#cnclsnpurps_bigclsfcn").val() // "";
	 	  var cnclsnpurps_bigclsfcn_nm  	= "";
	 	  var cnclsnpurps_midclsfcn 		= $("#cnclsnpurps_midclsfcn").val() // "";
	 	  var cnclsnpurps_midclsfcn_nm 		= "";
	
	 	  var result = new retObj(biz_clsfcn, biz_clsfcn_nm,  depth_clsfcn, depth_clsfcn_nm, cnclsnpurps_bigclsfcn, cnclsnpurps_bigclsfcn_nm, cnclsnpurps_midclsfcn, cnclsnpurps_midclsfcn_nm);
	      
	 	  //PopUpWindowModalOpen("/clm/manage/chooseContractType.do?method=forwardChooseContractTypePopup", 1000, 630, false, result);  	         
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
	 * 사전승인발의자  사전승인 승인자 정보	 /clm/manage/chooseClient.do   forwardChooseClientPopup
	 */
	 function searchEmployee(srcFrm, gb){
		 //alert("searchEmployee   ~~~~" + srcFrm.value);
		 var frm = document.frm;
		 var srchValue = comTrim(srcFrm.value); //입력받은 값 
		 
		 if (srchValue == "" || srchValue.length < 4) { //최소 2자리 이상 입력받게 한다.
	     	 alert('<spring:message code='secfw.msg.error.nameMinByte' />');
	     	 srcFrm.focus();
	         return;
	     }
		 
		 //사전승인발의자인지 사전승인승인자정보에서 호출했는지 구분해주기 위해.
	     frm.searchEmployeeGb.value = gb;
		 
		 PopUpWindowOpen('', 800, 450, true);
		 
		 frm.target = "PopUpWindow";
		 frm.srch_user_cntnt_type.value = "userName"; //이름으로 검색  - 보통 이름으로 검색함
		 frm.srch_user_cntnt.value = srchValue;
         
         frm.action = "<c:url value='/secfw/esbOrg.do' />";
         frm.method.value = "listEsbEmployee";
         frm.submit();
         
	 }
	 
	 
	 
	 /**
	 * 계약단가 활성화시 
	 */
	 function clickCntrtUntprc(){
		 var frm = document.frm;
		 if($("[name=cntrt_untprc]").is(":checked")){
			 document.getElementById("trCntrtUntprc").style.display="block";
			 $("input[name=cntrt_amt]").attr('disabled', true);
			 $("input[name=cntrt_amt]").val("");
		 }else{
			 frm.cntrt_untprc_expl.value = "";
			 document.getElementById("trCntrtUntprc").style.display="none";
			 $("input[name=cntrt_amt]").attr('disabled', false);
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
                
        PopUpWindowOpen('', "530", "470", true,'PopUpWindowClient');
        frm.action = "<c:url value='/clm/manage/chooseClient.do' />";
        frm.method.value="forwardChooseClientPopup";
        frm.target = "PopUpWindowClient";
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
	 /**
	 *의뢰자 리턴 객체 받기 심주완수정-2011.10.15
	 */
	 function setListClientInfo(returnValue) {
        var arrReturn = returnValue.split("!@#$");
        var innerHtml ="";	
        
        $('#id_trgtman_nm').html("");
        
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
		 
		 //기존 데이터가 있으면 삭제후 다시 셋팅 하기 위해.
		 if(frm.cntrtOppntChk.value != ""){
		 }
		 
		 var customer = frm.cntrt_oppnt_nm.value;
		 
		PopUpWindowOpen('', 900, 600, true, 'PopUpWindow100');
 		frm.target = "PopUpWindow100";
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
			cntrt_opnnt_region_gbn="C01802";
		}
		
		$('#dmstfrgn_gbn').val(dmstfrgn_gbn);	//국내 해외 구분 
		$("#region_gbn").val(cntrt_oppnt_region_gbn);
		$('#cntrt_oppnt_rprsntman').val(obj.tax_no);		
		//userInfo.legst            = frm.legsts.value;           //기업형태코드
		
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
		var tmpNmAbbr ="";
		if(!$('#orgnz_nm_abbr').val()){			
			tmpNmAbbr = $('#req_dept_nm').val();
		}else{
			tmpNmAbbr = $('#orgnz_nm_abbr').val();
		}
		var tmpCntrtOppntNm ="";
		
		if("H" == $('#dmstfrgn_gbn').val()){
			tmpCntrtOppntNm = chkLength($('#cntrt_oppnt_nm').val(), '16'); //한국일경우 8자리까지 김재원 값 16으로 변경
		}else{
			tmpCntrtOppntNm = chkLength($('#cntrt_oppnt_nm').val(), '12'); ///해외일경우 12자리까지
		}
		
		//사업부명으로 변경 사업부명이 없으면 부서명 으로 -1129일 수정 - orgnz_nm_abbr
		var cntrtNm = tmpNmAbbr + "_" + tmpCntrtOppntNm + "_" + $('#biz_clsfcn_nm').val() + "_" + $('#depth_clsfcn_nm').val() + "_" + $('#cnclsnpurps_bigclsfcn_nm').val() + "_" + $('#cnclsnpurps_midclsfcn_nm').val();
		
		$('#cntrt_nm').val(cntrtNm);
		$('#div_cntrt_nm').html("");
		$('#div_cntrt_nm').html(cntrtNm);
	}

	
	//다이렉트결재콜...수정하지마세요
	function openApproval() {
		var frm = document.frm;
		frm.submit_status.value = "req";
		var options = {
				url:"<c:url value='/clm/manage/consideration.do?method=insertConsideration' />",
				type:"post",
				dataType:"json",
				success: function(returnJson,returnMessage){						
					if(returnJson.retApproval=="Y") {
						alert("<spring:message code="clm.page.msg.manage.announce177" />");
						setApprovalYN("Y");
					} else {
						//결재 실패시 
						if(returnJson.ret){
							alert("<spring:message code="clm.page.msg.manage.announce176" />");
							setApprovalYN("N");	
						}else{
							
						}
						
					}						
				}			
			};
			$('#frm').ajaxSubmit(options);
	}
	
	//결재상신리턴결과
	function setApprovalYN(yn){
		var frm = document.frm;
		if(yn=="Y") {
			frm.action="<c:url value='/clm/manage/consideration.do' />";
	    	frm.target="_self";
	    	frm.pageGbn.value = "registration";
	    	frm.method.value="modifyConsiderationStatus";
	    	frm.submit();
		} else {
			frm.action="<c:url value='/clm/manage/consideration.do' />";
	    	frm.target="_self";
	    	frm.pageGbn.value = "registration";
	    	frm.method.value="listManageConsideration";
	    	frm.submit();
		}
	}  	
		 //미리보기팝업
		 function considerationPreview() {
			 alert("<spring:message code="clm.page.msg.manage.announce138" />");
			 var frm = document.frm;
				
			 PopUpWindowOpen('', "1024", "768", true);
			    
			 frm.action 		= "<c:url value='/clm/manage/consideration.do' />";
			 frm.method.value	= "forwardPreviewPop";
			 frm.target 		= "PopUpWindow";
			 frm.submit(); 
		 }
		 
		 // 체결정보에 필요한 스크립트
		 function setSealMethod(val) {
				var frm = document.frm;
				if(val=="C02101") {
					$('#seal-tr').attr("style", "display:");
					$('#sign-tr').attr("style", "display:none");
					$('#seal_ffmtman_id').attr("required", "");
					$('#seal_ffmtman_nm').attr("required", "");
					$('#seal_ffmtman_search_nm').attr("required", "");
					$('#sign_plndman_id').removeAttr("required");
					$('#sign_plndman_nm').removeAttr("required");
					$('#sign_plndman_search_nm').removeAttr("required");
				} else {
					$('#seal-tr').attr("style", "display:none");
					$('#sign-tr').attr("style", "display:");
					$('#seal_ffmtman_id').removeAttr("required");
					$('#seal_ffmtman_nm').removeAttr("required");
					$('#seal_ffmtman_search_nm').removeAttr("required");
					$('#sign_plndman_id').attr("required", "");
					$('#sign_plndman_nm').attr("required", "");
					$('#sign_plndman_search_nm').attr("required", "");
				}
			}
		//신성우 주석처리 2014-03-31
		//Val값만큼의 이전날짜를 가져온다.
		/*
		 function getBeforeDay(val) {
		 	var frm = document.frm;
		 	var endday = frm.cntrtperiod_endday.value;
		 	
		 	val = val - (val*2);
		 	frm.exprt_notiday.value=shiftTime(endday.replace(/-/g, ""), 0,0,val);
		 	
		 }*/
		
		
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
		  
		  /*
		   *	임직원조회팝업 
		   */
		  function searchEmployee2(flag) {
		  	var frm 		= document.frm;
		  	var srchValue 	= "";
		  	var obj 		= new Object();
		  	
		  	if(flag=="sign"){
		  		obj = frm.sign_plndman_search_nm;
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
		      	PopUpWindowOpen('', 800, 450, true,"PopUpEmployee");
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
		      	frm.sign_plndman_id.value 		= obj.epid;
		      	frm.sign_plndman_nm.value 		= obj.cn;
		      	//frm.sign_plndman_search_nm.value= obj.cn;
		      	frm.sign_plndman_search_nm.value= '';
		      	frm.sign_plnd_dept_nm.value 	= obj.department;
		      	frm.sign_plndman_jikgup_nm.value = obj.title;
		      	
		      	$('#plndman').html('');
		      	$('#plndman').append('&nbsp;&nbsp;' + obj.cn+'/'+obj.title+'/'+obj.department);
		      	
		      } else if(srch_user_target=="contract"){
		      	frm.cntrt_respman_id.value 			= obj.epid;
		      	frm.cntrt_respman_nm.value 			= obj.cn;
		      	//frm.cntrt_respman_search_nm.value	= obj.cn;
		      	frm.cntrt_respman_search_nm.value	= '';
		      	frm.cntrt_respman_jikgup_nm.value 	= obj.title;
		      	frm.cntrt_resp_dept_nm.value 		= obj.department;
		      	frm.cntrt_resp_dept.value			= obj.departmentnumber;
		      	
		      	$('#respman').html('');
		      	$('#respman').append('&nbsp;&nbsp;'+ obj.cn+'/'+obj.title+'/'+obj.department);
		      } else if(srch_user_target == "appr1"){      //사전승인발의자                                    
				     frm.bfhdcstn_mtnman_id.value 			= obj.epid;		//사전승인발의자 id     
				     frm.bfhdcstn_mtnman_nm.value 			= obj.cn;; 		//발의자 이름           
				     frm.bfhdcstn_mtnman_jikgup_nm.value 	= obj.title;  		//직급              
				     frm.bfhdcstn_mtn_dept_nm.value			= obj.department;	//부서              
				     frm.bfhdcstn_mtnman_email.value		= obj.mail;		//이메일                
				                                                                                    
				     $('#bfhdcstn_mtnman_jikgup_nm_span').html(userInfo.title);                     
				     $('#bfhdcstn_mtn_dept_nm_span').html(userInfo.department);                     
				     $('#bfhdcstn_mtn_email_span').html(userInfo.mail);                             
				                                                                                    
			   }else if(srch_user_target == "MTN"){      //사전승인발의자                                    
				     frm.bfhdcstn_mtnman_id.value 			= obj.epid;		//사전승인발의자 id     
				     frm.bfhdcstn_mtnman_nm.value 			= obj.cn;; 		//발의자 이름           
				     frm.bfhdcstn_mtnman_jikgup_nm.value 	= obj.title;  		//직급              
				     frm.bfhdcstn_mtn_dept_nm.value			= obj.department;	//부서              
				     frm.bfhdcstn_mtnman_email.value		= obj.mail;		//이메일                
				                                                                                    
				     $('#bfhdcstn_mtnman_jikgup_nm_span').html(userInfo.title);                     
				     $('#bfhdcstn_mtn_dept_nm_span').html(userInfo.department);                     
				     $('#bfhdcstn_mtn_email_span').html(userInfo.mail);                             
				                                                                                    
			   }else if(srch_user_target == "APBT"){     //사전승인승인자정	                               
				   frm.bfhdcstn_apbtman_id.value          = obj.epid;		//사전승인승인자정idf
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
		  
		  
		  /*
		   * 팝업오픈
		   */
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
		  
		  /*
		   * 날인자 검색팝업 
		   */
		  function searchSealPerson() {
		  	var frm 		= document.frm;
		  	PopUpWindowOpen('', 800, 450, true, "PopUpSealPerson");
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
		  
		  
		  /**
		  * 계약기본정보 조회
		  */
		  function detailBaseExecution(cnsdreq_id, cntrt_id){
		  		
		  	var frm = document.frm;
		  	 //초기화 : 연계 시 이 부분을 주석처리...
		  	$('input[name=cnsdreq_id]').val("");
		  	$('input[name=cntrt_id]').val("");
		  	
		      //설정
		      $('#cnsdreq_id').val(cnsdreq_id);
		      $('#cntrt_id').val(cntrt_id);
		      
		      var options = {
		      		url: "<c:url value='/clm/manage/execution.do?method=detailContract' />",
		      		type: "post",
		      		async: false,
		      		dataType: "html",
		      			
		      		success: function(data){
		      			$("#contract-view").html("");
		      			$("#contract-view").html(data);
		      			
		      			//계약상세내역 잘라내기 붙이기...
		      			$('#contract-detail').html("");
		      			$('#contract-cnclsnInfo').html("");
		      			$('#contract-cnclsnInfo').html("");
		      			$('#contract-relation').html("");
		      			$('#contract-detail').append($('#contract-detail-content'));
		      			$('#contract-cnclsnInfo').append($('#contract-cnclsnInfo-content'));
		      			$('#contract-cnclsnInfo').append($('#contract-cnclsnIns-content'));
		      			$('#contract-relation').append($('#contract-relation-content'));
		      			$('#contract-detail-content').attr("style","display:none");
		      			$('#contract-cnclsnInfo-content').attr("style","display:none");
		      			$('#contract-cnclsnIns-content').attr("style","display:none");
		      			$('#contract-relation-content').attr("style","display:none");
		      			
		  				//이행관리 탭 활성화
		  				$('#executionTab li').removeClass('on');
		  				$('#executionTab li:eq(4)').addClass('on');
		      		}
		      }
		      $("#frm").ajaxSubmit(options);
		      
		      //attachPage();
		  }
		  
		  /**
			  * 계약기본정보 조회
			  */
			  function detailBaseExecution2(cntrt_id){
			  		
			  	var frm = document.frm;
			  	 //초기화 : 연계 시 이 부분을 주석처리...
			  	//$('input[name=cnsdreq_id]').val("");
			  	$('input[name=cntrt_id]').val("");
			  	
			      //설정
			      //$('#cnsdreq_id').val(cnsdreq_id);
			      $('#cntrt_id').val(cntrt_id);
			      
			      var options = {
			      		url: "<c:url value='/clm/manage/execution.do?method=detailContract' />",
			      		type: "post",
			      		async: false,
			      		dataType: "html",
			      			
			      		success: function(data){
			      			$("#contract-view").html("");
			      			$("#contract-view").html(data);
			      			
			      			//계약상세내역 잘라내기 붙이기...
			      			$('#contract-detail').html("");
			      			$('#contract-cnclsnInfo').html("");
			      			$('#contract-cnclsnInfo').html("");
			      			$('#contract-relation').html("");
			      			$('#contract-detail').append($('#contract-detail-content'));
			      			$('#contract-cnclsnInfo').append($('#contract-cnclsnInfo-content'));
			      			$('#contract-cnclsnInfo').append($('#contract-cnclsnIns-content'));
			      			$('#contract-relation').append($('#contract-relation-content'));
			      			$('#contract-detail-content').attr("style","display:none");
			      			$('#contract-cnclsnInfo-content').attr("style","display:none");
			      			$('#contract-cnclsnIns-content').attr("style","display:none");
			      			$('#contract-relation-content').attr("style","display:none");
			      			
			  				//이행관리 탭 활성화
			  				$('#executionTab li').removeClass('on');
			  				$('#executionTab li:eq(4)').addClass('on');
			      		}
			      }
			      $("#frm").ajaxSubmit(options);
			      
			      //attachPage();
			  }
		  
		  
			  /**
			  * 권한에따른 버튼제어
			  */
			  function listContractRole(){
			  	//버튼제어
			  	//$clone = $('#contract-btn-content').clone();
			  	$('#contract-btnList').html("");
			  	//$('#contract-btnList2').html("");
			  	$('#contract-btnList').html($('#contract-btn-content'));
			  	//$('#contract-btnList2').html($clone);
			  	//이행 버튼 제어
			  	if("<c:out value='${contractCommand.session_user_id}'/>"!=frm.reqman_id.value){
			  		$('#rowAddDel').empty();
			  		$('#rowAddDel3').empty();
			  	}
			  }

</script>

<script language="javascript">
$(document).ready(function(){	
	// 2012.02.23 만료사전알림일의 라디오버튼 초기화 added by hanjihoon
	
	var frm = document.frm;
	var endday = $("#cntrtperiod_endday").val();
	
	//alert("endday = "+endday);
	//신성우 주석처리 2014-03-31
	/*
	if(endday == undefined){
		
	} else {
	
		if($("#exprt_notiday").val() == shiftTime($("#cntrtperiod_endday").val().replace(/-/g, ""), 0,0,-30)){
			$(':radio[name="notiday_before"]:radio[value="30"]').attr("checked", true);
		}else if($("#exprt_notiday").val() == shiftTime($("#cntrtperiod_endday").val().replace(/-/g, ""), 0,0,-60)){
			$(':radio[name="notiday_before"]:radio[value="60"]').attr("checked", true);
		}else{
			$(':radio[name="notiday_before"]:radio[value="0"]').attr("checked", true);
		}
	}*/
});
</script>

</head>
<body onLoad="init();">
<div id="wrap">
	<!-- container -->
	<div id="container">
		<form:form name="frm" id="frm" method="post" autocomplete="off">
		<input type="hidden" name="method" id="method" value="">
		<input type="hidden" name="menu_id" id="menu_id" value="<c:out value='${command.menu_id}'/>" />
		<input type="hidden" name="curPage" id="curPage" value="<c:out value='${command.curPage}'/>" />
		<input type="hidden" name="status" id="status" value="<c:out value='${lomRq.status}'/>" />
		<input type="hidden" name="submit_status" id="submit_status" />
		<!-- 나모 웹 에디터 관련 -->
		<!--  //입력일 때-->				
		
		<input type="hidden" name="srch_user_cntnt_type" value=""/>
		<input type="hidden" name="srch_user_cntnt" value=""/>
		<input type="hidden" name="srch_user_cntnt_target" value=""/>
		
	    <!-- 첨부파일정보 -->
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
	     
	   <input type="hidden" name="dmstfrgn_gbn" id="dmstfrgn_gbn" value="<c:out value='${lomRq.dmstfrgn_gbn}'/>">
	   <input type="hidden" name="C03204_pcid_cp" id="C03204_pcid_cp"/>	   
	   <!-- 관련자 데이타 설정  -->
	   <input type="hidden" name="chose_client" id="chose_client" />
	
	   <!-- 기존 데이터가 있을 경우에 데이터 삭제해주기 위해서. -->
	   <input type="hidden" name="cntrtOppntChk" id="cntrtOppntChk" value="" />

	   <!-- 임직원조회 팝업 시 리턴값 사전승인발의자인지 사전승인승인자정보 인지 구분하기 위해 -->
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
	   <!-- 재의뢰 OR 최종본 의뢰시 사용  -->
	   <% //if("forwardReq".equals(lomRq.get("status")) || "forwardLast".equals(lomRq.get("status"))){ %>
	   <input type="hidden" name="prev_cnsdreq_id" id ="prev_cnsdreq_id" value="<c:out value='${lomRq.prev_cnsdreq_id}'/>" />	   
	   <input type="hidden" name="prev_vc_respman_apnt_yn" id ="prev_vc_respman_apnt_yn" value="<c:out value='${lomRq.vc_respman_apnt_yn}'/>" />
	   <input type="hidden" name="mn_cnsd_dept" id ="mn_cnsd_dept" value="<c:out value='${lomRq.mn_cnsd_dept}'/>" />
	   <input type="hidden" name="vc_cnsd_dept" id ="vc_cnsd_dept" value="<c:out value='${lomRq.vc_cnsd_dept}'/>" />
	   	   
	   <input type="hidden" name="plndbn_req_yn" id ="plndbn_req_yn" value="<c:out value='${lomRq.plndbn_req_yn}'/>" />
	   <% //}%>
	   <input type="hidden" name="mod_cntrt_id" id ="mod_cntrt_id" value="<c:out value='${lomRq.cntrt_id}'/>" />
	   <input type="hidden" name="req_reg_id" id ="req_reg_id" value="<c:out value='${lomRq.req_reg_id}'/>" />
	   <input type="hidden" name="fileYn" id="fileYn" value="N" />
	   <input type="hidden" name="orgnz_nm_abbr" id="orgnz_nm_abbr" value="<c:out value='${command.session_blngt_orgnz_nm_abbr}'/>" />
	   
	   <input type="hidden" name="pageGbn" id="pageGbn" />
	   
		<!-- location -->
		<div class="location">
			<img src="<%=IMAGE %>/icon/ico_home.gif" border="0" alt="Home"> <spring:message code="clm.page.msg.common.contractManagement" /> > <spring:message code="clm.page.msg.manage.aftConclReg" /> > <strong><spring:message code="clm.page.msg.manage.aftConclWrite" /></strong>
		</div>
		<!-- //location -->
		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.msg.manage.aftConclReg" /></h3>
		</div>
		<!-- //title-->
		<!-- content -->
		<div id="content">			
			
			<div class="t_titBtn">
				<!-- title -->
				<div class="title_basic">
					<h4><c:out value='${command.req_status_title}'/> <spring:message code="clm.page.msg.manage.reqInf" /> </h4>
				</div>
				<!-- //title -->				
				<!-- button -->
				<div class="btn_wrap_t02">
					<!-- <span class="btn_all_w"><span class="preview"></span><a href="javascript:considerationPreview();">미리보기</a></span> -->					
					<span class="btn_all_w"><span class="tsave"></span><a href="javascript:insertConsideration('save');"><spring:message code="clm.page.msg.manage.tmpSave" /></a></span>
					<span class="btn_all_w"><span class="check2"></span><a href="javascript:insertConsideration('req');"><spring:message code="clm.page.msg.manage.approve" /></a></span>					
				    <span class="btn_all_w"><span class="list"></span><a href="javascript:forwardConsideration('list');"><spring:message code="clm.page.msg.manage.list" /></a></span>
				</div>
				<!-- //button -->
			</div>
			<!-- toptable -->
			<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
				<colgroup>				
					<col width="12%" />
					<col width="30%" />
					<col width="13%" />
					<col width="16%" />
					<col width="13%" />
					<col width="16%" />					 
				</colgroup>
				<tr>
					<th><spring:message code="clm.page.msg.manage.revReqTitle" /></th>
				  <td colspan="5"><input type="text" name="req_title" name="id" alt="<spring:message code="clm.page.msg.manage.revReqTitle" htmlEscape="true" />" required value="<c:out value="${lomRq.req_title}" />" class="text_full" maxlength="75" /></td>
				</tr>
				<tr>
					<th><spring:message code="clm.page.msg.manage.reqPerson" /></th>
					<td><input type="hidden" name="reqman_id" id="reqman_id" alt="<spring:message code="clm.page.msg.manage.reqPerson" htmlEscape="true" />" value="<c:out value="${lomRq.reqman_id}" />" />
						<input type="hidden" name="reqman_nm" id="reqman_nm"  value="<c:out value="${lomRq.reqman_nm}" />" />
						<input type="hidden" name="req_dept_nm" id="req_dept_nm" alt="<spring:message code="clm.page.msg.common.department" htmlEscape="true" />" value="<c:out value="${lomRq.req_dept_nm}" />" />
						<c:out value="${lomRq.reqman_nm}" />/
						<c:out value="${lomRq.reqman_jikgup_nm}" />/
						<c:out value="${lomRq.req_dept_nm}" />
					</td>					
					<th><spring:message code="clm.page.msg.manage.requDate" /></th>
					<td><input type="hidden" name="req_dt" alt="<spring:message code="clm.page.msg.manage.requDate" htmlEscape="true" />" value="<c:out value="${lomRq.req_dt}" />" />
						<c:out value="${lomRq.req_dt}" /></td>
					<th><spring:message code="clm.page.msg.manage.replyReqDate" /></th>
					<td><input type="text" name="re_demndday" id="re_demndday" required alt="<spring:message code="clm.page.msg.manage.replyReqDate" htmlEscape="true" />" value="<c:out value="${lomRq.re_demndday}" />" class="text_calendar02"/></td>
				</tr>
				<tr class="lineAdd">
					<th><spring:message code="clm.page.msg.manage.relPerson" /></th>
					<td colspan="5"><span id="id_trgtman_nm">
					<% for(int j=0;j<listCa.size();j++){%>
					<% ListOrderedMap lom = (ListOrderedMap)listCa.get(j);%>
					<% if((j !=0 && j !=1) && (j % 2 == 0 )){%><br/><%}%>
					<% if(j != 0 && (j % 2 !=0 )){%>,<% }%>	
					<input type="hidden" name="arr_demnd_seqno" id="arr_demnd_seqno" value="<%=lom.get("demnd_seqno") %>" /><input type="hidden" name="arr_trgtman_id" id="arr_trgtman_id" value="<%=lom.get("trgtman_id") %>" /><input type="hidden" name="arr_trgtman_nm" id="arr_trgtman_nm" value="<%=lom.get("trgtman_nm") %>" /><input type="hidden" name="arr_trgtman_jikgup_nm" id="arr_trgtman_jikgup_nm" value="<%=lom.get("trgtman_jikgup_nm") %>" /><input type="hidden" name="arr_trgtman_dept_nm" id="arr_trgtman_dept_nm" value="<%=lom.get("trgtman_dept_nm") %>" />
					<%=lom.get("trgtman_nm") %>/<%=lom.get("trgtman_jikgup_nm") %>/<%=lom.get("trgtman_dept_nm") %>					
					<% }%></span>
					<div class="tR">
					<a href="javascript:openChooseClient();"><img src="<%=IMAGE %>/btn/btn_wrap_fr_reference.gif" alt="<spring:message code="clm.page.msg.manage.add" htmlEscape="true" />" /></a>
					</div>
				</tr>						
				<tr>
					<th class="borTz02"><spring:message code="clm.page.msg.manage.conclRegRsn" /></th>
					<td colspan="5"><!-- <img src="<%=IMAGE %>/icon/texteditor.jpg" alt="<spring:message code="clm.page.msg.manage.texteditor" htmlEscape="true" />" /> //-->
					<input type="hidden" name="cnsd_demnd_cont" id="cnsd_demnd_cont" alt="<spring:message code="clm.page.msg.manage.reqContent" htmlEscape="true" />" value="<c:out value="${lomRq.cnsd_demnd_cont}" />" />
					<input type="hidden" name="body_mime" id="body_mime" value="<c:out value="${lomRq.cnsd_demnd_cont}" />" />		
					<%@ include file="/WEB-INF/jsp/secfw/common/NamoEditInclude2.jspf"%></td>
				</tr>
				<%	if("Y".equals(lomRq.get("plndbn_req_yn"))) {		//PREV_CNSDREQ_ID  이전검토아이디 %>
				<!-- 최종본의뢰시 표시 -->				
				<tr>
					<th class="borTz02"><spring:message code="clm.page.msg.manage.chgRevVerYn" /> </th><!-- lastbn_chge_yn -->
					<td colspan="5">
					    <select name="lastbn_chge_yn" id="lastbn_chge_yn">
						    <option value="Y" <% if("Y".equals(lomRq.get("lastbn_chge_yn"))){out.println(" selected");}%>>YES</option>
						    <option value="N" <% if("N".equals(lomRq.get("lastbn_chge_yn"))){out.println(" selected");}%>>NO</option>
					     </select>  <spring:message code="clm.page.msg.manage.announce182" />
					</td>
					
				</tr>
				<tr>
					<th class="borTz02"><spring:message code="clm.page.msg.manage.rsnBrkChange" /></th>
					<td colspan="5"><textarea name="plndbn_chge_cont" id="plndbn_chge_cont" alt="<spring:message code="clm.page.msg.manage.rsnBrkChange" htmlEscape="true" />" cols="30" rows="4" class="text_area_full"><c:out value="${lomRq.plndbn_chge_cont}" /></textarea></td>
				</tr>
				<!-- 최종본의뢰시 표시 ** -->
				<%	}													%>
			</table>
			<div style="display:none;"><iframe src="<c:url value='/clm/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="60px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe></div>
		
			<!-- //top table -->
			<!-- table001 -->
			<!-- //able001 -->
			<!-- title -->
		  <div class="title_basic">
				<h4><spring:message code="clm.page.msg.manage.bscInfCont" /> <img src="<%=IMAGE %>/btn/btn_up.gif" alt="up" onclick="tit_Toggle(this,'tr_down02');"/></h4>
			</div>
			<!-- //title -->
			<!-- tab01 -->
			<div class="t_titBtn">
			<ul id="tab_title" class="tab_basic03">
			<%	if("forwardInsert".equals(lomRq.get("status"))){ 	//최초의뢰건 입력%>
				<li id="tab_li1" class="on"><a href="javascript:titleTabClick('1','');"><spring:message code="clm.page.msg.common.contract" /></a></li>
			<% }else{%>
				<c:forEach var="list" items="${listDc}">
					<c:choose>
					<c:when test="${list.rn=='1'}">
			          		<li id="tab_li<c:out value='${list.rn}'/>" class="on"><a href="javascript:titleTabClick('<c:out value='${list.rn}'/>','<c:out value='${list.cntrt_id}'/>');">	<c:out value='${list.tab_nm}'/></a></li>
					</c:when>
					<c:otherwise>
							<li id="tab_li<c:out value='${list.rn}'/>"><a href="javascript:titleTabClick('<c:out value='${list.rn}'/>','<c:out value='${list.cntrt_id}'/>');">
								<c:out value='${list.tab_nm}'/>
							</a></li>
					</c:otherwise>	
					</c:choose>				
				</c:forEach>
			<% }%>										
			</ul>
			<%	if(null == lomRq.get("prev_cnsdreq_id") || "" == lomRq.get("prev_cnsdreq_id")){ %>
			<!-- <div class="btn_wrap_t03">		
				<span class="btn_all_w"><span class="add"></span><a href="javascript:contractAction('ADD');"><spring:message code="clm.page.msg.manage.addCont" /></a></span>
				<span class="btn_all_w"><span class="delete"></span><a href="javascript:contractAction('deleteContract');"><spring:message code="clm.page.msg.manage.deleteCont" /></a></span>
			</div> -->
			<%}%>
			</div>
			<!-- 두줄라인 추가 --><div class="tal_topLine"></div><!-- //두줄라인 추가 -->
			<!-- middle table -->
			<div id="tab_contents"></div>
			<!-- //tab_content -->
			<!-- 이력정보 -->
			<div id="contractHis-list"></div>
			<!-- //이력정보 -->
			
			<%	if("forwardInsert".equals(lomRq.get("status")) || ("forwardModify".equals(lomRq.get("status")) && "".equals(lomRq.get("prev_cnsdreq_id")))    ) {	%>
			<!-- 결재자정보 -->
			<div id="approval-info">
			<div class="title_basic">
  				<h5 class="ntitle05"><spring:message code="clm.page.msg.manage.apprInf" /></h5>
			</div>
			<table cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
					<col width="12%" />
					<col width="39%" />
					<col width="10%" />
					<col width="16%" />
					<col width="13%" />
					<col width="10%" />  
				</colgroup>
				<tr class="lineAdd">
					<th><spring:message code="clm.page.msg.manage.approver" /></th>
					<td>
						<input type="hidden" name="approvalman_id" id="approvalman_id" value="" />
						<input type="hidden" name="approvalman_nm" id="approvalman_nm" value="" />
						<input type="text"   name="approvalman_search_nm" id="approvalman_search_nm" value="" style="width:120px" class="text_search" onKeyPress="if(event.keyCode==13) {searchEmployee2('appr');return false;}"/><a href="javascript:searchEmployee2('appr');"><img src="<%=IMAGE %>/icon/ico_search.gif" class="cp" alt="search" /></a> <spring:message code="clm.page.msg.manage.aboveSenior" /></td>					
					</td>					
					<th><spring:message code="clm.page.msg.manage.level" /></th>
					<td>
						<input type="hidden" name="approvalman_jikgup_nm" value="" />
						<span id="approvalman_jikgup_nm"></span>
					</td>
					<th><spring:message code="clm.page.msg.manage.deptName" /></th>
					<td>
						<input type="hidden" name="approvalman_dept_nm" value="" />
						<span id="approvalman_dept_nm"></span>
					</td>
				</tr>			
			</table>
			</div>
			</div>
			<% } %>
		<!-- //content -->
		<!-- button -->
		<!-- 체결품의정보시작 -->
		<div class="title_basic">
			<h5 class="ntitle05"><spring:message code="clm.page.title.contract.conclusion.detail.title"/> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'tab_contents_sub_conts3');"/></h5>
		</div>
		<table id="tab_contents_sub_conts3" cellspacing="0" cellpadding="0" border="0" class="table-style01">
		<colgroup>
			<col width="16%" />
			<col width="16%" />
			<col width="16%" />
			<col width="16%" />
			<col width="16%" />
			<col />
		</colgroup>
		<tr>
			<th><spring:message code="clm.page.field.contract.consultation.detail.methodgbn"/></th>
			<td colspan="2">
				<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02101" checked onClick="javascript:setSealMethod(this.value);"><spring:message code="clm.page.field.contract.consultation.detail.seal"/></input>
				<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02102"  onClick="javascript:setSealMethod(this.value);"><spring:message code="clm.page.field.contract.consultation.detail.sign"/></input>
			</td>
<%-- 신성우 주석처리 2014-03-31			<th><spring:message code="clm.page.field.contract.consultation.detail.expirenotifyday"/></th>
			<td colspan="2">
				<input type="radio" name="notiday_before" id="notiday_before" class="radio" value="30" onClick="javascript:getBeforeDay(30);"><spring:message code="clm.page.msg.manage.30dayBef" /></input>
				&nbsp;<input type="radio" name="notiday_before" id="notiday_before" class="radio" value="60" onClick="javascript:getBeforeDay(60);"><spring:message code="clm.page.msg.manage.60dayBef" /></input>
				&nbsp;<input type="radio" name="notiday_before" id="notiday_before" class="radio" value="0" onClick="javascript:$('#exprt_notiday').val('');"><spring:message code="clm.page.msg.manage.sellevel" /></input>
				<input type="text" name="exprt_notiday" id="exprt_notiday" value="<c:out value='${contractLom.exprt_notiday}'/>"  class="text_calendar02" disabled required alt="<spring:message code="clm.page.field.contract.consultation.detail.expirenotifyday"/>" />
			</td>
--%>
		</tr>
		<!-- 날인 담당자 시작 -->
		<tr id="seal-tr" style="display:">
			<th><spring:message code="clm.page.field.contract.consultation.detail.sealffmtmannm"/></th><!-- 날인담당자 -->
		  	<td colspan="5">
		  		<input type="hidden" name="seal_ffmtman_id" id="seal_ffmtman_id" value="<c:out value='${contractLom.seal_ffmtman_id}'/>" /> 
		  		<input type="hidden" name="seal_ffmtman_nm" id="seal_ffmtman_nm" value="<c:out value='${contractLom.seal_ffmtman_nm}'/>" />
		  		<input type="hidden" name="seal_ffmt_dept_cd" id="seal_ffmt_dept_cd" value="<c:out value='${contractLom.seal_ffmt_dept_cd}'/>"/>
			  	<input type="hidden" name="seal_ffmt_dept_nm" id="seal_ffmt_dept_nm" value="<c:out value='${contractLom.seal_ffmt_dept_nm}'/>"/>
			  	<input type="hidden" name="seal_ffmtman_jikgup_nm" id="seal_ffmtman_jikgup_nm" value="<c:out value='${contractLom.seal_ffmtman_jikgup_nm}'/>"/>
		  		<input type="text"   name="seal_ffmtman_search_nm" id="seal_ffmtman_search_nm" value="" style="width:110px" class="text_search" onKeyDown="if(event.keyCode==13){event.returnValue = false;javascript:searchSealPerson();}"/><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchSealPerson();" class="cp" alt="search" />
		  		<span id="ffmtman">&nbsp;&nbsp;<c:out value='${contractLom.seal_ffmtman_nm}'/>/<c:out value='${contractLom.seal_ffmtman_jikgup_nm}'/>/<c:out value='${contractLom.seal_ffmt_dept_nm}'/></span>
		  	</td>
		 </tr>
		 <tr id="sign-tr" style="display:none">
		  	<th><spring:message code="clm.page.field.contract.consultation.detail.signplannernm"/></th><!-- 서명예정자 -->
			<td colspan="5">
		  		<input type="hidden" name="sign_plndman_id" id="sign_plndman_id" value="<c:out value='${contractLom.sign_plndman_id}'/>" />
		  		<input type="hidden" name="sign_plndman_nm" id="sign_plndman_nm" value="<c:out value='${contractLom.sign_plndman_nm}'/>" />
		  		<input type="hidden" name="sign_plndman_jikgup_nm" id="sign_plndman_jikgup_nm" value="<c:out value='${contractLom.sign_plndman_jikgup_nm}'/>"/>
		  		<input type="hidden" name="sign_plnd_dept_nm" id="sign_plnd_dept_nm" value="<c:out value='${contractLom.sign_plnd_dept_nm}'/>"/>
		  		<input type="text" name="sign_plndman_search_nm" id="sign_plndman_search_nm" value="" style="width:110px" class="text_search" alt="<spring:message code='clm.page.field.contract.consultation.detail.signplannernm'/>" onKeyDown="if(event.keyCode==13){event.returnValue = false;javascript:searchEmployee2('sign');}"/><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchEmployee2('sign');" class="cp" alt="1" />
		  		<span id="plndman">&nbsp;&nbsp;<c:out value='${contractLom.sign_plndman_nm}'/>/<c:out value='${contractLom.sign_plndman_jikgup_nm}'/>/<c:out value='${contractLom.sign_plnd_dept_nm}'/></span>
			</td>
		</tr>
		 
		<!-- 날인 담당자 끝 -->
		<tr id="sign-tr1" style="display:">
			<th><spring:message code="clm.page.field.contract.consultation.detail.conclusionday"/></th>
		  	<td colspan="5">
				<input type="text" name="cnclsn_plndday1" id="cnclsn_plndday1" value="<c:out value='${contractLom.cnclsn_plndday}'/>"  class="text_calendar02" required alt="<spring:message code='clm.page.field.contract.consultation.detail.conclusionday'/>"/>
		  	</td>
	  	</tr>
		<tr>
		  	<th><spring:message code="clm.page.field.contract.consultation.detail.contractrespmannm"/></th>
		  	<td colspan="5">
		  		<input type="hidden" name="cntrt_respman_id" id="cntrt_respman_id" value="<c:out value='${contractLom.cntrt_respman_id}'/>" />
			  	<input type="hidden" name="cntrt_respman_nm" id="cntrt_respman_nm" value="<c:out value='${contractLom.cntrt_respman_nm}'/>" />
				<input type="hidden" name="cntrt_respman_jikgup_nm" id="cntrt_respman_jikgup_nm" value="<c:out value='${contractLom.cntrt_respman_jikgup_nm}'/>"/>
				<input type="hidden" name="cntrt_resp_dept" id="cntrt_resp_dept" value="<c:out value='${contractLom.cntrt_resp_dept}'/>"/>
				<input type="hidden" name="cntrt_resp_dept_nm" id="cntrt_resp_dept_nm" value="<c:out value='${contractLom.cntrt_resp_dept_nm}'/>"/>
				<input type="text" name="cntrt_respman_search_nm" id="cntrt_respman_search_nm" value=""  style="width:110px" class="text_search"  alt="<spring:message code='clm.page.field.contract.consultation.detail.contractrespmannm'/>" onKeyDown="if(event.keyCode==13){event.returnValue = false;javascript:searchEmployee2('contract');}"/><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchEmployee2('contract');" class="cp" alt="search"/>
				<span id="respman">&nbsp;&nbsp;<c:out value='${contractLom.cntrt_respman_nm}'/>/<c:out value='${contractLom.cntrt_respman_jikgup_nm}'/>/<c:out value='${contractLom.cntrt_resp_dept_nm}'/></span>
				<br><br><span><spring:message code="clm.msg.alert.contract.consultation.respmannotice"/></span>
		  	</td>
		</tr>
		<c:if test="${command.user_role=='RD01'}">
			<tr>
			  	<th><spring:message code="clm.page.field.contract.consultation.detail.agreeday"/></th>
			  	<td colspan="2">
			  		<c:out value='${contractLom.agreeday}'/>
			  	</td>
			  	<th><spring:message code="clm.page.field.contract.consultation.detail.agreeyn"/></th>
			  	<td colspan="2"><c:out value='${contractLom.agree_yn}'/></td>
			</tr>
			<tr>
				<th><spring:message code="clm.page.field.contract.consultation.detail.agreecause"/></th>
				<td colspan="5">
					<c:out value='${contractLom.agree_cause}'/>
				</td>
			</tr>
		</c:if>	
	</table>
	<!-- 체결품의정보end -->
		<div id="approval-info">
			<div class="title_basic">
  				<h5 class="ntitle05"><spring:message code="clm.page.msg.manage.exeInf" /></h5>
			</div>
	    </div>
		<div id="consultation-execinfo-list"></div>
		    <div class="t_titBtn">
      		<div class="btn_wrap_c">
					<!-- <span class="btn_all_w"><span class="preview"></span><a href="javascript:considerationPreview();">미리보기</a></span> -->					
					<span class="btn_all_w"><span class="tsave"></span><a href="javascript:insertConsideration('save');"><spring:message code="clm.page.msg.manage.tmpSave" /></a></span>
					<span class="btn_all_w"><span class="check2"></span><a href="javascript:insertConsideration('req');"><spring:message code="clm.page.msg.manage.approve" /></a></span>					
				    <span class="btn_all_w"><span class="list"></span><a href="javascript:forwardConsideration('list');"><spring:message code="clm.page.msg.manage.list" /></a></span>
				</div>
				</div>
				<!-- //button -->
		<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- // footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</form:form>
	</div>
<!-- //container -->
</div>
<%if("forwardReq".equals(lomRq.get("status")) || "forwardLast".equals(lomRq.get("status"))){		//재의뢰 or 최종본의뢰%>
<SCRIPT language="javascript" FOR="wec" EVENT="OnInitCompleted()">
//재의뢰 or 최종본의뢰	

	//document.wec[0].Value ="<HTML><HEAD>" + "<TITLE></TITLE><META http-equiv=Content-Type content='text/html; charset=utf-8'><META content=ActiveSquare name=GENERATOR></HEAD><BODY>" + document.frm.body_mime.value+"</BODY></HTML>";
	document.wec[0].Value =document.frm.cnsd_demnd_cont.value;
	document.wec[0].SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');	
	document.wec[0].SetDefaultFontSize("9");
 	document.wec[0].EditMode = 1;
 	
 	//document.wec[1].Value ="<HTML><HEAD>" + "<TITLE></TITLE><META http-equiv=Content-Type content='text/html; charset=utf-8'><META content=ActiveSquare name=GENERATOR></HEAD><BODY>" + document.frm.body_mime1.value+"</BODY></HTML>";
 	document.wec[1].Value = document.frm.pshdbkgrnd_purps.value;
	document.wec[1].SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
	document.wec[1].SetDefaultFontSize("9");
	document.wec[1].EditMode = 1;
</SCRIPT>
<%}else if("forwardModify".equals(lomRq.get("status"))){				//수정%>
<SCRIPT language="javascript" FOR="wec" EVENT="OnInitCompleted()">
//수정
 	//document.wec[0].Value ="<HTML><HEAD>" + "<TITLE></TITLE><META http-equiv=Content-Type content='text/html; charset=utf-8'><META content=ActiveSquare name=GENERATOR></HEAD><BODY>" + document.frm.body_mime.value+"</BODY></HTML>"; 	
 	document.wec[0].Value =document.frm.cnsd_demnd_cont.value;
	document.wec[0].SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');	
	document.wec[0].SetDefaultFontSize("9");
 	document.wec[0].EditMode = 1;
 	
 	//document.wec[1].Value ="<HTML><HEAD>" + "<TITLE></TITLE><META http-equiv=Content-Type content='text/html; charset=utf-8'><META content=ActiveSquare name=GENERATOR></HEAD><BODY>" + document.frm.body_mime1.value+"</BODY></HTML>";
 	document.wec[1].Value = document.frm.pshdbkgrnd_purps.value;
	document.wec[1].SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
	document.wec[1].SetDefaultFontSize("9");
	document.wec[1].EditMode = 1; 
</SCRIPT>
<%}else{ %>
<SCRIPT language="javascript" FOR="wec" EVENT="OnInitCompleted()">
	//document.wec[0].Value ="<HTML><HEAD>" + "<TITLE></TITLE><META http-equiv=Content-Type content='text/html; charset=utf-8'><META content=ActiveSquare name=GENERATOR>" +"</HEAD><BODY></BODY></HTML>";
	document.wec[0].Value =document.frm.cnsd_demnd_cont.value;	 
	document.wec[0].SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');	
	document.wec[0].SetDefaultFontSize("9");    
	document.wec[0].EditMode = 1; 

	//document.wec[1].Value ="<HTML><HEAD>" + "<TITLE></TITLE><META http-equiv=Content-Type content='text/html; charset=utf-8'><META content=ActiveSquare name=GENERATOR>" +"</HEAD><BODY></BODY></HTML>";
	//pshdbkgrnd_purps
	document.wec[1].Value = document.frm.pshdbkgrnd_purps.value;
	document.wec[1].SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
	document.wec[1].SetDefaultFontSize("9");
	document.wec[1].EditMode = 1;	
	
	
</SCRIPT>
<%} %>
</body>
</html>