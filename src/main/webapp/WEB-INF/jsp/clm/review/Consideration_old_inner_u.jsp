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
 * 파  일  명 : Consideration_old_inner_u.jsp
 * 프로그램명 : 검토의뢰   작성
 * 설      명 : (구)법무시스템 검토의뢰 데이터 수정
 * 작  성  자 : 김형준
 * 작  성  일 : 2012.01.30
 */
--%>

<% 
	ConsultationForm command = (ConsultationForm)request.getAttribute("command");
	ListOrderedMap lomRq = (ListOrderedMap)request.getAttribute("lomRq");
	ListOrderedMap lomMn = (ListOrderedMap)request.getAttribute("lomMn");//일반조항 검토의견
	
	StringBuffer resultSb = new StringBuffer(1024);	
	
%>	
<script language="javascript">
//금액에 숫자만 입력하게 합니다. 숫자값이 아니면 그냥 빈칸으로 만들어 버리죠.
function olnyNum(obj){
	var str = obj.value;
	str = new String(str);
	var Re = /[^0-9]/g;
	str = str.replace(Re,'');
	
	// 금액 100,000 형태로 변환 추가 (2011/10/15)
	obj.value = Comma(str);
}

$(document).ready(function(){
	var frm = document.frm;
	var amt = frm.cntrt_amt.value;
	frm.cntrt_amt.value = Comma(amt);
		
	initCal("cntrtperiod_startday");
	initCal("cntrtperiod_endday");
	//initCal("cnclsn_plndday");    //체결예정일
	initCal("cntrt_cnclsnday");    //계약체결일
	
	//비즈니스 분류
	getCodeSelectByUpCd("frm", "biz_clsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=T01&combo_abbr=F&combo_type=T&combo_del_yn=N&combo_selected=<c:out value='${lomRq.biz_clsfcn}'/>");
	//단계
	getCodeSelectByUpCd("frm", "depth_clsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=T02&combo_abbr=F&combo_type=T&combo_del_yn=N&combo_selected=<c:out value='${lomRq.depth_clsfcn}'/>");
	//체결목적 대분류
	getCodeSelectByUpCd("frm", "cnclsnpurps_bigclsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=T03&combo_abbr=F&combo_type=T&combo_del_yn=N&combo_selected=<c:out value='${lomRq.cnclsnpurps_bigclsfcn}'/>");
	//체결목적 중분류
	getCodeSelectByUpCd("frm", "cnclsnpurps_midclsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=<c:out value='${lomRq.cnclsnpurps_bigclsfcn}'/>&combo_abbr=F&combo_type=T&combo_del_yn=N&combo_selected=<c:out value='${lomRq.cnclsnpurps_midclsfcn}'/>");

	//거래상재방 업체 Type - cntrt_oppnt_type	
	getCodeSelectByUpCd("frm", "cntrt_oppnt_type", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=F&combo_grp_cd=C056&combo_type=S&combo_del_yn=N&combo_selected=<c:out value='${lomRq.cntrt_oppnt_type}'/>');
	//계약대상  - cntrt_trgt		
	getCodeSelectByUpCd("frm", "cntrt_trgt", '/common/clmsCom.do?method=getComboHTML&combo_sys_cd=CLM&combo_gbn=CONTRACTTYPE&combo_up_cd=<c:out value='${lomRq.cnclsnpurps_midclsfcn}'/>&combo_abbr=F&combo_type=T&combo_del_yn=N&combo_selected=<c:out value='${lomRq.cntrt_trgt}'/>');
	//지불구분
	getCodeSelectByUpCd("frm", "payment_gbn", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=F&combo_grp_cd=C020&combo_type=&combo_del_yn=N&combo_selected=<c:out value='${lomRq.payment_gbn}'/>');
	//화페단위
	getCodeSelectByUpCd("frm", "crrncy_unit", '/common/clmsCom.do?method=listComCdByGrpCd&combo_abbr=A&combo_grp_cd=C5&combo_type=S&combo_del_yn=N&combo_selected=<c:out value='${lomRq.crrncy_unit}'/>');	
});

function changeSubCd(selObjId, gbn, upCd) {
	if(upCd == "") {
    	upCd = "XXX";
	}
	getCodeSelectByUpCd('frm', selObjId, '/common/clmsCom.do?method=getComboHTML&combo_gbn=' + gbn + '&combo_up_cd=' + upCd + '&combo_abbr=F&combo_type=T&combo_del_yn=N');
}
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
				<td colspan="6"><c:out value="${lomRq.cntrt_nm}" /> 
				</td>
			</tr>

			<tr>
				<th><spring:message code="clm.page.msg.common.otherParty" /></th>
				<td colspan="2">
					<input type="text" name="cntrt_oppnt_nm" id="cntrt_oppnt_nm" value="<c:out value="${lomRq.cntrt_oppnt_nm}" />" class="text_full" style="width:80%">
				</td>
				<th><spring:message code="clm.page.field.customer.registerNo" /></th>
				<td>
					<input type="text" name="cntrt_oppnt_rprsntman" id="cntrt_oppnt_rprsntman" value="<c:out value="${lomRq.cntrt_oppnt_rprsntman}" />" maxlength="60" class="text_full" style="width:80%">
				</td>
				<th><spring:message code="clm.page.field.customer.contractRequired" /></th>
				<td>
					<select name="cntrt_oppnt_type" id="cntrt_oppnt_type"></select>
				</td>
			</tr>
			<tr class="slide-target02 slide-area">			
				<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.cntrt_type" /></th>				
				<td colspan="6">
					<select style="width:140px;" name="biz_clsfcn" id="biz_clsfcn" >
					</select> 
					<select style="width:140px" name="depth_clsfcn" id="depth_clsfcn" >
					</select>
					<select style="width:140px" name="cnclsnpurps_bigclsfcn" id="cnclsnpurps_bigclsfcn" onChange='javascript:changeSubCd("cnclsnpurps_midclsfcn", "CONTRACTTYPE", this.value); changeSubCd("cntrt_trgt", "CONTRACTTYPE", "");'>
					</select>
					<select style="width:140px;" name="cnclsnpurps_midclsfcn" id="cnclsnpurps_midclsfcn" onChange='javascript:changeSubCd("cntrt_trgt", "CONTRACTTYPE", this.value);'>
						<option value=''>-- select --</option>
					</select>
				</td>
			</tr>
			<tr>
				<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.cntrt_trgt_nm" /></th>
				<td colspan="6">
					<select name="cntrt_trgt" id="cntrt_trgt" class="all" style="width:140px"></select>
				</td>
			</tr>
			<tr class="slide-target02 slide-area">
				<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.cntrtperiod" /></th>
				<td colspan="2">
<%
					String cntrtperiod_startday = "";
					String cntrtperiod_endday = "";
					if("00000000".equals((String)lomRq.get("cntrtperiod_startday")) && "99999999".equals((String)lomRq.get("cntrtperiod_endday"))) {
						
					} else {
						if(!"00000000".equals((String)lomRq.get("cntrtperiod_startday"))) {
							cntrtperiod_startday = DateUtil.formatDate((String)lomRq.get("cntrtperiod_startday"),"-");
						}
						if(!"99999999".equals((String)lomRq.get("cntrtperiod_endday"))) {
							cntrtperiod_endday = DateUtil.formatDate((String)lomRq.get("cntrtperiod_endday"),"-");
						}
					}
%>				
					<input type="text" name="cntrtperiod_startday" id="cntrtperiod_startday" alt="<spring:message code="las.page.field.contractManager.contFromDate"/>" value="<%=cntrtperiod_startday%>" class="text_calendar02" readOnly/> ~ 
					<input type="text" name="cntrtperiod_endday" id="cntrtperiod_endday" alt="<spring:message code="las.page.field.contractManager.contToDate"/>" value="<%=cntrtperiod_endday%>" class="text_calendar02" readOnly/>
				</td>
				<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.payment_gbn_nm" /></th>
				<td colspan="3">
					<select name="payment_gbn" id="payment_gbn" class="all" style="width:150px">						
					</select>
				</td>
			</tr>
			
			<tr class="slide-target02 slide-area">
				<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.cntrt_amt" /></th>
				<td colspan="2">
					<input type="text" name="cntrt_amt" id="cntrt_amt" value="<c:out value='${lomRq.cntrt_amt}' />" onkeyup='olnyNum(this)' style="width: 150px; text-align: right; margin-right: 1px; IME-MODE: disabled;" class="text_full" maxlength="19" />
				</td>
				<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.currency" /></th>
				<td colspan="3">
					<select name="crrncy_unit" id="crrncy_unit">						
					</select>
				</td>
			</tr>

			<tr class="slide-target02 slide-area">
				<th><spring:message code="clm.page.field.contract.consultation.detail.autorenew" /></th>
				<td colspan="2">
					<select name="auto_rnew_yn" id="auto_rnew_yn" style="width:100px;">
						<option value="Y" <c:if test="${lomRq.auto_rnew_yn=='Y'}">selected</c:if> >Yes</option>
						<option value="N" <c:if test="${lomRq.auto_rnew_yn=='N'}">selected</c:if>>No</option>
					</select>
				</td>
				<!-- 
				<th><spring:message code="clm.page.field.contract.conclusion.detail.conclusionyn" /></th>
				<td colspan="3">
					<select name="cntrt_cnclsn_yn" id="cntrt_cnclsn_yn" style="width:100px;">
						<option value="Y" <c:if test="${lomRq.cntrt_cnclsn_yn=='Y'}">selected</c:if> >Yes</option>
						<option value="N" <c:if test="${lomRq.cntrt_cnclsn_yn=='N'}">selected</c:if>>No</option>
					</select>
				</td>
				 -->
				 <th><spring:message code="clm.page.field.contract.conclusion.detail.conclusionday" /></th>
				<td colspan="3">
<%
					String cntrt_cnclsnday = "";
					if(!"00000000".equals((String)lomRq.get("cntrt_cnclsnday"))) {
						cntrt_cnclsnday = DateUtil.formatDate((String)lomRq.get("cntrt_cnclsnday"),"-");
					}
%>				
					<input type="text" name="cntrt_cnclsnday" id="cntrt_cnclsnday" value="<%=cntrt_cnclsnday%>" class="text_calendar02" readOnly/>
				</td>
			</tr>
			

			<tr class="slide-target02 slide-area">
				<th><spring:message code="las.page.field.contractManager.stempType"/></th>
				<td colspan="6">
					<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02101" onClick="javascript:setSealMethod(this.value);"><spring:message code="clm.page.field.contract.consultation.detail.seal"/></input>
					<input type="radio" name="seal_mthd" id="seal_mthd" class="radio" value="C02102" onClick="javascript:setSealMethod(this.value);"><spring:message code="clm.page.field.contract.consultation.detail.sign"/></input>
				</td>
			</tr>
			
			<tr id="sign-tr" style="display:none">
				<th><spring:message code="las.page.field.contractManager.theSigner"/></th>
				<td colspan="6">
					<input type="hidden" name="signman_id" id="signman_id" value="<c:out value='${lomRq.signman_id}'/>" />
			  		<input type="hidden" name="signman_nm" id="signman_nm" value="<c:out value='${lomRq.signman_nm}'/>" />
			  		<input type="hidden" name="signman_jikgup_nm" id="signman_jikgup_nm" value="<c:out value='${lomRq.signman_jikgup_nm}'/>"/>
			  		<input type="hidden" name="sign_dept_nm" id="sign_dept_nm" value="<c:out value='${lomRq.sign_dept_nm}'/>"/>
			  		<input type="text" name="sign_search_nm" id="sign_search_nm" value="" style="width:110px" class="text_search" alt="<spring:message code='clm.page.field.contract.consultation.detail.signplannernm'/>" onKeyDown="if(event.keyCode==13){event.returnValue = false;javascript:searchEmployee('sign');}"/><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchEmployee('sign');" class="cp" alt="search" />
			  		<span id="plndman">&nbsp;&nbsp;<c:out value='${lomRq.signman_nm}'/>/<c:out value='${lomRq.signman_jikgup_nm}'/>/<c:out value='${lomRq.sign_dept_nm}'/></span>
			  		<br><br>
			  		<spring:message code="las.page.field.contractManager.ifManualInput"/>
			  		<a href="javascript:selfInsertPlndman();"><spring:message code="las.page.field.contractManager.manualInput"/></a>
			  		<input type="hidden" name="plndman_self_yn" id="plndman_self_yn" value=""/>
			  		<div id="plndman_self" style="display:none">
			  			<spring:message code="las.page.field.contractManager.nm2"/><input type="text" name="signman_nm_t" id="signman_nm_t" value="" maxlength="60" class="text_full" style="width:110px">
			  			<spring:message code="las.page.field.contractManager.rank2"/><input type="text" name="signman_jikgup_nm_t" id="signman_jikgup_nm_t" value="" maxlength="60" class="text_full" style="width:110px">
			  			<spring:message code="las.page.field.contractManager.dept2"/><input type="text" name="sign_dept_nm_t" id="sign_dept_nm_t" value="" maxlength="60" class="text_full" style="width:110px">
			  		</div>
				</td>			
			</tr>			


			<tr class="slide-target02 slide-area">
				<th><spring:message code="las.page.field.contractManager.keepOrgnYn"/></th>
				<td colspan="6">
					<select name="org_strg_pos" id="org_strg_pos" style="width:100px;">
						<option value="Y" <c:if test="${lomRq.org_strg_pos=='Y'}">selected</c:if> >Yes</option>
						<option value="N" <c:if test="${lomRq.org_strg_pos=='N'}">selected</c:if>>No</option>
					</select>
				</td>
			</tr>		
		</table>
		
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
				
				<tr class="slide-target02 slide-area">
					<th rowspan="3"><spring:message code="las.page.field.contractmanager.consideration_inner_d.attached_file" /></th>
					<td class="blueD"><span class="blueD"><spring:message code="las.page.field.contractmanager.consideration_inner_d.contract" /></span></td>
					<td colspan="5">
						<iframe src="<c:url value='/clm/blank.do' />" id="fileList1" name="fileList1" frameborder="0" width="100%" height="100px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
					</td>
				</tr>
		</table>
		<!--//middle table -->
				