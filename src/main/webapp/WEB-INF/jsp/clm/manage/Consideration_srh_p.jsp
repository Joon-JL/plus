<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.manage.dto.ConsiderationForm"%>
<%@ page import="com.sds.secframework.common.util.DateUtil"%>
<%@ page import="com.sds.secframework.common.util.StringUtil"%>

<%@ page import="java.util.List"%>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap"%>

<%--
/**
 * 파  일  명 : Consideration_srh_p.jsp
 * 프로그램명 : 상세 검색 팝업 
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2013.04
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="clm.main.title" /></title>

<link href="<%=CSS%>/popup.css" type="text/css" rel="stylesheet"></link>
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet"></link>

	<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
	<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
	<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
	<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
	<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
	<script language="javascript" src="<c:url value='/script/clms/menu.js'/>"></script> 
	<script language="javascript" src="<c:url value='/script/secfw/jquery/ui.core.js' />"></script>
	<script language="javascript" src="<c:url value='/script/clms/jquery-1.6.1.min.js' />" type="text/javascript"></script>
	<script language="javascript" src="<c:url value='/script/clms/new_style.js' />"	type="text/javascript"></script>
	<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
	<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
	<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
	
	<script language="javascript">
	
	// 화면을 만들기 위해서 onload보다 먼저 실행이 된다.
	$(document).ready(function(){
		
		//listType_p 호출
		forwardTypePopup();
		
		//지불 /수금
		getCodeSelectByUpCd("frm", "payment_gbu", '/common/clmsCom.do?method=checkBoxComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=C020&combo_name=srch_lPayment_gbn&combo_del_yn=N&combo_checked=');
        
		getCodeSelectByUpCd("frm", "lseal_mthd", '/common/clmsCom.do?method=checkBoxComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=C021&combo_name=srch_lseal_mthd&combo_del_yn=N&combo_checked=');
		
		// 준거법
		getCodeSelectByUpCd("frm", "lLoac", '/common/clmsCom.do?method=checkBoxComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=C022&combo_name=srch_lLoac&combo_del_yn=N&combo_checked=');
		
		// 분쟁해결 방법
		getCodeSelectByUpCd("frm", "lDesp_resolt_mthd", '/common/clmsCom.do?method=checkBoxComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=C023&combo_name=srch_lDesp_resolt_mthd&combo_del_yn=N&combo_checked=');
		
		//initCal("str_org_acptday");   //첫번재 의뢰일 설정
	    //initCal("end_org_acptday");     //두번재 의뢰일 설정
		
	});
	
	// 상세 검색 보내기
	function fDetailSearch(){
		
		var frm = document.frm;
		var srhInfo = new Object();
		
		var srch_lType_cd           = "";
		var srch_mType_cd           = "";
		var srch_sType_cd           = "";
		var srch_sPayment_gbn       = "";
		var srch_sAuto_rnew_yn      = "";
		var srch_sMn_cnsd_dept      = "";
		var srch_sSeal_mthd         = "";
		var srch_sLoac              = "";
		var srch_sDesp              = "";

		srhInfo.srch_cntrt_trgt_det2      = frm.srch_cntrt_trgt_det2.value;
		srhInfo.srch_bfhdcstn_apbtman_nm  = frm.srch_bfhdcstn_apbtman_nm.value;
		//srhInfo.srch_str_org_acptday      = frm.srch_str_org_acptday.value;
		//srhInfo.srch_end_org_acptday      = frm.srch_end_org_acptday.value;
		srhInfo.srch_signman_nm           = frm.srch_signman_nm.value;
		
		// 대분류
		for(var i=0; i<eval(frm.srch_lsrch_Ltype_cd.length);i++){
			if(frm.srch_lsrch_Ltype_cd[i].checked == true){
				srch_lType_cd +=  ',' + frm.srch_lsrch_Ltype_cd[i].value ;
			}
		}
		
		// 중분류
		for(i=0; i<eval(frm.srch_lsrch_Mtype_cd.length);i++){
			if(frm.srch_lsrch_Mtype_cd[i].checked == true){
				srch_mType_cd +=  ',' + frm.srch_lsrch_Mtype_cd[i].value ;
				
			}
		}
		
		// 소분류
		for(i=0; i<eval(frm.srch_lsrch_Stype_cd.length);i++){
			if(frm.srch_lsrch_Stype_cd[i].checked == true){
				srch_sType_cd +=  ',' + frm.srch_lsrch_Stype_cd[i].value ;
				
			}
		}
		
		// 지불/수금 구분
		for(i=0; i<eval(frm.srch_lPayment_gbn.length);i++){
			if(frm.srch_lPayment_gbn[i].checked == true){
				srch_sPayment_gbn +=  ',' + frm.srch_lPayment_gbn[i].value ;
				
			}
		}
		
		// 계약 부서(계약 Ower_ship)  법무 LITE 버젼은 검색 조건에 빠짐.
		/*
		for(i=0; i<eval(frm.srch_mn_cnsd_dept.length);i++){
			if(frm.srch_mn_cnsd_dept[i].checked == true){
				srch_sMn_cnsd_dept +=  ',' + frm.srch_mn_cnsd_dept[i].value ;
				
			}
		}
		*/
		
		// 자동 갱신 여부
		for(i=0; i<eval(frm.srch_auto_rnew_yn.length);i++){
			if(frm.srch_auto_rnew_yn[i].checked == true){
				srch_sAuto_rnew_yn +=  ',' + frm.srch_auto_rnew_yn[i].value ;
				
			}
		}
		
		// 날인 방식
		for(i=0; i<eval(frm.srch_lseal_mthd.length);i++){
			if(frm.srch_lseal_mthd[i].checked == true){
				srch_sSeal_mthd +=  ',' + frm.srch_lseal_mthd[i].value ;
				
			}
		}
		
		// 준거법
		for(i=0; i<eval(frm.srch_lLoac.length);i++){
			if(frm.srch_lLoac[i].checked == true){
				srch_sLoac +=  ',' + frm.srch_lLoac[i].value ;
				
			}
		}
		
		// 준거법
		for(i=0; i<eval(frm.srch_lDesp_resolt_mthd.length);i++){
			if(frm.srch_lDesp_resolt_mthd[i].checked == true){
				srch_sDesp +=  ',' + frm.srch_lDesp_resolt_mthd[i].value ;
				
			}
		}
		
		//alert("sPayment_gbn = "+sPayment_gbn);
		
		srhInfo.srch_lSrch_LType_cd       = srch_lType_cd;    
		srhInfo.srch_lSrch_MType_cd       = srch_mType_cd;
		srhInfo.srch_lSrch_SType_cd       = srch_sType_cd;
		srhInfo.srch_lPayment_gbn         = srch_sPayment_gbn;
		srhInfo.srch_auto_rnew_yn         = srch_sAuto_rnew_yn;
		srhInfo.srch_mn_cnsd_dept         = srch_sMn_cnsd_dept;
		srhInfo.srch_lseal_mthd           = srch_sSeal_mthd;
		srhInfo.srch_lLoac                = srch_sLoac;
		srhInfo.srch_lDesp_resolt_mthd    = srch_sDesp;

		
		opener.returnDetailSrh(srhInfo);   // 검색 되는 값을 담아서 부모창으로 한번에 던저 준데요~~
		window.close();
		
	}
	
	//유형
	function forwardTypePopup() {

		var options = {
			url: "<c:url value='/clm/manage/myContract.do?method=forwardTypePopup' />",
			type: "post",
			dataType: "html",
			success: function (data) {
				
				// 유형 가지고 오는 것
				$("#inner_heigh_scroll").html("");    // 초기화
				$("#inner_heigh_scroll").html(data);  // 값의 세팅
			}
		};
		$("#frm").ajaxSubmit(options);
	}
		
	</script>
</head>

<body class="pop">

	<!-- header -->
	<h1><spring:message code="clm.page.field.manageCommon.dsrh" /></h1>
	<!-- //header -->
	<!-- body -->
	
		<div class="pop_area">
			<div class="pop_content"  style='height:'>
			
					<form:form name="frm" id="frm" method="post" autocomplete="off">
						<!-- 페이지 기본정보 -->
						<input type="hidden" name="method"       value="" />
						<input type="hidden" name="menu_id"      value="<c:out value='${command.menu_id}'/>" />
					
					<!-- title -->
					<div class="title_basic mt0">
						<h4><spring:message code="clm.page.field.manageCommon.dsrh" /></h4>
					</div>
					<!-- //title -->
					<!-- form -->
					
					<!-- //listType_p -->
					<div id="inner_heigh_scroll"></div>
					<!-- //listType_p -->
					
					<table class="table-style01 mt20">
						<colgroup>
							<col width="150px" />
							<col />
						</colgroup>
						<tbody>
							<tr>
								<th><spring:message code="clm.page.msg.manage.contTargetDtl" /></th>
								<td>
								  <input name="srch_cntrt_trgt_det2" type="text" value="" style="width: 200px" class="text_full" />
								</td>
							</tr>
							<tr>
								<th><spring:message code="clm.page.msg.manage.sendRecvDiv" /></th>
								<td id ="payment_gbu">
								</td>
							</tr>
							<tr>
								<th><spring:message code="clm.page.msg.manage.preApprPer" /></th>
								<td>
								    <input name="srch_bfhdcstn_apbtman_nm" type="text" value="" style="width: 200px" class="text_full" />
								</td>
							</tr>
							<!--법무LITE 버젼은 계약 Ownership이 없습니다.
							<tr>
								<th><spring:message code="clm.page.msg.manage.contOwnership" /></th>
								<td id ="srch_mn_cnsd_dept">
								    <input name="srch_mn_cnsd_dept" type="checkbox" value="A00000001" class="pL10" /> <spring:message code="clm.page.msg.common.legalDep" /> 
								    <input name="srch_mn_cnsd_dept" type="checkbox" value="A00000002" class="pL10" /> <spring:message code="clm.page.msg.manage.ovrseaLegal" />
								    <input name="srch_mn_cnsd_dept" type="checkbox" value="A00000003" class="pL10" /> <spring:message code="clm.page.msg.rule.ipCenter" />
								</td>
							</tr>
							 -->
							<!-- 
							<tr>
								<th><spring:message code="clm.page.msg.manage.conclContConf" /></th>
								<td> 
								    <input type="text" name="srch_str_org_acptday" id="str_org_acptday" value="" class="text_calendar02"/> ~ 
								    <input type="text" name="srch_end_org_acptday" id="end_org_acptday" value="" class="text_calendar02"/>
								</td>
							</tr>
							 -->
							<tr>
								<th><spring:message code="clm.page.msg.manage.autoExpYn" /></th>
								<td>
								    <input name="srch_auto_rnew_yn" type="checkbox" value="Y" class="pL10" /> Yes 
								    <input name="srch_auto_rnew_yn" type="checkbox" value="N" class="pL10" /> No
								</td>
							</tr>
							<tr>
								<th><spring:message code="clm.page.msg.manage.signType" /></th>
								<td id="lseal_mthd"></td>
							</tr>
							<!--  일단 주석 
							<tr>
								<th>체결 품의 승인자</th>
								<td>
								    <input name="" type="text" value="" style="width: 200px" class="text_full" />
								</td>
							</tr>
							-->
							<tr>
								<th><spring:message code="clm.page.msg.manage.signPerInf" /></th>
								<td>
								    <input type="text" name="srch_signman_nm" value="" style="width: 200px" class="text_full" />
								</td>
							</tr>
							<tr>
								<th><spring:message code="clm.page.msg.manage.properLaw" /></th>
								<td id="lLoac"></td>
							</tr>
							<tr>
								<th><spring:message code="clm.page.msg.manage.method" /></th>
								<td id="lDesp_resolt_mthd"></td>
							</tr>
						</tbody>
					</table>
					
					<!-- //form -->

</form:form>
				</div>
			</div>
		
	<!-- //body -->
	<!--footer -->
	<div class="pop_footer">
	        <!-- button -->
	        <div class="btn_wrap fR">
	            <span class="btn_all_w"><span class="view"></span><a href="javascript:fDetailSearch();"><spring:message code="clm.page.msg.common.search" /></a></span>
	            <span class="btn_all_w"><span class="cancel"></span><a href="javascript:self.close();"><spring:message code='clm.page.button.cancel' /></a></span>
	        </div>
             <!-- //button -->
         </div>
	<!-- //footer -->



</body>
</html>
