<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%--
/**
 * 파  일  명 : Consideration_requestHQ_p.jsp
 * 프로그램명 : HQ 검토의뢰 입력 팝업
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2014.05.07
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
%>
<html>
<head>
<meta sci="Consideration_requestHQ_p.jsp" />
<meta charset="utf-8" />
<meta http-equiv="x-ua-compatible" content="IE=edge" >
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css"    type="text/css" rel="stylesheet">
<link href="<%=CSS%>/popup.css" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="/script/secfw/jquery/calendar/jquery.mousewheel.min.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>

<script type="text/javascript" src="/crosseditor/js/namo_scripteditor.js"></script>

<script language="JavaScript" type="text/JavaScript" >

/**
 * 페이지 초기에 실행 되는 내용
 */
 function onLoad(){
	
    fileSetting();
    
    setTiele();
 
 }


/*
 * 내용 저장 하기
 */
function saveOpinion(gbn){
	
	var frm = document.frm;
	
	var confirmMessage = "";
	
	if("req" == gbn){
		confirmMessage = "<spring:message code='las.page.field.hqrequest.page23' />";   // 의뢰 하시겠습니까? 의뢰 시 의뢰 내용은 수정이 불가능 합니다.
	} else {
		confirmMessage = "<spring:message code='las.page.field.hqrequest.page24' />";   // 임시저장 하시겠습니까?
	}
	
	var msg_comp = "<spring:message code='las.msg.succ.process' />";   // 처리 되었습니다.
	
	// 필수 항목 체크 hq_req_title, hq_req_cont	
	if("" == frm.hq_req_title.value){
		alert("<spring:message code='las.page.field.hqrequest.page25' />");   // HQ 검토의뢰 제목은 필수 입니다.
		return;
	}
	
	if("" == frm.hq_cnsd_demnd_cont.value){
		alert("<spring:message code='las.page.field.hqrequest.page26' />");  //HQ 검토의뢰 내용은 필수 입니다.
		return;
	}
	
	
	
	
	if(confirm(confirmMessage)){	
		
		hq_fileList.UploadFile(function(uploadCount){
			
			//첨부파일 업로드 결과 잘못된 형식의 파일이 올라간 경우
			if(uploadCount == -1){
				initPage();	//첨부파일창 Reload
				alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
				return;
			}
			
			if(uploadCount == 0){  // The locally reviewed contract draft must be attached here.
				alert("<spring:message code='las.page.field.hqrequest.page34' />");
				return;
			}
		
		if("req" == gbn){   // HQ 검토 의뢰
			
			var prev_cnsdreq_id = frm.prev_cnsdreq_id.value;
		    var cnsdreq_id = frm.cnsdreq_id.value;
		    var hq_cnsd_status =frm.hq_cnsd_status.value;
		    var cnsd_level =frm.cnsd_level.value;
		    var hq_req_yn  = frm.hq_req_yn.value;
		    
		    
		    // 원 의뢰가 없는 경우
		    if("" == prev_cnsdreq_id){
		    	
		    	if("1" != cnsd_level && "C16201" == hq_cnsd_status){
		    		//alert("1");
			    	if("C16203" == hq_cnsd_status || "C16205" == hq_cnsd_status){
			    		//alert("2");
			    		frm.hq_cnsd_status.value="C16203";
			    	} else if("C16207" == hq_cnsd_status){
			    		//alert("3");
			    		frm.hq_cnsd_status.value="C16202";
			    	} else if("C16203" == hq_cnsd_status){
			    		//alert("3-1");
			    		frm.hq_cnsd_status.value="C16202";
			    	} else {
			    		//alert("3-2");
			    		frm.hq_cnsd_status.value="C16203";
			    	}
			    	
			    	if("" == hq_req_yn || "N" == hq_req_yn){
			    		frm.hq_cnsd_status.value="C16202";
			    	}
			    	
			    } else if("1" != cnsd_level && "C16203" == hq_cnsd_status){
			    	//alert("4");
			    	frm.hq_cnsd_status.value="C16203";
			    } else {
			    	//alert("4-1");
			    	frm.hq_cnsd_status.value="C16202";
			    }
		    	
		    	
		    	
		    // 원 의뢰가 있는 경우	
		    } else {
		    	
		    	if("1" != cnsd_level && "C16201" == hq_cnsd_status){
		    		//alert("5");
			    	if("C16203" == hq_cnsd_status || "C16205" == hq_cnsd_status){
			    		//alert("6");
			    		frm.hq_cnsd_status.value="C16203";
			    	} else if("C16207" == hq_cnsd_status){
			    		//alert("7");
			    		frm.hq_cnsd_status.value="C16203";
			    	} else if("C16203" == hq_cnsd_status){
			    		//alert("7-1");
			    		frm.hq_cnsd_status.value="C16203";
			    	} else {
			    		//alert("7-2");
			    		frm.hq_cnsd_status.value="C16203";
			    	}
			    } else if("1" == cnsd_level && "C16201" == hq_cnsd_status){
			    	//alert("8");
			    	frm.hq_cnsd_status.value="C16203";
			    } else {
			    	//alert("8-1");
			    	//alert("8-1");
			    	if("" == hq_req_yn || "N" == hq_req_yn){
			    		//alert("8-2");
			    		frm.hq_cnsd_status.value="C16202";
			    	} else {
			    		//alert("8-3");
			    		frm.hq_cnsd_status.value="C16203";
			    	}
			    }
		    	/*
		    	if("" == hq_req_yn || "N" == hq_req_yn){
		    		frm.hq_cnsd_status.value="C16202";
		    	} else {
		    		frm.hq_cnsd_status.value="C16203";
		    	}
		    	*/
		    }
			
			var options = {   
					url: "<c:url value='/clm/review/consideration.do?method=modifyHqRequest' />",
					type: "post",
					dataType: "json",
					success: function(responseText, statusText) {
						if(responseText.returnCnt != 0) {
							var html = "";
							$.each(responseText, function(entryIndex, entry) {
								
								var return_val = entry['return_val'];
								
								if(return_val == '1'){
									alert(msg_comp); // 처리하였습니다.							
									
									$(opener.location).attr("href", "javascript:forwardConsideration('SAVE')"); //  부모창의 내용을 입시저장 된다.
									
									self.close();  // 입력창을 닫아 버린다
								} else {
									alert("<spring:message code="clm.page.msg.manage.announce157" />");
								}									

							});						
						}	 
						
						// viewHiddenProgress(false) ;	
					}
			};		
			$("#frm").ajaxSubmit(options);	
			
		} else {            // 임시 저장
			
			frm.hq_cnsd_status.value="C16201";
			
			var options = {   
					url: "<c:url value='/clm/review/consideration.do?method=insertHqRequest' />",
					type: "post",
					dataType: "json",
					success: function(responseText, statusText) {
						if(responseText.returnCnt != 0) {
							var html = "";
							$.each(responseText, function(entryIndex, entry) {
								
								var return_val = entry['return_val'];
								
								if(return_val == '1'){
									alert(msg_comp); // 처리하였습니다.							
									
									$(opener.location).attr("href", "javascript:forwardConsideration('SAVE')"); //  부모창의 내용을 입시저장 된다.
									
									self.close();  // 입력창을 닫아 버린다
								} else {
									alert("<spring:message code="clm.page.msg.manage.announce157" />");
								}									

							});						
						}	 
						
						// viewHiddenProgress(false) ;	
					}
			};		
			$("#frm").ajaxSubmit(options);	
		}
		
		});
	}		
	
 }
 
 function setTiele(){
	 var frm = document.frm;
	 
	 var req_title = "<c:out value='${resultList[0].hq_req_title}'/>";
	 
	 if("" == req_title){
		 frm.hq_req_title.value = "<c:out value='${command.hq_req_title}' />"; 
	 } else {
		 frm.hq_req_title.value = req_title;
	 }
	 
 }
 
 function fileSetting(){
	 
	 var frm = document.frm;
	 var file_key = "";
	 var cnsd_level = frm.cnsd_level.value;
	 
	 //cnsd_level = cnsd_level -1;
	 
	 // 첨부파일의 경우 임시 저장인지 판단 하여 처리 한다.
	 file_key = frm.cntrt_id.value+"@"+frm.hq_cnsdreq_id.value+"_"+cnsd_level; 
	 
	 //alert("cnsd_level = " +cnsd_level);
	 //alert("file_key = "+file_key);
	 
	 //HQ 첨부 파일
     frm.target          = "hq_fileList";
     frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
     frm.method.value    = "forwardClmsFilePage";
     frm.file_bigclsfcn.value = "H010";
     frm.file_midclsfcn.value = "H01001";
     frm.file_smlclsfcn.value = "H0100101";
     frm.ref_key.value = file_key;
     frm.view_gbn.value = "modify";
     frm.multiYn.value = "Y";
     frm.fileInfoName.value = "fileInfos";
     frm.fileFrameName.value = "hq_fileList";
     frm.submit();
 }

</script>
</head>
<body onload="onLoad();" class="pop" >

<!-- header -->
<h1><spring:message code="las.page.field.hqrequest.page19"/> <span class="close" onclick="javascript:self.close();" title="close"></span></h1>
<!-- //header -->
<div class="pop_area">
<!-- Popup Detail -->
		
		<!-- pop_content -->
		<div class="pop_content" style='height:544px'>
		<form:form name="frm" id="frm" method="post" autocomplete="off">
		<input type="hidden" name="method" 		value="">
		<input type="hidden" name="menu_id" 	value="<c:out value='${command.menu_id}'/>" />
		<input type="hidden" name="cntrt_id" 	value="<c:out value='${command.cntrt_id}'/>" />
		<input type="hidden" name="cnsdreq_id" 	value="<c:out value='${command.cnsdreq_id}'/>" />
		<input type="hidden" name="hq_cnsdreq_id" 	value="<c:out value='${command.hq_cnsdreq_id}'/>" />
		<input type="hidden" name="cnsd_level" 	value="<c:out value='${command.cnsd_level}'/>" />
		<input type="hidden" name="cnsd_opnn"     value=""/>
		<input type="hidden" name="hq_cnsd_status"     value="<c:out value='${command.hq_cnsd_status}'/>"/>
		<input type="hidden" name="prev_cnsdreq_id"     value="<c:out value='${command.prev_cnsdreq_id}'/>"/>
		<input type="hidden" name="hq_req_yn" value="<c:out value='${command.hq_req_yn}'/>" /><!-- hq의뢰 여부 -->
		
		<!-- 첨부파일정보 -->
		<input type="hidden" name="mod_cntrt_id" id ="mod_cntrt_id" value="<c:out value='${resultList[0].CNTRT_ID}'/>" />
		<input type="hidden" name="hq_fileList" id="hq_fileList"  value="6" /> <!-- 저장될 파일 정보 -->	
		
		<input type="hidden" name="fileInfoName"    value="" /> <!-- 첨부파일 정보가 리턴될 객체명 -->
	    <input type="hidden" name="fileFrameName"    value="" /> <!-- 첨부파일 화면 iFrame 명 -->
	    <input type="hidden" name="fileInfos"  id="fileInfos"		value="" />
			
		<input type="hidden" name="file_bigclsfcn"  value="" /> <!-- 대분류 -->	
		<input type="hidden" name="file_midclsfcn"  value="" /> <!-- 중분류 -->	
		<input type="hidden" name="file_smlclsfcn"  value="" /> <!-- 소분류 -->	
		<input type="hidden" name="ref_key"     value="" /> <!-- 키값 -->				
		<input type="hidden" name="view_gbn"    value="" /> <!-- 첨부파일 화면 -->
		<input type="hidden" name="multiYn" value="" /><!-- 멀티 -->
		
		
					
		<table cellspacing='0' cellpadding='0' class='table-style01' border='0'>
			<colgroup>
				 <col width="15%" />
				 <col width="85%" />
			</colgroup>
			<tbody>
			<tr>
				<!-- <th><spring:message code="las.page.msg.manage.OldReviewOpRequ" /></th><!-- HQ 검토 의뢰 제목 --> 
				<th><spring:message code="las.page.field.hqrequest.page20" /> <span class='astro'>*</span></th><!-- HQ 검토 의뢰 제목 -->
				<td>
				    <input type="text" name="hq_req_title" id="hq_req_title" class="text_full" maxlength="50" value="<c:out value='${resultList[0].hq_req_title}' escapeXml=''/>" />
				</td>
			</tr>
			<tr>
			    <th><spring:message code="las.page.field.hqrequest.page21" /> <span class='astro'>*</span></th><!-- HQ 검토 의뢰 내용 -->
			    <td>
			    <span id="curByteExpl_body_mine">0</span>/<spring:message code="clm.page.msg.manage.stringLen4000" /><br>
			    <textarea rows="10" cols="" name="hq_cnsd_demnd_cont" id="hq_cnsd_demnd_cont" cols="30" rows="3" class="text_area_full" maxLength="4000" onkeyup="frmChkLenLang(this,4000,'curByteExpl_body_mine','')"><c:out value='${resultList[0].hq_cnsd_demnd_cont}'/></textarea>
			    </td>
			</tr>
			<tr>
				<th><spring:message code="las.page.field.hqrequest.page22" /><span class='astro'>*</span></th><!-- HQ 검토 의뢰 첨부파일 -->
				<td>
				    <div class='ico_maxsize fR'>Max Size : <span>50MB</span></div>
				     <spring:message code='las.page.field.hqrequest.page35' />
	                <iframe src="<c:url value='/clm/blank.do' />" id="hq_fileList" name="hq_fileList" frameborder="0" class='addfile_iframe' scrolling="no" allowTransparency="true"></iframe>
				</td>
			</tr>
			</tbody>
		</table>
					
		<!-- button 	 	<div class="btn_wrap fR mt20">	 	</div>  //button -->
	 	
       </form:form>
</div>
</div>
<!--footer -->
<div class="pop_footer">
	<!-- button -->
	 <div class="btn_wrap fR">
	     <span class="btn_all_w"><span class="save"></span><a href="javascript:saveOpinion('req');"><spring:message code="las.page.field.statistics.req" /></a></span>
	     <span class="btn_all_w"><span class="save"></span><a href="javascript:saveOpinion('save');"><spring:message code="clm.page.msg.common.save" /></a></span>
	     <span class="btn_all_w" onclick="javascript:self.close();"><span class="cancel"></span><a><spring:message code="clm.page.msg.common.close" /></a></span>
	 </div>
<!-- //button -->
</div>
<!-- //footer -->
<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</body>
</html>