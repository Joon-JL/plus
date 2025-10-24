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
 * 파  일  명 : Approval_i.jsp
 * 프로그램명 : 결재 승인자 - 등록
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
	getCodeSelectByUpCd("frm", "loc_gbn", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=A&combo_grp_cd=COMP2&combo_type=S&combo_del_yn=N");
	
	$('#type_3').change(function() {
		chgajax();
	});
	
	$("#loc_gbn").change(function() {
		$("[id^='sign_tr']").each(function(index) {
			$(this).remove();			
		});
		
		$("#trRelationContract").append("<tr id='sign_tr1'><td class='tC' id='td_val1'>1</td><td>"
                +"<select name='a_type1' id='a_type1'  style='width:100%;'>"
                +"<option value='0'>--select--</option>"
                +"<option value='1'>Approve</option>"
                +"<option value='2'>Consent</option>"
                +"</select></td>"
                +"<td class='tC'><select name='r_type1' id='r_type1'  style='width:100%;' onchange='javascript:chgApp(1);'>"
                +"<option value='0'>--select--</option>"
                +"<option value='r'>ROLE</option>"
                +"<option value='u'>USER</option>"
                +"</select></td>"
                +"<td class='tC'>"
                +"<p id='name_p1'></p>"
                +"</td>"
                +"<td class='tC'>"
                +"<input type='checkbox' name='delChk' id='delChk' value='1'/>"
                +"</td>"
                +"</tr>");
	});

	
});


	// 지법인 선택
// 	function selectOptions(select){
	
// 		var frm = document.frm;
		
// 		var cdNm;
// 		cdNm = '<spring:message code="las.page.field.userLocGbn"/>'+" : "+select.options[select.selectedIndex].value;
		
		
// 		$("#comp_nm").val(select.options[select.selectedIndex].text);
// 		$("#cd_nm").text(cdNm);
		
// 		var nationCd;
// 		nationCd = ", "+'<spring:message code="las.page.field.nationCd"/>'+" : "+select.options[select.selectedIndex].title;
		
// 		$("#nation_cd").text(nationCd);
		
// 	}
	
	//리스트
	function goList() { 
		var frm = document.frm;

		frm.method.value = "listApprovalPath";
		frm.action = "<c:url value='/clm/admin/approvalPath.do' />";
		frm.target = "_self";
		viewHiddenProgress(true);
		frm.submit();
	}
	
	//등록
	function save() {
		var frm = document.frm;
		
		var validationUserid = "";
		var validationCval = "";
		
		//validation check start
		$("[id^='user_id']").each(function(index) {
		  	validationUserid += $(this).val();
		});
		$("[id^='c_val']").each(function(index) {
			validationCval += $(this).val();
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
		}else if($("#loc_gbn").val()=="" || $("#loc_gbn").val()==null){
			alert('<spring:message code="las.page.filed.userLoc"/>'+' '+'<spring:message code="secfw.msg.ask.mandatory"/>');
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
	    
		//validation check end
		
		    if(confirm("<spring:message code='secfw.msg.ask.insert'/>")){
				
				var result1 = "";
				var result2 = "";
				var result3 = "";
				
				var result4 = "";
				var result5 = "";
				var result6 = "";
				

				$("[id^='condition']").each(function(index) {
					if($(this).val() == ""){
						$(this).remove();
					}
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
				
				
				frm.input_condi_list.value = result1;
				frm.input_condi_option.value = result2;
				frm.input_condi_val.value = result3;
				frm.ass_id_list.value = result4;
				frm.atype_val.value = result5;
				frm.rtype_val.value = result6;
	

		    	frm.method.value = "insertApprovalPath";
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

		
/***************************************** Condition  SCRIPT START************************************************************************/

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
	
/*********************************************************** Condition  SCRIPT end******************************************************/



/*********************************************************** Approval Path List SCRIPT START************************************/
 
	 //Approval Path List (role/user 선택에 따라 변경되는 스크립트)
	 function chgApp(no){
		 
		 if($("#r_type"+no+" option:selected").val() == "r"){
			 if($("#loc_gbn").val()=="" || $("#loc_gbn").val()==null){
				 $("#r_type"+no).val("0");
				 alert('<spring:message code="las.page.filed.userLoc"/>'+' '+'<spring:message code="secfw.msg.ask.mandatory"/>');
				 return;
			}
			 
			 var options = {
				 		url:"/clm/admin/approvalPath.do?method=checkRolelist",
				 		type: "post",
				 		dataType: "json",
				 		data: {comp_cd : $("#loc_gbn").val()},
				 		success: function (data ) {

				 			$("#name_p"+no).text("");
						    $("#name_p"+no).append("<select name='user_id"+no+"' id='user_id"+no+"'  style='width:100%;'>"+data.olist+"</select>");
							
				 		}
				 	};
			 
			$("#frm").ajaxSubmit(options);
			 
			 
		 }else if($("#r_type"+no+" option:selected").val() == "u"){
			 $("#name_p"+no).text("");
			 $("#name_p"+no).append("<input type='text' name='sign_nm"+no+"' id='sign_nm"+no+"' value='' style='width:95%;height:18px;' class='text_search'  onkeydown='if(event.keyCode==13){event.returnValue = false;javascript:searchUser("+no+");}'/><img src='/images/las/en/icon/ico_search.gif' class='imageIcon' alt='search' onclick='javascript:searchUser("+no+")'/>"
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

				
				$("#trRelationContract").append("<tr id='sign_tr"+co_no+"'><td class='tC' id='td_val1'>"+co_no+"</td><td>"
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
	
/*********************************************************** Approval Path List SCRIPT END******************************************************/



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

			<!--assign user parameter  -->
			<input type="hidden" id="Ass_no" name="Ass_no" value="1" />
			<input type="hidden" id="cod_no" name="cod_no" value="1" />
			<!-- insert parameter -->
			<input type="hidden" id="src_no" name="src_no" value="" />
			<input type="hidden" id="ass_id_list" name="ass_id_list" value="" />
			<input type="hidden" id="input_condi_list" name="input_condi_list" value="" />
			<input type="hidden" id="input_condi_option" name="input_condi_option" value="" />
			<input type="hidden" id="input_condi_val" name="input_condi_val" value="" />
			<input type="hidden" id="atype_val" name="atype_val" value="" />
			<input type="hidden" id="rtype_val" name="rtype_val" value="" />
			
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
						<td colspan="3">
					  		<input type="text" name="path_nm" id="path_nm"  class="text" style="width:174px" />
						</td>
					</tr>
					<tr>
						<th><spring:message code="secfw.page.field.approvalPath.Description" /></th><!-- Description-->
						<td colspan="3">
					  		<input type="text" name="comments" class="text" style="width:174px" />
						</td>
					</tr>
					<tr>
						<th><spring:message code="secfw.page.field.approvalPath.CreatedBy" /></th><!-- Created By-->
						<td>
							<c:out value='${command.session_user_nm}'/>/<c:out value='${command.session_dept_nm}'/>
						</td>
						<th>
							<spring:message code='clm.page.field.admin.type.reg_dt' />
						</th>
						<td>
							<%=DateUtil.getTime("yyyy-MM-dd")%>
						</td>
					</tr>
					<tr>
						<th><spring:message code="secfw.page.field.approvalPath.Priority" /></th><!-- Priority -->
						<td><input type="text" name="priority" id="priority" class="text" value="" style="width:58px" /></td>
						<th><spring:message code="secfw.page.field.approvalPath.Mandatory" /></th><!-- Mandatory -->
						<td>
							<input type="radio" name="mandatory" value="M"><spring:message code="secfw.page.field.approvalPath.Mandatory" /></input><!-- Mandatory-->
							<input type="radio" name="mandatory" value="O"><spring:message code="secfw.page.field.approvalPath.Option" /></input><!-- Option-->
						</td>
					</tr>
					<tr>
						<!-- 지법인 선택 -->
						<th class='head'><spring:message code="las.page.filed.userLoc"/><span class="astro">*</span></th>
			          	<td id="locTd" colspan="3">
							<select name="loc_gbn" id="loc_gbn"></select>							
						</td>
					</tr>
					<tr class="end">
						<th><spring:message code="secfw.page.field.approvalPath.Active" /></th><!-- Active -->
						<td colspan="3">
					  		<select name="use_yn" id="use_yn"  style="width:57px;">
								<option value="Y">Y</option>
								<option value="N">N</option>
							</select>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- title -->
			<div class="title_basic">
				<h4>Condition</h4>
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
					  			<option value="">--select--</option>
								<c:forEach var="list" items="${combo.combotype_1}" varStatus="cnt">
								<option value="<c:out value='${list.type_cd}'/>"><c:out value="${list.cd_nm}" /></option>
								</c:forEach>
							</select>
							<select name="type_2" id="type_2"  style="width:175px;">
								<option value="">--select--</option>
								<c:forEach var="list2" items="${combo.combotype_2}" varStatus="cnt">
								<option value="<c:out value='${list2.type_cd}'/>"><c:out value="${list2.cd_nm}" /></option>
								</c:forEach>
							</select>
							<select name="type_3" id="type_3"  style="width:175px;">
								<option value="">--select--</option>
								<c:forEach var="list3" items="${combo.combotype_3}" varStatus="cnt">
								<option value="<c:out value='${list3.type_cd}'/>"><c:out value="${list3.cd_nm}" /></option>
								</c:forEach>
							</select>
							<select name="type_4" id="type_4"  style="width:175px;">
								<option value="">--select--</option>
							</select>
						</td>
					</tr>
					<tr class="end">
						<th><spring:message code="secfw.page.field.approvalPath.ConditionDetail" /></th><!-- Condition Detail -->
						<td>
							<span id="codi_ss">
					  		<select name="operation" id="operation"  style="width:175px;">
								<option value="AND">AND(All items are true)</option>
								<option value="OR">OR(Atleast one item is true)</option>
							</select></br>
								<span id="codi_s1">
								<select name="condition1" id="condition1"  style="width:175px;" onchange="javascript:chgDetail(1);">
									<option value="">--select--</option>
									<option value="C001"><spring:message code="clm.page.msg.manage.amount" htmlEscape="true" /></option>
									<option value="C002">Automatic Extension</option>
									<option value="C003">Mandatory provisions</option>
								</select>
								<span id="opt_sp1"></span>
								<img src='<%=IMAGE %>/icon/ico_delete_set.gif' onclick='javascript:deleteInc(1);' class='cp' alt='delete' readonly/></br>
								</span>
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
	                <tr id="sign_tr1">
						<td class='tC' id="td_val1">1</td>
	                    <td>
		                       <select name="a_type1" id="a_type1"  style="width:100%;">
		                       	<option value="0">--select--</option>
								<option value="1">Approve</option>
								<option value="2">Consent</option>
							</select>
                    	</td>
                    	<td class='tC'>
                    		<select name="r_type1" id="r_type1"  style="width:100%;" onchange="javascript:chgApp(1);">
                    			<option value="0">--select--</option>
								<option value="r">ROLE</option>
								<option value="u">USER</option>
							</select>
						</td>
						<td class='tC'>
							<p id="name_p1"></p>
	                    </td>
	                    <td class='tC'>
	                    	<input type="checkbox" name="delChk" id='delChk' value="1"/>
	                    </td>
	                </tr>

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