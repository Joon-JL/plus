<!DOCTYPE html>
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
 * 파  일  명 : Sign_l.jsp
 * 프로그램명 : 날인 목록
 * 설      명 : 
 * 작  성  자 : 박병주
 * 작  성  일 : 2013.05
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
%>

<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script language="javascript">

	<c:choose>
		<c:when test="${prc_mode == 'apl'}">
			var listMethod = "listSign";
			var listMethodExcel = "dnSignExcel";
		</c:when>
		<c:when test="${prc_mode == 'prc'}">
			var listMethod = "listSignMng";
			var listMethodExcel = "dnSignMngExcel";
		</c:when>
		<c:otherwise>
			var listMethod = "listAplOut";
			var listMethodExcel = "dnAplOutExcel";
		</c:otherwise>
	</c:choose>

	/**
	* 초기화면 로딩 
	*/
	$(document).ready(function(){
		
		var frm = document.frm;
		
		// 캘린더 초기화
 		$(".text_calendar").each(function(index){    
			initCal($(this).attr("id"));  	
		}); 
		
		// 날인 처리 상태 (후처리에서 상신의 결재 상태  를 참조하여 날인처리상태 코드 취득)
		getCodeSelectByUpCd2("frm", "srch_seal_ffmt_status", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+"SL01"+"&combo_abbr=F&combo_type=T2&combo_use_yn=Y&combo_selected="+"<c:out value='${command.srch_seal_ffmt_status}'/>");
		
		// 날인신청 목록 / 날인 처리 목록 일 경우
		<c:if test="${prc_mode == 'apl' || prc_mode == 'prc'}">
		
			// 날인신청 목록 경우에만 초기화 시킨다.
			<c:if test="${prc_mode == 'apl'}">
				// 결재상태 (후처리에서 상신의 결재 상태  를 참조하여 날인처리상태 코드 취득)
				getCodeSelectByUpCd2("frm", "srch_seal_rqst_status", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+"AP01"+"&combo_abbr=F&combo_type=T2&combo_use_yn=Y&combo_selected="+"<c:out value='${command.srch_seal_rqst_status}'/>");
			</c:if>		
		
			// 날인 신청(유형) srch_seal_knd_cd
			getCodeSelectByUpCd2("frm", "srch_seal_knd_cd", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+"C021"+"&combo_abbr=F&combo_type=T2&combo_use_yn=Y&combo_selected="+"<c:out value='${command.srch_seal_knd_cd}'/>");
						
			// 신청유형 (금융거래용,계약용,,,) srch_apl_cls
			getCodeSelectByUpCd2("frm", "srch_apl_cls", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+"AT01"+"&combo_abbr=F&combo_type=T2&combo_use_yn=Y&combo_selected="+"<c:out value='${command.srch_apl_cls}'/>");
		
		    // 증명서류
			getCodeSelectByUpCd2("frm", "srch_doc_no", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+"DC02"+"&combo_abbr=F&combo_type=T2&combo_use_yn=Y&combo_selected="+"<c:out value='${command.srch_doc_no}'/>");
	
		    // 증명서류발급자 처리 상태 DC01
			getCodeSelectByUpCd2("frm", "srch_doc_issue_status", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+"DC01"+"&combo_abbr=F&combo_type=T2&combo_use_yn=Y&combo_selected="+"<c:out value='${command.srch_doc_issue_status}'/>");
		
	    </c:if>
	    
		// srch_apl_y 이 null 이 아닌 경우 
		$("#srch_apl_m").hide();
		var isSrch_apl_y = "<c:out value='${command.srch_apl_y}'/>";
		
		if(isSrch_apl_y!=''){
			$("#srch_apl_y").val("<c:out value='${command.srch_apl_y}'/>");
			$("#srch_apl_m").show();
			$("#srch_apl_m").val("<c:out value='${command.srch_apl_m}'/>");
		}
	
	});
	
	/**
	* POPUP function
	*/
	function SamplePop(mode)
	{
    	var frm = document.frm;
		PopUpWindowOpen('', "1000", "860", true);
		frm.action = "/secfw/main.do";
		frm.method.value="forwardPage";
		frm.target = "PopUpWindow";
		frm.forward_url.value="/WEB-INF/jsp/las/lawservice/NationList_p.jsp";
		frm.submit();
	}

	/**
	* 페이징 function
	*/
	function goPage(pgNum){
		var frm = document.frm;

		viewHiddenProgress(true) ;		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/sign/signManage.do' />";
		frm.method.value = listMethod;
		frm.curPage.value = pgNum;		
		frm.submit();
	}
	
	/**
	* 버튼 동작 부분
	*/ 
	function pageAction(flag){
		
		var frm = document.frm;		

		if(flag == "search"){
			
			viewHiddenProgress(true) ;
			frm.target = "_self";
			frm.action = "<c:url value='/clm/sign/signManage.do' />";
			frm.method.value = listMethod;
			frm.curPage.value = "1";		
			frm.submit();

		} else if(flag == "new"){
		
			viewHiddenProgress(true) ;
			frm.target = "_self";
			frm.action = "<c:url value='/clm/sign/signManage.do' />";
		    frm.method.value = "forwardSignInsert";
			frm.submit();
		
		} else if(flag == "excel"){

			frm.target = "_self";
			frm.action = "<c:url value='/clm/sign/signManage.do' />";
			frm.method.value = "excelDown";
			frm.excel_method.value = listMethod;
			frm.curPage.value = "1";		
			frm.submit();
		}
	}
	
	/**
	* 상세내역 조회
	*/
	function detailView(apl_sqn){
		var frm = document.frm;		
		viewHiddenProgress(true) ;
		frm.apl_sqn.value		= apl_sqn;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/sign/signManage.do' />";
		frm.method.value = "detailSign";		
		frm.submit();		
	}	
	
	/**
	* 신청년월 콤보세팅
	*/
	function setSrch_apl_m(val) {
		$("#srch_apl_m").val("");
		if(val==''){
			$("#srch_apl_m").hide();
		} else {
			$("#srch_apl_m").show();
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

</script>
</head>
<body onLoad='showMessage("<c:out value='${returnMessage}'/>");'>
<!-- Calendar  -->
<div id='popCal' style='position: absolute; visibility: hidden; ridge; width: 10; z-index:1'>
<iframe name="popFrame" src="<c:url value='/secfw/popCalendar.do?method=goPopCalendar' />" frameborder="0" scrolling="no" width="196" height="188"></iframe></div>
<script event=onclick() for=document> 
    if(popCal.style.visibility == "visible"){
      popCal.style.visibility="hidden";
    }
</script>

<div id="wrap">
<!-- container -->
<div id="container">
<!-- Location -->
<div class="location"><IMG SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
<!-- //Location -->	

	<!-- title -->
	<div class="title">	
		<c:choose>
			<c:when test="${prc_mode == 'apl'}">
				<h3><spring:message code="clm.field.signmng.apltab" /><!-- 날인/증명서류 신청 --></h3>
			</c:when>
			<c:when test="${prc_mode == 'prc'}">
				<h3><spring:message code="clm.field.signmng.prctab" /><!-- 날인/증명서류 처리 --></h3>
			</c:when>
			<c:otherwise>
				<h3><spring:message code="clm.field.signmng.aplouttab" /><!-- 인장반출 내역 --></h3>
			</c:otherwise>
		</c:choose>
	</div>
	<!-- //title -->
	
	<!-- content -->
	<div id="content">	
		<div id="content_in">
		
		<form:form name="frm" id='frm' method="post" autocomplete="off"> 
		<input type="hidden" name="method"  value="" />
		<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
		<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>" />
		<input type="hidden" id="forward_url" name="forward_url"  value="" />
		<input type="hidden" id="forwardFrom" name="forwardFrom"  value="<c:out value='${command.forwardFrom}'/>" />		
		
		<!-- EXCEL DOWNLOAD-->	
		<input type="hidden" id="excel_method" name="excel_method"  value="" />
		
		<!-- key Form -->
		<input type="hidden" name="apl_sqn" id="apl_sqn" value="" />	
		
		<!-- 날인신청 /처리 검색조건-->	
		<!-- 날인신청 /처리 검색조건-->		
		<c:if test="${prc_mode == 'apl' || prc_mode == 'prc' }">
		
		<!-- search-->
	            <div class="search_box">
					<div class="search_box_inner">
						<table class="search_tb">
							<colgroup>
								<col/>
								<col width="78px"/>
								</colgroup>
								<tr>
								<td>
								<table class="search_form">
									<colgroup>
										<col width="8%"/>
										<col width="17%"/>
										<col width="8%"/>
										<col width="17%"/>
										<col width="8%"/>
										<col width="17%"/>
										<col width="8%"/>
										<col width="17%"/>
									</colgroup>
									<tr>
										<th><spring:message code="clm.field.signmng.aplyymm" /><!-- 신청년월 --></th>
										<td><nobr><select id="srch_apl_y" name="srch_apl_y" class="" onChange="javascpript:setSrch_apl_m(this.value)">
												<option value="" <c:if test="${command.srch_apl_y eq ''}">selected</c:if>><spring:message code="clm.field.signmng.rec30" /><!-- 최근30일 --></option>
												<option value="2013" <c:if test="${command.srch_apl_y eq '2013'}">selected</c:if>>2013</option>
												<option value="2012" <c:if test="${command.srch_apl_y eq '2012'}">selected</c:if>>2012</option>
												<option value="2011" <c:if test="${command.srch_apl_y eq '2011'}">selected</c:if>>2011</option>
												<option value="2010" <c:if test="${command.srch_apl_y eq '2010'}">selected</c:if>>2010</option>
												<option value="2009" <c:if test="${command.srch_apl_y eq '2009'}">selected</c:if>>2009</option>
											</select>
											<select class=""  id="srch_apl_m" name="srch_apl_m" >
												<option value="" <c:if test="${command.srch_apl_m eq ''}">selected</c:if>><spring:message code="clm.page.field.board.srchTotal" /><!-- 전체 --></option>
												<option value="01" <c:if test="${command.srch_apl_m eq '01'}">selected</c:if>><spring:message code="clm.field.signmng.m1" /><!-- 1월 --></option>
												<option value="02" <c:if test="${command.srch_apl_m eq '02'}">selected</c:if>><spring:message code="clm.field.signmng.m2" /><!-- 2월 --> </option>
												<option value="03" <c:if test="${command.srch_apl_m eq '03'}">selected</c:if>><spring:message code="clm.field.signmng.m3" /><!-- 3월 --></option>
												<option value="04" <c:if test="${command.srch_apl_m eq '04'}">selected</c:if>><spring:message code="clm.field.signmng.m4" /><!-- 4월 --></option>
												<option value="05" <c:if test="${command.srch_apl_m eq '05'}">selected</c:if>><spring:message code="clm.field.signmng.m5" /><!-- 5월 --></option>
												<option value="06" <c:if test="${command.srch_apl_m eq '06'}">selected</c:if>><spring:message code="clm.field.signmng.m6" /><!-- 6월 --></option>
												<option value="07" <c:if test="${command.srch_apl_m eq '07'}">selected</c:if>><spring:message code="clm.field.signmng.m7" /><!-- 7월 --></option>
												<option value="08" <c:if test="${command.srch_apl_m eq '08'}">selected</c:if>><spring:message code="clm.field.signmng.m8" /><!-- 8월 --></option>
												<option value="09" <c:if test="${command.srch_apl_m eq '09'}">selected</c:if>><spring:message code="clm.field.signmng.m9" /><!-- 9월 --></option>
												<option value="10" <c:if test="${command.srch_apl_m eq '10'}">selected</c:if>><spring:message code="clm.field.signmng.m10" /><!-- 10월 --></option>
												<option value="11" <c:if test="${command.srch_apl_m eq '11'}">selected</c:if>><spring:message code="clm.field.signmng.m11" /><!-- 11월 --></option>
												<option value="12" <c:if test="${command.srch_apl_m eq '12'}">selected</c:if>><spring:message code="clm.field.signmng.m12" /><!-- 12월 --></option>
											</select></nobr>
											</td>
										
										<c:choose>
											<c:when test="${prc_mode == 'apl'}">
												<th><spring:message code="clm.field.signmng.apprst" /><!-- 결재상태 --></th>
										    	<td>
										    		<select class="w_70" id="srch_seal_rqst_status" name="srch_seal_rqst_status" ></select>
										    	</td>
											</c:when>
										<c:otherwise>
											<th><spring:message code="clm.field.signmng.aplno" /><!-- 반출인장번호 --></th>
										    	<td>
										    		<input type="text" id="srch_prc_seal_no" name="srch_prc_seal_no" value="<c:out value='${command.srch_prc_seal_no}'/>" class="text w_70" />
										    	</td>
												</c:otherwise>
										</c:choose>
									
										<th><spring:message code="clm.field.signmng.apl" /><!-- 날인신청 --></th>
										<td>
								    		<select class="w_70" id="srch_seal_knd_cd" name="srch_seal_knd_cd" ></select>
								    	</td>
										<th><spring:message code="clm.field.signmng.aplst" /><!-- 날인처리상태 --></th>
										<td>
											<select class="w_70" id="srch_seal_ffmt_status" name="srch_seal_ffmt_status" ></select>
										</td>
									</tr>
									<tr>
										<th><spring:message code="clm.field.signmng.title" /><!-- 건명 --></th>
										<td><input type="text" id="srch_title" name="srch_title" value="<c:out value='${command.srch_title}'/>" class="text w_70" /></td>
										<th><spring:message code="clm.field.signmng.apltype" /><!-- 신청유형 --></th>
								    	<td>
											<select class="w_70" id="srch_apl_cls" name="srch_apl_cls" ></select>
										</td>
										<th><spring:message code="clm.field.signmng.doc" /><!-- 증명서류 --></th>
										<td>
											<select class="w_70" id="srch_doc_no" name="srch_doc_no" ></select>
										</td>
										<th><spring:message code="clm.field.signmng.docprcst" /><!-- 증명서류처리상태 --></th>
										<td>
											<select class="w_70" id="srch_doc_issue_status" name="srch_doc_issue_status" ></select>
										</td>
									</tr>
									<tr>
										<th><spring:message code="clm.field.signmng.towhere" /><!-- 제출처 --></th>
										<td><input type="text"  id="srch_sbm" name="srch_sbm" class="text w_70" value="<c:out value='${command.srch_sbm}'/>"  /></td>
										<th><spring:message code="clm.field.signmng.reqman" /><!-- 신청자 --></th>
								    	<td><input type="text"  id="srch_seal_rqstman_nm" name="srch_seal_rqstman_nm" class="text w_70" value="<c:out value='${command.srch_seal_rqstman_nm}'/>"  /></td>
										<th><spring:message code="clm.field.signmng.prcman" /><!-- 처리자 --></th>
										<td><input type="text"  id="srch_seal_ffmtman_nm" name="srch_seal_ffmtman_nm" class="text w_70" value="<c:out value='${command.srch_seal_ffmtman_nm}'/>"  /></td>
										<th><spring:message code="clm.field.signmng.prcday" /><!-- 처리일자 --></th>
										<td><input type="text" name="srch_seal_ffmtday_start" id="srch_seal_ffmtday_start" value="<c:out value='${command.srch_seal_ffmtday_start}'/>"  class="text_calendar" />
									</tr>
								</table>
							</td>
							<td class="vb tC"><a href="javascript:pageAction('search')"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="Search" title="Search"/></a></td>
							</tr>
						</table>
					</div>
	            </div>     
				<!-- search-->
				
				</c:if>				
				<!-- //날인신청 /처리 검색조건-->
				<!-- //날인신청 /처리 검색조건-->				

				<!-- 신청목록 -->
				<!-- 신청목록 -->
				<c:if test="${prc_mode == 'apl'}">

				
					<!-- button -->
					<div class="fR mb3">					
						<span class="btn_all_w"><span class="edit"></span><a href="javascript:pageAction('new');"><spring:message code="clm.field.signmng.aplw" /><!-- 신청작성 --></a></span> 
						<span class="btn_all_w"><span class="excel_down"></span><a href="javascript:pageAction('excel')"><spring:message code="las.page.button.excelDownload" /><!-- 엑셀다운로드 --></a></span>
					</div>
					<!-- //button -->
	
						<!-- list -->
						<table class="list_basic mt5">
								<colgroup>
									<col width="10%" />
									<col width="22%" />
									<col width="18%" />
									<col width="10%" />
									<col width="14%" />
									<col width="8%" />
									<col width="10%" />
									<col width="8%" />						
								</colgroup>
								<tr>
									<th rowspan='2'><spring:message code="clm.field.signmng.reqday" /><!-- 신청일자 --></th>
								    <th rowspan='2'><spring:message code="clm.field.signmng.title" /><!-- 건명 --></th>
								    <th rowspan='2'><spring:message code="clm.field.signmng.towhere" /><!-- 제출처 --></th>
								    <th rowspan='2'><spring:message code="clm.field.signmng.reqman" /><!-- 신청자 --></th>
									<th rowspan='2'><spring:message code="clm.field.signmng.apltype" /><!-- 신청유형 --></th>
									<th rowspan='2' class='rightline'><spring:message code="clm.field.signmng.apprst" /><!-- 결재상태 --></th>
								    <th colspan='2' ><spring:message code="clm.field.signmng.aplinfo" /><!-- 신청내역 --></th>
								</tr>
								<tr>
									<th class='sub01' style="border-left:1px solid #CBDCE4" ><spring:message code="clm.field.signmng.sign" /><!-- 날인 --></th>
								    <th class='sub01'><spring:message code="clm.field.signmng.doc" /><!-- 증명서류 --></th>
								</tr>
					  <tbody>
					  
						 <c:choose>
									<c:when test="${pageUtil.totalRow > 0}">
										<c:forEach var="list" items="${resultList}">
			
										<tr style="cursor:pointer;" onClick="javascript:detailView(<c:out value='${list.apl_sqn}'/>);" >
										    <td class="tC"><c:out value='${list.seal_rqstday_txt}'/></td>
											<td class="overflow" title="<c:out value='${list.title}' escapeXml="false" />" ><c:out value='${list.title}' escapeXml="false" /></td>
								            <td class="tC overflow" title="<c:out value='${list.sbm}' escapeXml="false" />"><c:out value='${list.sbm}' escapeXml="false" /></td>
								            <td class="tC overflow" title="<c:out value='${list.seal_rqstman_nm}'/>/<c:out value='${list.seal_rqstman_jikgup_nm}'/>/<c:out value='${list.seal_rqst_dept_nm}'/>"><c:out value='${list.seal_rqstman_nm}'/></td>
								            <td class="tC overflow"  title="<c:out value='${list.apl_cls_nm}'/>" /><c:out value='${list.apl_cls_nm}'/></td>
								            <td class="tC">					            
									            <c:choose>
													<c:when test="${list.seal_rqst_status eq 'AP0104'}">
														<img src="<%=IMAGE%>/icon/icon_situation07.gif" alt="<c:out value='${list.seal_rqst_status_nm}'/>" title="<c:out value='${list.seal_rqst_status_nm}'/>"/>											
													</c:when>
													<c:when test="${list.seal_rqst_status eq 'AP0103'}">
														<img src="<%=IMAGE%>/icon/icon_situation06.gif" alt="<c:out value='${list.seal_rqst_status_nm}'/>" title="<c:out value='${list.seal_rqst_status_nm}'/>"/>				
													</c:when>
													<c:when test="${list.seal_rqst_status eq 'AP0102'}">
														<img src="<%=IMAGE%>/icon/icon_situation04.gif" alt="<c:out value='${list.seal_rqst_status_nm}'/>" title="<c:out value='${list.seal_rqst_status_nm}'/>"/>				
													</c:when>
													<c:when test="${list.seal_rqst_status eq 'AP0101'}">
														<img src="<%=IMAGE%>/icon/icon_situation05.gif" alt="<c:out value='${list.seal_rqst_status_nm}'/>" title="<c:out value='${list.seal_rqst_status_nm}'/>"/>				
													</c:when>
													<c:when test="${list.seal_rqst_status eq 'AP0105'}">
														<img src="<%=IMAGE%>/icon/icon_situation08.gif" alt="<c:out value='${list.seal_rqst_status_nm}'/>" title="<c:out value='${list.seal_rqst_status_nm}'/>"/>				
													</c:when>
													<c:otherwise>
														&nbsp;
													</c:otherwise>
												</c:choose>					            
								            </td>
								            <td class="tC overflow" title="<c:out value='${list.seal_knd_nm}'/> <c:out value='${list.apl_out_nm}'/>" >
								            	<c:out value='${list.seal_knd_nm}'/>
								            	<c:if test="${list.apl_out_yn eq 'Y'}">
													<br><c:out value='${list.apl_out_nm}'/>
												</c:if>					            	
								            </td>
								             <td class="tC">
								             <c:choose>
													<c:when test="${list.DOC_YN eq 'Y'}">
														<img src="<%=IMAGE%>/icon/icon_yes.gif" />											
													</c:when>
													<c:otherwise>
														<img src="<%=IMAGE%>/icon/icon_no.gif" />					
													</c:otherwise>
												</c:choose>
								            </td>
										</tr>
										</c:forEach>
								    </c:when>
								    <c:otherwise>
										<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
											<td colspan="8" align="center"><spring:message code="las.msg.succ.noResult" /></td>
										</tr>
								    </c:otherwise>
							</c:choose>
					  </tbody>
					  </table>
					<!-- //list -->
				
				</c:if>		
				<!-- //신청목록 -->
				<!-- //신청목록 -->

				
				<!-- 처리목록 -->
				<!-- 처리목록 -->
				<c:if test="${prc_mode == 'prc'}">
					<!-- button -->
					<div class="fR mb3">					
						<span class="btn_all_w"><span class="excel_down"></span><a href="javascript:pageAction('excel')"><spring:message code="las.page.button.excelDownload" /><!-- 엑셀다운로드 --></a></span>
					</div>
					<!-- //button -->
	
						<!-- list -->
						<table class="list_basic mt5">
								<colgroup>
									<col width="7%" />
									<col width="15%" />
									<col width="8%" />
									<col width="6%" />
									<col width="8%" />
									<col width="10%" />	
									<col width="3%" />
									<col width="3%" />
									<col width="6%" />		
									<col width="8%" />
									<col width="8%" />						
								</colgroup>
								<tr>
									<th rowspan='2'><spring:message code="clm.field.signmng.reqday" /><!-- 신청일자 --></th>
								    <th rowspan='2'><spring:message code="clm.field.signmng.title" /><!-- 건명 --></th>
								    <th rowspan='2'><spring:message code="clm.field.signmng.towhere" /><!-- 제출처 --></th>
								    <th rowspan='2'><spring:message code="clm.field.signmng.reqman" /><!-- 신청자 --></th>
									<th rowspan='2'><spring:message code="clm.field.signmng.apltype" /><!-- 신청유형 --></th>
								    <th colspan='2' ><spring:message code="clm.field.signmng.aplinfo" /><!-- 신청내역 --></th>
								    <th colspan='4' ><spring:message code="clm.field.signmng.aplprcinfo" /><!-- 처리내역 --></th>
								</tr>
								<tr>
									<th class='sub01' style="border-left:1px solid #CBDCE4" ><spring:message code="clm.field.signmng.sign" /><!-- 날인 --></th>
								    <th class='sub01'><spring:message code="clm.field.signmng.addCert" /><!-- 증명 --><br><spring:message code="clm.field.signmng.adddoc" /><!-- 서류 --></th>
								   	<th class='sub01'><spring:message code="clm.field.signmng.addkbn" /><!-- 구분 --></th>
								    <th class='sub01'><spring:message code="clm.field.signmng.prcman" /><!-- 처리자 --></th>
								    <th class='sub01'><spring:message code="clm.field.signmng.prcstat" /><!-- 처리상태 --></th>
								    <th class='sub01'><spring:message code="clm.field.signmng.prcday" /><!-- 처리일자 --></th>
								</tr>
					  <tbody>
					  
						 <c:choose>
									<c:when test="${pageUtil.totalRow > 0}">
										<c:forEach var="list" items="${resultList}">						
											<tr style="cursor:pointer;"  onClick="javascript:detailView(<c:out value='${list.apl_sqn}'/>);"  >
											    <td rowspan='2' class="tC"><c:out value='${list.seal_rqstday_txt}'/></td>
												<td rowspan='2' class="overflow" title="<c:out value='${list.title}' escapeXml="false" />" ><c:out value='${list.title}' escapeXml="false" /></td>
									            <td rowspan='2' class="overflow" title="<c:out value='${list.sbm}' escapeXml="false" />"><c:out value='${list.sbm}' escapeXml="false"/></td>
									            <td rowspan='2' class="tC overflow" title="<c:out value='${list.seal_rqstman_nm}'/>/<c:out value='${list.seal_rqstman_jikgup_nm}'/>/<c:out value='${list.seal_rqst_dept_nm}'/>">
									            <nobr><c:out value='${list.seal_rqstman_nm}'/></nobr></td>
									            <td rowspan='2' class="tC"><c:out value='${list.apl_cls_nm}'/></td>
									            <td rowspan='2' class="tC overflow" title="<c:out value='${list.seal_knd_nm}'/> <c:out value='${list.apl_out_nm}'/>" ><c:out value='${list.seal_knd_nm}'/>
									            	<c:if test="${list.apl_out_yn eq 'Y'}">
														<br><c:out value='${list.apl_out_nm}'/>
													</c:if><!-- 날인 -->	
												</td>									            				            
										        <td rowspan='2' class="tC"><c:out value='${list.doc_yn}'/><!-- 증명서류 --></td>
									            
									            <td class="tC"><spring:message code="clm.field.signmng.sign" /><!-- 날인 --></td>
									            <td class="overflow tC" title="<c:out value='${list.seal_ffmtman_txt}'/>" ><c:out value='${list.seal_ffmtman_nm}'/><!-- 날인처리자 --></td>
									            <td class="tC">
										            
										            <c:choose>
														<c:when test="${list.seal_ffmt_status eq 'SL0103'}">
															<img src="<%=IMAGE%>/icon/icon_situation03.gif" alt="<c:out value='${list.seal_ffmt_status_nm}'/>" title="<c:out value='${list.seal_ffmt_status_nm}'/>"/>				
														</c:when>
														<c:when test="${list.seal_ffmt_status eq 'SL0102'}">
															<img src="<%=IMAGE%>/icon/icon_situation02.gif" alt="<c:out value='${list.seal_ffmt_status_nm}'/>" title="<c:out value='${list.seal_ffmt_status_nm}'/>"/>				
														</c:when>
														<c:when test="${list.seal_ffmt_status eq 'SL0101'}">
															<img src="<%=IMAGE%>/icon/icon_situation01.gif" alt="<c:out value='${list.seal_ffmt_status_nm}'/>" title="<c:out value='${list.seal_ffmt_status_nm}'/>"/>				
														</c:when>
														<c:otherwise>
															&nbsp;
														</c:otherwise>
													</c:choose>
												
												</td>
									            <td class="tC"><c:out value='${list.seal_ffmtday_txt}'/><!-- 처리일 --></td>	
											</tr>
											<tr style="cursor:pointer;"  onClick="javascript:detailView(<c:out value='${list.apl_sqn}'/>);" >
												<td class="tC" style="border-left:1px solid #CBDCE4" ><spring:message code="clm.field.signmng.addCert" /><!-- 증명 --></td>
									            <td class="overflow tC" title="<c:out value='${list.doc_issuer_txt}'/>" ><c:out value='${list.doc_issuer_txt}'/><!-- 증명서류발급처리자 --></td>
									            <td class="tC">					            
									            	<c:choose>
														<c:when test="${list.doc_issue_status eq 'DC0102'}">
														    
														    <c:choose>
													            <c:when test="${list.DOC_YN eq 'Y'}">
														            <img src="<%=IMAGE%>/icon/icon_situation03.gif" alt="<c:out value='${list.doc_issue_status_nm}'/>" title="<c:out value='${list.doc_issue_status_nm}'/>"/>											
													            </c:when>
													            <c:otherwise>
														            &nbsp;
													            </c:otherwise>
												             </c:choose>
																			
														</c:when>
														<c:when test="${list.doc_issue_status eq 'DC0101'}">
														
														    <c:choose>
													             <c:when test="${list.DOC_YN eq 'Y'}">
														             <img src="<%=IMAGE%>/icon/icon_situation01.gif" alt="<c:out value='${list.doc_issue_status_nm}'/>" title="<c:out value='${list.doc_issue_status_nm}'/>"/>											
													             </c:when>
													             <c:otherwise>
														             &nbsp;
													             </c:otherwise>
												            </c:choose>
												            				
														</c:when>
														<c:otherwise>
															&nbsp;
														</c:otherwise>
													</c:choose>									            
									            
									            </td>
									            <td class="tC"><c:out value='${list.doc_issueday_txt}'/><!-- 처리일 --></td>	
											</tr>
										</c:forEach>
								    </c:when>
								    <c:otherwise>
										<tr>
											<td colspan="11" align="center"><spring:message code="las.msg.succ.noResult" /></td>
										</tr>
								    </c:otherwise>
							</c:choose>
					  </tbody>
					  </table>
					<!-- //list -->
		</c:if>
		<!-- //처리목록 -->
		<!-- //처리목록 -->
		
		<!-- 인장반출내역 -->
		<!-- 인장반출내역 -->
		<c:if test="${prc_mode == 'aplout'}">
		
		<!-- search-->
	            <div class="search_box">
					<div class="search_box_inner">
						<table class="search_tb">
							<colgroup>
								<col/>
								<col width="78px"/>
								</colgroup>
								<tr>
								<td>
								<table class="search_form">
									<colgroup>
										<col width="8%"/>
										<col width="17%"/>
										<col width="8%"/>
										<col width="17%"/>
										<col width="8%"/>
										<col width="17%"/>
										<col width="8%"/>
										<col width="17%"/>
									</colgroup>
									<tr>
										<th><spring:message code="clm.field.signmng.aplyymm" /><!-- 신청년월 --></th>
										<td><nobr><select id="srch_apl_y" name="srch_apl_y" class="" onChange="javascpript:setSrch_apl_m(this.value)">
												<option value="" <c:if test="${command.srch_apl_y eq ''}">selected</c:if>><spring:message code="clm.field.signmng.rec30" /><!-- 최근30일 --></option>
												<option value="2013" <c:if test="${command.srch_apl_y eq '2013'}">selected</c:if>>2013</option>
												<option value="2012" <c:if test="${command.srch_apl_y eq '2012'}">selected</c:if>>2012</option>
												<option value="2011" <c:if test="${command.srch_apl_y eq '2011'}">selected</c:if>>2011</option>
												<option value="2010" <c:if test="${command.srch_apl_y eq '2010'}">selected</c:if>>2010</option>
												<option value="2009" <c:if test="${command.srch_apl_y eq '2009'}">selected</c:if>>2009</option>
											</select>
											<select class=""  id="srch_apl_m" name="srch_apl_m" >
												<option value="" <c:if test="${command.srch_apl_m eq ''}">selected</c:if>><spring:message code="clm.page.field.board.srchTotal" /><!-- 전체 --></option>
												<option value="01" <c:if test="${command.srch_apl_m eq '01'}">selected</c:if>><spring:message code="clm.field.signmng.m1" /><!-- 1월 --></option>
												<option value="02" <c:if test="${command.srch_apl_m eq '02'}">selected</c:if>><spring:message code="clm.field.signmng.m2" /><!-- 2월 --> </option>
												<option value="03" <c:if test="${command.srch_apl_m eq '03'}">selected</c:if>><spring:message code="clm.field.signmng.m3" /><!-- 3월 --></option>
												<option value="04" <c:if test="${command.srch_apl_m eq '04'}">selected</c:if>><spring:message code="clm.field.signmng.m4" /><!-- 4월 --></option>
												<option value="05" <c:if test="${command.srch_apl_m eq '05'}">selected</c:if>><spring:message code="clm.field.signmng.m5" /><!-- 5월 --></option>
												<option value="06" <c:if test="${command.srch_apl_m eq '06'}">selected</c:if>><spring:message code="clm.field.signmng.m6" /><!-- 6월 --></option>
												<option value="07" <c:if test="${command.srch_apl_m eq '07'}">selected</c:if>><spring:message code="clm.field.signmng.m7" /><!-- 7월 --></option>
												<option value="08" <c:if test="${command.srch_apl_m eq '08'}">selected</c:if>><spring:message code="clm.field.signmng.m8" /><!-- 8월 --></option>
												<option value="09" <c:if test="${command.srch_apl_m eq '09'}">selected</c:if>><spring:message code="clm.field.signmng.m9" /><!-- 9월 --></option>
												<option value="10" <c:if test="${command.srch_apl_m eq '10'}">selected</c:if>><spring:message code="clm.field.signmng.m10" /><!-- 10월 --></option>
												<option value="11" <c:if test="${command.srch_apl_m eq '11'}">selected</c:if>><spring:message code="clm.field.signmng.m11" /><!-- 11월 --></option>
												<option value="12" <c:if test="${command.srch_apl_m eq '12'}">selected</c:if>><spring:message code="clm.field.signmng.m12" /><!-- 12월 --></option>
											</select></nobr>
											</td>										
										
										<th><spring:message code="clm.field.signmng.reqman" /><!-- 신청자 --></th>
								    	<td><input type="text"  id="srch_seal_rqstman_nm" name="srch_seal_rqstman_nm" class="text" value="<c:out value='${command.srch_seal_rqstman_nm}'/>"  /></td>
										<th><spring:message code="clm.field.signmng.prcstat" /><!-- 처리상태 --></th>
										<td>
											<select class="w_70" id="srch_seal_ffmt_status" name="srch_seal_ffmt_status" ></select>
										</td>
										<th><spring:message code="clm.field.signmng.rtnyn" /><!-- 반납여부 --></th>
								    	<td>
								    		<select class=""  id="srch_rtn_yn" name="srch_rtn_yn" >
												<option value="" <c:if test="${command.srch_rtn_yn eq ''}">selected</c:if>><spring:message code="clm.page.field.board.srchTotal" /><!-- 전체 --></option>
												<option value="Y" <c:if test="${command.srch_rtn_yn eq 'Y'}">selected</c:if>>Y</option>
												<option value="N" <c:if test="${command.srch_rtn_yn eq 'N'}">selected</c:if>>N</option>												
											</select>								    	
								    	</td>
									</tr>
									<tr>
										<th><spring:message code="clm.field.signmng.aplno" /><!-- 반출인장번호 --></th>
										<td>
											<input type="text" id="srch_prc_seal_no" name="srch_prc_seal_no" value="<c:out value='${command.srch_prc_seal_no}'/>" class="text w_70" />
										</td>
										<th><spring:message code="clm.field.signmng.outfromto" /><!-- 반출기간 --></th>
								    	<td>
											<input type="text" name="srch_prc_ymd_start" id="srch_prc_ymd_start" value="<c:out value='${command.srch_prc_ymd_start}'/>"  class="text_calendar"  />
											<%--  ~ 
											<input type="text" name="srch_prc_ymd_end" id="srch_prc_ymd_end" value="<c:out value='${command.srch_prc_ymd_end}'/>" class="text_calendar" /> --%>
										</td>
								    	<th><spring:message code="clm.field.signmng.rtnday" /><!-- 반납일 --></th>
								    	<td colspan="2">
											<NOBR><input type="text" name="srch_rtn_ymd_start" id="srch_rtn_ymd_start" value="<c:out value='${command.srch_rtn_ymd_start}'/>"  class="text_calendar"  />
											 ~ 
											<input type="text" name="srch_rtn_ymd_end" id="srch_rtn_ymd_end" value="<c:out value='${command.srch_rtn_ymd_end}'/>" class="text_calendar"  /></NOBR>
										</td>										
									</tr>
									
								</table>
							</td>
							<td class="vb tC"><a href="javascript:pageAction('search')"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="Search" title="Search"/></a></td>
							</tr>
						</table>
					</div>
	            </div>     
				<!-- search-->
		
			<!-- button -->
			<div class="fR mb3">					
				<span class="btn_all_w"><span class="excel_down"></span><a href="javascript:pageAction('excel')"><spring:message code="las.page.button.excelDownload" /><!-- 엑셀다운로드 --></a></span>
			</div>
			<!-- //button -->

				<!-- list -->
						<table class="list_basic mt5">
								<colgroup>									
									<col width="10%" />
									<col width="15%" />
									<col width="15%" />
									<col width="15%" />
									<col width="10%" />
									<col width="10%" />
									<col width="10%" />
									<col width="15%" />						
								</colgroup>
								<tr>
									<th rowspan='2'><spring:message code="clm.field.signmng.reqday" /><!-- 신청일자 --></th>
								    <th rowspan='2'><spring:message code="clm.field.signmng.reqman" /><!-- 신청자 --></th>
								    <th rowspan='2'><spring:message code="clm.field.signmng.aplprcman" /><!-- 날인담당자 --></th>
								    <th rowspan='2'><spring:message code="clm.field.signmng.aplno" /><!-- 반출인장번호 --></th>
								    <th colspan='2' ><spring:message code="clm.field.signmng.outfromto" /><!-- 반출기간 --></th>
								    <th rowspan='2'><spring:message code="clm.field.signmng.rtnday" /><!-- 반납일 --></th>
								  	<th rowspan='2'><spring:message code="clm.field.signmng.prcstat" /><!-- 처리상태 --></th>
								</tr>
								<tr>
									<th class='sub01' style="border-left:1px solid #CBDCE4" >From<!--FROM--></th>
								    <th class='sub01'>To</th>
								</tr>
					  <tbody>
					  
						 <c:choose>
									<c:when test="${pageUtil.totalRow > 0}">
										<c:forEach var="list" items="${resultList}">
			
										<tr >
										    <td class="tC" ><c:out value='${list.seal_rqstday_txt}'/><!--신청일--></td>
								            <td class="tC overflow" title="<c:out value='${list.seal_rqstman_nm}'/>/<c:out value='${list.seal_rqstman_jikgup_nm}'/>/<c:out value='${list.seal_rqst_dept_nm}'/>"><c:out value='${list.seal_rqstman_nm}'/>/<c:out value='${list.seal_rqstman_jikgup_nm}'/>/<c:out value='${list.seal_rqst_dept_nm}'/><!--신청자--></td>
								            <td class="tC overflow" title="<c:out value='${list.seal_ffmtman_nm}'/>/<c:out value='${list.seal_ffmtman_jikgup_nm}'/>/<c:out value='${list.seal_ffmt_dept_nm}'/>"><c:out value='${list.seal_ffmtman_nm}'/>/<c:out value='${list.seal_ffmtman_jikgup_nm}'/>/<c:out value='${list.seal_ffmtman_dept_nm}'/><!--날인담당자--></td>
								            <td class="tC" ><c:out value='${list.RTN_SEAL_NO}' escapeXml="false" /><!--인장번호--></td>
								            <td class="tC"><c:out value='${list.PRC_YMD_FROM_TXT}'/><!-- 처리반출일--></td>
								            <td class="tC"><c:out value='${list.PRC_YMD_TO_TXT}'/><!-- 처리반출일--></td>
								            <td class="tC"><c:out value='${list.RTN_YMD_TXT}'/><!-- 반납일--></td>		
								            <td class="tC">					            
									            <c:choose>
														<c:when test="${list.seal_ffmt_status eq 'SL0103'}">
															<img src="<%=IMAGE%>/icon/icon_situation03.gif" alt="<c:out value='${list.seal_ffmt_status_nm}'/>" title="<c:out value='${list.seal_ffmt_status_nm}'/>"/>				
														</c:when>
														<c:when test="${list.seal_ffmt_status eq 'SL0102'}">
															<img src="<%=IMAGE%>/icon/icon_situation02.gif" alt="<c:out value='${list.seal_ffmt_status_nm}'/>" title="<c:out value='${list.seal_ffmt_status_nm}'/>"/>				
														</c:when>
														<c:when test="${list.seal_ffmt_status eq 'SL0101'}">
															<img src="<%=IMAGE%>/icon/icon_situation01.gif" alt="<c:out value='${list.seal_ffmt_status_nm}'/>" title="<c:out value='${list.seal_ffmt_status_nm}'/>"/>				
														</c:when>
														<c:otherwise>
															&nbsp;
														</c:otherwise>
													</c:choose>			            
								            </td>								            						            
										</tr>
										</c:forEach>
								    </c:when>
								    <c:otherwise>
										<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
											<td colspan="8" align="center"><spring:message code="las.msg.succ.noResult" /></td>
										</tr>
								    </c:otherwise>
							</c:choose>
					  </tbody>
					  </table>
					<!-- //list -->
		
		</c:if>
		<!-- //인장반출내역 -->
		<!-- //인장반출내역 -->

		<div class="total_num">Total : <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%></div>
			
		<!-- pagination -->
		<div class="pagination">
			<%=pageUtil.getPageNavi(pageUtil, "") %>
		</div>
		<!-- //pagination -->
		
			</form:form>
		</div>
		<!-- //content_in -->
	</div>
	<!-- //content -->

			
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
<!-- //wrap -->

<!-- footer -->
<script language="JavaScript" src="/script/clms/footer.js"></script>
<!-- //footer -->
</body>
</html>
