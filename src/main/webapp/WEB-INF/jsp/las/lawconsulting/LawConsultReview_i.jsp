<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@page import="com.sds.secframework.common.util.PageUtil"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%@page import="com.sds.secframework.common.util.Token"%>
<!-- 2014-02-25 Kevin Added. -->
<%@page import="java.util.ArrayList" %>
<%@page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");

	// 2014-02-25 Kevin. CC ADDED.
	//자문 권한 관련자
	ArrayList listCa = (ArrayList)request.getAttribute("listCa");
%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.main.title" /></title>

<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->

<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/ui.core.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script type="text/javascript" src="/crosseditor/js/namo_scripteditor.js"></script>

<script language="javascript">

	$(document).ready(function(){
	    getCodeSelectByUpCd("frm", "crrncy_unit", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=A&combo_grp_cd=C5&combo_type=S&combo_del_yn=N&combo_selected=KRW');
	});

	
	function initPage(){

		if(frm.isModify.value != '1'){
			frm.ref_key.value = "";
		}
		
	    frm.target			= "fileList";
	    frm.file_midclsfcn.value = "F00301";
	    frm.action = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value  	= "forwardClmsFilePage";
		frm.fileInfoName.value = "fileInfos";
        frm.fileFrameName.value = "fileList";

		frm.submit();

	}
	

	function init(){
		alertMessage('<c:out value='${command.return_message}'/>') ;
	}
	
	function hidden_layer(layer_name)
	{
	if (layer_name == '') return;
	eval(layer_name+".style.display='none'");
	}

	function show_layer(layer_name)
	{
	if (layer_name == '') return;
	eval(layer_name+".style.display='block'");
	}

	/*임시저장*/
	function tempSave(){
		var f = document.frm;
		var confirmMessage = "<spring:message code='secfw.msg.ask.tempSave' />";
		
		f.check_prgrs_status.value = "tempSave";

		insert(confirmMessage);
	}
	
	/*발신*/
	function send(){
		var f = document.frm;
		var confirmMessage = "<spring:message code='secfw.msg.ask.send' />";
		
		//그룹장이 직접 발신할 시
		if(f.isGrpmgr.value == 'Y'){
			f.check_prgrs_status.value = "reply";
		}
		//담당자가 발신시
		else{		
			//회신전 결제확인 값이 Y일시는 미결	
			if($('input[name=grpmgr_re_yn]:checked').val() == 'Y'){
				f.check_prgrs_status.value = "undecided";
			}
				
			else
				f.check_prgrs_status.value = "reply";
		}
		
		insert(confirmMessage);
	}
	
	function insert(Message) {
		var f = document.frm ;
		var uploadCnt = -1;
		var reTest = new RegExp("A20");

		if(validateForm(document.frm)) {		
			var confirmMessage = Message;
			
			if(confirm(confirmMessage)){
				viewHiddenProgress(true) ;
				
				fileList.UploadFile(function(uploadCount){
					uploadCnt = uploadCount;
	                 //첨부파일 업로드 결과 잘못된 형식의 파일이 올라간 경우
	                if(uploadCount == -1){
	                	initPage(); //첨부파일창 Reload
	                    alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
	                    viewHiddenProgress(false) ;
	                    return;
	                }
	                 
	                if(frm.chk_type.value.match(reTest)){
	    				if(uploadCnt == 0){
	    	            	alert("<spring:message code='las.page.field.lawconsulting.formNeedAttchFile'/>");
	    	            	viewHiddenProgress(false) ;
	    	            	return;
	    	            }
	    			}
	    			
                	f.method.value = "insertReviewLawConsult" ;
    			 	f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
    				f.target = "_self" ;
    				//나모웹에디터 관련
    				f.main_matr_cont.value = CrossEditor.GetBodyValue('wec1');
    				f.cnsd_opnn.value      = CrossEditor2.GetBodyValue('wec2');
    				f.cnsd_opnn_body.value = CrossEditor2.GetBodyValue('wec2');
    				
    				var returnValue = CrossEditor2.GetTextValue();
    				
    				if("" == returnValue){
    					
    					alert("<spring:message code='las.page.field.lawconsulting.mustDoLgCmt'/>");
    					viewHiddenProgress(false) ;
    					return;
    					
    				}
    				
    				f.submit();   
	             });
			}
		}
	}
	
	function goList() {
		viewHiddenProgress(true) ;
		var f = document.frm ;
		f.method.value = "listLawConsult" ;
		f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
		f.target = "_self" ;
		f.submit() ;
	}
	
	// 2014-02-25 Kevin. Review도 CC할 수 있도록 버튼 추가했고, 버튼 클릭 이벤트의 함수 추가 함.
	function openChooseClient(){
        var frm = document.frm;
        
        var items_str1 = $("input[name=arr_demnd_seqno]").map(function(){return this.value;}).get();
        var items_str2 = $("input[name=arr_trgtman_id]").map(function(){return this.value;}).get();
        var items_str3 = $("input[name=arr_trgtman_nm]").map(function(){return this.value;}).get();
        var items_str4 = $("input[name=arr_trgtman_jikgup_nm]").map(function(){return this.value;}).get();
        var items_str5 = $("input[name=arr_trgtman_dept_nm]").map(function(){return this.value;}).get();
        
        var items = items_str1+"!@#$"+ items_str2  +"!@#$"+  items_str3  +"!@#$"+  items_str4 +"!@#$"+items_str5;
        frm.chose_client.value = items;
                
        PopUpWindowOpen('', "530", "480", false, "PopUpWindow");
        frm.action = "<c:url value='/clm/manage/chooseClient.do' />";
        frm.method.value="forwardChooseClientPopup";
        frm.target = "PopUpWindow";
        frm.submit();
     }
	
	
	/**
	 * 2014-02-25 Kevin
	 * CC 추가 버튼 클릭 후, 검색 창에서 OK 버튼 눌러 선택된 사용자 정보 받기. 
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
	
</script>
</head>
<!--  2012.04.26 Alert 수정 modified by hanjihoon  -->
<body onload="initPage();" autocomplete="off">		
<!-- container -->
<div id="container">
	<!-- Location -->
        <div class="location">
            <img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0"/><c:out value='${menuNavi}' escapeXml="false"/>
        </div>
    <!-- //Location -->
    
	<!-- title --> 
 	<div class="title">
      <%-- /* <h3><spring:message code="las.page.field.lawconsult.main_title" /></h3> */ --%>
      <h3><spring:message code="las.page.field.lawconsulting.lawAdvReviewOpnnIns" /></h3>
	</div>
	<!-- //title -->	
 
	<!-- content -->
	<div id="content">
	<div id="content_in">
	
	<form:form name="frm" id="frm" autocomplete="off">
	<input type="hidden" name="method" 				value=""/>
	<input type="hidden" name="menu_id" 			value="<c:out value='${command.menu_id}'/>"/>
	<input type="hidden" name="curPage" 			value="<c:out value='${command.curPage}'/>"/>
	<input type="hidden" name="isForeign" 			value="<c:out value='${command.isForeign}'/>"/>
	<input type="hidden" name="cnslt_no" 			value="<c:out value='${command.cnslt_no}'/>"/>
	<input type="hidden" name="hstry_no" 			value="<c:out value='${command.hstry_no}'/>"/>
	<input type="hidden" name="consult_type" 		value="<c:out value='${lom.consult_type}'/>"/>
	<input type="hidden" name="check_prgrs_status" 	value=""	/>
	<input type="hidden" name="prgrs_status" 		value="<c:out value='${lom.prgrs_status}'/>"/>
	<input type="hidden" name="isGrpmgr" 			value="<c:out value='${command.isGrpmgr}'/>"/>
	<input type="hidden" name="isReview" 			value="<c:out value='${command.isReview}'/>"/>
	<input type="hidden" name="isModify" 			value="<c:out value='${isModify}'/>"/>
	<input type="hidden" id="chk_type" name="chk_type" value="<c:out value='${lom.consult_type}'/>"/>
	<input type="hidden" name="req_reply_dt" value="<c:out value='${lom.req_reply_dt}'/>"/>
	
	<input type="hidden" name="title" 				value="<c:out value='${lom.title}'/>"/>
	<input type="hidden" name="reg_dt" 				value="<c:out value='${lom.reg_dt}'/>"/>
	<input type="hidden" name="respman_nm" 			value="<c:out value='${lom.respman_nm}'/>"/>
	<input type="hidden" name="comp_cd" 			value="<c:out value='${lom.comp_cd}'/>"/>
	<input type="hidden" name="grpmgr_re_yn" 		value="N"/> <!-- 모든 배정이 전결처리로 고정됨 2013.11.05 -->
	<input type="hidden" id="solo_yn"	/>
	
	<!-- 2014-02-25 Kevin. CC된 사람 추가.   -->
	<input type="hidden" name="chose_client" id="chose_client" />
	
	<c:choose>
	<c:when test="${isModify == '1'}">
	<input type="hidden" name="main_matr_cont" 	id="main_matr_cont" value="<c:out value='${lom.main_matr_cont}'/>" />
	<input type="hidden" name="cnsd_opnn" 		id="cnsd_opnn" 		value="<c:out value='${lom.cnsd_opnn}'/>" />
	<input type="hidden" name="cnsd_opnn_body" 	id="cnsd_opnn_body" value="" />
	</c:when>
	<c:otherwise>
	<input type="hidden" name="main_matr_cont" 	id="main_matr_cont" value="" />
	<input type="hidden" name="cnsd_opnn" 		id="cnsd_opnn" 		value="" />
	<input type="hidden" name="cnsd_opnn_body" 	id="cnsd_opnn_body" value="" />
	</c:otherwise>
	</c:choose>
	<!-- <input type="hidden" name="grpmgr_re_yn" value="<c:out value='${lom.grpmgr_re_yn}'/>"/> -->
	
	<!-- 검색 정보 -->
	<input type="hidden" name="srch_start_ymd" 		value="<c:out value='${command.srch_start_ymd}'/>"/>
	<input type="hidden" name="srch_end_ymd" 		value="<c:out value='${command.srch_end_ymd}'/>"/>
	<input type="hidden" name="srch_prgrs_status" 	value="<c:out value='${command.srch_prgrs_status}'/>"/>
	<input type="hidden" name="srch_title" 			value="<c:out value='${command.srch_title}'/>"/>
	<input type="hidden" name="srch_cont" 			value="<c:out value='${command.srch_cont}'/>"/>
	<input type="hidden" name="srch_reg_nm" 		value="<c:out value='${command.srch_reg_nm}'/>"/>
	<input type="hidden" name="srch_reg_dept" 		value="<c:out value='${command.srch_reg_dept}'/>"/>
	<input type="hidden" name="srch_consult_type" 	value="<c:out value='${command.srch_consult_type}'/>"/>
	<input type="hidden" name="srch_respman_nm" 	value="<c:out value='${command.srch_respman_nm}'/>"/>
	<input type="hidden" name="srch_reg_id" 		value="<c:out value='${command.srch_reg_id}'/>"/>
	<input type="hidden" name="srch_reception" 		value="<c:out value='${command.srch_reception}'/>"/>
	<input type="hidden" name="srch_elcomp" 		value="<c:out value='${command.srch_elcomp}'/>"/>
	
	<!-- 첨부파일정보 -->
	<input type="hidden" name="fileInfos" 		value="" />
	<input type="hidden" name="fileInfos3" 		value="" />
	<input type="hidden" name="file_bigclsfcn" 	value="F003" />
	<input type="hidden" name="file_midclsfcn" 	value="" />
	<input type="hidden" name="file_midclsfcn2" value="" />
	<input type="hidden" name="file_smlclsfcn" 	value="" />
	<input type="hidden" name="ref_key" 		value="<c:out value='${command.cnslt_no}'/>@<c:out value='${command.hstry_no}'/>"/>
	<input type="hidden" name="fileInfoName"    value="" /> <!-- 첨부파일 정보가 리턴될 객체명 	-->
	<input type="hidden" name="fileFrameName"   value="" /> <!-- 첨부파일 화면 iFrame 명		-->
	<input type="hidden" name="multiYn"         value="" /> <!-- 멀티여부 					-->
	<input type="hidden" name="size_gbn"        value="" /> <!-- CPMS IF 파일사이즈구분 		-->
	
	<c:choose>
		<c:when test="${isModify == '1'}">
			<input type="hidden" name="view_gbn" 	value="modify" />
		</c:when>
		<c:otherwise>
			<input type="hidden" name="view_gbn" 	value="upload" />
		</c:otherwise>
	</c:choose>
	
	<!-- 이중등록 방지 -->
	<input type="hidden" name="TOKEN"  value="<%=Token.set(request) %>" />
	
	<div class="t_titBtn">
		<div class="btn_wrap">
			<span class="btn_all_w"><span class="mail"></span><a href="javascript:send();"><spring:message code="las.page.button.lawconsult.send" /></a></span>
		
			<c:if test="${command.isGrpmgr == 'Y'}">
			<span class="btn_all_w"><span class="save"></span><a href="javascript:tempSave();"><spring:message code="las.page.button.lawconsult.tempsave" /></a></span>
			</c:if>
			
			<c:if test="${command.isGrpmgr == 'N' && lom.prgrs_status != 'S08'}">
			<span class="btn_all_w"><span class="save"></span><a href="javascript:tempSave();"><spring:message code="las.page.button.lawconsult.tempsave" /></a></span>
			</c:if>
			
			<span class="btn_all_w"><span class="list"></span><a href="javascript:goList()"><spring:message code="las.page.button.lawconsult.list" /></a></span>
		</div>
	</div>
 
		<!-- view -->
		<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
			<colgroup>
				<col width="15%" />
				<col width="35%" />
				<col width="15%" />
				<col width="35%" />
			</colgroup>
				<tr>
					<th><spring:message code="las.page.field.lawconsult.title" /></th>
					<td colspan="3">
						<c:out value='${lom.title}' escapeXml='false'/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawconsult.reqman_nm" /></th>
					<td><c:out value='${lom.reg_nm}'/></td>
					<th><spring:message code="las.page.field.lawconsult.reg_dt" /></th>
					<td><c:out value='${lom.reg_dt}'/></td>					
				</tr>
				
				<!-- 참조인 -->
				<tr class="lineAdd">
					<th><spring:message code="clm.page.msg.manage.relPerson" /> <img src="<%=IMAGE %>/common/step_q.gif" alt="info" title="<spring:message code="clm.page.msg.manage.announce064" htmlEscape="true" />" /></th>
					<td colspan="3">
					<span class="btn_all_b" onclick="javascript:openChooseClient();"><span class="add"></span><a> <spring:message code="clm.page.msg.manage.add" htmlEscape="true" /></a></span><!-- 추가 -->									
							<span id="id_trgtman_nm">
<% 
						if(listCa !=null){
							for(int j=0;j<listCa.size();j++){	
								ListOrderedMap lom = (ListOrderedMap)listCa.get(j);
%>
								<input type="hidden" name="arr_demnd_seqno" id="arr_demnd_seqno" value="<%=lom.get("seqno") %>" /><input type="hidden" name="arr_trgtman_id" id="arr_trgtman_id" value="<%=lom.get("respman_id") %>" /><input type="hidden" name="arr_trgtman_nm" id="arr_trgtman_nm" value="<%=lom.get("respman_nm") %>" /><input type="hidden" name="arr_trgtman_jikgup_nm" id="arr_trgtman_jikgup_nm" value="<%=lom.get("respman_jikgup") %>" /><input type="hidden" name="arr_trgtman_dept_nm" id="arr_trgtman_dept_nm" value="<%=lom.get("resp_dept") %>" />
								- <%=lom.get("respman_nm") %>/<%=lom.get("respman_jikgup") %>/<%=lom.get("resp_dept")%><BR/>					
<% 
							}
						}
%>
							</span>
					</td>
				</tr>
				
				<tr>
					<th><spring:message code="las.page.field.lawconsult.consulttype" /></th>
					<td colspan="3"><c:out value='${lom.consult_type_name}'/></td>
				</tr>
						    
			    <!-- 법무팀의견 -->
				<tr>
					<th><spring:message code="las.page.field.lawconsult.lasopnn" /> <span class='astro'>*</span></th>
					<td colspan=3>
						<script type="text/javascript" language="javascript">
							
						    var CrossEditor2 = new NamoSE('wec2');
							CrossEditor2.params.Width = "100%";
							CrossEditor2.params.Height = "300";
							CrossEditor2.params.UserLang = "enu";
							CrossEditor2.params.FullScreen = false;
							CrossEditor2.ShowTab(false);
							CrossEditor2.params.CreateToolbar = "newdoc|saveas|print|spacebar|undo|redo|cut|copy|paste|pastetext|search|replace|selectall|spacebar|word_style|space|word_color|space|cancelattribute|spacebar|word_justify|space|word_indentset|spacebar|word_listset|spacebar|tableinsert|spacebar|tablerowinsert|tablerowdelete|spacebar|tablecolumninsert|tablecolumndelete|spacebar|tablecellmerge|tablecellsplit|spacebar|tablecellattribute|formatblock|space|fontname|space|fontsize|space|lineheight|spacebar|fullscreen";   					
							CrossEditor2.EditorStart();
							
						</script> 
					</td>
				</tr>
			    
			    <!-- 사안개요 main_matr_cont -->
				<tr>
					<th><spring:message code="las.page.field.lawconsult.main_matr_cont" /></th>
					<td colspan=3>
						<script type="text/javascript" language="javascript">
							var CrossEditor = new NamoSE('wec1');
							CrossEditor.params.Width = "100%";
							CrossEditor.params.Height = "300";
							CrossEditor.params.UserLang = "enu";
							CrossEditor.params.FullScreen = false;
							CrossEditor.ShowTab(false);
							CrossEditor.params.CreateToolbar = "newdoc|saveas|print|spacebar|undo|redo|cut|copy|paste|pastetext|search|replace|selectall|spacebar|word_style|space|word_color|space|cancelattribute|spacebar|word_justify|space|word_indentset|spacebar|word_listset|spacebar|tableinsert|spacebar|tablerowinsert|tablerowdelete|spacebar|tablecolumninsert|tablecolumndelete|spacebar|tablecellmerge|tablecellsplit|spacebar|tablecellattribute|formatblock|space|fontname|space|fontsize|space|lineheight|spacebar|fullscreen";
							CrossEditor.EditorStart();
							
							function OnInitCompleted(e){
								CrossEditor.SetValue(document.frm.main_matr_cont.value);
								CrossEditor2.SetValue(document.frm.cnsd_opnn.value);
							}
							
						</script> 
					</td>
				</tr>
							
				<tr class="end"> 
					<th><spring:message code="las.page.field.lawconsult.attachfile" /><img src="/images/las/ko/common/step_q.gif" alt="info" title="DOC, DOCX, GUL, PDF, OCX, XLS, XLSX, PPT, PPTX,&#13;JPG, HWP, TIF, RTF, ZIP, PTX, TML, TXT, LSX, EML, BMP, GIF, MSG, ALZ, DOT &#13;&#13;<spring:message code='las.msg.com.fileFilter'/>" /></th>
					<td colspan="3">
						<div class='ico_maxsize fR'>Max Size : <span>50MB</span></div>
						<!-- Sungwoo Replaced scroll option 2014/08/04 -->
						<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>
					</td>
				</tr>
		</table>
		
		<br/>
	<div align="center">
	<span class="btn_all_w"><span class="mail"></span><a href="javascript:send();"><spring:message code="las.page.button.lawconsult.send" /></a></span>
	
	<c:if test="${command.isGrpmgr == 'Y'}">
	<span class="btn_all_w"><span class="save"></span><a href="javascript:tempSave();"><spring:message code="las.page.button.lawconsult.tempsave" /></a></span>
	</c:if>
		
	<c:if test="${command.isGrpmgr == 'N' && lom.prgrs_status != 'S08'}">
	<span class="btn_all_w"><span class="save"></span><a href="javascript:tempSave();"><spring:message code="las.page.button.lawconsult.tempsave" /></a></span>
	</c:if>
	
	<span class="btn_all_w"><span class="list"></span><a href="javascript:goList()"><spring:message code="las.page.button.lawconsult.list" /></a></span>
	</div>
	
	</form:form>
	
	</div>
	</div>
	<!-- //content -->
	
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</div>
<!-- //container -->

<jsp:include page="/WEB-INF/jsp/secfw/common/DlgAlert.jsp"/>

<!-- footer  -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
<!-- // footer -->
</body>

</html>
