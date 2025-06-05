<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.PageUtil"%>
<%@ page import="com.sds.secframework.common.control.CommonController"%>
<%@ page import="com.sds.secframework.user.dto.UserManagerForm"%>
<%@ page import="com.sds.secframework.common.util.StringUtil"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="org.apache.commons.collections.map.ListOrderedMap"%>
<%@page import="org.springframework.web.servlet.support.RequestContext"%>
<%--
/**
 * 파     일     명 : UserMngList.jsp
 * 프로그램명       : 게시판 관리 목록
 * 설            명 : 
 * 작     성     자 : 김정곤
 * 작     성     일 : 2011.03.16
 * 수     정     자 : 이준석
 * 수     정     일 : 2013.05.03
 */
--%>
<%
    //메뉴네비게이션 스트링
			String menuNavi = (String) request.getAttribute("menuNavi");

			UserManagerForm command = ((UserManagerForm) request
					.getAttribute("command") == null
					? new UserManagerForm()
					: (UserManagerForm) request.getAttribute("command"));
			//사용자 조회목록 
			ArrayList listUser = (ArrayList) request.getAttribute("listUser");
			//권한목록콤보
			ArrayList RollList = (ArrayList) request.getAttribute("RollList");
			//소속조직코드
			ArrayList orgnzList = (ArrayList) request.getAttribute("orgnzList");
%>

<%@ include file="/WEB-INF/jsp/common/common.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="secfw.page.title.user.UserManage" /></title>
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/ui.core.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.dynatree.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript">
	$(document).ready(function() {
		getCodeSelectByUpCd("frm", "loc_gbn", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=A&combo_grp_cd=COMP2&combo_type=S&combo_del_yn=N");
	});

	function pageAction(div) {

		if (div == "search") {

			var frm = document.frm;
			frm.target = "_self";
			frm.action = "<c:url value='/secfw/userMng.do' />";
			frm.method.value = "listUserMng";
			frm.srch_auth_apnt_yn.value = "Y";
			frm.submit();
			frm.target = "_self";

		} else if (div == "delete") {
			if(confirm("<spring:message code='las.msg.ask.delete'/>")){
				var frm = document.frm;
				frm.target = "_self";
				frm.action = "<c:url value='/secfw/userMng.do' />";
				frm.method.value = "DeleteUserMng";
				frm.submit();
				frm.target = "_self";
			}
		} else if (div == "save") {
			
			if($("#email_rcv_yn").val()=="" || $("#email_rcv_yn").val()==null){
				alert('<spring:message code="secfw.page.field.user.inbox"/>'+' '+'<spring:message code="secfw.msg.ask.mandatory"/>');
				return;
			}
			else if($("#auto_rnew_yn").val()=="" || $("#auto_rnew_yn").val()==null){
				alert('<spring:message code="secfw.page.field.user.autoInfoUpdate"/>'+' '+'<spring:message code="secfw.msg.ask.mandatory"/>');
				return;
			}
			if($("#access_yn").val()=="" || $("#access_yn").val()==null){
				alert('<spring:message code="las.page.field.accessYn"/>'+' '+'<spring:message code="secfw.msg.ask.mandatory"/>');
				return;
			}
			else if($("#loc_gbn").val()=="" || $("#loc_gbn").val()==null){
				alert('<spring:message code="las.page.filed.userLoc"/>'+' '+'<spring:message code="secfw.msg.ask.mandatory"/>');
				return;
			}
			else if($("#role_cd").val()=="" || $("#role_cd").val()==null){
				alert('<spring:message code="secfw.page.field.user.role2"/>'+' '+'<spring:message code="secfw.msg.ask.mandatory"/>');
				return;
			}
			else if($("#approval_yn").val()=="" || $("#approval_yn").val()==null){
				alert('<spring:message code="secfw.page.field.user.approval_yn"/>'+' '+'<spring:message code="secfw.msg.ask.mandatory"/>');
				return;
			}
			
// 			var frm = document.frm;
// 			frm.target = "_self";
// 			frm.action = "<c:url value='/secfw/userMng.do' />";
// 			frm.method.value = "SaveUserMng";
// 			frm.submit();
// 			frm.target = "_self";

			if(confirm("<spring:message code='secfw.page.field.code.wannaSave'/>")){
				var frm = document.frm;
				
				var options = {
                    url: "<c:url value='/secfw/userMng.do?method=SaveUserMng' />",
                    type: "post",
                    success: function(responseText,returnMessage) {
                    	
                    	alert(responseText);
                    	
                    }
	            };
	            $("#frm").ajaxSubmit(options);  
			}
		}

	}

	function appendRoleCd() {
		var frm = document.frm;

		var old_show = $("#role_cd_show").text();
		var old_cd = $("#role_cd").val();

		var selObj = frm.role_cd_cho;
		var selIndex = selObj.selectedIndex;
		var value = selObj.value;
		var text = selObj[selIndex].text;

		if (old_show.indexOf(text) != -1) {
			alert(text
					+ " <spring:message code='secfw.page.field.user.alreadyAuth'/>");
			return;
		} else {
			var spdiv = ",";
			if (old_show == "")
				spdiv = "";

			$("#role_cd_show").text(old_show + " " + spdiv + " " + text);
			$("#role_cd").val(old_cd + spdiv + value);
		}
	}

	function deleteRoleCd() {
		$("#role_cd_show").text("");
		$("#role_cd").val("");
	}

	function searchEmployeeName(div, vel) {
		$("#serch_detail_div").val(div);
		$("#serch_detail_vel").val(vel);

		var totalCnt = 0;

		var options = {
			url : "<c:url value='/secfw/userMng.do?method=UserDetail' />",
			type : "post",
			dataType : "json",
			success : function(responseText, returnMessage) {
				if (responseText != null && responseText.length != 0) {
					$.each(responseText, function(entryIndex, entry) {
										//조회내역이 존제할시 	
										if (entry['result_action'] == 'ok') {
											$("#user_id").val(entry['user_id']);
											$("#user_nm").val(entry['user_nm']);
											$("#singleId").text(
													entry['single_id']);
											$("#compNm").text(entry['comp_nm_eng']);
											$("#email").text(entry['email']);
											if (entry['status'] == "B") {
												$("#status").text('<spring:message code="secfw.page.field.user.empled"/>');
											} else if (entry['status'] == "V") {
												$("#status").text('<spring:message code="secfw.page.field.user.leaveWorkTmp"/>');
											} else if (entry['status'] == "R") {
												$("#status").text('<spring:message code="secfw.page.field.user.resign"/>');
											} else if (entry['status'] == "S") {
												$("#status").text('<spring:message code="secfw.page.field.user.inqDispatch"/>');
											}
											$("#divisionNm").text(
													entry['division_nm_eng']);
											$("#deptNm").text(entry['dept_nm_eng']);
											$("#jikgupNm").text(
													entry['jikgup_nm_eng']);
											$("#jikmuNm").text(
													entry['jikmu_nm_eng']);
											$("#office_tel_no").text(
													entry['office_tel_no']);											
											$("#mobile_no").text(
													entry['mobile_no']);
											$("#role_cd").val(entry['role_cd']);
											$("#role_cd_show").text(
													entry['role_cd_show']);
											$("#email_rcv_yn").val(
													entry['email_rcv_yn']);
											$("#auto_rnew_yn").val(
													entry['auto_rnew_yn']); //조직업데이트
											$("#approval_yn").val(
													entry['approval_yn']);
											$("#access_yn").val(
													entry['access_yn']);
											$("#loc_gbn").val(
													entry['comp_cd']);
											if(entry['comp_cd']!="")
												setSubsidiary(entry['comp_cd']);
													

										} else if (entry['result_action'] == 'close') {
											alert("<spring:message code='secfw.page.field.user.noResultFromSearch'/>");
											$("#user_id").val('');
											$("#user_nm").val('');
											$("#singleId").text('');
											$("#compNm").text('');
											$("#email").text('');
											$("#status").text('');
											$("#divisionNm").text('');
											$("#deptNm").text('');
											$("#jikgupNm").text('');
											$("#jikmuNm").text('');
											$("#office_tel_no").text('');											
											$("#mobile_no").text('');
											$("#role_cd").val('');
											$("#role_cd_show").text('');
											$("#email_rcv_yn").val('');
											$("#auto_rnew_yn").val(''); //조직업데이트		
											$("#approval_yn").val('N');
											$("#access_yn").val('');
											$("#loc_gbn").val('');
										} else if (entry['result_action'] == 'popup') {
											$("#user_id").val('');
											$("#singleId").text('');
											$("#compNm").text('');
											$("#email").text('');
											$("#status").text('');
											$("#divisionNm").text('');
											$("#deptNm").text('');
											$("#jikgupNm").text('');
											$("#office_tel_no").text('');											
											$("#mobile_no").text('');
											$("#role_cd").val('');
											$("#role_cd_show").text('');
											$("#email_rcv_yn").val('');
											$("#auto_rnew_yn").val(''); //조직업데이트	
											$("#approval_yn").val('N');
											$("#access_yn").val('');
											$("#loc_gbn").val('');
											searchUser();
										}
									});
				}
			}
		};
		$("#frm").ajaxSubmit(options);

	}
	
	function init(Msg) {
		if (Msg != "") {
			alert(Msg);
		}
		//화면상세 내역 있을 경우 로드 
		if ("<%=StringUtil.bvl(command.getUser_id(), "")%>" != ""){
			searchEmployeeName('user_id','<%=command.getUser_id()%>');
		}

	}

	function searchEmployeeNameDetail(div, val) {
		if (rtrim(val) == "") {
			alert("<spring:message code='secfw.page.field.user.inputNm'/>");
			document.getElementById(div).focus();
			return;
		}

		searchEmployeeName(div, val);
	}

	/**
	 * 임직원 조회 function
	 */
	function searchUser() {

		var frm = document.frm;
		var srchValue = (frm.user_nm.value);

		PopUpWindowOpen('', 800, 450, false);
		frm.target = "PopUpWindow";

		frm.srch_user_cntnt_type.value = 'userName';
		frm.srch_user_cntnt.value = srchValue;

		frm.action = "<c:url value='/secfw/esbOrg.do' />";
		frm.method.value = "listEsbEmployee";

		frm.submit();

	};

	/**
	 * 임직원정보 Setting
	 */
	function setUserInfos(obj) {

		var name = obj.cn;
		var type;
		var jikgupCd = obj.eptitlenumber;
		var jikgupNm = obj.title;
		var deptNm = obj.department;
		var compNm = obj.o;
		var email = obj.mail;
		var typeNm;
		var user_id = obj.epid;
		searchEmployeeName('user_id', user_id);

	}

	function disabledEmail(roleCd) {
		roleCd = roleCd == null ? document.getElementById("role_cd_cho").value
				: roleCd;
		if (roleCd == "ADMIN") {
			disabledYn("email_rcv_yn", "Y");
		} else {
			disabledYn("email_rcv_yn", "N");
		}
	}
	
	function selectOptions(select){

		var frm = document.frm;
		
		var cdNm;
		cdNm = '<spring:message code="las.page.field.userLocGbn"/>'+" : "+select.options[select.selectedIndex].value;
		
		
		$("#comp_nm").val(select.options[select.selectedIndex].text);
		$("#cd_nm").text(cdNm);
		
		var nationCd;
		nationCd = ", "+'<spring:message code="las.page.field.nationCd"/>'+" : "+select.options[select.selectedIndex].title;
		
		$("#nation_cd").text(nationCd);
		
	}
	
	function setSubsidiary(value){
		
		var cdNm;
// 		cdNm = '<spring:message code="las.page.field.userLocGbn"/>'+" : "+frm.loc_gbn.options[value].value;
				
		cdNm = '<spring:message code="las.page.field.userLocGbn"/>'+" : "+$("#loc_gbn > option[value = "+ value + "]").attr("value");

		
		$("#comp_nm").val($("#loc_gbn > option[value = "+ value + "]").text());
		$("#cd_nm").text(cdNm);
		
		var nationCd;
// 		nationCd = ", "+'<spring:message code="las.page.field.nationCd"/>'+" : "+frm.loc_gbn.options[value].title;
		nationCd = ", "+'<spring:message code="las.page.field.nationCd"/>'+" : "+$("#loc_gbn > option[value = " + value +"]").attr("title");
		
		$("#nation_cd").text(nationCd);
		
	}
</script>

</head>
<body onLoad="init('<%=command.getReturn_message()%>')">
	<div id="wrap">
		<div id="container">

			<!-- Location -->
			<div class="location">
				<IMG SRC="<%=IMAGE%>/icon/ico_home.gif" BORDER="0" alt="Home" title="Home"><%=menuNavi%></div>
			<!-- //Location -->
			<!-- Title -->
			<div class="title">
				<h3>
					<spring:message code="secfw.page.title.user.UserManage" />
				</h3>
			</div>
			<!-- //Title -->

			<!-- content -->
			<div id="content">
				<div id="content_in">
					<div class="newstyle-area">

						<form:form id="frm" name="frm" method="post" autocomplete="off">
							<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>">
							<!-- 페이지 공통 -->
							<input type="hidden" name="method" value="">
							<input type="hidden" name="curPage" value="">
							<input type="hidden" id="blngt_orgnz" name="blngt_orgnz" value="A00000001">
							<!-- 페이지별  -->
							<input type="hidden" name="board_id">
							<!-- Detail 조회키  -->
							<input type="hidden" id="serch_detail_div" name="serch_detail_div" value="">
							<input type="hidden" id="serch_detail_vel" name="serch_detail_vel" value="">
							<input type="hidden" id="srch_user_cntnt_type" name="srch_user_cntnt_type" value="">
							<input type="hidden" id="srch_user_cntnt" name="srch_user_cntnt" value="">
							<input type="hidden" id="srch_auth_apnt_yn" name="srch_auth_apnt_yn" value="">


							<!-- search-->
				            <div class="search_box">
								<div class="search_box_inner">
									<table class="search_tb">
										<colgroup>
											<col width="*"/>
											<col width="78px"/>
										</colgroup>
										<tr>
											<td>
												
												<table class="search_form">
													<colgroup>
														<col width='10%'/>
														<col width='40%'/>
														<col width='10%'/>
														<col width='40%'/>
													</colgroup>
			
													<tr>
														<th><spring:message code="secfw.page.field.bbs.classification" /></th>
														<td>
															<select name='srch_cntnt_type'>
																<option value='user_nm' <%=("user_nm".equals(command.getSrch_cntnt_type())) ? " selected " : ""%>>
																	<spring:message code="secfw.page.field.user.nm" />
																</option>
																<option value='single_id' <%=("single_id".equals(command.getSrch_cntnt_type())) ? " selected " : ""%>>Single ID</option>
															</select>
															<input class="text w_50" type="text" name='srch_cntnt' id='srch_cntnt' 
															value="<%=StringUtil.bvl(command.getSrch_cntnt(), "")%>" maxlength='24' onKeyPress='if(event.keyCode==13) {pageAction("search");return false;}' />
														</td>
														<th><spring:message code="secfw.page.field.user.role2" /></th>
														<td>
															<select name='srch_role_cd' id='srch_role_cd'>
																	<option value='%'>
																		<spring:message code="secfw.page.field.user.all" />
																	</option>
																	<%
																	    for (int idx = 0; idx < RollList.size(); idx++) {
																						ListOrderedMap lom = (ListOrderedMap) RollList.get(idx);
																	%>
																	<option value='<%=lom.get("role_cd")%>'><%=lom.get("role_nm")%></option>
																	<%
																	    }
																	%>
															</select>
														</td>
														
													</tr>
												</table>
									
											</td>
											<td class='tC'><a href="javascript:pageAction('search');"><img src="../../images/las/ko/btn/btn_search.gif" alt="Search" title="Search" /></a></td>
										</tr>
									</table>
								</div>
				            </div>     
							<!-- search-->	
							
							
							
                    
								
						
							<div class='tableWrap mt20'>
							<div class='tableone'>
								<table class="list_basic">
									<colgroup>
										<col width="40%"/>
										<col width="30%"/>
										<col width="30%"/>
									</colgroup>
									<tr id="UserListTitle">
										<th><spring:message code="secfw.page.field.user.nm" /></th>
										<th>SingleID</th>
										<th class='end'><spring:message code="secfw.page.field.user.mail" /></th>
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
									<col width="40%"/>
									<col width="30%"/>
									<col width="30%"/>
								</colgroup>
									<%
									    if (listUser == null || listUser.size() == 0) {
									%>
									<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
										<td colspan="3" align="center" class='end'>No Data Found.</td>
									</tr>
									<%
									    } else {

														for (int idx = 0; idx < listUser.size(); idx++) {
															ListOrderedMap lom = (ListOrderedMap) listUser.get(idx);
									%>
									<tr>
										<td id='user_info_<%=idx%>' title='<%=lom.get("user_info")%>'><a href="JavaScript:searchEmployeeName('user_id','<%=lom.get("user_id")%>');" /><%=lom.get("user_info")%></td>
										<td class='tC' id='single_id_<%=idx%>' title='<%=lom.get("single_id")%>'><a href="JavaScript:searchEmployeeName('user_id','<%=lom.get("user_id")%>');" /><%=lom.get("single_id")%></td>
										<td class='end' id='email_<%=idx%>' title='<%=lom.get("email")%>'><a href="JavaScript:searchEmployeeName('user_id','<%=lom.get("user_id")%>');" /><%=lom.get("email")%></td>
										<div style='display:none'>
										<input type="hidden" id="user_id_<%=idx%>" name="user_id_<%=idx%>" value="<%=lom.get("user_id")%>">
										</div>
									</tr>
									<%
									    }
													}
									%>
								</table>
							</div>	
							</div>
							
							<div class="total_num">
									
										Total : 
										<%
										  if (listUser == null || listUser.size() == 0) {
										%>
										  0 
										<%
										  } else {
										%> 
										<%=listUser.size()%> 
										<%
										  }
										%> 
							</div>

							<table class="list_basic mt20">
								<colgroup>
									<col width="15%" />
									<col width="35%" />
									<col width="15%" />
									<col width="35%" />
								</colgroup>
								<tr>
									<th class='head'><spring:message code="secfw.page.field.user.nm" /></th>
									<td>
										<input class='text_search' style="width : 30%" type='text' name='user_nm' id='user_nm'  onKeyPress="if(event.keyCode==13) {searchEmployeeNameDetail('user_nm',this.value);return false; }"><img src='../../images/las/ko/icon/ico_search.gif'  onclick="JavaScript:searchEmployeeNameDetail('user_nm',frm.user_nm.value)" class='cp' alt="Search" title="Search" /><span id="userNm"></span>
									</td>
									<th class='head'>Single ID</th>
									<td><span id="singleId"></span></td>
								</tr>
								<tr>
									<th class='head'><spring:message code="secfw.page.field.user.company" /></th>
									<td><span id="compNm"></span></td>
									<th class='head'><spring:message code="secfw.page.field.user.email" /></th>
									<td><span id="email"></span></td>
								</tr>
								<tr>
									<th class='head'><spring:message code="secfw.page.field.user.empStatus" /></th>
									<td><span id="status"></span></td>
									<th class='head'><spring:message code="las.page.field.usermng.companyaddress" /></th>
									<td><span id="divisionNm"></span></td>
								</tr>
								<tr>
									<th class='head'><spring:message code="secfw.page.field.user.department" /></th>
									<td><span id="deptNm"></span></td>
									<th class='head'><spring:message code="secfw.page.field.user.position" /></th>
									<td><span id="jikgupNm"></span></td>
								</tr>
								<tr>
									<th class='head'><spring:message code="las.page.field.usermng.workno" /></th>
									<td><span id="office_tel_no"></span></td>
<%-- 									<th class='head'><spring:message code="secfw.page.field.user.homePhoneNo" /></th> --%>
<!-- 									<td><span id="home_tel_no"></span></td> -->
									<th class='head'><spring:message code="las.page.field.usermng.mobileno" /></th>
									<td><span id="mobile_no"></span></td>

								</tr>
								<tr>
									<th class='head'><spring:message code="secfw.page.field.user.job" /></th>
									<td><span id="jikmuNm"></span></td>
									<th class='head'><spring:message code="secfw.page.field.user.inbox" /><span class="astro">*</span></th>
									<td><select name='email_rcv_yn' id='email_rcv_yn'>
											<option value='Y'>Y
											</option>
											<option value='N'>N
											</option>
									</select>
									</td>

								</tr>
								<tr>
									<th class='head'><spring:message code="las.page.field.accessYn"/><span class="astro">*</span></th>
						          	<td>
										<select name='access_yn' id='access_yn'>
											<option value=''><spring:message code="las.page.field.contractmanager.consideration_d.option_none"/></option>
											<option value='Y'><spring:message code="las.page.field.accessY" /></option>
											<option value='N'><spring:message code="las.page.field.accessN" /></option>
											<option value='P'><spring:message code="las.page.field.nowAccess" /></option>
										</select>
									</td>
									<th class='head'><spring:message code="secfw.page.field.user.autoInfoUpdate" /><span class="astro">*</span></th>
									<td><select name='auto_rnew_yn' id='auto_rnew_yn'>
											<option value='Y'>Y</option>
											<option value='N'>N</option>
									</select>
									</td>
								</tr>
								<tr>
									<!-- 지법인 선택 -->
									<th class='head'><spring:message code="las.page.filed.userLoc"/><span class="astro">*</span></th>
						          	<td id="locTd">
										<select name="loc_gbn" id="loc_gbn" onChange="javascript:selectOptions(this)">
										</select><br>					
										<span id="cd_nm"></span>					
										<span id="nation_cd"></span>			
										<input type="hidden" name="comp_nm" id="comp_nm" value=""/>
									</td>
									<th class='head'><spring:message code="secfw.page.field.user.approval_yn" /><span class="astro">*</span></th>
									<td><select name='approval_yn' id='approval_yn'>
											<option value='Y'>Y</option>
											<option value='N' selected>N</option>
									</select>
									</td>
								</tr>
								<tr>
									<th class='head'><spring:message code="secfw.page.field.user.role2" /><span class="astro">*</span></th>
									<td colspan='3'>
										<select name='role_cd_cho' id='role_cd_cho' style='width: 150'>
												<%
												    for (int idx = 0; idx < RollList.size(); idx++) {
																	ListOrderedMap lom = (ListOrderedMap) RollList.get(idx);
																	if(!"RM00".equals((String)lom.get("role_cd"))&&!"RD01".equals((String)lom.get("role_cd"))){
												%>
												<option value='<%=lom.get("role_cd")%>'><%=lom.get("role_nm")%></option>
												<%
																	}
												    }
												%>
										</select>
									
										<span class="btn_all_b"  id="btn_addSelect" onclick='JavaScript:appendRoleCd()'><span class="check"></span><a><spring:message code="secfw.page.field.user.addSelection" /></a></span>
										<span class="btn_all_b"  id="btn_delSelect" onclick='JavaScript:deleteRoleCd()'><span class="refresh"></span><a><spring:message code="secfw.page.field.user.init" /></a></span>
										
									</td>
									
								</tr>
								<tr>
									<td colspan='4'><input type="hidden" name='role_cd' id='role_cd'> <span name='role_cd_show' id='role_cd_show' /></td>
								</tr>
								<input type="hidden" id="user_id" name="user_id" value="">

							</table>
							<!-- write in end -->
							<div class="btnwrap mt20">
								<span class="btn_all_w" onclick="javascript:pageAction('save')"><span class="save"></span><a><spring:message code="secfw.page.field.user.save" /></a></span>
								<span class="btn_all_w" onclick="javascript:pageAction('delete')"><span class="delete"></span><a><spring:message code="secfw.page.field.user.delete" /></a></span>
							</div>

						</form:form>
					</div>
				</div>
			</div>
			<!-- //Content -->
		</div>
	</div>
	<!-- footer -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
	<!-- footer -->
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp" />
</body>

</html>