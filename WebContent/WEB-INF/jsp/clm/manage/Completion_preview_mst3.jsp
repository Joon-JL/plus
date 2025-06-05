<!DOCTYPE html>
<%@page import="com.autonomy.aci.businessobjects.ResultList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="com.sec.common.util.ClmsDataUtil" %>

<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>


<%

// common.jsp include 부분 가져옴.
response.setDateHeader("Expires",0);
response.setHeader("Pragma","no-cache");
if(request.getProtocol().equals("HTTP/1.1")){
	response.setHeader("Cache-Control","no-cache");
}

String langCd = (String)session.getAttribute("secfw.session.language_flag");
String sysCd = (String)session.getAttribute("secfw.session.sys_cd");
String compCd = (String)session.getAttribute("secfw.session.auth_comp_cd");
/*
세션이 생성안된 상태에서 바로 이페이지를 호출시 에러가 발생하므로 그럴경우 처리
*/
if(langCd==null) langCd = "en";
if(sysCd==null) {
	sysCd = "clm";
	String f_c = (String)request.getAttribute("f");
	if(f_c != null && f_c.equals("c")) sysCd = "clm";
	else if(f_c != null && f_c.equals("l")) sysCd = "las";
}

//이미지 경로설정
String IMAGE = "/images/"+ sysCd.toLowerCase() + "/" + langCd;  // for multi lang

//CSS 경로 설정
String CSS 	 = "/style/"+ sysCd.toLowerCase() + "/" + langCd; // for multi lang
String 	confidential = "../../../images/clm/en/icon/pncacc.gif";
		
request.setAttribute("langCd",langCd);
if(langCd.equals("ko"))	request.setAttribute("langCd2","H");
else request.setAttribute("langCd2","F");

String serverIP="http://localhost:8080";
ListOrderedMap lomRq = (ListOrderedMap) request.getAttribute("lomRq"); //검토의뢰 정보

ListOrderedMap gerpDetail = (ListOrderedMap)request.getAttribute("gerpDetail");
%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
</head>
<body>
<form id="frm">
<div id="wrap">	
	<!-- container -->
	<div id="container">
		<!-- content -->
		<div id="content">
			<!-- content_in-->
			<div id="content_in">
			<div class="fR"><img src="<%=confidential%>"></div>
			<div class="title_basic">
				<h4><spring:message code="clm.page.msg.manage.reqReviewInfo" /></h4>
			</div>
			<table cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
				<col width="15%" />
				<col width="7%" />
				<col width="20%" />
				<col width="13%" />
				<col width="16%" />
				<col width="13%" />
				<col width="16%" />
				</colgroup>
				<tr>
					<th><spring:message code="las.page.field.contractmanager.consideration_d.cnsd_title" /></th>
				  	<td colspan="6"><c:out value='${lomRq.req_title}' default="" /></td>
				</tr>
				<tr class="lineAdd">
					<th><spring:message code="las.page.field.contractmanager.consideration_d.reqman_nm" /></th><!-- 의뢰자 -->
					<td colspan="2"><c:out value="${lomRq.reqman_nm}" /> / <c:out value="${lomRq.reqman_jikgup_nm}" /> / <c:out value="${lomRq.req_dept_nm}" /><br><c:out value="${lomRq.reqman_tel}" /></td>
					<th><spring:message code="las.page.field.contractmanager.consideration_d.req_dt" /></th><!-- 의뢰일시 -->
					<td><c:out value="${lomRq.req_dt}" /></td>
					<th><spring:message code="las.page.field.contractmanager.consideration_d.re_demndday" /></th><!-- 회신요청일 -->
					<td><c:out value="${lomRq.re_demndday}" /></td>
				</tr>
				<tr class="lineAdd">
					<th><spring:message code="las.page.field.contractmanager.consideration_d.relation_man" /></th>
				  	<td colspan="6">	
				  	<c:if test="${!empty lomRm}"> 
				  	<c:forEach var="list" items="${lomRm}" varStatus="status">
						<c:out value='${list.relation_man}' /> <!-- 계약_마스터_계약_ID -->
							<c:if test="${status.count > 0 && !status.last}">
								,&nbsp;&nbsp;
							</c:if>
							<c:if test="${status.count mod 2==0}">
								</br>
							</c:if>
					</c:forEach>
					</c:if>
				  	</td>
				</tr>
				<c:if test="${!empty lomRq.cnsd_demnd_cont}">
				<tr class="lineAdd" >
					<th><spring:message code="las.page.field.contractmanager.consideration_d.cnsd_demnd_cont" /></th>
					<td colspan="6">									 
					<c:out value='${lomRq.cnsd_demnd_cont}' escapeXml="false" /></td>
				</tr>
				</c:if>
				<% if("Y".equals(lomRq.get("plndbn_req_yn"))){ //최종본의뢰여부%>
				<!-- 최종본의뢰시 표시 함 ** -->
				<c:if test="${!empty lomRq.lastbn_chge_yn_nm}">
				<tr class="lineAdd" >
					<th><spring:message code="las.page.field.contractmanager.consideration_d.lastbn_chge_yn_nm" /> </th>
					<td colspan="6"><c:out value='${lomRq.lastbn_chge_yn_nm}' escapeXml="false" /></td>
				</tr>
				</c:if>
				<c:if test="${!empty lomRq.plndbn_chge_cont}">
				<tr class="lineAdd" >
					<th><spring:message code="las.page.field.contractmanager.consideration_d.plndbn_chge_cont" /> </th>
					<td colspan="6"><c:out value='${lomRq.plndbn_chge_cont}' escapeXml="false" /></td>
				</tr>
				</c:if>
				<!-- 최종본의뢰시 표시 함**  -->
				<% }%>
			</table>
	<!-- //top table -->
		
<%
	HashMap hm = new HashMap();
	ArrayList totalList	= (ArrayList)request.getAttribute("totalList");
	//for(int k=0; k < totalList.size(); k++){
		hm = (HashMap)totalList.get(0);	
		
		ListOrderedMap contractMstLom = (ListOrderedMap)hm.get("contractMstLom");
		ArrayList contractExeLom = (ArrayList)hm.get("contractExeLom");
		ArrayList contractSpclLom = (ArrayList)hm.get("contractSpclLom");
		ArrayList contractCnclsndlayLom = (ArrayList)hm.get("contractCnclsndlayLom");
		ArrayList contractRelationLom =  (ArrayList)hm.get("contractRelationLom");
		int relationListSize = (Integer)hm.get("relationListSize");
		
		ArrayList considerationSpecialList = (ArrayList)hm.get("considerationSpecialList");
		ArrayList consultationSpecialList = (ArrayList)hm.get("consultationSpecialList");
		int considerationSpecialSize = (Integer)hm.get("considerationSpecialSize");
		int consultationSpecialSize = (Integer)hm.get("consultationSpecialSize");
		
		int maxrm = (Integer)hm.get("maxrm");
		ArrayList review = (ArrayList)hm.get("review");
		ArrayList approve = (ArrayList)hm.get("approve");
		ArrayList sign = (ArrayList)hm.get("sign");
		ArrayList info = (ArrayList)hm.get("info");
		
		//close 를 위한 변수 설정
		ListOrderedMap close = (ListOrderedMap)hm.get("close");
		
		ArrayList resultAttachArrList = (ArrayList)hm.get("resultAttachArrList");
		ListOrderedMap tempLom = new ListOrderedMap();
		ArrayList listContractAttach 		 = new ArrayList();		//의뢰계약서
		ArrayList listEtcAttach		 		 = new ArrayList();		//의뢰기타
		ArrayList listEtc2Attach	 		 = new ArrayList();		//의뢰별첨
		ArrayList listCmpltAttach	 		 = new ArrayList();		//종료관리
		ArrayList listInfoAttach	 		 = new ArrayList();		//사전품의
		ArrayList listHisConsultCntrtAttach	 = new ArrayList();		//의뢰계약서
		ArrayList listHisConsultAppendAttach = new ArrayList();		//의뢰별첨
		ArrayList listHisConsultOtherAttach	 = new ArrayList();		//의뢰기타
		ArrayList listHisConsultOutLookAttach= new ArrayList();		//Outlook Sungwoo added 2014-09-01
		ArrayList listHisReplyCntrtAttach	 = new ArrayList();		//회신계약서
		ArrayList listHisReplyAppendAttach	 = new ArrayList();		//회신별첨
		ArrayList listHisReplyOtherAttach	 = new ArrayList();		//회신기타 Sungwoo added 2014-09-01
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
				} else if("hisConsultOutlook".equals((String)tempLom.get("filetype"))) {
					listHisConsultOutLookAttach.add(resultAttachArrList.get(j));
				} else if("hisReplyCntrt".equals((String)tempLom.get("filetype"))) {
					listHisReplyCntrtAttach.add(resultAttachArrList.get(j));
				} else if("hisReplyAppend".equals((String)tempLom.get("filetype"))) {
					listHisReplyAppendAttach.add(resultAttachArrList.get(j));
				} else if("hisReplyOthers".equals((String)tempLom.get("filetype"))) {
					listHisReplyOtherAttach.add(resultAttachArrList.get(j));
				} else if("hisApprove".equals((String)tempLom.get("filetype"))) {
					listHisApproveAttach.add(resultAttachArrList.get(j));
				} else if("hisSign".equals((String)tempLom.get("filetype"))) {
					listHisSignAttach.add(resultAttachArrList.get(j));
				}
			}
		}
%>
<div class="title_basic">
	<h4><spring:message code="clm.page.msg.manage.bscInfCont" /> </h4><!-- 계약기본정보 -->
</div>
<table id="contract-base-content" cellspacing="0" cellpadding="0" border="0" class="table-style01" style="margin-top:0;">
	<colgroup>
		<col width="15%" />
		<col width="17%" />
		<col width="19%" />
		<col width="14%" />
		<col width="12%" />
		<col width="12%" />
		<col width="11%" />
	</colgroup>
	<tr>
		<th><spring:message code="clm.page.msg.manage.contName" /></th><!-- 계약명 -->
		<td colspan="6">
			<%=contractMstLom.get("cntrt_nm")%>
		</td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.common.otherParty" /></th><!-- 계약상대방 -->
		<td colspan="2">
			<%=StringUtil.bvl(contractMstLom.get("cntrt_oppnt_nm"),"") %>
		</td>
		<th><spring:message code="clm.page.field.customer.registerNo" /></th><!-- 대표자명 -->
		<td class="last-td">
			<%=StringUtil.bvl(contractMstLom.get("cntrt_oppnt_ceo"),"") %>
		</td>
		<th>
			<!-- 2014-04-17 Kevin commented. -->
			<%-- <spring:message code="clm.page.field.customer.contractRequired" /> --%>
		</th>
		<td>
			<!-- 2014-04-17 Kevin commented. -->
			<%-- <%=contractMstLom.get("cntrt_oppnt_type_nm")%> --%>
		</td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.field.contract.basic.oppntnm"/></th><!-- 상대방담당자 -->
		<td colspan="2">
			<%=StringUtil.bvl(contractMstLom.get("cntrt_oppnt_respman"),"") %>
		</td>			
		<th><spring:message code="clm.page.field.contract.basic.oppnttelno"/></th><!-- 전화번호 -->
		<td>
			<%=StringUtil.bvl(contractMstLom.get("cntrt_oppnt_telno"),"") %>
		</td>
		<th><spring:message code="clm.page.field.contract.basic.oppntemail"/></th><!-- 이메일 -->
		<td>
			<%=StringUtil.bvl(contractMstLom.get("cntrt_oppnt_email"),"") %>
		</td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.contType" /></th><!-- 계약유형 -->
		<td colspan="6">
			<%=contractMstLom.get("biz_clsfcn_nm")%>/<%=contractMstLom.get("depth_clsfcn_nm")%>/<%=contractMstLom.get("cnclsnpurps_bigclsfcn_nm")%>/<%=contractMstLom.get("cnclsnpurps_midclsfcn_nm")%>
		</td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.contItm" /></th><!-- 계약대상 -->
		<td colspan="2">
			<%=contractMstLom.get("cntrt_trgt_nm")%>
		</td>
		<th><spring:message code="clm.page.msg.manage.contItmDtl" /></th><!-- 계약대상상세 -->
		<td colspan="3">
			<%=contractMstLom.get("cntrt_trgt_det")%>
		</td>
	</tr>
	<%
		if(!"".equals(StringUtil.bvl(contractMstLom.get("pshdbkgrnd_purps"), ""))){
	%>
	<tr>
		<th><spring:message code="clm.page.msg.manage.bg" /></th><!-- 추진목적 및 배경 -->
		<td colspan="6">
			<%=contractMstLom.get("pshdbkgrnd_purps")%>
		</td>
	</tr>
	<%
		}
	%>
	<%
		if(!"".equals(StringUtil.bvl(contractMstLom.get("antcptnefct"), ""))){
	%>
	<tr>
		<th><spring:message code="clm.page.msg.manage.hope" /></th>
		<td colspan="6">
			<%=contractMstLom.get("antcptnefct")%>
		</td>
	</tr>
	<%
		}
	%>
	<tr>
		<th rowspan="3"><spring:message code="clm.page.msg.common.attachment" /></th><!-- 첨부파일 -->
		<td><span class="blueD"><spring:message code="clm.page.msg.manage.contract" /></span></td>
		<td colspan="5">
<%		
	if(listContractAttach != null && listContractAttach.size() > 0) {
  	  tempLom = (ListOrderedMap)listContractAttach.get(0);
%>  	  
	<a href=<c:out value='${attachinfo}'/><%=tempLom.get("file_id")%>><%=tempLom.get("org_file_nm") %></a>
<%	
	} else { 
%>
	<spring:message code="clm.page.msg.manage.noAttach" />
<%	
	} 
%>
		</td>
	</tr>
	<tr>
		<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.manage.attachment" /></span></td><!-- 첨부파일 -->
		<td colspan="5">
<%		
	if(listEtcAttach != null && listEtcAttach.size() > 0) {
		for(int i=0; i<listEtcAttach.size(); i++){
  	  		tempLom = (ListOrderedMap)listEtcAttach.get(i);
%>  	  
	<a href=<c:out value='${attachinfo}'/><%=tempLom.get("file_id")%>><%=tempLom.get("org_file_nm") %></a></br>
<%		
		}
	} else { 
%>
	<spring:message code="clm.page.msg.manage.noAttach" />
<%	
	} 
%>
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
<%		
		}
	} else { 
%>
	<spring:message code="clm.page.msg.manage.noAttach" />
<%	
	} 
%>
		</td>
	</tr>
</table>


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
		<th><spring:message code='clm.page.field.contract.detail.period' /></th><!-- 계약기간 -->
		<td colspan="2"> 
			<%=contractMstLom.get("cntrtperiod_startday") %> <% if(!contractMstLom.get("cntrtperiod_startday").equals("")){ %>~<%} %> <%=contractMstLom.get("cntrtperiod_endday") %> 
		</td>	
		<th><spring:message code='clm.page.field.contract.detail.secret' /></th><!-- 비밀유지기간 -->
		<td colspan="3">
			<%=StringUtil.bvl(contractMstLom.get("secret_keepperiod"),"") %>
		</td>
	</tr>
	<tr>
		<th><spring:message code='clm.page.field.contract.detail.paygubun' /></th><!-- 지불수불 구분 -->
		<td colspan="2">
			<%=contractMstLom.get("payment_gbn_nm") %>
		</td>
		<th><spring:message code='clm.page.field.contract.detail.price' /></th>
		<td colspan="3">
			<%=contractMstLom.get("cntrt_amt") %>
		</td>
	</tr>
	<%
		if(!"".equals(StringUtil.bvl(contractMstLom.get("crrncy_unit"), ""))){
	%>
	<tr>
		<th><spring:message code='clm.page.field.contract.detail.money' /></th><!-- 계약금액 -->
		<td colspan="5">
			<%=contractMstLom.get("crrncy_unit") %>
		</td>
	</tr>
	<%
		}
	%>
	<%
		if(!"".equals(StringUtil.bvl(contractMstLom.get("cntrt_untprc_expl"), ""))){
	%>
	<tr>
		<th><spring:message code='clm.page.field.contract.detail.unitcont' /></th><!--  단가체결내역 -->
		<td colspan="5">
			<%=contractMstLom.get("cntrt_untprc_expl") %></br>
		</td>
	</tr>
	<%
		}
	%>
	<%
		if(!"".equals(StringUtil.bvl(contractMstLom.get("payment_cond"), ""))){
	%>
	<tr>
		<th><spring:message code='clm.page.field.contract.detail.payment' /></th>
		<td colspan="5">
			<%=contractMstLom.get("payment_cond") %>
		</td>
	</tr>
	<%
		}
	%>
	<%
	for(int i=0; i < considerationSpecialList.size(); i++){
		ListOrderedMap tmpLom = (ListOrderedMap) considerationSpecialList.get(i);
		if(!"".equals(StringUtil.bvl(tmpLom.get("attr_value"), ""))){
	%>
		
	<tr>	
		<th><%=tmpLom.get("attr_nm")%></th>
		<td class="tal_lineL" colspan="5"><%=StringUtil.bvl(tmpLom.get("attr_value"),"") %></td>
	</tr>	
	<%
		}
	}	
	%>
	
	<%
	for(int i=0; i < consultationSpecialList.size(); i++){
		ListOrderedMap tmpLom = (ListOrderedMap) consultationSpecialList.get(i);
		if(!"".equals(StringUtil.bvl(tmpLom.get("attr_value"), ""))){
	%>
	<tr>	
		<th><%=tmpLom.get("attr_nm")%></th>
		<td class="tal_lineL" colspan="5"><%=StringUtil.bvl(tmpLom.get("attr_value"),"") %></td>
	</tr>
	<%
		}
	}
	%>

	<%
	if(!"C04301".equals(StringUtil.bvl(contractMstLom.get("cnsd_status"), ""))){
		if(!"".equals(StringUtil.bvl(contractMstLom.get("auto_rnew_yn"), ""))){
	%>
	<tr>
		<th><spring:message code="clm.page.field.contract.consultation.detail.autorenew" /></th><!-- 자동연장여부 -->
		<td colspan="5">
		<%
		if("Y".equals(contractMstLom.get("auto_rnew_yn"))){
		%>
		Yes
		<%				
		}
		%>
		<%
		if("N".equals(contractMstLom.get("auto_rnew_yn"))){
		%>
		No
		<%				
		}
		%>
		</td>
	</tr>
	<%				
	}
	%>
	<%
		if(!"".equals(StringUtil.bvl(contractMstLom.get("oblgt_lmt"), ""))){
	%>
	<tr>
		<th><spring:message code="clm.page.field.contract.consultation.detail.obligationlimit"/></th><!-- 배상책임한도 -->
	  	<td colspan="5">
	  		<c:out value='${contractMstLom.oblgt_lmt}'/>
	  		<%=StringUtil.bvl(contractMstLom.get("oblgt_lmt"),"") %>
      	</td>
	</tr>
	<%				
	}
	%>
	<%
		if(!"".equals(StringUtil.bvl(contractMstLom.get("spcl_cond"), ""))){
	%>
	<tr>
		<th><spring:message code="clm.page.field.contract.consultation.detail.specialcondition"/></th><!-- 기타특약사항 -->
		<td colspan="5">
			<%=StringUtil.bvl(contractMstLom.get("spcl_cond"),"") %>
		</td>
	</tr>
	<%
	}
	%>
	<tr>
		<th><spring:message code="clm.page.field.contract.detail.loac"/></th><!-- 준거법 -->
		<td colspan="2">
			<%=StringUtil.bvl(contractMstLom.get("loac"),"") %>
		</td>
		<th><spring:message code="clm.page.field.contract.detail.loacdetail"/></th><!-- 준거법 상세 -->
		<td colspan="2">
			<%=contractMstLom.get("loac_etc") %>
		</td>
		
	</tr>
	<tr>
		<th><spring:message code="clm.page.field.contract.detail.solutionmethod"/></th><!-- 분쟁해결방법 -->
		<td colspan="5">
			<%=StringUtil.bvl(contractMstLom.get("dspt_resolt_mthd_det"),"") %>
		</td>
		
	</tr>
	<%} %>
</table>
<% if(!contractMstLom.get("prcs_depth").equals("C02507")){ %>
<!-- 2014-05-01 Kevin added. GERP detail -->
<div class="title_basic">
	<h4><spring:message code="clm.page.field.contract.gerp.gerpInformation"/></h4>
</div>
 <table id="tb_grp_info" cellspacing="0" cellpadding="0" border="0" class="table-style01">
  <colgroup>
  	<col width="22%"/>
  	<col width="14%"/>
  	<col width="18%"/>
  	<col width="14%"/>
  	<col width="18%"/>
  	<col width="14%"/>
  </colgroup>
   	<tr>
        <th><spring:message code="clm.page.msg.common.otherParty"/></th>
        <td colspan="3"><%=StringUtil.bvl((String)gerpDetail.get("CUSTOMER_NM1"),"") %></td>
        <th><spring:message code="clm.page.field.customer.gerpCode"/></th>
        <td><%=StringUtil.bvl((String)gerpDetail.get("GERP_CD"),"") %></td>
    </tr>
    <tr>
        <th><spring:message code="clm.page.field.customer.IsContractRequired2"/></th>
        <td><%=StringUtil.bvl(((String)gerpDetail.get("MANDATORY") == "Y" ? "Yes" : "No"),"") %></td>
        <th><spring:message code="clm.page.field.customer.vendorType"/></th>
        <td><%=StringUtil.bvl((String)gerpDetail.get("VENDOR_TYPE_DESC"),"") %></td>
        <th><spring:message code="clm.page.field.contract.gerp.headDivisionCode"/></th>
        <td><%=StringUtil.bvl((String)gerpDetail.get("GERP_DIVISION"),"") %></td>
    </tr>
    <tr>
        <th><spring:message code="clm.page.field.contract.gerp.costType"/></th>
        <td><%=StringUtil.bvl((String)gerpDetail.get("COST_TYPE_DESC"),"") %></td>
        <th><spring:message code="clm.page.field.contract.gerp.contractClassification"/></th>
          <td><%=StringUtil.bvl((String)gerpDetail.get("CONTRACT_TYPE_DESC"),"") %></td>
          <th></th>
          <td></td>
      </tr>
</table>
<% } %>
<div class="title_basic">
	<h4><spring:message code="clm.page.msg.manage.preRevInf" /></h4><!-- 사전검토정보 -->
</div>
<table id="tab_contents_sub_conts2" cellspacing="0" cellpadding="0" border="0" class="table-style01">
	<colgroup>
		<col width="12%" />
		<col width="30%" />
		<col width="13%" />
		<col width="16%" />
		<col width="13%" />
		<col width="16%" />  
	</colgroup>
	<tr>
		<th><spring:message code="clm.page.msg.manage.preApprProp" /></th><!-- 사전승인발의자 -->
		<td colspan="3">
		<%
		if(!"".equals(StringUtil.bvl(contractMstLom.get("bfhdcstn_mtnman_nm"),""))){
		%>
			<%=contractMstLom.get("bfhdcstn_mtnman_nm") %>/<%=contractMstLom.get("bfhdcstn_mtnman_jikgup_nm") %>/<%=contractMstLom.get("bfhdcstn_mtn_dept_nm") %>
		<%				
		}
		%>
		</td>
		<th><spring:message code="clm.page.msg.manage.apprType" /></th><!-- 승인방식 -->
		<td>
			<%=contractMstLom.get("bfhdcstn_apbt_mthd_nm") %>
		</td>
	</tr>
	<tr>
		<th>E-mail</th>
		<td colspan="5">						    
		    <%=StringUtil.bvl(contractMstLom.get("bfhdcstn_mtnman_email"),"") %>
		</td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.preApproval" /><!-- 사전승인 --><br /><spring:message code="clm.page.msg.manage.apprPerInf" /><!-- 승인자정보 --></th>
		<td colspan="5">
			<!-- 테이블 안에 테이블 -->
			<table cellspacing="0" cellpadding="0" border="0" class="table-style_sub02">
				<colgroup>
					<col width="25%" />
					<col width="25%" />
					<col width="25%" />
					<col width="25%" />
				</colgroup>
				<thead>
					<tr>
						<th><spring:message code="clm.page.msg.manage.name" /></th>
						<th><spring:message code="clm.page.msg.manage.level" /></th>
						<th><spring:message code="clm.page.msg.common.department" /></th>
						<th><spring:message code="clm.page.msg.manage.apprDate" /></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><%=contractMstLom.get("bfhdcstn_apbtman_nm") %></td>
						<td><%=contractMstLom.get("bfhdcstn_apbtman_jikgup_nm") %></td>
						<td><%=contractMstLom.get("bfhdcstn_apbt_dept_nm") %></td>
						<td><%=contractMstLom.get("bfhdcstn_apbtday") %></td>
					</tr>
				</tbody>
			</table>
			<!-- //테이블 안에 테이블 -->
		</td>
	</tr>
</table>		


<div class="title_basic">
	<h4><spring:message code="clm.page.msg.manage.relContInf" /><!-- 연관계약정보 --></h4>
</div>
<table id="contract-relation-content" cellspacing="0" cellpadding="0" border="0" class="table-style01" style="display:">
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
	<%
	if(relationListSize > 0){
		for(int i=0; i < contractRelationLom.size(); i++){
			ListOrderedMap tmpLom = (ListOrderedMap) contractRelationLom.get(i);
	%>
	<tr id="trRelationContractCont">
		<td><%=tmpLom.get("rel_type_nm")%></td>
		<td><%=tmpLom.get("relation_cntrt_nm")%></a>
		<td><%=tmpLom.get("rel_defn")%></td>
		<td><%=tmpLom.get("expl")%></td>
	</tr>			
	<%			
		}
	}else{
	%>
	<tr id="trRelationContractCont"><td class="tC" colspan="4"><spring:message code='clm.msg.succ.noResult'/></td></tr>
	<%	
	}
	%>	
</table>	

	<!-- 이력정보 -->
	<div class="title_basic">
		<h4><spring:message code="clm.page.msg.manage.revHistory" /> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'contract-reviewHisView');"/></h4>
	</div>
	<table cellspacing="0" cellpadding="0" border="0" class="table-style01" id="contract-reviewHisView">
		<colgroup>
			<col width="66%" />
			<col width="10%" />
			<col width="24%" />
		</colgroup>
		<tr>
			<th class="tC"><spring:message code="clm.page.msg.manage.reqBrkd" /></th>
			<th class="tC"><spring:message code="clm.page.msg.manage.date" /></th>
			<th class="tC"><spring:message code="clm.page.msg.manage.reqRevPer" /></th>
		</tr>
<%
if(review.size() > 0){
	for(int idx=0;idx < review.size();idx++){
		ListOrderedMap lom = (ListOrderedMap)review.get(idx);
%>
		<tr>
			<td class="tL">
				<span class="mR5"><img src="<%=IMAGE%>/icon/ico_plus.gif" alt="show" /></span>
				<%=lom.get("cr_flan_nm") %>
				<%=StringUtil.convertHtmlTochars((String)lom.get("title"))%>
			</td>
			<td class="tC"><%=StringUtil.bvl((String)lom.get("reg_dt"),"")%></td>
			<td><%=lom.get("nm") %></td>
		</tr>
		<!-- 테이블안에 테이블 -->
		<tr class="Nocol" id="tr_show">
			<td colspan="3">
				<table class="table-style_sub02">
					<colgroup>
						<col width='13%'/>
						<col width='10%'/>
						<col width='77%'/>
					</colgroup>
			<%
			if("CONSULT".equals(lom.get("cr_flag"))){
			%>
					<tr class="slide-target02 slide-area">
						<th><spring:message code="clm.page.msg.manage.reviewReqCont" /></th>
						<td colspan="2"><%=StringUtil.convertEnterToBR((String)lom.get("cont")) %></td>
					</tr>
					<!--  2012.03.20 검토본변경여부, 변경내역 및 사유 추가 added by hanjihoon -->			   
				<%
				if("Y".equals(lom.get("plndbn_req_yn"))){
					if(!"".equals(StringUtil.bvl(lom.get("lastbn_chge_yn_nm"), ""))){
				%>
					<tr class="slide-target02 slide-area">
						<th><spring:message code="clm.page.msg.manage.chgRevVerYn" /></th>
						<td colspan="2"><%=lom.get("lastbn_chge_yn_nm") %></td>
					</tr>
				<%
					}
					if(!"".equals(StringUtil.bvl(lom.get("plndbn_chge_cont"), ""))){
				%>
					<tr class="slide-target02 slide-area">
						<th><spring:message code="clm.page.msg.manage.rsnBrkChange" /></th>
						<td colspan="2"><%=StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String)lom.get("plndbn_chge_cont"))) %></td>
					</tr>
				<%
					}
				}
				%>
				<tr class="slide-target02 slide-area">
					<th rowspan="4"><spring:message code="clm.page.msg.common.attachment" /></th>
				</tr>
				<%
				if(listHisConsultCntrtAttach != null && listHisConsultCntrtAttach.size() > 0) {
				%>
					<tr class="slide-target02 slide-area">
						<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.manage.contract" /></span></td>
						<td>
						<%
							for(int i=0; i<listHisConsultCntrtAttach.size(); i++){
								tempLom = (ListOrderedMap)listHisConsultCntrtAttach.get(i);
								if( (lom.get("cnsdreq_id")).equals(tempLom.get("cnsdreq_id")) ){
								%>
								<img src="/images/clm/en/icon/ico_save_w.gif"/> <a href=<%="javascript:downloadFile(\'"+tempLom.get("file_id")+"\');"%>><%=tempLom.get("org_file_nm") %></a><br/>
								<%		
								}
							}
							%>
						</td>
					</tr>
				<%
				}

				if(listHisConsultAppendAttach != null && listHisConsultAppendAttach.size() > 0) {
				%>
					<tr class="slide-target02 slide-area">
						<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.manage.attachment_br" /></span></td>
						<td>
						<%		
							for(int i=0; i<listHisConsultAppendAttach.size(); i++){
								tempLom = (ListOrderedMap)listHisConsultAppendAttach.get(i);
								if( (lom.get("cnsdreq_id")).equals(tempLom.get("cnsdreq_id")) ){
								%>
									<img src="/images/clm/en/icon/ico_save_w.gif"/> <a href=<%="javascript:downloadFile(\'"+tempLom.get("file_id")+"\');"%>><%=tempLom.get("org_file_nm") %></a><br/>
								<%	
								}
							}
							%>
						</td>
					</tr>
				<%
				} 

				if(listHisConsultOtherAttach != null && listHisConsultOtherAttach.size() > 0) {
				%>
					<tr class="slide-target02 slide-area">
						<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.common.etc" /></span></td>
						<td>
							<%		
							for(int i=0; i<listHisConsultOtherAttach.size();i++){
								tempLom = (ListOrderedMap)listHisConsultOtherAttach.get(i);
								if( (lom.get("cnsdreq_id")).equals(tempLom.get("cnsdreq_id")) ){
								%>
									<img src="/images/clm/en/icon/ico_save_w.gif"/> <a href=<%="javascript:downloadFile(\'"+tempLom.get("file_id")+"\');"%>><%=tempLom.get("org_file_nm") %></a><br/>
								<%	
								}
							}
							%>
						</td>
					</tr>
				<%
				}
				
				if(listHisConsultOutLookAttach != null && listHisConsultOutLookAttach.size() > 0) {
				%>
	 				<!-- Outlook attachment Sungwoo replaced 2014-09-01.-->
	 				<tr class="slide-target02 slide-area">
	 					<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.common.outlook" /></span></td>
	 					<td>
	 						<%		
							for(int i=0; i<listHisConsultOutLookAttach.size();i++){
								tempLom = (ListOrderedMap)listHisConsultOutLookAttach.get(i);
								if( (lom.get("cnsdreq_id")).equals(tempLom.get("cnsdreq_id")) ){
								%>
									<img src="/images/clm/en/icon/ico_save_w.gif"/> <a href=<%="javascript:downloadFile(\'"+tempLom.get("file_id")+"\');"%>><%=tempLom.get("org_file_nm") %></a><br/>
								<%	
								}
							}
							%>
						</td>
					</tr>
				<%
				}
			}else if("REPLY__".equals(lom.get("cr_flag"))){
				%>
					<tr class="slide-target02 slide-area">
						<th><spring:message code="clm.page.msg.manage.reviewOpinion" /></th>
						<td colspan="2"><%=(String)lom.get("cont")%></td>
					</tr>
				<%
				if(!"".equals(((String)lom.get("cnsd_apbt")).trim())){// 그룹장 의견 
				%>
				<c:if test="${command.top_role ne 'ETC' }">
					<tr class="slide-target02 slide-area">
						<th><spring:message code="clm.page.msg.manage.grpMasterOp" /></th>
						<td colspan="2"><%=StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String)lom.get("cnsd_apbt"))) %></td>
					</tr>
				</c:if>
				<%
				}
				%>
				<tr class="slide-target02 slide-area">
					<th rowspan="4"><spring:message code="clm.page.msg.common.attachment" /></th>
				</tr>
				<%
				if(listHisReplyCntrtAttach != null && listHisReplyCntrtAttach.size() > 0) {
				%>
					<tr class="slide-target02 slide-area">
						<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.manage.contract" /></span></td>
						<td><div style='clear:both;'></div>
							<%
							for(int i=0; i<listHisReplyCntrtAttach.size(); i++){
								tempLom = (ListOrderedMap)listHisReplyCntrtAttach.get(i);
								if( (lom.get("cnsdreq_id")).equals(tempLom.get("cnsdreq_id")) ){
							%>
								<img src="/images/clm/en/icon/ico_save_w.gif"/> <a href=<%="javascript:downloadFile(\'"+tempLom.get("file_id")+"\');"%>><%=tempLom.get("org_file_nm") %></a><br/>
							<%
								}
							}
							%>
						</td>
					</tr>
				<%
				}
				
				if(listHisReplyAppendAttach != null && listHisReplyAppendAttach.size() > 0) {
				%>
					<tr class="slide-target02 slide-area">
						<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.manage.attachment_br" /></span></td>
						<td>
							<%
							for(int i=0; i<listHisReplyAppendAttach.size(); i++){
								tempLom = (ListOrderedMap)listHisReplyAppendAttach.get(i);
								if( (lom.get("cnsdreq_id")).equals(tempLom.get("cnsdreq_id")) ){
							%>
								<img src="/images/clm/en/icon/ico_save_w.gif"/> <a href=<%="javascript:downloadFile(\'"+tempLom.get("file_id")+"\');"%>><%=tempLom.get("org_file_nm") %></a><br/>
							<%	
								}
							}
							%>
						</td>
					</tr>
				<%
				}

				if(listHisReplyOtherAttach != null && listHisReplyOtherAttach.size() > 0) {
				%>
					<tr class="slide-target02 slide-area">
						<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.common.etc" /></span></td>
						<td>
						<%
						for(int i=0; i<listHisReplyOtherAttach.size(); i++){
							tempLom = (ListOrderedMap)listHisReplyOtherAttach.get(i);
							if( (lom.get("cnsdreq_id")).equals(tempLom.get("cnsdreq_id")) ){
						%>
							<img src="/images/clm/en/icon/ico_save_w.gif"/> <a href=<%="javascript:downloadFile(\'"+tempLom.get("file_id")+"\');"%>><%=tempLom.get("org_file_nm") %></a><br/>
						<%
							}
						}
						%>
						</td>
					</tr>
				<%
				}
				}else if("RESHOLD".equals(lom.get("cr_flag")) && !lom.get("cont").equals("")){
				%>
					<tr class="slide-target02 slide-area">
						<th><spring:message code="clm.page.msg.manage.rsnDelay" /></th>
						<td><%=StringUtil.convertEnterToBR(StringUtil.convertHtmlTochars((String)lom.get("cont")))%></td>
					</tr>
				<%
				}else if("ZADMIN_REPLY".equals(lom.get("cr_flag")) && !lom.get("cont").equals("")){//ZADMIN_REPLY
				%>
					<tr class="slide-target02 slide-area">
						<th><spring:message code="las.page.field.contractManager.pstpnRs"/><!-- 보류사유 --></th>
						<td>
							<%=(String)lom.get("cont") %>
						</td>
					</tr>
				<% }%>	
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
	</table>

			<!-- Approval History -->
			<div class="title_basic">
				<h4><spring:message code="clm.page.msg.manage.apprHist" /> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'contract-approveHisView');"/></h4>
			</div>
			<table cellspacing="0" cellpadding="0" border="0" class="table-style01" id="contract-approveHisView">
				<colgroup>
					<col width="57%" />
					<col width="10%" />
					<col width="8%" />
					<col width="25%" />
				</colgroup>
				<tr>
					<th class="tC"><spring:message code="clm.page.msg.manage.itmName" /></th>
					<th class="tC"><spring:message code="clm.page.msg.manage.status" /></th>
					<th class="tC"><spring:message code="clm.page.msg.manage.date" /></th>
					<th class="tC"><spring:message code="clm.page.msg.manage.apprPer" /></th>
					</tr>
			<%
			if(info.size() > 0){
				for(int idx=0;idx < info.size();idx++){
					ListOrderedMap lom = (ListOrderedMap)info.get(idx);
			%>
					<tr>
						<td class="tL">
							<span class="mR5"><img src="<%=IMAGE%>/icon/ico_plus.gif" alt="show01" /></span>
							<spring:message code="clm.page.msg.manage.preApprInf" />
							</td>
							<td><%=lom.get("bfhdcstn_apbt_mthd_nm") %></td>
							<td class="tC"><%=lom.get("bfhdcstn_apbtday") %></td>
							<td><%=lom.get("bfhdcstn_apbtman_nm") %>/<%=lom.get("bfhdcstn_apbtman_jikgup_nm") %>/<%=lom.get("bfhdcstn_apbt_dept_nm") %></td>
						</tr>
					<!-- 테이블안에 테이블 -->
					<tr class="Nocol" id="tr_show01" >
						<td colspan="4">
							<table class="table-style_sub02">
								<colgroup>
									<col width="13%" />
									<col width="87%" /> <!-- width size 변경 신성우 2014-04-24 -->
								</colgroup>
								<tr class="Nocol">
									<th>
										<spring:message code="clm.page.msg.manage.proposer" />
									</th>
									<td>
										<%=lom.get("bfhdcstn_mtnman_nm") %>
									</td>
								</tr>
								<%
								if(listInfoAttach != null && listInfoAttach.size() > 0) {
								%>
								<tr class="Nocol">
									<th><spring:message code="clm.page.msg.common.attachment" /></th>
									<td>
										<%
										for(int i=0; i<listInfoAttach.size(); i++){
											tempLom = (ListOrderedMap)listInfoAttach.get(i);
										%>
											<img src="/images/clm/en/icon/ico_save_w.gif"/> <a href=<%="javascript:downloadFile(\'"+tempLom.get("file_id")+"\');"%>><%=tempLom.get("org_file_nm") %></a><br/>
										<%
										}
										%>
									</td>
								</tr>
								<%
								}
								%>
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
				boolean bFirstLine  = false; //현재 Mis_id의 첫번째 라인여부
				String sMemMis_id   = "" ;  //현재 Mis_id
				int iMemAllMember   = 0 ;   //현재 결재건(MIS_ID)에 묶인  기안,합의,승인자의 갯수
				int iMemRow		 = 0 ;   //현재 iMemAllMember의 Row위치
				String sNextMis_id  = "" ;  //다음  Mis_id
				int iFileRowIndex   = 0 ;   //파일 index  
				String sApprovalName= "";   //승인자명 
				String sStatus	  = "";
				String sStatusCd		= "";
				ListOrderedMap LastApprover = null;
				//Sungwoo replaced 2014-09-16 script 호출형태에서 변경처리
				if(approve.size() > 0){
					LastApprover = ((ListOrderedMap)approve.get(approve.size()-1));
					sApprovalName = (String)LastApprover.get("user_name") + "/" + (String)LastApprover.get("duty") + "/" + (String)LastApprover.get("dept_name");
				}
				
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
						<td><%=sApprovalName %></td>
					</tr>
					<!-- 테이블안에 테이블 -->
					<tr class="Nocol" id="tr_show01">
						<td colspan="4">
							<table class="table-style_sub02">
								<colgroup>
									<!-- Sungwoo 2014-05-14 Approve 상태 추가에 따른 width 재조정 -->
									<col width="13%" />
									<col width="57%"/>
									<col width="15%"/>
									<col width="15%"/>
								</colgroup>
								<tr class="Nocol">
									<th rowspan='<%=lom.get("cntrt_id_cnt")%>'><spring:message code="clm.page.msg.manage.approver" /></th>
									<td>
										<strong>[<%=lom.get("cd_nm") %>]</strong> <%=lom.get("user_name") %>/<%=lom.get("duty") %>/<%=lom.get("dept_name") %>/<%=lom.get("group_name") %>
									</td>
									<!-- Sungwoo 2014-05-14 Approve 상태 추가 -->
									<td><%=lom.get("prg_status")%></td>
									<td><%=lom.get("approved")%></td>
								</tr>
			<%
					}else if(! bFirstLine){
						sStatus = (String)lom.get("cd_nm");
						sStatusCd = (String)lom.get("cd");
			%>
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
								if(listHisApproveAttach != null && listHisApproveAttach.size() > 0) {
			%>
								<tr class="slide-target02 slide-area">
									<th><span class="tal_lineL"><spring:message code="clm.page.msg.common.attachment" /></span></th>
									<!-- Sungwoo 2014-05-14 Approve 상태 변경으로 인한 colspan 변경 -->
									<td colspan="3">
										<%
										for(int i=0; i<listHisApproveAttach.size(); i++){
											tempLom = (ListOrderedMap)listHisApproveAttach.get(i);
										%>
											<img src="/images/clm/en/icon/ico_save_w.gif"/> <a href=<%="javascript:downloadFile(\'"+tempLom.get("file_id")+"\');"%>><%=tempLom.get("org_file_nm") %></a><br/>
										<%
										}
										%>
									</td>
								</tr>
								<%
								}
								%>
							</table>
						</td>
					</tr>
			<%	
					}
				}
			}
			%>
			</table>
			<!-- //Approval History -->

<%
if(close != null){
%>

<div class="title_basic">
  <h4><spring:message code="clm.page.msg.manage.closeHist" /></h4>
</div>
<table cellspacing="0" cellpadding="0" border="0" class="list_basic" id="contract-approveHisView">
	
	<colgroup>
		 <col width="15%" />
		 <col width="85%" />
	</colgroup>
	<tbody>
		<tr>
			<th><spring:message code="clm.field.signmng.prcman"/><!-- 처리자 --></th>
			<td><%=close.get("reg_nm") %></td>
		</tr> 
		<tr>
			<th><spring:message code="clm.field.signmng.prcday" /><!-- 처리일 --></th>
			<td><%=close.get("reg_dt") %></td>
		</tr>
		<tr>
			<th><spring:message code="las.page.field.cal.Notes" /><!-- 내용 --></th>
			<td><%=StringUtil.bvl((String)close.get("close_cont"),"") %></td>
		</tr>
	</tbody>
</table>
<%
}
%>

</div>
</div>
</div>
</div>

<div id='footer'>
	<address>
    	<img src='../../../images/las/ko/common/logo_samsung.gif'> ⓒ SAMSUNG<!--<span class='contactus'><span>Contact Us</span> : selmsplus@samsung.com--></span>
  	</address>
</div>
</form>
</body>
</html>