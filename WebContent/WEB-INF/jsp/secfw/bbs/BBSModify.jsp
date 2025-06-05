<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<!--
 * FCKeditor - The text editor for Internet - http://www.fckeditor.net
 * Copyright (C) 2003-2009 Frederico Caldeira Knabben
 *
 * == BEGIN LICENSE ==
 *
 * Licensed under the terms of any of the following licenses at your
 * choice:
 *
 *  - GNU General Public License Version 2 or later (the "GPL")
 *    http://www.gnu.org/licenses/gpl.html
 *
 *  - GNU Lesser General Public License Version 2.1 or later (the "LGPL")
 *    http://www.gnu.org/licenses/lgpl.html
 *
 *  - Mozilla Public License Version 1.1 or later (the "MPL")
 *    http://www.mozilla.org/MPL/MPL-1.1.html
 *
 * == END LICENSE ==
 *
-->
<%--
/**
 * 파     일     명 : BBSModify.jsp
 * 프로그램명 : 게시내역 수정페이지
 * 설               명 : 
 * 작     성     자 : 금현서
 * 작     성     일 : 2009.11
 */
--%>
<%

	//메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");

	//처리결과 메시지
	String returnMessage = (String)request.getAttribute("returnMessage");
	
	ArrayList getBBSDetail     = (ArrayList)request.getAttribute("getBBSDetail");
	String    listBBSFile      = (String)request.getAttribute("listBBSFile");
	ArrayList listBBSAppend    = (ArrayList)request.getAttribute("listBBSAppend");
	ArrayList getBBSPrevNextID = (ArrayList)request.getAttribute("getBBSPrevNextID");
	String getBBSRecommendYn   = (String)request.getAttribute("getBBSRecommendYn");
	
	String regId = "";
	String noticeId = "";
	String fileRefNo = "";
	
	//게시글 상세내역
	ListOrderedMap detailLom = null;
	
	if(getBBSDetail != null && getBBSDetail.size() > 0) {
		detailLom = (ListOrderedMap)getBBSDetail.get(0);
		
		if(detailLom != null && getBBSDetail.size() > 0) {
			noticeId = (String)detailLom.get("notice_id");
			regId    = (String)detailLom.get("reg_id");
			fileRefNo = StringUtil.bvl((String)detailLom.get("file_ref_no"),"");
		}
	}

	ListOrderedMap lom = (ListOrderedMap)request.getAttribute("bbsOption");
	
	String bbsId             = "";
	String bbsNm             = "";
	String bbsNmEng          = "";
	String bbsType           = "";
	BigDecimal defaultHoldTerm   = null;
	BigDecimal urgency_hold_term = null;
	String holdTermYn        = "";
	String replyYn           = "";
	String appendYn          = "";
	String fileYn            = "";
	String urgencyYn         = "";
	String recommendYn       = "";
	String approvalNeedYn    = "";
	String extraBbsYn        = "";
	String extraBbsUrl       = "";
	
	if(lom != null) {
		bbsId             = (String)lom.get("bbs_id");
		bbsNm             = (String)lom.get("bbs_nm");
		bbsNmEng          = (String)lom.get("bbs_nm_eng");
		bbsType           = (String)lom.get("bbs_type");
		defaultHoldTerm   = (BigDecimal)lom.get("default_hold_term");
		urgency_hold_term = (BigDecimal)lom.get("urgency_hold_term");
		holdTermYn        = (String)lom.get("hold_term_yn");
		replyYn           = (String)lom.get("reply_yn");
		appendYn          = (String)lom.get("append_yn");
		fileYn            = (String)lom.get("file_yn");
		urgencyYn         = (String)lom.get("urgency_yn");
		recommendYn       = (String)lom.get("recommend_yn");
		approvalNeedYn    = (String)lom.get("approval_need_yn");
		extraBbsYn        = (String)lom.get("extra_bbs_yn");
		extraBbsUrl       = (String)lom.get("extra_bbs_url");
	}

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="secfw.page.title.bbs.BBSMngList" /></title>

<LINK href="<c:url value='/style/secfw/common.css' />" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
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
<%
	if("Y".equals(fileYn)) {
%>		
			//첨부파일 업로드
			var uploadCount = fileList.UploadFile();
<%
	}
%>
			frm.target = "_self";
			frm.action = "<c:url value='/secfw/bbs.do' />";
		    frm.method.value = "modifyBBSDetail";
		    
			frm.submit();

		} else if(flag == "list"){	

			frm.target = "_self";
			frm.action = "<c:url value='/secfw/bbs.do' />";
		    frm.method.value = "listBBSDetailPage";
		    
			frm.submit();
			
		}
	}

	function initPage() {
<%
	if("Y".equals(fileYn)) {
%>		
	    var frm = document.frm;
	    
	    frm.target		 = "fileList";
	    frm.action       = "<c:url value='/secfw/bbs.do' />";
		frm.method.value = "goFileUploadPage";
		frm.submit();
<%
	}
%>
	}

	/**
	* - FCKEdiotr 세팅 
	*/
	$(document).ready(function(){
		var sBasePath = "<c:url value='/util/fckeditor/' />" ;
		 
		var oFCKeditor = new FCKeditor( 'notice_cntnt' ) ;
		oFCKeditor.BasePath	= sBasePath ;
		oFCKeditor.ToolbarSet = 'MyToolbar' ;
		oFCKeditor.ReplaceTextarea();
	});
	
//-->

</script>

</head>
<body onLoad="initPage();">

<div class="contentWrap">
	<div class="content_area">
<!-- **************************** Calendar 관련 추가 영역 **************************** -->
<div id='popCal' style='position: absolute; visibility: hidden; ridge; width: 10'>
<iframe name="popFrame" src="<c:url value='/secfw/popCalendar.do?method=goPopCalendar' />" frameborder="0" scrolling="no" width=196 height=188></iframe></div>
<script event=onclick() for=document> 
if(popCal.style.visibility == "visible"){
  popCal.style.visibility="hidden";
}
</script>
<!-- //**************************** Calendar 관련 추가 영역 **************************** -->

<!-- **************************** Form Setting **************************** -->
<form:form name="frm" method="post" autocomplete="off">
<!-- 페이지 공통 -->
<input type="hidden" name="method"   value="" />
	
<!-- 검색처리 및 페이지처리 -->
<input type="hidden" name="doSearch" value="${command.doSearch}" />
<input type="hidden" name="curPage" value="${command.curPage}" />

<!-- 게시내역관리 -->
<input type="hidden" name="bbs_id"                value="${command.bbs_id}" />
<input type="hidden" name="notice_id"             value="${command.notice_id}" />
<input type="hidden" name="srch_start_notice_ymd" value="${command.srch_start_notice_ymd}" />
<input type="hidden" name="srch_end_notice_ymd"   value="${command.srch_end_notice_ymd}" />
<input type="hidden" name="srch_cntnt_type"       value="${command.srch_cntnt_type}" />
<input type="hidden" name="srch_cntnt"            value="${command.srch_cntnt}" />

<!-- 첨부파일정보 -->
<input type="hidden" name="fileInfos" 	value="<%=listBBSFile %>" />
<input type="hidden" name="file_ref_no" value="<%=StringUtil.bvl(detailLom.get("file_ref_no"),"") %>" />
<input type="hidden" name="view_gbn" 	value="modify" />

<!-- 조회카운트 증가여부 -->
<input type="hidden" name="ref_cnt_apply_yn" 	value="N" />
<!-- //**************************** Form Setting **************************** -->
		<!-- Location -->
		<div class="location"><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->
		<!-- Title -->
		<h3><spring:message code="secfw.page.field.bbs.bbsList"/></h3>
		<!-- //Title -->
		<!--  Form List  -->
		<table class="detail_form">
          <colgroup>
          <col width="100"/>
          <col />
          </colgroup>
          <tr>
            <th><spring:message code="secfw.page.field.bbs.title"/><span class="astro">*</span></th>
            <td>
            	<span><input name="notice_title" id="notice_title" type="text" class="text" value="<%= detailLom.get("notice_title")%>" /></span>
<%
	if("Y".equals(urgencyYn)) {
%>
            	<span><input type="checkbox" id="urgency_yn" name="urgency_yn" /><label><spring:message code="secfw.page.field.bbs.urgent"/></label></span>
<%
	}
%>
            </td>
          </tr>
<%
	if("Y".equals(holdTermYn)) {
%>
          <tr>
            <th><spring:message code="secfw.page.field.bbs.effectiveDate"/><span class="astro">*</span></th>
            <td>
              	<input type="text" name="notice_start_ymd" id="notice_start_ymd"  class="input_calendar" value="<%=DateUtil.formatDate((String)detailLom.get("notice_start_ymd"), "-") %>" /><img src="<c:url value='/images/secfw/ico/ico_input_calendar.gif' />" onClick="popFrame.fPopCalendar(notice_start_ymd,notice_start_ymd,popCal)" class="cp">
	            &nbsp;~&nbsp;
	            <input type="text" name="notice_end_ymd" id="notice_end_ymd"  class="input_calendar" value="<%=DateUtil.formatDate((String)detailLom.get("notice_end_ymd"), "-") %>" /><img src="<c:url value='/images/secfw/ico/ico_input_calendar.gif' />" onClick="popFrame.fPopCalendar(notice_start_ymd,notice_start_ymd,popCal)" class="cp">
            </td>
          </tr>
<%
	}
%>
          <tr>
            <td colspan="2" class="pd">
            	<textarea name="notice_cntnt" id="notice_cntnt" cols="45" rows="20" class="text all">
            		<%=detailLom.get("notice_cntnt") %>
            	</textarea></td>
          </tr>
<%
	if("Y".equals(fileYn)) {
%>
          <tr>
            <th><spring:message code="secfw.page.field.bbs.attachedFile"/><span class="astro"></span></th>
            <td colspan="5">
            <iframe src="<c:url value='/las/blank.do' />" name="fileList" frameborder="0" width="100%" height="98px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
            </td>
          </tr>
<%
	}
%>
		</table>
		<!-- //  Form List  -->
		<!--  Function Button  -->
		<div class="fR">
	      	<a href="javascript:pageAction('modify')"  class="bt_all_b"><span><img src="<c:url value='/images/secfw/ico/ico_save.gif' />"><spring:message code="secfw.page.field.bbs.save"/></span></a> 
	      	<a href="javascript:pageAction('list')"  class="bt_all_w"><span><img src="<c:url value='/images/secfw/ico/ico_list.gif' />"><spring:message code="secfw.page.field.bbs.list"/></span></a> 
		</div>
		<!-- //  Function Button  -->
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
