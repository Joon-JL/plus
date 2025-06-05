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
 * 파  일  명 : Approval_u.jsp
 * 프로그램명 : 결재 승인자 - 수정
 * 설      명 : 
 * 작  성  자 : 박정훈
 * 작  성  일 : 2014.06.03
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

$(document).ready(function(){

	var comp_cd='<c:out value="${lom.comp_cd}"/>'; 
	getCodeSelectByUpCd("frm", "loc_gbn", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=A&combo_grp_cd=COMP2&combo_type=S&combo_del_yn=N&combo_selected='+comp_cd);
	
// 	alert('<c:out value="${lom.comp_cd}"/>');
	
	$('#type_3').change(function() {
		chgajax();
	});
	
	
});

	
	function goList() { 
		var frm = document.frm;

		frm.method.value = "listApprovalPath";
		frm.action = "<c:url value='/clm/admin/approvalPath.do' />";
		frm.target = "_self";
		viewHiddenProgress(true);
		frm.submit();
	}
	
	function save() {
		var frm = document.frm;
		
		var validationUserid = "";
		var validationCval = "";
		var validationCad = "";
		
		//validation check
		$("[id^='user_id']").each(function(index) {
		  	validationUserid += $(this).val();
		});
		$("[id^='c_val']").each(function(index) {
			validationCval += $(this).val();
		});
		$("[id^='condition']").each(function(index) {
			validationCad += $(this).val()+"/";
		});
		
		if($("#path_nm").val() == "" || $("#path_nm").val() == null ){
			alert("Path Name is required");
			$("#path_nm").focus(); 
			return;
		}else if($("#priority").val() == "" || $("#priority").val() == null ){
			alert("priority is required");
			$("#priority").focus();
			return;
		}else if($(':radio[name="mandatory"]:checked').length < 1 ){
			alert("mandatory is required");
			$("#mandatory").focus();
			return;
		}else if(!$('#use_yn > option:selected').val()){
			alert("Active is required");
			$("#use_yn").focus();
			return;
		}else if(validationUserid == "" || validationUserid == null){
			alert("Approval Path user is required");
			return;
		}else if(($("#type_1").val() == "" && $("#type_2").val() == "" && $("#type_3").val() == "" && $("#type_4").val() == "") && validationCval == "" ){
			alert("Condition is required");
			return;
		}else if(validationCad != "" && validationCval == ""){
			alert("ConditionDeail is required");
			return;
		}
		
		var code1 = code2 = chklist = "";
	
	    $("[id^='user_id']").each(function(i) {
			
	        code2 = $(this).val();
	        
	        if(!code2 == ""){
	        
		        if(code1.match(code2)!= null) {
		        	alert("<spring:message code='secfw.page.field.approvalPath.duplication'/>");
		            $("#user_id"+(i+1)).focus();
		            chklist = "N";
		            return;
		        } else {
		            code1 += code2+"/";
		        }
	        }
	    });
	    
	    if(chklist == "N"){
	    	return;
	    }
	    
		    if(confirm("<spring:message code='secfw.msg.ask.insert'/>")){
				
				var result1 = "";
				var result2 = "";
				var result3 = "";
				
				var result4 = "";
				var result5 = "";
				var result6 = "";
				var result7 = "";
				
				$("[id^='condition']").each(function(index) {
				  	result1 += $(this).val()+"/";
				});
							
				$("[id^='c_option']").each(function(index) {
				  	result2 += $(this).val()+"/";
				});
				
				$("[id^='c_val']").each(function(index) {
				  	result3 += $(this).val()+"/";
				});
				
				$("[id^='user_id']").each(function(index) {
				  	result4 += $(this).val()+"/";
				});
				
				$("[id^='a_type']").each(function(index) {
				  	result5 += $(this).val()+"/";
				});
				
				$("[id^='r_type']").each(function(index) {
				  	result6 += $(this).val()+"/";
				});
				$("[id^='sort_num']").each(function(index) {
				  	result7 += $(this).val()+"/";
				});
				
				frm.input_condi_list.value = result1;
				frm.input_condi_option.value = result2;
				frm.input_condi_val.value = result3;
				frm.ass_id_list.value = result4;
				frm.atype_val.value = result5;
				frm.rtype_val.value = result6;
				
				frm.sort_no.value = result7;
				
		    	frm.method.value = "modifyApprovalPath";
				frm.action = "<c:url value='/clm/admin/approvalPath.do' />";
				frm.target = "_self";
				viewHiddenProgress(true);
				frm.submit();
				
			}
		
	}
	


	/**
	* 임직원 조회 function
	*/	
	function searchUser(no) {

		var frm = document.frm;
		
		$("#src_no").val(no);
		
		var srchValue = comTrim($("#sign_nm"+no).val());
		
		if(srchValue == '' || getByteLength(srchValue) < 4) {
			alert('<spring:message code='secfw.msg.error.nameMinByte' />');
			$("#sign_nm"+no).focus();
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
		
		var s_no = $("#src_no").val();
		
		$("#sign_nm"+s_no).val(name+"/"+jikgupNm+"/"+ deptNm);
		$("#user_id"+s_no).val(userId);
	
	
	}

		
/************** Condition  SCRIPT START************************************/
	//contract type 유형
	 function chgajax() {
			
		 	var options = {
		 		url:"/clm/admin/approvalPath.do?method=checkContract",
		 		type: "post",
		 		dataType: "json",
		 		success: function (data ) {

		 			$('select#type_4 > option').remove();
				    $('select#type_4').append(data.olist);
					
		 		}
		 	};
		 	$("#frm").ajaxSubmit(options);
		 }

	 
	 //condition detail 변경스크립트
	 function chgDetail(no){

		 if($("#condition"+no+" option:selected").val() == "C001"){
			 $("#opt_sp"+no).text("");
			 $("#opt_sp"+no).append("<select name='c_option"+no+"' id='c_option"+no+"'  style='width:57px;'>"
				+"<option value='1'>></option>"
				+"<option value='2'>=</option>"
				+"<option value='3'><</option>"
				+"</select>&nbsp;"
				+"<input type='text' name='c_val"+no+"' id='c_val"+no+"' class='text' value='' size='10' maxLength='18' alt='<spring:message code='clm.page.msg.manage.amount' htmlEscape='true' />' onkeyup='olnyNum(this)' style='IME-MODE: disabled; height:19px;' required/>&nbsp;");
		 
		 }else  if($("#condition"+no+" option:selected").val() == "C002"){
			 $("#opt_sp"+no).text("");
			 $("#opt_sp"+no).append("<select name='c_val"+no+"' id='c_val"+no+"'  style='width:57px;'>"
				+"<option value='Y'>Y</option>"
				+"<option value='N'>N</option>"
				+"</select>"
				+"<input type='hidden' id='c_option"+no+"' name='c_option"+no+"' value='2' />");
		 }else  if($("#condition"+no+" option:selected").val() == "C003"){
			 $("#opt_sp"+no).text("");
			 $("#opt_sp"+no).append("<select name='c_val"+no+"' id='c_val"+no+"'  style='width:57px;'>"
						+"<option value='Y'>Y</option>"
						+"<option value='N'>N</option>"
						+"</select>"
						+"<input type='hidden' id='c_option"+no+"' name='c_option"+no+"' value='2' />");
		 }else if($("#condition"+no+" option:selected").val() == ""){
			 $("#opt_sp"+no).text("");
		 }

	 }
	 
	 //condition detail row 삭제
	 function deleteInc(no){
			
			$("#codi_s"+no).remove();
		}
	 
	//Condition Detail row 추가
	function addrow() {
			
			
			if(Number($("#cod_no").val()) == 1){
					$("#cod_no").val(2);
				}else{
					$("#cod_no").val(Number($("#cod_no").val())+1);
				};
		
				var co_no = $("#cod_no").val();

				
				$("#codi_ss").append("<span id='codi_s"+co_no+"'><select name='condition"+co_no+"' id='condition"+co_no+"'  style='width:175px;' onchange='javascript:chgDetail("+co_no+");' >"
					+"<option value=''>--select--</option>"
					+"<option value='C001'><spring:message code='clm.page.msg.manage.amount' htmlEscape='true' /></option>"
					+"<option value='C002'>Automatic Extension</option>"
					+"<option value='C003'>Mandatory provisions</option>"
					+"</select>&nbsp;<span id='opt_sp"+co_no+"'></span>"
					+"<img src='/images/las/en/icon/ico_delete_set.gif' onclick='javascript:deleteInc("+co_no+");' class='cp' alt='delete' readonly/></br></span>");
				
			
		}
/************** Condition  SCRIPT START************************************/



/************** Approval Path List SCRIPT START************************************/
 
	 //Approval Path List (role/user 선택에 따라 변경되는 스크립트)
	 function chgApp(no){
		 if($("#r_type"+no+" option:selected").val() == "r"){
			 
			 var options = {
				 		url:"/clm/admin/approvalPath.do?method=checkRolelist",
				 		type: "post",
				 		dataType: "json",
				 		data: {comp_cd : "<c:out value='${lom.comp_cd}' />"},
				 		success: function (data ) {

				 			$("#name_p"+no).text("");
						    $("#name_p"+no).append("<select name='user_id"+no+"' id='user_id"+no+"'  style='width:100%;'>"+data.olist+"</select>");
							
				 		}
				 	};
			 
			$("#frm").ajaxSubmit(options);
			 
			 
		 }else if($("#r_type"+no+" option:selected").val() == "u"){
			 $("#name_p"+no).text("");
			 $("#name_p"+no).append("<input type='text' name='sign_nm"+no+"' id='sign_nm"+no+"' value='' style='width:95%;height:18px' class='text_search' onkeydown='if(event.keyCode==13){event.returnValue = false;javascript:searchUser("+no+");}'/><a href='javascript:searchUser("+no+");'>"
			 		 +"<img src='/images/las/en/icon/ico_search.gif' class='cp' alt='search' /></a>"
					 +"<input type='hidden' id='user_id"+no+"' name='user_id"+no+"' value='' />");
					 
		 }
	 }

		//Assigned Users row 추가
		function add() {
			
			if(Number($("#Ass_no").val()) == 1){
					$("#Ass_no").val(2);
				}else{
					$("#Ass_no").val(Number($("#Ass_no").val())+1);
				};
		
				var co_no = $("#Ass_no").val();

				
				$("#trRelationContract").append("<tr id='sign_tr"+co_no+"'><td class='tC' id='td_val1'><input type='text' name='sort_num' id='sort_num' onkeyup='olnyNum(this)' style='width:20%;' value='"+co_no+"'/></td><td>"
	                   +"<select name='a_type"+co_no+"' id='a_type"+co_no+"'  style='width:100%;'>"
	                   +"<option value='0'>--select--</option>"
	                   +"<option value='1'>Approve</option>"
	                   +"<option value='2'>Consent</option>"
	                   +"</select></td>"
	                   +"<td class='tC'><select name='r_type"+co_no+"' id='r_type"+co_no+"'  style='width:100%;' onchange='javascript:chgApp("+co_no+");'>"
	                   +"<option value='0'>--select--</option>"
	                   +"<option value='r'>ROLE</option>"
	                   +"<option value='u'>USER</option>"
	                   +"</select></td>"
	                   +"<td class='tC'>"
	                   +"<p id='name_p"+co_no+"'></p>"
	                   +"</td>"
	                   +"<td class='tC'>"
	                   +"<input type='checkbox' name='delChk' id='delChk' value='"+co_no+"'/>"
	                   +"</td>"
	                   +"</tr>");
				
				
			
		}
		
		//Assigned Users row 삭제
		function del() {
			$("input[name=delChk]:checked").each(function() {
				var chk = $(this).val();
				$("#sign_tr"+chk).remove();
			});
			
		}
	
/************** Approval Path List SCRIPT END************************************/



	// 금액에 숫자만 입력하게 합니다
	 function olnyNum(obj){
	 	var str = obj.value;
	 	str = new String(str);
	 	// 2014-02-05 Kevin. 소수점 구분 위해 "." 입력 허용함.
	     var Re = /[^0-9.]/g;
	 	str = str.replace(Re,'');
	 	
	 	// 금액 100,000 형태로 변환 추가 (2011/10/15)
	 	obj.value = Comma(str);
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
			<h3><spring:message code="secfw.page.field.approvalPath.Information" /></h3><!--Approval Path Information-->
		</div>
		<!-- //title -->
		<!-- content -->
		<div id="content">
		<div id="content_in">
			<!-- title -->
			<div class="title_basic">
				<h4><spring:message code="secfw.page.field.approvalPath.Information" /></h4><!--Approval Path Information-->
			</div>
			<!-- //title -->
		
			<form:form name="frm" id="frm" method="post" autocomplete="off">
			<input type="hidden" name="method"   value="" />
			<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />
			<input type="hidden" name="path_id"  value="<c:out value='${lom.path_id}'/>" />

			<!--assign user parameter  -->
			<input type="hidden" id="Ass_no" name="Ass_no" value="<c:out value='${Usersize}'/>" />
			<input type="hidden" id="cod_no" name="cod_no" value="1" />
			<!-- insert parameter -->
			<input type="hidden" id="src_no" name="src_no" value="" />
			<input type="hidden" id="ass_id_list" name="ass_id_list" value="" />
			<input type="hidden" id="input_condi_list" name="input_condi_list" value="" />
			<input type="hidden" id="input_condi_option" name="input_condi_option" value="" />
			<input type="hidden" id="input_condi_val" name="input_condi_val" value="" />
			<input type="hidden" id="atype_val" name="atype_val" value="" />
			<input type="hidden" id="rtype_val" name="rtype_val" value="" />
			<input type="hidden" id="sort_no" name="sort_no" value="" />


			
			<!-- 결재선검색 -->
			<input type='hidden' name='srch_user_cntnt_type' />
			<input type='hidden' name='srch_user_cntnt' />
			<input type='hidden' name='doSearch' value='Y' />
					
			<!-- button -->
			<div class="btnwrap">
				<span class="btn_all_w"><span class="save"></span><a href="javascript:save()"><spring:message code='clm.page.button.save' /></a></span>
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
						<th><spring:message code="secfw.page.field.approvalPath.PathName" /></th><!-- Path Name -->
						<td colspan="3"><input type="text" name="path_nm" id="path_nm" class="text" style="width:174px" value="<c:out value='${lom.path_nm}'/>" /></td>
					</tr>
					<tr>
						<th><spring:message code="secfw.page.field.approvalPath.Description" /></th><!-- Description-->
						<td colspan="3"><input type="text" name="comments" class="text" style="width:174px"  value="<c:out value='${lom.comments}'/>" /></td>
					</tr>
					<tr>
						<th><spring:message code="secfw.page.field.approvalPath.CreatedBy" /></th><!-- Created By-->
						<td><c:out value='${lom.reg_id}'/></td>
						<th><spring:message code='clm.page.field.admin.type.reg_dt' /></th>
						<td><c:out value='${lom.reg_dt}'/></td>
					</tr>
					<tr>
						<th><spring:message code="secfw.page.field.approvalPath.Priority" /></th><!-- Priority -->
						<td><input type="text" name="priority" id="priority" class="text"  style="width:58px" value="<c:out value='${lom.priority}'/>" /></td>
						<th><spring:message code="secfw.page.field.approvalPath.Mandatory" /></th><!-- Mandatory -->
						<td>
							<input type="radio" name="mandatory" value="M" <c:if test="${lom.mandatory eq 'M'}"> checked </c:if>>Mandatory</input>
							<input type="radio" name="mandatory" value="O" <c:if test="${lom.mandatory eq 'O'}"> checked </c:if>>Option</input>
						</td>
					</tr>
					<tr>
						<!-- 지법인 선택 -->
						<th class='head'><spring:message code="las.page.filed.userLoc"/></th>
						<td colspan="3">
							<c:out value='${lom.comp_cd}'/>
							<input type="hidden" id="loc_gbn" name="loc_gbn" value="<c:out value='${lom.comp_cd}'/>" />
						</td>
					</tr>
					<tr class="end">
						<th><spring:message code="secfw.page.field.approvalPath.Active" /></th><!-- Active -->
						<td colspan="3">
							<select name="use_yn" id="use_yn"  style="width:57px;">
									<option value="Y" <c:if test='${lom.use_yn eq "Y"}'> selected='selected' </c:if>>Y</option>
									<option value="N" <c:if test='${lom.use_yn eq "N"}'> selected='selected' </c:if>>N</option>
							</select>
						</td>
					</tr>
				</tbody>
			</table>
			
			
			<!-- title -->
			<div class="title_basic">
				<h4><spring:message code="secfw.page.field.approvalPath.Condition" /></h4><!-- Condition -->
			</div>
			<div id="rowAddDel" class="btnwraptab">
				<span class="btn_all_b"><span class="add"></span><a href="javascript:addrow();">Add</a></span>
			</div>
			<!--  view  -->
			<table class="table-style01">
				<colgroup>
					<col width="10%"/>
					<col width="*%"/>
				</colgroup>
				<tbody>
					<tr>
						<th><spring:message code="secfw.page.field.approvalPath.ContractType" /></th><!-- Contract Type -->
						<td>			  		
					  		<select name="type_1" id="type_1"  style="width:175px;" class="all" >
					  			<option value="" <c:if test="${lom.type_1 eq ''}"> selected='selected' </c:if>>--select--</option>
								<c:forEach var="list" items="${combo.combotype_1}" varStatus="cnt">
								<option value="<c:out value='${list.type_cd}'/>" <c:if test='${lom.type_1 eq list.type_cd}'> selected='selected' </c:if>><c:out value="${list.cd_nm}" /></option>
								</c:forEach>
							</select>
							<select name="type_2" id="type_2"  style="width:175px;">
								<option value="" <c:if test="${lom.type_2 eq ''}"> selected='selected' </c:if>>--select--</option>
								<c:forEach var="list2" items="${combo.combotype_2}" varStatus="cnt">
								<option value="<c:out value='${list2.type_cd}'/>" <c:if test='${lom.type_2 eq list2.type_cd}'> selected='selected' </c:if>><c:out value="${list2.cd_nm}" /></option>
								</c:forEach>
							</select>
							<select name="type_3" id="type_3"  style="width:175px;">
								<option value="" <c:if test="${lom.type_3 eq ''}"> selected='selected' </c:if>>--select--</option>
								<c:forEach var="list3" items="${combo.combotype_3}" varStatus="cnt">
								<option value="<c:out value='${list3.type_cd}'/>" <c:if test='${lom.type_3 eq list3.type_cd}'> selected='selected' </c:if>><c:out value="${list3.cd_nm}" /></option>
								</c:forEach>
							</select>
							<select name="type_4" id="type_4"  style="width:175px;">
								<option value="" <c:if test="${lom.type_4 eq ''}"> selected='selected' </c:if>>--select--</option>
								<c:forEach var="list4" items="${combo.combotype_4}" varStatus="cnt">
								<option value="<c:out value='${list4.type_cd}'/>" <c:if test='${lom.type_4 eq list4.type_cd}'> selected='selected' </c:if>><c:out value="${list4.cd_nm}" /></option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr class="end">
						<th><spring:message code="secfw.page.field.approvalPath.ConditionDetail" /></th><!-- Condition Detail -->
						<td>
							<span id="codi_ss">
					  		<select name="operation" id="operation"  style="width:175px;">
								<option value="AND" <c:if test="${lom2.operation eq 'AND'}">selected='selected' </c:if> >AND(All items are true)</option>
								<option value="OR"  <c:if test="${lom2.operation eq 'OR'}">selected='selected' </c:if> >OR(Atleast one item is true)</option>
							</select></br>						
						<c:choose>
							<c:when test="${resultdetailsize > 0}">
						<c:forEach var="list" items="${resultListDETAIL}" varStatus="i">
							<c:choose>
							<c:when test="${list.condition eq 'C001'}">
							<span id="codi_s<c:out value='${i.count}'/>">
								<select name="condition<c:out value='${i.count}'/>" id="condition<c:out value='${i.count}'/>"  style="width:175px;" onchange="javascript:chgDetail(<c:out value='${i.count}'/>);">
									<option value="">--select--</option>
									<option value="C001"  <c:if test="${list.condition eq 'C001'}">selected='selected' </c:if>><spring:message code="clm.page.msg.manage.amount" htmlEscape="true" /></option>
									<option value="C002"  <c:if test="${list.condition eq 'C002'}">selected='selected' </c:if>>Automatic Extension</option>
									<option value="C003"  <c:if test="${list.condition eq 'C003'}">selected='selected' </c:if>>Mandatory provisions</option>
								</select>
								<span id="opt_sp<c:out value='${i.count}'/>">
								<c:choose>
								<c:when test="${list.condition_option ne ''}">
								<select name='c_option<c:out value='${i.count}'/>' id='c_option<c:out value='${i.count}'/>'  style='width:57px;'>"
									<option value='1' <c:if test="${list.condition_option eq '1'}">selected='selected' </c:if> >></option>
									<option value='2' <c:if test="${list.condition_option eq '2'}">selected='selected' </c:if> >=</option>
									<option value='3' <c:if test="${list.condition_option eq '3'}">selected='selected' </c:if> ><</option>
									</select>&nbsp;
								<input type="text" name="c_val<c:out value='${i.count}'/>" id="c_val<c:out value='${i.count}'/>" class='text' value="<c:out value='${list.condition_val}'/>" size='10' maxLength='18' alt='<spring:message code='clm.page.msg.manage.amount' htmlEscape='true' />' onkeyup='olnyNum(this)' style='IME-MODE: disabled; height:19px;' required/>&nbsp;
								</c:when>
								<c:otherwise></c:otherwise>
								</c:choose>
								</span>
								<img src="<%=IMAGE %>/icon/ico_delete_set.gif" onclick="javascript:deleteInc(<c:out value='${i.count}'/>);" class="cp" alt="delete" readonly/></br>
								</span>
							</c:when>
							<c:otherwise>
								<span id="codi_s<c:out value='${i.count}'/>">
								<select name="condition<c:out value='${i.count}'/>" id="condition<c:out value='${i.count}'/>"  style="width:175px;" onchange="javascript:chgDetail(<c:out value='${i.count}'/>);">
									<option value="">--select--</option>
									<option value="C001"  <c:if test="${list.condition eq 'C001'}">selected='selected' </c:if>><spring:message code="clm.page.msg.manage.amount" htmlEscape="true" /></option>
									<option value="C002"  <c:if test="${list.condition eq 'C002'}">selected='selected' </c:if>>Automatic Extension</option>
									<option value="C003"  <c:if test="${list.condition eq 'C003'}">selected='selected' </c:if>>Mandatory provisions</option>
								</select>
								<span id="opt_sp<c:out value='${i.count}'/>">
								<select name="c_val<c:out value='${i.count}'/>" id="c_val<c:out value='${i.count}'/>"  style="width:57px;">
									<option value="Y" <c:if test="${list.condition_val eq 'Y'}">selected='selected' </c:if>>Y</option>
									<option value="N" <c:if test="${list.condition_val eq 'N'}">selected='selected' </c:if>>N</option>
								</select>
								<input type="hidden" id="c_option<c:out value='${i.count}'/>" name="c_option<c:out value='${i.count}'/>" value="2" />&nbsp;
								</span>
								<img src="<%=IMAGE %>/icon/ico_delete_set.gif" onclick="javascript:deleteInc(<c:out value='${i.count}'/>);" class="cp" alt="delete" readonly/></br>
								</span>							
							</c:otherwise>
							</c:choose>
								
						</c:forEach>
						</c:when>												
						<c:otherwise>
						</c:otherwise>
						</c:choose>
							</span>
						</td>
					</tr>
				</tbody>
			</table>
			
			
			<!-- title -->
			<div class="title_basic">
				<h4><spring:message code="secfw.page.field.approvalPath.ApprovalPathList" /></h4><!-- Approval Path List -->
			</div>
				<div id="rowAddDel" class="btnwraptab">
					<span class="btn_all_b"><span class="add"></span><a href="javascript:add();">Add</a></span>
				    <span class="btn_all_b"><span class="delete"></span><a href="javascript:del();">Del</a></span>
				</div>			
	            <div id="div_rel_contract">
	            <table cellspacing="0" cellpadding="0" border="0" class="table-style01" id="trRelationContract">
	                <colgroup>
	                    <col width="10%" />
	                    <col width="20%" />
	                    <col width="15%" />
	                    <col width="*%" />
	                    <col width="8%" />
	                    <col/>
	                </colgroup>
	                <tr>
	                    <th class="tC"><spring:message code="secfw.page.field.approvalPath.No" /></th><!-- No -->
	                    <th class="tC"><spring:message code="secfw.page.field.approvalPath.ApprovalType" /></th> <!-- Approval Type -->
	                    <th class="tC"><spring:message code="secfw.page.field.approvalPath.RoleUser" /></th><!-- Role/User -->
	                    <th class="tC"><spring:message code="secfw.page.field.approvalPath.Approver" /></th><!-- Approver -->
	                    <th class="tC">Del</th>
	                </tr>
	                <c:forEach var="list2" items="${resultListUser}" varStatus="i">               
	                <tr id="sign_tr<c:out value='${i.count}'/>">
						<td class='tC' id="td_val<c:out value='${i.count}'/>">
							<input type="text" name="sort_num" id='sort_num' onkeyup='olnyNum(this)' style="width:20%;" value="<c:out value='${i.count}'/>"/>
						</td>
	                    <td>
		                       <select name="a_type<c:out value='${i.count}'/>" id="a_type<c:out value='${i.count}'/>"  style="width:100%;">
								<option value="1" <c:if test="${list2.a_type eq '1'}"> selected='selected' </c:if> >Approve</option>
								<option value="2" <c:if test="${list2.a_type eq '2'}"> selected='selected' </c:if> >Consent</option>
							</select>
                    	</td>
                    	<td class='tC'>
                    		<select name="r_type<c:out value='${i.count}'/>" id="r_type<c:out value='${i.count}'/>"  style="width:100%;" onchange="javascript:chgApp(<c:out value='${i.count}'/>);">
								<option value="r" <c:if test="${list2.r_type eq 'r'}">selected='selected' </c:if> >ROLE</option>
								<option value="u" <c:if test="${list2.r_type eq 'u'}">selected='selected' </c:if> >USER</option>
							</select>
						</td>
						<td class='tC'>
							<p id="name_p<c:out value='${i.count}'/>">
							<c:choose>
							<c:when test="${list2.r_type eq 'r'}">
								<select name="user_id<c:out value='${i.count}'/>" id="user_id<c:out value='${i.count}'/>"  style="width:100%;">
									<c:forEach var="item" items="${combo.approverRole}" varStatus="cnt">
									<option value="<c:out value='${item.role_cd}'/>" <c:if test='${list2.role_user_id eq item.role_cd}'> selected='selected' </c:if>><c:out value="${item.role_nm}" /></option>
									</c:forEach>
								</select>
								
							</c:when>
							<c:when test="${list2.r_type eq 'u'}">
								<input type="text" name="sign_nm<c:out value='${i.count}'/>" id="sign_nm<c:out value='${i.count}'/>" value="<c:out value='${list2.user_nm}'/>" style="width:95%" class="text_search" onkeydown="if(event.keyCode==13){event.returnValue = false;javascript:searchUser(<c:out value='${i.count}'/>);}"/><a href="javascript:searchUser(<c:out value='${i.count}'/>);">
			 		 			<img src="/images/las/en/icon/ico_search.gif" class="cp" alt="search" /></a>
					 			<input type="hidden" id="user_id<c:out value='${i.count}'/>" name="user_id<c:out value='${i.count}'/>" value="<c:out value='${list2.role_user_id}'/>" />
							</c:when>
							<c:otherwise></c:otherwise>
							</c:choose>

							</p>
	                    </td>
	                    <td class='tC'>
	                    	<input type="checkbox" name="delChk" id='delChk' value="<c:out value='${i.count}'/>"/>
	                    </td>
	                </tr>
					</c:forEach>
	            </table>
            </div>
			<!-- //view  -->
			<!-- button -->
			<div class="btnwrap mt20">
				<span class="btn_all_w"><span class="save"></span><a href="javascript:save()"><spring:message code='clm.page.button.save' /></a></span>
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