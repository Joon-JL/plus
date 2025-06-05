<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>
<%@ page import="com.sec.clm.manage.dto.ConsultationForm" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%--
/**
 * 파  일  명 : RegConsideration_d_new.jsp
 * 프로그램명 : 체결 후 등록 화면
 * 설      명 : 
 * 작  성  자 : chahyunjin
 * 작  성  일 : 2011.08
 */
--%>
<% 
		
	ListOrderedMap lomRq = (ListOrderedMap)request.getAttribute("lomRq"); // 의뢰 정보
	ListOrderedMap lomRq2 = (ListOrderedMap)request.getAttribute("lomRq2"); // 계약정보(기존 inner 에서 가져왔단 정보)
	
	ArrayList listDc = (ArrayList)request.getAttribute("listDc");  // 의뢰정보(lomRq 을 담고 있는 List)
	ArrayList listCa = (ArrayList)request.getAttribute("listCa");  //brice add 11/09
	
%>

<html>
<!-- page name: RegConsideration_d_new.jsp -->
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="clm.main.title" /></title>
<!--2. CSS 추가  --> 
<!--계약관리일 경우  -->
<LINK href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet">
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="/script/clms/common.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/excanvas.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="/script/clms/clms_common.js"></script>
<script>

	$(document).ready(function(){	
		
		/*********** Consideration_innder_d.jsp 에 있던 스크립트 ************************/
		
		// 화면로드시 금액 100,000 형태로 변환
        var frm = document.frm;
        
        //첨부파일 설정 
        initPage();
		
        if(<%=StringUtil.isNull((String)lomRq2.get("cntrt_untprc_expl"))%> == false) { 
            frm.target          = "fileList3";
            frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
            frm.method.value    = "forwardClmsFilePage";
            frm.file_bigclsfcn.value = "F012";
            frm.file_midclsfcn.value = "F01202";
            frm.file_smlclsfcn.value = "F0120211";
            frm.ref_key.value = $('#master_cntrt_id').val();
            frm.view_gbn.value = "download";
            frm.fileInfoName.value = "fileInfos3";
            frm.fileFrameName.value = "fileList3";
            getClmsFilePageCheck('frm');
        }
        
        // 2012.03.28 활성화 계약건의 상태에 따라 DROP버튼 Display added by hanjihoon
        if($('#session_user_id').val() == $('#reqman_id').val() && ($('#depth_status').val() == "C02606" || $('#depth_status').val() == "C02608")){
            $("#id_dropContract").show();
        }else{
            $("#id_dropContract").hide();
        }
        
        /*********** Consideration_innder_d.jsp 에 있던 스크립트 끝 ************************/
		
	    // 2012.02.23 페이지 로딩후 alert added by hanjihoon
        <% if("C04201".equals(lomRq.get("prgrs_status")) && "C02601".equals(lomRq.get("depth_status"))){ 
            if(StringUtil.bvl(session.getAttribute("secfw.session.user_id"), "").equals(lomRq.get("reqman_id"))){
        %>
        
        //alert("작성중인 의뢰건은 수정버튼을 클릭하신 후 의뢰/재의뢰/최종본의뢰를 해야 합니다.");
        alert("<spring:message code="clm.page.msg.manage.announce207" />");
        <% }} %>
        
        contractHisList();
	});
		
	/**
	* page Action
	*/
	function forwardConsideration(arg){
		var frm = document.frm;

		frm.target = "_self";		 
		frm.action = "<c:url value='/clm/manage/registration.do' />";
		frm.submit_status.value = arg;
		if(arg == "list"){	//목록			
			frm.method.value = "listManageRegistration";			
		}else if(arg == "mod"){	//	수정	화면으로 이동 
			frm.status.value = "forwardModify";
			frm.method.value = "forwardModifyRegistration";						
		}

		frm.submit();
		
	}
	 
	  /*
	   * 인쇄 팝업 띄우기
	   */
	  function openPrint2() {
	      var frm = document.frm;
	      
	      PopUpWindowOpen('', '1000','768',true, "popupMyApproval");
	      
	      frm.action  = "<c:url value='/clm/manage/myApproval.do' />";
	      frm.method.value = "forwardPrintPop";
	      frm.target = "popupMyApproval";
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
	
	//거래선 팝업
	 function customerPop2(customerCd, dodun){
	 	PopUpWindowOpen("/clm/draft/customerTest.do?method=detailCustomerShare&customer_cd="+customerCd + "&dodun=" + dodun, 900, 300, false);
	 }
	
	function initPage(){		 
		var frm = document.frm;	
		//계약관련 #4
		    frm.target          = "fileList4";
		    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
		    frm.method.value    = "forwardClmsFilePage";
		    frm.file_bigclsfcn.value = "F012";
		    frm.file_midclsfcn.value = "F01201";
		    frm.file_smlclsfcn.value = "F0120101";
		    frm.ref_key.value = $('#master_cntrt_id').val();
		    frm.fileInfoName.value = "fileInfos4";
		    frm.fileFrameName.value = "fileList4";
		  //frm.submit();
		    getClmsFilePageCheck('frm');
		    
		  //체결본사본
			frm.target          		= "fileList6";
		    frm.action          		= "<c:url value='/clms/common/clmsfile.do' />";
		    frm.method.value    		= "forwardClmsFilePage";
		    frm.fileInfoName.value 		= "fileInfos6";
		    frm.fileFrameName.value 	= "fileList6";
		    
		    frm.file_bigclsfcn.value 	= "F012";
		    frm.file_midclsfcn.value	= "F01203";
			frm.file_smlclsfcn.value 	= "";
		    frm.ref_key.value			= $('#master_cntrt_id').val();
		    
		    getClmsFilePageCheck('frm');
		    
		  //OutLook 첨부파일 2013.10.25 추가
		    frm.target          = "fileListOutlook";
		    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
		    frm.method.value    = "forwardClmsFilePage";
		    frm.file_bigclsfcn.value = "O001";
		    frm.file_midclsfcn.value = "O0011";
		    frm.file_smlclsfcn.value = "O00111";
		    frm.ref_key.value = $('#master_cntrt_id').val();
		    frm.fileInfoName.value = "fileInfosOutlook";
		    frm.fileFrameName.value = "fileListOutlook";
		    getClmsFilePageCheck('frm');
		    
		 	// 2014-04-15 Kevin Added.
		    // GERP readonly iframe load. 
		    frm.target          = "iframeGERP";
		    frm.action          = "<c:url value='/clm/manage/consideration.do' />";
		    frm.gerpPageType.value = "R";		// detail readonly
		    frm.method.value    = "forwardGERPDetail";
			frm.submit();
		}
		
	function openTop30Customer(){
  	 
  		 PopUpWindowOpen('', 500, 540, false, 'Top30Customer');
  		    frm.target = "Top30Customer";
  		 
  		 frm.action = "<c:url value='/clm/draft/customerTest.do' />";
  		 frm.method.value = "listTop30Customer";
  		 frm.submit();
  	 
  	}
  	
</script>
</head>
<body>
<div id="wrap">
	<!-- container -->
	<div id="container">
		<!-- location -->
		<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //location -->
		<!-- title -->
		<div class="title">	<h3><spring:message code="clm.page.msg.manage.aftConclReg" /></h3></div>
		<!-- //title -->		
		<!-- content -->	
		<div id="content">	
			<!-- content_in -->	
			<div id="content_in">			
				<form:form name="frm" id='frm' method="post" autocomplete="off">
					<input type="hidden" name="method" id="method" value="">
					<input type="hidden" name="menu_id" id="menu_id" value="<c:out value='${command.menu_id}'/>" />
					<input type="hidden" name="curPage" id="curPage" value="<c:out value='${command.curPage}'/>">
					<!-- 리스트 검색 조건  -->
					<input type="hidden" name="list_mode" value="<c:out value='${command.list_mode}'/>"/>
					<input type="hidden" name="srch_resp_dept" value="<c:out value='${command.srch_resp_dept}'/>"/>				<!-- 담당부서코드 -->
					<input type="hidden" name="srch_oppnt_cd" value="<c:out value='${command.srch_oppnt_cd}'/>"/>   				<!-- 계약상대방코드 -->
					<input type="hidden" name="srch_review_title" value="<c:out value='${command.srch_review_title}'/>" />		<!-- 의뢰명 -->
					<input type="hidden" name="srch_reqman_nm" value="<c:out value='${command.srch_reqman_nm}'/>" />				<!-- 의뢰자 -->
					<input type="hidden" name="srch_start_reqday" value="<c:out value='${command.srch_start_reqday}'/>" />		<!-- 의뢰 시작일 -->
					<input type="hidden" name="srch_end_reqday" value="<c:out value='${command.srch_end_reqday}'/>" />			<!-- 의뢰 종료일 -->
					<input type="hidden" name="srch_start_cnlsnday" value="<c:out value='${command.srch_start_cnlsnday}'/>" />	<!-- 계약 시작일 -->
					<input type="hidden" name="srch_end_cnlsnday" value="<c:out value='${command.srch_end_cnlsnday}'/>" />		<!-- 계약 종료일 -->
					<input type="hidden" name="srch_resp_dept_nm" value="<c:out value='${command.srch_resp_dept_nm}'/>" />		<!-- 담당부서명 -->
					<input type="hidden" name="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}'/>" />			<!-- 담당자명 -->
					<input type="hidden" name="srch_biz_clsfcn" value="<c:out value='${command.srch_biz_clsfcn}'/>" />			<!-- 계약단계 -->
					<input type="hidden" name="srch_cnsdman_nm" value="<c:out value='${command.srch_cnsdman_nm}'/>" />			<!-- 검토자 -->
					<input type="hidden" name="srch_oppnt_nm" value="<c:out value='${command.srch_oppnt_nm}'/>" />				<!-- 계약상대방 -->
					<input type="hidden" name="srch_state" value="<c:out value='${command.srch_state}' />" />						<!-- 상태 -->
					<input type="hidden" name="srch_if_sys_cd" value="<c:out value='${command.srch_if_sys_cd}' />" />				<!-- 연계시스템 -->
					<input type="hidden" name="srch_cnclsnpurps_bigclsfcn" value="<c:out value='${command.srch_cnclsnpurps_bigclsfcn}'/>" />	<!-- 체결목적-->
					<input type="hidden" name="srch_step" value="<c:out value='${command.srch_step}' />" />						<!-- 상태 -->
					<input type="hidden" name="cnsd_status" id="cnsd_status" value="<c:out value='${lomRq.cnsd_status}'/>" />
					<input type="hidden" name="prgrs_status" id="prgrs_status" value="<c:out value='${lomRq.prgrs_status}'/>" />	
					<input type="hidden" name="plndbn_req_yn" id="plndbn_req_yn" value="<c:out value='${lomRq.plndbn_req_yn}'/>" />
					<input type="hidden" name="sys_nm" id="sys_nm" value="<c:out value='${lomRq.sys_nm}'/>" />
					<input type="hidden" name="mis_id" /> <!-- 12/07/13 추가 consultation 에서 끌어옴 -->
					<!-- 13/10/29 검색 조건 유지 추가 -->
					<input type="hidden" name="srch_cntrt_nm" value="<c:out value='${command.srch_cntrt_nm}'/>" />						<!-- Contract Title -->
					<input type="hidden" name="srch_cntrt_no" value="<c:out value='${command.srch_cntrt_no}'/>" />						<!-- Contract ID -->
					<input type="hidden" name="srch_str_org_acptday" value="<c:out value='${command.srch_str_org_acptday}'/>" />		<!-- Original Copy -->
					<input type="hidden" name="srch_end_org_acptday" value="<c:out value='${command.srch_end_org_acptday}'/>" />		<!-- Original Copy -->
					<!-- 의뢰시 필수 파라민터  -->
					<input type="hidden" name="status" id="status" value="" />
					<input type="hidden" name="tab_cnt" id="tab_cnt" value="<c:out value='${lomRq.total_cnt}'/>" /><!-- Tab 계약 증가 값 -->
					<input type="hidden" name="prev_cnsdreq_id" id ="prev_cnsdreq_id" value="<c:out value='${lomRq.prev_cnsdreq_id}'/>" />
					<input type="hidden" name="cnsdreq_id" id="cnsdreq_id" value="<c:out value='${lomRq.cnsdreq_id}'/>" />	 
					<input type="hidden" name="cntrt_id" id="cntrt_id"  value="<c:out value='${lomRq.cntrt_id}'/>" />
					<input type="hidden" name="dmstfrgn_gbn" id="dmstfrgn_gbn"  value="<c:out value='${lomRq.dmstfrgn_gbn}'/>" />
					<c:forEach var="cntrtMt" items="${listDc}">
						<input type="hidden" name="arr_cntrt_id"  value="<c:out value='${cntrtMt.cntrt_id}' />" /> <!-- 계약_마스터_계약_ID -->	
					</c:forEach>
					<input type="hidden" name="parent_cntrt_id" id="parent_cntrt_id" />
					<input type="hidden" name="submit_status" id="submit_status" />
					<!-- 첨부파일정보 -->
					<input type="hidden" name="fileInfos"   value="" /> <!-- 저장될 파일 정보 -->
					<input type="hidden" name="fileInfos1"   value="" /> <!-- 저장될 파일 정보 -->
					<input type="hidden" name="fileInfosEtc"   value="" /> <!-- 저장될 파일 정보 -->	
					<input type="hidden" name="fileInfos2"   value="" /> <!-- 저장될 파일 정보 -->	
					<input type="hidden" name="fileInfos3"   value="" /> <!-- 저장될 파일 정보 -->	
					<input type="hidden" name="fileInfos4"   value="" /> <!-- 저장될 파일 정보 -->
					<input type="hidden" name="fileInfos5"   value="" /> <!-- 저장될 파일 정보 -->	
					<input type="hidden" name="fileInfos6"   value="" /> <!-- 저장될 파일 정보 -->
					<input type="hidden" name="fileInfosOutlook"   value="" /> <!-- 저장될 파일 정보 -->
					<input type="hidden" name="fileInfoName"    value="" /> <!-- 첨부파일 정보가 리턴될 객체명 -->
					<input type="hidden" name="fileFrameName"    value="" /> <!-- 첨부파일 화면 iFrame 명 -->
					<input type="hidden" name="file_bigclsfcn"  value="" /> <!-- 대분류 -->	
					<input type="hidden" name="file_midclsfcn"  value="" /> <!-- 중분류 -->	
					<input type="hidden" name="file_smlclsfcn"  value="" /> <!-- 소분류 -->	
					<input type="hidden" name="ref_key"     value="<c:out value='${lomRq.cnsdreq_id}'/>" /> <!-- 키값 -->	
					<input type="hidden" name="view_gbn"    value="download" /> <!-- 첨부파일 출력 화면 종류 : upload, modify, download -->	
					<!-- 거래선 상대방 팝업 파라미터  -->
					<input type="hidden" name="customer_cd" id="customer_cd" value="" />
					<input type="hidden" name="dodun" id="dodun" value="" />
					<!-- Mycontract 파라미터 -->
					<input type="hidden" name="ismycontract" id="ismycontract" value="<c:out value='${command.ismycontract}'/>"/>
					<!-- Consideration_inner_d.jsp 에 있던 hidden -->
					<input type="hidden" name="master_cntrt_id" id="master_cntrt_id"  value="<c:out value='${lomRq.cntrt_id}'/>" />
			        <input type="hidden" name="file_validate" id="file_validate" value="<c:out value='${command.file_validate}'/>">
			        <input type="hidden" name="session_user_id" id="session_user_id" value="<c:out value='${command.session_user_id}'/>">
			        <input type="hidden" name="reqman_id" id="reqman_id" value="<c:out value='${lomRq.reqman_id}'/>">
			        <input type="hidden" name="depth_status" id="depth_status" value="<c:out value='${lomRq.depth_status}'/>">
					<!-- 2014-04-15 Kevin added. GERP 관련 페이지 형태 구분.(R/I) -->
					<input type="hidden" name="gerpPageType" id="gerpPageType" />
						
					<!-- Sungwoo added search parameter 2014-06-12 -->
					<input type="hidden" name="srch_division" value="<c:out value='${command.srch_division}'/>" />					<!-- Original Copy -->
					<input type="hidden" name="srch_vendor_type" value="<c:out value='${command.srch_vendor_type}'/>" />					<!-- Original Copy -->
					<input type="hidden" name="srch_vendor_type_detail" value="<c:out value='${command.srch_vendor_type_detail}'/>" />		<!-- Original Copy -->
				
					<div class="content-step t_titBtn">
						<ol>
							<li><img src="<%=IMAGE %>/common/step01.gif"	 alt="<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step01" htmlEscape="true" />" /></li>
							<li><img src="<%=IMAGE %>/common/step02.gif"     alt="<spring:message code="clm.page.msg.manage.step02" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step02" htmlEscape="true" />" /></li>
							<li class='step_on'>
								<img src="<%=IMAGE %>/common/step03_on.gif"  alt="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step03" htmlEscape="true" />" />
								<img src="<%=IMAGE %>/common/step_arr01.gif" alt="Next" title="Next" />
								<div class='step_link'><a href="javascript:open_window('win', '../../step03<%=langCd.equals("ko")?"":"_"+langCd %>.html', 0, 0, 696, 588, 0, 0, 0, 0, 0)" ><img src="<%=IMAGE %>/common/step_q.gif" alt="<spring:message code="clm.page.msg.manage.procStep"/>" title="<spring:message code="clm.page.msg.manage.procStep"/>" /></a></div>
							</li>
							<li><img src="<%=IMAGE %>/common/step04.gif"     alt="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step04" htmlEscape="true" />" /></li>
							<li><img src="<%=IMAGE %>/common/step05.gif"     alt="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" title="<spring:message code="clm.page.msg.manage.step05" htmlEscape="true" />" /></li>
						</ol>
					</div>
					<!-- title -->
					<div class="title_basic">
						<h4><spring:message code="clm.page.msg.manage.contInfo" /></h4>
					</div>
					<!-- //title -->
					<!-- button -->
					<div class="btnwrap mt_22">	
						<c:if test='${command.session_user_id == lomRq.reqman_id}'>			   
							<% if("C04211".equals(lomRq.get("prgrs_status")) && ("C02631".equals(lomRq.get("depth_status")))){ %>
								<span class="btn_all_w" onclick="forwardConsideration('mod');"><span class="check2"></span><a><spring:message code="las.page.button.modify" /></a></span><!-- 체결품의작성 -->
							<% } %>	
						</c:if>	
						<span class="btn_all_w" onclick="openPrint2();"><span class="print"></span><a><spring:message code="clm.page.msg.manage.printCover" /></a></span>	
						<span class="btn_all_w" onclick="forwardConsideration('list');"><span class="list"></span><a><spring:message code="clm.page.msg.manage.list" /></a></span>
					</div>
					<!-- //button -->
					<div class='title_basic3'><spring:message code="clm.page.msg.manage.bscInf" /></div>
		            <table class="table-style01">
		                <colgroup>
		                    <col width="12%" />
		                    <col width="22%" />
		                    <col width="12%" />
		                    <col width="21%" />
		                    <col width="12%" />
		                    <col width="21%" />
		                </colgroup>
		                <tr>
		                    <th><spring:message code="clm.page.msg.manage.reqName" /></th>
		                    <td colspan="5"><c:out value='${lomRq.req_title}' default="" /></td>
		                </tr>
		                <tr>
		                    <th><spring:message code="clm.page.msg.manage.contName" /></th>
		                    <td colspan="3"><span class="fL"><c:out value="${lomRq.cntrt_nm}" /></span></td>
		                    <th><spring:message code="clm.page.msg.manage.contId" /></th>
		                    <td><span class="fL"><c:out value="${lomRq2.cntrt_no}" /></span></td>
		                </tr>
		                <tr>
		                    <th><spring:message code="clm.page.msg.manage.reqPerson" /></th>
		                    <td>
		                        <c:out value="${lomRq2.reqman_nm}" />/
		                        <c:out value="${lomRq2.reqman_jikgup_nm}" />/
		                        <c:out value="${lomRq2.req_dept_nm}" />
		                    </td>
		                    <th><spring:message code="clm.page.msg.manage.chrgPerson" /></th>
		                    <td colspan="3">
		                        <c:out value="${lomRq2.cntrt_respman_nm}" />/
		                        <c:out value="${lomRq2.cntrt_respman_jikgup_nm}" />/
		                        <c:out value="${lomRq2.cntrt_resp_dept_nm}" />
		                    </td>
		                </tr>
		                <!-- brice 11/09 add cc -->
		                <% if(!listCa.isEmpty()){ %>
				        <tr id="deferOpnnArea" class="lineAdd">
					    <th class="borTz02"><spring:message code="clm.page.msg.manage.relPerson" /></th><!-- RELATION_MAN  relation_man -->
					    <td colspan="5"><span id="id_trgtman_nm">
					    <%for(int j=0;j<listCa.size();j++){ %>
					    <% ListOrderedMap lom = (ListOrderedMap)listCa.get(j);%>
					    <% if((j !=0 && j !=1) && (j % 2 == 0 )){%><br/><%}%>
					    <% if(j != 0 && (j % 2 !=0 )){%>,<% }%>						
					    <%=lom.get("trgtman_nm") %>/<%=lom.get("trgtman_jikgup_nm") %>/<%=lom.get("trgtman_dept_nm") %>
				    	<% }%></span>
					    </td>
			        	</tr>
				        <% }%>
		                <tr>
		                    <th><spring:message code="clm.page.msg.common.otherParty" /></th>
		                    <td><a href="javascript:customerPop2('<c:out value="${lomRq2.cntrt_oppnt_cd}" />','<c:out value="${lomRq2.cntrt_oppnt_cd}" />');"><c:out value="${lomRq2.cntrt_oppnt_nm}" /></a></td>
		                    <th><spring:message code="clm.page.field.customer.registerNo" /></th>
		                    <td colspan="3"><c:out value="${lomRq2.cntrt_oppnt_rprsntman}" /></td>
		                </tr>
		                <%  String strColspan="5";
		                if("Y".equals(lomRq2.get("plndbn_req_yn")) && "C02608".equals(lomRq2.get("depth_status"))){ //최종본의뢰여부
		                    strColspan="3";
		                    }
		                %>
		                <tr>
		                    <th><spring:message code="clm.page.msg.manage.contType" /></th>                   
		                    <td colspan="<%=strColspan%>">                  
		                        <c:out value="${lomRq.biz_clsfcn_nm}" /> / 
		                        <c:out value="${lomRq.depth_clsfcn_nm}" /> / 
		                        <c:out value="${lomRq.cnclsnpurps_bigclsfcn_nm}" /> / 
		                        <c:out value="${lomRq.cnclsnpurps_midclsfcn_nm}" /> /
		                        
		                    </td>
		                    <%  if("Y".equals(lomRq2.get("plndbn_req_yn")) && "C02608".equals(lomRq2.get("depth_status"))){ //최종본의뢰여부%>                   
		                    <th><spring:message code="clm.page.msg.manage.autoExpYn" /></th>
		                    <td><c:out value="${lomRq2.auto_rnew_yn}" /></td>                
		                    <% }%>                  
		                </tr>
		                <tr>
							<th><spring:message code="clm.page.msg.manage.top30Cus" /> <img src="<%=IMAGE %>/icon/ico_search2.gif" class="cp" onClick="javascript:openTop30Customer();" alt="search" style="padding-left:5px" title="Top 30 Customer List"/></th>
							<td ><c:out value="${lomRq2.top_30_cus}" /></td>
							<th><spring:message code="clm.page.msg.manage.relatedPrd" /></th>
							<td><c:out value="${lomRq2.related_products}" escapeXml="false"/></td>
							<th><spring:message code="clm.page.msg.manage.srcContDraft" /></th>
							<td><c:out value="${lomRq2.cont_draft}" /></td>
						</tr>
		                <tr>
		                    <th><spring:message code="clm.page.msg.manage.contItm" /></th>
		                    <td>
		                        <c:out value="${lomRq2.cntrt_trgt_nm}" />    
		                    </td>
		                    <th><spring:message code="clm.page.msg.manage.contItmDtl" /></th>
		                    <td colspan="3"><c:out value="${lomRq2.cntrt_trgt_det}" /></td>
		                </tr>
		                <tr>
		                    <th><spring:message code="clm.page.msg.manage.contPeriod" /></th>
		                    <td colspan="3">
		                        <c:out value="${lomRq2.cntrtperiod_startday}" /> ~ <c:out value="${lomRq2.cntrtperiod_endday}" />
		                    </td>
		                    <th><spring:message code="clm.page.msg.manage.sendRcvDiv" /></th>
		                    <td><c:out value="${lomRq2.payment_gbn_nm}" /></td>
		                </tr>
		                <%  strColspan="3";
		                if(!"".equals(lomRq2.get("cntrt_untprc_expl"))){ //단가로 체결 여부
		                    strColspan="0";
		                    }
		                %>
		                <tr>
		                    <th><spring:message code="clm.page.msg.manage.contAmt" /></th>
		                    <td colspan="<%=strColspan%>">
		                        <input type="text" id="cntrt_amt" name="cntrt_amt" class='text_noline w_50' value="<c:out value='${lomRq.cntrt_amt}' />" readonly="readonly"/>
		                    </td>
		                    <% if(!"".equals(lomRq2.get("cntrt_untprc_expl"))){  %>
		                    <th><spring:message code="clm.page.msg.manage.contUnitPrice" /></th>
		                    <td colspan="<%=strColspan%>">
		                        <spring:message code="clm.page.msg.manage.conclSingleAmt" />
		                    </td>
		                    <% } %>
		                    <th><spring:message code="clm.page.msg.manage.curr" /></th>
		                    <td><c:out value="${lomRq2.crrncy_unit}" /></td>
		                </tr>
		                <%if(!"".equals(lomRq2.get("cntrt_untprc_expl"))){ //단가로 체결 여부 %>
		                <tr>
		                    <th rowspan="2"><spring:message code="clm.page.msg.manage.singleAmtSum" /></th>
		                    <td colspan="5">
		                        <c:out value="${lomRq2.cntrt_untprc_expl}" escapeXml="false" />
		                    </td>
		                </tr>
		                <tr>
		                	<td colspan="5">
		                    	<iframe src="<c:url value='/las/blank.do' />" id="fileList3" name="fileList3" frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe>
		                    </td>
		                </tr>
		                <%} %>
		                <tr>
		                    <th><spring:message code="clm.page.msg.manage.bg" /></th>
		                    <td colspan="5" >                   
		                        <c:out value="${lomRq2.pshdbkgrnd_purps}"  escapeXml="false" />                                                       
		                    </td>
		                </tr>
		                <tr>
		                    <th><spring:message code="clm.page.msg.manage.etcMain" /></th>
		                    <td colspan="5" >                   
		                        <c:out value="${lomRq2.etc_main_cont}" escapeXml="false" />
		                        <c:if test="${!empty lomRq2.if_sys_cd}"> [<c:out value="${lomRq2.if_sys_cd}" />]</c:if>
		                    </td>
		                </tr>
		                <!-- 법무 회신 최종본 회신 완료시  표시 해야할 항목 임당~~ -->               
		                <c:if test='${lomRq2.cnsd_status == "C04305" && lomRq2.plndbn_req_yn == "Y"}' >
		                <c:if test="${!empty lomRq2.oblgt_lmt}">
		                <tr>
		                    <th><spring:message code="clm.page.msg.manage.limitResp" /></th>
		                    <td colspan="5"><c:out value="${lomRq2.oblgt_lmt}" /></td>
		                </tr>
		                </c:if>
		                <c:if test="${!empty lomRq2.spcl_cond}">
		                <tr>
		                    <th><spring:message code="clm.page.msg.manage.etcSpecial" /></th>
		                    <td colspan="5"><c:out value="${lomRq2.spcl_cond}" /></td>
		                </tr>
		                </c:if>
		                <tr>
		                    <th><spring:message code="clm.page.msg.manage.properLaw" /></th>
		                    <td><c:out value="${lomRq2.loac_nm}" /></td>
		                    <th><spring:message code="clm.page.msg.manage.properLawDtl" /></th>
		                    <td colspan="3"><c:out value="${lomRq2.loac_etc}" /></td>
		                </tr>
		                <tr>
		                    <th><spring:message code="clm.page.msg.manage.method" /></th>
		                    <td><c:out value="${lomRq2.dspt_resolt_mthd_nm}" /></td>
		                    <th><spring:message code="clm.page.msg.manage.methodDetail" /></th>
		                    <td colspan="3"><c:out value="${lomRq2.dspt_resolt_mthd_det}" /></td>
		                </tr>
		                </c:if>
		                <!-- 법무 회신 최종본 회신 완료시  표시 해야할 항목 임당~~ -->
		            </table>
					<!-- 2014-04-15 Kevin Added. GERP Information -->
					<iframe id="iframeGERP" name="iframeGERP" src="/clm/blank.do" style="border:0; width:100%; height:115px; margin:10px 0 10px 0;" scrolling="no"></iframe>
					
					<div class='title_basic3'><spring:message code="clm.page.msg.manage.preApprInf" /></div>
		            <table class="table-style01">
		                <colgroup>
		                    <col width="12%" />
							<col width="22%" />
							<col width="12%" />
							<col width="21%" />
							<col width="12%" />
							<col width="21%" />
		                </colgroup>
		                <tr>
		                    <th><spring:message code="clm.page.msg.manage.apprDt" /><!-- 승인일자 --></th>
		                    <td colspan='3'><c:out value='${lomRq2.bfhdcstn_apbtday}'/></td>
		                    <th><spring:message code="clm.page.msg.manage.apprType" /><!-- 승인방식 --></th>
		                    <td><c:out value="${lomRq2.BFHDCSTN_APBT_MTHD_NM}"/></td>
		                </tr>
		                <tr>
		                    <th><spring:message code="clm.page.msg.manage.proposer" /><!-- 발의자 --></th>
		                    <td colspan='3'>
		                        <c:if test="${lomRq2.bfhdcstn_mtnman_nm != ''}">
		                            <c:out value="${lomRq2.bfhdcstn_mtnman_nm}" />/ <c:out value='${lomRq2.bfhdcstn_mtnman_jikgup_nm}'/>/ <c:out value='${lomRq2.bfhdcstn_mtn_dept_nm}'/>
		                        </c:if>
		                    </td>
		                    <th><spring:message code="clm.page.msg.manage.apprPer" /><!-- 승인자 --></th>
		                    <td colspan='3'>
		                        <c:if test="${lomRq2.bfhdcstn_apbtman_nm != ''}">
		                            <c:out value='${lomRq2.bfhdcstn_apbtman_nm}'/>/ <c:out value='${lomRq2.bfhdcstn_apbtman_jikgup_nm}'/>/ <c:out value='${lomRq2.bfhdcstn_apbt_dept_nm}'/>
		                        </c:if>
		                    </td>
		                </tr>
		                
		                <tr>
		                    <th><spring:message code="clm.page.msg.manage.attachData" /></th>
		                    <td colspan="5">                  
			                    <iframe src="<c:url value='/las/blank.do' />" id="fileList4" name="fileList4" frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe>                             
		                    </td>
		                </tr>
		            </table>
		            
		             <!-- tnc include JSP -->
					<jsp:include page="/clm/manage/completion.do">
						<jsp:param name="method" value="getTncLink" />
						<jsp:param name="cntrt_id4" value='<%=lomRq2.get("cntrt_id") %>'/>
					</jsp:include>
			
					<div class='title_basic3'><spring:message code="clm.page.msg.manage.relContInf" /></div>
		            <table id="tbl_rel_contract" cellspacing="0" cellpadding="0" border="0" class="table-style01">
		                <colgroup>
		                    <col width="12%" />
		                    <col width="55%" />
		                    <col width="12%" />
		                    <col/>
		                </colgroup>
		                <tr>
		                    <th class="tC"><spring:message code="clm.page.msg.manage.relation" /></th>
		                    <th class="tC"><spring:message code="clm.page.msg.manage.relCont" /></th>
		                    <th class="tC"><spring:message code="clm.page.msg.manage.define" /></th>
		                    <th class="tC"><spring:message code="clm.page.msg.manage.relDetail" /></th>                   
		                </tr>               
		                <c:out value="${contRc}" escapeXml="false"/>
		            </table>
					<!-- 체결정보 -->
					<div class="title_basic" style='width:100%; margin:10px 0'>
						<h4><spring:message code="clm.page.msg.manage.conclInf" /></h4>
					</div>
					<div class='title_basic3'><spring:message code="clm.page.msg.manage.bscInf" /></div>
					<table class="table-style01">
						<colgroup>
							<col width="12%" />
							<col width="22%" />
							<col width="12%" />
							<col width="54%" />
						</colgroup>
						<tr>
							<th><spring:message code="clm.page.msg.manage.conclConf" /><!-- 체결확인 --></th>
							<td>
							<c:choose>
							<c:when test="${lomRq.cntrt_cnclsn_yn=='Y'}">Yes</c:when>
							<c:otherwise>No</c:otherwise>
							</c:choose>	
							</td>
							<th><spring:message code="clm.page.msg.manage.contConclDt" /><!-- 계약체결일 --></th>
							<td><c:out value='${lomRq.cntrt_cnclsnday}'/></td>
						</tr>
						<tr>
							<th><spring:message code="clm.page.msg.manage.sign" /><!-- 날인/서명 구분 --></th>
							<td><c:out value='${lomRq.seal_mthd_nm}'/></td>
							<c:choose>
							<c:when test="${lomRq.seal_mthd eq 'C02102'}">
							<th><spring:message code="clm.page.msg.manage.signChrgMan" /></th>
							<td><c:out value='${lomRq.signman_nm}'/>/<c:out value='${lomRq.signman_jikgup_nm}'/>/<c:out value='${lomRq.sign_dept_nm}'/></td>
							</c:when>
							<c:otherwise>
							<th><spring:message code="clm.page.msg.manage.signManager" /></th>
							<td><c:out value='${lomRq.seal_ffmtman_nm}'/>/<c:out value='${lomRq.seal_ffmtman_jikgup_nm}'/>/<c:out value='${lomRq.seal_ffmt_dept_nm}'/></td>
							</c:otherwise>
							</c:choose>	
						</tr>
					</table>
					<div class='title_basic3'><spring:message code="clm.page.msg.manage.copyVerInf" /></div>
					<table class="table-style01">
						<colgroup>
							<col width="12%" />
							<col width="22%" />
							<col width="12%" />
							<col width="54%" />
						</colgroup>
						<tr>
							<th><spring:message code="clm.page.msg.manage.copyVerRegPer" /><!-- 사본등록자 --></th>
							<td>
							<c:if test="${lomRq.cpy_regman_nm ne '' }" >
							<c:out value='${lomRq.cpy_regman_nm}'/>/<c:out value='${lomRq.cpy_regman_jikgup_nm}'/>/<c:out value='${lomRq.cpy_reg_dept_nm}'/>
							</c:if>
							</td>
							<th><spring:message code="clm.page.msg.manage.copyVerRegDate" /></th>
							<td><c:out value='${lomRq.cpy_regday}'/></td>
						</tr>
						<tr>
							<th><spring:message code="clm.page.msg.manage.conclCopy" /></th>
							<td colspan="3"><iframe id="fileList6" name="fileList6" frameborder="0" width="100%" height="50px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe></td>
						</tr>
						<tr>
							<th><spring:message code="clm.page.msg.common.outlook" /></th>
							<td colspan="3"><iframe id="fileListOutlook" name="fileListOutlook" frameborder="0" width="100%" height="50px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe></td>
						</tr>
					</table>
			<!-- 15개 항목 상세 -->
			<jsp:include page="/clm/review/considerationHQ.do">
				<jsp:param name="method" value="getCheckList" />
				<jsp:param name="cntrt_id3" value='<%=lomRq.get("cntrt_id") %>'/>
			</jsp:include>
			
			<div id="contractHis-list"></div>
			
					<div class="t_titBtn">
						<div class="btn_wrap_c">
							<c:if test='${command.session_user_id == lomRq.reqman_id}'>			   
							<% if("C04211".equals(lomRq.get("prgrs_status")) && ("C02631".equals(lomRq.get("depth_status")))){ %>
								<span class="btn_all_w" onclick="forwardConsideration('mod');"><span class="check2"></span><a><spring:message code="las.page.button.modify" /></a></span>
							<% } %>	
							</c:if>	        
							<span class="btn_all_w" onclick="openPrint2();" ><span class="print"></span><a><spring:message code="clm.page.msg.manage.printCover" /></a></span>  		
							<span class="btn_all_w" onclick="forwardConsideration('list');"><span class="list"></span><a><spring:message code="clm.page.msg.manage.list" /></a></span>
						</div>			
					</div>
					<!-- //button -->	
				</form:form>
			</div>
		<!-- //content_in -->	
		</div>
	</div>
	<!-- //content -->	
</div>
	<!-- //container -->
		<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- // footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</body>
</html>


