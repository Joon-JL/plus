<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>

<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%--
/**
 * 파  일  명 : LawyerManage_d.jsp
 * 프로그램명 : 변호사관리- 상세
 * 설      명 : 
 * 작  성  자 : 박병주
 * 작  성  일 : 2011.09.
 */
 --%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><spring:message code="las.main.title" /></title>
<LINK href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript" src="<c:url value='/script/clms/menu.js'/>"></script>

<script language="javascript">
<!--
	/**
	* 초기화면 로딩시
	*/
	$(document).ready(function(){
	
		var frm = document.frm;
		
		//첨부파일창 load
		initPage();
		
		// 변호사 전문 분야 
		var all_check_expert = document.getElementsByTagName("INPUT");

		var expert_area_tmp = "<c:out value='${lom.expert_area}'/>";
		var expert_area = new Array();
		
		if(expert_area_tmp!=''){
			expert_area = expert_area_tmp.split("/");
		}

		for(var i=0; i < all_check_expert.length ; i++) {
			if(all_check_expert[i].type == 'checkbox'){
				for(var j=0; j < expert_area.length ; j++) {
					if (all_check_expert(i).id == expert_area[j]){	
						all_check_expert.item(i).checked = true;
					}	
				}
			}
		}		
		
	});
	
	/**
	* 화면 갱신 
	*/
	function MM_reloadPage(init) {  //reloads the window if Nav4 resized
	  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
	    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
	  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
	}
	MM_reloadPage(true);
	
	/**
	* 프로필 사진 업로드 팝업 :2012/1/18 개인정보 관련으로 사용안함.
	*/
	function LawyerProfilePhoto_p()
	{		
		var frm = document.frm;
		
		PopUpWindowOpen('', "600", "170", true);
		frm.action = "<c:url value='/las/lawservice/lawyerManage.do' />";
		frm.method.value = "forwardUploadLawyerProfile";
		frm.target = "PopUpWindow";
		frm.submit();

	}	
		
	/**
	* 첨부파일창 load
	*/
	function initPage(){
	
	    frm.target          = "fileList";
	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    = "forwardClmsFilePage";
	    frm.submit();   
	}

	function goList() {
		var frm = document.frm;

		viewHiddenProgress(true);

		frm.method.value = "listLawyerManage";
		frm.action = "<c:url value='/las/lawservice/lawyerManage.do' />";
		frm.target = "_self";
		frm.submit();
	}
	
	function modify() {
		
		var frm = document.frm;

		viewHiddenProgress(true);		
		frm.method.value = "forwardLawyerManageModify";
		frm.action = "<c:url value='/las/lawservice/lawyerManage.do' />";
		frm.target = "_self";
		frm.submit();
	}
	
	function remove(){
		
		var frm = document.frm;
		if(confirm("<spring:message code='secfw.msg.ask.delete' />")) {
			viewHiddenProgress(true) ;
			frm.method.value = "deleteLawyerManage";
			frm.action = "<c:url value='/las/lawservice/lawyerManage.do' />";
			frm.target = "_self";			
			frm.submit();
		}
	}
	
	function showMessage(msg){
		if( msg != "" && msg != null && msg.length > 1 ) {
			alert(msg);
		}
	}
	
//-->
</script>
</head>
<body onLoad='showMessage("<c:out value='${returnMessage}'/>");'>

<form:form name="frm" id="frm" autocomplete="off" >

<input type="hidden" name="method"   	 	value="" />
<input type="hidden" name="menu_id"  	 	value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage"  	 	value="<c:out value='${command.curPage}'/>" />
<input type="hidden" name="screen_type"  value="insert" />
<input type="hidden" name="forward_url" 	value="" />
<input type="hidden" id="forward_from" name="forward_from"  value="" />

<!-- URL 이동시 사용 -->
<input type="hidden" name="targetMenuId">
<input type="hidden" name="selected_menu_id">
<input type="hidden" name="selected_menu_detail_id">
<input type="hidden" name="set_init_url">

<!--key -->
<input type="hidden" name="lwr_no" 		value="<c:out value='${command.lwr_no}'/>" />

<!-- search form -->
<input type="hidden" name="srch_event_no"   value="<c:out value='${command.srch_event_no}'/>" />
<input type="hidden" name="srch_event_nm"   value="<c:out value='${command.srch_event_nm}'/>" />
<input type="hidden" name="srch_lawfirm_nm"   value="<c:out value='${command.srch_lawfirm_nm}'/>" />  
<input type="hidden" name="srch_lawfirm_id"   value="<c:out value='${command.srch_lawfirm_id}'/>" />
<input type="hidden" name="srch_kbn1" value="<c:out value='${command.srch_kbn1}'/>" />
<input type="hidden" name="srch_kbn2" value="<c:out value='${command.srch_kbn2}'/>" />
<input type="hidden" name="srch_lwr_estimate_lvl"   value="<c:out value='${command.srch_lwr_estimate_lvl}'/>" />
<input type="hidden" name="srch_lwr_nm"   value="<c:out value='${command.srch_lwr_nm}'/>" />
<input type="hidden" name="srch_expert_area"   value="<c:out value='${command.srch_expert_area}'/>" />

<!-- 첨부파일정보 -->
<input type="hidden" name="fileInfos" 		value="" />
<input type="hidden" name="file_bigclsfcn" 	value="F005" />
<input type="hidden" name="file_midclsfcn" 	value="F0050102" />
<input type="hidden" name="file_smlclsfcn" 	value="" />
<input type="hidden" name="ref_key" 		value="<c:out value='${command.lwr_no}'/>" />
<input type="hidden" name="view_gbn" 		value="download" />

<!-- //**************************** Form Setting **************************** -->

<div id="wrap">   	
<!-- container -->
<div id="container">
	<!-- location -->
	<div class="location"><IMG SRC="/attach/image/lawyer/<c:out value='${command.lwr_no}'/>" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
	<!-- //location -->
	<!-- content -->
	<div id="content">
		<div class="t_titBtn">
				<!-- title -->
				<div class="title" >
					<h3><spring:message code="las.page.field.lawservice.lwrmng" />&nbsp;<spring:message code="las.page.field.lawservice.detail" />
				</h3>
				</div>
				<!-- //title -->
			<div class="btn_wrap">
				<c:if test="${command.lws_auth_modify}">
					<span class="btn_all_w"><span class="edit"></span><a href="javascript:modify()"><spring:message code="las.page.button.modify" /></a></span>
				</c:if>
				<c:if test="${command.lws_auth_delete}">
					<span class="btn_all_w"><span class="delete"></span><a href="javascript:remove()"><spring:message code="las.page.button.delete" /></a></span>
				</c:if>
			</div>
		</div>	

		
		<!-- view -->
		<table border="0" cellspacing="0" cellpadding="0" class="table-style01" border="2">
			<colgroup>
					<col width="15%" />
					<col width="35%" />
					<col width="15%" />
					<col width="35%" />
				</colgroup>
			<tbody>
				<!-- <tr>
					<td colspan="1">
						<c:choose>
							<c:when test="${profile_image_yn == 'Y'}">
								<img src="/attach/image/lawyer/<c:out value='${command.lwr_no}'/>" alt=""/></br>
								<c:if test="${command.lws_auth_modify}">
									<span class="btn_option"><span class="add"></span><a href="javascript:LawyerProfilePhoto_p();"><spring:message code="las.page.field.lawservice.modprofile" /></a></span>
								</c:if>
							</c:when>
							<c:otherwise>
								<c:if test="${command.lws_auth_modify}">
									<span class="btn_option"><span class="add"></span><a href="javascript:LawyerProfilePhoto_p();"><spring:message code="las.page.field.lawservice.insprofile" /></a></span>
								</c:if>
							</c:otherwise>
						</c:choose>
					</td>
					<td colspan="3">	
						<table border="0" cellspacing="0" cellpadding="0" class="table-styleSm"  width="70%">
								<colgroup>
								<col class="tal_w04" />
								<col />
							</colgroup>
							<tbody>
								<tr>
									<th><spring:message code="las.page.field.lawservice.lwrnmen" /></th>
									<td><c:out value='${lom.lwr_nm}'/></td>
								</tr>
								<tr>
									<th><spring:message code="las.page.field.lawservice.lwrtitle" /></th>
									<td><c:out value='${lom.lwr_jikgup_nm}'/></td>
								</tr>
								<tr>
									<th><spring:message code="las.page.field.lawservice.territoryen" /></th>
									<td><c:out value='${lom.nation_nm}'/></td>
								</tr>
								<tr>
									<th><spring:message code="las.page.field.lawservice.telen" /></th>
									<td><c:out value='${lom.telno}'/></td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>//-->
				<tr>
					<th><spring:message code="las.page.field.lawservice.lwrnmen" /></th>
					<td colspan="3"><c:out value='${lom.lwr_nm}'/></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.lwrtitle" /></th>
					<td colspan="3"><c:out value='${lom.lwr_jikgup_nm}'/></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.territoryen" /></th>
					<td colspan="3"><c:out value='${lom.nation_nm}'/></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.telen" /></th>
					<td colspan="3"><c:out value='${lom.telno}'/></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.lawfirm" /></th>
					<td colspan="3"><strong><c:out value='${lom.lawfirm_nm}'/></strong></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.lwrestmt" /></th>
					<td><c:out value='${lom.lwr_estmt_lvl_nm}'/></td>
					<th><spring:message code="las.page.field.lawservice.lwremail" /></th>
					<td><c:out value='${lom.email}'/></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.lwrestcont" /></th>
					<td colspan="3">
						<ul class="list_assess">							
							<c:forEach var="list" items="${estimate_list}" varStatus="x">							
							<li> 
							<input style="width:200px;" type="text" id="text_field" name="text_field"  value="<c:out value='${list.estmt_title}'/>" readonly class="text"/> 
							<span style='margin-right 1px;align:right;vertical-align:3px' > <spring:message code="las.page.field.lawservice.estDate2"/> <c:out value='${list.reg_dt}'/></span>
							</li>
							<li>
							<textarea id="textarea" name="textarea"  cols="10" rows="7" class="text_area_01" readonly ><c:out value='${list.estmt_cont}'/></textarea>
							</li>
							</c:forEach>
						</ul>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.lawschool" /></th>
					<td colspan="3"><c:out value='${lom.lawschool_nm}'/></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.fromschool" /></th>
					<td colspan="3"><c:out value='${lom.from_school}'/></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.expertarea" /></th>
					<td colspan="3">
					<table  border='0' cellspacing='0' cellpadding='0' class='tal_z' >
						<tr>
							<c:forEach var="list" items="${expertAreaList}" varStatus="status">
								<td>
									<input id='<c:out value='${list.cd}'/>' name ='att_speciality' type='checkbox' disabled />
									<c:out value='${list.cd_nm}'/>
								</td>
								<c:if test="${status.count mod 5==0}">
									</tr><tr>
								</c:if>
							</c:forEach>
						</tr>
					</table>

					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.lwrresume" /></th>
					<td colspan="3"><c:out value='${lom.career_cont}' escapeXml="false" />
					</td>
				</tr>
				<tr class="end">
					<th><spring:message code="las.page.field.lawservice.attach" /></th>
					<td colspan="3">
					<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="80px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
					</td>
				</tr>
			</tbody>
		</table>
		<!-- //view -->

		<!-- list -->
		<table border="0" cellspacing="0" cellpadding="0" class="list_basic mt20">
		  <colgroup>
		  <col class="tal_w02" />
		  <col />
		  <col class="tal_w03" />
		  <col class="tal_w04" />
		  <col class="tal_w05" />
		  </colgroup>
		  <thead>
			<tr>
			  <th><spring:message code="las.page.field.lawservice.order" /></th>
			  <th><spring:message code="las.page.field.lawservice.eventnm" /></th>
			  <th><spring:message code="las.page.field.lawservice.lawsuittrgt" /></th>
			  <th><spring:message code="las.page.field.lawservice.acptnm" /></th>
			  <th><spring:message code="las.page.field.lawservice.acptday" /></th>
			</tr>
		  </thead>
		  <tbody>
			<c:choose>
			<c:when test="${!empty event_list}">
				<c:forEach var="list" items="${event_list}">
					<tr>
					    <td><c:out value='${list.rn}'/></td>
						<td  class="tL">
						<a href='javascript:Menu.detail("frm", "_top", "20110804083733433_0000000169", "20110804083733433_0000000169", "/las/lawservice/eventManage.do?forward_from=&method=detailEventManage&event_no=<c:out value='${list.event_no}'/>")' >
							<strong><c:out value='${list.event_nm}'/></strong>
						</a>
		            </td>
		            <td><c:out value='${list.lawsuit_trgt_nm}'/></td>
		            <td><c:out value='${list.reg_nm}'/></td>
		            <td><c:out value='${list.acptday}'/></td>
				</tr>
				</c:forEach>
		    </c:when>
		    <c:otherwise>
				<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
					<td colspan="5" align="center"><spring:message code="las.msg.succ.noResult" /></td>
				</tr>
		    </c:otherwise>
			</c:choose>
		  </tbody>
		  </table>
		<!-- //list -->
		<div class="t_titBtn">
			<div class="btn_wrap">
				<span class="btn_all_w" ><span class="list"></span><a href="javascript:goList()"><spring:message code="las.page.button.lawservice.golist"/></a></span>
			</div>
		</div>
	</div>
	<!-- //content -->
	<!-- footer  -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
	<!-- // footer -->
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</div>
<!-- //container -->
</div>
</form:form>
</body>
</html>