<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Hashtable" %>
<%@ page import="com.sds.secframework.singleIF.dto.EmployeeVO" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="model.outldap.samsung.net.Employee" %>

<%--
/**
 * Subject   : ChangeReviewer_p.jsp
 * Author    : 제이남
 * Create On : 2013.06
 * 
 */
--%>
<%

%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<LINK href="<%=CSS%>/popup.css" type="text/css" rel="stylesheet">
<LINK href="<%=CSS%>/las.css" type="text/css" rel="stylesheet"> 
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->

<title><spring:message code='las.page.field.changRespman009' /></title><!-- 전자검토자 조회 -->

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript">
<!--

	$(function() {
		$("#allCheck").click(function() {
	        if(($(this).attr("checked"))) {         
	            $("input:checkbox[name='chkValues']")
	                .each(function(){
	                	if($(this).attr("disabled") == false){
	                    	$(this).attr("checked", true);
	                	}
	                });
	        } else {
	            $("input:checkbox[name='chkValues']")
	            .each(function(){
	                $(this).attr("checked", false);
	            });
	        }
	    }); 
		
	});
	//원담당자 조회
	function listUser()
	{
		var frm = document.frm;
		
		var srchValue = comTrim(frm.srch_user_nm.value); //입력받은 값

	    if (srchValue == "" || getByteLength(srchValue) < 4) { //최소 2자리 이상 입력받게 한다.
	        alert('<spring:message code="secfw.msg.error.nameMinByte" />');
	        frm.srch_user_nm.focus();
	        return;
	    }
		
		frm.target = "_self";
	
		frm.action = "<c:url value='/las/elecReviewer/elecReviewer.do' />";
		frm.method.value = "searchElecReviewer";
	
		frm.submit();
	}
	//원담당자/담당회사 선택
	function setUserInfos(){
		var frm = document.frm;
		
		var ck = false;
	    $("input[name='chkValues']:checked").each(function () {
	        ck = true;
	    });
	    
	    if(!ck) return;
	   
	    var userId = "";
	    var chkArr = "";
	    var compCd = "";
	    var compNm = "";
	    var userInfo = new Object();
	    $("input[name='chkValues']:checked").each(function (i) {

	    	chkArr = $(this).val().split("|");
	    	
	    	if(i == 0){
	    		userInfo.userId 	= chkArr[0];
	    		userInfo.userNm 	= chkArr[1];
	    		userInfo.jikgupNm 	= chkArr[2];
	    		userInfo.deptNm 	= chkArr[3];
	    		userInfo.deptCd 	= chkArr[4];
	    		compCd 				= chkArr[5];
	    		compNm 				= chkArr[6];
	    		
	    		userId 				= chkArr[0];
	    	}else{
	    		
	    		if(userId != chkArr[0]){
	    			userId = "";
	    			return false;
	    		}
	    		
	    		compCd += ","+chkArr[5];
	    		compNm += ","+chkArr[6];
	    	}
	    });
	    
	    if(userId == "") return; //선택한 전자 검토자가 동일하지 않을 경우 선택못함
	    
	    userInfo.compCd = compCd;
	    userInfo.compNm = compNm;
		opener.setCompInfos(userInfo);
		//window.close();

	}
		
//-->
</script>

</head>
<body class="pop">
<h1><spring:message code='las.page.field.changRespman009'/><span class="close" onclick="javascript:self.close();" title="close"></span></h1>
	<!-- //header -->
	<!-- body -->
	<div class="pop_area">
		<!-- Popup Detail -->
		<!-- Popup Size width:630px; -->
	
		  <div class="pop_content">
      	  
	      	<form:form name="frm" method="post" autocomplete="off">
			<!-- 페이지 공통 -->
			<input type="hidden" name="method"   value="">
			<input type="hidden" name="doUserSearch"   value="Y">
			<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>"/>
			<!-- 페이지별  -->
		      	  
      	  
			<div  class="search_box">
			<div class="search_box_inner">
					<table class="search_tb">
						<colgroup>
							<col/> 
							<col width="75px"/>
						</colgroup>
						<tr>
							<td>
								
								<table  class="search_form">
									<colgroup>
										<col width="15%"/>
										<col width="65%"/>
										<col width="20%"/>
									</colgroup>
									<tr>
						                <th><spring:message code="secfw.page.field.user.user_nm" /></th> 
	 									<td><input type="text" name="srch_user_nm"  value="<c:out value='${command.srch_user_nm}'/>" class="text" onKeyPress="if(event.keyCode==13) {listUser();return false;}"/></td>
						            	<td class="tC"><a href="javascript:listUser();"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="las.page.field.lawconsulting.search"/>"/></a></td>
									</tr>
								</table>
							
							</td>
						</tr>
					</table>
				</div>		
			</div>
		
        <!-- List -->
        <div class='tableWrap mt20'>
		<div class='tableone'>
        <table class="list_basic">
			<colgroup>
				<col width="7%" align="center"/> 
				<col width="53%" align="center"/> 
				<col width="40%" align="center"/> 
			</colgroup>
			<tr>
				<th><input type="checkbox" id='allCheck' name='allCheck' /></th>
				<th><spring:message code="secfw.page.field.user.user_nm" /></th>
				<th><spring:message code='las.page.field.changRespman005' /> <!--담당회사 --></th>
			</tr>
		</table>
		</div>
		</div>
		<style>
			.h_120 {height:120px;}
			*html .h_120 {height:120px;}
		</style>
		<div class='tabletwo h_120'>
		<table class="list_scr">
			<colgroup>
				<col width="7%" align="center"/> 
				<col width="53%" align="center"/> 
				<col width="40%" align="center"/> 
			</colgroup>
			<c:choose>
				<c:when test="${!empty resultList }" >
					<c:forEach var="list" items="${resultList}">
					<tr>
						<td class='tC'><input type="checkbox" name="chkValues" 
							<c:if test="${list.chk_tmp_cd == 'Y'}">disabled</c:if>
							value="<c:out value='${list.user_id}'/>|<c:out value='${list.user_real_nm}'/>|<c:out value='${list.jikgup_nm}'/>|<c:out value='${list.dept_nm}'/>|<c:out value='${list.dept_cd}'/>|<c:out value='${list.mng_comp_cd}'/>|<c:out value='${list.mng_comp_nm}'/>" /></td>
						<td class='tL'><c:out value='${list.user_real_nm}'/> / <c:out value='${list.jikgup_nm}'/> / <c:out value='${list.dept_nm}'/></td>
						<td class='tL'><c:out value='${list.mng_comp_nm}'/></td>
					</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td class='tC' colspan='3'><spring:message code="las.msg.succ.noResult" /></td>
					</tr>
				</c:otherwise>
			</c:choose>

		</table>
		<div class="alert_msg"><span><spring:message code="las.page.field.changRespman018" /></div>
		</div>
        <!-- //List -->
		</form:form>
  </div>
</div>



<!--footer -->
<div class="pop_footer">
	<div class="btn_wrap tR" >
	<span class="btn_all_w" onclick="setUserInfos();"><span class="check"></span><a><spring:message code="las.page.field.lawconsulting.select"/></a></span>
		<span class="btn_all_w"><span class="cancel"></span><a href="#" onclick="javascript:self.close();"><spring:message code="secfw.page.button.close" /></a></span>
	</div>
</div>
<!-- //footer -->

</body>
</html>