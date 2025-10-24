<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@page import="com.sds.secframework.common.util.DateUtil"%>
<%-- 
/**
 * 파  일  명 : OrgMgr_d_his.jsp
 * 프로그램명 : 이력정보 조회 목록
 * 설      명 : 
 * 작  성  자 : 박정훈
 * 작  성  일 : 2013.04
 */
--%>
<%
	ListOrderedMap m_orgMngLom = (ListOrderedMap)request.getAttribute("orgMngLom"); // 추가 2013.10.12
	String str_mod_cause="";
	if (m_orgMngLom!=null) {
		str_mod_cause = StringUtil.convertEnterToBR((String)m_orgMngLom.get("mod_cause")); // BR처리문제로 추가
	}
%>

<script>
	$(document).ready(
		function() {
			if('<c:out value='${contractCommand.page_gbn}'/>'=='modify2'){
				
				//수정항목
				getCodeSelectByUpCd("frm", "org_mng_type", '/common/clmsCom.do?method=listComCdByGrpCd&combo_abbr=A&combo_grp_cd=OM&combo_type=S&combo_del_yn=N&combo_selected=<c:out value='${orgMngLom.crrncy_unit}'/>');
				$('#orgmgr_rent_manage').css("display","block");
				$('#orgmgr_modify_info').css("display","none");
				
				initCal("org_mng_dt"); 
			}
			else if('<c:out value='${contractCommand.page_gbn}'/>'=='modify'){
				$('#orgmgr_rent_manage').css("display","none");
				$('#orgmgr_modify_info').css("display","block");
			}
		});
</script>

<!-- 수정정보 -->
<div id="orgmgr_modify_info">
<div class="title_basic">
	 <h4 class="ntitle05"><spring:message code="clm.page.msg.manage.modifyInf" /></h4>
</div>
<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
	<colgroup>
		<col width="12%" />
		<col width="22%" />
		<col width="12%" />
		<col width="54%" />
	</colgroup>
	<tr>
		<th><spring:message code="clm.page.msg.manage.modifyDate" /></th>
		<td>
			<c:choose>
				<c:when test="${contractCommand.page_gbn=='modify'}">
					<%=DateUtil.formatDate(DateUtil.today(), "-")%>
				</c:when>
				<c:otherwise>
					<c:out value='${orgMngLom.mod_st}'/>
				</c:otherwise>
			</c:choose>
		</td>
		<th><spring:message code="clm.page.msg.manage.modifyPer" /></th>
		<td>
			<c:choose>
				<c:when test="${contractCommand.page_gbn=='modify'}">
					<%=session.getAttribute("secfw.session.user_nm")%>/<%=session.getAttribute("secfw.session.grade_nm")%>/<%=session.getAttribute("secfw.session.dept_nm")%>		
				</c:when>
				<c:otherwise>
					<c:out value='${orgMngLom.mod_nm}'/>/<c:out value='${orgMngLom.mod_jikgup_nm}'/>/<c:out value='${orgMngLom.mod_dept_nm}'/>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.modifyItmRsn" /></th>
		<td colspan="3">
			<c:choose>
				<c:when test="${contractCommand.page_gbn=='modify'}">
					<span id="mod_cause">0</span>/<spring:message code="clm.page.msg.manage.stringLen1000" /><br>
					<textarea name="mod_cause" id="mod_cause" rows="8" class="text_area all" onkeyup="frmChkLenLang(this,1000,'mod_cause','<%=langCd%>')" alt="<spring:message code="clm.page.msg.manage.modifyItmRsn" htmlEscape="true" />"  maxLength="1000"></textarea>
				</c:when>
				<c:otherwise>
					<!-- <c:out value='${orgMngLom.mod_cause}'/>  --> 
					<%=str_mod_cause %>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</table>
</div>
<!-- 수정정보 -->

<!-- 원본출납관리 -->
<div id="orgmgr_rent_manage">
<div class="title_basic">
	 <h4 class="ntitle05"><spring:message code="clm.page.msg.manage.orgInOut" /></h4>
</div>
<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
	<colgroup>
		<col width="12%" />
		<col width="22%" />
		<col width="12%" />
		<col width="54%" />
	</colgroup>
	<tr>
		<th><spring:message code="clm.page.msg.manage.item" /></th>
		<td>
			<c:choose>
				<c:when test="${contractCommand.page_gbn eq 'modify2'}">
					<select name="org_mng_type" id="org_mng_type"></select>
				</c:when>
				<c:otherwise>
					<c:out value='${orgMngLom.org_mng_type_nm}'/>
				</c:otherwise>
			</c:choose>
			
		</td>
		<th><spring:message code="clm.page.msg.manage.date" /></th>
		<td>
			<c:choose>
				<c:when test="${contractCommand.page_gbn=='modify2'}">
					<input type="text" name="org_mng_dt" id="org_mng_dt" value="<%=DateUtil.formatDate(DateUtil.today(), "-")%>" class="text_calendar02"/>
				</c:when>
				<c:otherwise>
					<c:out value='${orgMngLom.org_mng_dt}'/>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.reqPer" /></th>
		<td>
			<c:choose>
				<c:when test="${contractCommand.page_gbn=='modify2'}">
					<input type="hidden" name="org_mng_reqman_id" id="org_mng_reqman_id" /> 
					<input type="hidden" name="org_mng_reqman_nm" id="org_mng_reqman_nm" />
					<input type="hidden" name="org_mng_req_dept_nm" id="org_mng_req_dept_nm"/>
					<input type="hidden" name="org_mng_req_jikgup_nm" id="org_mng_req_jikgup_nm"/>
					<input type="text" name="org_mng_reqman_search_nm" id="org_mng_reqman_search_nm" style="width:110px" class="text_search" alt="<spring:message code='clm.page.field.contract.consultation.detail.sealffmtmannm'/>" onkeydown="if(event.keyCode==13){event.returnValue = false;javascript:searchEmployee('orgmng');}"/><img id="seal_ffmtman_search_img" src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchEmployee('orgmng');" class="cp" alt="search" />
					<span id="orgmng"></span>
				</c:when>
				<c:otherwise>
					<c:out value='${orgMngLom.org_mng_reqman_nm}'/>/<c:out value='${orgMngLom.org_mng_req_jikgup_nm}'/>/<c:out value='${orgMngLom.org_mng_req_dept_nm}'/>
				</c:otherwise>
			</c:choose>
		</td>
		<th><spring:message code="clm.page.msg.manage.chrgPerson" /></th>			
		<td>
			<c:choose>
				<c:when test="${contractCommand.page_gbn=='modify2'}">
					<%=session.getAttribute("secfw.session.user_nm")%>/<%=session.getAttribute("secfw.session.grade_nm")%>/<%=session.getAttribute("secfw.session.dept_nm")%>		
				</c:when>
				<c:otherwise>
					<c:out value='${orgMngLom.mod_nm}'/>/<c:out value='${orgMngLom.mod_jikgup_nm}'/>/<c:out value='${orgMngLom.mod_dept_nm}'/>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</table>
</div>
<!-- //원본출납관리 -->

<div class="title_basic">
  <h4 class="ntitle05"><spring:message code="clm.page.msg.manage.manageHistory" /></h4> 
</div>
<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
	<colgroup>
		<col width="5%"/>
		<col width="20%"/>
		<col width="15%" />
		<col width="30%" />
		<col width="30%" />
	</colgroup>
	<thead>
	   <tr>
			<th>No</th>
			<th><spring:message code="clm.page.msg.manage.date" /></th>
		   	<th><spring:message code="clm.page.msg.manage.item" /></th>
		   	<th><spring:message code="clm.page.msg.manage.reqPer" /></th>
		   	<th><spring:message code="las.page.field.contractManager.groupChief" /></th>
	   </tr>
	</thead>
	<tbody>
	<c:set var="test" value="1"/>
	<c:forEach var="list" items="${orgMngHisList}" varStatus="cnt">
		<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
			<td align="center"><c:out value='${list.seqno}'/></td>
			<td align="center">
			<c:if test="${orgMngLom.seqno == list.seqno}"><b></c:if>
		    <a href="javascript:goHistory('<c:out value='${list.seqno}'/>','<c:out value='${list.max_seqno}'/>');window.scrollTo(0,0);"><c:out value='${list.mod_dt}'/></a>
			</td>
			
			<c:choose>
				<c:when test="${list.org_mng_type_nm == null}">
					<td><spring:message code="clm.page.msg.manage.infoModify" /></td>
				</c:when>
				<c:otherwise>
					<td><c:out value='${list.org_mng_type_nm}'/></td>				
				</c:otherwise>
			</c:choose>
			
			<c:choose>
				<c:when test="${list.org_mng_reqman_nm == ''}">
					<td></td>
				</c:when>
				<c:otherwise>
					<td><c:out value='${list.org_mng_reqman_nm}'/>/<c:out value='${list.org_mng_req_jikgup_nm}'/>/<c:out value='${list.org_mng_req_dept_nm}'/></td>				
				</c:otherwise>
			</c:choose>
			<td><c:out value='${list.mod_nm}'/>/<c:out value='${list.mod_jikgup_nm}'/>/<c:out value='${list.mod_dept_nm}'/></td>
		</tr>
	</c:forEach>
	</tbody>
</table>
