<!DOCTYPE html>

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
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%-- 
/**
 * 파  일  명 : ClmsSelUserList.jsp
 * 프로그램명 : 사용자 선택 리스트 페이지
 * 설      명 : 
 * 작  성  자 : 신남원
 * 작  성  일 : 2011.10.18
 */
--%>
<%
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	List resultList = (List)request.getAttribute("userList");
%>

<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet"/>  
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript">

<%-- 	$(document).ready(function(){
		getCodeSelectByUpCd("frm", "srch_cont_type", '/common/clmsCom.do?method=listComCdByGrpCd&combo_abbr=&combo_grp_cd=C015&combo_type=T&combo_del_yn=N&combo_selected=<%=command.getSrch_cont_type()%>');
	});
		 --%>
	/**
	* 페이징 function
	*/
	function goPage(pgNum){
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/secfw/ssoCheck.do' />";
		frm.method.value = "clmsSelLoginList";
		frm.curPage.value = pgNum;
		frm.submit();
	}
  
	/**
	* 검색버튼
	*/ 
	function pageAction(){
		var frm = document.frm;
		frm.target = "_self";
		frm.action = "<c:url value='/secfw/ssoCheck.do' />";

	    frm.method.value = "clmsSelLoginList";		    
		frm.curPage.value = "1";
		frm.submit();

	}
	
	/**
	* 상세내역 조회
	*/
	function detailView(userId){
		
		var frm = document.frm;
		frm.target = "_self";
		frm.action = "<c:url value='/secfw/ssoCheck.do' />";
		frm.method.value = "clmsSelLoginPrcs";
		frm.user_id.value = userId;
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

<body onLoad=''>

<div id="wrap">
	<!-- container -->
	<div id="container">

		<!-- location -->
		<div class="location"></div>
		<!-- //location -->
		
		<!-- Title -->
		<div class="title">
			<h3><spring:message code="secfw.page.field.common.userListK"/></h3>
		<!-- //title -->
		</div>
		<!-- //title -->
		
		<!-- content -->
		<div id="content">
		<!-- content_in -->
			<div id="content_in">	
			<!-- **************************** Form Setting **************************** -->
			<form:form name="frm" id='frm' method="post" autocomplete="off"> 
				<!-- search form -->
				<input type="hidden" name="method"   value="" />
				<input type="hidden" name="user_id" value="" />
				<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>">
				<input type="hidden" name="f" value="<%=request.getAttribute("f")%>"/>
				
			<!-- // **************************** Form Setting **************************** -->
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
										<th><spring:message code="secfw.page.field.common.userNm"/></th>
										<td>
											<input id="search_name" name="search_name" style="width:180px" class='text' value="<c:out value='${command.search_name}'/>" onKeypress="if(event.keyCode == 13){pageAction();return false;}"/>
											<input id="comp_cd" name="comp_cd" style="width:180px" class='text' value="<c:out value='${command.comp_cd}'/>" onKeypress="if(event.keyCode == 13){pageAction();return false;}"/>
											<select id="role_cd" name="role_cd" style="width:180px" class='text' value="" onKeypress="if(event.keyCode == 13){pageAction();return false;}">
												<option value="">----select-----</option>
												<option value="HQ03">HQ Legal Admin</option>
												<option value="RA00">System Admin</option>
												<option value="RA01">Legal Admin</option>
												<option value="RA02">Reviewer</option>
												<option value="RB01">Business Owner</option>
												<option value="RD01">Legal Admin2</option>
												<option value="RE01">날인담당자</option>
												<option value="RE02">증명서류발급자</option>
												<option value="RM00">Legal Admin3</option>
												<option value="RZ00">Requester</option>
												<option value="RB02">전자임원</option>
												<option value="RC01">Manager</option>
												<option value="HQ01">HQ Legal Head</option>
												<option value="HQ02">HQ Reviewer</option>
											</select>
										</td>
									</tr>
								</table>
							</td>
							<td class="tC">
								<a href="javascript:pageAction();"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="secfw.page.field.common.search2"/>"/></a>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!--//search-->
			
			<!-- list -->
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic mt20">
				<colgroup>
					<col width="10%"/>
					<col width="15%" />
					<col width="15%"/>
					<col width="10%"/>
					<col width="20%"/>
					<col width="10%"/>
					<col width="10%"/>
			  	</colgroup>
			  	<thead>
					<tr>
						<th><spring:message code="secfw.page.field.common.nm"/></th>
						<th><spring:message code="secfw.page.field.common.comNm"/></th>
						<th><spring:message code="secfw.page.field.common.deptNm"/></th>
						<th><spring:message code="secfw.page.field.common.position"/></th>
						<th>E-mail</th>
						<th><spring:message code="secfw.page.field.common.status"/></th>
						<th><spring:message code="secfw.page.field.common.group"/></th>
					</tr>
				</thead>
			 	<tbody>
				<%	if(pageUtil.getTotalRow() > 0){
						int idx = (pageUtil.getThisPage() -1)*pageUtil.getRowPerPage()+1;
						    for(idx=0;idx < resultList.size();idx++){
						    	ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
				%>
			        <tr <%if(idx == resultList.size()-1){%>class="end"<%}%> onMouseOut="this.className='<%if(idx == resultList.size()-1){%>end<%}%>';" onMouseOver="this.className='on <%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onClick="javascript:detailView('<%=lom.get("user_id")%>')"> 
			          <td class='tC'><%=lom.get("user_nm")%></td>
			          <td class='tC'><%=lom.get("comp_nm")%></td>
			          <td class='tC'><%=lom.get("dept_nm")%></td>
			          <td class='tC'><%=lom.get("jikgup_nm")%></td>
			          <td><%=lom.get("email")%></td>
			          <td class='tC'><%=lom.get("status")%></td>
			          <td class='tC'><%=lom.get("blngt_orgnz")%></td>
			        </tr>
				<%
						    }
					}else{		  
				%>
					<tr>
					  <td colspan="7" class="td_ac" align="center"><spring:message code="secfw.msg.succ.noResult" /></td>
					</tr>
				<%}%>			
			  </tbody>
			</table>
			<!-- //list -->
	
			<div class="t_titBtn">	
				<!-- total data -->
				<div class="total_num">
					<spring:message code="secfw.page.field.common.totalData" /> : <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%>
				</div>
				<!-- //total data -->
				
				<!-- pagination  -->
				<div class="pagination">
				<%=pageUtil.getPageNavi(pageUtil, "") %>
				</div>
				<!-- //pagination -->
			</div>
		</form:form>
		</div>
		</div>
		<!-- //content -->	
		</div>
		<!-- //container -->
	</div>
		<!-- footer -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- //footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</body>
</html>

