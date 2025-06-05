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
 * 파  일  명 : Role_i.jsp
 * 프로그램명 : 결재라인 Role - 등록
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
<!-- Role_i.jsp -->
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
	
	getCodeSelectByUpCd("frm", "loc_gbn", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=A&combo_grp_cd=COMP2&combo_type=S&combo_del_yn=N");
	
	var frm = document.frm;
	
	Map = function(){
		 this.map = new Object();
		};   
		Map.prototype = {   
		    put : function(key, value){   
		        this.map[key] = value;
		    },   
		    get : function(key){   
		        return this.map[key];
		    },
		    containsKey : function(key){    
		     return key in this.map;
		    },
		    containsValue : function(value){    
		     for(var prop in this.map){
		      if(this.map[prop] == value) return true;
		     }
		     return false;
		    },
		    isEmpty : function(key){    
		     return (this.size() == 0);
		    },
		    clear : function(){   
		     for(var prop in this.map){
		      delete this.map[prop];
		     }
		    },
		    remove : function(key){    
		     delete this.map[key];
		    },
		    keys : function(){   
		        var keys = new Array();   
		        for(var prop in this.map){   
		            keys.push(prop);
		        }   
		        return keys;
		    },
		    values : function(){   
		     var values = new Array();   
		        for(var prop in this.map){   
		         values.push(this.map[prop]);
		        }   
		        return values;
		    },
		    size : function(){
		      var count = 0;
		      for (var prop in this.map) {
		        count++;
		      }
		      return count;
		    }
		};
	

});


	
	
	function goList() { 
		var frm = document.frm;

		frm.method.value = "listRole";
		frm.action = "<c:url value='/clm/admin/role.do' />";
		frm.target = "_self";
		viewHiddenProgress(true);
		frm.submit();
	}
	
	function save() {
		var frm = document.frm;
		
		
		//validation check
		var validationUserid = "";
		
		$("[id^='user_id']").each(function(index) {
		  	validationUserid += $(this).val();
		});

		
		if($("#role_name").val() == "" || $("#role_name").val() == null ){
			alert("Role Name is required");
			$("#role_name").focus(); 
			return;
		}else if($("#loc_gbn").val()=="" || $("#loc_gbn").val()==null){
			alert('<spring:message code="las.page.filed.userLoc"/>'+' '+'<spring:message code="secfw.msg.ask.mandatory"/>');
			return;
		}else if(validationUserid == "" || validationUserid == null){
			alert("Assigned user is required");
			return;
		}
		
		var code1 = code2 = chklist = "";
		
	    $("[id^='user_id']").each(function(i) {
			
	        code2 = $(this).val();
	        
	        if(!code2 == ""){
	        
		        if(code1.match(code2)!= null) {
		        	alert("<spring:message code='secfw.page.field.approvalPath.duplication'/>");
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
				
				var result = "";
				$("[id^='user_id']").each(function(index) {
				  	result += $(this).val()+"/";
				});
				
				frm.ass_id_list.value =result;
				
		    	frm.method.value = "insertRole";
				frm.action = "<c:url value='/clm/admin/role.do' />";
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
		
		var co_no = $("#Ass_no").val();
		$("#assign_nm"+co_no).text(name+"/"+jikgupNm+"/"+ deptNm);
		$("#sign_td"+co_no).remove();
		$("#user_id"+co_no).val(userId);
	
	
	}
	
	
	//Assigned Users row 추가
	function add() {
		
		if($("[id^='sign_td']").length > 0){
			alert("Please select Assigned Users first.");
			return;
			
		}else{
			
		
			
			if(Number($("#Ass_no").val()) == 1){
				$("#Ass_no").val(2);
			}else{
				$("#Ass_no").val(Number($("#Ass_no").val())+1);
			};
	
			var co_no = $("#Ass_no").val();
			
			
			//console.log($("#Ass_no").val());
			$("#trRelationContract").append("<tr id='sign_tr"+co_no+"'><td class='tC' id='td_val1'>"+co_no+"</td><td><p id='sign_td"+co_no+"' ><input type='text' name='sign_nm"+co_no+"' id='sign_nm"+co_no+"' style='width:60%;height:18px'"
					+"class='text_search' onkeydown='if(event.keyCode==13){event.returnValue = false;javascript:searchUser("+co_no+");}'/>"
					+"<a href='javascript:searchUser("+co_no+");'><img src='<%=IMAGE%>/icon/ico_search.gif' class='cp' alt='search' /></a></p>"
					+"<span id='assign_nm"+co_no+"'></span><input type='hidden' id='user_id"+co_no+"' name='user_id"+co_no+"' value='' /></td>"
					+"<td class='tC'><input type='checkbox' name='delChk' id='delChk' value='"+co_no+"'/></td></tr>");
		}
		
		}
	//Assigned Users row 삭제
	function del() {
		$("input[name=delChk]:checked").each(function() {
			var chk = $(this).val();
			$("#sign_tr"+chk).remove();
			//console.log(chk);
		});
		
	}
	
	 //reviewer 추가 
	 function setUserReview(userId,name,jikgupNm,deptNm,i_no) {
		 
		 var co_no = i_no;
		 
		 if(i_no == 1){
				$("#Ass_no").val(2);

				$("#assign_nm"+co_no).text(name+"/"+jikgupNm+"/"+ deptNm);
				$("#sign_td"+co_no).remove();
				$("#user_id"+co_no).val(userId);
				
			}else{
				$("#Ass_no").val(Number($("#Ass_no").val())+1);
				
				$("#trRelationContract").append("<tr id='sign_tr"+co_no+"'><td class='tC' id='td_val1'>"+co_no+"</td><td>"
						+"<span id='assign_nm"+co_no+"'>"+name+"/"+jikgupNm+"/"+ deptNm+"</span><input type='hidden' id='user_id"+co_no+"' name='user_id"+co_no+"' value="+userId+" /></td>"
						+"<td class='tC'><input type='checkbox' name='delChk' id='delChk' value='"+co_no+"'/></td></tr>");
			};
			
			
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
			<input type="hidden" name="method"   value="" />
			<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />

			<!--assign user parameter  -->
			<input type="hidden" id="Ass_no" name="Ass_no" value="1" />
			<input type="hidden" id="ass_id_list" name="ass_id_list" value="" />

			
			<!-- 결재선검색 -->
			<input type='hidden' name='srch_user_cntnt_type' />
			<input type='hidden' name='srch_user_cntnt' />
			<input type='hidden' name='doSearch' value='Y' />
			
			<!--reviewer parameter  -->
			<input type="hidden" id="review_yn" name="review_yn" value="N" />
					
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
						<th><spring:message code="secfw.page.field.Role.PathName" /></th><!-- role name -->
						<td colspan="3">
					  		<input type="text" name="role_name" id="role_name" class="text" style="width:174px" />
						</td>
					</tr>
					<tr>
						<th><spring:message code="secfw.page.field.approvalPath.CreatedBy" /></th><!-- Created By -->
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
						<th><spring:message code="secfw.page.field.approvalPath.Description" /></th><!-- Description -->
						<td colspan="3">
					  		<input type="text" name="description" id="description" class="text_full" value="" alt="<spring:message code='clm.page.field.admin.type.cd_nm' />" maxLength="100" />
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
				<h4><spring:message code="secfw.page.field.Role.AssignedUser" /></h4><!-- Assigned Users -->
			</div>
				<div id="rowAddDel" class="btnwraptab">
					<span class="btn_all_b"><span class="add"></span><a href="javascript:add();">Add</a></span>
				    <span class="btn_all_b"><span class="delete"></span><a href="javascript:del();">Del</a></span>
				</div>			
	            <div id="div_rel_contract">
	            <table cellspacing="0" cellpadding="0" border="0" class="table-style01" id="trRelationContract">
	                <colgroup>
	                    <col width="10%" />
	                    <col width="80%" />
	                    <col width="10%" />
	                    <col/>
	                </colgroup>
	                <tr>
	                	<th class="tC"><spring:message code="secfw.page.field.approvalPath.No" /></th><!-- No -->
	                    <th class="tC"><spring:message code="secfw.page.field.Role.AssignedUser" /></th> <!-- Assigned Users -->
	                    <th class="tC">Del</th>
	                </tr>               
	                <tr id="sign_tr1">
	                     <td class='tC' id="td_val1">1</td>
	                     <td>
	                        <p id="sign_td1">
	                        <input type="text" name="sign_nm1" id="sign_nm1" value="" style="width:60%;height:18px;" class="text_search" onkeydown="if(event.keyCode==13){event.returnValue = false;javascript:searchUser(1);}"/><a href="javascript:searchUser(1);"><img src="<%=IMAGE%>/icon/ico_search.gif"  class="imageIcon" alt="search" /></a>
	                        </p>
	                        <span id="assign_nm1"></span>
	                        <input type='hidden' id='user_id1' name='user_id1' value='' />
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