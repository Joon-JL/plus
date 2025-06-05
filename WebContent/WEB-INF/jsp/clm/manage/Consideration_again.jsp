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
 * 파  일  명 : Consideration_again.jsp
 * 프로그램명 : 재의뢰
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2013.04
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
<link href="<%=CSS%>/common.css"   type="text/css" rel="stylesheet"></link>
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet"></link>
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
		initCal("re_demndday");
	});
	
	function forwardConsultation(arg){
		//detailConsultation
		var frm = document.frm;		
		frm.target = "_self";		 
		frm.action = "<c:url value='/clm/manage/consultation.do?method=detailConsultation&page_gbn=modify' />";
		frm.submit();
	}
	
	/**
	* page Action
	*/
	function forwardConsideration(arg){
		var frm = document.frm;
		
		//alert(arg);
		
		frm.target = "_self";		 
		frm.action = "<c:url value='/clm/manage/consideration.do' />";
		
		if(arg == "save"){	//임시저장
			alert("View X");
		}else if(arg == "req"){		//검토의뢰	
			frm.status.value = "forwardReq";
			frm.method.value = "prevCnsdReqCopyConsideration";
			
		}else if(arg == "last"){	//최종본회신		
			frm.status.value="forwardLast";
			frm.method.value = "prevCnsdReqCopyConsideration";
			
		}else if(arg == "list"){	//목록			
			frm.method.value = "listManageConsideration";			
		}else if(arg == "mod"){	//	수정	
			frm.status.value = "forwardModify";
			frm.method.value = "forwardModifyConsideration";			
		}
		
		//comLayerPopCenter('ProgressLayer1');
		frm.submit();
		
	}
	
	function prevCnsdReqCopyConsideration(arg){
		var frm = document.frm;
		if(arg == "req"){
			if(!confirm("<spring:message code="clm.page.msg.manage.announce145" />")) return;
			frm.status.value = "forwardReq";
		}else if(arg == "last"){			
			if(!confirm("<spring:message code="clm.page.msg.manage.announce187" />")) return;
			alert("<spring:message code="clm.page.msg.manage.announce002" />");
			
			frm.status.value="forwardLast";	
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
					forwardConsideration('mod');
				}
			}
		};
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
		  //var frm = document.frm;
		  
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
	  
	  function titleTabClick(tab,cntrt_id){  
		
		  var frm = document.frm;
		  frm.submit_status.value = "tab_save";//임시저장
		  
		  frm.body_mime.value= frm.wec.MIMEValue;		  
		  //의뢰내용 이동복사 나모가 이동안되는현상 제거 
		  document.frm.cnsd_demnd_cont.value = frm.wec.BodyValue;
		  
		 
		  		  
		  if(validateClmForm(frm)){		  
			  //alert("저장하고자 하는 마스터 아이디 "+frm.cntrt_id.value);
			  var options = {				  
					url: "<c:url value='/clm/manage/consideration.do?method=modifyAgainReqConsideration' />",
					type: "post",
					dataType: "json",			
					success: function(responseText, statusText) {						
						//alert(responseText.returnCd);
						//alert(responseText.returnCnsdReqId);						
						//alert(responseText.returnCd +"/"+returnCnsdReqId +"/"+ returnCntrtId);
						if(responseText.returnCd == undefined){
							alert("<spring:message code="clm.page.msg.manage.announce157" />");
						}else{
							//if(responseText.exeStatus =="insert"){
							//	var html = "<input type=\"hidden\" value=\""+responseText.returnCntrtId+"\" name=\"arr_cntrt_id\" id=\"arr_cntrt_id\" />";
							//	$("#cnsdreq_id").before(html);
							//} 
							//cnsdreqId 새로운 포맷에 의뢰 아이디 설정  11.01 chaahyunjin
							//if(!$('#cnsdreq_id').val()){
							//	$('#cnsdreq_id').val(responseText.returnCnsdReqId);
							//}
							titleTabMove(tab,cntrt_id);
						 //initPage();
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
			  				viewHiddenProgress(true);
			  				$("#frm").ajaxSubmit(options);
	 	    				
					 	});//end fileList2
	 				});//end fileListEtc 		
	 			 });//end fileList1
		  }			  
	  }
	
	  /**
	  *계약탭 클릭
	  */
	  function titleTabMove(tab,cntrt_id){
		  
		var tab_cnt = document.frm.tab_cnt.value;
		document.frm.cntrt_id.value = cntrt_id;
		//alert(" 계약 "+ tab + " 로 이동 ~");	
		var i;
		for( i=1; i<=tab_cnt; i++){			
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
			}
		};
		$("#frm").ajaxSubmit(options);
		viewHiddenProgress(false);
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
  		}  
  		
  		if("deleteRequest" == arg){
  			if(confirm(msg)){ 
  				processConsideration(arg);
  			}
		}else if("dropRequest" == arg || "deferRequest" == arg || "dropContract" == arg){
			if(confirm(msg)){
				opnnConsideration(arg);
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
						}else{
							
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
	  	       
		 //alert(obj.strMethod );
		 //alert(obj.Cd);
		 
        var strMethod =   obj.strMethod;
        //var strCd = obj.Cd;		//Y or N
        
        if("deferRequest" == strMethod){	//deferRequest 의뢰 보류
        	//$('#deferOpnnArea').append(returnValue.opnn);	
        	
        		detailDeferDrop(strMethod);
  			        	
        }else if("dropContract" == strMethod){	//dropContract 계약 drop
        	//$('#dropRequest').append(returnValue.opnn);
        	 
        		detailDeferDrop(strMethod);
  			
        	
        }else{	        	
        	
        }
	 }
	 
	 function detailDeferDrop(arg) {
		 //var strMethod="";
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
		 	};
		 	$("#frm").ajaxSubmit(options);
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

	/**
  	* 재검토의뢰건 임시저장 / 검토의뢰  
  	*/
  	function againReqConsideration(tab_num,arg){
		
  		var frm = document.frm;
  		var tab_cnt = frm.tab_cnt.value;
  		frm.submit_status.value = arg;
  		
		if("save" == arg){	//임시저장
			if(!confirm("<spring:message code="clm.page.msg.manage.announce137" />")) return;
  			frm.method.value = "modifyAgainReqConsideration";
		}else if("again" == arg){	//재검토의뢰
			if(!confirm("<spring:message code="clm.page.msg.manage.announce144" />")) return;
  			frm.method.value = "modifyAgainReqConsideration";
		}else if("tab_save" == arg){ //TAB 계약건 임시저장
			alert("<spring:message code="clm.page.msg.manage.announce198" /> ");
			frm.method.value = "modifyAgainReqConsideration";
		}else{
			alert("<spring:message code="clm.page.msg.manage.announce143" />");
		} 
		
		if(validateClmForm(frm)){
			if(arg == "again"){
				//계약서 필수 입력 항목에서 빠짐 2012.01.05
  				//if("Y" == $('#file_validate').val() && $("#cnsdreq_id").val() != ""){
  	  			//	alert("각 계약에 계약서 첨부파일을 필수 입력 사항 입니다. \n\r 각 계약건 검토확인 하여 주시기 바랍니다. ");
  	  			//	return;
  	  			//}
  			}
			
			//의뢰일과 회신요청일 
  			var str_req_dt = frm.req_dt.value.replace('-','').replace('-','');
  			var str_re_demndday = frm.re_demndday.value.replace('-','').replace('-','');
  			var str_db_demndday = "<c:out value='${lomRq.re_demndday}' />".replace('-','').replace('-','');
  			 	  		
  	  		if("" == stripHTMLtag(frm.wec.BodyValue)){//검토요청 내용 CNSD_DEMND_CONT
	  			alert("<spring:message code="clm.page.msg.manage.announce018" />");return;  	  			
	  		}
  	  		
  			if(eval(str_req_dt) > eval(str_re_demndday)){
  				alert("<spring:message code="clm.page.msg.manage.announce211" />  ");
  				return;
  			}

  			if(eval(str_db_demndday) > eval(str_re_demndday)){
  				alert("<spring:message code="clm.page.msg.manage.announce210" /> ");
  				return;
  			}
		
		frm.body_mime.value = frm.wec.MIMEValue;
		
		
 		var options = {
			url: "<c:url value='/clm/manage/consideration.do?method=modifyAgainReqConsideration' />",
			type: "post",
			async: false,
			dataType: "json",			
			success: function(responseText, statusText) {//undefined				
				//alert(responseText.returnCd +"/"+responseText.returnVal +"/"+ responseText.returnMsg);
				if(responseText.returnVal == "FA"){
					alert(responseText.returnMsg);
				}else{					
					if("tab_save" == arg){
						var i;
						for(i=1; i<=tab_cnt; i++){			
							if(tab_num==i){
								document.getElementById("tab_li"+i+"").className = "on";
						    }else{
							    document.getElementById("tab_li"+i+"").className = "";
						    }		  
						}//end form
						detailContractMaster();
						//tit_Toggle(this, 'tr_down02');
					}else{
						//목록으로 이동 
						forwardConsideration("list");
					}// end if("tab_save")
				}//end if(responseText.exeStatus)
			}//end success: function
		};//end var options
		
		//계약관련#1 계약서
		 fileList1.UploadFile(function(uploadCount){
			if(uploadCount == -1){
				initPage();
				alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
				return; 					
			}
			//계약서 필수 입력 사항에서 빠짐
			//if(uploadCount == 0 && arg != "save"){
 			//	alert("계약서 <spring:message code='secfw.msg.error.fileNon' />");
            //    return;
 			//}
		//계약서#2 첨부/별첨
		fileListEtc.UploadFile(function(uploadCount){
	  		if(uploadCount == -1){
	  			initPage();
	  			alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
	  			return; 					
	  		}
	  		
		 //계약관련#3 기타
	  	 fileList2.UploadFile(function(uploadCount){
	  		if(uploadCount == -1){
	  			initPage();
	  			alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
	  			return; 					
	  		}
	  		viewHiddenProgress(true);
	  		$("#frm").ajaxSubmit(options);
	  	 });//end fileList2
		 });//end fileListEtc 		
		 });//end fileList1
		}		 
	  }
  		 
	 function initPage(){	 
		
		var frm = document.frm;
		$('input[id^=fileInfos]').val("");
		
	   //계약관련 #1-계약서
	    frm.target          = "fileList1";
	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    = "forwardClmsFilePage";
	    frm.file_bigclsfcn.value = "F012";
	    frm.file_midclsfcn.value = "F01202";
	    frm.file_smlclsfcn.value = "F0120201";
	    frm.view_gbn.value = "modify";
		frm.ref_key.value = $('#master_cntrt_id').val()+"@"+$('#cnsdreq_id').val();				
	    frm.fileInfoName.value = "fileInfos1";
	    frm.fileFrameName.value = "fileList1";
	    frm.submit();
	    
	  	//fileListEtc 계약관련 - 첨부/별첨 
	    frm.target          = "fileListEtc";
	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    = "forwardClmsFilePage";
	    frm.file_bigclsfcn.value = "F012";
	    frm.file_midclsfcn.value = "F01202";
	    frm.file_smlclsfcn.value = "F0120208";
	    frm.view_gbn.value = "modify";
	    frm.ref_key.value = $('#master_cntrt_id').val()+"@"+$('#cnsdreq_id').val();
	    frm.fileInfoName.value = "fileInfosEtc";
	    frm.fileFrameName.value = "fileListEtc";
	    frm.submit();
	    
	  //계약관련 #2-기타
	    frm.target          = "fileList2";
	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    = "forwardClmsFilePage";
	    frm.file_bigclsfcn.value = "F012";
	    frm.file_midclsfcn.value = "F01202";
	    frm.file_smlclsfcn.value = "F0120205";
	    frm.view_gbn.value = "modify";
	    frm.ref_key.value = $('#master_cntrt_id').val()+"@"+$('#cnsdreq_id').val();
	    frm.fileInfoName.value = "fileInfos2";
	    frm.fileFrameName.value = "fileList2";
	  	frm.submit();	  	    
	    
	  //계약관련 #4
	    frm.target          = "fileList4";
	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    = "forwardClmsFilePage";
	    frm.file_bigclsfcn.value = "F012";
	    frm.file_midclsfcn.value = "F01201";
	    frm.file_smlclsfcn.value = "F0120101";
	    frm.view_gbn.value = "download";
	    frm.ref_key.value = $('#master_cntrt_id').val();
	    frm.fileInfoName.value = "fileInfos4";
	    frm.fileFrameName.value = "fileList4";
	  //frm.submit();
	    getClmsFilePageCheck('frm');
	    
	  //계약관련 #5 회신 검토의뢰 회신 계약서 파일
	    frm.target          = "fileList5";
	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    = "forwardClmsFilePage";
	    frm.file_bigclsfcn.value = "F012";
	    frm.file_midclsfcn.value = "F01202";
	    frm.file_smlclsfcn.value = "F0120202";
	    frm.ref_key.value = "<c:out value='${lomRq.cntrt_id}' />@<c:out value='${lomRq.cnsdreq_id}' />";	
	    frm.fileInfoName.value = "fileInfos5";
	    frm.fileFrameName.value = "fileList5";
	  //frm.submit();
	    getClmsFilePageCheck('frm');    
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
	 
</script>
</head>
<body>
<div id="wrap">
	<!-- container -->
	<div id="container">
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
		
		<input type="hidden" name="cnsd_status" id="cnsd_status" value="<c:out value='${lomRq.cnsd_status}'/>" />
		
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
		<input type="hidden" name="fileInfos" id="fileInfos" value="" /> <!-- 저장될 파일 정보 -->
		<input type="hidden" name="fileInfos1" id="fileInfos1" value="" /> <!-- 저장될 파일 정보 -->
		<input type="hidden" name="fileInfosEtc"  id="fileInfosEtc" value="" /> <!-- 저장될 파일 정보 -->	
		<input type="hidden" name="fileInfos2"  id="fileInfos2" value="" /> <!-- 저장될 파일 정보 -->	
		<input type="hidden" name="fileInfos3"  id="fileInfos3" value="" /> <!-- 저장될 파일 정보 -->	
		<input type="hidden" name="fileInfos4"  id="fileInfos4" value="" /> <!-- 저장될 파일 정보 -->
		<input type="hidden" name="fileInfos5"  id="fileInfos5" value="" /> <!-- 저장될 파일 정보 -->	
		<input type="hidden" name="fileInfos6"  id="fileInfos6" value="" /> <!-- 저장될 파일 정보 -->
		
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
		
		<!-- 첨부파일 명 생성에 필요한 계약 유형 파라미터 -->
		<!-- sysCd = StringUtil.bvl(tempvo.getSys_cd(), "");
		regOperdiv = StringUtil.bvl(tempvo.getReg_operdiv(), "");
		OppntCd = StringUtil.bvl(tempvo.getCntrt_oppnt_cd(), "");
		localeCd = StringUtil.bvl(tempvo.getSession_user_locale(), "");
		bizClsFcn = StringUtil.bvl(tempvo.getBiz_clsfcn(), "");
		depthClsFcn = StringUtil.bvl(tempvo.getDepth_clsfcn(), "");
		bigClsFcn = StringUtil.bvl(tempvo.getCnclsnpurps_bigclsfcn(), "");
		midClsFcn = StringUtil.bvl(tempvo.getCnclsnpurps_midclsfcn(), ""); -->
						
		<input type="hidden" name="cntrt_oppnt_cd" id="cntrt_oppnt_cd" required alt="<spring:message code="clm.page.msg.manage.othPartyCode" htmlEscape="true" />" value="<c:out value='${lomRq.cntrt_oppnt_cd}'/>"/>		
		<input type="hidden" name="biz_clsfcn" id="biz_clsfcn" value="<c:out value='${lomRq.biz_clsfcn}'/>" />
		<input type="hidden" name="depth_clsfcn" id="depth_clsfcn" value="<c:out value='${lomRq.depth_clsfcn}'/>" />
		<input type="hidden" name="cnclsnpurps_midclsfcn" id="cnclsnpurps_midclsfcn" value="<c:out value='${lomRq.cnclsnpurps_midclsfcn}'/>"  />
		<input type="hidden" name="cnclsnpurps_bigclsfcn" id="cnclsnpurps_bigclsfcn" value="<c:out value='${lomRq.cnclsnpurps_bigclsfcn}'/>" />	
	
		<!-- location -->
		<div class="location"><!-- Req_status_title <c:out value='${command.req_status_title}'/> -->
			<img src="<%=IMAGE %>/icon/ico_home.gif" border="0" alt="Home"></img> <spring:message code="clm.page.msg.common.contractManagement" /> > <spring:message code="clm.page.msg.manage.reviewReq" /> > <strong><c:out value='${command.req_status_title}'/><spring:message code="clm.page.msg.manage.reqMake" /></strong>
		</div>
		<!-- //location -->
		<!-- title -->
		<div class="title">
			<!-- 2012.06.04 최초의뢰일 경우, 타이틀변경 modified by hanjihoon -->
			<h3><c:choose><c:when test="${(command.req_status_title == '검토')}"><spring:message code="clm.page.msg.manage.1stReview" /></c:when><c:otherwise><c:out value='${command.req_status_title}'/></c:otherwise></c:choose></h3>
		</div>
		<!-- //title -->		
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
					<h4><c:out value='${command.req_status_title}'/> <spring:message code="clm.page.msg.manage.reqInf" /><!-- <img src="<%=IMAGE %>/btn/btn_up.gif" alt="up" onclick="tit_Toggle(this,'tr_down01');"/> --></h4>
				</div>
				<!-- //title -->
				<!--View -->
				<!-- 수정/검토의뢰/최종본작성/목록 -->			
				<!-- button -->
				<div class="btn_wrap_t02">		
					<span class="btn_all_w"><span class="preview"></span><a href="javascript:considerationPreview();"><spring:message code="clm.page.msg.manage.preview" /></a></span>
					<span class="btn_all_w"><span class="tsave"></span><a href="javascript:againReqConsideration('','save');"><spring:message code="clm.page.msg.manage.tmpSave" /></a></span>
					<span class="btn_all_w"><span class="check2"></span><a href="javascript:againReqConsideration('','again');"><spring:message code="clm.page.msg.manage.reply" /></a></span>				
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
					<td><input type="hidden" name="req_dt" alt="<spring:message code="clm.page.msg.manage.requDate" htmlEscape="true" />" value="<c:out value="${lomRq.req_dt}" />" /><c:out value="${lomRq.req_dt}" /></td>
					<th><spring:message code="clm.page.msg.manage.replyReqDate" /></th>
					<td><input type="text" name="re_demndday" id="re_demndday" required alt="<spring:message code="clm.page.msg.manage.replyReqDate" htmlEscape="true" />" value="<c:out value="${lomRq.re_demndday}" />" class="text_calendar02"/></td>
				</tr>
				<% if(!listCa.isEmpty()){ %>
				<tr id="deferOpnnArea" class="lineAdd">
					<th><spring:message code="clm.page.msg.manage.relPerson" /></th>
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
				<tr>
					<!-- 2012.03.19 "최종본 요청내용" => "최종본 검토의뢰내용" 으로 명칭 변경 modified by hanjihoon -->
					<c:choose>
						<c:when test="${lomRq.plndbn_req_yn == 'Y'}">
							<th class="borTz02"><spring:message code="clm.page.msg.manage.finalVer" /><br/><spring:message code="clm.page.msg.manage.reviewReqCont" /></th>
						</c:when>
						<c:otherwise>
							<th class="borTz02"><c:out value='${command.req_status_title}'/> <spring:message code="clm.page.msg.manage.reqCont" /></th>
						</c:otherwise>
					</c:choose>
					<td colspan="5"><!-- <img src="<%=IMAGE %>/icon/texteditor.jpg" alt="<spring:message code="clm.page.msg.manage.texteditor" htmlEscape="true" />" /> //-->
					<input type="hidden" name="cnsd_demnd_cont" id="cnsd_demnd_cont" alt="<spring:message code="clm.page.msg.manage.reqContent" htmlEscape="true" />" value="<c:out value="${lomRq.cnsd_demnd_cont}" />" />
					<input type="hidden" name="body_mime" id="body_mime" value="<c:out value="${lomRq.cnsd_demnd_cont}" />" />		
					<%@ include file="/WEB-INF/jsp/secfw/common/NamoEditInclude2.jspf"%></td>
				</tr>
				<%if(!lomCh.isEmpty()){ %>
				<tr id="tr_deferRequestOpnn" class="lineAdd">
					<th><spring:message code="clm.page.msg.manage.rsnDelay" /></th><!-- 보류 내용 있으면 화면에 표시 -->
					<td id="td_deferRequestOpnn" colspan="5"><c:out escapeXml="false" value='${lomCh.hold_cause}'/></td>
				</tr>
				<%} %>
			</table>
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
		          		<li id="tab_li<c:out value='${list.rn}'/>" class="on"><a href="javascript:titleTabClick('<c:out value='${list.rn}'/>','<c:out value='${list.cntrt_id}'/>');"><c:out value='${list.tab_nm}'/></a></li>
				</c:when>
				<c:otherwise>
						<li id="tab_li<c:out value='${list.rn}'/>"><a href="javascript:titleTabClick('<c:out value='${list.rn}'/>','<c:out value='${list.cntrt_id}'/>');"><c:out value='${list.tab_nm}'/></a></li>
				</c:otherwise>	
				</c:choose>				
				</c:forEach>									
			</ul>			
			<div class="btn_wrap_t03">
			<% if("C02606".equals(lomRq.get("depth_status")) ||  "C02608".equals(lomRq.get("depth_status"))){	//회신완료 %>				
				<span id ="id_dropContract" class="btn_all_w"><span class="reset"></span><a href="javascript:actionConsideration('dropContract');"><spring:message code="clm.page.msg.manage.dropCont" /></a></span>			
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
			<div id="contractHis-list"></div>
			<!-- //이력정보 -->	
			<!-- table001 -->
			<!-- //able001 -->	
			<div class="t_titBtn">
      		<div class="btn_wrap_c">
				<span class="btn_all_w"><span class="preview"></span><a href="javascript:considerationPreview();"><spring:message code="clm.page.msg.manage.preview" /></a></span>
				<span class="btn_all_w"><span class="tsave"></span><a href="javascript:againReqConsideration('','save');"><spring:message code="clm.page.msg.manage.tmpSave" /></a></span>
				<span class="btn_all_w"><span class="check2"></span><a href="javascript:againReqConsideration('','again');"><spring:message code="clm.page.msg.manage.reply" /></a></span>				
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
<script language="javascript" for="wec" event="OnInitCompleted()">
	//document.wec[0].Value ="<HTML><HEAD>" + "<TITLE></TITLE><META http-equiv=Content-Type content='text/html; charset=utf-8'><META content=ActiveSquare name=GENERATOR>" +"</HEAD><BODY></BODY></HTML>";
	document.wec.Value =document.frm.cnsd_demnd_cont.value;	 
	document.wec.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');	
	document.wec.SetDefaultFontSize("9");    
	document.wec.EditMode = 1;	
</script>
</body>
</html>