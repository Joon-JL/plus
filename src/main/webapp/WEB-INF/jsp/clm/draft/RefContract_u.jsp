<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.draft.dto.LibraryForm" %>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>
<%@page import="com.sds.secframework.common.util.Token"%>

<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.sec.clm.draft.dto.LibraryForm" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%-- 
/**
 * 파  일  명 : RefContract_u.jsp
 * 프로그램명 : Reference 계약서 수정
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	LibraryForm command = (LibraryForm)request.getAttribute("command");
	ListOrderedMap lom = (ListOrderedMap)request.getAttribute("lom");  	
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%@ include file="/WEB-INF/jsp/common/messageUtil.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="clm.main.title" /></title>
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->

<!-- style -->
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet" />

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>

<script language="javascript">
<!--

	$(document).ready(function(){
		//비즈니스 단계 
		getCodeSelectByUpCd("frm", "biz_clsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=T01&combo_abbr=F&combo_type=S&combo_del_yn=N&combo_selected=<%=lom.get("biz_clsfcn")%>");   
		//계약단계
		getCodeSelectByUpCd("frm", "depth_clsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=T02&combo_abbr=F&combo_type=S&combo_del_yn=N&combo_selected=<%=lom.get("depth_clsfcn")%>");   
		//계약유형1
		getCodeSelectByUpCd("frm", "cnclsnpurps_bigclsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=T03&combo_abbr=F&combo_type=S&combo_del_yn=N&combo_selected=<%=lom.get("cnclsnpurps_bigclsfcn")%>");   
		//계약유형2
		getCodeSelectByUpCd("frm", "cnclsnpurps_midclsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=<%=lom.get("cnclsnpurps_bigclsfcn")%>&combo_abbr=F&combo_type=S&combo_del_yn=N&combo_selected=<%=lom.get("cnclsnpurps_midclsfcn")%>");
		//언어
		getCodeSelectByUpCd("frm", "lang_gbn", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=&combo_grp_cd=C045&combo_type=S&combo_del_yn=N&combo_selected=<%=lom.get("lang_gbn")%>');
		
		initPage();
	});
	
	$(function() {
		$("#cnclsnpurps_bigclsfcn").change(function() {
			var grpCd = $("#cnclsnpurps_bigclsfcn").val();
			getCodeSelectByUpCd("frm", "cnclsnpurps_midclsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd="+grpCd+"&combo_abbr=F&combo_type=S&combo_del_yn=N&combo_selected=");
		});
	});	
	
	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/draft/refContract.do' />";
		
		if(flag == "insert"){
		    frm.method.value = "modifyRefContract";
		    
		  	//나모웹에디터 관련
// 		    frm.cont.value = frm.wec.MIMEValue;
		    
		    //Validation Check1 
		    if(frm.biz_clsfcn.value == "" && frm.depth_clsfcn.value == "" && frm.cnclsnpurps_bigclsfcn.value == ""){
		    	alert("<spring:message code='clm.page.field.refcontract.bizClsfcn' />, <spring:message code='clm.page.field.refcontract.depthClsfcn' />, <spring:message code='clm.page.field.refcontract.cnclsnpurpsClsfcn' /> : <spring:message code='secfw.msg.ask.cpAlert30' />");
		    	return;
		    }
		  	
		  	//Validation Check2
		    if(validateForm(frm)){
				if(confirm("<spring:message code='secfw.msg.ask.update' />")){
					
					viewHiddenProgress(true) ;
					
			    	//첨부파일 업로드 실행
					fileList.UploadFile(function(uploadCount){
						if(uploadCount == -1){
							initPage();
							alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
							return;
						}

                        //첨부파일 필수일 경우
                        if (uploadCount == 0) {
                            alert("<spring:message code='secfw.msg.error.fileNon' />");
                            return;
                        }    
				    	frm.submit();	
					});
				}
		    }

		} else if(flag == "cancel"){	
			if(confirm("<spring:message code='secfw.msg.ask.cancel' />")){
				viewHiddenProgress(true) ;
				
			    frm.method.value = "detailRefContract";
			    frm.lib_no.value = <%=lom.get("lib_no")%>;
				frm.submit();
			}
				
		}
	}
	
	function initPage(){
		var frm = document.frm;
	    frm.target			= "fileList";
	    frm.action 			= "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value  	= "forwardClmsFilePage";
		frm.submit();	
	}
//-->

</script>

</head>
<body>
<div id="wrap">
	<!-- container -->
	<div id="container">	

		<!-- Location -->
		<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->

		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.title.refcontract.listTitle" /></h3>
		</div>
		<!-- //title -->

		<!-- content -->
		<div id="content_in">
			<!-- **************************** Form Setting **************************** -->
			<form:form name="frm" id="frm" method="post" autocomplete="off">
			
			<!-- search form -->
			<input type="hidden" name="method"   value="" />
			<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />
			<input type="hidden" name="srch_biz_clsfcn" value="<c:out value='${command.srch_biz_clsfcn}'/>" />
			<input type="hidden" name="srch_depth_clsfcn"   value="<c:out value='${command.srch_depth_clsfcn}'/>" />
			<input type="hidden" name="srch_cnclsnpurps_bigclsfcn"      value="<c:out value='${command.srch_cnclsnpurps_bigclsfcn}'/>" />
			<input type="hidden" name="srch_cnclsnpurps_midclsfcn"       value="<c:out value='${command.srch_cnclsnpurps_midclsfcn}'/>" />
			<input type="hidden" name="srch_lang_gbn"    value="<c:out value='${command.srch_lang_gbn}'/>" />
			<input type="hidden" name="srch_keyword"    value="<c:out value='${command.srch_keyword}'/>" />
			<input type="hidden" name="srch_keyword_content"    value="<c:out value='${command.srch_keyword_content}'/>" />
			
			<!-- key form-->
			<input type="hidden" name="lib_no"	value="<%=lom.get("lib_no")%>" />
<%-- 			<input type="hidden" name="cont"	value="<c:out value='${lom.cont}' />" /> --%>
			
			<!-- 첨부파일정보 -->
			<input type="hidden" name="fileInfos" 	value="" />
			<input type="hidden" name="file_bigclsfcn" 	value="F013" />
			<input type="hidden" name="file_midclsfcn" 	value="" />
			<input type="hidden" name="file_smlclsfcn" 	value="" />
			<input type="hidden" name="ref_key" 	value="<%=lom.get("lib_no")%>" />
			<input type="hidden" name="view_gbn" 	value="modify" />

			<!-- // **************************** Form Setting **************************** -->
			
			<div class="btnwrap">
		<%if(command.isAuth_modify()){ %>
				<span class="btn_all_w"><span class="save"></span><a href="javascript:pageAction('insert');"><spring:message code="secfw.page.button.insert" /></a></span>
		<%} %>
				<span class="btn_all_w"><span class="cancel"></span><a href="javascript:pageAction('cancel');"><spring:message code="secfw.page.button.cancel" /></a></span>
			</div>
			
			<!-- view -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
					<col width="100" />
					<col width="310" />
					<col width="100" />
					<col width="310" />
				</colgroup>
				<tbody>
					<tr>
						<th><spring:message code="clm.page.field.refcontract.bizClsfcn" /></th>
						<td>
							<select name="biz_clsfcn" id="biz_clsfcn" style="width:180px">
							</select>
						</td>
						<th><spring:message code="clm.page.field.refcontract.depthClsfcn" /></th>
						<td>
							<select name="depth_clsfcn" id="depth_clsfcn" style="width:180px">
							</select>
						</td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.refcontract.cnclsnpurpsClsfcn" /></th>
						<td colspan="3">
							<select name="cnclsnpurps_bigclsfcn" id="cnclsnpurps_bigclsfcn" style="width:180px">
							</select>
							<select name="cnclsnpurps_midclsfcn" id="cnclsnpurps_midclsfcn" style="width:180px">
							</select>
						</td>
					</tr>
					<tr>
						<th><spring:message code="las.page.field.board.notice.title" /><span class="astro">*</span></th>
						<td colspan="3">
							<input type="text" name="title" id="title" required alt="<spring:message code="las.page.field.board.notice.title" />" maxLength="300" class="text_full" style="width:600px" value="<%=lom.get("title")%>" />
						</td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.refcontract.langGbn" /><span class="astro">*</span></th>
						<td colspan="3">
							<select name="lang_gbn" id="lang_gbn" required alt="<spring:message code="clm.page.field.refcontract.langGbn" />">
							</select>
						</td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.refcontract.cont" /><span class="astro">*</span></th>
						<td  colspan="3">
							<span id="cont">0</span>/<spring:message code="clm.page.msg.manage.stringLen2000" /><br>
						    <textarea id="cont" name="cont" cols="100%" rows="10" maxLength="2000" onkeyup="frmChkLenMe(this,2000,'cont')" class="text_area_full"><c:out value="${lom.cont}" escapeXml=""/></textarea>
							<%//@ include file="/WEB-INF/jsp/secfw/common/NamoEditInclude.jspf"%>
						</td>
					</tr>
					<tr class="end">
		            	<th><spring:message code="secfw.page.field.bbs.file" /><span class="astro">*</span> <img src="/images/las/ko/common/step_q.gif" alt="info" title="DOC, DOCX, GUL, PDF, OCX, XLS, XLSX, PPT, PPTX,&#13;JPG, HWP, TIF, RTF, ZIP, PTX, TML, TXT, LSX, EML, BMP, GIF, MSG, ALZ, DOT &#13;&#13;<spring:message code='las.msg.com.fileFilter'/>" /></th>
		            	<td colspan="3">
		            		<div class='ico_maxsize fR'>Max Size : <span>10MB</span></div>
		            		<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>
		            	</td>
		          	</tr>
				</tbody>
			</table>
			<!-- //view -->
	
			<div class="btnwrap mt20">
		<%if(command.isAuth_modify()){ %>
				<span class="btn_all_w"><span class="save"></span><a href="javascript:pageAction('insert');"><spring:message code="secfw.page.button.insert" /></a></span>
		<%} %>
				<span class="btn_all_w"><span class="cancel"></span><a href="javascript:pageAction('cancel');"><spring:message code="secfw.page.button.cancel" /></a></span>
			</div>
		</form:form>
		</div>
		<!-- //content -->
		
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
	<!-- footer  -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
	<!-- // footer -->
</body>
<SCRIPT language="javascript" FOR="wec" EVENT="OnInitCompleted()">
	var wecObj = document.wec;
	wecObj.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
	wecObj.SetDefaultFontSize("9");
	wecObj.EditMode = 1;
	wecObj.Value = document.frm.cont.value;
</SCRIPT>
</html>