<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.PageUtil" %>

<%--
/**
 * 파     일     명 : BoardSMngList.jsp
 * 프로그램명 : 게시판 관리 등록/수정
 * 설               명 : 
 * 작     성     자 : 강성문
 * 작     성     일 : 2011.03.17
 */
--%>
<%

	//메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="secfw.page.title.bbs.BBSMngInsert" /></title>

<LINK href="<c:url value='/style/secfw/common.css' />" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/shri/shri.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/ui.core.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript">
	function init() {
		var f = document.frm ;

		//getCodeSelectByUpCd("frm", "board_type", "/secfw/combo.do?method=getCommonCodeCombo&grp_cd=BOARD_TYPE&use_yn=Y&language=KOR&abbr=F") ;
		getComboOptions("frm", "board_type", "BOARD_TYPE", "Y", "<c:out value="${command.board_type}"/>") ;
		getComboOptions("frm", "div_cd", "BOARD_DIV", "Y", "<c:out value="${command.div_cd}"/>") ;

		
		disabledYn("row_cnt", "<c:out value='${command.row_cnt_yn}'/>") ;
		disabledYn("max_file_cnt", "<c:out value='${command.file_yn}'/>") ;
		disabledYn("max_file_size", "<c:out value='${command.file_yn}'/>") ;
		disabledYn("div_cd", "<c:out value='${command.div_yn}'/>") ;
		disabledYn("append_recommend_yn", "<c:out value='${command.append_yn}'/>") ;
		disabledYn("send_mail_addr", "<c:out value='${command.send_mail_yn}'/>") ;
		disabledYn("urgency_day", "<c:out value='${command.urgency_yn}'/>") ;
	}

	function pageAction(flag){
		var f = document.frm ;

		if(flag=="list") {
			f.action = "<c:url value='/secfw/boardMng.do' />" ;
			f.method.value = "listBoardMng" ;
			f.submit() ;
		} else if(flag=="save") {
			if(f.hold_day_yn[0].checked && f.default_hold_day.value<1) {
				alert("<spring:message code='secfw.page.field.board.ifYselectEffDate'/>") ;
				f.default_hold_day.focus() ;
				return ;
			}
			
			if(validateForm(document.frm)) { 
				f.action = "<c:url value='/secfw/boardMng.do' />" ;
				f.method.value = f.board_id.value=="" ? "insertBoardMng" : "modifyBoardMng";
				f.submit() ;
			}
		} else if(flag=="reset") {
			f.reset() ;
		} else if(flag=="delete") {
			if(confirm("<spring:message code='secfw.msg.ask.delete' />")){
				f.action = "<c:url value='/secfw/boardMng.do' />" ;
				f.method.value = "deleteBoardMng" ;
				f.submit() ;
			}
		}
	}

	function setDefaultHoldDay(index){
		var f = document.frm ;
		f.default_hold_day.value = index==0 ? 30 : 0 ;
	}
</script>

</head>
<body onLoad="init();alertMessage('<c:out value="${command.return_message}"/>')">
<div class="contentWrap">
	<div class="content_area">
<!-- 
**************************** Form Setting **************************** 
-->
<form:form id="frm" name="frm" method="post" autocomplete="off">
	<!-- 페이지 공통 -->
	<input type="hidden" name="method"   value="">
	<input type="hidden" name="curPage" value="${command.curPage}">
	<input type="hidden" name="srch_board_nm" value="${command.srch_board_nm}">
	<input type="hidden" name="srch_use_yn" value="${command.srch_use_yn}">
	
	<!-- 페이지별  -->
	<input type="hidden" name="board_id"   value="${command.board_id}">
<!-- //
**************************** Form Setting **************************** 
-->
		<!-- Location -->
		<div class="location"><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- Title -->
<c:choose>
	<c:when test="${command.board_id==''}">
		<h3><spring:message code="secfw.page.title.bbs.BBSMngInsert" /></h3>
	</c:when>
	<c:otherwise>
		<h3><spring:message code="secfw.page.title.bbs.BBSMngModify" /></h3>
	</c:otherwise>
</c:choose>
		
		<!-- List -->
        <table class="detail_form">
          <colgroup>
          <col width="120"/>
          <col />
          <col width="120"/>
          <col />
          </colgroup>
          <tr>
            <!-- 게시판 명 -->
            <th><spring:message code="secfw.page.field.bbs.bbs_nm"/> <span class="astro">*</span></th>
            <td>
            	<input type="text" name="board_nm_ko" id="board_nm_ko" value="<c:out value="${command.board_nm_ko}"/>" class="text all" fieldTitle="<spring:message code="secfw.page.field.bbs.bbs_nm" />" required="*" maxLength="300"/>
            </td>
            <th><spring:message code="secfw.page.field.bbs.bbs_nm_eng" /><span class="astro">*</span></th>
            <td>
            	<input type="text" name="board_nm_en" id="board_nm_en" value="<c:out value="${command.board_nm_en}"/>" class="text all" fieldTitle="<spring:message code="secfw.page.field.bbs.bbs_nm_eng" />" required="*" maxLength="300"/>
            </td>
          </tr>
          <tr>
            <!-- 게시판 타입 -->
            <th><spring:message code="secfw.page.field.bbs.bbs_type"/> <span class="astro">*</span></th>
            <td>
            	<select id="board_type" name="board_type"></select>
            </td>
            <!-- 기본 페이지당 ROW 수 -->
            <th><spring:message code="secfw.page.field.bbs.default_row_cnt" /><span class="astro">*</span></th>
            <td>
            	<input type="text" name="default_row_cnt" id="default_row_cnt" class="text all" value="<c:out value="${command.default_row_cnt}"/>" fieldTitle="<spring:message code="secfw.page.field.bbs.default_row_cnt"/>" required="*" num="i" fromNum="1"/>
            </td>
          </tr>
          <tr>
            <!-- ROW 설정 가능 여부 -->
            <th><spring:message code="secfw.page.field.bbs.row_cnt_yn"/> <span class="astro">*</span></th>
            <td>
            	<input type="radio" name="row_cnt_yn" id="row_cnt_yn" value="Y" class="radio" onClick="disabledYn('row_cnt', false)" <c:if test="${command.row_cnt_yn eq 'Y'}">checked</c:if>>Y
  				<input type="radio" name="row_cnt_yn" id="row_cnt_yn" value="N" class="radio" onClick="disabledYn('row_cnt', true)" <c:if test="${command.row_cnt_yn eq 'N'}">checked</c:if>>N &nbsp;
  				<input type="text" name="row_cnt" id="row_cnt" class="text_disabled" value="<c:out value='${command.row_cnt}'/>" disabled fieldTitle="<spring:message code="secfw.page.field.bbs.row_cnt"/>" required="*" maxLength="100"/>
            </td>
            <!-- 새글유지일자 -->
            <th><spring:message code="secfw.page.field.bbs.new_hold_term" /><span class="astro">*</span></th>
            <td>
            	<input type="text" name="new_hold_day" id="new_hold_day"  class="text all" value="<c:out value='${command.new_hold_day}'/>" fieldTitle="<spring:message code="secfw.page.field.bbs.new_hold_term" />" required="*" fromNum="1" toNum="999"/>
            </td>
            
          </tr>
          <tr>
          	<!-- 기본유효일자 -->
            <th><spring:message code="secfw.page.field.bbs.default_hold_day" /><span class="astro">*</span></th>
            <td>
            	<input type="text" name="default_hold_day" id="default_hold_day" class="text all" value="<c:out value='${command.default_hold_day}'/>" fieldTitle="<spring:message code="secfw.page.field.bbs.default_hold_day"/>" required="*" num="i" fromNum="0"/>
            </td>
            <!-- 유효기간설정여부 -->
            <th><spring:message code="secfw.page.field.bbs.hold_term_yn" /><span class="astro">*</span></th>
            <td>
            	<input type="radio" name="hold_day_yn" id="hold_day_yn" value="Y" class="radio" onClick="setDefaultHoldDay(0)" <c:if test="${command.hold_day_yn eq 'Y'}">checked</c:if>>Y
  				<input type="radio" name="hold_day_yn" id="hold_day_yn" value="N" class="radio" onClick="setDefaultHoldDay(1)" onClick="" <c:if test="${command.hold_day_yn eq 'N'}">checked</c:if>>N 
            </td>
          </tr>
          
          <tr>
            <!-- 첨부파일여부 -->
            <th><spring:message code="secfw.page.field.bbs.file_yn" /> <span class="astro">*</span></th>
            <td colspan="3">
            	<input type="radio" name="file_yn" id="file_yn" value="Y" class="radio" onClick="disabledYn('max_file_cnt', false);disabledYn('max_file_size', false);" <c:if test="${command.file_yn eq 'Y'}">checked</c:if>>Y
  				<input type="radio" name="file_yn" id="file_yn" value="N" class="radio" onClick="disabledYn('max_file_cnt', true);disabledYn('max_file_size', true);" <c:if test="${command.file_yn eq 'N'}">checked</c:if>>N &nbsp;&nbsp;&nbsp;
  				<spring:message code="secfw.page.field.bbs.max_file_cnt" /> : 
  					<input type="text" name="max_file_cnt" id="max_file_cnt"  class="text" size="3" value="<c:out value='${command.max_file_cnt}'/>" fieldTitle="<spring:message code="secfw.page.field.bbs.max_file_cnt" />" required="*" num="i"/>
  				<spring:message code="secfw.page.field.bbs.max_file_size" /> : 
  					<input type="text" name="max_file_size" id="max_file_size"  class="text" size="3" value="<c:out value='${command.max_file_size}'/>" fieldTitle="<spring:message code="secfw.page.field.bbs.max_file_size" />" required="*" num="i" fromNum="1"/>Mbyte
            </td>
           	
          </tr>
          
          <tr>
            <!-- 이미지등록여부 -->
            <th><spring:message code="secfw.page.field.bbs.img_yn" /><span class="astro">*</span></th>
            <td>
            	<input type="radio" name="img_yn" id="img_yn" value="Y" class="radio" <c:if test="${command.img_yn eq 'Y'}">checked</c:if>>Y
  				<input type="radio" name="img_yn" id="img_yn" value="N" class="radio" <c:if test="${command.img_yn eq 'N'}">checked</c:if>>N
            </td>
            <!-- 구분사용여부 -->
            <th><spring:message code="secfw.page.field.bbs.div_yn" /><span class="astro">*</span></th>
            <td>
            	<input type="radio" name="div_yn" id="div_yn" value="Y" class="radio" onClick="disabledYn('div_cd', false)" <c:if test="${command.div_yn eq 'Y'}">checked</c:if>>Y
  				<input type="radio" name="div_yn" id="div_yn" value="N" class="radio" onClick="disabledYn('div_cd', true)"  <c:if test="${command.div_yn eq 'N'}">checked</c:if>>N 
  				
  				<select name="div_cd" id="div_cd" disabled class="text_disabled"></select>
            </td>
          </tr>
          <tr>
            <!-- 추천여부 -->
            <th><spring:message code="secfw.page.field.bbs.recommend_yn" /><span class="astro">*</span></th>
            <td>
            	<input type="radio" name="recommend_yn" id="recommend_yn" value="Y" class="radio" <c:if test="${command.recommend_yn eq 'Y'}">checked</c:if>>Y
  				<input type="radio" name="recommend_yn" id="recommend_yn" value="N" class="radio" <c:if test="${command.recommend_yn eq 'N'}">checked</c:if>>N 
            </td>
            <!-- 사용여부 -->
            <th><spring:message code="secfw.page.field.bbs.use_yn" /><span class="astro">*</span></th>
            <td>
            	<input type="radio" name="use_yn" id="use_yn" value="Y" class="radio" <c:if test="${command.use_yn eq 'Y'}">checked</c:if>>Y
  				<input type="radio" name="use_yn" id="use_yn" value="N" class="radio" <c:if test="${command.use_yn eq 'N'}">checked</c:if>>N 
            </td>
          </tr>
          
          <tr>
            <!-- 댓글여부 -->
            <th><spring:message code="secfw.page.field.bbs.reply_yn" /><span class="astro">*</span></th>
            <td>
            	<input type="radio" name="reply_yn" id="reply_yn" value="Y" class="radio" <c:if test="${command.reply_yn eq 'Y'}">checked</c:if>>Y
  				<input type="radio" name="reply_yn" id="reply_yn" value="N" class="radio" <c:if test="${command.reply_yn eq 'N'}">checked</c:if>>N 
            </td>
            <!-- 긴급여부 -->
            <th><spring:message code="secfw.page.field.bbs.urgency_yn" /><span class="astro">*</span></th>
            <td>
            	<input type="radio" name="urgency_yn" id="urgency_yn" value="Y" class="radio" onClick="disabledYn('urgency_day', false)" <c:if test="${command.urgency_yn eq 'Y'}">checked</c:if>>Y
  				<input type="radio" name="urgency_yn" id="urgency_yn" value="N" class="radio" onClick="disabledYn('urgency_day', true)" <c:if test="${command.urgency_yn eq 'N'}">checked</c:if>>N 
  				&nbsp;&nbsp;
  				<spring:message code="secfw.page.field.bbs.urgency_day" /> : 
  				<input type="text" name="urgency_day" id="urgency_day" class="text_disabled" size="3" value="<c:out value='${command.urgency_day}'/>" required="*" num="i" fromNum="1" fieldTitle="<spring:message code="secfw.page.field.bbs.urgency_day"/>"/>
            </td>
            
          </tr>
          <tr>
            <!-- 한줄답변여부 -->
            <th><spring:message code="secfw.page.field.bbs.append_yn" /><span class="astro">*</span></th>
            <td>
            	<input type="radio" name="append_yn" id="append_yn" value="Y" class="radio" onClick="disabledYn('append_recommend_yn', false)" <c:if test="${command.append_yn eq 'Y'}">checked</c:if>>Y
  				<input type="radio" name="append_yn" id="append_yn" value="N" class="radio" onClick="disabledYn('append_recommend_yn', true)" <c:if test="${command.append_yn eq 'N'}">checked</c:if>>N
            </td>
            <!-- 댓글추천여부 -->
            <th><spring:message code="secfw.page.field.bbs.append_recommend_yn" /><span class="astro">*</span></th>
            <td>
            	<input type="radio" name="append_recommend_yn" id="append_recommend_yn" value="Y" class="radio" <c:if test="${command.append_recommend_yn eq 'Y'}">checked</c:if>>Y
  				<input type="radio" name="append_recommend_yn" id="append_recommend_yn" value="N" class="radio" <c:if test="${command.append_recommend_yn eq 'N'}">checked</c:if>>N 
            </td>
          </tr>
           
          <tr>
            <!-- 공개여부 -->
            <th><spring:message code="secfw.page.field.bbs.open_yn" /><span class="astro">*</span></th>
            <td>
            	<input type="radio" name="open_yn" id="open_yn" value="Y" class="radio" <c:if test="${command.open_yn eq 'Y'}">checked</c:if>>Y
  				<input type="radio" name="open_yn" id="open_yn" value="N" class="radio" <c:if test="${command.open_yn eq 'N'}">checked</c:if>>N
            </td>
            <!-- 익명여부 -->
            <th><spring:message code="secfw.page.field.bbs.anonymity_yn" /><span class="astro">*</span></th>
            <td>
            	<input type="radio" name="anonymity_yn" id="anonymity_yn" value="Y" class="radio" <c:if test="${command.anonymity_yn eq 'Y'}">checked</c:if>>Y
  				<input type="radio" name="anonymity_yn" id="anonymity_yn" value="N" class="radio" <c:if test="${command.anonymity_yn eq 'N'}">checked</c:if>>N
            </td>
          </tr>
          
          <tr>
            <!-- 임시저장여부 -->
            <th><spring:message code="secfw.page.field.bbs.temp_save_yn" /><span class="astro">*</span></th>
            <td>
            	<input type="radio" name="temp_save_yn" id="temp_save_yn" value="Y" class="radio" <c:if test="${command.temp_save_yn eq 'Y'}">checked</c:if>>Y
  				<input type="radio" name="temp_save_yn" id="temp_save_yn" value="N" class="radio" <c:if test="${command.temp_save_yn eq 'N'}">checked</c:if>>N
            </td>
            <!-- 메일전송여부 -->
            <th><spring:message code="secfw.page.field.bbs.send_mail_yn" /><span class="astro">*</span></th>
            <td>
            	<input type="radio" name="send_mail_yn" id="send_mail_yn" value="Y" class="radio" onClick="disabledYn('send_mail_addr', false)" <c:if test="${command.send_mail_yn eq 'Y'}">checked</c:if>>Y
  				<input type="radio" name="send_mail_yn" id="send_mail_yn" value="N" class="radio" onClick="disabledYn('send_mail_addr', true)" <c:if test="${command.send_mail_yn eq 'N'}">checked</c:if>>N 
				&nbsp;&nbsp;
  				<spring:message code="secfw.page.field.bbs.send_mail_addr" /> : 
  				<input type="text" name="send_mail_addr" id="send_mail_addr" class="text_disabled" size="13" value="<c:out value='${command.send_mail_addr}'/>" required="*" maxLength="1000" email fieldTitle="<spring:message code="secfw.page.field.bbs.send_mail_addr"/>"/>
            </td>
          </tr>
          <tr>
            <!-- 보안여부 -->
            <th><spring:message code="secfw.page.field.bbs.security_yn" /><span class="astro">*</span></th>
            <td>
            	<input type="radio" name="security_yn" id="security_yn" value="Y" class="radio" <c:if test="${command.security_yn eq 'Y'}">checked</c:if>>Y
  				<input type="radio" name="security_yn" id="security_yn" value="N" class="radio" <c:if test="${command.security_yn eq 'N'}">checked</c:if>>N
            </td>
            <!-- 조회가능자지정여부 -->
            <th><spring:message code="secfw.page.field.bbs.read_user_yn" /><span class="astro">*</span></th>
            <td>
            	<input type="radio" name="read_user_yn" id="read_user_yn" value="Y" class="radio" <c:if test="${command.read_user_yn eq 'Y'}">checked</c:if>>Y
  				<input type="radio" name="read_user_yn" id="read_user_yn" value="N" class="radio" <c:if test="${command.read_user_yn eq 'N'}">checked</c:if>>N
            </td>
          </tr>
          
          <tr>
            <!-- 설명 -->
            <th><spring:message code="secfw.page.field.bbs.comments" /></th>
            <td colspan="3">
            	<input type="text" name="comments" id="comments" class="text all" value="<c:out value='${command.comments}'/>" fieldTitle="<spring:message code="secfw.page.field.bbs.comments" />" maxLength="100"/>
            </td>
          </tr> 
          <tr>
            <!-- 권한 -->
            <th rowspan="3"><spring:message code="secfw.page.field.bbs.auth" /></th>
            <td colspan="3">
            	<spring:message code="secfw.page.field.bbs.insert_auth" /> : <a href="#" class="bt_fn ico_add"><span><spring:message code="secfw.page.field.board.add"/></span></a>
            	<span id="insert_auth"></span>
            </td>
          </tr>
          <tr>
            <td colspan="3">
            	<spring:message code="secfw.page.field.bbs.reply_auth" /> : <a href="#" class="bt_fn ico_add"><span><spring:message code="secfw.page.field.board.add"/></span></a>
            	<span id="reply_auth"></span>
            </td>
          </tr>
          <tr>
            <td colspan="3">
            	<spring:message code="secfw.page.field.bbs.append_auth" /> : <a href="#" class="bt_fn ico_add"><span><spring:message code="secfw.page.field.board.add"/></span></a>
            	<span id="append_auth"></span>
            </td>
          </tr>
         </table>
		<div class="fR">
			<a href="javascript:pageAction('save')"  class="bt_all_b"><span><img src="<c:url value='/images/secfw/ico/ico_save.gif' />"><spring:message code="secfw.page.field.board.save"/></span></a>
    	  	<a href="javascript:pageAction('reset')" class="bt_all_w"><span><img src="<c:url value='/images/secfw/ico/ico_reset.gif' />"><spring:message code="secfw.page.field.board.init"/></span></a> 
<c:if test="${command.board_id!=''}">
			<a href="javascript:pageAction('delete')" class="bt_all_w"><span><img src="<c:url value='/images/secfw/ico/ico_delete.gif' />"><spring:message code="secfw.page.field.board.delete"/></span></a>
</c:if>    	  	
      		<a href="javascript:pageAction('list')"  class="bt_all_w"><span><img src="<c:url value='/images/secfw/ico/ico_list.gif' />"><spring:message code="secfw.page.field.board.list"/></span></a> 
		</div>
	</form:form>
	<!-- Footer -->
	<address>
		<spring:message code="secfw.page.copyright.content.bottom" />
	</address>
	<!-- //Footer -->
	</div>
</div>

</body>
</html>