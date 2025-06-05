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
 * 파  일  명 : DeptList_p.jsp
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
	* 하위 부서 아이콘을 클릭했을때의 처리
	*/
	function showHide(iconPos) {	
 		if(dupSubmit) {
			if(document.all["plusIcon_" + iconPos].style.display == 'none') {
				document.all["plusIcon_" + iconPos].style.display = '';
				document.all["minusIcon_" + iconPos].style.display = 'none';
				document.all["DIV_" + iconPos].style.display = 'none';
			} else {
				document.all["plusIcon_" + iconPos].style.display = 'none';
				document.all["minusIcon_" + iconPos].style.display = '';
				if(document.all["DIV_" + iconPos].innerHTML == "") {
					subDept(iconPos);
				} else {
					document.all["DIV_" + iconPos].style.display = '';
				}
			}
		}
	}
	
	/**
	* 하위 부서트리 정보를  Ajax로 호출
	*/
	function subDept(iconPos) {
		
		document.searchForm.up_dept_cd.value = iconPos;
		var options = {
				url: "<c:url value='/las/lawservice/eventManage.do?method=listDeptTreeAjax' />",

				type: "post",
				dataType: "json",
				beforeSubmit: function(formData, form) {
					dupSubmit = false;
					return true;
				},
				success: function(responseText, statusText) {
					if(responseText.returnCnt != 0) {
						var changeFlag = false;
						var changeDept = "";
						var html = "<ul style='margin-left:12px;line-height:22px;'>";
						$.each(responseText, function(entryIndex, entry) {
							html += "<li>";
							
							if(entry['down_dept_yn'] == "T") {
								html += "<img src='<%=IMAGE %>/btn/arr_list_add.gif' id='plusIcon_" + entry['dept_cd'] + "' onClick='JavaScript:showHide(\"" + entry['dept_cd'] + "\");' style='cursor:hand'>";
								html += "<img src='<%=IMAGE %>/btn/arr_list_minus.gif' id='minusIcon_" + entry['dept_cd'] + "' onClick='JavaScript:showHide(\"" + entry['dept_cd'] + "\");' style='cursor:hand;display:none'>";
								html += "<span id='SP_" + entry['dept_cd'] + "'><a href='JavaScript:setSelectListInfo(\"" + entry['dept_cd'] + "\",\"" + entry['in_dept_cd'] + "\",\"" + entry['dept_nm'] + "\");'>&nbsp;" + entry['dept_nm'] + "</a></span>";
								html += "<div id='DIV_" + entry['dept_cd'] + "' style='display:none'></div>";
							} else {
								html += "<img src='<%=IMAGE %>/btn/arr_list_minus.gif' id='minusIcon_" + entry['dept_cd'] + "' >";
								html += "<span id='SP_" + entry['dept_cd'] + "'><a href='JavaScript:setSelectListInfo(\"" + entry['dept_cd'] + "\",\"" + entry['in_dept_cd'] + "\",\"" + entry['dept_nm'] + "\");'>&nbsp;" + entry['dept_nm'] + "</a></span>";
							}
							
							if(searchDept != "") {
								if(entry['dept_nm'] == searchDept) {
									changeFlag = true;
									changeDept = entry['dept_cd'];
									searchDept = "";
								}
							}
							html += "</li>";
						});
						html += "</ul>";
	
						$('#DIV_' + iconPos).html(html);
						document.all["DIV_" + iconPos].style.display = '';
	
						if(changeFlag) {
							$('#SP_' + changeDept).addClass("pick");
							choiceDept = changeDept;
							changeFlag = false;
							//listUser(changeDept);
						}	
					}
					dupSubmit = true;
				}
			}
	
		if(dupSubmit) {
			$("#searchForm").ajaxSubmit(options);
		}		
	}
	
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
			alert("<spring:message code='las.page.field.lawservice.aleadyAdd'/>");
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
		// var rNum = $("#collist option").index($("#collist option:selected"));
		// $("#collist option:eq(" + rNum + ")").before($("#collist option:eq(" + (rNum + 1) + ")")); 
		// 참고:주석 처리 된 2줄의 jquery 로 처리 가능하나, 화면이 느려지는  현상이 있음.
		
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
			str_msg = allDept(0).text + " <spring:message code='las.page.field.lawservice.etc'/> " + (t-1) + "<spring:message code='las.page.field.lawservice.chosenDept'/>";
		
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
<h1><spring:message code="las.page.field.lawservice.multiSelectDept"/><span class="close" onclick="javascript:self.close();" title="close"></span></h1>
<!-- //header -->
<div class="pop_area">
	<!-- Popup Size 840*600 -->
	<div class="popW640">
		<div class="pop_content mt10">

			<!-- title -->
			<div class="title">
				<h3><spring:message code="las.page.field.lawservice.selectDept"/></h3>
			</div>
			<!-- //title -->
			
			<!-- subLIst -->
			<div class="menuList_wrap">
				<div class="menuList02 fL">
					<!-- menuLIst02-->
					<ul style='line-height:22px;'>
								<c:forEach var="list" items="${resultList}" varStatus="status">
									<c:choose>
										<c:when test="${ list.down_dept_yn == 'T'}">
											<li>
											<img src="<%=IMAGE %>/btn/arr_list_add.gif" id="plusIcon_<c:out value='${list.dept_cd}' />" onClick="JavaScript:showHide('<c:out value='${list.dept_cd}' />');" style='cursor:hand' />
											<img src="<%=IMAGE %>/btn/arr_list_minus.gif" id="minusIcon_<c:out value='${list.dept_cd}' />" onClick="JavaScript:showHide('<c:out value='${list.dept_cd}' />');" style='cursor:hand;display:none' />
												<span id="SP_<c:out value='${list.dept_cd}' />">
													<a href="JavaScript:setSelectListInfo('<c:out value='${list.dept_cd}' />', '<c:out value='${list.in_dept_cd}' />' , '<c:out value='${list.dept_nm}' />');">
														<c:out value='${list.dept_nm}' />
													</a>
												</span>
												<div id="DIV_<c:out value='${list.dept_cd}' />" style='display:none'></div>									
											</li>											
										</c:when>
										<c:otherwise>
											<li>
											<img src='<%=IMAGE %>/btn/arr_list_minus.gif' alt='down' id="minusIcon_<c:out value='${list.dept_cd}' />" />
												<span id="<c:out value='${list.dept_cd}' />">
													<a href="JavaScript:setSelectListInfo('<c:out value='${list.dept_cd}' />', '<c:out value='${list.in_dept_cd}' />', '<c:out value='${list.dept_nm}' />')">
														<c:out value='${list.dept_nm}' />
													</a>
												</span>
											</li>	
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</ul>
					<!-- //menuLIst02 -->
				</div>
				<div class="menuList_sub fR">
					<div class="menuList_sub_btn fL">
						<!-- <a href="#"><img src="<%=IMAGE %>/btn/arr_big_next.gif" alt="추가"/></a>
						<a href="#"><img src="<%=IMAGE %>/btn/arr_big_next02.gif" alt="모두추가"/></a> -->
						<a href="javascript:removeRow()"><img src="<%=IMAGE %>/btn/arr_big_pre.gif" alt="<spring:message code="las.page.field.lawservice.subtr"/>"/></a>
						<a href="javascript:removeAllRow()"><img src="<%=IMAGE %>/btn/arr_big_pre02.gif" alt="<spring:message code="las.page.field.lawservice.allSubtr"/>"/></a>
					</div>
					<div class="subListBox_wrap fR">
						<div >
						<select id="collist" name="collist" size=12 multiple style='border:1px ; height:300px; width:270px; overflow-x:hidden; overflow-y:auto;scrollbar-face-color: #F1F1F1; margin-bottom:10px; padding:5px;'>
						</select>

						</div>
						<div id="subListBox" class="subListBox_btn">
							<span class="fL">
								<a href="javascript:pullUpRow()"><img src="<%=IMAGE %>/btn/arr_big_up.gif" alt="up" /></a>
								<a href="javascript:pullDownRow()"><img src="<%=IMAGE %>/btn/arr_big_down.gif" alt="down"/></a>
							</span>
							<span class="fR mR200"><span class="btn_all_b"><span class="save"></span><a href="javascript:setOpenerDeptInfo();"><spring:message code="las.page.field.lawservice.check"/></a></span></span>
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