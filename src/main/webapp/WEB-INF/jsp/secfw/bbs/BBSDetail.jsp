<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>
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
 * 파     일     명 : BBSDetail.jsp
 * 프로그램명 : 게시내역 상세
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

	ArrayList getBBSDetail      = (ArrayList)request.getAttribute("getBBSDetail");
	String    listBBSFile       = (String)request.getAttribute("listBBSFile");
	ArrayList listBBSAppend     = (ArrayList)request.getAttribute("listBBSAppend");
	ArrayList getBBSPrevNextID  = (ArrayList)request.getAttribute("getBBSPrevNextID");
	String    getPrevTitle      = (String)request.getAttribute("getPrevTitle");
	String    getNextTitle      = (String)request.getAttribute("getNextTitle");
	String    getBBSRecommendYn = (String)request.getAttribute("getBBSRecommendYn");
		
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
	
	//이전글, 다음글 아이디
	String prevNoticeId = "";
	String nextNoticeId = "";
	
	if(getBBSPrevNextID != null && getBBSPrevNextID.size() > 0) {
		ListOrderedMap prevNoticeIdLm = (ListOrderedMap)getBBSPrevNextID.get(0);
		prevNoticeId = (String)prevNoticeIdLm.get("notice_id");

		ListOrderedMap nextNoticeIdLm = (ListOrderedMap)getBBSPrevNextID.get(1);
		nextNoticeId = (String)nextNoticeIdLm.get("notice_id");
	}

	//게시판 옵션
	ListOrderedMap lom = (ListOrderedMap)request.getAttribute("bbsOption");
	
	String bbsId             = "";
	String bbsNm             = "";
	String bbsNmEng          = "";
	BigDecimal newHoldTerm   = null;
	String holdTermYn        = "";
	String replyYn           = "";
	String appendYn          = "";
	String fileYn            = "";
	String urgencyYn         = "";
	String recommendYn       = "";
	
	if(lom != null) {
		bbsId             = (String)lom.get("bbs_id");
		bbsNm             = (String)lom.get("bbs_nm");
		bbsNmEng          = (String)lom.get("bbs_nm_eng");
		newHoldTerm       = (BigDecimal)lom.get("new_hold_term");
		holdTermYn        = (String)lom.get("hold_term_yn");
		replyYn           = (String)lom.get("reply_yn");
		appendYn          = (String)lom.get("append_yn");
		fileYn            = (String)lom.get("file_yn");
		urgencyYn         = (String)lom.get("urgency_yn");
		recommendYn       = (String)lom.get("recommend_yn");
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="secfw.page.title.bbs.BBSDetail" /></title>

<LINK href="<c:url value='/style/secfw/common.css' />" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/util/fckeditor/fckeditor.js' />"></script>
<script language="javascript">
<!--


	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		var frm = document.frm;
	
		if(flag == "reply"){

		    frm.ref_cnt_apply_yn.value = "N"; //조회수 증가 방지
			
			frm.target = "_self";
			frm.action = "<c:url value='/secfw/bbs.do' />";
		    frm.method.value = "getBBSDetailForIns";
		    
			frm.submit();

		} else if(flag == "modify"){

		    frm.ref_cnt_apply_yn.value = "N"; //조회수 증가 방지
			
			frm.target = "_self";
			frm.action = "<c:url value='/secfw/bbs.do' />";
		    frm.method.value = "getBBSDetailForUpd";
		    
			frm.submit();

		} else if(flag == "delete"){	

			frm.target = "_self";
			frm.action = "<c:url value='/secfw/bbs.do' />";
		    frm.method.value = "deleteBBSDetail";
		    
			frm.submit();
			
		} else if(flag == "recommend"){	

		    frm.ref_cnt_apply_yn.value = "N"; //조회수 증가 방지
			
			frm.target = "_self";
			frm.action = "<c:url value='/secfw/bbs.do' />";
		    frm.method.value = "insertBBSRecommend";
		    
			frm.submit();
			
		} else if(flag == "list"){	

			frm.target = "_self";
			frm.action = "<c:url value='/secfw/bbs.do' />";
		    frm.method.value = "listBBSDetailPage";
		    
			frm.submit();
			
		} else if(flag == "insertAppend") {

		    frm.ref_cnt_apply_yn.value = "N"; //조회수 증가 방지
			
			frm.target = "_self";
			frm.action = "<c:url value='/secfw/bbs.do' />";
		    frm.method.value = "insertBBSAppend";
		    
			frm.submit();
			
		} 
	}

	/**
	* 상세내역 조회
	*/
	function detailView(bbs_id, notice_id){
		var frm = document.frm;
	
	    frm.bbs_id.value    = bbs_id;
	    frm.notice_id.value = notice_id;
		
		frm.target = "_self";
		frm.action = "<c:url value='/secfw/bbs.do' />";
		frm.method.value = "detailBBSDetail";
	
		frm.submit();		
	}

	/**
	* 한줄답변 삭제
	*/
	function deleteBBSAppend(append_seq){
		var frm = document.frm;
	
	    frm.append_seq.value = append_seq;
	    frm.ref_cnt_apply_yn.value = "N"; //조회수 증가 방지
		
		frm.target = "_self";
		frm.action = "<c:url value='/secfw/bbs.do' />";
		frm.method.value = "deleteBBSAppend";
	
		frm.submit();		
	}

	
	/**
	* 페이지초기화
	*/
	function initPage() {

<%
	if("Y".equals(fileYn)) {
%>		
	    var frm = document.frm;
	    
	    frm.target		 = "fileList";
	    frm.action       = "<c:url value='/secfw/bbs.do' />";
		frm.method.value = "goFileUploadPage";
		frm.fileInfos.value = encodeURIComponent(frm.fileInfos.value);
		frm.submit();
<%
	}
%>		
	}

	/**
	* 메시지 처리
	*/
	function showMessage(msg){
		if(msg != null && msg.length > 1) {
			alert(msg);
		}
	}
//-->

</script>

</head>
<body onLoad="showMessage('<%=returnMessage %>');initPage();">

<div class="contentWrap">
	<div class="content_area">
<!-- **************************** Form Setting **************************** -->
<form:form name="frm" method="post" autocomplete="off">
<!-- 페이지 공통 -->
<input type="hidden" name="method"   value="">
<input type="hidden" name="menu_id"  value="${command.menu_id}">
	
<!-- 검색처리 및 페이지처리 -->	
<input type="hidden" name="doSearch" value="${command.doSearch}" />
<input type="hidden" name="curPage" value="${command.curPage}" />

<!-- 게시내역관리 -->
<input type="hidden" name="bbs_id"                value="${command.bbs_id}" />
<input type="hidden" name="notice_id"             value="<%=noticeId %>" />
<input type="hidden" name="srch_start_notice_ymd" value="${command.srch_start_notice_ymd}" />
<input type="hidden" name="srch_end_notice_ymd"   value="${command.srch_end_notice_ymd}" />
<input type="hidden" name="srch_cntnt_type"       value="${command.srch_cntnt_type}" />
<input type="hidden" name="srch_cntnt"            value="${command.srch_cntnt}" />

<!-- 첨부파일정보 -->
<input type="hidden" name="file_ref_no" value="<%=fileRefNo %>" />
<input type="hidden" name="fileInfos" 	value="<%=listBBSFile %>" />
<input type="hidden" name="view_gbn" 	value="download" />

<!-- 한줄답변 SEQ정보 (삭제시 사용) -->
<input type="hidden" name="append_seq" 	value="" />
<!-- 조회카운트 증가여부 -->
<input type="hidden" name="ref_cnt_apply_yn" 	value="" />

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
          <col width="80"/>
          <col width="80"/>
          <col width="70"/>
          <col width="50"/>
          </colgroup>
          <tr>
            <th><spring:message code="secfw.page.field.bbs.title"/></th>
            <td colspan="5">
<%
	if("Y".equals(urgencyYn)) {
		if("Y".equals(detailLom.get("urgency_yn"))) {
%>
            	<span><img src="<c:url value='/images/secfw/ico/ico_emergency.gif' />"></span>
<%
		}
	}
%>
            	<span>&nbsp;<%= detailLom.get("notice_title")%></span>
           	</td>
          </tr>
          <tr>
            <th><spring:message code="secfw.page.field.bbs.writer"/></th>
            <td><%=detailLom.get("user_nm") + "/" + detailLom.get("dept_nm") + "/" + detailLom.get("comp_nm") %></td>
            <th><spring:message code="secfw.page.field.bbs.regDate"/></th>
            <td><%=DateUtil.formatDate((String)detailLom.get("reg_dt"),"-") %></td>
            <th><spring:message code="secfw.page.field.bbs.searchCount"/></th>
            <td><%=detailLom.get("ref_cnt") %></td>
          </tr>
<%
	if("Y".equals(holdTermYn) && !"Y".equals(replyYn)) {
%>
          <tr>
            <th><spring:message code="secfw.page.field.bbs.effectiveDate"/></th>
            <td colspan="6">
              	<%= DateUtil.formatDate((String)detailLom.get("notice_start_ymd"), "-") %> ~ <%= DateUtil.formatDate((String)detailLom.get("notice_end_ymd"), ".") %> 
            </td>
          </tr>
<%
	}
%>
          <tr>
            <td colspan="6" class="pd">
            <div class="text_area" style="height:250px;">
            <%=detailLom.get("notice_cntnt") %>
            </div>
            </td>
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
<%
	if("Y".equals(appendYn)) {
%>
		<!-- 한줄답변  -->
		<div class="comment_outline">
			<table class="comment_table">
			  <colgroup>
		          <col width="150"/>
		          <col width="5"/>
		          <col />
		          <col width="120"/>
	          </colgroup>			
				<tr>
					<th colspan="3"><input type="text" name="append_cntnt" id="textfield" class="text_red all"></th>
					<th><a href="javascript:pageAction('insertAppend');" class="bt_fn"><span class="txt_b"><spring:message code="secfw.page.field.bbs.registration"/></span></a></th>
				</tr>
<%
		if(listBBSAppend != null && listBBSAppend.size() > 0) {
			for(int i=0; i<listBBSAppend.size(); i++) {
			ListOrderedMap listBBSAppendMap = (ListOrderedMap)listBBSAppend.get(i);
%>				
				<tr>
					<td class="member"><strong><%=listBBSAppendMap.get("user_nm") %></strong> <%=listBBSAppendMap.get("dept_nm") + " " + listBBSAppendMap.get("comp_nm") %> </td>
					<td><span class="bar">|</span></td>
					<td><%=listBBSAppendMap.get("append_cntnt") %> 
<%
				if(session.getAttribute("secfw.session.user_id").equals(listBBSAppendMap.get("reg_id"))) { //등록자가 본인이면 삭제가능
%>
						<img src="<c:url value='/images/secfw/ico/ico_delete.gif' />" onClick="javascript:deleteBBSAppend('<%= listBBSAppendMap.get("append_seq")%>')" alt="Delete">
<%
				} 
%>
					</td>
					<td align="center"><%=listBBSAppendMap.get("reg_dt") %></td>
				</tr>
<%
			}
		} 	
%>
			</table>
		</div>
		<!-- // 한줄답변  -->
<%
	}
%>
		 <div class="detail_form_pn">
          <span class="pre"><spring:message code="secfw.page.field.bbs.prevArtc"/> : </span><a href="javascript:detailView('<%=bbsId %>','<%=prevNoticeId %>');"><%= getPrevTitle %></a><br>
          <span class="next"><spring:message code="secfw.page.field.bbs.nextArtc"/> : </span><a href="javascript:detailView('<%=bbsId %>','<%=nextNoticeId %>');"><%= getNextTitle %></a>
		 </div>
		<!--  Function Button  -->
		<div class="bt_right">
<%
	// 본인글의 경우
	if(regId != null && regId.equals((String)session.getAttribute("secfw.session.user_id"))) { 
		// 답변의 게시판
		if("Y".equals(replyYn)) {
%>      
 	     	<a href="javascript:pageAction('reply')" class="bt_all_w"><span><img src="<c:url value='/images/secfw/ico/ico_reply.gif' />"><spring:message code="secfw.page.field.bbs.addReply"/></span></a> 
<%
		}
%>
	      	<a href="javascript:pageAction('modify')" class="bt_all_w"><span><img src="<c:url value='/images/secfw/ico/ico_edit.gif' />"><spring:message code="secfw.page.field.bbs.update"/></span></a> 
	      	<a href="javascript:pageAction('delete')" class="bt_all_w"><span><img src="<c:url value='/images/secfw/ico/ico_minus.gif' />"><spring:message code="secfw.page.field.bbs.delete"/></span></a> 
	      	<a href="javascript:pageAction('list')" class="bt_all_w"><span><img src="<c:url value='/images/secfw/ico/ico_list.gif' />"><spring:message code="secfw.page.field.bbs.list"/></span></a> 
<% 
	// 타인글의 경우
	} else {
		// 답변의 게시판
		if("Y".equals(replyYn)) {
%>
 	     	<a href="javascript:pageAction('reply')" class="bt_all_w"><span><img src="<c:url value='/images/secfw/ico/ico_reply.gif' />"><spring:message code="secfw.page.field.bbs.addReply"/></span></a> 
<%
		}
		// 추천가능게시판 + 추천이 없을경우
		if("Y".equals(recommendYn) && "N".equals(getBBSRecommendYn)) {
%>
	      	<a href="javascript:pageAction('recommend')" class="bt_all_w"><span><img src="<c:url value='/images/secfw/ico/ico_good.gif' />"><spring:message code="secfw.page.field.bbs.recommand"/></span></a> 
<%
		}
%>
	      	<a href="javascript:pageAction('list')" class="bt_all_w"><span><img src="<c:url value='/images/secfw/ico/ico_list.gif' />"><spring:message code="secfw.page.field.bbs.list"/></span></a> 
<%
	}
%>
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