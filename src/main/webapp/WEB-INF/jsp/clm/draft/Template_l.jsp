<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.draft.dto.LibraryForm" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%-- 
/**
 * 파  일  명 : Template_l.jsp
 * 프로그램명 : 표준 Template 목록
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	List resultList = (List)request.getAttribute("resultList");
	LibraryForm command = (LibraryForm)request.getAttribute("command");
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<link href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet" />

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript">

 	$(document).ready(function(){

		//비즈니스 단계 
		getCodeSelectByUpCd("frm", "srch_biz_clsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=T01&combo_abbr=F&combo_type=T&combo_del_yn=N&combo_selected=<%=command.getSrch_biz_clsfcn()%>");   
		//계약단계
		getCodeSelectByUpCd("frm", "srch_depth_clsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=T02&combo_abbr=F&combo_type=T&combo_del_yn=N&combo_selected=<%=command.getSrch_depth_clsfcn()%>");   
		//계약유형1
		getCodeSelectByUpCd("frm", "srch_cnclsnpurps_bigclsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=T03&combo_abbr=F&combo_type=T&combo_del_yn=N&combo_selected=<%=command.getSrch_cnclsnpurps_bigclsfcn()%>");   
		//계약유형2
		getCodeSelectByUpCd("frm", "srch_cnclsnpurps_midclsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=<%=command.getSrch_cnclsnpurps_bigclsfcn()%>&combo_abbr=F&combo_type=T&combo_del_yn=N&combo_selected=<%=command.getSrch_cnclsnpurps_midclsfcn()%>");
		//언어
		getCodeSelectByUpCd("frm", "srch_lang_gbn", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=&combo_grp_cd=C045&combo_type=T&combo_del_yn=N&combo_selected=<%=command.getSrch_lang_gbn()%>');
		
		
	});

	$(function() {
		$("#srch_cnclsnpurps_bigclsfcn").change(function() {
			var grpCd = $("#srch_cnclsnpurps_bigclsfcn").val();
			getCodeSelectByUpCd("frm", "srch_cnclsnpurps_midclsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd="+grpCd+"&combo_abbr=F&combo_type=T&combo_del_yn=N&combo_selected=");
		});
	});
 
	/**
	* 페이징 function
	*/
	function goPage(pgNum){
		var frm = document.frm;

		viewHiddenProgress(true) ;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/draft/template.do' />";
		frm.method.value = "listTemplate";
		frm.curPage.value = pgNum;
		
		frm.submit();
	}
  
	/**
	* 버튼 동작 부분
	*/ 
	function pageAction(flag){
		viewHiddenProgress(true) ;

		var frm = document.frm;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/draft/template.do' />";

		if(flag == "search"){
		    frm.method.value = "listTemplate";		    
			frm.curPage.value = "1";
			frm.submit();
		}else if(flag == "new"){	
		    frm.method.value = "forwardInsertTemplate";
			frm.submit();
		}
	}
	
	/**
	* 상세내역 조회
	*/
	function detailView(libno){
		viewHiddenProgress(true) ;
		
		var frm = document.frm;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/draft/template.do' />";
		frm.method.value = "detailTemplate";
		frm.lib_no.value = libno;
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

<body onload='showMessage("<c:out value='${returnMessage}'/>");'>
<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id='frm' method="post" autocomplete="off"> 
	<!-- search form -->
	<input type="hidden" name="method"   value="" />
	<input type="hidden" name="menu_id"   value="<c:out value='${command.menu_id}'/>" />
	
	<!-- Detail Page Param -->
	<input type="hidden" name="lib_no" value="<c:out value='${command.lib_no}'/>" />

	<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>" />
	
	<!-- key Form -->

<!-- //
**************************** Form Setting **************************** 
-->
<div id="wrap">
	<!-- container -->
	<div id="container">

		<!-- Location -->
		<div class="location">
			<img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/>
		</div>
		<!-- //Location -->

		<!-- Title -->
		<div class="title">
			<h3><spring:message code="clm.page.title.template.listTitle" /></h3>
		</div>
		<!-- //title -->
		
		<!-- content -->
		<div id="content">
			<!-- search-->
			<div class="search_box">
				<div class="search_box_inner">
					<table class="search_tb">
						<colgroup>
							<col/>
							<col width="75px"/>
						</colgroup>
						<tr>
							<td>
								<table class="search_form">
									<colgroup>
										<col width="10%"/>
										<col width="22%"/>
										<col width="10%"/>
										<col width="15%"/>
										<col width="10%"/>
										<col width="33%"/>
									</colgroup>
									<tr>
										<th><spring:message code="clm.page.field.template.srchBizClsfcn" /></th>
										<td>
											<select name="srch_biz_clsfcn" id="srch_biz_clsfcn">
											</select>
										</td>
										<th><spring:message code="clm.page.field.template.srchCnclsnpurpsClsfcn" /></th>
								    	<td colspan="3">
											<select name="srch_cnclsnpurps_bigclsfcn" id="srch_cnclsnpurps_bigclsfcn" style="width:200px">
											</select>
											<select name="srch_cnclsnpurps_midclsfcn" id="srch_cnclsnpurps_midclsfcn">
											</select>
										</td>
									</tr>
									<tr>										
										<th><spring:message code="clm.page.field.template.srchDepthClsfcn" /></th>
										<td>
											<select name="srch_depth_clsfcn" id="srch_depth_clsfcn">
											</select>
										</td>
										<th><spring:message code="clm.page.field.template.srchLangGbn" /></th>
										<td>
											<select name="srch_lang_gbn" id="srch_lang_gbn">
											</select>								
										</td>
										<th><spring:message code="clm.page.field.template.srchKeyword" /></th>
										<td>
											<select name="srch_keyword" id="srch_keyword">
								           	  <option value="title" default <c:if test="${command.srch_keyword eq 'title'}">selected</c:if>><spring:message code="clm.page.field.template.srchTitle" /></option>
											  <option value="reg_nm"  <c:if test="${command.srch_keyword eq 'reg_nm'}">selected</c:if>><spring:message code="clm.page.field.template.srchRegNm" /></option>								        	
								           	  <option value="oppnt_cd" default <c:if test="${command.srch_keyword eq 'oppnt_cd'}">selected</c:if>><spring:message code="clm.page.field.template.srchCntrtOppntCd" /></option>
								           	</select>
											<input id="srch_keyword_content" name="srch_keyword_content" style="width:140px" value="<c:out value='${command.srch_keyword_content}'/>" onKeypress="if(event.keyCode == 13){pageAction('search');return false;}"/>
										</td>
									</tr>
								</table>
							</td>
							<td class="vb tC">
								<a href="javascript:pageAction('search');"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="clm.page.msg.common.inquire" htmlEscape="true" />"/></a>
							</td>
						</tr>
					</table>
				</div>
			</div>	
			<!--//search-->
	
		<%if(command.isAuth_insert()){ %>
			<!-- button -->
			<div class="t_titBtn">			
				<div class="btn_wrap_t">
					<span class="btn_all_b"><span class="save"></span><a href="javascript:pageAction('new');"><spring:message code="secfw.page.button.new" /></a></span>
				</div>
			</div>
			<!-- //button -->
		<%} %>
			
			<!-- list -->
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
				<colgroup>
					<col width="15%" />
					<col width="10%"/>
					<col width="20%"/>
					<col width="25%"/>
					<col width="8%"/>
					<col width="14%"/>
					<col width="8%"/>
			  	</colgroup>
			  	<thead>
					<tr>
						<th><spring:message code="clm.page.field.template.bizClsfcn" /></th>
						<th><spring:message code="clm.page.field.template.depthClsfcn" /></th>
						<th><spring:message code="clm.page.field.template.cnclsnpurpsClsfcn" /></th>
						<th><spring:message code="clm.page.field.template.title" /></th>
						<th><spring:message code="clm.page.field.template.langGbn" /></th>
						<th><spring:message code="clm.page.field.template.cntrtOppntCd" /></th>
						<th><spring:message code="clm.page.field.template.rdcnt" /></th>
					</tr>
				</thead>
			 	<tbody>
				<%	if(pageUtil.getTotalRow() > 0){
						int idx = (pageUtil.getThisPage() -1)*pageUtil.getRowPerPage()+1;
						    for(idx=0;idx < resultList.size();idx++){
						    	ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
				%>
			        <tr <%if(idx == resultList.size()-1){%>class="end"<%}%> onMouseOut="this.className='<%if(idx == resultList.size()-1){%>end<%}%>';" onMouseOver="this.className='on <%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onClick="javascript:detailView(<%=lom.get("lib_no")%>)"> 
			          <td class="txtover" title="<%=(String)lom.get("biz_clsfcn_nm")%>"><nobr><%=(String)lom.get("biz_clsfcn_nm")%></nobr></td>
			          <td><%=(String)lom.get("depth_clsfcn_nm")%></td>
			          <td class="tL txtover" title="<%=(String)lom.get("cnclsnpurps_bigclsfcn_nm")%><%if(!"".equals((String)lom.get("cnclsnpurps_midclsfcn"))){ %> ▶ <%}%><%=(String)lom.get("cnclsnpurps_midclsfcn_nm")%>"><nobr><%=(String)lom.get("cnclsnpurps_bigclsfcn_nm")%><%if(!"".equals((String)lom.get("cnclsnpurps_midclsfcn"))){ %> ▶ <%}%><%=(String)lom.get("cnclsnpurps_midclsfcn_nm")%></nobr></td>
			          <td class="tL txtover" title="<%=(String)lom.get("title")%>"><nobr><%=(String)lom.get("title")%></nobr></td>
			          <td><%=(String)lom.get("lang_gbn_nm")%></td>
			          <td class="tL txtover" title="<%=(String)lom.get("cntrt_oppnt")%>"><nobr><%=(String)lom.get("cntrt_oppnt")%></nobr></td>
			          <td><%=(Integer)lom.get("rdcnt")%></td>
			        </tr>
				<%
						    }
					}else{		  
				%>
					<tr class="end">
					  <td colspan="7"><spring:message code="secfw.msg.succ.noResult" /></td>
					</tr>
				<%}%>			
			  </tbody>
			</table>
			<!-- //list -->
	
			<div class="t_titBtn">	
				<!-- total data -->
				<div class="total_num">
					<spring:message code="secfw.page.field.common.totalData" />: <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%><spring:message code="clm.page.msg.common.inquire" />
				</div>
				<!-- //total data -->
				
				<!-- pagination  -->
				<div class="pagination">
				<%=pageUtil.getPageNavi(pageUtil, "") %>
				</div>
				<!-- //pagination -->
			</div>
		</div>
		<!-- //content -->
		
		<!-- footer -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- //footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
</form:form>
</body>
</html>
