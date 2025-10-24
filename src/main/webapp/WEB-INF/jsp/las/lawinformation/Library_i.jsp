<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>


<%@ page import="com.sec.las.lawinformation.dto.LibraryForm" %>

<%@page import="com.sds.secframework.common.util.Token"%>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ include file= "/WEB-INF/jsp/common/common.jsp" %>
<%--
/**
 * 파  일  명 : ContractLibrary_i.jsp
 * 프로그램명 : 국내/해외 계약서 등록
 * 설      명 : 
 * 작  성  자 : 신승완
 * 작  성  일 : 2011.08
 */
 --%>
<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
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

		//저장
		if(flag == "insert"){

			frm.target = "_self";
			frm.action = "<c:url value='/las/lawinformation/library.do' />";
		    frm.method.value = "insertLibrary";
		    
		    //나모웹에디터 관련
		    frm.body_mime.value = frm.wec.MIMEValue;
			
		    //유효성 체크
		    if(validateForm(frm)){
		    	
		    	if(confirm("<spring:message code='las.msg.ask.insert'/>")){

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
			}
			
		} else if(flag == "cancel"){	
			
			if (confirm("<spring:message code='secfw.msg.ask.cancel' />")){

				frm.target = "_self";
				frm.action = "<c:url value='/las/lawinformation/library.do' />";
			    frm.method.value = "listLibrary";

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
	* - Namo Editor 세팅 
	*/
	$(document).ready(function(){
		
		getCodeSelectByUpCd2("frm", "lib_gbn", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_grp_cd="+"<c:out value='${command.info_gbn}'/>"+"&combo_abbr=F&combo_type=T&combo_use_yn=Y&combo_selected="+"<c:out value='${command.lib_gbn}'/>");
		
		//첨부파일창 load
		initPage();
		
		var $inputTxt=$('#title');
		if($inputTxt.val()==''){
			$inputTxt.focus();
		}
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

<!-- key form-->
<input type="hidden" name="info_gbn" id="info_gbn" value="<c:out value='${command.info_gbn}'/>" />
<input type="hidden" name="seqno"    id="seqno"    value="<c:out value='${command.seqno}'/>" />

<!-- 나모 웹 에디터 관련 -->
<input type="hidden" name="body_mime" id="body_mime" value="" />

<!-- 첨부파일정보 -->
<input type="hidden" name="fileInfos" 	value="" />
<input type="hidden" name="file_bigclsfcn" 	value="F004" />
<input type="hidden" name="file_midclsfcn" 	value="<c:out value='${command.info_gbn}'/>" />
<input type="hidden" name="file_smlclsfcn" 	value="" />
<input type="hidden" name="ref_key" 	value="<c:out value='${command.seqno}'/>" />
<input type="hidden" name="view_gbn" 	value="upload" />

<!-- // **************************** Form Setting **************************** -->

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
			<!-- view -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
			<c:choose>
				<c:when test="${command.info_gbn=='C00403'}">
				<colgroup>
					<col width="15%"/>
					<col width="35%"/>
					<col width="15%"/>
					<col width="35%"/>
				</colgroup>
				</c:when>
				<c:otherwise>
				<colgroup>
					<col width="15%"/>
					<col width="85%"/>
				</colgroup>
				</c:otherwise>
			</c:choose>
				<tbody>
				<!-- 해외 : 분류 와 제목은 동일, 조회권한은 전체로... -->
				<c:choose>
					<c:when test="${command.info_gbn=='C00403'}">
					<tr>
			            <th><spring:message code='las.page.field.contract.library.lib_gbn' /></th>
			            <td>
							<select name="lib_gbn" id="lib_gbn" alt="<spring:message code='las.page.field.contract.library.lib_gbn' />" required>
							</select>
						</td>
			        	<th><spring:message code='las.page.field.contract.library.reg_dt2' /></th>
			        	<td><%=DateUtil.formatDate(DateUtil.today(), "-") %></td>
		        	</tr>
		        	<tr>
			            <th><spring:message code='las.page.field.contract.library.title' /></th>
			            <td colspan="3">
			            	<input type="text" name="title" id="title" class="text" size="50" value="" alt="<spring:message code='las.page.field.contract.library.title' />" maxLength="64" required />
			            </td>
		        	</tr>
		        	</c:when>
		        	<c:otherwise>
		        	<tr>
			            <th><spring:message code='las.page.field.contract.library.title' /></th>
			            <td width='100' colspan="3">
							<select name="lib_gbn" id="lib_gbn" alt="<spring:message code='las.page.field.contract.library.title' />" required onChange="javascript:selectOptions(this)">
							</select>
						</td>
						<input type="hidden" name="title" id="title" class="text" size="50" value="" />
		        	</tr>
		        	<tr>
			            <th><spring:message code='las.page.field.contract.library.reg_dt' /></th>
			        	<td colspan="3"><%=DateUtil.formatDate(DateUtil.today(), "-") %>
			        	</td>
		        	</tr>
		        	</c:otherwise>
		        </c:choose>
		        	<tr>
			            <th><spring:message code='las.page.field.contract.library.usage' /></th>
			            <td colspan="3">
			            	<span><input type="text" name="usage" id="usage" class="text" size="50" value="" alt="<spring:message code='las.page.field.contract.library.usage' />" maxLength="512" /></span>
			            </td>
		        	</tr>
		        <c:choose>
		        	<c:when test="${command.info_gbn=='C00403'}">
		        	<tr>
			            <th><spring:message code='las.page.field.contract.library.rd_auth' /></th>
			            <td colspan="3">
			            	<input type="radio" name="rd_auth" id="rd_auth" class="text" size="50" value="C00502" checked /><spring:message code='las.page.field.contract.library.rd_auth.all' /></input>
			            	<input type="radio" name="rd_auth" id="rd_auth" class="text" size="50" value="C00501" /><spring:message code='las.page.field.contract.library.rd_auth.las' /></input>		            	
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
			<!-- //view  -->
			<!--  Function Button  -->
			<div class="t_titBtn">
				<div class="btn_wrap">
					<span class="btn_all_b"><span class="save"></span><a href="javascript:pageAction('insert')"><spring:message code='las.page.button.save' /></a></span>
					<span class="btn_all_w"><span class="cancel"></span><a href="javascript:pageAction('cancel')"><spring:message code='las.page.button.cancel' /></a></span>
				</div>
			</div>
			<!-- //  Function Button  -->
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
<!-- 나모 웹에디터 관련 추가 -->
<SCRIPT language="javascript" FOR="wec" EVENT="OnInitCompleted()">
	var wecObj = document.wec;
	wecObj.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
	wecObj.SetDefaultFontSize("9");
	wecObj.EditMode = 1;
</SCRIPT>
</html>
