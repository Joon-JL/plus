<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.Token"%>

<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%--
/**
 * 파  일  명 : Pds_i.jsp
 * 프로그램명 : Pds 등록
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 2012.05
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet" />
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
			    		
						frm.target = "_self";
						frm.action = "<c:url value='/las/board/bbs.do' />";
						
						if(frm.insert_kbn.value==0){
							frm.method.value = "insertPds";
						} else {
							frm.method.value = "insertPds";
						}

						viewHiddenProgress(true) ;
				    	frm.submit();	
			    	});
		    	}
		    }
		    
			
		//취소
		} else if(flag == "cancel"){	
			
			if (confirm("<spring:message code='secfw.msg.ask.cancel' />")){
				
				frm.target = "_self";
				frm.action = "<c:url value='/las/board/bbs.do' />";
				if(frm.insert_kbn.value==0){	
					frm.method.value = "listPds";
				} else {
					frm.method.value = "detailSysQnA";
				}

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
	* - Namo Editor 세팅 
	*/
	$(document).ready(function(){

		var $inputTxt=$('#title');
		if($inputTxt.val()==''){
			$inputTxt.focus();
		}
		
		//첨부파일창 load
		initPage();
	});	
	
	function initPage() {

    	frm.target = "fileList";
    	frm.action = "<c:url value='/clms/common/clmsfile.do' />";
        frm.method.value  = "forwardClmsFilePage";

        frm.submit();
    }
//-->
</script>

</head>
<body>
<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id="frm" method="post" autocomplete="off">
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />

<!-- search form -->
<input type="hidden" name="srch_start_dt" 	value="<c:out value='${command.srch_start_dt}'/>" />
<input type="hidden" name="srch_end_dt"   	value="<c:out value='${command.srch_end_dt}'/>" />
<input type="hidden" name="srch_type"      	value="<c:out value='${command.srch_type}'/>" />
<input type="hidden" name="srch_word"       value="<c:out value='${command.srch_word}'/>" />

<!-- key form-->
<input type="hidden" name="bbs_knd" value="<c:out value='${command.bbs_knd}'/>" />
<input type="hidden" name="insert_kbn" value="<c:out value='${command.insert_kbn}'/>" />
<input type="hidden" name="grp_no" value="<c:out value='${command.grp_no}'/>" />
<input type="hidden" name="grp_seqno" value="<c:out value='${command.grp_seqno}'/>" />
<input type="hidden" name="postup_srt" value="<c:out value='${command.postup_srt}'/>" />
<input type="hidden" name="postup_depth" value="<c:out value='${command.postup_depth}'/>" />

<!-- 첨부파일정보 시작 -->
<input type="hidden" name="fileInfos"   value="" />
<input type="hidden" name="file_bigclsfcn"  value="F007" />
<input type="hidden" name="file_midclsfcn" 	value="<c:out value='${command.bbs_knd}'/>" />
<input type="hidden" name="file_smlclsfcn"  value="" />
<input type="hidden" name="ref_key"     value="" />
<input type="hidden" name="view_gbn" 	value="upload" />

<!-- 이중등록 방지 -->
<input type="hidden" name="TOKEN"  value="<%=Token.set(request) %>" />

<!-- 나모 웹 에디터 관련 -->
<input type="hidden" name="body_mime" id="body_mime" value="" />
<input type="hidden" name="cont" id="cont" value="" />

<input type="hidden" name="isPds" id="isPds" value="<c:out value='${command.isPds}' />" />

<!-- // **************************** Form Setting **************************** -->
<div id="wrap">	
	<div id="container">
		<!-- Location -->
		<div class="location"><IMG SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->
		<!-- content -->
		<div id="content">
			<!-- title -->
			<div class="title">
				<c:choose>
					<c:when test="${command.bbs_knd=='C01201'}">
					<h3><spring:message code="las.page.title.board.SysQnA" /></h3>
					</c:when>
					<c:when test="${command.bbs_knd=='C01202'}">
					<h3><spring:message code="las.page.title.board.bbs" /></h3>
					</c:when>
					<c:when test="${command.bbs_knd=='C01203'}">
					<h3><spring:message code="las.page.title.board.bbs.en" /></h3>
					</c:when>
					<c:when test="${command.bbs_knd=='C01204' || command.bbs_knd=='C01205'}">
					<h3><spring:message code="las.page.title.board.pds_1" /></h3>
					</c:when>
					<c:when test="${command.bbs_knd=='C01206' || command.bbs_knd=='C01207'}">
					<h3><spring:message code="las.page.title.board.pds_2" /></h3>
					</c:when>
					<c:when test="${command.bbs_knd=='C01208' || command.bbs_knd=='C01209'}">
					<h3><spring:message code="las.page.title.board.pds_3" /></h3>
					</c:when>
					<c:when test="${command.bbs_knd=='C01210' || command.bbs_knd=='C01211'}">
					<h3><spring:message code="las.page.title.board.pds_4" /></h3>
					</c:when>
					<c:when test="${command.bbs_knd=='C01212' || command.bbs_knd=='C01213'}">
					<h3><spring:message code="las.page.title.board.pds_5" /></h3>
					</c:when>
				</c:choose>
			</div>
			<!-- //title -->
			<!-- view -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
					<col width="15%" />
					<col width="35%" />
					<col width="15%" />
					<col width="35%" />
				</colgroup>
				<tbody>
		        	<tr>
			            <th><spring:message code="las.page.field.board.notice.title"/></th>
			            <td colspan="3">
			            	<span><input type="text" name="title" id="title" class="text" style="width:600px;" maxLength="256" value="<c:out value='${command.title}' escapeXml="false"/>" alt="<spring:message code='las.page.field.board.notice.title'/>" maxLength="64" required /></span>
			            </td>
		        	</tr>
		        	<tr>
			            <th><spring:message code="las.page.field.board.notice.reg_nm"/></th>
			            <td><c:out value="${command.session_user_nm}"/></td>
			            <th><spring:message code="las.page.field.board.notice.reg_dt"/></th>
			        	<td><%=DateUtil.formatDate(DateUtil.today(), "-") %></td>
		        	</tr>
		        	<tr>
			            <th><spring:message code="clm.page.msg.common.content"/></th>
						<td colspan="3">
							<%@ include file="/WEB-INF/jsp/secfw/common/NamoEditInclude.jspf"%>
						</td>
					</tr>
					<tr class="end">
			            <th><spring:message code="las.page.field.board.notice.attach_file"/> <img src="/images/las/ko/common/step_q.gif" alt="info" title="DOC, DOCX, GUL, PDF, OCX, XLS, XLSX, PPT, PPTX,&#13;JPG, HWP, TIF, RTF, ZIP, PTX, TML, TXT, LSX, EML, BMP, GIF, MSG, ALZ, DOT &#13;&#13;<spring:message code='las.msg.com.fileFilter'/>" /></th>
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
					<span class="btn_all_b"><span class="save"></span><a href="javascript:pageAction('insert')"><spring:message code="las.page.button.save"/></a></span>
					<span class="btn_all_w"><span class="cancel"></span><a href="javascript:pageAction('cancel')"><spring:message code="secfw.page.button.cancel"/></a></span>
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
