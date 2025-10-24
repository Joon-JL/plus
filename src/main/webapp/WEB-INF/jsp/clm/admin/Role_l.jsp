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
 * 파  일  명 : Role_l.jsp
 * 프로그램명 : 결재라인 Role - 리스트
 * 설      명 : 
 * 작  성  자 : 박정훈
 * 작  성  일 : 2014.05.19
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
%>

<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="x-ua-compatible" content="IE=edge" >
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<title><spring:message code="clm.main.title" /></title>
<link href="<%=CSS%>/las.css"    type="text/css" rel="stylesheet" />

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript">

	$(function(){
		getCodeSelectByUpCd("frm", "srch_loc_gbn", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=A&combo_grp_cd=COMP2&combo_type=S&combo_del_yn=N");
	});

	function search(){
		goPage(1);
	}

	function goPage(page) {
		var frm = document.frm;
		frm.curPage.value = page;
		frm.method.value = "listRole";
		frm.action = "<c:url value='/clm/admin/role.do' />";
		frm.target = "_self";
		viewHiddenProgress(true);
		frm.submit();
	}
	
	function goInsertForm() {
		var frm = document.frm;
		
		frm.method.value = "forwardInsertRole";
		frm.action = "<c:url value='/clm/admin/role.do' />";
		frm.target = "_self";
		viewHiddenProgress(true);
		frm.submit(); 
	}
	
	function goDetail(role_cd, comp_cd) {
		var frm = document.frm;
		
	    frm.role_cd.value = role_cd;
	    frm.loc_gbn.value = comp_cd;
	    frm.method.value = "detailRole";
		frm.action = "<c:url value='/clm/admin/role.do' />";
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
			<h3><spring:message code="secfw.page.field.role.Information" /></h3><!-- Role Information  -->
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
			<input type="hidden" name="role_cd" 	value="" />
			<input type="hidden" name="loc_gbn" 	value="" />
			

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
										<col width="30%"/>
										<col width="10%"/>
										<col width="20%"/>
										<col width="10%"/>
										<col width="20%"/>
									</colgroup>
									<tr>
										<th><spring:message code="secfw.page.field.Role.PathName" /></th><!-- Role Name -->
										<td>
											<input type="text" name="srch_role_name" class="text" style="width:174px"  OnKeyDown="if(event.keyCode==13) { search(); }"/>
										</td>
										<th class='head'><spring:message code="las.page.filed.userLoc"/></th>
										<td>
											<select name="srch_loc_gbn" id="srch_loc_gbn"></select>												
										</td>
										<th><spring:message code="secfw.page.field.approvalPath.Active" /></th><!-- Active-->
										<td>
											<select name="srch_use_yn" id="srch_use_yn"  style="width:57px;">
												<option value="all">All</option>
												<option value="Y">Y</option>
												<option value="N">N</option>
											</select>
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
					<col width="5%" />
					<col width="10%" />
					<col width="8%" />
					<col width="8%" />
					<col width="*" />
					<col width="20%" />
					<col width="10%" />
					<col width="5%" />
				</colgroup>
				<thead>
					<tr>
			            <th><spring:message code="clm.page.field.admin.type.no" /></th>			<!-- No -->
			            <th><spring:message code="secfw.page.field.Role.PathName" /></th>	<!-- role name -->
			            <th><spring:message code="secfw.page.field.Role.RoleId" /></th>		<!-- role id -->
			            <th><spring:message code="las.page.filed.userLoc"/></th>				<!-- Subsidary -->
			            <th><spring:message code="secfw.page.field.approvalPath.Description" /></th>		<!-- Description -->
			            <th><spring:message code="secfw.page.field.approvalPath.CreatedBy" /></th>		<!-- Created By -->
			            <th><spring:message code="secfw.page.field.approvalPath.CreatedOn" /></th>	<!-- Created on -->
			            <th><spring:message code="secfw.page.field.approvalPath.Active" /></th>	<!-- Active -->
					</tr>
				</thead>
				<tbody>
					<tr>
						<c:choose>
							<c:when test="${pageUtil.totalRow > 0}">
								<c:forEach var="list" items="${lom}">
								<tr onMouseOut="this.className='';" onMouseOver="this.className='on';this.style.cursor='pointer'" onClick='javascript:goDetail("<c:out value='${list.role_cd}'/>","<c:out value='${list.comp_cd}'/>");'>
								    <td class="tC"><c:out value="${list.rn}"/></td>
								    <td class="tC"><c:out value="${list.role_name}"/></td>
								    <td class="tC"><c:out value="${list.role_cd}"/></td>
								    <td class="tC"><c:out value="${list.comp_cd}"/></td>
								    <td class="tC"><c:out value="${list.description}"/></td>
						            <td class="tC"><c:out value="${list.reg_id}"/></td>
						            <td class="tC"><c:out value="${list.reg_dt}"/></td>
						            <td class="tC"><c:out value="${list.use_yn}"/></td>
								</tr>
								</c:forEach>
						    </c:when>
						    <c:otherwise>
								<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
									<td colspan="8" align="center" class="tC"><spring:message code="clm.msg.succ.noResult" /></td>
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