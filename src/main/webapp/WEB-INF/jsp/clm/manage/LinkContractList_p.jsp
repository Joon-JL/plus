<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.manage.dto.ManageForm" %>

<%@ page import="com.sds.secframework.common.util.Token"%>
<%@ page import="com.sds.secframework.common.util.DateUtil"%>
<%@ page import="com.sds.secframework.common.util.PageUtil"%>
<%@ page import="com.sds.secframework.common.util.StringUtil"%>
<%@ page import="com.sds.secframework.common.util.WebUtil"%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap"%>
<%--
/**
 * 파  일  명 : LinkContractList_p.jsp
 * 프로그램명 : 연관계약정보 팝업 목록
 * 설      명 : 
 * 작  성  자 : 신남원
 * 작  성  일 : 2011.10.20
 
 
 */
--%>
<%
	//PageUtil 객체를 읽어들임
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	List resultList = (List)request.getAttribute("resultList");
	ManageForm command = (ManageForm)request.getAttribute("command");
	
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title" /></title>

<LINK href="<%=CSS%>/popup.css"  type="text/css" rel="stylesheet">
<LINK href="<%=CSS%>/clm.css"    type="text/css" rel="stylesheet">
                                      
<script language="javascript" src="/script/clms/common.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>

<script language="javascript" src="/script/secfw/jquery/jquery.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.form.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.flot.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>

<script language="javascript" src="/script/secfw/jquery/calendar/jquery.mousewheel.min.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>

<script type="text/javascript">

	// 부모 창으로 내용을 전달 함.
	function parentContract(id, name) {
		
	//	document.domain = "samsung.net";
		
	//	var setInfo = new Object();
		
	//	setInfo.id = id;
	//	setInfo.name = name;
		
	//	opener.setContract(setInfo);
		
	//	self.close();
	
	    //alert("id = "+id+" name = "+name);
	
	    var frm = document.frm;
	    var rURL = frm.sReturnUrl.value;
	    
	    var sumURL = rURL+"?id="+id+"&name="+name;
	    
	    //alert("sumURL = "+sumURL);
	    
	    location.href = sumURL;
	

	}
	
	// 조회버튼 클릭
	function doAction(){
		
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/manage/contract.do' />";
		frm.method.value = "listContractPop";
	    frm.curPage.value = "1";
	    frm.submit();
        
	}
	
	
	/**
	* 페이징 function
	*/
	function goPage(pgNum){
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/manage/contract.do' />";
		frm.method.value = "listContractPop";
		frm.curPage.value = pgNum;
		frm.submit();
	}
	
</script>
</head>
<body class="pop">
<form:form name="frm" id='frm' method="post" autocomplete="off">
	<input type="hidden" name="method" value="" />
    <input type="hidden" name="menu_id" id="menu_id" value="<c:out value='${command.menu_id}'/>" />
    <input type="hidden" name="curPage" id="curPage" value="<c:out value='${command.curPage}'/>" />
    
    <input type="hidden" name="sReturnUrl" id="sReturnUrl" value="<c:out value='${command.sReturnUrl}'/>">

<!-- header -->
<h1><spring:message code="clm.page.title.manageContractPop.listTitle" /></h1>
<!-- //header -->
<div class="pop_area">
	<!-- Popup Size 840*600 -->
	<div class="h_600">
		<div class="pop_content mt10">
		<!-- container -->	
			<!--90% 안에서 작업하기 popup search-->
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
										<col />
									</colgroup>
									<tr>
										<th><spring:message code="clm.page.field.manageCommon.srchCntrtNm" /></th>
										<td><input type="text" name="srch_review_title" style="width:250px" class="text_full" value="<c:out value='${command.srch_review_title}'/>"/></td>
									</tr>
								</table>
							</td>
							<td class="vb tC"><a href="javascript:doAction();"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="clm.page.msg.common.inquire" htmlEscape="true" />"/></a>
							</td>
						</tr>
					</table>
				</div>
          	</div>        	
			<!--search-->
			
			
			<!--//90% 안에서 작업하기 popup search-->
					<!-- table01 -->
					<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
					  <colgroup>
						<col width="6%"/>
						<col width="30%"/>
						<col width="10%"/>
						<col width="12%"/>						
						<col width="12%"/>
						<col width="15%"/>
						<col width="15%"/>
					  </colgroup>
					  <thead>
						<tr>
						  <th><spring:message code="clm.page.field.manageCommon.no" /></th>
						  <th><spring:message code="clm.page.field.manageCommon.cntrtNm" /></th>
						  <th><spring:message code="clm.page.field.manageCommon.cntrtType" /></th>
						  <th><spring:message code="clm.page.field.manageCommon.respmanNm" />						  
						  <th><spring:message code="clm.page.field.manageCommon.regDay" /></th>
						  <th><spring:message code="clm.page.field.manageCommon.step" /></th>
						  <th><spring:message code="clm.page.field.manageCommon.status" /></th>
						</tr>
					  </thead>
					  <tbody>
					  <%
						if(pageUtil.getTotalRow() > 0) {
							for(int idx=0; idx<resultList.size(); idx++) {
								ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);	%>
						<tr  <%if(idx == resultList.size()-1){%>class="end"<%}%> onMouseOut="this.className='<%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onMouseOver="this.className='on <%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onClick="javascript:parentContract('<%=lom.get("cntrt_id") %>','<%=lom.get("cntrt_nm") %>');">
						  <td><%=lom.get("rn") %></td>
						  <td class="tL txtover" title="<%=lom.get("cntrt_nm") %>"><nobr><%=lom.get("cntrt_nm") %></nobr></td>
						  <td><%=lom.get("biz_clsfcn_nm") %></td>
						  <td><%=lom.get("cntrt_respman_nm") %></td>
						  <td><%=DateUtil.formatDate((String)(lom.get("reg_dt")), "-") %></td>
						  <td><%=lom.get("prcs_depth_nm") %></td>
						  <td><%=lom.get("depth_status_nm") %></td>
						  </tr>
						<% 	}
						}else{
						%>
							<tr class="end">
					  		<td colspan="7"><spring:message code="secfw.msg.succ.noResult" /></td>
							</tr>
						<%
						}
						%>
						  </tbody>
					</table>
					<!--// table01 -->

					<div class="total_num">
						Total : <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%><spring:message code="clm.page.msg.common.case" htmlEscape="true" />
					</div>
					<!-- pagination -->
					<div class="pagination">
					<%=pageUtil.getPageNavi(pageUtil, "") %>
					</div>
					<!-- //pagination -->
				<!-- //content -->
		</div>
	</div>
</div>
<input type="text" name="srch_review_title2" style="width:0px; height: 0px;" />
<!-- //body -->
<!--footer -->
<div class="pop_footer">

</div>
<!-- //footer -->
</form:form>
</body>

</html>
