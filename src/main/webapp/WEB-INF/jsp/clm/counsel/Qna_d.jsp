<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="java.util.List" %>
<%@ page import="com.sec.clm.counsel.dto.QnaForm" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%-- 
/**
 * 파  일  명 : Qna_d.jsp
 * 프로그램명 : QNA 상세
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%
	ListOrderedMap lom = (ListOrderedMap)request.getAttribute("lom");
    QnaForm command = (QnaForm)request.getAttribute("command");
    List replyList = (List)request.getAttribute("replyList");

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

<script language="javascript">
<!--
	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/counsel/qna.do' />";
		
		if(flag == "reply"){//답변
	    	frm.title.value = "<c:out value='${lom.title}' />";
	    	frm.method.value = "forwardReplyQna";
	    	viewHiddenProgress(true);
	    	frm.submit();
	    	
		}else if(flag == "modify"){//수정
			

				//alert("<spring:message code='clm.msg.alert.qna.replyX'/>");
			    //return;

			frm.method.value = "forwardModifyQna";
		    viewHiddenProgress(true);
			frm.submit();
			
	    }else if(flag == "delete"){//삭제
	    	if(confirm("<spring:message code='secfw.msg.ask.delete' />")){
	    		
	    		if(frm.postup_depth.value == 0 && frm.countReply.value > 0){
	    			alert("<spring:message code='clm.msg.alert.qna.deleteX'/>");
	    			return;
	    		}
	    		
			    frm.method.value = "deleteQna";
			    viewHiddenProgress(true);
			    frm.submit();
			}
	    
	    }else if(flag == "list"){//목록
	    	frm.method.value = "listQna";
	    	viewHiddenProgress(true);
	        frm.submit();
	        
	    }else if(flag == "transfer"){//이관
	    	frm.method.value = "forwardTransferQna";
	    	viewHiddenProgress(true);
	        frm.submit();
	    }
	}
	
	/**
	* 상세내역 조회
	*/
	function detailView(up_seqno, seqno){
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/counsel/qna.do' />";
	
		frm.method.value = "detailQna";
		frm.up_seqno.value = up_seqno;
		frm.seqno.value = seqno;
		viewHiddenProgress(true);
		frm.submit();
	}
	
	/**
	* 메시지 처리
	*/
	function showMessage(msg){
		if( msg != "" && msg != null && msg.length > 1 ) {
			alert(msg);
		}
	}
	
	$(document).ready(function(){
		//첨부파일창 load
		initPage();
	});
	
	function initPage(){
		var frm = document.frm;		
	    frm.target = "fileList";
	    frm.action = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value = "forwardClmsFilePage";
	    getClmsFilePageCheck('frm');
	}
	
//-->

</script>

</head>
<body onLoad="alertMessage('<c:out value='${command.return_message}'/>')" autocomplete="off">

<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id="frm" method="post" autocomplete="off">

<!-- search form -->
<input type="hidden" name="method"       value="" />
<input type="hidden" name="menu_id"      value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage"      value="<c:out value='${command.curPage}'/>">
<!-- key form-->
<input type="hidden" name="seqno"        value="<c:out value='${command.seqno}'/>" />
<input type="hidden" name="up_seqno"     value="<c:out value='${command.up_seqno}'/>" />
<input type="hidden" name="postup_srt"   value="<c:out value='${command.postup_srt}'/>" />
<input type="hidden" name="postup_depth" value="<c:out value='${command.postup_depth}'/>" />
<input type="hidden" name="title"        value="<c:out value='${command.title}'/>" />

<input type="hidden" name="countReply"        value="<c:out value='${countReply}'/>" />

<!-- 첨부파일정보 -->
<input type="hidden" name="fileInfos" 	value="" />
<input type="hidden" name="file_bigclsfcn" 	value="F009" />
<input type="hidden" name="file_midclsfcn" 	value="" />
<input type="hidden" name="file_smlclsfcn" 	value="" />
<input type="hidden" name="ref_key" 	value="<%=lom.get("seqno")%>" />
<input type="hidden" name="view_gbn" 	value="download" />
<!-- // **************************** Form Setting **************************** -->
<div id="wrap">
	<!-- container -->
	<div id="container">	
		<!-- Location -->
		<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->
		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.title.qna.detailTitle" /></h3>
		</div>
		<!-- //title -->
		
		<div class="btn_wrap">
			<c:if test="${command.auth_reply}">
		    <span class="btn_all_w"><span class="reply"></span><a href="javascript:pageAction('reply')"><spring:message code="las.page.button.reply2" /></a></span>
			</c:if>
			<c:if test="${command.auth_transfer}">
			<span class="btn_all_w"><span class="edit"></span><a href="javascript:pageAction('transfer');"><spring:message code="las.page.button.lawsuit.transfer" /></a></span>
			</c:if>
			<c:if test="${command.auth_modify}">
			<span class="btn_all_w"><span class="edit"></span><a href="javascript:pageAction('modify');"><spring:message code="secfw.page.button.modify" /></a></span>
			</c:if>
			<c:if test="${command.auth_delete}">
			<span class="btn_all_w"><span class="delete"></span><a href="javascript:pageAction('delete');"><spring:message code="secfw.page.button.delete" /></a></span>
			</c:if>
			<span class="btn_all_w"><span class="list"></span><a href="javascript:pageAction('list');"><spring:message code="secfw.page.button.list" /></a></span>			
		</div>
		
		<!-- content -->
		<div id="content">
			<!-- view -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
					<col class="tal_w04" />
					<col />
					<col class="tal_w04" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th><spring:message code="clm.page.field.qna.title" /></th>
						<td colspan="3"><c:out value="${lom.title}"/></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.qna.regNm" /></th>
						<td><c:out value="${lom.reg_nm}" />/<c:out value='${lom.regman_jikgup_nm}'/>/<c:out value='${lom.reg_dept_nm}'/></td>
						<th><spring:message code="clm.page.field.qna.regDt" /></th>
						<td><c:out value="${lom.reg_dt}" /></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.qna.cont" /></th>
						<td colspan="3"><%=StringUtil.convertNamoCharsToHtml((String)lom.get("cont"))%></td>
					</tr>
					<tr class="end">
		            	<th><spring:message code="secfw.page.field.bbs.file" /></th>
		            	<td colspan="3">
		            	<iframe src="<c:url value='/clm/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="100px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
		            	</td>
		          	</tr>
				</tbody>
			</table>
			<!-- //view -->
		</div>
		<!-- //content -->
		<!-- 
		<div class="title_basic">
				<h4><spring:message code="clm.page.msg.admin.qnaBrkdown" /></h4>
		</div>
		 -->
		<!-- list -->
		<!--
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
				<colgroup>
					<col width="65%"/>
					<col width="10%"/>
					<col width="15%"/>
					<col width="10%"/>
			  	</colgroup>
			  	<thead>
					<tr>
						<th><spring:message code="clm.page.field.qna.title" /></th>
						<th><spring:message code="clm.page.field.qna.prgrsStatus" /></th>
						<th><spring:message code="clm.page.field.qna.regNm" /></th>
						<th><spring:message code="clm.page.field.qna.regDt" /></th>
					</tr>
				</thead>
			 	<tbody>
		 	        <c:forEach var="list" items="${replyList}">
					 	<tr onMouseOut="this.className='';this.style.cursor='pointer';" onMouseOver="this.className='on';this.style.cursor='pointer';" onClick="detailView(<c:out value='${list.up_seqno}'/>, <c:out value='${list.seqno}'/>);">
					 	    <td class="tL">
					 	    <c:if test="${list.up_seqno > 0}">
								<c:forEach var="x" begin="0" end="${list.postup_depth}" step="1" >
		          					&nbsp;           				
		          				</c:forEach>
		          				<img src="<%=IMAGE%>/icon/icon_reply.gif"></img>
							</c:if>
							<c:if test="${list.new_yn eq 'Y'}">
								<img src="<%=IMAGE%>/icon/ico_new_m.gif" alt="new"/>
							</c:if>
				 	        <c:out value='${list.title}'/>
				 	        </td>
				 	        <td class="tC"><c:out value='${list.prgrs_status_nm}'/></td>
				 	        <td class="tC"><c:out value='${list.reg_nm}'/></td>
				 	        <td class="tC"><c:out value='${list.reg_dt}'/></td>
					 	</tr>
					 </c:forEach>
				 	
			    </tbody>
			</table>
			-->
			<!-- //list -->
			 
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