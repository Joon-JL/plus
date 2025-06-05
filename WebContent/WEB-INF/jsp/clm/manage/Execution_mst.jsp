<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="java.util.ArrayList" %>
<%@ include file= "/WEB-INF/jsp/common/common.jsp" %>
<%-- 
/**
 * 파  일  명 : Execution_mst.jsp
 * 프로그램명 : 계약상세 조회(기본정보)
 * 설      명 : 
 * 작  성  자 : 박정훈
 * 작  성  일 : 2013.05
 */
--%>
<%
ArrayList contractRelationLom	= (ArrayList)request.getAttribute("contractRelationLom");
%>
<!-- hidden Form -->
<input type="hidden" name="cntrt_respman_id"	id="cntrt_respman_id"   value="<c:out value='${contractMstLom.cntrt_respman_id}'/>" />
<input type="hidden" name="authreq_client" value="<c:out value='${reqAuthSvcInfo}'/>" />
<input type="hidden" name="customer_cd" id="customer_cd" value="" />
<input type="hidden" name="dodun" id="dodun" value="" />
<!-- 2014-04-16 Kevin added. GERP 관련 페이지 형태 구분.(R/I) -->
<input type="hidden" name="gerpPageType" id="gerpPageType" />

<script src="/script/func/ConsiderationFunc.js"></script><!-- 2014-07-28 Kevin added -->
<script language="javascript">
$(function(){
	
	// 2014-07-28 Kevin added.
	// Following codes retrive step/status information and show them.
	var considerationFunc = new ConsiderationFunc($);
	considerationFunc.getStepStatusInfoPerCnsdreqid($("#cnsdreq_id").val(), function(data){
		$("#spStep").text(data.step);
		$("#spStatus").text(data.status+" "+data.status_depth);
	});
	
	// 2014-04-16 Kevin Added.
    // GERP readonly iframe load. 
    frm.target          = "iframeGERP";
    frm.action          = "<c:url value='/clm/manage/consideration.do' />";
    frm.gerpPageType.value = "R";		// detail readonly
    frm.method.value    = "forwardGERPDetail";
	frm.submit();	
});
//거래선 팝업
function customerPop2(customerCd, dodun){
	PopUpWindowOpen("/clm/draft/customerTest.do?method=detailCustomerShare&customer_cd="+customerCd + "&dodun=" + dodun, 900, 300, false);
}

//TOP 30 팝업
function openTop30Customer(){
	   PopUpWindowOpen2("/clm/draft/customerTest.do?method=listTop30Customer", 500, 540, false, "Top30Customer");
}
</script>
<!--// hidden Form -->
<div id="contract-btn-content">
<c:if test="${contractExecCommand.session_user_id==contractMstLom.cntrt_respman_id || 'RA01' == command.top_role }" >
<script language="javascript">

$(document).ready(function(){
	$('#goSave').bind('click',function(){
		executionlist();
		$(this).remove();
		$('#goSave2').remove();
		alert("<spring:message code="clm.page.msg.manage.announce161" />");
		$('#mainExecutionDetail-Content').focus();
	});
});

</script>
	<span class="btn_all_w" id="bnt_forwardview"><span class="jungsanend_clear"></span><a href="javascript:alert('error')" onclick="javascript:goCompletion(document.frm.cnsdreq_id.value); return false;"><spring:message code="clm.page.msg.manage.endManage" /></a></span>
</c:if>

<c:if test="${contractMstLom.prcs_depth == 'C02504' && ssems_oppnt_cd != null && ssems_oppnt_cd != ''}">
	<span class="btn_all_w"><span class="list"></span><a href="javascript:listSSemsIF();"><spring:message code="clm.page.msg.manage.ssemsCont" />(<c:out value='${ssems_oppnt_cd}'/>)</a></span>
</c:if>	
				
<span class="btn_all_p"><span class="print_title"></span><a href="javascript:openPrint2();"><spring:message code="clm.page.msg.manage.printCover" /></a></span>
<span class="btn_all_p"><span class="print"></span><a href="javascript:openPrint();"><spring:message code="clm.page.msg.manage.print" /></a></span>
<span class="btn_all_p" id="bnt_list"><span class="list"></span><a href="javascript:listManageExecution();"><spring:message code="clm.page.msg.manage.list" /></a></span>
</div>

<div class='title_basic3'><spring:message code="clm.page.msg.manage.bscInf" /></div>
<table class="table-style01">
	<colgroup>
		<col width="16%" />
		<col width="11%" />
		<col width="12%" />
		<col width="16%" />
		<col width="15%" />
		<col width="15%" />
		<col width="15%" />
	</colgroup>
	<tr>
		<th><spring:message code="clm.page.msg.manage.reqName" /></th>
	    <td colspan="6"><c:out value="${contractReqLom.req_title}" escapeXml="false"/></td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.contName" /></th>
		<td colspan='4'><c:out value="${contractReqLom.cntrt_nm}" /></td>
		<th><spring:message code="clm.page.msg.manage.contId" /></th>
		<td><c:out value="${contractReqLom.cntrt_no}" />
			<c:if test="${!empty contractReqLom.tnc_no}">
				<span style='color:#A9B4BC;'>(<c:out value="${contractReqLom.tnc_no}" />)</span>
			</c:if>
			
		
		</td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.reqPerson" /></th>
		<td colspan="2"><c:out value="${contractReqLom.reqman_nm}" /></td>
		<th><spring:message code="clm.page.msg.manage.chrgPerson" /></th>
		<td><c:out value="${contractReqLom.cntrt_respman_nm}" /></td>
		<th><spring:message code="clm.page.msg.manage.reviewer" /></th>
		<td><c:out value="${contractReqLom.cnsdman_nm}" /></td>
	</tr>
	<!-- 2014-07-28 Kevin added. Step / Status -->
	<tr class="lineAdd">
		<th class="borTz02"><spring:message code="clm.page.field.manageCommon.step" /></th>
		<td colspan="2"><span id="spStep"></span></td>
		<th><spring:message code="clm.page.field.manageCommon.status" /></th>
		<td colspan="3"><span id="spStatus"></span></td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.common.otherParty" /></th>
		<td colspan="2"><a href="javascript:customerPop2('<c:out value="${contractMstLom.cntrt_oppnt_cd}" />', '<c:out value="${contractMstLom.cntrt_oppnt_cd}" />');"><c:out value="${contractMstLom.cntrt_oppnt_nm}" /></a></td>
		<th><spring:message code="clm.page.field.customer.registerNo" /></th>
		<td><c:out value="${contractMstLom.cntrt_oppnt_ceo}" escapeXml="false"/></td>
		<th></th>
		<td></td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.contItm" /></th>
		<td colspan="2"><c:out value="${contractMstLom.cntrt_trgt_nm}" /></td>
		<th><spring:message code="clm.page.msg.manage.contItmDtl" /></th>
		<td colspan="3"><c:out value="${contractMstLom.cntrt_trgt_det}" escapeXml="false"/></td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.contType" /></th>
		<td colspan="6"><c:out value='${contractMstLom.biz_clsfcn_nm}'/>/<c:out value='${contractMstLom.depth_clsfcn_nm}'/>/<c:out value='${contractMstLom.cnclsnpurps_bigclsfcn_nm}'/>/<c:out value='${contractMstLom.cnclsnpurps_midclsfcn_nm}'/></td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.top30Cus" /><img src="<%=IMAGE%>/icon/ico_search2.gif" class="cp" onClick="javascript:openTop30Customer();" alt="search" style="padding-left:5px" title="Top 30 Customer List"/></th>
		<td colspan="2"><c:out value="${contractMstLom.top_30_cus_nm}" /></td>
		<th><spring:message code="clm.page.msg.manage.relatedPrd" /></th>
		<td><c:out value="${contractMstLom.related_products_nm}" escapeXml="false"/></td>
		<th><spring:message code="clm.page.msg.manage.srcContDraft" /></th>
		<td><c:out value="${contractMstLom.cont_draft_nm}" /></td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.contPeriod" /></th>
		<td colspan="2"><c:out value="${contractMstLom.cntrtperiod_startday}" /> ~ <c:out value="${contractMstLom.cntrtperiod_endday}" /></td>
		<th><spring:message code="clm.page.field.contract.consultation.detail.autorenew" /></th>
		<td>
		<c:if test="${contractMstLom.auto_rnew_yn=='Y'}">Yes</c:if>
		<c:if test="${contractMstLom.auto_rnew_yn=='N'}">No</c:if>
		</td>
		<th><spring:message code="clm.page.msg.manage.sendRcvDiv" /></th>
		<td><c:out value="${contractMstLom.payment_gbn_nm}" escapeXml="false" /></td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.contAmt" /></th>
		<td colspan='4'>
		<input type="text" class="text" style="border:0px solid #fff;" value="<c:out value="${contractMstLom.cntrt_amt}" />" />
		<c:if test="${!empty contractMstLom.cntrt_untprc_expl}"><input type='checkbox' class='checkbox' checked disabled> <spring:message code="clm.page.msg.manage.conclSingleAmt" /></c:if>
		 </td>
		<th><spring:message code="clm.page.msg.manage.curr" /></th>
		<td><c:out value="${contractMstLom.crrncy_unit}" /></td>
	</tr>
	<c:if test="${!empty contractMstLom.cntrt_untprc_expl}">
	<tr>
		<th><spring:message code="clm.page.msg.manage.contUnitPriceSum" /></th>
		<td colspan='6' id='untprc_expl'><c:out value="${contractMstLom.cntrt_untprc_expl}" escapeXml="false"/></td>
	</tr>
	<tr>
		<th width="100"><spring:message code="clm.page.msg.manage.contUnitPriceAttch" /></th>
		<td class="last-td" colspan="6">
			<iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationUntPrc" name="fileConsultationUntPrc" frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true" ></iframe>
		</td>
	</tr>
	</c:if>
	<tr>
		<th><spring:message code="clm.page.msg.manage.bg" /></th>
		<td colspan="6"><c:out value="${contractMstLom.pshdbkgrnd_purps}" escapeXml="false" />
		</td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.etcMain" /></th>
		<td colspan="6">
		<c:out value="${contractMstLom.etc_main_cont}" escapeXml="false"/>
		<c:if test="${!empty contractMstLom.if_sys_cd}"> [<c:out value="${contractMstLom.if_sys_cd}" escapeXml="false" />]</c:if>
		</td>
	</tr>
	<!-- 특화 정보 -->
          <c:forEach var="list" items="${considerationSpecialList}">
           <c:if test="${!empty list.attr_value}">
           <tr>    
               <th><c:out value="${list.attr_nm}"/></th>
               <td colspan="6"><c:out value="${list.attr_value}" escapeXml="false"/></td>
           </tr>
           </c:if> 
       </c:forEach>

       <c:forEach var="list" items="${consultationSpecialList}">
           <c:if test="${!empty list.attr_value}">
           <tr>
               <th><c:out value="${list.attr_nm}"/></th>
               <td colspan="6"><c:out value="${list.attr_value}" escapeXml="false"/></td>
           </tr>
           </c:if>
       </c:forEach>
       
       <!-- 특화 정보 끝 -->
       
       <!-- 배상책임한도 -->
       <c:if test='${!empty contractMstLom.oblgt_lmt}'>
          <tr>
              <th><spring:message code="clm.page.field.contract.consultation.detail.obligationlimit"/></th>
              <td colspan="6">
                  <c:out value='${contractMstLom.oblgt_lmt}' escapeXml="false"/>
              </td>
          </tr>
          </c:if>
          
          <!-- 기타 특약사항 -->
          <c:if test='${!empty contractMstLom.spcl_cond}'>
          <tr>
              <th><spring:message code="clm.page.msg.manage.etcSpecial" /></th>
              <td colspan="6"><c:out value='${contractMstLom.spcl_cond}' escapeXml="false"/></td>
          </tr>
          </c:if>
          
          <!-- 준거법 / 준거법상세 -->
          <tr>
              <th><spring:message code="clm.page.field.contract.detail.loac"/></th>
              <td colspan="2"><c:out value='${contractMstLom.loac}'/></td>
              <th><spring:message code="clm.page.field.contract.detail.loacdetail"/></th>
              <td colspan="3"><c:out value='${contractMstLom.loac_etc}' escapeXml="false"/></td>
          </tr>
          <!-- 분쟁해결방법 / 분쟁해결방법 상세 -->
          <tr>
              <th><spring:message code="clm.page.field.contract.detail.solutionmethod"/></th>
              <td colspan="2"><c:out value='${contractMstLom.dspt_resolt_mthd}'/></td>
              <th><spring:message code="clm.page.field.contract.detail.solutionmethoddetail"/></th>
              <td colspan="3"><c:out value='${contractMstLom.dspt_resolt_mthd_det}' escapeXml="false"/></td>
          </tr>
          <tr>
		<th rowspan="4"><spring:message code="clm.page.msg.common.attachment" /></th>
		<td><span class="blueD"><spring:message code="clm.page.msg.manage.contract" /></span></td>
		<td colspan="5"><iframe src="<c:url value='/clm/blank.do' />" id="fileList" name="fileList" frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe>
		</td>
	</tr>
	<tr>
		<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.manage.attachment_br" /></span></td>
		<td colspan="5"><iframe src="<c:url value='/clm/blank.do' />" id="fileList3" name="fileList3" frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe>
		</td>
	</tr>
	<tr>
		<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.common.etc" /></span></td>
		<td colspan="5"><iframe src="<c:url value='/clm/blank.do' />" id="fileList2" name="fileList2" frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe>
		</td>
	</tr>
	<tr>
		<td class="tal_lineL"><span class="blueD"><spring:message code="clm.page.msg.common.outlook" /></span></td><!-- outlook -->
		<td colspan="5"><iframe src="<c:url value='/clm/blank.do' />" id="fileListOut" name="fileListOut" frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe>
		</td>
	</tr>
</table>	

<!-- 2014-04-16 Kevin Added. GERP Information -->
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
	<tr>
		<th width="100"><spring:message code='clm.page.field.contract.consult.approval.file' /></th>
		<td class="last-td" colspan="5">
			<iframe src="<c:url value='/clm/blank.do' />" id="fileList33" name="fileList33" frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true" ></iframe>
		</td>
	</tr>
</table>
	
<div class='title_basic3'><spring:message code="clm.page.msg.manage.relContInf" /> </div>
<table class="table-style01" id="contrack-relationView" >
	<colgroup>
		<col width="12%" />
		<col width="55%" />
		<col width="12%" />
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
			<c:forEach var="list" items="${contractRelationLom}">
				<tr>
					<td class='tC'><c:out value='${list.rel_type_nm}'/></td>
					<td><a href="javascript:goDetail('<c:out value='${list.cnsdreq_id}'/>');"><c:out value='${list.relation_cntrt_nm}'/></a></td>
					<td class='tC'><c:out value='${list.rel_defn}'/></td>
					<td class='tC'><c:out value='${list.expl}' escapeXml="false"/></td>
				</tr>		
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr><td colspan="4" class='tC'><spring:message code="clm.msg.succ.noResult" /></td></tr>
		</c:otherwise>
		</c:choose>	
</table>
<!-- // 계약정보 -->