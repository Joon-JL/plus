<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%-- 
/**
 * 파  일  명 : Completion_cmplt_p.jsp
 * 프로그램명 : 종료관리 팝업 
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%
	ArrayList cntrtStatusLom	= (ArrayList)request.getAttribute("cntrtStatusLom");
	ListOrderedMap tmpLom	= (ListOrderedMap)request.getAttribute("completionLom");
	
	//담당자 여부 : RD01 인 경우 확인자, respman_id 동일
	String respman_YN = "N";
	
	List tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.role") ;
	
	for(int i=0; i<tmpSessionRoleList.size(); i++) {
		Map tmpSessionRoleMap = (Map)tmpSessionRoleList.get(i) ;

		if(tmpSessionRoleMap.containsValue("RD01")){
			respman_YN = "Y";			
		}
	}
%>
<!-- key hidden Form -->
<!-- 상신을 위한 hidden 정보 -->
<input type="hidden" name="srch_user_cntnt_type" value=""/>
<input type="hidden" name="srch_user_cntnt" value=""/>
<!-- 임직원조회 팝업 시 리턴값 사전품의발의자인지 사전품의승인자정보 인지 구분하기 위해 -->
<input type="hidden" name="searchEmployeeGb" id="searchEmployeeGb" value="" />
<input type="hidden" name="conListGu" value="Z1000" />
<!-- //상신을 위한 hidden 정보 -->
<!-- //key hidden Form -->
<div class="title_basic">
	<h5 class="ntitle05"><spring:message code="clm.page.msg.manage.endInfo" /> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'contract-completion-content');"/></h5>
</div>
<!-- 의뢰인일 경우 -->
<c:choose>
	<c:when test="${compeltionCommand.session_user_id==completionLom.reqman_id && ( completionLom.depth_status=='C02662' || completionLom.depth_status=='C02681' ) }">
<script language="javascript">
//의뢰자검색
function searchEmployee(obj) {
	var frm 		= document.frm;
	var srchValue 	= "";
	srchValue = comTrim(obj.value);
    frm.target = "PopUpWindow";
    frm.srch_user_cntnt_type.value = "userName"; //이름으로 검색  - 보통 이름으로 검색함
    frm.action = "<c:url value='/secfw/esbOrg.do' />";
    frm.method.value = "listEsbEmployee";
    frm.srch_user_cntnt.value = srchValue;
    if (srchValue == "" || srchValue.length < 4) { //최소 2자리 이상 입력받게 한다.
        alert('<spring:message code='secfw.msg.error.nameMinByte' />');
        obj.focus();
    } else {
    	PopUpWindowOpen('', 800, 450, true, 'PopUpWindow');	//completion_l 에 정의된 메서드
    	frm.submit();
    }    	
} 


/**
 * 임직원 조회정보 결과 setting
 */
function setUserInfos(obj) {
     var userInfo = obj; 
     var frm = document.frm;
     //alert("setUserInfos   >>>> " + obj);
     
    //사전품의발의자
     frm.approvalman_id.value = userInfo.epid;
	 frm.approvalman_nm.value = userInfo.cn;
	 frm.approvalman_search_nm.value = userInfo.cn;
	 frm.approvalman_dept_nm.value = userInfo.department;
	 frm.approvalman_jikgup_nm.value=userInfo.title;
	 $('#approvalman_jikgup_nm').html(userInfo.title);
	 $('#approvalman_dept_nm').html(userInfo.department);
}
</script>

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
			<td><c:out value='${compeltionCommand.session_user_nm}' />
			<input type="hidden" id="cntrt_chge_demndman_id" name="cntrt_chge_demndman_id" value="<c:out value='${compeltionCommand.session_user_id}' />" />
			<input type="hidden" id="cntrt_chge_demndman_nm" name="cntrt_chge_demndman_nm" value="<c:out value='${compeltionCommand.session_user_nm}' />" />
			</td>
			<th ><spring:message code="clm.page.msg.manage.level" /></th>
			<td><c:out value='${compeltionCommand.session_jikgup_nm}' />
			<input type="hidden" id="cntrt_chge_demndman_jikgup_nm" name="cntrt_chge_demndman_jikgup_nm" value="<c:out value='${compeltionCommand.session_jikgup_nm}' />" />
			</td>
			<th ><spring:message code="clm.page.msg.manage.deptName" /></th>
			<td><c:out value='${compeltionCommand.session_dept_nm}' />
			<input type="hidden" id="cntrt_chge_demnd_dept_nm" name="cntrt_chge_demnd_dept_nm" value="<c:out value='${compeltionCommand.session_dept_nm}' />" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.manage.apprPer" /></th>
			<td>
				<input type="hidden" name="approvalman_id" id="approvalman_id" value="<c:out value='${approveManLom.user_id }' />" />
				<input type="hidden" name="approvalman_nm" id="approvalman_nm" value="<c:out value='${approveManLom.user_name }' />" />
				<input type="text"   name="approvalman_search_nm" id="approvalman_search_nm" value="" style="width:92px" class="text_search" onkeypress="if(event.keyCode==13) {searchEmployee(document.frm.approvalman_search_nm);return false;}" /><a href="javascript:searchEmployee(document.frm.approvalman_search_nm);"><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="<spring:message code="clm.page.msg.manage.apprPer" htmlEscape="true" />" /></a>					
			</td>
			<th><spring:message code="clm.page.msg.manage.level" /></th>
			<td>
				<input type="hidden" name="approvalman_jikgup_nm" value="<c:out value='${approveManLom.duty }' />" />
				<span id="approvalman_jikgup_nm"></span>
			</td>
			<th><spring:message code="clm.page.msg.manage.deptName" /></th>
			<td>
				<input type="hidden" name="approvalman_dept_nm" value="<c:out value='${approveManLom.group_name }' />" />
				<span id="approvalman_dept_nm"></span>
			</td>
		</tr>
<%
//요청일 : 존재 유무 체크
if("Y".equals(tmpLom.get("cntrt_chge_demndday_exist"))){
%>	
		<tr>
			<th><spring:message code="clm.page.msg.manage.reqDt" /></th>
			<td colspan="5"><c:out value='${completionLom.cntrt_chge_demndday}' /><input type="hidden" id="cntrt_chge_demndday" name="cntrt_chge_demndday" value="<c:out value='${completionLom.cntrt_chge_demndday}' />"></input></td>
		</tr>
<%
}else{
%>
<input type="hidden" id="cntrt_chge_demndday" name="cntrt_chge_demndday" value="<c:out value='${completionLom.cntrt_chge_demndday}' />" />
<%
}
%>
		<tr>
			<th ><spring:message code="clm.page.msg.manage.statChg" /></th>
			<td colspan="5">
				<input type="radio" name="cntrt_status" id="cntrt_status" size="50" value="C02404" <c:if test="${completionLom.cntrt_status=='C02404'}">checked</c:if> title="<spring:message code="clm.page.msg.manage.announce044" htmlEscape="true" />" /><strong style="padding: 0px 30px 0px 5px"><spring:message code="clm.page.msg.manage.endOfDate" /></strong>
				<!-- <input type="radio" name="cntrt_status" id="cntrt_status" size="50" value="C02403" <c:if test="${completionLom.cntrt_status=='C02403' || completionLom.cntrt_status=='C02402'}">checked</c:if>/><strong style="padding: 0px 30px 0px 5px">변경</strong> -->
				<input type="radio" name="cntrt_status" id="cntrt_status" size="50" value="C02405" <c:if test="${completionLom.cntrt_status=='C02405'}">checked</c:if> title="<spring:message code="clm.page.msg.manage.announce040" htmlEscape="true" />"/><strong style="padding: 0px 30px 0px 5px"><spring:message code="clm.page.msg.manage.calncel" /></strong>
			</td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.manage.rsnForStatChg" /></th>
			<td colspan="5"><textarea name="cntrt_chge_demnd_cause" id="cntrt_chge_demnd_cause" cols="10" rows="3" class="text_area_full" alt="<spring:message code="clm.page.msg.manage.chgStatRsn" htmlEscape="true" />" maxLength="330"><c:out value='${completionLom.cntrt_chge_demnd_cause}' /></textarea></td>
			<input type="hidden" id="cntrt_chge_plndday" name="cntrt_chge_plndday" value="" />
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.manage.fileAttach" /></th>
			<td colspan="5">
				<iframe src="<c:url value='/clm/blank.do' />" id="fileList10" name="fileList10" frameborder="0" width="100%" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
			</td>
		</tr>
	</table>
	</c:when>

	<c:when test="${completionRoleLom.cfrmYN==true && completionLom.depth_status=='C02685' && command.isMyApproval!='Y'}">
	<!-- //확인자일 경우-->
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
			<td colspan="2">
			<c:out value='${completionLom.cntrt_chge_demndman_nm}' />
				<c:choose><c:when test="${!empty completionLom.cntrt_chge_demndman_jikgup_nm}">/</c:when></c:choose>
			<c:out value='${completionLom.cntrt_chge_demndman_jikgup_nm}' />
				<c:choose><c:when test="${!empty completionLom.cntrt_chge_demnd_dept_nm}">/</c:when></c:choose>
			<c:out value='${completionLom.cntrt_chge_demnd_dept_nm}' /></td>
			<th ><spring:message code="clm.page.msg.manage.apprPer" /></th>
			<td colspan="2">
				<c:out value='${approveManLom.user_name }' />
					<c:choose><c:when test="${!empty approveManLom.dept_name}">/</c:when></c:choose>
				<c:out value='${approveManLom.dept_name }' />
					<c:choose><c:when test="${!empty approveManLom.duty}">/</c:when></c:choose>
				<c:out value='${approveManLom.duty }' />
			<input type="hidden" name="approvalman_nm" id="approvalman_nm" value="<c:out value='${approveManLom.user_name }' />" />
			<input type="hidden" name="approvalman_id" id="approvalman_id" value="<c:out value='${approveManLom.user_id }' />" />
			<input type="hidden" name="approvalman_jikgup_nm" value="<c:out value='${approveManLom.duty }' />" />
			<input type="hidden" name="approvalman_dept_nm" value="<c:out value='${approveManLom.dept_name }' />" />
			</td>
			
		</tr>
<%
//요청일 : 존재 유무 체크
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
			<td colspan="5"><c:out value='${completionLom.cntrt_status_nm}' />
			<input type="hidden" name="cntrt_status" id="cntrt_status" value="<c:out value='${completionLom.cntrt_status}' />" /></td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.manage.chgStatRsn" /></th>
			<td colspan="5"><c:out value='${completionLom.cntrt_chge_demnd_cause}' /></td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.common.attachment" /></th>
			<td colspan="5">
				<iframe src="<c:url value='/clm/blank.do' />" id="fileList10" name="fileList10" frameborder="0" width="100%" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
			</td>
		</tr>
	</table>
	<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
		<colgroup>
			<col width="14%" />
			<col width="22%" />
			<col width="14%" />
			<col width="18%" />
			<col width="14%" />
			<col width="18%" />
		</colgroup>
		<tr>
			<th><spring:message code="clm.page.msg.manage.confirmPer" /></th>  
			<td><c:out value='${compeltionCommand.session_user_nm}'/>
			<input type="hidden" name="cntrt_chge_confman_id" value="<c:out value='${compeltionCommand.session_user_id}'/>" />
			<input type="hidden" name="cntrt_chge_confman_nm" value="<c:out value='${compeltionCommand.session_user_nm}'/>" /></td>
			<th><spring:message code="clm.page.msg.manage.level" /></th>
			<td><c:out value='${compeltionCommand.session_jikgup_nm}'/>
			<input type="hidden" name=cntrt_chge_confman_jikgup_nm value="<c:out value='${compeltionCommand.session_jikgup_nm}'/>" /></td>
			<th><spring:message code="clm.page.msg.manage.deptName" /></th>
			<td><c:out value='${compeltionCommand.session_dept_nm}'/>
			<input type="hidden" name="cntrt_chge_conf_dept_nm" value="<c:out value='${compeltionCommand.session_dept_nm}'/>" /></td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.manage.confirmOpin" /></th>
			<td>
				<select name="cntrt_chge_conf_yn" id="cntrt_chge_conf_yn" style="width:100px;">
				<option value="Y" <c:if test="${completionLom.cntrt_chge_conf_yn=='Y'}">selected</c:if> ><spring:message code="clm.page.msg.common.confirm" /></option>
				<option value="N" <c:if test="${completionLom.cntrt_chge_conf_yn=='N'}">selected</c:if>><spring:message code="clm.page.msg.manage.noConfirm" /></option>
				</select>
			</td>
			<th><spring:message code="clm.page.msg.common.confirm" /></th>
			<td colspan="3"><input type="text" name="cntrt_chge_conf_cont" id="cntrt_chge_conf_cont" value="<c:out value='${completionLom.cntrt_chge_conf_cont}'/>" style="width:400px;" alt="<spring:message code="clm.page.msg.manage.reason" htmlEscape="true" />" maxlength="160" /></td>
		</tr>
	</table>
	</c:when>	
	<c:otherwise>
	<!-- //그외 경우 -->
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
			<td colspan="2">
				<c:out value='${completionLom.cntrt_chge_demndman_nm}' />
					<c:choose><c:when test="${!empty completionLom.cntrt_chge_demndman_jikgup_nm}">/</c:when></c:choose>
				<c:out value='${completionLom.cntrt_chge_demndman_jikgup_nm}' />
					<c:choose><c:when test="${!empty completionLom.cntrt_chge_demnd_dept_nm}">/</c:when></c:choose>
				<c:out value='${completionLom.cntrt_chge_demnd_dept_nm}' /></td>
			<th ><spring:message code="clm.page.msg.manage.apprPer" /></th>
			<td colspan="2">
				<c:choose>
					<c:when test="${!empty approveManLom.user_name}">
						<c:out value='${approveManLom.user_name }' />
							<c:choose><c:when test="${!empty approveManLom.dept_name}">/</c:when></c:choose>
						<c:out value='${approveManLom.dept_name }' />
							<c:choose><c:when test="${!empty approveManLom.duty}">/</c:when></c:choose>
						<c:out value='${approveManLom.duty }' />
					</c:when>
					<c:otherwise>
						<c:out value='${compeltionCommand.approvalman_nm }' />
							<c:choose><c:when test="${!empty compeltionCommand.approvalman_dept_nm}">/</c:when></c:choose>
						<c:out value='${compeltionCommand.approvalman_dept_nm }' />
							<c:choose><c:when test="${!empty compeltionCommand.approvalman_jikgup_nm}">/</c:when></c:choose>
						<c:out value='${compeltionCommand.approvalman_jikgup_nm }' />
					</c:otherwise>
				</c:choose>
			<input type="hidden" name="approvalman_id" id="approvalman_id" value="<c:out value='${compeltionCommand.approvalman_id }' />" />
			<input type="hidden" name="approvalman_nm" id="approvalman_nm" value="<c:out value='${compeltionCommand.approvalman_nm }' />" />
			<input type="hidden" name="approvalman_jikgup_nm" value="<c:out value='${compeltionCommand.approvalman_jikgup_nm }' />" />
			<input type="hidden" name="approvalman_dept_nm" value="<c:out value='${compeltionCommand.approvalman_dept_nm }' />" />
			</td>
		</tr>
<%
//요청일 : 존재 유무 체크
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
			<th><spring:message code="clm.page.msg.manage.rsnForStatChg" /></th>
			<td colspan="5"><c:out value='${completionLom.cntrt_chge_demnd_cause}' /></td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.common.attachment" /></th>
			<td colspan="5">
				<iframe src="<c:url value='/clm/blank.do' />" id="fileList10" name="fileList10" frameborder="0" width="100%" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
			</td>
		</tr>
	<%-- </table>
	<div class="title_basic">
	  <h5 class="ntitle05"><spring:message code="clm.page.msg.manage.contStatChgConf" /> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'contract-cfrmView');"/></h5>
	</div>
		<table cellspacing="0" cellpadding="0" border="0" class="table-style01" id="contract-cfrmView">
		<colgroup>
			<col width="14%" />
			<col width="22%" />
			<col width="14%" />
			<col width="18%" />
			<col width="14%" />
			<col width="18%" />
		</colgroup> --%>
	<c:if test="${! empty completionLom.cntrt_chge_confman_nm}">
		<tr>
			<th><spring:message code="clm.page.msg.manage.confirmPer" /></th>  
			<td colspan="2"><c:out value='${completionLom.cntrt_chge_confman_nm}'/>/<c:out value='${completionLom.cntrt_chge_confman_jikgup_nm}'/>/<c:out value='${completionLom.cntrt_chge_conf_dept_nm}'/></td>
			<th><spring:message code="clm.page.msg.manage.confirmYn" /></th>
			<td colspan="2">
				<c:choose>
					<c:when test="${completionLom.cntrt_chge_conf_yn=='Y'}"><spring:message code="clm.page.msg.common.confirm" /></c:when>
					<c:otherwise><spring:message code="clm.page.msg.manage.noConfirm" /></c:otherwise>					
				</c:choose>
			</td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.manage.confirmOpin" /></th>
			<td colspan="5"><c:out value='${completionLom.cntrt_chge_conf_cont}'/></td>
		</tr>
	</c:if>
	</table>
	</c:otherwise>
</c:choose>
