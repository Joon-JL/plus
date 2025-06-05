<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@page import="com.sds.secframework.common.util.PageUtil"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%@page import="com.sds.secframework.common.util.Token"%>


<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
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
	    
	    var f = document.frm;
	});

	
	function initPage(){

		//if(frm.prgrs_status.value == 'S03' || frm.prgrs_status.value == 'S11'){
		//	frm.ref_key.value = "";
		//}
		
		if(frm.isModify.value != '1'){
			frm.ref_key.value = "";
		}
		
	    frm.target			= "fileList";
	    frm.file_midclsfcn.value = "F00301";
	    frm.action = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value  	= "forwardClmsFilePage";
		frm.fileInfoName.value = "fileInfos";
        frm.fileFrameName.value = "fileList";
		//var reTest = new RegExp("A20");
	    //if(frm.chk_type.value.match(reTest)){
	    	frm.multiYn.value = "N";
	    	frm.preAllowFileList.value = "DOC,DOCX,";
	    //}
	    
		frm.submit();
		
		/*
		frm.target			= "fileList3";
		frm.file_midclsfcn2.value = "F00302";
	    frm.action = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value  	= "forwardClmsFilePage";
		frm.fileInfoName.value = "fileInfos3";
        frm.fileFrameName.value = "fileList3";
		frm.submit();
		*/
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
		
		//$("#extnl_list > option").attr("selected","selected");
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
		
		//$("#extnl_list > option").attr("selected","selected");	
		insert(confirmMessage);
	}
	
	/*
	function replaceFileInfo(info){
		info =  replace(info, "old|", "add|");
		var temp = info.split("|");
		for(var i=0; i < temp.length-1; i++ ) {
			tempCol = temp[i].split("*");
			var tempId = tempCol[0];
			info = replace(info, tempId, tempId+"_1");
		}
		return info;
	}*/
	
	
	function insert(Message) {
		var f = document.frm ;
		var uploadCnt = -1;
		//var reTest = new RegExp("A20");
		
		/*
		if($("input[name=extnl_cnsltyn]:checked").val() == 'Y'){
			if($("#extnl_list > option").length == 0){
				alert("<spring:message code='las.page.field.lawconsulting.addOsideInstt'/>");
				f.comp_nm.focus();
				return;
			}
		}
		*/
		
		//if("" == stripHTMLtag(frm.wec[1].BodyValue)){//검토요청 내용 CNSD_DEMND_CONT
        //   alert("<spring:message code='las.page.field.lawconsulting.mustDoLgCmt'/>");return;                  
        //}
		
		//f.cnslt_amt.value = "0";
		
		if(validateForm(document.frm)) {		
			var confirmMessage = Message;
			
			/*
			if(f.prgrs_status.value == 'S03' || f.prgrs_status.value == 'S08' || f.prgrs_status.value == 'S11'){
				var oldFileInfos = fileList2.POGC.totalFileInfos.value;
				oldFileInfos =replaceFileInfo(oldFileInfos);
				f.fileInfos2.value = oldFileInfos;
				
				var oldFileInfos2 = fileList3.POGC.totalFileInfos.value;
				oldFileInfos2 =replaceFileInfo(oldFileInfos2);
				f.fileInfos3.value = oldFileInfos2;
			}*/
			
			fileList.UploadFile(function(uploadCount){
				uploadCnt = uploadCount;
                 //첨부파일 업로드 결과 잘못된 형식의 파일이 올라간 경우
				if(uploadCount == -1){
					initPage(); //첨부파일창 Reload
					alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
					return;
				}
                 
				if(uploadCnt == 0){
					alert("<spring:message code='las.page.field.lawconsulting.formNeedAttchFile'/>");
					return;
				}
			//}
			 
				if(confirm(confirmMessage)){
					f.method.value = "insertReviewStaLawConsult" ;
					f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
					f.target = "_self" ;
					 
					 //나모웹에디터 관련
					 
					f.main_matr_cont.value = CrossEditor.GetBodyValue('wec1');
					f.cnsd_opnn.value = CrossEditor2.GetBodyValue('wec2');
					f.cnsd_opnn_body.value = CrossEditor2.GetBodyValue('wec2');
				     
					viewHiddenProgress(true) ;
					f.submit();   
				}
                 
			});
			 
			/*
			 fileList3.UploadFile(function(uploadCount){
                 //첨부파일 업로드 결과 잘못된 형식의 파일이 올라간 경우
                 if(uploadCount == -1){
                     initPage(); //첨부파일창 Reload
                     alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
                     return;
                 }
             });
			*/
			 
			 //if(frm.chk_type.value.match(reTest)){
			
		}
	}
	
	function goList() {
		viewHiddenProgress(true) ;
		var f = document.frm ;
		f.method.value = "listStaLawConsult" ;
		f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
		f.target = "_self" ;
		f.submit() ;
	}
	
	/*
	function addExtnl(){
		var f = document.frm ;
				
		if($("input[name=extnl_cnsltyn]:checked").val() == 'Y'){
			if(f.comp_nm.value == null || f.comp_nm.value == ""){
				alert("<spring:message code='las.page.field.lawconsulting.inputOsideInstt'/>");
				return;
			}
			if(f.cnslt_amt.value == null || f.cnslt_amt.value == ""){
				alert("<spring:message code='las.page.field.lawconsulting.inputAdvfee'/>");
				return;
			}
			if(!isNumber(f.cnslt_amt.value)){
				alert("<spring:message code='las.page.field.lawconsulting.advfeeNum'/>");
				return;
			}
			if( 9223372036854775807 < parseInt(f.cnslt_amt.value)){
				alert("<spring:message code='las.page.field.lawconsulting.overRange'/>");
			}
			if(f.crrncy_unit.value == null || f.crrncy_unit.value == ""){
				alert("<spring:message code='las.page.field.lawconsulting.selectCu'/>");
				return;
			}
		}
				
		var comp_nm = f.comp_nm.value;
		var cnslt_amt = f.cnslt_amt.value;
		var crrncy_unit = $("#crrncy_unit > option:selected").val();
		var str = "<spring:message code='las.page.field.lawconsulting.org'/>:" + comp_nm + ", " + "<spring:message code='las.page.field.lawconsulting.advfee'/>:" + commify(cnslt_amt) + ", " + "<spring:message code='las.page.field.lawconsulting.currcUnt'/>:" + crrncy_unit; 
		var value = comp_nm + "^" + cnslt_amt + "^" + crrncy_unit;
	
		var options = $("#extnl_list").attr("options");
		options[options.length] = new Option(str,value);
	}
	
	function removeExtnl(){
		var f = document.frm ;
		$("#extnl_list option:selected").each(function() {
			$(this).remove();
		});
	}
	*/
	
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
      <h3><spring:message code='las.page.field.lawconsulting.stdConReviewOpnnIns' /></h3>
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
	<input type="hidden" name="consult_type" value="<c:out value='${lom.consult_type}'/>"/>
	<input type="hidden" name="check_prgrs_status" value=""/>
	<input type="hidden" name="prgrs_status" value="<c:out value='${lom.prgrs_status}'/>"/>
	<input type="hidden" name="isGrpmgr" value="<c:out value='${command.isGrpmgr}'/>"/>
	<input type="hidden" name="isReview" value="<c:out value='${command.isReview}'/>"/>
	<input type="hidden" name="isModify" value="<c:out value='${isModify}'/>"/>
	<input type="hidden" id="chk_type" name="chk_type" value="<c:out value='${lom.consult_type}'/>"/>
	<input type="hidden" name="comp_cd" value="<c:out value='${lom.comp_cd}'/>"/>
	
	<input type="hidden" name="title" value="<c:out value='${lom.title}'/>"/>
	<input type="hidden" name="reg_dt" value="<c:out value='${lom.reg_dt}'/>"/>
	<input type="hidden" name="respman_nm" value="<c:out value='${lom.respman_nm}'/>"/>
	
	
	<c:choose>
	<c:when test="${isModify == '1'}">
	<input type="hidden" name="main_matr_cont" id="main_matr_cont" value="<c:out value='${lom.main_matr_cont}'/>" />
	<input type="hidden" name="cnsd_opnn" id="cnsd_opnn" value="<c:out value='${lom.cnsd_opnn}'/>" />
	<input type="hidden" name="cnsd_opnn_body" id="cnsd_opnn_body" value="" />
	</c:when>
	<c:otherwise>
	<input type="hidden" name="main_matr_cont" id="main_matr_cont" value="" />
	<input type="hidden" name="cnsd_opnn" id="cnsd_opnn" value="" />
	<input type="hidden" name="cnsd_opnn_body" id="cnsd_opnn_body" value="" />
	</c:otherwise>
	</c:choose>
	<!-- <input type="hidden" name="grpmgr_re_yn" value="<c:out value='${lom.grpmgr_re_yn}'/>"/> -->
	
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
	<input type="hidden" name="srch_elcomp" value="<c:out value='${command.srch_elcomp}'/>"/>
	
	<!-- 첨부파일정보 -->
	<input type="hidden" name="fileInfos" 	value="" />
	<input type="hidden" name="fileInfos3" 	value="" />
	<input type="hidden" name="file_bigclsfcn" 	value="F003" />
	<input type="hidden" name="file_midclsfcn" 	value="" />
	<input type="hidden" name="file_midclsfcn2" 	value="" />
	<input type="hidden" name="file_smlclsfcn" 	value="" />
	<input type="hidden" name="ref_key" 	value="<c:out value='${command.cnslt_no}'/>@<c:out value='${command.hstry_no}'/>"/>
	<input type="hidden" name="fileInfoName"    value="" /> <!-- 첨부파일 정보가 리턴될 객체명 -->
	<input type="hidden" name="fileFrameName"    value="" /> <!-- 첨부파일 화면 iFrame 명 -->
	<input type="hidden" name="multiYn"         value="" /> 		<!-- 멀티여부 -->
	<input type="hidden" name="size_gbn"        value="" /> 		<!-- CPMS IF 파일사이즈구분 -->
	<input type="hidden" name="preAllowFileList"         value="" />
	
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
				<col width="37%" />
				<col width="10%" />
				<col width="38%" />
			</colgroup>
				<tr>
					<th><spring:message code="las.page.field.lawconsult.reg_dt" /></th>
					<td><c:out value='${lom.reg_dt}'/></td>
					<th><spring:message code="las.page.field.lawconsult.reqman_nm" /></th>
					<td><c:out value='${lom.reg_nm}'/></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawconsult.title" /></th>
					<td>
						<c:out value='${lom.title}' escapeXml='false'/>
					</td>
					
					<th><spring:message code="las.page.field.lawconsult.grpmgr_re_yn" /></th>
					<td>
						<input type="radio" name="grpmgr_re_yn" value="Y" <c:if test="${lom.grpmgr_re_yn=='Y'}"> checked="checked"</c:if> disabled/> <spring:message code="las.page.field.contractManager.general" />
	            		<input type="radio" name="grpmgr_re_yn" value="N" <c:if test="${lom.grpmgr_re_yn=='N'}"> checked="checked"</c:if> disabled/> <spring:message code="las.page.field.contractManager.arbitrary" />
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawconsult.consulttype" /></th>
					<td><c:out value='${lom.consult_type_name}'/></td>
					
					<th><spring:message code="las.page.field.lawconsult.solo_yn" /></th>
	            	<td>
	            		<input type="radio" name="solo_yn" value="1" <c:if test="${lom.solo_yn=='1'}"> checked="checked"</c:if> disabled/> <spring:message code="las.page.field.lawconsult.cavity.dmst"/>
	            		<input type="radio" name="solo_yn" value="2" <c:if test="${lom.solo_yn=='2'}"> checked="checked"</c:if> disabled/> <spring:message code="las.page.field.lawconsult.cavity.frgn"/>
	            		<input type="radio" name="solo_yn" value="3" <c:if test="${lom.solo_yn=='3'}"> checked="checked"</c:if> disabled/> <spring:message code="las.page.field.lawconsult.alone" />
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
										
									</script> 
								</td>
							</tr>
							<!-- 법무팀의견 -->
							<tr>
								<th><spring:message code="las.page.field.lawconsult.lasopnn" /></th>
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
										   					
										function OnInitCompleted(e){
											CrossEditor.SetValue(document.frm.main_matr_cont.value);
											CrossEditor2.SetValue(document.frm.cnsd_opnn.value);
										}
									</script> 
								</td>
							</tr>
					
				
				<tr class="end">
					<th><spring:message code="las.page.field.lawconsult.attachfile" /><img src="/images/las/ko/common/step_q.gif" alt="info" title="DOC, DOCX&#13;&#13;<spring:message code='las.msg.com.fileFilter'/>" /></th>
					<td colspan="3">
						<div class='ico_maxsize fR'>Max Size : <span>10MB</span></div>
						<!-- Sungwoo Replaced scroll option 2014/08/04 -->
						<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>
					</td>
				</tr>
		</table>
		
		<!-- 
		<div id="layer" style='display:none;'>
		
			<div class="title_basic">
				<h4><spring:message code="las.page.field.lawconsult.extnlconsult" /></h4>
			</div>
		
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
					<col width="15%" />
					<col width="85%" />
				</colgroup>
				<tr>
				<th>
					<spring:message code="las.page.field.lawconsult.extnl_add" />
				</th>
				<td>
					<spring:message code="las.page.field.lawconsult.comp_nm" /> : <input type="text" id="comp_nm" name="comp_nm" alt="<spring:message code="las.page.field.lawconsulting.orgNm"/>" maxLength="64"/>
					<spring:message code="las.page.field.lawconsult.cnslt_amt" /> : <input type="text" name="cnslt_amt" alt="<spring:message code="las.page.field.lawconsulting.advfee"/>" alt="<spring:message code="las.page.field.lawconsulting.advfee"/>" maxLength="30" char="n"/>	
					<select name="crrncy_unit" id="crrncy_unit" class="select">
					</select>
					<span class="btn_all_b"><span class="save"></span><a href="javascript:addExtnl();"><spring:message code="las.page.button.lawconsult.add" /></a></span>
				</td>		
				</tr>
				<tr>
				
				<th>
					<spring:message code="las.page.field.lawconsult.extnl_list" />
				</th>
				<td>
					<select id="extnl_list" name="extnl_list" size="5"	multiple="multiple" style='width:600px;height:70px;overflow-y : auto'>
					<c:forEach var="list" items="${extnlcompList}">
						<option value="<c:out value='${list.extnl_comp_value}'/>">
							<spring:message code="las.page.field.lawconsult.comp_nm" />:<c:out value='${list.comp_nm}'  escapeXml='false'/>,
							<spring:message code="las.page.field.lawconsult.cnslt_amt" />:<c:out value='${list.cnslt_amt}'  escapeXml='false'/>,
							<spring:message code="las.page.field.lawconsult.crrncy_unit" />:<c:out value='${list.crrncy_unit}'  escapeXml='false'/>
						</option>
					</c:forEach>
					</select>
					<span class="btn_all_w vb"><span class="delete"></span><a href="javascript:removeExtnl();"><spring:message code="las.page.button.lawconsult.delete" /></a></span>
				</td>
				</tr>
					
				<tr>
				<th>
					<spring:message code="las.page.field.lawconsult.attachfile" />
				</th>
				<td>
					<iframe src="<c:url value='/las/blank.do' />" id="fileList3" name="fileList3" frameborder="0" width="100%" height="70px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
				</td>
				</tr>
			</table>
		</div>
		 -->
		<!-- //view -->
		
		<br>
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

<!-- footer  -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
	<!-- // footer -->

<jsp:include page="/WEB-INF/jsp/secfw/common/DlgAlert.jsp"/>
</body>
</html>
