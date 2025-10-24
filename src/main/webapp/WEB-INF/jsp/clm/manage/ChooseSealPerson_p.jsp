<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.manage.dto.ConsiderationForm" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%--
/**
 * 파  일  명 : ChooseSealPerson_p.jsp
 * 프로그램명 : 날인담당자선택팝업
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title" /></title>
<link href="<%=CSS%>/popup.css" type="text/css" rel="stylesheet" />
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet" />

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript">
<!--
$(document).ready(function(){
	//$('select[name=seal_ffmt_search_dept_cd]').val("<c:out value='${command.seal_ffmt_search_dept_cd}'/>");
	//getCodeSelectByUpCd("frm", "seal_ffmt_search_dept_cd", '/clm/manage/chooseSealPerson.do?method=getSealDeptComboHTML&seal_ffmt_search_dept_cd=' + '<c:out value='${command.seal_ffmt_search_dept_cd}'/>');
});

 function searchSealPerson() {
	var frm    = document.frm;
    frm.target = "_self";
    frm.action = "<c:url value='/clm/manage/chooseSealPerson.do' />";
    frm.method.value = "forwardChooseSealPersonPopup";
    frm.submit();
} 

function save() {
	if(typeof $('#sealman-tr :checked').val()=="undefined" ||$('#sealman-tr :checked').val() == "" ) {
		alert("<spring:message code='clm.msg.alert.choosesealperson.noSelect'/>");
		return;
	} else {
		//날인담당자 팝업 인 경우
		<c:if test="${command.srch_type_gbn eq 'SC0101'}">
			opener.document.frm.seal_ffmtman_id.value  			= $('#sealman-tr :checked').next().val();
			opener.document.frm.seal_ffmtman_nm.value  			= $('#sealman-tr :checked').next().next().val();
			//opener.document.frm.seal_ffmtman_search_nm.value 	= $('#sealman-tr :checked').next().next().val();
			opener.document.frm.seal_ffmtman_search_nm.value 	= '';
			opener.document.frm.seal_ffmt_dept_cd.value			= $('#sealman-tr :checked').next().next().next().val();
			opener.document.frm.seal_ffmt_dept_nm.value			= $('#sealman-tr :checked').next().next().next().next().val();
			opener.document.frm.seal_ffmtman_jikgup_nm.value	= $('#sealman-tr :checked').next().next().next().next().next().val();
			
			opener.setSealPerson($('#sealman-tr :checked').next().next().val()+'/'+$('#sealman-tr :checked').next().next().next().next().next().val()+'/'+$('#sealman-tr :checked').next().next().next().next().val());	
		</c:if>
		//증명서류 담당자 팝업 인 경우
	
		<c:if test="${command.srch_type_gbn eq 'SC0102'}">
			opener.document.frm.doc_issuer_id.value  			= $('#sealman-tr :checked').next().val();
			opener.document.frm.doc_issuer_nm.value  			= $('#sealman-tr :checked').next().next().val();
			//opener.document.frm.seal_ffmtman_search_nm.value 	= $('#sealman-tr :checked').next().next().val();
			opener.document.frm.doc_issuer_search_nm.value 	= '';
			opener.document.frm.doc_issuer_dept_cd.value			= $('#sealman-tr :checked').next().next().next().val();
			opener.document.frm.doc_issuer_dept_nm.value			= $('#sealman-tr :checked').next().next().next().next().val();
			opener.document.frm.doc_issuer_jikgup_nm.value	= $('#sealman-tr :checked').next().next().next().next().next().val();
			
			opener.setDocPerson($('#sealman-tr :checked').next().next().val()+'/'+$('#sealman-tr :checked').next().next().next().next().next().val()+'/'+$('#sealman-tr :checked').next().next().next().next().val());	
		</c:if>
		
		self.close();
		
	}
}
//-->
</script>
</head>
<body class="pop">
<!-- header -->
<h1><c:if test="${command.srch_type_gbn eq 'SC0101'}"><spring:message code='clm.page.title.choosesealperson.mainTitle'/></c:if>
<c:if test="${command.srch_type_gbn eq 'SC0102'}">증명서류 발급 담당자 팝업</c:if>
</h1>
<!-- //header -->
<div class="pop_area">
	<!-- content -->
	<div class="pop_content" >
					<!-- search-->
	            <div class="search_box">
					<div class="search_box_inner">
						<form:form name="frm" id='frm' method="post" autocomplete="off">
						<input type="hidden" name="method" id="method" value=""/>
						<input type="hidden" name="menu_id" id="menu_id" value="<c:out value='${command.menu_id}'/>" />
						<input type="hidden" name="curPage" id="curPage" value="<c:out value='${command.curPage}'/>"/> 
						<input type="hidden" name="srch_type_gbn" id="srch_type_gbn" value="<c:out value='${command.srch_type_gbn}'/>" />
						<table class="search_tb">
							<colgroup>
								<col width="*px"/>
								<col width="78px"/>
							</colgroup>
							<tr>
								<td>
									
									<table class="search_form">
										<colgroup>
											<col width="180px"/>
											<col width="700px"/>
											<col width="*px"/>
										</colgroup>
										<tr>
											<th>
												<select name='srch_cntnt_type' >
													<option value='user_nm' <c:if test="${command.srch_cntnt_type eq 'user_nm'}">selected</c:if> ><spring:message code="secfw.page.field.user.nm"/></option>
													<option value='dept_nm' <c:if test="${command.srch_cntnt_type eq 'dept_nm'}">selected</c:if> ><spring:message code="secfw.page.field.user.department"/></option>

											</select>	
											</th>
											<td>
												<input type="text" class="text_30" name="srch_cntnt" id="srch_cntnt" value="<c:out value='${command.srch_cntnt}'/>" onkeydown="if(event.keyCode==13){event.returnValue = false;javascript:searchSealPerson();}"/>
											</td>
											<td></td>
										</tr>
									</table>
								</td>
								<td class='tC'><a href="javascript:searchSealPerson();"><img src="../../images/las/ko/btn/btn_search.gif" alt="Search" title="Search" /></a></td>
							</tr>
						</table>
					</div>
	            </div>     
				<!-- search-->	
				
				<!-- button -->
				<div class="mb3 mt10 tR">
					<span class="btn_all_w"><span class="save"></span><a href="javascript:save();"><spring:message code="clm.page.msg.common.save" /></a></span>
					<span class="btn_all_w"><span class="cancel"></span><a href="#" onclick="javascript:self.close();"><spring:message code="clm.page.msg.common.cancel" /></a></span>
				</div>
				<!-- //button -->
				
		        <!-- List -->
		        <div class='tableWrap mt5'>
					<div class='tableone'>
				        <table class="list_basic">
							<colgroup>
								<col width="10%" />
								<col width="15%" />
								<col width="20%" />
								<col width="20%" />
								<col width="20%" />
								<col width="15%" />
							</colgroup>
							<tr>
							<th><spring:message code='clm.page.field.choosesealperson.choice'/></th>
						<th><spring:message code='clm.page.field.choosesealperson.sealpersonnm'/></th>
						<th><spring:message code='clm.page.field.choosesealperson.deptnm'/></th>
						<th><spring:message code='clm.page.field.choosesealperson.jikgupnm'/></th>
								<th><spring:message code='clm.page.field.sign.corp'/><!-- 관리사업장 --></th>
								<th><spring:message code='clm.page.msg.common.note'/><!-- 비고 --></th>
							</tr>
						</table>
					</div>
				</div>
				
				
				<style>
					.h_120 {max-height:240px;}
					*html .h_120 {height:240px;}
				</style>				
				
				<div class='tabletwo h_120'>
				<table class="list_scr" >
					<colgroup>
						<col width="10%" />
						<col width="15%" />
						<col width="20%" />
						<col width="20%" />
						<col width="20%" />
						<col width="15%" />
					</colgroup>
					<tbody id="sealman-tbody">
						<c:forEach var="list" items="${resultList}"  varStatus="i">
						<tr id="sealman-tr" onmouseout="this.className='';" onmouseover="this.className='on'">
							<td  class='tC'>
							
								<input type="radio" class="radio" name="seal_ffmtman_choice" id="seal_ffmtman_choice" value="<c:out value='${i.count}'/>"/>
								<input type="hidden" name="seal_ffmtman_id" value="<c:out value='${list.user_id}'/>"/>
					  			<input type="hidden" name="seal_ffmtman_nm" value="<c:out value='${list.user_nm}'/>"/>
					  			<input type="hidden" name="seal_ffmt_dept_cd" value="<c:out value='${list.dept_cd}'/>"/>
					  			<input type="hidden" name="seal_ffmt_dept_nm" value="<c:out value='${list.dept_nm}'/>"/>
					  			<input type="hidden" name="seal_ffmtman_jikgup_nm" value="<c:out value='${list.jikgup_nm}'/>"/>
					  			<input type="hidden" name="seal_ffmtman_jikgup_cd" value="<c:out value='${list.jikgup_cd}'/>"/>
							</td>
					  		<td class='tC'><c:out value='${list.user_nm}'/></td>
							<td class="tC"><c:out value='${list.dept_nm}'/></td>
							<td class='tC'><c:out value='${list.jikgup_nm}'/></td>
							<td class='tC'><c:out value='${list.ara_nm}'/></td>
							<td class='tC'><c:out value='${list.comments}'/></td>
						 </tr>
						</c:forEach>
					</tbody>
				</table>		
				
				</form:form>
				</div>
				<!-- //list -->				
				
	</div>
	<!-- //content -->
</div>
<!-- //body -->


<!--footer -->
<div class="pop_footer">
	<div class="btn_wrap fR" >
		<span class="btn_all_w"><span class="save"></span><a href="javascript:save();"><spring:message code="clm.page.msg.common.save" /></a></span>
		<span class="btn_all_w"><span class="cancel"></span><a href="#" onclick="javascript:self.close();"><spring:message code="clm.page.msg.common.cancel" /></a></span>
	</div>
</div>
<!-- //footer -->


</body>

</html>