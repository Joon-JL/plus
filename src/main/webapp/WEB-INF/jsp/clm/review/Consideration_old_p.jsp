<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="com.sec.clm.review.dto.ConsultationForm" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<%--
/**
 * 파  일  명 : Consideration_old_d.jsp
 * 프로그램명 : 의뢰내역 - 검토의뢰 - 계약검토[일반의뢰]
 * 설      명 : (구)법무시스템에서 이관된 검토정보에 대해 상세조회하는 출력 페이지 
 * 작  성  자 : 최준영
 * 작  성  일 : 2011.11.07
 */
--%>

<%
	ArrayList listDc = (ArrayList) request.getAttribute("listDc");
	ArrayList listTs = (ArrayList) request.getAttribute("listTs");
	ArrayList listRc = (ArrayList) request.getAttribute("listRc");
	ArrayList listCa = (ArrayList)request.getAttribute("listCa");

	ConsultationForm command = (ConsultationForm) request.getAttribute("command");
	ListOrderedMap lomRq = (ListOrderedMap) request.getAttribute("lomRq"); 
	ListOrderedMap lomMn = (ListOrderedMap) request.getAttribute("lomMn");
	ListOrderedMap lomVc = (ListOrderedMap) request.getAttribute("lomVc");
	
	ListOrderedMap tempLom = new ListOrderedMap();
	
	ArrayList listContractAttach 		 = new ArrayList();		//의뢰계약서
	
	ArrayList resultAttachArrList = (ArrayList)request.getAttribute("resultAttachArrList");
	
	if(resultAttachArrList != null && resultAttachArrList.size() > 0) {
		for(int j=0; j < resultAttachArrList.size(); j++) {
			tempLom = (ListOrderedMap)resultAttachArrList.get(j);
			if("cntrt".equals((String)tempLom.get("filetype"))) {
				listContractAttach.add(resultAttachArrList.get(j));
			} 
		}
	}
	
	//String serverIP="http://165.213.248.184";
	// !@# 서버URL일괄변경
	String serverIP="http://slas.sec.samsung.net";
%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><spring:message code="clm.main.title" /></title>
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet"/>  

</head>
<body>
<div id="wrap">
	<!-- container -->
	<div id="container">
		<form:form name="frm" id='frm' method="post" autocomplete="off">
		
		<c:forEach var="cntrtMt" items="${listDc}">
			<input type="hidden" name="master_cntrt_ids"  value="<c:out value='${cntrtMt.cntrt_id}' />" /> <!-- 계약_마스터_계약_ID -->	
		</c:forEach>	
		<div align="right" style="height: 20px;">
		    <h5></h5>
		</div>
		<!-- location -->
		<div align="right">
		    <h5><spring:message code="las.page.field.contractManager.contId2"/><c:out value="${lomRq.cntrt_no}" /></h5>
		</div>
		<!-- //location -->
		<!-- title -->
		<div class="title">
		</div>
		<!-- //title -->		
		<!-- content -->	
		<div id="content">
			<div class="t_titBtn">
				<!-- title -->
				<div class="title_basic">
					<h4><spring:message code="las.page.field.contractmanager.consideration_d.title_cnsdinfo" /></h4>
				</div>
				<!-- //title -->
				<!--View -->
				<!-- 수정/검토의뢰/최종본작성/목록 -->			
				<!-- button -->
				<!-- //button -->
			</div>
			<!-- toptable -->
			<table cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
				<col width="15%" />
				<col width="9%" />
				<col width="21%" />
				<col width="14%" />
				<col width="17%" />
				<col width="12%" />
				<col width="12%" />
				</colgroup>
				<tr>
					<th><spring:message code="las.page.field.contractManager.requNm"/></th>
				  	<td colspan="6"><c:out value='${lomRq.req_title}' default="" /></td>
				</tr>
				<!-- <tr class="lineAdd">
					<th><spring:message code="las.page.field.contractmanager.consideration_d.reqman_nm" /></th>
					<td colspan="6"><c:out value="${lomRq.reqman_nm}" /> / <c:out value="${lomRq.reqman_jikgup_nm}" /> / <c:out value="${lomRq.req_dept_nm}" /></td>
				</tr>  -->
			</table>
			<div id="tr_down01" class="border-top-no">
				<table cellspacing="0" cellpadding="0" class="table-style01">
					<colgroup>
						<col width="15%" />
						<col width="11%" />
						<col width="6%" />
						<col width="8%" />
						<col width="6%" />
						<col width="8%" />
						<col width="8%" />
						<col width="15%" />
						<col width="8%" />
						<col width="15%" />
					</colgroup>
					<!-- <tr class="lineAdd">
						<th class="borTz02"><spring:message code="las.page.field.contractmanager.consideration_d.cnsd_demnd_cont" /></th>
						<td colspan="9">									 
						<c:out value='${lomRq.cnsd_demnd_cont}' escapeXml="false" /></td>
					</tr> -->
				</table>
				<!-- //top table -->
					
				<!-- 법무 시스템 - 계약검토 -->
				<div id="detailInfoScript">
				</div>
				
				<div id="detailInfoHtmlUp" class="border-top-no">
				</div>
			</div>
 		
			<!-- //법무 시스템 - 계약검토 -->
			
			<!-- title -->
		   <div class="title_basic">
				<h4><spring:message code="las.page.field.contractmanager.consideration_d.cnsd_default_info" /></h4>
			</div>
			<!-- //title -->
			<!-- tab01 -->
			
			<!-- 두줄라인 추가 --><div class="tal_topLine"></div><!-- //두줄라인 추가 -->
			<!-- middle table -->
			<table cellspacing="0" cellpadding="0" class="table-style03">
		   <colgroup>
				<col width="15%" />
				<col width="9%" />
				<col width="21%" />
				<col width="14%" />
				<col width="12%" />
				<col width="12%" />
				<col width="17%" />
			</colgroup>
			<tr>
				<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.cntrt_nm" /></th>
				<td colspan="6"><span class="fL"><c:out value="${lomRq.cntrt_nm}" /></span></td>
			</tr>
<%
			if("C05403".equals((String)lomRq.get("CNTRT_INFO_GBN"))) {		// 사업부 이관 데이터
%>
			<tr>
				<th><spring:message code="clm.page.msg.common.otherParty"/></th>
				<td colspan="2"><c:out value="${lomRq.cntrt_oppnt_nm}" /></td>
				<th><spring:message code="clm.page.field.customer.registerNo"/></th>
				<td><c:out value="${lomRq2.cntrt_oppnt_rprsntman}" /></td>
				<th><spring:message code="clm.page.field.customer.contractRequired" /></th>
				<td><c:out value="${lomRq2.cntrt_oppnt_type_nm}" /></td>
			</tr>
			<tr class="lineAdd">
					<th><spring:message code="las.page.field.contractManager.contChargeEmp"/></th>
					<td colspan="6"><c:out value="${lomRq2.reqman_nm}" /> / <c:out value="${lomRq2.reqman_jikgup_nm}" /> / <c:out value="${lomRq2.req_dept_nm}" /></td>
				</tr>
			<tr class="slide-target02 slide-area">			
				<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.cntrt_type" /></th>				
				<td colspan="4">
					<c:out value="${lomRq.biz_clsfcn_nm}" /> / 
					<c:out value="${lomRq.depth_clsfcn_nm}" /> / 
					<c:out value="${lomRq.cnclsnpurps_bigclsfcn_nm}" /> / 
					<c:out value="${lomRq.cnclsnpurps_midclsfcn_nm}" />
				</td>
				<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.cntrt_trgt_nm" /></th>
				<td>
					<c:out value="${lomRq.cntrt_trgt_nm}" />	
				</td>
			</tr>
			<tr class="slide-target02 slide-area">
				<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.cntrtperiod" /></th>
				<td colspan="2">
<%
					if("00000000".equals((String)lomRq.get("cntrtperiod_startday")) && "99999999".equals((String)lomRq.get("cntrtperiod_endday"))) {
						out.println("미입력");
					} else {
						if(!"00000000".equals((String)lomRq.get("cntrtperiod_startday"))) {
							out.println(DateUtil.formatDate((String)lomRq.get("cntrtperiod_startday"),"-"));
						}
						out.println(" ~ ");
						if(!"99999999".equals((String)lomRq.get("cntrtperiod_endday"))) {
							out.println(DateUtil.formatDate((String)lomRq.get("cntrtperiod_endday"),"-"));
						}
					}
%>
				</td>
				<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.payment_gbn_nm" /></th>
				<td colspan="3"><c:out value="${lomRq.payment_gbn_nm}" /></td>
			</tr>
			<tr class="slide-target02 slide-area">
				<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.cntrt_amt" /></th>
				<td colspan="2">
					<%if(lomRq.get("cntrt_amt_chg") != null) {%>
						<%=lomRq.get("cntrt_amt_chg") %>
					<%} %>
				</td>
				<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.currency" /></th>
				<td colspan="3"><c:out value="${lomRq.crrncy_unit}" /></td>
			</tr>
<!--
			<tr class="slide-target02 slide-area">
				 <th><spring:message code="clm.page.field.contract.consultation.detail.autorenew" /></th>
				<td colspan="2">
					<c:choose>
					<c:when test="${lomRq.auto_rnew_yn=='Y'}">Yes</c:when>
					<c:otherwise>No</c:otherwise>
					</c:choose>
				</td> 
				<th><spring:message code="clm.page.field.contract.conclusion.detail.conclusionyn" /></th>
				<td colspan="3">
					<c:choose>
					<c:when test="${lomRq.cntrt_cnclsn_yn=='Y'}">Yes</c:when>
					<c:otherwise>No</c:otherwise>
					</c:choose>
				</td>
			</tr>
-->
<!-- 
			<tr class="slide-target02 slide-area">
				<th><spring:message code="clm.page.field.contract.consultation.detail.conclusionday" /></th>
				<td colspan="2">
<%
				//	if(!"00000000".equals((String)lomRq.get("cnclsn_plndday"))) {
					//	out.println(DateUtil.formatDate((String)lomRq.get("cnclsn_plndday"),"-"));
			//		}
%>
				</td>
-->				
				
			</tr></table>
		</div>
		<div>
		<!-- title -->
		   <div class="title_basic">
				<h4><spring:message code="las.page.field.contractManager.signInfo"/></h4>
			</div>
			<!-- //title -->
			<!-- tab01 -->
			
			<!-- 두줄라인 추가 --><div class="tal_topLine"></div><!-- //두줄라인 추가 -->
			<!-- middle table -->
		
		<table cellspacing="0" cellpadding="0" class="table-style03">
		<colgroup>
				<col width="15%" />
				<col width="9%" />
				<col width="21%" />
				<col width="14%" />
				<col width="12%" />
				<col width="12%" />
			</colgroup>

			<tr class="slide-target02 slide-area">
				<th><spring:message code="las.page.field.contractManager.stempType"/></th>
				<td colspan="7"><c:out value='${lomRq2.seal_mthd_nm}'/></td>
		    </tr>
		    <tr>
				<th><spring:message code="las.page.field.contractManager.theSignature"/></th>
				<td colspan="7"><c:out value='${lomRq2.signman_nm}'/>/<c:out value='${lomRq2.signman_jikgup_nm}'/>/<c:out value='${lomRq2.sign_dept_nm}'/></td>
			</tr>
			<tr class="slide-target02 slide-area">
				<th><spring:message code="las.page.field.contractManager.dupl"/></th>
				<td colspan="7">
<%	
   if(listContractAttach.size() > 0 && listContractAttach != null){
	   tempLom = (ListOrderedMap)listContractAttach.get(0); 
%>
       <a href=<c:out value='${attachinfo}'/><%=tempLom.get("file_id")%>><%=tempLom.get("org_file_nm") %></a>
<%	   
   } else {
%>
       <spring:message code="las.page.field.contractManager.noForm"/>
<%	   
   }
%>  	  
	
				</td>
			</tr> 

			<tr class="slide-target02 slide-area">
				<th><spring:message code="las.page.field.contractManager.orgnYn"/></th>
				<td colspan="2">
					<c:choose>
					<c:when test="${lomRq.org_strg_pos=='Y'}">Yes</c:when>
					<c:otherwise>No</c:otherwise>
					</c:choose>
				</td>
				<th><spring:message code="las.page.field.contractManager.signDate"/></th>
				<td colspan="4">
<%
					if(!"00000000".equals((String)lomRq.get("cntrt_cnclsnday"))) {
						out.println(DateUtil.formatDate((String)lomRq.get("cntrt_cnclsnday"),"-"));
					}
%>
				</td>
			</tr>

<%
			} 
%>


		</table>
		<!--//middle table -->
		
	 	<!--bottom table 검토의견 -->
		<div class="tal_subBox" id="tr_down03" style="display:none;">		
				<!-- tab -->
				<div class="tab_box">
					 <ul class="tab_basic">
						<li id="tab_contents_sub_css3" class="on"><a><spring:message code="las.page.field.contractmanager.consideration_inner_d.cnsd_opnn" /></a></li>
					 </ul>
				</div>

				<!-- //tab -->
		
				<!-- blngt_orgnz  국내법인 /해외 법인/IP  -->
				<!-- top_role 그룹장/담당자 -->
				<table id="tab_contents_sub_conts3" cellspacing="0" cellpadding="0" border="0" class="table-style01" style="display:;">
					<colgroup>
						<col width="14%" />
						<col width="10%" />
						<col width="30%" />
						<col width="14%" />
						<col width="32%" />
					</colgroup>
						<c:if test="${lomRq.cnsdman_info != null && lomRq.cnsdman_info != ''}">
						<tr>
							<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.cnsdman_info" /></th><!-- 검토 담당자 -->
							<td colspan="4"><c:out value='${lomRq.cnsdman_info}' escapeXml="false" /></td>
						</tr>
						</c:if>
					<% if( "RA01".equals(command.getTop_role())){ // 그룹장인경우 VieWmode %>
						<% if(lomRq.get("mn_cnsd_dept").equals(command.getBlngt_orgnz())){%>
						<tr>
							<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.main_matr_cont" /></th><!-- TN_CLM_CONTRACT_CNSD	주요_사안_내용 MAIN_MATR_CONT    부서 검토 테이블로 바꿀꺼임 -->
							<td colspan="4"><c:out value='${lomMn.main_matr_cont}' escapeXml="false"/></td>						
						</tr>
						<% }%>	
						<tr>
							<th><spring:message code="las.page.field.contractManager.picMemo"/><br><spring:message code="las.page.field.contractManager.personRef"/></th>
							<td colspan="4"><c:out value='${lomRq.cnsd_memo}'  escapeXml="false" /></td>
						</tr>
						<tr>
							<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.total_cnsd_opnn" /></th>
							<td colspan="4">
								<c:out value='${lomRq.cnsd_opnn}' escapeXml="false" />
							</td>
						</tr>
					<% }%>
					
					<% if( "RA02".equals(command.getTop_role()) && lomRq.get("mn_cnsd_dept").equals(command.getBlngt_orgnz()) ){ //로그인자가 정검토부서 이고  담당자인경우 InserTmode %>
						<tr>
							<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.main_matr_cont" /></th><!-- TN_CLM_CONTRACT_CNSD	주요_사안_내용 MAIN_MATR_CONT    부서 검토 테이블로 바꿀꺼임 -->
							<td colspan="4"><c:out value='${lomMn.main_matr_cont}'  escapeXml="false" /></td>						
						</tr>
						<tr>
							<th><spring:message code="las.page.field.contractManager.picMemo"/><br><spring:message code="las.page.field.contractManager.personRef"/></th>
							<td colspan="4"><c:out value='${lomRq.cnsd_memo}'  escapeXml="false" /></td>
						</tr>
						<tr>
							<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.total_cnsd_opnn" /></th>
							<td colspan="4">
								<c:out value='${lomRq.cnsd_opnn}'  escapeXml="false" />
							</td>
						</tr>
					<% }%>				
				</table>
		</div>
			<!-- //tab_content -->
			
			<!-- 이력정보 -->
			<div id="contractHis-list"></div>
			<!-- //이력정보 -->
 
 		</div>
		
		<div id="detailInfoHtmlDown">
		</div>
	</form:form>
	</div>
	<!-- //container -->
</div>

</body>
</html>
 
 