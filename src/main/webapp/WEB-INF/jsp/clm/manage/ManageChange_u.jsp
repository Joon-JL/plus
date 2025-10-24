<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%--
/**
 * 파 일 명   : ManageChange_u.jsp
 * 프로그램명 : 계약의뢰자/담당자변경 상세화면
 * 설      명 : 
 * 작  성  자 : 박정훈
 * 작  성  일 : 2013.04
 */
 --%>
<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
    ListOrderedMap  authReqInfo = (ListOrderedMap)request.getAttribute("authReqInfo");
    String reqOperdiv = (String)authReqInfo.get("req_operdiv");
   
  

%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>


<%@page import="org.apache.commons.collections.map.ListOrderedMap"%>
<%@page import="com.sds.secframework.common.util.StringUtil"%><html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet"/>
<!-- [if IE]><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
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
		
		if(flag == "change"){//승인
			if(confirm("<spring:message code="clm.page.msg.manage.announce076" />")){
			
				 if(frm.trgtman_nm.value=='' || frm.trgtman_id.value == ''){
					 alert("<spring:message code="clm.page.msg.manage.announce077" />");
					 return;
					 }else{
				frm.method.value = "modifyChange";
				viewHiddenProgress(true);
				frm.submit();
				
			   }
			}
		}else if(flag == "list"){//목록 버튼 클릭 시
			frm.method.value = "listChange";
			viewHiddenProgress(true);
		    frm.submit();
		}
	}
	
	$(function() {
		$("#trgtman_nm").keydown(function(event) {
			if(event.keyCode == "13") {
				searchEmployee();
			}
		});
	});

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
	*  변경 : 소유진 orgnzCd 비교하는 부분 추가함 
	*/
	function setUserInfos(obj){		
		var frm = document.frm;
		frm.cntrt_resp_dept.value 			= obj.departmentnumber; 	//trgtman_으로 변수명 통일했으나 동일 사업부 체크 로직 때문에 남겨둠(체결품의 컨트롤러에서 씀)
		frm.trgtman_id.value        		= obj.epid;					//변경담당자ID
    	frm.trgtman_nm.value        		= obj.cn;					//변경담당자명
    	frm.trgtman_dept.value				= obj.departmentnumber; 	//변경부서코드
    	frm.trgtman_dept_nm.value   		= obj.department;			//변경부서명 
    	frm.trgtman_in_dept.value			= obj.epindeptcode; 		//변경내부부서코드
    	frm.trgtman_jikgup_nm.value 		= obj.title;				//직급
   	 
   	 var options = { 
			    	url:"<c:url value='/clm/manage/consultation.do?method=getOrgnzCd' />",
			     	type:"post",
			    	dataType:"json",
			    	success:function(returnJson, returnMessage){
			    	}
   	       };
   		
   	    	
   		$("#frm").ajaxSubmit(options);	
	  
       
	}
	
</script>

</head>
<body>
<div id="wrap">
	<!-- container -->
	<div id="container">
	
	    <!-- Location -->
		<div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->
	
		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.msg.manage.chgContChrg" /></h3>
		</div>
		<!-- //title -->
	
		<!-- content -->
		<div id="content">
		<div id="content_in">
		<!-- **************************** Form Setting **************************** -->
			<form:form name="frm" id="frm" method="post" autocomplete="off">
			<!-- search form -->
			<input type="hidden" name="method"   value="" />
			<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>"/>
			<input type="hidden" name="trgtman_dept" value=""/>
			<input type="hidden" name="trgtman_in_dept" value=""/>
			<input type="hidden" name="cntrt_resp_dept" value=""/>
			<input type="hidden" name="srch_resp_dept" value="<c:out value='${command.srch_resp_dept}' escapeXml='false'/>"/>				<!-- 담당부서코드 -->
			<input type="hidden" name="srch_oppnt_cd" value="<c:out value='${command.srch_oppnt_cd}' escapeXml='false'/>"/>   				<!-- 계약상대방코드 -->
			<input type="hidden" name="srch_review_title" value="<c:out value='${command.srch_review_title}' escapeXml='false'/>" />		<!-- 의뢰명 -->
			<input type="hidden" name="srch_reqman_nm" value="<c:out value='${command.srch_reqman_nm}' escapeXml='false'/>" />				<!-- 의뢰자 -->
			<input type="hidden" name="srch_start_reqday" value="<c:out value='${command.srch_start_reqday}' escapeXml='false'/>" />		<!-- 의뢰 시작일 -->
			<input type="hidden" name="srch_end_reqday" value="<c:out value='${command.srch_end_reqday}' escapeXml='false'/>" />			<!-- 의뢰 종료일 -->
			<input type="hidden" name="srch_start_cnlsnday" value="<c:out value='${command.srch_start_cnlsnday}' escapeXml='false'/>" />	<!-- 계약 시작일 -->
			<input type="hidden" name="srch_end_cnlsnday" value="<c:out value='${command.srch_end_cnlsnday}' escapeXml='false'/>" />		<!-- 계약 종료일 -->
			<input type="hidden" name="srch_resp_dept_nm" value="<c:out value='${command.srch_resp_dept_nm}' escapeXml='false'/>" />		<!-- 담당부서명 -->
			<input type="hidden" name="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}' escapeXml='false'/>" />			<!-- 담당자명 -->
			<input type="hidden" name="srch_step" value="<c:out value='${command.srch_step}' escapeXml='false'/>" />			<!-- 담당자명 -->
			<input type="hidden" name="srch_biz_clsfcn" value="<c:out value='${command.srch_biz_clsfcn}' escapeXml='false'/>" />			<!-- 계약단계 -->
			<input type="hidden" name="srch_cnsdman_nm" value="<c:out value='${command.srch_cnsdman_nm}' escapeXml='false'/>" />			<!-- 검토자 -->
			<input type="hidden" name="srch_oppnt_nm" value="<c:out value='${command.srch_oppnt_nm}' escapeXml='false'/>" />				<!-- 계약상대방 -->
			<input type="hidden" name="srch_state" value="<c:out value='${command.srch_state}' escapeXml='false'/>" />						<!-- 상태 -->
			<input type="hidden" name="srch_cnclsnpurps_bigclsfcn" value="<c:out value='${command.srch_cnclsnpurps_bigclsfcn}' escapeXml='false'/>" />	<!-- 체결목적-->			
			
			<input type="hidden" name="list_mode" value="<c:out value='${command.list_mode}'/>"/>
			<input type="hidden" name="P_demnd_gbn" value="<c:out value='${command.p_demnd_gbn}'/>"/>
			<input type="hidden" name="p_prcs_depth" value="<c:out value='${command.p_prcs_depth}'/>" />
			<!-- key form-->
			<input type="hidden" name="srch_user_cntnt_type" value="" />
			<input type="hidden" name="srch_user_cntnt"      value="" />
			<input type="hidden" name="cnsdreq_id" value="<c:out value='${authReqInfo.cnsdreq_id}'/>" />
			<input type="hidden" name="cntrt_id" value="<c:out value='${authReqInfo.cntrt_id}'/>" />
			<!-- 알림메일전송 관련 추가 -->
			<input type="hidden" name="req_title" value="<c:out value='${authReqInfo.req_title}'/>" />
			<input type="hidden" name="cntrt_nm" value="<c:out value='${authReqInfo.cntrt_nm}'/>" />
			<input type="hidden" name="cntrt_respman_id" value="<c:out value='${authReqInfo.cntrt_respman_id}'/>" />
			<input type="hidden" name="cntrt_respman_nm" value="<c:out value='${authReqInfo.cntrt_respman_nm}'/>/<c:out value='${authReqInfo.cntrt_respman_jikgup_nm}'/>/<c:out value='${authReqInfo.cntrt_resp_dept_nm}'/>" />
			<!-- 첨부파일정보 -->
			
			<!-- // **************************** Form Setting **************************** -->
		    <!-- view -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
					<col width="15%" />
					<col />
					<col width="10%"/>
					<col width="20%"/>
					<col width="10%"/>
					<col width="20%"/>
				</colgroup>
				<tbody>
						<tr>
<!-- 							<th> -->
<%-- 							    <c:choose> --%>
<%-- 				 	    			<c:when test="${command.p_prcs_depth == 'C02501' || command.p_prcs_depth == 'C02502'}"> --%>
<%-- 				 	    			    <spring:message code="clm.page.field.manageRequest.reqTitle" /> --%>
<%-- 				 	    			</c:when> --%>
<%-- 				 	    			<c:otherwise> --%>
<%-- 				 	    			    <spring:message code="clm.page.field.manageRequest.cntrtNm" /> --%>
<%-- 				 	    			</c:otherwise> --%>
<%-- 			 	    			</c:choose> --%>
<!-- 							</th> -->
<!-- 							<td colspan="5"> -->
<%-- 							    <c:choose> --%>
<%-- 				 	    			<c:when test="${command.p_prcs_depth == 'C02501' || command.p_prcs_depth == 'C02502'}"> --%>
<%-- 				 	    			    <c:out value="${authReqInfo.req_title}"/> --%>
<%-- 				 	    			</c:when> --%>
<%-- 				 	    			<c:otherwise> --%>
<%-- 				 	    			    <c:out value="${authReqInfo.cntrt_nm}"/> --%>
<%-- 				 	    			</c:otherwise> --%>
<%-- 			 	    			</c:choose> --%>
<!-- 							</td> -->
							<th>
				 	    		<spring:message code="clm.page.field.manageRequest.reqTitle" />
				 	    	</th>
				 	    	<td colspan="5">							    
				 	    		<c:out value="${authReqInfo.req_title}"/>
				 	    	</td>
				 	    </tr>
				 	    <tr>
				 	    	<th>
				 	    		<spring:message code="clm.page.field.manageRequest.cntrtNm" />				 	    	
				 	    	</th>				 	    			    
							<td colspan="5">							    
			 	   			    <c:out value="${authReqInfo.cntrt_nm}"/>				 	    			
							</td>
						</tr>
						<tr>
							<th><spring:message code="clm.page.field.manageCommon.srchStep" /></th>
							<td><c:out value="${authReqInfo.prcs_depth_nm}"/></td>
							<th><spring:message code="clm.page.field.manageCommon.srchStatus" /></th>
							<td ><c:out value="${authReqInfo.depth_status_nm}"/></td>
							<th><spring:message code="clm.page.msg.manage.contId" /></th>
							<td><c:out value="${authReqInfo.cntrt_no}"/></td>
							 
						</tr>
						<tr>
							<th><spring:message code="clm.page.msg.manage.nowPer" /></th>
							<td><c:out value="${authReqInfo.cntrt_respman_nm}"/></td>
							<th><spring:message code="clm.page.field.manageRequest.jikgupNm" /></th>
							<td><c:out value="${authReqInfo.cntrt_respman_jikgup_nm}"/></td>
							<th><spring:message code="clm.page.field.manageRequest.deptNm" /></th>
							<td><c:out value="${authReqInfo.cntrt_resp_dept_nm}"/></td>
						</tr>
						<tr>
							<th title="<spring:message code="clm.page.msg.manage.announce068" htmlEscape="true" />"><spring:message code="clm.page.field.manageRequest.ChTrgtmanNm" /></th>
							<td>
		 	    			    <!-- input type="text" name="trgtman_nm" id="trgtman_nm" value="" style="width:80px" class="text_search"/><a href="javascript:searchEmployee();"><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search" /></a -->
		 	    			    <input type="text" name="trgtman_nm" id="trgtman_nm" value="" style="width:110px" class="text_search" onkeydown="javascript:if(event.keyCode==13){event.returnValue = false;searchEmployee();}" /><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchEmployee()" class="cp" alt="search" />		 	    			    
							    <input type="hidden" name="trgtman_id" id="trgtman_id" value="" />
							</td>
							<th><spring:message code="clm.page.field.manageRequest.jikgupNm" /></th>
							<td>
								  <span id="trgtman_jikgup_nm"><input type="text" name="trgtman_jikgup_nm" id="trgtman_jikgup_nm" value="" style="width:100%;border:0" readonly="readonly" onfocus="blur()" /></span>
							</td>
							<th><spring:message code="clm.page.field.manageRequest.deptNm" /></th>
							<td>
								<span id="trgtman_dept_nm"><input type="text" name="trgtman_dept_nm" id="trgtman_dept_nm" value="" style="width:100%;border:0" readonly="readonly" onfocus="blur()" /></span>
							</td>
						</tr>
						<tr class="end">
						    <th><spring:message code="clm.page.msg.manage.chgContent" /></th>
							<td colspan="5"><input type="text" id="demnd_cont" name="demnd_cont" value="" maxlength="1000" class="text_full" style="width:90%" /></td>
						</tr>
				</tbody>
			</table>
			<!-- //view -->
			
			<div class="btn_wrap">
		        <span class="btn_all_b"><span class="save"></span>
		        <c:choose>
		        <c:when test="${buttonAuth}">
		        	<a href="javascript:pageAction('change');">
		        </c:when>
		        <c:otherwise>
		        	<a href="javascript:alert('<spring:message code="secfw.page.field.alert.noModifyAuth" />');">
		        </c:otherwise>
		        </c:choose><spring:message code="clm.page.msg.manage.change" /></a></span>
				<span class="btn_all_w"><span class="list"></span><a href="javascript:pageAction('list');"><spring:message code="secfw.page.button.list" /></a></span>
			</div>
			</form:form>
			</div>
		</div>
		<!-- //content -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
		<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- // footer -->
</body>
</html>
