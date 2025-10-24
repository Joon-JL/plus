<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="com.sec.clm.admin.dto.DimensionForm" %>
<%-- 
/**
 * 파  일  명 : DimensionWord_d.jsp
 * 프로그램명 : 계약용어집 상세
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%
    

    DimensionForm command = (DimensionForm)request.getAttribute("command");

    String   menuNavi  = (String)request.getAttribute("secfw.menuNavi");
    String   menu_id   = command.getMenu_id();    // 메뉴 아이디로 3개의 화면을 컨트롤 한다.
    String   menuGubun = "";

	if(menu_id.equals("20130319155933166_0000000380")||menu_id.equals("20130321153745987_0000000437")){   // 계약 용어집
		menuGubun = "C05302";
	}  else if(menu_id.equals("20111106105940035_0000000283")) {  // 계약 유형별 이해
		menuGubun = "C05303";
	} else {
		menuGubun = ""; // 값을 가지고 오지 못할 경우 
	}

	ListOrderedMap lom = (ListOrderedMap)request.getAttribute("lom"); 
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet" />

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
		frm.action = "<c:url value='/clm/admin/dimension.do' />";
		if(flag == "modify"){
			viewHiddenProgress(true) ;
		    frm.method.value = "forwardModifyDimensionWordAdmin";
			frm.submit();

		} else if(flag == "delete"){	
			if(confirm("<spring:message code='secfw.msg.ask.delete' />")){
				viewHiddenProgress(true) ;
			    frm.method.value = "deleteDimensionWordAmdin";
			    frm.submit();
			}	
		} else if(flag == "list"){	
			viewHiddenProgress(true) ;
		    frm.method.value = "listDimensionWordAdmin";
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
	
	$(document).ready(function(){
		//첨부파일창 load
		//initPage();
	});
	
	function initPage(){
	    frm.target			= "fileList";
	    frm.action 			= "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value  	= "forwardClmsFilePage";
		frm.submit();	
	}	
	
//-->

</script>

</head>
<body onload='showMessage("<c:out value='${returnMessage}'/>");'>

<div id="wrap">
	<!-- container -->
	<div id="container">	
	
		<!-- Location -->
		<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->
<!-- title -->	
<%		
        if(menu_id.equals("20130319155933166_0000000380")||menu_id.equals("20130321153745987_0000000437")){   // 계약 용어집
%>			
    	<div class="title">
		  	<h3><spring:message code="clm.page.title.dimension.wordList" /></h3>
		<!-- //title -->
		</div>
<%		
        }  else if(menu_id.equals("20111106105940035_0000000283")) {  // 계약 유형별 이해
%>
    	<div class="title">
		    <h3><spring:message code="clm.page.title.dimension.infowordList" /></h3>
		<!-- //title -->
		</div>
<%		
        } else {
%>
    	<div class="title">
		    <h3><spring:message code="clm.page.msg.common.announce002" /></h3>
		<!-- //title -->
		</div>
<%		
    }
%>
		<!-- //title -->
	
		<!-- content -->
		<div id="content">
		<div id="content_in">
			
			<!-- **************************** Form Setting **************************** -->
			<form:form name="frm" id="frm" method="post" autocomplete="off">
			
			<!-- search form -->
			<input type="hidden" name="method"   value="" />
			<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />
			<!-- key form-->
			<input type="hidden" name="rule_no"	 value="<c:out value='${command.rule_no}'/>" />
			<input type="hidden" name="menuGubun"  value="<%=menuGubun %>" />
			<!-- 첨부파일정보 -->
			<input type="hidden" name="fileInfos" 	value="" />
			<input type="hidden" name="file_bigclsfcn" 	value="F012" />
			<input type="hidden" name="file_midclsfcn" 	value="" />
			<input type="hidden" name="file_smlclsfcn" 	value="" />
			<input type="hidden" name="ref_key" 	value="" />
			<input type="hidden" name="view_gbn" 	value="download" />
			
			<!-- // **************************** Form Setting **************************** -->
			
			<div class="btnwrap">
				<span class="btn_all_w"><span class="edit"></span><a href="javascript:pageAction('modify');"><spring:message code="secfw.page.button.modify" /></a></span>
				<span class="btn_all_w"><span class="delete"></span><a href="javascript:pageAction('delete');"><spring:message code="secfw.page.button.delete" /></a></span>
				<span class="btn_all_w"><span class="list"></span><a href="javascript:pageAction('list');"><spring:message code="secfw.page.button.list" /></a></span>
			</div>
			
			<!-- view -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
					<col width="100" />
					<col width="720" />
				</colgroup>
				<tbody>
					<tr>
						<th><spring:message code="clm.page.field.regulation.ruleTitle" /></th>
						<td><%=lom.get("rule_title")%></td>
					</tr>
					<tr>
					    <th><spring:message code="clm.page.field.regulation.regNm"/></th>
					    <td><%=StringUtil.convertNamoCharsToHtml((String)lom.get("reg_nm"))%></td>
					</tr>
					<tr class="end">
						<th><spring:message code="clm.page.field.regulation.ruleCont" /></th>
						<td>
							<!-- <textarea class="text_area_full" cols="100%" rows="10" readonly="true"><%//=StringUtil.convertBRToEnter((String)lom.get("rule_cont_en"))%></textarea> -->
<%-- 							<c:out value='${lom.rule_cont_en}' escapeXml="false"/> --%>
							<%=StringUtil.convertEnterToBR((String)lom.get("rule_cont_en"))%>
						</td>
					</tr>
					<!-- 
					<tr class="end">
		            	<th><spring:message code="secfw.page.field.bbs.file" /></th>
		            	<td>
		            	<iframe src="<c:url value='/clm/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="100px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
		            	</td>
		          	</tr>
		          	 -->
				</tbody>
			</table>
			<!-- //view -->
	
			<div class="btnwrap mt20">
				<span class="btn_all_w"><span class="edit"></span><a href="javascript:pageAction('modify');"><spring:message code="secfw.page.button.modify" /></a></span>
				<span class="btn_all_w"><span class="delete"></span><a href="javascript:pageAction('delete');"><spring:message code="secfw.page.button.delete" /></a></span>
				<span class="btn_all_w"><span class="list"></span><a href="javascript:pageAction('list');"><spring:message code="secfw.page.button.list" /></a></span>
			</div>
			</form:form>
		</div>
		</div>
		<!-- //content -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
<!-- footer  -->
<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
<!-- // footer -->
	
</body>
</html>