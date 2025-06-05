<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.code.dto.CodeVO" %>

<%--
/**
 * 파     일     명 : CodeMng.jsp
 * 프로그램명 : 공통코드관리
 * 설               명 : 
 * 작     성     자 : 금현서
 * 작     성     일 : 2009.11
 */
--%>
<%
	CodeVO cVO = (CodeVO)request.getAttribute("vo");

    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
    String menu_id  = cVO.getMenu_id();
%>
<%@ include file="/WEB-INF/jsp/secfw/common/CommonProgress.jspf"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<LINK href="<c:url value='/style/secfw/common.css' />" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/ui.core.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/CommonProgress.js' />"></script>
<script language="javascript">
<!--
var title_grp_cd             = '<spring:message code="secfw.page.field.code.grp_cd" />';
var title_grp_cd_nm          = '<spring:message code="secfw.page.field.code.grp_cd_nm" />';
var title_grp_cd_abbr_nm     = '<spring:message code="secfw.page.field.code.grp_cd_abbr_nm" />';
var title_grp_cd_nm_eng      = '<spring:message code="secfw.page.field.code.grp_cd_nm_eng" />';
var title_grp_cd_abbr_nm_eng = '<spring:message code="secfw.page.field.code.grp_cd_abbr_nm_eng" />';
var title_cd                 = '<spring:message code="secfw.page.field.code.cd" />';
var title_cd_nm              = '<spring:message code="secfw.page.field.code.cd_nm" />';
var title_cd_abbr_nm         = '<spring:message code="secfw.page.field.code.cd_abbr_nm" />';
var title_cd_nm_eng          = '<spring:message code="secfw.page.field.code.cd_nm_eng" />';
var title_cd_abbr_nm_eng     = '<spring:message code="secfw.page.field.code.cd_abbr_nm_eng" />';
var title_comments           = '<spring:message code="secfw.page.field.code.comments" />';
var title_use_yn             = '<spring:message code="secfw.page.field.code.use_yn" />' + ' (Y/N)';
var title_cd_order           = '<spring:message code="secfw.page.field.code.cd_order" />';
var title_useman_mng_itm1    = '<spring:message code="secfw.page.field.code.useman_mng_itm1" />';
var title_useman_mng_itm2    = '<spring:message code="secfw.page.field.code.useman_mng_itm2" />';
var title_useman_mng_itm3    = '<spring:message code="secfw.page.field.code.useman_mng_itm3" />';
var title_useman_mng_itm4    = '<spring:message code="secfw.page.field.code.useman_mng_itm4" />';
var title_useman_mng_itm5    = '<spring:message code="secfw.page.field.code.useman_mng_itm5" />';

	$(function() {

		$("#allCheckGrpCode")
		.click(function() {
			if(($(this).attr("checked"))) {			
				$("input:checkbox[name='chk_grp_cds']")
					.each(function(){
						$(this).attr("checked", true);
					});
			} else {
				$("input:checkbox[name='chk_grp_cds']")
				.each(function(){
					$(this).attr("checked", false);
				});
			}
		}); 

		$("#allCheckCode")
		.click(function() {
			if(($(this).attr("checked"))) {			
				$("input:checkbox[name='chk_cds']")
					.each(function(){
						$(this).attr("checked", true);
					});
			} else {
				$("input:checkbox[name='chk_cds']")
				.each(function(){
					$(this).attr("checked", false);
				});
			}
		});
		
		$(document).keydown(function(event){

			if(event.keyCode == "13") {
				return false;
			}

		});

		listGrpCode();
	});

	/**
	* 그룹코드 조회
	*/
	function listGrpCode(displayYn) {

		var totalCnt = 0;
		
		var options = {
			url: "<c:url value='/secfw/code.do?method=listGrpCode' />",
			type: "post",
			dataType: "json",
			beforeSubmit: function(formData, form){
				//if("N" != displayYn)
				//	comLayerPopCenter('ProgressLayer1');
				//return true;
			},
			success: function(responseText,returnMessage) {
				$('#grpCodeTable tr:not(#grpCodeTableHeader)').remove();
				if(responseText != null && responseText.length != 0) {
					$.each(responseText, function(entryIndex, entry) {
						var html = "<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\" onClick=\"javascript:listCode(\'" + entry['grp_cd'] + "\')\">"
						         +	"<td><input type=\'checkbox\' id=\'" + entry['grp_cd'] + "\' name=\'chk_grp_cds\' class=\'checkbox\' value=\'" + entry['grp_cd'] + "\'></td>"
						         +	"<td><input type=\'text\' name=\'grp_cds\' id=\'grp_cds\' class=\'text\' value=\'" + entry['grp_cd'] + "\' size=\'18\' fieldTitle=\'" + title_grp_cd + "\' required=\'*\' maxLength=\'30\' /></td>"
						         +	"<td><input type=\'text\' name=\'grp_cd_nms\' id=\'grp_cd_nms\' class=\'text\' value=\'" + entry['grp_cd_nm'] + "\' size=\'18\' fieldTitle=\'" + title_grp_cd_nm + "\' required=\'*\' maxLength=\'30\' /></td>"
						         +	"<td><input type=\'text\' name=\'grp_cd_abbr_nms\' id=\'grp_cd_abbr_nms\' class=\'text\' value=\'" + entry['grp_cd_abbr_nm'] + "\' size=\'12\' fieldTitle=\'" + title_grp_cd_abbr_nm + "\' maxLength=\'30\' /></td>"
						         +	"<td><input type=\'text\' name=\'grp_cd_nm_engs\' id=\'grp_cd_nm_engs\' class=\'text\' value=\'" + entry['grp_cd_nm_eng'] + "\' size=\'12\' fieldTitle=\'" + title_grp_cd_nm_eng + "\' maxLength=\'30\' /></td>"
						         +	"<td><input type=\'text\' name=\'grp_cd_abbr_nm_engs\' id=\'grp_cd_abbr_nm_engs\' class=\'text\' value=\'" + entry['grp_cd_abbr_nm_eng'] + "\' size=\'12\' fieldTitle=\'" + title_grp_cd_abbr_nm_eng + "\' maxLength=\'30\' /></td>"
						         +	"<td><input type=\'text\' name=\'use_yns\' id=\'use_yns\' class=\'text\' value=\'" + entry['use_yn'] + "\' size=\'1\' fieldTitle=\'" + title_use_yn + "\' required=\'*\' maxLength=\'1\' /></td>"
						         +	"<td><input type=\'text\' name=\'commentss\' id=\'commentss\' class=\'text\' value=\'" + entry['comments'] + "\' size=\'12\' fieldTitle=\'" + title_comments + "\' maxLength=\'100\'/></td>"
						    	 + "</tr>";
						$('#grpCodeTable').append(html);
						totalCnt = entry['total_cnt'];
					});
				} else {
					var html = "<tr id=\'resultNotFoundRowGrpCode\'><td colspan=8 align=center><spring:message code="secfw.msg.succ.noResult" /></td></tr>";
					$('#grpCodeTable').append(html);
				}

				$('#grpCodeTotalCnt').text(totalCnt);
				//if("N" != displayYn)
				//	progressWinClose();
			}
		}
		$("#frmGrpCode").ajaxSubmit(options);
	}

	/**
	* 공통코드 조회
	*/
	function listCode(data) {

		var frm = document.frmCode;
		frm.select_grp_cd.value = data;

		var totalCnt = 0;

		$('#codeTable tr:not(#codeTableHeader)').remove();
		
		var options = {
			url: "<c:url value='/secfw/code.do?method=listCode'/>",
			type: "post",
			dataType: "json",
			beforeSubmit: function(formData, form){
				if("N" != data)
					comLayerPopCenter('ProgressLayer1');
				return true;
			},
			success: function(responseText, statusText) {
				$('#codeTable tr:not(#codeTableHeader)').remove();
				if(responseText != null && responseText.length != 0) {
					$.each(responseText, function(entryIndex, entry) {
						var html = "<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\">"
						         +	"<td><input type=\'checkbox\' id=\'" + entry['cd'] + "\' name=\'chk_cds\' class=\'checkbox\' value=\'" + entry['cd'] + "\'></td>"
						         +	"<td><input type=\'text\' name=\'cds\' id=\'cds\' class=\'text\' value=\'" + entry['cd'] + "\' size=\'14\' fieldTitle=\'" + title_cd + "\' required=\'*\' maxLength=\'30\' /></td>"
						         +	"<td><input type=\'text\' name=\'cd_nms\' id=\'cd_nms\' class=\'text\' value=\'" + entry['cd_nm'] + "\' size=\'20\' fieldTitle=\'" + title_cd_nm + "\' required=\'*\' maxLength=\'30\' /></td>"
						         +	"<td>" + entry['grp_cd'] + "</td>"
						         +	"<td><input type=\'text\' name=\'cd_abbr_nms\' id=\'cd_abbr_nms\' class=\'text\' value=\'" + entry['cd_abbr_nm'] + "\' size=\'12\' fieldTitle=\'" + title_cd_abbr_nm + "\' maxLength=\'30\' /></td>"
						         +	"<td><input type=\'text\' name=\'cd_nm_engs\' id=\'cd_nm_engs\' class=\'text\' value=\'" + entry['cd_nm_eng'] + "\' size=\'12\' fieldTitle=\'" + title_cd_nm_eng + "\' maxLength=\'30\' /></td>"
						         +	"<td><input type=\'text\' name=\'cd_abbr_nm_engs\' id=\'cd_abbr_nm_engs\' class=\'text\' value=\'" + entry['cd_abbr_nm_eng'] + "\' size=\'12\' fieldTitle=\'" + title_cd_abbr_nm_eng + "\' maxLength=\'30\' /></td>"
						         +	"<td><input type=\'text\' name=\'cd_orders\' id=\'cd_orders\' class=\'text\' value=\'" + entry['cd_order'] + "\' size=\'3\' fieldTitle=\'" + title_cd_order + "\' required=\'*\' maxLength=\'3\' char=\'n\' /></td>"
						         +	"<td><input type=\'text\' name=\'use_yns\' id=\'use_yns\' class=\'text\' value=\'" + entry['use_yn'] + "\' size=\'1\' fieldTitle=\'" + title_use_yn + "\' required=\'*\' maxLength=\'1\' /></td>"
						         +	"<td><input type=\'text\' name=\'commentss\' id=\'commentss\' class=\'text\' value=\'" + entry['comments'] + "\' size=\'10\' fieldTitle=\'" + title_comments + "\' maxLength=\'100\'/></td>"
						         +	"<td><input type=\'text\' name=\'useman_mng_itm1s\' id=\'useman_mng_itm1s\' class=\'text\' value=\'" + entry['useman_mng_itm1'] + "\' size=\'10\' fieldTitle=\'" + title_useman_mng_itm1 + "\' maxLength=\'30\'/></td>"
						         +	"<td><input type=\'text\' name=\'useman_mng_itm2s\' id=\'useman_mng_itm2s\' class=\'text\' value=\'" + entry['useman_mng_itm2'] + "\' size=\'10\' fieldTitle=\'" + title_useman_mng_itm2 + "\' maxLength=\'30\'/></td>"
						         +	"<td><input type=\'text\' name=\'useman_mng_itm3s\' id=\'useman_mng_itm3s\' class=\'text\' value=\'" + entry['useman_mng_itm3'] + "\' size=\'10\' fieldTitle=\'" + title_useman_mng_itm3 + "\' maxLength=\'30\'/></td>"
						         +	"<td><input type=\'text\' name=\'useman_mng_itm4s\' id=\'useman_mng_itm4s\' class=\'text\' value=\'" + entry['useman_mng_itm4'] + "\' size=\'10\' fieldTitle=\'" + title_useman_mng_itm4 + "\' maxLength=\'30\'/></td>"
						         +	"<td><input type=\'text\' name=\'useman_mng_itm5s\' id=\'useman_mng_itm5s\' class=\'text\' value=\'" + entry['useman_mng_itm5'] + "\' size=\'10\' fieldTitle=\'" + title_useman_mng_itm5 + "\' maxLength=\'30\'/></td>"
						    	 + "</tr>";
						$('#codeTable').append(html);
						totalCnt = entry['total_cnt'];
					});
				} else {
					var html = "<tr id=\'resultNotFoundRowCode\'><td colspan=15 align=center><spring:message code="secfw.msg.succ.noResult" /></td></tr>";
					$('#codeTable').append(html);
				}

				$('#codeTotalCnt').text(totalCnt);
				if("N" != data)
					progressWinClose();
			}
		}
		$("#frmCode").ajaxSubmit(options);
	}

	/**
	* 그룹코드 추가
	*/
	function newGrpCode() {

		$('#resultNotFoundRowGrpCode').remove();
		var html = "<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\">"
	         +	"<td><input type=\'checkbox\' id=\'\' name=\'chk_grp_cds\' class=\'checkbox\' value=\'\'></td>"
	         +	"<td><input type=\'text\' name=\'grp_cds\' id=\'grp_cds\' class=\'text\' value=\'\' size=\'18\' fieldTitle=\'" + title_grp_cd + "\' required=\'*\' maxLength=\'30\' /></td>"
	         +	"<td><input type=\'text\' name=\'grp_cd_nms\' id=\'grp_cd_nms\' class=\'text\' value=\'\' size=\'18\' fieldTitle=\'" + title_grp_cd_nm + "\' required=\'*\' maxLength=\'30\' /></td>"
	         +	"<td><input type=\'text\' name=\'grp_cd_abbr_nms\' id=\'grp_cd_abbr_nms\' class=\'text\' value=\'\' size=\'12\' fieldTitle=\'" + title_grp_cd_abbr_nm + "\' maxLength=\'30\' /></td>"
	         +	"<td><input type=\'text\' name=\'grp_cd_nm_engs\' id=\'grp_cd_nm_engs\' class=\'text\' value=\'\' size=\'12\' fieldTitle=\'" + title_grp_cd_nm_eng + "\' maxLength=\'30\' /></td>"
	         +	"<td><input type=\'text\' name=\'grp_cd_abbr_nm_engs\' id=\'grp_cd_abbr_nm_engs\' class=\'text\' value=\'\' size=\'12\' fieldTitle=\'" + title_grp_cd_abbr_nm_eng + "\' maxLength=\'30\' /></td>"
	         +	"<td><input type=\'text\' name=\'use_yns\' id=\'use_yns\' class=\'text\' value=\'\' size=\'1\' fieldTitle=\'" + title_use_yn + "\' required=\'*\' maxLength=\'1\' /></td>"
	         +	"<td><input type=\'text\' name=\'commentss\' id=\'commentss\' class=\'text\' value=\'\' size=\'12\' fieldTitle=\'" + title_comments + "\' maxLength=\'100\'/></td>"
		     + "</tr>";
		$('#grpCodeTable').append(html);
		$('#grpCodeTable tr:last td :checkbox[name=chk_grp_cds]').focus();
		
	}

	/**
	* 공통코드 추가
	*/
	function newCode() {

		var frm = document.frmCode;
		var grpCd = frm.select_grp_cd.value;

		$('#resultNotFoundRowCode').remove();
		var html = "<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\">"
	         +	"<td><input type=\'checkbox\' id=\'\' name=\'chk_cds\' class=\'checkbox\' value=\'\'></td>"
	         +	"<td><input type=\'text\' name=\'cds\' id=\'cds\' class=\'text\' value=\'\' size=\'14\' fieldTitle=\'" + title_cd + "\' required=\'*\' maxLength=\'30\' /></td>"
	         +	"<td><input type=\'text\' name=\'cd_nms\' id=\'cd_nms\' class=\'text\' value=\'\' size=\'20\' fieldTitle=\'" + title_cd_nm + "\' required=\'*\' maxLength=\'30\' /></td>"
	         +	"<td>" + grpCd + "</td>"
	         +	"<td><input type=\'text\' name=\'cd_abbr_nms\' id=\'cd_abbr_nms\' class=\'text\' value=\'\' size=\'12\' fieldTitle=\'" + title_cd_abbr_nm + "\' maxLength=\'30\' /></td>"
	         +	"<td><input type=\'text\' name=\'cd_nm_engs\' id=\'cd_nm_engs\' class=\'text\' value=\'\' size=\'12\' fieldTitle=\'" + title_cd_nm_eng + "\' maxLength=\'30\' /></td>"
	         +	"<td><input type=\'text\' name=\'cd_abbr_nm_engs\' id=\'cd_abbr_nm_engs\' class=\'text\' value=\'\' size=\'12\' fieldTitle=\'" + title_cd_abbr_nm_eng + "\' maxLength=\'30\' /></td>"
	         +	"<td><input type=\'text\' name=\'cd_orders\' id=\'cd_orders\' class=\'text\' value=\'\' size=\'3\' fieldTitle=\'" + title_cd_order + "\' required=\'*\' maxLength=\'3\' char=\'n\' /></td>"
	         +	"<td><input type=\'text\' name=\'use_yns\' id=\'use_yns\' class=\'text\' value=\'\' size=\'1\' fieldTitle=\'" + title_use_yn + "\' required=\'*\' maxLength=\'1\' /></td>"
	         +	"<td><input type=\'text\' name=\'commentss\' id=\'commentss\' class=\'text\' value=\'\' size=\'10\' fieldTitle=\'" + title_comments + "\' maxLength=\'100\'/></td>"
	         +	"<td><input type=\'text\' name=\'useman_mng_itm1s\' id=\'useman_mng_itm1s\' class=\'text\' value=\'\' size=\'10\' fieldTitle=\'" + title_useman_mng_itm1 + "\' maxLength=\'30\'/></td>"
	         +	"<td><input type=\'text\' name=\'useman_mng_itm2s\' id=\'useman_mng_itm2s\' class=\'text\' value=\'\' size=\'10\' fieldTitle=\'" + title_useman_mng_itm2 + "\' maxLength=\'30\'/></td>"
	         +	"<td><input type=\'text\' name=\'useman_mng_itm3s\' id=\'useman_mng_itm3s\' class=\'text\' value=\'\' size=\'10\' fieldTitle=\'" + title_useman_mng_itm3 + "\' maxLength=\'30\'/></td>"
	         +	"<td><input type=\'text\' name=\'useman_mng_itm4s\' id=\'useman_mng_itm4s\' class=\'text\' value=\'\' size=\'10\' fieldTitle=\'" + title_useman_mng_itm4 + "\' maxLength=\'30\'/></td>"
	         +	"<td><input type=\'text\' name=\'useman_mng_itm5s\' id=\'useman_mng_itm5s\' class=\'text\' value=\'\' size=\'10\' fieldTitle=\'" + title_useman_mng_itm5 + "\' maxLength=\'30\'/></td>"
	    	 + "</tr>";
		$('#codeTable').append(html);
		$('#codeTable tr:last td :checkbox[name=chk_cds]').focus();
		
	}
	
	/**
	* 그룹코드 등록
	*/
	function insertGrpCode() {

		//등록할려는 코드가 없을경우
		if($('#grpCodeTable tr:not(#resultNotFoundRowGrpCode)').is("tr") != true) {
			alert("<spring:message code='secfw.page.field.code.noGroupCd'/>");
			return;
		}
		
		if(validateForm(document.frmGrpCode) != false) {

			var options = {
				url: "<c:url value='/secfw/code.do?method=insertGrpCode'/>",
				type: "post",
				dataType: "json",
				beforeSubmit: function(formData, form){
					comLayerPopCenter('ProgressLayer1');
					return true;
				},
				success: function(responseText, statusText) {
					progressWinClose();
					alert(responseText.returnMessage);
					
					if(responseText.returnMessage.indexOf('Duplication!') == -1) 
						listGrpCode('N');
				}
			}
			$("#frmGrpCode").ajaxSubmit(options);

		}
	}

	/**
	* 공통코드 등록
	*/
	function insertCode() {

		var frm = document.frmCode;
		var selectGrpCd = frm.select_grp_cd.value;

		if(selectGrpCd == null || selectGrpCd == '') {
			alert("<spring:message code='secfw.page.field.code.regGroupCd'/>");
			return;
		}

		//등록할려는 코드가 없을경우
		if($('#codeTable tr:not(#codeTableHeader,#resultNotFoundRowCode)').is("tr") != true) {
			alert("<spring:message code='secfw.page.field.code.noCd'/>");
			return;
		}
		
		if(validateForm(document.frmCode) != false) {
		
			var options = {
				url: "<c:url value='/secfw/code.do?method=insertCode'/>",
				type: "post",
				dataType: "json",
				beforeSubmit: function(formData, form){
					comLayerPopCenter('ProgressLayer1');
					return true;
				},
				success: function(responseText, statusText) {
					progressWinClose();
					alert(responseText.returnMessage);
					
					if(responseText.returnMessage.indexOf('Duplication!') == -1) 
						listCode(document.frmCode.select_grp_cd.value);

				} 
			}
			$("#frmCode").ajaxSubmit(options);

		}

	}

	/**
	* 그룹코드 삭제
	*/
	function deleteGrpCode() {

		var options = {
			url: "<c:url value='/secfw/code.do?method=deleteGrpCode'/>",
			type: "post",
			dataType: "json",
			beforeSubmit: function(formData, form){
				comLayerPopCenter('ProgressLayer1');
				return true;
			},
			success: function() {
				progressWinClose();
				$('#grpCodeTable input:checked:not(#allCheckGrpCode)').parent().parent().remove();
			}
		}
		$("#frmGrpCode").ajaxSubmit(options);

	}

	/**
	* 공통코드 삭제
	*/
	function deleteCode() {

		var options = {
			url: "<c:url value='/secfw/code.do?method=deleteCode'/>",
			type: "post",
			dataType: "json",
			beforeSubmit: function(formData, form){
				comLayerPopCenter('ProgressLayer1');
				return true;
			},
			success: function() {
				progressWinClose();
				$('#codeTable input:checked:not(#allCheckCode)').parent().parent().remove();
			}
		}
		$("#frmCode").ajaxSubmit(options);

	}
//-->
</script>
</head>
<body>
<div class="contentWrap">
	<div class="content_area">
		<!-- **************************** Form Setting **************************** -->
		<form:form id="frmGrpCode" name="frmGrpCode" method="post" autocomplete="off">
		<input type="hidden" name="menu_id" value="<%=menu_id%>">
		<input type="text" name="menu_id" value="<%=menu_id%>">
		<!-- //**************************** Form Setting **************************** -->

		<!-- Location -->
		<div class="location"><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->
		<!-- Title -->
		<h3><spring:message code="secfw.page.field.code.manageCd"/></h3>
		<!-- //Title -->
		<h4><spring:message code="secfw.page.field.code.groupCd"/></h4>
		<br>
		<!-- Total -->
		<div class="total"><p>Total : <strong><span id="grpCodeTotalCnt"></span></strong></p></div>
		<div class="bt_content_top">
			<a href="javascript:newGrpCode();" class="bt_fn ico_add"><span><spring:message code="secfw.page.field.code.add"/></span></a>
			<a href="javascript:insertGrpCode();" class="bt_fn ico_register"><span><spring:message code="secfw.page.field.code.registration"/></span></a>
			<a href="javascript:deleteGrpCode();" class="bt_fn ico_delete"><span><spring:message code="secfw.page.field.code.delete"/></span></a>
		</div>
		<!-- List -->
		<br>

		<table class="basic_list mz fix">
			<colgroup>
				<col width="30px" align="center"/>
				<col width="130px"/>
				<col width="130px" align="center"/>
				<col width="100px" align="center"/>
				<col width="100px" align="center"/>
				<col width="100px" align="center"/>
				<col width="40px" align="center"/>
				<col align="center"/>
				<col />
			</colgroup>
				<tr id="grpCodeTableHeader">
					<th><input type="checkbox" id="allCheckGrpCode" name="allCheckGrpCode" class="checkbox"></th>
					<th><spring:message code="secfw.page.field.code.groupCd"/></th>
					<th><spring:message code="secfw.page.field.code.groupCdNm"/></th>
					<th><spring:message code="secfw.page.field.code.cdSum"/></th>
					<th><spring:message code="secfw.page.field.code.engNm"/></th>
					<th><spring:message code="secfw.page.field.code.engAbb"/></th>
					<th><spring:message code="secfw.page.field.code.use"/></th>
					<th class="end tR"><spring:message code="secfw.page.field.code.explain"/></th>
					<th class="th_scroll"></th>
				</tr>
		</table>
		<div class="dscrolly_mb" style="height:200px">
			<table id="grpCodeTable" class="basic_list tb_non fix">
				<colgroup>
				<col width="30px" align="center"/>
				<col width="130px"/>
				<col width="130px" align="center"/>
				<col width="100px" align="center"/>
				<col width="100px" align="center"/>
				<col width="100px" align="center"/>
				<col width="40px" align="center"/>
				<col align="center"/>
				</colgroup>
			</table>
		</div>
		</form:form>
		<!-- **************************** Form Setting **************************** -->
		<form:form id="frmCode" name="frmCode" method="post">
			<input type="hidden" name="select_grp_cd" value="">
			<input type="hidden" name="menu_id" value="<%=menu_id%>">
		    <input type="text" name="menu_id" value="<%=menu_id%>">
		<!-- //**************************** Form Setting **************************** -->
		<!-- //List -->
		<br>
		<h4><spring:message code="secfw.page.field.code.commonCd"/></h4>
		<br>
		<!-- Total -->
		<div class="total"><p>Total : <strong><span id="codeTotalCnt"></span></strong></p></div>
		<div class="bt_content_top">
			<a href="javascript:newCode();" class="bt_fn ico_add"><span><spring:message code="secfw.page.field.code.add"/></span></a>
			<a href="javascript:insertCode();" class="bt_fn ico_register"><span><spring:message code="secfw.page.field.code.registration"/></span></a>
			<a href="javascript:deleteCode();" class="bt_fn ico_delete"><span><spring:message code="secfw.page.field.code.delete"/></span></a>
		</div>
		<!-- List -->
		<br>
		<div class="dscrollxy w100" style="height:230px;" >
			<table id="codeTable" class="basic_list fix" style="width:1240px;">
				<colgroup>
				<col width="30px" align="center"/>
				<col width="100px" align="center"/>
				<col align="center"/>
				<col width="110px" align="center"/>
				<col width="100px" align="center"/>
				<col width="100px" align="center"/>
				<col width="100px" align="center"/>
				<col width="40px" align="center"/>
				<col width="40px" align="center"/>
				<col width="80px" align="center"/>
				<col width="80px" align="center"/>
				<col width="80px" align="center"/>
				<col width="80px" align="center"/>
				<col width="80px" align="center"/>
				<col width="80px" align="center"/>
				</colgroup>
				<tr id="codeTableHeader">
					<th><input type="checkbox" id="allCheckCode" name="allCheckCode" class="checkbox"></th>
					<th><spring:message code="secfw.page.field.code.cd"/></th>
					<th><spring:message code="secfw.page.field.code.cdNm"/></th>
					<th><spring:message code="secfw.page.field.code.groupCd"/></th>
					<th><spring:message code="secfw.page.field.code.cdSumNm"/></th>
					<th><spring:message code="secfw.page.field.code.cdEngNm"/></th>
					<th><spring:message code="secfw.page.field.code.cdSumEngNm"/></th>
					<th><spring:message code="secfw.page.field.code.order"/></th>
					<th><spring:message code="secfw.page.field.code.use"/></th>
					<th><spring:message code="secfw.page.field.code.explain"/></th>
					<th><spring:message code="secfw.page.field.code.others1"/></th>
					<th><spring:message code="secfw.page.field.code.others2"/></th>
					<th><spring:message code="secfw.page.field.code.others3"/></th>
					<th><spring:message code="secfw.page.field.code.others4"/></th>
					<th><spring:message code="secfw.page.field.code.others5"/></th>
				</tr>
			</table>
		</div>
		<!-- //List -->
		<!-- Footer -->
		<address>
			<spring:message code="secfw.page.copyright.content.bottom" />
		</address>
		<!-- //Footer -->
		</form:form>
	</div>
</div>

</body>
</html>
