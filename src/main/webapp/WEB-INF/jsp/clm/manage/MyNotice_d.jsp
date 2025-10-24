<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.manage.dto.MyNoticeForm" %>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%-- 
/**
 * 파  일  명 : MyNotice_d.jsp
 * 프로그램명 :  MyNotice
 * 설      명 : 
 * 작  성  자 : 유영섭 
 * 작  성  일 : 2011.10. 07
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	MyNoticeForm command = (MyNoticeForm)request.getAttribute("command");
	ListOrderedMap lom = (ListOrderedMap)request.getAttribute("lom"); 
	String sRe = (String)request.getAttribute("sRe");
	sRe = sRe.replaceAll("&lt;br&gt;","<br>").replaceAll("//","/");
%>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>


<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript">

	$(document).ready(function(){
	
		initPage();
		
	    $('#frcontent').bind('load', function() {
	    	setIframe();
	    });
		
	});

/* 	$(window).load(function(){ 
		setIframe();
	 });  */	

	/**
	* Iframe값 세팅
	*/
	function setIframe(){
		frcontent.document.body.innerHTML = document.all.memcontent.value;
	}

	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/manage/myNotice.do' />";  
		if(flag == "list"){	
			viewHiddenProgress(true) ;
		    frm.method.value = "listMyNotice";
			frm.submit();
		}
	}
	

	function modify() {
		viewHiddenProgress(true) ;
		var frm = document.frm ;
		frm.method.value = "forwardContractNegoPointModify" ;
		frm.action = "<c:url value='/clm/share/ContractNegoPoint.do' />"; 
		frm.target = "_self" ;
		frm.submit() ;
	}
	
	function remove(){
		if(confirm("<spring:message code="clm.page.msg.common.announce003" />")) {
			viewHiddenProgress(true) ;
			var frm = document.frm ;
			frm.method.value = "deleteContractNegoPoint" ;
			frm.action = "<c:url value='/clm/share/ContractNegoPoint.do' />"; 
			frm.target = "_self" ;
			frm.submit() ;
		}
	}

	/**
	* 메일첨부파일
	*/
	function initPage(){
	    var frm = document.frm;
	    frm.target = "fileList";
	    frm.action = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value = "forwardComFilePage";
		frm.submit();
	}
	

  



</script>

</head>
<body onLoad=''>

 <div id="wrap"> 
	<div id="container">	
		<!-- Location -->
		<div class="location">
			<img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/>
		</div>
		<!-- //Location -->
		
		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.title.MyNotice.detailTitle" /></h3>
		</div>
		<!-- //title -->
		
		
		<!-- content -->
		<div id="content">
			<div id="content_in">
			
						
			<!-- **************************** Form Setting **************************** -->
			<form:form name="frm" id="frm" method="post" autocomplete="off">
			
			<!-- search form -->
			<input type="hidden" name="method"   value="">
			<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>">
			<input type="hidden" name="srch_start_reg_dt" value="<c:out value='${command.srch_start_reg_dt}'/>" />
			<input type="hidden" name="srch_end_reg_dt"   value="<c:out value='${command.srch_end_reg_dt}'/>" />
			<input type="hidden" name="srch_Addressee"    value="<c:out value='${command.srch_Addressee}'/>" />
			<input type="hidden" name="srch_stat"    value="<c:out value='${command.srch_stat}'/>" />
			<input type="hidden" name="srch_keyword_mailtitle"    value="<c:out value='${command.srch_keyword_mailtitle}'/>" />
			
			
			<!-- key form-->
			<input type="hidden" name="mis_id" value="<c:out value='${command.mis_id}'/>" />
			<input type="hidden" name="module_id" value="<c:out value='${command.module_id}'/>" />
			
			<!-- 첨부파일정보 시작 -->
			<input type="hidden" name="sys_gbn" 	value="mail" />
			<input type="hidden" name="view_gbn" 	value="download" />
			
			<!-- // **************************** Form Setting **************************** -->
			
			<!-- view -->
			
			<div class="btn_wrap mt0">
				<span class="btn_all_w"><span class="list"></span><a href="javascript:pageAction('list');"><spring:message code="secfw.page.button.list" /></a></span>
			</div>
		
			
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
				<colgroup>
					<col width="15%" />
					<col width="35%"/>
					<col width="15%" />
					<col width="35%"/>
				</colgroup>
				<tbody>
					<tr>
						<th><spring:message code="clm.page.field.MyNotice.MailTitle1" /></th>
						<td colspan="3"><c:out value='${lom.subject}' /></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.MyNotice.Addressee" /></th>
						<td colspan="3"><%=sRe %></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.MyNotice.MessageKind" /></th>
						<td colspan="1"><c:out value='${lom.msg_type}' /></td>
			             
			            <th><spring:message code="clm.page.field.MyNotice.regdt" /></th>
						<td><c:out value='${lom.create_date}' /></td>
				   </tr>
				 	<tr>
						<th><spring:message code="clm.page.field.MyNotice.TransferState" /></th>
						<td colspan="3"><c:out value='${lom.status}' /></td>
			       </tr>
				   <tr>
						<th><spring:message code="clm.page.field.MyNotice.originator" /></th>
						<td colspan="3"><c:out value='${lom.sender_mail_addr}' /></td>
				   </tr>
					<tr>
						<td colspan="4">
							<iframe src='/las/blank.do' id='frcontent' style='width:100%; min-height:500px' frameborder="0" scrolling="auto" allowTransparency="true"></iframe>
							<textarea id='memcontent' style='display:none'><c:out value='${lom.body}' escapeXml="false" /></textarea>
						</td>
					</tr>
					<tr>
		            	<th><spring:message code="secfw.page.field.bbs.file" /></th>
		            	<td colspan="3">
		            	<iframe src="<c:url value='/clm/blank.do' />" id="fileList" name="fileList" frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe>
		            	</td>
		          	</tr>
				</tbody>
			</table>
			<!-- //view -->
	
			<div class="btn_wrap">
			<%-- 	<span class="btn_all_w"><span class="edit"></span><a href="javascript:modify();"><spring:message code="las.page.button.modify" /></a></span>
				<span class="btn_all_w"><span class="delete"></span><a href="javascript:remove();"><spring:message code="las.page.button.delete" /></a></span> --%>
				<span class="btn_all_w"><span class="list"></span><a href="javascript:pageAction('list');"><spring:message code="secfw.page.button.list" /></a></span>
			</div>
			</form:form>
			</div>
		</div>
	</div>
</div>
<!-- footer -->
<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
<!-- //footer -->
<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</body>
</html>