<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ page import="com.sds.secframework.common.util.PageUtil"%>
<%@ page import="com.sds.secframework.common.util.Token"%>
<%

%>
<html>
<head>
<meta charset="utf-8" />

<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet"/>  
</head>
<body>

<!-- body -->
	       	<form:form name="frm" id="frm" method="post" autocomplete="off">
			<input type="hidden" name="method" id="method" value=""/>
			<input type="hidden" name="menu_id" id="menu_id" value="<c:out value='${command.menu_id}'/>" />
   			<input type="hidden" name="body_mime" id="body_mime" value="" />
			<input type="hidden" name="curPage"         value="<c:out value='${command.curPage}'/>"/>
			<input type="hidden" name="isForeign"       value="<c:out value='${command.isForeign}'/>"/>
			<input type="hidden" name="cnslt_no"        value="<c:out value='${command.cnslt_no}'/>"/>
			<input type="hidden" name="hstry_no"        value="<c:out value='${command.hstry_no}'/>"/>
			<input type="hidden" name="consult_type"    value=""/>
			<input type="hidden" name="top_role"        value="<c:out value='${command.top_role}'/>"/>
			<!-- 첨부파일정보 -->
			<input type="hidden" name="fileInfos"       value="" />
			<input type="hidden" name="fileInfos2"      value="" />
			<input type="hidden" name="fileInfos3"      value="" />
			<input type="hidden" name="file_bigclsfcn"  value="F003" />
			<input type="hidden" name="file_midclsfcn"  value="F00301" />
			<input type="hidden" name="file_smlclsfcn"  value="" />
			<input type="hidden" name="ref_key"         value="<c:out value='${command.cnslt_no}'/>@<c:out value='${command.hstry_no}'/>"/>
			<input type="hidden" name="view_gbn"        value="download" />
			<input type="hidden" name="fileInfoName"    value="" /><!-- 첨부파일 정보가 리턴될 객체명 -->
			<input type="hidden" name="fileFrameName"   value="" /><!-- 첨부파일 화면 iFrame 명 -->
			<input type="hidden" name="extnl_cnsltyn"   value="<c:out value='${lom.extnl_cnsltyn}'/>"/>
			<input type="hidden" name="prgrs_status"    value="<c:out value='${lom.prgrs_status}'/>"/>

			 
	

<div id="contents">
	  <!-- title -->
		    <div class="title_basic">
		      <%-- /* <h3><spring:message code="las.page.field.lawconsult.main_title" /></h3> */ --%>
		      <h4><spring:message code="las.page.field.common.title" /> : <c:out value='${lom.title}' escapeXml='false'/></h4>
		    </div>
		  <!-- //title -->
		  <!-- view -->
		  <table border="0" cellspacing="0" cellpadding="0" class="table-style01" >
		    <colgroup>
		      <col width="15%"/>
		      <col width="35%" />
		      <col width="15%"/>
		      <col width="35%" />
		    </colgroup>
		    <tbody>
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
		              <td colspan="3"><c:out value='${lom.cnsd_opnn}' escapeXml="false"/></td>
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
		              <td colspan="3"><c:out value='${lom.main_matr_cont}' escapeXml="false"/></td>
		            </tr>
		            <tr>
		              <th><spring:message code="las.page.field.lawconsult.lasopnn" /></th>
		              <td colspan="3"><c:out value='${lom.cnsd_opnn}' escapeXml="false"/></td>
		            </tr>
		          </c:otherwise>
		        </c:choose>
		
		      </c:if>
		    </tbody>
		  </table>
		  <!-- //view -->
</div>
</form:form>
</body>
</html>