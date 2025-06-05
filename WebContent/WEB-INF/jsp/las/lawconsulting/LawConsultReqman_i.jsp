<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.PageUtil"%>
<%@ page import="com.sds.secframework.common.util.Token"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%@ include file="/WEB-INF/jsp/common/messageUtil.jsp" %>


<%
    // 메뉴네비게이션 스트링
    String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	
	// 자문 권한 관련자
	ArrayList listCa = (ArrayList)request.getAttribute("listCa");
%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.main.title" /></title>

<link href="<%=CSS%>/las.css"    type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->

<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/CommonProgress.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/ui.core.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script language="javascript">
    function initPage(){
		
		
        frm.target          = "fileList";
        frm.action = "<c:url value='/clms/common/clmsfile.do' />";
        frm.method.value    = "forwardClmsFilePage";

        frm.submit();
    	initCalendar();
        //alert(document.frm.body_mime.value);
    }

    /*자문유형(상담종류) 팝업*/
    function popup(){
        var f = document.frm ;
        f.method.value = "forwardPopupLawConsult" ;
        f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
        PopUpWindowOpen('', 610, 310, true);
        frm.target = "PopUpWindow";
        f.submit() ;
    }

    /* 팝업 창에서 체크한 상담종류에 대한 값을 받음 */
    function returnType(type, type_name){
        frm.consult_type.value = type;
        //frm.consult_type_name.value = type_name;
        //frm.consult_type_name.setAttribute('width',frm.consult_type_name.value.length);
        //frm.selected_type_name.value=type_name;
        
        $("#selected_type_name").empty();
        $("#selected_type_name").append(type_name);
        
    }

    function cancel() {
        viewHiddenProgress(true) ;
        var f = document.frm ;
        f.method.value = "listLawConsult" ;
        f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
        f.target = "_self" ;
        f.submit() ;
    }

    function tempSave(){
        var f = document.frm;
        f.check_prgrs_status.value = "tempSaveReqman";
        insert();
    }

    function send(){
        var f = document.frm;
        if(f.check_prgrs_status.value != 'resend' && f.hstry_no.value == 0){
            f.check_prgrs_status.value = "send";
        }
        insert();
    }

	function insert() {
    	var f = document.frm;
        var confirmMessage = "";
        
        if(f.hstry_no.value == 0) {
        	if(f.check_prgrs_status.value == "tempSaveReqman") {
            	confirmMessage = "<spring:message code='secfw.msg.ask.tempSave' />";
            }else{
            	confirmMessage = f.cnslt_no.value == "" ? "<spring:message code='secfw.msg.ask.insert' />"
						: "<spring:message code='secfw.msg.ask.update' />";
            }
        }else{
        	confirmMessage = f.check_prgrs_status.value == "tempSaveReqman" ? "<spring:message code='secfw.msg.ask.tempSave' />"
            		: "<spring:message code='secfw.msg.ask.insert' />";
        }
           
		if (frm.title.value == "") {
        	alert("<spring:message code='las.msg.alert.titleIsReq'/>");
            return;
		}

		if (frm.consult_type.value == "") {
        	alert("<spring:message code='las.page.field.lawconsulting.selectConsultType'/>");
            return;
        }
            
        if (confirm(confirmMessage)) {
        	viewHiddenProgress(true);
            	 
           	fileList
               	.UploadFile(function(uploadCount) {
                       //첨부파일 업로드 결과 잘못된 형식의 파일이 올라간 경우
                       if (uploadCount == -1) {
                           initPage(); //첨부파일창 Reload
                           alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
                           viewHiddenProgress(false);
                           return;
                       }
                       
					//if(f.hstry_no.value == 0){
					if ('<c:out value="${command.prgrs_status}"/>' == 'S05' || '<c:out value="${command.prgrs_status}"/>' == 'S03') {
					    f.method.value = "insertLawConsult";
					} else {
					    f.method.value = f.cnslt_no.value == "" ? "insertLawConsult" : "modifyLawConsult";
					}
					//}
					//else {
					//   f.method.value = "insertLawConsult";
					//}
					f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />";
					f.target = "_self";
					f.submit();
			});
		}
	}
	
	
	// 회신요청일 초기화
	function initCalendar(){
		var frm = document.frm;
		
		initCal("req_reply_dt");     //회신요청일 초기화
		
		if("<c:out value='${lom.cnslt_no}'/>" == ""){
			frm.req_reply_dt.value = "<c:out value='${command.req_reply_dt}'/>";
		}else{
			frm.req_reply_dt.value = "<c:out value='${lom.req_reply_dt}'/>";
		}
		
	}
	
	// 오늘일짜 구하기 
	function getToday() {
			
		var NowDate = new Date();
		yyyy = NowDate.getFullYear();
		mm = Number(NowDate.getMonth()) + 1;
		dd =  NowDate.getDate();
			
		str_mm = "";
		str_dd = "";
			
		if ( mm < 10) {
			str_mm = "0" + mm;
		} else {
			str_mm = String(mm);
		}
		
	  	if (dd < 10) {
	  		str_dd = "0"+ dd;
		} else {
			str_dd = String(dd);
		}
	  	
	  	return yyyy+"-"+str_mm+"-"+str_dd;
	  	
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
	     //var Feture = "fullscreen=no,toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=" + (bScroll ? "yes" : "no") + ",resizable=no,left=" + Left + ",top=" + Top + ",width=" + popupwidth + ",height=" + popupheight;
		 PopUpWindow = window.open(surl, "PopUpWindow" , Feture);
		 PopUpWindow.focus();
	 }
	 
	 
	/**
	 * 참조자 검색팝업 리턴 객체 받기 
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
        	//$('#reqman_nm').append("<option value=" + arrInfo[2]+">" + arrInfo[2]+ "</option>");
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
	
</script>
</head>
<!--  2012.04.26 Alert 수정 modified by hanjihoon  -->
<body onload="initPage()" autocomplete="off">
	<div id ="wrap">
  	<!-- container -->
  	<div id="container">
    	
    	<!-- Location -->
    	<div class="location">
      		<img src="<%=IMAGE%>/icon/ico_home.gif" border="0" alt="Home" /><c:out value='${menuNavi}' escapeXml="false"/>
	    </div>	
    	<!-- //Location -->
    	
    	<!-- title -->
    	<div class="title">
      		<h3><spring:message code="las.page.field.lawconsulting.lawReqAdv" /></h3>
        </div>
    	<!-- //title -->
    
    
	    <!-- content -->
	    <div id="content">
	    <div id="content_in">
	    
		    <form:form name="frm" id="frm" autocomplete="off">
			<input type="hidden" name="method" value=""/>
			<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>"/>
			<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>"/>
			<input type="hidden" name="isForeign" value="<c:out value='${command.isForeign}'/>"/>
			<input type="hidden" name="cnslt_no" value="<c:out value='${command.cnslt_no}'/>"/>
			<input type="hidden" name="hstry_no" value="<c:out value='${command.hstry_no}'/>"/>
			<input type="hidden" name="consult_type" value="<c:out value='${command.consult_type}'/>"/>
			<input type="hidden" name="check_prgrs_status" value="<c:out value='${command.check_prgrs_status}'/>"/>
			<input type="hidden" name="prgrs_status" value="<c:out value='${lom.prgrs_status}'/>"/>
	
			<!-- 작성완료 후 페이지 포워딩 구분을 위한 페이지 구분정보 -->
			<c:choose>
				<c:when test="${command.fwd_gbn != null}">
					<input type="hidden" name="fwd_gbn" value="<c:out value='${command.fwd_gbn}'/>" />
				</c:when>
				<c:otherwise>
					<input type="hidden" name="fwd_gbn" value="req"/>
				</c:otherwise>
			</c:choose>
		
	
			<!-- 검색 정보 -->
			<input type="hidden" name="srch_start_ymd" value="<c:out value='${command.srch_start_ymd}'/>"/>
			<input type="hidden" name="srch_end_ymd" value="<c:out value='${command.srch_end_ymd}'/>"/>
			<input type="hidden" name="srch_prgrs_status" value="<c:out value='${command.srch_prgrs_status}'/>"/>
			<input type="hidden" name="srch_title" value="<c:out value='${command.srch_title}'/>"/>
			<input type="hidden" name="srch_cont" value="<c:out value='${command.srch_cont}'/>"/>
			<input type="hidden" name="srch_reg_nm" value="<c:out value='${command.srch_reg_nm}'/>"/>
			<input type="hidden" name="srch_reg_dept" value="<c:out value='${command.srch_reg_dept}'/>"/>
			<input type="hidden" name="srch_consult_type" value="<c:out value='${command.srch_consult_type}'/>"/>
			<input type="hidden" name="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}'/>"/>
			<input type="hidden" name="srch_reg_id" value="<c:out value='${command.srch_reg_id}'/>"/>
			<input type="hidden" name="srch_reception" value="<c:out value='${command.srch_reception}'/>"/>
			<!-- 관련자 데이타 설정  -->
			<input type="hidden" name="chose_client" id="chose_client" />
			<!-- 첨부파일정보 -->
			<input type="hidden" name="fileInfos"      value="" />
			<input type="hidden" name="file_bigclsfcn" value="F003" />
			<input type="hidden" name="file_midclsfcn" value="F00301" />
			<input type="hidden" name="file_smlclsfcn" value="" />
			<input type="hidden" name="ref_key"        value="<c:out value='${command.cnslt_no}'/>@<c:out value='${command.hstry_no}'/>"/>
			<input type="hidden" id="isStdCont" name="isStdCont" value="N"/>
			
			<c:choose>
			  	<c:when test="${command.cnslt_no == '' || command.check_prgrs_status == 'resend'}">
			    	<input type="hidden" name="view_gbn"    value="upload" />
				</c:when>
			  	<c:otherwise>
			    	<input type="hidden" name="view_gbn"    value="modify" />
			  	</c:otherwise>
			</c:choose>
	
			<!-- 이중등록 방지 -->
			<input type="hidden" name="TOKEN"  value="<%=Token.set(request) %>" />
    
      		<!-- upper button -->
			<div class="btnwrap">
        		<c:choose>
					<c:when test="${command.top_role != 'etc'}">
	            		<c:choose>
		           			<c:when test="${otherModify == 'Y'}">
		           				<span class="btn_all_w"><span class="modify"></span><a href="javascript:send();"><spring:message code="las.page.button.modify"/></a></span>
		            		</c:when>
							<c:otherwise>
							<!-- 수정화면에서 request 바로가기 막음. -->
								<c:if test="${command.prgrs_status != 'S01'}">
									<span class="btn_all_w"><span class="edit"></span><a href="javascript:send();"><spring:message code="las.page.button.lawconsult.newreq"/></a></span>
								</c:if>
								<span class="btn_all_w"><span class="save"></span><a href="javascript:tempSave();"><spring:message code="las.page.button.lawconsult.tempsave" /></a></span>
			        		</c:otherwise>
		        		</c:choose>
            		</c:when>
            		<c:otherwise>
            			<c:choose>
            				<c:when test="${command.prgrs_status == 'S02' || command.prgrs_status == 'S03' || command.prgrs_status == 'S05' || command.prgrs_status == 'S03'}">
								<span class="btn_all_w"><span class="edit"></span><a href="javascript:send();"><spring:message code="las.page.button.lawconsult.resend"/></a></span>
                  				<span class="btn_all_w"><span class="save"></span><a href="javascript:tempSave();"><spring:message code="las.page.button.lawconsult.tempsave" /></a></span>
                			</c:when>
                			<c:otherwise>
                				<!-- Sungwoo Replaced Send button -->
                				<c:choose>
                    				<c:when test="${command.cnslt_no == ''}">
                    					<span class="btn_all_w"><span class="mail"></span><a href="javascript:send();"><spring:message code="las.page.button.lawconsult.newreq" /></a></span>
                    				</c:when>
                    				<c:otherwise>
                    					<span class="btn_all_w"><span class="save"></span><a href="javascript:tempSave();"><spring:message code="las.page.button.lawconsult.tempsave" /></a></span>
                    				</c:otherwise>
                  				</c:choose>
                			</c:otherwise>
						</c:choose>
            		</c:otherwise>
				</c:choose>
				
				<span class="btn_all_w"><span class="list"></span><a href="javascript:cancel();"><spring:message code="las.page.button.lawconsult.list" /></a></span>
			</div>
	
		    <!-- //upper button -->
		    <!-- view -->
			<table class="table-style01">
				<colgroup>
		        	<col width="15%"/>
		          	<col width="35%" />
		          	<col width="15%"/>
		          	<col width="35%" />
		        </colgroup>
		        <tbody>
		         	<tr>
			            <th><spring:message code="las.page.field.lawconsult.title" /> <span class='astro'>*</span> </th>
			            <td colspan="3">
			            	<input id="title" name="title" type="text" value="<c:out value='${command.title}' escapeXml='false'/>" class="text_full" alt="<spring:message code="las.page.field.lawconsulting.title"/>" required="required" maxLength="128"/>
			        	</td>
		          	</tr>
		          	<tr>
			            <th><spring:message code="las.page.field.lawconsult.reqman_nm" /></th>
			            <td><c:out value='${command.reg_nm}'/></td>
			            <th><spring:message code="las.page.field.lawconsult.department" /></th>
			            <td><c:out value='${command.reg_dept_nm}'/></td>
		          	</tr>
		          	<!--!@#2013.11.22 : CC(참조인)추가 -->
		          	<tr class="lineAdd">
						<th><spring:message code="clm.page.msg.manage.relPerson" /> <img src="<%=IMAGE %>/common/step_q.gif" alt="info" title="<spring:message code="clm.page.msg.manage.announce064" htmlEscape="true" />" /></th>
						<td colspan="3">
						
							<span class="btn_all_b" onclick="javascript:openChooseClient();"><span class="add"></span><a> <spring:message code="clm.page.msg.manage.add" htmlEscape="true" /></a></span><!-- 추가 -->									
							<span id="id_trgtman_nm">
<% 
							if(listCa !=null){
								for(int j=0;j<listCa.size();j++){	
									ListOrderedMap lom = (ListOrderedMap)listCa.get(j);
									if((j !=0 && j !=1) && (j % 2 == 0 )){%><br/><%}
									if(j != 0 && (j % 2 !=0 )){%>,<% }%>	
										<input type="hidden" name="arr_demnd_seqno" id="arr_demnd_seqno" value="<%=lom.get("seqno") %>" /><input type="hidden" name="arr_trgtman_id" id="arr_trgtman_id" value="<%=lom.get("respman_id") %>" /><input type="hidden" name="arr_trgtman_nm" id="arr_trgtman_nm" value="<%=lom.get("respman_nm") %>" /><input type="hidden" name="arr_trgtman_jikgup_nm" id="arr_trgtman_jikgup_nm" value="<%=lom.get("respman_jikgup") %>" /><input type="hidden" name="arr_trgtman_dept_nm" id="arr_trgtman_dept_nm" value="<%=lom.get("resp_dept") %>" />
										<%=lom.get("respman_nm") %>/<%=lom.get("respman_jikgup") %>/<%=lom.get("resp_dept")%>					
<% 
								}
							}
%>
							</span>
						</td>
					</tr>
		          	
		          	<tr>
						<th><spring:message code="las.page.field.lawconsult.consulttype" /></th>
		            	<td colspan="3"> <!-- 자문유형 선택 --> 
		              		<a href='javascript:popup()'><img src='<%=IMAGE%>/btn/btn_select.gif' alt="<spring:message code="las.page.field.lawconsulting.select"/>" /></a>
							<%--<input id="consult_type_name" name="consult_type_name" type="text" style="width:10%;" class="text_search" readonly="readonly" onkeydown="if(event.keyCode==13){event.returnValue = false;javascript:popup();}"/><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:popup();" class="cp" alt="search"/> --%>
							<%--<input id="selected_type_name" name="selected_type_name" type="text" style="width:85%;border:0px;" readonly value="<c:out value='${command.consult_type_name}' escapeXml='false'/>" /> --%>
							<span id="selected_type_name"><c:out value='${command.consult_type_name}'/></span>
		            	</td>
		          	</tr>
		          	
		          	
		          	<!--!@#2013.11.22 : 회신요청일 추가 (Requested date for reply) -->
		          	<tr>
		          		<th><spring:message code="clm.page.field.contract.request.returndt"/></th> <!-- 회신요청일 -->
		          		<td colspan="3">
		          			<input type="text" id="req_reply_dt" name="req_reply_dt" value="" class="text_calendar02" style='width:74px' />
		          		</td>
		          	</tr>
		          	
		          	
					<tr>
			            <th><spring:message code="clm.page.msg.common.content" /></th>
			            <td colspan="3">
			            	<span id="curByteExpl_body_mine">0</span>/<spring:message code="clm.page.msg.manage.stringLen2000" /><br>
			            	<c:choose>
			              		<c:when test="${command.cont!=null}"> 
			              			<textarea name="body_mime" id="body_mime" rows="10" cols="15" maxLength="2000" onkeyup="frmChkLenLang(this,2000,'curByteExpl_body_mine','<%=langCd%>')" class="text_area_full"><c:out value='${command.cont}'/></textarea>
			              		</c:when>
				              	<c:otherwise>
				              		<textarea name="body_mime" id="body_mime" rows="10" cols="15" maxLength="2000" onkeyup="frmChkLenLang(this,2000,'curByteExpl_body_mine','<%=langCd%>')" class="text_area_full"></textarea>
				              	</c:otherwise>
							</c:choose>
			            </td>
					</tr>
					<tr>  
						<th><spring:message code="las.page.field.lawconsult.attachfile" /> <img src="/images/las/ko/common/step_q.gif" alt="info" title="DOC, DOCX, GUL, PDF, OCX, XLS, XLSX, PPT, PPTX,&#13;JPG, HWP, TIF, RTF, ZIP, PTX, TML, TXT, LSX, EML, BMP, GIF, MSG, ALZ, DOT &#13;&#13;<spring:message code='las.msg.com.fileFilter'/>" /></th>
						<td colspan="3">
							<div class='ico_maxsize fR'>Max Size : <span>50MB</span></div>
							<!-- Sungwoo Replaced scroll option 2014/08/04 -->
							<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>
						</td>
					</tr>
				</tbody>
    		</table>
    		<!-- //view -->
      
    
			<!-- lower button -->
			<div class='btnwrap mt10'>
				<c:choose>
					<c:when test="${command.top_role != 'etc'}">
		           		<c:choose>
		           	  		<c:when test="${otherModify == 'Y'}">
		           	  			<span class="btn_all_w"><span class="modify"></span><a href="javascript:send();"><spring:message code="las.page.button.modify"/></a></span>
		              		</c:when>
			          		<c:otherwise>
			          		<!-- 수정화면에서 request 바로가기 막음. -->
								<c:if test="${command.prgrs_status != 'S01'}">
			          				<span class="btn_all_w"><span class="edit"></span><a href="javascript:send();"><spring:message code="las.page.button.lawconsult.newreq"/></a></span>
			          			</c:if>
			                	<span class="btn_all_w"><span class="save"></span><a href="javascript:tempSave();"><spring:message code="las.page.button.lawconsult.tempsave" /></a></span>
			          		</c:otherwise>
			          	</c:choose>
					</c:when>
		        	<c:otherwise>
		          		<c:choose>
            				<c:when test="${command.prgrs_status == 'S02' || command.prgrs_status == 'S03' || command.prgrs_status == 'S05' || command.prgrs_status == 'S03'}">
								<span class="btn_all_w"><span class="edit"></span><a href="javascript:send();"><spring:message code="las.page.button.lawconsult.resend"/></a></span>
                  				<span class="btn_all_w"><span class="save"></span><a href="javascript:tempSave();"><spring:message code="las.page.button.lawconsult.tempsave" /></a></span>
                			</c:when>
                			<c:otherwise>
                				<!-- Sungwoo Replaced Send button -->
                				<c:choose>
                    				<c:when test="${command.cnslt_no == ''}">
                    					<span class="btn_all_w"><span class="mail"></span><a href="javascript:send();"><spring:message code="las.page.button.lawconsult.newreq" /></a></span>
                    				</c:when>
                    				<c:otherwise>
                    					<span class="btn_all_w"><span class="save"></span><a href="javascript:tempSave();"><spring:message code="las.page.button.lawconsult.tempsave" /></a></span>
                    				</c:otherwise>
                  				</c:choose>
                			</c:otherwise>
		          		</c:choose>
		        	</c:otherwise>
				</c:choose>
				<span class="btn_all_w"><span class="list"></span><a href="javascript:cancel();"><spring:message code="las.page.button.lawconsult.list" /></a></span>
    		</div>
    		<!-- //lower button -->
    
	    </form:form>
	    </div>
	    </div>
	    <!-- //content -->
	    <br/>
	    
	    
    	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
  		</div>
  		<!-- //container -->
		</div>
		<!-- footer -->
    <script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
    <!-- // footer -->
	<jsp:include page="/WEB-INF/jsp/secfw/common/DlgAlert.jsp"/>
</body>
</html>