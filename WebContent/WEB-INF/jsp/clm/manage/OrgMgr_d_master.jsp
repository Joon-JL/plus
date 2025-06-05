<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.DateUtil"%>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<%
	String str_org_acpt_dlay_cause="";
	ListOrderedMap m_contractLom = (ListOrderedMap)request.getAttribute("contractLom"); // 추가 2013.10.12
	if (m_contractLom!=null) {
		str_org_acpt_dlay_cause = StringUtil.convertEnterToBR((String)m_contractLom.get("org_acpt_dlay_cause")); // BR처리문제로 추가
	}	
	//==========================================================================================
	ListOrderedMap contractLom = (ListOrderedMap)request.getAttribute("contractLom");
%>
<script>
	$(document)
			.ready(					
					function() {
						if ('<c:out value='${contractLom.org_hdovman_id}'/>' == '' && '<c:out value='${contractCommand.page_gbn}'/>'=='modify') {
							frm.org_hdovman_nm.value = '<c:out value='${contractLom.cntrt_respman_nm}'/>';
							frm.org_hdovman_search_nm.value = '<c:out value='${contractLom.cntrt_respman_nm}'/>';
							frm.org_hdovman_id.value = '<c:out value='${contractLom.cntrt_respman_id}'/>';
							frm.org_hdovman_jikgup_nm.value = '<c:out value='${contractLom.cntrt_respman_jikgup_nm}'/>';
							frm.org_hdov_dept_nm.value = '<c:out value='${contractLom.cntrt_resp_dept_nm}'/>';
						}
						
						if('<c:out value='${contractLom.seal_mthd}'/>' == 'C02101' || '<c:out value='${contractLom.seal_mthd}'/>' == 'C02103'){
							$('#seal_th').attr("style", "display:");
							$('#seal_td').attr("style", "display:");
							$('#sign_th').attr("style", "display:none");//none
							$('#sign_td').attr("style", "display:none");
							$('#sign2_tr1').attr("style", "display:none");
							$('#sign2_tr2').attr("style", "display:none");
						}else{
							$('#sign_td').attr("colspan", "1");
							$('#seal_td').attr("colspan", "1");
							$('#seal_th').attr("style", "display:");
							$('#seal_td').attr("style", "display:");
							$('#sign_th').attr("style", "display:");
							$('#sign_td').attr("style", "display:");
						}
						
						

						
						if('<c:out value='${contractCommand.page_gbn}'/>'=='modify'){
							//화페단위
							getCodeSelectByUpCd("frm", "crrncy_unit", '/common/clmsCom.do?method=listComCdByGrpCd&combo_abbr=A&combo_grp_cd=C5&combo_type=S&combo_del_yn=N&combo_selected=<c:out value='${contractLom.crrncy_unit}'/>');
							
							initCal("cntrtperiod_startday");    //계약기간
					  		initCal("cntrtperiod_endday");		//계약기간
					  		initCal("org_acptday"); //원본접수일
					  		//initCal('exprt_notiday');//만료사전알림일	신성우 주석처리 2014-03-31
					  		initCal("cnclsn_plndday");//체결예정일
					  		initCal("cntrt_cnclsnday");//계약체결일
					  		
					  		initCal('signday');
					  		initCal('oppnt_signday');
						}
					});
</script>

<!-- toptable-->
<input type="hidden" name="reg_operdiv" id="reg_operdiv" value="<c:out value='${contractLom.reg_operdiv}'/>" />
<input type="hidden" name="depth_status" id="depth_status" value="<c:out value='${contractLom.depth_status}'/>" />
<input type="hidden" name="req_title" id="req_title" value="<c:out value='${contractReqLom.req_title}' />" />
<input type="hidden" name="reqman_id" id="reqman_id" value="<c:out value='${contractReqLom.reqman_id}' />" />
<input type="hidden" name="dmstfrgn_gbn" id="dmstfrgn_gbn" value="<c:out value='${contractReqLom.dmstfrgn_gbn}'/>" />

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
		<td colspan="5"><c:out value="${contractReqLom.req_title}" escapeXml="false"/></td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.contName" /></th>
		<td colspan='3'><c:out value="${contractReqLom.cntrt_nm}" /></td>
		<th><spring:message code="clm.page.msg.manage.contId" /></th>
		<td><c:out value="${contractReqLom.cntrt_no}" /></td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.reqPerson" /></th>
		<td><c:out value="${contractReqLom.reqman_nm}" />/<c:out value="${contractReqLom.reqman_jikgup_nm}" />/<c:out value="${contractReqLom.req_dept_nm}" /></td>
		<th><spring:message code="clm.page.msg.manage.chrgPerson" /></th>
		<td><c:out value="${contractLom.cntrt_respman_nm}" />/<c:out value="${contractLom.cntrt_respman_jikgup_nm}" />/<c:out value="${contractLom.cntrt_resp_dept_nm}" /></td>
		<th><spring:message code="clm.page.msg.manage.reviewer" /></th>
		<td><c:out value="${contractReqLom.cnsdmans}" escapeXml="false" /></td>
	</tr>
	<tr class="lineAdd">
		<th><spring:message code="clm.page.msg.manage.relPerson" /> <img src="<%=IMAGE %>/common/step_q.gif" alt="info" title="<spring:message code="clm.page.msg.manage.announce064" htmlEscape="true" />" /></th>
		<td colspan="5">
			<c:choose>
				<c:when test="${contractCommand.page_gbn=='modify'}">
					<span class="tL">
		            	<!-- a href="javascript:openChooseClient();"><img src="<%=IMAGE %>/btn/btn_wrap_fr_add.gif" alt="<spring:message code="clm.page.msg.manage.add" htmlEscape="true" />" /></a -->
		            	<span class="btn_all_b" onclick="javascript:openChooseClient();"><span class="add"></span><a><spring:message code="clm.page.msg.manage.add" htmlEscape="true" /></a></span><!-- 추가 -->
		            </span>
					<span id="id_trgtman_nm">
						<c:forEach var="list" items="${listCa}" varStatus="cnt">
							<c:if test="${(cnt.count != '1' && cnt.count != '2') && (cnt.count mod 3 == '1')}"><br/></c:if> 
							<c:if test="${cnt.count != '1' && (cnt.count mod 3 != '1')}">,</c:if>
							
							<input type="hidden" name="arr_demnd_seqno" 		id="arr_demnd_seqno" 		value="<c:out value='${list.demnd_seqno}'/>" />
							<input type="hidden" name="arr_trgtman_id" 			id="arr_trgtman_id" 		value="<c:out value='${list.trgtman_id}'/>" />
							<input type="hidden" name="arr_trgtman_nm" 			id="arr_trgtman_nm" 		value="<c:out value='${list.trgtman_nm}'/>" />
							<input type="hidden" name="arr_trgtman_jikgup_nm" 	id="arr_trgtman_jikgup_nm" 	value="<c:out value='${list.trgtman_jikgup_nm}'/>" />
							<input type="hidden" name="arr_trgtman_dept_nm" 	id="arr_trgtman_dept_nm" 	value="<c:out value='${list.trgtman_dept_nm}'/>" />
							
							<c:out value='${list.trgtman_nm}'/>/<c:out value='${list.trgtman_jikgup_nm}'/>/<c:out value='${list.trgtman_dept_nm}'/>							
						</c:forEach>
					</span>					
				</c:when>
				<c:otherwise>
					<c:choose>
					<c:when test ="${!empty listCa}">
						<c:forEach var="list" items="${listCa}" varStatus="cnt">
							<c:if test="${(cnt.count != '1' && cnt.count != '2') && (cnt.count mod 3 == '1')}"><br/></c:if> 
							<c:if test="${cnt.count != '1' && (cnt.count mod 3 != '1')}">,</c:if>
							<c:out value='${list.trgtman_nm}'/>/<c:out value='${list.trgtman_jikgup_nm}'/>/<c:out value='${list.trgtman_dept_nm}'/>							
						</c:forEach>
					</c:when>
					<c:otherwise>						
					</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>		
		</td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.common.otherParty" /></th>
		<td>
			<input type="hidden" id="cntrt_oppnt_nm" name="cntrt_oppnt_nm" value="<c:out value="${contractLom.cntrt_oppnt_nm}" />" />
			<a href="javascript:customerPop2('<c:out value="${contractLom.cntrt_oppnt_cd}" />', '<c:out value="${contractLom.cntrt_oppnt_cd}" />');"><c:out value="${contractLom.cntrt_oppnt_nm}" /> </a></td>
		<th><spring:message code="clm.page.field.customer.registerNo" /></th>
		<td><c:out value="${contractLom.cntrt_oppnt_rprsntman}" escapeXml="false"/></td>
		<th><spring:message code="clm.page.field.customer.contractRequired" /></th>
		<td><c:out value="${contractLom.cntrt_oppnt_type_nm}" /></td>
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
		<!-- 수정가능필드 -->
		<th><spring:message code="clm.page.msg.manage.contPeriod" /></th>
		<td>
			<c:choose>
				<c:when test="${contractCommand.page_gbn=='modify'}">
					<input type="text" name="cntrtperiod_startday" id="cntrtperiod_startday" required alt="<spring:message code="clm.page.msg.manage.contPerStrtDt" htmlEscape="true" />" value="<c:out value='${contractLom.cntrtperiod_startday}'/>" class="text_calendar02"/> ~ 
					<input type="text" name="cntrtperiod_endday" id="cntrtperiod_endday" required alt="<spring:message code="clm.page.msg.manage.contPerEndDt" htmlEscape="true" />" value="<c:out value='${contractLom.cntrtperiod_endday}'/>" class="text_calendar02" />
				</c:when>
				<c:when test="${contractCommand.page_gbn=='history'}">
					<c:out value="${orgMngLom.cntrtperiod_startday}" /> ~ <c:out value="${orgMngLom.cntrtperiod_endday}" />
				</c:when>
				<c:otherwise>
					<input type="hidden" name="cntrtperiod_startday" id="cntrtperiod_startday" value="<c:out value='${contractLom.cntrtperiod_startday}'/>"/> 
					<input type="hidden" name="cntrtperiod_endday" id="cntrtperiod_endday" value="<c:out value='${contractLom.cntrtperiod_endday}'/>"/>
					<c:out value="${contractLom.cntrtperiod_startday}" /> ~ <c:out value="${contractLom.cntrtperiod_endday}" />
				</c:otherwise>
			</c:choose>
		</td>
		
		<!-- 수정가능필드 -->
		<th><spring:message code="clm.page.msg.manage.autoExpYn" /></th>
		<td>		
			<c:choose>
				<c:when test="${contractCommand.page_gbn=='modify'}">
					<c:choose>
						<c:when test="${contractLom.auto_rnew_yn=='Y'}">
							<input type="radio" name="auto_rnew_yn" id="auto_rnew_yn" class="radio" value="Y" checked>Yes</input>
							<input type="radio" name="auto_rnew_yn" id="auto_rnew_yn" class="radio" value="N" >No</input>
						</c:when>
						<c:otherwise>					
							<input type="radio" name="auto_rnew_yn" id="auto_rnew_yn" class="radio" value="Y" >Yes</input>
							<input type="radio" name="auto_rnew_yn" id="auto_rnew_yn" class="radio" value="N" checked>No</input>	
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:when test="${contractCommand.page_gbn=='history'}">
					<c:choose>
						<c:when test="${orgMngLom.auto_rnew_yn=='Y'}">
							<input type="radio" name="auto_rnew_yn" id="auto_rnew_yn" class="radio" value="Y" checked disabled="disabled">Yes</input>
							<input type="radio" name="auto_rnew_yn" id="auto_rnew_yn" class="radio" value="N" disabled="disabled">No</input>
						</c:when>
						<c:otherwise>					
							<input type="radio" name="auto_rnew_yn" id="auto_rnew_yn" class="radio" value="Y" disabled="disabled">Yes</input>
							<input type="radio" name="auto_rnew_yn" id="auto_rnew_yn" class="radio" value="N" checked disabled="disabled">No</input>	
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
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
				</c:otherwise>
			</c:choose>
		</td>		
		<th><spring:message code="clm.page.msg.manage.sendRcvDiv" /></th>
		<td><c:out value="${contractLom.payment_gbn_nm}" escapeXml="false" /></td>
	</tr>
	<tr>
		<!-- 수정가능필드 -->
		<th><spring:message code="clm.page.msg.manage.contAmt" /></th>
		<c:if test="${contractLom.cntrt_untprc_expl}"></c:if>
		<td>
			<c:choose>
				<c:when test="${contractCommand.page_gbn=='modify'}">
					<input type="text" name="cntrt_amt" id="cntrt_amt" value="<c:out value='${contractLom.cntrt_amt}' />"  onkeyup='olnyNum(this)' style="width: 150px; text-align: right; margin-right: 1px; IME-MODE: disabled;" class="text_full" maxlength="18" />
				</c:when>
				<c:when test="${contractCommand.page_gbn=='history'}">
					<c:out value="${orgMngLom.cntrt_amt}" />
				</c:when>
				<c:otherwise>
					<input type="hidden" name="cntrt_amt" id="cntrt_amt" value="<c:out value='${contractLom.cntrt_amt}'/>"/>
					<c:out value="${contractLom.cntrt_amt}" />
				</c:otherwise>
			</c:choose>
		</td>
		<!-- 
		<td colspan='2'><input type='checkbox' class='checkbox'
			<c:if test="${contractLom.cntrt_untprc_expl}">checked</c:if> disabled>
				<spring:message code="clm.page.msg.manage.conclSingleAmt" /> 
		</td> -->
		
		<!-- 수정가능필드 -->
		<th><spring:message code="clm.page.msg.manage.curr" /></th>
		<td colspan='3'>
			<c:choose>
				<c:when test="${contractCommand.page_gbn=='modify'}">
					<select name="crrncy_unit" id="crrncy_unit"></select>
				</c:when>
				<c:when test="${contractCommand.page_gbn=='history'}">
					<c:out value="${orgMngLom.crrncy_unit}" />
				</c:when>
				<c:otherwise>
					<input type="hidden" id="crrncy_unit" name="crrncy_unit" value="<c:out value='${contractLom.crrncy_unit}'/>"/>
					<c:out value="${contractLom.crrncy_unit}" />
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<c:if test="${contractLom.cntrt_untprc_expl}">
		<tr>
			<th><spring:message code="clm.page.msg.manage.contUnitPriceSum" /></th>
			<td colspan='5'><c:out value="${contractLom.cntrt_untprc_expl}" />
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
	<!-- 
	<tr>
		<th rowspan="3">첨부 파일</th>
		<td><span class="blueD">계약서</span></td>
		<td colspan="5"><iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationContract" name="fileConsultationContract" frameborder="0" width="100%" height="50px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
		</td>
	</tr>
	<tr>
		<td class="tal_lineL"><span class="blueD">첨부/별첨</span></td>
		<td colspan="5"><iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationEtc" name="fileConsultationEtc" frameborder="0" width="100%" height="50px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
		</td>
	</tr>
	<tr>
		<td class="tal_lineL"><span class="blueD">기타</span></td>
		<td colspan="5"><iframe src="<c:url value='/clm/blank.do' />" id="fileConsultationEtc2" name="fileConsultationEtc2" frameborder="0" width="100%" height="50px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
		</td>
	</tr>
	 -->
</table>

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
		<th><spring:message code="clm.page.msg.manage.apprPer" /></th>
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
		<jsp:param name="cntrt_id4" value='<%=StringUtil.bvl((String)contractLom.get("cntrt_id"),"") %>'/>
	</jsp:include>
	
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


	<div class="title_basic mt20">
		<h4><spring:message code="clm.page.msg.manage.conclInf" /></h4>
	</div>
	<!-- 체결품의정보시작 : default : display:none -->
	<div id="contract-cnclsnInfo-view">
		<div class="title_basic">
			<div class="title_basic3"><spring:message code="clm.page.msg.manage.bscInf" /></div>
		</div>
		<table cellspacing="0" cellpadding="0" border="0"
			class="table-style01">
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
						<c:when test="${contractLom.cntrt_cnclsn_yn=='Y'}"><spring:message code="clm.page.field.contract.ConclusionYes"/></c:when>
						<c:otherwise><spring:message code="clm.page.field.contract.ConclusionNo"/></c:otherwise>
					</c:choose>
				</td>
				<!-- 수정가능필드 -->
				<th><spring:message code="clm.page.msg.manage.conclResDate" /></th>
				<td>
				<c:choose>
					<c:when test="${contractCommand.page_gbn=='modify'}">
						<input type="text" name="cnclsn_plndday" id="cnclsn_plndday" value="<c:out value='${contractLom.cnclsn_plndday}'/>"  class="text_calendar02" required/>
					</c:when>
					<c:when test="${contractCommand.page_gbn=='history'}">
						<c:out value='${orgMngLom.cnclsn_plndday}' />
					</c:when>
					<c:otherwise>
						<input type="hidden" name="cnclsn_plndday" id="cnclsn_plndday" value="<c:out value='${contractLom.cnclsn_plndday}'/>"/>
						<c:out value='${contractLom.cnclsn_plndday}' />
					</c:otherwise>
				</c:choose>
				</td>

				<th><spring:message code="clm.page.msg.manage.contConclDt" /></th>
				<c:choose>
					<c:when test="${contractCommand.page_gbn=='history'}">
						<td><c:out value='${orgMngLom.cntrt_cnclsnday}' /></td>
					</c:when>
					<c:otherwise>
						<td><c:out value='${contractLom.cntrt_cnclsnday}' /></td>
					</c:otherwise>
				</c:choose>
			</tr>

			<tr>
				<!-- 수정가능필드 -->
				<th><spring:message code="clm.page.msg.manage.signType" /></th>
				<td>
				<c:choose>
					<c:when test="${contractCommand.page_gbn=='modify'}">
					
						<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02102" checked><spring:message code="clm.page.msg.manage.sign" /></input>
						
						<!-- 
						<c:choose>
							<c:when test="${contractLom.seal_mthd=='C02101'}">
								<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02101" checked onclick="javascript:setSealMethod('C02101');"><spring:message code="clm.page.msg.manage.userSign" /></input>
								<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02103" onclick="javascript:setSealMethod('C02103');"><spring:message code="clm.page.msg.manage.signCorp" /></input>
								<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02102" onclick="javascript:setSealMethod('C02102');"><spring:message code="clm.page.msg.manage.sign" /></input>
							</c:when>
							<c:when test="${contractLom.seal_mthd=='C02103'}">
								<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02101" onclick="javascript:setSealMethod('C02101');"><spring:message code="clm.page.msg.manage.userSign" /></input>
								<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02103" checked onclick="javascript:setSealMethod('C02103');"><spring:message code="clm.page.msg.manage.signCorp" /></input>
								<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02102" onclick="javascript:setSealMethod('C02102');"><spring:message code="clm.page.msg.manage.sign" /></input>
							</c:when>
							<c:otherwise>
								<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02101" onclick="javascript:setSealMethod('C02101');"><spring:message code="clm.page.msg.manage.userSign" /></input>
								<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02103" onclick="javascript:setSealMethod('C02103');"><spring:message code="clm.page.msg.manage.signCorp" /></input>
								<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02102" checked onclick="javascript:setSealMethod('C02102');"><spring:message code="clm.page.msg.manage.sign" /></input>	
							</c:otherwise>
						</c:choose>
						 -->
						 						
					</c:when>
					<c:when test="${contractCommand.page_gbn=='history'}">
						<c:out value='${orgMngLom.seal_mthd_nm}' />
					</c:when>
					<c:otherwise>
						<input type="hidden" name="seal_mthd" id="seal_mthd" value="<c:out value='${contractLom.seal_mthd}' />" />
						<c:out value='${contractLom.seal_mthd_nm}' />
					</c:otherwise>
				</c:choose>
				</td>

				<!-- 수정가능필드 -->
						<th  ><spring:message code="clm.page.msg.manage.signRes" /></th>
						<td colspan="3">
							<c:choose>
								<c:when test="${contractCommand.page_gbn eq 'modify'}">								
									<input type="hidden" name="sign_plndman_id" id="sign_plndman_id" value="<c:out value='${contractLom.sign_plndman_id}'/>" />
							  		<input type="hidden" name="sign_plndman_nm" id="sign_plndman_nm" value="<c:out value='${contractLom.sign_plndman_nm}'/>" />
							  		<input type="hidden" name="sign_plndman_jikgup_nm" id="sign_plndman_jikgup_nm" value="<c:out value='${contractLom.sign_plndman_jikgup_nm}'/>"/>
							  		<input type="hidden" name="sign_plnd_dept_nm" id="sign_plnd_dept_nm" value="<c:out value='${contractLom.sign_plnd_dept_nm}'/>"/>
							  		<input type="text" name="sign_plndman_search_nm" id="sign_plndman_search_nm" value="<c:out value='${contractLom.sign_plndman_nm}'/>" style="width:110px" class="text_search" alt="<spring:message code='clm.page.field.contract.consultation.detail.signplannernm'/>" onkeydown="if(event.keyCode==13){event.returnValue = false;javascript:searchEmployee('sign');}"/><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchEmployee('sign');" class="cp" alt="search" />
							  		<span id="plndman">&nbsp;&nbsp;<c:out value='${contractLom.sign_plndman_jikgup_nm}'/>/<c:out value='${contractLom.sign_plnd_dept_nm}'/></span>
								</c:when>
								<c:when test="${contractCommand.page_gbn  eq 'history'}">
									<c:out value='${orgMngLom.sign_plndman_nm}' />/<c:out value='${orgMngLom.sign_plndman_jikgup_nm}' />/<c:out value='${orgMngLom.sign_plnd_dept_nm}' />
								</c:when>
								<c:otherwise>
									<input type="hidden" name="sign_plndman_id" id="sign_plndman_id" value="<c:out value='${contractLom.sign_plndman_id}'/>" />
							  		<input type="hidden" name="sign_plndman_nm" id="sign_plndman_nm" value="<c:out value='${contractLom.sign_plndman_nm}'/>" />
							  		<input type="hidden" name="sign_plndman_jikgup_nm" id="sign_plndman_jikgup_nm" value="<c:out value='${contractLom.sign_plndman_jikgup_nm}'/>"/>
							  		<input type="hidden" name="sign_plnd_dept_nm" id="sign_plnd_dept_nm" value="<c:out value='${contractLom.sign_plnd_dept_nm}'/>"/>
									<c:out value='${contractLom.sign_plndman_nm}' />/<c:out value='${contractLom.sign_plndman_jikgup_nm}' />/<c:out value='${contractLom.sign_plnd_dept_nm}' />
								</c:otherwise>
							</c:choose>						
						</td>
			</tr>

			<c:choose>
				<c:when test="${contractCommand.page_gbn eq 'modify'}">
					<tr id="sign2_tr1">
						<th><spring:message code="clm.page.msg.manage.signPerSE" /></th>
						<td colspan=3>
							<input type="hidden" name="signman_id" id="signman_id" value="<c:out value='${contractLom.signman_id}'/>" alt="<spring:message code="clm.page.field.contract.conclusion.detail.signmannm"/>"/>
							<input type="hidden" name="signman_jikgup_nm" id="signman_jikgup_nm" value="<c:out value='${contractLom.signman_jikgup_nm}'/>" />
							<input type="hidden" name="signman_nm" id="signman_nm" value="<c:out value='${contractLom.signman_nm}'/>" alt="<spring:message code="clm.page.field.contract.conclusion.detail.signmannm"/>"/>
							<input type="hidden" name="sign_dept_nm" id="sign_dept_nm" value="<c:out value='${contractLom.sign_dept_nm}'/>" />
							<input type="text" name="signman_search_nm" id="signman_search_nm" value="<c:out value='${contractLom.signman_nm}'/>" style="width:102px" class="text_search" alt="<spring:message code="clm.page.field.contract.conclusion.detail.signmannm"/>" onkeydown="if(event.keyCode==13){event.returnValue = false;javascript:searchEmployee('sign2');}"/><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchEmployee('sign2');" class="cp" alt="search" />
							<span id="signman">&nbsp;&nbsp;<c:out value='${contractLom.signman_jikgup_nm}'/>/<c:out value='${contractLom.sign_dept_nm}'/></span>
						
						</td>
						<th><spring:message code="clm.page.msg.manage.signDateSE" /></th>
						<td><input type="text" name="signday" id="signday" value="<c:out value='${contractLom.signday}'/>"  class="text_calendar02" alt="<spring:message code="clm.page.field.contract.conclusion.detail.signday"/>"/></td>
					</tr>
					<tr id="sign2_tr2">
						<th><spring:message code="clm.page.msg.manage.signPerOth" /></th>
						<td colspan=3>
							<span><spring:message code="clm.page.msg.common.name" /></span>
							<input type="text" name="oppnt_signman_nm" id="oppnt_signman_nm" value="<c:out value='${contractLom.oppnt_signman_nm}'/>"  required maxLength="100" alt="<spring:message code="clm.page.field.contract.conclusion.detail.oppntsignmannm"/>"/>
							<span><spring:message code="clm.page.msg.manage.level" /></span>
							<input type="text" name="oppnt_signman_jikgup" id="oppnt_signman_jikgup" value="<c:out value='${contractLom.oppnt_signman_jikgup}'/>" required maxLength="50" alt="<spring:message code="clm.page.field.choosesealperson.jikgupnm"/>" />
							<span><spring:message code="clm.page.msg.manage.deptName" /></span>
							<input type="text" name="oppnt_signman_dept" id="oppnt_signman_dept" value="<c:out value='${contractLom.oppnt_signman_dept}'/>" required maxLength="50" alt="<spring:message code="clm.page.field.contract.conclusion.detail.signdeptnm"/>" />
						</td>
						<th><spring:message code="clm.page.msg.manage.signDateOth" /></th>
						<td><input type="text" name="oppnt_signday" id="oppnt_signday" value="<c:out value='${contractLom.oppnt_signday}'/>" required class="text_calendar02" alt="<spring:message code="clm.page.field.contract.conclusion.detail.oppntsignday"/>"/></td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<th><spring:message code="clm.page.msg.manage.signPerSE" /></th>
						<td colspan=3><c:out value='${contractLom.signman_nm}' />/<c:out value='${contractLom.signman_jikgup_nm}' />/<c:out value='${contractLom.sign_dept_nm}' /></td>
						<th><spring:message code="clm.page.msg.manage.signDateSE" /></th>
						<td ><c:out value='${contractLom.signday}' /></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.msg.manage.signPerOth" /></th>
						<td colspan=3><c:out value='${contractLom.oppnt_signman_nm}' escapeXml="false"/>/<c:out value='${contractLom.oppnt_signman_jikgup}' escapeXml="false"/>/<c:out value='${contractLom.oppnt_signman_dept}' escapeXml="false"/></td>
						<th><spring:message code="clm.page.msg.manage.signDateOth" /></th>
						<td ><c:out value='${contractLom.oppnt_signday}' />
					</td>
				</tr>
				</c:otherwise>
				</c:choose>


			<tr>
				<!-- 수정가능필드 -->
				<th><spring:message code="clm.page.msg.manage.chrgCont" /></th>
				<td colspan="3">
					<c:choose>
						<c:when test="${contractCommand.page_gbn=='modify'}">
							<input type="hidden" name="cntrt_respman_id" 	id="cntrt_respman_id" value="<c:out value='${contractLom.cntrt_respman_id}'/>" />
							<input type="hidden" name="cntrt_respman_nm" 	id="cntrt_respman_nm" value="<c:out value='${contractLom.cntrt_respman_nm}'/>" />
							<input type="hidden" name="cntrt_resp_dept" 	id="cntrt_resp_dept" value="<c:out value='${contractLom.cntrt_resp_dept}'/>" />							
							<input type="hidden" name="cntrt_resp_up_dept" 	id="cntrt_resp_up_dept" value="<c:out value='${contractLom.cntrt_resp_up_dept}'/>" />
							<input type="text"   name="cntrt_respman_search_nm" id="cntrt_respman_search_nm" value="<c:out value='${contractLom.cntrt_respman_nm}'/>" style="width:110px" class="text_search" onKeyDown="if(event.keyCode==13){event.returnValue = false;javascript:searchEmployee('contract');}"/><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchEmployee('contract')" class="cp" alt="search" />
							<input type="hidden" name="cntrt_respman_jikgup_nm" id="cntrt_respman_jikgup_nm" value="<c:out value='${contractLom.cntrt_respman_jikgup_nm}'/>" />
							<input type="hidden" name="cntrt_resp_dept_nm" 	id="cntrt_resp_dept_nm" value="<c:out value='${contractLom.cntrt_resp_dept_nm}'/>" />
					  		<span id="cntrt_respman">&nbsp;&nbsp;<c:out value='${contractLom.cntrt_respman_jikgup_nm}'/>/<c:out value='${contractLom.cntrt_resp_dept_nm}'/></span>
						</c:when>
						<c:when test="${contractCommand.page_gbn=='history'}">
							<c:out value='${orgMngLom.cntrt_respman_nm}' />/<c:out value='${orgMngLom.cntrt_respman_jikgup_nm}' />/<c:out value='${orgMngLom.cntrt_resp_dept_nm}' />
						</c:when>
						<c:otherwise>
							<input type="hidden" name="cntrt_respman_id" 	id="cntrt_respman_id" value="<c:out value='${contractLom.cntrt_respman_id}'/>" />
							<input type="hidden" name="cntrt_respman_nm" 	id="cntrt_respman_nm" value="<c:out value='${contractLom.cntrt_respman_nm}'/>" />
							<input type="hidden" name="cntrt_resp_dept" 	id="cntrt_resp_dept" value="<c:out value='${contractLom.cntrt_resp_dept}'/>" />							
							<input type="hidden" name="cntrt_resp_up_dept" 	id="cntrt_resp_up_dept" value="<c:out value='${contractLom.cntrt_resp_up_dept}'/>" />
							<input type="hidden" name="cntrt_respman_jikgup_nm" id="cntrt_respman_jikgup_nm" value="<c:out value='${contractLom.cntrt_respman_jikgup_nm}'/>" />
							<input type="hidden" name="cntrt_resp_dept_nm" 	id="cntrt_resp_dept_nm" value="<c:out value='${contractLom.cntrt_resp_dept_nm}'/>" />
							<c:out value='${contractLom.cntrt_respman_nm}' />/<c:out value='${contractLom.cntrt_respman_jikgup_nm}' />/<c:out value='${contractLom.cntrt_resp_dept_nm}' />
						</c:otherwise>
					</c:choose>
				</td>

				<!-- 수정가능필드 -->
<%--	신성우 주석처리 2014-03-31
				<th><spring:message code="clm.page.msg.manage.preAnnDate" /></th>
				<td >
					<c:choose>
						<c:when test="${contractCommand.page_gbn=='modify'}">
							<input type="text" name="exprt_notiday" id="exprt_notiday" value="<c:out value='${contractLom.exprt_notiday}'/>" class="text_calendar02" required alt="<spring:message code='clm.page.field.contract.consultation.detail.expirenotifyday'/>"/>
						</c:when>
						<c:when test="${contractCommand.page_gbn=='history'}">
							<c:out value='${orgMngLom.exprt_notiday}' />
						</c:when>
						<c:otherwise>
							<c:out value='${contractLom.exprt_notiday}' />
							<input type="hidden" name="exprt_notiday" id="exprt_notiday" value="<c:out value='${contractLom.exprt_notiday}'/>"/>
						</c:otherwise>
					</c:choose>
				</td>
--%>
			</tr>
		</table>
		<br> 
				<div class="title_basic">
					<div class="title_basic3"><spring:message code="clm.page.msg.manage.copyVerInf" /></div>
				</div>
				<table cellspacing="0" cellpadding="0" border="0"
					class="table-style01">
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
						<th><spring:message code="clm.page.msg.manage.conclCopy" /></th>
						<td colspan="3">
							<iframe	src="<c:url value='/clm/blank.do' />" id="fileContractCopy" name="fileContractCopy" frameborder="0" width="100%" height="50px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
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
				<div id="org_info">
					<div class="title_basic">
						<div class="title_basic3"><spring:message code="clm.page.msg.manage.orgInf" /></div>
					</div>
					<table cellspacing="0" cellpadding="0" border="0"
						class="table-style01">
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
										<input type="text" name="org_hdovman_search_nm" id="org_hdovman_search_nm" value="<c:out value='${contractLom.org_hdovman_nm}'/>" style="width: 110px" class="text_search"
													alt="<spring:message code="clm.page.field.contract.conclusion.detail.signmannm"/>" onkeydown="if(event.keyCode==13) {searchEmployee('hdovman');return false;}" /><img src="<%=IMAGE%>/icon/ico_search.gif"  onclick="javascript:searchEmployee('hdovman');" class="cp" alt="search" />
												
												<input type="hidden" name="org_hdovman_nm" id="org_hdovman_nm" value="<c:out value='${contractLom.org_hdovman_nm}'/>" />
												<input type="hidden" name="org_hdovman_id" id="org_hdovman_id" value="<c:out value='${contractLom.org_hdovman_id}'/>" />
												<input type="hidden" name="org_hdov_dept_nm" id="org_hdov_dept_nm" value="<c:out value='${contractLom.org_hdov_dept_nm}'/>" /> 
												<input type="hidden" name="org_hdovman_jikgup_nm" id="org_hdovman_jikgup_nm" value="<c:out value='${contractLom.org_hdovman_jikgup_nm}'/>" />
												<span id="hdovman">&nbsp;&nbsp;<c:out value='${contractLom.org_hdovman_jikgup_nm}'/>/<c:out value='${contractLom.org_hdov_dept_nm}'/></span>			
									</c:when>
									<c:when test="${contractCommand.page_gbn=='history'}">
										<c:out value='${orgMngLom.org_hdovman_nm}' />/<c:out value='${orgMngLom.org_hdovman_jikgup_nm}' />/<c:out value='${orgMngLom.org_hdov_dept_nm}' />
									</c:when>
									<c:otherwise>
										<input type="hidden" name="org_hdovman_nm" id="org_hdovman_nm" value="<c:out value='${contractLom.org_hdovman_nm}'/>" />
										<input type="hidden" name="org_hdovman_id" id="org_hdovman_id" value="<c:out value='${contractLom.org_hdovman_id}'/>" />
										<input type="hidden" name="org_hdov_dept_nm" id="org_hdov_dept_nm" value="<c:out value='${contractLom.org_hdov_dept_nm}'/>" /> 
										<input type="hidden" name="org_hdovman_jikgup_nm" id="org_hdovman_jikgup_nm" value="<c:out value='${contractLom.org_hdovman_jikgup_nm}'/>" />
										<c:out value='${contractLom.org_hdovman_nm}' />/<c:out value='${contractLom.org_hdovman_jikgup_nm}' />/<c:out value='${contractLom.org_hdov_dept_nm}' />
									</c:otherwise>
								</c:choose>
							</td>
							<th><spring:message code="las.page.field.lawconsideration.groupChief" />
							</th>
							<td><%=session.getAttribute("secfw.session.user_nm")%>/<%=session.getAttribute("secfw.session.grade_nm")%>/<%=session.getAttribute("secfw.session.dept_nm")%>
								<!--<c:out value='${contractLom.org_tkovman_nm}'/>/<c:out value='${contractLom.org_tkovman_jikgup_nm}'/>/<c:out value='${contractLom.org_tkov_dept_nm}'/>-->
							</td>
						</tr>
						<tr>
								<c:choose>
									<c:when test="${contractCommand.page_gbn=='modify'}">
										<th><spring:message code="clm.page.msg.manage.contConclDt" /><span class="astro">*</span><img src='<%=IMAGE%>/btn/btn_info.gif' title="<spring:message code="clm.page.msg.manage.announce031" htmlEscape="true" />" ></th>
										<td>
										<input type="text" name="cntrt_cnclsnday" id="cntrt_cnclsnday" value="<c:out value='${contractLom.cntrt_cnclsnday}' />" class="text_calendar02" alt="<spring:message code="clm.page.msg.manage.contConclDt" htmlEscape="true" />" />
									</c:when>
									<c:when test="${contractCommand.page_gbn=='history'}">
										<th><spring:message code="clm.page.msg.manage.contConclDt" /></th>
										<td>
										<c:out value='${orgMngLom.cntrt_cnclsnday}' />
									</c:when>
									<c:otherwise>
										<th><spring:message code="clm.page.msg.manage.contConclDt" /></th>
										<td>
										<c:out value='${contractLom.cntrt_cnclsnday}' />
									</c:otherwise>
								</c:choose>
							</td>
						
							<th><spring:message code="clm.page.field.contract.conclusion.detail.conclusionorgacceptday" />
							</th>
							<td><c:choose>
									<c:when test="${contractCommand.page_gbn=='modify'}">
										<input type="text" name="org_acptday" id="org_acptday" value="<%=DateUtil.formatDate(DateUtil.today(), "-")%>" class="text_calendar02"
													alt="<spring:message code="clm.page.field.contract.conclusion.detail.conclusionorgacceptday"/>" />
									</c:when>
									<c:when test="${contractCommand.page_gbn=='history'}">
										<c:out value='${orgMngLom.org_acptday}' />
									</c:when>
									<c:otherwise>
										<input type="hidden" name="org_acptday" id="org_acptday" value="<c:out value='${contractLom.org_acptday}'/>" />
										<c:out value='${contractLom.org_acptday}' />
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<th><spring:message code="clm.page.field.contract.conclusion.detail.conclusionorgloacation" /><span class="astro">*</span>
							</th>
							<td colspan="3"><c:choose>
									<c:when test="${contractCommand.page_gbn=='modify'}">
										<input type="text" name="org_strg_pos" id="org_strg_pos" value="<c:out value='${contractLom.org_strg_pos}'/>" style="width: 350px" class="text_full" maxLength="100" />
									</c:when>
									<c:when test="${contractCommand.page_gbn=='history'}">
										<c:out value='${orgMngLom.org_strg_pos}' />
									</c:when>
									<c:otherwise>
										<input type="hidden" name="org_strg_pos" id="org_strg_pos" value="<c:out value='${contractLom.org_strg_pos}'/>"/>
										<c:out value='${contractLom.org_strg_pos}' />
									</c:otherwise>
								</c:choose>
							</td>
						</tr>

						<c:choose>
							<c:when test="${contractCommand.page_gbn=='modify'}">
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
									<c:when test="${contractCommand.page_gbn=='history'}">
										<c:if test="${orgMngLom.org_acpt_dlay_cause != null}">
											<tr>
												<th><spring:message	code="clm.page.field.contract.conclusion.detail.orgacceptdelaycause" />
												</th>
												<td colspan="3">
													<!-- c:out value='${orgMngLom.org_acpt_dlay_cause}' / -->
													<%=str_org_acpt_dlay_cause%>													
												</td>
											</tr>
										</c:if>
									</c:when>
									<c:otherwise>
										<input type="hidden" name="org_acpt_dlay_cause" id="org_acpt_dlay_cause" />
										<c:if test="${contractLom.org_acpt_dlay_cause != null}">
											<tr>
												<th><spring:message	code="clm.page.field.contract.conclusion.detail.orgacceptdelaycause" />
												</th>
												<td colspan="3">
													<!-- c:out value='${contractLom.org_acpt_dlay_cause}' / -->
													<%=str_org_acpt_dlay_cause%>
												</td>
											</tr>
										</c:if>
									</c:otherwise>
								</c:choose>
					</table>
					<br>
	</div>

		<c:if
			test="${contractMstLom.cntrt_cnclsn_yn=='N' && !empty contractCnclsndlayLom}">
			<div class="title_basic">
				<h3><spring:message code="clm.page.msg.manage.conclDelayInf" /></h3>
			</div>
			<table cellspacing="0" cellpadding="0" border="0"
				class="table-style01">
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
	<!--// 체결정보 -->





