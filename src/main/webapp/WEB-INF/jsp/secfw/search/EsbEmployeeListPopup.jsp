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
 * Subject   : EsbEmployeeListPopup.jsp
 * Author    : 금현서
 * Create On : 2009.11
 * 
 */
--%>
<%

	Vector listUser    = (Vector)request.getAttribute("resultList");
	EmployeeVO command = (EmployeeVO)request.getAttribute("command");
	
	String srchCntntType = command.getSrch_user_cntnt_type();
	String srchCntnt     = command.getSrch_user_cntnt();

	int listSize = 0;
	
	if(listUser != null && listUser.size()>0) {
		listSize = listUser.size();
	}
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<LINK href="<%=CSS%>/popup.css" type="text/css" rel="stylesheet">
<LINK href="<%=CSS%>/las.css" type="text/css" rel="stylesheet"> 
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->

<title><spring:message code="secfw.page.field.search.empSearch"/></title>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript">
<!--

	$(function() {

		if(<%=listSize %> == 1) {
			setUserInfos();
		}
	});
	
	function listUser()
	{
		var frm = document.frm;
		
		var srchValue = comTrim(frm.srch_user_cntnt.value); //입력받은 값

	    if (srchValue == "" || getByteLength(srchValue) < 4) { //최소 2자리 이상 입력받게 한다.
	        alert('<spring:message code="secfw.msg.error.nameMinByte" />');
	        frm.srch_user_cntnt.focus();
	        return;
	    }
		
		if(srchValue.indexOf(";") > 0){
			alert('On this page, you can not search for multi-user.');			
			frm.srch_user_cntnt.value = "";
			frm.srch_user_cntnt.focus();
	        return;
		}
		
		frm.target = "_self";
		frm.action = "<c:url value='/secfw/esbOrg.do' />";
		if("Y" == "<c:out value='${elecYn}'/>"){
			frm.method.value = "listEsbElecEmployee";
		}else{
			frm.method.value = "listEsbEmployee";
		}
		
		frm.submit();
	}
		
	function setUserInfos(index){

		var frm = document.frm;
		var listSize = <%=listSize%>;
		
		if(listSize == 1) {
			var userInfo = new Object();
			var index = 0;

			userInfo.departmentnumber         = $("#departmentnumber_" + index).val();//부서코드
			userInfo.employeenumber           = $("#employeenumber_" + index).val();//사번
			userInfo.employeetype             = $("#employeetype_" + index).val();//사용자구분
			userInfo.epbusicode               = $("#epbusicode_" + index).val();//사업장 코드
			userInfo.o                        = $("#o_" + index).val();//회사명
			userInfo.eppostaladdress          = $("#eppostaladdress_" + index).val();//회사주소
			userInfo.epsuborgname             = $("#epsuborgname_" + index).val();//총괄명
			userInfo.epbusiname               = $("#epbusiname_" + index).val();//사업장명
			userInfo.department               = $("#department_" + index).val();//부서명
			userInfo.epregionname             = $("#epregionname_" + index).val();//지역명
			userInfo.cn                       = $("#cn_" + index).val();//전체 이름
			userInfo.sn                       = $("#sn_" + index).val();//성
			userInfo.givenname                = $("#givenname_" + index).val();//이름
			userInfo.description              = $("#description_" + index).val();//담당업무
			userInfo.title                    = $("#title_" + index).val();//직급명
			userInfo.epgradename              = $("#epgradename_" + index).val();//직위명
			userInfo.epsendcompanyname        = $("#epsendcompanyname_" + index).val();//파견 회사명
			userInfo.epsendsuborgname         = $("#epsendsuborgname_" + index).val();//파견 총괄명
			userInfo.epsenddeptname           = $("#epsenddeptname_" + index).val();//파견 부서명
			userInfo.epsendtitlename          = $("#epsendtitlename_" + index).val();//파견 직급명
			userInfo.epsendgradename          = $("#epsendgradename_" + index).val();//파견 직위명
			userInfo.epid                     = $("#epid_" + index).val();//EPID
			userInfo.eporganizationnumber     = $("#eporganizationnumber_" + index).val();//회사코드
			userInfo.epregioncode             = $("#epregioncode_" + index).val();//지역코드
			userInfo.epsuborgcode             = $("#epsuborgcode_" + index).val();//총괄코드
			userInfo.eptitlenumber            = $("#eptitlenumber_" + index).val();//직급코드
			userInfo.epuserstatus             = $("#epuserstatus_" + index).val();//임직원 상태
			userInfo.l                        = $("#l_" + index).val();//회사주소1
			userInfo.mail                     = $("#mail_" + index).val();//메일주소
			userInfo.mobile                   = $("#mobile_" + index).val();//핸드폰
			userInfo.postaladdress            = $("#postaladdress_" + index).val();//회사 주소 2
			userInfo.postalcode               = $("#postalcode_" + index).val();//회사 우편번호
			userInfo.telephonenumber          = $("#telephonenumber_" + index).val();//회사 전화번호
			userInfo.userid                   = $("#userid_" + index).val();//마이싱글ID
			userInfo.eptitlesortorder         = $("#eptitlesortorder_" + index).val();//직급 정렬 순서
			userInfo.epsendtitlesortorder     = $("#epsendtitlesortorder_" + index).val();//파견 직급 정렬 순서
			userInfo.c                        = $("#c_" + index).val();//국가
			userInfo.dn                       = $("#dn_" + index).val();//dn
			userInfo.epdefaultcompcode        = $("#epdefaultcompcode_" + index).val();//기본소속구분코드
			userInfo.epgradeortitle           = $("#epgradeortitle_" + index).val();//직급/직위 표기방식
			userInfo.episblue                 = $("#episblue_" + index).val();//임원여부
			userInfo.eppreferredlanguage      = $("#eppreferredlanguage_" + index).val();//표현언어
			userInfo.epsecuritylevel          = $("#epsecuritylevel_" + index).val();//보안등급
			userInfo.epsendbusicode           = $("#epsendbusicode_" + index).val();//파견사업장코드
			userInfo.epsendcompanycode        = $("#epsendcompanycode_" + index).val();//파견회사코드
			userInfo.epsenddeptcode           = $("#epsenddeptcode_" + index).val();//파견부서코드
			userInfo.epsendgradeortitle       = $("#epsendgradeortitle_" + index).val();//파견사 직급/직위 표기방식
			userInfo.epsendregioncode         = $("#epsendregioncode_" + index).val();//파견지역코드
			userInfo.epsendsecuritylevel      = $("#epsendsecuritylevel_" + index).val();//파견보안등급
			userInfo.epsendsuborgcode         = $("#epsendsuborgcode_" + index).val();//파견총괄코드
			userInfo.epsendtitlenumber        = $("#epsendtitlenumber_" + index).val();//파견직급코드
			userInfo.epuserlevel              = $("#epuserlevel_" + index).val();//사용자등급
			userInfo.facsimiletelephonenumber = $("#facsimiletelephonenumber_" + index).val();//회사팩스번호
			userInfo.nickname                 = $("#nickname_" + index).val();//Nickname
			userInfo.preferredlanguage        = $("#preferredlanguage_" + index).val();//메일자동응답언어
			userInfo.epvoipnumber             = $("#epvoipnumber_" + index).val();//인터넷전화
			userInfo.mailHost                 = $("#mailHost_" + index).val();//메일호스트
			userInfo.epindeptcode             = $("#epindeptcode_" + index).val();//내부부서코드
			userInfo.epjob                    = $("#epjob_" + index).val();//직무코드
			userInfo.epjobname                = $("#epjobname_" + index).val();//직무명
			userInfo.epindeptcodename         = $("#epindeptcodename_" + index).val();//내부부서명
			userInfo.epwithdrawdate           = $("#epwithdrawdate_" + index).val();//퇴직/휴직일
			userInfo.epnative                 = $("#epnative_" + index).val();//현채인여부
			userInfo.epuserclassify           = $("#epuserclassify_" + index).val();//실가명구분
			userInfo.serverlocation           = $("#serverlocation_" + index).val();//서버위치
            
			opener.setUserInfos(userInfo);
			window.close();
			
		} else if(listSize > 1 ) {

				var userInfo = new Object();

				userInfo.departmentnumber         = eval("frm.departmentnumber_" + index+".value");//부서코드
				userInfo.employeenumber           = eval("frm.employeenumber_" + index+".value");//사번
				userInfo.employeetype             = eval("frm.employeetype_" + index+".value");//사용자구분
				userInfo.epbusicode               = eval("frm.epbusicode_" + index+".value");//사업장 코드
				userInfo.o                        = eval("frm.o_" + index+".value");//회사명
				userInfo.eppostaladdress          = eval("frm.eppostaladdress_" + index+".value");//회사주소
				userInfo.epsuborgname             = eval("frm.epsuborgname_" + index+".value");//총괄명
				userInfo.epbusiname               = eval("frm.epbusiname_" + index+".value");//사업장명
				userInfo.department               = eval("frm.department_" + index+".value");//부서명
				userInfo.epregionname             = eval("frm.epregionname_" + index+".value");//지역명
				userInfo.cn                       = eval("frm.cn_" + index+".value");//전체 이름
				userInfo.sn                       = eval("frm.sn_" + index+".value");//성
				userInfo.givenname                = eval("frm.givenname_" + index+".value");//이름
				userInfo.description              = eval("frm.description_" + index+".value");//담당업무
				userInfo.title                    = eval("frm.title_" + index+".value");//직급명
				userInfo.epgradename              = eval("frm.epgradename_" + index+".value");//직위명
				userInfo.epsendcompanyname        = eval("frm.epsendcompanyname_" + index+".value");//파견 회사명
				userInfo.epsendsuborgname         = eval("frm.epsendsuborgname_" + index+".value");//파견 총괄명
				userInfo.epsenddeptname           = eval("frm.epsenddeptname_" + index+".value");//파견 부서명
				userInfo.epsendtitlename          = eval("frm.epsendtitlename_" + index+".value");//파견 직급명
				userInfo.epsendgradename          = eval("frm.epsendgradename_" + index+".value");//파견 직위명
				userInfo.epid                     = eval("frm.epid_" + index+".value");//EPID
				userInfo.eporganizationnumber     = eval("frm.eporganizationnumber_" + index+".value");//회사코드
				userInfo.epregioncode             = eval("frm.epregioncode_" + index+".value");//지역코드
				userInfo.epsuborgcode             = eval("frm.epsuborgcode_" + index+".value");//총괄코드
				userInfo.eptitlenumber            = eval("frm.eptitlenumber_" + index+".value");//직급코드
				userInfo.epuserstatus             = eval("frm.epuserstatus_" + index+".value");//임직원 상태
				userInfo.l                        = eval("frm.l_" + index+".value");//회사주소1
				userInfo.mail                     = eval("frm.mail_" + index+".value");//메일주소
				userInfo.mobile                   = eval("frm.mobile_" + index+".value");//핸드폰
				userInfo.postaladdress            = eval("frm.postaladdress_" + index+".value");//회사 주소 2
				userInfo.postalcode               = eval("frm.postalcode_" + index+".value");//회사 우편번호
				userInfo.telephonenumber          = eval("frm.telephonenumber_" + index+".value");//회사 전화번호
				userInfo.userid                   = eval("frm.userid_" + index+".value");//마이싱글ID
				userInfo.eptitlesortorder         = eval("frm.eptitlesortorder_" + index+".value");//직급 정렬 순서
				userInfo.epsendtitlesortorder     = eval("frm.epsendtitlesortorder_" + index+".value");//파견 직급 정렬 순서
				userInfo.c                        = eval("frm.c_" + index+".value");//국가
				userInfo.dn                       = eval("frm.dn_" + index+".value");//dn
				userInfo.epdefaultcompcode        = eval("frm.epdefaultcompcode_" + index+".value");//기본소속구분코드
				userInfo.epgradeortitle           = eval("frm.epgradeortitle_" + index+".value");//직급/직위 표기방식
				userInfo.episblue                 = eval("frm.episblue_" + index+".value");//임원여부
				userInfo.eppreferredlanguage      = eval("frm.eppreferredlanguage_" + index+".value");//표현언어
				userInfo.epsecuritylevel          = eval("frm.epsecuritylevel_" + index+".value");//보안등급
				userInfo.epsendbusicode           = eval("frm.epsendbusicode_" + index+".value");//파견사업장코드
				userInfo.epsendcompanycode        = eval("frm.epsendcompanycode_" + index+".value");//파견회사코드
				userInfo.epsenddeptcode           = eval("frm.epsenddeptcode_" + index+".value");//파견부서코드
				userInfo.epsendgradeortitle       = eval("frm.epsendgradeortitle_" + index+".value");//파견사 직급/직위 표기방식
				userInfo.epsendregioncode         = eval("frm.epsendregioncode_" + index+".value");//파견지역코드
				userInfo.epsendsecuritylevel      = eval("frm.epsendsecuritylevel_" + index+".value");//파견보안등급
				userInfo.epsendsuborgcode         = eval("frm.epsendsuborgcode_" + index+".value");//파견총괄코드
				userInfo.epsendtitlenumber        = eval("frm.epsendtitlenumber_" + index+".value");//파견직급코드
				userInfo.epuserlevel              = eval("frm.epuserlevel_" + index+".value");//사용자등급
				userInfo.facsimiletelephonenumber = eval("frm.facsimiletelephonenumber_" + index+".value");//회사팩스번호
				userInfo.nickname                 = eval("frm.nickname_" + index+".value");//Nickname
				userInfo.preferredlanguage        = eval("frm.preferredlanguage_" + index+".value");//메일자동응답언어
				userInfo.epvoipnumber             = eval("frm.epvoipnumber_" + index+".value");//인터넷전화
				userInfo.mailHost                 = eval("frm.mailHost_" + index+".value");//메일호스트
				userInfo.epindeptcode             = eval("frm.epindeptcode_" + index+".value");//내부부서코드
				userInfo.epjob                    = eval("frm.epjob_" + index+".value");//직무코드
				userInfo.epjobname                = eval("frm.epjobname_" + index+".value");//직무명
				userInfo.epindeptcodename         = eval("frm.epindeptcodename_" + index+".value");//내부부서명
				userInfo.epwithdrawdate           = eval("frm.epwithdrawdate_" + index+".value");//퇴직/휴직일
				userInfo.epnative                 = eval("frm.epnative_" + index+".value");//현채인여부
				userInfo.epuserclassify           = eval("frm.epuserclassify_" + index+".value");//실가명구분
				userInfo.serverlocation           = eval("frm.serverlocation_" + index+".value");//서버위치
				
			opener.setUserInfos(userInfo);
			window.close();
		}
	}
		
//-->
</script>

</head>
<body class="pop">
<h1><spring:message code="secfw.page.field.search.empSearch"/><span class="close" onclick="javascript:self.close();" title="close"></span></h1>
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
					
							
								<table  class="search_form">
									<colgroup>
										<col width="15%"/>
										<col width="70%"/>
										<col width="15%"/>
								</colgroup>
								<tr>
								
									<th>
					              		<select name="srch_user_cntnt_type" class="select" >
											<option value="userName" <%="userName".equals(command.getSrch_user_cntnt_type()) ? "selected" : "" %>><spring:message code="secfw.page.field.user.user_nm" /></option>
											<option value="userId" <%="userId".equals(command.getSrch_user_cntnt_type()) ? "selected" : "" %>>SingleID</option>
										</select>
 									</th> 
 									<td>
					              		<input type="text" name="srch_user_cntnt"  value="<c:out value='${command.srch_user_cntnt}'/>" class="text" onKeyPress="if(event.keyCode==13) {listUser();return false;}"/>
					              	</td>
					              	<td class="tC"><a href="javascript:listUser();"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="las.page.field.lawconsulting.search"/>"/></a></td>
								</tr>
								</table>
							
				</div>		
			</div>
		
        <!-- List -->
        <div class='tableWrap mt20'>
		<div class='tableone'>
        <table class="list_basic">
			<colgroup>
				<col width="15%" align="center"/> 
				<col width="20%" align="center"/> 
				<col width="35%" align="center"/> 
				<col width="15%" align="center"/> 
				<col width="15%" align="center"/> 
			</colgroup>
			<tr>
				<th>SingleID</th>
				<th><spring:message code="secfw.page.field.user.user_nm" /></th>
				<th>
					<spring:message code="secfw.page.field.user.dept" /> / 
					<spring:message code="secfw.page.field.user.jikgup" /> / 
					<spring:message code="secfw.page.field.user.comp" />
				</th>
				<th><spring:message code="las.page.field.usermng.workno" /></th>
				<th class="end"><spring:message code="las.page.field.usermng.mobileno" /></th>
				
			</tr>
		</table>
		</div>
		</div>
		<style>
			.h_120 {height:273px;}
			*html .h_120 {height:273px;}
		</style>
		<div class='tabletwo h_120'>
		<table class="list_scr">
			<colgroup>
				<col width="15%" align="center"/> 
				<col width="20%" align="center"/> 
				<col width="35%" align="center"/> 
				<col width="15%" align="center"/> 
				<col width="15%" align="center"/> 
</colgroup>
			
 	<%
 	if(listUser != null && listUser.size()>0) {
	    for(int idx=0;idx < listUser.size();idx++){
	    
	    	Hashtable ht = (Hashtable)listUser.get(idx);

	%> 
			<tr>
				<td align="center"><a href="javascript:setUserInfos('<%=idx %>');"><%=ht.get("userid") %></a></td>
				<td align="center"><a href="javascript:setUserInfos('<%=idx %>');"><%=ht.get("cn") %></a></td>
				<td align="center" title="<%=ht.get("department") + "/" + ht.get("title") + "/" + ht.get("o") %>" ><div style='width:230;overflow:hidden;text-overflow:ellipsis'><nobr><%=ht.get("department") + "/" + ht.get("title") + "/" + ht.get("o") %></nobr></div></td>
				<td align="center"><%=ht.get("telephonenumber") %></td>
				<td class='end'><%=ht.get("mobile") %></td>
				
				<div style='display:none'>
				<input type=hidden name="departmentnumber_<%=idx%>" id="departmentnumber_<%=idx%>" value='<%=ht.get("departmentnumber") %>'>
				<input type=hidden name="employeenumber_<%=idx%>" id="employeenumber_<%=idx%>" value='<%=ht.get("employeenumber") %>'>
				<input type=hidden name="employeetype_<%=idx%>" id="employeetype_<%=idx%>" value='<%=ht.get("employeetype") %>'>
				<input type=hidden name="epbusicode_<%=idx%>" id="epbusicode_<%=idx%>" value='<%=ht.get("epbusicode") %>'>
				<input type=hidden name="o_<%=idx%>" id="o_<%=idx%>" value='<%=ht.get("o") %>'>
				<input type=hidden name="eppostaladdress_<%=idx%>" id="eppostaladdress_<%=idx%>" value='<%=ht.get("eppostaladdress") %>'>
				<input type=hidden name="epsuborgname_<%=idx%>" id="epsuborgname_<%=idx%>" value='<%=ht.get("epsuborgname") %>'>
				<input type=hidden name="epbusiname_<%=idx%>" id="epbusiname_<%=idx%>" value='<%=ht.get("epbusiname") %>'>
				<input type=hidden name="department_<%=idx%>" id="department_<%=idx%>" value='<%=ht.get("department") %>'>
				<input type=hidden name="epregionname_<%=idx%>" id="epregionname_<%=idx%>" value='<%=ht.get("epregionname") %>'>
				<input type=hidden name="cn_<%=idx%>" id="cn_<%=idx%>" value='<%=ht.get("cn") %>'>
				<input type=hidden name="sn_<%=idx%>" id="sn_<%=idx%>" value='<%=ht.get("sn") %>'>
				<input type=hidden name="givenname_<%=idx%>" id="givenname_<%=idx%>" value='<%=ht.get("givenname") %>'>
				<input type=hidden name="description_<%=idx%>" id="description_<%=idx%>" value='<%=ht.get("description") %>'>
				<input type=hidden name="title_<%=idx%>" id="title_<%=idx%>" value='<%=ht.get("title") %>'>
				<input type=hidden name="epgradename_<%=idx%>" id="epgradename_<%=idx%>" value='<%=ht.get("epgradename") %>'>
				<input type=hidden name="epsendcompanyname_<%=idx%>" id="epsendcompanyname_<%=idx%>" value='<%=ht.get("epsendcompanyname") %>'>
				<input type=hidden name="epsendsuborgname_<%=idx%>" id="epsendsuborgname_<%=idx%>" value='<%=ht.get("epsendsuborgname") %>'>
				<input type=hidden name="epsenddeptname_<%=idx%>" id="epsenddeptname_<%=idx%>" value='<%=ht.get("epsenddeptname") %>'>
				<input type=hidden name="epsendtitlename_<%=idx%>" id="epsendtitlename_<%=idx%>" value='<%=ht.get("epsendtitlename") %>'>
				<input type=hidden name="epsendgradename_<%=idx%>" id="epsendgradename_<%=idx%>" value='<%=ht.get("epsendgradename") %>'>
				<input type=hidden name="epid_<%=idx%>" id="epid_<%=idx%>" value='<%=ht.get("epid") %>'>
				<input type=hidden name="eporganizationnumber_<%=idx%>" id="eporganizationnumber_<%=idx%>" value='<%=ht.get("eporganizationnumber") %>'>
				<input type=hidden name="epregioncode_<%=idx%>" id="epregioncode_<%=idx%>" value='<%=ht.get("epregioncode") %>'>
				<input type=hidden name="epsuborgcode_<%=idx%>" id="epsuborgcode_<%=idx%>" value='<%=ht.get("epsuborgcode") %>'>
				<input type=hidden name="eptitlenumber_<%=idx%>" id="eptitlenumber_<%=idx%>" value='<%=ht.get("eptitlenumber") %>'>
				<input type=hidden name="epuserstatus_<%=idx%>" id="epuserstatus_<%=idx%>" value='<%=ht.get("epuserstatus") %>'>
				<input type=hidden name="l_<%=idx%>" id="l_<%=idx%>" value='<%=ht.get("l") %>'>
				<input type=hidden name="mail_<%=idx%>" id="mail_<%=idx%>" value='<%=ht.get("mail") %>'>
				<input type=hidden name="mobile_<%=idx%>" id="mobile_<%=idx%>" value='<%=ht.get("mobile") %>'>
				<input type=hidden name="postaladdress_<%=idx%>" id="postaladdress_<%=idx%>" value='<%=ht.get("postaladdress") %>'>
				<input type=hidden name="postalcode_<%=idx%>" id="postalcode_<%=idx%>" value='<%=ht.get("postalcode") %>'>
				<input type=hidden name="telephonenumber_<%=idx%>" id="telephonenumber_<%=idx%>" value='<%=ht.get("telephonenumber") %>'>
				<input type=hidden name="userid_<%=idx%>" id="userid_<%=idx%>" value='<%=ht.get("userid") %>'>
				<input type=hidden name="eptitlesortorder_<%=idx%>" id="eptitlesortorder_<%=idx%>" value='<%=ht.get("eptitlesortorder") %>'>
				<input type=hidden name="epsendtitlesortorder_<%=idx%>" id="epsendtitlesortorder_<%=idx%>" value='<%=ht.get("epsendtitlesortorder") %>'>
				<input type=hidden name="c_<%=idx%>" id="c_<%=idx%>" value='<%=ht.get("c") %>'>
				<input type=hidden name="dn_<%=idx%>" id="dn_<%=idx%>" value='<%=ht.get("dn") %>'>
				<input type=hidden name="epdefaultcompcode_<%=idx%>" id="epdefaultcompcode_<%=idx%>" value='<%=ht.get("epdefaultcompcode") %>'>
				<input type=hidden name="epgradeortitle_<%=idx%>" id="epgradeortitle_<%=idx%>" value='<%=ht.get("epgradeortitle") %>'>
				<input type=hidden name="episblue_<%=idx%>" id="episblue_<%=idx%>" value='<%=ht.get("episblue") %>'>
				<input type=hidden name="eppreferredlanguage_<%=idx%>" id="eppreferredlanguage_<%=idx%>" value='<%=ht.get("eppreferredlanguage") %>'>
				<input type=hidden name="epsecuritylevel_<%=idx%>" id="epsecuritylevel_<%=idx%>" value='<%=ht.get("epsecuritylevel") %>'>
				<input type=hidden name="epsendbusicode_<%=idx%>" id="epsendbusicode_<%=idx%>" value='<%=ht.get("epsendbusicode") %>'>
				<input type=hidden name="epsendcompanycode_<%=idx%>" id="epsendcompanycode_<%=idx%>" value='<%=ht.get("epsendcompanycode") %>'>
				<input type=hidden name="epsenddeptcode_<%=idx%>" id="epsenddeptcode_<%=idx%>" value='<%=ht.get("epsenddeptcode") %>'>
				<input type=hidden name="epsendgradeortitle_<%=idx%>" id="epsendgradeortitle_<%=idx%>" value='<%=ht.get("epsendgradeortitle") %>'>
				<input type=hidden name="epsendregioncode_<%=idx%>" id="epsendregioncode_<%=idx%>" value='<%=ht.get("epsendregioncode") %>'>
				<input type=hidden name="epsendsecuritylevel_<%=idx%>" id="epsendsecuritylevel_<%=idx%>" value='<%=ht.get("epsendsecuritylevel") %>'>
				<input type=hidden name="epsendsuborgcode_<%=idx%>" id="epsendsuborgcode_<%=idx%>" value='<%=ht.get("epsendsuborgcode") %>'>
				<input type=hidden name="epsendtitlenumber_<%=idx%>" id="epsendtitlenumber_<%=idx%>" value='<%=ht.get("epsendtitlenumber") %>'>
				<input type=hidden name="epuserlevel_<%=idx%>" id="epuserlevel_<%=idx%>" value='<%=ht.get("epuserlevel") %>'>
				<input type=hidden name="facsimiletelephonenumber_<%=idx%>" id="facsimiletelephonenumber_<%=idx%>" value='<%=ht.get("facsimiletelephonenumber") %>'>
				<input type=hidden name="nickname_<%=idx%>" id="nickname_<%=idx%>" value='<%=ht.get("nickname") %>'>
				<input type=hidden name="preferredlanguage_<%=idx%>" id="preferredlanguage_<%=idx%>" value='<%=ht.get("preferredlanguage") %>'>
				<input type=hidden name="epvoipnumber_<%=idx%>" id="epvoipnumber_<%=idx%>" value='<%=ht.get("epvoipnumber") %>'>
				<input type=hidden name="mailHost_<%=idx%>" id="mailHost_<%=idx%>" value='<%=ht.get("mailHost") %>'>
				<input type=hidden name="epindeptcode_<%=idx%>" id="epindeptcode_<%=idx%>" value='<%=ht.get("epindeptcode") %>'>
				<input type=hidden name="epjob_<%=idx%>" id="epjob_<%=idx%>" value='<%=ht.get("epjob") %>'>
				<input type=hidden name="epjobname_<%=idx%>" id="epjobname_<%=idx%>" value='<%=ht.get("epjobname") %>'>
				<input type=hidden name="epindeptcodename_<%=idx%>" id="epindeptcodename_<%=idx%>" value='<%=ht.get("epindeptcodename") %>'>
				<input type=hidden name="epwithdrawdate_<%=idx%>" id="epwithdrawdate_<%=idx%>" value='<%=ht.get("epwithdrawdate") %>'>
				<input type=hidden name="epnative_<%=idx%>" id="epnative_<%=idx%>" value='<%=ht.get("epnative") %>'>
				<input type=hidden name="epuserclassify_<%=idx%>" id="epuserclassify_<%=idx%>" value='<%=ht.get("epuserclassify") %>'>
				<input type=hidden name="serverlocation_<%=idx%>" id="serverlocation_<%=idx%>" value='<%=ht.get("serverlocation") %>'>
				</div>
			</tr>
	<%
	    }
	}
	%>
		</table>
		</div>
        <!-- //List -->
		</form:form>
  </div>
</div>



<!--footer -->
<div class="pop_footer">
	<div class="btn_wrap tR" >
		<span class="btn_all_w"><span class="cancel"></span><a href="#" onclick="javascript:self.close();"><spring:message code="secfw.page.button.close" /></a></span>
	</div>
</div>
<!-- //footer -->

</body>
</html>