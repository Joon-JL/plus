<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ page import="com.sds.secframework.common.util.PageUtil"%>
<%@ page import="com.sds.secframework.common.util.Token"%>
 
<%
	response.setDateHeader("Expires",0);
	response.setHeader("Pragma","no-cache");

	if(request.getProtocol().equals("HTTP/1.1")){
		response.setHeader("Cache-Control","no-cache");
	}

	String langCd = (String)session.getAttribute("secfw.session.language_flag"); // 언어코드(ko|en)
	String sysCd  = (String)session.getAttribute("secfw.session.sys_cd");        // 시스템코드(LAS)
	String compCd = (String)session.getAttribute("secfw.session.comp_cd");       // 회사코드

	// 세션이 생성안된 상태에서 바로 이 페이지를 호출 시 에러가 발생하므로 그럴 경우 처리
	if (langCd == null) {
		langCd = "en";
	}

	if (sysCd == null) {
		sysCd = "las";
		//String f_c = (String)request.getAttribute("f");
		//if(f_c != null && f_c.equals("c")) sysCd = "clm";
		//else if(f_c != null && f_c.equals("l")) sysCd = "las";
	}

	String IMAGE = "/images/"+ sysCd.toLowerCase() + "/" + langCd; // 이미지 경로설정
	String CSS 	 = "/style/"+ sysCd.toLowerCase() + "/" + langCd;  // CSS 경로 설정

	request.setAttribute("langCd",langCd);

	if (langCd.equals("ko")) {
		request.setAttribute("langCd2","H");
	} else {
		request.setAttribute("langCd2","F");
	}
%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>

<title><spring:message code="las.main.title" /></title>
<%-- <link href="<%=CSS%>/popup.css"  type="text/css" rel="stylesheet" /> --%>
<link href="<%=CSS%>/las.css"    type="text/css" rel="stylesheet" />

<style>
	.pop_content .title h3 {float:left; color:#00366c;font-family:Nanum Gothic, Malgun Gothic, Dotum;line-height:20px; font-size:16px; background:url(/images/clm/ko/icon/bu_square_blue_l.gif) no-repeat 2px 2px; padding:0 0 11px 22px;}
	h1, h2, h3, h4, h5, dl, dt, dd, ul, li, ol, th, td, p, blockquote, form, fieldset, legend, div,body { -webkit-print-color-adjust:exact; }
	td{word-wrap:break-word;}	
</style>


</head>

<body class="pop">   
<!-- body -->
	<div class="pop_area">
    	<div class="pop_content">
	
			<!-- title -->
			<div class="t_titBtn">
			    <br>
			    <br>
		    	<div class="title">
		      		<h3><spring:message code="clm.page.msg.share.legalAdvice" /></h3>
		    	</div>
		  	</div>
			<!-- //title -->
			
			<!-- view -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01" style="width:100%;">
				<colgroup>
			      	<col width="18%"/>
			      	<col width="32%" />
			      	<col width="18%" />
			      	<col width="32%"/>
		    	</colgroup>
		    	
		    	<tbody>
		    		<tr>
		    			<th><spring:message code="las.page.field.common.title" /></th>
		    			<td colspan="3"><c:out value='${lom.title}' escapeXml='false'/></td>
		    		</tr>
		    		<tr>
				        <th><spring:message code="las.page.field.lawconsult.reqman_nm" /></th>
				        <td><c:out value='${lom.reg_nm}'/>/<c:out value='${lom.regman_jikgup_nm}'/></td>
				        <th><spring:message code="las.page.field.lawconsult.department" /></th>
				        <td><c:out value='${lom.reg_dept_nm}'/></td>
		      		</tr>
		      		<tr>
				        <th><spring:message code="las.page.field.lawconsult.respman_nm" /></th>
				        <td colspan="3"><c:out value='${lom.respman_nm}'/></td>
		      		</tr>
		      		<tr>
				        <th><spring:message code="las.page.field.lawconsult.reg_dt" /></th>
				        <td><c:out value='${lom.reg_dt}'/></td>
				        <th><spring:message code="las.page.field.lawconsult.prgrs_status" /></th>
				        <td><c:out value='${lom.prgrs_status_name}'/></td>
			      	</tr>
			      	<tr>
		        		<th><spring:message code="las.page.field.lawconsult.consulttype" /></th>
		        		<td colspan="3"><c:out value='${lom.consult_type_name}'/></td>
		      		</tr>
		      		<c:if test="${command.top_role == 'etc'}">
		        		<c:choose>
		          			<c:when test="${lom.prgrs_status == 'S02' || lom.prgrs_status == 'S04'}">
		            			<tr>
		              				<th><spring:message code="clm.page.msg.common.content" /></th>
		              				<td colspan="3"><c:out value='${lom.cont}' escapeXml="false"/></td>
		            			</tr>
		            			<tr>
		              				<th><spring:message code="las.page.field.lawconsult.attachfile" /></th>
		              				<td colspan="3"><c:out value='${lom.file_name}' escapeXml="false"/></td>
		            			</tr>
		          			</c:when>
		          			<c:when test="${lom.prgrs_status == 'S05'}">
		            			<tr>
					              	<th><spring:message code="las.page.field.lawconsult.holdcont" /></th>
					              	<td colspan="3"><c:out value='${lom.hold_cause}' escapeXml="false"/></td>
		            			</tr>
		          			</c:when>
		          			<c:when test="${lom.prgrs_status == 'S07'}">
		            			<tr>
		              				<th><spring:message code="las.page.field.lawconsult.rejctcont" /></th>
		              				<td colspan="3"><c:out value='${lom.rejct_cause}' escapeXml="false"/></td>
		            			</tr>
		          			</c:when>
		          			<c:when test="${lom.prgrs_status == 'S03'}">
		            			<tr>
		              				<th><spring:message code="las.page.field.lawconsult.lasopnn" /></th>
		              				<td colspan="3"><br><c:out value='${lom.cnsd_opnn}' escapeXml="false"/><br></td>
		            			</tr>
		            			<tr>
		              				<th><spring:message code="las.page.field.lawconsult.las_attachfile" /></th>
		              				<td colspan="3"><c:out value='${lom.file_name}' escapeXml="false"/></td>
		            			</tr>
		          			</c:when>
		        		</c:choose>
		      		</c:if>
		      		<c:if test="${command.top_role != 'etc'}">
		        		<c:choose>
		          			<c:when test="${lom.prgrs_status == 'S02' || lom.prgrs_status == 'S04'}">
		            			<tr>
		              				<th><spring:message code="clm.page.msg.common.content" /></th>
		              				<td colspan="3"><c:out value='${lom.cont}' escapeXml="false"/></td>
		            			</tr>
		            			<tr>
		              				<th><spring:message code="las.page.field.lawconsult.attachfile" /></th>
		              				<td colspan="3"><c:out value='${lom.file_name}' escapeXml="false"/></td>
		            			</tr>
		          			</c:when>
		          			<c:when test="${lom.prgrs_status == 'S05'}">
		            			<tr>
		              				<th><spring:message code="las.page.field.lawconsult.holdcont" /></th>
		              				<td colspan="3"><c:out value='${lom.hold_cause}' escapeXml="false"/></td>
		            			</tr>
		          			</c:when>
		          			<c:when test="${lom.prgrs_status == 'S07' || lom.prgrs_status == 'S11'}">
		            			<tr>
		              				<th><spring:message code="las.page.field.lawconsult.rejctcont" /></th>
		              				<td colspan="3"><c:out value='${lom.rejct_cause}' escapeXml="false"/></td>
		            			</tr>
		          			</c:when>
		          			<c:otherwise>
				            	<tr>
				              		<th><spring:message code="las.page.field.lawconsult.main_matr_cont" /></th>
				              		<td colspan="3"><br><c:out value='${lom.main_matr_cont}' escapeXml="false"/><br></td>
				            	</tr>
				            	<tr>
						          	<th><spring:message code="las.page.field.lawconsult.lasopnn" /></th>
						            <td colspan="3"><br><c:out value='${lom.cnsd_opnn}' escapeXml="false"/><br></td>
				            	</tr>
				            	<tr>
				              		<th><spring:message code="las.page.field.lawconsult.las_attachfile" /></th>
				              		<td colspan="3"><c:out value='${lom.file_name}' escapeXml="false"/></td>
				            	</tr>
		          			</c:otherwise>
						</c:choose>
					</c:if>
				</tbody>
			</table>
		<!-- //view -->
		</div>
	</div>
</body>
</html>