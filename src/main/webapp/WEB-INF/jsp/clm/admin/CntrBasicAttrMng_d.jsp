<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>
<%@ page import="com.sds.secframework.common.util.Token"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%--
/**
 * 파  일  명 : CntrBasicAttrMng_d.jsp
 * 프로그램명 : 계약속성관리 - 상세
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	//첨부파일 창 높이
	String layoutHeight = "100";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><spring:message code="clm.main.title" /></title>
<link href="<%=CSS %>/clm.css" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>

<script language="javascript">
	function goList() {
		var frm = document.frm;
		
		viewHiddenProgress(true) ;
		frm.method.value = "listCntrBasicAttrMng";
		frm.action = "<c:url value='/clm/admin/cntrBasicAttrMng.do' />";
		frm.target = "_self";
		frm.submit();
	}
	
	function showMessage(msg){
		if( msg != "" && msg != null && msg.length > 1 ) {
			alert(msg);
		}
	}

</script>
</head>
<body>

<form:form name="frm" id="frm" autocomplete="off">
<input type="hidden" name="method"   	 		value="">
<input type="hidden" name="menu_id"  	 		value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage"  	 		value="<c:out value='${command.curPage}'/>" />

<input type="hidden" name="srch_info_itm"  		value="<c:out value='${command.srch_info_itm}'/>" />

<div id="wrap">
	<!-- container -->
	<div id="container">
	
		<!-- location -->
		<div class="location"><IMG SRC="<%=IMAGE %>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //location -->
	
		<!-- title -->
		<div class="title">
		  <h3 class="h3_mz"><spring:message code="clm.page.title.cntrBasicAttrMng.detailTitle" /></h3>
		</div>
		<!-- //title -->
		
		<!-- content -->
		<div id="content">
		<!-- button -->
	  	<div class="btn_wrap_t03">
	  		<!-- 
			<span class="btn_all_w"><span class="edit"></span><a href="#"><spring:message code="clm.page.msg.common.modify" /></a></span>
			<span class="btn_all_w"><span class="delete"></span><a href="#"><spring:message code="clm.page.msg.common.delete" /></a></span>
			 -->
			<span class="btn_all_w"><span class="list"></span><a href="javascript:goList();"><spring:message code="secfw.page.button.list" /></a></span>
	  	</div>
		<!-- //button -->
		 
		<!--table -->
	  	<table cellspacing="0" cellpadding="0" class="table-style01">
			<colgroup>
				<col width="110">
				<col/>
				<col width="80">
				<col/>
				<col width="80">
				<col/>
			</colgroup>
			<tr class="lineAdd">
			  	<th><spring:message code="clm.page.field.cntrBasicAttrMng.infoItemNobr" /></th> <!-- 정보항목 -->
			  	<td><c:out value='${lom.info_itm}'/></td>
			  	<th><spring:message code="clm.page.field.cntrBasicAttrMng.regDt" /></th> <!-- 등록일 -->
			  	<td><c:out value='${lom.reg_dt}'/></td>
			  	<th><spring:message code="clm.page.field.cntrBasicAttrMng.regNm" /></th> <!-- 등록자 -->
			  	<td><c:out value='${lom.reg_nm}'/><c:out value='${lom.reg_dept_nm}'/></td>
			</tr>
			<tr class="lineAdd">
			  <th><spring:message code="clm.page.field.cntrBasicAttrMng.infoRegist" /><br /><!-- 정보입력 -->			    
			    <spring:message code="clm.page.field.cntrBasicAttrMng.createStage" /><br /></th> <!-- 생성단계 -->
			  <td colspan="5">
			  	<table cellspacing="0" cellpadding="0" class="table-style02">
			  	<colgroup>
					<col width="100">
					<col width="90">
					<col width="60">
                    <col width="100">
					<col width="70">
					<col width="70">
                    <col width="55">
					<col width="45">
					<col width="45">
				</colgroup>
			    <tr>
			      <th colspan="3"><spring:message code="clm.page.field.cntrBasicAttrMng.cntrLawExam" /></th> <!-- 계약서 법무검토 -->
			      <th colspan="3"><spring:message code="clm.page.field.cntrBasicAttrMng.cntrConclusion" /></th> <!-- 계약체결 -->
			      <th rowspan="2"><spring:message code="clm.page.field.cntrBasicAttrMng.conclusionRegist" /></th> <!-- 체결본 등록 -->
			      <th rowspan="2"><spring:message code="clm.page.field.cntrBasicAttrMng.execMng" /></th> <!-- 이행관리 -->
			      <th rowspan="2"><spring:message code="clm.page.field.cntrBasicAttrMng.closeMng" /></th> <!-- 종료관리 -->
		        </tr>
			    <tr>
			      <th class="ana_col"><spring:message code="clm.page.field.cntrBasicAttrMng.cntrDraft" /></th><!-- 계약서초안작성 -->
			      <th class="ana_col"><spring:message code="clm.page.field.cntrBasicAttrMng.cntrExamRequest" /></th><!-- 계약검토의뢰 -->
			      <th class="ana_col"><spring:message code="clm.page.field.cntrBasicAttrMng.cntrExam" /></th><!-- 계약검토 -->
			      <th class="ana_col"><spring:message code="clm.page.field.cntrBasicAttrMng.conclusionExam" /></th><!-- 체결예정본검토 -->
			      <th class="ana_col"><spring:message code="clm.page.field.cntrBasicAttrMng.conclusionConsult" /></th><!-- 쳬결품의 -->
			      <th class="ana_col"><spring:message code="clm.page.field.cntrBasicAttrMng.cntrConclusion" /></th><!-- 계약체결 -->
		        </tr>
			    <tr>
			      <td><c:choose><c:when test="${lom.crtn_depth=='C04001'}"><input name="" type="radio" disabled="disabled" class="radio" value="" checked="checked" /></c:when><c:otherwise><input name="" type="radio" disabled="disabled" class="radio" value="" /></c:otherwise></c:choose></td>
			      <td><c:choose><c:when test="${lom.crtn_depth=='C04002'}"><input name="" type="radio" disabled="disabled" class="radio" value="" checked="checked" /></c:when><c:otherwise><input name="" type="radio" disabled="disabled" class="radio" value="" /></c:otherwise></c:choose></td>
			      <td><c:choose><c:when test="${lom.crtn_depth=='C04003'}"><input name="" type="radio" disabled="disabled" class="radio" value="" checked="checked" /></c:when><c:otherwise><input name="" type="radio" disabled="disabled" class="radio" value="" /></c:otherwise></c:choose></td>
			      <td><c:choose><c:when test="${lom.crtn_depth=='C04004'}"><input name="" type="radio" disabled="disabled" class="radio" value="" checked="checked" /></c:when><c:otherwise><input name="" type="radio" disabled="disabled" class="radio" value="" /></c:otherwise></c:choose></td>
			      <td><c:choose><c:when test="${lom.crtn_depth=='C04005'}"><input name="" type="radio" disabled="disabled" class="radio" value="" checked="checked" /></c:when><c:otherwise><input name="" type="radio" disabled="disabled" class="radio" value="" /></c:otherwise></c:choose></td>
			      <td><c:choose><c:when test="${lom.crtn_depth=='C04006'}"><input name="" type="radio" disabled="disabled" class="radio" value="" checked="checked" /></c:when><c:otherwise><input name="" type="radio" disabled="disabled" class="radio" value="" /></c:otherwise></c:choose></td>
			      <td><c:choose><c:when test="${lom.crtn_depth=='C04007'}"><input name="" type="radio" disabled="disabled" class="radio" value="" checked="checked" /></c:when><c:otherwise><input name="" type="radio" disabled="disabled" class="radio" value="" /></c:otherwise></c:choose></td>
			      <td><c:choose><c:when test="${lom.crtn_depth=='C04008'}"><input name="" type="radio" disabled="disabled" class="radio" value="" checked="checked" /></c:when><c:otherwise><input name="" type="radio" disabled="disabled" class="radio" value="" /></c:otherwise></c:choose></td>
			      <td><c:choose><c:when test="${lom.crtn_depth=='C04009'}"><input name="" type="radio" disabled="disabled" class="radio" value="" checked="checked" /></c:when><c:otherwise><input name="" type="radio" disabled="disabled" class="radio" value="" /></c:otherwise></c:choose></td>
		        </tr>
		      </table></td>
	    	</tr>
			<tr class="lineAdd">
			  <th><spring:message code="clm.page.field.cntrBasicAttrMng.definition" /></th><!-- 정의 -->
			  <td colspan="5"><c:out value='${lom.attr_defn}'/></td>
	    	</tr>
			<tr class="lineAdd">
			  <th><spring:message code="clm.page.field.cntrBasicAttrMng.infoMakeMethod" /></th><!-- 정보생성방식 -->
			  <td colspan="5"><c:out value='${lom.crtn_mthd}'/></td>
	    	</tr>
			<tr class="lineAdd">
			  <th>List of Value</th>
			  <td colspan="5"><c:out value='${lom.attr_val}'/></td>
	    	</tr>
			<tr class="lineAdd">
			  <th><spring:message code="clm.page.field.cntrBasicAttrMng.example" />1</th><!-- 예시1 -->
			  <td colspan="5"><c:out value='${lom.val_exmpl1}'/></td>
	    	</tr>
			<tr class="lineAdd">
			  <th><spring:message code="clm.page.field.cntrBasicAttrMng.example" />2</th><!-- 예시2 -->
			  <td colspan="5"><c:out value='${lom.val_exmpl2}'/></td>
	    	</tr>
			<tr class="lineAdd">
			  <th><spring:message code="clm.page.field.cntrBasicAttrMng.infoMakeMain" /></th><!-- 정보생성주체 -->
			  <td colspan="5"><c:out value='${lom.crtn_mnagt}'/></td>
	    	</tr>
			<tr class="lineAdd">
			  <th><spring:message code="clm.page.field.cntrBasicAttrMng.infoType" /></th><!-- 정보유형 -->
			  <td colspan="5"><c:out value='${lom.info_type}'/></td>
	    	</tr>
			<tr class="lineAdd">
				<th><spring:message code="clm.page.field.cntrBasicAttrMng.infoMakeSystem" /></th><!-- 정보생성시스템 -->
				<td colspan="5"><c:out value='${lom.crtn_sys}'/></td>
			</tr>
			<tr class="end"">
				<th><span><spring:message code="clm.page.field.cntrBasicAttrMng.listOrder" /></span></th><!-- 정렬순서 -->
				<td colspan="5"><c:out value='${lom.prnt_srt}'/></td>
			</tr>
		</table>
		<!-- //table -->
		</div>
		<!-- //content -->
	
		<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js"></script>
		<!-- // footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>	
</form:form>
</body>
</html>