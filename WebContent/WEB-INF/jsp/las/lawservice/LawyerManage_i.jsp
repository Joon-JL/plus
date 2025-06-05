<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
 * 파  일  명 : LawyerManage_i.jsp
 * 프로그램명 : 변호사관리 등록
 * 설      명 : 
 * 작  성  자 : 박병주
 * 작  성  일 : 2011.09
 */
--%>
<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.main.title" /></title>
<LINK href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript">
<!--
	/**
	* 초기화면 로딩
	*/
	$(document).ready(function(){
		
		//첨부파일창 load
		initPage();
		
		// title
		getCodeSelectByUpCd2("frm", "lwr_jikgup", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+"109"+"&combo_abbr=F&combo_type=S2&combo_use_yn=Y&combo_selected="+"<c:out value='${lom.lwr_jikgup}'/>");
	
		// 변호사평가
		getCodeSelectByUpCd2("frm", "lwr_estmt_lvl", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+"C006"+"&combo_abbr=F&combo_type=S2&combo_use_yn=Y&combo_selected="+"<c:out value='${lom.lwr_estmt_lvl}'/>");
		
		// Law School
		getCodeSelectByUpCd2("frm", "lawschool", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+"108"+"&combo_abbr=F&combo_type=S2&combo_use_yn=Y&combo_selected="+"<c:out value='${lom.lawschool}'/>");
		
		// 전문 분야 
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
	* 첨부파일창 load
	*/
	function initPage(){
	
	    frm.target          = "fileList";
	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    = "forwardClmsFilePage";
	    frm.submit();   
	}

	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		
		var frm = document.frm;
		//저장
		if(flag == "insert"){
		    
		    //나모웹에디터 관련
		    frm.body_mime.value = frm.wec.MIMEValue;
		    var confirmMessage = frm.lwr_no.value==("") ? "<spring:message code='secfw.msg.ask.insert' />" : "<spring:message code='secfw.msg.ask.update' />" ;
		    
		    //유효성 체크
		    if(validateForm(frm)){
		    	
		    	fileList.UploadFile(function(uploadCount){

					//지정된 형식 이외에 파일을 추가한 경우
					if (uploadCount < 0) {
						return;
					} 

                    if(checkForm() && confirm(confirmMessage)){
    		    		
    		    		viewHiddenProgress(true) ;
    		    		frm.method.value = frm.lwr_no.value==("") ? "insertLawyerManage" : "modifyLawyerManage";
    					frm.target = "_self";
    					frm.action = "<c:url value='/las/lawservice/lawyerManage.do' />";

    			    	frm.submit();	
    		    	} else {
    		    		return;	
    		    	}
                });
		    }        
			
		//취소
		} else if(flag == "cancel"){
			
			viewHiddenProgress(true) ;
			
			frm.target = "_self";
			frm.action = "<c:url value='/las/lawservice/lawyerManage.do' />";
			frm.method.value = frm.lwr_no.value==("") ? "listLawyerManage" : "detailLawyerManage";
			frm.submit();			
		} 
	}
	
	/**
	* From Check
	*/
	function checkForm(){
		
		var form = document.frm;

		var str_lwr_nm = "";
		var check_flag = true;
		
		if((replace(form.last_name.value,' ',''))=='' || replace(form.first_name.value,' ','')==''){
			alert("<spring:message code='las.page.msg.lawservice.lwrnm' />");
			return false;
		}
		
		// 변호사 이름 조합
		str_lwr_nm = form.last_name.value + " " + form.first_name.value;
		form.lwr_nm.value = str_lwr_nm;
		
		//alert("사건셀렉트" + $('#event_no').val());

		// 사건명 중복 입력 체크		
		if($('#event_no').val()!=''){
			var str_event_id = "";
			var check_flg = false;
			var total_selected_count = 0;
			var check_null_count = 0;
			for ( var i =0 ; i < form.event_no.length; i++)
			{			
				
				str_event_id = form.event_no(i).value;
				
				for ( var j=0 ; j < form.event_no.length ; j++ )
				{			
					if((form.event_no(j).value!='') && ((i!=j)&&(str_event_id==form.event_no(j).value))){
						check_flg = true;					
					}
				}
				
				if(form.event_no(i).value==''){
					check_null_count = check_null_count + 1;
				}
	
			} 
			
			if(check_flg){
				alert("<spring:message code='las.page.msg.lawservice.lwrevent' />");
				return;
			}
		}
	
		var str_att_estimate_name;
		
		// 전문분야를 한개의 스트링으로 세팅
		var str_att_speciality = '';
		
		if(form.att_speciality.length >= 1){			
			
			var  t = 0;
			
			for ( var i =0 ; i < form.att_speciality.length; i++)
			{				
				if(form.att_speciality[i].checked == true){
					t = t + 1;
					if(t > 1){
						str_att_speciality = str_att_speciality + '/' + form.att_speciality(i).id;
					} else {
						str_att_speciality = form.att_speciality(i).id;
					}				
				}
			}			
					
			form.expert_area.value = str_att_speciality;
		} 
		
		return check_flag;
	}
	
	/**
	* 사건명 선택리스트 추가
	*/
	function dataInsertPlc()
	{
		var plcCnt = 0;
		plcCnt = eval(plcCnt) + 1;
		if( document.getElementById )	var tbl = document.getElementById('tbl_plc');		
		else							var tbl = document.all['tbl_plc'];					

		var tRow = tbl.insertRow();		

		tRow.bgColor   = "#ffffff";
		tRow.height    = "24";
		tRow.align     = "left";
	 			
		// 사건명
		var sHTML="";
									
		sHTML += "<select id='event_no' name='event_no' style='width:250px;'>";
		sHTML += "<option value=''><spring:message code='las.page.button.lawservice.select'/></option>\n";	
		<c:forEach var="list" items="${eventList}">
		sHTML += "<option value=" + "<c:out value='${list.event_no}'/>" + 
				 " >" +
				 "<c:out value='${list.event_nm}'/>" +
				 "</option>\n";			
		</c:forEach>
		
		sHTML += "</select>&nbsp;";		
		sHTML += "<span class='btn_option'><span class='delete'></span><a href='#' onclick='dataDeletePlc(this);' ><spring:message code='las.page.button.lawservice.del'/></a></span>";
		
		oCell           = tRow.insertCell();
		oCell.innerHTML = sHTML;
		
	}
	
	/**
	* 사건명 선택리스트 삭제
	*/
	function dataDeletePlc(n)
	{
		if( document.getElementById )	var tbl = document.getElementById('tbl_plc');	
		else							var tbl = document.all['tbl_plc'];					

		if ( parentTag("tr",n).rowIndex == 0 )
		{
			
		}else{
			tbl.deleteRow(parentTag("tr",n).rowIndex);
		}
	}
	
	/**
	* 변호사 평가 Form 추가
	*/
	function dataInsertPlc2()  
	{
		var plcCnt = 0;
		plcCnt = eval(plcCnt) + 1;
		if( document.getElementById )	var tbl = document.getElementById('tbl_plc2');		
		else							var tbl = document.all['tbl_plc2'];					

		var tRow = tbl.insertRow();		

		// tRow.bgColor   = "#ffffff";
		tRow.height    = "24";
		tRow.align     = "left";
		
		var sHTML="";
				
		sHTML += "	<ul class='list_assess'>";
		sHTML += "		<li>"; 
		sHTML += "			<input type='text' id='text_field' name='estmt_title' value='' class='text' alt=" + "<spring:message code='las.page.field.lawservice.estmtnm' />" + " maxLength='50' />";
		sHTML += "			&nbsp;<span class='btn_option' ><span class='delete'></span><a href='#' onclick='dataDeletePlc2(this);'>" + "<spring:message code='las.page.button.lawservice.del'/>" + "</a></span>";
		sHTML += "		</li>";
		sHTML += "		<li>";
		sHTML += "			<textarea id='textarea' name='estmt_cont'  cols='8' rows='7' class='text_area_01'  alt='" + "<spring:message code='las.page.field.lawservice.lwrestcont' />" +"' maxLength='1000'  ></textarea>";
		sHTML += "		</li>";
		sHTML += "	</ul>";		
		
		oCell           = tRow.insertCell();
		oCell.innerHTML = sHTML;
			
	}
	
	/**
	* 변호사 평가 Form 삭제
	*/
	function dataDeletePlc2(n)
	{
		if( document.getElementById )	var tbl = document.getElementById('tbl_plc2');	
		else							var tbl = document.all['tbl_plc2'];					
		
		if ( parentTag("tr",n).rowIndex == 0 )
		{
			
		}else{
			tbl.deleteRow(parentTag("tr",n).rowIndex);
		}
	}

	
	function parentTag(tag, e)
	{

		if (e == null || e.parentElement == null || e.parentElement.tagName == "HTML")
		{
			return null;
		}
		else if (e.parentElement.tagName == tag.toUpperCase())
		{
			return e.parentElement;
		}
		else
		{
			return parentTag(tag, e.parentElement);
		}
	}
		
	/**
	* 변호사 검색 팝업
	*/
	function LawerSearch()
	{
		var frm = document.frm;
		var fname = frm.first_name.value;
		var lname = frm.last_name.value;

		//var opnStr = "http://www.martindale.com/xp/Martindale/Lawyer_Locator/Search_Lawyer_Locator/search_result.xml?PG=0&STYPE=N&LNAME="+lname+"&FNAME="+fname+"&CN=&CTY=&STS=&CRY=&LSCH="
		var opnStr = "http://www.martindale.com";
		
		open_window("LawerSearch", opnStr, "0", "0", "800", "700", "yes", "yes", "yes", "yes" , "yes");
		
	}
	
	/**
	* 국가 선택 팝업
	*/
	function NationPop()
	{
		var frm = document.frm;
		PopUpWindowOpen('', "1000", "860", true);
		frm.action = "/secfw/main.do";
		frm.method.value="forwardPage";
		frm.target = "PopUpWindow";
		frm.forward_url.value="/WEB-INF/jsp/las/lawservice/NationList_p.jsp";
		frm.submit();
	}
	
//-->
</script>

</head>
<body>
	<form:form name="frm" id="frm" method="post" autocomplete="off">
		<input type="hidden" name="method" value="" />
		<input type="hidden" name="menu_id"			value="<c:out value='${command.menu_id}'/>" />
		<input type="hidden" name="curPage"			value="<c:out value='${command.curPage}'/>" />
		<input type="hidden" name="screen_type" value="insert" />
		<input type="hidden" name="forward_url" value="" />

		<!-- search form -->
		<input type="hidden" name="srch_event_no"			value="<c:out value='${command.srch_event_no}'/>" />
		<input type="hidden" name="srch_event_nm"			value="<c:out value='${command.srch_event_nm}'/>" />
		<input type="hidden" name="srch_lawfirm_nm"			value="<c:out value='${command.srch_lawfirm_nm}'/>" />
		<input type="hidden" name="srch_lawfirm_id"			value="<c:out value='${command.srch_lawfirm_id}'/>" />
		<input type="hidden" name="srch_kbn1"			value="<c:out value='${command.srch_kbn1}'/>" />
		<input type="hidden" name="srch_kbn2"			value="<c:out value='${command.srch_kbn2}'/>" />
		<input type="hidden" name="srch_lwr_estimate_lvl"			value="<c:out value='${command.srch_lwr_estimate_lvl}'/>" />
		<input type="hidden" name="srch_lwr_nm"			value="<c:out value='${command.srch_lwr_nm}'/>" />
		<input type="hidden" name="srch_expert_area"			value="<c:out value='${command.srch_expert_area}'/>" />

		<!-- key form-->
		<input type="hidden" name="lwr_no" value="<c:out value='${lom.lwr_no}'/>" />			
		<input type="hidden" name="lwr_nm" value="<c:out value='${lom.lwr_nm}'/>" />
		<input type="hidden" name="nation" value="<c:out value='${lom.nation}'/>" />
		<input type="hidden" name="expert_area" value="" />

		<!-- 첨부파일정보 시작 -->
		<input type="hidden" name="fileInfos" value="" />
		<input type="hidden" name="file_bigclsfcn" value="F005" />
		<input type="hidden" name="file_midclsfcn" value="F0050102" />
		<input type="hidden" name="file_smlclsfcn" value="" />
		<input type="hidden" name="ref_key"	value="<c:out value='${lom.lwr_no}'/>" />
		<c:choose>
			<c:when test="${lom.lwr_no == ''}">
				<input type="hidden" name="view_gbn" value="upload" />
			</c:when>
			<c:otherwise>
				<input type="hidden" name="view_gbn" value="modify" />
			</c:otherwise>
		</c:choose>

		<!-- 나모 웹 에디터 관련 -->
		<c:choose>
			<c:when test="${lom.lwr_no == ''}">
				<input type="hidden" name="body_mime" id="body_mime" value="" />
			</c:when>
			<c:otherwise>
				<input type="hidden" name="body_mime" id="body_mime" value="<c:out value='${lom.career_cont}' />" />
			</c:otherwise>
		</c:choose>

		<div id="wrap"> 
		<!-- container -->
		<div id="container">
			<!-- Location -->
			<div class="location">
				<img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0" alt="Home"> <c:out value='${menuNavi}' escapeXml="false" />
			</div>
			<!-- //Location -->
			<!-- title -->
			<div class="title">
				<c:choose>
					<c:when test="${command.lwr_no == ''}">
						<h3><spring:message code="las.page.field.lawservice.lwrmng" />&nbsp;<spring:message code="las.page.field.lawservice.register" /></h3>
					</c:when>
					<c:otherwise>
						<h3><spring:message code="las.page.field.lawservice.lwrmng" />&nbsp;<spring:message code="las.page.field.lawservice.modify" /></h3>
					</c:otherwise>
				</c:choose>
			</div>
			<!-- //title -->
			<!-- content -->
			<div id="content">
				<!-- view -->
				<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
						<colgroup>
								<col width="15%" />
								<col width="35%" />
								<col width="15%" />
								<col width="35%" />
							</colgroup>
						<tbody>
						<tr>
							<th>Last Name</th>
							<td><input required alt="Last Name" type="text" id="last_name" name="last_name" value="<c:out value='${lom.last_name}'/>" maxlength="32"
								class="text_full" style="width: 200px" />
							</td>
							<th>First Name</th>
							<td><input required alt="First Name" type="text" id="first_name" name="first_name" value="<c:out value='${lom.first_name}'/>" maxlength="32" class="text_full" style="width: 150px" />
								&nbsp;&nbsp;<span class="btn_option mL5"><span class="arrow"></span><a href="#" onclick="javascript:LawerSearch()"><spring:message code="las.page.field.lawservice.srchlwr" /></a> </span>
							</td>
						</tr>
						<tr>
							<th>Law Firm</th>
							<td colspan="3"><select id='lawfirm_id' name='lawfirm_id' style='width: 250px;'>
									<option value=''><spring:message code="las.page.field.lawservice.select" /></option>
									<c:forEach var="list" items="${lawfirmList}">
										<option value="<c:out value='${list.lawfirm_id}'/>"
											<c:if test='${list.lawfirm_id == lom.lawfirm_id}'>selected</c:if>>
											<c:out value='${list.lawfirm_nm}' />
										</option>
									</c:forEach>
							</select>
							</td>
						</tr>
						<tr>
							<th><spring:message code="las.page.field.lawservice.eventnm" /></th>
							<td colspan="3" style='border-left:1px solid #dddddd !important;padding: 0px 0px;border-bottom: 0px'>
								<table id="tbl_plc"
									
									width=100%>
									<c:choose>
										<c:when test="${(command.lwr_no == '') || (event2_list_cnt == '0')}">
											<tr>
												<td>
													<select id='event_no' name='event_no' style='width: 250px;'>
														<option value=''><spring:message code="las.page.button.lawservice.select"/></option>
														<c:forEach var="list" items="${eventList}">
															<option value="<c:out value='${list.event_no}'/>">
																<c:out value='${list.event_nm}' />
															</option>
														</c:forEach>
													</select> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<span class="btn_option"><span class="add"></span><a href="#" onclick='dataInsertPlc();'><spring:message code="las.page.button.lawservice.add"/></a> </span>
												</td>
											</tr>
										</c:when>
										<c:otherwise>
											<c:forEach var="list2" items="${eventList2}" varStatus="status">
												<tr>
													<td>
														<select id='event_no' name='event_no' style='width: 250px;'>
															<option value=''><spring:message code="las.page.button.lawservice.select"/></option>
															<c:forEach var="list" items="${eventList}">
																<option value="<c:out value='${list.event_no}'/>" <c:if test='${list.event_no == list2.event_no}'>selected</c:if>>
																	<c:out value='${list.event_nm}' />
																</option>
															</c:forEach>
														</select> 
														<c:choose>
															<c:when test='${ status.count > 1 }'>
																<span class='btn_option'><span class='delete'></span><a href='#' onclick='dataDeletePlc(this);'><spring:message code="las.page.button.lawservice.del"/></a> </span>
															</c:when>
															<c:otherwise>
																&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="btn_option"><span class="add"></span><a href="#" onclick='dataInsertPlc();'><spring:message code="las.page.button.lawservice.add"/></a> </span>
															</c:otherwise>
														</c:choose>
													</td>
												</tr>
											</c:forEach>
										</c:otherwise>
									</c:choose>

								</table>
							</td>
						</tr>
						<tr>
							<th>Territory</th>
							<td colspan="3">
								<input type="text" id="nation_nm" name="nation_nm" value="<c:out value='${lom.nation_nm}'/>" readonly class="text_full" style="width: 200px" size="20" onclick="javascript:NationPop();" maxlength="20" class=form1>
									 &nbsp;&nbsp;<a	href="javascript:NationPop();"><spring:message code="las.page.field.lawservice.selectnation" /></a>
							</td>
						</tr>
						<tr>
							<th>Title</th>
							<td>
								<select id="lwr_jikgup" name="lwr_jikgup" style="width: 200px;">
								</select>
							</td>
							<th><spring:message code="las.page.field.lawservice.lwrcontact" /></th>
							<td><input id="telno" name="telno" value="<c:out value='${lom.telno}'/>" maxlength="32" type="text" class="text_full" style="width: 200px" alt="<spring:message code="las.page.field.lawservice.lwrcontact" />" maxLength="32" />
							</td>
						</tr>
						<tr>
							<th><spring:message code="las.page.field.lawservice.lwrestmt" /></th>
							<td>
								<select id="lwr_estmt_lvl" name="lwr_estmt_lvl" style="width: 200px;">
									<option><spring:message code="las.page.field.lawservice.select" /></option>
								</select>
							</td>
							<th><spring:message code="las.page.field.lawservice.lwremail" /></th>
							<td><input id="email" name="email" value="<c:out value='${lom.email}'/>" alt="E-MAIL" maxlength="100" type="text" class="text_full" style="width: 200px" email />
							</td>
						</tr>
						<tr>
							<th><spring:message code="las.page.field.lawservice.lwrestcont" />
									<span class="btn_option"><span class="add"></span><a href="#" onclick='dataInsertPlc2();'><spring:message code="las.page.button.lawservice.add"/></a></span>
							</th>
							<td class="tal_lineL" colspan="3" style='border-left:1px solid #dddddd !important;padding: 0px 0px;border-bottom: 0px'>
								<table id="tbl_plc2" style="line-height: 0px; padding: 0px 0px; border-left: 0px solid #cadbe2; border-bottom: 0px solid #CADBE2; color: #7b858e; text-align: left;" width=100%>
									<c:choose>
										<c:when test="${lom.lwr_no == ''}">
											<tr>
												<td width=85% align="left">
													<ul class="list_assess">
														<li><input type="text" id="text_field" name="estmt_title" value="" class="text" alt="<spring:message code="las.page.field.lawservice.estmtnm" />" maxLength="50" /></li>
														<li><textarea id="textarea" name="estmt_cont" cols="8" rows="7" class="text_area_01" alt="<spring:message code="las.page.field.lawservice.lwrestcont" />" maxLength="1000"  ></textarea></li>
													</ul>
												</td>
											</tr>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${command.estimate_list_cnt == 0}">
													<tr>
														<td width=85% align="left">
															<ul class="list_assess">
																<li><input type="text" id="text_field" name="estmt_title" value="" class="text" alt="<spring:message code="las.page.field.lawservice.estmtnm" />" maxLength="50" /></li>
																<li><textarea id="textarea" name="estmt_cont" cols="8" rows="7" class="text_area_01" alt="<spring:message code="las.page.field.lawservice.lwrestcont" />" maxLength="1000"  ></textarea></li>
															</ul>
														</td>
													</tr>
												</c:when>
												<c:otherwise>
													<c:forEach var="list" items="${estimateList}" varStatus="status">
													<tr>
														<td width=85% align="left">														
																<ul class="list_assess">																
																	<li>
																		<input type="text" id="text_field" name="estmt_title"  value="<c:out value='${list.estmt_title}'/>" class="text" alt="<spring:message code="las.page.field.lawservice.estmtnm" />" maxLength="50" />
																			<c:choose>
																				<c:when test='${ status.count > 1 }'>
																					&nbsp;<span class='btn_option' ><span class='delete' style='vertical-align:8px'></span><a href='#' onclick='dataDeletePlc2(this);' style='vertical-align:7px'><spring:message code="las.page.button.lawservice.del"/></a></span>
																				</c:when>
																			</c:choose>
																	</li>
																	<li>
																		<textarea id="textarea" name="estmt_cont" cols="10" rows="7" class="text_area_01" alt="<spring:message code="las.page.field.lawservice.lwrestcont" />" maxLength="1000"  ><c:out value='${list.estmt_cont}' /></textarea>
																	</li>																	
																</ul>										
														</td>
													</tr>
													</c:forEach>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</table></td>
						</tr>
						<tr>
							<th><spring:message code="las.page.field.lawservice.lawschool" /></th>
							<td colspan="3">
								<select id="lawschool" name="lawschool" style="width: 350px;">
								</select>
								<input id="lawschool_grdtyear" name="lawschool_grdtyear" value="<c:out value='${lom.lawschool_grdtyear}'/>" type="text" alt="<spring:message code="las.page.field.lawservice.grdyear" />" maxlength="4" class="text_full" style="width: 100px" /> (<spring:message code="las.page.field.lawservice.grdyear" />)
							</td>
						</tr>
						<tr>
							<th><spring:message code="las.page.field.lawservice.fromschool" /></th>
							<td colspan="3">
								<input id="from_school" name="from_school" value="<c:out value='${lom.from_school}'/>" type="text" class="text_full" style="width: 350px" alt="<spring:message code="las.page.field.lawservice.fromschool" />" maxLength="128" /> 
								<input id="from_school_grdtyear" name="from_school_grdtyear" value="<c:out value='${lom.from_school_grdtyear}'/>" alt="<spring:message code="las.page.field.lawservice.grdyear" />" type="text" class="text_full" style="width: 100px" maxlength="4" /> (<spring:message code="las.page.field.lawservice.grdyear" />)
								</td>
						</tr>
						<tr>
							<th><spring:message code="las.page.field.lawservice.expertarea" /></th>
							<td colspan="3">
								<table border='0' cellspacing='0' cellpadding='0' class='tal_z'>
									<tr>
										<c:forEach var="list" items="${expertAreaList}" varStatus="status">
											<td>
												<input id='<c:out value='${list.cd}'/>'	name='att_speciality' type='checkbox' />
												<c:out value='${list.cd_nm}' />
											</td>
												<c:if test="${status.count mod 5==0}">
													</tr>
													<tr>
												</c:if>
										</c:forEach>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th><spring:message code="las.page.field.lawservice.lwrresume" /></th>
							<td colspan="3">
								<%@ include file="/WEB-INF/jsp/secfw/common/NamoEditInclude.jspf"%>
							</td>
						</tr>
						<tr class="end">
							<th><spring:message code="las.page.field.lawservice.attach" /></th>
							<td colspan="3">
								<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="100px" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
							</td>
						</tr>
					</tbody>
				</table>
				<!-- //view -->
				<div class="t_titBtn">
					<div class="btn_wrap">
						<span class="btn_all_b"> <span class="save"></span><a href="#" onclick="javascript:pageAction('insert')"><spring:message code="las.page.button.lawservice.save"/></a></span> 
						<span class="btn_all_w"> <span class="cancel"></span><a href="#" onclick="javascript:pageAction('cancel')"><spring:message code="las.page.button.lawservice.cancel"/></a></span>
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
<!-- 나모 웹에디터 관련 추가 -->
		<c:choose>
			<c:when test="${lom.lwr_no == ''}">
				<SCRIPT language="javascript" FOR="wec" EVENT="OnInitCompleted()">
					var wecObj = document.wec;
					wecObj.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
					wecObj.SetDefaultFontSize("9");
					wecObj.EditMode = 1;
				</SCRIPT>
			</c:when>
			<c:otherwise>
				<SCRIPT language="javascript" FOR="wec" EVENT="OnInitCompleted()">
					var wecObj = document.wec;
					wecObj.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
					wecObj.SetDefaultFontSize("9");
					wecObj.EditMode = 1;
					wecObj.Value = document.frm.body_mime.value;
				</SCRIPT>
			</c:otherwise>
		</c:choose>
</html>
