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
 * 파  일  명 : Type_i.jsp
 * 프로그램명 : 계약유형관리 - 등록
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
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
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="las.main.title" /></title>
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<LINK href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">

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
	
	//계약분류 코드
  	getCodeSelectByUpCd("frm", "dimension_cd", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=TOP&combo_abbr=F&combo_type=&combo_del_yn=N&combo_selected=");
	
  	//상세 코드
  	chgDetailCd();
  	
  	frmChkLenMe(frm.cd_expl,1000,'curByteExpl');
});

$(function() {
	$("#dimension_cd").change(function(){
		var cd = new Array();
		var cdNm = new Array();
		var cdNmAbbr = new Array();
		var cdNmEng = new Array();
		var cdNmAbbrEng = new Array();
		
		chgDetailCd();
		returnTypeItem(cd, cdNm, cdNmAbbr, cdNmEng, cdNmAbbrEng);
	});
});

	function chgDetailCd() {
		//상세 코드
		getCodeSelectByUpCd("frm", "detail_cd", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd="+frm.dimension_cd.value+"&combo_abbr=F&combo_type=N&combo_del_yn=N&combo_selected=");
	}
	
	function goList() { 
		var frm = document.frm;

		frm.method.value = "listType";
		frm.action = "<c:url value='/clm/admin/type.do' />";
		frm.target = "_self";
		viewHiddenProgress(true);
		frm.submit();
	}
	
	function refresh(){
		if (confirm("<spring:message code='las.msg.alert.reset' />")) {
			//계약분류 코드
		  	getCodeSelectByUpCd("frm", "dimension_cd", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=TOP&combo_abbr=F&combo_type=&combo_del_yn=N&combo_selected=");
			
		  	//상세 코드
		  	chgDetailCd();
		  	
		  	$("#cd_nm_eng").val("");
		  	$("#cd_nm_abbr_eng").val("");
		  	$("#cd_expl").val("");
		}
	}

	function save() {
		var frm = document.frm;
		if(frm.cd_nm_eng.value==""){
			alert("<spring:message code='clm.page.field.admin.type.cd_nm' />"+"<spring:message code='las.msg.alert.isRequired'/>");
		}else{
		    if(confirm("<spring:message code='secfw.msg.ask.insert' />")){
		    	frm.method.value = "insertType";
				frm.action = "<c:url value='/clm/admin/type.do' />";
				frm.target = "_self";
				viewHiddenProgress(true);
				frm.submit();
			}
		}
	}
	
	function popCntrItem(){
		var frm = document.frm;

		if(frm.dimension_cd.value != "T03"){
			alert("<spring:message code="clm.page.msg.admin.announce001" />");
			frm.dimension_cd.value = "T03";
			return;
		}
		/*
		if(frm.detail_cd.value == ""){
			alert("<spring:message code="clm.page.msg.admin.announce002" />");
			return;
		}
		*/
		PopUpWindowOpen('', 400, 500, false);
		frm.method.value = "listTypeItem";
		frm.action = "<c:url value='/clm/admin/type.do' />";
		frm.target = "PopUpWindow";
		
		frm.submit();
	}
	
	function newCntrItem(){
		document.getElementById("typeItem").innerHTML = "<input type='hidden' id='item_cds' name='item_cds' value='' />";
	}
	
	function returnTypeItem(arCd, arCdNm, arCdNmAbbr, arCdNmEng, arCdNmAbbrEng){
		returnCdNm = "";
		
	    for(var i=0; i<arCd.length; i++){
	    	returnCdNm += "<input type='hidden' id='item_cds' name='item_cds' value='" + arCd[i] + "' />";
	    	returnCdNm += "<input type='hidden' id='item_cd_nms' name='item_cd_nms' value='" + arCdNm[i] + "' />";
	    	returnCdNm += "<input type='hidden' id='item_cd_nm_abbrs' name='item_cd_nm_abbrs' value='" + arCdNmAbbr[i] + "' />";
	    	returnCdNm += "<input type='hidden' id='item_cd_nm_engs' name='item_cd_nm_engs' value='" + arCdNmEng[i] + "' />";
	    	returnCdNm += "<input type='hidden' id='item_cd_nm_abbr_engs' name='item_cd_nm_abbr_engs' value='" + arCdNmAbbrEng[i] + "' />";
	    	
	    	returnCdNm += (i+1) + ") " + arCdNm[i];
	    	if(i+1 != arCd.length){
	    		returnCdNm += "<br>";
	    	}
	    }
	    
	    document.getElementById("typeItem").innerHTML = returnCdNm;
	}
	
</script>
</head>
<body>

<div id="wrap">
	<!-- container -->
	<div id="container">
		<!-- location -->
		<div class="location"><IMG SRC="<%=IMAGE %>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //location -->
		
		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.title.admin.Type" /></h3>
		</div>
		<!-- //title -->
		<!-- content -->
		<div id="content">
		<div id="content_in">
		
			<form:form name="frm" id="frm" method="post" autocomplete="off">
			<input type="hidden" name="method"   	 		value="" />
			<input type="hidden" name="menu_id"  	 		value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="curPage"  	 		value="<c:out value='${command.curPage}'/>" />
			<input type="hidden" name="srch_word"  			value="<c:out value='${command.srch_word}'/>" />
			<input type="hidden" name="srch_dimension" 		value="<c:out value='${command.srch_dimension}'/>" />
			<input type="hidden" name="type_cd"     id="type_cd"     value="" />
			<input type="hidden" id="item_cds" name="item_cds" value="" />
			<input type="hidden" id="item_cd_nms" name="item_cd_nms" value="" />
			<input type="hidden" id="item_cd_nm_abbrs" name="item_cd_nm_abbrs" value="" />
			<input type="hidden" id="item_cd_nm_engs" name="item_cd_nm_engs" value="" />
			<input type="hidden" id="item_cd_nm_abbr_engs" name="item_cd_nm_abbr_engs" value="" />
					
			<!-- button -->
			<div class="btnwrap">
				<span class="btn_all_w"><span class="save"></span><a href="javascript:save()"><spring:message code='clm.page.button.save' /></a></span>
				<span class="btn_all_w"><span class="reset"></span><a href="javascript:refresh()"><spring:message code='clm.page.button.reset' /></a></span>
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
						<th>
							<spring:message code='clm.page.field.admin.type.dimension' />
						</th>
						<td colspan="3">
					  		<select name="dimension_cd" id="dimension_cd"  style="width:180px;">
							</select>
						</td>
					</tr>
					<tr>
						<th>
							<spring:message code='clm.page.field.admin.type.reg_dt' />
						</th>
						<td>
							<%=DateUtil.getTime("yyyy-MM-dd")%>
						</td>
						<th>
							<spring:message code='clm.page.field.admin.type.registrant' />
						</th>
						<td>
							<c:out value='${command.session_user_nm}'/>/<c:out value='${command.session_dept_nm}'/>
						</td>
					</tr>
					<tr>
						<th>
							<spring:message code='clm.page.field.admin.type.detail' />
						</th>
						<td colspan="3">
					  		<select name="detail_cd" id="detail_cd"  style="width:180px;" onchange="javascript:newCntrItem();">
							</select>
						</td>
					</tr>
<!-- 					<tr> -->
<!-- 						<th> -->
<%-- 							<spring:message code='clm.page.field.admin.type.cd_nm' /> --%>
<!-- 						</th> -->
<!-- 						<td colspan="3"> -->
<%-- 					  		<input type="text" name="cd_nm" id="cd_nm" class="text" size="100" value="" alt="<spring:message code='clm.page.field.admin.type.cd_nm' />" maxLength="100" required /> --%>
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<th> -->
<%-- 							<spring:message code='clm.page.field.admin.type.cd_nm_abbr' /> --%>
<!-- 						</th> -->
<!-- 						<td colspan="3"> -->
<%-- 					  		<input type="text" name="cd_nm_abbr" id="cd_nm_abbr" class="text" size="100" value="" alt="<spring:message code='clm.page.field.admin.type.cd_nm_abbr' />" maxLength="50" /> --%>
<!-- 						</td> -->
<!-- 					</tr> -->
					<tr>
						<th>
							<spring:message code='clm.page.field.admin.type.cd_nm' />
						</th>
						<td colspan="3">
					  		<input type="text" name="cd_nm_eng" id="cd_nm_eng" class="text_full" value="" alt="<spring:message code='clm.page.field.admin.type.cd_nm' />" maxLength="100" />
						</td>
					</tr>
					<tr>
						<th>
							<spring:message code='clm.page.field.admin.type.cd_nm_abbr' />
						</th>
						<td colspan="3">
					  		<input type="text" name="cd_nm_abbr_eng" id="cd_nm_abbr_eng" class="text_full" value="" alt="<spring:message code='clm.page.field.admin.type.cd_nm_abbr' />" maxLength="50" />
						</td>
					</tr>
					<tr>
						<th>
							<spring:message code='clm.page.field.admin.type.cd_expl' />
						</th>
						<td colspan="3">
							<span id="curByteExpl">0</span>/<spring:message code="clm.page.msg.common.max1000byte" /><br>
							<textarea name="cd_expl" id="cd_expl" rows="10" cols="98" maxLength="1000" onkeyup="frmChkLenMe(this,1000,'curByteExpl')" class="text_area_full"></textarea>
						</td>
					</tr>
					<tr class="end">
						<th>
							<spring:message code='clm.page.field.admin.type.cntr_item' />
						</th>
						<td colspan="3">
						<dl>
							<dt style="float:left; padding-right:15px">
								<span class="btn_all_w"><span class="select"></span><a href="javascript:popCntrItem()"><spring:message code='clm.page.button.select' /></a></span>
							</dt>
							<dd style="float:left;">
								<span id="typeItem" >
								</span>
							</dd>
						</dl>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- //view  -->
			
			<!-- button -->
			<div class="btnwrap mt20">
				<span class="btn_all_w"><span class="save"></span><a href="javascript:save()"><spring:message code='clm.page.button.save' /></a></span>
				<span class="btn_all_w"><span class="reset"></span><a href="javascript:refresh()"><spring:message code='clm.page.button.reset' /></a></span>
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