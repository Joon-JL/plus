<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="com.sec.clm.review.dto.ConsultationForm" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%--
/**
 * 파  일  명 : Consideration_old_inner_d.jsp
 * 프로그램명 : 검토의뢰   작성
 * 설      명 : (구)법무시스템 검토의뢰 데이터 조회
 * 작  성  자 : 최준영
 * 작  성  일 : 2011.11.07
 */
--%>

<% 
	ConsultationForm command = (ConsultationForm)request.getAttribute("command");
	ListOrderedMap lomRq = (ListOrderedMap)request.getAttribute("lomRq");
	ListOrderedMap lomMn = (ListOrderedMap)request.getAttribute("lomMn");//일반조항 검토의견
	
	StringBuffer resultSb = new StringBuffer(1024);	
	
%>	
<script language="javascript">

</script>
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
				<th><spring:message code="clm.page.msg.common.otherParty" /></th>
				<td colspan="2"><c:out value="${lomRq.cntrt_oppnt_nm}" /></td>
				<th><spring:message code="clm.page.field.customer.registerNo" /></th>
				<td><c:out value="${lomRq.cntrt_oppnt_rprsntman}" /></td>
				<th><spring:message code="clm.page.field.customer.contractRequired" /></th>
				<td><c:out value="${lomRq.cntrt_oppnt_type_nm}" /></td>
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

			<tr class="slide-target02 slide-area">
				<th><spring:message code="clm.page.field.contract.consultation.detail.autorenew" /></th>
				<td colspan="2">
					<c:choose>
					<c:when test="${lomRq.auto_rnew_yn=='Y'}">Yes</c:when>
					<c:otherwise>No</c:otherwise>
					</c:choose>
				</td>
				<th><spring:message code="clm.page.field.contract.conclusion.detail.conclusionday" /></th>
				<td colspan="3">
<%
					if(!"00000000".equals((String)lomRq.get("cntrt_cnclsnday"))) {
						out.println(DateUtil.formatDate((String)lomRq.get("cntrt_cnclsnday"),"-"));
					}
%>
				</td>
				<!-- 				
				<th><spring:message code="clm.page.field.contract.conclusion.detail.conclusionyn" /></th>
				<td colspan="3">
					<c:choose>
					<c:when test="${lomRq.cntrt_cnclsn_yn=='Y'}">Yes</c:when>
					<c:otherwise>No</c:otherwise>
					</c:choose>
				</td>
				 -->
			</tr>
<!-- 
			<tr class="slide-target02 slide-area">
				<th><spring:message code="clm.page.field.contract.consultation.detail.conclusionday" /></th>
				<td colspan="2">
<%
					if(!"00000000".equals((String)lomRq.get("cnclsn_plndday"))) {
						out.println(DateUtil.formatDate((String)lomRq.get("cnclsn_plndday"),"-"));
					}
%>
				</td>
				<th><spring:message code="clm.page.field.contract.conclusion.detail.conclusionday" /></th>
				<td colspan="3">
<%
					if(!"00000000".equals((String)lomRq.get("cntrt_cnclsnday"))) {
						out.println(DateUtil.formatDate((String)lomRq.get("cntrt_cnclsnday"),"-"));
					}
%>
				</td>
			</tr>
 -->
			<tr class="slide-target02 slide-area">
				
<%
					if("C02101".equals((String)lomRq.get("seal_mthd"))){
%>
				<th><spring:message code="las.page.field.contractManager.stempType"/></th>
				<td colspan="6"><c:out value='${lomRq.seal_mthd_nm}'/></td>
				<!-- <th>날인담당자</th>
				<td colspan="3"><c:out value='${lomRq.seal_ffmtman_nm}'/>/<c:out value='${lomRq.seal_ffmtman_jikgup_nm}'/>/<c:out value='${lomRq.seal_ffmt_dept_nm}'/></td> -->
<%						
					}else{
%>
				<th><spring:message code="las.page.field.contractManager.stempType"/></th>
				<td colspan="2"><c:out value='${lomRq.seal_mthd_nm}'/></td>
				<th><spring:message code="las.page.field.contractManager.theSigner"/></th>
				<td colspan="3"><c:out value='${lomRq.signman_nm}'/>/<c:out value='${lomRq.signman_jikgup_nm}'/>/<c:out value='${lomRq.sign_dept_nm}'/></td>
<%						
					}
%>				
			</tr>

			<tr class="slide-target02 slide-area">
				<th><spring:message code="las.page.field.contractManager.keepOrgnYn"/></th>
				<td colspan="6">
					<c:choose>
					<c:when test="${lomRq.org_strg_pos=='Y'}">Yes</c:when>
					<c:otherwise>No</c:otherwise>
					</c:choose>
				</td>
			</tr>

<%
			} else {		// 구법무 이관 데이터
%>
			<tr>
				<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.cntrt_oppnt_nm" /></th>
				<td colspan="6"><c:out value="${lomRq.cntrt_oppnt_nm}" /></td>
			</tr>
			<tr class="slide-target02 slide-area">			
				<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.cntrt_type" /></th>				
				<td colspan="6">
					<c:out value="${lomRq.biz_clsfcn_nm}" /> / 
					<c:out value="${lomRq.depth_clsfcn_nm}" /> / 
					<c:out value="${lomRq.cnclsnpurps_bigclsfcn_nm}" /> / 
					<c:out value="${lomRq.cnclsnpurps_midclsfcn_nm}" />
				</td>
			</tr>
			<tr class="slide-target02 slide-area">
				<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.cntrtperiod" /></th>
				<td colspan="6">
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
			</tr>
			<tr class="slide-target02 slide-area">
				<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.cntrt_amt" /></th>
				<td colspan="3">
					<%if(lomRq.get("cntrt_amt_chg") != null) {%>
						<%=lomRq.get("cntrt_amt_chg") %>
					<%} %>
				</td>
				<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.currency" /></th>
				<td colspan="2"><c:out value="${lomRq.crrncy_unit}" /></td>
			</tr>
<%
			}
%>




			<tr class="slide-target02 slide-area">
				<th rowspan="3"><spring:message code="las.page.field.contractmanager.consideration_inner_d.attached_file" /></th>
				<td class="blueD"><span class="blueD"><spring:message code="las.page.field.contractmanager.consideration_inner_d.contract" /></span></td>
				<td colspan="5">
					<iframe src="<c:url value='/clm/blank.do' />" id="fileList1" name="fileList1" frameborder="0" width="100%" height="100px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
				</td>
			</tr> 
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
		
	    		
	    		
			
    		
			
			
			