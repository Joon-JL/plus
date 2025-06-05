<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.manage.dto.ConsiderationForm" %>
<%@ page import="com.sec.clm.manage.dto.ConsiderationVO" %>

<%@ page import="com.sds.secframework.common.util.Token"%>
<%@ page import="com.sds.secframework.common.util.DateUtil"%>
<%@ page import="com.sds.secframework.common.util.PageUtil"%>
<%@ page import="com.sds.secframework.common.util.StringUtil"%>
<%@ page import="com.sds.secframework.common.util.WebUtil"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap"%>
<%--
/**
 * 파  일  명 : RelationContract_l.jsp
 * 프로그램명 : 연관계약정보 팝업 목록
 * 설      명 : 
 * 작  성  자 : 차현진 
 * 작  성  일 : 2011.09.23
 
 
 */
--%>
<%
	//PageUtil 객체를 읽어들임
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	ArrayList resultList = (ArrayList)request.getAttribute("resultList");
	ConsiderationForm command = (ConsiderationForm)request.getAttribute("command");
	String returnMessage = (String)request.getAttribute("returnMessage");
	
	
	/*
	String sArg = request.getParameter("arg").substring(0,6);
	
	String sSearchName = request.getParameter("search_cntrt_nm");
	String sSearchReqName = request.getParameter("search_req_nm");
	
	if(sSearchName == null){
		sSearchName = "";
	} else if(sSearchName.equals("")){
		
		sSearchName = "";
	
	} else {
		
		sSearchName = request.getParameter("search_cntrt_nm");
	}
	
	if(sSearchReqName == null){
		sSearchReqName = "";
		
	} else if(sSearchReqName.equals("")){
		
		sSearchReqName = "";
	
	} else {
		sSearchReqName = request.getParameter("search_req_nm");
	}
	
	*/
	
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta sci="ContractList_p.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title" /></title>

<LINK href="<%=CSS%>/popup.css"  type="text/css" rel="stylesheet"/>
<link href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet"/>
                                      
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
	function parentContract(id, name, biz, sArg) {
		
		//alert("전달 = "+ id+" "+name+" "+biz+" "+sArg );
		
		opener.setContract(id,name,biz,sArg);

	}
	
	// 조회하는 거여요...
	function fSearch(){
		
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/manage/consideration.do?method=popupListContract' />";
        frm.submit();
        
        // clm/manage/consideration.do?method=popupListContract
	}
	
	
	/**
	* 페이징 function
	*/
	function goPage(pgNum){
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/manage/consideration.do?method=popupListContract' />";
		frm.method.value = "popupListContract";
		frm.curPage.value = pgNum;
		frm.submit();
	}
	
	/**
	* 취소
	*/
	function cancle(){
		if(confirm("<spring:message code='secfw.msg.ask.cancel' />")){
			
			opener.setContract('','','','');
			
			window.close();
		}
	}
	
	/**
	* 확인
	*/
	function con(){
        
		if(confirm("<spring:message code="clm.page.msg.manage.announce107" />")){
			window.close();
		}
		
	}
	
	/**
	* 상세 페이지 팝업을 위한 스크립트
	
	function goDetail(cnsdreq_id){
		
		//mUrl = "/clm/manage/completion.do?method=listContract&menu_id=20110803091537445_0000000053&conListGu=Z1000&cnsdreq_id="+cnsdreq_id;
		
		PopUpWindowOpen('/clm/manage/completion.do?method=listContract&menu_id=20110803091537445_0000000053&conListGu=Z1000&cnsdreq_id='+cnsdreq_id, 900, 600, true,"detailPopUp");
		
		
	}
	*/
	
	// 상세 페이지 팝업오픈
	function PopUpWindowOpen(surl, popupwidth, popupheight, bScroll, popName) {
		if( popupwidth  > window.screen.width )
			popupwidth = window.screen.width;
		if( popupheight > window.screen.height )
			popupheight = window.screen.height; 
			
		if( isNaN(parseInt(popupwidth)) ){
			Top  = (window.screen.availHeight - 600) / 2;
			Left = (window.screen.availWidth  - 800) / 2;
		} else {
			Top  = (window.screen.availHeight - popupheight)  / 2;
			Left = (window.screen.availWidth  - popupwidth) / 2;
		}

		if (Top < 0) Top = 0;
		if (Left < 0) Left = 0;
		
		popupwidth = parseInt(popupwidth) + 10 ;
		popupheight = parseInt(popupheight) + 29 ;
		
		var Feture = "toolbar=0, location=0, status=1, menubar=0, scrollbars=yes, resizable=yes, top="+Top+", left="+Left+", width="+popupwidth+", height="+popupheight;
		PopUpWindow = window.open(surl, popName , Feture);

		PopUpWindow.focus();
		
	}
	
	
</script>
</head>
<body class="pop">
<form:form name="frm" id='frm' method="post" autocomplete="off">
    <input type="hidden" name="menu_id" id="menu_id" value="<c:out value='${command.menu_id}'/>" />
    <input type="hidden" name="curPage" id="curPage" value="<c:out value='${command.curPage}'/>" />
    
    <input type="hidden" name="arg" id="arg" value="<c:out value='${command.arg}'/>"/>
    
    <input type="hidden" name="rel_type1" id="rel_type1" value="<c:out value='${command.rel_type1}'/>"/>
    
    <input type="hidden" name="cnsdreq_id" value="" />
    <input type="hidden" name="conListGu" value="Z1000" />
    <input type="hidden" name="method" value="" />
    
   
<!-- header -->
<h1><spring:message code="clm.page.msg.manage.reviewReq" /></h1>
<!-- //header -->
<div class="pop_area">
	<!-- Popup Size 840*600 -->
	<div class="h_600">
		<div class="pop_content mt10">
		<!-- container -->	
			<!-- title -->
			<div class="title">
				<h3><spring:message code="clm.page.msg.manage.relContList" /></h3>
			</div>
			<!-- //title -->
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
									<col />
									<col />
									<col />
									<col />
								</colgroup>
								<tr>
									<th><spring:message code="clm.page.msg.manage.contName" /></th>
									<td><input type="text" name="search_cntrt_nm" style="width:160px" class="text_full" value="<c:out value='${command.search_cntrt_nm}'/>"/></td>
									<th><spring:message code="clm.page.msg.manage.chrgPerson" /><span class="astro"></span></th>
							        <td><input type="text" name="search_req_nm" id="search_req_nm" style="width:100px" class="text_full" value="<c:out value='${command.search_req_nm}'/>" /></td>
									<!-- <th>의뢰일<span class="astro"></span></th>
									<td>
										 <input type="text" name="srch_regdt1" id="srch_regdt1" class="text_calendar02"/>~
								         <input type="text" name="srch_regdt2" id="srch_regdt2" class="text_calendar02"/>
									</td> -->
								</tr>
							</table>
						</td>
						<td class="vb tC"><a href="javascript:fSearch();"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="clm.page.msg.common.inquire" htmlEscape="true" />"/></a></td>
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
						<col width="45%"/>
						<col width="10%"/>
						<col width="8%"/>
						<col width="11%"/>						
						<col width="10%"/>
						<col width="10%"/>
						<col width="19%"/>
					  </colgroup>
					  <thead>
						<tr>
						  <th><spring:message code="clm.page.msg.common.select" /></th>
						  <th><spring:message code="clm.page.msg.manage.contName" /></th>
						  <th><spring:message code="clm.page.msg.manage.contType" /></th>
						  <th><spring:message code="clm.page.msg.manage.chrgPerson" /></th>
						  <th><spring:message code="clm.page.msg.manage.regiDate" /></th>
						  <th>Step</th>
						  <th><spring:message code="clm.page.msg.manage.status" /></th>
						  <!-- <th>Biz관계</th>-->
						</tr>
					  </thead>
					  <tbody>
<%
						if(pageUtil.getTotalRow() > 0) {
							for(int i=0; i<resultList.size(); i++) {
								ListOrderedMap lom = (ListOrderedMap)resultList.get(i);
								
					   String mUrl = ""; // 각 단계로의 상세 
					   String pd = (String)lom.get("prcs_depth"); // 프로세스 단계
					   String cnsdreq_id = (String)lom.get("cnsdreq_id");
					   
					   
					   
							mUrl = "/clm/manage/completion.do?method=listContract&menu_id=20110803091537445_0000000053&conListGu=Z1000&cnsdreq_id="+cnsdreq_id;
					   
								
%>
						<tr>
						  <td><input type="radio" name="check_item" value="<%=lom.get("cntrt_id") %>" onclick="javascript:parentContract('<%=lom.get("cntrt_id") %>','<%=lom.get("cntrt_nm") %>','<%=lom.get("biz_clsfcn") %>','<c:out value='${command.arg}'/>');"></td>
						  <td class="tL" title="<%=lom.get("cntrt_nm") %>">
						  <a href="javascript:goDetail('<%=lom.get("cnsdreq_id") %>');"><%=lom.get("cntrt_nm") %></a>
						  </td>
						  <td class="tL" align="center"><%=lom.get("biz_clsfcn_nm") %></td>
						  <td class="tL"><%=lom.get("reqman_nm") %></td>
						  <td class="tL"><%=DateUtil.formatDate((String)(lom.get("reg_dt")), "-") %></td>
						  <td class="tL"><%=lom.get("prcs_depth_nm") %></td>
						  <td><%=lom.get("depth_status_nm") %></td>
						  <!--<td><%=lom.get("biz_clsfcn_nm") %></td> -->
						  </tr>
						<% 		}
							}    															%>
					  </tbody>
					 </table>
					<!--// table01 -->

					<div class="total_num">
						Total : <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%><spring:message code="clm.page.msg.common.case" />
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
<!-- //body -->
<!--footer -->
<div class="pop_footer">
    <!-- button -->
	<div class="btn_wrap fR">
		<span class="btn_all_w">
		    <span class="save">
		    </span>
		        <a href="javascript:con()"><spring:message code='clm.page.button.confirm' /></a>
		    </span>
	        <span class="btn_all_w">
	            <span class="save">
	            </span>
	                <a href="javascript:cancle()"><spring:message code='clm.page.button.cancel' /></a>
		</span>
	</div>
	<!-- //button -->
</div>
<!-- //footer -->
</form:form>
</body>

</html>
