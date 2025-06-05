<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
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
 * 파  일  명 : RemitCert_l.jsp
 * 프로그램명 : 송금증 목록 화면
 * 설      명 :  로펌별 송금증 목록을 조회한다.
 * 작  성  자 :  박병주
 * 작  성  일 : 2011.10
 */
--%>
<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet" />

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>

<script language="javascript">
<!--
	/**
	* 초기화면 로딩
	*/
	$(document).ready(function(){
		
		var frm = document.frm;

	}); 	
	//초기화면 로딩 끝

	
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
		frm.method.value = "listLawfirmRemitCert";
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
	

	
//-->
</script>

</head>
<body>

<form:form name="frm" id="frm" method="post" autocomplete="off">
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />
<input type="hidden" name="forward_from"  value="<c:out value='${command.forward_from}'/>" />
<input type="hidden" name="forward_url"   value="" />

<!-- search form -->
<input type="hidden" name="srch_lawfirm_nm"   value="<c:out value='${command.srch_lawfirm_nm}'/>" />  
<input type="hidden" name="srch_start_dt"   value="<c:out value='${command.srch_start_dt}'/>" />
<input type="hidden" name="srch_end_dt"   value="<c:out value='${command.srch_end_dt}'/>" />
<input type="hidden" name="srch_order_type"   value="<c:out value='${command.srch_mainftre_estmt}'/>" />

<!-- key form-->
<input type="hidden" name="lawfirm_id" id="lawfirm_id" value="<c:out value='${lom.lawfirm_id}'/>" />
<input type="hidden" name="lawfirm_nm" id="lawfirm_nm" value="<c:out value='${lom.lawfirm_nm}'/>" />
<input type="hidden" name="remitcert_no" id="remitcert_no" value="" />

<div id="wrap">	
	<!-- container -->
<div id="container">
	<!-- Location -->
	<div class="location"><IMG SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
	<!-- //Location -->	
	<!-- title -->
	<div class="title">
		<h3><spring:message code="las.page.field.lawservice.remitcert"/></h3>
	</div>
	<!-- //title -->
	<!-- content -->
	<div id="content">
		<!-- view -->
		<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
			<colgroup>
				<col width="15%" />
				<col width="35%" />
				<col width="15%" />
				<col width="35%" />
			</colgroup>
			<tbody>
				<tr>
					<th><spring:message code="las.page.field.lawservice.lawfirm" /></th>
					<td colspan="3">
						<c:out value='${lom.lawfirm_nm}'/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.fstcont" /></th>
					<td>
						<c:out value='${lom.fst_cntrtday}'/>
					</td>
					<th><spring:message code="las.page.field.lawservice.fstcontevent" /></th>
					<td>
						<c:out value='${lom.fst_event_nm}'/>
					</td>
				</tr>
				<tr>	
					<th><spring:message code="las.page.field.lawservice.contact" /></th>
					<td>
						<c:out value='${lom.rprsnt_tel}'/>
					</td>
					<th><spring:message code="las.page.field.lawservice.fax" /></th>
					<td>
						<c:out value='${lom.rprsnt_fax}'/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.email" /></th>
					<td colspan="3">
						<c:out value='${lom.rprsnt_email}'/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.addr" /></th>
					<td colspan="3">
						<c:out value='${lom.addr}'/>
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
						
						</a></nobr></td>
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