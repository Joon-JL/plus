<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%--
/**
 * 파     일     명 : BBSMngDetail.jsp
 * 프로그램명 : 게시판관리 내역 상세 페이지
 * 설               명 : 
 * 작     성     자 : 금현서
 * 작     성     일 : 2009.11
 */
--%>
<%
	ArrayList resultList = (ArrayList)request.getAttribute("resultList");

	ListOrderedMap lom = null;
	
	if(resultList != null && resultList.size() > 0) {
		for(int i=0; i<resultList.size(); i++) {
			lom = (ListOrderedMap)resultList.get(i);
		}
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="secfw.page.title.bbs.BBSMngDetail" /></title>

<LINK href="<c:url value='/style/secfw/popup.css' />" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript">
<!--

	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		var frm = document.frm;
	
		if(flag == "modify"){

			if(validateForm(document.frm) != false) { 

				frm.target = "_parent";
				frm.action = "<c:url value='/secfw/bbsMng.do' />";
			    frm.method.value = "modifyBBSMaster";
				frm.submit();

				opener.reload();
				self.close();
			}

		} else if(flag == "delete"){

			frm.target = '_parent';
			frm.action = "<c:url value='/secfw/bbsMng.do' />";
		    frm.method.value = "deleteBBSMaster";
			frm.submit();

			opener.reload();
			self.close();
			
		} else if(flag == "close"){
			self.close();
		} 
	}


//-->

</script>

</head>
<body class="pop">
<!-- 
**************************** Form Setting **************************** 
-->
<form:form name="frm" method="post" autocomplete="off">
<!-- 페이지 공통 -->
<input type="hidden" name="method"   value="">

<input type="hidden" name="bbs_id"   value="<%=lom.get("bbs_id") %>">
<!-- //
**************************** Form Setting **************************** 
-->
<!-- Popup Top -->
<h1 class="pop_top"><spring:message code="secfw.page.title.bbs.BBSMngDetail" /></h1>
<!-- //Popup Top -->
<!-- Popup Body-->
<div class="pop_body">
	<div class="pop_area">

		<!-- Popup Detail -->
		<div class="dscrolly" style="height:360px;">
			<div class="pop_content">
			
				<!-- list -->
				<table class="basic_form">
		          <colgroup>
		          <col width="30%"/>
		          <col />
		          </colgroup>
		          <tr>
		            <!-- 게시판ID -->
		            <th><spring:message code="secfw.page.field.bbs.bbs_id"/><span class="astro">*</span></th>
		            <td colspan="3"><%=lom.get("bbs_id") %></td>
		          </tr>
		          <tr>
		            <!-- 게시판 명 -->
		            <th><spring:message code="secfw.page.field.bbs.bbs_nm"/><span class="astro">*</span></th>
		            <td colspan="3">
		            	<input type="text" name="bbs_nm" id="bbs_nm" size="60" class="text all" value="<%=lom.get("bbs_nm") %>" fieldTitle="<spring:message code="secfw.page.field.bbs.bbs_nm"/>" required="*" maxLength="100"/>
		            </td>
		          </tr>
		          <tr>
		            <!-- 게시판 영문명 -->
		            <th><spring:message code="secfw.page.field.bbs.bbs_nm_eng"/><span class="astro">*</span></th>
		            <td colspan="3">
		            	<input type="text" name="bbs_nm_eng" id="bbs_nm_eng" size="60" class="text all" value="<%=lom.get("bbs_nm_eng") %>" fieldTitle="<spring:message code="secfw.page.field.bbs.bbs_nm_eng"/>" required="*" maxLength="100"/>
		            </td>
		          </tr>
		          <tr>
		            <!-- 새글유지일자 -->
		            <th><spring:message code="secfw.page.field.bbs.new_hold_term"/> <span class="astro">*</span></th>
		            <td>
		            	<input type="text" name="new_hold_term" id="new_hold_term"  class="text all" value="<%=lom.get("new_hold_term") %>" fieldTitle="<spring:message code="secfw.page.field.bbs.new_hold_term" />" required="*" fromNum="1" toNum="999"/>
		            </td>
		            <!-- 게시기간설정여부 -->
		            <th><spring:message code="secfw.page.field.bbs.hold_term_yn"/> <span class="astro">*</span></th>
		            <td>
		            	<input type="radio" name="hold_term_yn" id="hold_term_yn" value="Y" class="radio" <%= "Y".equals(lom.get("hold_term_yn")) ? "checked" : "" %> >Y
		  				<input type="radio" name="hold_term_yn" id="hold_term_yn" value="N" class="radio" <%= "N".equals(lom.get("hold_term_yn")) ? "checked" : "" %> >N 
		            </td>
		          </tr>
		          <tr>
		            <!-- 댓글여부 -->
		            <th><spring:message code="secfw.page.field.bbs.reply_yn"/> <span class="astro">*</span></th>
		            <td>
		            	<input type="radio" name="reply_yn" id="reply_yn" value="Y" class="radio" <%= "Y".equals(lom.get("reply_yn")) ? "checked" : "" %> >Y
		  				<input type="radio" name="reply_yn" id="reply_yn" value="N" class="radio" <%= "N".equals(lom.get("reply_yn")) ? "checked" : "" %> >N 
		            </td>
		            <!-- 한줄답변여부-->
		            <th><spring:message code="secfw.page.field.bbs.append_yn"/> <span class="astro">*</span></th>
		            <td>
		            	<input type="radio" name="append_yn" id="append_yn" value="Y" class="radio" <%= "Y".equals(lom.get("append_yn")) ? "checked" : "" %> >Y
		  				<input type="radio" name="append_yn" id="append_yn" value="N" class="radio" <%= "N".equals(lom.get("append_yn")) ? "checked" : "" %> >N
		            </td>
		          </tr>
		          <tr>
		            <!-- 첨부파일여부 -->
		            <th><spring:message code="secfw.page.field.bbs.file_yn"/> <span class="astro">*</span></th>
		            <td>
		            	<input type="radio" name="file_yn" id="file_yn" value="Y" class="radio" <%= "Y".equals(lom.get("file_yn")) ? "checked" : "" %> >Y
		  				<input type="radio" name="file_yn" id="file_yn" value="N" class="radio" <%= "N".equals(lom.get("file_yn")) ? "checked" : "" %> >N 
		            </td>
		            <!-- 긴급여부 -->
		            <th><spring:message code="secfw.page.field.bbs.urgency_yn"/> <span class="astro">*</span></th>
		            <td>
		            	<input type="radio" name="urgency_yn" id="urgency_yn" value="Y" class="radio" <%= "Y".equals(lom.get("urgency_yn")) ? "checked" : "" %> >Y
		  				<input type="radio" name="urgency_yn" id="urgency_yn" value="N" class="radio" <%= "N".equals(lom.get("urgency_yn")) ? "checked" : "" %> >N 
		            </td>
		          </tr>
		          <tr>
		            <!-- 추천여부 -->
		            <th><spring:message code="secfw.page.field.bbs.recommend_yn"/> <span class="astro">*</span></th>
		            <td>
		            	<input type="radio" name="recommend_yn" id="recommend_yn" value="Y" class="radio" <%= "Y".equals(lom.get("recommend_yn")) ? "checked" : "" %> >Y
		  				<input type="radio" name="recommend_yn" id="recommend_yn" value="N" class="radio" <%= "N".equals(lom.get("recommend_yn")) ? "checked" : "" %> >N 
		            </td>
		            <!-- 사용여부 -->
		            <th><spring:message code="secfw.page.field.bbs.use_yn"/> <span class="astro">*</span></th>
		            <td>
		            	<input type="radio" name="use_yn" id="use_yn" value="Y" class="radio" <%= "Y".equals(lom.get("use_yn")) ? "checked" : "" %> >Y
		  				<input type="radio" name="use_yn" id="use_yn" value="N" class="radio" <%= "N".equals(lom.get("use_yn")) ? "checked" : "" %> >N 
		            </td>
		          </tr>
		          <tr>
		            <!-- 설명 -->
		            <th><spring:message code="secfw.page.field.bbs.comments"/></th>
		            <td colspan="3">
		            	<input type="text" name="comments" id="comments" size="60" class="text all" value="<%=lom.get("comments") %>" fieldTitle="<spring:message code="secfw.page.field.bbs.comments" />"/>
		            </td>
		          </tr>    
				</table>
				<!-- //list -->
			</div>
		</div>
		<!-- //Popup Detail -->
	</div>
</div>
<!-- //Popup Body -->
<!-- Popup Footer -->
<div class="pop_footer">
	<!-- Function Button -->
    <a href="javascript:pageAction('modify');" class="bt_all_b"><span><img src="<c:url value='/images/secfw/ico/ico_save.gif' />"><spring:message code="secfw.page.field.bbs.close"/></span></a>
    <a href="javascript:pageAction('delete');" class="bt_all_w"><span><img src="<c:url value='/images/secfw/ico/ico_minus.gif' />"><spring:message code="secfw.page.field.bbs.delete"/></span></a>
    <a href="javascript:pageAction('close');" class="bt_all_w"><span><img src="<c:url value='/images/secfw/ico/ico_cancel.gif' />"><spring:message code="secfw.page.field.bbs.close"/></span></a>
	<!-- // Function Button -->
</div>
<!-- //Popup Footer -->
</form:form>
</body>
</html>