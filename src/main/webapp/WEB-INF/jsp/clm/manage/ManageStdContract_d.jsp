<!DOCTYPE html>
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
 * 파  일  명 : ManageStdContract_d.jsp
 * 프로그램명 : 표준계약서 승인 조회
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 2012.08
 */
--%>
<% 
		
	ListOrderedMap lomRq = (ListOrderedMap)request.getAttribute("lomRq"); // 의뢰 정보
    ListOrderedMap lomRq2 = (ListOrderedMap)request.getAttribute("lomRq2"); // 계약정보(기존 inner 에서 가져왔단 정보)
	ListOrderedMap lomCh = (ListOrderedMap)request.getAttribute("lomCh"); // 보류 사유
	
	ArrayList listTs = (ArrayList)request.getAttribute("listTs");  // 특화정보
	ArrayList listDc = (ArrayList)request.getAttribute("listDc");  // 의뢰정보(lomRq 을 담고 있는 List)
	ArrayList listCa = (ArrayList)request.getAttribute("listCa");  // 관련자 정보
	
    ArrayList review    = (ArrayList)request.getAttribute("review");
    ArrayList approve   = (ArrayList)request.getAttribute("approve");
    ArrayList sign      = (ArrayList)request.getAttribute("sign");
    ArrayList info      = (ArrayList)request.getAttribute("info");
    ArrayList defer     = (ArrayList)request.getAttribute("defer");
%>

<html>
<!-- page name: ManageStdContract_d.jsp -->
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="clm.main.title" /></title>
<!--2. CSS 추가  --> 
<!--계약관리일 경우  -->
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet"/>
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
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
		
		//detailContractMaster();
		//initPage();
		//tit_Toggle(this, 'tr_down02'); // 김재원 추가 2011.10.15
		
		
		/*********** Consideration_innder_d.jsp 에 있던 스크립트 ************************/
		
		// 화면로드시 금액 100,000 형태로 변환
        var frm = document.frm;
        var amt = frm.cntrt_amt.value;
        //alert("amt = " +amt);
        //alert("cAmt = " +Comma(amt));
        frm.cntrt_amt.value = Comma(amt);
        
        //첨부파일 설정 
        initPage();
		
        if(<%=StringUtil.isNull((String)lomRq2.get("cntrt_untprc_expl"))%> == false) { 
            frm.target          = "fileList3";
            frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
            frm.method.value    = "forwardClmsFilePage";
            frm.file_bigclsfcn.value = "F012";
            frm.file_midclsfcn.value = "F01202";
            frm.file_smlclsfcn.value = "F0120211";
            frm.ref_key.value = $('#master_cntrt_id').val();
            frm.view_gbn.value = "download";
            frm.fileInfoName.value = "fileInfos3";
            frm.fileFrameName.value = "fileList3";
          //frm.submit();
            getClmsFilePageCheck('frm');
        }
        
        // 2012.03.28 활성화 계약건의 상태에 따라 DROP버튼 Display added by hanjihoon
        if($('#session_user_id').val() == $('#reqman_id').val() && ($('#depth_status').val() == "C02606" || $('#depth_status').val() == "C02608")){
            $("#id_dropContract").show();
        }else{
            $("#id_dropContract").hide();
        }
        
        /*********** Consideration_innder_d.jsp 에 있던 스크립트 끝 ************************/
		
		
		/*********** Consideration_his.jsp 에 있던 스크립트 ************************/
		var collapseIcon = '<%=IMAGE%>/icon/ico_plus.gif';
	    var expandIcon = '<%=IMAGE%>/icon/ico_minus.gif';
	    
	    //$('img[src$=ico_plus.gif]').toggle(function(){
	    //검토이력
	    $('img[alt$=show]').toggle(function(){
	        $(this).removeAttr().attr("src",expandIcon);
	        $(this).parent().parent().parent().next('#tr_show').attr("style", "display:");     
	    }, function(){
	        $(this).removeAttr().attr("src",collapseIcon);
	        $(this).parent().parent().parent().next('#tr_show').attr("style", "display:none");
	    });
	    //승인이력
	    $('img[alt$=show01]').toggle(function(){
	        $(this).removeAttr().attr("src",expandIcon);
	        $(this).parent().parent().parent().next('#tr_show01').attr("style", "display:");
	        
	    }, function(){
	        $(this).removeAttr().attr("src",collapseIcon);
	        $(this).parent().parent().parent().next('#tr_show01').attr("style", "display:none");
	    });
	    //체결이력
	    $('img[alt$=show02]').toggle(function(){
	        $(this).removeAttr().attr("src",expandIcon);
	        $(this).parent().parent().parent().next('#tr_show02').attr("style", "display:");
	        
	    }, function(){
	        $(this).removeAttr().attr("src",collapseIcon);
	        $(this).parent().parent().parent().next('#tr_show02').attr("style", "display:none");
	    });
	    
	    attachHisPage() ;
	    
	    /*********** Consideration_his.jsp 에 있던 스크립트 끝************************/
	    
	    // 2012.02.23 페이지 로딩후 alert added by hanjihoon
        <% if("C04201".equals(lomRq.get("prgrs_status")) && "C02601".equals(lomRq.get("depth_status"))){ 
            if(StringUtil.bvl(session.getAttribute("secfw.session.user_id"), "").equals(lomRq.get("reqman_id"))){
               if(((Integer)lomRq.get("cnsd_level"))!=1) {
        %>
        alert("<spring:message code="clm.page.msg.manage.announce207" />");
        <%      } else {%>
        
        alert("<spring:message code="clm.page.msg.manage.announce208" />");
        <%      }}} %>
	});
	
	function forwardConsultation(arg){
		//detailConsultation
		var frm = document.frm;
	
		//	alert($("#sys_nm").val());
		
		if($("#sys_nm").val() =="myProject"){
			alert("<spring:message code="clm.page.msg.manage.announce056" />");
			return;
		}else{
			frm.status.value = arg;
			frm.target = "_self";		 
			frm.action = "<c:url value='/clm/manage/consultation.do?method=detailConsultationNew&page_gbn=modify' />";
			viewHiddenProgress(true) ;
			frm.submit();
		}
	
	}
	
	
	/**
	* page Action
	*/
	function forwardConsideration(arg){
		var frm = document.frm;
		
		frm.target = "_self";		 
		frm.action = "<c:url value='/clm/manage/myApproval.do' />";
		frm.method.value = "listStdContract";		
		viewHiddenProgress(true) ;
		frm.submit();

	}
	
	function prevCnsdReqCopyConsideration(arg){
		var frm = document.frm;
		if(arg == "again"){
			if(!confirm("<spring:message code="clm.page.msg.manage.announce145" />")) return;
			frm.status.value = "forwardReq";
		}else if(arg == "last"){
			// 2012.07.26 최종본검토건일 경우, 이전검토건중에 회신건이 한건이상일경우에만 가능 added by hanjihoon
            verifyFinalConsideration();
            return;
			
			/*
			if(!confirm("<spring:message code="clm.page.msg.manage.announce186" />")) return;
			alert("<spring:message code="clm.page.msg.manage.announce002" />");
			
			frm.status.value="forwardLast";
			*/
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
					viewHiddenProgress(false);
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
		viewHiddenProgress(true);
		$("#frm").ajaxSubmit(options);
  	}
	
	/**
     * 최종본검토의뢰 
     */
   function verifyFinalConsideration(arg){
       var options = {
               url: "<c:url value='/clm/manage/consideration.do?method=verifyFinalConsideration' />",
               type: "post",
               dataType: "json",
               success: function(responseText, statusText){
                   viewHiddenProgress(false);
                   
                   if(responseText != null && responseText.length != 0 && responseText.returnMessage == "OK") {
                       
                       if(!confirm("<spring:message code="clm.page.msg.manage.announce186" />")) return;
                       alert("<spring:message code="clm.page.msg.manage.announce002" />");
                       
                       frm.status.value="forwardLast";

                       var options2 = {
                           url: "<c:url value='/clm/manage/consideration.do?method=prevCnsdReqCopyConsideration' />",
                           type: "post",
                           dataType: "json",           
                           success: function(responseText, statusText) {
                               viewHiddenProgress(false);
                               
                               if(responseText.cnsdreqId == undefined){
                                   alert("<spring:message code="clm.page.msg.manage.announce157" />");
                               }else{
                                   document.frm.cnsdreq_id.value = responseText.cnsdreqId;
                                   forwardConsideration('mod');
                               }
                           }
                       }
                       viewHiddenProgress(true);
                       $("#frm").ajaxSubmit(options2);
                   }else{
                       alert(responseText.returnMessage);
                   }
           }
       }
       viewHiddenProgress(true);
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
		
		for(var i=1; i<=tab_cnt; i++){			
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
  	* 의뢰 계약 보류 삭제 드랍 처리 
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
		};
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
		};
		$("#frm").ajaxSubmit(options);
	}
  	
  	
  	/**
  	* Reload
  	*/
  	/**
	* 관리페이지 이동
	*/	
	function detailAction(){
		var frm = document.frm;
		//viewHiddenProgress(true) ;
		frm.target = "_self";
		frm.action = "/clm/manage/consideration.do";
		frm.method.value = "detailConsiderationNew";//detailConsideration		
		viewHiddenProgress(true) ;
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
	 
	 /*
	   * 승인이력 - [체결품의] 상세 팝업 띄우기
	   */
	  function openHis(mis_id){
	  	
	  	var frm = document.frm;
	  	
	  	PopUpWindowOpen('', '1024','768',true);
	  	
	  	frm.action	= "<c:url value='/clm/manage/consultation.do' />";
	  	frm.method.value = "forwardHistoryPop";
	  	frm.mis_id.value = mis_id;
	  	frm.target = "PopUpWindow";
	  	frm.submit();
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
	 
	 function considerationPreview() {
		 var frm = document.frm;
			
		 PopUpWindowOpen('', "1024", "768", true);
		    
		 frm.action 		= "<c:url value='/clm/manage/consideration.do' />";
		 frm.method.value	= "forwardPreviewPop";
		 frm.target 		= "PopUpWindow";
		 frm.submit(); 
	 }
	 
	 function initPage(){		 
		var frm = document.frm;	

var frm = document.frm;
		
		$('input[id^=fileInfos]').val("");
		var view = "";
		var file_key = "";
		
		<% if("forwardInsert".equals(lomRq.get("status"))){%>
			view="modify";
			file_key = frm.cntrt_id.value+"@"+frm.cnsdreq_id.value;
		<% }else if("forwardReq".equals(lomRq.get("status")) || "forwardLast".equals(lomRq.get("status"))){%>
			view="modify";
			file_key = frm.cntrt_id.value+"@"+frm.cnsdreq_id.value;
		<% }else{%>
			view="modify";
			file_key = frm.cntrt_id.value+"@"+frm.cnsdreq_id.value;
		<% }%>
		
		//Sungwoo added flag seperated attachment 2014-08-01
		var flag = "<c:out value='${lomRe.re_c_exists}'/>" ; // 회신첨부파일 존재여부
		// 회신 계약서 첨부 파일이 없을 경우 의뢰 첨부파일을 보여준다.
        if(flag=="N") {
        	 //계약관련 #1-계약서
            frm.target          = "fileList1";
            frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
            frm.method.value    = "forwardClmsFilePage";
            frm.file_bigclsfcn.value = "F012";
            frm.file_midclsfcn.value = "F01202";
            frm.file_smlclsfcn.value = "F0120201";
            frm.ref_key.value = file_key;
            frm.view_gbn.value = view;
            frm.fileInfoName.value = "fileInfos1";
            frm.fileFrameName.value = "fileList1";
            getClmsFilePageCheck('frm');
            
            //fileListEtc 계약관련 - 첨부/별첨 
            frm.target          = "fileListEtc";
            frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
            frm.method.value    = "forwardClmsFilePage";
            frm.file_bigclsfcn.value = "F012";
            frm.file_midclsfcn.value = "F01202";
            frm.file_smlclsfcn.value = "F0120208";
            frm.ref_key.value = file_key;
            frm.view_gbn.value = view;
            frm.fileInfoName.value = "fileInfosEtc";
            frm.fileFrameName.value = "fileListEtc";
            getClmsFilePageCheck('frm');
            
          //계약관련 #2-기타
            frm.target          = "fileList2";
            frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
            frm.method.value    = "forwardClmsFilePage";
            frm.file_bigclsfcn.value = "F012";
            frm.file_midclsfcn.value = "F01202";
            frm.file_smlclsfcn.value = "F0120205";
	     	frm.ref_key.value = file_key;
            frm.view_gbn.value = view;
            frm.fileInfoName.value = "fileInfos2";
            frm.fileFrameName.value = "fileList2";
            getClmsFilePageCheck('frm');
        } else {
		  //회신 #1-계약서
            frm.target          = "fileList1" ;
            frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
            frm.method.value    = "forwardClmsFilePage";
            frm.file_bigclsfcn.value = "F012";
            frm.file_midclsfcn.value = "F01202";
            frm.file_smlclsfcn.value = "F0120202";
	     	frm.ref_key.value = file_key;
            frm.view_gbn.value = view;
            frm.fileInfoName.value = "fileInfos1";
            frm.fileFrameName.value = "fileList1";
            getClmsFilePageCheck('frm');
		    
		  	//fileListEtc 계약관련 - 첨부/별첨 
		    frm.target          = "fileListEtc";
		    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
		    frm.method.value    = "forwardClmsFilePage";
		    frm.file_bigclsfcn.value = "F012";
		    frm.file_midclsfcn.value = "F01202";
		    frm.file_smlclsfcn.value = "F0120209";
		    frm.ref_key.value = file_key;
            frm.view_gbn.value = view;
		    frm.fileInfoName.value = "fileInfosEtc";
		    frm.fileFrameName.value = "fileListEtc";
		    getClmsFilePageCheck('frm');
		    
		    
		  //계약관련 #2-기타
		    frm.target          = "fileList2";
		    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
		    frm.method.value    = "forwardClmsFilePage";
		    frm.file_bigclsfcn.value = "F012";
		    frm.file_midclsfcn.value = "F01202";
		    frm.file_smlclsfcn.value = "F0120206"; // 기타가 추가될경우 이것만 바뀌면 될 듯.
		    frm.ref_key.value = file_key;
            frm.view_gbn.value = view;
		    frm.fileInfoName.value = "fileInfos2";
		    frm.fileFrameName.value = "fileList2";
		    getClmsFilePageCheck('frm');
        }
		
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
		    getClmsFilePageCheck('frm');
		}
	 
	// 이력 첨부파일
	function attachHisPage(){
	    var frm = document.frm;
	    
	        //의뢰-계약서파일 CONSULT
	         $('input[name^=fileInfos_cntrt]').each(function(index){
	            $fileInfos_cntrt = $(this).attr("name");
	            $fileList_cntrt = $(this).prev().attr("name");
	            $cntrt_cnsdreq_id = $(this).next().val();
	            
	            frm.target          = $fileList_cntrt;
	            frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	            frm.method.value    = "forwardClmsFilePage";
	            frm.fileInfoName.value = $fileInfos_cntrt;
	            frm.fileFrameName.value = $fileList_cntrt;
	            //의뢰 계약서파일
	            frm.file_bigclsfcn.value = "F012";
	            frm.file_midclsfcn.value = "F01202";
	            frm.file_smlclsfcn.value = "F0120201";
	            frm.view_gbn.value = "download";
	            frm.ref_key.value = "<c:out value='${lomRq.cntrt_id}'/>@"+$cntrt_cnsdreq_id;
	            getClmsFilePageCheck('frm');
	            //frm.submit();
	        });
	         //의뢰-첨부/별첨파일 CONSULT
	         $('input[name^=fileInfos_append]').each(function(index){
	            $fileInfos_append = $(this).attr("name");
	            $fileList_append = $(this).prev().attr("name");
	            $append_cnsdreq_id = $(this).next().val();
	            
	            frm.target          = $fileList_append;
	            frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	            frm.method.value    = "forwardClmsFilePage";
	            frm.fileInfoName.value = $fileInfos_append;
	            frm.fileFrameName.value = $fileList_append;
	            //의뢰 계약서파일
	            frm.file_bigclsfcn.value = "F012";
	            frm.file_midclsfcn.value = "F01202";
	            frm.file_smlclsfcn.value = "F0120208";
	            frm.view_gbn.value = "download";
	            frm.ref_key.value = "<c:out value='${lomRq.cntrt_id}'/>@"+$append_cnsdreq_id;
	            getClmsFilePageCheck('frm');
	            //frm.submit();
	        });
	         //의뢰-기타파일 CONSULT
	         $('input[name^=fileInfos_other]').each(function(index){
	            $fileInfos_other = $(this).attr("name");
	            $fileList_other = $(this).prev().attr("name");
	            $other_cnsdreq_id = $(this).next().val();
	            
	            frm.target          = $fileList_other;
	            frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	            frm.method.value    = "forwardClmsFilePage";
	            frm.fileInfoName.value = $fileInfos_other;
	            frm.fileFrameName.value = $fileList_other;
	            //의뢰 계약서파일
	            frm.file_bigclsfcn.value = "F012";
	            frm.file_midclsfcn.value = "F01202";
	            frm.file_smlclsfcn.value = "F0120205";
	            frm.ref_key.value = "<c:out value='${lomRq.cntrt_id}'/>@"+$other_cnsdreq_id;
	            getClmsFilePageCheck('frm');
	            //frm.submit();
	        });
	         
	       //회신-계약서파일 CONSULT
             $('input[name^=reply_fileInfos_cntrt]').each(function(index){
                $reply_fileInfos_cntrt = $(this).attr("name");
                $reply_fileList_cntrt = $(this).prev().attr("name");
                $reply_cntrt_cnsdreq_id = $(this).next().val();
                
                frm.target          = $reply_fileList_cntrt;
                frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
                frm.method.value    = "forwardClmsFilePage";
                frm.fileInfoName.value = $reply_fileInfos_cntrt;
                frm.fileFrameName.value = $reply_fileList_cntrt;
                //회신 계약서파일
                frm.file_bigclsfcn.value = "F012";
                frm.file_midclsfcn.value = "F01202";
                frm.file_smlclsfcn.value = "F0120202";
                frm.view_gbn.value = "download";
                frm.ref_key.value = "<c:out value='${lomRq.cntrt_id}'/>@"+$reply_cntrt_cnsdreq_id;
                getClmsFilePageCheck('frm');
                //frm.submit();
            });
             //회신-첨부/별첨파일 CONSULT
             $('input[name^=reply_fileInfos_append]').each(function(index){
                $reply_fileInfos_append = $(this).attr("name");
                $reply_fileList_append = $(this).prev().attr("name");
                $reply_append_cnsdreq_id = $(this).next().val();
                
                frm.target          = $reply_fileList_append;
                frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
                frm.method.value    = "forwardClmsFilePage";
                frm.fileInfoName.value = $reply_fileInfos_append;
                frm.fileFrameName.value = $reply_fileList_append;
                //회신 계약서파일
                frm.file_bigclsfcn.value = "F012";
                frm.file_midclsfcn.value = "F01202";
                frm.file_smlclsfcn.value = "F0120209";
                frm.view_gbn.value = "download";
                frm.ref_key.value = "<c:out value='${lomRq.cntrt_id}'/>@"+$reply_append_cnsdreq_id;
                getClmsFilePageCheck('frm');
                //frm.submit();
            });
	

	
	         //승인-파일 
	         $('input[name^=approve_fileInfos]').each(function(index){
	            $approve_fileInfos = $(this).attr("name");
	            $approve_fileList = $(this).prev().attr("name");
	            $approve_cnsdreq_id = $(this).next().val();
	            
	            frm.target          = $approve_fileList;
	            frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	            frm.method.value    = "forwardClmsFilePage";
	            frm.fileInfoName.value = $approve_fileInfos;
	            frm.fileFrameName.value = $approve_fileList;
	            //회신 계약서파일
	            frm.file_bigclsfcn.value = "F012";
	            frm.file_midclsfcn.value = "F01203";
	            frm.file_smlclsfcn.value = "";
	            frm.view_gbn.value = "download";
	            frm.ref_key.value = "<c:out value='${lomRq.cntrt_id}'/>";
	            getClmsFilePageCheck('frm');
	            //frm.submit();
	        });
	       //사전검토정보-파일 
	         $('input[name^=info_fileInfos]').each(function(index){
	            $info_fileInfos = $(this).attr("name");
	            $info_fileList = $(this).prev().attr("name");
	            
	            frm.target          = $info_fileList;
	            frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	            frm.method.value    = "forwardClmsFilePage";
	            frm.fileInfoName.value = $info_fileInfos;
	            frm.fileFrameName.value = $info_fileList;
	            //회신 계약서파일
	            frm.file_bigclsfcn.value = "F012";
	            frm.file_midclsfcn.value = "F01201";
	            frm.file_smlclsfcn.value = "F0120101";
	            frm.view_gbn.value = "download";
	            frm.ref_key.value = "<c:out value='${lomRq.cntrt_id}'/>";
	            getClmsFilePageCheck('frm');
	            //frm.submit();
	        });
	    }
	 
	/************************************************************************
	* 반려 표준계약서 팝업 띄우기
	*/ 
	 function openStdReject(){
		var frm = document.frm;
		
		PopUpWindowOpen('', 600, 170, true);
 		frm.target = "PopUpWindow";
 		frm.action = "<c:url value='/clm/manage/myApproval.do' />";
 		frm.method.value = "forwardPage"; 		
 		frm.submit();
	 }
 	
	 /************************************************************************
	* 반려하기
	*/ 
	function forwardRejctOpnn(){
		
		var frm = document.frm;
		var respId = new Array();
		var respNm = new Array();
		
		//$("#cntrt_srch_yn").val("N");
		
		//frm.stat_flag.value = arg;
		implJson("rejctOpnn");
	}
	 
	function implJson(method){
		var frm = document.frm;
		
		var options = {
				url: "<c:url value='/clm/manage/myApproval.do?method=" + method + "' />",
				type: "post",
				dataType: "json",
	    		success: function(responseText, statusText){
	    			viewHiddenProgress(false);
	    			if(responseText != null && responseText.length != 0) {
	    				alert("<spring:message code="clm.page.msg.manage.announce158" />");
		    			
		    			// 2012.04.20 미배정 리스트로 리턴 modified by hanjihoon
		    			forwardConsideration('list');		//표준계약서 목록으로 리턴
		    			
		    			// 그룹장의 검토작성 실행 여부 초기화
		    			//$("#cnsd_resp_div").val('N');
	    			}
	    		}
		};
		
		//viewHiddenProgress(true);
    	$("#frm").ajaxSubmit(options);
	}
	
	/**
	* 표준계약서 승인 하기
	*/
	function forwardApproval(){
		var frm = document.frm;
		if(!confirm("<spring:message code="clm.page.msg.manage.announce195" />")) return;
		//frm.submit_status.value="ApprovalContract";
		var options = {
			url: "<c:url value='/clm/manage/myApproval.do?method=ApprovalContract' />",
			type: "post",
			dataType: "json",			
			success: function(responseText, statusText) {
				//alert(responseText.returnCd);
				if(responseText.returnCd == "Y"){
					//detailAction();
					alert("<spring:message code="clm.page.msg.manage.announce158" />");
					forwardConsideration('list');		//표준계약서 목록으로 리턴
					
					
				}else{
					alert("<spring:message code="clm.page.msg.manage.announce157" />");
				}
			}
		};
		$("#frm").ajaxSubmit(options);
	}
 
	 
</script>
</head>
<body>
<div id="wrap">
	<div id="container">
			<!-- location -->
			<div class="location"><!-- Req_status_title <c:out value='${command.req_status_title}'/> -->
				<img src="<%=IMAGE %>/icon/ico_home.gif" border="0" alt="Home"/> <spring:message code="clm.page.msg.common.contractManagement" /> > <spring:message code="clm.page.msg.manage.reviewReq" /> > <strong><c:out value='${command.req_status_title}'/> <spring:message code="clm.page.msg.manage.write" /></strong>
			</div>
			<!-- //location -->
			<!-- title -->
			<div class="title">
				<h3><spring:message code="clm.page.msg.main.approvStdCont" text="NoMsg"/></h3>
			</div>
			<!-- 2012.06.04 최초의뢰일 경우, 타이틀변경 modified by hanjihoon 
			<h3><c:choose><c:when test="${(command.req_status_title == '검토')}"><spring:message code="clm.page.msg.manage.1stReview" /></c:when><c:otherwise><c:out value='${command.req_status_title}'/></c:otherwise></c:choose>&nbsp;<c:out value='${lomRq.depth_status_nm}'/></h3>-->
		
		<!-- //title -->
		<!-- content -->	
		<div id="content">
			<div id="content_in">
			<form:form name="frm" id='frm' method="post" autocomplete="off">
			<input type="hidden" name="method" id="method" value=""/>
			<input type="hidden" name="menu_id" id="menu_id" value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="curPage" id="curPage" value="<c:out value='${command.curPage}'/>"/>
			<!-- 리스트 검색 조건  -->
			<input type="hidden" name="list_mode" value="<c:out value='${command.list_mode}'/>"/>
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
			<input type="hidden" name="srch_if_sys_cd" value="<c:out value='${command.srch_if_sys_cd}' escapeXml='false'/>" />				<!-- 연계시스템 -->
			<input type="hidden" name="srch_cnclsnpurps_bigclsfcn" value="<c:out value='${command.srch_cnclsnpurps_bigclsfcn}' escapeXml='false'/>" />	<!-- 체결목적-->
			<input type="hidden" name="srch_step" value="<c:out value='${command.srch_step}' escapeXml='false'/>" />						<!-- 상태 -->
			
			<input type="hidden" name="cnsd_status" id="cnsd_status" value="<c:out value='${lomRq.cnsd_status}'/>" />
			<input type="hidden" name="prgrs_status" id="prgrs_status" value="<c:out value='${lomRq.prgrs_status}'/>" />	
			<input type="hidden" name="plndbn_req_yn" id="plndbn_req_yn" value="<c:out value='${lomRq.plndbn_req_yn}'/>" />
			<input type="hidden" name="sys_nm" id="sys_nm" value="<c:out value='${lomRq.sys_nm}'/>" />
			
			<input type="hidden" name="mis_id" /> <!-- 12/07/13 추가 consultation 에서 끌어옴 -->
			
			<!-- 의뢰시 필수 파라민터  -->
			<input type="hidden" name="status" id="status" value="" />
			<input type="hidden" name="tab_cnt" id="tab_cnt" value="<c:out value='${lomRq.total_cnt}'/>" /><!-- Tab 계약 증가 값 -->
			<input type="hidden" name="prev_cnsdreq_id" id ="prev_cnsdreq_id" value="<c:out value='${lomRq.prev_cnsdreq_id}'/>" />
			<input type="hidden" name="cnsdreq_id" id="cnsdreq_id" value="<c:out value='${lomRq.cnsdreq_id}'/>" />	 
			<input type="hidden" name="cntrt_id" id="cntrt_id"  value="<c:out value='${lomRq.cntrt_id}'/>" />
			<input type="hidden" name="req_title" value="<c:out value='${lomRq.req_title }'/>" /> <!-- 승인,반려 메일보내기 추가(2012.08.20 by jnam) -->
			<input type="hidden" name="cntrt_nm" value="<c:out value='${lomRq2.cntrt_nm}' />" />
			<input type="hidden" name="req_dt" value="<c:out value='${lomRq.req_dt}' />" />
			
			<input type="hidden" name="dmstfrgn_gbn" id="dmstfrgn_gbn"  value="<c:out value='${lomRq.dmstfrgn_gbn}'/>" />
			<c:forEach var="cntrtMt" items="${listDc}">
				<input type="hidden" name="arr_cntrt_id"  value="<c:out value='${cntrtMt.cntrt_id}' />" /> <!-- 계약_마스터_계약_ID -->	
			</c:forEach>
			<input type="hidden" name="parent_cntrt_id" id="parent_cntrt_id" />
			<input type="hidden" name="submit_status" id="submit_status" />
			<input type="hidden" name="rejct_opnn" id="rejct_opnn" />
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
	
			<!-- Mycontract 파라미터 -->
			<input type="hidden" name="ismycontract" id="ismycontract" value="<c:out value='${command.ismycontract}'/>"/>
			
			<!-- Consideration_inner_d.jsp 에 있던 hidden -->
			<input type="hidden" name="master_cnslt_no" id="master_cnslt_no"  value="<c:out value='${lomRq2.cnslt_no}'/>@<c:out value='${lomRq2.hstry_no}'/>" />
			<input type="hidden" name="master_cntrt_id" id="master_cntrt_id"  value="<c:out value='${lomRq.cntrt_id}'/>" />
	        <input type="hidden" name="file_validate" id="file_validate" value="<c:out value='${command.file_validate}'/>"/>
	        <input type="hidden" name="session_user_id" id="session_user_id" value="<c:out value='${command.session_user_id}'/>"/>
	        <input type="hidden" name="reqman_id" id="reqman_id" value="<c:out value='${lomRq.reqman_id}'/>"/>
	        <input type="hidden" name="depth_status" id="depth_status" value="<c:out value='${lomRq.depth_status}'/>"/>
		
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
			
			<!-- title -->
			<div class="title_basic">
				<h4><c:out value='${command.req_status_title}'/> <spring:message code="clm.page.msg.manage.reqInf" /><!-- <img src="</%=IMAGE %>/btn/btn_up.gif" alt="up" onclick="tit_Toggle(this,'tr_down01');"/> --></h4>
			</div>
			<!-- //title -->
				
			<!-- button -->
			<div class="btnwrap mt_22">	
				<span class="btn_all_w"><span class="mail"></span><a href="javascript:forwardApproval();"><spring:message code="clm.page.msg.manage.approval" /></a></span>	
				<span id="btn_up11" class="btn_all_w"><span class="edit"></span><a href="javascript:openStdReject();"><spring:message code="clm.page.msg.common.return" /></a></span>
				<span class="btn_all_w"><span class="list"></span><a href="javascript:forwardConsideration('list');"><spring:message code="clm.page.msg.manage.list" /></a></span>				
		     </div>
			<!-- //button -->
			
			<!-- toptable -->
			<table id="table_requestInfo" class="table-style01">
				<colgroup>              
                    <col width="12%" />
                    <col width="8%" />
                    <col width="22%" />
                    <col width="13%" />
                    <col width="16%" />
                    <col width="13%" />
                    <col width="16%" />                  
                </colgroup>
				<tr>
					<th class="borTz02"><spring:message code="clm.page.msg.manage.reqName" /></th>
				  <td colspan="6"><c:out value='${lomRq.req_title}' default="" /></td>
				</tr>
				<tr class="lineAdd">
					<th class="borTz02"><spring:message code="clm.page.msg.manage.reqPerson" /></th>
					<td colspan="2">
						<c:out value="${lomRq.reqman_nm}" />/<c:out value="${lomRq.reqman_jikgup_nm}" />/<c:out value="${lomRq.req_dept_nm}" />					
					</td>
					<th><spring:message code="clm.page.msg.manage.requDate" /></th>
					<td><c:out value="${lomRq.req_dt}" /></td>
					<th><spring:message code="clm.page.msg.manage.replyReqDate" /></th>
					<td><c:out value="${lomRq.re_demndday}" /></td>
				</tr>
				<tr id="deferOpnnArea" class="lineAdd">
					<th class="borTz02"><spring:message code="clm.page.msg.manage.relPerson" /></th><!-- RELATION_MAN  relation_man -->
					<td colspan="6"><span id="id_trgtman_nm">
					<%for(int j=0;j<listCa.size();j++){ %>
					<% ListOrderedMap lom = (ListOrderedMap)listCa.get(j);%>
					<% if((j !=0 && j !=1) && (j % 2 == 0 )){%><br/><%}%>
					<% if(j != 0 && (j % 2 !=0 )){%>,<% }%>						
					<%=lom.get("trgtman_nm") %>/<%=lom.get("trgtman_jikgup_nm") %>/<%=lom.get("trgtman_dept_nm") %>
					<% }%></span>
					</td>
				</tr>
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
					<td colspan="6"><c:out escapeXml="false" value="${lomRq.cnsd_demnd_cont}" />
					</td>
				</tr>
				<c:if test="${lomRq.plndbn_req_yn == 'Y' || lomRq.lastbn_chge_yn == 'Y'}">
				<c:if test="${!empty lomRq.lastbn_chge_yn_nm}">
				<tr>
					<th class="borTz02"><spring:message code="clm.page.msg.manage.chgRevVerYn" /></th><!-- 마지막본 변경 영부  LASTBN_CHGE_YN -->
					<td colspan="6"><c:out value='${lomRq.lastbn_chge_yn_nm}' /></td>
				</tr>
				</c:if>
				<c:if test="${!empty lomRq.plndbn_chge_cont}">
				<tr>
					<th class="borTz02"><spring:message code="clm.page.msg.manage.rsnBrkChange" /> </th>
					<td colspan="6"><c:out value='${lomRq.plndbn_chge_cont}' escapeXml="false" /></td>
				</tr>
				</c:if>
				</c:if>
				</c:if>
				<c:if test="${!empty lomCh.hold_cause}">
				<tr id="tr_deferRequestOpnn" class="lineAdd">
					<th><spring:message code="clm.page.msg.manage.rsnDelay" /></th><!-- 보류 내용 있으면 화면에 표시 -->
					<input type="hidden" name="hold_seqno" value="<c:out value='${lomCh.hold_seqno}'/>" />
					<td id="td_deferRequestOpnn" colspan="6"><c:out escapeXml="false" value='${lomCh.hold_cause}'/></td>
				</tr>
				</c:if>
				<!-- 첨부파일 -->
				<tr class="slide-target02 slide-area">
                    <th rowspan="3"><c:out value='${command.req_status_title}'/> <spring:message code="clm.page.msg.common.attachment" /></th>
                    <td class="blueD"><span class="blueD"><c:if test="${command.plndbn_req_yn == 'Y'}"><spring:message code="clm.page.msg.manage.finVr" /><br></c:if><spring:message code="clm.page.msg.manage.contract" /></span></td>
                    <td  colspan="5">
                    <c:choose>
                        <c:when test="${command.plndbn_req_yn == 'Y'}">
                            <span><spring:message code="clm.page.msg.manage.announce112" /><br><spring:message code="clm.page.msg.manage.announce168" /></span>
                        </c:when>
                        <c:otherwise>
                            <span><spring:message code="clm.page.msg.manage.announce004" /></span>
                        </c:otherwise>
                    </c:choose>
                    <iframe src="<c:url value='/clm/blank.do' />" id="fileList1" name="fileList1" frameborder="0" width="100%" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
                    </td>
                </tr> 
                <tr class="slide-target02 slide-area">
                    <td class="tal_lineL"><span class="blueD"><c:if test="${command.plndbn_req_yn == 'Y'}"><spring:message code="clm.page.msg.manage.finVr" /><br></c:if><spring:message code="clm.page.msg.manage.attachment" /></span></td>
                    <td colspan="5">
                    <c:choose>
                        <c:when test="${command.plndbn_req_yn == 'Y'}">
                            <span><spring:message code="clm.page.msg.manage.announce050" /><br><spring:message code="clm.page.msg.manage.announce168" /></span>
                        </c:when>
                        <c:otherwise>
                            <span><spring:message code="clm.page.msg.manage.announce051" /></span>
                        </c:otherwise>
                    </c:choose>
                    <!-- Sungwoo 2014-07-21 scrolling changed -->
		            <iframe src="<c:url value='/clm/blank.do' />" id="fileListEtc" name="fileListEtc" frameborder="0" class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>             
                    </td>
                </tr>
                <tr class="slide-target02 slide-area">
                    <td class="tal_lineL">
                        <c:choose>
                            <c:when test="${command.plndbn_req_yn == 'Y'}">
                                <span class="blueD"><spring:message code="clm.page.msg.manage.chgMrkd" /><br><spring:message code="clm.page.msg.manage.contract" />,<br><spring:message code="clm.page.msg.manage.attach" /></span>
                            </c:when>
                            <c:otherwise>
                                <span class="blueD"><spring:message code="clm.page.msg.common.etc" /></span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td colspan="6">
                    <c:choose>
                        <c:when test="${command.plndbn_req_yn == 'Y'}">
                            <span><spring:message code="clm.page.msg.manage.announce183" /></span>
                        </c:when>
                        <c:otherwise>
                            <span><spring:message code="clm.page.msg.manage.announce046" /></span>
                        </c:otherwise>
                    </c:choose>
                    <iframe src="<c:url value='/clm/blank.do' />" id="fileList2" name="fileList2" frameborder="0" width="100%" height="60px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>             
                    </td>
                </tr>
<!--                 <tr class="slide-target02 slide-area"> -->
<%-- 					<th class="borTz02"><spring:message code="clm.page.msg.manage.stdCont" /></th>					 --%>
<!-- 					<td colspan="6">					 -->
<%-- 						<iframe src="<c:url value='/clm/blank.do' />" id="fileListStd" name="fileListStd" frameborder="0" width="100%" height="5px" leftmargin="0" topmargin="0" scrolling="auto"></iframe> --%>
<!-- 					</td> -->
<!-- 				</tr> -->
                
                <%if("C02406".equals(lomRq.get("cntrt_status"))){//DROP %>          
                <tr id="tr_dropContractOpnn" class="slide-target02 slide-area">
                    <th><spring:message code="clm.page.msg.manage.rsnForDrop" /></th><!-- out  -->
                    <td id="td_dropContractOpnn" colspan="6"><c:out escapeXml="false" value="${lomRq.cntrt_chge_demnd_cause}" />                                
                    </td>
                </tr>           
                <%  } %>
			</table>
			<br>
<% // 회신일 경우에만 아래의 문구가 보여주게 됩니다. 12.1.14 김재원
   // 2012.03.05 문구변경 modified by hanjihoon
   // 2012.03.19 재의뢰, 최종본의뢰 문구변경 modified by hanjihoon
if("C02608".equals(lomRq.get("depth_status"))){ %>
            <div class="tL" style="margin-bottom:10px;">
	   		<c:choose>
				<c:when test="${lomRq.plndbn_req_yn == 'Y'}">
					<spring:message code="clm.page.msg.manage.announce030" /><br><spring:message code="clm.page.msg.manage.announce001" />
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
			</div>
<%}%>
			<!-- //top table -->			
			
			<div class="title_basic">
                <h4><spring:message code="clm.page.msg.manage.contInfo" /></h4>
            </div>
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
                    <th><spring:message code="clm.page.msg.manage.reqName" /></th>
                    <td colspan="5"><c:out value='${lomRq.req_title}' default="" /></td>
                </tr>
                <tr>
                    <th><spring:message code="clm.page.msg.manage.contName" /></th>
                    <td colspan="3"><span class="fL"><c:out value="${lomRq2.cntrt_nm}" /></span></td>
                    <th><spring:message code="clm.page.msg.manage.contId" /></th>
                    <td><span class="fL"><c:out value="${lomRq2.cntrt_no}" /></span></td>
                </tr>
                <tr>
                    <th><spring:message code="clm.page.msg.manage.reqPerson" /></th>
                    <td>
                        <c:out value="${lomRq2.reqman_nm}" />/
                        <c:out value="${lomRq2.reqman_jikgup_nm}" />/
                        <c:out value="${lomRq2.req_dept_nm}" />
                    </td>
                    <th><spring:message code="clm.page.msg.manage.chrgPerson" /></th>
                    <td>
                        <c:out value="${lomRq2.cntrt_respman_nm}" />/
                        <c:out value="${lomRq2.cntrt_respman_jikgup_nm}" />/
                        <c:out value="${lomRq2.cntrt_resp_dept_nm}" />
                    </td>
                    <th><spring:message code="clm.page.msg.manage.reviewer" /></th>
                    <td><c:out value="${lomRq2.cnsdmans}"  escapeXml="false"/></td>
                </tr>
                <tr>
                    <th><spring:message code="clm.page.msg.common.otherParty" /></th>
                    <td><a href="javascript:customerPop('<c:out value="${lomRq2.cntrt_oppnt_cd}" />','<c:out value="${lomRq2.cntrt_oppnt_cd}" />');"><c:out value="${lomRq2.cntrt_oppnt_nm}" /></a></td>
                    <th><spring:message code="clm.page.field.customer.registerNo" /></th>
                    <td><c:out value="${lomRq2.cntrt_oppnt_rprsntman}" /></td>
                    <th><spring:message code="clm.page.field.customer.contractRequired" /></th>
                    <td><c:out value="${lomRq2.cntrt_oppnt_type_nm}" /></td>
                </tr>
                <%  String strColspan="5";
                if("Y".equals(lomRq2.get("plndbn_req_yn")) && "C02608".equals(lomRq2.get("depth_status"))){ //최종본의뢰여부
                    strColspan="3";
                    }
                %>
                <tr>
                    <th><spring:message code="clm.page.msg.manage.contType" /></th>                   
                    <td colspan="<%=strColspan%>">                  
                        <c:out value="${lomRq.biz_clsfcn_nm}" /> / 
                        <c:out value="${lomRq.depth_clsfcn_nm}" /> / 
                        <c:out value="${lomRq.cnclsnpurps_bigclsfcn_nm}" /> / 
                        <c:out value="${lomRq.cnclsnpurps_midclsfcn_nm}" /> /
                        
                    </td>
                    <%  if("Y".equals(lomRq2.get("plndbn_req_yn")) && "C02608".equals(lomRq2.get("depth_status"))){ //최종본의뢰여부%>                   
                    <th><spring:message code="clm.page.msg.manage.autoExpYn" /></th>
                    <td><c:out value="${lomRq2.auto_rnew_yn}" /></td>                
                    <% }%>                  
                </tr>
                <tr>
                    <th><spring:message code="clm.page.msg.manage.contItm" /></th>
                    <td>
                        <c:out value="${lomRq2.cntrt_trgt_nm}" />    
                    </td>
                    <th><spring:message code="clm.page.msg.manage.contItmDtl" /></th>
                    <td colspan="3"><c:out value="${lomRq2.cntrt_trgt_det}" /></td>
                    <!-- 검토 주관 부서 없어짐
                    <th><spring:message code="clm.page.msg.manage.revDept" /></th>
                    <td><c:out value="${lomRq2.mn_cnsd_dept_nm}" /></td>
                    -->
                </tr>
                <tr>
                    <th><spring:message code="clm.page.msg.manage.contPeriod" /></th>
                    <td colspan="3">
                        <c:out value="${lomRq2.cntrtperiod_startday}" /> ~ <c:out value="${lomRq2.cntrtperiod_endday}" />
                    </td>
                    <th><spring:message code="clm.page.msg.manage.sendRcvDiv" /></th>
                    <td><c:out value="${lomRq2.payment_gbn_nm}" /></td>
                </tr>
                <%  strColspan="3";
                if(!"".equals(lomRq2.get("cntrt_untprc_expl"))){ //단가로 체결 여부
                    strColspan="0";
                    }
                %>
                <tr>
                    <th><spring:message code="clm.page.msg.manage.contAmt" /></th>
                    <td colspan="<%=strColspan%>">
                        <input type="text" id="cntrt_amt" name="cntrt_amt" style="border:1px" width="100%" value="<c:out value='${lomRq.cntrt_amt}' />" readonly="readonly"/>
                    </td>
                    <% if(!"".equals(lomRq2.get("cntrt_untprc_expl"))){  %>
                    <th><spring:message code="clm.page.msg.manage.contUnitPrice" /></th>
                    <td colspan="<%=strColspan%>">
                        <spring:message code="clm.page.msg.manage.conclSingleAmt" />
                    </td>
                    <% } %>
                    <th><spring:message code="clm.page.msg.manage.curr" /></th>
                    <td><c:out value="${lomRq2.crrncy_unit}" /></td>
                </tr>
                <%if(!"".equals(lomRq2.get("cntrt_untprc_expl"))){ //단가로 체결 여부 %>
                <tr>
                    <th rowspan="2"><spring:message code="clm.page.msg.manage.singleAmtSum" /></th>
                    <td colspan="5">
                        <c:out value="${lomRq2.cntrt_untprc_expl }" escapeXml="false"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="5"  class="tal_lineL">
                        <iframe src="<c:url value='/clm/blank.do' />" id="fileList3" name="fileList3" frameborder="0" width="100%" height="80px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
                    </td>
                </tr>
                
                <%} %>
                <tr>
                    <th><spring:message code="clm.page.msg.manage.bg" /></th>
                    <td colspan="5" >                   
                        <c:out escapeXml="false" value="${lomRq2.pshdbkgrnd_purps}" />                                                       
                    </td>
                </tr>
                <tr>
                    <th><spring:message code="clm.page.msg.manage.etcMain" /></th>
                    <td colspan="5" >                   
                        <c:out escapeXml="false" value="${lomRq2.etc_main_cont}" />
                        <c:if test="${!empty lomRq2.if_sys_cd}"> [<c:out value="${lomRq2.if_sys_cd}" escapeXml="false" />]</c:if>
                    </td>
                </tr>
                <!-- 특화속성 정보 표시  -->
                <%
                    for(int j =0; j<listTs.size(); j++){
                        Map lom = (Map)listTs.get(j);
                        if(!"".equals(StringUtil.bvl(lom.get("attr_value"), ""))){
                %>
                <tr>
                <th><span><%=(String)lom.get("attr_nm") %></span></th>
                    <td valign="top" colspan="5"><%=(String)lom.get("attr_value") %></td>
                </tr>           
                <%      }
                    }
                %>
                
                <!-- 법무 회신 최종본 회신 완료시  표시 해야할 항목 임당~~ -->               
                <c:if test='${lomRq2.cnsd_status == "C04305" && lomRq2.plndbn_req_yn == "Y"}' >
                <c:if test="${!empty lomRq2.oblgt_lmt}">
                <tr>
                    <th><spring:message code="clm.page.msg.manage.limitResp" /></th>
                    <td colspan="5"><c:out escapeXml="false" value="${lomRq2.oblgt_lmt}" /></td>
                </tr>
                </c:if>
                <c:if test="${!empty lomRq2.spcl_cond}">
                <tr>
                    <th><spring:message code="clm.page.msg.manage.etcSpecial" /></th>
                    <td colspan="5"><c:out escapeXml="false" value="${lomRq2.spcl_cond}" /></td>
                </tr>
                </c:if>
                <tr>
                    <th><spring:message code="clm.page.msg.manage.properLaw" /></th>
                    <td><c:out value="${lomRq2.loac_nm}" /></td>
                    <th><spring:message code="clm.page.msg.manage.properLawDtl" /></th>
                    <td colspan="3"><c:out escapeXml="false" value="${lomRq2.loac_etc}" /></td>
                </tr>
                <tr>
                    <th><spring:message code="clm.page.msg.manage.method" /></th>
                    <td><c:out value="${lomRq2.dspt_resolt_mthd_nm}" /></td>
                    <th><spring:message code="clm.page.msg.manage.methodDetail" /></th>
                    <td colspan="3"><c:out escapeXml="false" value="${lomRq2.dspt_resolt_mthd_det}" /></td>
                </tr>
                </c:if>
                <!-- 법무 회신 최종본 회신 완료시  표시 해야할 항목 임당~~ -->
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
                    <th><spring:message code="clm.page.msg.manage.apprDt" /></th>
                    <td><c:out value='${lomRq2.bfhdcstn_apbtday}'/></td>
                    <th><spring:message code="clm.page.msg.manage.apprType" /></th>
                    <td><c:out value="${lomRq2.BFHDCSTN_APBT_MTHD_NM}"/></td>
                </tr>
                <tr>
                    <th><spring:message code="clm.page.msg.manage.proposer" /></th>
                    <td>
                        <c:if test="${lomRq2.bfhdcstn_mtnman_nm != ''}">
                            <c:out value="${lomRq2.bfhdcstn_mtnman_nm}" />/ <c:out value='${lomRq2.bfhdcstn_mtnman_jikgup_nm}'/>/ <c:out value='${lomRq2.bfhdcstn_mtn_dept_nm}'/>
                        </c:if>
                    </td>
                    <th><spring:message code="clm.page.msg.manage.apprPer" /></th>
                    <td>
                        <c:if test="${lomRq2.bfhdcstn_apbtman_nm != ''}">
                            <c:out value='${lomRq2.bfhdcstn_apbtman_nm}'/>/ <c:out value='${lomRq2.bfhdcstn_apbtman_jikgup_nm}'/>/ <c:out value='${lomRq2.bfhdcstn_apbt_dept_nm}'/>
                        </c:if>
                    </td>
                </tr>
                
                <tr>
                    <th><spring:message code="clm.page.msg.manage.attachData" /></th>
                    <td colspan="3">
                        <spring:message code="clm.page.msg.manage.announce097" />                   
                    <iframe src="<c:url value='/clm/blank.do' />" id="fileList4" name="fileList4" frameborder="0" width="100%" height="80px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>                             
                    </td>
                </tr>
            </table>
			
			<div class='title_basic3'><spring:message code="clm.page.msg.manage.relContInf" /></div>
            <table id="tbl_rel_contract" cellspacing="0" cellpadding="0" border="0" class="table-style01">
                <colgroup>
                    <col width="12%" />
                    <col width="50%" />
                    <col width="10%" />
                    <col/>
                </colgroup>
                <tr>
                    <th class="tC"><spring:message code="clm.page.msg.manage.relation" /></th>
                    <th class="tC"><spring:message code="clm.page.msg.manage.relCont" /></th>
                    <th class="tC"><spring:message code="clm.page.msg.manage.define" /></th>
                    <th class="tC"><spring:message code="clm.page.msg.manage.relDetail" /></th>                   
                </tr>               
                <c:out value="${contRc}" escapeXml="false"/>
            </table>
            
            
            <!-- 이력정보 -->
            <div class="title_basic">
                <h4><spring:message code="clm.page.msg.manage.revHistory" /> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'contract-reviewHisView');"/></h4>
            </div>
            
            <table cellspacing="0" cellpadding="0" border="0" class="table-style01" id="contract-reviewHisView">
                <colgroup>
                    <col width="66%" />
                    <col width="10%" />
                    <col width="24%" />
                    
                </colgroup>
                <tr>
                    <th class="tC"><spring:message code="clm.page.msg.manage.reqBrkd" /></th>
                    <th class="tC"><spring:message code="clm.page.msg.manage.date" /></th>
                    <th class="tC"><spring:message code="clm.page.msg.manage.reqRevPer" /></th>
                    
                </tr>
                <%
if(review.size() > 0){
    for(int idx=0;idx < review.size();idx++){

        ListOrderedMap lom = (ListOrderedMap)review.get(idx);       
%>      
                <tr>
		            <td class="tL">
		                <span class="mR5">
		                <img src="<%=IMAGE%>/icon/ico_plus.gif" alt="show" />
		                </span>
		                <%=lom.get("cr_flan_nm") %>
		                <%=StringUtil.convertHtmlTochars((String)lom.get("title"))%>
		            </td>
		            <td class="tC"><%=lom.get("reg_dt") %></td>
    <%
        if(!"".equals(lom.get("dept_nm"))){
    %>
                    <td><%=lom.get("man_nm") %> / <%=lom.get("dept_nm") %></td>
    <%
        }else{
    %>
                    <td><%=lom.get("man_nm") %></td>
    <%
        }
    %>
                </tr>
                        <!-- 테이블안에 테이블 -->
		        <tr class="Nocol" id="tr_show" style="display:none">
		            <td colspan="3">
		                <table class="table-style_sub02">
			                <colgroup>
			                    <col class="tal_w04" />
			                    <col class="tal_w04" />
			                    <col />
			                </colgroup>

    <%
        if("CONSULT".equals(lom.get("cr_flag"))){
    %>                  
			                <tr class="Nocol">
			                    <th><spring:message code="clm.page.msg.manage.reviewReqCont" /></th>
			                    <td colspan="2">
			                        <%=lom.get("cont") %>
			                    </td>
			                </tr>
    <!--  2012.03.20 검토본변경여부, 변경내역 및 사유 추가 added by hanjihoon -->               
    <%
            if("Y".equals(lom.get("plndbn_req_yn"))){
                if(!"".equals(StringUtil.bvl(lom.get("lastbn_chge_yn_nm"), ""))){
    %>
			                <tr>
			                    <th><spring:message code="clm.page.msg.manage.chgRevVerYn" /></th>
			                    <td colspan="2"><%=lom.get("lastbn_chge_yn_nm") %></td>
			                </tr>
    <%
                }
                
                if(!"".equals(StringUtil.bvl(lom.get("plndbn_chge_cont"), ""))){
    %>
			                <tr>
			                    <th><spring:message code="clm.page.msg.manage.rsnBrkChange" /></th>
			                    <td colspan="2"><%=lom.get("plndbn_chge_cont") %></td>
			                </tr>
    <%
                }
            }
    %>
			                <tr>
			                    <th rowspan="3"><spring:message code="clm.page.msg.common.attachment" /></th>
			                    <td><span class="blueD"><spring:message code="clm.page.msg.manage.contract" /></span></td>
			                    <td>
			                        <iframe src="<c:url value='/clm/blank.do' />" id="fileList_cntrt<%=idx %>" name="fileList_cntrt<%=idx %>" frameborder="0" width="100%" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
			                        <input type="hidden" name="fileInfos_cntrt<%=idx %>"  value="" /> <!-- 이력계약서파일정보 -->
			                        <input type="hidden" name="cntrt_cnsdreq_id<%=idx %>"  value="<%=lom.get("cnsdreq_id") %>" /> <!-- 이력정보첨부파일정보 -->
			                    </td>
			                </tr>
			                <tr>
			                    <td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.manage.attachment" /></span></td>
			                    <td>
                    				<!-- Sungwoo 2014-07-21 scrolling changed -->
			                    	<iframe src="<c:url value='/clm/blank.do' />" id="fileList_append<%=idx %>" name="fileList_append<%=idx %>" frameborder="0" width="100%" class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe>
			                        <input type="hidden" name="fileInfos_append<%=idx %>"  value="" /> <!-- 이력기타파일정보 -->
			                        <input type="hidden" name="append_cnsdreq_id<%=idx %>"  value="<%=lom.get("cnsdreq_id") %>" /> <!-- 이력정보첨부파일정보 -->
			                    </td>
			                    
			                </tr>
			                <tr>
			                    <td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.common.etc" /></span></td>
			                    <td>
			                        <iframe src="<c:url value='/clm/blank.do' />" id="fileList_other<%=idx %>" name="fileList_other<%=idx %>" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
			                        <input type="hidden" name="fileInfos_other<%=idx %>"  value="" /> <!-- 이력기타파일정보 -->
			                        <input type="hidden" name="other_cnsdreq_id<%=idx %>"  value="<%=lom.get("cnsdreq_id") %>" /> <!-- 이력정보첨부파일정보 -->
			                    </td>
			                </tr>

    <%
        }else if("REPLY__".equals(lom.get("cr_flag"))){
            
            if("CLM".equals(session.getAttribute("secfw.session.sys_cd"))){
    %>
			                <tr class="Nocol">
			                    <th><spring:message code="clm.page.msg.manage.reviewOpinion" /></th>
			                    <td colspan="2">
			                        <%=lom.get("cont") %>
			                    </td>
			                </tr>
            <%          if(!"".equals(((String)lom.get("cnsd_apbt")).trim())){// 그룹장 의견 %>
                            <tr class="Nocol">
                                <th><spring:message code="clm.page.msg.manage.grpMasterOp" /></th>
                                <td colspan="2">
                                    <%=StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String)lom.get("cnsd_apbt"))) %>
                                </td>
                            </tr>
            <%          } %>			                
			                <tr>
			                <th rowspan="2"><spring:message code="clm.page.msg.common.attachment" /></th>
			                    <td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.manage.contract" /></span></td>
			                    <td>
			                        <iframe src="<c:url value='/clm/blank.do' />" id="reply_fileList_cntrt<%=idx %>" name="reply_fileList_cntrt<%=idx %>" frameborder="0" width="100%" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
			                        <input type="hidden" name="reply_fileInfos_cntrt<%=idx %>"  value="" />
			                        <input type="hidden" name="reply_cntrt_cnsdreq_id<%=idx %>"  value="<%=lom.get("cnsdreq_id") %>" />
			                    </td>
			                </tr>
			                <tr>
			                    <td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.manage.attachment" /></span></td>
			                    <td>
			                        <iframe src="<c:url value='/clm/blank.do' />" id="reply_fileList_append<%=idx %>" name="reply_fileList_append<%=idx %>" frameborder="0" width="100%" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
			                        <input type="hidden" name="reply_fileInfos_append<%=idx %>"  value="" />
			                        <input type="hidden" name="reply_append_cnsdreq_id<%=idx %>"  value="<%=lom.get("cnsdreq_id") %>" />
			                    </td>
			                    
			                </tr>
    <%
            }
        }else if("RESHOLD".equals(lom.get("cr_flag")) && !lom.get("cont").equals("")){
    %>
			                <tr class="Nocol">
			                    <th><spring:message code="clm.page.msg.manage.rsnDelay" /></th>
			                    <td colspan="2">
			                        <%=lom.get("cont") %>
			                    </td>
			                </tr>
    <%
        }
    %>
	                    </table>
	                </td>
	            </tr>
<%
    }
}else{
%>
		        <tr id="notFoundList" onmouseout="this.className='';" onmouseover="this.className='selected'">
		            <td colspan="3" align="center" class="tC"><spring:message code="clm.msg.succ.noResult" /></td>
		        </tr>
<%
}
%>
                
            </table>
			
			<div class="title_basic">
                <h4><spring:message code="clm.page.msg.manage.apprHist" /> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'contract-approveHisView');"/></h4>
			</div>
			<table cellspacing="0" cellpadding="0" border="0" class="list_basic" id="contract-approveHisView">
			    <colgroup>
			        <col width="57%" />
			        <col width="10%" />
			        <col width="8%" />
			        <col width="25%" />
			    </colgroup>
			    <thead>
			        <tr>
			            <th><spring:message code="clm.page.msg.manage.itmName" /></th>
			            <th><spring:message code="clm.page.msg.manage.status" /></th>
			            <th><spring:message code="clm.page.msg.manage.date" /></th>
			            <th><spring:message code="clm.page.msg.manage.apprPer" /></th>
			        </tr>
			    </thead>
			    <tbody>
			<%
			if(info.size() > 0){
			    
			    for(int idx=0;idx < info.size();idx++){
			
			        ListOrderedMap lom = (ListOrderedMap)info.get(idx);
			        
			%>      
			        <tr>
			            <td class="tL">
			                <span class="mR5">
			                <img src="<%=IMAGE%>/icon/ico_plus.gif" alt="show01" />
			                </span>
			                <spring:message code="clm.page.msg.manage.preApprInf" />
			            </td>
			            <td><%=lom.get("bfhdcstn_apbt_mthd_nm") %></td>
			            <td  class="tC"><%=lom.get("bfhdcstn_apbtday") %></td>
			            <td><%=lom.get("bfhdcstn_apbtman_nm") %> <%=lom.get("bfhdcstn_apbtman_jikgup_nm") %>/<%=lom.get("bfhdcstn_apbt_dept_nm") %></td>
			        </tr>
			        <!-- 테이블안에 테이블 -->
			        <tr class="Nocol" id="tr_show01" style="display:none">
			            <td colspan="4">
			                <table class="table-style_sub02">
			                    <colgroup>
			                        <col class="tal_w04" />
			                        <col />
			                    </colgroup>
			                    <tr class="Nocol">
			                        <th>
			                             <spring:message code="clm.page.msg.manage.proposer" />
			                        </th>
			                        <td colspan="3">
			                            <%=lom.get("bfhdcstn_mtnman_nm") %>
			                        </td>
			                    </tr>
			                    <tr class="Nocol">
			                        <th><span class="blueD"><spring:message code="clm.page.msg.common.attachment" /></span></th>
			                        <td colspan="3">
			                            <iframe src="<c:url value='/clm/blank.do' />" id="info_fileList" name="info_fileList" frameborder="0" width="100%" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
			                            <input type="hidden" name="info_fileInfos"  value="" />
			                        </td>
			                    </tr>
			                </table>
			            </td>
			        </tr>
			<%
			    } 
			}else {
			%>
			        <tr id="notFoundList" onmouseout="this.className='';" onmouseover="this.className='selected'">
			            <td colspan="4" align="center" class="tC"><spring:message code="clm.msg.succ.noResult" /></td>
			        </tr>
			<%
			}
			%>  
			<%
			if(approve.size() > 0){
			    /* 현재Mis_id에 속한 결재경로갯수 만큼 구분하여  루핑처리    
			        예) -----------------------------------
			             품의명     상태, 일자, 승인자
			            -----------------------------------
			            -----------------------------------
			            의뢰품의명     완결, 2011-11-01, 김길동
			            -----------------------------------
			                   결재자   [기안] 홍길동/대리/사업부
			                   [결재] 김길동/대리/사업부
			            -----------------------------------
			                  첨부파일
			            -----------------------------------
			                  
			            -----------------------------------
			            체결품의명     반려, 2011-11-01, 김길동
			            -----------------------------------
			                   결재자   [기안] 홍길동/대리/사업부
			                   [결재] 김길동/대리/사업부
			            -----------------------------------
			                  첨부파일
			            -----------------------------------
			                   
			            -----------------------------------
			            체결품의명     완결, 2011-11-01, 김길동
			            -----------------------------------
			                   결재자   [기안] 홍길동/대리/사업부
			                   [결재] 김길동/대리/사업부       
			            -----------------------------------
			                  첨부파일
			            -----------------------------------
			                       
			            -----------------------------------
			            종료품의명      완결, 2011-11-01, 김길동
			            -----------------------------------
			                   결재자   [기안] 홍길동/대리/사업부
			                   [결재] 김길동/대리/사업부
			            -----------------------------------
			                  첨부파일
			            -----------------------------------
			        
			    */
			    
			    
			    boolean bFirstLine  = false; //현재 Mis_id의 첫번째 라인여부
			    String sMemMis_id   = "" ;  //현재 Mis_id
			    int iMemAllMember   = 0 ;   //현재 결재건(MIS_ID)에 묶인  기안,합의,승인자의 갯수
			    int iMemRow         = 0 ;   //현재 iMemAllMember의 Row위치
			    String sNextMis_id  = "" ;  //다음  Mis_id
			    int iFileRowIndex   = 0 ;   //파일 index
			    int iFile_cnt       = 0;    //파일갯수  
			    String sApprovalName= "";   //승인자명 
			    String sStatus      = "";
			    String sStatusCd		= "";
			    for(int idx=0;idx < approve.size();idx++){
			
			        ListOrderedMap lom = (ListOrderedMap)approve.get(idx);
			        
			        sNextMis_id = (String)lom.get("mis_id");
			        
			        if(idx == 0){
			            sMemMis_id  = (String)lom.get("mis_id");
			            bFirstLine  = true;
			            iMemAllMember= ((java.lang.Integer)lom.get("cntrt_id_cnt"));
			            iMemRow = 1;
			        }else{
			            //다음Mis_id와  현재Mis_id가 동일하지 않으면 다른 결재권임.
			            if(! sNextMis_id.equals(sMemMis_id)){
			                sMemMis_id = sNextMis_id;
			                bFirstLine  = true;
			                iMemAllMember= ((java.lang.Integer)lom.get("cntrt_id_cnt"));
			                iMemRow = 0;
			                
			                iFileRowIndex ++;
			            }
			            
			            iMemRow ++;
			        }
			        
			        if(bFirstLine) {
			            bFirstLine = false;
			%>      
			        <tr>
			            <td class="tL">
			                <span class="mR5">
			                <img src="<%=IMAGE%>/icon/ico_plus.gif" alt="show01" />
			                </span>
			                <a href="javascript:openHis('<%=sMemMis_id %>');"><%=lom.get("title") %></a>
			            </td>
			            <td><%=lom.get("status") %></td>
			            <td><%=lom.get("create_date") %></td>
			            <td id='sp_approval<%=iFileRowIndex %>'></td>
			        </tr>
			        <!-- 테이블안에 테이블 -->
			        <tr class="Nocol" id="tr_show01" style="display:none">
			            <td colspan="4">
			                <table class="table-style_sub02">
			                    <colgroup>
									<!-- Sungwoo 2014-05-14 Approve 상태 추가에 따른 width 재조정 -->
									<col width="10%" />
									<col width="60%"/>
									<col width="15%"/>
									<col width="15%"/>
			                    </colgroup>
			                    <tr class="Nocol">
			                        <th rowspan='<%=lom.get("cntrt_id_cnt")%>'>
			                             <spring:message code="clm.page.msg.manage.approver" />
			                        </th>
			                        <td>
			                            <strong>[<%=lom.get("cd_nm") %>]</strong> <%=lom.get("user_name") %>/<%=lom.get("duty") %>/<%=lom.get("dept_name") %>/<%=lom.get("group_name") %>
			                        </td>
									<!-- Sungwoo 2014-05-14 Approve 상태 추가 -->
									<td><%=lom.get("prg_status")%></td>
									<td><%=lom.get("approved")%></td>
			                    </tr>
			<%
			        }else if(! bFirstLine){
			            sApprovalName = "";
			            sStatus = (String)lom.get("cd_nm");
			            sStatusCd = (String)lom.get("cd");
						//if(sStatus != null && sStatus.equals("결재") ){
						if(sStatus != null && sStatusCd.equals("1") ){
			                sApprovalName = (String)lom.get("user_name") + "/" + (String)lom.get("duty") + "/" + (String)lom.get("dept_name");
			%>                  
			                    <script>document.getElementById('sp_approval<%=iFileRowIndex %>').innerHTML = '<%=sApprovalName%>';</script>
			                    <%} %>
			                    <tr class="Nocol">
			                        <td class="tal_lineL">
			                            <strong>[<%=lom.get("cd_nm") %>]</strong> <%=lom.get("user_name") %>/<%=lom.get("duty") %>/<%=lom.get("dept_name") %>/<%=lom.get("group_name") %>
			                        </td>
									<!-- Sungwoo 2014-05-14 Approve 상태 추가 -->
									<td><%=lom.get("prg_status")%></td>
									<td><%=lom.get("approved")%></td>
			                    </tr>
			<%
			        }
			         
			        //해당Mis_id의 최종 Row이면
			        if(iMemRow == iMemAllMember) {
			            iFile_cnt   = ((BigDecimal)lom.get("file_cnt")).intValue();
			            //첨부 파일이 있으면    
			            if(iFile_cnt > 0) {
			%>
			                    <tr class="Nocol">
			                        <th><span class="blueD"><spring:message code="clm.page.msg.common.attachment" /><%=lom.get("file_cnt") %></span></th>
			                        <!-- Sungwoo 2014-05-14 Approve 상태 변경으로 인한 colspan 변경 -->
									<td colspan="3">
			                            <iframe src="<c:url value='/clm/blank.do' />" id="approve_fileList<%=iFileRowIndex %>" name="approve_fileList<%=iFileRowIndex %>" frameborder="0" width="100%" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
			                            <input type="hidden" name="approve_fileInfos<%=iFileRowIndex %>"  value="" /> <!-- 이력기타파일정보 -->
			                            <input type="hidden" name="approve_cnsdreq_id<%=iFileRowIndex %>"  value="<%=lom.get("cnsdreq_id") %>" /> <!-- 이력정보첨부파일정보 -->
			                        </td>
			                    </tr>
			                <%} %>  
			                </table>
			            </td>
			        </tr>
			<%           
			        }
			    }
			}
			%>
			    </tbody>
			</table>
			
			<% if(defer.size() > 0){ %>

				<div class="title_basic">
                    <h4><spring:message code="clm.page.msg.manage.conclDelayHstr" /> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'contract-deferHisView');"/></h4>
				</div>
				
				<table cellspacing="0" cellpadding="0" border="0" class="list_basic" id="contract-deferHisView">
				    <colgroup>
				        <col width="10%" />
				        <col width="50%" />
				        <col width="15%" />
				        <col width="25%" />
				    </colgroup>
				    <thead>
				        <tr>
				            <th><spring:message code="clm.page.msg.common.div" /></th>
				            <th><spring:message code="clm.page.msg.manage.reason" /></th>
				            <th><spring:message code="clm.page.msg.manage.regiDate" /></th>
				            <th><spring:message code="clm.page.msg.common.registrant" /></th>
				        </tr>
				    </thead>
				    <tbody>
				<%
				if(defer.size() > 0){
				    for(int idx=0;idx < defer.size();idx++){
				
				        ListOrderedMap lom = (ListOrderedMap)defer.get(idx);        
				%>      
				        <tr>
				            <td>
				                <%if( lom.get("hold_endday") == null ){ %>
				                <spring:message code="clm.page.msg.manage.delay" />
				                <%} else { %>
				                <spring:message code="clm.page.msg.manage.cnclDelay" />
				                <%} %>
				            </td>
				            <td class="tL"><%=lom.get("hold_cause") %></td>
				            <td><%=lom.get("reg_dt") %></td>
				            <td><%=lom.get("reg_nm") %>/<%=lom.get("dept_nm") %>/<%=lom.get("jikgup_nm") %></td>
				        </tr>
				<%
				    }
				} else {
				%>
				        <tr id="notFoundList" onmouseout="this.className='';" onmouseover="this.className='selected'">
				            <td colspan="4" align="center" class="tC"><spring:message code="clm.msg.succ.noResult" /></td>
				        </tr>
				<%
				}
				%>
				    </tbody>
				</table> 
				<%} %>
			
			
			<!-- //tab_content -->			
			<!-- 이력정보 -->
			<div id="contractHis-list"></div>
			<!-- //이력정보 -->	
			<!-- table001 -->
			<!-- //able001 -->	
			
			<div class="t_titBtn">
      			<div class="btn_wrap_c">		
      				<span class="btn_all_w"><span class="mail"></span><a href="javascript:forwardApproval();"><spring:message code="clm.page.msg.manage.approval" /></a></span>		 
				   	<span id="btn_up11" class="btn_all_w"><span class="edit"></span><a href="javascript:openStdReject();"><spring:message code="clm.page.msg.common.return" /></a></span>
					<span class="btn_all_w"><span class="list"></span><a href="javascript:forwardConsideration('list');"><spring:message code="clm.page.msg.manage.list" /></a></span>	
				</div>			
			</div>
			<!-- //button -->
			</form:form>
			
		
		
		</div>
		</div>
	</div>
</div>
		
		<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- // footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	
</body>
</html>


