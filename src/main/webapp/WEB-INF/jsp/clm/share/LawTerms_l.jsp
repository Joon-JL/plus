<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.share.dto.LawTermsForm" %>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%-- 
/**
 * 파  일  명 : LawTerms_l.jsp
 * 프로그램명 : 계약법률용어  목록
 * 설      명 : 
 * 작  성  자 : 유영섭
 * 작  성  일 : 2011.09.01
 */
--%>  
 <%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	List resultList = (List)request.getAttribute("resultList");
	LawTermsForm command = (LawTermsForm)request.getAttribute("command");
%> 

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>



<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<LINK href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet">
<LINK href="<%=CSS%>/common.css"   type="text/css" rel="stylesheet">


<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>


<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript">
		
	/**
	* 페이징 function
	*/
	function goPage(pgNum){
		
		var frm = document.frm;

		viewHiddenProgress(true) ;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/share/LawTerms.do' />"; 
		frm.method.value = "listLawTerms";
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
		frm.action = "<c:url value='/clm/share/LawTerms.do' />"; 
		if(flag == "search"){
		    frm.method.value = "listLawTerms";		    
			frm.curPage.value = "1";
			frm.submit();
	    }else if(flag == "new"){	
			frm.target = "_self";
			frm.action = "<c:url value='/clm/share/LawTerms.do' />"; 
		    frm.method.value = "forwardLawTermsInsert";
		    frm.seqno.value=0;

			viewHiddenProgress(true) ;
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
		frm.action = "<c:url value='/clm/share/LawTerms.do' />"; 
		frm.method.value = "detailLawTerms";
		frm.seqno.value = seqno;
		frm.submit();		
	}
	
</script>
</head>

<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id='frm' method="post" autocomplete="off"> 
	<!-- search form -->
	<input type="hidden" name="method"   value="" />
	<input type="hidden" name="menu_id"   value="<c:out value='${command.menu_id}'/>" />
	<!-- Detail Page Param -->
	<input type="hidden" name="seqno" value="<c:out value='${command.seqno}'/>" />

	<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>"> 
	
	<!-- key Form -->

<!-- //
**************************** Form Setting **************************** 
-->
<div id="wrap">

	
	<!-- container -->
	<div id="container">
		<!-- Location -->
	<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
	  <!-- //Location -->
		<!-- Title -->
		<div class="title">
		<h3><spring:message code="clm.page.title.lawterms.listTitle" /></h3>
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
										<col width="90%"/>
									</colgroup>
									<tr>
										<th><spring:message code="clm.page.msg.share.searchCondition" /></th>
										<td>
										<select name="srch_keyword" id="srch_keyword">
											<option value="terms" default <c:if test="${command.srch_keyword eq 'terms'}">selected</c:if>><spring:message code="clm.page.field.lawterms.terms" /></option>
						           	 	    <option value="termsabbrnm"  <c:if test="${command.srch_keyword eq 'termsabbrnm'}">selected</c:if>><spring:message code="clm.page.field.lawterms.terms_ab" /></option>
						           		</select>
								    	<input id="srch_keyword_content" name="srch_keyword_content" style="width:120px" value="<c:out value='${command.srch_keyword_content}'/>" OnKeyDown="if(event.keyCode==13) { pageAction('search'); }"/>
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
			<div class="btn_wrap_t">
				<span class="btn_all_b"><span class="save"></span><a href="javascript:pageAction('new');"><spring:message code="secfw.page.button.new" /></a></span>
			</div>
			<!-- //button -->
		<%} %>
			
			<!-- list -->
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
				<colgroup>
					<col width="60px"/>
					<col width="250rpx" />
					<col width="100px"/>
					<col width="130px"/>
					<col width="100px"/>
					<col width="100px"/>
			  	</colgroup>
			  	<thead>
					<tr>
						<th><spring:message code="clm.page.field.lawterms.terms_no" /></th>
						<th><spring:message code="clm.page.field.lawterms.terms_nm" /></th>
						<th><spring:message code="clm.page.field.lawterms.terms_abbr_nm" /></th>
						<th><spring:message code="clm.page.field.lawterms.regNm" /></th>
						<th><spring:message code="clm.page.field.lawterms.regdt" /></th>
						<th><spring:message code="clm.page.field.lawterms.rdcnt" /></th>
					</tr>
				</thead>
			 	<tbody>
				<%   if(pageUtil.getTotalRow() > 0){
						int idx = (pageUtil.getThisPage() -1)*pageUtil.getRowPerPage()+1;
						    for(idx=0;idx < resultList.size();idx++){
						    	ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
				%>
			        <tr <%if(idx == resultList.size()-1){%>class="end"<%}%> onMouseOut="this.className='<%if(idx == resultList.size()-1){%>end<%}%>';" onMouseOver="this.className='on <%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onClick="javascript:detailView(<%=lom.get("seqno")%>)"> 
			          <td class="tC"><%=lom.get("seqno")%></td>
			          <td class="tL txtover"><%=(String)lom.get("terms_nm")%></a></td>
			          <td class="tL txtover"><%=lom.get("terms_abbr_nm")%></td>
			          <td class="tC"><%=lom.get("reg_nm")%></td>
			          <td class="tC"><%=DateUtil.formatDate((String)lom.get("reg_dt"), "-")%></td>
			          <td class="tC"><%=lom.get("rdcnt")%></td>
			        </tr>
			      <%
							}
					}else{		  
				  %>
			
					<tr>
					  <td colspan="6" class="td_ac" align="center"><spring:message code="secfw.msg.succ.noResult" /></td>
					</tr>
					<%}%>	
			  </tbody>
			</table>
			<!-- //list -->
	
			<div class="total_num">
				<spring:message code="secfw.page.field.common.totalData" />: <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%><spring:message code="clm.page.msg.common.case" />
			</div>
			
			<!-- pagination  -->
			<div class="pagination">
				<%=pageUtil.getPageNavi(pageUtil, "") %>
			</div>
			<!-- //pagination -->
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
