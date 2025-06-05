<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.admin.dto.FaqForm" %>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%-- 
/**
 * 파  일  명 : Faq_l.jsp
 * 프로그램명 : FAQ 목록
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	List resultList = (List)request.getAttribute("resultList");
	FaqForm command = (FaqForm)request.getAttribute("command");
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
		getCodeSelectByUpCd("frm", "srch_cont_type", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=&combo_grp_cd=C015&combo_type=T&combo_del_yn=N&combo_selected=<%=command.getSrch_cont_type()%>');
	});
		
	/**
	* 페이징 function
	*/
	function goPage(pgNum){
		var frm = document.frm;

		viewHiddenProgress(true) ;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/admin/faq.do' />";
		frm.method.value = "listFaq";
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
		frm.action = "<c:url value='/clm/admin/faq.do' />";

		if(flag == "search"){
		    frm.method.value = "listFaq";		    
			frm.curPage.value = "1";
			frm.submit();
		}else if(flag == "new"){	
		    frm.method.value = "forwardInsertFaq";
			frm.submit();
		}
	}
	
	/**
	* 상세내역 조회
	*/
	function detailView(seqno){
		viewHiddenProgress(true) ;
		
		var frm = document.frm;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/admin/faq.do' />";
		frm.method.value = "detailFaq";
		frm.seqno.value = seqno;
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
	<input type="hidden" name="seqno" value="<c:out value='${command.seqno}'/>" />
	<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>">
	
<!-- // **************************** Form Setting **************************** -->
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
			<h3><spring:message code="clm.page.title.faq.listTitle" /></h3>
		<!-- //title -->
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
										<col width="35%"/>
										<col width="10%"/>
										<col width="45%"/>
									</colgroup>
									<tr>
										<th><spring:message code="clm.page.field.faq.srchContType" /></th>
										<td>
											<select name="srch_cont_type" id="srch_cont_type">
											</select>										
										</td>
										<th><spring:message code="clm.page.field.faq.srchKeyword" /></th>
								    	<td>
											<select name="srch_keyword" id="srch_keyword">
								           	  <option value="title" default <c:if test="${command.srch_keyword eq 'title'}">selected</c:if>><spring:message code="clm.page.field.faq.srchTitle" /></option>
								           	  <option value="cont"  <c:if test="${command.srch_keyword eq 'cont'}">selected</c:if>><spring:message code="clm.page.field.faq.srchCont" /></option>
								        	</select>
											<input id="srch_keyword_content" name="srch_keyword_content" style="width:180px" value="<c:out value='${command.srch_keyword_content}'/>" onKeypress="if(event.keyCode == 13){pageAction('search');return false;}"/>
										</td>
									</tr>
								</table>
							</td>
							<td class="tC">
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
					<col width="7%"/>
					<col width="9%" />
					<col width="30%"/>
					<col width="10%"/>
					<col width="18%"/>
					<col width="8%"/>
					<col width="10%"/>
					<col width="8%"/>
			  	</colgroup>
			  	<thead>
					<tr>
						<th><spring:message code="clm.page.field.faq.no" /></th>
						<th><spring:message code="clm.page.field.faq.contType" /></th>
						<th><spring:message code="clm.page.field.faq.title" /></th>
						<th><spring:message code="clm.page.field.faq.title" /></th>
						<th><spring:message code="clm.page.field.faq.rdTrgt1" /></th>
						<th><spring:message code="clm.page.field.faq.orgnzCd" /></th>
						<th><spring:message code="clm.page.field.faq.regNm" /></th>
						<th><spring:message code="clm.page.field.faq.regDt" /></th>
						<th><spring:message code="clm.page.field.faq.rdcnt" /></th>
					</tr>
				</thead>
			 	<tbody>
				<%	if(pageUtil.getTotalRow() > 0){
						int idx = (pageUtil.getThisPage() -1)*pageUtil.getRowPerPage()+1;
						    for(idx=0;idx < resultList.size();idx++){
						    	ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
				%>
			        <tr <%if(idx == resultList.size()-1){%>class="end"<%}%> onMouseOut="this.className='<%if(idx == resultList.size()-1){%>end<%}%>';" onMouseOver="this.className='on <%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onClick="javascript:detailView(<%=lom.get("seqno")%>)"> 
			          <td><%=lom.get("rn")%></td>
			          <td><%=lom.get("cont_type_nm")%></td>
			          <%if("ko".equals(langCd)) { %>
			          <td class="tL txtover" title="<%=(String)lom.get("title")%>"><nobr><%=(String)lom.get("title")%></nobr></td>
			          <%} else if("fr".equals(langCd)) { %>
			          <td class="tL txtover" title="<%=(String)lom.get("title_fr")%>"><nobr><%=(String)lom.get("title_fr")%></nobr></td>
			          <%} else if("de".equals(langCd)) { %>
			          <td class="tL txtover" title="<%=(String)lom.get("title_de")%>"><nobr><%=(String)lom.get("title_de")%></nobr></td>
			          <%} else { %>
			          <td class="tL txtover" title="<%=(String)lom.get("title_en")%>"><nobr><%=(String)lom.get("title_en")%></nobr></td>
			          <%} %>
			          <td><nobr><%=(String)lom.get("rd_trgt1_nm")%></nobr></td>
			          <td title="<%=(String)lom.get("orgnz_nms")%>"><nobr><%=(String)lom.get("orgnz_nm")%></nobr></td>
			          <td><%=(String)lom.get("reg_nm")%></td>
			          <td><%=DateUtil.formatDate((String)lom.get("reg_dt"), "-")%></td>
			          <td><%=lom.get("rdcnt")%></td>
			        </tr>
				<%
						    }
					}else{		  
				%>
					<tr>
					  <td colspan="8" class="td_ac" align="center"><spring:message code="secfw.msg.succ.noResult" /></td>
					</tr>
				<%}%>			
			  </tbody>
			</table>
			<!-- //list -->
	
			<div class="t_titBtn">	
				<!-- total data -->
				<div class="total_num">
					<spring:message code="secfw.page.field.common.totalData" />: <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%><spring:message code="clm.page.msg.common.case" />
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

