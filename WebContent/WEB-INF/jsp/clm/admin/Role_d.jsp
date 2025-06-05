﻿<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%--
/**
 * 파  일  명 : Role_d.jsp
 * 프로그램명 : 결재라인 Role - 상세
 * 설      명 : 
 * 작  성  자 : 박정훈
 * 작  성  일 : 2014.05.19
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%@ include file="/WEB-INF/jsp/common/messageUtil.jsp" %>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta charset="utf-8" />
<meta http-equiv="x-ua-compatible" content="IE=edge" >
<title><spring:message code="las.main.title" /></title>
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>


<script language="javascript">

var returnCdNm = "";

$(document).ready(function(){
	var frm = document.frm;
	
	

});


	
	
	function goList() { 
		var frm = document.frm;

		frm.method.value = "listRole";
		frm.action = "<c:url value='/clm/admin/role.do' />";
		frm.target = "_self";
		viewHiddenProgress(true);
		frm.submit();
	}
	
	function mod() {
		var frm = document.frm;
		
		    	frm.method.value = "forwardModifyRole";
				frm.action = "<c:url value='/clm/admin/role.do' />";
				frm.target = "_self";
				viewHiddenProgress(true);
				frm.submit();
		
	}
	


	/**
	* 임직원 조회 function
	*/	
	function searchUser() {

		var frm = document.frm;

		var srchValue = comTrim(frm.sign_nm.value);

		if(srchValue == '' || getByteLength(srchValue) < 4) {
			alert('<spring:message code='secfw.msg.error.nameMinByte' />');
			frm.sign_nm.focus();
			return ;
		}

		PopUpWindowOpen('', 850, 450, false);
		frm.target = "PopUpWindow";

		frm.srch_user_cntnt_type.value = 'userName';
		frm.srch_user_cntnt.value = srchValue;
		
		frm.action = "<c:url value='/secfw/esbOrg.do' />";
		frm.method.value = "listEsbEmployee";

		frm.submit();
		
	}
	
	

	/**
	* 임직원정보 Setting
	*/	
	function ignoreNull(str,dstr){
		if( str == null || str == "")
			return dstr;
		return str;
	}
	
	function setUserInfos(obj) {
		
		var name     = ignoreNull(obj.cn,"-");
		var type;
		var userId   = ignoreNull(obj.epid,"-");
		var userNm   = ignoreNull(obj.cn,"-"); 
		var jikgupCd = ignoreNull(obj.eptitlenumber,"-");
		var jikgupNm = ignoreNull(obj.title,"-");
		var deptCd   = ignoreNull(obj.departmentnumber,"-");
		var deptNm   = ignoreNull(obj.department,"-");
		var compCd   = ignoreNull(obj.eporganizationnumber,"-");
		var compNm   = ignoreNull(obj.o,"-");
		var grpCd    = ignoreNull(obj.epsuborgcode,"-");
		var grpNm 	 = ignoreNull(obj.epsuborgname,"-");
		var email    = ignoreNull(obj.mail,"-");
		var typeNm;
		
		$("#sign_nm").val(name+"/"+jikgupNm+"/"+ deptNm);
		$("#user_id").val(userId);
		
			
		//var sd_no = Number($("[id^='sign_tr']").length);
		//var co_no = sd_no - 2;
		//var ss = "";
			
		}
	
	function add() {
		
		var sd_no = Number($("[id^='sign_tr']").length);
			//console.log($("#delChk").last().val());
		var co_no = sd_no + 1;
		
		console.log($("#delChk").val());

		
		$("#trRelationContract").append("<tr id='sign_tr"+co_no+"'><td class='tC' id='td_val1'>"+co_no+"</td><td><input type='text' name='sign_nm' id='sign_nm' style='width:60%'"
				+"class='text_search' onkeydown='if(event.keyCode==13){event.returnValue = false;javascript:searchUser();}'/>"
				+"<a href='javascript:searchUser();'><img src='<%=IMAGE%>/icon/ico_search.gif' class='cp' alt='search' /></a></td>"
				+"<td class='tC'><input type='checkbox' name='delChk' id='delChk' value='"+co_no+"'/></td></tr>");
		}
	

	function del() {
		$("input[name=delChk]:checked").each(function() {
			var chk = $(this).val();
			$("#sign_tr"+chk).remove();
		});
		
	}
	
	
	function delInfo() {
		var frm = document.frm;
		
		//해당 Role이 ApprovalPath에 사용중이라면 삭제가 불가능하다.
	 	var options = {
		 		url:"/clm/admin/role.do?method=checkApprovalData",
		 		type: "post",
		 		dataType: "json",
		 		success: function (data) {
		 			if(data.val > 0){
		 				alert("Since the use, it cannot be deleted.");
		 				return;
		 				
		 			}else{
		 				
		 				if(confirm("<spring:message code='secfw.msg.ask.delete' />")){
		 			    	frm.method.value = "deleteRole";
		 					frm.action = "<c:url value='/clm/admin/role.do' />";
		 					frm.target = "_self";
		 					viewHiddenProgress(true);
		 					frm.submit();
		 					
		 				}
		 			}
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
		<!-- location -->
		<div class="location"><img src="<%=IMAGE %>/icon/ico_home.gif" width="13" height="11" border="0" alt="home"><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //location -->
		
		<!-- title -->
		<div class="title">
			<h3><spring:message code="secfw.page.field.role.Information" /></h3><!-- Role Information  -->
		</div>
		<!-- //title -->
		<!-- content -->
		<div id="content">
		<div id="content_in">
			<!-- title -->
			<div class="title_basic">
				<h4><spring:message code="secfw.page.field.role.Information" /></h4><!-- Role Information  -->
			</div>
			<!-- //title -->
		
			<form:form name="frm" id="frm" method="post" autocomplete="off">
			<input type="hidden" name="method"   	 		value="" />
			<input type="hidden" name="menu_id"  	 		value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="curPage"  	 		value="<c:out value='${command.curPage}'/>" />
			<input type="hidden" name="role_cd"  	 		value="<c:out value='${lom.role_cd}'/>" />

			<input type="hidden" id="user_id" name="user_id" value="" />
			<input type="hidden" id="item_cd_nms" name="item_cd_nms" value="" />
			<input type="hidden" id="item_cd_nm_abbrs" name="item_cd_nm_abbrs" value="" />
			<input type="hidden" id="item_cd_nm_engs" name="item_cd_nm_engs" value="" />
			<input type="hidden" id="item_cd_nm_abbr_engs" name="item_cd_nm_abbr_engs" value="" />
			
			<!-- 결재선검색 -->
			<input type='hidden' name='srch_user_cntnt_type' />
			<input type='hidden' name='srch_user_cntnt' />
			<input type='hidden' name='doSearch' value='Y' />
			
					
			<!-- button -->
			<div class="btnwrap">
				<span class="btn_all_w"><span class="save"></span><a href="javascript:mod()"><spring:message code='clm.page.button.modify' /></a></span>
				<span class="btn_all_w"><span class="del"></span><a href="javascript:delInfo()"><spring:message code='clm.page.button.delete' /></a></span>
				<span class="btn_all_w"><span class="list"></span><a href="javascript:goList()"><spring:message code='clm.page.button.list' /></a></span>
			</div>
			<!-- //button -->
					
			<!--  view  -->
			<table class="table-style01">
				<colgroup>
					<col width="10%"/>
					<col width="40%"/>
					<col width="10%"/>
					<col width="*"/>
				</colgroup>
				<tbody>
					<tr>
						<th><spring:message code="secfw.page.field.Role.PathName" /></th><!-- role name -->
						<td colspan="3"><c:out value='${lom.role_name}'/></td>
					</tr>
					<tr>
						<th><spring:message code="secfw.page.field.approvalPath.CreatedBy" /></th><!-- Created By -->
						<td>
							<c:out value='${lom.reg_id}'/>
						</td>
						<th>
							<spring:message code='clm.page.field.admin.type.reg_dt' />
						</th>
						<td><c:out value='${lom.reg_dt}'/></td>
					</tr>
					<tr>
						<th><spring:message code="secfw.page.field.approvalPath.Description" /></th><!-- Description -->
						<td colspan="3"><c:out value='${lom.description}'/></td>
					</tr>
					<tr>
						<!-- 지법인 선택 -->
						<th class='head'><spring:message code="las.page.filed.userLoc"/></th>
			          	<td colspan="3">
							<c:out value='${lom.comp_cd}'/>
							<input type="hidden" id="loc_gbn" name="loc_gbn" value='<c:out value='${lom.comp_cd}'/>' />
						</td>
					</tr>
					<tr class="end">
						<th><spring:message code="secfw.page.field.approvalPath.Active" /></th><!-- Active -->
						<td colspan="3"><c:out value='${lom.use_yn}'/></td>
					</tr>
				
				</tbody>
			</table>
			<!-- title -->
			<div class="title_basic">
				<h4><spring:message code="secfw.page.field.Role.AssignedUser" /></h4><!-- Assigned Users -->
			</div>		
	            <div id="div_rel_contract">
	            <table cellspacing="0" cellpadding="0" border="0" class="table-style01" id="trRelationContract">
	                <colgroup>
	                    <col width="10%" />
	                    <col width="90%" />
	                    <col/>
	                </colgroup>
	                <tr>
	                    <th class="tC"><spring:message code="secfw.page.field.approvalPath.No" /></th><!-- No -->
	                    <th class="tC"><spring:message code="secfw.page.field.Role.AssignedUser" /></th> <!-- Assigned Users -->
	                </tr>
	                <c:forEach var="item" items="${resultListUser}" varStatus="i">
	                <tr>
	                     <td class='tC'><c:out value="${i.count}"/></td>
	                     <td><c:out value="${item.user_nm}"/></td>
	                     </td>
	                </tr>
					</c:forEach>
	            </table>
            </div>
			<!-- //view  -->
			
			<!-- button -->
			<div class="btnwrap mt20">
				<span class="btn_all_w"><span class="save"></span><a href="javascript:mod()"><spring:message code='clm.page.button.modify' /></a></span>
				<span class="btn_all_w"><span class="del"></span><a href="javascript:delInfo()"><spring:message code='clm.page.button.delete' /></a></span>
				<span class="btn_all_w"><span class="list"></span><a href="javascript:goList()"><spring:message code='clm.page.button.list' /></a></span>
			</div>
			<!-- //button -->
			
			</form:form>
		</div>
		</div>
		<!-- //content -->
		
		
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
		<!-- footer -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- //footer -->
</body>
</html>