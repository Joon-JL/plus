<!DOCTYPE html>
<%@page import="com.sec.common.util.ClmsDataUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.manage.dto.MyNoticeForm" %>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%-- 
/**
 * 파  일  명 : Mynotice_l.jsp
 * 프로그램명 : MyNotice 목록
 * 설      명 : 
 * 작  성  자 : 유영섭
 * 작  성  일 : 2011.10.14
 */
--%>  
 <%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	List resultList = (List)request.getAttribute("resultList");
	MyNoticeForm command = (MyNoticeForm)request.getAttribute("command");
	
	%> 

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<LINK href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet">
<LINK href="<%=CSS%>/common.css"   type="text/css" rel="stylesheet">


<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>


<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
  
<script language="javascript">

$(document).ready(function(){
	initCal("srch_start_reg_dt");	
	initCal("srch_end_reg_dt","20110803091536762_0000000047");	
});	
		

	/**
	* 페이징 function
	*/
	function goPage(pgNum){
		var frm = document.frm;

		viewHiddenProgress(true) ;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/manage/myNotice.do' />"; 
		frm.method.value = "listMyNotice";
		frm.curPage.value = pgNum;
		
		frm.submit(); 
	}   
  
	/**
	* 버튼 동작 부분
	*/ 
	function pageAction(flag){
		
		var frm = document.frm;   
		frm.target = "_self";
		frm.action = "<c:url value='/clm/manage/myNotice.do' />";  

		if(flag == "search"){
			
			var startRegDt = frm.srch_start_reg_dt.value;
			var endRegDt = frm.srch_end_reg_dt.value;
			
			if((startRegDt != "" && endRegDt != "") && (startRegDt > endRegDt)){
		  		alert("<spring:message code='clm.msg.alert.board.srchAnnceDt' />");
		  		return;
		  	}
			
			viewHiddenProgress(true) ;
			frm.target = "_self";
		    frm.method.value = "listMyNotice";		    
			frm.curPage.value = "1";
			frm.submit();
	    }
	}
		
	
	
	/**
	* 상세내역 조회
	*/
	function detailView(mis_id,module_id){

		viewHiddenProgress(true) ;

		var frm = document.frm;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/manage/myNotice.do' />";      
		frm.method.value = "detailMyNotice";
		frm.mis_id.value = mis_id;
		frm.module_id.value = module_id;
		frm.submit();		
	}
	
	
	  

</script>
</head>

<body onLoad=''>
<!-- **************************** Calendar 관련 추가 영역 **************************** -->
<div id='popCal' style='position: absolute; visibility: hidden; ridge; width: 10; z-index:1;'>
<iframe name="popFrame" src="<c:url value='/secfw/popCalendar.do?method=goPopCalendar' />" frameborder="0" scrolling="no" width=196 height=188></iframe></div>
<script event=onclick() for=document> 

</script>


 <div id="wrap"> 
	
	
	<!-- container -->
	<div id="container">
		<!-- Location -->
		<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->
		<!-- Title -->
		<div class="title">
		<h3><spring:message code="clm.page.title.MyNotice.listTitle" /></h3>
		<!-- //title -->
		</div>
		
		<!-- content -->
		<div id="content">
		<div id="content_in">
		
		<!-- **************************** Form Setting **************************** -->
		<form:form name="frm" id='frm' method="post" autocomplete="off"> 
			<!-- search form -->
			<input type="hidden" name="method"   value=" " />
			<input type="hidden" name="menu_id"   value="<c:out value='${command.menu_id}'/>" />
			<!-- Detail Page Param -->
			<input type="hidden" name="mis_id" value="<c:out value='${command.mis_id}'/>" />
			<input type="hidden" name="module_id" value="<c:out value='${command.module_id}'/>" />
		    <input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>">
		<!-- //**************************** Form Setting ****************************-->
		
		<!-- search-->
			<div class="search_box">
				<div class="search_box_inner">
					<table class="search_tb" >
						<colgroup>
							<col/>
							<col width="75px"/>
						</colgroup>
						<tr>
							<td>
								<table class="search_form">
									<colgroup>
										<col width="8%"/>
										<col width="30%"/>
										<col width="8%"/>
										<col width="25%"/>
										<col width="8%"/>
										<col width="11%"/>
									</colgroup>
									<tr>
										<th><spring:message code="clm.page.field.MyNotice.srchMailTitle" /></th>
										<td>
											<input id="srch_keyword_mailtitle" name="srch_keyword_mailtitle" class='text' style="width:80%" value="<c:out value='${command.srch_keyword_mailtitle}'/>" OnKeyDown="if(event.keyCode==13) { pageAction('search'); }"/>
										</td>
										<th><spring:message code="clm.page.field.MyNotice.srchRegDt" /></th>
										<td>            
										<input type="text" name="srch_start_reg_dt" id="srch_start_reg_dt"   readonly="readonly"  value="<c:out value='${command.srch_start_reg_dt}'/>" class="text_calendar02"/>								
											~
											<input type="text" name="srch_end_reg_dt" id="srch_end_reg_dt"  readonly="readonly"  value="<c:out value='${command.srch_end_reg_dt}'/>"  class="text_calendar02"/>
										</td>	
<%-- 										<th><spring:message code="clm.page.field.MyNotice.srchAddressee" /></th>	 --%>
<!-- 										<td> -->
<%-- 											<input id="srch_Addressee" name="srch_Addressee" class='text' style="width:80%" value="<c:out value='${command.srch_Addressee}'/>" /> --%>
<!-- 										</td> -->
									</tr>
								</table>
							</td>
							<td class="vb tC">
								<a href="javascript:pageAction('search');"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="secfw.page.button.searchView" />"/></a>
							</td>
						</tr>
					</table>
				</div>
			</div>			
			<!--//search-->
	

			<!-- list -->
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic mt20">
				<colgroup>
					<col width="70%"/>
					<col width="20%" />   
					<col width="10%"/>
				</colgroup>
			  	<thead>
					<tr>
						<th><spring:message code="clm.page.field.MyNotice.MailTitle1" /></th>
						<th><spring:message code="clm.page.field.MyNotice.Addressee" /></th>
						<th><spring:message code="clm.page.field.MyNotice.regdt" /></th>
					</tr>
				</thead>
			 	<tbody>
				<%   if(pageUtil.getTotalRow() > 0){
						int idx = (pageUtil.getThisPage() -1)*pageUtil.getRowPerPage()+1;
						    for(idx=0;idx < resultList.size();idx++){
						    	ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
				%>
			    <tr <%if(idx == resultList.size()-1){%>class="end"<%}%> onMouseOut="this.className='<%if(idx == resultList.size()-1){%>end<%}%>';" onMouseOver="this.className='on <%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onClick="javascript:detailView('<%=lom.get("mis_id")%>','<%=lom.get("module_id")%>')"> 
			          <td class="tL txtover"><%=lom.get("subject")%></td>
			          <td class="tL"><%=lom.get("name")%></a></td>
			          <td class="tC"><%=lom.get("create_date")%></td>
			   </tr>
				<%
						    }
					}else{		   
				%>
			
					<tr>
					  <td colspan="3" class="tC"><spring:message code="secfw.msg.succ.noResult" /></td>
					</tr>
				<%}%> 
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
		
          
	</div>
	<!-- //container -->
</div>

 <!-- footer -->
		   <script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- //footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</body>
</html>
