<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap"%>
<%@ page import="com.sds.secframework.code.dto.ActscodeForm" %>

<%@ page import="org.springframework.web.context.*"%>
<%@ page import="org.springframework.web.context.support.*"%>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil"%>

<%--
/**
 * 파     일     명 : ActscodeManage.jsp
 * 프로그램명 : 공통코드 관리 메인 화면
 * 설               명 : 
 * 작     성     자 : 최준영
 * 작     성     일 : 2011.04.13
 */
--%>
<%
	//ServletContext ctx = pageContext.getServletContext();	
	//WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);
	//FasooService fasooService = (FasooService)wac.getBean("acts.cmm.fasooService");

	ArrayList listCode		= (ArrayList)request.getAttribute("listCode");
	ActscodeForm command	= (ActscodeForm)request.getAttribute("command") == null ? new ActscodeForm() : (ActscodeForm)request.getAttribute("command");
	String returnMessage	= StringUtil.bvl((String)request.getAttribute("returnMessage"), "");
	
%>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="secfw.page.title.auth.AuthList" /></title>
<link href="<%=CSS%>/las.css"    type="text/css" rel="stylesheet">
<link href="<c:url value='/style/secfw/common.css' />" type="text/css" rel="stylesheet">
<link href="<c:url value='/style/secfw/common_acts.css' />" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.hoverIntent.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/tools/jquery.tools.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/acts/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.tablednd_0_5.js' />"></script>

<script language="javascript">
var dupSubmit = true;
var choiceFolder = "";

function showHide(iconPos, cdLevel, sysCd, grpCd) {
	if(dupSubmit) {
		if(document.all["plusIcon_" + sysCd + "_" + grpCd + "_" + iconPos].style.display == 'none') {
			document.all["plusIcon_" + sysCd + "_" + grpCd + "_" + iconPos].style.display = '';
			document.all["minusIcon_" + sysCd + "_" + grpCd + "_" + iconPos].style.display = 'none';
			document.all["DIV_" + sysCd + "_" + grpCd + "_" + iconPos].style.display = 'none';
		} else {
			document.all["plusIcon_" + sysCd + "_" + grpCd + "_" + iconPos].style.display = 'none';
			document.all["minusIcon_" + sysCd + "_" + grpCd + "_" + iconPos].style.display = '';
			if(document.all["DIV_" + sysCd + "_" + grpCd + "_" + iconPos].innerHTML == "") {
				subCode(iconPos, cdLevel, sysCd, grpCd);
			} else {
				document.all["DIV_" + sysCd + "_" + grpCd + "_" + iconPos].style.display = '';
			}
		}
	}
}

function subCode(iconPos, cdLevel, sysCd, grpCd) {
	document.searchForm.grp_cd.value = iconPos;
	document.searchForm.srch_sys_cd.value = sysCd;
	cdLevel++;
	var options = {
			url: "/secfw/actsCode.do?method=listCodeAjax",
			type: "post",
			dataType: "json",
			beforeSubmit: function(formData, form) {
				dupSubmit = false;
				return true;
			},
			success: function(responseText) {
				if(responseText != null && responseText.length != 0) {
					var html = "<table style='width:100%'  cellpadding='0' cellspacing='0'>";
					$.each(responseText, function(entryIndex, entry) {
						html += "<tr><td width='100%' nowrap>";
						for(var iLen = 1; iLen < cdLevel; iLen++) {
							html += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
						}
						if(entry['SUB_CNT'] > 0) {
							html += "<img border=0 align='absmiddle' src='/images/secfw/dtree/nolines_plus.gif' id='plusIcon_" + entry['SYS_CD'] + "_" + entry['GRP_CD'] + "_" + entry['CD'] + "' onClick='JavaScript:showHide(\"" + entry['CD'] + "\", " + cdLevel + ", \"" + entry['SYS_CD'] + "\", \"" + entry['GRP_CD'] + "\");' style='cursor:hand'>";
							html += "<img border=0 align='absmiddle' src='/images/secfw/dtree/nolines_minus.gif' id='minusIcon_" + entry['SYS_CD'] + "_" + entry['GRP_CD'] + "_" + entry['CD'] + "' onClick='JavaScript:showHide(\"" + entry['CD'] + "\", " + cdLevel + ", \"" + entry['SYS_CD'] + "\", \"" + entry['GRP_CD'] + "\");' style='cursor:hand;display:none'>";
							html += "<img border=0 align='absmiddle' src='/images/acts/sub/i_treefolder1.gif' id='closeFolder_" + entry['SYS_CD'] + "_" + entry['GRP_CD'] + "_" + entry['CD'] + "' onClick='JavaScript:listSubCode(\"" + entry['CD'] + "\", \"" + entry['SYS_CD'] + "\", \"" + entry['GRP_CD'] + "\", \"" + entry['CD_NM'] + "\");' style='cursor:hand'>";
							html += "<img border=0 align='absmiddle' src='/images/acts/sub/i_treefolder2.gif' id='openFolder_" + entry['SYS_CD'] + "_" + entry['GRP_CD'] + "_" + entry['CD'] + "' style='display:none'>";
							html += "<span id='SP_" + entry['SYS_CD'] + "_" + entry['GRP_CD'] + "_" + entry['CD'] + "'><a href='JavaScript:listSubCode(\"" + entry['CD'] + "\", \"" + entry['SYS_CD'] + "\", \"" + entry['GRP_CD'] + "\", \"" + entry['CD_NM'] + "\");'>" + entry['CD_NM'] + "</a></span>";
							html += "<div id='DIV_" + entry['SYS_CD'] + "_" + entry['GRP_CD'] + "_" + entry['CD'] + "' style='display:none'></div>";
						} else {
							html += "<img border=0 align='absmiddle' src='/images/secfw/dtree/nolines_minus.gif' id='minusIcon_" + entry['SYS_CD'] + "_" + entry['GRP_CD'] + "_" + entry['CD'] + "' >";
							html += "<img border=0 align='absmiddle' src='/images/acts/sub/i_treefolder1.gif' id='closeFolder_" + entry['SYS_CD'] + "_" + entry['GRP_CD'] + "_" + entry['CD'] + "' onClick='JavaScript:listSubCode(\"" + entry['CD'] + "\", \"" + entry['SYS_CD'] + "\", \"" + entry['GRP_CD'] + "\", \"" + entry['CD_NM'] + "\");' style='cursor:hand'>";
							html += "<img border=0 align='absmiddle' src='/images/acts/sub/i_treefolder2.gif' id='openFolder_" + entry['SYS_CD'] + "_" + entry['GRP_CD'] + "_" + entry['CD'] + "' style='display:none'>";
							html += "<span id='SP_" + entry['SYS_CD'] + "_" + entry['GRP_CD'] + "_" + entry['CD'] + "'><a href='JavaScript:listSubCode(\"" + entry['CD'] + "\", \"" + entry['SYS_CD'] + "\", \"" + entry['GRP_CD'] + "\", \"" + entry['CD_NM'] + "\");'>" + entry['CD_NM'] + "</a></span>";
						}
						html += "</td></tr>";
					});
					html += "</table>";

					$("#DIV_" + sysCd + "_" + grpCd + "_" + iconPos).html(html);
					document.all["DIV_" + sysCd + "_" + grpCd + "_" + iconPos].style.display = '';
				}
				dupSubmit = true;
			}
		}

	if(dupSubmit) {
		$("#searchForm").ajaxSubmit(options);
	}
}

function listSubCode(iconPos, sysCd, grpCd, editTitle) {
	var frm = document.searchForm;

	document.all.editTitle.innerHTML = editTitle;
	
	frm.grp_cd.value = iconPos;
	frm.srch_sys_cd.value = sysCd;
	var options = {
			url: "/secfw/actsCode.do?method=listCodeAjax",
			type: "post",
			dataType: "json",
			beforeSubmit: function(formData, form) {
				dupSubmit = false;
				return true;
			},
			success: function(responseText) {
				$('#CodeTableInfo tr').remove();
				if(responseText != null && responseText.length != 0) {
					$.each(responseText, function(entryIndex, entry) {
						var html = "<tr class='tr_list' onmouseover=\"this.className='tr_list_over';\" onmouseout=\"this.className='tr_list';\">"
							+ "<td align='center' width='30px'><input type='checkbox' name='del_cd_arr' id='del_cd_arr' value='" + entry['CD'] + "'></td>"
							+ "<td align='center' width='90px'>" + entry['CD'] + "</td>"
							+ "<td width='200px'><input class='input' type='text' name='cd_nm_arr' id='cd_nm_arr' value='" + entry['CD_NM'] + "' style='width:90%'></td>"
							+ "<td><input class='input' type='text' name='cd_nm_eng_arr' id='cd_nm_eng_arr' value='" + entry['CD_NM_ENG'] + "' style='width:90%'></td>"
							+ "<input type='hidden' name='cd_arr' id='cd_arr' value='" + entry['CD'] + "'>"
							+ "</tr>";
						$('#CodeTableInfo').append(html);
					});
				} else {
					alert("<spring:message code='secfw.page.field.code.noLowlevelCd'/>");
				}
				dupSubmit = true;

				$("#CodeTableInfo tbody").tableDnD();
			}
		}

	if(dupSubmit) {
		$("#searchForm").ajaxSubmit(options);
	}
}

function addRow() {
	var html = "<tr class='tr_list' onmouseover=\"this.className='tr_list_over';\" onmouseout=\"this.className='tr_list';\">"
		+ "<td align='center' width='30px'><input type='checkbox' name='del_cd_arr' id='del_cd_arr' value=''></td>"
		+ "<td align='center' width='90px'>&nbsp;</td>"
		+ "<td align='center' width='200px'><input class='input' type='text' name='cd_nm_arr' id='cd_nm_arr' value='' style='width:90%'></td>"
		+ "<td align='center' class='end'><input class='input' type='text' name='cd_nm_eng_arr' id='cd_nm_eng_arr' value='' style='width:90%'></td>"
		+ "<input type='hidden' name='cd_arr' id='cd_arr' value='genCode'>"
		+ "</tr>";
	$('#CodeTableInfo').append(html);
}

function delRow() {
	$('#CodeTableInfo input:checked').parent().parent().remove();
}

function confirmSave() {
	var frm = document.searchForm;

	if(frm.cd_arr != null) {
		if(frm.cd_arr.length == undefined) {
			frm.cd_nm_arr.value = comTrim(frm.cd_nm_arr.value);
			frm.cd_nm_eng_arr.value = comTrim(frm.cd_nm_eng_arr.value);

			if(frm.cd_nm_arr.value == "") {
				alert("<spring:message code='secfw.page.field.code.mustDoCdNm'/>");
				frm.cd_nm_arr.focus();
				return;
			} else if(!checkMaxLength(frm.cd_nm_arr.value, 100)) {
				alert("<spring:message code='secfw.page.field.code.cdNmNotOver100byte'/>");
				frm.cd_nm_arr.focus();
				return;
			} else if(frm.cd_nm_eng_arr.value == "") {
				alert("<spring:message code='secfw.page.field.code.mustDoCdEngNm'/>");
				frm.cd_nm_eng_arr.focus();
				return;
			} else if(!checkMaxLength(frm.cd_nm_eng_arr.value, 100)) {
				alert("<spring:message code='secfw.page.field.code.notCdEngNm'/>");
				frm.cd_nm_eng_arr.focus();
				return;
			}
		} else {
			for(var len=0; len<frm.cd_arr.length; len++) {
				frm.cd_nm_arr[len].value = comTrim(frm.cd_nm_arr[len].value);
				frm.cd_nm_eng_arr[len].value = comTrim(frm.cd_nm_eng_arr[len].value);

				if(frm.cd_nm_arr[len].value == "") {
					alert("<spring:message code='secfw.page.field.code.mustDoCdNm'/>");
					frm.cd_nm_arr[len].focus();
					return;
				} else if(!checkMaxLength(frm.cd_nm_arr[len].value, 100)) {
					alert("<spring:message code='secfw.page.field.code.cdNmNotOver100byte'/>");
					frm.cd_nm_arr[len].focus();
					return;
				} else if(frm.cd_nm_eng_arr[len].value == "") {
					alert("<spring:message code='secfw.page.field.code.mustDoCdEngNm'/>");
					frm.cd_nm_eng_arr[len].focus();
					return;
				} else if(!checkMaxLength(frm.cd_nm_eng_arr[len].value, 100)) {
					alert("<spring:message code='secfw.page.field.code.notCdEngNm'/>");
					frm.cd_nm_eng_arr[len].focus();
					return;
				}
			}
		}
	}

	if(confirm("<spring:message code='secfw.page.field.code.wannaSave'/>")) {
		frm.action = "<c:url value='/acts/cmm/actscode.do' />";
		frm.target = "_self";
		frm.method.value = "saveCode";
		frm.submit();
	}
}

function allSelected() {
	var frm = document.searchForm;

	if(frm.del_cd_arr != null) {
		if(frm.del_cd_arr.length == undefined) {
			if(frm.allChoice.checked == true) {
				frm.del_cd_arr.checked = true;
			} else {
				frm.del_cd_arr.checked = false;
			}
		} else {
			if(frm.allChoice.checked == true) {
				for(var i=0; i<frm.del_cd_arr.length; i++) {
					frm.del_cd_arr[i].checked = true;
				}
			} else {
				for(var i=0; i<frm.del_cd_arr.length; i++) {
					frm.del_cd_arr[i].checked = false;
				}
			}
		}
	}
}

function showMessage() {
<%
	if(!returnMessage.equals("")) {
%>
		alert("<%=returnMessage%>");
<%
	}
%>
}
</script>
</head>


<body onLoad="showMessage();" class='overflow_y'>
<div id="wrap">
	<div id="container">
	
		<!-- location -->
		<div class="location"><IMG SRC="<%=IMAGE%>/icon/ico_home.gif" BORDER="0" alt="Home" title="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //location -->
		
		<!-- title -->
		<div class="title">
			<h3><spring:message code="secfw.page.field.code.commonCd"/></h3>
		</div>
		<!-- //title -->
	
		
		<!-- content -->
		<div id="content">
			<div class="newstyle-area">
				
		


   
      <!-- 서브 타이틀 끝 -->
      <!-- td_pad30 시작 -->
	  <!-- 
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr> 
          <td class="td_pad30">&nbsp;</td>
        </tr>
      </table>

      <table width="100%" border="0" cellspacing="1" cellpadding="0" class="sertb_brdr">
        <tr> 
          <td width="120" nowrap class="ser_ti">코드검색</td>
          <td width="*" nowrap class="ser_td">
             <table border="0"><tr><td>
          		<input type="text" name="textfield" class="inputF" style="width:200">
          	 </td><td>
	              <table border="0" cellspacing="0" cellpadding="0">
                	<tr> 
	                  <td nowrap class=btn_left>&nbsp;</td>
	                  <td nowrap class=btn>검색</td>
	                  <td nowrap class=btn_right>&nbsp;</td>
	                </tr>
	              </table>
          	</td></tr></table>
          </td>
        </tr>
      </table>
       -->
      <!-- table 끝 -->

     
      <!-- folder 시작 -->
      <table width="100%" height="420px" border="0" cellpadding="0" cellspacing="0" class="boxB" id="codeTree" >
        <tr> 
          <form name="searchForm" id="searchForm" method="post" autocomplete="off">
          <input type="hidden" name="method" id="method" >
          <input type="hidden" name="grp_cd" id="grp_cd" >
          <input type="hidden" name="cd" id="cd" >
          <input type="hidden" name="srch_sys_cd" id="srch_sys_cd" >
          <td valign="top" style="padding:0px; width:330px">
		  <!-- 폴더 테이블 시작 -->
		    <div style="width:300px; margin:26px 0 20px 10px; height:387px; overflow: auto; background-color: #fff; border:1px solid #d5d5d5; ">
              <table width="100%" border="0" cellpadding="0" cellspacing="0">
<%
			String cd = "";
			String cdNm = "";
			int subCnt = 0;
			String sys_cd = "";
			String grpCd = "";
			if(listCode != null && listCode.size() > 0) {
				for(int idx=0; idx<listCode.size(); idx++) {
					ListOrderedMap lom = (ListOrderedMap)listCode.get(idx);
					
					cd = StringUtil.bvl((String)lom.get("CD"), "");
					cdNm = StringUtil.bvl((String)lom.get("CD_NM"), "");
					subCnt = ((Integer)lom.get("SUB_CNT")).intValue();
					sys_cd = StringUtil.bvl((String)lom.get("SYS_CD"), "");
					grpCd = StringUtil.bvl((String)lom.get("GRP_CD"), "");
					
					out.print("<tr><td width='100%' nowrap>");
					if(subCnt > 0) {
						out.print("<img border=0 align='absmiddle' src='/images/secfw/dtree/nolines_plus.gif' id='plusIcon_" + sys_cd + "_" + grpCd + "_" + cd + "' onClick='JavaScript:showHide(\"" + cd + "\", 1, \"" + sys_cd + "\", \"" + grpCd + "\");' style='cursor:hand'>");
						out.print("<img border=0 align='absmiddle' src='/images/secfw/dtree/nolines_minus.gif' id='minusIcon_" + sys_cd + "_" + grpCd + "_" + cd + "' onClick='JavaScript:showHide(\"" + cd + "\", 1, \"" + sys_cd + "\", \"" + grpCd + "\");' style='cursor:hand;display:none'>");
						out.print("<img border=0 align='absmiddle' src='/images/acts/sub/i_treefolder1.gif' id='closeFolder_" + sys_cd + "_" + grpCd + "_" + cd + "' onClick='JavaScript:listSubCode(\"" + cd + "\", \"" + sys_cd + "\", \"" + grpCd + "\", \"" + cdNm + "\");' style='cursor:hand'>");
						out.print("<img border=0 align='absmiddle' src='/images/acts/sub/i_treefolder2.gif' id='openFolder_" + sys_cd + "_" + grpCd + "_" + cd + "' style='display:none'>");
						out.print("<span id='SP_" + sys_cd + "_" + grpCd + "_" + cd + "'><a href='JavaScript:listSubCode(\"" + cd + "\", \"" + sys_cd + "\", \"" + grpCd + "\", \"" + cdNm + "\");'>" + cdNm + "</a></span>");
						out.print("<div id='DIV_" + sys_cd + "_" + grpCd + "_" + cd + "' style='display:none'></div>");
					} else {
						out.print("<img border=0 align='absmiddle' src='/images/secfw/dtree/nolines_minus.gif' id='minusIcon_" + sys_cd + "_" + grpCd + "_" + cd + "' >");
						out.print("<img border=0 align='absmiddle' src='/images/acts/sub/i_treefolder1.gif' id='closeFolder_" + sys_cd + "_" + grpCd + "_" + cd + "' onClick='JavaScript:listSubCode(\"" + cd + "\", \"" + sys_cd + "\", \"" + grpCd + "\", \"" + cdNm + "\");' style='cursor:hand'>");
						out.print("<img border=0 align='absmiddle' src='/images/acts/sub/i_treefolder2.gif' id='openFolder_" + sys_cd + "_" + grpCd + "_" + cd + "' style='display:none'>");
						out.print("<span id='SP_" + sys_cd + "_" + grpCd + "_" + cd + "'><a href='JavaScript:listSubCode(\"" + cd + "\", \"" + sys_cd + "\", \"" + grpCd + "\", \"" + cdNm + "\");'>" + cdNm + "</a></span>");
					}
					out.println("</td></tr>");
				}
			}
%>
              </table>
            </div>
          </td>
         
          <td valign="top" style="padding:0;width:*">

            <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td style="padding:0px">
                <table width="100%" border="0" cellspacing="0" cellpadding="0" style='padding:0'>
                    <tr> 
                      <td width="100%"><b><font color="333333"><span id='editTitle'></span></font></b></td>
                      <td class="btn_padT">
                      	<a href="JavaScript:addRow();" class="bt_fn ico_add"><span><spring:message code="secfw.page.field.code.add"/></span></a>
                      </td>
                      <td class="btn_padT">
                      	<a href="JavaScript:delRow();" class="bt_fn ico_delete"><span><spring:message code="secfw.page.field.code.delete"/></span></a>
                      </td>
                      <td class="btn_padT">
                      	<a href="JavaScript:confirmSave();" class="bt_fn ico_save"><span><spring:message code="secfw.page.field.code.save"/></span></a>
                      </td>
                    </tr>
                  </table></td>
              </tr>
              
              <tr>
                <td height="100%" valign="top" style="padding:2px 0px 0 0px">
				<!--- 아이프레임으로 들어갈 곳 --->
				
				<table  class="basic_list mz" style='width:100%'>
					<tr id="CodeTableHeader">
						<th class="tb_ti" width='30px'><input type='checkbox' name='allChoice' id='allChoice' onClick='JavaScript:allSelected();'></th>
						<th class="tb_ti" width='90px'><spring:message code="secfw.page.field.code.cd"/></th>
						<th class="tb_ti" width='200px'><spring:message code="secfw.page.field.code.cdNm"/></td>
						<th class="tb_ti end"><spring:message code="secfw.page.field.code.cdEngNm"/></th>
						<th class="tb_ti th_scroll"></th>
					</tr>
				</table>
				<div class="dscrolly_mb" style="height:350px; width:100%">
				
				<table width="100%" id="CodeTableInfo" class="basic_list tb_non mz fix" >
				
                </table>
                </div>
			    </td>
              </tr>
            </table>

          </td>
          </form>
        </tr>
      </table> 
      <!-- folder 끝 -->



</div>
		</div>
		
		<!-- footer -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
</div>
</body>
</html>
