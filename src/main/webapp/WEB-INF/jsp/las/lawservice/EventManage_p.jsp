<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>
<%@ page import="com.sds.secframework.common.util.Token"%>

<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%--
/**
 * 파  일  명 : EventManage_p.jsp
 * 프로그램명 : 법무시스템 : 비용귀속 부서 선택 팝업
 * 설      명 : 사건의 비용귀속 부서정보를 부모창에 전달한다.
 * 작  성  자 : 박병주
 * 작  성  일 : 2011.09
 */
 --%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.main.title" /></title>
<LINK href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">
<LINK href="<%=CSS%>/popup.css" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript">
<!--
	
	var dupSubmit = true;
	var choiceDept = "";
	var searchDept = "";
		
	/**
	* 부서선택 중복 체크
	*/
	function setSelectListInfo(retDeptCd, retInDeptCd, retDeptNm){
		
		var allDept = document.all.tags("OPTION");
		var checkedNum = 0;
		
		for(var i=0; i < allDept.length ; i++) {
			if(allDept(i).value == retInDeptCd){
				checkedNum = checkedNum + 1;
			}
		}
		
		if(checkedNum == 0){
			addRow(retDeptCd,retInDeptCd,retDeptNm);
		} else {
			alert("<spring:message code='las.page.msg.lawservice.alreadydept' />");
			return;
		}		
	}
	
	/**
	* 부서추가
	*/
	function addRow(retDeptCd,retInDeptCd,retDeptNm) {	
	
		$('#collist').append("<option value=" + retInDeptCd + " id='" + retDeptCd + "'>" + retDeptNm + "</option>\n");
	}
	
	/**
	* 선택 부서 상향 이동 
	*/
	function pullUpRow() {
		
		var rNum = $("#collist option").index($("#collist option:selected"));
		
		 $("#collist option").removeAttr("selected");
		
 		var temp_html_0_text = $("#collist option:eq(" + (rNum-1) + ")").text();
 		var temp_html_0_val = $("#collist option:eq(" + (rNum-1) + ")").val();
 		var temp_html_0_id = $("#collist option:eq(" + (rNum-1) + ")").attr("id");
 		var temp_html_1_text = $("#collist option:eq(" + rNum + ")").text();
		var temp_html_1_val = $("#collist option:eq(" + rNum + ")").val();
		var temp_html_1_id = $("#collist option:eq(" + rNum + ")").attr("id");		
		
		$("#collist option:eq(" + rNum + ")").text(temp_html_0_text);
		$("#collist option:eq(" + rNum + ")").val(temp_html_0_val);
		$("#collist option:eq(" + rNum + ")").attr("id",temp_html_0_id);
		$("#collist option:eq(" + (rNum - 1) + ")").text(temp_html_1_text);
		$("#collist option:eq(" + (rNum - 1) + ")").val(temp_html_1_val);
		$("#collist option:eq(" + (rNum - 1) + ")").attr("id",temp_html_1_id);
		
		$("#collist option:eq(" + (rNum - 1) + ")").attr("selected", "selected"); 

	}
	
	/**
	* 선택 부서 하향 이동
	*/
	function pullDownRow() {
		
		var rNum = $("#collist option").index($("#collist option:selected"));
		var last_rNum = $("#collist option").index($("#collist option:last"));
		
		if(rNum != last_rNum){
			
			$("#collist option").removeAttr("selected");			 
		 		
			var temp_html_0_text = $("#collist option:eq(" + rNum + ")").text();
		 	var temp_html_0_val = $("#collist option:eq(" + rNum + ")").val();
		 	var temp_html_0_id = $("#collist option:eq(" + rNum + ")").attr("id");
		 	var temp_html_1_text = $("#collist option:eq(" + (rNum+1) + ")").text();
			var temp_html_1_val = $("#collist option:eq(" + (rNum+1) + ")").val();
			var temp_html_1_id = $("#collist option:eq(" + (rNum+1) + ")").attr("id");				
			
			$("#collist option:eq(" + (rNum+1) + ")").text(temp_html_0_text);
			$("#collist option:eq(" + (rNum+1) + ")").val(temp_html_0_val);
			$("#collist option:eq(" + (rNum+1) + ")").attr("id",temp_html_0_id);
			$("#collist option:eq(" + rNum + ")").text(temp_html_1_text);
			$("#collist option:eq(" + rNum + ")").val(temp_html_1_val);
			$("#collist option:eq(" + rNum + ")").attr("id",temp_html_1_id);
			
			$("#collist option:eq(" + (rNum+1) + ")").attr("selected", "selected"); 
		}
	}
	
	/**
	* 선택 부서 삭제 
	*/
	function removeRow() {	
	
		$("#collist option:selected").remove();   
	}
	
	/**
	* 리스트박스 초기화 
	*/
	function removeAllRow() {	
		
		$("#collist").each(function(){   
			       $("#collist option").attr("selected","selected");  		      
		 });  
	
		$("#collist option:selected").remove();   
	}		
	
	/**
	* 부모창에 데이타 세팅 
	*/
	function setOpenerDeptInfo(){
		
		var allDept = document.all.tags("OPTION");		
		var selected_cd = '';
		var selected_nm = '';
		var selected_id = '';
		var selected_nm_alone = '';
		var t = 0;
		var str_msg = '';
		
		for(var i=0; i < allDept.length ; i++) {			
				t = t + 1;				
				if(t > 1){
					selected_nm = selected_nm + '/' + allDept(i).text;
					selected_cd = selected_cd + '/' + allDept(i).value;
					selected_id = selected_id + '/' + allDept(i).id;
				} else {
					selected_nm = allDept(i).text;
					selected_cd =  allDept(i).value;
					selected_id =  allDept(i).id;
				}				
		}
		
		if(t > 0)
			str_msg = allDept(0).text + "<spring:message code='las.page.msg.lawservice.deptnum1' />" + (t-1) + "<spring:message code='las.page.msg.lawservice.deptnum2' />";
		
		opener.frm.dept_select.value = "0";
		opener.frm.intnl_dept.value = str_msg;		
		opener.frm.intnl_dept_cd.value = selected_cd;
		opener.frm.dept_nm.value = selected_nm;
		opener.frm.grp_dept_cd.value = selected_id;
		
		opener.frm.dept_nm.alt = selected_nm;
				
		self.close();		
	}

//-->
</script>
</head>
<body class="pop">
<form:form name="searchForm" id="searchForm" autocomplete="off">
<input type="hidden" id="method" name="method" value="" />
<input type="hidden" id="menu_id" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" id="curPage" name="curPage" value="<c:out value='${command.curPage}'/>" />

<input type="hidden" id="up_dept_cd" name="up_dept_cd" value="" />
<!-- header -->
<h1><spring:message code="las.page.field.lawservice.srvcostdept"/>&nbsp;<spring:message code="las.page.field.lawservice.multisel" /><span class="close" onclick="javascript:self.close();" title="close"></span></h1>
<!-- //header -->
<div class="pop_area">
	<!-- Popup Size 840*600 -->
	<div class="popW640">
		<div class="pop_content mt10">
			<!-- title -->
			<div class="title">
				<h3><spring:message code="las.page.field.lawservice.selectdept" /></h3>
			</div>
			<!-- //title -->			
			<!-- subLIst -->
			<div class="menuList_wrap">
				<div class="menuList02 fL">
					<!-- menuLIst02-->
					<ul style='line-height:22px;'>
								<c:forEach var="list" items="${resultList}" varStatus="status">	
									<li>
									<img src='<%=IMAGE %>/btn/arr_list_minus.gif' alt='down' id="minusIcon_<c:out value='${list.grp_dept_cd}' />" />
										<span id="<c:out value='${list.grp_dept_cd}' />">
											<a href="JavaScript:setSelectListInfo('<c:out value='${list.grp_dept_cd}' />', '<c:out value='${list.intnl_dept_cd}' />', '<c:out value='${list.dept_nm}' />')">
												<c:out value='${list.dept_nm}' />
											</a>
										</span>
									</li>	
								</c:forEach>
							</ul>
					<!-- //menuLIst02 -->
				</div>
				<div class="menuList_sub fR">
					<div class="menuList_sub_btn fL">
						<a href="javascript:removeRow()"><img src="<%=IMAGE %>/btn/arr_big_pre.gif" alt="<spring:message code="las.page.button.lawservice.listout" />"/></a>
						<a href="javascript:removeAllRow()"><img src="<%=IMAGE %>/btn/arr_big_pre02.gif" alt="<spring:message code="las.page.button.lawservice.listoutall" />"/></a>
					</div>
					<div class="subListBox_wrap fR">

							<select id="collist" name="collist" size=12 multiple style="height:300px; width:260px;margin-bottom:10px;">
							</select>
	
						<div id="subListBox" class="subListBox_btn">
							<span class="fL">
								<a href="javascript:pullUpRow()"><img src="<%=IMAGE %>/btn/arr_big_up.gif" alt="up" /></a>
								<a href="javascript:pullDownRow()"><img src="<%=IMAGE %>/btn/arr_big_down.gif" alt="down"/></a>
							</span>
							<span class="fR mR200"><span class="btn_all_b"><span class="save"></span><a href="javascript:setOpenerDeptInfo();"><spring:message code="las.page.button.lawservice.confirm" /></a></span></span>
						</div>
					</div>
				</div>
			</div>
			<!-- //subLIst -->
		</div>
	</div>
</div>
<!-- //body -->
</form:form>
</body>
</html>