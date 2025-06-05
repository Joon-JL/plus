<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>
<%@ page import="com.sds.secframework.common.util.Token"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="com.sec.common.util.ClmsDataUtil" %>
<%--
/**
 * 파  일  명 : CntrTypeBasicInfoMng_d.jsp
 * 프로그램명 : 계약유형 속성관리 - 상세
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
    HashMap resultMap = (HashMap)request.getAttribute("resultMap");
	
	List detaillist = (List)resultMap.get("detaillist");
	//정보항목
	List infoItmlist = (List)resultMap.get("infoItmlist");
	
	String modifyRole = (String)request.getAttribute("modifyRole");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><spring:message code="clm.main.title" /></title>
<link href="<%=CSS %>/clm.css" type="text/css" rel="stylesheet" />
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript">

	/**
	* 리스트 이동
	*/
	function goList() {
		var frm = document.frm;
		viewHiddenProgress(true) ;
		
		frm.method.value = "listCntrTypeBasicInfoMng";
		frm.action = "<c:url value='/clm/admin/cntrTypeBasicInfoMng.do' />";
		frm.target = "_self";
		frm.submit();
	}
	
	/**
	* 수정 이동
	*/	
	function goModify(){
		var frm = document.frm;
		viewHiddenProgress(true) ;
		
		frm.method.value = "forwardModifyTypeBasicInfoMng";
		frm.action = "<c:url value='/clm/admin/cntrTypeBasicInfoMng.do' />";
		frm.target = "_self";
		frm.submit();
	}
	
	/**
	* 메세지 처리
	*/
	function showMessage(msg){
		if( msg != "" && msg != null && msg.length > 1 ) {
			alert(msg);
		}
	}

</script>
</head>
<body>

<form:form name="frm" id="frm" autocomplete="off">
<input type="hidden" name="method"   	 		value="" />
<input type="hidden" name="menu_id"  	 		value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage"  	 		value="<c:out value='${command.curPage}'/>" />

<input type="hidden" name="srch_biz_clsfcn"  		value="<c:out value='${command.srch_biz_clsfcn}'/>" />
<input type="hidden" name="srch_depth_clsfcn"  		value="<c:out value='${command.srch_depth_clsfcn}'/>" />
<input type="hidden" name="srch_cnclsnpurps_bigclsfcn"  		value="<c:out value='${command.srch_cnclsnpurps_bigclsfcn}'/>" />
<input type="hidden" name="srch_cnclsnpurps_midclsfcn"  		value="<c:out value='${command.srch_cnclsnpurps_midclsfcn}'/>" />

<div id="wrap">
	<!-- container -->
	<div id="container">
		
		<!-- location -->
		<div class="location"><IMG SRC="<%=IMAGE %>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //location -->
		
		<!-- title -->
		<div class="title">
		  <h3><spring:message code="clm.page.cntrTypeBasicInfoMng.detailTitle" /></h3><!-- 계약유형별 속성관리 상세 -->
		</div>
		<!-- //title -->
		
		<!-- content -->
		<div id="content">
		<!-- button -->
	  	<div class="btn_wrap_t03">
<%
	//수정권한이 있는 role만 수정 버튼이 보이게 한다.
	if("A".equals(modifyRole)){
%>
			<span class="btn_all_w"><span class="edit"></span><a href="javascript:goModify();"><spring:message code="secfw.page.button.modify" /></a></span><!-- 수정 -->
<%		
	}
%>	  		 
			<span class="btn_all_w"><span class="list"></span><a href="javascript:goList();"><spring:message code="secfw.page.button.list" /></a></span><!-- 목록 -->
	  	</div>
		<!-- //button -->
<%
	ListOrderedMap dataLom = (ListOrderedMap)detaillist.get(0);
%>		 
		<!--table -->
	  	<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
			<colgroup>
				<col width="140"></col>
				<col></col>
			</colgroup>
			<tr>
				<th><spring:message code="clm.field.cntrTypeBasicInfoMng.bizStage" /></th><!-- BIZ단계 -->
				<td><%=StringUtil.bvl(dataLom.get("biz_clsfcn_nm"), "")%></td>
			</tr>
			<tr>
				<th><spring:message code="clm.field.cntrTypeBasicInfoMng.contractStage" /></th><!-- 계약단계 -->
				<td><%=StringUtil.bvl(dataLom.get("depth_clsfcn_nm"), "")%></td>
			</tr>
			<tr>
				<th><spring:message code="clm.field.cntrTypeBasicInfoMng.conclusionPurpose" /></th><!-- 체결의목적 -->
				<td><%=StringUtil.bvl(dataLom.get("cnclsnpurps_bigclsfcn_nm"), "")%>&nbsp;&nbsp;<%=StringUtil.bvl(dataLom.get("cnclsnpurps_midclsfcn_nm"), "")%></td>
			</tr>
		</table>
		
		<input type="hidden" name="biz_clsfcn" value="<%=StringUtil.bvl(dataLom.get("biz_clsfcn"), "")%>"/>
		<input type="hidden" name="depth_clsfcn" value="<%=StringUtil.bvl(dataLom.get("depth_clsfcn"), "")%>"/>
		<input type="hidden" name="cnclsnpurps_bigclsfcn" value="<%=StringUtil.bvl(dataLom.get("cnclsnpurps_bigclsfcn"), "")%>"/>
		<input type="hidden" name="cnclsnpurps_midclsfcn" value="<%=StringUtil.bvl(dataLom.get("cnclsnpurps_midclsfcn"), "")%>"/>
		
		<!-- 상하 스크롤 테이블 -->
		<div class="heigh_scroll">
			<table cellspacing="0" cellpadding="0" border="0" class="table-style04">
				<colgroup>
					<col width="140"></col>
					<col width="150"></col>
					<col></col>
				</colgroup>
<%
	for(int i = 0; i < infoItmlist.size(); i++){
		ListOrderedMap lom = (ListOrderedMap)infoItmlist.get(i);
		if(i == 0){
%>
				<tr>
					<th valign='top' rowspan="<%=infoItmlist.size()%>" class="end_th"><spring:message code="clm.field.cntrTypeBasicInfoMng.infoArticle" /></th><!-- 정보항목 -->
					<td class="td_col_03 tal_lineL"><%--<%=lom.get("attr_seqno")%>&nbsp;--%><%=lom.get("info_itm")%></td>
					<td>
<%
			for(int j=0; j < detaillist.size(); j++){
				ListOrderedMap subLom = (ListOrderedMap)detaillist.get(j);
				
				if(StringUtil.bvl(lom.get("attr_seqno"), "").equals(StringUtil.bvl(subLom.get("attr_seqno"), ""))){
%>
				<%--<%=StringUtil.bvl(subLom.get("attr_seqno"), "")%>&nbsp;<%=StringUtil.bvl(subLom.get("mndtry_yn"), "")%>&nbsp;--%>
				<% if(subLom.get("mndtry_yn").equals("Y")) { %>
				<spring:message code="clm.field.cntrTypeBasicInfoMng.necessariness" />
				<%} else {%>
				<spring:message code="clm.field.cntrTypeBasicInfoMng.choice" />
				<%}%>				
<%					
				}
			}
%>						
					</td>
				</tr>
<%			
		}else{
%>
				<tr>
					<td class="td_col_03 tal_lineL"><%--<%=lom.get("attr_seqno")%>&nbsp;--%><%=lom.get("info_itm")%></td>
					<td>
<%
			for(int j=0; j < detaillist.size(); j++){
				ListOrderedMap subLom = (ListOrderedMap)detaillist.get(j);
				
				if(StringUtil.bvl(lom.get("attr_seqno"), "").equals(StringUtil.bvl(subLom.get("attr_seqno"), ""))){
%>
				<%--<%=StringUtil.bvl(subLom.get("attr_seqno"), "")%>&nbsp;<%=StringUtil.bvl(subLom.get("mndtry_yn"), "")%>&nbsp;--%>
				<% if(subLom.get("mndtry_yn").equals("Y")) { %>
				<spring:message code="clm.field.cntrTypeBasicInfoMng.necessariness" />
				<%} else {%>
				<spring:message code="clm.field.cntrTypeBasicInfoMng.choice" />
				<%}%>				
<%					
				}
			}
%>
					</td>
				</tr>
<%			
		}	
	}
%>				
			</table>
		</div>
		<!-- //상하 스크롤 테이블 -->
		</div>
		<!-- //content -->
	
		<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js"></script>
		<!-- // footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>	
</form:form>
</body>
</html>