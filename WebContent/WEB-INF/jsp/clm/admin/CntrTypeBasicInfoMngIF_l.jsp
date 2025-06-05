<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<%@page import="com.sec.common.util.ClmsDataUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="com.sec.clm.admin.dto.CntrTypeBasicInfoMngForm" %>
<%--
/**
 * 파  일  명 : CntrTypeBasicInfoMngIF_l.jsp
 * 프로그램명 : 계약 유형별 속성관리 - 리스트(iframe 부분)
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
	HashMap resultMap = (HashMap)request.getAttribute("resultMap");
	
	//정보항목
	List infoItmlist = (List)resultMap.get("infoItmlist");
	
	List iframeList0 = (List)resultMap.get("iframeList0");
	List iframeList1 = (List)resultMap.get("iframeList1");
	List iframeList2 = (List)resultMap.get("iframeList2");
	List iframeList3 = (List)resultMap.get("iframeList3");
	List iframeList4 = (List)resultMap.get("iframeList4");
	List iframeList5 = (List)resultMap.get("iframeList5");
	List iframeList6 = (List)resultMap.get("iframeList6");
	List iframeList7 = (List)resultMap.get("iframeList7");
	List iframeList8 = (List)resultMap.get("iframeList8");
	List iframeList9 = (List)resultMap.get("iframeList9");
	

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
</script>
</head>
<body>
<!--------------- 왼쪽테이블 스클롤되는 테이블 -->
<table class="list_basic04">
	<thead>
		<tr>
			<th colspan="<%=infoItmlist.size()%>" class="tL" style="padding-left:280px;"><spring:message code="clm.field.cntrTypeBasicInfoMng.infoArticle" /></th><!-- 정보항목 -->
		</tr>
		<tr>
<%
	String[] itmList = new String[infoItmlist.size()];
	for(int i = 0; i < infoItmlist.size(); i++){
		ListOrderedMap lom = (ListOrderedMap)infoItmlist.get(i);
		
		itmList[i] = StringUtil.bvl(lom.get("attr_seqno"), "");		
%>
			<th class="tal_bg_cor04"><%--<%=lom.get("attr_seqno")%>&nbsp;--%><%=lom.get("info_itm")%></th>
<%			
	}				
%>		
		</tr>
	</thead>
	<tbody>
<%
	//0
	if(iframeList0 != null){
%>
		<tr>
<% 
		//총 컬럼 갯수 로 roop!!
		for(int i=0; i < infoItmlist.size(); i++){ //116
			ListOrderedMap lom = (ListOrderedMap)infoItmlist.get(i);
%>
			<td>
<%			
			for(int j=0; j < iframeList0.size(); j++){
				ListOrderedMap subLom = (ListOrderedMap)iframeList0.get(j);
				
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
<%
		}		
%>
		</tr>
<%		
	}
%>		

<%
	//1
	if(iframeList1 != null){
%>
		<tr>
<% 
		//총 컬럼 갯수 로 roop!!
		for(int i=0; i < infoItmlist.size(); i++){ //116
			ListOrderedMap lom = (ListOrderedMap)infoItmlist.get(i);
%>
			<td>
<%			
			for(int j=0; j < iframeList1.size(); j++){
				ListOrderedMap subLom = (ListOrderedMap)iframeList1.get(j);
				
				if(StringUtil.bvl(lom.get("attr_seqno"), "").equals(StringUtil.bvl(subLom.get("attr_seqno"), ""))){
%>
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
<%
		}		
%>
		</tr>
<%		
	}
%>		

<%
	//2
	if(iframeList2 != null){
%>
		<tr>
<% 
		//총 컬럼 갯수 로 roop!!
		for(int i=0; i < infoItmlist.size(); i++){ //116
			ListOrderedMap lom = (ListOrderedMap)infoItmlist.get(i);
%>
			<td>
<%			
			for(int j=0; j < iframeList2.size(); j++){
				ListOrderedMap subLom = (ListOrderedMap)iframeList2.get(j);
				
				if(StringUtil.bvl(lom.get("attr_seqno"), "").equals(StringUtil.bvl(subLom.get("attr_seqno"), ""))){
%>
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
<%
		}		
%>
		</tr>
<%		
	}
%>		

<%
	//3
	if(iframeList3 != null){
%>
		<tr>
<% 
		//총 컬럼 갯수 로 roop!!
		for(int i=0; i < infoItmlist.size(); i++){ //116
			ListOrderedMap lom = (ListOrderedMap)infoItmlist.get(i);
%>
			<td>
<%			
			for(int j=0; j < iframeList3.size(); j++){
				ListOrderedMap subLom = (ListOrderedMap)iframeList3.get(j);
				
				if(StringUtil.bvl(lom.get("attr_seqno"), "").equals(StringUtil.bvl(subLom.get("attr_seqno"), ""))){
%>
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
<%
		}		
%>
		</tr>
<%		
	}
%>		

<%
	//4
	if(iframeList4 != null){
%>
		<tr>
<% 
		//총 컬럼 갯수 로 roop!!
		for(int i=0; i < infoItmlist.size(); i++){ //116
			ListOrderedMap lom = (ListOrderedMap)infoItmlist.get(i);
%>
			<td>
<%			
			for(int j=0; j < iframeList4.size(); j++){
				ListOrderedMap subLom = (ListOrderedMap)iframeList4.get(j);
				
				if(StringUtil.bvl(lom.get("attr_seqno"), "").equals(StringUtil.bvl(subLom.get("attr_seqno"), ""))){
%>
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
<%
		}		
%>
		</tr>
<%		
	}
%>		

<%
	//5
	if(iframeList5 != null){
%>
		<tr>
<% 
		//총 컬럼 갯수 로 roop!!
		for(int i=0; i < infoItmlist.size(); i++){ //116
			ListOrderedMap lom = (ListOrderedMap)infoItmlist.get(i);
%>
			<td>
<%			
			for(int j=0; j < iframeList5.size(); j++){
				ListOrderedMap subLom = (ListOrderedMap)iframeList5.get(j);
				
				if(StringUtil.bvl(lom.get("attr_seqno"), "").equals(StringUtil.bvl(subLom.get("attr_seqno"), ""))){
%>
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
<%
		}		
%>
		</tr>
<%		
	}
%>		

<%
	//6
	if(iframeList6 != null){
%>
		<tr>
<% 
		//총 컬럼 갯수 로 roop!!
		for(int i=0; i < infoItmlist.size(); i++){ //116
			ListOrderedMap lom = (ListOrderedMap)infoItmlist.get(i);
%>
			<td>
<%			
			for(int j=0; j < iframeList6.size(); j++){
				ListOrderedMap subLom = (ListOrderedMap)iframeList6.get(j);
				
				if(StringUtil.bvl(lom.get("attr_seqno"), "").equals(StringUtil.bvl(subLom.get("attr_seqno"), ""))){
%>
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
<%
		}		
%>
		</tr>
<%		
	}
%>		

<%
	//7
	if(iframeList7 != null){
%>
		<tr>
<% 
		//총 컬럼 갯수 로 roop!!
		for(int i=0; i < infoItmlist.size(); i++){ //116
			ListOrderedMap lom = (ListOrderedMap)infoItmlist.get(i);
%>
			<td>
<%			
			for(int j=0; j < iframeList7.size(); j++){
				ListOrderedMap subLom = (ListOrderedMap)iframeList7.get(j);
				
				if(StringUtil.bvl(lom.get("attr_seqno"), "").equals(StringUtil.bvl(subLom.get("attr_seqno"), ""))){
%>
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
<%
		}		
%>
		</tr>
<%		
	}
%>		

<%
	//8
	if(iframeList8 != null){
%>
		<tr>
<% 
		//총 컬럼 갯수 로 roop!!
		for(int i=0; i < infoItmlist.size(); i++){ //116
			ListOrderedMap lom = (ListOrderedMap)infoItmlist.get(i);
%>
			<td>
<%			
			for(int j=0; j < iframeList8.size(); j++){
				ListOrderedMap subLom = (ListOrderedMap)iframeList8.get(j);
				
				if(StringUtil.bvl(lom.get("attr_seqno"), "").equals(StringUtil.bvl(subLom.get("attr_seqno"), ""))){
%>
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
<%
		}		
%>
		</tr>
<%		
	}
%>		

<%
	//9
	if(iframeList9 != null){
%>
		<tr>
<% 
		//총 컬럼 갯수 로 roop!!
		for(int i=0; i < infoItmlist.size(); i++){ //116
			ListOrderedMap lom = (ListOrderedMap)infoItmlist.get(i);
%>
			<td>
<%			
			for(int j=0; j < iframeList9.size(); j++){
				ListOrderedMap subLom = (ListOrderedMap)iframeList9.get(j);
				
				if(StringUtil.bvl(lom.get("attr_seqno"), "").equals(StringUtil.bvl(subLom.get("attr_seqno"), ""))){
%>
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
<%
		}		
%>
		</tr>
<%		
	}
%>		
		 
	</tbody>
</table>
<!------------------- //왼쪽테이블 스클롤되는 테이블 -->
</body>
</html>
