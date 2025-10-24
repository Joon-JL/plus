<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.control.CommonController" %>
<%@ page import="com.sds.secframework.user.dto.UserManagerForm" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@page  import="java.util.Locale"%>
<%@page  import="java.util.ArrayList" %>
<%@page  import="java.math.BigDecimal" %>
<%@page  import="java.net.URLEncoder" %>
<%@page  import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@page  import="org.springframework.web.servlet.support.RequestContext"%>

<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("menuNavi");

    UserManagerForm command  = ((UserManagerForm)request.getAttribute("command") == null ? new UserManagerForm() : (UserManagerForm)request.getAttribute("command"));	
    //사용자 조회목록 
	ArrayList 		listUser = 	(ArrayList)request.getAttribute("listUser");
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="secfw.page.title.auth.AuthList" /></title>
<link href="<%=CSS%>/las.css"    type="text/css" rel="stylesheet">
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/shri/shri.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/ui.core.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.dynatree.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript">
$(document).ready(function(){	
	//$("#userNm").text('');
	//$("#deptNm").val('');
	getCodeSelectByUpCd("frm", "loc_gbn", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=COMP2&combo_type=S&combo_del_yn=N");
});

function selectOptions(select){
	var frm = document.frm;
	
	
	
	var cdNm;
	cdNm = '<spring:message code="las.page.field.userLocGbn"/>'+" : "+select.options[select.selectedIndex].value;
	
// 	var sp1 = document.getElementById("cd_nm");
// 	sp1.innerHTML =cdNm;
	
	$("#comp_nm").val(select.options[select.selectedIndex].text);
	$("#cd_nm").text(cdNm);
	
	var nationCd;
	nationCd = ", "+'<spring:message code="las.page.field.nationCd"/>'+" : "+select.options[select.selectedIndex].title;
	
	
	$("#nation_cd").text(nationCd);
	
// 	var sp2 = document.getElementById("nation_cd");
// 	sp2.innerHTML ="asd";
	
// 	if (gbnValue=="F"){
// 		frm.trgt_nation.style.visibility = "visible";
// 	} else {
// 		frm.trgt_nation.style.visibility = "hidden";
// 	}
}

function init(Msg){
	if(Msg!=""){
		alert(Msg);
	}
	//화면상세 내역 있을 경우 로드 
	if("<%=StringUtil.bvl(command.getUser_id(),"")%>" != ""){
		
		$("#userNm").text('<%=StringUtil.bvl(command.getUser_nm(),"")%>');
		$("#user_id").val('<%=StringUtil.bvl(command.getUser_id(),"")%>');
		$("#user_nm").val('<%=StringUtil.bvl(command.getUser_nm(),"")%>');
		$("#ara_nm").text('<%=StringUtil.bvl(command.getArea_nm(),"")%>');
		$("#deptNm").text('<%=StringUtil.bvl(command.getDept_nm(),"")%>');
		$("#dept_cd").val('<%=StringUtil.bvl(command.getDept_cd(),"")%>');
		$("#dept_nm").val('<%=StringUtil.bvl(command.getDept_nm(),"")%>');
		
		$("#access_yn").val('<%=StringUtil.bvl(command.getAccess_yn(),"")%>');
		
		var tmp = '<%=StringUtil.bvl(command.getAccess_yn(),"")%>';
		
		if(tmp != ''){
			$("#auth_apnt_dept_nm").val('<%=StringUtil.bvl(command.getAuth_apnt_dept_nm(),"")%>');
			$("#auth_apnt_dept").val('<%=StringUtil.bvl(command.getAuth_apnt_dept(),"")%>');
			if(tmp == "Y"){
				$('#picker').attr("style", "cursor:hand;");
			}else{
				$('#picker').attr("style", "cursor:hand;display:none");
			}
		}else{
			$("#auth_apnt_dept_nm").val('');
			$("#auth_apnt_dept").val('');
			$('#picker').attr("style", "cursor:hand;display:none");	
		}
	}
}

function pageAction(div){

	if(div == "search"){		
		var frm = document.frm;
		frm.target		 = "_self";
		frm.action       = "<c:url value='/secfw/userMng.do' />";
		frm.method.value = "listUserAccess";
		frm.submit();
		frm.target = "_self";
		
	}else if(div == "save"){
		var frm = document.frm;
		
		if(frm.user_id.value == ""){
			alert("<spring:message code='secfw.page.field.user.selectUser'/>");
			return;			
		}
		
		if($("#access_yn").val() ==""){
			alert('<spring:message code="las.msg.selectAccess" />');
			return;
		}
		
		if($("#loc_gbn").val() ==""){
			alert('<spring:message code="las.msg.selectLoc" />');
			return;
		}
		
		
		
		
		frm.target		 = "_self";
		frm.action       = "<c:url value='/secfw/userMng.do' />";
		frm.method.value = "SaveUserAccess";
		frm.submit();
		frm.target = "_self";
	}
}

function setInfo(val){
	var frm = document.frm;
	var tr_cnt = $("#table_tbody tr").length;
	
	if(tr_cnt ==1 && val == 0){
		$("#userNm").text(frm.user_nm_arr.value);
		$("#user_id").val(frm.user_id_arr.value);
		$("#user_nm").val(frm.user_nm_arr.value);
		
		$("#deptNm").text(frm.dept_nm_arr.value);
		$("#dept_cd").val(frm.dept_cd_arr.value);
		$("#dept_nm").val(frm.dept_nm_arr.value);
		
		$("#ara_nm").val(frm.ara_nm_arr.value);
		$("#comments").val(frm.comments_arr.value);
		$("#comp_cd").val(frm.comp_cd_arr.value);
		
		$("#access_yn").val(frm.access_yn_arr.value);
		
		if(frm.access_yn_arr.value != ''){
			
			$("#auth_apnt_dept_nm").val(frm.auth_apnt_dept_nm_arr.value);
			$("#auth_apnt_dept").val(frm.auth_apnt_dept_arr.value);
			if(frm.access_yn_arr.value == "Y"){
				$('#picker').attr("style", "cursor:hand;");
			}else{
				$('#picker').attr("style", "cursor:hand;display:none");
			}
		}else{
			$("#auth_apnt_dept_nm").val('');
			$("#auth_apnt_dept").val('');
			$('#picker').attr("style", "cursor:hand;display:none");	
		}
	}else{
	
		$("#userNm").text(frm.user_nm_arr[val].value);
		$("#user_id").val(frm.user_id_arr[val].value);
		$("#user_nm").val(frm.user_nm_arr[val].value);
		
	
		$("#deptNm").text(frm.dept_nm_arr[val].value);
		$("#dept_cd").val(frm.dept_cd_arr[val].value);
		$("#dept_nm").val(frm.dept_nm_arr[val].value);
		$("#comp_cd").val(frm.comp_cd_arr[val].value);
		$("#ara_nm").val(frm.ara_nm_arr[val].value);
		$("#comments").val(frm.comments_arr[val].value);
		
		
		$("#access_yn").val(frm.access_yn_arr[val].value);
		
		if(frm.access_yn_arr[val].value != ''){
			
			$("#auth_apnt_dept_nm").val(frm.auth_apnt_dept_nm_arr[val].value);
			$("#auth_apnt_dept").val(frm.auth_apnt_dept_arr[val].value);
			if(frm.access_yn_arr[val].value == "Y"){
				$('#picker').attr("style", "cursor:hand;");
			}else{
				$('#picker').attr("style", "cursor:hand;display:none");
			}
		}else{
			$("#auth_apnt_dept_nm").val('');
			$("#auth_apnt_dept").val('');
			$('#picker').attr("style", "cursor:hand;display:none");	
		}
	}
	
	
}

function setAccessYn(val){
	if(val == "Y"){
		$('#picker').attr("style", "cursor:hand;");
	}else{
		$("#auth_apnt_dept_nm").val('');
		$("#auth_apnt_dept").val('');
		$('#picker').attr("style", "cursor:hand;display:none");
	}
}


/* 부서 검색 테스트 */
function listDeptTree() {
	var frm = document.frm;

    PopUpWindowOpen('', 640, 640, false);
	frm.target = "PopUpWindow";

	frm.action = "<c:url value='/common/clmsCom.do' />";
	frm.method.value = "listDeptTree";
	frm.page_gbn.value = "one";

	frm.submit();
}

function setDeptInfo(retDeptCd, retDeptNm){
	$("#auth_apnt_dept").val(retDeptCd);
	$("#auth_apnt_dept_nm").val(retDeptNm);	
}

/**
* 메시지 처리
*/
function showMessage(msg) {
	if( msg != "" && msg != null && msg.length > 1 ) {
		alert(msg);
	}
}

</script>

</head>
<body onLoad='showMessage("<c:out value='${returnMessage}'/>");'>


<div id="wrap">
	<div id="container">
		
	<!-- location -->
	<div class="location"><IMG SRC="<%=IMAGE%>/icon/ico_home.gif" BORDER="0" alt="Home" title="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
	<!-- //location -->
	
	<!-- title -->
	<div class="title">
		<h3><spring:message code="las.page.title.userAccess"/></h3>
	</div>
	<!-- //title -->		

	<div id="content">
		<div id="content_in">
			<form:form id="frm" name="frm" method="post" autocomplete="off">
			<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>">
			<!-- 페이지 공통 -->
			<input type="hidden" name="method"  value="">
			<!-- 날인담당자 화면코드 -->
<!-- 			<input type="hidden" name="gubn_cd"  value="SC0101"> -->
 			
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
									<col width="10%"/>
									<col width="*"/>
<%-- 									<col width="10%"/> --%>
<%-- 									<col width="40%"/> --%>
									</colgroup>
								<tr>
								    <th><spring:message code="las.page.field.contractManager.reqBy"/></th>
									<td>
										<select name='srch_cntnt_type'>
											<option value='user_nm' <%=("user_nm".equals(command.getSrch_cntnt_type()))?" selected ":"" %> ><spring:message code="secfw.page.field.user.nm"/></option>
											<option value='dept_nm' <%=("dept_nm".equals(command.getSrch_cntnt_type()))?" selected ":"" %> ><spring:message code="secfw.page.field.user.department"/></option>
											<option value='single_id' <%=("single_id".equals(command.getSrch_cntnt_type()))?" selected ":"" %> >Single ID</option>
										</select>	
										<input class="text w_50" type="text" name='srch_cntnt' id='srch_cntnt' value="<%=StringUtil.bvl(command.getSrch_cntnt(), "")%>" maxlength='24' onKeyPress='if(event.keyCode==13) {pageAction("search");return false;}'/>
									</td>
<!-- 									<th class='paddLeft10' align='right'>승인여부</th>											 -->
<!-- 									<td> -->
<!-- 									<select name='srch_access_yn' id='srch_access_yn'> -->
<!-- 											<option value='A'>전체</option> -->
<%-- 											<option value='Y' <%=("Y".equals(command.getSrch_access_yn()))?" selected ":"" %>>Y</option> --%>
<%-- 											<option value='N' <%=("N".equals(command.getSrch_access_yn()))?" selected ":"" %>>N</option> --%>
<%-- 											<option value='P' <%=("P".equals(command.getSrch_access_yn()))?" selected ":"" %>>P</option> --%>
<!-- 										</select>	 -->
<!-- 									</td> -->
								</tr>
							</table>
								
						</td>
				<td class='tC'><a href="javascript:pageAction('search');"><img src="../../images/las/ko/btn/btn_search.gif" alt="Search" title="Search" /></a></td>
					</tr>
				</table>
			</div>
           </div>     
		<!-- // search-->	

		
		
		
		<div class='tableWrap mt20'>
		<div class='tableone'>
			<table class="list_basic">
			<colgroup>
			<col width="40%"/>
			<col width="20%"/>
			<col width="10%"/>
			<col width="30%"/>
			</colgroup>
			<tr id="UserListTitle">
				<th><spring:message code="secfw.page.field.user.nm"/></th>
				<th>SingleID</th>
				<th><spring:message code="las.page.field.accessYn"/></th>				
				<th class='end'><spring:message code="secfw.page.field.user.mail"/></th>								
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
			<col width="20%"/>
			<col width="10%"/>
			<col width="30%"/>
			</colgroup>
			<tbody id="table_tbody">					
			<%
			 for(int idx=0;idx< listUser.size();idx++){
			    	ListOrderedMap lom = (ListOrderedMap)listUser.get(idx);
			%>
			<tr>
				<td id='user_info_<%=idx%>'		title='<%=lom.get("user_info")%>'      ><a href="JavaScript:setInfo('<%=idx%>');"/><%=lom.get("user_info")%></td>			
				<td id='single_id_<%=idx%>' 	title='<%=lom.get("single_id")%>'      align="center"><a href="JavaScript:setInfo('<%=idx%>');"/><%=lom.get("single_id")%></td>		
				<%if("P".equals(lom.get("access_yn"))){ %>
					<td  align="center" ><spring:message code="las.page.field.nowAccess" /></td>
				<%}else if("Y".equals(lom.get("access_yn"))){ %>	
					<td  align="center" ><spring:message code="las.page.field.accessY" /></td>
				<%} if("N".equals(lom.get("access_yn"))){ %>	
					<td  align="center" ><spring:message code="las.page.field.accessN" /></td>
				<%} %>
<%-- 				<td  align="center" ><%=lom.get("access_yn")%></td>			 --%>
				<td id='email_<%=idx%>' 		title='<%=lom.get("email")%>'          class='end'><a href="JavaScript:setInfo('<%=idx%>');"/><%=lom.get("email")%></td>	
				<div style='display:none'>
				<input type="hidden" name="user_id_arr" id="user_id_arr" value="<%=lom.get("user_id")%>">
				<input type="hidden" name="comp_cd_arr" id="comp_cd_arr" value="<%=lom.get("comp_cd")%>">
				<input type="hidden" name="ara_nm_arr" id="ara_nm_arr" value="<%=lom.get("ara_nm")%>">
				<input type="hidden" name="comments_arr" id="comments_arr" value="<%=lom.get("comments")%>">				
				
				<input type="hidden" name="user_nm_arr" id="user_nm_arr" value="<%=lom.get("user_nm")%>">
				<input type="hidden" name="dept_cd_arr" id="dept_cd_arr" value="<%=lom.get("dept_cd")%>">
				<input type="hidden" name="dept_nm_arr" id="dept_nm_arr" value="<%=lom.get("dept_nm")%>">
				<input type="hidden" name="access_yn_arr" id="access_yn_arr" value="<%=lom.get("access_yn")%>">
				<input type="hidden" name="auth_apnt_dept_arr" id="auth_apnt_dept_arr" value="<%=lom.get("auth_apnt_dept")%>">		
				<input type="hidden" name="auth_apnt_dept_nm_arr" id="auth_apnt_dept_nm_arr" value="<%=lom.get("auth_apnt_dept_nm")%>">		
				</div>			
			</tr>			
			<% 
			 }
			if(listUser==null||listUser.size()==0){
			%>
	          <tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
	            <td colspan="3" align="center"><spring:message code="secfw.msg.succ.noResult" /></td>
	          </tr>
			<%} %>
			</tbody>
			</table>         
		</div>
		<div class="total_num">Total : <%=listUser.size()%></div>	
 
     <table class="list_basic mt20">
	        <colgroup>
	        <col width="15%"/>
	        <col width="*"/>
	        </colgroup>
			<tr>
					<th class='head'><spring:message code="secfw.page.field.user.nm"/></th>
				<td>
					<span id="userNm"></span>
					<input type="hidden" name="user_id" id="user_id" value=""/>
					<input type="hidden" name="comp_cd" id="comp_cd" value=""/>
					<input type="hidden" name="user_nm" id="user_nm" value=""/>
				</td>
			</tr>
			<tr>						
				<th class='head'><spring:message code="secfw.page.field.user.department"/></th>
				<td>
					<span id="deptNm"></span>
					<input type="hidden" name="dept_cd" id="dept_cd" value=""/>
					<input type="hidden" name="dept_nm" id="dept_nm" value=""/>
				</td>
			</tr>
			<tr>
				<th class='head'><spring:message code="las.page.field.accessYn"/> <span class="astro">*</span></th>
	          	<td>
					<select name='access_yn' id='access_yn' onChange="setAccessYn(this.value)">
						<option value=''><spring:message code="las.page.field.contractmanager.consideration_d.option_none"/></option>
						<option value='Y'><spring:message code="las.page.field.accessY" /></option>
						<option value='N'><spring:message code="las.page.field.accessN" /></option>
						<option value='P'><spring:message code="las.page.field.nowAccess" /></option>
					</select>
					
		
				</td>
			</tr>
			<!-- 지법인 선택 -->
			<tr>
				<th class='head'><spring:message code="las.page.filed.userLoc"/> <span class="astro">*</span></th>
	          	<td id="locTd">
					<select name="loc_gbn" id="loc_gbn" onChange="javascript:selectOptions(this)">
					</select>					
					<span id="cd_nm"></span>					
					<span id="nation_cd"></span>			
					<input type="hidden" name="comp_nm" id="comp_nm" value=""/>		
				</td>
			</tr>
		</table>
		
 		<div class="btn_wrap">
			<span class="btn_all_w"><a href="javascript:pageAction('save')"><span class="save"></span><spring:message code="secfw.page.field.user.save"/></a></span>
		</div>
		
		<input type="hidden" name="page_gbn" id="page_gbn" value=""/>
		</form:form>
		
			</div>
		</div>
	</div>
</div>


<!-- footer -->
<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
<!-- footer -->
<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</body>
</html>