<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%--
/**
 * 파  일  명 : ManageRequest_u.jsp
 * 프로그램명 : 계약서담당자변경요청
 * 설      명 : 
 * 작  성  자 : dawn
 * 작  성  일 : 2011.10.11
 */
 --%>
<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");

%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<LINK href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet">

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>

<script language="javascript">
	
	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/manage/request.do' />";
		
		if(flag == "request"){//요청
			if(confirm("<spring:message code='secfw.msg.ask.request' />")){
				if(frm.trgtman_id.value == "" || frm.trgtman_nm.value == ""){
					alert("<spring:message code='clm.page.alert.manageRequest.trgtmanNmChk'/>");
					frm.trgtman_nm.focus();
					return;
				}
				
				frm.method.value = "insertRequest";
				viewHiddenProgress(true);
				frm.submit();
			}
		}else if(flag == "cancel"){//취소
			if(confirm("<spring:message code='secfw.msg.ask.cancel' />")){
				
				frm.method.value = "deleteRequest";
				viewHiddenProgress(true);
				frm.submit();
			}
		}else if(flag == "approval"){//승인
			if(confirm("<spring:message code='secfw.msg.ask.approve' />")){
				
				frm.method.value = "modifyRequest";
				viewHiddenProgress(true);
				frm.submit();
			}
		}else if(flag == "list"){//목록 버튼 클릭 시
			
			frm.method.value = "listRequest";
			viewHiddenProgress(true);
		    frm.submit();
		}
	}
	
	/**
	* 임직원조회
	*/
	function searchEmployee(){
		var frm = document.frm;
		
		var srchValue = comTrim(frm.trgtman_nm.value); //입력받은 값
		frm.target = "PopUpWindow";
	    frm.action = "<c:url value='/secfw/esbOrg.do' />";
	    frm.method.value = "listEsbEmployee";
	    frm.srch_user_cntnt_type.value = "userName"; //이름으로 검색  - 보통 이름으로 검색함
	    frm.srch_user_cntnt.value = srchValue;
	    
	    if (srchValue == "" || getByteLength(srchValue) < 4) { //최소 2자리 이상 입력받게 한다.
	        alert('<spring:message code='secfw.msg.error.nameMinByte' />');
	        frm.trgtman_nm.focus();
	        return;
	    }
	    
    	PopUpWindowOpen('', 800, 450, true);
	    frm.submit();
	}
	
	/**
	* 임직원 조회정보 결과 setting
	*/
	function setUserInfos(obj){
		var frm = document.frm;
		
    	frm.trgtman_id.value        = obj.epid;//변경담당자ID
    	frm.trgtman_nm.value        = obj.cn;//변경담당자명
    	frm.trgtman_dept_nm.value   = obj.department;//부서명 
    	frm.trgtman_jikgup_nm.value = obj.title;//직급
	}
	
</script>

</head>
<body>
<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id="frm" method="post" autocomplete="off">
<!-- search form -->
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>">

<input type="hidden" name="P_demnd_gbn" value="<c:out value='${command.p_demnd_gbn}'/>"/>
<input type="hidden" name="p_prcs_depth" value="<c:out value='${command.p_prcs_depth}'/>" />
<!-- key form-->
<input type="hidden" name="srch_user_cntnt_type" value="" />
<input type="hidden" name="srch_user_cntnt"      value="" />
<input type="hidden" name="cnsdreq_id" value="<c:out value='${command.cnsdreq_id}'/>" />
<input type="hidden" name="cntrt_id" value="<c:out value='${command.cntrt_id}'/>" />

<!-- 첨부파일정보 -->

<!-- // **************************** Form Setting **************************** -->
<div id="wrap">
	<!-- container -->
	<div id="container">
	
	    <!-- Location -->
		<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->
	
		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.title.manageRequest.modifyTitle" /></h3>
		</div>
		<!-- //title -->
	
		<!-- content -->
		<div id="content">
		    <!-- view -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
					<col width="15%" />
					<col />
					<col width="10%" />
					<col width="20%"/>
					<col width="10%" />
					<col width="20%"/>
				</colgroup>
				<tbody>
						<tr>
							<th>
							    <c:choose>
				 	    			<c:when test="${command.p_prcs_depth == 'C02501' || command.p_prcs_depth == 'C02502'}">
				 	    			    <spring:message code="clm.page.field.manageRequest.reqTitle" />
				 	    			</c:when>
				 	    			<c:otherwise>
				 	    			    <spring:message code="clm.page.field.manageRequest.cntrtNm" />
				 	    			</c:otherwise>
			 	    			</c:choose>
							</th>
							<td colspan="3"><c:out value="${authReqInfo.demnd_title_nm}"/></td>
							<th>
								<c:choose>
				 	    			<c:when test="${command.p_prcs_depth == 'C02501' || command.p_prcs_depth == 'C02502'}">
				 	    			    <spring:message code="clm.page.field.manageRequest.reqDt" />
				 	    			</c:when>
				 	    			<c:otherwise>
				 	    			    <spring:message code="clm.page.field.manageRequest.regDt" />
				 	    			</c:otherwise>
			 	    			</c:choose>
							</th>
							<td><c:out value="${authReqInfo.req_dt}"/></td>							
						</tr>
						<tr>
							<th><spring:message code="clm.page.field.manageRequest.demndmanNm" /></th>
							<td>
								<c:out value="${authReqInfo.demndman_nm}"/>
								<input type="hidden" id="demndman_id" name="demndman_id" value="<c:out value="${authReqInfo.demndman_id}"/>" />
								<input type="hidden" id="demndman_nm" name="demndman_nm" value="<c:out value="${authReqInfo.demndman_nm}"/>" />
							</td>
							<th><spring:message code="clm.page.field.manageRequest.jikgupNm" /></th>
							<td>
								<c:out value="${authReqInfo.demndman_jikgup_nm}"/>
								<input type="hidden" id="demndman_jikgup_nm" name="demndman_jikgup_nm" value="<c:out value="${authReqInfo.demndman_jikgup_nm}"/>" />
							</td>
							<th><spring:message code="clm.page.field.manageRequest.deptNm" /></th>
							<td>
								<c:out value="${authReqInfo.demndman_dept_nm}"/>
								<input type="hidden" id="demndman_dept_nm" name="demndman_dept_nm" value="<c:out value="${authReqInfo.demndman_dept_nm}"/>" />
							</td>
						</tr>
						<tr>
							<th title="<spring:message code="clm.page.msg.manage.announce068" htmlEscape="true" />"><spring:message code="clm.page.field.manageRequest.ChTrgtmanNm" /></th>
							<td>
							    <c:choose>
				 	    			<c:when test="${command.p_demnd_gbn eq 'D'}">
				 	    			    <input type="text" name="trgtman_nm" id="trgtman_nm" value="" style="width:80px" class="text_search"/><a href="javascript:searchEmployee();"><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search" /></a>
									    <input type="hidden" name="trgtman_id" id="trgtman_id" value="" />
				 	    			</c:when>
				 	    			<c:otherwise>
				 	    				<c:out value='${authReqInfo.trgtman_nm}'/>
				 	    			    <input type="hidden" name="trgtman_nm" id="trgtman_nm" value="<c:out value='${authReqInfo.trgtman_nm}'/>"/>
									    <input type="hidden" name="trgtman_id" id="trgtman_id" value="<c:out value='${authReqInfo.trgtman_id}'/>" />
				 	    			</c:otherwise>
			 	    			</c:choose>
							</td>
							<th><spring:message code="clm.page.field.manageRequest.jikgupNm" /></th>
							<td>
							    <c:choose>
				 	    			<c:when test="${command.p_demnd_gbn eq 'D'}">
										<input type="text" name="trgtman_jikgup_nm" id="trgtman_jikgup_nm" value="" style="border:0" READONLY="READONLY" onfocus="blur()" />
									</c:when>
				 	    			<c:otherwise>
				 	    				<c:out value='${authReqInfo.trgtman_jikgup_nm}'/>
										<input type="hidden" name="trgtman_jikgup_nm" id="trgtman_jikgup_nm" value="<c:out value='${authReqInfo.trgtman_jikgup_nm}'/>" />
									</c:otherwise>
			 	    			</c:choose>
							</td>
							<th><spring:message code="clm.page.field.manageRequest.deptNm" /></th>
							<td>
							    <c:choose>
				 	    			<c:when test="${command.p_demnd_gbn eq 'D'}">
										<input type="text" name="trgtman_dept_nm" id="trgtman_dept_nm" value="" style="border:0" READONLY="READONLY" onfocus="blur()" />
									</c:when>										
				 	    			<c:otherwise>
				 	    				<c:out value='${authReqInfo.trgtman_dept_nm}'/>
										<input type="hidden" name="trgtman_dept_nm" id="trgtman_dept_nm" value="<c:out value='${authReqInfo.trgtman_dept_nm}'/>"/>
									</c:otherwise>
			 	    			</c:choose>
							</td>
						</tr>
						<tr>
							<th><spring:message code="clm.page.msg.manage.reqApprYn" /></th>
							<td>
							    <c:choose>
				 	    			<c:when test="${command.p_demnd_gbn eq 'D' || command.p_demnd_gbn eq 'E' }">
										<c:out value='${authReqInfo.demnd_status_nm}'/>
										<input type="hidden" name="demnd_status" id="demnd_status" value="<c:out value='${authReqInfo.demnd_status}'/>"/>
				 	    			</c:when>
				 	    			<c:otherwise>
									    <select name="demnd_status" id="demnd_status" >
											<option value="C03702" selected><spring:message code="clm.page.field.manageRequest.demndStatus2"/></option>
											<option value="C03703"><spring:message code="clm.page.field.manageRequest.demndStatus3"/></option>
										</select>
									</c:otherwise>
			 	    			</c:choose>
							</td>
							<th><spring:message code="clm.page.field.manageRequest.demndDt" /></th>
							<td colspan="3"><c:out value='${authReqInfo.demnd_dt2}'/></td>
						</tr>
						<c:if test="${command.p_demnd_gbn eq 'D'}"><!-- 승인신청 -->
						<tr class="end">
						    <th><spring:message code="clm.page.field.manageRequest.demndCont" /></th>
							<td colspan="5"><input type="text" id="demnd_cont" name="demnd_cont" value="" maxlength="1000" class="text_full" style="width:90%" /></td>
						</tr>
						</c:if>
						<c:if test="${command.p_demnd_gbn eq 'E'}"><!-- 신청 -->
						<tr class="end">
						    <th><spring:message code="clm.page.field.manageRequest.demndCont" /></th>
							<td colspan="5">
								<c:out value="${authReqInfo.demnd_cont}"/>
								<input type="hidden" id="demnd_cont" name="demnd_cont" value="<c:out value='${authReqInfo.demnd_cont}'/>"/>
							</td>
						</tr>
						</c:if>
						<c:if test="${command.p_demnd_gbn eq 'T'}"><!-- 승인 -->
						<tr>
						    <th><spring:message code="clm.page.field.manageRequest.demndCont" /></th>
							<td colspan="5">
								<c:out value="${authReqInfo.demnd_cont}"/>
								<input type="hidden" id="demnd_cont" name="demnd_cont" value="<c:out value='${authReqInfo.demnd_cont}'/>"/>
							</td>
						</tr>
						<tr class="end">
						    <th><spring:message code="clm.page.field.manageRequest.hndlCont" /></th>
							<td colspan="5"><input type="text" id="hndl_cont" name="hndl_cont" value="" maxlength="1000" class="text_full" style="width:90%" /></td>
						</tr>
						</c:if>
				</tbody>
			</table>
			<!-- //view -->
			
			<div class="btn_wrap">
			    <c:if test="${command.p_demnd_gbn eq 'D'}"><!-- 요청 -->
			        <span class="btn_all_b"><span class="save"></span><a href="javascript:pageAction('request');"><spring:message code="secfw.page.button.request" /></a></span>
			    </c:if>
				<c:if test="${command.p_demnd_gbn eq 'E'}"><!-- 취소 -->
					<span class="btn_all_b"><span class="delete"></span><a href="javascript:pageAction('cancel');"><spring:message code="secfw.page.button.cancel" /></a></span>
				</c:if>
				<c:if test="${command.p_demnd_gbn eq 'T'}"><!-- 승인 -->
					<span class="btn_all_b"><span class="save"></span><a href="javascript:pageAction('approval');"><spring:message code="secfw.page.button.accept" /></a></span>
				</c:if>
				<span class="btn_all_w"><span class="list"></span><a href="javascript:pageAction('list');"><spring:message code="secfw.page.button.list" /></a></span>
			</div>
		</div>
		<!-- //content -->
		<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- // footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
</form:form>
</body>
</html>
