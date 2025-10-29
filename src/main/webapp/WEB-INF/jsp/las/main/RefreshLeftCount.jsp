<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%@ page import="java.util.ArrayList" %>

<script language="javascript">

$(document).ready(function(){
	
	$('.section').click(function(){
		if($(this).next().css("display") == 'block'){
			$(this).next().css("display","none");
		}
		else{
		$(this).next().css("display","block");
		}
	});

});

</script>

<div class="box_table mt20">
	<div class='box_table_top'><span><spring:message code="las.page.field.main.todoList"/></span></div>
	<div class='box_table_mid'>
			<!-- 전자검토자의 검토유형 기본세팅 -->
            <c:set var="solo_yn" value=""/>
            <c:choose>
            <c:when test="${sessionScope['secfw.session.user_nm'] eq '검토자(국내)'}">
            <c:set var="solo_yn" value="1"/>
            </c:when>
            <c:when test="${sessionScope['secfw.session.user_nm'] eq '검토자(해외)'}">
            <c:set var="solo_yn" value="2"/>
            </c:when>
            </c:choose>
            <!--// 전자검토자의 검토유형 기본세팅 -->
		<!-- ① 계약 -->
		<div class='section'>
			<spring:message code="las.page.field.main.contract"/> <!-- <a href="javascript:Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/review/consideration.do?method=listConsideration&page_flag=1');"> --><span>[<c:out value="${cntTotal}"/>]</span>
		</div>
		
		<div class='section_sub' id='menu01' style='display:'>
			<div class='sub_top'></div>
			<div class='sub_mid'>
				<table>
				<c:choose>
					<c:when test="${topRole == 'RA01' || topRole == 'RB01'}">
						<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/review/consideration.do?method=listConsideration&page_flag=3&srch_law_status=C04224&srch_solo_yn=<c:out value="${solo_yn }"/>');">
							<th><a><spring:message code="las.page.field.main.unchosenLong"/></a></th>
							<td>: <a><c:out value="${cntUnassign}"/></a></td>
						</tr>
						<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/review/consideration.do?method=listConsideration&page_flag=3&srch_law_status=C04204&srch_closed_yn=N&srch_solo_yn=<c:out value="${solo_yn }"/>');">
							<th><a><spring:message code="las.page.field.main.inCheck"/></a></th>
							<td>: <a><c:out value="${cntConsider}"/></a></td>
						</tr>
						<!-- 2014-02-05 Kevin Added-->
						<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/review/consideration.do?method=listConsideration&page_flag=3&srch_closed_yn=Y&srch_solo_yn=<c:out value="${solo_yn }"/>');">
							<th><a><spring:message code="las.page.title.main.closed"/></a></th>
							<td>: <a><c:out value="${allClosed}"/></a></td>
						</tr>
						<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/review/consideration.do?method=listConsideration&page_flag=3&srch_law_status=C04225&srch_solo_yn=<c:out value="${solo_yn }"/>');">
							<th><a><spring:message code="las.page.field.main.tmpSave"/></a></th>
							<td>: <a><c:out value="${cntTempsave}"/></a></td>
						</tr>
						<!-- 
						<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/review/consideration.do?method=listConsideration&page_flag=3&srch_law_status=C04205&srch_solo_yn=<c:out value="${solo_yn }"/>');">
							<th><a><spring:message code="las.page.field.main.undecidedLong"/></a></th>
							<td>: <a><c:out value="${cntPendding}"/></a></td>
						</tr>
						 -->
						<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/review/consideration.do?method=listConsideration&page_flag=3&srch_law_status=C04299&srch_solo_yn=<c:out value="${solo_yn }"/>');">
							<th><a><spring:message code="las.page.field.main.reassign"/></a></th>
							<td>: <a><c:out value="${cntResign}"/></a></td>
						</tr>
					</c:when>
					<c:when test="${topRole == 'RA02'}">
						<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/review/consideration.do?method=listConsideration&page_flag=2&srch_law_status=C04204&srch_closed_yn=N');">
							<th><a><spring:message code="las.page.field.main.inCheck"/></a></th>
							<td>: <a><c:out value="${cntConsider}"/></a></td>
						</tr>
						<!-- 2014-02-05 Kevin Added-->
						<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/review/consideration.do?method=listConsideration&page_flag=2&srch_closed_yn=Y');">
							<th><a><spring:message code="las.page.title.main.closed"/></a></th>
							<td>: <a><c:out value="${allClosed}"/></a></td>
						</tr>
						<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/review/consideration.do?method=listConsideration&page_flag=2&srch_law_status=C04225');">
							<th><a><spring:message code="las.page.field.main.tmpSave"/></a></th>
							<td>: <a><c:out value="${cntTempsave}"/></a></td>
						</tr>
						<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/review/consideration.do?method=listConsideration&page_flag=2&srch_law_status=C04299');">
							<th><a><spring:message code="las.page.field.main.reassign"/></a></th>
							<td>: <a><c:out value="${cntResign}"/></a></td>
						</tr>
				</c:when>
				<c:otherwise>
						<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642301_0000000355', '/clm/manage/myContract.do?method=listMyContract&srch_step=C02501&closed_yn=N');">
							<th><a><spring:message code="las.page.title.main.review"/></a></th>
							<td>: <a><c:out value="${cntCont01}"/></a></td>
						</tr>
						<!-- 2014-01-31 Kevin Added-->
						<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642301_0000000355', '/clm/manage/myContract.do?method=listMyContract&srch_step=C02501&closed_yn=Y');">
							<th><a><spring:message code="las.page.title.main.closed"/></a></th>
							<td>: <a><c:out value="${closed}"/></a></td>
						</tr>
						<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642301_0000000355', '/clm/manage/myContract.do?method=listMyContract&srch_step=C02502');">
							<th><a><spring:message code="las.page.title.main.consultation"/></a></th>
							<td>: <a><c:out value="${cntCont02}"/></a></td>
						</tr>
						<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642301_0000000355', '/clm/manage/myContract.do?method=listMyContract&srch_step=C02503');">
							<th><a><spring:message code="las.page.title.main.conclusion"/></a></th>
							<td>: <a><c:out value="${cntCont03}"/></a></td>
						</tr>
						<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642301_0000000355', '/clm/manage/myContract.do?method=listMyContract&srch_step=C02504');">
							<th><a><spring:message code="las.page.title.main.execution"/></a></th>
							<td>: <a><c:out value="${cntCont04}"/></a></td>
						</tr>
						<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642301_0000000355', '/clm/manage/myContract.do?method=listMyContract&srch_step=C02505');">
							<th><a><spring:message code="las.page.title.main.completion"/></a></th>
							<td>: <a><c:out value="${cntCont05}"/></a></td>
						</tr>
				</c:otherwise>
			</c:choose>
				</table>
			</div>
			<div class='sub_btm'></div>
		</div>
		
		<!-- ② 자문 -->
		<div class='section'>
          <spring:message code="las.page.field.main.advice"/> <!-- <a href="javascript:Menu.detail3('frm', '_top', '20130319152827442_0000000337', '20130319155500478_0000000371', '/las/lawconsulting/lawConsult.do?method=listLawConsult&isForeign=H&srch_reception=Y');"> --><span>[<c:out value="${lawCntTotal}"/>]</span>
		</div>
		
		<div class='section_sub' id='menu02' style='display:'>
			<div class='sub_top'></div>
			<div class='sub_mid'>
				<table>
				<c:choose>
					<c:when test="${topRole == 'RA01' || topRole == 'RB01'}">
						<tr onclick="Menu.detail3('frm', '_top', '20130319152827442_0000000337', '20130319155500478_0000000371', '/las/lawconsulting/lawConsult.do?method=listLawConsult&isForeign=H&srch_reception=Y&srch_prgrs_status=V01&srch_solo_yn=<c:out value="${solo_yn }"/>');">
							<th><a><spring:message code="las.page.field.main.unchosenLong"/></a></th>
							<td>: <a><c:out value="${lawCntNoassign}"/></a></td>
						</tr>
						<tr onclick="Menu.detail3('frm', '_top', '20130319152827442_0000000337', '20130319155500478_0000000371', '/las/lawconsulting/lawConsult.do?method=listLawConsult&isForeign=H&srch_reception=Y&srch_prgrs_status=V02&srch_solo_yn=<c:out value="${solo_yn }"/>');">
							<th><a><spring:message code="las.page.field.main.inCheck"/></a></th>
							<td>: <a><c:out value="${lawCntReview}"/></a></td>
						</tr>
						<tr onclick="Menu.detail3('frm', '_top', '20130319152827442_0000000337', '20130319155500478_0000000371', '/las/lawconsulting/lawConsult.do?method=listLawConsult&isForeign=H&srch_reception=Y&srch_prgrs_status=S09&srch_solo_yn=<c:out value="${solo_yn }"/>');">
							<th><a><spring:message code="las.page.field.main.tmpSave"/></a></th>
							<td>: <a><c:out value="${lawCntTemp}"/></a></td>
						</tr>
						<!-- 
						<tr onclick="Menu.detail3('frm', '_top', '20130319152827442_0000000337', '20130319155500478_0000000371', '/las/lawconsulting/lawConsult.do?method=listLawConsult&isForeign=H&srch_reception=Y&srch_prgrs_status=S08&srch_solo_yn=<c:out value="${solo_yn }"/>');">
							<th><a><spring:message code="las.page.field.main.undecidedLong"/></a></th>
							<td>: <a><c:out value="${lawCntPending}"/></a></td>
						</tr>
						 -->
						<tr onclick="Menu.detail3('frm', '_top', '20130319152827442_0000000337', '20130319155500478_0000000371', '/las/lawconsulting/lawConsult.do?method=listLawConsult&isForeign=H&srch_reception=Y&srch_prgrs_status=V03&srch_solo_yn=<c:out value="${solo_yn }"/>');">
							<th><a><spring:message code="las.page.field.main.reassign"/></a></th>
							<td>: <a><c:out value="${lawCntResign}"/></a></td>
						</tr>
					</c:when>
					<c:when test="${topRole == 'RA02'}">
						<tr onclick="Menu.detail3('frm', '_top', '20130319152827442_0000000337', '20130319155500478_0000000371', '/las/lawconsulting/lawConsult.do?method=listLawConsult&isForeign=H&srch_reception=Y&srch_prgrs_status=V02');">
							<th><a><spring:message code="las.page.field.main.inCheck"/></a></th>
							<td>: <a><c:out value="${lawCntReview}"/></a></td>
						</tr>
						<tr onclick="Menu.detail3('frm', '_top', '20130319152827442_0000000337', '20130319155500478_0000000371', '/las/lawconsulting/lawConsult.do?method=listLawConsult&isForeign=H&srch_reception=Y&srch_prgrs_status=S09');">
							<th><a><spring:message code="las.page.field.main.tmpSave"/></a></th>
							<td>: <a><c:out value="${lawCntTemp}"/></a></td>
						</tr>
						<!-- 
						<tr onclick="Menu.detail3('frm', '_top', '20130319152827442_0000000337', '20130319155500478_0000000371', '/las/lawconsulting/lawConsult.do?method=listLawConsult&isForeign=H&srch_reception=Y&srch_prgrs_status=S08');">
							<th><a><spring:message code="las.page.field.main.undecidedLong"/></a></th>
							<td>: <a><c:out value="${lawCntPending}"/></a></td>
						</tr>
						 -->
						<tr onclick="Menu.detail3('frm', '_top', '20130319152827442_0000000337', '20130319155500478_0000000371', '/las/lawconsulting/lawConsult.do?method=listLawConsult&isForeign=H&srch_reception=Y&srch_prgrs_status=V03');">
							<th><a><spring:message code="las.page.field.main.reassign"/></a></th>
							<td>: <a><c:out value="${lawCntResign}"/></a></td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr onclick="Menu.detail3('frm', '_top', '20130319152827442_0000000337', '20130319155500478_0000000371', '/las/lawconsulting/lawConsult.do?method=listLawConsult&isForeign=H&srch_reception=Y&srch_prgrs_status=V02');">
							<th><a><spring:message code="las.page.field.main.inCheck"/></a></th>
							<td>: <a><c:out value="${lawCntReview}"/></a></td>
						</tr>
					</c:otherwise>
			</c:choose>
			</table>
			</div>
			<div class='sub_btm'></div>
		</div>
		
		<!-- ④ 계약관리 -->
		<c:if test="${isCntMng == 'Y'}">
		<div class='section'>
          <spring:message code="las.page.title.main.tab04"/> <!-- <a href="javascript:Menu.detail3('frm', '_top', '20130319152827442_0000000337', '20130321151916099_0000000427', '/las/lawconsulting/lawConsult.do?method=listStaLawConsult&isForeign=H&srch_reception=Y');"> --><span>[<c:out value="${cntContMngTotal}"/>]</span>
		</div>
		
		<div class='section_sub' id='menu04' style='display:none'>
			<div class='sub_top'></div>
			<div class='sub_mid'>
				<table>
					<tr onclick="Menu.detail3('frm', '_top', '20130319155330501_0000000362', '20130319155330501_0000000362', '/clm/manage/myApproval.do?method=listMyApproval&list_mode=cnsdreq&isOrgMgr=N');">
						<th><a><spring:message code="las.page.title.main.orgRcv"/></a></th>
						<td>: <a><c:out value="${cntContMngOrgRcv}"/></a></td>
					</tr>
					<tr onclick="Menu.detail3('frm', '_top', '20130319155330749_0000000363', '20130319155330749_0000000363', '/clm/manage/completion.do?method=listAutoRenewApproval');">
						<th><a><spring:message code="las.page.title.main.autoExp"/></a></th>
						<td>: <a><c:out value="${cntContMngAutoExp}"/></a></td>
					</tr>
					<tr onclick="Menu.detail3('frm', '_top', '20130319155330773_0000000364', '20130319155330773_0000000364', '/clm/manage/registration.do?method=listRegistrationApproval');">
						<th><a><spring:message code="las.page.title.main.aftconclReg"/></a></th>
						<td>: <a><c:out value="${cntContMngAfterConReg}"/></a></td>
					</tr>
				</table>
			</div>
			<div class='sub_btm'></div>
		</div>
		</c:if>
		
		<!-- ⑤ 날인 · 증명서류접수 -->
		<c:if test="${isCntSealorSign == 'Y'}">
		<div class='section'>
          <spring:message code="las.page.field.main.sealSign"/> <a href="javascript:Menu.detail3('frm', '_top', '20130319152827442_0000000337', '20130321151916099_0000000427', '/las/lawconsulting/lawConsult.do?method=listStaLawConsult&isForeign=H&srch_reception=Y');"><span>[<c:out value="${stdCntTotal}"/>]</span></a>
		</div>
		<div>
		
		</div>
		</c:if>
	</div>
	<div class='box_table_btm'></div>
</div>