<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>
<%@page import="com.sds.secframework.common.util.Token"%>

<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%@ include file= "/WEB-INF/jsp/common/common.jsp" %>
<%--
/**
 * 파  일  명 : ContractLibrary_u.jsp
 * 프로그램명 : 국내/해외 계약서 수정
 * 설      명 : 
 * 작  성  자 : 신승완
 * 작  성  일 : 2011.08
 */
 --%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.main.title" /></title>
<LINK href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/util/fckeditor/fckeditor.js' />"></script>

<script language="javascript">
<!--

	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		var frm = document.frm;
	
		if(flag == "modify"){
			
			frm.target = "_self";
			frm.action = "<c:url value='/las/lawinformation/library.do' />";
		    frm.method.value = "modifyLibrary";
		    
		  	//나모웹에디터 관련
		    frm.body_mime.value = frm.wec.MIMEValue;
		    
		  	//유효성 체크
		    if(validateForm(frm)){

	    		fileList.UploadFile(function(uploadCount){
	    			//첨부파일 업로드 결과 잘못된 형식의 파일이 올라간 경우
	    			if(uploadCount == -1){
	    				initPage();	//첨부파일창 Reload
	    				alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
	    				return;
	    			}
	    			
	    			//첨부파일이 필수인 경우: 사용자 선택사항
	    			//if(uploadCount==0){
	    			//	alert("<spring:message code='secfw.msg.error.fileNon' />");
	    			//	return;
	    			//}

					viewHiddenProgress(true) ;
			    	frm.submit();	
	    		});
		    }

		} else if(flag == "cancel"){
			
			if (confirm("<spring:message code='secfw.msg.ask.cancel' />")){	

				frm.target = "_self";
				frm.action = "<c:url value='/las/lawinformation/library.do' />";
			    frm.method.value = "detailLibrary";

				viewHiddenProgress(true) ;
				frm.submit();
			}
		}
	}
	
	function selectOptions(select){
		var frm = document.frm;
		
		var textValue;
		textValue = select.options[select.selectedIndex].text;
		
		frm.title.value=textValue;
	}
	
	/**
	* Tab 이동
	*/
 
	function moveTab(){
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "/las/lawinformation/guidenedu.do";
		frm.method.value = "listGuideline";
				
		frm.submit();
	}

	/**
	* 메시지 처리
	*/
	function showMessage(msg){
		if(msg != null && msg.length > 1) {
			alert(msg);
		}
	}
	
	/**
	* - FCKEdiotr 세팅 
	*/
	$(document).ready(function(){
		
		//첨부파일창 load
		initPage();
		
		getCodeSelectByUpCd2("frm", "lib_gbn", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_grp_cd="+"<c:out value='${command.info_gbn}'/>"+"&combo_abbr=F&combo_type=T&combo_use_yn=Y&combo_selected="+"<c:out value='${lom.lib_gbn}'/>");
	});	
	
	function initPage(){
	    frm.target			= "fileList";
	    frm.action = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value  	= "forwardClmsFilePage";
		
		frm.submit();	
	}
//-->

</script>

</head>
<body>
<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id="frm" method="post" autocomplete="off">

<!-- search form -->
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />
<input type="hidden" name="srch_start_dt" 	value="<c:out value='${command.srch_start_dt}'/>" />
<input type="hidden" name="srch_end_dt"   	value="<c:out value='${command.srch_end_dt}'/>" />
<input type="hidden" name="srch_combo"      value="<c:out value='${command.srch_combo}'/>" />
<input type="hidden" name="srch_name"       value="<c:out value='${command.srch_name}'/>" />
<input type="hidden" name="srch_lib_gbn"    value="<c:out value='${command.srch_lib_gbn}'/>" />

<!-- key form -->
<input type="hidden" name="info_gbn" id="info_gbn" value="<c:out value='${command.info_gbn}'/>" />
<input type="hidden" name="seqno"    id="seqno"    value="<c:out value='${command.seqno}'/>" />

<!-- 나모 웹 에디터 관련 -->
<input type="hidden" name="body_mime" id="body_mime" value="<c:out value='${lom.cont}' />" />

<!-- 첨부파일정보 -->
<input type="hidden" name="fileInfos" 	value="" />
<input type="hidden" name="file_bigclsfcn" 	value="F004" />
<input type="hidden" name="file_midclsfcn" 	value="<c:out value='${command.info_gbn}'/>" />
<input type="hidden" name="file_smlclsfcn" 	value="" />
<input type="hidden" name="ref_key" 	value="<c:out value='${command.seqno}'/>" />
<input type="hidden" name="view_gbn" 	value="modify" />

<div id="wrap">	
	<div id="container">
		<!-- Location -->
		<div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0" alt="Home" /><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->
		<!-- content -->
		<div id="content">
			<!-- title -->
			<div class="title">
			<c:choose>
				<c:when test="${command.info_gbn=='C00403'}">
				<h3><spring:message code="las.page.title.contract.local.Library" /></h3>
				</c:when>
				<c:otherwise>
				<h3><spring:message code="las.page.title.contract.foreign.Library" /></h3>
				</c:otherwise>
			</c:choose>
			</div>
			<!-- //title -->
			<c:if test="${command.info_gbn=='C00404'}">
			<!-- tab01 -->
			<ul class="tab_basic04">
				<li><a href="javascript:moveTab();"><spring:message code="las.page.field.lawinformation.tab.guideline"/></a></li>
				<li class="on"><a href="#"><spring:message code="las.page.field.lawinformation.tab.samplecont"/></a></li>
			</ul>
			<!-- 두줄라인 추가 --><div class="tal_topLine"></div><!-- //두줄라인 추가 -->
			</c:if>
			<!--  view  -->	
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
			<c:choose>
				<c:when test="${command.info_gbn=='C00403'}">
					<colgroup>
						<col width="15%" />
						<col width="35%" />
						<col width="15%" />
						<col width="35%" />
					</colgroup>
				</c:when>
				<c:otherwise>
					<colgroup>
						<col width="15%" />
						<col width="85%" />
					</colgroup>
				</c:otherwise>
			</c:choose>
				<tbody>
				<c:choose>
					<c:when test="${command.info_gbn=='C00403'}">
					<tr>
						<th><spring:message code='las.page.field.contract.library.lib_gbn' /></th>
						<td>
							<select name="lib_gbn" id="lib_gbn" alt="<spring:message code='las.page.field.contract.library.lib_gbn' />" required >
							</select>
						</td>
						<th><spring:message code='las.page.field.contract.library.reg_dt2' /></th>
						<td>
			        		<input type="text" name="mod_dt" class="text" value="<c:out value='${lom.reg_dt}' />" readonly />
			        	</td>
					</tr>
		        	<tr>
			            <th><spring:message code='las.page.field.contract.library.title' /></th>
			            <td colspan="3">
			            	<span><input name="title" id="title" type="text" class="text" size="50" value="<c:out value='${lom.title}' />" alt="<spring:message code='las.page.field.contract.library.title' />" maxLength="64" required /></span>
			            </td>
		        	</tr>
		        	</c:when>
		        	<c:otherwise>
		        	<tr>
						<th><spring:message code='las.page.field.contract.library.title' /></th>
						<td colspan="3">
							<select name="lib_gbn" id="lib_gbn" alt="<spring:message code='las.page.field.contract.library.title' />" required onChange="javascript:selectOptions(this)">
							</select>
						</td>
						<input type="hidden" name="title" id="title" class="text" size="50" value="<c:out value='${lom.title}' />" />
					</tr>
		        	<tr>
			            <th><spring:message code='las.page.field.contract.library.reg_dt2' /></th>
						<td colspan="3">
			        		<input type="text" name="mod_dt" class="text" value="<%=DateUtil.dateInMain(DateUtil.getShortDate(new Date())) %>" readonly alt="<spring:message code='las.page.field.contract.library.reg_dt2' />" required />
			        	</td>
		        	</tr>
		        	</c:otherwise>
		        </c:choose>
		        	<tr>
						<th><spring:message code='las.page.field.contract.library.usage' /></th>
						<td colspan="3">
							<span><input type="text" name="usage" id="usage" class="text" size="50" value="<c:out value='${lom.usage}' />" alt="<spring:message code='las.page.field.contract.library.usage' />" maxLength="512" /> </span>
						</td>
					</tr>
				<c:choose>
		        	<c:when test="${command.info_gbn=='C00403'}">
		        	<tr>
			            <th><spring:message code='las.page.field.contract.library.rd_auth' /></th>
			            <td colspan="3">
			            	<input type="radio" name="rd_auth" id="rd_auth" class="text" size="50" value="C00502" <c:if test="${lom.rd_auth=='C00502'}">checked</c:if> /><spring:message code='las.page.field.contract.library.rd_auth.all' />
							<input type="radio" name="rd_auth" id="rd_auth" class="text" size="50" value="C00501" <c:if test="${lom.rd_auth=='C00501'}">checked</c:if> /><spring:message code='las.page.field.contract.library.rd_auth.las' />	            	
			            </td>
		        	</tr>
		        	</c:when>
		        	<c:otherwise>
		        			<input type="hidden" name="rd_auth" id="rd_auth" class="text" size="50" value="C00502" checked></input>
		        	</c:otherwise>
		        </c:choose>
					<tr>
						<th><spring:message code="clm.page.msg.common.content" /></th>
						<td colspan="3">
		            		<%@ include file="/WEB-INF/jsp/secfw/common/NamoEditInclude.jspf"%>
		            	</td>
		        	</tr>
					<tr class="end">
			    		<th><spring:message code="las.page.field.board.notice.attach_file" /> <img src="/images/las/ko/common/step_q.gif" alt="info" title="DOC, DOCX, GUL, PDF, OCX, XLS, XLSX, PPT, PPTX,&#13;JPG, HWP, TIF, RTF, ZIP, PTX, TML, TXT, LSX, EML, BMP, GIF, MSG, ALZ, DOT &#13;&#13;<spring:message code='las.msg.com.fileFilter'/>" /></th>
						<td colspan="3">
		            		<div class='ico_maxsize fR'>Max Size : <span>10MB</span></div>
		            		<!-- Sungwoo Replaced scroll option 2014/08/04 -->
		            		<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" class='addfile_iframe' scrolling="auto" allowTransparency="true"></iframe>
		            	</td>
		          	</tr>
		        </tbody>
			</table>
			<!-- //  Form List  -->
			<!-- Function Button  -->
			<div class="t_titBtn">
				<div class="btn_wrap">
					<span class="btn_all_b"><span class="save"></span><a href="javascript:pageAction('modify')"><spring:message code='las.page.button.save' /></a></span>
					<span class="btn_all_w"><span class="cancel"></span><a href="javascript:pageAction('cancel')"><spring:message code='las.page.button.cancel' /></a></span>
				</div>
			</div>
			<!-- //Function Button  -->
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
<SCRIPT language="javascript" FOR="wec" EVENT="OnInitCompleted()">
	var wecObj = document.wec;
	wecObj.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
	wecObj.SetDefaultFontSize("9");
	wecObj.EditMode = 1;
	wecObj.Value = document.frm.body_mime.value;
</SCRIPT>
</html>