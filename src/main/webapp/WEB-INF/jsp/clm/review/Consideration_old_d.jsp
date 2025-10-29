<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="com.sec.clm.review.dto.ConsultationForm" %>

<%--
/**
 * 파  일  명 : Consideration_old_d.jsp
 * 프로그램명 : 의뢰내역 - 검토의뢰 - 계약검토[일반의뢰]
 * 설      명 : (구)법무시스템에서 이관된 검토정보에 대해 상세조회하는 페이지
 * 작  성  자 : 김재원
 * 작  성  일 : 2013.04
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
	String menuNavi = (String) request.getAttribute("secfw.menuNavi");

	ArrayList listDc = (ArrayList) request.getAttribute("listDc");

	ConsultationForm command = (ConsultationForm) request.getAttribute("command");
	ListOrderedMap lomRq = (ListOrderedMap) request.getAttribute("lomRq");
	ListOrderedMap lomMn = (ListOrderedMap) request.getAttribute("lomMn");
	ListOrderedMap lomVc = (ListOrderedMap) request.getAttribute("lomVc");
	
	
%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta sci="Consideration_old_d.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><spring:message code="clm.main.title" /></title>
<link href="<%=CSS%>/las.css"    type="text/css" rel="stylesheet" />

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="/script/clms/common.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>

<script language="javascript">

	$(document).ready(function(){
		//detailInfo("<c:out value='${lomRq.cnsdreq_id}'/>");
	
		detailContractMaster();
		
		initPage('');
		
		//검토의견만 활성화 
		//if($("#blngt_orgnz").val() == $("#mn_cnsd_dept").val()){ //정검토부서 이면
			//document.getElementById('tr_down03').style.display = "block";
		//}
		 
		viewHiddenProgress(false);
	});

	/**
	* page Action
	*/
	function forwardConsideration(arg){
		var frm = document.frm;
		
		if(arg == "LIST"){									//모든 계약 목록은 mycontract로 이동하게 됩니다.
<%
			//if(command.getStatus_mode() != null && "myContract".equals(command.getStatus_mode())) {
%>
			frm.target = "_self";		 
			frm.action = "<c:url value='/clm/manage/myContract.do' />";
			frm.method.value = "listMyContract";		
			frm.submit();
<%
			//} else {
%>
			//frm.target = "_self";		 
			//frm.action = "<c:url value='/las/contractmanager/consideration.do' />";
			//frm.method.value = "listConsideration";		
			//frm.submit();
<%
			//}
%>
		}else if(arg == "MODIFY"){ //수정으로 이동
			frm.target = "_self";
			frm.action = "<c:url value='/las/contractmanager/consideration.do' />";
			frm.method.value = "detailConsiderationForModify";
			frm.submit();
		}else if(arg == "DELETE"){
			if(confirm("<spring:message code='las.page.field.contractManager.askDel'/>")){
				frm.target = "_self";
				frm.action = "<c:url value='/las/contractmanager/consideration.do' />";
				frm.method.value = "deleteOldConsideration";
				frm.submit();
			}
		}
	}
	
	// 인쇄 요청
	function forwardConsideration2() {
		var frm = document.frm;

		var options = {
	 		url: "<c:url value='/las/contractmanager/consideration.do?method=detailConsiderationPrint' />",
	 		type: "post",
	 		async: false,
	 		dataType: "html",
	 			
	 		success: function(data){
	 			//alert(data);
	 			frm.contents.value=data;
	 			showCompletionPop(); 			
	 		}
	   };
	   $("#frm").ajaxSubmit(options);
	}
	

	function showCompletionPop(){
		
		var frm = document.frm;
		
		PopUpWindowOpen('', '1024','560',true,'popUpPrint');
		
		frm.action	= "<c:url value='/las/contractmanager/consideration.do' />";
		frm.method.value = "forwardPop";
		frm.target = "popUpPrint";
		frm.submit();
	}
	
	/**
	* 계약검토의뢰등록 폼  inner View 
	*/
	function detailContractMaster(){
		var options = {
			url: "<c:url value='/las/contractmanager/consideration.do?method=detailOldContractMaster' />",
			type: "post",
			async: false,
			dataType: "html",
			success: function (data) {
				$("#tab_contents").html("");
				$("#tab_contents").html(data);
				
				// (구)법무에서 이관된 데이터만 검토이력을 보여줌. (사업부 이관 데이터는 검토이력이 없음)
				if("<c:out value='${lomRq.cntrt_info_gbn}'/>" == "C05402") {
					contractHisList();
				}
			}
		};
		$("#frm").ajaxSubmit(options);
	}

	
	/**
	* 검토의뢰 상세정보 리턴
	*/
	function detailInfo(cnsdreq_id){
		
		 //초기화 : 연계 시 이 부분을 주석처리
		$('input[name=cnsdreq_id]').val("");

	    //설정
	    $('#cnsdreq_id').val(cnsdreq_id);
	    
	    var options = {
	    		url: "<c:url value='/las/contractmanager/consideration.do?method=detailOldCnsdreq' />",
	    		type: "post",
	    		async: false,
	    		dataType: "html",
	    			
	    		success: function(data){
	    			$("#detailInfoScript").html("");
	    			$("#detailInfoScript").html(data);
	    			makeInnerHtml();
	    		}
	    };
	    $("#frm").ajaxSubmit(options);
	}

	function initPage(arg){
		var frm = document.frm;		
		var	file_key = frm.cntrt_id.value +"@"+ frm.cnsdreq_id.value;
		
	   //계약관련 #1 계약서
	    frm.target          = "fileList1";
	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    = "forwardClmsFilePage";
	    frm.file_bigclsfcn.value = "F012";
	    frm.file_midclsfcn.value = "F01202";
	    frm.file_smlclsfcn.value = "F0120201";
		frm.ref_key.value = file_key;
		frm.view_gbn.value = "download";
	    frm.fileInfoName.value = "fileInfos1";
	    frm.fileFrameName.value = "fileList1";
	    frm.submit(); 
	}


	//팝업오픈
	function PopUpWindowOpen(surl, popupwidth, popupheight, bScroll, popName) {
		if( popupwidth  > window.screen.width )
			popupwidth = window.screen.width;
		if( popupheight > window.screen.height )
			popupheight = window.screen.height; 
			
		if( isNaN(parseInt(popupwidth)) ){
			Top  = (window.screen.availHeight - 600) / 2;
			Left = (window.screen.availWidth  - 800) / 2;
		} else {
			Top  = (window.screen.availHeight - popupheight)  / 2;
			Left = (window.screen.availWidth  - popupwidth) / 2;
		}

		if (Top < 0) Top = 0;
		if (Left < 0) Left = 0;
		
		popupwidth = parseInt(popupwidth) + 10 ;
		popupheight = parseInt(popupheight) + 29 ;
		
		var Feture = "toolbar=0, location=0, status=1, menubar=0, scrollbars=yes, resizable=yes, top="+Top+", left="+Left+", width="+popupwidth+", height="+popupheight;
		PopUpWindow = window.open(surl, popName , Feture);

		PopUpWindow.focus();
		
	}
	
</script>


</head>
<body>
<div id="wrap">
	<!-- container -->
	<div id="container">
		<form:form name="frm" id='frm' method="post" autocomplete="off">
		<input type="hidden" name="method" id="method" value="" />
		<input type="hidden" name="menu_id" id="menu_id" value="<c:out value='${command.menu_id}'/>" />
		<input type="hidden" name="curPage" id="curPage" value="<c:out value='${command.curPage}'/>" />
		<input type="hidden" name="status" id="status" value="" />
		<input type="hidden" name="tab_cnt" id="tab_cnt" value="<c:out value='${lomRq.total_cnt}'/>" /><!-- Tab 계약 증가 값 -->
		<input type="hidden" name="cnsdreq_id" id="cnsdreq_id" value="<c:out value='${lomRq.cnsdreq_id}'/>" />		 
		<input type="hidden" name="cntrt_id" id="cntrt_id"  value="<c:out value='${lomRq.cntrt_id}'/>" />		
		<input type="hidden" name="prev_cnsdreq_id" id="prev_cnsdreq_id" value="<c:out value='${lomRq.prev_cnsdreq_id}'/>" />
		<input type="hidden" name="stat_flag" id="stat_flag" value="<c:out value='${command.stat_flag}'/>" />
		<input type="hidden" name="page_flag" id="page_flag" value="<c:out value='${command.page_flag}'/>" />
		<input type="hidden" name="dmstfrgn" id="dmstfrgn" value="<c:out value='${command.dmstfrgn}'/>" />
		<input type="hidden" name="req_title" id="req_title" value="<c:out value='${lomRq.req_title}'/>" />
		<input type="hidden" name="cntrt_oppnt_nm" id="cntrt_oppnt_nm" value="<c:out value='${command.cntrt_oppnt_nm}'/>" />
			
		<!-- 첨부파일정보 -->
		<input type="hidden" name="fileInfos"   value="" /> <!-- 저장될 파일 정보 -->
		<input type="hidden" name="fileInfos1"   value="" /> <!-- 저장될 파일 정보 -->	
		<input type="hidden" name="fileInfos2"   value="" /> <!-- 저장될 파일 정보 -->	
		<input type="hidden" name="fileInfos3"   value="" /> <!-- 저장될 파일 정보 -->	
		<input type="hidden" name="fileInfos4"   value="" /> <!-- 저장될 파일 정보 -->
		<input type="hidden" name="fileInfos6"   value="" /> <!-- 저장될 파일 정보 -->	
		<input type="hidden" name="fileInfos5"   value="" /> <!-- 저장될 파일 정보 -->	
		
		<input type="hidden" name="fileInfoName"    value="" /> <!-- 첨부파일 정보가 리턴될 객체명 -->
		<input type="hidden" name="fileFrameName"    value="" /> <!-- 첨부파일 화면 iFrame 명 -->
			
		<input type="hidden" name="file_bigclsfcn"  value="" /> <!-- 대분류 -->	
		<input type="hidden" name="file_midclsfcn"  value="" /> <!-- 중분류 -->	
		<input type="hidden" name="file_smlclsfcn"  value="" /> <!-- 소분류 -->	
		<input type="hidden" name="ref_key"     value="<c:out value='${lomRq.cnsdreq_id}'/>" /> <!-- 키값 -->	
		<input type="hidden" name="view_gbn"    value="download" /> <!-- 첨부파일 출력 화면 종류 : upload, modify, download -->		
		
		<input type="hidden" name="blngt_orgnz" 	id="blngt_orgnz" 	value="<c:out value='${command.blngt_orgnz}'/>" />	<!-- 소속조직 -->
		<input type="hidden" name="top_role" 		id="top_role" 		value="<c:out value='${command.top_role}'/>" />		<!-- ROLL -->	
		<input type="hidden" name="file_yn" id="file_yn" value="" />
		<input type="hidden" name="multiYn" value="" /><!-- 멀티 -->
		<input type="hidden" name="plndbn_req_yn" id="plndbn_req_yn" value="<c:out value="${lomRq.plndbn_req_yn}" />" /><!-- 최종본 의뢰 여부  -->
		
		<input type="hidden" name="srch_type_cd" value="<c:out value='${command.srch_type_cd}'/>" />
		<input type="hidden" name="srch_req_title" value="<c:out value='${command.srch_req_title}'/>" />
		<input type="hidden" name="srch_orgnz" value="<c:out value='${command.srch_orgnz}'/>" />
		<input type="hidden" name="srch_start_dt" value="<c:out value='${command.srch_start_dt}'/>" />
		<input type="hidden" name="srch_end_dt" value="<c:out value='${command.srch_end_dt}'/>" />
		<input type="hidden" name="srch_reqman_nm" value="<c:out value='${command.srch_reqman_nm}'/>" />
		<input type="hidden" name="srch_prgrs_status" value="<c:out value='${command.srch_prgrs_status}'/>" />
		<input type="hidden" name="srch_owner_dept" value="<c:out value='${command.srch_owner_dept}'/>" />
		<input type="hidden" name="srch_law_status" value="<c:out value='${command.srch_law_status}'/>" />
		<input type="hidden" name="srch_ip_status" value="<c:out value='${command.srch_ip_status}'/>" />
		<input type="hidden" name="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}'/>" />
		<input type="hidden" name="srch_biz_depth" value="<c:out value='${command.srch_biz_depth}'/>" />
		<input type="hidden" name="srch_cnclsnpurps" value="<c:out value='${command.srch_cnclsnpurps}'/>" />
		<input type="hidden" name="srch_cntrt_oppnt_nm" value="<c:out value='${command.srch_cntrt_oppnt_nm}'/>" />
		<input type="hidden" name="srch_cnsd_cont" value="<c:out value='${command.srch_cnsd_cont}'/>" />
		<input type="hidden" name="srch_if_sys_cd" value="<c:out value='${command.srch_if_sys_cd}'/>" />
		
		<!-- 계약관리의 My Contract로 돌아갈때 필요한 파라미터 -->
		<input type="hidden" name="status_mode" value="<c:out value='${command.status_mode}'/>" />
		<input type="hidden" name="list_mode" value="<c:out value='${command.list_mode}'/>" />
		<input type="hidden" name="srch_resp_dept" value="<c:out value='${command.srch_resp_dept}'/>" />
		<input type="hidden" name="srch_oppnt_cd" value="<c:out value='${command.srch_oppnt_cd}'/>" />
		<input type="hidden" name="srch_review_title" value="<c:out value='${command.srch_review_title}'/>" />
		<input type="hidden" name="srch_start_reqday" value="<c:out value='${command.srch_start_reqday}'/>" />
		<input type="hidden" name="srch_end_reqday" value="<c:out value='${command.srch_end_reqday}'/>" />
		<input type="hidden" name="srch_resp_dept_nm" value="<c:out value='${command.srch_resp_dept_nm}'/>" />
		<input type="hidden" name="srch_cnsdman_nm" value="<c:out value='${command.srch_cnsdman_nm}'/>" />
		<input type="hidden" name="srch_oppnt_nm" value="<c:out value='${command.srch_oppnt_nm}'/>" />
		<input type="hidden" name="srch_step" value="<c:out value='${command.srch_step}'/>" />
		<input type="hidden" name="srch_state" value="<c:out value='${command.srch_state}'/>" />
		<input type="hidden" name="srch_biz_clsfcn" value="<c:out value='${command.srch_biz_clsfcn}'/>" />
		<input type="hidden" name="srch_cnclsnpurps_bigclsfcn" value="<c:out value='${command.srch_cnclsnpurps_bigclsfcn}'/>" />
		<input type="hidden" name="srch_start_cnlsnday" value="<c:out value='${command.srch_start_cnlsnday}'/>" />
		<input type="hidden" name="srch_end_cnlsnday" value="<c:out value='${command.srch_end_cnlsnday}'/>" />
		
		<!-- Sungwoo added search parameter 2014-06-12 -->
		<input type="hidden" name="srch_division" value="<c:out value='${command.srch_division}'/>" />					<!-- Original Copy -->
		<input type="hidden" name="srch_vendor_type" value="<c:out value='${command.srch_vendor_type}'/>" />					<!-- Original Copy -->
		<input type="hidden" name="srch_vendor_type_detail" value="<c:out value='${command.srch_vendor_type_detail}'/>" />		<!-- Original Copy -->
		
		<c:forEach var="cntrtMt" items="${listDc}">
			<input type="hidden" name="master_cntrt_ids"  value="<c:out value='${cntrtMt.cntrt_id}' />" /> <!-- 계약_마스터_계약_ID -->	
		</c:forEach>	
		
		<input type="hidden" name="contents" id="contents" value=""/>
				
		<!-- location -->
		<div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0" alt="Home" /><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //location -->
		<!-- title -->
		<div class="title">
<%
		if(command.getStatus_mode() != null && "myContract".equals(command.getStatus_mode())) {
%>
			<h3>My Contract</h3>
<%
		} else {
%>
			<h3><spring:message code="las.page.field.contractmanager.consideration_d.title_cnsdreq" /></h3>
<%
		}
%>
		</div>
		<!-- //title -->		
		<!-- content -->	
		<div id="content">
			<div class="t_titBtn">
				<!-- title -->
				<div class="title_basic">
					<h4><spring:message code="las.page.field.contractmanager.consideration_d.title_cnsdinfo" /><img src="<%=IMAGE%>/btn/btn_up.gif" alt="up" onclick="tit_Toggle(this,'tr_down01');"/></h4>
				</div>
				<!-- //title -->
				<!--View -->
				<!-- 수정/검토의뢰/최종본작성/목록 -->			
				<!-- button -->
				<div class="btn_wrap_t02">
                <c:if test="${lomRq.cntrt_info_gbn == 'C05403'}">
					<span id="btn_up5" class="btn_all_w"><span class="list"></span><a href="javascript:forwardConsideration2();"><spring:message code="las.page.field.contractManager.print"/></a></span>
					<c:if test="${viewOldContractBtnFlag}">										
						<span id="btn_up5" class="btn_all_w"><span class="edit"></span><a href="javascript:forwardConsideration('MODIFY');"><spring:message code="las.page.field.contractManager.update"/></a></span>
						<span id="btn_up5" class="btn_all_w"><span class="delete"></span><a href="javascript:forwardConsideration('DELETE');"><spring:message code="las.page.field.contractManager.delete"/></a></span>
					</c:if>
			    </c:if>
					<span id="btn_up5" class="btn_all_w"><span class="list"></span><a href="javascript:forwardConsideration('LIST');"><spring:message code="las.page.button.contractmanager.consideration_d.list" /></a></span>
				</div>
				<!-- //button -->
			</div>
			<!-- toptable -->
			<table cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
				<col width="15%" />
				<col width="85%" />
				</colgroup>
				<tr>
					<th><spring:message code="las.page.field.contractmanager.consideration_d.cnsd_title" /></th>
				  	<td><c:out value='${lomRq.req_title}' default="" /></td>
				</tr>
				<tr class="lineAdd">
					<th><spring:message code="las.page.field.contractmanager.consideration_d.reqman_nm" /></th>
					<td><c:out value="${lomRq.reqman_nm}" /> / <c:out value="${lomRq.reqman_jikgup_nm}" /> / <c:out value="${lomRq.req_dept_nm}" /><br/><c:out value="${lomRq.reqman_tel}" /></td>
				</tr>
				<tr class="lineAdd">
						<th class="borTz02"><spring:message code="las.page.field.contractmanager.consideration_d.cnsd_demnd_cont" /></th>
						<td><c:out value='${lomRq.cnsd_demnd_cont}' escapeXml="false" /></td>
				</tr>
			</table>
			
 		
			<!-- //법무 시스템 - 계약검토 -->
			
			<!-- title -->
		   <div class="title_basic">
				<h4><spring:message code="las.page.field.contractmanager.consideration_d.cnsd_default_info" /></h4>
			</div>
			<!-- //title -->
			<!-- tab01 -->
			<ul id="tab_title" class="tab_basic04">
				<c:forEach var="list" items="${listDc}">
				<c:choose>
				<c:when test="${list.rn=='1'}">
		          		<li id="tab_li<c:out value='${list.rn}'/>" class="on"><a href="javascript:titleTabClick('<c:out value='${list.rn}'/>','<c:out value='${list.cntrt_id}'/>');"><spring:message code="las.page.field.contractmanager.consideration_d.cntrt" /><c:out value='${list.rn}'/></a></li>	
				</c:when>
				<c:otherwise>
						<li id="tab_li<c:out value='${list.rn}'/>"><a href="javascript:titleTabClick('<c:out value='${list.rn}'/>','<c:out value='${list.cntrt_id}'/>');"><spring:message code="las.page.field.contractmanager.consideration_d.cntrt" /><c:out value='${list.rn}'/></a></li>
				</c:otherwise>	
				</c:choose>				
				</c:forEach>						
			</ul>
			<!-- 두줄라인 추가 --><div class="tal_topLine"></div><!-- //두줄라인 추가 -->
			<!-- middle table -->
			<div id="tab_contents"></div>
			<!-- //tab_content -->
			
			<!-- 이력정보 -->
			<div id="contractHis-list"></div>
			<!-- //이력정보 -->
 
 		</div>
		
		<div id="detailInfoHtmlDown">
		</div>
		<!-- button -->
		<div class="btn_wrap_c">
              <c:if test="${lomRq.cntrt_info_gbn == 'C05403'}">
			<span id="btn_up5" class="btn_all_w"><span class="list"></span><a href="javascript:forwardConsideration2();"><spring:message code="las.page.field.contractManager.print"/></a></span>
			<c:if test="${viewOldContractBtnFlag}">										
				<span id="btn_up5" class="btn_all_w"><span class="edit"></span><a href="javascript:forwardConsideration('MODIFY');"><spring:message code="las.page.field.contractManager.update"/></a></span>
				<span id="btn_up5" class="btn_all_w"><span class="delete"></span><a href="javascript:forwardConsideration('DELETE');"><spring:message code="las.page.field.contractManager.delete"/></a></span>
			</c:if>
	    </c:if>
			<span id="btn_up5" class="btn_all_w"><span class="list"></span><a href="javascript:forwardConsideration('LIST');"><spring:message code="las.page.button.contractmanager.consideration_d.list" /></a></span>
		</div>		
		
		<!-- //button -->
		<!-- //content -->	
		<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- // footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</form:form>
	</div>
	<!-- //container -->
</div>
</body>
</html>
 
 