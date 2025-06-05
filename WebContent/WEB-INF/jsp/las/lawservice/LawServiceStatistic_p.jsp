<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%--
/**
 * 파  일  명 : LawServiceStaticstic_p.jsp
 * 프로그램명 : 로펌 통계 팝업
 * 설      명 : 
 * 작  성  자 : 박병주
 * 작  성  일 : 2011.10
 */
--%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><spring:message code="las.main.title" /></title>
<LINK href="<%=CSS%>/las.css" type="text/css" rel="stylesheet" >
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript">

	/**
	* 초기화면 로딩 
	*/
	$(document).ready(function(){
		
		var frm = document.frm;
		
		// 사건구분1
		getCodeSelectByUpCd2("frm", "srch_kbn1", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+"106"+"&combo_abbr=F&combo_type=S2&combo_use_yn=Y&combo_selected="+"<c:out value='${command.srch_kbn1}'/>");
	
		if("<c:out value='${command.srch_kbn1}'/>" != ''){
			StReload_partin("<c:out value='${command.srch_kbn1}'/>");
		}		
		
		// 소송상대 리스트
		getCodeSelectByUpCd2("frm", "srch_lawsuit_trgt_cd", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+"105"+"&combo_abbr=F&combo_type=S2&combo_use_yn=Y&combo_selected="+"<c:out value='${command.srch_lawsuit_trgt_cd}'/>");

	});
	
	/**
	*  사건구분2  reload
	*/
	function StReload_partin(index){
		$(partin2).html("<select name='srch_kbn2' id='srch_kbn2' alt='<spring:message code='las.page.field.lawservice.event2'/>' style='width:92px;'></select>");
		getCodeSelectByUpCd2("frm", "srch_kbn2", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+ index +"&combo_abbr=F&combo_type=S2&combo_use_yn=Y&combo_selected="+"<c:out value='${command.srch_kbn2}'/>");
	} 
	
	/**
	* 검색 버튼 동작 부분
	*/ 
	function pageAction(){
		
		var frm = document.frm;		
		var flag = $('#srch_type').val();
				
		if($('#srch_year').val() == ''){
			alert("<spring:message code='las.page.msg.lawservice.setsrchyearcond' />");
			return;
		}
 
		viewHiddenProgress(true) ;
		
		frm.target = "_self";
		frm.action = "<c:url value='/las/lawservice/lawServiceStatistic.do' />";
		frm.method.value = "popLawServiceStatistic";
		frm.submit();
	}
	
	/**
	* 메시지 처리
	*/
	function showMessage(msg) {
		if( msg != "" && msg != null && msg.length > 1 ) {
			alert(msg);
		}
	}
	
	/**
	* 엑셀 다운 팝업 
	*/
	function dnExcel()
	{			
		var frm = document.frm;
		frm.action = "<c:url value='/las/lawservice/lawServiceStatistic.do' />";
		frm.method.value="excelDownLoad";
		frm.submit();
	}

//-->
</script>
</head>

<body onLoad='showMessage("<c:out value='${returnMessage}'/>");' >

<form:form name="frm" id='frm' method="post" autocomplete="off"> 
<input type="hidden" name="method"  value="" />
<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>" />	
<input type="hidden" name="forward_from"  value="pop" />
<input type="hidden" name="forward_url"  value="" />
<!-- search form -->

<!-- key Form -->


<!-- EXCEL 관련-->
<input type="hidden" id="exel_nm_pop" 		name="exel_nm_pop" 		value="" />
<input type="hidden" id="exel_vel_pop" 		name="exel_vel_pop" 	value="" />	
<input type="hidden" id="exel_nm" 			name="exel_nm" 			value="" />
<input type="hidden" id="exel_vel" 			name="exel_vel" 		value="" />
<input type="hidden" id="start_datetime"	name="start_datetime" 	value="" />
<input type="hidden" id="end_datetime" 		name="end_datetime" 	value="" />	

<div id="wrap" style="width:3000px">
<!-- container -->
<div id="container">
	<!-- title -->
	<div class="title">
		<h3><spring:message code="las.page.field.lawservice.lgStats"/></h3>
	</div>
	<!-- //title -->
	<!-- content -->
	<div id="content">	
		<!--search-->        
    <div class="search_box" style="width:980px">
				<div class="search_box_inner"  >
					<table class="search_tb" id="searchbox" >
						<colgroup>
							<col/>
							<col width="75px"/>
							</colgroup>
							<tr>
							<td>
							<table class="search_form"  >
								<colgroup>
									<col width="120px"/>
									<col width="320px"/>
									<col width="120px"/>
									<col width="40px"/>
								</colgroup>
								<tr>
									<th><spring:message code="las.page.field.lawservice.stactictype"/></th>
									<td>
										<select id='srch_type' name='srch_type' >
											<option value="">--<spring:message code="las.page.msg.lawservice.setsrchtype" />--</option>
				                			<option value="event" <c:if test="${command.srch_type == 'event'}"> selected </c:if>  ><spring:message code="las.page.field.lawservice.stbyevent"/></option>
				                			<option value="lawfirm" <c:if test="${command.srch_type == 'lawfirm'}"> selected </c:if> ><spring:message code="las.page.field.lawservice.stbylawfirm"/></option>
										</select>
									</td>
									<th><spring:message code="las.page.field.lawservice.kbn" /></th>
									<td>
										<select id="srch_kbn1" name="srch_kbn1" onChange="StReload_partin(this.value)">
										</select>
										<span id="partin2"></span>
									</td>

							  	</tr>
								<tr>
									<th><spring:message code="las.page.field.lawservice.year"/></th>
									<td >
										<select id="srch_year_type" name="srch_year_type">
				                			<option value="SRCH_REMITDAY" <c:if test="${command.srch_year_type == 'SRCH_REMITDAY'}"> selected </c:if>><spring:message code="las.page.field.lawservice.paid"/></option>               			
				                			<option value="SRCH_ACPTDAY" <c:if test="${command.srch_year_type == 'SRCH_ACPTDAY'}"> selected </c:if>><spring:message code="las.page.field.lawservice.occur"/></option>               			
				                			<option value="SRCH_SRVCSTARTDAY" <c:if test="${command.srch_year_type == 'SRCH_SRVCSTARTDAY'}"> selected </c:if>><spring:message code="las.page.field.lawservice.srvfromto"/></option>               			
				                			<option value="SRCH_CLAIMDAY" <c:if test="${command.srch_year_type == 'SRCH_CLAIMDAY'}"> selected </c:if>><spring:message code="las.page.field.lawservice.claimday"/></option>               			
										</select>	
										
										<select id="srch_year" name="srch_year">
				                			<option value=""><spring:message code="las.page.field.lawservice.select" /></option> 
				                			<c:forEach var="list" items="${year_list}" varStatus="st">
				                				<option value="<c:out value='${list}'/>" <c:if test="${command.srch_year == list}"> selected </c:if>><c:out value='${list}'/></option>               	
				     			          	</c:forEach>
				     			          	<option value="1111" <c:if test="${command.srch_year == '1111'}"> selected </c:if>><spring:message code="las.page.field.lawservice.all"/></option>
										</select>	
					

									</td>
									<th><spring:message code="las.page.field.lawservice.total"/></th>
									<td>
										<select id="srch_group_cd" name="srch_group_cd" >
											<option value=''><spring:message code="las.page.field.lawservice.select" /></option>	
											<c:forEach var="list" items="${srch_dept_list}">
											<!-- <option value="<c:out value='${list.intnl_dept_cd}'/>" <c:if test='${list.intnl_dept_cd == command.srch_group_cd}'> selected</c:if>><c:out value='${list.dept_nm}'/></option> -->
											<option value="<c:out value='${list.dept_nm}'/>" <c:if test='${list.dept_nm == command.srch_group_cd}'> selected</c:if>><c:out value='${list.dept_nm}'/></option>						
											</c:forEach>														
										</select>	
									</td>
							  	</tr>
							  	<tr>
									<th><spring:message code="las.page.field.lawservice.lawsuittrgt" /></th>
									<td >
										<select id="srch_lawsuit_trgt_cd" name="srch_lawsuit_trgt_cd" style='width:250px;'>
				                		</select>	
									</td>
									<th><spring:message code="las.page.field.lawservice.exp"/>/<spring:message code="las.page.field.lawservice.for"/></th>
									<td >
										<select id="srch_money_type" name="srch_money_type">
				                			<option value=""><spring:message code="las.page.field.lawservice.frCurrnWon"/></option>
				                			<option value="FWD" <c:if test="${command.srch_money_type == 'FWD'}"> selected </c:if>><spring:message code="las.page.field.lawservice.frCurrn"/></option>
				                			<option value="KWD" <c:if test="${command.srch_money_type == 'KWD'}"> selected </c:if>><spring:message code="las.page.field.lawservice.won"/></option>                			
				                			<option value="USD" <c:if test="${command.srch_money_type == 'USD'}"> selected </c:if>>USD</option>      
										</select>	
									</td>
								<tr>
									<th><spring:message code="las.page.field.lawservice.event" /></th>
									<td colspan=3" >
										<select id='srch_event_no' name='srch_event_no' style='width:250px;'>
											<option value=''><spring:message code="las.page.field.lawservice.select" /></option>	
											<c:forEach var="list" items="${srch_event_list}">
											<option value="<c:out value='${list.event_no}'/>" <c:if test='${list.event_no == command.srch_event_no}'> selected</c:if>><c:out value='${list.event_nm}'/></option>			
											</c:forEach>
										</select>
									</td>
							  	</tr>
							  	<tr>
									<th>LAWFIRM</th>
									<td colspan=3" >
										<select id='srch_lawfirm_id' name='srch_lawfirm_id' style='width:250px;'>
											<option value=''><spring:message code="las.page.field.lawservice.select" /></option>	
											<c:forEach var="list" items="${srch_lawfirm_list}">
											<option value="<c:out value='${list.lawfirm_id}'/>" <c:if test='${list.lawfirm_id == command.srch_lawfirm_id}'> selected </c:if>><c:out value='${list.lawfirm_nm}'/></option>			
											</c:forEach>
										</select>
									</td>
							 	</tr>
							 </table>
						</td>
						<td class="vb tC">
							<a href="javascript:pageAction('search');"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="las.page.button.lawservice.read" />"/></a>
						</td>
						</tr>
					</table>

				</div>

	  		</div>
			<!--//search-->	
			<div class="btn_wrap fR" style="position:relative">
				<span class="btn"><span class="excel_down"></span><a href="javascript:dnExcel();"><spring:message code="las.page.button.lawservice.exceldn" /></a></span>
			</div>

						
		<!-- list -->
		<table border="0" cellspacing="0" cellpadding="0" class="list_basic tr_nobg" style="margin-bottom:30px;" >
			<colgroup>
				<col width="150px" />
				<col width="150px" />
				<col width="150px" />
				<col width="40px" />
				<c:forEach begin="1" end="26" varStatus="status" step="1" >
					<col />
				</c:forEach>
			</colgroup>
		  	<thead>
				<tr>
					<c:if test="${command.srch_type == 'event'}">
						<th rowspan="2" ><spring:message code="las.page.field.lawservice.event" /></th>
						<th rowspan="2" >LAWFIRM</th>
					</c:if>
					<c:if test="${command.srch_type == 'lawfirm'}">
						<th rowspan="2" >LAWFIRM</th>
						<th rowspan="2" ><spring:message code="las.page.field.lawservice.event" /></th>
					</c:if>
					<th rowspan="2"><spring:message code="las.page.field.lawservice.lawsuittrgt" /></th>
					<th rowspan="2"><spring:message code="las.page.field.lawservice.currency"/></th>
						<c:forEach begin="1" end="12" varStatus="status" step="1" >
							<th colspan="2" rowspan="1" style="width:140px;"  ><c:out value="${status.count}"/><spring:message code="las.page.field.lawservice.month"/></th>
				  		</c:forEach>
				  	<th colspan="2" rowspan="1" style="width:140px;" ><spring:message code="las.page.field.lawservice.totalsum"/></th>
				</tr>
				<tr>
					<c:forEach begin="1" end="13" varStatus="status" step="1" >
						<c:if test="${command.srch_money_type == ''}"><th class="tal_lineL tal_bg_cor01" style="width:70px;" ><spring:message code="las.page.field.lawservice.exp"/></th><th class="tal_bg_cor01" style="width:70px;" ><spring:message code="las.page.field.lawservice.for"/></th></c:if>
						<c:if test="${command.srch_money_type == 'FWD'}"><th colspan="2"  class="tal_lineL tal_bg_cor01" style="width:140px;" ><spring:message code="las.page.field.lawservice.frCurrn"/></th></c:if>
						<c:if test="${command.srch_money_type == 'KWD'}"><th colspan="2"  class="tal_lineL tal_bg_cor01" style="width:140px;" ><spring:message code="las.page.field.lawservice.won"/></th></c:if>
						<c:if test="${command.srch_money_type == 'USD'}"><th colspan="2"  class="tal_lineL tal_bg_cor01" style="width:140px;" >USD</th></c:if>						
				  	</c:forEach>
				</tr>
		  	</thead>
		  <tbody>
		  	<c:choose>
				<c:when test="${command.forward_from == 'pop'}">
		  		 <tr>
		  			<c:if test="${command.srch_money_type == ''}">
		  			<td colspan="4"><spring:message code="las.page.field.lawservice.sum"/></td>
			  			<c:forEach var="list" items="${lom_sum_all}"  varStatus="status" >
							<td class="tR"><c:out value='${list}'/></td>
			  			</c:forEach>
			  		</c:if>	
			  		<c:if test="${command.srch_money_type == 'FWD'}">
			  		<td colspan="4"><spring:message code="las.page.field.lawservice.sum"/></td>
			  			<c:forEach var="list" items="${lom_sum_fore}"  varStatus="status" >
							<td class="tR" colspan="2"><c:out value='${list}'/></td>
			  			</c:forEach>
			  		</c:if>
			  		<c:if test="${command.srch_money_type == 'KWD'}">
			  		<td colspan="4"><spring:message code="las.page.field.lawservice.sum"/></td>
			  			<c:forEach var="list" items="${lom_sum_exp}"  varStatus="status" >
							<td class="tR" colspan="2"><c:out value='${list}'/></td>
			  			</c:forEach>
			  		</c:if>		
		  		</tr>
		  		<c:forEach var="list" items="${resultSumList}">
		  		<tr>		  		
		  			<c:if test="${command.srch_money_type == ''}">
		  				<td colspan="3"><spring:message code="las.page.field.lawservice.sumbycurrency"/></td>
		  				<td><c:out value='${list.crrncy_unit}'/></td>
						<td class="tR" ><c:out value='${list.for01}'/></td>
						<td class="tR" ><c:out value='${list.exp01}'/></td>
						<td class="tR" ><c:out value='${list.for02}'/></td>
						<td class="tR" ><c:out value='${list.exp02}'/></td>
						<td class="tR" ><c:out value='${list.for03}'/></td>
						<td class="tR" ><c:out value='${list.exp03}'/></td>
						<td class="tR" ><c:out value='${list.for04}'/></td>
						<td class="tR" ><c:out value='${list.exp04}'/></td>
						<td class="tR" ><c:out value='${list.for05}'/></td>
						<td class="tR" ><c:out value='${list.exp05}'/></td>
						<td class="tR" ><c:out value='${list.for06}'/></td>
						<td class="tR" ><c:out value='${list.exp06}'/></td>
						<td class="tR" ><c:out value='${list.for07}'/></td>
						<td class="tR" ><c:out value='${list.exp07}'/></td>
						<td class="tR" ><c:out value='${list.for08}'/></td>
						<td class="tR" ><c:out value='${list.exp08}'/></td>
						<td class="tR" ><c:out value='${list.for09}'/></td>
						<td class="tR" ><c:out value='${list.exp09}'/></td>
						<td class="tR" ><c:out value='${list.for10}'/></td>
						<td class="tR" ><c:out value='${list.exp10}'/></td>
						<td class="tR" ><c:out value='${list.for11}'/></td>
						<td class="tR" ><c:out value='${list.exp11}'/></td>
						<td class="tR" ><c:out value='${list.for12}'/></td>
						<td class="tR" ><c:out value='${list.exp12}'/></td>
						<td class="tR" ><c:out value='${list.forsum}'/></td>
						<td class="tR" ><c:out value='${list.expsum}'/></td>
					</c:if>
				    <c:if test="${command.srch_money_type == 'FWD'}">
				    	<td colspan="3"><spring:message code="las.page.field.lawservice.sumbycurrency"/></td>
		  				<td><c:out value='${list.crrncy_unit}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for01}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for02}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for03}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for04}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for05}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for06}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for07}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for08}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for09}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for10}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for11}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for12}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.forsum}'/></td>
					</c:if>
					<c:if test="${command.srch_money_type == 'KWD'}">
						<td colspan="3"><spring:message code="las.page.field.lawservice.sumbycurrency"/></td>
		  				<td><c:out value='${list.crrncy_unit}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp01}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp02}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp03}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp04}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp05}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp06}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp07}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp08}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp09}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp10}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp11}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp12}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.expsum}'/></td>
					</c:if>
					<c:if test="${command.srch_money_type == 'USD'}">
						<td colspan="4"> <spring:message code="las.page.field.lawservice.sum"/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp01}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp02}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp03}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp04}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp05}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp06}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp07}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp08}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp09}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp10}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp11}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp12}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.expsum}'/></td>
					</c:if>
		  			
		  		
		  		</tr>
		  		</c:forEach>		  
				<c:forEach var="list" items="${resultList}">
				<tr>
					<c:if test="${command.srch_type == 'event'}">
					    <td><c:out value='${list.event_nm}'/></td>
					    <td><c:out value='${list.lawfirm_nm}'/></td>
					    <td><c:out value='${list.lawsuit_trgt_cd}'/></td>

				    </c:if>
				    <c:if test="${command.srch_type == 'lawfirm'}">
					    <td><c:out value='${list.lawfirm_nm}'/></td>
					    <td><c:out value='${list.event_nm}'/></td>
					    <td><c:out value='${list.lawsuit_trgt_cd}'/></td>

				    </c:if>
				    <c:if test="${command.srch_money_type == ''}">
				    	<td><c:out value='${list.crrncy_unit}'/></td>
						<td class="tR" ><c:out value='${list.for01}'/></td>
						<td class="tR" ><c:out value='${list.exp01}'/></td>
						<td class="tR" ><c:out value='${list.for02}'/></td>
						<td class="tR" ><c:out value='${list.exp02}'/></td>
						<td class="tR" ><c:out value='${list.for03}'/></td>
						<td class="tR" ><c:out value='${list.exp03}'/></td>
						<td class="tR" ><c:out value='${list.for04}'/></td>
						<td class="tR" ><c:out value='${list.exp04}'/></td>
						<td class="tR" ><c:out value='${list.for05}'/></td>
						<td class="tR" ><c:out value='${list.exp05}'/></td>
						<td class="tR" ><c:out value='${list.for06}'/></td>
						<td class="tR" ><c:out value='${list.exp06}'/></td>
						<td class="tR" ><c:out value='${list.for07}'/></td>
						<td class="tR" ><c:out value='${list.exp07}'/></td>
						<td class="tR" ><c:out value='${list.for08}'/></td>
						<td class="tR" ><c:out value='${list.exp08}'/></td>
						<td class="tR" ><c:out value='${list.for09}'/></td>
						<td class="tR" ><c:out value='${list.exp09}'/></td>
						<td class="tR" ><c:out value='${list.for10}'/></td>
						<td class="tR" ><c:out value='${list.exp10}'/></td>
						<td class="tR" ><c:out value='${list.for11}'/></td>
						<td class="tR" ><c:out value='${list.exp11}'/></td>
						<td class="tR" ><c:out value='${list.for12}'/></td>
						<td class="tR" ><c:out value='${list.exp12}'/></td>
						<td class="tR" ><c:out value='${list.forsum}'/></td>
						<td class="tR" ><c:out value='${list.expsum}'/></td>
					</c:if>
				    <c:if test="${command.srch_money_type == 'FWD'}">
				    	<td><c:out value='${list.crrncy_unit}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for01}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for02}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for03}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for04}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for05}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for06}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for07}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for08}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for09}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for10}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for11}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.for12}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.forsum}'/></td>
					</c:if>
					<c:if test="${command.srch_money_type == 'KWD'}">
						<td><c:out value='${list.crrncy_unit}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp01}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp02}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp03}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp04}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp05}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp06}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp07}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp08}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp09}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp10}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp11}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp12}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.expsum}'/></td>
					</c:if>
					<c:if test="${command.srch_money_type == 'USD'}">
						<td>USD</td>
						<td class="tR" colspan="2" ><c:out value='${list.exp01}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp02}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp03}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp04}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp05}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp06}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp07}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp08}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp09}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp10}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp11}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.exp12}'/></td>
						<td class="tR" colspan="2" ><c:out value='${list.expsum}'/></td>
					</c:if>

				</tr>
				</c:forEach>
			
				</c:when>
				    <c:otherwise>
						<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
							<td colspan="30" align="center"><spring:message code="las.msg.succ.noResult" /></td>
						</tr>
				    </c:otherwise>
				</c:choose>
		  </tbody>
		  </table>
		<!-- //list -->
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