<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>
<%@ page import="com.sec.clm.manage.dto.ConsultationForm" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%--
/**
 * 파  일  명 : RegConsideration_d.jsp
 * 프로그램명 : 체결 후 등록 상세
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2012.01
 */
--%>
<% 
		
	ListOrderedMap lomRq = (ListOrderedMap)request.getAttribute("lomRq");		
	ListOrderedMap lomCh = (ListOrderedMap)request.getAttribute("lomCh");
	ArrayList listDc = (ArrayList)request.getAttribute("listDc");
	ArrayList listTs = (ArrayList)request.getAttribute("listTs");	
	ArrayList listRc = (ArrayList)request.getAttribute("listRc");
	ArrayList listCa = (ArrayList)request.getAttribute("listCa");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title" /></title>
<!--2. CSS 추가  --> 
<!--계약관리일 경우  -->
<LINK href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet">
<script language="javascript" src="/script/clms/common.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/excanvas.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="/script/clms/clms_common.js"></script>
<script>
	$(document).ready(function(){	
		//011207-04-011168 
		//의뢰건 드랍 보류 삭제 버튼 컨트롤 
		//$("#id_defer").hide();
		//$("#id_drop").hide();
		//$("#id_delete").hide();
		//$("#id_deleteContract").hide();
		//$("#id_dropContract").hide();		
		//의뢰건 드랍 보류 삭제 버튼 컨트롤
		
		detailContractMaster();
		listMyApprovalExe();
		//initPage();
		//tit_Toggle(this, 'tr_down02'); // 김재원 추가 2011.10.15
	});
	
	function forwardConsultation(arg){
		//detailConsultation
		var frm = document.frm;	
		frm.status.value = arg;
		frm.target = "_self";		 
		frm.action = "<c:url value='/clm/manage/consultation.do?method=detailConsultation&page_gbn=modify' />";
		frm.submit();
	}
	
	
	/**
	* page Action
	*/
	function forwardConsideration(arg){
		var frm = document.frm;
		
		alert("arg = " +arg);
		
		frm.target = "_self";
		frm.pageGbn.value = "registration";
		frm.action = "<c:url value='/clm/manage/consideration.do' />";
		
		if(arg == "save"){	//임시저장 사용 안함
			alert("View X");
		}else if(arg == "req"){		//검토의뢰	사용안함 
			frm.method.value = "prevCnsdReqCopyConsideration";			
		}else if(arg == "last"){	//최종본회신    사용안함		
			frm.method.value = "prevCnsdReqCopyConsideration";			
		}else if(arg == "list"){	//목록			
			frm.method.value = "listManageConsideration";
		}else if(arg == "mod"){	//	수정	화면으로 이동 
			frm.method.value = "forwardModifyConsideration";			
		}else if(arg == "again"){	//재검토 화면으로 이동 
			frm.method.value = "forwardAgainReqConsideration";
		}else if(arg == "detail"){		//상세 화면
			frm.method.value = "detailConsideration";
			
		}
		
		//comLayerPopCenter('ProgressLayer1');
		frm.submit();
		
	}
	
	function prevCnsdReqCopyConsideration(arg){
		var frm = document.frm;
		if(arg == "again"){
			if(!confirm("<spring:message code="clm.page.msg.manage.announce145" />")) return;
			frm.status.value = "forwardReq";
		}else if(arg == "last"){			
			if(!confirm("<spring:message code="clm.page.msg.manage.announce187" />")) return;
			alert("<spring:message code="clm.page.msg.manage.announce002" />");
			
			frm.status.value="forwardLast";	
		}else if(arg == "deferAgain"){
			frm.status.value = "forwardReq";
		}
		var options = {
			url: "<c:url value='/clm/manage/consideration.do?method=prevCnsdReqCopyConsideration' />",
			type: "post",
			dataType: "json",			
			success: function(responseText, statusText) {
				//alert(responseText.cnsdreqId);
				if(responseText.cnsdreqId == undefined){
					alert("<spring:message code="clm.page.msg.manage.announce157" />");
				}else{
					document.frm.cnsdreq_id.value = responseText.cnsdreqId;
					if(arg == "again" || arg == "deferAgain"){  //재검토 의뢰시 재검토 화면 으로 이동 
						forwardConsideration('again');
					}else if(arg == "last"){
						forwardConsideration('mod');	
					}
				}
			}
		}
		$("#frm").ajaxSubmit(options);
  	}
	/**
	  *계약상세 탭 클릭 
	  */
	  function subTitleTabMove(num){
		  
		  if(num==1){						    
		      document.getElementById("tab_contents_sub_conts1").style.display = "block"; 
		      document.getElementById("tab_contents_sub_conts2").style.display = "none";
		      <% if("C04207".equals(lomRq.get("prgrs_status"))){		//최종회신 완료 %>
		    	  //document.getElementById("tab_contents_sub_css3").className = "";
		    	  //document.getElementById("tab_contents_sub_conts3").style.display = "none";
		      <%}%>
		      
		      document.getElementById("tab_contents_sub_css1").className = "on";
		      document.getElementById("tab_contents_sub_css2").className = "";	
		      
		      document.getElementById("tab_contents_sub_conts4").style.display = "none";
		      document.getElementById("tab_contents_sub_css4").className = "";
		      
	      }else if(num==2){	
	    	  document.getElementById("tab_contents_sub_conts1").style.display = "none";
		      document.getElementById("tab_contents_sub_conts2").style.display = "block";
		      
		      document.getElementById("tab_contents_sub_css1").className = "";
		      document.getElementById("tab_contents_sub_css2").className = "on";
		      
		      
		      <% if("C04207".equals(lomRq.get("prgrs_status"))){		//최종회신 완료 %>
		    	  //document.getElementById("tab_contents_sub_conts3").style.display = "none";
		    	  //document.getElementById("tab_contents_sub_css3").className = "";
		      <%}%>
		      document.getElementById("tab_contents_sub_conts4").style.display = "none";
		      document.getElementById("tab_contents_sub_css4").className = "";
	      }else if(num==4){
	    	  document.getElementById("tab_contents_sub_conts1").style.display = "none";
		      document.getElementById("tab_contents_sub_conts2").style.display = "none";
		      
		      document.getElementById("tab_contents_sub_css1").className = "";
		      document.getElementById("tab_contents_sub_css2").className = "";
		      
		      document.getElementById("tab_contents_sub_conts4").style.display = "block";
		      document.getElementById("tab_contents_sub_css4").className = "on";
		      
		      
		      <% if("C04207".equals(lomRq.get("prgrs_status"))){		//최종회신 완료 %>
		    	  //document.getElementById("tab_contents_sub_conts3").style.display = "none";
		    	  //document.getElementById("tab_contents_sub_css3").className = "";
		      <%}%>
	      
	      }else{
	    	  document.getElementById("tab_contents_sub_conts1").style.display = "none";
		      document.getElementById("tab_contents_sub_conts2").style.display = "none";
		      
		      document.getElementById("tab_contents_sub_css1").className = "";
		      document.getElementById("tab_contents_sub_css2").className = "";
		      
		      
		      <% if("C04207".equals(lomRq.get("prgrs_status"))){		//최종회신 완료 %>
		    	  //document.getElementById("tab_contents_sub_conts3").style.display = "block";
		    	  //document.getElementById("tab_contents_sub_css3").className = "on";
		      <%}%>
		      document.getElementById("tab_contents_sub_conts4").style.display = "none";
		      document.getElementById("tab_contents_sub_css4").className = "";
	    	  
	      }	
	  }
	
	  /**
	  * 연관계약 정보 reload
	  */
	  function listRelationContract(){
		  var frm = document.frm;
		  
		  var options = {
  				url: "<c:url value='/clm/manage/consideration.do?method=listRelationContract' />",
  				type: "post",
  				async: false,
  				dataType: "html",
  				success: function (data) {  
  					if(data.indexOf('<html>') == -1){ 
	  					$("#trRelationContract").next().remove();
	  					$("#trRelationContract").after(data);
  					}
  				}
  			};
	  		$("#frm").ajaxSubmit(options);
	  }
	
	  /**
	  *계약탭 클릭
	  */
	  function titleTabMove(tab,cntrt_id){
		  
		var tab_cnt = document.frm.tab_cnt.value;
		document.frm.cntrt_id.value = cntrt_id;
		//alert(" 계약 "+document.frm.cntrt_id.value + " 로 이동 ~");	
		
		for(i=1; i<=tab_cnt; i++){			
			if(tab==i){
				document.getElementById("tab_li"+i+"").className = "on";
		    }else{
			    document.getElementById("tab_li"+i+"").className = "";
		    }		  
		}
		
		detailContractMaster();	
	  }
	
	/**
	* 계약검토의뢰등록 폼  inner View 
	*/
	function detailContractMaster(){
		var options = {
			url: "<c:url value='/clm/manage/consideration.do?method=detailContractMaster' />",
			type: "post",
			async: false,
			dataType: "html",
			success: function (data) {
				$("#tab_contents").html("");
				$("#tab_contents").html(data);				
								
				contractHisList();
				
				tit_Toggle(this, 'tr_down02'); 
			}
		}
		$("#frm").ajaxSubmit(options);
	}
	
	/**
  	* 의뢰건 상단 버튼 Action 의뢰 보류/ 드랍 /삭제 
  	*/
  	function actionConsideration(arg){
		var msg ="";
  		//defer -보류 , drop -Drop ,delete - 삭제
  		if("dropRequest" == arg){
  			msg = "<spring:message code="clm.page.msg.manage.announce206" />";  			
  		}else if("deferRequest" == arg){
  			msg = "<spring:message code="clm.page.msg.manage.announce126" />";  			
  		}else if("deleteRequest" == arg){		//의뢰건 삭제 
  			msg = "<spring:message code="clm.page.msg.manage.announce120" />";
  		}else if("dropContract" == arg){		//의뢰건 삭제 
  			msg = "<spring:message code="clm.page.msg.manage.announce108" />";
  		}else if("deferCancelRequest" == arg){		//의뢰건 보류 해제
  			msg = "<spring:message code="clm.page.msg.manage.announce127" />";
  		}else if("saveCancelRequest" == arg){	//작성중일때 (재의뢰시) 의뢰 취소 
  			msg = "<spring:message code="clm.page.msg.manage.announce142" />";
  		}
  		
  		if("deleteRequest" == arg){
  			if(confirm(msg)){ 
  				processConsideration(arg);
  			}
		}else if("dropRequest" == arg || "deferRequest" == arg || "dropContract" == arg){
			if(confirm(msg)){
				opnnConsideration(arg);
			}
		}else if("deferCancelRequest" == arg){
			if(confirm(msg)){ 
  				processConsideration(arg);
  			}
		}else if("saveCancelRequest" == arg){
			if(confirm(msg)){ 
  				processConsideration(arg);
  			}
		}
  	}
  	/**
  	* 
  	*/
  	function processConsideration(arg){
		var frm = document.frm;
		frm.submit_status.value = arg;
		
		var options = {
			url: "<c:url value='/clm/manage/consideration.do?method="+ arg +"' />",
			type: "post",
			dataType: "json",			
			success: function(responseText, statusText) {
				//alert(responseText.returnCd);
				if(responseText.returnCd == "Y"){
					alert("<spring:message code="clm.page.msg.manage.announce158" />");
						if("deleteRequest" == arg){
							forwardConsideration('list');
							//detailAction();
						}else if("deferCancelRequest" == arg){		//보류해제시  재의뢰 할수 있도록 재의뢰 Process Copy 진행							
							//prevCnsdReqCopyConsideration('deferAgain');//일단 주석 처리함 오픈 이후 적용 할거임...로직변경으로 사용 안함
							
							//의뢰시 보류 해제는 보류 해제일을 의뢰일로 업데이트 ?
							//회신시보류 해제는 예전 상태로 롤백?							
							//forwardConsideration('list');
							//현재 페이지 reload
							//reload
							detailAction();
						}else if ("saveCancelRequest" ==  arg){	//작성중인 의뢰건 취소 후 
							forwardConsideration('list');
						}
					
				}else{
					alert("<spring:message code="clm.page.msg.manage.announce157" />");
				}
			}
		}
		$("#frm").ajaxSubmit(options);
  	}
  	
  	/** 
	  *계약 탭 추가 및 계약 컨테츠 생성 데이타 저장 
	  */
	  function contractAction(arg){
		  var frm = document.frm;
		  var msg ="";
		  if("deleteContract" == arg){			  
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
		var options = {
			url: "<c:url value='/clm/manage/consideration.do?method=deleteContract' />",
			type: "post",
			dataType: "json",			
			success: function(responseText, statusText) {
				//alert(responseText.returnCd);
				if(responseText.returnCd == "Y"){
					detailAction();
					alert("<spring:message code="clm.page.msg.manage.announce158" />");
					
					
				}else{
					alert("<spring:message code="clm.page.msg.manage.announce157" />");
				}
			}
		}
		$("#frm").ajaxSubmit(options);
	}
  	
  	/**
	* 관리페이지 이동
	*/	
	function detailAction(){
		var frm = document.frm;
		//viewHiddenProgress(true) ;
		frm.target = "_self";
		frm.action = "/clm/manage/consideration.do";
		frm.method.value = "detailConsideration";//detailConsideration		
		frm.submit();
	}
  	
	 /**
	 * 의뢰 보류 / drop /계약 drop 
	 * frm.submit_status.value = arg;
	 */
	 function opnnConsideration(arg){
		
	 	var frm = document.frm;
	 	frm.submit_status.value = arg; 
	 	
	        PopUpWindowOpen('', "530", "150", true);
	        frm.action = "<c:url value='/clm/manage/consideration.do?method=opnnConsideration' />";
	        frm.method.value="forwardUpdateOpnnPopup";
	        frm.target = "PopUpWindow";
	        frm.submit();
	 	
	 }
	 
	 function setOpnnConsideration(obj) {    
	       var frm = document.frm;
	       
		 //alert(obj.strMethod );
		 //alert(obj.Cd);
		 
        var strMethod =   obj.strMethod;
        var strCd = obj.Cd;		//Y or N
        
        if("deferRequest" == strMethod){	//deferRequest 의뢰 보류
        	//$('#deferOpnnArea').append(returnValue.opnn);        	
        		//detailDeferDrop(strMethod);
        		//리스트로 forward 하기
        		//forwardConsideration('list');
        	detailAction();
  			        	
        }else if("dropContract" == strMethod){	//dropContract 계약 drop
        	//$('#dropRequest').append(returnValue.opnn);        	 
        	//detailDeferDrop(strMethod);
        	forwardConsideration("detail");
        }else{	        	
        	forwardConsideration("list");
        }
	 }
	 
	 function detailDeferDrop(arg) {
		 var strMethod="";
			var innerHtml ="";
			$("#submit_status").val(arg);
			
		 	var options = {
		 		url: "<c:url value='/clm/manage/consideration.do?method=detailDropDefer' />",
		 		type: "post",
				dataType: "json",			
				success: function(responseText, statusText) {
					
			 			if("deferRequest" == arg){		 				
			 				$("#tr_deferRequestOpnn").remove();
			 				//
			 				innerHtml = "<tr id=\"tr_deferRequestOpnn\" class=\"lineAdd\">";
							innerHtml +="<th><spring:message code='clm.page.msg.manage.rsnDelay' /></th>";
							innerHtml +="<td id=\"td_deferRequestOpnn\" colspan=\"5\">"+responseText.returnVal+"</td>";
							innerHtml +="</tr>";
							
							$("#table_requestInfo").append(innerHtml);
							
			 			}else if("dropContract" == arg){		 				
			 				
			 				$("#tr_dropContractOpnn").remove();
			 				innerHtml = "<tr id=\"tr_dropContractOpnn\" class=\"slide-target02 slide-area\">";
			 				innerHtml += "<th colspan=\"2\">Drop</th>";
			 				innerHtml += "<td id=\"td_dropContractOpnn\" colspan=\"5\">"+responseText.returnVal+"";								
			 				innerHtml += "</td>";
			 				innerHtml += "</tr>";
					
			 				$("#table_contractBasicInfo").append(innerHtml);
			 				
			 			}
		 		}
		 	}
		 	$("#frm").ajaxSubmit(options);
		 }
	 
	 /**
	  * 인쇄버튼 
	  * 
	  */
	 function goRdView() {
		 var frm 				= document.frm;
		 PopUpWindowOpen('', "1000", "600", true);
		 frm.action = "<c:url value='/clm/manage/consideration.do' />";
		 frm.method.value="forwardPrintPop";		 		 
		 frm.target = "PopUpWindow";
		 frm.submit();
	 }
	 //거래선 거래상대방 상세내역 팝업
	 function customerPop(customerCd, dodun){
		    var frm = document.frm;
		    PopUpWindowOpen('', 900, 300, true);
		    frm.target = "PopUpWindow";
		    frm.customer_cd.value = customerCd;
		    frm.dodun.value       = dodun;
		    frm.action = "<c:url value='/clm/draft/customerTest.do'/>";
		    frm.method.value = "detailCustomerShare";
		    frm.submit();
		}
	 
	 function initPage(){		 
		var frm = document.frm;	
			
		   //계약관련 #1-계약서
		    frm.target          = "fileList1";
		    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
		    frm.method.value    = "forwardClmsFilePage";
		    frm.file_bigclsfcn.value = "F012";
		    frm.file_midclsfcn.value = "F01202";
		    frm.file_smlclsfcn.value = "F0120201";
			frm.ref_key.value = $('#master_cntrt_id').val()+"@"+$('#cnsdreq_id').val();				
		    frm.fileInfoName.value = "fileInfos1";
		    frm.fileFrameName.value = "fileList1";
		    //frm.submit();
		    getClmsFilePageCheck('frm');
		    
		  	//fileListEtc 계약관련 - 첨부/별첨 
		    frm.target          = "fileListEtc";
		    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
		    frm.method.value    = "forwardClmsFilePage";
		    frm.file_bigclsfcn.value = "F012";
		    frm.file_midclsfcn.value = "F01202";
		    frm.file_smlclsfcn.value = "F0120208";
		    frm.ref_key.value = $('#master_cntrt_id').val()+"@"+$('#cnsdreq_id').val();
		    frm.fileInfoName.value = "fileInfosEtc";
		    frm.fileFrameName.value = "fileListEtc";
		  //frm.submit();
		    getClmsFilePageCheck('frm');
		    
		  //계약관련 #2-기타_체결본
		    frm.target          = "fileList2";
		    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
		    frm.method.value    = "forwardClmsFilePage";
		    frm.file_bigclsfcn.value = "F012";
		    frm.file_midclsfcn.value = "F01202";
		    frm.file_smlclsfcn.value = "F0120212";
		    frm.ref_key.value = $('#master_cntrt_id').val()+"@"+$('#cnsdreq_id').val();
		    frm.fileInfoName.value = "fileInfos2";
		    frm.fileFrameName.value = "fileList2";
		  //frm.submit();
		    getClmsFilePageCheck('frm');
		    
		    
		    
		  //계약관련 #4
		    frm.target          = "fileList4";
		    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
		    frm.method.value    = "forwardClmsFilePage";
		    frm.file_bigclsfcn.value = "F012";
		    frm.file_midclsfcn.value = "F01201";
		    frm.file_smlclsfcn.value = "F0120101";
		    frm.ref_key.value = $('#master_cntrt_id').val();
		    frm.fileInfoName.value = "fileInfos4";
		    frm.fileFrameName.value = "fileList4";
		  //frm.submit();
		    getClmsFilePageCheck('frm');
		    
		    
		}
	 
	//신규이행정보
	 function listMyApprovalExe() {
	 	var options = {
	 			url: "<c:url value='/clm/manage/myApproval.do?method=listMyApprovalExe' />",
	 			type: "post",
	 			async: false,
	 			dataType: "html",
	 			success: function (data) {
	 				$("#consultation-execinfo-list").html("");
	 				$("#consultation-execinfo-list").html(data);
	 				
	 				$('#execution-mng-content').attr("style","display:");
	 				$('#rowAddDel').attr("style","display:none");
	 				$('#execution-mng2-content').attr("style","display:none");
	 				$('#rowAddDel2').attr("style","display:none");
	 				$('#execution-mng3-content').attr("style","display:none");
	 				$('#rowAddDel3').attr("style","display:none");
	 				
	 		
	 			}
	 		};
	 	$("#frm").ajaxSubmit(options);
	 }
	 
	 
	 
</script>
</head>
<body>
<div id="wrap">
	<!-- container -->
	<div id="container">
		<form:form name="frm" id='frm' method="post" autocomplete="off">
		<input type="hidden" name="method" id="method" value="">
		<input type="hidden" name="menu_id" id="menu_id" value="<c:out value='${command.menu_id}'/>" />
		<input type="hidden" name="curPage" id="curPage" value="<c:out value='${command.curPage}'/>">
		<!-- 리스트 검색 조건  -->
		<input type="hidden" name="srch_resp_dept" value="<c:out value='${command.srch_resp_dept}' escapeXml='false'/>"/>				<!-- 담당부서코드 -->
		<input type="hidden" name="srch_oppnt_cd" value="<c:out value='${command.srch_oppnt_cd}' escapeXml='false'/>"/>   				<!-- 계약상대방코드 -->
		<input type="hidden" name="srch_review_title" value="<c:out value='${command.srch_review_title}' escapeXml='false'/>" />		<!-- 의뢰명 -->
		<input type="hidden" name="srch_reqman_nm" value="<c:out value='${command.srch_reqman_nm}' escapeXml='false'/>" />				<!-- 의뢰자 -->
		<input type="hidden" name="srch_start_reqday" value="<c:out value='${command.srch_start_reqday}' escapeXml='false'/>" />		<!-- 의뢰 시작일 -->
		<input type="hidden" name="srch_end_reqday" value="<c:out value='${command.srch_end_reqday}' escapeXml='false'/>" />			<!-- 의뢰 종료일 -->
		<input type="hidden" name="srch_start_cnlsnday" value="<c:out value='${command.srch_start_cnlsnday}' escapeXml='false'/>" />	<!-- 계약 시작일 -->
		<input type="hidden" name="srch_end_cnlsnday" value="<c:out value='${command.srch_end_cnlsnday}' escapeXml='false'/>" />		<!-- 계약 종료일 -->
		<input type="hidden" name="srch_resp_dept_nm" value="<c:out value='${command.srch_resp_dept_nm}' escapeXml='false'/>" />		<!-- 담당부서명 -->
		<input type="hidden" name="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}' escapeXml='false'/>" />			<!-- 담당자명 -->
		<input type="hidden" name="srch_biz_clsfcn" value="<c:out value='${command.srch_biz_clsfcn}' escapeXml='false'/>" />			<!-- 계약단계 -->
		<input type="hidden" name="srch_cnsdman_nm" value="<c:out value='${command.srch_cnsdman_nm}' escapeXml='false'/>" />			<!-- 검토자 -->
		<input type="hidden" name="srch_oppnt_nm" value="<c:out value='${command.srch_oppnt_nm}' escapeXml='false'/>" />				<!-- 계약상대방 -->
		<input type="hidden" name="srch_state" value="<c:out value='${command.srch_state}' escapeXml='false'/>" />						<!-- 상태 -->
		<input type="hidden" name="srch_cnclsnpurps_bigclsfcn" value="<c:out value='${command.srch_cnclsnpurps_bigclsfcn}' escapeXml='false'/>" />	<!-- 체결목적-->
		
		<input type="hidden" name="cnsd_status" id="cnsd_status" value="<c:out value='${lomRq.cnsd_status}'/>" />
		<input type="hidden" name="prgrs_status" id="prgrs_status" value="<c:out value='${lomRq.prgrs_status}'/>" />	
		<input type="hidden" name="plndbn_req_yn" id="plndbn_req_yn" value="<c:out value='${lomRq.plndbn_req_yn}'/>" />
		
		<!-- 의뢰시 필수 파라민터  -->
		<input type="hidden" name="status" id="status" value="" />
		<input type="hidden" name="tab_cnt" id="tab_cnt" value="<c:out value='${lomRq.total_cnt}'/>" /><!-- Tab 계약 증가 값 -->
		<input type="hidden" name="prev_cnsdreq_id" id ="prev_cnsdreq_id" value="<c:out value='${lomRq.prev_cnsdreq_id}'/>" />
		<input type="hidden" name="cnsdreq_id" id="cnsdreq_id" value="<c:out value='${lomRq.cnsdreq_id}'/>" />	 
		<input type="hidden" name="cntrt_id" id="cntrt_id"  value="<c:out value='${lomRq.cntrt_id}'/>" />
		
		<input type="hidden" name="dmstfrgn_gbn" id="dmstfrgn_gbn"  value="<c:out value='${lomRq.dmstfrgn_gbn}'/>" />
		<c:forEach var="cntrtMt" items="${listDc}">
			<input type="hidden" name="arr_cntrt_id"  value="<c:out value='${cntrtMt.cntrt_id}' />" /> <!-- 계약_마스터_계약_ID -->	
		</c:forEach>
		<input type="hidden" name="parent_cntrt_id" id="parent_cntrt_id" />
		<input type="hidden" name="submit_status" id="submit_status" />
		<!-- 첨부파일정보 -->
		<input type="hidden" name="fileInfos"   value="" /> <!-- 저장될 파일 정보 -->
		<input type="hidden" name="fileInfos1"   value="" /> <!-- 저장될 파일 정보 -->
		<input type="hidden" name="fileInfosEtc"   value="" /> <!-- 저장될 파일 정보 -->	
		<input type="hidden" name="fileInfos2"   value="" /> <!-- 저장될 파일 정보 -->	
		<input type="hidden" name="fileInfos3"   value="" /> <!-- 저장될 파일 정보 -->	
		<input type="hidden" name="fileInfos4"   value="" /> <!-- 저장될 파일 정보 -->
		<input type="hidden" name="fileInfos5"   value="" /> <!-- 저장될 파일 정보 -->	
		<input type="hidden" name="fileInfos6"   value="" /> <!-- 저장될 파일 정보 -->
		
		<input type="hidden" name="fileInfoName"    value="" /> <!-- 첨부파일 정보가 리턴될 객체명 -->
		<input type="hidden" name="fileFrameName"    value="" /> <!-- 첨부파일 화면 iFrame 명 -->
			
		<input type="hidden" name="file_bigclsfcn"  value="" /> <!-- 대분류 -->	
		<input type="hidden" name="file_midclsfcn"  value="" /> <!-- 중분류 -->	
		<input type="hidden" name="file_smlclsfcn"  value="" /> <!-- 소분류 -->	
		<input type="hidden" name="ref_key"     value="<c:out value='${lomRq.cnsdreq_id}'/>" /> <!-- 키값 -->	
		<input type="hidden" name="view_gbn"    value="download" /> <!-- 첨부파일 출력 화면 종류 : upload, modify, download -->	
		
		<!-- 거래선 상대방 팝업 파라미터  -->
		<input type="hidden" name="customer_cd" id="customer_cd" value="" />
		<input type="hidden" name="dodun" id="dodun" value="" />
		
		<input type="hidden" name="status_mode" id="status_mode" />
		<input type="hidden" name="pageGbn" id="pageGbn" />

	
		<!-- location -->
		<div class="location"><!-- Req_status_title <c:out value='${command.req_status_title}'/> -->
			<img src="<%=IMAGE %>/icon/ico_home.gif" border="0" alt="Home"> <spring:message code="clm.page.msg.common.contractManagement" /> > <spring:message code="clm.page.msg.manage.aftConclReg" /> > <strong><c:out value='${command.req_status_title}'/> <spring:message code="clm.page.msg.manage.write" /></strong>
		</div>
		<!-- //location -->
		<!-- title -->
		<div class="title">
			<h3><c:out value='${command.req_status_title}'/>111111111111111111111</h3>
		</div>
		<!-- //title -->		
		<!-- content -->	
		<div id="content">		
			
			<div class="t_titBtn">
				<!-- title -->
				<div class="title_basic">
					<h4><c:out value='${command.req_status_title}'/> <spring:message code="clm.page.msg.common.info" /><!-- <img src="<%=IMAGE %>/btn/btn_up.gif" alt="up" onclick="tit_Toggle(this,'tr_down01');"/> --></h4>
				</div>
				<!-- //title -->
				<!--View -->
				<!-- 수정/검토의뢰/최종본작성/목록 -->			
				<!-- button -->
				<div class="btn_wrap_t02">						
			   <!--
			    계약상태 : <c:out value='${lomRq.prgrs_status}' />
			    프로세스단계 : <c:out value='${lomRq.cntrt_status}' />
			    단계상태 : <c:out value='${lomRq.prcs_depth}' />
			    진행상태 : <c:out value='${lomRq.depth_status}' />			    
			    -->		  
			   <c:if test='${command.session_user_id == lomRq.reqman_id}'>			   
			   <% if("C04201".equals(lomRq.get("prgrs_status")) && ("C02601".equals(lomRq.get("depth_status")))){ %>
			   		<c:choose>			   			
						<c:when test="${lomRq.plndbn_req_yn == 'N' && lomRq.prev_cnsdreq_id != ''}">
						<span class="btn_all_w"><span class="check2"></span><a href="javascript:forwardConsideration('again');"><spring:message code="clm.page.msg.common.modify" /></a></span>
						</c:when>
						<c:otherwise> 
						<span class="btn_all_w"><span class="check2"></span><a href="javascript:forwardConsideration('mod');"><spring:message code="clm.page.msg.common.modify" /></a></span>
						</c:otherwise>
					</c:choose>
					<c:if test="${lomRq.prev_cnsdreq_id !=''}">
					<span class="btn_all_w"><span class="check2"></span><a href="javascript:actionConsideration('saveCancelRequest');"><spring:message code="clm.page.msg.manage.cnclTmpSave" /></a></span>
					</c:if>
			   <% }else if(!"Y".equals(lomRq.get("plndbn_req_yn")) && "C04305".equals(lomRq.get("cnsd_status")) && (!"C02607".equals(lomRq.get("depth_status")))){%>
			   		<span class="btn_all_w"><span class="check2"></span><a href="javascript:prevCnsdReqCopyConsideration('again');"><spring:message code="clm.page.msg.manage.reviewRequ" /></a></span>
			   		<!-- 구주는 최종본 의뢰가 없음. 김재원 20131003 <span class="btn_all_w"><span class="check3"></span><a href="javascript:prevCnsdReqCopyConsideration('last');"><spring:message code="clm.page.msg.manage.finalVerReq" /></a></span> -->			   		
			   <%  }else if("C02608".equals(lomRq.get("depth_status"))  || "C04215".equals(lomRq.get("prgrs_status")) || "C04216".equals(lomRq.get("prgrs_status"))){  %>
					<%if("Y".equals(lomRq.get("plndbn_req_yn")) && "C04207".equals(lomRq.get("prgrs_status"))){ //품의작성 버튼 %>		
			   		<span class="btn_all_w"><span class="reply"></span><a href="javascript:forwardConsultation('consult');"><spring:message code="clm.page.msg.manage.itmNameWrite" /></a></span>
			   		<%  } %>
			   <%  } %>
			 
			    
				<% if("C04202".equals(lomRq.get("prgrs_status")) || "C04203".equals(lomRq.get("prgrs_status")) || "C04204".equals(lomRq.get("prgrs_status")) || "C04205".equals(lomRq.get("prgrs_status")) || "C04206".equals(lomRq.get("prgrs_status")) || "C04207".equals(lomRq.get("prgrs_status")) ){	//검토중  회신 일때 Drop 보임%>
					<%-- <% if("C02607".equals(lomRq.get("depth_status"))){ %>
					<span id ="id_defer" class="btn_all_w"><span class="delete"></span><a href="javascript:actionConsideration('deferCancelRequest');">보류해제</a></span>
					<% }else{%>
					<span id ="id_defer" class="btn_all_w"><span class="delete"></span><a href="javascript:actionConsideration('deferRequest');" title="계약검토 혹은 체결을 일시 유보">보류</a></span>
					<% }%>	 --%>					
					<c:choose>			   			
						<c:when test="${lomRq.defer_btn == 'defer'}">
						<span id ="id_defer" class="btn_all_w"><span class="delete"></span><a href="javascript:actionConsideration('deferRequest');" title="<spring:message code="clm.page.msg.manage.tempConcl" htmlEscape="true" />"><spring:message code="clm.page.msg.manage.delay" /></a></span>
						</c:when>
						<c:otherwise> 
						<span id ="id_defer" class="btn_all_w"><span class="delete"></span><a href="javascript:actionConsideration('deferCancelRequest');"><spring:message code="clm.page.msg.manage.cnclDelay" /></a></span>
						</c:otherwise>
					</c:choose>									
				<span id ="id_drop"  class="btn_all_w"><span class="reset"></span><a href="javascript:actionConsideration('dropRequest');" title="<spring:message code="clm.page.msg.manage.finalDrop" htmlEscape="true" />">Drop</a></span>
				<% }else if("".equals(lomRq.get("prev_cnsdreq_id")) && "C04201".equals(lomRq.get("prgrs_status")) && "C02601".equals(lomRq.get("depth_status")) ){ %>				
				<span id ="id_delete"  class="btn_all_w"><span class="delete"></span><a href="javascript:actionConsideration('deleteRequest');"><spring:message code="clm.page.msg.common.delete" /></a></span>
				<% }%>
				</c:if>
				
				<c:if test='${command.session_user_id == lomRq.reqman_id}'>
				<%if(!"Y".equals(lomRq.get("plndbn_req_yn")) && "C04305".equals(lomRq.get("cnsd_status"))){%>		
				<%}else if("C02608".equals(lomRq.get("depth_status"))  || "C04215".equals(lomRq.get("prgrs_status")) || "C04216".equals(lomRq.get("prgrs_status"))){  %>
				(<span class="btn_all_w"><span class="check2"></span><a href="javascript:prevCnsdReqCopyConsideration('again');"><spring:message code="clm.page.msg.manage.reviewRequ" /></a></span>
				<!-- 구주는 최종본 의뢰가 없음. 김재원 20131003 <span class="btn_all_w"><span class="check3"></span><a href="javascript:prevCnsdReqCopyConsideration('last');"><spring:message code="clm.page.msg.manage.finalVerWrite" /></a></span>) -->				
				<%} %>	
				</c:if>	
				<!-- <span class="btn_all_w"><span class="preview"></span><a href="javascript:goRdView();">인쇄</a></span>  -->				
				<span class="btn_all_w"><span class="list"></span><a href="javascript:forwardConsideration('list');"><spring:message code="clm.page.msg.manage.list" /></a></span>
				</div>
				<!-- //button -->
			</div>
			<!-- toptable -->
			<table id="table_requestInfo" cellspacing="0" cellpadding="0" class="table-style01">
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
				  <td colspan="7"><c:out value='${lomRq.req_title}' default="" /></td>
				</tr>
				<tr class="lineAdd">
					<th><spring:message code="clm.page.msg.manage.reqPerson" /></th>
					<td>
						<c:out value="${lomRq.reqman_nm}" />/<c:out value="${lomRq.reqman_jikgup_nm}" />/<c:out value="${lomRq.req_dept_nm}" />					
					</td>
					<th><spring:message code="clm.page.msg.manage.requDate" /></th>
					<td><c:out value="${lomRq.req_dt}" /></td>
					<th><spring:message code="clm.page.msg.manage.replyReqDate" /></th>
					<td><c:out value="${lomRq.re_demndday}" /></td>
				</tr>
				<% if(!listCa.isEmpty()){ %>
				<tr id="deferOpnnArea" class="lineAdd">
					<th><spring:message code="clm.page.msg.manage.relPerson" /></th><!-- RELATION_MAN  relation_man -->
					<td colspan="5"><span id="id_trgtman_nm">
					<%for(int j=0;j<listCa.size();j++){ %>
					<% ListOrderedMap lom = (ListOrderedMap)listCa.get(j);%>
					<% if((j !=0 && j !=1) && (j % 2 == 0 )){%><br/><%}%>
					<% if(j != 0 && (j % 2 !=0 )){%>,<% }%>						
					<%=lom.get("trgtman_nm") %>/<%=lom.get("trgtman_jikgup_nm") %>/<%=lom.get("trgtman_dept_nm") %>
					<% }%></span>
					</td>
				</tr>
				<% }%>
				<c:if test="${lomRq.cnsd_status != 'C04305'}">
				<tr>
					<!-- 2012.03.19 "최종본 요청내용" => "최종본 검토의뢰내용" 으로 명칭 변경 modified by hanjihoon -->
					<c:choose>
						<c:when test="${lomRq.plndbn_req_yn == 'Y'}">
							<th class="borTz02"><spring:message code="clm.page.msg.manage.finalVer" /><br><spring:message code="clm.page.msg.manage.reviewReqCont" /></th>
						</c:when>
						<c:otherwise>
							<th class="borTz02"><c:out value='${command.req_status_title}'/> <spring:message code="clm.page.msg.manage.reqCont" /></th>
						</c:otherwise>
					</c:choose>
					<td colspan="5"><c:out escapeXml="false" value="${lomRq.cnsd_demnd_cont}" />
					</td>
				</tr>
				<c:if test="${lomRq.plndbn_req_yn == 'Y' || lomRq.lastbn_chge_yn == 'Y'}">
				<c:if test="${!empty lomRq.lastbn_chge_yn_nm}">
				<tr>
					<th class="borTz02"><spring:message code="clm.page.msg.manage.chgRevVerYn" /></th><!-- 마지막본 변경 영부  LASTBN_CHGE_YN -->
					<td colspan="5"><c:out value='${lomRq.lastbn_chge_yn_nm}' /></td>
				</tr>
				</c:if>
				<c:if test="${!empty lomRq.plndbn_chge_cont}">
				<tr>
					<th class="borTz02"><spring:message code="clm.page.msg.manage.rsnBrkChange" /> </th>
					<td colspan="5"><c:out value='${lomRq.plndbn_chge_cont}' escapeXml="false" /></td>
				</tr>
				</c:if>
				</c:if>
				</c:if>
				<%if(!lomCh.isEmpty()){ %>
				<tr id="tr_deferRequestOpnn" class="lineAdd">
					<th><spring:message code="clm.page.msg.manage.rsnDelay" /></th><!-- 보류 내용 있으면 화면에 표시 -->
					<input type="hidden" name="hold_seqno" value="<c:out value='${lomCh.hold_seqno}'/>" />
					<td id="td_deferRequestOpnn" colspan="5"><c:out escapeXml="false" value='${lomCh.hold_cause}'/></td>
				</tr>
				<%} %>
			</table>
			<br>
<% // 회신일 경우에만 아래의 문구가 보여주게 됩니다. 12.1.14 김재원
   // 2012.03.05 문구변경 modified by hanjihoon
   // 2012.03.19 재의뢰, 최종본의뢰 문구변경 modified by hanjihoon
if(("C02608".equals(lomRq.get("depth_status")))){ %>
	   		<c:choose>
				<c:when test="${lomRq.plndbn_req_yn == 'Y'}">
					<spring:message code="clm.page.msg.manage.announce030" /><br></br><spring:message code="clm.page.msg.manage.announce001" />
				</c:when>
				<c:otherwise>
<%-- 					<spring:message code="clm.page.msg.manage.announce048" /> --%>
				</c:otherwise>
			</c:choose>	
<%} else { %>
		    
<%} %>
			<!-- //top table -->			
			<!-- title -->
		   <div class="title_basic03">
				<h4><spring:message code="clm.page.msg.manage.bscInfCont" /> <img src="<%=IMAGE %>/btn/btn_up.gif" alt="up" onclick="tit_Toggle(this,'tr_down02');"/></h4>
			</div>
			<!-- //title -->
			<!-- tab01 -->
			<div class="t_titBtn">
			<ul id="tab_title" class="tab_basic03">
				<c:forEach var="list" items="${listDc}">
				<c:choose>
				<c:when test="${list.rn=='1'}">
		          		<li id="tab_li<c:out value='${list.rn}'/>" class="on"><a href="javascript:titleTabMove('<c:out value='${list.rn}'/>','<c:out value='${list.cntrt_id}'/>');"><c:out value='${list.tab_nm}'/></a></li>
				</c:when>
				</c:choose>				
				</c:forEach>									
			</ul>			
			<div class="btn_wrap_t03">
			<% if("C02606".equals(lomRq.get("depth_status")) ||  "C02608".equals(lomRq.get("depth_status"))){	//회신완료 %>				
				<!-- <span id ="id_dropContract" class="btn_all_w"><span class="reset"></span><a href="javascript:actionConsideration('dropContract');">계약Drop</a></span> -->			
			<%}else if("".equals(lomRq.get("prev_cnsdreq_id")) && "C02601".equals(lomRq.get("depth_status"))){		//최초 의뢰건 작성중 %>		
				<!-- <span id ="id_deleteContract" class="btn_all_w"><span class="delete"></span><a href="javascript:contractAction('deleteContract');">계약삭제</a></span>//-->		
			<%} %>
			</div>	
			</div>	
			<!-- 두줄라인 추가 --><div class="tal_topLine"></div><!-- //두줄라인 추가 -->
			<!-- middle table -->
			<div id="tab_contents"></div>
			<!-- //tab_content -->			
			<!-- 이력정보 -->
			<!-- <div id="contractHis-list"></div> -->
			<!-- //이력정보 -->
			<!-- 이행정보영역Start -->
			<div class="title_basic">
				<h5 class="ntitle05">
					<spring:message code="clm.page.tab.title.contractexec" />
					<img src="<%=IMAGE%>/btn/btn_up.gif" alt="down"
						onclick="tit_Toggle(this,'consultation-execinfo-list');" />
				</h5>
			</div>
			<div id="consultation-execinfo-list"></div>
			<!-- 이행정보영역 End -->
			<!-- table001 -->
			<!-- //able001 -->	
			<div class="t_titBtn">
      		<div class="btn_wrap_c">			 
			   <c:if test='${command.session_user_id == lomRq.reqman_id}'>			   
			   <% if("C04201".equals(lomRq.get("prgrs_status")) && "C02601".equals(lomRq.get("depth_status")) ){ %>
			   		<c:choose>		   			
						<c:when test="${lomRq.plndbn_req_yn == 'N' && lomRq.prev_cnsdreq_id != ''}">
						<span class="btn_all_w"><span class="check2"></span><a href="javascript:forwardConsideration('again');"><spring:message code="clm.page.msg.common.modify" /></a></span>
						</c:when>
						<c:otherwise> 
						<span class="btn_all_w"><span class="check2"></span><a href="javascript:forwardConsideration('mod');"><spring:message code="clm.page.msg.common.modify" /></a></span>
						</c:otherwise>
					</c:choose>	
					<c:if test="${lomRq.prev_cnsdreq_id !=''}">
					<span class="btn_all_w"><span class="check2"></span><a href="javascript:actionConsideration('saveCancelRequest');"><spring:message code="clm.page.msg.manage.cnclTmpSave" /></a></span>
					</c:if>						
			   <% }else if(!"Y".equals(lomRq.get("plndbn_req_yn")) && "C04305".equals(lomRq.get("cnsd_status")) && (!"C02607".equals(lomRq.get("depth_status")))){%>
			   		<span class="btn_all_w"><span class="check2"></span><a href="javascript:prevCnsdReqCopyConsideration('again');"><spring:message code="clm.page.msg.manage.reviewRequ" /></a></span>
			   		<!-- 구주는 최종본 의뢰가 없음. 김재원 20131003 <span class="btn_all_w"><span class="check3"></span><a href="javascript:prevCnsdReqCopyConsideration('last');"><spring:message code="clm.page.msg.manage.finalVerReq" /></a></span> -->			   		
			   <%  }else if("C02608".equals(lomRq.get("depth_status"))  || "C04215".equals(lomRq.get("prgrs_status")) || "C04216".equals(lomRq.get("prgrs_status"))){  %>
					<%if("Y".equals(lomRq.get("plndbn_req_yn")) && "C04207".equals(lomRq.get("prgrs_status"))){ //품의작성 버튼 %>		
			   		<span class="btn_all_w"><span class="reply"></span><a href="javascript:forwardConsultation('consult');"><spring:message code="clm.page.msg.manage.itmNameWrite" /></a></span>
			   		<%  } %>
			   <%  } %>		  
				<% if("C04202".equals(lomRq.get("prgrs_status")) || "C04203".equals(lomRq.get("prgrs_status")) || "C04204".equals(lomRq.get("prgrs_status")) || "C04205".equals(lomRq.get("prgrs_status")) || "C04206".equals(lomRq.get("prgrs_status")) || "C04207".equals(lomRq.get("prgrs_status")) ){	//작성중 빼고 보류가능%>
					<%-- <% if("C02607".equals(lomRq.get("depth_status"))){ %>
					<span id ="id_defer" class="btn_all_w"><span class="delete"></span><a href="javascript:actionConsideration('deferCancelRequest');">보류해제</a></span>
					<% }else{%>
					<span id ="id_defer" class="btn_all_w"><span class="delete"></span><a href="javascript:actionConsideration('deferRequest');" title="계약검토 혹은 체결을 일시 유보">보류</a></span>
					<% }%>	 --%>
					
					<c:choose>			   			
						<c:when test="${lomRq.defer_btn == 'defer'}">
						<span id ="id_defer" class="btn_all_w"><span class="delete"></span><a href="javascript:actionConsideration('deferRequest');" title="<spring:message code="clm.page.msg.manage.tempConcl" htmlEscape="true" />"><spring:message code="clm.page.msg.manage.delay" /></a></span>
						</c:when>
						<c:otherwise> 
						<span id ="id_defer" class="btn_all_w"><span class="delete"></span><a href="javascript:actionConsideration('deferCancelRequest');"><spring:message code="clm.page.msg.manage.cnclDelay" /></a></span>
						</c:otherwise>
					</c:choose>
													
				<span id ="id_drop"  class="btn_all_w"><span class="reset"></span><a href="javascript:actionConsideration('dropRequest');" title="<spring:message code="clm.page.msg.manage.finalDrop" htmlEscape="true" />">Drop</a></span>
				<% }else if("".equals(lomRq.get("prev_cnsdreq_id")) && "C04201".equals(lomRq.get("prgrs_status")) && "C02601".equals(lomRq.get("depth_status")) ){ %>				
				<span id ="id_delete"  class="btn_all_w"><span class="delete"></span><a href="javascript:actionConsideration('deleteRequest');"><spring:message code="clm.page.msg.common.delete" /></a></span>
				<% }%>
				</c:if>				
				<c:if test='${command.session_user_id == lomRq.reqman_id}'>
				<%if(!"Y".equals(lomRq.get("plndbn_req_yn")) && "C04305".equals(lomRq.get("cnsd_status"))){%>		
				<%}else if("C02608".equals(lomRq.get("depth_status"))  || "C04215".equals(lomRq.get("prgrs_status")) || "C04216".equals(lomRq.get("prgrs_status"))){  %>
				(<span class="btn_all_w"><span class="check2"></span><a href="javascript:prevCnsdReqCopyConsideration('again');"><spring:message code="clm.page.msg.manage.reviewRequ" /></a></span>
				<!-- 구주는 최종본 의뢰가 없음. 김재원 20131003 <span class="btn_all_w"><span class="check3"></span><a href="javascript:prevCnsdReqCopyConsideration('last');"><spring:message code="clm.page.msg.manage.finalVerWrite" /></a></span>) -->				
				<%} %>	
				</c:if>	
				<!-- <span class="btn_all_w"><span class="preview"></span><a href="javascript:goRdView();">인쇄</a></span> -->				
				<span class="btn_all_w"><span class="list"></span><a href="javascript:forwardConsideration('list');"><spring:message code="clm.page.msg.manage.list" /></a></span>
				</div>			
				</div>
				<!-- //button -->	
		</div>
		<!-- //content -->	
		<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- // footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</form:form>
	</div>
	<!-- //container -->
</div>
</body>
</html>


