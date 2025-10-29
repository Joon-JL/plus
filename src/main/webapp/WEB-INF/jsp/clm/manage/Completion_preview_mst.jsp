<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.Map" %>

<%-- 
/**
 * 파  일  명 : Execution_mst.jsp
 * 프로그램명 : 계약상세 조회
 * 설      명 : 
 * 작  성  자 : 신승완
 * 작  성  일 : 2011.09
 */
--%>
<%
String serverIP="http://selmsplus.sec.samsung.net";

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
}

String IMAGE = serverIP+"/images/"+ sysCd.toLowerCase() + "/" + langCd; // 이미지 경로설정
String CSS 	 = serverIP+"/style/"+ sysCd.toLowerCase() + "/" + langCd;  // CSS 경로 설정

request.setAttribute("langCd",langCd);

if (langCd.equals("ko")) {
	request.setAttribute("langCd2","H");
} else {
	request.setAttribute("langCd2","F");
}





ArrayList contractRelationLom	= (ArrayList)request.getAttribute("contractRelationLom");
ListOrderedMap tmpLom	= (ListOrderedMap)request.getAttribute("completionLom");
ArrayList review	= (ArrayList)request.getAttribute("review");
ArrayList approve 	= (ArrayList)request.getAttribute("approve");
ArrayList sign 		= (ArrayList)request.getAttribute("sign");
ArrayList info 		= (ArrayList)request.getAttribute("info");
int maxrm 			= (Integer)request.getAttribute("maxrm");

ArrayList resultAttachArrList = (ArrayList)request.getAttribute("resultAttachArrList");
ListOrderedMap tempLom = new ListOrderedMap();
ArrayList listContractAttach 		 = new ArrayList();		//의뢰계약서
ArrayList listEtcAttach		 		 = new ArrayList();		//의뢰기타
ArrayList listEtc2Attach	 		 = new ArrayList();		//의뢰별첨
ArrayList listCmpltAttach	 		 = new ArrayList();		//종료관리
ArrayList listInfoAttach	 		 = new ArrayList();		//사전품의
ArrayList listHisConsultCntrtAttach	 = new ArrayList();		//의뢰계약서
ArrayList listHisConsultAppendAttach = new ArrayList();		//의뢰별첨
ArrayList listHisConsultOtherAttach	 = new ArrayList();		//의뢰기타
ArrayList listHisReplyCntrtAttach	 = new ArrayList();		//회신계약서
ArrayList listHisReplyAppendAttach	 = new ArrayList();		//회신별첨
ArrayList listHisApproveAttach		 = new ArrayList();		//승인
ArrayList listHisSignAttach			 = new ArrayList();		//체결

if(resultAttachArrList != null && resultAttachArrList.size() > 0) {
	for(int j=0; j < resultAttachArrList.size(); j++) {
		tempLom = (ListOrderedMap)resultAttachArrList.get(j);
		if("cntrt".equals((String)tempLom.get("filetype"))) {
			listContractAttach.add(resultAttachArrList.get(j));
		} else if("etc".equals((String)tempLom.get("filetype"))) {
			listEtcAttach.add(resultAttachArrList.get(j));
		} else if("etc2".equals((String)tempLom.get("filetype"))) {
			listEtc2Attach.add(resultAttachArrList.get(j));
		} else if("cmplt".equals((String)tempLom.get("filetype"))) {
			listCmpltAttach.add(resultAttachArrList.get(j));
		} else if("info".equals((String)tempLom.get("filetype"))) {
			listInfoAttach.add(resultAttachArrList.get(j));
		} else if("hisConsultCntrt".equals((String)tempLom.get("filetype"))) {
			listHisConsultCntrtAttach.add(resultAttachArrList.get(j));
		} else if("hisConsultOther".equals((String)tempLom.get("filetype"))) {
			listHisConsultOtherAttach.add(resultAttachArrList.get(j));
		} else if("hisConsultAppend".equals((String)tempLom.get("filetype"))) {
			listHisConsultAppendAttach.add(resultAttachArrList.get(j));
		} else if("hisReplyCntrt".equals((String)tempLom.get("filetype"))) {
			listHisReplyCntrtAttach.add(resultAttachArrList.get(j));
		} else if("hisReplyAppend".equals((String)tempLom.get("filetype"))) {
			listHisReplyAppendAttach.add(resultAttachArrList.get(j));
		} else if("hisApprove".equals((String)tempLom.get("filetype"))) {
			listHisApproveAttach.add(resultAttachArrList.get(j));
		} else if("hisSign".equals((String)tempLom.get("filetype"))) {
			listHisSignAttach.add(resultAttachArrList.get(j));
		}
	}
}
 

%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="title_basic">
	<h4><spring:message code="clm.page.msg.manage.endInfo" /></h4>
</div>
<!-- //tab02 -->
<table id="contract-completion-content" cellspacing="0" cellpadding="0" border="0" class="table-style01">
	<colgroup>
		<col width="14%" />
		<col width="22%" />
		<col width="14%" />
		<col width="18%" />
		<col width="14%" />
		<col width="18%" />
	</colgroup>
	<tr>
		<th ><spring:message code="clm.page.msg.manage.reqPrsn" /></th>
		<td><c:out value='${completionLom.cntrt_chge_demndman_nm}' /></td>
		<th ><spring:message code="clm.page.msg.manage.level" /></th>
		<td><c:out value='${completionLom.cntrt_chge_demndman_jikgup_nm}' /></td>
		<th ><spring:message code="clm.page.msg.manage.deptName" /></th>
		<td><c:out value='${completionLom.cntrt_chge_demnd_dept_nm}' /></td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.apprPer" /></th>
		<td><c:out value='${approveManLom.user_name }' /></td>
		<th><spring:message code="clm.page.msg.manage.level" /></th>
		<td><c:out value='${approveManLom.duty }' /></td>
		<th><spring:message code="clm.page.msg.manage.deptName" /></th>
		<td><c:out value='${approveManLom.dept_name }' /></td>
	</tr>
<%
if("Y".equals(tmpLom.get("cntrt_chge_demndday_exist"))){
%>	
	<tr>
		<th><spring:message code="clm.page.msg.manage.reqDt" /></th>
		<td colspan="5"><c:out value='${completionLom.cntrt_chge_demndday}' /></td>
	</tr>
<%
}
%>
	<tr>
		<th ><spring:message code="clm.page.msg.manage.statChg" /></th>
		<td colspan="5"><c:out value='${completionLom.cntrt_status_nm}' /></td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.chgStatRsn" /></th>
		<td colspan="5"><c:out value='${completionLom.cntrt_chge_demnd_cause}' /></td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.fileAttach" /></th>
		<td colspan="5">
<%		
	if(listCmpltAttach != null && listCmpltAttach.size() > 0) {
		for(int i=0; i<listCmpltAttach.size(); i++){
  	  		tempLom = (ListOrderedMap)listCmpltAttach.get(i);
%>  	  
	<a href=<c:out value='${attachinfo}'/><%=tempLom.get("file_id")%>><%=tempLom.get("org_file_nm") %></a></br>
<%	}} else { %><spring:message code="clm.page.msg.manage.noAttach" /><%	} %>		
		</td>
	</tr>
</table>

<div class="title_basic">
	<h4><spring:message code="clm.page.msg.manage.bscInfCont" /></h4>
</div>
<div style="margin:20px 0 0 0; float:right;">
 <spring:message code="clm.page.msg.manage.contId" /> : <c:out value='${contractMstLom.cntrt_id}'/>&nbsp;&nbsp;
</div>
<table id="contract-base-content" cellspacing="0" cellpadding="0" border="0" class="table-style01" style="margin-top:0;">
	<colgroup>
		<col width="15%" />
		<col width="11%" />
		<col width="19%" />
		<col width="14%" />
		<col width="12%" />
		<col width="12%" />
		<col width="17%" />
	</colgroup>
	<tr>
		<th><spring:message code="clm.page.msg.manage.contName" /></th>
		<td colspan="6"><c:out value="${contractMstLom.cntrt_nm}" /></td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.common.otherParty" /></th>
		<td colspan="2"><c:out value="${contractMstLom.cntrt_oppnt_nm}" /></td>
		<th><spring:message code="clm.page.field.customer.registerNo" /></th>
		<td class="last-td"><c:out value="${contractMstLom.cntrt_oppnt_ceo}" /></td>
		<th><spring:message code="clm.page.field.customer.contractRequired" /></th>
		<td><c:out value="${contractMstLom.cntrt_oppnt_type_nm}" /></td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.field.contract.basic.oppntnm"/></th>
		<td colspan="2">
			<c:out value="${contractMstLom.cntrt_oppnt_respman}" />
		</td>			
		<th><spring:message code="clm.page.field.contract.basic.oppnttelno"/></th>
		<td>
			<c:out value="${contractMstLom.cntrt_oppnt_telno}" />
		</td>
		<th><spring:message code="clm.page.field.contract.basic.oppntemail"/></th>
		<td>
			<c:out value="${contractMstLom.cntrt_oppnt_email}" />
		</td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.contType" /></th>
		<td colspan="6">
			<c:out value='${contractMstLom.biz_clsfcn_nm}'/>/<c:out value='${contractMstLom.depth_clsfcn_nm}'/>/<c:out value='${contractMstLom.cnclsnpurps_bigclsfcn_nm}'/>/<c:out value='${contractMstLom.cnclsnpurps_midclsfcn_nm}'/>
		</td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.contItm" /></th>
		<td colspan="2"><c:out value="${contractMstLom.cntrt_trgt_nm}" /></td>
		<th><spring:message code="clm.page.msg.manage.contItmDtl" /></th>
		<td colspan="3"><c:out value="${contractMstLom.cntrt_trgt_det}" /></td>
	</tr>
	<c:if test="${!empty contractMstLom.pshdbkgrnd_purps}">
	<tr>
		<th><spring:message code="clm.page.msg.manage.bg" /></th>
		<td colspan="6"><c:out value="${contractMstLom.pshdbkgrnd_purps}" escapeXml="false" /></td>
	</tr>
	</c:if>
	<c:if test="${!empty contractMstLom.antcptnefct}">
	<tr>
		<th><spring:message code="clm.page.msg.manage.hope" /></th>
		<td colspan="6"><c:out value="${contractMstLom.antcptnefct}" escapeXml="false" /></td>
	</tr>
	</c:if>
	<tr>
		<th rowspan="3"><spring:message code="clm.page.msg.common.attachment" /></th>
		<td><span class="blueD"><spring:message code="clm.page.msg.manage.contract" /></span></td>
		<td colspan="5">
<%		
	if(listContractAttach != null && listContractAttach.size() > 0) {
  	  tempLom = (ListOrderedMap)listContractAttach.get(0);
%>  	  
	<a href=<c:out value='${attachinfo}'/><%=tempLom.get("file_id")%>><%=tempLom.get("org_file_nm") %></a>
<%	} else { %><spring:message code="clm.page.msg.manage.noAttach" /><%	} %>
		</td>
	</tr>
	<tr>
		<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.manage.attachment" /></span></td>
		<td colspan="5">
<%		
	if(listEtcAttach != null && listEtcAttach.size() > 0) {
		for(int i=0; i<listEtcAttach.size(); i++){
  	  		tempLom = (ListOrderedMap)listEtcAttach.get(i);
%>  	  
	<a href=<c:out value='${attachinfo}'/><%=tempLom.get("file_id")%>><%=tempLom.get("org_file_nm") %></a></br>
<%	}} else { %><spring:message code="clm.page.msg.manage.noAttach" /><%	} %>
		</td>
	</tr> 
	<tr>
		<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.common.etc" /></span></td>
		<td colspan="5">
<%		
	if(listEtc2Attach != null && listEtc2Attach.size() > 0) {
		for(int i=0; i<listEtc2Attach.size(); i++){
  	  		tempLom = (ListOrderedMap)listEtc2Attach.get(i);
%>  	  
	<a href=<c:out value='${attachinfo}'/><%=tempLom.get("file_id")%>><%=tempLom.get("org_file_nm") %></a></br>
<%	}} else { %><spring:message code="clm.page.msg.manage.noAttach" /><%	} %>
		</td>
	</tr>
</table>
<!-- 계약상세내역 : default : display:none -->
<div class="title_basic">
	<h4><spring:message code="clm.page.msg.manage.contDetailInf" /></h4>
</div>
<table id="contract-detail-content" cellspacing="0" cellpadding="0" border="0" class="table-style01" style="margin-top:0;">
	<colgroup>
		<col width="14%" />
		<col width="10%" />
		<col width="14%" />
		<col width="16%" />
		<col width="14%" />
		<col width="32%" />
	</colgroup>
	<tr>
		<th><spring:message code='clm.page.field.contract.detail.period' /></th>
		<td colspan="2"><c:out value="${contractMstLom.cntrtperiod_startday}" /> ~ <c:out value="${contractMstLom.cntrtperiod_endday}" /></td>	
		<th><spring:message code='clm.page.field.contract.detail.secret' /></th>
		<td colspan="3"><c:out value="${contractMstLom.secret_keepperiod}" /></td>
	</tr>
	<tr>
		<th><spring:message code='clm.page.field.contract.detail.paygubun' /></th>
		<td colspan="2"><c:out value="${contractMstLom.payment_gbn_nm}" /></td>
		<th><spring:message code='clm.page.field.contract.detail.price' /></th>
		<td colspan="3"><c:out value="${contractMstLom.cntrt_amt}" /></td>
	</tr>
	<c:if test="${!empty contractMstLom.crrncy_unit}">
	<tr>
		<th><spring:message code='clm.page.field.contract.detail.money' /></th>
		<td colspan="5"><c:out value="${contractMstLom.crrncy_unit}" /></td>
	</tr>
	</c:if>
	<c:if test="${!empty contractMstLom.cntrt_untprc_expl}">	
	<tr>
		<th><spring:message code='clm.page.field.contract.detail.unitcont' /></th>
		<td colspan="5">
			<c:out value="${contractMstLom.cntrt_untprc_expl}" escapeXml="false" /></br>
		</td>
	</tr>
	</c:if>		
	<c:if test="${!empty contractMstLom.payment_cond}">
	<tr>
		<th><spring:message code='clm.page.field.contract.detail.payment' /></th>
		<td colspan="5"><c:out value="${contractMstLom.payment_cond}" /></td>
	</tr>
	</c:if>
		<!-- 특화정보  -->

		<c:forEach var="list" items="${considerationSpecialList}">
			<c:if test="${!empty list.attr_value}">
			<tr>	
				<th><c:out value="${list.attr_nm}"/></th>
				<td class="tal_lineL" colspan="5"><c:out value="${list.attr_value}"/></td>
			</tr>	
			</c:if>
		</c:forEach>
		
		<c:forEach var="list" items="${consultationSpecialList}">
			<c:if test="${!empty list.attr_value}">
			<tr>	
				<th><c:out value="${list.attr_nm}"/></th>
				<td class="tal_lineL" colspan="5"><c:out value="${list.attr_value}"/></td>
			</tr>	
			</c:if>
		</c:forEach>
	
	<!-- //특화정보 -->
	<c:if test="${!empty contractMstLom.auto_rnew_yn}">
	<tr>
		<th><spring:message code="clm.page.field.contract.consultation.detail.autorenew" /></th>
		<td colspan="5">
		<c:if test="${contractMstLom.auto_rnew_yn=='Y'}">Yes</c:if>
		<c:if test="${contractMstLom.auto_rnew_yn=='N'}">No</c:if>
		</td>
	</tr>
	</c:if>
	<c:if test="${!empty contractMstLom.oblgt_lmt}">
	<tr>
		<th><spring:message code="clm.page.field.contract.consultation.detail.obligationlimit"/></th>
	  	<td colspan="5">
	  		<c:out value='${contractMstLom.oblgt_lmt}'/>
      	</td>
	</tr>
	</c:if>
	<c:if test="${!empty contractMstLom.spcl_cond}">
	<tr>
		<th><spring:message code="clm.page.field.contract.consultation.detail.specialcondition"/></th>
		<td colspan="5"><c:out value='${contractMstLom.spcl_cond}' escapeXml="false" /></td>
	</tr>	
	</c:if>
	<tr>
		<th><spring:message code="clm.page.field.contract.detail.loac"/></th>
		<td colspan="2"><c:out value='${contractMstLom.loac}'/></td>
		<th><spring:message code="clm.page.field.contract.detail.loacdetail"/></th>
		<td colspan="2"><c:out value='${contractMstLom.loac_etc}'/></td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.field.contract.detail.solutionmethod"/></th>
		<td colspan="2"><c:out value='${contractMstLom.dspt_resolt_mthd}'/></td>
		<th><spring:message code="clm.page.field.contract.detail.solutionmethoddetail"/></th>
		<td colspan="2"><c:out value='${contractMstLom.dspt_resolt_mthd_det}'/></td>
	</tr>
</table>
<!-- //top table -->
<!-- //계약상세내역 : default : display:none -->

<!-- 연관계약정보 -->
<div class="title_basic">
	<h4><spring:message code="clm.page.msg.manage.relContInf" /></h4>
</div>
<table id="contract-relation-content" cellspacing="0" cellpadding="0" border="0" class="table-style01" style="display:;">
	<colgroup>
		<col width="12%" />
		<col width="50%" />
		<col width="10%" />
		<col/>					
	</colgroup>
	<tbody id="relationTbody">
	<tr id="trRelationContract">
		<th>RelationType</th>
		<th><spring:message code="clm.page.msg.manage.relCont" /></th>
		<th><spring:message code="clm.page.msg.manage.define" /></th>
		<th><spring:message code="clm.page.msg.manage.relDetail" /></th>
	</tr>	
	<c:choose>
	<c:when test ="${relationListSize > 0}">
		<c:forEach var="list" items="${contractRelationLom}" varStatus="i">
			<tr id="trRelationContractCont">
				<td><c:out value='${list.rel_type_nm}'/></td>
				<td><c:out value='${list.relation_cntrt_nm}'/></td>
				<td><c:out value='${list.rel_defn}'/></td>
				<td><c:out value='${list.expl}'/></td>
			</tr>		
		</c:forEach>
	</c:when>
	<c:otherwise>
		<tr id="trRelationContractCont"><td colspan="4">No Data Found. </td></tr>
	</c:otherwise>
	</c:choose>	
	</tbody>		
</table>	

<!-- //이력정보 -->
<div class="title_basic">
	<h4><spring:message code="clm.page.msg.manage.revHistory" /></h4>
</div>
<table cellspacing="0" cellpadding="0" border="0" class="list_basic" id="contract-reviewHisView">
	<colgroup>
		<col width="60%" />
		<col width="15%" />
		<col width="25%" />
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="clm.page.msg.manage.reqBrkd" /></th>
		<th><spring:message code="clm.page.msg.manage.date" /></th>
		<th><spring:message code="clm.page.msg.manage.name" /></th>		
	</tr>
	</thead>
	<tbody>
<%
if(review.size() > 0){
	for(int idx=0;idx < review.size();idx++){

		ListOrderedMap lom = (ListOrderedMap)review.get(idx);		
%>		
		<tr>
			<td class="tL">
				<span class="mR5">
				<img src="<spring:message code='secfw.url.domain' /><%=IMAGE%>/icon/ico_minus.gif" alt="show" />
				</span>
				<%=lom.get("cr_flan_nm") %>
				<%=StringUtil.convertHtmlTochars((String)lom.get("title"))%>
			</td>
			<td><%=lom.get("reg_dt") %></td>
			<td><%=lom.get("man_nm") %>/<%=lom.get("jikgup_nm") %>/<%=lom.get("dept_nm") %></td>
		</tr>
		<!-- 테이블안에 테이블 -->
		<tr class="Nocol" id="tr_show" >
			<td colspan="3">
				<table class="table-style_sub02">
				<colgroup>
					<col width="11%" />
					<col width="*" />
					<col width="78%" />
				</colgroup>

	<%
		//최종검토내용 및 첨부파일 없애기위해  false 셋팅함  : 2011. 12.15일 수정
		if(false && "CONSULT".equals(lom.get("cr_flag"))){
	%>					
					<tr class="Nocol">
						<th><spring:message code="clm.page.msg.manage.reviewReqCont" /></th>
						<td colspan="2">
							<%=lom.get("cont") %>
						</td>
					</tr>
	<!--  2012.03.20 검토본변경여부, 변경내역 및 사유 추가 added by hanjihoon -->				
	<%
			if("Y".equals(lom.get("plndbn_req_yn"))){
				if(!"".equals(StringUtil.bvl(lom.get("lastbn_chge_yn_nm"), ""))){
	%>
					<tr>
						<th><spring:message code="clm.page.msg.manage.chgRevVerYn" /></th>
						<td colspan="2"><%=lom.get("lastbn_chge_yn_nm") %></td>
					</tr>
	<%
				}
				
				if(!"".equals(StringUtil.bvl(lom.get("plndbn_chge_cont"), ""))){
	%>
					<tr>
						<th><spring:message code="clm.page.msg.manage.rsnBrkChange" /></th>
						<td colspan="2"><%=lom.get("plndbn_chge_cont") %></td>
					</tr>
	<%
				}
			}
	%>
					<tr>
					<th rowspan="3"><spring:message code="clm.page.msg.common.attachment" /></th>
						<td><span class="blueD"><spring:message code="clm.page.msg.manage.contract" /></span></td>
						<td>
<%		
	if(listHisConsultCntrtAttach != null && listHisConsultCntrtAttach.size() > 0) {
		for(int i=0; i<listHisConsultCntrtAttach.size(); i++){
  	  		tempLom = (ListOrderedMap)listHisConsultCntrtAttach.get(i);
  	  		if( (lom.get("cnsdreq_id")).equals(tempLom.get("cnsdreq_id")) ){
%>  	  
	<a href=<c:out value='${attachinfo}'/><%=tempLom.get("file_id")%>><%=tempLom.get("org_file_nm") %></a></br>
<%	}}} else { %><spring:message code="clm.page.msg.manage.noAttach" /><%	} %>
						</td>
					</tr>
					<tr>
						<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.manage.attachment" /></span></td>
						<td>
<%		
	if(listHisConsultAppendAttach != null && listHisConsultAppendAttach.size() > 0) {
		for(int i=0; i<listHisConsultAppendAttach.size(); i++){
  	  		tempLom = (ListOrderedMap)listHisConsultAppendAttach.get(i);
  	  		if( (lom.get("cnsdreq_id")).equals(tempLom.get("cnsdreq_id")) ){
%>  	  
	<a href=<c:out value='${attachinfo}'/><%=tempLom.get("file_id")%>><%=tempLom.get("org_file_nm") %></a></br>
<%	}}} else { %><spring:message code="clm.page.msg.manage.noAttach" /><%	} %>
						</td>
						
					</tr>
					<tr>
						<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.common.etc" /></span></td>
						<td>
<%		
	if(listHisConsultOtherAttach != null && listHisConsultOtherAttach.size() > 0) {
		for(int i=0; i<listHisConsultOtherAttach.size();i++){
  	  		tempLom = (ListOrderedMap)listHisConsultOtherAttach.get(i);
  	  		if( (lom.get("cnsdreq_id")).equals(tempLom.get("cnsdreq_id")) ){
%>  	  
	<a href=<c:out value='${attachinfo}'/><%=tempLom.get("file_id")%>><%=tempLom.get("org_file_nm") %></a></br>
<%	}}} else { %><spring:message code="clm.page.msg.manage.noAttach" /><%	} %>
						</td>
					</tr>
					
	<%
		//최종검토내용 및 첨부파일 없애기위해  false 셋팅함  : 2011. 12.15일 수정
		}else if(false){
	%>
					<tr class="Nocol">
						<th><spring:message code="clm.page.msg.manage.finalOpin" /></th>
						<td colspan="2">
							<%=lom.get("cont") %>
						</td>
					</tr>
					<tr class="Nocol">
						<th><spring:message code="clm.page.msg.manage.opinLegalCom" /></th>
						<td colspan="2">
							<%=lom.get("cnsd_apbt") %>
						</td>
					</tr>
	<%
			if("CLM".equals(session.getAttribute("secfw.session.sys_cd"))){
	%>					
					<tr>
					<th rowspan="2"><spring:message code="clm.page.msg.common.attachment" /></th>
						<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.manage.contract" /></span></td>
						<td>
<%		
	if(listHisReplyCntrtAttach != null && listHisReplyCntrtAttach.size() > 0) {
		for(int i=0; i<listHisReplyCntrtAttach.size(); i++){
			tempLom = (ListOrderedMap)listHisReplyCntrtAttach.get(i);
  	  		if( (lom.get("cnsdreq_id")).equals(tempLom.get("cnsdreq_id")) ){
%>  	  
	<a href=<c:out value='${attachinfo}'/><%=tempLom.get("file_id")%>><%=tempLom.get("org_file_nm") %></a></br>
<%	}}} else { %><spring:message code="clm.page.msg.manage.noAttach" /><%	} %>
						</td>
					</tr>
					<tr>
						<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.manage.noAttach" /></span></td>
						<td>
<%		
	if(listHisReplyAppendAttach != null && listHisReplyAppendAttach.size() > 0) {
		for(int i=0; i<listHisReplyAppendAttach.size(); i++){
  	  		tempLom = (ListOrderedMap)listHisReplyAppendAttach.get(0);
  	  		if( (lom.get("cnsdreq_id")).equals(tempLom.get("cnsdreq_id")) ){
%>  	  
	<a href=<c:out value='${attachinfo}'/><%=tempLom.get("file_id")%>><%=tempLom.get("org_file_nm") %></a></br>
<%	}}} else { %><spring:message code="clm.page.msg.manage.attachment" /><%	} %>
						</td>
						
					</tr>
<%
			}else if("LAS".equals(session.getAttribute("secfw.session.sys_cd"))){
%>					
					<!-- <tr>
						<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.manage.contract" />1</span></td>
						<td></td>
					</tr>
					<tr>
						<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.manage.attachment" />1</span></td>
						<td></td>
						
					</tr> -->
					
	<%
			}
		}
	%>
	
				</table>
			</td>
		</tr>
<%
	}
}else{
%>
		<tr id="notFoundList" onmouseout="this.className='';" onmouseover="this.className='selected'">
			<td colspan="3" align="center" class="tC"><spring:message code="clm.msg.succ.noResult" /></td>
		</tr>
<%
}
%>
	</tbody>
</table>
<div class="title_basic">
  <h4><spring:message code="clm.page.msg.manage.apprHist" /></h4>
</div>

<table cellspacing="0" cellpadding="0" border="0" class="list_basic" id="contract-approveHisView">
	<colgroup>
		<col width="57%" />
		<col width="10%" />
		<col width="8%" />
		<col width="25%" />
	</colgroup>
	<thead>
		<tr>
			<th><spring:message code="clm.page.msg.manage.itmName" /></th>
			<th><spring:message code="clm.page.msg.common.div" /></th>
			<th><spring:message code="clm.page.msg.manage.date" /></th>
			<th><spring:message code="clm.page.msg.manage.apprPer" /></th>
		</tr>
	</thead>
	<tbody>
<%
if(info.size() > 0){
	
	for(int idx=0;idx < info.size();idx++){

		ListOrderedMap lom = (ListOrderedMap)info.get(idx);
		
%>		
		<tr>
			<td class="tL">
				<span class="mR5">
				<!-- img src="<spring:message code='secfw.url.domain' /><%=IMAGE%>/icon/ico_plus.gif" alt="show01" /-->
				<img src="<spring:message code='secfw.url.domain' /><%=IMAGE%>/icon/ico_minus.gif" alt="show" />
				</span>
				<spring:message code="clm.page.msg.manage.preApprInf" />
			</td>
			<td><%=lom.get("bfhdcstn_apbt_mthd_nm") %></td>
			<td><%=lom.get("bfhdcstn_apbtday") %></td>
			<td><%=lom.get("bfhdcstn_apbtman_nm") %> <%=lom.get("bfhdcstn_apbtman_jikgup_nm") %>/<%=lom.get("bfhdcstn_apbt_dept_nm") %></td>
		</tr>
		<!-- 테이블안에 테이블 -->
		<tr class="Nocol" id="tr_show01" style="display:none">
			<td colspan="4">
				<table class="table-style_sub02">
					<colgroup>
						<col width="11%" />
						<col width="*" />
					</colgroup>
					<tr class="Nocol">
						<th>
							 <spring:message code="clm.page.msg.manage.proposer" />
						</th>
						<td colspan="3">
							<%=lom.get("bfhdcstn_mtnman_nm") %>
						</td>
					</tr>
					<tr class="Nocol">
						<th><span class="blueD"><spring:message code="clm.page.msg.common.attachment" /></span></th>
						<td colspan="3">
							<%--
							<iframe src="<c:url value='/clm/blank.do' />" id="info_fileList" name="info_fileList" frameborder="0" width="100%" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
							<input type="hidden" name="info_fileInfos"  value="" />
							 --%>
							<%		
								if(listInfoAttach != null && listInfoAttach.size() > 0) {
									for(int i=0; i<listInfoAttach.size(); i++){
							  	  		tempLom = (ListOrderedMap)listInfoAttach.get(i);
							%>  	  
								<a href=<c:out value='${attachinfo}'/><%=tempLom.get("file_id")%>><%=tempLom.get("org_file_nm") %></a></br>
							<%	}} else { %><spring:message code="clm.page.msg.manage.noAttach" /><%	} %>
						</td>
					</tr>
				</table>
			</td>
		</tr>
<%
	} 
}else {
%>
		<tr id="notFoundList" onmouseout="this.className='';" onmouseover="this.className='selected'">
			<td colspan="4" align="center" class="tC"><spring:message code="clm.msg.succ.noResult" /></td>
		</tr>
<%
}
%>	
<%
if(approve.size() > 0){

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
    
    
    boolean bFirstLine  = false; //현재 Mis_id의 첫번째 라인여부
    String sMemMis_id   = "" ;  //현재 Mis_id
    int iMemAllMember   = 0 ;   //현재 결재건(MIS_ID)에 묶인  기안,합의,승인자의 갯수
    int iMemRow         = 0 ;   //현재 iMemAllMember의 Row위치
    String sNextMis_id  = "" ;  //다음  Mis_id
    int iFileRowIndex   = 0 ;   //파일 index
    int iFile_cnt       = 0;    //파일갯수  
    String sApprovalName= "";   //승인자명 
    String sStatus      = "";
    String sStatusCd		= "";
    for(int idx=0;idx < approve.size();idx++){

        ListOrderedMap lom = (ListOrderedMap)approve.get(idx);
        
        sNextMis_id = (String)lom.get("mis_id");
        
        if(idx == 0){
            sMemMis_id  = (String)lom.get("mis_id");
            bFirstLine  = true;
            iMemAllMember= (((BigDecimal)lom.get("cntrt_id_cnt")).intValue());
            iMemRow = 1;
        }else{
            //다음Mis_id와  현재Mis_id가 동일하지 않으면 다른 결재권임.
            if(! sNextMis_id.equals(sMemMis_id)){
                sMemMis_id = sNextMis_id;
                bFirstLine  = true;
                iMemAllMember= (((BigDecimal)lom.get("cntrt_id_cnt")).intValue());
                iMemRow = 0;
                
                iFileRowIndex ++;
            }
            
            iMemRow ++;
        }
        
        if(bFirstLine) {
            bFirstLine = false;
%>      
        <tr>
            <td class="tL">
                <span class="mR5">
                <img src="<%=IMAGE%>/icon/ico_plus.gif" alt="show01" />
                </span>
                <a href="javascript:considerationPreview('<%=sMemMis_id %>');"><%=lom.get("title") %></a>
            </td>
            <td><%=lom.get("status") %></td>
            <td class="tC"><%=lom.get("create_date") %></td>
            <td id='sp_approval<%=iFileRowIndex %>'></td>
        </tr>
        <!-- 테이블안에 테이블 -->
        <tr class="Nocol" id="tr_show01">
            <td colspan="4">
                <table class="table-style_sub02">
                    <colgroup>
						<!-- Sungwoo 2014-05-14 Approve 상태 추가에 따른 width 재조정 -->
						<col width="10%" />
						<col width="60%"/>
						<col width="15%"/>
						<col width="15%"/>
                    </colgroup>
                    <tr class="Nocol">
                        <th rowspan='<%=lom.get("cntrt_id_cnt")%>'>
                             <spring:message code="clm.page.msg.manage.approver" />
                        </th>
                        <td>
                            <strong>[<%=lom.get("cd_nm") %>]</strong> <%=lom.get("user_name") %>/<%=lom.get("duty") %>/<%=lom.get("dept_name") %>/<%=lom.get("group_name") %>
                        </td>
						<!-- Sungwoo 2014-05-14 Approve 상태 추가 -->
						<td><%=lom.get("prg_status")%></td>
						<td><%=lom.get("approved")%></td>
                    </tr>
<%
        }else if(! bFirstLine){
            sApprovalName = "";
            sStatus = (String)lom.get("cd_nm");
            sStatusCd = (String)lom.get("cd");
			//if(sStatus != null && sStatus.equals("결재") ){
			if(sStatus != null && sStatusCd.equals("1") ){
                sApprovalName = (String)lom.get("user_name") + "/" + (String)lom.get("duty") + "/" + (String)lom.get("dept_name");
%>                  
                    <script>document.getElementById('sp_approval<%=iFileRowIndex %>').innerHTML = '<%=sApprovalName%>';</script>
                    <%} %>
                    <tr class="Nocol">
                        <td class="tal_lineL">
                            <strong>[<%=lom.get("cd_nm") %>]</strong> <%=lom.get("user_name") %>/<%=lom.get("duty") %>/<%=lom.get("dept_name") %>/<%=lom.get("group_name") %>
                        </td>
						<!-- Sungwoo 2014-05-14 Approve 상태 추가 -->
						<td><%=lom.get("prg_status")%></td>
						<td><%=lom.get("approved")%></td>
                    </tr>
<%
        }
         
        //해당Mis_id의 최종 Row이면
        if(iMemRow == iMemAllMember) {
            iFile_cnt   = ((BigDecimal)lom.get("file_cnt")).intValue();
            //첨부 파일이 있으면    
            if(iFile_cnt > 0) {
%>
                    <tr class="slide-target02 slide-area">
                        <th><span class="tal_lineL"><spring:message code="clm.page.msg.common.attachment" /><%=lom.get("file_cnt") %></span></th>
                        <!-- Sungwoo 2014-05-14 Approve 상태 변경으로 인한 colspan 변경 -->
						<td colspan="3">
                    		<iframe src="<c:url value='/clm/blank.do' />" id="approve_fileList<%=iFileRowIndex %>" name="approve_fileList<%=iFileRowIndex %>"  frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe>
                            <input type="hidden" name="approve_fileInfos<%=iFileRowIndex %>"  value="" /> <!-- 이력기타파일정보 -->
                            <input type="hidden" name="approve_cnsdreq_id<%=iFileRowIndex %>"  value="<%=lom.get("cnsdreq_id") %>" /> <!-- 이력정보첨부파일정보 -->
                        </td>
                    </tr>
                <%} %>  
                </table>
            </td>
        </tr>
<%           
        }
    }
}
%>
	</tbody>
</table>

<div class="title_basic">
  <h4><spring:message code="clm.page.msg.manage.conclInf" /></h4>
</div>

<!-- 체결품의정보시작 : default : display:none -->
<div id="contract-cnclsnInfo-view">
<div class="title_basic"><h5 class="ntitle05"><spring:message code="clm.page.msg.manage.bscInf" /></h5></div>
<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
	<colgroup>
		<col width="14%" />
		<col width="25%" />
		<col width="14%" />
		<col width="19%" />
		<col width="14%" />
		<col width="14%" />
	</colgroup>
	<tr>
		<th><spring:message code="clm.page.msg.manage.conclYn" /></th>
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
		<th><spring:message code="clm.page.msg.manage.signType" /></th>
		<td><c:out value='${contractMstLom.seal_mthd_nm}'/></td>
		
		<c:choose>
		<c:when test="${contractMstLom.seal_mthd=='C02101'}">
			<th><spring:message code="clm.page.msg.manage.signManager" /></th>
		  	<td colspan=3><c:out value='${contractMstLom.seal_ffmtman_nm}'/>/<c:out value='${contractMstLom.seal_ffmtman_jikgup_nm}'/>/<c:out value='${contractMstLom.seal_ffmt_dept_nm}'/></td>
	  	</c:when>
	  	<c:otherwise>
		  	<th><spring:message code="clm.page.msg.manage.signRes" /></th>
		  	<td colspan=3><c:out value='${contractMstLom.sign_plndman_nm}'/>/<c:out value='${contractMstLom.sign_plndman_jikgup_nm}'/>/<c:out value='${contractMstLom.sign_plnd_dept_nm}'/></td>
	  	</c:otherwise>
		</c:choose>
	</tr>
	
	<c:if test="${contractMstLom.seal_mthd=='C02102'}">
		<tr>
			<th><spring:message code="clm.page.msg.manage.signPerSE" /></th>
			<td><c:out value='${contractMstLom.signman_nm}'/>/<c:out value='${contractMstLom.signman_jikgup_nm}'/>/<c:out value='${contractMstLom.sign_dept_nm}'/></td>
			<th><spring:message code="clm.page.msg.manage.signDateSE" /></th>
			<td colspan=3><c:out value='${contractMstLom.signday}'/></td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.manage.signPerOth" /></th>
			<td><c:out value='${contractMstLom.oppnt_signman_nm}'/>/<c:out value='${contractMstLom.oppnt_signman_jikgup}'/>/<c:out value='${contractMstLom.oppnt_signman_dept}'/></td>
			<th><spring:message code="clm.page.msg.manage.signDateOth" /></th>
			<td colspan=3><c:out value='${contractMstLom.oppnt_signday}'/></td>
		</tr>
	</c:if>
	
	<tr>
	<th><spring:message code="clm.page.msg.manage.chrgCont" /></th>
	<td><c:out value='${contractMstLom.cntrt_respman_nm}'/>/<c:out value='${contractMstLom.cntrt_respman_jikgup_nm}'/>/<c:out value='${contractMstLom.cntrt_resp_dept_nm}'/></td>
	
<%--	신성우 주석처리 2014-04-01
	<th><spring:message code="clm.page.msg.manage.preAnnDate" /></th>
	<td colspan='3'><c:out value='${contractMstLom.exprt_notiday}'/></td> --%>	
	</tr>
</table><br>

<div class="title_basic"><h5 class="ntitle05"><spring:message code="clm.page.msg.manage.copyVerInf" /></h5></div>
<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
	<colgroup>
		<col width="14%" />
		<col width="25%" />
		<col width="14%" />
		<col width="19%" />
		<col width="14%" />
		<col width="14%" />
	</colgroup>
	<tr>
		<th><spring:message code="clm.page.msg.manage.copyVerRegPer" /></th>
		<td><c:out value='${contractMstLom.cpy_regman_nm}'/>/<c:out value='${contractMstLom.cpy_regman_jikgup_nm}'/>/<c:out value='${contractMstLom.cpy_reg_dept_nm}'/></td>
		<th><spring:message code="clm.page.msg.manage.copyVerRegDate" /></th>
		<td colspan="3"><c:out value='${contractMstLom.cpy_regday}'/></td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.conclCopy" /></th>
		<td colspan="5">
		<%		
	if(listHisSignAttach != null && listHisSignAttach.size() > 0) {
		for(int i=0; i<listHisSignAttach.size(); i++){
  	  		tempLom = (ListOrderedMap)listHisSignAttach.get(i);
%>  	  
	<a href=<c:out value='${attachinfo}'/><%=tempLom.get("file_id")%>><%=tempLom.get("org_file_nm") %></a></br>
<%	}} else { %><spring:message code="clm.page.msg.manage.noAttach" /><%	} %>
	</td>
	</tr>
	<!-- 
	<tr>
		<th><spring:message code="clm.page.msg.manage.attachment" /></th>
		<td colspan=5></td>
	</tr>
	 -->
</table><br>

<div class="title_basic"><h5 class="ntitle05"><spring:message code="clm.page.msg.manage.orgInf" /></h5></div>
<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
	<colgroup>
		<col width="14%" />
		<col width="25%" />
		<col width="14%" />
		<col width="19%" />
		<col width="14%" />
		<col width="14%" />
	</colgroup>
	<tr>
		<th><spring:message code="clm.page.msg.manage.orgSendPer" /></th>
		<td><c:out value='${contractMstLom.org_hdovman_nm}'/>/<c:out value='${contractMstLom.org_hdovman_jikgup_nm}'/>/<c:out value='${contractMstLom.org_hdov_dept_nm}'/></td>
		<th><spring:message code="clm.page.msg.manage.orgRcvDate" /></th>
		<td colspan=3><c:out value='${contractMstLom.org_acptday}'/></td>
	</tr>
	<tr>
		<th><spring:message code="las.page.field.contractManager.groupChief" /></th>
		<td><c:out value='${contractMstLom.org_tkovman_nm}'/>/<c:out value='${contractMstLom.org_tkovman_jikgup_nm}'/>/<c:out value='${contractMstLom.org_tkov_dept_nm}'/></td>
		<th><spring:message code="clm.page.msg.manage.orgPlace" /></th>
		<td colspan=3><c:out value='${contractMstLom.org_strg_pos}'/></td>
	</tr>
</table><br>

<c:if test="${contractMstLom.cntrt_cnclsn_yn=='N' && !empty contractCnclsndlayLom}">
<div class="title_basic"><h5 class="ntitle05"><spring:message code="clm.page.msg.manage.conclDelayInf" /></h5></div>
<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
	<colgroup>
		<col width="14%" />
		<col width="25%" />
		<col width="14%" />
		<col width="19%" />
		<col width="14%" />
		<col width="14%" />
	</colgroup>
	<tr>
		<th><spring:message code="clm.page.msg.manage.conclDelayRsn" /></th>
		<td colspan=5><c:out value='${contractCnclsndlayLom.dlay_cause}'/></td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.preChgConclDate" /></th>
		<td colspan=5><c:out value='${contractCnclsndlayLom.chgeaft_cnclsn_plndday}'/></td>
	</tr>
</table>
</c:if>
</div>

</body>
</html>