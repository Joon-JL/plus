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
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%--
/**
 * 파  일  명 : Type_l.jsp
 * 프로그램명 : 계약유형관리 - 리스트
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<title><spring:message code="clm.main.title" /></title>
<link href="<%=CSS%>/las.css"    type="text/css" rel="stylesheet" />

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript">

$(document).ready(function(){
	//계약분류 코드
  	getCodeSelectByUpCd("frm", "srch_dimension", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=TOP&combo_abbr=F&combo_type=T&combo_del_yn=N&combo_selected="+"<c:out value='${command.srch_dimension}'/>");
});

	function search(){
		goPage(1);
	}

	function goPage(page) {
		var frm = document.frm;
		frm.curPage.value = page;
		frm.method.value = "listType";
		frm.action = "<c:url value='/clm/admin/type.do' />";
		frm.target = "_self";
		viewHiddenProgress(true);
		frm.submit();
	}
	
	function goInsertForm() {
		var frm = document.frm;
		
		frm.method.value = "forwardInsertType";
		frm.action = "<c:url value='/clm/admin/type.do' />";
		frm.target = "_self";
		viewHiddenProgress(true);
		frm.submit(); 
	}
	
	function goDetail(type_cd) {
		var frm = document.frm;
		
	    frm.type_cd.value = type_cd;
	    frm.method.value = "detailType";
		frm.action = "<c:url value='/clm/admin/type.do' />";
		frm.target = "_self";
		viewHiddenProgress(true);
		frm.submit();
	}

	/**
	* 메시지 처리
	*/
	function showMessage(msg) {
		if(msg != "" && msg != null && msg.length > 1) {
			alert(msg);
		}
	}
</script>
</head>
<body onload='showMessage("<c:out value='${returnMessage}'/>");'>
<div id="wrap">
	<!-- container -->
	<div id="container">
		<!-- location -->
		<div class="location"><IMG SRC="<%=IMAGE %>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //location -->
		
		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.title.admin.Type" /></h3>
			<!-- button -->
			<div class="btn_wrap fR">
			</div>
			<!-- //button -->
		</div>
		<!-- //title -->
		
		<!-- content -->
		<div id="content">
		<div id="content_in">
			<form:form name="frm" id="frm" method="post" autocomplete="off">
			<input type="hidden" name="method" 		value="" />
			<input type="hidden" name="menu_id" 	value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="curPage" 	value="<c:out value='${command.curPage}'/>" />
			<input type="hidden" name="type_cd"     id="type_cd"     value="<c:out value='${command.type_cd}'/>" />

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
										<col width="40%"/>
										<col width="10%"/>
										<col width="40%"/>
									</colgroup>
									<tr>
										<th>
											<spring:message code="clm.page.field.admin.type.cntr_cd" />
										</th>
										<td>
											<select name="srch_dimension" id="srch_dimension"  style="width:196px;">
											</select>
										</td>
										<th>
											<spring:message code="clm.page.field.admin.type.type_nm" />
										</th>
										<td>
											<input type="text" name="srch_word" class="text" style="width:174px" value="<c:out value='${command.srch_word}'/>" OnKeyDown="if(event.keyCode==13) { search(); }"/>
										</td>
									</tr>
								</table>
							</td>
							<td class="vb tC">
								<a href="javascript:search();" style="cursor:hand;"><img src="<%=IMAGE %>/btn/btn_search.gif" alt="<spring:message code="clm.page.button.search" />"/></a>
							</td>
						</tr>
					</table>
				</div>
         	</div>
			<!--search-->
			
			<!-- button -->
			<div class="t_titBtn">
				<div class="btn_wrap_t">
					<span class="btn_all_w"><span class="edit"></span><a href="javascript:goInsertForm()"><spring:message code="clm.page.button.insert" /></a></span>
				</div>
			</div>
			<!-- //button -->
			
			<!-- list -->
			<table class="list_basic">
				<colgroup>
					<col width="60" />
					<col width="180" />
					<col width="180" />
					<col />
					<col width="120" />
					<col width="120" />
				</colgroup>
				<thead>
					<tr>
			            <th><spring:message code="clm.page.field.admin.type.no" /></th>			<!-- No -->
			            <th><spring:message code="clm.page.field.admin.type.dimension" /></th>	<!-- 분류명(Dimension) -->
			            <th><spring:message code="clm.page.field.admin.type.detail" /></th>		<!-- 상세 -->
			            <th><spring:message code="clm.page.field.admin.type.type" /></th>		<!-- 유형 -->
			            <th><spring:message code="clm.page.field.admin.type.reg_dt" /></th>		<!-- 등록일 -->
			            <th><spring:message code="clm.page.field.admin.type.registrant" /></th>	<!-- 등록자 -->
					</tr>
				</thead>
				<tbody>
					<tr>
						<c:choose>
							<c:when test="${pageUtil.totalRow > 0}">
								<c:forEach var="list" items="${lom}">
								<tr onMouseOut="this.className='';" onMouseOver="this.className='on';this.style.cursor='pointer'" onClick='javascript:goDetail("<c:out value='${list.type_cd}'/>");'>
								    <td class="tC"><c:out value='${list.rn}'/></td>
								    <%if("en".equals(langCd)) { %>
						            <td class="tL"><c:out value='${list.dimension_nm_eng}'/></td>
						            <td class="tL"><c:out value='${list.detail_nm_eng}'/></td>
									<td class="tL">
										<div style='width:280px;overflow:hidden;text-overflow:ellipsis'>
							            	<nobr><c:out value='${list.cd_nm_eng}'/></nobr>
						            	</div>
						            </td>
						            <%} else { %>
						            <td class="tL"><c:out value='${list.dimension_nm}'/></td>
						            <td class="tL"><c:out value='${list.detail_nm}'/></td>
									<td class="tL">
										<div style='width:280px;overflow:hidden;text-overflow:ellipsis'>
							            	<nobr><c:out value='${list.cd_nm}'/></nobr>
						            	</div>
						            </td>
						            <%} %>
						            <td class="tC"><c:out value='${list.reg_dt}'/></td>
						            <td class="tL"><c:out value='${list.reg_nm}'/>/<c:out value='${list.reg_dept_nm}'/></td>
								</tr>
								</c:forEach>
						    </c:when>
						    <c:otherwise>
								<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
									<td colspan="6" align="center" class="tC"><spring:message code="clm.msg.succ.noResult" /></td>
								</tr>
						    </c:otherwise>
						</c:choose>
					</tr>
				</tbody>
			</table>
			<!-- //list -->
				
			<div class="total_num">
				Total : <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%>
			</div>
	        <!-- pagination  -->
			<div class="pagination">
				<%=pageUtil.getPageNavi(pageUtil, "") %>
			</div>
			<!-- //pagination -->
		</form:form>
		</div>
		</div>
		<!-- //content -->
		
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
	<!-- footer -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
	<!-- //footer -->
</body>
</html>