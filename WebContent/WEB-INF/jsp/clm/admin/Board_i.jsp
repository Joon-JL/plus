<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.admin.dto.BoardForm" %>

<%@page import="com.sds.secframework.common.util.Token"%>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%--
/**
 * 파  일  명 : Board_i.jsp
 * 프로그램명 : 공지사항 등록
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
 --%>
<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	BoardForm command = (BoardForm)request.getAttribute("command");
	String wec_set_id = ""; 
	String wec_mode = ""; 
	String wec_com_script_yn = ""; 
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<link href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet" />

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>

<script language="javascript">
var index = 0;

	$(document).ready(function(){
		getCodeSelectByUpCd("frm", "rd_trgt1", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=&combo_grp_cd=C016&combo_type=S&combo_del_yn=N&combo_selected=');
		getCodeSelectByUpCd("frm", "rd_trgt2", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=&combo_grp_cd=C057&combo_type=S&combo_del_yn=N&combo_selected=');
		getCodeSelectByUpCd("frm", "sel_lang", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=F&combo_grp_cd=MULTI_LANG&combo_type=NULL&combo_del_yn=N&combo_selected=');
		initCal("annce_startday");	
		initCal("annce_endday");	
		initPage();
		
	});
	
	$(function() {
		//공지대상1 컨트롤
		$("#rd_trgt1").change(function() {
			var val1 = $("#rd_trgt1").val();
			
			if(val1 == "C01602"){
				$("#searchBtn").attr('style', 'padding-left:5px;padding-right:5px;display:');
				$("#returnOrgnzCds").attr('style', 'display:');
			}else{
				$('.orgnz_span').remove("");
				$("#searchBtn").attr('style', 'padding-left:5px;padding-right:5px;display:none');
				$("#returnOrgnzCds").attr('style', 'display:none');
			}

		});
		
		//공지기간 컨트롤
		$("#annce_gbn").change(function() {
			var annceVal = $("#annce_gbn").val();
			
			if(annceVal == "T"){
				$('#annce_dt').attr('style', 'display:none');
				$('#annce_startday').val('');
				$('#annce_endday').val('');
			}else{
				$('#annce_dt').attr('style', 'display:');
			}
		});
	
	});
	
	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/admin/board.do' />";
		//저장
		if(flag == "insert"){
		    frm.method.value = "insertBoard";
		    
		    //나모웹에디터 관련
		    frm.cont.value = frm.wec1.MIMEValue;
		    frm.cont_en.value = frm.wec2.MIMEValue;
			//공지대상1 체크
		    if(frm.rd_trgt1.value == "C01602"){
		    	if(frm.orgnz_cds == null){
		    		alert("<spring:message code='clm.page.alert.orgnz.noSelect' />");
		    		return;
		    	}		    	
		    }
		  	//Validation Check1
		  	if((frm.annce_startday.value == "" && frm.annce_endday.value != "") || (frm.annce_startday.value != "" && frm.annce_endday.value == "")){
		  		alert("<spring:message code='clm.msg.alert.board.noAnnceDt' />");
		  		return;
		  	}else if((frm.annce_startday.value != "" && frm.annce_endday.value != "") && (frm.annce_startday.value > frm.annce_endday.value)){
		  		alert("<spring:message code='clm.msg.alert.board.annceDt' />");
		  		return;
		  	}
		    //Validation Check2
		    if(validateForm(frm)){
				if(confirm("<spring:message code='secfw.msg.ask.insert' />")){
					viewHiddenProgress(true);
					//첨부파일 업로드 실행
					fileList.UploadFile(function(uploadCount){
						if(uploadCount == -1){
							initPage();
							alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
							return;
						}
						
				    	frm.submit();	
					});
				}
		    }
			
		//초기화
		} else if(flag == "cancel"){	
			if(confirm("<spring:message code='secfw.msg.ask.cancel' />")){
				viewHiddenProgress(true) ;
			    frm.method.value = "listBoard";
				frm.submit();			
			}
		}
	}
	
	function initPage(){
	    frm.target			= "fileList";
 	    frm.action = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value  	= "forwardClmsFilePage";

		frm.submit();	
	}

	//단위조직 팝업 호출
	function customerClick(){
		var frm = document.frm;
		
		//viewHiddenProgress(true);

		PopUpWindowOpen('', 640, 300, true);
		frm.target = "PopUpWindow";
		frm.action = "<c:url value='/common/clmsCom.do' />";
		frm.method.value = "forwardOrgnzPop";
		//frm.srch_up_orgnz_cd.value = "B00000004";
		frm.submit();
	}
	
	//공지대상 삭제
	function orgnzCdDel(id){
		$("#"+id).remove();
	}
	
	//단위조직 팝업 리턴 값.
	function setReturnOrgnz(orgnzList){
		var frm = document.frm;
		
		viewHiddenProgress(false);

		if(orgnzList.length > 0){
			//전체 조직단위 삭제
			$('.orgnz_span').remove("");
			//선택된 데이터 출력
			for(var idx=0; idx<orgnzList.length; idx++) {
				var obj = orgnzList[idx];
				
				var returnVal = "";
				returnVal += "<span class=\"orgnz_span\" style=\"padding-right:5px;\" id=\"orgnz_"+index+"\">"+obj.orgnz_nm;
		    	returnVal += "<a href=\"javascript:orgnzCdDel('orgnz_"+index+"');\" ><img SRC=\"<%=IMAGE%>/icon/cancel_new.gif\"/></a>";
		    	returnVal += "<input type=\"hidden\" id=\"orgnz_cds"+index+"\" name=\"orgnz_cds\" value=\"" + obj.orgnz_cd + "\" />";
		    	returnVal += "</span>";
			    
			    $('#returnOrgnzCds').append(returnVal);
			    index = index + 1;
			}
		}
	}
	

</script>

</head>
<body>

<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id="frm" method="post" autocomplete="off">

<!-- search form -->
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />
<input type="hidden" name="mode"  value="<c:out value='${command.mode}'/>" />
<input type="hidden" name="srch_start_dt" value="<c:out value='${command.srch_start_dt}'/>" />
<input type="hidden" name="srch_end_dt"   value="<c:out value='${command.srch_end_dt}'/>" />
<input type="hidden" name="srch_reg_operdiv"      value="<c:out value='${command.srch_reg_operdiv}'/>" />
<input type="hidden" name="srch_keyword"    value="<c:out value='${command.srch_keyword}'/>" />
<input type="hidden" name="srch_keyword_content"    value="<c:out value='${command.srch_keyword_content}'/>" />

<!-- key form-->
<input type="hidden" name="seqno"	value="<c:out value='${command.seqno}'/>" />
<input type="hidden" name="cont"	value="" />
<input type="hidden" name="cont_en"	value="" />

<!-- 첨부파일정보 -->
<input type="hidden" name="fileInfos" 	value="" />
<input type="hidden" name="file_bigclsfcn" 	value="F008" />
<input type="hidden" name="file_midclsfcn" 	value="" />
<input type="hidden" name="file_smlclsfcn" 	value="" />
<input type="hidden" name="ref_key" 	value="<c:out value='${command.seqno}'/>" />
<input type="hidden" name="view_gbn" 	value="upload" />
<input type="hidden" name="sec_yn" value="Y" />

<!-- 단위조직 팝업 form-->
<input type="hidden" name="srch_up_orgnz_cd"	value="" /><!-- 상위조직코드 -->
<!-- // **************************** Form Setting **************************** -->

<div id="wrap">
	<!-- container -->
	<div id="container">	

		<!-- Location -->
		<div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->
	
		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.title.board.insertTitle" /></h3>
		</div>
		<!-- //title -->
		
		<!-- content -->
		<div id="content">
			<!-- view -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
					<col width="100" />
					<col width="310"/>
					<col width="100" />
					<col width="310"/>
				</colgroup>
				<tbody>
					<tr>
						<th><spring:message code="clm.page.field.board.titleKr" /><span class="astro">*</span></th>
						<td colspan="3">
							<input type="text" name="title" id="title" required alt="<spring:message code="clm.page.field.board.titleKr" />" maxLength="128" class="text_full" style="width:90%" />
						</td>
					</tr>
					<!-- 영문공지사항 추가 -->
					<tr>
						<th><spring:message code="clm.page.field.board.titleEn" /><span class="astro">*</span></th>
						<td colspan="3">
							<input type="text" name="title_en" id="title_en" required alt="<spring:message code="clm.page.field.board.titleEn" />" maxLength="128" class="text_full" style="width:90%" />
						</td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.board.rd_trgt1" /><span class="astro">*</span></th>
						<td colspan="3">
							<select name="rd_trgt1" id="rd_trgt1" required alt="<spring:message code="clm.page.field.board.rd_trgt1" />">
							</select>
							<span id="searchBtn" style="padding-left:5px;padding-right:5px;display:none"><a href="javascript:customerClick();" ><img src="<%=IMAGE%>/btn/btn_wrap_fr_search.gif" alt="<spring:message code="clm.page.msg.common.search" htmlEscape="true"/>" /></a></span>
							<span id="returnOrgnzCds" style="display:none">
							</span>		
						</td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.board.rd_trgt2" /><span class="astro">*</span></th>
						<td colspan="3">
							<select name="rd_trgt2" id="rd_trgt2" required alt="<spring:message code="clm.page.field.board.rd_trgt2" />">
							</select>
						</td>
						<!-- 
						<th><spring:message code="clm.page.field.board.secYn" /></th>
						<td>
							<input type="radio" name="sec_yn" value="Y"><spring:message code="clm.page.field.board.secYn1" /></input>
							<input type="radio" name="sec_yn" value="N" checked><spring:message code="clm.page.field.board.secYn2" /></input>
						</td>
						 -->						
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.board.annceGbn" /></th>
						<td>
							<select name="annce_gbn" id="annce_gbn">
								<option value="T"><spring:message code="clm.page.field.board.annceGbn1" /></option>
								<option value="D"><spring:message code="clm.page.field.board.annceGbn2" /></option>
							</select>
						</td>						
						<th><spring:message code="clm.page.field.board.annceDt" /></th>
						<td>
							<span id="annce_dt" style="display:none">
							<input type="text" name="annce_startday" id="annce_startday" value=""  class="text_calendar02"/>
							~
							<input type="text" name="annce_endday" id="annce_endday" value=""  class="text_calendar02"/>
							</span>
						</td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.common.content" /><span class="astro">*</span></th>
						<td  colspan="3">
							<%
		  					request.setAttribute("wec_set_id","wec1"); 
		  					request.setAttribute("wec_mode","EDIT");	
		  					request.setAttribute("wec_com_script_yn","Y");
		  					%>
							<%@ include file="/WEB-INF/jsp/secfw/common/NamoAutoInclude.jspf"%>
						</td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.common.content" /><span class="astro">*</span></th>
						<td  colspan="3">
							<%
		  					request.setAttribute("wec_set_id","wec2"); 
		  					request.setAttribute("wec_mode","EDIT");	
		  					request.setAttribute("wec_com_script_yn","Y");
		  					%>
							<%@ include file="/WEB-INF/jsp/secfw/common/NamoAutoInclude.jspf"%>
						</td>
					</tr>
					<tr class="end">
		            	<th><spring:message code="secfw.page.field.bbs.file" /></th>
		            	<td colspan="3">
		            	<iframe src="<c:url value='/clm/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="150px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
		            	</td>
		          	</tr>
				</tbody>
			</table>
			<!-- //view -->
	
			<div class="btn_wrap">
		<%if(command.isAuth_insert()){ %>
				<span class="btn_all_b"><span class="save"></span><a href="javascript:pageAction('insert');"><spring:message code="secfw.page.button.new" /></a></span>
		<%} %>
				<span class="btn_all_w"><span class="cancel"></span><a href="javascript:pageAction('cancel');"><spring:message code="secfw.page.button.cancel" /></a></span>
			</div>
		</div>
		<!-- //content -->
		<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- // footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
</form:form>
</body>
</html>
