<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sec.clm.admin.dto.DimensionForm" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%-- 
/**
 * 파  일  명 : DimensionWord_l.jsp
 * 프로그램명 : 계약 용어집 목록
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%
	
    
    DimensionForm command = (DimensionForm)request.getAttribute("command");

    String   menuNavi  = (String)request.getAttribute("secfw.menuNavi");
    String   menu_id   = command.getMenu_id();    // 메뉴 아이디로 3개의 화면을 컨트롤 한다.
    String   menuGubun = "";

    PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
    
    if(menu_id.equals("20130319155933166_0000000380")||menu_id.equals("20130321153745987_0000000437")){   // 계약 용어집
    	menuGubun = "C05302";
    }  else if(menu_id.equals("20111106105940035_0000000283")) {  // 계약 유형별 이해
    	menuGubun = "C05303";
    } else {
    	menuGubun = ""; // 값을 가지고 오지 못할 경우 
    }
	
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet" />

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript">

// 	$(document).ready(function(){
// 		getCodeSelectByUpCd("frm", "srch_two_rule_depth", "/clm/admin/regulation.do?method=getRuldCdHTML&combo_sys_cd=LAS&combo_up_cd=<c:out value='${command.srch_one_rule_depth}'/>&combo_selected=<c:out value='${command.srch_two_rule_depth}'/>");
// 	});

// 	$(function() {
		
// 		$("#srch_one_rule_depth").change(function() {
// 			var oneCd = $("#srch_one_rule_depth").val();
// 			getCodeSelectByUpCd("frm", "srch_two_rule_depth", "/clm/admin/regulation.do?method=getRuldCdHTML&combo_sys_cd=LAS&combo_up_cd="+oneCd+"&combo_selected=");
// 		});
		
// 	});	

	/**
	* 페이징 function
	*/
	function goPage(pgNum){
		var frm = document.frm;
	
		viewHiddenProgress(true);
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/admin/dimension.do' />";
		frm.method.value = "listDimensionWordAdmin";
		frm.curPage.value = pgNum;
		frm.submit();
	}

	function pageAction(flag){
		viewHiddenProgress(true);
		
		var frm = document.frm;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/admin/dimension.do' />";
		
		if(flag == "search"){//조회.
			frm.method.value = "listDimensionWordAdmin";
			frm.curPage.value = "1";
			frm.submit();
		}else if(flag == "new"){//등록.
			frm.method.value = "forwardInsertDimensionAdmin";
			frm.submit();
		}
	}
	
	/**
	* 상세내역 조회
	*/
	function detailView(ruleNo){
		viewHiddenProgress(true);
		
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/admin/dimension.do' />";
		frm.method.value = "detailDimensionWordAdmin";
		frm.rule_no.value = ruleNo;
		frm.submit();
	}

</script>
</head>

<body>

<div id="wrap">
	<!-- container -->
	<div id="container">
	    <!-- Location -->
		<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->
		<!-- Title -->
<%		
        if(menu_id.equals("20130319155933166_0000000380")||menu_id.equals("20130321153745987_0000000437")){   // 계약 용어집 
%>			
    	<div class="title">
		    <h3><spring:message code="clm.page.title.dimension.wordList" /></h3>
		<!-- //title -->
		</div>
<%		
        }  else if(menu_id.equals("20111106105940035_0000000283")) {  // 계약 유형별 이해
%>
    	<div class="title">
		    <h3><spring:message code="clm.page.title.dimension.infowordList" /></h3>
		<!-- //title -->
		</div>
<%		
        } else {
%>
    	<div class="title">
		    <h3><spring:message code="clm.page.msg.common.announce002" /></h3>
		<!-- //title -->
		</div>
<%		
    }
%>
		
		<!-- //title -->
		
		<!-- content -->
		<div id="content">
		<div id="content_in">
			<!-- **************************** Form Setting **************************** -->
			<form:form name="frm" id='frm' method="post" autocomplete="off"> 
			<!-- search form -->
			<input type="hidden" name="method"   value="" />
			<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />
			<!-- Detail Page Param -->
			<!-- key Form -->
			<input type="hidden" name="rule_no"    value="<c:out value='${command.rule_no}'/>" />
			<input type="hidden" name="menuGubun"  value="<%= menuGubun %>" />			
			<!-- //**************************** Form Setting **************************** -->
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
										<th><spring:message code="clm.page.field.regulation.srchORuleDepth" /></th>
										<td>
											<select name="srch_one_rule_depth" id="srch_one_rule_depth">
									        <option value="">-- <spring:message code="clm.page.msg.common.all" /> --</option>
									        <c:forEach var="oneList" items="${ruleListODepth}">
									            <option value="<c:out value='${oneList.up_rule_no}'/>"<c:if test="${oneList.up_rule_no eq command.srch_one_rule_depth}">selected</c:if>><c:out value='${oneList.rule_title}'/></option>
									        </c:forEach>
										</select>
										</td>
										<th><spring:message code="clm.page.field.regulation.srchTRuleDepth" /></th>
										<td>
<!-- 											<select name="srch_two_rule_depth" id="srch_two_rule_depth"> -->
<!-- 										</select> -->
											<input type="text" id ="srch_two_rule_depth" name="srch_two_rule_depth" class="text" style="width:300px" value="<c:out value='${command.srch_cont}'/>"/>
										</td>
									</tr>
									<tr>										
										<th><spring:message code="clm.page.field.regulation.srchCont" /></th>
								    	<td colspan="3">
											<input type="text" id="srch_cont" name="srch_cont" class="text" style="width:300px" value="<c:out value='${command.srch_cont}'/>" />
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
	
			<!-- button -->
			<div class="btn_wrap_t">
				<span class="btn_all_w"><span class="edit"></span><a href="javascript:pageAction('new');"><spring:message code="secfw.page.button.new" /></a></span>
			</div>
			<!-- //button -->
	
			<!-- list -->
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
				<colgroup>
				    <col width="32%"/>
				    <col width="32%"/>
				    <col width="16%"/>
				    <col width="10%"/>
			  	</colgroup>
			  	<thead>
					<tr>
					    <th><spring:message code="clm.page.field.regulation.oneRuleDepthTitle" /></th>
					    <th><spring:message code="clm.page.field.regulation.twoRuleDepthTitle" /></th>
					    <th><spring:message code="clm.page.field.board.srchRegNm" /></th>
						<th><spring:message code="clm.page.field.board.srchAnnceDt" /></th>
					</tr>
				</thead>
			 	<tbody>
			 	    <c:choose>
			 	    <c:when test="${pageUtil.totalRow > 0}">
				 	    <c:forEach var="list" items="${resultList}">
				 	        <tr onMouseOut="this.className='';this.style.cursor='pointer';" onMouseOver="this.className='on';this.style.cursor='pointer';" onClick="detailView(<c:out value='${list.rule_no}'/>);">
					 	        <td class="tL overflow" title='<c:out value="${list.one_title}"/>'><c:out value="${list.one_title}"/></td>
					 	        <td class="tL overflow" title='<c:out value="${list.two_title}"/>'><c:out value="${list.two_title}"/></td>
					 	        <td class="tL overflow" title='<c:out value="${list.reg_nm}"/>'><c:out value="${list.reg_nm}"/></td>
					 	        <td class="tC"><c:out value="${list.reg_dt}"/></td>
				 	        </tr>
				 	    </c:forEach>
				 	 </c:when>
				 	<c:otherwise>
				 	    <tr>
					        <td colspan="4" class="td_ac" align="center"><spring:message code="secfw.msg.succ.noResult" /></td>
					    </tr>
				 	</c:otherwise>
			 	    </c:choose>
			    </tbody>
			</table>
			<!-- //list -->
			<div class="total_num">
				<spring:message code="secfw.page.field.common.totalData" /> : <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%>
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