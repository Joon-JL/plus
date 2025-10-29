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
 * 파  일  명 : LawServiceStaticstic_l.jsp
 * 프로그램명 : 로펌 통계 목록
 * 설      명 : 
 * 작  성  자 : 박병주
 * 작  성  일 : 2011.10
 */
--%>
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
<script language="javascript">

	/**
	* 초기화면 로딩 
	*/
	$(document).ready(function(){
		
		var frm = document.frm;
				
		// 사건구분1
		getCodeSelectByUpCd2("frm", "srch_kbn1", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+"106"+"&combo_abbr=F&combo_type=S2&combo_use_yn=Y&combo_selected="+"<c:out value='${command.srch_kbn1}'/>");
		
		if("<c:out value='${command.srch_kbn1}'/>" != ''){
			$(partin2).html("<select name='srch_kbn2' id='srch_kbn2' alt='<spring:message code='las.page.field.lawservice.event2'/>' style='width:92px;'></select>");
			getCodeSelectByUpCd2("frm", "srch_kbn2", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+"<c:out value='${command.srch_kbn1}'/>"+"&combo_abbr=F&combo_type=S2&combo_use_yn=Y&combo_selected="+"<c:out value='${command.srch_kbn2}'/>");
		}	
		
		type_reload("<c:out value='${command.srch_type}'/>");			
	
	});
		
	/**
	*  사건구분2  reload
	*/
	function StReload_partin(index){
		$(partin2).html("<select name='srch_kbn2' id='srch_kbn2' alt='사건구분2' style='width:92px;'></select>");
		getCodeSelectByUpCd2("frm", "srch_kbn2", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+ index +"&combo_abbr=F&combo_type=S2&combo_use_yn=Y&combo_selected=");
	}
	
	function type_reload(flag)
	{		
		// 검색BOX초기화
		$('#tr_kbn').hide();
		$('#tr_year').hide();
		$('#tr_event').hide();
		$('#tr_lawfirm').hide();
		
		// 결과LIST 초기화
		$('#tb_list_group').hide();
		$('#tb_list_DOJ').hide();
		$('#tb_list_DOJ_dnExcel').hide();
		$('#tb_list_year').hide();
		
		if ( flag == "group" ){
			$('#tr_kbn').show();
			$('#tr_year').show();
			$('#tb_list_group').show();
		} else if ( flag == "DOJ" ){
			$('#tr_event').show();
			$('#tr_lawfirm').show();
			$('#tb_list_DOJ').show();
			$('#tb_list_DOJ_dnExcel').show();
		} else if ( flag == "event_all" ){
			$('#tr_event').show();
			$('#tb_list_year').show();
		} else {
			$('#tr_year').show();
		}
	}	
	/**
	* 검색 버튼 동작 부분
	*/ 
	function pageAction(mode){
		
		var frm = document.frm;		
		var flag = $('#srch_type').val();				
		if(flag==''){
			alert("<spring:message code='las.page.msg.lawservice.setsrchcond' />");
			return;
		}
		
		var winpos = "left=0,top=0";
		var winstyle="height=800,width=1024,status=no,toolbar=no,menubar=no,location=no,resizable=no,"
		      +	 "scrollbars=yes,copyhistory=no," + winpos;

		if(flag == "event"){

       		PopUpWindow = window.open('', "PopUpWindow" , winstyle);
			frm.action = "<c:url value='/las/lawservice/lawServiceStatistic.do' />" ;
			frm.method.value="popLawServiceStatistic";
			frm.target = "PopUpWindow";
			frm.submit();

		} else if(flag == "lawfirm"){
			
			PopUpWindow = window.open('', "PopUpWindow" , winstyle);
			frm.action = "<c:url value='/las/lawservice/lawServiceStatistic.do' />" ;
			frm.method.value="popLawServiceStatistic";
			frm.target = "PopUpWindow";
			frm.submit();

		} else if(flag == "group"){
						
			if($('#srch_year').val() == ''){
				alert("<spring:message code='las.page.msg.lawservice.setsrchyearcond' />");
				return;
			}
			
			viewHiddenProgress(true) ;
			frm.target = "_self";
			frm.action = "<c:url value='/las/lawservice/lawServiceStatistic.do' />";
		    frm.method.value = "listLawServiceStatistic";
			frm.submit();

		} else if(flag == "DOJ"){

			viewHiddenProgress(true) ;
			frm.target = "_self";
			frm.action = "<c:url value='/las/lawservice/lawServiceStatistic.do' />";
			
			if(mode=='dnExcel'){
				frm.method.value = "dnExcelDOJ";
			} else {
				frm.method.value = "listLawServiceStatistic";
			}		    
			frm.submit();
		
		} else if(flag == "event_all"){
			
			if($('#srch_event_no').val() == ''){
				alert("<spring:message code='las.page.msg.lawservice.setsrchevent' />");
				return;
			}

			viewHiddenProgress(true) ;
			frm.target = "_self";
			frm.action = "<c:url value='/las/lawservice/lawServiceStatistic.do' />";
		    frm.method.value = "listLawServiceStatistic";
			frm.submit();
		}
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

<body onLoad='showMessage("<c:out value='${returnMessage}'/>");'>

<form:form name="frm" id='frm' method="post" autocomplete="off"> 
<input type="hidden" name="method"  value="" />
<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>" />	

<!-- key Form -->
<input type="hidden" name="forward_url"  value="" />
<input type="hidden" name="forward_from"  value="mother" />
<input type="hidden" id="search_flag" name="search_flag"  value="" />
<input type="hidden" id="dnContents" name="dnContents"  value="" />

<div id="wrap">
<!-- container -->
<div id="container">
<!-- Location -->
<div class="location"><IMG SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
<!-- //Location -->	
	<!-- title -->
	<div class="title">
		<h3><spring:message code="las.page.field.lawservice.statistic"/></h3>
	</div>
	<!-- //title -->
	<!-- content -->
	<div id="content">	
	
	<!--search-->        
    <div class="search_box">
				<div class="search_box_inner">
					<table class="search_tb" id="searchbox" >
						<colgroup>
							<col/>
							<col width="75px"/>
							</colgroup>
							<tr>
							<td>
							<table class="search_form" id="srchtb">
								<colgroup>
									<col width="15%"/>
									<col width="35%"/>
									<col width="15%"/>
									<col width="35%"/>
								</colgroup>
								<tr id="tr_type">
									<th><spring:message code="las.page.field.lawservice.stactictype"/></th>
									<td>
										<select id='srch_type' name='srch_type' onChange="type_reload(this.value);" >
											<option value="">--<spring:message code="las.page.msg.lawservice.setsrchtype" />--</option>
				                			<option value="event" <c:if test="${command.srch_type == 'event'}"> selected </c:if>  ><spring:message code="las.page.field.lawservice.stbyevent"/></option>
				                			<option value="lawfirm" <c:if test="${command.srch_type == 'lawfirm'}"> selected </c:if> ><spring:message code="las.page.field.lawservice.stbylawfirm"/></option>
											<option value="group" <c:if test="${command.srch_type == 'group'}"> selected </c:if>><spring:message code="las.page.field.lawservice.stbytotal"/></option>                			                			
											<option value="DOJ" <c:if test="${command.srch_type == 'DOJ'}"> selected </c:if>  ><spring:message code="las.page.field.lawservice.stbydoj"/></option> 
											<option value="event_all" <c:if test="${command.srch_type == 'event_all'}"> selected </c:if>><spring:message code="las.page.field.lawservice.stbyyear"/></option>									
										</select>
									</td>
								<tr id="tr_kbn" colspan=3" >
									<th><span id="kbn_dt" ><spring:message code="las.page.field.lawservice.kbn" /></span>&nbsp;</th>
									<td>
										<div id="kbn_dd">
											<select id="srch_kbn1" name="srch_kbn1" onChange="StReload_partin(this.value)">
											</select>
											<span id="partin2"></span>&nbsp;
										</div>
									</td>								
								</tr>
								<tr id="tr_year">
									<th><spring:message code="las.page.field.lawservice.year"/></th>
									<td colspan=3" >
										<select id="srch_year" name="srch_year">
				                			<option value=""><spring:message code="las.page.field.lawservice.select" /></option> 
				                			<c:forEach var="list" items="${year_list}" varStatus="st">
				                				<option value="<c:out value='${list}'/>" <c:if test="${command.srch_year == list}"> selected </c:if>><c:out value='${list}'/></option>               	
				     			          	</c:forEach>
				     			          	<option value="1111" <c:if test="${command.srch_year == '1111'}"> selected </c:if>><spring:message code="las.page.field.lawservice.all"/></option>
										</select>	
									</td>
							  </tr>
								<tr id="tr_event">
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
							  	<tr id="tr_lawfirm">
									<th><spring:message code="las.page.field.lawservice.lawfirm" /></th>
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
						<td class="vb tC"><a href="javascript:pageAction('search');"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="las.page.button.lawservice.read" />"/></a></td>
						</tr>
					</table>
				</div>
	  </div>
	<!--//search-->		
	
		<!-- list1 총괄별송금금액 -->
		<table id="tb_list_group" border="0" cellspacing="0" cellpadding="0" class="list_basic_new tr_nobg">
		  <colgroup>
				<col width="40%" />
				<col width="25%" />
				<col width="10%" />
				<col width="25%" />
			</colgroup>
		  <thead>
			<tr>
			  <th><spring:message code="las.page.field.lawservice.total"/></th>
			  <th><spring:message code="las.page.field.lawservice.remitamt"/></th>
			  <th><spring:message code="las.page.field.lawservice.currency"/></th>
			  <th><spring:message code="las.page.field.lawservice.claimamt"/></th>
			</tr>
		  </thead>
		  <tbody>
				<c:choose>
						<c:when test="${!empty resultList_group}">
							<c:forEach var="list" items="${resultList_group}">
							<tr>
					            <td class="tC"><c:out value='${list.dept_nm}'/></td>
					            <td class="tR"><c:out value='${list.tot_remit_amt}'/></td>
					            <td class="tC"><c:out value='${list.crrncy_unit}'/></td>
					            <td class="tR"><c:out value='${list.totamt}'/></td>
							</tr>
							</c:forEach>
					    </c:when>
					    <c:otherwise>
							<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
								<td colspan="4" align="center"><spring:message code="las.msg.succ.noResult" /></td>
							</tr>
					    </c:otherwise>
				</c:choose>
		  </tbody>
		  </table>
		<!-- //list1 -->
		
		<!-- list2 소송상대DOJ건-->
		<div class="btn_wrap fR" id="tb_list_DOJ_dnExcel" style="position:relative;">
			<span class="btn"><span class="excel_down"></span><a href="javascript:dnExcel();"><spring:message code="las.page.button.lawservice.exceldn" /></a></span>
		</div>
		
		<table id="tb_list_DOJ" border="0" cellspacing="0" cellpadding="0" class="list_basic_new tr_nobg">
		  <colgroup>
				<col width="10%" />
				<col width="10%" />
				<col width="7%" />
				<col width="6%" />
				<col width="10%" />
				<col width="10%" />
				<col width="10%" />
				<col width="10%" />
				<col width="10%" />
				<col width="10%" />
				<col width="7%" />
			</colgroup>
		  <thead>
			<tr>
			  <th><spring:message code="las.page.field.lawservice.eventnm" /></th>
			  <th><spring:message code="las.page.field.lawservice.lawfirmnm" /></th>
			  <th><spring:message code="las.page.field.lawservice.acptday" /></th>
			  <th><spring:message code="las.page.field.lawservice.currency"/></th>
			  <th><spring:message code="las.page.field.lawservice.unpayamy"/></th>
			  <th><spring:message code="las.page.field.lawservice.remitamt"/></th>
			  <th>FEES</th>
			  <th>EXP.</th>
			  <th><spring:message code="las.page.field.lawservice.remitamtfor"/></th>
			  <th><spring:message code="las.page.field.lawservice.remitamtkrw"/></th>
			  <th><spring:message code="las.page.field.lawservice.remitday"/></th>
			</tr>
		  </thead>
		  <tbody>
		  
		  	  <c:if test="${!empty resultList_DOJSum_all}">			  	
			  	<tr>
			          <td class="tC" colspan="4" ><spring:message code="las.page.field.lawservice.totalsum"/></td>
			          <c:forEach var="list" items="${resultList_DOJSum_all}" varStatus="st">
				          <td class="tC"><c:out value='${list}'/></td>
			          </c:forEach>
			          <td class="tC">&nbsp;</td>					            					            
			  	</tr>			  	
			  </c:if>			  
			  <c:if test="${!empty resultList_DOJSum}">
			  	<c:forEach var="list" items="${resultList_DOJSum}" varStatus="st">
			  	<tr>
			          <td class="tC" colspan="3" ><spring:message code="las.page.field.lawservice.sumbycurrency"/></td>
			          <td class="tC"><c:out value='${list.crrncy_unit}'/></td>
			          <td class="tR"><c:out value='${list.pre_for_amount}'/></td>
			          <td class="tR"><c:out value='${list.totamt}'/></td>
			          <td class="tR"><c:out value='${list.srvc_amt}'/></td>
			          <td class="tR"><c:out value='${list.addtnl_amt}'/></td>
			          <td class="tR"><c:out value='${list.exp_amount_exp}'/></td>
			          <td class="tR"><c:out value='${list.exp_amount}'/></td>
			          <td class="tC">&nbsp;</td>					            					            
			  	</tr>
			  	</c:forEach>
			  </c:if>
			  <c:if test="${!empty resultList_DOJ}">
			  	<c:forEach var="list" items="${resultList_DOJ}">
			  	<tr>
			       <td class="tC"><c:out value='${list.event_nm}'/></td>
			       <td class="tC"><c:out value='${list.lawfirm_nm}'/></td>
			       <td class="tC"><c:out value='${list.acptday}'/></td>
			       <td class="tC"><c:out value='${list.crrncy_unit}'/></td>
			       <td class="tR"><c:out value='${list.pre_for_amount}'/></td>
			       <td class="tR"><c:out value='${list.totamt}'/></td>
			       <td class="tR"><c:out value='${list.srvc_amt}'/></td>
			       <td class="tR"><c:out value='${list.addtnl_amt}'/></td>
			       <td class="tR"><c:out value='${list.exp_amount_exp}'/></td>
			       <td class="tR"><c:out value='${list.tot_remit_amt}'/></td>
			       <td class="tC"><c:out value='${list.remitday}'/></td>					            					            
			  	</tr>
			  	</c:forEach>
			  </c:if>
			  <c:if test="${empty resultList_DOJ && empty resultList_DOJSum}">
			  	<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
			  		<td colspan="11" align="center"><spring:message code="las.msg.succ.noResult" /></td>
			  	</tr>
			  </c:if>
			  
		  </tbody>
		  </table>
		<!-- //list2 -->
		
		<!-- list3 년도 사건별 -->
		<table id="tb_list_year" border="0" cellspacing="0" cellpadding="0" class="list_basic_new tr_nobg">
		  <colgroup>
				<col width="26%" />
				<col width="7%" />
				<col width="7%" />
				<col width="15%" />
				<col width="15%" />
				<col width="15%" />
				<col width="15%" />				
			</colgroup>
		  <thead>
			<tr>
			  <th rowspan="2"><spring:message code="las.page.field.lawservice.eventnm" /></th>
			  <th rowspan="2"><spring:message code="las.page.field.lawservice.year"/></th>
			  <th rowspan="2"><spring:message code="las.page.field.lawservice.currency"/></th>
			  <th rowspan="1" colspan="3"><spring:message code="las.page.field.lawservice.exp"/></th>
			  <th rowspan="2"><spring:message code="las.page.field.lawservice.for"/></th>
			</tr>
			<tr>
			  <th class="tal_lineL tal_bg_cor01" >FEES</th>
			  <th class="tal_bg_cor01" >DIS</th>
			  <th class="tal_bg_cor01" ><spring:message code="las.page.field.lawservice.totalsum"/></th>
			</tr>
		  </thead>
		  <tbody>
				<c:choose>
						<c:when test="${!empty resultList_year}">
							<c:forEach var="list" items="${resultList_year}">
							<tr>
					            <td class="tC"><c:out value='${list.event_nm}'/></td>
					            <td class="tC"><c:out value='${list.remitday_year}'/></td>
					            <td class="tC"><c:out value='${list.crrncy_unit}'/></td>
					            <td class="tR"><c:out value='${list.srvc_amt}'/></td>		
					            <td class="tR"><c:out value='${list.addtnl_amt}'/></td>
					            <td class="tR"><c:out value='${list.totamt}'/></td>
					            <td class="tR"><c:out value='${list.tot_remit_amt}'/></td>            
							</tr>
							</c:forEach>
					    </c:when>
					    <c:otherwise>
							<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
								<td colspan="7" align="center"><spring:message code="las.msg.succ.noResult" /></td>
							</tr>
					    </c:otherwise>
				</c:choose>
		  </tbody>
		  </table>
		<!-- //list2 -->

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