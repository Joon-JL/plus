<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.DateUtil"%>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<%
	ListOrderedMap contractLom = (ListOrderedMap)request.getAttribute("contractLom");
%>
<script src="/script/func/ConsiderationFunc.js"></script><!-- 2014-07-28 Kevin added -->
<script>
	$(document).ready(
		function() {
			if ('<c:out value='${contractLom.org_hdovman_id}'/>' == '' && '<c:out value='${contractCommand.page_gbn}'/>'=='modify') {
				frm.org_hdovman_nm.value = '<c:out value='${contractLom.cntrt_respman_nm}'/>';
				frm.org_hdovman_search_nm.value = '<c:out value='${contractLom.cntrt_respman_nm}'/>';
				frm.org_hdovman_id.value = '<c:out value='${contractLom.cntrt_respman_id}'/>';
				frm.org_hdovman_jikgup_nm.value = '<c:out value='${contractLom.cntrt_respman_jikgup_nm}'/>';
				frm.org_hdov_dept_nm.value = '<c:out value='${contractLom.cntrt_resp_dept_nm}'/>';
			}
			
			// 2014-07-28 Kevin added.
			// Following codes retrive step/status information and show them.
			var considerationFunc = new ConsiderationFunc($);
			considerationFunc.getStepStatusInfoPerCnsdreqid($("#cnsdreq_id").val(), function(data){
				$("#spStep").text(data.step);
				$("#spStatus").text(data.status+" "+data.status_depth);
			});

			// 2014-04-15 Kevin Added.
		    // GERP readonly iframe load. 
		    frm.target          = "iframeGERP";
		    frm.action          = "<c:url value='/clm/manage/consideration.do' />";
		    frm.gerpPageType.value = "R";		// detail readonly
		    frm.method.value    = "forwardGERPDetail";
			frm.submit();	
	});
</script>

<!-- toptable-->
<input type="hidden" name="reg_operdiv" id="reg_operdiv" value="<c:out value='${contractLom.reg_operdiv}'/>" />
<input type="hidden" name="depth_status" id="depth_status" value="<c:out value='${contractLom.depth_status}'/>" />
<input type="hidden" name="req_title" id="req_title" value="<c:out value='${contractReqLom.req_title}' />" />
<input type="hidden" name="reqman_id" id="reqman_id" value="<c:out value='${contractReqLom.reqman_id}' />" />
<input type="hidden" name="dmstfrgn_gbn" id="dmstfrgn_gbn" value="<c:out value='${contractReqLom.dmstfrgn_gbn}'/>" />
<!-- 2014-04-15 Kevin added. GERP 관련 페이지 형태 구분.(R/I) -->
<input type="hidden" name="gerpPageType" id="gerpPageType" />

<div class='title_basic3'><spring:message code="clm.page.msg.manage.bscInf" /></div>
<table class="table-style01">
	<colgroup>
		<col width="16%" />
		<col width="17%" />
		<col width="16%" />
		<col width="21%" />
		<col width="12%" />
		<col width="18%" />
	</colgroup>
	<tr>
		<th><spring:message code="clm.page.msg.manage.reqName" /></th>
		<td colspan="5"><c:out value="${contractReqLom.req_title}" escapeXml="false"/></td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.contName" /></th>
		<td colspan='3'><c:out value="${contractReqLom.cntrt_nm}" /></td>
		<th><spring:message code="clm.page.msg.manage.contId" /></th>
		<td><c:out value="${contractReqLom.cntrt_no}" />
		<c:if test="${not empty contractReqLom.tnc_no}">
			<span style='color:#A9B4BC;'>(<c:out value="${contractReqLom.tnc_no}" />)</span>
		</c:if>
		</td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.reqPerson" /></th>
		<td><c:out value="${contractReqLom.reqman_nm}" />/<c:out value="${contractReqLom.reqman_jikgup_nm}" />/<c:out value="${contractReqLom.req_dept_nm}" /></td>
		<th><spring:message code="clm.page.msg.manage.chrgPerson" /></th>
		<td><c:out value="${contractReqLom.cntrt_respman_nm}" />/<c:out value="${contractLom.cntrt_respman_jikgup_nm}" />/<c:out value="${contractLom.cntrt_resp_dept_nm}" /></td>
		<th><spring:message code="clm.page.msg.manage.reviewer" /></th>
		<td><c:out value="${contractReqLom.cnsdmans}" escapeXml="false" /></td>
	</tr>
	<!-- 2014-07-28 Kevin added. Step / Status -->
	<tr class="lineAdd">
		<th class="borTz02"><spring:message code="clm.page.field.manageCommon.step" /></th>
		<td><span id="spStep"></span></td>
		<th><spring:message code="clm.page.field.manageCommon.status" /></th>
		<td colspan="3"><span id="spStatus"></span></td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.common.otherParty" /></th>
		<td>
			<input type="hidden" id="cntrt_oppnt_nm" name="cntrt_oppnt_nm" value="<c:out value="${contractLom.cntrt_oppnt_nm}" />" />
			<a href="javascript:customerPop2('<c:out value="${contractLom.cntrt_oppnt_cd}" />', '<c:out value="${contractLom.cntrt_oppnt_cd}" />');"><c:out value="${contractLom.cntrt_oppnt_nm}" /> </a></td>
		<th><spring:message code="clm.page.field.customer.registerNo" /></th>
		<td><c:out value="${contractLom.cntrt_oppnt_rprsntman}" escapeXml="false"/></td>
		<th></th>
		<td></td>
	</tr>
	<tr>
        <th><spring:message code="clm.page.msg.manage.top30Cus" /></th>
        <td><c:out value="${contractLom.top_30_cus_nm}"  escapeXml="false"/></td>
        <th><spring:message code="clm.page.msg.manage.relatedPrd" /></th>
		<td><c:out value="${contractLom.related_products_nm}"  escapeXml="false"/></td>
		<th><spring:message code="clm.page.msg.manage.srcContDraft" /></th>
		<td><c:out value="${contractLom.cont_draft_nm}" escapeXml="false"/></td>
    </tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.contItm" /></th>
		<td><c:out value="${contractLom.cntrt_trgt_nm}" /></td>
		<th><spring:message code="clm.page.msg.manage.contItmDtl" /></th>
		<td colspan="3"><c:out value="${contractLom.cntrt_trgt_det}" escapeXml="false"/>
		</td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.contType" /></th>
		<td colspan="5"><c:out value='${contractLom.biz_clsfcn_nm}' />/<c:out
				value='${contractLom.depth_clsfcn_nm}' />/<c:out
				value='${contractLom.cnclsnpurps_bigclsfcn_nm}' />/<c:out
				value='${contractLom.cnclsnpurps_midclsfcn_nm}' /></td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.contPeriod" /></th>
		<td><c:out
				value="${contractLom.cntrtperiod_startday}" /> ~ <c:out
				value="${contractLom.cntrtperiod_endday}" /></td>
		<th><spring:message code="clm.page.msg.manage.autoExpYn" /></th>
		<td>
		<c:choose>
			<c:when test="${contractLom.auto_rnew_yn=='Y'}">
				<input type="radio" name="auto_rnew_yn" id="auto_rnew_yn" class="radio" value="Y" checked disabled="disabled">Yes</input>
				<input type="radio" name="auto_rnew_yn" id="auto_rnew_yn" class="radio" value="N" disabled="disabled">No</input>
			</c:when>
			<c:otherwise>					
				<input type="radio" name="auto_rnew_yn" id="auto_rnew_yn" class="radio" value="Y" disabled="disabled">Yes</input>
				<input type="radio" name="auto_rnew_yn" id="auto_rnew_yn" class="radio" value="N" checked disabled="disabled">No</input>	
			</c:otherwise>
		</c:choose>
		</td>		
		<th><spring:message code="clm.page.msg.manage.sendRcvDiv" /></th>
		<td><c:out value="${contractLom.payment_gbn_nm}"
				escapeXml="false" /></td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.contAmt" /></th>
		<c:if test="${contractLom.cntrt_untprc_expl}"></c:if>
		<td colspan="3"><c:out value="${contractLom.cntrt_amt}" />
		</td>
		<th><spring:message code="clm.page.msg.manage.curr" /></th>
		<td><c:out value="${contractLom.crrncy_unit}" /></td>
	</tr>
	<c:if test="${contractLom.cntrt_untprc_expl}">
		<tr>
			<th><spring:message code="clm.page.msg.manage.contUnitPriceSum" /></th>
			<td colspan='5'><c:out value="${contractLom.cntrt_untprc_expl}" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.manage.contUnitPriceAttch" /></th>
			<td class="last-td" colspan="5">
				<iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationUntPrc" name="fileConsultationUntPrc" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
			</td>
		</tr>
	</c:if>
	<tr>
		<th><spring:message code="clm.page.msg.manage.bg" /></th>
		<td colspan="5"><c:out value="${contractLom.pshdbkgrnd_purps}"
				escapeXml="false" />
		</td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.etcMain" /></th>
		<td colspan="5"><c:out value="${contractLom.etc_main_cont}"
				escapeXml="false" /> <c:if test="${!empty contractLom.if_sys_cd}"> [<c:out
					value="${contractLom.if_sys_cd}" escapeXml="false" />]</c:if>
		</td>
	</tr>
	<!-- 특화 정보 -->
   <c:forEach var="list" items="${considerationSpecialList}">
       <c:if test="${!empty list.attr_value}">
       <tr>    
           <th><c:out value="${list.attr_nm}"/></th>
           <td colspan="5"><c:out value="${list.attr_value}" escapeXml="false"/></td>
       </tr>
       </c:if> 
   </c:forEach>

   <c:forEach var="list" items="${consultationSpecialList}">
       <c:if test="${!empty list.attr_value}">
       <tr>
           <th><c:out value="${list.attr_nm}"/></th>
           <td colspan="5"><c:out value="${list.attr_value}" escapeXml="false"/></td>
       </tr>
       </c:if>
   </c:forEach>
   
   <!-- 특화 정보 끝 -->
   
   <!-- 배상책임한도 -->
   <c:if test='${!empty contractLom.oblgt_lmt}'>
      <tr>
          <th><spring:message code="clm.page.field.contract.consultation.detail.obligationlimit"/></th>
          <td colspan="5">
              <c:out value='${contractLom.oblgt_lmt}' escapeXml="false"/>
          </td>
      </tr>
      </c:if>
      
      <!-- 기타 특약사항 -->
      <c:if test='${!empty contractLom.spcl_cond}'>
      <tr>
          <th><spring:message code="clm.page.msg.manage.etcSpecial" /></th>
          <td colspan="5"><c:out value='${contractLom.spcl_cond}' escapeXml="false"/></td>
      </tr>
      </c:if>
      
      <!-- 준거법 / 준거법상세 -->
      <tr>
          <th><spring:message code="clm.page.field.contract.detail.loac"/></th>
          <td><c:out value='${contractLom.loac}'/></td>
          <th><spring:message code="clm.page.field.contract.detail.loacdetail"/></th>
          <td colspan="3"><c:out value='${contractLom.loac_etc}' escapeXml="false"/></td>
      </tr>
      <!-- 분쟁해결방법 / 분쟁해결방법 상세 -->
      <tr>
          <th><spring:message code="clm.page.field.contract.detail.solutionmethod"/></th>
          <td><c:out value='${contractLom.dspt_resolt_mthd}'/></td>
          <th><spring:message code="clm.page.field.contract.detail.solutionmethoddetail"/></th>
          <td colspan="3"><c:out value='${contractLom.dspt_resolt_mthd_det}' escapeXml="false"/></td>
      </tr>
	<tr>
		<th rowspan="3"><spring:message code="clm.page.msg.common.attachment" /></th>
		<td><span class="blueD"><spring:message code="clm.page.msg.manage.contract" /></span></td>
		<td colspan="4"><iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationContract" name="fileConsultationContract" frameborder="0" width="100%" height="50px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
		</td>
	</tr>
	<tr>
		<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.manage.attachment_br" /></span></td>
		<td colspan="4"><iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationEtc" name="fileConsultationEtc" frameborder="0" width="100%" height="50px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
		</td>
	</tr>
	<tr>
		<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.common.etc" /></span></td>
		<td colspan="4"><iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationEtc2" name="fileConsultationEtc2" frameborder="0" width="100%" height="50px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
		</td>
	</tr>
</table>

<!-- 2014-04-15 Kevin Added. GERP Information -->
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
		<td colspan='3'><c:out
				value="${contractLom.bfhdcstn_apbt_mthd_nm}" /></td>
		<th><spring:message code="clm.page.msg.manage.apprDate" /></th>
		<fmt:parseDate value="${contractLom.bfhdcstn_apbtday}"
			var="bfhdcstn_apbtday" pattern="yyyymmdd" />
		<td><fmt:formatDate value="${bfhdcstn_apbtday}"
				pattern="yyyy-mm-dd" /></td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.proposer" /></th>
		<td colspan='3'>
			<c:out value="${contractLom.bfhdcstn_mtnman_nm}" />/<c:out value="${contractLom.bfhdcstn_mtnman_jikgup_nm}" />/<c:out value="${contractLom.bfhdcstn_mtn_dept_nm}" />
		</td>
		<th><spring:message code="clm.page.msg.manage.proposer" /></th>
		<td>
			<c:out value="${contractLom.bfhdcstn_apbtman_nm}" />/<c:out value="${contractLom.bfhdcstn_apbtman_jikgup_nm}" />/<c:out value="${contractLom.bfhdcstn_apbt_dept_nm}" /></td>
	</tr>
	<tr>
		<th width="100"><spring:message
				code='clm.page.field.contract.consult.approval.file' /></th>
		<td class="last-td" colspan="5">
			<iframe src="<c:url value='/clm/blank.do' />" id="fileList33" name="fileList33" frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe>
		</td>
	</tr>
</table>

	<!-- tnc include JSP -->
	<jsp:include page="/clm/manage/completion.do">
		<jsp:param name="method" value="getTncLink" />
		<jsp:param name="cntrt_id4" value='<%=contractLom.get("cntrt_id") %>'/>
	</jsp:include>
	
	<div class='title_basic3'><spring:message code="clm.page.msg.manage.relContInf" /> </div>
	<table class="table-style01" id="contrack-relationView" >
		<colgroup>
			<col width="12%" />
			<col width="55%" /> <!-- width size 변경 신성우 2014-04-24 -->
			<col width="12%" />
			<col width="21%" />
		</colgroup>
		<tr>
			<th><spring:message code="clm.page.msg.manage.relation" /></th>
			<th><spring:message code="clm.page.msg.manage.relCont" /></th>
			<th><spring:message code="clm.page.msg.manage.define" /></th>
			<th><spring:message code="clm.page.msg.manage.relDetail" /></th>
		</tr>
			<c:choose>
			<c:when test ="${!empty contractRelationList}">
				<c:forEach var="list" items="${contractRelationList}">
					<tr>
						<td><c:out value='${list.rel_type_nm}'/></td>
						<td><c:out value='${list.relation_cntrt_nm}'/></a>
						<td><c:out value='${list.rel_defn}'/></td>
						<td><c:out value='${list.expl}' escapeXml="false"/></td>
					</tr>		
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr><td colspan="4" align="center"><spring:message code="secfw.msg.succ.noResult" /></td></tr>
			</c:otherwise>
			</c:choose>	
	</table>
	<!-- // 계약정보 -->
<%// C02662 추가 by joon // Sungwoo 2014-05-13 Termination step added%>
<c:if test="${contractLom.depth_status=='C02642' || contractLom.depth_status=='C02636'  || contractLom.depth_status=='C02662'|| contractLom.prcs_depth=='C02505'}">
	<div class="title_basic mt20">
		<h4><spring:message code="clm.page.msg.manage.conclInf" /></h4>
	</div>
	<!-- 체결품의정보시작 : default : display:none -->
	<div class="title_basic3"><spring:message code="clm.page.msg.manage.bscInf" /></div>
	<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
		<colgroup>
			<col width="12%" />
			<col width="22%" />
			<col width="12%" />
			<col width="21%" />
			<col width="12%" />
			<col width="21%" />
		</colgroup>
		<tr>
			<th><spring:message code="clm.page.msg.manage.conclYn" /></th>
			<td><c:choose>
					<c:when test="${contractLom.cntrt_cnclsn_yn=='Y'}">Yes</c:when>
					<c:otherwise>No</c:otherwise>
				</c:choose>
			</td>
			<th><spring:message code="clm.page.msg.manage.conclResDate" /></th>
			<td><c:out value='${contractLom.cnclsn_plndday}' /></td>

			<th><spring:message code="clm.page.msg.manage.contConclDt" /></th>
			<td><c:out value='${contractLom.cntrt_cnclsnday}' /></td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.manage.signDiv" /></th>
			<td><c:out value='${contractLom.seal_mthd_nm}' /></td>
			<c:choose>
				<c:when test="${contractLom.seal_mthd=='C02101'}">
					<th><spring:message code="clm.page.msg.manage.signManager" /></th>
					<td colspan=3><c:out value='${contractLom.seal_ffmtman_nm}' />/<c:out value='${contractLom.seal_ffmtman_jikgup_nm}' />/<c:out value='${contractLom.seal_ffmt_dept_nm}' /></td>
				</c:when>
				<c:otherwise>
					<th><spring:message code="clm.page.msg.manage.signRes" /></th>
					<td colspan=3><c:out value='${contractLom.sign_plndman_nm}' />/<c:out value='${contractLom.sign_plndman_jikgup_nm}' />/<c:out value='${contractLom.sign_plnd_dept_nm}' /></td>
				</c:otherwise>
			</c:choose>
		</tr>
	<c:if test="${contractLom.seal_mthd=='C02102'}">
		<tr>
			<th><spring:message code="clm.page.msg.manage.signPerSE" /></th>
			<td><c:out value='${contractLom.signman_nm}' />/<c:out value='${contractLom.signman_jikgup_nm}' />/<c:out value='${contractLom.sign_dept_nm}' /></td>
			<th><spring:message code="clm.page.msg.manage.signDateSE" /></th>
			<td colspan=3><c:out value='${contractLom.signday}' /></td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.manage.signPerOth" /></th>
			<td><c:out value='${contractLom.oppnt_signman_nm}' escapeXml="false"/>/<c:out value='${contractLom.oppnt_signman_jikgup}' escapeXml="false"/>/<c:out value='${contractLom.oppnt_signman_dept}' escapeXml="false"/></td>
			<th><spring:message code="clm.page.msg.manage.signDateOth" /></th>
			<td colspan=3><c:out value='${contractLom.oppnt_signday}' />
			</td>
		</tr>
	</c:if>
		<tr>
			<th><spring:message code="clm.page.msg.manage.chrgCont" /></th>
			<td colspan=5><c:out value='${contractLom.cntrt_respman_nm}' />/<c:out value='${contractLom.cntrt_respman_jikgup_nm}' />/<c:out value='${contractLom.cntrt_resp_dept_nm}' /></td>
<%--	신성우 주석처리 2014-03-31
				<th><spring:message code="clm.page.msg.manage.preAnnDate" /></th>
				<td colspan="3"><c:out value='${contractLom.exprt_notiday}' /></td>
--%>
		</tr>
	</table>
	<br> 
<c:if test="${contractLom.depth_status!='C02636'}">
	<div class="title_basic3"><spring:message code="clm.page.msg.manage.copyVerInf" /></div>
	<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
		<colgroup>
			<col width="12%" />
			<col width="22%" />
			<col width="12%" />
			<col width="54%" />
		</colgroup>
		<tr>
			<th><spring:message code="clm.page.msg.manage.copyVerRegPer" /></th>
			<td><c:out value='${contractLom.cpy_regman_nm}' />/<c:out value='${contractLom.cpy_regman_jikgup_nm}' />/<c:out	value='${contractLom.cpy_reg_dept_nm}' /></td>
			<th><spring:message code="clm.page.msg.manage.copyVerRegDate" /></th>
			<td><c:out value='${contractLom.cpy_regday}' />
			</td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.manage.conclCopy" /></th><!--height="50px"-->
			<td colspan="3">
				<iframe	src="<c:url value='/clm/blank.do' />" id="fileContractCopy" name="fileContractCopy" height="102px" frameborder="0" width="100%"  leftmargin="0" topmargin="0" scrolling="auto"></iframe>
			</td>
		</tr>
		<!-- 
		<tr>
			<th><spring:message code="clm.page.msg.manage.attachment" /></th>
			<td colspan=5></td>
		</tr>
		 -->
	</table>
	<br>
</c:if> 
	<c:if test="${contractLom.depth_status=='C02636' && contractCommand.page_gbn=='modify' || contractLom.depth_status=='C02642'}">
		<c:if test="${contractLom.depth_status=='C02636' && contractCommand.page_gbn=='modify'}">
			<div class="title_basic">
				<h3><spring:message code="clm.page.msg.manage.agreeInf" /></h3>
			</div>
			<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
				<colgroup>
					<col width="14%" />
					<col width="36%" />
					<col width="14%" />
					<col width="36%" />
				</colgroup>
				<tr>
					<th><spring:message code="clm.page.field.contract.consultation.detail.agree" /></th>
					<td colspan="3">
						<select name="agree_yn" id="agree_yn" class="all" style="width: 74px" required>
							<option value=""><spring:message code="clm.page.msg.common.select" /></option>
							<option value="Y"><spring:message code="clm.page.msg.common.agreement" /></option>
							<option value="N"><spring:message code="clm.page.msg.manage.noAgree" /></option>
						</select>
					</td>
				</tr>
				<tr>
					<th><spring:message code="clm.page.field.contract.consultation.detail.agreecause" /></th>
					<td colspan="3">
						<span id="agree_cause">0</span>/<spring:message code="clm.page.msg.manage.stringLen4000" /><br>
						<textarea name="agree_cause" id="agree_cause" cols="10" rows="3" class="text_area_full"	maxLength="4000" onkeyup="frmChkLenLang(this,4000,'agree_cause','<%=langCd%>')" alt="<spring:message code='clm.page.field.contract.consultation.detail.agreecause'/>">
							<c:out value='${lomReq.agree_cause}' />
						</textarea>
					</td>
				</tr>
			</table>
			<br>
		</c:if>

		<div id="org_info">
			<div class="title_basic3">
				<spring:message code="clm.page.msg.manage.orgInf" />
			</div>
			<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
				<colgroup>
					<col width="12%" />
					<col width="22%" />
					<col width="12%" />
					<col width="54%" />
				</colgroup>
				<tr>
					<th><spring:message code="clm.page.field.contract.conclusion.detail.conclusionhandovernm" />
					</th>
					<td><c:choose>
							<c:when test="${contractCommand.page_gbn=='modify'}">
								<c:choose>
									<c:when test="${contractLom.depth_status=='C02642' || contractLom.depth_status=='C02636'}">
										<input type="text" name="org_hdovman_search_nm" id="org_hdovman_search_nm" value="<c:out value='${contractLom.org_hdovman_nm}'/>" style="width: 85px" class="text_search"
											alt="<spring:message code="clm.page.field.contract.conclusion.detail.signmannm"/>" onKeyPress="if(event.keyCode==13) {searchEmployee();return false;}" /><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchEmployee();" class="cp" alt="search" />
										<input type="hidden" name="org_hdovman_nm" id="org_hdovman_nm" value="<c:out value='${contractLom.org_hdovman_nm}'/>" />
										<input type="hidden" name="org_hdovman_id" id="org_hdovman_id" value="<c:out value='${contractLom.org_hdovman_id}'/>" />
										<input type="text" name="org_hdovman_jikgup_nm" id="org_hdovman_jikgup_nm" style="width:100%;border: 0px" value="<c:out value='${contractLom.org_hdovman_jikgup_nm}'/>" readonly="readonly" />
										<input type="text" name="org_hdov_dept_nm" id="org_hdov_dept_nm" style="width:100%;border: 0px" value="<c:out value='${contractLom.org_hdov_dept_nm}'/>" readonly="readonly" />												
									</c:when>
									<c:otherwise>
										<c:out value='${contractLom.org_hdovman_nm}' />/
										<c:out value='${contractLom.org_org_hdovman_jikgup_nm}' />/
										<c:out value='${contractLom.org_hdov_dept_nm}' />
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:out value='${contractLom.org_hdovman_nm}' />/<c:out
									value='${contractLom.org_hdovman_jikgup_nm}' />/<c:out
									value='${contractLom.org_hdov_dept_nm}' />
							</c:otherwise>
						</c:choose>
					</td>
					<th><spring:message
							code="las.page.field.lawconsideration.groupChief" />
					</th>
					<td><%=session.getAttribute("secfw.session.user_nm")%>/<%=session.getAttribute("secfw.session.grade_nm")%>/<%=session.getAttribute("secfw.session.dept_nm")%>
						<!--<c:out value='${contractLom.org_tkovman_nm}'/>/<c:out value='${contractLom.org_tkovman_jikgup_nm}'/>/<c:out value='${contractLom.org_tkov_dept_nm}'/>-->
					</td>
				</tr>
				<tr>
					<c:choose>
						<c:when test="${contractCommand.page_gbn=='modify'}">
							<c:choose>
								<c:when test="${contractLom.depth_status=='C02642' || contractLom.depth_status=='C02636'}">
									<th><spring:message code="clm.page.msg.manage.contConclDt" /><span class="astro">*</span><img src='<%=IMAGE%>/btn/btn_info.gif' title="<spring:message code="clm.page.msg.manage.announce031" htmlEscape="true" />" ></th>
									<td>
										<input type="text" name="cntrt_cnclsnday" id="cntrt_cnclsnday" value="<c:out value='${contractLom.cntrt_cnclsnday}' />" class="text_calendar02"
										alt="<spring:message code="clm.page.msg.manage.contConclDt" htmlEscape="true" />" />
									</td>
								</c:when>
								<c:otherwise>
									<th><spring:message code="clm.page.msg.manage.contConclDt" /></th>
									<td >
									<c:out value='${contractLom.cntrt_cnclsnday}' />
									</td>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
						<th><spring:message code="clm.page.msg.manage.contConclDt" /></th>
						<td><c:out value='${contractLom.cntrt_cnclsnday}' /></td>
						</c:otherwise>
					</c:choose>
					<th><spring:message code="clm.page.field.contract.conclusion.detail.conclusionorgacceptday" />
					</th>
					<td><c:choose>
							<c:when test="${contractCommand.page_gbn=='modify'}">
								<c:choose>
									<c:when
										test="${contractLom.depth_status=='C02642' || contractLom.depth_status=='C02636'}">
										<input type="text" name="org_acptday" id="org_acptday" value="<%=DateUtil.formatDate(DateUtil.today(), "-")%>" class="text_calendar02"
											alt="<spring:message code="clm.page.field.contract.conclusion.detail.conclusionorgacceptday"/>" />
									</c:when>
									<c:otherwise>
										<c:out value='${contractLom.org_acptday}' />
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:out value='${contractLom.org_acptday}' />
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th>
						<c:choose>
							<c:when test="${contractCommand.page_gbn=='modify'}">
								<c:choose>
									<c:when
										test="${contractLom.depth_status=='C02642' || contractLom.depth_status=='C02636'}">
										<spring:message	code="clm.page.field.contract.conclusion.detail.conclusionorgloacation" /><span class="astro">*</span>
									</c:when>
									<c:otherwise>
										<spring:message	code="clm.page.field.contract.conclusion.detail.conclusionorgloacation" /></span>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<spring:message	code="clm.page.field.contract.conclusion.detail.conclusionorgloacation" /></span>
							</c:otherwise>
						</c:choose>
					
					</th>
					<td colspan="3">
						<c:choose>
							<c:when test="${contractCommand.page_gbn=='modify'}">
								<c:choose>
									<c:when
										test="${contractLom.depth_status=='C02642' || contractLom.depth_status=='C02636'}">
										<input type="text" name="org_strg_pos" id="org_strg_pos" value="<c:out value='${contractLom.org_strg_pos}'/>" class="text_full" maxLength="100" />
									</c:when>
									<c:otherwise>
										<c:out value='${contractLom.org_strg_pos}' />
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:out value='${contractLom.org_strg_pos}' />
							</c:otherwise>
						</c:choose>
					</td>
				</tr>

				<c:choose>
					<c:when test="${contractCommand.page_gbn=='modify'}">
						<c:choose>
							<c:when
								test="${contractLom.depth_status=='C02642' || contractLom.depth_status=='C02636'}">
								<!-- 미등록상태일때 -->
								<tr>
									<th><spring:message	code="clm.page.field.contract.conclusion.detail.orgacceptdelaycause" />
									</th>
									<td colspan="3">
										<span id="org_acpt_dlay_cause">0</span>/<spring:message code="clm.page.msg.manage.stringLen1000" /><br>
										<textarea name="org_acpt_dlay_cause" id="org_acpt_dlay_cause" cols="100%" rows="5" maxLength="1000" onkeyup="frmChkLenLang(this,1000,'org_acpt_dlay_cause','<%=langCd%>')" class="text_area_full" alt='<spring:message code="clm.page.field.contract.conclusion.detail.orgacceptdelaycause"/>'><c:out value='${contractLom.org_acpt_dlay_cause}' escapeXml="false"/></textarea>
									</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:if test="${contractLom.org_acpt_dlay_cause != null}">
									<tr>
										<th><spring:message	code="clm.page.field.contract.conclusion.detail.orgacceptdelaycause" />
										</th>
										<td colspan="3">
											<c:out value='${contractLom.org_acpt_dlay_cause}' escapeXml="false" />
										</td>
									</tr>
								</c:if>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<tr>
							<th><spring:message	code="clm.page.field.contract.conclusion.detail.orgacceptdelaycause" />
							</th>
							<td colspan="3">
								<c:out value='${contractLom.org_acpt_dlay_cause}' escapeXml="false" />
							</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>
			</div>
			<br>
	</c:if>

	<c:if test="${contractLom.depth_status!='C02636'}">
		<div id="org_info">
		<c:if test="${contractMstLom.cntrt_cnclsn_yn=='N' && !empty contractCnclsndlayLom}">
			<div class="title_basic">
				<h3><spring:message code="clm.page.msg.manage.conclDelayInf" /></h3>
			</div>
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
					<td colspan=5><c:out value='${contractCnclsndlayLom.dlay_cause}' /></td>
				</tr>
				<tr>
					<th><spring:message code="clm.page.msg.manage.preChgConclDate" /></th>
					<td colspan=5><c:out value='${contractCnclsndlayLom.chgeaft_cnclsn_plndday}' /></td>
				</tr>
			</table>
		</c:if>
		</div>
	</c:if>
	<!--// 체결정보 -->
</c:if>


