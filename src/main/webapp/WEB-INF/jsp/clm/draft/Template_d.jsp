<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.draft.dto.LibraryForm" %>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%-- 
/**
 * 파  일  명 : Template_d.jsp
 * 프로그램명 : 표준 Template 상세
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	LibraryForm command = (LibraryForm)request.getAttribute("command");
	ListOrderedMap lom = (ListOrderedMap)request.getAttribute("lom"); 
	List oppntCdList = (List)request.getAttribute("oppntCdList");

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
		frm.action = "<c:url value='/clm/draft/template.do' />";
		if(flag == "modify"){
			viewHiddenProgress(true) ;
		    frm.method.value = "forwardModifyTemplate";
		    frm.lib_no.value = <%=lom.get("lib_no")%>;
			frm.submit();

		} else if(flag == "delete"){	
			if(confirm("<spring:message code='secfw.msg.ask.delete' />")){
				viewHiddenProgress(true) ;
			    frm.method.value = "deleteTemplate";
				frm.lib_no.value = <%=lom.get("lib_no")%>;
			    frm.submit();
			}	
		} else if(flag == "list"){	
			viewHiddenProgress(true) ;
		    frm.method.value = "listTemplate";
		    frm.lib_no.value = 0;
			frm.submit();
		}
	}
	
	/**
	* 메시지 처리
	*/
	function showMessage(msg){
		if( msg != "" && msg != null && msg.length > 1 ) {
			alert(msg);
		}
	}
	
	function initPage(){
		var frm = document.frm;
	    frm.target			= "fileList";
	    frm.action 			= "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value  	= "forwardClmsFilePage";
	    getClmsFilePageCheck('frm');
	}	
	
//-->

</script>

</head>
<body onload='initPage();showMessage("<c:out value='${returnMessage}'/>");'>

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
<input type="hidden" name="lib_no"	value="" />

<!-- 첨부파일정보 -->
<input type="hidden" name="fileInfos" 	value="" />
<input type="hidden" name="file_bigclsfcn" 	value="F013" />
<input type="hidden" name="file_midclsfcn" 	value="" />
<input type="hidden" name="file_smlclsfcn" 	value="" />
<input type="hidden" name="ref_key" 	value="<%=lom.get("lib_no")%>" />
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
			<h3><spring:message code="clm.page.title.template.detailTitle" /></h3>
		</div>
		<!-- //title -->

		<!-- content -->
		<div id="content">
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
						<th><spring:message code="clm.page.field.template.bizClsfcn" /></th>
						<td><%=lom.get("biz_clsfcn_nm")%></td>
						<th><spring:message code="clm.page.field.template.depthClsfcn" /></th>
						<td><%=lom.get("depth_clsfcn_nm")%></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.template.cnclsnpurpsClsfcn" /></th>
						<td colspan="3"><%=lom.get("cnclsnpurps_bigclsfcn_nm")%><%if(!"".equals((String)lom.get("cnclsnpurps_midclsfcn"))){ %> ▶ <%}%><%=lom.get("cnclsnpurps_midclsfcn_nm")%></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.template.langGbn" /></th>
						<td colspan="3"><%=lom.get("lang_gbn_nm")%></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.template.cntrtOppntCd" /></th>
						<td colspan="3">
						<%if (oppntCdList != null && oppntCdList.size()>0){
						   for(int i=0; i< oppntCdList.size(); i++){
							   ListOrderedMap oppnt = (ListOrderedMap)oppntCdList.get(i);
						%>
							<span><%=oppnt.get("cntrt_oppnt_nm")%></span>	
						<%	   
								if(i != oppntCdList.size()-1){
						%>
							/
						<%			
								}
						   }
						} %>
						</td>
					</tr>				
					<tr>
						<th><spring:message code="clm.page.field.template.title" /></th>
						<td colspan="3"><%=lom.get("title")%></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.template.cont" /></th>
						<td colspan="3">
							<c:out value='${lom.cont}' escapeXml="false" />
						</td>
					</tr>
					<tr class="end">
		            	<th><spring:message code="secfw.page.field.bbs.file" /></th>
		            	<td colspan="3">
		            	<iframe src="<c:url value='/clm/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="100px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
		            	</td>
		          	</tr>
				</tbody>
			</table>
			<!-- //view -->
	
			<div class="btn_wrap">
	<%if(command.isAuth_modify()){ %>		
				<span class="btn_all_w"><span class="edit"></span><a href="javascript:pageAction('modify');"><spring:message code="secfw.page.button.modify" /></a></span>
	<%}%>
	<%if(command.isAuth_delete()){ %>		
				<span class="btn_all_w"><span class="delete"></span><a href="javascript:pageAction('delete');"><spring:message code="secfw.page.button.delete" /></a></span>
	<%}%>
				<span class="btn_all_w"><span class="list"></span><a href="javascript:pageAction('list');"><spring:message code="secfw.page.button.list" /></a></span>
			</div>
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
</html>