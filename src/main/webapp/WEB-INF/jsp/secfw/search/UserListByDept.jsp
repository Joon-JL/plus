<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page import="com.sds.secframework.dept.dto.DeptForm" %>
<%--
/**
 * 파     일     명 : DeptUserMain.jsp
 * 프로그램명 : 부서조직도 (트리)
 * 설               명 : 
 * 작     성     자 : 금현서
 * 작     성     일 : 2009.11
 */
--%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="secfw.page.field.search.serDeptMem"/></title>
<LINK href="<c:url value='/style/secfw/popup.css' />" type="text/css" rel="stylesheet">
<LINK href="<c:url value='/script/secfw/jquery/jstree/jstree/themes/classic.style.css' />" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/ui.core.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jstree/jquery.tree.js' />"></script>
<script language="javascript">
<!--

	$(function() {
	
		$("#allCheck")
		.click(function() {
			if(($(this).attr("checked"))) {			
				$("input:checkbox[name='chk_id']")
					.each(function(){
						$(this).attr("checked", true);
					});
			} else {
				$("input:checkbox[name='chk_id']")
				.each(function(){
					$(this).attr("checked", false);
				});
			}
		}); 
	
		listDeptTreeAjax();
	});

	/**
	* 부서Tree 조회
	*/
	function listDeptTreeAjax() {
	
				$("#deptTree").tree({
					data	: {
						type	: "json",
						async	: true,
						opts	: {
							method	: "POST",
							url		: "<c:url value='/secfw/dept.do?method=listChildDept' />"
						}
					},
					ui	: {
						theme_name : "classic"
					},
					callback	: {
						onselect	: function(NODE, TREE_OBJ) {
						alert($(NODE).attr("data"));
						},
						error 		: function(TEXT){
							if(TEXT.match('parsererror') != null){
								alert('error1');
								return;
							}
							alert(TEXT);
						}
					}
				});

	}

	/**
	* 메뉴Table 조회
	*/
	function listUserTableAjax(data) {

		var frm = document.frm;
		frm.select_dept_cd.value = data;

		var totalCnt = 0;
		
		var options = {
			url: "<c:url value='/secfw/user.do?method=listUserByDeptCd'/>",
			type: "post",
			dataType: "json",
			async:false,
			success: function(responseText, statusText) {
				$('#userTable tr:not(#userTableHeader)').remove();
				if(responseText != null && responseText.length != 0) {
					$.each(responseText, function(entryIndex, entry) {
						var html = "<tr onMouseOut=\"this.className=\'\';\" onMouseOver=\"this.className=\'selected\'\">"
						         +	"<td><input type=\'checkbox\' id=\'" + entry['user_id'] + "\' name=\'chk_id\' class=\'checkbox\' value=\'" + entry['user_id'] + "\'></td>"
							     +	"<td>" + entry['user_nm'] + "</td>"
							     +	"<td>" + entry['dept_nm'] + "</td>"
							     +	"<td>" + entry['jikgup_nm'] + "</td>"
							     +	"<td>" + entry['office_tel_no'] + "</td>"
							     +       "<input type=hidden name=\'user_id_" + entryIndex + "\'           id=\'user_id_" + entryIndex + "\' value=\'" + entry['user_id'] + "\' />"
							     +       "<input type=hidden name=\'emp_no_" + entryIndex + "\'            id=\'emp_no_" + entryIndex + "\' value=\'" + entry['emp_no'] + "\' />"
							     +       "<input type=hidden name=\'user_nm_" + entryIndex + "\'           id=\'user_nm_" + entryIndex + "\' value=\'" + entry['user_nm'] + "\' />"
							     +       "<input type=hidden name=\'user_nm_eng_" + entryIndex + "\'       id=\'user_nm_eng_" + entryIndex + "\' value=\'" + entry['user_nm_eng'] + "\' />"
							     +       "<input type=hidden name=\'chinese_nm_" + entryIndex + "\'        id=\'chinese_nm_" + entryIndex + "\' value=\'" + entry['chinese_nm'] + "\' />"
							     +       "<input type=hidden name=\'single_id_" + entryIndex + "\'         id=\'single_id_" + entryIndex + "\' value=\'" + entry['single_id'] + "\' />"
							     +       "<input type=hidden name=\'single_epid_" + entryIndex + "\'       id=\'single_epid_" + entryIndex + "\' value=\'" + entry['single_epid'] + "\' />"
							     +       "<input type=hidden name=\'comp_cd_" + entryIndex + "\'           id=\'comp_cd_" + entryIndex + "\' value=\'" + entry['comp_cd'] + "\' />"
							     +       "<input type=hidden name=\'comp_nm_" + entryIndex + "\'           id=\'comp_nm_" + entryIndex + "\' value=\'" + entry['comp_nm'] + "\' />"
							     +       "<input type=hidden name=\'comp_nm_eng_" + entryIndex + "\'       id=\'comp_nm_eng_" + entryIndex + "\' value=\'" + entry['comp_nm_eng'] + "\' />"
							     +       "<input type=hidden name=\'business_cd_" + entryIndex + "\'       id=\'business_cd_" + entryIndex + "\' value=\'" + entry['business_cd'] + "\' />"
							     +       "<input type=hidden name=\'business_nm_" + entryIndex + "\'       id=\'business_nm_" + entryIndex + "\' value=\'" + entry['business_nm'] + "\' />"
							     +       "<input type=hidden name=\'business_nm_eng_" + entryIndex + "\'   id=\'business_nm_eng_" + entryIndex + "\' value=\'" + entry['business_nm_eng'] + "\' />"
							     +       "<input type=hidden name=\'dept_cd_" + entryIndex + "\'           id=\'dept_cd_" + entryIndex + "\' value=\'" + entry['dept_cd'] + "\' />"
							     +       "<input type=hidden name=\'dept_nm_" + entryIndex + "\'           id=\'dept_nm_" + entryIndex + "\' value=\'" + entry['dept_nm'] + "\' />"
							     +       "<input type=hidden name=\'origin_dept_cd_" + entryIndex + "\'    id=\'origin_dept_cd_" + entryIndex + "\' value=\'" + entry['origin_dept_cd'] + "\' />"
							     +       "<input type=hidden name=\'origin_dept_nm_" + entryIndex + "\'    id=\'origin_dept_nm_" + entryIndex + "\' value=\'" + entry['origin_dept_nm'] + "\' />"
							     +       "<input type=hidden name=\'jikgun_cd_" + entryIndex + "\'         id=\'jikgun_cd_" + entryIndex + "\' value=\'" + entry['jikgun_cd'] + "\' />"
							     +       "<input type=hidden name=\'jikgun_nm_" + entryIndex + "\'         id=\'jikgun_nm_" + entryIndex + "\' value=\'" + entry['jikgun_nm'] + "\' />"
							     +       "<input type=hidden name=\'jikgun_nm_eng_" + entryIndex + "\'     id=\'jikgun_nm_eng_" + entryIndex + "\' value=\'" + entry['jikgun_nm_eng'] + "\' />"
							     +       "<input type=hidden name=\'jikgup_cd_" + entryIndex + "\'         id=\'jikgup_cd_" + entryIndex + "\' value=\'" + entry['jikgup_cd'] + "\' />"
							     +       "<input type=hidden name=\'jikgup_nm_" + entryIndex + "\'         id=\'jikgup_nm_" + entryIndex + "\' value=\'" + entry['jikgup_nm'] + "\' />"
							     +       "<input type=hidden name=\'jikgup_nm_eng_" + entryIndex + "\'     id=\'jikgup_nm_eng_" + entryIndex + "\' value=\'" + entry['jikgup_nm_eng'] + "\' />"
							     +       "<input type=hidden name=\'grp_jikgup_cd_" + entryIndex + "\'     id=\'grp_jikgup_cd_" + entryIndex + "\' value=\'" + entry['grp_jikgup_cd'] + "\' />"
							     +       "<input type=hidden name=\'grp_jikgup_nm_" + entryIndex + "\'     id=\'grp_jikgup_nm_" + entryIndex + "\' value=\'" + entry['grp_jikgup_nm'] + "\' />"
							     +       "<input type=hidden name=\'grp_jikgup_nm_eng_" + entryIndex + "\' id=\'grp_jikgup_nm_eng_" + entryIndex + "\' value=\'" + entry['grp_jikgup_nm_eng'] + "\' />"
							     +       "<input type=hidden name=\'jikmu_cd_" + entryIndex + "\'          id=\'jikmu_cd_" + entryIndex + "\' value=\'" + entry['jikmu_cd'] + "\' />"
							     +       "<input type=hidden name=\'jikmu_nm_" + entryIndex + "\'          id=\'jikmu_nm_" + entryIndex + "\' value=\'" + entry['jikmu_nm'] + "\' />"
							     +       "<input type=hidden name=\'jikmu_nm_eng_" + entryIndex + "\'      id=\'jikmu_nm_eng_" + entryIndex + "\' value=\'" + entry['jikmu_nm_eng'] + "\' />"
							     +       "<input type=hidden name=\'jikchek_cd_" + entryIndex + "\'        id=\'jikchek_cd_" + entryIndex + "\' value=\'" + entry['jikchek_cd'] + "\' />"
							     +       "<input type=hidden name=\'birth_ymd_" + entryIndex + "\'         id=\'birth_ymd_" + entryIndex + "\' value=\'" + entry['birth_ymd'] + "\' />"
							     +       "<input type=hidden name=\'solar_yn_" + entryIndex + "\'          id=\'solar_yn_" + entryIndex + "\' value=\'" + entry['solar_yn'] + "\' />"
							     +       "<input type=hidden name=\'office_tel_no_" + entryIndex + "\'     id=\'office_tel_no_" + entryIndex + "\' value=\'" + entry['office_tel_no'] + "\' />"
							     +       "<input type=hidden name=\'mobile_no_" + entryIndex + "\'         id=\'mobile_no_" + entryIndex + "\' value=\'" + entry['mobile_no'] + "\' />"
							     +       "<input type=hidden name=\'status_" + entryIndex + "\'            id=\'status_" + entryIndex + "\' value=\'" + entry['status'] + "\' />"
							     +       "<input type=hidden name=\'last_locale_" + entryIndex + "\'       id=\'last_locale_" + entryIndex + "\' value=\'" + entry['last_locale'] + "\' />"			
						$('#userTable').append(html);
						totalCnt = entry['total_cnt'];
					});
				} else {
					var html = "<tr id=\'resultNotFoundRow\'><td colspan=5 align=center><spring:message code="secfw.msg.succ.noResult" /></td></tr>";
					$('#userTable').append(html);
				}

				$('#totalCnt').text(totalCnt);
			}
		}
		$("#frm").ajaxSubmit(options);

		redrawTree();
	}
//-->
</script>
</head>
<body class="pop">
<form:form id="frm" name="frm" method="post" autocomplete="off">
<input type="hidden" name="method"   value="">
<input type="hidden" name="select_dept_cd" value="" />
<!-- Popup Top -->
<h1 class="pop_top"><spring:message code="secfw.page.field.search.searchDept"/></h1>
<!-- //Popup Top -->
<!-- Popup Body-->
<div class="pop_body">
	<div class="pop_area">
		<!-- Popup Detail -->
		<div class="dscrolly 600">
			<div class="pop_content">
				<table class="all">
					<colgroup>
					<col width="330px"/>
					<col/>
					</colgroup>
					<tr>
						<td class="vt"><!-- Treeview -->
							<div id="deptTree" class="pop_treeview_authMenu" style="height:454px"></div>
							<!-- //Treeview --></td>
						<td class="vt">
							<!-- List -->
							<table class="basic_list mz fix">
								<colgroup>
								<col width="10%" align="center"/>
								<col />
								<col width="10%" align="center"/>
								<col width="10%" align="center"/>
								<col width="10%" align="center"/>
								</colgroup>
								<tr>
									<th><input type="checkbox" id="allCheck" name="allCheck" class="checkbox"></th>
									<th><spring:message code="secfw.page.field.search.nm"/></th>
									<th><spring:message code="secfw.page.field.search.department"/></th>
									<th><spring:message code="secfw.page.field.search.position"/></th>
									<th class="end"><spring:message code="secfw.page.field.search.comPhone"/></th>
									<th class="th_scroll"></th>
								</tr>
							</table>
						<div class="dscrolly_mb" style="height:425px">
							<table id="userTable" class="basic_list tb_non fix">
									<colgroup>
										<col width="10%" align="center"/>
										<col width="60" align="center"/>
										<col width="60" align="center"/>
										<col width="60" align="center"/>
										<col align="center"/>									
									</colgroup>
							</table>
						</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<!-- //Popup Detail -->
	</div>
</div>
<!-- //Popup Body -->
<!-- Popup Footer -->
<div class="pop_footer">
	<!-- Function Button -->
	<a href="#" class="bt_all_b"><span><img src="<c:url value='/images/secfw/ico/ico_confirm.gif' />"><spring:message code="secfw.page.field.search.confirm"/></span></a>
	<a href="javascript:self.close()" class="bt_all_w"><span><img src="<c:url value='/images/secfw/ico/ico_cancel.gif' />"><spring:message code="secfw.page.field.search.cancel"/></span></a>
	<!-- // Function Button -->
</div>
<!-- //Popup Footer -->
</form:form>
</body>
</html>