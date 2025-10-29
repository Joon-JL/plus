<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
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

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%--
/**
 * 파  일  명 : LawfirmRemitCert_d.jsp
 * 프로그램명 : 송금증 - 상세
 * 설      명 : 송금증 상세 정보를 조회한다.
 * 작  성  자 : 박병주
 * 작  성  일 : 2011.10
 */
--%>
<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.main.title" /></title>
<LINK href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript">
<!--
	/**
	* 초기화면 로딩
	*/
	$(document).ready(function(){
		
		var frm = document.frm;
						
		//첨부파일창 load
		initPage();
	});
	
	/**
	* 첨부파일창 load
	*/
	function initPage(){

	    frm.target          = "fileList";
	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    = "forwardClmsFilePage";
	    frm.submit();   
	}
	
	/**
	* 송금증 상세화면으로 가기
	*/
	function detailRemitCertView(remitcert_no){
		var frm = document.frm;
		
		viewHiddenProgress(true) ;
		frm.remitcert_no.value		= remitcert_no;
		frm.target = "_self";
		frm.action = "<c:url value='/las/lawservice/lawfirmRemitCert.do' />";
		frm.method.value = "detailLawfirmRemitCert";
		
		frm.submit();		
	}
	
	/**
	* 목록으로 가기
	*/
	function goList() {
		var frm = document.frm;

		frm.action = "<c:url value='/las/lawservice/lawfirmRemitCert.do' />";
		frm.method.value = "listRemitCert";
		viewHiddenProgress(true);
		frm.target = "_self";
		frm.submit();
	}
	
	/**
	* 송금증 입력 화면으로 가기
	*/
	function goSrvInsert(){
		
		var frm = document.frm;
		
		viewHiddenProgress(true);
		frm.method.value = "forwardLawfirmRemitCertInsert";
		frm.action = "<c:url value='/las/lawservice/lawfirmRemitCert.do' />";
		frm.target = "_self";
		frm.submit();

	}
	
	/**
	* 수정처리
	*/
	function modify() {
		var frm = document.frm;
		viewHiddenProgress(true);
		frm.method.value = "forwardLawfirmRemitCertModify";		
		frm.action = "<c:url value='/las/lawservice/lawfirmRemitCert.do' />";
		frm.target = "_self";
		frm.submit();
	}
	
	/**
	* 삭제처리
	*/
	function remove(){
		var frm = document.frm;
		
		if(confirm("<spring:message code='secfw.msg.ask.delete' />")) {
			
			viewHiddenProgress(true);
			frm.method.value = "deleteLawfirmRemitCert";
			frm.action = "<c:url value='/las/lawservice/lawfirmRemitCert.do' />";
			frm.target = "_self";
			frm.submit();
		}
	}
	
	function showMessage(msg){
		if( msg != "" && msg != null && msg.length > 1 ) {
			alert(msg);
		}
	}
	//-->
</script>
</head>
<body onLoad='showMessage("<c:out value='${returnMessage}'/>");'>

<form:form name="frm" id="frm" autocomplete="off">
<input type="hidden" id="method" name="method" value="" />
<input type="hidden" id="menu_id" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" id="curPage" name="curPage" value="<c:out value='${command.curPage}'/>" />
<input type="hidden" name="forward_from"  value="<c:out value='${command.forward_from}'/>" />

<!-- search form -->
<input type="hidden" name="srch_lawfirm_nm"   value="<c:out value='${command.srch_lawfirm_nm}'/>" />  
<input type="hidden" name="srch_start_dt"   value="<c:out value='${command.srch_start_dt}'/>" />
<input type="hidden" name="srch_end_dt"   value="<c:out value='${command.srch_end_dt}'/>" />
<input type="hidden" name="srch_order_type"   value="<c:out value='${command.srch_mainftre_estmt}'/>" />

<!-- key form-->
<input type="hidden" name="lawfirm_id" id="lawfirm_id" value="<c:out value='${command.lawfirm_id}'/>"  />
<input type="hidden" name="remitcert_no" id="remitcert_no" value="<c:out value='${command.remitcert_no}'/>"  />

<!-- 첨부파일정보 -->
<input type="hidden" name="fileInfos" 		value="" />
<input type="hidden" name="file_bigclsfcn" 	value="F005" />
<input type="hidden" name="file_midclsfcn" 	value="F00507" />
<input type="hidden" name="file_smlclsfcn" 	value="" />
<input type="hidden" name="ref_key" 		value="<c:out value='${command.remitcert_no}'/>" />
<input type="hidden" name="view_gbn" 		value="download" />

<!-- //**************************** Form Setting **************************** -->

<div id="wrap">
<!-- container -->
<div id="container">
	<!-- content -->
	<div id="content">
	<!-- location -->
	<div class="location">
		<IMG SRC="<%=IMAGE %>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/>
	</div>
	<!-- //location -->
		<div class="t_titBtn">
				<!-- title -->
				<div class="title" >
					<h3><spring:message code="las.page.field.lawservice.remitcert"/>&nbsp;<spring:message code="las.page.field.lawservice.detail" /></h3>
				</div>
				<!-- //title -->
			<div class="btn_wrap">
				<c:if test="${command.lws_auth_modify}">
					<span class="btn_all_w"><span class="edit"></span><a href="javascript:modify()"><spring:message code="las.page.button.modify" /></a></span>
				</c:if>
				<c:if test="${command.lws_auth_delete}">
					<span class="btn_all_w"><span class="delete"></span><a href="javascript:remove()"><spring:message code="las.page.button.delete" /></a></span>
				</c:if>
			</div>
		</div>
		
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
					<th><spring:message code="las.page.field.lawservice.subtitle"/></th>
					<td colspan="3">
						<c:out value='${lom.remitcert_nm}'/>
					</td>
				</tr>
				<tr>
					<th>LAWFIRM</th>
					<td colspan="3">
						<c:out value='${lom.lawfirm_nm}'/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.remitday"/></th>
					<td colspan="3">
						<c:out value='${lom.remitday}'/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.regnm" /></th>
					<td><c:out value='${lom.reg_nm}'/></td>
					<th><spring:message code="las.page.field.lawservice.acptday" /></th>
					<td><c:out value='${lom.reg_dt}'/></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.remitcont"/></th>
					<td colspan="3"><c:out value='${lom.cont}' /></td>
				</tr>
				<tr class="end">
					<th><spring:message code="las.page.field.lawservice.attachfile" /></th>
					<td colspan="3">
						<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="80px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
					</td>
				</tr>
			</tbody>
		</table>
		<!-- //view -->
		
		<div class="t_titBtn">
			<div class="btn_wrap">
				<span class="btn_all_b"><span class="save"></span><a href="javascript:goSrvInsert()"> <spring:message code="las.page.button.lawservice.input" /></a></span>
			</div>
		</div>		    
	
		<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
		  <colgroup>
		  <col width="7%" />
		  <col width="48%" />
		  <col width="15%" />
		  <col width="15%" />
		  <col width="15%" />		
		  </colgroup>
		  <thead>
			<tr>
			  <th><spring:message code="las.page.field.lawservice.order" /></th>
			  <th><spring:message code="las.page.field.lawservice.subtitle"/></th>
			  <th><spring:message code="las.page.field.lawservice.regnm" /></th>
			  <th><spring:message code="las.page.field.lawservice.regdt"/></th>
			  <th><spring:message code="las.page.field.lawservice.remitday"/></th>
			</tr>
		  </thead>
		  <tbody>
			<c:choose>
			<c:when test="${remit_cert_list_cnt > 0}">
				<c:forEach var="list" items="${remit_cert_list}">
					<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
					    <td  class="tC"><c:out value='${list.rn}'/></td>
						<td  class="tL">
						
						<img src="<%=IMAGE%>/icon/icon_reply.gif" />
						<c:if test="${list.upfile_yn != 'N'}">
							<img src="<%=IMAGE%>/icon/ico_save_w.gif" />
						</c:if>
						<a href="javascript:detailRemitCertView('<c:out value='${list.remitcert_no}'/>')">
						
						<c:if test="${list.remitcert_no == lom.remitcert_no}">
							<strong><c:out value='${list.remitcert_nm}'/></strong>
						</c:if>
						<c:if test="${list.remitcert_no != lom.remitcert_no}">
							<c:out value='${list.remitcert_nm}'/>
						</c:if>
						
						</a></strong></nobr></td>
		            	<td><c:out value='${list.reg_nm}'/></td>
		            	<td><c:out value='${list.reg_dt}'/></td>
		            	<td><c:out value='${list.remitday}'/></td>
					</tr>
				</c:forEach>
		    </c:when>
		    <c:otherwise>
				<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
					<td colspan="7" align="center"><spring:message code="las.msg.succ.noResult" /></td>
				</tr>
		    </c:otherwise>
		</c:choose>
		 </tbody>
		 </table>
		  		
		<div class="t_titBtn">
			<div class="btn_wrap">
				<span class="btn_all_w"><span class="list"></span><a href="javascript:goList()"><spring:message code="las.page.button.lawservice.golist" /></a></span>
			</div>
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