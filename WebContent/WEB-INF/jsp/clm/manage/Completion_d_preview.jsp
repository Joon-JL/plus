<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ include file= "/WEB-INF/jsp/common/common.jsp" %>
<%-- 
/**
 * 파  일  명 : Execution_l.jsp
 * 프로그램명 : 종료정보 조회 목록
 * 설      명 : 
 * 작  성  자 : 신승완
 * 작  성  일 : 2011.09
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	
	//===================================================================
	//검토이력, 승인이력 관련 시작
	//===================================================================
	ArrayList review	= (ArrayList)request.getAttribute("review");
	ArrayList approve 	= (ArrayList)request.getAttribute("approve");
	ArrayList info 		= (ArrayList)request.getAttribute("info");
	//===================================================================
	//검토이력, 승인이력 관련 끝
	//===================================================================
		
	ArrayList approvalHisList	= (ArrayList)request.getAttribute("approvalHisList");
	ListOrderedMap lomDetail = (ListOrderedMap)request.getAttribute("lomDetail");
	// !@# 서버URL일괄변경
	String serverIP="http://slas.sec.samsung.net";
%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<link href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet"/>
<link href="<%=serverIP %>/style/main.css"   type="text/css" rel="stylesheet"/>
<link href="<%=serverIP %>/style/popup.css"   type="text/css" rel="stylesheet"/>
<link href="<%=serverIP %>/style/common.css"   type="text/css" rel="stylesheet"/>
<link href="<%=serverIP %>/style/clm.css"   type="text/css" rel="stylesheet"/>	
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script>
	$(function(){
		// 2014-04-17 Kevin Added.
	    // GERP readonly iframe load. 
		var frm = document.frm;
	    frm.target          = "iframeGERP";
	    frm.action          = "<c:url value='/clm/manage/consideration.do' />";
	    frm.gerpPageType.value = "R";		// detail readonly
	    frm.method.value    = "forwardGERPDetail";
		frm.submit();
	});
</script>
<style>
					.title_basic h4, 
					*html .title_basic h4 {padding:8px 10px 2px 18px; background-position:3px 11px}
					.mt20 {margin-top:20px;}
					.table-style01 th {text-align:center}
					.tableWrap {width:100%; clear:both;}
				    .tableone {margin-right:17px; _width:100%; }
				    *:first-child+html .tableone {margin-right:0px; }
				    .tabletwo {overflow-y:scroll; overflow-x:hidden; table-layout:fixed;}
				   
					.table-style_sub03 {width:99%; _border-left:1px solid #fff; margin:0; zoom:1; table-layout:fixed;}
					.table-style_sub03 th {line-height:150%; text-align:left; padding:3px 10px; background:#f6f6f6; border:1px solid #dddddd; color:#647996; font-weight:normal;border-right:0;}
					.table-style_sub03 td {line-height:150%;text-align:left; padding:3px 10px; border-left:1px solid #dddddd; border-bottom:1px solid #dddddd; border-top:1px solid #dddddd; color:#7d848a;}
					.table-style_sub03 .blank {border:1px solid #fff}
					.table-style_sub03 .blank_side {border-left:1px solid #fff;}
					.table-style_sub03 .topline {border-top:1px solid #7697c2;}
				</style>
</head>
<body>

<!-- 

**************************** Form Setting **************************** 
-->
<form:form name="frm" id='frm' method="post" autocomplete="off">
<!-- 2014-04-15 Kevin added. GERP 관련 페이지 형태 구분.(R/I) -->
<input type="hidden" name="gerpPageType" id="gerpPageType" />
<!-- //
**************************** Form Setting **************************** 
-->
<div id="wrap">
<!-- container -->
<div id="container">
	<!-- //title -->
	<!-- content -->
	<div id="content">
		
				

				<!-- button -->
				<!-- //button -->		
<table cellspacing=0 cellpadding=0 width="100%" border="0" nowrap>
<tbody>
	<tr>
		<td>
			<table style="cellspacing:0; cellpadding:0; width:'100%'; border:0;">
			<tbody>
				<tr>
					<td style="background-color: #ffffff" height=10></td>
				</tr>
			</tbody>
			</table>
		</td>
	</tr>
	<tr align="center">
		<td style="padding-right: 0px; padding-left: 0px; padding-bottom: 0px; padding-top: 0px; background-color: #d8e7ef">
			<table cellspacing=0 cellpadding=0 width="100%" border=0>
			<tbody>
				<tr>
					<td style="background-color: #b4b4b4" height=1></td>
				</tr>
			</tbody>
			</table>
			<table cellspacing=0 cellpadding=0 width="100%" bgcolor=#ffffff border=0>
			<tbody>
				<tr>
					<td style="padding-right: 5px; padding-left: 10px; font-weight: bold; font-size: 9pt; width: 100px; color: #7a5e4b; height: 28px; background-color: #f4f1e7" nowrap><spring:message code="clm.page.msg.common.title" /></td>
					<td style="padding-left: 5px; font-weight: bold; font-size: 9pt; color: #666666; padding-top: 5px; background-color: #f4f1e7;text-align: left !important" width="100%">
						<table cellspacing=0 cellpadding=0 border=0>
						<tbody>
							<tr>
								<td style="padding-left: 10px; font-size: 9pt; color: #666666; padding-top: 2px; height: 28px; background-color: #f4f1e7"><c:out value="${lomDetail.title}"/></td>
							</tr>
						</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td style="background-color: #dbdbdb" colspan=2 height=1></td>
				</tr>
				<tr>
					<td style="padding-right: 5px; padding-left: 10px; font-weight: bold; font-size: 9pt; width: 100px; color: #7a5e4b; padding-top: 1px; height: 28px; background-color: #fcfbf8"><spring:message code="clm.page.msg.manage.writer" /></td>
					<td style="padding-left: 5px; font-weight: bold; font-size: 9pt; color: #666666; padding-top: 5px; background-color: #fcfbf8;text-align: left !important" width="100%">
						<table cellspacing=0 cellpadding=0 border=0>
						<tbody>
							<tr>
								<td style="padding-left: 10px; font-size: 9pt; color: #666666; padding-top: 2px; height: 28px; background-color: #fcfbf8"><c:out value="${lomDetail.g_user}"/>
								</td>
							</tr>
						</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td style="background-color: #dbdbdb" colspan=2 height=1></td>
				</tr>
				<tr>
					<td style="padding-right: 5px; padding-left: 10px; font-weight: bold; font-size: 9pt; width: 100px; color: #7a5e4b; padding-top: 1px; height: 28px; background-color: #fcfbf8"><spring:message code="clm.page.msg.manage.stdTime" /></td>
					<td style="padding-left: 5px; font-weight: bold; font-size: 9pt; color: #666666; padding-top: 5px; background-color: #fcfbf8;text-align: left !important" width="100%">
						<table  cellspacing=0 cellpadding=0 border=0>
						<tbody>
							<tr>
								<td style="padding-left: 10px; font-size: 9pt; color: #666666; padding-top: 2px; height: 28px; background-color: #fcfbf8"><c:out value="${lomDetail.time_zone}"/></td>
							</tr>
						</tbody>
						</table>
					</td>
				</tr>
			</tbody>
			</table>
			<table style="cellspacing:0; cellpadding:0; width:'100%'; border:0;">
			<tbody>
				<tr>
					<td style="background-color: #b4b4b4" height=1></td>
				</tr>
			</tbody>
			</table>
			<table style="table-layout: fixed" cellspacing=0 cellpadding=0 width="100%" border=0>
			<tbody>
				<tr align="center">
					<td style="font-weight: bold; font-size: 9pt; color: #7a5e4b; height: 28px; background-color: #e4e2d6; text-align: center" nowrap width="10%" ><spring:message code="clm.page.msg.common.sequence" /></td>
					<td style="font-weight: normal; font-size: 9pt; color: #333333; height: 28px; background-color: #e4e2d6; text-align: center" nowrap width="10%" ><spring:message code="clm.page.msg.common.div" /></td>
					<td style="font-weight: normal; font-size: 9pt; color: #333333; height: 28px; background-color: #e4e2d6; text-align: center" nowrap width="25%" ><spring:message code="clm.page.msg.manage.name" /></td>
					<td style="font-weight: normal; font-size: 9pt; color: #333333; height: 28px; background-color: #e4e2d6; text-align: center" nowrap width="25%" ><spring:message code="clm.page.msg.manage.apprDateTime" /></td>
					<td style="font-weight: normal; font-size: 9pt; color: #333333; height: 28px; background-color: #e4e2d6; text-align: center" nowrap width="25%" ><spring:message code="clm.page.msg.manage.deptName" /></td>
				</tr>
				<tr>
					<td bgcolor=#d9d9d9 colspan=5 height=1 ></td>
				</tr>
			</tbody>
			</table>
			<table cellspacing=0 cellpadding=0 width="100%" border=0>
			<tbody>
<%
			for(int i=0; i < approvalHisList.size(); i++) {  
				ListOrderedMap s1Lom = (ListOrderedMap)approvalHisList.get(i);

%>
				<tr>
					<td style="padding-right: 5px; padding-left: 10px; font-weight: bold; font-size: 9pt; width: 100px; color: #7a5e4b; padding-top: 1px; border-bottom: #cccccc 1px solid; height: 28px; background-color: #fcfbf8" align="center" width="10%" rowspan=2 ><%=s1Lom.get("sequence")%></td>
					<td style="background-color: #dbdbdb" width=1 ></td>
					<td style="padding-left: 10px; font-size: 9pt; color: #333333; padding-top: 2px; border-bottom: #cccccc 1px solid; height: 28px; background-color: #fcfbf8;text-align: left !important" width="10%" ><%=s1Lom.get("activity")%></td>
					<td style="background-color: #dbdbdb" width=1 ></td>
					<td style="padding-left: 10px; font-size: 9pt; color: #333333; padding-top: 2px; border-bottom: #cccccc 1px solid; height: 28px; background-color: #fcfbf8;text-align: left !important" width="25%" ><%=s1Lom.get("user_nm")%></td>
					<td style="background-color: #dbdbdb" width=1 ></td>
					<td style="padding-left: 10px; font-size: 9pt; color: #755232; padding-top: 2px; border-bottom: #cccccc 1px solid; height: 28px; background-color: #fcfbf8;text-align: left !important" width="25%" ><%=s1Lom.get("approved")%></td>
					<td style="background-color: #dbdbdb" width=1 ></td>
					<td style="padding-left: 10px; font-size: 9pt; color: #333333; padding-top: 2px; border-bottom: #cccccc 1px solid; height: 28px; background-color: #fcfbf8;text-align: left !important" width="25%" ><%=s1Lom.get("dept_name")%></td>
				</tr>
				<tr>
					<td style="background-color: #dbdbdb" width=1 ></td>
					<td style="padding-left: 10px; font-size: 9pt; color: #666666; word-break: break-all; padding-top: 2px; border-bottom: #cccccc 1px solid; height: 28px; background-color: #ffffff; word-wrap: break-word;text-align: left !important" colspan=7 ><%=s1Lom.get("opinion") %></td>
				</tr>
<%
			}
%>
			</tbody>
			</table>
		</td>
	</tr>
</tbody>
</table>				
				<!-- 계약정보 -->
				<div class="title_basic" style='width:100%; margin:10px 0'>
					<h4><spring:message code="clm.page.msg.manage.contInfo" /></h4>
				</div>
				<div class='title_basic3'><spring:message code="clm.page.msg.manage.bscInf" /></div>
				<table class="table-style01">
					<colgroup>
						<col width="12%" />
						<col width="22%" />
						<col width="12%" />
						<col width="21%" />
						<col width="12%" />
						<col width="21%" />
					</colgroup>
					<tr>
						<th><spring:message code="clm.page.msg.manage.reqName" /></th>
					    <td colspan="5"><c:out value="${contractReqLom.req_title}" /></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.manage.contName" /></th>
						<td colspan='3'><c:out value="${contractReqLom.cntrt_nm}" /></td>
						<th><spring:message code="clm.page.msg.manage.contId" /></th>
						<td><c:out value="${contractReqLom.cntrt_no}" /></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.manage.reqPerson" /></th>
						<td><c:out value="${contractReqLom.reqman_nm}" /></td>
						<th><spring:message code="clm.page.msg.manage.chrgPerson" /></th>
						<td><c:out value="${contractReqLom.cntrt_respman_nm}" /></td>
						<th><spring:message code="clm.page.msg.manage.reviewer" /></th>
						<td><c:out value="${contractReqLom.cnsdman_nm}" /></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.common.otherParty" /></th>
						<td><a href="javascript:customerPop2('<c:out value="${contractMstLom.cntrt_oppnt_cd}" />', '<c:out value="${contractMstLom.cntrt_oppnt_cd}" />');"><c:out value="${contractMstLom.cntrt_oppnt_nm}" /></a></td>
						<th><spring:message code="clm.page.field.customer.registerNo" /></th>
						<td><c:out value="${contractMstLom.cntrt_oppnt_ceo}" /></td>
						<th>
							<!-- 2014-04-17 Kevin commented. -->
							<%-- <spring:message code="clm.page.field.customer.contractRequired" /> --%>
						</th>
						<td>
							<!-- 2014-04-17 Kevin commented. -->
							<%-- <c:out value="${contractMstLom.cntrt_oppnt_type_nm}" /> --%>
						</td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.manage.contItm" /></th>
						<td><c:out value="${contractMstLom.cntrt_trgt_nm}" /></td>
						<th><spring:message code="clm.page.msg.manage.contItmDtl" /></th>
						<td colspan="3"><c:out value="${contractMstLom.cntrt_trgt_det}" /></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.manage.contType" /></th>
						<td colspan="5"><c:out value='${contractMstLom.biz_clsfcn_nm}'/>/<c:out value='${contractMstLom.depth_clsfcn_nm}'/>/<c:out value='${contractMstLom.cnclsnpurps_bigclsfcn_nm}'/>/<c:out value='${contractMstLom.cnclsnpurps_midclsfcn_nm}'/></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.manage.top30Cus" /></th>
						<td colspan="2"><c:out value="${contractMstLom.top_30_cus_nm}" /></td>
						<th><spring:message code="clm.page.msg.manage.relatedPrd" /></th>
						<td><c:out value="${contractMstLom.related_products_nm}" escapeXml="false"/></td>
						<th><spring:message code="clm.page.msg.manage.srcContDraft" /></th>
						<td><c:out value="${contractMstLom.cont_draft_nm}" /></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.manage.contPeriod" /></th>
						<td><c:out value="${contractMstLom.cntrtperiod_startday}" /> ~ <c:out value="${contractMstLom.cntrtperiod_endday}" /></td>
						<th><spring:message code="clm.page.field.contract.consultation.detail.autorenew" /></th>
						<td>
						<c:if test="${contractMstLom.auto_rnew_yn=='Y'}">Yes</c:if>
						<c:if test="${contractMstLom.auto_rnew_yn=='N'}">No</c:if>
						</td>
						<th><spring:message code="clm.page.msg.manage.sendRcvDiv" /></th>
						<td><c:out value="${contractMstLom.payment_gbn_nm}" /></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.manage.contAmt" /></th>
						<td colspan='3'>
						<input type="text" class="text" style="border:0px solid #fff;" value="<c:out value="${contractMstLom.cntrt_amt}" />" />
						<input type='checkbox' class='checkbox' <c:if test="${!empty contractMstLom.cntrt_untprc_expl}">checked</c:if> disabled> <spring:message code="clm.page.msg.manage.conclSingleAmt" />
						 </td>
						<th><spring:message code="clm.page.msg.manage.curr" /></th>
						<td><c:out value="${contractMstLom.crrncy_unit}" /></td>
					</tr>
					<c:if test="${!empty contractMstLom.cntrt_untprc_expl}">
					<tr>
						<th><spring:message code="clm.page.msg.manage.contUnitPriceSum" /></th>
						<td colspan='5'><c:out value="${contractMstLom.cntrt_untprc_expl}"/></td>
					</tr>
					</c:if>
					<tr>
						<th><spring:message code="clm.page.msg.manage.bg" /></th>
						<td colspan="5"><c:out value="${contractMstLom.pshdbkgrnd_purps}" escapeXml="false" />
						</td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.manage.etcMain" /></th>
						<td colspan="5">
						<c:out value="${contractMstLom.etc_main_cont}" />
						<c:if test="${!empty contractMstLom.if_sys_cd}"> [<c:out value="${contractMstLom.if_sys_cd}"  />]</c:if>
						</td>
					</tr>
				</table>	
				
				<!-- 2014-04-17 Kevin Added. GERP Information -->
            	<iframe id="iframeGERP" name="iframeGERP" src="/clm/blank.do" style="border:0; width:100%; height:115px; margin:10px 0 10px 0;" scrolling="no"></iframe>
				
				<div class='title_basic3'><spring:message code="clm.page.msg.manage.preApprInf" /></div>
				<table class="table-style01">
					<colgroup>
						<col width="12%" />
						<col width="22%" />
						<col width="12%" />
						<col width="21%" />
						<col width="12%" />
						<col width="21%" />
					</colgroup>
					<tr>
						<th><spring:message code="clm.page.msg.manage.apprType" /></th>
						<td colspan='3'><c:out value="${contractMstLom.bfhdcstn_apbt_mthd_nm}" /></td>
						<th><spring:message code="clm.page.msg.manage.apprDate" /></th>
						<fmt:parseDate value="${contractMstLom.bfhdcstn_apbtday}" var="bfhdcstn_apbtday" pattern="yyyymmdd"/>
						<td><fmt:formatDate value="${bfhdcstn_apbtday}" pattern="yyyy-mm-dd"/></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.manage.proposer" /></th>
						<td colspan='3'><c:out value="${contractMstLom.bfhdcstn_mtnman_nm}" />/<c:out value="${contractMstLom.bfhdcstn_mtnman_jikgup_nm}" />/<c:out value="${contractMstLom.bfhdcstn_mtn_dept_nm}" /> </td>
						<th><spring:message code="clm.page.msg.manage.apprPer" /></th>
						<td><c:out value="${contractMstLom.bfhdcstn_apbtman_nm}" />/<c:out value="${contractMstLom.bfhdcstn_apbtman_jikgup_nm}" />/<c:out value="${contractMstLom.bfhdcstn_apbt_dept_nm}" /></td>
					</tr>
				</table>
					
				<div class='title_basic3'><spring:message code="clm.page.msg.manage.relContInf" /> </div>
				<table class="table-style01" id="contrack-relationView" >
					<colgroup>
						<col width="12%" />
						<col width="50%" />
						<col width="10%" />
						<col/>	
					</colgroup>
					<tr>
						<th><spring:message code="clm.page.msg.manage.relation" /></th>
						<th><spring:message code="clm.page.msg.manage.relCont" /></th>
						<th><spring:message code="clm.page.msg.manage.define" /></th>
						<th><spring:message code="clm.page.msg.manage.relDetail" /></th>
					</tr>
						<c:choose>
						<c:when test ="${!empty contractRelationLom}">
							<c:forEach var="list" items="${contractRelationLom}" varStatus="i">
								<tr>
									<td><c:out value='${list.rel_type_nm}'/></td>
									<td><c:out value='${list.relation_cntrt_nm}'/></td>
									<td><c:out value='${list.rel_defn}'/></td>
									<td><c:out value='${list.expl}'/></td>
								</tr>		
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr><td colspan="4" align="center">No Data Found</td></tr>
						</c:otherwise>
						</c:choose>	
				</table>
				<!-- // 계약정보 -->
				
				<!-- 체결정보 -->
				<div class="title_basic" style='width:100%; margin:10px 0'>
					<h4><spring:message code="clm.page.msg.manage.conclInf" /></h4>
				</div>
				
				<div class='title_basic3'><spring:message code="clm.page.msg.manage.bscInf" /></div>
				<table class="table-style01">
					<colgroup>
						<col width="12%" />
						<col width="22%" />
						<col width="12%" />
						<col width="21%" />
						<col width="12%" />
						<col width="21%" />
					</colgroup>
					<tr>
						<th><spring:message code="clm.page.msg.manage.conclConf" /></th>
						<td>
						<c:choose>
						<c:when test="${contractMstLom.cntrt_cnclsn_yn=='Y'}">Yes</c:when>
						<c:otherwise>No</c:otherwise>
						</c:choose>	
						</td>
						<th><spring:message code="clm.page.msg.manage.conclResDate" /></th>
						<td><c:out value='${contractMstLom.cnclsn_plndday}'/></td>
						<th><spring:message code="clm.page.msg.manage.contConclDt" /></th>
						<td><c:out value='${contractMstLom.cntrt_cnclsnday}'/></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.manage.signDiv" /></th>
						<td><c:out value='${contractMstLom.seal_mthd_nm}'/></td>
						<c:choose>
						<c:when test="${contractMstLom.seal_mthd eq 'C02102'}">
						<th><spring:message code="clm.page.msg.manage.signChrgMan" /></th>
						<td><c:out value='${contractMstLom.sign_plndman_nm}'/>/<c:out value='${contractMstLom.sign_plndman_jikgup_nm}'/>/<c:out value='${contractMstLom.sign_plnd_dept_nm}'/></td>
						</c:when>
						<c:otherwise>
						<th><spring:message code="clm.page.msg.manage.signManager" /></th>
						<td><c:out value='${contractMstLom.seal_ffmtman_nm}'/>/<c:out value='${contractMstLom.seal_ffmtman_jikgup_nm}'/>/<c:out value='${contractMstLom.seal_ffmt_dept_nm}'/></td>
						</c:otherwise>
						</c:choose>	
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.manage.chrgCont" /></th>
						<td><c:out value='${contractMstLom.cntrt_respman_nm}'/>/<c:out value='${contractMstLom.cntrt_respman_jikgup_nm}'/>/<c:out value='${contractMstLom.cntrt_resp_dept_nm}'/></td>
					<%--	신성우 주석처리 2014-04-01
						<th><spring:message code="clm.page.msg.manage.preAnnDate" /></th>
						<td colspan='3'><c:out value='${contractMstLom.exprt_notiday}'/></td> --%>
					</tr>
				</table>
				
				<div class='title_basic3'><spring:message code="clm.page.msg.manage.copyVerInf" /></div>
				<table class="table-style01">
					<colgroup>
						<col width="12%" />
						<col width="22%" />
						<col width="12%" />
						<col width="54%" />
					</colgroup>
					<tr>
						<th><spring:message code="clm.page.msg.manage.copyVerRegPer" /></th>
						<td><c:out value='${contractMstLom.cpy_regman_nm}'/>/<c:out value='${contractMstLom.cpy_regman_jikgup_nm}'/>/<c:out value='${contractMstLom.cpy_reg_dept_nm}'/></td>
						<th><spring:message code="clm.page.msg.manage.copyVerRegDate" /></th>
						<td><c:out value='${contractMstLom.cpy_regday}'/></td>
					</tr>
				</table>
				
				<div class='title_basic3'><spring:message code="clm.page.msg.manage.orgInf" /></div>
				<table class="table-style01">
					<colgroup>
						<col width="12%" />
						<col width="22%" />
						<col width="12%" />
						<col width="54%" />
					</colgroup>
					<tr>
						<th><spring:message code="clm.page.msg.manage.orgSendPer" /></th>
						<td><c:out value='${contractMstLom.org_hdovman_nm}'/>/<c:out value='${contractMstLom.org_hdovman_jikgup_nm}'/>/<c:out value='${contractMstLom.org_hdov_dept_nm}'/></td>
						<th><spring:message code="clm.page.msg.manage.orgRcvDate" /></th>
						<td><c:out value='${contractMstLom.org_acptday}'/></td>
						
					</tr>
					<tr>
						<th><spring:message code="las.page.field.contractManager.groupChief" /></th>
						<td><c:out value='${contractMstLom.org_tkovman_nm}'/>/<c:out value='${contractMstLom.org_tkovman_jikgup_nm}'/>/<c:out value='${contractMstLom.org_tkov_dept_nm}'/></td>
						<th><spring:message code="clm.page.msg.manage.orgPlace" /></th>
						<td><c:out value='${contractMstLom.org_strg_pos}'/></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.common.memo" /></th>
						<td colspan='3'><c:out value='${contractMstLom.org_acpt_dlay_cause}'/></td>
					</tr>
				</table>
				<!-- // 계약상세내역 -->
				<!-- 종료정보 -->
				<div class="title_basic" style='width:100%; margin:10px 0 0 0'> 
					<h4><spring:message code="clm.page.msg.manage.endInfo" /></h4>
				</div>
	<table class="table-style01">
		<colgroup>
			<col width="12%" />
			<col width="22%" />
			<col width="12%" />
			<col width="54%" />
		</colgroup>
		<tr>
			<th ><spring:message code="clm.page.msg.manage.reqPrsn" /></th>
			<td>
				<c:out value='${completionLom.cntrt_chge_demndman_nm}' />
					<c:choose><c:when test="${!empty completionLom.cntrt_chge_demndman_jikgup_nm}">/</c:when></c:choose>
				<c:out value='${completionLom.cntrt_chge_demndman_jikgup_nm}' />
					<c:choose><c:when test="${!empty completionLom.cntrt_chge_demnd_dept_nm}">/</c:when></c:choose>
				<c:out value='${completionLom.cntrt_chge_demnd_dept_nm}' /></td>
			<th ><spring:message code="clm.page.msg.manage.apprPer" /></th>
			<td>
				<c:choose>
					<c:when test="${!empty approveManLom.user_name}">
						<c:out value='${approveManLom.user_name }' />
							<c:choose><c:when test="${!empty approveManLom.dept_name}">/</c:when></c:choose>
						<c:out value='${approveManLom.dept_name }' />
							<c:choose><c:when test="${!empty approveManLom.duty}">/</c:when></c:choose>
						<c:out value='${approveManLom.duty }' />
					</c:when>
					<c:otherwise>
						<c:out value='${command.approvalman_nm }' />
							<c:choose><c:when test="${!empty command.approvalman_dept_nm}">/</c:when></c:choose>
						<c:out value='${command.approvalman_dept_nm }' />
							<c:choose><c:when test="${!empty command.approvalman_jikgup_nm}">/</c:when></c:choose>
						<c:out value='${command.approvalman_jikgup_nm }' />
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<c:if test="${!empty completionLom.cntrt_chge_demndday}">
		<tr>
			<th><spring:message code="clm.page.msg.manage.reqDt" /></th>
			<td colspan="3"><c:out value='${completionLom.cntrt_chge_demndday}' /></td>
		</tr>
		</c:if>		
		<tr>
			<th ><spring:message code="clm.page.msg.manage.chgStat" /></th>
			<td colspan="3"><c:out value='${completionLom.cntrt_status_nm}' /></td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.manage.chgStatRsn" /></th>
			<td colspan="3"><c:out value='${completionLom.cntrt_chge_demnd_cause}' /></td>
		</tr>

	<c:if test="${! empty completionLom.cntrt_chge_confman_nm}">
		<tr>
			<th><spring:message code="clm.page.msg.manage.confirmPer" /></th>  
			<td><c:out value='${completionLom.cntrt_chge_confman_nm}'/>/<c:out value='${completionLom.cntrt_chge_confman_jikgup_nm}'/>/<c:out value='${completionLom.cntrt_chge_conf_dept_nm}'/></td>
			<th><spring:message code="clm.page.msg.manage.confirmYn" /></th>
			<td colspan="3">
				<c:choose>
					<c:when test="${completionLom.cntrt_chge_conf_yn=='Y'}"><spring:message code="clm.page.msg.common.confirm" /></c:when>
					<c:otherwise><spring:message code="clm.page.msg.common.noConfirm" />
</c:otherwise>					
				</c:choose>
			</td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.manage.confirmOpin" /></th>
			<td colspan="5"><c:out value='${completionLom.cntrt_chge_conf_cont}'/></td>
		</tr>
	</c:if>
	</table>			
				<!-- // 종료정보 -->
				
				
				<!-- 검토이력 -->
				<div class="title_basic" style='width:100%; margin:10px 0 0 0;'>
					<h4><spring:message code="clm.page.msg.manage.revHistory" /> </h4>
				</div>
				
				<div id="contract-reviewHisView">
					<table class="table-style01" >
						<colgroup>
							<col width="67%" />
							<col width="11%" />
							<col width="22%" />
						</colgroup>
						<tr>
							<th><spring:message code="clm.page.msg.manage.reqBrkd" /></th>
							<th><spring:message code="clm.page.msg.manage.date" /></th>
							<th><spring:message code="clm.page.msg.manage.name" /></th>	
						</tr>
<%
if(review.size() > 0){
	for(int idx=0;idx < review.size();idx++){

		ListOrderedMap lom = (ListOrderedMap)review.get(idx);		
%>		
		<tr>
			<td class="tL">
				<%=lom.get("cr_flan_nm") %>
				<%=StringUtil.convertHtmlTochars((String)lom.get("title"))%>
			</td>
			<td><%=lom.get("reg_dt") %></td>
			<td><%=lom.get("man_nm") %>/<%=lom.get("jikgup_nm") %>/<%=lom.get("dept_nm") %></td>
		</tr>

<%
	}
}else{
%>
		<tr id="notFoundList" onMouseOut="this.className='';" onMouseOver="this.className='selected'">
			<td colspan="4" align="center" class="tC"><spring:message code="clm.msg.succ.noResult" /></td>
		</tr>
<%
}
%>
						
					</table>
				</div>
				<!-- // 검토이력 -->
				
				<!-- 승인이력 -->
				<div class="title_basic" style='width:100%; margin:10px 0 0 0;'>
					<h4><spring:message code="clm.page.msg.manage.apprHist" /> </h4>
				</div>
				
				<div id="contract-approveHisView" style="display:;">
					<table class="table-style01" >
						<colgroup>
							<col width="67%" />
							<col width="8%" />
							<col width="8%" />
							<col width="17%" />
						</colgroup>
						<tr>
							<th><spring:message code="clm.page.msg.manage.itmName" /></th>
							<th><spring:message code="clm.page.msg.manage.status" /></th>
							<th><spring:message code="clm.page.msg.manage.date" /></th>
							<th><spring:message code="clm.page.msg.manage.apprPer" /></th>
						</tr>
<%
if(info.size() > 0){
	
	for(int idx=0;idx < info.size();idx++){

		ListOrderedMap lom = (ListOrderedMap)info.get(idx);
		
%>		
		<tr>
			<td class="tL">
				<spring:message code="clm.page.msg.manage.preApprInf" />
			</td>
			<td><%=lom.get("bfhdcstn_apbt_mthd_nm") %></td>
			<td><%=lom.get("bfhdcstn_apbtday") %></td>
			<td class="txtover"><%=lom.get("bfhdcstn_apbtman_nm") %> <%=lom.get("bfhdcstn_apbtman_jikgup_nm") %>/<%=lom.get("bfhdcstn_apbt_dept_nm") %></td>
		</tr>
<%
	} 
}else {
%>
		<tr id="notFoundList" onMouseOut="this.className='';" onMouseOver="this.className='selected'">
			<td colspan="4" align="center" class="tC"><spring:message code="clm.msg.succ.noResult" /></td>
		</tr>
<%
}
%>	 
<%
if(null != approve && approve.size() > 0){
	/* 현재Mis_id에 속한 결재경로갯수 만큼 구분하여  루핑처리    
		예) -----------------------------------
			 품의명     상태, 일자, 승인자
			-----------------------------------
			-----------------------------------
			의뢰품의명     완결, 2011-11-01, 김길동
			-----------------------------------
		           결재자   [기안] 홍길동/대리/사업부
		           [결재] 김길동/대리/사업부
		    -----------------------------------
		          첨부파일
		    -----------------------------------
		          
		    -----------------------------------
			체결품의명     반려, 2011-11-01, 김길동
			-----------------------------------
		           결재자   [기안] 홍길동/대리/사업부
		           [결재] 김길동/대리/사업부
	        -----------------------------------
		          첨부파일
		    -----------------------------------
		           
            -----------------------------------
			체결품의명     완결, 2011-11-01, 김길동
			-----------------------------------
		           결재자   [기안] 홍길동/대리/사업부
		           [결재] 김길동/대리/사업부       
	        -----------------------------------
		          첨부파일
		    -----------------------------------
			           
		    -----------------------------------
			종료품의명      완결, 2011-11-01, 김길동
			-----------------------------------
		           결재자   [기안] 홍길동/대리/사업부
		           [결재] 김길동/대리/사업부
		    -----------------------------------
		          첨부파일
		    -----------------------------------
	    
	*/
	
	
	boolean bFirstLine	= false; //현재 Mis_id의 첫번째 라인여부
	String sMemMis_id  	= "" ; 	//현재 Mis_id
	int iMemAllMember  	= 0 ; 	//현재 결재건(MIS_ID)에 묶인  기안,합의,승인자의 갯수
	int iMemRow		   	= 0 ;	//현재 iMemAllMember의 Row위치
	String sNextMis_id  = "" ; 	//다음  Mis_id
	int iFileRowIndex	= 0 ;	//파일 index
	int iFile_cnt		= 0; 	//파일갯수  
	String sApprovalName= "";	//승인자명 
	String sStatus		= "";
	for(int idx=0;idx < approve.size();idx++){
		ListOrderedMap lom = (ListOrderedMap)approve.get(idx);
		
		sNextMis_id = (String)lom.get("mis_id");
		
		if(idx == 0){
			sMemMis_id  = (String)lom.get("mis_id");
			bFirstLine	= true;
			iMemAllMember= ((java.lang.Integer)lom.get("cntrt_id_cnt"));
			iMemRow = 1;
		}else{
			//다음Mis_id와  현재Mis_id가 동일하지 않으면 다른 결재권임.
			if(! sNextMis_id.equals(sMemMis_id)){
				sMemMis_id = sNextMis_id;
				bFirstLine	= true;
				iMemAllMember= ((java.lang.Integer)lom.get("cntrt_id_cnt"));
				iMemRow = 0;
				
				iFileRowIndex ++;
			}
			
			iMemRow ++;
		}
		
		if(bFirstLine) {
			bFirstLine = false;
			%>		
			<tr>
				<td class="tL"><%=lom.get("title") %></td>
				<td><%=lom.get("status") %></td>
				<td><%=lom.get("create_date") %></td>
				<td ></td>
			</tr>
<%			} 
		}
	}
%>
					</table>
				</div>
				<!-- // 승인이력 -->
						
				<!-- 자동연장이력 -->
				<c:if test="${completionLom.auto_rnew_yn eq 'Y'}">	
				<div class="title_basic" style='width:100%; margin:10px 0 0 0;'>
					<h4><spring:message code="clm.page.msg.manage.autoExpHstr" /> </h4>
				</div>
				
				<table class="table-style01" id="contrack-autoHistoryView" >
					<colgroup>
						<col width="12%" />
						<col width="15%" />
						<col width="15%" />
						<col width="31%" />
						<col width="17%" />
					</colgroup>
					<tr>
						<th><spring:message code="clm.page.msg.manage.seqNo" /></th>
						<th><spring:message code="clm.page.msg.manage.startDate" /></th>
						<th><spring:message code="clm.page.msg.manage.termDate" /></th>
						<th><spring:message code="clm.page.msg.common.note" /></th>
						<th><spring:message code="clm.page.msg.manage.apprPer" /></th>
					</tr>
						<c:choose>
						<c:when test ="${!empty orgMngHistory}">
							<c:forEach var="list" items="${orgMngHistory}" >
								<tr>
									<td><c:out value='${list.rn}'/><spring:message code="clm.page.msg.manage.seq" /></td>
									<td><c:out value='${list.cntrtperiod_startday}'/></td>
									<td><c:out value='${list.cntrtperiod_endday}'/></td>
									<td><c:out value='${list.mod_cause}'/></td>
									<td>
									<c:if test="${list.gubun eq 'A'}">
									<c:out value='${list.mod_nm}'/>/<c:out value='${list.mod_dept_nm}'/>/<c:out value='${list.mod_jikgup_nm}'/>
									</c:if>
									</td>
								</tr>		
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr><td colspan="5" align="center"><spring:message code="clm.page.msg.manage.announce141" /> </td></tr>
						</c:otherwise>
						</c:choose>	
				</table>
				</c:if>
				<!-- 자동연장이력 -->

				<!-- button -->
				
				<!-- //button -->
		
					
			
			
		</div>
		<!-- //content -->
		
		<!-- footer  -->
		<!-- // footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
</div>

</form:form>
</body>
</html>

