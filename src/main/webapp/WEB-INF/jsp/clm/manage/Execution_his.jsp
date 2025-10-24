<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%-- 
/**
 * 파  일  명 : Execution_his.jsp
 * 프로그램명 : 이행상세 - 이력정보 조회 목록
 * 설      명 : 
 * 작  성  자 : 박정훈
 * 작  성  일 : 2013.05
 */
--%>
<%
	ArrayList authReqList = (ArrayList)request.getAttribute("authReqList");  // 관련자 정보
	ListOrderedMap contractMstLom = (ListOrderedMap)request.getAttribute("contractMstLom");
%>
<div class="title_basic">
  <h4><spring:message code="clm.page.msg.manage.conclInf" /><img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'contract-cnclsnInfo-view');"/></h4>
</div>

<!-- 체결품의정보시작 : default : display:none -->
<div id="contract-cnclsnInfo-view">
<div class="title_basic3"><h3><spring:message code="clm.page.msg.manage.bscInf" /></h3></div>
<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
	<colgroup>
		<col width="14%" />
		<col width="25%" />
		<col width="14%" />
		<col width="19%" />
		<col width="14%" />
		<col width="14%" />
	</colgroup>
	<tr>
		<th><spring:message code="clm.page.msg.manage.conclYn" /></th>
		<td>
			<c:choose>
			<c:when test="${contractMstLom.cntrt_cnclsn_yn=='Y'}">Yes</c:when>
			<c:otherwise>No</c:otherwise>
			</c:choose>	
		</td>
		<th><spring:message code="clm.page.msg.manage.conclResDate" /></th>
	  	<td><c:out value='${contractMstLom.cnclsn_plndday}'/></td>
	  	
	  	<th><spring:message code="clm.page.msg.manage.contConclDt" /></th>
		<td><c:out value='${contractMstLom.cntrt_cnclsnday}'/></td>
	</tr>
	
	<tr>
		<th><spring:message code="clm.page.msg.manage.signType" /></th>
		<td><c:out value='${contractMstLom.seal_mthd_nm}'/></td>
		
		<c:choose>
		<c:when test="${contractMstLom.seal_mthd=='C02101'}">
			<th><spring:message code="clm.page.msg.manage.signManager" /></th>
		  	<td colspan=3><c:out value='${contractMstLom.seal_ffmtman_nm}'/>/<c:out value='${contractMstLom.seal_ffmtman_jikgup_nm}'/>/<c:out value='${contractMstLom.seal_ffmt_dept_nm}'/></td>
	  	</c:when>
	  	<c:otherwise>
		  	<th><spring:message code="clm.page.msg.manage.signRes" /></th>
		  	<td colspan=3><c:out value='${contractMstLom.sign_plndman_nm}'/>/<c:out value='${contractMstLom.sign_plndman_jikgup_nm}'/>/<c:out value='${contractMstLom.sign_plnd_dept_nm}'/></td>
	  	</c:otherwise>
		</c:choose>
	</tr>
	
	<c:if test="${contractMstLom.seal_mthd=='C02102'}">
		<tr>
			<th><spring:message code="clm.page.msg.manage.signPerSE" /></th>
			<td><c:out value='${contractMstLom.signman_nm}'/>/<c:out value='${contractMstLom.signman_jikgup_nm}'/>/<c:out value='${contractMstLom.sign_dept_nm}'/></td>
			<th><spring:message code="clm.page.msg.manage.signDateSE" /></th>
			<td colspan=3><c:out value='${contractMstLom.signday}'/></td>
		</tr>
		<tr>
			<th><spring:message code="clm.page.msg.manage.signPerOth" /></th>
			<td><c:out value='${contractMstLom.oppnt_signman_nm}' escapeXml="false"/>/<c:out value='${contractMstLom.oppnt_signman_jikgup}' escapeXml="false"/>/<c:out value='${contractMstLom.oppnt_signman_dept}' escapeXml="false"/></td>
			<th><spring:message code="clm.page.msg.manage.signDateOth" /></th>
			<td colspan=3><c:out value='${contractMstLom.oppnt_signday}'/></td>
		</tr>
	</c:if>
	
	<tr>
		<th><spring:message code="clm.page.msg.manage.chrgCont" /></th>
		<td><c:out value='${contractMstLom.cntrt_respman_nm}'/>/<c:out value='${contractMstLom.cntrt_respman_jikgup_nm}'/>/<c:out value='${contractMstLom.cntrt_resp_dept_nm}'/></td>
<%--	신성우 주석처리 2014-03-31		
		<th><spring:message code="clm.page.msg.manage.preAnnDate" /></th>
		<td colspan="3"><c:out value='${contractMstLom.exprt_notiday}'/></td>
--%>
	</tr>
	<tr id="deferOpnnArea" class="lineAdd">
		<th class="borTz02"><spring:message code="clm.page.msg.manage.relPerson" /></th><!-- 관련자 RELATION_MAN  relation_man -->
		<td colspan="5">
		<c:if test="${command.session_user_id == contractMstLom.reqman_id or 'RA01' eq command.top_role }">
		<span class="btn_all_b" onclick="javascript:openChooseClient();"><span class="add"></span><a> <spring:message code="clm.page.msg.manage.add" htmlEscape="true" /></a></span><!-- 추가 -->
		</c:if>
		<span id="id_trgtman_nm">
		<%if(authReqList !=null && authReqList.size() >0){ %>
		<%for(int j=0;j<authReqList.size();j++){ %>
		<% ListOrderedMap lom = (ListOrderedMap)authReqList.get(j);%>
		<% if((j !=0 && j !=1) && (j % 2 == 0 )){%><br/><%}%>
		<% if(j != 0 && (j % 2 !=0 )){%>,<% }%>	
		<input type="hidden" name="arr_demnd_seqno" id="arr_demnd_seqno" value="<%=lom.get("demnd_seqno") %>" /><input type="hidden" name="arr_trgtman_id" id="arr_trgtman_id" value="<%=lom.get("trgtman_id") %>" /><input type="hidden" name="arr_trgtman_nm" id="arr_trgtman_nm" value="<%=lom.get("trgtman_nm") %>" /><input type="hidden" name="arr_trgtman_jikgup_nm" id="arr_trgtman_jikgup_nm" value="<%=lom.get("trgtman_jikgup_nm") %>" /><input type="hidden" name="arr_trgtman_dept_nm" id="arr_trgtman_dept_nm" value="<%=lom.get("trgtman_dept_nm") %>" />					
		<%=lom.get("trgtman_nm") %>/<%=lom.get("trgtman_jikgup_nm") %>/<%=lom.get("trgtman_dept_nm") %>
		<% }%>
		<% }%>
		</span>
		</td>
	</tr>
<%-- 	<th><spring:message code="clm.page.msg.manage.relPerson" /></th> --%>
<%-- 	<td colspan="5"><c:out value="${reqAuthInfo}" /></td> --%>
</table><br>

<div class="title_basic3"><h3><spring:message code="clm.page.msg.manage.copyVerInf" /></h3></div>
<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
	<colgroup>
		<col width="14%" />
		<col width="39%" />
		<col width="14%" />
		<col width="&%" />
	</colgroup>
	<tr>
		<th><spring:message code="clm.page.msg.manage.copyVerRegPer" /></th>
		<td><c:out value='${contractMstLom.cpy_regman_nm}'/>/<c:out value='${contractMstLom.cpy_regman_jikgup_nm}'/>/<c:out value='${contractMstLom.cpy_reg_dept_nm}'/></td>
		<th><spring:message code="clm.page.msg.manage.copyVerRegDate" /></th>
		<td colspan="3"><c:out value='${contractMstLom.cpy_regday}'/></td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.conclCopy" /></th><!--style='height:20px' -->
		<td class="last-td" colspan="5">
			<!-- Sungwoo replacement height size 2014-07-03-->
			<iframe src="<c:url value='/clm/blank.do' />" id="fileList4" name="fileList4" frameborder="0" class='addfile_iframe_d'  width="100%" height="100%" scrolling="auto" allowTransparency="true"></iframe>
		</td>
	</tr>
	<!-- 
	<tr>
		<th><spring:message code="clm.page.msg.manage.attachment" /></th>
		<td colspan=5></td>
	</tr>
	 -->
</table><br>

<div class="title_basic3"><h3><spring:message code="clm.page.msg.manage.orgInf" /></h3></div>
<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
	<colgroup>
		<col width="14%" />
		<col width="39%" />
		<col width="14%" />
		<col width="&%" />
	</colgroup>
	<tr>
		<th><spring:message code="clm.page.msg.manage.orgSendPer" /></th>
		<td><c:out value='${contractMstLom.org_hdovman_nm}'/>/<c:out value='${contractMstLom.org_hdovman_jikgup_nm}'/>/<c:out value='${contractMstLom.org_hdov_dept_nm}'/></td>
		<th><spring:message code="clm.page.msg.manage.orgRcvDate" /></th>
		<td colspan=3><c:out value='${contractMstLom.org_acptday}'/></td>
	</tr>
	<tr>
		<th><spring:message code="las.page.field.contractManager.groupChief" /></th>
		<td><c:out value='${contractMstLom.org_tkovman_nm}'/>/<c:out value='${contractMstLom.org_tkovman_jikgup_nm}'/>/<c:out value='${contractMstLom.org_tkov_dept_nm}'/></td>
		<th><spring:message code="clm.page.msg.manage.orgPlace" /></th>
		<td colspan=3><c:out value='${contractMstLom.org_strg_pos}'/></td>
	</tr>
	<c:if test="${contractMstLom.org_acpt_dlay_cause != null}">
	<tr>
		<th><spring:message code="clm.page.msg.common.memo" /></th>
		<td colspan=5><%--<c:out value='${contractMstLom.org_acpt_dlay_cause}'/> --%>
			<c:out escapeXml="false" value="${ contractMstLom.org_acpt_dlay_cause}" />
		</td>
	</tr>
	</c:if>
</table><br>


<c:if test="${contractMstLom.cntrt_cnclsn_yn=='N' && !empty contractCnclsndlayLom}">
<div class="title_basic"><h4><spring:message code="clm.page.msg.manage.conclDelayInf" /></h4></div>
<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
	<colgroup>
		<col width="14%" />
		<col width="86%" /> <!-- width size 변경 신성우 2014-04-24 -->
	</colgroup>
	<tr>
		<th><spring:message code="clm.page.msg.manage.conclDelayRsn" /></th>
		<td colspan=5><c:out value='${contractCnclsndlayLom.dlay_cause}'/></td>
	</tr>
	<tr>
		<th><spring:message code="clm.page.msg.manage.preChgConclDate" /></th>
		<td colspan=5><c:out value='${contractCnclsndlayLom.chgeaft_cnclsn_plndday}'/></td>
	</tr>
</table>
</c:if>
</div>
 
<script language="javascript">
$(document).ready(function(){
	attachHisPage();
});
 	var collapseIcon = '<%=IMAGE%>/icon/ico_plus.gif';
 	var expandIcon = '<%=IMAGE%>/icon/ico_minus.gif';
 	
	//$('img[src$=ico_plus.gif]').toggle(function(){
	//검토이력
	$('img[alt$=show]').toggle(function(){
		$(this).removeAttr().attr("src",expandIcon);
		$(this).parent().parent().parent().next('#tr_show').show();
		// iframe 높이 변경 추가
		$("iframe[id^='final_cont']").each(function(i,o){
			var is_chrome = navigator.userAgent.toLowerCase().indexOf('chrome') > -1;
			if (is_chrome) {
				o.height = o.contentDocument.documentElement.scrollHeight + 30;	
			} else {
				o.height = o.contentWindow.document.body.scrollHeight + 30;	
			}
		});		 		
	}, function(){
		$(this).removeAttr().attr("src",collapseIcon);
		$(this).parent().parent().parent().next('#tr_show').hide();
	});
	//승인이력
	$('img[alt$=show01]').toggle(function(){
		$(this).removeAttr().attr("src",expandIcon);
		$(this).parent().parent().parent().next('#tr_show01').show();
	}, function(){
		$(this).removeAttr().attr("src",collapseIcon);
		$(this).parent().parent().parent().next('#tr_show01').hide();
	});
	//체결이력
	$('img[alt$=show02]').toggle(function(){
		$(this).removeAttr().attr("src",expandIcon);
		$(this).parent().parent().parent().next('#tr_show02').show();
		
	}, function(){
		$(this).removeAttr().attr("src",collapseIcon);
		$(this).parent().parent().parent().next('#tr_show02').hide();
	});

	//첨부파일
	function attachHisPage(){
	var frm = document.frm;
	
	    //의뢰-계약서파일 CONSULT
	     $('input[name^=fileInfos_cntrt]').each(function(index){
	    	$fileInfos_cntrt = $(this).attr("name");
	        $fileList_cntrt = $(this).prev().attr("name");
	        $cntrt_cnsdreq_id = $(this).next().val();
	    	
	        frm.target          = $fileList_cntrt;
	    	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    	frm.method.value    = "forwardClmsFilePage";
	    	frm.fileInfoName.value = $fileInfos_cntrt;
	    	frm.fileFrameName.value = $fileList_cntrt;
	    	//의뢰 계약서파일
	    	frm.file_bigclsfcn.value = "F012";
	    	frm.file_midclsfcn.value = "F01202";
	    	frm.file_smlclsfcn.value = "F0120201";
	    	frm.ref_key.value = "<c:out value='${hisCommand.cntrt_id}'/>@"+$cntrt_cnsdreq_id;
	    	getClmsFilePageCheck('frm');
	    	//frm.submit();
	    });
	     //의뢰-첨부/별첨파일 CONSULT
	     $('input[name^=fileInfos_append]').each(function(index){
	    	$fileInfos_append = $(this).attr("name");
	        $fileList_append = $(this).prev().attr("name");
	        $append_cnsdreq_id = $(this).next().val();
	    	
	        frm.target          = $fileList_append;
	    	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    	frm.method.value    = "forwardClmsFilePage";
	    	frm.fileInfoName.value = $fileInfos_append;
	    	frm.fileFrameName.value = $fileList_append;
	    	//의뢰 계약서파일
	    	frm.file_bigclsfcn.value = "F012";
	    	frm.file_midclsfcn.value = "F01202";
	    	frm.file_smlclsfcn.value = "F0120208";
	    	frm.ref_key.value = "<c:out value='${hisCommand.cntrt_id}'/>@"+$append_cnsdreq_id;
	    	getClmsFilePageCheck('frm');
	    	//frm.submit();
	    });
 	     //의뢰-기타_체결본 파일 CONSULT
	     $('input[name^=fileInfos_other]').each(function(index){
	    	$fileInfos_other = $(this).attr("name");
	        $fileList_other = $(this).prev().attr("name");
	        $other_cnsdreq_id = $(this).next().val();
	    	
	        frm.target          = $fileList_other;
	    	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    	frm.method.value    = "forwardClmsFilePage";
	    	frm.fileInfoName.value = $fileInfos_other;
	    	frm.fileFrameName.value = $fileList_other;
	    	//의뢰 계약서파일
	    	frm.file_bigclsfcn.value = "F012";
	    	frm.file_midclsfcn.value = "F01202";
	    	frm.file_smlclsfcn.value = "F0120212";
	    	frm.ref_key.value = "<c:out value='${hisCommand.cntrt_id}'/>@"+$other_cnsdreq_id;
	    	getClmsFilePageCheck('frm');
	    });
	     
/*	   //의뢰-계약단가파일 CONSULT
	     $('input[name^=fileInfos_unit]').each(function(index){
	    	$fileInfos_unit = $(this).attr("name");
	        $fileList_unit = $(this).prev().attr("name");
	        $unit_cnsdreq_id = $(this).next().val();
	    	
	        frm.target          = $fileList_unit;
	    	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    	frm.method.value    = "forwardClmsFilePage";
	    	frm.fileInfoName.value = $fileInfos_unit;
	    	frm.fileFrameName.value = $fileList_unit;
	    	//의뢰 계약서파일
	    	frm.file_bigclsfcn.value = "F012";
	    	frm.file_midclsfcn.value = "F01202";
	    	frm.file_smlclsfcn.value = "F0120211";
	    	frm.ref_key.value = "<c:out value='${hisCommand.cntrt_id}'/>@"+$unit_cnsdreq_id;
	    	
	    	frm.submit();
	    }); */
	    
	     //-- 회신

<%
if("CLM".equals(session.getAttribute("secfw.session.sys_cd"))){
%>
	     //회신-계약서파일 CONSULT
	     $('input[name^=reply_fileInfos_cntrt]').each(function(index){
	    	$reply_fileInfos_cntrt = $(this).attr("name");
	        $reply_fileList_cntrt = $(this).prev().attr("name");
	        $reply_cntrt_cnsdreq_id = $(this).next().val();
	    	
	        frm.target          = $reply_fileList_cntrt;
	    	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    	frm.method.value    = "forwardClmsFilePage";
	    	frm.fileInfoName.value = $reply_fileInfos_cntrt;
	    	frm.fileFrameName.value = $reply_fileList_cntrt;
	    	//회신 계약서파일
	    	frm.file_bigclsfcn.value = "F012";
	    	frm.file_midclsfcn.value = "F01202";
	    	frm.file_smlclsfcn.value = "F0120202";
	    	frm.ref_key.value = "<c:out value='${hisCommand.cntrt_id}'/>@"+$reply_cntrt_cnsdreq_id;
	    	getClmsFilePageCheck('frm');
	    	//frm.submit();
	    });
	     //회신-첨부/별첨파일 CONSULT
	     $('input[name^=reply_fileInfos_append]').each(function(index){
	    	$reply_fileInfos_append = $(this).attr("name");
	        $reply_fileList_append = $(this).prev().attr("name");
	        $reply_append_cnsdreq_id = $(this).next().val();
	    	
	        frm.target          = $reply_fileList_append;
	    	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    	frm.method.value    = "forwardClmsFilePage";
	    	frm.fileInfoName.value = $reply_fileInfos_append;
	    	frm.fileFrameName.value = $reply_fileList_append;
	    	//회신 계약서파일
	    	frm.file_bigclsfcn.value = "F012";
	    	frm.file_midclsfcn.value = "F01202";
	    	frm.file_smlclsfcn.value = "F0120209";
	    	frm.ref_key.value = "<c:out value='${hisCommand.cntrt_id}'/>@"+$reply_append_cnsdreq_id;
	    	getClmsFilePageCheck('frm');
	    	//frm.submit();
	    });
/* 	     //회신-기타파일 CONSULT
	     $('input[name^=reply_fileInfos_other]').each(function(index){
	    	$reply_fileInfos_other = $(this).attr("name");
	        $reply_fileList_other = $(this).prev().attr("name");
	        $reply_other_cnsdreq_id = $(this).next().val();
	    	
	        frm.target          = $reply_fileList_other;
	    	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    	frm.method.value    = "forwardClmsFilePage";
	    	frm.fileInfoName.value = $reply_fileInfos_other;
	    	frm.fileFrameName.value = $reply_fileList_other;
	    	//회신 계약서파일
	    	frm.file_bigclsfcn.value = "F012";
	    	frm.file_midclsfcn.value = "F01202";
	    	frm.file_smlclsfcn.value = "F0120206";
	    	frm.ref_key.value = "<c:out value='${hisCommand.cntrt_id}'/>@"+$reply_other_cnsdreq_id;
	    	
	    	frm.submit();
	    });
	   //회신-계약단가파일 CONSULT
	     $('input[name^=reply_fileInfos_unit]').each(function(index){
	    	$reply_fileInfos_unit = $(this).attr("name");
	        $reply_fileList_unit = $(this).prev().attr("name");
	        $reply_unit_cnsdreq_id = $(this).next().val();
	    	
	        frm.target          = $reply_fileList_unit;
	    	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    	frm.method.value    = "forwardClmsFilePage";
	    	frm.fileInfoName.value = $reply_fileInfos_unit;
	    	frm.fileFrameName.value = $reply_fileList_unit;
	    	//회신 계약서파일
	    	frm.file_bigclsfcn.value = "F012";
	    	frm.file_midclsfcn.value = "F01202";
	    	frm.file_smlclsfcn.value = "F0120212";
	    	frm.ref_key.value = "<c:out value='${hisCommand.cntrt_id}'/>@"+$reply_unit_cnsdreq_id;
	    	
	    	frm.submit();
	    }); */
<%
}else if("LAS".equals(session.getAttribute("secfw.session.sys_cd"))){
%>
		//회신-계약서파일 (일반/IP) CONSULT
	     $('input[name^=reply_fileInfos_sub_cntrt]').each(function(index){
	    	$reply_fileInfos_sub_cntrt = $(this).attr("name");
	        $reply_fileList_sub_cntrt = $(this).prev().attr("name");
	        $reply_sub_cntrt_cnsdreq_id = $(this).next().val();
	        $reply_sub_cntrt_cnsd_dept = $(this).next().next().val();
	    	
	        frm.target          = $reply_fileList_sub_cntrt;
	    	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    	frm.method.value    = "forwardClmsFilePage";
	    	frm.fileInfoName.value = $reply_fileInfos_sub_cntrt;
	    	frm.fileFrameName.value = $reply_fileList_sub_cntrt;
	    	//회신 계약서파일
	    	frm.file_bigclsfcn.value = "F012";
	    	frm.file_midclsfcn.value = "F01202";
	    	frm.file_smlclsfcn.value = "F0120203";
	    	frm.ref_key.value = "<c:out value='${hisCommand.cntrt_id}'/>@"+$reply_sub_cntrt_cnsdreq_id+"@"+$reply_sub_cntrt_cnsd_dept;
	    	getClmsFilePageCheck('frm');
	    	//frm.submit();
	    });
	     //회신-첨부/별첨파일 (일반/IP)CONSULT
	     $('input[name^=reply_fileInfos_sub_append]').each(function(index){
	    	$reply_fileInfos_sub_append = $(this).attr("name");
	        $reply_fileList_sub_append = $(this).prev().attr("name");
	        $reply_sub_append_cnsdreq_id = $(this).next().val();
	        $reply_sub_append_cnsd_dept = $(this).next().next().val();
	    	
	        frm.target          = $reply_fileList_sub_append;
	    	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    	frm.method.value    = "forwardClmsFilePage";
	    	frm.fileInfoName.value = $reply_fileInfos_sub_append;
	    	frm.fileFrameName.value = $reply_fileList_sub_append;
	    	//회신 계약서파일
	    	frm.file_bigclsfcn.value = "F012";
	    	frm.file_midclsfcn.value = "F01202";
	    	frm.file_smlclsfcn.value = "F0120210";
	    	frm.ref_key.value = "<c:out value='${hisCommand.cntrt_id}'/>@"+$reply_sub_append_cnsdreq_id+"@"+$reply_sub_append_cnsd_dept;
	    	getClmsFilePageCheck('frm');
	    	//frm.submit();
	    });
	   //회신-기타파일 (일반/IP)CONSULT
	     $('input[name^=reply_fileInfos_sub_other]').each(function(index){
	    	$reply_fileInfos_sub_other = $(this).attr("name");
	        $reply_fileList_sub_other = $(this).prev().attr("name");
	        $reply_sub_other_cnsdreq_id = $(this).next().val();
	        $reply_sub_other_cnsd_dept = $(this).next().next().val();
	    	
	        frm.target          = $reply_fileList_sub_other;
	    	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    	frm.method.value    = "forwardClmsFilePage";
	    	frm.fileInfoName.value = $reply_fileInfos_sub_other;
	    	frm.fileFrameName.value = $reply_fileList_sub_other;
	    	//회신 계약서파일
	    	frm.file_bigclsfcn.value = "F012";
	    	frm.file_midclsfcn.value = "F01202";
	    	frm.file_smlclsfcn.value = "F0120207";
	    	frm.ref_key.value = "<c:out value='${hisCommand.cntrt_id}'/>@"+$reply_sub_other_cnsdreq_id+"@"+$reply_sub_other_cnsd_dept;
	    	getClmsFilePageCheck('frm');
	    	//frm.submit();
	    });
	   //회신-계약단가파일(일반/IP) CONSULT
	     $('input[name^=reply_fileInfos_sub_unit]').each(function(index){
	    	$reply_fileInfos_sub_unit = $(this).attr("name");
	        $reply_fileList_sub_unit = $(this).prev().attr("name");
	        $reply_sub_unit_cnsdreq_id = $(this).next().val();
	        $reply_sub_unit_cnsd_dept = $(this).next().next().val();
	    	
	        frm.target          = $reply_fileList_sub_unit;
	    	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    	frm.method.value    = "forwardClmsFilePage";
	    	frm.fileInfoName.value = $reply_fileInfos_sub_unit;
	    	frm.fileFrameName.value = $reply_fileList_sub_unit;
	    	//회신 계약서파일
	    	frm.file_bigclsfcn.value = "F012";
	    	frm.file_midclsfcn.value = "F01202";
	    	frm.file_smlclsfcn.value = "F0120213";
	    	frm.ref_key.value = "<c:out value='${hisCommand.cntrt_id}'/>@"+$reply_sub_unit_cnsdreq_id+"@"+$reply_sub_unit_cnsd_dept;
	    	getClmsFilePageCheck('frm');
	    	//frm.submit();
	    });
<%
}
%>
	     //승인-파일 
	     $('input[name^=approve_fileInfos]').each(function(index){
	    	$approve_fileInfos = $(this).attr("name");
	        $approve_fileList = $(this).prev().attr("name");
	        $approve_cnsdreq_id = $(this).next().val();
	    	
	        frm.target          = $approve_fileList;
	    	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    	frm.method.value    = "forwardClmsFilePage";
	    	frm.fileInfoName.value = $approve_fileInfos;
	    	frm.fileFrameName.value = $approve_fileList;
	    	//회신 계약서파일
	    	frm.file_bigclsfcn.value = "F012";
	    	frm.file_midclsfcn.value = "F01203";
	    	frm.file_smlclsfcn.value = "";
	    	frm.ref_key.value = "<c:out value='${hisCommand.cntrt_id}'/>";
	    	getClmsFilePageCheck('frm');
	    	//frm.submit();
	    });
	   //사전검토정보-파일 
	     $('input[name^=info_fileInfos]').each(function(index){
	    	$info_fileInfos = $(this).attr("name");
	        $info_fileList = $(this).prev().attr("name");
	    	
	        frm.target          = $info_fileList;
	    	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    	frm.method.value    = "forwardClmsFilePage";
	    	frm.fileInfoName.value = $info_fileInfos;
	    	frm.fileFrameName.value = $info_fileList;
	    	//회신 계약서파일
	    	frm.file_bigclsfcn.value = "F012";
	    	frm.file_midclsfcn.value = "F01201";
	    	frm.file_smlclsfcn.value = "F0120101";
	    	frm.ref_key.value = "<c:out value='${hisCommand.cntrt_id}'/>";
	    	getClmsFilePageCheck('frm');
	    	//frm.submit();
	    });
	   //체결이력 사본첨부-파일 
	     $('input[name^=sign_copyFileInfos]').each(function(index){
	    	$sign_copyFileInfos = $(this).attr("name");
	        $sign_copyFileList = $(this).prev().attr("name");
	    	
	        frm.target          = $sign_copyFileList;
	    	frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    	frm.method.value    = "forwardClmsFilePage";
	    	frm.fileInfoName.value = $sign_copyFileInfos;
	    	frm.fileFrameName.value = $sign_copyFileList;
	    	//회신 계약서파일
	    	frm.file_bigclsfcn.value = "F012";
	    	frm.file_midclsfcn.value = "F01203";
	    	frm.file_smlclsfcn.value = "";
	    	frm.ref_key.value = "<c:out value='${hisCommand.cntrt_id}'/>";
	    	getClmsFilePageCheck('frm');
	    	//frm.submit();
	    });		   
	}
	  
</script>
