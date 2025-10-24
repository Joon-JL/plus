<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.Token" %>
<%@ page import="com.sec.las.statistics.dto.StatisticsForm" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>


<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Iterator" %>

<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.GregorianCalendar" %>
<%@ page import="java.math.BigDecimal" %>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>   
<%--
/**
 * 파     일     명 : ReportContractByDeptPop.jsp
 * 프로그램명 : 계약유형별 체결 현황 조회 POP
 * 설               명 : 
 * 작     성     자 : 서백진
 * 작     성     일 : 2011.09
 */
--%>
<%
	//메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	String returnMessage = (String)request.getAttribute("returnMessage");
	HttpSession hs = request.getSession(false);

	ArrayList roleList = (ArrayList)hs.getAttribute("secfw.session.role"); //session 사용할 경우
	ArrayList userRoleList = new ArrayList(); //role_cd만 꺼내기 위한 arrayList
	
	if(roleList != null && roleList.size() > 0){	
	    for(int idx = 0; idx < roleList.size(); idx++){	
	        HashMap roleListMap = (HashMap)roleList.get(idx);	 
	        userRoleList.add((String)roleListMap.get("role_cd"));
	    }
	}
	String accessLevel = "";
	
	//사용자 role 비교
	// RA00: 시스템관리자  RA01: 법무관리자  RA02: 법무담당자  RA03: 법무일반사용자
	// RC01: CP관리자
	// RD01: 사업부계약관리자  RD02: 사업부계약담당자
	// RM00: 시스템운영자
	
	if(userRoleList != null && userRoleList.size() > 0){
	    if(userRoleList.contains("RA00") || userRoleList.contains("RA01")|| userRoleList.contains("RA02")){
	        accessLevel = "A";
	    }else if(userRoleList.contains("RD01") || userRoleList.contains("RD02")){
	        accessLevel = "D";
	    }else if(userRoleList.contains("RC01")){
	        accessLevel = "B";
	    }else{
	        accessLevel = "C";
	    }
	    accessLevel = "A";
    
	}

	//out.println("blngt_orgnz:"+blngt_orgnz);
	//out.println("accessLevel:"+accessLevel);

%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.page.title.report.StatisticsContractByType" /></title>
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet"/>  
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script language="javascript">
<!--
	$(document).ready(function(){
	});
	$(function() {

		$(document).keydown(function(event){
	
		});
	});


	function showMessage(msg){
		if(msg != null && msg.length > 1) {
			//alert(msg);
		}
	}	

	function rdOpen(path, param, data, zoom, print) {
		
		var	rd_path = path;
		//var rd_param = param + "/ruseswapdisk /rswapfilepage [1000] /riprnmargin /rprncenteropt [1]";
		var rd_param = param;
		Rdviewer.AutoAdjust = 0;
	    Rdviewer.ZoomRatio  = 100;
	    Rdviewer.SetBackgroundColor(255,255,255);
	    Rdviewer.SetPagelineColor(255,255,255);
	    Rdviewer.ViewShowMode (2);
	    //Rdviewer.SetReportDialogInfo (1,"데이타를 조회중입니다.", "데이타 조회중", 1, "보고서를 작성중입니다", "보고서작성중");
		Rdviewer.HideStatusBar()
	    Rdviewer.SetPageScroll(0); 
		//Rdviewer.SetParameterEncrypt(1);
		//Rdviewer.SetKindOfParam(2);
		
<%		if(accessLevel.equals("A") || accessLevel.equals("D")){	%>
			Rdviewer.FileOpen(rd_path, rd_param);
<%		}else{	%>
			alert("권한이 없습니다.");
<%		}	%>
	    return true;
	}
		
//-->
</script>

</head>
<body > 

<div id="wrap">
	<!-- container -->
	<div id="container">	
	
	<form:form id="frm" name="frm" method="post" autocomplete="off">
		<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
		<!-- 페이지 공통 -->	
	    <!-- Location -->	
	    <div class="location">
	        <img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/>
	    </div>
	    <!-- //Location -->	
	
		<!-- Title -->
		<div class="title">
		<h3><a href="#"><spring:message code="las.page.title.report.StatisticsContractByType" /></a></h3>
		</div>
		
		<!-- content --> 		
		<div id="content">
	
		</div>
		<!-- content -->

</form:form>	
<!--  form 밑에 선언해야 정상적으로 호출됨 -->
	<div align="center">
		<table width="100%" align="center">
		<tr>
		<td height="600px" >
	
			<script language="javascript" src="<c:url value='/script/secfw/report/rdviewer.js' />"></script>
									     
			<script>	

			  	var parm = "<c:out value='${command.report_param}'/>";
			  	parm = replaceStr(parm, "&#039;","'");
			  	
			  	//alert(parm);
			  	//rdOpen("<c:out value='${command.report_url}'/>", parm , "", 100);
			  	rdOpen("<c:out value='${command.report_url}'/>"+"/report/<%=langCd.startsWith("ko")?"":langCd+"_"%>contractBytype_pop.mrd", parm , "", 100);
			  	
			</script>
		</td>
		</tr>	
		</table>	
	</div>	
	<!-- footer -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>	 
	<!-- // footer -->
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
</body>
</html>