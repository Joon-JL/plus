<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@page import="com.sds.secframework.common.util.PageUtil"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%@page import="com.sds.secframework.common.util.Token"%>
<%--
/**
 * 파  일  명 : LawStaConsultGrpmgr_d.jsp
 * 프로그램명 : 표준계약서 상세 (일반 페이지)
 * 설      명 : 표준계약서의 상세만 보여주게 됩니다.
 * 작  성  자 : 
 * 작  성  일 : 2013.04
 */
--%>
<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->


<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/ui.core.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>

<script language="javascript">
	$(document).ready(function(){
		initPage();
	});


	function initPage(){
		
		//임시저장, 의뢰, 재의뢰 시 
		if(frm.prgrs_status.value == 'S01' || frm.prgrs_status.value == 'S02' || frm.prgrs_status.value == 'S04'){
		frm.target			= "fileList";
	    frm.action = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value  	= "forwardClmsFilePage";
		frm.fileInfoName.value = "fileInfos";
        frm.fileFrameName.value = "fileList";
        getClmsFilePageCheck('frm');
        //frm.submit();	
		}
        //회신 시
        if(frm.prgrs_status.value == 'S03'){
        frm.target			= "fileList";
        frm.file_midclsfcn.value = "F00301";
	    frm.action = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value  	= "forwardClmsFilePage";
		frm.fileInfoName.value = "fileInfos";
        frm.fileFrameName.value = "fileList";
        getClmsFilePageCheck('frm');
        //frm.submit();	
        
       	if(frm.extnl_cnsltyn.value == 'Y'){
	        frm.target			= "fileList3";
	        frm.file_midclsfcn.value = "F00302";
		    frm.action = "<c:url value='/clms/common/clmsfile.do' />";
			frm.method.value  	= "forwardClmsFilePage";
			frm.fileInfoName.value = "fileInfos3";
	        frm.fileFrameName.value = "fileList3";
	        getClmsFilePageCheck('frm');
	        //frm.submit();
	        }
        }
	}
	
	function init(){
		
		alertMessage('<c:out value='${command.return_message}'/>') ;
	}
	
	
	function remove(){
		if(confirm("<spring:message code="secfw.msg.ask.delete" />")) {
			viewHiddenProgress(true) ;
			var f = document.frm ;
			f.method.value = "deleteStaLawConsult" ;
			f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
			f.target = "_self" ;
			f.submit() ;
		}
	}
	
	function goModifyForm() {
		viewHiddenProgress(true) ;
		var f = document.frm ;
		f.method.value = "forwardModifyStaLawConsult" ;
		f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
		f.target = "_self" ;
		f.submit() ; 
	}
	
	function goHoldForm() {
		viewHiddenProgress(true) ;
		var f = document.frm ;
		f.check_prgrs_status.value = 'hold';
		f.method.value = "forwardHoldStaLawConsult" ;
		f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
		f.target = "_self" ;
		f.submit() ; 
	}

	function send(){
		var f = document.frm;
		var confirmMessage = "<spring:message code='secfw.msg.ask.send' />";
		if(confirm(confirmMessage)){
			//hstry_no 가 0 보다 크면 재의뢰
			if(f.hstry_no.value > 0)
				f.check_prgrs_status.value = "resend";
			//hstry_no = 0 이면 의뢰
			else
				f.check_prgrs_status.value = "send";
			
			f.method.value = "modifyStatusStaLawConsult" ;
			f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
			f.target = "_self" ;
			f.submit();
		}
	}
	
	function goList() {
		viewHiddenProgress(true) ;
		var f = document.frm ;
		f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
		f.method.value = "listStaLawConsultRequest" ;
		f.target = "_self" ;
		f.submit() ;
	}
	
// 	function goPrint() {
// 		viewHiddenProgress(true) ;
// 		var f = document.frm ;
// 		f.method.value = "forwardPrintLawConsult" ;
// 		f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
// 		PopUpWindowOpen('', 800, 500, true);
// 		frm.target = "PopUpWindow";
// 		f.submit() ;
// 		viewHiddenProgress(false) ;
// 	}
	
	function goPrint() {
        viewHiddenProgress(true) ;
        var f = document.frm ;
        f.method.value = "forwardPrintDetailLawConsult" ;
        f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
        PopUpWindowOpen('', 800, 500, true);
        frm.target = "PopUpWindow";
        f.submit() ;
        viewHiddenProgress(false) ;
    }
	
	
	
	function goDetail(cnslt_no, hstry_no) {
		viewHiddenProgress(true) ;
		var f = document.frm ;
		f.cnslt_no.value = cnslt_no;
		f.hstry_no.value = hstry_no;
		f.method.value = "forwardDetailStaLawConsultReqman" ;
		f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
		f.target = "_self" ;
		f.submit() ; 
	}
	
	function goInsertForm() {
		viewHiddenProgress(true) ;
		var f = document.frm ;
		f.check_prgrs_status.value = "resend";
		f.method.value = "forwardInsertStaLawConsult" ;
		f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
		f.target = "_self" ;
		f.submit() ; 
	}
	
</script>
</head>

<body onload="init()" autocomplete="off">

<div id="wrap">
<!-- container -->
<div id="container">	
	<!-- Location -->
        <div class="location">
            <img src="<%=IMAGE%>/icon/ico_home.gif" border="0" alt="Home"/><c:out value='${menuNavi}' escapeXml="false"/>
        </div>
    <!-- //Location -->
 
 	<div class="title">
			<h3><spring:message code="las.page.field.lawconsulting.stndFormReq"/></h3>
	</div>
	
	<!-- content -->
	<div id="content">
	<div id="content_in">
	
	<form:form name="frm" id="frm" autocomplete="off">
	<input type="hidden" name="method" value=""/>
	<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>"/>
	<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>"/>
	<input type="hidden" name="isForeign" value="<c:out value='${command.isForeign}'/>"/>
	<input type="hidden" name="cnslt_no" value="<c:out value='${command.cnslt_no}'/>"/>
	<input type="hidden" name="hstry_no" value="<c:out value='${command.hstry_no}'/>"/>
	<input type="hidden" name="consult_type" value="<c:out value='${lom.consult_type}'/>"/>
	<input type="hidden" name="prgrs_status" value="<c:out value='${lom.prgrs_status}'/>"/>
	<input type="hidden" name="check_prgrs_status" value=""/>
	<input type="hidden" name="extnl_cnsltyn" value="<c:out value='${lom.extnl_cnsltyn}'/>"/>
	
	<!-- 검색 정보 -->
	<input type="hidden" name="srch_start_ymd" value="<c:out value='${command.srch_start_ymd}'/>"/>
	<input type="hidden" name="srch_end_ymd" value="<c:out value='${command.srch_end_ymd}'/>"/>
	<input type="hidden" name="srch_prgrs_status" value="<c:out value='${command.srch_prgrs_status}'/>"/>
	<input type="hidden" name="srch_title" value="<c:out value='${command.srch_title}'/>"/>
	<input type="hidden" name="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}'/>"/>
	
	<!-- 작성완료 후 페이지 포워딩 구분을 위한 페이지 구분정보 -->
	<input type="hidden" name="fwd_gbn" value="req"/>
	
	<!-- 첨부파일정보 -->
	<input type="hidden" name="fileInfos" 	value="" />
	<input type="hidden" name="fileInfos2" 	value="" />
	<input type="hidden" name="fileInfos3" 	value="" />
	<input type="hidden" name="file_bigclsfcn" 	value="F003" />
	<input type="hidden" name="file_midclsfcn" 	value="F00301" />
	<input type="hidden" name="file_smlclsfcn" 	value="" />
	<input type="hidden" name="ref_key" 	value="<c:out value='${command.cnslt_no}'/>@<c:out value='${command.hstry_no}'/>"/>
	<input type="hidden" name="view_gbn" 	value="download" />
	<input type="hidden" name="fileInfoName"    value="" /> <!-- 첨부파일 정보가 리턴될 객체명 -->
	<input type="hidden" name="fileFrameName"    value="" /> <!-- 첨부파일 화면 iFrame 명 -->
	
	<!-- 이중등록 방지 -->
	<input type="hidden" name="TOKEN"  value="<%=Token.set(request) %>" />
	
	<!-- title -->
	<div class="t_titBtn">
		
		<div class="btn_wrap">
			<c:if test="${lom.prgrs_status == 'S01'}">
			<span class="btn_all_w"><span class="edit"></span><a href="javascript:send()"><spring:message code="las.page.field.lawconsulting.stndFormReq"/></a></span>
			<span class="btn_all_w"><span class="modify"></span><a href="javascript:goModifyForm()"><spring:message code="las.page.button.lawconsult.modify" /></a></span>
			<span class="btn_all_w"><span class="delete"></span><a href="javascript:remove()"><spring:message code="las.page.button.lawconsult.delete" /></a></span>
			</c:if>
			<c:if test="${lom.prgrs_status == 'S02' || lom.prgrs_status == 'S04'}">
				<c:if test="${command.hstry_no == maxHstryNo}">
				<span class="btn_all_w"><span class="modify"></span><a href="javascript:goHoldForm()"><spring:message code="las.page.button.lawconsult.hold" /></a></span>
				</c:if>
			</c:if>
			<c:if test="${lom.prgrs_status == 'S03' || lom.prgrs_status == 'S05'}">
				<c:if test="${command.hstry_no == maxHstryNo}">
					<span class="btn_all_w"><span class="edit"></span><a href="javascript:goInsertForm();"><spring:message code="las.page.button.lawconsult.resend" /></a></span>
				</c:if>
			</c:if>
			<span class="btn_all_w"><span class="print"></span><a href="javascript:goPrint()"><spring:message code="las.page.button.lawconsult.print" /></a></span>
			<span class="btn_all_w"><span class="list"></span><a href="javascript:goList()"><spring:message code="las.page.button.lawconsult.list" /></a></span>
			
		</div>
	</div>
	<!-- //title -->
 
		<!-- view -->
		<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
			<colgroup>
				<col width="15%"/>
          		<col width="35%"/>
          		<col width="15%"/>
          		<col width="35%"/>
			</colgroup>
			<tbody>
				
				<c:choose>
				<c:when test="${lom.prgrs_status == 'S05'}">
				<tr>
					<th><spring:message code="las.page.field.lawconsult.prgrs_status" /></th>
					<td colspan="3">
						<c:out value='${lom.prgrs_status_name}'/>
					</td> 
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawconsult.holdcont" /></th>
					<td colspan="3">
						<c:out value='${lom.hold_cause}' escapeXml="false"/>	
					</td>
				</tr>
				</c:when>
				
				
				<c:when test="${lom.prgrs_status == 'S07'}">
				<tr>
					<th><spring:message code="las.page.field.lawconsult.prgrs_status" /></th>
					<td colspan="3">
						<c:out value='${lom.prgrs_status_name}'/>
					</td> 
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawconsulting.rejectBy"/></th>
					<td colspan="3">
						<c:out value='${lom.mod_nm}' escapeXml="false"/>	
					</td>
				</tr>					
				<tr>
					<th><spring:message code="las.page.field.lawconsult.rejctcont" /></th>
					<td colspan="3">
						<c:out value='${lom.rejct_cause}' escapeXml="false"/>	
					</td>
				</tr>
				</c:when>
				<c:otherwise>
				<tr>
					<th><spring:message code="las.page.field.lawconsult.title" /></th>
					<td colspan="3">
						<c:out value='${lom.title}' escapeXml='false'/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.speakconsult.reqman_nm" /></th>
					<td><c:out value='${lom.reg_nm}'/>/<c:out value='${lom.regman_jikgup_nm}'/></td>
					<th><spring:message code="las.page.field.lawconsult.department" /></th>
					<td><c:out value='${lom.reg_dept_nm}'/></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawconsult.respman_nm" /></th>
					<td colspan=3><c:out value='${lom.respman_nm}'/></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawconsult.reg_dt" /></th>
					<td><c:out value='${lom.reg_dt}'/></td>
					<th><spring:message code="las.page.field.lawconsult.prgrs_status" /></th>
					<td><c:out value='${lom.prgrs_status_name}'/></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.mainproject.mainmatr_type" /></th>
					<td colspan=3><c:out value='${lom.consult_type_name}'/></td>
				</tr>
				
				
				<c:choose>
					<c:when test="${lom.prgrs_status == 'S03'}">
					<tr>
					<th>
						<c:if test="${lom.dmstfrgn_gbn == 'H'}">
							<spring:message code="las.page.field.lawconsult.lasopnn" />
						</c:if>
						<c:if test="${lom.dmstfrgn_gbn == 'F'}">
							<spring:message code="las.page.field.lawconsulting.lgAbGrCmt"/>
						</c:if>
						<c:if test="${lom.dmstfrgn_gbn == 'IP'}">
							<spring:message code="las.page.field.lawconsulting.ipCntOpi"/>
						</c:if>
					</th>
					<td colspan="3"><c:out value='${lom.cnsd_opnn}' escapeXml="false"/></td>
					</tr>
					
					<c:if test="${lom.grpmgr_re_yn == 'Y'}">
					<tr>
					<th><spring:message code="las.page.field.lawconsult.apbt_opnn" /></th>
					<td colspan="3"><c:out value='${lom.apbt_opnn}' escapeXml="false"/></td>
					</tr>
					</c:if>
					
					<tr>
					<th><spring:message code="las.page.field.lawconsult.las_attachfile" /></th>
					<td colspan="3">	
						<!-- Sungwoo Replaced scroll option 2014/08/04 -->
						<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe>
					</td>
					</tr>
					</c:when>
					
					<c:otherwise>
					<tr>
						<th><spring:message code="clm.page.msg.common.content" /></th>
						<td colspan=3><c:out value='${lom.cont}' escapeXml="false"/></td>
					</tr>
					<tr>
						<th><spring:message code="las.page.field.lawconsult.attachfile" /></th>
						<td colspan="3">
							<!-- Sungwoo Replaced scroll option 2014/08/04 -->
							<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe>
						</td>
					</tr>
					</c:otherwise>
				</c:choose>
				
				
				<c:if test="${lom.prgrs_status == 'S03'}">
				<c:if test="${lom.extnl_cnsltyn == 'Y'}">
				<tr>
					<th><spring:message code="las.page.field.lawconsult.extnlconsult" /></th>
					<td colspan="3">
						<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
							<colgroup>
							<col width="50%" />
							<col width="25%" />
							<col width="25%" />
							</colgroup>
							 <thead>
						     <tr>
						      <th><spring:message code="las.page.field.lawconsult.comp_nm" /></th>
						      <th><spring:message code="las.page.field.lawconsult.cnslt_amt" /></th>
						      <th><spring:message code="las.page.field.lawconsult.crrncy_unit" /></th>
							 </tr>
					         </thead>
					         <tbody>
					         	<c:forEach var="list" items="${extnlcompList}" varStatus="cnt">
									<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
										<td class="tL">								    
									   		<c:out value='${list.comp_nm}' escapeXml="false"/>
									    </td>
										<!-- <td><c:out value='${list.cnslt_amt}'/></td> -->
										<td><fmt:formatNumber value="${list.cnslt_amt}" pattern="#,###"/></td>
										<td><c:out value='${list.crrncy_unit}'/></td>
									</tr>
								</c:forEach>
					         </tbody>
						</table>	
					</td>
					<tr>
					<th><spring:message code="las.page.field.lawconsult.extnl_attachfile" /></th>
					<td colspan="3">
						<!-- Sungwoo Replaced scroll option 2014/08/04 -->
						<iframe src="<c:url value='/las/blank.do' />" id="fileList3" name="fileList3" frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe>
					</td>
					</tr>
				</tr>
				</c:if>
				</c:if>
				</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		
		<div class="title_basic">
				<h4><spring:message code="las.page.field.lawconsult.history" /></h4>
		</div>

		<!-- list -->
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
			 <colgroup>
			   <col width="50%" />
			   <col width="21%" />
			   <col width="21%" />
			   <col width="8%" />
		     </colgroup>
			  <thead>
			    <tr>
			      <th><spring:message code="las.page.field.lawconsult.title" /></th>
			      <th><spring:message code="las.page.field.speakconsult.reqman_nm" />/<spring:message code="las.page.field.lawconsult.cnsdman_nm" /></th>
			      <th><spring:message code="las.page.field.lawconsult.respman_nm" /></th>
			      <th><spring:message code="las.page.field.lawconsult.dt" /></th>
				</tr>
		      </thead>
			  <tbody>
				<c:forEach var="list" items="${historyList}" varStatus="cnt">
					<tr onmouseout="this.className='';" onmouseover="this.className='selected'">
						<td class="tL">
						<c:choose>
							<c:when test="${list.cnslt_pos == 0 }">
								
							</c:when>
							<c:otherwise>
								
								<c:forEach begin="1" end="${list.cnslt_pos}">
					    			&nbsp;
						   		 </c:forEach>
						   		 <img src="<%=IMAGE%>/icon/icon_reply.gif" alt="" />
							</c:otherwise>
						</c:choose>
						
					    <a href="javascript:goDetail('<c:out value="${list.cnslt_no}"/>','<c:out value="${list.hstry_no}"/>')">
					    
					    <c:if test="${command.hstry_no == list.hstry_no}"><b></c:if>    
					    [<c:out value='${list.prgrs_status_name}'/>] <c:out value='${list.title}' escapeXml="false"/></a>
					    <c:if test="${command.hstry_no == list.hstry_no}"></b></c:if>
					    </td>
						<td class="tC"><c:out value='${list.reg_nm}'/></td>
						<td class="tC"><c:out value='${list.respman_nm}'/></td>
						<td class="tC"><c:out value='${list.reg_dt_date}'/></td>
					</tr>
				</c:forEach>
		      </tbody>
		  </table>
		  <!-- //list -->
		  <!-- //view -->
	</form:form>		
	</div>
	</div>
	<!-- //content -->
	
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</div>
<!-- //container -->
</div>
<jsp:include page="/WEB-INF/jsp/secfw/common/DlgAlert.jsp"/>
<!-- footer  -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
	<!-- // footer -->
</body>
</html>
