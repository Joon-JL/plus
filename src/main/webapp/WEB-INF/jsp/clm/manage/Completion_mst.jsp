<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="java.util.ArrayList" %>
<%-- 
/**
 * 파  일  명 : Completion_mst.jsp
 * 프로그램명 : 계약상세 조회
 * 설      명 : 
 * 작  성  자 : 신승완
 * 작  성  일 : 2011.09
 */
--%>
<%
ArrayList contractRelationLom	= (ArrayList)request.getAttribute("contractRelationLom");
%>
<!-- hidden Form -->
	<input type="hidden" name="cntrt_respman_id"	id="cntrt_respman_id"   value="<c:out value='${contractMstLom.cntrt_respman_id}'/>" />
	<input type="hidden" name="chose_client" value="<c:out value='${reqAuthFormInfo}'/>" />
	<input type="hidden" name="authreq_client" value="<c:out value='${reqAuthSvcInfo}'/>" />
	<input type="hidden" id="hidden_reqAuthInfo" name="hidden_reqAuthInfo" value="<c:out value='${reqAuthInfo}'/>" />

<script language="javascript">
//거래선 팝업
function customerPop2(customerCd, dodun){
	PopUpWindowOpen("/clm/draft/customerTest.do?method=detailCustomerShare&customer_cd="+customerCd + "&dodun=" + dodun, 900, 300, false);
}
</script>	
	
<!--// hidden Form -->
<table border="0"cellspacing="0" cellpadding="0" class="table-style03">
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
		<td colspan="2"><a href="javascript:customerPop2('<c:out value="${contractMstLom.cntrt_oppnt_cd}" />', '<c:out value="${contractMstLom.cntrt_oppnt_cd}" />');"><c:out value="${contractMstLom.cntrt_oppnt_nm}" /></a></td>
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
			<iframe src="<c:url value='/clm/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
		</td>
	</tr>
	<tr>
		<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.manage.attachment" /></span></td>
		<td colspan="5">
			<iframe src="<c:url value='/clm/blank.do' />" id="fileList3" name="fileList3" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
		</td>
	</tr> 
	<tr>
		<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.common.etc" /></span></td>
		<td colspan="5">
			<iframe src="<c:url value='/clm/blank.do' />" id="fileList2" name="fileList2" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
		</td>
	</tr>
</table>
<!-- 계약상세내역 : default : display:none -->
<table id="contract-detail-content" cellspacing="0" cellpadding="0" border="0" class="table-style01" style="margin-top:0;display:none">
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
		<td colspan="2"><c:out value="${contractMstLom.payment_gbn_nm}" escapeXml="false" /></td>
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
			<th rowspan="2"><spring:message code='clm.page.field.contract.detail.unitcont' /></th>
			<td colspan="5">
				<c:out value="${contractMstLom.cntrt_untprc_expl}" escapeXml="false" />
			</td>
		</tr>
		<tr>
			<td colspan="5" class="tal_lineL">
				<iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationUntPrc" name="fileConsultationUntPrc" frameborder="0" width="100%" height="50px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>							
			</td>
		</tr>
	</c:if>
	<c:if test="${!empty contractMstLom.payment_cond}">
	<tr>
		<th><spring:message code='clm.page.field.contract.detail.payment' /></th>
		<td colspan="5"><c:out value="${contractMstLom.payment_cond}" escapeXml="false" /></td>
	</tr>
	</c:if>
	<c:if test="${!empty contractMstLom.etc_main_cont || !empty contractMstLom.if_sys_cd}">
	<tr>
		<th><spring:message code='las.page.field.contractmanager.consideration_inner_d.etc_main_cont' /></th>
		<td colspan="5">
			<c:out value="${contractMstLom.etc_main_cont}" escapeXml="false" />
			<c:if test="${!empty contractMstLom.if_sys_cd}"> [<c:out value="${contractMstLom.if_sys_cd}" escapeXml="false" />]</c:if>
		</td>
	</tr>
	</c:if>
	<!-- 특화정보  -->

		<c:forEach var="list" items="${considerationSpecialList}">
			<c:if test="${!empty list.attr_value}">
			<tr>	
				<th><c:out value="${list.attr_nm}"/></th>
				<td class="tal_lineL" colspan="5"><c:out value="${list.attr_value}" escapeXml="false" /></td>
			</tr>
			</c:if>
		</c:forEach>
		
		<c:forEach var="list" items="${consultationSpecialList}">
			<c:if test="${!empty list.attr_value}">
			<tr>	
				<th><c:out value="${list.attr_nm}"/></th>
				<td class="tal_lineL" colspan="5"><c:out value="${list.attr_value}" escapeXml="false" /></td>
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
	  		<c:out value='${contractMstLom.oblgt_lmt}' escapeXml="false" />
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
		<td colspan="2"><c:out value='${contractMstLom.loac_etc}' escapeXml="false" /></td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.field.contract.detail.solutionmethod"/></th>
		<td colspan="2"><c:out value='${contractMstLom.dspt_resolt_mthd}'/></td>
		<th><spring:message code="clm.page.field.contract.detail.solutionmethoddetail"/></th>
		<td colspan="2"><c:out value='${contractMstLom.dspt_resolt_mthd_det}' escapeXml="false" /></td>
	</tr>
</table>
<!-- //top table -->
<!-- //계약상세내역 : default : display:none -->
<!-- 사전 검토정보 : default : display:none-->
<table id="contract-cnsdInfo-content" cellspacing="0" cellpadding="0" class="table-style01 table-top" style="margin-top:0;display:none">
	<colgroup>
		<col width="14%" />
		<col width="36%" />
		<col width="14%" />
		<col width="14%" />
		<col width="12%" />
		<col width="12%" />
	</colgroup>
	<tr>
		<th width="100"><spring:message code="clm.page.msg.manage.preApprProp" /></th>
		<td width="220"><c:out value="${contractMstLom.bfhdcstn_mtnman_nm}" />
		</td>
		<th width="90"><spring:message code="clm.page.msg.manage.level" /></th>
	    <td><c:out value="${contractMstLom.bfhdcstn_mtnman_jikgup_nm}" /></td>
		<th width="90"><spring:message code="clm.page.msg.manage.deptName" /></th>
		<td class="last-td"><c:out value="${contractMstLom.bfhdcstn_mtn_dept_nm}" /></td>
	</tr>
	<tr>
		<th>E-mail</th>
		<td><c:out value="${contractMstLom.bfhdcstn_mtnman_email}" /></td>
	    <th><spring:message code="clm.page.msg.manage.apprType" /></th>
		<td class="last-td" colspan="3"><c:out value="${contractMstLom.bfhdcstn_apbt_mthd_nm}" />
		</td>
	</tr>
	<tr>
		<th><span><spring:message code="clm.page.msg.manage.preApprPerInf" /></span></th>
		<td class="last-td" colspan="5">
			<table cellspacing="0" cellpadding="0" class="table-style02">
				<tr>
					<th><spring:message code="clm.page.msg.manage.name" /></th>
		      		<th><spring:message code="clm.page.msg.manage.level" /></th>
					<th><spring:message code="clm.page.msg.common.department" /></th>
					<th class="last-td"><spring:message code="clm.page.msg.manage.procDate" /></th>
				</tr>
				<tr>
					<td><c:out value="${contractMstLom.bfhdcstn_apbtman_nm}" /></td>
					<td><c:out value="${contractMstLom.bfhdcstn_apbtman_jikgup_nm}" /></td>
					<td><c:out value="${contractMstLom.bfhdcstn_apbt_dept_nm}" /></td>
					<td class="last-td"><c:out value="${contractMstLom.bfhdcstn_apbtday}" /></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<th width="100"><spring:message code='clm.page.field.contract.consult.approval.file' /></th>
		<td class="last-td" colspan="5">
			<iframe src="<c:url value='/clm/blank.do' />" id="fileList33" name="fileList33" frameborder="0" width="100%" height="30px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
		</td>
	</tr>
</table>							
<!--  사전검토 정보 : default : display:none -->

<!-- 연관계약정보 -->
<%
if(contractRelationLom.size()==0){
%>
<script language="javascript">
//연관계약정보 탭 숨기기
function displayTabChecked(){
	$('#contract-relationInfoTab').attr("style","display:none");
}
displayTabChecked();
</script>
<%
}
%>

<table id="contract-relation-content" cellspacing="0" cellpadding="0" border="0" class="table-style01" style="display:none;">
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

<!-- 
<table id="contract-relation-content" cellspacing="0" cellpadding="0" border="0" class="table-style01" style="display:none;">
	<colgroup>
		<col width="14%" />
		<col width="10%" />
		<col width="21%" />
		<col width="14%" />
		<col width="12%" />
		<col width="12%" />
		<col width="17%" />
	</colgroup>
	<tbody id="relationTbody">
	<c:forEach var="list" items="${contractRelationLom}" varStatus="i">
		<tr id="relationTR">
			<c:if test="${i.count == '1'}">
				<th rowspan=<c:out value='${list.relation_cnt*3}'/>><spring:message code="clm.page.field.contract.detail.relation"/></th>	
			</c:if>						
			<td class="tal_lineL"><span class="th-color"><spring:message code="clm.page.field.contract.detail.relation.name"/></span></td>					
			<td colspan="5"><c:out value='${list.relation_cntrt_nm}'/></td>
		</tr>
		<tr>
			<td class="tal_lineL"><span class="th-color"><c:out value='${list.rel_type_nm}'/></span></td>
			<td class="tal_lineL" td colspan="5">
				<c:out value='${list.first_cntrt_nm}'/> <br/>
				<c:out value='${list.second_cntrt_nm}'/>  
				<c:out value='${list.rel_defn}'/>
			</td>
		</tr>
		<tr>
			<td class="tal_lineL">
				<span class="th-color"><spring:message code='clm.page.field.refcontract.cont'/></span>
			</td>
			<td class="tal_lineL" td colspan="5">
				<c:out value='${list.expl}'/>							
			</td>
		</tr>	
	</c:forEach>	
	</table>
	 -->
