<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>
<%@ page import="com.sec.las.board.dto.NoticeForm" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<%--
/**
 * 파  일  명 : ChangeReviewer_i.jsp
 * 프로그램명 : 전자검토자 임시 담당자 지정
 * 작  성  자 : 제이남
 * 작  성  일 : 2013.06.19
 */
 --%>

<%

%>

<html>
<head>
<meta charset="utf-8" />

<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript">
	//목록
	function goList(){
		var frm 	= document.frm;
		
		frm.method.value 	= "listChangeReviewer";
		frm.action 			= "<c:url value='/las/elecReviewer/elecReviewer.do' />";	
		frm.target 			= "_self";
		viewHiddenProgress(true);
		frm.submit();	
	}
	//임시담당자 지정
	function changeReviewer(){
		var frm 	= document.frm;
		
		if($('#comp_cd').val() == "") return;
		if($('#org_resp_id').val() == "") return;
		if($('#tmp_resp_id').val() == "") return;
		
		if(!confirm("<spring:message code='las.page.field.changRespman012' />")) return; //임시담당자 지정하시겠습니까?
		
		frm.method.value 	= "appointTmpElecReviewer";
		frm.action 			= "<c:url value='/las/elecReviewer/elecReviewer.do' />";	
		frm.target 			= "_self";
		viewHiddenProgress(true);
		frm.submit();
	}
	
	//원담당자 조회
	function searchEmployee(type) {
		var frm = document.frm;	
		var srchValue 	= "";
	  	var obj 		= new Object();
	  	
	  	if(type == 'org'){
	  		obj = frm.org_nm;
	  		frm.method.value = "searchElecReviewer";
	  		PopUpWindowOpen('', "520", "300", false);
	  	}
	  	else{
	  		obj = frm.tmp_nm;
	  		frm.method.value = "searchElecReviewerTmp";
	  		PopUpWindowOpen('', "520", "500", false);
	  	}
	  	
	  	
	  	srchValue = comTrim(obj.value);
	  	
		frm.srch_user_nm.value = srchValue;
		frm.target = "PopUpWindow";		 
		frm.action = "<c:url value='/las/elecReviewer/elecReviewer.do' />";			
		
		//if (srchValue == "" || srchValue.length < 4) { //최소 2자리 이상 입력받게 한다.
		//	alert("<spring:message code='secfw.msg.error.nameMinByte' />");
		//	obj.focus();
		//} else {
			
			frm.submit();	
		//}
		
	}
	//원담당자조회 결과 정보 세팅
	function setCompInfos(userInfo){
		
		var userId 		= userInfo.userId;
		var userNm 		= userInfo.userNm;
		var jikgupNm 	= userInfo.jikgupNm;
		var deptNm   	= userInfo.deptNm;
		var deptCd   	= userInfo.deptCd;
		var compCds 	= userInfo.compCd;
		var compNms		= userInfo.compNm;

		var chkArr 		= compCds.split(",");
		
		if(chkArr.length > 0 && $('#mngCompCd').val() != ""){
			for(i =0; i < chkArr.length; i++){
				if(chkArr[i] != "" && $('#mngCompCd').val().indexOf(chkArr[i]) > -1){
					window.open("", "PopUpWindow").close();
					//alert("<spring:message code='las.page.field.changRespman019'/>");//지정할 담당회사와 임시담당자의 담당회사가 동일할 경우는 임시담당자를 지정할 수 없습니다.
					setTimeout("chkAlert()", 1000*0.3);
					return false;
				}
			}
		}
		
		//원담당자 정보 세팅
		$('#org_nm').val(userNm);
		$('#sp_org_jikgup').text(jikgupNm);
		$('#sp_org_dept').text(deptNm);
		$('#sp_comp_cds').text(compNms);
		
		//hidden value setting
		$('#comp_cd').val(compCds);
		$('#comp_nm').val(compNms);
		$('#org_resp_id').val(userId);
		$('#org_resp_nm').val(userNm);
		$('#org_resp_dept').val(deptCd);
		$('#org_resp_dept_nm').val(deptNm);
		$('#org_resp_jikgup_nm').val(jikgupNm);
		
		window.open("", "PopUpWindow").close();
	}
	
	/**
	 * 임시담당자 조회정보 결과 setting
	 */
	 function setUserInfos(userInfo) {
		
		var userId 		= userInfo.userId;
		var userNm 		= userInfo.userNm;
		var jikgupNm 	= userInfo.jikgupNm;
		var deptNm   	= userInfo.deptNm;
		var deptCd   	= userInfo.deptCd;
		var mngCompCd	= userInfo.mngCompCd;
		
		var chkArr 		= mngCompCd.split(",");
		
		if(chkArr.length > 0 && $('#comp_cd').val() != ""){
			for(i =0; i < chkArr.length; i++){
				if(chkArr[i] != "" && $('#comp_cd').val().indexOf(chkArr[i]) > -1){//
					window.open("", "PopUpWindow").close();
					//alert("<spring:message code='las.page.field.changRespman019'/>");//지정할 담당회사와 임시담당자의 담당회사가 동일할 경우는 임시담당자를 지정할 수 없습니다.
					setTimeout("chkAlert()", 1000*0.3);
					return false;
				}
			}
		}
		
		$('#mngCompCd').val(mngCompCd);

		//임시담당자 정보 세팅
		$('#tmp_nm').val(userNm);
		$('#sp_tmp_jikgup').text(jikgupNm);
		$('#sp_tmp_dept').text(deptNm);
		
		//hidden value setting
		$('#tmp_resp_id').val(userId);
		$('#tmp_resp_nm').val(userNm);
		$('#tmp_resp_dept').val(deptCd);
		$('#tmp_resp_dept_nm').val(deptNm);
		$('#tmp_resp_jikgup_nm').val(jikgupNm);
		
		window.open("", "PopUpWindow").close();
	}
	
	function chkAlert(){
		alert("<spring:message code='las.page.field.changRespman019'/>");//지정할 담당회사와 임시담당자의 담당회사가 동일할 경우는 임시담당자를 지정할 수 없습니다.
	}
</script>
</head>
<body>

<div id="wrap">	
	<div id="container">
		<!-- location -->
		<div class="location"><IMG SRC="<%=IMAGE %>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //location -->
		<!-- title -->
		<div class="title">
			<h3><spring:message code='las.page.field.changRespman001' /> <!--전자검토자 임시 담당자 지정 --></h3>
		</div>
		<!-- //title -->
		
		<!-- content -->
		<div id="content"> 
		<div id="content_in">
			<form:form name="frm" id="frm" autocomplete="off">
			<input type="hidden" name="method"   	 	value="" />
			<input type="hidden" name="menu_id"  	 	value="<c:out value='${command.menu_id}'/>" />
			<!-- 임직원 조회 -->
			<input type="hidden" name="srch_user_cntnt_target" value=""/>
			<input type="hidden" name="srch_user_cntnt_type" value=""/>
			<input type="hidden" name="srch_user_cntnt" value=""/>
			<input type="hidden" name="srch_user_nm" value=""/>
			<!--// 임직원 조회 -->
			<input type="hidden" name="comp_cd" id="comp_cd" />	
			<input type="hidden" name="comp_nm" id="comp_nm" />	
			<input type="hidden" name="org_resp_id" id="org_resp_id" />	
			<input type="hidden" name="org_resp_nm" id="org_resp_nm" />	
			<input type="hidden" name="org_resp_dept" id="org_resp_dept" />	
			<input type="hidden" name="org_resp_dept_nm" id="org_resp_dept_nm" />	
			<input type="hidden" name="org_resp_jikgup_nm" id="org_resp_jikgup_nm" />
			<input type="hidden" name="tmp_resp_id" id="tmp_resp_id" />	
			<input type="hidden" name="tmp_resp_nm" id="tmp_resp_nm" />	
			<input type="hidden" name="tmp_resp_dept" id="tmp_resp_dept" />	
			<input type="hidden" name="tmp_resp_dept_nm" id="tmp_resp_dept_nm" />	
			<input type="hidden" name="tmp_resp_jikgup_nm" id="tmp_resp_jikgup_nm" />
			<input type="hidden" name="mngCompCd" id="mngCompCd" />		
			<!-- //**************************** Form Setting **************************** -->
					
			<!--  view  -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
					<col width="14%"/>
					<col width="20%"/>
					<col width="13%"/>
					<col width="20%"/>
					<col width="13%"/>
					<col width="20%"/>
				</colgroup>
				<tbody>	
					<tr>
						<th><spring:message code='las.page.field.changRespman004' /> <!--원 담당자 --></th>
						<td><input type="text" name="org_nm" id="org_nm" value="" style="width:110px" class="text_search" onKeyPress="if(event.keyCode==13) {searchEmployee('org');return false;}" /><img src="<%=IMAGE %>/icon/ico_search.gif" class="cp" alt="search" onclick="searchEmployee('org')" style="cursor:pointer"/></td>
						<th><spring:message code='las.page.field.mainproject.operdiv_respman_jikgup'/><!-- 직급 --></th>
						<td><span id="sp_org_jikgup"></span></td>
						<th><spring:message code='las.page.field.mainproject.operdiv_respman_dept_nm'/><!-- 부서 --></th>
						<td><span id="sp_org_dept"></span></td>
					</tr>
					<tr>
		            	<th><spring:message code='las.page.field.changRespman005' /> <!-- 담당회사 --></th>
		            	<td colspan="5"><span id="sp_comp_cds"></span></td>
		          	</tr>
		          	<tr>
						<th><spring:message code='las.page.field.changRespman006' /> <!-- 임시 담당자 --></th>
						<td><input type="text" name="tmp_nm" id="tmp_nm" value="" style="width:110px" class="text_search" onKeyPress="if(event.keyCode==13) {searchEmployee('tmp');return false;}" /><img src="<%=IMAGE %>/icon/ico_search.gif" class="cp" alt="search" onclick="searchEmployee('tmp')" style="cursor:pointer"/></td>
						<th><spring:message code='las.page.field.mainproject.operdiv_respman_jikgup'/><!-- 직급 --></th>
						<td><span id="sp_tmp_jikgup"></span></td>
						<th><spring:message code='las.page.field.mainproject.operdiv_respman_dept_nm'/><!-- 부서 --></th>
						<td><span id="sp_tmp_dept"></span></td>
					</tr>
					<tr>
		            	<th><spring:message code='las.page.field.changRespman007' /> <!-- 임시 담당자</br>지정 사유--></th>
		            	<td colspan="5">
		            	<textarea name="tmp_cause" id="tmp_cause" cols="30" rows="3" class="text_area_full" ></textarea>
		            	</td>
		          	</tr>
				</tbody>
			</table>
			<!-- //view  -->
			
			<!-- Function Button  -->
			<div class="t_titBtn">
				<div class="btn_wrap">
				<span class="btn_all_w"><span class="list"></span><a href="javascript:changeReviewer();"><spring:message code='las.page.field.changRespman008' /> <!-- 지정완료 --></a></span>
					<span class="btn_all_w" onclick="goList();"><span class="list"></span><a><spring:message code='las.page.button.list' /></a></span>
				</div>
			</div>
			<!-- //Function Button  -->
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