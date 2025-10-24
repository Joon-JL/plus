<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.share.dto.ContractNegoPointForm" %>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%-- 
/**
 * 파  일  명 : ContractNegoPoint_d.jsp
 * 프로그램명 : 주요거래 협상포인트 상세 
 * 설      명 : 
 * 작  성  자 : 유영섭 
 * 작  성  일 : 2011.10. 07
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	ContractNegoPointForm command = (ContractNegoPointForm)request.getAttribute("command");
	ListOrderedMap lom = (ListOrderedMap)request.getAttribute("lom"); 

%>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<LINK href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet">
<LINK href="<%=CSS%>/common.css"   type="text/css" rel="stylesheet">

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>


<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript">

$(document).ready(function(){

	initPage();
	
});




	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/share/ContractNegoPoint.do' />"; 
		if(flag == "list"){	
			viewHiddenProgress(true) ;
		    frm.method.value = "listContractNegoPoint";
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
	* 신규첨부파일
	*/
	function initPage(){

        var frm = document.frm;
		frm.target          = "fileList";
		frm.action = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value    = "forwardClmsFilePage";
		getClmsFilePageCheck('frm');    

    }   


  



</script>

</head>
<body onLoad=''>

<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id="frm" method="post" autocomplete="off">

<!-- search form -->
<input type="hidden" name="method"   value="">
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>">


<!-- key form-->
<input type="hidden" name="nego_no" value="<c:out value='${command.nego_no}'/>" />

<!-- 첨부파일정보 -->
<input type="hidden" name="fileInfos" 	value="" />
<input type="hidden" name="file_bigclsfcn" 	value="F014" />
<input type="hidden" name="file_midclsfcn" 	value="" />
<input type="hidden" name="file_smlclsfcn" 	value="" />
<input type="hidden" name="ref_key" 	value="<%=lom.get("nego_no")%>" />
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
			<h3><spring:message code="clm.page.title.negopoint.detailTitle" /></h3>
		</div>
		<!-- //title -->
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
						<th><spring:message code="clm.page.field.negopoint.nego_theother_person" /></th>
						<td colspan="3"><c:out value='${lom.cntrt_oppnt_nm}' /></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.negopoint.title" /></th>
						<td colspan="3"><c:out value='${lom.title}' /></td>
					
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.negopoint.regNm" /></th>
						<td colspan="1"><c:out value='${lom.reg_nm}' /></td>
			             
			            <th><spring:message code="clm.page.field.negopoint.regdt" /></th>
						<td><c:out value='${lom.reg_dt}' /></td>
							  
						  
						
							
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.negopoint.cont" /></th>
						<td colspan="3">
							<c:out value='${lom.cont}' escapeXml="false" />
						</td>
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
	
			<div class="btn_wrap">
				<%if(command.isAuth_modify()){ %>
				<span class="btn_all_w"><span class="edit"></span><a href="javascript:modify();"><spring:message code="las.page.button.modify" /></a></span>
				<%} %>
				<%if(command.isAuth_delete()){ %>
				<span class="btn_all_w"><span class="delete"></span><a href="javascript:remove();"><spring:message code="las.page.button.delete" /></a></span>
				<%} %>
				<span class="btn_all_w"><span class="list"></span><a href="javascript:pageAction('list');"><spring:message code="secfw.page.button.list" /></a></span>
			</div>
		</div>
		<!-- //content -->
        <!-- footer -->
			<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- //footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
</form:form>
</body>
</html>