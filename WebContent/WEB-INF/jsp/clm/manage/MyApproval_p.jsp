<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.Token" %>
<%@ page import="com.sec.las.statistics.dto.StatisticsForm" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>


<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Iterator" %>

<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.GregorianCalendar" %>
<%@ page import="java.math.BigDecimal" %>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>   
<%--
/**
 * 파     일     명 : Myapproval_p.jsp
 * 프로그램명 : 표지인쇄
 * 설               명 : 계약관리담당자 원본접수 시 표지인쇄 기능 
 * 작     성     자 : 김현구
 * 작     성     일 : 2012.8
 */
--%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<title><spring:message code="clm.page.msg.common.contractReview" /></title>
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet"/>  
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script language="javascript">
<!--
	$(document).ready(function(){
		
		var frm = document.frm;
		
		  frm.target          		= "fileContractCopy";		
		  frm.fileFrameName.value	= "fileContractCopy";
	      frm.file_midclsfcn.value	= "F01203";
		  frm.file_smlclsfcn.value 	= "";		
		  frm.fileInfoName.value 	= "fileInfos";
		  frm.multiYn.value 		= "N";
		  frm.ref_key.value			= frm.cntrt_id.value;
	
		  var options = { 
			    	url:"<c:url value='/clms/common/clmsfile.do?method=getFileInfo' />",
			     	type:"post",
			    	dataType:"json",
			    	success:function(returnJson, returnMessage){
			    	   	  
			    	    	$('#fileContractCopy').html('');
			    	    	if(returnJson.fileSz=='') $('#fileContractCopy').append("");
			    	    	else $('#fileContractCopy').append(returnJson.orgFileNm+'('+returnJson.fileSz+')');
			    	    	
			    	    	window.print();
					    		}
			    	};
  		
  	    	
  		$("#frm").ajaxSubmit(options);	
	});	
	
	$(function() {

	});

//-->
</script>

<style>
 body, table, tr, th, td {margin:0;padding:0; font-family:gulim, Malgun Gothic;}
 table {clear:both; border-collapse:collapse; border-spacing:0;}
 table th,table td {font-family:gulim, Malgun Gothic; font-color:#000000 }


 .logoImg {padding:0 0 10px 0}
 
 .print_wrap {border:1px solid #000;padding:20px 27px 27px 27px; overflow:hidden}
 *html .print_wrap {width:100%}
 
 .print_title {font-size:25px;padding:5px 0 0px 0;font-family:Malgun Gothic;font-weight:bold;text-align:center; width:47% }
 
 .print_tbl    {border:2px solid #000000;width:100%;}
 .print_tbl td {padding:4px 10px; border:1px solid #000; font-family:Malgun Gothic; height:24px; font-size:15px;}
 .print_tbl th {padding:4px 10px; border:1px solid #000; font-family:Malgun Gothic; height:24px; background-color:#DEDEDE; font-weight:bold; font-size:15px;}
 *html .print_tbl td {height:35px;}
 *html .print_tbl th {height:35px;}
 
 .print_tbl00    {border:2px solid #000000;width:100%;margin:10px 0 20px 0;}
 .print_tbl00 td {padding:4px 10px; border:1px solid #000; font-family:Malgun Gothic; height:21px; font-size:15px;}
 .print_tbl00 th {padding:4px 10px; border:1px solid #000; font-family:Malgun Gothic; height:21px; background-color:#DEDEDE; font-weight:bold; font-size:13px;}
 *html .print_tbl00 td {height:34px;}
 *html .print_tbl00 th {height:34px;}
 
 
 .print_sign {font-size:17px;padding:0;font-family:Malgun Gothic;text-align:center; width:100%; margin-top:25px; line-height:180%;}
 .tbl_title {width:100%; font-family:Malgun Gothic;margin-bottom:5px; clear:both; margin-top:15px; font-size:15px; font-weight:bold }
 </style>



</head>
<body >
<form:form name="frm" id="frm" autocomplete="off">
<input type="hidden" name="method" value=""/>
<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>"/>
<input type="hidden" name="cntrt_id" id="cntrt_id" value="<c:out value='${lom.cntrt_id}'/>" /> 

<input type="hidden" name="file_bigclsfcn" value="F012" />
<!-- 대분류 -->
<input type="hidden" name="file_midclsfcn" value="" />
<!-- 중분류 -->
<input type="hidden" name="file_smlclsfcn" value="" />
<!-- 소분류 -->
<input type="hidden" name="ref_key" value="" />
<!-- 키값 -->
<input type="hidden" name="view_gbn" value="download" />
<!-- 첨부파일 화면 -->
<input type="hidden" name="fileInfoName" value="" />
<!-- 저장될 파일 정보 -->
<input type="hidden" name="fileFrameName" value="" />
<!-- 소분류 -->
<input type="hidden" name="multiYn" value="" />
<input type="hidden" name="fileInfos" value="" />
	<div class="confidential"></div>
	<div class='logoImg'><img src='<%=IMAGE%>/common/system_logo2.gif'></div>
	<div class='print_wrap'>

	<div style='width:100%; height:; margin-bottom:-20px; overflow:hidden'>
		<div class='print_title' style='float:left; padding-top:20px; text-align:center'><spring:message code="clm.page.msg.manage.contCoverPage" /></div>
		<div style='float:right; width:50%'>
		<table class='print_tbl00'>
			<colgroup>
			<col width="45%">
			<col width="55%">
			</colgroup>
			<tr>
				<th><spring:message code="clm.page.msg.manage.contId" /></th>
				<td><c:out value="${lom.cntrt_no}"/></td>
			</tr>
			<tr>
				<th><spring:message code="clm.page.msg.manage.orgRcvDate" /></th>
				<td><c:out value="${lom.org_acptday}"/></td>
			</tr>
			<tr>
				<th><spring:message code="clm.page.msg.manage.reqPerChrg" /></th>
				<td><c:out value="${lom.reqman_nm}"/>/<c:out value="${lom.cntrt_respman_nm}"/></td>
			</tr>
		</table>
		</div>
	</div>
	
	<div class='tbl_title'><img src='<%=IMAGE%>/icon/ball.gif'> <spring:message code="clm.page.msg.manage.bscInfCont" /></div>
	<table class='print_tbl'>
		<colgroup>
		<col width="20%">
		<col width="80%">
		</colgroup>
		<tr>
			<th><spring:message code="clm.page.msg.manage.reqName" /></th>
			<td><c:out value="${lom.req_title}"/></td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.manage.contName" /></th>
			<td><c:out value="${lom.cntrt_nm}"/></td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.manage.1stReqDate" /></th>
			<td><c:out value="${lom.req_dt}"/></td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.manage.conclDate" /></th>
			<td><c:out value="${lom.cntrt_cnclsnday}"/></td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.manage.reqPerson" /></th>
			<td><c:out value="${lom.reqman_nm}"/>/<c:out value="${lom.reqman_jikgup_nm}"/>/<c:out value="${lom.req_dept_nm}"/></td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.manage.chrgPerson" /></th>
			<td><c:out value="${lom.cntrt_respman_nm}"/>/<c:out value="${lom.cntrt_respman_jikgup_nm}"/>/<c:out value="${lom.cntrt_resp_dept_nm}"/></td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.manage.reviewer" /></th>
			<td><c:out value="${lom.cnsdman_nm}"/></td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.manage.contCopy" /></th>
			<td>
				  <span id="fileContractCopy" name="fileContractCopy" frameborder="0" width="100%" height="50px" leftmargin="0" topmargin="0" scrolling="auto"></span>
			</td>
		</tr>
	</table>
	
	<div class='tbl_title'><img src='<%=IMAGE%>/icon/ball.gif'> <spring:message code="clm.page.msg.manage.contDetailInf" /></div>
	<table class='print_tbl'>
		<colgroup>
		<col width="20%">
		<col width="80%">
		</colgroup>
		<tr>
			<th><spring:message code="clm.page.msg.manage.contType" /></th>
			<td><c:out value="${lom.biz_clsfcn_nm}"/>/<c:out value="${lom.depth_clsfcn_nm}"/>/<c:out value="${lom.cnclsnpurps_bigclsfcn_nm}"/>/<c:out value="${lom.cnclsnpurps_midclsfcn_nm}"/></td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.common.otherParty" /></th>
			<td><c:out value="${lom.cntrt_oppnt_nm}"/></td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.field.customer.registerNo" /></th>
			<td><c:out value="${lom.cntrt_oppnt_RPRSNTMAN}"/></td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.field.customer.contractRequired" /></th>
			<td><c:out value="${lom.cntrt_oppnt_type_nm}"/></td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.manage.contPeriod" /></th>
			<td><c:out value="${lom.cntrtperiod_startday}"/> - <c:out value="${lom.cntrtperiod_endday}"/></td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.manage.contAmt" /></th>
			<td><c:out value="${lom.cntrt_amt}"/></td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.manage.curr" /></th>
			<td><c:out value="${lom.crrncy_unit}"/></td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.manage.sendRcvDiv" /></th>
			<td><c:out value="${lom.payment_gbn_nm}"/></td>
		</tr>
	</table>
	
	
	<div class='tbl_title'><img src='<%=IMAGE%>/icon/ball.gif'> <spring:message code="clm.page.msg.manage.etcMain" /></div>
	<table class='print_tbl'>
		<colgroup>
		<col width="20%">
		<col width="80%">
		</colgroup>
		<tr>
			<th><spring:message code="clm.page.msg.manage.autoExpYn" /></th>
			<td><c:out value="${lom.auto_rnew_yn}"/></td>
		</tr>
		<!-- 연계시스템 없음. 
		<tr>
			<th><spring:message code="clm.page.msg.common.connSys" /></th>
			<td><c:out value="${lom.if_sys_cd}"/></td>
		</tr>
		 -->
	</table>
</div>
</form:form>
</body>
</html>