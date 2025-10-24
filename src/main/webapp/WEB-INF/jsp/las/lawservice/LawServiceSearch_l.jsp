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
 * 파  일  명 : LawServiceSearch_l.jsp
 * 프로그램명 : 검색
 * 설      명 : 
 * 작  성  자 : 박병주
 * 작  성  일 : 2011.09
 */
--%>

<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
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

<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>

<script language="javascript" src="<c:url value='/script/clms/menu.js'/>"></script>

<script language="javascript">

	/**
	* 초기화면 로딩 
	*/

	$(document).ready(function(){
		
		var frm = document.frm;
		
		// 날짜 textbox 달력 set
 		$('*').attr('class',function(index){
			if($(this).attr("class") == 'text_calendar02'){
				initCal($(this).attr("id"));
			}
		});
		
		// 사건명
		frm.srch_event_nm.value = "<c:out value='${command.srch_event_nm}'/>";		
		
		// 소송상대 리스트
		getCodeSelectByUpCd2("frm", "srch_lawsuit_trgt_cd", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+"105"+"&combo_abbr=F&combo_type=S2&combo_use_yn=Y&combo_selected="+"<c:out value='${command.srch_lawsuit_trgt_cd}'/>");
						
		// 사건구분1
		getCodeSelectByUpCd2("frm", "srch_kbn1", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+"106"+"&combo_abbr=F&combo_type=S2&combo_use_yn=Y&combo_selected="+"<c:out value='${command.srch_kbn1}'/>");
		
		// 사건구분1이 null 이 아닌 경우 
		var isSrch_kbn1 = "<c:out value='${command.srch_kbn1}'/>";
		
		if(isSrch_kbn1!=''){
			// 사건구분2 로드
			StReload_partin("<c:out value='${command.srch_kbn1}'/>");
		}
	});

	/**
	*  사건구분2  reload
	*/
	function StReload_partin(index){
		$(partin2).html("<select name='srch_kbn2' id='srch_kbn2' alt='<spring:message code='las.page.field.lawservice.event2'/>' style='width:92px;'></select>");
		getCodeSelectByUpCd2("frm", "srch_kbn2", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+index+"&combo_abbr=F&combo_type=T&combo_use_yn=Y&combo_selected="+"<c:out value='${command.srch_kbn2}'/>");
	} 

	/**
	* 페이징 function
	*/
	function goPage(pgNum){
		var frm = document.frm;
		checkForm();
		viewHiddenProgress(true) ;
		
		frm.target = "_self";
		frm.action = "<c:url value='/las/lawservice/lawServiceSearch.do' />";
		frm.method.value = "listLawServiceSearch";
		frm.curPage.value = pgNum;
		
		frm.submit();
	}
  
	/**
	* 버튼 동작 부분
	*/ 
	function pageAction(flag){
		
		var frm = document.frm;	
		var winpos = "left=50,top=200";
		var winstyle="height=700,width=780,status=no,toolbar=no,menubar=no,location=no,resizable=yes,"
		      +	 "scrollbars=yes,copyhistory=no," + winpos;
		
		if(!checkForm()){
			alert("<spring:message code='las.page.msg.lawservice.setsrchcond' />");
			return;
		}
				
	    //유효성 체크
	    if(validateForm(frm) 
	    		&& date_check($("#srch_acpt_start_dt").val(),$("#srch_acpt_end_dt").val()) 
	    		&& date_check($("#srch_unpay_start_dt").val(),$("#srch_unpay_end_dt").val()) 
	    		&& date_check($("#srch_remit_start_dt").val(),$("#srch_remit_end_dt").val())){		    
		
			if(flag == "search"){
				viewHiddenProgress(true) ;
				frm.target = "_self";
				frm.action = "<c:url value='/las/lawservice/lawServiceSearch.do' />";
			    frm.method.value = "listLawServiceSearch";
				frm.curPage.value = "1";		
				frm.submit();
			} else if (flag == "dnExcel"){
				
				frm.target = "_self";
				frm.action = "<c:url value='/las/lawservice/lawServiceSearch.do' />" ;
				frm.method.value = "excelDownLoad";
				frm.submit();
			} else if (flag == "consultForm"){
				
				PopUpWindow = window.open('', "PopUpWindow" , winstyle);
				frm.action = "<c:url value='/las/lawservice/lawServiceSearch.do' />" ;
				frm.method.value="popLawServiceSearch";
				frm.target = "PopUpWindow";
				//frm.forward_url.value="/WEB-INF/jsp/las/lawservice/LawServiceSearch_p.jsp";
				frm.submit();
			}
	    }
	}
	
	/**
	* Form check
	*/
	function checkForm(){
		var frm = document.frm;
		var check_tag = false;
		
		if($('#ssrch_unpay_yn').attr('checked') == true){
			$('#srch_unpay_yn').val("Y");
			check_tag = true;
		} else {
		    $('#srch_unpay_yn').val("N");
		}
	
 		$('#searchbox td select').each(function(index,elem) {
 			if($(this).val() != "")
	 				check_tag = true;
 		}); 
		
 		$('#searchbox td input').each(function(index,elem) {
 			if($(this).val() != "")
	 				check_tag = true;
 		}); 
 		return check_tag;
	}
	
	/**
	* 날짜 체크
	*/ 
	function date_check(st_day,ed_day){	
		var check = true;		
		if((st_day != '') && (ed_day != '')){
			var num_st_day = st_day.replace(/-/gi,'');
			var num_ed_day = ed_day.replace(/-/gi,'');			
			if((num_ed_day - num_st_day) < 0){
				alert("<spring:message code='las.page.msg.lawservice.wrongsrchday' />");
				check = false;
			}
		}		
		return check;
	}
	
	/**
	* 상세내역 조회
	*/
	function detailView(acpt_no){
		var frm = document.frm;		
		viewHiddenProgress(true) ;
		frm.acpt_no.value		= acpt_no;
		frm.target = "_self";
		frm.action = "<c:url value='/las/lawservice/eventAcceptSrvCost.do' />";
		frm.method.value = "detailEventAcceptSrvCost";		
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
</script>
</head>
<body onLoad='showMessage("<c:out value='${returnMessage}'/>");'>
<!-- **************************** Calendar 관련 추가 영역 **************************** -->
<div id='popCal' style='position: absolute; visibility: hidden; ridge; width: 10; z-index:1'>
<iframe name="popFrame" src="<c:url value='/secfw/popCalendar.do?method=goPopCalendar' />" frameborder="0" scrolling="no" width="196" height="188"></iframe></div>
<script event=onclick() for=document> 
    if(popCal.style.visibility == "visible"){
      popCal.style.visibility="hidden";
    }
</script>

<form:form name="frm" id='frm' method="post" autocomplete="off"> 
<input type="hidden" name="method"  value="" />
<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>" />	
<input type="hidden" name="forward_url" value="" />	
<input type="hidden" name="forward_from" value="2" />	
<!-- URL 이동시 사용 -->
<input type="hidden" name="targetMenuId">
<input type="hidden" name="selected_menu_id">
<input type="hidden" name="selected_menu_detail_id">
<input type="hidden" name="set_init_url">

<input type="hidden" name="acpt_no" value="" />	
<input type="hidden" id="srch_unpay_yn" name="srch_unpay_yn"   value="<c:out value='${command.srch_unpay_yn}'/>" />

<!-- EXCEL 관련-->
<input type="hidden" id="exel_nm_pop" 		name="exel_nm_pop" 		value="" />
<input type="hidden" id="exel_vel_pop" 		name="exel_vel_pop" 	value="" />	
<input type="hidden" id="exel_nm" 			name="exel_nm" 			value="" />
<input type="hidden" id="exel_vel" 			name="exel_vel" 		value="" />
<input type="hidden" id="start_datetime"	name="start_datetime" 	value="" />
<input type="hidden" id="end_datetime" 		name="end_datetime" 	value="" />	

<!-- search form -->

<!-- key Form -->

<div id="wrap">
<!-- container -->
<div id="container">
<!-- Location -->
<div class="location">
	<IMG SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/>
</div>
<!-- //Location -->

	<!-- content -->
	<div id="content">
	
	<!-- title -->
		<div class="title">
			<h3><spring:message code="las.page.field.lawservice.srch"/></h3>
		</div>
		<!-- //title -->
		
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
							<table class="search_form">
								<colgroup>
									<col width="15%"/>
									<col width="40%"/>
									<col width="15%"/>
									<col width="30%"/>
								</colgroup>
								<tr>
									<th><spring:message code="las.page.field.lawservice.event" /></th>
									<td colspan=3" >
										<select id='srch_event_no' name='srch_event_no' style='width:250px;'>
											<option value=''><spring:message code="las.page.field.lawservice.select" /></option>	
											<c:forEach var="list" items="${srch_event_list}">
											<option value="<c:out value='${list.event_no}'/>" <c:if test='${list.event_no == command.srch_event_no}'> selected</c:if>><c:out value='${list.event_nm}'/></option>			
											</c:forEach>
										</select>
										<input id="srch_event_nm" name="srch_event_nm" value="<c:out value='${command.srch_event_nm}'/>" type="text" style="width:150px;" alt="<spring:message code="las.page.field.lawservice.eventnm" />" maxlength="100" onKeyDown="javascript:if(event.keyCode==13){event.returnValue = false;pageAction('search');}" />
									</td>
							  </tr>
							  <tr>
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
							   <tr>
									<th><spring:message code="las.page.field.lawservice.gbn1" /></th>
									<td>
										<select id="srch_kbn1" name="srch_kbn1" style="width:154px;" onChange="StReload_partin(this.value)">
										</select>
										<span id="partin2"></span>
									</td>
									<th><spring:message code="las.page.field.lawservice.remityn" /></th>
									<td>
										<spring:message code="las.page.field.lawservice.unpaycert"/>
										<input type="checkbox" id="ssrch_unpay_yn" name="ssrch_unpay_yn" value="" <c:if test="${command.srch_unpay_yn == 'Y'}"> checked</c:if>></input>
										<select id="srch_remit_yn" name="srch_remit_yn">
											<option value="" ><spring:message code="las.page.field.lawservice.select" /></option>
											<option value="Y" <c:if test="${command.srch_remit_yn == 'Y'}"> selected </c:if>><spring:message code="las.page.field.lawservice.remit"/></option>
											<option value="N" <c:if test="${command.srch_remit_yn == 'N'}"> selected </c:if>><spring:message code="las.page.field.lawservice.unremit"/></option>
										</select>
									</td>
							  </tr>
							  <tr>
									<th><spring:message code="las.page.field.lawservice.lawsuittrgt" /></th>
									<td>
										<select id="srch_lawsuit_trgt_cd" name="srch_lawsuit_trgt_cd" style='width:250px;'>
										</select>
									</td>
									<th><spring:message code="las.page.field.lawservice.reviewyn"/></th>
									<td>
										<select id="srch_review_yn" name="srch_review_yn">
											<option value="" ><spring:message code="las.page.field.lawservice.select" /></option>
											<option value="Y" <c:if test="${command.srch_review_yn == 'Y'}"> selected </c:if>>Y</option>
											<option value="N" <c:if test="${command.srch_review_yn == 'N'}"> selected </c:if>>N</option>
										</select>		
									</td>
							  </tr>
							   <tr>
									<th><spring:message code="las.page.field.lawservice.total"/></th>
									<td colspan=3" >
										<select id="srch_group_cd" name="srch_group_cd" >
											<option value=''><spring:message code="las.page.field.lawservice.select" /></option>	
											<c:forEach var="list" items="${srch_dept_list}">
											<option value="<c:out value='${list.dept_nm}'/>" <c:if test='${list.dept_nm == command.srch_group_cd}'> selected</c:if>><c:out value='${list.dept_nm}'/></option>			
											</c:forEach>														
										</select>
									</td>
							  </tr>
							   <tr>
									<th><spring:message code="las.page.field.lawservice.acptday" /></th>
									<td>
										<input type="text" name="srch_acpt_start_dt" id="srch_acpt_start_dt" value="<c:out value='${command.srch_acpt_start_dt}'/>"  class="text_calendar02" readonly />
									 ~
									  <input type="text" name="srch_acpt_end_dt" id="srch_acpt_end_dt" value="<c:out value='${command.srch_acpt_end_dt}'/>" class="text_calendar02" readonly />			
									</td>
									<th><spring:message code="las.page.field.lawservice.unpayday"/></th>
									<td>
									  <input type="text" name="srch_unpay_start_dt" id="srch_unpay_start_dt" value="<c:out value='${command.srch_unpay_start_dt}'/>"  class="text_calendar02" readonly />
									 ~
									  <input type="text" name="srch_unpay_end_dt" id="srch_unpay_end_dt" value="<c:out value='${command.srch_unpay_end_dt}'/>" class="text_calendar02" readonly />			
									</td>
							  </tr>
							   <tr>
									<th><spring:message code="las.page.field.lawservice.remitday"/></th>
									<td>
										<input type="text" name="srch_remit_start_dt" id="srch_remit_start_dt" value="<c:out value='${command.srch_remit_start_dt}'/>"  class="text_calendar02" readonly />
									 ~
									  <input type="text" name="srch_remit_end_dt" id="srch_remit_end_dt" value="<c:out value='${command.srch_remit_end_dt}'/>" class="text_calendar02" readonly />			
									</td>
									<!-- 
									<th>&nbsp;</th>
									<td>
										&nbsp;		
									</td>
									-->
							  </tr>
							</table>
						</td>
						<td class="vb tC"><a href="javascript:pageAction('search');"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="las.page.button.lawservice.read" />"/></a></td>
						</tr>
					</table>
				</div>
	  </div>
			<!--//search-->	
		
			<div class="btn_wrap fR">
				<span class="btn"><span class="list"></span><a href="javascript:pageAction('consultForm');"><spring:message code="las.page.button.lawservice.aprovalformat" /></a></span>
				<span class="btn"><span class="excel_down"></span><a href="javascript:pageAction('dnExcel');"><spring:message code="las.page.button.lawservice.exceldn" /></a></span>
			</div>			

		<!-- list -->
		<table class="list_basic tr_nobg">
		  <colgroup>
				<col width="3%" />
				<col width="15%" />
				<col width="15%" />
			    <col width="10%" />
				<col width="7%" />
				<col width="8%" />
				<col width="8%" />
				<col width="8%" />
				<col width="4%" />
				<col width="7%" />
				<col width="7%" />
				<col width="7%" />
			</colgroup>
		  <thead>
			<tr>
			  <th><spring:message code="las.page.field.lawservice.order" /></th>
			  <th><spring:message code="las.page.field.lawservice.eventnm" /></th>
			  <th><spring:message code="las.page.field.lawservice.lawfirm" /></th>
			  <th><spring:message code="las.page.field.lawservice.totalnm"/></th>
			  <th>INVOICE</th>
			  <th><spring:message code="las.page.field.lawservice.notPayDate"/></th>
			  <th><spring:message code="las.page.field.lawservice.rmtcDate"/></th>
			  <th><spring:message code="las.page.field.lawservice.remittance"/></th>
			  <th>&nbsp;</th>
			  <th>USD</th>
			  <th><spring:message code="las.page.field.lawservice.regnm" /></th>
			  <th><spring:message code="las.page.field.lawservice.acptday" /></th>
			</tr>
		  </thead>
		  <tbody>
			<c:choose>
						<c:when test="${pageUtil.totalRow > 0}">
							<c:forEach var="list" items="${resultList}">
							<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
							    <td><c:out value='${list.rn}'/></td>
								<td class="tL">
									<!--<div style='width:280px;overflow:hidden;text-overflow:ellipsis'>
						          		<nobr><a href='javascript:Menu.detail("frm", "_top", "20110804083733562_0000000170", "20110804083733562_0000000170", "/las/lawservice/eventAcceptSrvCost.do?method=detailEventAcceptSrvCost&forward_from=2&acpt_no=<c:out value='${list.acpt_no}'/>")' >
						          		<c:out value='${list.event_nm}'/></a></nobr>
						          	</div>//-->
						          	<div style='width:280px;overflow:hidden;text-overflow:ellipsis'>
						          		<nobr><a href='javascript:detailView("<c:out value='${list.acpt_no}'/>");'><c:out value='${list.event_nm}'/></a></nobr>
						          	</div>
					            </td>
					            <td class="tC"><c:out value='${list.lawfirm_nm}'/></td>
					            <td class="tC"><c:out value='${list.dept_nm}'/></td>
					            <td class="tC"><c:out value='${list.invoice_no}'/></td>
					            <td class="tC"><c:out value='${list.unpayday}'/></td>
					            <td class="tC"><c:out value='${list.remitday}'/></td>
					            <td class="tR"><fmt:formatNumber value='${list.totamt}' pattern="#,#00.00#"/></td>
					            <td class="tC"><c:out value='${list.crrncy_unit}'/></td>
					            <td class="tR"><fmt:formatNumber value='${list.usd_amt}' pattern="#,#00.00#"/></td>
					            <td class="tC"><c:out value='${list.reg_nm}'/></td>
					            <td class="tC"><c:out value='${list.reg_dt}'/></td>
							</tr>
							</c:forEach>
					    </c:when>
					    <c:otherwise>
							<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
								<td colspan="12" align="center"><spring:message code="las.msg.succ.noResult" /></td>
							</tr>
					    </c:otherwise>
				</c:choose>
		  </tbody>
		  </table>
			<!-- //list -->
		<div class="total_num">Total : <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%> <spring:message code="las.page.field.lawservice.case"/></div>
		
			<!-- pagination -->
			<div class="pagination">
				<%=pageUtil.getPageNavi(pageUtil, "") %>
			</div>
			<!-- //pagination -->
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
